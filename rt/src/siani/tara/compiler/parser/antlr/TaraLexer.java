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
		HAS=8, ON=9, IS=10, WITH=11, IF=12, PRIVATE=13, SINGLE=14, REQUIRED=15, 
		ROOT=16, TERMINAL=17, NAMED=18, PROPERTY=19, LEFT_PARENTHESIS=20, RIGHT_PARENTHESIS=21, 
		LEFT_SQUARE=22, RIGHT_SQUARE=23, LIST=24, OPEN_BRACKET=25, CLOSE_BRACKET=26, 
		AMPERSAND=27, DOLLAR=28, EURO=29, PERCENTAGE=30, GRADE=31, COLON=32, COMMA=33, 
		DOT=34, EQUALS=35, APHOSTROPHE=36, SEMICOLON=37, STAR=38, POSITIVE=39, 
		DASH=40, DASHES=41, UNDERDASH=42, VAR=43, WORD=44, RESOURCE=45, REFERENCE_TYPE=46, 
		COORDINATE_TYPE=47, INT_TYPE=48, NATURAL_TYPE=49, DOUBLE_TYPE=50, STRING_TYPE=51, 
		BOOLEAN_TYPE=52, DATE_TYPE=53, EMPTY=54, BOOLEAN_VALUE=55, NATURAL_VALUE=56, 
		NEGATIVE_VALUE=57, DOUBLE_VALUE=58, STRING_VALUE=59, STRING_MULTILINE_VALUE_KEY=60, 
		CODE_VALUE=61, DATE_VALUE=62, COORDINATE_VALUE=63, IDENTIFIER=64, DIGIT=65, 
		LETTER=66, NEWLINE=67, SPACES=68, DOC=69, SP=70, NL=71, NEW_LINE_INDENT=72, 
		DEDENT=73, UNKNOWN_TOKEN=74;
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
		"'F'", "'G'", "'H'", "'I'", "'J'"
	};
	public static final String[] ruleNames = {
		"METAIDENTIFIER", "INTENTION", "CASE", "USE", "BOX", "METAMODEL", "AS", 
		"HAS", "ON", "IS", "WITH", "IF", "PRIVATE", "SINGLE", "REQUIRED", "ROOT", 
		"TERMINAL", "NAMED", "PROPERTY", "LEFT_PARENTHESIS", "RIGHT_PARENTHESIS", 
		"LEFT_SQUARE", "RIGHT_SQUARE", "LIST", "OPEN_BRACKET", "CLOSE_BRACKET", 
		"AMPERSAND", "DOLLAR", "EURO", "PERCENTAGE", "GRADE", "COLON", "COMMA", 
		"DOT", "EQUALS", "APHOSTROPHE", "SEMICOLON", "STAR", "POSITIVE", "DASH", 
		"DASHES", "UNDERDASH", "VAR", "WORD", "RESOURCE", "REFERENCE_TYPE", "COORDINATE_TYPE", 
		"INT_TYPE", "NATURAL_TYPE", "DOUBLE_TYPE", "STRING_TYPE", "BOOLEAN_TYPE", 
		"DATE_TYPE", "EMPTY", "BOOLEAN_VALUE", "NATURAL_VALUE", "NEGATIVE_VALUE", 
		"DOUBLE_VALUE", "STRING_VALUE", "STRING_MULTILINE_VALUE_KEY", "CODE_VALUE", 
		"DATE_VALUE", "COORDINATE_VALUE", "IDENTIFIER", "DIGIT", "LETTER", "NEWLINE", 
		"SPACES", "DOC", "SP", "NL", "NEW_LINE_INDENT", "DEDENT", "UNKNOWN_TOKEN"
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
		case 24: OPEN_BRACKET_action((RuleContext)_localctx, actionIndex); break;
		case 25: CLOSE_BRACKET_action((RuleContext)_localctx, actionIndex); break;
		case 36: SEMICOLON_action((RuleContext)_localctx, actionIndex); break;
		case 66: NEWLINE_action((RuleContext)_localctx, actionIndex); break;
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2L\u023d\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
		"\tI\4J\tJ\4K\tK\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3"+
		"\n\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3"+
		"\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\22\3"+
		"\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3"+
		"\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3"+
		"\27\3\30\3\30\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\33\3\33\3\33\3\34\3"+
		"\34\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3"+
		"%\3&\6&\u0132\n&\r&\16&\u0133\3&\3&\3\'\3\'\3(\3(\3)\3)\3*\3*\6*\u0140"+
		"\n*\r*\16*\u0141\3+\3+\3,\3,\3,\3,\3-\3-\3-\3-\3-\3.\3.\3.\3.\3.\3.\3"+
		".\3.\3.\3/\3/\3/\3/\3/\3/\3/\3/\3/\3/\3\60\3\60\3\60\3\60\3\60\3\60\3"+
		"\60\3\60\3\60\3\60\3\60\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\62\3"+
		"\62\3\62\3\62\3\62\3\62\3\62\3\62\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3"+
		"\64\3\64\3\64\3\64\3\64\3\64\3\64\3\65\3\65\3\65\3\65\3\65\3\65\3\65\3"+
		"\65\3\66\3\66\3\66\3\66\3\66\3\67\3\67\3\67\3\67\3\67\3\67\38\38\38\3"+
		"8\38\38\38\38\38\58\u01a7\n8\39\59\u01aa\n9\39\69\u01ad\n9\r9\169\u01ae"+
		"\3:\3:\6:\u01b3\n:\r:\16:\u01b4\3;\3;\5;\u01b9\n;\3;\6;\u01bc\n;\r;\16"+
		";\u01bd\3;\3;\6;\u01c2\n;\r;\16;\u01c3\3<\3<\7<\u01c8\n<\f<\16<\u01cb"+
		"\13<\3<\3<\3=\3=\7=\u01d1\n=\f=\16=\u01d4\13=\3=\3=\3>\3>\3>\6>\u01db"+
		"\n>\r>\16>\u01dc\3?\3?\3?\6?\u01e2\n?\r?\16?\u01e3\3?\3?\3?\5?\u01e9\n"+
		"?\3@\3@\3@\6@\u01ee\n@\r@\16@\u01ef\3@\3@\3@\5@\u01f5\n@\3A\3A\3A\3A\3"+
		"A\7A\u01fc\nA\fA\16A\u01ff\13A\3B\3B\3C\3C\3D\6D\u0206\nD\rD\16D\u0207"+
		"\3D\7D\u020b\nD\fD\16D\u020e\13D\3D\3D\3E\6E\u0213\nE\rE\16E\u0214\3E"+
		"\5E\u0218\nE\3E\3E\3F\3F\7F\u021e\nF\fF\16F\u0221\13F\3F\3F\3G\3G\3H\5"+
		"H\u0228\nH\3H\3H\5H\u022c\nH\3I\3I\3I\3I\3I\3I\3I\3J\3J\3J\3J\3J\3J\3"+
		"J\3K\3K\3\u021f\2L\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r"+
		"\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33"+
		"\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a\62c\63"+
		"e\64g\65i\66k\67m8o9q:s;u<w=y>{?}@\177A\u0081B\u0083C\u0085D\u0087E\u0089"+
		"F\u008bG\u008dH\u008fI\u0091J\u0093K\u0095L\3\2\7\3\2))\3\2//\3\2\62;"+
		"\4\2C\\c|\4\2\13\13\"\"\u0259\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t"+
		"\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2"+
		"\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2"+
		"\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2"+
		"+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2"+
		"\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2"+
		"C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3"+
		"\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2"+
		"\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2"+
		"i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\2u\3"+
		"\2\2\2\2w\3\2\2\2\2y\3\2\2\2\2{\3\2\2\2\2}\3\2\2\2\2\177\3\2\2\2\2\u0081"+
		"\3\2\2\2\2\u0083\3\2\2\2\2\u0085\3\2\2\2\2\u0087\3\2\2\2\2\u0089\3\2\2"+
		"\2\2\u008b\3\2\2\2\2\u008d\3\2\2\2\2\u008f\3\2\2\2\2\u0091\3\2\2\2\2\u0093"+
		"\3\2\2\2\2\u0095\3\2\2\2\3\u0097\3\2\2\2\5\u009f\3\2\2\2\7\u00a9\3\2\2"+
		"\2\t\u00ae\3\2\2\2\13\u00b2\3\2\2\2\r\u00b6\3\2\2\2\17\u00c0\3\2\2\2\21"+
		"\u00c3\3\2\2\2\23\u00c7\3\2\2\2\25\u00ca\3\2\2\2\27\u00cd\3\2\2\2\31\u00d2"+
		"\3\2\2\2\33\u00d5\3\2\2\2\35\u00dd\3\2\2\2\37\u00e4\3\2\2\2!\u00ed\3\2"+
		"\2\2#\u00f2\3\2\2\2%\u00fb\3\2\2\2\'\u0101\3\2\2\2)\u010a\3\2\2\2+\u010c"+
		"\3\2\2\2-\u010e\3\2\2\2/\u0110\3\2\2\2\61\u0112\3\2\2\2\63\u0116\3\2\2"+
		"\2\65\u0119\3\2\2\2\67\u011c\3\2\2\29\u011e\3\2\2\2;\u0120\3\2\2\2=\u0122"+
		"\3\2\2\2?\u0124\3\2\2\2A\u0126\3\2\2\2C\u0128\3\2\2\2E\u012a\3\2\2\2G"+
		"\u012c\3\2\2\2I\u012e\3\2\2\2K\u0131\3\2\2\2M\u0137\3\2\2\2O\u0139\3\2"+
		"\2\2Q\u013b\3\2\2\2S\u013d\3\2\2\2U\u0143\3\2\2\2W\u0145\3\2\2\2Y\u0149"+
		"\3\2\2\2[\u014e\3\2\2\2]\u0157\3\2\2\2_\u0161\3\2\2\2a\u016c\3\2\2\2c"+
		"\u0174\3\2\2\2e\u017c\3\2\2\2g\u0183\3\2\2\2i\u018a\3\2\2\2k\u0192\3\2"+
		"\2\2m\u0197\3\2\2\2o\u01a6\3\2\2\2q\u01a9\3\2\2\2s\u01b0\3\2\2\2u\u01b8"+
		"\3\2\2\2w\u01c5\3\2\2\2y\u01ce\3\2\2\2{\u01d7\3\2\2\2}\u01e8\3\2\2\2\177"+
		"\u01f4\3\2\2\2\u0081\u01f6\3\2\2\2\u0083\u0200\3\2\2\2\u0085\u0202\3\2"+
		"\2\2\u0087\u0205\3\2\2\2\u0089\u0212\3\2\2\2\u008b\u021b\3\2\2\2\u008d"+
		"\u0224\3\2\2\2\u008f\u022b\3\2\2\2\u0091\u022d\3\2\2\2\u0093\u0234\3\2"+
		"\2\2\u0095\u023b\3\2\2\2\u0097\u0098\7E\2\2\u0098\u0099\7q\2\2\u0099\u009a"+
		"\7p\2\2\u009a\u009b\7e\2\2\u009b\u009c\7g\2\2\u009c\u009d\7r\2\2\u009d"+
		"\u009e\7v\2\2\u009e\4\3\2\2\2\u009f\u00a0\7K\2\2\u00a0\u00a1\7p\2\2\u00a1"+
		"\u00a2\7v\2\2\u00a2\u00a3\7g\2\2\u00a3\u00a4\7p\2\2\u00a4\u00a5\7v\2\2"+
		"\u00a5\u00a6\7k\2\2\u00a6\u00a7\7q\2\2\u00a7\u00a8\7p\2\2\u00a8\6\3\2"+
		"\2\2\u00a9\u00aa\7e\2\2\u00aa\u00ab\7c\2\2\u00ab\u00ac\7u\2\2\u00ac\u00ad"+
		"\7g\2\2\u00ad\b\3\2\2\2\u00ae\u00af\7w\2\2\u00af\u00b0\7u\2\2\u00b0\u00b1"+
		"\7g\2\2\u00b1\n\3\2\2\2\u00b2\u00b3\7d\2\2\u00b3\u00b4\7q\2\2\u00b4\u00b5"+
		"\7z\2\2\u00b5\f\3\2\2\2\u00b6\u00b7\7o\2\2\u00b7\u00b8\7g\2\2\u00b8\u00b9"+
		"\7v\2\2\u00b9\u00ba\7c\2\2\u00ba\u00bb\7o\2\2\u00bb\u00bc\7q\2\2\u00bc"+
		"\u00bd\7f\2\2\u00bd\u00be\7g\2\2\u00be\u00bf\7n\2\2\u00bf\16\3\2\2\2\u00c0"+
		"\u00c1\7c\2\2\u00c1\u00c2\7u\2\2\u00c2\20\3\2\2\2\u00c3\u00c4\7j\2\2\u00c4"+
		"\u00c5\7c\2\2\u00c5\u00c6\7u\2\2\u00c6\22\3\2\2\2\u00c7\u00c8\7q\2\2\u00c8"+
		"\u00c9\7p\2\2\u00c9\24\3\2\2\2\u00ca\u00cb\7k\2\2\u00cb\u00cc\7u\2\2\u00cc"+
		"\26\3\2\2\2\u00cd\u00ce\7y\2\2\u00ce\u00cf\7k\2\2\u00cf\u00d0\7v\2\2\u00d0"+
		"\u00d1\7j\2\2\u00d1\30\3\2\2\2\u00d2\u00d3\7k\2\2\u00d3\u00d4\7h\2\2\u00d4"+
		"\32\3\2\2\2\u00d5\u00d6\7r\2\2\u00d6\u00d7\7t\2\2\u00d7\u00d8\7k\2\2\u00d8"+
		"\u00d9\7x\2\2\u00d9\u00da\7c\2\2\u00da\u00db\7v\2\2\u00db\u00dc\7g\2\2"+
		"\u00dc\34\3\2\2\2\u00dd\u00de\7u\2\2\u00de\u00df\7k\2\2\u00df\u00e0\7"+
		"p\2\2\u00e0\u00e1\7i\2\2\u00e1\u00e2\7n\2\2\u00e2\u00e3\7g\2\2\u00e3\36"+
		"\3\2\2\2\u00e4\u00e5\7t\2\2\u00e5\u00e6\7g\2\2\u00e6\u00e7\7s\2\2\u00e7"+
		"\u00e8\7w\2\2\u00e8\u00e9\7k\2\2\u00e9\u00ea\7t\2\2\u00ea\u00eb\7g\2\2"+
		"\u00eb\u00ec\7f\2\2\u00ec \3\2\2\2\u00ed\u00ee\7t\2\2\u00ee\u00ef\7q\2"+
		"\2\u00ef\u00f0\7q\2\2\u00f0\u00f1\7v\2\2\u00f1\"\3\2\2\2\u00f2\u00f3\7"+
		"v\2\2\u00f3\u00f4\7g\2\2\u00f4\u00f5\7t\2\2\u00f5\u00f6\7o\2\2\u00f6\u00f7"+
		"\7k\2\2\u00f7\u00f8\7p\2\2\u00f8\u00f9\7c\2\2\u00f9\u00fa\7n\2\2\u00fa"+
		"$\3\2\2\2\u00fb\u00fc\7p\2\2\u00fc\u00fd\7c\2\2\u00fd\u00fe\7o\2\2\u00fe"+
		"\u00ff\7g\2\2\u00ff\u0100\7f\2\2\u0100&\3\2\2\2\u0101\u0102\7r\2\2\u0102"+
		"\u0103\7t\2\2\u0103\u0104\7q\2\2\u0104\u0105\7r\2\2\u0105\u0106\7g\2\2"+
		"\u0106\u0107\7t\2\2\u0107\u0108\7v\2\2\u0108\u0109\7{\2\2\u0109(\3\2\2"+
		"\2\u010a\u010b\7*\2\2\u010b*\3\2\2\2\u010c\u010d\7+\2\2\u010d,\3\2\2\2"+
		"\u010e\u010f\7]\2\2\u010f.\3\2\2\2\u0110\u0111\7_\2\2\u0111\60\3\2\2\2"+
		"\u0112\u0113\7\60\2\2\u0113\u0114\7\60\2\2\u0114\u0115\7\60\2\2\u0115"+
		"\62\3\2\2\2\u0116\u0117\7}\2\2\u0117\u0118\b\32\2\2\u0118\64\3\2\2\2\u0119"+
		"\u011a\7\177\2\2\u011a\u011b\b\33\3\2\u011b\66\3\2\2\2\u011c\u011d\7("+
		"\2\2\u011d8\3\2\2\2\u011e\u011f\7&\2\2\u011f:\3\2\2\2\u0120\u0121\7\u20ae"+
		"\2\2\u0121<\3\2\2\2\u0122\u0123\7\'\2\2\u0123>\3\2\2\2\u0124\u0125\7\u00bc"+
		"\2\2\u0125@\3\2\2\2\u0126\u0127\7<\2\2\u0127B\3\2\2\2\u0128\u0129\7.\2"+
		"\2\u0129D\3\2\2\2\u012a\u012b\7\60\2\2\u012bF\3\2\2\2\u012c\u012d\7?\2"+
		"\2\u012dH\3\2\2\2\u012e\u012f\7)\2\2\u012fJ\3\2\2\2\u0130\u0132\7=\2\2"+
		"\u0131\u0130\3\2\2\2\u0132\u0133\3\2\2\2\u0133\u0131\3\2\2\2\u0133\u0134"+
		"\3\2\2\2\u0134\u0135\3\2\2\2\u0135\u0136\b&\4\2\u0136L\3\2\2\2\u0137\u0138"+
		"\7,\2\2\u0138N\3\2\2\2\u0139\u013a\7-\2\2\u013aP\3\2\2\2\u013b\u013c\7"+
		"/\2\2\u013cR\3\2\2\2\u013d\u013f\5Q)\2\u013e\u0140\5Q)\2\u013f\u013e\3"+
		"\2\2\2\u0140\u0141\3\2\2\2\u0141\u013f\3\2\2\2\u0141\u0142\3\2\2\2\u0142"+
		"T\3\2\2\2\u0143\u0144\7a\2\2\u0144V\3\2\2\2\u0145\u0146\7x\2\2\u0146\u0147"+
		"\7c\2\2\u0147\u0148\7t\2\2\u0148X\3\2\2\2\u0149\u014a\7y\2\2\u014a\u014b"+
		"\7q\2\2\u014b\u014c\7t\2\2\u014c\u014d\7f\2\2\u014dZ\3\2\2\2\u014e\u014f"+
		"\7t\2\2\u014f\u0150\7g\2\2\u0150\u0151\7u\2\2\u0151\u0152\7q\2\2\u0152"+
		"\u0153\7w\2\2\u0153\u0154\7t\2\2\u0154\u0155\7e\2\2\u0155\u0156\7g\2\2"+
		"\u0156\\\3\2\2\2\u0157\u0158\7t\2\2\u0158\u0159\7g\2\2\u0159\u015a\7h"+
		"\2\2\u015a\u015b\7g\2\2\u015b\u015c\7t\2\2\u015c\u015d\7g\2\2\u015d\u015e"+
		"\7p\2\2\u015e\u015f\7e\2\2\u015f\u0160\7g\2\2\u0160^\3\2\2\2\u0161\u0162"+
		"\7e\2\2\u0162\u0163\7q\2\2\u0163\u0164\7q\2\2\u0164\u0165\7t\2\2\u0165"+
		"\u0166\7f\2\2\u0166\u0167\7k\2\2\u0167\u0168\7p\2\2\u0168\u0169\7c\2\2"+
		"\u0169\u016a\7v\2\2\u016a\u016b\7g\2\2\u016b`\3\2\2\2\u016c\u016d\7k\2"+
		"\2\u016d\u016e\7p\2\2\u016e\u016f\7v\2\2\u016f\u0170\7g\2\2\u0170\u0171"+
		"\7i\2\2\u0171\u0172\7g\2\2\u0172\u0173\7t\2\2\u0173b\3\2\2\2\u0174\u0175"+
		"\7p\2\2\u0175\u0176\7c\2\2\u0176\u0177\7v\2\2\u0177\u0178\7w\2\2\u0178"+
		"\u0179\7t\2\2\u0179\u017a\7c\2\2\u017a\u017b\7n\2\2\u017bd\3\2\2\2\u017c"+
		"\u017d\7f\2\2\u017d\u017e\7q\2\2\u017e\u017f\7w\2\2\u017f\u0180\7d\2\2"+
		"\u0180\u0181\7n\2\2\u0181\u0182\7g\2\2\u0182f\3\2\2\2\u0183\u0184\7u\2"+
		"\2\u0184\u0185\7v\2\2\u0185\u0186\7t\2\2\u0186\u0187\7k\2\2\u0187\u0188"+
		"\7p\2\2\u0188\u0189\7i\2\2\u0189h\3\2\2\2\u018a\u018b\7d\2\2\u018b\u018c"+
		"\7q\2\2\u018c\u018d\7q\2\2\u018d\u018e\7n\2\2\u018e\u018f\7g\2\2\u018f"+
		"\u0190\7c\2\2\u0190\u0191\7p\2\2\u0191j\3\2\2\2\u0192\u0193\7f\2\2\u0193"+
		"\u0194\7c\2\2\u0194\u0195\7v\2\2\u0195\u0196\7g\2\2\u0196l\3\2\2\2\u0197"+
		"\u0198\7g\2\2\u0198\u0199\7o\2\2\u0199\u019a\7r\2\2\u019a\u019b\7v\2\2"+
		"\u019b\u019c\7{\2\2\u019cn\3\2\2\2\u019d\u019e\7v\2\2\u019e\u019f\7t\2"+
		"\2\u019f\u01a0\7w\2\2\u01a0\u01a7\7g\2\2\u01a1\u01a2\7h\2\2\u01a2\u01a3"+
		"\7c\2\2\u01a3\u01a4\7n\2\2\u01a4\u01a5\7u\2\2\u01a5\u01a7\7g\2\2\u01a6"+
		"\u019d\3\2\2\2\u01a6\u01a1\3\2\2\2\u01a7p\3\2\2\2\u01a8\u01aa\5O(\2\u01a9"+
		"\u01a8\3\2\2\2\u01a9\u01aa\3\2\2\2\u01aa\u01ac\3\2\2\2\u01ab\u01ad\5\u0083"+
		"B\2\u01ac\u01ab\3\2\2\2\u01ad\u01ae\3\2\2\2\u01ae\u01ac\3\2\2\2\u01ae"+
		"\u01af\3\2\2\2\u01afr\3\2\2\2\u01b0\u01b2\5Q)\2\u01b1\u01b3\5\u0083B\2"+
		"\u01b2\u01b1\3\2\2\2\u01b3\u01b4\3\2\2\2\u01b4\u01b2\3\2\2\2\u01b4\u01b5"+
		"\3\2\2\2\u01b5t\3\2\2\2\u01b6\u01b9\5O(\2\u01b7\u01b9\5Q)\2\u01b8\u01b6"+
		"\3\2\2\2\u01b8\u01b7\3\2\2\2\u01b8\u01b9\3\2\2\2\u01b9\u01bb\3\2\2\2\u01ba"+
		"\u01bc\5\u0083B\2\u01bb\u01ba\3\2\2\2\u01bc\u01bd\3\2\2\2\u01bd\u01bb"+
		"\3\2\2\2\u01bd\u01be\3\2\2\2\u01be\u01bf\3\2\2\2\u01bf\u01c1\5E#\2\u01c0"+
		"\u01c2\5\u0083B\2\u01c1\u01c0\3\2\2\2\u01c2\u01c3\3\2\2\2\u01c3\u01c1"+
		"\3\2\2\2\u01c3\u01c4\3\2\2\2\u01c4v\3\2\2\2\u01c5\u01c9\5I%\2\u01c6\u01c8"+
		"\n\2\2\2\u01c7\u01c6\3\2\2\2\u01c8\u01cb\3\2\2\2\u01c9\u01c7\3\2\2\2\u01c9"+
		"\u01ca\3\2\2\2\u01ca\u01cc\3\2\2\2\u01cb\u01c9\3\2\2\2\u01cc\u01cd\5I"+
		"%\2\u01cdx\3\2\2\2\u01ce\u01d2\5S*\2\u01cf\u01d1\n\3\2\2\u01d0\u01cf\3"+
		"\2\2\2\u01d1\u01d4\3\2\2\2\u01d2\u01d0\3\2\2\2\u01d2\u01d3\3\2\2\2\u01d3"+
		"\u01d5\3\2\2\2\u01d4\u01d2\3\2\2\2\u01d5\u01d6\5S*\2\u01d6z\3\2\2\2\u01d7"+
		"\u01da\5\67\34\2\u01d8\u01db\5\u0083B\2\u01d9\u01db\5\u0085C\2\u01da\u01d8"+
		"\3\2\2\2\u01da\u01d9\3\2\2\2\u01db\u01dc\3\2\2\2\u01dc\u01da\3\2\2\2\u01dc"+
		"\u01dd\3\2\2\2\u01dd|\3\2\2\2\u01de\u01df\5q9\2\u01df\u01e0\5Q)\2\u01e0"+
		"\u01e2\3\2\2\2\u01e1\u01de\3\2\2\2\u01e2\u01e3\3\2\2\2\u01e3\u01e1\3\2"+
		"\2\2\u01e3\u01e4\3\2\2\2\u01e4\u01e5\3\2\2\2\u01e5\u01e6\5q9\2\u01e6\u01e9"+
		"\3\2\2\2\u01e7\u01e9\5q9\2\u01e8\u01e1\3\2\2\2\u01e8\u01e7\3\2\2\2\u01e9"+
		"~\3\2\2\2\u01ea\u01eb\5u;\2\u01eb\u01ec\5Q)\2\u01ec\u01ee\3\2\2\2\u01ed"+
		"\u01ea\3\2\2\2\u01ee\u01ef\3\2\2\2\u01ef\u01ed\3\2\2\2\u01ef\u01f0\3\2"+
		"\2\2\u01f0\u01f1\3\2\2\2\u01f1\u01f2\5u;\2\u01f2\u01f5\3\2\2\2\u01f3\u01f5"+
		"\5u;\2\u01f4\u01ed\3\2\2\2\u01f4\u01f3\3\2\2\2\u01f5\u0080\3\2\2\2\u01f6"+
		"\u01fd\5\u0085C\2\u01f7\u01fc\5\u0083B\2\u01f8\u01fc\5\u0085C\2\u01f9"+
		"\u01fc\5Q)\2\u01fa\u01fc\5U+\2\u01fb\u01f7\3\2\2\2\u01fb\u01f8\3\2\2\2"+
		"\u01fb\u01f9\3\2\2\2\u01fb\u01fa\3\2\2\2\u01fc\u01ff\3\2\2\2\u01fd\u01fb"+
		"\3\2\2\2\u01fd\u01fe\3\2\2\2\u01fe\u0082\3\2\2\2\u01ff\u01fd\3\2\2\2\u0200"+
		"\u0201\t\4\2\2\u0201\u0084\3\2\2\2\u0202\u0203\t\5\2\2\u0203\u0086\3\2"+
		"\2\2\u0204\u0206\5\u008fH\2\u0205\u0204\3\2\2\2\u0206\u0207\3\2\2\2\u0207"+
		"\u0205\3\2\2\2\u0207\u0208\3\2\2\2\u0208\u020c\3\2\2\2\u0209\u020b\5\u008d"+
		"G\2\u020a\u0209\3\2\2\2\u020b\u020e\3\2\2\2\u020c\u020a\3\2\2\2\u020c"+
		"\u020d\3\2\2\2\u020d\u020f\3\2\2\2\u020e\u020c\3\2\2\2\u020f\u0210\bD"+
		"\5\2\u0210\u0088\3\2\2\2\u0211\u0213\5\u008dG\2\u0212\u0211\3\2\2\2\u0213"+
		"\u0214\3\2\2\2\u0214\u0212\3\2\2\2\u0214\u0215\3\2\2\2\u0215\u0217\3\2"+
		"\2\2\u0216\u0218\7\2\2\3\u0217\u0216\3\2\2\2\u0217\u0218\3\2\2\2\u0218"+
		"\u0219\3\2\2\2\u0219\u021a\bE\6\2\u021a\u008a\3\2\2\2\u021b\u021f\7%\2"+
		"\2\u021c\u021e\13\2\2\2\u021d\u021c\3\2\2\2\u021e\u0221\3\2\2\2\u021f"+
		"\u0220\3\2\2\2\u021f\u021d\3\2\2\2\u0220\u0222\3\2\2\2\u0221\u021f\3\2"+
		"\2\2\u0222\u0223\5\u008fH\2\u0223\u008c\3\2\2\2\u0224\u0225\t\6\2\2\u0225"+
		"\u008e\3\2\2\2\u0226\u0228\7\17\2\2\u0227\u0226\3\2\2\2\u0227\u0228\3"+
		"\2\2\2\u0228\u0229\3\2\2\2\u0229\u022c\7\f\2\2\u022a\u022c\7\f\2\2\u022b"+
		"\u0227\3\2\2\2\u022b\u022a\3\2\2\2\u022c\u0090\3\2\2\2\u022d\u022e\7k"+
		"\2\2\u022e\u022f\7p\2\2\u022f\u0230\7f\2\2\u0230\u0231\7g\2\2\u0231\u0232"+
		"\7p\2\2\u0232\u0233\7v\2\2\u0233\u0092\3\2\2\2\u0234\u0235\7f\2\2\u0235"+
		"\u0236\7g\2\2\u0236\u0237\7f\2\2\u0237\u0238\7g\2\2\u0238\u0239\7p\2\2"+
		"\u0239\u023a\7v\2\2\u023a\u0094\3\2\2\2\u023b\u023c\13\2\2\2\u023c\u0096"+
		"\3\2\2\2\35\2\u0133\u0141\u01a6\u01a9\u01ae\u01b4\u01b8\u01bd\u01c3\u01c9"+
		"\u01d2\u01da\u01dc\u01e3\u01e8\u01ef\u01f4\u01fb\u01fd\u0207\u020c\u0214"+
		"\u0217\u021f\u0227\u022b\7\3\32\2\3\33\3\3&\4\3D\5\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}