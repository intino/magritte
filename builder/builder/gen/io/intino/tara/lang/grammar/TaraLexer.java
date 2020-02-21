// Generated from /Users/oroncal/workspace/tara/core/language/src/io/intino/tara/lang/lexicon/TaraLexer.g4 by ANTLR 4.7.2
package io.intino.tara.lang.grammar;

import io.intino.tara.compiler.parser.antlr.BlockManager;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;

import static io.intino.tara.lang.grammar.TaraGrammar.CHARACTER;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TaraLexer extends Lexer {
	static {
		RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION);
	}

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
			new PredictionContextCache();
	public static final int
			SUB = 1, USE = 2, DSL = 3, VAR = 4, AS = 5, HAS = 6, IS = 7, INTO = 8, WITH = 9, ANY = 10,
			EXTENDS = 11, CONCEPT = 12, ABSTRACT = 13, TERMINAL = 14, COMPONENT = 15, FEATURE = 16,
			DIVINE = 17, REQUIRED = 18, FINAL = 19, ENCLOSED = 20, PRIVATE = 21, REACTIVE = 22,
			VOLATILE = 23, DECORABLE = 24, LEFT_PARENTHESIS = 25, RIGHT_PARENTHESIS = 26,
			LEFT_SQUARE = 27, RIGHT_SQUARE = 28, LEFT_CURLY = 29, RIGHT_CURLY = 30, INLINE = 31,
			CLOSE_INLINE = 32, AT = 33, HASHTAG = 34, COLON = 35, COMMA = 36, DOT = 37, EQUALS = 38,
			STAR = 39, LIST = 40, SEMICOLON = 41, PLUS = 42, WORD = 43, RESOURCE = 44, INT_TYPE = 45,
			FUNCTION_TYPE = 46, OBJECT_TYPE = 47, DOUBLE_TYPE = 48, LONG_TYPE = 49, STRING_TYPE = 50,
			BOOLEAN_TYPE = 51, DATE_TYPE = 52, INSTANT_TYPE = 53, TIME_TYPE = 54, EMPTY = 55,
			BLOCK_COMMENT = 56, LINE_COMMENT = 57, SCIENCE_NOT = 58, BOOLEAN_VALUE = 59, NATURAL_VALUE = 60,
			NEGATIVE_VALUE = 61, DOUBLE_VALUE = 62, STRING = 63, STRING_MULTILINE = 64, SINGLE_QUOTE = 65,
			EXPRESSION_MULTILINE = 66, CLASS_TYPE = 67, IDENTIFIER = 68, MEASURE_VALUE = 69,
			NEWLINE = 70, SPACES = 71, DOC = 72, SP = 73, NL = 74, NEW_LINE_INDENT = 75, DEDENT = 76,
			UNKNOWN_TOKEN = 77, ME_STRING_MULTILINE = 78, ME_CHARACTER = 79, E_QUOTE = 80,
			E_SLASH_Q = 81, E_SLASH = 82, E_CHARACTER = 83, QUOTE_BEGIN = 84, QUOTE_END = 85,
			EXPRESSION_BEGIN = 86, EXPRESSION_END = 87;
	public static final int
			EXPRESSION_MULTILINE_MODE = 1, EXPRESSION_QUOTED = 2;
	public static String[] channelNames = {
			"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
			"DEFAULT_MODE", "EXPRESSION_MULTILINE_MODE", "EXPRESSION_QUOTED"
	};

	private static String[] makeRuleNames() {
		return new String[]{
				"SUB", "USE", "DSL", "VAR", "AS", "HAS", "IS", "INTO", "WITH", "ANY",
				"EXTENDS", "CONCEPT", "ABSTRACT", "TERMINAL", "COMPONENT", "FEATURE",
				"DIVINE", "REQUIRED", "FINAL", "ENCLOSED", "PRIVATE", "REACTIVE", "VOLATILE",
				"DECORABLE", "LEFT_PARENTHESIS", "RIGHT_PARENTHESIS", "LEFT_SQUARE",
				"RIGHT_SQUARE", "LEFT_CURLY", "RIGHT_CURLY", "INLINE", "CLOSE_INLINE",
				"AT", "HASHTAG", "COLON", "COMMA", "DOT", "EQUALS", "STAR", "LIST", "SEMICOLON",
				"PLUS", "WORD", "RESOURCE", "INT_TYPE", "FUNCTION_TYPE", "OBJECT_TYPE",
				"DOUBLE_TYPE", "LONG_TYPE", "STRING_TYPE", "BOOLEAN_TYPE", "DATE_TYPE",
				"INSTANT_TYPE", "TIME_TYPE", "EMPTY", "BLOCK_COMMENT", "LINE_COMMENT",
				"SCIENCE_NOT", "BOOLEAN_VALUE", "NATURAL_VALUE", "NEGATIVE_VALUE", "DOUBLE_VALUE",
				"STRING", "STRING_MULTILINE", "SINGLE_QUOTE", "EXPRESSION_MULTILINE",
				"CLASS_TYPE", "IDENTIFIER", "MEASURE_VALUE", "NEWLINE", "SPACES", "DOC",
				"SP", "NL", "NEW_LINE_INDENT", "DEDENT", "UNKNOWN_TOKEN", "ME_STRING_MULTILINE",
				"ME_CHARACTER", "E_QUOTE", "E_SLASH_Q", "E_SLASH", "E_CHARACTER", "QUOTE_BEGIN",
				"QUOTE_END", "EXPRESSION_BEGIN", "EXPRESSION_END", "DOLLAR", "EURO",
				"PERCENTAGE", "GRADE", "BY", "DIVIDED_BY", "DASH", "UNDERDASH", "DIGIT",
				"LETTER"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[]{
				null, "'sub'", "'use'", "'dsl'", "'var'", "'as'", "'has'", "'is'", "'into'",
				"'with'", "'any'", "'extends'", "'concept'", "'abstract'", "'terminal'",
				"'component'", "'feature'", "'divine'", "'required'", "'final'", "'enclosed'",
				"'private'", "'reactive'", "'volatile'", "'decorable'", "'('", "')'",
				"'['", "']'", "'{'", "'}'", "'>'", "'<'", "'@'", "'#'", "':'", "','",
				"'.'", "'='", "'*'", "'...'", null, "'+'", "'word'", "'resource'", "'integer'",
				"'function'", "'object'", "'double'", "'long'", "'string'", "'boolean'",
				"'datex'", "'instant'", "'time'", "'empty'", null, null, null, null,
				null, null, null, null, null, null, null, null, null, null, null, null,
				null, null, null, "'indent'", "'dedent'", null, null, null, null, "'\\''",
				"'\\'", null, "'%QUOTE_BEGIN%'", "'%QUOTE_END%'", "'%EXPRESSION_BEGIN%'",
				"'%EXPRESSION_END%'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[]{
				null, "SUB", "USE", "DSL", "VAR", "AS", "HAS", "IS", "INTO", "WITH",
				"ANY", "EXTENDS", "CONCEPT", "ABSTRACT", "TERMINAL", "COMPONENT", "FEATURE",
				"DIVINE", "REQUIRED", "FINAL", "ENCLOSED", "PRIVATE", "REACTIVE", "VOLATILE",
				"DECORABLE", "LEFT_PARENTHESIS", "RIGHT_PARENTHESIS", "LEFT_SQUARE",
				"RIGHT_SQUARE", "LEFT_CURLY", "RIGHT_CURLY", "INLINE", "CLOSE_INLINE",
				"AT", "HASHTAG", "COLON", "COMMA", "DOT", "EQUALS", "STAR", "LIST", "SEMICOLON",
				"PLUS", "WORD", "RESOURCE", "INT_TYPE", "FUNCTION_TYPE", "OBJECT_TYPE",
				"DOUBLE_TYPE", "LONG_TYPE", "STRING_TYPE", "BOOLEAN_TYPE", "DATE_TYPE",
				"INSTANT_TYPE", "TIME_TYPE", "EMPTY", "BLOCK_COMMENT", "LINE_COMMENT",
				"SCIENCE_NOT", "BOOLEAN_VALUE", "NATURAL_VALUE", "NEGATIVE_VALUE", "DOUBLE_VALUE",
				"STRING", "STRING_MULTILINE", "SINGLE_QUOTE", "EXPRESSION_MULTILINE",
				"CLASS_TYPE", "IDENTIFIER", "MEASURE_VALUE", "NEWLINE", "SPACES", "DOC",
				"SP", "NL", "NEW_LINE_INDENT", "DEDENT", "UNKNOWN_TOKEN", "ME_STRING_MULTILINE",
				"ME_CHARACTER", "E_QUOTE", "E_SLASH_Q", "E_SLASH", "E_CHARACTER", "QUOTE_BEGIN",
				"QUOTE_END", "EXPRESSION_BEGIN", "EXPRESSION_END"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
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

	    private void newlinesAndSpaces() {
	        if (!isWhiteLineOrEOF()){
	            blockManager.newlineAndSpaces(getTextSpaces(getText()));
	            sendTokens();
	        }
	        else skip();
	    }

	    private String getTextSpaces(String text) {
	        int index = (text.indexOf(' ') == -1)? text.indexOf('\t') : text.indexOf(' ');
	        return (index == -1)? "" : text.substring(index);
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
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 30:
			INLINE_action((RuleContext)_localctx, actionIndex);
			break;
		case 40:
			SEMICOLON_action((RuleContext)_localctx, actionIndex);
			break;
		case 63:
			STRING_MULTILINE_action((RuleContext)_localctx, actionIndex);
			break;
		case 64:
			SINGLE_QUOTE_action((RuleContext)_localctx, actionIndex);
			break;
		case 65:
			EXPRESSION_MULTILINE_action((RuleContext)_localctx, actionIndex);
			break;
		case 69:
			NEWLINE_action((RuleContext)_localctx, actionIndex);
			break;
		case 71:
			DOC_action((RuleContext)_localctx, actionIndex);
			break;
		case 77:
			ME_STRING_MULTILINE_action((RuleContext)_localctx, actionIndex);
			break;
		case 78:
			ME_CHARACTER_action((RuleContext)_localctx, actionIndex);
			break;
		case 79:
			E_QUOTE_action((RuleContext)_localctx, actionIndex);
			break;
		case 80:
			E_SLASH_Q_action((RuleContext)_localctx, actionIndex);
			break;
		case 81:
			E_SLASH_action((RuleContext)_localctx, actionIndex);
			break;
		case 82:
			E_CHARACTER_action((RuleContext)_localctx, actionIndex);
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
	private void STRING_MULTILINE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 2:
			setType(STRING);
			break;
		}
	}
	private void SINGLE_QUOTE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 3:
			setType(EXPRESSION_BEGIN);
			break;
		}
	}
	private void EXPRESSION_MULTILINE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 4:
			setType(EXPRESSION_BEGIN);
			break;
		}
	}
	private void NEWLINE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 5:
			 newlinesAndSpaces(); 
			break;
		}
	}
	private void DOC_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 6:
			emitToken(DOC);
			break;
		}
	}
	private void ME_STRING_MULTILINE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 7:
			   setType(EXPRESSION_END); 
			break;
		}
	}
	private void ME_CHARACTER_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 8:
			   setType(CHARACTER); 
			break;
		}
	}
	private void E_QUOTE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 9:
			   setType(EXPRESSION_END); 
			break;
		}
	}
	private void E_SLASH_Q_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 10:
			   setType(CHARACTER); 
			break;
		}
	}
	private void E_SLASH_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 11:
			   setType(CHARACTER); 
			break;
		}
	}
	private void E_CHARACTER_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 12:
			   setType(CHARACTER); 
			break;
		}
	}

	public static final String _serializedATN =
			"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2Y\u035a\b\1\b\1\b" +
					"\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n" +
					"\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21" +
					"\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30" +
					"\4\31\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37" +
					"\4 \t \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t" +
					"*\4+\t+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63" +
					"\4\64\t\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t" +
					"<\4=\t=\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4" +
					"H\tH\4I\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\4O\tO\4P\tP\4Q\tQ\4R\tR\4S\t" +
					"S\4T\tT\4U\tU\4V\tV\4W\tW\4X\tX\4Y\tY\4Z\tZ\4[\t[\4\\\t\\\4]\t]\4^\t^" +
					"\4_\t_\4`\t`\4a\ta\4b\tb\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3" +
					"\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t" +
					"\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3" +
					"\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3" +
					"\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3" +
					"\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3" +
					"\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3" +
					"\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3" +
					"\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3" +
					"\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3" +
					"\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3" +
					"\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3 \3" +
					"!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\3&\3&\3\'\3\'\3(\3(\3)\3)\3)\3)\3*\6*\u018b" +
					"\n*\r*\16*\u018c\3*\3*\3+\3+\3,\3,\3,\3,\3,\3-\3-\3-\3-\3-\3-\3-\3-\3" +
					"-\3.\3.\3.\3.\3.\3.\3.\3.\3/\3/\3/\3/\3/\3/\3/\3/\3/\3\60\3\60\3\60\3" +
					"\60\3\60\3\60\3\60\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\62\3\62\3\62\3" +
					"\62\3\62\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\64\3\64\3\64\3\64\3\64\3" +
					"\64\3\64\3\64\3\65\3\65\3\65\3\65\3\65\3\65\3\66\3\66\3\66\3\66\3\66\3" +
					"\66\3\66\3\66\3\67\3\67\3\67\3\67\3\67\38\38\38\38\38\38\39\39\39\39\7" +
					"9\u01f1\n9\f9\169\u01f4\139\39\39\39\39\39\3:\7:\u01fc\n:\f:\16:\u01ff" +
					"\13:\3:\7:\u0202\n:\f:\16:\u0205\13:\3:\3:\3:\3:\7:\u020b\n:\f:\16:\u020e" +
					"\13:\3:\3:\3;\3;\3;\5;\u0215\n;\3;\6;\u0218\n;\r;\16;\u0219\3<\3<\3<\3" +
					"<\3<\3<\3<\3<\3<\5<\u0225\n<\3=\5=\u0228\n=\3=\6=\u022b\n=\r=\16=\u022c" +
					"\3>\3>\6>\u0231\n>\r>\16>\u0232\3?\3?\5?\u0237\n?\3?\6?\u023a\n?\r?\16" +
					"?\u023b\3?\3?\6?\u0240\n?\r?\16?\u0241\3@\3@\3@\3@\7@\u0248\n@\f@\16@" +
					"\u024b\13@\3@\3@\3A\3A\6A\u0251\nA\rA\16A\u0252\3A\3A\3A\7A\u0258\nA\f" +
					"A\16A\u025b\13A\3A\3A\6A\u025f\nA\rA\16A\u0260\3A\3A\3B\3B\3B\3B\3B\3" +
					"C\3C\6C\u026c\nC\rC\16C\u026d\3C\3C\3C\3C\3D\3D\3D\3D\7D\u0278\nD\fD\16" +
					"D\u027b\13D\3D\3D\3D\3D\3D\3D\7D\u0283\nD\fD\16D\u0286\13D\3D\3D\5D\u028a" +
					"\nD\3D\3D\3E\3E\5E\u0290\nE\3E\3E\3E\3E\7E\u0296\nE\fE\16E\u0299\13E\3" +
					"F\3F\3F\3F\3F\5F\u02a0\nF\3F\3F\3F\3F\3F\3F\3F\3F\3F\7F\u02ab\nF\fF\16" +
					"F\u02ae\13F\3G\6G\u02b1\nG\rG\16G\u02b2\3G\7G\u02b6\nG\fG\16G\u02b9\13" +
					"G\3G\3G\3H\6H\u02be\nH\rH\16H\u02bf\3H\5H\u02c3\nH\3H\3H\3I\3I\3I\3I\7" +
					"I\u02cb\nI\fI\16I\u02ce\13I\3I\3I\3I\3J\3J\3K\5K\u02d6\nK\3K\3K\5K\u02da" +
					"\nK\3L\3L\3L\3L\3L\3L\3L\3M\3M\3M\3M\3M\3M\3M\3N\3N\3O\3O\6O\u02ee\nO" +
					"\rO\16O\u02ef\3O\3O\3O\3O\3P\3P\3P\3Q\3Q\3Q\3Q\3Q\3R\3R\3R\3R\3R\3S\3" +
					"S\3S\3T\3T\3T\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3V\3V\3V\3V\3" +
					"V\3V\3V\3V\3V\3V\3V\3V\3W\3W\3W\3W\3W\3W\3W\3W\3W\3W\3W\3W\3W\3W\3W\3" +
					"W\3W\3W\3W\3X\3X\3X\3X\3X\3X\3X\3X\3X\3X\3X\3X\3X\3X\3X\3X\3X\3Y\3Y\3" +
					"Z\3Z\3[\3[\3\\\3\\\3]\3]\3^\3^\3_\3_\3`\3`\3a\3a\3b\3b\4\u01f2\u02cc\2" +
					"c\5\3\7\4\t\5\13\6\r\7\17\b\21\t\23\n\25\13\27\f\31\r\33\16\35\17\37\20" +
					"!\21#\22%\23\'\24)\25+\26-\27/\30\61\31\63\32\65\33\67\349\35;\36=\37" +
					"? A!C\"E#G$I%K&M\'O(Q)S*U+W,Y-[.]/_\60a\61c\62e\63g\64i\65k\66m\67o8q" +
					"9s:u;w<y={>}?\177@\u0081A\u0083B\u0085C\u0087D\u0089E\u008bF\u008dG\u008f" +
					"H\u0091I\u0093J\u0095K\u0097L\u0099M\u009bN\u009dO\u009fP\u00a1Q\u00a3" +
					"R\u00a5S\u00a7T\u00a9U\u00abV\u00adW\u00afX\u00b1Y\u00b3\2\u00b5\2\u00b7" +
					"\2\u00b9\2\u00bb\2\u00bd\2\u00bf\2\u00c1\2\u00c3\2\u00c5\2\5\2\3\4\t\4" +
					"\2\f\f\17\17\4\2\13\13\"\"\4\2$$^^\4\2??^^\4\2\u00b2\u00b2\u00bc\u00bc" +
					"\3\2\62;\20\2C\\c|\u00c3\u00c3\u00cb\u00cb\u00cf\u00cf\u00d3\u00d3\u00d5" +
					"\u00d5\u00dc\u00dc\u00e3\u00e3\u00eb\u00eb\u00ef\u00ef\u00f3\u00f3\u00f5" +
					"\u00f5\u00fc\u00fc\2\u0381\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3" +
					"\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2" +
					"\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3" +
					"\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2" +
					"\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\2" +
					"9\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3" +
					"\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2" +
					"\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2" +
					"_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3" +
					"\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\2u\3\2\2\2\2w\3\2\2" +
					"\2\2y\3\2\2\2\2{\3\2\2\2\2}\3\2\2\2\2\177\3\2\2\2\2\u0081\3\2\2\2\2\u0083" +
					"\3\2\2\2\2\u0085\3\2\2\2\2\u0087\3\2\2\2\2\u0089\3\2\2\2\2\u008b\3\2\2" +
					"\2\2\u008d\3\2\2\2\2\u008f\3\2\2\2\2\u0091\3\2\2\2\2\u0093\3\2\2\2\2\u0095" +
					"\3\2\2\2\2\u0097\3\2\2\2\2\u0099\3\2\2\2\2\u009b\3\2\2\2\2\u009d\3\2\2" +
					"\2\3\u009f\3\2\2\2\3\u00a1\3\2\2\2\4\u00a3\3\2\2\2\4\u00a5\3\2\2\2\4\u00a7" +
					"\3\2\2\2\4\u00a9\3\2\2\2\4\u00ab\3\2\2\2\4\u00ad\3\2\2\2\4\u00af\3\2\2" +
					"\2\4\u00b1\3\2\2\2\5\u00c7\3\2\2\2\7\u00cb\3\2\2\2\t\u00cf\3\2\2\2\13" +
					"\u00d3\3\2\2\2\r\u00d7\3\2\2\2\17\u00da\3\2\2\2\21\u00de\3\2\2\2\23\u00e1" +
					"\3\2\2\2\25\u00e6\3\2\2\2\27\u00eb\3\2\2\2\31\u00ef\3\2\2\2\33\u00f7\3" +
					"\2\2\2\35\u00ff\3\2\2\2\37\u0108\3\2\2\2!\u0111\3\2\2\2#\u011b\3\2\2\2" +
					"%\u0123\3\2\2\2\'\u012a\3\2\2\2)\u0133\3\2\2\2+\u0139\3\2\2\2-\u0142\3" +
					"\2\2\2/\u014a\3\2\2\2\61\u0153\3\2\2\2\63\u015c\3\2\2\2\65\u0166\3\2\2" +
					"\2\67\u0168\3\2\2\29\u016a\3\2\2\2;\u016c\3\2\2\2=\u016e\3\2\2\2?\u0170" +
					"\3\2\2\2A\u0172\3\2\2\2C\u0175\3\2\2\2E\u0177\3\2\2\2G\u0179\3\2\2\2I" +
					"\u017b\3\2\2\2K\u017d\3\2\2\2M\u017f\3\2\2\2O\u0181\3\2\2\2Q\u0183\3\2" +
					"\2\2S\u0185\3\2\2\2U\u018a\3\2\2\2W\u0190\3\2\2\2Y\u0192\3\2\2\2[\u0197" +
					"\3\2\2\2]\u01a0\3\2\2\2_\u01a8\3\2\2\2a\u01b1\3\2\2\2c\u01b8\3\2\2\2e" +
					"\u01bf\3\2\2\2g\u01c4\3\2\2\2i\u01cb\3\2\2\2k\u01d3\3\2\2\2m\u01d9\3\2" +
					"\2\2o\u01e1\3\2\2\2q\u01e6\3\2\2\2s\u01ec\3\2\2\2u\u01fd\3\2\2\2w\u0211" +
					"\3\2\2\2y\u0224\3\2\2\2{\u0227\3\2\2\2}\u022e\3\2\2\2\177\u0236\3\2\2" +
					"\2\u0081\u0243\3\2\2\2\u0083\u024e\3\2\2\2\u0085\u0264\3\2\2\2\u0087\u0269" +
					"\3\2\2\2\u0089\u0273\3\2\2\2\u008b\u028f\3\2\2\2\u008d\u029f\3\2\2\2\u008f" +
					"\u02b0\3\2\2\2\u0091\u02bd\3\2\2\2\u0093\u02c6\3\2\2\2\u0095\u02d2\3\2" +
					"\2\2\u0097\u02d9\3\2\2\2\u0099\u02db\3\2\2\2\u009b\u02e2\3\2\2\2\u009d" +
					"\u02e9\3\2\2\2\u009f\u02eb\3\2\2\2\u00a1\u02f5\3\2\2\2\u00a3\u02f8\3\2" +
					"\2\2\u00a5\u02fd\3\2\2\2\u00a7\u0302\3\2\2\2\u00a9\u0305\3\2\2\2\u00ab" +
					"\u0308\3\2\2\2\u00ad\u0316\3\2\2\2\u00af\u0322\3\2\2\2\u00b1\u0335\3\2" +
					"\2\2\u00b3\u0346\3\2\2\2\u00b5\u0348\3\2\2\2\u00b7\u034a\3\2\2\2\u00b9" +
					"\u034c\3\2\2\2\u00bb\u034e\3\2\2\2\u00bd\u0350\3\2\2\2\u00bf\u0352\3\2" +
					"\2\2\u00c1\u0354\3\2\2\2\u00c3\u0356\3\2\2\2\u00c5\u0358\3\2\2\2\u00c7" +
					"\u00c8\7u\2\2\u00c8\u00c9\7w\2\2\u00c9\u00ca\7d\2\2\u00ca\6\3\2\2\2\u00cb" +
					"\u00cc\7w\2\2\u00cc\u00cd\7u\2\2\u00cd\u00ce\7g\2\2\u00ce\b\3\2\2\2\u00cf" +
					"\u00d0\7f\2\2\u00d0\u00d1\7u\2\2\u00d1\u00d2\7n\2\2\u00d2\n\3\2\2\2\u00d3" +
					"\u00d4\7x\2\2\u00d4\u00d5\7c\2\2\u00d5\u00d6\7t\2\2\u00d6\f\3\2\2\2\u00d7" +
					"\u00d8\7c\2\2\u00d8\u00d9\7u\2\2\u00d9\16\3\2\2\2\u00da\u00db\7j\2\2\u00db" +
					"\u00dc\7c\2\2\u00dc\u00dd\7u\2\2\u00dd\20\3\2\2\2\u00de\u00df\7k\2\2\u00df" +
					"\u00e0\7u\2\2\u00e0\22\3\2\2\2\u00e1\u00e2\7k\2\2\u00e2\u00e3\7p\2\2\u00e3" +
					"\u00e4\7v\2\2\u00e4\u00e5\7q\2\2\u00e5\24\3\2\2\2\u00e6\u00e7\7y\2\2\u00e7" +
					"\u00e8\7k\2\2\u00e8\u00e9\7v\2\2\u00e9\u00ea\7j\2\2\u00ea\26\3\2\2\2\u00eb" +
					"\u00ec\7c\2\2\u00ec\u00ed\7p\2\2\u00ed\u00ee\7{\2\2\u00ee\30\3\2\2\2\u00ef" +
					"\u00f0\7g\2\2\u00f0\u00f1\7z\2\2\u00f1\u00f2\7v\2\2\u00f2\u00f3\7g\2\2" +
					"\u00f3\u00f4\7p\2\2\u00f4\u00f5\7f\2\2\u00f5\u00f6\7u\2\2\u00f6\32\3\2" +
					"\2\2\u00f7\u00f8\7e\2\2\u00f8\u00f9\7q\2\2\u00f9\u00fa\7p\2\2\u00fa\u00fb" +
					"\7e\2\2\u00fb\u00fc\7g\2\2\u00fc\u00fd\7r\2\2\u00fd\u00fe\7v\2\2\u00fe" +
					"\34\3\2\2\2\u00ff\u0100\7c\2\2\u0100\u0101\7d\2\2\u0101\u0102\7u\2\2\u0102" +
					"\u0103\7v\2\2\u0103\u0104\7t\2\2\u0104\u0105\7c\2\2\u0105\u0106\7e\2\2" +
					"\u0106\u0107\7v\2\2\u0107\36\3\2\2\2\u0108\u0109\7v\2\2\u0109\u010a\7" +
					"g\2\2\u010a\u010b\7t\2\2\u010b\u010c\7o\2\2\u010c\u010d\7k\2\2\u010d\u010e" +
					"\7p\2\2\u010e\u010f\7c\2\2\u010f\u0110\7n\2\2\u0110 \3\2\2\2\u0111\u0112" +
					"\7e\2\2\u0112\u0113\7q\2\2\u0113\u0114\7o\2\2\u0114\u0115\7r\2\2\u0115" +
					"\u0116\7q\2\2\u0116\u0117\7p\2\2\u0117\u0118\7g\2\2\u0118\u0119\7p\2\2" +
					"\u0119\u011a\7v\2\2\u011a\"\3\2\2\2\u011b\u011c\7h\2\2\u011c\u011d\7g" +
					"\2\2\u011d\u011e\7c\2\2\u011e\u011f\7v\2\2\u011f\u0120\7w\2\2\u0120\u0121" +
					"\7t\2\2\u0121\u0122\7g\2\2\u0122$\3\2\2\2\u0123\u0124\7f\2\2\u0124\u0125" +
					"\7k\2\2\u0125\u0126\7x\2\2\u0126\u0127\7k\2\2\u0127\u0128\7p\2\2\u0128" +
					"\u0129\7g\2\2\u0129&\3\2\2\2\u012a\u012b\7t\2\2\u012b\u012c\7g\2\2\u012c" +
					"\u012d\7s\2\2\u012d\u012e\7w\2\2\u012e\u012f\7k\2\2\u012f\u0130\7t\2\2" +
					"\u0130\u0131\7g\2\2\u0131\u0132\7f\2\2\u0132(\3\2\2\2\u0133\u0134\7h\2" +
					"\2\u0134\u0135\7k\2\2\u0135\u0136\7p\2\2\u0136\u0137\7c\2\2\u0137\u0138" +
					"\7n\2\2\u0138*\3\2\2\2\u0139\u013a\7g\2\2\u013a\u013b\7p\2\2\u013b\u013c" +
					"\7e\2\2\u013c\u013d\7n\2\2\u013d\u013e\7q\2\2\u013e\u013f\7u\2\2\u013f" +
					"\u0140\7g\2\2\u0140\u0141\7f\2\2\u0141,\3\2\2\2\u0142\u0143\7r\2\2\u0143" +
					"\u0144\7t\2\2\u0144\u0145\7k\2\2\u0145\u0146\7x\2\2\u0146\u0147\7c\2\2" +
					"\u0147\u0148\7v\2\2\u0148\u0149\7g\2\2\u0149.\3\2\2\2\u014a\u014b\7t\2" +
					"\2\u014b\u014c\7g\2\2\u014c\u014d\7c\2\2\u014d\u014e\7e\2\2\u014e\u014f" +
					"\7v\2\2\u014f\u0150\7k\2\2\u0150\u0151\7x\2\2\u0151\u0152\7g\2\2\u0152" +
					"\60\3\2\2\2\u0153\u0154\7x\2\2\u0154\u0155\7q\2\2\u0155\u0156\7n\2\2\u0156" +
					"\u0157\7c\2\2\u0157\u0158\7v\2\2\u0158\u0159\7k\2\2\u0159\u015a\7n\2\2" +
					"\u015a\u015b\7g\2\2\u015b\62\3\2\2\2\u015c\u015d\7f\2\2\u015d\u015e\7" +
					"g\2\2\u015e\u015f\7e\2\2\u015f\u0160\7q\2\2\u0160\u0161\7t\2\2\u0161\u0162" +
					"\7c\2\2\u0162\u0163\7d\2\2\u0163\u0164\7n\2\2\u0164\u0165\7g\2\2\u0165" +
					"\64\3\2\2\2\u0166\u0167\7*\2\2\u0167\66\3\2\2\2\u0168\u0169\7+\2\2\u0169" +
					"8\3\2\2\2\u016a\u016b\7]\2\2\u016b:\3\2\2\2\u016c\u016d\7_\2\2\u016d<" +
					"\3\2\2\2\u016e\u016f\7}\2\2\u016f>\3\2\2\2\u0170\u0171\7\177\2\2\u0171" +
					"@\3\2\2\2\u0172\u0173\7@\2\2\u0173\u0174\b \2\2\u0174B\3\2\2\2\u0175\u0176" +
					"\7>\2\2\u0176D\3\2\2\2\u0177\u0178\7B\2\2\u0178F\3\2\2\2\u0179\u017a\7" +
					"%\2\2\u017aH\3\2\2\2\u017b\u017c\7<\2\2\u017cJ\3\2\2\2\u017d\u017e\7." +
					"\2\2\u017eL\3\2\2\2\u017f\u0180\7\60\2\2\u0180N\3\2\2\2\u0181\u0182\7" +
					"?\2\2\u0182P\3\2\2\2\u0183\u0184\7,\2\2\u0184R\3\2\2\2\u0185\u0186\7\60" +
					"\2\2\u0186\u0187\7\60\2\2\u0187\u0188\7\60\2\2\u0188T\3\2\2\2\u0189\u018b" +
					"\7=\2\2\u018a\u0189\3\2\2\2\u018b\u018c\3\2\2\2\u018c\u018a\3\2\2\2\u018c" +
					"\u018d\3\2\2\2\u018d\u018e\3\2\2\2\u018e\u018f\b*\3\2\u018fV\3\2\2\2\u0190" +
					"\u0191\7-\2\2\u0191X\3\2\2\2\u0192\u0193\7y\2\2\u0193\u0194\7q\2\2\u0194" +
					"\u0195\7t\2\2\u0195\u0196\7f\2\2\u0196Z\3\2\2\2\u0197\u0198\7t\2\2\u0198" +
					"\u0199\7g\2\2\u0199\u019a\7u\2\2\u019a\u019b\7q\2\2\u019b\u019c\7w\2\2" +
					"\u019c\u019d\7t\2\2\u019d\u019e\7e\2\2\u019e\u019f\7g\2\2\u019f\\\3\2" +
					"\2\2\u01a0\u01a1\7k\2\2\u01a1\u01a2\7p\2\2\u01a2\u01a3\7v\2\2\u01a3\u01a4" +
					"\7g\2\2\u01a4\u01a5\7i\2\2\u01a5\u01a6\7g\2\2\u01a6\u01a7\7t\2\2\u01a7" +
					"^\3\2\2\2\u01a8\u01a9\7h\2\2\u01a9\u01aa\7w\2\2\u01aa\u01ab\7p\2\2\u01ab" +
					"\u01ac\7e\2\2\u01ac\u01ad\7v\2\2\u01ad\u01ae\7k\2\2\u01ae\u01af\7q\2\2" +
					"\u01af\u01b0\7p\2\2\u01b0`\3\2\2\2\u01b1\u01b2\7q\2\2\u01b2\u01b3\7d\2" +
					"\2\u01b3\u01b4\7l\2\2\u01b4\u01b5\7g\2\2\u01b5\u01b6\7e\2\2\u01b6\u01b7" +
					"\7v\2\2\u01b7b\3\2\2\2\u01b8\u01b9\7f\2\2\u01b9\u01ba\7q\2\2\u01ba\u01bb" +
					"\7w\2\2\u01bb\u01bc\7d\2\2\u01bc\u01bd\7n\2\2\u01bd\u01be\7g\2\2\u01be" +
					"d\3\2\2\2\u01bf\u01c0\7n\2\2\u01c0\u01c1\7q\2\2\u01c1\u01c2\7p\2\2\u01c2" +
					"\u01c3\7i\2\2\u01c3f\3\2\2\2\u01c4\u01c5\7u\2\2\u01c5\u01c6\7v\2\2\u01c6" +
					"\u01c7\7t\2\2\u01c7\u01c8\7k\2\2\u01c8\u01c9\7p\2\2\u01c9\u01ca\7i\2\2" +
					"\u01cah\3\2\2\2\u01cb\u01cc\7d\2\2\u01cc\u01cd\7q\2\2\u01cd\u01ce\7q\2" +
					"\2\u01ce\u01cf\7n\2\2\u01cf\u01d0\7g\2\2\u01d0\u01d1\7c\2\2\u01d1\u01d2" +
					"\7p\2\2\u01d2j\3\2\2\2\u01d3\u01d4\7f\2\2\u01d4\u01d5\7c\2\2\u01d5\u01d6" +
					"\7v\2\2\u01d6\u01d7\7g\2\2\u01d7\u01d8\7z\2\2\u01d8l\3\2\2\2\u01d9\u01da" +
					"\7k\2\2\u01da\u01db\7p\2\2\u01db\u01dc\7u\2\2\u01dc\u01dd\7v\2\2\u01dd" +
					"\u01de\7c\2\2\u01de\u01df\7p\2\2\u01df\u01e0\7v\2\2\u01e0n\3\2\2\2\u01e1" +
					"\u01e2\7v\2\2\u01e2\u01e3\7k\2\2\u01e3\u01e4\7o\2\2\u01e4\u01e5\7g\2\2" +
					"\u01e5p\3\2\2\2\u01e6\u01e7\7g\2\2\u01e7\u01e8\7o\2\2\u01e8\u01e9\7r\2" +
					"\2\u01e9\u01ea\7v\2\2\u01ea\u01eb\7{\2\2\u01ebr\3\2\2\2\u01ec\u01ed\7" +
					"\61\2\2\u01ed\u01ee\7,\2\2\u01ee\u01f2\3\2\2\2\u01ef\u01f1\13\2\2\2\u01f0" +
					"\u01ef\3\2\2\2\u01f1\u01f4\3\2\2\2\u01f2\u01f3\3\2\2\2\u01f2\u01f0\3\2" +
					"\2\2\u01f3\u01f5\3\2\2\2\u01f4\u01f2\3\2\2\2\u01f5\u01f6\7,\2\2\u01f6" +
					"\u01f7\7\61\2\2\u01f7\u01f8\3\2\2\2\u01f8\u01f9\b9\4\2\u01f9t\3\2\2\2" +
					"\u01fa\u01fc\t\2\2\2\u01fb\u01fa\3\2\2\2\u01fc\u01ff\3\2\2\2\u01fd\u01fb" +
					"\3\2\2\2\u01fd\u01fe\3\2\2\2\u01fe\u0203\3\2\2\2\u01ff\u01fd\3\2\2\2\u0200" +
					"\u0202\t\3\2\2\u0201\u0200\3\2\2\2\u0202\u0205\3\2\2\2\u0203\u0201\3\2" +
					"\2\2\u0203\u0204\3\2\2\2\u0204\u0206\3\2\2\2\u0205\u0203\3\2\2\2\u0206" +
					"\u0207\7\61\2\2\u0207\u0208\7\61\2\2\u0208\u020c\3\2\2\2\u0209\u020b\n" +
					"\2\2\2\u020a\u0209\3\2\2\2\u020b\u020e\3\2\2\2\u020c\u020a\3\2\2\2\u020c" +
					"\u020d\3\2\2\2\u020d\u020f\3\2\2\2\u020e\u020c\3\2\2\2\u020f\u0210\b:" +
					"\4\2\u0210v\3\2\2\2\u0211\u0214\7G\2\2\u0212\u0215\5W+\2\u0213\u0215\5" +
					"\u00bf_\2\u0214\u0212\3\2\2\2\u0214\u0213\3\2\2\2\u0214\u0215\3\2\2\2" +
					"\u0215\u0217\3\2\2\2\u0216\u0218\5\u00c3a\2\u0217\u0216\3\2\2\2\u0218" +
					"\u0219\3\2\2\2\u0219\u0217\3\2\2\2\u0219\u021a\3\2\2\2\u021ax\3\2\2\2" +
					"\u021b\u021c\7v\2\2\u021c\u021d\7t\2\2\u021d\u021e\7w\2\2\u021e\u0225" +
					"\7g\2\2\u021f\u0220\7h\2\2\u0220\u0221\7c\2\2\u0221\u0222\7n\2\2\u0222" +
					"\u0223\7u\2\2\u0223\u0225\7g\2\2\u0224\u021b\3\2\2\2\u0224\u021f\3\2\2" +
					"\2\u0225z\3\2\2\2\u0226\u0228\5W+\2\u0227\u0226\3\2\2\2\u0227\u0228\3" +
					"\2\2\2\u0228\u022a\3\2\2\2\u0229\u022b\5\u00c3a\2\u022a\u0229\3\2\2\2" +
					"\u022b\u022c\3\2\2\2\u022c\u022a\3\2\2\2\u022c\u022d\3\2\2\2\u022d|\3" +
					"\2\2\2\u022e\u0230\5\u00bf_\2\u022f\u0231\5\u00c3a\2\u0230\u022f\3\2\2" +
					"\2\u0231\u0232\3\2\2\2\u0232\u0230\3\2\2\2\u0232\u0233\3\2\2\2\u0233~" +
					"\3\2\2\2\u0234\u0237\5W+\2\u0235\u0237\5\u00bf_\2\u0236\u0234\3\2\2\2" +
					"\u0236\u0235\3\2\2\2\u0236\u0237\3\2\2\2\u0237\u0239\3\2\2\2\u0238\u023a" +
					"\5\u00c3a\2\u0239\u0238\3\2\2\2\u023a\u023b\3\2\2\2\u023b\u0239\3\2\2" +
					"\2\u023b\u023c\3\2\2\2\u023c\u023d\3\2\2\2\u023d\u023f\5M&\2\u023e\u0240" +
					"\5\u00c3a\2\u023f\u023e\3\2\2\2\u0240\u0241\3\2\2\2\u0241\u023f\3\2\2" +
					"\2\u0241\u0242\3\2\2\2\u0242\u0080\3\2\2\2\u0243\u0249\7$\2\2\u0244\u0248" +
					"\n\4\2\2\u0245\u0246\7^\2\2\u0246\u0248\t\4\2\2\u0247\u0244\3\2\2\2\u0247" +
					"\u0245\3\2\2\2\u0248\u024b\3\2\2\2\u0249\u0247\3\2\2\2\u0249\u024a\3\2" +
					"\2\2\u024a\u024c\3\2\2\2\u024b\u0249\3\2\2\2\u024c\u024d\7$\2\2\u024d" +
					"\u0082\3\2\2\2\u024e\u0250\5O\'\2\u024f\u0251\5O\'\2\u0250\u024f\3\2\2" +
					"\2\u0251\u0252\3\2\2\2\u0252\u0250\3\2\2\2\u0252\u0253\3\2\2\2\u0253\u0259" +
					"\3\2\2\2\u0254\u0258\n\5\2\2\u0255\u0256\7^\2\2\u0256\u0258\t\5\2\2\u0257" +
					"\u0254\3\2\2\2\u0257\u0255\3\2\2\2\u0258\u025b\3\2\2\2\u0259\u0257\3\2" +
					"\2\2\u0259\u025a\3\2\2\2\u025a\u025c\3\2\2\2\u025b\u0259\3\2\2\2\u025c" +
					"\u025e\5O\'\2\u025d\u025f\5O\'\2\u025e\u025d\3\2\2\2\u025f\u0260\3\2\2" +
					"\2\u0260\u025e\3\2\2\2\u0260\u0261\3\2\2\2\u0261\u0262\3\2\2\2\u0262\u0263" +
					"\bA\5\2\u0263\u0084\3\2\2\2\u0264\u0265\7)\2\2\u0265\u0266\bB\6\2\u0266" +
					"\u0267\3\2\2\2\u0267\u0268\bB\7\2\u0268\u0086\3\2\2\2\u0269\u026b\5\u00bf" +
					"_\2\u026a\u026c\5\u00bf_\2\u026b\u026a\3\2\2\2\u026c\u026d\3\2\2\2\u026d" +
					"\u026b\3\2\2\2\u026d\u026e\3\2\2\2\u026e\u026f\3\2\2\2\u026f\u0270\bC" +
					"\b\2\u0270\u0271\3\2\2\2\u0271\u0272\bC\t\2\u0272\u0088\3\2\2\2\u0273" +
					"\u0279\5C!\2\u0274\u0275\5\u008bE\2\u0275\u0276\5M&\2\u0276\u0278\3\2" +
					"\2\2\u0277\u0274\3\2\2\2\u0278\u027b\3\2\2\2\u0279\u0277\3\2\2\2\u0279" +
					"\u027a\3\2\2\2\u027a\u027c\3\2\2\2\u027b\u0279\3\2\2\2\u027c\u027d\5\u008b" +
					"E\2\u027d\u0289\3\2\2\2\u027e\u0284\5K%\2\u027f\u0280\5\u008bE\2\u0280" +
					"\u0281\5M&\2\u0281\u0283\3\2\2\2\u0282\u027f\3\2\2\2\u0283\u0286\3\2\2" +
					"\2\u0284\u0282\3\2\2\2\u0284\u0285\3\2\2\2\u0285\u0287\3\2\2\2\u0286\u0284" +
					"\3\2\2\2\u0287\u0288\5\u008bE\2\u0288\u028a\3\2\2\2\u0289\u027e\3\2\2" +
					"\2\u0289\u028a\3\2\2\2\u028a\u028b\3\2\2\2\u028b\u028c\5A \2\u028c\u008a" +
					"\3\2\2\2\u028d\u0290\5\u00c5b\2\u028e\u0290\5\u00c1`\2\u028f\u028d\3\2" +
					"\2\2\u028f\u028e\3\2\2\2\u0290\u0297\3\2\2\2\u0291\u0296\5\u00c3a\2\u0292" +
					"\u0296\5\u00c5b\2\u0293\u0296\5\u00bf_\2\u0294\u0296\5\u00c1`\2\u0295" +
					"\u0291\3\2\2\2\u0295\u0292\3\2\2\2\u0295\u0293\3\2\2\2\u0295\u0294\3\2" +
					"\2\2\u0296\u0299\3\2\2\2\u0297\u0295\3\2\2\2\u0297\u0298\3\2\2\2\u0298" +
					"\u008c\3\2\2\2\u0299\u0297\3\2\2\2\u029a\u02a0\5\u00c5b\2\u029b\u02a0" +
					"\5\u00b7[\2\u029c\u02a0\5\u00b3Y\2\u029d\u02a0\5\u00b5Z\2\u029e\u02a0" +
					"\5\u00b9\\\2\u029f\u029a\3\2\2\2\u029f\u029b\3\2\2\2\u029f\u029c\3\2\2" +
					"\2\u029f\u029d\3\2\2\2\u029f\u029e\3\2\2\2\u02a0\u02ac\3\2\2\2\u02a1\u02ab" +
					"\5\u00c1`\2\u02a2\u02ab\5\u00bb]\2\u02a3\u02ab\5\u00bd^\2\u02a4\u02ab" +
					"\5\u00b7[\2\u02a5\u02ab\5\u00b3Y\2\u02a6\u02ab\5\u00b5Z\2\u02a7\u02ab" +
					"\5\u00b9\\\2\u02a8\u02ab\5\u00c5b\2\u02a9\u02ab\5\u00c3a\2\u02aa\u02a1" +
					"\3\2\2\2\u02aa\u02a2\3\2\2\2\u02aa\u02a3\3\2\2\2\u02aa\u02a4\3\2\2\2\u02aa" +
					"\u02a5\3\2\2\2\u02aa\u02a6\3\2\2\2\u02aa\u02a7\3\2\2\2\u02aa\u02a8\3\2" +
					"\2\2\u02aa\u02a9\3\2\2\2\u02ab\u02ae\3\2\2\2\u02ac\u02aa\3\2\2\2\u02ac" +
					"\u02ad\3\2\2\2\u02ad\u008e\3\2\2\2\u02ae\u02ac\3\2\2\2\u02af\u02b1\5\u0097" +
					"K\2\u02b0\u02af\3\2\2\2\u02b1\u02b2\3\2\2\2\u02b2\u02b0\3\2\2\2\u02b2" +
					"\u02b3\3\2\2\2\u02b3\u02b7\3\2\2\2\u02b4\u02b6\5\u0095J\2\u02b5\u02b4" +
					"\3\2\2\2\u02b6\u02b9\3\2\2\2\u02b7\u02b5\3\2\2\2\u02b7\u02b8\3\2\2\2\u02b8" +
					"\u02ba\3\2\2\2\u02b9\u02b7\3\2\2\2\u02ba\u02bb\bG\n\2\u02bb\u0090\3\2" +
					"\2\2\u02bc\u02be\5\u0095J\2\u02bd\u02bc\3\2\2\2\u02be\u02bf\3\2\2\2\u02bf" +
					"\u02bd\3\2\2\2\u02bf\u02c0\3\2\2\2\u02c0\u02c2\3\2\2\2\u02c1\u02c3\7\2" +
					"\2\3\u02c2\u02c1\3\2\2\2\u02c2\u02c3\3\2\2\2\u02c3\u02c4\3\2\2\2\u02c4" +
					"\u02c5\bH\13\2\u02c5\u0092\3\2\2\2\u02c6\u02c7\7#\2\2\u02c7\u02c8\7#\2" +
					"\2\u02c8\u02cc\3\2\2\2\u02c9\u02cb\13\2\2\2\u02ca\u02c9\3\2\2\2\u02cb" +
					"\u02ce\3\2\2\2\u02cc\u02cd\3\2\2\2\u02cc\u02ca\3\2\2\2\u02cd\u02cf\3\2" +
					"\2\2\u02ce\u02cc\3\2\2\2\u02cf\u02d0\5\u008fG\2\u02d0\u02d1\bI\f\2\u02d1" +
					"\u0094\3\2\2\2\u02d2\u02d3\t\3\2\2\u02d3\u0096\3\2\2\2\u02d4\u02d6\7\17" +
					"\2\2\u02d5\u02d4\3\2\2\2\u02d5\u02d6\3\2\2\2\u02d6\u02d7\3\2\2\2\u02d7" +
					"\u02da\7\f\2\2\u02d8\u02da\7\17\2\2\u02d9\u02d5\3\2\2\2\u02d9\u02d8\3" +
					"\2\2\2\u02da\u0098\3\2\2\2\u02db\u02dc\7k\2\2\u02dc\u02dd\7p\2\2\u02dd" +
					"\u02de\7f\2\2\u02de\u02df\7g\2\2\u02df\u02e0\7p\2\2\u02e0\u02e1\7v\2\2" +
					"\u02e1\u009a\3\2\2\2\u02e2\u02e3\7f\2\2\u02e3\u02e4\7g\2\2\u02e4\u02e5" +
					"\7f\2\2\u02e5\u02e6\7g\2\2\u02e6\u02e7\7p\2\2\u02e7\u02e8\7v\2\2\u02e8" +
					"\u009c\3\2\2\2\u02e9\u02ea\13\2\2\2\u02ea\u009e\3\2\2\2\u02eb\u02ed\5" +
					"\u00bf_\2\u02ec\u02ee\5\u00bf_\2\u02ed\u02ec\3\2\2\2\u02ee\u02ef\3\2\2" +
					"\2\u02ef\u02ed\3\2\2\2\u02ef\u02f0\3\2\2\2\u02f0\u02f1\3\2\2\2\u02f1\u02f2" +
					"\bO\r\2\u02f2\u02f3\3\2\2\2\u02f3\u02f4\bO\16\2\u02f4\u00a0\3\2\2\2\u02f5" +
					"\u02f6\13\2\2\2\u02f6\u02f7\bP\17\2\u02f7\u00a2\3\2\2\2\u02f8\u02f9\7" +
					")\2\2\u02f9\u02fa\bQ\20\2\u02fa\u02fb\3\2\2\2\u02fb\u02fc\bQ\16\2\u02fc" +
					"\u00a4\3\2\2\2\u02fd\u02fe\7^\2\2\u02fe\u02ff\7)\2\2\u02ff\u0300\3\2\2" +
					"\2\u0300\u0301\bR\21\2\u0301\u00a6\3\2\2\2\u0302\u0303\7^\2\2\u0303\u0304" +
					"\bS\22\2\u0304\u00a8\3\2\2\2\u0305\u0306\13\2\2\2\u0306\u0307\bT\23\2" +
					"\u0307\u00aa\3\2\2\2\u0308\u0309\7\'\2\2\u0309\u030a\7S\2\2\u030a\u030b" +
					"\7W\2\2\u030b\u030c\7Q\2\2\u030c\u030d\7V\2\2\u030d\u030e\7G\2\2\u030e" +
					"\u030f\7a\2\2\u030f\u0310\7D\2\2\u0310\u0311\7G\2\2\u0311\u0312\7I\2\2" +
					"\u0312\u0313\7K\2\2\u0313\u0314\7P\2\2\u0314\u0315\7\'\2\2\u0315\u00ac" +
					"\3\2\2\2\u0316\u0317\7\'\2\2\u0317\u0318\7S\2\2\u0318\u0319\7W\2\2\u0319" +
					"\u031a\7Q\2\2\u031a\u031b\7V\2\2\u031b\u031c\7G\2\2\u031c\u031d\7a\2\2" +
					"\u031d\u031e\7G\2\2\u031e\u031f\7P\2\2\u031f\u0320\7F\2\2\u0320\u0321" +
					"\7\'\2\2\u0321\u00ae\3\2\2\2\u0322\u0323\7\'\2\2\u0323\u0324\7G\2\2\u0324" +
					"\u0325\7Z\2\2\u0325\u0326\7R\2\2\u0326\u0327\7T\2\2\u0327\u0328\7G\2\2" +
					"\u0328\u0329\7U\2\2\u0329\u032a\7U\2\2\u032a\u032b\7K\2\2\u032b\u032c" +
					"\7Q\2\2\u032c\u032d\7P\2\2\u032d\u032e\7a\2\2\u032e\u032f\7D\2\2\u032f" +
					"\u0330\7G\2\2\u0330\u0331\7I\2\2\u0331\u0332\7K\2\2\u0332\u0333\7P\2\2" +
					"\u0333\u0334\7\'\2\2\u0334\u00b0\3\2\2\2\u0335\u0336\7\'\2\2\u0336\u0337" +
					"\7G\2\2\u0337\u0338\7Z\2\2\u0338\u0339\7R\2\2\u0339\u033a\7T\2\2\u033a" +
					"\u033b\7G\2\2\u033b\u033c\7U\2\2\u033c\u033d\7U\2\2\u033d\u033e\7K\2\2" +
					"\u033e\u033f\7Q\2\2\u033f\u0340\7P\2\2\u0340\u0341\7a\2\2\u0341\u0342" +
					"\7G\2\2\u0342\u0343\7P\2\2\u0343\u0344\7F\2\2\u0344\u0345\7\'\2\2\u0345" +
					"\u00b2\3\2\2\2\u0346\u0347\7&\2\2\u0347\u00b4\3\2\2\2\u0348\u0349\7\u20ae" +
					"\2\2\u0349\u00b6\3\2\2\2\u034a\u034b\7\'\2\2\u034b\u00b8\3\2\2\2\u034c" +
					"\u034d\t\6\2\2\u034d\u00ba\3\2\2\2\u034e\u034f\7\u00b9\2\2\u034f\u00bc" +
					"\3\2\2\2\u0350\u0351\7\61\2\2\u0351\u00be\3\2\2\2\u0352\u0353\7/\2\2\u0353" +
					"\u00c0\3\2\2\2\u0354\u0355\7a\2\2\u0355\u00c2\3\2\2\2\u0356\u0357\t\7" +
					"\2\2\u0357\u00c4\3\2\2\2\u0358\u0359\t\b\2\2\u0359\u00c6\3\2\2\2,\2\3" +
					"\4\u018c\u01f2\u01fd\u0201\u0203\u020c\u0214\u0219\u0224\u0227\u022c\u0232" +
					"\u0236\u023b\u0241\u0247\u0249\u0252\u0257\u0259\u0260\u026d\u0279\u0284" +
					"\u0289\u028f\u0295\u0297\u029f\u02aa\u02ac\u02b2\u02b7\u02bf\u02c2\u02cc" +
					"\u02d5\u02d9\u02ef\24\3 \2\3*\3\b\2\2\3A\4\3B\5\4\4\2\3C\6\4\3\2\3G\7" +
					"\2\3\2\3I\b\3O\t\4\2\2\3P\n\3Q\13\3R\f\3S\r\3T\16";
	public static final ATN _ATN =
			new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}