package tara.io;

import java.util.ArrayList;
import java.util.List;

public class Case {
	public String name;
	public List<String> types = new ArrayList<>();
	public List<Variable> variables = new ArrayList<>();
	public List<Case> cases = new ArrayList<>();

	public Case() {
	}

	public Case(String name, List<String> types, List<Variable> variables, List<Case> cases) {
		this.name = name;
		this.types = types;
		this.variables = variables;
		this.cases = cases;
	}
}
