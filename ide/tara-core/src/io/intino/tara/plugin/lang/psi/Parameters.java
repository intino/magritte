package io.intino.tara.plugin.lang.psi;

import com.intellij.pom.Navigatable;
import org.jetbrains.annotations.NotNull;
import io.intino.tara.lang.model.Parameter;

import java.util.List;

public interface Parameters extends Navigatable, TaraPsiElement {

	@NotNull
	List<Parameter> getParameters();

	boolean areExplicit();

	TaraFacetApply isInFacet();

}
