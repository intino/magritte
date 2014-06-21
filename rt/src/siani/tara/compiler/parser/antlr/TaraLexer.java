// Generated from /Users/oroncal/workspace/tara/rt/src/siani/tara/compiler/parser/antlr/TaraLexer.g4 by ANTLR 4.x

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
		METAIDENTIFIER=1, CASE=2, MULTIPLE=3, REQUIRED=4, ROOT=5, TERMINAL=6, 
		INTENTION=7, HAS_NAME=8, VAR=9, PROPERTY=10, WORD=11, RESOURCE=12, ABSTRACT=13, 
		BASE=14, IMPORT=15, PACKAGE=16, NAMESPACE=17, LEFT_SQUARE=18, RIGHT_SQUARE=19, 
		LEFT_PARENTHESIS=20, RIGHT_PARENTHESIS=21, OPEN_BRACKET=22, CLOSE_BRACKET=23, 
		OPEN_AN=24, CLOSE_AN=25, COLON=26, COMMA=27, DOT=28, ASSIGN=29, DOUBLE_COMMAS=30, 
		SEMICOLON=31, POSITIVE=32, NEGATIVE=33, ALIAS_TYPE=34, INT_TYPE=35, NATURAL_TYPE=36, 
		DOUBLE_TYPE=37, STRING_TYPE=38, BOOLEAN_TYPE=39, BOOLEAN_VALUE=40, POSITIVE_VALUE=41, 
		NEGATIVE_VALUE=42, DOUBLE_VALUE=43, STRING_VALUE=44, IDENTIFIER=45, DIGIT=46, 
		LETTER=47, NEWLINE=48, SPACES=49, DOC=50, SP=51, NL=52, NEW_LINE_INDENT=53, 
		DEDENT=54, UNKNOWN_TOKEN=55;
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
		"'2'", "'3'", "'4'", "'5'", "'6'", "'7'"
	};
	public static final String[] ruleNames = {
		"METAIDENTIFIER", "CASE", "MULTIPLE", "REQUIRED", "ROOT", "TERMINAL", 
		"INTENTION", "HAS_NAME", "VAR", "PROPERTY", "WORD", "RESOURCE", "ABSTRACT", 
		"BASE", "IMPORT", "PACKAGE", "NAMESPACE", "LEFT_SQUARE", "RIGHT_SQUARE", 
		"LEFT_PARENTHESIS", "RIGHT_PARENTHESIS", "OPEN_BRACKET", "CLOSE_BRACKET", 
		"OPEN_AN", "CLOSE_AN", "COLON", "COMMA", "DOT", "ASSIGN", "DOUBLE_COMMAS", 
		"SEMICOLON", "POSITIVE", "NEGATIVE", "ALIAS_TYPE", "INT_TYPE", "NATURAL_TYPE", 
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\29\u01bc\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\24\3\24"+
		"\3\25\3\25\3\26\3\26\3\27\3\27\3\27\3\30\3\30\3\30\3\31\3\31\3\32\3\32"+
		"\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3 \6 \u0111\n \r \16"+
		" \u0112\3 \3 \3!\3!\3\"\3\"\3#\3#\3#\3#\3#\3#\3$\3$\3$\3$\3$\3$\3$\3$"+
		"\3%\3%\3%\3%\3%\3%\3%\3%\3&\3&\3&\3&\3&\3&\3&\3\'\3\'\3\'\3\'\3\'\3\'"+
		"\3\'\3(\3(\3(\3(\3(\3(\3(\3(\3)\3)\3)\3)\3)\3)\3)\3)\3)\5)\u0150\n)\3"+
		"*\5*\u0153\n*\3*\6*\u0156\n*\r*\16*\u0157\3+\3+\6+\u015c\n+\r+\16+\u015d"+
		"\3,\3,\5,\u0162\n,\3,\6,\u0165\n,\r,\16,\u0166\3,\3,\6,\u016b\n,\r,\16"+
		",\u016c\3-\3-\7-\u0171\n-\f-\16-\u0174\13-\3-\3-\3.\3.\3.\7.\u017b\n."+
		"\f.\16.\u017e\13.\3/\3/\3\60\3\60\3\61\6\61\u0185\n\61\r\61\16\61\u0186"+
		"\3\61\7\61\u018a\n\61\f\61\16\61\u018d\13\61\3\61\3\61\3\62\6\62\u0192"+
		"\n\62\r\62\16\62\u0193\3\62\5\62\u0197\n\62\3\62\3\62\3\63\3\63\7\63\u019d"+
		"\n\63\f\63\16\63\u01a0\13\63\3\63\3\63\3\64\3\64\3\65\5\65\u01a7\n\65"+
		"\3\65\3\65\5\65\u01ab\n\65\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\67\3\67"+
		"\3\67\3\67\3\67\3\67\3\67\38\38\3\u019e\29\3\3\5\4\7\5\t\6\13\7\r\b\17"+
		"\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+"+
		"\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+"+
		"U,W-Y.[/]\60_\61a\62c\63e\64g\65i\66k\67m8o9\3\2\6\3\2$$\3\2\62;\4\2C"+
		"\\c|\4\2\13\13\"\"\u01ce\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2"+
		"\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25"+
		"\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2"+
		"\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2"+
		"\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3"+
		"\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2"+
		"\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2"+
		"Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3"+
		"\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2"+
		"\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\3q\3\2\2\2\5y\3\2\2\2\7~\3\2\2\2\t"+
		"\u0087\3\2\2\2\13\u0090\3\2\2\2\r\u0095\3\2\2\2\17\u009e\3\2\2\2\21\u00a8"+
		"\3\2\2\2\23\u00b1\3\2\2\2\25\u00b5\3\2\2\2\27\u00be\3\2\2\2\31\u00c3\3"+
		"\2\2\2\33\u00cc\3\2\2\2\35\u00d5\3\2\2\2\37\u00da\3\2\2\2!\u00e1\3\2\2"+
		"\2#\u00e9\3\2\2\2%\u00f3\3\2\2\2\'\u00f5\3\2\2\2)\u00f7\3\2\2\2+\u00f9"+
		"\3\2\2\2-\u00fb\3\2\2\2/\u00fe\3\2\2\2\61\u0101\3\2\2\2\63\u0103\3\2\2"+
		"\2\65\u0105\3\2\2\2\67\u0107\3\2\2\29\u0109\3\2\2\2;\u010b\3\2\2\2=\u010d"+
		"\3\2\2\2?\u0110\3\2\2\2A\u0116\3\2\2\2C\u0118\3\2\2\2E\u011a\3\2\2\2G"+
		"\u0120\3\2\2\2I\u0128\3\2\2\2K\u0130\3\2\2\2M\u0137\3\2\2\2O\u013e\3\2"+
		"\2\2Q\u014f\3\2\2\2S\u0152\3\2\2\2U\u0159\3\2\2\2W\u0161\3\2\2\2Y\u016e"+
		"\3\2\2\2[\u0177\3\2\2\2]\u017f\3\2\2\2_\u0181\3\2\2\2a\u0184\3\2\2\2c"+
		"\u0191\3\2\2\2e\u019a\3\2\2\2g\u01a3\3\2\2\2i\u01aa\3\2\2\2k\u01ac\3\2"+
		"\2\2m\u01b3\3\2\2\2o\u01ba\3\2\2\2qr\7E\2\2rs\7q\2\2st\7p\2\2tu\7e\2\2"+
		"uv\7g\2\2vw\7r\2\2wx\7v\2\2x\4\3\2\2\2yz\7e\2\2z{\7c\2\2{|\7u\2\2|}\7"+
		"g\2\2}\6\3\2\2\2~\177\7o\2\2\177\u0080\7w\2\2\u0080\u0081\7n\2\2\u0081"+
		"\u0082\7v\2\2\u0082\u0083\7k\2\2\u0083\u0084\7r\2\2\u0084\u0085\7n\2\2"+
		"\u0085\u0086\7g\2\2\u0086\b\3\2\2\2\u0087\u0088\7t\2\2\u0088\u0089\7g"+
		"\2\2\u0089\u008a\7s\2\2\u008a\u008b\7w\2\2\u008b\u008c\7k\2\2\u008c\u008d"+
		"\7t\2\2\u008d\u008e\7g\2\2\u008e\u008f\7f\2\2\u008f\n\3\2\2\2\u0090\u0091"+
		"\7t\2\2\u0091\u0092\7q\2\2\u0092\u0093\7q\2\2\u0093\u0094\7v\2\2\u0094"+
		"\f\3\2\2\2\u0095\u0096\7v\2\2\u0096\u0097\7g\2\2\u0097\u0098\7t\2\2\u0098"+
		"\u0099\7o\2\2\u0099\u009a\7k\2\2\u009a\u009b\7p\2\2\u009b\u009c\7c\2\2"+
		"\u009c\u009d\7n\2\2\u009d\16\3\2\2\2\u009e\u009f\7k\2\2\u009f\u00a0\7"+
		"p\2\2\u00a0\u00a1\7v\2\2\u00a1\u00a2\7g\2\2\u00a2\u00a3\7p\2\2\u00a3\u00a4"+
		"\7v\2\2\u00a4\u00a5\7k\2\2\u00a5\u00a6\7q\2\2\u00a6\u00a7\7p\2\2\u00a7"+
		"\20\3\2\2\2\u00a8\u00a9\7j\2\2\u00a9\u00aa\7c\2\2\u00aa\u00ab\7u\2\2\u00ab"+
		"\u00ac\7/\2\2\u00ac\u00ad\7p\2\2\u00ad\u00ae\7c\2\2\u00ae\u00af\7o\2\2"+
		"\u00af\u00b0\7g\2\2\u00b0\22\3\2\2\2\u00b1\u00b2\7x\2\2\u00b2\u00b3\7"+
		"c\2\2\u00b3\u00b4\7t\2\2\u00b4\24\3\2\2\2\u00b5\u00b6\7r\2\2\u00b6\u00b7"+
		"\7t\2\2\u00b7\u00b8\7q\2\2\u00b8\u00b9\7r\2\2\u00b9\u00ba\7g\2\2\u00ba"+
		"\u00bb\7t\2\2\u00bb\u00bc\7v\2\2\u00bc\u00bd\7{\2\2\u00bd\26\3\2\2\2\u00be"+
		"\u00bf\7Y\2\2\u00bf\u00c0\7q\2\2\u00c0\u00c1\7t\2\2\u00c1\u00c2\7f\2\2"+
		"\u00c2\30\3\2\2\2\u00c3\u00c4\7T\2\2\u00c4\u00c5\7g\2\2\u00c5\u00c6\7"+
		"u\2\2\u00c6\u00c7\7q\2\2\u00c7\u00c8\7w\2\2\u00c8\u00c9\7t\2\2\u00c9\u00ca"+
		"\7e\2\2\u00ca\u00cb\7g\2\2\u00cb\32\3\2\2\2\u00cc\u00cd\7c\2\2\u00cd\u00ce"+
		"\7d\2\2\u00ce\u00cf\7u\2\2\u00cf\u00d0\7v\2\2\u00d0\u00d1\7t\2\2\u00d1"+
		"\u00d2\7c\2\2\u00d2\u00d3\7e\2\2\u00d3\u00d4\7v\2\2\u00d4\34\3\2\2\2\u00d5"+
		"\u00d6\7d\2\2\u00d6\u00d7\7c\2\2\u00d7\u00d8\7u\2\2\u00d8\u00d9\7g\2\2"+
		"\u00d9\36\3\2\2\2\u00da\u00db\7k\2\2\u00db\u00dc\7o\2\2\u00dc\u00dd\7"+
		"r\2\2\u00dd\u00de\7q\2\2\u00de\u00df\7t\2\2\u00df\u00e0\7v\2\2\u00e0 "+
		"\3\2\2\2\u00e1\u00e2\7r\2\2\u00e2\u00e3\7c\2\2\u00e3\u00e4\7e\2\2\u00e4"+
		"\u00e5\7m\2\2\u00e5\u00e6\7c\2\2\u00e6\u00e7\7i\2\2\u00e7\u00e8\7g\2\2"+
		"\u00e8\"\3\2\2\2\u00e9\u00ea\7p\2\2\u00ea\u00eb\7c\2\2\u00eb\u00ec\7o"+
		"\2\2\u00ec\u00ed\7g\2\2\u00ed\u00ee\7u\2\2\u00ee\u00ef\7r\2\2\u00ef\u00f0"+
		"\7c\2\2\u00f0\u00f1\7e\2\2\u00f1\u00f2\7g\2\2\u00f2$\3\2\2\2\u00f3\u00f4"+
		"\7]\2\2\u00f4&\3\2\2\2\u00f5\u00f6\7_\2\2\u00f6(\3\2\2\2\u00f7\u00f8\7"+
		"*\2\2\u00f8*\3\2\2\2\u00f9\u00fa\7+\2\2\u00fa,\3\2\2\2\u00fb\u00fc\7}"+
		"\2\2\u00fc\u00fd\b\27\2\2\u00fd.\3\2\2\2\u00fe\u00ff\7\177\2\2\u00ff\u0100"+
		"\b\30\3\2\u0100\60\3\2\2\2\u0101\u0102\7>\2\2\u0102\62\3\2\2\2\u0103\u0104"+
		"\7@\2\2\u0104\64\3\2\2\2\u0105\u0106\7<\2\2\u0106\66\3\2\2\2\u0107\u0108"+
		"\7.\2\2\u01088\3\2\2\2\u0109\u010a\7\60\2\2\u010a:\3\2\2\2\u010b\u010c"+
		"\7?\2\2\u010c<\3\2\2\2\u010d\u010e\7$\2\2\u010e>\3\2\2\2\u010f\u0111\7"+
		"=\2\2\u0110\u010f\3\2\2\2\u0111\u0112\3\2\2\2\u0112\u0110\3\2\2\2\u0112"+
		"\u0113\3\2\2\2\u0113\u0114\3\2\2\2\u0114\u0115\b \4\2\u0115@\3\2\2\2\u0116"+
		"\u0117\7-\2\2\u0117B\3\2\2\2\u0118\u0119\7/\2\2\u0119D\3\2\2\2\u011a\u011b"+
		"\7C\2\2\u011b\u011c\7n\2\2\u011c\u011d\7k\2\2\u011d\u011e\7c\2\2\u011e"+
		"\u011f\7u\2\2\u011fF\3\2\2\2\u0120\u0121\7K\2\2\u0121\u0122\7p\2\2\u0122"+
		"\u0123\7v\2\2\u0123\u0124\7g\2\2\u0124\u0125\7i\2\2\u0125\u0126\7g\2\2"+
		"\u0126\u0127\7t\2\2\u0127H\3\2\2\2\u0128\u0129\7P\2\2\u0129\u012a\7c\2"+
		"\2\u012a\u012b\7v\2\2\u012b\u012c\7w\2\2\u012c\u012d\7t\2\2\u012d\u012e"+
		"\7c\2\2\u012e\u012f\7n\2\2\u012fJ\3\2\2\2\u0130\u0131\7F\2\2\u0131\u0132"+
		"\7q\2\2\u0132\u0133\7w\2\2\u0133\u0134\7d\2\2\u0134\u0135\7n\2\2\u0135"+
		"\u0136\7g\2\2\u0136L\3\2\2\2\u0137\u0138\7U\2\2\u0138\u0139\7v\2\2\u0139"+
		"\u013a\7t\2\2\u013a\u013b\7k\2\2\u013b\u013c\7p\2\2\u013c\u013d\7i\2\2"+
		"\u013dN\3\2\2\2\u013e\u013f\7D\2\2\u013f\u0140\7q\2\2\u0140\u0141\7q\2"+
		"\2\u0141\u0142\7n\2\2\u0142\u0143\7g\2\2\u0143\u0144\7c\2\2\u0144\u0145"+
		"\7p\2\2\u0145P\3\2\2\2\u0146\u0147\7v\2\2\u0147\u0148\7t\2\2\u0148\u0149"+
		"\7w\2\2\u0149\u0150\7g\2\2\u014a\u014b\7h\2\2\u014b\u014c\7c\2\2\u014c"+
		"\u014d\7n\2\2\u014d\u014e\7u\2\2\u014e\u0150\7g\2\2\u014f\u0146\3\2\2"+
		"\2\u014f\u014a\3\2\2\2\u0150R\3\2\2\2\u0151\u0153\5A!\2\u0152\u0151\3"+
		"\2\2\2\u0152\u0153\3\2\2\2\u0153\u0155\3\2\2\2\u0154\u0156\5]/\2\u0155"+
		"\u0154\3\2\2\2\u0156\u0157\3\2\2\2\u0157\u0155\3\2\2\2\u0157\u0158\3\2"+
		"\2\2\u0158T\3\2\2\2\u0159\u015b\5C\"\2\u015a\u015c\5]/\2\u015b\u015a\3"+
		"\2\2\2\u015c\u015d\3\2\2\2\u015d\u015b\3\2\2\2\u015d\u015e\3\2\2\2\u015e"+
		"V\3\2\2\2\u015f\u0162\5A!\2\u0160\u0162\5C\"\2\u0161\u015f\3\2\2\2\u0161"+
		"\u0160\3\2\2\2\u0161\u0162\3\2\2\2\u0162\u0164\3\2\2\2\u0163\u0165\5]"+
		"/\2\u0164\u0163\3\2\2\2\u0165\u0166\3\2\2\2\u0166\u0164\3\2\2\2\u0166"+
		"\u0167\3\2\2\2\u0167\u0168\3\2\2\2\u0168\u016a\59\35\2\u0169\u016b\5]"+
		"/\2\u016a\u0169\3\2\2\2\u016b\u016c\3\2\2\2\u016c\u016a\3\2\2\2\u016c"+
		"\u016d\3\2\2\2\u016dX\3\2\2\2\u016e\u0172\5=\37\2\u016f\u0171\n\2\2\2"+
		"\u0170\u016f\3\2\2\2\u0171\u0174\3\2\2\2\u0172\u0170\3\2\2\2\u0172\u0173"+
		"\3\2\2\2\u0173\u0175\3\2\2\2\u0174\u0172\3\2\2\2\u0175\u0176\5=\37\2\u0176"+
		"Z\3\2\2\2\u0177\u017c\5_\60\2\u0178\u017b\5]/\2\u0179\u017b\5_\60\2\u017a"+
		"\u0178\3\2\2\2\u017a\u0179\3\2\2\2\u017b\u017e\3\2\2\2\u017c\u017a\3\2"+
		"\2\2\u017c\u017d\3\2\2\2\u017d\\\3\2\2\2\u017e\u017c\3\2\2\2\u017f\u0180"+
		"\t\3\2\2\u0180^\3\2\2\2\u0181\u0182\t\4\2\2\u0182`\3\2\2\2\u0183\u0185"+
		"\5i\65\2\u0184\u0183\3\2\2\2\u0185\u0186\3\2\2\2\u0186\u0184\3\2\2\2\u0186"+
		"\u0187\3\2\2\2\u0187\u018b\3\2\2\2\u0188\u018a\5g\64\2\u0189\u0188\3\2"+
		"\2\2\u018a\u018d\3\2\2\2\u018b\u0189\3\2\2\2\u018b\u018c\3\2\2\2\u018c"+
		"\u018e\3\2\2\2\u018d\u018b\3\2\2\2\u018e\u018f\b\61\5\2\u018fb\3\2\2\2"+
		"\u0190\u0192\5g\64\2\u0191\u0190\3\2\2\2\u0192\u0193\3\2\2\2\u0193\u0191"+
		"\3\2\2\2\u0193\u0194\3\2\2\2\u0194\u0196\3\2\2\2\u0195\u0197\7\2\2\3\u0196"+
		"\u0195\3\2\2\2\u0196\u0197\3\2\2\2\u0197\u0198\3\2\2\2\u0198\u0199\b\62"+
		"\6\2\u0199d\3\2\2\2\u019a\u019e\7)\2\2\u019b\u019d\13\2\2\2\u019c\u019b"+
		"\3\2\2\2\u019d\u01a0\3\2\2\2\u019e\u019f\3\2\2\2\u019e\u019c\3\2\2\2\u019f"+
		"\u01a1\3\2\2\2\u01a0\u019e\3\2\2\2\u01a1\u01a2\5i\65\2\u01a2f\3\2\2\2"+
		"\u01a3\u01a4\t\5\2\2\u01a4h\3\2\2\2\u01a5\u01a7\7\17\2\2\u01a6\u01a5\3"+
		"\2\2\2\u01a6\u01a7\3\2\2\2\u01a7\u01a8\3\2\2\2\u01a8\u01ab\7\f\2\2\u01a9"+
		"\u01ab\7\f\2\2\u01aa\u01a6\3\2\2\2\u01aa\u01a9\3\2\2\2\u01abj\3\2\2\2"+
		"\u01ac\u01ad\7k\2\2\u01ad\u01ae\7p\2\2\u01ae\u01af\7f\2\2\u01af\u01b0"+
		"\7g\2\2\u01b0\u01b1\7p\2\2\u01b1\u01b2\7v\2\2\u01b2l\3\2\2\2\u01b3\u01b4"+
		"\7f\2\2\u01b4\u01b5\7g\2\2\u01b5\u01b6\7f\2\2\u01b6\u01b7\7g\2\2\u01b7"+
		"\u01b8\7p\2\2\u01b8\u01b9\7v\2\2\u01b9n\3\2\2\2\u01ba\u01bb\13\2\2\2\u01bb"+
		"p\3\2\2\2\25\2\u0112\u014f\u0152\u0157\u015d\u0161\u0166\u016c\u0172\u017a"+
		"\u017c\u0186\u018b\u0193\u0196\u019e\u01a6\u01aa\7\3\27\2\3\30\3\3 \4"+
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