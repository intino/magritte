package io.intino.tara.compiler.core;

import io.intino.tara.Language;
import io.intino.tara.compiler.codegeneration.FileSystemUtils;
import io.intino.tara.compiler.core.errorcollection.TaraException;
import io.intino.tara.compiler.semantic.LanguageLoader;
import io.intino.tara.compiler.shared.Configuration;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

import static io.intino.tara.compiler.semantic.LanguageLoader.getLanguagePath;
import static io.intino.tara.compiler.shared.TaraBuildConstants.WORKING_PACKAGE;
import static java.io.File.separator;

public class CompilerConfiguration implements Cloneable, Configuration {
	public static final String REPOSITORY = "repository";
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

	private int warningLevel;
	private String sourceEncoding;
	private String project;
	private String module;
	private File outDirectory;
	private boolean debug;
	private Locale languageForCodeGeneration = Locale.ENGLISH;
	private List<File> sourceDirectories = new ArrayList<>();
	private File resourcesDirectory;
	private File semanticRulesLib;
	private List<Integer> excludedPhases = new ArrayList<>();
	private List<DSL> languages = new ArrayList<>();
	private String outDSL;
	private String groupID;
	private String artifactID;
	private String version;
	private Level level;
	private boolean make;
	private boolean verbose;
	private File tempDirectory;
	private File intinoProjectDirectory;
	private File taraDirectory;
	private boolean test;
	private String workingPackage;
	private String nativeLanguage = "java";
	private Map<String, String> packageParameters;
	private boolean stashGeneration = false;
	private PrintStream out = System.out;

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

	public String sourceEncoding() {
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

	public String version() {
		return version;
	}

	public void version(String version) {
		this.version = version;
	}

	public void workingPackage(String workingPackage) {
		this.workingPackage = workingPackage;
	}

	public String workingPackage() {
		return workingPackage == null || workingPackage.isEmpty() ? outLanguage() : workingPackage;
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

	public LanguageLibrary addLanguage(String name, String version) {
		final LanguageLibrary language = this.languages.stream().filter(d -> d.name().equalsIgnoreCase(name)).findFirst().orElse(null);
		if (language == null) {
			final DSL dsl = new DSL(name, version);
			languages.add(dsl);
			return dsl;
		}
		return language;
	}

	public void addLanguage(Language taraLanguage) {
		final LanguageLibrary language = this.languages.stream().filter(d -> d.name().equalsIgnoreCase(taraLanguage.languageName())).findFirst().orElse(null);
		if (language == null) {
			final DSL dsl = new DSL(taraLanguage);
			languages.add(dsl);
		}
	}

	public String outLanguage() {
		return outDSL;
	}

	@Override
	public String outLanguageVersion() {
		return version;
	}

	public String outDSL(String outDSL) {
		return this.outDSL = outDSL;
	}

	@Override
	public String boxVersion() {
		return "1.0.0";
	}

	@Override
	public String boxPackage() {
		return "box";
	}

	public void systemStashName(String name) {
		outDSL = name;
	}

	@Override
	public List<DSL> languages() {
		return languages;
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

	public boolean isVerbose() {
		return verbose;
	}

	public void setVerbose(boolean verbose) {
		this.verbose = verbose;
	}

	public boolean isMake() {
		return make;
	}

	public void setMake(boolean make) {
		this.make = make;
	}

	public void intinoProjectDirectory(File intinoPath) {
		this.intinoProjectDirectory = intinoPath;
	}

	public File intinoProjectDirectory() {
		return intinoProjectDirectory;
	}

	public File artifactsDirectory() {
		return new File(intinoProjectDirectory(), "artifacts");
	}

	private File getTaraProjectDirectory() {
		return new File(intinoProjectDirectory(), "tara");
	}


	public File getImportsFile() {
		return new File(getTaraProjectDirectory(), module + ".json");
	}

	public List<File> sourceDirectories() {
		return sourceDirectories;
	}

	public File srcDirectory() {
		return sourceDirectories.stream().filter(f -> f.getName().equals("src")).findAny().orElse(sourceDirectories.isEmpty() ? null : sourceDirectories.get(0));
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

	public boolean isTest() {
		return test;
	}

	public void setTest(boolean test) {
		this.test = test;
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

	public Map<String, String> packageParameters() {
		return packageParameters;
	}

	public void packageParameters(Map<String, String> packageParameters) {
		this.packageParameters = packageParameters;
	}

	public void setStashGeneration() {
		this.stashGeneration = true;
	}

	public boolean isStashGeneration() {
		return this.stashGeneration;
	}

	public PrintStream out() {
		return out;
	}

	public void out(PrintStream out) {
		this.out = out;
	}

	public class DSL implements LanguageLibrary {
		io.intino.tara.Language language;
		String name;
		String version;
		String effectiveVersion;
		String generationPackage;

		public DSL(String name, String version) {
			this.name = name;
			this.version = version;
		}

		public DSL(Language language) {
			this.language = language;
			this.name = this.language.languageName();
		}

		public io.intino.tara.Language get() {
			return language == null ? (language = loadLanguage()) : language;
		}

		@Override
		public String name() {
			return language == null ? name : language.languageName();
		}

		@Override
		public String version() {
			return version;
		}

		@Override
		public String effectiveVersion() {
			return effectiveVersion;
		}

		@Override
		public void version(String version) {
			this.version = version;
		}

		@Override
		public String generationPackage() {
			return this.generationPackage;
		}

		void generationPackage(File language) {
			if (language.isDirectory() || !language.exists()) {
				this.generationPackage = name;
			} else {
				try {
					Manifest manifest = new JarFile(language).getManifest();
					final Attributes tara = manifest.getAttributes("tara");
					this.generationPackage = tara == null ? name : tara.getValue(WORKING_PACKAGE.replace(".", "-"));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		private io.intino.tara.Language loadLanguage() {
			try {
				final io.intino.tara.Language language = LanguageLoader.load(name, version, languagesDirectory().getAbsolutePath());
				if (language != null)
					generationPackage(getLanguagePath(name, version, languagesDirectory().getAbsolutePath()));
				return language;
			} catch (NoClassDefFoundError | TaraException e) {
				LOG.info("Language " + name() + " cannot be load");
				return null;
			}
		}
	}
}
