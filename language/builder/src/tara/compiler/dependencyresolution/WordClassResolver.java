package tara.compiler.dependencyresolution;

import tara.compiler.codegeneration.JavaCompiler;
import tara.compiler.core.errorcollection.TaraException;
import tara.language.model.Variable;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

import static tara.compiler.codegeneration.FileSystemUtils.removeDir;

public class WordClassResolver {
	private final Variable variable;
	private final File wordsPath;

	public WordClassResolver(Variable variable, File wordsPath) {
		this.variable = variable;
		this.wordsPath = wordsPath;
	}

	public String[] collectAllowedValues() throws TaraException {
		File wordClass = new File(wordsPath, variable.contract() + ".java");
		final File temp = compile(wordClass);
		Class enumClass = loadClass(temp);
		if (enumClass == null) return new String[0];
		final String[] enums = getEnums(enumClass);
		removeDir(new File(temp, wordsPath.getParentFile().getName()));
		return enums;
	}

	public File compile(File wordClass) throws TaraException {
		final File destiny = new File(System.getProperty("java.io.tmpdir"));
		try {
			JavaCompiler.compile(wordClass, "", destiny);
		} catch (TaraException | IOException | InterruptedException e) {
			throw new TaraException("Impossible to compile " + wordClass.getAbsolutePath());
		}
		return destiny;
	}

	private Class loadClass(File destiny) throws TaraException {
		try {
			ClassLoader cl = new URLClassLoader(new URL[]{destiny.toURI().toURL()});
			final String name = wordsPath.getParentFile().getName();
			return cl.loadClass(name + ".words." + variable.contract());
		} catch (MalformedURLException | ClassNotFoundException ignored) {
			throw new TaraException("Impossible to load " + destiny.getAbsolutePath());
		}
	}

	private String[] getEnums(Class enumClass) {
		List<String> enums = new ArrayList<>();
		for (Field field : enumClass.getDeclaredFields())
			if (field.isEnumConstant()) enums.add(field.getName());
		return enums.toArray(new String[enums.size()]);
	}
}
