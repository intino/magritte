package tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import tara.intellij.lang.psi.FacetTarget;

public class FacetTargetMixin extends ASTWrapperPsiElement {

	public FacetTargetMixin(ASTNode node) {
		super(node);
	}

	public String toString() {
		return ((FacetTarget) this).qualifiedName();
	}

}
