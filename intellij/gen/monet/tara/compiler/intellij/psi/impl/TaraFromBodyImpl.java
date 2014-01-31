// This is a generated file. Not intended for manual editing.
package monet.tara.compiler.intellij.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import monet.tara.compiler.intellij.psi.*;

public class TaraFromBodyImpl extends ASTWrapperPsiElement implements TaraFromBody {

  public TaraFromBodyImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitFromBody(this);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<TaraChild> getChildList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, TaraChild.class);
  }

}
