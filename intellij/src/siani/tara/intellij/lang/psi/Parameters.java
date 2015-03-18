package siani.tara.intellij.lang.psi;

import com.intellij.pom.Navigatable;
import org.jetbrains.annotations.NotNull;

public interface Parameters extends Navigatable, TaraPsiElement {

	@NotNull
	Parameter[] getParameters();

	boolean areExplicit();

	TaraFacetApply isInFacet();

}
