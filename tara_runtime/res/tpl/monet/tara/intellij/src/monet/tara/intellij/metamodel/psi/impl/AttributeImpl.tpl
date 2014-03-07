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

public class ::projectProperName::AttributeImpl extends ::projectProperName::AttributeMixin implements ::projectProperName::Attribute {

  public ::projectProperName::AttributeImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ::projectProperName::Visitor) ((::projectProperName::Visitor)visitor).visitAttribute(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ::projectProperName::BooleanList getBooleanList() {
    return findChildByClass(::projectProperName::BooleanList.class);
  }

  @Override
  @Nullable
  public ::projectProperName::BooleanValue getBooleanValue() {
    return findChildByClass(::projectProperName::BooleanValue.class);
  }

  @Override
  @Nullable
  public ::projectProperName::DoubleList getDoubleList() {
    return findChildByClass(::projectProperName::DoubleList.class);
  }

  @Override
  @Nullable
  public ::projectProperName::DoubleValue getDoubleValue() {
    return findChildByClass(::projectProperName::DoubleValue.class);
  }

  @Override
  @Nullable
  public ::projectProperName::IntegerList getIntegerList() {
    return findChildByClass(::projectProperName::IntegerList.class);
  }

  @Override
  @Nullable
  public ::projectProperName::IntegerValue getIntegerValue() {
    return findChildByClass(::projectProperName::IntegerValue.class);
  }

  @Override
  @Nullable
  public ::projectProperName::NaturalList getNaturalList() {
    return findChildByClass(::projectProperName::NaturalList.class);
  }

  @Override
  @Nullable
  public ::projectProperName::NaturalValue getNaturalValue() {
    return findChildByClass(::projectProperName::NaturalValue.class);
  }

  @Override
  @Nullable
  public ::projectProperName::StringList getStringList() {
    return findChildByClass(::projectProperName::StringList.class);
  }

  @Override
  @Nullable
  public ::projectProperName::StringValue getStringValue() {
    return findChildByClass(::projectProperName::StringValue.class);
  }

  @Override
  @Nullable
  public ::projectProperName::VariableNames getVariableNames() {
    return findChildByClass(::projectProperName::VariableNames.class);
  }

}
