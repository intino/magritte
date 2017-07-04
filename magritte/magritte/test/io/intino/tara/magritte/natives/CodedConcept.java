package io.intino.tara.magritte.natives;

import io.intino.tara.magritte.Concept;
import io.intino.tara.magritte.Expression;
import io.intino.tara.magritte.Layer;
import io.intino.tara.magritte.NativeCode;
import io.intino.tara.magritte.layers.MockLayer;

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