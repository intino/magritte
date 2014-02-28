package monet.tara.compiler.parser.antlr;

import java.util.Arrays;

public class BlockManager {

    private int level;
	private int tabSize;
    private int brackets;
	private Token[] tokens;

	public BlockManager() {
		this.tokens = new Token[] {};
        this.level = 0;
        this.brackets = 0;
        this.tabSize = 4;
	}
    public BlockManager(int tabulationSize) {
        this.tokens = new Token[] {};
        this.level = 0;
        this.brackets = 0;
        this.tabSize = (tabulationSize < 0) ? 1 : tabulationSize;
	}

    public void reset(){
        this.tokens = new Token[] {};
        this.level = 0;
        this.brackets = 0;
    }

	public void spaces(String text) {
        if (!bracketsMode()) {
            int newLevel = (spacesLength(text) / this.tabSize);
            this.tokens = spacesIndentTokens(newLevel - level);
            this.level = newLevel;
        }
        else
            this.tokens = create(Token.ERROR);
    }

    private int spacesLength(String text){
        int value = 0;
        for(int i = 0; i < text.length(); i++)
            value += (text.charAt(i) == ('\t')) ? this.tabSize : 1;
        return value;
    }

    private Token[] spacesIndentTokens(int size) {
        int length = (size > 0) ? size*2 : Math.abs(size*2)+1;
        Token[] actions = new Token [length];
        Arrays.fill(actions, 0, actions.length, (size > 0) ? Token.INDENT : Token.DEDENT);
        for (int i=0; i < actions.length; i+=2)
            actions[i] = Token.NEWLINE;
        return actions;
    }

    private Token[] indentTokens(int size) {
        Token[] actions = new Token [Math.abs(size*2)];
        Arrays.fill(actions, 0, actions.length, (size > 0) ? Token.INDENT : Token.DEDENT);
        for (int i=0; i < actions.length; i+=2)
            actions[i] = Token.NEWLINE;
        return actions;
    }

    private Token[] create(Token token) {
		return new Token[]{token};
	}

    public boolean bracketsMode(){
        return (brackets > 0);
    }

    public int getLevel() {
        return level;
    }

	public Token[] actions() {
		return tokens;
	}

	public void openBracket(int size) {
        this.tokens = indentTokens(size);
        this.level+= size;
        this.brackets+= size;
	}

	public void closeBracket(int size) {
        if (bracketsMode() && (brackets-size >= 0)){
            this.tokens = indentTokens(-size);
            this.level-= size;
            this.brackets-= size;
        }
        else
            this.tokens = create(Token.ERROR);
    }

    public void eof() {
        if (!bracketsMode()){
            this.tokens = indentTokens(-level);
            this.level-= level;
        }
        else
            this.tokens = create(Token.ERROR);
    }

	public void addSemicolon(int size) {
        if (bracketsMode() && size == 1)
            this.tokens = create(Token.NEWLINE);
        else
            this.tokens = create(Token.ERROR);
	}

    public enum Token{
        INDENT, DEDENT, NEWLINE, ERROR
    }
}
