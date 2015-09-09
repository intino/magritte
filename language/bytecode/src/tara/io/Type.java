package tara.io;

import java.util.ArrayList;
import java.util.List;

public class Type {
	public boolean isAbstract;
    public boolean isTerminal;
    public boolean isMain;
    public String className;
    public String name;
    public String parent;
    public List<String> types = new ArrayList<>();
    public List<String> allowsMultiple = new ArrayList<>();
    public List<String> allowsSingle = new ArrayList<>();
    public List<String> requiresMultiple = new ArrayList<>();
    public List<String> requiresSingle = new ArrayList<>();
    public List<Prototype> prototypes = new ArrayList<>();
    public List<Variable> variables = new ArrayList<>();
    public List<Case> cases = new ArrayList<>();

    public Type() {
	}

	public Type(List<String> types, List<String> allowsMultiple, List<String> allowsSingle, List<String> requiresMultiple, List<String> requiresSingle, List<Prototype> prototypes, List<Case> cases) {
		this.types = types;
		this.allowsMultiple = allowsMultiple;
		this.allowsSingle = allowsSingle;
		this.requiresMultiple = requiresMultiple;
		this.requiresSingle = requiresSingle;
		this.prototypes = prototypes;
		this.cases = cases;
	}

	public boolean add(Prototype prototype) {
		return prototypes.add(prototype);
	}

	public boolean add(Variable variable) {
		return variables.add(variable);
	}

	public boolean addAll(List<Variable> variable) {
		return variables.addAll(variable);
	}

	public boolean add(Case aCase) {
		return cases.add(aCase);
	}
}
