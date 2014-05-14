package monet.::projectName::.intellij.metamodel.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiInvalidElementAccessException;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.SearchScope;
import monet.::projectName::.intellij.metamodel.::projectProperName::Icons;
import monet.::projectName::.intellij.metamodel.psi.Definition;
import monet.::projectName::.intellij.metamodel.psi.MetaIdentifier;
import monet.::projectName::.intellij.metamodel.psi.::projectProperName::Types;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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

	\@Override
	public Icon getIcon(\@IconFlags int i) {
		return ::projectProperName::Icons.DEFINITION_13;
	}

	public boolean isCase() {
		return getNode().findChildByType(::projectProperName::Types.CASE_KEY) != null;
	}

	public boolean isBase() {
		return getNode().findChildByType(::projectProperName::Types.BASE_KEY) != null;
	}

	\@Nullable
	public MetaIdentifier getType() {
		return findChildByClass(MetaIdentifier.class);
	}
}
