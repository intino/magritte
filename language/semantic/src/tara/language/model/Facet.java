package tara.language.model;

import java.util.List;

public interface Facet extends NodeContainer, Parametrized {

	String type();

	List<? extends Parameter> parameters();
}
