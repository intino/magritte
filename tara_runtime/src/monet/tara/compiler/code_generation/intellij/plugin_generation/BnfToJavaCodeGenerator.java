package monet.tara.compiler.code_generation.intellij.plugin_generation;

import com.intellij.execution.ui.RunContentDescriptor;
import monet.tara.compiler.code_generation.JavaCommandHelper;
import monet.tara.compiler.core.CompilerConfiguration;
import monet.tara.compiler.core.error_collection.StreamWrapper;
import monet.tara.compiler.core.error_collection.TaraException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class BnfToJavaCodeGenerator extends CodeGenerator {
	RunContentDescriptor console;

	//java -jar grammar-kit.jar ./ Tara.bnf
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
		String grammarKit = BnfToJavaCodeGenerator.class.getResource("grammar-kit.jar").getPath();
		ArrayList<String> cmd = JavaCommandHelper.buildJavaJarExecuteCommandLine(grammarKit,
			null, new String[]{directory, source.getAbsolutePath()});
		return JavaCommandHelper.join(cmd.toArray(new String[cmd.size()]), " ");
	}

	private static void printResult(Process process) throws InterruptedException {
		StreamWrapper error, output;
		error = StreamWrapper.getStreamWrapper(process.getErrorStream(), "ERROR");
		output = StreamWrapper.getStreamWrapper(process.getInputStream(), "OUTPUT");
		error.start();
		output.start();
		error.join(3000);
		output.join(3000);
		if (!output.getMessage().equals(""))
			System.err.println("Output: " + output.getMessage());
		if (!error.getMessage().equals(""))
			System.err.println("Error: " + error.getMessage());
	}
}
