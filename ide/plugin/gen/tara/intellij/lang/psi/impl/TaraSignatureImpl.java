// This is a generated file. Not intended for manual editing.
package tara.intellij.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.intellij.lang.psi.*;
import tara.lang.model.Facet;

import java.util.Collections;
import java.util.List;

public class TaraSignatureImpl extends SignatureMixin implements TaraSignature {

	public TaraSignatureImpl(ASTNode node) {
		super(node);
	}

	public void accept(@NotNull TaraVisitor visitor) {
		visitor.visitSignature(this);
	}

	public void accept(@NotNull PsiElementVisitor visitor) {
		if (visitor instanceof TaraVisitor) accept((TaraVisitor) visitor);
		else super.accept(visitor);
	}

	@Override
	@Nullable
	public TaraAnchor getAnchor() {
		return findChildByClass(TaraAnchor.class);
	}

	@Override
	@Nullable
	public TaraFacetTarget getFacetTarget() {
		return findChildByClass(TaraFacetTarget.class);
	}

	@NotNull
	@Override
	public List<? extends Facet> getFacetApplyList() {
		if (getFacets() == null) return Collections.emptyList();
		return this.getFacets().getFacetApplyList();
	}

	@Override
	@Nullable
	public TaraFacets getFacets() {
		return findChildByClass(TaraFacets.class);
	}

	@Override
	@Nullable
	public TaraIdentifier getIdentifier() {
		return findChildByClass(TaraIdentifier.class);
	}

	@Override
	@Nullable
	public TaraIdentifierReference getIdentifierReference() {
		return findChildByClass(TaraIdentifierReference.class);
	}

	@Override
	@Nullable
	public TaraMetaIdentifier getMetaIdentifier() {
		return findChildByClass(TaraMetaIdentifier.class);
	}

	@Override
	@Nullable
	public TaraParameters getParameters() {
		return findChildByClass(TaraParameters.class);
	}

	@Override
	@NotNull
	public List<TaraRuleContainer> getRuleContainerList() {
		return PsiTreeUtil.getChildrenOfTypeAsList(this, TaraRuleContainer.class);
	}

	@Override
	@Nullable
	public TaraTags getTags() {
		return findChildByClass(TaraTags.class);
	}

	@Override
	@Nullable
	public TaraWithTable getWithTable() {
		return findChildByClass(TaraWithTable.class);
	}

}
