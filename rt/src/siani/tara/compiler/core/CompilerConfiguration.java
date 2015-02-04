package siani.tara.compiler.core;

import java.io.File;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Locale;

public class CompilerConfiguration {
	private int warningLevel;
	private String sourceEncoding;
	private String project;
	private String module;
	private PrintWriter output;
	private File tempDirectory;
	private File rulesDirectory;
	private File targetDirectory;
	private boolean debug;
	private String tdkHome;
	private String projectIcon;
	private Locale languageForCodeGeneration;
	private String version = "1.0";
	private String description = "";
	private ArrayList<String> icons = new ArrayList<>();
	private String modelsDirectory;
	private boolean system;
	private File metricsDirectory;
	private File resourcesDirectory;
	private String metamodelFile;
	private String modelName;


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

	public void setTargetDirectory(String directory) {
		if ((directory != null) && (directory.length() > 0))
			this.targetDirectory = new File(directory);
		else
			this.tempDirectory = null;
	}

	public void setTargetDirectory(File targetDirectory) {
		this.targetDirectory = targetDirectory;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
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

	public void setTdkHome(String tdkHome) {
		this.tdkHome = tdkHome;
	}

	public String getProjectIcon() {
		return projectIcon;
	}

	public void setProjectIcon(String projectIcon) {
		this.projectIcon = projectIcon;
	}

	public void addIconPath(String iconsDir) {
		icons.add(iconsDir);
	}

	public ArrayList<String> getIconDirectories() {
		return icons;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public void setModelsDirectory(String modelsDirectory) {
		this.modelsDirectory = modelsDirectory;
	}

	public String getModelsDirectory() {
		return modelsDirectory;
	}

	public File getRulesDirectory() {
		return rulesDirectory;
	}

	public void setRulesDirectory(File rulesDirectory) {
		this.rulesDirectory = rulesDirectory;
	}

	public void setTerminal(boolean system) {
		this.system = system;
	}

	public boolean isTerminal() {
		return system;
	}

	public void setMetricsDirectory(File metricsDirectory) {
		this.metricsDirectory = metricsDirectory;
	}

	public File getMetricsDirectory() {
		return metricsDirectory;
	}

	public Locale getLanguageForCodeGeneration() {
		return languageForCodeGeneration;
	}

	public void setLanguageForCodeGeneration(Locale languageForCodeGeneration) {
		this.languageForCodeGeneration = languageForCodeGeneration;
	}

	public String getMetamodelFile() {
		return metamodelFile;
	}

	public void setMetamodelFile(String metamodelFile) {
		this.metamodelFile = metamodelFile;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getModelName() {
		return modelName;
	}
}
