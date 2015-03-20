// This is a generated file. Not intended for manual editing.
package siani.tara.intellij.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.lang.psi.TaraBody;
import siani.tara.intellij.lang.psi.TaraNode;
import siani.tara.intellij.lang.psi.TaraSignature;
import siani.tara.intellij.lang.psi.TaraVisitor;

public class TaraNodeImpl extends NodeMixin implements TaraNode {

  public TaraNodeImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitNode(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public TaraBody getBody() {
    return findChildByClass(TaraBody.class);
  }

  @Override
  @NotNull
  public TaraSignature getSignature() {
    return findNotNullChildByClass(TaraSignature.class);
  }

}
