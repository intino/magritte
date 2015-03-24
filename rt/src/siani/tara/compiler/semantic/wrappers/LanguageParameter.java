package siani.tara.compiler.semantic.wrappers;


import siani.tara.compiler.model.Element;
import siani.tara.compiler.model.Node;
import siani.tara.compiler.model.Parameter;
import siani.tara.compiler.model.impl.NodeImpl;
import siani.tara.semantic.model.EmptyNode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LanguageParameter extends LanguageElement implements siani.tara.semantic.model.Parameter {

	Parameter parameter;

	public LanguageParameter(Parameter parameter) {
		this.parameter = parameter;
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
	public String[] getAnnotations() {
		return parameter.getAnnotations();
	}

	@Override
	public void setAnnotations(String[] annotations) {
		parameter.setAnnotations(annotations);
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
	public Object[] getValues() {
		return wrapValues(parameter.getValues());
	}

	@Override
	public String getExtension() {
		return parameter.getExtension();
	}

	private static Object[] wrapValues(Collection<Object> values) {
		List<Object> objects = new ArrayList<>();
		for (Object value : values)
			if (value instanceof Node) objects.add(new LanguageNode((NodeImpl) value));
			else if (values.iterator().next() instanceof siani.tara.compiler.model.EmptyNode)
				objects.add(new EmptyNode());
			else objects.add(value);
		return objects.toArray();
	}

	@Override
	public Element element() {
		return (Element) parameter;
	}
}
