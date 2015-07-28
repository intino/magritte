package tara.io;

public class Prototype {
	public String name;
	public String morph;
	public String[] types;
	public Variable[] variables;
	public Prototype[] prototypes;

	public Prototype() {
	}


	public Prototype(String name, String morph, String[] types, Variable[] variables, Prototype[] prototypes) {
		this.name = name;
		this.morph = morph;
		this.types = types;
		this.variables = variables;
		this.prototypes = prototypes;
	}
}
