package tara.semantic.constraints;

import tara.semantic.Allow;
import tara.semantic.Rejectable;
import tara.semantic.SemanticException;
import tara.semantic.model.Element;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrimitiveParameterAllow extends ParameterAllow implements Allow.Parameter {

	private final String name;
	private final String type;
	private final boolean multiple;
	private final int position;
	private final String contract;
	private final List<String> flags;


	public PrimitiveParameterAllow(String name, String type, boolean multiple, int position, String contract, List<String> flags) {
		this.name = name;
		this.type = type;
		this.multiple = multiple;
		this.position = position;
		this.contract = contract;
		this.flags = flags;
	}

	@Override
	public void check(Element element, List<? extends Rejectable> rejectables) throws SemanticException {
		List<Rejectable> toRemove = new ArrayList<>();
		checkParameter(rejectables, toRemove);
		rejectables.removeAll(toRemove);
	}

	@Override
	public String name() {
		return name;
	}

	@Override
	public String type() {
		return type;
	}

	@Override
	public List<String> allowedValues() {
		return Collections.emptyList();
	}

	@Override
	public boolean multiple() {
		return multiple;
	}

	@Override
	public int position() {
		return position;
	}

	@Override
	public String contract() {
		return contract;
	}

	@Override
	public List<String> flags() {
		return flags;
	}

	private void checkParameter(List<? extends Rejectable> rejectables, List<Rejectable> toRemove) {
		Rejectable.Parameter parameter = findParameter(rejectables, name, position);
		if (parameter == null) return;
		if (checkParameter(parameter)) {
			toRemove.add(parameter);
			parameter.getParameter().setName(name());
			parameter.getParameter().setInferredType(type());
			parameter.getParameter().setAnnotations(flags);
			parameter.getParameter().setMultiple(multiple());
			parameter.getParameter().setContract(contract());
		} else parameter.invalidType(type);
	}

	private boolean checkParameter(Rejectable.Parameter rejectable) {
		List<Object> values = rejectable.getParameter().getValues();
		if (values.isEmpty()) return true;
		String inferredType = PrimitiveTypeCompatibility.inferType(values.get(0));
		return !inferredType.isEmpty() && PrimitiveTypeCompatibility.checkCompatiblePrimitives(type(), inferredType) && checkCardinality(values.size());
	}

	private boolean checkCardinality(int size) {
		return size <= 1 || multiple();
	}


}
