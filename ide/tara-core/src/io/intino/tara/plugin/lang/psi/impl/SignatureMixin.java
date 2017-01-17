package io.intino.tara.plugin.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiInvalidElementAccessException;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.SearchScope;
import io.intino.tara.plugin.lang.TaraIcons;
import io.intino.tara.plugin.lang.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import io.intino.tara.plugin.lang.psi.resolve.ReferenceManager;
import io.intino.tara.lang.model.Node;

import javax.swing.*;
import java.util.Collections;
import java.util.List;

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
	public Node parent() {
		IdentifierReference parentReference = findChildByClass(IdentifierReference.class);
		if (parentReference == null) return null;
		return ReferenceManager.resolveToNode(parentReference);
	}

	@Nullable
	public List<TaraFacetApply> facets() {
		if (((TaraSignature) this).getFacets() == null) return Collections.emptyList();
		return ((TaraSignature) this).getFacets().getFacetApplyList();
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
