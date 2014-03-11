// This is a generated file. Not intended for manual editing.
package monet.tara.intellij.metamodel.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static monet.tara.intellij.metamodel.psi.TaraTypes.*;
import monet.tara.intellij.metamodel.psi.*;

public class TaraSignatureImpl extends SignatureMixin implements TaraSignature {

  public TaraSignatureImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitSignature(this);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public TaraIdentifier getIdentifier() {
    return findNotNullChildByClass(TaraIdentifier.class);
  }

  @Override
  @Nullable
  public TaraModifier getModifier() {
    return findChildByClass(TaraModifier.class);
  }

  @Override
  @Nullable
  public TaraMorph getMorph() {
    return findChildByClass(TaraMorph.class);
  }

  @Override
  @Nullable
  public TaraPolymorphic getPolymorphic() {
    return findChildByClass(TaraPolymorphic.class);
  }

  @Override
  @Nullable
  public TaraReferenceIdentifier getReferenceIdentifier() {
    return findChildByClass(TaraReferenceIdentifier.class);
  }

}
