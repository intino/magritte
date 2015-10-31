package tara.lang.model.rules;

import tara.lang.model.Node;
import tara.lang.model.Rule;

import java.util.List;

public interface CompositionRule extends Rule<List<Node>> {


	int min();

	int max();

	default boolean isRequired() {
		return min() > 0;
	}

	default boolean isSingle() {
		return max() == 1;
	}

}
