package tara.intellij.lang.psi;

import com.intellij.pom.Navigatable;
import org.jetbrains.annotations.NotNull;
import tara.language.model.Facet;

import java.util.List;

public interface FacetApply extends Facet, NodeContainer, Parametrized, Navigatable, TaraPsiElement {

	@NotNull
	String getType();

	Body getBody();

	@NotNull
	List<Node> components();

	@NotNull
	List<Variable> variables();
}
