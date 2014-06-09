package siani.tara.compiler.codegeneration;

import siani.tara.compiler.core.errorcollection.TaraException;

import java.io.File;
import java.io.InputStream;
import java.net.URL;


public class ResourceManager {

	private ResourceManager() {
	}

	public static String get(String resource) throws TaraException {
		String query = (!resource.startsWith("/")) ? "/" + resource : resource;
		URL url = ResourceManager.class.getResource(query);
		if (url != null) {
			File file = new File(url.getFile());
			return file.exists() ? file.getAbsolutePath() : url.getFile();
		} else throw new TaraException("Resource not found: " + query);
	}

	public static InputStream getStream(String resource) throws TaraException {
		String query = (!resource.startsWith("/")) ? "/" + resource : resource;
		InputStream stream = ResourceManager.class.getResourceAsStream(query);
		if (stream != null) {
			return stream;
		} else throw new TaraException("Resource not found: " + query);
	}

	public static File getFile(String resource) throws TaraException {
		URL url = ResourceManager.class.getClassLoader().getResource(resource);
		if (url != null) {
			File file = new File(url.getFile());
			return file.exists() ? file : null;
		}
		throw new TaraException("Resource file not found: " + resource);

	}
}