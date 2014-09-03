// This is a generated file. Not intended for manual editing.
package siani.tara.intellij.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.psi.TaraAnnotations;
import siani.tara.intellij.lang.psi.TaraVisitor;

public class TaraAnnotationsImpl extends AnnotationsMixin implements TaraAnnotations {

  public TaraAnnotationsImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitAnnotations(this);
    else super.accept(visitor);
  }

}
