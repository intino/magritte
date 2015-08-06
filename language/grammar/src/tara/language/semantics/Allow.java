package tara.language.semantics;

import tara.language.model.Element;
import tara.language.model.Tag;

import java.util.List;

public interface Allow {
	void check(Element node, List<? extends Rejectable> rejectables) throws SemanticException;

	interface Name extends Allow {
	}

	interface Facet extends Allow, AllowContainer {
		String type();

		String[] with();

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

		String type();

		boolean multiple();

		List<String> allowedValues();

		int position();

		String contract();

		List<String> flags();
	}
}
