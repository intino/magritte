package tara.intellij.lang.psi;

import com.intellij.openapi.util.Iconable;
import com.intellij.pom.Navigatable;
import com.intellij.psi.PsiNamedElement;
import org.jetbrains.annotations.Nullable;
import tara.language.model.Tag;

import java.util.List;

public interface Variable extends tara.language.model.Variable, Navigatable, Iconable, PsiNamedElement {

	@Nullable
	String getType();

	@Nullable
	Contract getContract();

	@Nullable
	TaraValue getValue();

	@Nullable
	Flags getFlags();

	boolean isReference();

	boolean isMultiple();

	int getSize();

	boolean isOverriden();

	List<Tag> getAllFlags();

	void addFlags(String... flags);
}
