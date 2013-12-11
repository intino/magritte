// This is a generated file. Not intended for manual editing.
package monet.tara.metamodel.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static monet.tara.metamodel.psi.TaraTypes.*;
import monet.tara.metamodel.psi.*;

public class TaraConceptDefinitionImpl extends TaraNamedElementImpl implements TaraConceptDefinition {

  public TaraConceptDefinitionImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitConceptDefinition(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public TaraConceptBody getConceptBody() {
    return findChildByClass(TaraConceptBody.class);
  }

  @Override
  @NotNull
  public TaraConceptSignature getConceptSignature() {
    return findNotNullChildByClass(TaraConceptSignature.class);
  }

  public String getIdentifier() {
    return TaraPsiImplUtil.getIdentifier(this);
  }

  public String getName() {
    return TaraPsiImplUtil.getName(this);
  }

  public PsiElement setName(String newName) {
    return TaraPsiImplUtil.setName(this, newName);
  }

  public PsiElement getNameIdentifier() {
    return TaraPsiImplUtil.getNameIdentifier(this);
  }

}
