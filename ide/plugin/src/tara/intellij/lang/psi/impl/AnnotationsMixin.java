package tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public class AnnotationsMixin extends ASTWrapperPsiElement {
	public AnnotationsMixin(@NotNull ASTNode node) {
		super(node);
	}


}
