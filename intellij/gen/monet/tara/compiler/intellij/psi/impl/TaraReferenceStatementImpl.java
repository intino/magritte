// This is a generated file. Not intended for manual editing.
package monet.tara.compiler.intellij.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import monet.tara.compiler.intellij.psi.*;

public class TaraReferenceStatementImpl extends ASTWrapperPsiElement implements TaraReferenceStatement {

  public TaraReferenceStatementImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitReferenceStatement(this);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public TaraIdentifier getIdentifier() {
    return findNotNullChildByClass(TaraIdentifier.class);
  }

}