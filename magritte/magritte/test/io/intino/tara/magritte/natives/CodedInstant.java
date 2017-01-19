package io.intino.tara.magritte.natives;

import io.intino.tara.magritte.Expression;
import io.intino.tara.magritte.Layer;
import io.intino.tara.magritte.NativeCode;
import io.intino.tara.magritte.layers.MockLayer;
import io.intino.tara.magritte.types.InstantX;

@SuppressWarnings("unused")
public class CodedInstant implements NativeCode, Expression<InstantX> {

    @Override
    public InstantX value() {
        return InstantX.parse("1987-09-17T04:00:00Z");
    }

    @Override
    public void self(Layer context) {
    }

    @Override
    public Class<? extends Layer> selfClass() {
        return MockLayer.class;
    }
}