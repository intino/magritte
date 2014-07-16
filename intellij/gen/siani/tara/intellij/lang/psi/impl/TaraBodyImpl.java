// This is a generated file. Not intended for manual editing.
package siani.tara.intellij.lang.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static siani.tara.intellij.lang.psi.TaraTypes.*;
import siani.tara.intellij.lang.psi.*;

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
  public List<TaraAnnotationsAndFacets> getAnnotationsAndFacetsList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, TaraAnnotationsAndFacets.class);
  }

  @Override
  @NotNull
  public List<TaraAttribute> getAttributeList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, TaraAttribute.class);
  }

  @Override
  @NotNull
  public List<TaraConcept> getConceptList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, TaraConcept.class);
  }

  @Override
  @NotNull
  public List<TaraFacetTarget> getFacetTargetList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, TaraFacetTarget.class);
  }

  @Override
  @NotNull
  public List<TaraVarInit> getVarInitList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, TaraVarInit.class);
  }

}
