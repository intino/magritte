package siani.tara.builder;

import org.jetbrains.annotations.NotNull;
import siani.tara.compiler.core.CompilationUnit;
import siani.tara.compiler.core.CompilerConfiguration;
import siani.tara.compiler.core.CompilerMessage;
import siani.tara.compiler.core.SourceUnit;
import siani.tara.compiler.core.errorcollection.*;
import siani.tara.compiler.core.errorcollection.message.*;
import siani.tara.compiler.model.Element;

import java.io.File;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class StashBuilder {
	private final CompilationUnit compilationUnit;
	private final List<CompilerMessage> compilationMessages;
	private static final String LINE_AT = " @ line ";

	public StashBuilder(File file, Charset charset) {
		this.compilationUnit = file.exists() ? buildCompilationUnit(file, charset) : null;
		this.compilationMessages = new ArrayList<>();
	}

	public void build() {
		compile(compilationUnit);
		processErrors(compilationMessages);
	}

	@NotNull
	private static CompilationUnit buildCompilationUnit(File file, Charset charset) {
		final CompilationUnit unit = new CompilationUnit(buildConfiguration(file, charset));
		unit.addSource(new SourceUnit(file, unit.getConfiguration(), unit.getErrorCollector()));
		return unit;
	}

	private static CompilerConfiguration buildConfiguration(File src, Charset charset) {
		final CompilerConfiguration configuration = new CompilerConfiguration();
		configuration.setOutput(new PrintWriter(System.err));
		configuration.setWarningLevel(WarningMessage.PARANOIA);
		configuration.setSourceEncoding(charset.name());
		configuration.setOutDirectory(src.getParent());
		return configuration;
	}

	private static void processErrors(List<CompilerMessage> compilerMessages) {
		int errorCount = 0;
		for (CompilerMessage message : compilerMessages) {
			if (message.getCategory().equals(TaraCompilerMessageCategories.ERROR)) {
				if (errorCount > 100) continue;
				errorCount++;
			}
			System.err.println(message);
		}
	}


	void compile(CompilationUnit unit) {
		try {
			if (unit == null) return;
			unit.compile();
		} catch (CompilationFailedException e) {
			processCompilationException(e);
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
		else if (message instanceof ExceptionMessage)
			processException(((ExceptionMessage) message).getCause());
		else if (message instanceof SimpleMessage)
			addErrorMessage((SimpleMessage) message, compilationMessages);
		else
			addMessageWithoutLocation(compilationMessages, "An unknown error occurred: " + message, true);
	}

	private void processException(Throwable e) {
		if (e instanceof TaraRuntimeException) {
			addErrorMessage((TaraRuntimeException) e);
			return;
		}
		addMessageWithoutLocation(compilationMessages, e.getMessage(), true);

	}

	private void addMessageWithoutLocation(List compilerMessages, String message, boolean error) {
		compilerMessages.add(new CompilerMessage(error ? CompilerMessage.ERROR : CompilerMessage.WARNING, message, null, -1, -1));
	}

	private void addErrorMessage(SyntaxException exception) {
		String message = exception.getMessage();
		String justMessage = message.substring(0, message.lastIndexOf(LINE_AT));
		compilationMessages.add(new CompilerMessage(TaraCompilerMessageCategories.ERROR, justMessage, exception.getSourceLocator(),
			exception.getLine(), exception.getStartColumn()));
	}

	private void addErrorMessage(TaraRuntimeException exception) {
		Element element = exception.getElement();
		compilationMessages.add(element != null ?
			new CompilerMessage(CompilerMessage.ERROR, exception.getMessageWithoutLocationText(), element.getFile(), element.getLine(), -1) :
			new CompilerMessage(CompilerMessage.ERROR, exception.getMessageWithoutLocationText(), "null", -1, -1));
	}

	private void addErrorMessage(SimpleMessage message, List compilerMessages) {
		addMessageWithoutLocation(compilerMessages, message.getMessage(), true);
	}
}
