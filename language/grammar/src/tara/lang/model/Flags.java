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

	public static List<Tag> hasTags() {
		return Arrays.asList(Tag.ENCLOSED, Tag.FINAL);
	}

	public static List<Tag> componentTags() {
		return Arrays.asList(Tag.ABSTRACT, Tag.TERMINAL, Tag.PROTOTYPE, Tag.FACET, Tag.FINAL, Tag.FEATURE, Tag.ENCLOSED, Tag.FINAL, Tag.MAIN, Tag.NAMED, Tag.PROFILER);
	}

	public static List<Tag> variableTags() {
		return Arrays.asList(Tag.TERMINAL, Tag.FINAL, Tag.PRIVATE, Tag.DEFINITION, Tag.NATIVE);
	}

	public static List<Tag> primeTags() {
		return Arrays.asList(Tag.ABSTRACT, Tag.TERMINAL, Tag.FACET, Tag.FINAL, Tag.FEATURE, Tag.PROTOTYPE, Tag.MAIN, Tag.NAMED, Tag.PROFILER);
	}

	public static List<Tag> internalTags() {
		return Arrays.asList(Tag.TERMINAL_INSTANCE, Tag.FACET_INSTANCE, Tag.FEATURE_INSTANCE);
	}
}
