package io.intino.tara.magritte.natives;

import io.intino.tara.magritte.layers.MockLayer;
import io.intino.tara.magritte.Date;
import io.intino.tara.magritte.Expression;
import io.intino.tara.magritte.Layer;
import io.intino.tara.magritte.NativeCode;

@SuppressWarnings("unused")
public class CodedDate implements NativeCode, Expression<Date> {

	@Override
	public Date value() {
		return Date.parse("1987-09-17 04:00");
	}

	@Override
	public void self(Layer context) {
	}

	@Override
	public Class<? extends Layer> selfClass() {
		return MockLayer.class;
	}
}