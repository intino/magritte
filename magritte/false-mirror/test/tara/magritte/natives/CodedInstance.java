package tara.magritte.natives;

import tara.magritte.Expression;
import tara.magritte.Layer;
import tara.magritte.NativeCode;
import tara.magritte.layers.MockLayer;

public class CodedInstance implements NativeCode, Expression<MockLayer> {

	private MockLayer self;

	@Override
	public MockLayer value() {
		return self.model().loadNode("Empty#mock2").as(MockLayer.class);
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