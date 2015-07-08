package siani.tara.intellij.lang.semantic;


import com.intellij.psi.PsiElement;
import siani.tara.intellij.lang.psi.Node;
import siani.tara.intellij.lang.psi.Parameter;
import siani.tara.semantic.model.EmptyNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LanguageParameter extends LanguageElement implements siani.tara.semantic.model.Parameter {

	Parameter parameter;

	public LanguageParameter(Parameter parameter) {
		this.parameter = parameter;
	}

	private static List<Object> wrapValues(List<Object> values) {
		List<Object> objects = new ArrayList<>();
		if ("$empty".equals(values.get(0))) objects.add(new EmptyNode());
		else for (Object value : values)
			if (value instanceof Node) objects.add(new LanguageNode((Node) value));
			else objects.add(value);
		return objects;
	}

	@Override
	public String inferredType() {
		return null;
	}

	@Override
	public void setInferredType(String type) {
		parameter.setInferredType(type);
	}

	@Override
	public List<String> getAnnotations() {
		return Collections.emptyList();
	}

	@Override
	public void setAnnotations(List<String> annotations) {

	}

	@Override
	public boolean isMultiple() {
		return false;
	}

	@Override
	public void setMultiple(boolean multiple) {
		//TODO?
	}

	@Override
	public int getPosition() {
		return parameter.getIndexInParent();
	}

	@Override
	public String getName() {
		return parameter.getName();
	}

	@Override
	public void setName(String name) {
		parameter.setInferredName(name);
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
		return parameter.getMetric() != null ? parameter.getMetric().getIdentifier().getText() : "";
	}

	@Override
	public boolean isVariableInit() {
		return false;
	}

	@Override
	public void addAllowedParameters(List<String> values) {

	}


	@Override
	public PsiElement element() {
		return parameter;
	}
}
