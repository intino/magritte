package monet.tafat.intellij.psi;

import com.intellij.psi.tree.IElementType;
import monet.tafat.intellij.metamodel.TafatLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class TafatElementType extends IElementType {
	public TafatElementType(@NotNull @NonNls String debugName) {
		super(debugName, TafatLanguage.INSTANCE);
	}
}