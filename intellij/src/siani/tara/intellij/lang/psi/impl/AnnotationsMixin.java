package siani.tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AnnotationsMixin extends ASTWrapperPsiElement {
	public AnnotationsMixin(@NotNull ASTNode node) {
		super(node);
	}

	public PsiElement[] getAnnotations() {
		List<PsiElement> annotations = new ArrayList<>();
		for (LeafPsiElement leafPsiElement : findChildrenByClass(LeafPsiElement.class)) {
			if (leafPsiElement instanceof PsiWhiteSpace) continue;
			annotations.add(leafPsiElement);
		}
		return annotations.toArray(new PsiElement[annotations.size()]);
	}

}
