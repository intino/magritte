// This is a generated file. Not intended for manual editing.
package siani.tara.intellij.lang.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import siani.tara.intellij.lang.psi.*;

public class TaraIdentifierImpl extends IdentifierMixin implements TaraIdentifier {

  public TaraIdentifierImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitIdentifier(this);
    else super.accept(visitor);
  }

}
