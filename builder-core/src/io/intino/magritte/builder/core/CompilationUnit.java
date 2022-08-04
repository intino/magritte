package io.intino.magritte.builder.core;

import io.intino.magritte.Language;
import io.intino.magritte.builder.core.errorcollection.CompilationFailedException;
import io.intino.magritte.builder.core.operation.Operation;
import io.intino.magritte.builder.core.operation.model.*;
import io.intino.magritte.builder.core.operation.setup.SetupConfigurationOperation;
import io.intino.magritte.builder.core.operation.setup.SetupOperation;
import io.intino.magritte.builder.core.operation.sourceunit.*;
import io.intino.magritte.builder.model.Model;
import io.intino.magritte.builder.utils.DifferentialCache;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public final class CompilationUnit extends ProcessingUnit {

	private final DifferentialCache differentialCache;
	private final Map<String, SourceUnit> sourceUnits;
	private final Map<Language, Model> models = new HashMap<>();
	private final List<Operation>[] phaseOperations;
	private final Map<String, List<String>> outputItems = new HashMap<>();


	public CompilationUnit(CompilerConfiguration configuration) {
		super(configuration, null);
		this.sourceUnits = new HashMap<>();
		this.phaseOperations = new LinkedList[Phases.ALL];
		differentialCache = new DifferentialCache(new File(configuration.intinoProjectDirectory(), "model" + File.separator + configuration.getModule()));
		IntStream.range(0, phaseOperations.length).forEach(i -> phaseOperations[i] = new LinkedList<>());
		addBasePhaseOperations();
	}

	public static void cleanOut(CompilerConfiguration configuration) {
		final String generationPackage = (configuration.workingPackage() == null ? configuration.getModule() : configuration.workingPackage()).replace(".", File.separator);
		File out = new File(configuration.getOutDirectory(), generationPackage.toLowerCase());
//		if (out.exists()) FileSystemUtils.removeDir(out); TODO
	}

	private void addBasePhaseOperations() {
		addPhaseOperation(new SetupConfigurationOperation(this), Phases.INITIALIZATION);
		addPhaseOperation(new ParseOperation(this), Phases.PARSING);
		addPhaseOperation(new ModelGenerationOperation(this), Phases.CONVERSION);
		addPhaseOperation(new UnifyModelOperation(this), Phases.CONVERSION);
		addPhaseOperation(new ModelDependencyResolutionOperation(this), Phases.DEPENDENCY_RESOLUTION);
		addPhaseOperation(new ModelResolutionOperation(), Phases.MODEL_DEPENDENCY_RESOLUTION);
		addPhaseOperation(new SemanticAnalysisOperation(this), Phases.SEMANTIC_ANALYSIS);
		addPhaseOperation(new MetricResolutionOperation(this), Phases.POST_ANALYSIS_RESOLUTION);
		addPhaseOperation(new NativeTransformationOperation(this), Phases.POST_ANALYSIS_RESOLUTION);
	}


	public void addPhaseOperation(Operation operation, int phase) {
		if (phase < Phases.FIRST || phase > Phases.LAST)
			throw new IllegalArgumentException("phase " + phase + " is unknown");
		if (!isExcludedPhase(phase)) this.phaseOperations[phase].add(operation);
	}

	private boolean isExcludedPhase(int phase) {
		return configuration.getExcludedPhases().contains(phase);
	}

	public void addOutputItems(Map<String, List<String>> paths) {
		outputItems.putAll(paths);
	}

	public void addSource(SourceUnit source) {
		String name = source.getName();
		for (SourceUnit su : sourceUnits.values())
			if (name.equals(su.getName())) return;
		this.sourceUnits.put(name, source);
	}

	public Map<String, SourceUnit> getSourceUnits() {
		return sourceUnits;
	}

	public void compile() throws CompilationFailedException {
		compile(Phases.ALL);
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
		else if (operation instanceof SourceUnitCollectionOperation)
			((SourceUnitCollectionOperation) operation).call(sourceUnits.values());
		else if (operation instanceof ModelCollectionOperation) {
			((ModelCollectionOperation) operation).call(models.values());
		} else if (operation instanceof ModelOperation)
			models.values().forEach(((ModelOperation) operation)::call);
		else if (operation instanceof SetupOperation)
			((SetupOperation) operation).call();
	}

	public Map<Language, Model> models() {
		return models;
	}

	public void addModel(Language language, Model model) {
		this.models.put(language, model);
	}

	public Map<String, List<String>> getOutputItems() {
		return outputItems;
	}

	public DifferentialCache compilationDifferentialCache() {
		return differentialCache;
	}
}
