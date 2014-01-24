// This is a generated file. Not intended for manual editing.
package monet.tara.transpiler.intellij.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import monet.tara.transpiler.intellij.psi.*;

public class TaraReferenceStatementListImpl extends ASTWrapperPsiElement implements TaraReferenceStatementList {

  public TaraReferenceStatementListImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitReferenceStatementList(this);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public TaraIdentifier getIdentifier() {
    return findNotNullChildByClass(TaraIdentifier.class);
  }

}
