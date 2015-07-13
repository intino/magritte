package siani.tara.compiler.semantic.wrappers;

import siani.tara.compiler.model.Element;
import siani.tara.compiler.model.Variable;
import siani.tara.compiler.model.impl.VariableReference;
import siani.tara.semantic.model.Tag;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.unmodifiableList;

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
	public String contract() {
		return variable.getContract();
	}

	@Override
	public List<String> flags() {
		return unmodifiableList(variable.getFlags().stream().map(Tag::name).collect(Collectors.toList()));
	}

	@Override
	public void flags(String... flags) {
		variable.addFlags(flags);
	}

	@Override
	public boolean isReference() {
		return variable instanceof VariableReference;
	}

	@Override
	public boolean isMultiple() {
		return variable.isMultiple();
	}

	@Override
	public int getSize() {
		return variable.getSize();
	}

	@Override
	public boolean isOverriden() {
		return variable.isOverriden();
	}

	@Override
	public List<Object> defaultValue() {
		return unmodifiableList(variable.getDefaultValues());
	}

	@Override
	public Element element() {
		return variable;
	}
}
