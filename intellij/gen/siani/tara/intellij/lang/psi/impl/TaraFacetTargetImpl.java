// This is a generated file. Not intended for manual editing.
package siani.tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.lang.psi.TaraBody;
import siani.tara.intellij.lang.psi.TaraFacetTarget;
import siani.tara.intellij.lang.psi.TaraIdentifierReference;
import siani.tara.intellij.lang.psi.TaraVisitor;

public class TaraFacetTargetImpl extends ASTWrapperPsiElement implements TaraFacetTarget {

  public TaraFacetTargetImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitFacetTarget(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public TaraBody getBody() {
    return findChildByClass(TaraBody.class);
  }

  @Override
  @Nullable
  public TaraIdentifierReference getIdentifierReference() {
    return findChildByClass(TaraIdentifierReference.class);
  }

	@Override
	public String toString() {
		return "on " + getIdentifierReference().getText();
	}
}
