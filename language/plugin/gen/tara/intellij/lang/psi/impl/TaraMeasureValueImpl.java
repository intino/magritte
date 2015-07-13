// This is a generated file. Not intended for manual editing.
package tara.intellij.lang.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import tara.intellij.lang.psi.TaraIdentifier;
import tara.intellij.lang.psi.TaraMeasureValue;
import tara.intellij.lang.psi.TaraVisitor;

public class TaraMeasureValueImpl extends ASTWrapperPsiElement implements TaraMeasureValue {

  public TaraMeasureValueImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitMeasureValue(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public TaraIdentifier getIdentifier() {
    return findChildByClass(TaraIdentifier.class);
  }

}
