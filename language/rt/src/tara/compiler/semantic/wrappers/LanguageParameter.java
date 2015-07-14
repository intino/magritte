package tara.compiler.semantic.wrappers;


import tara.compiler.model.Element;
import tara.compiler.model.Node;
import tara.compiler.model.Parameter;
import tara.compiler.model.impl.NodeImpl;
import tara.semantic.model.EmptyNode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class LanguageParameter extends LanguageElement implements tara.semantic.model.Parameter {

	Parameter parameter;

	public LanguageParameter(Parameter parameter) {
		this.parameter = parameter;
	}

	private static List<Object> wrapValues(Collection<Object> values) {
		List<Object> objects = new ArrayList<>();
		for (Object value : values)
			if (value instanceof Node) objects.add(new LanguageNode((NodeImpl) value));
			else if (values.iterator().next() instanceof tara.compiler.model.impl.EmptyNode)
				objects.add(new EmptyNode());
			else objects.add(value);
		return objects;
	}

	@Override
	public String inferredType() {
		return parameter.inferredType();
	}

	@Override
	public void inferredType(String inferredType) {
		parameter.inferredType(inferredType);
	}

	@Override
	public List<String> annotations() {
		return parameter.annotations();
	}

	@Override
	public void annotations(List<String> annotations) {
		parameter.annotations(annotations);
	}

	@Override
	public boolean isMultiple() {
		return parameter.isMultiple();
	}

	@Override
	public void multiple(boolean multiple) {
		parameter.multiple(multiple);
	}

	@Override
	public int position() {
		return parameter.position();
	}

	@Override
	public String name() {
		return parameter.name();
	}

	@Override
	public void name(String name) {
		parameter.name(name);
	}

	@Override
	public List<Object> values() {
		return Collections.unmodifiableList(wrapValues(parameter.values()));
	}

	@Override
	public String contract() {
		return parameter.contract();
	}

	@Override
	public void contract(String contract) {
		parameter.contract(contract);
	}

	@Override
	public String metric() {
		return parameter.metric();
	}

	@Override
	public boolean isVariableInit() {
		return false;
	}

	@Override
	public void addAllowedParameters(List<String> values) {
		parameter.addAllowedValues(values);
	}

	@Override
	public Element element() {
		return parameter;
	}
}
