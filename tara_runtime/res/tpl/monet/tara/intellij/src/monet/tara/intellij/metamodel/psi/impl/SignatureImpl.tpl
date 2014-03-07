// This is a generated file. Not intended for manual editing.
package monet.::projectName::.intellij.metamodel.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static monet.::projectName::.intellij.metamodel.psi.::projectProperName::Types.*;
import monet.::projectName::.intellij.metamodel.psi.*;

public class ::projectProperName::SignatureImpl extends SignatureMixin implements ::projectProperName::Signature {

  public ::projectProperName::SignatureImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ::projectProperName::Visitor) ((::projectProperName::Visitor)visitor).visitSignature(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ::projectProperName::ExtendedDefinition getExtendedDefinition() {
    return findChildByClass(::projectProperName::ExtendedDefinition.class);
  }

  @Override
  @NotNull
  public ::projectProperName::Identifier getIdentifier() {
    return findNotNullChildByClass(::projectProperName::Identifier.class);
  }

  @Override
  @Nullable
  public ::projectProperName::Modifier getModifier() {
    return findChildByClass(::projectProperName::Modifier.class);
  }

  @Override
  @Nullable
  public ::projectProperName::Morph getMorph() {
    return findChildByClass(::projectProperName::Morph.class);
  }

  @Override
  @Nullable
  public ::projectProperName::Polymorphic getPolymorphic() {
    return findChildByClass(::projectProperName::Polymorphic.class);
  }

}
