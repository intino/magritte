// This is a generated file. Not intended for manual editing.
package tara.intellij.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import  tara.language.model.Parameter;
import  com.intellij.pom.Navigatable;

public interface TaraExplicitParameter extends Valued, Parameter, Navigatable {

  @NotNull
  TaraIdentifier getIdentifier();

  @Nullable
  TaraValue getValue();

}
