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
  public TaraReferenceIdentifier getReferenceIdentifier() {
    return findNotNullChildByClass(TaraReferenceIdentifier.class);
  }

  @Override
  @NotNull
  public TaraVariableNames getVariableNames() {
    return findNotNullChildByClass(TaraVariableNames.class);
  }

}
