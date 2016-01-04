// Generated from /Users/oroncal/workspace/tara/language/grammar/src/tara/lang/lexicon/TaraLexer.g4 by ANTLR 4.5.1
package tara.lang.grammar;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import tara.compiler.parser.antlr.BlockManager;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TaraLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		SUB=1, USE=2, DSL=3, VAR=4, AS=5, HAS=6, ON=7, IS=8, INTO=9, WITH=10, 
		ANY=11, EXTENDS=12, CONCEPT=13, ABSTRACT=14, TERMINAL=15, COMPONENT=16, 
		PROTOTYPE=17, FEATURE=18, FINAL=19, ENCLOSED=20, PRIVATE=21, NATIVE=22,
		FRAGMENT = 23, LEFT_PARENTHESIS = 24, RIGHT_PARENTHESIS = 25, LEFT_SQUARE = 26,
		RIGHT_SQUARE = 27, LEFT_CURLY = 28, RIGHT_CURLY = 29, INLINE = 30, CLOSE_INLINE = 31,
		HASHTAG = 32, COLON = 33, COMMA = 34, DOT = 35, EQUALS = 36, STAR = 37, SEMICOLON = 38,
		PLUS = 39, WORD = 40, RESOURCE = 41, INT_TYPE = 42, TUPLE_TYPE = 43, FUNCTION_TYPE = 44,
		DOUBLE_TYPE = 45, STRING_TYPE = 46, BOOLEAN_TYPE = 47, DATE_TYPE = 48, TIME_TYPE = 49,
		EMPTY = 50, BLOCK_COMMENT = 51, LINE_COMMENT = 52, SCIENCE_NOT = 53, BOOLEAN_VALUE = 54,
		NATURAL_VALUE = 55, NEGATIVE_VALUE = 56, DOUBLE_VALUE = 57, APHOSTROPHE = 58,
		STRING_MULTILINE = 59, SINGLE_QUOTE = 60, EXPRESSION_MULTILINE = 61, ANCHOR_VALUE = 62,
		IDENTIFIER = 63, MEASURE_VALUE = 64, NEWLINE = 65, SPACES = 66, DOC = 67, SP = 68,
		NL = 69, NEW_LINE_INDENT = 70, DEDENT = 71, UNKNOWN_TOKEN = 72, QUOTE = 73, Q = 74,
		SLASH_Q = 75, SLASH = 76, CHARACTER = 77, M_QUOTE = 78, M_CHARACTER = 79, ME_STRING_MULTILINE = 80,
		ME_CHARACTER = 81, E_QUOTE = 82, E_SLASH_Q = 83, E_SLASH = 84, E_CHARACTER = 85,
		QUOTE_BEGIN = 86, QUOTE_END = 87, EXPRESSION_BEGIN = 88, EXPRESSION_END = 89;
	public static final int QUOTED = 1;
	public static final int MULTILINE = 2;
	public static final int EXPRESSION_MULTILINE_MODE = 3;
	public static final int EXPRESSION_QUOTED = 4;
	public static String[] modeNames = {
		"DEFAULT_MODE", "QUOTED", "MULTILINE", "EXPRESSION_MULTILINE_MODE", "EXPRESSION_QUOTED"
	};

	public static final String[] ruleNames = {
		"SUB", "USE", "DSL", "VAR", "AS", "HAS", "ON", "IS", "INTO", "WITH", "ANY", 
		"EXTENDS", "CONCEPT", "ABSTRACT", "TERMINAL", "COMPONENT", "PROTOTYPE",
		"FEATURE", "FINAL", "ENCLOSED", "PRIVATE", "NATIVE", "FRAGMENT", "LEFT_PARENTHESIS", 
		"RIGHT_PARENTHESIS", "LEFT_SQUARE", "RIGHT_SQUARE", "LEFT_CURLY", "RIGHT_CURLY", 
		"INLINE", "CLOSE_INLINE", "HASHTAG", "COLON", "COMMA", "DOT", "EQUALS", 
		"STAR", "SEMICOLON", "PLUS", "WORD", "RESOURCE", "INT_TYPE", "TUPLE_TYPE", 
		"FUNCTION_TYPE", "DOUBLE_TYPE", "STRING_TYPE", "BOOLEAN_TYPE", "DATE_TYPE", 
		"TIME_TYPE", "EMPTY", "BLOCK_COMMENT", "LINE_COMMENT", "SCIENCE_NOT", 
		"BOOLEAN_VALUE", "NATURAL_VALUE", "NEGATIVE_VALUE", "DOUBLE_VALUE", "APHOSTROPHE", 
		"STRING_MULTILINE", "SINGLE_QUOTE", "EXPRESSION_MULTILINE", "ANCHOR_VALUE", 
		"IDENTIFIER", "MEASURE_VALUE", "NEWLINE", "SPACES", "DOC", "SP", "NL", 
		"NEW_LINE_INDENT", "DEDENT", "UNKNOWN_TOKEN", "QUOTE", "Q", "SLASH_Q", 
		"SLASH", "CHARACTER", "M_QUOTE", "M_CHARACTER", "ME_STRING_MULTILINE", 
		"ME_CHARACTER", "E_QUOTE", "E_SLASH_Q", "E_SLASH", "E_CHARACTER", "QUOTE_BEGIN", 
		"QUOTE_END", "EXPRESSION_BEGIN", "EXPRESSION_END", "DOLLAR", "EURO", "PERCENTAGE", 
		"GRADE", "BY", "DIVIDED_BY", "DASH", "UNDERDASH", "DIGIT", "LETTER"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'sub'", "'use'", "'dsl'", "'var'", "'as'", "'has'", "'on'", "'is'", 
		"'into'", "'with'", "'any'", "'extends'", "'concept'", "'abstract'", "'terminal'", 
		"'component'", "'prototype'", "'feature'", "'final'", "'enclosed'", "'private'",
		"'native'", "'fragment'", "'('", "')'", "'['", "']'", "'{'", "'}'", "'>'",
		"'<'", "'#'", "':'", "','", "'.'", "'='", "'*'", null, "'+'", "'word'",
		"'resource'", "'integer'", "'tuple'", "'function'", "'double'", "'string'",
		"'boolean'", "'date'", "'time'", "'empty'", null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null,
		null, null, "'indent'", "'dedent'", null, null, "'\"'", "'\\\"'", null,
		null, null, null, null, null, null, "'\\''", null, null, "'%QUOTE_BEGIN%'",
		"'%QUOTE_END%'", "'%EXPRESSION_BEGIN%'", "'%EXPRESSION_END%'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "SUB", "USE", "DSL", "VAR", "AS", "HAS", "ON", "IS", "INTO", "WITH", 
		"ANY", "EXTENDS", "CONCEPT", "ABSTRACT", "TERMINAL", "COMPONENT", "PROTOTYPE",
		"FEATURE", "FINAL", "ENCLOSED", "PRIVATE", "NATIVE", "FRAGMENT", "LEFT_PARENTHESIS", 
		"RIGHT_PARENTHESIS", "LEFT_SQUARE", "RIGHT_SQUARE", "LEFT_CURLY", "RIGHT_CURLY", 
		"INLINE", "CLOSE_INLINE", "HASHTAG", "COLON", "COMMA", "DOT", "EQUALS", 
		"STAR", "SEMICOLON", "PLUS", "WORD", "RESOURCE", "INT_TYPE", "TUPLE_TYPE", 
		"FUNCTION_TYPE", "DOUBLE_TYPE", "STRING_TYPE", "BOOLEAN_TYPE", "DATE_TYPE", 
		"TIME_TYPE", "EMPTY", "BLOCK_COMMENT", "LINE_COMMENT", "SCIENCE_NOT", 
		"BOOLEAN_VALUE", "NATURAL_VALUE", "NEGATIVE_VALUE", "DOUBLE_VALUE", "APHOSTROPHE", 
		"STRING_MULTILINE", "SINGLE_QUOTE", "EXPRESSION_MULTILINE", "ANCHOR_VALUE", 
		"IDENTIFIER", "MEASURE_VALUE", "NEWLINE", "SPACES", "DOC", "SP", "NL", 
		"NEW_LINE_INDENT", "DEDENT", "UNKNOWN_TOKEN", "QUOTE", "Q", "SLASH_Q", 
		"SLASH", "CHARACTER", "M_QUOTE", "M_CHARACTER", "ME_STRING_MULTILINE", 
		"ME_CHARACTER", "E_QUOTE", "E_SLASH_Q", "E_SLASH", "E_CHARACTER", "QUOTE_BEGIN", 
		"QUOTE_END", "EXPRESSION_BEGIN", "EXPRESSION_END"
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
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
			case 29:
			INLINE_action((RuleContext)_localctx, actionIndex);
			break;
			case 37:
			SEMICOLON_action((RuleContext)_localctx, actionIndex);
			break;
			case 57:
			APHOSTROPHE_action((RuleContext)_localctx, actionIndex);
			break;
			case 58:
			STRING_MULTILINE_action((RuleContext)_localctx, actionIndex);
			break;
			case 59:
			SINGLE_QUOTE_action((RuleContext)_localctx, actionIndex);
			break;
			case 60:
			EXPRESSION_MULTILINE_action((RuleContext)_localctx, actionIndex);
			break;
			case 64:
			NEWLINE_action((RuleContext)_localctx, actionIndex);
			break;
			case 66:
			DOC_action((RuleContext)_localctx, actionIndex);
			break;
			case 72:
			QUOTE_action((RuleContext)_localctx, actionIndex);
			break;
			case 73:
			Q_action((RuleContext)_localctx, actionIndex);
			break;
			case 74:
			SLASH_Q_action((RuleContext)_localctx, actionIndex);
			break;
			case 75:
			SLASH_action((RuleContext)_localctx, actionIndex);
			break;
			case 76:
			CHARACTER_action((RuleContext)_localctx, actionIndex);
			break;
			case 77:
			M_QUOTE_action((RuleContext)_localctx, actionIndex);
			break;
			case 78:
			M_CHARACTER_action((RuleContext)_localctx, actionIndex);
			break;
			case 79:
			ME_STRING_MULTILINE_action((RuleContext)_localctx, actionIndex);
			break;
			case 80:
			ME_CHARACTER_action((RuleContext)_localctx, actionIndex);
			break;
			case 81:
			E_QUOTE_action((RuleContext)_localctx, actionIndex);
			break;
			case 82:
			E_SLASH_Q_action((RuleContext)_localctx, actionIndex);
			break;
			case 83:
			E_SLASH_action((RuleContext)_localctx, actionIndex);
			break;
			case 84:
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2[\u0335\b\1\b\1\b" +
		"\1\b\1\b\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t"+
		"\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4"+
		"\21\t\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4"+
		"\30\t\30\4\31\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4"+
		"\37\t\37\4 \t \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)"+
		"\t)\4*\t*\4+\t+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62"+
		"\4\63\t\63\4\64\t\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4"+
		";\t;\4<\t<\4=\t=\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\t"+
		"F\4G\tG\4H\tH\4I\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\4O\tO\4P\tP\4Q\tQ\4"+
		"R\tR\4S\tS\4T\tT\4U\tU\4V\tV\4W\tW\4X\tX\4Y\tY\4Z\tZ\4[\t[\4\\\t\\\4]"+
			"\t]\4^\t^\4_\t_\4`\t`\4a\ta\4b\tb\4c\tc\4d\td\3\2\3\2\3\2\3\2\3\3\3\3" +
			"\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3" +
			"\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3" +
			"\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16" +
			"\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20" +
			"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21" +
			"\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\23" +
			"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\25" +
			"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26" +
			"\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30" +
			"\3\30\3\30\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35" +
			"\3\36\3\36\3\37\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\3&\3&" +
			"\3\'\6\'\u017c\n\'\r\'\16\'\u017d\3\'\3\'\3(\3(\3)\3)\3)\3)\3)\3*\3*\3" +
			"*\3*\3*\3*\3*\3*\3*\3+\3+\3+\3+\3+\3+\3+\3+\3,\3,\3,\3,\3,\3,\3-\3-\3" +
			"-\3-\3-\3-\3-\3-\3-\3.\3.\3.\3.\3.\3.\3.\3/\3/\3/\3/\3/\3/\3/\3\60\3\60" +
			"\3\60\3\60\3\60\3\60\3\60\3\60\3\61\3\61\3\61\3\61\3\61\3\62\3\62\3\62" +
			"\3\62\3\62\3\63\3\63\3\63\3\63\3\63\3\63\3\64\3\64\3\64\3\64\7\64\u01d3" +
			"\n\64\f\64\16\64\u01d6\13\64\3\64\3\64\3\64\3\64\3\64\3\65\7\65\u01de" +
			"\n\65\f\65\16\65\u01e1\13\65\3\65\7\65\u01e4\n\65\f\65\16\65\u01e7\13" +
			"\65\3\65\3\65\3\65\3\65\7\65\u01ed\n\65\f\65\16\65\u01f0\13\65\3\65\3" +
			"\65\3\66\3\66\3\66\5\66\u01f7\n\66\3\66\6\66\u01fa\n\66\r\66\16\66\u01fb" +
			"\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\5\67\u0207\n\67\38\58\u020a" +
			"\n8\38\68\u020d\n8\r8\168\u020e\39\39\69\u0213\n9\r9\169\u0214\3:\3:\5" +
			":\u0219\n:\3:\6:\u021c\n:\r:\16:\u021d\3:\3:\6:\u0222\n:\r:\16:\u0223" +
			"\3;\3;\3;\3;\3;\3<\3<\6<\u022d\n<\r<\16<\u022e\3<\3<\3<\3<\3=\3=\3=\3" +
			"=\3=\3>\3>\6>\u023c\n>\r>\16>\u023d\3>\3>\3>\3>\3?\3?\3?\6?\u0247\n?\r" +
			"?\16?\u0248\3?\3?\3@\3@\3@\3@\7@\u0251\n@\f@\16@\u0254\13@\3A\3A\3A\3" +
			"A\3A\5A\u025b\nA\3A\3A\3A\3A\3A\3A\3A\3A\3A\7A\u0266\nA\fA\16A\u0269\13" +
			"A\3B\6B\u026c\nB\rB\16B\u026d\3B\7B\u0271\nB\fB\16B\u0274\13B\3B\3B\3" +
			"C\6C\u0279\nC\rC\16C\u027a\3C\5C\u027e\nC\3C\3C\3D\3D\3D\3D\7D\u0286\n" +
			"D\fD\16D\u0289\13D\3D\3D\3D\3E\3E\3F\5F\u0291\nF\3F\3F\5F\u0295\nF\3G" +
			"\3G\3G\3G\3G\3G\3G\3H\3H\3H\3H\3H\3H\3H\3I\3I\3J\3J\3J\3J\3J\3K\3K\3K" +
			"\3L\3L\3L\3L\3L\3M\3M\3M\3N\3N\3N\3O\3O\6O\u02bc\nO\rO\16O\u02bd\3O\3" +
			"O\3O\3O\3P\3P\3P\3Q\3Q\6Q\u02c9\nQ\rQ\16Q\u02ca\3Q\3Q\3Q\3Q\3R\3R\3R\3" +
			"S\3S\3S\3S\3S\3T\3T\3T\3T\3T\3U\3U\3U\3V\3V\3V\3W\3W\3W\3W\3W\3W\3W\3" +
			"W\3W\3W\3W\3W\3W\3W\3X\3X\3X\3X\3X\3X\3X\3X\3X\3X\3X\3X\3Y\3Y\3Y\3Y\3" +
			"Y\3Y\3Y\3Y\3Y\3Y\3Y\3Y\3Y\3Y\3Y\3Y\3Y\3Y\3Y\3Z\3Z\3Z\3Z\3Z\3Z\3Z\3Z\3" +
			"Z\3Z\3Z\3Z\3Z\3Z\3Z\3Z\3Z\3[\3[\3\\\3\\\3]\3]\3^\3^\3_\3_\3`\3`\3a\3a" +
			"\3b\3b\3c\3c\3d\3d\4\u01d4\u0287\2e\7\3\t\4\13\5\r\6\17\7\21\b\23\t\25" +
			"\n\27\13\31\f\33\r\35\16\37\17!\20#\21%\22\'\23)\24+\25-\26/\27\61\30" +
			"\63\31\65\32\67\339\34;\35=\36?\37A C!E\"G#I$K%M&O\'Q(S)U*W+Y,[-]._/a" +
			"\60c\61e\62g\63i\64k\65m\66o\67q8s9u:w;y<{=}>\177?\u0081@\u0083A\u0085" +
			"B\u0087C\u0089D\u008bE\u008dF\u008fG\u0091H\u0093I\u0095J\u0097K\u0099" +
			"L\u009bM\u009dN\u009fO\u00a1P\u00a3Q\u00a5R\u00a7S\u00a9T\u00abU\u00ad" +
			"V\u00afW\u00b1X\u00b3Y\u00b5Z\u00b7[\u00b9\2\u00bb\2\u00bd\2\u00bf\2\u00c1" +
			"\2\u00c3\2\u00c5\2\u00c7\2\u00c9\2\u00cb\2\7\2\3\4\5\6\7\4\2\f\f\17\17" +
			"\4\2\13\13\"\"\4\2\u00b2\u00b2\u00bc\u00bc\3\2\62;\7\2C\\c|\u00c2\u00d8" +
			"\u00db\u00f8\u00fa\u0301\u0353\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2" +
			"\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3" +
			"\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2" +
			"\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2" +
			"/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2" +
			"\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2" +
			"G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3" +
			"\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2" +
			"\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2" +
			"m\3\2\2\2\2o\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\2u\3\2\2\2\2w\3\2\2\2\2y\3" +
			"\2\2\2\2{\3\2\2\2\2}\3\2\2\2\2\177\3\2\2\2\2\u0081\3\2\2\2\2\u0083\3\2" +
			"\2\2\2\u0085\3\2\2\2\2\u0087\3\2\2\2\2\u0089\3\2\2\2\2\u008b\3\2\2\2\2" +
			"\u008d\3\2\2\2\2\u008f\3\2\2\2\2\u0091\3\2\2\2\2\u0093\3\2\2\2\2\u0095" +
		"\3\2\2\2\3\u0097\3\2\2\2\3\u0099\3\2\2\2\3\u009b\3\2\2\2\3\u009d\3\2\2"+
			"\2\3\u009f\3\2\2\2\4\u00a1\3\2\2\2\4\u00a3\3\2\2\2\5\u00a5\3\2\2\2\5\u00a7" +
		"\3\2\2\2\6\u00a9\3\2\2\2\6\u00ab\3\2\2\2\6\u00ad\3\2\2\2\6\u00af\3\2\2"+
			"\2\6\u00b1\3\2\2\2\6\u00b3\3\2\2\2\6\u00b5\3\2\2\2\6\u00b7\3\2\2\2\7\u00cd" +
			"\3\2\2\2\t\u00d1\3\2\2\2\13\u00d5\3\2\2\2\r\u00d9\3\2\2\2\17\u00dd\3\2" +
			"\2\2\21\u00e0\3\2\2\2\23\u00e4\3\2\2\2\25\u00e7\3\2\2\2\27\u00ea\3\2\2" +
			"\2\31\u00ef\3\2\2\2\33\u00f4\3\2\2\2\35\u00f8\3\2\2\2\37\u0100\3\2\2\2" +
			"!\u0108\3\2\2\2#\u0111\3\2\2\2%\u011a\3\2\2\2\'\u0124\3\2\2\2)\u012e\3" +
			"\2\2\2+\u0136\3\2\2\2-\u013c\3\2\2\2/\u0145\3\2\2\2\61\u014d\3\2\2\2\63" +
			"\u0154\3\2\2\2\65\u015d\3\2\2\2\67\u015f\3\2\2\29\u0161\3\2\2\2;\u0163" +
			"\3\2\2\2=\u0165\3\2\2\2?\u0167\3\2\2\2A\u0169\3\2\2\2C\u016c\3\2\2\2E" +
			"\u016e\3\2\2\2G\u0170\3\2\2\2I\u0172\3\2\2\2K\u0174\3\2\2\2M\u0176\3\2" +
			"\2\2O\u0178\3\2\2\2Q\u017b\3\2\2\2S\u0181\3\2\2\2U\u0183\3\2\2\2W\u0188" +
			"\3\2\2\2Y\u0191\3\2\2\2[\u0199\3\2\2\2]\u019f\3\2\2\2_\u01a8\3\2\2\2a" +
			"\u01af\3\2\2\2c\u01b6\3\2\2\2e\u01be\3\2\2\2g\u01c3\3\2\2\2i\u01c8\3\2" +
			"\2\2k\u01ce\3\2\2\2m\u01df\3\2\2\2o\u01f3\3\2\2\2q\u0206\3\2\2\2s\u0209" +
			"\3\2\2\2u\u0210\3\2\2\2w\u0218\3\2\2\2y\u0225\3\2\2\2{\u022a\3\2\2\2}" +
			"\u0234\3\2\2\2\177\u0239\3\2\2\2\u0081\u0243\3\2\2\2\u0083\u024c\3\2\2" +
			"\2\u0085\u025a\3\2\2\2\u0087\u026b\3\2\2\2\u0089\u0278\3\2\2\2\u008b\u0281" +
			"\3\2\2\2\u008d\u028d\3\2\2\2\u008f\u0294\3\2\2\2\u0091\u0296\3\2\2\2\u0093" +
			"\u029d\3\2\2\2\u0095\u02a4\3\2\2\2\u0097\u02a6\3\2\2\2\u0099\u02ab\3\2" +
			"\2\2\u009b\u02ae\3\2\2\2\u009d\u02b3\3\2\2\2\u009f\u02b6\3\2\2\2\u00a1" +
			"\u02b9\3\2\2\2\u00a3\u02c3\3\2\2\2\u00a5\u02c6\3\2\2\2\u00a7\u02d0\3\2" +
			"\2\2\u00a9\u02d3\3\2\2\2\u00ab\u02d8\3\2\2\2\u00ad\u02dd\3\2\2\2\u00af" +
			"\u02e0\3\2\2\2\u00b1\u02e3\3\2\2\2\u00b3\u02f1\3\2\2\2\u00b5\u02fd\3\2" +
			"\2\2\u00b7\u0310\3\2\2\2\u00b9\u0321\3\2\2\2\u00bb\u0323\3\2\2\2\u00bd" +
			"\u0325\3\2\2\2\u00bf\u0327\3\2\2\2\u00c1\u0329\3\2\2\2\u00c3\u032b\3\2" +
			"\2\2\u00c5\u032d\3\2\2\2\u00c7\u032f\3\2\2\2\u00c9\u0331\3\2\2\2\u00cb" +
			"\u0333\3\2\2\2\u00cd\u00ce\7u\2\2\u00ce\u00cf\7w\2\2\u00cf\u00d0\7d\2" +
			"\2\u00d0\b\3\2\2\2\u00d1\u00d2\7w\2\2\u00d2\u00d3\7u\2\2\u00d3\u00d4\7" +
			"g\2\2\u00d4\n\3\2\2\2\u00d5\u00d6\7f\2\2\u00d6\u00d7\7u\2\2\u00d7\u00d8" +
			"\7n\2\2\u00d8\f\3\2\2\2\u00d9\u00da\7x\2\2\u00da\u00db\7c\2\2\u00db\u00dc" +
			"\7t\2\2\u00dc\16\3\2\2\2\u00dd\u00de\7c\2\2\u00de\u00df\7u\2\2\u00df\20" +
			"\3\2\2\2\u00e0\u00e1\7j\2\2\u00e1\u00e2\7c\2\2\u00e2\u00e3\7u\2\2\u00e3" +
			"\22\3\2\2\2\u00e4\u00e5\7q\2\2\u00e5\u00e6\7p\2\2\u00e6\24\3\2\2\2\u00e7" +
			"\u00e8\7k\2\2\u00e8\u00e9\7u\2\2\u00e9\26\3\2\2\2\u00ea\u00eb\7k\2\2\u00eb" +
			"\u00ec\7p\2\2\u00ec\u00ed\7v\2\2\u00ed\u00ee\7q\2\2\u00ee\30\3\2\2\2\u00ef" +
			"\u00f0\7y\2\2\u00f0\u00f1\7k\2\2\u00f1\u00f2\7v\2\2\u00f2\u00f3\7j\2\2" +
			"\u00f3\32\3\2\2\2\u00f4\u00f5\7c\2\2\u00f5\u00f6\7p\2\2\u00f6\u00f7\7" +
			"{\2\2\u00f7\34\3\2\2\2\u00f8\u00f9\7g\2\2\u00f9\u00fa\7z\2\2\u00fa\u00fb" +
			"\7v\2\2\u00fb\u00fc\7g\2\2\u00fc\u00fd\7p\2\2\u00fd\u00fe\7f\2\2\u00fe" +
			"\u00ff\7u\2\2\u00ff\36\3\2\2\2\u0100\u0101\7e\2\2\u0101\u0102\7q\2\2\u0102" +
			"\u0103\7p\2\2\u0103\u0104\7e\2\2\u0104\u0105\7g\2\2\u0105\u0106\7r\2\2" +
			"\u0106\u0107\7v\2\2\u0107 \3\2\2\2\u0108\u0109\7c\2\2\u0109\u010a\7d\2" +
			"\2\u010a\u010b\7u\2\2\u010b\u010c\7v\2\2\u010c\u010d\7t\2\2\u010d\u010e" +
			"\7c\2\2\u010e\u010f\7e\2\2\u010f\u0110\7v\2\2\u0110\"\3\2\2\2\u0111\u0112" +
			"\7v\2\2\u0112\u0113\7g\2\2\u0113\u0114\7t\2\2\u0114\u0115\7o\2\2\u0115" +
			"\u0116\7k\2\2\u0116\u0117\7p\2\2\u0117\u0118\7c\2\2\u0118\u0119\7n\2\2" +
			"\u0119$\3\2\2\2\u011a\u011b\7e\2\2\u011b\u011c\7q\2\2\u011c\u011d\7o\2" +
			"\2\u011d\u011e\7r\2\2\u011e\u011f\7q\2\2\u011f\u0120\7p\2\2\u0120\u0121" +
			"\7g\2\2\u0121\u0122\7p\2\2\u0122\u0123\7v\2\2\u0123&\3\2\2\2\u0124\u0125" +
			"\7r\2\2\u0125\u0126\7t\2\2\u0126\u0127\7q\2\2\u0127\u0128\7v\2\2\u0128" +
			"\u0129\7q\2\2\u0129\u012a\7v\2\2\u012a\u012b\7{\2\2\u012b\u012c\7r\2\2" +
			"\u012c\u012d\7g\2\2\u012d(\3\2\2\2\u012e\u012f\7h\2\2\u012f\u0130\7g\2" +
			"\2\u0130\u0131\7c\2\2\u0131\u0132\7v\2\2\u0132\u0133\7w\2\2\u0133\u0134" +
			"\7t\2\2\u0134\u0135\7g\2\2\u0135*\3\2\2\2\u0136\u0137\7h\2\2\u0137\u0138" +
			"\7k\2\2\u0138\u0139\7p\2\2\u0139\u013a\7c\2\2\u013a\u013b\7n\2\2\u013b" +
			",\3\2\2\2\u013c\u013d\7g\2\2\u013d\u013e\7p\2\2\u013e\u013f\7e\2\2\u013f" +
			"\u0140\7n\2\2\u0140\u0141\7q\2\2\u0141\u0142\7u\2\2\u0142\u0143\7g\2\2" +
			"\u0143\u0144\7f\2\2\u0144.\3\2\2\2\u0145\u0146\7r\2\2\u0146\u0147\7t\2" +
			"\2\u0147\u0148\7k\2\2\u0148\u0149\7x\2\2\u0149\u014a\7c\2\2\u014a\u014b" +
			"\7v\2\2\u014b\u014c\7g\2\2\u014c\60\3\2\2\2\u014d\u014e\7p\2\2\u014e\u014f" +
			"\7c\2\2\u014f\u0150\7v\2\2\u0150\u0151\7k\2\2\u0151\u0152\7x\2\2\u0152" +
			"\u0153\7g\2\2\u0153\62\3\2\2\2\u0154\u0155\7h\2\2\u0155\u0156\7t\2\2\u0156" +
			"\u0157\7c\2\2\u0157\u0158\7i\2\2\u0158\u0159\7o\2\2\u0159\u015a\7g\2\2" +
			"\u015a\u015b\7p\2\2\u015b\u015c\7v\2\2\u015c\64\3\2\2\2\u015d\u015e\7" +
			"*\2\2\u015e\66\3\2\2\2\u015f\u0160\7+\2\2\u01608\3\2\2\2\u0161\u0162\7" +
			"]\2\2\u0162:\3\2\2\2\u0163\u0164\7_\2\2\u0164<\3\2\2\2\u0165\u0166\7}" +
			"\2\2\u0166>\3\2\2\2\u0167\u0168\7\177\2\2\u0168@\3\2\2\2\u0169\u016a\7" +
			"@\2\2\u016a\u016b\b\37\2\2\u016bB\3\2\2\2\u016c\u016d\7>\2\2\u016dD\3" +
			"\2\2\2\u016e\u016f\7%\2\2\u016fF\3\2\2\2\u0170\u0171\7<\2\2\u0171H\3\2" +
			"\2\2\u0172\u0173\7.\2\2\u0173J\3\2\2\2\u0174\u0175\7\60\2\2\u0175L\3\2" +
			"\2\2\u0176\u0177\7?\2\2\u0177N\3\2\2\2\u0178\u0179\7,\2\2\u0179P\3\2\2" +
			"\2\u017a\u017c\7=\2\2\u017b\u017a\3\2\2\2\u017c\u017d\3\2\2\2\u017d\u017b" +
			"\3\2\2\2\u017d\u017e\3\2\2\2\u017e\u017f\3\2\2\2\u017f\u0180\b\'\3\2\u0180" +
			"R\3\2\2\2\u0181\u0182\7-\2\2\u0182T\3\2\2\2\u0183\u0184\7y\2\2\u0184\u0185" +
			"\7q\2\2\u0185\u0186\7t\2\2\u0186\u0187\7f\2\2\u0187V\3\2\2\2\u0188\u0189" +
			"\7t\2\2\u0189\u018a\7g\2\2\u018a\u018b\7u\2\2\u018b\u018c\7q\2\2\u018c" +
			"\u018d\7w\2\2\u018d\u018e\7t\2\2\u018e\u018f\7e\2\2\u018f\u0190\7g\2\2" +
			"\u0190X\3\2\2\2\u0191\u0192\7k\2\2\u0192\u0193\7p\2\2\u0193\u0194\7v\2" +
			"\2\u0194\u0195\7g\2\2\u0195\u0196\7i\2\2\u0196\u0197\7g\2\2\u0197\u0198" +
			"\7t\2\2\u0198Z\3\2\2\2\u0199\u019a\7v\2\2\u019a\u019b\7w\2\2\u019b\u019c" +
			"\7r\2\2\u019c\u019d\7n\2\2\u019d\u019e\7g\2\2\u019e\\\3\2\2\2\u019f\u01a0" +
			"\7h\2\2\u01a0\u01a1\7w\2\2\u01a1\u01a2\7p\2\2\u01a2\u01a3\7e\2\2\u01a3" +
			"\u01a4\7v\2\2\u01a4\u01a5\7k\2\2\u01a5\u01a6\7q\2\2\u01a6\u01a7\7p\2\2" +
			"\u01a7^\3\2\2\2\u01a8\u01a9\7f\2\2\u01a9\u01aa\7q\2\2\u01aa\u01ab\7w\2" +
			"\2\u01ab\u01ac\7d\2\2\u01ac\u01ad\7n\2\2\u01ad\u01ae\7g\2\2\u01ae`\3\2" +
			"\2\2\u01af\u01b0\7u\2\2\u01b0\u01b1\7v\2\2\u01b1\u01b2\7t\2\2\u01b2\u01b3" +
			"\7k\2\2\u01b3\u01b4\7p\2\2\u01b4\u01b5\7i\2\2\u01b5b\3\2\2\2\u01b6\u01b7" +
			"\7d\2\2\u01b7\u01b8\7q\2\2\u01b8\u01b9\7q\2\2\u01b9\u01ba\7n\2\2\u01ba" +
			"\u01bb\7g\2\2\u01bb\u01bc\7c\2\2\u01bc\u01bd\7p\2\2\u01bdd\3\2\2\2\u01be" +
			"\u01bf\7f\2\2\u01bf\u01c0\7c\2\2\u01c0\u01c1\7v\2\2\u01c1\u01c2\7g\2\2" +
			"\u01c2f\3\2\2\2\u01c3\u01c4\7v\2\2\u01c4\u01c5\7k\2\2\u01c5\u01c6\7o\2" +
			"\2\u01c6\u01c7\7g\2\2\u01c7h\3\2\2\2\u01c8\u01c9\7g\2\2\u01c9\u01ca\7" +
			"o\2\2\u01ca\u01cb\7r\2\2\u01cb\u01cc\7v\2\2\u01cc\u01cd\7{\2\2\u01cdj" +
			"\3\2\2\2\u01ce\u01cf\7\61\2\2\u01cf\u01d0\7,\2\2\u01d0\u01d4\3\2\2\2\u01d1" +
			"\u01d3\13\2\2\2\u01d2\u01d1\3\2\2\2\u01d3\u01d6\3\2\2\2\u01d4\u01d5\3" +
			"\2\2\2\u01d4\u01d2\3\2\2\2\u01d5\u01d7\3\2\2\2\u01d6\u01d4\3\2\2\2\u01d7" +
			"\u01d8\7,\2\2\u01d8\u01d9\7\61\2\2\u01d9\u01da\3\2\2\2\u01da\u01db\b\64" +
			"\4\2\u01dbl\3\2\2\2\u01dc\u01de\t\2\2\2\u01dd\u01dc\3\2\2\2\u01de\u01e1" +
			"\3\2\2\2\u01df\u01dd\3\2\2\2\u01df\u01e0\3\2\2\2\u01e0\u01e5\3\2\2\2\u01e1" +
			"\u01df\3\2\2\2\u01e2\u01e4\t\3\2\2\u01e3\u01e2\3\2\2\2\u01e4\u01e7\3\2" +
			"\2\2\u01e5\u01e3\3\2\2\2\u01e5\u01e6\3\2\2\2\u01e6\u01e8\3\2\2\2\u01e7" +
			"\u01e5\3\2\2\2\u01e8\u01e9\7\61\2\2\u01e9\u01ea\7\61\2\2\u01ea\u01ee\3" +
			"\2\2\2\u01eb\u01ed\n\2\2\2\u01ec\u01eb\3\2\2\2\u01ed\u01f0\3\2\2\2\u01ee" +
			"\u01ec\3\2\2\2\u01ee\u01ef\3\2\2\2\u01ef\u01f1\3\2\2\2\u01f0\u01ee\3\2" +
			"\2\2\u01f1\u01f2\b\65\4\2\u01f2n\3\2\2\2\u01f3\u01f6\7G\2\2\u01f4\u01f7" +
			"\5S(\2\u01f5\u01f7\5\u00c5a\2\u01f6\u01f4\3\2\2\2\u01f6\u01f5\3\2\2\2" +
			"\u01f6\u01f7\3\2\2\2\u01f7\u01f9\3\2\2\2\u01f8\u01fa\5\u00c9c\2\u01f9" +
			"\u01f8\3\2\2\2\u01fa\u01fb\3\2\2\2\u01fb\u01f9\3\2\2\2\u01fb\u01fc\3\2" +
			"\2\2\u01fcp\3\2\2\2\u01fd\u01fe\7v\2\2\u01fe\u01ff\7t\2\2\u01ff\u0200" +
			"\7w\2\2\u0200\u0207\7g\2\2\u0201\u0202\7h\2\2\u0202\u0203\7c\2\2\u0203" +
			"\u0204\7n\2\2\u0204\u0205\7u\2\2\u0205\u0207\7g\2\2\u0206\u01fd\3\2\2" +
			"\2\u0206\u0201\3\2\2\2\u0207r\3\2\2\2\u0208\u020a\5S(\2\u0209\u0208\3" +
			"\2\2\2\u0209\u020a\3\2\2\2\u020a\u020c\3\2\2\2\u020b\u020d\5\u00c9c\2" +
			"\u020c\u020b\3\2\2\2\u020d\u020e\3\2\2\2\u020e\u020c\3\2\2\2\u020e\u020f" +
			"\3\2\2\2\u020ft\3\2\2\2\u0210\u0212\5\u00c5a\2\u0211\u0213\5\u00c9c\2" +
			"\u0212\u0211\3\2\2\2\u0213\u0214\3\2\2\2\u0214\u0212\3\2\2\2\u0214\u0215" +
			"\3\2\2\2\u0215v\3\2\2\2\u0216\u0219\5S(\2\u0217\u0219\5\u00c5a\2\u0218" +
			"\u0216\3\2\2\2\u0218\u0217\3\2\2\2\u0218\u0219\3\2\2\2\u0219\u021b\3\2" +
			"\2\2\u021a\u021c\5\u00c9c\2\u021b\u021a\3\2\2\2\u021c\u021d\3\2\2\2\u021d" +
			"\u021b\3\2\2\2\u021d\u021e\3\2\2\2\u021e\u021f\3\2\2\2\u021f\u0221\5K" +
			"$\2\u0220\u0222\5\u00c9c\2\u0221\u0220\3\2\2\2\u0222\u0223\3\2\2\2\u0223" +
			"\u0221\3\2\2\2\u0223\u0224\3\2\2\2\u0224x\3\2\2\2\u0225\u0226\7$\2\2\u0226" +
			"\u0227\b;\5\2\u0227\u0228\3\2\2\2\u0228\u0229\b;\6\2\u0229z\3\2\2\2\u022a" +
			"\u022c\5M%\2\u022b\u022d\5M%\2\u022c\u022b\3\2\2\2\u022d\u022e\3\2\2\2" +
			"\u022e\u022c\3\2\2\2\u022e\u022f\3\2\2\2\u022f\u0230\3\2\2\2\u0230\u0231" +
			"\b<\7\2\u0231\u0232\3\2\2\2\u0232\u0233\b<\b\2\u0233|\3\2\2\2\u0234\u0235" +
			"\7)\2\2\u0235\u0236\b=\t\2\u0236\u0237\3\2\2\2\u0237\u0238\b=\n\2\u0238" +
			"~\3\2\2\2\u0239\u023b\5\u00c5a\2\u023a\u023c\5\u00c5a\2\u023b\u023a\3" +
			"\2\2\2\u023c\u023d\3\2\2\2\u023d\u023b\3\2\2\2\u023d\u023e\3\2\2\2\u023e" +
			"\u023f\3\2\2\2\u023f\u0240\b>\13\2\u0240\u0241\3\2\2\2\u0241\u0242\b>" +
			"\f\2\u0242\u0080\3\2\2\2\u0243\u0246\5O&\2\u0244\u0247\5\u00c9c\2\u0245" +
			"\u0247\5\u00cbd\2\u0246\u0244\3\2\2\2\u0246\u0245\3\2\2\2\u0247\u0248" +
			"\3\2\2\2\u0248\u0246\3\2\2\2\u0248\u0249\3\2\2\2\u0249\u024a\3\2\2\2\u024a" +
			"\u024b\5O&\2\u024b\u0082\3\2\2\2\u024c\u0252\5\u00cbd\2\u024d\u0251\5" +
			"\u00c9c\2\u024e\u0251\5\u00cbd\2\u024f\u0251\5\u00c5a\2\u0250\u024d\3" +
			"\2\2\2\u0250\u024e\3\2\2\2\u0250\u024f\3\2\2\2\u0251\u0254\3\2\2\2\u0252" +
			"\u0250\3\2\2\2\u0252\u0253\3\2\2\2\u0253\u0084\3\2\2\2\u0254\u0252\3\2" +
			"\2\2\u0255\u025b\5\u00cbd\2\u0256\u025b\5\u00bd]\2\u0257\u025b\5\u00b9" +
			"[\2\u0258\u025b\5\u00bb\\\2\u0259\u025b\5\u00bf^\2\u025a\u0255\3\2\2\2" +
			"\u025a\u0256\3\2\2\2\u025a\u0257\3\2\2\2\u025a\u0258\3\2\2\2\u025a\u0259" +
			"\3\2\2\2\u025b\u0267\3\2\2\2\u025c\u0266\5\u00c7b\2\u025d\u0266\5\u00c1" +
			"_\2\u025e\u0266\5\u00c3`\2\u025f\u0266\5\u00bd]\2\u0260\u0266\5\u00b9" +
			"[\2\u0261\u0266\5\u00bb\\\2\u0262\u0266\5\u00bf^\2\u0263\u0266\5\u00cb" +
			"d\2\u0264\u0266\5\u00c9c\2\u0265\u025c\3\2\2\2\u0265\u025d\3\2\2\2\u0265" +
			"\u025e\3\2\2\2\u0265\u025f\3\2\2\2\u0265\u0260\3\2\2\2\u0265\u0261\3\2" +
			"\2\2\u0265\u0262\3\2\2\2\u0265\u0263\3\2\2\2\u0265\u0264\3\2\2\2\u0266" +
			"\u0269\3\2\2\2\u0267\u0265\3\2\2\2\u0267\u0268\3\2\2\2\u0268\u0086\3\2" +
			"\2\2\u0269\u0267\3\2\2\2\u026a\u026c\5\u008fF\2\u026b\u026a\3\2\2\2\u026c" +
			"\u026d\3\2\2\2\u026d\u026b\3\2\2\2\u026d\u026e\3\2\2\2\u026e\u0272\3\2" +
			"\2\2\u026f\u0271\5\u008dE\2\u0270\u026f\3\2\2\2\u0271\u0274\3\2\2\2\u0272" +
			"\u0270\3\2\2\2\u0272\u0273\3\2\2\2\u0273\u0275\3\2\2\2\u0274\u0272\3\2" +
			"\2\2\u0275\u0276\bB\r\2\u0276\u0088\3\2\2\2\u0277\u0279\5\u008dE\2\u0278" +
			"\u0277\3\2\2\2\u0279\u027a\3\2\2\2\u027a\u0278\3\2\2\2\u027a\u027b\3\2" +
			"\2\2\u027b\u027d\3\2\2\2\u027c\u027e\7\2\2\3\u027d\u027c\3\2\2\2\u027d" +
			"\u027e\3\2\2\2\u027e\u027f\3\2\2\2\u027f\u0280\bC\16\2\u0280\u008a\3\2" +
			"\2\2\u0281\u0282\7#\2\2\u0282\u0283\7#\2\2\u0283\u0287\3\2\2\2\u0284\u0286" +
			"\13\2\2\2\u0285\u0284\3\2\2\2\u0286\u0289\3\2\2\2\u0287\u0288\3\2\2\2" +
			"\u0287\u0285\3\2\2\2\u0288\u028a\3\2\2\2\u0289\u0287\3\2\2\2\u028a\u028b" +
			"\5\u0087B\2\u028b\u028c\bD\17\2\u028c\u008c\3\2\2\2\u028d\u028e\t\3\2" +
			"\2\u028e\u008e\3\2\2\2\u028f\u0291\7\17\2\2\u0290\u028f\3\2\2\2\u0290" +
			"\u0291\3\2\2\2\u0291\u0292\3\2\2\2\u0292\u0295\7\f\2\2\u0293\u0295\7\17" +
			"\2\2\u0294\u0290\3\2\2\2\u0294\u0293\3\2\2\2\u0295\u0090\3\2\2\2\u0296" +
			"\u0297\7k\2\2\u0297\u0298\7p\2\2\u0298\u0299\7f\2\2\u0299\u029a\7g\2\2" +
			"\u029a\u029b\7p\2\2\u029b\u029c\7v\2\2\u029c\u0092\3\2\2\2\u029d\u029e" +
			"\7f\2\2\u029e\u029f\7g\2\2\u029f\u02a0\7f\2\2\u02a0\u02a1\7g\2\2\u02a1" +
			"\u02a2\7p\2\2\u02a2\u02a3\7v\2\2\u02a3\u0094\3\2\2\2\u02a4\u02a5\13\2" +
			"\2\2\u02a5\u0096\3\2\2\2\u02a6\u02a7\7$\2\2\u02a7\u02a8\bJ\20\2\u02a8" +
			"\u02a9\3\2\2\2\u02a9\u02aa\bJ\21\2\u02aa\u0098\3\2\2\2\u02ab\u02ac\7$" +
			"\2\2\u02ac\u02ad\bK\22\2\u02ad\u009a\3\2\2\2\u02ae\u02af\7^\2\2\u02af" +
			"\u02b0\7$\2\2\u02b0\u02b1\3\2\2\2\u02b1\u02b2\bL\23\2\u02b2\u009c\3\2" +
			"\2\2\u02b3\u02b4\7^\2\2\u02b4\u02b5\bM\24\2\u02b5\u009e\3\2\2\2\u02b6" +
			"\u02b7\13\2\2\2\u02b7\u02b8\bN\25\2\u02b8\u00a0\3\2\2\2\u02b9\u02bb\5" +
			"M%\2\u02ba\u02bc\5M%\2\u02bb\u02ba\3\2\2\2\u02bc\u02bd\3\2\2\2\u02bd\u02bb" +
			"\3\2\2\2\u02bd\u02be\3\2\2\2\u02be\u02bf\3\2\2\2\u02bf\u02c0\bO\26\2\u02c0" +
			"\u02c1\3\2\2\2\u02c1\u02c2\bO\21\2\u02c2\u00a2\3\2\2\2\u02c3\u02c4\13" +
			"\2\2\2\u02c4\u02c5\bP\27\2\u02c5\u00a4\3\2\2\2\u02c6\u02c8\5\u00c5a\2" +
			"\u02c7\u02c9\5\u00c5a\2\u02c8\u02c7\3\2\2\2\u02c9\u02ca\3\2\2\2\u02ca" +
			"\u02c8\3\2\2\2\u02ca\u02cb\3\2\2\2\u02cb\u02cc\3\2\2\2\u02cc\u02cd\bQ" +
			"\30\2\u02cd\u02ce\3\2\2\2\u02ce\u02cf\bQ\21\2\u02cf\u00a6\3\2\2\2\u02d0" +
			"\u02d1\13\2\2\2\u02d1\u02d2\bR\31\2\u02d2\u00a8\3\2\2\2\u02d3\u02d4\7" +
			")\2\2\u02d4\u02d5\bS\32\2\u02d5\u02d6\3\2\2\2\u02d6\u02d7\bS\21\2\u02d7" +
			"\u00aa\3\2\2\2\u02d8\u02d9\7^\2\2\u02d9\u02da\7)\2\2\u02da\u02db\3\2\2" +
			"\2\u02db\u02dc\bT\33\2\u02dc\u00ac\3\2\2\2\u02dd\u02de\7^\2\2\u02de\u02df" +
			"\bU\34\2\u02df\u00ae\3\2\2\2\u02e0\u02e1\13\2\2\2\u02e1\u02e2\bV\35\2" +
			"\u02e2\u00b0\3\2\2\2\u02e3\u02e4\7\'\2\2\u02e4\u02e5\7S\2\2\u02e5\u02e6" +
			"\7W\2\2\u02e6\u02e7\7Q\2\2\u02e7\u02e8\7V\2\2\u02e8\u02e9\7G\2\2\u02e9" +
			"\u02ea\7a\2\2\u02ea\u02eb\7D\2\2\u02eb\u02ec\7G\2\2\u02ec\u02ed\7I\2\2" +
			"\u02ed\u02ee\7K\2\2\u02ee\u02ef\7P\2\2\u02ef\u02f0\7\'\2\2\u02f0\u00b2" +
			"\3\2\2\2\u02f1\u02f2\7\'\2\2\u02f2\u02f3\7S\2\2\u02f3\u02f4\7W\2\2\u02f4" +
			"\u02f5\7Q\2\2\u02f5\u02f6\7V\2\2\u02f6\u02f7\7G\2\2\u02f7\u02f8\7a\2\2" +
			"\u02f8\u02f9\7G\2\2\u02f9\u02fa\7P\2\2\u02fa\u02fb\7F\2\2\u02fb\u02fc" +
			"\7\'\2\2\u02fc\u00b4\3\2\2\2\u02fd\u02fe\7\'\2\2\u02fe\u02ff\7G\2\2\u02ff" +
			"\u0300\7Z\2\2\u0300\u0301\7R\2\2\u0301\u0302\7T\2\2\u0302\u0303\7G\2\2" +
			"\u0303\u0304\7U\2\2\u0304\u0305\7U\2\2\u0305\u0306\7K\2\2\u0306\u0307" +
			"\7Q\2\2\u0307\u0308\7P\2\2\u0308\u0309\7a\2\2\u0309\u030a\7D\2\2\u030a" +
			"\u030b\7G\2\2\u030b\u030c\7I\2\2\u030c\u030d\7K\2\2\u030d\u030e\7P\2\2" +
			"\u030e\u030f\7\'\2\2\u030f\u00b6\3\2\2\2\u0310\u0311\7\'\2\2\u0311\u0312" +
			"\7G\2\2\u0312\u0313\7Z\2\2\u0313\u0314\7R\2\2\u0314\u0315\7T\2\2\u0315" +
			"\u0316\7G\2\2\u0316\u0317\7U\2\2\u0317\u0318\7U\2\2\u0318\u0319\7K\2\2" +
			"\u0319\u031a\7Q\2\2\u031a\u031b\7P\2\2\u031b\u031c\7a\2\2\u031c\u031d" +
			"\7G\2\2\u031d\u031e\7P\2\2\u031e\u031f\7F\2\2\u031f\u0320\7\'\2\2\u0320" +
			"\u00b8\3\2\2\2\u0321\u0322\7&\2\2\u0322\u00ba\3\2\2\2\u0323\u0324\7\u20ae" +
			"\2\2\u0324\u00bc\3\2\2\2\u0325\u0326\7\'\2\2\u0326\u00be\3\2\2\2\u0327" +
			"\u0328\t\4\2\2\u0328\u00c0\3\2\2\2\u0329\u032a\7\u00b9\2\2\u032a\u00c2" +
			"\3\2\2\2\u032b\u032c\7\61\2\2\u032c\u00c4\3\2\2\2\u032d\u032e\7/\2\2\u032e" +
			"\u00c6\3\2\2\2\u032f\u0330\7a\2\2\u0330\u00c8\3\2\2\2\u0331\u0332\t\5" +
			"\2\2\u0332\u00ca\3\2\2\2\u0333\u0334\t\6\2\2\u0334\u00cc\3\2\2\2(\2\3" +
			"\4\5\6\u017d\u01d4\u01df\u01e3\u01e5\u01ee\u01f6\u01fb\u0206\u0209\u020e" +
			"\u0214\u0218\u021d\u0223\u022e\u023d\u0246\u0248\u0250\u0252\u025a\u0265" +
			"\u0267\u026d\u0272\u027a\u027d\u0287\u0290\u0294\u02bd\u02ca\36\3\37\2" +
			"\3\'\3\b\2\2\3;\4\4\3\2\3<\5\4\4\2\3=\6\4\6\2\3>\7\4\5\2\3B\b\2\3\2\3" +
			"D\t\3J\n\4\2\2\3K\13\3L\f\3M\r\3N\16\3O\17\3P\20\3Q\21\3R\22\3S\23\3T" +
			"\24\3U\25\3V\26";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}