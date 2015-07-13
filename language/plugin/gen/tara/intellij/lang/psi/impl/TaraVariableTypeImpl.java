// This is a generated file. Not intended for manual editing.
package tara.intellij.lang.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import tara.intellij.lang.psi.TaraIdentifierReference;
import tara.intellij.lang.psi.TaraVariableType;
import tara.intellij.lang.psi.TaraVisitor;

public class TaraVariableTypeImpl extends ASTWrapperPsiElement implements TaraVariableType {

  public TaraVariableTypeImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitVariableType(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public TaraIdentifierReference getIdentifierReference() {
    return findChildByClass(TaraIdentifierReference.class);
  }

}
