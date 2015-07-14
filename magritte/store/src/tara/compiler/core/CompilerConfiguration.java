package tara.compiler.core;

import java.io.File;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;

public class CompilerConfiguration {
	private int warningLevel;
	private String sourceEncoding;
	private PrintWriter output;
	private File tempDirectory;
	private File targetDirectory;
	private boolean debug;
	private String tdkHome;
	private Locale languageForCodeGeneration;
	private String languagesDirectory;
	private File metricsDirectory;
	private File resourcesDirectory;
	private File nativePath;
	private int level;
	private String storeDirectory;
	private Map<String, File> classPath;


	public CompilerConfiguration() {
		setWarningLevel(1);
		setTempDirectory(null);
		setDebug(false);
		String encoding;
		encoding = System.getProperty("file.encoding", "UTF8");
		encoding = System.getProperty("tara.source.encoding", encoding);
		setSourceEncoding(encoding);
		setOutput(new PrintWriter(System.err));
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
		if (encoding == null) sourceEncoding = "UTF8";
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

	public File getOutDirectory() {
		return this.tempDirectory;
	}

	public void setOutDirectory(String directory) {
		if ((directory != null) && (directory.length() > 0)) {
			this.tempDirectory = new File(directory);
			this.tempDirectory.mkdirs();
		} else
			this.tempDirectory = null;
	}

	public void setTempDirectory(File directory) {
		this.tempDirectory = directory;
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

	public File getResourcesDirectory() {
		return resourcesDirectory;
	}

	public void setResourcesDirectory(File resourcesDirectory) {
		this.resourcesDirectory = resourcesDirectory;
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


	public String getTdkHome() {
		return tdkHome;
	}

	public void setMagritteHome(String tdkHome) {
		this.tdkHome = tdkHome;
	}

	public void setLanguagesDirectory(String languagesDirectory) {
		this.languagesDirectory = languagesDirectory;
	}

	public String getLanguageDirectory() {
		return languagesDirectory;
	}


	public File getMetricsDirectory() {
		return metricsDirectory;
	}

	public void setMetricsDirectory(File metricsDirectory) {
		this.metricsDirectory = metricsDirectory;
	}

	public Locale getLocale() {
		return languageForCodeGeneration;
	}

	public void setLocale(Locale localeForCodeGeneration) {
		this.languageForCodeGeneration = localeForCodeGeneration;
	}

	public File getNativePath() {
		return nativePath;
	}

	public void setNativePath(File nativePath) {
		this.nativePath = nativePath;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public void setStoreDirectory(String storeDirectory) {
		this.storeDirectory = storeDirectory;
	}

	public String getStoreDirectory() {
		return storeDirectory;
	}

	public void setClassPath(Map<String, File> classPath) {
		this.classPath = classPath;
	}

	public Map<String, File> getClassPath() {
		return classPath;
	}
}
