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
import monet.tara.intellij.psi.impl.TaraPsiImplUtil;

public class TaraFromImpl extends ASTWrapperPsiElement implements TaraFrom {

  public TaraFromImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitFrom(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public TaraFromAnnotations getFromAnnotations() {
    return findChildByClass(TaraFromAnnotations.class);
  }

  @Override
  @NotNull
  public TaraFromBody getFromBody() {
    return findNotNullChildByClass(TaraFromBody.class);
  }

}
