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
  @Nullable
  public TaraBased getBased() {
    return findChildByClass(TaraBased.class);
  }

  @Override
  @Nullable
  public TaraCased getCased() {
    return findChildByClass(TaraCased.class);
  }

  @Override
  @Nullable
  public TaraIdentifier getIdentifier() {
    return findChildByClass(TaraIdentifier.class);
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
