package siani.tara.compiler.core.errorcollection;

public class SyntaxException extends TaraException {

	private final int startLine;
	private final int endLine;
	private final int startColumn;
	private final int endColumn;
	private String sourceLocator;

	public SyntaxException(String message, int startLine, int startColumn) {
		this(message, startLine, startColumn, startLine, startColumn + 1);
	}

	public SyntaxException(String message, int startLine, int startColumn, int endLine, int endColumn) {
		super(message, false);
		this.startLine = startLine;
		this.startColumn = startColumn;
		this.endLine = endLine;
		this.endColumn = endColumn;
	}

	public SyntaxException(String message, Throwable cause, int startLine, int startColumn) {
		this(message, cause, startLine, startColumn, startLine, startColumn + 1);
	}

	public SyntaxException(String message, Throwable cause, int startLine, int startColumn, int endLine, int endColumn) {
		super(message, cause);
		this.startLine = startLine;
		this.startColumn = startColumn;
		this.endLine = endLine;
		this.endColumn = endColumn;
	}

	public String getSourceLocator() {
		return this.sourceLocator;
	}

	public void setSourceLocator(String sourceLocator) {
		this.sourceLocator = sourceLocator;
	}

	public int getLine() {
		return getStartLine();
	}

	public int getStartLine() {
		return this.startLine;
	}

	public int getStartColumn() {
		return this.startColumn;
	}

	public int getEndLine() {
		return this.endLine;
	}

	public int getEndColumn() {
		return this.endColumn;
	}

	public String getOriginalMessage() {
		return super.getMessage();
	}

	public String getMessage() {
		return super.getMessage() + " @ line " + this.startLine + ", column " + this.startColumn + ".";
	}
}
