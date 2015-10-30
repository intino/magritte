package tara.lang.semantics;

import tara.lang.model.Element;
import tara.lang.model.Primitive;
import tara.lang.model.Rule;
import tara.lang.model.Tag;
import tara.lang.model.rules.Size;

import java.util.List;

public interface Constraint {

	void check(Element element) throws SemanticException;


	interface Name extends Constraint {
	}

	interface TerminalVariableRedefinition extends Constraint {

	}

	interface Component extends Constraint {
		String type();

		Size size();

		List<Tag> annotations();
	}

	interface OneOf extends Component {
		Constraint[] components();
	}

	interface Plate extends Constraint {
	}

	interface Facet extends Constraint {
	}

	interface Parameter extends Constraint {

		String name();

		Primitive type();

		Size size();

		Object defaultValue();

		int position();

		Rule rule();

		List<String> annotations();
	}

	interface ComponentNotFound extends Component {

	}

	interface ParameterNotFound extends Parameter {

	}


}
