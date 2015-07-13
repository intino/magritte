// This is a generated file. Not intended for manual editing.
package tara.intellij.lang.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import tara.intellij.lang.psi.TaraBooleanValue;
import tara.intellij.lang.psi.TaraVisitor;

public class TaraBooleanValueImpl extends ASTWrapperPsiElement implements TaraBooleanValue {

  public TaraBooleanValueImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitBooleanValue(this);
    else super.accept(visitor);
  }

}
