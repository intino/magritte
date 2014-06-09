package siani.tara.compiler.parser.antlr;

public class BlockManager {

	private int level;
	private int tabSize;
	private int brackets;
	private Token[] tokens;

	public BlockManager() {
		this.tokens = new Token[]{};
		this.level = 0;
		this.brackets = 0;
		this.tabSize = 4;
	}

	public BlockManager(int tabulationSize) {
		this.tokens = new Token[]{};
		this.level = 0;
		this.brackets = 0;
		this.tabSize = (tabulationSize < 0) ? 1 : tabulationSize;
	}

	public void reset() {
		this.tokens = new Token[]{};
		this.level = 0;
		this.brackets = 0;
	}

	public void newlineAndSpaces(String text) {
		if (!bracketsMode()) {
			int newLevel = (spacesLength(text) / this.tabSize);
			this.tokens = indentationTokens(newLevel - level, true);
			this.level = newLevel;
		} else
			this.tokens = create(Token.ERROR);
	}

	private int spacesLength(String text) {
		int value = 0;
		for (int i = 0; i < text.length(); i++)
			value += (text.charAt(i) == ('\t')) ? this.tabSize : 1;
		return value;
	}

	private Token[] indentationTokens(int size, boolean addLastNewline) {
		if (size > 0)
			return create(Token.NEWLINE_INDENT);
		else {
			int length = (!addLastNewline) ? Math.abs(size * 2) : Math.abs(size * 2) + 1;
			return createDedents(length);
		}
	}

	private Token[] createDedents(int size) {
		Token[] actions = new Token[size];
		for (int i = 0; i < actions.length; i++)
			actions[i] = (i % 2 == 0) ? Token.NEWLINE : Token.DEDENT;
		return actions;
	}

	private Token[] create(Token token) {
		return new Token[]{token};
	}

	public boolean bracketsMode() {
		return (brackets > 0);
	}

	public int getLevel() {
		return level;
	}

	public Token[] actions() {
		return tokens;
	}

	public void openBracket(int size) {
		this.tokens = indentationTokens(size, false);
		this.level += size;
		this.brackets += size;
	}

	public void semicolon(int size) {
		if (bracketsMode() && size == 1)
			this.tokens = create(Token.NEWLINE);
		else
			this.tokens = create(Token.ERROR);
	}

	public void closeBracket(int size) {
		if (bracketsMode() && (brackets - size >= 0)) {
			this.tokens = indentationTokens(-size, false);
			this.level -= size;
			this.brackets -= size;
		} else
			this.tokens = create(Token.ERROR);
	}

	public void eof() {
		if (!bracketsMode()) {
			this.tokens = indentationTokens(-level, false);
			this.level -= level;
		} else
			this.tokens = create(Token.ERROR);
	}

	public enum Token {
		NEWLINE_INDENT, DEDENT, NEWLINE, ERROR
	}
}
