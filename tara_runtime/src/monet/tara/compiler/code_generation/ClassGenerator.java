package monet.tara.compiler.code_generation;

import monet.tara.compiler.code_generation.intellij.CodeGenerator;
import monet.tara.compiler.core.CompilerConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ClassGenerator extends CodeGenerator {

	CompilerConfiguration configuration;

	public ClassGenerator(CompilerConfiguration configuration) {
		this.configuration = configuration;
	}

	public void generate(CompilerConfiguration configuration) {
		Runtime rt = Runtime.getRuntime();
		try {
			Process compileProcess = rt.exec(makeCompileCommand(getSourceFiles("java")));
			if (compileProcess.waitFor() == -1) return;
			printResult(compileProcess);
			Process jarProcess = rt.exec(makeJarCommand(configuration.getProject()), null, getOutPath());
			printResult(jarProcess);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	private String makeJarCommand(String name) {
		ArrayList<String> cmd = JavaCommandHelper.buildJarCommandLine(configuration.getTempDirectory().getAbsolutePath(), name, new String[]{});
		return JavaCommandHelper.join(cmd.toArray(new String[cmd.size()]), " ");
	}

	private File getTaraCoreFile() {
		return new File(getClass().getResource(SEP + "tara" + SEP + "core.jar").getPath());
	}

	private String makeCompileCommand(File[] sources) {
		ArrayList<String> cmd = JavaCommandHelper.buildJavaCompileCommandLine(sources,
			new String[]{getTaraCoreFile().getAbsolutePath() + SEP + "build" + SEP},
			null, configuration.getTempDirectory().getAbsolutePath());
		return JavaCommandHelper.join(cmd.toArray(new String[cmd.size()]), " ");
	}

	private File[] getSourceFiles(String type) {
		File path = new File(configuration.getTempDirectory() + SEP + configuration.getProject());
		ArrayList<File> javaFiles = new ArrayList<>();
		getSourceFiles(path, javaFiles, type);
		return javaFiles.toArray(new File[javaFiles.size()]);
	}

	private File[] getClassFiles() {
		File path = new File(configuration.getTempDirectory() + SEP + "build" + SEP);
		return path.listFiles();
	}

	private void getSourceFiles(File path, ArrayList<File> javaFiles, String extension) {
		for (File file : path.listFiles())
			if (file.isDirectory())
				getSourceFiles(file, javaFiles, extension);
			else if (file.getName().endsWith("." + extension))
				javaFiles.add(file);
	}

	public File getOutPath() {
		String outPath = configuration.getTempDirectory() + SEP + "out" + SEP;
		File file = new File(outPath);
		file.mkdirs();
		return file;
	}

}
