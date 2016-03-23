// This is a generated file. Not intended for manual editing.
package tara.intellij.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.psi.TaraHeaderReference;
import tara.intellij.lang.psi.TaraIdentifier;
import tara.intellij.lang.psi.TaraVisitor;

import java.util.List;

public class TaraHeaderReferenceImpl extends IdentifierReferenceMixin implements TaraHeaderReference {

  public TaraHeaderReferenceImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull TaraVisitor visitor) {
    visitor.visitHeaderReference(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) accept((TaraVisitor) visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<TaraIdentifier> getIdentifierList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, TaraIdentifier.class);
  }

}
