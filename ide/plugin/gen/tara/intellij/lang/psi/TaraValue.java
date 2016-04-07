// This is a generated file. Not intended for manual editing.
package tara.intellij.lang.psi;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface TaraValue extends Value, TaraPsiElement {

  @NotNull
  List<TaraBooleanValue> getBooleanValueList();

  @NotNull
  List<TaraClassReference> getClassReferenceList();

  @NotNull
  List<TaraDoubleValue> getDoubleValueList();

  @Nullable
  TaraEmptyField getEmptyField();

  @NotNull
  List<TaraExpression> getExpressionList();

  @NotNull
  List<TaraIdentifierReference> getIdentifierReferenceList();

  @NotNull
  List<TaraIntegerValue> getIntegerValueList();

  @Nullable
  TaraMetric getMetric();

  @NotNull
  List<TaraStringValue> getStringValueList();

  @NotNull
  List<TaraTupleValue> getTupleValueList();

}
