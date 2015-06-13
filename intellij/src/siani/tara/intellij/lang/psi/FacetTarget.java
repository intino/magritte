package siani.tara.intellij.lang.psi;

import com.intellij.pom.Navigatable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface FacetTarget extends NodeContainer, Navigatable {

	String getTarget();

	@NotNull
	List<Node> getIncludes();

	@NotNull
	List<Variable> getVariables();

	@Nullable
	TaraIdentifierReference getIdentifierReference();

	@Nullable
	TaraBody getBody();
}
