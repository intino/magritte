// This is a generated file. Not intended for manual editing.
package monet.tara.intellij.lang.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static monet.tara.intellij.lang.psi.TaraTypes.*;
import monet.tara.intellij.lang.psi.*;

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
  public List<TaraReferenceStatement> getReferenceStatementList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, TaraReferenceStatement.class);
  }

  @Override
  @NotNull
  public List<TaraWord> getWordList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, TaraWord.class);
  }

}
