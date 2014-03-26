package monet.::projectName::.intellij.metamodel.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiInvalidElementAccessException;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.SearchScope;
import monet.::projectName::.intellij.metamodel.::projectProperName::Icons;
import monet.::projectName::.intellij.metamodel.psi.Definition;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class SignatureMixin extends ASTWrapperPsiElement {

	public SignatureMixin(\@NotNull ASTNode node) {
		super(node);
	}

	\@NotNull
	public SearchScope getUseScope() {
		return GlobalSearchScope.allScope(getProject());
	}

	\@Override
	public String getName() {
		return ::projectProperName::PsiImplUtil.getIdentifier((Definition) this);
	}

	public ::projectProperName::FileImpl getFile() throws PsiInvalidElementAccessException {
		return (::projectProperName::FileImpl) super.getContainingFile();
	}


	public PsiElement getPsiElement() {
		return this;
	}


	public PsiElement getIdentifierNode() {
		return ::projectProperName::PsiImplUtil.getIdentifierNode((Definition) this);
	}

	\@Override
	public Icon getIcon(\@IconFlags int i) {
		return ::projectProperName::Icons.CONCEPT_13;
	}
}
