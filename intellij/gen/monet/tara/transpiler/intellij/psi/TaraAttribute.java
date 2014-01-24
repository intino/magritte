// This is a generated file. Not intended for manual editing.
package monet.tara.transpiler.intellij.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface TaraAttribute extends PsiElement {

  @Nullable
  TaraBooleanAssign getBooleanAssign();

  @Nullable
  TaraDoubleAssign getDoubleAssign();

  @Nullable
  TaraIntegerAssign getIntegerAssign();

  @Nullable
  TaraNaturalAssign getNaturalAssign();

  @Nullable
  TaraStringAssign getStringAssign();

  @Nullable
  TaraWordBody getWordBody();

}
