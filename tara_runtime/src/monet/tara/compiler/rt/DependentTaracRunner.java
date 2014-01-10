package monet.tara.compiler.rt;

import java.io.File;

/**
 * Created by oroncal on 27/12/13.
 */
public class DependentTaracRunner {
	static boolean runTarac(boolean forStubs, File argsFile) {
//		try {
//			TaraGrammar parser = grammarInitialize("IsDouble");
//			grammarTest(new String(Files.readAllBytes(Paths.get(argsFile.getAbsolutePath())), StandardCharsets.UTF_8));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

//		final List<File> srcFiles = new ArrayList<>();
//		final Map<String, File> class2File = new HashMap<>();
//
//		final String[] finalOutput = new String[1];
//		fillFromArgsFile(argsFile, srcFiles, class2File, finalOutput);
//		if (srcFiles.isEmpty()) return true;
//		if (forStubs) {
//			Map<String, Object> options = new HashMap<>();
//			options.put("keepStubs", Boolean.TRUE);
//		}
//
//		final AstAwareResourceLoader resourceLoader = new AstAwareResourceLoader(class2File);
//		final CompilationUnit unit = createCompilationUnit(forStubs, config, finalOutput[0], buildClassLoaderFor(config, resourceLoader));
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
//
//		System.out.println();
//		reportCompiledItems(compiledFiles);
//
//		System.out.println();
//		if (compiledFiles.isEmpty()) {
//			reportNotCompiledItems(srcFiles);
//		}
		return false;
	}

//	private static TaraGrammar grammarInitialize(String query) {
//
//		CharStream stream = new ANTLRInputStream(query);
//		TaraLexer lexer = new TaraLexer(stream);
//		lexer.reset();
//
//		CommonTokenStream tokens = new CommonTokenStream(lexer);
//
//		TaraGrammar parser = new TaraGrammar(tokens);
//		parser.setErrorHandler(new BailErrorStrategy());
//
//		return parser;
//	}
//
//	private static boolean grammarTest(String query) {
//		CharStream stream = new ANTLRInputStream(query);
//		TaraLexer lexer = new TaraLexer(stream);
//		lexer.reset();
//
//		CommonTokenStream tokens = new CommonTokenStream(lexer);
//
//		TaraGrammar parser = new TaraGrammar(tokens);
//		parser.setErrorHandler(new BailErrorStrategy());
//
//		try {
//			ParseTree tree = parser.root();
//			TestRig.main(new String[]{"TaraParser", "r", "-tree"});
///*            System.out.println(tree.toStringTree(parser));
//            System.out.println("-----------------");*/
//		} catch (Exception error) {
//			return false;
//		}
//
//		return true;
//	}
//
//	private static String fillFromArgsFile(File argsFile, List<File> srcFiles, Map<String, File> class2File, String[] finalOutput) {
//		String moduleClasspath = null;
//
//		BufferedReader reader = null;
//		FileInputStream stream;
//
//		try {
//			stream = new FileInputStream(argsFile);
//			reader = new BufferedReader(new InputStreamReader(stream));
//
//			String line;
//
//			while ((line = reader.readLine()) != null) {
//				if (!GroovyRtConstants.SRC_FILE.equals(line)) {
//					break;
//				}
//
//				final File file = new File(reader.readLine());
//				srcFiles.add(file);
//			}
//
//			while (line != null) {
//				if (line.equals("class2src")) {
//					while (!GroovyRtConstants.END.equals(line = reader.readLine())) {
//						class2File.put(line, new File(reader.readLine()));
//					}
//				} else if (line.startsWith(GroovyRtConstants.PATCHERS)) {
//					String s;
//					while (!GroovyRtConstants.END.equals(s = reader.readLine())) {
//						try {
//							final CompilationUnitPatcher patcher = (CompilationUnitPatcher) Class.forName(s).newInstance();
//							patchers.add(patcher);
//						} catch (InstantiationException e) {
//							addExceptionInfo(compilerMessages, e, "Couldn't instantiate " + s);
//						} catch (IllegalAccessException e) {
//							addExceptionInfo(compilerMessages, e, "Couldn't instantiate " + s);
//						} catch (ClassNotFoundException e) {
//							addExceptionInfo(compilerMessages, e, "Couldn't instantiate " + s);
//						}
//					}
//				} else if (line.startsWith(GroovyRtConstants.ENCODING)) {
//					compilerConfiguration.setSourceEncoding(reader.readLine());
//				} else if (line.startsWith(GroovyRtConstants.OUTPUTPATH)) {
//					compilerConfiguration.setTargetDirectory(reader.readLine());
//				} else if (line.startsWith(GroovyRtConstants.FINAL_OUTPUTPATH)) {
//					finalOutput[0] = reader.readLine();
//				}
//
//				line = reader.readLine();
//			}
//
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				reader.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			} finally {
//				argsFile.delete();
//			}
//		}
//		return moduleClasspath;
//	}
//
//	private static void reportCompiledItems(List<TaraCompilerWrapper.OutputItem> compiledFiles) {
//		for (TaraCompilerWrapper.OutputItem compiledFile : compiledFiles) {
//	  /*
//      * output path
//      * source file
//      * output root directory
//      */
//			System.out.print(GroovyRtConstants.COMPILED_START);
//			System.out.print(compiledFile.getOutputPath());
//			System.out.print(GroovyRtConstants.SEPARATOR);
//			System.out.print(compiledFile.getSourceFile());
//			System.out.print(GroovyRtConstants.COMPILED_END);
//			System.out.println();
//		}
//	}
//
//	private static void reportNotCompiledItems(Collection<File> toRecompile) {
//		for (File file : toRecompile) {
//			System.out.print(GroovyRtConstants.TO_RECOMPILE_START);
//			System.out.print(file.getAbsolutePath());
//			System.out.print(GroovyRtConstants.TO_RECOMPILE_END);
//			System.out.println();
//		}
//	}
}
