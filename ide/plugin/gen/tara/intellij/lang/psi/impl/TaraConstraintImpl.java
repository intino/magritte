// This is a generated file. Not intended for manual editing.
package tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.psi.TaraConstraint;
import tara.intellij.lang.psi.TaraIdentifierReference;
import tara.intellij.lang.psi.TaraVisitor;

import java.util.List;

public class TaraConstraintImpl extends ASTWrapperPsiElement implements TaraConstraint {

  public TaraConstraintImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull TaraVisitor visitor) {
    visitor.visitConstraint(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) accept((TaraVisitor) visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<TaraIdentifierReference> getIdentifierReferenceList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, TaraIdentifierReference.class);
  }

}
