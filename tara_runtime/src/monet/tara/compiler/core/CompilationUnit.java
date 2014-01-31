package monet.tara.compiler.core;

import monet.tara.compiler.core.error_collection.CompilationFailedException;
import monet.tara.compiler.core.error_collection.ErrorCollector;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

public class CompilationUnit extends ProcessingUnit {

	protected Map<String, SourceUnit> sources;
	private LinkedList[] phaseOperations;
	protected ProgressCallback progressCallback;

	private SourceUnitOperation parsing = new SourceUnitOperation() {
		public void call(SourceUnit source) throws CompilationFailedException {
			source.parse();
		}
	};
	private ModuleUnitOperation semantic = new ModuleUnitOperation() {
		public void call(SourceUnit[] source) throws CompilationFailedException {
			//source.convert();
//			CompilationUnit.this.AST.addModule(source.getAST());
//			if (CompilationUnit.this.progressCallback != null)
//				CompilationUnit.this.progressCallback.call(source, CompilationUnit.this.phase);
		}
	};

	private SourceUnitOperation convert = new SourceUnitOperation() {
		public void call(SourceUnit source) throws CompilationFailedException {
			source.convert();
//			CompilationUnit.this.AST.addModule(source.getAST());
//			if (CompilationUnit.this.progressCallback != null)
//				CompilationUnit.this.progressCallback.call(source, CompilationUnit.this.phase);
		}
	};


	private SourceUnitOperation mark = new SourceUnitOperation() {
		public void call(SourceUnit source) throws CompilationFailedException {
			if (source.phase < CompilationUnit.this.phase)
				source.gotoPhase(CompilationUnit.this.phase);
			if ((source.phase == CompilationUnit.this.phase) && (CompilationUnit.this.phaseComplete) && (!source.phaseComplete)) {
				source.completePhase();
			}
		}
	};

	private SourceUnitOperation output = new SourceUnitOperation() {
		public void call(SourceUnit taraclass) throws CompilationFailedException {


		}
	};

	public CompilationUnit(boolean pluginGeneration, CompilerConfiguration configuration, ErrorCollector errorCollector) {
		super(configuration, errorCollector);
		this.sources = new HashMap();

		this.phaseOperations = new LinkedList[10];
		for (int i = 0; i < this.phaseOperations.length; i++)
			this.phaseOperations[i] = new LinkedList();
		addPhaseOperation(parsing, Phases.PARSING);
	}

	public void addPhaseOperation(SourceUnitOperation sourceUnitOperation, int phase) {
		if ((phase < 0) || (phase > 9)) throw new IllegalArgumentException("phase " + phase + " is unknown");
		this.phaseOperations[phase].add(sourceUnitOperation);
	}

	public SourceUnit addSource(SourceUnit source) {
		String name = source.getName();
		for (SourceUnit su : sources.values())
			if (name.equals(su.getName())) return su;
		this.sources.put(name, source);
		return source;
	}

	public void addSources(String[] paths) {
		for (String path : paths)
			addSource(new File(path));
	}

	public void addSources(File[] files) {
		for (File file : files)
			addSource(file);
	}

	public SourceUnit addSource(File file) {
		return addSource(new SourceUnit(file, this.configuration, getErrorCollector()));
	}


	public String getPhaseDescription() {
		return Phases.getDescription(this.phase);
	}

	public Iterator<SourceUnit> iterator() {
		return new Iterator() {
			Iterator<String> nameIterator = CompilationUnit.this.sources.keySet().iterator();

			public boolean hasNext() {
				return this.nameIterator.hasNext();
			}

			public SourceUnit next() {
				String name = (String) this.nameIterator.next();
				return (SourceUnit) CompilationUnit.this.sources.get(name);
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
	}

	public void applyToSourceUnits(SourceUnitOperation body) throws CompilationFailedException {
		SourceUnit source;
		for (String name : this.sources.keySet()) {
			source = this.sources.get(name);
			if ((source.phase < this.phase) || ((source.phase == this.phase) && (!source.phaseComplete)))
				try {
					body.call(source);
				} catch (CompilationFailedException e) {
					throw e;
				}
		}
		getErrorCollector().failIfErrors();
	}

	public static abstract class ProgressCallback {
		public abstract void call(ProcessingUnit paramProcessingUnit, int paramInt)
			throws CompilationFailedException;
	}

	public static abstract class SourceUnitOperation {
		public abstract void call(SourceUnit unit) throws CompilationFailedException;
	}

	public static abstract class ModuleUnitOperation {
		public abstract void call(SourceUnit[] units) throws CompilationFailedException;
	}
}
