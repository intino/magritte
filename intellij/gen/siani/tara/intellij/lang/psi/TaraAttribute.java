// This is a generated file. Not intended for manual editing.
package siani.tara.intellij.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface TaraAttribute extends Attribute {

  @Nullable
  TaraAnnotationsAndFacets getAnnotationsAndFacets();

  @Nullable
  TaraAttributeType getAttributeType();

  @Nullable
  TaraBooleanValue getBooleanValue();

  @Nullable
  TaraCodeValue getCodeValue();

  @Nullable
  TaraCoordinateValue getCoordinateValue();

  @Nullable
  TaraDateValue getDateValue();

  @Nullable
  TaraDoc getDoc();

  @Nullable
  TaraDoubleValue getDoubleValue();

  @Nullable
  TaraEmptyField getEmptyField();

  @Nullable
  TaraIdentifierReference getIdentifierReference();

  @Nullable
  TaraIntegerValue getIntegerValue();

  @Nullable
  TaraNaturalValue getNaturalValue();

  @Nullable
  TaraStringValue getStringValue();

  @Nullable
  TaraWord getWord();

}
