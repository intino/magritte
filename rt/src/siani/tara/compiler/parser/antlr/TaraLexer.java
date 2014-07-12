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
		METAIDENTIFIER=1, INTENTION=2, CASE=3, USE=4, BOX=5, METAMODEL=6, AS=7, 
		ON=8, IS=9, WITH=10, PRIVATE=11, SINGLE=12, REQUIRED=13, ROOT=14, TERMINAL=15, 
		NAMEABLE=16, PROPERTY=17, LEFT_PARENTHESIS=18, RIGHT_PARENTHESIS=19, LEFT_SQUARE=20, 
		RIGHT_SQUARE=21, LIST=22, OPEN_BRACKET=23, CLOSE_BRACKET=24, OPEN_AN=25, 
		CLOSE_AN=26, COLON=27, COMMA=28, DOT=29, EQUALS=30, APHOSTROPHE=31, SEMICOLON=32, 
		STAR=33, POSITIVE=34, DASH=35, DASHES=36, VAR=37, WORD=38, RESOURCE=39, 
		ALIAS_TYPE=40, INT_TYPE=41, NATURAL_TYPE=42, DOUBLE_TYPE=43, STRING_TYPE=44, 
		BOOLEAN_TYPE=45, DATE_TYPE=46, EMPTY=47, BOOLEAN_VALUE=48, POSITIVE_VALUE=49, 
		NEGATIVE_VALUE=50, DOUBLE_VALUE=51, STRING_VALUE=52, STRING_MULTILINE_VALUE_KEY=53, 
		IDENTIFIER=54, DIGIT=55, LETTER=56, NEWLINE=57, SPACES=58, DOC=59, SP=60, 
		NL=61, NEW_LINE_INDENT=62, DEDENT=63, UNKNOWN_TOKEN=64;
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
		"'2'", "'3'", "'4'", "'5'", "'6'", "'7'", "'8'", "'9'", "':'", "';'", 
		"'<'", "'='", "'>'", "'?'", "'@'"
	};
	public static final String[] ruleNames = {
		"METAIDENTIFIER", "INTENTION", "CASE", "USE", "BOX", "METAMODEL", "AS", 
		"ON", "IS", "WITH", "PRIVATE", "SINGLE", "REQUIRED", "ROOT", "TERMINAL", 
		"NAMEABLE", "PROPERTY", "LEFT_PARENTHESIS", "RIGHT_PARENTHESIS", "LEFT_SQUARE", 
		"RIGHT_SQUARE", "LIST", "OPEN_BRACKET", "CLOSE_BRACKET", "OPEN_AN", "CLOSE_AN", 
		"COLON", "COMMA", "DOT", "EQUALS", "APHOSTROPHE", "SEMICOLON", "STAR", 
		"POSITIVE", "DASH", "DASHES", "VAR", "WORD", "RESOURCE", "ALIAS_TYPE", 
		"INT_TYPE", "NATURAL_TYPE", "DOUBLE_TYPE", "STRING_TYPE", "BOOLEAN_TYPE", 
		"DATE_TYPE", "EMPTY", "BOOLEAN_VALUE", "POSITIVE_VALUE", "NEGATIVE_VALUE", 
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
		case 22: OPEN_BRACKET_action((RuleContext)_localctx, actionIndex); break;
		case 23: CLOSE_BRACKET_action((RuleContext)_localctx, actionIndex); break;
		case 31: SEMICOLON_action((RuleContext)_localctx, actionIndex); break;
		case 56: NEWLINE_action((RuleContext)_localctx, actionIndex); break;
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2B\u01ed\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6"+
		"\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3"+
		"\t\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27"+
		"\3\27\3\27\3\27\3\30\3\30\3\30\3\31\3\31\3\31\3\32\3\32\3\33\3\33\3\34"+
		"\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3!\6!\u0114\n!\r!\16!\u0115"+
		"\3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\6%\u0122\n%\r%\16%\u0123\3&\3&\3&\3&"+
		"\3\'\3\'\3\'\3\'\3\'\3(\3(\3(\3(\3(\3(\3(\3(\3(\3)\3)\3)\3)\3)\3)\3*\3"+
		"*\3*\3*\3*\3*\3*\3*\3+\3+\3+\3+\3+\3+\3+\3+\3,\3,\3,\3,\3,\3,\3,\3-\3"+
		"-\3-\3-\3-\3-\3-\3.\3.\3.\3.\3.\3.\3.\3.\3/\3/\3/\3/\3/\3\60\3\60\3\60"+
		"\3\60\3\60\3\60\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\5\61\u0178"+
		"\n\61\3\62\5\62\u017b\n\62\3\62\6\62\u017e\n\62\r\62\16\62\u017f\3\63"+
		"\3\63\6\63\u0184\n\63\r\63\16\63\u0185\3\64\3\64\5\64\u018a\n\64\3\64"+
		"\6\64\u018d\n\64\r\64\16\64\u018e\3\64\3\64\6\64\u0193\n\64\r\64\16\64"+
		"\u0194\3\65\3\65\7\65\u0199\n\65\f\65\16\65\u019c\13\65\3\65\3\65\3\66"+
		"\3\66\7\66\u01a2\n\66\f\66\16\66\u01a5\13\66\3\66\3\66\3\67\3\67\3\67"+
		"\7\67\u01ac\n\67\f\67\16\67\u01af\13\67\38\38\39\39\3:\6:\u01b6\n:\r:"+
		"\16:\u01b7\3:\7:\u01bb\n:\f:\16:\u01be\13:\3:\3:\3;\6;\u01c3\n;\r;\16"+
		";\u01c4\3;\5;\u01c8\n;\3;\3;\3<\3<\7<\u01ce\n<\f<\16<\u01d1\13<\3<\3<"+
		"\3=\3=\3>\5>\u01d8\n>\3>\3>\5>\u01dc\n>\3?\3?\3?\3?\3?\3?\3?\3@\3@\3@"+
		"\3@\3@\3@\3@\3A\3A\3\u01cf\2B\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13"+
		"\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61"+
		"\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61"+
		"a\62c\63e\64g\65i\66k\67m8o9q:s;u<w=y>{?}@\177A\u0081B\3\2\7\3\2))\3\2"+
		"//\3\2\62;\4\2C\\c|\4\2\13\13\"\"\u0201\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3"+
		"\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2"+
		"\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35"+
		"\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)"+
		"\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2"+
		"\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2"+
		"A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3"+
		"\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2"+
		"\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2"+
		"g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q\3\2\2\2\2s\3"+
		"\2\2\2\2u\3\2\2\2\2w\3\2\2\2\2y\3\2\2\2\2{\3\2\2\2\2}\3\2\2\2\2\177\3"+
		"\2\2\2\2\u0081\3\2\2\2\3\u0083\3\2\2\2\5\u008b\3\2\2\2\7\u0095\3\2\2\2"+
		"\t\u009a\3\2\2\2\13\u009e\3\2\2\2\r\u00a2\3\2\2\2\17\u00ac\3\2\2\2\21"+
		"\u00af\3\2\2\2\23\u00b2\3\2\2\2\25\u00b5\3\2\2\2\27\u00ba\3\2\2\2\31\u00c2"+
		"\3\2\2\2\33\u00c9\3\2\2\2\35\u00d2\3\2\2\2\37\u00d7\3\2\2\2!\u00e0\3\2"+
		"\2\2#\u00e9\3\2\2\2%\u00f2\3\2\2\2\'\u00f4\3\2\2\2)\u00f6\3\2\2\2+\u00f8"+
		"\3\2\2\2-\u00fa\3\2\2\2/\u00fe\3\2\2\2\61\u0101\3\2\2\2\63\u0104\3\2\2"+
		"\2\65\u0106\3\2\2\2\67\u0108\3\2\2\29\u010a\3\2\2\2;\u010c\3\2\2\2=\u010e"+
		"\3\2\2\2?\u0110\3\2\2\2A\u0113\3\2\2\2C\u0119\3\2\2\2E\u011b\3\2\2\2G"+
		"\u011d\3\2\2\2I\u011f\3\2\2\2K\u0125\3\2\2\2M\u0129\3\2\2\2O\u012e\3\2"+
		"\2\2Q\u0137\3\2\2\2S\u013d\3\2\2\2U\u0145\3\2\2\2W\u014d\3\2\2\2Y\u0154"+
		"\3\2\2\2[\u015b\3\2\2\2]\u0163\3\2\2\2_\u0168\3\2\2\2a\u0177\3\2\2\2c"+
		"\u017a\3\2\2\2e\u0181\3\2\2\2g\u0189\3\2\2\2i\u0196\3\2\2\2k\u019f\3\2"+
		"\2\2m\u01a8\3\2\2\2o\u01b0\3\2\2\2q\u01b2\3\2\2\2s\u01b5\3\2\2\2u\u01c2"+
		"\3\2\2\2w\u01cb\3\2\2\2y\u01d4\3\2\2\2{\u01db\3\2\2\2}\u01dd\3\2\2\2\177"+
		"\u01e4\3\2\2\2\u0081\u01eb\3\2\2\2\u0083\u0084\7E\2\2\u0084\u0085\7q\2"+
		"\2\u0085\u0086\7p\2\2\u0086\u0087\7e\2\2\u0087\u0088\7g\2\2\u0088\u0089"+
		"\7r\2\2\u0089\u008a\7v\2\2\u008a\4\3\2\2\2\u008b\u008c\7K\2\2\u008c\u008d"+
		"\7p\2\2\u008d\u008e\7v\2\2\u008e\u008f\7g\2\2\u008f\u0090\7p\2\2\u0090"+
		"\u0091\7v\2\2\u0091\u0092\7k\2\2\u0092\u0093\7q\2\2\u0093\u0094\7p\2\2"+
		"\u0094\6\3\2\2\2\u0095\u0096\7e\2\2\u0096\u0097\7c\2\2\u0097\u0098\7u"+
		"\2\2\u0098\u0099\7g\2\2\u0099\b\3\2\2\2\u009a\u009b\7w\2\2\u009b\u009c"+
		"\7u\2\2\u009c\u009d\7g\2\2\u009d\n\3\2\2\2\u009e\u009f\7d\2\2\u009f\u00a0"+
		"\7q\2\2\u00a0\u00a1\7z\2\2\u00a1\f\3\2\2\2\u00a2\u00a3\7o\2\2\u00a3\u00a4"+
		"\7g\2\2\u00a4\u00a5\7v\2\2\u00a5\u00a6\7c\2\2\u00a6\u00a7\7o\2\2\u00a7"+
		"\u00a8\7q\2\2\u00a8\u00a9\7f\2\2\u00a9\u00aa\7g\2\2\u00aa\u00ab\7n\2\2"+
		"\u00ab\16\3\2\2\2\u00ac\u00ad\7c\2\2\u00ad\u00ae\7u\2\2\u00ae\20\3\2\2"+
		"\2\u00af\u00b0\7q\2\2\u00b0\u00b1\7p\2\2\u00b1\22\3\2\2\2\u00b2\u00b3"+
		"\7k\2\2\u00b3\u00b4\7u\2\2\u00b4\24\3\2\2\2\u00b5\u00b6\7y\2\2\u00b6\u00b7"+
		"\7k\2\2\u00b7\u00b8\7v\2\2\u00b8\u00b9\7j\2\2\u00b9\26\3\2\2\2\u00ba\u00bb"+
		"\7r\2\2\u00bb\u00bc\7t\2\2\u00bc\u00bd\7k\2\2\u00bd\u00be\7x\2\2\u00be"+
		"\u00bf\7c\2\2\u00bf\u00c0\7v\2\2\u00c0\u00c1\7g\2\2\u00c1\30\3\2\2\2\u00c2"+
		"\u00c3\7u\2\2\u00c3\u00c4\7k\2\2\u00c4\u00c5\7p\2\2\u00c5\u00c6\7i\2\2"+
		"\u00c6\u00c7\7n\2\2\u00c7\u00c8\7g\2\2\u00c8\32\3\2\2\2\u00c9\u00ca\7"+
		"t\2\2\u00ca\u00cb\7g\2\2\u00cb\u00cc\7s\2\2\u00cc\u00cd\7w\2\2\u00cd\u00ce"+
		"\7k\2\2\u00ce\u00cf\7t\2\2\u00cf\u00d0\7g\2\2\u00d0\u00d1\7f\2\2\u00d1"+
		"\34\3\2\2\2\u00d2\u00d3\7t\2\2\u00d3\u00d4\7q\2\2\u00d4\u00d5\7q\2\2\u00d5"+
		"\u00d6\7v\2\2\u00d6\36\3\2\2\2\u00d7\u00d8\7v\2\2\u00d8\u00d9\7g\2\2\u00d9"+
		"\u00da\7t\2\2\u00da\u00db\7o\2\2\u00db\u00dc\7k\2\2\u00dc\u00dd\7p\2\2"+
		"\u00dd\u00de\7c\2\2\u00de\u00df\7n\2\2\u00df \3\2\2\2\u00e0\u00e1\7p\2"+
		"\2\u00e1\u00e2\7c\2\2\u00e2\u00e3\7o\2\2\u00e3\u00e4\7g\2\2\u00e4\u00e5"+
		"\7c\2\2\u00e5\u00e6\7d\2\2\u00e6\u00e7\7n\2\2\u00e7\u00e8\7g\2\2\u00e8"+
		"\"\3\2\2\2\u00e9\u00ea\7r\2\2\u00ea\u00eb\7t\2\2\u00eb\u00ec\7q\2\2\u00ec"+
		"\u00ed\7r\2\2\u00ed\u00ee\7g\2\2\u00ee\u00ef\7t\2\2\u00ef\u00f0\7v\2\2"+
		"\u00f0\u00f1\7{\2\2\u00f1$\3\2\2\2\u00f2\u00f3\7*\2\2\u00f3&\3\2\2\2\u00f4"+
		"\u00f5\7+\2\2\u00f5(\3\2\2\2\u00f6\u00f7\7]\2\2\u00f7*\3\2\2\2\u00f8\u00f9"+
		"\7_\2\2\u00f9,\3\2\2\2\u00fa\u00fb\7\60\2\2\u00fb\u00fc\7\60\2\2\u00fc"+
		"\u00fd\7\60\2\2\u00fd.\3\2\2\2\u00fe\u00ff\7}\2\2\u00ff\u0100\b\30\2\2"+
		"\u0100\60\3\2\2\2\u0101\u0102\7\177\2\2\u0102\u0103\b\31\3\2\u0103\62"+
		"\3\2\2\2\u0104\u0105\7>\2\2\u0105\64\3\2\2\2\u0106\u0107\7@\2\2\u0107"+
		"\66\3\2\2\2\u0108\u0109\7<\2\2\u01098\3\2\2\2\u010a\u010b\7.\2\2\u010b"+
		":\3\2\2\2\u010c\u010d\7\60\2\2\u010d<\3\2\2\2\u010e\u010f\7?\2\2\u010f"+
		">\3\2\2\2\u0110\u0111\7)\2\2\u0111@\3\2\2\2\u0112\u0114\7=\2\2\u0113\u0112"+
		"\3\2\2\2\u0114\u0115\3\2\2\2\u0115\u0113\3\2\2\2\u0115\u0116\3\2\2\2\u0116"+
		"\u0117\3\2\2\2\u0117\u0118\b!\4\2\u0118B\3\2\2\2\u0119\u011a\7,\2\2\u011a"+
		"D\3\2\2\2\u011b\u011c\7-\2\2\u011cF\3\2\2\2\u011d\u011e\7/\2\2\u011eH"+
		"\3\2\2\2\u011f\u0121\5G$\2\u0120\u0122\5G$\2\u0121\u0120\3\2\2\2\u0122"+
		"\u0123\3\2\2\2\u0123\u0121\3\2\2\2\u0123\u0124\3\2\2\2\u0124J\3\2\2\2"+
		"\u0125\u0126\7x\2\2\u0126\u0127\7c\2\2\u0127\u0128\7t\2\2\u0128L\3\2\2"+
		"\2\u0129\u012a\7y\2\2\u012a\u012b\7q\2\2\u012b\u012c\7t\2\2\u012c\u012d"+
		"\7f\2\2\u012dN\3\2\2\2\u012e\u012f\7t\2\2\u012f\u0130\7g\2\2\u0130\u0131"+
		"\7u\2\2\u0131\u0132\7q\2\2\u0132\u0133\7w\2\2\u0133\u0134\7t\2\2\u0134"+
		"\u0135\7e\2\2\u0135\u0136\7g\2\2\u0136P\3\2\2\2\u0137\u0138\7c\2\2\u0138"+
		"\u0139\7n\2\2\u0139\u013a\7k\2\2\u013a\u013b\7c\2\2\u013b\u013c\7u\2\2"+
		"\u013cR\3\2\2\2\u013d\u013e\7k\2\2\u013e\u013f\7p\2\2\u013f\u0140\7v\2"+
		"\2\u0140\u0141\7g\2\2\u0141\u0142\7i\2\2\u0142\u0143\7g\2\2\u0143\u0144"+
		"\7t\2\2\u0144T\3\2\2\2\u0145\u0146\7p\2\2\u0146\u0147\7c\2\2\u0147\u0148"+
		"\7v\2\2\u0148\u0149\7w\2\2\u0149\u014a\7t\2\2\u014a\u014b\7c\2\2\u014b"+
		"\u014c\7n\2\2\u014cV\3\2\2\2\u014d\u014e\7f\2\2\u014e\u014f\7q\2\2\u014f"+
		"\u0150\7w\2\2\u0150\u0151\7d\2\2\u0151\u0152\7n\2\2\u0152\u0153\7g\2\2"+
		"\u0153X\3\2\2\2\u0154\u0155\7u\2\2\u0155\u0156\7v\2\2\u0156\u0157\7t\2"+
		"\2\u0157\u0158\7k\2\2\u0158\u0159\7p\2\2\u0159\u015a\7i\2\2\u015aZ\3\2"+
		"\2\2\u015b\u015c\7d\2\2\u015c\u015d\7q\2\2\u015d\u015e\7q\2\2\u015e\u015f"+
		"\7n\2\2\u015f\u0160\7g\2\2\u0160\u0161\7c\2\2\u0161\u0162\7p\2\2\u0162"+
		"\\\3\2\2\2\u0163\u0164\7f\2\2\u0164\u0165\7c\2\2\u0165\u0166\7v\2\2\u0166"+
		"\u0167\7g\2\2\u0167^\3\2\2\2\u0168\u0169\7g\2\2\u0169\u016a\7o\2\2\u016a"+
		"\u016b\7r\2\2\u016b\u016c\7v\2\2\u016c\u016d\7{\2\2\u016d`\3\2\2\2\u016e"+
		"\u016f\7v\2\2\u016f\u0170\7t\2\2\u0170\u0171\7w\2\2\u0171\u0178\7g\2\2"+
		"\u0172\u0173\7h\2\2\u0173\u0174\7c\2\2\u0174\u0175\7n\2\2\u0175\u0176"+
		"\7u\2\2\u0176\u0178\7g\2\2\u0177\u016e\3\2\2\2\u0177\u0172\3\2\2\2\u0178"+
		"b\3\2\2\2\u0179\u017b\5E#\2\u017a\u0179\3\2\2\2\u017a\u017b\3\2\2\2\u017b"+
		"\u017d\3\2\2\2\u017c\u017e\5o8\2\u017d\u017c\3\2\2\2\u017e\u017f\3\2\2"+
		"\2\u017f\u017d\3\2\2\2\u017f\u0180\3\2\2\2\u0180d\3\2\2\2\u0181\u0183"+
		"\5G$\2\u0182\u0184\5o8\2\u0183\u0182\3\2\2\2\u0184\u0185\3\2\2\2\u0185"+
		"\u0183\3\2\2\2\u0185\u0186\3\2\2\2\u0186f\3\2\2\2\u0187\u018a\5E#\2\u0188"+
		"\u018a\5G$\2\u0189\u0187\3\2\2\2\u0189\u0188\3\2\2\2\u0189\u018a\3\2\2"+
		"\2\u018a\u018c\3\2\2\2\u018b\u018d\5o8\2\u018c\u018b\3\2\2\2\u018d\u018e"+
		"\3\2\2\2\u018e\u018c\3\2\2\2\u018e\u018f\3\2\2\2\u018f\u0190\3\2\2\2\u0190"+
		"\u0192\5;\36\2\u0191\u0193\5o8\2\u0192\u0191\3\2\2\2\u0193\u0194\3\2\2"+
		"\2\u0194\u0192\3\2\2\2\u0194\u0195\3\2\2\2\u0195h\3\2\2\2\u0196\u019a"+
		"\5? \2\u0197\u0199\n\2\2\2\u0198\u0197\3\2\2\2\u0199\u019c\3\2\2\2\u019a"+
		"\u0198\3\2\2\2\u019a\u019b\3\2\2\2\u019b\u019d\3\2\2\2\u019c\u019a\3\2"+
		"\2\2\u019d\u019e\5? \2\u019ej\3\2\2\2\u019f\u01a3\5I%\2\u01a0\u01a2\n"+
		"\3\2\2\u01a1\u01a0\3\2\2\2\u01a2\u01a5\3\2\2\2\u01a3\u01a1\3\2\2\2\u01a3"+
		"\u01a4\3\2\2\2\u01a4\u01a6\3\2\2\2\u01a5\u01a3\3\2\2\2\u01a6\u01a7\5I"+
		"%\2\u01a7l\3\2\2\2\u01a8\u01ad\5q9\2\u01a9\u01ac\5o8\2\u01aa\u01ac\5q"+
		"9\2\u01ab\u01a9\3\2\2\2\u01ab\u01aa\3\2\2\2\u01ac\u01af\3\2\2\2\u01ad"+
		"\u01ab\3\2\2\2\u01ad\u01ae\3\2\2\2\u01aen\3\2\2\2\u01af\u01ad\3\2\2\2"+
		"\u01b0\u01b1\t\4\2\2\u01b1p\3\2\2\2\u01b2\u01b3\t\5\2\2\u01b3r\3\2\2\2"+
		"\u01b4\u01b6\5{>\2\u01b5\u01b4\3\2\2\2\u01b6\u01b7\3\2\2\2\u01b7\u01b5"+
		"\3\2\2\2\u01b7\u01b8\3\2\2\2\u01b8\u01bc\3\2\2\2\u01b9\u01bb\5y=\2\u01ba"+
		"\u01b9\3\2\2\2\u01bb\u01be\3\2\2\2\u01bc\u01ba\3\2\2\2\u01bc\u01bd\3\2"+
		"\2\2\u01bd\u01bf\3\2\2\2\u01be\u01bc\3\2\2\2\u01bf\u01c0\b:\5\2\u01c0"+
		"t\3\2\2\2\u01c1\u01c3\5y=\2\u01c2\u01c1\3\2\2\2\u01c3\u01c4\3\2\2\2\u01c4"+
		"\u01c2\3\2\2\2\u01c4\u01c5\3\2\2\2\u01c5\u01c7\3\2\2\2\u01c6\u01c8\7\2"+
		"\2\3\u01c7\u01c6\3\2\2\2\u01c7\u01c8\3\2\2\2\u01c8\u01c9\3\2\2\2\u01c9"+
		"\u01ca\b;\6\2\u01cav\3\2\2\2\u01cb\u01cf\7%\2\2\u01cc\u01ce\13\2\2\2\u01cd"+
		"\u01cc\3\2\2\2\u01ce\u01d1\3\2\2\2\u01cf\u01d0\3\2\2\2\u01cf\u01cd\3\2"+
		"\2\2\u01d0\u01d2\3\2\2\2\u01d1\u01cf\3\2\2\2\u01d2\u01d3\5{>\2\u01d3x"+
		"\3\2\2\2\u01d4\u01d5\t\6\2\2\u01d5z\3\2\2\2\u01d6\u01d8\7\17\2\2\u01d7"+
		"\u01d6\3\2\2\2\u01d7\u01d8\3\2\2\2\u01d8\u01d9\3\2\2\2\u01d9\u01dc\7\f"+
		"\2\2\u01da\u01dc\7\f\2\2\u01db\u01d7\3\2\2\2\u01db\u01da\3\2\2\2\u01dc"+
		"|\3\2\2\2\u01dd\u01de\7k\2\2\u01de\u01df\7p\2\2\u01df\u01e0\7f\2\2\u01e0"+
		"\u01e1\7g\2\2\u01e1\u01e2\7p\2\2\u01e2\u01e3\7v\2\2\u01e3~\3\2\2\2\u01e4"+
		"\u01e5\7f\2\2\u01e5\u01e6\7g\2\2\u01e6\u01e7\7f\2\2\u01e7\u01e8\7g\2\2"+
		"\u01e8\u01e9\7p\2\2\u01e9\u01ea\7v\2\2\u01ea\u0080\3\2\2\2\u01eb\u01ec"+
		"\13\2\2\2\u01ec\u0082\3\2\2\2\27\2\u0115\u0123\u0177\u017a\u017f\u0185"+
		"\u0189\u018e\u0194\u019a\u01a3\u01ab\u01ad\u01b7\u01bc\u01c4\u01c7\u01cf"+
		"\u01d7\u01db\7\3\30\2\3\31\3\3!\4\3:\5\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}