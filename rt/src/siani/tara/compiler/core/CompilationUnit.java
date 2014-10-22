package siani.tara.compiler.core;

import siani.tara.compiler.codegeneration.java.JavaCodeGenerator;
import siani.tara.compiler.core.errorcollection.CompilationFailedException;
import siani.tara.compiler.core.operation.ModelToJavaOperation;
import siani.tara.compiler.core.operation.Operation;
import siani.tara.compiler.core.operation.SrcToClassOperation;
import siani.tara.compiler.core.operation.model.*;
import siani.tara.compiler.core.operation.module.MergeToModelOperation;
import siani.tara.compiler.core.operation.module.ModuleUnitOperation;
import siani.tara.compiler.core.operation.sourceunit.ImportDataOperation;
import siani.tara.compiler.core.operation.sourceunit.MarkOperation;
import siani.tara.compiler.core.operation.sourceunit.ParseOperation;
import siani.tara.compiler.core.operation.sourceunit.SourceUnitOperation;
import siani.tara.compiler.rt.TaraRtConstants;
import siani.tara.lang.Model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class CompilationUnit extends ProcessingUnit {

	private static final Logger LOG = Logger.getLogger(CompilationUnit.class.getName());
	private final boolean pluginGeneration;
	protected ProgressCallback progressCallback;
	private Map<String, SourceUnit> sourceUnits;
	private Model model;
	private LinkedList<Operation>[] phaseOperations;
	private SrcToClassOperation classGeneration = new SrcToClassOperation() {
		@Override
		public void call() throws CompilationFailedException {
			System.out.println(TaraRtConstants.PRESENTABLE_MESSAGE + "Generating classes");
			JavaCodeGenerator generator = new JavaCodeGenerator(configuration);
			//generator.generate();
		}
	};

	private SourceUnitOperation output = new SourceUnitOperation() {
		public void call(SourceUnit taraClass) throws CompilationFailedException {
			//TODO
		}
	};

	public CompilationUnit(boolean pluginGeneration, CompilerConfiguration configuration) {
		super(configuration, null);
		this.pluginGeneration = pluginGeneration;
		this.sourceUnits = new HashMap<>();
		this.phaseOperations = new LinkedList[10];
		for (int i = 0; i < this.phaseOperations.length; i++)
			this.phaseOperations[i] = new LinkedList();
		addPhaseOperation(new ParseOperation(this.errorCollector), Phases.PARSING);
		addPhaseOperation(new ImportDataOperation(this.errorCollector), Phases.CONVERSION);
		addPhaseOperation(new MergeToModelOperation(this), Phases.CONVERSION);
		addPhaseOperation(new SemanticPreAnalysisOperation(this), Phases.SEMANTIC_PRE_ANALYSIS);
		addPhaseOperation(new ModelDependencyResolutionOperation(this), Phases.DEPENDENCY_RESOLUTION);
		addPhaseOperation(new LinkToParentModelOperation(this), Phases.DEPENDENCY_RESOLUTION);
		addPhaseOperation(new SemanticAnalysisOperation(this), Phases.SEMANTIC_ANALYSIS);
		addPhaseOperation(new ModelToJavaOperation(this), Phases.CLASS_GENERATION);
		addPhaseOperation(classGeneration, Phases.CLASS_GENERATION);
		if (pluginGeneration) addPhaseOperation(new SaveModelOperation(this), Phases.MODEL_GENERATION);
		addPhaseOperation(output, Phases.OUTPUT);
	}

	public void addPhaseOperation(Operation operation, int phase) {
		if ((phase < Phases.FIRST) || (phase > Phases.LAST))
			throw new IllegalArgumentException("phase " + phase + " is unknown");
		this.phaseOperations[phase].add(operation);
	}

	public boolean isPluginGeneration() {
		return pluginGeneration;
	}

	public SourceUnit addSource(SourceUnit source) {
		String name = source.getName();
		for (SourceUnit su : sourceUnits.values())
			if (name.equals(su.getName())) return su;
		this.sourceUnits.put(name, source);
		return source;
	}

	public String[] getSourceUnitNames() {
		return sourceUnits.keySet().toArray(new String[sourceUnits.keySet().size()]);
	}

	public Map<String, SourceUnit> getSourceUnits() {
		return sourceUnits;
	}

	public void compile() throws CompilationFailedException {
//		FileSystemUtils.removeDir(configuration.getOutDirectory());
		compile(Phases.ALL);
	}

	public void compile(int throughPhase) throws CompilationFailedException {
		gotoPhase(1);
		while ((Math.min(throughPhase, 9) >= this.phase) && (this.phase <= 9)) {
			processPhaseOperations(this.phase);
			if (this.progressCallback != null) this.progressCallback.call(this, this.phase);
			completePhase();
			nextPhase();
			applyToSourceUnits(new MarkOperation(this));
		}
		this.errorCollector.failIfErrors();
	}

	public void applyToSourceUnits(SourceUnitOperation mark) throws CompilationFailedException {
		SourceUnit source;
		for (String name : this.sourceUnits.keySet()) {
			source = this.sourceUnits.get(name);
			if ((source.phase < this.phase) || ((source.phase == this.phase) && (!source.phaseComplete)))
				mark.call(source);
		}
		getErrorCollector().failIfErrors();
	}

	private void processPhaseOperations(int ph) {
		List<Operation> ops = this.phaseOperations[ph];
		for (Operation next : ops)
			doPhaseOperation(next);
	}

	private void doPhaseOperation(Operation operation) {
		if (operation instanceof SourceUnitOperation)
			applyToSourceUnits((SourceUnitOperation) operation);
		else if (operation instanceof SrcToClassOperation)
			((SrcToClassOperation) operation).call();
		else if (operation instanceof ModuleUnitOperation)
			((ModuleUnitOperation) operation).call(sourceUnits.values());
		else if (operation instanceof ModelOperation)
			((ModelOperation) operation).call(model);
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public abstract static class ProgressCallback {
		public abstract void call(ProcessingUnit paramProcessingUnit, int paramInt) throws CompilationFailedException;
	}

}
