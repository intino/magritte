package io.intino.tara.magritte.natives;

import io.intino.tara.magritte.Expression;
import io.intino.tara.magritte.Layer;
import io.intino.tara.magritte.NativeCode;
import io.intino.tara.magritte.layers.MockLayer;

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