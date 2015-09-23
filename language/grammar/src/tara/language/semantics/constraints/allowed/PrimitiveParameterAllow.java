package tara.language.semantics.constraints.allowed;

import tara.language.model.Element;
import tara.language.model.Primitives;
import tara.language.semantics.Allow;
import tara.language.semantics.Rejectable;
import tara.language.semantics.SemanticException;
import tara.language.semantics.constraints.PrimitiveTypeCompatibility;

import java.util.ArrayList;
import java.util.Arrays;
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
			parameter.getParameter().name(name());
			parameter.getParameter().inferredType(type());
			parameter.getParameter().flags(flags);
			parameter.getParameter().multiple(multiple());
			parameter.getParameter().contract(contract());
		} else throwError(parameter);
	}

	private void throwError(Rejectable.Parameter parameter) {
		switch (error) {
			case TYPE:
				parameter.invalidType(type);
				break;
			case METRIC:
				parameter.invalidMetric(Arrays.asList(allowedMetrics()));
				break;
			case CARDINALITY:
				parameter.invalidCardinality();
				break;
		}
	}

	private boolean checkParameter(Rejectable.Parameter rejectable) {
		List<Object> values = rejectable.getParameter().values();
		if (values.isEmpty()) return true;
		String inferredType = PrimitiveTypeCompatibility.inferType(values.get(0));
		return !inferredType.isEmpty() && PrimitiveTypeCompatibility.checkCompatiblePrimitives(type(), inferredType) && checkCardinality(values.size()) && checkMetric(rejectable.getParameter());
	}

	private boolean checkMetric(tara.language.model.Parameter parameter) {
		if (!type().equals(Primitives.MEASURE)) return true;
		String[] split = allowedMetrics();
		if (split.length == 0) return false;
		for (String s : split) if (s.equals(parameter.metric())) return true;
		error = ERROR.METRIC;
		return false;
	}

	private String[] allowedMetrics() {
		if (!contract().contains("[")) return new String[0];
		String allowedMetrics = contract().substring(contract.indexOf("[") + 1, contract.lastIndexOf("]"));
		return allowedMetrics.split(", ");
	}

	private boolean checkCardinality(int size) {
		boolean check = size <= 1 || multiple();
		if (!check) error = ERROR.CARDINALITY;
		return check;
	}

	private static ERROR error = ERROR.TYPE;

	private enum ERROR {
		TYPE, CARDINALITY, METRIC
	}
}
