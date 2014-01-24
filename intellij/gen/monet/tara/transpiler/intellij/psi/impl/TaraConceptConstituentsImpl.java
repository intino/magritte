// This is a generated file. Not intended for manual editing.
package monet.tara.transpiler.intellij.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import monet.tara.transpiler.intellij.psi.*;

public class TaraConceptConstituentsImpl extends ASTWrapperPsiElement implements TaraConceptConstituents {

  public TaraConceptConstituentsImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitConceptConstituents(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public TaraAttribute getAttribute() {
    return findChildByClass(TaraAttribute.class);
  }

  @Override
  @Nullable
  public TaraAttributeList getAttributeList() {
    return findChildByClass(TaraAttributeList.class);
  }

  @Override
  @Nullable
  public TaraChild getChild() {
    return findChildByClass(TaraChild.class);
  }

  @Override
  @Nullable
  public TaraFrom getFrom() {
    return findChildByClass(TaraFrom.class);
  }

  @Override
  @Nullable
  public TaraReferenceStatement getReferenceStatement() {
    return findChildByClass(TaraReferenceStatement.class);
  }

  @Override
  @Nullable
  public TaraReferenceStatementList getReferenceStatementList() {
    return findChildByClass(TaraReferenceStatementList.class);
  }

}
