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

public class TaraBodyImpl extends BodyMixin implements TaraBody {

  public TaraBodyImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitBody(this);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<TaraDoc> getDocList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, TaraDoc.class);
  }

  @Override
  @NotNull
  public List<TaraFacetApply> getFacetApplyList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, TaraFacetApply.class);
  }

  @Override
  @NotNull
  public List<TaraFacetTarget> getFacetTargetList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, TaraFacetTarget.class);
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
