package tara.magritte.loaders;

import tara.magritte.Layer;
import tara.magritte.NativeCode;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static tara.magritte.loaders.NativeCodeLoader.nativeCodeOf;

@SuppressWarnings("unused")
public class FunctionLoader {

    public static <T> List<T> load(List<?> list, Layer layer, Class<T> functionClass) {
        return StringLoader.load(list).stream().map(f -> link(nativeCodeOf(f), layer, functionClass)).collect(toList());
    }

    public static <T> T load(Object object, Layer layer, Class<T> functionClass) {
        return link((NativeCode) object, layer, functionClass);
    }

    private static <T> T link(NativeCode nativeCode, Layer layer, Class<T> functionClass) {
        if (nativeCode == null) return null;
        NativeCode clone = nativeCodeOf(nativeCode.getClass());
        clone.self(morphContextOf(layer, clone.selfClass()));
        return (T) clone;
    }

    private static Layer morphContextOf(Layer layer, Class<? extends Layer> $Class) {
        return $Class.isAssignableFrom(layer.getClass()) ? layer :
                (layer._instance().is($Class) ? layer._instance().as($Class) :
                        layer._instance().ownerWith($Class));
    }

}
