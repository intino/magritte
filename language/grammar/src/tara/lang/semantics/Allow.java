package tara.lang.semantics;

import tara.lang.model.Element;
import tara.lang.model.Primitive;
import tara.lang.model.Rule;
import tara.lang.model.Tag;

import java.util.List;

public interface Allow {
	void check(Element node, List<? extends Rejectable> rejectables) throws SemanticException;

	interface Name extends Allow {
	}

	interface Facet extends Allow, AllowContainer {
		String type();

		String[] with();

		boolean terminal();

		Facet allow(Allow... allow);

		Facet require(Constraint.Require... require);

		List<Allow> allows();

		List<Constraint> constraints();
	}

	interface Include extends Allow {
		String type();

		Tag[] annotations();
	}

	interface Multiple extends Include {

	}

	interface OneOf extends Include {
		List<Allow> allows();
	}

	interface Single extends Include {
	}

	interface Parameter extends Allow {
		String name();

		Primitive type();

		Object defaultValue();

		boolean multiple();

		int position();

		Rule rule();

		List<String> flags();
	}
}
