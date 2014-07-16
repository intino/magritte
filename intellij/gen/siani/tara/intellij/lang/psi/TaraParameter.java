// This is a generated file. Not intended for manual editing.
package siani.tara.intellij.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface TaraParameter extends Parameter {

  @NotNull
  List<TaraBooleanValue> getBooleanValueList();

  @NotNull
  List<TaraCodeValue> getCodeValueList();

  @NotNull
  List<TaraCoordinateValue> getCoordinateValueList();

  @NotNull
  List<TaraDateValue> getDateValueList();

  @NotNull
  List<TaraDoubleValue> getDoubleValueList();

  @Nullable
  TaraEmpty getEmpty();

  @NotNull
  List<TaraIdentifierReference> getIdentifierReferenceList();

  @NotNull
  List<TaraIntegerValue> getIntegerValueList();

  @Nullable
  TaraMeasure getMeasure();

  @Nullable
  TaraMetaWord getMetaWord();

  @NotNull
  List<TaraNaturalValue> getNaturalValueList();

  @NotNull
  List<TaraStringValue> getStringValueList();

}
