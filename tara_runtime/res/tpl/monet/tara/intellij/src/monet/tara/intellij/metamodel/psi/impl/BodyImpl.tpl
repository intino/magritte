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

public class ::projectProperName::BodyImpl extends BodyMixin implements ::projectProperName::Body {

  public ::projectProperName::BodyImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ::projectProperName::Visitor) ((::projectProperName::Visitor)visitor).visitBody(this);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<::projectProperName::Attribute> getAttributeList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ::projectProperName::Attribute.class);
  }

  @Override
  @NotNull
  public List<::projectProperName::Definition> getDefinitionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ::projectProperName::Definition.class);
  }

  @Override
  @NotNull
  public List<::projectProperName::DefinitionInjection> getDefinitionInjectionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ::projectProperName::DefinitionInjection.class);
  }

  @Override
  @NotNull
  public List<::projectProperName::ReferenceStatement> getReferenceStatementList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ::projectProperName::ReferenceStatement.class);
  }

  @Override
  @NotNull
  public List<::projectProperName::Word> getWordList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ::projectProperName::Word.class);
  }

}
