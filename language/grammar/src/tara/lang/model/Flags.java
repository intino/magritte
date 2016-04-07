package tara.lang.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static tara.lang.model.Tag.*;
import static tara.lang.model.Tag.Facet;

public class Flags {

	private Flags() {
	}

	public static String[] all() {
		List<String> list = new ArrayList();
		for (Tag tags : values()) list.add(tags.name());
		return list.toArray(new String[list.size()]);
	}

	public static List<Tag> forRoot() {
		return Arrays.asList(Abstract, Final, Feature, Prototype, Component, Facet, Volatile);
	}

	public static List<Tag> forReference() {
		return Arrays.asList(Enclosed, Final);
	}

	public static List<Tag> forComponent() {
		return Arrays.asList(Abstract, Prototype, Final, Feature, Enclosed, Final, Component, Volatile);
	}

	public static List<Tag> forVariable() {
		return Arrays.asList(Final, Private, Concept, Reactive, Terminal);
	}

	public static List<Tag> internalTags() {
		return Arrays.asList(Instance, Facet, FacetInstance, Terminal);
	}
}
