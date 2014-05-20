// Generated from /Users/oroncal/workspace/tara/rt/src/monet/tara/compiler/parser/antlr/TaraM2Lexer.g4 by ANTLR 4.x

	package monet.tara.compiler.parser.antlr;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TaraM2Lexer extends Lexer {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		CONCEPT=1, CASE=2, MULTIPLE=3, REQUIRED=4, ROOT=5, SINGLETON=6, GENERIC=7, 
		INTENTION=8, HAS_NAME=9, VAR=10, WORD=11, FINAL=12, ABSTRACT=13, BASE=14, 
		IMPORT=15, PACKAGE=16, LIST=17, LEFT_SQUARE=18, RIGHT_SQUARE=19, OPEN_BRACKET=20, 
		CLOSE_BRACKET=21, OPEN_AN=22, CLOSE_AN=23, COLON=24, COMMA=25, DOT=26, 
		ASSIGN=27, DOUBLE_COMMAS=28, SEMICOLON=29, POSITIVE=30, NEGATIVE=31, UID_TYPE=32, 
		INT_TYPE=33, NATURAL_TYPE=34, DOUBLE_TYPE=35, STRING_TYPE=36, BOOLEAN_TYPE=37, 
		BOOLEAN_VALUE=38, POSITIVE_VALUE=39, NEGATIVE_VALUE=40, DOUBLE_VALUE=41, 
		STRING_VALUE=42, IDENTIFIER=43, DIGIT=44, LETTER=45, NEWLINE=46, SPACES=47, 
		DOC=48, SP=49, NL=50, NEW_LINE_INDENT=51, DEDENT=52, UNKNOWN_TOKEN=53;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'", "'\\u0004'", "'\\u0005'", 
		"'\\u0006'", "'\\u0007'", "'\b'", "'\t'", "'\n'", "'\\u000B'", "'\f'", 
		"'\r'", "'\\u000E'", "'\\u000F'", "'\\u0010'", "'\\u0011'", "'\\u0012'", 
		"'\\u0013'", "'\\u0014'", "'\\u0015'", "'\\u0016'", "'\\u0017'", "'\\u0018'", 
		"'\\u0019'", "'\\u001A'", "'\\u001B'", "'\\u001C'", "'\\u001D'", "'\\u001E'", 
		"'\\u001F'", "' '", "'!'", "'\"'", "'#'", "'$'", "'%'", "'&'", "'''", 
		"'('", "')'", "'*'", "'+'", "','", "'-'", "'.'", "'/'", "'0'", "'1'", 
		"'2'", "'3'", "'4'", "'5'"
	};
	public static final String[] ruleNames = {
		"CONCEPT", "CASE", "MULTIPLE", "REQUIRED", "ROOT", "SINGLETON", "GENERIC", 
		"INTENTION", "HAS_NAME", "VAR", "WORD", "FINAL", "ABSTRACT", "BASE", "IMPORT", 
		"PACKAGE", "LIST", "LEFT_SQUARE", "RIGHT_SQUARE", "OPEN_BRACKET", "CLOSE_BRACKET", 
		"OPEN_AN", "CLOSE_AN", "COLON", "COMMA", "DOT", "ASSIGN", "DOUBLE_COMMAS", 
		"SEMICOLON", "POSITIVE", "NEGATIVE", "UID_TYPE", "INT_TYPE", "NATURAL_TYPE", 
		"DOUBLE_TYPE", "STRING_TYPE", "BOOLEAN_TYPE", "BOOLEAN_VALUE", "POSITIVE_VALUE", 
		"NEGATIVE_VALUE", "DOUBLE_VALUE", "STRING_VALUE", "IDENTIFIER", "DIGIT", 
		"LETTER", "NEWLINE", "SPACES", "DOC", "SP", "NL", "NEW_LINE_INDENT", "DEDENT", 
		"UNKNOWN_TOKEN"
	};


	    BlockManager blockManager = new BlockManager();
	    private static java.util.Queue<Token> queue = new java.util.LinkedList<>();

	    @Override
	    public void reset(){
	        super.reset();
	        queue.clear();
	        blockManager.reset();
	    }

	    @Override
	    public void emit(Token token) {
	        if (token.getType() == EOF) eof();
	        queue.offer(token);
	        setToken(token);
	    }

	    @Override
	    public Token nextToken() {
	        super.nextToken();
	        return queue.isEmpty()? emitEOF() : queue.poll();
	    }

	    private void emitToken(int ttype) {
	        setType(ttype);
	        emit();
	    }

	    private boolean isWhiteLineOrEOF() {
	        int character = _input.LA(1);
	        return (character == -1 || (char) character == '\n');
	    }

	    private String getTextSpaces(String text){
	        int index = (text.indexOf(' ') == -1)? text.indexOf('\t') : text.indexOf(' ');
	        return (index == -1)? "" : text.substring(index);
	    }

	    private void newlinesAndSpaces() {
	        if (!isWhiteLineOrEOF()){
	            blockManager.newlineAndSpaces(getTextSpaces(getText()));
	            sendTokens();
	        }
	        else
	            skip();
	    }

	    private void openBracket() {
	        blockManager.openBracket(getText().length());
	        sendTokens();
	    }

	    private void closeBracket() {
	        blockManager.closeBracket(getText().length());
	        sendTokens();
	    }

	    private void semicolon(){
	        blockManager.semicolon(getText().length());
	        sendTokens();
	    }

	    private void eof(){
	        blockManager.eof();
	        sendTokens();
	    }

	    private void sendTokens(){
	        blockManager.actions();
	        for (BlockManager.Token token : blockManager.actions())
	            emitToken(translate(token));
	    }

	    private int translate (BlockManager.Token token){
	        if (token.toString().equals("NEWLINE")) return NEWLINE;
	        if (token.toString().equals("DEDENT")) return DEDENT;
	        if (token.toString().equals("NEWLINE_INDENT")) return NEW_LINE_INDENT;
	        return UNKNOWN_TOKEN;
	    }


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
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 19: OPEN_BRACKET_action((RuleContext)_localctx, actionIndex); break;
		case 20: CLOSE_BRACKET_action((RuleContext)_localctx, actionIndex); break;
		case 28: SEMICOLON_action((RuleContext)_localctx, actionIndex); break;
		case 45: NEWLINE_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void OPEN_BRACKET_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:   openBracket();  break;
		}
	}
	private void SEMICOLON_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 2:  semicolon();  break;
		}
	}
	private void NEWLINE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 3:  newlinesAndSpaces();  break;
		}
	}
	private void CLOSE_BRACKET_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1:  closeBracket();  break;
		}
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\67\u01a8\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64"+
		"\t\64\4\65\t\65\4\66\t\66\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3"+
		"\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\f\3\f\3\f"+
		"\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3"+
		"\16\3\16\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3"+
		"\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\23\3\23\3\24\3"+
		"\24\3\25\3\25\3\25\3\26\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3"+
		"\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36\6\36\u00ff\n\36\r\36\16\36\u0100"+
		"\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3!\3!\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\""+
		"\3#\3#\3#\3#\3#\3#\3#\3#\3$\3$\3$\3$\3$\3$\3$\3%\3%\3%\3%\3%\3%\3%\3&"+
		"\3&\3&\3&\3&\3&\3&\3&\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\5\'\u013c\n"+
		"\'\3(\5(\u013f\n(\3(\6(\u0142\n(\r(\16(\u0143\3)\3)\6)\u0148\n)\r)\16"+
		")\u0149\3*\3*\5*\u014e\n*\3*\6*\u0151\n*\r*\16*\u0152\3*\3*\6*\u0157\n"+
		"*\r*\16*\u0158\3+\3+\7+\u015d\n+\f+\16+\u0160\13+\3+\3+\3,\3,\3,\7,\u0167"+
		"\n,\f,\16,\u016a\13,\3-\3-\3.\3.\3/\6/\u0171\n/\r/\16/\u0172\3/\7/\u0176"+
		"\n/\f/\16/\u0179\13/\3/\3/\3\60\6\60\u017e\n\60\r\60\16\60\u017f\3\60"+
		"\5\60\u0183\n\60\3\60\3\60\3\61\3\61\7\61\u0189\n\61\f\61\16\61\u018c"+
		"\13\61\3\61\3\61\3\62\3\62\3\63\5\63\u0193\n\63\3\63\3\63\5\63\u0197\n"+
		"\63\3\64\3\64\3\64\3\64\3\64\3\64\3\64\3\65\3\65\3\65\3\65\3\65\3\65\3"+
		"\65\3\66\3\66\3\u018a\2\67\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25"+
		"\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32"+
		"\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a"+
		"\62c\63e\64g\65i\66k\67\3\2\6\3\2$$\3\2\62;\4\2C\\c|\4\2\13\13\"\"\u01ba"+
		"\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2"+
		"\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2"+
		"\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2"+
		"\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2"+
		"\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3"+
		"\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2"+
		"\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2"+
		"U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3"+
		"\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\3m\3\2\2"+
		"\2\5u\3\2\2\2\7z\3\2\2\2\t\u0083\3\2\2\2\13\u008c\3\2\2\2\r\u0091\3\2"+
		"\2\2\17\u009b\3\2\2\2\21\u00a3\3\2\2\2\23\u00ad\3\2\2\2\25\u00b6\3\2\2"+
		"\2\27\u00ba\3\2\2\2\31\u00bf\3\2\2\2\33\u00c5\3\2\2\2\35\u00ce\3\2\2\2"+
		"\37\u00d3\3\2\2\2!\u00da\3\2\2\2#\u00e2\3\2\2\2%\u00e5\3\2\2\2\'\u00e7"+
		"\3\2\2\2)\u00e9\3\2\2\2+\u00ec\3\2\2\2-\u00ef\3\2\2\2/\u00f1\3\2\2\2\61"+
		"\u00f3\3\2\2\2\63\u00f5\3\2\2\2\65\u00f7\3\2\2\2\67\u00f9\3\2\2\29\u00fb"+
		"\3\2\2\2;\u00fe\3\2\2\2=\u0104\3\2\2\2?\u0106\3\2\2\2A\u0108\3\2\2\2C"+
		"\u010c\3\2\2\2E\u0114\3\2\2\2G\u011c\3\2\2\2I\u0123\3\2\2\2K\u012a\3\2"+
		"\2\2M\u013b\3\2\2\2O\u013e\3\2\2\2Q\u0145\3\2\2\2S\u014d\3\2\2\2U\u015a"+
		"\3\2\2\2W\u0163\3\2\2\2Y\u016b\3\2\2\2[\u016d\3\2\2\2]\u0170\3\2\2\2_"+
		"\u017d\3\2\2\2a\u0186\3\2\2\2c\u018f\3\2\2\2e\u0196\3\2\2\2g\u0198\3\2"+
		"\2\2i\u019f\3\2\2\2k\u01a6\3\2\2\2mn\7E\2\2no\7q\2\2op\7p\2\2pq\7e\2\2"+
		"qr\7g\2\2rs\7r\2\2st\7v\2\2t\4\3\2\2\2uv\7e\2\2vw\7c\2\2wx\7u\2\2xy\7"+
		"g\2\2y\6\3\2\2\2z{\7o\2\2{|\7w\2\2|}\7n\2\2}~\7v\2\2~\177\7k\2\2\177\u0080"+
		"\7r\2\2\u0080\u0081\7n\2\2\u0081\u0082\7g\2\2\u0082\b\3\2\2\2\u0083\u0084"+
		"\7t\2\2\u0084\u0085\7g\2\2\u0085\u0086\7s\2\2\u0086\u0087\7w\2\2\u0087"+
		"\u0088\7k\2\2\u0088\u0089\7t\2\2\u0089\u008a\7g\2\2\u008a\u008b\7f\2\2"+
		"\u008b\n\3\2\2\2\u008c\u008d\7t\2\2\u008d\u008e\7q\2\2\u008e\u008f\7q"+
		"\2\2\u008f\u0090\7v\2\2\u0090\f\3\2\2\2\u0091\u0092\7u\2\2\u0092\u0093"+
		"\7k\2\2\u0093\u0094\7p\2\2\u0094\u0095\7i\2\2\u0095\u0096\7n\2\2\u0096"+
		"\u0097\7g\2\2\u0097\u0098\7v\2\2\u0098\u0099\7q\2\2\u0099\u009a\7p\2\2"+
		"\u009a\16\3\2\2\2\u009b\u009c\7i\2\2\u009c\u009d\7g\2\2\u009d\u009e\7"+
		"p\2\2\u009e\u009f\7g\2\2\u009f\u00a0\7t\2\2\u00a0\u00a1\7k\2\2\u00a1\u00a2"+
		"\7e\2\2\u00a2\20\3\2\2\2\u00a3\u00a4\7k\2\2\u00a4\u00a5\7p\2\2\u00a5\u00a6"+
		"\7v\2\2\u00a6\u00a7\7g\2\2\u00a7\u00a8\7p\2\2\u00a8\u00a9\7v\2\2\u00a9"+
		"\u00aa\7k\2\2\u00aa\u00ab\7q\2\2\u00ab\u00ac\7p\2\2\u00ac\22\3\2\2\2\u00ad"+
		"\u00ae\7j\2\2\u00ae\u00af\7c\2\2\u00af\u00b0\7u\2\2\u00b0\u00b1\7/\2\2"+
		"\u00b1\u00b2\7p\2\2\u00b2\u00b3\7c\2\2\u00b3\u00b4\7o\2\2\u00b4\u00b5"+
		"\7g\2\2\u00b5\24\3\2\2\2\u00b6\u00b7\7x\2\2\u00b7\u00b8\7c\2\2\u00b8\u00b9"+
		"\7t\2\2\u00b9\26\3\2\2\2\u00ba\u00bb\7Y\2\2\u00bb\u00bc\7q\2\2\u00bc\u00bd"+
		"\7t\2\2\u00bd\u00be\7f\2\2\u00be\30\3\2\2\2\u00bf\u00c0\7h\2\2\u00c0\u00c1"+
		"\7k\2\2\u00c1\u00c2\7p\2\2\u00c2\u00c3\7c\2\2\u00c3\u00c4\7n\2\2\u00c4"+
		"\32\3\2\2\2\u00c5\u00c6\7c\2\2\u00c6\u00c7\7d\2\2\u00c7\u00c8\7u\2\2\u00c8"+
		"\u00c9\7v\2\2\u00c9\u00ca\7t\2\2\u00ca\u00cb\7c\2\2\u00cb\u00cc\7e\2\2"+
		"\u00cc\u00cd\7v\2\2\u00cd\34\3\2\2\2\u00ce\u00cf\7d\2\2\u00cf\u00d0\7"+
		"c\2\2\u00d0\u00d1\7u\2\2\u00d1\u00d2\7g\2\2\u00d2\36\3\2\2\2\u00d3\u00d4"+
		"\7k\2\2\u00d4\u00d5\7o\2\2\u00d5\u00d6\7r\2\2\u00d6\u00d7\7q\2\2\u00d7"+
		"\u00d8\7t\2\2\u00d8\u00d9\7v\2\2\u00d9 \3\2\2\2\u00da\u00db\7r\2\2\u00db"+
		"\u00dc\7c\2\2\u00dc\u00dd\7e\2\2\u00dd\u00de\7m\2\2\u00de\u00df\7c\2\2"+
		"\u00df\u00e0\7i\2\2\u00e0\u00e1\7g\2\2\u00e1\"\3\2\2\2\u00e2\u00e3\5%"+
		"\23\2\u00e3\u00e4\5\'\24\2\u00e4$\3\2\2\2\u00e5\u00e6\7]\2\2\u00e6&\3"+
		"\2\2\2\u00e7\u00e8\7_\2\2\u00e8(\3\2\2\2\u00e9\u00ea\7}\2\2\u00ea\u00eb"+
		"\b\25\2\2\u00eb*\3\2\2\2\u00ec\u00ed\7\177\2\2\u00ed\u00ee\b\26\3\2\u00ee"+
		",\3\2\2\2\u00ef\u00f0\7>\2\2\u00f0.\3\2\2\2\u00f1\u00f2\7@\2\2\u00f2\60"+
		"\3\2\2\2\u00f3\u00f4\7<\2\2\u00f4\62\3\2\2\2\u00f5\u00f6\7.\2\2\u00f6"+
		"\64\3\2\2\2\u00f7\u00f8\7\60\2\2\u00f8\66\3\2\2\2\u00f9\u00fa\7?\2\2\u00fa"+
		"8\3\2\2\2\u00fb\u00fc\7$\2\2\u00fc:\3\2\2\2\u00fd\u00ff\7=\2\2\u00fe\u00fd"+
		"\3\2\2\2\u00ff\u0100\3\2\2\2\u0100\u00fe\3\2\2\2\u0100\u0101\3\2\2\2\u0101"+
		"\u0102\3\2\2\2\u0102\u0103\b\36\4\2\u0103<\3\2\2\2\u0104\u0105\7-\2\2"+
		"\u0105>\3\2\2\2\u0106\u0107\7/\2\2\u0107@\3\2\2\2\u0108\u0109\7W\2\2\u0109"+
		"\u010a\7k\2\2\u010a\u010b\7f\2\2\u010bB\3\2\2\2\u010c\u010d\7K\2\2\u010d"+
		"\u010e\7p\2\2\u010e\u010f\7v\2\2\u010f\u0110\7g\2\2\u0110\u0111\7i\2\2"+
		"\u0111\u0112\7g\2\2\u0112\u0113\7t\2\2\u0113D\3\2\2\2\u0114\u0115\7P\2"+
		"\2\u0115\u0116\7c\2\2\u0116\u0117\7v\2\2\u0117\u0118\7w\2\2\u0118\u0119"+
		"\7t\2\2\u0119\u011a\7c\2\2\u011a\u011b\7n\2\2\u011bF\3\2\2\2\u011c\u011d"+
		"\7F\2\2\u011d\u011e\7q\2\2\u011e\u011f\7w\2\2\u011f\u0120\7d\2\2\u0120"+
		"\u0121\7n\2\2\u0121\u0122\7g\2\2\u0122H\3\2\2\2\u0123\u0124\7U\2\2\u0124"+
		"\u0125\7v\2\2\u0125\u0126\7t\2\2\u0126\u0127\7k\2\2\u0127\u0128\7p\2\2"+
		"\u0128\u0129\7i\2\2\u0129J\3\2\2\2\u012a\u012b\7D\2\2\u012b\u012c\7q\2"+
		"\2\u012c\u012d\7q\2\2\u012d\u012e\7n\2\2\u012e\u012f\7g\2\2\u012f\u0130"+
		"\7c\2\2\u0130\u0131\7p\2\2\u0131L\3\2\2\2\u0132\u0133\7v\2\2\u0133\u0134"+
		"\7t\2\2\u0134\u0135\7w\2\2\u0135\u013c\7g\2\2\u0136\u0137\7h\2\2\u0137"+
		"\u0138\7c\2\2\u0138\u0139\7n\2\2\u0139\u013a\7u\2\2\u013a\u013c\7g\2\2"+
		"\u013b\u0132\3\2\2\2\u013b\u0136\3\2\2\2\u013cN\3\2\2\2\u013d\u013f\5"+
		"=\37\2\u013e\u013d\3\2\2\2\u013e\u013f\3\2\2\2\u013f\u0141\3\2\2\2\u0140"+
		"\u0142\5Y-\2\u0141\u0140\3\2\2\2\u0142\u0143\3\2\2\2\u0143\u0141\3\2\2"+
		"\2\u0143\u0144\3\2\2\2\u0144P\3\2\2\2\u0145\u0147\5? \2\u0146\u0148\5"+
		"Y-\2\u0147\u0146\3\2\2\2\u0148\u0149\3\2\2\2\u0149\u0147\3\2\2\2\u0149"+
		"\u014a\3\2\2\2\u014aR\3\2\2\2\u014b\u014e\5=\37\2\u014c\u014e\5? \2\u014d"+
		"\u014b\3\2\2\2\u014d\u014c\3\2\2\2\u014d\u014e\3\2\2\2\u014e\u0150\3\2"+
		"\2\2\u014f\u0151\5Y-\2\u0150\u014f\3\2\2\2\u0151\u0152\3\2\2\2\u0152\u0150"+
		"\3\2\2\2\u0152\u0153\3\2\2\2\u0153\u0154\3\2\2\2\u0154\u0156\5\65\33\2"+
		"\u0155\u0157\5Y-\2\u0156\u0155\3\2\2\2\u0157\u0158\3\2\2\2\u0158\u0156"+
		"\3\2\2\2\u0158\u0159\3\2\2\2\u0159T\3\2\2\2\u015a\u015e\59\35\2\u015b"+
		"\u015d\n\2\2\2\u015c\u015b\3\2\2\2\u015d\u0160\3\2\2\2\u015e\u015c\3\2"+
		"\2\2\u015e\u015f\3\2\2\2\u015f\u0161\3\2\2\2\u0160\u015e\3\2\2\2\u0161"+
		"\u0162\59\35\2\u0162V\3\2\2\2\u0163\u0168\5[.\2\u0164\u0167\5Y-\2\u0165"+
		"\u0167\5[.\2\u0166\u0164\3\2\2\2\u0166\u0165\3\2\2\2\u0167\u016a\3\2\2"+
		"\2\u0168\u0166\3\2\2\2\u0168\u0169\3\2\2\2\u0169X\3\2\2\2\u016a\u0168"+
		"\3\2\2\2\u016b\u016c\t\3\2\2\u016cZ\3\2\2\2\u016d\u016e\t\4\2\2\u016e"+
		"\\\3\2\2\2\u016f\u0171\5e\63\2\u0170\u016f\3\2\2\2\u0171\u0172\3\2\2\2"+
		"\u0172\u0170\3\2\2\2\u0172\u0173\3\2\2\2\u0173\u0177\3\2\2\2\u0174\u0176"+
		"\5c\62\2\u0175\u0174\3\2\2\2\u0176\u0179\3\2\2\2\u0177\u0175\3\2\2\2\u0177"+
		"\u0178\3\2\2\2\u0178\u017a\3\2\2\2\u0179\u0177\3\2\2\2\u017a\u017b\b/"+
		"\5\2\u017b^\3\2\2\2\u017c\u017e\5c\62\2\u017d\u017c\3\2\2\2\u017e\u017f"+
		"\3\2\2\2\u017f\u017d\3\2\2\2\u017f\u0180\3\2\2\2\u0180\u0182\3\2\2\2\u0181"+
		"\u0183\7\2\2\3\u0182\u0181\3\2\2\2\u0182\u0183\3\2\2\2\u0183\u0184\3\2"+
		"\2\2\u0184\u0185\b\60\6\2\u0185`\3\2\2\2\u0186\u018a\7)\2\2\u0187\u0189"+
		"\13\2\2\2\u0188\u0187\3\2\2\2\u0189\u018c\3\2\2\2\u018a\u018b\3\2\2\2"+
		"\u018a\u0188\3\2\2\2\u018b\u018d\3\2\2\2\u018c\u018a\3\2\2\2\u018d\u018e"+
		"\5e\63\2\u018eb\3\2\2\2\u018f\u0190\t\5\2\2\u0190d\3\2\2\2\u0191\u0193"+
		"\7\17\2\2\u0192\u0191\3\2\2\2\u0192\u0193\3\2\2\2\u0193\u0194\3\2\2\2"+
		"\u0194\u0197\7\f\2\2\u0195\u0197\7\f\2\2\u0196\u0192\3\2\2\2\u0196\u0195"+
		"\3\2\2\2\u0197f\3\2\2\2\u0198\u0199\7k\2\2\u0199\u019a\7p\2\2\u019a\u019b"+
		"\7f\2\2\u019b\u019c\7g\2\2\u019c\u019d\7p\2\2\u019d\u019e\7v\2\2\u019e"+
		"h\3\2\2\2\u019f\u01a0\7f\2\2\u01a0\u01a1\7g\2\2\u01a1\u01a2\7f\2\2\u01a2"+
		"\u01a3\7g\2\2\u01a3\u01a4\7p\2\2\u01a4\u01a5\7v\2\2\u01a5j\3\2\2\2\u01a6"+
		"\u01a7\13\2\2\2\u01a7l\3\2\2\2\25\2\u0100\u013b\u013e\u0143\u0149\u014d"+
		"\u0152\u0158\u015e\u0166\u0168\u0172\u0177\u017f\u0182\u018a\u0192\u0196"+
		"\7\3\25\2\3\26\3\3\36\4\3/\5\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}