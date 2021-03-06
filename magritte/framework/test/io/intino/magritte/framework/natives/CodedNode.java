package io.intino.magritte.framework.natives;

import io.intino.magritte.framework.Expression;
import io.intino.magritte.framework.Layer;
import io.intino.magritte.framework.NativeCode;
import io.intino.magritte.framework.layers.MockLayer;

@SuppressWarnings("unused")
public class CodedNode implements NativeCode, Expression<MockLayer> {

	private MockLayer self;

	@Override
	public MockLayer value() {
		return self.core$().graph().load("Empty#mock2").as(MockLayer.class);
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