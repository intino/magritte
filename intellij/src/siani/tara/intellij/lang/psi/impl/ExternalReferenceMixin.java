package siani.tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.lang.psi.resolve.TaraReferenceSolver;

public class ExternalReferenceMixin extends ASTWrapperPsiElement {
	public ExternalReferenceMixin(@NotNull ASTNode node) {
		super(node);
	}

	@NotNull
	@Override
	public PsiReference[] getReferences() {
		PsiElement identifierNode = TaraPsiImplUtil.getContextOf(this).getIdentifierNode();
		if (identifierNode == null) return PsiReference.EMPTY_ARRAY;
		return new PsiReference[]{new TaraReferenceSolver(identifierNode, new TextRange(0, identifierNode.getText().length()), true)};
	}

	@Nullable
	@Override
	public PsiReference getReference() {
		PsiElement identifierNode = TaraPsiImplUtil.getContextOf(this).getIdentifierNode();
		if (identifierNode == null) return null;
		PsiReference[] references = new PsiReference[]{new TaraReferenceSolver(identifierNode, new TextRange(0, identifierNode.getText().length()), true)};
		return references.length == 0 ? null : references[0];
	}
}
