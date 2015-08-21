package tara.io;

import java.util.List;

public class Prototype {
	public String name;
	public String morph;
	public List<String> types;
	public List<Variable> variables;
	public List<Prototype> prototypes;

	public Prototype() {
	}


	public Prototype(String name, String morph, List<String> types, List<Variable> variables, List<Prototype> prototypes) {
		this.name = name;
		this.morph = morph;
		this.types = types;
		this.variables = variables;
		this.prototypes = prototypes;
	}
}
