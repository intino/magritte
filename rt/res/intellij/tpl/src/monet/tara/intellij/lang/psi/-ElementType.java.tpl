package monet.::projectName::.intellij.lang.psi;

import com.intellij.psi.tree.IElementType;
import monet.::projectName::.intellij.lang.::projectProperName::Language;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class ::projectProperName::ElementType extends IElementType {
	public ::projectProperName::ElementType(\@NotNull \@NonNls String debugName) {
		super(debugName, ::projectProperName::Language.INSTANCE);
	}
}