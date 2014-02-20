// This is a generated file. Not intended for manual editing.
package monet.tara.compiler.intellij.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static monet.tara.compiler.intellij.psi.TaraTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import monet.tara.compiler.intellij.psi.*;

public class TaraAttributeImpl extends ASTWrapperPsiElement implements TaraAttribute {

  public TaraAttributeImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitAttribute(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public TaraBooleanAssign getBooleanAssign() {
    return findChildByClass(TaraBooleanAssign.class);
  }

  @Override
  @Nullable
  public TaraBooleanListAssign getBooleanListAssign() {
    return findChildByClass(TaraBooleanListAssign.class);
  }

  @Override
  @Nullable
  public TaraDoubleAssign getDoubleAssign() {
    return findChildByClass(TaraDoubleAssign.class);
  }

  @Override
  @Nullable
  public TaraDoubleListAssign getDoubleListAssign() {
    return findChildByClass(TaraDoubleListAssign.class);
  }

  @Override
  @Nullable
  public TaraIntegerAssign getIntegerAssign() {
    return findChildByClass(TaraIntegerAssign.class);
  }

  @Override
  @Nullable
  public TaraIntegerListAssign getIntegerListAssign() {
    return findChildByClass(TaraIntegerListAssign.class);
  }

  @Override
  @Nullable
  public TaraNaturalAssign getNaturalAssign() {
    return findChildByClass(TaraNaturalAssign.class);
  }

  @Override
  @Nullable
  public TaraNaturalListAssign getNaturalListAssign() {
    return findChildByClass(TaraNaturalListAssign.class);
  }

  @Override
  @Nullable
  public TaraStringAssign getStringAssign() {
    return findChildByClass(TaraStringAssign.class);
  }

  @Override
  @Nullable
  public TaraStringListAssign getStringListAssign() {
    return findChildByClass(TaraStringListAssign.class);
  }

}
