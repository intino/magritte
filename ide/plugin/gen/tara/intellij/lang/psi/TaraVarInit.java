// This is a generated file. Not intended for manual editing.
package tara.intellij.lang.psi;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.lang.model.Parameter;

public interface TaraVarInit extends Valued, Parameter {

  @Nullable
  TaraBodyValue getBodyValue();

  @NotNull
  TaraIdentifier getIdentifier();

  @Nullable
  TaraValue getValue();

}
