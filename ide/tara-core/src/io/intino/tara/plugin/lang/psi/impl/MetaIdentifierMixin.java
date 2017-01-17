package io.intino.tara.plugin.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiReference;
import io.intino.tara.plugin.lang.psi.resolve.TaraMetaReferenceSolver;
import io.intino.tara.plugin.lang.psi.MetaIdentifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MetaIdentifierMixin extends ASTWrapperPsiElement {
	public MetaIdentifierMixin(@NotNull ASTNode node) {
		super(node);
	}

	public String getIdentifier() {
		return this.getText();
	}

	@NotNull
	@Override
	public PsiReference[] getReferences() {
		return new PsiReference[]{new TaraMetaReferenceSolver((MetaIdentifier) this, new TextRange(0, getIdentifier().length()))};
	}

	@Nullable
	@Override
	public PsiReference getReference() {
		PsiReference[] references = new PsiReference[]{new TaraMetaReferenceSolver((MetaIdentifier) this, new TextRange(0, getIdentifier().length()))};
		return references.length == 0 ? null : references[0];
	}

	@Override
	public String getName() {
		return this.getText();
	}
}
