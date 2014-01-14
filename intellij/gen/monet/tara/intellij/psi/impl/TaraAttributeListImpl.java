// This is a generated file. Not intended for manual editing.
package monet.tara.intellij.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static monet.tara.intellij.psi.TaraTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import monet.tara.intellij.psi.*;

public class TaraAttributeListImpl extends ASTWrapperPsiElement implements TaraAttributeList {

  public TaraAttributeListImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitAttributeList(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public TaraBooleanListAssign getBooleanListAssign() {
    return findChildByClass(TaraBooleanListAssign.class);
  }

  @Override
  @Nullable
  public TaraDoubleListAssign getDoubleListAssign() {
    return findChildByClass(TaraDoubleListAssign.class);
  }

  @Override
  @Nullable
  public TaraIntegerListAssign getIntegerListAssign() {
    return findChildByClass(TaraIntegerListAssign.class);
  }

  @Override
  @Nullable
  public TaraNaturalListAssign getNaturalListAssign() {
    return findChildByClass(TaraNaturalListAssign.class);
  }

  @Override
  @Nullable
  public TaraStringListAssign getStringListAssign() {
    return findChildByClass(TaraStringListAssign.class);
  }

}
