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

public class ::projectProperName::DefinitionImpl extends DefinitionMixin implements ::projectProperName::Definition {

  public ::projectProperName::DefinitionImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ::projectProperName::Visitor) ((::projectProperName::Visitor)visitor).visitDefinition(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ::projectProperName::Annotations getAnnotations() {
    return findChildByClass(::projectProperName::Annotations.class);
  }

  @Override
  @Nullable
  public ::projectProperName::Body getBody() {
    return findChildByClass(::projectProperName::Body.class);
  }

  @Override
  @Nullable
  public ::projectProperName::Doc getDoc() {
    return findChildByClass(::projectProperName::Doc.class);
  }

  @Override
  @NotNull
  public ::projectProperName::Signature getSignature() {
    return findNotNullChildByClass(::projectProperName::Signature.class);
  }

}
