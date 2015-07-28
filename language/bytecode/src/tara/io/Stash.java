package tara.io;

import java.util.ArrayList;
import java.util.List;

public class Stash {
	public String language;
	public List<String> uses;
	public List<Type> types;
	public List<Case> cases;
	public List<Prototype> prototypes;


	public boolean add(Type type) {
		if (types == null) types = new ArrayList<>();
		return types.add(type);
	}

	public boolean add(Case aCase) {
		if (cases == null) cases = new ArrayList<>();
		return cases.add(aCase);
	}

	public boolean add(Prototype prototype) {
		if (prototypes == null) prototypes = new ArrayList<>();
		return prototypes.add(prototype);
	}
}