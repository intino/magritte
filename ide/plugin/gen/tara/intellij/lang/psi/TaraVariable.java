// This is a generated file. Not intended for manual editing.
package tara.intellij.lang.psi;

import com.intellij.openapi.util.Iconable;
import com.intellij.pom.Navigatable;
import com.intellij.psi.PsiNamedElement;
import org.jetbrains.annotations.Nullable;
import tara.lang.model.Variable;

public interface TaraVariable extends TaraPsiElement, Variable, Valued, Iconable, Navigatable, PsiNamedElement {

  @Nullable
  TaraAnchor getAnchor();

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
