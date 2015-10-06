// Generated from /Users/oroncal/workspace/tara/language/grammar/src/tara/language/lexicon/TaraLexer.g4 by ANTLR 4.5.1
package tara.language.grammar;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import tara.compiler.parser.antlr.BlockManager;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TaraLexer extends Lexer {
	static {
		RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION);
	}

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		METAIDENTIFIER = 1, SUB = 2, USE = 3, DSL = 4, VAR = 5, AS = 6, HAS = 7, ON = 8, IS = 9,
		INTO = 10, WITH = 11, ANY = 12, EXTENDS = 13, ABSTRACT = 14, SINGLE = 15, REQUIRED = 16,
		TERMINAL = 17, MAIN = 18, NAMED = 19, PROTOTYPE = 20, FEATURE = 21, FINAL = 22, ENCLOSED = 23,
		PRIVATE = 24, FACET = 25, LEFT_PARENTHESIS = 26, RIGHT_PARENTHESIS = 27, LEFT_SQUARE = 28,
		RIGHT_SQUARE = 29, LIST = 30, INLINE = 31, CLOSE_INLINE = 32, HASHTAG = 33, COLON = 34,
		COMMA = 35, DOT = 36, EQUALS = 37, SEMICOLON = 38, PLUS = 39, WORD = 40, RESOURCE = 41,
		INT_TYPE = 42, TUPLE_TYPE = 43, NATURAL_TYPE = 44, NATIVE_TYPE = 45, DOUBLE_TYPE = 46,
		STRING_TYPE = 47, BOOLEAN_TYPE = 48, MEASURE_TYPE = 49, RATIO_TYPE = 50, DATE_TYPE = 51,
		TIME_TYPE = 52, EMPTY = 53, BLOCK_COMMENT = 54, LINE_COMMENT = 55, SCIENCE_NOT = 56,
		BOOLEAN_VALUE = 57, NATURAL_VALUE = 58, NEGATIVE_VALUE = 59, DOUBLE_VALUE = 60,
		APHOSTROPHE = 61, STRING_MULTILINE = 62, SINGLE_QUOTE = 63, EXPRESSION_MULTILINE = 64,
		PLATE_VALUE = 65, IDENTIFIER = 66, MEASURE_VALUE = 67, NEWLINE = 68, SPACES = 69,
		DOC = 70, SP = 71, NL = 72, NEW_LINE_INDENT = 73, DEDENT = 74, UNKNOWN_TOKEN = 75,
		QUOTE = 76, Q = 77, SLASH_Q = 78, SLASH = 79, CHARACTER = 80, M_QUOTE = 81, M_CHARACTER = 82,
		ME_STRING_MULTILINE = 83, ME_CHARACTER = 84, E_QUOTE = 85, E_SLASH_Q = 86, E_SLASH = 87,
		E_CHARACTER = 88, QUOTE_BEGIN = 89, QUOTE_END = 90, EXPRESSION_BEGIN = 91, EXPRESSION_END = 92;
	public static final int QUOTED = 1;
	public static final int MULTILINE = 2;
	public static final int EXPRESSION_MULTILINE_MODE = 3;
	public static final int EXPRESSION_QUOTED = 4;
	public static String[] modeNames = {
		"DEFAULT_MODE", "QUOTED", "MULTILINE", "EXPRESSION_MULTILINE_MODE", "EXPRESSION_QUOTED"
	};

	public static final String[] ruleNames = {
		"METAIDENTIFIER", "SUB", "USE", "DSL", "VAR", "AS", "HAS", "ON", "IS",
		"INTO", "WITH", "ANY", "EXTENDS", "ABSTRACT", "SINGLE", "REQUIRED", "TERMINAL",
		"MAIN", "NAMED", "PROTOTYPE", "FEATURE", "FINAL", "ENCLOSED", "PRIVATE",
		"FACET", "LEFT_PARENTHESIS", "RIGHT_PARENTHESIS", "LEFT_SQUARE", "RIGHT_SQUARE",
		"LIST", "INLINE", "CLOSE_INLINE", "HASHTAG", "COLON", "COMMA", "DOT",
		"EQUALS", "SEMICOLON", "PLUS", "WORD", "RESOURCE", "INT_TYPE", "TUPLE_TYPE",
		"NATURAL_TYPE", "NATIVE_TYPE", "DOUBLE_TYPE", "STRING_TYPE", "BOOLEAN_TYPE",
		"MEASURE_TYPE", "RATIO_TYPE", "DATE_TYPE", "TIME_TYPE", "EMPTY", "BLOCK_COMMENT",
		"LINE_COMMENT", "SCIENCE_NOT", "BOOLEAN_VALUE", "NATURAL_VALUE", "NEGATIVE_VALUE",
		"DOUBLE_VALUE", "APHOSTROPHE", "STRING_MULTILINE", "SINGLE_QUOTE", "EXPRESSION_MULTILINE",
		"PLATE_VALUE", "IDENTIFIER", "MEASURE_VALUE", "NEWLINE", "SPACES", "DOC",
		"SP", "NL", "NEW_LINE_INDENT", "DEDENT", "UNKNOWN_TOKEN", "QUOTE", "Q",
		"SLASH_Q", "SLASH", "CHARACTER", "M_QUOTE", "M_CHARACTER", "ME_STRING_MULTILINE",
		"ME_CHARACTER", "E_QUOTE", "E_SLASH_Q", "E_SLASH", "E_CHARACTER", "QUOTE_BEGIN",
		"QUOTE_END", "EXPRESSION_BEGIN", "EXPRESSION_END", "DOLLAR", "EURO", "PERCENTAGE",
		"GRADE", "BY", "DIVIDED_BY", "DASH", "UNDERDASH", "DIGIT", "LETTER"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'Concept'", "'sub'", "'use'", "'dsl'", "'var'", "'as'", "'has'",
		"'on'", "'is'", "'into'", "'with'", "'any'", "'extends'", "'abstract'",
		"'single'", "'required'", "'terminal'", "'main'", "'named'", "'prototype'",
		"'feature'", "'final'", "'enclosed'", "'private'", "'facet'", "'('", "')'",
		"'['", "']'", "'...'", "'>'", "'<'", "'#'", "':'", "','", "'.'", "'='",
		null, "'+'", "'word'", "'file'", "'integer'", "'tuple'", "'natural'",
		"'native'", "'double'", "'string'", "'boolean'", "'measure'", "'ratio'",
		"'date'", "'time'", "'empty'", null, null, null, null, null, null, null,
		null, null, null, null, null, null, null, null, null, null, null, null,
		"'indent'", "'dedent'", null, null, "'\"'", "'\\\"'", null, null, null,
		null, null, null, null, "'\\''", null, null, "'%QUOTE_BEGIN%'", "'%QUOTE_END%'",
		"'%EXPRESSION_BEGIN%'", "'%EXPRESSION_END%'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "METAIDENTIFIER", "SUB", "USE", "DSL", "VAR", "AS", "HAS", "ON",
		"IS", "INTO", "WITH", "ANY", "EXTENDS", "ABSTRACT", "SINGLE", "REQUIRED",
		"TERMINAL", "MAIN", "NAMED", "PROTOTYPE", "FEATURE", "FINAL", "ENCLOSED",
		"PRIVATE", "FACET", "LEFT_PARENTHESIS", "RIGHT_PARENTHESIS", "LEFT_SQUARE",
		"RIGHT_SQUARE", "LIST", "INLINE", "CLOSE_INLINE", "HASHTAG", "COLON",
		"COMMA", "DOT", "EQUALS", "SEMICOLON", "PLUS", "WORD", "RESOURCE", "INT_TYPE",
		"TUPLE_TYPE", "NATURAL_TYPE", "NATIVE_TYPE", "DOUBLE_TYPE", "STRING_TYPE",
		"BOOLEAN_TYPE", "MEASURE_TYPE", "RATIO_TYPE", "DATE_TYPE", "TIME_TYPE",
		"EMPTY", "BLOCK_COMMENT", "LINE_COMMENT", "SCIENCE_NOT", "BOOLEAN_VALUE",
		"NATURAL_VALUE", "NEGATIVE_VALUE", "DOUBLE_VALUE", "APHOSTROPHE", "STRING_MULTILINE",
		"SINGLE_QUOTE", "EXPRESSION_MULTILINE", "PLATE_VALUE", "IDENTIFIER", "MEASURE_VALUE",
		"NEWLINE", "SPACES", "DOC", "SP", "NL", "NEW_LINE_INDENT", "DEDENT", "UNKNOWN_TOKEN",
		"QUOTE", "Q", "SLASH_Q", "SLASH", "CHARACTER", "M_QUOTE", "M_CHARACTER",
		"ME_STRING_MULTILINE", "ME_CHARACTER", "E_QUOTE", "E_SLASH_Q", "E_SLASH",
		"E_CHARACTER", "QUOTE_BEGIN", "QUOTE_END", "EXPRESSION_BEGIN", "EXPRESSION_END"
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
		return queue.isEmpty() ? emitEOF() : queue.poll();
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
		if (!isWhiteLineOrEOF()) {
			blockManager.newlineAndSpaces(getTextSpaces(getText()));
			sendTokens();
		} else skip();
	}

	private String getTextSpaces(String text) {
		int index = (text.indexOf(' ') == -1) ? text.indexOf('\t') : text.indexOf(' ');
		return (index == -1) ? "" : text.substring(index);
	}

	private void inline() {
		blockManager.openBracket(getText().length());
		sendTokens();
	}

	private void semicolon() {
		blockManager.semicolon(getText().length());
		sendTokens();
	}

	private void eof() {
		blockManager.eof();
		sendTokens();
	}

	private void sendTokens() {
		blockManager.actions();
		for (BlockManager.Token token : blockManager.actions())
			emitToken(translate(token));
	}

	private int translate(BlockManager.Token token) {
		if (token.toString().equals("NEWLINE")) return NEWLINE;
		if (token.toString().equals("DEDENT")) return DEDENT;
		if (token.toString().equals("NEWLINE_INDENT")) return NEW_LINE_INDENT;
		return UNKNOWN_TOKEN;
	}


	public TaraLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
	}

	@Override
	public String getGrammarFileName() {
		return "TaraLexer.g4";
	}

	@Override
	public String[] getRuleNames() {
		return ruleNames;
	}

	@Override
	public String getSerializedATN() {
		return _serializedATN;
	}

	@Override
	public String[] getModeNames() {
		return modeNames;
	}

	@Override
	public ATN getATN() {
		return _ATN;
	}

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
			case 30:
				INLINE_action((RuleContext) _localctx, actionIndex);
				break;
			case 37:
				SEMICOLON_action((RuleContext) _localctx, actionIndex);
				break;
			case 60:
				APHOSTROPHE_action((RuleContext) _localctx, actionIndex);
				break;
			case 61:
				STRING_MULTILINE_action((RuleContext) _localctx, actionIndex);
				break;
			case 62:
				SINGLE_QUOTE_action((RuleContext) _localctx, actionIndex);
				break;
			case 63:
				EXPRESSION_MULTILINE_action((RuleContext) _localctx, actionIndex);
				break;
			case 67:
				NEWLINE_action((RuleContext) _localctx, actionIndex);
				break;
			case 69:
				DOC_action((RuleContext) _localctx, actionIndex);
				break;
			case 75:
				QUOTE_action((RuleContext) _localctx, actionIndex);
				break;
			case 76:
				Q_action((RuleContext) _localctx, actionIndex);
				break;
			case 77:
				SLASH_Q_action((RuleContext) _localctx, actionIndex);
				break;
			case 78:
				SLASH_action((RuleContext) _localctx, actionIndex);
				break;
			case 79:
				CHARACTER_action((RuleContext) _localctx, actionIndex);
				break;
			case 80:
				M_QUOTE_action((RuleContext) _localctx, actionIndex);
				break;
			case 81:
				M_CHARACTER_action((RuleContext) _localctx, actionIndex);
				break;
			case 82:
				ME_STRING_MULTILINE_action((RuleContext) _localctx, actionIndex);
				break;
			case 83:
				ME_CHARACTER_action((RuleContext) _localctx, actionIndex);
				break;
			case 84:
				E_QUOTE_action((RuleContext) _localctx, actionIndex);
				break;
			case 85:
				E_SLASH_Q_action((RuleContext) _localctx, actionIndex);
				break;
			case 86:
				E_SLASH_action((RuleContext) _localctx, actionIndex);
				break;
			case 87:
				E_CHARACTER_action((RuleContext) _localctx, actionIndex);
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

	private void APHOSTROPHE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
			case 2:
				setType(QUOTE_BEGIN);
				break;
		}
	}

	private void STRING_MULTILINE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
			case 3:
				setType(QUOTE_BEGIN);
				break;
		}
	}

	private void SINGLE_QUOTE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
			case 4:
				setType(EXPRESSION_BEGIN);
				break;
		}
	}

	private void EXPRESSION_MULTILINE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
			case 5:
				setType(EXPRESSION_BEGIN);
				break;
		}
	}

	private void NEWLINE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
			case 6:
				newlinesAndSpaces();
				break;
		}
	}

	private void DOC_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
			case 7:
				emitToken(DOC);
				break;
		}
	}

	private void QUOTE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
			case 8:
				setType(QUOTE_END);
				break;
		}
	}

	private void Q_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
			case 9:
				setType(CHARACTER);
				break;
		}
	}

	private void SLASH_Q_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
			case 10:
				setType(CHARACTER);
				break;
		}
	}

	private void SLASH_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
			case 11:
				setType(CHARACTER);
				break;
		}
	}

	private void CHARACTER_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
			case 12:
				setType(CHARACTER);
				break;
		}
	}

	private void M_QUOTE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
			case 13:
				setType(QUOTE_END);
				break;
		}
	}

	private void M_CHARACTER_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
			case 14:
				setType(CHARACTER);
				break;
		}
	}

	private void ME_STRING_MULTILINE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
			case 15:
				setType(EXPRESSION_END);
				break;
		}
	}

	private void ME_CHARACTER_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
			case 16:
				setType(CHARACTER);
				break;
		}
	}

	private void E_QUOTE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
			case 17:
				setType(EXPRESSION_END);
				break;
		}
	}

	private void E_SLASH_Q_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
			case 18:
				setType(CHARACTER);
				break;
		}
	}

	private void E_SLASH_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
			case 19:
				setType(CHARACTER);
				break;
		}
	}

	private void E_CHARACTER_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
			case 20:
				setType(CHARACTER);
				break;
		}
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2^\u034d\b\1\b\1\b" +
			"\1\b\1\b\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t" +
			"\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4" +
			"\21\t\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4" +
			"\30\t\30\4\31\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4" +
			"\37\t\37\4 \t \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)" +
			"\t)\4*\t*\4+\t+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62" +
			"\4\63\t\63\4\64\t\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4" +
			";\t;\4<\t<\4=\t=\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\t" +
			"F\4G\tG\4H\tH\4I\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\4O\tO\4P\tP\4Q\tQ\4" +
			"R\tR\4S\tS\4T\tT\4U\tU\4V\tV\4W\tW\4X\tX\4Y\tY\4Z\tZ\4[\t[\4\\\t\\\4]" +
			"\t]\4^\t^\4_\t_\4`\t`\4a\ta\4b\tb\4c\tc\4d\td\4e\te\4f\tf\4g\tg\3\2\3" +
			"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5" +
			"\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3" +
			"\n\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\16\3" +
			"\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3" +
			"\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3" +
			"\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3" +
			"\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3" +
			"\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3" +
			"\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3" +
			"\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3" +
			"\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3\37\3\37\3 \3 " +
			"\3 \3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\3&\3&\3\'\6\'\u0187\n\'\r\'\16\'\u0188" +
			"\3\'\3\'\3(\3(\3)\3)\3)\3)\3)\3*\3*\3*\3*\3*\3+\3+\3+\3+\3+\3+\3+\3+\3" +
			",\3,\3,\3,\3,\3,\3-\3-\3-\3-\3-\3-\3-\3-\3.\3.\3.\3.\3.\3.\3.\3/\3/\3" +
			"/\3/\3/\3/\3/\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\61\3\61\3\61\3\61\3" +
			"\61\3\61\3\61\3\61\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\63\3\63\3" +
			"\63\3\63\3\63\3\63\3\64\3\64\3\64\3\64\3\64\3\65\3\65\3\65\3\65\3\65\3" +
			"\66\3\66\3\66\3\66\3\66\3\66\3\67\3\67\3\67\3\67\7\67\u01ee\n\67\f\67" +
			"\16\67\u01f1\13\67\3\67\3\67\3\67\3\67\3\67\38\78\u01f9\n8\f8\168\u01fc" +
			"\138\38\78\u01ff\n8\f8\168\u0202\138\38\38\38\38\78\u0208\n8\f8\168\u020b" +
			"\138\38\38\39\39\39\59\u0212\n9\39\69\u0215\n9\r9\169\u0216\3:\3:\3:\3" +
			":\3:\3:\3:\3:\3:\5:\u0222\n:\3;\5;\u0225\n;\3;\6;\u0228\n;\r;\16;\u0229" +
			"\3<\3<\6<\u022e\n<\r<\16<\u022f\3=\3=\5=\u0234\n=\3=\6=\u0237\n=\r=\16" +
			"=\u0238\3=\3=\6=\u023d\n=\r=\16=\u023e\3>\3>\3>\3>\3>\3?\3?\6?\u0248\n" +
			"?\r?\16?\u0249\3?\3?\3?\3?\3@\3@\3@\3@\3@\3A\3A\6A\u0257\nA\rA\16A\u0258" +
			"\3A\3A\3A\3A\3B\3B\6B\u0261\nB\rB\16B\u0262\3C\3C\3C\3C\7C\u0269\nC\f" +
			"C\16C\u026c\13C\3D\3D\3D\3D\3D\5D\u0273\nD\3D\3D\3D\3D\3D\3D\3D\3D\3D" +
			"\7D\u027e\nD\fD\16D\u0281\13D\3E\6E\u0284\nE\rE\16E\u0285\3E\7E\u0289" +
			"\nE\fE\16E\u028c\13E\3E\3E\3F\6F\u0291\nF\rF\16F\u0292\3F\5F\u0296\nF" +
			"\3F\3F\3G\3G\3G\3G\7G\u029e\nG\fG\16G\u02a1\13G\3G\3G\3G\3H\3H\3I\5I\u02a9" +
			"\nI\3I\3I\5I\u02ad\nI\3J\3J\3J\3J\3J\3J\3J\3K\3K\3K\3K\3K\3K\3K\3L\3L" +
			"\3M\3M\3M\3M\3M\3N\3N\3N\3O\3O\3O\3O\3O\3P\3P\3P\3Q\3Q\3Q\3R\3R\6R\u02d4" +
			"\nR\rR\16R\u02d5\3R\3R\3R\3R\3S\3S\3S\3T\3T\6T\u02e1\nT\rT\16T\u02e2\3" +
			"T\3T\3T\3T\3U\3U\3U\3V\3V\3V\3V\3V\3W\3W\3W\3W\3W\3X\3X\3X\3Y\3Y\3Y\3" +
			"Z\3Z\3Z\3Z\3Z\3Z\3Z\3Z\3Z\3Z\3Z\3Z\3Z\3Z\3[\3[\3[\3[\3[\3[\3[\3[\3[\3" +
			"[\3[\3[\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3" +
			"\\\3\\\3\\\3\\\3]\3]\3]\3]\3]\3]\3]\3]\3]\3]\3]\3]\3]\3]\3]\3]\3]\3^\3" +
			"^\3_\3_\3`\3`\3a\3a\3b\3b\3c\3c\3d\3d\3e\3e\3f\3f\3g\3g\4\u01ef\u029f" +
			"\2h\7\3\t\4\13\5\r\6\17\7\21\b\23\t\25\n\27\13\31\f\33\r\35\16\37\17!" +
			"\20#\21%\22\'\23)\24+\25-\26/\27\61\30\63\31\65\32\67\339\34;\35=\36?" +
			"\37A C!E\"G#I$K%M&O\'Q(S)U*W+Y,[-]._/a\60c\61e\62g\63i\64k\65m\66o\67" +
			"q8s9u:w;y<{=}>\177?\u0081@\u0083A\u0085B\u0087C\u0089D\u008bE\u008dF\u008f" +
			"G\u0091H\u0093I\u0095J\u0097K\u0099L\u009bM\u009dN\u009fO\u00a1P\u00a3" +
			"Q\u00a5R\u00a7S\u00a9T\u00abU\u00adV\u00afW\u00b1X\u00b3Y\u00b5Z\u00b7" +
			"[\u00b9\\\u00bb]\u00bd^\u00bf\2\u00c1\2\u00c3\2\u00c5\2\u00c7\2\u00c9" +
			"\2\u00cb\2\u00cd\2\u00cf\2\u00d1\2\7\2\3\4\5\6\7\4\2\f\f\17\17\4\2\13" +
			"\13\"\"\4\2\u00b2\u00b2\u00bc\u00bc\3\2\62;\6\2C\\c|\u00d3\u00d3\u00f3" +
			"\u00f3\u036a\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2" +
			"\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2" +
			"\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2" +
			"\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2" +
			"\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2" +
			"\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2" +
			"\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W" +
			"\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2" +
			"\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2" +
			"\2q\3\2\2\2\2s\3\2\2\2\2u\3\2\2\2\2w\3\2\2\2\2y\3\2\2\2\2{\3\2\2\2\2}" +
			"\3\2\2\2\2\177\3\2\2\2\2\u0081\3\2\2\2\2\u0083\3\2\2\2\2\u0085\3\2\2\2" +
			"\2\u0087\3\2\2\2\2\u0089\3\2\2\2\2\u008b\3\2\2\2\2\u008d\3\2\2\2\2\u008f" +
			"\3\2\2\2\2\u0091\3\2\2\2\2\u0093\3\2\2\2\2\u0095\3\2\2\2\2\u0097\3\2\2" +
			"\2\2\u0099\3\2\2\2\2\u009b\3\2\2\2\3\u009d\3\2\2\2\3\u009f\3\2\2\2\3\u00a1" +
			"\3\2\2\2\3\u00a3\3\2\2\2\3\u00a5\3\2\2\2\4\u00a7\3\2\2\2\4\u00a9\3\2\2" +
			"\2\5\u00ab\3\2\2\2\5\u00ad\3\2\2\2\6\u00af\3\2\2\2\6\u00b1\3\2\2\2\6\u00b3" +
			"\3\2\2\2\6\u00b5\3\2\2\2\6\u00b7\3\2\2\2\6\u00b9\3\2\2\2\6\u00bb\3\2\2" +
			"\2\6\u00bd\3\2\2\2\7\u00d3\3\2\2\2\t\u00db\3\2\2\2\13\u00df\3\2\2\2\r" +
			"\u00e3\3\2\2\2\17\u00e7\3\2\2\2\21\u00eb\3\2\2\2\23\u00ee\3\2\2\2\25\u00f2" +
			"\3\2\2\2\27\u00f5\3\2\2\2\31\u00f8\3\2\2\2\33\u00fd\3\2\2\2\35\u0102\3" +
			"\2\2\2\37\u0106\3\2\2\2!\u010e\3\2\2\2#\u0117\3\2\2\2%\u011e\3\2\2\2\'" +
			"\u0127\3\2\2\2)\u0130\3\2\2\2+\u0135\3\2\2\2-\u013b\3\2\2\2/\u0145\3\2" +
			"\2\2\61\u014d\3\2\2\2\63\u0153\3\2\2\2\65\u015c\3\2\2\2\67\u0164\3\2\2" +
			"\29\u016a\3\2\2\2;\u016c\3\2\2\2=\u016e\3\2\2\2?\u0170\3\2\2\2A\u0172" +
			"\3\2\2\2C\u0176\3\2\2\2E\u0179\3\2\2\2G\u017b\3\2\2\2I\u017d\3\2\2\2K" +
			"\u017f\3\2\2\2M\u0181\3\2\2\2O\u0183\3\2\2\2Q\u0186\3\2\2\2S\u018c\3\2" +
			"\2\2U\u018e\3\2\2\2W\u0193\3\2\2\2Y\u0198\3\2\2\2[\u01a0\3\2\2\2]\u01a6" +
			"\3\2\2\2_\u01ae\3\2\2\2a\u01b5\3\2\2\2c\u01bc\3\2\2\2e\u01c3\3\2\2\2g" +
			"\u01cb\3\2\2\2i\u01d3\3\2\2\2k\u01d9\3\2\2\2m\u01de\3\2\2\2o\u01e3\3\2" +
			"\2\2q\u01e9\3\2\2\2s\u01fa\3\2\2\2u\u020e\3\2\2\2w\u0221\3\2\2\2y\u0224" +
			"\3\2\2\2{\u022b\3\2\2\2}\u0233\3\2\2\2\177\u0240\3\2\2\2\u0081\u0245\3" +
			"\2\2\2\u0083\u024f\3\2\2\2\u0085\u0254\3\2\2\2\u0087\u025e\3\2\2\2\u0089" +
			"\u0264\3\2\2\2\u008b\u0272\3\2\2\2\u008d\u0283\3\2\2\2\u008f\u0290\3\2" +
			"\2\2\u0091\u0299\3\2\2\2\u0093\u02a5\3\2\2\2\u0095\u02ac\3\2\2\2\u0097" +
			"\u02ae\3\2\2\2\u0099\u02b5\3\2\2\2\u009b\u02bc\3\2\2\2\u009d\u02be\3\2" +
			"\2\2\u009f\u02c3\3\2\2\2\u00a1\u02c6\3\2\2\2\u00a3\u02cb\3\2\2\2\u00a5" +
			"\u02ce\3\2\2\2\u00a7\u02d1\3\2\2\2\u00a9\u02db\3\2\2\2\u00ab\u02de\3\2" +
			"\2\2\u00ad\u02e8\3\2\2\2\u00af\u02eb\3\2\2\2\u00b1\u02f0\3\2\2\2\u00b3" +
			"\u02f5\3\2\2\2\u00b5\u02f8\3\2\2\2\u00b7\u02fb\3\2\2\2\u00b9\u0309\3\2" +
			"\2\2\u00bb\u0315\3\2\2\2\u00bd\u0328\3\2\2\2\u00bf\u0339\3\2\2\2\u00c1" +
			"\u033b\3\2\2\2\u00c3\u033d\3\2\2\2\u00c5\u033f\3\2\2\2\u00c7\u0341\3\2" +
			"\2\2\u00c9\u0343\3\2\2\2\u00cb\u0345\3\2\2\2\u00cd\u0347\3\2\2\2\u00cf" +
			"\u0349\3\2\2\2\u00d1\u034b\3\2\2\2\u00d3\u00d4\7E\2\2\u00d4\u00d5\7q\2" +
			"\2\u00d5\u00d6\7p\2\2\u00d6\u00d7\7e\2\2\u00d7\u00d8\7g\2\2\u00d8\u00d9" +
			"\7r\2\2\u00d9\u00da\7v\2\2\u00da\b\3\2\2\2\u00db\u00dc\7u\2\2\u00dc\u00dd" +
			"\7w\2\2\u00dd\u00de\7d\2\2\u00de\n\3\2\2\2\u00df\u00e0\7w\2\2\u00e0\u00e1" +
			"\7u\2\2\u00e1\u00e2\7g\2\2\u00e2\f\3\2\2\2\u00e3\u00e4\7f\2\2\u00e4\u00e5" +
			"\7u\2\2\u00e5\u00e6\7n\2\2\u00e6\16\3\2\2\2\u00e7\u00e8\7x\2\2\u00e8\u00e9" +
			"\7c\2\2\u00e9\u00ea\7t\2\2\u00ea\20\3\2\2\2\u00eb\u00ec\7c\2\2\u00ec\u00ed" +
			"\7u\2\2\u00ed\22\3\2\2\2\u00ee\u00ef\7j\2\2\u00ef\u00f0\7c\2\2\u00f0\u00f1" +
			"\7u\2\2\u00f1\24\3\2\2\2\u00f2\u00f3\7q\2\2\u00f3\u00f4\7p\2\2\u00f4\26" +
			"\3\2\2\2\u00f5\u00f6\7k\2\2\u00f6\u00f7\7u\2\2\u00f7\30\3\2\2\2\u00f8" +
			"\u00f9\7k\2\2\u00f9\u00fa\7p\2\2\u00fa\u00fb\7v\2\2\u00fb\u00fc\7q\2\2" +
			"\u00fc\32\3\2\2\2\u00fd\u00fe\7y\2\2\u00fe\u00ff\7k\2\2\u00ff\u0100\7" +
			"v\2\2\u0100\u0101\7j\2\2\u0101\34\3\2\2\2\u0102\u0103\7c\2\2\u0103\u0104" +
			"\7p\2\2\u0104\u0105\7{\2\2\u0105\36\3\2\2\2\u0106\u0107\7g\2\2\u0107\u0108" +
			"\7z\2\2\u0108\u0109\7v\2\2\u0109\u010a\7g\2\2\u010a\u010b\7p\2\2\u010b" +
			"\u010c\7f\2\2\u010c\u010d\7u\2\2\u010d \3\2\2\2\u010e\u010f\7c\2\2\u010f" +
			"\u0110\7d\2\2\u0110\u0111\7u\2\2\u0111\u0112\7v\2\2\u0112\u0113\7t\2\2" +
			"\u0113\u0114\7c\2\2\u0114\u0115\7e\2\2\u0115\u0116\7v\2\2\u0116\"\3\2" +
			"\2\2\u0117\u0118\7u\2\2\u0118\u0119\7k\2\2\u0119\u011a\7p\2\2\u011a\u011b" +
			"\7i\2\2\u011b\u011c\7n\2\2\u011c\u011d\7g\2\2\u011d$\3\2\2\2\u011e\u011f" +
			"\7t\2\2\u011f\u0120\7g\2\2\u0120\u0121\7s\2\2\u0121\u0122\7w\2\2\u0122" +
			"\u0123\7k\2\2\u0123\u0124\7t\2\2\u0124\u0125\7g\2\2\u0125\u0126\7f\2\2" +
			"\u0126&\3\2\2\2\u0127\u0128\7v\2\2\u0128\u0129\7g\2\2\u0129\u012a\7t\2" +
			"\2\u012a\u012b\7o\2\2\u012b\u012c\7k\2\2\u012c\u012d\7p\2\2\u012d\u012e" +
			"\7c\2\2\u012e\u012f\7n\2\2\u012f(\3\2\2\2\u0130\u0131\7o\2\2\u0131\u0132" +
			"\7c\2\2\u0132\u0133\7k\2\2\u0133\u0134\7p\2\2\u0134*\3\2\2\2\u0135\u0136" +
			"\7p\2\2\u0136\u0137\7c\2\2\u0137\u0138\7o\2\2\u0138\u0139\7g\2\2\u0139" +
			"\u013a\7f\2\2\u013a,\3\2\2\2\u013b\u013c\7r\2\2\u013c\u013d\7t\2\2\u013d" +
			"\u013e\7q\2\2\u013e\u013f\7v\2\2\u013f\u0140\7q\2\2\u0140\u0141\7v\2\2" +
			"\u0141\u0142\7{\2\2\u0142\u0143\7r\2\2\u0143\u0144\7g\2\2\u0144.\3\2\2" +
			"\2\u0145\u0146\7h\2\2\u0146\u0147\7g\2\2\u0147\u0148\7c\2\2\u0148\u0149" +
			"\7v\2\2\u0149\u014a\7w\2\2\u014a\u014b\7t\2\2\u014b\u014c\7g\2\2\u014c" +
			"\60\3\2\2\2\u014d\u014e\7h\2\2\u014e\u014f\7k\2\2\u014f\u0150\7p\2\2\u0150" +
			"\u0151\7c\2\2\u0151\u0152\7n\2\2\u0152\62\3\2\2\2\u0153\u0154\7g\2\2\u0154" +
			"\u0155\7p\2\2\u0155\u0156\7e\2\2\u0156\u0157\7n\2\2\u0157\u0158\7q\2\2" +
			"\u0158\u0159\7u\2\2\u0159\u015a\7g\2\2\u015a\u015b\7f\2\2\u015b\64\3\2" +
			"\2\2\u015c\u015d\7r\2\2\u015d\u015e\7t\2\2\u015e\u015f\7k\2\2\u015f\u0160" +
			"\7x\2\2\u0160\u0161\7c\2\2\u0161\u0162\7v\2\2\u0162\u0163\7g\2\2\u0163" +
			"\66\3\2\2\2\u0164\u0165\7h\2\2\u0165\u0166\7c\2\2\u0166\u0167\7e\2\2\u0167" +
			"\u0168\7g\2\2\u0168\u0169\7v\2\2\u01698\3\2\2\2\u016a\u016b\7*\2\2\u016b" +
			":\3\2\2\2\u016c\u016d\7+\2\2\u016d<\3\2\2\2\u016e\u016f\7]\2\2\u016f>" +
			"\3\2\2\2\u0170\u0171\7_\2\2\u0171@\3\2\2\2\u0172\u0173\7\60\2\2\u0173" +
			"\u0174\7\60\2\2\u0174\u0175\7\60\2\2\u0175B\3\2\2\2\u0176\u0177\7@\2\2" +
			"\u0177\u0178\b \2\2\u0178D\3\2\2\2\u0179\u017a\7>\2\2\u017aF\3\2\2\2\u017b" +
			"\u017c\7%\2\2\u017cH\3\2\2\2\u017d\u017e\7<\2\2\u017eJ\3\2\2\2\u017f\u0180" +
			"\7.\2\2\u0180L\3\2\2\2\u0181\u0182\7\60\2\2\u0182N\3\2\2\2\u0183\u0184" +
			"\7?\2\2\u0184P\3\2\2\2\u0185\u0187\7=\2\2\u0186\u0185\3\2\2\2\u0187\u0188" +
			"\3\2\2\2\u0188\u0186\3\2\2\2\u0188\u0189\3\2\2\2\u0189\u018a\3\2\2\2\u018a" +
			"\u018b\b\'\3\2\u018bR\3\2\2\2\u018c\u018d\7-\2\2\u018dT\3\2\2\2\u018e" +
			"\u018f\7y\2\2\u018f\u0190\7q\2\2\u0190\u0191\7t\2\2\u0191\u0192\7f\2\2" +
			"\u0192V\3\2\2\2\u0193\u0194\7h\2\2\u0194\u0195\7k\2\2\u0195\u0196\7n\2" +
			"\2\u0196\u0197\7g\2\2\u0197X\3\2\2\2\u0198\u0199\7k\2\2\u0199\u019a\7" +
			"p\2\2\u019a\u019b\7v\2\2\u019b\u019c\7g\2\2\u019c\u019d\7i\2\2\u019d\u019e" +
			"\7g\2\2\u019e\u019f\7t\2\2\u019fZ\3\2\2\2\u01a0\u01a1\7v\2\2\u01a1\u01a2" +
			"\7w\2\2\u01a2\u01a3\7r\2\2\u01a3\u01a4\7n\2\2\u01a4\u01a5\7g\2\2\u01a5" +
			"\\\3\2\2\2\u01a6\u01a7\7p\2\2\u01a7\u01a8\7c\2\2\u01a8\u01a9\7v\2\2\u01a9" +
			"\u01aa\7w\2\2\u01aa\u01ab\7t\2\2\u01ab\u01ac\7c\2\2\u01ac\u01ad\7n\2\2" +
			"\u01ad^\3\2\2\2\u01ae\u01af\7p\2\2\u01af\u01b0\7c\2\2\u01b0\u01b1\7v\2" +
			"\2\u01b1\u01b2\7k\2\2\u01b2\u01b3\7x\2\2\u01b3\u01b4\7g\2\2\u01b4`\3\2" +
			"\2\2\u01b5\u01b6\7f\2\2\u01b6\u01b7\7q\2\2\u01b7\u01b8\7w\2\2\u01b8\u01b9" +
			"\7d\2\2\u01b9\u01ba\7n\2\2\u01ba\u01bb\7g\2\2\u01bbb\3\2\2\2\u01bc\u01bd" +
			"\7u\2\2\u01bd\u01be\7v\2\2\u01be\u01bf\7t\2\2\u01bf\u01c0\7k\2\2\u01c0" +
			"\u01c1\7p\2\2\u01c1\u01c2\7i\2\2\u01c2d\3\2\2\2\u01c3\u01c4\7d\2\2\u01c4" +
			"\u01c5\7q\2\2\u01c5\u01c6\7q\2\2\u01c6\u01c7\7n\2\2\u01c7\u01c8\7g\2\2" +
			"\u01c8\u01c9\7c\2\2\u01c9\u01ca\7p\2\2\u01caf\3\2\2\2\u01cb\u01cc\7o\2" +
			"\2\u01cc\u01cd\7g\2\2\u01cd\u01ce\7c\2\2\u01ce\u01cf\7u\2\2\u01cf\u01d0" +
			"\7w\2\2\u01d0\u01d1\7t\2\2\u01d1\u01d2\7g\2\2\u01d2h\3\2\2\2\u01d3\u01d4" +
			"\7t\2\2\u01d4\u01d5\7c\2\2\u01d5\u01d6\7v\2\2\u01d6\u01d7\7k\2\2\u01d7" +
			"\u01d8\7q\2\2\u01d8j\3\2\2\2\u01d9\u01da\7f\2\2\u01da\u01db\7c\2\2\u01db" +
			"\u01dc\7v\2\2\u01dc\u01dd\7g\2\2\u01ddl\3\2\2\2\u01de\u01df\7v\2\2\u01df" +
			"\u01e0\7k\2\2\u01e0\u01e1\7o\2\2\u01e1\u01e2\7g\2\2\u01e2n\3\2\2\2\u01e3" +
			"\u01e4\7g\2\2\u01e4\u01e5\7o\2\2\u01e5\u01e6\7r\2\2\u01e6\u01e7\7v\2\2" +
			"\u01e7\u01e8\7{\2\2\u01e8p\3\2\2\2\u01e9\u01ea\7\61\2\2\u01ea\u01eb\7" +
			",\2\2\u01eb\u01ef\3\2\2\2\u01ec\u01ee\13\2\2\2\u01ed\u01ec\3\2\2\2\u01ee" +
			"\u01f1\3\2\2\2\u01ef\u01f0\3\2\2\2\u01ef\u01ed\3\2\2\2\u01f0\u01f2\3\2" +
			"\2\2\u01f1\u01ef\3\2\2\2\u01f2\u01f3\7,\2\2\u01f3\u01f4\7\61\2\2\u01f4" +
			"\u01f5\3\2\2\2\u01f5\u01f6\b\67\4\2\u01f6r\3\2\2\2\u01f7\u01f9\t\2\2\2" +
			"\u01f8\u01f7\3\2\2\2\u01f9\u01fc\3\2\2\2\u01fa\u01f8\3\2\2\2\u01fa\u01fb" +
			"\3\2\2\2\u01fb\u0200\3\2\2\2\u01fc\u01fa\3\2\2\2\u01fd\u01ff\t\3\2\2\u01fe" +
			"\u01fd\3\2\2\2\u01ff\u0202\3\2\2\2\u0200\u01fe\3\2\2\2\u0200\u0201\3\2" +
			"\2\2\u0201\u0203\3\2\2\2\u0202\u0200\3\2\2\2\u0203\u0204\7\61\2\2\u0204" +
			"\u0205\7\61\2\2\u0205\u0209\3\2\2\2\u0206\u0208\n\2\2\2\u0207\u0206\3" +
			"\2\2\2\u0208\u020b\3\2\2\2\u0209\u0207\3\2\2\2\u0209\u020a\3\2\2\2\u020a" +
			"\u020c\3\2\2\2\u020b\u0209\3\2\2\2\u020c\u020d\b8\4\2\u020dt\3\2\2\2\u020e" +
			"\u0211\7G\2\2\u020f\u0212\5S(\2\u0210\u0212\5\u00cbd\2\u0211\u020f\3\2" +
			"\2\2\u0211\u0210\3\2\2\2\u0211\u0212\3\2\2\2\u0212\u0214\3\2\2\2\u0213" +
			"\u0215\5\u00cff\2\u0214\u0213\3\2\2\2\u0215\u0216\3\2\2\2\u0216\u0214" +
			"\3\2\2\2\u0216\u0217\3\2\2\2\u0217v\3\2\2\2\u0218\u0219\7v\2\2\u0219\u021a" +
			"\7t\2\2\u021a\u021b\7w\2\2\u021b\u0222\7g\2\2\u021c\u021d\7h\2\2\u021d" +
			"\u021e\7c\2\2\u021e\u021f\7n\2\2\u021f\u0220\7u\2\2\u0220\u0222\7g\2\2" +
			"\u0221\u0218\3\2\2\2\u0221\u021c\3\2\2\2\u0222x\3\2\2\2\u0223\u0225\5" +
			"S(\2\u0224\u0223\3\2\2\2\u0224\u0225\3\2\2\2\u0225\u0227\3\2\2\2\u0226" +
			"\u0228\5\u00cff\2\u0227\u0226\3\2\2\2\u0228\u0229\3\2\2\2\u0229\u0227" +
			"\3\2\2\2\u0229\u022a\3\2\2\2\u022az\3\2\2\2\u022b\u022d\5\u00cbd\2\u022c" +
			"\u022e\5\u00cff\2\u022d\u022c\3\2\2\2\u022e\u022f\3\2\2\2\u022f\u022d" +
			"\3\2\2\2\u022f\u0230\3\2\2\2\u0230|\3\2\2\2\u0231\u0234\5S(\2\u0232\u0234" +
			"\5\u00cbd\2\u0233\u0231\3\2\2\2\u0233\u0232\3\2\2\2\u0233\u0234\3\2\2" +
			"\2\u0234\u0236\3\2\2\2\u0235\u0237\5\u00cff\2\u0236\u0235\3\2\2\2\u0237" +
			"\u0238\3\2\2\2\u0238\u0236\3\2\2\2\u0238\u0239\3\2\2\2\u0239\u023a\3\2" +
			"\2\2\u023a\u023c\5M%\2\u023b\u023d\5\u00cff\2\u023c\u023b\3\2\2\2\u023d" +
			"\u023e\3\2\2\2\u023e\u023c\3\2\2\2\u023e\u023f\3\2\2\2\u023f~\3\2\2\2" +
			"\u0240\u0241\7$\2\2\u0241\u0242\b>\5\2\u0242\u0243\3\2\2\2\u0243\u0244" +
			"\b>\6\2\u0244\u0080\3\2\2\2\u0245\u0247\5O&\2\u0246\u0248\5O&\2\u0247" +
			"\u0246\3\2\2\2\u0248\u0249\3\2\2\2\u0249\u0247\3\2\2\2\u0249\u024a\3\2" +
			"\2\2\u024a\u024b\3\2\2\2\u024b\u024c\b?\7\2\u024c\u024d\3\2\2\2\u024d" +
			"\u024e\b?\b\2\u024e\u0082\3\2\2\2\u024f\u0250\7)\2\2\u0250\u0251\b@\t" +
			"\2\u0251\u0252\3\2\2\2\u0252\u0253\b@\n\2\u0253\u0084\3\2\2\2\u0254\u0256" +
			"\5\u00cbd\2\u0255\u0257\5\u00cbd\2\u0256\u0255\3\2\2\2\u0257\u0258\3\2" +
			"\2\2\u0258\u0256\3\2\2\2\u0258\u0259\3\2\2\2\u0259\u025a\3\2\2\2\u025a" +
			"\u025b\bA\13\2\u025b\u025c\3\2\2\2\u025c\u025d\bA\f\2\u025d\u0086\3\2" +
			"\2\2\u025e\u0260\5G\"\2\u025f\u0261\5\u00d1g\2\u0260\u025f\3\2\2\2\u0261" +
			"\u0262\3\2\2\2\u0262\u0260\3\2\2\2\u0262\u0263\3\2\2\2\u0263\u0088\3\2" +
			"\2\2\u0264\u026a\5\u00d1g\2\u0265\u0269\5\u00cff\2\u0266\u0269\5\u00d1" +
			"g\2\u0267\u0269\5\u00cbd\2\u0268\u0265\3\2\2\2\u0268\u0266\3\2\2\2\u0268" +
			"\u0267\3\2\2\2\u0269\u026c\3\2\2\2\u026a\u0268\3\2\2\2\u026a\u026b\3\2" +
			"\2\2\u026b\u008a\3\2\2\2\u026c\u026a\3\2\2\2\u026d\u0273\5\u00d1g\2\u026e" +
			"\u0273\5\u00c3`\2\u026f\u0273\5\u00bf^\2\u0270\u0273\5\u00c1_\2\u0271" +
			"\u0273\5\u00c5a\2\u0272\u026d\3\2\2\2\u0272\u026e\3\2\2\2\u0272\u026f" +
			"\3\2\2\2\u0272\u0270\3\2\2\2\u0272\u0271\3\2\2\2\u0273\u027f\3\2\2\2\u0274" +
			"\u027e\5\u00cde\2\u0275\u027e\5\u00c7b\2\u0276\u027e\5\u00c9c\2\u0277" +
			"\u027e\5\u00c3`\2\u0278\u027e\5\u00bf^\2\u0279\u027e\5\u00c1_\2\u027a" +
			"\u027e\5\u00c5a\2\u027b\u027e\5\u00d1g\2\u027c\u027e\5\u00cff\2\u027d" +
			"\u0274\3\2\2\2\u027d\u0275\3\2\2\2\u027d\u0276\3\2\2\2\u027d\u0277\3\2" +
			"\2\2\u027d\u0278\3\2\2\2\u027d\u0279\3\2\2\2\u027d\u027a\3\2\2\2\u027d" +
			"\u027b\3\2\2\2\u027d\u027c\3\2\2\2\u027e\u0281\3\2\2\2\u027f\u027d\3\2" +
			"\2\2\u027f\u0280\3\2\2\2\u0280\u008c\3\2\2\2\u0281\u027f\3\2\2\2\u0282" +
			"\u0284\5\u0095I\2\u0283\u0282\3\2\2\2\u0284\u0285\3\2\2\2\u0285\u0283" +
			"\3\2\2\2\u0285\u0286\3\2\2\2\u0286\u028a\3\2\2\2\u0287\u0289\5\u0093H" +
			"\2\u0288\u0287\3\2\2\2\u0289\u028c\3\2\2\2\u028a\u0288\3\2\2\2\u028a\u028b" +
			"\3\2\2\2\u028b\u028d\3\2\2\2\u028c\u028a\3\2\2\2\u028d\u028e\bE\r\2\u028e" +
			"\u008e\3\2\2\2\u028f\u0291\5\u0093H\2\u0290\u028f\3\2\2\2\u0291\u0292" +
			"\3\2\2\2\u0292\u0290\3\2\2\2\u0292\u0293\3\2\2\2\u0293\u0295\3\2\2\2\u0294" +
			"\u0296\7\2\2\3\u0295\u0294\3\2\2\2\u0295\u0296\3\2\2\2\u0296\u0297\3\2" +
			"\2\2\u0297\u0298\bF\16\2\u0298\u0090\3\2\2\2\u0299\u029a\7#\2\2\u029a" +
			"\u029b\7#\2\2\u029b\u029f\3\2\2\2\u029c\u029e\13\2\2\2\u029d\u029c\3\2" +
			"\2\2\u029e\u02a1\3\2\2\2\u029f\u02a0\3\2\2\2\u029f\u029d\3\2\2\2\u02a0" +
			"\u02a2\3\2\2\2\u02a1\u029f\3\2\2\2\u02a2\u02a3\5\u008dE\2\u02a3\u02a4" +
			"\bG\17\2\u02a4\u0092\3\2\2\2\u02a5\u02a6\t\3\2\2\u02a6\u0094\3\2\2\2\u02a7" +
			"\u02a9\7\17\2\2\u02a8\u02a7\3\2\2\2\u02a8\u02a9\3\2\2\2\u02a9\u02aa\3" +
			"\2\2\2\u02aa\u02ad\7\f\2\2\u02ab\u02ad\7\17\2\2\u02ac\u02a8\3\2\2\2\u02ac" +
			"\u02ab\3\2\2\2\u02ad\u0096\3\2\2\2\u02ae\u02af\7k\2\2\u02af\u02b0\7p\2" +
			"\2\u02b0\u02b1\7f\2\2\u02b1\u02b2\7g\2\2\u02b2\u02b3\7p\2\2\u02b3\u02b4" +
			"\7v\2\2\u02b4\u0098\3\2\2\2\u02b5\u02b6\7f\2\2\u02b6\u02b7\7g\2\2\u02b7" +
			"\u02b8\7f\2\2\u02b8\u02b9\7g\2\2\u02b9\u02ba\7p\2\2\u02ba\u02bb\7v\2\2" +
			"\u02bb\u009a\3\2\2\2\u02bc\u02bd\13\2\2\2\u02bd\u009c\3\2\2\2\u02be\u02bf" +
			"\7$\2\2\u02bf\u02c0\bM\20\2\u02c0\u02c1\3\2\2\2\u02c1\u02c2\bM\21\2\u02c2" +
			"\u009e\3\2\2\2\u02c3\u02c4\7$\2\2\u02c4\u02c5\bN\22\2\u02c5\u00a0\3\2" +
			"\2\2\u02c6\u02c7\7^\2\2\u02c7\u02c8\7$\2\2\u02c8\u02c9\3\2\2\2\u02c9\u02ca" +
			"\bO\23\2\u02ca\u00a2\3\2\2\2\u02cb\u02cc\7^\2\2\u02cc\u02cd\bP\24\2\u02cd" +
			"\u00a4\3\2\2\2\u02ce\u02cf\13\2\2\2\u02cf\u02d0\bQ\25\2\u02d0\u00a6\3" +
			"\2\2\2\u02d1\u02d3\5O&\2\u02d2\u02d4\5O&\2\u02d3\u02d2\3\2\2\2\u02d4\u02d5" +
			"\3\2\2\2\u02d5\u02d3\3\2\2\2\u02d5\u02d6\3\2\2\2\u02d6\u02d7\3\2\2\2\u02d7" +
			"\u02d8\bR\26\2\u02d8\u02d9\3\2\2\2\u02d9\u02da\bR\21\2\u02da\u00a8\3\2" +
			"\2\2\u02db\u02dc\13\2\2\2\u02dc\u02dd\bS\27\2\u02dd\u00aa\3\2\2\2\u02de" +
			"\u02e0\5\u00cbd\2\u02df\u02e1\5\u00cbd\2\u02e0\u02df\3\2\2\2\u02e1\u02e2" +
			"\3\2\2\2\u02e2\u02e0\3\2\2\2\u02e2\u02e3\3\2\2\2\u02e3\u02e4\3\2\2\2\u02e4" +
			"\u02e5\bT\30\2\u02e5\u02e6\3\2\2\2\u02e6\u02e7\bT\21\2\u02e7\u00ac\3\2" +
			"\2\2\u02e8\u02e9\13\2\2\2\u02e9\u02ea\bU\31\2\u02ea\u00ae\3\2\2\2\u02eb" +
			"\u02ec\7)\2\2\u02ec\u02ed\bV\32\2\u02ed\u02ee\3\2\2\2\u02ee\u02ef\bV\21" +
			"\2\u02ef\u00b0\3\2\2\2\u02f0\u02f1\7^\2\2\u02f1\u02f2\7)\2\2\u02f2\u02f3" +
			"\3\2\2\2\u02f3\u02f4\bW\33\2\u02f4\u00b2\3\2\2\2\u02f5\u02f6\7^\2\2\u02f6" +
			"\u02f7\bX\34\2\u02f7\u00b4\3\2\2\2\u02f8\u02f9\13\2\2\2\u02f9\u02fa\b" +
			"Y\35\2\u02fa\u00b6\3\2\2\2\u02fb\u02fc\7\'\2\2\u02fc\u02fd\7S\2\2\u02fd" +
			"\u02fe\7W\2\2\u02fe\u02ff\7Q\2\2\u02ff\u0300\7V\2\2\u0300\u0301\7G\2\2" +
			"\u0301\u0302\7a\2\2\u0302\u0303\7D\2\2\u0303\u0304\7G\2\2\u0304\u0305" +
			"\7I\2\2\u0305\u0306\7K\2\2\u0306\u0307\7P\2\2\u0307\u0308\7\'\2\2\u0308" +
			"\u00b8\3\2\2\2\u0309\u030a\7\'\2\2\u030a\u030b\7S\2\2\u030b\u030c\7W\2" +
			"\2\u030c\u030d\7Q\2\2\u030d\u030e\7V\2\2\u030e\u030f\7G\2\2\u030f\u0310" +
			"\7a\2\2\u0310\u0311\7G\2\2\u0311\u0312\7P\2\2\u0312\u0313\7F\2\2\u0313" +
			"\u0314\7\'\2\2\u0314\u00ba\3\2\2\2\u0315\u0316\7\'\2\2\u0316\u0317\7G" +
			"\2\2\u0317\u0318\7Z\2\2\u0318\u0319\7R\2\2\u0319\u031a\7T\2\2\u031a\u031b" +
			"\7G\2\2\u031b\u031c\7U\2\2\u031c\u031d\7U\2\2\u031d\u031e\7K\2\2\u031e" +
			"\u031f\7Q\2\2\u031f\u0320\7P\2\2\u0320\u0321\7a\2\2\u0321\u0322\7D\2\2" +
			"\u0322\u0323\7G\2\2\u0323\u0324\7I\2\2\u0324\u0325\7K\2\2\u0325\u0326" +
			"\7P\2\2\u0326\u0327\7\'\2\2\u0327\u00bc\3\2\2\2\u0328\u0329\7\'\2\2\u0329" +
			"\u032a\7G\2\2\u032a\u032b\7Z\2\2\u032b\u032c\7R\2\2\u032c\u032d\7T\2\2" +
			"\u032d\u032e\7G\2\2\u032e\u032f\7U\2\2\u032f\u0330\7U\2\2\u0330\u0331" +
			"\7K\2\2\u0331\u0332\7Q\2\2\u0332\u0333\7P\2\2\u0333\u0334\7a\2\2\u0334" +
			"\u0335\7G\2\2\u0335\u0336\7P\2\2\u0336\u0337\7F\2\2\u0337\u0338\7\'\2" +
			"\2\u0338\u00be\3\2\2\2\u0339\u033a\7&\2\2\u033a\u00c0\3\2\2\2\u033b\u033c" +
			"\7\u20ae\2\2\u033c\u00c2\3\2\2\2\u033d\u033e\7\'\2\2\u033e\u00c4\3\2\2" +
			"\2\u033f\u0340\t\4\2\2\u0340\u00c6\3\2\2\2\u0341\u0342\7\u00b9\2\2\u0342" +
			"\u00c8\3\2\2\2\u0343\u0344\7\61\2\2\u0344\u00ca\3\2\2\2\u0345\u0346\7" +
			"/\2\2\u0346\u00cc\3\2\2\2\u0347\u0348\7a\2\2\u0348\u00ce\3\2\2\2\u0349" +
			"\u034a\t\5\2\2\u034a\u00d0\3\2\2\2\u034b\u034c\t\6\2\2\u034c\u00d2\3\2" +
			"\2\2\'\2\3\4\5\6\u0188\u01ef\u01fa\u01fe\u0200\u0209\u0211\u0216\u0221" +
			"\u0224\u0229\u022f\u0233\u0238\u023e\u0249\u0258\u0262\u0268\u026a\u0272" +
			"\u027d\u027f\u0285\u028a\u0292\u0295\u029f\u02a8\u02ac\u02d5\u02e2\36" +
			"\3 \2\3\'\3\b\2\2\3>\4\4\3\2\3?\5\4\4\2\3@\6\4\6\2\3A\7\4\5\2\3E\b\2\3" +
			"\2\3G\t\3M\n\4\2\2\3N\13\3O\f\3P\r\3Q\16\3R\17\3S\20\3T\21\3U\22\3V\23" +
			"\3W\24\3X\25\3Y\26";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());

	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}