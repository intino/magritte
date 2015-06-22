package siani.tara.semantic;

import siani.tara.semantic.model.Element;
import siani.tara.semantic.model.Tag;

import java.util.Collection;
import java.util.List;

public interface Allow {
	void check(Element node, List<? extends Rejectable> rejectables) throws SemanticException;

	interface Name extends Allow {
	}

	interface Facet extends Allow {
		String type();

		Facet allow(Allow... parameter);

		Facet require(Constraint.Require... require);

		Collection<Allow> allows();

		Collection<Constraint> constraints();
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
