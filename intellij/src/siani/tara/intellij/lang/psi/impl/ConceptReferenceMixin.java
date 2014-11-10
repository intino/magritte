package siani.tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import siani.tara.intellij.lang.psi.TaraConceptReference;
import siani.tara.intellij.lang.psi.TaraTypes;

public class ConceptReferenceMixin extends ASTWrapperPsiElement {
	public ConceptReferenceMixin(ASTNode node) {
		super(node);
	}

	public boolean isAggregated() {
		TaraConceptReference reference = (TaraConceptReference) this;
		return reference.getLastChild().getNode().getElementType().equals(TaraTypes.AGGREGATED);
	}


}
