package tara.io;

import java.util.ArrayList;
import java.util.List;

public class Instance {
	public String name;
	public List<String> types = new ArrayList<>();
	public List<Variable> variables = new ArrayList<>();
	public List<Instance> cases = new ArrayList<>();

	public Instance() {
	}

	public Instance(String name, List<String> types, List<Variable> variables, List<Instance> cases) {
		this.name = name;
		this.types = types;
		this.variables = variables;
		this.cases = cases;
	}
}
