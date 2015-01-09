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
		METAIDENTIFIER=1, SUB=2, USE=3, VAR=4, AS=5, HAS=6, ON=7, IS=8, WITH=9, 
		EXTENDS=10, ABSTRACT=11, SINGLE=12, REQUIRED=13, COMPONENT=14, FACET=15, 
		INTENTION=16, TERMINAL=17, NAMED=18, PROPERTY=19, LOCAL=20, ALWAYS=21, 
		ADDRESSED=22, AGGREGATED=23, READONLY=24, ROOT=25, LEFT_PARENTHESIS=26, 
		RIGHT_PARENTHESIS=27, LEFT_SQUARE=28, RIGHT_SQUARE=29, LIST=30, INLINE=31, 
		CLOSE_INLINE=32, AMPERSAND=33, COLON=34, COMMA=35, DOT=36, EQUALS=37, 
		APHOSTROPHE=38, SEMICOLON=39, STAR=40, PLUS=41, WORD=42, RESOURCE=43, 
		INT_TYPE=44, NATURAL_TYPE=45, DOUBLE_TYPE=46, STRING_TYPE=47, BOOLEAN_TYPE=48, 
		MEASURE_TYPE=49, RATIO_TYPE=50, DATE_TYPE=51, EMPTY=52, SCIENCE_NOT=53, 
		BOOLEAN_VALUE=54, NATURAL_VALUE=55, NEGATIVE_VALUE=56, DOUBLE_VALUE=57, 
		STRING_VALUE=58, STRING_MULTILINE_VALUE_KEY=59, ADDRESS_VALUE=60, IDENTIFIER=61, 
		MEASURE_VALUE=62, NEWLINE=63, SPACES=64, DOC=65, SP=66, NL=67, NEW_LINE_INDENT=68, 
		DEDENT=69, UNKNOWN_TOKEN=70;
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
		"'F'"
	};
	public static final String[] ruleNames = {
		"METAIDENTIFIER", "SUB", "USE", "VAR", "AS", "HAS", "ON", "IS", "WITH", 
		"EXTENDS", "ABSTRACT", "SINGLE", "REQUIRED", "COMPONENT", "FACET", "INTENTION", 
		"TERMINAL", "NAMED", "PROPERTY", "LOCAL", "ALWAYS", "ADDRESSED", "AGGREGATED", 
		"READONLY", "ROOT", "LEFT_PARENTHESIS", "RIGHT_PARENTHESIS", "LEFT_SQUARE", 
		"RIGHT_SQUARE", "LIST", "INLINE", "CLOSE_INLINE", "AMPERSAND", "COLON", 
		"COMMA", "DOT", "EQUALS", "APHOSTROPHE", "SEMICOLON", "STAR", "PLUS", 
		"WORD", "RESOURCE", "INT_TYPE", "NATURAL_TYPE", "DOUBLE_TYPE", "STRING_TYPE", 
		"BOOLEAN_TYPE", "MEASURE_TYPE", "RATIO_TYPE", "DATE_TYPE", "EMPTY", "SCIENCE_NOT", 
		"BOOLEAN_VALUE", "NATURAL_VALUE", "NEGATIVE_VALUE", "DOUBLE_VALUE", "STRING_VALUE", 
		"STRING_MULTILINE_VALUE_KEY", "ADDRESS_VALUE", "IDENTIFIER", "MEASURE_VALUE", 
		"NEWLINE", "SPACES", "DOC", "SP", "NL", "NEW_LINE_INDENT", "DEDENT", "UNKNOWN_TOKEN", 
		"DOLLAR", "EURO", "PERCENTAGE", "GRADE", "BY", "DIVIDED_BY", "DASH", "DASHES", 
		"UNDERDASH", "DIGIT", "LETTER"
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
		case 30: INLINE_action((RuleContext)_localctx, actionIndex); break;
		case 38: SEMICOLON_action((RuleContext)_localctx, actionIndex); break;
		case 62: NEWLINE_action((RuleContext)_localctx, actionIndex); break;
		case 64: DOC_action((RuleContext)_localctx, actionIndex); break;
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
	private void DOC_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 3: emitToken(DOC);emitToken(NEWLINE); break;
		}
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2H\u0286\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
		"\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\4O\tO\4P\tP\4Q\tQ\4R\tR\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3"+
		"\6\3\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30"+
		"\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32"+
		"\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3\37\3\37"+
		"\3 \3 \3 \3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\3&\3&\3\'\3\'\3(\6(\u016d\n"+
		"(\r(\16(\u016e\3(\3(\3)\3)\3*\3*\3+\3+\3+\3+\3+\3,\3,\3,\3,\3,\3-\3-\3"+
		"-\3-\3-\3-\3-\3-\3.\3.\3.\3.\3.\3.\3.\3.\3/\3/\3/\3/\3/\3/\3/\3\60\3\60"+
		"\3\60\3\60\3\60\3\60\3\60\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\62"+
		"\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\63\3\63\3\63\3\63\3\63\3\63\3\64"+
		"\3\64\3\64\3\64\3\64\3\65\3\65\3\65\3\65\3\65\3\65\3\66\3\66\3\66\5\66"+
		"\u01c3\n\66\3\66\6\66\u01c6\n\66\r\66\16\66\u01c7\3\67\3\67\3\67\3\67"+
		"\3\67\3\67\3\67\3\67\3\67\5\67\u01d3\n\67\38\58\u01d6\n8\38\68\u01d9\n"+
		"8\r8\168\u01da\39\39\69\u01df\n9\r9\169\u01e0\3:\3:\5:\u01e5\n:\3:\6:"+
		"\u01e8\n:\r:\16:\u01e9\3:\3:\6:\u01ee\n:\r:\16:\u01ef\3;\3;\7;\u01f4\n"+
		";\f;\16;\u01f7\13;\3;\3;\3<\3<\7<\u01fd\n<\f<\16<\u0200\13<\3<\3<\3=\3"+
		"=\3=\3=\3=\3=\3=\3=\3=\6=\u020d\n=\r=\16=\u020e\3>\3>\3>\3>\3>\7>\u0216"+
		"\n>\f>\16>\u0219\13>\3?\3?\3?\3?\3?\5?\u0220\n?\3?\3?\3?\3?\3?\3?\3?\3"+
		"?\3?\7?\u022b\n?\f?\16?\u022e\13?\3@\6@\u0231\n@\r@\16@\u0232\3@\7@\u0236"+
		"\n@\f@\16@\u0239\13@\3@\3@\3A\6A\u023e\nA\rA\16A\u023f\3A\5A\u0243\nA"+
		"\3A\3A\3B\3B\3B\3B\3B\7B\u024c\nB\fB\16B\u024f\13B\3B\3B\3B\3C\3C\3D\5"+
		"D\u0257\nD\3D\3D\5D\u025b\nD\3E\3E\3E\3E\3E\3E\3E\3F\3F\3F\3F\3F\3F\3"+
		"F\3G\3G\3H\3H\3I\3I\3J\3J\3K\3K\3L\3L\3M\3M\3N\3N\3O\3O\6O\u027d\nO\r"+
		"O\16O\u027e\3P\3P\3Q\3Q\3R\3R\3\u024d\2S\3\3\5\4\7\5\t\6\13\7\r\b\17\t"+
		"\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27"+
		"-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W"+
		"-Y.[/]\60_\61a\62c\63e\64g\65i\66k\67m8o9q:s;u<w=y>{?}@\177A\u0081B\u0083"+
		"C\u0085D\u0087E\u0089F\u008bG\u008dH\u008f\2\u0091\2\u0093\2\u0095\2\u0097"+
		"\2\u0099\2\u009b\2\u009d\2\u009f\2\u00a1\2\u00a3\2\3\2\b\3\2$$\3\2//\4"+
		"\2\13\13\"\"\4\2\u00b2\u00b2\u00bc\u00bc\3\2\62;\6\2C\\c|\u00d3\u00d3"+
		"\u00f3\u00f3\u02a2\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13"+
		"\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2"+
		"\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2"+
		"!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3"+
		"\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2"+
		"\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E"+
		"\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2"+
		"\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2"+
		"\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k"+
		"\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\2u\3\2\2\2\2w\3\2"+
		"\2\2\2y\3\2\2\2\2{\3\2\2\2\2}\3\2\2\2\2\177\3\2\2\2\2\u0081\3\2\2\2\2"+
		"\u0083\3\2\2\2\2\u0085\3\2\2\2\2\u0087\3\2\2\2\2\u0089\3\2\2\2\2\u008b"+
		"\3\2\2\2\2\u008d\3\2\2\2\3\u00a5\3\2\2\2\5\u00ad\3\2\2\2\7\u00b1\3\2\2"+
		"\2\t\u00b5\3\2\2\2\13\u00b9\3\2\2\2\r\u00bc\3\2\2\2\17\u00c0\3\2\2\2\21"+
		"\u00c3\3\2\2\2\23\u00c6\3\2\2\2\25\u00cb\3\2\2\2\27\u00d3\3\2\2\2\31\u00dc"+
		"\3\2\2\2\33\u00e3\3\2\2\2\35\u00ec\3\2\2\2\37\u00f6\3\2\2\2!\u00fc\3\2"+
		"\2\2#\u0106\3\2\2\2%\u010f\3\2\2\2\'\u0115\3\2\2\2)\u011e\3\2\2\2+\u0124"+
		"\3\2\2\2-\u012b\3\2\2\2/\u0135\3\2\2\2\61\u0140\3\2\2\2\63\u0149\3\2\2"+
		"\2\65\u014e\3\2\2\2\67\u0150\3\2\2\29\u0152\3\2\2\2;\u0154\3\2\2\2=\u0156"+
		"\3\2\2\2?\u015a\3\2\2\2A\u015d\3\2\2\2C\u015f\3\2\2\2E\u0161\3\2\2\2G"+
		"\u0163\3\2\2\2I\u0165\3\2\2\2K\u0167\3\2\2\2M\u0169\3\2\2\2O\u016c\3\2"+
		"\2\2Q\u0172\3\2\2\2S\u0174\3\2\2\2U\u0176\3\2\2\2W\u017b\3\2\2\2Y\u0180"+
		"\3\2\2\2[\u0188\3\2\2\2]\u0190\3\2\2\2_\u0197\3\2\2\2a\u019e\3\2\2\2c"+
		"\u01a6\3\2\2\2e\u01ae\3\2\2\2g\u01b4\3\2\2\2i\u01b9\3\2\2\2k\u01bf\3\2"+
		"\2\2m\u01d2\3\2\2\2o\u01d5\3\2\2\2q\u01dc\3\2\2\2s\u01e4\3\2\2\2u\u01f1"+
		"\3\2\2\2w\u01fa\3\2\2\2y\u0203\3\2\2\2{\u0210\3\2\2\2}\u021f\3\2\2\2\177"+
		"\u0230\3\2\2\2\u0081\u023d\3\2\2\2\u0083\u0246\3\2\2\2\u0085\u0253\3\2"+
		"\2\2\u0087\u025a\3\2\2\2\u0089\u025c\3\2\2\2\u008b\u0263\3\2\2\2\u008d"+
		"\u026a\3\2\2\2\u008f\u026c\3\2\2\2\u0091\u026e\3\2\2\2\u0093\u0270\3\2"+
		"\2\2\u0095\u0272\3\2\2\2\u0097\u0274\3\2\2\2\u0099\u0276\3\2\2\2\u009b"+
		"\u0278\3\2\2\2\u009d\u027a\3\2\2\2\u009f\u0280\3\2\2\2\u00a1\u0282\3\2"+
		"\2\2\u00a3\u0284\3\2\2\2\u00a5\u00a6\7E\2\2\u00a6\u00a7\7q\2\2\u00a7\u00a8"+
		"\7p\2\2\u00a8\u00a9\7e\2\2\u00a9\u00aa\7g\2\2\u00aa\u00ab\7r\2\2\u00ab"+
		"\u00ac\7v\2\2\u00ac\4\3\2\2\2\u00ad\u00ae\7u\2\2\u00ae\u00af\7w\2\2\u00af"+
		"\u00b0\7d\2\2\u00b0\6\3\2\2\2\u00b1\u00b2\7w\2\2\u00b2\u00b3\7u\2\2\u00b3"+
		"\u00b4\7g\2\2\u00b4\b\3\2\2\2\u00b5\u00b6\7x\2\2\u00b6\u00b7\7c\2\2\u00b7"+
		"\u00b8\7t\2\2\u00b8\n\3\2\2\2\u00b9\u00ba\7c\2\2\u00ba\u00bb\7u\2\2\u00bb"+
		"\f\3\2\2\2\u00bc\u00bd\7j\2\2\u00bd\u00be\7c\2\2\u00be\u00bf\7u\2\2\u00bf"+
		"\16\3\2\2\2\u00c0\u00c1\7q\2\2\u00c1\u00c2\7p\2\2\u00c2\20\3\2\2\2\u00c3"+
		"\u00c4\7k\2\2\u00c4\u00c5\7u\2\2\u00c5\22\3\2\2\2\u00c6\u00c7\7y\2\2\u00c7"+
		"\u00c8\7k\2\2\u00c8\u00c9\7v\2\2\u00c9\u00ca\7j\2\2\u00ca\24\3\2\2\2\u00cb"+
		"\u00cc\7g\2\2\u00cc\u00cd\7z\2\2\u00cd\u00ce\7v\2\2\u00ce\u00cf\7g\2\2"+
		"\u00cf\u00d0\7p\2\2\u00d0\u00d1\7f\2\2\u00d1\u00d2\7u\2\2\u00d2\26\3\2"+
		"\2\2\u00d3\u00d4\7c\2\2\u00d4\u00d5\7d\2\2\u00d5\u00d6\7u\2\2\u00d6\u00d7"+
		"\7v\2\2\u00d7\u00d8\7t\2\2\u00d8\u00d9\7c\2\2\u00d9\u00da\7e\2\2\u00da"+
		"\u00db\7v\2\2\u00db\30\3\2\2\2\u00dc\u00dd\7u\2\2\u00dd\u00de\7k\2\2\u00de"+
		"\u00df\7p\2\2\u00df\u00e0\7i\2\2\u00e0\u00e1\7n\2\2\u00e1\u00e2\7g\2\2"+
		"\u00e2\32\3\2\2\2\u00e3\u00e4\7t\2\2\u00e4\u00e5\7g\2\2\u00e5\u00e6\7"+
		"s\2\2\u00e6\u00e7\7w\2\2\u00e7\u00e8\7k\2\2\u00e8\u00e9\7t\2\2\u00e9\u00ea"+
		"\7g\2\2\u00ea\u00eb\7f\2\2\u00eb\34\3\2\2\2\u00ec\u00ed\7e\2\2\u00ed\u00ee"+
		"\7q\2\2\u00ee\u00ef\7o\2\2\u00ef\u00f0\7r\2\2\u00f0\u00f1\7q\2\2\u00f1"+
		"\u00f2\7p\2\2\u00f2\u00f3\7g\2\2\u00f3\u00f4\7p\2\2\u00f4\u00f5\7v\2\2"+
		"\u00f5\36\3\2\2\2\u00f6\u00f7\7h\2\2\u00f7\u00f8\7c\2\2\u00f8\u00f9\7"+
		"e\2\2\u00f9\u00fa\7g\2\2\u00fa\u00fb\7v\2\2\u00fb \3\2\2\2\u00fc\u00fd"+
		"\7k\2\2\u00fd\u00fe\7p\2\2\u00fe\u00ff\7v\2\2\u00ff\u0100\7g\2\2\u0100"+
		"\u0101\7p\2\2\u0101\u0102\7v\2\2\u0102\u0103\7k\2\2\u0103\u0104\7q\2\2"+
		"\u0104\u0105\7p\2\2\u0105\"\3\2\2\2\u0106\u0107\7v\2\2\u0107\u0108\7g"+
		"\2\2\u0108\u0109\7t\2\2\u0109\u010a\7o\2\2\u010a\u010b\7k\2\2\u010b\u010c"+
		"\7p\2\2\u010c\u010d\7c\2\2\u010d\u010e\7n\2\2\u010e$\3\2\2\2\u010f\u0110"+
		"\7p\2\2\u0110\u0111\7c\2\2\u0111\u0112\7o\2\2\u0112\u0113\7g\2\2\u0113"+
		"\u0114\7f\2\2\u0114&\3\2\2\2\u0115\u0116\7r\2\2\u0116\u0117\7t\2\2\u0117"+
		"\u0118\7q\2\2\u0118\u0119\7r\2\2\u0119\u011a\7g\2\2\u011a\u011b\7t\2\2"+
		"\u011b\u011c\7v\2\2\u011c\u011d\7{\2\2\u011d(\3\2\2\2\u011e\u011f\7n\2"+
		"\2\u011f\u0120\7q\2\2\u0120\u0121\7e\2\2\u0121\u0122\7c\2\2\u0122\u0123"+
		"\7n\2\2\u0123*\3\2\2\2\u0124\u0125\7c\2\2\u0125\u0126\7n\2\2\u0126\u0127"+
		"\7y\2\2\u0127\u0128\7c\2\2\u0128\u0129\7{\2\2\u0129\u012a\7u\2\2\u012a"+
		",\3\2\2\2\u012b\u012c\7c\2\2\u012c\u012d\7f\2\2\u012d\u012e\7f\2\2\u012e"+
		"\u012f\7t\2\2\u012f\u0130\7g\2\2\u0130\u0131\7u\2\2\u0131\u0132\7u\2\2"+
		"\u0132\u0133\7g\2\2\u0133\u0134\7f\2\2\u0134.\3\2\2\2\u0135\u0136\7c\2"+
		"\2\u0136\u0137\7i\2\2\u0137\u0138\7i\2\2\u0138\u0139\7t\2\2\u0139\u013a"+
		"\7g\2\2\u013a\u013b\7i\2\2\u013b\u013c\7c\2\2\u013c\u013d\7v\2\2\u013d"+
		"\u013e\7g\2\2\u013e\u013f\7f\2\2\u013f\60\3\2\2\2\u0140\u0141\7t\2\2\u0141"+
		"\u0142\7g\2\2\u0142\u0143\7c\2\2\u0143\u0144\7f\2\2\u0144\u0145\7q\2\2"+
		"\u0145\u0146\7p\2\2\u0146\u0147\7n\2\2\u0147\u0148\7{\2\2\u0148\62\3\2"+
		"\2\2\u0149\u014a\7t\2\2\u014a\u014b\7q\2\2\u014b\u014c\7q\2\2\u014c\u014d"+
		"\7v\2\2\u014d\64\3\2\2\2\u014e\u014f\7*\2\2\u014f\66\3\2\2\2\u0150\u0151"+
		"\7+\2\2\u01518\3\2\2\2\u0152\u0153\7]\2\2\u0153:\3\2\2\2\u0154\u0155\7"+
		"_\2\2\u0155<\3\2\2\2\u0156\u0157\7\60\2\2\u0157\u0158\7\60\2\2\u0158\u0159"+
		"\7\60\2\2\u0159>\3\2\2\2\u015a\u015b\7@\2\2\u015b\u015c\b \2\2\u015c@"+
		"\3\2\2\2\u015d\u015e\7>\2\2\u015eB\3\2\2\2\u015f\u0160\7(\2\2\u0160D\3"+
		"\2\2\2\u0161\u0162\7<\2\2\u0162F\3\2\2\2\u0163\u0164\7.\2\2\u0164H\3\2"+
		"\2\2\u0165\u0166\7\60\2\2\u0166J\3\2\2\2\u0167\u0168\7?\2\2\u0168L\3\2"+
		"\2\2\u0169\u016a\7$\2\2\u016aN\3\2\2\2\u016b\u016d\7=\2\2\u016c\u016b"+
		"\3\2\2\2\u016d\u016e\3\2\2\2\u016e\u016c\3\2\2\2\u016e\u016f\3\2\2\2\u016f"+
		"\u0170\3\2\2\2\u0170\u0171\b(\3\2\u0171P\3\2\2\2\u0172\u0173\7,\2\2\u0173"+
		"R\3\2\2\2\u0174\u0175\7-\2\2\u0175T\3\2\2\2\u0176\u0177\7y\2\2\u0177\u0178"+
		"\7q\2\2\u0178\u0179\7t\2\2\u0179\u017a\7f\2\2\u017aV\3\2\2\2\u017b\u017c"+
		"\7h\2\2\u017c\u017d\7k\2\2\u017d\u017e\7n\2\2\u017e\u017f\7g\2\2\u017f"+
		"X\3\2\2\2\u0180\u0181\7k\2\2\u0181\u0182\7p\2\2\u0182\u0183\7v\2\2\u0183"+
		"\u0184\7g\2\2\u0184\u0185\7i\2\2\u0185\u0186\7g\2\2\u0186\u0187\7t\2\2"+
		"\u0187Z\3\2\2\2\u0188\u0189\7p\2\2\u0189\u018a\7c\2\2\u018a\u018b\7v\2"+
		"\2\u018b\u018c\7w\2\2\u018c\u018d\7t\2\2\u018d\u018e\7c\2\2\u018e\u018f"+
		"\7n\2\2\u018f\\\3\2\2\2\u0190\u0191\7f\2\2\u0191\u0192\7q\2\2\u0192\u0193"+
		"\7w\2\2\u0193\u0194\7d\2\2\u0194\u0195\7n\2\2\u0195\u0196\7g\2\2\u0196"+
		"^\3\2\2\2\u0197\u0198\7u\2\2\u0198\u0199\7v\2\2\u0199\u019a\7t\2\2\u019a"+
		"\u019b\7k\2\2\u019b\u019c\7p\2\2\u019c\u019d\7i\2\2\u019d`\3\2\2\2\u019e"+
		"\u019f\7d\2\2\u019f\u01a0\7q\2\2\u01a0\u01a1\7q\2\2\u01a1\u01a2\7n\2\2"+
		"\u01a2\u01a3\7g\2\2\u01a3\u01a4\7c\2\2\u01a4\u01a5\7p\2\2\u01a5b\3\2\2"+
		"\2\u01a6\u01a7\7o\2\2\u01a7\u01a8\7g\2\2\u01a8\u01a9\7c\2\2\u01a9\u01aa"+
		"\7u\2\2\u01aa\u01ab\7w\2\2\u01ab\u01ac\7t\2\2\u01ac\u01ad\7g\2\2\u01ad"+
		"d\3\2\2\2\u01ae\u01af\7t\2\2\u01af\u01b0\7c\2\2\u01b0\u01b1\7v\2\2\u01b1"+
		"\u01b2\7k\2\2\u01b2\u01b3\7q\2\2\u01b3f\3\2\2\2\u01b4\u01b5\7f\2\2\u01b5"+
		"\u01b6\7c\2\2\u01b6\u01b7\7v\2\2\u01b7\u01b8\7g\2\2\u01b8h\3\2\2\2\u01b9"+
		"\u01ba\7g\2\2\u01ba\u01bb\7o\2\2\u01bb\u01bc\7r\2\2\u01bc\u01bd\7v\2\2"+
		"\u01bd\u01be\7{\2\2\u01bej\3\2\2\2\u01bf\u01c2\7G\2\2\u01c0\u01c3\5S*"+
		"\2\u01c1\u01c3\5\u009bN\2\u01c2\u01c0\3\2\2\2\u01c2\u01c1\3\2\2\2\u01c2"+
		"\u01c3\3\2\2\2\u01c3\u01c5\3\2\2\2\u01c4\u01c6\5\u00a1Q\2\u01c5\u01c4"+
		"\3\2\2\2\u01c6\u01c7\3\2\2\2\u01c7\u01c5\3\2\2\2\u01c7\u01c8\3\2\2\2\u01c8"+
		"l\3\2\2\2\u01c9\u01ca\7v\2\2\u01ca\u01cb\7t\2\2\u01cb\u01cc\7w\2\2\u01cc"+
		"\u01d3\7g\2\2\u01cd\u01ce\7h\2\2\u01ce\u01cf\7c\2\2\u01cf\u01d0\7n\2\2"+
		"\u01d0\u01d1\7u\2\2\u01d1\u01d3\7g\2\2\u01d2\u01c9\3\2\2\2\u01d2\u01cd"+
		"\3\2\2\2\u01d3n\3\2\2\2\u01d4\u01d6\5S*\2\u01d5\u01d4\3\2\2\2\u01d5\u01d6"+
		"\3\2\2\2\u01d6\u01d8\3\2\2\2\u01d7\u01d9\5\u00a1Q\2\u01d8\u01d7\3\2\2"+
		"\2\u01d9\u01da\3\2\2\2\u01da\u01d8\3\2\2\2\u01da\u01db\3\2\2\2\u01dbp"+
		"\3\2\2\2\u01dc\u01de\5\u009bN\2\u01dd\u01df\5\u00a1Q\2\u01de\u01dd\3\2"+
		"\2\2\u01df\u01e0\3\2\2\2\u01e0\u01de\3\2\2\2\u01e0\u01e1\3\2\2\2\u01e1"+
		"r\3\2\2\2\u01e2\u01e5\5S*\2\u01e3\u01e5\5\u009bN\2\u01e4\u01e2\3\2\2\2"+
		"\u01e4\u01e3\3\2\2\2\u01e4\u01e5\3\2\2\2\u01e5\u01e7\3\2\2\2\u01e6\u01e8"+
		"\5\u00a1Q\2\u01e7\u01e6\3\2\2\2\u01e8\u01e9\3\2\2\2\u01e9\u01e7\3\2\2"+
		"\2\u01e9\u01ea\3\2\2\2\u01ea\u01eb\3\2\2\2\u01eb\u01ed\5I%\2\u01ec\u01ee"+
		"\5\u00a1Q\2\u01ed\u01ec\3\2\2\2\u01ee\u01ef\3\2\2\2\u01ef\u01ed\3\2\2"+
		"\2\u01ef\u01f0\3\2\2\2\u01f0t\3\2\2\2\u01f1\u01f5\5M\'\2\u01f2\u01f4\n"+
		"\2\2\2\u01f3\u01f2\3\2\2\2\u01f4\u01f7\3\2\2\2\u01f5\u01f3\3\2\2\2\u01f5"+
		"\u01f6\3\2\2\2\u01f6\u01f8\3\2\2\2\u01f7\u01f5\3\2\2\2\u01f8\u01f9\5M"+
		"\'\2\u01f9v\3\2\2\2\u01fa\u01fe\5\u009dO\2\u01fb\u01fd\n\3\2\2\u01fc\u01fb"+
		"\3\2\2\2\u01fd\u0200\3\2\2\2\u01fe\u01fc\3\2\2\2\u01fe\u01ff\3\2\2\2\u01ff"+
		"\u0201\3\2\2\2\u0200\u01fe\3\2\2\2\u0201\u0202\5\u009dO\2\u0202x\3\2\2"+
		"\2\u0203\u0204\5C\"\2\u0204\u0205\5\u00a1Q\2\u0205\u0206\5\u00a1Q\2\u0206"+
		"\u020c\5\u00a1Q\2\u0207\u0208\5I%\2\u0208\u0209\5\u00a1Q\2\u0209\u020a"+
		"\5\u00a1Q\2\u020a\u020b\5\u00a1Q\2\u020b\u020d\3\2\2\2\u020c\u0207\3\2"+
		"\2\2\u020d\u020e\3\2\2\2\u020e\u020c\3\2\2\2\u020e\u020f\3\2\2\2\u020f"+
		"z\3\2\2\2\u0210\u0217\5\u00a3R\2\u0211\u0216\5\u00a1Q\2\u0212\u0216\5"+
		"\u00a3R\2\u0213\u0216\5\u009bN\2\u0214\u0216\5\u009fP\2\u0215\u0211\3"+
		"\2\2\2\u0215\u0212\3\2\2\2\u0215\u0213\3\2\2\2\u0215\u0214\3\2\2\2\u0216"+
		"\u0219\3\2\2\2\u0217\u0215\3\2\2\2\u0217\u0218\3\2\2\2\u0218|\3\2\2\2"+
		"\u0219\u0217\3\2\2\2\u021a\u0220\5\u00a3R\2\u021b\u0220\5\u0093J\2\u021c"+
		"\u0220\5\u008fH\2\u021d\u0220\5\u0091I\2\u021e\u0220\5\u0095K\2\u021f"+
		"\u021a\3\2\2\2\u021f\u021b\3\2\2\2\u021f\u021c\3\2\2\2\u021f\u021d\3\2"+
		"\2\2\u021f\u021e\3\2\2\2\u0220\u022c\3\2\2\2\u0221\u022b\5\u009fP\2\u0222"+
		"\u022b\5\u0097L\2\u0223\u022b\5\u0099M\2\u0224\u022b\5\u0093J\2\u0225"+
		"\u022b\5\u008fH\2\u0226\u022b\5\u0091I\2\u0227\u022b\5\u0095K\2\u0228"+
		"\u022b\5\u00a3R\2\u0229\u022b\5\u00a1Q\2\u022a\u0221\3\2\2\2\u022a\u0222"+
		"\3\2\2\2\u022a\u0223\3\2\2\2\u022a\u0224\3\2\2\2\u022a\u0225\3\2\2\2\u022a"+
		"\u0226\3\2\2\2\u022a\u0227\3\2\2\2\u022a\u0228\3\2\2\2\u022a\u0229\3\2"+
		"\2\2\u022b\u022e\3\2\2\2\u022c\u022a\3\2\2\2\u022c\u022d\3\2\2\2\u022d"+
		"~\3\2\2\2\u022e\u022c\3\2\2\2\u022f\u0231\5\u0087D\2\u0230\u022f\3\2\2"+
		"\2\u0231\u0232\3\2\2\2\u0232\u0230\3\2\2\2\u0232\u0233\3\2\2\2\u0233\u0237"+
		"\3\2\2\2\u0234\u0236\5\u0085C\2\u0235\u0234\3\2\2\2\u0236\u0239\3\2\2"+
		"\2\u0237\u0235\3\2\2\2\u0237\u0238\3\2\2\2\u0238\u023a\3\2\2\2\u0239\u0237"+
		"\3\2\2\2\u023a\u023b\b@\4\2\u023b\u0080\3\2\2\2\u023c\u023e\5\u0085C\2"+
		"\u023d\u023c\3\2\2\2\u023e\u023f\3\2\2\2\u023f\u023d\3\2\2\2\u023f\u0240"+
		"\3\2\2\2\u0240\u0242\3\2\2\2\u0241\u0243\7\2\2\3\u0242\u0241\3\2\2\2\u0242"+
		"\u0243\3\2\2\2\u0243\u0244\3\2\2\2\u0244\u0245\bA\5\2\u0245\u0082\3\2"+
		"\2\2\u0246\u0247\7f\2\2\u0247\u0248\7g\2\2\u0248\u0249\7h\2\2\u0249\u024d"+
		"\3\2\2\2\u024a\u024c\13\2\2\2\u024b\u024a\3\2\2\2\u024c\u024f\3\2\2\2"+
		"\u024d\u024e\3\2\2\2\u024d\u024b\3\2\2\2\u024e\u0250\3\2\2\2\u024f\u024d"+
		"\3\2\2\2\u0250\u0251\5\u0087D\2\u0251\u0252\bB\6\2\u0252\u0084\3\2\2\2"+
		"\u0253\u0254\t\4\2\2\u0254\u0086\3\2\2\2\u0255\u0257\7\17\2\2\u0256\u0255"+
		"\3\2\2\2\u0256\u0257\3\2\2\2\u0257\u0258\3\2\2\2\u0258\u025b\7\f\2\2\u0259"+
		"\u025b\7\17\2\2\u025a\u0256\3\2\2\2\u025a\u0259\3\2\2\2\u025b\u0088\3"+
		"\2\2\2\u025c\u025d\7k\2\2\u025d\u025e\7p\2\2\u025e\u025f\7f\2\2\u025f"+
		"\u0260\7g\2\2\u0260\u0261\7p\2\2\u0261\u0262\7v\2\2\u0262\u008a\3\2\2"+
		"\2\u0263\u0264\7f\2\2\u0264\u0265\7g\2\2\u0265\u0266\7f\2\2\u0266\u0267"+
		"\7g\2\2\u0267\u0268\7p\2\2\u0268\u0269\7v\2\2\u0269\u008c\3\2\2\2\u026a"+
		"\u026b\13\2\2\2\u026b\u008e\3\2\2\2\u026c\u026d\7&\2\2\u026d\u0090\3\2"+
		"\2\2\u026e\u026f\7\u20ae\2\2\u026f\u0092\3\2\2\2\u0270\u0271\7\'\2\2\u0271"+
		"\u0094\3\2\2\2\u0272\u0273\t\5\2\2\u0273\u0096\3\2\2\2\u0274\u0275\7\u00b9"+
		"\2\2\u0275\u0098\3\2\2\2\u0276\u0277\7\61\2\2\u0277\u009a\3\2\2\2\u0278"+
		"\u0279\7/\2\2\u0279\u009c\3\2\2\2\u027a\u027c\5\u009bN\2\u027b\u027d\5"+
		"\u009bN\2\u027c\u027b\3\2\2\2\u027d\u027e\3\2\2\2\u027e\u027c\3\2\2\2"+
		"\u027e\u027f\3\2\2\2\u027f\u009e\3\2\2\2\u0280\u0281\7a\2\2\u0281\u00a0"+
		"\3\2\2\2\u0282\u0283\t\6\2\2\u0283\u00a2\3\2\2\2\u0284\u0285\t\7\2\2\u0285"+
		"\u00a4\3\2\2\2\35\2\u016e\u01c2\u01c7\u01d2\u01d5\u01da\u01e0\u01e4\u01e9"+
		"\u01ef\u01f5\u01fe\u020e\u0215\u0217\u021f\u022a\u022c\u0232\u0237\u023f"+
		"\u0242\u024d\u0256\u025a\u027e\7\3 \2\3(\3\3@\4\2\3\2\3B\5";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}