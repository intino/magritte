package monet.::projectName::.intellij.metamodel.psi;

import com.intellij.psi.tree.IElementType;
import monet.::projectName::.intellij.metamodel.::projectProperName::Language;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class ::projectProperName::TokenType extends IElementType {
	public ::projectProperName::TokenType(\@NotNull \@NonNls String debugName) {
		super(debugName, ::projectProperName::Language.INSTANCE);
	}

	\@Override
	public String toString() {
		return "::projectProperName::TokenType." + super.toString();
	}
}