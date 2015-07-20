package tara.compiler.core;

import tara.compiler.core.errorcollection.CompilationFailedException;
import tara.compiler.core.operation.ModelToStashOperation;
import tara.compiler.core.operation.Operation;
import tara.compiler.core.operation.SrcToClassOperation;
import tara.compiler.core.operation.model.EnrichModelOperation;
import tara.compiler.core.operation.model.ModelOperation;
import tara.compiler.core.operation.module.MergeToModelOperation;
import tara.compiler.core.operation.module.ModuleUnitOperation;
import tara.compiler.core.operation.sourceunit.BuildModelOperation;
import tara.compiler.core.operation.sourceunit.MarkOperation;
import tara.compiler.core.operation.sourceunit.ParseOperation;
import tara.compiler.core.operation.sourceunit.SourceUnitOperation;
import tara.compiler.model.impl.Model;

import java.io.File;
import java.util.*;

public class CompilationUnit extends ProcessingUnit {

	protected ProgressCallback progressCallback;
	private Map<String, SourceUnit> sourceUnits;
	private Model model;
	private List<Operation>[] phaseOperations;

	public CompilationUnit(CompilerConfiguration configuration) {
		super(configuration, null);
		this.sourceUnits = new HashMap<>();
		this.phaseOperations = new LinkedList[Phases.ALL];
		for (int i = 0; i < this.phaseOperations.length; i++)
			this.phaseOperations[i] = new LinkedList();
		addPhaseOperation(new ParseOperation(this.errorCollector), Phases.PARSING);
		addPhaseOperation(new BuildModelOperation(this.errorCollector), Phases.CONVERSION);
		addPhaseOperation(new MergeToModelOperation(this), Phases.CONVERSION);
		addPhaseOperation(new EnrichModelOperation(this), Phases.CONVERSION);
		addPhaseOperation(new ModelToStashOperation(this), Phases.STASH_GENERATION);
	}

	public void addPhaseOperation(Operation operation, int phase) {
		if ((phase < Phases.FIRST) || (phase > Phases.LAST))
			throw new IllegalArgumentException("phase " + phase + " is unknown");
		this.phaseOperations[phase].add(operation);
	}

	public SourceUnit addSource(File source) {
		return addSource(new SourceUnit(source, this.configuration, this.errorCollector));
	}

	private SourceUnit addSource(SourceUnit source) {
		String name = source.getName();
		for (SourceUnit su : sourceUnits.values())
			if (name.equals(su.getName())) return su;
		this.sourceUnits.put(name, source);
		return source;
	}

	public void compile() throws CompilationFailedException {
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

	public Model getOutput() {
		return null;
	}

	public abstract static class ProgressCallback {
		public abstract void call(ProcessingUnit paramProcessingUnit, int paramInt) throws CompilationFailedException;
	}

}
