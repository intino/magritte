// This is a generated file. Not intended for manual editing.
package tara.intellij.lang.psi;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface TaraRule extends Rule, TaraPsiElement {

  @Nullable
  TaraClassTypeValue getClassTypeValue();

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
