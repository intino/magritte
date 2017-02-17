package io.intino.tara;

import io.intino.tara.compiler.TaraCompiler;
import io.intino.tara.compiler.core.CompilationUnit;
import io.intino.tara.compiler.core.CompilerConfiguration;
import io.intino.tara.compiler.core.CompilerMessage;
import io.intino.tara.compiler.core.SourceUnit;
import io.intino.tara.compiler.shared.Configuration.Level;
import io.intino.tara.compiler.shared.TaraBuildConstants;
import io.intino.tara.compiler.shared.TaraCompilerMessageCategories;

import java.io.File;
import java.util.*;

import static io.intino.tara.compiler.shared.TaraBuildConstants.*;
import static java.lang.System.out;

class TaraCompilerRunner {
	private final boolean verbose;

	TaraCompilerRunner(boolean verbose) {
		this.verbose = verbose;
	}

	boolean run(File argsFile) {
		final CompilerConfiguration config = new CompilerConfiguration();
		config.setVerbose(verbose);
		final Map<File, Boolean> sources = new LinkedHashMap<>();
		CompilationInfoExtractor.getInfoFromArgsFile(argsFile, config, sources);
		if (sources.isEmpty()) return true;
		if (verbose) out.println(PRESENTABLE_MESSAGE + "Tarac: loading sources...");
		final List<CompilerMessage> messages = new ArrayList<>();
		List<TaraCompiler.OutputItem> compiled = compile(config, sources, messages);
		if (verbose) report(sources, compiled);
		processErrors(messages);
		return false;
	}


	boolean run(CompilerConfiguration config, List<File> files) {
		config.setVerbose(verbose);
		if (verbose) out.println(PRESENTABLE_MESSAGE + "Tarac: loading sources...");
		final List<CompilerMessage> messages = new ArrayList<>();
		final Map<File, Boolean> sources = new LinkedHashMap<>();
		files.forEach(f -> sources.put(f, Boolean.TRUE));
		List<TaraCompiler.OutputItem> compiled = compile(config, sources, messages);
		if (verbose) report(sources, compiled);
		processErrors(messages);
		return false;
	}


	private List<TaraCompiler.OutputItem> compile(CompilerConfiguration config, Map<File, Boolean> sources, List<CompilerMessage> messages) {
		List<TaraCompiler.OutputItem> compiled = new ArrayList<>();
		if (!sources.values().contains(false)) CompilationUnit.cleanOut(config);
		if (!config.isTest()) compiled.addAll(compileSources(config, sources, messages));
		else compiled.addAll(compileTests(config, sources, messages));
		return compiled;
	}

	private List<TaraCompiler.OutputItem> compileSources(CompilerConfiguration config, Map<File, Boolean> sources, List<CompilerMessage> messages) {
		if (verbose) out.println(PRESENTABLE_MESSAGE + "Tarac: Compiling model...");
		out.println();
		final CompilationUnit unit = new CompilationUnit(config);
		addSources(sources, unit);
		return new TaraCompiler(messages).compile(unit);
	}

	private void report(Map<File, Boolean> srcFiles, List<TaraCompiler.OutputItem> compiled) {
		if (compiled.isEmpty()) reportNotCompiledItems(srcFiles);
		else reportCompiledItems(compiled);
		out.println();
	}

	private List<TaraCompiler.OutputItem> compileTests(CompilerConfiguration config, Map<File, Boolean> testFiles, List<CompilerMessage> compilerMessages) {
		if (testFiles.isEmpty()) return Collections.emptyList();
		if (verbose) out.println(PRESENTABLE_MESSAGE + "Tarac: compiling tests...");
		CompilerConfiguration testConf = config.clone();
		testConf.setTest(true);
		if (config.outDSL() != null) testConf.addLanguage(config.outDSL(), config.version());
		if (config.level() != null)
			testConf.level(Level.values()[config.level().ordinal() == 0 ? 0 : config.level().ordinal() - 1]);
		List<TaraCompiler.OutputItem> compiledFiles = new ArrayList<>();
		for (Map.Entry<File, Boolean> file : testFiles.entrySet()) {
			final CompilationUnit unit = new CompilationUnit(testConf);
			if (verbose) out.println(PRESENTABLE_MESSAGE + "Tarac: compiling test: " + file.getKey().getName());
			unit.addSource(new SourceUnit(file.getKey(), unit.getConfiguration(), unit.getErrorCollector(), file.getValue()));
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
		srcFiles.entrySet().forEach(file -> unit.addSource(new SourceUnit(file.getKey(), unit.getConfiguration(), unit.getErrorCollector(), file.getValue())));
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
