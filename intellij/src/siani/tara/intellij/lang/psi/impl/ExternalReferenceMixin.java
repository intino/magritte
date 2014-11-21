package siani.tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.resolve.TaraExternalReferenceSolver;

@SuppressWarnings("UnusedDeclaration")
public class ExternalReferenceMixin extends ASTWrapperPsiElement {
	public ExternalReferenceMixin(@NotNull ASTNode node) {
		super(node);
	}

	@NotNull
	@Override
	public PsiReference[] getReferences() {
		Concept contextOf = TaraPsiImplUtil.getConceptContainerOf(this);
		if (contextOf == null) return PsiReference.EMPTY_ARRAY;
		PsiElement identifierNode = contextOf.getIdentifierNode();
		if (identifierNode == null) return PsiReference.EMPTY_ARRAY;
		return new PsiReference[]{new TaraExternalReferenceSolver(identifierNode, new TextRange(0, identifierNode.getText().length()))};
	}

	@Nullable
	@Override
	public PsiReference getReference() {
		Concept contextOf = TaraPsiImplUtil.getConceptContainerOf(this);
		if (contextOf == null) return null;
		PsiElement identifierNode = contextOf.getIdentifierNode();
		if (identifierNode == null) return null;
		PsiReference[] references = new PsiReference[]{new TaraExternalReferenceSolver(identifierNode, new TextRange(0, identifierNode.getText().length()))};
		return references.length == 0 ? null : references[0];
	}
}
