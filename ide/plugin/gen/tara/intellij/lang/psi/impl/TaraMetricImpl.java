// This is a generated file. Not intended for manual editing.
package tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.intellij.lang.psi.TaraIdentifier;
import tara.intellij.lang.psi.TaraMetric;
import tara.intellij.lang.psi.TaraVisitor;

public class TaraMetricImpl extends ASTWrapperPsiElement implements TaraMetric {

  public TaraMetricImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull TaraVisitor visitor) {
    visitor.visitMetric(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) accept((TaraVisitor) visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public TaraIdentifier getIdentifier() {
    return findChildByClass(TaraIdentifier.class);
  }

}
