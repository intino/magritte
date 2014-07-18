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
		METAIDENTIFIER=1, INTENTION=2, CASE=3, USE=4, BOX=5, METAMODEL=6, AS=7, 
		ON=8, IS=9, WITH=10, IF=11, PRIVATE=12, SINGLE=13, REQUIRED=14, ROOT=15, 
		TERMINAL=16, NAMED=17, PROPERTY=18, LEFT_PARENTHESIS=19, RIGHT_PARENTHESIS=20, 
		LEFT_SQUARE=21, RIGHT_SQUARE=22, LIST=23, OPEN_BRACKET=24, CLOSE_BRACKET=25, 
		AMPERSAND=26, DOLLAR=27, EURO=28, PERCENTAGE=29, GRADE=30, COLON=31, COMMA=32, 
		DOT=33, EQUALS=34, APHOSTROPHE=35, SEMICOLON=36, STAR=37, POSITIVE=38, 
		DASH=39, DASHES=40, UNDERDASH=41, VAR=42, WORD=43, RESOURCE=44, REFERENCE_TYPE=45, 
		COORDINATE_TYPE=46, INT_TYPE=47, NATURAL_TYPE=48, DOUBLE_TYPE=49, STRING_TYPE=50, 
		BOOLEAN_TYPE=51, DATE_TYPE=52, EMPTY=53, BOOLEAN_VALUE=54, NATURAL_VALUE=55, 
		NEGATIVE_VALUE=56, DOUBLE_VALUE=57, STRING_VALUE=58, STRING_MULTILINE_VALUE_KEY=59, 
		CODE_VALUE=60, DATE_VALUE=61, COORDINATE_VALUE=62, IDENTIFIER=63, DIGIT=64, 
		LETTER=65, NEWLINE=66, SPACES=67, DOC=68, SP=69, NL=70, NEW_LINE_INDENT=71, 
		DEDENT=72, UNKNOWN_TOKEN=73;
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
		"'<'", "'='", "'>'", "'?'", "'@'", "'A'", "'B'", "'C'", "'D'", "'E'", 
		"'F'", "'G'", "'H'", "'I'"
	};
	public static final String[] ruleNames = {
		"METAIDENTIFIER", "INTENTION", "CASE", "USE", "BOX", "METAMODEL", "AS", 
		"ON", "IS", "WITH", "IF", "PRIVATE", "SINGLE", "REQUIRED", "ROOT", "TERMINAL", 
		"NAMED", "PROPERTY", "LEFT_PARENTHESIS", "RIGHT_PARENTHESIS", "LEFT_SQUARE", 
		"RIGHT_SQUARE", "LIST", "OPEN_BRACKET", "CLOSE_BRACKET", "AMPERSAND", 
		"DOLLAR", "EURO", "PERCENTAGE", "GRADE", "COLON", "COMMA", "DOT", "EQUALS", 
		"APHOSTROPHE", "SEMICOLON", "STAR", "POSITIVE", "DASH", "DASHES", "UNDERDASH", 
		"VAR", "WORD", "RESOURCE", "REFERENCE_TYPE", "COORDINATE_TYPE", "INT_TYPE", 
		"NATURAL_TYPE", "DOUBLE_TYPE", "STRING_TYPE", "BOOLEAN_TYPE", "DATE_TYPE", 
		"EMPTY", "BOOLEAN_VALUE", "NATURAL_VALUE", "NEGATIVE_VALUE", "DOUBLE_VALUE", 
		"STRING_VALUE", "STRING_MULTILINE_VALUE_KEY", "CODE_VALUE", "DATE_VALUE", 
		"COORDINATE_VALUE", "IDENTIFIER", "DIGIT", "LETTER", "NEWLINE", "SPACES", 
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
		case 23: OPEN_BRACKET_action((RuleContext)_localctx, actionIndex); break;
		case 24: CLOSE_BRACKET_action((RuleContext)_localctx, actionIndex); break;
		case 35: SEMICOLON_action((RuleContext)_localctx, actionIndex); break;
		case 65: NEWLINE_action((RuleContext)_localctx, actionIndex); break;
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2K\u0237\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
		"\tI\4J\tJ\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n"+
		"\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\30"+
		"\3\30\3\31\3\31\3\31\3\32\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36"+
		"\3\36\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3#\3#\3$\3$\3%\6%\u012c\n%\r%\16%"+
		"\u012d\3%\3%\3&\3&\3\'\3\'\3(\3(\3)\3)\6)\u013a\n)\r)\16)\u013b\3*\3*"+
		"\3+\3+\3+\3+\3,\3,\3,\3,\3,\3-\3-\3-\3-\3-\3-\3-\3-\3-\3.\3.\3.\3.\3."+
		"\3.\3.\3.\3.\3.\3/\3/\3/\3/\3/\3/\3/\3/\3/\3/\3/\3\60\3\60\3\60\3\60\3"+
		"\60\3\60\3\60\3\60\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\62\3\62\3"+
		"\62\3\62\3\62\3\62\3\62\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\64\3\64\3"+
		"\64\3\64\3\64\3\64\3\64\3\64\3\65\3\65\3\65\3\65\3\65\3\66\3\66\3\66\3"+
		"\66\3\66\3\66\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\5\67\u01a1"+
		"\n\67\38\58\u01a4\n8\38\68\u01a7\n8\r8\168\u01a8\39\39\69\u01ad\n9\r9"+
		"\169\u01ae\3:\3:\5:\u01b3\n:\3:\6:\u01b6\n:\r:\16:\u01b7\3:\3:\6:\u01bc"+
		"\n:\r:\16:\u01bd\3;\3;\7;\u01c2\n;\f;\16;\u01c5\13;\3;\3;\3<\3<\7<\u01cb"+
		"\n<\f<\16<\u01ce\13<\3<\3<\3=\3=\3=\6=\u01d5\n=\r=\16=\u01d6\3>\3>\3>"+
		"\6>\u01dc\n>\r>\16>\u01dd\3>\3>\3>\5>\u01e3\n>\3?\3?\3?\6?\u01e8\n?\r"+
		"?\16?\u01e9\3?\3?\3?\5?\u01ef\n?\3@\3@\3@\3@\3@\7@\u01f6\n@\f@\16@\u01f9"+
		"\13@\3A\3A\3B\3B\3C\6C\u0200\nC\rC\16C\u0201\3C\7C\u0205\nC\fC\16C\u0208"+
		"\13C\3C\3C\3D\6D\u020d\nD\rD\16D\u020e\3D\5D\u0212\nD\3D\3D\3E\3E\7E\u0218"+
		"\nE\fE\16E\u021b\13E\3E\3E\3F\3F\3G\5G\u0222\nG\3G\3G\5G\u0226\nG\3H\3"+
		"H\3H\3H\3H\3H\3H\3I\3I\3I\3I\3I\3I\3I\3J\3J\3\u0219\2K\3\3\5\4\7\5\t\6"+
		"\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24"+
		"\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K"+
		"\'M(O)Q*S+U,W-Y.[/]\60_\61a\62c\63e\64g\65i\66k\67m8o9q:s;u<w=y>{?}@\177"+
		"A\u0081B\u0083C\u0085D\u0087E\u0089F\u008bG\u008dH\u008fI\u0091J\u0093"+
		"K\3\2\7\3\2))\3\2//\3\2\62;\4\2C\\c|\4\2\13\13\"\"\u0253\2\3\3\2\2\2\2"+
		"\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2"+
		"\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2"+
		"\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2"+
		"\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2"+
		"\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2"+
		"\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2"+
		"K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3"+
		"\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2"+
		"\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2"+
		"q\3\2\2\2\2s\3\2\2\2\2u\3\2\2\2\2w\3\2\2\2\2y\3\2\2\2\2{\3\2\2\2\2}\3"+
		"\2\2\2\2\177\3\2\2\2\2\u0081\3\2\2\2\2\u0083\3\2\2\2\2\u0085\3\2\2\2\2"+
		"\u0087\3\2\2\2\2\u0089\3\2\2\2\2\u008b\3\2\2\2\2\u008d\3\2\2\2\2\u008f"+
		"\3\2\2\2\2\u0091\3\2\2\2\2\u0093\3\2\2\2\3\u0095\3\2\2\2\5\u009d\3\2\2"+
		"\2\7\u00a7\3\2\2\2\t\u00ac\3\2\2\2\13\u00b0\3\2\2\2\r\u00b4\3\2\2\2\17"+
		"\u00be\3\2\2\2\21\u00c1\3\2\2\2\23\u00c4\3\2\2\2\25\u00c7\3\2\2\2\27\u00cc"+
		"\3\2\2\2\31\u00cf\3\2\2\2\33\u00d7\3\2\2\2\35\u00de\3\2\2\2\37\u00e7\3"+
		"\2\2\2!\u00ec\3\2\2\2#\u00f5\3\2\2\2%\u00fb\3\2\2\2\'\u0104\3\2\2\2)\u0106"+
		"\3\2\2\2+\u0108\3\2\2\2-\u010a\3\2\2\2/\u010c\3\2\2\2\61\u0110\3\2\2\2"+
		"\63\u0113\3\2\2\2\65\u0116\3\2\2\2\67\u0118\3\2\2\29\u011a\3\2\2\2;\u011c"+
		"\3\2\2\2=\u011e\3\2\2\2?\u0120\3\2\2\2A\u0122\3\2\2\2C\u0124\3\2\2\2E"+
		"\u0126\3\2\2\2G\u0128\3\2\2\2I\u012b\3\2\2\2K\u0131\3\2\2\2M\u0133\3\2"+
		"\2\2O\u0135\3\2\2\2Q\u0137\3\2\2\2S\u013d\3\2\2\2U\u013f\3\2\2\2W\u0143"+
		"\3\2\2\2Y\u0148\3\2\2\2[\u0151\3\2\2\2]\u015b\3\2\2\2_\u0166\3\2\2\2a"+
		"\u016e\3\2\2\2c\u0176\3\2\2\2e\u017d\3\2\2\2g\u0184\3\2\2\2i\u018c\3\2"+
		"\2\2k\u0191\3\2\2\2m\u01a0\3\2\2\2o\u01a3\3\2\2\2q\u01aa\3\2\2\2s\u01b2"+
		"\3\2\2\2u\u01bf\3\2\2\2w\u01c8\3\2\2\2y\u01d1\3\2\2\2{\u01e2\3\2\2\2}"+
		"\u01ee\3\2\2\2\177\u01f0\3\2\2\2\u0081\u01fa\3\2\2\2\u0083\u01fc\3\2\2"+
		"\2\u0085\u01ff\3\2\2\2\u0087\u020c\3\2\2\2\u0089\u0215\3\2\2\2\u008b\u021e"+
		"\3\2\2\2\u008d\u0225\3\2\2\2\u008f\u0227\3\2\2\2\u0091\u022e\3\2\2\2\u0093"+
		"\u0235\3\2\2\2\u0095\u0096\7E\2\2\u0096\u0097\7q\2\2\u0097\u0098\7p\2"+
		"\2\u0098\u0099\7e\2\2\u0099\u009a\7g\2\2\u009a\u009b\7r\2\2\u009b\u009c"+
		"\7v\2\2\u009c\4\3\2\2\2\u009d\u009e\7K\2\2\u009e\u009f\7p\2\2\u009f\u00a0"+
		"\7v\2\2\u00a0\u00a1\7g\2\2\u00a1\u00a2\7p\2\2\u00a2\u00a3\7v\2\2\u00a3"+
		"\u00a4\7k\2\2\u00a4\u00a5\7q\2\2\u00a5\u00a6\7p\2\2\u00a6\6\3\2\2\2\u00a7"+
		"\u00a8\7e\2\2\u00a8\u00a9\7c\2\2\u00a9\u00aa\7u\2\2\u00aa\u00ab\7g\2\2"+
		"\u00ab\b\3\2\2\2\u00ac\u00ad\7w\2\2\u00ad\u00ae\7u\2\2\u00ae\u00af\7g"+
		"\2\2\u00af\n\3\2\2\2\u00b0\u00b1\7d\2\2\u00b1\u00b2\7q\2\2\u00b2\u00b3"+
		"\7z\2\2\u00b3\f\3\2\2\2\u00b4\u00b5\7o\2\2\u00b5\u00b6\7g\2\2\u00b6\u00b7"+
		"\7v\2\2\u00b7\u00b8\7c\2\2\u00b8\u00b9\7o\2\2\u00b9\u00ba\7q\2\2\u00ba"+
		"\u00bb\7f\2\2\u00bb\u00bc\7g\2\2\u00bc\u00bd\7n\2\2\u00bd\16\3\2\2\2\u00be"+
		"\u00bf\7c\2\2\u00bf\u00c0\7u\2\2\u00c0\20\3\2\2\2\u00c1\u00c2\7q\2\2\u00c2"+
		"\u00c3\7p\2\2\u00c3\22\3\2\2\2\u00c4\u00c5\7k\2\2\u00c5\u00c6\7u\2\2\u00c6"+
		"\24\3\2\2\2\u00c7\u00c8\7y\2\2\u00c8\u00c9\7k\2\2\u00c9\u00ca\7v\2\2\u00ca"+
		"\u00cb\7j\2\2\u00cb\26\3\2\2\2\u00cc\u00cd\7k\2\2\u00cd\u00ce\7h\2\2\u00ce"+
		"\30\3\2\2\2\u00cf\u00d0\7r\2\2\u00d0\u00d1\7t\2\2\u00d1\u00d2\7k\2\2\u00d2"+
		"\u00d3\7x\2\2\u00d3\u00d4\7c\2\2\u00d4\u00d5\7v\2\2\u00d5\u00d6\7g\2\2"+
		"\u00d6\32\3\2\2\2\u00d7\u00d8\7u\2\2\u00d8\u00d9\7k\2\2\u00d9\u00da\7"+
		"p\2\2\u00da\u00db\7i\2\2\u00db\u00dc\7n\2\2\u00dc\u00dd\7g\2\2\u00dd\34"+
		"\3\2\2\2\u00de\u00df\7t\2\2\u00df\u00e0\7g\2\2\u00e0\u00e1\7s\2\2\u00e1"+
		"\u00e2\7w\2\2\u00e2\u00e3\7k\2\2\u00e3\u00e4\7t\2\2\u00e4\u00e5\7g\2\2"+
		"\u00e5\u00e6\7f\2\2\u00e6\36\3\2\2\2\u00e7\u00e8\7t\2\2\u00e8\u00e9\7"+
		"q\2\2\u00e9\u00ea\7q\2\2\u00ea\u00eb\7v\2\2\u00eb \3\2\2\2\u00ec\u00ed"+
		"\7v\2\2\u00ed\u00ee\7g\2\2\u00ee\u00ef\7t\2\2\u00ef\u00f0\7o\2\2\u00f0"+
		"\u00f1\7k\2\2\u00f1\u00f2\7p\2\2\u00f2\u00f3\7c\2\2\u00f3\u00f4\7n\2\2"+
		"\u00f4\"\3\2\2\2\u00f5\u00f6\7p\2\2\u00f6\u00f7\7c\2\2\u00f7\u00f8\7o"+
		"\2\2\u00f8\u00f9\7g\2\2\u00f9\u00fa\7f\2\2\u00fa$\3\2\2\2\u00fb\u00fc"+
		"\7r\2\2\u00fc\u00fd\7t\2\2\u00fd\u00fe\7q\2\2\u00fe\u00ff\7r\2\2\u00ff"+
		"\u0100\7g\2\2\u0100\u0101\7t\2\2\u0101\u0102\7v\2\2\u0102\u0103\7{\2\2"+
		"\u0103&\3\2\2\2\u0104\u0105\7*\2\2\u0105(\3\2\2\2\u0106\u0107\7+\2\2\u0107"+
		"*\3\2\2\2\u0108\u0109\7]\2\2\u0109,\3\2\2\2\u010a\u010b\7_\2\2\u010b."+
		"\3\2\2\2\u010c\u010d\7\60\2\2\u010d\u010e\7\60\2\2\u010e\u010f\7\60\2"+
		"\2\u010f\60\3\2\2\2\u0110\u0111\7}\2\2\u0111\u0112\b\31\2\2\u0112\62\3"+
		"\2\2\2\u0113\u0114\7\177\2\2\u0114\u0115\b\32\3\2\u0115\64\3\2\2\2\u0116"+
		"\u0117\7(\2\2\u0117\66\3\2\2\2\u0118\u0119\7&\2\2\u01198\3\2\2\2\u011a"+
		"\u011b\7\u20ae\2\2\u011b:\3\2\2\2\u011c\u011d\7\'\2\2\u011d<\3\2\2\2\u011e"+
		"\u011f\7\u00bc\2\2\u011f>\3\2\2\2\u0120\u0121\7<\2\2\u0121@\3\2\2\2\u0122"+
		"\u0123\7.\2\2\u0123B\3\2\2\2\u0124\u0125\7\60\2\2\u0125D\3\2\2\2\u0126"+
		"\u0127\7?\2\2\u0127F\3\2\2\2\u0128\u0129\7)\2\2\u0129H\3\2\2\2\u012a\u012c"+
		"\7=\2\2\u012b\u012a\3\2\2\2\u012c\u012d\3\2\2\2\u012d\u012b\3\2\2\2\u012d"+
		"\u012e\3\2\2\2\u012e\u012f\3\2\2\2\u012f\u0130\b%\4\2\u0130J\3\2\2\2\u0131"+
		"\u0132\7,\2\2\u0132L\3\2\2\2\u0133\u0134\7-\2\2\u0134N\3\2\2\2\u0135\u0136"+
		"\7/\2\2\u0136P\3\2\2\2\u0137\u0139\5O(\2\u0138\u013a\5O(\2\u0139\u0138"+
		"\3\2\2\2\u013a\u013b\3\2\2\2\u013b\u0139\3\2\2\2\u013b\u013c\3\2\2\2\u013c"+
		"R\3\2\2\2\u013d\u013e\7a\2\2\u013eT\3\2\2\2\u013f\u0140\7x\2\2\u0140\u0141"+
		"\7c\2\2\u0141\u0142\7t\2\2\u0142V\3\2\2\2\u0143\u0144\7y\2\2\u0144\u0145"+
		"\7q\2\2\u0145\u0146\7t\2\2\u0146\u0147\7f\2\2\u0147X\3\2\2\2\u0148\u0149"+
		"\7t\2\2\u0149\u014a\7g\2\2\u014a\u014b\7u\2\2\u014b\u014c\7q\2\2\u014c"+
		"\u014d\7w\2\2\u014d\u014e\7t\2\2\u014e\u014f\7e\2\2\u014f\u0150\7g\2\2"+
		"\u0150Z\3\2\2\2\u0151\u0152\7t\2\2\u0152\u0153\7g\2\2\u0153\u0154\7h\2"+
		"\2\u0154\u0155\7g\2\2\u0155\u0156\7t\2\2\u0156\u0157\7g\2\2\u0157\u0158"+
		"\7p\2\2\u0158\u0159\7e\2\2\u0159\u015a\7g\2\2\u015a\\\3\2\2\2\u015b\u015c"+
		"\7e\2\2\u015c\u015d\7q\2\2\u015d\u015e\7q\2\2\u015e\u015f\7t\2\2\u015f"+
		"\u0160\7f\2\2\u0160\u0161\7k\2\2\u0161\u0162\7p\2\2\u0162\u0163\7c\2\2"+
		"\u0163\u0164\7v\2\2\u0164\u0165\7g\2\2\u0165^\3\2\2\2\u0166\u0167\7k\2"+
		"\2\u0167\u0168\7p\2\2\u0168\u0169\7v\2\2\u0169\u016a\7g\2\2\u016a\u016b"+
		"\7i\2\2\u016b\u016c\7g\2\2\u016c\u016d\7t\2\2\u016d`\3\2\2\2\u016e\u016f"+
		"\7p\2\2\u016f\u0170\7c\2\2\u0170\u0171\7v\2\2\u0171\u0172\7w\2\2\u0172"+
		"\u0173\7t\2\2\u0173\u0174\7c\2\2\u0174\u0175\7n\2\2\u0175b\3\2\2\2\u0176"+
		"\u0177\7f\2\2\u0177\u0178\7q\2\2\u0178\u0179\7w\2\2\u0179\u017a\7d\2\2"+
		"\u017a\u017b\7n\2\2\u017b\u017c\7g\2\2\u017cd\3\2\2\2\u017d\u017e\7u\2"+
		"\2\u017e\u017f\7v\2\2\u017f\u0180\7t\2\2\u0180\u0181\7k\2\2\u0181\u0182"+
		"\7p\2\2\u0182\u0183\7i\2\2\u0183f\3\2\2\2\u0184\u0185\7d\2\2\u0185\u0186"+
		"\7q\2\2\u0186\u0187\7q\2\2\u0187\u0188\7n\2\2\u0188\u0189\7g\2\2\u0189"+
		"\u018a\7c\2\2\u018a\u018b\7p\2\2\u018bh\3\2\2\2\u018c\u018d\7f\2\2\u018d"+
		"\u018e\7c\2\2\u018e\u018f\7v\2\2\u018f\u0190\7g\2\2\u0190j\3\2\2\2\u0191"+
		"\u0192\7g\2\2\u0192\u0193\7o\2\2\u0193\u0194\7r\2\2\u0194\u0195\7v\2\2"+
		"\u0195\u0196\7{\2\2\u0196l\3\2\2\2\u0197\u0198\7v\2\2\u0198\u0199\7t\2"+
		"\2\u0199\u019a\7w\2\2\u019a\u01a1\7g\2\2\u019b\u019c\7h\2\2\u019c\u019d"+
		"\7c\2\2\u019d\u019e\7n\2\2\u019e\u019f\7u\2\2\u019f\u01a1\7g\2\2\u01a0"+
		"\u0197\3\2\2\2\u01a0\u019b\3\2\2\2\u01a1n\3\2\2\2\u01a2\u01a4\5M\'\2\u01a3"+
		"\u01a2\3\2\2\2\u01a3\u01a4\3\2\2\2\u01a4\u01a6\3\2\2\2\u01a5\u01a7\5\u0081"+
		"A\2\u01a6\u01a5\3\2\2\2\u01a7\u01a8\3\2\2\2\u01a8\u01a6\3\2\2\2\u01a8"+
		"\u01a9\3\2\2\2\u01a9p\3\2\2\2\u01aa\u01ac\5O(\2\u01ab\u01ad\5\u0081A\2"+
		"\u01ac\u01ab\3\2\2\2\u01ad\u01ae\3\2\2\2\u01ae\u01ac\3\2\2\2\u01ae\u01af"+
		"\3\2\2\2\u01afr\3\2\2\2\u01b0\u01b3\5M\'\2\u01b1\u01b3\5O(\2\u01b2\u01b0"+
		"\3\2\2\2\u01b2\u01b1\3\2\2\2\u01b2\u01b3\3\2\2\2\u01b3\u01b5\3\2\2\2\u01b4"+
		"\u01b6\5\u0081A\2\u01b5\u01b4\3\2\2\2\u01b6\u01b7\3\2\2\2\u01b7\u01b5"+
		"\3\2\2\2\u01b7\u01b8\3\2\2\2\u01b8\u01b9\3\2\2\2\u01b9\u01bb\5C\"\2\u01ba"+
		"\u01bc\5\u0081A\2\u01bb\u01ba\3\2\2\2\u01bc\u01bd\3\2\2\2\u01bd\u01bb"+
		"\3\2\2\2\u01bd\u01be\3\2\2\2\u01bet\3\2\2\2\u01bf\u01c3\5G$\2\u01c0\u01c2"+
		"\n\2\2\2\u01c1\u01c0\3\2\2\2\u01c2\u01c5\3\2\2\2\u01c3\u01c1\3\2\2\2\u01c3"+
		"\u01c4\3\2\2\2\u01c4\u01c6\3\2\2\2\u01c5\u01c3\3\2\2\2\u01c6\u01c7\5G"+
		"$\2\u01c7v\3\2\2\2\u01c8\u01cc\5Q)\2\u01c9\u01cb\n\3\2\2\u01ca\u01c9\3"+
		"\2\2\2\u01cb\u01ce\3\2\2\2\u01cc\u01ca\3\2\2\2\u01cc\u01cd\3\2\2\2\u01cd"+
		"\u01cf\3\2\2\2\u01ce\u01cc\3\2\2\2\u01cf\u01d0\5Q)\2\u01d0x\3\2\2\2\u01d1"+
		"\u01d4\5\65\33\2\u01d2\u01d5\5\u0081A\2\u01d3\u01d5\5\u0083B\2\u01d4\u01d2"+
		"\3\2\2\2\u01d4\u01d3\3\2\2\2\u01d5\u01d6\3\2\2\2\u01d6\u01d4\3\2\2\2\u01d6"+
		"\u01d7\3\2\2\2\u01d7z\3\2\2\2\u01d8\u01d9\5o8\2\u01d9\u01da\5O(\2\u01da"+
		"\u01dc\3\2\2\2\u01db\u01d8\3\2\2\2\u01dc\u01dd\3\2\2\2\u01dd\u01db\3\2"+
		"\2\2\u01dd\u01de\3\2\2\2\u01de\u01df\3\2\2\2\u01df\u01e0\5o8\2\u01e0\u01e3"+
		"\3\2\2\2\u01e1\u01e3\5o8\2\u01e2\u01db\3\2\2\2\u01e2\u01e1\3\2\2\2\u01e3"+
		"|\3\2\2\2\u01e4\u01e5\5s:\2\u01e5\u01e6\5O(\2\u01e6\u01e8\3\2\2\2\u01e7"+
		"\u01e4\3\2\2\2\u01e8\u01e9\3\2\2\2\u01e9\u01e7\3\2\2\2\u01e9\u01ea\3\2"+
		"\2\2\u01ea\u01eb\3\2\2\2\u01eb\u01ec\5s:\2\u01ec\u01ef\3\2\2\2\u01ed\u01ef"+
		"\5s:\2\u01ee\u01e7\3\2\2\2\u01ee\u01ed\3\2\2\2\u01ef~\3\2\2\2\u01f0\u01f7"+
		"\5\u0083B\2\u01f1\u01f6\5\u0081A\2\u01f2\u01f6\5\u0083B\2\u01f3\u01f6"+
		"\5O(\2\u01f4\u01f6\5S*\2\u01f5\u01f1\3\2\2\2\u01f5\u01f2\3\2\2\2\u01f5"+
		"\u01f3\3\2\2\2\u01f5\u01f4\3\2\2\2\u01f6\u01f9\3\2\2\2\u01f7\u01f5\3\2"+
		"\2\2\u01f7\u01f8\3\2\2\2\u01f8\u0080\3\2\2\2\u01f9\u01f7\3\2\2\2\u01fa"+
		"\u01fb\t\4\2\2\u01fb\u0082\3\2\2\2\u01fc\u01fd\t\5\2\2\u01fd\u0084\3\2"+
		"\2\2\u01fe\u0200\5\u008dG\2\u01ff\u01fe\3\2\2\2\u0200\u0201\3\2\2\2\u0201"+
		"\u01ff\3\2\2\2\u0201\u0202\3\2\2\2\u0202\u0206\3\2\2\2\u0203\u0205\5\u008b"+
		"F\2\u0204\u0203\3\2\2\2\u0205\u0208\3\2\2\2\u0206\u0204\3\2\2\2\u0206"+
		"\u0207\3\2\2\2\u0207\u0209\3\2\2\2\u0208\u0206\3\2\2\2\u0209\u020a\bC"+
		"\5\2\u020a\u0086\3\2\2\2\u020b\u020d\5\u008bF\2\u020c\u020b\3\2\2\2\u020d"+
		"\u020e\3\2\2\2\u020e\u020c\3\2\2\2\u020e\u020f\3\2\2\2\u020f\u0211\3\2"+
		"\2\2\u0210\u0212\7\2\2\3\u0211\u0210\3\2\2\2\u0211\u0212\3\2\2\2\u0212"+
		"\u0213\3\2\2\2\u0213\u0214\bD\6\2\u0214\u0088\3\2\2\2\u0215\u0219\7%\2"+
		"\2\u0216\u0218\13\2\2\2\u0217\u0216\3\2\2\2\u0218\u021b\3\2\2\2\u0219"+
		"\u021a\3\2\2\2\u0219\u0217\3\2\2\2\u021a\u021c\3\2\2\2\u021b\u0219\3\2"+
		"\2\2\u021c\u021d\5\u008dG\2\u021d\u008a\3\2\2\2\u021e\u021f\t\6\2\2\u021f"+
		"\u008c\3\2\2\2\u0220\u0222\7\17\2\2\u0221\u0220\3\2\2\2\u0221\u0222\3"+
		"\2\2\2\u0222\u0223\3\2\2\2\u0223\u0226\7\f\2\2\u0224\u0226\7\f\2\2\u0225"+
		"\u0221\3\2\2\2\u0225\u0224\3\2\2\2\u0226\u008e\3\2\2\2\u0227\u0228\7k"+
		"\2\2\u0228\u0229\7p\2\2\u0229\u022a\7f\2\2\u022a\u022b\7g\2\2\u022b\u022c"+
		"\7p\2\2\u022c\u022d\7v\2\2\u022d\u0090\3\2\2\2\u022e\u022f\7f\2\2\u022f"+
		"\u0230\7g\2\2\u0230\u0231\7f\2\2\u0231\u0232\7g\2\2\u0232\u0233\7p\2\2"+
		"\u0233\u0234\7v\2\2\u0234\u0092\3\2\2\2\u0235\u0236\13\2\2\2\u0236\u0094"+
		"\3\2\2\2\35\2\u012d\u013b\u01a0\u01a3\u01a8\u01ae\u01b2\u01b7\u01bd\u01c3"+
		"\u01cc\u01d4\u01d6\u01dd\u01e2\u01e9\u01ee\u01f5\u01f7\u0201\u0206\u020e"+
		"\u0211\u0219\u0221\u0225\7\3\31\2\3\32\3\3%\4\3C\5\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}