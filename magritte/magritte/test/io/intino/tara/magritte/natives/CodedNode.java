package io.intino.tara.magritte.natives;

import io.intino.tara.magritte.layers.MockLayer;
import io.intino.tara.magritte.Expression;
import io.intino.tara.magritte.Layer;
import io.intino.tara.magritte.NativeCode;

@SuppressWarnings("unused")
public class CodedNode implements NativeCode, Expression<MockLayer> {

	private MockLayer self;

	@Override
	public MockLayer value() {
		return self.graph().load("Empty#mock2").as(MockLayer.class);
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