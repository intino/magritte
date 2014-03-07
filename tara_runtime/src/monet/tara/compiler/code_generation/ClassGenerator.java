package monet.tara.compiler.code_generation;

import monet.tara.compiler.core.CompilerConfiguration;
import monet.tara.compiler.core.JavaCommandHelper;
import monet.tara.compiler.core.error_collection.StreamWrapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ClassGenerator {

	CompilerConfiguration configuration;

	public ClassGenerator(CompilerConfiguration configuration) {
		this.configuration = configuration;
	}

	public int generate() {
		Runtime rt = Runtime.getRuntime();
		try {
			Process compileProcess = rt.exec(makeCompileCommand(getSourceFiles("java")));
			if (compileProcess.waitFor() == -1) return -1;
			printResult(compileProcess);
			Process jarProcess = rt.exec(makeJarCommand(configuration.getProject()), null, getOutPath());
			printResult(jarProcess);
			return jarProcess.waitFor();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return -1;
		}
	}

	private void printResult(Process process) throws InterruptedException {
		StreamWrapper error, output;
		error = StreamWrapper.getStreamWrapper(process.getErrorStream(), "ERROR");
		output = StreamWrapper.getStreamWrapper(process.getInputStream(), "OUTPUT");
		error.start();
		output.start();
		error.join(3000);
		output.join(3000);
		if (!output.getMessage().equals(""))
			System.err.println("Output: " + output.getMessage());
		if (!error.getMessage().equals(""))
			System.err.println("Error: " + error.getMessage());
	}

	private String makeJarCommand(String name) {
		ArrayList<String> cmd = JavaCommandHelper.buildJarCommandLine(configuration.getTempDirectory().getAbsolutePath(), name, new String[]{});
		return JavaCommandHelper.join(cmd.toArray(new String[cmd.size()]), " ");
	}

	private File getTaraCoreFile() {
		return new File(getClass().getResource(File.separator + "tara_core" + File.separator + "tara_core.jar").getPath());
	}

	private String makeCompileCommand(File[] sources) {
		ArrayList<String> cmd = JavaCommandHelper.buildJavaCompileCommandLine(sources, new String[]{getTaraCoreFile().getAbsolutePath()},
			null, configuration.getTempDirectory().getAbsolutePath());
		return JavaCommandHelper.join(cmd.toArray(new String[cmd.size()]), " ");
	}

	private File[] getSourceFiles(String type) {
		File path = new File(configuration.getTempDirectory() + File.separator + configuration.getProject());
		ArrayList<File> javaFiles = new ArrayList<>();
		getSourceFiles(path, javaFiles, type);
		return javaFiles.toArray(new File[javaFiles.size()]);
	}

	private File[] getClassFiles() {
		File path = new File(configuration.getTempDirectory() + File.separator + "build" + File.separator);
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
		String outPath = configuration.getTempDirectory() + File.separator + "out" + File.separator;
		File file = new File(outPath);
		file.mkdirs();
		return file;
	}

}
