// This is a generated file. Not intended for manual editing.
package monet.tara.intellij.metamodel.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import monet.tara.intellij.metamodel.psi.TaraIdentifierReference;
import monet.tara.intellij.metamodel.psi.TaraModifier;
import monet.tara.intellij.metamodel.psi.TaraSignature;
import monet.tara.intellij.metamodel.psi.TaraVisitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TaraSignatureImpl extends SignatureMixin implements TaraSignature {

  public TaraSignatureImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TaraVisitor) ((TaraVisitor)visitor).visitSignature(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public TaraIdentifierReference getIdentifierReference() {
    return findChildByClass(TaraIdentifierReference.class);
  }

  @Override
  @Nullable
  public TaraModifier getModifier() {
    return findChildByClass(TaraModifier.class);
  }

}
