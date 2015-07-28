package tara.compiler.core;

import tara.compiler.core.errorcollection.CompilationFailedException;
import tara.compiler.core.operation.Operation;
import tara.compiler.core.operation.model.EnrichModelOperation;
import tara.compiler.core.operation.model.ModelOperation;
import tara.compiler.core.operation.model.ModelToStashOperation;
import tara.compiler.core.operation.module.MergeModelOperation;
import tara.compiler.core.operation.module.ModuleUnitOperation;
import tara.compiler.core.operation.sourceunit.BuildModelOperation;
import tara.compiler.core.operation.sourceunit.ParseOperation;
import tara.compiler.core.operation.sourceunit.SourceUnitOperation;
import tara.compiler.model.Model;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CompilationUnit extends ProcessingUnit {

	private Map<String, SourceUnit> sourceUnits;
	private Model model;
	private List<Operation> operations = new LinkedList<>();

	public CompilationUnit(CompilerConfiguration configuration) {
		super(configuration, null);
		this.sourceUnits = new HashMap<>();
		addPhaseOperation(new ParseOperation(this.errorCollector));
		addPhaseOperation(new BuildModelOperation(this.errorCollector));
		addPhaseOperation(new MergeModelOperation(this));
		addPhaseOperation(new EnrichModelOperation(this));
		addPhaseOperation(new ModelToStashOperation(this));
	}

	public void addPhaseOperation(Operation operation) {
		this.operations.add(operation);
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
		operations.forEach(this::doPhaseOperation);
	}

	private void doPhaseOperation(Operation operation) {
		if (operation instanceof SourceUnitOperation)
			applyToSourceUnits((SourceUnitOperation) operation);
		else if (operation instanceof ModuleUnitOperation)
			((ModuleUnitOperation) operation).call(sourceUnits.values());
		else if (operation instanceof ModelOperation)
			((ModelOperation) operation).call(model);
		completeOperation();
	}

	public void applyToSourceUnits(SourceUnitOperation unitOperation) throws CompilationFailedException {
		SourceUnit source;
		for (String name : this.sourceUnits.keySet()) {
			source = this.sourceUnits.get(name);
			unitOperation.call(source);
		}
		getErrorCollector().failIfErrors();
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}
}
