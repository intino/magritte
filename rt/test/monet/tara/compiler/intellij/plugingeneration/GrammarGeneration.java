package monet.tara.compiler.intellij.plugingeneration;

import monet.tara.compiler.core.errorcollection.TaraException;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class GrammarGeneration {
	File[] projectFiles;

	@Before
	public void setUp() throws Exception {
		projectFiles = new File[]{};
	}

	@Test
	public void chargeLibsTest() throws Exception {
		String jar = "markdown4j-2.2.jar";
		String path = "file:/home/oroncal/-IntelliJIdea13/config/plugins/tara/lib/tara.jar!markdown4j-2.2.jar";
		if (path.contains("!")) {
			path = path.substring(0, path.indexOf('!')).replace("file:", "");
			if (path.contains(":")) path = path.substring(0);
			path = path.substring(0, path.lastIndexOf('/') + 1) + jar;
			path = new File(path).getAbsolutePath();
			System.out.println(path);
			if (!new File(path).exists()) throw new TaraException("Libs not found");
		}
		System.out.println(path);
	}


}
