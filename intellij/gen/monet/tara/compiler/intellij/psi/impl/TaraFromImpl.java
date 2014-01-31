// This is a generated file. Not intended for manual editing.
package monet.tara.compiler.intellij.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import monet.tara.compiler.intellij.psi.*;

public class TaraFromImpl extends ASTWrapperPsiElement implements TaraFrom {

  public TaraFromImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitFrom(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public TaraFromBody getFromBody() {
    return findChildByClass(TaraFromBody.class);
  }

  @Override
  @Nullable
  public TaraRangeAnnotation getRangeAnnotation() {
    return findChildByClass(TaraRangeAnnotation.class);
  }

}
