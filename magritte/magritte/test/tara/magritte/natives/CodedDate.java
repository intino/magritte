package tara.magritte.natives;

import tara.magritte.Date;
import tara.magritte.Expression;
import tara.magritte.Layer;
import tara.magritte.NativeCode;
import tara.magritte.layers.MockLayer;

import java.time.LocalDateTime;

public class CodedDate implements NativeCode, Expression<Date> {

	private MockLayer self;

	@Override
	public Date value() {
		return Date.parse("1987-09-17 04:00");
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