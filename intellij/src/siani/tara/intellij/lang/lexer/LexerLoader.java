package siani.tara.intellij.lang.lexer;

import com.intellij.openapi.application.PathManager;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class LexerLoader extends ClassLoader {

	public LexerLoader(ClassLoader parent) {
		super(parent);
	}

	private Class<?> getClass(String name)
		throws ClassNotFoundException {
		String file = name.replace('.', File.separatorChar) + ".class";
		byte[] b;
		try {
			b = loadClassData(file);
			Class<?> c = defineClass(name, b, 0, b.length);
			resolveClass(c);
			return c;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Class<?> loadClass(String name) throws ClassNotFoundException {
		System.out.println("loading class '" + name + "'");
		Class<?> aClass = getClass(name);
		return (aClass != null) ? aClass : super.loadClass(name);
	}

	private byte[] loadClassData(String name) throws IOException {
		// Opening the file
		String basePath = PathManager.getPluginsPath() + File.separator + "classes";
		FileInputStream stream = new FileInputStream(new File(basePath, name));
		int size = stream.available();
		byte buff[] = new byte[size];
		DataInputStream in = new DataInputStream(stream);
		// Reading the binary data
		in.readFully(buff);
		in.close();
		return buff;
	}

}
