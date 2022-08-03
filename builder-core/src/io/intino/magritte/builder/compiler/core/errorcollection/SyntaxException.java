package io.intino.magritte.builder.compiler.core.errorcollection;

public class SyntaxException extends TaraException {

	private final int startLine;
	private final int endLine;
	private final int startColumn;
	private final int endColumn;
	private final String expectedTokens;
	private String sourceLocator;

	public SyntaxException(String message, int startLine, int startColumn, String expectedTokens) {
		this(message, startLine, startColumn, startLine, startColumn + 1, expectedTokens);
	}

	public SyntaxException(String message, int startLine, int startColumn, int endLine, int endColumn, String expectedTokens) {
		super(message, false);
		this.startLine = startLine;
		this.startColumn = startColumn;
		this.endLine = endLine;
		this.endColumn = endColumn;
		this.expectedTokens = expectedTokens;
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
		this.expectedTokens = "";
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

	public String getExpectedTokens() {
		return expectedTokens;
	}

	public String getOriginalMessage() {
		return super.getMessage();
	}

	@Override
	public String getMessage() {
		return super.getMessage() + (!expectedTokens.isEmpty() ? ". Expected " + this.expectedTokens : "") + " @ line " + this.startLine + ", column " + this.startColumn + ".";
	}
}
