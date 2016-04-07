// This is a generated file. Not intended for manual editing.
package tara.intellij.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.intellij.lang.psi.*;

import java.util.List;

public class TaraRuleImpl extends RuleMixin implements TaraRule {

  public TaraRuleImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull TaraVisitor visitor) {
    visitor.visitRule(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) accept((TaraVisitor) visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public TaraClassTypeValue getClassTypeValue() {
    return findChildByClass(TaraClassTypeValue.class);
  }

  @Override
  @NotNull
  public List<TaraIdentifier> getIdentifierList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, TaraIdentifier.class);
  }

  @Override
  @Nullable
  public TaraIdentifierReference getIdentifierReference() {
    return findChildByClass(TaraIdentifierReference.class);
  }

  @Override
  @Nullable
  public TaraMetric getMetric() {
    return findChildByClass(TaraMetric.class);
  }

  @Override
  @Nullable
  public TaraRange getRange() {
    return findChildByClass(TaraRange.class);
  }

  @Override
  @Nullable
  public TaraStringValue getStringValue() {
    return findChildByClass(TaraStringValue.class);
  }

}
