package siani.tara.lang;

import java.util.ArrayList;
import java.util.List;

import static siani.tara.lang.Annotation.*;

public class Annotations {

	public static final Annotation[] SUB_ANNOTATIONS = new Annotation[]{PROPERTY, NAMED, TERMINAL, ADDRESSED, AGGREGATED};
	public static final Annotation[] HAS_ANNOTATIONS = new Annotation[]{COMPOSED, AGGREGATED, MULTIPLE, SINGLE, REQUIRED};
	public static final Annotation[] COMPONENT_ANNOTATIONS = new Annotation[]{ABSTRACT, TERMINAL, REQUIRED, SINGLE,
		PROPERTY, NAMED, INTENTION, ADDRESSED, AGGREGATED, FACET};
	public static final Annotation[] VARIABLE_ANNOTATIONS = new Annotation[]{ABSTRACT, TERMINAL, PROPERTY, SINGLE, PROPERTY, LOCAL};
	public static final Annotation[] PRIME_ANNOTATIONS = new Annotation[]{ABSTRACT, COMPONENT, SINGLE, NAMED, TERMINAL,
		PROPERTY, REQUIRED, INTENTION, FACET, ADDRESSED, AGGREGATED};

	private Annotations() {
	}

	public static String[] getAnnotations() {
		List<String> list = new ArrayList();
		for (Annotation annotation : values()) list.add(annotation.getName());
		return list.toArray(new String[list.size()]);
	}


}
