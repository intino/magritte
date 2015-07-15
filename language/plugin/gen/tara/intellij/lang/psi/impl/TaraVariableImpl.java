// This is a generated file. Not intended for manual editing.
package tara.intellij.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.intellij.lang.psi.*;

import java.util.List;

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
  @NotNull
  public List<TaraDoc> getDocList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, TaraDoc.class);
  }

  @Override
  @Nullable
  public TaraFlags getFlagsNode() {
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
