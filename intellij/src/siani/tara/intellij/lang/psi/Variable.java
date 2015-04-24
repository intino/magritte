package siani.tara.intellij.lang.psi;

import com.intellij.openapi.util.Iconable;
import com.intellij.pom.Navigatable;
import com.intellij.psi.PsiNamedElement;
import org.jetbrains.annotations.Nullable;

public interface Variable extends Navigatable, Iconable, PsiNamedElement {

	@Nullable
	String getType();

	NativeName getNativeName();

	@Nullable
	TaraValue getValue();

	@Nullable
	Flags getFlags();

}
