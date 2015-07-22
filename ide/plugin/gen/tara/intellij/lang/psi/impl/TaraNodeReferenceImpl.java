// This is a generated file. Not intended for manual editing.
package tara.intellij.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.intellij.lang.psi.TaraIdentifierReference;
import tara.intellij.lang.psi.TaraNodeReference;
import tara.intellij.lang.psi.TaraTags;
import tara.intellij.lang.psi.TaraVisitor;

public class TaraNodeReferenceImpl extends NodeReferenceMixin implements TaraNodeReference {

  public TaraNodeReferenceImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitNodeReference(this);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public TaraIdentifierReference getIdentifierReference() {
    return findNotNullChildByClass(TaraIdentifierReference.class);
  }

  @Override
  @Nullable
  public TaraTags getTags() {
    return findChildByClass(TaraTags.class);
  }

}
