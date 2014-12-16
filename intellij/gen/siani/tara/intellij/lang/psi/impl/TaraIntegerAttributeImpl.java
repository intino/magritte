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

public class TaraIntegerAttributeImpl extends ASTWrapperPsiElement implements TaraIntegerAttribute {

  public TaraIntegerAttributeImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitIntegerAttribute(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public TaraDoubleMeasure getDoubleMeasure() {
    return findChildByClass(TaraDoubleMeasure.class);
  }

  @Override
  @NotNull
  public List<TaraIntegerValue> getIntegerValueList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, TaraIntegerValue.class);
  }

  @Override
  @Nullable
  public TaraMeasureValue getMeasureValue() {
    return findChildByClass(TaraMeasureValue.class);
  }

}
