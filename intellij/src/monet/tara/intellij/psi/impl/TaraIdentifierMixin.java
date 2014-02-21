package monet.tara.intellij.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiReference;
import monet.tara.compiler.intellij.psi.TaraIdentifier;
import monet.tara.intellij.TaraReference;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TaraIdentifierMixin extends ASTWrapperPsiElement {
	public TaraIdentifierMixin(@NotNull ASTNode node) {
		super(node);
	}

	public String getIdentifier() {
		return TaraPsiImplUtil.getIdentifier((TaraIdentifier) this);
	}

	@NotNull
	@Override
	public PsiReference[] getReferences() {
		return new PsiReference[]{new TaraReference(this, new TextRange(0, getIdentifier().length()))};
	}

	@Nullable
	@Override
	public PsiReference getReference() {
		PsiReference[] references = new PsiReference[]{new TaraReference(this, new TextRange(0, getIdentifier().length()))};
		return references.length == 0 ? null : references[0];
	}
}
