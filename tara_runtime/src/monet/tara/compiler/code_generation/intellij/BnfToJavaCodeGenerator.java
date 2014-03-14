package monet.tara.compiler.code_generation.intellij;

import monet.tara.compiler.code_generation.JavaCommandHelper;
import monet.tara.compiler.core.CompilerConfiguration;
import monet.tara.compiler.core.error_collection.TaraException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class BnfToJavaCodeGenerator extends CodeGenerator {

	public static void bnfToJava(CompilerConfiguration configuration, File grammarFile) throws TaraException {
		Runtime rt = Runtime.getRuntime();
		try {
			Process compileProcess = rt.exec(makeJavaCommand(configuration.getTempDirectory().getAbsolutePath(), grammarFile));
			printResult(compileProcess);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			throw new TaraException("Error during plugin generation");
		}
	}

	private static String makeJavaCommand(String directory, File source) {
		String grammarKit = BnfToJavaCodeGenerator.class.getResource("/grammar-kit/grammar-kit.jar").getPath();
		String destiny = directory + SEP + SRC + SEP + IDE;
		String[] vParams = new String[]{"-DUser.dir=" + destiny};
		ArrayList<String> cmd = JavaCommandHelper.buildJavaJarExecuteCommandLine(grammarKit,
			vParams, new String[]{destiny, source.getAbsolutePath()});
		return JavaCommandHelper.join(cmd.toArray(new String[cmd.size()]), " ");
	}
}
