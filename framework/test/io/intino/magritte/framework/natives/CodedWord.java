package io.intino.magritte.framework.natives;

import io.intino.magritte.framework.Expression;
import io.intino.magritte.framework.Layer;
import io.intino.magritte.framework.NativeCode;
import io.intino.magritte.framework.layers.MockLayer;
import io.intino.magritte.framework.loaders.WordLoaderTest;

@SuppressWarnings("unused")
public class CodedWord implements NativeCode, Expression<WordLoaderTest.Modes> {

	@Override
	public WordLoaderTest.Modes value() {
		return WordLoaderTest.Modes.On;
	}

	@Override
	public void self(Layer context) {
	}

	@Override
	public Class<? extends Layer> selfClass() {
		return MockLayer.class;
	}
}