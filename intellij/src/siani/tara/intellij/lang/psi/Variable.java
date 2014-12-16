package siani.tara.intellij.lang.psi;

import com.intellij.openapi.util.Iconable;
import com.intellij.pom.Navigatable;
import com.intellij.psi.PsiNamedElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public interface Variable extends Navigatable, Iconable, PsiNamedElement {

	@NotNull
	String getType();

	@Nullable
	TaraAnnotations getAnnotations();

	@NotNull
	Collection<String> getDefaultValuesAsString();

}
