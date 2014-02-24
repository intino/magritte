// This is a generated file. Not intended for manual editing.
package monet.tara.intellij.metamodel.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static monet.tara.intellij.metamodel.psi.TaraTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import monet.tara.intellij.metamodel.psi.*;

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
  public List<TaraFromComponent> getFromComponentList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, TaraFromComponent.class);
  }

}
