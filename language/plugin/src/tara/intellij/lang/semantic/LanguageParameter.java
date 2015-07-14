package tara.intellij.lang.semantic;


import com.intellij.psi.PsiElement;
import tara.intellij.lang.psi.Node;
import tara.intellij.lang.psi.Parameter;
import tara.language.model.EmptyNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LanguageParameter extends LanguageElement implements tara.language.model.Parameter {

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
	public void inferredType(String type) {
		parameter.setInferredType(type);
	}

	@Override
	public List<String> annotations() {
		return Collections.emptyList();
	}

	@Override
	public void annotations(List<String> annotations) {

	}

	@Override
	public boolean isMultiple() {
		return false;
	}

	@Override
	public void multiple(boolean multiple) {
		//TODO?
	}

	@Override
	public int position() {
		return parameter.getIndexInParent();
	}

	@Override
	public String name() {
		return parameter.getName();
	}

	@Override
	public void name(String name) {
		parameter.setInferredName(name);
	}

	@Override
	public List<Object> values() {
		return Collections.unmodifiableList(wrapValues(parameter.getValues()));
	}

	@Override
	public String contract() {
		return parameter.getContract();
	}

	@Override
	public void contract(String contract) {
		parameter.setContract(contract);
	}

	@Override
	public String metric() {
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
