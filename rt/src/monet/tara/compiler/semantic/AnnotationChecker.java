package monet.tara.compiler.semantic;


import monet.tara.compiler.core.ast.ASTNode;
import monet.tara.compiler.core.ast.ASTNode.AnnotationType;
import monet.tara.compiler.core.errorcollection.semantic.SemanticErrorList;
import monet.tara.compiler.core.errorcollection.semantic.WrongAnnotationError;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnnotationChecker {

	private SemanticErrorList errors = new SemanticErrorList();
	private List<AnnotationType> annotations = new ArrayList<>();

	public AnnotationChecker(SemanticErrorList errors) {
		this.errors = errors;
	}

	public void checkAnnotations(ASTNode concept) {
		annotations = Arrays.asList(concept.getAnnotations());
		rootAnnotation(concept);
		optionalAnnotation(concept);
		multipleAnnotation(concept);
	}

	private void rootAnnotation(ASTNode concept) {
		if (!concept.isRoot() && annotations.contains(AnnotationType.ROOT))
			errors.add(new WrongAnnotationError(AnnotationType.ROOT.name(), concept));
	}

	private void optionalAnnotation(ASTNode concept) {
		if ((concept.isRoot() || concept.isMorph()) && annotations.contains(AnnotationType.OPTIONAL))
			errors.add(new WrongAnnotationError(AnnotationType.OPTIONAL.name(), concept));
	}

	private void multipleAnnotation(ASTNode concept) {
		if ((concept.isRoot() || concept.isMorph()) && annotations.contains(AnnotationType.MULTIPLE))
			errors.add(new WrongAnnotationError(AnnotationType.MULTIPLE.name(), concept));
	}
}
