package monet.tara.intellij.metamodel.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiInvalidElementAccessException;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.SearchScope;
import monet.tara.intellij.metamodel.TaraIcons;
import monet.tara.intellij.metamodel.psi.Concept;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class SignatureMixin extends ASTWrapperPsiElement {

	public SignatureMixin(@NotNull ASTNode node) {
		super(node);
	}

	@NotNull
	public SearchScope getUseScope() {
		return GlobalSearchScope.allScope(getProject());
	}

	@Override
	public String getName() {
		return TaraPsiImplUtil.getIdentifier((Concept) this);
	}

	public TaraFileImpl getFile() throws PsiInvalidElementAccessException {
		return (TaraFileImpl) super.getContainingFile();
	}


	public PsiElement getPsiElement() {
		return this;
	}


	public PsiElement getIdentifierNode() {
		return TaraPsiImplUtil.getIdentifierNode((Concept) this);
	}

	@Override
	public Icon getIcon(@IconFlags int i) {
		return TaraIcons.CONCEPT_13;
	}
}
