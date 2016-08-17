package tara.magritte.natives;

import tara.magritte.Expression;
import tara.magritte.Layer;
import tara.magritte.NativeCode;
import tara.magritte.layers.MockLayer;

import java.time.LocalDateTime;

public class CodedDate implements NativeCode, Expression<LocalDateTime> {

	private MockLayer self;

	@Override
	public LocalDateTime value() {
		return LocalDateTime.of(1987, 9, 17, 4, 0);
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