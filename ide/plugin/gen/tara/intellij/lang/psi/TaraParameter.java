// This is a generated file. Not intended for manual editing.
package tara.intellij.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import  tara.lang.model.Parameter;
import  com.intellij.pom.Navigatable;

public interface TaraParameter extends Valued, Parameter, Navigatable {

  @Nullable
  TaraIdentifier getIdentifier();

  @NotNull
  TaraValue getValue();

}
