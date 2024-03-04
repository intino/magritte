package io.intino.magritte.framework;

public interface NativeCode extends Cloneable {
	void self(Layer context);

	Class<? extends Layer> selfClass();
}
