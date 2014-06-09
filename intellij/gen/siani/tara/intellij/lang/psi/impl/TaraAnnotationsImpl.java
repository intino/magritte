// This is a generated file. Not intended for manual editing.
package siani.tara.intellij.lang.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import siani.tara.intellij.lang.psi.*;

public class TaraAnnotationsImpl extends AnnotationsMixin implements TaraAnnotations {

  public TaraAnnotationsImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitAnnotations(this);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<TaraIntention> getIntentionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, TaraIntention.class);
  }

}
