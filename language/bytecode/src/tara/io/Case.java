package tara.io;

import java.util.List;

public class Case {
	public String name;
	public List<String> types;
	public List<Variable> variables;
	public List<Case> cases;

	public Case() {
	}

	public Case(String name, List<String> types, List<Variable> variables, List<Case> cases) {
		this.name = name;
		this.types = types;
		this.variables = variables;
		this.cases = cases;
	}
}
