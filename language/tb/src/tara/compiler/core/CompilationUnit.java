package tara.compiler.core;

import tara.compiler.codegeneration.FileSystemUtils;
import tara.compiler.core.errorcollection.CompilationFailedException;
import tara.compiler.core.operation.MorphGenerationOperation;
import tara.compiler.core.operation.Operation;
import tara.compiler.core.operation.StashGenerationOperation;
import tara.compiler.core.operation.model.GenerateLanguageOperation;
import tara.compiler.core.operation.model.ModelDependencyResolutionOperation;
import tara.compiler.core.operation.model.ModelOperation;
import tara.compiler.core.operation.model.SemanticAnalysisOperation;
import tara.compiler.core.operation.module.MergeToModelOperation;
import tara.compiler.core.operation.module.ModuleUnitOperation;
import tara.compiler.core.operation.sourceunit.ImportDataOperation;
import tara.compiler.core.operation.sourceunit.MarkOperation;
import tara.compiler.core.operation.sourceunit.ParseOperation;
import tara.compiler.core.operation.sourceunit.SourceUnitOperation;
import tara.compiler.model.Model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public final class CompilationUnit extends ProcessingUnit {

	protected ProgressCallback progressCallback;
	private Map<String, SourceUnit> sourceUnits;
	private Model model;
	private List<Operation>[] phaseOperations;
	private Map<String, List<String>> outputItems = new HashMap<>();

	public CompilationUnit(CompilerConfiguration configuration) {
		super(configuration, null);
		this.sourceUnits = new HashMap<>();
		this.phaseOperations = new LinkedList[Phases.ALL];
		for (int i = 0; i < this.phaseOperations.length; i++) this.phaseOperations[i] = new LinkedList();
		addAllPhaseOperations();
	}

	private void addAllPhaseOperations() {
		addPhaseOperation(new ParseOperation(this.errorCollector), Phases.PARSING);
		addPhaseOperation(new ImportDataOperation(this.errorCollector), Phases.CONVERSION);
		addPhaseOperation(new MergeToModelOperation(this), Phases.CONVERSION);
		addPhaseOperation(new ModelDependencyResolutionOperation(this), Phases.DEPENDENCY_RESOLUTION);
		addPhaseOperation(new SemanticAnalysisOperation(this), Phases.SEMANTIC_ANALYSIS);
		addPhaseOperation(new MorphGenerationOperation(this), Phases.CODE_GENERATION);
		addPhaseOperation(new StashGenerationOperation(this), Phases.CODE_GENERATION);
		addPhaseOperation(new GenerateLanguageOperation(this), Phases.LANGUAGE_GENERATION);
	}

	public void addPhaseOperation(Operation operation, int phase) {
		if ((phase < Phases.FIRST) || (phase > Phases.LAST))
			throw new IllegalArgumentException("phase " + phase + " is unknown");
		this.phaseOperations[phase].add(operation);
	}

	public void addOutputItems(Map<String, List<String>> paths) {
		outputItems.putAll(paths);
	}

	public SourceUnit addSource(SourceUnit source) {
		String name = source.getName();
		for (SourceUnit su : sourceUnits.values())
			if (name.equals(su.getName())) return su;
		this.sourceUnits.put(name, source);
		return source;
	}

	public Map<String, SourceUnit> getSourceUnits() {
		return sourceUnits;
	}

	public void compile() throws CompilationFailedException {
		FileSystemUtils.removeDir(configuration.getOutDirectory());
		compile(Phases.ALL);
	}

	public void compile(int throughPhase) throws CompilationFailedException {
		gotoPhase(1);
		while ((Math.min(throughPhase, Phases.LAST) >= this.phase) && (this.phase <= Phases.LAST)) {
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
		ops.forEach(this::doPhaseOperation);
	}

	private void doPhaseOperation(Operation operation) {
		if (operation instanceof SourceUnitOperation)
			applyToSourceUnits((SourceUnitOperation) operation);
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

	public Map<String, List<String>> getOutputItems() {
		return outputItems;
	}

	public abstract static class ProgressCallback {
		public abstract void call(ProcessingUnit paramProcessingUnit, int paramInt) throws CompilationFailedException;
	}

}
