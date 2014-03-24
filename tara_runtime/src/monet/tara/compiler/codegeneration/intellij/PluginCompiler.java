package monet.tara.compiler.codegeneration.intellij;

import monet.tara.compiler.codegeneration.JavaCommandHelper;
import monet.tara.compiler.codegeneration.PathManager;
import monet.tara.compiler.core.CompilerConfiguration;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class PluginCompiler extends CodeGenerator {

	public static final String LIB = "lib";
	private static final Logger LOG = Logger.getLogger(PluginCompiler.class.getName());
	private static CompilerConfiguration conf;

	public static void generateClasses(CompilerConfiguration configuration) {
		Runtime rt = Runtime.getRuntime();
		try {
			PluginCompiler.conf = configuration;
			Process compileProcess = rt.exec(makeCompileCommand(getSources()));
			if (compileProcess.waitFor() == -1) return;
			printResult(compileProcess);
		} catch (IOException | InterruptedException e) {
			LOG.severe(e.getMessage());
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

	private static String makeCompileCommand(File[] sources) {
		String SEP = PathManager.SEP;
		List<String> cmd = JavaCommandHelper.buildJavaCompileCommandLine(sources, getClassPath(),
			new String[]{"-encoding " + System.getProperty("file.encoding")},
			PathManager.getBuildIdeDir(conf.getTempDirectory()) + conf.getProject() + SEP + LIB + SEP + conf.getProject()
		);
		return JavaCommandHelper.join(cmd.toArray(new String[cmd.size()]), " ");
	}

	public static String[] getClassPath() {
		String libPath = conf.getIdeaHome();
		File[] jars = new File(libPath).listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				return pathname.getName().endsWith(".jar");
			}
		});
		List<String> jarNames = new ArrayList<>();
		for (File jar : jars) jarNames.add(jar.getAbsolutePath());
		jarNames.add(PluginCompiler.class.getResource("/markdown4j-2.2.jar").getPath());
		jarNames.add(PluginCompiler.class.getResource("/commons-email-1.3.2.jar").getPath());
		jarNames.add(PluginCompiler.class.getResource("/javax.mail.jar").getPath());
		return jarNames.toArray(new String[jarNames.size()]);
	}
}
