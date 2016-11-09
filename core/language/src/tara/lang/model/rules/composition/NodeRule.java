package tara.lang.model.rules.composition;

public interface NodeRule extends tara.lang.model.rules.NodeRule {

	default tara.lang.model.rules.NodeRule is() {
		return this;
	}

	default void is(tara.lang.model.rules.NodeRule rule) {

	}

	default tara.lang.model.rules.NodeRule into() {
		return null;
	}

	default void into(tara.lang.model.rules.NodeRule rule) {
	}

}
