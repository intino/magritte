package io.intino.tara.magritte.loaders;

import io.intino.tara.magritte.Layer;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static io.intino.tara.magritte.loaders.ListProcessor.process;

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
