// This is a generated file. Not intended for manual editing.
package tara.intellij.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import  tara.lang.model.Parameter;

public interface TaraVarInit extends Valued, Parameter {

  @NotNull
  TaraIdentifier getIdentifier();

  @Nullable
  TaraValue getValue();

}
