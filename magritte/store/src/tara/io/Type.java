package tara.io;

import java.util.ArrayList;
import java.util.List;

public class Type {
	public boolean isAbstract;
	public String morph;
	public String name;
	public String[] types;
	public String[] allowsMultiple;
	public String[] allowsSingle;
	public String[] requiresMultiple;
	public String[] requiresSingle;
	public List<Prototype> prototypes;
	public List<Case> cases;

	public Type() {
	}

	public Type(String[] types, String[] allowsMultiple, String[] allowsSingle, String[] requiresMultiple, String[] requiresSingle, List<Prototype> prototypes, List<Case> cases) {
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
