package io.intino.tara.magritte.natives;

import io.intino.tara.magritte.layers.MockLayer;
import io.intino.tara.magritte.loaders.WordLoaderTest;
import io.intino.tara.magritte.Expression;
import io.intino.tara.magritte.Layer;
import io.intino.tara.magritte.NativeCode;

public class CodedWord implements NativeCode, Expression<WordLoaderTest.Modes> {

	private MockLayer self;

	@Override
	public WordLoaderTest.Modes value() {
		return WordLoaderTest.Modes.On;
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