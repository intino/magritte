package monet.tara.compiler.code_generation.intellij;

import monet.tara.compiler.code_generation.JavaCommandHelper;
import monet.tara.compiler.core.CompilerConfiguration;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;

public class PluginCompiler extends CodeGenerator {

	private static CompilerConfiguration configuration;

	public static void generateClasses(CompilerConfiguration configuration) {
		Runtime rt = Runtime.getRuntime();
		try {
			PluginCompiler.configuration = configuration;
			Process compileProcess = rt.exec(makeCompileCommand(getSources()));
			if (compileProcess.waitFor() == -1) return;
			printResult(compileProcess);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static File[] getSources() {
		return FileSystemUtils.listFiles(configuration.getTempDirectory() + SEP + SRC + SEP, new FileFilter() {
			@Override
			public boolean accept(File file) {
				return !file.getName().startsWith(".") && (file.getName().endsWith(".java") | file.isDirectory());
			}
		});
	}

	private static String makeCompileCommand(File[] sources) {
		ArrayList<String> cmd = JavaCommandHelper.buildJavaCompileCommandLine(sources, new String[]{},
			new String[]{}, configuration.getTempDirectory().getAbsolutePath());
		return JavaCommandHelper.join(cmd.toArray(new String[cmd.size()]), " ");
	}
}
