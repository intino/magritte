package monet.tara.compiler;

import monet.tara.compiler.core.CompilationUnit;
import monet.tara.compiler.core.CompilerMessage;
import monet.tara.compiler.core.SourceUnit;
import monet.tara.compiler.core.ast.ASTNode;
import monet.tara.compiler.core.errorcollection.*;
import monet.tara.compiler.core.errorcollection.message.*;
import monet.tara.compiler.core.errorcollection.semantic.SemanticError;
import monet.tara.compiler.rt.TaraCompilerMessageCategories;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

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
		File targetDirectory = compilationUnit.getConfiguration().getTargetDirectory();
		final String outputPath = targetDirectory.getCanonicalPath().replace(File.separatorChar, File.separator.charAt(0));
		for (String fileName : compilationUnit.getSources())
			compiledFiles.add(new OutputItem(outputPath, fileName));
		if (compilationUnit.isPluginGeneration())
			compiledFiles.add(new OutputItem(outputPath, compilationUnit.getConfiguration().getProject() + ".zip"));
	}

	private void addWarnings(ErrorCollector errorCollector, List collector) {
		for (int i = 0; i < errorCollector.getWarningCount(); i++) {
			WarningMessage warning = errorCollector.getWarning(i);
			collector.add(new CompilerMessage(CompilerMessage.WARNING, warning.getMessage(), ((SourceUnit) warning.getOwner()).getName(), -1, -1));
		}
	}

	private void processCompilationException(Exception exception) {
		if (exception instanceof MultipleCompilationErrorsException) {
			MultipleCompilationErrorsException multipleCompilationErrorsException = (MultipleCompilationErrorsException) exception;
			ErrorCollector errorCollector = multipleCompilationErrorsException.getErrorCollector();
			for (int i = 0; i < errorCollector.getErrorCount(); i++)
				processException(errorCollector.getError(i));
		} else processException(exception);
	}

	private void processException(Message message) {
		if (message instanceof SyntaxErrorMessage)
			addErrorMessage(((SyntaxErrorMessage) message).getCause());
		if (message instanceof SemanticErrorMessage)
			addErrorMessage(((SemanticErrorMessage) message).getCause());
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

	private void addErrorMessage(SemanticError exception) {
		String message;
		if (exception.getNode() != null) {
			message = exception.getMessage().substring(0, exception.getMessage().lastIndexOf(LINE_AT));
			collector.add(new CompilerMessage(TaraCompilerMessageCategories.ERROR, message, exception.getNode().getFile(),
				exception.getLine(), 1));
		} else {
			message = exception.getMessage();
			collector.add(new CompilerMessage(TaraCompilerMessageCategories.ERROR, message, null, -1, -1));
		}
	}

	private void addErrorMessage(TaraRuntimeException exception) {
		ASTNode astNode = exception.getNode();
		if (astNode != null)
			collector.add(new CompilerMessage(CompilerMessage.ERROR, exception.getMessageWithoutLocationText(),
				astNode.getFile(), astNode.getLine(), -1));
		else
			collector.add(new CompilerMessage(CompilerMessage.ERROR, exception.getMessageWithoutLocationText(), "null", -1, -1));
	}

	private void addErrorMessage(SimpleMessage message, List collector) {
		addMessageWithoutLocation(collector, message.getMessage(), true);
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
