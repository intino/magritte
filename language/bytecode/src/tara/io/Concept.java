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
	public List<Content> contentRules = new ArrayList<>();
	public List<Prototype> prototypes = new ArrayList<>();
	public List<Variable> variables = new ArrayList<>();
	public List<Instance> instances = new ArrayList<>();

	@Override
	public String toString() {
		return "Concept{" + name + '}';
	}

	public static class Content {
		public String type;
		public int min;
		public int max;

		public Content(String type, int min, int max) {
			this.type = type;
			this.min = min;
			this.max = max;
		}
	}
}
