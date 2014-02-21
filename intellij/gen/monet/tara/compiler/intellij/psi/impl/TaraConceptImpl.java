// This is a generated file. Not intended for manual editing.
package monet.tara.compiler.intellij.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static monet.tara.compiler.intellij.psi.TaraTypes.*;
import monet.tara.intellij.psi.impl.TaraConceptMixin;
import monet.tara.compiler.intellij.psi.*;
import monet.tara.intellij.psi.impl.TaraPsiImplUtil;

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
