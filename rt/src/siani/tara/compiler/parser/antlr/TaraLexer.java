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
		METAIDENTIFIER=1, SUB=2, USE=3, BOX=4, METAMODEL=5, AS=6, HAS=7, ON=8, 
		IS=9, WITH=10, EXTENDS=11, PRIVATE=12, SINGLE=13, REQUIRED=14, COMPONENT=15, 
		FACET=16, INTENTION=17, TERMINAL=18, NAMED=19, PROPERTY=20, ALWAYS=21, 
		LEFT_PARENTHESIS=22, RIGHT_PARENTHESIS=23, LEFT_SQUARE=24, RIGHT_SQUARE=25, 
		LIST=26, OPEN_BRACKET=27, CLOSE_BRACKET=28, AMPERSAND=29, DOLLAR=30, EURO=31, 
		PERCENTAGE=32, GRADE=33, COLON=34, COMMA=35, DOT=36, EQUALS=37, APHOSTROPHE=38, 
		SEMICOLON=39, STAR=40, POSITIVE=41, DASH=42, DASHES=43, UNDERDASH=44, 
		VAR=45, WORD=46, RESOURCE=47, PORT_TYPE=48, COORDINATE_TYPE=49, INT_TYPE=50, 
		NATURAL_TYPE=51, DOUBLE_TYPE=52, STRING_TYPE=53, BOOLEAN_TYPE=54, DATE_TYPE=55, 
		EMPTY=56, BOOLEAN_VALUE=57, NATURAL_VALUE=58, NEGATIVE_VALUE=59, DOUBLE_VALUE=60, 
		STRING_VALUE=61, STRING_MULTILINE_VALUE_KEY=62, CODE_VALUE=63, DATE_VALUE=64, 
		COORDINATE_VALUE=65, IDENTIFIER=66, DIGIT=67, LETTER=68, NEWLINE=69, SPACES=70, 
		DOC=71, SP=72, NL=73, NEW_LINE_INDENT=74, DEDENT=75, UNKNOWN_TOKEN=76;
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
		"'F'", "'G'", "'H'", "'I'", "'J'", "'K'", "'L'"
	};
	public static final String[] ruleNames = {
		"METAIDENTIFIER", "SUB", "USE", "BOX", "METAMODEL", "AS", "HAS", "ON", 
		"IS", "WITH", "EXTENDS", "PRIVATE", "SINGLE", "REQUIRED", "COMPONENT", 
		"FACET", "INTENTION", "TERMINAL", "NAMED", "PROPERTY", "ALWAYS", "LEFT_PARENTHESIS", 
		"RIGHT_PARENTHESIS", "LEFT_SQUARE", "RIGHT_SQUARE", "LIST", "OPEN_BRACKET", 
		"CLOSE_BRACKET", "AMPERSAND", "DOLLAR", "EURO", "PERCENTAGE", "GRADE", 
		"COLON", "COMMA", "DOT", "EQUALS", "APHOSTROPHE", "SEMICOLON", "STAR", 
		"POSITIVE", "DASH", "DASHES", "UNDERDASH", "VAR", "WORD", "RESOURCE", 
		"PORT_TYPE", "COORDINATE_TYPE", "INT_TYPE", "NATURAL_TYPE", "DOUBLE_TYPE", 
		"STRING_TYPE", "BOOLEAN_TYPE", "DATE_TYPE", "EMPTY", "BOOLEAN_VALUE", 
		"NATURAL_VALUE", "NEGATIVE_VALUE", "DOUBLE_VALUE", "STRING_VALUE", "STRING_MULTILINE_VALUE_KEY", 
		"CODE_VALUE", "DATE_VALUE", "COORDINATE_VALUE", "IDENTIFIER", "DIGIT", 
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
		case 26: OPEN_BRACKET_action((RuleContext)_localctx, actionIndex); break;
		case 27: CLOSE_BRACKET_action((RuleContext)_localctx, actionIndex); break;
		case 38: SEMICOLON_action((RuleContext)_localctx, actionIndex); break;
		case 68: NEWLINE_action((RuleContext)_localctx, actionIndex); break;
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2N\u0252\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
		"\tI\4J\tJ\4K\tK\4L\tL\4M\tM\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3"+
		"\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\13\3\13"+
		"\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26"+
		"\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\33"+
		"\3\33\3\33\3\33\3\34\3\34\3\34\3\35\3\35\3\35\3\36\3\36\3\37\3\37\3 \3"+
		" \3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\3&\3&\3\'\3\'\3(\6(\u014c\n(\r(\16("+
		"\u014d\3(\3(\3)\3)\3*\3*\3+\3+\3,\3,\6,\u015a\n,\r,\16,\u015b\3-\3-\3"+
		".\3.\3.\3.\3/\3/\3/\3/\3/\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60"+
		"\3\61\3\61\3\61\3\61\3\61\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\62"+
		"\3\62\3\62\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\64\3\64\3\64\3\64"+
		"\3\64\3\64\3\64\3\64\3\65\3\65\3\65\3\65\3\65\3\65\3\65\3\66\3\66\3\66"+
		"\3\66\3\66\3\66\3\66\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\38\38\38"+
		"\38\38\39\39\39\39\39\39\3:\3:\3:\3:\3:\3:\3:\3:\3:\5:\u01bc\n:\3;\5;"+
		"\u01bf\n;\3;\6;\u01c2\n;\r;\16;\u01c3\3<\3<\6<\u01c8\n<\r<\16<\u01c9\3"+
		"=\3=\5=\u01ce\n=\3=\6=\u01d1\n=\r=\16=\u01d2\3=\3=\6=\u01d7\n=\r=\16="+
		"\u01d8\3>\3>\7>\u01dd\n>\f>\16>\u01e0\13>\3>\3>\3?\3?\7?\u01e6\n?\f?\16"+
		"?\u01e9\13?\3?\3?\3@\3@\3@\6@\u01f0\n@\r@\16@\u01f1\3A\3A\3A\6A\u01f7"+
		"\nA\rA\16A\u01f8\3A\3A\3A\5A\u01fe\nA\3B\3B\3B\6B\u0203\nB\rB\16B\u0204"+
		"\3B\3B\3B\5B\u020a\nB\3C\3C\3C\3C\3C\7C\u0211\nC\fC\16C\u0214\13C\3D\3"+
		"D\3E\3E\3F\6F\u021b\nF\rF\16F\u021c\3F\7F\u0220\nF\fF\16F\u0223\13F\3"+
		"F\3F\3G\6G\u0228\nG\rG\16G\u0229\3G\5G\u022d\nG\3G\3G\3H\3H\7H\u0233\n"+
		"H\fH\16H\u0236\13H\3H\3H\3I\3I\3J\5J\u023d\nJ\3J\3J\5J\u0241\nJ\3K\3K"+
		"\3K\3K\3K\3K\3K\3L\3L\3L\3L\3L\3L\3L\3M\3M\3\u0234\2N\3\3\5\4\7\5\t\6"+
		"\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24"+
		"\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K"+
		"\'M(O)Q*S+U,W-Y.[/]\60_\61a\62c\63e\64g\65i\66k\67m8o9q:s;u<w=y>{?}@\177"+
		"A\u0081B\u0083C\u0085D\u0087E\u0089F\u008bG\u008dH\u008fI\u0091J\u0093"+
		"K\u0095L\u0097M\u0099N\3\2\7\3\2))\3\2//\3\2\62;\4\2C\\c|\4\2\13\13\""+
		"\"\u026e\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2"+
		"\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27"+
		"\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2"+
		"\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2"+
		"\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2"+
		"\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2"+
		"\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S"+
		"\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2"+
		"\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2"+
		"\2m\3\2\2\2\2o\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\2u\3\2\2\2\2w\3\2\2\2\2y"+
		"\3\2\2\2\2{\3\2\2\2\2}\3\2\2\2\2\177\3\2\2\2\2\u0081\3\2\2\2\2\u0083\3"+
		"\2\2\2\2\u0085\3\2\2\2\2\u0087\3\2\2\2\2\u0089\3\2\2\2\2\u008b\3\2\2\2"+
		"\2\u008d\3\2\2\2\2\u008f\3\2\2\2\2\u0091\3\2\2\2\2\u0093\3\2\2\2\2\u0095"+
		"\3\2\2\2\2\u0097\3\2\2\2\2\u0099\3\2\2\2\3\u009b\3\2\2\2\5\u00a3\3\2\2"+
		"\2\7\u00a7\3\2\2\2\t\u00ab\3\2\2\2\13\u00af\3\2\2\2\r\u00b9\3\2\2\2\17"+
		"\u00bc\3\2\2\2\21\u00c0\3\2\2\2\23\u00c3\3\2\2\2\25\u00c6\3\2\2\2\27\u00cb"+
		"\3\2\2\2\31\u00d3\3\2\2\2\33\u00db\3\2\2\2\35\u00e2\3\2\2\2\37\u00eb\3"+
		"\2\2\2!\u00f5\3\2\2\2#\u00fb\3\2\2\2%\u0105\3\2\2\2\'\u010e\3\2\2\2)\u0114"+
		"\3\2\2\2+\u011d\3\2\2\2-\u0124\3\2\2\2/\u0126\3\2\2\2\61\u0128\3\2\2\2"+
		"\63\u012a\3\2\2\2\65\u012c\3\2\2\2\67\u0130\3\2\2\29\u0133\3\2\2\2;\u0136"+
		"\3\2\2\2=\u0138\3\2\2\2?\u013a\3\2\2\2A\u013c\3\2\2\2C\u013e\3\2\2\2E"+
		"\u0140\3\2\2\2G\u0142\3\2\2\2I\u0144\3\2\2\2K\u0146\3\2\2\2M\u0148\3\2"+
		"\2\2O\u014b\3\2\2\2Q\u0151\3\2\2\2S\u0153\3\2\2\2U\u0155\3\2\2\2W\u0157"+
		"\3\2\2\2Y\u015d\3\2\2\2[\u015f\3\2\2\2]\u0163\3\2\2\2_\u0168\3\2\2\2a"+
		"\u0171\3\2\2\2c\u0176\3\2\2\2e\u0181\3\2\2\2g\u0189\3\2\2\2i\u0191\3\2"+
		"\2\2k\u0198\3\2\2\2m\u019f\3\2\2\2o\u01a7\3\2\2\2q\u01ac\3\2\2\2s\u01bb"+
		"\3\2\2\2u\u01be\3\2\2\2w\u01c5\3\2\2\2y\u01cd\3\2\2\2{\u01da\3\2\2\2}"+
		"\u01e3\3\2\2\2\177\u01ec\3\2\2\2\u0081\u01fd\3\2\2\2\u0083\u0209\3\2\2"+
		"\2\u0085\u020b\3\2\2\2\u0087\u0215\3\2\2\2\u0089\u0217\3\2\2\2\u008b\u021a"+
		"\3\2\2\2\u008d\u0227\3\2\2\2\u008f\u0230\3\2\2\2\u0091\u0239\3\2\2\2\u0093"+
		"\u0240\3\2\2\2\u0095\u0242\3\2\2\2\u0097\u0249\3\2\2\2\u0099\u0250\3\2"+
		"\2\2\u009b\u009c\7E\2\2\u009c\u009d\7q\2\2\u009d\u009e\7p\2\2\u009e\u009f"+
		"\7e\2\2\u009f\u00a0\7g\2\2\u00a0\u00a1\7r\2\2\u00a1\u00a2\7v\2\2\u00a2"+
		"\4\3\2\2\2\u00a3\u00a4\7u\2\2\u00a4\u00a5\7w\2\2\u00a5\u00a6\7d\2\2\u00a6"+
		"\6\3\2\2\2\u00a7\u00a8\7w\2\2\u00a8\u00a9\7u\2\2\u00a9\u00aa\7g\2\2\u00aa"+
		"\b\3\2\2\2\u00ab\u00ac\7d\2\2\u00ac\u00ad\7q\2\2\u00ad\u00ae\7z\2\2\u00ae"+
		"\n\3\2\2\2\u00af\u00b0\7o\2\2\u00b0\u00b1\7g\2\2\u00b1\u00b2\7v\2\2\u00b2"+
		"\u00b3\7c\2\2\u00b3\u00b4\7o\2\2\u00b4\u00b5\7q\2\2\u00b5\u00b6\7f\2\2"+
		"\u00b6\u00b7\7g\2\2\u00b7\u00b8\7n\2\2\u00b8\f\3\2\2\2\u00b9\u00ba\7c"+
		"\2\2\u00ba\u00bb\7u\2\2\u00bb\16\3\2\2\2\u00bc\u00bd\7j\2\2\u00bd\u00be"+
		"\7c\2\2\u00be\u00bf\7u\2\2\u00bf\20\3\2\2\2\u00c0\u00c1\7q\2\2\u00c1\u00c2"+
		"\7p\2\2\u00c2\22\3\2\2\2\u00c3\u00c4\7k\2\2\u00c4\u00c5\7u\2\2\u00c5\24"+
		"\3\2\2\2\u00c6\u00c7\7y\2\2\u00c7\u00c8\7k\2\2\u00c8\u00c9\7v\2\2\u00c9"+
		"\u00ca\7j\2\2\u00ca\26\3\2\2\2\u00cb\u00cc\7g\2\2\u00cc\u00cd\7z\2\2\u00cd"+
		"\u00ce\7v\2\2\u00ce\u00cf\7g\2\2\u00cf\u00d0\7p\2\2\u00d0\u00d1\7f\2\2"+
		"\u00d1\u00d2\7u\2\2\u00d2\30\3\2\2\2\u00d3\u00d4\7r\2\2\u00d4\u00d5\7"+
		"t\2\2\u00d5\u00d6\7k\2\2\u00d6\u00d7\7x\2\2\u00d7\u00d8\7c\2\2\u00d8\u00d9"+
		"\7v\2\2\u00d9\u00da\7g\2\2\u00da\32\3\2\2\2\u00db\u00dc\7u\2\2\u00dc\u00dd"+
		"\7k\2\2\u00dd\u00de\7p\2\2\u00de\u00df\7i\2\2\u00df\u00e0\7n\2\2\u00e0"+
		"\u00e1\7g\2\2\u00e1\34\3\2\2\2\u00e2\u00e3\7t\2\2\u00e3\u00e4\7g\2\2\u00e4"+
		"\u00e5\7s\2\2\u00e5\u00e6\7w\2\2\u00e6\u00e7\7k\2\2\u00e7\u00e8\7t\2\2"+
		"\u00e8\u00e9\7g\2\2\u00e9\u00ea\7f\2\2\u00ea\36\3\2\2\2\u00eb\u00ec\7"+
		"e\2\2\u00ec\u00ed\7q\2\2\u00ed\u00ee\7o\2\2\u00ee\u00ef\7r\2\2\u00ef\u00f0"+
		"\7q\2\2\u00f0\u00f1\7p\2\2\u00f1\u00f2\7g\2\2\u00f2\u00f3\7p\2\2\u00f3"+
		"\u00f4\7v\2\2\u00f4 \3\2\2\2\u00f5\u00f6\7h\2\2\u00f6\u00f7\7c\2\2\u00f7"+
		"\u00f8\7e\2\2\u00f8\u00f9\7g\2\2\u00f9\u00fa\7v\2\2\u00fa\"\3\2\2\2\u00fb"+
		"\u00fc\7k\2\2\u00fc\u00fd\7p\2\2\u00fd\u00fe\7v\2\2\u00fe\u00ff\7g\2\2"+
		"\u00ff\u0100\7p\2\2\u0100\u0101\7v\2\2\u0101\u0102\7k\2\2\u0102\u0103"+
		"\7q\2\2\u0103\u0104\7p\2\2\u0104$\3\2\2\2\u0105\u0106\7v\2\2\u0106\u0107"+
		"\7g\2\2\u0107\u0108\7t\2\2\u0108\u0109\7o\2\2\u0109\u010a\7k\2\2\u010a"+
		"\u010b\7p\2\2\u010b\u010c\7c\2\2\u010c\u010d\7n\2\2\u010d&\3\2\2\2\u010e"+
		"\u010f\7p\2\2\u010f\u0110\7c\2\2\u0110\u0111\7o\2\2\u0111\u0112\7g\2\2"+
		"\u0112\u0113\7f\2\2\u0113(\3\2\2\2\u0114\u0115\7r\2\2\u0115\u0116\7t\2"+
		"\2\u0116\u0117\7q\2\2\u0117\u0118\7r\2\2\u0118\u0119\7g\2\2\u0119\u011a"+
		"\7t\2\2\u011a\u011b\7v\2\2\u011b\u011c\7{\2\2\u011c*\3\2\2\2\u011d\u011e"+
		"\7c\2\2\u011e\u011f\7n\2\2\u011f\u0120\7y\2\2\u0120\u0121\7c\2\2\u0121"+
		"\u0122\7{\2\2\u0122\u0123\7u\2\2\u0123,\3\2\2\2\u0124\u0125\7*\2\2\u0125"+
		".\3\2\2\2\u0126\u0127\7+\2\2\u0127\60\3\2\2\2\u0128\u0129\7]\2\2\u0129"+
		"\62\3\2\2\2\u012a\u012b\7_\2\2\u012b\64\3\2\2\2\u012c\u012d\7\60\2\2\u012d"+
		"\u012e\7\60\2\2\u012e\u012f\7\60\2\2\u012f\66\3\2\2\2\u0130\u0131\7}\2"+
		"\2\u0131\u0132\b\34\2\2\u01328\3\2\2\2\u0133\u0134\7\177\2\2\u0134\u0135"+
		"\b\35\3\2\u0135:\3\2\2\2\u0136\u0137\7(\2\2\u0137<\3\2\2\2\u0138\u0139"+
		"\7&\2\2\u0139>\3\2\2\2\u013a\u013b\7\u20ae\2\2\u013b@\3\2\2\2\u013c\u013d"+
		"\7\'\2\2\u013dB\3\2\2\2\u013e\u013f\7\u00bc\2\2\u013fD\3\2\2\2\u0140\u0141"+
		"\7<\2\2\u0141F\3\2\2\2\u0142\u0143\7.\2\2\u0143H\3\2\2\2\u0144\u0145\7"+
		"\60\2\2\u0145J\3\2\2\2\u0146\u0147\7?\2\2\u0147L\3\2\2\2\u0148\u0149\7"+
		")\2\2\u0149N\3\2\2\2\u014a\u014c\7=\2\2\u014b\u014a\3\2\2\2\u014c\u014d"+
		"\3\2\2\2\u014d\u014b\3\2\2\2\u014d\u014e\3\2\2\2\u014e\u014f\3\2\2\2\u014f"+
		"\u0150\b(\4\2\u0150P\3\2\2\2\u0151\u0152\7,\2\2\u0152R\3\2\2\2\u0153\u0154"+
		"\7-\2\2\u0154T\3\2\2\2\u0155\u0156\7/\2\2\u0156V\3\2\2\2\u0157\u0159\5"+
		"U+\2\u0158\u015a\5U+\2\u0159\u0158\3\2\2\2\u015a\u015b\3\2\2\2\u015b\u0159"+
		"\3\2\2\2\u015b\u015c\3\2\2\2\u015cX\3\2\2\2\u015d\u015e\7a\2\2\u015eZ"+
		"\3\2\2\2\u015f\u0160\7x\2\2\u0160\u0161\7c\2\2\u0161\u0162\7t\2\2\u0162"+
		"\\\3\2\2\2\u0163\u0164\7y\2\2\u0164\u0165\7q\2\2\u0165\u0166\7t\2\2\u0166"+
		"\u0167\7f\2\2\u0167^\3\2\2\2\u0168\u0169\7t\2\2\u0169\u016a\7g\2\2\u016a"+
		"\u016b\7u\2\2\u016b\u016c\7q\2\2\u016c\u016d\7w\2\2\u016d\u016e\7t\2\2"+
		"\u016e\u016f\7e\2\2\u016f\u0170\7g\2\2\u0170`\3\2\2\2\u0171\u0172\7r\2"+
		"\2\u0172\u0173\7q\2\2\u0173\u0174\7t\2\2\u0174\u0175\7v\2\2\u0175b\3\2"+
		"\2\2\u0176\u0177\7e\2\2\u0177\u0178\7q\2\2\u0178\u0179\7q\2\2\u0179\u017a"+
		"\7t\2\2\u017a\u017b\7f\2\2\u017b\u017c\7k\2\2\u017c\u017d\7p\2\2\u017d"+
		"\u017e\7c\2\2\u017e\u017f\7v\2\2\u017f\u0180\7g\2\2\u0180d\3\2\2\2\u0181"+
		"\u0182\7k\2\2\u0182\u0183\7p\2\2\u0183\u0184\7v\2\2\u0184\u0185\7g\2\2"+
		"\u0185\u0186\7i\2\2\u0186\u0187\7g\2\2\u0187\u0188\7t\2\2\u0188f\3\2\2"+
		"\2\u0189\u018a\7p\2\2\u018a\u018b\7c\2\2\u018b\u018c\7v\2\2\u018c\u018d"+
		"\7w\2\2\u018d\u018e\7t\2\2\u018e\u018f\7c\2\2\u018f\u0190\7n\2\2\u0190"+
		"h\3\2\2\2\u0191\u0192\7f\2\2\u0192\u0193\7q\2\2\u0193\u0194\7w\2\2\u0194"+
		"\u0195\7d\2\2\u0195\u0196\7n\2\2\u0196\u0197\7g\2\2\u0197j\3\2\2\2\u0198"+
		"\u0199\7u\2\2\u0199\u019a\7v\2\2\u019a\u019b\7t\2\2\u019b\u019c\7k\2\2"+
		"\u019c\u019d\7p\2\2\u019d\u019e\7i\2\2\u019el\3\2\2\2\u019f\u01a0\7d\2"+
		"\2\u01a0\u01a1\7q\2\2\u01a1\u01a2\7q\2\2\u01a2\u01a3\7n\2\2\u01a3\u01a4"+
		"\7g\2\2\u01a4\u01a5\7c\2\2\u01a5\u01a6\7p\2\2\u01a6n\3\2\2\2\u01a7\u01a8"+
		"\7f\2\2\u01a8\u01a9\7c\2\2\u01a9\u01aa\7v\2\2\u01aa\u01ab\7g\2\2\u01ab"+
		"p\3\2\2\2\u01ac\u01ad\7g\2\2\u01ad\u01ae\7o\2\2\u01ae\u01af\7r\2\2\u01af"+
		"\u01b0\7v\2\2\u01b0\u01b1\7{\2\2\u01b1r\3\2\2\2\u01b2\u01b3\7v\2\2\u01b3"+
		"\u01b4\7t\2\2\u01b4\u01b5\7w\2\2\u01b5\u01bc\7g\2\2\u01b6\u01b7\7h\2\2"+
		"\u01b7\u01b8\7c\2\2\u01b8\u01b9\7n\2\2\u01b9\u01ba\7u\2\2\u01ba\u01bc"+
		"\7g\2\2\u01bb\u01b2\3\2\2\2\u01bb\u01b6\3\2\2\2\u01bct\3\2\2\2\u01bd\u01bf"+
		"\5S*\2\u01be\u01bd\3\2\2\2\u01be\u01bf\3\2\2\2\u01bf\u01c1\3\2\2\2\u01c0"+
		"\u01c2\5\u0087D\2\u01c1\u01c0\3\2\2\2\u01c2\u01c3\3\2\2\2\u01c3\u01c1"+
		"\3\2\2\2\u01c3\u01c4\3\2\2\2\u01c4v\3\2\2\2\u01c5\u01c7\5U+\2\u01c6\u01c8"+
		"\5\u0087D\2\u01c7\u01c6\3\2\2\2\u01c8\u01c9\3\2\2\2\u01c9\u01c7\3\2\2"+
		"\2\u01c9\u01ca\3\2\2\2\u01cax\3\2\2\2\u01cb\u01ce\5S*\2\u01cc\u01ce\5"+
		"U+\2\u01cd\u01cb\3\2\2\2\u01cd\u01cc\3\2\2\2\u01cd\u01ce\3\2\2\2\u01ce"+
		"\u01d0\3\2\2\2\u01cf\u01d1\5\u0087D\2\u01d0\u01cf\3\2\2\2\u01d1\u01d2"+
		"\3\2\2\2\u01d2\u01d0\3\2\2\2\u01d2\u01d3\3\2\2\2\u01d3\u01d4\3\2\2\2\u01d4"+
		"\u01d6\5I%\2\u01d5\u01d7\5\u0087D\2\u01d6\u01d5\3\2\2\2\u01d7\u01d8\3"+
		"\2\2\2\u01d8\u01d6\3\2\2\2\u01d8\u01d9\3\2\2\2\u01d9z\3\2\2\2\u01da\u01de"+
		"\5M\'\2\u01db\u01dd\n\2\2\2\u01dc\u01db\3\2\2\2\u01dd\u01e0\3\2\2\2\u01de"+
		"\u01dc\3\2\2\2\u01de\u01df\3\2\2\2\u01df\u01e1\3\2\2\2\u01e0\u01de\3\2"+
		"\2\2\u01e1\u01e2\5M\'\2\u01e2|\3\2\2\2\u01e3\u01e7\5W,\2\u01e4\u01e6\n"+
		"\3\2\2\u01e5\u01e4\3\2\2\2\u01e6\u01e9\3\2\2\2\u01e7\u01e5\3\2\2\2\u01e7"+
		"\u01e8\3\2\2\2\u01e8\u01ea\3\2\2\2\u01e9\u01e7\3\2\2\2\u01ea\u01eb\5W"+
		",\2\u01eb~\3\2\2\2\u01ec\u01ef\5;\36\2\u01ed\u01f0\5\u0087D\2\u01ee\u01f0"+
		"\5\u0089E\2\u01ef\u01ed\3\2\2\2\u01ef\u01ee\3\2\2\2\u01f0\u01f1\3\2\2"+
		"\2\u01f1\u01ef\3\2\2\2\u01f1\u01f2\3\2\2\2\u01f2\u0080\3\2\2\2\u01f3\u01f4"+
		"\5u;\2\u01f4\u01f5\5U+\2\u01f5\u01f7\3\2\2\2\u01f6\u01f3\3\2\2\2\u01f7"+
		"\u01f8\3\2\2\2\u01f8\u01f6\3\2\2\2\u01f8\u01f9\3\2\2\2\u01f9\u01fa\3\2"+
		"\2\2\u01fa\u01fb\5u;\2\u01fb\u01fe\3\2\2\2\u01fc\u01fe\5u;\2\u01fd\u01f6"+
		"\3\2\2\2\u01fd\u01fc\3\2\2\2\u01fe\u0082\3\2\2\2\u01ff\u0200\5y=\2\u0200"+
		"\u0201\5U+\2\u0201\u0203\3\2\2\2\u0202\u01ff\3\2\2\2\u0203\u0204\3\2\2"+
		"\2\u0204\u0202\3\2\2\2\u0204\u0205\3\2\2\2\u0205\u0206\3\2\2\2\u0206\u0207"+
		"\5y=\2\u0207\u020a\3\2\2\2\u0208\u020a\5y=\2\u0209\u0202\3\2\2\2\u0209"+
		"\u0208\3\2\2\2\u020a\u0084\3\2\2\2\u020b\u0212\5\u0089E\2\u020c\u0211"+
		"\5\u0087D\2\u020d\u0211\5\u0089E\2\u020e\u0211\5U+\2\u020f\u0211\5Y-\2"+
		"\u0210\u020c\3\2\2\2\u0210\u020d\3\2\2\2\u0210\u020e\3\2\2\2\u0210\u020f"+
		"\3\2\2\2\u0211\u0214\3\2\2\2\u0212\u0210\3\2\2\2\u0212\u0213\3\2\2\2\u0213"+
		"\u0086\3\2\2\2\u0214\u0212\3\2\2\2\u0215\u0216\t\4\2\2\u0216\u0088\3\2"+
		"\2\2\u0217\u0218\t\5\2\2\u0218\u008a\3\2\2\2\u0219\u021b\5\u0093J\2\u021a"+
		"\u0219\3\2\2\2\u021b\u021c\3\2\2\2\u021c\u021a\3\2\2\2\u021c\u021d\3\2"+
		"\2\2\u021d\u0221\3\2\2\2\u021e\u0220\5\u0091I\2\u021f\u021e\3\2\2\2\u0220"+
		"\u0223\3\2\2\2\u0221\u021f\3\2\2\2\u0221\u0222\3\2\2\2\u0222\u0224\3\2"+
		"\2\2\u0223\u0221\3\2\2\2\u0224\u0225\bF\5\2\u0225\u008c\3\2\2\2\u0226"+
		"\u0228\5\u0091I\2\u0227\u0226\3\2\2\2\u0228\u0229\3\2\2\2\u0229\u0227"+
		"\3\2\2\2\u0229\u022a\3\2\2\2\u022a\u022c\3\2\2\2\u022b\u022d\7\2\2\3\u022c"+
		"\u022b\3\2\2\2\u022c\u022d\3\2\2\2\u022d\u022e\3\2\2\2\u022e\u022f\bG"+
		"\6\2\u022f\u008e\3\2\2\2\u0230\u0234\7%\2\2\u0231\u0233\13\2\2\2\u0232"+
		"\u0231\3\2\2\2\u0233\u0236\3\2\2\2\u0234\u0235\3\2\2\2\u0234\u0232\3\2"+
		"\2\2\u0235\u0237\3\2\2\2\u0236\u0234\3\2\2\2\u0237\u0238\5\u0093J\2\u0238"+
		"\u0090\3\2\2\2\u0239\u023a\t\6\2\2\u023a\u0092\3\2\2\2\u023b\u023d\7\17"+
		"\2\2\u023c\u023b\3\2\2\2\u023c\u023d\3\2\2\2\u023d\u023e\3\2\2\2\u023e"+
		"\u0241\7\f\2\2\u023f\u0241\7\f\2\2\u0240\u023c\3\2\2\2\u0240\u023f\3\2"+
		"\2\2\u0241\u0094\3\2\2\2\u0242\u0243\7k\2\2\u0243\u0244\7p\2\2\u0244\u0245"+
		"\7f\2\2\u0245\u0246\7g\2\2\u0246\u0247\7p\2\2\u0247\u0248\7v\2\2\u0248"+
		"\u0096\3\2\2\2\u0249\u024a\7f\2\2\u024a\u024b\7g\2\2\u024b\u024c\7f\2"+
		"\2\u024c\u024d\7g\2\2\u024d\u024e\7p\2\2\u024e\u024f\7v\2\2\u024f\u0098"+
		"\3\2\2\2\u0250\u0251\13\2\2\2\u0251\u009a\3\2\2\2\35\2\u014d\u015b\u01bb"+
		"\u01be\u01c3\u01c9\u01cd\u01d2\u01d8\u01de\u01e7\u01ef\u01f1\u01f8\u01fd"+
		"\u0204\u0209\u0210\u0212\u021c\u0221\u0229\u022c\u0234\u023c\u0240\7\3"+
		"\34\2\3\35\3\3(\4\3F\5\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}