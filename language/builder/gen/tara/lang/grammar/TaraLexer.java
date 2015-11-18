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
		METAIDENTIFIER=1, SUB=2, USE=3, DSL=4, VAR=5, AS=6, HAS=7, ON=8, IS=9, 
		INTO=10, WITH=11, ANY=12, EXTENDS=13, ABSTRACT=14, TERMINAL=15, MAIN=16, 
		NAMED=17, DEFINITION=18, PROTOTYPE=19, PROFILER=20, FEATURE=21, FINAL=22, 
		ENCLOSED=23, PRIVATE=24, FACET=25, NATIVE=26, LEFT_PARENTHESIS=27, RIGHT_PARENTHESIS=28, 
		LEFT_SQUARE=29, RIGHT_SQUARE=30, LEFT_CURLY=31, RIGHT_CURLY=32, INLINE=33, 
		CLOSE_INLINE=34, HASHTAG=35, COLON=36, COMMA=37, DOT=38, EQUALS=39, STAR=40, 
		SEMICOLON=41, PLUS=42, WORD=43, RESOURCE=44, INT_TYPE=45, TUPLE_TYPE=46, 
		FUNCTION_TYPE=47, DOUBLE_TYPE=48, STRING_TYPE=49, BOOLEAN_TYPE=50, DATE_TYPE=51, 
		TIME_TYPE=52, EMPTY=53, BLOCK_COMMENT=54, LINE_COMMENT=55, SCIENCE_NOT=56, 
		BOOLEAN_VALUE=57, NATURAL_VALUE=58, NEGATIVE_VALUE=59, DOUBLE_VALUE=60, 
		APHOSTROPHE=61, STRING_MULTILINE=62, SINGLE_QUOTE=63, EXPRESSION_MULTILINE=64, 
		ANCHOR_VALUE=65, IDENTIFIER=66, MEASURE_VALUE=67, NEWLINE=68, SPACES=69, 
		DOC=70, SP=71, NL=72, NEW_LINE_INDENT=73, DEDENT=74, UNKNOWN_TOKEN=75, 
		QUOTE=76, Q=77, SLASH_Q=78, SLASH=79, CHARACTER=80, M_QUOTE=81, M_CHARACTER=82, 
		ME_STRING_MULTILINE=83, ME_CHARACTER=84, E_QUOTE=85, E_SLASH_Q=86, E_SLASH=87, 
		E_CHARACTER=88, QUOTE_BEGIN=89, QUOTE_END=90, EXPRESSION_BEGIN=91, EXPRESSION_END=92;
	public static final int QUOTED = 1;
	public static final int MULTILINE = 2;
	public static final int EXPRESSION_MULTILINE_MODE = 3;
	public static final int EXPRESSION_QUOTED = 4;
	public static String[] modeNames = {
		"DEFAULT_MODE", "QUOTED", "MULTILINE", "EXPRESSION_MULTILINE_MODE", "EXPRESSION_QUOTED"
	};

	public static final String[] ruleNames = {
		"METAIDENTIFIER", "SUB", "USE", "DSL", "VAR", "AS", "HAS", "ON", "IS", 
		"INTO", "WITH", "ANY", "EXTENDS", "ABSTRACT", "TERMINAL", "MAIN", "NAMED", 
		"DEFINITION", "PROTOTYPE", "PROFILER", "FEATURE", "FINAL", "ENCLOSED", 
		"PRIVATE", "FACET", "NATIVE", "LEFT_PARENTHESIS", "RIGHT_PARENTHESIS", 
		"LEFT_SQUARE", "RIGHT_SQUARE", "LEFT_CURLY", "RIGHT_CURLY", "INLINE", 
		"CLOSE_INLINE", "HASHTAG", "COLON", "COMMA", "DOT", "EQUALS", "STAR", 
		"SEMICOLON", "PLUS", "WORD", "RESOURCE", "INT_TYPE", "TUPLE_TYPE", "FUNCTION_TYPE", 
		"DOUBLE_TYPE", "STRING_TYPE", "BOOLEAN_TYPE", "DATE_TYPE", "TIME_TYPE", 
		"EMPTY", "BLOCK_COMMENT", "LINE_COMMENT", "SCIENCE_NOT", "BOOLEAN_VALUE", 
		"NATURAL_VALUE", "NEGATIVE_VALUE", "DOUBLE_VALUE", "APHOSTROPHE", "STRING_MULTILINE", 
		"SINGLE_QUOTE", "EXPRESSION_MULTILINE", "ANCHOR_VALUE", "IDENTIFIER", 
		"MEASURE_VALUE", "NEWLINE", "SPACES", "DOC", "SP", "NL", "NEW_LINE_INDENT", 
		"DEDENT", "UNKNOWN_TOKEN", "QUOTE", "Q", "SLASH_Q", "SLASH", "CHARACTER", 
		"M_QUOTE", "M_CHARACTER", "ME_STRING_MULTILINE", "ME_CHARACTER", "E_QUOTE", 
		"E_SLASH_Q", "E_SLASH", "E_CHARACTER", "QUOTE_BEGIN", "QUOTE_END", "EXPRESSION_BEGIN", 
		"EXPRESSION_END", "DOLLAR", "EURO", "PERCENTAGE", "GRADE", "BY", "DIVIDED_BY", 
		"DASH", "UNDERDASH", "DIGIT", "LETTER"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'Concept'", "'sub'", "'use'", "'dsl'", "'var'", "'as'", "'has'", 
		"'on'", "'is'", "'into'", "'with'", "'any'", "'extends'", "'abstract'", 
		"'terminal'", "'main'", "'named'", "'definition'", "'prototype'", "'profiler'", 
		"'feature'", "'final'", "'enclosed'", "'private'", "'facet'", "'native'", 
		"'('", "')'", "'['", "']'", "'{'", "'}'", "'>'", "'<'", "'#'", "':'", 
		"','", "'.'", "'='", "'*'", null, "'+'", "'word'", "'file'", "'integer'", 
		"'tuple'", "'function'", "'double'", "'string'", "'boolean'", "'date'", 
		"'time'", "'empty'", null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, "'indent'", 
		"'dedent'", null, null, "'\"'", "'\\\"'", null, null, null, null, null, 
		null, null, "'\\''", null, null, "'%QUOTE_BEGIN%'", "'%QUOTE_END%'", "'%EXPRESSION_BEGIN%'", 
		"'%EXPRESSION_END%'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "METAIDENTIFIER", "SUB", "USE", "DSL", "VAR", "AS", "HAS", "ON", 
		"IS", "INTO", "WITH", "ANY", "EXTENDS", "ABSTRACT", "TERMINAL", "MAIN", 
		"NAMED", "DEFINITION", "PROTOTYPE", "PROFILER", "FEATURE", "FINAL", "ENCLOSED", 
		"PRIVATE", "FACET", "NATIVE", "LEFT_PARENTHESIS", "RIGHT_PARENTHESIS", 
		"LEFT_SQUARE", "RIGHT_SQUARE", "LEFT_CURLY", "RIGHT_CURLY", "INLINE", 
		"CLOSE_INLINE", "HASHTAG", "COLON", "COMMA", "DOT", "EQUALS", "STAR", 
		"SEMICOLON", "PLUS", "WORD", "RESOURCE", "INT_TYPE", "TUPLE_TYPE", "FUNCTION_TYPE", 
		"DOUBLE_TYPE", "STRING_TYPE", "BOOLEAN_TYPE", "DATE_TYPE", "TIME_TYPE", 
		"EMPTY", "BLOCK_COMMENT", "LINE_COMMENT", "SCIENCE_NOT", "BOOLEAN_VALUE", 
		"NATURAL_VALUE", "NEGATIVE_VALUE", "DOUBLE_VALUE", "APHOSTROPHE", "STRING_MULTILINE", 
		"SINGLE_QUOTE", "EXPRESSION_MULTILINE", "ANCHOR_VALUE", "IDENTIFIER", 
		"MEASURE_VALUE", "NEWLINE", "SPACES", "DOC", "SP", "NL", "NEW_LINE_INDENT", 
		"DEDENT", "UNKNOWN_TOKEN", "QUOTE", "Q", "SLASH_Q", "SLASH", "CHARACTER", 
		"M_QUOTE", "M_CHARACTER", "ME_STRING_MULTILINE", "ME_CHARACTER", "E_QUOTE", 
		"E_SLASH_Q", "E_SLASH", "E_CHARACTER", "QUOTE_BEGIN", "QUOTE_END", "EXPRESSION_BEGIN", 
		"EXPRESSION_END"
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
		case 32:
			INLINE_action((RuleContext)_localctx, actionIndex);
			break;
		case 40:
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
		case 67:
			NEWLINE_action((RuleContext)_localctx, actionIndex);
			break;
		case 69:
			DOC_action((RuleContext)_localctx, actionIndex);
			break;
		case 75:
			QUOTE_action((RuleContext)_localctx, actionIndex);
			break;
		case 76:
			Q_action((RuleContext)_localctx, actionIndex);
			break;
		case 77:
			SLASH_Q_action((RuleContext)_localctx, actionIndex);
			break;
		case 78:
			SLASH_action((RuleContext)_localctx, actionIndex);
			break;
		case 79:
			CHARACTER_action((RuleContext)_localctx, actionIndex);
			break;
		case 80:
			M_QUOTE_action((RuleContext)_localctx, actionIndex);
			break;
		case 81:
			M_CHARACTER_action((RuleContext)_localctx, actionIndex);
			break;
		case 82:
			ME_STRING_MULTILINE_action((RuleContext)_localctx, actionIndex);
			break;
		case 83:
			ME_CHARACTER_action((RuleContext)_localctx, actionIndex);
			break;
		case 84:
			E_QUOTE_action((RuleContext)_localctx, actionIndex);
			break;
		case 85:
			E_SLASH_Q_action((RuleContext)_localctx, actionIndex);
			break;
		case 86:
			E_SLASH_action((RuleContext)_localctx, actionIndex);
			break;
		case 87:
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2^\u0349\b\1\b\1\b"+
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
		"\t]\4^\t^\4_\t_\4`\t`\4a\ta\4b\tb\4c\tc\4d\td\4e\te\4f\tf\4g\tg\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5"+
		"\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3"+
		"\n\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\16\3"+
		"\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3"+
		"\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3"+
		"\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3"+
		"\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3"+
		"\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3"+
		"\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3"+
		"\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3"+
		"\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3"+
		"\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3\"\3#\3#\3$\3$\3%\3"+
		"%\3&\3&\3\'\3\'\3(\3(\3)\3)\3*\6*\u0194\n*\r*\16*\u0195\3*\3*\3+\3+\3"+
		",\3,\3,\3,\3,\3-\3-\3-\3-\3-\3.\3.\3.\3.\3.\3.\3.\3.\3/\3/\3/\3/\3/\3"+
		"/\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\61\3\61\3\61\3\61\3\61"+
		"\3\61\3\61\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\63\3\63\3\63\3\63\3\63"+
		"\3\63\3\63\3\63\3\64\3\64\3\64\3\64\3\64\3\65\3\65\3\65\3\65\3\65\3\66"+
		"\3\66\3\66\3\66\3\66\3\66\3\67\3\67\3\67\3\67\7\67\u01e7\n\67\f\67\16"+
		"\67\u01ea\13\67\3\67\3\67\3\67\3\67\3\67\38\78\u01f2\n8\f8\168\u01f5\13"+
		"8\38\78\u01f8\n8\f8\168\u01fb\138\38\38\38\38\78\u0201\n8\f8\168\u0204"+
		"\138\38\38\39\39\39\59\u020b\n9\39\69\u020e\n9\r9\169\u020f\3:\3:\3:\3"+
		":\3:\3:\3:\3:\3:\5:\u021b\n:\3;\5;\u021e\n;\3;\6;\u0221\n;\r;\16;\u0222"+
		"\3<\3<\6<\u0227\n<\r<\16<\u0228\3=\3=\5=\u022d\n=\3=\6=\u0230\n=\r=\16"+
		"=\u0231\3=\3=\6=\u0236\n=\r=\16=\u0237\3>\3>\3>\3>\3>\3?\3?\6?\u0241\n"+
		"?\r?\16?\u0242\3?\3?\3?\3?\3@\3@\3@\3@\3@\3A\3A\6A\u0250\nA\rA\16A\u0251"+
		"\3A\3A\3A\3A\3B\3B\3B\6B\u025b\nB\rB\16B\u025c\3B\3B\3C\3C\3C\3C\7C\u0265"+
		"\nC\fC\16C\u0268\13C\3D\3D\3D\3D\3D\5D\u026f\nD\3D\3D\3D\3D\3D\3D\3D\3"+
		"D\3D\7D\u027a\nD\fD\16D\u027d\13D\3E\6E\u0280\nE\rE\16E\u0281\3E\7E\u0285"+
		"\nE\fE\16E\u0288\13E\3E\3E\3F\6F\u028d\nF\rF\16F\u028e\3F\5F\u0292\nF"+
		"\3F\3F\3G\3G\3G\3G\7G\u029a\nG\fG\16G\u029d\13G\3G\3G\3G\3H\3H\3I\5I\u02a5"+
		"\nI\3I\3I\5I\u02a9\nI\3J\3J\3J\3J\3J\3J\3J\3K\3K\3K\3K\3K\3K\3K\3L\3L"+
		"\3M\3M\3M\3M\3M\3N\3N\3N\3O\3O\3O\3O\3O\3P\3P\3P\3Q\3Q\3Q\3R\3R\6R\u02d0"+
		"\nR\rR\16R\u02d1\3R\3R\3R\3R\3S\3S\3S\3T\3T\6T\u02dd\nT\rT\16T\u02de\3"+
		"T\3T\3T\3T\3U\3U\3U\3V\3V\3V\3V\3V\3W\3W\3W\3W\3W\3X\3X\3X\3Y\3Y\3Y\3"+
		"Z\3Z\3Z\3Z\3Z\3Z\3Z\3Z\3Z\3Z\3Z\3Z\3Z\3Z\3[\3[\3[\3[\3[\3[\3[\3[\3[\3"+
		"[\3[\3[\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3"+
		"\\\3\\\3\\\3\\\3]\3]\3]\3]\3]\3]\3]\3]\3]\3]\3]\3]\3]\3]\3]\3]\3]\3^\3"+
		"^\3_\3_\3`\3`\3a\3a\3b\3b\3c\3c\3d\3d\3e\3e\3f\3f\3g\3g\4\u01e8\u029b"+
		"\2h\7\3\t\4\13\5\r\6\17\7\21\b\23\t\25\n\27\13\31\f\33\r\35\16\37\17!"+
		"\20#\21%\22\'\23)\24+\25-\26/\27\61\30\63\31\65\32\67\339\34;\35=\36?"+
		"\37A C!E\"G#I$K%M&O\'Q(S)U*W+Y,[-]._/a\60c\61e\62g\63i\64k\65m\66o\67"+
		"q8s9u:w;y<{=}>\177?\u0081@\u0083A\u0085B\u0087C\u0089D\u008bE\u008dF\u008f"+
		"G\u0091H\u0093I\u0095J\u0097K\u0099L\u009bM\u009dN\u009fO\u00a1P\u00a3"+
		"Q\u00a5R\u00a7S\u00a9T\u00abU\u00adV\u00afW\u00b1X\u00b3Y\u00b5Z\u00b7"+
		"[\u00b9\\\u00bb]\u00bd^\u00bf\2\u00c1\2\u00c3\2\u00c5\2\u00c7\2\u00c9"+
		"\2\u00cb\2\u00cd\2\u00cf\2\u00d1\2\7\2\3\4\5\6\7\4\2\f\f\17\17\4\2\13"+
		"\13\"\"\4\2\u00b2\u00b2\u00bc\u00bc\3\2\62;\6\2C\\c|\u00d3\u00d3\u00f3"+
		"\u00f3\u0367\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2"+
		"\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2"+
		"\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2"+
		"\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2"+
		"\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2"+
		"\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2"+
		"\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W"+
		"\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2"+
		"\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2"+
		"\2q\3\2\2\2\2s\3\2\2\2\2u\3\2\2\2\2w\3\2\2\2\2y\3\2\2\2\2{\3\2\2\2\2}"+
		"\3\2\2\2\2\177\3\2\2\2\2\u0081\3\2\2\2\2\u0083\3\2\2\2\2\u0085\3\2\2\2"+
		"\2\u0087\3\2\2\2\2\u0089\3\2\2\2\2\u008b\3\2\2\2\2\u008d\3\2\2\2\2\u008f"+
		"\3\2\2\2\2\u0091\3\2\2\2\2\u0093\3\2\2\2\2\u0095\3\2\2\2\2\u0097\3\2\2"+
		"\2\2\u0099\3\2\2\2\2\u009b\3\2\2\2\3\u009d\3\2\2\2\3\u009f\3\2\2\2\3\u00a1"+
		"\3\2\2\2\3\u00a3\3\2\2\2\3\u00a5\3\2\2\2\4\u00a7\3\2\2\2\4\u00a9\3\2\2"+
		"\2\5\u00ab\3\2\2\2\5\u00ad\3\2\2\2\6\u00af\3\2\2\2\6\u00b1\3\2\2\2\6\u00b3"+
		"\3\2\2\2\6\u00b5\3\2\2\2\6\u00b7\3\2\2\2\6\u00b9\3\2\2\2\6\u00bb\3\2\2"+
		"\2\6\u00bd\3\2\2\2\7\u00d3\3\2\2\2\t\u00db\3\2\2\2\13\u00df\3\2\2\2\r"+
		"\u00e3\3\2\2\2\17\u00e7\3\2\2\2\21\u00eb\3\2\2\2\23\u00ee\3\2\2\2\25\u00f2"+
		"\3\2\2\2\27\u00f5\3\2\2\2\31\u00f8\3\2\2\2\33\u00fd\3\2\2\2\35\u0102\3"+
		"\2\2\2\37\u0106\3\2\2\2!\u010e\3\2\2\2#\u0117\3\2\2\2%\u0120\3\2\2\2\'"+
		"\u0125\3\2\2\2)\u012b\3\2\2\2+\u0136\3\2\2\2-\u0140\3\2\2\2/\u0149\3\2"+
		"\2\2\61\u0151\3\2\2\2\63\u0157\3\2\2\2\65\u0160\3\2\2\2\67\u0168\3\2\2"+
		"\29\u016e\3\2\2\2;\u0175\3\2\2\2=\u0177\3\2\2\2?\u0179\3\2\2\2A\u017b"+
		"\3\2\2\2C\u017d\3\2\2\2E\u017f\3\2\2\2G\u0181\3\2\2\2I\u0184\3\2\2\2K"+
		"\u0186\3\2\2\2M\u0188\3\2\2\2O\u018a\3\2\2\2Q\u018c\3\2\2\2S\u018e\3\2"+
		"\2\2U\u0190\3\2\2\2W\u0193\3\2\2\2Y\u0199\3\2\2\2[\u019b\3\2\2\2]\u01a0"+
		"\3\2\2\2_\u01a5\3\2\2\2a\u01ad\3\2\2\2c\u01b3\3\2\2\2e\u01bc\3\2\2\2g"+
		"\u01c3\3\2\2\2i\u01ca\3\2\2\2k\u01d2\3\2\2\2m\u01d7\3\2\2\2o\u01dc\3\2"+
		"\2\2q\u01e2\3\2\2\2s\u01f3\3\2\2\2u\u0207\3\2\2\2w\u021a\3\2\2\2y\u021d"+
		"\3\2\2\2{\u0224\3\2\2\2}\u022c\3\2\2\2\177\u0239\3\2\2\2\u0081\u023e\3"+
		"\2\2\2\u0083\u0248\3\2\2\2\u0085\u024d\3\2\2\2\u0087\u0257\3\2\2\2\u0089"+
		"\u0260\3\2\2\2\u008b\u026e\3\2\2\2\u008d\u027f\3\2\2\2\u008f\u028c\3\2"+
		"\2\2\u0091\u0295\3\2\2\2\u0093\u02a1\3\2\2\2\u0095\u02a8\3\2\2\2\u0097"+
		"\u02aa\3\2\2\2\u0099\u02b1\3\2\2\2\u009b\u02b8\3\2\2\2\u009d\u02ba\3\2"+
		"\2\2\u009f\u02bf\3\2\2\2\u00a1\u02c2\3\2\2\2\u00a3\u02c7\3\2\2\2\u00a5"+
		"\u02ca\3\2\2\2\u00a7\u02cd\3\2\2\2\u00a9\u02d7\3\2\2\2\u00ab\u02da\3\2"+
		"\2\2\u00ad\u02e4\3\2\2\2\u00af\u02e7\3\2\2\2\u00b1\u02ec\3\2\2\2\u00b3"+
		"\u02f1\3\2\2\2\u00b5\u02f4\3\2\2\2\u00b7\u02f7\3\2\2\2\u00b9\u0305\3\2"+
		"\2\2\u00bb\u0311\3\2\2\2\u00bd\u0324\3\2\2\2\u00bf\u0335\3\2\2\2\u00c1"+
		"\u0337\3\2\2\2\u00c3\u0339\3\2\2\2\u00c5\u033b\3\2\2\2\u00c7\u033d\3\2"+
		"\2\2\u00c9\u033f\3\2\2\2\u00cb\u0341\3\2\2\2\u00cd\u0343\3\2\2\2\u00cf"+
		"\u0345\3\2\2\2\u00d1\u0347\3\2\2\2\u00d3\u00d4\7E\2\2\u00d4\u00d5\7q\2"+
		"\2\u00d5\u00d6\7p\2\2\u00d6\u00d7\7e\2\2\u00d7\u00d8\7g\2\2\u00d8\u00d9"+
		"\7r\2\2\u00d9\u00da\7v\2\2\u00da\b\3\2\2\2\u00db\u00dc\7u\2\2\u00dc\u00dd"+
		"\7w\2\2\u00dd\u00de\7d\2\2\u00de\n\3\2\2\2\u00df\u00e0\7w\2\2\u00e0\u00e1"+
		"\7u\2\2\u00e1\u00e2\7g\2\2\u00e2\f\3\2\2\2\u00e3\u00e4\7f\2\2\u00e4\u00e5"+
		"\7u\2\2\u00e5\u00e6\7n\2\2\u00e6\16\3\2\2\2\u00e7\u00e8\7x\2\2\u00e8\u00e9"+
		"\7c\2\2\u00e9\u00ea\7t\2\2\u00ea\20\3\2\2\2\u00eb\u00ec\7c\2\2\u00ec\u00ed"+
		"\7u\2\2\u00ed\22\3\2\2\2\u00ee\u00ef\7j\2\2\u00ef\u00f0\7c\2\2\u00f0\u00f1"+
		"\7u\2\2\u00f1\24\3\2\2\2\u00f2\u00f3\7q\2\2\u00f3\u00f4\7p\2\2\u00f4\26"+
		"\3\2\2\2\u00f5\u00f6\7k\2\2\u00f6\u00f7\7u\2\2\u00f7\30\3\2\2\2\u00f8"+
		"\u00f9\7k\2\2\u00f9\u00fa\7p\2\2\u00fa\u00fb\7v\2\2\u00fb\u00fc\7q\2\2"+
		"\u00fc\32\3\2\2\2\u00fd\u00fe\7y\2\2\u00fe\u00ff\7k\2\2\u00ff\u0100\7"+
		"v\2\2\u0100\u0101\7j\2\2\u0101\34\3\2\2\2\u0102\u0103\7c\2\2\u0103\u0104"+
		"\7p\2\2\u0104\u0105\7{\2\2\u0105\36\3\2\2\2\u0106\u0107\7g\2\2\u0107\u0108"+
		"\7z\2\2\u0108\u0109\7v\2\2\u0109\u010a\7g\2\2\u010a\u010b\7p\2\2\u010b"+
		"\u010c\7f\2\2\u010c\u010d\7u\2\2\u010d \3\2\2\2\u010e\u010f\7c\2\2\u010f"+
		"\u0110\7d\2\2\u0110\u0111\7u\2\2\u0111\u0112\7v\2\2\u0112\u0113\7t\2\2"+
		"\u0113\u0114\7c\2\2\u0114\u0115\7e\2\2\u0115\u0116\7v\2\2\u0116\"\3\2"+
		"\2\2\u0117\u0118\7v\2\2\u0118\u0119\7g\2\2\u0119\u011a\7t\2\2\u011a\u011b"+
		"\7o\2\2\u011b\u011c\7k\2\2\u011c\u011d\7p\2\2\u011d\u011e\7c\2\2\u011e"+
		"\u011f\7n\2\2\u011f$\3\2\2\2\u0120\u0121\7o\2\2\u0121\u0122\7c\2\2\u0122"+
		"\u0123\7k\2\2\u0123\u0124\7p\2\2\u0124&\3\2\2\2\u0125\u0126\7p\2\2\u0126"+
		"\u0127\7c\2\2\u0127\u0128\7o\2\2\u0128\u0129\7g\2\2\u0129\u012a\7f\2\2"+
		"\u012a(\3\2\2\2\u012b\u012c\7f\2\2\u012c\u012d\7g\2\2\u012d\u012e\7h\2"+
		"\2\u012e\u012f\7k\2\2\u012f\u0130\7p\2\2\u0130\u0131\7k\2\2\u0131\u0132"+
		"\7v\2\2\u0132\u0133\7k\2\2\u0133\u0134\7q\2\2\u0134\u0135\7p\2\2\u0135"+
		"*\3\2\2\2\u0136\u0137\7r\2\2\u0137\u0138\7t\2\2\u0138\u0139\7q\2\2\u0139"+
		"\u013a\7v\2\2\u013a\u013b\7q\2\2\u013b\u013c\7v\2\2\u013c\u013d\7{\2\2"+
		"\u013d\u013e\7r\2\2\u013e\u013f\7g\2\2\u013f,\3\2\2\2\u0140\u0141\7r\2"+
		"\2\u0141\u0142\7t\2\2\u0142\u0143\7q\2\2\u0143\u0144\7h\2\2\u0144\u0145"+
		"\7k\2\2\u0145\u0146\7n\2\2\u0146\u0147\7g\2\2\u0147\u0148\7t\2\2\u0148"+
		".\3\2\2\2\u0149\u014a\7h\2\2\u014a\u014b\7g\2\2\u014b\u014c\7c\2\2\u014c"+
		"\u014d\7v\2\2\u014d\u014e\7w\2\2\u014e\u014f\7t\2\2\u014f\u0150\7g\2\2"+
		"\u0150\60\3\2\2\2\u0151\u0152\7h\2\2\u0152\u0153\7k\2\2\u0153\u0154\7"+
		"p\2\2\u0154\u0155\7c\2\2\u0155\u0156\7n\2\2\u0156\62\3\2\2\2\u0157\u0158"+
		"\7g\2\2\u0158\u0159\7p\2\2\u0159\u015a\7e\2\2\u015a\u015b\7n\2\2\u015b"+
		"\u015c\7q\2\2\u015c\u015d\7u\2\2\u015d\u015e\7g\2\2\u015e\u015f\7f\2\2"+
		"\u015f\64\3\2\2\2\u0160\u0161\7r\2\2\u0161\u0162\7t\2\2\u0162\u0163\7"+
		"k\2\2\u0163\u0164\7x\2\2\u0164\u0165\7c\2\2\u0165\u0166\7v\2\2\u0166\u0167"+
		"\7g\2\2\u0167\66\3\2\2\2\u0168\u0169\7h\2\2\u0169\u016a\7c\2\2\u016a\u016b"+
		"\7e\2\2\u016b\u016c\7g\2\2\u016c\u016d\7v\2\2\u016d8\3\2\2\2\u016e\u016f"+
		"\7p\2\2\u016f\u0170\7c\2\2\u0170\u0171\7v\2\2\u0171\u0172\7k\2\2\u0172"+
		"\u0173\7x\2\2\u0173\u0174\7g\2\2\u0174:\3\2\2\2\u0175\u0176\7*\2\2\u0176"+
		"<\3\2\2\2\u0177\u0178\7+\2\2\u0178>\3\2\2\2\u0179\u017a\7]\2\2\u017a@"+
		"\3\2\2\2\u017b\u017c\7_\2\2\u017cB\3\2\2\2\u017d\u017e\7}\2\2\u017eD\3"+
		"\2\2\2\u017f\u0180\7\177\2\2\u0180F\3\2\2\2\u0181\u0182\7@\2\2\u0182\u0183"+
		"\b\"\2\2\u0183H\3\2\2\2\u0184\u0185\7>\2\2\u0185J\3\2\2\2\u0186\u0187"+
		"\7%\2\2\u0187L\3\2\2\2\u0188\u0189\7<\2\2\u0189N\3\2\2\2\u018a\u018b\7"+
		".\2\2\u018bP\3\2\2\2\u018c\u018d\7\60\2\2\u018dR\3\2\2\2\u018e\u018f\7"+
		"?\2\2\u018fT\3\2\2\2\u0190\u0191\7,\2\2\u0191V\3\2\2\2\u0192\u0194\7="+
		"\2\2\u0193\u0192\3\2\2\2\u0194\u0195\3\2\2\2\u0195\u0193\3\2\2\2\u0195"+
		"\u0196\3\2\2\2\u0196\u0197\3\2\2\2\u0197\u0198\b*\3\2\u0198X\3\2\2\2\u0199"+
		"\u019a\7-\2\2\u019aZ\3\2\2\2\u019b\u019c\7y\2\2\u019c\u019d\7q\2\2\u019d"+
		"\u019e\7t\2\2\u019e\u019f\7f\2\2\u019f\\\3\2\2\2\u01a0\u01a1\7h\2\2\u01a1"+
		"\u01a2\7k\2\2\u01a2\u01a3\7n\2\2\u01a3\u01a4\7g\2\2\u01a4^\3\2\2\2\u01a5"+
		"\u01a6\7k\2\2\u01a6\u01a7\7p\2\2\u01a7\u01a8\7v\2\2\u01a8\u01a9\7g\2\2"+
		"\u01a9\u01aa\7i\2\2\u01aa\u01ab\7g\2\2\u01ab\u01ac\7t\2\2\u01ac`\3\2\2"+
		"\2\u01ad\u01ae\7v\2\2\u01ae\u01af\7w\2\2\u01af\u01b0\7r\2\2\u01b0\u01b1"+
		"\7n\2\2\u01b1\u01b2\7g\2\2\u01b2b\3\2\2\2\u01b3\u01b4\7h\2\2\u01b4\u01b5"+
		"\7w\2\2\u01b5\u01b6\7p\2\2\u01b6\u01b7\7e\2\2\u01b7\u01b8\7v\2\2\u01b8"+
		"\u01b9\7k\2\2\u01b9\u01ba\7q\2\2\u01ba\u01bb\7p\2\2\u01bbd\3\2\2\2\u01bc"+
		"\u01bd\7f\2\2\u01bd\u01be\7q\2\2\u01be\u01bf\7w\2\2\u01bf\u01c0\7d\2\2"+
		"\u01c0\u01c1\7n\2\2\u01c1\u01c2\7g\2\2\u01c2f\3\2\2\2\u01c3\u01c4\7u\2"+
		"\2\u01c4\u01c5\7v\2\2\u01c5\u01c6\7t\2\2\u01c6\u01c7\7k\2\2\u01c7\u01c8"+
		"\7p\2\2\u01c8\u01c9\7i\2\2\u01c9h\3\2\2\2\u01ca\u01cb\7d\2\2\u01cb\u01cc"+
		"\7q\2\2\u01cc\u01cd\7q\2\2\u01cd\u01ce\7n\2\2\u01ce\u01cf\7g\2\2\u01cf"+
		"\u01d0\7c\2\2\u01d0\u01d1\7p\2\2\u01d1j\3\2\2\2\u01d2\u01d3\7f\2\2\u01d3"+
		"\u01d4\7c\2\2\u01d4\u01d5\7v\2\2\u01d5\u01d6\7g\2\2\u01d6l\3\2\2\2\u01d7"+
		"\u01d8\7v\2\2\u01d8\u01d9\7k\2\2\u01d9\u01da\7o\2\2\u01da\u01db\7g\2\2"+
		"\u01dbn\3\2\2\2\u01dc\u01dd\7g\2\2\u01dd\u01de\7o\2\2\u01de\u01df\7r\2"+
		"\2\u01df\u01e0\7v\2\2\u01e0\u01e1\7{\2\2\u01e1p\3\2\2\2\u01e2\u01e3\7"+
		"\61\2\2\u01e3\u01e4\7,\2\2\u01e4\u01e8\3\2\2\2\u01e5\u01e7\13\2\2\2\u01e6"+
		"\u01e5\3\2\2\2\u01e7\u01ea\3\2\2\2\u01e8\u01e9\3\2\2\2\u01e8\u01e6\3\2"+
		"\2\2\u01e9\u01eb\3\2\2\2\u01ea\u01e8\3\2\2\2\u01eb\u01ec\7,\2\2\u01ec"+
		"\u01ed\7\61\2\2\u01ed\u01ee\3\2\2\2\u01ee\u01ef\b\67\4\2\u01efr\3\2\2"+
		"\2\u01f0\u01f2\t\2\2\2\u01f1\u01f0\3\2\2\2\u01f2\u01f5\3\2\2\2\u01f3\u01f1"+
		"\3\2\2\2\u01f3\u01f4\3\2\2\2\u01f4\u01f9\3\2\2\2\u01f5\u01f3\3\2\2\2\u01f6"+
		"\u01f8\t\3\2\2\u01f7\u01f6\3\2\2\2\u01f8\u01fb\3\2\2\2\u01f9\u01f7\3\2"+
		"\2\2\u01f9\u01fa\3\2\2\2\u01fa\u01fc\3\2\2\2\u01fb\u01f9\3\2\2\2\u01fc"+
		"\u01fd\7\61\2\2\u01fd\u01fe\7\61\2\2\u01fe\u0202\3\2\2\2\u01ff\u0201\n"+
		"\2\2\2\u0200\u01ff\3\2\2\2\u0201\u0204\3\2\2\2\u0202\u0200\3\2\2\2\u0202"+
		"\u0203\3\2\2\2\u0203\u0205\3\2\2\2\u0204\u0202\3\2\2\2\u0205\u0206\b8"+
		"\4\2\u0206t\3\2\2\2\u0207\u020a\7G\2\2\u0208\u020b\5Y+\2\u0209\u020b\5"+
		"\u00cbd\2\u020a\u0208\3\2\2\2\u020a\u0209\3\2\2\2\u020a\u020b\3\2\2\2"+
		"\u020b\u020d\3\2\2\2\u020c\u020e\5\u00cff\2\u020d\u020c\3\2\2\2\u020e"+
		"\u020f\3\2\2\2\u020f\u020d\3\2\2\2\u020f\u0210\3\2\2\2\u0210v\3\2\2\2"+
		"\u0211\u0212\7v\2\2\u0212\u0213\7t\2\2\u0213\u0214\7w\2\2\u0214\u021b"+
		"\7g\2\2\u0215\u0216\7h\2\2\u0216\u0217\7c\2\2\u0217\u0218\7n\2\2\u0218"+
		"\u0219\7u\2\2\u0219\u021b\7g\2\2\u021a\u0211\3\2\2\2\u021a\u0215\3\2\2"+
		"\2\u021bx\3\2\2\2\u021c\u021e\5Y+\2\u021d\u021c\3\2\2\2\u021d\u021e\3"+
		"\2\2\2\u021e\u0220\3\2\2\2\u021f\u0221\5\u00cff\2\u0220\u021f\3\2\2\2"+
		"\u0221\u0222\3\2\2\2\u0222\u0220\3\2\2\2\u0222\u0223\3\2\2\2\u0223z\3"+
		"\2\2\2\u0224\u0226\5\u00cbd\2\u0225\u0227\5\u00cff\2\u0226\u0225\3\2\2"+
		"\2\u0227\u0228\3\2\2\2\u0228\u0226\3\2\2\2\u0228\u0229\3\2\2\2\u0229|"+
		"\3\2\2\2\u022a\u022d\5Y+\2\u022b\u022d\5\u00cbd\2\u022c\u022a\3\2\2\2"+
		"\u022c\u022b\3\2\2\2\u022c\u022d\3\2\2\2\u022d\u022f\3\2\2\2\u022e\u0230"+
		"\5\u00cff\2\u022f\u022e\3\2\2\2\u0230\u0231\3\2\2\2\u0231\u022f\3\2\2"+
		"\2\u0231\u0232\3\2\2\2\u0232\u0233\3\2\2\2\u0233\u0235\5Q\'\2\u0234\u0236"+
		"\5\u00cff\2\u0235\u0234\3\2\2\2\u0236\u0237\3\2\2\2\u0237\u0235\3\2\2"+
		"\2\u0237\u0238\3\2\2\2\u0238~\3\2\2\2\u0239\u023a\7$\2\2\u023a\u023b\b"+
		">\5\2\u023b\u023c\3\2\2\2\u023c\u023d\b>\6\2\u023d\u0080\3\2\2\2\u023e"+
		"\u0240\5S(\2\u023f\u0241\5S(\2\u0240\u023f\3\2\2\2\u0241\u0242\3\2\2\2"+
		"\u0242\u0240\3\2\2\2\u0242\u0243\3\2\2\2\u0243\u0244\3\2\2\2\u0244\u0245"+
		"\b?\7\2\u0245\u0246\3\2\2\2\u0246\u0247\b?\b\2\u0247\u0082\3\2\2\2\u0248"+
		"\u0249\7)\2\2\u0249\u024a\b@\t\2\u024a\u024b\3\2\2\2\u024b\u024c\b@\n"+
		"\2\u024c\u0084\3\2\2\2\u024d\u024f\5\u00cbd\2\u024e\u0250\5\u00cbd\2\u024f"+
		"\u024e\3\2\2\2\u0250\u0251\3\2\2\2\u0251\u024f\3\2\2\2\u0251\u0252\3\2"+
		"\2\2\u0252\u0253\3\2\2\2\u0253\u0254\bA\13\2\u0254\u0255\3\2\2\2\u0255"+
		"\u0256\bA\f\2\u0256\u0086\3\2\2\2\u0257\u025a\5U)\2\u0258\u025b\5\u00cf"+
		"f\2\u0259\u025b\5\u00d1g\2\u025a\u0258\3\2\2\2\u025a\u0259\3\2\2\2\u025b"+
		"\u025c\3\2\2\2\u025c\u025a\3\2\2\2\u025c\u025d\3\2\2\2\u025d\u025e\3\2"+
		"\2\2\u025e\u025f\5U)\2\u025f\u0088\3\2\2\2\u0260\u0266\5\u00d1g\2\u0261"+
		"\u0265\5\u00cff\2\u0262\u0265\5\u00d1g\2\u0263\u0265\5\u00cbd\2\u0264"+
		"\u0261\3\2\2\2\u0264\u0262\3\2\2\2\u0264\u0263\3\2\2\2\u0265\u0268\3\2"+
		"\2\2\u0266\u0264\3\2\2\2\u0266\u0267\3\2\2\2\u0267\u008a\3\2\2\2\u0268"+
		"\u0266\3\2\2\2\u0269\u026f\5\u00d1g\2\u026a\u026f\5\u00c3`\2\u026b\u026f"+
		"\5\u00bf^\2\u026c\u026f\5\u00c1_\2\u026d\u026f\5\u00c5a\2\u026e\u0269"+
		"\3\2\2\2\u026e\u026a\3\2\2\2\u026e\u026b\3\2\2\2\u026e\u026c\3\2\2\2\u026e"+
		"\u026d\3\2\2\2\u026f\u027b\3\2\2\2\u0270\u027a\5\u00cde\2\u0271\u027a"+
		"\5\u00c7b\2\u0272\u027a\5\u00c9c\2\u0273\u027a\5\u00c3`\2\u0274\u027a"+
		"\5\u00bf^\2\u0275\u027a\5\u00c1_\2\u0276\u027a\5\u00c5a\2\u0277\u027a"+
		"\5\u00d1g\2\u0278\u027a\5\u00cff\2\u0279\u0270\3\2\2\2\u0279\u0271\3\2"+
		"\2\2\u0279\u0272\3\2\2\2\u0279\u0273\3\2\2\2\u0279\u0274\3\2\2\2\u0279"+
		"\u0275\3\2\2\2\u0279\u0276\3\2\2\2\u0279\u0277\3\2\2\2\u0279\u0278\3\2"+
		"\2\2\u027a\u027d\3\2\2\2\u027b\u0279\3\2\2\2\u027b\u027c\3\2\2\2\u027c"+
		"\u008c\3\2\2\2\u027d\u027b\3\2\2\2\u027e\u0280\5\u0095I\2\u027f\u027e"+
		"\3\2\2\2\u0280\u0281\3\2\2\2\u0281\u027f\3\2\2\2\u0281\u0282\3\2\2\2\u0282"+
		"\u0286\3\2\2\2\u0283\u0285\5\u0093H\2\u0284\u0283\3\2\2\2\u0285\u0288"+
		"\3\2\2\2\u0286\u0284\3\2\2\2\u0286\u0287\3\2\2\2\u0287\u0289\3\2\2\2\u0288"+
		"\u0286\3\2\2\2\u0289\u028a\bE\r\2\u028a\u008e\3\2\2\2\u028b\u028d\5\u0093"+
		"H\2\u028c\u028b\3\2\2\2\u028d\u028e\3\2\2\2\u028e\u028c\3\2\2\2\u028e"+
		"\u028f\3\2\2\2\u028f\u0291\3\2\2\2\u0290\u0292\7\2\2\3\u0291\u0290\3\2"+
		"\2\2\u0291\u0292\3\2\2\2\u0292\u0293\3\2\2\2\u0293\u0294\bF\16\2\u0294"+
		"\u0090\3\2\2\2\u0295\u0296\7#\2\2\u0296\u0297\7#\2\2\u0297\u029b\3\2\2"+
		"\2\u0298\u029a\13\2\2\2\u0299\u0298\3\2\2\2\u029a\u029d\3\2\2\2\u029b"+
		"\u029c\3\2\2\2\u029b\u0299\3\2\2\2\u029c\u029e\3\2\2\2\u029d\u029b\3\2"+
		"\2\2\u029e\u029f\5\u008dE\2\u029f\u02a0\bG\17\2\u02a0\u0092\3\2\2\2\u02a1"+
		"\u02a2\t\3\2\2\u02a2\u0094\3\2\2\2\u02a3\u02a5\7\17\2\2\u02a4\u02a3\3"+
		"\2\2\2\u02a4\u02a5\3\2\2\2\u02a5\u02a6\3\2\2\2\u02a6\u02a9\7\f\2\2\u02a7"+
		"\u02a9\7\17\2\2\u02a8\u02a4\3\2\2\2\u02a8\u02a7\3\2\2\2\u02a9\u0096\3"+
		"\2\2\2\u02aa\u02ab\7k\2\2\u02ab\u02ac\7p\2\2\u02ac\u02ad\7f\2\2\u02ad"+
		"\u02ae\7g\2\2\u02ae\u02af\7p\2\2\u02af\u02b0\7v\2\2\u02b0\u0098\3\2\2"+
		"\2\u02b1\u02b2\7f\2\2\u02b2\u02b3\7g\2\2\u02b3\u02b4\7f\2\2\u02b4\u02b5"+
		"\7g\2\2\u02b5\u02b6\7p\2\2\u02b6\u02b7\7v\2\2\u02b7\u009a\3\2\2\2\u02b8"+
		"\u02b9\13\2\2\2\u02b9\u009c\3\2\2\2\u02ba\u02bb\7$\2\2\u02bb\u02bc\bM"+
		"\20\2\u02bc\u02bd\3\2\2\2\u02bd\u02be\bM\21\2\u02be\u009e\3\2\2\2\u02bf"+
		"\u02c0\7$\2\2\u02c0\u02c1\bN\22\2\u02c1\u00a0\3\2\2\2\u02c2\u02c3\7^\2"+
		"\2\u02c3\u02c4\7$\2\2\u02c4\u02c5\3\2\2\2\u02c5\u02c6\bO\23\2\u02c6\u00a2"+
		"\3\2\2\2\u02c7\u02c8\7^\2\2\u02c8\u02c9\bP\24\2\u02c9\u00a4\3\2\2\2\u02ca"+
		"\u02cb\13\2\2\2\u02cb\u02cc\bQ\25\2\u02cc\u00a6\3\2\2\2\u02cd\u02cf\5"+
		"S(\2\u02ce\u02d0\5S(\2\u02cf\u02ce\3\2\2\2\u02d0\u02d1\3\2\2\2\u02d1\u02cf"+
		"\3\2\2\2\u02d1\u02d2\3\2\2\2\u02d2\u02d3\3\2\2\2\u02d3\u02d4\bR\26\2\u02d4"+
		"\u02d5\3\2\2\2\u02d5\u02d6\bR\21\2\u02d6\u00a8\3\2\2\2\u02d7\u02d8\13"+
		"\2\2\2\u02d8\u02d9\bS\27\2\u02d9\u00aa\3\2\2\2\u02da\u02dc\5\u00cbd\2"+
		"\u02db\u02dd\5\u00cbd\2\u02dc\u02db\3\2\2\2\u02dd\u02de\3\2\2\2\u02de"+
		"\u02dc\3\2\2\2\u02de\u02df\3\2\2\2\u02df\u02e0\3\2\2\2\u02e0\u02e1\bT"+
		"\30\2\u02e1\u02e2\3\2\2\2\u02e2\u02e3\bT\21\2\u02e3\u00ac\3\2\2\2\u02e4"+
		"\u02e5\13\2\2\2\u02e5\u02e6\bU\31\2\u02e6\u00ae\3\2\2\2\u02e7\u02e8\7"+
		")\2\2\u02e8\u02e9\bV\32\2\u02e9\u02ea\3\2\2\2\u02ea\u02eb\bV\21\2\u02eb"+
		"\u00b0\3\2\2\2\u02ec\u02ed\7^\2\2\u02ed\u02ee\7)\2\2\u02ee\u02ef\3\2\2"+
		"\2\u02ef\u02f0\bW\33\2\u02f0\u00b2\3\2\2\2\u02f1\u02f2\7^\2\2\u02f2\u02f3"+
		"\bX\34\2\u02f3\u00b4\3\2\2\2\u02f4\u02f5\13\2\2\2\u02f5\u02f6\bY\35\2"+
		"\u02f6\u00b6\3\2\2\2\u02f7\u02f8\7\'\2\2\u02f8\u02f9\7S\2\2\u02f9\u02fa"+
		"\7W\2\2\u02fa\u02fb\7Q\2\2\u02fb\u02fc\7V\2\2\u02fc\u02fd\7G\2\2\u02fd"+
		"\u02fe\7a\2\2\u02fe\u02ff\7D\2\2\u02ff\u0300\7G\2\2\u0300\u0301\7I\2\2"+
		"\u0301\u0302\7K\2\2\u0302\u0303\7P\2\2\u0303\u0304\7\'\2\2\u0304\u00b8"+
		"\3\2\2\2\u0305\u0306\7\'\2\2\u0306\u0307\7S\2\2\u0307\u0308\7W\2\2\u0308"+
		"\u0309\7Q\2\2\u0309\u030a\7V\2\2\u030a\u030b\7G\2\2\u030b\u030c\7a\2\2"+
		"\u030c\u030d\7G\2\2\u030d\u030e\7P\2\2\u030e\u030f\7F\2\2\u030f\u0310"+
		"\7\'\2\2\u0310\u00ba\3\2\2\2\u0311\u0312\7\'\2\2\u0312\u0313\7G\2\2\u0313"+
		"\u0314\7Z\2\2\u0314\u0315\7R\2\2\u0315\u0316\7T\2\2\u0316\u0317\7G\2\2"+
		"\u0317\u0318\7U\2\2\u0318\u0319\7U\2\2\u0319\u031a\7K\2\2\u031a\u031b"+
		"\7Q\2\2\u031b\u031c\7P\2\2\u031c\u031d\7a\2\2\u031d\u031e\7D\2\2\u031e"+
		"\u031f\7G\2\2\u031f\u0320\7I\2\2\u0320\u0321\7K\2\2\u0321\u0322\7P\2\2"+
		"\u0322\u0323\7\'\2\2\u0323\u00bc\3\2\2\2\u0324\u0325\7\'\2\2\u0325\u0326"+
		"\7G\2\2\u0326\u0327\7Z\2\2\u0327\u0328\7R\2\2\u0328\u0329\7T\2\2\u0329"+
		"\u032a\7G\2\2\u032a\u032b\7U\2\2\u032b\u032c\7U\2\2\u032c\u032d\7K\2\2"+
		"\u032d\u032e\7Q\2\2\u032e\u032f\7P\2\2\u032f\u0330\7a\2\2\u0330\u0331"+
		"\7G\2\2\u0331\u0332\7P\2\2\u0332\u0333\7F\2\2\u0333\u0334\7\'\2\2\u0334"+
		"\u00be\3\2\2\2\u0335\u0336\7&\2\2\u0336\u00c0\3\2\2\2\u0337\u0338\7\u20ae"+
		"\2\2\u0338\u00c2\3\2\2\2\u0339\u033a\7\'\2\2\u033a\u00c4\3\2\2\2\u033b"+
		"\u033c\t\4\2\2\u033c\u00c6\3\2\2\2\u033d\u033e\7\u00b9\2\2\u033e\u00c8"+
		"\3\2\2\2\u033f\u0340\7\61\2\2\u0340\u00ca\3\2\2\2\u0341\u0342\7/\2\2\u0342"+
		"\u00cc\3\2\2\2\u0343\u0344\7a\2\2\u0344\u00ce\3\2\2\2\u0345\u0346\t\5"+
		"\2\2\u0346\u00d0\3\2\2\2\u0347\u0348\t\6\2\2\u0348\u00d2\3\2\2\2(\2\3"+
		"\4\5\6\u0195\u01e8\u01f3\u01f7\u01f9\u0202\u020a\u020f\u021a\u021d\u0222"+
		"\u0228\u022c\u0231\u0237\u0242\u0251\u025a\u025c\u0264\u0266\u026e\u0279"+
		"\u027b\u0281\u0286\u028e\u0291\u029b\u02a4\u02a8\u02d1\u02de\36\3\"\2"+
		"\3*\3\b\2\2\3>\4\4\3\2\3?\5\4\4\2\3@\6\4\6\2\3A\7\4\5\2\3E\b\2\3\2\3G"+
		"\t\3M\n\4\2\2\3N\13\3O\f\3P\r\3Q\16\3R\17\3S\20\3T\21\3U\22\3V\23\3W\24"+
		"\3X\25\3Y\26";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}