// This is a generated file. Not intended for manual editing.
package tara.intellij.lang.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import tara.intellij.lang.psi.TaraDslDeclaration;
import tara.intellij.lang.psi.TaraHeaderReference;
import tara.intellij.lang.psi.TaraVisitor;

public class TaraDslDeclarationImpl extends ASTWrapperPsiElement implements TaraDslDeclaration {

  public TaraDslDeclarationImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitDslDeclaration(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public TaraHeaderReference getHeaderReference() {
    return findChildByClass(TaraHeaderReference.class);
  }

}
