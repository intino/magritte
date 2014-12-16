// This is a generated file. Not intended for manual editing.
package siani.tara.intellij.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface TaraDoubleAttribute extends TaraPsiElement {

  @Nullable
  TaraCount getCount();

  @Nullable
  TaraDoubleMeasure getDoubleMeasure();

  @NotNull
  List<TaraDoubleValue> getDoubleValueList();

  @Nullable
  TaraMeasureValue getMeasureValue();

}
