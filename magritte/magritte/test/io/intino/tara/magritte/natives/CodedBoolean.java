package io.intino.tara.magritte.natives;

import io.intino.tara.magritte.Expression;
import io.intino.tara.magritte.Layer;
import io.intino.tara.magritte.NativeCode;
import io.intino.tara.magritte.layers.MockLayer;

@SuppressWarnings("unused")
public class CodedBoolean implements NativeCode, Expression<Boolean> {

    @Override
	public Boolean value() {
		return false;
	}

	@Override
	public void self(Layer context) {
    }

	@Override
	public Class<? extends Layer> selfClass() {
		return MockLayer.class;
	}
}