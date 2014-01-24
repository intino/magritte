package monet.tara.transpiler.core;

public class TranspilerMessage {
	private final String category;
	private final String message;
	private final String url;
	private final int lineNum;
	private final int columnNum;

	public TranspilerMessage(String category, String message, String url, int lineNum, int columnNum) {
		this.category = category;
		this.message = message;
		this.url = url;
		this.lineNum = lineNum;
		this.columnNum = columnNum;
	}

	public String getCategory() {
		return category;
	}

	public String getMessage() {
		return message;
	}

	public String getUrl() {
		return url;
	}

	public int getLineNum() {
		return lineNum;
	}

	public int getColumnNum() {
		return columnNum;
	}
}
