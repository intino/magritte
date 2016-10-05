package tara.compiler.core;

import tara.compiler.codegeneration.FileSystemUtils;
import tara.compiler.core.errorcollection.CompilationFailedException;
import tara.compiler.core.operation.LayerGenerationOperation;
import tara.compiler.core.operation.Operation;
import tara.compiler.core.operation.StashGenerationOperation;
import tara.compiler.core.operation.model.*;
import tara.compiler.core.operation.module.ModuleUnitOperation;
import tara.compiler.core.operation.module.UnifyModelOperation;
import tara.compiler.core.operation.setup.SetupConfigurationOperation;
import tara.compiler.core.operation.setup.SetupOperation;
import tara.compiler.core.operation.sourceunit.MarkOperation;
import tara.compiler.core.operation.sourceunit.ModelGenerationOperation;
import tara.compiler.core.operation.sourceunit.ParseOperation;
import tara.compiler.core.operation.sourceunit.SourceUnitOperation;
import tara.compiler.model.Model;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static tara.compiler.core.Phases.*;

public final class CompilationUnit extends ProcessingUnit {

	private Map<String, SourceUnit> sourceUnits;
	private Model model;
	private List<Operation>[] phaseOperations;
	private Map<String, List<String>> outputItems = new HashMap<>();

	public CompilationUnit(CompilerConfiguration configuration) {
		super(configuration, null);
		this.sourceUnits = new HashMap<>();
		this.phaseOperations = new LinkedList[ALL];
		for (int i = 0; i < this.phaseOperations.length; i++) this.phaseOperations[i] = new LinkedList();
		addPhaseOperations();
	}

	private void addPhaseOperations() {
		addPhaseOperation(new SetupConfigurationOperation(this), INITIALIZATION);
		addPhaseOperation(new ParseOperation(this), PARSING);
		addPhaseOperation(new ModelGenerationOperation(this), CONVERSION);
		addPhaseOperation(new UnifyModelOperation(this), CONVERSION);
		addPhaseOperation(new ModelDependencyResolutionOperation(this), DEPENDENCY_RESOLUTION);
		addPhaseOperation(new ModelResolutionOperation(), MODEL_DEPENDENCY_RESOLUTION);
		addPhaseOperation(new SemanticAnalysisOperation(this), SEMANTIC_ANALYSIS);
		addPhaseOperation(new MetricResolutionOperation(this), POST_ANALYSIS_RESOLUTION);
		addPhaseOperation(new TableProfilingOperation(this), POST_ANALYSIS_RESOLUTION);
		addPhaseOperation(new NativeTransformationOperation(this), POST_ANALYSIS_RESOLUTION);
		addPhaseOperation(new LayerGenerationOperation(this), CODE_GENERATION);
		addPhaseOperation(new StashGenerationOperation(this), STASH_GENERATION);
		if (!configuration.isTest()) {
			addPhaseOperation(new RefactorHistoryOperation(this), REFACTOR_HISTORY);
			addPhaseOperation(new GenerateLanguageOperation(this), LANGUAGE_GENERATION);
		}
	}

	private void addPhaseOperation(Operation operation, int phase) {
		if ((phase < FIRST) || (phase > LAST))
			throw new IllegalArgumentException("phase " + phase + " is unknown");
		if (isExcludedPhase(phase)) return;
		this.phaseOperations[phase].add(operation);
	}

	private boolean isExcludedPhase(int phase) {
		return configuration.getExcludedPhases().contains(phase);
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
		compile(ALL);
	}

	public static void cleanOut(CompilerConfiguration configuration) {
		final String genLanguagePackage = configuration.workingPackage() == null ? configuration.getModule() : configuration.workingPackage();
		File out = new File(configuration.getOutDirectory(), genLanguagePackage.toLowerCase());
		if (!configuration.isStashGeneration() && out.exists()) FileSystemUtils.removeDir(out);
	}

	private void compile(int throughPhase) throws CompilationFailedException {
		gotoPhase(1);
		while ((Math.min(throughPhase, LAST) >= this.phase) && (this.phase <= LAST)) {
			processPhaseOperations(this.phase);
			completePhase();
			nextPhase();
			applyToSourceUnits(new MarkOperation(this));
		}
		this.errorCollector.failIfErrors();
	}

	private void applyToSourceUnits(SourceUnitOperation mark) throws CompilationFailedException {
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
		else if (operation instanceof SetupOperation)
			((SetupOperation) operation).call();
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

}
