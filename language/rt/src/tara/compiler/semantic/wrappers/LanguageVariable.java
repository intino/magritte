package tara.compiler.semantic.wrappers;

import tara.compiler.model.Element;
import tara.compiler.model.Variable;
import tara.compiler.model.impl.VariableReference;
import tara.semantic.model.Tag;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.unmodifiableList;

public class LanguageVariable extends LanguageElement implements tara.semantic.model.Variable {
	private final Variable variable;

	public LanguageVariable(Variable variable) {
		this.variable = variable;
	}

	@Override
	public String name() {
		return variable.name();
	}

	@Override
	public String type() {
		return variable.type();
	}

	@Override
	public String contract() {
		return variable.contract();
	}

	@Override
	public List<String> flags() {
		return unmodifiableList(variable.flags().stream().map(Tag::name).collect(Collectors.toList()));
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
		return variable.size();
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
