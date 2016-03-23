// This is a generated file. Not intended for manual editing.
package tara.intellij.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.psi.TaraParameter;
import tara.intellij.lang.psi.TaraParameters;
import tara.intellij.lang.psi.TaraVisitor;

import java.util.List;

public class TaraParametersImpl extends ParametersMixin implements TaraParameters {

  public TaraParametersImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull TaraVisitor visitor) {
    visitor.visitParameters(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) accept((TaraVisitor) visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<TaraParameter> getParameterList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, TaraParameter.class);
  }

}
