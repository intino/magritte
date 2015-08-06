package tara.language.model;

import java.util.ArrayList;
import java.util.List;

import static tara.language.model.Tag.*;


public class Flags {

	private Flags() {
	}

	public static String[] all() {
		List<String> list = new ArrayList();
		for (Tag tags : values()) list.add(tags.name());
		return list.toArray(new String[list.size()]);
	}

	public static Tag[] subAnnotations() {
		return new Tag[]{ABSTRACT, TERMINAL, ENCLOSED, FINAL, PROTOTYPE, FEATURE, MAIN};
	}

	public static Tag[] hasAnnotations() {
		return new Tag[]{SINGLE, REQUIRED, ENCLOSED, FINAL};
	}

	public static Tag[] componentAnnotations() {
		return new Tag[]{ABSTRACT, TERMINAL, PROTOTYPE, REQUIRED, SINGLE, FACET, FINAL, FEATURE, ENCLOSED, FINAL, MAIN};
	}

	public static Tag[] variableAnnotations() {
		return new Tag[]{TERMINAL, FINAL, PRIVATE};
	}

	public static Tag[] primeAnnotations() {
		return new Tag[]{ABSTRACT, SINGLE, TERMINAL, REQUIRED, FACET, FINAL, FEATURE, PROTOTYPE, MAIN};
	}
}
