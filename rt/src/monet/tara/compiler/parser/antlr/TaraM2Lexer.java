// Generated from /home/bycor/Projects/Tara/src/AntlrM2/src/TaraM2Lexer.g4 by ANTLR 4.x

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
		CONCEPT=1, CASE=2, MULTIPLE=3, OPTIONAL=4, HAS_CODE=5, ROOT=6, SINGLETON=7, 
		GENERIC=8, INTENTION=9, EXTENSION=10, EXTENSIBLE=11, VAR=12, WORD=13, 
		FINAL=14, ABSTRACT=15, BASE=16, IMPORT=17, PACKAGE=18, LIST=19, LEFT_SQUARE=20, 
		RIGHT_SQUARE=21, OPEN_BRACKET=22, CLOSE_BRACKET=23, OPEN_AN=24, CLOSE_AN=25, 
		COLON=26, COMMA=27, DOT=28, ASSIGN=29, DOUBLE_COMMAS=30, SEMICOLON=31, 
		POSITIVE=32, NEGATIVE=33, UID_TYPE=34, INT_TYPE=35, NATURAL_TYPE=36, DOUBLE_TYPE=37, 
		STRING_TYPE=38, BOOLEAN_TYPE=39, BOOLEAN_VALUE=40, POSITIVE_VALUE=41, 
		NEGATIVE_VALUE=42, DOUBLE_VALUE=43, STRING_VALUE=44, IDENTIFIER=45, DIGIT=46, 
		LETTER=47, NEWLINE=48, SPACES=49, DOC=50, SP=51, NL=52, NEW_LINE_INDENT=53, 
		DEDENT=54, UNKNOWN_TOKEN=55;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'Concept'", "'case'", "'multiple'", "'optional'", "'has-code'", "'root'", 
		"'singleton'", "'generic'", "'intention'", "'extension'", "'extensible'", 
		"'var'", "'Word'", "'final'", "'abstract'", "'base'", "'import'", "'package'", 
		"LIST", "'['", "']'", "'{'", "'}'", "'<'", "'>'", "':'", "','", "'.'", 
		"'='", "'\"'", "SEMICOLON", "'+'", "'-'", "'Uid'", "'Integer'", "'Natural'", 
		"'Double'", "'String'", "'Boolean'", "BOOLEAN_VALUE", "POSITIVE_VALUE", 
		"NEGATIVE_VALUE", "DOUBLE_VALUE", "STRING_VALUE", "IDENTIFIER", "DIGIT", 
		"LETTER", "NEWLINE", "SPACES", "DOC", "SP", "NL", "'indent'", "'dedent'", 
		"UNKNOWN_TOKEN"
	};
	public static final String[] ruleNames = {
		"CONCEPT", "CASE", "MULTIPLE", "OPTIONAL", "HAS_CODE", "ROOT", "SINGLETON", 
		"GENERIC", "INTENTION", "EXTENSION", "EXTENSIBLE", "VAR", "WORD", "FINAL", 
		"ABSTRACT", "BASE", "IMPORT", "PACKAGE", "LIST", "LEFT_SQUARE", "RIGHT_SQUARE", 
		"OPEN_BRACKET", "CLOSE_BRACKET", "OPEN_AN", "CLOSE_AN", "COLON", "COMMA", 
		"DOT", "ASSIGN", "DOUBLE_COMMAS", "SEMICOLON", "POSITIVE", "NEGATIVE", 
		"UID_TYPE", "INT_TYPE", "NATURAL_TYPE", "DOUBLE_TYPE", "STRING_TYPE", 
		"BOOLEAN_TYPE", "BOOLEAN_VALUE", "POSITIVE_VALUE", "NEGATIVE_VALUE", "DOUBLE_VALUE", 
		"STRING_VALUE", "IDENTIFIER", "DIGIT", "LETTER", "NEWLINE", "SPACES", 
		"DOC", "SP", "NL", "NEW_LINE_INDENT", "DEDENT", "UNKNOWN_TOKEN"
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
		case 21: OPEN_BRACKET_action((RuleContext)_localctx, actionIndex); break;

		case 22: CLOSE_BRACKET_action((RuleContext)_localctx, actionIndex); break;

		case 30: SEMICOLON_action((RuleContext)_localctx, actionIndex); break;

		case 47: NEWLINE_action((RuleContext)_localctx, actionIndex); break;
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\29\u01c1\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3"+
		"\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21"+
		"\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\24\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\27\3\30"+
		"\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36"+
		"\3\37\3\37\3 \6 \u0118\n \r \16 \u0119\3 \3 \3!\3!\3\"\3\"\3#\3#\3#\3"+
		"#\3$\3$\3$\3$\3$\3$\3$\3$\3%\3%\3%\3%\3%\3%\3%\3%\3&\3&\3&\3&\3&\3&\3"+
		"&\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3(\3(\3(\3(\3(\3(\3(\3(\3)\3)\3)\3)\3)\3"+
		")\3)\3)\3)\5)\u0155\n)\3*\5*\u0158\n*\3*\6*\u015b\n*\r*\16*\u015c\3+\3"+
		"+\6+\u0161\n+\r+\16+\u0162\3,\3,\5,\u0167\n,\3,\6,\u016a\n,\r,\16,\u016b"+
		"\3,\3,\6,\u0170\n,\r,\16,\u0171\3-\3-\7-\u0176\n-\f-\16-\u0179\13-\3-"+
		"\3-\3.\3.\3.\7.\u0180\n.\f.\16.\u0183\13.\3/\3/\3\60\3\60\3\61\6\61\u018a"+
		"\n\61\r\61\16\61\u018b\3\61\7\61\u018f\n\61\f\61\16\61\u0192\13\61\3\61"+
		"\3\61\3\62\6\62\u0197\n\62\r\62\16\62\u0198\3\62\5\62\u019c\n\62\3\62"+
		"\3\62\3\63\3\63\7\63\u01a2\n\63\f\63\16\63\u01a5\13\63\3\63\3\63\3\64"+
		"\3\64\3\65\5\65\u01ac\n\65\3\65\3\65\5\65\u01b0\n\65\3\66\3\66\3\66\3"+
		"\66\3\66\3\66\3\66\3\67\3\67\3\67\3\67\3\67\3\67\3\67\38\38\3\u01a3\2"+
		"9\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20"+
		"\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37"+
		"= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a\62c\63e\64g\65i\66k\67m8o"+
		"9\3\2\6\3\2$$\3\2\62;\4\2C\\c|\4\2\13\13\"\"\u01d3\2\3\3\2\2\2\2\5\3\2"+
		"\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21"+
		"\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2"+
		"\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3"+
		"\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3"+
		"\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3"+
		"\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2"+
		"\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2"+
		"Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3"+
		"\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\3q\3\2\2"+
		"\2\5y\3\2\2\2\7~\3\2\2\2\t\u0087\3\2\2\2\13\u0090\3\2\2\2\r\u0099\3\2"+
		"\2\2\17\u009e\3\2\2\2\21\u00a8\3\2\2\2\23\u00b0\3\2\2\2\25\u00ba\3\2\2"+
		"\2\27\u00c4\3\2\2\2\31\u00cf\3\2\2\2\33\u00d3\3\2\2\2\35\u00d8\3\2\2\2"+
		"\37\u00de\3\2\2\2!\u00e7\3\2\2\2#\u00ec\3\2\2\2%\u00f3\3\2\2\2\'\u00fb"+
		"\3\2\2\2)\u00fe\3\2\2\2+\u0100\3\2\2\2-\u0102\3\2\2\2/\u0105\3\2\2\2\61"+
		"\u0108\3\2\2\2\63\u010a\3\2\2\2\65\u010c\3\2\2\2\67\u010e\3\2\2\29\u0110"+
		"\3\2\2\2;\u0112\3\2\2\2=\u0114\3\2\2\2?\u0117\3\2\2\2A\u011d\3\2\2\2C"+
		"\u011f\3\2\2\2E\u0121\3\2\2\2G\u0125\3\2\2\2I\u012d\3\2\2\2K\u0135\3\2"+
		"\2\2M\u013c\3\2\2\2O\u0143\3\2\2\2Q\u0154\3\2\2\2S\u0157\3\2\2\2U\u015e"+
		"\3\2\2\2W\u0166\3\2\2\2Y\u0173\3\2\2\2[\u017c\3\2\2\2]\u0184\3\2\2\2_"+
		"\u0186\3\2\2\2a\u0189\3\2\2\2c\u0196\3\2\2\2e\u019f\3\2\2\2g\u01a8\3\2"+
		"\2\2i\u01af\3\2\2\2k\u01b1\3\2\2\2m\u01b8\3\2\2\2o\u01bf\3\2\2\2qr\7E"+
		"\2\2rs\7q\2\2st\7p\2\2tu\7e\2\2uv\7g\2\2vw\7r\2\2wx\7v\2\2x\4\3\2\2\2"+
		"yz\7e\2\2z{\7c\2\2{|\7u\2\2|}\7g\2\2}\6\3\2\2\2~\177\7o\2\2\177\u0080"+
		"\7w\2\2\u0080\u0081\7n\2\2\u0081\u0082\7v\2\2\u0082\u0083\7k\2\2\u0083"+
		"\u0084\7r\2\2\u0084\u0085\7n\2\2\u0085\u0086\7g\2\2\u0086\b\3\2\2\2\u0087"+
		"\u0088\7q\2\2\u0088\u0089\7r\2\2\u0089\u008a\7v\2\2\u008a\u008b\7k\2\2"+
		"\u008b\u008c\7q\2\2\u008c\u008d\7p\2\2\u008d\u008e\7c\2\2\u008e\u008f"+
		"\7n\2\2\u008f\n\3\2\2\2\u0090\u0091\7j\2\2\u0091\u0092\7c\2\2\u0092\u0093"+
		"\7u\2\2\u0093\u0094\7/\2\2\u0094\u0095\7e\2\2\u0095\u0096\7q\2\2\u0096"+
		"\u0097\7f\2\2\u0097\u0098\7g\2\2\u0098\f\3\2\2\2\u0099\u009a\7t\2\2\u009a"+
		"\u009b\7q\2\2\u009b\u009c\7q\2\2\u009c\u009d\7v\2\2\u009d\16\3\2\2\2\u009e"+
		"\u009f\7u\2\2\u009f\u00a0\7k\2\2\u00a0\u00a1\7p\2\2\u00a1\u00a2\7i\2\2"+
		"\u00a2\u00a3\7n\2\2\u00a3\u00a4\7g\2\2\u00a4\u00a5\7v\2\2\u00a5\u00a6"+
		"\7q\2\2\u00a6\u00a7\7p\2\2\u00a7\20\3\2\2\2\u00a8\u00a9\7i\2\2\u00a9\u00aa"+
		"\7g\2\2\u00aa\u00ab\7p\2\2\u00ab\u00ac\7g\2\2\u00ac\u00ad\7t\2\2\u00ad"+
		"\u00ae\7k\2\2\u00ae\u00af\7e\2\2\u00af\22\3\2\2\2\u00b0\u00b1\7k\2\2\u00b1"+
		"\u00b2\7p\2\2\u00b2\u00b3\7v\2\2\u00b3\u00b4\7g\2\2\u00b4\u00b5\7p\2\2"+
		"\u00b5\u00b6\7v\2\2\u00b6\u00b7\7k\2\2\u00b7\u00b8\7q\2\2\u00b8\u00b9"+
		"\7p\2\2\u00b9\24\3\2\2\2\u00ba\u00bb\7g\2\2\u00bb\u00bc\7z\2\2\u00bc\u00bd"+
		"\7v\2\2\u00bd\u00be\7g\2\2\u00be\u00bf\7p\2\2\u00bf\u00c0\7u\2\2\u00c0"+
		"\u00c1\7k\2\2\u00c1\u00c2\7q\2\2\u00c2\u00c3\7p\2\2\u00c3\26\3\2\2\2\u00c4"+
		"\u00c5\7g\2\2\u00c5\u00c6\7z\2\2\u00c6\u00c7\7v\2\2\u00c7\u00c8\7g\2\2"+
		"\u00c8\u00c9\7p\2\2\u00c9\u00ca\7u\2\2\u00ca\u00cb\7k\2\2\u00cb\u00cc"+
		"\7d\2\2\u00cc\u00cd\7n\2\2\u00cd\u00ce\7g\2\2\u00ce\30\3\2\2\2\u00cf\u00d0"+
		"\7x\2\2\u00d0\u00d1\7c\2\2\u00d1\u00d2\7t\2\2\u00d2\32\3\2\2\2\u00d3\u00d4"+
		"\7Y\2\2\u00d4\u00d5\7q\2\2\u00d5\u00d6\7t\2\2\u00d6\u00d7\7f\2\2\u00d7"+
		"\34\3\2\2\2\u00d8\u00d9\7h\2\2\u00d9\u00da\7k\2\2\u00da\u00db\7p\2\2\u00db"+
		"\u00dc\7c\2\2\u00dc\u00dd\7n\2\2\u00dd\36\3\2\2\2\u00de\u00df\7c\2\2\u00df"+
		"\u00e0\7d\2\2\u00e0\u00e1\7u\2\2\u00e1\u00e2\7v\2\2\u00e2\u00e3\7t\2\2"+
		"\u00e3\u00e4\7c\2\2\u00e4\u00e5\7e\2\2\u00e5\u00e6\7v\2\2\u00e6 \3\2\2"+
		"\2\u00e7\u00e8\7d\2\2\u00e8\u00e9\7c\2\2\u00e9\u00ea\7u\2\2\u00ea\u00eb"+
		"\7g\2\2\u00eb\"\3\2\2\2\u00ec\u00ed\7k\2\2\u00ed\u00ee\7o\2\2\u00ee\u00ef"+
		"\7r\2\2\u00ef\u00f0\7q\2\2\u00f0\u00f1\7t\2\2\u00f1\u00f2\7v\2\2\u00f2"+
		"$\3\2\2\2\u00f3\u00f4\7r\2\2\u00f4\u00f5\7c\2\2\u00f5\u00f6\7e\2\2\u00f6"+
		"\u00f7\7m\2\2\u00f7\u00f8\7c\2\2\u00f8\u00f9\7i\2\2\u00f9\u00fa\7g\2\2"+
		"\u00fa&\3\2\2\2\u00fb\u00fc\5)\25\2\u00fc\u00fd\5+\26\2\u00fd(\3\2\2\2"+
		"\u00fe\u00ff\7]\2\2\u00ff*\3\2\2\2\u0100\u0101\7_\2\2\u0101,\3\2\2\2\u0102"+
		"\u0103\7}\2\2\u0103\u0104\b\27\2\2\u0104.\3\2\2\2\u0105\u0106\7\177\2"+
		"\2\u0106\u0107\b\30\3\2\u0107\60\3\2\2\2\u0108\u0109\7>\2\2\u0109\62\3"+
		"\2\2\2\u010a\u010b\7@\2\2\u010b\64\3\2\2\2\u010c\u010d\7<\2\2\u010d\66"+
		"\3\2\2\2\u010e\u010f\7.\2\2\u010f8\3\2\2\2\u0110\u0111\7\60\2\2\u0111"+
		":\3\2\2\2\u0112\u0113\7?\2\2\u0113<\3\2\2\2\u0114\u0115\7$\2\2\u0115>"+
		"\3\2\2\2\u0116\u0118\7=\2\2\u0117\u0116\3\2\2\2\u0118\u0119\3\2\2\2\u0119"+
		"\u0117\3\2\2\2\u0119\u011a\3\2\2\2\u011a\u011b\3\2\2\2\u011b\u011c\b "+
		"\4\2\u011c@\3\2\2\2\u011d\u011e\7-\2\2\u011eB\3\2\2\2\u011f\u0120\7/\2"+
		"\2\u0120D\3\2\2\2\u0121\u0122\7W\2\2\u0122\u0123\7k\2\2\u0123\u0124\7"+
		"f\2\2\u0124F\3\2\2\2\u0125\u0126\7K\2\2\u0126\u0127\7p\2\2\u0127\u0128"+
		"\7v\2\2\u0128\u0129\7g\2\2\u0129\u012a\7i\2\2\u012a\u012b\7g\2\2\u012b"+
		"\u012c\7t\2\2\u012cH\3\2\2\2\u012d\u012e\7P\2\2\u012e\u012f\7c\2\2\u012f"+
		"\u0130\7v\2\2\u0130\u0131\7w\2\2\u0131\u0132\7t\2\2\u0132\u0133\7c\2\2"+
		"\u0133\u0134\7n\2\2\u0134J\3\2\2\2\u0135\u0136\7F\2\2\u0136\u0137\7q\2"+
		"\2\u0137\u0138\7w\2\2\u0138\u0139\7d\2\2\u0139\u013a\7n\2\2\u013a\u013b"+
		"\7g\2\2\u013bL\3\2\2\2\u013c\u013d\7U\2\2\u013d\u013e\7v\2\2\u013e\u013f"+
		"\7t\2\2\u013f\u0140\7k\2\2\u0140\u0141\7p\2\2\u0141\u0142\7i\2\2\u0142"+
		"N\3\2\2\2\u0143\u0144\7D\2\2\u0144\u0145\7q\2\2\u0145\u0146\7q\2\2\u0146"+
		"\u0147\7n\2\2\u0147\u0148\7g\2\2\u0148\u0149\7c\2\2\u0149\u014a\7p\2\2"+
		"\u014aP\3\2\2\2\u014b\u014c\7v\2\2\u014c\u014d\7t\2\2\u014d\u014e\7w\2"+
		"\2\u014e\u0155\7g\2\2\u014f\u0150\7h\2\2\u0150\u0151\7c\2\2\u0151\u0152"+
		"\7n\2\2\u0152\u0153\7u\2\2\u0153\u0155\7g\2\2\u0154\u014b\3\2\2\2\u0154"+
		"\u014f\3\2\2\2\u0155R\3\2\2\2\u0156\u0158\5A!\2\u0157\u0156\3\2\2\2\u0157"+
		"\u0158\3\2\2\2\u0158\u015a\3\2\2\2\u0159\u015b\5]/\2\u015a\u0159\3\2\2"+
		"\2\u015b\u015c\3\2\2\2\u015c\u015a\3\2\2\2\u015c\u015d\3\2\2\2\u015dT"+
		"\3\2\2\2\u015e\u0160\5C\"\2\u015f\u0161\5]/\2\u0160\u015f\3\2\2\2\u0161"+
		"\u0162\3\2\2\2\u0162\u0160\3\2\2\2\u0162\u0163\3\2\2\2\u0163V\3\2\2\2"+
		"\u0164\u0167\5A!\2\u0165\u0167\5C\"\2\u0166\u0164\3\2\2\2\u0166\u0165"+
		"\3\2\2\2\u0166\u0167\3\2\2\2\u0167\u0169\3\2\2\2\u0168\u016a\5]/\2\u0169"+
		"\u0168\3\2\2\2\u016a\u016b\3\2\2\2\u016b\u0169\3\2\2\2\u016b\u016c\3\2"+
		"\2\2\u016c\u016d\3\2\2\2\u016d\u016f\59\35\2\u016e\u0170\5]/\2\u016f\u016e"+
		"\3\2\2\2\u0170\u0171\3\2\2\2\u0171\u016f\3\2\2\2\u0171\u0172\3\2\2\2\u0172"+
		"X\3\2\2\2\u0173\u0177\5=\37\2\u0174\u0176\n\2\2\2\u0175\u0174\3\2\2\2"+
		"\u0176\u0179\3\2\2\2\u0177\u0175\3\2\2\2\u0177\u0178\3\2\2\2\u0178\u017a"+
		"\3\2\2\2\u0179\u0177\3\2\2\2\u017a\u017b\5=\37\2\u017bZ\3\2\2\2\u017c"+
		"\u0181\5_\60\2\u017d\u0180\5]/\2\u017e\u0180\5_\60\2\u017f\u017d\3\2\2"+
		"\2\u017f\u017e\3\2\2\2\u0180\u0183\3\2\2\2\u0181\u017f\3\2\2\2\u0181\u0182"+
		"\3\2\2\2\u0182\\\3\2\2\2\u0183\u0181\3\2\2\2\u0184\u0185\t\3\2\2\u0185"+
		"^\3\2\2\2\u0186\u0187\t\4\2\2\u0187`\3\2\2\2\u0188\u018a\5i\65\2\u0189"+
		"\u0188\3\2\2\2\u018a\u018b\3\2\2\2\u018b\u0189\3\2\2\2\u018b\u018c\3\2"+
		"\2\2\u018c\u0190\3\2\2\2\u018d\u018f\5g\64\2\u018e\u018d\3\2\2\2\u018f"+
		"\u0192\3\2\2\2\u0190\u018e\3\2\2\2\u0190\u0191\3\2\2\2\u0191\u0193\3\2"+
		"\2\2\u0192\u0190\3\2\2\2\u0193\u0194\b\61\5\2\u0194b\3\2\2\2\u0195\u0197"+
		"\5g\64\2\u0196\u0195\3\2\2\2\u0197\u0198\3\2\2\2\u0198\u0196\3\2\2\2\u0198"+
		"\u0199\3\2\2\2\u0199\u019b\3\2\2\2\u019a\u019c\7\2\2\3\u019b\u019a\3\2"+
		"\2\2\u019b\u019c\3\2\2\2\u019c\u019d\3\2\2\2\u019d\u019e\b\62\6\2\u019e"+
		"d\3\2\2\2\u019f\u01a3\7)\2\2\u01a0\u01a2\13\2\2\2\u01a1\u01a0\3\2\2\2"+
		"\u01a2\u01a5\3\2\2\2\u01a3\u01a4\3\2\2\2\u01a3\u01a1\3\2\2\2\u01a4\u01a6"+
		"\3\2\2\2\u01a5\u01a3\3\2\2\2\u01a6\u01a7\5i\65\2\u01a7f\3\2\2\2\u01a8"+
		"\u01a9\t\5\2\2\u01a9h\3\2\2\2\u01aa\u01ac\7\17\2\2\u01ab\u01aa\3\2\2\2"+
		"\u01ab\u01ac\3\2\2\2\u01ac\u01ad\3\2\2\2\u01ad\u01b0\7\f\2\2\u01ae\u01b0"+
		"\7\f\2\2\u01af\u01ab\3\2\2\2\u01af\u01ae\3\2\2\2\u01b0j\3\2\2\2\u01b1"+
		"\u01b2\7k\2\2\u01b2\u01b3\7p\2\2\u01b3\u01b4\7f\2\2\u01b4\u01b5\7g\2\2"+
		"\u01b5\u01b6\7p\2\2\u01b6\u01b7\7v\2\2\u01b7l\3\2\2\2\u01b8\u01b9\7f\2"+
		"\2\u01b9\u01ba\7g\2\2\u01ba\u01bb\7f\2\2\u01bb\u01bc\7g\2\2\u01bc\u01bd"+
		"\7p\2\2\u01bd\u01be\7v\2\2\u01ben\3\2\2\2\u01bf\u01c0\13\2\2\2\u01c0p"+
		"\3\2\2\2\25\2\u0119\u0154\u0157\u015c\u0162\u0166\u016b\u0171\u0177\u017f"+
		"\u0181\u018b\u0190\u0198\u019b\u01a3\u01ab\u01af\7\3\27\2\3\30\3\3 \4"+
		"\3\61\5\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}