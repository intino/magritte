// This is a generated file. Not intended for manual editing.
package tara.intellij.lang.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import tara.intellij.lang.psi.TaraAnnotation;
import tara.intellij.lang.psi.TaraVisitor;

public class TaraAnnotationImpl extends AnnotationMixin implements TaraAnnotation {

  public TaraAnnotationImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitAnnotation(this);
    else super.accept(visitor);
  }

}
