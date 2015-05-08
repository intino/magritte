package siani.tara.compiler.semantic.wrappers;

import siani.tara.compiler.model.Element;
import siani.tara.compiler.model.Tag;
import siani.tara.compiler.model.Variable;

import java.util.ArrayList;
import java.util.List;

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
		List<String> flags = new ArrayList<>();
		for (Tag tag : variable.getFlags()) flags.add(tag.getName());
		return flags.toArray(new String[flags.size()]);
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
