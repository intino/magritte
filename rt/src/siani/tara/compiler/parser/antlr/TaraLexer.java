// Generated from /Users/oroncal/workspace/tara/rt/src/siani/tara/compiler/parser/antlr/TaraLexer.g4 by ANTLR 4.4.1-dev

package siani.tara.compiler.parser.antlr;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TaraLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.4.1-dev", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		METAIDENTIFIER=1, SUB=2, USE=3, BOX=4, VAR=5, AS=6, HAS=7, ON=8, IS=9, 
		WITH=10, EXTENDS=11, ABSTRACT=12, SINGLE=13, REQUIRED=14, COMPONENT=15, 
		FACET=16, INTENTION=17, TERMINAL=18, NAMED=19, PROPERTY=20, UNIVERSAL=21, 
		ALWAYS=22, ADDRESSED=23, AGGREGATED=24, LEFT_PARENTHESIS=25, RIGHT_PARENTHESIS=26, 
		LEFT_SQUARE=27, RIGHT_SQUARE=28, LIST=29, INLINE=30, CLOSE_INLINE=31, 
		AMPERSAND=32, DOLLAR=33, EURO=34, PERCENTAGE=35, GRADE=36, COLON=37, COMMA=38, 
		DOT=39, EQUALS=40, APHOSTROPHE=41, SEMICOLON=42, STAR=43, POSITIVE=44, 
		DASH=45, DASHES=46, UNDERDASH=47, WORD=48, RESOURCE=49, COORDINATE_TYPE=50, 
		INT_TYPE=51, NATURAL_TYPE=52, DOUBLE_TYPE=53, STRING_TYPE=54, BOOLEAN_TYPE=55, 
		DATE_TYPE=56, EMPTY=57, BOOLEAN_VALUE=58, NATURAL_VALUE=59, NEGATIVE_VALUE=60, 
		DOUBLE_VALUE=61, STRING_VALUE=62, STRING_MULTILINE_VALUE_KEY=63, ADDRESS_VALUE=64, 
		DATE_VALUE=65, COORDINATE_VALUE=66, IDENTIFIER=67, DIGIT=68, LETTER=69, 
		NEWLINE=70, SPACES=71, DOC=72, SP=73, NL=74, NEW_LINE_INDENT=75, DEDENT=76, 
		UNKNOWN_TOKEN=77;
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
		"'F'", "'G'", "'H'", "'I'", "'J'", "'K'", "'L'", "'M'"
	};
	public static final String[] ruleNames = {
		"METAIDENTIFIER", "SUB", "USE", "BOX", "VAR", "AS", "HAS", "ON", "IS", 
		"WITH", "EXTENDS", "ABSTRACT", "SINGLE", "REQUIRED", "COMPONENT", "FACET", 
		"INTENTION", "TERMINAL", "NAMED", "PROPERTY", "UNIVERSAL", "ALWAYS", "ADDRESSED", 
		"AGGREGATED", "LEFT_PARENTHESIS", "RIGHT_PARENTHESIS", "LEFT_SQUARE", 
		"RIGHT_SQUARE", "LIST", "INLINE", "CLOSE_INLINE", "AMPERSAND", "DOLLAR", 
		"EURO", "PERCENTAGE", "GRADE", "COLON", "COMMA", "DOT", "EQUALS", "APHOSTROPHE", 
		"SEMICOLON", "STAR", "POSITIVE", "DASH", "DASHES", "UNDERDASH", "WORD", 
		"RESOURCE", "COORDINATE_TYPE", "INT_TYPE", "NATURAL_TYPE", "DOUBLE_TYPE", 
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
		case 29: INLINE_action((RuleContext)_localctx, actionIndex); break;
		case 41: SEMICOLON_action((RuleContext)_localctx, actionIndex); break;
		case 69: NEWLINE_action((RuleContext)_localctx, actionIndex); break;
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2O\u0270\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
		"\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3"+
		"\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3"+
		"\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26"+
		"\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30"+
		"\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31"+
		"\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36"+
		"\3\36\3\36\3\36\3\37\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\3"+
		"&\3&\3\'\3\'\3(\3(\3)\3)\3*\3*\3+\6+\u0167\n+\r+\16+\u0168\3+\3+\3,\3"+
		",\3-\3-\3.\3.\3/\3/\6/\u0175\n/\r/\16/\u0176\3\60\3\60\3\61\3\61\3\61"+
		"\3\61\3\61\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\63\3\63\3\63"+
		"\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\64\3\64\3\64\3\64\3\64\3\64"+
		"\3\64\3\64\3\65\3\65\3\65\3\65\3\65\3\65\3\65\3\65\3\66\3\66\3\66\3\66"+
		"\3\66\3\66\3\66\3\67\3\67\3\67\3\67\3\67\3\67\3\67\38\38\38\38\38\38\3"+
		"8\38\39\39\39\39\39\3:\3:\3:\3:\3:\3:\3;\3;\3;\3;\3;\3;\3;\3;\3;\5;\u01ce"+
		"\n;\3<\5<\u01d1\n<\3<\6<\u01d4\n<\r<\16<\u01d5\3=\3=\6=\u01da\n=\r=\16"+
		"=\u01db\3>\3>\5>\u01e0\n>\3>\6>\u01e3\n>\r>\16>\u01e4\3>\3>\6>\u01e9\n"+
		">\r>\16>\u01ea\3?\3?\7?\u01ef\n?\f?\16?\u01f2\13?\3?\3?\3@\3@\7@\u01f8"+
		"\n@\f@\16@\u01fb\13@\3@\3@\3A\3A\3A\3A\3A\3A\3A\3A\3A\6A\u0208\nA\rA\16"+
		"A\u0209\3B\3B\3B\6B\u020f\nB\rB\16B\u0210\3B\3B\3B\5B\u0216\nB\3C\3C\3"+
		"C\3C\5C\u021c\nC\3C\3C\3C\3C\5C\u0222\nC\6C\u0224\nC\rC\16C\u0225\3C\3"+
		"C\3D\3D\3D\3D\3D\7D\u022f\nD\fD\16D\u0232\13D\3E\3E\3F\3F\3G\6G\u0239"+
		"\nG\rG\16G\u023a\3G\7G\u023e\nG\fG\16G\u0241\13G\3G\3G\3H\6H\u0246\nH"+
		"\rH\16H\u0247\3H\5H\u024b\nH\3H\3H\3I\3I\7I\u0251\nI\fI\16I\u0254\13I"+
		"\3I\3I\3J\3J\3K\5K\u025b\nK\3K\3K\5K\u025f\nK\3L\3L\3L\3L\3L\3L\3L\3M"+
		"\3M\3M\3M\3M\3M\3M\3N\3N\3\u0252\2O\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n"+
		"\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30"+
		"/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.["+
		"/]\60_\61a\62c\63e\64g\65i\66k\67m8o9q:s;u<w=y>{?}@\177A\u0081B\u0083"+
		"C\u0085D\u0087E\u0089F\u008bG\u008dH\u008fI\u0091J\u0093K\u0095L\u0097"+
		"M\u0099N\u009bO\3\2\7\3\2))\3\2//\3\2\62;\4\2C\\c|\4\2\13\13\"\"\u028e"+
		"\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2"+
		"\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2"+
		"\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2"+
		"\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2"+
		"\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3"+
		"\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2"+
		"\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2"+
		"U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3"+
		"\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2"+
		"\2\2o\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\2u\3\2\2\2\2w\3\2\2\2\2y\3\2\2\2\2"+
		"{\3\2\2\2\2}\3\2\2\2\2\177\3\2\2\2\2\u0081\3\2\2\2\2\u0083\3\2\2\2\2\u0085"+
		"\3\2\2\2\2\u0087\3\2\2\2\2\u0089\3\2\2\2\2\u008b\3\2\2\2\2\u008d\3\2\2"+
		"\2\2\u008f\3\2\2\2\2\u0091\3\2\2\2\2\u0093\3\2\2\2\2\u0095\3\2\2\2\2\u0097"+
		"\3\2\2\2\2\u0099\3\2\2\2\2\u009b\3\2\2\2\3\u009d\3\2\2\2\5\u00a5\3\2\2"+
		"\2\7\u00a9\3\2\2\2\t\u00ad\3\2\2\2\13\u00b1\3\2\2\2\r\u00b5\3\2\2\2\17"+
		"\u00b8\3\2\2\2\21\u00bc\3\2\2\2\23\u00bf\3\2\2\2\25\u00c2\3\2\2\2\27\u00c7"+
		"\3\2\2\2\31\u00cf\3\2\2\2\33\u00d8\3\2\2\2\35\u00df\3\2\2\2\37\u00e8\3"+
		"\2\2\2!\u00f2\3\2\2\2#\u00f8\3\2\2\2%\u0102\3\2\2\2\'\u010b\3\2\2\2)\u0111"+
		"\3\2\2\2+\u011a\3\2\2\2-\u0124\3\2\2\2/\u012b\3\2\2\2\61\u0135\3\2\2\2"+
		"\63\u0140\3\2\2\2\65\u0142\3\2\2\2\67\u0144\3\2\2\29\u0146\3\2\2\2;\u0148"+
		"\3\2\2\2=\u014c\3\2\2\2?\u014f\3\2\2\2A\u0151\3\2\2\2C\u0153\3\2\2\2E"+
		"\u0155\3\2\2\2G\u0157\3\2\2\2I\u0159\3\2\2\2K\u015b\3\2\2\2M\u015d\3\2"+
		"\2\2O\u015f\3\2\2\2Q\u0161\3\2\2\2S\u0163\3\2\2\2U\u0166\3\2\2\2W\u016c"+
		"\3\2\2\2Y\u016e\3\2\2\2[\u0170\3\2\2\2]\u0172\3\2\2\2_\u0178\3\2\2\2a"+
		"\u017a\3\2\2\2c\u017f\3\2\2\2e\u0188\3\2\2\2g\u0193\3\2\2\2i\u019b\3\2"+
		"\2\2k\u01a3\3\2\2\2m\u01aa\3\2\2\2o\u01b1\3\2\2\2q\u01b9\3\2\2\2s\u01be"+
		"\3\2\2\2u\u01cd\3\2\2\2w\u01d0\3\2\2\2y\u01d7\3\2\2\2{\u01df\3\2\2\2}"+
		"\u01ec\3\2\2\2\177\u01f5\3\2\2\2\u0081\u01fe\3\2\2\2\u0083\u0215\3\2\2"+
		"\2\u0085\u0217\3\2\2\2\u0087\u0229\3\2\2\2\u0089\u0233\3\2\2\2\u008b\u0235"+
		"\3\2\2\2\u008d\u0238\3\2\2\2\u008f\u0245\3\2\2\2\u0091\u024e\3\2\2\2\u0093"+
		"\u0257\3\2\2\2\u0095\u025e\3\2\2\2\u0097\u0260\3\2\2\2\u0099\u0267\3\2"+
		"\2\2\u009b\u026e\3\2\2\2\u009d\u009e\7E\2\2\u009e\u009f\7q\2\2\u009f\u00a0"+
		"\7p\2\2\u00a0\u00a1\7e\2\2\u00a1\u00a2\7g\2\2\u00a2\u00a3\7r\2\2\u00a3"+
		"\u00a4\7v\2\2\u00a4\4\3\2\2\2\u00a5\u00a6\7u\2\2\u00a6\u00a7\7w\2\2\u00a7"+
		"\u00a8\7d\2\2\u00a8\6\3\2\2\2\u00a9\u00aa\7w\2\2\u00aa\u00ab\7u\2\2\u00ab"+
		"\u00ac\7g\2\2\u00ac\b\3\2\2\2\u00ad\u00ae\7d\2\2\u00ae\u00af\7q\2\2\u00af"+
		"\u00b0\7z\2\2\u00b0\n\3\2\2\2\u00b1\u00b2\7x\2\2\u00b2\u00b3\7c\2\2\u00b3"+
		"\u00b4\7t\2\2\u00b4\f\3\2\2\2\u00b5\u00b6\7c\2\2\u00b6\u00b7\7u\2\2\u00b7"+
		"\16\3\2\2\2\u00b8\u00b9\7j\2\2\u00b9\u00ba\7c\2\2\u00ba\u00bb\7u\2\2\u00bb"+
		"\20\3\2\2\2\u00bc\u00bd\7q\2\2\u00bd\u00be\7p\2\2\u00be\22\3\2\2\2\u00bf"+
		"\u00c0\7k\2\2\u00c0\u00c1\7u\2\2\u00c1\24\3\2\2\2\u00c2\u00c3\7y\2\2\u00c3"+
		"\u00c4\7k\2\2\u00c4\u00c5\7v\2\2\u00c5\u00c6\7j\2\2\u00c6\26\3\2\2\2\u00c7"+
		"\u00c8\7g\2\2\u00c8\u00c9\7z\2\2\u00c9\u00ca\7v\2\2\u00ca\u00cb\7g\2\2"+
		"\u00cb\u00cc\7p\2\2\u00cc\u00cd\7f\2\2\u00cd\u00ce\7u\2\2\u00ce\30\3\2"+
		"\2\2\u00cf\u00d0\7c\2\2\u00d0\u00d1\7d\2\2\u00d1\u00d2\7u\2\2\u00d2\u00d3"+
		"\7v\2\2\u00d3\u00d4\7t\2\2\u00d4\u00d5\7c\2\2\u00d5\u00d6\7e\2\2\u00d6"+
		"\u00d7\7v\2\2\u00d7\32\3\2\2\2\u00d8\u00d9\7u\2\2\u00d9\u00da\7k\2\2\u00da"+
		"\u00db\7p\2\2\u00db\u00dc\7i\2\2\u00dc\u00dd\7n\2\2\u00dd\u00de\7g\2\2"+
		"\u00de\34\3\2\2\2\u00df\u00e0\7t\2\2\u00e0\u00e1\7g\2\2\u00e1\u00e2\7"+
		"s\2\2\u00e2\u00e3\7w\2\2\u00e3\u00e4\7k\2\2\u00e4\u00e5\7t\2\2\u00e5\u00e6"+
		"\7g\2\2\u00e6\u00e7\7f\2\2\u00e7\36\3\2\2\2\u00e8\u00e9\7e\2\2\u00e9\u00ea"+
		"\7q\2\2\u00ea\u00eb\7o\2\2\u00eb\u00ec\7r\2\2\u00ec\u00ed\7q\2\2\u00ed"+
		"\u00ee\7p\2\2\u00ee\u00ef\7g\2\2\u00ef\u00f0\7p\2\2\u00f0\u00f1\7v\2\2"+
		"\u00f1 \3\2\2\2\u00f2\u00f3\7h\2\2\u00f3\u00f4\7c\2\2\u00f4\u00f5\7e\2"+
		"\2\u00f5\u00f6\7g\2\2\u00f6\u00f7\7v\2\2\u00f7\"\3\2\2\2\u00f8\u00f9\7"+
		"k\2\2\u00f9\u00fa\7p\2\2\u00fa\u00fb\7v\2\2\u00fb\u00fc\7g\2\2\u00fc\u00fd"+
		"\7p\2\2\u00fd\u00fe\7v\2\2\u00fe\u00ff\7k\2\2\u00ff\u0100\7q\2\2\u0100"+
		"\u0101\7p\2\2\u0101$\3\2\2\2\u0102\u0103\7v\2\2\u0103\u0104\7g\2\2\u0104"+
		"\u0105\7t\2\2\u0105\u0106\7o\2\2\u0106\u0107\7k\2\2\u0107\u0108\7p\2\2"+
		"\u0108\u0109\7c\2\2\u0109\u010a\7n\2\2\u010a&\3\2\2\2\u010b\u010c\7p\2"+
		"\2\u010c\u010d\7c\2\2\u010d\u010e\7o\2\2\u010e\u010f\7g\2\2\u010f\u0110"+
		"\7f\2\2\u0110(\3\2\2\2\u0111\u0112\7r\2\2\u0112\u0113\7t\2\2\u0113\u0114"+
		"\7q\2\2\u0114\u0115\7r\2\2\u0115\u0116\7g\2\2\u0116\u0117\7t\2\2\u0117"+
		"\u0118\7v\2\2\u0118\u0119\7{\2\2\u0119*\3\2\2\2\u011a\u011b\7w\2\2\u011b"+
		"\u011c\7p\2\2\u011c\u011d\7k\2\2\u011d\u011e\7x\2\2\u011e\u011f\7g\2\2"+
		"\u011f\u0120\7t\2\2\u0120\u0121\7u\2\2\u0121\u0122\7c\2\2\u0122\u0123"+
		"\7n\2\2\u0123,\3\2\2\2\u0124\u0125\7c\2\2\u0125\u0126\7n\2\2\u0126\u0127"+
		"\7y\2\2\u0127\u0128\7c\2\2\u0128\u0129\7{\2\2\u0129\u012a\7u\2\2\u012a"+
		".\3\2\2\2\u012b\u012c\7c\2\2\u012c\u012d\7f\2\2\u012d\u012e\7f\2\2\u012e"+
		"\u012f\7t\2\2\u012f\u0130\7g\2\2\u0130\u0131\7u\2\2\u0131\u0132\7u\2\2"+
		"\u0132\u0133\7g\2\2\u0133\u0134\7f\2\2\u0134\60\3\2\2\2\u0135\u0136\7"+
		"c\2\2\u0136\u0137\7i\2\2\u0137\u0138\7i\2\2\u0138\u0139\7t\2\2\u0139\u013a"+
		"\7g\2\2\u013a\u013b\7i\2\2\u013b\u013c\7c\2\2\u013c\u013d\7v\2\2\u013d"+
		"\u013e\7g\2\2\u013e\u013f\7f\2\2\u013f\62\3\2\2\2\u0140\u0141\7*\2\2\u0141"+
		"\64\3\2\2\2\u0142\u0143\7+\2\2\u0143\66\3\2\2\2\u0144\u0145\7]\2\2\u0145"+
		"8\3\2\2\2\u0146\u0147\7_\2\2\u0147:\3\2\2\2\u0148\u0149\7\60\2\2\u0149"+
		"\u014a\7\60\2\2\u014a\u014b\7\60\2\2\u014b<\3\2\2\2\u014c\u014d\7@\2\2"+
		"\u014d\u014e\b\37\2\2\u014e>\3\2\2\2\u014f\u0150\7>\2\2\u0150@\3\2\2\2"+
		"\u0151\u0152\7(\2\2\u0152B\3\2\2\2\u0153\u0154\7&\2\2\u0154D\3\2\2\2\u0155"+
		"\u0156\7\u20ae\2\2\u0156F\3\2\2\2\u0157\u0158\7\'\2\2\u0158H\3\2\2\2\u0159"+
		"\u015a\7\u00bc\2\2\u015aJ\3\2\2\2\u015b\u015c\7<\2\2\u015cL\3\2\2\2\u015d"+
		"\u015e\7.\2\2\u015eN\3\2\2\2\u015f\u0160\7\60\2\2\u0160P\3\2\2\2\u0161"+
		"\u0162\7?\2\2\u0162R\3\2\2\2\u0163\u0164\7)\2\2\u0164T\3\2\2\2\u0165\u0167"+
		"\7=\2\2\u0166\u0165\3\2\2\2\u0167\u0168\3\2\2\2\u0168\u0166\3\2\2\2\u0168"+
		"\u0169\3\2\2\2\u0169\u016a\3\2\2\2\u016a\u016b\b+\3\2\u016bV\3\2\2\2\u016c"+
		"\u016d\7,\2\2\u016dX\3\2\2\2\u016e\u016f\7-\2\2\u016fZ\3\2\2\2\u0170\u0171"+
		"\7/\2\2\u0171\\\3\2\2\2\u0172\u0174\5[.\2\u0173\u0175\5[.\2\u0174\u0173"+
		"\3\2\2\2\u0175\u0176\3\2\2\2\u0176\u0174\3\2\2\2\u0176\u0177\3\2\2\2\u0177"+
		"^\3\2\2\2\u0178\u0179\7a\2\2\u0179`\3\2\2\2\u017a\u017b\7y\2\2\u017b\u017c"+
		"\7q\2\2\u017c\u017d\7t\2\2\u017d\u017e\7f\2\2\u017eb\3\2\2\2\u017f\u0180"+
		"\7t\2\2\u0180\u0181\7g\2\2\u0181\u0182\7u\2\2\u0182\u0183\7q\2\2\u0183"+
		"\u0184\7w\2\2\u0184\u0185\7t\2\2\u0185\u0186\7e\2\2\u0186\u0187\7g\2\2"+
		"\u0187d\3\2\2\2\u0188\u0189\7e\2\2\u0189\u018a\7q\2\2\u018a\u018b\7q\2"+
		"\2\u018b\u018c\7t\2\2\u018c\u018d\7f\2\2\u018d\u018e\7k\2\2\u018e\u018f"+
		"\7p\2\2\u018f\u0190\7c\2\2\u0190\u0191\7v\2\2\u0191\u0192\7g\2\2\u0192"+
		"f\3\2\2\2\u0193\u0194\7k\2\2\u0194\u0195\7p\2\2\u0195\u0196\7v\2\2\u0196"+
		"\u0197\7g\2\2\u0197\u0198\7i\2\2\u0198\u0199\7g\2\2\u0199\u019a\7t\2\2"+
		"\u019ah\3\2\2\2\u019b\u019c\7p\2\2\u019c\u019d\7c\2\2\u019d\u019e\7v\2"+
		"\2\u019e\u019f\7w\2\2\u019f\u01a0\7t\2\2\u01a0\u01a1\7c\2\2\u01a1\u01a2"+
		"\7n\2\2\u01a2j\3\2\2\2\u01a3\u01a4\7f\2\2\u01a4\u01a5\7q\2\2\u01a5\u01a6"+
		"\7w\2\2\u01a6\u01a7\7d\2\2\u01a7\u01a8\7n\2\2\u01a8\u01a9\7g\2\2\u01a9"+
		"l\3\2\2\2\u01aa\u01ab\7u\2\2\u01ab\u01ac\7v\2\2\u01ac\u01ad\7t\2\2\u01ad"+
		"\u01ae\7k\2\2\u01ae\u01af\7p\2\2\u01af\u01b0\7i\2\2\u01b0n\3\2\2\2\u01b1"+
		"\u01b2\7d\2\2\u01b2\u01b3\7q\2\2\u01b3\u01b4\7q\2\2\u01b4\u01b5\7n\2\2"+
		"\u01b5\u01b6\7g\2\2\u01b6\u01b7\7c\2\2\u01b7\u01b8\7p\2\2\u01b8p\3\2\2"+
		"\2\u01b9\u01ba\7f\2\2\u01ba\u01bb\7c\2\2\u01bb\u01bc\7v\2\2\u01bc\u01bd"+
		"\7g\2\2\u01bdr\3\2\2\2\u01be\u01bf\7g\2\2\u01bf\u01c0\7o\2\2\u01c0\u01c1"+
		"\7r\2\2\u01c1\u01c2\7v\2\2\u01c2\u01c3\7{\2\2\u01c3t\3\2\2\2\u01c4\u01c5"+
		"\7v\2\2\u01c5\u01c6\7t\2\2\u01c6\u01c7\7w\2\2\u01c7\u01ce\7g\2\2\u01c8"+
		"\u01c9\7h\2\2\u01c9\u01ca\7c\2\2\u01ca\u01cb\7n\2\2\u01cb\u01cc\7u\2\2"+
		"\u01cc\u01ce\7g\2\2\u01cd\u01c4\3\2\2\2\u01cd\u01c8\3\2\2\2\u01cev\3\2"+
		"\2\2\u01cf\u01d1\5Y-\2\u01d0\u01cf\3\2\2\2\u01d0\u01d1\3\2\2\2\u01d1\u01d3"+
		"\3\2\2\2\u01d2\u01d4\5\u0089E\2\u01d3\u01d2\3\2\2\2\u01d4\u01d5\3\2\2"+
		"\2\u01d5\u01d3\3\2\2\2\u01d5\u01d6\3\2\2\2\u01d6x\3\2\2\2\u01d7\u01d9"+
		"\5[.\2\u01d8\u01da\5\u0089E\2\u01d9\u01d8\3\2\2\2\u01da\u01db\3\2\2\2"+
		"\u01db\u01d9\3\2\2\2\u01db\u01dc\3\2\2\2\u01dcz\3\2\2\2\u01dd\u01e0\5"+
		"Y-\2\u01de\u01e0\5[.\2\u01df\u01dd\3\2\2\2\u01df\u01de\3\2\2\2\u01df\u01e0"+
		"\3\2\2\2\u01e0\u01e2\3\2\2\2\u01e1\u01e3\5\u0089E\2\u01e2\u01e1\3\2\2"+
		"\2\u01e3\u01e4\3\2\2\2\u01e4\u01e2\3\2\2\2\u01e4\u01e5\3\2\2\2\u01e5\u01e6"+
		"\3\2\2\2\u01e6\u01e8\5O(\2\u01e7\u01e9\5\u0089E\2\u01e8\u01e7\3\2\2\2"+
		"\u01e9\u01ea\3\2\2\2\u01ea\u01e8\3\2\2\2\u01ea\u01eb\3\2\2\2\u01eb|\3"+
		"\2\2\2\u01ec\u01f0\5S*\2\u01ed\u01ef\n\2\2\2\u01ee\u01ed\3\2\2\2\u01ef"+
		"\u01f2\3\2\2\2\u01f0\u01ee\3\2\2\2\u01f0\u01f1\3\2\2\2\u01f1\u01f3\3\2"+
		"\2\2\u01f2\u01f0\3\2\2\2\u01f3\u01f4\5S*\2\u01f4~\3\2\2\2\u01f5\u01f9"+
		"\5]/\2\u01f6\u01f8\n\3\2\2\u01f7\u01f6\3\2\2\2\u01f8\u01fb\3\2\2\2\u01f9"+
		"\u01f7\3\2\2\2\u01f9\u01fa\3\2\2\2\u01fa\u01fc\3\2\2\2\u01fb\u01f9\3\2"+
		"\2\2\u01fc\u01fd\5]/\2\u01fd\u0080\3\2\2\2\u01fe\u01ff\5A!\2\u01ff\u0200"+
		"\5\u0089E\2\u0200\u0201\5\u0089E\2\u0201\u0207\5\u0089E\2\u0202\u0203"+
		"\5O(\2\u0203\u0204\5\u0089E\2\u0204\u0205\5\u0089E\2\u0205\u0206\5\u0089"+
		"E\2\u0206\u0208\3\2\2\2\u0207\u0202\3\2\2\2\u0208\u0209\3\2\2\2\u0209"+
		"\u0207\3\2\2\2\u0209\u020a\3\2\2\2\u020a\u0082\3\2\2\2\u020b\u020c\5w"+
		"<\2\u020c\u020d\5[.\2\u020d\u020f\3\2\2\2\u020e\u020b\3\2\2\2\u020f\u0210"+
		"\3\2\2\2\u0210\u020e\3\2\2\2\u0210\u0211\3\2\2\2\u0211\u0212\3\2\2\2\u0212"+
		"\u0213\5w<\2\u0213\u0216\3\2\2\2\u0214\u0216\5w<\2\u0215\u020e\3\2\2\2"+
		"\u0215\u0214\3\2\2\2\u0216\u0084\3\2\2\2\u0217\u021b\7]\2\2\u0218\u021c"+
		"\5{>\2\u0219\u021c\5y=\2\u021a\u021c\5w<\2\u021b\u0218\3\2\2\2\u021b\u0219"+
		"\3\2\2\2\u021b\u021a\3\2\2\2\u021c\u0223\3\2\2\2\u021d\u0221\5M\'\2\u021e"+
		"\u0222\5{>\2\u021f\u0222\5y=\2\u0220\u0222\5w<\2\u0221\u021e\3\2\2\2\u0221"+
		"\u021f\3\2\2\2\u0221\u0220\3\2\2\2\u0222\u0224\3\2\2\2\u0223\u021d\3\2"+
		"\2\2\u0224\u0225\3\2\2\2\u0225\u0223\3\2\2\2\u0225\u0226\3\2\2\2\u0226"+
		"\u0227\3\2\2\2\u0227\u0228\7_\2\2\u0228\u0086\3\2\2\2\u0229\u0230\5\u008b"+
		"F\2\u022a\u022f\5\u0089E\2\u022b\u022f\5\u008bF\2\u022c\u022f\5[.\2\u022d"+
		"\u022f\5_\60\2\u022e\u022a\3\2\2\2\u022e\u022b\3\2\2\2\u022e\u022c\3\2"+
		"\2\2\u022e\u022d\3\2\2\2\u022f\u0232\3\2\2\2\u0230\u022e\3\2\2\2\u0230"+
		"\u0231\3\2\2\2\u0231\u0088\3\2\2\2\u0232\u0230\3\2\2\2\u0233\u0234\t\4"+
		"\2\2\u0234\u008a\3\2\2\2\u0235\u0236\t\5\2\2\u0236\u008c\3\2\2\2\u0237"+
		"\u0239\5\u0095K\2\u0238\u0237\3\2\2\2\u0239\u023a\3\2\2\2\u023a\u0238"+
		"\3\2\2\2\u023a\u023b\3\2\2\2\u023b\u023f\3\2\2\2\u023c\u023e\5\u0093J"+
		"\2\u023d\u023c\3\2\2\2\u023e\u0241\3\2\2\2\u023f\u023d\3\2\2\2\u023f\u0240"+
		"\3\2\2\2\u0240\u0242\3\2\2\2\u0241\u023f\3\2\2\2\u0242\u0243\bG\4\2\u0243"+
		"\u008e\3\2\2\2\u0244\u0246\5\u0093J\2\u0245\u0244\3\2\2\2\u0246\u0247"+
		"\3\2\2\2\u0247\u0245\3\2\2\2\u0247\u0248\3\2\2\2\u0248\u024a\3\2\2\2\u0249"+
		"\u024b\7\2\2\3\u024a\u0249\3\2\2\2\u024a\u024b\3\2\2\2\u024b\u024c\3\2"+
		"\2\2\u024c\u024d\bH\5\2\u024d\u0090\3\2\2\2\u024e\u0252\7%\2\2\u024f\u0251"+
		"\13\2\2\2\u0250\u024f\3\2\2\2\u0251\u0254\3\2\2\2\u0252\u0253\3\2\2\2"+
		"\u0252\u0250\3\2\2\2\u0253\u0255\3\2\2\2\u0254\u0252\3\2\2\2\u0255\u0256"+
		"\5\u0095K\2\u0256\u0092\3\2\2\2\u0257\u0258\t\6\2\2\u0258\u0094\3\2\2"+
		"\2\u0259\u025b\7\17\2\2\u025a\u0259\3\2\2\2\u025a\u025b\3\2\2\2\u025b"+
		"\u025c\3\2\2\2\u025c\u025f\7\f\2\2\u025d\u025f\7\f\2\2\u025e\u025a\3\2"+
		"\2\2\u025e\u025d\3\2\2\2\u025f\u0096\3\2\2\2\u0260\u0261\7k\2\2\u0261"+
		"\u0262\7p\2\2\u0262\u0263\7f\2\2\u0263\u0264\7g\2\2\u0264\u0265\7p\2\2"+
		"\u0265\u0266\7v\2\2\u0266\u0098\3\2\2\2\u0267\u0268\7f\2\2\u0268\u0269"+
		"\7g\2\2\u0269\u026a\7f\2\2\u026a\u026b\7g\2\2\u026b\u026c\7p\2\2\u026c"+
		"\u026d\7v\2\2\u026d\u009a\3\2\2\2\u026e\u026f\13\2\2\2\u026f\u009c\3\2"+
		"\2\2\35\2\u0168\u0176\u01cd\u01d0\u01d5\u01db\u01df\u01e4\u01ea\u01f0"+
		"\u01f9\u0209\u0210\u0215\u021b\u0221\u0225\u022e\u0230\u023a\u023f\u0247"+
		"\u024a\u0252\u025a\u025e\6\3\37\2\3+\3\3G\4\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}