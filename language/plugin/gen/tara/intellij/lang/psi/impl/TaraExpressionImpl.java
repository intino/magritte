// This is a generated file. Not intended for manual editing.
package tara.intellij.lang.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import tara.intellij.lang.psi.TaraExpression;
import tara.intellij.lang.psi.TaraVisitor;

public class TaraExpressionImpl extends ExpressionMixin implements TaraExpression {

  public TaraExpressionImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitExpression(this);
    else super.accept(visitor);
  }

}
