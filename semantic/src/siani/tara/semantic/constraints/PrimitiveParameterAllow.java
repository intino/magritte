package siani.tara.semantic.constraints;

import siani.tara.semantic.Allow;
import siani.tara.semantic.Rejectable;
import siani.tara.semantic.SemanticException;
import siani.tara.semantic.model.Element;

import java.util.ArrayList;
import java.util.List;

import static siani.tara.semantic.constraints.PrimitiveTypeCompatibility.inferType;

public class PrimitiveParameterAllow extends ParameterAllow implements Allow.Parameter {

	private final String name;
	private final String type;
	private final boolean multiple;
	private final int position;
	private final String contract;
	private final String[] flags;


	public PrimitiveParameterAllow(String name, String type, boolean multiple, int position, String contract, String[] flags) {
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
	public String[] allowedValues() {
		return new String[0];
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
	public String[] flags() {
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
		Object[] values = rejectable.getParameter().getValues();
		if (values.length == 0) return true;
		String inferredType = inferType(values[0]);
		return !inferredType.isEmpty() && PrimitiveTypeCompatibility.checkCompatiblePrimitives(type(), inferredType);
	}


}
