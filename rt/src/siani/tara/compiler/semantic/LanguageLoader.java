package siani.tara.compiler.semantic;

import siani.tara.Language;
import siani.tara.TaraCompilerRunner;
import siani.tara.compiler.core.errorcollection.TaraException;
import siani.tara.dsls.Proteo;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.logging.Logger;

public class LanguageLoader {
	private static final Logger LOG = Logger.getLogger(LanguageLoader.class.getName());

	public static Language load(String name, String languagesDirectory) throws TaraException {
		if (name.equalsIgnoreCase("Proteo"))
			return new Proteo();
		File file = new File(languagesDirectory);
		try {
			ClassLoader cl = new URLClassLoader(new URL[]{file.toURI().toURL()}, TaraCompilerRunner.class.getClassLoader());
			Class cls = cl.loadClass("siani.tara.dsls." + name);
			return (Language) cls.newInstance();
		} catch (MalformedURLException | ClassNotFoundException e1) {
			LOG.info(e1.getMessage());
			return null;
		} catch (InstantiationException | IllegalAccessException e2) {
			throw new TaraException("Impossible to create a language instance based in " + name);
		}
	}
}
