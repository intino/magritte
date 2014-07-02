// Generated from /Users/octavio/workspace/tara/rt/src/siani/tara/compiler/parser/antlr/TaraLexer.g4 by ANTLR 4.x

package siani.tara.compiler.parser.antlr;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TaraLexer extends Lexer {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		METAIDENTIFIER=1, CASE=2, PRIVATE=3, MULTIPLE=4, REQUIRED=5, ROOT=6, TERMINAL=7, 
		INTENTION=8, HAS_NAME=9, VAR=10, PROPERTY=11, WORD=12, RESOURCE=13, IMPORT=14, 
		BOX=15, LEFT_SQUARE=16, RIGHT_SQUARE=17, LEFT_PARENTHESIS=18, RIGHT_PARENTHESIS=19, 
		OPEN_BRACKET=20, CLOSE_BRACKET=21, OPEN_AN=22, CLOSE_AN=23, COLON=24, 
		COMMA=25, DOT=26, ASSIGN=27, APHOSTROPHE=28, SEMICOLON=29, STAR=30, POSITIVE=31, 
		DASHES=32, DASH=33, ALIAS_TYPE=34, INT_TYPE=35, NATURAL_TYPE=36, DOUBLE_TYPE=37, 
		STRING_TYPE=38, BOOLEAN_TYPE=39, EMPTY=40, BOOLEAN_VALUE=41, POSITIVE_VALUE=42, 
		NEGATIVE_VALUE=43, DOUBLE_VALUE=44, STRING_VALUE=45, STRING_MULTILINE_VALUE_KEY=46, 
		IDENTIFIER=47, DIGIT=48, LETTER=49, NEWLINE=50, SPACES=51, DOC=52, SP=53, 
		NL=54, NEW_LINE_INDENT=55, DEDENT=56, UNKNOWN_TOKEN=57;
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
		"'2'", "'3'", "'4'", "'5'", "'6'", "'7'", "'8'", "'9'"
	};
	public static final String[] ruleNames = {
		"METAIDENTIFIER", "CASE", "PRIVATE", "MULTIPLE", "REQUIRED", "ROOT", "TERMINAL", 
		"INTENTION", "HAS_NAME", "VAR", "PROPERTY", "WORD", "RESOURCE", "IMPORT", 
		"BOX", "LEFT_SQUARE", "RIGHT_SQUARE", "LEFT_PARENTHESIS", "RIGHT_PARENTHESIS", 
		"OPEN_BRACKET", "CLOSE_BRACKET", "OPEN_AN", "CLOSE_AN", "COLON", "COMMA", 
		"DOT", "ASSIGN", "APHOSTROPHE", "SEMICOLON", "STAR", "POSITIVE", "DASHES", 
		"DASH", "ALIAS_TYPE", "INT_TYPE", "NATURAL_TYPE", "DOUBLE_TYPE", "STRING_TYPE", 
		"BOOLEAN_TYPE", "EMPTY", "BOOLEAN_VALUE", "POSITIVE_VALUE", "NEGATIVE_VALUE", 
		"DOUBLE_VALUE", "STRING_VALUE", "STRING_MULTILINE_VALUE_KEY", "IDENTIFIER", 
		"DIGIT", "LETTER", "NEWLINE", "SPACES", "DOC", "SP", "NL", "NEW_LINE_INDENT", 
		"DEDENT", "UNKNOWN_TOKEN"
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


	public TaraLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "TaraLexer.g4"; }

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
		case 49: NEWLINE_action((RuleContext)_localctx, actionIndex); break;
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2;\u01c3\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13"+
		"\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\20\3\20\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25"+
		"\3\25\3\25\3\26\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\33"+
		"\3\33\3\34\3\34\3\35\3\35\3\36\6\36\u0101\n\36\r\36\16\36\u0102\3\36\3"+
		"\36\3\37\3\37\3 \3 \3!\3!\6!\u010d\n!\r!\16!\u010e\3\"\3\"\3#\3#\3#\3"+
		"#\3#\3#\3$\3$\3$\3$\3$\3$\3$\3$\3%\3%\3%\3%\3%\3%\3%\3%\3&\3&\3&\3&\3"+
		"&\3&\3&\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3(\3(\3(\3(\3(\3(\3(\3(\3)\3)\3)\3"+
		")\3)\3)\3*\3*\3*\3*\3*\3*\3*\3*\3*\5*\u014e\n*\3+\5+\u0151\n+\3+\6+\u0154"+
		"\n+\r+\16+\u0155\3,\3,\6,\u015a\n,\r,\16,\u015b\3-\3-\5-\u0160\n-\3-\6"+
		"-\u0163\n-\r-\16-\u0164\3-\3-\6-\u0169\n-\r-\16-\u016a\3.\3.\7.\u016f"+
		"\n.\f.\16.\u0172\13.\3.\3.\3/\3/\7/\u0178\n/\f/\16/\u017b\13/\3/\3/\3"+
		"\60\3\60\3\60\7\60\u0182\n\60\f\60\16\60\u0185\13\60\3\61\3\61\3\62\3"+
		"\62\3\63\6\63\u018c\n\63\r\63\16\63\u018d\3\63\7\63\u0191\n\63\f\63\16"+
		"\63\u0194\13\63\3\63\3\63\3\64\6\64\u0199\n\64\r\64\16\64\u019a\3\64\5"+
		"\64\u019e\n\64\3\64\3\64\3\65\3\65\7\65\u01a4\n\65\f\65\16\65\u01a7\13"+
		"\65\3\65\3\65\3\66\3\66\3\67\5\67\u01ae\n\67\3\67\3\67\5\67\u01b2\n\67"+
		"\38\38\38\38\38\38\38\39\39\39\39\39\39\39\3:\3:\3\u01a5\2;\3\3\5\4\7"+
		"\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22"+
		"#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C"+
		"#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a\62c\63e\64g\65i\66k\67m8o9q:s;\3\2"+
		"\7\3\2))\3\2//\3\2\62;\4\2C\\c|\4\2\13\13\"\"\u01d7\2\3\3\2\2\2\2\5\3"+
		"\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2"+
		"\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3"+
		"\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'"+
		"\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63"+
		"\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2"+
		"?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3"+
		"\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2"+
		"\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2"+
		"e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q\3"+
		"\2\2\2\2s\3\2\2\2\3u\3\2\2\2\5}\3\2\2\2\7\u0082\3\2\2\2\t\u008a\3\2\2"+
		"\2\13\u0093\3\2\2\2\r\u009c\3\2\2\2\17\u00a1\3\2\2\2\21\u00aa\3\2\2\2"+
		"\23\u00b4\3\2\2\2\25\u00bd\3\2\2\2\27\u00c1\3\2\2\2\31\u00ca\3\2\2\2\33"+
		"\u00cf\3\2\2\2\35\u00d8\3\2\2\2\37\u00df\3\2\2\2!\u00e3\3\2\2\2#\u00e5"+
		"\3\2\2\2%\u00e7\3\2\2\2\'\u00e9\3\2\2\2)\u00eb\3\2\2\2+\u00ee\3\2\2\2"+
		"-\u00f1\3\2\2\2/\u00f3\3\2\2\2\61\u00f5\3\2\2\2\63\u00f7\3\2\2\2\65\u00f9"+
		"\3\2\2\2\67\u00fb\3\2\2\29\u00fd\3\2\2\2;\u0100\3\2\2\2=\u0106\3\2\2\2"+
		"?\u0108\3\2\2\2A\u010a\3\2\2\2C\u0110\3\2\2\2E\u0112\3\2\2\2G\u0118\3"+
		"\2\2\2I\u0120\3\2\2\2K\u0128\3\2\2\2M\u012f\3\2\2\2O\u0136\3\2\2\2Q\u013e"+
		"\3\2\2\2S\u014d\3\2\2\2U\u0150\3\2\2\2W\u0157\3\2\2\2Y\u015f\3\2\2\2["+
		"\u016c\3\2\2\2]\u0175\3\2\2\2_\u017e\3\2\2\2a\u0186\3\2\2\2c\u0188\3\2"+
		"\2\2e\u018b\3\2\2\2g\u0198\3\2\2\2i\u01a1\3\2\2\2k\u01aa\3\2\2\2m\u01b1"+
		"\3\2\2\2o\u01b3\3\2\2\2q\u01ba\3\2\2\2s\u01c1\3\2\2\2uv\7E\2\2vw\7q\2"+
		"\2wx\7p\2\2xy\7e\2\2yz\7g\2\2z{\7r\2\2{|\7v\2\2|\4\3\2\2\2}~\7e\2\2~\177"+
		"\7c\2\2\177\u0080\7u\2\2\u0080\u0081\7g\2\2\u0081\6\3\2\2\2\u0082\u0083"+
		"\7r\2\2\u0083\u0084\7t\2\2\u0084\u0085\7k\2\2\u0085\u0086\7x\2\2\u0086"+
		"\u0087\7c\2\2\u0087\u0088\7v\2\2\u0088\u0089\7g\2\2\u0089\b\3\2\2\2\u008a"+
		"\u008b\7o\2\2\u008b\u008c\7w\2\2\u008c\u008d\7n\2\2\u008d\u008e\7v\2\2"+
		"\u008e\u008f\7k\2\2\u008f\u0090\7r\2\2\u0090\u0091\7n\2\2\u0091\u0092"+
		"\7g\2\2\u0092\n\3\2\2\2\u0093\u0094\7t\2\2\u0094\u0095\7g\2\2\u0095\u0096"+
		"\7s\2\2\u0096\u0097\7w\2\2\u0097\u0098\7k\2\2\u0098\u0099\7t\2\2\u0099"+
		"\u009a\7g\2\2\u009a\u009b\7f\2\2\u009b\f\3\2\2\2\u009c\u009d\7t\2\2\u009d"+
		"\u009e\7q\2\2\u009e\u009f\7q\2\2\u009f\u00a0\7v\2\2\u00a0\16\3\2\2\2\u00a1"+
		"\u00a2\7v\2\2\u00a2\u00a3\7g\2\2\u00a3\u00a4\7t\2\2\u00a4\u00a5\7o\2\2"+
		"\u00a5\u00a6\7k\2\2\u00a6\u00a7\7p\2\2\u00a7\u00a8\7c\2\2\u00a8\u00a9"+
		"\7n\2\2\u00a9\20\3\2\2\2\u00aa\u00ab\7k\2\2\u00ab\u00ac\7p\2\2\u00ac\u00ad"+
		"\7v\2\2\u00ad\u00ae\7g\2\2\u00ae\u00af\7p\2\2\u00af\u00b0\7v\2\2\u00b0"+
		"\u00b1\7k\2\2\u00b1\u00b2\7q\2\2\u00b2\u00b3\7p\2\2\u00b3\22\3\2\2\2\u00b4"+
		"\u00b5\7j\2\2\u00b5\u00b6\7c\2\2\u00b6\u00b7\7u\2\2\u00b7\u00b8\7/\2\2"+
		"\u00b8\u00b9\7p\2\2\u00b9\u00ba\7c\2\2\u00ba\u00bb\7o\2\2\u00bb\u00bc"+
		"\7g\2\2\u00bc\24\3\2\2\2\u00bd\u00be\7x\2\2\u00be\u00bf\7c\2\2\u00bf\u00c0"+
		"\7t\2\2\u00c0\26\3\2\2\2\u00c1\u00c2\7r\2\2\u00c2\u00c3\7t\2\2\u00c3\u00c4"+
		"\7q\2\2\u00c4\u00c5\7r\2\2\u00c5\u00c6\7g\2\2\u00c6\u00c7\7t\2\2\u00c7"+
		"\u00c8\7v\2\2\u00c8\u00c9\7{\2\2\u00c9\30\3\2\2\2\u00ca\u00cb\7Y\2\2\u00cb"+
		"\u00cc\7q\2\2\u00cc\u00cd\7t\2\2\u00cd\u00ce\7f\2\2\u00ce\32\3\2\2\2\u00cf"+
		"\u00d0\7T\2\2\u00d0\u00d1\7g\2\2\u00d1\u00d2\7u\2\2\u00d2\u00d3\7q\2\2"+
		"\u00d3\u00d4\7w\2\2\u00d4\u00d5\7t\2\2\u00d5\u00d6\7e\2\2\u00d6\u00d7"+
		"\7g\2\2\u00d7\34\3\2\2\2\u00d8\u00d9\7k\2\2\u00d9\u00da\7o\2\2\u00da\u00db"+
		"\7r\2\2\u00db\u00dc\7q\2\2\u00dc\u00dd\7t\2\2\u00dd\u00de\7v\2\2\u00de"+
		"\36\3\2\2\2\u00df\u00e0\7d\2\2\u00e0\u00e1\7q\2\2\u00e1\u00e2\7z\2\2\u00e2"+
		" \3\2\2\2\u00e3\u00e4\7]\2\2\u00e4\"\3\2\2\2\u00e5\u00e6\7_\2\2\u00e6"+
		"$\3\2\2\2\u00e7\u00e8\7*\2\2\u00e8&\3\2\2\2\u00e9\u00ea\7+\2\2\u00ea("+
		"\3\2\2\2\u00eb\u00ec\7}\2\2\u00ec\u00ed\b\25\2\2\u00ed*\3\2\2\2\u00ee"+
		"\u00ef\7\177\2\2\u00ef\u00f0\b\26\3\2\u00f0,\3\2\2\2\u00f1\u00f2\7>\2"+
		"\2\u00f2.\3\2\2\2\u00f3\u00f4\7@\2\2\u00f4\60\3\2\2\2\u00f5\u00f6\7<\2"+
		"\2\u00f6\62\3\2\2\2\u00f7\u00f8\7.\2\2\u00f8\64\3\2\2\2\u00f9\u00fa\7"+
		"\60\2\2\u00fa\66\3\2\2\2\u00fb\u00fc\7?\2\2\u00fc8\3\2\2\2\u00fd\u00fe"+
		"\7)\2\2\u00fe:\3\2\2\2\u00ff\u0101\7=\2\2\u0100\u00ff\3\2\2\2\u0101\u0102"+
		"\3\2\2\2\u0102\u0100\3\2\2\2\u0102\u0103\3\2\2\2\u0103\u0104\3\2\2\2\u0104"+
		"\u0105\b\36\4\2\u0105<\3\2\2\2\u0106\u0107\7,\2\2\u0107>\3\2\2\2\u0108"+
		"\u0109\7-\2\2\u0109@\3\2\2\2\u010a\u010c\5C\"\2\u010b\u010d\5C\"\2\u010c"+
		"\u010b\3\2\2\2\u010d\u010e\3\2\2\2\u010e\u010c\3\2\2\2\u010e\u010f\3\2"+
		"\2\2\u010fB\3\2\2\2\u0110\u0111\7/\2\2\u0111D\3\2\2\2\u0112\u0113\7C\2"+
		"\2\u0113\u0114\7n\2\2\u0114\u0115\7k\2\2\u0115\u0116\7c\2\2\u0116\u0117"+
		"\7u\2\2\u0117F\3\2\2\2\u0118\u0119\7K\2\2\u0119\u011a\7p\2\2\u011a\u011b"+
		"\7v\2\2\u011b\u011c\7g\2\2\u011c\u011d\7i\2\2\u011d\u011e\7g\2\2\u011e"+
		"\u011f\7t\2\2\u011fH\3\2\2\2\u0120\u0121\7P\2\2\u0121\u0122\7c\2\2\u0122"+
		"\u0123\7v\2\2\u0123\u0124\7w\2\2\u0124\u0125\7t\2\2\u0125\u0126\7c\2\2"+
		"\u0126\u0127\7n\2\2\u0127J\3\2\2\2\u0128\u0129\7F\2\2\u0129\u012a\7q\2"+
		"\2\u012a\u012b\7w\2\2\u012b\u012c\7d\2\2\u012c\u012d\7n\2\2\u012d\u012e"+
		"\7g\2\2\u012eL\3\2\2\2\u012f\u0130\7U\2\2\u0130\u0131\7v\2\2\u0131\u0132"+
		"\7t\2\2\u0132\u0133\7k\2\2\u0133\u0134\7p\2\2\u0134\u0135\7i\2\2\u0135"+
		"N\3\2\2\2\u0136\u0137\7D\2\2\u0137\u0138\7q\2\2\u0138\u0139\7q\2\2\u0139"+
		"\u013a\7n\2\2\u013a\u013b\7g\2\2\u013b\u013c\7c\2\2\u013c\u013d\7p\2\2"+
		"\u013dP\3\2\2\2\u013e\u013f\7g\2\2\u013f\u0140\7o\2\2\u0140\u0141\7r\2"+
		"\2\u0141\u0142\7v\2\2\u0142\u0143\7{\2\2\u0143R\3\2\2\2\u0144\u0145\7"+
		"v\2\2\u0145\u0146\7t\2\2\u0146\u0147\7w\2\2\u0147\u014e\7g\2\2\u0148\u0149"+
		"\7h\2\2\u0149\u014a\7c\2\2\u014a\u014b\7n\2\2\u014b\u014c\7u\2\2\u014c"+
		"\u014e\7g\2\2\u014d\u0144\3\2\2\2\u014d\u0148\3\2\2\2\u014eT\3\2\2\2\u014f"+
		"\u0151\5? \2\u0150\u014f\3\2\2\2\u0150\u0151\3\2\2\2\u0151\u0153\3\2\2"+
		"\2\u0152\u0154\5a\61\2\u0153\u0152\3\2\2\2\u0154\u0155\3\2\2\2\u0155\u0153"+
		"\3\2\2\2\u0155\u0156\3\2\2\2\u0156V\3\2\2\2\u0157\u0159\5C\"\2\u0158\u015a"+
		"\5a\61\2\u0159\u0158\3\2\2\2\u015a\u015b\3\2\2\2\u015b\u0159\3\2\2\2\u015b"+
		"\u015c\3\2\2\2\u015cX\3\2\2\2\u015d\u0160\5? \2\u015e\u0160\5C\"\2\u015f"+
		"\u015d\3\2\2\2\u015f\u015e\3\2\2\2\u015f\u0160\3\2\2\2\u0160\u0162\3\2"+
		"\2\2\u0161\u0163\5a\61\2\u0162\u0161\3\2\2\2\u0163\u0164\3\2\2\2\u0164"+
		"\u0162\3\2\2\2\u0164\u0165\3\2\2\2\u0165\u0166\3\2\2\2\u0166\u0168\5\65"+
		"\33\2\u0167\u0169\5a\61\2\u0168\u0167\3\2\2\2\u0169\u016a\3\2\2\2\u016a"+
		"\u0168\3\2\2\2\u016a\u016b\3\2\2\2\u016bZ\3\2\2\2\u016c\u0170\59\35\2"+
		"\u016d\u016f\n\2\2\2\u016e\u016d\3\2\2\2\u016f\u0172\3\2\2\2\u0170\u016e"+
		"\3\2\2\2\u0170\u0171\3\2\2\2\u0171\u0173\3\2\2\2\u0172\u0170\3\2\2\2\u0173"+
		"\u0174\59\35\2\u0174\\\3\2\2\2\u0175\u0179\5A!\2\u0176\u0178\n\3\2\2\u0177"+
		"\u0176\3\2\2\2\u0178\u017b\3\2\2\2\u0179\u0177\3\2\2\2\u0179\u017a\3\2"+
		"\2\2\u017a\u017c\3\2\2\2\u017b\u0179\3\2\2\2\u017c\u017d\5A!\2\u017d^"+
		"\3\2\2\2\u017e\u0183\5c\62\2\u017f\u0182\5a\61\2\u0180\u0182\5c\62\2\u0181"+
		"\u017f\3\2\2\2\u0181\u0180\3\2\2\2\u0182\u0185\3\2\2\2\u0183\u0181\3\2"+
		"\2\2\u0183\u0184\3\2\2\2\u0184`\3\2\2\2\u0185\u0183\3\2\2\2\u0186\u0187"+
		"\t\4\2\2\u0187b\3\2\2\2\u0188\u0189\t\5\2\2\u0189d\3\2\2\2\u018a\u018c"+
		"\5m\67\2\u018b\u018a\3\2\2\2\u018c\u018d\3\2\2\2\u018d\u018b\3\2\2\2\u018d"+
		"\u018e\3\2\2\2\u018e\u0192\3\2\2\2\u018f\u0191\5k\66\2\u0190\u018f\3\2"+
		"\2\2\u0191\u0194\3\2\2\2\u0192\u0190\3\2\2\2\u0192\u0193\3\2\2\2\u0193"+
		"\u0195\3\2\2\2\u0194\u0192\3\2\2\2\u0195\u0196\b\63\5\2\u0196f\3\2\2\2"+
		"\u0197\u0199\5k\66\2\u0198\u0197\3\2\2\2\u0199\u019a\3\2\2\2\u019a\u0198"+
		"\3\2\2\2\u019a\u019b\3\2\2\2\u019b\u019d\3\2\2\2\u019c\u019e\7\2\2\3\u019d"+
		"\u019c\3\2\2\2\u019d\u019e\3\2\2\2\u019e\u019f\3\2\2\2\u019f\u01a0\b\64"+
		"\6\2\u01a0h\3\2\2\2\u01a1\u01a5\7%\2\2\u01a2\u01a4\13\2\2\2\u01a3\u01a2"+
		"\3\2\2\2\u01a4\u01a7\3\2\2\2\u01a5\u01a6\3\2\2\2\u01a5\u01a3\3\2\2\2\u01a6"+
		"\u01a8\3\2\2\2\u01a7\u01a5\3\2\2\2\u01a8\u01a9\5m\67\2\u01a9j\3\2\2\2"+
		"\u01aa\u01ab\t\6\2\2\u01abl\3\2\2\2\u01ac\u01ae\7\17\2\2\u01ad\u01ac\3"+
		"\2\2\2\u01ad\u01ae\3\2\2\2\u01ae\u01af\3\2\2\2\u01af\u01b2\7\f\2\2\u01b0"+
		"\u01b2\7\f\2\2\u01b1\u01ad\3\2\2\2\u01b1\u01b0\3\2\2\2\u01b2n\3\2\2\2"+
		"\u01b3\u01b4\7k\2\2\u01b4\u01b5\7p\2\2\u01b5\u01b6\7f\2\2\u01b6\u01b7"+
		"\7g\2\2\u01b7\u01b8\7p\2\2\u01b8\u01b9\7v\2\2\u01b9p\3\2\2\2\u01ba\u01bb"+
		"\7f\2\2\u01bb\u01bc\7g\2\2\u01bc\u01bd\7f\2\2\u01bd\u01be\7g\2\2\u01be"+
		"\u01bf\7p\2\2\u01bf\u01c0\7v\2\2\u01c0r\3\2\2\2\u01c1\u01c2\13\2\2\2\u01c2"+
		"t\3\2\2\2\27\2\u0102\u010e\u014d\u0150\u0155\u015b\u015f\u0164\u016a\u0170"+
		"\u0179\u0181\u0183\u018d\u0192\u019a\u019d\u01a5\u01ad\u01b1\7\3\25\2"+
		"\3\26\3\3\36\4\3\63\5\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}