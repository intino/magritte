// This is a generated file. Not intended for manual editing.
package tara.intellij.lang.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import tara.intellij.lang.psi.TaraAnnotations;
import tara.intellij.lang.psi.TaraFlags;
import tara.intellij.lang.psi.TaraTags;
import tara.intellij.lang.psi.TaraVisitor;

public class TaraTagsImpl extends TagsMixin implements TaraTags {

  public TaraTagsImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitTags(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public TaraAnnotations getAnnotations() {
    return findChildByClass(TaraAnnotations.class);
  }

  @Override
  @Nullable
  public TaraFlags getFlags() {
    return findChildByClass(TaraFlags.class);
  }

}