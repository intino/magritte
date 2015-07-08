package siani.tara.intellij.lang.psi;

import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.TaraLanguage;

public class TaraElementType extends IElementType {
	public TaraElementType(@NotNull @NonNls String debugName) {
		super(debugName, TaraLanguage.INSTANCE);
	}
}