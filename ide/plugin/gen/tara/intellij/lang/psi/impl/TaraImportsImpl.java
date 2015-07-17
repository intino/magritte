// This is a generated file. Not intended for manual editing.
package tara.intellij.lang.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import tara.intellij.lang.psi.TaraAnImport;
import tara.intellij.lang.psi.TaraImports;
import tara.intellij.lang.psi.TaraVisitor;

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
