// This is a generated file. Not intended for manual editing.
package monet.tara.transpiler.intellij.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import monet.tara.transpiler.intellij.psi.*;

public class TaraIntegerValueImpl extends ASTWrapperPsiElement implements TaraIntegerValue {

  public TaraIntegerValueImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitIntegerValue(this);
    else super.accept(visitor);
  }

}
