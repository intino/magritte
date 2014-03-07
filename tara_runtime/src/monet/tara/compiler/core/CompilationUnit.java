package monet.tara.compiler.core;

import monet.tara.compiler.code_generation.ClassGenerator;
import monet.tara.compiler.core.error_collection.CompilationFailedException;
import monet.tara.compiler.core.error_collection.ErrorCollector;
import monet.tara.compiler.core.error_collection.TaraException;
import monet.tara.compiler.core.operation.ModuleUnitOperation;
import monet.tara.compiler.core.operation.Operation;
import monet.tara.compiler.core.operation.SourceUnitOperation;
import monet.tara.compiler.core.operation.SrcToClassOperation;
import monet.tara.intellij.plugin_generation.PluginGenerator;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

public class CompilationUnit extends ProcessingUnit {

	protected Map<String, SourceUnit> sources;
	protected ProgressCallback progressCallback;
	private LinkedList[] phaseOperations;

	private SourceUnitOperation parsing = new SourceUnitOperation() {
		public void call(SourceUnit source) throws CompilationFailedException {
			source.parse();
		}
	};
	private ModuleUnitOperation semantic = new ModuleUnitOperation() {
		public void call(SourceUnit[] source) throws CompilationFailedException {
			//source.analyze();
//			CompilationUnit.this.AST.addModule(source.getAST());
//			if (CompilationUnit.this.progressCallback != null)
//				CompilationUnit.this.progressCallback.call(source, CompilationUnit.this.phase);
		}
	};

	private SourceUnitOperation convert = new SourceUnitOperation() {
		public void call(SourceUnit source) throws CompilationFailedException {
			try {
				source.convert();
			} catch (TaraException ignored) {
				System.err.print("Error during conversion");
				throw new CompilationFailedException(phase, CompilationUnit.this);
			}
		}
	};

	private SrcToClassOperation classGeneration = new SrcToClassOperation() {
		@Override
		public void call() throws CompilationFailedException {
			ClassGenerator generator = new ClassGenerator(configuration);
			generator.generate();
		}
	};

	private ModuleUnitOperation pluginGenerationOperation = new ModuleUnitOperation() {
		@Override
		public void call(SourceUnit[] units) throws CompilationFailedException {
			try {
				PluginGenerator generator = new PluginGenerator(configuration);
				generator.generate(units);
			} catch (TaraException ignored) {
				System.err.print("Error during conversion");
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
			if (source.phase < CompilationUnit.this.phase)
				source.gotoPhase(CompilationUnit.this.phase);
			if ((source.phase == CompilationUnit.this.phase) && (CompilationUnit.this.phaseComplete) && (!source.phaseComplete))
				source.completePhase();
		}
	};

	public CompilationUnit(boolean pluginGeneration, CompilerConfiguration configuration, ErrorCollector errorCollector) {
		super(configuration, errorCollector);
		this.sources = new HashMap<>();
		this.phaseOperations = new LinkedList[10];
		for (int i = 0; i < this.phaseOperations.length; i++)
			this.phaseOperations[i] = new LinkedList();
		addPhaseOperation(parsing, Phases.PARSING);
		addPhaseOperation(semantic, Phases.SEMANTIC_ANALYSIS);
		addPhaseOperation(convert, Phases.CONVERSION);
		addPhaseOperation(classGeneration, Phases.CLASS_GENERATION);
		if (pluginGeneration) addPhaseOperation(pluginGenerationOperation, Phases.PLUGIN_GENERATION);
		addPhaseOperation(output, Phases.OUTPUT);

	}

	public void addPhaseOperation(Operation operation, int phase) {
		if ((phase < 0) || (phase > 9)) throw new IllegalArgumentException("phase " + phase + " is unknown");
		this.phaseOperations[phase].add(operation);
	}


	public SourceUnit addSource(SourceUnit source) {
		String name = source.getName();
		for (SourceUnit su : sources.values())
			if (name.equals(su.getName())) return su;
		this.sources.put(name, source);
		return source;
	}


	public String getPhaseDescription() {
		return Phases.getDescription(this.phase);
	}

	public Iterator iterator() {
		return new Iterator() {
			Iterator<String> nameIterator = CompilationUnit.this.sources.keySet().iterator();

			public boolean hasNext() {
				return this.nameIterator.hasNext();
			}

			public SourceUnit next() {
				String name = this.nameIterator.next();
				return CompilationUnit.this.sources.get(name);
			}

			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}


	public void compile() throws CompilationFailedException {
		compile(9);
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
		LinkedList ops = this.phaseOperations[ph];
		for (Object next : ops)
			doPhaseOperation(next);
	}

	private void doPhaseOperation(Object operation) {
		if ((operation instanceof SourceUnitOperation))
			applyToSourceUnits((SourceUnitOperation) operation);
		if ((operation instanceof SrcToClassOperation))
			((SrcToClassOperation) operation).call();
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
		public abstract void call(ProcessingUnit paramProcessingUnit, int paramInt)
			throws CompilationFailedException;
	}
}
