// This is a generated file. Not intended for manual editing.
package monet.tara.intellij.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static monet.tara.intellij.psi.TaraTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import monet.tara.intellij.psi.*;

public class TaraDoubleAssignImpl extends ASTWrapperPsiElement implements TaraDoubleAssign {

  public TaraDoubleAssignImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitDoubleAssign(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public TaraIntegerValue getIntegerValue() {
    return findChildByClass(TaraIntegerValue.class);
  }

}
