package io.intino.tara.magritte.natives;

import io.intino.tara.magritte.Expression;
import io.intino.tara.magritte.Layer;
import io.intino.tara.magritte.NativeCode;
import io.intino.tara.magritte.layers.MockLayer;

@SuppressWarnings("unused")
public class CodedLong implements NativeCode, Expression<Long> {

    @Override
	public Long value() {
		return 2L;
	}

	@Override
	public void self(Layer context) {
    }

	@Override
	public Class<? extends Layer> selfClass() {
		return MockLayer.class;
	}
}