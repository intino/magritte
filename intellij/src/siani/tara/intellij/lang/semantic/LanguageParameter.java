package siani.tara.intellij.lang.semantic;


import com.intellij.psi.PsiElement;
import siani.tara.intellij.lang.psi.Node;
import siani.tara.intellij.lang.psi.Parameter;
import siani.tara.semantic.model.EmptyNode;

import java.util.ArrayList;
import java.util.List;

public class LanguageParameter extends LanguageElement implements siani.tara.semantic.model.Parameter {

	Parameter parameter;

	public LanguageParameter(Parameter parameter) {
		this.parameter = parameter;
	}

	@Override
	public String inferredType() {
		return null;
	}

	@Override
	public void setInferredType(String s) {

	}

	@Override
	public String[] getAnnotations() {
		return new String[0];
	}

	@Override
	public void setAnnotations(String[] strings) {
		//TODO?
	}

	@Override
	public void setMultiple(boolean multiple) {
		//TODO?
	}

	@Override
	public boolean isMultiple() {
		return false;
	}

	@Override
	public int getPosition() {
		return parameter.getIndexInParent();
	}

	@Override
	public String getName() {
		return parameter.getExplicitName();
	}

	@Override
	public void setName(String name) {
	}

	@Override
	public Object[] getValues() {
		return wrapValues(parameter.getValues());
	}

	@Override
	public String getContract() {
		return parameter.getContract();
	}

	@Override
	public String getMetric() {
		return parameter.getMetric() != null ? parameter.getMetric().getIdentifier().getText() : "";
	}

	@Override
	public void setContract(String contract) {
		parameter.setContract(contract);
	}

	@Override
	public boolean isVariableInit() {
		return false;
	}

	@Override
	public void addAllowedParameters(String[] values) {

	}

	private static Object[] wrapValues(Object[] values) {
		List<Object> objects = new ArrayList<>();
		if ("$empty".equals(values[0])) objects.add(new EmptyNode());
		else for (Object value : values)
			if (value instanceof Node) objects.add(new LanguageNode((Node) value));
			else objects.add(value);
		return objects.toArray();
	}

	@Override
	public PsiElement element() {
		return parameter;
	}
}
