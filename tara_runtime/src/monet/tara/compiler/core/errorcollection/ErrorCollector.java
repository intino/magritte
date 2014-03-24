package monet.tara.compiler.core.errorcollection;

import monet.tara.compiler.core.CompilerConfiguration;
import monet.tara.compiler.core.SourceUnit;
import monet.tara.compiler.core.errorcollection.message.ExceptionMessage;
import monet.tara.compiler.core.errorcollection.message.Message;
import monet.tara.compiler.core.errorcollection.message.SyntaxErrorMessage;
import monet.tara.compiler.core.errorcollection.message.WarningMessage;

import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

public class ErrorCollector {
	protected LinkedList warnings;
	protected LinkedList errors;
	protected CompilerConfiguration configuration;

	public ErrorCollector(CompilerConfiguration configuration) {
		this.warnings = null;
		this.errors = null;
		this.configuration = configuration;
	}

	public void addCollectorContents(ErrorCollector er) {
		if (er.errors != null)
			if (this.errors == null) this.errors = er.errors;
			else this.errors.addAll(er.errors);
		if (er.warnings != null)
			if (this.warnings == null) this.warnings = er.warnings;
			else this.warnings.addAll(er.warnings);
	}

	public void addErrorAndContinue(Message message) {
		if (this.errors == null) this.errors = new LinkedList();
		this.errors.add(message);
	}

	public void addError(Message message) throws CompilationFailedException {
		addErrorAndContinue(message);
	}

	public void addError(Message message, boolean fatal) throws CompilationFailedException {
		if (fatal) addFatalError(message);
		else addError(message);
	}

	public void addError(SyntaxException error, SourceUnit source) throws CompilationFailedException {
		addError(Message.create(error, source), error.isFatal());
	}

	public void addFatalError(Message message) throws CompilationFailedException {
		addError(message);
		failIfErrors();
	}

	public void addException(Exception cause, SourceUnit source) throws CompilationFailedException {
		addError(new ExceptionMessage(cause, this.configuration.getDebug(), source));
		failIfErrors();
	}

	public boolean hasErrors() {
		return this.errors != null;
	}

	public CompilerConfiguration getConfiguration() {
		return this.configuration;
	}

	public boolean hasWarnings() {
		return this.warnings != null;
	}

	public List getWarnings() {
		return this.warnings;
	}

	public List getErrors() {
		return this.errors;
	}

	public int getWarningCount() {
		return this.warnings == null ? 0 : this.warnings.size();
	}

	public int getErrorCount() {
		return this.errors == null ? 0 : this.errors.size();
	}

	public WarningMessage getWarning(int index) {
		if (index < getWarningCount())
			return (WarningMessage) this.warnings.get(index);
		return null;
	}

	public Message getError(int index) {
		if (index < getErrorCount())
			return (Message) this.errors.get(index);
		return null;
	}

	public Message getLastError() {
		return (Message) this.errors.getLast();
	}

	public SyntaxException getSyntaxError(int index) {
		SyntaxException exception = null;
		Message message = getError(index);
		if ((message != null) && ((message instanceof SyntaxErrorMessage)))
			exception = ((SyntaxErrorMessage) message).getCause();
		return exception;
	}

	public Exception getException(int index) {
		Exception exception = null;
		Message message = getError(index);
		if (message != null)
			if ((message instanceof ExceptionMessage))
				exception = ((ExceptionMessage) message).getCause();
			else if ((message instanceof SyntaxErrorMessage))
				exception = ((SyntaxErrorMessage) message).getCause();
		return exception;
	}

	public void addWarning(WarningMessage message) {
		if (message.isRelevant(this.configuration.getWarningLevel())) {
			if (this.warnings == null)
				this.warnings = new LinkedList();
			this.warnings.add(message);
		}
	}

	public void addWarning(int importance, String text, SourceUnit source) {
		if (WarningMessage.isRelevant(importance, this.configuration.getWarningLevel()))
			addWarning(new WarningMessage(importance, text, source));
	}

	public void addWarning(int importance, String text, Object data, SourceUnit source) {
		if (WarningMessage.isRelevant(importance, this.configuration.getWarningLevel()))
			addWarning(new WarningMessage(importance, text, data, source));
	}

	public void failIfErrors() throws CompilationFailedException {
		if (hasErrors()) throw new MultipleCompilationErrorsException(this);
	}

	private void write(PrintWriter writer, List messages, String txt) {
		if ((messages == null) || (messages.size() == 0)) return;
		for (Object message1 : messages) {
			Message message = (Message) message1;
			message.write(writer);
			if ((this.configuration.getDebug()) && ((message instanceof SyntaxErrorMessage))) {
				SyntaxErrorMessage sem = (SyntaxErrorMessage) message;
				sem.getCause().printStackTrace(writer);
			}
			writer.println();
		}
		writer.print(messages.size());
		writer.print(" " + txt);
		if (messages.size() > 1) writer.print("s");
		writer.println();
	}

	public void write(PrintWriter writer) {
		write(writer, this.warnings, "warning");
		write(writer, this.errors, "error");
	}
}