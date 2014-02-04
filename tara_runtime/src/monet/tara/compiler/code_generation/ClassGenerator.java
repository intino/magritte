package monet.tara.compiler.code_generation;

import com.intellij.openapi.util.text.StringUtil;
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
			Process proccess = rt.exec(makeCommand(getSourceFiles()));
			error = StreamWrapper.getStreamWrapper(proccess.getErrorStream(), "ERROR");
			output = StreamWrapper.getStreamWrapper(proccess.getInputStream(), "OUTPUT");
			error.start();
			output.start();
			error.join(3000);
			output.join(3000);
			System.out.println("Output: " + output.message + "\nError: " + error.message);
			return proccess.waitFor();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return -1;
		}
	}

	private File getTaraCoreFile() {
		File file = new File(getClass().getResource(File.separator+"tara_code"+File.separator+"tara_core.jar").getPath());
		return file;
	}

	private String makeCommand(File[] sources) {
		ArrayList<String> cmd = JavaCHelper.buildJavaCommandLine(sources, new String[] {getTaraCoreFile().getAbsolutePath()},
			null, configuration.getTempDirectory().getAbsolutePath());
		return StringUtil.join(cmd, " ");
	}

	private File[] getSourceFiles() {
		File path = new File(configuration.getTempDirectory() + File.separator + configuration.getProject());
		ArrayList<File> javaFiles = new ArrayList<>();
		getSourceFiles(path, javaFiles);
		return javaFiles.toArray(new File[javaFiles.size()]);
	}

	private void getSourceFiles(File path, ArrayList<File> javaFiles) {
		for (File file : path.listFiles())
			if (file.isDirectory())
				getSourceFiles(file, javaFiles);
			else if (file.getName().endsWith(".java"))
				javaFiles.add(file);
	}

}
