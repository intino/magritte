// This is a generated file. Not intended for manual editing.
package tara.intellij.lang.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import tara.intellij.lang.psi.TaraEmptyField;
import tara.intellij.lang.psi.TaraVisitor;

public class TaraEmptyFieldImpl extends ASTWrapperPsiElement implements TaraEmptyField {

  public TaraEmptyFieldImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitEmptyField(this);
    else super.accept(visitor);
  }

}
