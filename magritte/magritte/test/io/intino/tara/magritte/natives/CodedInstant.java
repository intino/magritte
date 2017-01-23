package io.intino.tara.magritte.natives;

import io.intino.tara.magritte.Expression;
import io.intino.tara.magritte.Layer;
import io.intino.tara.magritte.NativeCode;
import io.intino.tara.magritte.layers.MockLayer;

import java.time.Instant;

@SuppressWarnings("unused")
public class CodedInstant implements NativeCode, Expression<Instant> {

    @Override
    public Instant value() {
        return Instant.parse("1987-09-17T04:00:00Z");
    }

    @Override
    public void self(Layer context) {
    }

    @Override
    public Class<? extends Layer> selfClass() {
        return MockLayer.class;
    }
}