// This is a generated file. Not intended for manual editing.
package tara.intellij.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import  tara.language.model.Variable;
import  tara.intellij.lang.psi.Valued;
import  com.intellij.openapi.util.Iconable;
import  com.intellij.pom.Navigatable;
import  com.intellij.psi.PsiNamedElement;

public interface TaraVariable extends TaraPsiElement, Variable, Valued, Iconable, Navigatable, PsiNamedElement {

  @Nullable
  TaraAttributeType getAttributeType();

  @Nullable
  TaraCount getCount();

  @NotNull
  List<TaraDoc> getDocList();

  @Nullable
  TaraFlags getFlags();

  @Nullable
  TaraIdentifier getIdentifier();

  @Nullable
  TaraMeasureValue getMeasureValue();

  @Nullable
  TaraValue getValue();

  @Nullable
  TaraVariableType getVariableType();

}
