package siani.tara.intellij.lang.lexer;

import com.intellij.openapi.application.PathManager;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static java.io.File.separator;

public class LexerLoader extends ClassLoader {

	private static final Map<String, Class<?>> loadedClasses = new HashMap<>();
	private final String BASE_PATH;

	public LexerLoader(ClassLoader parent, String projectName) {
		super(parent);
		BASE_PATH = PathManager.getPluginsPath() + separator + "tara" + separator + "classes" + separator + projectName;
	}

	private Class<?> getClass(String name) throws ClassNotFoundException {
		String file = name.replace('.', File.separatorChar) + ".class";
		byte[] b;
		try {
			b = loadClassData(file);
			Class<?> c = defineClass(name, b, 0, b.length);
			resolveClass(c);
			return c;
		} catch (IOException e) {
			return null;
		}
	}

	public Class<?> loadClass(String name) throws ClassNotFoundException {
		if (!haveToReload()) {
			if (findClassLoaded(name))
				return loadedClasses.get(name);
		}
		try {
			return super.loadClass(name);
		} catch (ClassNotFoundException ignored) {
		}
		Class<?> aClass = getClass(name);
		if (aClass == null) throw new ClassNotFoundException(name);
		loadedClasses.put(name, aClass);
		return aClass;
	}

	private boolean haveToReload() {
		File reload = new File(BASE_PATH, ".reload");
		if (reload.exists()) {
			reload.delete();
			return true;
		}
		return false;
	}

	private boolean findClassLoaded(String name) {
		return loadedClasses.containsKey(name);
	}

	private byte[] loadClassData(String name) throws IOException {
		FileInputStream stream = new FileInputStream(new File(BASE_PATH, name));
		int size = stream.available();
		byte buff[] = new byte[size];
		DataInputStream in = new DataInputStream(stream);
		in.readFully(buff);
		in.close();
		return buff;
	}


}
