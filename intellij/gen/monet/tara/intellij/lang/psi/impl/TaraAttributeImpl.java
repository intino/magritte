// This is a generated file. Not intended for manual editing.
package monet.tara.intellij.lang.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static monet.tara.intellij.lang.psi.TaraTypes.*;
import monet.tara.intellij.lang.psi.*;

public class TaraAttributeImpl extends AttributeMixin implements TaraAttribute {

  public TaraAttributeImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitAttribute(this);
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
  public TaraDoc getDoc() {
    return findChildByClass(TaraDoc.class);
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
  public TaraReferenceStatement getReferenceStatement() {
    return findChildByClass(TaraReferenceStatement.class);
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

  @Override
  @Nullable
  public TaraWord getWord() {
    return findChildByClass(TaraWord.class);
  }

}
