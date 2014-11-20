package siani.tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import siani.tara.intellij.lang.psi.TaraConceptReference;
import siani.tara.lang.Annotations;

public class ConceptReferenceMixin extends ASTWrapperPsiElement {
	public ConceptReferenceMixin(ASTNode node) {
		super(node);
	}

	public boolean isAggregated() {
		TaraConceptReference reference = (TaraConceptReference) this;
		if (reference.getAnnotations() == null) return false;
		for (PsiElement psiElement : reference.getAnnotations().getAnnotations())
			if (Annotations.Annotation.AGGREGATED.getName().equals(psiElement.getText())) return true;
		return false;
	}


}
