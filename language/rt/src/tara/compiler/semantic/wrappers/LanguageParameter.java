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
			else if (values.iterator().next() instanceof tara.compiler.model.EmptyNode)
				objects.add(new EmptyNode());
			else objects.add(value);
		return objects;
	}

	@Override
	public String inferredType() {
		return parameter.getInferredType();
	}

	@Override
	public void setInferredType(String inferredType) {
		parameter.setInferredType(inferredType);
	}

	@Override
	public List<String> getAnnotations() {
		return parameter.getAnnotations();
	}

	@Override
	public void setAnnotations(List<String> annotations) {
		parameter.setAnnotations(annotations);
	}

	@Override
	public boolean isMultiple() {
		return parameter.isMultiple();
	}

	@Override
	public void setMultiple(boolean multiple) {
		parameter.setMultiple(multiple);
	}

	@Override
	public int getPosition() {
		return parameter.getPosition();
	}

	@Override
	public String getName() {
		return parameter.getName();
	}

	@Override
	public void setName(String name) {
		parameter.setName(name);
	}

	@Override
	public List<Object> getValues() {
		return Collections.unmodifiableList(wrapValues(parameter.getValues()));
	}

	@Override
	public String getContract() {
		return parameter.getContract();
	}

	@Override
	public void setContract(String contract) {
		parameter.setContract(contract);
	}

	@Override
	public String getMetric() {
		return parameter.getMetric();
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
