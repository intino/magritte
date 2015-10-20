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
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		METAIDENTIFIER=1, SUB=2, USE=3, DSL=4, VAR=5, AS=6, HAS=7, ON=8, IS=9, 
		INTO=10, WITH=11, ANY=12, EXTENDS=13, ABSTRACT=14, SINGLE=15, REQUIRED=16, 
		TERMINAL=17, MAIN=18, NAMED=19, PROTOTYPE=20, FEATURE=21, FINAL=22, ENCLOSED=23, 
		PRIVATE=24, FACET=25, LEFT_PARENTHESIS=26, RIGHT_PARENTHESIS=27, LEFT_SQUARE=28, 
		RIGHT_SQUARE=29, LIST=30, INLINE=31, CLOSE_INLINE=32, HASHTAG=33, COLON=34, 
		COMMA=35, DOT=36, EQUALS=37, SEMICOLON=38, PLUS=39, WORD=40, RESOURCE=41, 
		INT_TYPE=42, TUPLE_TYPE=43, NATURAL_TYPE=44, NATIVE_TYPE=45, DOUBLE_TYPE=46, 
		STRING_TYPE=47, BOOLEAN_TYPE=48, MEASURE_TYPE=49, RATIO_TYPE=50, DATE_TYPE=51, 
		TYPE_TYPE=52, TIME_TYPE=53, EMPTY=54, BLOCK_COMMENT=55, LINE_COMMENT=56, 
		SCIENCE_NOT=57, BOOLEAN_VALUE=58, NATURAL_VALUE=59, NEGATIVE_VALUE=60, 
		DOUBLE_VALUE=61, APHOSTROPHE=62, STRING_MULTILINE=63, SINGLE_QUOTE=64, 
		EXPRESSION_MULTILINE=65, PLATE_VALUE=66, IDENTIFIER=67, MEASURE_VALUE=68, 
		NEWLINE=69, SPACES=70, DOC=71, SP=72, NL=73, NEW_LINE_INDENT=74, DEDENT=75, 
		UNKNOWN_TOKEN=76, QUOTE=77, Q=78, SLASH_Q=79, SLASH=80, CHARACTER=81, 
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
		"METAIDENTIFIER", "SUB", "USE", "DSL", "VAR", "AS", "HAS", "ON", "IS", 
		"INTO", "WITH", "ANY", "EXTENDS", "ABSTRACT", "SINGLE", "REQUIRED", "TERMINAL", 
		"MAIN", "NAMED", "PROTOTYPE", "FEATURE", "FINAL", "ENCLOSED", "PRIVATE", 
		"FACET", "LEFT_PARENTHESIS", "RIGHT_PARENTHESIS", "LEFT_SQUARE", "RIGHT_SQUARE", 
		"LIST", "INLINE", "CLOSE_INLINE", "HASHTAG", "COLON", "COMMA", "DOT", 
		"EQUALS", "SEMICOLON", "PLUS", "WORD", "RESOURCE", "INT_TYPE", "TUPLE_TYPE", 
		"NATURAL_TYPE", "NATIVE_TYPE", "DOUBLE_TYPE", "STRING_TYPE", "BOOLEAN_TYPE", 
		"MEASURE_TYPE", "RATIO_TYPE", "DATE_TYPE", "TYPE_TYPE", "TIME_TYPE", "EMPTY", 
		"BLOCK_COMMENT", "LINE_COMMENT", "SCIENCE_NOT", "BOOLEAN_VALUE", "NATURAL_VALUE", 
		"NEGATIVE_VALUE", "DOUBLE_VALUE", "APHOSTROPHE", "STRING_MULTILINE", "SINGLE_QUOTE", 
		"EXPRESSION_MULTILINE", "PLATE_VALUE", "IDENTIFIER", "MEASURE_VALUE", 
		"NEWLINE", "SPACES", "DOC", "SP", "NL", "NEW_LINE_INDENT", "DEDENT", "UNKNOWN_TOKEN", 
		"QUOTE", "Q", "SLASH_Q", "SLASH", "CHARACTER", "M_QUOTE", "M_CHARACTER", 
		"ME_STRING_MULTILINE", "ME_CHARACTER", "E_QUOTE", "E_SLASH_Q", "E_SLASH", 
		"E_CHARACTER", "QUOTE_BEGIN", "QUOTE_END", "EXPRESSION_BEGIN", "EXPRESSION_END", 
		"DOLLAR", "EURO", "PERCENTAGE", "GRADE", "BY", "DIVIDED_BY", "DASH", "UNDERDASH", 
		"DIGIT", "LETTER"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'Concept'", "'sub'", "'use'", "'dsl'", "'var'", "'as'", "'has'", 
		"'on'", "'is'", "'into'", "'with'", "'any'", "'extends'", "'abstract'", 
		"'single'", "'required'", "'terminal'", "'main'", "'named'", "'prototype'", 
		"'feature'", "'final'", "'enclosed'", "'private'", "'facet'", "'('", "')'", 
		"'['", "']'", "'...'", "'>'", "'<'", "'#'", "':'", "','", "'.'", "'='", 
		null, "'+'", "'word'", "'file'", "'integer'", "'tuple'", "'natural'", 
		"'native'", "'double'", "'string'", "'boolean'", "'measure'", "'ratio'", 
		"'date'", "'type'", "'time'", "'empty'", null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, "'indent'", "'dedent'", null, null, "'\"'", "'\\\"'", null, 
		null, null, null, null, null, null, "'\\''", null, null, "'%QUOTE_BEGIN%'", 
		"'%QUOTE_END%'", "'%EXPRESSION_BEGIN%'", "'%EXPRESSION_END%'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "METAIDENTIFIER", "SUB", "USE", "DSL", "VAR", "AS", "HAS", "ON", 
		"IS", "INTO", "WITH", "ANY", "EXTENDS", "ABSTRACT", "SINGLE", "REQUIRED", 
		"TERMINAL", "MAIN", "NAMED", "PROTOTYPE", "FEATURE", "FINAL", "ENCLOSED", 
		"PRIVATE", "FACET", "LEFT_PARENTHESIS", "RIGHT_PARENTHESIS", "LEFT_SQUARE", 
		"RIGHT_SQUARE", "LIST", "INLINE", "CLOSE_INLINE", "HASHTAG", "COLON", 
		"COMMA", "DOT", "EQUALS", "SEMICOLON", "PLUS", "WORD", "RESOURCE", "INT_TYPE", 
		"TUPLE_TYPE", "NATURAL_TYPE", "NATIVE_TYPE", "DOUBLE_TYPE", "STRING_TYPE", 
		"BOOLEAN_TYPE", "MEASURE_TYPE", "RATIO_TYPE", "DATE_TYPE", "TYPE_TYPE", 
		"TIME_TYPE", "EMPTY", "BLOCK_COMMENT", "LINE_COMMENT", "SCIENCE_NOT", 
		"BOOLEAN_VALUE", "NATURAL_VALUE", "NEGATIVE_VALUE", "DOUBLE_VALUE", "APHOSTROPHE", 
		"STRING_MULTILINE", "SINGLE_QUOTE", "EXPRESSION_MULTILINE", "PLATE_VALUE", 
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
		case 30:
			INLINE_action((RuleContext)_localctx, actionIndex);
			break;
		case 37:
			SEMICOLON_action((RuleContext)_localctx, actionIndex);
			break;
		case 61:
			APHOSTROPHE_action((RuleContext)_localctx, actionIndex);
			break;
		case 62:
			STRING_MULTILINE_action((RuleContext)_localctx, actionIndex);
			break;
		case 63:
			SINGLE_QUOTE_action((RuleContext)_localctx, actionIndex);
			break;
		case 64:
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2_\u0354\b\1\b\1\b"+
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
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3"+
		"\5\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\n"+
		"\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26"+
		"\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30"+
		"\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32"+
		"\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3\37\3\37"+
		"\3 \3 \3 \3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\3&\3&\3\'\6\'\u0189\n\'\r\'"+
		"\16\'\u018a\3\'\3\'\3(\3(\3)\3)\3)\3)\3)\3*\3*\3*\3*\3*\3+\3+\3+\3+\3"+
		"+\3+\3+\3+\3,\3,\3,\3,\3,\3,\3-\3-\3-\3-\3-\3-\3-\3-\3.\3.\3.\3.\3.\3"+
		".\3.\3/\3/\3/\3/\3/\3/\3/\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\61\3\61"+
		"\3\61\3\61\3\61\3\61\3\61\3\61\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\62"+
		"\3\63\3\63\3\63\3\63\3\63\3\63\3\64\3\64\3\64\3\64\3\64\3\65\3\65\3\65"+
		"\3\65\3\65\3\66\3\66\3\66\3\66\3\66\3\67\3\67\3\67\3\67\3\67\3\67\38\3"+
		"8\38\38\78\u01f5\n8\f8\168\u01f8\138\38\38\38\38\38\39\79\u0200\n9\f9"+
		"\169\u0203\139\39\79\u0206\n9\f9\169\u0209\139\39\39\39\39\79\u020f\n"+
		"9\f9\169\u0212\139\39\39\3:\3:\3:\5:\u0219\n:\3:\6:\u021c\n:\r:\16:\u021d"+
		"\3;\3;\3;\3;\3;\3;\3;\3;\3;\5;\u0229\n;\3<\5<\u022c\n<\3<\6<\u022f\n<"+
		"\r<\16<\u0230\3=\3=\6=\u0235\n=\r=\16=\u0236\3>\3>\5>\u023b\n>\3>\6>\u023e"+
		"\n>\r>\16>\u023f\3>\3>\6>\u0244\n>\r>\16>\u0245\3?\3?\3?\3?\3?\3@\3@\6"+
		"@\u024f\n@\r@\16@\u0250\3@\3@\3@\3@\3A\3A\3A\3A\3A\3B\3B\6B\u025e\nB\r"+
		"B\16B\u025f\3B\3B\3B\3B\3C\3C\6C\u0268\nC\rC\16C\u0269\3D\3D\3D\3D\7D"+
		"\u0270\nD\fD\16D\u0273\13D\3E\3E\3E\3E\3E\5E\u027a\nE\3E\3E\3E\3E\3E\3"+
		"E\3E\3E\3E\7E\u0285\nE\fE\16E\u0288\13E\3F\6F\u028b\nF\rF\16F\u028c\3"+
		"F\7F\u0290\nF\fF\16F\u0293\13F\3F\3F\3G\6G\u0298\nG\rG\16G\u0299\3G\5"+
		"G\u029d\nG\3G\3G\3H\3H\3H\3H\7H\u02a5\nH\fH\16H\u02a8\13H\3H\3H\3H\3I"+
		"\3I\3J\5J\u02b0\nJ\3J\3J\5J\u02b4\nJ\3K\3K\3K\3K\3K\3K\3K\3L\3L\3L\3L"+
		"\3L\3L\3L\3M\3M\3N\3N\3N\3N\3N\3O\3O\3O\3P\3P\3P\3P\3P\3Q\3Q\3Q\3R\3R"+
		"\3R\3S\3S\6S\u02db\nS\rS\16S\u02dc\3S\3S\3S\3S\3T\3T\3T\3U\3U\6U\u02e8"+
		"\nU\rU\16U\u02e9\3U\3U\3U\3U\3V\3V\3V\3W\3W\3W\3W\3W\3X\3X\3X\3X\3X\3"+
		"Y\3Y\3Y\3Z\3Z\3Z\3[\3[\3[\3[\3[\3[\3[\3[\3[\3[\3[\3[\3[\3[\3\\\3\\\3\\"+
		"\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3]\3]\3]\3]\3]\3]\3]\3]\3]\3]\3]"+
		"\3]\3]\3]\3]\3]\3]\3]\3]\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^"+
		"\3^\3^\3_\3_\3`\3`\3a\3a\3b\3b\3c\3c\3d\3d\3e\3e\3f\3f\3g\3g\3h\3h\4\u01f6"+
		"\u02a6\2i\7\3\t\4\13\5\r\6\17\7\21\b\23\t\25\n\27\13\31\f\33\r\35\16\37"+
		"\17!\20#\21%\22\'\23)\24+\25-\26/\27\61\30\63\31\65\32\67\339\34;\35="+
		"\36?\37A C!E\"G#I$K%M&O\'Q(S)U*W+Y,[-]._/a\60c\61e\62g\63i\64k\65m\66"+
		"o\67q8s9u:w;y<{=}>\177?\u0081@\u0083A\u0085B\u0087C\u0089D\u008bE\u008d"+
		"F\u008fG\u0091H\u0093I\u0095J\u0097K\u0099L\u009bM\u009dN\u009fO\u00a1"+
		"P\u00a3Q\u00a5R\u00a7S\u00a9T\u00abU\u00adV\u00afW\u00b1X\u00b3Y\u00b5"+
		"Z\u00b7[\u00b9\\\u00bb]\u00bd^\u00bf_\u00c1\2\u00c3\2\u00c5\2\u00c7\2"+
		"\u00c9\2\u00cb\2\u00cd\2\u00cf\2\u00d1\2\u00d3\2\7\2\3\4\5\6\7\4\2\f\f"+
		"\17\17\4\2\13\13\"\"\4\2\u00b2\u00b2\u00bc\u00bc\3\2\62;\6\2C\\c|\u00d3"+
		"\u00d3\u00f3\u00f3\u0371\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2"+
		"\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2"+
		"\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2"+
		"\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2"+
		"\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3"+
		"\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2"+
		"\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2"+
		"U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3"+
		"\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2"+
		"\2\2o\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\2u\3\2\2\2\2w\3\2\2\2\2y\3\2\2\2\2"+
		"{\3\2\2\2\2}\3\2\2\2\2\177\3\2\2\2\2\u0081\3\2\2\2\2\u0083\3\2\2\2\2\u0085"+
		"\3\2\2\2\2\u0087\3\2\2\2\2\u0089\3\2\2\2\2\u008b\3\2\2\2\2\u008d\3\2\2"+
		"\2\2\u008f\3\2\2\2\2\u0091\3\2\2\2\2\u0093\3\2\2\2\2\u0095\3\2\2\2\2\u0097"+
		"\3\2\2\2\2\u0099\3\2\2\2\2\u009b\3\2\2\2\2\u009d\3\2\2\2\3\u009f\3\2\2"+
		"\2\3\u00a1\3\2\2\2\3\u00a3\3\2\2\2\3\u00a5\3\2\2\2\3\u00a7\3\2\2\2\4\u00a9"+
		"\3\2\2\2\4\u00ab\3\2\2\2\5\u00ad\3\2\2\2\5\u00af\3\2\2\2\6\u00b1\3\2\2"+
		"\2\6\u00b3\3\2\2\2\6\u00b5\3\2\2\2\6\u00b7\3\2\2\2\6\u00b9\3\2\2\2\6\u00bb"+
		"\3\2\2\2\6\u00bd\3\2\2\2\6\u00bf\3\2\2\2\7\u00d5\3\2\2\2\t\u00dd\3\2\2"+
		"\2\13\u00e1\3\2\2\2\r\u00e5\3\2\2\2\17\u00e9\3\2\2\2\21\u00ed\3\2\2\2"+
		"\23\u00f0\3\2\2\2\25\u00f4\3\2\2\2\27\u00f7\3\2\2\2\31\u00fa\3\2\2\2\33"+
		"\u00ff\3\2\2\2\35\u0104\3\2\2\2\37\u0108\3\2\2\2!\u0110\3\2\2\2#\u0119"+
		"\3\2\2\2%\u0120\3\2\2\2\'\u0129\3\2\2\2)\u0132\3\2\2\2+\u0137\3\2\2\2"+
		"-\u013d\3\2\2\2/\u0147\3\2\2\2\61\u014f\3\2\2\2\63\u0155\3\2\2\2\65\u015e"+
		"\3\2\2\2\67\u0166\3\2\2\29\u016c\3\2\2\2;\u016e\3\2\2\2=\u0170\3\2\2\2"+
		"?\u0172\3\2\2\2A\u0174\3\2\2\2C\u0178\3\2\2\2E\u017b\3\2\2\2G\u017d\3"+
		"\2\2\2I\u017f\3\2\2\2K\u0181\3\2\2\2M\u0183\3\2\2\2O\u0185\3\2\2\2Q\u0188"+
		"\3\2\2\2S\u018e\3\2\2\2U\u0190\3\2\2\2W\u0195\3\2\2\2Y\u019a\3\2\2\2["+
		"\u01a2\3\2\2\2]\u01a8\3\2\2\2_\u01b0\3\2\2\2a\u01b7\3\2\2\2c\u01be\3\2"+
		"\2\2e\u01c5\3\2\2\2g\u01cd\3\2\2\2i\u01d5\3\2\2\2k\u01db\3\2\2\2m\u01e0"+
		"\3\2\2\2o\u01e5\3\2\2\2q\u01ea\3\2\2\2s\u01f0\3\2\2\2u\u0201\3\2\2\2w"+
		"\u0215\3\2\2\2y\u0228\3\2\2\2{\u022b\3\2\2\2}\u0232\3\2\2\2\177\u023a"+
		"\3\2\2\2\u0081\u0247\3\2\2\2\u0083\u024c\3\2\2\2\u0085\u0256\3\2\2\2\u0087"+
		"\u025b\3\2\2\2\u0089\u0265\3\2\2\2\u008b\u026b\3\2\2\2\u008d\u0279\3\2"+
		"\2\2\u008f\u028a\3\2\2\2\u0091\u0297\3\2\2\2\u0093\u02a0\3\2\2\2\u0095"+
		"\u02ac\3\2\2\2\u0097\u02b3\3\2\2\2\u0099\u02b5\3\2\2\2\u009b\u02bc\3\2"+
		"\2\2\u009d\u02c3\3\2\2\2\u009f\u02c5\3\2\2\2\u00a1\u02ca\3\2\2\2\u00a3"+
		"\u02cd\3\2\2\2\u00a5\u02d2\3\2\2\2\u00a7\u02d5\3\2\2\2\u00a9\u02d8\3\2"+
		"\2\2\u00ab\u02e2\3\2\2\2\u00ad\u02e5\3\2\2\2\u00af\u02ef\3\2\2\2\u00b1"+
		"\u02f2\3\2\2\2\u00b3\u02f7\3\2\2\2\u00b5\u02fc\3\2\2\2\u00b7\u02ff\3\2"+
		"\2\2\u00b9\u0302\3\2\2\2\u00bb\u0310\3\2\2\2\u00bd\u031c\3\2\2\2\u00bf"+
		"\u032f\3\2\2\2\u00c1\u0340\3\2\2\2\u00c3\u0342\3\2\2\2\u00c5\u0344\3\2"+
		"\2\2\u00c7\u0346\3\2\2\2\u00c9\u0348\3\2\2\2\u00cb\u034a\3\2\2\2\u00cd"+
		"\u034c\3\2\2\2\u00cf\u034e\3\2\2\2\u00d1\u0350\3\2\2\2\u00d3\u0352\3\2"+
		"\2\2\u00d5\u00d6\7E\2\2\u00d6\u00d7\7q\2\2\u00d7\u00d8\7p\2\2\u00d8\u00d9"+
		"\7e\2\2\u00d9\u00da\7g\2\2\u00da\u00db\7r\2\2\u00db\u00dc\7v\2\2\u00dc"+
		"\b\3\2\2\2\u00dd\u00de\7u\2\2\u00de\u00df\7w\2\2\u00df\u00e0\7d\2\2\u00e0"+
		"\n\3\2\2\2\u00e1\u00e2\7w\2\2\u00e2\u00e3\7u\2\2\u00e3\u00e4\7g\2\2\u00e4"+
		"\f\3\2\2\2\u00e5\u00e6\7f\2\2\u00e6\u00e7\7u\2\2\u00e7\u00e8\7n\2\2\u00e8"+
		"\16\3\2\2\2\u00e9\u00ea\7x\2\2\u00ea\u00eb\7c\2\2\u00eb\u00ec\7t\2\2\u00ec"+
		"\20\3\2\2\2\u00ed\u00ee\7c\2\2\u00ee\u00ef\7u\2\2\u00ef\22\3\2\2\2\u00f0"+
		"\u00f1\7j\2\2\u00f1\u00f2\7c\2\2\u00f2\u00f3\7u\2\2\u00f3\24\3\2\2\2\u00f4"+
		"\u00f5\7q\2\2\u00f5\u00f6\7p\2\2\u00f6\26\3\2\2\2\u00f7\u00f8\7k\2\2\u00f8"+
		"\u00f9\7u\2\2\u00f9\30\3\2\2\2\u00fa\u00fb\7k\2\2\u00fb\u00fc\7p\2\2\u00fc"+
		"\u00fd\7v\2\2\u00fd\u00fe\7q\2\2\u00fe\32\3\2\2\2\u00ff\u0100\7y\2\2\u0100"+
		"\u0101\7k\2\2\u0101\u0102\7v\2\2\u0102\u0103\7j\2\2\u0103\34\3\2\2\2\u0104"+
		"\u0105\7c\2\2\u0105\u0106\7p\2\2\u0106\u0107\7{\2\2\u0107\36\3\2\2\2\u0108"+
		"\u0109\7g\2\2\u0109\u010a\7z\2\2\u010a\u010b\7v\2\2\u010b\u010c\7g\2\2"+
		"\u010c\u010d\7p\2\2\u010d\u010e\7f\2\2\u010e\u010f\7u\2\2\u010f \3\2\2"+
		"\2\u0110\u0111\7c\2\2\u0111\u0112\7d\2\2\u0112\u0113\7u\2\2\u0113\u0114"+
		"\7v\2\2\u0114\u0115\7t\2\2\u0115\u0116\7c\2\2\u0116\u0117\7e\2\2\u0117"+
		"\u0118\7v\2\2\u0118\"\3\2\2\2\u0119\u011a\7u\2\2\u011a\u011b\7k\2\2\u011b"+
		"\u011c\7p\2\2\u011c\u011d\7i\2\2\u011d\u011e\7n\2\2\u011e\u011f\7g\2\2"+
		"\u011f$\3\2\2\2\u0120\u0121\7t\2\2\u0121\u0122\7g\2\2\u0122\u0123\7s\2"+
		"\2\u0123\u0124\7w\2\2\u0124\u0125\7k\2\2\u0125\u0126\7t\2\2\u0126\u0127"+
		"\7g\2\2\u0127\u0128\7f\2\2\u0128&\3\2\2\2\u0129\u012a\7v\2\2\u012a\u012b"+
		"\7g\2\2\u012b\u012c\7t\2\2\u012c\u012d\7o\2\2\u012d\u012e\7k\2\2\u012e"+
		"\u012f\7p\2\2\u012f\u0130\7c\2\2\u0130\u0131\7n\2\2\u0131(\3\2\2\2\u0132"+
		"\u0133\7o\2\2\u0133\u0134\7c\2\2\u0134\u0135\7k\2\2\u0135\u0136\7p\2\2"+
		"\u0136*\3\2\2\2\u0137\u0138\7p\2\2\u0138\u0139\7c\2\2\u0139\u013a\7o\2"+
		"\2\u013a\u013b\7g\2\2\u013b\u013c\7f\2\2\u013c,\3\2\2\2\u013d\u013e\7"+
		"r\2\2\u013e\u013f\7t\2\2\u013f\u0140\7q\2\2\u0140\u0141\7v\2\2\u0141\u0142"+
		"\7q\2\2\u0142\u0143\7v\2\2\u0143\u0144\7{\2\2\u0144\u0145\7r\2\2\u0145"+
		"\u0146\7g\2\2\u0146.\3\2\2\2\u0147\u0148\7h\2\2\u0148\u0149\7g\2\2\u0149"+
		"\u014a\7c\2\2\u014a\u014b\7v\2\2\u014b\u014c\7w\2\2\u014c\u014d\7t\2\2"+
		"\u014d\u014e\7g\2\2\u014e\60\3\2\2\2\u014f\u0150\7h\2\2\u0150\u0151\7"+
		"k\2\2\u0151\u0152\7p\2\2\u0152\u0153\7c\2\2\u0153\u0154\7n\2\2\u0154\62"+
		"\3\2\2\2\u0155\u0156\7g\2\2\u0156\u0157\7p\2\2\u0157\u0158\7e\2\2\u0158"+
		"\u0159\7n\2\2\u0159\u015a\7q\2\2\u015a\u015b\7u\2\2\u015b\u015c\7g\2\2"+
		"\u015c\u015d\7f\2\2\u015d\64\3\2\2\2\u015e\u015f\7r\2\2\u015f\u0160\7"+
		"t\2\2\u0160\u0161\7k\2\2\u0161\u0162\7x\2\2\u0162\u0163\7c\2\2\u0163\u0164"+
		"\7v\2\2\u0164\u0165\7g\2\2\u0165\66\3\2\2\2\u0166\u0167\7h\2\2\u0167\u0168"+
		"\7c\2\2\u0168\u0169\7e\2\2\u0169\u016a\7g\2\2\u016a\u016b\7v\2\2\u016b"+
		"8\3\2\2\2\u016c\u016d\7*\2\2\u016d:\3\2\2\2\u016e\u016f\7+\2\2\u016f<"+
		"\3\2\2\2\u0170\u0171\7]\2\2\u0171>\3\2\2\2\u0172\u0173\7_\2\2\u0173@\3"+
		"\2\2\2\u0174\u0175\7\60\2\2\u0175\u0176\7\60\2\2\u0176\u0177\7\60\2\2"+
		"\u0177B\3\2\2\2\u0178\u0179\7@\2\2\u0179\u017a\b \2\2\u017aD\3\2\2\2\u017b"+
		"\u017c\7>\2\2\u017cF\3\2\2\2\u017d\u017e\7%\2\2\u017eH\3\2\2\2\u017f\u0180"+
		"\7<\2\2\u0180J\3\2\2\2\u0181\u0182\7.\2\2\u0182L\3\2\2\2\u0183\u0184\7"+
		"\60\2\2\u0184N\3\2\2\2\u0185\u0186\7?\2\2\u0186P\3\2\2\2\u0187\u0189\7"+
		"=\2\2\u0188\u0187\3\2\2\2\u0189\u018a\3\2\2\2\u018a\u0188\3\2\2\2\u018a"+
		"\u018b\3\2\2\2\u018b\u018c\3\2\2\2\u018c\u018d\b\'\3\2\u018dR\3\2\2\2"+
		"\u018e\u018f\7-\2\2\u018fT\3\2\2\2\u0190\u0191\7y\2\2\u0191\u0192\7q\2"+
		"\2\u0192\u0193\7t\2\2\u0193\u0194\7f\2\2\u0194V\3\2\2\2\u0195\u0196\7"+
		"h\2\2\u0196\u0197\7k\2\2\u0197\u0198\7n\2\2\u0198\u0199\7g\2\2\u0199X"+
		"\3\2\2\2\u019a\u019b\7k\2\2\u019b\u019c\7p\2\2\u019c\u019d\7v\2\2\u019d"+
		"\u019e\7g\2\2\u019e\u019f\7i\2\2\u019f\u01a0\7g\2\2\u01a0\u01a1\7t\2\2"+
		"\u01a1Z\3\2\2\2\u01a2\u01a3\7v\2\2\u01a3\u01a4\7w\2\2\u01a4\u01a5\7r\2"+
		"\2\u01a5\u01a6\7n\2\2\u01a6\u01a7\7g\2\2\u01a7\\\3\2\2\2\u01a8\u01a9\7"+
		"p\2\2\u01a9\u01aa\7c\2\2\u01aa\u01ab\7v\2\2\u01ab\u01ac\7w\2\2\u01ac\u01ad"+
		"\7t\2\2\u01ad\u01ae\7c\2\2\u01ae\u01af\7n\2\2\u01af^\3\2\2\2\u01b0\u01b1"+
		"\7p\2\2\u01b1\u01b2\7c\2\2\u01b2\u01b3\7v\2\2\u01b3\u01b4\7k\2\2\u01b4"+
		"\u01b5\7x\2\2\u01b5\u01b6\7g\2\2\u01b6`\3\2\2\2\u01b7\u01b8\7f\2\2\u01b8"+
		"\u01b9\7q\2\2\u01b9\u01ba\7w\2\2\u01ba\u01bb\7d\2\2\u01bb\u01bc\7n\2\2"+
		"\u01bc\u01bd\7g\2\2\u01bdb\3\2\2\2\u01be\u01bf\7u\2\2\u01bf\u01c0\7v\2"+
		"\2\u01c0\u01c1\7t\2\2\u01c1\u01c2\7k\2\2\u01c2\u01c3\7p\2\2\u01c3\u01c4"+
		"\7i\2\2\u01c4d\3\2\2\2\u01c5\u01c6\7d\2\2\u01c6\u01c7\7q\2\2\u01c7\u01c8"+
		"\7q\2\2\u01c8\u01c9\7n\2\2\u01c9\u01ca\7g\2\2\u01ca\u01cb\7c\2\2\u01cb"+
		"\u01cc\7p\2\2\u01ccf\3\2\2\2\u01cd\u01ce\7o\2\2\u01ce\u01cf\7g\2\2\u01cf"+
		"\u01d0\7c\2\2\u01d0\u01d1\7u\2\2\u01d1\u01d2\7w\2\2\u01d2\u01d3\7t\2\2"+
		"\u01d3\u01d4\7g\2\2\u01d4h\3\2\2\2\u01d5\u01d6\7t\2\2\u01d6\u01d7\7c\2"+
		"\2\u01d7\u01d8\7v\2\2\u01d8\u01d9\7k\2\2\u01d9\u01da\7q\2\2\u01daj\3\2"+
		"\2\2\u01db\u01dc\7f\2\2\u01dc\u01dd\7c\2\2\u01dd\u01de\7v\2\2\u01de\u01df"+
		"\7g\2\2\u01dfl\3\2\2\2\u01e0\u01e1\7v\2\2\u01e1\u01e2\7{\2\2\u01e2\u01e3"+
		"\7r\2\2\u01e3\u01e4\7g\2\2\u01e4n\3\2\2\2\u01e5\u01e6\7v\2\2\u01e6\u01e7"+
		"\7k\2\2\u01e7\u01e8\7o\2\2\u01e8\u01e9\7g\2\2\u01e9p\3\2\2\2\u01ea\u01eb"+
		"\7g\2\2\u01eb\u01ec\7o\2\2\u01ec\u01ed\7r\2\2\u01ed\u01ee\7v\2\2\u01ee"+
		"\u01ef\7{\2\2\u01efr\3\2\2\2\u01f0\u01f1\7\61\2\2\u01f1\u01f2\7,\2\2\u01f2"+
		"\u01f6\3\2\2\2\u01f3\u01f5\13\2\2\2\u01f4\u01f3\3\2\2\2\u01f5\u01f8\3"+
		"\2\2\2\u01f6\u01f7\3\2\2\2\u01f6\u01f4\3\2\2\2\u01f7\u01f9\3\2\2\2\u01f8"+
		"\u01f6\3\2\2\2\u01f9\u01fa\7,\2\2\u01fa\u01fb\7\61\2\2\u01fb\u01fc\3\2"+
		"\2\2\u01fc\u01fd\b8\4\2\u01fdt\3\2\2\2\u01fe\u0200\t\2\2\2\u01ff\u01fe"+
		"\3\2\2\2\u0200\u0203\3\2\2\2\u0201\u01ff\3\2\2\2\u0201\u0202\3\2\2\2\u0202"+
		"\u0207\3\2\2\2\u0203\u0201\3\2\2\2\u0204\u0206\t\3\2\2\u0205\u0204\3\2"+
		"\2\2\u0206\u0209\3\2\2\2\u0207\u0205\3\2\2\2\u0207\u0208\3\2\2\2\u0208"+
		"\u020a\3\2\2\2\u0209\u0207\3\2\2\2\u020a\u020b\7\61\2\2\u020b\u020c\7"+
		"\61\2\2\u020c\u0210\3\2\2\2\u020d\u020f\n\2\2\2\u020e\u020d\3\2\2\2\u020f"+
		"\u0212\3\2\2\2\u0210\u020e\3\2\2\2\u0210\u0211\3\2\2\2\u0211\u0213\3\2"+
		"\2\2\u0212\u0210\3\2\2\2\u0213\u0214\b9\4\2\u0214v\3\2\2\2\u0215\u0218"+
		"\7G\2\2\u0216\u0219\5S(\2\u0217\u0219\5\u00cde\2\u0218\u0216\3\2\2\2\u0218"+
		"\u0217\3\2\2\2\u0218\u0219\3\2\2\2\u0219\u021b\3\2\2\2\u021a\u021c\5\u00d1"+
		"g\2\u021b\u021a\3\2\2\2\u021c\u021d\3\2\2\2\u021d\u021b\3\2\2\2\u021d"+
		"\u021e\3\2\2\2\u021ex\3\2\2\2\u021f\u0220\7v\2\2\u0220\u0221\7t\2\2\u0221"+
		"\u0222\7w\2\2\u0222\u0229\7g\2\2\u0223\u0224\7h\2\2\u0224\u0225\7c\2\2"+
		"\u0225\u0226\7n\2\2\u0226\u0227\7u\2\2\u0227\u0229\7g\2\2\u0228\u021f"+
		"\3\2\2\2\u0228\u0223\3\2\2\2\u0229z\3\2\2\2\u022a\u022c\5S(\2\u022b\u022a"+
		"\3\2\2\2\u022b\u022c\3\2\2\2\u022c\u022e\3\2\2\2\u022d\u022f\5\u00d1g"+
		"\2\u022e\u022d\3\2\2\2\u022f\u0230\3\2\2\2\u0230\u022e\3\2\2\2\u0230\u0231"+
		"\3\2\2\2\u0231|\3\2\2\2\u0232\u0234\5\u00cde\2\u0233\u0235\5\u00d1g\2"+
		"\u0234\u0233\3\2\2\2\u0235\u0236\3\2\2\2\u0236\u0234\3\2\2\2\u0236\u0237"+
		"\3\2\2\2\u0237~\3\2\2\2\u0238\u023b\5S(\2\u0239\u023b\5\u00cde\2\u023a"+
		"\u0238\3\2\2\2\u023a\u0239\3\2\2\2\u023a\u023b\3\2\2\2\u023b\u023d\3\2"+
		"\2\2\u023c\u023e\5\u00d1g\2\u023d\u023c\3\2\2\2\u023e\u023f\3\2\2\2\u023f"+
		"\u023d\3\2\2\2\u023f\u0240\3\2\2\2\u0240\u0241\3\2\2\2\u0241\u0243\5M"+
		"%\2\u0242\u0244\5\u00d1g\2\u0243\u0242\3\2\2\2\u0244\u0245\3\2\2\2\u0245"+
		"\u0243\3\2\2\2\u0245\u0246\3\2\2\2\u0246\u0080\3\2\2\2\u0247\u0248\7$"+
		"\2\2\u0248\u0249\b?\5\2\u0249\u024a\3\2\2\2\u024a\u024b\b?\6\2\u024b\u0082"+
		"\3\2\2\2\u024c\u024e\5O&\2\u024d\u024f\5O&\2\u024e\u024d\3\2\2\2\u024f"+
		"\u0250\3\2\2\2\u0250\u024e\3\2\2\2\u0250\u0251\3\2\2\2\u0251\u0252\3\2"+
		"\2\2\u0252\u0253\b@\7\2\u0253\u0254\3\2\2\2\u0254\u0255\b@\b\2\u0255\u0084"+
		"\3\2\2\2\u0256\u0257\7)\2\2\u0257\u0258\bA\t\2\u0258\u0259\3\2\2\2\u0259"+
		"\u025a\bA\n\2\u025a\u0086\3\2\2\2\u025b\u025d\5\u00cde\2\u025c\u025e\5"+
		"\u00cde\2\u025d\u025c\3\2\2\2\u025e\u025f\3\2\2\2\u025f\u025d\3\2\2\2"+
		"\u025f\u0260\3\2\2\2\u0260\u0261\3\2\2\2\u0261\u0262\bB\13\2\u0262\u0263"+
		"\3\2\2\2\u0263\u0264\bB\f\2\u0264\u0088\3\2\2\2\u0265\u0267\5G\"\2\u0266"+
		"\u0268\5\u00d3h\2\u0267\u0266\3\2\2\2\u0268\u0269\3\2\2\2\u0269\u0267"+
		"\3\2\2\2\u0269\u026a\3\2\2\2\u026a\u008a\3\2\2\2\u026b\u0271\5\u00d3h"+
		"\2\u026c\u0270\5\u00d1g\2\u026d\u0270\5\u00d3h\2\u026e\u0270\5\u00cde"+
		"\2\u026f\u026c\3\2\2\2\u026f\u026d\3\2\2\2\u026f\u026e\3\2\2\2\u0270\u0273"+
		"\3\2\2\2\u0271\u026f\3\2\2\2\u0271\u0272\3\2\2\2\u0272\u008c\3\2\2\2\u0273"+
		"\u0271\3\2\2\2\u0274\u027a\5\u00d3h\2\u0275\u027a\5\u00c5a\2\u0276\u027a"+
		"\5\u00c1_\2\u0277\u027a\5\u00c3`\2\u0278\u027a\5\u00c7b\2\u0279\u0274"+
		"\3\2\2\2\u0279\u0275\3\2\2\2\u0279\u0276\3\2\2\2\u0279\u0277\3\2\2\2\u0279"+
		"\u0278\3\2\2\2\u027a\u0286\3\2\2\2\u027b\u0285\5\u00cff\2\u027c\u0285"+
		"\5\u00c9c\2\u027d\u0285\5\u00cbd\2\u027e\u0285\5\u00c5a\2\u027f\u0285"+
		"\5\u00c1_\2\u0280\u0285\5\u00c3`\2\u0281\u0285\5\u00c7b\2\u0282\u0285"+
		"\5\u00d3h\2\u0283\u0285\5\u00d1g\2\u0284\u027b\3\2\2\2\u0284\u027c\3\2"+
		"\2\2\u0284\u027d\3\2\2\2\u0284\u027e\3\2\2\2\u0284\u027f\3\2\2\2\u0284"+
		"\u0280\3\2\2\2\u0284\u0281\3\2\2\2\u0284\u0282\3\2\2\2\u0284\u0283\3\2"+
		"\2\2\u0285\u0288\3\2\2\2\u0286\u0284\3\2\2\2\u0286\u0287\3\2\2\2\u0287"+
		"\u008e\3\2\2\2\u0288\u0286\3\2\2\2\u0289\u028b\5\u0097J\2\u028a\u0289"+
		"\3\2\2\2\u028b\u028c\3\2\2\2\u028c\u028a\3\2\2\2\u028c\u028d\3\2\2\2\u028d"+
		"\u0291\3\2\2\2\u028e\u0290\5\u0095I\2\u028f\u028e\3\2\2\2\u0290\u0293"+
		"\3\2\2\2\u0291\u028f\3\2\2\2\u0291\u0292\3\2\2\2\u0292\u0294\3\2\2\2\u0293"+
		"\u0291\3\2\2\2\u0294\u0295\bF\r\2\u0295\u0090\3\2\2\2\u0296\u0298\5\u0095"+
		"I\2\u0297\u0296\3\2\2\2\u0298\u0299\3\2\2\2\u0299\u0297\3\2\2\2\u0299"+
		"\u029a\3\2\2\2\u029a\u029c\3\2\2\2\u029b\u029d\7\2\2\3\u029c\u029b\3\2"+
		"\2\2\u029c\u029d\3\2\2\2\u029d\u029e\3\2\2\2\u029e\u029f\bG\16\2\u029f"+
		"\u0092\3\2\2\2\u02a0\u02a1\7#\2\2\u02a1\u02a2\7#\2\2\u02a2\u02a6\3\2\2"+
		"\2\u02a3\u02a5\13\2\2\2\u02a4\u02a3\3\2\2\2\u02a5\u02a8\3\2\2\2\u02a6"+
		"\u02a7\3\2\2\2\u02a6\u02a4\3\2\2\2\u02a7\u02a9\3\2\2\2\u02a8\u02a6\3\2"+
		"\2\2\u02a9\u02aa\5\u008fF\2\u02aa\u02ab\bH\17\2\u02ab\u0094\3\2\2\2\u02ac"+
		"\u02ad\t\3\2\2\u02ad\u0096\3\2\2\2\u02ae\u02b0\7\17\2\2\u02af\u02ae\3"+
		"\2\2\2\u02af\u02b0\3\2\2\2\u02b0\u02b1\3\2\2\2\u02b1\u02b4\7\f\2\2\u02b2"+
		"\u02b4\7\17\2\2\u02b3\u02af\3\2\2\2\u02b3\u02b2\3\2\2\2\u02b4\u0098\3"+
		"\2\2\2\u02b5\u02b6\7k\2\2\u02b6\u02b7\7p\2\2\u02b7\u02b8\7f\2\2\u02b8"+
		"\u02b9\7g\2\2\u02b9\u02ba\7p\2\2\u02ba\u02bb\7v\2\2\u02bb\u009a\3\2\2"+
		"\2\u02bc\u02bd\7f\2\2\u02bd\u02be\7g\2\2\u02be\u02bf\7f\2\2\u02bf\u02c0"+
		"\7g\2\2\u02c0\u02c1\7p\2\2\u02c1\u02c2\7v\2\2\u02c2\u009c\3\2\2\2\u02c3"+
		"\u02c4\13\2\2\2\u02c4\u009e\3\2\2\2\u02c5\u02c6\7$\2\2\u02c6\u02c7\bN"+
		"\20\2\u02c7\u02c8\3\2\2\2\u02c8\u02c9\bN\21\2\u02c9\u00a0\3\2\2\2\u02ca"+
		"\u02cb\7$\2\2\u02cb\u02cc\bO\22\2\u02cc\u00a2\3\2\2\2\u02cd\u02ce\7^\2"+
		"\2\u02ce\u02cf\7$\2\2\u02cf\u02d0\3\2\2\2\u02d0\u02d1\bP\23\2\u02d1\u00a4"+
		"\3\2\2\2\u02d2\u02d3\7^\2\2\u02d3\u02d4\bQ\24\2\u02d4\u00a6\3\2\2\2\u02d5"+
		"\u02d6\13\2\2\2\u02d6\u02d7\bR\25\2\u02d7\u00a8\3\2\2\2\u02d8\u02da\5"+
		"O&\2\u02d9\u02db\5O&\2\u02da\u02d9\3\2\2\2\u02db\u02dc\3\2\2\2\u02dc\u02da"+
		"\3\2\2\2\u02dc\u02dd\3\2\2\2\u02dd\u02de\3\2\2\2\u02de\u02df\bS\26\2\u02df"+
		"\u02e0\3\2\2\2\u02e0\u02e1\bS\21\2\u02e1\u00aa\3\2\2\2\u02e2\u02e3\13"+
		"\2\2\2\u02e3\u02e4\bT\27\2\u02e4\u00ac\3\2\2\2\u02e5\u02e7\5\u00cde\2"+
		"\u02e6\u02e8\5\u00cde\2\u02e7\u02e6\3\2\2\2\u02e8\u02e9\3\2\2\2\u02e9"+
		"\u02e7\3\2\2\2\u02e9\u02ea\3\2\2\2\u02ea\u02eb\3\2\2\2\u02eb\u02ec\bU"+
		"\30\2\u02ec\u02ed\3\2\2\2\u02ed\u02ee\bU\21\2\u02ee\u00ae\3\2\2\2\u02ef"+
		"\u02f0\13\2\2\2\u02f0\u02f1\bV\31\2\u02f1\u00b0\3\2\2\2\u02f2\u02f3\7"+
		")\2\2\u02f3\u02f4\bW\32\2\u02f4\u02f5\3\2\2\2\u02f5\u02f6\bW\21\2\u02f6"+
		"\u00b2\3\2\2\2\u02f7\u02f8\7^\2\2\u02f8\u02f9\7)\2\2\u02f9\u02fa\3\2\2"+
		"\2\u02fa\u02fb\bX\33\2\u02fb\u00b4\3\2\2\2\u02fc\u02fd\7^\2\2\u02fd\u02fe"+
		"\bY\34\2\u02fe\u00b6\3\2\2\2\u02ff\u0300\13\2\2\2\u0300\u0301\bZ\35\2"+
		"\u0301\u00b8\3\2\2\2\u0302\u0303\7\'\2\2\u0303\u0304\7S\2\2\u0304\u0305"+
		"\7W\2\2\u0305\u0306\7Q\2\2\u0306\u0307\7V\2\2\u0307\u0308\7G\2\2\u0308"+
		"\u0309\7a\2\2\u0309\u030a\7D\2\2\u030a\u030b\7G\2\2\u030b\u030c\7I\2\2"+
		"\u030c\u030d\7K\2\2\u030d\u030e\7P\2\2\u030e\u030f\7\'\2\2\u030f\u00ba"+
		"\3\2\2\2\u0310\u0311\7\'\2\2\u0311\u0312\7S\2\2\u0312\u0313\7W\2\2\u0313"+
		"\u0314\7Q\2\2\u0314\u0315\7V\2\2\u0315\u0316\7G\2\2\u0316\u0317\7a\2\2"+
		"\u0317\u0318\7G\2\2\u0318\u0319\7P\2\2\u0319\u031a\7F\2\2\u031a\u031b"+
		"\7\'\2\2\u031b\u00bc\3\2\2\2\u031c\u031d\7\'\2\2\u031d\u031e\7G\2\2\u031e"+
		"\u031f\7Z\2\2\u031f\u0320\7R\2\2\u0320\u0321\7T\2\2\u0321\u0322\7G\2\2"+
		"\u0322\u0323\7U\2\2\u0323\u0324\7U\2\2\u0324\u0325\7K\2\2\u0325\u0326"+
		"\7Q\2\2\u0326\u0327\7P\2\2\u0327\u0328\7a\2\2\u0328\u0329\7D\2\2\u0329"+
		"\u032a\7G\2\2\u032a\u032b\7I\2\2\u032b\u032c\7K\2\2\u032c\u032d\7P\2\2"+
		"\u032d\u032e\7\'\2\2\u032e\u00be\3\2\2\2\u032f\u0330\7\'\2\2\u0330\u0331"+
		"\7G\2\2\u0331\u0332\7Z\2\2\u0332\u0333\7R\2\2\u0333\u0334\7T\2\2\u0334"+
		"\u0335\7G\2\2\u0335\u0336\7U\2\2\u0336\u0337\7U\2\2\u0337\u0338\7K\2\2"+
		"\u0338\u0339\7Q\2\2\u0339\u033a\7P\2\2\u033a\u033b\7a\2\2\u033b\u033c"+
		"\7G\2\2\u033c\u033d\7P\2\2\u033d\u033e\7F\2\2\u033e\u033f\7\'\2\2\u033f"+
		"\u00c0\3\2\2\2\u0340\u0341\7&\2\2\u0341\u00c2\3\2\2\2\u0342\u0343\7\u20ae"+
		"\2\2\u0343\u00c4\3\2\2\2\u0344\u0345\7\'\2\2\u0345\u00c6\3\2\2\2\u0346"+
		"\u0347\t\4\2\2\u0347\u00c8\3\2\2\2\u0348\u0349\7\u00b9\2\2\u0349\u00ca"+
		"\3\2\2\2\u034a\u034b\7\61\2\2\u034b\u00cc\3\2\2\2\u034c\u034d\7/\2\2\u034d"+
		"\u00ce\3\2\2\2\u034e\u034f\7a\2\2\u034f\u00d0\3\2\2\2\u0350\u0351\t\5"+
		"\2\2\u0351\u00d2\3\2\2\2\u0352\u0353\t\6\2\2\u0353\u00d4\3\2\2\2\'\2\3"+
		"\4\5\6\u018a\u01f6\u0201\u0205\u0207\u0210\u0218\u021d\u0228\u022b\u0230"+
		"\u0236\u023a\u023f\u0245\u0250\u025f\u0269\u026f\u0271\u0279\u0284\u0286"+
		"\u028c\u0291\u0299\u029c\u02a6\u02af\u02b3\u02dc\u02e9\36\3 \2\3\'\3\b"+
		"\2\2\3?\4\4\3\2\3@\5\4\4\2\3A\6\4\6\2\3B\7\4\5\2\3F\b\2\3\2\3H\t\3N\n"+
		"\4\2\2\3O\13\3P\f\3Q\r\3R\16\3S\17\3T\20\3U\21\3V\22\3W\23\3X\24\3Y\25"+
		"\3Z\26";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}