package siani.tara.compiler.semantic;


import siani.tara.compiler.core.errorcollection.semantic.SemanticErrorList;
import siani.tara.compiler.core.errorcollection.semantic.WrongAnnotationError;
import siani.tara.lang.ModelObject.AnnotationType;
import siani.tara.lang.Node;
import siani.tara.lang.NodeObject;

public class AnnotationsAnalyzer {

	private SemanticErrorList errors = new SemanticErrorList();

	public AnnotationsAnalyzer(SemanticErrorList errors) {
		this.errors = errors;
	}

	public void checkAnnotations(Node node) {
		rootAnnotation(node);
		requiredAnnotation(node);
		singleAnnotation(node);
		propertyAnnotation(node);
	}

	private void rootAnnotation(Node node) {
		if (!node.isPrime() && node.getObject().is(AnnotationType.ROOT))
			if (node.isCase()) {
				if (!checkRootCase(node))
					errors.add(new WrongAnnotationError(AnnotationType.ROOT.name(), node));
			} else errors.add(new WrongAnnotationError(AnnotationType.ROOT.name(), node));
	}

	private boolean checkRootCase(Node node) {
		NodeObject caseNode = node.getObject();
		while (caseNode.getParent() != null)
			if (!caseNode.getParent().is(AnnotationType.ROOT)) return false;
			else caseNode = caseNode.getParent();
		return true;
	}

	private void requiredAnnotation(Node node) {
		if (node.isCase() && node.getObject().is(AnnotationType.REQUIRED))
			errors.add(new WrongAnnotationError(AnnotationType.REQUIRED.name(), node));
	}

	private void singleAnnotation(Node node) {
		if (node.isCase() && node.getObject().is(AnnotationType.SINGLE))
			errors.add(new WrongAnnotationError(AnnotationType.SINGLE.name(), node));
	}

	private void propertyAnnotation(Node node) {
		if (node.isCase() && node.getObject().is(AnnotationType.SINGLE))
			errors.add(new WrongAnnotationError(AnnotationType.SINGLE.name(), node));
	}
}
