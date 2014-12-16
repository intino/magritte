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

public class TaraNaturalAttributeImpl extends ASTWrapperPsiElement implements TaraNaturalAttribute {

  public TaraNaturalAttributeImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitNaturalAttribute(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public TaraDoubleMeasure getDoubleMeasure() {
    return findChildByClass(TaraDoubleMeasure.class);
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

}
