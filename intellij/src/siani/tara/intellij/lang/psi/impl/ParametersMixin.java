package siani.tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.Parameter;
import siani.tara.intellij.lang.psi.TaraFacetApply;

public class ParametersMixin extends ASTWrapperPsiElement {
	public ParametersMixin(@NotNull ASTNode node) {
		super(node);
	}

	@NotNull
	public Parameter[] getParameters() {
		Parameter[] childrenOfType = PsiTreeUtil.getChildrenOfType(this, Parameter.class);
		return childrenOfType == null ? new Parameter[0] : childrenOfType;
	}

	public boolean areExplicit() {
		Parameter[] parameters = getParameters();
		return parameters.length != 0 && parameters[0].isExplicit();
	}

	public TaraFacetApply isInFacet() {
		PsiElement aElement = this;
		while (!(aElement.getParent() instanceof Concept) && !(aElement.getParent() instanceof TaraFacetApply))
			aElement = aElement.getParent();
		return (aElement.getParent() instanceof TaraFacetApply) ? (TaraFacetApply) aElement.getParent() : null;
	}
}
