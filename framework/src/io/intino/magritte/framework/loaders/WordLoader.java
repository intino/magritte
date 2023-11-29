package io.intino.magritte.framework.loaders;

import io.intino.magritte.framework.Layer;

import java.util.List;

import static io.intino.magritte.framework.loaders.ListProcessor.process;
import static java.util.stream.Collectors.toList;

@SuppressWarnings("unused")
public class WordLoader {

	public static <T extends Enum<T>> List<T> load(List<?> list, Class<T> aClass, Layer layer) {
		return list.stream().map(w -> processWord(aClass, (String) w, layer)).collect(toList());
	}

	private static <T extends Enum<T>> T processWord(Class<T> aClass, String word, Layer layer) {
		Object wordObject = process((Object) word, layer);
		return aClass.isInstance(wordObject) ? (T) wordObject : Enum.valueOf(aClass, word);
	}

}
