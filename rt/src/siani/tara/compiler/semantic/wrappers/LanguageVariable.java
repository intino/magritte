package siani.tara.compiler.semantic.wrappers;

import siani.tara.compiler.model.Annotation;
import siani.tara.compiler.model.Variable;

import java.util.ArrayList;
import java.util.List;

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
		List<String> annotations = new ArrayList<>();
		for (Annotation annotation : variable.getAnnotations()) annotations.add(annotation.getName());
		return annotations.toArray(new String[annotations.size()]);
	}

	@Override
	public Object[] defaultValue() {
		return variable.getDefaultValues().toArray(new Object[variable.getDefaultValues().size()]);
	}
}
