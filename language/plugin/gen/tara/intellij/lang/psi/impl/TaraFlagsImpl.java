// This is a generated file. Not intended for manual editing.
package tara.intellij.lang.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import tara.intellij.lang.psi.TaraFlag;
import tara.intellij.lang.psi.TaraFlags;
import tara.intellij.lang.psi.TaraVisitor;

public class TaraFlagsImpl extends FlagsMixin implements TaraFlags {

  public TaraFlagsImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitFlags(this);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<TaraFlag> getFlagList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, TaraFlag.class);
  }

}
