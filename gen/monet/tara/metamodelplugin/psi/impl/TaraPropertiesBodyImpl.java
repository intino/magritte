// This is a generated file. Not intended for manual editing.
package monet.tara.metamodelplugin.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static monet.tara.metamodelplugin.psi.TaraTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import monet.tara.metamodelplugin.psi.*;

public class TaraPropertiesBodyImpl extends ASTWrapperPsiElement implements TaraPropertiesBody {

  public TaraPropertiesBodyImpl(ASTNode node) {
    super(node);
  }

  @Override
  @Nullable
  public TaraPRIMITIVE getPRIMITIVE() {
    return findChildByClass(TaraPRIMITIVE.class);
  }

  @Override
  @NotNull
  public TaraPRIMITIVETYPE getPRIMITIVETYPE() {
    return findNotNullChildByClass(TaraPRIMITIVETYPE.class);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitPropertiesBody(this);
    else super.accept(visitor);
  }

}
