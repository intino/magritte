package siani.tara.semantic.constraints;

import siani.tara.semantic.Allow;
import siani.tara.semantic.Rejectable;
import siani.tara.semantic.model.Element;
import siani.tara.semantic.model.EmptyNode;
import siani.tara.semantic.model.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReferenceParameterAllow extends ParameterAllow implements Allow.Parameter {

	private static final String WORD = "word";
	private static final String REFERENCE = "reference";
	private static final String WORD_TYPE = ":word";
	private final String name;
	private final boolean multiple;
	private final List<String> values;
	private final int position;
	private String nativeName;
	private final List<String> flags;


	public ReferenceParameterAllow(String name, List<String> values, boolean multiple, int position, String nativeName, List<String> flags) {
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
	public List<String> allowedValues() {
		return Collections.unmodifiableList(values);
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
	public List<String> flags() {
		return Collections.unmodifiableList(flags);
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

	private boolean checkAsReferenceOrWord(List<Object> values) {
		if (type().equals(WORD)) return checkWords(values);
		else return checkReferences(values);
	}

	private boolean checkWords(List<Object> rejectableValues) {
		for (Object value : rejectableValues)
			if (value != null && !values.contains(value.toString().replace(REFERENCE + ":", ""))) return false;
		return true;
	}

	private boolean checkReferences(List<Object> values) {
		if (values.get(0) instanceof EmptyNode) return values.size() == 1;
		for (Object value : values) {
			if (!(value instanceof Node)) return false;
			if (areCompatibleReference((Node) value)) return true;
		}
		return false;
	}

	private boolean areCompatibleReference(Node node) {
		for (String type : node.types())
			if (values.contains(type)) return true;
		return false;
	}
}