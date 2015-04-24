package siani.tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import siani.tara.intellij.lang.lexer.Tag;

public class AnnotationMixin extends ASTWrapperPsiElement {

	public AnnotationMixin(ASTNode node) {
		super(node);
	}

	public boolean is(Tag tag) {
		return this.getText().equals(tag.getName());
	}

	@Override
	public String toString() {
		return getText();
	}
}

