// This is a generated file. Not intended for manual editing.
package siani.tara.intellij.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

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
