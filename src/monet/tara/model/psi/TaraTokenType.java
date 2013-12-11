package monet.tara.model.psi;

import com.intellij.psi.tree.IElementType;
import monet.tara.metamodel.TaraLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class TaraTokenType extends IElementType {
	public TaraTokenType(@NotNull @NonNls String debugName) {
		super(debugName, TaraLanguage.INSTANCE);
	}

	@Override
	public String toString() {
		return "TaraTokenType." + super.toString();
	}
}