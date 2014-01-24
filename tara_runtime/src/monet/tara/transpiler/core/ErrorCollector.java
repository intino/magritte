package monet.tara.transpiler.core;

import org.codehaus.groovy.control.CompilationFailedException;
import org.codehaus.groovy.control.Janitor;
import org.codehaus.groovy.control.messages.*;
import org.codehaus.groovy.syntax.CSTNode;
import org.codehaus.groovy.syntax.SyntaxException;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ErrorCollector {
	protected LinkedList warnings;
	protected LinkedList errors;
	protected TranspilerConfiguration configuration;

	public ErrorCollector(TranspilerConfiguration configuration) {
		this.warnings = null;
		this.errors = null;
		this.configuration = configuration;
	}

	public void addCollectorContents(ErrorCollector er) {
		if (er.errors != null) {
			if (this.errors == null)
				this.errors = er.errors;
			else {
				this.errors.addAll(er.errors);
			}
		}
		if (er.warnings != null)
			if (this.warnings == null)
				this.warnings = er.warnings;
			else
				this.warnings.addAll(er.warnings);
	}

	public void addErrorAndContinue(Message message) {
		if (this.errors == null) {
			this.errors = new LinkedList();
		}

		this.errors.add(message);
	}

	public void addError(Message message)
		throws CompilationFailedException {
		addErrorAndContinue(message);

		if ((this.errors != null) && (this.errors.size() >= this.configuration.getTolerance()))
			failIfErrors();
	}

	public void addError(Message message, boolean fatal)
		throws CompilationFailedException {
		if (fatal) {
			addFatalError(message);
		} else
			addError(message);
	}

	public void addError(SyntaxException error, org.codehaus.groovy.control.SourceUnit source)
		throws CompilationFailedException {
		addError(Message.create(error, source), error.isFatal());
	}

	public void addError(String text, CSTNode context, org.codehaus.groovy.control.SourceUnit source)
		throws CompilationFailedException {
		addError(new LocatedMessage(text, context, source));
	}

	public void addFatalError(Message message)
		throws CompilationFailedException {
		addError(message);
		failIfErrors();
	}

	public void addException(Exception cause, org.codehaus.groovy.control.SourceUnit source) throws CompilationFailedException {
		addError(new ExceptionMessage(cause, this.configuration.getDebug(), source));
		failIfErrors();
	}

	public boolean hasErrors() {
		return this.errors != null;
	}

	public TranspilerConfiguration getConfiguration() {
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
		if (index < getWarningCount()) {
			return (WarningMessage) this.warnings.get(index);
		}
		return null;
	}

	public Message getError(int index) {
		if (index < getErrorCount()) {
			return (Message) this.errors.get(index);
		}
		return null;
	}

	public Message getLastError() {
		return (Message) this.errors.getLast();
	}

	public SyntaxException getSyntaxError(int index) {
		SyntaxException exception = null;

		Message message = getError(index);
		if ((message != null) && ((message instanceof SyntaxErrorMessage))) {
			exception = ((SyntaxErrorMessage) message).getCause();
		}
		return exception;
	}

	public Exception getException(int index) {
		Exception exception = null;

		Message message = getError(index);
		if (message != null) {
			if ((message instanceof ExceptionMessage)) {
				exception = ((ExceptionMessage) message).getCause();
			} else if ((message instanceof SyntaxErrorMessage)) {
				exception = ((SyntaxErrorMessage) message).getCause();
			}
		}
		return exception;
	}

	public void addWarning(WarningMessage message) {
		if (message.isRelevant(this.configuration.getWarningLevel())) {
			if (this.warnings == null) {
				this.warnings = new LinkedList();
			}

			this.warnings.add(message);
		}
	}

	public void addWarning(int importance, String text, CSTNode context, org.codehaus.groovy.control.SourceUnit source) {
		if (WarningMessage.isRelevant(importance, this.configuration.getWarningLevel()))
			addWarning(new WarningMessage(importance, text, context, source));
	}

	public void addWarning(int importance, String text, Object data, CSTNode context, org.codehaus.groovy.control.SourceUnit source) {
		if (WarningMessage.isRelevant(importance, this.configuration.getWarningLevel()))
			addWarning(new WarningMessage(importance, text, data, context, source));
	}

	protected void failIfErrors()
		throws CompilationFailedException {
		if (hasErrors())
			throw new MultipleTranspilationErrorsException(this);
	}

	private void write(PrintWriter writer, Janitor janitor, List messages, String txt) {
		if ((messages == null) || (messages.size() == 0)) return;
		Iterator iterator = messages.iterator();
		while (iterator.hasNext()) {
			Message message = (Message) iterator.next();
			message.write(writer, janitor);

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

	public void write(PrintWriter writer, Janitor janitor) {
		write(writer, janitor, this.warnings, "warning");
		write(writer, janitor, this.errors, "error");
	}
}