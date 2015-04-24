package siani.tara.intellij.lang.lexer;

import java.util.ArrayList;
import java.util.List;

import static siani.tara.intellij.lang.lexer.Tag.*;


public class Flags {

	public static final Tag[] SUB_ANNOTATIONS = new Tag[]{PROPERTY, ABSTRACT, TERMINAL,
		ADDRESSED, AGGREGATED, ENCLOSED};

	public static final Tag[] HAS_ANNOTATIONS = new Tag[]{AGGREGATED, SINGLE, REQUIRED, ENCLOSED, ASSOCIATED};

	public static final Tag[] COMPONENT_ANNOTATIONS = new Tag[]{ABSTRACT, TERMINAL, REQUIRED, SINGLE,
		PROPERTY, ADDRESSED, AGGREGATED, ASSOCIATED, FACET, ENCLOSED};

	public static final Tag[] VARIABLE_ANNOTATIONS = new Tag[]{TERMINAL, READONLY};

	public static final Tag[] PRIME_ANNOTATIONS = new Tag[]{ABSTRACT, COMPONENT, ASSOCIATED, SINGLE, TERMINAL,
		PROPERTY, REQUIRED, NATIVE, FACET, ADDRESSED, AGGREGATED};

	private Flags() {
	}

	public static String[] all() {
		List<String> list = new ArrayList();
		for (Tag tags : values()) list.add(tags.getName());
		return list.toArray(new String[list.size()]);
	}



}
