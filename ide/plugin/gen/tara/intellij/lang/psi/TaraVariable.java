// This is a generated file. Not intended for manual editing.
package tara.intellij.lang.psi;

import com.intellij.openapi.util.Iconable;
import com.intellij.pom.Navigatable;
import com.intellij.psi.PsiNamedElement;
import org.jetbrains.annotations.Nullable;
import tara.language.model.Variable;

public interface TaraVariable extends TaraPsiElement, Variable, Valued, Iconable, Navigatable, PsiNamedElement {

  @Nullable
  TaraAttributeType getAttributeType();

  @Nullable
  TaraCount getCount();

  @Nullable
  TaraDoc getDoc();

  @Nullable
  TaraFlags getFlags();

  @Nullable
  TaraIdentifier getIdentifier();

  @Nullable
  TaraValue getValue();

  @Nullable
  TaraVariableType getVariableType();

}
