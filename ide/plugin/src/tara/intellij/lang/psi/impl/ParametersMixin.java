package tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.psi.Node;
import tara.intellij.lang.psi.Parameter;
import tara.intellij.lang.psi.TaraFacetApply;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ParametersMixin extends ASTWrapperPsiElement {
	public ParametersMixin(@NotNull ASTNode node) {
		super(node);
	}

	@NotNull
	public List<Parameter> getParameters() {
		Parameter[] childrenOfType = PsiTreeUtil.getChildrenOfType(this, Parameter.class);
		return childrenOfType == null ? Collections.EMPTY_LIST : Collections.unmodifiableList(Arrays.asList(childrenOfType));
	}

	public boolean areExplicit() {
		Collection<Parameter> parameters = getParameters();
		return !parameters.isEmpty() && parameters.iterator().next().isExplicit();
	}

	public TaraFacetApply isInFacet() {
		PsiElement aElement = this;
		while (!(aElement.getParent() instanceof Node) && !(aElement.getParent() instanceof TaraFacetApply))
			aElement = aElement.getParent();
		return (aElement.getParent() instanceof TaraFacetApply) ? (TaraFacetApply) aElement.getParent() : null;
	}
}
