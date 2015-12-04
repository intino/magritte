package tara.io;

import java.util.ArrayList;
import java.util.List;

public class Concept {
	public boolean isAbstract;
	public boolean isMetaConcept;
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
	public List<Instance> instances = new ArrayList<>();

	@Override
	public String toString() {
		return "Concept{" + name + '}';
	}
}
