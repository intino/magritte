// This is a generated file. Not intended for manual editing.
package tara.intellij.lang.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static tara.intellij.lang.psi.TaraTypes.*;
import tara.intellij.lang.psi.*;

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
  public TaraAddress getAddress() {
    return findChildByClass(TaraAddress.class);
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
  public TaraMetaIdentifier getMetaIdentifier() {
    return findChildByClass(TaraMetaIdentifier.class);
  }

  @Override
  @Nullable
  public TaraParameters getParameters() {
    return findChildByClass(TaraParameters.class);
  }

  @Override
  @Nullable
  public TaraTags getTags() {
    return findChildByClass(TaraTags.class);
  }

}
