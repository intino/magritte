package tara.io;

import java.util.List;

public class Case {
	public String name;
	public String[] types;
	public Variable[] variables;
	public List<Case> cases;

	public Case() {
	}

	public Case(String name, String[] types, Variable[] variables, List<Case> cases) {
		this.name = name;
		this.types = types;
		this.variables = variables;
		this.cases = cases;
	}
}
