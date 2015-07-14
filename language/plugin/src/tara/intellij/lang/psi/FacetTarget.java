package tara.intellij.lang.psi;

import com.intellij.pom.Navigatable;
import org.jetbrains.annotations.Nullable;

public interface FacetTarget extends tara.language.model.FacetTarget, NodeContainer, Navigatable {

	@Nullable
	IdentifierReference getIdentifierReference();

	@Nullable
	TaraBody getBody();

	<T extends tara.language.model.Node> void targetNode(T destiny);
}
