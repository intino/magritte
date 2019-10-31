// This is a generated file. Not intended for manual editing.
package io.intino.tara.plugin.lang.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static io.intino.tara.plugin.lang.psi.TaraTypes.*;
import io.intino.tara.plugin.lang.psi.*;

public class TaraBodyImpl extends BodyMixin implements TaraBody {

  public TaraBodyImpl(@NotNull ASTNode node) {
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
