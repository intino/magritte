// This is a generated file. Not intended for manual editing.
package siani.tara.intellij.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface TaraVariable extends Variable {

  @Nullable
  TaraAnnotationsAndFacets getAnnotationsAndFacets();

  @Nullable
  TaraAttributeType getAttributeType();

  @NotNull
  List<TaraBooleanValue> getBooleanValueList();

  @NotNull
  List<TaraCoordinateValue> getCoordinateValueList();

  @NotNull
  List<TaraDateValue> getDateValueList();

  @Nullable
  TaraDoc getDoc();

  @NotNull
  List<TaraDoubleValue> getDoubleValueList();

  @Nullable
  TaraEmptyField getEmptyField();

  @Nullable
  TaraIdentifierReference getIdentifierReference();

  @NotNull
  List<TaraIntegerValue> getIntegerValueList();

  @Nullable
  TaraMeasure getMeasure();

  @NotNull
  List<TaraNaturalValue> getNaturalValueList();

  @NotNull
  List<TaraStringValue> getStringValueList();

  @Nullable
  TaraWord getWord();

}
