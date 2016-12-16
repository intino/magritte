package tara.intellij.lang.psi;

import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.TaraLanguage;

public class TaraTokenType extends IElementType {
	public TaraTokenType(@NotNull @NonNls String debugName) {
		super(debugName, TaraLanguage.INSTANCE);
	}

	@Override
	public String toString() {
		return "TaraTokenType." + super.toString();
	}
}