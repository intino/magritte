package siani.tara.compiler.semantic;


import siani.tara.compiler.core.errorcollection.semantic.SemanticErrorList;
import siani.tara.compiler.core.errorcollection.semantic.WrongAnnotationError;
import siani.tara.lang.Node;

import java.util.List;

import static java.util.Arrays.asList;
import static siani.tara.lang.Annotations.*;

public class AnnotationsAnalyzer {

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

	private void checkAnnotations(Node node, Annotation[] annotations) {
		List<Annotation> list = asList(annotations);
		for (Annotation annotation : node.getObject().getAnnotations())
			if (!list.contains(annotation))
				errors.add(new WrongAnnotationError(annotation.name(), node));
	}
}
