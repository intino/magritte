package monet.tara.compiler.core;

import java.io.File;
import java.io.PrintWriter;
import java.io.Writer;

public class CompilerConfiguration {
	private int warningLevel;
	private String sourceEncoding;
	private PrintWriter output;
	private File targetDirectory;
	private boolean debug;

	public CompilerConfiguration() {
		setWarningLevel(1);
		setTargetDirectory((File) null);
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

	public File getTargetDirectory() {
		return this.targetDirectory;
	}

	public void setTargetDirectory(String directory) {
		if ((directory != null) && (directory.length() > 0))
			this.targetDirectory = new File(directory);
		else
			this.targetDirectory = null;
	}

	public void setTargetDirectory(File directory) {
		this.targetDirectory = directory;
	}

	public boolean getDebug() {
		return this.debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

}