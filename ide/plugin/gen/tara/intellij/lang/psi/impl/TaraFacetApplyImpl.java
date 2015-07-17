// This is a generated file. Not intended for manual editing.
package tara.intellij.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.intellij.lang.psi.*;

import java.util.List;

public class TaraFacetApplyImpl extends FacetApplyMixin implements TaraFacetApply {

  public TaraFacetApplyImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitFacetApply(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public TaraBody getBody() {
    return findChildByClass(TaraBody.class);
  }

  @Override
  @NotNull
  public List<TaraMetaIdentifier> getMetaIdentifierList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, TaraMetaIdentifier.class);
  }

  @Override
  @Nullable
  public TaraParameters getParameters() {
    return findChildByClass(TaraParameters.class);
  }

}
