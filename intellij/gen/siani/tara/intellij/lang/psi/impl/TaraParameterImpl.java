// This is a generated file. Not intended for manual editing.
package siani.tara.intellij.lang.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import siani.tara.intellij.lang.psi.*;

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
  public TaraBooleanList getBooleanList() {
    return findChildByClass(TaraBooleanList.class);
  }

  @Override
  @Nullable
  public TaraBooleanValue getBooleanValue() {
    return findChildByClass(TaraBooleanValue.class);
  }

  @Override
  @Nullable
  public TaraDoubleList getDoubleList() {
    return findChildByClass(TaraDoubleList.class);
  }

  @Override
  @Nullable
  public TaraDoubleValue getDoubleValue() {
    return findChildByClass(TaraDoubleValue.class);
  }

  @Override
  @Nullable
  public TaraEmpty getEmpty() {
    return findChildByClass(TaraEmpty.class);
  }

  @Override
  @Nullable
  public TaraIdentifierList getIdentifierList() {
    return findChildByClass(TaraIdentifierList.class);
  }

  @Override
  @Nullable
  public TaraIdentifierReference getIdentifierReference() {
    return findChildByClass(TaraIdentifierReference.class);
  }

  @Override
  @Nullable
  public TaraIntegerList getIntegerList() {
    return findChildByClass(TaraIntegerList.class);
  }

  @Override
  @Nullable
  public TaraIntegerValue getIntegerValue() {
    return findChildByClass(TaraIntegerValue.class);
  }

  @Override
  @Nullable
  public TaraNaturalList getNaturalList() {
    return findChildByClass(TaraNaturalList.class);
  }

  @Override
  @Nullable
  public TaraNaturalValue getNaturalValue() {
    return findChildByClass(TaraNaturalValue.class);
  }

  @Override
  @Nullable
  public TaraStringList getStringList() {
    return findChildByClass(TaraStringList.class);
  }

  @Override
  @Nullable
  public TaraStringValue getStringValue() {
    return findChildByClass(TaraStringValue.class);
  }

}
