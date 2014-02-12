package monet.tara.compiler.code_generation;

import monet.tara.compiler.core.CompilerConfiguration;

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
		StreamWrapper error, output;
		try {

			Process compileProcess = rt.exec(makeCompileCommand(getSourceFiles("java")));
			if (compileProcess.waitFor() == -1) return -1;
			//Process jarProcess = rt.exec(makeJarCommand(getSourceFiles("class")));
			error = StreamWrapper.getStreamWrapper(compileProcess.getErrorStream(), "ERROR");
			output = StreamWrapper.getStreamWrapper(compileProcess.getInputStream(), "OUTPUT");
			error.start();
			output.start();
			error.join(3000);
			output.join(3000);
			System.err.println("Output: " + output.message + "\nError: " + error.message);
			return compileProcess.waitFor();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return -1;
		}
	}

	private String makeJarCommand(File[] sourceFiles) {
		ArrayList<String> cmd = new ArrayList<>();
		cmd.add(JavaCHelper.getJarExecutable());
		return JavaCHelper.join(cmd.toArray(new String[cmd.size()]), " ");
	}

	private File getTaraCoreFile() {
		return new File(getClass().getResource(File.separator+"tara_code"+File.separator+"tara_core.jar").getPath());
	}

	private String makeCompileCommand(File[] sources) {
		ArrayList<String> cmd = JavaCHelper.buildJavaCompileCommandLine(sources, new String[]{getTaraCoreFile().getAbsolutePath()},
			null, configuration.getTempDirectory().getAbsolutePath());
		return JavaCHelper.join(cmd.toArray(new String[cmd.size()]), " ");
	}

	private File[] getSourceFiles(String type) {
		File path = new File(configuration.getTempDirectory() + File.separator + configuration.getProject());
		ArrayList<File> javaFiles = new ArrayList<>();
		getSourceFiles(path, javaFiles, type);
		return javaFiles.toArray(new File[javaFiles.size()]);
	}

	private void getSourceFiles(File path, ArrayList<File> javaFiles, String extension) {
		for (File file : path.listFiles())
			if (file.isDirectory())
				getSourceFiles(file, javaFiles, extension);
			else if (file.getName().endsWith("."+ extension))
				javaFiles.add(file);
	}

}
