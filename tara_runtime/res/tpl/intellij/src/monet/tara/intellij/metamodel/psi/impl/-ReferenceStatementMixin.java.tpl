package monet.::projectName::.intellij.metamodel.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiReference;
import monet.::projectName::.intellij.::projectProperName::Reference;
import monet.::projectName::.intellij.metamodel.psi.::projectProperName::ReferenceIdentifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by oroncal on 13/02/14.
 */
public class ::projectProperName::ReferenceStatementMixin extends ASTWrapperPsiElement {

	public ::projectProperName::ReferenceStatementMixin(\@NotNull ASTNode node) {
		super(node);
	}

	public String getIdentifier() {
		return ::projectProperName::PsiImplUtil.getIdentifier((::projectProperName::ReferenceIdentifier) this);
	}

	\@NotNull
	\@Override
	public PsiReference[] getReferences() {
		return new PsiReference[]{new ::projectProperName::Reference(this, new TextRange(0, getIdentifier().length()))};
	}

	\@Nullable
	\@Override
	public PsiReference getReference() {
		PsiReference[] references = new PsiReference[]{new ::projectProperName::Reference(this, new TextRange(0, getIdentifier().length()))};
		return references.length == 0 ? null : references[0];
	}
}

