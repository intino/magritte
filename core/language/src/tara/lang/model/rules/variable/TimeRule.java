package tara.lang.model.rules.variable;

public class TimeRule implements VariableRule<String> {

	private String regex = "";

	@Override
	public boolean accept(String value) {
		return false;
	}
}
