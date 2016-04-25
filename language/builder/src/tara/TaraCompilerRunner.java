package tara;

import tara.compiler.TaraCompiler;
import tara.compiler.constants.TaraBuildConstants;
import tara.compiler.constants.TaraCompilerMessageCategories;
import tara.compiler.core.CompilationUnit;
import tara.compiler.core.CompilerConfiguration;
import tara.compiler.core.CompilerConfiguration.ModuleType;
import tara.compiler.core.CompilerMessage;
import tara.compiler.core.SourceUnit;

import java.io.File;
import java.util.*;

import static java.lang.System.out;
import static tara.CompilationInfoExtractor.getInfoFromArgsFile;
import static tara.compiler.constants.TaraBuildConstants.*;
import static tara.compiler.core.CompilerConfiguration.ModuleType.*;
import static tara.compiler.core.CompilerConfiguration.ModuleType.System;

class TaraCompilerRunner {
	private static final String TARA_FILE_EXTENSION = ".tara";
	private final File argsFile;
	private final boolean verbose;

	TaraCompilerRunner(File argsFile, boolean verbose) {
		this.argsFile = argsFile;
		this.verbose = verbose;
	}

	boolean run() {
		final CompilerConfiguration config = new CompilerConfiguration();
		config.setVerbose(verbose);
		final Map<File, Boolean> sources = new LinkedHashMap<>();
		getInfoFromArgsFile(argsFile, config, sources);
		if (sources.isEmpty()) return true;
		if (verbose) out.println(PRESENTABLE_MESSAGE + "Tarac: loading sources...");
		final List<CompilerMessage> messages = new ArrayList<>();
		List<TaraCompiler.OutputItem> compiled = compile(config, sources, messages);
		if (verbose) report(sources, compiled);
		processErrors(messages);
		return false;
	}

	private List<TaraCompiler.OutputItem> compile(CompilerConfiguration config, Map<File, Boolean> sources, List<CompilerMessage> messages) {
		final ModuleType type = config.modelType();
		List<TaraCompiler.OutputItem> compiled = new ArrayList<>();
		if (type.equals(ProductLine) || type.equals(Platform))
			compiled.addAll(compilePlatform(config, sources(sources, config.testDirectory()), messages));
		if (type.equals(ProductLine) || type.equals(Application) || type.equals(Ontology))
			compiled.addAll(compileApplication(config, sources(sources, config.testDirectory()), messages));
		if (type.equals(ProductLine) || type.equals(System))
			compiled.addAll(compileSystem(config, sources(sources, config.testDirectory()), messages));
		compiled.addAll(compileTests(config, testSources(sources, config.testDirectory()), messages));
		return compiled;
	}

	private Map<File, Boolean> sources(Map<File, Boolean> srcFiles, File testDirectory) {
		Map<File, Boolean> map = new HashMap<>();
		final Map<File, Boolean> tests = testSources(srcFiles, testDirectory);
		srcFiles.entrySet().stream().filter(v -> !tests.containsKey(v.getKey())).forEach(e -> map.put(e.getKey(), e.getValue()));
		return map;
	}

	private Map<File, Boolean> testSources(Map<File, Boolean> srcFiles, File testDirectory) {
		Map<File, Boolean> map = new HashMap<>();
		srcFiles.entrySet().stream().filter(entry -> entry.getKey().getPath().startsWith(testDirectory.getPath())).forEach(entry -> map.put(entry.getKey(), entry.getValue()));
		return map;
	}

	private void report(Map<File, Boolean> srcFiles, List<TaraCompiler.OutputItem> compiled) {
		if (compiled.isEmpty()) reportNotCompiledItems(srcFiles);
		else reportCompiledItems(compiled);
		out.println();
	}

	private List<TaraCompiler.OutputItem> compilePlatform(CompilerConfiguration config, Map<File, Boolean> srcFiles, List<CompilerMessage> compilerMessages) {
		if (srcFiles.isEmpty()) return Collections.emptyList();
		List<TaraCompiler.OutputItem> compiledFiles;
		config.setTest(false);
		final CompilationUnit unit = new CompilationUnit(config);
		addSources(srcFiles, unit);
		if (verbose) out.println(PRESENTABLE_MESSAGE + "Tarac: compiling definitions...");
		compiledFiles = new TaraCompiler(compilerMessages).compile(unit);
		out.println();
		return compiledFiles;
	}

	private List<TaraCompiler.OutputItem> compileApplication(CompilerConfiguration config, Map<File, Boolean> srcFiles, List<CompilerMessage> compilerMessages) {
		if (srcFiles.isEmpty()) return Collections.emptyList();
		List<TaraCompiler.OutputItem> compiledFiles;
		CompilerConfiguration modelConf = config.clone();
		modelConf.setModuleType(ModuleType.Application);
		modelConf.setTest(false);
		final CompilationUnit unit = new CompilationUnit(modelConf);
		addSources(srcFiles, unit);
		if (verbose) out.println(PRESENTABLE_MESSAGE + "Tarac: compiling model...");
		compiledFiles = new TaraCompiler(compilerMessages).compile(unit);
		out.println();
		return compiledFiles;
	}

	private List<TaraCompiler.OutputItem> compileSystem(CompilerConfiguration config, Map<File, Boolean> srcFiles, List<CompilerMessage> compilerMessages) {
		if (srcFiles.isEmpty()) return Collections.emptyList();
		List<TaraCompiler.OutputItem> compiledFiles;
		CompilerConfiguration modelConf = config.clone();
		modelConf.setModuleType(ModuleType.System);
		modelConf.setTest(false);
		final CompilationUnit unit = new CompilationUnit(modelConf);
		addSources(srcFiles, unit);
		if (verbose) out.println(PRESENTABLE_MESSAGE + "Tarac: compiling model...");
		compiledFiles = new TaraCompiler(compilerMessages).compile(unit);
		out.println();
		return compiledFiles;
	}

	private List<TaraCompiler.OutputItem> compileTests(CompilerConfiguration config, Map<File, Boolean> testFiles, List<CompilerMessage> compilerMessages) {
		if (testFiles.isEmpty()) return Collections.emptyList();
		List<TaraCompiler.OutputItem> compiledFiles = new ArrayList<>();
		CompilerConfiguration testConf = config.clone();
		testConf.setModuleType(ModuleType.System);
		testConf.setTest(true);
		for (Map.Entry<File, Boolean> file : testFiles.entrySet()) {
			final CompilationUnit unit = new CompilationUnit(testConf);
			unit.addSource(new SourceUnit(file.getKey(), unit.getConfiguration(), unit.getErrorCollector(), file.getValue()));
			if (verbose) out.println(PRESENTABLE_MESSAGE + "Tarac: compiling tests...");
			compiledFiles.addAll(new TaraCompiler(compilerMessages).compile(unit));
			out.println();
		}
		return compiledFiles;
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
		out.print(MESSAGES_START);
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

	private static void reportNotCompiledItems(Map<File, Boolean> toRecompile) {
		for (File file : toRecompile.keySet()) {
			out.print(TO_RECOMPILE_START);
			out.print(file.getAbsolutePath());
			out.print(TO_RECOMPILE_END);
			out.println();
		}

	}
}
