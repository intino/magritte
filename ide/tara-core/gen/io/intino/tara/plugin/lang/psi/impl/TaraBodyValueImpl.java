// This is a generated file. Not intended for manual editing.
package io.intino.tara.plugin.lang.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static io.intino.tara.plugin.lang.psi.TaraTypes.*;
import io.intino.tara.plugin.lang.psi.*;

public class TaraBodyValueImpl extends ValueMixin implements TaraBodyValue {

  public TaraBodyValueImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull TaraVisitor visitor) {
    visitor.visitBodyValue(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) accept((TaraVisitor)visitor);
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
