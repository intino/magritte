// Generated from /Users/oroncal/workspace/tara/rt/src/siani/tara/compiler/parser/antlr/TaraLexer.g4 by ANTLR 4.4.1-dev

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
	static { RuntimeMetaData.checkVersion("4.4.1-dev", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		METAIDENTIFIER=1, SUB=2, USE=3, BOX=4, AS=5, HAS=6, ON=7, IS=8, WITH=9, 
		EXTENDS=10, PRIVATE=11, SINGLE=12, REQUIRED=13, COMPONENT=14, FACET=15, 
		INTENTION=16, TERMINAL=17, NAMED=18, PROPERTY=19, UNIVERSAL=20, ALWAYS=21, 
		ADDRESSED=22, AGGREGABLE=23, LEFT_PARENTHESIS=24, RIGHT_PARENTHESIS=25, 
		LEFT_SQUARE=26, RIGHT_SQUARE=27, LIST=28, INLINE=29, CLOSE_INLINE=30, 
		AMPERSAND=31, DOLLAR=32, EURO=33, PERCENTAGE=34, GRADE=35, COLON=36, COMMA=37, 
		DOT=38, EQUALS=39, APHOSTROPHE=40, SEMICOLON=41, STAR=42, POSITIVE=43, 
		DASH=44, DASHES=45, UNDERDASH=46, SLOT=47, LINK_KEY=48, WORD=49, RESOURCE=50, 
		COORDINATE_TYPE=51, INT_TYPE=52, NATURAL_TYPE=53, DOUBLE_TYPE=54, STRING_TYPE=55, 
		BOOLEAN_TYPE=56, DATE_TYPE=57, EMPTY=58, BOOLEAN_VALUE=59, NATURAL_VALUE=60, 
		NEGATIVE_VALUE=61, DOUBLE_VALUE=62, STRING_VALUE=63, STRING_MULTILINE_VALUE_KEY=64, 
		ADDRESS_VALUE=65, DATE_VALUE=66, COORDINATE_VALUE=67, IDENTIFIER=68, DIGIT=69, 
		LETTER=70, NEWLINE=71, SPACES=72, DOC=73, SP=74, NL=75, NEW_LINE_INDENT=76, 
		DEDENT=77, UNKNOWN_TOKEN=78;
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
		"'F'", "'G'", "'H'", "'I'", "'J'", "'K'", "'L'", "'M'", "'N'"
	};
	public static final String[] ruleNames = {
		"METAIDENTIFIER", "SUB", "USE", "BOX", "AS", "HAS", "ON", "IS", "WITH", 
		"EXTENDS", "PRIVATE", "SINGLE", "REQUIRED", "COMPONENT", "FACET", "INTENTION", 
		"TERMINAL", "NAMED", "PROPERTY", "UNIVERSAL", "ALWAYS", "ADDRESSED", "AGGREGABLE", 
		"LEFT_PARENTHESIS", "RIGHT_PARENTHESIS", "LEFT_SQUARE", "RIGHT_SQUARE", 
		"LIST", "INLINE", "CLOSE_INLINE", "AMPERSAND", "DOLLAR", "EURO", "PERCENTAGE", 
		"GRADE", "COLON", "COMMA", "DOT", "EQUALS", "APHOSTROPHE", "SEMICOLON", 
		"STAR", "POSITIVE", "DASH", "DASHES", "UNDERDASH", "SLOT", "LINK_KEY", 
		"WORD", "RESOURCE", "COORDINATE_TYPE", "INT_TYPE", "NATURAL_TYPE", "DOUBLE_TYPE", 
		"STRING_TYPE", "BOOLEAN_TYPE", "DATE_TYPE", "EMPTY", "BOOLEAN_VALUE", 
		"NATURAL_VALUE", "NEGATIVE_VALUE", "DOUBLE_VALUE", "STRING_VALUE", "STRING_MULTILINE_VALUE_KEY", 
		"ADDRESS_VALUE", "DATE_VALUE", "COORDINATE_VALUE", "IDENTIFIER", "DIGIT", 
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

	    private void inline() {
	        blockManager.openBracket(getText().length());
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
		case 28: INLINE_action((RuleContext)_localctx, actionIndex); break;
		case 40: SEMICOLON_action((RuleContext)_localctx, actionIndex); break;
		case 70: NEWLINE_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void SEMICOLON_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1:  semicolon();  break;
		}
	}
	private void INLINE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:  inline();  break;
		}
	}
	private void NEWLINE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 2:  newlinesAndSpaces();  break;
		}
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2P\u0274\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
		"\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\4O\tO\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\7\3\7"+
		"\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30"+
		"\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\35\3\35"+
		"\3\36\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\3&\3&"+
		"\3\'\3\'\3(\3(\3)\3)\3*\6*\u0164\n*\r*\16*\u0165\3*\3*\3+\3+\3,\3,\3-"+
		"\3-\3.\3.\6.\u0172\n.\r.\16.\u0173\3/\3/\3\60\3\60\3\60\3\60\3\60\3\61"+
		"\3\61\3\61\3\61\3\61\3\62\3\62\3\62\3\62\3\62\3\63\3\63\3\63\3\63\3\63"+
		"\3\63\3\63\3\63\3\63\3\64\3\64\3\64\3\64\3\64\3\64\3\64\3\64\3\64\3\64"+
		"\3\64\3\65\3\65\3\65\3\65\3\65\3\65\3\65\3\65\3\66\3\66\3\66\3\66\3\66"+
		"\3\66\3\66\3\66\3\67\3\67\3\67\3\67\3\67\3\67\3\67\38\38\38\38\38\38\3"+
		"8\39\39\39\39\39\39\39\39\3:\3:\3:\3:\3:\3;\3;\3;\3;\3;\3;\3<\3<\3<\3"+
		"<\3<\3<\3<\3<\3<\5<\u01d5\n<\3=\5=\u01d8\n=\3=\6=\u01db\n=\r=\16=\u01dc"+
		"\3>\3>\6>\u01e1\n>\r>\16>\u01e2\3?\3?\5?\u01e7\n?\3?\6?\u01ea\n?\r?\16"+
		"?\u01eb\3?\3?\6?\u01f0\n?\r?\16?\u01f1\3@\3@\7@\u01f6\n@\f@\16@\u01f9"+
		"\13@\3@\3@\3A\3A\7A\u01ff\nA\fA\16A\u0202\13A\3A\3A\3B\3B\3B\3B\3B\3B"+
		"\3B\3B\3B\6B\u020f\nB\rB\16B\u0210\3C\3C\3C\6C\u0216\nC\rC\16C\u0217\3"+
		"C\3C\3C\5C\u021d\nC\3D\3D\3D\5D\u0222\nD\3D\3D\3D\3D\5D\u0228\nD\6D\u022a"+
		"\nD\rD\16D\u022b\3E\3E\3E\3E\3E\7E\u0233\nE\fE\16E\u0236\13E\3F\3F\3G"+
		"\3G\3H\6H\u023d\nH\rH\16H\u023e\3H\7H\u0242\nH\fH\16H\u0245\13H\3H\3H"+
		"\3I\6I\u024a\nI\rI\16I\u024b\3I\5I\u024f\nI\3I\3I\3J\3J\7J\u0255\nJ\f"+
		"J\16J\u0258\13J\3J\3J\3K\3K\3L\5L\u025f\nL\3L\3L\5L\u0263\nL\3M\3M\3M"+
		"\3M\3M\3M\3M\3N\3N\3N\3N\3N\3N\3N\3O\3O\3\u0256\2P\3\3\5\4\7\5\t\6\13"+
		"\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'"+
		"\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'"+
		"M(O)Q*S+U,W-Y.[/]\60_\61a\62c\63e\64g\65i\66k\67m8o9q:s;u<w=y>{?}@\177"+
		"A\u0081B\u0083C\u0085D\u0087E\u0089F\u008bG\u008dH\u008fI\u0091J\u0093"+
		"K\u0095L\u0097M\u0099N\u009bO\u009dP\3\2\7\3\2))\3\2//\3\2\62;\4\2C\\"+
		"c|\4\2\13\13\"\"\u0292\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2"+
		"\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25"+
		"\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2"+
		"\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2"+
		"\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3"+
		"\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2"+
		"\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2"+
		"Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3"+
		"\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2"+
		"\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\2u\3\2\2\2\2"+
		"w\3\2\2\2\2y\3\2\2\2\2{\3\2\2\2\2}\3\2\2\2\2\177\3\2\2\2\2\u0081\3\2\2"+
		"\2\2\u0083\3\2\2\2\2\u0085\3\2\2\2\2\u0087\3\2\2\2\2\u0089\3\2\2\2\2\u008b"+
		"\3\2\2\2\2\u008d\3\2\2\2\2\u008f\3\2\2\2\2\u0091\3\2\2\2\2\u0093\3\2\2"+
		"\2\2\u0095\3\2\2\2\2\u0097\3\2\2\2\2\u0099\3\2\2\2\2\u009b\3\2\2\2\2\u009d"+
		"\3\2\2\2\3\u009f\3\2\2\2\5\u00a7\3\2\2\2\7\u00ab\3\2\2\2\t\u00af\3\2\2"+
		"\2\13\u00b3\3\2\2\2\r\u00b6\3\2\2\2\17\u00ba\3\2\2\2\21\u00bd\3\2\2\2"+
		"\23\u00c0\3\2\2\2\25\u00c5\3\2\2\2\27\u00cd\3\2\2\2\31\u00d5\3\2\2\2\33"+
		"\u00dc\3\2\2\2\35\u00e5\3\2\2\2\37\u00ef\3\2\2\2!\u00f5\3\2\2\2#\u00ff"+
		"\3\2\2\2%\u0108\3\2\2\2\'\u010e\3\2\2\2)\u0117\3\2\2\2+\u0121\3\2\2\2"+
		"-\u0128\3\2\2\2/\u0132\3\2\2\2\61\u013d\3\2\2\2\63\u013f\3\2\2\2\65\u0141"+
		"\3\2\2\2\67\u0143\3\2\2\29\u0145\3\2\2\2;\u0149\3\2\2\2=\u014c\3\2\2\2"+
		"?\u014e\3\2\2\2A\u0150\3\2\2\2C\u0152\3\2\2\2E\u0154\3\2\2\2G\u0156\3"+
		"\2\2\2I\u0158\3\2\2\2K\u015a\3\2\2\2M\u015c\3\2\2\2O\u015e\3\2\2\2Q\u0160"+
		"\3\2\2\2S\u0163\3\2\2\2U\u0169\3\2\2\2W\u016b\3\2\2\2Y\u016d\3\2\2\2["+
		"\u016f\3\2\2\2]\u0175\3\2\2\2_\u0177\3\2\2\2a\u017c\3\2\2\2c\u0181\3\2"+
		"\2\2e\u0186\3\2\2\2g\u018f\3\2\2\2i\u019a\3\2\2\2k\u01a2\3\2\2\2m\u01aa"+
		"\3\2\2\2o\u01b1\3\2\2\2q\u01b8\3\2\2\2s\u01c0\3\2\2\2u\u01c5\3\2\2\2w"+
		"\u01d4\3\2\2\2y\u01d7\3\2\2\2{\u01de\3\2\2\2}\u01e6\3\2\2\2\177\u01f3"+
		"\3\2\2\2\u0081\u01fc\3\2\2\2\u0083\u0205\3\2\2\2\u0085\u021c\3\2\2\2\u0087"+
		"\u0221\3\2\2\2\u0089\u022d\3\2\2\2\u008b\u0237\3\2\2\2\u008d\u0239\3\2"+
		"\2\2\u008f\u023c\3\2\2\2\u0091\u0249\3\2\2\2\u0093\u0252\3\2\2\2\u0095"+
		"\u025b\3\2\2\2\u0097\u0262\3\2\2\2\u0099\u0264\3\2\2\2\u009b\u026b\3\2"+
		"\2\2\u009d\u0272\3\2\2\2\u009f\u00a0\7E\2\2\u00a0\u00a1\7q\2\2\u00a1\u00a2"+
		"\7p\2\2\u00a2\u00a3\7e\2\2\u00a3\u00a4\7g\2\2\u00a4\u00a5\7r\2\2\u00a5"+
		"\u00a6\7v\2\2\u00a6\4\3\2\2\2\u00a7\u00a8\7u\2\2\u00a8\u00a9\7w\2\2\u00a9"+
		"\u00aa\7d\2\2\u00aa\6\3\2\2\2\u00ab\u00ac\7w\2\2\u00ac\u00ad\7u\2\2\u00ad"+
		"\u00ae\7g\2\2\u00ae\b\3\2\2\2\u00af\u00b0\7d\2\2\u00b0\u00b1\7q\2\2\u00b1"+
		"\u00b2\7z\2\2\u00b2\n\3\2\2\2\u00b3\u00b4\7c\2\2\u00b4\u00b5\7u\2\2\u00b5"+
		"\f\3\2\2\2\u00b6\u00b7\7j\2\2\u00b7\u00b8\7c\2\2\u00b8\u00b9\7u\2\2\u00b9"+
		"\16\3\2\2\2\u00ba\u00bb\7q\2\2\u00bb\u00bc\7p\2\2\u00bc\20\3\2\2\2\u00bd"+
		"\u00be\7k\2\2\u00be\u00bf\7u\2\2\u00bf\22\3\2\2\2\u00c0\u00c1\7y\2\2\u00c1"+
		"\u00c2\7k\2\2\u00c2\u00c3\7v\2\2\u00c3\u00c4\7j\2\2\u00c4\24\3\2\2\2\u00c5"+
		"\u00c6\7g\2\2\u00c6\u00c7\7z\2\2\u00c7\u00c8\7v\2\2\u00c8\u00c9\7g\2\2"+
		"\u00c9\u00ca\7p\2\2\u00ca\u00cb\7f\2\2\u00cb\u00cc\7u\2\2\u00cc\26\3\2"+
		"\2\2\u00cd\u00ce\7r\2\2\u00ce\u00cf\7t\2\2\u00cf\u00d0\7k\2\2\u00d0\u00d1"+
		"\7x\2\2\u00d1\u00d2\7c\2\2\u00d2\u00d3\7v\2\2\u00d3\u00d4\7g\2\2\u00d4"+
		"\30\3\2\2\2\u00d5\u00d6\7u\2\2\u00d6\u00d7\7k\2\2\u00d7\u00d8\7p\2\2\u00d8"+
		"\u00d9\7i\2\2\u00d9\u00da\7n\2\2\u00da\u00db\7g\2\2\u00db\32\3\2\2\2\u00dc"+
		"\u00dd\7t\2\2\u00dd\u00de\7g\2\2\u00de\u00df\7s\2\2\u00df\u00e0\7w\2\2"+
		"\u00e0\u00e1\7k\2\2\u00e1\u00e2\7t\2\2\u00e2\u00e3\7g\2\2\u00e3\u00e4"+
		"\7f\2\2\u00e4\34\3\2\2\2\u00e5\u00e6\7e\2\2\u00e6\u00e7\7q\2\2\u00e7\u00e8"+
		"\7o\2\2\u00e8\u00e9\7r\2\2\u00e9\u00ea\7q\2\2\u00ea\u00eb\7p\2\2\u00eb"+
		"\u00ec\7g\2\2\u00ec\u00ed\7p\2\2\u00ed\u00ee\7v\2\2\u00ee\36\3\2\2\2\u00ef"+
		"\u00f0\7h\2\2\u00f0\u00f1\7c\2\2\u00f1\u00f2\7e\2\2\u00f2\u00f3\7g\2\2"+
		"\u00f3\u00f4\7v\2\2\u00f4 \3\2\2\2\u00f5\u00f6\7k\2\2\u00f6\u00f7\7p\2"+
		"\2\u00f7\u00f8\7v\2\2\u00f8\u00f9\7g\2\2\u00f9\u00fa\7p\2\2\u00fa\u00fb"+
		"\7v\2\2\u00fb\u00fc\7k\2\2\u00fc\u00fd\7q\2\2\u00fd\u00fe\7p\2\2\u00fe"+
		"\"\3\2\2\2\u00ff\u0100\7v\2\2\u0100\u0101\7g\2\2\u0101\u0102\7t\2\2\u0102"+
		"\u0103\7o\2\2\u0103\u0104\7k\2\2\u0104\u0105\7p\2\2\u0105\u0106\7c\2\2"+
		"\u0106\u0107\7n\2\2\u0107$\3\2\2\2\u0108\u0109\7p\2\2\u0109\u010a\7c\2"+
		"\2\u010a\u010b\7o\2\2\u010b\u010c\7g\2\2\u010c\u010d\7f\2\2\u010d&\3\2"+
		"\2\2\u010e\u010f\7r\2\2\u010f\u0110\7t\2\2\u0110\u0111\7q\2\2\u0111\u0112"+
		"\7r\2\2\u0112\u0113\7g\2\2\u0113\u0114\7t\2\2\u0114\u0115\7v\2\2\u0115"+
		"\u0116\7{\2\2\u0116(\3\2\2\2\u0117\u0118\7w\2\2\u0118\u0119\7p\2\2\u0119"+
		"\u011a\7k\2\2\u011a\u011b\7x\2\2\u011b\u011c\7g\2\2\u011c\u011d\7t\2\2"+
		"\u011d\u011e\7u\2\2\u011e\u011f\7c\2\2\u011f\u0120\7n\2\2\u0120*\3\2\2"+
		"\2\u0121\u0122\7c\2\2\u0122\u0123\7n\2\2\u0123\u0124\7y\2\2\u0124\u0125"+
		"\7c\2\2\u0125\u0126\7{\2\2\u0126\u0127\7u\2\2\u0127,\3\2\2\2\u0128\u0129"+
		"\7c\2\2\u0129\u012a\7f\2\2\u012a\u012b\7f\2\2\u012b\u012c\7t\2\2\u012c"+
		"\u012d\7g\2\2\u012d\u012e\7u\2\2\u012e\u012f\7u\2\2\u012f\u0130\7g\2\2"+
		"\u0130\u0131\7f\2\2\u0131.\3\2\2\2\u0132\u0133\7c\2\2\u0133\u0134\7i\2"+
		"\2\u0134\u0135\7i\2\2\u0135\u0136\7t\2\2\u0136\u0137\7g\2\2\u0137\u0138"+
		"\7i\2\2\u0138\u0139\7c\2\2\u0139\u013a\7d\2\2\u013a\u013b\7n\2\2\u013b"+
		"\u013c\7g\2\2\u013c\60\3\2\2\2\u013d\u013e\7*\2\2\u013e\62\3\2\2\2\u013f"+
		"\u0140\7+\2\2\u0140\64\3\2\2\2\u0141\u0142\7]\2\2\u0142\66\3\2\2\2\u0143"+
		"\u0144\7_\2\2\u01448\3\2\2\2\u0145\u0146\7\60\2\2\u0146\u0147\7\60\2\2"+
		"\u0147\u0148\7\60\2\2\u0148:\3\2\2\2\u0149\u014a\7@\2\2\u014a\u014b\b"+
		"\36\2\2\u014b<\3\2\2\2\u014c\u014d\7>\2\2\u014d>\3\2\2\2\u014e\u014f\7"+
		"(\2\2\u014f@\3\2\2\2\u0150\u0151\7&\2\2\u0151B\3\2\2\2\u0152\u0153\7\u20ae"+
		"\2\2\u0153D\3\2\2\2\u0154\u0155\7\'\2\2\u0155F\3\2\2\2\u0156\u0157\7\u00bc"+
		"\2\2\u0157H\3\2\2\2\u0158\u0159\7<\2\2\u0159J\3\2\2\2\u015a\u015b\7.\2"+
		"\2\u015bL\3\2\2\2\u015c\u015d\7\60\2\2\u015dN\3\2\2\2\u015e\u015f\7?\2"+
		"\2\u015fP\3\2\2\2\u0160\u0161\7)\2\2\u0161R\3\2\2\2\u0162\u0164\7=\2\2"+
		"\u0163\u0162\3\2\2\2\u0164\u0165\3\2\2\2\u0165\u0163\3\2\2\2\u0165\u0166"+
		"\3\2\2\2\u0166\u0167\3\2\2\2\u0167\u0168\b*\3\2\u0168T\3\2\2\2\u0169\u016a"+
		"\7,\2\2\u016aV\3\2\2\2\u016b\u016c\7-\2\2\u016cX\3\2\2\2\u016d\u016e\7"+
		"/\2\2\u016eZ\3\2\2\2\u016f\u0171\5Y-\2\u0170\u0172\5Y-\2\u0171\u0170\3"+
		"\2\2\2\u0172\u0173\3\2\2\2\u0173\u0171\3\2\2\2\u0173\u0174\3\2\2\2\u0174"+
		"\\\3\2\2\2\u0175\u0176\7a\2\2\u0176^\3\2\2\2\u0177\u0178\7u\2\2\u0178"+
		"\u0179\7n\2\2\u0179\u017a\7q\2\2\u017a\u017b\7v\2\2\u017b`\3\2\2\2\u017c"+
		"\u017d\7n\2\2\u017d\u017e\7k\2\2\u017e\u017f\7p\2\2\u017f\u0180\7m\2\2"+
		"\u0180b\3\2\2\2\u0181\u0182\7y\2\2\u0182\u0183\7q\2\2\u0183\u0184\7t\2"+
		"\2\u0184\u0185\7f\2\2\u0185d\3\2\2\2\u0186\u0187\7t\2\2\u0187\u0188\7"+
		"g\2\2\u0188\u0189\7u\2\2\u0189\u018a\7q\2\2\u018a\u018b\7w\2\2\u018b\u018c"+
		"\7t\2\2\u018c\u018d\7e\2\2\u018d\u018e\7g\2\2\u018ef\3\2\2\2\u018f\u0190"+
		"\7e\2\2\u0190\u0191\7q\2\2\u0191\u0192\7q\2\2\u0192\u0193\7t\2\2\u0193"+
		"\u0194\7f\2\2\u0194\u0195\7k\2\2\u0195\u0196\7p\2\2\u0196\u0197\7c\2\2"+
		"\u0197\u0198\7v\2\2\u0198\u0199\7g\2\2\u0199h\3\2\2\2\u019a\u019b\7k\2"+
		"\2\u019b\u019c\7p\2\2\u019c\u019d\7v\2\2\u019d\u019e\7g\2\2\u019e\u019f"+
		"\7i\2\2\u019f\u01a0\7g\2\2\u01a0\u01a1\7t\2\2\u01a1j\3\2\2\2\u01a2\u01a3"+
		"\7p\2\2\u01a3\u01a4\7c\2\2\u01a4\u01a5\7v\2\2\u01a5\u01a6\7w\2\2\u01a6"+
		"\u01a7\7t\2\2\u01a7\u01a8\7c\2\2\u01a8\u01a9\7n\2\2\u01a9l\3\2\2\2\u01aa"+
		"\u01ab\7f\2\2\u01ab\u01ac\7q\2\2\u01ac\u01ad\7w\2\2\u01ad\u01ae\7d\2\2"+
		"\u01ae\u01af\7n\2\2\u01af\u01b0\7g\2\2\u01b0n\3\2\2\2\u01b1\u01b2\7u\2"+
		"\2\u01b2\u01b3\7v\2\2\u01b3\u01b4\7t\2\2\u01b4\u01b5\7k\2\2\u01b5\u01b6"+
		"\7p\2\2\u01b6\u01b7\7i\2\2\u01b7p\3\2\2\2\u01b8\u01b9\7d\2\2\u01b9\u01ba"+
		"\7q\2\2\u01ba\u01bb\7q\2\2\u01bb\u01bc\7n\2\2\u01bc\u01bd\7g\2\2\u01bd"+
		"\u01be\7c\2\2\u01be\u01bf\7p\2\2\u01bfr\3\2\2\2\u01c0\u01c1\7f\2\2\u01c1"+
		"\u01c2\7c\2\2\u01c2\u01c3\7v\2\2\u01c3\u01c4\7g\2\2\u01c4t\3\2\2\2\u01c5"+
		"\u01c6\7g\2\2\u01c6\u01c7\7o\2\2\u01c7\u01c8\7r\2\2\u01c8\u01c9\7v\2\2"+
		"\u01c9\u01ca\7{\2\2\u01cav\3\2\2\2\u01cb\u01cc\7v\2\2\u01cc\u01cd\7t\2"+
		"\2\u01cd\u01ce\7w\2\2\u01ce\u01d5\7g\2\2\u01cf\u01d0\7h\2\2\u01d0\u01d1"+
		"\7c\2\2\u01d1\u01d2\7n\2\2\u01d2\u01d3\7u\2\2\u01d3\u01d5\7g\2\2\u01d4"+
		"\u01cb\3\2\2\2\u01d4\u01cf\3\2\2\2\u01d5x\3\2\2\2\u01d6\u01d8\5W,\2\u01d7"+
		"\u01d6\3\2\2\2\u01d7\u01d8\3\2\2\2\u01d8\u01da\3\2\2\2\u01d9\u01db\5\u008b"+
		"F\2\u01da\u01d9\3\2\2\2\u01db\u01dc\3\2\2\2\u01dc\u01da\3\2\2\2\u01dc"+
		"\u01dd\3\2\2\2\u01ddz\3\2\2\2\u01de\u01e0\5Y-\2\u01df\u01e1\5\u008bF\2"+
		"\u01e0\u01df\3\2\2\2\u01e1\u01e2\3\2\2\2\u01e2\u01e0\3\2\2\2\u01e2\u01e3"+
		"\3\2\2\2\u01e3|\3\2\2\2\u01e4\u01e7\5W,\2\u01e5\u01e7\5Y-\2\u01e6\u01e4"+
		"\3\2\2\2\u01e6\u01e5\3\2\2\2\u01e6\u01e7\3\2\2\2\u01e7\u01e9\3\2\2\2\u01e8"+
		"\u01ea\5\u008bF\2\u01e9\u01e8\3\2\2\2\u01ea\u01eb\3\2\2\2\u01eb\u01e9"+
		"\3\2\2\2\u01eb\u01ec\3\2\2\2\u01ec\u01ed\3\2\2\2\u01ed\u01ef\5M\'\2\u01ee"+
		"\u01f0\5\u008bF\2\u01ef\u01ee\3\2\2\2\u01f0\u01f1\3\2\2\2\u01f1\u01ef"+
		"\3\2\2\2\u01f1\u01f2\3\2\2\2\u01f2~\3\2\2\2\u01f3\u01f7\5Q)\2\u01f4\u01f6"+
		"\n\2\2\2\u01f5\u01f4\3\2\2\2\u01f6\u01f9\3\2\2\2\u01f7\u01f5\3\2\2\2\u01f7"+
		"\u01f8\3\2\2\2\u01f8\u01fa\3\2\2\2\u01f9\u01f7\3\2\2\2\u01fa\u01fb\5Q"+
		")\2\u01fb\u0080\3\2\2\2\u01fc\u0200\5[.\2\u01fd\u01ff\n\3\2\2\u01fe\u01fd"+
		"\3\2\2\2\u01ff\u0202\3\2\2\2\u0200\u01fe\3\2\2\2\u0200\u0201\3\2\2\2\u0201"+
		"\u0203\3\2\2\2\u0202\u0200\3\2\2\2\u0203\u0204\5[.\2\u0204\u0082\3\2\2"+
		"\2\u0205\u0206\5? \2\u0206\u0207\5\u008bF\2\u0207\u0208\5\u008bF\2\u0208"+
		"\u020e\5\u008bF\2\u0209\u020a\5M\'\2\u020a\u020b\5\u008bF\2\u020b\u020c"+
		"\5\u008bF\2\u020c\u020d\5\u008bF\2\u020d\u020f\3\2\2\2\u020e\u0209\3\2"+
		"\2\2\u020f\u0210\3\2\2\2\u0210\u020e\3\2\2\2\u0210\u0211\3\2\2\2\u0211"+
		"\u0084\3\2\2\2\u0212\u0213\5y=\2\u0213\u0214\5Y-\2\u0214\u0216\3\2\2\2"+
		"\u0215\u0212\3\2\2\2\u0216\u0217\3\2\2\2\u0217\u0215\3\2\2\2\u0217\u0218"+
		"\3\2\2\2\u0218\u0219\3\2\2\2\u0219\u021a\5y=\2\u021a\u021d\3\2\2\2\u021b"+
		"\u021d\5y=\2\u021c\u0215\3\2\2\2\u021c\u021b\3\2\2\2\u021d\u0086\3\2\2"+
		"\2\u021e\u0222\5}?\2\u021f\u0222\5{>\2\u0220\u0222\5y=\2\u0221\u021e\3"+
		"\2\2\2\u0221\u021f\3\2\2\2\u0221\u0220\3\2\2\2\u0222\u0229\3\2\2\2\u0223"+
		"\u0227\5Y-\2\u0224\u0228\5}?\2\u0225\u0228\5{>\2\u0226\u0228\5y=\2\u0227"+
		"\u0224\3\2\2\2\u0227\u0225\3\2\2\2\u0227\u0226\3\2\2\2\u0228\u022a\3\2"+
		"\2\2\u0229\u0223\3\2\2\2\u022a\u022b\3\2\2\2\u022b\u0229\3\2\2\2\u022b"+
		"\u022c\3\2\2\2\u022c\u0088\3\2\2\2\u022d\u0234\5\u008dG\2\u022e\u0233"+
		"\5\u008bF\2\u022f\u0233\5\u008dG\2\u0230\u0233\5Y-\2\u0231\u0233\5]/\2"+
		"\u0232\u022e\3\2\2\2\u0232\u022f\3\2\2\2\u0232\u0230\3\2\2\2\u0232\u0231"+
		"\3\2\2\2\u0233\u0236\3\2\2\2\u0234\u0232\3\2\2\2\u0234\u0235\3\2\2\2\u0235"+
		"\u008a\3\2\2\2\u0236\u0234\3\2\2\2\u0237\u0238\t\4\2\2\u0238\u008c\3\2"+
		"\2\2\u0239\u023a\t\5\2\2\u023a\u008e\3\2\2\2\u023b\u023d\5\u0097L\2\u023c"+
		"\u023b\3\2\2\2\u023d\u023e\3\2\2\2\u023e\u023c\3\2\2\2\u023e\u023f\3\2"+
		"\2\2\u023f\u0243\3\2\2\2\u0240\u0242\5\u0095K\2\u0241\u0240\3\2\2\2\u0242"+
		"\u0245\3\2\2\2\u0243\u0241\3\2\2\2\u0243\u0244\3\2\2\2\u0244\u0246\3\2"+
		"\2\2\u0245\u0243\3\2\2\2\u0246\u0247\bH\4\2\u0247\u0090\3\2\2\2\u0248"+
		"\u024a\5\u0095K\2\u0249\u0248\3\2\2\2\u024a\u024b\3\2\2\2\u024b\u0249"+
		"\3\2\2\2\u024b\u024c\3\2\2\2\u024c\u024e\3\2\2\2\u024d\u024f\7\2\2\3\u024e"+
		"\u024d\3\2\2\2\u024e\u024f\3\2\2\2\u024f\u0250\3\2\2\2\u0250\u0251\bI"+
		"\5\2\u0251\u0092\3\2\2\2\u0252\u0256\7%\2\2\u0253\u0255\13\2\2\2\u0254"+
		"\u0253\3\2\2\2\u0255\u0258\3\2\2\2\u0256\u0257\3\2\2\2\u0256\u0254\3\2"+
		"\2\2\u0257\u0259\3\2\2\2\u0258\u0256\3\2\2\2\u0259\u025a\5\u0097L\2\u025a"+
		"\u0094\3\2\2\2\u025b\u025c\t\6\2\2\u025c\u0096\3\2\2\2\u025d\u025f\7\17"+
		"\2\2\u025e\u025d\3\2\2\2\u025e\u025f\3\2\2\2\u025f\u0260\3\2\2\2\u0260"+
		"\u0263\7\f\2\2\u0261\u0263\7\f\2\2\u0262\u025e\3\2\2\2\u0262\u0261\3\2"+
		"\2\2\u0263\u0098\3\2\2\2\u0264\u0265\7k\2\2\u0265\u0266\7p\2\2\u0266\u0267"+
		"\7f\2\2\u0267\u0268\7g\2\2\u0268\u0269\7p\2\2\u0269\u026a\7v\2\2\u026a"+
		"\u009a\3\2\2\2\u026b\u026c\7f\2\2\u026c\u026d\7g\2\2\u026d\u026e\7f\2"+
		"\2\u026e\u026f\7g\2\2\u026f\u0270\7p\2\2\u0270\u0271\7v\2\2\u0271\u009c"+
		"\3\2\2\2\u0272\u0273\13\2\2\2\u0273\u009e\3\2\2\2\35\2\u0165\u0173\u01d4"+
		"\u01d7\u01dc\u01e2\u01e6\u01eb\u01f1\u01f7\u0200\u0210\u0217\u021c\u0221"+
		"\u0227\u022b\u0232\u0234\u023e\u0243\u024b\u024e\u0256\u025e\u0262\6\3"+
		"\36\2\3*\3\3H\4\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}