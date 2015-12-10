package tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiInvalidElementAccessException;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.SearchScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.intellij.lang.TaraIcons;
import tara.intellij.lang.psi.*;
import tara.intellij.lang.psi.resolve.ReferenceManager;
import tara.lang.model.Node;

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
		return TaraPsiImplUtil.getIdentifier((Node) this);
	}

	public TaraModelImpl getFile() throws PsiInvalidElementAccessException {
		return (TaraModelImpl) super.getContainingFile();
	}


	@Override
	public Icon getIcon(@IconFlags int i) {
		return TaraIcons.NODE;
	}

	public boolean isSub() {
		return getNode().findChildByType(TaraTypes.SUB) != null;
	}

	@Nullable
	public MetaIdentifier getType() {
		return findChildByClass(MetaIdentifier.class);
	}

	@Nullable
	public Node getParentNode() {
		IdentifierReference parentReference = findChildByClass(IdentifierReference.class);
		if (parentReference == null) return null;
		return ReferenceManager.resolveToNode(parentReference);
	}


	public Flags getFlags() {
		TaraTags tags = ((TaraSignature) this).getTags();
		return tags != null ? tags.getFlags() : null;
	}

	public Annotations getAnnotations() {
		TaraTags tags = ((TaraSignature) this).getTags();
		return tags != null ? tags.getAnnotations() : null;
	}

	@Nullable
	public TaraIdentifierReference getParentReference() {
		return findChildByClass(TaraIdentifierReference.class);
	}

	@Nullable
	public Parameters getParameters() {
		return findChildByClass(Parameters.class);
	}
}
