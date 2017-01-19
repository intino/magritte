package io.intino.tara.magritte.loaders;

import io.intino.tara.magritte.Layer;
import io.intino.tara.magritte.types.ResX;

import java.net.URL;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static io.intino.tara.magritte.loaders.ListProcessor.process;

@SuppressWarnings("unused")
public class ResXLoader {

	public static List<ResX> load(List<?> list, Layer layer) {
		return list.stream().map((path) -> loadResource((String) path, layer)).collect(toList());
	}

	private static ResX loadResource(String path, Layer layer) {
		Object resourceObject = process((Object) path, layer);
		return resourceObject instanceof ResX ? (ResX) resourceObject : layer.graph().loadResource(path);
	}

}
