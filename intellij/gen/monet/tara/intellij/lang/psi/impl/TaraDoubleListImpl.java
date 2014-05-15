// This is a generated file. Not intended for manual editing.
package monet.tara.intellij.lang.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import monet.tara.intellij.lang.psi.*;

public class TaraDoubleListImpl extends ASTWrapperPsiElement implements TaraDoubleList {

  public TaraDoubleListImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitDoubleList(this);
    else super.accept(visitor);
  }

}
