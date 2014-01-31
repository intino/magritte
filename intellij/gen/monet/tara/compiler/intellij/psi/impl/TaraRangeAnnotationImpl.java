// This is a generated file. Not intended for manual editing.
package monet.tara.compiler.intellij.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import monet.tara.compiler.intellij.psi.*;

public class TaraRangeAnnotationImpl extends ASTWrapperPsiElement implements TaraRangeAnnotation {

  public TaraRangeAnnotationImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitRangeAnnotation(this);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<TaraRange> getRangeList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, TaraRange.class);
  }

}