package siani.tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public class ConceptInjectionMixin extends ASTWrapperPsiElement {
	public ConceptInjectionMixin(@NotNull ASTNode node) {
		super(node);
	}
}
