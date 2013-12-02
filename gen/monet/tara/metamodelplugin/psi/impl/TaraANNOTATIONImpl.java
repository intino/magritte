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

public class TaraANNOTATIONImpl extends ASTWrapperPsiElement implements TaraANNOTATION {

  public TaraANNOTATIONImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitANNOTATION(this);
    else super.accept(visitor);
  }

}
