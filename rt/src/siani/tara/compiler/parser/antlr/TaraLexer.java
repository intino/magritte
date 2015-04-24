// Generated from /Users/oroncal/workspace/tara/rt/src/siani/tara/compiler/parser/antlr/TaraLexer.g4 by ANTLR 4.5
package siani.tara.compiler.parser.antlr;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.NotNull;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TaraLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		METAIDENTIFIER=1, SUB=2, USE=3, DSL=4, VAR=5, AS=6, HAS=7, ON=8, IS=9, 
		INTO=10, WITH=11, EXTENDS=12, ABSTRACT=13, SINGLE=14, MULTIPLE=15, OPTIONAL=16, 
		REQUIRED=17, TERMINAL=18, PROPERTY=19, FEATURE=20, AGGREGATED=21, ASSOCIATED=22, 
		COMPONENT=23, READONLY=24, ENCLOSED=25, FACET=26, ADDRESSED=27, LEFT_PARENTHESIS=28, 
		RIGHT_PARENTHESIS=29, LEFT_SQUARE=30, RIGHT_SQUARE=31, LIST=32, INLINE=33, 
		CLOSE_INLINE=34, AMPERSAND=35, COLON=36, COMMA=37, DOT=38, EQUALS=39, 
		APHOSTROPHE=40, SEMICOLON=41, STAR=42, PLUS=43, WORD=44, RESOURCE=45, 
		INT_TYPE=46, NATURAL_TYPE=47, NATIVE_TYPE=48, DOUBLE_TYPE=49, STRING_TYPE=50, 
		BOOLEAN_TYPE=51, MEASURE_TYPE=52, RATIO_TYPE=53, DATE_TYPE=54, EMPTY=55, 
		BLOCK_COMMENT=56, LINE_COMMENT=57, SCIENCE_NOT=58, BOOLEAN_VALUE=59, NATURAL_VALUE=60, 
		NEGATIVE_VALUE=61, DOUBLE_VALUE=62, STRING_VALUE=63, STRING_MULTILINE_VALUE_KEY=64, 
		ADDRESS_VALUE=65, IDENTIFIER=66, MEASURE_VALUE=67, NEWLINE=68, SPACES=69, 
		DOC=70, SP=71, NL=72, NEW_LINE_INDENT=73, DEDENT=74, UNKNOWN_TOKEN=75;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"METAIDENTIFIER", "SUB", "USE", "DSL", "VAR", "AS", "HAS", "ON", "IS", 
		"INTO", "WITH", "EXTENDS", "ABSTRACT", "SINGLE", "MULTIPLE", "OPTIONAL", 
		"REQUIRED", "TERMINAL", "PROPERTY", "FEATURE", "AGGREGATED", "ASSOCIATED", 
		"COMPONENT", "READONLY", "ENCLOSED", "FACET", "ADDRESSED", "LEFT_PARENTHESIS", 
		"RIGHT_PARENTHESIS", "LEFT_SQUARE", "RIGHT_SQUARE", "LIST", "INLINE", 
		"CLOSE_INLINE", "AMPERSAND", "COLON", "COMMA", "DOT", "EQUALS", "APHOSTROPHE", 
		"SEMICOLON", "STAR", "PLUS", "WORD", "RESOURCE", "INT_TYPE", "NATURAL_TYPE", 
		"NATIVE_TYPE", "DOUBLE_TYPE", "STRING_TYPE", "BOOLEAN_TYPE", "MEASURE_TYPE", 
		"RATIO_TYPE", "DATE_TYPE", "EMPTY", "BLOCK_COMMENT", "LINE_COMMENT", "SCIENCE_NOT", 
		"BOOLEAN_VALUE", "NATURAL_VALUE", "NEGATIVE_VALUE", "DOUBLE_VALUE", "STRING_VALUE", 
		"STRING_MULTILINE_VALUE_KEY", "ADDRESS_VALUE", "IDENTIFIER", "MEASURE_VALUE", 
		"NEWLINE", "SPACES", "DOC", "SP", "NL", "NEW_LINE_INDENT", "DEDENT", "UNKNOWN_TOKEN", 
		"DOLLAR", "EURO", "PERCENTAGE", "GRADE", "BY", "DIVIDED_BY", "DASH", "DASHES", 
		"UNDERDASH", "DIGIT", "LETTER"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'Concept'", "'sub'", "'use'", "'dsl'", "'var'", "'as'", "'has'", 
		"'on'", "'is'", "'into'", "'with'", "'extends'", "'abstract'", "'single'", 
		"'multiple'", "'optional'", "'required'", "'terminal'", "'property'", 
		"'feature'", "'aggregated'", "'associated'", "'component'", "'readonly'", 
		"'enclosed'", "'facet'", "'addressed'", "'('", "')'", "'['", "']'", "'...'", 
		"'>'", "'<'", "'&'", "':'", "','", "'.'", "'='", "'\"'", null, "'*'", 
		"'+'", "'word'", "'file'", "'integer'", "'natural'", "'native'", "'double'", 
		"'string'", "'boolean'", "'measure'", "'ratio'", "'date'", "'empty'", 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, "'indent'", "'dedent'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "METAIDENTIFIER", "SUB", "USE", "DSL", "VAR", "AS", "HAS", "ON", 
		"IS", "INTO", "WITH", "EXTENDS", "ABSTRACT", "SINGLE", "MULTIPLE", "OPTIONAL", 
		"REQUIRED", "TERMINAL", "PROPERTY", "FEATURE", "AGGREGATED", "ASSOCIATED", 
		"COMPONENT", "READONLY", "ENCLOSED", "FACET", "ADDRESSED", "LEFT_PARENTHESIS", 
		"RIGHT_PARENTHESIS", "LEFT_SQUARE", "RIGHT_SQUARE", "LIST", "INLINE", 
		"CLOSE_INLINE", "AMPERSAND", "COLON", "COMMA", "DOT", "EQUALS", "APHOSTROPHE", 
		"SEMICOLON", "STAR", "PLUS", "WORD", "RESOURCE", "INT_TYPE", "NATURAL_TYPE", 
		"NATIVE_TYPE", "DOUBLE_TYPE", "STRING_TYPE", "BOOLEAN_TYPE", "MEASURE_TYPE", 
		"RATIO_TYPE", "DATE_TYPE", "EMPTY", "BLOCK_COMMENT", "LINE_COMMENT", "SCIENCE_NOT", 
		"BOOLEAN_VALUE", "NATURAL_VALUE", "NEGATIVE_VALUE", "DOUBLE_VALUE", "STRING_VALUE", 
		"STRING_MULTILINE_VALUE_KEY", "ADDRESS_VALUE", "IDENTIFIER", "MEASURE_VALUE", 
		"NEWLINE", "SPACES", "DOC", "SP", "NL", "NEW_LINE_INDENT", "DEDENT", "UNKNOWN_TOKEN"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override
	@NotNull
	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


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
		case 32: 
			INLINE_action((RuleContext)_localctx, actionIndex); 
			break;

		case 40: 
			SEMICOLON_action((RuleContext)_localctx, actionIndex); 
			break;

		case 67: 
			NEWLINE_action((RuleContext)_localctx, actionIndex); 
			break;

		case 69: 
			DOC_action((RuleContext)_localctx, actionIndex); 
			break;
		}
	}
	private void INLINE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0: 
			 inline();  
			break;
		}
	}
	private void SEMICOLON_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1: 
			 semicolon();  
			break;
		}
	}
	private void NEWLINE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 2: 
			 newlinesAndSpaces();  
			break;
		}
	}
	private void DOC_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 3: 
			emitToken(DOC);emitToken(NEWLINE); 
			break;
		}
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2M\u02c5\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
		"\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\4O\tO\4P\tP\4Q\tQ\4R\tR\4S\tS\4T\tT"+
		"\4U\tU\4V\tV\4W\tW\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\4"+
		"\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3"+
		"\b\3\t\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3"+
		"\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26"+
		"\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31"+
		"\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32"+
		"\3\33\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34"+
		"\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3!\3!\3\"\3\"\3\""+
		"\3#\3#\3$\3$\3%\3%\3&\3&\3\'\3\'\3(\3(\3)\3)\3*\6*\u018c\n*\r*\16*\u018d"+
		"\3*\3*\3+\3+\3,\3,\3-\3-\3-\3-\3-\3.\3.\3.\3.\3.\3/\3/\3/\3/\3/\3/\3/"+
		"\3/\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\61\3\61\3\61\3\61\3\61\3"+
		"\61\3\61\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\63\3\63\3\63\3\63\3\63\3"+
		"\63\3\63\3\64\3\64\3\64\3\64\3\64\3\64\3\64\3\64\3\65\3\65\3\65\3\65\3"+
		"\65\3\65\3\65\3\65\3\66\3\66\3\66\3\66\3\66\3\66\3\67\3\67\3\67\3\67\3"+
		"\67\38\38\38\38\38\38\39\39\39\39\79\u01ea\n9\f9\169\u01ed\139\39\39\3"+
		"9\39\39\3:\3:\3:\3:\7:\u01f8\n:\f:\16:\u01fb\13:\3:\3:\3;\3;\3;\5;\u0202"+
		"\n;\3;\6;\u0205\n;\r;\16;\u0206\3<\3<\3<\3<\3<\3<\3<\3<\3<\5<\u0212\n"+
		"<\3=\5=\u0215\n=\3=\6=\u0218\n=\r=\16=\u0219\3>\3>\6>\u021e\n>\r>\16>"+
		"\u021f\3?\3?\5?\u0224\n?\3?\6?\u0227\n?\r?\16?\u0228\3?\3?\6?\u022d\n"+
		"?\r?\16?\u022e\3@\3@\7@\u0233\n@\f@\16@\u0236\13@\3@\3@\3A\3A\7A\u023c"+
		"\nA\fA\16A\u023f\13A\3A\3A\3B\3B\3B\3B\3B\3B\3B\3B\3B\6B\u024c\nB\rB\16"+
		"B\u024d\3C\3C\3C\3C\3C\7C\u0255\nC\fC\16C\u0258\13C\3D\3D\3D\3D\3D\5D"+
		"\u025f\nD\3D\3D\3D\3D\3D\3D\3D\3D\3D\7D\u026a\nD\fD\16D\u026d\13D\3E\6"+
		"E\u0270\nE\rE\16E\u0271\3E\7E\u0275\nE\fE\16E\u0278\13E\3E\3E\3F\6F\u027d"+
		"\nF\rF\16F\u027e\3F\5F\u0282\nF\3F\3F\3G\3G\3G\3G\3G\7G\u028b\nG\fG\16"+
		"G\u028e\13G\3G\3G\3G\3H\3H\3I\5I\u0296\nI\3I\3I\5I\u029a\nI\3J\3J\3J\3"+
		"J\3J\3J\3J\3K\3K\3K\3K\3K\3K\3K\3L\3L\3M\3M\3N\3N\3O\3O\3P\3P\3Q\3Q\3"+
		"R\3R\3S\3S\3T\3T\6T\u02bc\nT\rT\16T\u02bd\3U\3U\3V\3V\3W\3W\4\u01eb\u028c"+
		"\2X\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35"+
		"\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36"+
		";\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a\62c\63e\64g\65i\66k\67"+
		"m8o9q:s;u<w=y>{?}@\177A\u0081B\u0083C\u0085D\u0087E\u0089F\u008bG\u008d"+
		"H\u008fI\u0091J\u0093K\u0095L\u0097M\u0099\2\u009b\2\u009d\2\u009f\2\u00a1"+
		"\2\u00a3\2\u00a5\2\u00a7\2\u00a9\2\u00ab\2\u00ad\2\3\2\t\4\2\f\f\17\17"+
		"\3\2$$\3\2//\4\2\13\13\"\"\4\2\u00b2\u00b2\u00bc\u00bc\3\2\62;\6\2C\\"+
		"c|\u00d3\u00d3\u00f3\u00f3\u02e3\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2"+
		"\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2"+
		"\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2"+
		"\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2"+
		"\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2"+
		"\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2"+
		"\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O"+
		"\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2"+
		"\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2"+
		"\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\2u"+
		"\3\2\2\2\2w\3\2\2\2\2y\3\2\2\2\2{\3\2\2\2\2}\3\2\2\2\2\177\3\2\2\2\2\u0081"+
		"\3\2\2\2\2\u0083\3\2\2\2\2\u0085\3\2\2\2\2\u0087\3\2\2\2\2\u0089\3\2\2"+
		"\2\2\u008b\3\2\2\2\2\u008d\3\2\2\2\2\u008f\3\2\2\2\2\u0091\3\2\2\2\2\u0093"+
		"\3\2\2\2\2\u0095\3\2\2\2\2\u0097\3\2\2\2\3\u00af\3\2\2\2\5\u00b7\3\2\2"+
		"\2\7\u00bb\3\2\2\2\t\u00bf\3\2\2\2\13\u00c3\3\2\2\2\r\u00c7\3\2\2\2\17"+
		"\u00ca\3\2\2\2\21\u00ce\3\2\2\2\23\u00d1\3\2\2\2\25\u00d4\3\2\2\2\27\u00d9"+
		"\3\2\2\2\31\u00de\3\2\2\2\33\u00e6\3\2\2\2\35\u00ef\3\2\2\2\37\u00f6\3"+
		"\2\2\2!\u00ff\3\2\2\2#\u0108\3\2\2\2%\u0111\3\2\2\2\'\u011a\3\2\2\2)\u0123"+
		"\3\2\2\2+\u012b\3\2\2\2-\u0136\3\2\2\2/\u0141\3\2\2\2\61\u014b\3\2\2\2"+
		"\63\u0154\3\2\2\2\65\u015d\3\2\2\2\67\u0163\3\2\2\29\u016d\3\2\2\2;\u016f"+
		"\3\2\2\2=\u0171\3\2\2\2?\u0173\3\2\2\2A\u0175\3\2\2\2C\u0179\3\2\2\2E"+
		"\u017c\3\2\2\2G\u017e\3\2\2\2I\u0180\3\2\2\2K\u0182\3\2\2\2M\u0184\3\2"+
		"\2\2O\u0186\3\2\2\2Q\u0188\3\2\2\2S\u018b\3\2\2\2U\u0191\3\2\2\2W\u0193"+
		"\3\2\2\2Y\u0195\3\2\2\2[\u019a\3\2\2\2]\u019f\3\2\2\2_\u01a7\3\2\2\2a"+
		"\u01af\3\2\2\2c\u01b6\3\2\2\2e\u01bd\3\2\2\2g\u01c4\3\2\2\2i\u01cc\3\2"+
		"\2\2k\u01d4\3\2\2\2m\u01da\3\2\2\2o\u01df\3\2\2\2q\u01e5\3\2\2\2s\u01f3"+
		"\3\2\2\2u\u01fe\3\2\2\2w\u0211\3\2\2\2y\u0214\3\2\2\2{\u021b\3\2\2\2}"+
		"\u0223\3\2\2\2\177\u0230\3\2\2\2\u0081\u0239\3\2\2\2\u0083\u0242\3\2\2"+
		"\2\u0085\u024f\3\2\2\2\u0087\u025e\3\2\2\2\u0089\u026f\3\2\2\2\u008b\u027c"+
		"\3\2\2\2\u008d\u0285\3\2\2\2\u008f\u0292\3\2\2\2\u0091\u0299\3\2\2\2\u0093"+
		"\u029b\3\2\2\2\u0095\u02a2\3\2\2\2\u0097\u02a9\3\2\2\2\u0099\u02ab\3\2"+
		"\2\2\u009b\u02ad\3\2\2\2\u009d\u02af\3\2\2\2\u009f\u02b1\3\2\2\2\u00a1"+
		"\u02b3\3\2\2\2\u00a3\u02b5\3\2\2\2\u00a5\u02b7\3\2\2\2\u00a7\u02b9\3\2"+
		"\2\2\u00a9\u02bf\3\2\2\2\u00ab\u02c1\3\2\2\2\u00ad\u02c3\3\2\2\2\u00af"+
		"\u00b0\7E\2\2\u00b0\u00b1\7q\2\2\u00b1\u00b2\7p\2\2\u00b2\u00b3\7e\2\2"+
		"\u00b3\u00b4\7g\2\2\u00b4\u00b5\7r\2\2\u00b5\u00b6\7v\2\2\u00b6\4\3\2"+
		"\2\2\u00b7\u00b8\7u\2\2\u00b8\u00b9\7w\2\2\u00b9\u00ba\7d\2\2\u00ba\6"+
		"\3\2\2\2\u00bb\u00bc\7w\2\2\u00bc\u00bd\7u\2\2\u00bd\u00be\7g\2\2\u00be"+
		"\b\3\2\2\2\u00bf\u00c0\7f\2\2\u00c0\u00c1\7u\2\2\u00c1\u00c2\7n\2\2\u00c2"+
		"\n\3\2\2\2\u00c3\u00c4\7x\2\2\u00c4\u00c5\7c\2\2\u00c5\u00c6\7t\2\2\u00c6"+
		"\f\3\2\2\2\u00c7\u00c8\7c\2\2\u00c8\u00c9\7u\2\2\u00c9\16\3\2\2\2\u00ca"+
		"\u00cb\7j\2\2\u00cb\u00cc\7c\2\2\u00cc\u00cd\7u\2\2\u00cd\20\3\2\2\2\u00ce"+
		"\u00cf\7q\2\2\u00cf\u00d0\7p\2\2\u00d0\22\3\2\2\2\u00d1\u00d2\7k\2\2\u00d2"+
		"\u00d3\7u\2\2\u00d3\24\3\2\2\2\u00d4\u00d5\7k\2\2\u00d5\u00d6\7p\2\2\u00d6"+
		"\u00d7\7v\2\2\u00d7\u00d8\7q\2\2\u00d8\26\3\2\2\2\u00d9\u00da\7y\2\2\u00da"+
		"\u00db\7k\2\2\u00db\u00dc\7v\2\2\u00dc\u00dd\7j\2\2\u00dd\30\3\2\2\2\u00de"+
		"\u00df\7g\2\2\u00df\u00e0\7z\2\2\u00e0\u00e1\7v\2\2\u00e1\u00e2\7g\2\2"+
		"\u00e2\u00e3\7p\2\2\u00e3\u00e4\7f\2\2\u00e4\u00e5\7u\2\2\u00e5\32\3\2"+
		"\2\2\u00e6\u00e7\7c\2\2\u00e7\u00e8\7d\2\2\u00e8\u00e9\7u\2\2\u00e9\u00ea"+
		"\7v\2\2\u00ea\u00eb\7t\2\2\u00eb\u00ec\7c\2\2\u00ec\u00ed\7e\2\2\u00ed"+
		"\u00ee\7v\2\2\u00ee\34\3\2\2\2\u00ef\u00f0\7u\2\2\u00f0\u00f1\7k\2\2\u00f1"+
		"\u00f2\7p\2\2\u00f2\u00f3\7i\2\2\u00f3\u00f4\7n\2\2\u00f4\u00f5\7g\2\2"+
		"\u00f5\36\3\2\2\2\u00f6\u00f7\7o\2\2\u00f7\u00f8\7w\2\2\u00f8\u00f9\7"+
		"n\2\2\u00f9\u00fa\7v\2\2\u00fa\u00fb\7k\2\2\u00fb\u00fc\7r\2\2\u00fc\u00fd"+
		"\7n\2\2\u00fd\u00fe\7g\2\2\u00fe \3\2\2\2\u00ff\u0100\7q\2\2\u0100\u0101"+
		"\7r\2\2\u0101\u0102\7v\2\2\u0102\u0103\7k\2\2\u0103\u0104\7q\2\2\u0104"+
		"\u0105\7p\2\2\u0105\u0106\7c\2\2\u0106\u0107\7n\2\2\u0107\"\3\2\2\2\u0108"+
		"\u0109\7t\2\2\u0109\u010a\7g\2\2\u010a\u010b\7s\2\2\u010b\u010c\7w\2\2"+
		"\u010c\u010d\7k\2\2\u010d\u010e\7t\2\2\u010e\u010f\7g\2\2\u010f\u0110"+
		"\7f\2\2\u0110$\3\2\2\2\u0111\u0112\7v\2\2\u0112\u0113\7g\2\2\u0113\u0114"+
		"\7t\2\2\u0114\u0115\7o\2\2\u0115\u0116\7k\2\2\u0116\u0117\7p\2\2\u0117"+
		"\u0118\7c\2\2\u0118\u0119\7n\2\2\u0119&\3\2\2\2\u011a\u011b\7r\2\2\u011b"+
		"\u011c\7t\2\2\u011c\u011d\7q\2\2\u011d\u011e\7r\2\2\u011e\u011f\7g\2\2"+
		"\u011f\u0120\7t\2\2\u0120\u0121\7v\2\2\u0121\u0122\7{\2\2\u0122(\3\2\2"+
		"\2\u0123\u0124\7h\2\2\u0124\u0125\7g\2\2\u0125\u0126\7c\2\2\u0126\u0127"+
		"\7v\2\2\u0127\u0128\7w\2\2\u0128\u0129\7t\2\2\u0129\u012a\7g\2\2\u012a"+
		"*\3\2\2\2\u012b\u012c\7c\2\2\u012c\u012d\7i\2\2\u012d\u012e\7i\2\2\u012e"+
		"\u012f\7t\2\2\u012f\u0130\7g\2\2\u0130\u0131\7i\2\2\u0131\u0132\7c\2\2"+
		"\u0132\u0133\7v\2\2\u0133\u0134\7g\2\2\u0134\u0135\7f\2\2\u0135,\3\2\2"+
		"\2\u0136\u0137\7c\2\2\u0137\u0138\7u\2\2\u0138\u0139\7u\2\2\u0139\u013a"+
		"\7q\2\2\u013a\u013b\7e\2\2\u013b\u013c\7k\2\2\u013c\u013d\7c\2\2\u013d"+
		"\u013e\7v\2\2\u013e\u013f\7g\2\2\u013f\u0140\7f\2\2\u0140.\3\2\2\2\u0141"+
		"\u0142\7e\2\2\u0142\u0143\7q\2\2\u0143\u0144\7o\2\2\u0144\u0145\7r\2\2"+
		"\u0145\u0146\7q\2\2\u0146\u0147\7p\2\2\u0147\u0148\7g\2\2\u0148\u0149"+
		"\7p\2\2\u0149\u014a\7v\2\2\u014a\60\3\2\2\2\u014b\u014c\7t\2\2\u014c\u014d"+
		"\7g\2\2\u014d\u014e\7c\2\2\u014e\u014f\7f\2\2\u014f\u0150\7q\2\2\u0150"+
		"\u0151\7p\2\2\u0151\u0152\7n\2\2\u0152\u0153\7{\2\2\u0153\62\3\2\2\2\u0154"+
		"\u0155\7g\2\2\u0155\u0156\7p\2\2\u0156\u0157\7e\2\2\u0157\u0158\7n\2\2"+
		"\u0158\u0159\7q\2\2\u0159\u015a\7u\2\2\u015a\u015b\7g\2\2\u015b\u015c"+
		"\7f\2\2\u015c\64\3\2\2\2\u015d\u015e\7h\2\2\u015e\u015f\7c\2\2\u015f\u0160"+
		"\7e\2\2\u0160\u0161\7g\2\2\u0161\u0162\7v\2\2\u0162\66\3\2\2\2\u0163\u0164"+
		"\7c\2\2\u0164\u0165\7f\2\2\u0165\u0166\7f\2\2\u0166\u0167\7t\2\2\u0167"+
		"\u0168\7g\2\2\u0168\u0169\7u\2\2\u0169\u016a\7u\2\2\u016a\u016b\7g\2\2"+
		"\u016b\u016c\7f\2\2\u016c8\3\2\2\2\u016d\u016e\7*\2\2\u016e:\3\2\2\2\u016f"+
		"\u0170\7+\2\2\u0170<\3\2\2\2\u0171\u0172\7]\2\2\u0172>\3\2\2\2\u0173\u0174"+
		"\7_\2\2\u0174@\3\2\2\2\u0175\u0176\7\60\2\2\u0176\u0177\7\60\2\2\u0177"+
		"\u0178\7\60\2\2\u0178B\3\2\2\2\u0179\u017a\7@\2\2\u017a\u017b\b\"\2\2"+
		"\u017bD\3\2\2\2\u017c\u017d\7>\2\2\u017dF\3\2\2\2\u017e\u017f\7(\2\2\u017f"+
		"H\3\2\2\2\u0180\u0181\7<\2\2\u0181J\3\2\2\2\u0182\u0183\7.\2\2\u0183L"+
		"\3\2\2\2\u0184\u0185\7\60\2\2\u0185N\3\2\2\2\u0186\u0187\7?\2\2\u0187"+
		"P\3\2\2\2\u0188\u0189\7$\2\2\u0189R\3\2\2\2\u018a\u018c\7=\2\2\u018b\u018a"+
		"\3\2\2\2\u018c\u018d\3\2\2\2\u018d\u018b\3\2\2\2\u018d\u018e\3\2\2\2\u018e"+
		"\u018f\3\2\2\2\u018f\u0190\b*\3\2\u0190T\3\2\2\2\u0191\u0192\7,\2\2\u0192"+
		"V\3\2\2\2\u0193\u0194\7-\2\2\u0194X\3\2\2\2\u0195\u0196\7y\2\2\u0196\u0197"+
		"\7q\2\2\u0197\u0198\7t\2\2\u0198\u0199\7f\2\2\u0199Z\3\2\2\2\u019a\u019b"+
		"\7h\2\2\u019b\u019c\7k\2\2\u019c\u019d\7n\2\2\u019d\u019e\7g\2\2\u019e"+
		"\\\3\2\2\2\u019f\u01a0\7k\2\2\u01a0\u01a1\7p\2\2\u01a1\u01a2\7v\2\2\u01a2"+
		"\u01a3\7g\2\2\u01a3\u01a4\7i\2\2\u01a4\u01a5\7g\2\2\u01a5\u01a6\7t\2\2"+
		"\u01a6^\3\2\2\2\u01a7\u01a8\7p\2\2\u01a8\u01a9\7c\2\2\u01a9\u01aa\7v\2"+
		"\2\u01aa\u01ab\7w\2\2\u01ab\u01ac\7t\2\2\u01ac\u01ad\7c\2\2\u01ad\u01ae"+
		"\7n\2\2\u01ae`\3\2\2\2\u01af\u01b0\7p\2\2\u01b0\u01b1\7c\2\2\u01b1\u01b2"+
		"\7v\2\2\u01b2\u01b3\7k\2\2\u01b3\u01b4\7x\2\2\u01b4\u01b5\7g\2\2\u01b5"+
		"b\3\2\2\2\u01b6\u01b7\7f\2\2\u01b7\u01b8\7q\2\2\u01b8\u01b9\7w\2\2\u01b9"+
		"\u01ba\7d\2\2\u01ba\u01bb\7n\2\2\u01bb\u01bc\7g\2\2\u01bcd\3\2\2\2\u01bd"+
		"\u01be\7u\2\2\u01be\u01bf\7v\2\2\u01bf\u01c0\7t\2\2\u01c0\u01c1\7k\2\2"+
		"\u01c1\u01c2\7p\2\2\u01c2\u01c3\7i\2\2\u01c3f\3\2\2\2\u01c4\u01c5\7d\2"+
		"\2\u01c5\u01c6\7q\2\2\u01c6\u01c7\7q\2\2\u01c7\u01c8\7n\2\2\u01c8\u01c9"+
		"\7g\2\2\u01c9\u01ca\7c\2\2\u01ca\u01cb\7p\2\2\u01cbh\3\2\2\2\u01cc\u01cd"+
		"\7o\2\2\u01cd\u01ce\7g\2\2\u01ce\u01cf\7c\2\2\u01cf\u01d0\7u\2\2\u01d0"+
		"\u01d1\7w\2\2\u01d1\u01d2\7t\2\2\u01d2\u01d3\7g\2\2\u01d3j\3\2\2\2\u01d4"+
		"\u01d5\7t\2\2\u01d5\u01d6\7c\2\2\u01d6\u01d7\7v\2\2\u01d7\u01d8\7k\2\2"+
		"\u01d8\u01d9\7q\2\2\u01d9l\3\2\2\2\u01da\u01db\7f\2\2\u01db\u01dc\7c\2"+
		"\2\u01dc\u01dd\7v\2\2\u01dd\u01de\7g\2\2\u01den\3\2\2\2\u01df\u01e0\7"+
		"g\2\2\u01e0\u01e1\7o\2\2\u01e1\u01e2\7r\2\2\u01e2\u01e3\7v\2\2\u01e3\u01e4"+
		"\7{\2\2\u01e4p\3\2\2\2\u01e5\u01e6\7\61\2\2\u01e6\u01e7\7,\2\2\u01e7\u01eb"+
		"\3\2\2\2\u01e8\u01ea\13\2\2\2\u01e9\u01e8\3\2\2\2\u01ea\u01ed\3\2\2\2"+
		"\u01eb\u01ec\3\2\2\2\u01eb\u01e9\3\2\2\2\u01ec\u01ee\3\2\2\2\u01ed\u01eb"+
		"\3\2\2\2\u01ee\u01ef\7,\2\2\u01ef\u01f0\7\61\2\2\u01f0\u01f1\3\2\2\2\u01f1"+
		"\u01f2\b9\4\2\u01f2r\3\2\2\2\u01f3\u01f4\7\61\2\2\u01f4\u01f5\7\61\2\2"+
		"\u01f5\u01f9\3\2\2\2\u01f6\u01f8\n\2\2\2\u01f7\u01f6\3\2\2\2\u01f8\u01fb"+
		"\3\2\2\2\u01f9\u01f7\3\2\2\2\u01f9\u01fa\3\2\2\2\u01fa\u01fc\3\2\2\2\u01fb"+
		"\u01f9\3\2\2\2\u01fc\u01fd\b:\4\2\u01fdt\3\2\2\2\u01fe\u0201\7G\2\2\u01ff"+
		"\u0202\5W,\2\u0200\u0202\5\u00a5S\2\u0201\u01ff\3\2\2\2\u0201\u0200\3"+
		"\2\2\2\u0201\u0202\3\2\2\2\u0202\u0204\3\2\2\2\u0203\u0205\5\u00abV\2"+
		"\u0204\u0203\3\2\2\2\u0205\u0206\3\2\2\2\u0206\u0204\3\2\2\2\u0206\u0207"+
		"\3\2\2\2\u0207v\3\2\2\2\u0208\u0209\7v\2\2\u0209\u020a\7t\2\2\u020a\u020b"+
		"\7w\2\2\u020b\u0212\7g\2\2\u020c\u020d\7h\2\2\u020d\u020e\7c\2\2\u020e"+
		"\u020f\7n\2\2\u020f\u0210\7u\2\2\u0210\u0212\7g\2\2\u0211\u0208\3\2\2"+
		"\2\u0211\u020c\3\2\2\2\u0212x\3\2\2\2\u0213\u0215\5W,\2\u0214\u0213\3"+
		"\2\2\2\u0214\u0215\3\2\2\2\u0215\u0217\3\2\2\2\u0216\u0218\5\u00abV\2"+
		"\u0217\u0216\3\2\2\2\u0218\u0219\3\2\2\2\u0219\u0217\3\2\2\2\u0219\u021a"+
		"\3\2\2\2\u021az\3\2\2\2\u021b\u021d\5\u00a5S\2\u021c\u021e\5\u00abV\2"+
		"\u021d\u021c\3\2\2\2\u021e\u021f\3\2\2\2\u021f\u021d\3\2\2\2\u021f\u0220"+
		"\3\2\2\2\u0220|\3\2\2\2\u0221\u0224\5W,\2\u0222\u0224\5\u00a5S\2\u0223"+
		"\u0221\3\2\2\2\u0223\u0222\3\2\2\2\u0223\u0224\3\2\2\2\u0224\u0226\3\2"+
		"\2\2\u0225\u0227\5\u00abV\2\u0226\u0225\3\2\2\2\u0227\u0228\3\2\2\2\u0228"+
		"\u0226\3\2\2\2\u0228\u0229\3\2\2\2\u0229\u022a\3\2\2\2\u022a\u022c\5M"+
		"\'\2\u022b\u022d\5\u00abV\2\u022c\u022b\3\2\2\2\u022d\u022e\3\2\2\2\u022e"+
		"\u022c\3\2\2\2\u022e\u022f\3\2\2\2\u022f~\3\2\2\2\u0230\u0234\5Q)\2\u0231"+
		"\u0233\n\3\2\2\u0232\u0231\3\2\2\2\u0233\u0236\3\2\2\2\u0234\u0232\3\2"+
		"\2\2\u0234\u0235\3\2\2\2\u0235\u0237\3\2\2\2\u0236\u0234\3\2\2\2\u0237"+
		"\u0238\5Q)\2\u0238\u0080\3\2\2\2\u0239\u023d\5\u00a7T\2\u023a\u023c\n"+
		"\4\2\2\u023b\u023a\3\2\2\2\u023c\u023f\3\2\2\2\u023d\u023b\3\2\2\2\u023d"+
		"\u023e\3\2\2\2\u023e\u0240\3\2\2\2\u023f\u023d\3\2\2\2\u0240\u0241\5\u00a7"+
		"T\2\u0241\u0082\3\2\2\2\u0242\u0243\5G$\2\u0243\u0244\5\u00abV\2\u0244"+
		"\u0245\5\u00abV\2\u0245\u024b\5\u00abV\2\u0246\u0247\5M\'\2\u0247\u0248"+
		"\5\u00abV\2\u0248\u0249\5\u00abV\2\u0249\u024a\5\u00abV\2\u024a\u024c"+
		"\3\2\2\2\u024b\u0246\3\2\2\2\u024c\u024d\3\2\2\2\u024d\u024b\3\2\2\2\u024d"+
		"\u024e\3\2\2\2\u024e\u0084\3\2\2\2\u024f\u0256\5\u00adW\2\u0250\u0255"+
		"\5\u00abV\2\u0251\u0255\5\u00adW\2\u0252\u0255\5\u00a5S\2\u0253\u0255"+
		"\5\u00a9U\2\u0254\u0250\3\2\2\2\u0254\u0251\3\2\2\2\u0254\u0252\3\2\2"+
		"\2\u0254\u0253\3\2\2\2\u0255\u0258\3\2\2\2\u0256\u0254\3\2\2\2\u0256\u0257"+
		"\3\2\2\2\u0257\u0086\3\2\2\2\u0258\u0256\3\2\2\2\u0259\u025f\5\u00adW"+
		"\2\u025a\u025f\5\u009dO\2\u025b\u025f\5\u0099M\2\u025c\u025f\5\u009bN"+
		"\2\u025d\u025f\5\u009fP\2\u025e\u0259\3\2\2\2\u025e\u025a\3\2\2\2\u025e"+
		"\u025b\3\2\2\2\u025e\u025c\3\2\2\2\u025e\u025d\3\2\2\2\u025f\u026b\3\2"+
		"\2\2\u0260\u026a\5\u00a9U\2\u0261\u026a\5\u00a1Q\2\u0262\u026a\5\u00a3"+
		"R\2\u0263\u026a\5\u009dO\2\u0264\u026a\5\u0099M\2\u0265\u026a\5\u009b"+
		"N\2\u0266\u026a\5\u009fP\2\u0267\u026a\5\u00adW\2\u0268\u026a\5\u00ab"+
		"V\2\u0269\u0260\3\2\2\2\u0269\u0261\3\2\2\2\u0269\u0262\3\2\2\2\u0269"+
		"\u0263\3\2\2\2\u0269\u0264\3\2\2\2\u0269\u0265\3\2\2\2\u0269\u0266\3\2"+
		"\2\2\u0269\u0267\3\2\2\2\u0269\u0268\3\2\2\2\u026a\u026d\3\2\2\2\u026b"+
		"\u0269\3\2\2\2\u026b\u026c\3\2\2\2\u026c\u0088\3\2\2\2\u026d\u026b\3\2"+
		"\2\2\u026e\u0270\5\u0091I\2\u026f\u026e\3\2\2\2\u0270\u0271\3\2\2\2\u0271"+
		"\u026f\3\2\2\2\u0271\u0272\3\2\2\2\u0272\u0276\3\2\2\2\u0273\u0275\5\u008f"+
		"H\2\u0274\u0273\3\2\2\2\u0275\u0278\3\2\2\2\u0276\u0274\3\2\2\2\u0276"+
		"\u0277\3\2\2\2\u0277\u0279\3\2\2\2\u0278\u0276\3\2\2\2\u0279\u027a\bE"+
		"\5\2\u027a\u008a\3\2\2\2\u027b\u027d\5\u008fH\2\u027c\u027b\3\2\2\2\u027d"+
		"\u027e\3\2\2\2\u027e\u027c\3\2\2\2\u027e\u027f\3\2\2\2\u027f\u0281\3\2"+
		"\2\2\u0280\u0282\7\2\2\3\u0281\u0280\3\2\2\2\u0281\u0282\3\2\2\2\u0282"+
		"\u0283\3\2\2\2\u0283\u0284\bF\4\2\u0284\u008c\3\2\2\2\u0285\u0286\7f\2"+
		"\2\u0286\u0287\7q\2\2\u0287\u0288\7e\2\2\u0288\u028c\3\2\2\2\u0289\u028b"+
		"\13\2\2\2\u028a\u0289\3\2\2\2\u028b\u028e\3\2\2\2\u028c\u028d\3\2\2\2"+
		"\u028c\u028a\3\2\2\2\u028d\u028f\3\2\2\2\u028e\u028c\3\2\2\2\u028f\u0290"+
		"\5\u0091I\2\u0290\u0291\bG\6\2\u0291\u008e\3\2\2\2\u0292\u0293\t\5\2\2"+
		"\u0293\u0090\3\2\2\2\u0294\u0296\7\17\2\2\u0295\u0294\3\2\2\2\u0295\u0296"+
		"\3\2\2\2\u0296\u0297\3\2\2\2\u0297\u029a\7\f\2\2\u0298\u029a\7\17\2\2"+
		"\u0299\u0295\3\2\2\2\u0299\u0298\3\2\2\2\u029a\u0092\3\2\2\2\u029b\u029c"+
		"\7k\2\2\u029c\u029d\7p\2\2\u029d\u029e\7f\2\2\u029e\u029f\7g\2\2\u029f"+
		"\u02a0\7p\2\2\u02a0\u02a1\7v\2\2\u02a1\u0094\3\2\2\2\u02a2\u02a3\7f\2"+
		"\2\u02a3\u02a4\7g\2\2\u02a4\u02a5\7f\2\2\u02a5\u02a6\7g\2\2\u02a6\u02a7"+
		"\7p\2\2\u02a7\u02a8\7v\2\2\u02a8\u0096\3\2\2\2\u02a9\u02aa\13\2\2\2\u02aa"+
		"\u0098\3\2\2\2\u02ab\u02ac\7&\2\2\u02ac\u009a\3\2\2\2\u02ad\u02ae\7\u20ae"+
		"\2\2\u02ae\u009c\3\2\2\2\u02af\u02b0\7\'\2\2\u02b0\u009e\3\2\2\2\u02b1"+
		"\u02b2\t\6\2\2\u02b2\u00a0\3\2\2\2\u02b3\u02b4\7\u00b9\2\2\u02b4\u00a2"+
		"\3\2\2\2\u02b5\u02b6\7\61\2\2\u02b6\u00a4\3\2\2\2\u02b7\u02b8\7/\2\2\u02b8"+
		"\u00a6\3\2\2\2\u02b9\u02bb\5\u00a5S\2\u02ba\u02bc\5\u00a5S\2\u02bb\u02ba"+
		"\3\2\2\2\u02bc\u02bd\3\2\2\2\u02bd\u02bb\3\2\2\2\u02bd\u02be\3\2\2\2\u02be"+
		"\u00a8\3\2\2\2\u02bf\u02c0\7a\2\2\u02c0\u00aa\3\2\2\2\u02c1\u02c2\t\7"+
		"\2\2\u02c2\u00ac\3\2\2\2\u02c3\u02c4\t\b\2\2\u02c4\u00ae\3\2\2\2\37\2"+
		"\u018d\u01eb\u01f9\u0201\u0206\u0211\u0214\u0219\u021f\u0223\u0228\u022e"+
		"\u0234\u023d\u024d\u0254\u0256\u025e\u0269\u026b\u0271\u0276\u027e\u0281"+
		"\u028c\u0295\u0299\u02bd\7\3\"\2\3*\3\2\3\2\3E\4\3G\5";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}