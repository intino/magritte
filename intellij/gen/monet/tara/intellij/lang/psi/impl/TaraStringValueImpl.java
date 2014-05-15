// This is a generated file. Not intended for manual editing.
package monet.tara.intellij.lang.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import monet.tara.intellij.lang.psi.*;

public class TaraStringValueImpl extends ASTWrapperPsiElement implements TaraStringValue {

  public TaraStringValueImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitStringValue(this);
    else super.accept(visitor);
  }

}
