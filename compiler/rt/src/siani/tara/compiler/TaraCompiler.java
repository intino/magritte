package siani.tara.compiler;

import siani.tara.compiler.core.CompilationUnit;
import siani.tara.compiler.core.CompilerMessage;
import siani.tara.compiler.core.SourceUnit;
import siani.tara.compiler.core.errorcollection.*;
import siani.tara.compiler.core.errorcollection.message.*;
import siani.tara.compiler.model.Element;
import siani.tara.compiler.rt.TaraCompilerMessageCategories;
import siani.tara.compiler.semantic.wrappers.LanguageElement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static siani.tara.compiler.core.CompilerMessage.ERROR;
import static siani.tara.compiler.core.CompilerMessage.WARNING;

public class TaraCompiler {
	public static final String LINE_AT = " @ line ";
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
			collector.add(new CompilerMessage(WARNING, warning.getMessage(), ((SourceUnit) warning.getOwner()).getName(), -1, -1));
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
		collector.add(new CompilerMessage(error ? ERROR : WARNING, message, null, -1, -1));
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
			Element element = ((LanguageElement) error.getErrors()[0].origin()).element();
			collector.add(new CompilerMessage(TaraCompilerMessageCategories.ERROR, message, element.getFile(), element.getLine(), 1));
		} else {
			message = error.getMessage();
			collector.add(new CompilerMessage(TaraCompilerMessageCategories.ERROR, message, null, -1, -1));
		}
	}

	private void addErrorMessage(DependencyException exception) {
		String message = exception.getMessage();
		String justMessage = message.substring(0, message.lastIndexOf(LINE_AT));
		collector.add(new CompilerMessage(TaraCompilerMessageCategories.ERROR, justMessage, exception.getElement().getFile(),
			exception.getLine(), 1));
	}

	private void addErrorMessage(TaraRuntimeException exception) {
		Element element = exception.getElement();
		collector.add(element != null ?
			new CompilerMessage(ERROR, exception.getMessageWithoutLocationText(), element.getFile(), element.getLine(), -1) :
			new CompilerMessage(ERROR, exception.getMessageWithoutLocationText(), "null", -1, -1));
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
