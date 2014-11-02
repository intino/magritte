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
  public TaraAnnotationsAndFacets getAnnotationsAndFacets() {
    return findChildByClass(TaraAnnotationsAndFacets.class);
  }

  @Override
  @Nullable
  public TaraAttributeType getAttributeType() {
    return findChildByClass(TaraAttributeType.class);
  }

  @Override
  @NotNull
  public List<TaraBooleanValue> getBooleanValueList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, TaraBooleanValue.class);
  }

  @Override
  @NotNull
  public List<TaraCoordinateValue> getCoordinateValueList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, TaraCoordinateValue.class);
  }

  @Override
  @NotNull
  public List<TaraDateValue> getDateValueList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, TaraDateValue.class);
  }

  @Override
  @Nullable
  public TaraDoc getDoc() {
    return findChildByClass(TaraDoc.class);
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
  @Nullable
  public TaraIdentifierReference getIdentifierReference() {
    return findChildByClass(TaraIdentifierReference.class);
  }

  @Override
  @NotNull
  public List<TaraIntegerValue> getIntegerValueList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, TaraIntegerValue.class);
  }

  @Override
  @Nullable
  public TaraMeasure getMeasure() {
    return findChildByClass(TaraMeasure.class);
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

  @Override
  @Nullable
  public TaraWord getWord() {
    return findChildByClass(TaraWord.class);
  }

}
