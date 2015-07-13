package tara.intellij.lang.psi;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface Parametrized {

	@NotNull
	List<Parameter> getParameterList();

}
