package monet.tara.compiler;

import monet.tara.compiler.core.CompilationUnit;
import monet.tara.compiler.core.CompilerMessage;
import monet.tara.compiler.core.Phases;
import monet.tara.compiler.core.error_collection.*;
import monet.tara.compiler.core.error_collection.message.*;
import monet.tara.compiler.rt.TaraCompilerMessageCategories;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaraCompiler {

	private final List<CompilerMessage> collector;
	private final boolean pluginGeneration;


	public TaraCompiler(boolean pluginGeneration, List<CompilerMessage> collector) {
		this.collector = collector;
		this.pluginGeneration = pluginGeneration;
	}

	private static void addCompiledFiles(CompilationUnit compilationUnit, final List<OutputItem> compiledFiles) throws IOException {
		File targetDirectory = compilationUnit.getConfiguration().getTempDirectory();
		final String outputPath = targetDirectory.getCanonicalPath().replace(File.separatorChar, '/');
		//TODO
	}

	public List<OutputItem> compile(CompilationUnit unit) {
		List<OutputItem> compiledFiles = new ArrayList<>();
		try {
			unit.compile(Phases.ALL);
			addCompiledFiles(unit, compiledFiles);
		} catch (CompilationFailedException e) {
			processCompilationException(e);
		} catch (Exception e) {
			processException(e, "");
		}
		return compiledFiles;
	}

	private void processCompilationException(Exception exception) {
		if (exception instanceof MultipleCompilationErrorsException) {
			MultipleCompilationErrorsException multipleCompilationErrorsException = (MultipleCompilationErrorsException) exception;
			ErrorCollector errorCollector = multipleCompilationErrorsException.getErrorCollector();
			for (int i = 0; i < errorCollector.getErrorCount(); i++) {
				processException(errorCollector.getError(i));
			}
		} else {
			processException(exception, "");
		}
	}

	private void processException(Message message) {
		if (message instanceof SyntaxErrorMessage) {
			SyntaxErrorMessage syntaxErrorMessage = (SyntaxErrorMessage) message;
			addErrorMessage(syntaxErrorMessage.getCause());
		} else if (message instanceof ExceptionMessage) {
			ExceptionMessage exceptionMessage = (ExceptionMessage) message;
			processException(exceptionMessage.getCause(), "");
		} else if (message instanceof SimpleMessage) {
			addErrorMessage((SimpleMessage) message);
		} else {
//			addMessageWithoutLocation("An unknown error occurred: " + message, true);
		}
	}

	private void processException(Throwable exception, String prefix) {

	}


	private void addErrorMessage(SyntaxException exception) {
		final String LINE_AT = " @ line ";
		String message = exception.getMessage();
		String justMessage = message.substring(0, message.lastIndexOf(LINE_AT));
		collector.add(new CompilerMessage(TaraCompilerMessageCategories.ERROR, justMessage, exception.getSourceLocator(),
			exception.getLine(), exception.getStartColumn()));
	}

	private void addErrorMessage(TaraRuntimeException exception) {
//		ASTNode astNode = exception.getNode();
//		ModuleNode module = exception.getModule();
//		if (module == null) {
//			module = findModule(astNode);
//		}
//		String moduleName = module == null ? "<no module>" : module.getDescription();
//
//		int lineNumber = astNode == null ? -1 : astNode.getLineNumber();
//		int columnNumber = astNode == null ? -1 : astNode.getColumnNumber();
//
//		String message = exception.getMessageWithoutLocationText();
//		if (message == null) {
//			StringWriter stringWriter = new StringWriter();
//			//noinspection IOResourceOpenedButNotSafelyClosed
//			PrintWriter writer = new PrintWriter(stringWriter);
//			exception.printStackTrace(writer);
//			message = stringWriter.getBuffer().toString();
//		}
//
//		collector.add(new CompilerMessage(TaraCompilerMessageCategories.ERROR, message, moduleName, lineNumber, columnNumber));
	}

	private void addErrorMessage(SimpleMessage message) {
//		ASTNode astNode = exception.getNode();
//		ModuleNode module = exception.getModule();
//		if (module == null) {
//			module = findModule(astNode);
//		}
//		String moduleName = module == null ? "<no module>" : module.getDescription();
//
//		int lineNumber = astNode == null ? -1 : astNode.getLineNumber();
//		int columnNumber = astNode == null ? -1 : astNode.getColumnNumber();
//
//		String message = exception.getMessageWithoutLocationText();
//		if (message == null) {
//			StringWriter stringWriter = new StringWriter();
//			//noinspection IOResourceOpenedButNotSafelyClosed
//			PrintWriter writer = new PrintWriter(stringWriter);
//			exception.printStackTrace(writer);
//			message = stringWriter.getBuffer().toString();
//		}
//
//		collector.add(new CompilerMessage(TaraCompilerMessageCategories.ERROR, message, moduleName, lineNumber, columnNumber));
	}

	private void addWarnings(ErrorCollector errorCollector) {
		for (int i = 0; i < errorCollector.getWarningCount(); i++) {
			WarningMessage warning = errorCollector.getWarning(i);
			collector.add(new CompilerMessage(TaraCompilerMessageCategories.WARNING, warning.getMessage(), null, -1, -1));
		}
	}

	public static class OutputItem {
		private final String myOutputPath;
		private final String mySourceFileName;

		public OutputItem(String outputPath, String sourceFileName) {
			myOutputPath = outputPath;
			mySourceFileName = sourceFileName;
		}

		public String getOutputPath() {
			return myOutputPath;
		}

		public String getSourceFile() {
			return mySourceFileName;
		}
	}
}
