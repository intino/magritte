// This is a generated file. Not intended for manual editing.
package io.intino.tara.plugin.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import  io.intino.tara.lang.model.Variable;
import  io.intino.tara.plugin.lang.psi.Valued;
import  com.intellij.openapi.util.Iconable;
import  com.intellij.pom.Navigatable;
import  com.intellij.psi.PsiNamedElement;

public interface TaraVariable extends TaraPsiElement, Variable, Valued, Iconable, Navigatable, PsiNamedElement {

  @Nullable
  TaraBodyValue getBodyValue();

  @Nullable
  TaraDoc getDoc();

  @Nullable
  TaraFlags getFlags();

  @Nullable
  TaraIdentifier getIdentifier();

  @Nullable
  TaraRuleContainer getRuleContainer();

  @Nullable
  TaraSizeRange getSizeRange();

  @Nullable
  TaraValue getValue();

  @Nullable
  TaraVariableType getVariableType();

}
