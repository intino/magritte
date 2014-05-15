// This is a generated file. Not intended for manual editing.
package monet.tara.intellij.lang.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import monet.tara.intellij.lang.psi.*;

public class TaraImportStatementImpl extends ImportMixin implements TaraImportStatement {

  public TaraImportStatementImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitImportStatement(this);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public TaraHeaderReference getHeaderReference() {
    return findNotNullChildByClass(TaraHeaderReference.class);
  }

}
