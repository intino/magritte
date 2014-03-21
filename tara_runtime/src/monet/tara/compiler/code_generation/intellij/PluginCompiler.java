package monet.tara.compiler.code_generation.intellij;

import monet.tara.compiler.code_generation.JavaCommandHelper;
import monet.tara.compiler.code_generation.PathManager;
import monet.tara.compiler.core.CompilerConfiguration;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;

public class PluginCompiler extends CodeGenerator {

	public static final String LIB = "lib";
	private static CompilerConfiguration conf;

	public static void generateClasses(CompilerConfiguration configuration) {
		Runtime rt = Runtime.getRuntime();
		try {
			PluginCompiler.conf = configuration;
			Process compileProcess = rt.exec(makeCompileCommand(getSources()));
			if (compileProcess.waitFor() == -1) return;
			printResult(compileProcess);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
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
		ArrayList<String> cmd = JavaCommandHelper.buildJavaCompileCommandLine(sources, getClassPath(),
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
		ArrayList<String> jarNames = new ArrayList<>();
		for (File jar : jars) jarNames.add(jar.getAbsolutePath());
		jarNames.add(PluginCompiler.class.getResource("/markdown4j-2.2.jar").getPath());
		jarNames.add(PluginCompiler.class.getResource("/commons-email-1.3.2.jar").getPath());
		jarNames.add(PluginCompiler.class.getResource("/javax.mail.jar").getPath());
		return jarNames.toArray(new String[jarNames.size()]);
	}
}
