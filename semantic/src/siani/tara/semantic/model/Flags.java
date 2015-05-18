package siani.tara.semantic.model;

import java.util.ArrayList;
import java.util.List;

import static siani.tara.semantic.model.Tag.*;


public class Flags {

	public static final Tag[] SUB_ANNOTATIONS = new Tag[]{PROPERTY, ABSTRACT, TERMINAL, ENCLOSED};

	public static final Tag[] HAS_ANNOTATIONS = new Tag[]{SINGLE, REQUIRED, ENCLOSED};

	public static final Tag[] COMPONENT_ANNOTATIONS = new Tag[]{ABSTRACT, TERMINAL, REQUIRED, SINGLE,
		PROPERTY, FACET, ENCLOSED};

	public static final Tag[] VARIABLE_ANNOTATIONS = new Tag[]{TERMINAL, READONLY};

	public static final Tag[] PRIME_ANNOTATIONS = new Tag[]{ABSTRACT, SINGLE, TERMINAL,
		PROPERTY, REQUIRED, FACET};

	private Flags() {
	}

	public static String[] all() {
		List<String> list = new ArrayList();
		for (Tag tags : values()) list.add(tags.name());
		return list.toArray(new String[list.size()]);
	}
}
