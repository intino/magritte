package monet.::projectName::.intellij.psi;

import com.intellij.psi.tree.IElementType;
import monet.::projectName::.intellij.metamodel.::projectProperName::Language;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class ::projectProperName::ElementType extends IElementType {
	public ::projectProperName::ElementType(\@NotNull \@NonNls String debugName) {
		super(debugName, ::projectProperName::Language.INSTANCE);
	}
}