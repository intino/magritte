package monet.tara;

import monet.tara.compiler.rt.TaraCompilerMessageCategories;
import monet.tara.compiler.rt.TaraRtConstants;
import monet.tara.transpiler.TaraTranspiler;
import monet.tara.transpiler.core.*;

import java.io.*;
import java.util.*;

/**
 * Created by oroncal on 27/12/13.
 */
public class TaraTranspilerRunner {
	static boolean runTaraTranspiler(File argsFile, String outputPath) {
		final TranspilerConfiguration config = new TranspilerConfiguration();
		final List<TranspilerMessage> transpilerMessages = new ArrayList<>();
		final List<File> srcFiles = new ArrayList<>();
		final Map<String, File> class2File = new HashMap<>();

		final String[] finalOutput = new String[1];
		fillFromArgsFile(argsFile, config, transpilerMessages, srcFiles, class2File, finalOutput);
		if (srcFiles.isEmpty()) return true;
		Map<String, Object> options = new HashMap<>();
		options.put("keepStubs", Boolean.TRUE);

		System.out.println(TaraRtConstants.PRESENTABLE_MESSAGE + "Tarat: loading sources...");


		final TranspilationUnit unit = createTranspilationUnit(config, finalOutput[0]);
		unit.addPhaseOperation(new TranspilationUnit.SourceUnitOperation() {
			@Override
			public void call(monet.tara.transpiler.core.SourceUnit unit) throws TranspilationFailedException {
				return;//operaciones de conversion
			}
		}, Phases.CONVERSION);

		addSources(srcFiles, unit);
		runSemanticChecker(transpilerMessages, unit, srcFiles);

		System.out.println("Tarat: Transpiling...");
		final List<TaraTranspiler.OutputItem> compiledFiles = new TaraTranspiler(transpilerMessages).compile(unit);

		System.out.println();
		reportCompiledItems(compiledFiles);

		System.out.println();
		if (compiledFiles.isEmpty()) {
			reportNotCompiledItems(srcFiles);
		}
		return false;
	}

	private static TranspilationUnit createTranspilationUnit(TranspilerConfiguration config, String s) {
		return null;
	}

	private static String fillFromArgsFile(File argsFile, TranspilerConfiguration compilerConfiguration,
	                                       List<TranspilerMessage> transpilerMessages, List<File> srcFiles,
	                                       Map<String, File> class2File, String[] finalOutput) {
		return null;
	}


	private static void addSources(List<File> srcFiles, final TranspilationUnit unit) {
		for (final File file : srcFiles) {
			if (!file.getName().endsWith(".m2"))
				continue;

			unit.addSource(new SourceUnit(file, unit.getConfiguration(), unit.getErrorCollector()));
		}
	}

	private static void runSemanticChecker(List<TranspilerMessage> compilerMessages, TranspilationUnit unit, List<File> srcFiles) {
	}

	private static void printMessage(TranspilerMessage message) {
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

	private static void reportCompiledItems(List<TaraTranspiler.OutputItem> compiledFiles) {
		for (TaraTranspiler.OutputItem compiledFile : compiledFiles) {
	  /*
	  * output path
      * source file
      * output root directory
      */
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

	private static void addExceptionInfo(List<TranspilerMessage> compilerMessages, Throwable e, String message) {
		final StringWriter writer = new StringWriter();
		e.printStackTrace(new PrintWriter(writer));
		compilerMessages.add(new TranspilerMessage(TaraCompilerMessageCategories.WARNING, message + ":\n" + writer, "<exception>", -1, -1));
	}
}
