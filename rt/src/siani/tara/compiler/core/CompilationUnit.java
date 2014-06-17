package siani.tara.compiler.core;

import siani.tara.compiler.codegeneration.ClassGenerator;
import siani.tara.compiler.codegeneration.intellij.FileSystemUtils;
import siani.tara.compiler.core.errorcollection.CompilationFailedException;
import siani.tara.compiler.core.errorcollection.ErrorCollector;
import siani.tara.compiler.core.operation.Operation;
import siani.tara.compiler.core.operation.SrcToClassOperation;
import siani.tara.compiler.core.operation.model.ModelOperation;
import siani.tara.compiler.core.operation.model.PluginUpdateOperation;
import siani.tara.compiler.core.operation.model.SemanticAnalysisOperation;
import siani.tara.compiler.core.operation.module.ASTDependencyResolutionOperation;
import siani.tara.compiler.core.operation.module.MergeToModelOperation;
import siani.tara.compiler.core.operation.module.ModuleUnitOperation;
import siani.tara.compiler.core.operation.sourceunit.ImportDataOperation;
import siani.tara.compiler.core.operation.sourceunit.MarkOperation;
import siani.tara.compiler.core.operation.sourceunit.ParseOperation;
import siani.tara.compiler.core.operation.sourceunit.SourceUnitOperation;
import siani.tara.compiler.rt.TaraRtConstants;
import siani.tara.lang.TreeWrapper;

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
	private TreeWrapper model;
	private LinkedList<Operation>[] phaseOperations;
	private ModelOperation pluginUpdateOperation = new PluginUpdateOperation(this);
	private SrcToClassOperation classGeneration = new SrcToClassOperation() {
		@Override
		public void call() throws CompilationFailedException {
			System.out.println(TaraRtConstants.PRESENTABLE_MESSAGE + "Generating classes");
			ClassGenerator generator = new ClassGenerator(configuration);
			//generator.generate();
		}
	};
	private SourceUnitOperation output = new SourceUnitOperation() {
		public void call(SourceUnit taraClass) throws CompilationFailedException {
			//TODO
		}
	};

	public CompilationUnit(boolean pluginGeneration, CompilerConfiguration configuration, ErrorCollector errorCollector) {
		super(configuration, errorCollector);
		this.pluginGeneration = pluginGeneration;
		this.sourceUnits = new HashMap<>();
		this.phaseOperations = new LinkedList[10];
		for (int i = 0; i < this.phaseOperations.length; i++)
			this.phaseOperations[i] = new LinkedList();
		addPhaseOperation(new ParseOperation(this.errorCollector), Phases.PARSING);
		addPhaseOperation(new ImportDataOperation(this.errorCollector), Phases.CONVERSION);
		addPhaseOperation(new MergeToModelOperation(this), Phases.CONVERSION);
		addPhaseOperation(new SemanticAnalysisOperation(this), Phases.SEMANTIC_ANALYSIS);
		addPhaseOperation(new ASTDependencyResolutionOperation(this), Phases.DEPENDENCY_RESOLUTION);
		addPhaseOperation(classGeneration, Phases.CLASS_GENERATION);
		if (pluginGeneration) addPhaseOperation(pluginUpdateOperation, Phases.PLUGIN_GENERATION);
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
		FileSystemUtils.removeDir(configuration.getTempDirectory());
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
		if (operation instanceof SrcToClassOperation)
			((SrcToClassOperation) operation).call();
		if (operation instanceof ModuleUnitOperation)
			((ModuleUnitOperation) operation).call(sourceUnits.values());
		if (operation instanceof ModelOperation)
			((ModelOperation) operation).call(model);
	}

	public TreeWrapper getModel() {
		return model;
	}



	public void setModel(TreeWrapper model) {
		this.model = model;
	}



	public abstract static class ProgressCallback {
		public abstract void call(ProcessingUnit paramProcessingUnit, int paramInt) throws CompilationFailedException;
	}

}
