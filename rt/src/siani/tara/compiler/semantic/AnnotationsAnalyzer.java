package siani.tara.compiler.semantic;


import siani.tara.compiler.core.errorcollection.semantic.SemanticErrorList;
import siani.tara.compiler.core.errorcollection.semantic.WrongAnnotationError;
import siani.tara.model.Annotation;
import siani.tara.model.Annotations;
import siani.tara.model.Node;

import java.util.List;

import static java.util.Arrays.asList;

public class AnnotationsAnalyzer {

	private SemanticErrorList errors = new SemanticErrorList();

	public AnnotationsAnalyzer(SemanticErrorList errors) {
		this.errors = errors;
	}

	public void checkAnnotations(Node node) {
		if (node.isPrime()) checkAnnotations(node, Annotations.PRIME_ANNOTATIONS);
		else if (node.isSub())
			checkAnnotations(node, Annotations.SUB_ANNOTATIONS);
		else checkAnnotations(node, Annotations.COMPONENT_ANNOTATIONS);
	}

	private void checkAnnotations(Node node, Annotation[] annotations) {
		List<Annotation> list = asList(annotations);
		for (Annotation annotation : node.getAnnotations())
			if (!list.contains(annotation))
				errors.add(new WrongAnnotationError(annotation.name(), node));
	}
}
