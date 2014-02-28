// Generated from /home/bycor/Projects/Tara/src/AntlrM2/src/TaraM2Lexer.g4 by ANTLR 4.x

    package monet.tara.compiler.parser.antlr;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TaraM2Lexer extends Lexer {
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		CONCEPT=1, FROM=2, AS=3, FINAL=4, ABSTRACT=5, MULTIPLE=6, OPTIONAL=7, 
		HAS_CODE=8, EXTENSIBLE=9, WORD=10, VAR=11, ROOT=12, SINGLETON=13, NEW=14, 
		LIST=15, LEFT_SQUARE=16, RIGHT_SQUARE=17, OPEN_BRACKET=18, CLOSE_BRACKET=19, 
		OPEN_AN=20, CLOSE_AN=21, COMMA=22, DOT=23, ASSIGN=24, DOUBLE_COMMAS=25, 
		SEMICOLON=26, POSITIVE=27, NEGATIVE=28, UID_TYPE=29, INT_TYPE=30, NATURAL_TYPE=31, 
		DOUBLE_TYPE=32, STRING_TYPE=33, BOOLEAN_TYPE=34, BOOLEAN_VALUE=35, POSITIVE_VALUE=36, 
		NEGATIVE_VALUE=37, DOUBLE_VALUE=38, STRING_VALUE=39, IDENTIFIER=40, DIGIT=41, 
		LETTER=42, NEWLINE=43, SPACES=44, DOC=45, SP=46, NL=47, NEWLINE_INDENT=48, 
		DEDENT=49, UNKNOWN_TOKEN=50;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'Concept'", "'from'", "'as'", "'final'", "'abstract'", "'multiple'", 
		"'optional'", "'has-code'", "'extensible'", "'Word'", "'var'", "'root'", 
		"'singleton'", "'new'", "LIST", "'['", "']'", "'{'", "'}'", "'<'", "'>'", 
		"','", "'.'", "':'", "'\"'", "SEMICOLON", "'+'", "'-'", "'Uid'", "'Int'", 
		"'Natural'", "'Double'", "'String'", "'Boolean'", "BOOLEAN_VALUE", "POSITIVE_VALUE", 
		"NEGATIVE_VALUE", "DOUBLE_VALUE", "STRING_VALUE", "IDENTIFIER", "DIGIT", 
		"LETTER", "NEWLINE", "SPACES", "DOC", "SP", "NL", "'indent'", "'dedent'", 
		"UNKNOWN_TOKEN"
	};
	public static final String[] ruleNames = {
		"CONCEPT", "FROM", "AS", "FINAL", "ABSTRACT", "MULTIPLE", "OPTIONAL", 
		"HAS_CODE", "EXTENSIBLE", "WORD", "VAR", "ROOT", "SINGLETON", "NEW", "LIST", 
		"LEFT_SQUARE", "RIGHT_SQUARE", "OPEN_BRACKET", "CLOSE_BRACKET", "OPEN_AN", 
		"CLOSE_AN", "COMMA", "DOT", "ASSIGN", "DOUBLE_COMMAS", "SEMICOLON", "POSITIVE", 
		"NEGATIVE", "UID_TYPE", "INT_TYPE", "NATURAL_TYPE", "DOUBLE_TYPE", "STRING_TYPE", 
		"BOOLEAN_TYPE", "BOOLEAN_VALUE", "POSITIVE_VALUE", "NEGATIVE_VALUE", "DOUBLE_VALUE", 
		"STRING_VALUE", "IDENTIFIER", "DIGIT", "LETTER", "NEWLINE", "SPACES", 
		"DOC", "SP", "NL", "NEWLINE_INDENT", "DEDENT", "UNKNOWN_TOKEN"
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
	            blockManager.spaces(getTextSpaces(getText()));
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
	        blockManager.addSemicolon(getText().length());
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
	        if (token.toString().equals("NEWLINE_INDENT")) return NEWLINE_INDENT;
	        return UNKNOWN_TOKEN;
	    }


	public TaraM2Lexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,null,null,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "TaraM2Lexer.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }


	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return null; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 17: OPEN_BRACKET_action((RuleContext)_localctx, actionIndex); break;
		case 18: CLOSE_BRACKET_action((RuleContext)_localctx, actionIndex); break;
		case 25: SEMICOLON_action((RuleContext)_localctx, actionIndex); break;
		case 42: NEWLINE_action((RuleContext)_localctx, actionIndex); break;
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\64\u0188\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3"+
		"\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\20\3\20\3\20"+
		"\3\21\3\21\3\22\3\22\3\23\3\23\3\23\3\24\3\24\3\24\3\25\3\25\3\26\3\26"+
		"\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\33\6\33\u00e3\n\33\r\33\16"+
		"\33\u00e4\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\36\3\36\3\37\3\37"+
		"\3\37\3\37\3 \3 \3 \3 \3 \3 \3 \3 \3!\3!\3!\3!\3!\3!\3!\3\"\3\"\3\"\3"+
		"\"\3\"\3\"\3\"\3#\3#\3#\3#\3#\3#\3#\3#\3$\3$\3$\3$\3$\3$\3$\3$\3$\5$\u011c"+
		"\n$\3%\5%\u011f\n%\3%\6%\u0122\n%\r%\16%\u0123\3&\3&\6&\u0128\n&\r&\16"+
		"&\u0129\3\'\3\'\5\'\u012e\n\'\3\'\6\'\u0131\n\'\r\'\16\'\u0132\3\'\3\'"+
		"\6\'\u0137\n\'\r\'\16\'\u0138\3(\3(\7(\u013d\n(\f(\16(\u0140\13(\3(\3"+
		"(\3)\3)\3)\7)\u0147\n)\f)\16)\u014a\13)\3*\3*\3+\3+\3,\6,\u0151\n,\r,"+
		"\16,\u0152\3,\7,\u0156\n,\f,\16,\u0159\13,\3,\3,\3-\6-\u015e\n-\r-\16"+
		"-\u015f\3-\5-\u0163\n-\3-\3-\3.\3.\7.\u0169\n.\f.\16.\u016c\13.\3.\3."+
		"\3/\3/\3\60\5\60\u0173\n\60\3\60\3\60\5\60\u0177\n\60\3\61\3\61\3\61\3"+
		"\61\3\61\3\61\3\61\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\63\3\63\3\u016a"+
		"\2\64\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35"+
		"\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36"+
		";\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a\62c\63e\64\3\2\6\3\2$"+
		"$\3\2\62;\4\2C\\c|\4\2\13\13\"\"\u019a\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2"+
		"\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2"+
		"\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3"+
		"\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3"+
		"\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65"+
		"\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3"+
		"\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2"+
		"\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2"+
		"[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\3g\3"+
		"\2\2\2\5o\3\2\2\2\7t\3\2\2\2\tw\3\2\2\2\13}\3\2\2\2\r\u0086\3\2\2\2\17"+
		"\u008f\3\2\2\2\21\u0098\3\2\2\2\23\u00a1\3\2\2\2\25\u00ac\3\2\2\2\27\u00b1"+
		"\3\2\2\2\31\u00b5\3\2\2\2\33\u00ba\3\2\2\2\35\u00c4\3\2\2\2\37\u00c8\3"+
		"\2\2\2!\u00cb\3\2\2\2#\u00cd\3\2\2\2%\u00cf\3\2\2\2\'\u00d2\3\2\2\2)\u00d5"+
		"\3\2\2\2+\u00d7\3\2\2\2-\u00d9\3\2\2\2/\u00db\3\2\2\2\61\u00dd\3\2\2\2"+
		"\63\u00df\3\2\2\2\65\u00e2\3\2\2\2\67\u00e8\3\2\2\29\u00ea\3\2\2\2;\u00ec"+
		"\3\2\2\2=\u00f0\3\2\2\2?\u00f4\3\2\2\2A\u00fc\3\2\2\2C\u0103\3\2\2\2E"+
		"\u010a\3\2\2\2G\u011b\3\2\2\2I\u011e\3\2\2\2K\u0125\3\2\2\2M\u012d\3\2"+
		"\2\2O\u013a\3\2\2\2Q\u0143\3\2\2\2S\u014b\3\2\2\2U\u014d\3\2\2\2W\u0150"+
		"\3\2\2\2Y\u015d\3\2\2\2[\u0166\3\2\2\2]\u016f\3\2\2\2_\u0176\3\2\2\2a"+
		"\u0178\3\2\2\2c\u017f\3\2\2\2e\u0186\3\2\2\2gh\7E\2\2hi\7q\2\2ij\7p\2"+
		"\2jk\7e\2\2kl\7g\2\2lm\7r\2\2mn\7v\2\2n\4\3\2\2\2op\7h\2\2pq\7t\2\2qr"+
		"\7q\2\2rs\7o\2\2s\6\3\2\2\2tu\7c\2\2uv\7u\2\2v\b\3\2\2\2wx\7h\2\2xy\7"+
		"k\2\2yz\7p\2\2z{\7c\2\2{|\7n\2\2|\n\3\2\2\2}~\7c\2\2~\177\7d\2\2\177\u0080"+
		"\7u\2\2\u0080\u0081\7v\2\2\u0081\u0082\7t\2\2\u0082\u0083\7c\2\2\u0083"+
		"\u0084\7e\2\2\u0084\u0085\7v\2\2\u0085\f\3\2\2\2\u0086\u0087\7o\2\2\u0087"+
		"\u0088\7w\2\2\u0088\u0089\7n\2\2\u0089\u008a\7v\2\2\u008a\u008b\7k\2\2"+
		"\u008b\u008c\7r\2\2\u008c\u008d\7n\2\2\u008d\u008e\7g\2\2\u008e\16\3\2"+
		"\2\2\u008f\u0090\7q\2\2\u0090\u0091\7r\2\2\u0091\u0092\7v\2\2\u0092\u0093"+
		"\7k\2\2\u0093\u0094\7q\2\2\u0094\u0095\7p\2\2\u0095\u0096\7c\2\2\u0096"+
		"\u0097\7n\2\2\u0097\20\3\2\2\2\u0098\u0099\7j\2\2\u0099\u009a\7c\2\2\u009a"+
		"\u009b\7u\2\2\u009b\u009c\7/\2\2\u009c\u009d\7e\2\2\u009d\u009e\7q\2\2"+
		"\u009e\u009f\7f\2\2\u009f\u00a0\7g\2\2\u00a0\22\3\2\2\2\u00a1\u00a2\7"+
		"g\2\2\u00a2\u00a3\7z\2\2\u00a3\u00a4\7v\2\2\u00a4\u00a5\7g\2\2\u00a5\u00a6"+
		"\7p\2\2\u00a6\u00a7\7u\2\2\u00a7\u00a8\7k\2\2\u00a8\u00a9\7d\2\2\u00a9"+
		"\u00aa\7n\2\2\u00aa\u00ab\7g\2\2\u00ab\24\3\2\2\2\u00ac\u00ad\7Y\2\2\u00ad"+
		"\u00ae\7q\2\2\u00ae\u00af\7t\2\2\u00af\u00b0\7f\2\2\u00b0\26\3\2\2\2\u00b1"+
		"\u00b2\7x\2\2\u00b2\u00b3\7c\2\2\u00b3\u00b4\7t\2\2\u00b4\30\3\2\2\2\u00b5"+
		"\u00b6\7t\2\2\u00b6\u00b7\7q\2\2\u00b7\u00b8\7q\2\2\u00b8\u00b9\7v\2\2"+
		"\u00b9\32\3\2\2\2\u00ba\u00bb\7u\2\2\u00bb\u00bc\7k\2\2\u00bc\u00bd\7"+
		"p\2\2\u00bd\u00be\7i\2\2\u00be\u00bf\7n\2\2\u00bf\u00c0\7g\2\2\u00c0\u00c1"+
		"\7v\2\2\u00c1\u00c2\7q\2\2\u00c2\u00c3\7p\2\2\u00c3\34\3\2\2\2\u00c4\u00c5"+
		"\7p\2\2\u00c5\u00c6\7g\2\2\u00c6\u00c7\7y\2\2\u00c7\36\3\2\2\2\u00c8\u00c9"+
		"\5!\21\2\u00c9\u00ca\5#\22\2\u00ca \3\2\2\2\u00cb\u00cc\7]\2\2\u00cc\""+
		"\3\2\2\2\u00cd\u00ce\7_\2\2\u00ce$\3\2\2\2\u00cf\u00d0\7}\2\2\u00d0\u00d1"+
		"\b\23\2\2\u00d1&\3\2\2\2\u00d2\u00d3\7\177\2\2\u00d3\u00d4\b\24\3\2\u00d4"+
		"(\3\2\2\2\u00d5\u00d6\7>\2\2\u00d6*\3\2\2\2\u00d7\u00d8\7@\2\2\u00d8,"+
		"\3\2\2\2\u00d9\u00da\7.\2\2\u00da.\3\2\2\2\u00db\u00dc\7\60\2\2\u00dc"+
		"\60\3\2\2\2\u00dd\u00de\7<\2\2\u00de\62\3\2\2\2\u00df\u00e0\7$\2\2\u00e0"+
		"\64\3\2\2\2\u00e1\u00e3\7=\2\2\u00e2\u00e1\3\2\2\2\u00e3\u00e4\3\2\2\2"+
		"\u00e4\u00e2\3\2\2\2\u00e4\u00e5\3\2\2\2\u00e5\u00e6\3\2\2\2\u00e6\u00e7"+
		"\b\33\4\2\u00e7\66\3\2\2\2\u00e8\u00e9\7-\2\2\u00e98\3\2\2\2\u00ea\u00eb"+
		"\7/\2\2\u00eb:\3\2\2\2\u00ec\u00ed\7W\2\2\u00ed\u00ee\7k\2\2\u00ee\u00ef"+
		"\7f\2\2\u00ef<\3\2\2\2\u00f0\u00f1\7K\2\2\u00f1\u00f2\7p\2\2\u00f2\u00f3"+
		"\7v\2\2\u00f3>\3\2\2\2\u00f4\u00f5\7P\2\2\u00f5\u00f6\7c\2\2\u00f6\u00f7"+
		"\7v\2\2\u00f7\u00f8\7w\2\2\u00f8\u00f9\7t\2\2\u00f9\u00fa\7c\2\2\u00fa"+
		"\u00fb\7n\2\2\u00fb@\3\2\2\2\u00fc\u00fd\7F\2\2\u00fd\u00fe\7q\2\2\u00fe"+
		"\u00ff\7w\2\2\u00ff\u0100\7d\2\2\u0100\u0101\7n\2\2\u0101\u0102\7g\2\2"+
		"\u0102B\3\2\2\2\u0103\u0104\7U\2\2\u0104\u0105\7v\2\2\u0105\u0106\7t\2"+
		"\2\u0106\u0107\7k\2\2\u0107\u0108\7p\2\2\u0108\u0109\7i\2\2\u0109D\3\2"+
		"\2\2\u010a\u010b\7D\2\2\u010b\u010c\7q\2\2\u010c\u010d\7q\2\2\u010d\u010e"+
		"\7n\2\2\u010e\u010f\7g\2\2\u010f\u0110\7c\2\2\u0110\u0111\7p\2\2\u0111"+
		"F\3\2\2\2\u0112\u0113\7v\2\2\u0113\u0114\7t\2\2\u0114\u0115\7w\2\2\u0115"+
		"\u011c\7g\2\2\u0116\u0117\7h\2\2\u0117\u0118\7c\2\2\u0118\u0119\7n\2\2"+
		"\u0119\u011a\7u\2\2\u011a\u011c\7g\2\2\u011b\u0112\3\2\2\2\u011b\u0116"+
		"\3\2\2\2\u011cH\3\2\2\2\u011d\u011f\5\67\34\2\u011e\u011d\3\2\2\2\u011e"+
		"\u011f\3\2\2\2\u011f\u0121\3\2\2\2\u0120\u0122\5S*\2\u0121\u0120\3\2\2"+
		"\2\u0122\u0123\3\2\2\2\u0123\u0121\3\2\2\2\u0123\u0124\3\2\2\2\u0124J"+
		"\3\2\2\2\u0125\u0127\59\35\2\u0126\u0128\5S*\2\u0127\u0126\3\2\2\2\u0128"+
		"\u0129\3\2\2\2\u0129\u0127\3\2\2\2\u0129\u012a\3\2\2\2\u012aL\3\2\2\2"+
		"\u012b\u012e\5\67\34\2\u012c\u012e\59\35\2\u012d\u012b\3\2\2\2\u012d\u012c"+
		"\3\2\2\2\u012d\u012e\3\2\2\2\u012e\u0130\3\2\2\2\u012f\u0131\5S*\2\u0130"+
		"\u012f\3\2\2\2\u0131\u0132\3\2\2\2\u0132\u0130\3\2\2\2\u0132\u0133\3\2"+
		"\2\2\u0133\u0134\3\2\2\2\u0134\u0136\5/\30\2\u0135\u0137\5S*\2\u0136\u0135"+
		"\3\2\2\2\u0137\u0138\3\2\2\2\u0138\u0136\3\2\2\2\u0138\u0139\3\2\2\2\u0139"+
		"N\3\2\2\2\u013a\u013e\5\63\32\2\u013b\u013d\n\2\2\2\u013c\u013b\3\2\2"+
		"\2\u013d\u0140\3\2\2\2\u013e\u013c\3\2\2\2\u013e\u013f\3\2\2\2\u013f\u0141"+
		"\3\2\2\2\u0140\u013e\3\2\2\2\u0141\u0142\5\63\32\2\u0142P\3\2\2\2\u0143"+
		"\u0148\5U+\2\u0144\u0147\5S*\2\u0145\u0147\5U+\2\u0146\u0144\3\2\2\2\u0146"+
		"\u0145\3\2\2\2\u0147\u014a\3\2\2\2\u0148\u0146\3\2\2\2\u0148\u0149\3\2"+
		"\2\2\u0149R\3\2\2\2\u014a\u0148\3\2\2\2\u014b\u014c\t\3\2\2\u014cT\3\2"+
		"\2\2\u014d\u014e\t\4\2\2\u014eV\3\2\2\2\u014f\u0151\5_\60\2\u0150\u014f"+
		"\3\2\2\2\u0151\u0152\3\2\2\2\u0152\u0150\3\2\2\2\u0152\u0153\3\2\2\2\u0153"+
		"\u0157\3\2\2\2\u0154\u0156\5]/\2\u0155\u0154\3\2\2\2\u0156\u0159\3\2\2"+
		"\2\u0157\u0155\3\2\2\2\u0157\u0158\3\2\2\2\u0158\u015a\3\2\2\2\u0159\u0157"+
		"\3\2\2\2\u015a\u015b\b,\5\2\u015bX\3\2\2\2\u015c\u015e\5]/\2\u015d\u015c"+
		"\3\2\2\2\u015e\u015f\3\2\2\2\u015f\u015d\3\2\2\2\u015f\u0160\3\2\2\2\u0160"+
		"\u0162\3\2\2\2\u0161\u0163\7\2\2\3\u0162\u0161\3\2\2\2\u0162\u0163\3\2"+
		"\2\2\u0163\u0164\3\2\2\2\u0164\u0165\b-\6\2\u0165Z\3\2\2\2\u0166\u016a"+
		"\7)\2\2\u0167\u0169\13\2\2\2\u0168\u0167\3\2\2\2\u0169\u016c\3\2\2\2\u016a"+
		"\u016b\3\2\2\2\u016a\u0168\3\2\2\2\u016b\u016d\3\2\2\2\u016c\u016a\3\2"+
		"\2\2\u016d\u016e\5_\60\2\u016e\\\3\2\2\2\u016f\u0170\t\5\2\2\u0170^\3"+
		"\2\2\2\u0171\u0173\7\17\2\2\u0172\u0171\3\2\2\2\u0172\u0173\3\2\2\2\u0173"+
		"\u0174\3\2\2\2\u0174\u0177\7\f\2\2\u0175\u0177\7\f\2\2\u0176\u0172\3\2"+
		"\2\2\u0176\u0175\3\2\2\2\u0177`\3\2\2\2\u0178\u0179\7k\2\2\u0179\u017a"+
		"\7p\2\2\u017a\u017b\7f\2\2\u017b\u017c\7g\2\2\u017c\u017d\7p\2\2\u017d"+
		"\u017e\7v\2\2\u017eb\3\2\2\2\u017f\u0180\7f\2\2\u0180\u0181\7g\2\2\u0181"+
		"\u0182\7f\2\2\u0182\u0183\7g\2\2\u0183\u0184\7p\2\2\u0184\u0185\7v\2\2"+
		"\u0185d\3\2\2\2\u0186\u0187\13\2\2\2\u0187f\3\2\2\2\25\2\u00e4\u011b\u011e"+
		"\u0123\u0129\u012d\u0132\u0138\u013e\u0146\u0148\u0152\u0157\u015f\u0162"+
		"\u016a\u0172\u0176\7\3\23\2\3\24\3\3\33\4\3,\5\2\3\2";
}