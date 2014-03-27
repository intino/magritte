package monet.tara.compiler.codegeneration.intellij;

import monet.tara.compiler.codegeneration.JavaCommandHelper;
import monet.tara.compiler.codegeneration.PathManager;
import monet.tara.compiler.codegeneration.ResourceManager;
import monet.tara.compiler.core.CompilerConfiguration;
import monet.tara.compiler.core.errorcollection.TaraException;

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

	public static void generateClasses(CompilerConfiguration configuration) throws TaraException {
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

	private static String[] makeCompileCommand(File[] sources) throws TaraException {
		String sep = PathManager.SEP;
		try {
			List<String> cmd = JavaCommandHelper.buildJavaCompileCommandLine(sources, getClassPath(),
				new String[]{"-encoding", System.getProperty("file.encoding")},
				PathManager.getBuildIdeDir(conf.getTempDirectory()) + conf.getProject() + sep + LIB + sep + conf.getProject());
			return cmd.toArray(new String[cmd.size()]);
		} catch (IOException e) {
			throw new TaraException("Error compiling plugin");
		}
	}

	public static String[] getClassPath() throws TaraException {
		List<String> jarNames = new ArrayList<>();
		jarNames.add(conf.getIdeaHome() + "*");
		jarNames.add(ResourceManager.get("markdown4j-2.2.jar"));
		jarNames.add(ResourceManager.get("commons-email-1.3.2.jar"));
		jarNames.add(ResourceManager.get("javax.mail.jar"));
		return jarNames.toArray(new String[jarNames.size()]);
	}
}
