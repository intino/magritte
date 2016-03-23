// This is a generated file. Not intended for manual editing.
package tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.psi.TaraAnImport;
import tara.intellij.lang.psi.TaraImports;
import tara.intellij.lang.psi.TaraVisitor;

import java.util.List;

public class TaraImportsImpl extends ASTWrapperPsiElement implements TaraImports {

  public TaraImportsImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull TaraVisitor visitor) {
    visitor.visitImports(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) accept((TaraVisitor) visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<TaraAnImport> getAnImportList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, TaraAnImport.class);
  }

}
