// This is a generated file. Not intended for manual editing.
package monet.tara.compiler.intellij.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static monet.tara.compiler.intellij.psi.TaraTypes.*;
import monet.tara.compiler.intellij.psi.*;

public class TaraExtendedConceptImpl extends TaraExtendedConceptMixin implements TaraExtendedConcept {

  public TaraExtendedConceptImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitExtendedConcept(this);
    else super.accept(visitor);
  }

  public String getIdentifier() {
    return TaraPsiImplUtil.getIdentifier(this);
  }

}
