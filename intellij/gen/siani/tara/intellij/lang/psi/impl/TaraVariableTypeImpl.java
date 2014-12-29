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
  public TaraBooleanAttribute getBooleanAttribute() {
    return findChildByClass(TaraBooleanAttribute.class);
  }

  @Override
  @Nullable
  public TaraDateAttribute getDateAttribute() {
    return findChildByClass(TaraDateAttribute.class);
  }

  @Override
  @Nullable
  public TaraDoubleAttribute getDoubleAttribute() {
    return findChildByClass(TaraDoubleAttribute.class);
  }

  @Override
  @Nullable
  public TaraIntegerAttribute getIntegerAttribute() {
    return findChildByClass(TaraIntegerAttribute.class);
  }

  @Override
  @Nullable
  public TaraMeasureAttribute getMeasureAttribute() {
    return findChildByClass(TaraMeasureAttribute.class);
  }

  @Override
  @Nullable
  public TaraNaturalAttribute getNaturalAttribute() {
    return findChildByClass(TaraNaturalAttribute.class);
  }

  @Override
  @Nullable
  public TaraRatioAttribute getRatioAttribute() {
    return findChildByClass(TaraRatioAttribute.class);
  }

  @Override
  @Nullable
  public TaraReferenceAttribute getReferenceAttribute() {
    return findChildByClass(TaraReferenceAttribute.class);
  }

  @Override
  @Nullable
  public TaraResource getResource() {
    return findChildByClass(TaraResource.class);
  }

  @Override
  @Nullable
  public TaraStringAttribute getStringAttribute() {
    return findChildByClass(TaraStringAttribute.class);
  }

  @Override
  @Nullable
  public TaraWord getWord() {
    return findChildByClass(TaraWord.class);
  }

}