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
		METAIDENTIFIER=1, SUB=2, USE=3, BOX=4, VAR=5, AS=6, HAS=7, ON=8, IS=9, 
		WITH=10, EXTENDS=11, ABSTRACT=12, SINGLE=13, REQUIRED=14, COMPONENT=15, 
		FACET=16, INTENTION=17, TERMINAL=18, NAMED=19, PROPERTY=20, UNIVERSAL=21, 
		ALWAYS=22, ADDRESSED=23, AGGREGATED=24, COMPOSED=25, MULTIPLE=26, ROOT=27, 
		LEFT_PARENTHESIS=28, RIGHT_PARENTHESIS=29, LEFT_SQUARE=30, RIGHT_SQUARE=31, 
		LIST=32, INLINE=33, CLOSE_INLINE=34, AMPERSAND=35, DOLLAR=36, EURO=37, 
		PERCENTAGE=38, GRADE=39, COLON=40, COMMA=41, DOT=42, EQUALS=43, APHOSTROPHE=44, 
		SEMICOLON=45, STAR=46, POSITIVE=47, DASH=48, DASHES=49, UNDERDASH=50, 
		WORD=51, RESOURCE=52, COORDINATE_TYPE=53, INT_TYPE=54, NATURAL_TYPE=55, 
		DOUBLE_TYPE=56, STRING_TYPE=57, BOOLEAN_TYPE=58, DATE_TYPE=59, EMPTY=60, 
		BOOLEAN_VALUE=61, NATURAL_VALUE=62, NEGATIVE_VALUE=63, DOUBLE_VALUE=64, 
		STRING_VALUE=65, STRING_MULTILINE_VALUE_KEY=66, ADDRESS_VALUE=67, DATE_VALUE=68, 
		COORDINATE_VALUE=69, IDENTIFIER=70, DIGIT=71, LETTER=72, NEWLINE=73, SPACES=74, 
		DOC=75, SP=76, NL=77, NEW_LINE_INDENT=78, DEDENT=79, UNKNOWN_TOKEN=80;
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
		"'F'", "'G'", "'H'", "'I'", "'J'", "'K'", "'L'", "'M'", "'N'", "'O'", 
		"'P'"
	};
	public static final String[] ruleNames = {
		"METAIDENTIFIER", "SUB", "USE", "BOX", "VAR", "AS", "HAS", "ON", "IS", 
		"WITH", "EXTENDS", "ABSTRACT", "SINGLE", "REQUIRED", "COMPONENT", "FACET", 
		"INTENTION", "TERMINAL", "NAMED", "PROPERTY", "UNIVERSAL", "ALWAYS", "ADDRESSED", 
		"AGGREGATED", "COMPOSED", "MULTIPLE", "ROOT", "LEFT_PARENTHESIS", "RIGHT_PARENTHESIS", 
		"LEFT_SQUARE", "RIGHT_SQUARE", "LIST", "INLINE", "CLOSE_INLINE", "AMPERSAND", 
		"DOLLAR", "EURO", "PERCENTAGE", "GRADE", "COLON", "COMMA", "DOT", "EQUALS", 
		"APHOSTROPHE", "SEMICOLON", "STAR", "POSITIVE", "DASH", "DASHES", "UNDERDASH", 
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
		case 32: INLINE_action((RuleContext)_localctx, actionIndex); break;
		case 44: SEMICOLON_action((RuleContext)_localctx, actionIndex); break;
		case 72: NEWLINE_action((RuleContext)_localctx, actionIndex); break;
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2R\u028d\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
		"\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\4O\tO\4P\tP\4Q\tQ\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6"+
		"\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\13\3\13"+
		"\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\26"+
		"\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31"+
		"\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32"+
		"\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\34"+
		"\3\34\3\34\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3!\3!\3"+
		"\"\3\"\3\"\3#\3#\3$\3$\3%\3%\3&\3&\3\'\3\'\3(\3(\3)\3)\3*\3*\3+\3+\3,"+
		"\3,\3-\3-\3.\6.\u0184\n.\r.\16.\u0185\3.\3.\3/\3/\3\60\3\60\3\61\3\61"+
		"\3\62\3\62\6\62\u0192\n\62\r\62\16\62\u0193\3\63\3\63\3\64\3\64\3\64\3"+
		"\64\3\64\3\65\3\65\3\65\3\65\3\65\3\65\3\65\3\65\3\65\3\66\3\66\3\66\3"+
		"\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\67\3\67\3\67\3\67\3\67\3\67\3"+
		"\67\3\67\38\38\38\38\38\38\38\38\39\39\39\39\39\39\39\3:\3:\3:\3:\3:\3"+
		":\3:\3;\3;\3;\3;\3;\3;\3;\3;\3<\3<\3<\3<\3<\3=\3=\3=\3=\3=\3=\3>\3>\3"+
		">\3>\3>\3>\3>\3>\3>\5>\u01eb\n>\3?\5?\u01ee\n?\3?\6?\u01f1\n?\r?\16?\u01f2"+
		"\3@\3@\6@\u01f7\n@\r@\16@\u01f8\3A\3A\5A\u01fd\nA\3A\6A\u0200\nA\rA\16"+
		"A\u0201\3A\3A\6A\u0206\nA\rA\16A\u0207\3B\3B\7B\u020c\nB\fB\16B\u020f"+
		"\13B\3B\3B\3C\3C\7C\u0215\nC\fC\16C\u0218\13C\3C\3C\3D\3D\3D\3D\3D\3D"+
		"\3D\3D\3D\6D\u0225\nD\rD\16D\u0226\3E\3E\3E\6E\u022c\nE\rE\16E\u022d\3"+
		"E\3E\3E\5E\u0233\nE\3F\3F\3F\3F\5F\u0239\nF\3F\3F\3F\3F\5F\u023f\nF\6"+
		"F\u0241\nF\rF\16F\u0242\3F\3F\3G\3G\3G\3G\3G\7G\u024c\nG\fG\16G\u024f"+
		"\13G\3H\3H\3I\3I\3J\6J\u0256\nJ\rJ\16J\u0257\3J\7J\u025b\nJ\fJ\16J\u025e"+
		"\13J\3J\3J\3K\6K\u0263\nK\rK\16K\u0264\3K\5K\u0268\nK\3K\3K\3L\3L\7L\u026e"+
		"\nL\fL\16L\u0271\13L\3L\3L\3M\3M\3N\5N\u0278\nN\3N\3N\5N\u027c\nN\3O\3"+
		"O\3O\3O\3O\3O\3O\3P\3P\3P\3P\3P\3P\3P\3Q\3Q\3\u026f\2R\3\3\5\4\7\5\t\6"+
		"\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24"+
		"\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K"+
		"\'M(O)Q*S+U,W-Y.[/]\60_\61a\62c\63e\64g\65i\66k\67m8o9q:s;u<w=y>{?}@\177"+
		"A\u0081B\u0083C\u0085D\u0087E\u0089F\u008bG\u008dH\u008fI\u0091J\u0093"+
		"K\u0095L\u0097M\u0099N\u009bO\u009dP\u009fQ\u00a1R\3\2\7\3\2))\3\2//\3"+
		"\2\62;\4\2C\\c|\4\2\13\13\"\"\u02ab\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2"+
		"\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23"+
		"\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2"+
		"\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2"+
		"\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3"+
		"\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2"+
		"\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2"+
		"\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2["+
		"\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2"+
		"\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2"+
		"\2u\3\2\2\2\2w\3\2\2\2\2y\3\2\2\2\2{\3\2\2\2\2}\3\2\2\2\2\177\3\2\2\2"+
		"\2\u0081\3\2\2\2\2\u0083\3\2\2\2\2\u0085\3\2\2\2\2\u0087\3\2\2\2\2\u0089"+
		"\3\2\2\2\2\u008b\3\2\2\2\2\u008d\3\2\2\2\2\u008f\3\2\2\2\2\u0091\3\2\2"+
		"\2\2\u0093\3\2\2\2\2\u0095\3\2\2\2\2\u0097\3\2\2\2\2\u0099\3\2\2\2\2\u009b"+
		"\3\2\2\2\2\u009d\3\2\2\2\2\u009f\3\2\2\2\2\u00a1\3\2\2\2\3\u00a3\3\2\2"+
		"\2\5\u00ab\3\2\2\2\7\u00af\3\2\2\2\t\u00b3\3\2\2\2\13\u00b7\3\2\2\2\r"+
		"\u00bb\3\2\2\2\17\u00be\3\2\2\2\21\u00c2\3\2\2\2\23\u00c5\3\2\2\2\25\u00c8"+
		"\3\2\2\2\27\u00cd\3\2\2\2\31\u00d5\3\2\2\2\33\u00de\3\2\2\2\35\u00e5\3"+
		"\2\2\2\37\u00ee\3\2\2\2!\u00f8\3\2\2\2#\u00fe\3\2\2\2%\u0108\3\2\2\2\'"+
		"\u0111\3\2\2\2)\u0117\3\2\2\2+\u0120\3\2\2\2-\u012a\3\2\2\2/\u0131\3\2"+
		"\2\2\61\u013b\3\2\2\2\63\u0146\3\2\2\2\65\u014f\3\2\2\2\67\u0158\3\2\2"+
		"\29\u015d\3\2\2\2;\u015f\3\2\2\2=\u0161\3\2\2\2?\u0163\3\2\2\2A\u0165"+
		"\3\2\2\2C\u0169\3\2\2\2E\u016c\3\2\2\2G\u016e\3\2\2\2I\u0170\3\2\2\2K"+
		"\u0172\3\2\2\2M\u0174\3\2\2\2O\u0176\3\2\2\2Q\u0178\3\2\2\2S\u017a\3\2"+
		"\2\2U\u017c\3\2\2\2W\u017e\3\2\2\2Y\u0180\3\2\2\2[\u0183\3\2\2\2]\u0189"+
		"\3\2\2\2_\u018b\3\2\2\2a\u018d\3\2\2\2c\u018f\3\2\2\2e\u0195\3\2\2\2g"+
		"\u0197\3\2\2\2i\u019c\3\2\2\2k\u01a5\3\2\2\2m\u01b0\3\2\2\2o\u01b8\3\2"+
		"\2\2q\u01c0\3\2\2\2s\u01c7\3\2\2\2u\u01ce\3\2\2\2w\u01d6\3\2\2\2y\u01db"+
		"\3\2\2\2{\u01ea\3\2\2\2}\u01ed\3\2\2\2\177\u01f4\3\2\2\2\u0081\u01fc\3"+
		"\2\2\2\u0083\u0209\3\2\2\2\u0085\u0212\3\2\2\2\u0087\u021b\3\2\2\2\u0089"+
		"\u0232\3\2\2\2\u008b\u0234\3\2\2\2\u008d\u0246\3\2\2\2\u008f\u0250\3\2"+
		"\2\2\u0091\u0252\3\2\2\2\u0093\u0255\3\2\2\2\u0095\u0262\3\2\2\2\u0097"+
		"\u026b\3\2\2\2\u0099\u0274\3\2\2\2\u009b\u027b\3\2\2\2\u009d\u027d\3\2"+
		"\2\2\u009f\u0284\3\2\2\2\u00a1\u028b\3\2\2\2\u00a3\u00a4\7E\2\2\u00a4"+
		"\u00a5\7q\2\2\u00a5\u00a6\7p\2\2\u00a6\u00a7\7e\2\2\u00a7\u00a8\7g\2\2"+
		"\u00a8\u00a9\7r\2\2\u00a9\u00aa\7v\2\2\u00aa\4\3\2\2\2\u00ab\u00ac\7u"+
		"\2\2\u00ac\u00ad\7w\2\2\u00ad\u00ae\7d\2\2\u00ae\6\3\2\2\2\u00af\u00b0"+
		"\7w\2\2\u00b0\u00b1\7u\2\2\u00b1\u00b2\7g\2\2\u00b2\b\3\2\2\2\u00b3\u00b4"+
		"\7d\2\2\u00b4\u00b5\7q\2\2\u00b5\u00b6\7z\2\2\u00b6\n\3\2\2\2\u00b7\u00b8"+
		"\7x\2\2\u00b8\u00b9\7c\2\2\u00b9\u00ba\7t\2\2\u00ba\f\3\2\2\2\u00bb\u00bc"+
		"\7c\2\2\u00bc\u00bd\7u\2\2\u00bd\16\3\2\2\2\u00be\u00bf\7j\2\2\u00bf\u00c0"+
		"\7c\2\2\u00c0\u00c1\7u\2\2\u00c1\20\3\2\2\2\u00c2\u00c3\7q\2\2\u00c3\u00c4"+
		"\7p\2\2\u00c4\22\3\2\2\2\u00c5\u00c6\7k\2\2\u00c6\u00c7\7u\2\2\u00c7\24"+
		"\3\2\2\2\u00c8\u00c9\7y\2\2\u00c9\u00ca\7k\2\2\u00ca\u00cb\7v\2\2\u00cb"+
		"\u00cc\7j\2\2\u00cc\26\3\2\2\2\u00cd\u00ce\7g\2\2\u00ce\u00cf\7z\2\2\u00cf"+
		"\u00d0\7v\2\2\u00d0\u00d1\7g\2\2\u00d1\u00d2\7p\2\2\u00d2\u00d3\7f\2\2"+
		"\u00d3\u00d4\7u\2\2\u00d4\30\3\2\2\2\u00d5\u00d6\7c\2\2\u00d6\u00d7\7"+
		"d\2\2\u00d7\u00d8\7u\2\2\u00d8\u00d9\7v\2\2\u00d9\u00da\7t\2\2\u00da\u00db"+
		"\7c\2\2\u00db\u00dc\7e\2\2\u00dc\u00dd\7v\2\2\u00dd\32\3\2\2\2\u00de\u00df"+
		"\7u\2\2\u00df\u00e0\7k\2\2\u00e0\u00e1\7p\2\2\u00e1\u00e2\7i\2\2\u00e2"+
		"\u00e3\7n\2\2\u00e3\u00e4\7g\2\2\u00e4\34\3\2\2\2\u00e5\u00e6\7t\2\2\u00e6"+
		"\u00e7\7g\2\2\u00e7\u00e8\7s\2\2\u00e8\u00e9\7w\2\2\u00e9\u00ea\7k\2\2"+
		"\u00ea\u00eb\7t\2\2\u00eb\u00ec\7g\2\2\u00ec\u00ed\7f\2\2\u00ed\36\3\2"+
		"\2\2\u00ee\u00ef\7e\2\2\u00ef\u00f0\7q\2\2\u00f0\u00f1\7o\2\2\u00f1\u00f2"+
		"\7r\2\2\u00f2\u00f3\7q\2\2\u00f3\u00f4\7p\2\2\u00f4\u00f5\7g\2\2\u00f5"+
		"\u00f6\7p\2\2\u00f6\u00f7\7v\2\2\u00f7 \3\2\2\2\u00f8\u00f9\7h\2\2\u00f9"+
		"\u00fa\7c\2\2\u00fa\u00fb\7e\2\2\u00fb\u00fc\7g\2\2\u00fc\u00fd\7v\2\2"+
		"\u00fd\"\3\2\2\2\u00fe\u00ff\7k\2\2\u00ff\u0100\7p\2\2\u0100\u0101\7v"+
		"\2\2\u0101\u0102\7g\2\2\u0102\u0103\7p\2\2\u0103\u0104\7v\2\2\u0104\u0105"+
		"\7k\2\2\u0105\u0106\7q\2\2\u0106\u0107\7p\2\2\u0107$\3\2\2\2\u0108\u0109"+
		"\7v\2\2\u0109\u010a\7g\2\2\u010a\u010b\7t\2\2\u010b\u010c\7o\2\2\u010c"+
		"\u010d\7k\2\2\u010d\u010e\7p\2\2\u010e\u010f\7c\2\2\u010f\u0110\7n\2\2"+
		"\u0110&\3\2\2\2\u0111\u0112\7p\2\2\u0112\u0113\7c\2\2\u0113\u0114\7o\2"+
		"\2\u0114\u0115\7g\2\2\u0115\u0116\7f\2\2\u0116(\3\2\2\2\u0117\u0118\7"+
		"r\2\2\u0118\u0119\7t\2\2\u0119\u011a\7q\2\2\u011a\u011b\7r\2\2\u011b\u011c"+
		"\7g\2\2\u011c\u011d\7t\2\2\u011d\u011e\7v\2\2\u011e\u011f\7{\2\2\u011f"+
		"*\3\2\2\2\u0120\u0121\7w\2\2\u0121\u0122\7p\2\2\u0122\u0123\7k\2\2\u0123"+
		"\u0124\7x\2\2\u0124\u0125\7g\2\2\u0125\u0126\7t\2\2\u0126\u0127\7u\2\2"+
		"\u0127\u0128\7c\2\2\u0128\u0129\7n\2\2\u0129,\3\2\2\2\u012a\u012b\7c\2"+
		"\2\u012b\u012c\7n\2\2\u012c\u012d\7y\2\2\u012d\u012e\7c\2\2\u012e\u012f"+
		"\7{\2\2\u012f\u0130\7u\2\2\u0130.\3\2\2\2\u0131\u0132\7c\2\2\u0132\u0133"+
		"\7f\2\2\u0133\u0134\7f\2\2\u0134\u0135\7t\2\2\u0135\u0136\7g\2\2\u0136"+
		"\u0137\7u\2\2\u0137\u0138\7u\2\2\u0138\u0139\7g\2\2\u0139\u013a\7f\2\2"+
		"\u013a\60\3\2\2\2\u013b\u013c\7c\2\2\u013c\u013d\7i\2\2\u013d\u013e\7"+
		"i\2\2\u013e\u013f\7t\2\2\u013f\u0140\7g\2\2\u0140\u0141\7i\2\2\u0141\u0142"+
		"\7c\2\2\u0142\u0143\7v\2\2\u0143\u0144\7g\2\2\u0144\u0145\7f\2\2\u0145"+
		"\62\3\2\2\2\u0146\u0147\7e\2\2\u0147\u0148\7q\2\2\u0148\u0149\7o\2\2\u0149"+
		"\u014a\7r\2\2\u014a\u014b\7q\2\2\u014b\u014c\7u\2\2\u014c\u014d\7g\2\2"+
		"\u014d\u014e\7f\2\2\u014e\64\3\2\2\2\u014f\u0150\7o\2\2\u0150\u0151\7"+
		"w\2\2\u0151\u0152\7n\2\2\u0152\u0153\7v\2\2\u0153\u0154\7k\2\2\u0154\u0155"+
		"\7r\2\2\u0155\u0156\7n\2\2\u0156\u0157\7g\2\2\u0157\66\3\2\2\2\u0158\u0159"+
		"\7t\2\2\u0159\u015a\7q\2\2\u015a\u015b\7q\2\2\u015b\u015c\7v\2\2\u015c"+
		"8\3\2\2\2\u015d\u015e\7*\2\2\u015e:\3\2\2\2\u015f\u0160\7+\2\2\u0160<"+
		"\3\2\2\2\u0161\u0162\7]\2\2\u0162>\3\2\2\2\u0163\u0164\7_\2\2\u0164@\3"+
		"\2\2\2\u0165\u0166\7\60\2\2\u0166\u0167\7\60\2\2\u0167\u0168\7\60\2\2"+
		"\u0168B\3\2\2\2\u0169\u016a\7@\2\2\u016a\u016b\b\"\2\2\u016bD\3\2\2\2"+
		"\u016c\u016d\7>\2\2\u016dF\3\2\2\2\u016e\u016f\7(\2\2\u016fH\3\2\2\2\u0170"+
		"\u0171\7&\2\2\u0171J\3\2\2\2\u0172\u0173\7\u20ae\2\2\u0173L\3\2\2\2\u0174"+
		"\u0175\7\'\2\2\u0175N\3\2\2\2\u0176\u0177\7\u00bc\2\2\u0177P\3\2\2\2\u0178"+
		"\u0179\7<\2\2\u0179R\3\2\2\2\u017a\u017b\7.\2\2\u017bT\3\2\2\2\u017c\u017d"+
		"\7\60\2\2\u017dV\3\2\2\2\u017e\u017f\7?\2\2\u017fX\3\2\2\2\u0180\u0181"+
		"\7)\2\2\u0181Z\3\2\2\2\u0182\u0184\7=\2\2\u0183\u0182\3\2\2\2\u0184\u0185"+
		"\3\2\2\2\u0185\u0183\3\2\2\2\u0185\u0186\3\2\2\2\u0186\u0187\3\2\2\2\u0187"+
		"\u0188\b.\3\2\u0188\\\3\2\2\2\u0189\u018a\7,\2\2\u018a^\3\2\2\2\u018b"+
		"\u018c\7-\2\2\u018c`\3\2\2\2\u018d\u018e\7/\2\2\u018eb\3\2\2\2\u018f\u0191"+
		"\5a\61\2\u0190\u0192\5a\61\2\u0191\u0190\3\2\2\2\u0192\u0193\3\2\2\2\u0193"+
		"\u0191\3\2\2\2\u0193\u0194\3\2\2\2\u0194d\3\2\2\2\u0195\u0196\7a\2\2\u0196"+
		"f\3\2\2\2\u0197\u0198\7y\2\2\u0198\u0199\7q\2\2\u0199\u019a\7t\2\2\u019a"+
		"\u019b\7f\2\2\u019bh\3\2\2\2\u019c\u019d\7t\2\2\u019d\u019e\7g\2\2\u019e"+
		"\u019f\7u\2\2\u019f\u01a0\7q\2\2\u01a0\u01a1\7w\2\2\u01a1\u01a2\7t\2\2"+
		"\u01a2\u01a3\7e\2\2\u01a3\u01a4\7g\2\2\u01a4j\3\2\2\2\u01a5\u01a6\7e\2"+
		"\2\u01a6\u01a7\7q\2\2\u01a7\u01a8\7q\2\2\u01a8\u01a9\7t\2\2\u01a9\u01aa"+
		"\7f\2\2\u01aa\u01ab\7k\2\2\u01ab\u01ac\7p\2\2\u01ac\u01ad\7c\2\2\u01ad"+
		"\u01ae\7v\2\2\u01ae\u01af\7g\2\2\u01afl\3\2\2\2\u01b0\u01b1\7k\2\2\u01b1"+
		"\u01b2\7p\2\2\u01b2\u01b3\7v\2\2\u01b3\u01b4\7g\2\2\u01b4\u01b5\7i\2\2"+
		"\u01b5\u01b6\7g\2\2\u01b6\u01b7\7t\2\2\u01b7n\3\2\2\2\u01b8\u01b9\7p\2"+
		"\2\u01b9\u01ba\7c\2\2\u01ba\u01bb\7v\2\2\u01bb\u01bc\7w\2\2\u01bc\u01bd"+
		"\7t\2\2\u01bd\u01be\7c\2\2\u01be\u01bf\7n\2\2\u01bfp\3\2\2\2\u01c0\u01c1"+
		"\7f\2\2\u01c1\u01c2\7q\2\2\u01c2\u01c3\7w\2\2\u01c3\u01c4\7d\2\2\u01c4"+
		"\u01c5\7n\2\2\u01c5\u01c6\7g\2\2\u01c6r\3\2\2\2\u01c7\u01c8\7u\2\2\u01c8"+
		"\u01c9\7v\2\2\u01c9\u01ca\7t\2\2\u01ca\u01cb\7k\2\2\u01cb\u01cc\7p\2\2"+
		"\u01cc\u01cd\7i\2\2\u01cdt\3\2\2\2\u01ce\u01cf\7d\2\2\u01cf\u01d0\7q\2"+
		"\2\u01d0\u01d1\7q\2\2\u01d1\u01d2\7n\2\2\u01d2\u01d3\7g\2\2\u01d3\u01d4"+
		"\7c\2\2\u01d4\u01d5\7p\2\2\u01d5v\3\2\2\2\u01d6\u01d7\7f\2\2\u01d7\u01d8"+
		"\7c\2\2\u01d8\u01d9\7v\2\2\u01d9\u01da\7g\2\2\u01dax\3\2\2\2\u01db\u01dc"+
		"\7g\2\2\u01dc\u01dd\7o\2\2\u01dd\u01de\7r\2\2\u01de\u01df\7v\2\2\u01df"+
		"\u01e0\7{\2\2\u01e0z\3\2\2\2\u01e1\u01e2\7v\2\2\u01e2\u01e3\7t\2\2\u01e3"+
		"\u01e4\7w\2\2\u01e4\u01eb\7g\2\2\u01e5\u01e6\7h\2\2\u01e6\u01e7\7c\2\2"+
		"\u01e7\u01e8\7n\2\2\u01e8\u01e9\7u\2\2\u01e9\u01eb\7g\2\2\u01ea\u01e1"+
		"\3\2\2\2\u01ea\u01e5\3\2\2\2\u01eb|\3\2\2\2\u01ec\u01ee\5_\60\2\u01ed"+
		"\u01ec\3\2\2\2\u01ed\u01ee\3\2\2\2\u01ee\u01f0\3\2\2\2\u01ef\u01f1\5\u008f"+
		"H\2\u01f0\u01ef\3\2\2\2\u01f1\u01f2\3\2\2\2\u01f2\u01f0\3\2\2\2\u01f2"+
		"\u01f3\3\2\2\2\u01f3~\3\2\2\2\u01f4\u01f6\5a\61\2\u01f5\u01f7\5\u008f"+
		"H\2\u01f6\u01f5\3\2\2\2\u01f7\u01f8\3\2\2\2\u01f8\u01f6\3\2\2\2\u01f8"+
		"\u01f9\3\2\2\2\u01f9\u0080\3\2\2\2\u01fa\u01fd\5_\60\2\u01fb\u01fd\5a"+
		"\61\2\u01fc\u01fa\3\2\2\2\u01fc\u01fb\3\2\2\2\u01fc\u01fd\3\2\2\2\u01fd"+
		"\u01ff\3\2\2\2\u01fe\u0200\5\u008fH\2\u01ff\u01fe\3\2\2\2\u0200\u0201"+
		"\3\2\2\2\u0201\u01ff\3\2\2\2\u0201\u0202\3\2\2\2\u0202\u0203\3\2\2\2\u0203"+
		"\u0205\5U+\2\u0204\u0206\5\u008fH\2\u0205\u0204\3\2\2\2\u0206\u0207\3"+
		"\2\2\2\u0207\u0205\3\2\2\2\u0207\u0208\3\2\2\2\u0208\u0082\3\2\2\2\u0209"+
		"\u020d\5Y-\2\u020a\u020c\n\2\2\2\u020b\u020a\3\2\2\2\u020c\u020f\3\2\2"+
		"\2\u020d\u020b\3\2\2\2\u020d\u020e\3\2\2\2\u020e\u0210\3\2\2\2\u020f\u020d"+
		"\3\2\2\2\u0210\u0211\5Y-\2\u0211\u0084\3\2\2\2\u0212\u0216\5c\62\2\u0213"+
		"\u0215\n\3\2\2\u0214\u0213\3\2\2\2\u0215\u0218\3\2\2\2\u0216\u0214\3\2"+
		"\2\2\u0216\u0217\3\2\2\2\u0217\u0219\3\2\2\2\u0218\u0216\3\2\2\2\u0219"+
		"\u021a\5c\62\2\u021a\u0086\3\2\2\2\u021b\u021c\5G$\2\u021c\u021d\5\u008f"+
		"H\2\u021d\u021e\5\u008fH\2\u021e\u0224\5\u008fH\2\u021f\u0220\5U+\2\u0220"+
		"\u0221\5\u008fH\2\u0221\u0222\5\u008fH\2\u0222\u0223\5\u008fH\2\u0223"+
		"\u0225\3\2\2\2\u0224\u021f\3\2\2\2\u0225\u0226\3\2\2\2\u0226\u0224\3\2"+
		"\2\2\u0226\u0227\3\2\2\2\u0227\u0088\3\2\2\2\u0228\u0229\5}?\2\u0229\u022a"+
		"\5a\61\2\u022a\u022c\3\2\2\2\u022b\u0228\3\2\2\2\u022c\u022d\3\2\2\2\u022d"+
		"\u022b\3\2\2\2\u022d\u022e\3\2\2\2\u022e\u022f\3\2\2\2\u022f\u0230\5}"+
		"?\2\u0230\u0233\3\2\2\2\u0231\u0233\5}?\2\u0232\u022b\3\2\2\2\u0232\u0231"+
		"\3\2\2\2\u0233\u008a\3\2\2\2\u0234\u0238\7]\2\2\u0235\u0239\5\u0081A\2"+
		"\u0236\u0239\5\177@\2\u0237\u0239\5}?\2\u0238\u0235\3\2\2\2\u0238\u0236"+
		"\3\2\2\2\u0238\u0237\3\2\2\2\u0239\u0240\3\2\2\2\u023a\u023e\5S*\2\u023b"+
		"\u023f\5\u0081A\2\u023c\u023f\5\177@\2\u023d\u023f\5}?\2\u023e\u023b\3"+
		"\2\2\2\u023e\u023c\3\2\2\2\u023e\u023d\3\2\2\2\u023f\u0241\3\2\2\2\u0240"+
		"\u023a\3\2\2\2\u0241\u0242\3\2\2\2\u0242\u0240\3\2\2\2\u0242\u0243\3\2"+
		"\2\2\u0243\u0244\3\2\2\2\u0244\u0245\7_\2\2\u0245\u008c\3\2\2\2\u0246"+
		"\u024d\5\u0091I\2\u0247\u024c\5\u008fH\2\u0248\u024c\5\u0091I\2\u0249"+
		"\u024c\5a\61\2\u024a\u024c\5e\63\2\u024b\u0247\3\2\2\2\u024b\u0248\3\2"+
		"\2\2\u024b\u0249\3\2\2\2\u024b\u024a\3\2\2\2\u024c\u024f\3\2\2\2\u024d"+
		"\u024b\3\2\2\2\u024d\u024e\3\2\2\2\u024e\u008e\3\2\2\2\u024f\u024d\3\2"+
		"\2\2\u0250\u0251\t\4\2\2\u0251\u0090\3\2\2\2\u0252\u0253\t\5\2\2\u0253"+
		"\u0092\3\2\2\2\u0254\u0256\5\u009bN\2\u0255\u0254\3\2\2\2\u0256\u0257"+
		"\3\2\2\2\u0257\u0255\3\2\2\2\u0257\u0258\3\2\2\2\u0258\u025c\3\2\2\2\u0259"+
		"\u025b\5\u0099M\2\u025a\u0259\3\2\2\2\u025b\u025e\3\2\2\2\u025c\u025a"+
		"\3\2\2\2\u025c\u025d\3\2\2\2\u025d\u025f\3\2\2\2\u025e\u025c\3\2\2\2\u025f"+
		"\u0260\bJ\4\2\u0260\u0094\3\2\2\2\u0261\u0263\5\u0099M\2\u0262\u0261\3"+
		"\2\2\2\u0263\u0264\3\2\2\2\u0264\u0262\3\2\2\2\u0264\u0265\3\2\2\2\u0265"+
		"\u0267\3\2\2\2\u0266\u0268\7\2\2\3\u0267\u0266\3\2\2\2\u0267\u0268\3\2"+
		"\2\2\u0268\u0269\3\2\2\2\u0269\u026a\bK\5\2\u026a\u0096\3\2\2\2\u026b"+
		"\u026f\7%\2\2\u026c\u026e\13\2\2\2\u026d\u026c\3\2\2\2\u026e\u0271\3\2"+
		"\2\2\u026f\u0270\3\2\2\2\u026f\u026d\3\2\2\2\u0270\u0272\3\2\2\2\u0271"+
		"\u026f\3\2\2\2\u0272\u0273\5\u009bN\2\u0273\u0098\3\2\2\2\u0274\u0275"+
		"\t\6\2\2\u0275\u009a\3\2\2\2\u0276\u0278\7\17\2\2\u0277\u0276\3\2\2\2"+
		"\u0277\u0278\3\2\2\2\u0278\u0279\3\2\2\2\u0279\u027c\7\f\2\2\u027a\u027c"+
		"\7\f\2\2\u027b\u0277\3\2\2\2\u027b\u027a\3\2\2\2\u027c\u009c\3\2\2\2\u027d"+
		"\u027e\7k\2\2\u027e\u027f\7p\2\2\u027f\u0280\7f\2\2\u0280\u0281\7g\2\2"+
		"\u0281\u0282\7p\2\2\u0282\u0283\7v\2\2\u0283\u009e\3\2\2\2\u0284\u0285"+
		"\7f\2\2\u0285\u0286\7g\2\2\u0286\u0287\7f\2\2\u0287\u0288\7g\2\2\u0288"+
		"\u0289\7p\2\2\u0289\u028a\7v\2\2\u028a\u00a0\3\2\2\2\u028b\u028c\13\2"+
		"\2\2\u028c\u00a2\3\2\2\2\35\2\u0185\u0193\u01ea\u01ed\u01f2\u01f8\u01fc"+
		"\u0201\u0207\u020d\u0216\u0226\u022d\u0232\u0238\u023e\u0242\u024b\u024d"+
		"\u0257\u025c\u0264\u0267\u026f\u0277\u027b\6\3\"\2\3.\3\3J\4\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}