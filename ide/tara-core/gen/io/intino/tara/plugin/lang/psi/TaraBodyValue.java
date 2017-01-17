// This is a generated file. Not intended for manual editing.
package io.intino.tara.plugin.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import  io.intino.tara.plugin.lang.psi.Value;

public interface TaraBodyValue extends TaraPsiElement, Value {

  @Nullable
  TaraExpression getExpression();

  @Nullable
  TaraStringValue getStringValue();

}
