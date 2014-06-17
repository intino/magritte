package siani.tafat.intellij.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import siani.tafat.intellij.psi.TafatNamedElement;
import org.jetbrains.annotations.NotNull;

public abstract class TafatNamedElementImpl extends ASTWrapperPsiElement implements TafatNamedElement {
	public TafatNamedElementImpl(@NotNull ASTNode node) {
		super(node);
	}
}