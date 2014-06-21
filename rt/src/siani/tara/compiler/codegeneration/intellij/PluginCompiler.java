package siani.tara.compiler.codegeneration.intellij;

import siani.tara.compiler.codegeneration.JavaCommandHelper;
import siani.tara.compiler.codegeneration.PathManager;
import siani.tara.compiler.codegeneration.ResourceManager;
import siani.tara.compiler.core.CompilerConfiguration;
import siani.tara.compiler.core.errorcollection.TaraException;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class PluginCompiler extends CodeGenerator {
	private static final Logger LOG = Logger.getLogger(PluginCompiler.class.getName());
	private static final String SEP = PathManager.SEP;
	private static CompilerConfiguration conf;

	public static void generateClasses(CompilerConfiguration configuration) throws TaraException {
		Runtime rt = Runtime.getRuntime();
		try {
			PluginCompiler.conf = configuration;
			Process compileProcess = rt.exec(makeCompileCommand(getSources()));
			if (compileProcess.waitFor() == -1) throw new TaraException("Plugin Compilation failed");
			String errorMessage = printResult(compileProcess);
			if (isFatal(errorMessage)) throw new TaraException("Plugin Compilation failed");
		} catch (IOException | InterruptedException e) {
			LOG.severe(e.getLocalizedMessage());
		}
	}

	private static File[] getSources() {
		return FileSystemUtils.listFiles(PathManager.getSrcDir(conf.getTempDirectory()), new FileFilter() {
			@Override
			public boolean accept(File file) {
				return !file.getName().startsWith(".") && (file.getName().endsWith(".java") | file.isDirectory());
			}
		});
	}

	private static String[] makeCompileCommand(File[] sources) throws TaraException {
		try {
			List<String> cmd = JavaCommandHelper.buildJavaCompileCommandLine(sources, getClassPath(),
				new String[]{"-encoding", System.getProperty("file.encoding")},
				getBuildDirectory());
			return cmd.toArray(new String[cmd.size()]);
		} catch (IOException e) {
			LOG.severe(e.getMessage());
			throw new TaraException("Error compiling plugin");
		}
	}

	private static String getBuildDirectory() {
		return PathManager.getBuildIdeDir(conf.getTempDirectory()) + conf.getProject() + SEP;
	}

	private static String[] getClassPath() throws TaraException {
		List<String> jarNames = new ArrayList<>();
		jarNames.add(conf.getIdeaHome() + "*");
		jarNames.add(getJar("psi.jar"));
		return jarNames.toArray(new String[jarNames.size()]);
	}

	private static String getJar(String jar) throws TaraException {
		String path = ResourceManager.get(jar);
		if (path.contains("!")) {
			path = path.substring(0, path.indexOf('!')).replace("file:", "");
			if (path.contains(":")) path = path.substring(0);
			path = path.substring(0, path.lastIndexOf('/') + 1);
			File file = extractFromTaraJar(path, jar);
			path = file.getAbsolutePath();
			if (!file.exists()) throw new TaraException("Lib not found: " + path);
		}
		return path;
	}

	public static File extractFromTaraJar(String path, String jarName) {
		File jarExtracted = null;
		try {
			File jarFile = new File(path, "tara.jar");
			java.util.jar.JarFile jar = new java.util.jar.JarFile(jarFile);
			java.util.Enumeration enumEntries = jar.entries();
			while (enumEntries.hasMoreElements()) {
				java.util.jar.JarEntry file = (java.util.jar.JarEntry) enumEntries.nextElement();
				if (file.getName().endsWith(jarName)) {
					jarExtracted = new File(conf.getTempDirectory() + File.separator + file.getName());
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

	private static boolean isFatal(String errorMessage) {
		return errorMessage != null && !errorMessage.equals("") && !errorMessage.startsWith("Note");
	}
}
