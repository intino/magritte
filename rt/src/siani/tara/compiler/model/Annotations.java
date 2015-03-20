package siani.tara.compiler.model;

import java.util.ArrayList;
import java.util.List;

public class Annotations {

	public static final Annotation[] META_ANNOTATIONS = new Annotation[]{Annotation.META_COMPONENT, Annotation.META_SINGLE, Annotation.META_NAMED,
		Annotation.META_PROPERTY, Annotation.META_REQUIRED, Annotation.META_INTENTION, Annotation.META_FACET, Annotation.META_ADDRESSED, Annotation.META_AGGREGATED, Annotation.META_TERMINAL};

	public static final Annotation[] SUB_ANNOTATIONS = join(new Annotation[]{Annotation.PROPERTY, Annotation.NAMED, Annotation.ABSTRACT, Annotation.TERMINAL,
		Annotation.ADDRESSED, Annotation.AGGREGATED, Annotation.CASE, Annotation.ENCLOSED, Annotation.INTENTION}, META_ANNOTATIONS);

	public static final Annotation[] HAS_ANNOTATIONS = new Annotation[]{Annotation.AGGREGATED, Annotation.SINGLE, Annotation.REQUIRED, Annotation.ENCLOSED, Annotation.ASSOCIATED};

	public static final Annotation[] COMPONENT_ANNOTATIONS = join(new Annotation[]{Annotation.ABSTRACT, Annotation.TERMINAL, Annotation.REQUIRED, Annotation.SINGLE,
		Annotation.PROPERTY, Annotation.NAMED, Annotation.INTENTION, Annotation.ADDRESSED, Annotation.AGGREGATED, Annotation.ASSOCIATED, Annotation.FACET, Annotation.ENCLOSED}, META_ANNOTATIONS);

	public static final Annotation[] VARIABLE_ANNOTATIONS = new Annotation[]{Annotation.TERMINAL, Annotation.READONLY};

	public static final Annotation[] PRIME_ANNOTATIONS = join(new Annotation[]{Annotation.ABSTRACT, Annotation.COMPONENT, Annotation.ASSOCIATED, Annotation.SINGLE, Annotation.NAMED, Annotation.TERMINAL,
		Annotation.PROPERTY, Annotation.REQUIRED, Annotation.INTENTION, Annotation.FACET, Annotation.ADDRESSED, Annotation.AGGREGATED, Annotation.CASE}, META_ANNOTATIONS);


	private Annotations() {
	}

	public static String[] getAnnotations() {
		List<String> list = new ArrayList();
		for (Annotation annotation : Annotation.values()) list.add(annotation.getName());
		return list.toArray(new String[list.size()]);
	}


	private static Annotation[] join(Annotation[] annotations1, Annotation[] annotations2) {
		Annotation[] annotationsAll = new Annotation[annotations1.length + annotations2.length];
		System.arraycopy(annotations1, 0, annotationsAll, 0, annotations1.length);
		System.arraycopy(annotations2, 0, annotationsAll, annotations1.length, annotations2.length);
		return annotationsAll;
	}


	public static class Class {

	}

}
