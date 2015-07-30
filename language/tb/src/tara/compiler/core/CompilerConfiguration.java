package tara.compiler.core;

import tara.Language;
import tara.compiler.core.errorcollection.TaraException;
import tara.compiler.core.errorcollection.TaraRuntimeException;
import tara.compiler.semantic.LanguageLoader;

import java.io.File;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class CompilerConfiguration {
	private int warningLevel;
	private String sourceEncoding;
	private String project;
	private String module;
	private boolean plateRequired;
	private PrintWriter output;
	private File tempDirectory;
	private File rulesDirectory;
	private File targetDirectory;
	private boolean debug;
	private String magritteLibrary;
	private String projectIcon;
	private Locale languageForCodeGeneration;
	private String version = "1.0";
	private String description = "";
	private List<String> icons = new ArrayList<>();
	private String languagesDirectory;
	private boolean stashGeneration = false;
	private Set<String> stashPath;
	private File metricsDirectory;
	private File resourcesDirectory;
	private String generatedLanguage;
	private String semanticRulesLib;
	private List<Integer> excludedPhases;
	private Language language;
	private String languageName = "Proteo";
	private File nativePath;
	private int level;


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


	public String magriteLibrary() {
		return magritteLibrary;
	}

	public void magritteLibrary(String library) {
		this.magritteLibrary = library;
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

	public List<String> getIconDirectories() {
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

	public void setLanguagesDirectory(String languagesDirectory) {
		this.languagesDirectory = languagesDirectory;
	}

	public String getLanguageDirectory() {
		return languagesDirectory;
	}

	public File getRulesDirectory() {
		return rulesDirectory;
	}

	public void setRulesDirectory(File rulesDirectory) {
		this.rulesDirectory = rulesDirectory;
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

	public String getGeneratedLanguage() {
		return generatedLanguage;
	}

	public void setGeneratedLanguage(String language) {
		this.generatedLanguage = language;
	}

	public String getSemanticRulesLib() {
		return semanticRulesLib;
	}

	public void setSemanticRulesLib(String semanticRulesURL) {
		this.semanticRulesLib = semanticRulesURL;
	}

	public Language getLanguage() {
		if (language == null) return loadLanguage();
		return language;
	}

	public void setLanguage(String language) {
		languageName = language;
	}

	public Language loadLanguage() {
		try {
			if (language != null) return language;
			this.language = LanguageLoader.load(languageName, languagesDirectory);
			return language;
		} catch (TaraException ignored) {
			throw new TaraRuntimeException("Language cannot be loaded", null);
		}
	}

	public boolean isPlateRequired() {
		return plateRequired;
	}

	public void setPlateRequired(boolean plateRequired) {
		this.plateRequired = plateRequired;
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

	public List<Integer> getExcludedPhases() {
		return excludedPhases;
	}

	public void setExcludedPhases(List<Integer> excludedPhases) {
		this.excludedPhases = excludedPhases;
	}

	public boolean isStashGeneration() {
		return stashGeneration;
	}

	public void setStashGeneration(boolean stashGeneration) {
		this.stashGeneration = stashGeneration;
	}

	public Set<String> getStashPath() {
		return stashPath;
	}

	public void setStashPath(Set<String> stashPath) {
		this.stashPath = stashPath;
	}
}
