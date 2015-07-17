package tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import tara.language.model.Tag;

public class AnnotationMixin extends ASTWrapperPsiElement {

	public AnnotationMixin(ASTNode node) {
		super(node);
	}

	public boolean is(Tag tag) {
		return this.getText().equalsIgnoreCase(tag.name());
	}

	@Override
	public String toString() {
		return getText();
	}
}

