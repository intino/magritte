package io.intino.magritte.framework.natives;

import io.intino.magritte.framework.Concept;
import io.intino.magritte.framework.Expression;
import io.intino.magritte.framework.Layer;
import io.intino.magritte.framework.NativeCode;
import io.intino.magritte.framework.layers.MockLayer;

public class CodedConcept implements NativeCode, Expression<Concept> {

	private MockLayer self;

	@Override
	public Concept value() {
		return self.core$().graph().concept("Mock");
	}

	@Override
	public void self(Layer context) {
		self = (MockLayer) context;
	}

	@Override
	public Class<? extends Layer> selfClass() {
		return MockLayer.class;
	}
}