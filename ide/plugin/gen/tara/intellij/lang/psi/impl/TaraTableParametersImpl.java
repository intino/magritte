// This is a generated file. Not intended for manual editing.
package tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.psi.TaraTableParameters;
import tara.intellij.lang.psi.TaraVisitor;

public class TaraTableParametersImpl extends ASTWrapperPsiElement implements TaraTableParameters {

  public TaraTableParametersImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull TaraVisitor visitor) {
    visitor.visitTableParameters(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) accept((TaraVisitor) visitor);
    else super.accept(visitor);
  }

}
