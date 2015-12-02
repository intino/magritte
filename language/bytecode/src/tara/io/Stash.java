package tara.io;

import java.util.ArrayList;
import java.util.List;

public class Stash {
	public String language;
	public List<String> uses = new ArrayList<>();
	public List<Concept> concepts = new ArrayList<>();
	public List<Instance> cases = new ArrayList<>();
	public List<Prototype> prototypes = new ArrayList<>();

	public boolean add(Concept concept) {
		return concepts.add(concept);
	}

	public boolean addAll(List<Concept> concept) {
		return concepts.addAll(concept);
	}

	public boolean add(Instance aCase) {
		return cases.add(aCase);
	}

	public boolean add(Prototype prototype) {
		return prototypes.add(prototype);
	}
}