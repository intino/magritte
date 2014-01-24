package monet.tara;

import monet.tara.compiler.rt.TaraRtConstants;
import monet.tara.transpiler.TranspilationUnit;
import monet.tara.transpiler.TranspilerConfiguration;
import monet.tara.transpiler.TranspilerMessage;
import org.codehaus.groovy.control.SourceUnit;

import java.io.*;
import java.util.*;

/**
 * Created by oroncal on 27/12/13.
 */
public class TaraTranspilerRunner {
	static boolean runTaraTranspile(File argsFile, String outputPath) {
		final TranspilerConfiguration config = new TranspilerConfiguration();
		try {

		} catch (IOException e) {
			e.printStackTrace();
		}

		final List<File> srcFiles = new ArrayList<>();
		final Map<String, File> class2File = new HashMap<>();

		final String[] finalOutput = new String[1];
		fillFromArgsFile(argsFile, srcFiles, class2File, finalOutput);
		if (srcFiles.isEmpty()) return true;
			Map<String, Object> options = new HashMap<>();
			options.put("keepStubs", Boolean.TRUE);

		System.out.println(TaraRtConstants.PRESENTABLE_MESSAGE + "Tarat: loading sources...");
		final AstAwareResourceLoader resourceLoader = new AstAwareResourceLoader(class2File);
		final TranspilationUnit unit = createTranspilationUnit(config, finalOutput[0], buildClassLoaderFor(config, resourceLoader));
//		unit.addPhaseOperation(new CompilationUnit.SourceUnitOperation() {
//			public void call(SourceUnit source) throws CompilationFailedException {
//				File file = new File(source.getName());
//				for (ClassNode aClass : source.getAST().getClasses())
//					resourceLoader.myClass2File.put(aClass.getName(), file);
//			}
//		}, Phases.CONVERSION);
//
//		addSources(forStubs, srcFiles, unit);
//		runPatchers(patchers, compilerMessages, unit, resourceLoader, srcFiles);
//
//		System.out.println("Tarac: compiling...");
//		final List<GroovyCompilerWrapper.OutputItem> compiledFiles = new GroovyCompilerWrapper(compilerMessages, forStubs).compile(unit);

		System.out.println();
		reportCompiledItems(compiledFiles);

		System.out.println();
		if (compiledFiles.isEmpty()) {
			reportNotCompiledItems(srcFiles);
		}
		return false;
	}

	private static String fillFromArgsFile(File argsFile, List<File> srcFiles, Map<String, File> class2File, String[] finalOutput) {
		String moduleClasspath = null;

		BufferedReader reader = null;
		FileInputStream stream;

		try {
			stream = new FileInputStream(argsFile);
			reader = new BufferedReader(new InputStreamReader(stream));

			String line;

			while ((line = reader.readLine()) != null) {
				if (!TaraRtConstants.SRC_FILE.equals(line)) {
					break;
				}

				final File file = new File(reader.readLine());
				srcFiles.add(file);
			}

			while (line != null) {
				if (line.equals("class2src")) {
					while (!TaraRtConstants.END.equals(line = reader.readLine())) {
						class2File.put(line, new File(reader.readLine()));
					}
				} else if (line.startsWith(TaraRtConstants.PATCHERS)) {
					String s;
					while (!TaraRtConstants.END.equals(s = reader.readLine())) {
						try {
							final CompilationUnitPatcher patcher = (CompilationUnitPatcher) Class.forName(s).newInstance();
							patchers.add(patcher);
						} catch (InstantiationException e) {
							addExceptionInfo(compilerMessages, e, "Couldn't instantiate " + s);
						} catch (IllegalAccessException e) {
							addExceptionInfo(compilerMessages, e, "Couldn't instantiate " + s);
						} catch (ClassNotFoundException e) {
							addExceptionInfo(compilerMessages, e, "Couldn't instantiate " + s);
						}
					}
				} else if (line.startsWith(TaraRtConstants.ENCODING)) {
					compilerConfiguration.setSourceEncoding(reader.readLine());
				} else if (line.startsWith(TaraRtConstants.OUTPUTPATH)) {
					compilerConfiguration.setTargetDirectory(reader.readLine());
				} else if (line.startsWith(TaraRtConstants.FINAL_OUTPUTPATH)) {
					finalOutput[0] = reader.readLine();
				}

				line = reader.readLine();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				argsFile.delete();
			}
		}
		return moduleClasspath;
	}


	private static void addSources(List<File> srcFiles, final TranspilationUnit unit) {
		for (final File file : srcFiles) {
			if (!file.getName().endsWith(".m2"))
				continue;

			unit.addSource(new SourceUnit(file, unit.getConfiguration(), unit.getClassLoader(), unit.getErrorCollector()));
		}
	}

	private static void runPatchers(List<CompilationUnitPatcher> patchers, List<CompilerMessage> compilerMessages, CompilationUnit unit, final AstAwareResourceLoader loader, List<File> srcFiles) {
		if (!patchers.isEmpty()) {
			for (CompilationUnitPatcher patcher : patchers) {
				try {
					patcher.patchCompilationUnit(unit, loader, srcFiles.toArray(new File[srcFiles.size()]));
				} catch (LinkageError e) {
					addExceptionInfo(compilerMessages, e, "Couldn't run " + patcher.getClass().getName());
				}
			}
		}
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

	private static void reportCompiledItems(List<TaraCompilerWrapper.OutputItem> compiledFiles) {
		for (TaraCompilerWrapper.OutputItem compiledFile : compiledFiles) {
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
}
