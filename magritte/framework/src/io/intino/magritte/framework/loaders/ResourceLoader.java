package io.intino.magritte.framework.loaders;

import io.intino.magritte.framework.Layer;

import java.net.URL;
import java.util.List;

import static io.intino.magritte.framework.loaders.ListProcessor.process;
import static java.util.stream.Collectors.toList;

@SuppressWarnings("unused")
public class ResourceLoader {

	public static List<URL> load(List<?> list, Layer layer) {
		return list.stream().map((path) -> loadResource((String) path, layer)).collect(toList());
	}

	private static URL loadResource(String path, Layer layer) {
		Object resourceObject = process((Object) path, layer);
		return resourceObject instanceof URL ? (URL) resourceObject : layer.core$().graph().loadResource(path);
	}

}
