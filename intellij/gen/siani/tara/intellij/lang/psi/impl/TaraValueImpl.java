// This is a generated file. Not intended for manual editing.
package siani.tara.intellij.lang.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static siani.tara.intellij.lang.psi.TaraTypes.*;
import siani.tara.intellij.lang.psi.*;

public class TaraValueImpl extends ValueMixin implements TaraValue {

  public TaraValueImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitValue(this);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<TaraBooleanValue> getBooleanValueList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, TaraBooleanValue.class);
  }

  @Override
  @NotNull
  public List<TaraDoubleValue> getDoubleValueList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, TaraDoubleValue.class);
  }

  @Override
  @Nullable
  public TaraEmptyField getEmptyField() {
    return findChildByClass(TaraEmptyField.class);
  }

  @Override
  @NotNull
  public List<TaraExpression> getExpressionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, TaraExpression.class);
  }

  @Override
  @NotNull
  public List<TaraIdentifierReference> getIdentifierReferenceList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, TaraIdentifierReference.class);
  }

  @Override
  @NotNull
  public List<TaraIntegerValue> getIntegerValueList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, TaraIntegerValue.class);
  }

  @Override
  @NotNull
  public List<TaraLinkValue> getLinkValueList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, TaraLinkValue.class);
  }

  @Override
  @Nullable
  public TaraMeasureValue getMeasureValue() {
    return findChildByClass(TaraMeasureValue.class);
  }

  @Override
  @NotNull
  public List<TaraNaturalValue> getNaturalValueList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, TaraNaturalValue.class);
  }

  @Override
  @NotNull
  public List<TaraStringValue> getStringValueList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, TaraStringValue.class);
  }

}
