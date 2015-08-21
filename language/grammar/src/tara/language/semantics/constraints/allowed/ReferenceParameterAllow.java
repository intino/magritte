package tara.language.semantics.constraints.allowed;

import tara.language.semantics.Allow;
import tara.language.semantics.Rejectable;
import tara.language.model.Element;
import tara.language.model.EmptyNode;
import tara.language.model.Node;

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
	private final List<String> flags;
	private String nativeName;

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
		if (checkAsReferenceOrWord(parameter.getParameter().values())) {
			parameter.getParameter().name(name());
			parameter.getParameter().inferredType(type());
			parameter.getParameter().flags(flags);
			parameter.getParameter().multiple(multiple());
			parameter.getParameter().addAllowedParameters(allowedValues());
			toRemove.add(parameter);
		} else parameter.invalidValue(values);
	}

	private boolean checkAsReferenceOrWord(List<Object> values) {
		if (type().equals(WORD) && checkCardinality(values.size())) return checkWords(values);
		else return checkReferences(values) && checkCardinality(values.size());
	}

	private boolean checkWords(List<Object> rejectableValues) {
		for (Object value : rejectableValues)
			if (value != null && !values.contains(value.toString().replace(REFERENCE + ":", ""))) return false;
		return true;
	}

	private boolean checkReferences(List<Object> values) {
		if (values.isEmpty()) return false;
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

	private boolean checkCardinality(int size) {
		return size <= 1 || multiple();
	}
}
