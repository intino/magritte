// Generated from /Users/oroncal/workspace/tara/rt/src/siani/tara/compiler/parser/antlr/TaraLexer.g4 by ANTLR 4.5
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
	static { RuntimeMetaData.checkVersion("4.5", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		METAIDENTIFIER=1, SUB=2, USE=3, DSL=4, VAR=5, AS=6, HAS=7, ON=8, IS=9, 
		INTO=10, WITH=11, EXTENDS=12, ABSTRACT=13, SINGLE=14, MULTIPLE=15, REQUIRED=16, 
		TERMINAL=17, PROPERTY=18, FEATURE=19, READONLY=20, ENCLOSED=21, FACET=22, 
		LEFT_PARENTHESIS=23, RIGHT_PARENTHESIS=24, LEFT_SQUARE=25, RIGHT_SQUARE=26, 
		LIST=27, INLINE=28, CLOSE_INLINE=29, HASHTAG=30, COLON=31, COMMA=32, DOT=33, 
		EQUALS=34, APHOSTROPHE=35, SEMICOLON=36, STAR=37, PLUS=38, WORD=39, RESOURCE=40, 
		INT_TYPE=41, NATURAL_TYPE=42, NATIVE_TYPE=43, DOUBLE_TYPE=44, STRING_TYPE=45, 
		BOOLEAN_TYPE=46, MEASURE_TYPE=47, RATIO_TYPE=48, DATE_TYPE=49, EMPTY=50, 
		BLOCK_COMMENT=51, LINE_COMMENT=52, SCIENCE_NOT=53, BOOLEAN_VALUE=54, NATURAL_VALUE=55, 
		NEGATIVE_VALUE=56, DOUBLE_VALUE=57, STRING_VALUE=58, STRING_MULTILINE_VALUE_KEY=59, 
		ADDRESS_VALUE=60, IDENTIFIER=61, MEASURE_VALUE=62, NEWLINE=63, SPACES=64, 
		DOC=65, SP=66, NL=67, NEW_LINE_INDENT=68, DEDENT=69, UNKNOWN_TOKEN=70;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"METAIDENTIFIER", "SUB", "USE", "DSL", "VAR", "AS", "HAS", "ON", "IS", 
		"INTO", "WITH", "EXTENDS", "ABSTRACT", "SINGLE", "MULTIPLE", "REQUIRED", 
		"TERMINAL", "PROPERTY", "FEATURE", "READONLY", "ENCLOSED", "FACET", "LEFT_PARENTHESIS", 
		"RIGHT_PARENTHESIS", "LEFT_SQUARE", "RIGHT_SQUARE", "LIST", "INLINE", 
		"CLOSE_INLINE", "HASHTAG", "COLON", "COMMA", "DOT", "EQUALS", "APHOSTROPHE", 
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
		"'multiple'", "'required'", "'terminal'", "'property'", "'feature'", "'readonly'", 
		"'enclosed'", "'facet'", "'('", "')'", "'['", "']'", "'...'", "'>'", "'<'", 
		"'#'", "':'", "','", "'.'", "'='", "'\"'", null, "'*'", "'+'", "'word'", 
		"'file'", "'integer'", "'natural'", "'native'", "'double'", "'string'", 
		"'boolean'", "'measure'", "'ratio'", "'date'", "'empty'", null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, "'indent'", "'dedent'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "METAIDENTIFIER", "SUB", "USE", "DSL", "VAR", "AS", "HAS", "ON", 
		"IS", "INTO", "WITH", "EXTENDS", "ABSTRACT", "SINGLE", "MULTIPLE", "REQUIRED", 
		"TERMINAL", "PROPERTY", "FEATURE", "READONLY", "ENCLOSED", "FACET", "LEFT_PARENTHESIS", 
		"RIGHT_PARENTHESIS", "LEFT_SQUARE", "RIGHT_SQUARE", "LIST", "INLINE", 
		"CLOSE_INLINE", "HASHTAG", "COLON", "COMMA", "DOT", "EQUALS", "APHOSTROPHE", 
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
	    public void reset() {
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
		case 27: 
			INLINE_action((RuleContext)_localctx, actionIndex); 
			break;

		case 35: 
			SEMICOLON_action((RuleContext)_localctx, actionIndex); 
			break;

		case 62: 
			NEWLINE_action((RuleContext)_localctx, actionIndex); 
			break;

		case 64: 
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2H\u0283\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
		"\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\4O\tO\4P\tP\4Q\tQ\4R\tR\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3"+
		"\6\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\13"+
		"\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3"+
		"\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3"+
		"\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3"+
		"\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3"+
		"\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3"+
		"\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3"+
		"\27\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\34\3\34\3\35\3"+
		"\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3#\3#\3$\3$\3%\6%\u014f"+
		"\n%\r%\16%\u0150\3%\3%\3&\3&\3\'\3\'\3(\3(\3(\3(\3(\3)\3)\3)\3)\3)\3*"+
		"\3*\3*\3*\3*\3*\3*\3*\3+\3+\3+\3+\3+\3+\3+\3+\3,\3,\3,\3,\3,\3,\3,\3-"+
		"\3-\3-\3-\3-\3-\3-\3.\3.\3.\3.\3.\3.\3.\3/\3/\3/\3/\3/\3/\3/\3/\3\60\3"+
		"\60\3\60\3\60\3\60\3\60\3\60\3\60\3\61\3\61\3\61\3\61\3\61\3\61\3\62\3"+
		"\62\3\62\3\62\3\62\3\63\3\63\3\63\3\63\3\63\3\63\3\64\3\64\3\64\3\64\7"+
		"\64\u01ad\n\64\f\64\16\64\u01b0\13\64\3\64\3\64\3\64\3\64\3\64\3\65\3"+
		"\65\3\65\3\65\7\65\u01bb\n\65\f\65\16\65\u01be\13\65\3\65\3\65\3\66\3"+
		"\66\3\66\5\66\u01c5\n\66\3\66\6\66\u01c8\n\66\r\66\16\66\u01c9\3\67\3"+
		"\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\5\67\u01d5\n\67\38\58\u01d8\n8"+
		"\38\68\u01db\n8\r8\168\u01dc\39\39\69\u01e1\n9\r9\169\u01e2\3:\3:\5:\u01e7"+
		"\n:\3:\6:\u01ea\n:\r:\16:\u01eb\3:\3:\6:\u01f0\n:\r:\16:\u01f1\3;\3;\3"+
		";\3;\7;\u01f8\n;\f;\16;\u01fb\13;\3;\3;\3<\3<\7<\u0201\n<\f<\16<\u0204"+
		"\13<\3<\3<\3=\3=\6=\u020a\n=\r=\16=\u020b\3>\3>\3>\3>\3>\7>\u0213\n>\f"+
		">\16>\u0216\13>\3?\3?\3?\3?\3?\5?\u021d\n?\3?\3?\3?\3?\3?\3?\3?\3?\3?"+
		"\7?\u0228\n?\f?\16?\u022b\13?\3@\6@\u022e\n@\r@\16@\u022f\3@\7@\u0233"+
		"\n@\f@\16@\u0236\13@\3@\3@\3A\6A\u023b\nA\rA\16A\u023c\3A\5A\u0240\nA"+
		"\3A\3A\3B\3B\3B\3B\3B\7B\u0249\nB\fB\16B\u024c\13B\3B\3B\3B\3C\3C\3D\5"+
		"D\u0254\nD\3D\3D\5D\u0258\nD\3E\3E\3E\3E\3E\3E\3E\3F\3F\3F\3F\3F\3F\3"+
		"F\3G\3G\3H\3H\3I\3I\3J\3J\3K\3K\3L\3L\3M\3M\3N\3N\3O\3O\6O\u027a\nO\r"+
		"O\16O\u027b\3P\3P\3Q\3Q\3R\3R\4\u01ae\u024a\2S\3\3\5\4\7\5\t\6\13\7\r"+
		"\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25"+
		")\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O"+
		")Q*S+U,W-Y.[/]\60_\61a\62c\63e\64g\65i\66k\67m8o9q:s;u<w=y>{?}@\177A\u0081"+
		"B\u0083C\u0085D\u0087E\u0089F\u008bG\u008dH\u008f\2\u0091\2\u0093\2\u0095"+
		"\2\u0097\2\u0099\2\u009b\2\u009d\2\u009f\2\u00a1\2\u00a3\2\3\2\n\4\2\f"+
		"\f\17\17\6\2\f\f\17\17$$^^\4\2$$^^\3\2//\4\2\13\13\"\"\4\2\u00b2\u00b2"+
		"\u00bc\u00bc\3\2\62;\6\2C\\c|\u00d3\u00d3\u00f3\u00f3\u02a2\2\3\3\2\2"+
		"\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3"+
		"\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2"+
		"\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2"+
		"\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2"+
		"\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3"+
		"\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2"+
		"\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2"+
		"W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3"+
		"\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2"+
		"\2\2q\3\2\2\2\2s\3\2\2\2\2u\3\2\2\2\2w\3\2\2\2\2y\3\2\2\2\2{\3\2\2\2\2"+
		"}\3\2\2\2\2\177\3\2\2\2\2\u0081\3\2\2\2\2\u0083\3\2\2\2\2\u0085\3\2\2"+
		"\2\2\u0087\3\2\2\2\2\u0089\3\2\2\2\2\u008b\3\2\2\2\2\u008d\3\2\2\2\3\u00a5"+
		"\3\2\2\2\5\u00ad\3\2\2\2\7\u00b1\3\2\2\2\t\u00b5\3\2\2\2\13\u00b9\3\2"+
		"\2\2\r\u00bd\3\2\2\2\17\u00c0\3\2\2\2\21\u00c4\3\2\2\2\23\u00c7\3\2\2"+
		"\2\25\u00ca\3\2\2\2\27\u00cf\3\2\2\2\31\u00d4\3\2\2\2\33\u00dc\3\2\2\2"+
		"\35\u00e5\3\2\2\2\37\u00ec\3\2\2\2!\u00f5\3\2\2\2#\u00fe\3\2\2\2%\u0107"+
		"\3\2\2\2\'\u0110\3\2\2\2)\u0118\3\2\2\2+\u0121\3\2\2\2-\u012a\3\2\2\2"+
		"/\u0130\3\2\2\2\61\u0132\3\2\2\2\63\u0134\3\2\2\2\65\u0136\3\2\2\2\67"+
		"\u0138\3\2\2\29\u013c\3\2\2\2;\u013f\3\2\2\2=\u0141\3\2\2\2?\u0143\3\2"+
		"\2\2A\u0145\3\2\2\2C\u0147\3\2\2\2E\u0149\3\2\2\2G\u014b\3\2\2\2I\u014e"+
		"\3\2\2\2K\u0154\3\2\2\2M\u0156\3\2\2\2O\u0158\3\2\2\2Q\u015d\3\2\2\2S"+
		"\u0162\3\2\2\2U\u016a\3\2\2\2W\u0172\3\2\2\2Y\u0179\3\2\2\2[\u0180\3\2"+
		"\2\2]\u0187\3\2\2\2_\u018f\3\2\2\2a\u0197\3\2\2\2c\u019d\3\2\2\2e\u01a2"+
		"\3\2\2\2g\u01a8\3\2\2\2i\u01b6\3\2\2\2k\u01c1\3\2\2\2m\u01d4\3\2\2\2o"+
		"\u01d7\3\2\2\2q\u01de\3\2\2\2s\u01e6\3\2\2\2u\u01f3\3\2\2\2w\u01fe\3\2"+
		"\2\2y\u0207\3\2\2\2{\u020d\3\2\2\2}\u021c\3\2\2\2\177\u022d\3\2\2\2\u0081"+
		"\u023a\3\2\2\2\u0083\u0243\3\2\2\2\u0085\u0250\3\2\2\2\u0087\u0257\3\2"+
		"\2\2\u0089\u0259\3\2\2\2\u008b\u0260\3\2\2\2\u008d\u0267\3\2\2\2\u008f"+
		"\u0269\3\2\2\2\u0091\u026b\3\2\2\2\u0093\u026d\3\2\2\2\u0095\u026f\3\2"+
		"\2\2\u0097\u0271\3\2\2\2\u0099\u0273\3\2\2\2\u009b\u0275\3\2\2\2\u009d"+
		"\u0277\3\2\2\2\u009f\u027d\3\2\2\2\u00a1\u027f\3\2\2\2\u00a3\u0281\3\2"+
		"\2\2\u00a5\u00a6\7E\2\2\u00a6\u00a7\7q\2\2\u00a7\u00a8\7p\2\2\u00a8\u00a9"+
		"\7e\2\2\u00a9\u00aa\7g\2\2\u00aa\u00ab\7r\2\2\u00ab\u00ac\7v\2\2\u00ac"+
		"\4\3\2\2\2\u00ad\u00ae\7u\2\2\u00ae\u00af\7w\2\2\u00af\u00b0\7d\2\2\u00b0"+
		"\6\3\2\2\2\u00b1\u00b2\7w\2\2\u00b2\u00b3\7u\2\2\u00b3\u00b4\7g\2\2\u00b4"+
		"\b\3\2\2\2\u00b5\u00b6\7f\2\2\u00b6\u00b7\7u\2\2\u00b7\u00b8\7n\2\2\u00b8"+
		"\n\3\2\2\2\u00b9\u00ba\7x\2\2\u00ba\u00bb\7c\2\2\u00bb\u00bc\7t\2\2\u00bc"+
		"\f\3\2\2\2\u00bd\u00be\7c\2\2\u00be\u00bf\7u\2\2\u00bf\16\3\2\2\2\u00c0"+
		"\u00c1\7j\2\2\u00c1\u00c2\7c\2\2\u00c2\u00c3\7u\2\2\u00c3\20\3\2\2\2\u00c4"+
		"\u00c5\7q\2\2\u00c5\u00c6\7p\2\2\u00c6\22\3\2\2\2\u00c7\u00c8\7k\2\2\u00c8"+
		"\u00c9\7u\2\2\u00c9\24\3\2\2\2\u00ca\u00cb\7k\2\2\u00cb\u00cc\7p\2\2\u00cc"+
		"\u00cd\7v\2\2\u00cd\u00ce\7q\2\2\u00ce\26\3\2\2\2\u00cf\u00d0\7y\2\2\u00d0"+
		"\u00d1\7k\2\2\u00d1\u00d2\7v\2\2\u00d2\u00d3\7j\2\2\u00d3\30\3\2\2\2\u00d4"+
		"\u00d5\7g\2\2\u00d5\u00d6\7z\2\2\u00d6\u00d7\7v\2\2\u00d7\u00d8\7g\2\2"+
		"\u00d8\u00d9\7p\2\2\u00d9\u00da\7f\2\2\u00da\u00db\7u\2\2\u00db\32\3\2"+
		"\2\2\u00dc\u00dd\7c\2\2\u00dd\u00de\7d\2\2\u00de\u00df\7u\2\2\u00df\u00e0"+
		"\7v\2\2\u00e0\u00e1\7t\2\2\u00e1\u00e2\7c\2\2\u00e2\u00e3\7e\2\2\u00e3"+
		"\u00e4\7v\2\2\u00e4\34\3\2\2\2\u00e5\u00e6\7u\2\2\u00e6\u00e7\7k\2\2\u00e7"+
		"\u00e8\7p\2\2\u00e8\u00e9\7i\2\2\u00e9\u00ea\7n\2\2\u00ea\u00eb\7g\2\2"+
		"\u00eb\36\3\2\2\2\u00ec\u00ed\7o\2\2\u00ed\u00ee\7w\2\2\u00ee\u00ef\7"+
		"n\2\2\u00ef\u00f0\7v\2\2\u00f0\u00f1\7k\2\2\u00f1\u00f2\7r\2\2\u00f2\u00f3"+
		"\7n\2\2\u00f3\u00f4\7g\2\2\u00f4 \3\2\2\2\u00f5\u00f6\7t\2\2\u00f6\u00f7"+
		"\7g\2\2\u00f7\u00f8\7s\2\2\u00f8\u00f9\7w\2\2\u00f9\u00fa\7k\2\2\u00fa"+
		"\u00fb\7t\2\2\u00fb\u00fc\7g\2\2\u00fc\u00fd\7f\2\2\u00fd\"\3\2\2\2\u00fe"+
		"\u00ff\7v\2\2\u00ff\u0100\7g\2\2\u0100\u0101\7t\2\2\u0101\u0102\7o\2\2"+
		"\u0102\u0103\7k\2\2\u0103\u0104\7p\2\2\u0104\u0105\7c\2\2\u0105\u0106"+
		"\7n\2\2\u0106$\3\2\2\2\u0107\u0108\7r\2\2\u0108\u0109\7t\2\2\u0109\u010a"+
		"\7q\2\2\u010a\u010b\7r\2\2\u010b\u010c\7g\2\2\u010c\u010d\7t\2\2\u010d"+
		"\u010e\7v\2\2\u010e\u010f\7{\2\2\u010f&\3\2\2\2\u0110\u0111\7h\2\2\u0111"+
		"\u0112\7g\2\2\u0112\u0113\7c\2\2\u0113\u0114\7v\2\2\u0114\u0115\7w\2\2"+
		"\u0115\u0116\7t\2\2\u0116\u0117\7g\2\2\u0117(\3\2\2\2\u0118\u0119\7t\2"+
		"\2\u0119\u011a\7g\2\2\u011a\u011b\7c\2\2\u011b\u011c\7f\2\2\u011c\u011d"+
		"\7q\2\2\u011d\u011e\7p\2\2\u011e\u011f\7n\2\2\u011f\u0120\7{\2\2\u0120"+
		"*\3\2\2\2\u0121\u0122\7g\2\2\u0122\u0123\7p\2\2\u0123\u0124\7e\2\2\u0124"+
		"\u0125\7n\2\2\u0125\u0126\7q\2\2\u0126\u0127\7u\2\2\u0127\u0128\7g\2\2"+
		"\u0128\u0129\7f\2\2\u0129,\3\2\2\2\u012a\u012b\7h\2\2\u012b\u012c\7c\2"+
		"\2\u012c\u012d\7e\2\2\u012d\u012e\7g\2\2\u012e\u012f\7v\2\2\u012f.\3\2"+
		"\2\2\u0130\u0131\7*\2\2\u0131\60\3\2\2\2\u0132\u0133\7+\2\2\u0133\62\3"+
		"\2\2\2\u0134\u0135\7]\2\2\u0135\64\3\2\2\2\u0136\u0137\7_\2\2\u0137\66"+
		"\3\2\2\2\u0138\u0139\7\60\2\2\u0139\u013a\7\60\2\2\u013a\u013b\7\60\2"+
		"\2\u013b8\3\2\2\2\u013c\u013d\7@\2\2\u013d\u013e\b\35\2\2\u013e:\3\2\2"+
		"\2\u013f\u0140\7>\2\2\u0140<\3\2\2\2\u0141\u0142\7%\2\2\u0142>\3\2\2\2"+
		"\u0143\u0144\7<\2\2\u0144@\3\2\2\2\u0145\u0146\7.\2\2\u0146B\3\2\2\2\u0147"+
		"\u0148\7\60\2\2\u0148D\3\2\2\2\u0149\u014a\7?\2\2\u014aF\3\2\2\2\u014b"+
		"\u014c\7$\2\2\u014cH\3\2\2\2\u014d\u014f\7=\2\2\u014e\u014d\3\2\2\2\u014f"+
		"\u0150\3\2\2\2\u0150\u014e\3\2\2\2\u0150\u0151\3\2\2\2\u0151\u0152\3\2"+
		"\2\2\u0152\u0153\b%\3\2\u0153J\3\2\2\2\u0154\u0155\7,\2\2\u0155L\3\2\2"+
		"\2\u0156\u0157\7-\2\2\u0157N\3\2\2\2\u0158\u0159\7y\2\2\u0159\u015a\7"+
		"q\2\2\u015a\u015b\7t\2\2\u015b\u015c\7f\2\2\u015cP\3\2\2\2\u015d\u015e"+
		"\7h\2\2\u015e\u015f\7k\2\2\u015f\u0160\7n\2\2\u0160\u0161\7g\2\2\u0161"+
		"R\3\2\2\2\u0162\u0163\7k\2\2\u0163\u0164\7p\2\2\u0164\u0165\7v\2\2\u0165"+
		"\u0166\7g\2\2\u0166\u0167\7i\2\2\u0167\u0168\7g\2\2\u0168\u0169\7t\2\2"+
		"\u0169T\3\2\2\2\u016a\u016b\7p\2\2\u016b\u016c\7c\2\2\u016c\u016d\7v\2"+
		"\2\u016d\u016e\7w\2\2\u016e\u016f\7t\2\2\u016f\u0170\7c\2\2\u0170\u0171"+
		"\7n\2\2\u0171V\3\2\2\2\u0172\u0173\7p\2\2\u0173\u0174\7c\2\2\u0174\u0175"+
		"\7v\2\2\u0175\u0176\7k\2\2\u0176\u0177\7x\2\2\u0177\u0178\7g\2\2\u0178"+
		"X\3\2\2\2\u0179\u017a\7f\2\2\u017a\u017b\7q\2\2\u017b\u017c\7w\2\2\u017c"+
		"\u017d\7d\2\2\u017d\u017e\7n\2\2\u017e\u017f\7g\2\2\u017fZ\3\2\2\2\u0180"+
		"\u0181\7u\2\2\u0181\u0182\7v\2\2\u0182\u0183\7t\2\2\u0183\u0184\7k\2\2"+
		"\u0184\u0185\7p\2\2\u0185\u0186\7i\2\2\u0186\\\3\2\2\2\u0187\u0188\7d"+
		"\2\2\u0188\u0189\7q\2\2\u0189\u018a\7q\2\2\u018a\u018b\7n\2\2\u018b\u018c"+
		"\7g\2\2\u018c\u018d\7c\2\2\u018d\u018e\7p\2\2\u018e^\3\2\2\2\u018f\u0190"+
		"\7o\2\2\u0190\u0191\7g\2\2\u0191\u0192\7c\2\2\u0192\u0193\7u\2\2\u0193"+
		"\u0194\7w\2\2\u0194\u0195\7t\2\2\u0195\u0196\7g\2\2\u0196`\3\2\2\2\u0197"+
		"\u0198\7t\2\2\u0198\u0199\7c\2\2\u0199\u019a\7v\2\2\u019a\u019b\7k\2\2"+
		"\u019b\u019c\7q\2\2\u019cb\3\2\2\2\u019d\u019e\7f\2\2\u019e\u019f\7c\2"+
		"\2\u019f\u01a0\7v\2\2\u01a0\u01a1\7g\2\2\u01a1d\3\2\2\2\u01a2\u01a3\7"+
		"g\2\2\u01a3\u01a4\7o\2\2\u01a4\u01a5\7r\2\2\u01a5\u01a6\7v\2\2\u01a6\u01a7"+
		"\7{\2\2\u01a7f\3\2\2\2\u01a8\u01a9\7\61\2\2\u01a9\u01aa\7,\2\2\u01aa\u01ae"+
		"\3\2\2\2\u01ab\u01ad\13\2\2\2\u01ac\u01ab\3\2\2\2\u01ad\u01b0\3\2\2\2"+
		"\u01ae\u01af\3\2\2\2\u01ae\u01ac\3\2\2\2\u01af\u01b1\3\2\2\2\u01b0\u01ae"+
		"\3\2\2\2\u01b1\u01b2\7,\2\2\u01b2\u01b3\7\61\2\2\u01b3\u01b4\3\2\2\2\u01b4"+
		"\u01b5\b\64\4\2\u01b5h\3\2\2\2\u01b6\u01b7\7\61\2\2\u01b7\u01b8\7\61\2"+
		"\2\u01b8\u01bc\3\2\2\2\u01b9\u01bb\n\2\2\2\u01ba\u01b9\3\2\2\2\u01bb\u01be"+
		"\3\2\2\2\u01bc\u01ba\3\2\2\2\u01bc\u01bd\3\2\2\2\u01bd\u01bf\3\2\2\2\u01be"+
		"\u01bc\3\2\2\2\u01bf\u01c0\b\65\4\2\u01c0j\3\2\2\2\u01c1\u01c4\7G\2\2"+
		"\u01c2\u01c5\5M\'\2\u01c3\u01c5\5\u009bN\2\u01c4\u01c2\3\2\2\2\u01c4\u01c3"+
		"\3\2\2\2\u01c4\u01c5\3\2\2\2\u01c5\u01c7\3\2\2\2\u01c6\u01c8\5\u00a1Q"+
		"\2\u01c7\u01c6\3\2\2\2\u01c8\u01c9\3\2\2\2\u01c9\u01c7\3\2\2\2\u01c9\u01ca"+
		"\3\2\2\2\u01cal\3\2\2\2\u01cb\u01cc\7v\2\2\u01cc\u01cd\7t\2\2\u01cd\u01ce"+
		"\7w\2\2\u01ce\u01d5\7g\2\2\u01cf\u01d0\7h\2\2\u01d0\u01d1\7c\2\2\u01d1"+
		"\u01d2\7n\2\2\u01d2\u01d3\7u\2\2\u01d3\u01d5\7g\2\2\u01d4\u01cb\3\2\2"+
		"\2\u01d4\u01cf\3\2\2\2\u01d5n\3\2\2\2\u01d6\u01d8\5M\'\2\u01d7\u01d6\3"+
		"\2\2\2\u01d7\u01d8\3\2\2\2\u01d8\u01da\3\2\2\2\u01d9\u01db\5\u00a1Q\2"+
		"\u01da\u01d9\3\2\2\2\u01db\u01dc\3\2\2\2\u01dc\u01da\3\2\2\2\u01dc\u01dd"+
		"\3\2\2\2\u01ddp\3\2\2\2\u01de\u01e0\5\u009bN\2\u01df\u01e1\5\u00a1Q\2"+
		"\u01e0\u01df\3\2\2\2\u01e1\u01e2\3\2\2\2\u01e2\u01e0\3\2\2\2\u01e2\u01e3"+
		"\3\2\2\2\u01e3r\3\2\2\2\u01e4\u01e7\5M\'\2\u01e5\u01e7\5\u009bN\2\u01e6"+
		"\u01e4\3\2\2\2\u01e6\u01e5\3\2\2\2\u01e6\u01e7\3\2\2\2\u01e7\u01e9\3\2"+
		"\2\2\u01e8\u01ea\5\u00a1Q\2\u01e9\u01e8\3\2\2\2\u01ea\u01eb\3\2\2\2\u01eb"+
		"\u01e9\3\2\2\2\u01eb\u01ec\3\2\2\2\u01ec\u01ed\3\2\2\2\u01ed\u01ef\5C"+
		"\"\2\u01ee\u01f0\5\u00a1Q\2\u01ef\u01ee\3\2\2\2\u01f0\u01f1\3\2\2\2\u01f1"+
		"\u01ef\3\2\2\2\u01f1\u01f2\3\2\2\2\u01f2t\3\2\2\2\u01f3\u01f9\5G$\2\u01f4"+
		"\u01f8\n\3\2\2\u01f5\u01f6\7^\2\2\u01f6\u01f8\t\4\2\2\u01f7\u01f4\3\2"+
		"\2\2\u01f7\u01f5\3\2\2\2\u01f8\u01fb\3\2\2\2\u01f9\u01f7\3\2\2\2\u01f9"+
		"\u01fa\3\2\2\2\u01fa\u01fc\3\2\2\2\u01fb\u01f9\3\2\2\2\u01fc\u01fd\5G"+
		"$\2\u01fdv\3\2\2\2\u01fe\u0202\5\u009dO\2\u01ff\u0201\n\5\2\2\u0200\u01ff"+
		"\3\2\2\2\u0201\u0204\3\2\2\2\u0202\u0200\3\2\2\2\u0202\u0203\3\2\2\2\u0203"+
		"\u0205\3\2\2\2\u0204\u0202\3\2\2\2\u0205\u0206\5\u009dO\2\u0206x\3\2\2"+
		"\2\u0207\u0209\5=\37\2\u0208\u020a\5\u00a3R\2\u0209\u0208\3\2\2\2\u020a"+
		"\u020b\3\2\2\2\u020b\u0209\3\2\2\2\u020b\u020c\3\2\2\2\u020cz\3\2\2\2"+
		"\u020d\u0214\5\u00a3R\2\u020e\u0213\5\u00a1Q\2\u020f\u0213\5\u00a3R\2"+
		"\u0210\u0213\5\u009bN\2\u0211\u0213\5\u009fP\2\u0212\u020e\3\2\2\2\u0212"+
		"\u020f\3\2\2\2\u0212\u0210\3\2\2\2\u0212\u0211\3\2\2\2\u0213\u0216\3\2"+
		"\2\2\u0214\u0212\3\2\2\2\u0214\u0215\3\2\2\2\u0215|\3\2\2\2\u0216\u0214"+
		"\3\2\2\2\u0217\u021d\5\u00a3R\2\u0218\u021d\5\u0093J\2\u0219\u021d\5\u008f"+
		"H\2\u021a\u021d\5\u0091I\2\u021b\u021d\5\u0095K\2\u021c\u0217\3\2\2\2"+
		"\u021c\u0218\3\2\2\2\u021c\u0219\3\2\2\2\u021c\u021a\3\2\2\2\u021c\u021b"+
		"\3\2\2\2\u021d\u0229\3\2\2\2\u021e\u0228\5\u009fP\2\u021f\u0228\5\u0097"+
		"L\2\u0220\u0228\5\u0099M\2\u0221\u0228\5\u0093J\2\u0222\u0228\5\u008f"+
		"H\2\u0223\u0228\5\u0091I\2\u0224\u0228\5\u0095K\2\u0225\u0228\5\u00a3"+
		"R\2\u0226\u0228\5\u00a1Q\2\u0227\u021e\3\2\2\2\u0227\u021f\3\2\2\2\u0227"+
		"\u0220\3\2\2\2\u0227\u0221\3\2\2\2\u0227\u0222\3\2\2\2\u0227\u0223\3\2"+
		"\2\2\u0227\u0224\3\2\2\2\u0227\u0225\3\2\2\2\u0227\u0226\3\2\2\2\u0228"+
		"\u022b\3\2\2\2\u0229\u0227\3\2\2\2\u0229\u022a\3\2\2\2\u022a~\3\2\2\2"+
		"\u022b\u0229\3\2\2\2\u022c\u022e\5\u0087D\2\u022d\u022c\3\2\2\2\u022e"+
		"\u022f\3\2\2\2\u022f\u022d\3\2\2\2\u022f\u0230\3\2\2\2\u0230\u0234\3\2"+
		"\2\2\u0231\u0233\5\u0085C\2\u0232\u0231\3\2\2\2\u0233\u0236\3\2\2\2\u0234"+
		"\u0232\3\2\2\2\u0234\u0235\3\2\2\2\u0235\u0237\3\2\2\2\u0236\u0234\3\2"+
		"\2\2\u0237\u0238\b@\5\2\u0238\u0080\3\2\2\2\u0239\u023b\5\u0085C\2\u023a"+
		"\u0239\3\2\2\2\u023b\u023c\3\2\2\2\u023c\u023a\3\2\2\2\u023c\u023d\3\2"+
		"\2\2\u023d\u023f\3\2\2\2\u023e\u0240\7\2\2\3\u023f\u023e\3\2\2\2\u023f"+
		"\u0240\3\2\2\2\u0240\u0241\3\2\2\2\u0241\u0242\bA\4\2\u0242\u0082\3\2"+
		"\2\2\u0243\u0244\7f\2\2\u0244\u0245\7q\2\2\u0245\u0246\7e\2\2\u0246\u024a"+
		"\3\2\2\2\u0247\u0249\13\2\2\2\u0248\u0247\3\2\2\2\u0249\u024c\3\2\2\2"+
		"\u024a\u024b\3\2\2\2\u024a\u0248\3\2\2\2\u024b\u024d\3\2\2\2\u024c\u024a"+
		"\3\2\2\2\u024d\u024e\5\u0087D\2\u024e\u024f\bB\6\2\u024f\u0084\3\2\2\2"+
		"\u0250\u0251\t\6\2\2\u0251\u0086\3\2\2\2\u0252\u0254\7\17\2\2\u0253\u0252"+
		"\3\2\2\2\u0253\u0254\3\2\2\2\u0254\u0255\3\2\2\2\u0255\u0258\7\f\2\2\u0256"+
		"\u0258\7\17\2\2\u0257\u0253\3\2\2\2\u0257\u0256\3\2\2\2\u0258\u0088\3"+
		"\2\2\2\u0259\u025a\7k\2\2\u025a\u025b\7p\2\2\u025b\u025c\7f\2\2\u025c"+
		"\u025d\7g\2\2\u025d\u025e\7p\2\2\u025e\u025f\7v\2\2\u025f\u008a\3\2\2"+
		"\2\u0260\u0261\7f\2\2\u0261\u0262\7g\2\2\u0262\u0263\7f\2\2\u0263\u0264"+
		"\7g\2\2\u0264\u0265\7p\2\2\u0265\u0266\7v\2\2\u0266\u008c\3\2\2\2\u0267"+
		"\u0268\13\2\2\2\u0268\u008e\3\2\2\2\u0269\u026a\7&\2\2\u026a\u0090\3\2"+
		"\2\2\u026b\u026c\7\u20ae\2\2\u026c\u0092\3\2\2\2\u026d\u026e\7\'\2\2\u026e"+
		"\u0094\3\2\2\2\u026f\u0270\t\7\2\2\u0270\u0096\3\2\2\2\u0271\u0272\7\u00b9"+
		"\2\2\u0272\u0098\3\2\2\2\u0273\u0274\7\61\2\2\u0274\u009a\3\2\2\2\u0275"+
		"\u0276\7/\2\2\u0276\u009c\3\2\2\2\u0277\u0279\5\u009bN\2\u0278\u027a\5"+
		"\u009bN\2\u0279\u0278\3\2\2\2\u027a\u027b\3\2\2\2\u027b\u0279\3\2\2\2"+
		"\u027b\u027c\3\2\2\2\u027c\u009e\3\2\2\2\u027d\u027e\7a\2\2\u027e\u00a0"+
		"\3\2\2\2\u027f\u0280\t\b\2\2\u0280\u00a2\3\2\2\2\u0281\u0282\t\t\2\2\u0282"+
		"\u00a4\3\2\2\2 \2\u0150\u01ae\u01bc\u01c4\u01c9\u01d4\u01d7\u01dc\u01e2"+
		"\u01e6\u01eb\u01f1\u01f7\u01f9\u0202\u020b\u0212\u0214\u021c\u0227\u0229"+
		"\u022f\u0234\u023c\u023f\u024a\u0253\u0257\u027b\7\3\35\2\3%\3\2\3\2\3"+
		"@\4\3B\5";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}