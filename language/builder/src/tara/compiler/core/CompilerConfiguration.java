package tara.compiler.core;

import tara.Language;
import tara.compiler.codegeneration.FileSystemUtils;
import tara.compiler.core.errorcollection.TaraException;
import tara.compiler.semantic.LanguageLoader;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.util.*;

import static java.io.File.separator;

public class
CompilerConfiguration implements Cloneable {

	public enum ModuleType {
		System, Application, Ontology, ProductLine, Platform;

		public int compareLevelWith(ModuleType type) {
			if (type.ordinal() == this.ordinal()) return 0;
			if ((type.ordinal() == 1 || type.ordinal() == 2) && (this.ordinal() == 1 || this.ordinal() == 2)) return 0;
			if ((type.ordinal() == 3 || type.ordinal() == 4) && (this.ordinal() == 3 || this.ordinal() == 4)) return 0;
			return type.ordinal() - this.ordinal();
		}

	}

	public static final String DSL = "dsl";

	public static final String[] SOURCE_DIRECTORIES = new String[]{"src", "test"};

	private int warningLevel;
	private String sourceEncoding;
	private String project;
	private String module;
	private PrintWriter output;
	private File outDirectory;
	private File finalOutDirectory;
	private boolean debug;
	private Locale languageForCodeGeneration = Locale.ENGLISH;
	private boolean stashGeneration = false;
	private File sourceDirectory;
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
	private File taraDirectory;
	private boolean test;
	private int engineRefactorId;
	private int domainRefactorId;
	private String nativeLanguage = "java";

	public CompilerConfiguration() {
		setWarningLevel(1);
		setDebug(false);
		String encoding;
		encoding = System.getProperty("file.encoding", "UTF8");
		encoding = System.getProperty("tara.source.encoding", encoding);
		sourceEncoding(encoding);
		setOutput(new PrintWriter(System.err));
		this.languages = new LinkedHashMap<>();
		final Language proteo = loadLanguage("Proteo");
		this.languages.put(ModuleType.Platform, new LanguageEntry("Proteo", proteo));
		this.languages.put(ModuleType.Ontology, new LanguageEntry("Proteo", proteo));
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

	String sourceEncoding() {
		return this.sourceEncoding;
	}

	public void sourceEncoding(String encoding) {
		if (encoding == null) sourceEncoding = "UTF8";
		this.sourceEncoding = encoding;
	}

	public void setOutput(PrintWriter output) {
		this.output = output == null ? new PrintWriter((Writer) null) : output;
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

	public void plarformOutDsl(String dsl) {
		outDsls.put(ModuleType.Platform.name(), dsl);
	}

	public void applicationOutDsl(String dsl) {
		outDsls.put(ModuleType.Application.name(), dsl);
	}

	private Language loadLanguage(String dsl) {
		try {
			return LanguageLoader.load(dsl,  new File(taraDirectory, DSL).getAbsolutePath());
		} catch (TaraException e) {
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

	public void setTaraDirectory(File refactorsPath) {
		this.taraDirectory = refactorsPath;
	}

	public File getTaraDirectory() {
		return taraDirectory;
	}

	public File getImportsFile() {
		return new File(new File(getTaraDirectory(), "misc"), (outDsl() != null ? outDsl() : module) + (type.equals(ModuleType.System) ? "_model" : "") + ".json");
	}

	public File sourceDirectory() {
		return sourceDirectory;
	}

	public void sourceDirectory(File path) {
		this.sourceDirectory = path;
	}

	public File rulesDirectory() {
		final File rules = new File(sourceDirectory, (outDsl() == null ? module.toLowerCase() : outDsl().toLowerCase()) + separator + "rules");
		rules.mkdirs();
		return rules;
	}


	public File functionsDirectory() {
		final File functions = new File(sourceDirectory, (outDsl() == null ? module.toLowerCase() : outDsl().toLowerCase()) + separator + "functions");
		functions.mkdirs();
		return functions;
	}

	public File nativesDirectory() {
		final File natives = new File(sourceDirectory, (outDsl() == null ? module.toLowerCase() : outDsl().toLowerCase()) + separator + "natives");
		natives.mkdirs();
		return natives;
	}

	public void setTest(boolean test) {
		this.test = test;
	}

	public boolean isTest() {
		return test;
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
			return null;
		}
	}

	private class LanguageEntry {

		String name;
		Language language;

		public LanguageEntry(String name, Language language) {
			this.name = name;
			this.language = language;
		}
	}
}
