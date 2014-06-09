// This is a generated file. Not intended for manual editing.
package siani.tara.intellij.lang.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import siani.tara.intellij.lang.psi.*;

public class TaraBooleanListImpl extends ASTWrapperPsiElement implements TaraBooleanList {

  public TaraBooleanListImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitBooleanList(this);
    else super.accept(visitor);
  }

}
