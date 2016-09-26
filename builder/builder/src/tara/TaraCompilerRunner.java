package tara;

import tara.compiler.TaraCompiler;
import tara.compiler.TaraCompiler.OutputItem;
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

class TaraCompilerRunner {
	private final boolean verbose;

	TaraCompilerRunner(boolean verbose) {
		this.verbose = verbose;
	}

	boolean run(File argsFile) {
		final CompilerConfiguration config = new CompilerConfiguration();
		config.setVerbose(verbose);
		final Map<File, Boolean> sources = new LinkedHashMap<>();
		getInfoFromArgsFile(argsFile, config, sources);
		if (sources.isEmpty()) return true;
		if (verbose) out.println(PRESENTABLE_MESSAGE + "Tarac: loading sources...");
		final List<CompilerMessage> messages = new ArrayList<>();
		List<OutputItem> compiled = compile(config, sources, messages);
		if (verbose) report(sources, compiled);
		processErrors(messages);
		return false;
	}


	boolean run(CompilerConfiguration config, File source) {
		config.setVerbose(verbose);
		if (verbose) out.println(PRESENTABLE_MESSAGE + "Tarac: loading sources...");
		final List<CompilerMessage> messages = new ArrayList<>();
		final Map<File, Boolean> sources = Collections.singletonMap(source, true);
		List<OutputItem> compiled = compile(config, sources, messages);
		if (verbose) report(sources, compiled);
		processErrors(messages);
		return false;
	}


	private List<OutputItem> compile(CompilerConfiguration config, Map<File, Boolean> sources, List<CompilerMessage> messages) {
		List<OutputItem> compiled = new ArrayList<>();
		if (!sources.values().contains(false)) CompilationUnit.cleanOut(config);
		if (!config.isTest()) compiled.addAll(compileSources(config, sources, messages));
		else compiled.addAll(compileTests(config, sources, messages));
		return compiled;
	}

	private List<OutputItem> compileSources(CompilerConfiguration config, Map<File, Boolean> sources, List<CompilerMessage> messages) {
		if (verbose) out.println(PRESENTABLE_MESSAGE + "Tarac: Compiling model...");
		out.println();
		final CompilationUnit unit = new CompilationUnit(config);
		addSources(sources, unit);
		return new TaraCompiler(messages).compile(unit);
	}

	private void report(Map<File, Boolean> srcFiles, List<OutputItem> compiled) {
		if (compiled.isEmpty()) reportNotCompiledItems(srcFiles);
		else reportCompiledItems(compiled);
		out.println();
	}

	private List<OutputItem> compileTests(CompilerConfiguration config, Map<File, Boolean> testFiles, List<CompilerMessage> compilerMessages) {
		if (testFiles.isEmpty()) return Collections.emptyList();
		if (verbose) out.println(PRESENTABLE_MESSAGE + "Tarac: compiling tests...");
		CompilerConfiguration testConf = config.clone();
		testConf.setTest(true);
		testConf.language(testConf.outDSL());
		testConf.moduleType(ModuleType.values()[config.moduleType().ordinal() == 0 ? 0 : config.moduleType().ordinal() - 1]);
		List<OutputItem> compiledFiles = new ArrayList<>();
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

	private static void reportCompiledItems(List<OutputItem> compiledFiles) {
		for (OutputItem compiledFile : compiledFiles) {
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
