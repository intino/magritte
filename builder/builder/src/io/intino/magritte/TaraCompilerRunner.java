package io.intino.magritte;

import io.intino.Configuration;
import io.intino.magritte.compiler.TaraCompiler;
import io.intino.magritte.compiler.core.CompilationUnit;
import io.intino.magritte.compiler.core.CompilerConfiguration;
import io.intino.magritte.compiler.core.CompilerMessage;
import io.intino.magritte.compiler.core.SourceUnit;
import io.intino.magritte.compiler.shared.TaraBuildConstants;
import io.intino.magritte.compiler.shared.TaraCompilerMessageCategories;

import java.io.File;
import java.io.PrintStream;
import java.util.*;

import static io.intino.magritte.compiler.shared.TaraBuildConstants.*;

class TaraCompilerRunner {
	private final boolean verbose;
	private PrintStream out = System.out;

	TaraCompilerRunner(boolean verbose) {
		this.verbose = verbose;
	}

	boolean run(File argsFile) {
		final CompilerConfiguration config = new CompilerConfiguration();
		final Map<File, Boolean> sources = new LinkedHashMap<>();
		CompilationInfoExtractor.getInfoFromArgsFile(argsFile, config, sources);
		config.setVerbose(verbose);
		config.out(System.out);
		this.out = config.out();
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
		this.out = config.out();
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
		if (!sources.containsValue(false)) CompilationUnit.cleanOut(config);
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
		out.println();
		CompilerConfiguration testConf = config.clone();
		testConf.setTest(true);
		testConf.workingPackage(testConf.workingPackage() + ".test");
		if (config.model().outDsl() != null) testConf.addLanguage(config.model().outDsl(), config.version());
		if (config.model().level() != null)
			testConf.model().level(Configuration.Artifact.Model.Level.values()[config.model().level().ordinal() == 0 ? 0 : config.model().level().ordinal() - 1]);
		List<TaraCompiler.OutputItem> outputs = new ArrayList<>();
		for (File file : testFiles.keySet()) {
			testConf.model().outDsl(file.getName());
			final CompilationUnit unit = new CompilationUnit(testConf);
			addSources(Collections.singletonMap(file, true), unit);
			outputs.addAll(new TaraCompiler(compilerMessages).compile(unit));
		}
		return outputs;
	}

	private void processErrors(List<CompilerMessage> compilerMessages) {
		int errorCount = 0;
		for (CompilerMessage message : compilerMessages) {
			if (message.getCategory().equals(TaraCompilerMessageCategories.ERROR)) {
				if (errorCount > 100) continue;
				errorCount++;
			}
			printMessage(message);
		}
	}

	private void addSources(Map<File, Boolean> srcFiles, final CompilationUnit unit) {
		srcFiles.forEach((key, value) -> unit.addSource(new SourceUnit(key, unit.configuration(), unit.getErrorCollector(), value)));
	}

	private void printMessage(CompilerMessage message) {
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

	private void reportCompiledItems(List<TaraCompiler.OutputItem> compiledFiles) {
		for (TaraCompiler.OutputItem compiledFile : compiledFiles) {
			out.print(COMPILED_START);
			out.print(compiledFile.getOutputPath());
			out.print(SEPARATOR);
			out.print(compiledFile.getSourceFile());
			out.print(COMPILED_END);
			out.println();
		}
	}

	private void reportNotCompiledItems(Map<File, Boolean> toRecompile) {
		for (File file : toRecompile.keySet()) {
			out.print(TO_RECOMPILE_START);
			out.print(file.getAbsolutePath());
			out.print(TO_RECOMPILE_END);
			out.println();
		}
	}
}
