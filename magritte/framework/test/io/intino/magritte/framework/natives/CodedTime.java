package io.intino.magritte.framework.natives;

import io.intino.magritte.framework.Expression;
import io.intino.magritte.framework.Layer;
import io.intino.magritte.framework.NativeCode;
import io.intino.magritte.framework.layers.MockLayer;

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