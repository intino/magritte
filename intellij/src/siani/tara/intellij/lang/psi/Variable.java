package siani.tara.intellij.lang.psi;

import com.intellij.openapi.util.Iconable;
import com.intellij.pom.Navigatable;
import com.intellij.psi.PsiNamedElement;

import java.util.Collection;

public interface Variable extends Navigatable, Iconable, PsiNamedElement {

	String getType();

	TaraAnnotations getAnnotations();

	Collection<String> getDefaultValuesAsString();

}
