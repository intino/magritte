package siani.tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.psi.TaraFacetApply;

public class FacetApplyMixin extends ASTWrapperPsiElement {

	public FacetApplyMixin(@NotNull ASTNode node) {
		super(node);
	}

	public String getFacetName() {
		if (!((TaraFacetApply) this).getMetaIdentifierList().isEmpty())
			return ((TaraFacetApply) this).getMetaIdentifierList().get(0).getText();
		return null;
	}
}
