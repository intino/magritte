package monet.tara.compiler.code_generation.intellij;

import monet.tara.compiler.code_generation.JavaCommandHelper;
import monet.tara.compiler.code_generation.render.RenderUtils;
import monet.tara.compiler.code_generation.render.TemplateFactory;
import monet.tara.compiler.core.CompilerConfiguration;
import monet.tara.compiler.core.error_collection.TaraException;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.util.ArrayList;

public class JFlexToJavaGenerator extends CodeGenerator {
	public static void jFlexToJava(CompilerConfiguration configuration, File lexFile) throws TaraException {
		System.out.println("JFlex to java Generation");
		File[] jflex = getJLexerFiles();
		Runtime rt = Runtime.getRuntime();
		try {
			Process compileProcess = rt.exec(makeJavaCommand(configuration.getTempDirectory().getAbsolutePath(), lexFile, jflex));
			fixTypes(configuration.getTempDirectory(), configuration.getProject());
			printResult(compileProcess);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			throw new TaraException("Error during plugin generation");
		}
	}

	private static String makeJavaCommand(String directory, File lexFile, File[] jflex) {
		String destiny = directory + SEP + SRC + SEP + IDE;
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

	private static File[] getJLexerFiles() {
		return new File[]{new File(JFlexToJavaGenerator.class.getResource("/flex/JFlex.jar").getPath()),
			new File(JFlexToJavaGenerator.class.getClass().getResource("/flex/idea-flex.skeleton").getPath())};
	}

	public static void fixTypes(File tempPath, String project) {
		try {
			String typesFile = tempPath.getAbsolutePath() + SEP + SRC + SEP +
				TemplateFactory.getTemplate("Types.java").replace("-", RenderUtils.toProperCase(project));
			String fileContent = FileSystemUtils.readFile(typesFile);
			fileContent.replace("new " + project + "TokenType(\"NEW_LINE_INDENT\");", "TokenType.NEW_LINE_INDENT;");

		} catch (FileSystemException e) {
			e.printStackTrace();
		}
	}

}
