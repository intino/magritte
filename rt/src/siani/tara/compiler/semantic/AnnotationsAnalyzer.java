package siani.tara.compiler.semantic;


import siani.tara.compiler.core.errorcollection.semantic.SemanticErrorList;
import siani.tara.compiler.core.errorcollection.semantic.WrongAnnotationError;
import siani.tara.lang.ModelObject.AnnotationType;
import siani.tara.lang.Node;

import java.util.List;

import static java.util.Arrays.asList;

public class AnnotationsAnalyzer {

	public static final String COMPONENT = "component";
	public static final String NAMED = "named";
	public static final String REQUIRED = "required";
	public static final String SINGLE = "single";
	public static final String TERMINAL = "terminal";
	public static final String PROPERTY = "property";
	public static final String[] SUB_ANNOTATIONS = new String[]{PROPERTY, NAMED, TERMINAL};
	public static final String PRIVATE = "private";
	public static final String[] COMPONENT_ANNOTATIONS = new String[]{PRIVATE, TERMINAL, REQUIRED, SINGLE, PROPERTY, NAMED};
	public static final String INTENTION = "intention";
	public static final String FACET = "facet";
	public static final String[] PRIME_ANNOTATIONS = new String[]{PRIVATE, COMPONENT, SINGLE, NAMED, TERMINAL, PROPERTY, REQUIRED, INTENTION, FACET};
	private SemanticErrorList errors = new SemanticErrorList();

	public AnnotationsAnalyzer(SemanticErrorList errors) {
		this.errors = errors;
	}

	public void checkAnnotations(Node node) {
		if (node.isPrime()) checkAnnotations(node, PRIME_ANNOTATIONS);
		else if (node.isSub())
			checkAnnotations(node, SUB_ANNOTATIONS);
		else checkAnnotations(node, COMPONENT_ANNOTATIONS);
	}

	private void checkAnnotations(Node node, String[] annotations) {
		List<String> list = asList(annotations);
		for (AnnotationType annotation : node.getObject().getAnnotations()) {
			if (!list.contains(annotation.name().toLowerCase()))
				errors.add(new WrongAnnotationError(annotation.name(), node));
		}
	}
}
