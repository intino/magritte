package monet.tara.compiler.rt;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.Map;

/**
 * Created by oroncal on 27/12/13.
 */
public class AstAwareResourceLoader {
	final Map<String, File> myClass2File;

	AstAwareResourceLoader(Map<String, File> class2File) {
		myClass2File = Collections.synchronizedMap(class2File);
	}

	public URL loadTaraSource(String className) throws MalformedURLException {
		if (className == null) return null;

		File file = getSourceFile(className);
		if (file != null && file.exists()) {
			return file.toURL();
		}

		file = new File(className);
		if (file.exists()) {
			return file.toURL();
		}

		return null;
	}

	File getSourceFile(String className) {
		return myClass2File.get(className);
	}
}
