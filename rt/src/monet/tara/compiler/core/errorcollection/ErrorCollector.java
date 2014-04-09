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

	public void addSyntaxError(SyntaxException error, SourceUnit source) throws CompilationFailedException {
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

	public void failIfErrors() throws CompilationFailedException {
		if (hasErrors()) throw new MultipleCompilationErrorsException(this);
	}

	private void write(PrintWriter writer, List messages, String txt) {
		if (messages == null || messages.isEmpty()) return;
		for (Object message1 : messages) {
			Message message = (Message) message1;
			message.write(writer);
			if (this.configuration.getDebug() && message instanceof SyntaxErrorMessage) {
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