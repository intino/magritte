// This is a generated file. Not intended for manual editing.
package monet.tara.transpiler.intellij.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import monet.tara.transpiler.intellij.psi.*;

public class TaraDoubleListAssignImpl extends ASTWrapperPsiElement implements TaraDoubleListAssign {

  public TaraDoubleListAssignImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitDoubleListAssign(this);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<TaraIntegerValue> getIntegerValueList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, TaraIntegerValue.class);
  }

}
