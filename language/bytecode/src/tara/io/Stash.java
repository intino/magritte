package tara.io;

import java.util.ArrayList;
import java.util.List;

public class Stash {
	public String language;
	public List<String> uses = new ArrayList<>();
	public List<Type> types = new ArrayList<>();
	public List<Case> cases = new ArrayList<>();
	public List<Prototype> prototypes = new ArrayList<>();

	public boolean add(Type type) {
		return types.add(type);
	}

	public boolean addAll(List<Type> type) {
		return types.addAll(type);
	}

	public boolean add(Case aCase) {
		return cases.add(aCase);
	}

	public boolean add(Prototype prototype) {
		return prototypes.add(prototype);
	}
}