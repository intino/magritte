// This is a generated file. Not intended for manual editing.
package tara.intellij.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.psi.*;

import java.util.List;

public class TaraBodyImpl extends BodyMixin implements TaraBody {

  public TaraBodyImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull TaraVisitor visitor) {
    visitor.visitBody(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) accept((TaraVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<TaraNode> getNodeList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, TaraNode.class);
  }

  @Override
  @NotNull
  public List<TaraNodeReference> getNodeReferenceList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, TaraNodeReference.class);
  }

  @Override
  @NotNull
  public List<TaraVarInit> getVarInitList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, TaraVarInit.class);
  }

  @Override
  @NotNull
  public List<TaraVariable> getVariableList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, TaraVariable.class);
  }

}
