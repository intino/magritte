package tara.intellij.lang.psi;

import java.util.List;

public interface Parametrized extends tara.language.model.Parametrized {

	List<? extends tara.language.model.Parameter> parameters();
}
