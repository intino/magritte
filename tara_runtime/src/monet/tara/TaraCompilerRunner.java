package monet.tara;

import monet.tara.compiler.TaraCompiler;
import monet.tara.compiler.core.CompilationUnit;
import monet.tara.compiler.core.CompilerConfiguration;
import monet.tara.compiler.core.CompilerMessage;
import monet.tara.compiler.core.SourceUnit;
import monet.tara.compiler.rt.TaraCompilerMessageCategories;
import monet.tara.compiler.rt.TaraRtConstants;
import org.codehaus.groovy.control.messages.WarningMessage;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TaraCompilerRunner {

	static boolean runTaraCompiler(File argsFile, boolean pluginGeneration) {
		final CompilerConfiguration config = new CompilerConfiguration();
		config.setOutput(new PrintWriter(System.err));
		config.setWarningLevel(WarningMessage.PARANOIA);

		final List<CompilerMessage> compilerMessages = new ArrayList<>();
		final List<File> srcFiles = new ArrayList<>();
		fillFromArgsFile(argsFile, config, srcFiles);
		if (srcFiles.isEmpty()) return true;
		System.out.println(TaraRtConstants.PRESENTABLE_MESSAGE + "Tarac: loading sources...");

		final CompilationUnit unit = new CompilationUnit(pluginGeneration, config, null);
		addSources(srcFiles, unit);

		System.out.println("Tarac: compiling...");
		final List<TaraCompiler.OutputItem> compiledFiles = new TaraCompiler(pluginGeneration, compilerMessages).compile(unit);

		System.out.println();
		reportCompiledItems(compiledFiles);
		System.out.println();
		if (compiledFiles.isEmpty()) reportNotCompiledItems(srcFiles);
		int errorCount = 0;
		for (CompilerMessage message : compilerMessages) {
			if (message.getCategory().equals(TaraCompilerMessageCategories.ERROR)) {
				if (errorCount > 100) continue;
				errorCount++;
			}
			printMessage(message);
		}
		return false;
	}


	private static void fillFromArgsFile(File argsFile, CompilerConfiguration compilerConfiguration, List<File> srcFiles) {
		BufferedReader reader = null;
		FileInputStream stream;
		try {
			stream = new FileInputStream(argsFile);
			reader = new BufferedReader(new InputStreamReader(stream));
			String line;
			while ((line = reader.readLine()) != null) {
				if (!TaraRtConstants.SRC_FILE.equals(line)) break;
				final File file = new File(reader.readLine());
				srcFiles.add(file);
			}
			while (line != null) {
				if (line.startsWith(TaraRtConstants.ENCODING))
					compilerConfiguration.setSourceEncoding(reader.readLine());
				else if (line.startsWith(TaraRtConstants.OUTPUTPATH))
					compilerConfiguration.setTempDirectory(reader.readLine());
				else if (line.startsWith(TaraRtConstants.FINAL_OUTPUTPATH))
					compilerConfiguration.setTargetDirectory(reader.readLine());
				else if (line.startsWith(TaraRtConstants.PROJECT))
					compilerConfiguration.setProject(reader.readLine());
				line = reader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				assert reader != null;
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				//argsFile.delete();
			}
		}
	}


	private static void addSources(List<File> srcFiles, final CompilationUnit unit) {
		for (final File file : srcFiles) {
			if (!file.getName().endsWith(".m2"))
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

	private static void addExceptionInfo(List<CompilerMessage> compilerMessages, Throwable e, String message) {
		final StringWriter writer = new StringWriter();
		e.printStackTrace(new PrintWriter(writer));
		compilerMessages.add(new CompilerMessage(TaraCompilerMessageCategories.WARNING, message + ":\n" + writer, "<exception>", -1, -1));
	}
}
