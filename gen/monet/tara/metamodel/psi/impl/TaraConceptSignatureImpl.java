// This is a generated file. Not intended for manual editing.
package monet.tara.metamodel.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static monet.tara.metamodel.psi.TaraTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import monet.tara.metamodel.psi.*;

public class TaraConceptSignatureImpl extends ASTWrapperPsiElement implements TaraConceptSignature {

  public TaraConceptSignatureImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitConceptSignature(this);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<TaraAnnotation> getAnnotationList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, TaraAnnotation.class);
  }

}
