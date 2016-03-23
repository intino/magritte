// This is a generated file. Not intended for manual editing.
package tara.intellij.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.psi.TaraAnnotation;
import tara.intellij.lang.psi.TaraAnnotations;
import tara.intellij.lang.psi.TaraVisitor;

import java.util.List;

public class TaraAnnotationsImpl extends AnnotationsMixin implements TaraAnnotations {

  public TaraAnnotationsImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull TaraVisitor visitor) {
    visitor.visitAnnotations(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) accept((TaraVisitor) visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<TaraAnnotation> getAnnotationList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, TaraAnnotation.class);
  }

}
