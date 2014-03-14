package monet.tara.compiler.code_generation.intellij;

import monet.tara.compiler.core.CompilerConfiguration;

import java.io.File;
import java.nio.file.FileSystemException;

public class PluginPackager extends CodeGenerator {


	public static void doPackage(CompilerConfiguration configuration) {
		File libPath = null;//LibManager.getLibraryPath();
		String buildPath = configuration.getTempDirectory() + SEP + BUILD + SEP + IDE + "lib";
		String srcPath = configuration.getTempDirectory() + SEP + SRC + SEP + IDE;
		FileSystemUtils.createDir(buildPath);
		compileTaraPluginSource(srcPath);
		try {
			FileSystemUtils.copyDir(libPath, new File(buildPath));
		} catch (FileSystemException e) {
			e.printStackTrace();
		}
	}

	private static void compileTaraPluginSource(String srcPath) {

	}

}

