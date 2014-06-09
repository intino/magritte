// This is a generated file. Not intended for manual editing.
package siani.tara.intellij.lang.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import siani.tara.intellij.lang.psi.*;

public class TaraDocImpl extends DocMixin implements TaraDoc {

  public TaraDocImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitDoc(this);
    else super.accept(visitor);
  }

}
