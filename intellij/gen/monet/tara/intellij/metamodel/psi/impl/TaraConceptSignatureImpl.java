// This is a generated file. Not intended for manual editing.
package monet.tara.intellij.metamodel.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static monet.tara.intellij.metamodel.psi.TaraTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import monet.tara.intellij.metamodel.psi.*;

public class TaraConceptSignatureImpl extends ASTWrapperPsiElement implements TaraConceptSignature {

  public TaraConceptSignatureImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitConceptSignature(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public TaraExtendedConcept getExtendedConcept() {
    return findChildByClass(TaraExtendedConcept.class);
  }

  @Override
  @NotNull
  public TaraIdentifier getIdentifier() {
    return findNotNullChildByClass(TaraIdentifier.class);
  }

  @Override
  @Nullable
  public TaraModifier getModifier() {
    return findChildByClass(TaraModifier.class);
  }

}
