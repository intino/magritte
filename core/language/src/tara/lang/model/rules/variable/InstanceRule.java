package tara.lang.model.rules.variable;

public class InstanceRule implements VariableRule<String> {

	@Override
	public boolean accept(String value) {
		return DateLoader.load(value) != null;
	}
}
