package siani.tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiInvalidElementAccessException;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.SearchScope;
import siani.tara.intellij.lang.TaraIcons;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.MetaIdentifier;
import siani.tara.intellij.lang.psi.Parameters;
import siani.tara.intellij.lang.psi.TaraTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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

	@Override
	public Icon getIcon(@IconFlags int i) {
		return TaraIcons.getIcon(TaraIcons.CONCEPT);
	}

	public boolean isCase() {
		return getNode().findChildByType(TaraTypes.CASE_KEY) != null;
	}


	@Nullable
	public MetaIdentifier getType() {
		return findChildByClass(MetaIdentifier.class);
	}

	@Nullable
	public Parameters getParameters() {
		return findChildByClass(Parameters.class);
	}
}
