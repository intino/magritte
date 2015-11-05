package tara.lang.semantics;

import tara.lang.model.Element;
import tara.lang.model.Primitive;
import tara.lang.model.Rule;
import tara.lang.model.Tag;
import tara.lang.model.rules.CompositionRule;
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

		CompositionRule compositionRule();

		List<Tag> annotations();
	}

	interface OneOf extends Component {
		List<Component> components();
	}

	interface Plate extends Constraint {
	}

	interface Facet extends Constraint {
		String type();

		String[] with();

		boolean terminal();

		Facet has(Constraint... require);

		List<Constraint> constraints();
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

	interface ComponentNotFound extends Constraint {

	}

	interface RejectOTherParameters extends Constraint {

	}


}
