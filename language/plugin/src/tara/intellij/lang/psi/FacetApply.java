package tara.intellij.lang.psi;

import com.intellij.pom.Navigatable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.language.model.Facet;

import java.util.List;

public interface FacetApply extends Facet, NodeContainer, Parametrized, Navigatable, TaraPsiElement {

	@Nullable
	Body getBody();

	@NotNull
	List<Node> components();

	@NotNull
	List<Variable> variables();

	@NotNull
	default String qualifiedName() {
		return container().qualifiedName() + "." + container().name() + "_" + type();
	}

	@NotNull
	default String type() {
		if (!((TaraFacetApply) this).getMetaIdentifierList().isEmpty())
			return ((TaraFacetApply) this).getMetaIdentifierList().get(0).getText();
		return "";
	}

}
