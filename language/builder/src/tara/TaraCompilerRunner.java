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
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.System.out;

class TaraCompilerRunner {
	private static final Logger LOG = Logger.getLogger(TaraCompilerRunner.class.getName());
	public static final String TARA = ".tara";

	private TaraCompilerRunner() {
	}

	static boolean runTaraCompiler(File argsFile, boolean verbose) {
		final CompilerConfiguration config = new CompilerConfiguration();
		config.setVerbose(verbose);
		final Map<File, Boolean> srcFiles = new HashMap<>();
		final List<CompilerMessage> compilerMessages = new ArrayList<>();
		getInfoFromArgsFile(argsFile, config, srcFiles);
		if (srcFiles.isEmpty()) return true;
		if (verbose) out.println(TaraBuildConstants.PRESENTABLE_MESSAGE + "Tarac: loading sources...");
		List<TaraCompiler.OutputItem> compiledFiles = new ArrayList<>();
		if (config.isTest()) {
			for (Map.Entry<File, Boolean> file : srcFiles.entrySet()) {
				final CompilationUnit unit = new CompilationUnit(config);
				if (!file.getKey().getName().endsWith(TARA)) continue;
				unit.addSource(new SourceUnit(file.getKey(), unit.getConfiguration(), unit.getErrorCollector(), file.getValue()));
				if (verbose) out.println(TaraBuildConstants.PRESENTABLE_MESSAGE + "Tarac: compiling...");
				compiledFiles = new TaraCompiler(compilerMessages).compile(unit);
				out.println();
			}
		} else {
			final CompilationUnit unit = new CompilationUnit(config);
			addSources(srcFiles, unit);
			if (verbose) out.println(TaraBuildConstants.PRESENTABLE_MESSAGE + "Tarac: compiling...");
			compiledFiles = new TaraCompiler(compilerMessages).compile(unit);
			out.println();
		}
		if (verbose) {
			if (compiledFiles.isEmpty()) reportNotCompiledItems(srcFiles);
			else reportCompiledItems(compiledFiles);
			out.println();
		}
		processErrors(compilerMessages);
		return false;
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

	private static void getInfoFromArgsFile(File argsFile, CompilerConfiguration configuration, Map<File, Boolean> srcFiles) {
		BufferedReader reader = null;
		configuration.setOutput(new PrintWriter(System.err));
		configuration.setWarningLevel(WarningMessage.PARANOIA);
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(argsFile)));
			String line;
			while ((line = reader.readLine()) != null) {
				if (!TaraBuildConstants.SRC_FILE.equals(line)) break;
				while (!"".equals(line = reader.readLine())) {
					final String[] split = line.split("#");
					final File file = new File(split[0]);
					srcFiles.put(file, Boolean.valueOf(split[1]));
				}
			}
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
			case TaraBuildConstants.CUSTOM_LAYERS:
				configuration.setCustomLayers(Boolean.valueOf(reader.readLine()));
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
		if (stashGeneration)
			conf.setStashPath(generateStashPath(conf.getOutDirectory(), conf.getOutDirectory()));
	}

	private static Set<String> generateStashPath(File folder, File rootFolder) {
		Set<String> files = taraFilesIn(folder, rootFolder);
		for (File file : folder.listFiles(File::isDirectory))
			files.addAll(generateStashPath(file, rootFolder));
		return files;
	}

	private static List<Integer> parseToInt(String[] phases) throws IOException {
		List<Integer> list = new ArrayList<>();
		for (String phase : phases) list.add(Integer.parseInt(phase));
		return list;
	}

	private static Set<String> taraFilesIn(File folder, File rootFolder) {
		File[] files = folder.listFiles((f, n) -> n.endsWith(TARA));
		Set<String> result = new LinkedHashSet<>(files.length);
		for (File file : files) result.add(getNameSpace(file, rootFolder));
		return result;
	}

	private static String getNameSpace(File file, File root) {
		return file.getAbsolutePath().substring(root.getAbsolutePath().length() + 1).replace(TARA, "").replace(File.separator, ".");
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

	private static void reportNotCompiledItems(Map<File, Boolean> toRecompile) {
		for (File file : toRecompile.keySet()) {
			out.print(TaraBuildConstants.TO_RECOMPILE_START);
			out.print(file.getAbsolutePath());
			out.print(TaraBuildConstants.TO_RECOMPILE_END);
			out.println();
		}
	}
}
