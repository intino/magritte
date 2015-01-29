// Generated from /Users/octavio/workspace/tara/rt/src/siani/tara/compiler/parser/antlr/TaraLexer.g4 by ANTLR 4.4.1-dev
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
		METAIDENTIFIER=1, SUB=2, USE=3, DSL=4, VAR=5, AS=6, HAS=7, ON=8, IS=9, 
		WITH=10, EXTENDS=11, ABSTRACT=12, SINGLE=13, REQUIRED=14, COMPONENT=15, 
		FACET=16, INTENTION=17, TERMINAL=18, NAMED=19, PROPERTY=20, LOCAL=21, 
		ALWAYS=22, ADDRESSED=23, AGGREGATED=24, READONLY=25, CASE=26, LEFT_PARENTHESIS=27, 
		RIGHT_PARENTHESIS=28, LEFT_SQUARE=29, RIGHT_SQUARE=30, LIST=31, INLINE=32, 
		CLOSE_INLINE=33, AMPERSAND=34, COLON=35, COMMA=36, DOT=37, EQUALS=38, 
		APHOSTROPHE=39, SEMICOLON=40, STAR=41, PLUS=42, WORD=43, RESOURCE=44, 
		INT_TYPE=45, NATURAL_TYPE=46, DOUBLE_TYPE=47, STRING_TYPE=48, BOOLEAN_TYPE=49, 
		MEASURE_TYPE=50, RATIO_TYPE=51, DATE_TYPE=52, EMPTY=53, SCIENCE_NOT=54, 
		BOOLEAN_VALUE=55, NATURAL_VALUE=56, NEGATIVE_VALUE=57, DOUBLE_VALUE=58, 
		STRING_VALUE=59, STRING_MULTILINE_VALUE_KEY=60, ADDRESS_VALUE=61, IDENTIFIER=62, 
		MEASURE_VALUE=63, NEWLINE=64, SPACES=65, DOC=66, SP=67, NL=68, NEW_LINE_INDENT=69, 
		DEDENT=70, UNKNOWN_TOKEN=71;
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
		"'F'", "'G'"
	};
	public static final String[] ruleNames = {
		"METAIDENTIFIER", "SUB", "USE", "DSL", "VAR", "AS", "HAS", "ON", "IS", 
		"WITH", "EXTENDS", "ABSTRACT", "SINGLE", "REQUIRED", "COMPONENT", "FACET", 
		"INTENTION", "TERMINAL", "NAMED", "PROPERTY", "LOCAL", "ALWAYS", "ADDRESSED", 
		"AGGREGATED", "READONLY", "CASE", "LEFT_PARENTHESIS", "RIGHT_PARENTHESIS", 
		"LEFT_SQUARE", "RIGHT_SQUARE", "LIST", "INLINE", "CLOSE_INLINE", "AMPERSAND", 
		"COLON", "COMMA", "DOT", "EQUALS", "APHOSTROPHE", "SEMICOLON", "STAR", 
		"PLUS", "WORD", "RESOURCE", "INT_TYPE", "NATURAL_TYPE", "DOUBLE_TYPE", 
		"STRING_TYPE", "BOOLEAN_TYPE", "MEASURE_TYPE", "RATIO_TYPE", "DATE_TYPE", 
		"EMPTY", "SCIENCE_NOT", "BOOLEAN_VALUE", "NATURAL_VALUE", "NEGATIVE_VALUE", 
		"DOUBLE_VALUE", "STRING_VALUE", "STRING_MULTILINE_VALUE_KEY", "ADDRESS_VALUE", 
		"IDENTIFIER", "MEASURE_VALUE", "NEWLINE", "SPACES", "DOC", "SP", "NL", 
		"NEW_LINE_INDENT", "DEDENT", "UNKNOWN_TOKEN", "DOLLAR", "EURO", "PERCENTAGE", 
		"GRADE", "BY", "DIVIDED_BY", "DASH", "DASHES", "UNDERDASH", "DIGIT", "LETTER"
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
		case 31: INLINE_action((RuleContext)_localctx, actionIndex); break;
		case 39: SEMICOLON_action((RuleContext)_localctx, actionIndex); break;
		case 63: NEWLINE_action((RuleContext)_localctx, actionIndex); break;
		case 65: DOC_action((RuleContext)_localctx, actionIndex); break;
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2I\u028c\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
		"\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\4O\tO\4P\tP\4Q\tQ\4R\tR\4S\tS\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5"+
		"\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3"+
		"\n\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31"+
		"\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3\32"+
		"\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\37"+
		"\3\37\3 \3 \3 \3 \3!\3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\3&\3&\3\'\3\'\3("+
		"\3(\3)\6)\u0173\n)\r)\16)\u0174\3)\3)\3*\3*\3+\3+\3,\3,\3,\3,\3,\3-\3"+
		"-\3-\3-\3-\3.\3.\3.\3.\3.\3.\3.\3.\3/\3/\3/\3/\3/\3/\3/\3/\3\60\3\60\3"+
		"\60\3\60\3\60\3\60\3\60\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\62\3\62\3"+
		"\62\3\62\3\62\3\62\3\62\3\62\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3"+
		"\64\3\64\3\64\3\64\3\64\3\64\3\65\3\65\3\65\3\65\3\65\3\66\3\66\3\66\3"+
		"\66\3\66\3\66\3\67\3\67\3\67\5\67\u01c9\n\67\3\67\6\67\u01cc\n\67\r\67"+
		"\16\67\u01cd\38\38\38\38\38\38\38\38\38\58\u01d9\n8\39\59\u01dc\n9\39"+
		"\69\u01df\n9\r9\169\u01e0\3:\3:\6:\u01e5\n:\r:\16:\u01e6\3;\3;\5;\u01eb"+
		"\n;\3;\6;\u01ee\n;\r;\16;\u01ef\3;\3;\6;\u01f4\n;\r;\16;\u01f5\3<\3<\7"+
		"<\u01fa\n<\f<\16<\u01fd\13<\3<\3<\3=\3=\7=\u0203\n=\f=\16=\u0206\13=\3"+
		"=\3=\3>\3>\3>\3>\3>\3>\3>\3>\3>\6>\u0213\n>\r>\16>\u0214\3?\3?\3?\3?\3"+
		"?\7?\u021c\n?\f?\16?\u021f\13?\3@\3@\3@\3@\3@\5@\u0226\n@\3@\3@\3@\3@"+
		"\3@\3@\3@\3@\3@\7@\u0231\n@\f@\16@\u0234\13@\3A\6A\u0237\nA\rA\16A\u0238"+
		"\3A\7A\u023c\nA\fA\16A\u023f\13A\3A\3A\3B\6B\u0244\nB\rB\16B\u0245\3B"+
		"\5B\u0249\nB\3B\3B\3C\3C\3C\3C\3C\7C\u0252\nC\fC\16C\u0255\13C\3C\3C\3"+
		"C\3D\3D\3E\5E\u025d\nE\3E\3E\5E\u0261\nE\3F\3F\3F\3F\3F\3F\3F\3G\3G\3"+
		"G\3G\3G\3G\3G\3H\3H\3I\3I\3J\3J\3K\3K\3L\3L\3M\3M\3N\3N\3O\3O\3P\3P\6"+
		"P\u0283\nP\rP\16P\u0284\3Q\3Q\3R\3R\3S\3S\3\u0253\2T\3\3\5\4\7\5\t\6\13"+
		"\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'"+
		"\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'"+
		"M(O)Q*S+U,W-Y.[/]\60_\61a\62c\63e\64g\65i\66k\67m8o9q:s;u<w=y>{?}@\177"+
		"A\u0081B\u0083C\u0085D\u0087E\u0089F\u008bG\u008dH\u008fI\u0091\2\u0093"+
		"\2\u0095\2\u0097\2\u0099\2\u009b\2\u009d\2\u009f\2\u00a1\2\u00a3\2\u00a5"+
		"\2\3\2\b\3\2$$\3\2//\4\2\13\13\"\"\4\2\u00b2\u00b2\u00bc\u00bc\3\2\62"+
		";\6\2C\\c|\u00d3\u00d3\u00f3\u00f3\u02a8\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3"+
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
		"\2\2\2\2\u0081\3\2\2\2\2\u0083\3\2\2\2\2\u0085\3\2\2\2\2\u0087\3\2\2\2"+
		"\2\u0089\3\2\2\2\2\u008b\3\2\2\2\2\u008d\3\2\2\2\2\u008f\3\2\2\2\3\u00a7"+
		"\3\2\2\2\5\u00af\3\2\2\2\7\u00b3\3\2\2\2\t\u00b7\3\2\2\2\13\u00bb\3\2"+
		"\2\2\r\u00bf\3\2\2\2\17\u00c2\3\2\2\2\21\u00c6\3\2\2\2\23\u00c9\3\2\2"+
		"\2\25\u00cc\3\2\2\2\27\u00d1\3\2\2\2\31\u00d9\3\2\2\2\33\u00e2\3\2\2\2"+
		"\35\u00e9\3\2\2\2\37\u00f2\3\2\2\2!\u00fc\3\2\2\2#\u0102\3\2\2\2%\u010c"+
		"\3\2\2\2\'\u0115\3\2\2\2)\u011b\3\2\2\2+\u0124\3\2\2\2-\u012a\3\2\2\2"+
		"/\u0131\3\2\2\2\61\u013b\3\2\2\2\63\u0146\3\2\2\2\65\u014f\3\2\2\2\67"+
		"\u0154\3\2\2\29\u0156\3\2\2\2;\u0158\3\2\2\2=\u015a\3\2\2\2?\u015c\3\2"+
		"\2\2A\u0160\3\2\2\2C\u0163\3\2\2\2E\u0165\3\2\2\2G\u0167\3\2\2\2I\u0169"+
		"\3\2\2\2K\u016b\3\2\2\2M\u016d\3\2\2\2O\u016f\3\2\2\2Q\u0172\3\2\2\2S"+
		"\u0178\3\2\2\2U\u017a\3\2\2\2W\u017c\3\2\2\2Y\u0181\3\2\2\2[\u0186\3\2"+
		"\2\2]\u018e\3\2\2\2_\u0196\3\2\2\2a\u019d\3\2\2\2c\u01a4\3\2\2\2e\u01ac"+
		"\3\2\2\2g\u01b4\3\2\2\2i\u01ba\3\2\2\2k\u01bf\3\2\2\2m\u01c5\3\2\2\2o"+
		"\u01d8\3\2\2\2q\u01db\3\2\2\2s\u01e2\3\2\2\2u\u01ea\3\2\2\2w\u01f7\3\2"+
		"\2\2y\u0200\3\2\2\2{\u0209\3\2\2\2}\u0216\3\2\2\2\177\u0225\3\2\2\2\u0081"+
		"\u0236\3\2\2\2\u0083\u0243\3\2\2\2\u0085\u024c\3\2\2\2\u0087\u0259\3\2"+
		"\2\2\u0089\u0260\3\2\2\2\u008b\u0262\3\2\2\2\u008d\u0269\3\2\2\2\u008f"+
		"\u0270\3\2\2\2\u0091\u0272\3\2\2\2\u0093\u0274\3\2\2\2\u0095\u0276\3\2"+
		"\2\2\u0097\u0278\3\2\2\2\u0099\u027a\3\2\2\2\u009b\u027c\3\2\2\2\u009d"+
		"\u027e\3\2\2\2\u009f\u0280\3\2\2\2\u00a1\u0286\3\2\2\2\u00a3\u0288\3\2"+
		"\2\2\u00a5\u028a\3\2\2\2\u00a7\u00a8\7E\2\2\u00a8\u00a9\7q\2\2\u00a9\u00aa"+
		"\7p\2\2\u00aa\u00ab\7e\2\2\u00ab\u00ac\7g\2\2\u00ac\u00ad\7r\2\2\u00ad"+
		"\u00ae\7v\2\2\u00ae\4\3\2\2\2\u00af\u00b0\7u\2\2\u00b0\u00b1\7w\2\2\u00b1"+
		"\u00b2\7d\2\2\u00b2\6\3\2\2\2\u00b3\u00b4\7w\2\2\u00b4\u00b5\7u\2\2\u00b5"+
		"\u00b6\7g\2\2\u00b6\b\3\2\2\2\u00b7\u00b8\7f\2\2\u00b8\u00b9\7u\2\2\u00b9"+
		"\u00ba\7n\2\2\u00ba\n\3\2\2\2\u00bb\u00bc\7x\2\2\u00bc\u00bd\7c\2\2\u00bd"+
		"\u00be\7t\2\2\u00be\f\3\2\2\2\u00bf\u00c0\7c\2\2\u00c0\u00c1\7u\2\2\u00c1"+
		"\16\3\2\2\2\u00c2\u00c3\7j\2\2\u00c3\u00c4\7c\2\2\u00c4\u00c5\7u\2\2\u00c5"+
		"\20\3\2\2\2\u00c6\u00c7\7q\2\2\u00c7\u00c8\7p\2\2\u00c8\22\3\2\2\2\u00c9"+
		"\u00ca\7k\2\2\u00ca\u00cb\7u\2\2\u00cb\24\3\2\2\2\u00cc\u00cd\7y\2\2\u00cd"+
		"\u00ce\7k\2\2\u00ce\u00cf\7v\2\2\u00cf\u00d0\7j\2\2\u00d0\26\3\2\2\2\u00d1"+
		"\u00d2\7g\2\2\u00d2\u00d3\7z\2\2\u00d3\u00d4\7v\2\2\u00d4\u00d5\7g\2\2"+
		"\u00d5\u00d6\7p\2\2\u00d6\u00d7\7f\2\2\u00d7\u00d8\7u\2\2\u00d8\30\3\2"+
		"\2\2\u00d9\u00da\7c\2\2\u00da\u00db\7d\2\2\u00db\u00dc\7u\2\2\u00dc\u00dd"+
		"\7v\2\2\u00dd\u00de\7t\2\2\u00de\u00df\7c\2\2\u00df\u00e0\7e\2\2\u00e0"+
		"\u00e1\7v\2\2\u00e1\32\3\2\2\2\u00e2\u00e3\7u\2\2\u00e3\u00e4\7k\2\2\u00e4"+
		"\u00e5\7p\2\2\u00e5\u00e6\7i\2\2\u00e6\u00e7\7n\2\2\u00e7\u00e8\7g\2\2"+
		"\u00e8\34\3\2\2\2\u00e9\u00ea\7t\2\2\u00ea\u00eb\7g\2\2\u00eb\u00ec\7"+
		"s\2\2\u00ec\u00ed\7w\2\2\u00ed\u00ee\7k\2\2\u00ee\u00ef\7t\2\2\u00ef\u00f0"+
		"\7g\2\2\u00f0\u00f1\7f\2\2\u00f1\36\3\2\2\2\u00f2\u00f3\7e\2\2\u00f3\u00f4"+
		"\7q\2\2\u00f4\u00f5\7o\2\2\u00f5\u00f6\7r\2\2\u00f6\u00f7\7q\2\2\u00f7"+
		"\u00f8\7p\2\2\u00f8\u00f9\7g\2\2\u00f9\u00fa\7p\2\2\u00fa\u00fb\7v\2\2"+
		"\u00fb \3\2\2\2\u00fc\u00fd\7h\2\2\u00fd\u00fe\7c\2\2\u00fe\u00ff\7e\2"+
		"\2\u00ff\u0100\7g\2\2\u0100\u0101\7v\2\2\u0101\"\3\2\2\2\u0102\u0103\7"+
		"k\2\2\u0103\u0104\7p\2\2\u0104\u0105\7v\2\2\u0105\u0106\7g\2\2\u0106\u0107"+
		"\7p\2\2\u0107\u0108\7v\2\2\u0108\u0109\7k\2\2\u0109\u010a\7q\2\2\u010a"+
		"\u010b\7p\2\2\u010b$\3\2\2\2\u010c\u010d\7v\2\2\u010d\u010e\7g\2\2\u010e"+
		"\u010f\7t\2\2\u010f\u0110\7o\2\2\u0110\u0111\7k\2\2\u0111\u0112\7p\2\2"+
		"\u0112\u0113\7c\2\2\u0113\u0114\7n\2\2\u0114&\3\2\2\2\u0115\u0116\7p\2"+
		"\2\u0116\u0117\7c\2\2\u0117\u0118\7o\2\2\u0118\u0119\7g\2\2\u0119\u011a"+
		"\7f\2\2\u011a(\3\2\2\2\u011b\u011c\7r\2\2\u011c\u011d\7t\2\2\u011d\u011e"+
		"\7q\2\2\u011e\u011f\7r\2\2\u011f\u0120\7g\2\2\u0120\u0121\7t\2\2\u0121"+
		"\u0122\7v\2\2\u0122\u0123\7{\2\2\u0123*\3\2\2\2\u0124\u0125\7n\2\2\u0125"+
		"\u0126\7q\2\2\u0126\u0127\7e\2\2\u0127\u0128\7c\2\2\u0128\u0129\7n\2\2"+
		"\u0129,\3\2\2\2\u012a\u012b\7c\2\2\u012b\u012c\7n\2\2\u012c\u012d\7y\2"+
		"\2\u012d\u012e\7c\2\2\u012e\u012f\7{\2\2\u012f\u0130\7u\2\2\u0130.\3\2"+
		"\2\2\u0131\u0132\7c\2\2\u0132\u0133\7f\2\2\u0133\u0134\7f\2\2\u0134\u0135"+
		"\7t\2\2\u0135\u0136\7g\2\2\u0136\u0137\7u\2\2\u0137\u0138\7u\2\2\u0138"+
		"\u0139\7g\2\2\u0139\u013a\7f\2\2\u013a\60\3\2\2\2\u013b\u013c\7c\2\2\u013c"+
		"\u013d\7i\2\2\u013d\u013e\7i\2\2\u013e\u013f\7t\2\2\u013f\u0140\7g\2\2"+
		"\u0140\u0141\7i\2\2\u0141\u0142\7c\2\2\u0142\u0143\7v\2\2\u0143\u0144"+
		"\7g\2\2\u0144\u0145\7f\2\2\u0145\62\3\2\2\2\u0146\u0147\7t\2\2\u0147\u0148"+
		"\7g\2\2\u0148\u0149\7c\2\2\u0149\u014a\7f\2\2\u014a\u014b\7q\2\2\u014b"+
		"\u014c\7p\2\2\u014c\u014d\7n\2\2\u014d\u014e\7{\2\2\u014e\64\3\2\2\2\u014f"+
		"\u0150\7e\2\2\u0150\u0151\7c\2\2\u0151\u0152\7u\2\2\u0152\u0153\7g\2\2"+
		"\u0153\66\3\2\2\2\u0154\u0155\7*\2\2\u01558\3\2\2\2\u0156\u0157\7+\2\2"+
		"\u0157:\3\2\2\2\u0158\u0159\7]\2\2\u0159<\3\2\2\2\u015a\u015b\7_\2\2\u015b"+
		">\3\2\2\2\u015c\u015d\7\60\2\2\u015d\u015e\7\60\2\2\u015e\u015f\7\60\2"+
		"\2\u015f@\3\2\2\2\u0160\u0161\7@\2\2\u0161\u0162\b!\2\2\u0162B\3\2\2\2"+
		"\u0163\u0164\7>\2\2\u0164D\3\2\2\2\u0165\u0166\7(\2\2\u0166F\3\2\2\2\u0167"+
		"\u0168\7<\2\2\u0168H\3\2\2\2\u0169\u016a\7.\2\2\u016aJ\3\2\2\2\u016b\u016c"+
		"\7\60\2\2\u016cL\3\2\2\2\u016d\u016e\7?\2\2\u016eN\3\2\2\2\u016f\u0170"+
		"\7$\2\2\u0170P\3\2\2\2\u0171\u0173\7=\2\2\u0172\u0171\3\2\2\2\u0173\u0174"+
		"\3\2\2\2\u0174\u0172\3\2\2\2\u0174\u0175\3\2\2\2\u0175\u0176\3\2\2\2\u0176"+
		"\u0177\b)\3\2\u0177R\3\2\2\2\u0178\u0179\7,\2\2\u0179T\3\2\2\2\u017a\u017b"+
		"\7-\2\2\u017bV\3\2\2\2\u017c\u017d\7y\2\2\u017d\u017e\7q\2\2\u017e\u017f"+
		"\7t\2\2\u017f\u0180\7f\2\2\u0180X\3\2\2\2\u0181\u0182\7h\2\2\u0182\u0183"+
		"\7k\2\2\u0183\u0184\7n\2\2\u0184\u0185\7g\2\2\u0185Z\3\2\2\2\u0186\u0187"+
		"\7k\2\2\u0187\u0188\7p\2\2\u0188\u0189\7v\2\2\u0189\u018a\7g\2\2\u018a"+
		"\u018b\7i\2\2\u018b\u018c\7g\2\2\u018c\u018d\7t\2\2\u018d\\\3\2\2\2\u018e"+
		"\u018f\7p\2\2\u018f\u0190\7c\2\2\u0190\u0191\7v\2\2\u0191\u0192\7w\2\2"+
		"\u0192\u0193\7t\2\2\u0193\u0194\7c\2\2\u0194\u0195\7n\2\2\u0195^\3\2\2"+
		"\2\u0196\u0197\7f\2\2\u0197\u0198\7q\2\2\u0198\u0199\7w\2\2\u0199\u019a"+
		"\7d\2\2\u019a\u019b\7n\2\2\u019b\u019c\7g\2\2\u019c`\3\2\2\2\u019d\u019e"+
		"\7u\2\2\u019e\u019f\7v\2\2\u019f\u01a0\7t\2\2\u01a0\u01a1\7k\2\2\u01a1"+
		"\u01a2\7p\2\2\u01a2\u01a3\7i\2\2\u01a3b\3\2\2\2\u01a4\u01a5\7d\2\2\u01a5"+
		"\u01a6\7q\2\2\u01a6\u01a7\7q\2\2\u01a7\u01a8\7n\2\2\u01a8\u01a9\7g\2\2"+
		"\u01a9\u01aa\7c\2\2\u01aa\u01ab\7p\2\2\u01abd\3\2\2\2\u01ac\u01ad\7o\2"+
		"\2\u01ad\u01ae\7g\2\2\u01ae\u01af\7c\2\2\u01af\u01b0\7u\2\2\u01b0\u01b1"+
		"\7w\2\2\u01b1\u01b2\7t\2\2\u01b2\u01b3\7g\2\2\u01b3f\3\2\2\2\u01b4\u01b5"+
		"\7t\2\2\u01b5\u01b6\7c\2\2\u01b6\u01b7\7v\2\2\u01b7\u01b8\7k\2\2\u01b8"+
		"\u01b9\7q\2\2\u01b9h\3\2\2\2\u01ba\u01bb\7f\2\2\u01bb\u01bc\7c\2\2\u01bc"+
		"\u01bd\7v\2\2\u01bd\u01be\7g\2\2\u01bej\3\2\2\2\u01bf\u01c0\7g\2\2\u01c0"+
		"\u01c1\7o\2\2\u01c1\u01c2\7r\2\2\u01c2\u01c3\7v\2\2\u01c3\u01c4\7{\2\2"+
		"\u01c4l\3\2\2\2\u01c5\u01c8\7G\2\2\u01c6\u01c9\5U+\2\u01c7\u01c9\5\u009d"+
		"O\2\u01c8\u01c6\3\2\2\2\u01c8\u01c7\3\2\2\2\u01c8\u01c9\3\2\2\2\u01c9"+
		"\u01cb\3\2\2\2\u01ca\u01cc\5\u00a3R\2\u01cb\u01ca\3\2\2\2\u01cc\u01cd"+
		"\3\2\2\2\u01cd\u01cb\3\2\2\2\u01cd\u01ce\3\2\2\2\u01cen\3\2\2\2\u01cf"+
		"\u01d0\7v\2\2\u01d0\u01d1\7t\2\2\u01d1\u01d2\7w\2\2\u01d2\u01d9\7g\2\2"+
		"\u01d3\u01d4\7h\2\2\u01d4\u01d5\7c\2\2\u01d5\u01d6\7n\2\2\u01d6\u01d7"+
		"\7u\2\2\u01d7\u01d9\7g\2\2\u01d8\u01cf\3\2\2\2\u01d8\u01d3\3\2\2\2\u01d9"+
		"p\3\2\2\2\u01da\u01dc\5U+\2\u01db\u01da\3\2\2\2\u01db\u01dc\3\2\2\2\u01dc"+
		"\u01de\3\2\2\2\u01dd\u01df\5\u00a3R\2\u01de\u01dd\3\2\2\2\u01df\u01e0"+
		"\3\2\2\2\u01e0\u01de\3\2\2\2\u01e0\u01e1\3\2\2\2\u01e1r\3\2\2\2\u01e2"+
		"\u01e4\5\u009dO\2\u01e3\u01e5\5\u00a3R\2\u01e4\u01e3\3\2\2\2\u01e5\u01e6"+
		"\3\2\2\2\u01e6\u01e4\3\2\2\2\u01e6\u01e7\3\2\2\2\u01e7t\3\2\2\2\u01e8"+
		"\u01eb\5U+\2\u01e9\u01eb\5\u009dO\2\u01ea\u01e8\3\2\2\2\u01ea\u01e9\3"+
		"\2\2\2\u01ea\u01eb\3\2\2\2\u01eb\u01ed\3\2\2\2\u01ec\u01ee\5\u00a3R\2"+
		"\u01ed\u01ec\3\2\2\2\u01ee\u01ef\3\2\2\2\u01ef\u01ed\3\2\2\2\u01ef\u01f0"+
		"\3\2\2\2\u01f0\u01f1\3\2\2\2\u01f1\u01f3\5K&\2\u01f2\u01f4\5\u00a3R\2"+
		"\u01f3\u01f2\3\2\2\2\u01f4\u01f5\3\2\2\2\u01f5\u01f3\3\2\2\2\u01f5\u01f6"+
		"\3\2\2\2\u01f6v\3\2\2\2\u01f7\u01fb\5O(\2\u01f8\u01fa\n\2\2\2\u01f9\u01f8"+
		"\3\2\2\2\u01fa\u01fd\3\2\2\2\u01fb\u01f9\3\2\2\2\u01fb\u01fc\3\2\2\2\u01fc"+
		"\u01fe\3\2\2\2\u01fd\u01fb\3\2\2\2\u01fe\u01ff\5O(\2\u01ffx\3\2\2\2\u0200"+
		"\u0204\5\u009fP\2\u0201\u0203\n\3\2\2\u0202\u0201\3\2\2\2\u0203\u0206"+
		"\3\2\2\2\u0204\u0202\3\2\2\2\u0204\u0205\3\2\2\2\u0205\u0207\3\2\2\2\u0206"+
		"\u0204\3\2\2\2\u0207\u0208\5\u009fP\2\u0208z\3\2\2\2\u0209\u020a\5E#\2"+
		"\u020a\u020b\5\u00a3R\2\u020b\u020c\5\u00a3R\2\u020c\u0212\5\u00a3R\2"+
		"\u020d\u020e\5K&\2\u020e\u020f\5\u00a3R\2\u020f\u0210\5\u00a3R\2\u0210"+
		"\u0211\5\u00a3R\2\u0211\u0213\3\2\2\2\u0212\u020d\3\2\2\2\u0213\u0214"+
		"\3\2\2\2\u0214\u0212\3\2\2\2\u0214\u0215\3\2\2\2\u0215|\3\2\2\2\u0216"+
		"\u021d\5\u00a5S\2\u0217\u021c\5\u00a3R\2\u0218\u021c\5\u00a5S\2\u0219"+
		"\u021c\5\u009dO\2\u021a\u021c\5\u00a1Q\2\u021b\u0217\3\2\2\2\u021b\u0218"+
		"\3\2\2\2\u021b\u0219\3\2\2\2\u021b\u021a\3\2\2\2\u021c\u021f\3\2\2\2\u021d"+
		"\u021b\3\2\2\2\u021d\u021e\3\2\2\2\u021e~\3\2\2\2\u021f\u021d\3\2\2\2"+
		"\u0220\u0226\5\u00a5S\2\u0221\u0226\5\u0095K\2\u0222\u0226\5\u0091I\2"+
		"\u0223\u0226\5\u0093J\2\u0224\u0226\5\u0097L\2\u0225\u0220\3\2\2\2\u0225"+
		"\u0221\3\2\2\2\u0225\u0222\3\2\2\2\u0225\u0223\3\2\2\2\u0225\u0224\3\2"+
		"\2\2\u0226\u0232\3\2\2\2\u0227\u0231\5\u00a1Q\2\u0228\u0231\5\u0099M\2"+
		"\u0229\u0231\5\u009bN\2\u022a\u0231\5\u0095K\2\u022b\u0231\5\u0091I\2"+
		"\u022c\u0231\5\u0093J\2\u022d\u0231\5\u0097L\2\u022e\u0231\5\u00a5S\2"+
		"\u022f\u0231\5\u00a3R\2\u0230\u0227\3\2\2\2\u0230\u0228\3\2\2\2\u0230"+
		"\u0229\3\2\2\2\u0230\u022a\3\2\2\2\u0230\u022b\3\2\2\2\u0230\u022c\3\2"+
		"\2\2\u0230\u022d\3\2\2\2\u0230\u022e\3\2\2\2\u0230\u022f\3\2\2\2\u0231"+
		"\u0234\3\2\2\2\u0232\u0230\3\2\2\2\u0232\u0233\3\2\2\2\u0233\u0080\3\2"+
		"\2\2\u0234\u0232\3\2\2\2\u0235\u0237\5\u0089E\2\u0236\u0235\3\2\2\2\u0237"+
		"\u0238\3\2\2\2\u0238\u0236\3\2\2\2\u0238\u0239\3\2\2\2\u0239\u023d\3\2"+
		"\2\2\u023a\u023c\5\u0087D\2\u023b\u023a\3\2\2\2\u023c\u023f\3\2\2\2\u023d"+
		"\u023b\3\2\2\2\u023d\u023e\3\2\2\2\u023e\u0240\3\2\2\2\u023f\u023d\3\2"+
		"\2\2\u0240\u0241\bA\4\2\u0241\u0082\3\2\2\2\u0242\u0244\5\u0087D\2\u0243"+
		"\u0242\3\2\2\2\u0244\u0245\3\2\2\2\u0245\u0243\3\2\2\2\u0245\u0246\3\2"+
		"\2\2\u0246\u0248\3\2\2\2\u0247\u0249\7\2\2\3\u0248\u0247\3\2\2\2\u0248"+
		"\u0249\3\2\2\2\u0249\u024a\3\2\2\2\u024a\u024b\bB\5\2\u024b\u0084\3\2"+
		"\2\2\u024c\u024d\7f\2\2\u024d\u024e\7g\2\2\u024e\u024f\7h\2\2\u024f\u0253"+
		"\3\2\2\2\u0250\u0252\13\2\2\2\u0251\u0250\3\2\2\2\u0252\u0255\3\2\2\2"+
		"\u0253\u0254\3\2\2\2\u0253\u0251\3\2\2\2\u0254\u0256\3\2\2\2\u0255\u0253"+
		"\3\2\2\2\u0256\u0257\5\u0089E\2\u0257\u0258\bC\6\2\u0258\u0086\3\2\2\2"+
		"\u0259\u025a\t\4\2\2\u025a\u0088\3\2\2\2\u025b\u025d\7\17\2\2\u025c\u025b"+
		"\3\2\2\2\u025c\u025d\3\2\2\2\u025d\u025e\3\2\2\2\u025e\u0261\7\f\2\2\u025f"+
		"\u0261\7\17\2\2\u0260\u025c\3\2\2\2\u0260\u025f\3\2\2\2\u0261\u008a\3"+
		"\2\2\2\u0262\u0263\7k\2\2\u0263\u0264\7p\2\2\u0264\u0265\7f\2\2\u0265"+
		"\u0266\7g\2\2\u0266\u0267\7p\2\2\u0267\u0268\7v\2\2\u0268\u008c\3\2\2"+
		"\2\u0269\u026a\7f\2\2\u026a\u026b\7g\2\2\u026b\u026c\7f\2\2\u026c\u026d"+
		"\7g\2\2\u026d\u026e\7p\2\2\u026e\u026f\7v\2\2\u026f\u008e\3\2\2\2\u0270"+
		"\u0271\13\2\2\2\u0271\u0090\3\2\2\2\u0272\u0273\7&\2\2\u0273\u0092\3\2"+
		"\2\2\u0274\u0275\7\u20ae\2\2\u0275\u0094\3\2\2\2\u0276\u0277\7\'\2\2\u0277"+
		"\u0096\3\2\2\2\u0278\u0279\t\5\2\2\u0279\u0098\3\2\2\2\u027a\u027b\7\u00b9"+
		"\2\2\u027b\u009a\3\2\2\2\u027c\u027d\7\61\2\2\u027d\u009c\3\2\2\2\u027e"+
		"\u027f\7/\2\2\u027f\u009e\3\2\2\2\u0280\u0282\5\u009dO\2\u0281\u0283\5"+
		"\u009dO\2\u0282\u0281\3\2\2\2\u0283\u0284\3\2\2\2\u0284\u0282\3\2\2\2"+
		"\u0284\u0285\3\2\2\2\u0285\u00a0\3\2\2\2\u0286\u0287\7a\2\2\u0287\u00a2"+
		"\3\2\2\2\u0288\u0289\t\6\2\2\u0289\u00a4\3\2\2\2\u028a\u028b\t\7\2\2\u028b"+
		"\u00a6\3\2\2\2\35\2\u0174\u01c8\u01cd\u01d8\u01db\u01e0\u01e6\u01ea\u01ef"+
		"\u01f5\u01fb\u0204\u0214\u021b\u021d\u0225\u0230\u0232\u0238\u023d\u0245"+
		"\u0248\u0253\u025c\u0260\u0284\7\3!\2\3)\3\3A\4\2\3\2\3C\5";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}