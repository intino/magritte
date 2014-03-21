package monet.tara.compiler.core;

import monet.tara.compiler.code_generation.ClassGenerator;
import monet.tara.compiler.code_generation.intellij.FileSystemUtils;
import monet.tara.compiler.code_generation.intellij.PluginGenerator;
import monet.tara.compiler.core.error_collection.*;
import monet.tara.compiler.core.error_collection.message.Message;
import monet.tara.compiler.core.error_collection.semantic.SemanticError;
import monet.tara.compiler.core.operation.ModuleUnitOperation;
import monet.tara.compiler.core.operation.Operation;
import monet.tara.compiler.core.operation.SourceUnitOperation;
import monet.tara.compiler.core.operation.SrcToClassOperation;
import monet.tara.compiler.rt.TaraRtConstants;
import monet.tara.compiler.semantic.SemanticAnalyzer;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class CompilationUnit extends ProcessingUnit {

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
				System.err.println("Error during conversion");
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
				System.err.println("Error during Parsing");
				getErrorCollector().addError(Message.create(e.getMessage(), CompilationUnit.this));
			} catch (SyntaxException e) {
				System.err.println("Syntax error during Parsing");
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
						System.err.println("Error during semantic analyze");
						getErrorCollector().addError(Message.create(error, getSourceFromFile(error.getNode().getFile())));
					} else
						getErrorCollector().addWarning(2, error.getMessage(), getSourceFromFile(error.getNode().getFile()));
			}
			getErrorCollector().failIfErrors();
		}

		private SourceUnit getSourceFromFile(String file) {
			for (String name : sources.keySet())
				if (name.equals(file)) return sources.get(name);
			return null;
		}
	};
	private LinkedList<Operation>[] phaseOperations;
	private SrcToClassOperation classGeneration = new SrcToClassOperation() {
		@Override
		public void call() throws CompilationFailedException {
			System.out.println("Generating classes");
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
				e.printStackTrace();
				System.err.print("Error during plugin generation");
				throw new CompilationFailedException(phase, CompilationUnit.this);
			}
		}
	};

	private SourceUnitOperation output = new SourceUnitOperation() {
		public void call(SourceUnit taraClass) throws CompilationFailedException {

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
		if ((phase < 0) || (phase > 9)) throw new IllegalArgumentException("phase " + phase + " is unknown");
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
		throughPhase = Math.min(throughPhase, 9);
		while ((throughPhase >= this.phase) && (this.phase <= 9)) {
			processPhaseOperations(this.phase);
			if (this.progressCallback != null) this.progressCallback.call(this, this.phase);
			completePhase();
			nextPhase();
			applyToSourceUnits(this.mark);
		}
		this.errorCollector.failIfErrors();
	}

	private void processPhaseOperations(int ph) {
		LinkedList<Operation> ops = this.phaseOperations[ph];
		for (Operation next : ops)
			doPhaseOperation(next);
	}

	private void doPhaseOperation(Operation operation) {
		if ((operation instanceof SourceUnitOperation))
			applyToSourceUnits((SourceUnitOperation) operation);
		if ((operation instanceof SrcToClassOperation))
			((SrcToClassOperation) operation).call();
		if ((operation instanceof ModuleUnitOperation))
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

	public static abstract class ProgressCallback {
		public abstract void call(ProcessingUnit paramProcessingUnit, int paramInt) throws CompilationFailedException;
	}
}
