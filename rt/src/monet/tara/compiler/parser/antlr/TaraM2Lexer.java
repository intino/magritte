// Generated from /home/bycor/Projects/Tara/src/AntlrM2/src/TaraM2Lexer.g4 by ANTLR 4.x

    package monet.tara.compiler.parser.antlr;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TaraM2Lexer extends Lexer {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		CONCEPT=1, FINAL=2, ABSTRACT=3, MULTIPLE=4, OPTIONAL=5, HAS_CODE=6, EXTENSIBLE=7, 
		VAR=8, ROOT=9, SINGLETON=10, NEW=11, POLYMORPHIC=12, MORPH=13, WORD=14, 
		GENERIC=15, LIST=16, LEFT_SQUARE=17, RIGHT_SQUARE=18, OPEN_BRACKET=19, 
		CLOSE_BRACKET=20, OPEN_AN=21, CLOSE_AN=22, COLON=23, COMMA=24, DOT=25, 
		ASSIGN=26, DOUBLE_COMMAS=27, SEMICOLON=28, POSITIVE=29, NEGATIVE=30, UID_TYPE=31, 
		INT_TYPE=32, NATURAL_TYPE=33, DOUBLE_TYPE=34, STRING_TYPE=35, BOOLEAN_TYPE=36, 
		BOOLEAN_VALUE=37, POSITIVE_VALUE=38, NEGATIVE_VALUE=39, DOUBLE_VALUE=40, 
		STRING_VALUE=41, IDENTIFIER=42, DIGIT=43, LETTER=44, NEWLINE=45, SPACES=46, 
		DOC=47, SP=48, NL=49, NEW_LINE_INDENT=50, DEDENT=51, UNKNOWN_TOKEN=52;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'Concept'", "'final'", "'abstract'", "'multiple'", "'optional'", "'has-code'", 
		"'extensible'", "'var'", "'root'", "'singleton'", "'new'", "'polymorphic'", 
		"'morph'", "'Word'", "'generic'", "LIST", "'['", "']'", "'{'", "'}'", 
		"'<'", "'>'", "':'", "','", "'.'", "'='", "'\"'", "SEMICOLON", "'+'", 
		"'-'", "'Uid'", "'Integer'", "'Natural'", "'Double'", "'String'", "'Boolean'", 
		"BOOLEAN_VALUE", "POSITIVE_VALUE", "NEGATIVE_VALUE", "DOUBLE_VALUE", "STRING_VALUE", 
		"IDENTIFIER", "DIGIT", "LETTER", "NEWLINE", "SPACES", "DOC", "SP", "NL", 
		"'indent'", "'dedent'", "UNKNOWN_TOKEN"
	};
	public static final String[] ruleNames = {
		"CONCEPT", "FINAL", "ABSTRACT", "MULTIPLE", "OPTIONAL", "HAS_CODE", "EXTENSIBLE", 
		"VAR", "ROOT", "SINGLETON", "NEW", "POLYMORPHIC", "MORPH", "WORD", "GENERIC", 
		"LIST", "LEFT_SQUARE", "RIGHT_SQUARE", "OPEN_BRACKET", "CLOSE_BRACKET", 
		"OPEN_AN", "CLOSE_AN", "COLON", "COMMA", "DOT", "ASSIGN", "DOUBLE_COMMAS", 
		"SEMICOLON", "POSITIVE", "NEGATIVE", "UID_TYPE", "INT_TYPE", "NATURAL_TYPE", 
		"DOUBLE_TYPE", "STRING_TYPE", "BOOLEAN_TYPE", "BOOLEAN_VALUE", "POSITIVE_VALUE", 
		"NEGATIVE_VALUE", "DOUBLE_VALUE", "STRING_VALUE", "IDENTIFIER", "DIGIT", 
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


	public TaraM2Lexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "TaraM2Lexer.g4"; }

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
		case 18: OPEN_BRACKET_action((RuleContext)_localctx, actionIndex); break;

		case 19: CLOSE_BRACKET_action((RuleContext)_localctx, actionIndex); break;

		case 27: SEMICOLON_action((RuleContext)_localctx, actionIndex); break;

		case 44: NEWLINE_action((RuleContext)_localctx, actionIndex); break;
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\66\u01a4\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64"+
		"\t\64\4\65\t\65\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\n"+
		"\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f"+
		"\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\21\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\24\3\25"+
		"\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33"+
		"\3\34\3\34\3\35\6\35\u00fb\n\35\r\35\16\35\u00fc\3\35\3\35\3\36\3\36\3"+
		"\37\3\37\3 \3 \3 \3 \3!\3!\3!\3!\3!\3!\3!\3!\3\"\3\"\3\"\3\"\3\"\3\"\3"+
		"\"\3\"\3#\3#\3#\3#\3#\3#\3#\3$\3$\3$\3$\3$\3$\3$\3%\3%\3%\3%\3%\3%\3%"+
		"\3%\3&\3&\3&\3&\3&\3&\3&\3&\3&\5&\u0138\n&\3\'\5\'\u013b\n\'\3\'\6\'\u013e"+
		"\n\'\r\'\16\'\u013f\3(\3(\6(\u0144\n(\r(\16(\u0145\3)\3)\5)\u014a\n)\3"+
		")\6)\u014d\n)\r)\16)\u014e\3)\3)\6)\u0153\n)\r)\16)\u0154\3*\3*\7*\u0159"+
		"\n*\f*\16*\u015c\13*\3*\3*\3+\3+\3+\7+\u0163\n+\f+\16+\u0166\13+\3,\3"+
		",\3-\3-\3.\6.\u016d\n.\r.\16.\u016e\3.\7.\u0172\n.\f.\16.\u0175\13.\3"+
		".\3.\3/\6/\u017a\n/\r/\16/\u017b\3/\5/\u017f\n/\3/\3/\3\60\3\60\7\60\u0185"+
		"\n\60\f\60\16\60\u0188\13\60\3\60\3\60\3\61\3\61\3\62\5\62\u018f\n\62"+
		"\3\62\3\62\5\62\u0193\n\62\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\64\3\64"+
		"\3\64\3\64\3\64\3\64\3\64\3\65\3\65\3\u0186\2\66\3\3\5\4\7\5\t\6\13\7"+
		"\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25"+
		")\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O"+
		")Q*S+U,W-Y.[/]\60_\61a\62c\63e\64g\65i\66\3\2\6\3\2$$\3\2\62;\4\2C\\c"+
		"|\4\2\13\13\"\"\u01b6\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2"+
		"\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25"+
		"\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2"+
		"\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2"+
		"\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3"+
		"\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2"+
		"\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2"+
		"Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3"+
		"\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2"+
		"\2\3k\3\2\2\2\5s\3\2\2\2\7y\3\2\2\2\t\u0082\3\2\2\2\13\u008b\3\2\2\2\r"+
		"\u0094\3\2\2\2\17\u009d\3\2\2\2\21\u00a8\3\2\2\2\23\u00ac\3\2\2\2\25\u00b1"+
		"\3\2\2\2\27\u00bb\3\2\2\2\31\u00bf\3\2\2\2\33\u00cb\3\2\2\2\35\u00d1\3"+
		"\2\2\2\37\u00d6\3\2\2\2!\u00de\3\2\2\2#\u00e1\3\2\2\2%\u00e3\3\2\2\2\'"+
		"\u00e5\3\2\2\2)\u00e8\3\2\2\2+\u00eb\3\2\2\2-\u00ed\3\2\2\2/\u00ef\3\2"+
		"\2\2\61\u00f1\3\2\2\2\63\u00f3\3\2\2\2\65\u00f5\3\2\2\2\67\u00f7\3\2\2"+
		"\29\u00fa\3\2\2\2;\u0100\3\2\2\2=\u0102\3\2\2\2?\u0104\3\2\2\2A\u0108"+
		"\3\2\2\2C\u0110\3\2\2\2E\u0118\3\2\2\2G\u011f\3\2\2\2I\u0126\3\2\2\2K"+
		"\u0137\3\2\2\2M\u013a\3\2\2\2O\u0141\3\2\2\2Q\u0149\3\2\2\2S\u0156\3\2"+
		"\2\2U\u015f\3\2\2\2W\u0167\3\2\2\2Y\u0169\3\2\2\2[\u016c\3\2\2\2]\u0179"+
		"\3\2\2\2_\u0182\3\2\2\2a\u018b\3\2\2\2c\u0192\3\2\2\2e\u0194\3\2\2\2g"+
		"\u019b\3\2\2\2i\u01a2\3\2\2\2kl\7E\2\2lm\7q\2\2mn\7p\2\2no\7e\2\2op\7"+
		"g\2\2pq\7r\2\2qr\7v\2\2r\4\3\2\2\2st\7h\2\2tu\7k\2\2uv\7p\2\2vw\7c\2\2"+
		"wx\7n\2\2x\6\3\2\2\2yz\7c\2\2z{\7d\2\2{|\7u\2\2|}\7v\2\2}~\7t\2\2~\177"+
		"\7c\2\2\177\u0080\7e\2\2\u0080\u0081\7v\2\2\u0081\b\3\2\2\2\u0082\u0083"+
		"\7o\2\2\u0083\u0084\7w\2\2\u0084\u0085\7n\2\2\u0085\u0086\7v\2\2\u0086"+
		"\u0087\7k\2\2\u0087\u0088\7r\2\2\u0088\u0089\7n\2\2\u0089\u008a\7g\2\2"+
		"\u008a\n\3\2\2\2\u008b\u008c\7q\2\2\u008c\u008d\7r\2\2\u008d\u008e\7v"+
		"\2\2\u008e\u008f\7k\2\2\u008f\u0090\7q\2\2\u0090\u0091\7p\2\2\u0091\u0092"+
		"\7c\2\2\u0092\u0093\7n\2\2\u0093\f\3\2\2\2\u0094\u0095\7j\2\2\u0095\u0096"+
		"\7c\2\2\u0096\u0097\7u\2\2\u0097\u0098\7/\2\2\u0098\u0099\7e\2\2\u0099"+
		"\u009a\7q\2\2\u009a\u009b\7f\2\2\u009b\u009c\7g\2\2\u009c\16\3\2\2\2\u009d"+
		"\u009e\7g\2\2\u009e\u009f\7z\2\2\u009f\u00a0\7v\2\2\u00a0\u00a1\7g\2\2"+
		"\u00a1\u00a2\7p\2\2\u00a2\u00a3\7u\2\2\u00a3\u00a4\7k\2\2\u00a4\u00a5"+
		"\7d\2\2\u00a5\u00a6\7n\2\2\u00a6\u00a7\7g\2\2\u00a7\20\3\2\2\2\u00a8\u00a9"+
		"\7x\2\2\u00a9\u00aa\7c\2\2\u00aa\u00ab\7t\2\2\u00ab\22\3\2\2\2\u00ac\u00ad"+
		"\7t\2\2\u00ad\u00ae\7q\2\2\u00ae\u00af\7q\2\2\u00af\u00b0\7v\2\2\u00b0"+
		"\24\3\2\2\2\u00b1\u00b2\7u\2\2\u00b2\u00b3\7k\2\2\u00b3\u00b4\7p\2\2\u00b4"+
		"\u00b5\7i\2\2\u00b5\u00b6\7n\2\2\u00b6\u00b7\7g\2\2\u00b7\u00b8\7v\2\2"+
		"\u00b8\u00b9\7q\2\2\u00b9\u00ba\7p\2\2\u00ba\26\3\2\2\2\u00bb\u00bc\7"+
		"p\2\2\u00bc\u00bd\7g\2\2\u00bd\u00be\7y\2\2\u00be\30\3\2\2\2\u00bf\u00c0"+
		"\7r\2\2\u00c0\u00c1\7q\2\2\u00c1\u00c2\7n\2\2\u00c2\u00c3\7{\2\2\u00c3"+
		"\u00c4\7o\2\2\u00c4\u00c5\7q\2\2\u00c5\u00c6\7t\2\2\u00c6\u00c7\7r\2\2"+
		"\u00c7\u00c8\7j\2\2\u00c8\u00c9\7k\2\2\u00c9\u00ca\7e\2\2\u00ca\32\3\2"+
		"\2\2\u00cb\u00cc\7o\2\2\u00cc\u00cd\7q\2\2\u00cd\u00ce\7t\2\2\u00ce\u00cf"+
		"\7r\2\2\u00cf\u00d0\7j\2\2\u00d0\34\3\2\2\2\u00d1\u00d2\7Y\2\2\u00d2\u00d3"+
		"\7q\2\2\u00d3\u00d4\7t\2\2\u00d4\u00d5\7f\2\2\u00d5\36\3\2\2\2\u00d6\u00d7"+
		"\7i\2\2\u00d7\u00d8\7g\2\2\u00d8\u00d9\7p\2\2\u00d9\u00da\7g\2\2\u00da"+
		"\u00db\7t\2\2\u00db\u00dc\7k\2\2\u00dc\u00dd\7e\2\2\u00dd \3\2\2\2\u00de"+
		"\u00df\5#\22\2\u00df\u00e0\5%\23\2\u00e0\"\3\2\2\2\u00e1\u00e2\7]\2\2"+
		"\u00e2$\3\2\2\2\u00e3\u00e4\7_\2\2\u00e4&\3\2\2\2\u00e5\u00e6\7}\2\2\u00e6"+
		"\u00e7\b\24\2\2\u00e7(\3\2\2\2\u00e8\u00e9\7\177\2\2\u00e9\u00ea\b\25"+
		"\3\2\u00ea*\3\2\2\2\u00eb\u00ec\7>\2\2\u00ec,\3\2\2\2\u00ed\u00ee\7@\2"+
		"\2\u00ee.\3\2\2\2\u00ef\u00f0\7<\2\2\u00f0\60\3\2\2\2\u00f1\u00f2\7.\2"+
		"\2\u00f2\62\3\2\2\2\u00f3\u00f4\7\60\2\2\u00f4\64\3\2\2\2\u00f5\u00f6"+
		"\7?\2\2\u00f6\66\3\2\2\2\u00f7\u00f8\7$\2\2\u00f88\3\2\2\2\u00f9\u00fb"+
		"\7=\2\2\u00fa\u00f9\3\2\2\2\u00fb\u00fc\3\2\2\2\u00fc\u00fa\3\2\2\2\u00fc"+
		"\u00fd\3\2\2\2\u00fd\u00fe\3\2\2\2\u00fe\u00ff\b\35\4\2\u00ff:\3\2\2\2"+
		"\u0100\u0101\7-\2\2\u0101<\3\2\2\2\u0102\u0103\7/\2\2\u0103>\3\2\2\2\u0104"+
		"\u0105\7W\2\2\u0105\u0106\7k\2\2\u0106\u0107\7f\2\2\u0107@\3\2\2\2\u0108"+
		"\u0109\7K\2\2\u0109\u010a\7p\2\2\u010a\u010b\7v\2\2\u010b\u010c\7g\2\2"+
		"\u010c\u010d\7i\2\2\u010d\u010e\7g\2\2\u010e\u010f\7t\2\2\u010fB\3\2\2"+
		"\2\u0110\u0111\7P\2\2\u0111\u0112\7c\2\2\u0112\u0113\7v\2\2\u0113\u0114"+
		"\7w\2\2\u0114\u0115\7t\2\2\u0115\u0116\7c\2\2\u0116\u0117\7n\2\2\u0117"+
		"D\3\2\2\2\u0118\u0119\7F\2\2\u0119\u011a\7q\2\2\u011a\u011b\7w\2\2\u011b"+
		"\u011c\7d\2\2\u011c\u011d\7n\2\2\u011d\u011e\7g\2\2\u011eF\3\2\2\2\u011f"+
		"\u0120\7U\2\2\u0120\u0121\7v\2\2\u0121\u0122\7t\2\2\u0122\u0123\7k\2\2"+
		"\u0123\u0124\7p\2\2\u0124\u0125\7i\2\2\u0125H\3\2\2\2\u0126\u0127\7D\2"+
		"\2\u0127\u0128\7q\2\2\u0128\u0129\7q\2\2\u0129\u012a\7n\2\2\u012a\u012b"+
		"\7g\2\2\u012b\u012c\7c\2\2\u012c\u012d\7p\2\2\u012dJ\3\2\2\2\u012e\u012f"+
		"\7v\2\2\u012f\u0130\7t\2\2\u0130\u0131\7w\2\2\u0131\u0138\7g\2\2\u0132"+
		"\u0133\7h\2\2\u0133\u0134\7c\2\2\u0134\u0135\7n\2\2\u0135\u0136\7u\2\2"+
		"\u0136\u0138\7g\2\2\u0137\u012e\3\2\2\2\u0137\u0132\3\2\2\2\u0138L\3\2"+
		"\2\2\u0139\u013b\5;\36\2\u013a\u0139\3\2\2\2\u013a\u013b\3\2\2\2\u013b"+
		"\u013d\3\2\2\2\u013c\u013e\5W,\2\u013d\u013c\3\2\2\2\u013e\u013f\3\2\2"+
		"\2\u013f\u013d\3\2\2\2\u013f\u0140\3\2\2\2\u0140N\3\2\2\2\u0141\u0143"+
		"\5=\37\2\u0142\u0144\5W,\2\u0143\u0142\3\2\2\2\u0144\u0145\3\2\2\2\u0145"+
		"\u0143\3\2\2\2\u0145\u0146\3\2\2\2\u0146P\3\2\2\2\u0147\u014a\5;\36\2"+
		"\u0148\u014a\5=\37\2\u0149\u0147\3\2\2\2\u0149\u0148\3\2\2\2\u0149\u014a"+
		"\3\2\2\2\u014a\u014c\3\2\2\2\u014b\u014d\5W,\2\u014c\u014b\3\2\2\2\u014d"+
		"\u014e\3\2\2\2\u014e\u014c\3\2\2\2\u014e\u014f\3\2\2\2\u014f\u0150\3\2"+
		"\2\2\u0150\u0152\5\63\32\2\u0151\u0153\5W,\2\u0152\u0151\3\2\2\2\u0153"+
		"\u0154\3\2\2\2\u0154\u0152\3\2\2\2\u0154\u0155\3\2\2\2\u0155R\3\2\2\2"+
		"\u0156\u015a\5\67\34\2\u0157\u0159\n\2\2\2\u0158\u0157\3\2\2\2\u0159\u015c"+
		"\3\2\2\2\u015a\u0158\3\2\2\2\u015a\u015b\3\2\2\2\u015b\u015d\3\2\2\2\u015c"+
		"\u015a\3\2\2\2\u015d\u015e\5\67\34\2\u015eT\3\2\2\2\u015f\u0164\5Y-\2"+
		"\u0160\u0163\5W,\2\u0161\u0163\5Y-\2\u0162\u0160\3\2\2\2\u0162\u0161\3"+
		"\2\2\2\u0163\u0166\3\2\2\2\u0164\u0162\3\2\2\2\u0164\u0165\3\2\2\2\u0165"+
		"V\3\2\2\2\u0166\u0164\3\2\2\2\u0167\u0168\t\3\2\2\u0168X\3\2\2\2\u0169"+
		"\u016a\t\4\2\2\u016aZ\3\2\2\2\u016b\u016d\5c\62\2\u016c\u016b\3\2\2\2"+
		"\u016d\u016e\3\2\2\2\u016e\u016c\3\2\2\2\u016e\u016f\3\2\2\2\u016f\u0173"+
		"\3\2\2\2\u0170\u0172\5a\61\2\u0171\u0170\3\2\2\2\u0172\u0175\3\2\2\2\u0173"+
		"\u0171\3\2\2\2\u0173\u0174\3\2\2\2\u0174\u0176\3\2\2\2\u0175\u0173\3\2"+
		"\2\2\u0176\u0177\b.\5\2\u0177\\\3\2\2\2\u0178\u017a\5a\61\2\u0179\u0178"+
		"\3\2\2\2\u017a\u017b\3\2\2\2\u017b\u0179\3\2\2\2\u017b\u017c\3\2\2\2\u017c"+
		"\u017e\3\2\2\2\u017d\u017f\7\2\2\3\u017e\u017d\3\2\2\2\u017e\u017f\3\2"+
		"\2\2\u017f\u0180\3\2\2\2\u0180\u0181\b/\6\2\u0181^\3\2\2\2\u0182\u0186"+
		"\7)\2\2\u0183\u0185\13\2\2\2\u0184\u0183\3\2\2\2\u0185\u0188\3\2\2\2\u0186"+
		"\u0187\3\2\2\2\u0186\u0184\3\2\2\2\u0187\u0189\3\2\2\2\u0188\u0186\3\2"+
		"\2\2\u0189\u018a\5c\62\2\u018a`\3\2\2\2\u018b\u018c\t\5\2\2\u018cb\3\2"+
		"\2\2\u018d\u018f\7\17\2\2\u018e\u018d\3\2\2\2\u018e\u018f\3\2\2\2\u018f"+
		"\u0190\3\2\2\2\u0190\u0193\7\f\2\2\u0191\u0193\7\f\2\2\u0192\u018e\3\2"+
		"\2\2\u0192\u0191\3\2\2\2\u0193d\3\2\2\2\u0194\u0195\7k\2\2\u0195\u0196"+
		"\7p\2\2\u0196\u0197\7f\2\2\u0197\u0198\7g\2\2\u0198\u0199\7p\2\2\u0199"+
		"\u019a\7v\2\2\u019af\3\2\2\2\u019b\u019c\7f\2\2\u019c\u019d\7g\2\2\u019d"+
		"\u019e\7f\2\2\u019e\u019f\7g\2\2\u019f\u01a0\7p\2\2\u01a0\u01a1\7v\2\2"+
		"\u01a1h\3\2\2\2\u01a2\u01a3\13\2\2\2\u01a3j\3\2\2\2\25\2\u00fc\u0137\u013a"+
		"\u013f\u0145\u0149\u014e\u0154\u015a\u0162\u0164\u016e\u0173\u017b\u017e"+
		"\u0186\u018e\u0192\7\3\24\2\3\25\3\3\35\4\3.\5\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}