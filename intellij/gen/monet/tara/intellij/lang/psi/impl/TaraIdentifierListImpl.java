// This is a generated file. Not intended for manual editing.
package monet.tara.intellij.lang.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static monet.tara.intellij.lang.psi.TaraTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import monet.tara.intellij.lang.psi.*;

public class TaraIdentifierListImpl extends ASTWrapperPsiElement implements TaraIdentifierList {

  public TaraIdentifierListImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitIdentifierList(this);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<TaraIdentifier> getIdentifierList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, TaraIdentifier.class);
  }

}
