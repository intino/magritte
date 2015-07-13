package siani.tara;

import siani.tara.compiler.TaraCompiler;
import siani.tara.compiler.core.CompilationUnit;
import siani.tara.compiler.core.CompilerConfiguration;
import siani.tara.compiler.core.CompilerMessage;
import siani.tara.compiler.core.SourceUnit;
import siani.tara.compiler.core.errorcollection.TaraCompilerMessageCategories;
import siani.tara.compiler.core.errorcollection.TaraRtConstants;
import siani.tara.compiler.core.errorcollection.message.WarningMessage;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

class TaraToStashRunner {
	private static final Logger LOG = Logger.getLogger(TaraToStashRunner.class.getName());

	private TaraToStashRunner() {
	}

	static boolean runTaraCompiler(String[] args) {
		final CompilerConfiguration config = new CompilerConfiguration();
		final List<File> srcFiles = new ArrayList<>();
		final List<CompilerMessage> compilerMessages = new ArrayList<>();
		getInfoFromArgs(args, config, srcFiles);
		if (srcFiles.isEmpty()) return true;
		System.out.println("Tarac: loading sources...");
		final CompilationUnit unit = new CompilationUnit(config);
		addSources(srcFiles, unit);
		System.out.println("Tarac: compiling...");
		final List<TaraCompiler.OutputItem> compiledFiles = new TaraCompiler(compilerMessages).compile(unit);
		System.out.println();
		if (compiledFiles.isEmpty()) reportNotCompiledItems(srcFiles);
		else reportCompiledItems(compiledFiles);
		System.out.println();
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

	private static void getInfoFromArgs(String[] args, CompilerConfiguration configuration, List<File> srcFiles) {
		configuration.setOutput(new PrintWriter(System.err));
		configuration.setWarningLevel(WarningMessage.PARANOIA);
		srcFiles.add(new File(args[0]));
		configuration.setSourceEncoding(args[1]);
		configuration.setOutDirectory(new File(args[0]).getParent());
	}

	private static void addSources(List<File> srcFiles, final CompilationUnit unit) {
		for (final File file : srcFiles) {
			if (!file.getName().endsWith(".tara"))
				continue;
			unit.addSource(new SourceUnit(file, unit.getConfiguration(), unit.getErrorCollector()));
		}
	}

	private static void printMessage(CompilerMessage message) {
		System.out.print(TaraRtConstants.MESSAGES_START);
		System.out.print(message.getCategory());
		System.out.print(TaraRtConstants.SEPARATOR);
		System.out.print(message.getMessage());
		System.out.print(TaraRtConstants.SEPARATOR);
		System.out.print(message.getUrl());
		System.out.print(TaraRtConstants.SEPARATOR);
		System.out.print(message.getLineNum());
		System.out.print(TaraRtConstants.SEPARATOR);
		System.out.print(message.getColumnNum());
		System.out.print(TaraRtConstants.SEPARATOR);
		System.out.print(TaraRtConstants.MESSAGES_END);
		System.out.println();
	}

	private static void reportCompiledItems(List<TaraCompiler.OutputItem> compiledFiles) {
		for (TaraCompiler.OutputItem compiledFile : compiledFiles) {
			System.out.print(TaraRtConstants.COMPILED_START);
			System.out.print(compiledFile.getOutputPath());
			System.out.print(TaraRtConstants.SEPARATOR);
			System.out.print(compiledFile.getSourceFile());
			System.out.print(TaraRtConstants.COMPILED_END);
			System.out.println();
		}
	}

	private static void reportNotCompiledItems(Collection<File> toRecompile) {
		for (File file : toRecompile) {
			System.out.print(TaraRtConstants.TO_RECOMPILE_START);
			System.out.print(file.getAbsolutePath());
			System.out.print(TaraRtConstants.TO_RECOMPILE_END);
			System.out.println();
		}
	}
}
