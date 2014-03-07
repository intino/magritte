// This is a generated file. Not intended for manual editing.
package monet.::projectName::.intellij.metamodel.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static monet.::projectName::.intellij.metamodel.psi.::projectProperName::Types.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import monet.::projectName::.intellij.metamodel.psi.*;

public class ::projectProperName::DefinitionInjectionImpl extends ASTWrapperPsiElement implements ::projectProperName::DefinitionInjection {

  public ::projectProperName::DefinitionInjectionImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ::projectProperName::Visitor) ((::projectProperName::Visitor)visitor).visitDefinitionInjection(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ::projectProperName::Annotations getAnnotations() {
    return findChildByClass(::projectProperName::Annotations.class);
  }

  @Override
  @NotNull
  public ::projectProperName::ExtendedDefinition getExtendedDefinition() {
    return findNotNullChildByClass(::projectProperName::ExtendedDefinition.class);
  }

}
