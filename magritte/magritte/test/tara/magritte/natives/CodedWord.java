package tara.magritte.natives;

import tara.magritte.Expression;
import tara.magritte.Layer;
import tara.magritte.NativeCode;
import tara.magritte.layers.MockLayer;
import tara.magritte.loaders.WordLoaderTest;

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