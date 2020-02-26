package io.intino.magritte.framework.natives;

import io.intino.magritte.framework.Expression;
import io.intino.magritte.framework.Layer;
import io.intino.magritte.framework.NativeCode;
import io.intino.magritte.framework.layers.MockLayer;
import io.intino.magritte.framework.types.DateX;

@SuppressWarnings("unused")
public class CodedDateX implements NativeCode, Expression<DateX> {

	@Override
	public DateX value() {
		return DateX.parse("1987-09-17 04:00");
	}

	@Override
	public void self(Layer context) {
	}

	@Override
	public Class<? extends Layer> selfClass() {
		return MockLayer.class;
	}
}