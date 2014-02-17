package monet.tara.compiler.intellij.metamodel;

import java.util.ArrayList;
import java.util.List;

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
        if (bracketsMode())
            this.tokens = create(Token.ERROR);
        else{
            int newLevel = (length(text) / this.tabSize);
            List<Token> result = getIndentationTokens(newLevel - level);
            if (newLevel-level < 0) result.add(Token.NEWLINE);
            this.tokens = result.toArray(new Token [result.size()]);
            this.level = newLevel;
        }
	}

    private int length(String text){
        int value = 0;
        for(int i = 0; i < text.length(); i++)
            value += (text.charAt(i) == ('\t')) ? this.tabSize : 1;
        return value;
    }

    private List<Token> getIndentationTokens(int size) {
        List<Token> result = new ArrayList<>();
        Token indentToken = (size > 0) ? Token.INDENT : Token.DEDENT;
        for (int i=0; i < Math.abs(size*2); i++)
            result.add( (i % 2 == 0)? Token.NEWLINE : indentToken);
        return result;
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

	public void openBracket() {
        List<Token> result = getIndentationTokens(1);
        this.tokens = result.toArray(new Token [result.size()]);
        this.level++;
        this.brackets++;
	}

	public void closeBracket() {
        if (!bracketsMode())
            this.tokens = create(Token.ERROR);
        else{
            List<Token> result = getIndentationTokens(-1);
            this.tokens = result.toArray(new Token[result.size()]);
            this.level--;
            this.brackets--;
        }
	}

	public void addSemicolon(int size) {
        if (bracketsMode() && size==1)
            this.tokens = create(Token.NEWLINE);
        else
            this.tokens = create(Token.ERROR);
	}

    public enum Token{
        INDENT, DEDENT, NEWLINE, ERROR
    }
}
