package monet.tara.compiler.core;

import monet.tara.compiler.codegeneration.ClassGenerator;
import monet.tara.compiler.codegeneration.intellij.FileSystemUtils;
import monet.tara.compiler.codegeneration.intellij.PluginGenerator;
import monet.tara.lang.ASTNode;
import monet.tara.compiler.core.errorcollection.*;
import monet.tara.compiler.core.errorcollection.message.Message;
import monet.tara.compiler.core.errorcollection.semantic.SemanticError;
import monet.tara.compiler.core.operation.ModuleUnitOperation;
import monet.tara.compiler.core.operation.Operation;
import monet.tara.compiler.core.operation.SourceUnitOperation;
import monet.tara.compiler.core.operation.SrcToClassOperation;
import monet.tara.compiler.rt.TaraRtConstants;
import monet.tara.compiler.semantic.SemanticAnalyzer;

import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

public class CompilationUnit extends ProcessingUnit {

	private static final Logger LOG = Logger.getLogger(CompilationUnit.class.getName());
	private final boolean pluginGeneration;
	protected Map<String, SourceUnit> sources;
	protected ProgressCallback progressCallback;
	private SourceUnitOperation convert = new SourceUnitOperation() {
		public void call(SourceUnit source) throws CompilationFailedException {
			try {
				System.out.println(TaraRtConstants.PRESENTABLE_MESSAGE + "Converting");
				source.convert();
				getErrorCollector().failIfErrors();
			} catch (TaraException e) {
				LOG.severe("Error during conversion");
				getErrorCollector().addError(Message.create(e.getMessage(), source));
			}
		}
	};
	private SourceUnitOperation parsing = new SourceUnitOperation() {
		public void call(SourceUnit source) throws CompilationFailedException {
			try {
				System.out.println(TaraRtConstants.PRESENTABLE_MESSAGE + "Parsing");
				source.parse();
				getErrorCollector().failIfErrors();
			} catch (IOException e) {
				LOG.severe("Error during Parsing");
				getErrorCollector().addError(Message.create(e.getMessage(), CompilationUnit.this));
			} catch (SyntaxException e) {
				LOG.severe("Syntax error during Parsing");
				getErrorCollector().addError(Message.create(e, source));
			}
		}
	};
	private ModuleUnitOperation semantic = new ModuleUnitOperation() {
		public void call(Collection<SourceUnit> sources) throws CompilationFailedException {
			try {
				System.out.println(TaraRtConstants.PRESENTABLE_MESSAGE + "Analyzing semantic");
				SemanticAnalyzer analyzer = new SemanticAnalyzer(sources);
				analyzer.analyze();
			} catch (SemanticException e) {
				for (SemanticError error : e.getErrors())
					if (error instanceof SemanticError.FatalError) {
						LOG.severe("Error during semantic analyze");
						getErrorCollector().addError(Message.create(error, getSourceFromFile(error.getNode())));
					} else
						getErrorCollector().addWarning(2, error.getMessage(), getSourceFromFile(error.getNode()));
			}
		}

		private SourceUnit getSourceFromFile(ASTNode node) {
			if (node != null)
				for (String name : sources.keySet())
					if (name.equals(node.getFile())) return sources.get(name);
			return null;
		}
	};
	private LinkedList<Operation>[] phaseOperations;
	private SrcToClassOperation classGeneration = new SrcToClassOperation() {
		@Override
		public void call() throws CompilationFailedException {
			System.out.println(TaraRtConstants.PRESENTABLE_MESSAGE + "Generating classes");
			ClassGenerator generator = new ClassGenerator(configuration);
			//generator.generate();
		}
	};

	private ModuleUnitOperation pluginGenerationOperation = new ModuleUnitOperation() {
		@Override
		public void call(Collection<SourceUnit> units) throws CompilationFailedException {
			try {
				System.out.println(TaraRtConstants.PRESENTABLE_MESSAGE + "Generating plugin");
				PluginGenerator generator = new PluginGenerator(configuration);
				generator.generate(units);
				getErrorCollector().failIfErrors();
			} catch (TaraException e) {
				LOG.severe("Error during plugin generation: " + e.getMessage() + "\n");
				throw new CompilationFailedException(phase, CompilationUnit.this);
			}
		}
	};
	private SourceUnitOperation output = new SourceUnitOperation() {
		public void call(SourceUnit taraClass) throws CompilationFailedException {
			//TODO
		}
	};
	private SourceUnitOperation mark = new SourceUnitOperation() {
		public void call(SourceUnit source) throws CompilationFailedException {
			if (source.phase < CompilationUnit.this.phase) source.gotoPhase(CompilationUnit.this.phase);
			if ((source.phase == CompilationUnit.this.phase) && (CompilationUnit.this.phaseComplete) && (!source.phaseComplete))
				source.completePhase();
		}
	};

	public CompilationUnit(boolean pluginGeneration, CompilerConfiguration configuration, ErrorCollector errorCollector) {
		super(configuration, errorCollector);
		this.pluginGeneration = pluginGeneration;
		this.sources = new HashMap<>();
		this.phaseOperations = new LinkedList[10];
		for (int i = 0; i < this.phaseOperations.length; i++)
			this.phaseOperations[i] = new LinkedList();
		addPhaseOperation(parsing, Phases.PARSING);
		addPhaseOperation(convert, Phases.CONVERSION);
		addPhaseOperation(semantic, Phases.SEMANTIC_ANALYSIS);
		addPhaseOperation(classGeneration, Phases.CLASS_GENERATION);
		if (pluginGeneration) addPhaseOperation(pluginGenerationOperation, Phases.PLUGIN_GENERATION);
		addPhaseOperation(output, Phases.OUTPUT);

	}

	public void addPhaseOperation(Operation operation, int phase) {
		if ((phase < 1) || (phase > 8)) throw new IllegalArgumentException("phase " + phase + " is unknown");
		this.phaseOperations[phase].add(operation);
	}

	public boolean isPluginGeneration() {
		return pluginGeneration;
	}

	public SourceUnit addSource(SourceUnit source) {
		String name = source.getName();
		for (SourceUnit su : sources.values())
			if (name.equals(su.getName())) return su;
		this.sources.put(name, source);
		return source;
	}

	public String[] getSources() {
		return sources.keySet().toArray(new String[sources.keySet().size()]);
	}

	public void compile() throws CompilationFailedException {
		FileSystemUtils.removeDir(configuration.getTempDirectory());
		compile(Phases.ALL);
	}

	public void compile(int throughPhase) throws CompilationFailedException {
		gotoPhase(1);
		while ((Math.min(throughPhase, 8) >= this.phase) && (this.phase <= 8)) {
			processPhaseOperations(this.phase);
			if (this.progressCallback != null) this.progressCallback.call(this, this.phase);
			completePhase();
			nextPhase();
			applyToSourceUnits(this.mark);
		}
		this.errorCollector.failIfErrors();
	}

	private void processPhaseOperations(int ph) {
		List<Operation> ops = this.phaseOperations[ph];
		for (Operation next : ops)
			doPhaseOperation(next);
	}

	private void doPhaseOperation(Operation operation) {
		if (operation instanceof SourceUnitOperation)
			applyToSourceUnits((SourceUnitOperation) operation);
		if (operation instanceof SrcToClassOperation)
			((SrcToClassOperation) operation).call();
		if (operation instanceof ModuleUnitOperation)
			((ModuleUnitOperation) operation).call(sources.values());
	}

	public void applyToSourceUnits(SourceUnitOperation body) throws CompilationFailedException {
		SourceUnit source;
		for (String name : this.sources.keySet()) {
			source = this.sources.get(name);
			if ((source.phase < this.phase) || ((source.phase == this.phase) && (!source.phaseComplete)))
				body.call(source);
		}
		getErrorCollector().failIfErrors();
	}

	public abstract static class ProgressCallback {
		public abstract void call(ProcessingUnit paramProcessingUnit, int paramInt) throws CompilationFailedException;
	}
}