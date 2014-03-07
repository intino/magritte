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

public class ::projectProperName::ExtendedDefinitionImpl extends ASTWrapperPsiElement implements ::projectProperName::ExtendedDefinition {

  public ::projectProperName::ExtendedDefinitionImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ::projectProperName::Visitor) ((::projectProperName::Visitor)visitor).visitExtendedDefinition(this);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<::projectProperName::Identifier> getIdentifierList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ::projectProperName::Identifier.class);
  }

}
