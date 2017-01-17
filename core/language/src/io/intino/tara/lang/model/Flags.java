package io.intino.tara.lang.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static io.intino.tara.lang.model.Tag.*;

public class Flags {

	private Flags() {
	}

	public static String[] all() {
		List<String> list = new ArrayList();
		for (Tag tags : values()) list.add(tags.name());
		return list.toArray(new String[list.size()]);
	}

	public static List<Tag> forRoot() {
		return Arrays.asList(Abstract, Final, Enclosed, Feature, Component, Volatile, Versioned);
	}

	public static List<Tag> forReference() {
		return Collections.singletonList(Final);
	}

	public static List<Tag> forComponent() {
		return Arrays.asList(Abstract, Final, Feature, Enclosed, Final, Component, Volatile, Versioned);
	}

	public static List<Tag> forVariable() {
		return Arrays.asList(Final, Private, Concept, Reactive, Terminal, Volatile);
	}

	public static List<Tag> internalTags() {
		return Arrays.asList(Instance, Facet, FacetInstance, Terminal);
	}
}
