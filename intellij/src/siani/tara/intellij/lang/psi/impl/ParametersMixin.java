package siani.tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.psi.Parameter;

public class ParametersMixin extends ASTWrapperPsiElement {
	public ParametersMixin(@NotNull ASTNode node) {
		super(node);
	}

	public Parameter[] getParameters() {
		return PsiTreeUtil.getChildrenOfType(this, Parameter.class);
	}
}
