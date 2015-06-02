package siani.tara.intellij.lang.psi;

import com.intellij.pom.Navigatable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface FacetTarget extends Navigatable, TaraPsiElement {

	String target();

	@NotNull
	List<Node> includes();

	@NotNull
	List<Variable> getVariables();

	@Nullable
	TaraIdentifierReference getIdentifierReference();

	@Nullable
	TaraBody getBody();
}
