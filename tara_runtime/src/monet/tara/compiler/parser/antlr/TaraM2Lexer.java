// Generated from TaraM2Lexer.g4 by ANTLR 4.1

    package monet.tara.compiler.parser.antlr;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNSimulator;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TaraM2Lexer extends Lexer {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		CONCEPT=1, FROM=2, AS=3, FINAL=4, ABSTRACT=5, MULTIPLE=6, OPTIONAL=7, 
		HAS_CODE=8, EXTENSIBLE=9, WORD=10, VAR=11, ROOT=12, SINGLETON=13, LIST=14, 
		LEFT_BRACKET=15, RIGHT_BRACKET=16, INDENT=17, DEDENT=18, OPEN_BRACKET=19, 
		CLOSE_BRACKET=20, OPEN_AN=21, CLOSE_AN=22, COMMA=23, DOT=24, ASSIGN=25, 
		DOUBLE_COMMAS=26, SEMICOLON=27, POSITIVE=28, NEGATIVE=29, UID_TYPE=30, 
		INT_TYPE=31, NATURAL_TYPE=32, DOUBLE_TYPE=33, STRING_TYPE=34, BOOLEAN_TYPE=35, 
		BOOLEAN_VALUE=36, POSITIVE_VALUE=37, NEGATIVE_VALUE=38, DOUBLE_VALUE=39, 
		STRING_VALUE=40, IDENTIFIER=41, DIGIT=42, LETTER=43, ACCENTED_LETTER=44, 
		WHITE_LINE=45, SPACES=46, NEWLINE=47, DOC_BLOCK=48, DOC_LINE=49, SP=50, 
		NL=51, UNKNOWN_TOKEN=52;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'Concept'", "'from'", "'as'", "'final'", "'abstract'", "'multiple'", 
		"'optional'", "'has-code'", "'extensible'", "'Word'", "'var'", "'root'", 
		"'singleton'", "LIST", "'['", "']'", "'('", "')'", "'{'", "CLOSE_BRACKET", 
		"'<'", "'>'", "','", "'.'", "':'", "'\"'", "';'", "'+'", "'-'", "'Uid'", 
		"'Int'", "'Natural'", "'Double'", "'String'", "'Boolean'", "BOOLEAN_VALUE", 
		"POSITIVE_VALUE", "NEGATIVE_VALUE", "DOUBLE_VALUE", "STRING_VALUE", "IDENTIFIER", 
		"DIGIT", "LETTER", "ACCENTED_LETTER", "WHITE_LINE", "SPACES", "NEWLINE", 
		"DOC_BLOCK", "DOC_LINE", "SP", "NL", "UNKNOWN_TOKEN"
	};
	public static final String[] ruleNames = {
		"CONCEPT", "FROM", "AS", "FINAL", "ABSTRACT", "MULTIPLE", "OPTIONAL", 
		"HAS_CODE", "EXTENSIBLE", "WORD", "VAR", "ROOT", "SINGLETON", "LIST",
		"LEFT_BRACKET", "RIGHT_BRACKET", "INDENT", "DEDENT", "OPEN_BRACKET", "CLOSE_BRACKET", 
		"OPEN_AN", "CLOSE_AN", "COMMA", "DOT", "ASSIGN", "DOUBLE_COMMAS", "SEMICOLON", 
		"POSITIVE", "NEGATIVE", "UID_TYPE", "INT_TYPE", "NATURAL_TYPE", "DOUBLE_TYPE", 
		"STRING_TYPE", "BOOLEAN_TYPE", "BOOLEAN_VALUE", "POSITIVE_VALUE", "NEGATIVE_VALUE", 
		"DOUBLE_VALUE", "STRING_VALUE", "IDENTIFIER", "DIGIT", "LETTER", "ACCENTED_LETTER", 
		"WHITE_LINE", "SPACES", "NEWLINE", "DOC_BLOCK", "DOC_LINE", "SP", "NL", 
		"UNKNOWN_TOKEN"
	};


	    private static java.util.Queue<Token>   queue = new java.util.LinkedList<Token>();
	    private static java.util.Stack<Integer> stack = new java.util.Stack<java.lang.Integer>();
	    private static int brackets = 0;

	/*
	    @Override
	    public void recover(final LexerNoViableAltException error) {
	        throw new RuntimeException(error);
	    }
	*/

	    @Override
	    public void reset(){
	        super.reset();
	        queue.clear();
	        stack.clear();
	        brackets=0;
	    }

	    @Override
	    public void emit(Token token) {
	        if (token.getType() == EOF) endReached();
	        queue.offer(token);
	        setToken(token);
	    }

	    @Override
	    public Token nextToken() {
	        super.nextToken();
	        return queue.isEmpty() ? emitEOF() : queue.poll();
	    }

	    private void emitToken(int ttype) {
	        setType(ttype);
	        emit();
	    }

	    private int getTextLength(String text){
	        int value = 0;
	        for(int i = 0; i < text.length(); i++)
	            if (text.charAt(i) == ('\t')) value += 4;
	            else value += 1;
	        return value;
	    }

	    private boolean isTextIndented(int textLength){
	        if (!stack.empty())
	            return textLength > stack.peek();
	        return false;
	    }

	    private boolean isTextDedented(int textLength){
	        if (!stack.empty())
	            return textLength < stack.peek();
	        return false;
	    }

	    private void calculateIndentationToken(String text) {
	        int textLength = getTextLength(text);
	        if (stack.empty() || isTextIndented(textLength)){
	            stack.push(textLength);
	            emitToken(INDENT);
	        } else {
	            while(isTextDedented(textLength)) {
	                stack.pop();
	                emitToken(DEDENT);
	            }
	        }
	    }

	    private boolean nextCharEqualTo(char character) {
	        char nextCharacter = (char) _input.LA(1);
	        return (nextCharacter == character)? true : false;
	    }

	    private boolean firstTokenInLine() {
	       return (_tokenStartCharPositionInLine==0);
	    }

	    private void endReached() {
	        if (brackets>0) emitToken(UNKNOWN_TOKEN);
	        else emitToken(NEWLINE);
	        while (!stack.empty()) {
	            stack.pop();
	            emitToken(DEDENT);
	        }
	    }

	    private void evaluateSpaces() {
	        if (firstTokenInLine() && !nextCharEqualTo('\n'))
	            calculateIndentationToken(getText());
	        setChannel(HIDDEN);
	    }

	    private void evaluateNewline() {
	        if (brackets>0) emitToken(UNKNOWN_TOKEN);
	        else{
	            if (!nextCharEqualTo(' ') && !nextCharEqualTo('\t'))
	                endReached();
	        }
	    }

	    private void openBracket() {
	        brackets++;
	        emitToken(NEWLINE);
	        emitToken(INDENT);
	    }

	    private void closeBracket() {
	        if (brackets<=0) emitToken(UNKNOWN_TOKEN);
	        else{
	            brackets--;
	            emitToken(DEDENT);
	        }
	    }

	    private void semicolon(){
	        if (brackets>0)
	            emitToken(NEWLINE);
	        else
	            emitToken(UNKNOWN_TOKEN);
	    }

	/*
	    private void calculateIndentationTokenInline(int indentSpaces) {
	        int textLength = (stack.empty())? indentSpaces : stack.peek()+indentSpaces;
	        textLength = (textLength<0)? 0 : textLength;
	        calculateIndentationToken(textLength);
	    }
	*/




	public TaraM2Lexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "TaraM2Lexer.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 18: OPEN_BRACKET_action((RuleContext)_localctx, actionIndex); break;

		case 19: CLOSE_BRACKET_action((RuleContext)_localctx, actionIndex); break;

		case 26: SEMICOLON_action((RuleContext)_localctx, actionIndex); break;

		case 44: WHITE_LINE_action((RuleContext)_localctx, actionIndex); break;

		case 45: SPACES_action((RuleContext)_localctx, actionIndex); break;

		case 46: NEWLINE_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void OPEN_BRACKET_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:  openBracket();  break;
		}
	}
	private void SEMICOLON_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 2:  semicolon();  break;
		}
	}
	private void NEWLINE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 4:  evaluateNewline();  break;
		}
	}
	private void WHITE_LINE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 5: _channel = HIDDEN;  break;
		}
	}
	private void SPACES_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 3:  evaluateSpaces();  break;
		}
	}
	private void CLOSE_BRACKET_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1:  closeBracket();  break;
		}
	}

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\2\66\u0194\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64"+
		"\t\64\4\65\t\65\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3"+
		"\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r"+
		"\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17"+
		"\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\24\3\25\3\25\5\25"+
		"\u00d9\n\25\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32"+
		"\3\33\3\33\3\34\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3\37\3\37\3 \3"+
		" \3 \3 \3!\3!\3!\3!\3!\3!\3!\3!\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3#\3#\3#\3"+
		"#\3#\3#\3#\3$\3$\3$\3$\3$\3$\3$\3$\3%\3%\3%\3%\3%\3%\3%\3%\3%\5%\u011f"+
		"\n%\3&\5&\u0122\n&\3&\6&\u0125\n&\r&\16&\u0126\3\'\3\'\6\'\u012b\n\'\r"+
		"\'\16\'\u012c\3(\3(\5(\u0131\n(\3(\6(\u0134\n(\r(\16(\u0135\3(\3(\6(\u013a"+
		"\n(\r(\16(\u013b\3)\3)\3)\3)\3)\6)\u0143\n)\r)\16)\u0144\3)\3)\3*\3*\3"+
		"*\7*\u014c\n*\f*\16*\u014f\13*\3+\3+\3,\3,\3-\3-\3.\6.\u0158\n.\r.\16"+
		".\u0159\3.\3.\3.\3.\3/\6/\u0161\n/\r/\16/\u0162\3/\3/\3\60\6\60\u0168"+
		"\n\60\r\60\16\60\u0169\3\60\3\60\3\61\3\61\3\61\3\61\7\61\u0172\n\61\f"+
		"\61\16\61\u0175\13\61\3\61\3\61\3\61\3\61\5\61\u017b\n\61\3\62\3\62\3"+
		"\62\3\62\7\62\u0181\n\62\f\62\16\62\u0184\13\62\3\62\3\62\5\62\u0188\n"+
		"\62\3\63\3\63\3\64\5\64\u018d\n\64\3\64\3\64\5\64\u0191\n\64\3\65\3\65"+
		"\4\u0173\u0182\66\3\3\1\5\4\1\7\5\1\t\6\1\13\7\1\r\b\1\17\t\1\21\n\1\23"+
		"\13\1\25\f\1\27\r\1\31\16\1\33\17\1\35\20\1\37\21\1!\22\1#\23\1%\24\1"+
		"\'\25\2)\26\3+\27\1-\30\1/\31\1\61\32\1\63\33\1\65\34\1\67\35\49\36\1"+
		";\37\1= \1?!\1A\"\1C#\1E$\1G%\1I&\1K\'\1M(\1O)\1Q*\1S+\1U,\1W-\1Y.\1["+
		"/\7]\60\5_\61\6a\62\1c\63\1e\64\1g\65\1i\66\1\3\2\6\3\2\62;\4\2C\\c|\f"+
		"\2\u00c3\u00c3\u00cb\u00cb\u00cf\u00cf\u00d5\u00d5\u00dc\u00dc\u00e3\u00e3"+
		"\u00eb\u00eb\u00ef\u00ef\u00f5\u00f5\u00fc\u00fc\4\2\13\13\"\"\u01ab\2"+
		"\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2"+
		"\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2"+
		"\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2"+
		"\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2"+
		"\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2"+
		"\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2"+
		"\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U"+
		"\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2"+
		"\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\3k\3\2\2\2\5s\3\2\2\2"+
		"\7x\3\2\2\2\t{\3\2\2\2\13\u0081\3\2\2\2\r\u008a\3\2\2\2\17\u0093\3\2\2"+
		"\2\21\u009c\3\2\2\2\23\u00a5\3\2\2\2\25\u00b0\3\2\2\2\27\u00b5\3\2\2\2"+
		"\31\u00b9\3\2\2\2\33\u00be\3\2\2\2\35\u00c8\3\2\2\2\37\u00cb\3\2\2\2!"+
		"\u00cd\3\2\2\2#\u00cf\3\2\2\2%\u00d1\3\2\2\2\'\u00d3\3\2\2\2)\u00d6\3"+
		"\2\2\2+\u00dc\3\2\2\2-\u00de\3\2\2\2/\u00e0\3\2\2\2\61\u00e2\3\2\2\2\63"+
		"\u00e4\3\2\2\2\65\u00e6\3\2\2\2\67\u00e8\3\2\2\29\u00eb\3\2\2\2;\u00ed"+
		"\3\2\2\2=\u00ef\3\2\2\2?\u00f3\3\2\2\2A\u00f7\3\2\2\2C\u00ff\3\2\2\2E"+
		"\u0106\3\2\2\2G\u010d\3\2\2\2I\u011e\3\2\2\2K\u0121\3\2\2\2M\u0128\3\2"+
		"\2\2O\u0130\3\2\2\2Q\u013d\3\2\2\2S\u0148\3\2\2\2U\u0150\3\2\2\2W\u0152"+
		"\3\2\2\2Y\u0154\3\2\2\2[\u0157\3\2\2\2]\u0160\3\2\2\2_\u0167\3\2\2\2a"+
		"\u016d\3\2\2\2c\u017c\3\2\2\2e\u0189\3\2\2\2g\u0190\3\2\2\2i\u0192\3\2"+
		"\2\2kl\7E\2\2lm\7q\2\2mn\7p\2\2no\7e\2\2op\7g\2\2pq\7r\2\2qr\7v\2\2r\4"+
		"\3\2\2\2st\7h\2\2tu\7t\2\2uv\7q\2\2vw\7o\2\2w\6\3\2\2\2xy\7c\2\2yz\7u"+
		"\2\2z\b\3\2\2\2{|\7h\2\2|}\7k\2\2}~\7p\2\2~\177\7c\2\2\177\u0080\7n\2"+
		"\2\u0080\n\3\2\2\2\u0081\u0082\7c\2\2\u0082\u0083\7d\2\2\u0083\u0084\7"+
		"u\2\2\u0084\u0085\7v\2\2\u0085\u0086\7t\2\2\u0086\u0087\7c\2\2\u0087\u0088"+
		"\7e\2\2\u0088\u0089\7v\2\2\u0089\f\3\2\2\2\u008a\u008b\7o\2\2\u008b\u008c"+
		"\7w\2\2\u008c\u008d\7n\2\2\u008d\u008e\7v\2\2\u008e\u008f\7k\2\2\u008f"+
		"\u0090\7r\2\2\u0090\u0091\7n\2\2\u0091\u0092\7g\2\2\u0092\16\3\2\2\2\u0093"+
		"\u0094\7q\2\2\u0094\u0095\7r\2\2\u0095\u0096\7v\2\2\u0096\u0097\7k\2\2"+
		"\u0097\u0098\7q\2\2\u0098\u0099\7p\2\2\u0099\u009a\7c\2\2\u009a\u009b"+
		"\7n\2\2\u009b\20\3\2\2\2\u009c\u009d\7j\2\2\u009d\u009e\7c\2\2\u009e\u009f"+
		"\7u\2\2\u009f\u00a0\7/\2\2\u00a0\u00a1\7e\2\2\u00a1\u00a2\7q\2\2\u00a2"+
		"\u00a3\7f\2\2\u00a3\u00a4\7g\2\2\u00a4\22\3\2\2\2\u00a5\u00a6\7g\2\2\u00a6"+
		"\u00a7\7z\2\2\u00a7\u00a8\7v\2\2\u00a8\u00a9\7g\2\2\u00a9\u00aa\7p\2\2"+
		"\u00aa\u00ab\7u\2\2\u00ab\u00ac\7k\2\2\u00ac\u00ad\7d\2\2\u00ad\u00ae"+
		"\7n\2\2\u00ae\u00af\7g\2\2\u00af\24\3\2\2\2\u00b0\u00b1\7Y\2\2\u00b1\u00b2"+
		"\7q\2\2\u00b2\u00b3\7t\2\2\u00b3\u00b4\7f\2\2\u00b4\26\3\2\2\2\u00b5\u00b6"+
		"\7x\2\2\u00b6\u00b7\7c\2\2\u00b7\u00b8\7t\2\2\u00b8\30\3\2\2\2\u00b9\u00ba"+
		"\7t\2\2\u00ba\u00bb\7q\2\2\u00bb\u00bc\7q\2\2\u00bc\u00bd\7v\2\2\u00bd"+
		"\32\3\2\2\2\u00be\u00bf\7u\2\2\u00bf\u00c0\7k\2\2\u00c0\u00c1\7p\2\2\u00c1"+
		"\u00c2\7i\2\2\u00c2\u00c3\7n\2\2\u00c3\u00c4\7g\2\2\u00c4\u00c5\7v\2\2"+
		"\u00c5\u00c6\7q\2\2\u00c6\u00c7\7p\2\2\u00c7\34\3\2\2\2\u00c8\u00c9\5"+
		"\37\20\2\u00c9\u00ca\5!\21\2\u00ca\36\3\2\2\2\u00cb\u00cc\7]\2\2\u00cc"+
		" \3\2\2\2\u00cd\u00ce\7_\2\2\u00ce\"\3\2\2\2\u00cf\u00d0\7*\2\2\u00d0"+
		"$\3\2\2\2\u00d1\u00d2\7+\2\2\u00d2&\3\2\2\2\u00d3\u00d4\7}\2\2\u00d4\u00d5"+
		"\b\24\2\2\u00d5(\3\2\2\2\u00d6\u00d8\7\177\2\2\u00d7\u00d9\5_\60\2\u00d8"+
		"\u00d7\3\2\2\2\u00d8\u00d9\3\2\2\2\u00d9\u00da\3\2\2\2\u00da\u00db\b\25"+
		"\3\2\u00db*\3\2\2\2\u00dc\u00dd\7>\2\2\u00dd,\3\2\2\2\u00de\u00df\7@\2"+
		"\2\u00df.\3\2\2\2\u00e0\u00e1\7.\2\2\u00e1\60\3\2\2\2\u00e2\u00e3\7\60"+
		"\2\2\u00e3\62\3\2\2\2\u00e4\u00e5\7<\2\2\u00e5\64\3\2\2\2\u00e6\u00e7"+
		"\7$\2\2\u00e7\66\3\2\2\2\u00e8\u00e9\7=\2\2\u00e9\u00ea\b\34\4\2\u00ea"+
		"8\3\2\2\2\u00eb\u00ec\7-\2\2\u00ec:\3\2\2\2\u00ed\u00ee\7/\2\2\u00ee<"+
		"\3\2\2\2\u00ef\u00f0\7W\2\2\u00f0\u00f1\7k\2\2\u00f1\u00f2\7f\2\2\u00f2"+
		">\3\2\2\2\u00f3\u00f4\7K\2\2\u00f4\u00f5\7p\2\2\u00f5\u00f6\7v\2\2\u00f6"+
		"@\3\2\2\2\u00f7\u00f8\7P\2\2\u00f8\u00f9\7c\2\2\u00f9\u00fa\7v\2\2\u00fa"+
		"\u00fb\7w\2\2\u00fb\u00fc\7t\2\2\u00fc\u00fd\7c\2\2\u00fd\u00fe\7n\2\2"+
		"\u00feB\3\2\2\2\u00ff\u0100\7F\2\2\u0100\u0101\7q\2\2\u0101\u0102\7w\2"+
		"\2\u0102\u0103\7d\2\2\u0103\u0104\7n\2\2\u0104\u0105\7g\2\2\u0105D\3\2"+
		"\2\2\u0106\u0107\7U\2\2\u0107\u0108\7v\2\2\u0108\u0109\7t\2\2\u0109\u010a"+
		"\7k\2\2\u010a\u010b\7p\2\2\u010b\u010c\7i\2\2\u010cF\3\2\2\2\u010d\u010e"+
		"\7D\2\2\u010e\u010f\7q\2\2\u010f\u0110\7q\2\2\u0110\u0111\7n\2\2\u0111"+
		"\u0112\7g\2\2\u0112\u0113\7c\2\2\u0113\u0114\7p\2\2\u0114H\3\2\2\2\u0115"+
		"\u0116\7v\2\2\u0116\u0117\7t\2\2\u0117\u0118\7w\2\2\u0118\u011f\7g\2\2"+
		"\u0119\u011a\7h\2\2\u011a\u011b\7c\2\2\u011b\u011c\7n\2\2\u011c\u011d"+
		"\7u\2\2\u011d\u011f\7g\2\2\u011e\u0115\3\2\2\2\u011e\u0119\3\2\2\2\u011f"+
		"J\3\2\2\2\u0120\u0122\59\35\2\u0121\u0120\3\2\2\2\u0121\u0122\3\2\2\2"+
		"\u0122\u0124\3\2\2\2\u0123\u0125\5U+\2\u0124\u0123\3\2\2\2\u0125\u0126"+
		"\3\2\2\2\u0126\u0124\3\2\2\2\u0126\u0127\3\2\2\2\u0127L\3\2\2\2\u0128"+
		"\u012a\5;\36\2\u0129\u012b\5U+\2\u012a\u0129\3\2\2\2\u012b\u012c\3\2\2"+
		"\2\u012c\u012a\3\2\2\2\u012c\u012d\3\2\2\2\u012dN\3\2\2\2\u012e\u0131"+
		"\59\35\2\u012f\u0131\5;\36\2\u0130\u012e\3\2\2\2\u0130\u012f\3\2\2\2\u0130"+
		"\u0131\3\2\2\2\u0131\u0133\3\2\2\2\u0132\u0134\5U+\2\u0133\u0132\3\2\2"+
		"\2\u0134\u0135\3\2\2\2\u0135\u0133\3\2\2\2\u0135\u0136\3\2\2\2\u0136\u0137"+
		"\3\2\2\2\u0137\u0139\5\61\31\2\u0138\u013a\5U+\2\u0139\u0138\3\2\2\2\u013a"+
		"\u013b\3\2\2\2\u013b\u0139\3\2\2\2\u013b\u013c\3\2\2\2\u013cP\3\2\2\2"+
		"\u013d\u0142\5\65\33\2\u013e\u0143\5U+\2\u013f\u0143\5W,\2\u0140\u0143"+
		"\5Y-\2\u0141\u0143\5i\65\2\u0142\u013e\3\2\2\2\u0142\u013f\3\2\2\2\u0142"+
		"\u0140\3\2\2\2\u0142\u0141\3\2\2\2\u0143\u0144\3\2\2\2\u0144\u0142\3\2"+
		"\2\2\u0144\u0145\3\2\2\2\u0145\u0146\3\2\2\2\u0146\u0147\5\65\33\2\u0147"+
		"R\3\2\2\2\u0148\u014d\5W,\2\u0149\u014c\5U+\2\u014a\u014c\5W,\2\u014b"+
		"\u0149\3\2\2\2\u014b\u014a\3\2\2\2\u014c\u014f\3\2\2\2\u014d\u014b\3\2"+
		"\2\2\u014d\u014e\3\2\2\2\u014eT\3\2\2\2\u014f\u014d\3\2\2\2\u0150\u0151"+
		"\t\2\2\2\u0151V\3\2\2\2\u0152\u0153\t\3\2\2\u0153X\3\2\2\2\u0154\u0155"+
		"\t\4\2\2\u0155Z\3\2\2\2\u0156\u0158\5e\63\2\u0157\u0156\3\2\2\2\u0158"+
		"\u0159\3\2\2\2\u0159\u0157\3\2\2\2\u0159\u015a\3\2\2\2\u015a\u015b\3\2"+
		"\2\2\u015b\u015c\7\2\2\3\u015c\u015d\3\2\2\2\u015d\u015e\b.\7\2\u015e"+
		"\\\3\2\2\2\u015f\u0161\5e\63\2\u0160\u015f\3\2\2\2\u0161\u0162\3\2\2\2"+
		"\u0162\u0160\3\2\2\2\u0162\u0163\3\2\2\2\u0163\u0164\3\2\2\2\u0164\u0165"+
		"\b/\5\2\u0165^\3\2\2\2\u0166\u0168\5g\64\2\u0167\u0166\3\2\2\2\u0168\u0169"+
		"\3\2\2\2\u0169\u0167\3\2\2\2\u0169\u016a\3\2\2\2\u016a\u016b\3\2\2\2\u016b"+
		"\u016c\b\60\6\2\u016c`\3\2\2\2\u016d\u016e\7\61\2\2\u016e\u016f\7A\2\2"+
		"\u016f\u0173\3\2\2\2\u0170\u0172\13\2\2\2\u0171\u0170\3\2\2\2\u0172\u0175"+
		"\3\2\2\2\u0173\u0174\3\2\2\2\u0173\u0171\3\2\2\2\u0174\u0176\3\2\2\2\u0175"+
		"\u0173\3\2\2\2\u0176\u0177\7A\2\2\u0177\u0178\7\61\2\2\u0178\u017a\3\2"+
		"\2\2\u0179\u017b\5g\64\2\u017a\u0179\3\2\2\2\u017a\u017b\3\2\2\2\u017b"+
		"b\3\2\2\2\u017c\u017d\7A\2\2\u017d\u017e\7A\2\2\u017e\u0182\3\2\2\2\u017f"+
		"\u0181\13\2\2\2\u0180\u017f\3\2\2\2\u0181\u0184\3\2\2\2\u0182\u0183\3"+
		"\2\2\2\u0182\u0180\3\2\2\2\u0183\u0187\3\2\2\2\u0184\u0182\3\2\2\2\u0185"+
		"\u0188\5g\64\2\u0186\u0188\7\2\2\3\u0187\u0185\3\2\2\2\u0187\u0186\3\2"+
		"\2\2\u0188d\3\2\2\2\u0189\u018a\t\5\2\2\u018af\3\2\2\2\u018b\u018d\7\17"+
		"\2\2\u018c\u018b\3\2\2\2\u018c\u018d\3\2\2\2\u018d\u018e\3\2\2\2\u018e"+
		"\u0191\7\f\2\2\u018f\u0191\7\f\2\2\u0190\u018c\3\2\2\2\u0190\u018f\3\2"+
		"\2\2\u0191h\3\2\2\2\u0192\u0193\13\2\2\2\u0193j\3\2\2\2\30\2\u00d8\u011e"+
		"\u0121\u0126\u012c\u0130\u0135\u013b\u0142\u0144\u014b\u014d\u0159\u0162"+
		"\u0169\u0173\u017a\u0182\u0187\u018c\u0190";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}