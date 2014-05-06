// This is a generated file. Not intended for manual editing.
package monet.tara.intellij.metamodel.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import monet.tara.intellij.metamodel.psi.TaraIdentifier;
import monet.tara.intellij.metamodel.psi.TaraVisitor;
import org.jetbrains.annotations.NotNull;

public class TaraIdentifierImpl extends IdentifierMixin implements TaraIdentifier {

  public TaraIdentifierImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitIdentifier(this);
    else super.accept(visitor);
  }

}
