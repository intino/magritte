package monet.tara.compiler.core;

import java.io.File;
import java.io.PrintWriter;
import java.io.Writer;

public class CompilerConfiguration {
	private int warningLevel;
	private String sourceEncoding;
	private String project;
	private PrintWriter output;
	private File tempDirectory;
	private File targetDirectory;
	private boolean debug;

	public CompilerConfiguration() {
		setWarningLevel(1);
		setTempDirectory((File) null);
		setDebug(false);
		String encoding = null;
		try {
			encoding = System.getProperty("file.encoding", "UTF8");
		} catch (Exception ignored) {
		}
		try {
			encoding = System.getProperty("tara.source.encoding", encoding);
		} catch (Exception ignored) {
		}
		setSourceEncoding(encoding);
		try {
			setOutput(new PrintWriter(System.err));
		} catch (Exception ignored) {
		}
	}

	public int getWarningLevel() {
		return this.warningLevel;
	}

	public void setWarningLevel(int level) {
		if ((level < 0) || (level > 3)) {
			this.warningLevel = 1;
		} else
			this.warningLevel = level;
	}

	public String getSourceEncoding() {
		return this.sourceEncoding;
	}

	public void setSourceEncoding(String encoding) {
		if (encoding == null) encoding = "UTF8";
		this.sourceEncoding = encoding;
	}

	public PrintWriter getOutput() {
		return this.output;
	}

	public void setOutput(PrintWriter output) {
		if (output == null) {
			this.output = new PrintWriter((Writer) null);
		} else
			this.output = output;
	}

	public File getTempDirectory() {
		return this.tempDirectory;
	}

	public void setTempDirectory(File directory) {
		this.tempDirectory = directory;
	}

	public void setTempDirectory(String directory) {
		if ((directory != null) && (directory.length() > 0))
			this.tempDirectory = new File(directory);
		else
			this.tempDirectory = null;
	}

	public boolean getDebug() {
		return this.debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public File getTargetDirectory() {
		return targetDirectory;
	}

	public void setTargetDirectory(File targetDirectory) {
		this.targetDirectory = targetDirectory;
	}

	public void setTargetDirectory(String directory) {
		if ((directory != null) && (directory.length() > 0))
			this.targetDirectory = new File(directory);
		else
			this.tempDirectory = null;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}


	public void cleanTemp() {
		cleanTemp(tempDirectory);
	}

	private void cleanTemp(File folder) {
		File[] files = folder.listFiles();
		if (files != null)
			for (File f : files)
				if (f.isDirectory()) cleanTemp(f);
				else f.delete();
	}
}
