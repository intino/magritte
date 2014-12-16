// This is a generated file. Not intended for manual editing.
package siani.tara.intellij.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface TaraNaturalAttribute extends TaraPsiElement {

  @Nullable
  TaraDoubleMeasure getDoubleMeasure();

  @Nullable
  TaraMeasureValue getMeasureValue();

  @NotNull
  List<TaraNaturalValue> getNaturalValueList();

}
