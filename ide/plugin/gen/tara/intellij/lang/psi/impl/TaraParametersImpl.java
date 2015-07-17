// This is a generated file. Not intended for manual editing.
package tara.intellij.lang.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import tara.intellij.lang.psi.TaraExplicitParameter;
import tara.intellij.lang.psi.TaraImplicitParameter;
import tara.intellij.lang.psi.TaraParameters;
import tara.intellij.lang.psi.TaraVisitor;

public class TaraParametersImpl extends ParametersMixin implements TaraParameters {

  public TaraParametersImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitParameters(this);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<TaraExplicitParameter> getExplicitParameterList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, TaraExplicitParameter.class);
  }

  @Override
  @NotNull
  public List<TaraImplicitParameter> getImplicitParameterList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, TaraImplicitParameter.class);
  }

}