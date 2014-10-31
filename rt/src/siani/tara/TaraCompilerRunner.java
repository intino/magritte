package siani.tara;

import siani.tara.compiler.TaraCompiler;
import siani.tara.compiler.core.CompilationUnit;
import siani.tara.compiler.core.CompilerConfiguration;
import siani.tara.compiler.core.CompilerMessage;
import siani.tara.compiler.core.SourceUnit;
import siani.tara.compiler.core.errorcollection.message.WarningMessage;
import siani.tara.compiler.rt.TaraCompilerMessageCategories;
import siani.tara.compiler.rt.TaraRtConstants;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

public class TaraCompilerRunner {
	private static final Logger LOG = Logger.getLogger(TaraCompilerRunner.class.getName());

	private TaraCompilerRunner() {
	}

	static boolean runTaraCompiler(File argsFile, boolean pluginGeneration) {
		final CompilerConfiguration config = new CompilerConfiguration();
		final List<File> srcFiles = new ArrayList<>();
		final List<CompilerMessage> compilerMessages = new ArrayList<>();
		getInfoFromArgsFile(argsFile, config, srcFiles);
		if (srcFiles.isEmpty()) return true;
		System.out.println(TaraRtConstants.PRESENTABLE_MESSAGE + "Tarac: loading sources...");
		final CompilationUnit unit = new CompilationUnit(pluginGeneration, config);
		addSources(srcFiles, unit);
		System.out.println(TaraRtConstants.PRESENTABLE_MESSAGE + "Tarac: compiling...");
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

	private static void getInfoFromArgsFile(File argsFile, CompilerConfiguration configuration, List<File> srcFiles) {
		BufferedReader reader = null;
		configuration.setOutput(new PrintWriter(System.err));
		configuration.setWarningLevel(WarningMessage.PARANOIA);
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(argsFile)));
			String line;
			while ((line = reader.readLine()) != null) {
				if (!TaraRtConstants.SRC_FILE.equals(line)) break;
				while (!(line = reader.readLine()).equals("")) {
					final File file = new File(line);
					srcFiles.add(file);
				}
			}
			processArgs(configuration, reader, line);
		} catch (IOException e) {
			LOG.severe("Error getting Args IO: " + e.getMessage());
		} finally {
			try {
				assert reader != null;
				reader.close();
			} catch (IOException e) {
				LOG.severe("Error getting Args IO2: " + e.getMessage());
			} finally {
				//argsFile.delete();
			}
		}
	}

	private static void processArgs(CompilerConfiguration configuration, BufferedReader reader, String line) throws IOException {
		String aLine = line;
		while (aLine != null) {
			switch (aLine) {
				case TaraRtConstants.ENCODING:
					configuration.setSourceEncoding(reader.readLine());
					break;
				case TaraRtConstants.OUTPUTPATH:
					configuration.setOutDirectory(reader.readLine());
					break;
				case TaraRtConstants.FINAL_OUTPUTPATH:
					configuration.setTargetDirectory(reader.readLine());
					break;
				case TaraRtConstants.PROJECT:
					configuration.setProject(reader.readLine());
					break;
				case TaraRtConstants.BUILD_NUMBER:
					configuration.setBuildNumber(Long.valueOf(reader.readLine()));
					break;
				case TaraRtConstants.MODULE:
					configuration.setModule(reader.readLine());
					break;
				case TaraRtConstants.SYSTEM:
					configuration.setSystem(Boolean.valueOf(reader.readLine()));
					break;
				case TaraRtConstants.MODELS_PATH:
					configuration.setModelsDirectory(reader.readLine());
					break;
				case TaraRtConstants.ICONS_PATH:
					configuration.addIconPath(reader.readLine());
					break;
				case TaraRtConstants.IT_RULES:
					configuration.setRulesDirectory(new File(reader.readLine()));
					break;
				case TaraRtConstants.PROJECT_ICON:
					configuration.setProjectIcon(reader.readLine());
					break;
			}
			aLine = reader.readLine();
		}
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
