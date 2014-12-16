package siani.tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import siani.tara.intellij.lang.psi.TaraTypes;

public class AnnotationMixin extends ASTWrapperPsiElement {


	public AnnotationMixin(ASTNode node) {
		super(node);
	}

	public boolean isMetaAnnotation() {
		for (LeafPsiElement leafPsiElement : findChildrenByClass(LeafPsiElement.class))
			if (leafPsiElement.getElementType().equals(TaraTypes.PLUS)) return true;
		return false;
	}

	public boolean is(siani.tara.lang.Annotation annotation) {
		return this.getText().equals(annotation.getName());
	}

	@Override
	public String toString() {
		return getText();
	}
}

