package monet.::projectName::.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import monet.::projectName::.intellij.::projectProperName::ReferenceSolver;
import monet.::projectName::.intellij.lang.psi.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class IdentifierMixin extends ASTWrapperPsiElement {
	public IdentifierMixin(\@NotNull ASTNode node) {
		super(node);
	}

	public String getIdentifier() {
		return ::projectProperName::PsiImplUtil.getIdentifier((Identifier) this);
	}

	\@NotNull
	\@Override
	public PsiReference[] getReferences() {
		return new PsiReference[]{new ::projectProperName::ReferenceSolver(this, new TextRange(0, getIdentifier().length()), false)};
	}

	\@Nullable
	\@Override
	public PsiReference getReference() {
		PsiReference[] references = new PsiReference[]{new ::projectProperName::ReferenceSolver(this, new TextRange(0, getIdentifier().length()), false)};
		return references.length == 0 ? null \: references[0];
	}

	\@NotNull
	public PsiElement setName(String name) {
		Identifier identifier = ::projectProperName::ElementFactoryImpl.getInstance(this.getProject()).createNameIdentifier(name);
		ASTNode node = identifier.getNode();
		this.getParent().getNode().replaceChild(getNode(), node);
		return identifier;
	}

	\@Override
	public String getName() {
		return this.getText();
	}
}
