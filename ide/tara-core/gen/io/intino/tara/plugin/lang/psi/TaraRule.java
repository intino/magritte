// This is a generated file. Not intended for manual editing.
package io.intino.tara.plugin.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import  io.intino.tara.plugin.lang.psi.TaraPsiElement;

public interface TaraRule extends Rule, TaraPsiElement {

  @NotNull
  List<TaraIdentifier> getIdentifierList();

  @Nullable
  TaraIdentifierReference getIdentifierReference();

  @Nullable
  TaraMetric getMetric();

  @Nullable
  TaraRange getRange();

  @Nullable
  TaraStringValue getStringValue();

}
