// This is a generated file. Not intended for manual editing.
package tara.intellij.lang.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import tara.intellij.lang.psi.TaraNaturalValue;
import tara.intellij.lang.psi.TaraVisitor;

public class TaraNaturalValueImpl extends ASTWrapperPsiElement implements TaraNaturalValue {

  public TaraNaturalValueImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitNaturalValue(this);
    else super.accept(visitor);
  }

}
