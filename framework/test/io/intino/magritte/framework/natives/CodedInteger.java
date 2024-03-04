package io.intino.magritte.framework.natives;

import io.intino.magritte.framework.Expression;
import io.intino.magritte.framework.Layer;
import io.intino.magritte.framework.NativeCode;
import io.intino.magritte.framework.layers.MockLayer;

@SuppressWarnings("unused")
public class CodedInteger implements NativeCode, Expression<Integer> {

	@Override
	public Integer value() {
		return 2;
	}

	@Override
	public void self(Layer context) {
	}

	@Override
	public Class<? extends Layer> selfClass() {
		return MockLayer.class;
	}
}