package tara.intellij.lang.psi;

import java.util.List;

public interface Parametrized extends tara.language.model.Parametrized {

	List<Parameter> parameters();
}
