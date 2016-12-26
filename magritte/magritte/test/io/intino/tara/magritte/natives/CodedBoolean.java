package io.intino.tara.magritte.natives;

import io.intino.tara.magritte.Expression;
import io.intino.tara.magritte.Layer;
import io.intino.tara.magritte.NativeCode;
import io.intino.tara.magritte.layers.MockLayer;

public class CodedBoolean implements NativeCode, Expression<Boolean> {

	private MockLayer self;

	@Override
	public Boolean value() {
		return false;
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