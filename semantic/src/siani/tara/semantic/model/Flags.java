package siani.tara.semantic.model;

import java.util.ArrayList;
import java.util.List;

import static siani.tara.semantic.model.Tag.*;


public class Flags {

	public static final Tag[] SUB_ANNOTATIONS = new Tag[]{IMPLICIT, ABSTRACT, TERMINAL, ENCLOSED, FINAL};

	public static final Tag[] HAS_ANNOTATIONS = new Tag[]{SINGLE, REQUIRED, ENCLOSED, FINAL};

	public static final Tag[] COMPONENT_ANNOTATIONS = new Tag[]{ABSTRACT, TERMINAL, REQUIRED, SINGLE,
		IMPLICIT, FACET, ENCLOSED, FINAL};

	public static final Tag[] VARIABLE_ANNOTATIONS = new Tag[]{TERMINAL, FINAL};

	public static final Tag[] PRIME_ANNOTATIONS = new Tag[]{ABSTRACT, SINGLE, TERMINAL,
		IMPLICIT, REQUIRED, FACET, FINAL};

	private Flags() {
	}

	public static String[] all() {
		List<String> list = new ArrayList();
		for (Tag tags : values()) list.add(tags.name());
		return list.toArray(new String[list.size()]);
	}
}
