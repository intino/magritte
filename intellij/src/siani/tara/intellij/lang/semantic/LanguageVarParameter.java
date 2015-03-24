package siani.tara.intellij.lang.semantic;

import com.intellij.psi.PsiElement;
import siani.tara.intellij.lang.psi.Node;
import siani.tara.intellij.lang.psi.VarInit;
import siani.tara.semantic.model.EmptyNode;
import siani.tara.semantic.model.Parameter;

import java.util.ArrayList;
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
	public String[] getAnnotations() {
		return new String[0];
	}

	@Override
	public void setAnnotations(String[] strings) {

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
	public Object[] getValues() {
		return wrapValues(varInit.getValues());
	}

	private static Object[] wrapValues(Object[] values) {
		List<Object> objects = new ArrayList<>();
		for (Object value : values)
			if (value instanceof Node) objects.add(new LanguageNode((Node) value));
			else if (values[0].equals("$empty")) objects.add(new EmptyNode());
			else objects.add(value);
		return objects.toArray();
	}

	@Override
	public String getExtension() {
		return varInit.getMeasureValue();
	}

	@Override
	public PsiElement element() {
		return varInit;
	}
}
