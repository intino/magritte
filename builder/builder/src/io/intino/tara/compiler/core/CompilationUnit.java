package io.intino.tara.compiler.core;

import io.intino.tara.compiler.core.errorcollection.CompilationFailedException;
import io.intino.tara.compiler.core.operation.LayerGenerationOperation;
import io.intino.tara.compiler.core.operation.Operation;
import io.intino.tara.compiler.core.operation.StashGenerationOperation;
import io.intino.tara.compiler.core.operation.model.*;
import io.intino.tara.compiler.core.operation.module.ModuleUnitOperation;
import io.intino.tara.compiler.core.operation.module.UnifyModelOperation;
import io.intino.tara.compiler.core.operation.setup.SetupConfigurationOperation;
import io.intino.tara.compiler.core.operation.setup.SetupOperation;
import io.intino.tara.compiler.core.operation.sourceunit.MarkOperation;
import io.intino.tara.compiler.core.operation.sourceunit.ModelGenerationOperation;
import io.intino.tara.compiler.core.operation.sourceunit.ParseOperation;
import io.intino.tara.compiler.core.operation.sourceunit.SourceUnitOperation;
import io.intino.tara.compiler.model.Model;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public final class CompilationUnit extends ProcessingUnit {

	private Map<String, SourceUnit> sourceUnits;
	private Model model;
	private List<Operation>[] phaseOperations;
	private Map<String, List<String>> outputItems = new HashMap<>();

	public CompilationUnit(CompilerConfiguration configuration) {
		super(configuration, null);
		this.sourceUnits = new HashMap<>();
		this.phaseOperations = new LinkedList[Phases.ALL];
		for (int i = 0; i < this.phaseOperations.length; i++) this.phaseOperations[i] = new LinkedList();
		addPhaseOperations();
	}

	private void addPhaseOperations() {
		addPhaseOperation(new SetupConfigurationOperation(this), Phases.INITIALIZATION);
		addPhaseOperation(new ParseOperation(this), Phases.PARSING);
		addPhaseOperation(new ModelGenerationOperation(this), Phases.CONVERSION);
		addPhaseOperation(new UnifyModelOperation(this), Phases.CONVERSION);
		addPhaseOperation(new ModelDependencyResolutionOperation(this), Phases.DEPENDENCY_RESOLUTION);
		addPhaseOperation(new ModelResolutionOperation(), Phases.MODEL_DEPENDENCY_RESOLUTION);
		addPhaseOperation(new SemanticAnalysisOperation(this), Phases.SEMANTIC_ANALYSIS);
		addPhaseOperation(new MetricResolutionOperation(this), Phases.POST_ANALYSIS_RESOLUTION);
		addPhaseOperation(new TableProfilingOperation(this), Phases.POST_ANALYSIS_RESOLUTION);
		addPhaseOperation(new NativeTransformationOperation(this), Phases.POST_ANALYSIS_RESOLUTION);
		addPhaseOperation(new LayerGenerationOperation(this), Phases.CODE_GENERATION);
		addPhaseOperation(new StashGenerationOperation(this), Phases.STASH_GENERATION);
		if (!configuration.isTest()) addPhaseOperation(new GenerateLanguageOperation(this), Phases.LANGUAGE_GENERATION);
	}

	private void addPhaseOperation(Operation operation, int phase) {
		if ((phase < Phases.FIRST) || (phase > Phases.LAST))
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
		compile(Phases.ALL);
	}

	public static void cleanOut(CompilerConfiguration configuration) {
		final String generationPackage = (configuration.workingPackage() == null ? configuration.getModule() : configuration.workingPackage()).replace(".", File.separator);
		File out = new File(configuration.getOutDirectory(), generationPackage.toLowerCase());
//		if (!configuration.isStashGeneration() && out.exists()) FileSystemUtils.removeDir(out); TODO avoid removing pandora directory
	}

	private void compile(int throughPhase) throws CompilationFailedException {
		gotoPhase(1);
		while ((Math.min(throughPhase, Phases.LAST) >= this.phase) && (this.phase <= Phases.LAST)) {
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
