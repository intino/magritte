package siani.tara.compiler.codegeneration.java;

import siani.tara.compiler.codegeneration.CodeGenerator;
import siani.tara.compiler.codegeneration.ResourceManager;
import siani.tara.compiler.core.CompilerConfiguration;
import siani.tara.compiler.core.errorcollection.TaraException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaCodeGenerator extends CodeGenerator {
	private static final Logger LOG = Logger.getLogger(JavaCodeGenerator.class.getName());
	private static final String SEP = File.separator;
	CompilerConfiguration conf;

	public JavaCodeGenerator(CompilerConfiguration conf) {
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
			LOG.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	private String makeJarCommand(String name) {
		List<String> cmd = JavaCommandHelper.buildJarCommandLine(conf.getOutDirectory().getAbsolutePath(), name, new String[]{});
		return JavaCommandHelper.join(cmd.toArray(new String[cmd.size()]), " ");
	}

	private File getTaraCoreFile() throws TaraException {
		return ResourceManager.getFile("/tara/core.jar");
	}

	private String makeCompileCommand(File[] sources) throws TaraException {
		try {
			List<String> cmd = JavaCommandHelper.buildJavaCompileCommandLine(sources,
				new String[]{getTaraCoreFile().getAbsolutePath() + SEP + "build" + SEP},
				null, conf.getOutDirectory().getAbsolutePath());
			return JavaCommandHelper.join(cmd.toArray(new String[cmd.size()]), " ");
		} catch (IOException e) {
			throw new TaraException("Error compiling plugin");
		}
	}

	private File[] getSourceFiles(String type) {
		File path = new File(conf.getOutDirectory() + SEP + conf.getProject());
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
		String outPath = conf.getOutDirectory() + SEP + "out" + SEP;
		File file = new File(outPath);
		file.mkdirs();
		return file;
	}

}
