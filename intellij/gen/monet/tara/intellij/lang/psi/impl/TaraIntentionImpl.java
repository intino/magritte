// This is a generated file. Not intended for manual editing.
package monet.tara.intellij.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import monet.tara.intellij.lang.psi.TaraIntention;
import monet.tara.intellij.lang.psi.TaraVisitor;
import org.jetbrains.annotations.NotNull;

public class TaraIntentionImpl extends ExternalReferenceMixin implements TaraIntention {

  public TaraIntentionImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitIntention(this);
    else super.accept(visitor);
  }

}
