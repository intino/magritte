// This is a generated file. Not intended for manual editing.
package siani.tara.intellij.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.psi.TaraStringValue;
import siani.tara.intellij.lang.psi.TaraVisitor;

public class TaraStringValueImpl extends StringMixin implements TaraStringValue {

  public TaraStringValueImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitStringValue(this);
    else super.accept(visitor);
  }

}
