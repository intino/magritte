package monet.tara.intellij.metamodel.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiReference;
import monet.tara.intellij.TaraReferenceSolver;
import monet.tara.intellij.metamodel.psi.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class IdentifierMixin extends ASTWrapperPsiElement {
	public IdentifierMixin(@NotNull ASTNode node) {
		super(node);
	}

	public String getIdentifier() {
		return TaraPsiImplUtil.getIdentifier((Identifier) this);
	}

	@NotNull
	@Override
	public PsiReference[] getReferences() {
		return new PsiReference[]{new TaraReferenceSolver(this, new TextRange(0, getIdentifier().length()))};
	}

	@Nullable
	@Override
	public PsiReference getReference() {
		PsiReference[] references = new PsiReference[]{new TaraReferenceSolver(this, new TextRange(0, getIdentifier().length()))};
		return references.length == 0 ? null : references[0];
	}
}
