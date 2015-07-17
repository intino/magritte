// This is a generated file. Not intended for manual editing.
package tara.intellij.lang.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import tara.intellij.lang.psi.TaraImplicitParameter;
import tara.intellij.lang.psi.TaraValue;
import tara.intellij.lang.psi.TaraVisitor;

public class TaraImplicitParameterImpl extends ParameterMixin implements TaraImplicitParameter {

  public TaraImplicitParameterImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitImplicitParameter(this);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public TaraValue getValue() {
    return findNotNullChildByClass(TaraValue.class);
  }

}
