// This is a generated file. Not intended for manual editing.
package monet.tara.compiler.intellij.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import monet.tara.compiler.intellij.psi.*;

public class TaraDocImpl extends ASTWrapperPsiElement implements TaraDoc {

  public TaraDocImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitDoc(this);
    else super.accept(visitor);
  }

}