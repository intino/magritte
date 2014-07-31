// This is a generated file. Not intended for manual editing.
package siani.tara.intellij.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.lang.psi.*;

public class TaraConceptImpl extends ConceptMixin implements TaraConcept {

  public TaraConceptImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitConcept(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public TaraAnnotationsAndFacets getAnnotationsAndFacets() {
    return findChildByClass(TaraAnnotationsAndFacets.class);
  }

  @Override
  @Nullable
  public TaraBody getBody() {
    return findChildByClass(TaraBody.class);
  }

  @Override
  @Nullable
  public TaraDoc getDoc() {
    return findChildByClass(TaraDoc.class);
  }

  @Override
  @NotNull
  public TaraSignature getSignature() {
    return findNotNullChildByClass(TaraSignature.class);
  }

}
