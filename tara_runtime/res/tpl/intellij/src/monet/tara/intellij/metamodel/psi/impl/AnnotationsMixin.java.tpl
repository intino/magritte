package monet.::projectName::.intellij.metamodel.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import monet.::projectName::.intellij.metamodel.psi.::projectProperName::Annotations;
import org.jetbrains.annotations.NotNull;

public class AnnotationsMixin extends ASTWrapperPsiElement {
	public AnnotationsMixin(\@NotNull ASTNode node) {
		super(node);
	}

	public PsiElement[] getAnnotations() {
		return ::projectProperName::PsiImplUtil.getAnnotations((::projectProperName::Annotations)this);
	}

}
