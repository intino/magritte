package monet.tara.compiler.parser.antlr;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TaraM2Lexer extends Lexer {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		CONCEPT=1, AS=2, FINAL=3, ABSTRACT=4, MULTIPLE=5, OPTIONAL=6, HAS_CODE=7,
		EXTENSIBLE=8, VAR=9, ROOT=10, SINGLETON=11, NEW=12, POLYMORPHIC=13, MORPH=14,
		WORD=15, LIST=16, LEFT_SQUARE=17, RIGHT_SQUARE=18, OPEN_BRACKET=19, CLOSE_BRACKET=20,
		OPEN_AN=21, CLOSE_AN=22, COMMA=23, DOT=24, ASSIGN=25, DOUBLE_COMMAS=26,
		SEMICOLON=27, POSITIVE=28, NEGATIVE=29, UID_TYPE=30, INT_TYPE=31, NATURAL_TYPE=32,
		DOUBLE_TYPE=33, STRING_TYPE=34, BOOLEAN_TYPE=35, BOOLEAN_VALUE=36, POSITIVE_VALUE=37,
		NEGATIVE_VALUE=38, DOUBLE_VALUE=39, STRING_VALUE=40, IDENTIFIER=41, DIGIT=42,
		LETTER=43, NEWLINE=44, SPACES=45, DOC=46, SP=47, NL=48, NEW_LINE_INDENT=49,
		DEDENT=50, UNKNOWN_TOKEN=51;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'Concept'", "'as'", "'final'", "'abstract'", "'multiple'", "'optional'",
		"'has-code'", "'extensible'", "'var'", "'root'", "'singleton'", "'new'",
		"'polymorphic'", "'morph'", "'Word'", "LIST", "'['", "']'", "'{'", "'}'",
		"'<'", "'>'", "','", "'.'", "'='", "'\"'", "SEMICOLON", "'+'", "'-'",
		"'Uid'", "'Integer'", "'Natural'", "'Double'", "'String'", "'Boolean'",
		"BOOLEAN_VALUE", "POSITIVE_VALUE", "NEGATIVE_VALUE", "DOUBLE_VALUE", "STRING_VALUE",
		"IDENTIFIER", "DIGIT", "LETTER", "NEWLINE", "SPACES", "DOC", "SP", "NL",
		"'indent'", "'dedent'", "UNKNOWN_TOKEN"
	};
	public static final String[] ruleNames = {
		"CONCEPT", "AS", "FINAL", "ABSTRACT", "MULTIPLE", "OPTIONAL", "HAS_CODE",
		"EXTENSIBLE", "VAR", "ROOT", "SINGLETON", "NEW", "POLYMORPHIC", "MORPH",
		"WORD", "LIST", "LEFT_SQUARE", "RIGHT_SQUARE", "OPEN_BRACKET", "CLOSE_BRACKET",
		"OPEN_AN", "CLOSE_AN", "COMMA", "DOT", "ASSIGN", "DOUBLE_COMMAS", "SEMICOLON",
		"POSITIVE", "NEGATIVE", "UID_TYPE", "INT_TYPE", "NATURAL_TYPE", "DOUBLE_TYPE",
		"STRING_TYPE", "BOOLEAN_TYPE", "BOOLEAN_VALUE", "POSITIVE_VALUE", "NEGATIVE_VALUE",
		"DOUBLE_VALUE", "STRING_VALUE", "IDENTIFIER", "DIGIT", "LETTER", "NEWLINE",
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
		case 26: SEMICOLON_action((RuleContext)_localctx, actionIndex); break;
		case 43: NEWLINE_action((RuleContext)_localctx, actionIndex); break;
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\65\u019b\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64"+
		"\t\64\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3"+
		"\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3"+
		"\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21"+
		"\3\22\3\22\3\23\3\23\3\24\3\24\3\24\3\25\3\25\3\25\3\26\3\26\3\27\3\27"+
		"\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34\6\34\u00f2\n\34\r\34\16"+
		"\34\u00f3\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3\37\3\37\3 \3 \3 \3"+
		" \3 \3 \3 \3 \3!\3!\3!\3!\3!\3!\3!\3!\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3#\3"+
		"#\3#\3#\3#\3#\3#\3$\3$\3$\3$\3$\3$\3$\3$\3%\3%\3%\3%\3%\3%\3%\3%\3%\5"+
		"%\u012f\n%\3&\5&\u0132\n&\3&\6&\u0135\n&\r&\16&\u0136\3\'\3\'\6\'\u013b"+
		"\n\'\r\'\16\'\u013c\3(\3(\5(\u0141\n(\3(\6(\u0144\n(\r(\16(\u0145\3(\3"+
		"(\6(\u014a\n(\r(\16(\u014b\3)\3)\7)\u0150\n)\f)\16)\u0153\13)\3)\3)\3"+
		"*\3*\3*\7*\u015a\n*\f*\16*\u015d\13*\3+\3+\3,\3,\3-\6-\u0164\n-\r-\16"+
		"-\u0165\3-\7-\u0169\n-\f-\16-\u016c\13-\3-\3-\3.\6.\u0171\n.\r.\16.\u0172"+
		"\3.\5.\u0176\n.\3.\3.\3/\3/\7/\u017c\n/\f/\16/\u017f\13/\3/\3/\3\60\3"+
		"\60\3\61\5\61\u0186\n\61\3\61\3\61\5\61\u018a\n\61\3\62\3\62\3\62\3\62"+
		"\3\62\3\62\3\62\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\64\3\64\3\u017d\2"+
		"\65\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35"+
		"\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36"+
		";\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a\62c\63e\64g\65\3\2\6\3"+
		"\2$$\3\2\62;\4\2C\\c|\4\2\13\13\"\"\u01ad\2\3\3\2\2\2\2\5\3\2\2\2\2\7"+
		"\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2"+
		"\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2"+
		"\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2"+
		"\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2"+
		"\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2"+
		"\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M"+
		"\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2"+
		"\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2"+
		"\2g\3\2\2\2\3i\3\2\2\2\5q\3\2\2\2\7t\3\2\2\2\tz\3\2\2\2\13\u0083\3\2\2"+
		"\2\r\u008c\3\2\2\2\17\u0095\3\2\2\2\21\u009e\3\2\2\2\23\u00a9\3\2\2\2"+
		"\25\u00ad\3\2\2\2\27\u00b2\3\2\2\2\31\u00bc\3\2\2\2\33\u00c0\3\2\2\2\35"+
		"\u00cc\3\2\2\2\37\u00d2\3\2\2\2!\u00d7\3\2\2\2#\u00da\3\2\2\2%\u00dc\3"+
		"\2\2\2\'\u00de\3\2\2\2)\u00e1\3\2\2\2+\u00e4\3\2\2\2-\u00e6\3\2\2\2/\u00e8"+
		"\3\2\2\2\61\u00ea\3\2\2\2\63\u00ec\3\2\2\2\65\u00ee\3\2\2\2\67\u00f1\3"+
		"\2\2\29\u00f7\3\2\2\2;\u00f9\3\2\2\2=\u00fb\3\2\2\2?\u00ff\3\2\2\2A\u0107"+
		"\3\2\2\2C\u010f\3\2\2\2E\u0116\3\2\2\2G\u011d\3\2\2\2I\u012e\3\2\2\2K"+
		"\u0131\3\2\2\2M\u0138\3\2\2\2O\u0140\3\2\2\2Q\u014d\3\2\2\2S\u0156\3\2"+
		"\2\2U\u015e\3\2\2\2W\u0160\3\2\2\2Y\u0163\3\2\2\2[\u0170\3\2\2\2]\u0179"+
		"\3\2\2\2_\u0182\3\2\2\2a\u0189\3\2\2\2c\u018b\3\2\2\2e\u0192\3\2\2\2g"+
		"\u0199\3\2\2\2ij\7E\2\2jk\7q\2\2kl\7p\2\2lm\7e\2\2mn\7g\2\2no\7r\2\2o"+
		"p\7v\2\2p\4\3\2\2\2qr\7c\2\2rs\7u\2\2s\6\3\2\2\2tu\7h\2\2uv\7k\2\2vw\7"+
		"p\2\2wx\7c\2\2xy\7n\2\2y\b\3\2\2\2z{\7c\2\2{|\7d\2\2|}\7u\2\2}~\7v\2\2"+
		"~\177\7t\2\2\177\u0080\7c\2\2\u0080\u0081\7e\2\2\u0081\u0082\7v\2\2\u0082"+
		"\n\3\2\2\2\u0083\u0084\7o\2\2\u0084\u0085\7w\2\2\u0085\u0086\7n\2\2\u0086"+
		"\u0087\7v\2\2\u0087\u0088\7k\2\2\u0088\u0089\7r\2\2\u0089\u008a\7n\2\2"+
		"\u008a\u008b\7g\2\2\u008b\f\3\2\2\2\u008c\u008d\7q\2\2\u008d\u008e\7r"+
		"\2\2\u008e\u008f\7v\2\2\u008f\u0090\7k\2\2\u0090\u0091\7q\2\2\u0091\u0092"+
		"\7p\2\2\u0092\u0093\7c\2\2\u0093\u0094\7n\2\2\u0094\16\3\2\2\2\u0095\u0096"+
		"\7j\2\2\u0096\u0097\7c\2\2\u0097\u0098\7u\2\2\u0098\u0099\7/\2\2\u0099"+
		"\u009a\7e\2\2\u009a\u009b\7q\2\2\u009b\u009c\7f\2\2\u009c\u009d\7g\2\2"+
		"\u009d\20\3\2\2\2\u009e\u009f\7g\2\2\u009f\u00a0\7z\2\2\u00a0\u00a1\7"+
		"v\2\2\u00a1\u00a2\7g\2\2\u00a2\u00a3\7p\2\2\u00a3\u00a4\7u\2\2\u00a4\u00a5"+
		"\7k\2\2\u00a5\u00a6\7d\2\2\u00a6\u00a7\7n\2\2\u00a7\u00a8\7g\2\2\u00a8"+
		"\22\3\2\2\2\u00a9\u00aa\7x\2\2\u00aa\u00ab\7c\2\2\u00ab\u00ac\7t\2\2\u00ac"+
		"\24\3\2\2\2\u00ad\u00ae\7t\2\2\u00ae\u00af\7q\2\2\u00af\u00b0\7q\2\2\u00b0"+
		"\u00b1\7v\2\2\u00b1\26\3\2\2\2\u00b2\u00b3\7u\2\2\u00b3\u00b4\7k\2\2\u00b4"+
		"\u00b5\7p\2\2\u00b5\u00b6\7i\2\2\u00b6\u00b7\7n\2\2\u00b7\u00b8\7g\2\2"+
		"\u00b8\u00b9\7v\2\2\u00b9\u00ba\7q\2\2\u00ba\u00bb\7p\2\2\u00bb\30\3\2"+
		"\2\2\u00bc\u00bd\7p\2\2\u00bd\u00be\7g\2\2\u00be\u00bf\7y\2\2\u00bf\32"+
		"\3\2\2\2\u00c0\u00c1\7r\2\2\u00c1\u00c2\7q\2\2\u00c2\u00c3\7n\2\2\u00c3"+
		"\u00c4\7{\2\2\u00c4\u00c5\7o\2\2\u00c5\u00c6\7q\2\2\u00c6\u00c7\7t\2\2"+
		"\u00c7\u00c8\7r\2\2\u00c8\u00c9\7j\2\2\u00c9\u00ca\7k\2\2\u00ca\u00cb"+
		"\7e\2\2\u00cb\34\3\2\2\2\u00cc\u00cd\7o\2\2\u00cd\u00ce\7q\2\2\u00ce\u00cf"+
		"\7t\2\2\u00cf\u00d0\7r\2\2\u00d0\u00d1\7j\2\2\u00d1\36\3\2\2\2\u00d2\u00d3"+
		"\7Y\2\2\u00d3\u00d4\7q\2\2\u00d4\u00d5\7t\2\2\u00d5\u00d6\7f\2\2\u00d6"+
		" \3\2\2\2\u00d7\u00d8\5#\22\2\u00d8\u00d9\5%\23\2\u00d9\"\3\2\2\2\u00da"+
		"\u00db\7]\2\2\u00db$\3\2\2\2\u00dc\u00dd\7_\2\2\u00dd&\3\2\2\2\u00de\u00df"+
		"\7}\2\2\u00df\u00e0\b\24\2\2\u00e0(\3\2\2\2\u00e1\u00e2\7\177\2\2\u00e2"+
		"\u00e3\b\25\3\2\u00e3*\3\2\2\2\u00e4\u00e5\7>\2\2\u00e5,\3\2\2\2\u00e6"+
		"\u00e7\7@\2\2\u00e7.\3\2\2\2\u00e8\u00e9\7.\2\2\u00e9\60\3\2\2\2\u00ea"+
		"\u00eb\7\60\2\2\u00eb\62\3\2\2\2\u00ec\u00ed\7?\2\2\u00ed\64\3\2\2\2\u00ee"+
		"\u00ef\7$\2\2\u00ef\66\3\2\2\2\u00f0\u00f2\7=\2\2\u00f1\u00f0\3\2\2\2"+
		"\u00f2\u00f3\3\2\2\2\u00f3\u00f1\3\2\2\2\u00f3\u00f4\3\2\2\2\u00f4\u00f5"+
		"\3\2\2\2\u00f5\u00f6\b\34\4\2\u00f68\3\2\2\2\u00f7\u00f8\7-\2\2\u00f8"+
		":\3\2\2\2\u00f9\u00fa\7/\2\2\u00fa<\3\2\2\2\u00fb\u00fc\7W\2\2\u00fc\u00fd"+
		"\7k\2\2\u00fd\u00fe\7f\2\2\u00fe>\3\2\2\2\u00ff\u0100\7K\2\2\u0100\u0101"+
		"\7p\2\2\u0101\u0102\7v\2\2\u0102\u0103\7g\2\2\u0103\u0104\7i\2\2\u0104"+
		"\u0105\7g\2\2\u0105\u0106\7t\2\2\u0106@\3\2\2\2\u0107\u0108\7P\2\2\u0108"+
		"\u0109\7c\2\2\u0109\u010a\7v\2\2\u010a\u010b\7w\2\2\u010b\u010c\7t\2\2"+
		"\u010c\u010d\7c\2\2\u010d\u010e\7n\2\2\u010eB\3\2\2\2\u010f\u0110\7F\2"+
		"\2\u0110\u0111\7q\2\2\u0111\u0112\7w\2\2\u0112\u0113\7d\2\2\u0113\u0114"+
		"\7n\2\2\u0114\u0115\7g\2\2\u0115D\3\2\2\2\u0116\u0117\7U\2\2\u0117\u0118"+
		"\7v\2\2\u0118\u0119\7t\2\2\u0119\u011a\7k\2\2\u011a\u011b\7p\2\2\u011b"+
		"\u011c\7i\2\2\u011cF\3\2\2\2\u011d\u011e\7D\2\2\u011e\u011f\7q\2\2\u011f"+
		"\u0120\7q\2\2\u0120\u0121\7n\2\2\u0121\u0122\7g\2\2\u0122\u0123\7c\2\2"+
		"\u0123\u0124\7p\2\2\u0124H\3\2\2\2\u0125\u0126\7v\2\2\u0126\u0127\7t\2"+
		"\2\u0127\u0128\7w\2\2\u0128\u012f\7g\2\2\u0129\u012a\7h\2\2\u012a\u012b"+
		"\7c\2\2\u012b\u012c\7n\2\2\u012c\u012d\7u\2\2\u012d\u012f\7g\2\2\u012e"+
		"\u0125\3\2\2\2\u012e\u0129\3\2\2\2\u012fJ\3\2\2\2\u0130\u0132\59\35\2"+
		"\u0131\u0130\3\2\2\2\u0131\u0132\3\2\2\2\u0132\u0134\3\2\2\2\u0133\u0135"+
		"\5U+\2\u0134\u0133\3\2\2\2\u0135\u0136\3\2\2\2\u0136\u0134\3\2\2\2\u0136"+
		"\u0137\3\2\2\2\u0137L\3\2\2\2\u0138\u013a\5;\36\2\u0139\u013b\5U+\2\u013a"+
		"\u0139\3\2\2\2\u013b\u013c\3\2\2\2\u013c\u013a\3\2\2\2\u013c\u013d\3\2"+
		"\2\2\u013dN\3\2\2\2\u013e\u0141\59\35\2\u013f\u0141\5;\36\2\u0140\u013e"+
		"\3\2\2\2\u0140\u013f\3\2\2\2\u0140\u0141\3\2\2\2\u0141\u0143\3\2\2\2\u0142"+
		"\u0144\5U+\2\u0143\u0142\3\2\2\2\u0144\u0145\3\2\2\2\u0145\u0143\3\2\2"+
		"\2\u0145\u0146\3\2\2\2\u0146\u0147\3\2\2\2\u0147\u0149\5\61\31\2\u0148"+
		"\u014a\5U+\2\u0149\u0148\3\2\2\2\u014a\u014b\3\2\2\2\u014b\u0149\3\2\2"+
		"\2\u014b\u014c\3\2\2\2\u014cP\3\2\2\2\u014d\u0151\5\65\33\2\u014e\u0150"+
		"\n\2\2\2\u014f\u014e\3\2\2\2\u0150\u0153\3\2\2\2\u0151\u014f\3\2\2\2\u0151"+
		"\u0152\3\2\2\2\u0152\u0154\3\2\2\2\u0153\u0151\3\2\2\2\u0154\u0155\5\65"+
		"\33\2\u0155R\3\2\2\2\u0156\u015b\5W,\2\u0157\u015a\5U+\2\u0158\u015a\5"+
		"W,\2\u0159\u0157\3\2\2\2\u0159\u0158\3\2\2\2\u015a\u015d\3\2\2\2\u015b"+
		"\u0159\3\2\2\2\u015b\u015c\3\2\2\2\u015cT\3\2\2\2\u015d\u015b\3\2\2\2"+
		"\u015e\u015f\t\3\2\2\u015fV\3\2\2\2\u0160\u0161\t\4\2\2\u0161X\3\2\2\2"+
		"\u0162\u0164\5a\61\2\u0163\u0162\3\2\2\2\u0164\u0165\3\2\2\2\u0165\u0163"+
		"\3\2\2\2\u0165\u0166\3\2\2\2\u0166\u016a\3\2\2\2\u0167\u0169\5_\60\2\u0168"+
		"\u0167\3\2\2\2\u0169\u016c\3\2\2\2\u016a\u0168\3\2\2\2\u016a\u016b\3\2"+
		"\2\2\u016b\u016d\3\2\2\2\u016c\u016a\3\2\2\2\u016d\u016e\b-\5\2\u016e"+
		"Z\3\2\2\2\u016f\u0171\5_\60\2\u0170\u016f\3\2\2\2\u0171\u0172\3\2\2\2"+
		"\u0172\u0170\3\2\2\2\u0172\u0173\3\2\2\2\u0173\u0175\3\2\2\2\u0174\u0176"+
		"\7\2\2\3\u0175\u0174\3\2\2\2\u0175\u0176\3\2\2\2\u0176\u0177\3\2\2\2\u0177"+
		"\u0178\b.\6\2\u0178\\\3\2\2\2\u0179\u017d\7)\2\2\u017a\u017c\13\2\2\2"+
		"\u017b\u017a\3\2\2\2\u017c\u017f\3\2\2\2\u017d\u017e\3\2\2\2\u017d\u017b"+
		"\3\2\2\2\u017e\u0180\3\2\2\2\u017f\u017d\3\2\2\2\u0180\u0181\5a\61\2\u0181"+
		"^\3\2\2\2\u0182\u0183\t\5\2\2\u0183`\3\2\2\2\u0184\u0186\7\17\2\2\u0185"+
		"\u0184\3\2\2\2\u0185\u0186\3\2\2\2\u0186\u0187\3\2\2\2\u0187\u018a\7\f"+
		"\2\2\u0188\u018a\7\f\2\2\u0189\u0185\3\2\2\2\u0189\u0188\3\2\2\2\u018a"+
		"b\3\2\2\2\u018b\u018c\7k\2\2\u018c\u018d\7p\2\2\u018d\u018e\7f\2\2\u018e"+
		"\u018f\7g\2\2\u018f\u0190\7p\2\2\u0190\u0191\7v\2\2\u0191d\3\2\2\2\u0192"+
		"\u0193\7f\2\2\u0193\u0194\7g\2\2\u0194\u0195\7f\2\2\u0195\u0196\7g\2\2"+
		"\u0196\u0197\7p\2\2\u0197\u0198\7v\2\2\u0198f\3\2\2\2\u0199\u019a\13\2"+
		"\2\2\u019ah\3\2\2\2\25\2\u00f3\u012e\u0131\u0136\u013c\u0140\u0145\u014b"+
		"\u0151\u0159\u015b\u0165\u016a\u0172\u0175\u017d\u0185\u0189\7\3\24\2"+
		"\3\25\3\3\34\4\3-\5\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}