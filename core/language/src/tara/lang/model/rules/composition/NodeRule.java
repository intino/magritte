package tara.lang.model.rules.composition;

import tara.lang.model.rules.CompositionRule;

public abstract class NodeRule implements CompositionRule {

	@Override
	public CompositionRule is() {
		return this;
	}

	@Override
	public void is(CompositionRule rule) {

	}

	@Override
	public CompositionRule into() {
		return null;
	}

	@Override
	public void into(CompositionRule rule) {
	}

}
