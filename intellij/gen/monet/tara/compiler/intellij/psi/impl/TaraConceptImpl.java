// This is a generated file. Not intended for manual editing.
package monet.tara.compiler.intellij.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import monet.tara.compiler.intellij.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TaraConceptImpl extends TaraConceptMixin implements TaraConcept {

  public TaraConceptImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitConcept(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public TaraConceptAnnotations getConceptAnnotations() {
    return findChildByClass(TaraConceptAnnotations.class);
  }

  @Override
  @Nullable
  public TaraConceptBody getConceptBody() {
    return findChildByClass(TaraConceptBody.class);
  }

  @Override
  @NotNull
  public TaraConceptSignature getConceptSignature() {
    return findNotNullChildByClass(TaraConceptSignature.class);
  }

  @Override
  @Nullable
  public TaraDoc getDoc() {
    return findChildByClass(TaraDoc.class);
  }

  public String getIdentifier() {
    return TaraPsiImplUtil.getIdentifier(this);
  }

}
