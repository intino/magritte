// This is a generated file. Not intended for manual editing.
package monet.tara.intellij.lang.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import monet.tara.intellij.lang.psi.*;

public class TaraModifierImpl extends ASTWrapperPsiElement implements TaraModifier {

  public TaraModifierImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitModifier(this);
    else super.accept(visitor);
  }

}
