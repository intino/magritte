package siani.tara.semantic;


import siani.tara.semantic.model.Node;

public interface Assumption {

	void assume(Node node);

	interface Addressed extends Assumption {
	}

	interface Facet extends Assumption {
	}

	interface FacetInstance extends Assumption {
	}

	interface Implicit extends Assumption {
	}

	interface Main extends Assumption {
	}

	interface Feature extends Assumption {
	}

	interface PropertyInstance extends Assumption {
	}

	interface Featureinstance extends Assumption {
	}

	interface ImplicitInstance extends Assumption {

	}

	interface Root extends Assumption {
	}

	interface Single extends Assumption {

	}

	interface Required extends Assumption {
	}

	interface Local extends Assumption {

	}

	interface Terminal extends Assumption {

	}

	interface TerminalInstance extends Assumption {

	}
}
