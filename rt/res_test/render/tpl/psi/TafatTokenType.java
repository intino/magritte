package monet.tafat.intellij.psi;

import com.intellij.psi.tree.IElementType;
import monet.tafat.intellij.metamodel.TafatLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class TafatTokenType extends IElementType {
	public TafatTokenType(@NotNull @NonNls String debugName) {
		super(debugName, TafatLanguage.INSTANCE);
	}

	@Override
	public String toString() {
		return "TafatTokenType." + super.toString();
	}
}