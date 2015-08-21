package tara.io;

import java.util.ArrayList;
import java.util.List;

public class Type {
	public boolean isAbstract;
	public String morph;
	public String name;
	public List<String> types;
	public List<String> allowsMultiple;
	public List<String> allowsSingle;
	public List<String> requiresMultiple;
	public List<String> requiresSingle;
	public List<Prototype> prototypes;
	public List<Case> cases;

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
		if (prototypes == null) prototypes = new ArrayList<>();
		return prototypes.add(prototype);
	}

	public boolean add(Case aCase) {
		if (cases == null) cases = new ArrayList<>();
		return cases.add(aCase);
	}
}
