package siani.tara.intellij.lang.lexer;

import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import siani.tara.intellij.lang.psi.TaraTypes;

public class BlockManager {

	private int level;
	private int tabSize;
	private int brackets;
	private IElementType[] tokens;

	public BlockManager() {
		this.tokens = new IElementType[]{};
		this.level = 0;
		this.brackets = 0;
		this.tabSize = 4;
	}

	public BlockManager(int tabulationSize) {
		this.tokens = new IElementType[]{};
		this.level = 0;
		this.brackets = 0;
		this.tabSize = (tabulationSize < 0) ? 1 : tabulationSize;
	}

	public void spaces(String text) {
		if (!bracketsMode()) {
			int newLevel = spacesLength(text) / this.tabSize;
			this.tokens = spacesIndentTokens(newLevel - level);
			this.level = newLevel;
		} else
			this.tokens = create(TokenType.BAD_CHARACTER);
	}

	private int spacesLength(String text) {
		int value = 0;
		for (int i = 0; i < text.length(); i++)
			value += (text.charAt(i) == ('\t')) ? this.tabSize : 1;
		return value;
	}

	private IElementType[] spacesIndentTokens(int size) {
		int length = (size > 0) ? size : Math.abs(size * 2) + 1;
		IElementType[] actions = new IElementType[length];
		if (size > 0) return new IElementType[]{TokenType.NEW_LINE_INDENT};
		else
			for (int i = 0; i < actions.length; i++)
				actions[i] = (i % 2 == 0) ? TaraTypes.NEWLINE : TaraTypes.DEDENT;
		return actions;
	}

	private IElementType[] indentTokens(int size) {
		IElementType[] actions = new IElementType[Math.abs(size * 2)];
		if (size > 0) return new IElementType[]{TokenType.NEW_LINE_INDENT};
		else
			for (int i = 0; i < actions.length; i++)
				actions[i] = (i % 2 == 0) ? TaraTypes.NEWLINE : TaraTypes.DEDENT;
		return actions;
	}

	private IElementType[] create(IElementType token) {
		return new IElementType[]{token};
	}

	public boolean bracketsMode() {
		return brackets > 0;
	}

	public IElementType[] actions() {
		return tokens;
	}

	public void openBracket(int size) {
		this.tokens = indentTokens(size);
		this.level += size;
		this.brackets += size;
	}

	public void semicolon(int size) {
		if (bracketsMode() && size == 1)
			this.tokens = create(TaraTypes.NEWLINE);
		else
			this.tokens = create(TokenType.BAD_CHARACTER);
	}

	public void closeBracket(int size) {
		if (bracketsMode() && (brackets - size >= 0)) {
			this.tokens = indentTokens(-size);
			this.level -= size;
			this.brackets -= size;
		} else
			this.tokens = create(TokenType.BAD_CHARACTER);
	}

	public void eof() {
		if (!bracketsMode()) {
			this.tokens = indentTokens(-level);
			this.level -= level;
		} else
			this.tokens = create(TokenType.BAD_CHARACTER);
	}
}
