package siani.tara.intellij.lang.semantic;

import com.intellij.psi.PsiElement;
import siani.tara.intellij.lang.psi.Node;
import siani.tara.intellij.lang.psi.VarInit;
import siani.tara.semantic.model.EmptyNode;
import siani.tara.semantic.model.Parameter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LanguageVarParameter extends LanguageElement implements Parameter {
	private final VarInit varInit;

	public LanguageVarParameter(VarInit varInit) {
		this.varInit = varInit;
	}

	@Override
	public String inferredType() {
		return null;
	}

	@Override
	public void setInferredType(String s) {

	}

	@Override
	public List<String> getAnnotations() {
		return Collections.emptyList();
	}

	@Override
	public void setAnnotations(List<String> annotations) {

	}


	@Override
	public void setMultiple(boolean multiple) {

	}

	@Override
	public boolean isMultiple() {
		return false;
	}

	@Override
	public int getPosition() {
		return -1;
	}

	@Override
	public String getName() {
		return varInit.getName();
	}

	@Override
	public void setName(String name) {

	}

	@Override
	public List<Object> getValues() {
		return Collections.unmodifiableList(wrapValues(varInit.getValues()));
	}

	private static List<Object> wrapValues(Object[] values) {
		List<Object> objects = new ArrayList<>();
		for (Object value : values) {
			if (value == null) continue;
			if (value instanceof Node) objects.add(new LanguageNode((Node) value));
			else if (values[0].equals("$empty")) objects.add(new EmptyNode());
			else objects.add(value);
		}
		return objects;
	}

	@Override
	public String getContract() {
		return varInit.getContract();
	}

	@Override
	public String getMetric() {
		return varInit.getMetric();
	}

	@Override
	public void setContract(String contract) {

	}

	@Override
	public boolean isVariableInit() {
		return true;
	}

	@Override
	public void addAllowedParameters(List<String> values) {

	}

	@Override
	public PsiElement element() {
		return varInit;
	}
}
