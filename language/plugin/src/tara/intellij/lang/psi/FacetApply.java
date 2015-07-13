package tara.intellij.lang.psi;

import com.intellij.pom.Navigatable;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface FacetApply extends NodeContainer, Parametrized, Navigatable, TaraPsiElement {

	@NotNull
	String getType();

	Body getBody();

	@NotNull
	List<Node> getIncludes();

	@NotNull
	List<Variable> getVariables();
}
