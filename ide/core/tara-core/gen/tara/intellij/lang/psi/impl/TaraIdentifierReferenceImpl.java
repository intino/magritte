// This is a generated file. Not intended for manual editing.
package tara.intellij.lang.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static tara.intellij.lang.psi.TaraTypes.*;
import tara.intellij.lang.psi.*;

public class TaraIdentifierReferenceImpl extends IdentifierReferenceMixin implements TaraIdentifierReference {

  public TaraIdentifierReferenceImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull TaraVisitor visitor) {
    visitor.visitIdentifierReference(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) accept((TaraVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<TaraIdentifier> getIdentifierList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, TaraIdentifier.class);
  }

}