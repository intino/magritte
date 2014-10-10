// This is a generated file. Not intended for manual editing.
package siani.tara.intellij.lang.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static siani.tara.intellij.lang.psi.TaraTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import siani.tara.intellij.lang.psi.*;

public class TaraParameterValueImpl extends ASTWrapperPsiElement implements TaraParameterValue {

  public TaraParameterValueImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitParameterValue(this);
    else super.accept(visitor);
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
  @NotNull
  public List<TaraDoubleValue> getDoubleValueList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, TaraDoubleValue.class);
  }

  @Override
  @Nullable
  public TaraEmpty getEmpty() {
    return findChildByClass(TaraEmpty.class);
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
  @Nullable
  public TaraMeasure getMeasure() {
    return findChildByClass(TaraMeasure.class);
  }

  @Override
  @Nullable
  public TaraMetaWord getMetaWord() {
    return findChildByClass(TaraMetaWord.class);
  }

  @Override
  @NotNull
  public List<TaraNaturalValue> getNaturalValueList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, TaraNaturalValue.class);
  }

  @Override
  @NotNull
  public List<TaraPortValue> getPortValueList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, TaraPortValue.class);
  }

  @Override
  @NotNull
  public List<TaraStringValue> getStringValueList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, TaraStringValue.class);
  }

}
