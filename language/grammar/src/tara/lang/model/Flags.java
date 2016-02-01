package tara.lang.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Flags {

	private Flags() {
	}

	public static String[] all() {
		List<String> list = new ArrayList();
		for (Tag tags : Tag.values()) list.add(tags.name());
		return list.toArray(new String[list.size()]);
	}

	public static List<Tag> forRoot() {
		return Arrays.asList(Tag.Abstract, Tag.Final, Tag.Feature, Tag.Prototype, Tag.Component, Tag.Facet);
	}

	public static List<Tag> forReference() {
		return Arrays.asList(Tag.Enclosed, Tag.Final);
	}

	public static List<Tag> forComponent() {
		return Arrays.asList(Tag.Abstract, Tag.Prototype, Tag.Final, Tag.Feature, Tag.Enclosed, Tag.Final, Tag.Component);
	}

	public static List<Tag> forVariable() {
		return Arrays.asList(Tag.Final, Tag.Private, Tag.Concept, Tag.Native, Tag.Terminal);
	}

	public static List<Tag> internalTags() {
		return Arrays.asList(Tag.Instance, Tag.Facet, Tag.FacetInstance, Tag.Terminal);
	}
}
