package siani.tara.intellij.lang.lexer;

import java.util.ArrayList;
import java.util.List;

import static siani.tara.intellij.lang.lexer.Annotation.*;


public class Annotations {

	public static final Annotation[] META_ANNOTATIONS = new Annotation[]{META_COMPONENT, META_SINGLE, META_NAMED,
		META_PROPERTY, META_REQUIRED, META_INTENTION, META_FACET, META_ADDRESSED, META_AGGREGATED, META_TERMINAL};

	public static final Annotation[] SUB_ANNOTATIONS = join(new Annotation[]{PROPERTY, NAMED, ABSTRACT, TERMINAL, INTENTION,
		ADDRESSED, AGGREGATED, CASE, ENCLOSED}, META_ANNOTATIONS);

	public static final Annotation[] HAS_ANNOTATIONS = new Annotation[]{AGGREGATED, SINGLE, REQUIRED, ENCLOSED, ASSOCIATED};

	public static final Annotation[] COMPONENT_ANNOTATIONS = join(new Annotation[]{ABSTRACT, TERMINAL, REQUIRED, SINGLE,
		PROPERTY, NAMED, INTENTION, ADDRESSED, AGGREGATED, ASSOCIATED, FACET, ENCLOSED}, META_ANNOTATIONS);

	public static final Annotation[] VARIABLE_ANNOTATIONS = new Annotation[]{TERMINAL, READONLY};

	public static final Annotation[] PRIME_ANNOTATIONS = join(new Annotation[]{ABSTRACT, COMPONENT, ASSOCIATED, SINGLE, NAMED, TERMINAL,
		PROPERTY, REQUIRED, INTENTION, FACET, ADDRESSED, AGGREGATED, CASE}, META_ANNOTATIONS);


	private Annotations() {
	}

	public static String[] getAnnotations() {
		List<String> list = new ArrayList();
		for (Annotation annotation : values()) list.add(annotation.getName());
		return list.toArray(new String[list.size()]);
	}


	private static Annotation[] join(Annotation[] annotations1, Annotation[] annotations2) {
		Annotation[] annotationsAll = new Annotation[annotations1.length + annotations2.length];
		System.arraycopy(annotations1, 0, annotationsAll, 0, annotations1.length);
		System.arraycopy(annotations2, 0, annotationsAll, annotations1.length, annotations2.length);
		return annotationsAll;
	}

}
