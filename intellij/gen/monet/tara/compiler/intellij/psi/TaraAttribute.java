// This is a generated file. Not intended for manual editing.
package monet.tara.compiler.intellij.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import monet.tara.intellij.psi.IAttribute;

public interface TaraAttribute extends IAttribute {

  @Nullable
  TaraBooleanAssign getBooleanAssign();

  @Nullable
  TaraBooleanListAssign getBooleanListAssign();

  @Nullable
  TaraDoubleAssign getDoubleAssign();

  @Nullable
  TaraDoubleListAssign getDoubleListAssign();

  @Nullable
  TaraIntegerAssign getIntegerAssign();

  @Nullable
  TaraIntegerListAssign getIntegerListAssign();

  @Nullable
  TaraNaturalAssign getNaturalAssign();

  @Nullable
  TaraNaturalListAssign getNaturalListAssign();

  @Nullable
  TaraStringAssign getStringAssign();

  @Nullable
  TaraStringListAssign getStringListAssign();

}
