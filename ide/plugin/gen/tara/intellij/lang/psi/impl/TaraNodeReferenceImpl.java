// This is a generated file. Not intended for manual editing.
package tara.intellij.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.intellij.lang.psi.*;

import java.util.List;

public class TaraNodeReferenceImpl extends NodeReferenceMixin implements TaraNodeReference {

  public TaraNodeReferenceImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull TaraVisitor visitor) {
    visitor.visitNodeReference(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) accept((TaraVisitor) visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public TaraIdentifierReference getIdentifierReference() {
    return findChildByClass(TaraIdentifierReference.class);
  }

  @Override
  @NotNull
  public List<TaraRuleContainer> getRuleContainerList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, TaraRuleContainer.class);
  }

  @Override
  @Nullable
  public TaraTags getTags() {
    return findChildByClass(TaraTags.class);
  }

}
