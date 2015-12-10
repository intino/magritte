// Generated from /Users/oroncal/workspace/tara/language/grammar/src/tara/lang/lexicon/TaraLexer.g4 by ANTLR 4.5.1
package tara.lang.grammar;
import tara.compiler.parser.antlr.BlockManager;
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
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		SUB=1, USE=2, DSL=3, VAR=4, AS=5, HAS=6, ON=7, IS=8, INTO=9, WITH=10, 
		ANY=11, EXTENDS=12, CONCEPT=13, ABSTRACT=14, TERMINAL=15, COMPONENT=16, 
		NAMED=17, PROTOTYPE=18, PROFILER=19, FEATURE=20, FINAL=21, ENCLOSED=22, 
		PRIVATE=23, NATIVE=24, LEFT_PARENTHESIS=25, RIGHT_PARENTHESIS=26, LEFT_SQUARE=27, 
		RIGHT_SQUARE=28, LEFT_CURLY=29, RIGHT_CURLY=30, INLINE=31, CLOSE_INLINE=32, 
		HASHTAG=33, COLON=34, COMMA=35, DOT=36, EQUALS=37, STAR=38, SEMICOLON=39, 
		PLUS=40, WORD=41, RESOURCE=42, INT_TYPE=43, TUPLE_TYPE=44, FUNCTION_TYPE=45, 
		DOUBLE_TYPE=46, STRING_TYPE=47, BOOLEAN_TYPE=48, DATE_TYPE=49, TIME_TYPE=50, 
		EMPTY=51, BLOCK_COMMENT=52, LINE_COMMENT=53, SCIENCE_NOT=54, BOOLEAN_VALUE=55, 
		NATURAL_VALUE=56, NEGATIVE_VALUE=57, DOUBLE_VALUE=58, APHOSTROPHE=59, 
		STRING_MULTILINE=60, SINGLE_QUOTE=61, EXPRESSION_MULTILINE=62, ANCHOR_VALUE=63, 
		IDENTIFIER=64, MEASURE_VALUE=65, NEWLINE=66, SPACES=67, DOC=68, SP=69, 
		NL=70, NEW_LINE_INDENT=71, DEDENT=72, UNKNOWN_TOKEN=73, QUOTE=74, Q=75, 
		SLASH_Q=76, SLASH=77, CHARACTER=78, M_QUOTE=79, M_CHARACTER=80, ME_STRING_MULTILINE=81, 
		ME_CHARACTER=82, E_QUOTE=83, E_SLASH_Q=84, E_SLASH=85, E_CHARACTER=86, 
		QUOTE_BEGIN=87, QUOTE_END=88, EXPRESSION_BEGIN=89, EXPRESSION_END=90;
	public static final int QUOTED = 1;
	public static final int MULTILINE = 2;
	public static final int EXPRESSION_MULTILINE_MODE = 3;
	public static final int EXPRESSION_QUOTED = 4;
	public static String[] modeNames = {
		"DEFAULT_MODE", "QUOTED", "MULTILINE", "EXPRESSION_MULTILINE_MODE", "EXPRESSION_QUOTED"
	};

	public static final String[] ruleNames = {
		"SUB", "USE", "DSL", "VAR", "AS", "HAS", "ON", "IS", "INTO", "WITH", "ANY", 
		"EXTENDS", "CONCEPT", "ABSTRACT", "TERMINAL", "COMPONENT", "NAMED", "PROTOTYPE", 
		"PROFILER", "FEATURE", "FINAL", "ENCLOSED", "PRIVATE", "NATIVE", "LEFT_PARENTHESIS", 
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
		"'component'", "'named'", "'prototype'", "'profiler'", "'feature'", "'final'", 
		"'enclosed'", "'private'", "'native'", "'('", "')'", "'['", "']'", "'{'", 
		"'}'", "'>'", "'<'", "'#'", "':'", "','", "'.'", "'='", "'*'", null, "'+'", 
		"'word'", "'file'", "'integer'", "'tuple'", "'function'", "'double'", 
		"'string'", "'boolean'", "'date'", "'time'", "'empty'", null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, "'indent'", "'dedent'", null, null, "'\"'", "'\\\"'", 
		null, null, null, null, null, null, null, "'\\''", null, null, "'%QUOTE_BEGIN%'", 
		"'%QUOTE_END%'", "'%EXPRESSION_BEGIN%'", "'%EXPRESSION_END%'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "SUB", "USE", "DSL", "VAR", "AS", "HAS", "ON", "IS", "INTO", "WITH", 
		"ANY", "EXTENDS", "CONCEPT", "ABSTRACT", "TERMINAL", "COMPONENT", "NAMED", 
		"PROTOTYPE", "PROFILER", "FEATURE", "FINAL", "ENCLOSED", "PRIVATE", "NATIVE", 
		"LEFT_PARENTHESIS", "RIGHT_PARENTHESIS", "LEFT_SQUARE", "RIGHT_SQUARE", 
		"LEFT_CURLY", "RIGHT_CURLY", "INLINE", "CLOSE_INLINE", "HASHTAG", "COLON", 
		"COMMA", "DOT", "EQUALS", "STAR", "SEMICOLON", "PLUS", "WORD", "RESOURCE", 
		"INT_TYPE", "TUPLE_TYPE", "FUNCTION_TYPE", "DOUBLE_TYPE", "STRING_TYPE", 
		"BOOLEAN_TYPE", "DATE_TYPE", "TIME_TYPE", "EMPTY", "BLOCK_COMMENT", "LINE_COMMENT", 
		"SCIENCE_NOT", "BOOLEAN_VALUE", "NATURAL_VALUE", "NEGATIVE_VALUE", "DOUBLE_VALUE", 
		"APHOSTROPHE", "STRING_MULTILINE", "SINGLE_QUOTE", "EXPRESSION_MULTILINE", 
		"ANCHOR_VALUE", "IDENTIFIER", "MEASURE_VALUE", "NEWLINE", "SPACES", "DOC", 
		"SP", "NL", "NEW_LINE_INDENT", "DEDENT", "UNKNOWN_TOKEN", "QUOTE", "Q", 
		"SLASH_Q", "SLASH", "CHARACTER", "M_QUOTE", "M_CHARACTER", "ME_STRING_MULTILINE", 
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
		case 30:
			INLINE_action((RuleContext)_localctx, actionIndex);
			break;
		case 38:
			SEMICOLON_action((RuleContext)_localctx, actionIndex);
			break;
		case 58:
			APHOSTROPHE_action((RuleContext)_localctx, actionIndex);
			break;
		case 59:
			STRING_MULTILINE_action((RuleContext)_localctx, actionIndex);
			break;
		case 60:
			SINGLE_QUOTE_action((RuleContext)_localctx, actionIndex);
			break;
		case 61:
			EXPRESSION_MULTILINE_action((RuleContext)_localctx, actionIndex);
			break;
		case 65:
			NEWLINE_action((RuleContext)_localctx, actionIndex);
			break;
		case 67:
			DOC_action((RuleContext)_localctx, actionIndex);
			break;
		case 73:
			QUOTE_action((RuleContext)_localctx, actionIndex);
			break;
		case 74:
			Q_action((RuleContext)_localctx, actionIndex);
			break;
		case 75:
			SLASH_Q_action((RuleContext)_localctx, actionIndex);
			break;
		case 76:
			SLASH_action((RuleContext)_localctx, actionIndex);
			break;
		case 77:
			CHARACTER_action((RuleContext)_localctx, actionIndex);
			break;
		case 78:
			M_QUOTE_action((RuleContext)_localctx, actionIndex);
			break;
		case 79:
			M_CHARACTER_action((RuleContext)_localctx, actionIndex);
			break;
		case 80:
			ME_STRING_MULTILINE_action((RuleContext)_localctx, actionIndex);
			break;
		case 81:
			ME_CHARACTER_action((RuleContext)_localctx, actionIndex);
			break;
		case 82:
			E_QUOTE_action((RuleContext)_localctx, actionIndex);
			break;
		case 83:
			E_SLASH_Q_action((RuleContext)_localctx, actionIndex);
			break;
		case 84:
			E_SLASH_action((RuleContext)_localctx, actionIndex);
			break;
		case 85:
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\\\u0339\b\1\b\1\b"+
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
		"\t]\4^\t^\4_\t_\4`\t`\4a\ta\4b\tb\4c\tc\4d\td\4e\te\3\2\3\2\3\2\3\2\3"+
		"\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\7"+
		"\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3"+
		"\13\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26"+
		"\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30"+
		"\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\33"+
		"\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3 \3!\3!\3\"\3\"\3"+
		"#\3#\3$\3$\3%\3%\3&\3&\3\'\3\'\3(\6(\u0184\n(\r(\16(\u0185\3(\3(\3)\3"+
		")\3*\3*\3*\3*\3*\3+\3+\3+\3+\3+\3,\3,\3,\3,\3,\3,\3,\3,\3-\3-\3-\3-\3"+
		"-\3-\3.\3.\3.\3.\3.\3.\3.\3.\3.\3/\3/\3/\3/\3/\3/\3/\3\60\3\60\3\60\3"+
		"\60\3\60\3\60\3\60\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\62\3\62\3"+
		"\62\3\62\3\62\3\63\3\63\3\63\3\63\3\63\3\64\3\64\3\64\3\64\3\64\3\64\3"+
		"\65\3\65\3\65\3\65\7\65\u01d7\n\65\f\65\16\65\u01da\13\65\3\65\3\65\3"+
		"\65\3\65\3\65\3\66\7\66\u01e2\n\66\f\66\16\66\u01e5\13\66\3\66\7\66\u01e8"+
		"\n\66\f\66\16\66\u01eb\13\66\3\66\3\66\3\66\3\66\7\66\u01f1\n\66\f\66"+
		"\16\66\u01f4\13\66\3\66\3\66\3\67\3\67\3\67\5\67\u01fb\n\67\3\67\6\67"+
		"\u01fe\n\67\r\67\16\67\u01ff\38\38\38\38\38\38\38\38\38\58\u020b\n8\3"+
		"9\59\u020e\n9\39\69\u0211\n9\r9\169\u0212\3:\3:\6:\u0217\n:\r:\16:\u0218"+
		"\3;\3;\5;\u021d\n;\3;\6;\u0220\n;\r;\16;\u0221\3;\3;\6;\u0226\n;\r;\16"+
		";\u0227\3<\3<\3<\3<\3<\3=\3=\6=\u0231\n=\r=\16=\u0232\3=\3=\3=\3=\3>\3"+
		">\3>\3>\3>\3?\3?\6?\u0240\n?\r?\16?\u0241\3?\3?\3?\3?\3@\3@\3@\6@\u024b"+
		"\n@\r@\16@\u024c\3@\3@\3A\3A\3A\3A\7A\u0255\nA\fA\16A\u0258\13A\3B\3B"+
		"\3B\3B\3B\5B\u025f\nB\3B\3B\3B\3B\3B\3B\3B\3B\3B\7B\u026a\nB\fB\16B\u026d"+
		"\13B\3C\6C\u0270\nC\rC\16C\u0271\3C\7C\u0275\nC\fC\16C\u0278\13C\3C\3"+
		"C\3D\6D\u027d\nD\rD\16D\u027e\3D\5D\u0282\nD\3D\3D\3E\3E\3E\3E\7E\u028a"+
		"\nE\fE\16E\u028d\13E\3E\3E\3E\3F\3F\3G\5G\u0295\nG\3G\3G\5G\u0299\nG\3"+
		"H\3H\3H\3H\3H\3H\3H\3I\3I\3I\3I\3I\3I\3I\3J\3J\3K\3K\3K\3K\3K\3L\3L\3"+
		"L\3M\3M\3M\3M\3M\3N\3N\3N\3O\3O\3O\3P\3P\6P\u02c0\nP\rP\16P\u02c1\3P\3"+
		"P\3P\3P\3Q\3Q\3Q\3R\3R\6R\u02cd\nR\rR\16R\u02ce\3R\3R\3R\3R\3S\3S\3S\3"+
		"T\3T\3T\3T\3T\3U\3U\3U\3U\3U\3V\3V\3V\3W\3W\3W\3X\3X\3X\3X\3X\3X\3X\3"+
		"X\3X\3X\3X\3X\3X\3X\3Y\3Y\3Y\3Y\3Y\3Y\3Y\3Y\3Y\3Y\3Y\3Y\3Z\3Z\3Z\3Z\3"+
		"Z\3Z\3Z\3Z\3Z\3Z\3Z\3Z\3Z\3Z\3Z\3Z\3Z\3Z\3Z\3[\3[\3[\3[\3[\3[\3[\3[\3"+
		"[\3[\3[\3[\3[\3[\3[\3[\3[\3\\\3\\\3]\3]\3^\3^\3_\3_\3`\3`\3a\3a\3b\3b"+
		"\3c\3c\3d\3d\3e\3e\4\u01d8\u028b\2f\7\3\t\4\13\5\r\6\17\7\21\b\23\t\25"+
		"\n\27\13\31\f\33\r\35\16\37\17!\20#\21%\22\'\23)\24+\25-\26/\27\61\30"+
		"\63\31\65\32\67\339\34;\35=\36?\37A C!E\"G#I$K%M&O\'Q(S)U*W+Y,[-]._/a"+
		"\60c\61e\62g\63i\64k\65m\66o\67q8s9u:w;y<{=}>\177?\u0081@\u0083A\u0085"+
		"B\u0087C\u0089D\u008bE\u008dF\u008fG\u0091H\u0093I\u0095J\u0097K\u0099"+
		"L\u009bM\u009dN\u009fO\u00a1P\u00a3Q\u00a5R\u00a7S\u00a9T\u00abU\u00ad"+
		"V\u00afW\u00b1X\u00b3Y\u00b5Z\u00b7[\u00b9\\\u00bb\2\u00bd\2\u00bf\2\u00c1"+
		"\2\u00c3\2\u00c5\2\u00c7\2\u00c9\2\u00cb\2\u00cd\2\7\2\3\4\5\6\7\4\2\f"+
		"\f\17\17\4\2\13\13\"\"\4\2\u00b2\u00b2\u00bc\u00bc\3\2\62;\7\2C\\c|\u00c2"+
		"\u00d8\u00db\u00f8\u00fa\u0301\u0357\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2"+
		"\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2"+
		"\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2"+
		"\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2"+
		"\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3"+
		"\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2"+
		"\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2"+
		"S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3"+
		"\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2"+
		"\2\2m\3\2\2\2\2o\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\2u\3\2\2\2\2w\3\2\2\2\2"+
		"y\3\2\2\2\2{\3\2\2\2\2}\3\2\2\2\2\177\3\2\2\2\2\u0081\3\2\2\2\2\u0083"+
		"\3\2\2\2\2\u0085\3\2\2\2\2\u0087\3\2\2\2\2\u0089\3\2\2\2\2\u008b\3\2\2"+
		"\2\2\u008d\3\2\2\2\2\u008f\3\2\2\2\2\u0091\3\2\2\2\2\u0093\3\2\2\2\2\u0095"+
		"\3\2\2\2\2\u0097\3\2\2\2\3\u0099\3\2\2\2\3\u009b\3\2\2\2\3\u009d\3\2\2"+
		"\2\3\u009f\3\2\2\2\3\u00a1\3\2\2\2\4\u00a3\3\2\2\2\4\u00a5\3\2\2\2\5\u00a7"+
		"\3\2\2\2\5\u00a9\3\2\2\2\6\u00ab\3\2\2\2\6\u00ad\3\2\2\2\6\u00af\3\2\2"+
		"\2\6\u00b1\3\2\2\2\6\u00b3\3\2\2\2\6\u00b5\3\2\2\2\6\u00b7\3\2\2\2\6\u00b9"+
		"\3\2\2\2\7\u00cf\3\2\2\2\t\u00d3\3\2\2\2\13\u00d7\3\2\2\2\r\u00db\3\2"+
		"\2\2\17\u00df\3\2\2\2\21\u00e2\3\2\2\2\23\u00e6\3\2\2\2\25\u00e9\3\2\2"+
		"\2\27\u00ec\3\2\2\2\31\u00f1\3\2\2\2\33\u00f6\3\2\2\2\35\u00fa\3\2\2\2"+
		"\37\u0102\3\2\2\2!\u010a\3\2\2\2#\u0113\3\2\2\2%\u011c\3\2\2\2\'\u0126"+
		"\3\2\2\2)\u012c\3\2\2\2+\u0136\3\2\2\2-\u013f\3\2\2\2/\u0147\3\2\2\2\61"+
		"\u014d\3\2\2\2\63\u0156\3\2\2\2\65\u015e\3\2\2\2\67\u0165\3\2\2\29\u0167"+
		"\3\2\2\2;\u0169\3\2\2\2=\u016b\3\2\2\2?\u016d\3\2\2\2A\u016f\3\2\2\2C"+
		"\u0171\3\2\2\2E\u0174\3\2\2\2G\u0176\3\2\2\2I\u0178\3\2\2\2K\u017a\3\2"+
		"\2\2M\u017c\3\2\2\2O\u017e\3\2\2\2Q\u0180\3\2\2\2S\u0183\3\2\2\2U\u0189"+
		"\3\2\2\2W\u018b\3\2\2\2Y\u0190\3\2\2\2[\u0195\3\2\2\2]\u019d\3\2\2\2_"+
		"\u01a3\3\2\2\2a\u01ac\3\2\2\2c\u01b3\3\2\2\2e\u01ba\3\2\2\2g\u01c2\3\2"+
		"\2\2i\u01c7\3\2\2\2k\u01cc\3\2\2\2m\u01d2\3\2\2\2o\u01e3\3\2\2\2q\u01f7"+
		"\3\2\2\2s\u020a\3\2\2\2u\u020d\3\2\2\2w\u0214\3\2\2\2y\u021c\3\2\2\2{"+
		"\u0229\3\2\2\2}\u022e\3\2\2\2\177\u0238\3\2\2\2\u0081\u023d\3\2\2\2\u0083"+
		"\u0247\3\2\2\2\u0085\u0250\3\2\2\2\u0087\u025e\3\2\2\2\u0089\u026f\3\2"+
		"\2\2\u008b\u027c\3\2\2\2\u008d\u0285\3\2\2\2\u008f\u0291\3\2\2\2\u0091"+
		"\u0298\3\2\2\2\u0093\u029a\3\2\2\2\u0095\u02a1\3\2\2\2\u0097\u02a8\3\2"+
		"\2\2\u0099\u02aa\3\2\2\2\u009b\u02af\3\2\2\2\u009d\u02b2\3\2\2\2\u009f"+
		"\u02b7\3\2\2\2\u00a1\u02ba\3\2\2\2\u00a3\u02bd\3\2\2\2\u00a5\u02c7\3\2"+
		"\2\2\u00a7\u02ca\3\2\2\2\u00a9\u02d4\3\2\2\2\u00ab\u02d7\3\2\2\2\u00ad"+
		"\u02dc\3\2\2\2\u00af\u02e1\3\2\2\2\u00b1\u02e4\3\2\2\2\u00b3\u02e7\3\2"+
		"\2\2\u00b5\u02f5\3\2\2\2\u00b7\u0301\3\2\2\2\u00b9\u0314\3\2\2\2\u00bb"+
		"\u0325\3\2\2\2\u00bd\u0327\3\2\2\2\u00bf\u0329\3\2\2\2\u00c1\u032b\3\2"+
		"\2\2\u00c3\u032d\3\2\2\2\u00c5\u032f\3\2\2\2\u00c7\u0331\3\2\2\2\u00c9"+
		"\u0333\3\2\2\2\u00cb\u0335\3\2\2\2\u00cd\u0337\3\2\2\2\u00cf\u00d0\7u"+
		"\2\2\u00d0\u00d1\7w\2\2\u00d1\u00d2\7d\2\2\u00d2\b\3\2\2\2\u00d3\u00d4"+
		"\7w\2\2\u00d4\u00d5\7u\2\2\u00d5\u00d6\7g\2\2\u00d6\n\3\2\2\2\u00d7\u00d8"+
		"\7f\2\2\u00d8\u00d9\7u\2\2\u00d9\u00da\7n\2\2\u00da\f\3\2\2\2\u00db\u00dc"+
		"\7x\2\2\u00dc\u00dd\7c\2\2\u00dd\u00de\7t\2\2\u00de\16\3\2\2\2\u00df\u00e0"+
		"\7c\2\2\u00e0\u00e1\7u\2\2\u00e1\20\3\2\2\2\u00e2\u00e3\7j\2\2\u00e3\u00e4"+
		"\7c\2\2\u00e4\u00e5\7u\2\2\u00e5\22\3\2\2\2\u00e6\u00e7\7q\2\2\u00e7\u00e8"+
		"\7p\2\2\u00e8\24\3\2\2\2\u00e9\u00ea\7k\2\2\u00ea\u00eb\7u\2\2\u00eb\26"+
		"\3\2\2\2\u00ec\u00ed\7k\2\2\u00ed\u00ee\7p\2\2\u00ee\u00ef\7v\2\2\u00ef"+
		"\u00f0\7q\2\2\u00f0\30\3\2\2\2\u00f1\u00f2\7y\2\2\u00f2\u00f3\7k\2\2\u00f3"+
		"\u00f4\7v\2\2\u00f4\u00f5\7j\2\2\u00f5\32\3\2\2\2\u00f6\u00f7\7c\2\2\u00f7"+
		"\u00f8\7p\2\2\u00f8\u00f9\7{\2\2\u00f9\34\3\2\2\2\u00fa\u00fb\7g\2\2\u00fb"+
		"\u00fc\7z\2\2\u00fc\u00fd\7v\2\2\u00fd\u00fe\7g\2\2\u00fe\u00ff\7p\2\2"+
		"\u00ff\u0100\7f\2\2\u0100\u0101\7u\2\2\u0101\36\3\2\2\2\u0102\u0103\7"+
		"e\2\2\u0103\u0104\7q\2\2\u0104\u0105\7p\2\2\u0105\u0106\7e\2\2\u0106\u0107"+
		"\7g\2\2\u0107\u0108\7r\2\2\u0108\u0109\7v\2\2\u0109 \3\2\2\2\u010a\u010b"+
		"\7c\2\2\u010b\u010c\7d\2\2\u010c\u010d\7u\2\2\u010d\u010e\7v\2\2\u010e"+
		"\u010f\7t\2\2\u010f\u0110\7c\2\2\u0110\u0111\7e\2\2\u0111\u0112\7v\2\2"+
		"\u0112\"\3\2\2\2\u0113\u0114\7v\2\2\u0114\u0115\7g\2\2\u0115\u0116\7t"+
		"\2\2\u0116\u0117\7o\2\2\u0117\u0118\7k\2\2\u0118\u0119\7p\2\2\u0119\u011a"+
		"\7c\2\2\u011a\u011b\7n\2\2\u011b$\3\2\2\2\u011c\u011d\7e\2\2\u011d\u011e"+
		"\7q\2\2\u011e\u011f\7o\2\2\u011f\u0120\7r\2\2\u0120\u0121\7q\2\2\u0121"+
		"\u0122\7p\2\2\u0122\u0123\7g\2\2\u0123\u0124\7p\2\2\u0124\u0125\7v\2\2"+
		"\u0125&\3\2\2\2\u0126\u0127\7p\2\2\u0127\u0128\7c\2\2\u0128\u0129\7o\2"+
		"\2\u0129\u012a\7g\2\2\u012a\u012b\7f\2\2\u012b(\3\2\2\2\u012c\u012d\7"+
		"r\2\2\u012d\u012e\7t\2\2\u012e\u012f\7q\2\2\u012f\u0130\7v\2\2\u0130\u0131"+
		"\7q\2\2\u0131\u0132\7v\2\2\u0132\u0133\7{\2\2\u0133\u0134\7r\2\2\u0134"+
		"\u0135\7g\2\2\u0135*\3\2\2\2\u0136\u0137\7r\2\2\u0137\u0138\7t\2\2\u0138"+
		"\u0139\7q\2\2\u0139\u013a\7h\2\2\u013a\u013b\7k\2\2\u013b\u013c\7n\2\2"+
		"\u013c\u013d\7g\2\2\u013d\u013e\7t\2\2\u013e,\3\2\2\2\u013f\u0140\7h\2"+
		"\2\u0140\u0141\7g\2\2\u0141\u0142\7c\2\2\u0142\u0143\7v\2\2\u0143\u0144"+
		"\7w\2\2\u0144\u0145\7t\2\2\u0145\u0146\7g\2\2\u0146.\3\2\2\2\u0147\u0148"+
		"\7h\2\2\u0148\u0149\7k\2\2\u0149\u014a\7p\2\2\u014a\u014b\7c\2\2\u014b"+
		"\u014c\7n\2\2\u014c\60\3\2\2\2\u014d\u014e\7g\2\2\u014e\u014f\7p\2\2\u014f"+
		"\u0150\7e\2\2\u0150\u0151\7n\2\2\u0151\u0152\7q\2\2\u0152\u0153\7u\2\2"+
		"\u0153\u0154\7g\2\2\u0154\u0155\7f\2\2\u0155\62\3\2\2\2\u0156\u0157\7"+
		"r\2\2\u0157\u0158\7t\2\2\u0158\u0159\7k\2\2\u0159\u015a\7x\2\2\u015a\u015b"+
		"\7c\2\2\u015b\u015c\7v\2\2\u015c\u015d\7g\2\2\u015d\64\3\2\2\2\u015e\u015f"+
		"\7p\2\2\u015f\u0160\7c\2\2\u0160\u0161\7v\2\2\u0161\u0162\7k\2\2\u0162"+
		"\u0163\7x\2\2\u0163\u0164\7g\2\2\u0164\66\3\2\2\2\u0165\u0166\7*\2\2\u0166"+
		"8\3\2\2\2\u0167\u0168\7+\2\2\u0168:\3\2\2\2\u0169\u016a\7]\2\2\u016a<"+
		"\3\2\2\2\u016b\u016c\7_\2\2\u016c>\3\2\2\2\u016d\u016e\7}\2\2\u016e@\3"+
		"\2\2\2\u016f\u0170\7\177\2\2\u0170B\3\2\2\2\u0171\u0172\7@\2\2\u0172\u0173"+
		"\b \2\2\u0173D\3\2\2\2\u0174\u0175\7>\2\2\u0175F\3\2\2\2\u0176\u0177\7"+
		"%\2\2\u0177H\3\2\2\2\u0178\u0179\7<\2\2\u0179J\3\2\2\2\u017a\u017b\7."+
		"\2\2\u017bL\3\2\2\2\u017c\u017d\7\60\2\2\u017dN\3\2\2\2\u017e\u017f\7"+
		"?\2\2\u017fP\3\2\2\2\u0180\u0181\7,\2\2\u0181R\3\2\2\2\u0182\u0184\7="+
		"\2\2\u0183\u0182\3\2\2\2\u0184\u0185\3\2\2\2\u0185\u0183\3\2\2\2\u0185"+
		"\u0186\3\2\2\2\u0186\u0187\3\2\2\2\u0187\u0188\b(\3\2\u0188T\3\2\2\2\u0189"+
		"\u018a\7-\2\2\u018aV\3\2\2\2\u018b\u018c\7y\2\2\u018c\u018d\7q\2\2\u018d"+
		"\u018e\7t\2\2\u018e\u018f\7f\2\2\u018fX\3\2\2\2\u0190\u0191\7h\2\2\u0191"+
		"\u0192\7k\2\2\u0192\u0193\7n\2\2\u0193\u0194\7g\2\2\u0194Z\3\2\2\2\u0195"+
		"\u0196\7k\2\2\u0196\u0197\7p\2\2\u0197\u0198\7v\2\2\u0198\u0199\7g\2\2"+
		"\u0199\u019a\7i\2\2\u019a\u019b\7g\2\2\u019b\u019c\7t\2\2\u019c\\\3\2"+
		"\2\2\u019d\u019e\7v\2\2\u019e\u019f\7w\2\2\u019f\u01a0\7r\2\2\u01a0\u01a1"+
		"\7n\2\2\u01a1\u01a2\7g\2\2\u01a2^\3\2\2\2\u01a3\u01a4\7h\2\2\u01a4\u01a5"+
		"\7w\2\2\u01a5\u01a6\7p\2\2\u01a6\u01a7\7e\2\2\u01a7\u01a8\7v\2\2\u01a8"+
		"\u01a9\7k\2\2\u01a9\u01aa\7q\2\2\u01aa\u01ab\7p\2\2\u01ab`\3\2\2\2\u01ac"+
		"\u01ad\7f\2\2\u01ad\u01ae\7q\2\2\u01ae\u01af\7w\2\2\u01af\u01b0\7d\2\2"+
		"\u01b0\u01b1\7n\2\2\u01b1\u01b2\7g\2\2\u01b2b\3\2\2\2\u01b3\u01b4\7u\2"+
		"\2\u01b4\u01b5\7v\2\2\u01b5\u01b6\7t\2\2\u01b6\u01b7\7k\2\2\u01b7\u01b8"+
		"\7p\2\2\u01b8\u01b9\7i\2\2\u01b9d\3\2\2\2\u01ba\u01bb\7d\2\2\u01bb\u01bc"+
		"\7q\2\2\u01bc\u01bd\7q\2\2\u01bd\u01be\7n\2\2\u01be\u01bf\7g\2\2\u01bf"+
		"\u01c0\7c\2\2\u01c0\u01c1\7p\2\2\u01c1f\3\2\2\2\u01c2\u01c3\7f\2\2\u01c3"+
		"\u01c4\7c\2\2\u01c4\u01c5\7v\2\2\u01c5\u01c6\7g\2\2\u01c6h\3\2\2\2\u01c7"+
		"\u01c8\7v\2\2\u01c8\u01c9\7k\2\2\u01c9\u01ca\7o\2\2\u01ca\u01cb\7g\2\2"+
		"\u01cbj\3\2\2\2\u01cc\u01cd\7g\2\2\u01cd\u01ce\7o\2\2\u01ce\u01cf\7r\2"+
		"\2\u01cf\u01d0\7v\2\2\u01d0\u01d1\7{\2\2\u01d1l\3\2\2\2\u01d2\u01d3\7"+
		"\61\2\2\u01d3\u01d4\7,\2\2\u01d4\u01d8\3\2\2\2\u01d5\u01d7\13\2\2\2\u01d6"+
		"\u01d5\3\2\2\2\u01d7\u01da\3\2\2\2\u01d8\u01d9\3\2\2\2\u01d8\u01d6\3\2"+
		"\2\2\u01d9\u01db\3\2\2\2\u01da\u01d8\3\2\2\2\u01db\u01dc\7,\2\2\u01dc"+
		"\u01dd\7\61\2\2\u01dd\u01de\3\2\2\2\u01de\u01df\b\65\4\2\u01dfn\3\2\2"+
		"\2\u01e0\u01e2\t\2\2\2\u01e1\u01e0\3\2\2\2\u01e2\u01e5\3\2\2\2\u01e3\u01e1"+
		"\3\2\2\2\u01e3\u01e4\3\2\2\2\u01e4\u01e9\3\2\2\2\u01e5\u01e3\3\2\2\2\u01e6"+
		"\u01e8\t\3\2\2\u01e7\u01e6\3\2\2\2\u01e8\u01eb\3\2\2\2\u01e9\u01e7\3\2"+
		"\2\2\u01e9\u01ea\3\2\2\2\u01ea\u01ec\3\2\2\2\u01eb\u01e9\3\2\2\2\u01ec"+
		"\u01ed\7\61\2\2\u01ed\u01ee\7\61\2\2\u01ee\u01f2\3\2\2\2\u01ef\u01f1\n"+
		"\2\2\2\u01f0\u01ef\3\2\2\2\u01f1\u01f4\3\2\2\2\u01f2\u01f0\3\2\2\2\u01f2"+
		"\u01f3\3\2\2\2\u01f3\u01f5\3\2\2\2\u01f4\u01f2\3\2\2\2\u01f5\u01f6\b\66"+
		"\4\2\u01f6p\3\2\2\2\u01f7\u01fa\7G\2\2\u01f8\u01fb\5U)\2\u01f9\u01fb\5"+
		"\u00c7b\2\u01fa\u01f8\3\2\2\2\u01fa\u01f9\3\2\2\2\u01fa\u01fb\3\2\2\2"+
		"\u01fb\u01fd\3\2\2\2\u01fc\u01fe\5\u00cbd\2\u01fd\u01fc\3\2\2\2\u01fe"+
		"\u01ff\3\2\2\2\u01ff\u01fd\3\2\2\2\u01ff\u0200\3\2\2\2\u0200r\3\2\2\2"+
		"\u0201\u0202\7v\2\2\u0202\u0203\7t\2\2\u0203\u0204\7w\2\2\u0204\u020b"+
		"\7g\2\2\u0205\u0206\7h\2\2\u0206\u0207\7c\2\2\u0207\u0208\7n\2\2\u0208"+
		"\u0209\7u\2\2\u0209\u020b\7g\2\2\u020a\u0201\3\2\2\2\u020a\u0205\3\2\2"+
		"\2\u020bt\3\2\2\2\u020c\u020e\5U)\2\u020d\u020c\3\2\2\2\u020d\u020e\3"+
		"\2\2\2\u020e\u0210\3\2\2\2\u020f\u0211\5\u00cbd\2\u0210\u020f\3\2\2\2"+
		"\u0211\u0212\3\2\2\2\u0212\u0210\3\2\2\2\u0212\u0213\3\2\2\2\u0213v\3"+
		"\2\2\2\u0214\u0216\5\u00c7b\2\u0215\u0217\5\u00cbd\2\u0216\u0215\3\2\2"+
		"\2\u0217\u0218\3\2\2\2\u0218\u0216\3\2\2\2\u0218\u0219\3\2\2\2\u0219x"+
		"\3\2\2\2\u021a\u021d\5U)\2\u021b\u021d\5\u00c7b\2\u021c\u021a\3\2\2\2"+
		"\u021c\u021b\3\2\2\2\u021c\u021d\3\2\2\2\u021d\u021f\3\2\2\2\u021e\u0220"+
		"\5\u00cbd\2\u021f\u021e\3\2\2\2\u0220\u0221\3\2\2\2\u0221\u021f\3\2\2"+
		"\2\u0221\u0222\3\2\2\2\u0222\u0223\3\2\2\2\u0223\u0225\5M%\2\u0224\u0226"+
		"\5\u00cbd\2\u0225\u0224\3\2\2\2\u0226\u0227\3\2\2\2\u0227\u0225\3\2\2"+
		"\2\u0227\u0228\3\2\2\2\u0228z\3\2\2\2\u0229\u022a\7$\2\2\u022a\u022b\b"+
		"<\5\2\u022b\u022c\3\2\2\2\u022c\u022d\b<\6\2\u022d|\3\2\2\2\u022e\u0230"+
		"\5O&\2\u022f\u0231\5O&\2\u0230\u022f\3\2\2\2\u0231\u0232\3\2\2\2\u0232"+
		"\u0230\3\2\2\2\u0232\u0233\3\2\2\2\u0233\u0234\3\2\2\2\u0234\u0235\b="+
		"\7\2\u0235\u0236\3\2\2\2\u0236\u0237\b=\b\2\u0237~\3\2\2\2\u0238\u0239"+
		"\7)\2\2\u0239\u023a\b>\t\2\u023a\u023b\3\2\2\2\u023b\u023c\b>\n\2\u023c"+
		"\u0080\3\2\2\2\u023d\u023f\5\u00c7b\2\u023e\u0240\5\u00c7b\2\u023f\u023e"+
		"\3\2\2\2\u0240\u0241\3\2\2\2\u0241\u023f\3\2\2\2\u0241\u0242\3\2\2\2\u0242"+
		"\u0243\3\2\2\2\u0243\u0244\b?\13\2\u0244\u0245\3\2\2\2\u0245\u0246\b?"+
		"\f\2\u0246\u0082\3\2\2\2\u0247\u024a\5Q\'\2\u0248\u024b\5\u00cbd\2\u0249"+
		"\u024b\5\u00cde\2\u024a\u0248\3\2\2\2\u024a\u0249\3\2\2\2\u024b\u024c"+
		"\3\2\2\2\u024c\u024a\3\2\2\2\u024c\u024d\3\2\2\2\u024d\u024e\3\2\2\2\u024e"+
		"\u024f\5Q\'\2\u024f\u0084\3\2\2\2\u0250\u0256\5\u00cde\2\u0251\u0255\5"+
		"\u00cbd\2\u0252\u0255\5\u00cde\2\u0253\u0255\5\u00c7b\2\u0254\u0251\3"+
		"\2\2\2\u0254\u0252\3\2\2\2\u0254\u0253\3\2\2\2\u0255\u0258\3\2\2\2\u0256"+
		"\u0254\3\2\2\2\u0256\u0257\3\2\2\2\u0257\u0086\3\2\2\2\u0258\u0256\3\2"+
		"\2\2\u0259\u025f\5\u00cde\2\u025a\u025f\5\u00bf^\2\u025b\u025f\5\u00bb"+
		"\\\2\u025c\u025f\5\u00bd]\2\u025d\u025f\5\u00c1_\2\u025e\u0259\3\2\2\2"+
		"\u025e\u025a\3\2\2\2\u025e\u025b\3\2\2\2\u025e\u025c\3\2\2\2\u025e\u025d"+
		"\3\2\2\2\u025f\u026b\3\2\2\2\u0260\u026a\5\u00c9c\2\u0261\u026a\5\u00c3"+
		"`\2\u0262\u026a\5\u00c5a\2\u0263\u026a\5\u00bf^\2\u0264\u026a\5\u00bb"+
		"\\\2\u0265\u026a\5\u00bd]\2\u0266\u026a\5\u00c1_\2\u0267\u026a\5\u00cd"+
		"e\2\u0268\u026a\5\u00cbd\2\u0269\u0260\3\2\2\2\u0269\u0261\3\2\2\2\u0269"+
		"\u0262\3\2\2\2\u0269\u0263\3\2\2\2\u0269\u0264\3\2\2\2\u0269\u0265\3\2"+
		"\2\2\u0269\u0266\3\2\2\2\u0269\u0267\3\2\2\2\u0269\u0268\3\2\2\2\u026a"+
		"\u026d\3\2\2\2\u026b\u0269\3\2\2\2\u026b\u026c\3\2\2\2\u026c\u0088\3\2"+
		"\2\2\u026d\u026b\3\2\2\2\u026e\u0270\5\u0091G\2\u026f\u026e\3\2\2\2\u0270"+
		"\u0271\3\2\2\2\u0271\u026f\3\2\2\2\u0271\u0272\3\2\2\2\u0272\u0276\3\2"+
		"\2\2\u0273\u0275\5\u008fF\2\u0274\u0273\3\2\2\2\u0275\u0278\3\2\2\2\u0276"+
		"\u0274\3\2\2\2\u0276\u0277\3\2\2\2\u0277\u0279\3\2\2\2\u0278\u0276\3\2"+
		"\2\2\u0279\u027a\bC\r\2\u027a\u008a\3\2\2\2\u027b\u027d\5\u008fF\2\u027c"+
		"\u027b\3\2\2\2\u027d\u027e\3\2\2\2\u027e\u027c\3\2\2\2\u027e\u027f\3\2"+
		"\2\2\u027f\u0281\3\2\2\2\u0280\u0282\7\2\2\3\u0281\u0280\3\2\2\2\u0281"+
		"\u0282\3\2\2\2\u0282\u0283\3\2\2\2\u0283\u0284\bD\16\2\u0284\u008c\3\2"+
		"\2\2\u0285\u0286\7#\2\2\u0286\u0287\7#\2\2\u0287\u028b\3\2\2\2\u0288\u028a"+
		"\13\2\2\2\u0289\u0288\3\2\2\2\u028a\u028d\3\2\2\2\u028b\u028c\3\2\2\2"+
		"\u028b\u0289\3\2\2\2\u028c\u028e\3\2\2\2\u028d\u028b\3\2\2\2\u028e\u028f"+
		"\5\u0089C\2\u028f\u0290\bE\17\2\u0290\u008e\3\2\2\2\u0291\u0292\t\3\2"+
		"\2\u0292\u0090\3\2\2\2\u0293\u0295\7\17\2\2\u0294\u0293\3\2\2\2\u0294"+
		"\u0295\3\2\2\2\u0295\u0296\3\2\2\2\u0296\u0299\7\f\2\2\u0297\u0299\7\17"+
		"\2\2\u0298\u0294\3\2\2\2\u0298\u0297\3\2\2\2\u0299\u0092\3\2\2\2\u029a"+
		"\u029b\7k\2\2\u029b\u029c\7p\2\2\u029c\u029d\7f\2\2\u029d\u029e\7g\2\2"+
		"\u029e\u029f\7p\2\2\u029f\u02a0\7v\2\2\u02a0\u0094\3\2\2\2\u02a1\u02a2"+
		"\7f\2\2\u02a2\u02a3\7g\2\2\u02a3\u02a4\7f\2\2\u02a4\u02a5\7g\2\2\u02a5"+
		"\u02a6\7p\2\2\u02a6\u02a7\7v\2\2\u02a7\u0096\3\2\2\2\u02a8\u02a9\13\2"+
		"\2\2\u02a9\u0098\3\2\2\2\u02aa\u02ab\7$\2\2\u02ab\u02ac\bK\20\2\u02ac"+
		"\u02ad\3\2\2\2\u02ad\u02ae\bK\21\2\u02ae\u009a\3\2\2\2\u02af\u02b0\7$"+
		"\2\2\u02b0\u02b1\bL\22\2\u02b1\u009c\3\2\2\2\u02b2\u02b3\7^\2\2\u02b3"+
		"\u02b4\7$\2\2\u02b4\u02b5\3\2\2\2\u02b5\u02b6\bM\23\2\u02b6\u009e\3\2"+
		"\2\2\u02b7\u02b8\7^\2\2\u02b8\u02b9\bN\24\2\u02b9\u00a0\3\2\2\2\u02ba"+
		"\u02bb\13\2\2\2\u02bb\u02bc\bO\25\2\u02bc\u00a2\3\2\2\2\u02bd\u02bf\5"+
		"O&\2\u02be\u02c0\5O&\2\u02bf\u02be\3\2\2\2\u02c0\u02c1\3\2\2\2\u02c1\u02bf"+
		"\3\2\2\2\u02c1\u02c2\3\2\2\2\u02c2\u02c3\3\2\2\2\u02c3\u02c4\bP\26\2\u02c4"+
		"\u02c5\3\2\2\2\u02c5\u02c6\bP\21\2\u02c6\u00a4\3\2\2\2\u02c7\u02c8\13"+
		"\2\2\2\u02c8\u02c9\bQ\27\2\u02c9\u00a6\3\2\2\2\u02ca\u02cc\5\u00c7b\2"+
		"\u02cb\u02cd\5\u00c7b\2\u02cc\u02cb\3\2\2\2\u02cd\u02ce\3\2\2\2\u02ce"+
		"\u02cc\3\2\2\2\u02ce\u02cf\3\2\2\2\u02cf\u02d0\3\2\2\2\u02d0\u02d1\bR"+
		"\30\2\u02d1\u02d2\3\2\2\2\u02d2\u02d3\bR\21\2\u02d3\u00a8\3\2\2\2\u02d4"+
		"\u02d5\13\2\2\2\u02d5\u02d6\bS\31\2\u02d6\u00aa\3\2\2\2\u02d7\u02d8\7"+
		")\2\2\u02d8\u02d9\bT\32\2\u02d9\u02da\3\2\2\2\u02da\u02db\bT\21\2\u02db"+
		"\u00ac\3\2\2\2\u02dc\u02dd\7^\2\2\u02dd\u02de\7)\2\2\u02de\u02df\3\2\2"+
		"\2\u02df\u02e0\bU\33\2\u02e0\u00ae\3\2\2\2\u02e1\u02e2\7^\2\2\u02e2\u02e3"+
		"\bV\34\2\u02e3\u00b0\3\2\2\2\u02e4\u02e5\13\2\2\2\u02e5\u02e6\bW\35\2"+
		"\u02e6\u00b2\3\2\2\2\u02e7\u02e8\7\'\2\2\u02e8\u02e9\7S\2\2\u02e9\u02ea"+
		"\7W\2\2\u02ea\u02eb\7Q\2\2\u02eb\u02ec\7V\2\2\u02ec\u02ed\7G\2\2\u02ed"+
		"\u02ee\7a\2\2\u02ee\u02ef\7D\2\2\u02ef\u02f0\7G\2\2\u02f0\u02f1\7I\2\2"+
		"\u02f1\u02f2\7K\2\2\u02f2\u02f3\7P\2\2\u02f3\u02f4\7\'\2\2\u02f4\u00b4"+
		"\3\2\2\2\u02f5\u02f6\7\'\2\2\u02f6\u02f7\7S\2\2\u02f7\u02f8\7W\2\2\u02f8"+
		"\u02f9\7Q\2\2\u02f9\u02fa\7V\2\2\u02fa\u02fb\7G\2\2\u02fb\u02fc\7a\2\2"+
		"\u02fc\u02fd\7G\2\2\u02fd\u02fe\7P\2\2\u02fe\u02ff\7F\2\2\u02ff\u0300"+
		"\7\'\2\2\u0300\u00b6\3\2\2\2\u0301\u0302\7\'\2\2\u0302\u0303\7G\2\2\u0303"+
		"\u0304\7Z\2\2\u0304\u0305\7R\2\2\u0305\u0306\7T\2\2\u0306\u0307\7G\2\2"+
		"\u0307\u0308\7U\2\2\u0308\u0309\7U\2\2\u0309\u030a\7K\2\2\u030a\u030b"+
		"\7Q\2\2\u030b\u030c\7P\2\2\u030c\u030d\7a\2\2\u030d\u030e\7D\2\2\u030e"+
		"\u030f\7G\2\2\u030f\u0310\7I\2\2\u0310\u0311\7K\2\2\u0311\u0312\7P\2\2"+
		"\u0312\u0313\7\'\2\2\u0313\u00b8\3\2\2\2\u0314\u0315\7\'\2\2\u0315\u0316"+
		"\7G\2\2\u0316\u0317\7Z\2\2\u0317\u0318\7R\2\2\u0318\u0319\7T\2\2\u0319"+
		"\u031a\7G\2\2\u031a\u031b\7U\2\2\u031b\u031c\7U\2\2\u031c\u031d\7K\2\2"+
		"\u031d\u031e\7Q\2\2\u031e\u031f\7P\2\2\u031f\u0320\7a\2\2\u0320\u0321"+
		"\7G\2\2\u0321\u0322\7P\2\2\u0322\u0323\7F\2\2\u0323\u0324\7\'\2\2\u0324"+
		"\u00ba\3\2\2\2\u0325\u0326\7&\2\2\u0326\u00bc\3\2\2\2\u0327\u0328\7\u20ae"+
		"\2\2\u0328\u00be\3\2\2\2\u0329\u032a\7\'\2\2\u032a\u00c0\3\2\2\2\u032b"+
		"\u032c\t\4\2\2\u032c\u00c2\3\2\2\2\u032d\u032e\7\u00b9\2\2\u032e\u00c4"+
		"\3\2\2\2\u032f\u0330\7\61\2\2\u0330\u00c6\3\2\2\2\u0331\u0332\7/\2\2\u0332"+
		"\u00c8\3\2\2\2\u0333\u0334\7a\2\2\u0334\u00ca\3\2\2\2\u0335\u0336\t\5"+
		"\2\2\u0336\u00cc\3\2\2\2\u0337\u0338\t\6\2\2\u0338\u00ce\3\2\2\2(\2\3"+
		"\4\5\6\u0185\u01d8\u01e3\u01e7\u01e9\u01f2\u01fa\u01ff\u020a\u020d\u0212"+
		"\u0218\u021c\u0221\u0227\u0232\u0241\u024a\u024c\u0254\u0256\u025e\u0269"+
		"\u026b\u0271\u0276\u027e\u0281\u028b\u0294\u0298\u02c1\u02ce\36\3 \2\3"+
		"(\3\b\2\2\3<\4\4\3\2\3=\5\4\4\2\3>\6\4\6\2\3?\7\4\5\2\3C\b\2\3\2\3E\t"+
		"\3K\n\4\2\2\3L\13\3M\f\3N\r\3O\16\3P\17\3Q\20\3R\21\3S\22\3T\23\3U\24"+
		"\3V\25\3W\26";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}