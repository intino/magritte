// This is a generated file. Not intended for manual editing.
package siani.tara.intellij.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.psi.TaraAnImport;
import siani.tara.intellij.lang.psi.TaraHeaderReference;
import siani.tara.intellij.lang.psi.TaraVisitor;

public class TaraAnImportImpl extends ImportMixin implements TaraAnImport {

  public TaraAnImportImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitAnImport(this);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public TaraHeaderReference getHeaderReference() {
    return findNotNullChildByClass(TaraHeaderReference.class);
  }

}
