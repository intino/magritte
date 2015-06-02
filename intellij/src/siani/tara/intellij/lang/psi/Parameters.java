package siani.tara.intellij.lang.psi;

import com.intellij.pom.Navigatable;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface Parameters extends Navigatable, TaraPsiElement {

	@NotNull
	List<Parameter> getParameters();

	boolean areExplicit();

	TaraFacetApply isInFacet();

}
