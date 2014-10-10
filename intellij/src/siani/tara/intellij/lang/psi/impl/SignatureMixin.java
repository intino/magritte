package siani.tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiInvalidElementAccessException;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.SearchScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.lang.TaraIcons;
import siani.tara.intellij.lang.psi.*;

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

	public TaraBoxFileImpl getFile() throws PsiInvalidElementAccessException {
		return (TaraBoxFileImpl) super.getContainingFile();
	}


	@Override
	public Icon getIcon(@IconFlags int i) {
		return TaraIcons.getIcon(TaraIcons.CONCEPT);
	}

	public boolean isSub() {
		return getNode().findChildByType(TaraTypes.SUB) != null;
	}

	@Nullable
	public MetaIdentifier getType() {
		return findChildByClass(MetaIdentifier.class);
	}

	@Nullable
	public Concept getParentConcept() {
		IdentifierReference parentReference = findChildByClass(IdentifierReference.class);
		if (parentReference == null) return null;
		PsiElement resolve = ReferenceManager.resolve(parentReference);
		if (resolve instanceof Identifier) {
			Identifier identifier = (Identifier) resolve;
			return TaraPsiImplUtil.getConceptContextOf(identifier);
		}
		return null;
	}

	@Nullable
	public Parameters getParameters() {
		return findChildByClass(Parameters.class);
	}
}
