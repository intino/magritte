// This is a generated file. Not intended for manual editing.
package siani.tara.intellij.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.lang.psi.*;

public class TaraVariableImpl extends VariableMixin implements TaraVariable {

  public TaraVariableImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitVariable(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public TaraAttributeType getAttributeType() {
    return findChildByClass(TaraAttributeType.class);
  }

  @Override
  @Nullable
  public TaraCount getCount() {
    return findChildByClass(TaraCount.class);
  }

  @Override
  @Nullable
  public TaraFlags getFlags() {
    return findChildByClass(TaraFlags.class);
  }

  @Override
  @Nullable
  public TaraIdentifier getIdentifier() {
    return findChildByClass(TaraIdentifier.class);
  }

  @Override
  @Nullable
  public TaraMeasureValue getMeasureValue() {
    return findChildByClass(TaraMeasureValue.class);
  }

  @Override
  @Nullable
  public TaraValue getValue() {
    return findChildByClass(TaraValue.class);
  }

  @Override
  @Nullable
  public TaraVariableType getVariableType() {
    return findChildByClass(TaraVariableType.class);
  }

}
