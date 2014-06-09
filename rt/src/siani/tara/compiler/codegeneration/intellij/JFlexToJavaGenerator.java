package siani.tara.compiler.codegeneration.intellij;

import siani.tara.compiler.codegeneration.JavaCommandHelper;
import siani.tara.compiler.codegeneration.PathManager;
import siani.tara.compiler.codegeneration.ResourceManager;
import siani.tara.compiler.core.errorcollection.TaraException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystemException;
import java.util.List;
import java.util.logging.Logger;

public class JFlexToJavaGenerator extends CodeGenerator {

	private static final String JFLEX_JAR = "JFlex.jar";
	private static final String IDEA_FLEX_SKELETON = "idea-flex.skeleton";
	private static final Logger LOG = Logger.getLogger(JFlexToJavaGenerator.class.getName());

	public static void jFlexToJava(File tempDir, File lexFile) throws TaraException {
		String[] flex = getFlexLibraries(tempDir);
		Runtime rt = Runtime.getRuntime();
		try {
			Process jflexToJavaProcess = rt.exec(makeJavaCommand(tempDir, lexFile, flex));
			printResult(jflexToJavaProcess);
		} catch (IOException | InterruptedException e) {
			LOG.severe(e.getMessage());
			throw new TaraException("Error during plugin generation");
		}
	}

	private static String makeJavaCommand(File tempDir, File lexFile, String[] jflex) {
		String destiny = PathManager.getSrcIdeDir(tempDir);
		String[] vParams = new String[]{
			"-Xmx512m",
			"-DUser.dir=" + destiny};
		String[] pParams = new String[]{
			"-sliceandcharat",
			"-skel " + jflex[1],
			"-d " + lexFile.getParentFile().getAbsolutePath(),
			lexFile.getAbsolutePath()
		};
		List<String> cmd = JavaCommandHelper.buildJavaJarExecuteCommandLine(jflex[0], vParams, pParams);
		return JavaCommandHelper.join(cmd.toArray(new String[cmd.size()]), " ");
	}

	private static String[] getFlexLibraries(File tempDir) throws TaraException {
		InputStream grammarStream = ResourceManager.getStream(JFLEX_JAR);
		InputStream psiStream = ResourceManager.getStream(IDEA_FLEX_SKELETON);
		if (grammarStream != null && psiStream != null)
			try {
				File jflex = new File(tempDir, JFLEX_JAR);
				File skeleton = new File(tempDir, IDEA_FLEX_SKELETON);
				FileSystemUtils.copyFile(grammarStream, jflex);
				FileSystemUtils.copyFile(psiStream, skeleton);
				jflex.deleteOnExit();
				skeleton.deleteOnExit();
				return new String[]{jflex.getAbsolutePath(), skeleton.getAbsolutePath()};
			} catch (FileSystemException e) {
				LOG.severe(e.getMessage());
				throw new TaraException("JFlex not found");
			}
		else {
			File jflex = ResourceManager.getFile(JFLEX_JAR);
			File skeleton = ResourceManager.getFile(IDEA_FLEX_SKELETON);
			if (jflex == null | skeleton == null) throw new TaraException("JFlex not found");
			return new String[]{jflex.getAbsolutePath(), skeleton.getAbsolutePath()};
		}


	}

}
