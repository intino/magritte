// This is a generated file. Not intended for manual editing.
package tara.intellij.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import  tara.intellij.lang.psi.TaraPsiElement;

public interface TaraParameters extends Parameters, TaraPsiElement {

  @NotNull
  List<TaraExplicitParameter> getExplicitParameterList();

  @NotNull
  List<TaraImplicitParameter> getImplicitParameterList();

}
