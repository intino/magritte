// This is a generated file. Not intended for manual editing.
package io.intino.tara.plugin.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import  io.intino.tara.plugin.lang.psi.TaraPsiElement;

public interface TaraValue extends Value, TaraPsiElement {

  @NotNull
  List<TaraBooleanValue> getBooleanValueList();

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

  @NotNull
  List<TaraMethodReference> getMethodReferenceList();

  @Nullable
  TaraMetric getMetric();

  @NotNull
  List<TaraStringValue> getStringValueList();

  @NotNull
  List<TaraTupleValue> getTupleValueList();

}
