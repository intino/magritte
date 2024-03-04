package io.intino.magritte.framework.natives;

import io.intino.magritte.framework.Expression;
import io.intino.magritte.framework.Layer;
import io.intino.magritte.framework.NativeCode;
import io.intino.magritte.framework.layers.MockLayer;

import java.time.Instant;

@SuppressWarnings("unused")
public class CodedInstant implements NativeCode, Expression<Instant> {

	@Override
	public Instant value() {
		return Instant.parse("1987-09-17T04:00:00Z");
	}

	@Override
	public void self(Layer context) {
	}

	@Override
	public Class<? extends Layer> selfClass() {
		return MockLayer.class;
	}
}