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

public class TaraChildImpl extends ASTWrapperPsiElement implements TaraChild {

  public TaraChildImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitChild(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public TaraChildAnnotation getChildAnnotation() {
    return findChildByClass(TaraChildAnnotation.class);
  }

  @Override
  @Nullable
  public TaraConceptBody getConceptBody() {
    return findChildByClass(TaraConceptBody.class);
  }

  @Override
  @Nullable
  public TaraDoc getDoc() {
    return findChildByClass(TaraDoc.class);
  }

  @Override
  @NotNull
  public List<TaraIdentifier> getIdentifierList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, TaraIdentifier.class);
  }

  @Override
  @Nullable
  public TaraModifier getModifier() {
    return findChildByClass(TaraModifier.class);
  }

}
