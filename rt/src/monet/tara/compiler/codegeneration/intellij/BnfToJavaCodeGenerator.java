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
import java.io.InputStream;
import java.nio.file.FileSystemException;
import java.util.List;
import java.util.logging.Logger;

public class BnfToJavaCodeGenerator extends CodeGenerator {

	public static final String GRAMMAR_KIT_JAR = "grammar-kit.jar";
	public static final String LIGHT_PSI_ALL_JAR = "light-psi-all.jar";
	private static final Logger LOG = Logger.getLogger(BnfToJavaCodeGenerator.class.getName());

	public static void bnfToJava(CompilerConfiguration configuration, File grammarFile) throws TaraException {
		String[] grammarKit = getGrammarLibraries(configuration);
		Runtime rt = Runtime.getRuntime();
		try {
			Process compileProcess = rt.exec(makeJavaCommand(grammarKit, configuration.getTempDirectory(), grammarFile));
			printResult(compileProcess);
			fixTypes(configuration.getTempDirectory(), configuration.getProject());
		} catch (IOException | InterruptedException e) {
			LOG.severe(e.getMessage());
			throw new TaraException("Error during plugin generation");
		}
	}

	private static String[] getGrammarLibraries(CompilerConfiguration configuration) throws TaraException {
		InputStream grammarStream = ResourceManager.getStream(GRAMMAR_KIT_JAR);
		InputStream psiStream = ResourceManager.getStream(LIGHT_PSI_ALL_JAR);
		if (grammarStream != null && psiStream != null)
			try {
				File grammar = new File(configuration.getTempDirectory(), GRAMMAR_KIT_JAR);
				File psi = new File(configuration.getTempDirectory(), LIGHT_PSI_ALL_JAR);
				grammar.deleteOnExit();
				psi.deleteOnExit();
				FileSystemUtils.copyFile(grammarStream, grammar);
				FileSystemUtils.copyFile(psiStream, psi);
				return new String[]{grammar.getAbsolutePath(), psi.getAbsolutePath()};
			} catch (FileSystemException e) {
				LOG.severe(e.getMessage());
				throw new TaraException("Error during plugin generation");
			}
		else {
			String grammarFile = ResourceManager.get(GRAMMAR_KIT_JAR);
			String psiFile = ResourceManager.get(LIGHT_PSI_ALL_JAR);
			if (grammarFile != null && psiFile != null) return new String[]{grammarFile, psiFile};
		}
		throw new TaraException("Grammar-kit not found not found");
	}

	private static String makeJavaCommand(String[] jars, File tempDir, File source) throws TaraException {
		String destiny = PathManager.getSrcIdeDir(tempDir);
		String[] vParams = new String[]{"-DUser.dir=" + destiny};
		List<String> cmd = JavaCommandHelper.buildJavaJarExecuteCommandLine(jars[0],
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
			int index = fileContent.indexOf("\n", fileContent.indexOf("\n") + 1);
			fileContent = fileContent.substring(0, index) + "\nimport com.intellij.psi.TokenType;\n" + fileContent.substring(index);
			FileSystemUtils.writeFile(typesFile, fileContent);
		} catch (FileSystemException e) {
			LOG.severe(e.getMessage());
			throw new TaraException("Error during plugin generation");
		}
	}

}
