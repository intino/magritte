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

public class TaraImportsImpl extends ASTWrapperPsiElement implements TaraImports {

  public TaraImportsImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitImports(this);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<TaraAnImport> getAnImportList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, TaraAnImport.class);
  }

}