package siani.tara.compiler.semantic.wrappers;

import siani.tara.compiler.model.Element;
import siani.tara.compiler.model.Variable;
import siani.tara.compiler.model.impl.VariableReference;
import siani.tara.semantic.model.Tag;

import java.util.List;
import java.util.stream.Collectors;

public class LanguageVariable extends LanguageElement implements siani.tara.semantic.model.Variable {
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
	public String[] flags() {
		List<String> flags = variable.getFlags().stream().map(Tag::name).collect(Collectors.toList());
		return flags.toArray(new String[flags.size()]);
	}

	@Override
	public boolean isReference() {
		return variable instanceof VariableReference;
	}

	@Override
	public boolean isOverriden() {
		return variable.isOverriden();
	}

	@Override
	public Object[] defaultValue() {
		return variable.getDefaultValues().toArray(new Object[variable.getDefaultValues().size()]);
	}

	@Override
	public Element element() {
		return (Element) variable;
	}
}
