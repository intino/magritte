// This is a generated file. Not intended for manual editing.
package io.intino.tara.plugin.lang.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static io.intino.tara.plugin.lang.psi.TaraTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import io.intino.tara.plugin.lang.psi.*;

public class TaraFacetsImpl extends ASTWrapperPsiElement implements TaraFacets {

  public TaraFacetsImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull TaraVisitor visitor) {
    visitor.visitFacets(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) accept((TaraVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<TaraFacetApply> getFacetApplyList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, TaraFacetApply.class);
  }

}
