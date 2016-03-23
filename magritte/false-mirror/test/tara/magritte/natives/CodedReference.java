package tara.magritte.natives;

import tara.magritte.Expression;
import tara.magritte.Layer;
import tara.magritte.NativeCode;
import tara.magritte.layers.DynamicMockLayer;
import tara.magritte.layers.MockLayer;

public class CodedReference implements NativeCode, Expression<DynamicMockLayer> {

	private DynamicMockLayer self;

	@Override
	public DynamicMockLayer value() {
		return self.model().loadInstance("Empty#mock2").as(DynamicMockLayer.class);
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