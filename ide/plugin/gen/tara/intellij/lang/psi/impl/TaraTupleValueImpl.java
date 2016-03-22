// This is a generated file. Not intended for manual editing.
package tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.psi.TaraDoubleValue;
import tara.intellij.lang.psi.TaraStringValue;
import tara.intellij.lang.psi.TaraTupleValue;
import tara.intellij.lang.psi.TaraVisitor;

public class TaraTupleValueImpl extends ASTWrapperPsiElement implements TaraTupleValue {

  public TaraTupleValueImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull TaraVisitor visitor) {
    visitor.visitTupleValue(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) accept((TaraVisitor) visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public TaraDoubleValue getDoubleValue() {
    return findNotNullChildByClass(TaraDoubleValue.class);
  }

  @Override
  @NotNull
  public TaraStringValue getStringValue() {
    return findNotNullChildByClass(TaraStringValue.class);
  }

}
