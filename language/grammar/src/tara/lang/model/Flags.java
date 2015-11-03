package tara.lang.model;

import java.util.ArrayList;
import java.util.List;


public class Flags {

	private Flags() {
	}

	public static String[] all() {
		List<String> list = new ArrayList();
		for (Tag tags : Tag.values()) list.add(tags.name());
		return list.toArray(new String[list.size()]);
	}

	public static Tag[] subAnnotations() {
		return new Tag[]{Tag.ABSTRACT, Tag.TERMINAL, Tag.ENCLOSED, Tag.FINAL, Tag.PROTOTYPE, Tag.FEATURE, Tag.MAIN, Tag.NAMED};
	}

	public static Tag[] hasAnnotations() {
		return new Tag[]{Tag.ENCLOSED, Tag.FINAL};
	}

	public static Tag[] componentAnnotations() {
		return new Tag[]{Tag.ABSTRACT, Tag.TERMINAL, Tag.PROTOTYPE, Tag.FACET, Tag.FINAL, Tag.FEATURE, Tag.ENCLOSED, Tag.FINAL, Tag.MAIN, Tag.NAMED};
	}

	public static Tag[] variableAnnotations() {
		return new Tag[]{Tag.TERMINAL, Tag.FINAL, Tag.PRIVATE, Tag.DEFINITION};
	}

	public static Tag[] primeAnnotations() {
		return new Tag[]{Tag.ABSTRACT, Tag.TERMINAL, Tag.FACET, Tag.FINAL, Tag.FEATURE, Tag.PROTOTYPE, Tag.MAIN, Tag.NAMED};
	}
}
