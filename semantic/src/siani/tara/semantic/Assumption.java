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

	interface Feature extends Assumption {

	}

	interface PropertyInstance extends Assumption {

	}

	interface Featureinstance extends Assumption {

	}

	interface Root extends Assumption {
	}

	interface Single extends Assumption {

	}

	interface Multiple extends Assumption {
	}

	interface Required extends Assumption {
	}

	interface Enclosed extends Assumption {

	}

	interface Terminal extends Assumption {

	}

	interface TerminalInstance extends Assumption {

	}

}
