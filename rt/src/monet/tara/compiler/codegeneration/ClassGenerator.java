package monet.tara.compiler.codegeneration;

import monet.tara.compiler.codegeneration.intellij.CodeGenerator;
import monet.tara.compiler.core.CompilerConfiguration;
import monet.tara.compiler.core.errorcollection.TaraException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ClassGenerator extends CodeGenerator {
	private static final Logger LOG = Logger.getLogger(ClassGenerator.class.getName());
	private static final String SEP = PathManager.SEP;
	CompilerConfiguration conf;

	public ClassGenerator(CompilerConfiguration conf) {
		this.conf = conf;
	}

	public void generate(CompilerConfiguration configuration) throws TaraException {
		Runtime rt = Runtime.getRuntime();
		try {
			Process compileProcess = rt.exec(makeCompileCommand(getSourceFiles("java")));
			if (compileProcess.waitFor() == -1) return;
			printResult(compileProcess);
			Process jarProcess = rt.exec(makeJarCommand(configuration.getProject()), null, getOutPath());
			printResult(jarProcess);
		} catch (IOException | InterruptedException e) {
			LOG.severe(e.getMessage());
		}
	}

	private String makeJarCommand(String name) {
		List<String> cmd = JavaCommandHelper.buildJarCommandLine(conf.getTempDirectory().getAbsolutePath(), name, new String[]{});
		return JavaCommandHelper.join(cmd.toArray(new String[cmd.size()]), " ");
	}

	private File getTaraCoreFile() throws TaraException {
		return ResourceManager.getFile("/tara/core.jar");
	}

	private String makeCompileCommand(File[] sources) throws TaraException {
		try {
			List<String> cmd = JavaCommandHelper.buildJavaCompileCommandLine(sources,
				new String[]{getTaraCoreFile().getAbsolutePath() + SEP + "build" + SEP},
				null, conf.getTempDirectory().getAbsolutePath());
			return JavaCommandHelper.join(cmd.toArray(new String[cmd.size()]), " ");
		} catch (IOException e) {
			throw new TaraException("Error compiling plugin");
		}
	}

	private File[] getSourceFiles(String type) {
		File path = new File(conf.getTempDirectory() + SEP + conf.getProject());
		List<File> javaFiles = new ArrayList<>();
		getSourceFiles(path, javaFiles, type);
		return javaFiles.toArray(new File[javaFiles.size()]);
	}

	private void getSourceFiles(File path, List<File> javaFiles, String extension) {
		for (File file : path.listFiles())
			if (file.isDirectory())
				getSourceFiles(file, javaFiles, extension);
			else if (file.getName().endsWith("." + extension))
				javaFiles.add(file);
	}

	public File getOutPath() {
		String outPath = conf.getTempDirectory() + SEP + "out" + SEP;
		File file = new File(outPath);
		file.mkdirs();
		return file;
	}

}
