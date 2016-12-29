package io.intino.tara.magritte;

public interface NativeCode extends Cloneable {
    void self(Layer context);

    Class<? extends Layer> selfClass();
}
