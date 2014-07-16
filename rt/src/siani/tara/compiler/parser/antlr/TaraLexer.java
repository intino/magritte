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
		ON=8, IS=9, WITH=10, PRIVATE=11, SINGLE=12, REQUIRED=13, ROOT=14, TERMINAL=15, 
		NAMED=16, PROPERTY=17, LEFT_PARENTHESIS=18, RIGHT_PARENTHESIS=19, LEFT_SQUARE=20, 
		RIGHT_SQUARE=21, LIST=22, OPEN_BRACKET=23, CLOSE_BRACKET=24, AMPERSAND=25, 
		DOLLAR=26, EURO=27, PERCENTAGE=28, GRADE=29, COLON=30, COMMA=31, DOT=32, 
		EQUALS=33, APHOSTROPHE=34, SEMICOLON=35, STAR=36, POSITIVE=37, DASH=38, 
		DASHES=39, VAR=40, WORD=41, RESOURCE=42, REFERENCE_TYPE=43, COORDINATE_TYPE=44, 
		INT_TYPE=45, NATURAL_TYPE=46, DOUBLE_TYPE=47, STRING_TYPE=48, BOOLEAN_TYPE=49, 
		DATE_TYPE=50, EMPTY=51, BOOLEAN_VALUE=52, NATURAL_VALUE=53, NEGATIVE_VALUE=54, 
		DOUBLE_VALUE=55, STRING_VALUE=56, STRING_MULTILINE_VALUE_KEY=57, CODE_VALUE=58, 
		DATE_VALUE=59, COORDINATE_VALUE=60, IDENTIFIER=61, DIGIT=62, LETTER=63, 
		NEWLINE=64, SPACES=65, DOC=66, SP=67, NL=68, NEW_LINE_INDENT=69, DEDENT=70, 
		UNKNOWN_TOKEN=71;
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
		"METAIDENTIFIER", "INTENTION", "CASE", "USE", "BOX", "METAMODEL", "AS", 
		"ON", "IS", "WITH", "PRIVATE", "SINGLE", "REQUIRED", "ROOT", "TERMINAL", 
		"NAMED", "PROPERTY", "LEFT_PARENTHESIS", "RIGHT_PARENTHESIS", "LEFT_SQUARE", 
		"RIGHT_SQUARE", "LIST", "OPEN_BRACKET", "CLOSE_BRACKET", "AMPERSAND", 
		"DOLLAR", "EURO", "PERCENTAGE", "GRADE", "COLON", "COMMA", "DOT", "EQUALS", 
		"APHOSTROPHE", "SEMICOLON", "STAR", "POSITIVE", "DASH", "DASHES", "VAR", 
		"WORD", "RESOURCE", "REFERENCE_TYPE", "COORDINATE_TYPE", "INT_TYPE", "NATURAL_TYPE", 
		"DOUBLE_TYPE", "STRING_TYPE", "BOOLEAN_TYPE", "DATE_TYPE", "EMPTY", "BOOLEAN_VALUE", 
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
		case 22: OPEN_BRACKET_action((RuleContext)_localctx, actionIndex); break;
		case 23: CLOSE_BRACKET_action((RuleContext)_localctx, actionIndex); break;
		case 34: SEMICOLON_action((RuleContext)_localctx, actionIndex); break;
		case 63: NEWLINE_action((RuleContext)_localctx, actionIndex); break;
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2I\u022c\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\13"+
		"\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\24\3\24"+
		"\3\25\3\25\3\26\3\26\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\31\3\31\3\31"+
		"\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3!"+
		"\3!\3\"\3\"\3#\3#\3$\6$\u0125\n$\r$\16$\u0126\3$\3$\3%\3%\3&\3&\3\'\3"+
		"\'\3(\3(\6(\u0133\n(\r(\16(\u0134\3)\3)\3)\3)\3*\3*\3*\3*\3*\3+\3+\3+"+
		"\3+\3+\3+\3+\3+\3+\3,\3,\3,\3,\3,\3,\3,\3,\3,\3,\3-\3-\3-\3-\3-\3-\3-"+
		"\3-\3-\3-\3-\3.\3.\3.\3.\3.\3.\3.\3.\3/\3/\3/\3/\3/\3/\3/\3/\3\60\3\60"+
		"\3\60\3\60\3\60\3\60\3\60\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\62\3\62"+
		"\3\62\3\62\3\62\3\62\3\62\3\62\3\63\3\63\3\63\3\63\3\63\3\64\3\64\3\64"+
		"\3\64\3\64\3\64\3\65\3\65\3\65\3\65\3\65\3\65\3\65\3\65\3\65\5\65\u0198"+
		"\n\65\3\66\5\66\u019b\n\66\3\66\6\66\u019e\n\66\r\66\16\66\u019f\3\67"+
		"\3\67\6\67\u01a4\n\67\r\67\16\67\u01a5\38\38\58\u01aa\n8\38\68\u01ad\n"+
		"8\r8\168\u01ae\38\38\68\u01b3\n8\r8\168\u01b4\39\39\79\u01b9\n9\f9\16"+
		"9\u01bc\139\39\39\3:\3:\7:\u01c2\n:\f:\16:\u01c5\13:\3:\3:\3;\3;\3;\6"+
		";\u01cc\n;\r;\16;\u01cd\3<\3<\3<\6<\u01d3\n<\r<\16<\u01d4\3<\3<\3<\5<"+
		"\u01da\n<\3=\3=\3=\6=\u01df\n=\r=\16=\u01e0\3=\3=\3=\5=\u01e6\n=\3>\3"+
		">\3>\7>\u01eb\n>\f>\16>\u01ee\13>\3?\3?\3@\3@\3A\6A\u01f5\nA\rA\16A\u01f6"+
		"\3A\7A\u01fa\nA\fA\16A\u01fd\13A\3A\3A\3B\6B\u0202\nB\rB\16B\u0203\3B"+
		"\5B\u0207\nB\3B\3B\3C\3C\7C\u020d\nC\fC\16C\u0210\13C\3C\3C\3D\3D\3E\5"+
		"E\u0217\nE\3E\3E\5E\u021b\nE\3F\3F\3F\3F\3F\3F\3F\3G\3G\3G\3G\3G\3G\3"+
		"G\3H\3H\3\u020e\2I\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r"+
		"\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33"+
		"\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a\62c\63"+
		"e\64g\65i\66k\67m8o9q:s;u<w=y>{?}@\177A\u0081B\u0083C\u0085D\u0087E\u0089"+
		"F\u008bG\u008dH\u008fI\3\2\7\3\2))\3\2//\3\2\62;\4\2C\\c|\4\2\13\13\""+
		"\"\u0246\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2"+
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
		"\2\u008d\3\2\2\2\2\u008f\3\2\2\2\3\u0091\3\2\2\2\5\u0099\3\2\2\2\7\u00a3"+
		"\3\2\2\2\t\u00a8\3\2\2\2\13\u00ac\3\2\2\2\r\u00b0\3\2\2\2\17\u00ba\3\2"+
		"\2\2\21\u00bd\3\2\2\2\23\u00c0\3\2\2\2\25\u00c3\3\2\2\2\27\u00c8\3\2\2"+
		"\2\31\u00d0\3\2\2\2\33\u00d7\3\2\2\2\35\u00e0\3\2\2\2\37\u00e5\3\2\2\2"+
		"!\u00ee\3\2\2\2#\u00f4\3\2\2\2%\u00fd\3\2\2\2\'\u00ff\3\2\2\2)\u0101\3"+
		"\2\2\2+\u0103\3\2\2\2-\u0105\3\2\2\2/\u0109\3\2\2\2\61\u010c\3\2\2\2\63"+
		"\u010f\3\2\2\2\65\u0111\3\2\2\2\67\u0113\3\2\2\29\u0115\3\2\2\2;\u0117"+
		"\3\2\2\2=\u0119\3\2\2\2?\u011b\3\2\2\2A\u011d\3\2\2\2C\u011f\3\2\2\2E"+
		"\u0121\3\2\2\2G\u0124\3\2\2\2I\u012a\3\2\2\2K\u012c\3\2\2\2M\u012e\3\2"+
		"\2\2O\u0130\3\2\2\2Q\u0136\3\2\2\2S\u013a\3\2\2\2U\u013f\3\2\2\2W\u0148"+
		"\3\2\2\2Y\u0152\3\2\2\2[\u015d\3\2\2\2]\u0165\3\2\2\2_\u016d\3\2\2\2a"+
		"\u0174\3\2\2\2c\u017b\3\2\2\2e\u0183\3\2\2\2g\u0188\3\2\2\2i\u0197\3\2"+
		"\2\2k\u019a\3\2\2\2m\u01a1\3\2\2\2o\u01a9\3\2\2\2q\u01b6\3\2\2\2s\u01bf"+
		"\3\2\2\2u\u01c8\3\2\2\2w\u01d9\3\2\2\2y\u01e5\3\2\2\2{\u01e7\3\2\2\2}"+
		"\u01ef\3\2\2\2\177\u01f1\3\2\2\2\u0081\u01f4\3\2\2\2\u0083\u0201\3\2\2"+
		"\2\u0085\u020a\3\2\2\2\u0087\u0213\3\2\2\2\u0089\u021a\3\2\2\2\u008b\u021c"+
		"\3\2\2\2\u008d\u0223\3\2\2\2\u008f\u022a\3\2\2\2\u0091\u0092\7E\2\2\u0092"+
		"\u0093\7q\2\2\u0093\u0094\7p\2\2\u0094\u0095\7e\2\2\u0095\u0096\7g\2\2"+
		"\u0096\u0097\7r\2\2\u0097\u0098\7v\2\2\u0098\4\3\2\2\2\u0099\u009a\7K"+
		"\2\2\u009a\u009b\7p\2\2\u009b\u009c\7v\2\2\u009c\u009d\7g\2\2\u009d\u009e"+
		"\7p\2\2\u009e\u009f\7v\2\2\u009f\u00a0\7k\2\2\u00a0\u00a1\7q\2\2\u00a1"+
		"\u00a2\7p\2\2\u00a2\6\3\2\2\2\u00a3\u00a4\7e\2\2\u00a4\u00a5\7c\2\2\u00a5"+
		"\u00a6\7u\2\2\u00a6\u00a7\7g\2\2\u00a7\b\3\2\2\2\u00a8\u00a9\7w\2\2\u00a9"+
		"\u00aa\7u\2\2\u00aa\u00ab\7g\2\2\u00ab\n\3\2\2\2\u00ac\u00ad\7d\2\2\u00ad"+
		"\u00ae\7q\2\2\u00ae\u00af\7z\2\2\u00af\f\3\2\2\2\u00b0\u00b1\7o\2\2\u00b1"+
		"\u00b2\7g\2\2\u00b2\u00b3\7v\2\2\u00b3\u00b4\7c\2\2\u00b4\u00b5\7o\2\2"+
		"\u00b5\u00b6\7q\2\2\u00b6\u00b7\7f\2\2\u00b7\u00b8\7g\2\2\u00b8\u00b9"+
		"\7n\2\2\u00b9\16\3\2\2\2\u00ba\u00bb\7c\2\2\u00bb\u00bc\7u\2\2\u00bc\20"+
		"\3\2\2\2\u00bd\u00be\7q\2\2\u00be\u00bf\7p\2\2\u00bf\22\3\2\2\2\u00c0"+
		"\u00c1\7k\2\2\u00c1\u00c2\7u\2\2\u00c2\24\3\2\2\2\u00c3\u00c4\7y\2\2\u00c4"+
		"\u00c5\7k\2\2\u00c5\u00c6\7v\2\2\u00c6\u00c7\7j\2\2\u00c7\26\3\2\2\2\u00c8"+
		"\u00c9\7r\2\2\u00c9\u00ca\7t\2\2\u00ca\u00cb\7k\2\2\u00cb\u00cc\7x\2\2"+
		"\u00cc\u00cd\7c\2\2\u00cd\u00ce\7v\2\2\u00ce\u00cf\7g\2\2\u00cf\30\3\2"+
		"\2\2\u00d0\u00d1\7u\2\2\u00d1\u00d2\7k\2\2\u00d2\u00d3\7p\2\2\u00d3\u00d4"+
		"\7i\2\2\u00d4\u00d5\7n\2\2\u00d5\u00d6\7g\2\2\u00d6\32\3\2\2\2\u00d7\u00d8"+
		"\7t\2\2\u00d8\u00d9\7g\2\2\u00d9\u00da\7s\2\2\u00da\u00db\7w\2\2\u00db"+
		"\u00dc\7k\2\2\u00dc\u00dd\7t\2\2\u00dd\u00de\7g\2\2\u00de\u00df\7f\2\2"+
		"\u00df\34\3\2\2\2\u00e0\u00e1\7t\2\2\u00e1\u00e2\7q\2\2\u00e2\u00e3\7"+
		"q\2\2\u00e3\u00e4\7v\2\2\u00e4\36\3\2\2\2\u00e5\u00e6\7v\2\2\u00e6\u00e7"+
		"\7g\2\2\u00e7\u00e8\7t\2\2\u00e8\u00e9\7o\2\2\u00e9\u00ea\7k\2\2\u00ea"+
		"\u00eb\7p\2\2\u00eb\u00ec\7c\2\2\u00ec\u00ed\7n\2\2\u00ed \3\2\2\2\u00ee"+
		"\u00ef\7p\2\2\u00ef\u00f0\7c\2\2\u00f0\u00f1\7o\2\2\u00f1\u00f2\7g\2\2"+
		"\u00f2\u00f3\7f\2\2\u00f3\"\3\2\2\2\u00f4\u00f5\7r\2\2\u00f5\u00f6\7t"+
		"\2\2\u00f6\u00f7\7q\2\2\u00f7\u00f8\7r\2\2\u00f8\u00f9\7g\2\2\u00f9\u00fa"+
		"\7t\2\2\u00fa\u00fb\7v\2\2\u00fb\u00fc\7{\2\2\u00fc$\3\2\2\2\u00fd\u00fe"+
		"\7*\2\2\u00fe&\3\2\2\2\u00ff\u0100\7+\2\2\u0100(\3\2\2\2\u0101\u0102\7"+
		"]\2\2\u0102*\3\2\2\2\u0103\u0104\7_\2\2\u0104,\3\2\2\2\u0105\u0106\7\60"+
		"\2\2\u0106\u0107\7\60\2\2\u0107\u0108\7\60\2\2\u0108.\3\2\2\2\u0109\u010a"+
		"\7}\2\2\u010a\u010b\b\30\2\2\u010b\60\3\2\2\2\u010c\u010d\7\177\2\2\u010d"+
		"\u010e\b\31\3\2\u010e\62\3\2\2\2\u010f\u0110\7(\2\2\u0110\64\3\2\2\2\u0111"+
		"\u0112\7&\2\2\u0112\66\3\2\2\2\u0113\u0114\7\u20ae\2\2\u01148\3\2\2\2"+
		"\u0115\u0116\7\'\2\2\u0116:\3\2\2\2\u0117\u0118\7\u00bc\2\2\u0118<\3\2"+
		"\2\2\u0119\u011a\7<\2\2\u011a>\3\2\2\2\u011b\u011c\7.\2\2\u011c@\3\2\2"+
		"\2\u011d\u011e\7\60\2\2\u011eB\3\2\2\2\u011f\u0120\7?\2\2\u0120D\3\2\2"+
		"\2\u0121\u0122\7)\2\2\u0122F\3\2\2\2\u0123\u0125\7=\2\2\u0124\u0123\3"+
		"\2\2\2\u0125\u0126\3\2\2\2\u0126\u0124\3\2\2\2\u0126\u0127\3\2\2\2\u0127"+
		"\u0128\3\2\2\2\u0128\u0129\b$\4\2\u0129H\3\2\2\2\u012a\u012b\7,\2\2\u012b"+
		"J\3\2\2\2\u012c\u012d\7-\2\2\u012dL\3\2\2\2\u012e\u012f\7/\2\2\u012fN"+
		"\3\2\2\2\u0130\u0132\5M\'\2\u0131\u0133\5M\'\2\u0132\u0131\3\2\2\2\u0133"+
		"\u0134\3\2\2\2\u0134\u0132\3\2\2\2\u0134\u0135\3\2\2\2\u0135P\3\2\2\2"+
		"\u0136\u0137\7x\2\2\u0137\u0138\7c\2\2\u0138\u0139\7t\2\2\u0139R\3\2\2"+
		"\2\u013a\u013b\7y\2\2\u013b\u013c\7q\2\2\u013c\u013d\7t\2\2\u013d\u013e"+
		"\7f\2\2\u013eT\3\2\2\2\u013f\u0140\7t\2\2\u0140\u0141\7g\2\2\u0141\u0142"+
		"\7u\2\2\u0142\u0143\7q\2\2\u0143\u0144\7w\2\2\u0144\u0145\7t\2\2\u0145"+
		"\u0146\7e\2\2\u0146\u0147\7g\2\2\u0147V\3\2\2\2\u0148\u0149\7t\2\2\u0149"+
		"\u014a\7g\2\2\u014a\u014b\7h\2\2\u014b\u014c\7g\2\2\u014c\u014d\7t\2\2"+
		"\u014d\u014e\7g\2\2\u014e\u014f\7p\2\2\u014f\u0150\7e\2\2\u0150\u0151"+
		"\7g\2\2\u0151X\3\2\2\2\u0152\u0153\7e\2\2\u0153\u0154\7q\2\2\u0154\u0155"+
		"\7q\2\2\u0155\u0156\7t\2\2\u0156\u0157\7f\2\2\u0157\u0158\7k\2\2\u0158"+
		"\u0159\7p\2\2\u0159\u015a\7c\2\2\u015a\u015b\7v\2\2\u015b\u015c\7g\2\2"+
		"\u015cZ\3\2\2\2\u015d\u015e\7k\2\2\u015e\u015f\7p\2\2\u015f\u0160\7v\2"+
		"\2\u0160\u0161\7g\2\2\u0161\u0162\7i\2\2\u0162\u0163\7g\2\2\u0163\u0164"+
		"\7t\2\2\u0164\\\3\2\2\2\u0165\u0166\7p\2\2\u0166\u0167\7c\2\2\u0167\u0168"+
		"\7v\2\2\u0168\u0169\7w\2\2\u0169\u016a\7t\2\2\u016a\u016b\7c\2\2\u016b"+
		"\u016c\7n\2\2\u016c^\3\2\2\2\u016d\u016e\7f\2\2\u016e\u016f\7q\2\2\u016f"+
		"\u0170\7w\2\2\u0170\u0171\7d\2\2\u0171\u0172\7n\2\2\u0172\u0173\7g\2\2"+
		"\u0173`\3\2\2\2\u0174\u0175\7u\2\2\u0175\u0176\7v\2\2\u0176\u0177\7t\2"+
		"\2\u0177\u0178\7k\2\2\u0178\u0179\7p\2\2\u0179\u017a\7i\2\2\u017ab\3\2"+
		"\2\2\u017b\u017c\7d\2\2\u017c\u017d\7q\2\2\u017d\u017e\7q\2\2\u017e\u017f"+
		"\7n\2\2\u017f\u0180\7g\2\2\u0180\u0181\7c\2\2\u0181\u0182\7p\2\2\u0182"+
		"d\3\2\2\2\u0183\u0184\7f\2\2\u0184\u0185\7c\2\2\u0185\u0186\7v\2\2\u0186"+
		"\u0187\7g\2\2\u0187f\3\2\2\2\u0188\u0189\7g\2\2\u0189\u018a\7o\2\2\u018a"+
		"\u018b\7r\2\2\u018b\u018c\7v\2\2\u018c\u018d\7{\2\2\u018dh\3\2\2\2\u018e"+
		"\u018f\7v\2\2\u018f\u0190\7t\2\2\u0190\u0191\7w\2\2\u0191\u0198\7g\2\2"+
		"\u0192\u0193\7h\2\2\u0193\u0194\7c\2\2\u0194\u0195\7n\2\2\u0195\u0196"+
		"\7u\2\2\u0196\u0198\7g\2\2\u0197\u018e\3\2\2\2\u0197\u0192\3\2\2\2\u0198"+
		"j\3\2\2\2\u0199\u019b\5K&\2\u019a\u0199\3\2\2\2\u019a\u019b\3\2\2\2\u019b"+
		"\u019d\3\2\2\2\u019c\u019e\5}?\2\u019d\u019c\3\2\2\2\u019e\u019f\3\2\2"+
		"\2\u019f\u019d\3\2\2\2\u019f\u01a0\3\2\2\2\u01a0l\3\2\2\2\u01a1\u01a3"+
		"\5M\'\2\u01a2\u01a4\5}?\2\u01a3\u01a2\3\2\2\2\u01a4\u01a5\3\2\2\2\u01a5"+
		"\u01a3\3\2\2\2\u01a5\u01a6\3\2\2\2\u01a6n\3\2\2\2\u01a7\u01aa\5K&\2\u01a8"+
		"\u01aa\5M\'\2\u01a9\u01a7\3\2\2\2\u01a9\u01a8\3\2\2\2\u01a9\u01aa\3\2"+
		"\2\2\u01aa\u01ac\3\2\2\2\u01ab\u01ad\5}?\2\u01ac\u01ab\3\2\2\2\u01ad\u01ae"+
		"\3\2\2\2\u01ae\u01ac\3\2\2\2\u01ae\u01af\3\2\2\2\u01af\u01b0\3\2\2\2\u01b0"+
		"\u01b2\5A!\2\u01b1\u01b3\5}?\2\u01b2\u01b1\3\2\2\2\u01b3\u01b4\3\2\2\2"+
		"\u01b4\u01b2\3\2\2\2\u01b4\u01b5\3\2\2\2\u01b5p\3\2\2\2\u01b6\u01ba\5"+
		"E#\2\u01b7\u01b9\n\2\2\2\u01b8\u01b7\3\2\2\2\u01b9\u01bc\3\2\2\2\u01ba"+
		"\u01b8\3\2\2\2\u01ba\u01bb\3\2\2\2\u01bb\u01bd\3\2\2\2\u01bc\u01ba\3\2"+
		"\2\2\u01bd\u01be\5E#\2\u01ber\3\2\2\2\u01bf\u01c3\5O(\2\u01c0\u01c2\n"+
		"\3\2\2\u01c1\u01c0\3\2\2\2\u01c2\u01c5\3\2\2\2\u01c3\u01c1\3\2\2\2\u01c3"+
		"\u01c4\3\2\2\2\u01c4\u01c6\3\2\2\2\u01c5\u01c3\3\2\2\2\u01c6\u01c7\5O"+
		"(\2\u01c7t\3\2\2\2\u01c8\u01cb\5\63\32\2\u01c9\u01cc\5}?\2\u01ca\u01cc"+
		"\5\177@\2\u01cb\u01c9\3\2\2\2\u01cb\u01ca\3\2\2\2\u01cc\u01cd\3\2\2\2"+
		"\u01cd\u01cb\3\2\2\2\u01cd\u01ce\3\2\2\2\u01cev\3\2\2\2\u01cf\u01d0\5"+
		"k\66\2\u01d0\u01d1\5M\'\2\u01d1\u01d3\3\2\2\2\u01d2\u01cf\3\2\2\2\u01d3"+
		"\u01d4\3\2\2\2\u01d4\u01d2\3\2\2\2\u01d4\u01d5\3\2\2\2\u01d5\u01d6\3\2"+
		"\2\2\u01d6\u01d7\5k\66\2\u01d7\u01da\3\2\2\2\u01d8\u01da\5k\66\2\u01d9"+
		"\u01d2\3\2\2\2\u01d9\u01d8\3\2\2\2\u01dax\3\2\2\2\u01db\u01dc\5o8\2\u01dc"+
		"\u01dd\5M\'\2\u01dd\u01df\3\2\2\2\u01de\u01db\3\2\2\2\u01df\u01e0\3\2"+
		"\2\2\u01e0\u01de\3\2\2\2\u01e0\u01e1\3\2\2\2\u01e1\u01e2\3\2\2\2\u01e2"+
		"\u01e3\5o8\2\u01e3\u01e6\3\2\2\2\u01e4\u01e6\5o8\2\u01e5\u01de\3\2\2\2"+
		"\u01e5\u01e4\3\2\2\2\u01e6z\3\2\2\2\u01e7\u01ec\5\177@\2\u01e8\u01eb\5"+
		"}?\2\u01e9\u01eb\5\177@\2\u01ea\u01e8\3\2\2\2\u01ea\u01e9\3\2\2\2\u01eb"+
		"\u01ee\3\2\2\2\u01ec\u01ea\3\2\2\2\u01ec\u01ed\3\2\2\2\u01ed|\3\2\2\2"+
		"\u01ee\u01ec\3\2\2\2\u01ef\u01f0\t\4\2\2\u01f0~\3\2\2\2\u01f1\u01f2\t"+
		"\5\2\2\u01f2\u0080\3\2\2\2\u01f3\u01f5\5\u0089E\2\u01f4\u01f3\3\2\2\2"+
		"\u01f5\u01f6\3\2\2\2\u01f6\u01f4\3\2\2\2\u01f6\u01f7\3\2\2\2\u01f7\u01fb"+
		"\3\2\2\2\u01f8\u01fa\5\u0087D\2\u01f9\u01f8\3\2\2\2\u01fa\u01fd\3\2\2"+
		"\2\u01fb\u01f9\3\2\2\2\u01fb\u01fc\3\2\2\2\u01fc\u01fe\3\2\2\2\u01fd\u01fb"+
		"\3\2\2\2\u01fe\u01ff\bA\5\2\u01ff\u0082\3\2\2\2\u0200\u0202\5\u0087D\2"+
		"\u0201\u0200\3\2\2\2\u0202\u0203\3\2\2\2\u0203\u0201\3\2\2\2\u0203\u0204"+
		"\3\2\2\2\u0204\u0206\3\2\2\2\u0205\u0207\7\2\2\3\u0206\u0205\3\2\2\2\u0206"+
		"\u0207\3\2\2\2\u0207\u0208\3\2\2\2\u0208\u0209\bB\6\2\u0209\u0084\3\2"+
		"\2\2\u020a\u020e\7%\2\2\u020b\u020d\13\2\2\2\u020c\u020b\3\2\2\2\u020d"+
		"\u0210\3\2\2\2\u020e\u020f\3\2\2\2\u020e\u020c\3\2\2\2\u020f\u0211\3\2"+
		"\2\2\u0210\u020e\3\2\2\2\u0211\u0212\5\u0089E\2\u0212\u0086\3\2\2\2\u0213"+
		"\u0214\t\6\2\2\u0214\u0088\3\2\2\2\u0215\u0217\7\17\2\2\u0216\u0215\3"+
		"\2\2\2\u0216\u0217\3\2\2\2\u0217\u0218\3\2\2\2\u0218\u021b\7\f\2\2\u0219"+
		"\u021b\7\f\2\2\u021a\u0216\3\2\2\2\u021a\u0219\3\2\2\2\u021b\u008a\3\2"+
		"\2\2\u021c\u021d\7k\2\2\u021d\u021e\7p\2\2\u021e\u021f\7f\2\2\u021f\u0220"+
		"\7g\2\2\u0220\u0221\7p\2\2\u0221\u0222\7v\2\2\u0222\u008c\3\2\2\2\u0223"+
		"\u0224\7f\2\2\u0224\u0225\7g\2\2\u0225\u0226\7f\2\2\u0226\u0227\7g\2\2"+
		"\u0227\u0228\7p\2\2\u0228\u0229\7v\2\2\u0229\u008e\3\2\2\2\u022a\u022b"+
		"\13\2\2\2\u022b\u0090\3\2\2\2\35\2\u0126\u0134\u0197\u019a\u019f\u01a5"+
		"\u01a9\u01ae\u01b4\u01ba\u01c3\u01cb\u01cd\u01d4\u01d9\u01e0\u01e5\u01ea"+
		"\u01ec\u01f6\u01fb\u0203\u0206\u020e\u0216\u021a\7\3\30\2\3\31\3\3$\4"+
		"\3A\5\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}