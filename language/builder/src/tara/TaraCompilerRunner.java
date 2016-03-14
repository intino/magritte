package tara;

import tara.compiler.TaraCompiler;
import tara.compiler.constants.TaraBuildConstants;
import tara.compiler.constants.TaraCompilerMessageCategories;
import tara.compiler.core.CompilationUnit;
import tara.compiler.core.CompilerConfiguration;
import tara.compiler.core.CompilerMessage;
import tara.compiler.core.SourceUnit;
import tara.compiler.core.errorcollection.message.WarningMessage;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;

import static java.lang.System.out;
import static tara.compiler.constants.TaraBuildConstants.*;

class TaraCompilerRunner {
	private static final Logger LOG = Logger.getLogger(TaraCompilerRunner.class.getName());
	public static final String TARA = ".tara";
	private final File argsFile;
	private final boolean verbose;

	TaraCompilerRunner(File argsFile, boolean verbose) {
		this.argsFile = argsFile;
		this.verbose = verbose;
	}

	boolean run() {
		final CompilerConfiguration config = new CompilerConfiguration();
		config.setVerbose(verbose);
		final List<Map<File, Boolean>> srcFiles = initSourceMap();
		final List<CompilerMessage> compilerMessages = new ArrayList<>();
		getInfoFromArgsFile(argsFile, config, srcFiles);
		if (srcFiles.stream().allMatch(Map::isEmpty)) return true;
		if (verbose) out.println(PRESENTABLE_MESSAGE + "Tarac: loading sources...");
		List<TaraCompiler.OutputItem> compiledFiles = new ArrayList<>();
		if (!srcFiles.get(0).isEmpty()) compiledFiles.addAll(compileDefinitions(config, srcFiles, compilerMessages));
		if (!srcFiles.get(1).isEmpty()) compiledFiles.addAll(compileModels(config, srcFiles, compilerMessages));
		if (!srcFiles.get(2).isEmpty()) compiledFiles.addAll(compileTests(config, srcFiles, compilerMessages));
		if (verbose) {
			if (compiledFiles.isEmpty()) reportNotCompiledItems(srcFiles);
			else reportCompiledItems(compiledFiles);
			out.println();
		}
		processErrors(compilerMessages);
		return false;
	}

	private List<TaraCompiler.OutputItem> compileDefinitions(CompilerConfiguration config, List<Map<File, Boolean>> srcFiles, List<CompilerMessage> compilerMessages) {
		CompilationUnit.cleanOut(config);
		List<TaraCompiler.OutputItem> compiledFiles;
		config.setTest(false);
		final CompilationUnit unit = new CompilationUnit(config);
		addSources(srcFiles.get(0), unit);
		if (verbose) out.println(PRESENTABLE_MESSAGE + "Tarac: compiling definitions...");
		compiledFiles = new TaraCompiler(compilerMessages).compile(unit);
		out.println();
		return compiledFiles;
	}

	private List<TaraCompiler.OutputItem> compileModels(CompilerConfiguration config, List<Map<File, Boolean>> srcFiles, List<CompilerMessage> compilerMessages) {
		List<TaraCompiler.OutputItem> compiledFiles;
		CompilerConfiguration modelConf = config.clone();
		if (config.generatedLanguage() != null) modelConf.setLanguage(config.generatedLanguage());
		modelConf.loadLanguage();
		modelConf.setGeneratedLanguage(null);
		modelConf.setLevel(0);
		modelConf.setTest(false);
		final CompilationUnit unit = new CompilationUnit(modelConf);
		addSources(srcFiles.get(1), unit);
		if (verbose) out.println(PRESENTABLE_MESSAGE + "Tarac: compiling model...");
		compiledFiles = new TaraCompiler(compilerMessages).compile(unit);
		out.println();
		return compiledFiles;
	}

	private List<TaraCompiler.OutputItem> compileTests(CompilerConfiguration config, List<Map<File, Boolean>> srcFiles, List<CompilerMessage> compilerMessages) {
		List<TaraCompiler.OutputItem> compiledFiles = new ArrayList<>();
		CompilerConfiguration testConf = config.clone();
		if (config.generatedLanguage() != null) testConf.setLanguage(config.generatedLanguage());
		testConf.loadLanguage();
		testConf.setGeneratedLanguage(null);
		testConf.setLevel(0);
		testConf.setTest(true);
		for (Map.Entry<File, Boolean> file : srcFiles.get(2).entrySet()) {
			final CompilationUnit unit = new CompilationUnit(testConf);
			if (!file.getKey().getName().endsWith(TARA)) continue;
			unit.addSource(new SourceUnit(file.getKey(), unit.getConfiguration(), unit.getErrorCollector(), file.getValue()));
			if (verbose) out.println(PRESENTABLE_MESSAGE + "Tarac: compiling tests...");
			compiledFiles.addAll(new TaraCompiler(compilerMessages).compile(unit));
			out.println();
		}
		return compiledFiles;
	}

	private static List<Map<File, Boolean>> initSourceMap() {
		List<Map<File, Boolean>> list = new ArrayList<>();
		IntStream.rangeClosed(1, 3).forEach(i -> list.add(new LinkedHashMap<>()));
		return list;

	}

	private static void processErrors(List<CompilerMessage> compilerMessages) {
		int errorCount = 0;
		for (CompilerMessage message : compilerMessages) {
			if (message.getCategory().equals(TaraCompilerMessageCategories.ERROR)) {
				if (errorCount > 100) continue;
				errorCount++;
			}
			printMessage(message);
		}
	}

	private static void getInfoFromArgsFile(File argsFile, CompilerConfiguration configuration, List<Map<File, Boolean>> srcFiles) {
		BufferedReader reader = null;
		configuration.setOutput(new PrintWriter(System.err));
		configuration.setWarningLevel(WarningMessage.PARANOIA);
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(argsFile)));
			String line;
			readSrc(srcFiles.get(0), DEF_FILE, reader);
			readSrc(srcFiles.get(1), MODEL_FILE, reader);
			line = readSrc(srcFiles.get(2), TEST_MODEL_FILE, reader);
			processArgs(configuration, reader, line);
		} catch (IOException e) {
			LOG.log(Level.SEVERE, "Error getting Args IO: " + e.getMessage(), e);
		} finally {
			try {
				assert reader != null;
				reader.close();
			} catch (IOException e) {
				LOG.log(Level.SEVERE, "Error getting Args IO2: " + e.getMessage(), e);
			} finally {
//				argsFile.delete();
			}
		}
	}

	private static String readSrc(Map<File, Boolean> srcFiles, String type, BufferedReader reader) throws IOException {
		String line;
		while (!"".equals(line = reader.readLine())) {
			if (type.equals(line)) continue;
			final String[] split = line.split("#");
			final File file = new File(split[0]);
			srcFiles.put(file, Boolean.valueOf(split[1]));
		}
		return line;
	}

	private static void processArgs(CompilerConfiguration configuration, BufferedReader reader, String line) throws IOException {
		String aLine = line;
		while (aLine != null) {
			processLine(configuration, reader, aLine);
			aLine = reader.readLine();
		}
	}

	private static void processLine(CompilerConfiguration configuration, BufferedReader reader, String aLine) throws IOException {
		switch (aLine) {
			case TaraBuildConstants.ENCODING:
				configuration.setSourceEncoding(reader.readLine());
				break;
			case TaraBuildConstants.OUTPUTPATH:
				configuration.setOutDirectory(reader.readLine());
				break;
			case TaraBuildConstants.FINAL_OUTPUTPATH:
				configuration.setFinalOutputDirectory(reader.readLine());
				break;
			case TaraBuildConstants.PROJECT:
				configuration.setProject(reader.readLine());
				break;
			case TaraBuildConstants.RESOURCES:
				configuration.setResourcesDirectory(new File(reader.readLine()));
				break;
			case TaraBuildConstants.MODULE:
				configuration.setModule(reader.readLine());
				break;
			case TaraBuildConstants.MODEL_LEVEL:
				configuration.setLevel(Integer.valueOf(reader.readLine()));
				break;
			case TaraBuildConstants.EXCLUDED_PHASES:
				configuration.setExcludedPhases(parseToInt(reader.readLine().split(" ")));
				break;
			case TaraBuildConstants.STASH_GENERATION:
				setStashGeneration(configuration, reader);
				break;
			case TaraBuildConstants.SEMANTIC_LIB:
				configuration.setSemanticRulesLib(new File(reader.readLine()));
				break;
			case TaraBuildConstants.GENERATED_LANG_NAME:
				configuration.setGeneratedLanguage(reader.readLine());
				break;
			case TaraBuildConstants.DYNAMIC_LOAD:
				configuration.setDynamicLoad(Boolean.valueOf(reader.readLine()));
				break;
			case TaraBuildConstants.MAKE:
				configuration.setMake(Boolean.valueOf(reader.readLine()));
				break;
			case TaraBuildConstants.TEST:
				configuration.setTest(Boolean.valueOf(reader.readLine()));
				break;
			case TaraBuildConstants.ONTOLOGY:
				configuration.setOntology(Boolean.valueOf(reader.readLine()));
				break;
			case TaraBuildConstants.PLATFORM_REFACTOR_ID:
				configuration.setEngineRefactorId(Integer.valueOf(reader.readLine()));
				break;
			case TaraBuildConstants.APPLICATION_REFACTOR_ID:
				configuration.setApplicationRefactorId(Integer.valueOf(reader.readLine()));
				break;
			case TaraBuildConstants.SRC_PATH:
				configuration.setSrcPath(new File(reader.readLine()));
				break;
			case TaraBuildConstants.TARA_PATH:
				configuration.setTaraDirectory(new File(reader.readLine()));
				break;
			case TaraBuildConstants.NATIVES_PATH:
				configuration.setNativePath(new File(reader.readLine()));
				break;
			case TaraBuildConstants.NATIVES_LANGUAGE:
				configuration.nativeLanguage(reader.readLine());
				break;
			case TaraBuildConstants.LANGUAGE:
				configuration.setLanguage(reader.readLine());
				break;
			case TaraBuildConstants.MAGRITTE:
				configuration.magritteLibrary(reader.readLine());
				break;
			case TaraBuildConstants.RULES:
				configuration.setRulesDirectory(new File(reader.readLine()));
				break;
			default:
				break;
		}
	}

	private static void setStashGeneration(CompilerConfiguration conf, BufferedReader reader) throws IOException {
		final boolean stashGeneration = Boolean.parseBoolean(reader.readLine());
		conf.setStashGeneration(stashGeneration);
	}

	private static List<Integer> parseToInt(String[] phases) throws IOException {
		List<Integer> list = new ArrayList<>();
		for (String phase : phases) list.add(Integer.parseInt(phase));
		return list;
	}

	private static void addSources(Map<File, Boolean> srcFiles, final CompilationUnit unit) {
		srcFiles.entrySet().stream().
			filter(file -> file.getKey().getName().endsWith(TARA)).
			forEach(file -> unit.addSource(new SourceUnit(file.getKey(), unit.getConfiguration(), unit.getErrorCollector(), file.getValue())));
	}

	private static void printMessage(CompilerMessage message) {
		out.print(TaraBuildConstants.MESSAGES_START);
		out.print(message.getCategory());
		out.print(TaraBuildConstants.SEPARATOR);
		out.print(message.getMessage());
		out.print(TaraBuildConstants.SEPARATOR);
		out.print(message.getUrl());
		out.print(TaraBuildConstants.SEPARATOR);
		out.print(message.getLineNum());
		out.print(TaraBuildConstants.SEPARATOR);
		out.print(message.getColumnNum());
		out.print(TaraBuildConstants.SEPARATOR);
		out.print(TaraBuildConstants.MESSAGES_END);
		out.println();
	}

	private static void reportCompiledItems(List<TaraCompiler.OutputItem> compiledFiles) {
		for (TaraCompiler.OutputItem compiledFile : compiledFiles) {
			out.print(TaraBuildConstants.COMPILED_START);
			out.print(compiledFile.getOutputPath());
			out.print(TaraBuildConstants.SEPARATOR);
			out.print(compiledFile.getSourceFile());
			out.print(TaraBuildConstants.COMPILED_END);
			out.println();
		}
	}

	private static void reportNotCompiledItems(List<Map<File, Boolean>> toRecompile) {
		for (Map<File, Boolean> entry : toRecompile) {
			for (File file : entry.keySet()) {
				out.print(TaraBuildConstants.TO_RECOMPILE_START);
				out.print(file.getAbsolutePath());
				out.print(TaraBuildConstants.TO_RECOMPILE_END);
				out.println();
			}
		}

	}
}
