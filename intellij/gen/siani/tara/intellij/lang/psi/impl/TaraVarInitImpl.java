// This is a generated file. Not intended for manual editing.
package siani.tara.intellij.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.lang.psi.TaraVarInit;
import siani.tara.intellij.lang.psi.TaraVarInitValue;
import siani.tara.intellij.lang.psi.TaraVisitor;

public class TaraVarInitImpl extends VarInitMixin implements TaraVarInit {

  public TaraVarInitImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitVarInit(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public TaraVarInitValue getVarInitValue() {
    return findChildByClass(TaraVarInitValue.class);
  }

}
