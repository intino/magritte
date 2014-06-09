package siani.tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import siani.tara.intellij.lang.psi.Parameter;
import org.jetbrains.annotations.NotNull;

public class ParametersMixin extends ASTWrapperPsiElement {
	public ParametersMixin(@NotNull ASTNode node) {
		super(node);
	}

	public Parameter[] getParameters() {
		return null;
	}
}
