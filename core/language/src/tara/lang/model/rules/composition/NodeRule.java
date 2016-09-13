package tara.lang.model.rules.composition;

import tara.lang.model.rules.CompositionRule;

public interface NodeRule extends CompositionRule {

	default CompositionRule is() {
		return this;
	}

	default void is(CompositionRule rule) {

	}

	default CompositionRule into() {
		return null;
	}

	default void into(CompositionRule rule) {
	}

}
