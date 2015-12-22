// This is a generated file. Not intended for manual editing.
package tara.intellij.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.intellij.lang.psi.TaraConstraint;
import tara.intellij.lang.psi.TaraFacetTarget;
import tara.intellij.lang.psi.TaraIdentifierReference;
import tara.intellij.lang.psi.TaraVisitor;

public class TaraFacetTargetImpl extends FacetTargetMixin implements TaraFacetTarget {

  public TaraFacetTargetImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitFacetTarget(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public TaraConstraint getConstraint() {
    return findChildByClass(TaraConstraint.class);
  }

  @Override
  @Nullable
  public TaraIdentifierReference getIdentifierReference() {
    return findChildByClass(TaraIdentifierReference.class);
  }

}
