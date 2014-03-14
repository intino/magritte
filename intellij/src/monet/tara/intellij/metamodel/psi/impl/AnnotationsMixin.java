package monet.tara.intellij.metamodel.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import monet.tara.intellij.metamodel.psi.TaraAnnotations;
import org.jetbrains.annotations.NotNull;

public class AnnotationsMixin extends ASTWrapperPsiElement {
	public AnnotationsMixin(@NotNull ASTNode node) {
		super(node);
	}

	public PsiElement[] getAnnotations() {
		return TaraPsiImplUtil.getAnnotations((TaraAnnotations)this);
	}

}
