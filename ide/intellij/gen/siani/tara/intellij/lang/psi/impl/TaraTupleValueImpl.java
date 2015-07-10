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

public class TaraTupleValueImpl extends ASTWrapperPsiElement implements TaraTupleValue {

  public TaraTupleValueImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitTupleValue(this);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public TaraDoubleValue getDoubleValue() {
    return findNotNullChildByClass(TaraDoubleValue.class);
  }

  @Override
  @NotNull
  public TaraStringValue getStringValue() {
    return findNotNullChildByClass(TaraStringValue.class);
  }

}
