// This is a generated file. Not intended for manual editing.
package tara.intellij.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.intellij.lang.psi.TaraBodyValue;
import tara.intellij.lang.psi.TaraExpression;
import tara.intellij.lang.psi.TaraStringValue;
import tara.intellij.lang.psi.TaraVisitor;

public class TaraBodyValueImpl extends ValueMixin implements TaraBodyValue {

  public TaraBodyValueImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull TaraVisitor visitor) {
    visitor.visitBodyValue(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) accept((TaraVisitor) visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public TaraExpression getExpression() {
    return findChildByClass(TaraExpression.class);
  }

  @Override
  @Nullable
  public TaraStringValue getStringValue() {
    return findChildByClass(TaraStringValue.class);
  }

}
