// This is a generated file. Not intended for manual editing.
package tara.intellij.lang.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import tara.intellij.lang.psi.TaraMetaIdentifier;
import tara.intellij.lang.psi.TaraVisitor;

public class TaraMetaIdentifierImpl extends MetaIdentifierMixin implements TaraMetaIdentifier {

  public TaraMetaIdentifierImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitMetaIdentifier(this);
    else super.accept(visitor);
  }

}
