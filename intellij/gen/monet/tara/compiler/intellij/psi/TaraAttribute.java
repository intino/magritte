// This is a generated file. Not intended for manual editing.
package monet.tara.compiler.intellij.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;

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

}
