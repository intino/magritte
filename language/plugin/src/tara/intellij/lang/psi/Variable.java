package tara.intellij.lang.psi;

import com.intellij.openapi.util.Iconable;
import com.intellij.pom.Navigatable;
import com.intellij.psi.PsiNamedElement;
import org.jetbrains.annotations.Nullable;
import tara.language.model.Tag;

public interface Variable extends tara.language.model.Variable, Navigatable, Iconable, PsiNamedElement {

	@Nullable
	Contract getContract();

	@Nullable
	TaraValue getValue();

	@Nullable
	Flags getFlagsNode();

	boolean isReference();

	boolean isMultiple();

	int getSize();

	boolean isOverriden();

	void addFlags(Tag... flags);
}
