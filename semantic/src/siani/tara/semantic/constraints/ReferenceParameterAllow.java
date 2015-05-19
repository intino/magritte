package siani.tara.semantic.constraints;

import siani.tara.semantic.Allow;
import siani.tara.semantic.Rejectable;
import siani.tara.semantic.model.Element;
import siani.tara.semantic.model.EmptyNode;
import siani.tara.semantic.model.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReferenceParameterAllow extends ParameterAllow implements Allow.Parameter {

	private static final String WORD = "word";
	private static final String REFERENCE = "reference";
	private static final String WORD_TYPE = ":word";
	private final String name;
	private final boolean multiple;
	private final String[] values;
	private final int position;
	private String nativeName;
	private final String[] flags;


	public ReferenceParameterAllow(String name, String[] values, boolean multiple, int position, String nativeName, String[] flags) {
		this.name = name;
		this.multiple = multiple;
		this.values = values;
		this.position = position;
		this.nativeName = nativeName;
		this.flags = flags;
	}

	@Override
	public void check(Element element, List<? extends Rejectable> rejectables) {
		List<Rejectable> toRemove = new ArrayList<>();
		checkParameter(rejectables, toRemove);
		rejectables.removeAll(toRemove);
	}

	@Override
	public String name() {
		return name.endsWith(WORD_TYPE) ? name.replace(WORD_TYPE, "") : name;
	}

	@Override
	public String type() {
		return name.endsWith(WORD_TYPE) ? WORD : REFERENCE;
	}

	@Override
	public boolean multiple() {
		return multiple;
	}

	@Override
	public String[] allowedValues() {
		return values;
	}

	@Override
	public int position() {
		return position;
	}

	@Override
	public String contract() {
		return nativeName;
	}

	@Override
	public String[] flags() {
		return flags;
	}

	private void checkParameter(List<? extends Rejectable> rejectables, List<Rejectable> toRemove) {
		Rejectable.Parameter parameter = findParameter(rejectables, name(), position);
		if (parameter == null) return;
		if (checkAsReferenceOrWord(parameter.getParameter().getValues())) {
			parameter.getParameter().setName(name());
			parameter.getParameter().setInferredType(type());
			parameter.getParameter().setAnnotations(flags);
			parameter.getParameter().setMultiple(multiple());
			parameter.getParameter().addAllowedParameters(allowedValues());
			toRemove.add(parameter);
		} else parameter.invalidValue(values);
	}

	private boolean checkAsReferenceOrWord(Object[] values) {
		if (type().equals(WORD)) return checkWords(values);
		else return checkReferences(values);
	}

	private boolean checkWords(Object[] rejectableValues) {
		List<String> allowedValues = Arrays.asList(values);
		for (Object value : rejectableValues)
			if (value != null && !allowedValues.contains(value.toString().replace(REFERENCE + ":", ""))) return false;
		return true;
	}

	private boolean checkReferences(Object[] values) {
		if (values[0] instanceof EmptyNode) return values.length == 1;
		for (Object value : values) {
			if (!(value instanceof Node)) return false;
			if (!areCompatibleReference((Node) value)) return false;
		}
		return true;
	}

	private boolean areCompatibleReference(Node node) {
		List<String> allowed = Arrays.asList(values);
		for (String type : node.types())
			if (allowed.contains(type)) return true;
		return false;
	}
}
