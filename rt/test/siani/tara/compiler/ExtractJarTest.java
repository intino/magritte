package siani.tara.compiler;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class ExtractJarTest {

	static String config = "/Users/oroncal/workspace/sandbox/";

	public static File extractFromTaraJar(String path, String jarName) {
		File jarExtracted = null;
		try {
			File jarFile = new File(path, "tara.jar");
			java.util.jar.JarFile jar = new java.util.jar.JarFile(jarFile);
			java.util.Enumeration enumEntries = jar.entries();
			while (enumEntries.hasMoreElements()) {
				java.util.jar.JarEntry file = (java.util.jar.JarEntry) enumEntries.nextElement();
				if (file.getName().endsWith(jarName)) {
					jarExtracted = new File(config + file.getName());
					jarExtracted.deleteOnExit();
					java.io.InputStream is = jar.getInputStream(file);
					java.io.FileOutputStream fos = new java.io.FileOutputStream(jarExtracted);
					while (is.available() > 0) fos.write(is.read());
					fos.close();
					is.close();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jarExtracted;
	}

	@Test
	public void testName() throws Exception {
		String path = "/Users/oroncal/workspace/sandbox/plugins/tara/lib/";
		File file = ExtractJarTest.extractFromTaraJar(path, "psi.jar");
		Assert.assertTrue(file.exists());


	}
}
