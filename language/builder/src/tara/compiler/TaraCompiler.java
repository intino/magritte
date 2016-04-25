package tara.compiler;

import tara.compiler.constants.TaraCompilerMessageCategories;
import tara.compiler.core.CompilationUnit;
import tara.compiler.core.CompilerMessage;
import tara.compiler.core.SourceUnit;
import tara.compiler.core.errorcollection.*;
import tara.compiler.core.errorcollection.message.*;
import tara.lang.model.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class TaraCompiler {
	private static final String LINE_AT = " @ line ";
	private static final Logger LOG = Logger.getLogger(TaraCompiler.class.getName());
	private final List<CompilerMessage> collector;

	public TaraCompiler(List<CompilerMessage> collector) {
		this.collector = collector;
	}

	public List<OutputItem> compile(CompilationUnit unit) {
		List<OutputItem> compiledFiles = new ArrayList<>();
		try {
			unit.compile();
			addCompiledFiles(unit, compiledFiles);
		} catch (CompilationFailedException e) {
			processCompilationException(e);
		} catch (IOException | TaraRuntimeException e) {
			processException(e);
		} finally {
			addWarnings(unit.getErrorCollector(), collector);
		}
		return compiledFiles;
	}

	private void addCompiledFiles(CompilationUnit compilationUnit, final List<OutputItem> compiledFiles) throws IOException {
		for (Map.Entry<String, List<String>> entry : compilationUnit.getOutputItems().entrySet())
			compiledFiles.addAll(entry.getValue().stream().map(outFile -> new OutputItem(entry.getKey(), outFile)).collect(Collectors.toList()));
	}

	private void addWarnings(ErrorCollector errorCollector, List collector) {
		for (int i = 0; i < errorCollector.getWarningCount(); i++) {
			WarningMessage warning = errorCollector.getWarning(i);
			collector.add(new CompilerMessage(CompilerMessage.WARNING, warning.getMessage(), ((SourceUnit) warning.getOwner()).getName(), warning.line(), warning.column()));
		}
	}

	private void processCompilationException(Exception exception) {
		if (exception instanceof MultipleCompilationErrorsException) {
			MultipleCompilationErrorsException errorsException = (MultipleCompilationErrorsException) exception;
			ErrorCollector errorCollector = errorsException.getErrorCollector();
			for (int i = 0; i < errorCollector.getErrorCount(); i++)
				processException(errorCollector.getError(i));
		} else processException(exception);
	}

	private void processException(Message message) {
		if (message instanceof SyntaxErrorMessage)
			addErrorMessage(((SyntaxErrorMessage) message).getCause());
		else if (message instanceof SemanticErrorMessage)
			addErrorMessage(((SemanticErrorMessage) message).getCause());
		else if (message instanceof DependencyErrorMessage)
			addErrorMessage(((DependencyErrorMessage) message).getCause());
		else if (message instanceof ExceptionMessage)
			processException(((ExceptionMessage) message).getCause());
		else if (message instanceof SimpleMessage)
			addErrorMessage((SimpleMessage) message, collector);
		else
			addMessageWithoutLocation(collector, "An unknown error occurred: " + message, true);
	}

	private void processException(Throwable e) {
		if (e instanceof TaraRuntimeException) {
			addErrorMessage((TaraRuntimeException) e);
			return;
		}
		LOG.severe(e.getMessage());
		addMessageWithoutLocation(collector, e.getMessage(), true);

	}

	private void addMessageWithoutLocation(List collector, String message, boolean error) {
		collector.add(new CompilerMessage(error ? CompilerMessage.ERROR : CompilerMessage.WARNING, message, null, -1, -1));
	}

	private void addErrorMessage(SyntaxException exception) {
		String message = exception.getMessage();
		String justMessage = message.substring(0, message.lastIndexOf(LINE_AT));
		collector.add(new CompilerMessage(TaraCompilerMessageCategories.ERROR, justMessage, exception.getSourceLocator(),
			exception.getLine(), exception.getStartColumn()));
	}

	private void addErrorMessage(SemanticException error) {
		String message;
		if (error.getErrors()[0].origin() != null) {
			message = (error.getMessage().contains(LINE_AT)) ?
				error.getMessage().substring(0, error.getMessage().lastIndexOf(LINE_AT)) : error.getMessage();
			Element element = error.getErrors()[0].origin();
			collector.add(new CompilerMessage(TaraCompilerMessageCategories.ERROR, message, element.file(), element.line(), 1));
		} else {
			message = error.getMessage();
			collector.add(new CompilerMessage(TaraCompilerMessageCategories.ERROR, message, null, -1, -1));
		}
	}

	private void addErrorMessage(DependencyException exception) {
		String message = exception.getMessage();
		String justMessage = message.substring(0, message.lastIndexOf(LINE_AT));
		collector.add(new CompilerMessage(TaraCompilerMessageCategories.ERROR, justMessage, exception.getElement().file(),
			exception.getLine(), 1));
	}

	private void addErrorMessage(TaraRuntimeException exception) {
		Element element = exception.getElement();
		collector.add(element != null ?
			new CompilerMessage(CompilerMessage.ERROR, exception.getMessageWithoutLocationText(), element.file(), element.line(), -1) :
			new CompilerMessage(CompilerMessage.ERROR, exception.getMessageWithoutLocationText(), "null", -1, -1));
	}

	private void addErrorMessage(SimpleMessage message, List collector) {
		addMessageWithoutLocation(collector, message.getMessage(), true);
	}

	public static class OutputItem {
		private final String myOutputPath;
		private final String mySourceFileName;

		public OutputItem(String sourceFileName, String outputFilePath) {
			myOutputPath = outputFilePath;
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
