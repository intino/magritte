package siani.tara.intellij.lang.semantic;


import siani.tara.intellij.lang.psi.TaraAnnotations;
import siani.tara.intellij.lang.psi.TaraValue;
import siani.tara.intellij.lang.psi.Variable;

public class LanguageVariable implements siani.tara.semantic.model.Variable {
	private final Variable variable;

	public LanguageVariable(Variable variable) {
		this.variable = variable;
	}

	@Override
	public String name() {
		return variable.getName();
	}

	@Override
	public String type() {
		return variable.getType();
	}

	@Override
	public String[] annotations() {
		TaraAnnotations annotations = variable.getAnnotations();
		return annotations != null ? annotations.asStringArray() : new String[0];
	}

	@Override
	public Object[] defaultValue() {
		TaraValue value = variable.getValue();
		return value != null ? value.getValues() : new Object[0];
	}
}
