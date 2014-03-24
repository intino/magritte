package monet.tara.compiler.codegeneration.intellij;

import monet.tara.compiler.codegeneration.JavaCommandHelper;
import monet.tara.compiler.codegeneration.PathManager;
import monet.tara.compiler.core.CompilerConfiguration;
import monet.tara.compiler.core.errorcollection.TaraException;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class JFlexToJavaGenerator extends CodeGenerator {

	private static final Logger LOG = Logger.getLogger(JFlexToJavaGenerator.class.getName());

	public static void jFlexToJava(CompilerConfiguration configuration, File lexFile) throws TaraException {
		LOG.info("JFlex to java Generation");
		File[] jflex = getJFlexLibFiles();
		Runtime rt = Runtime.getRuntime();
		try {
			Process jflexToJavaProcess = rt.exec(makeJavaCommand(configuration.getTempDirectory(), lexFile, jflex));
			printResult(jflexToJavaProcess);
		} catch (IOException | InterruptedException e) {
			LOG.severe(e.getMessage());
			throw new TaraException("Error during plugin generation");
		}
	}

	private static String makeJavaCommand(File tempDir, File lexFile, File[] jflex) {
		String destiny = PathManager.getSrcIdeDir(tempDir);
		String[] vParams = new String[]{
			"-Xmx512m",
			"-DUser.dir=" + destiny};
		String[] pParams = new String[]{
			"-sliceandcharat",
			"-skel " + jflex[1].getAbsolutePath(),
			"-d " + lexFile.getParentFile().getAbsolutePath(),
			lexFile.getAbsolutePath()
		};
		List<String> cmd = JavaCommandHelper.buildJavaJarExecuteCommandLine(jflex[0].getAbsolutePath(), vParams, pParams);
		return JavaCommandHelper.join(cmd.toArray(new String[cmd.size()]), " ");
	}

	private static File[] getJFlexLibFiles() {
		try {
			return new File[]{new File(JFlexToJavaGenerator.class.getResource("/JFlex.jar").getPath()),
				new File(JFlexToJavaGenerator.class.getResource("/idea-flex.skeleton").getPath())};
		} catch (NullPointerException e) {
			LOG.severe("JFlex not found in " + JFlexToJavaGenerator.class.getResource("/JFlex.jar").getPath());
			throw new NullPointerException();
		}
	}


}
