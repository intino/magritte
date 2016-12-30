// This is a generated file. Not intended for manual editing.
package io.intino.tara.plugin.lang.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static io.intino.tara.plugin.lang.psi.TaraTypes.*;
import io.intino.tara.plugin.lang.psi.*;

public class TaraVarInitImpl extends VarInitMixin implements TaraVarInit {

  public TaraVarInitImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull TaraVisitor visitor) {
    visitor.visitVarInit(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) accept((TaraVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public TaraBodyValue getBodyValue() {
    return findChildByClass(TaraBodyValue.class);
  }

  @Override
  @NotNull
  public TaraIdentifier getIdentifier() {
    return findNotNullChildByClass(TaraIdentifier.class);
  }

  @Override
  @Nullable
  public TaraValue getValue() {
    return findChildByClass(TaraValue.class);
  }

}