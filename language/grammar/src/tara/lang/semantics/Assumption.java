package tara.lang.semantics;


import tara.lang.model.Node;

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

	interface FeatureInstance extends Assumption {
	}

	interface ImplicitInstance extends Assumption {
	}

	interface MainInstance extends Assumption {
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

	interface Prototype extends Assumption {
	}
}
