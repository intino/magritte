package siani.tara.intellij.lang.semantic;


import com.intellij.psi.PsiElement;
import siani.tara.intellij.lang.psi.Node;
import siani.tara.intellij.lang.psi.Parameter;
import siani.tara.model.EmptyNode;

import java.util.ArrayList;
import java.util.List;

public class LanguageParameter extends LanguageElement implements siani.tara.model.Parameter {


	Parameter parameter;

	public LanguageParameter(Parameter parameter) {
		this.parameter = parameter;
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
	public Object[] getValues() {
		return wrapValues(parameter.getValues());
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
	public PsiElement element() {
		return parameter;
	}
}
