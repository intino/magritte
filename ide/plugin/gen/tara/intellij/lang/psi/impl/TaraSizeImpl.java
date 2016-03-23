// This is a generated file. Not intended for manual editing.
package tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.intellij.lang.psi.TaraListRange;
import tara.intellij.lang.psi.TaraSize;
import tara.intellij.lang.psi.TaraVisitor;

public class TaraSizeImpl extends ASTWrapperPsiElement implements TaraSize {

  public TaraSizeImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull TaraVisitor visitor) {
    visitor.visitSize(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) accept((TaraVisitor) visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public TaraListRange getListRange() {
    return findChildByClass(TaraListRange.class);
  }

}
