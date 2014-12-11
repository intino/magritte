package siani.tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import siani.tara.intellij.lang.psi.Annotation;
import siani.tara.intellij.lang.psi.Annotations;
import siani.tara.intellij.lang.psi.TaraConceptReference;

import static siani.tara.lang.Annotation.AGGREGATED;

public class ConceptReferenceMixin extends ASTWrapperPsiElement {
	public ConceptReferenceMixin(ASTNode node) {
		super(node);
	}

	public boolean isAggregated() {
		TaraConceptReference reference = (TaraConceptReference) this;
		Annotations annotations = reference.getAnnotations();
		if (annotations == null) return false;
		for (Annotation annotation : annotations.getNormalAnnotations())
			if (AGGREGATED.getName().equals(annotation.getText())) return true;
		return false;
	}
}
