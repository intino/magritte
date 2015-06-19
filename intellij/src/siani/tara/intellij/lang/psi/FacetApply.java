package siani.tara.intellij.lang.psi;

import com.intellij.pom.Navigatable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface FacetApply extends NodeContainer, Parametrized, Navigatable, TaraPsiElement {

	@NotNull
	String getType();

	Body getBody();

	@NotNull
	List<Node> getIncludes();

	@Nullable
	Parameters getParameters();

	@NotNull
	List<Variable> getVariables();

	@NotNull
	List<Parameter> getParameterList();

	@NotNull
	List<VarInit> getVarInits();
}
