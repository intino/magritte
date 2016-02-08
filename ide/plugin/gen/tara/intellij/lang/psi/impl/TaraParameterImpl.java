// This is a generated file. Not intended for manual editing.
package tara.intellij.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.intellij.lang.psi.TaraIdentifier;
import tara.intellij.lang.psi.TaraParameter;
import tara.intellij.lang.psi.TaraValue;
import tara.intellij.lang.psi.TaraVisitor;

public class TaraParameterImpl extends ParameterMixin implements TaraParameter {

  public TaraParameterImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitParameter(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public TaraIdentifier getIdentifier() {
    return findChildByClass(TaraIdentifier.class);
  }

  @Override
  @NotNull
  public TaraValue getValue() {
    return findNotNullChildByClass(TaraValue.class);
  }

}
