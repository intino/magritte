package monet.tara.compiler.codegeneration;

import monet.tara.compiler.core.errorcollection.TaraException;

import java.io.File;
import java.net.URL;


public class ResourceManager {

	public static String get(String resource) throws TaraException {
		URL url = ResourceManager.class.getClassLoader().getResource(resource);
		if (url != null) {
			File file = new File(url.getFile());
			return file.exists() ? file.getAbsolutePath() : null;
		} else throw new TaraException("Resource not found: " + resource);
	}

	public static File getFile(String resource) throws TaraException {
		URL url = ResourceManager.class.getClassLoader().getResource(resource);
		if (url != null) {
			File file = new File(url.getFile());
			return file.exists() ? file : null;
		}
		throw new TaraException("Resource not found: " + resource);

	}
}