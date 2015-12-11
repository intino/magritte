package tara.compiler.core;

import tara.Language;
import tara.compiler.codegeneration.FileSystemUtils;
import tara.compiler.core.errorcollection.TaraException;
import tara.compiler.core.errorcollection.TaraRuntimeException;
import tara.compiler.semantic.LanguageLoader;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class CompilerConfiguration {
	public static final String DSL = "dsl";
	private int warningLevel;
	private String sourceEncoding;
	private String project;
	private String module;
	private PrintWriter output;
	private File outDirectory;
	private File rulesDirectory;
	private File finalOutDirectory;
	private boolean debug;
	private String magritteLibrary;
	private Locale languageForCodeGeneration = Locale.ENGLISH;
	private String version = "1.0";
	private boolean stashGeneration = false;
	private Set<String> stashPath;
	private File resourcesDirectory;
	private String generatedLanguage;
	private File semanticRulesLib;
	private List<Integer> excludedPhases = new ArrayList<>();
	private Language language;
	private String languageName = "Proteo";
	private File nativePath;
	private int level;
	private boolean dynamicLoad;
	private boolean make;
	private Boolean customLayers;
	private boolean verbose;
	private File tempDirectory;
	private File taraDirectory;
	private File srcPath;
	private boolean test;


	public CompilerConfiguration() {
		setWarningLevel(1);
		setDebug(false);
		String encoding;
		encoding = System.getProperty("file.encoding", "UTF8");
		encoding = System.getProperty("tara.source.encoding", encoding);
		setSourceEncoding(encoding);
		setOutput(new PrintWriter(System.err));
		try {
			tempDirectory = Files.createTempDirectory("_tara_").toFile();
		} catch (IOException e) {
			e.printStackTrace();
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
		if (encoding == null) sourceEncoding = "UTF8";
		this.sourceEncoding = encoding;
	}

	public void setOutput(PrintWriter output) {
		this.output = output == null ? new PrintWriter((Writer) null) : output;
	}

	public File getOutDirectory() {
		return this.outDirectory;
	}

	public void setOutDirectory(String directory) {
		if ((directory != null) && (directory.length() > 0)) {
			this.outDirectory = new File(directory);
			this.outDirectory.mkdirs();
		} else
			this.outDirectory = null;
	}


	public boolean getDebug() {
		return this.debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public File getTempDirectory() {
		return tempDirectory;
	}

	public File getFinalOutDirectory() {
		return finalOutDirectory;
	}

	public void setFinalOutputDirectory(String directory) {
		this.finalOutDirectory = (directory != null) && (directory.length() > 0) ? new File(directory) : null;
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
		FileSystemUtils.removeDir(tempDirectory);
	}

	public String magriteLibrary() {
		return magritteLibrary;
	}

	public void magritteLibrary(String library) {
		this.magritteLibrary = library;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public File getRulesDirectory() {
		return rulesDirectory;
	}

	public void setRulesDirectory(File rulesDirectory) {
		this.rulesDirectory = rulesDirectory;
	}

	public Locale getLocale() {
		return languageForCodeGeneration;
	}

	public String generatedLanguage() {
		return generatedLanguage;
	}

	public void setGeneratedLanguage(String language) {
		this.generatedLanguage = language;
	}

	public File getSemanticRulesLib() {
		return semanticRulesLib;
	}

	public void setSemanticRulesLib(File semanticRulesURL) {
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
			this.language = LanguageLoader.load(languageName, new File(taraDirectory, DSL).getAbsolutePath());
			return language;
		} catch (TaraException e) {
			throw new TaraRuntimeException("Language cannot be loaded", null, e);
		}
	}

	public File getNativePath() {
		return nativePath;
	}

	public void setNativePath(File nativePath) {
		this.nativePath = nativePath;
	}

	public int level() {
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

	public void setDynamicLoad(boolean dynamicLoad) {
		this.dynamicLoad = dynamicLoad;
	}

	public boolean isDynamicLoad() {
		return dynamicLoad;
	}

	public void setCustomLayers(Boolean customLayers) {
		this.customLayers = customLayers;
	}

	public Boolean getCustomLayers() {
		return customLayers;
	}

	public void setVerbose(boolean verbose) {
		this.verbose = verbose;
	}

	public boolean isVerbose() {
		return verbose;
	}

	public boolean isMake() {
		return make;
	}

	public void setMake(boolean make) {
		this.make = make;
	}

	public void setTaraDirectory(File refactorsPath) {
		this.taraDirectory = refactorsPath;
	}

	public File getTaraDirectory() {
		return taraDirectory;
	}

	public File getSrcPath() {
		return srcPath;
	}

	public void setSrcPath(File srcPath) {
		this.srcPath = srcPath;
	}

	public void setTest(boolean test) {
		this.test = test;
	}

	public boolean isTest() {
		return test;
	}
}
