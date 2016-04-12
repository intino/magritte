package tara.magritte.natives;

import tara.magritte.Concept;
import tara.magritte.Expression;
import tara.magritte.Layer;
import tara.magritte.NativeCode;
import tara.magritte.layers.MockLayer;

public class CodedConcept implements NativeCode, Expression<Concept> {

	private MockLayer self;

	@Override
	public Concept value() {
		return self.model().concept("Mock");
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