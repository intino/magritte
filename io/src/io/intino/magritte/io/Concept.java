package io.intino.magritte.io;

import java.util.ArrayList;
import java.util.List;

public class Concept {
	public boolean isAbstract;
	public boolean isMetaConcept;
	public boolean isMain;
	public boolean isAspect;
	public String className;
	public String name;
	public String parent;
	public List<String> types = new ArrayList<>();
	public List<Content> contentRules = new ArrayList<>();
	public List<Variable> variables = new ArrayList<>();
	public List<Variable> parameters = new ArrayList<>();
	public List<Node> nodes = new ArrayList<>();

	@Override
	public String toString() {
		return "Concept{" + name + '}';
	}

	public static class Content {
		public String type;
		public int min;
		public int max;

		public Content() {
		}

		public Content(String type, int min, int max) {
			this.type = type;
			this.min = min;
			this.max = max;
		}
	}
}
