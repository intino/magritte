package io.intino.magritte.builder;

import io.intino.magritte.builder.TaraCompiler.OutputItem;
import io.intino.magritte.builder.core.*;
import io.intino.magritte.builder.core.operation.Operation;
import io.intino.magritte.builder.shared.TaraBuildConstants;
import io.intino.magritte.builder.shared.TaraCompilerMessageCategories;
import io.intino.magritte.builder.utils.FileSystemUtils;

import java.io.File;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static io.intino.Configuration.Artifact.Model.Level.values;
import static io.intino.magritte.builder.shared.TaraBuildConstants.*;

public class TaraCompilerRunner {
	private final boolean verbose;
	private final List<Class<? extends Operation>> codeGenerationOperations;
	private PrintStream out = System.out;

	public TaraCompilerRunner(boolean verbose, List<Class<? extends Operation>> codeGenerationOperations) {
		this.verbose = verbose;
		this.codeGenerationOperations = codeGenerationOperations;
	}

	public TaraCompilerRunner(boolean verbose) {
		this(verbose, List.of());
	}

	public void run(File argsFile) {
		final CompilerConfiguration config = new CompilerConfiguration();
		final Map<File, Boolean> sources = new LinkedHashMap<>();
		CompilationInfoExtractor.getInfoFromArgsFile(argsFile, config, sources);
		config.setVerbose(verbose);
		config.out(System.out);
		this.out = config.out();
		if (sources.isEmpty()) return;
		if (verbose) out.println(PRESENTABLE_MESSAGE + "Tarac: loading sources...");
		final List<CompilerMessage> messages = new ArrayList<>();
		List<OutputItem> compiled = compile(config, sources, messages);
		if (verbose) report(sources, compiled);
		processErrors(messages);
		FileSystemUtils.removeDir(config.getTempDirectory());
	}


	public void run(CompilerConfiguration config, List<File> files) {
		config.setVerbose(verbose);
		this.out = config.out();
		if (verbose) out.println(PRESENTABLE_MESSAGE + "Tarac: loading sources...");
		final List<CompilerMessage> messages = new ArrayList<>();
		final Map<File, Boolean> sources = new LinkedHashMap<>();
		files.forEach(f -> sources.put(f, Boolean.TRUE));
		List<OutputItem> compiled = compile(config, sources, messages);
		if (verbose) report(sources, compiled);
		processErrors(messages);
		FileSystemUtils.removeDir(config.getTempDirectory());
	}


	private List<OutputItem> compile(CompilerConfiguration config, Map<File, Boolean> sources, List<CompilerMessage> messages) {
		List<OutputItem> compiled = new ArrayList<>();
//		if (!sources.containsValue(false)) CompilationUnit.cleanOut(config);
		if (!config.isTest()) compiled.addAll(compileSources(config, sources, messages));
		else compiled.addAll(compileTests(config, sources, messages));
		return compiled;
	}

	private List<OutputItem> compileSources(CompilerConfiguration config, Map<File, Boolean> sources, List<CompilerMessage> messages) {
		if (verbose) out.println(PRESENTABLE_MESSAGE + "Tarac: Compiling model...");
		out.println();
		final CompilationUnit unit = new CompilationUnit(config);
		addCodeGenerationOperations(unit);
		addSources(sources, unit);
		return new TaraCompiler(messages).compile(unit);
	}

	private void addCodeGenerationOperations(CompilationUnit unit) {
		for (Class<? extends Operation> codeGenerationOperation : codeGenerationOperations)
			try {
				unit.addPhaseOperation((Operation) codeGenerationOperation.getDeclaredConstructors()[0].newInstance(unit), Phases.CODE_GENERATION);
			} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
				Logger.getGlobal().log(Level.SEVERE, e.getMessage() == null ? e.getStackTrace()[0].toString() : e.getMessage());
			}
	}

	private void report(Map<File, Boolean> srcFiles, List<OutputItem> compiled) {
//		if (compiled.isEmpty()) reportNotCompiledItems(srcFiles);
//		else reportCompiledItems(compiled);
		out.println();
	}

	private List<OutputItem> compileTests(CompilerConfiguration config, Map<File, Boolean> testFiles, List<CompilerMessage> compilerMessages) {
		if (testFiles.isEmpty()) return Collections.emptyList();
		if (verbose) out.println(PRESENTABLE_MESSAGE + "Tarac: compiling tests...");
		out.println();
		CompilerConfiguration testConf = config.clone();
		testConf.setTest(true);
		testConf.workingPackage(testConf.workingPackage() + ".test");
		if (config.model().outDsl() != null) testConf.addLanguage(config.model().outDsl(), config.version());
		if (config.model().level() != null)
			testConf.model().level(values()[config.model().level().ordinal() == 0 ? 0 : config.model().level().ordinal() - 1]);
		List<OutputItem> outputs = new ArrayList<>();
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

	private void reportCompiledItems(List<OutputItem> compiledFiles) {
		for (OutputItem compiledFile : compiledFiles) {
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
