// This is a generated file. Not intended for manual editing.
package monet.tara.intellij.metamodel.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import monet.tara.intellij.metamodel.psi.*;

public class IdentifierImpl extends IdentifierMixin implements TaraIdentifier {

  public IdentifierImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitIdentifier(this);
    else super.accept(visitor);
  }

  public String getIdentifier() {
    return TaraPsiImplUtil.getIdentifier(this);
  }

}
