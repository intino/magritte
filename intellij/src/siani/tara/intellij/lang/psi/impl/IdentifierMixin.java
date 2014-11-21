package siani.tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.lang.TaraIcons;
import siani.tara.intellij.lang.psi.Identifier;
import siani.tara.intellij.lang.psi.resolve.TaraInternalReferenceSolver;

import javax.swing.*;

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
		return new PsiReference[]{new TaraInternalReferenceSolver(this, new TextRange(0, getIdentifier().length()))};
	}

	@Nullable
	@Override
	public PsiReference getReference() {
		PsiReference[] references = new PsiReference[]{new TaraInternalReferenceSolver(this, new TextRange(0, getIdentifier().length()))};
		return references.length == 0 ? null : references[0];
	}

	@NotNull
	public PsiElement setName(String name) {
		Identifier identifier = TaraElementFactoryImpl.getInstance(this.getProject()).createNameIdentifier(name);
		ASTNode node = identifier.getNode();
		this.getParent().getNode().replaceChild(getNode(), node);
		return identifier;
	}

	@Override
	public Icon getIcon(@IconFlags int i) {
		return TaraIcons.getIcon(TaraIcons.CONCEPT);
	}

	@Override
	public String getName() {
		return this.getText();
	}

	public String toString() {
		return this.getName();
	}
}
