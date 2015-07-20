// This is a generated file. Not intended for manual editing.
package tara.intellij.lang.psi;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface TaraVariable extends Variable {

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
