package monet.tara.compiler.code_generation.intellij;

import monet.tara.compiler.code_generation.JavaCommandHelper;
import monet.tara.compiler.core.CompilerConfiguration;
import monet.tara.compiler.core.error_collection.TaraException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class JFlexToJavaGenerator extends CodeGenerator {
	public static void jFlexToJava(CompilerConfiguration configuration, File lexFile) throws TaraException {
		System.out.println("JFlex to java Generation");
		File[] jflex = getJFlexLibFiles();
		Runtime rt = Runtime.getRuntime();
		try {
			Process jflexToJavaProcess = rt.exec(makeJavaCommand(configuration.getTempDirectory().getAbsolutePath(), lexFile, jflex));
			printResult(jflexToJavaProcess);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			throw new TaraException("Error during plugin generation");
		}
	}

	private static String makeJavaCommand(String directory, File lexFile, File[] jflex) {
		String destiny = directory + SEP + SRC + SEP + IDE + SRC + SEP;
		String[] vParams = new String[]{
			"-Xmx512m",
			"-DUser.dir=" + destiny};
		String[] pParams = new String[]{
			"-sliceandcharat",
			"-skel " + jflex[1].getAbsolutePath(),
			"-d " + lexFile.getParentFile().getAbsolutePath(),
			lexFile.getAbsolutePath()
		};
		ArrayList<String> cmd = JavaCommandHelper.buildJavaJarExecuteCommandLine(jflex[0].getAbsolutePath(), vParams, pParams);
		return JavaCommandHelper.join(cmd.toArray(new String[cmd.size()]), " ");
	}

	private static File[] getJFlexLibFiles() {
		return new File[]{new File(JFlexToJavaGenerator.class.getResource("/flex/JFlex.jar").getPath()),
			new File(JFlexToJavaGenerator.class.getClass().getResource("/flex/idea-flex.skeleton").getPath())};
	}



}
