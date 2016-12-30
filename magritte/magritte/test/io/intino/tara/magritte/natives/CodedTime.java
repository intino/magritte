package io.intino.tara.magritte.natives;

import io.intino.tara.magritte.layers.MockLayer;
import io.intino.tara.magritte.Expression;
import io.intino.tara.magritte.Layer;
import io.intino.tara.magritte.NativeCode;

import java.time.LocalTime;

@SuppressWarnings("unused")
public class CodedTime implements NativeCode, Expression<LocalTime> {

    @Override
	public LocalTime value() {
		return LocalTime.of(4, 0);
	}

	@Override
	public void self(Layer context) {
    }

	@Override
	public Class<? extends Layer> selfClass() {
		return MockLayer.class;
	}
}