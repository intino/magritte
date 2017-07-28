package io.intino.tara.magritte.loaders;

import io.intino.tara.magritte.Layer;
import io.intino.tara.magritte.NativeCode;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static io.intino.tara.magritte.loaders.NativeCodeLoader.nativeCodeOf;

@SuppressWarnings("unused")
public class FunctionLoader {

    public static <T> List<T> load(List<?> list, Layer layer, Class<T> functionClass) {
        return list.stream().map(f -> link(NativeCodeLoader.nativeCodeOf((String) f), layer, functionClass)).collect(toList());
    }

    public static <T> T load(Object object, Layer layer, Class<T> functionClass) {
        return link((NativeCode) object, layer, functionClass);
    }

    static <T> T link(NativeCode nativeCode, Layer layer, Class<T> functionClass) {
        if (nativeCode == null) return null;
        NativeCode clone = nativeCodeOf(nativeCode.getClass());
        clone.self(morphContextOf(layer, clone.selfClass()));
        return (T) clone;
    }

    private static Layer morphContextOf(Layer layer, Class<? extends Layer> $Class) {
        return $Class.isAssignableFrom(layer.getClass()) ? layer :
            (layer.core$().is($Class) ? layer.a$($Class) :
                layer.core$().ownerAs($Class));
    }

}
