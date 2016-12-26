// Generated from /Users/oroncal/workspace/tara/core/language/src/tara/lang/lexicon/TaraLexer.g4 by ANTLR 4.6
package io.intino.tara.lang.grammar;
import io.intino.tara.compiler.parser.antlr.BlockManager;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TaraLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.6", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		SUB=1, USE=2, DSL=3, VAR=4, AS=5, HAS=6, ON=7, IS=8, INTO=9, WITH=10, 
		ANY=11, EXTENDS=12, CONCEPT=13, ABSTRACT=14, TERMINAL=15, COMPONENT=16, 
		FEATURE=17, FINAL=18, ENCLOSED=19, PRIVATE=20, REACTIVE=21, VOLATILE=22, 
		VERSIONED=23, LEFT_PARENTHESIS=24, RIGHT_PARENTHESIS=25, LEFT_SQUARE=26, 
		RIGHT_SQUARE=27, LEFT_CURLY=28, RIGHT_CURLY=29, INLINE=30, CLOSE_INLINE=31, 
		AT=32, HASHTAG=33, COLON=34, COMMA=35, DOT=36, EQUALS=37, STAR=38, LIST=39, 
		SEMICOLON=40, PLUS=41, WORD=42, RESOURCE=43, INT_TYPE=44, FUNCTION_TYPE=45, 
		OBJECT_TYPE=46, DOUBLE_TYPE=47, STRING_TYPE=48, BOOLEAN_TYPE=49, DATE_TYPE=50, 
		INSTANT_TYPE=51, TIME_TYPE=52, EMPTY=53, BLOCK_COMMENT=54, LINE_COMMENT=55, 
		SCIENCE_NOT=56, BOOLEAN_VALUE=57, NATURAL_VALUE=58, NEGATIVE_VALUE=59, 
		DOUBLE_VALUE=60, APHOSTROPHE=61, STRING_MULTILINE=62, SINGLE_QUOTE=63, 
		EXPRESSION_MULTILINE=64, CLASS_TYPE=65, ANCHOR_VALUE=66, IDENTIFIER=67, 
		MEASURE_VALUE=68, NEWLINE=69, SPACES=70, DOC=71, SP=72, NL=73, NEW_LINE_INDENT=74, 
		DEDENT=75, UNKNOWN_TOKEN=76, QUOTE=77, Q=78, SLASH_Q=79, SLASH=80, CHARACTER=81, 
		M_QUOTE=82, M_CHARACTER=83, ME_STRING_MULTILINE=84, ME_CHARACTER=85, E_QUOTE=86, 
		E_SLASH_Q=87, E_SLASH=88, E_CHARACTER=89, QUOTE_BEGIN=90, QUOTE_END=91, 
		EXPRESSION_BEGIN=92, EXPRESSION_END=93;
	public static final int QUOTED = 1;
	public static final int MULTILINE = 2;
	public static final int EXPRESSION_MULTILINE_MODE = 3;
	public static final int EXPRESSION_QUOTED = 4;
	public static String[] modeNames = {
		"DEFAULT_MODE", "QUOTED", "MULTILINE", "EXPRESSION_MULTILINE_MODE", "EXPRESSION_QUOTED"
	};

	public static final String[] ruleNames = {
		"SUB", "USE", "DSL", "VAR", "AS", "HAS", "ON", "IS", "INTO", "WITH", "ANY", 
		"EXTENDS", "CONCEPT", "ABSTRACT", "TERMINAL", "COMPONENT", "FEATURE", 
		"FINAL", "ENCLOSED", "PRIVATE", "REACTIVE", "VOLATILE", "VERSIONED", "LEFT_PARENTHESIS", 
		"RIGHT_PARENTHESIS", "LEFT_SQUARE", "RIGHT_SQUARE", "LEFT_CURLY", "RIGHT_CURLY", 
		"INLINE", "CLOSE_INLINE", "AT", "HASHTAG", "COLON", "COMMA", "DOT", "EQUALS", 
		"STAR", "LIST", "SEMICOLON", "PLUS", "WORD", "RESOURCE", "INT_TYPE", "FUNCTION_TYPE", 
		"OBJECT_TYPE", "DOUBLE_TYPE", "STRING_TYPE", "BOOLEAN_TYPE", "DATE_TYPE", 
		"INSTANT_TYPE", "TIME_TYPE", "EMPTY", "BLOCK_COMMENT", "LINE_COMMENT", 
		"SCIENCE_NOT", "BOOLEAN_VALUE", "NATURAL_VALUE", "NEGATIVE_VALUE", "DOUBLE_VALUE", 
		"APHOSTROPHE", "STRING_MULTILINE", "SINGLE_QUOTE", "EXPRESSION_MULTILINE", 
		"CLASS_TYPE", "ANCHOR_VALUE", "IDENTIFIER", "MEASURE_VALUE", "NEWLINE", 
		"SPACES", "DOC", "SP", "NL", "NEW_LINE_INDENT", "DEDENT", "UNKNOWN_TOKEN", 
		"QUOTE", "Q", "SLASH_Q", "SLASH", "CHARACTER", "M_QUOTE", "M_CHARACTER", 
		"ME_STRING_MULTILINE", "ME_CHARACTER", "E_QUOTE", "E_SLASH_Q", "E_SLASH", 
		"E_CHARACTER", "QUOTE_BEGIN", "QUOTE_END", "EXPRESSION_BEGIN", "EXPRESSION_END", 
		"DOLLAR", "EURO", "PERCENTAGE", "GRADE", "BY", "DIVIDED_BY", "DASH", "UNDERDASH", 
		"DIGIT", "LETTER"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'sub'", "'use'", "'dsl'", "'var'", "'as'", "'has'", "'on'", "'is'", 
		"'into'", "'with'", "'any'", "'extends'", "'concept'", "'abstract'", "'terminal'", 
		"'component'", "'feature'", "'final'", "'enclosed'", "'private'", "'reactive'", 
		"'volatile'", "'versioned'", "'('", "')'", "'['", "']'", "'{'", "'}'", 
		"'>'", "'<'", "'@'", "'#'", "':'", "','", "'.'", "'='", "'*'", "'...'", 
		null, "'+'", "'Word'", "'Resource'", "'Integer'", "'Function'", "'Object'", 
		"'Double'", "'String'", "'Boolean'", "'Date'", "'Instant'", "'Time'", 
		"'empty'", null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, "'indent'", 
		"'dedent'", null, null, "'\"'", "'\\\"'", null, null, null, null, null, 
		null, null, "'\\''", null, null, "'%QUOTE_BEGIN%'", "'%QUOTE_END%'", "'%EXPRESSION_BEGIN%'", 
		"'%EXPRESSION_END%'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "SUB", "USE", "DSL", "VAR", "AS", "HAS", "ON", "IS", "INTO", "WITH", 
		"ANY", "EXTENDS", "CONCEPT", "ABSTRACT", "TERMINAL", "COMPONENT", "FEATURE", 
		"FINAL", "ENCLOSED", "PRIVATE", "REACTIVE", "VOLATILE", "VERSIONED", "LEFT_PARENTHESIS", 
		"RIGHT_PARENTHESIS", "LEFT_SQUARE", "RIGHT_SQUARE", "LEFT_CURLY", "RIGHT_CURLY", 
		"INLINE", "CLOSE_INLINE", "AT", "HASHTAG", "COLON", "COMMA", "DOT", "EQUALS", 
		"STAR", "LIST", "SEMICOLON", "PLUS", "WORD", "RESOURCE", "INT_TYPE", "FUNCTION_TYPE", 
		"OBJECT_TYPE", "DOUBLE_TYPE", "STRING_TYPE", "BOOLEAN_TYPE", "DATE_TYPE", 
		"INSTANT_TYPE", "TIME_TYPE", "EMPTY", "BLOCK_COMMENT", "LINE_COMMENT", 
		"SCIENCE_NOT", "BOOLEAN_VALUE", "NATURAL_VALUE", "NEGATIVE_VALUE", "DOUBLE_VALUE", 
		"APHOSTROPHE", "STRING_MULTILINE", "SINGLE_QUOTE", "EXPRESSION_MULTILINE", 
		"CLASS_TYPE", "ANCHOR_VALUE", "IDENTIFIER", "MEASURE_VALUE", "NEWLINE", 
		"SPACES", "DOC", "SP", "NL", "NEW_LINE_INDENT", "DEDENT", "UNKNOWN_TOKEN", 
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
		case 39:
			SEMICOLON_action((RuleContext)_localctx, actionIndex);
			break;
		case 60:
			APHOSTROPHE_action((RuleContext)_localctx, actionIndex);
			break;
		case 61:
			STRING_MULTILINE_action((RuleContext)_localctx, actionIndex);
			break;
		case 62:
			SINGLE_QUOTE_action((RuleContext)_localctx, actionIndex);
			break;
		case 63:
			EXPRESSION_MULTILINE_action((RuleContext)_localctx, actionIndex);
			break;
		case 68:
			NEWLINE_action((RuleContext)_localctx, actionIndex);
			break;
		case 70:
			DOC_action((RuleContext)_localctx, actionIndex);
			break;
		case 76:
			QUOTE_action((RuleContext)_localctx, actionIndex);
			break;
		case 77:
			Q_action((RuleContext)_localctx, actionIndex);
			break;
		case 78:
			SLASH_Q_action((RuleContext)_localctx, actionIndex);
			break;
		case 79:
			SLASH_action((RuleContext)_localctx, actionIndex);
			break;
		case 80:
			CHARACTER_action((RuleContext)_localctx, actionIndex);
			break;
		case 81:
			M_QUOTE_action((RuleContext)_localctx, actionIndex);
			break;
		case 82:
			M_CHARACTER_action((RuleContext)_localctx, actionIndex);
			break;
		case 83:
			ME_STRING_MULTILINE_action((RuleContext)_localctx, actionIndex);
			break;
		case 84:
			ME_CHARACTER_action((RuleContext)_localctx, actionIndex);
			break;
		case 85:
			E_QUOTE_action((RuleContext)_localctx, actionIndex);
			break;
		case 86:
			E_SLASH_Q_action((RuleContext)_localctx, actionIndex);
			break;
		case 87:
			E_SLASH_action((RuleContext)_localctx, actionIndex);
			break;
		case 88:
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2_\u035b\b\1\b\1\b"+
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
		"\t]\4^\t^\4_\t_\4`\t`\4a\ta\4b\tb\4c\tc\4d\td\4e\te\4f\tf\4g\tg\4h\th"+
		"\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3"+
		"\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13"+
		"\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3"+
		"\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3"+
		"\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3"+
		"\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3"+
		"\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3"+
		"\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3"+
		"\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3"+
		"\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\32\3"+
		"\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3\37\3 \3 \3!\3"+
		"!\3\"\3\"\3#\3#\3$\3$\3%\3%\3&\3&\3\'\3\'\3(\3(\3(\3(\3)\6)\u018c\n)\r"+
		")\16)\u018d\3)\3)\3*\3*\3+\3+\3+\3+\3+\3,\3,\3,\3,\3,\3,\3,\3,\3,\3-\3"+
		"-\3-\3-\3-\3-\3-\3-\3.\3.\3.\3.\3.\3.\3.\3.\3.\3/\3/\3/\3/\3/\3/\3/\3"+
		"\60\3\60\3\60\3\60\3\60\3\60\3\60\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3"+
		"\62\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\63\3\63\3\63\3\63\3\63\3\64\3"+
		"\64\3\64\3\64\3\64\3\64\3\64\3\64\3\65\3\65\3\65\3\65\3\65\3\66\3\66\3"+
		"\66\3\66\3\66\3\66\3\67\3\67\3\67\3\67\7\67\u01ec\n\67\f\67\16\67\u01ef"+
		"\13\67\3\67\3\67\3\67\3\67\3\67\38\78\u01f7\n8\f8\168\u01fa\138\38\78"+
		"\u01fd\n8\f8\168\u0200\138\38\38\38\38\78\u0206\n8\f8\168\u0209\138\3"+
		"8\38\39\39\39\59\u0210\n9\39\69\u0213\n9\r9\169\u0214\3:\3:\3:\3:\3:\3"+
		":\3:\3:\3:\5:\u0220\n:\3;\5;\u0223\n;\3;\6;\u0226\n;\r;\16;\u0227\3<\3"+
		"<\6<\u022c\n<\r<\16<\u022d\3=\3=\5=\u0232\n=\3=\6=\u0235\n=\r=\16=\u0236"+
		"\3=\3=\6=\u023b\n=\r=\16=\u023c\3>\3>\3>\3>\3>\3?\3?\6?\u0246\n?\r?\16"+
		"?\u0247\3?\3?\3?\3?\3@\3@\3@\3@\3@\3A\3A\6A\u0255\nA\rA\16A\u0256\3A\3"+
		"A\3A\3A\3B\3B\3B\3B\3B\3B\5B\u0263\nB\3B\3B\3C\3C\3C\6C\u026a\nC\rC\16"+
		"C\u026b\3C\3C\3D\3D\3D\3D\7D\u0274\nD\fD\16D\u0277\13D\3E\3E\3E\3E\3E"+
		"\5E\u027e\nE\3E\3E\3E\3E\3E\3E\3E\3E\3E\7E\u0289\nE\fE\16E\u028c\13E\3"+
		"F\6F\u028f\nF\rF\16F\u0290\3F\7F\u0294\nF\fF\16F\u0297\13F\3F\3F\3G\6"+
		"G\u029c\nG\rG\16G\u029d\3G\5G\u02a1\nG\3G\3G\3H\3H\3H\3H\7H\u02a9\nH\f"+
		"H\16H\u02ac\13H\3H\3H\3H\3I\3I\3J\5J\u02b4\nJ\3J\3J\5J\u02b8\nJ\3K\3K"+
		"\3K\3K\3K\3K\3K\3L\3L\3L\3L\3L\3L\3L\3M\3M\3N\3N\3N\3N\3N\3O\3O\3O\3P"+
		"\3P\3P\3P\3P\3Q\3Q\3Q\3R\3R\3R\3S\3S\6S\u02df\nS\rS\16S\u02e0\3S\3S\3"+
		"S\3S\3T\3T\3T\3U\3U\6U\u02ec\nU\rU\16U\u02ed\3U\3U\3U\3U\3V\3V\3V\3W\3"+
		"W\3W\3W\3W\3X\3X\3X\3X\3X\3Y\3Y\3Y\3Z\3Z\3Z\3[\3[\3[\3[\3[\3[\3[\3[\3"+
		"[\3[\3[\3[\3[\3[\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3]\3"+
		"]\3]\3]\3]\3]\3]\3]\3]\3]\3]\3]\3]\3]\3]\3]\3]\3]\3]\3^\3^\3^\3^\3^\3"+
		"^\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^\3_\3_\3`\3`\3a\3a\3b\3b\3c\3c\3d\3"+
		"d\3e\3e\3f\3f\3g\3g\3h\3h\3h\5h\u035a\nh\4\u01ed\u02aa\2i\7\3\t\4\13\5"+
		"\r\6\17\7\21\b\23\t\25\n\27\13\31\f\33\r\35\16\37\17!\20#\21%\22\'\23"+
		")\24+\25-\26/\27\61\30\63\31\65\32\67\339\34;\35=\36?\37A C!E\"G#I$K%"+
		"M&O\'Q(S)U*W+Y,[-]._/a\60c\61e\62g\63i\64k\65m\66o\67q8s9u:w;y<{=}>\177"+
		"?\u0081@\u0083A\u0085B\u0087C\u0089D\u008bE\u008dF\u008fG\u0091H\u0093"+
		"I\u0095J\u0097K\u0099L\u009bM\u009dN\u009fO\u00a1P\u00a3Q\u00a5R\u00a7"+
		"S\u00a9T\u00abU\u00adV\u00afW\u00b1X\u00b3Y\u00b5Z\u00b7[\u00b9\\\u00bb"+
		"]\u00bd^\u00bf_\u00c1\2\u00c3\2\u00c5\2\u00c7\2\u00c9\2\u00cb\2\u00cd"+
		"\2\u00cf\2\u00d1\2\u00d3\2\7\2\3\4\5\6\7\4\2\f\f\17\17\4\2\13\13\"\"\4"+
		"\2\u00b2\u00b2\u00bc\u00bc\3\2\62;\5\2C\\c|\u00f3\u00f3\u037b\2\7\3\2"+
		"\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2"+
		"\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3"+
		"\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3"+
		"\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65"+
		"\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3"+
		"\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2"+
		"\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2"+
		"[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3"+
		"\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q\3\2\2\2\2s\3\2\2"+
		"\2\2u\3\2\2\2\2w\3\2\2\2\2y\3\2\2\2\2{\3\2\2\2\2}\3\2\2\2\2\177\3\2\2"+
		"\2\2\u0081\3\2\2\2\2\u0083\3\2\2\2\2\u0085\3\2\2\2\2\u0087\3\2\2\2\2\u0089"+
		"\3\2\2\2\2\u008b\3\2\2\2\2\u008d\3\2\2\2\2\u008f\3\2\2\2\2\u0091\3\2\2"+
		"\2\2\u0093\3\2\2\2\2\u0095\3\2\2\2\2\u0097\3\2\2\2\2\u0099\3\2\2\2\2\u009b"+
		"\3\2\2\2\2\u009d\3\2\2\2\3\u009f\3\2\2\2\3\u00a1\3\2\2\2\3\u00a3\3\2\2"+
		"\2\3\u00a5\3\2\2\2\3\u00a7\3\2\2\2\4\u00a9\3\2\2\2\4\u00ab\3\2\2\2\5\u00ad"+
		"\3\2\2\2\5\u00af\3\2\2\2\6\u00b1\3\2\2\2\6\u00b3\3\2\2\2\6\u00b5\3\2\2"+
		"\2\6\u00b7\3\2\2\2\6\u00b9\3\2\2\2\6\u00bb\3\2\2\2\6\u00bd\3\2\2\2\6\u00bf"+
		"\3\2\2\2\7\u00d5\3\2\2\2\t\u00d9\3\2\2\2\13\u00dd\3\2\2\2\r\u00e1\3\2"+
		"\2\2\17\u00e5\3\2\2\2\21\u00e8\3\2\2\2\23\u00ec\3\2\2\2\25\u00ef\3\2\2"+
		"\2\27\u00f2\3\2\2\2\31\u00f7\3\2\2\2\33\u00fc\3\2\2\2\35\u0100\3\2\2\2"+
		"\37\u0108\3\2\2\2!\u0110\3\2\2\2#\u0119\3\2\2\2%\u0122\3\2\2\2\'\u012c"+
		"\3\2\2\2)\u0134\3\2\2\2+\u013a\3\2\2\2-\u0143\3\2\2\2/\u014b\3\2\2\2\61"+
		"\u0154\3\2\2\2\63\u015d\3\2\2\2\65\u0167\3\2\2\2\67\u0169\3\2\2\29\u016b"+
		"\3\2\2\2;\u016d\3\2\2\2=\u016f\3\2\2\2?\u0171\3\2\2\2A\u0173\3\2\2\2C"+
		"\u0176\3\2\2\2E\u0178\3\2\2\2G\u017a\3\2\2\2I\u017c\3\2\2\2K\u017e\3\2"+
		"\2\2M\u0180\3\2\2\2O\u0182\3\2\2\2Q\u0184\3\2\2\2S\u0186\3\2\2\2U\u018b"+
		"\3\2\2\2W\u0191\3\2\2\2Y\u0193\3\2\2\2[\u0198\3\2\2\2]\u01a1\3\2\2\2_"+
		"\u01a9\3\2\2\2a\u01b2\3\2\2\2c\u01b9\3\2\2\2e\u01c0\3\2\2\2g\u01c7\3\2"+
		"\2\2i\u01cf\3\2\2\2k\u01d4\3\2\2\2m\u01dc\3\2\2\2o\u01e1\3\2\2\2q\u01e7"+
		"\3\2\2\2s\u01f8\3\2\2\2u\u020c\3\2\2\2w\u021f\3\2\2\2y\u0222\3\2\2\2{"+
		"\u0229\3\2\2\2}\u0231\3\2\2\2\177\u023e\3\2\2\2\u0081\u0243\3\2\2\2\u0083"+
		"\u024d\3\2\2\2\u0085\u0252\3\2\2\2\u0087\u025c\3\2\2\2\u0089\u0266\3\2"+
		"\2\2\u008b\u026f\3\2\2\2\u008d\u027d\3\2\2\2\u008f\u028e\3\2\2\2\u0091"+
		"\u029b\3\2\2\2\u0093\u02a4\3\2\2\2\u0095\u02b0\3\2\2\2\u0097\u02b7\3\2"+
		"\2\2\u0099\u02b9\3\2\2\2\u009b\u02c0\3\2\2\2\u009d\u02c7\3\2\2\2\u009f"+
		"\u02c9\3\2\2\2\u00a1\u02ce\3\2\2\2\u00a3\u02d1\3\2\2\2\u00a5\u02d6\3\2"+
		"\2\2\u00a7\u02d9\3\2\2\2\u00a9\u02dc\3\2\2\2\u00ab\u02e6\3\2\2\2\u00ad"+
		"\u02e9\3\2\2\2\u00af\u02f3\3\2\2\2\u00b1\u02f6\3\2\2\2\u00b3\u02fb\3\2"+
		"\2\2\u00b5\u0300\3\2\2\2\u00b7\u0303\3\2\2\2\u00b9\u0306\3\2\2\2\u00bb"+
		"\u0314\3\2\2\2\u00bd\u0320\3\2\2\2\u00bf\u0333\3\2\2\2\u00c1\u0344\3\2"+
		"\2\2\u00c3\u0346\3\2\2\2\u00c5\u0348\3\2\2\2\u00c7\u034a\3\2\2\2\u00c9"+
		"\u034c\3\2\2\2\u00cb\u034e\3\2\2\2\u00cd\u0350\3\2\2\2\u00cf\u0352\3\2"+
		"\2\2\u00d1\u0354\3\2\2\2\u00d3\u0359\3\2\2\2\u00d5\u00d6\7u\2\2\u00d6"+
		"\u00d7\7w\2\2\u00d7\u00d8\7d\2\2\u00d8\b\3\2\2\2\u00d9\u00da\7w\2\2\u00da"+
		"\u00db\7u\2\2\u00db\u00dc\7g\2\2\u00dc\n\3\2\2\2\u00dd\u00de\7f\2\2\u00de"+
		"\u00df\7u\2\2\u00df\u00e0\7n\2\2\u00e0\f\3\2\2\2\u00e1\u00e2\7x\2\2\u00e2"+
		"\u00e3\7c\2\2\u00e3\u00e4\7t\2\2\u00e4\16\3\2\2\2\u00e5\u00e6\7c\2\2\u00e6"+
		"\u00e7\7u\2\2\u00e7\20\3\2\2\2\u00e8\u00e9\7j\2\2\u00e9\u00ea\7c\2\2\u00ea"+
		"\u00eb\7u\2\2\u00eb\22\3\2\2\2\u00ec\u00ed\7q\2\2\u00ed\u00ee\7p\2\2\u00ee"+
		"\24\3\2\2\2\u00ef\u00f0\7k\2\2\u00f0\u00f1\7u\2\2\u00f1\26\3\2\2\2\u00f2"+
		"\u00f3\7k\2\2\u00f3\u00f4\7p\2\2\u00f4\u00f5\7v\2\2\u00f5\u00f6\7q\2\2"+
		"\u00f6\30\3\2\2\2\u00f7\u00f8\7y\2\2\u00f8\u00f9\7k\2\2\u00f9\u00fa\7"+
		"v\2\2\u00fa\u00fb\7j\2\2\u00fb\32\3\2\2\2\u00fc\u00fd\7c\2\2\u00fd\u00fe"+
		"\7p\2\2\u00fe\u00ff\7{\2\2\u00ff\34\3\2\2\2\u0100\u0101\7g\2\2\u0101\u0102"+
		"\7z\2\2\u0102\u0103\7v\2\2\u0103\u0104\7g\2\2\u0104\u0105\7p\2\2\u0105"+
		"\u0106\7f\2\2\u0106\u0107\7u\2\2\u0107\36\3\2\2\2\u0108\u0109\7e\2\2\u0109"+
		"\u010a\7q\2\2\u010a\u010b\7p\2\2\u010b\u010c\7e\2\2\u010c\u010d\7g\2\2"+
		"\u010d\u010e\7r\2\2\u010e\u010f\7v\2\2\u010f \3\2\2\2\u0110\u0111\7c\2"+
		"\2\u0111\u0112\7d\2\2\u0112\u0113\7u\2\2\u0113\u0114\7v\2\2\u0114\u0115"+
		"\7t\2\2\u0115\u0116\7c\2\2\u0116\u0117\7e\2\2\u0117\u0118\7v\2\2\u0118"+
		"\"\3\2\2\2\u0119\u011a\7v\2\2\u011a\u011b\7g\2\2\u011b\u011c\7t\2\2\u011c"+
		"\u011d\7o\2\2\u011d\u011e\7k\2\2\u011e\u011f\7p\2\2\u011f\u0120\7c\2\2"+
		"\u0120\u0121\7n\2\2\u0121$\3\2\2\2\u0122\u0123\7e\2\2\u0123\u0124\7q\2"+
		"\2\u0124\u0125\7o\2\2\u0125\u0126\7r\2\2\u0126\u0127\7q\2\2\u0127\u0128"+
		"\7p\2\2\u0128\u0129\7g\2\2\u0129\u012a\7p\2\2\u012a\u012b\7v\2\2\u012b"+
		"&\3\2\2\2\u012c\u012d\7h\2\2\u012d\u012e\7g\2\2\u012e\u012f\7c\2\2\u012f"+
		"\u0130\7v\2\2\u0130\u0131\7w\2\2\u0131\u0132\7t\2\2\u0132\u0133\7g\2\2"+
		"\u0133(\3\2\2\2\u0134\u0135\7h\2\2\u0135\u0136\7k\2\2\u0136\u0137\7p\2"+
		"\2\u0137\u0138\7c\2\2\u0138\u0139\7n\2\2\u0139*\3\2\2\2\u013a\u013b\7"+
		"g\2\2\u013b\u013c\7p\2\2\u013c\u013d\7e\2\2\u013d\u013e\7n\2\2\u013e\u013f"+
		"\7q\2\2\u013f\u0140\7u\2\2\u0140\u0141\7g\2\2\u0141\u0142\7f\2\2\u0142"+
		",\3\2\2\2\u0143\u0144\7r\2\2\u0144\u0145\7t\2\2\u0145\u0146\7k\2\2\u0146"+
		"\u0147\7x\2\2\u0147\u0148\7c\2\2\u0148\u0149\7v\2\2\u0149\u014a\7g\2\2"+
		"\u014a.\3\2\2\2\u014b\u014c\7t\2\2\u014c\u014d\7g\2\2\u014d\u014e\7c\2"+
		"\2\u014e\u014f\7e\2\2\u014f\u0150\7v\2\2\u0150\u0151\7k\2\2\u0151\u0152"+
		"\7x\2\2\u0152\u0153\7g\2\2\u0153\60\3\2\2\2\u0154\u0155\7x\2\2\u0155\u0156"+
		"\7q\2\2\u0156\u0157\7n\2\2\u0157\u0158\7c\2\2\u0158\u0159\7v\2\2\u0159"+
		"\u015a\7k\2\2\u015a\u015b\7n\2\2\u015b\u015c\7g\2\2\u015c\62\3\2\2\2\u015d"+
		"\u015e\7x\2\2\u015e\u015f\7g\2\2\u015f\u0160\7t\2\2\u0160\u0161\7u\2\2"+
		"\u0161\u0162\7k\2\2\u0162\u0163\7q\2\2\u0163\u0164\7p\2\2\u0164\u0165"+
		"\7g\2\2\u0165\u0166\7f\2\2\u0166\64\3\2\2\2\u0167\u0168\7*\2\2\u0168\66"+
		"\3\2\2\2\u0169\u016a\7+\2\2\u016a8\3\2\2\2\u016b\u016c\7]\2\2\u016c:\3"+
		"\2\2\2\u016d\u016e\7_\2\2\u016e<\3\2\2\2\u016f\u0170\7}\2\2\u0170>\3\2"+
		"\2\2\u0171\u0172\7\177\2\2\u0172@\3\2\2\2\u0173\u0174\7@\2\2\u0174\u0175"+
		"\b\37\2\2\u0175B\3\2\2\2\u0176\u0177\7>\2\2\u0177D\3\2\2\2\u0178\u0179"+
		"\7B\2\2\u0179F\3\2\2\2\u017a\u017b\7%\2\2\u017bH\3\2\2\2\u017c\u017d\7"+
		"<\2\2\u017dJ\3\2\2\2\u017e\u017f\7.\2\2\u017fL\3\2\2\2\u0180\u0181\7\60"+
		"\2\2\u0181N\3\2\2\2\u0182\u0183\7?\2\2\u0183P\3\2\2\2\u0184\u0185\7,\2"+
		"\2\u0185R\3\2\2\2\u0186\u0187\7\60\2\2\u0187\u0188\7\60\2\2\u0188\u0189"+
		"\7\60\2\2\u0189T\3\2\2\2\u018a\u018c\7=\2\2\u018b\u018a\3\2\2\2\u018c"+
		"\u018d\3\2\2\2\u018d\u018b\3\2\2\2\u018d\u018e\3\2\2\2\u018e\u018f\3\2"+
		"\2\2\u018f\u0190\b)\3\2\u0190V\3\2\2\2\u0191\u0192\7-\2\2\u0192X\3\2\2"+
		"\2\u0193\u0194\7Y\2\2\u0194\u0195\7q\2\2\u0195\u0196\7t\2\2\u0196\u0197"+
		"\7f\2\2\u0197Z\3\2\2\2\u0198\u0199\7T\2\2\u0199\u019a\7g\2\2\u019a\u019b"+
		"\7u\2\2\u019b\u019c\7q\2\2\u019c\u019d\7w\2\2\u019d\u019e\7t\2\2\u019e"+
		"\u019f\7e\2\2\u019f\u01a0\7g\2\2\u01a0\\\3\2\2\2\u01a1\u01a2\7K\2\2\u01a2"+
		"\u01a3\7p\2\2\u01a3\u01a4\7v\2\2\u01a4\u01a5\7g\2\2\u01a5\u01a6\7i\2\2"+
		"\u01a6\u01a7\7g\2\2\u01a7\u01a8\7t\2\2\u01a8^\3\2\2\2\u01a9\u01aa\7H\2"+
		"\2\u01aa\u01ab\7w\2\2\u01ab\u01ac\7p\2\2\u01ac\u01ad\7e\2\2\u01ad\u01ae"+
		"\7v\2\2\u01ae\u01af\7k\2\2\u01af\u01b0\7q\2\2\u01b0\u01b1\7p\2\2\u01b1"+
		"`\3\2\2\2\u01b2\u01b3\7Q\2\2\u01b3\u01b4\7d\2\2\u01b4\u01b5\7l\2\2\u01b5"+
		"\u01b6\7g\2\2\u01b6\u01b7\7e\2\2\u01b7\u01b8\7v\2\2\u01b8b\3\2\2\2\u01b9"+
		"\u01ba\7F\2\2\u01ba\u01bb\7q\2\2\u01bb\u01bc\7w\2\2\u01bc\u01bd\7d\2\2"+
		"\u01bd\u01be\7n\2\2\u01be\u01bf\7g\2\2\u01bfd\3\2\2\2\u01c0\u01c1\7U\2"+
		"\2\u01c1\u01c2\7v\2\2\u01c2\u01c3\7t\2\2\u01c3\u01c4\7k\2\2\u01c4\u01c5"+
		"\7p\2\2\u01c5\u01c6\7i\2\2\u01c6f\3\2\2\2\u01c7\u01c8\7D\2\2\u01c8\u01c9"+
		"\7q\2\2\u01c9\u01ca\7q\2\2\u01ca\u01cb\7n\2\2\u01cb\u01cc\7g\2\2\u01cc"+
		"\u01cd\7c\2\2\u01cd\u01ce\7p\2\2\u01ceh\3\2\2\2\u01cf\u01d0\7F\2\2\u01d0"+
		"\u01d1\7c\2\2\u01d1\u01d2\7v\2\2\u01d2\u01d3\7g\2\2\u01d3j\3\2\2\2\u01d4"+
		"\u01d5\7K\2\2\u01d5\u01d6\7p\2\2\u01d6\u01d7\7u\2\2\u01d7\u01d8\7v\2\2"+
		"\u01d8\u01d9\7c\2\2\u01d9\u01da\7p\2\2\u01da\u01db\7v\2\2\u01dbl\3\2\2"+
		"\2\u01dc\u01dd\7V\2\2\u01dd\u01de\7k\2\2\u01de\u01df\7o\2\2\u01df\u01e0"+
		"\7g\2\2\u01e0n\3\2\2\2\u01e1\u01e2\7g\2\2\u01e2\u01e3\7o\2\2\u01e3\u01e4"+
		"\7r\2\2\u01e4\u01e5\7v\2\2\u01e5\u01e6\7{\2\2\u01e6p\3\2\2\2\u01e7\u01e8"+
		"\7\61\2\2\u01e8\u01e9\7,\2\2\u01e9\u01ed\3\2\2\2\u01ea\u01ec\13\2\2\2"+
		"\u01eb\u01ea\3\2\2\2\u01ec\u01ef\3\2\2\2\u01ed\u01ee\3\2\2\2\u01ed\u01eb"+
		"\3\2\2\2\u01ee\u01f0\3\2\2\2\u01ef\u01ed\3\2\2\2\u01f0\u01f1\7,\2\2\u01f1"+
		"\u01f2\7\61\2\2\u01f2\u01f3\3\2\2\2\u01f3\u01f4\b\67\4\2\u01f4r\3\2\2"+
		"\2\u01f5\u01f7\t\2\2\2\u01f6\u01f5\3\2\2\2\u01f7\u01fa\3\2\2\2\u01f8\u01f6"+
		"\3\2\2\2\u01f8\u01f9\3\2\2\2\u01f9\u01fe\3\2\2\2\u01fa\u01f8\3\2\2\2\u01fb"+
		"\u01fd\t\3\2\2\u01fc\u01fb\3\2\2\2\u01fd\u0200\3\2\2\2\u01fe\u01fc\3\2"+
		"\2\2\u01fe\u01ff\3\2\2\2\u01ff\u0201\3\2\2\2\u0200\u01fe\3\2\2\2\u0201"+
		"\u0202\7\61\2\2\u0202\u0203\7\61\2\2\u0203\u0207\3\2\2\2\u0204\u0206\n"+
		"\2\2\2\u0205\u0204\3\2\2\2\u0206\u0209\3\2\2\2\u0207\u0205\3\2\2\2\u0207"+
		"\u0208\3\2\2\2\u0208\u020a\3\2\2\2\u0209\u0207\3\2\2\2\u020a\u020b\b8"+
		"\4\2\u020bt\3\2\2\2\u020c\u020f\7G\2\2\u020d\u0210\5W*\2\u020e\u0210\5"+
		"\u00cde\2\u020f\u020d\3\2\2\2\u020f\u020e\3\2\2\2\u020f\u0210\3\2\2\2"+
		"\u0210\u0212\3\2\2\2\u0211\u0213\5\u00d1g\2\u0212\u0211\3\2\2\2\u0213"+
		"\u0214\3\2\2\2\u0214\u0212\3\2\2\2\u0214\u0215\3\2\2\2\u0215v\3\2\2\2"+
		"\u0216\u0217\7v\2\2\u0217\u0218\7t\2\2\u0218\u0219\7w\2\2\u0219\u0220"+
		"\7g\2\2\u021a\u021b\7h\2\2\u021b\u021c\7c\2\2\u021c\u021d\7n\2\2\u021d"+
		"\u021e\7u\2\2\u021e\u0220\7g\2\2\u021f\u0216\3\2\2\2\u021f\u021a\3\2\2"+
		"\2\u0220x\3\2\2\2\u0221\u0223\5W*\2\u0222\u0221\3\2\2\2\u0222\u0223\3"+
		"\2\2\2\u0223\u0225\3\2\2\2\u0224\u0226\5\u00d1g\2\u0225\u0224\3\2\2\2"+
		"\u0226\u0227\3\2\2\2\u0227\u0225\3\2\2\2\u0227\u0228\3\2\2\2\u0228z\3"+
		"\2\2\2\u0229\u022b\5\u00cde\2\u022a\u022c\5\u00d1g\2\u022b\u022a\3\2\2"+
		"\2\u022c\u022d\3\2\2\2\u022d\u022b\3\2\2\2\u022d\u022e\3\2\2\2\u022e|"+
		"\3\2\2\2\u022f\u0232\5W*\2\u0230\u0232\5\u00cde\2\u0231\u022f\3\2\2\2"+
		"\u0231\u0230\3\2\2\2\u0231\u0232\3\2\2\2\u0232\u0234\3\2\2\2\u0233\u0235"+
		"\5\u00d1g\2\u0234\u0233\3\2\2\2\u0235\u0236\3\2\2\2\u0236\u0234\3\2\2"+
		"\2\u0236\u0237\3\2\2\2\u0237\u0238\3\2\2\2\u0238\u023a\5M%\2\u0239\u023b"+
		"\5\u00d1g\2\u023a\u0239\3\2\2\2\u023b\u023c\3\2\2\2\u023c\u023a\3\2\2"+
		"\2\u023c\u023d\3\2\2\2\u023d~\3\2\2\2\u023e\u023f\7$\2\2\u023f\u0240\b"+
		">\5\2\u0240\u0241\3\2\2\2\u0241\u0242\b>\6\2\u0242\u0080\3\2\2\2\u0243"+
		"\u0245\5O&\2\u0244\u0246\5O&\2\u0245\u0244\3\2\2\2\u0246\u0247\3\2\2\2"+
		"\u0247\u0245\3\2\2\2\u0247\u0248\3\2\2\2\u0248\u0249\3\2\2\2\u0249\u024a"+
		"\b?\7\2\u024a\u024b\3\2\2\2\u024b\u024c\b?\b\2\u024c\u0082\3\2\2\2\u024d"+
		"\u024e\7)\2\2\u024e\u024f\b@\t\2\u024f\u0250\3\2\2\2\u0250\u0251\b@\n"+
		"\2\u0251\u0084\3\2\2\2\u0252\u0254\5\u00cde\2\u0253\u0255\5\u00cde\2\u0254"+
		"\u0253\3\2\2\2\u0255\u0256\3\2\2\2\u0256\u0254\3\2\2\2\u0256\u0257\3\2"+
		"\2\2\u0257\u0258\3\2\2\2\u0258\u0259\bA\13\2\u0259\u025a\3\2\2\2\u025a"+
		"\u025b\bA\f\2\u025b\u0086\3\2\2\2\u025c\u025d\5\u008bD\2\u025d\u025e\5"+
		"C \2\u025e\u0262\5\u008bD\2\u025f\u0260\5K$\2\u0260\u0261\5\u008bD\2\u0261"+
		"\u0263\3\2\2\2\u0262\u025f\3\2\2\2\u0262\u0263\3\2\2\2\u0263\u0264\3\2"+
		"\2\2\u0264\u0265\5A\37\2\u0265\u0088\3\2\2\2\u0266\u0269\5Q\'\2\u0267"+
		"\u026a\5\u00d1g\2\u0268\u026a\5\u00d3h\2\u0269\u0267\3\2\2\2\u0269\u0268"+
		"\3\2\2\2\u026a\u026b\3\2\2\2\u026b\u0269\3\2\2\2\u026b\u026c\3\2\2\2\u026c"+
		"\u026d\3\2\2\2\u026d\u026e\5Q\'\2\u026e\u008a\3\2\2\2\u026f\u0275\5\u00d3"+
		"h\2\u0270\u0274\5\u00d1g\2\u0271\u0274\5\u00d3h\2\u0272\u0274\5\u00cd"+
		"e\2\u0273\u0270\3\2\2\2\u0273\u0271\3\2\2\2\u0273\u0272\3\2\2\2\u0274"+
		"\u0277\3\2\2\2\u0275\u0273\3\2\2\2\u0275\u0276\3\2\2\2\u0276\u008c\3\2"+
		"\2\2\u0277\u0275\3\2\2\2\u0278\u027e\5\u00d3h\2\u0279\u027e\5\u00c5a\2"+
		"\u027a\u027e\5\u00c1_\2\u027b\u027e\5\u00c3`\2\u027c\u027e\5\u00c7b\2"+
		"\u027d\u0278\3\2\2\2\u027d\u0279\3\2\2\2\u027d\u027a\3\2\2\2\u027d\u027b"+
		"\3\2\2\2\u027d\u027c\3\2\2\2\u027e\u028a\3\2\2\2\u027f\u0289\5\u00cff"+
		"\2\u0280\u0289\5\u00c9c\2\u0281\u0289\5\u00cbd\2\u0282\u0289\5\u00c5a"+
		"\2\u0283\u0289\5\u00c1_\2\u0284\u0289\5\u00c3`\2\u0285\u0289\5\u00c7b"+
		"\2\u0286\u0289\5\u00d3h\2\u0287\u0289\5\u00d1g\2\u0288\u027f\3\2\2\2\u0288"+
		"\u0280\3\2\2\2\u0288\u0281\3\2\2\2\u0288\u0282\3\2\2\2\u0288\u0283\3\2"+
		"\2\2\u0288\u0284\3\2\2\2\u0288\u0285\3\2\2\2\u0288\u0286\3\2\2\2\u0288"+
		"\u0287\3\2\2\2\u0289\u028c\3\2\2\2\u028a\u0288\3\2\2\2\u028a\u028b\3\2"+
		"\2\2\u028b\u008e\3\2\2\2\u028c\u028a\3\2\2\2\u028d\u028f\5\u0097J\2\u028e"+
		"\u028d\3\2\2\2\u028f\u0290\3\2\2\2\u0290\u028e\3\2\2\2\u0290\u0291\3\2"+
		"\2\2\u0291\u0295\3\2\2\2\u0292\u0294\5\u0095I\2\u0293\u0292\3\2\2\2\u0294"+
		"\u0297\3\2\2\2\u0295\u0293\3\2\2\2\u0295\u0296\3\2\2\2\u0296\u0298\3\2"+
		"\2\2\u0297\u0295\3\2\2\2\u0298\u0299\bF\r\2\u0299\u0090\3\2\2\2\u029a"+
		"\u029c\5\u0095I\2\u029b\u029a\3\2\2\2\u029c\u029d\3\2\2\2\u029d\u029b"+
		"\3\2\2\2\u029d\u029e\3\2\2\2\u029e\u02a0\3\2\2\2\u029f\u02a1\7\2\2\3\u02a0"+
		"\u029f\3\2\2\2\u02a0\u02a1\3\2\2\2\u02a1\u02a2\3\2\2\2\u02a2\u02a3\bG"+
		"\16\2\u02a3\u0092\3\2\2\2\u02a4\u02a5\7#\2\2\u02a5\u02a6\7#\2\2\u02a6"+
		"\u02aa\3\2\2\2\u02a7\u02a9\13\2\2\2\u02a8\u02a7\3\2\2\2\u02a9\u02ac\3"+
		"\2\2\2\u02aa\u02ab\3\2\2\2\u02aa\u02a8\3\2\2\2\u02ab\u02ad\3\2\2\2\u02ac"+
		"\u02aa\3\2\2\2\u02ad\u02ae\5\u008fF\2\u02ae\u02af\bH\17\2\u02af\u0094"+
		"\3\2\2\2\u02b0\u02b1\t\3\2\2\u02b1\u0096\3\2\2\2\u02b2\u02b4\7\17\2\2"+
		"\u02b3\u02b2\3\2\2\2\u02b3\u02b4\3\2\2\2\u02b4\u02b5\3\2\2\2\u02b5\u02b8"+
		"\7\f\2\2\u02b6\u02b8\7\17\2\2\u02b7\u02b3\3\2\2\2\u02b7\u02b6\3\2\2\2"+
		"\u02b8\u0098\3\2\2\2\u02b9\u02ba\7k\2\2\u02ba\u02bb\7p\2\2\u02bb\u02bc"+
		"\7f\2\2\u02bc\u02bd\7g\2\2\u02bd\u02be\7p\2\2\u02be\u02bf\7v\2\2\u02bf"+
		"\u009a\3\2\2\2\u02c0\u02c1\7f\2\2\u02c1\u02c2\7g\2\2\u02c2\u02c3\7f\2"+
		"\2\u02c3\u02c4\7g\2\2\u02c4\u02c5\7p\2\2\u02c5\u02c6\7v\2\2\u02c6\u009c"+
		"\3\2\2\2\u02c7\u02c8\13\2\2\2\u02c8\u009e\3\2\2\2\u02c9\u02ca\7$\2\2\u02ca"+
		"\u02cb\bN\20\2\u02cb\u02cc\3\2\2\2\u02cc\u02cd\bN\21\2\u02cd\u00a0\3\2"+
		"\2\2\u02ce\u02cf\7$\2\2\u02cf\u02d0\bO\22\2\u02d0\u00a2\3\2\2\2\u02d1"+
		"\u02d2\7^\2\2\u02d2\u02d3\7$\2\2\u02d3\u02d4\3\2\2\2\u02d4\u02d5\bP\23"+
		"\2\u02d5\u00a4\3\2\2\2\u02d6\u02d7\7^\2\2\u02d7\u02d8\bQ\24\2\u02d8\u00a6"+
		"\3\2\2\2\u02d9\u02da\13\2\2\2\u02da\u02db\bR\25\2\u02db\u00a8\3\2\2\2"+
		"\u02dc\u02de\5O&\2\u02dd\u02df\5O&\2\u02de\u02dd\3\2\2\2\u02df\u02e0\3"+
		"\2\2\2\u02e0\u02de\3\2\2\2\u02e0\u02e1\3\2\2\2\u02e1\u02e2\3\2\2\2\u02e2"+
		"\u02e3\bS\26\2\u02e3\u02e4\3\2\2\2\u02e4\u02e5\bS\21\2\u02e5\u00aa\3\2"+
		"\2\2\u02e6\u02e7\13\2\2\2\u02e7\u02e8\bT\27\2\u02e8\u00ac\3\2\2\2\u02e9"+
		"\u02eb\5\u00cde\2\u02ea\u02ec\5\u00cde\2\u02eb\u02ea\3\2\2\2\u02ec\u02ed"+
		"\3\2\2\2\u02ed\u02eb\3\2\2\2\u02ed\u02ee\3\2\2\2\u02ee\u02ef\3\2\2\2\u02ef"+
		"\u02f0\bU\30\2\u02f0\u02f1\3\2\2\2\u02f1\u02f2\bU\21\2\u02f2\u00ae\3\2"+
		"\2\2\u02f3\u02f4\13\2\2\2\u02f4\u02f5\bV\31\2\u02f5\u00b0\3\2\2\2\u02f6"+
		"\u02f7\7)\2\2\u02f7\u02f8\bW\32\2\u02f8\u02f9\3\2\2\2\u02f9\u02fa\bW\21"+
		"\2\u02fa\u00b2\3\2\2\2\u02fb\u02fc\7^\2\2\u02fc\u02fd\7)\2\2\u02fd\u02fe"+
		"\3\2\2\2\u02fe\u02ff\bX\33\2\u02ff\u00b4\3\2\2\2\u0300\u0301\7^\2\2\u0301"+
		"\u0302\bY\34\2\u0302\u00b6\3\2\2\2\u0303\u0304\13\2\2\2\u0304\u0305\b"+
		"Z\35\2\u0305\u00b8\3\2\2\2\u0306\u0307\7\'\2\2\u0307\u0308\7S\2\2\u0308"+
		"\u0309\7W\2\2\u0309\u030a\7Q\2\2\u030a\u030b\7V\2\2\u030b\u030c\7G\2\2"+
		"\u030c\u030d\7a\2\2\u030d\u030e\7D\2\2\u030e\u030f\7G\2\2\u030f\u0310"+
		"\7I\2\2\u0310\u0311\7K\2\2\u0311\u0312\7P\2\2\u0312\u0313\7\'\2\2\u0313"+
		"\u00ba\3\2\2\2\u0314\u0315\7\'\2\2\u0315\u0316\7S\2\2\u0316\u0317\7W\2"+
		"\2\u0317\u0318\7Q\2\2\u0318\u0319\7V\2\2\u0319\u031a\7G\2\2\u031a\u031b"+
		"\7a\2\2\u031b\u031c\7G\2\2\u031c\u031d\7P\2\2\u031d\u031e\7F\2\2\u031e"+
		"\u031f\7\'\2\2\u031f\u00bc\3\2\2\2\u0320\u0321\7\'\2\2\u0321\u0322\7G"+
		"\2\2\u0322\u0323\7Z\2\2\u0323\u0324\7R\2\2\u0324\u0325\7T\2\2\u0325\u0326"+
		"\7G\2\2\u0326\u0327\7U\2\2\u0327\u0328\7U\2\2\u0328\u0329\7K\2\2\u0329"+
		"\u032a\7Q\2\2\u032a\u032b\7P\2\2\u032b\u032c\7a\2\2\u032c\u032d\7D\2\2"+
		"\u032d\u032e\7G\2\2\u032e\u032f\7I\2\2\u032f\u0330\7K\2\2\u0330\u0331"+
		"\7P\2\2\u0331\u0332\7\'\2\2\u0332\u00be\3\2\2\2\u0333\u0334\7\'\2\2\u0334"+
		"\u0335\7G\2\2\u0335\u0336\7Z\2\2\u0336\u0337\7R\2\2\u0337\u0338\7T\2\2"+
		"\u0338\u0339\7G\2\2\u0339\u033a\7U\2\2\u033a\u033b\7U\2\2\u033b\u033c"+
		"\7K\2\2\u033c\u033d\7Q\2\2\u033d\u033e\7P\2\2\u033e\u033f\7a\2\2\u033f"+
		"\u0340\7G\2\2\u0340\u0341\7P\2\2\u0341\u0342\7F\2\2\u0342\u0343\7\'\2"+
		"\2\u0343\u00c0\3\2\2\2\u0344\u0345\7&\2\2\u0345\u00c2\3\2\2\2\u0346\u0347"+
		"\7\u20ae\2\2\u0347\u00c4\3\2\2\2\u0348\u0349\7\'\2\2\u0349\u00c6\3\2\2"+
		"\2\u034a\u034b\t\4\2\2\u034b\u00c8\3\2\2\2\u034c\u034d\7\u00b9\2\2\u034d"+
		"\u00ca\3\2\2\2\u034e\u034f\7\61\2\2\u034f\u00cc\3\2\2\2\u0350\u0351\7"+
		"/\2\2\u0351\u00ce\3\2\2\2\u0352\u0353\7a\2\2\u0353\u00d0\3\2\2\2\u0354"+
		"\u0355\t\5\2\2\u0355\u00d2\3\2\2\2\u0356\u035a\t\6\2\2\u0357\u0358\7\u00d3"+
		"\2\2\u0358\u035a\4\u00fa\u0301\2\u0359\u0356\3\2\2\2\u0359\u0357\3\2\2"+
		"\2\u035a\u00d4\3\2\2\2*\2\3\4\5\6\u018d\u01ed\u01f8\u01fc\u01fe\u0207"+
		"\u020f\u0214\u021f\u0222\u0227\u022d\u0231\u0236\u023c\u0247\u0256\u0262"+
		"\u0269\u026b\u0273\u0275\u027d\u0288\u028a\u0290\u0295\u029d\u02a0\u02aa"+
		"\u02b3\u02b7\u02e0\u02ed\u0359\36\3\37\2\3)\3\b\2\2\3>\4\4\3\2\3?\5\4"+
		"\4\2\3@\6\4\6\2\3A\7\4\5\2\3F\b\2\3\2\3H\t\3N\n\4\2\2\3O\13\3P\f\3Q\r"+
		"\3R\16\3S\17\3T\20\3U\21\3V\22\3W\23\3X\24\3Y\25\3Z\26";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}