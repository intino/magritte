// This is a generated file. Not intended for manual editing.
package siani.tara.intellij.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface TaraVarInitValue extends TaraPsiElement {

  @NotNull
  List<TaraBooleanValue> getBooleanValueList();

  @NotNull
  List<TaraDateValue> getDateValueList();

  @NotNull
  List<TaraDoubleValue> getDoubleValueList();

  @Nullable
  TaraEmptyField getEmptyField();

  @NotNull
  List<TaraIdentifier> getIdentifierList();

  @NotNull
  List<TaraIntegerValue> getIntegerValueList();

  @NotNull
  List<TaraLinkValue> getLinkValueList();

  @Nullable
  TaraMeasure getMeasure();

  @NotNull
  List<TaraNaturalValue> getNaturalValueList();

  @NotNull
  List<TaraStringValue> getStringValueList();

}
