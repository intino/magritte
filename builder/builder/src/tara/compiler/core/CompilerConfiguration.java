package tara.compiler.core;

import tara.Language;
import tara.compiler.codegeneration.FileSystemUtils;
import tara.compiler.core.errorcollection.TaraException;
import tara.compiler.semantic.LanguageLoader;
import tara.dsl.ProteoConstants;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.logging.*;

import static java.io.File.separator;

public class
CompilerConfiguration implements Cloneable {
	private static final Logger LOG = Logger.getGlobal();

	static {
		Logger.getGlobal().setLevel(Level.INFO);
		LOG.setUseParentHandlers(false);
		for (Handler handler : LOG.getHandlers()) LOG.removeHandler(handler);
		final StreamHandler errorHandler = new StreamHandler(System.err, new SimpleFormatter());
		errorHandler.setLevel(Level.WARNING);
		LOG.addHandler(errorHandler);
		final StreamHandler infoHandler = new StreamHandler(System.out, new SimpleFormatter());
		infoHandler.setLevel(Level.INFO);
		LOG.addHandler(infoHandler);
	}

	public enum ModuleType {
		System, Application, Ontology, ProductLine, Platform;

		public int compareLevelWith(ModuleType type) {
			if (type.ordinal() == this.ordinal()) return 0;
			if ((is(type, 1) || is(type, 2)) && (is(this, 1) || is(this, 2))) return 0;
			if ((is(type, 3) || is(type, 4)) && (is(this, 3) || is(this, 4))) return 0;
			return type.ordinal() - this.ordinal();
		}

		public boolean is(ModuleType type, int level) {
			return type.ordinal() == level;
		}

	}

	public static final String DSL = "dsl";

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
	private Map<ModuleType, LanguageEntry> languages;
	private Map<String, String> outDsls = new LinkedHashMap<>();
	private boolean ontology = false;
	private ModuleType type;
	private boolean dynamicLoad;
	private boolean make;
	private boolean verbose;
	private File tempDirectory;
	private File taraProjectDirectory;
	private File taraDirectory;
	private boolean test;
	private boolean generateMain = true;
	private int engineRefactorId;
	private int domainRefactorId;
	private String workingPackage;
	private String nativeLanguage = "java";

	public CompilerConfiguration() {
		setWarningLevel(1);
		setDebug(false);
		String encoding;
		encoding = System.getProperty("file.encoding", "UTF8");
		encoding = System.getProperty("tara.source.encoding", encoding);
		sourceEncoding(encoding);
		this.languages = new LinkedHashMap<>();
		final Language proteo = loadLanguage(ProteoConstants.PROTEO);
		this.languages.put(ModuleType.Platform, new LanguageEntry(ProteoConstants.PROTEO, proteo));
		this.languages.put(ModuleType.Ontology, new LanguageEntry(ProteoConstants.PROTEO, proteo));
		try {
			tempDirectory = Files.createTempDirectory("_tara_").toFile();
		} catch (IOException e) {
			LOG.log(Level.SEVERE, e.getMessage(), e);
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

	public void setWorkingPackage(String workingPackage) {
		this.workingPackage = workingPackage;
	}

	public String workingPackage() {
		return workingPackage;
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

	public Language language() {
		return language(this.type);
	}

	private Language language(ModuleType type) {
		final LanguageEntry entry = languages.get(type);
		if (entry != null && entry.language == null)
			entry.language = loadLanguage(entry.name);
		return entry != null ? entry.language : null;
	}

	public Language applicationLanguage() {
		return language(ModuleType.Application);
	}

	public Language platformLanguage() {
		return language(ModuleType.Platform);
	}


	public Language systemLanguage() {
		return language(ModuleType.System);
	}

	public void applicationLanguage(String dsl) {
		this.languages.put(ModuleType.Application, new LanguageEntry(dsl, loadLanguage(dsl)));
	}

	public void systemLanguage(String dsl) {
		this.languages.put(ModuleType.System, new LanguageEntry(dsl, loadLanguage(dsl)));
	}

	public String outDsl() {
		return outDsls.get(type.name());
	}

	public String outDsl(ModuleType type) {
		return outDsls.get(type.name());
	}

	public void platformOutDsl(String dsl) {
		outDsls.put(ModuleType.Platform.name(), dsl);
	}

	public void applicationOutDsl(String dsl) {
		outDsls.put(ModuleType.Application.name(), dsl);
	}

	public void systemStashName(String name) {
		outDsls.put(ModuleType.System.name(), name);
	}

	private Language loadLanguage(String dsl) {
		try {
			return LanguageLoader.load(dsl, new File(taraDirectory, DSL).getAbsolutePath());
		} catch (TaraException e) {
			LOG.info("Language " + dsl + " cannot be load");
			return null;
		}
	}

	public String nativeLanguage() {
		return this.nativeLanguage;
	}

	public void nativeLanguage(String language) {
		this.nativeLanguage = language;
	}

	public ModuleType moduleType() {
		return type;
	}

	public void moduleType(ModuleType moduleType) {
		this.type = moduleType;
	}

	List<Integer> getExcludedPhases() {
		return excludedPhases;
	}

	public void setExcludedPhases(List<Integer> excludedPhases) {
		this.excludedPhases = excludedPhases;
	}

	boolean isStashGeneration() {
		return stashGeneration;
	}

	public void setStashGeneration(boolean stashGeneration) {
		this.stashGeneration = stashGeneration;
	}

	public void setDynamicLoad(boolean dynamicLoad) {
		this.dynamicLoad = dynamicLoad;
	}

	public boolean isLazyLoad() {
		return dynamicLoad;
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

	public File getImportsFile() {
		return new File(new File(getTaraProjectDirectory(), "misc"), (outDsl() != null ? outDsl() : module) + ".json");
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

	public void generateMain(boolean main) {
		this.generateMain = main;
	}

	public boolean generateMain() {
		return generateMain;
	}

	public int domainRefactorId() {
		return this.domainRefactorId;
	}

	public int engineRefactorId() {
		return this.engineRefactorId;
	}

	public void setEngineRefactorId(int engineRefactorId) {
		this.engineRefactorId = engineRefactorId;
	}

	public void setApplicationRefactorId(int domainRefactorId) {
		this.domainRefactorId = domainRefactorId;
	}

	public boolean isOntology() {
		return ontology;
	}

	public void setOntology(boolean ontology) {
		this.ontology = ontology;
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

	private static class LanguageEntry {

		String name;
		Language language;

		LanguageEntry(String name, Language language) {
			this.name = name;
			this.language = language;
		}
	}
}
