package siani.tara.lang;

import java.util.ArrayList;
import java.util.List;

import static siani.tara.lang.Annotation.*;
import static siani.tara.lang.Annotation.META_REQUIRED;

public class Annotations {

	public static final Annotation[] META_ANNOTATIONS = new Annotation[]{META_COMPONENT, META_SINGLE, META_NAMED,
		META_PROPERTY, META_REQUIRED, META_INTENTION, META_FACET, META_ADDRESSED, META_AGGREGATED, META_TERMINAL};
	public static final Annotation[] SUB_ANNOTATIONS = join(new Annotation[]{PROPERTY, NAMED,ABSTRACT, TERMINAL, ADDRESSED, AGGREGATED}, META_ANNOTATIONS);
	public static final Annotation[] HAS_ANNOTATIONS = new Annotation[]{COMPOSED, AGGREGATED, MULTIPLE, SINGLE, REQUIRED};
	public static final Annotation[] COMPONENT_ANNOTATIONS = join(new Annotation[]{ABSTRACT, TERMINAL, REQUIRED, SINGLE,
		PROPERTY, NAMED, INTENTION, ADDRESSED, AGGREGATED, FACET}, META_ANNOTATIONS);
	public static final Annotation[] VARIABLE_ANNOTATIONS = new Annotation[]{ABSTRACT, TERMINAL, PROPERTY, SINGLE, PROPERTY, LOCAL};
	public static final Annotation[] PRIME_ANNOTATIONS = join(new Annotation[]{ABSTRACT, COMPONENT, SINGLE, NAMED, TERMINAL,
		PROPERTY, REQUIRED, INTENTION, FACET, ADDRESSED, AGGREGATED}, META_ANNOTATIONS);


	private Annotations() {
	}

	public static String[] getAnnotations() {
		List<String> list = new ArrayList();
		for (Annotation annotation : values()) list.add(annotation.getName());
		return list.toArray(new String[list.size()]);
	}

	private static Annotation[] join(Annotation[] Annotations1, Annotation[] Annotations2) {
		Annotation[] AnnotationsAll = new Annotation[Annotations1.length + Annotations2.length];
		System.arraycopy(Annotations1, 0, AnnotationsAll, 0, Annotations1.length);
		System.arraycopy(Annotations2, 0, AnnotationsAll, Annotations1.length, Annotations2.length);
		return AnnotationsAll;
	}


	public static class Class {

	}

}
