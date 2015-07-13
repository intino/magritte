// This is a generated file. Not intended for manual editing.
package tara.intellij.lang.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import tara.intellij.lang.psi.TaraAttributeType;
import tara.intellij.lang.psi.TaraContract;
import tara.intellij.lang.psi.TaraVisitor;

public class TaraAttributeTypeImpl extends ASTWrapperPsiElement implements TaraAttributeType {

  public TaraAttributeTypeImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitAttributeType(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public TaraContract getContract() {
    return findChildByClass(TaraContract.class);
  }

}
