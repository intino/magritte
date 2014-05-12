package monet.tara.intellij.metamodel.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import monet.tara.intellij.TaraReferenceSolver;
import monet.tara.intellij.metamodel.psi.Identifier;
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
		//new TaraReferenceSolver(this, new TextRange(0, getIdentifier().length()))
		return new PsiReference[]{};
	}

	@Nullable
	@Override
	public PsiReference getReference() {
		PsiReference[] references = new PsiReference[]{new TaraReferenceSolver(this, new TextRange(0, getIdentifier().length()))};
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
	public String getName() {
		return this.getText();
	}
}