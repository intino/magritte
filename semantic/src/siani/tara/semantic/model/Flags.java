package siani.tara.semantic.model;

import java.util.ArrayList;
import java.util.List;

import static siani.tara.semantic.model.Tag.*;


public class Flags {

	public static final Tag[] SUB_ANNOTATIONS = new Tag[]{ABSTRACT, TERMINAL, ENCLOSED, FINAL, PROTOTYPE, FEATURE, MAIN};

	public static final Tag[] HAS_ANNOTATIONS = new Tag[]{SINGLE, REQUIRED, ENCLOSED, FINAL};

	public static final Tag[] COMPONENT_ANNOTATIONS = new Tag[]{ABSTRACT, TERMINAL, PROTOTYPE, REQUIRED, SINGLE,
		FACET, FINAL, FEATURE, ENCLOSED, FINAL, MAIN};

	public static final Tag[] VARIABLE_ANNOTATIONS = new Tag[]{TERMINAL, FINAL, PRIVATE};

	public static final Tag[] PRIME_ANNOTATIONS = new Tag[]{ABSTRACT, SINGLE, TERMINAL,
		REQUIRED, FACET, FINAL, FEATURE, PROTOTYPE, MAIN};

	private Flags() {
	}

	public static String[] all() {
		List<String> list = new ArrayList();
		for (Tag tags : values()) list.add(tags.name());
		return list.toArray(new String[list.size()]);
	}
}
