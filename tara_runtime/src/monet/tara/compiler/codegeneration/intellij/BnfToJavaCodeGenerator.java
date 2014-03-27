package monet.tara.compiler.codegeneration.intellij;

import monet.tara.compiler.codegeneration.JavaCommandHelper;
import monet.tara.compiler.codegeneration.PathManager;
import monet.tara.compiler.codegeneration.ResourceManager;
import monet.tara.compiler.codegeneration.render.RenderUtils;
import monet.tara.compiler.codegeneration.render.TemplateFactory;
import monet.tara.compiler.core.CompilerConfiguration;
import monet.tara.compiler.core.errorcollection.TaraException;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.util.List;
import java.util.logging.Logger;

public class BnfToJavaCodeGenerator extends CodeGenerator {

	private static final Logger LOG = Logger.getLogger(BnfToJavaCodeGenerator.class.getName());

	public static void bnfToJava(CompilerConfiguration configuration, File grammarFile) throws TaraException {
		Runtime rt = Runtime.getRuntime();
		try {
			Process compileProcess = rt.exec(makeJavaCommand(configuration.getTempDirectory(), grammarFile));
			fixTypes(configuration.getTempDirectory(), configuration.getProject());
			printResult(compileProcess);
		} catch (IOException | InterruptedException e) {
			LOG.severe(e.getMessage());
			throw new TaraException("Error during plugin generation");
		}
	}

	private static String makeJavaCommand(File tempDir, File source) throws TaraException {
		String grammarKit = ResourceManager.get("grammar-kit.jar");
		String destiny = PathManager.getSrcIdeDir(tempDir);
		String[] vParams = new String[]{"-DUser.dir=" + destiny};
		List<String> cmd = JavaCommandHelper.buildJavaJarExecuteCommandLine(grammarKit,
			vParams, new String[]{destiny, source.getAbsolutePath()});
		return JavaCommandHelper.join(cmd.toArray(new String[cmd.size()]), " ");
	}

	public static void fixTypes(File tempDir, String project) throws TaraException {
		try {
			String sep = PathManager.SEP;
			String typesFile = PathManager.getSrcDir(tempDir) +
				TemplateFactory.getTemplate("Types.java")
					.replace("/tpl", "")
					.replace("-", RenderUtils.toProperCase(project))
					.replace("/tara/", sep + project.toLowerCase() + sep).replaceAll("/", "\\" + sep);
			String fileContent = FileSystemUtils.readFile(typesFile);
			fileContent = fileContent.replace("new " + RenderUtils.toProperCase(project) + "TokenType(\"NEW_LINE_INDENT\");", "TokenType.NEW_LINE_INDENT;");
			FileSystemUtils.writeFile(typesFile, fileContent);
		} catch (FileSystemException e) {
			LOG.severe(e.getMessage());
			throw new TaraException("Error during plugin generation");
		}
	}

}
