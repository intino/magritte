package io.intino.magritte.framework.natives;

import io.intino.magritte.framework.Expression;
import io.intino.magritte.framework.Layer;
import io.intino.magritte.framework.NativeCode;
import io.intino.magritte.framework.layers.MockLayer;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

@SuppressWarnings("unused")
public class CodedResource implements NativeCode, Expression<URL> {

	@Override
	public URL value() {
		try {
			return new File("test-res/oldFile").toURI().toURL();
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void self(Layer context) {
	}

	@Override
	public Class<? extends Layer> selfClass() {
		return MockLayer.class;
	}
}