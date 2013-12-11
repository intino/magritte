package monet.tara.metamodel.psi;

import com.intellij.psi.tree.IElementType;
import monet.tara.metamodel.TaraLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class TaraElementType extends IElementType {
	public TaraElementType(@NotNull @NonNls String debugName) {
		super(debugName, TaraLanguage.INSTANCE);
	}
}