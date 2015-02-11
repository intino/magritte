package siani.tara.intellij.lang.psi;

import com.intellij.pom.Navigatable;
import org.jetbrains.annotations.NotNull;

public interface FacetApply extends Navigatable, TaraPsiElement {

	@NotNull
	String getFacetName();
	Body getBody();
}
