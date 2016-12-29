package io.intino.tara.magritte.natives;

import io.intino.tara.magritte.layers.DynamicMockLayer;
import io.intino.tara.magritte.Expression;
import io.intino.tara.magritte.Layer;
import io.intino.tara.magritte.NativeCode;

public class CodedReference implements NativeCode, Expression<DynamicMockLayer> {

	private DynamicMockLayer self;

	@Override
	public DynamicMockLayer value() {
		return self.graph().loadNode("Empty#mock2").as(DynamicMockLayer.class);
	}

	@Override
	public void self(Layer context) {
		self = (DynamicMockLayer) context;
	}

	@Override
	public Class<? extends Layer> selfClass() {
		return DynamicMockLayer.class;
	}
}