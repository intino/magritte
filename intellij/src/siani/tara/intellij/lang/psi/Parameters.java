package siani.tara.intellij.lang.psi;

import com.intellij.pom.Navigatable;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public interface Parameters extends Navigatable, TaraPsiElement {

	@NotNull
	Collection<Parameter> getParameters();

	boolean areExplicit();

	TaraFacetApply isInFacet();

}
