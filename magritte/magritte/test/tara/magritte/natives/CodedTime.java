package tara.magritte.natives;

import tara.magritte.Expression;
import tara.magritte.Layer;
import tara.magritte.NativeCode;
import tara.magritte.layers.MockLayer;

import java.time.LocalTime;

public class CodedTime implements NativeCode, Expression<LocalTime> {

	private MockLayer self;

	@Override
	public LocalTime value() {
		return LocalTime.of(4, 0);
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