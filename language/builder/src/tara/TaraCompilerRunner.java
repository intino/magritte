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
		if (verbose) System.out.println(TaraBuildConstants.PRESENTABLE_MESSAGE + "Tarac: loading sources...");
		final CompilationUnit unit = new CompilationUnit(config);
		addSources(srcFiles, unit);
		if (verbose) System.out.println(TaraBuildConstants.PRESENTABLE_MESSAGE + "Tarac: compiling...");
		final List<TaraCompiler.OutputItem> compiledFiles = new TaraCompiler(compilerMessages).compile(unit);
		System.out.println();
		if (verbose) {
			if (compiledFiles.isEmpty()) reportNotCompiledItems(srcFiles);
			else reportCompiledItems(compiledFiles);
			System.out.println();
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
			case TaraBuildConstants.CUSTOM_MORPHS:
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
			case TaraBuildConstants.LANGUAGES_PATH:
				configuration.setLanguagesDirectory(reader.readLine());
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
		System.out.print(TaraBuildConstants.MESSAGES_START);
		System.out.print(message.getCategory());
		System.out.print(TaraBuildConstants.SEPARATOR);
		System.out.print(message.getMessage());
		System.out.print(TaraBuildConstants.SEPARATOR);
		System.out.print(message.getUrl());
		System.out.print(TaraBuildConstants.SEPARATOR);
		System.out.print(message.getLineNum());
		System.out.print(TaraBuildConstants.SEPARATOR);
		System.out.print(message.getColumnNum());
		System.out.print(TaraBuildConstants.SEPARATOR);
		System.out.print(TaraBuildConstants.MESSAGES_END);
		System.out.println();
	}

	private static void reportCompiledItems(List<TaraCompiler.OutputItem> compiledFiles) {
		for (TaraCompiler.OutputItem compiledFile : compiledFiles) {
			System.out.print(TaraBuildConstants.COMPILED_START);
			System.out.print(compiledFile.getOutputPath());
			System.out.print(TaraBuildConstants.SEPARATOR);
			System.out.print(compiledFile.getSourceFile());
			System.out.print(TaraBuildConstants.COMPILED_END);
			System.out.println();
		}
	}

	private static void reportNotCompiledItems(Map<File, Boolean> toRecompile) {
		for (File file : toRecompile.keySet()) {
			System.out.print(TaraBuildConstants.TO_RECOMPILE_START);
			System.out.print(file.getAbsolutePath());
			System.out.print(TaraBuildConstants.TO_RECOMPILE_END);
			System.out.println();
		}
	}
}
