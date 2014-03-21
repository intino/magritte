package monet.tara.compiler.code_generation.intellij;

import monet.tara.compiler.code_generation.JavaCommandHelper;
import monet.tara.compiler.code_generation.PathManager;
import monet.tara.compiler.code_generation.render.RenderUtils;
import monet.tara.compiler.code_generation.render.TemplateFactory;
import monet.tara.compiler.core.CompilerConfiguration;
import monet.tara.compiler.core.error_collection.TaraException;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.util.ArrayList;

public class BnfToJavaCodeGenerator extends CodeGenerator {

	public static void bnfToJava(CompilerConfiguration configuration, File grammarFile) throws TaraException {
		Runtime rt = Runtime.getRuntime();
		try {
			Process compileProcess = rt.exec(makeJavaCommand(configuration.getTempDirectory(), grammarFile));
			fixTypes(configuration.getTempDirectory(), configuration.getProject());
			printResult(compileProcess);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			throw new TaraException("Error during plugin generation");
		}
	}

	private static String makeJavaCommand(File tempDir, File source) {
		String grammarKit = BnfToJavaCodeGenerator.class.getResource("/grammar-kit.jar").getPath();
		String destiny = PathManager.getSrcIdeDir(tempDir);
		String[] vParams = new String[]{"-DUser.dir=" + destiny};
		ArrayList<String> cmd = JavaCommandHelper.buildJavaJarExecuteCommandLine(grammarKit,
			vParams, new String[]{destiny, source.getAbsolutePath()});
		return JavaCommandHelper.join(cmd.toArray(new String[cmd.size()]), " ");
	}

	public static void fixTypes(File tempDir, String project) {
		try {
			String SEP = PathManager.SEP;
			String typesFile = PathManager.getSrcDir(tempDir) +
				TemplateFactory.getTemplate("Types.java")
					.replace(SEP + "tpl", "")
					.replace("-", RenderUtils.toProperCase(project))
					.replace(SEP + "tara" + SEP, SEP + project.toLowerCase() + SEP);
			String fileContent = FileSystemUtils.readFile(typesFile);
			fileContent = fileContent.replace("new " + RenderUtils.toProperCase(project) + "TokenType(\"NEW_LINE_INDENT\");", "TokenType.NEW_LINE_INDENT;");
			FileSystemUtils.writeFile(typesFile, fileContent);
		} catch (FileSystemException e) {
			e.printStackTrace();
		}
	}

}
