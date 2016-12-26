package io.intino.tara.lang.semantics;


import io.intino.tara.lang.model.Node;

public interface Assumption {

	void assume(Node node);

	interface Facet extends Assumption {
	}

	interface FacetInstance extends Assumption {
	}

	interface Main extends Assumption {
	}

	interface Feature extends Assumption {
	}

	interface FeatureInstance extends Assumption {
	}

	interface Component extends Assumption {
	}

	interface Single extends Assumption {
	}

	interface Required extends Assumption {
	}

	interface Local extends Assumption {
	}

	interface Terminal extends Assumption {
	}

	interface Instance extends Assumption {
	}

	interface Versioned extends Assumption {
	}

	interface Volatile extends Assumption {
	}
}
