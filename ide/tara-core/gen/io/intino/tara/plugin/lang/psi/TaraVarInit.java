// This is a generated file. Not intended for manual editing.
package io.intino.tara.plugin.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import  io.intino.tara.lang.model.Parameter;

public interface TaraVarInit extends Valued, Parameter {

  @Nullable
  TaraBodyValue getBodyValue();

  @NotNull
  TaraIdentifier getIdentifier();

  @Nullable
  TaraValue getValue();

}
