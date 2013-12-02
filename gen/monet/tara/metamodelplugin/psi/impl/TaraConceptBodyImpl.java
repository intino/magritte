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

public class TaraConceptBodyImpl extends ASTWrapperPsiElement implements TaraConceptBody {

  public TaraConceptBodyImpl(ASTNode node) {
    super(node);
  }

  @Override
  @NotNull
  public List<TaraAttrSiblings> getAttrSiblingsList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, TaraAttrSiblings.class);
  }

  @Override
  @Nullable
  public TaraChildrenBody getChildrenBody() {
    return findChildByClass(TaraChildrenBody.class);
  }

  @Override
  @Nullable
  public TaraPropertiesBody getPropertiesBody() {
    return findChildByClass(TaraPropertiesBody.class);
  }

  @Override
  @Nullable
  public TaraReferencesBody getReferencesBody() {
    return findChildByClass(TaraReferencesBody.class);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitConceptBody(this);
    else super.accept(visitor);
  }

}
