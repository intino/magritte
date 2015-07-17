// This is a generated file. Not intended for manual editing.
package tara.intellij.lang.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import tara.intellij.lang.psi.TaraDoubleValue;
import tara.intellij.lang.psi.TaraVisitor;

public class TaraDoubleValueImpl extends ASTWrapperPsiElement implements TaraDoubleValue {

  public TaraDoubleValueImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitDoubleValue(this);
    else super.accept(visitor);
  }

}
