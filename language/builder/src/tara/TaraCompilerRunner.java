package tara;

import tara.compiler.TaraCompiler;
import tara.compiler.constants.TaraBuildConstants;
import tara.compiler.constants.TaraCompilerMessageCategories;
import tara.compiler.core.CompilationUnit;
import tara.compiler.core.CompilerConfiguration;
import tara.compiler.core.CompilerMessage;
import tara.compiler.core.SourceUnit;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static java.lang.System.out;
import static tara.compiler.constants.TaraBuildConstants.*;

public class TaraCompilerRunner {
	private static final String TARA_FILE_EXTENSION = ".tara";
	private final File argsFile;
	private final boolean verbose;

	public TaraCompilerRunner(File argsFile, boolean verbose) {
		this.argsFile = argsFile;
		this.verbose = verbose;
	}

	boolean run() {
		final CompilerConfiguration config = new CompilerConfiguration();
		config.setVerbose(verbose);
		final List<Map<File, Boolean>> srcFiles = initSourceMap();
		final List<CompilerMessage> compilerMessages = new ArrayList<>();
		CompilationInfoExtractor.getInfoFromArgsFile(argsFile, config, srcFiles);
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
			if (!file.getKey().getName().endsWith(TARA_FILE_EXTENSION)) continue;
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

	private static void addSources(Map<File, Boolean> srcFiles, final CompilationUnit unit) {
		srcFiles.entrySet().stream().
			filter(file -> file.getKey().getName().endsWith(TARA_FILE_EXTENSION)).
			forEach(file -> unit.addSource(new SourceUnit(file.getKey(), unit.getConfiguration(), unit.getErrorCollector(), file.getValue())));
	}

	private static void printMessage(CompilerMessage message) {
		out.print(TaraBuildConstants.MESSAGES_START);
		out.print(message.getCategory());
		out.print(SEPARATOR);
		out.print(message.getMessage());
		out.print(SEPARATOR);
		out.print(message.getUrl());
		out.print(SEPARATOR);
		out.print(message.getLineNum());
		out.print(SEPARATOR);
		out.print(message.getColumnNum());
		out.print(SEPARATOR);
		out.print(TaraBuildConstants.MESSAGES_END);
		out.println();
	}

	private static void reportCompiledItems(List<TaraCompiler.OutputItem> compiledFiles) {
		for (TaraCompiler.OutputItem compiledFile : compiledFiles) {
			out.print(COMPILED_START);
			out.print(compiledFile.getOutputPath());
			out.print(SEPARATOR);
			out.print(compiledFile.getSourceFile());
			out.print(COMPILED_END);
			out.println();
		}
	}

	private static void reportNotCompiledItems(List<Map<File, Boolean>> toRecompile) {
		for (Map<File, Boolean> entry : toRecompile) {
			for (File file : entry.keySet()) {
				out.print(TO_RECOMPILE_START);
				out.print(file.getAbsolutePath());
				out.print(TO_RECOMPILE_END);
				out.println();
			}
		}

	}
}
