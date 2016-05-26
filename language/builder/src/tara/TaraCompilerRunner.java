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
import java.io.IOException;
import java.nio.file.Files;
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
		List<TaraCompiler.OutputItem> compiled = new ArrayList<>();
		CompilationUnit.cleanOut(config);
		if (!config.isTest()) compiled.addAll(compileSources(config, sources, messages));
		else compiled.addAll(compileTests(config, sources, messages));
		return compiled;
	}

	private List<TaraCompiler.OutputItem> compileSources(CompilerConfiguration config, Map<File, Boolean> sources, List<CompilerMessage> messages) {
		final ModuleType type = config.moduleType();
		List<TaraCompiler.OutputItem> compiled = new ArrayList<>();
		if (type.equals(ProductLine) || type.equals(Platform))
			compiled.addAll(compilePlatform(config, filter(sources, config.platformLanguage()), messages));
		if (hasErrors(messages)) return compiled;
		if (type.equals(ProductLine) || type.equals(Application) || type.equals(Ontology))
			compiled.addAll(compileApplication(config, filter(sources, config.applicationLanguage()), messages));
		if (hasErrors(messages)) return compiled;
		if (type.equals(ProductLine) || type.equals(System) || !filter(sources, config.systemLanguage()).isEmpty())
			compiled.addAll(compileSystem(config, filter(sources, config.systemLanguage()), messages));
		return compiled;
	}

	private boolean hasErrors(List<CompilerMessage> messages) {
		for (CompilerMessage message : messages) if (message.getCategory().equals(CompilerMessage.ERROR)) return true;
		return false;
	}


	private void report(Map<File, Boolean> srcFiles, List<TaraCompiler.OutputItem> compiled) {
		if (compiled.isEmpty()) reportNotCompiledItems(srcFiles);
		else reportCompiledItems(compiled);
		out.println();
	}

	private List<TaraCompiler.OutputItem> compilePlatform(CompilerConfiguration config, Map<File, Boolean> srcFiles, List<CompilerMessage> compilerMessages) {
		if (srcFiles.isEmpty()) return Collections.emptyList();
		List<TaraCompiler.OutputItem> compiledFiles;
		CompilerConfiguration clone = config.clone();
		clone.moduleType(ModuleType.Platform);
		clone.setTest(false);
		final CompilationUnit unit = new CompilationUnit(clone);
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
		modelConf.moduleType(ModuleType.Application);
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
		modelConf.moduleType(ModuleType.System);
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
		CompilerConfiguration testConf = config.clone();
		testConf.moduleType(Application);
		final Map<File, Boolean> appTests = filter(testFiles, config.applicationLanguage());
		compileApplication(config, appTests, compilerMessages);
		List<TaraCompiler.OutputItem> compiledFiles = new ArrayList<>();
		testConf.moduleType(ModuleType.System);
		testConf.setTest(true);
		appTests.keySet().stream().forEach(testFiles::remove);
		for (Map.Entry<File, Boolean> file : testFiles.entrySet()) {
			final CompilationUnit unit = new CompilationUnit(testConf);
			unit.addSource(new SourceUnit(file.getKey(), unit.getConfiguration(), unit.getErrorCollector(), file.getValue()));
			if (verbose) out.println(PRESENTABLE_MESSAGE + "Tarac: compiling tests...");
			compiledFiles.addAll(new TaraCompiler(compilerMessages).compile(unit));
			out.println();
		}
		return compiledFiles;
	}


	private Map<File, Boolean> filter(Map<File, Boolean> sources, Language language) {
		Map<File, Boolean> candidates = new HashMap();
		if (language == null) return candidates;
		for (File file : sources.keySet())
			try {
				String fileText = new String(Files.readAllBytes(file.toPath())).trim();
				fileText = fileText.substring(0, fileText.indexOf("\n")).trim();
				if (fileText.startsWith("dsl " + language.languageName()) || fileText.startsWith("dsl " + language.languageName()))
					candidates.put(file, sources.get(file));
			} catch (IOException ignored) {
				out.println(ignored.getMessage());
			}
		return candidates;
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
