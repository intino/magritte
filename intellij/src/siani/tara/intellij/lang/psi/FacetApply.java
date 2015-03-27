package siani.tara.intellij.lang.psi;

import com.intellij.pom.Navigatable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface FacetApply extends Navigatable, TaraPsiElement {

	@NotNull
	String getType();

	Body getBody();

	@Nullable
	Parameters getParameters();
}
