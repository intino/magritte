package siani.tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public class ReferenceStatementMixin extends ASTWrapperPsiElement {
	public ReferenceStatementMixin(@NotNull ASTNode node) {
		super(node);
	}
}
