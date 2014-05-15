package monet.::projectName::.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import monet.::projectName::.intellij.::projectProperName::ReferenceSolver;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ExternalReferenceMixin extends ASTWrapperPsiElement {
	public ExternalReferenceMixin(\@NotNull ASTNode node) {
		super(node);
	}

	\@NotNull
	\@Override
	public PsiReference[] getReferences() {
		PsiElement identifierNode = ::projectProperName::PsiImplUtil.getContextOf(this).getIdentifierNode();
		return new PsiReference[]{new ::projectProperName::ReferenceSolver(identifierNode, new TextRange(0, identifierNode.getText().length()), true)};
	}

	\@Nullable
	\@Override
	public PsiReference getReference() {
		PsiElement identifierNode = ::projectProperName::PsiImplUtil.getContextOf(this).getIdentifierNode();
		PsiReference[] references = new PsiReference[]{new ::projectProperName::ReferenceSolver(identifierNode, new TextRange(0, identifierNode.getText().length()), true)};
		return references.length == 0 ? null \: references[0];
	}
}
