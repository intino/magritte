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
		METAIDENTIFIER=1, CASE=2, MULTIPLE=3, REQUIRED=4, ROOT=5, TERMINAL=6, 
		INTENTION=7, HAS_NAME=8, VAR=9, PROPERTY=10, WORD=11, RESOURCE=12, ABSTRACT=13, 
		BASE=14, IMPORT=15, PACKAGE=16, NAMESPACE=17, LEFT_SQUARE=18, RIGHT_SQUARE=19, 
		LEFT_PARENTHESIS=20, RIGHT_PARENTHESIS=21, OPEN_BRACKET=22, CLOSE_BRACKET=23, 
		OPEN_AN=24, CLOSE_AN=25, COLON=26, COMMA=27, DOT=28, ASSIGN=29, APHOSTROPHE=30, 
		SEMICOLON=31, POSITIVE=32, DASHES=33, DASH=34, ALIAS_TYPE=35, INT_TYPE=36, 
		NATURAL_TYPE=37, DOUBLE_TYPE=38, STRING_TYPE=39, BOOLEAN_TYPE=40, BOOLEAN_VALUE=41, 
		POSITIVE_VALUE=42, NEGATIVE_VALUE=43, DOUBLE_VALUE=44, STRING_VALUE=45, 
		STRING_MULTILINE_VALUE_KEY=46, IDENTIFIER=47, DIGIT=48, LETTER=49, NEWLINE=50, 
		SPACES=51, DOC=52, SP=53, NL=54, NEW_LINE_INDENT=55, DEDENT=56, UNKNOWN_TOKEN=57;
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
		"'2'", "'3'", "'4'", "'5'", "'6'", "'7'", "'8'", "'9'"
	};
	public static final String[] ruleNames = {
		"METAIDENTIFIER", "CASE", "MULTIPLE", "REQUIRED", "ROOT", "TERMINAL", 
		"INTENTION", "HAS_NAME", "VAR", "PROPERTY", "WORD", "RESOURCE", "ABSTRACT", 
		"BASE", "IMPORT", "PACKAGE", "NAMESPACE", "LEFT_SQUARE", "RIGHT_SQUARE", 
		"LEFT_PARENTHESIS", "RIGHT_PARENTHESIS", "OPEN_BRACKET", "CLOSE_BRACKET", 
		"OPEN_AN", "CLOSE_AN", "COLON", "COMMA", "DOT", "ASSIGN", "APHOSTROPHE", 
		"SEMICOLON", "POSITIVE", "DASHES", "DASH", "ALIAS_TYPE", "INT_TYPE", "NATURAL_TYPE", 
		"DOUBLE_TYPE", "STRING_TYPE", "BOOLEAN_TYPE", "BOOLEAN_VALUE", "POSITIVE_VALUE", 
		"NEGATIVE_VALUE", "DOUBLE_VALUE", "STRING_VALUE", "STRING_MULTILINE_VALUE_KEY", 
		"IDENTIFIER", "DIGIT", "LETTER", "NEWLINE", "SPACES", "DOC", "SP", "NL", 
		"NEW_LINE_INDENT", "DEDENT", "UNKNOWN_TOKEN"
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
		case 21: OPEN_BRACKET_action((RuleContext)_localctx, actionIndex); break;
		case 22: CLOSE_BRACKET_action((RuleContext)_localctx, actionIndex); break;
		case 30: SEMICOLON_action((RuleContext)_localctx, actionIndex); break;
		case 49: NEWLINE_action((RuleContext)_localctx, actionIndex); break;
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2;\u01cf\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17"+
		"\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23"+
		"\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\27\3\30\3\30\3\30\3\31\3\31"+
		"\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3 \6 \u0115"+
		"\n \r \16 \u0116\3 \3 \3!\3!\3\"\3\"\6\"\u011f\n\"\r\"\16\"\u0120\3#\3"+
		"#\3$\3$\3$\3$\3$\3$\3%\3%\3%\3%\3%\3%\3%\3%\3&\3&\3&\3&\3&\3&\3&\3&\3"+
		"\'\3\'\3\'\3\'\3\'\3\'\3\'\3(\3(\3(\3(\3(\3(\3(\3)\3)\3)\3)\3)\3)\3)\3"+
		")\3*\3*\3*\3*\3*\3*\3*\3*\3*\5*\u015a\n*\3+\5+\u015d\n+\3+\6+\u0160\n"+
		"+\r+\16+\u0161\3,\3,\6,\u0166\n,\r,\16,\u0167\3-\3-\5-\u016c\n-\3-\6-"+
		"\u016f\n-\r-\16-\u0170\3-\3-\6-\u0175\n-\r-\16-\u0176\3.\3.\7.\u017b\n"+
		".\f.\16.\u017e\13.\3.\3.\3/\3/\7/\u0184\n/\f/\16/\u0187\13/\3/\3/\3\60"+
		"\3\60\3\60\7\60\u018e\n\60\f\60\16\60\u0191\13\60\3\61\3\61\3\62\3\62"+
		"\3\63\6\63\u0198\n\63\r\63\16\63\u0199\3\63\7\63\u019d\n\63\f\63\16\63"+
		"\u01a0\13\63\3\63\3\63\3\64\6\64\u01a5\n\64\r\64\16\64\u01a6\3\64\5\64"+
		"\u01aa\n\64\3\64\3\64\3\65\3\65\7\65\u01b0\n\65\f\65\16\65\u01b3\13\65"+
		"\3\65\3\65\3\66\3\66\3\67\5\67\u01ba\n\67\3\67\3\67\5\67\u01be\n\67\3"+
		"8\38\38\38\38\38\38\39\39\39\39\39\39\39\3:\3:\3\u01b1\2;\3\3\5\4\7\5"+
		"\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23"+
		"%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G"+
		"%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a\62c\63e\64g\65i\66k\67m8o9q:s;\3\2\7\3"+
		"\2))\3\2//\3\2\62;\4\2C\\c|\4\2\13\13\"\"\u01e3\2\3\3\2\2\2\2\5\3\2\2"+
		"\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21"+
		"\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2"+
		"\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3"+
		"\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3"+
		"\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3"+
		"\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2"+
		"\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2"+
		"Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3"+
		"\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q\3\2\2"+
		"\2\2s\3\2\2\2\3u\3\2\2\2\5}\3\2\2\2\7\u0082\3\2\2\2\t\u008b\3\2\2\2\13"+
		"\u0094\3\2\2\2\r\u0099\3\2\2\2\17\u00a2\3\2\2\2\21\u00ac\3\2\2\2\23\u00b5"+
		"\3\2\2\2\25\u00b9\3\2\2\2\27\u00c2\3\2\2\2\31\u00c7\3\2\2\2\33\u00d0\3"+
		"\2\2\2\35\u00d9\3\2\2\2\37\u00de\3\2\2\2!\u00e5\3\2\2\2#\u00ed\3\2\2\2"+
		"%\u00f7\3\2\2\2\'\u00f9\3\2\2\2)\u00fb\3\2\2\2+\u00fd\3\2\2\2-\u00ff\3"+
		"\2\2\2/\u0102\3\2\2\2\61\u0105\3\2\2\2\63\u0107\3\2\2\2\65\u0109\3\2\2"+
		"\2\67\u010b\3\2\2\29\u010d\3\2\2\2;\u010f\3\2\2\2=\u0111\3\2\2\2?\u0114"+
		"\3\2\2\2A\u011a\3\2\2\2C\u011c\3\2\2\2E\u0122\3\2\2\2G\u0124\3\2\2\2I"+
		"\u012a\3\2\2\2K\u0132\3\2\2\2M\u013a\3\2\2\2O\u0141\3\2\2\2Q\u0148\3\2"+
		"\2\2S\u0159\3\2\2\2U\u015c\3\2\2\2W\u0163\3\2\2\2Y\u016b\3\2\2\2[\u0178"+
		"\3\2\2\2]\u0181\3\2\2\2_\u018a\3\2\2\2a\u0192\3\2\2\2c\u0194\3\2\2\2e"+
		"\u0197\3\2\2\2g\u01a4\3\2\2\2i\u01ad\3\2\2\2k\u01b6\3\2\2\2m\u01bd\3\2"+
		"\2\2o\u01bf\3\2\2\2q\u01c6\3\2\2\2s\u01cd\3\2\2\2uv\7E\2\2vw\7q\2\2wx"+
		"\7p\2\2xy\7e\2\2yz\7g\2\2z{\7r\2\2{|\7v\2\2|\4\3\2\2\2}~\7e\2\2~\177\7"+
		"c\2\2\177\u0080\7u\2\2\u0080\u0081\7g\2\2\u0081\6\3\2\2\2\u0082\u0083"+
		"\7o\2\2\u0083\u0084\7w\2\2\u0084\u0085\7n\2\2\u0085\u0086\7v\2\2\u0086"+
		"\u0087\7k\2\2\u0087\u0088\7r\2\2\u0088\u0089\7n\2\2\u0089\u008a\7g\2\2"+
		"\u008a\b\3\2\2\2\u008b\u008c\7t\2\2\u008c\u008d\7g\2\2\u008d\u008e\7s"+
		"\2\2\u008e\u008f\7w\2\2\u008f\u0090\7k\2\2\u0090\u0091\7t\2\2\u0091\u0092"+
		"\7g\2\2\u0092\u0093\7f\2\2\u0093\n\3\2\2\2\u0094\u0095\7t\2\2\u0095\u0096"+
		"\7q\2\2\u0096\u0097\7q\2\2\u0097\u0098\7v\2\2\u0098\f\3\2\2\2\u0099\u009a"+
		"\7v\2\2\u009a\u009b\7g\2\2\u009b\u009c\7t\2\2\u009c\u009d\7o\2\2\u009d"+
		"\u009e\7k\2\2\u009e\u009f\7p\2\2\u009f\u00a0\7c\2\2\u00a0\u00a1\7n\2\2"+
		"\u00a1\16\3\2\2\2\u00a2\u00a3\7k\2\2\u00a3\u00a4\7p\2\2\u00a4\u00a5\7"+
		"v\2\2\u00a5\u00a6\7g\2\2\u00a6\u00a7\7p\2\2\u00a7\u00a8\7v\2\2\u00a8\u00a9"+
		"\7k\2\2\u00a9\u00aa\7q\2\2\u00aa\u00ab\7p\2\2\u00ab\20\3\2\2\2\u00ac\u00ad"+
		"\7j\2\2\u00ad\u00ae\7c\2\2\u00ae\u00af\7u\2\2\u00af\u00b0\7/\2\2\u00b0"+
		"\u00b1\7p\2\2\u00b1\u00b2\7c\2\2\u00b2\u00b3\7o\2\2\u00b3\u00b4\7g\2\2"+
		"\u00b4\22\3\2\2\2\u00b5\u00b6\7x\2\2\u00b6\u00b7\7c\2\2\u00b7\u00b8\7"+
		"t\2\2\u00b8\24\3\2\2\2\u00b9\u00ba\7r\2\2\u00ba\u00bb\7t\2\2\u00bb\u00bc"+
		"\7q\2\2\u00bc\u00bd\7r\2\2\u00bd\u00be\7g\2\2\u00be\u00bf\7t\2\2\u00bf"+
		"\u00c0\7v\2\2\u00c0\u00c1\7{\2\2\u00c1\26\3\2\2\2\u00c2\u00c3\7Y\2\2\u00c3"+
		"\u00c4\7q\2\2\u00c4\u00c5\7t\2\2\u00c5\u00c6\7f\2\2\u00c6\30\3\2\2\2\u00c7"+
		"\u00c8\7T\2\2\u00c8\u00c9\7g\2\2\u00c9\u00ca\7u\2\2\u00ca\u00cb\7q\2\2"+
		"\u00cb\u00cc\7w\2\2\u00cc\u00cd\7t\2\2\u00cd\u00ce\7e\2\2\u00ce\u00cf"+
		"\7g\2\2\u00cf\32\3\2\2\2\u00d0\u00d1\7c\2\2\u00d1\u00d2\7d\2\2\u00d2\u00d3"+
		"\7u\2\2\u00d3\u00d4\7v\2\2\u00d4\u00d5\7t\2\2\u00d5\u00d6\7c\2\2\u00d6"+
		"\u00d7\7e\2\2\u00d7\u00d8\7v\2\2\u00d8\34\3\2\2\2\u00d9\u00da\7d\2\2\u00da"+
		"\u00db\7c\2\2\u00db\u00dc\7u\2\2\u00dc\u00dd\7g\2\2\u00dd\36\3\2\2\2\u00de"+
		"\u00df\7k\2\2\u00df\u00e0\7o\2\2\u00e0\u00e1\7r\2\2\u00e1\u00e2\7q\2\2"+
		"\u00e2\u00e3\7t\2\2\u00e3\u00e4\7v\2\2\u00e4 \3\2\2\2\u00e5\u00e6\7r\2"+
		"\2\u00e6\u00e7\7c\2\2\u00e7\u00e8\7e\2\2\u00e8\u00e9\7m\2\2\u00e9\u00ea"+
		"\7c\2\2\u00ea\u00eb\7i\2\2\u00eb\u00ec\7g\2\2\u00ec\"\3\2\2\2\u00ed\u00ee"+
		"\7p\2\2\u00ee\u00ef\7c\2\2\u00ef\u00f0\7o\2\2\u00f0\u00f1\7g\2\2\u00f1"+
		"\u00f2\7u\2\2\u00f2\u00f3\7r\2\2\u00f3\u00f4\7c\2\2\u00f4\u00f5\7e\2\2"+
		"\u00f5\u00f6\7g\2\2\u00f6$\3\2\2\2\u00f7\u00f8\7]\2\2\u00f8&\3\2\2\2\u00f9"+
		"\u00fa\7_\2\2\u00fa(\3\2\2\2\u00fb\u00fc\7*\2\2\u00fc*\3\2\2\2\u00fd\u00fe"+
		"\7+\2\2\u00fe,\3\2\2\2\u00ff\u0100\7}\2\2\u0100\u0101\b\27\2\2\u0101."+
		"\3\2\2\2\u0102\u0103\7\177\2\2\u0103\u0104\b\30\3\2\u0104\60\3\2\2\2\u0105"+
		"\u0106\7>\2\2\u0106\62\3\2\2\2\u0107\u0108\7@\2\2\u0108\64\3\2\2\2\u0109"+
		"\u010a\7<\2\2\u010a\66\3\2\2\2\u010b\u010c\7.\2\2\u010c8\3\2\2\2\u010d"+
		"\u010e\7\60\2\2\u010e:\3\2\2\2\u010f\u0110\7?\2\2\u0110<\3\2\2\2\u0111"+
		"\u0112\7)\2\2\u0112>\3\2\2\2\u0113\u0115\7=\2\2\u0114\u0113\3\2\2\2\u0115"+
		"\u0116\3\2\2\2\u0116\u0114\3\2\2\2\u0116\u0117\3\2\2\2\u0117\u0118\3\2"+
		"\2\2\u0118\u0119\b \4\2\u0119@\3\2\2\2\u011a\u011b\7-\2\2\u011bB\3\2\2"+
		"\2\u011c\u011e\5E#\2\u011d\u011f\5E#\2\u011e\u011d\3\2\2\2\u011f\u0120"+
		"\3\2\2\2\u0120\u011e\3\2\2\2\u0120\u0121\3\2\2\2\u0121D\3\2\2\2\u0122"+
		"\u0123\7/\2\2\u0123F\3\2\2\2\u0124\u0125\7C\2\2\u0125\u0126\7n\2\2\u0126"+
		"\u0127\7k\2\2\u0127\u0128\7c\2\2\u0128\u0129\7u\2\2\u0129H\3\2\2\2\u012a"+
		"\u012b\7K\2\2\u012b\u012c\7p\2\2\u012c\u012d\7v\2\2\u012d\u012e\7g\2\2"+
		"\u012e\u012f\7i\2\2\u012f\u0130\7g\2\2\u0130\u0131\7t\2\2\u0131J\3\2\2"+
		"\2\u0132\u0133\7P\2\2\u0133\u0134\7c\2\2\u0134\u0135\7v\2\2\u0135\u0136"+
		"\7w\2\2\u0136\u0137\7t\2\2\u0137\u0138\7c\2\2\u0138\u0139\7n\2\2\u0139"+
		"L\3\2\2\2\u013a\u013b\7F\2\2\u013b\u013c\7q\2\2\u013c\u013d\7w\2\2\u013d"+
		"\u013e\7d\2\2\u013e\u013f\7n\2\2\u013f\u0140\7g\2\2\u0140N\3\2\2\2\u0141"+
		"\u0142\7U\2\2\u0142\u0143\7v\2\2\u0143\u0144\7t\2\2\u0144\u0145\7k\2\2"+
		"\u0145\u0146\7p\2\2\u0146\u0147\7i\2\2\u0147P\3\2\2\2\u0148\u0149\7D\2"+
		"\2\u0149\u014a\7q\2\2\u014a\u014b\7q\2\2\u014b\u014c\7n\2\2\u014c\u014d"+
		"\7g\2\2\u014d\u014e\7c\2\2\u014e\u014f\7p\2\2\u014fR\3\2\2\2\u0150\u0151"+
		"\7v\2\2\u0151\u0152\7t\2\2\u0152\u0153\7w\2\2\u0153\u015a\7g\2\2\u0154"+
		"\u0155\7h\2\2\u0155\u0156\7c\2\2\u0156\u0157\7n\2\2\u0157\u0158\7u\2\2"+
		"\u0158\u015a\7g\2\2\u0159\u0150\3\2\2\2\u0159\u0154\3\2\2\2\u015aT\3\2"+
		"\2\2\u015b\u015d\5A!\2\u015c\u015b\3\2\2\2\u015c\u015d\3\2\2\2\u015d\u015f"+
		"\3\2\2\2\u015e\u0160\5a\61\2\u015f\u015e\3\2\2\2\u0160\u0161\3\2\2\2\u0161"+
		"\u015f\3\2\2\2\u0161\u0162\3\2\2\2\u0162V\3\2\2\2\u0163\u0165\5E#\2\u0164"+
		"\u0166\5a\61\2\u0165\u0164\3\2\2\2\u0166\u0167\3\2\2\2\u0167\u0165\3\2"+
		"\2\2\u0167\u0168\3\2\2\2\u0168X\3\2\2\2\u0169\u016c\5A!\2\u016a\u016c"+
		"\5E#\2\u016b\u0169\3\2\2\2\u016b\u016a\3\2\2\2\u016b\u016c\3\2\2\2\u016c"+
		"\u016e\3\2\2\2\u016d\u016f\5a\61\2\u016e\u016d\3\2\2\2\u016f\u0170\3\2"+
		"\2\2\u0170\u016e\3\2\2\2\u0170\u0171\3\2\2\2\u0171\u0172\3\2\2\2\u0172"+
		"\u0174\59\35\2\u0173\u0175\5a\61\2\u0174\u0173\3\2\2\2\u0175\u0176\3\2"+
		"\2\2\u0176\u0174\3\2\2\2\u0176\u0177\3\2\2\2\u0177Z\3\2\2\2\u0178\u017c"+
		"\5=\37\2\u0179\u017b\n\2\2\2\u017a\u0179\3\2\2\2\u017b\u017e\3\2\2\2\u017c"+
		"\u017a\3\2\2\2\u017c\u017d\3\2\2\2\u017d\u017f\3\2\2\2\u017e\u017c\3\2"+
		"\2\2\u017f\u0180\5=\37\2\u0180\\\3\2\2\2\u0181\u0185\5C\"\2\u0182\u0184"+
		"\n\3\2\2\u0183\u0182\3\2\2\2\u0184\u0187\3\2\2\2\u0185\u0183\3\2\2\2\u0185"+
		"\u0186\3\2\2\2\u0186\u0188\3\2\2\2\u0187\u0185\3\2\2\2\u0188\u0189\5C"+
		"\"\2\u0189^\3\2\2\2\u018a\u018f\5c\62\2\u018b\u018e\5a\61\2\u018c\u018e"+
		"\5c\62\2\u018d\u018b\3\2\2\2\u018d\u018c\3\2\2\2\u018e\u0191\3\2\2\2\u018f"+
		"\u018d\3\2\2\2\u018f\u0190\3\2\2\2\u0190`\3\2\2\2\u0191\u018f\3\2\2\2"+
		"\u0192\u0193\t\4\2\2\u0193b\3\2\2\2\u0194\u0195\t\5\2\2\u0195d\3\2\2\2"+
		"\u0196\u0198\5m\67\2\u0197\u0196\3\2\2\2\u0198\u0199\3\2\2\2\u0199\u0197"+
		"\3\2\2\2\u0199\u019a\3\2\2\2\u019a\u019e\3\2\2\2\u019b\u019d\5k\66\2\u019c"+
		"\u019b\3\2\2\2\u019d\u01a0\3\2\2\2\u019e\u019c\3\2\2\2\u019e\u019f\3\2"+
		"\2\2\u019f\u01a1\3\2\2\2\u01a0\u019e\3\2\2\2\u01a1\u01a2\b\63\5\2\u01a2"+
		"f\3\2\2\2\u01a3\u01a5\5k\66\2\u01a4\u01a3\3\2\2\2\u01a5\u01a6\3\2\2\2"+
		"\u01a6\u01a4\3\2\2\2\u01a6\u01a7\3\2\2\2\u01a7\u01a9\3\2\2\2\u01a8\u01aa"+
		"\7\2\2\3\u01a9\u01a8\3\2\2\2\u01a9\u01aa\3\2\2\2\u01aa\u01ab\3\2\2\2\u01ab"+
		"\u01ac\b\64\6\2\u01ach\3\2\2\2\u01ad\u01b1\7%\2\2\u01ae\u01b0\13\2\2\2"+
		"\u01af\u01ae\3\2\2\2\u01b0\u01b3\3\2\2\2\u01b1\u01b2\3\2\2\2\u01b1\u01af"+
		"\3\2\2\2\u01b2\u01b4\3\2\2\2\u01b3\u01b1\3\2\2\2\u01b4\u01b5\5m\67\2\u01b5"+
		"j\3\2\2\2\u01b6\u01b7\t\6\2\2\u01b7l\3\2\2\2\u01b8\u01ba\7\17\2\2\u01b9"+
		"\u01b8\3\2\2\2\u01b9\u01ba\3\2\2\2\u01ba\u01bb\3\2\2\2\u01bb\u01be\7\f"+
		"\2\2\u01bc\u01be\7\f\2\2\u01bd\u01b9\3\2\2\2\u01bd\u01bc\3\2\2\2\u01be"+
		"n\3\2\2\2\u01bf\u01c0\7k\2\2\u01c0\u01c1\7p\2\2\u01c1\u01c2\7f\2\2\u01c2"+
		"\u01c3\7g\2\2\u01c3\u01c4\7p\2\2\u01c4\u01c5\7v\2\2\u01c5p\3\2\2\2\u01c6"+
		"\u01c7\7f\2\2\u01c7\u01c8\7g\2\2\u01c8\u01c9\7f\2\2\u01c9\u01ca\7g\2\2"+
		"\u01ca\u01cb\7p\2\2\u01cb\u01cc\7v\2\2\u01ccr\3\2\2\2\u01cd\u01ce\13\2"+
		"\2\2\u01cet\3\2\2\2\27\2\u0116\u0120\u0159\u015c\u0161\u0167\u016b\u0170"+
		"\u0176\u017c\u0185\u018d\u018f\u0199\u019e\u01a6\u01a9\u01b1\u01b9\u01bd"+
		"\7\3\27\2\3\30\3\3 \4\3\63\5\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}