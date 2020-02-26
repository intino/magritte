package io.intino.magritte.lang.semantics;

import io.intino.magritte.lang.model.Element;
import io.intino.magritte.lang.model.Primitive;
import io.intino.magritte.lang.model.Rule;
import io.intino.magritte.lang.model.Tag;
import io.intino.magritte.lang.model.rules.Size;
import io.intino.magritte.lang.model.rules.variable.VariableRule;
import io.intino.magritte.lang.semantics.errorcollector.SemanticException;

import java.util.List;

public interface Constraint {

	void check(Element element) throws SemanticException;


	interface Name extends Constraint {
	}

	interface TerminalVariableRedefinition extends Constraint {

	}

	interface Component extends Constraint {

		String type();

		@Deprecated
		Rule compositionRule();

		List<Rule> rules();

		List<Tag> annotations();
	}

	interface OneOf extends Component {
		List<Component> components();
	}

	interface Aspect extends Constraint {
		String type();

		String[] with();

		boolean isRequired();

		String[] withOut();

		boolean terminal();

		Aspect has(Constraint... require);

		List<Constraint> constraints();
	}

	interface MetaAspect extends Constraint {
		String type();

		String[] with();
	}

	interface Parameter extends Constraint {

		String name();

		Primitive type();

		String aspect();

		Size size();

		int position();

		String scope();

		VariableRule rule();

		List<Tag> flags();
	}

	interface ComponentNotFound extends Constraint {

	}

	interface RejectOtherParameters extends Constraint {

	}

	interface RejectOtherAspects extends Constraint {

	}
}
