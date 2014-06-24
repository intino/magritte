// This is a generated file. Not intended for manual editing.
package siani.tara.intellij.lang.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static siani.tara.intellij.lang.psi.TaraTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import siani.tara.intellij.lang.psi.*;

public class TaraHeaderImpl extends ASTWrapperPsiElement implements TaraHeader {

  public TaraHeaderImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitHeader(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public TaraBox getBox() {
    return findChildByClass(TaraBox.class);
  }

  @Override
  @NotNull
  public List<TaraImportStatement> getImportStatementList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, TaraImportStatement.class);
  }

}
