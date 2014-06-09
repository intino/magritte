// This is a generated file. Not intended for manual editing.
package siani.tara.intellij.lang.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import siani.tara.intellij.lang.psi.*;

public class TaraWordImpl extends WordMixin implements TaraWord {

  public TaraWordImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitWord(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public TaraDoc getDoc() {
    return findChildByClass(TaraDoc.class);
  }

}
