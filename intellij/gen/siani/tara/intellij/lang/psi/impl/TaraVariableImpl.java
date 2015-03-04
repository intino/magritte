// This is a generated file. Not intended for manual editing.
package siani.tara.intellij.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.lang.psi.TaraAnnotations;
import siani.tara.intellij.lang.psi.TaraVariable;
import siani.tara.intellij.lang.psi.TaraVariableType;
import siani.tara.intellij.lang.psi.TaraVisitor;

public class TaraVariableImpl extends VariableMixin implements TaraVariable {

  public TaraVariableImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitVariable(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public TaraAnnotations getAnnotations() {
    return findChildByClass(TaraAnnotations.class);
  }

  @Override
  @NotNull
  public TaraVariableType getVariableType() {
    return findNotNullChildByClass(TaraVariableType.class);
  }

}
