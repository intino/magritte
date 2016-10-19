package tara.compiler.core;

import tara.Language;
import tara.compiler.codegeneration.FileSystemUtils;
import tara.compiler.core.errorcollection.TaraException;
import tara.compiler.semantic.LanguageLoader;
import tara.compiler.shared.Configuration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

import static java.io.File.separator;
import static tara.compiler.shared.TaraBuildConstants.WORKING_PACKAGE;

public class CompilerConfiguration implements Cloneable, Configuration {
	private static final Logger LOG = Logger.getGlobal();

	static {
		Logger.getGlobal().setLevel(java.util.logging.Level.INFO);
		LOG.setUseParentHandlers(false);
		for (Handler handler : LOG.getHandlers()) LOG.removeHandler(handler);
		final StreamHandler errorHandler = new StreamHandler(System.err, new SimpleFormatter());
		errorHandler.setLevel(java.util.logging.Level.WARNING);
		LOG.addHandler(errorHandler);
		final StreamHandler infoHandler = new StreamHandler(System.out, new SimpleFormatter());
		infoHandler.setLevel(java.util.logging.Level.INFO);
		LOG.addHandler(infoHandler);
	}

	public static final String REPOSITORY = "repository";
	private int warningLevel;
	private String sourceEncoding;
	private String project;
	private String module;
	private File outDirectory;
	private boolean debug;
	private Locale languageForCodeGeneration = Locale.ENGLISH;
	private boolean stashGeneration = false;
	private List<File> sourceDirectories = new ArrayList<>();
	private File resourcesDirectory;
	private File semanticRulesLib;
	private List<Integer> excludedPhases = new ArrayList<>();
	private String dslName;
	private String dslVersion;
	private Language dsl;
	private String outDSL;
	private String groupID;
	private String artifactID;
	private String version;
	private Level level;
	private boolean persistent;
	private boolean make;
	private boolean verbose;
	private File tempDirectory;
	private File taraProjectDirectory;
	private File taraDirectory;
	private boolean test;
	private int refactorId;
	private String workingPackage;
	private String languageWorkingPackage = "";
	private String nativeLanguage = "java";

	public CompilerConfiguration() {
		setWarningLevel(1);
		setDebug(false);
		String encoding;
		encoding = System.getProperty("file.encoding", "UTF8");
		encoding = System.getProperty("tara.source.encoding", encoding);
		sourceEncoding(encoding);
		try {
			tempDirectory = Files.createTempDirectory("_tara_").toFile();
		} catch (IOException e) {
			LOG.log(java.util.logging.Level.SEVERE, e.getMessage(), e);
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

	String sourceEncoding() {
		return this.sourceEncoding;
	}

	public void sourceEncoding(String encoding) {
		if (encoding == null) sourceEncoding = "UTF8";
		this.sourceEncoding = encoding;
	}

	public File getOutDirectory() {
		return this.outDirectory;
	}

	public void setOutDirectory(File directory) {
		if (directory != null) {
			this.outDirectory = directory;
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

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public File getTaraDirectory() {
		return taraDirectory;
	}

	public void setTaraDirectory(File taraDirectory) {
		this.taraDirectory = taraDirectory;
	}

	public void workingPackage(String workingPackage) {
		this.workingPackage = workingPackage;
	}

	public String workingPackage() {
		return workingPackage == null || workingPackage.isEmpty() ? outDSL() : workingPackage;
	}

	public String calculateLanguageWorkingPackage() {
		return languageWorkingPackage;
	}

	public String dslGroupId() {
		return "tara.dsl";
	}

	public File resourcesDirectory() {
		return resourcesDirectory;
	}

	public void setResourcesDirectory(File resourcesDirectory) {
		this.resourcesDirectory = resourcesDirectory;
	}

	public void cleanTemp() {
		FileSystemUtils.removeDir(tempDirectory);
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public Locale getLocale() {
		return languageForCodeGeneration;
	}

	public File getSemanticRulesLib() {
		return semanticRulesLib;
	}

	public void setSemanticRulesLib(File semanticRulesURL) {
		this.semanticRulesLib = semanticRulesURL;
	}

	public Language language(String language) {
		this.dslName = language;
		return dslVersion == null || dslName == null ? null : (this.dsl = loadLanguage());
	}

	public Language language(Language language) {
		this.dslName = language.languageName();
		this.dsl = language;
		return language;
	}

	public void dslVersion(String version) {
		this.dslVersion = version;
		if (dslName != null) this.dsl = loadLanguage();
	}

	public String dslVersion() {
		return this.dslVersion;
	}

	public Language language() {
		return this.dsl;
	}

	public String dsl() {
		return this.dsl.languageName();
	}

	public String outDSL() {
		return outDSL;
	}

	public String outDSL(String outDSL) {
		return this.outDSL = outDSL;
	}

	public String groupId() {
		return groupID;
	}

	public void groupId(String groupID) {
		this.groupID = groupID;
	}

	public String artifactId() {
		return artifactID;
	}

	public void artifactId(String artifactID) {
		this.artifactID = artifactID;
	}

	public String modelVersion() {
		return version;
	}

	public void modelVersion(String version) {
		this.version = version;
	}

	public void systemStashName(String name) {
		outDSL = name;
	}

	private Language loadLanguage() {
		try {
			final Language language = LanguageLoader.load(dslName, dslVersion, languagesDirectory().getAbsolutePath());
			if (language != null)
				calculateLanguageWorkingPackage(LanguageLoader.getLanguagePath(dslName, dslVersion, languagesDirectory().getAbsolutePath()));
			return language;
		} catch (TaraException e) {
			LOG.info("Language " + dslName + " cannot be load");
			return null;
		}
	}

	private void calculateLanguageWorkingPackage(File language) {
		try {
			if (language.isDirectory()) return;
			Manifest manifest = new JarFile(language).getManifest();
			final Attributes tara = manifest.getAttributes("tara");
			if (tara == null) return;
			this.languageWorkingPackage = tara.getValue(WORKING_PACKAGE.replace(".", "-"));
		} catch (IOException ignored) {
		}
	}

	private File languagesDirectory() {
		return new File(taraDirectory, REPOSITORY);
	}

	public String nativeLanguage() {
		return this.nativeLanguage;
	}

	public void nativeLanguage(String language) {
		this.nativeLanguage = language;
	}

	public Level level() {
		return level;
	}

	public void level(Level level) {
		this.level = level;
	}

	List<Integer> getExcludedPhases() {
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

	public void persistent(boolean persistent) {
		this.persistent = persistent;
	}

	public boolean isPersistent() {
		return persistent;
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

	public void setTaraProjectDirectory(File taraPath) {
		this.taraProjectDirectory = taraPath;
	}

	public File getTaraProjectDirectory() {
		return taraProjectDirectory;
	}

	public File getMiscDirectory() {
		return new File(getTaraProjectDirectory(), "misc");
	}

	public File getImportsFile() {
		return new File(getMiscDirectory(), module + ".json");
	}

	public List<File> sourceDirectories() {
		return sourceDirectories;
	}

	public File srcDirectory() {
		return sourceDirectories.stream().filter(f -> f.getName().equals("src")).findAny().orElse(null);
	}

	public File rulesDirectory() {
		for (File sourceDirectory : sourceDirectories) {
			final String rulesPackage = (workingPackage() == null ? module.toLowerCase() : workingPackage().toLowerCase().replace(".", File.separator)) + separator + "rules";
			final File file = new File(sourceDirectory, rulesPackage);
			if (file.exists()) return file;
		}
		return null;
	}

	public File functionsDirectory() {
		for (File sourceDirectory : sourceDirectories) {
			final String functionsPackage = (workingPackage() == null ? module.toLowerCase() : workingPackage().toLowerCase().replace(".", File.separator)) + separator + "functions";
			final File file = new File(sourceDirectory, functionsPackage);
			if (file.exists()) return file;
		}
		return null;
	}

	public void setTest(boolean test) {
		this.test = test;
	}

	public boolean isTest() {
		return test;
	}

	public int refactorId() {
		return this.refactorId;
	}

	public void refactorId(int refactorId) {
		this.refactorId = refactorId;
	}

	@Override
	public CompilerConfiguration clone() {
		try {
			return (CompilerConfiguration) super.clone();
		} catch (CloneNotSupportedException e) {
			LOG.info(e.getMessage());
			return null;
		}
	}

}
