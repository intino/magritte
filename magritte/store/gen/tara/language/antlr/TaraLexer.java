// Generated from /Users/oroncal/workspace/tara/ide/language/src/tara/language/lexicon/TaraLexer.g4 by ANTLR 4.5.1
package tara.language.antlr;
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
		TERMINAL=17, MAIN=18, PROTOTYPE=19, FEATURE=20, FINAL=21, ENCLOSED=22, 
		PRIVATE=23, FACET=24, LEFT_PARENTHESIS=25, RIGHT_PARENTHESIS=26, LEFT_SQUARE=27, 
		RIGHT_SQUARE=28, LIST=29, INLINE=30, CLOSE_INLINE=31, HASHTAG=32, COLON=33, 
		COMMA=34, DOT=35, EQUALS=36, SEMICOLON=37, PLUS=38, WORD=39, RESOURCE=40, 
		INT_TYPE=41, TUPLE_TYPE=42, NATURAL_TYPE=43, NATIVE_TYPE=44, DOUBLE_TYPE=45, 
		STRING_TYPE=46, BOOLEAN_TYPE=47, MEASURE_TYPE=48, RATIO_TYPE=49, DATE_TYPE=50, 
		EMPTY=51, BLOCK_COMMENT=52, LINE_COMMENT=53, SCIENCE_NOT=54, BOOLEAN_VALUE=55, 
		NATURAL_VALUE=56, NEGATIVE_VALUE=57, DOUBLE_VALUE=58, APHOSTROPHE=59, 
		STRING_MULTILINE=60, SINGLE_QUOTE=61, EXPRESSION_MULTILINE=62, PLATE_VALUE=63, 
		IDENTIFIER=64, MEASURE_VALUE=65, NEWLINE=66, SPACES=67, DOC=68, SP=69, 
		NL=70, NEW_LINE_INDENT=71, DEDENT=72, UNKNOWN_TOKEN=73, M_CHARACTER=74, 
		QUOTE=75, Q=76, SLASH_Q=77, SLASH=78, CHARACTER=79, ME_STRING_MULTILINE=80, 
		ME_CHARACTER=81, E_QUOTE=82, E_SLASH_Q=83, E_SLASH=84, E_CHARACTER=85, 
		QUOTE_BEGIN=86, QUOTE_END=87, EXPRESSION_BEGIN=88, EXPRESSION_END=89;
	public static final int MULTILINE = 1;
	public static final int QUOTED = 2;
	public static final int EXPRESSION_MULTILINE_MODE = 3;
	public static final int EXPRESSION_QUOTED = 4;
	public static String[] modeNames = {
		"DEFAULT_MODE", "MULTILINE", "QUOTED", "EXPRESSION_MULTILINE_MODE", "EXPRESSION_QUOTED"
	};

	public static final String[] ruleNames = {
		"METAIDENTIFIER", "SUB", "USE", "DSL", "VAR", "AS", "HAS", "ON", "IS", 
		"INTO", "WITH", "ANY", "EXTENDS", "ABSTRACT", "SINGLE", "REQUIRED", "TERMINAL", 
		"MAIN", "PROTOTYPE", "FEATURE", "FINAL", "ENCLOSED", "PRIVATE", "FACET", 
		"LEFT_PARENTHESIS", "RIGHT_PARENTHESIS", "LEFT_SQUARE", "RIGHT_SQUARE", 
		"LIST", "INLINE", "CLOSE_INLINE", "HASHTAG", "COLON", "COMMA", "DOT", 
		"EQUALS", "SEMICOLON", "PLUS", "WORD", "RESOURCE", "INT_TYPE", "TUPLE_TYPE", 
		"NATURAL_TYPE", "NATIVE_TYPE", "DOUBLE_TYPE", "STRING_TYPE", "BOOLEAN_TYPE", 
		"MEASURE_TYPE", "RATIO_TYPE", "DATE_TYPE", "EMPTY", "BLOCK_COMMENT", "LINE_COMMENT", 
		"SCIENCE_NOT", "BOOLEAN_VALUE", "NATURAL_VALUE", "NEGATIVE_VALUE", "DOUBLE_VALUE", 
		"APHOSTROPHE", "STRING_MULTILINE", "SINGLE_QUOTE", "EXPRESSION_MULTILINE", 
		"PLATE_VALUE", "IDENTIFIER", "MEASURE_VALUE", "NEWLINE", "SPACES", "DOC", 
		"SP", "NL", "NEW_LINE_INDENT", "DEDENT", "UNKNOWN_TOKEN", "M_CHARACTER", 
		"QUOTE", "Q", "SLASH_Q", "SLASH", "CHARACTER", "ME_STRING_MULTILINE", 
		"ME_CHARACTER", "E_QUOTE", "E_SLASH_Q", "E_SLASH", "E_CHARACTER", "QUOTE_BEGIN", 
		"QUOTE_END", "EXPRESSION_BEGIN", "EXPRESSION_END", "DOLLAR", "EURO", "PERCENTAGE", 
		"GRADE", "BY", "DIVIDED_BY", "DASH", "UNDERDASH", "DIGIT", "LETTER"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'Concept'", "'sub'", "'use'", "'dsl'", "'var'", "'as'", "'has'", 
		"'on'", "'is'", "'into'", "'with'", "'any'", "'extends'", "'abstract'", 
		"'single'", "'required'", "'terminal'", "'main'", "'prototype'", "'feature'", 
		"'final'", "'enclosed'", "'private'", "'facet'", "'('", "')'", "'['", 
		"']'", "'...'", "'>'", "'<'", "'#'", "':'", "','", "'.'", "'='", null, 
		"'+'", "'word'", "'file'", "'integer'", "'tuple'", "'natural'", "'native'", 
		"'double'", "'string'", "'boolean'", "'measure'", "'ratio'", "'date'", 
		"'empty'", null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, "'indent'", "'dedent'", 
		null, null, null, "'\"'", "'\\\"'", null, null, null, null, null, "'\\''", 
		null, null, "'%QUOTE_BEGIN%'", "'%QUOTE_END%'", "'%EXPRESSION_BEGIN%'", 
		"'%EXPRESSION_END%'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "METAIDENTIFIER", "SUB", "USE", "DSL", "VAR", "AS", "HAS", "ON", 
		"IS", "INTO", "WITH", "ANY", "EXTENDS", "ABSTRACT", "SINGLE", "REQUIRED", 
		"TERMINAL", "MAIN", "PROTOTYPE", "FEATURE", "FINAL", "ENCLOSED", "PRIVATE", 
		"FACET", "LEFT_PARENTHESIS", "RIGHT_PARENTHESIS", "LEFT_SQUARE", "RIGHT_SQUARE", 
		"LIST", "INLINE", "CLOSE_INLINE", "HASHTAG", "COLON", "COMMA", "DOT", 
		"EQUALS", "SEMICOLON", "PLUS", "WORD", "RESOURCE", "INT_TYPE", "TUPLE_TYPE", 
		"NATURAL_TYPE", "NATIVE_TYPE", "DOUBLE_TYPE", "STRING_TYPE", "BOOLEAN_TYPE", 
		"MEASURE_TYPE", "RATIO_TYPE", "DATE_TYPE", "EMPTY", "BLOCK_COMMENT", "LINE_COMMENT", 
		"SCIENCE_NOT", "BOOLEAN_VALUE", "NATURAL_VALUE", "NEGATIVE_VALUE", "DOUBLE_VALUE", 
		"APHOSTROPHE", "STRING_MULTILINE", "SINGLE_QUOTE", "EXPRESSION_MULTILINE", 
		"PLATE_VALUE", "IDENTIFIER", "MEASURE_VALUE", "NEWLINE", "SPACES", "DOC", 
		"SP", "NL", "NEW_LINE_INDENT", "DEDENT", "UNKNOWN_TOKEN", "M_CHARACTER", 
		"QUOTE", "Q", "SLASH_Q", "SLASH", "CHARACTER", "ME_STRING_MULTILINE", 
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
		case 29:
			INLINE_action((RuleContext)_localctx, actionIndex);
			break;
		case 36:
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
			M_CHARACTER_action((RuleContext)_localctx, actionIndex);
			break;
		case 74:
			QUOTE_action((RuleContext)_localctx, actionIndex);
			break;
		case 75:
			Q_action((RuleContext)_localctx, actionIndex);
			break;
		case 76:
			SLASH_Q_action((RuleContext)_localctx, actionIndex);
			break;
		case 77:
			SLASH_action((RuleContext)_localctx, actionIndex);
			break;
		case 78:
			CHARACTER_action((RuleContext)_localctx, actionIndex);
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
			emitToken(DOC);emitToken(NEWLINE);
			break;
		}
	}
	private void M_CHARACTER_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 8:
			   setType(CHARACTER); 
			break;
		}
	}
	private void QUOTE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 9:
			   setType(QUOTE_END); 
			break;
		}
	}
	private void Q_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 10:
			   setType(CHARACTER); 
			break;
		}
	}
	private void SLASH_Q_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 11:
			   setType(CHARACTER); 
			break;
		}
	}
	private void SLASH_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 12:
			   setType(CHARACTER); 
			break;
		}
	}
	private void CHARACTER_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 13:
			   setType(CHARACTER); 
			break;
		}
	}
	private void ME_STRING_MULTILINE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 14:
			   setType(EXPRESSION_END); 
			break;
		}
	}
	private void ME_CHARACTER_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 15:
			   setType(CHARACTER); 
			break;
		}
	}
	private void E_QUOTE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 16:
			   setType(EXPRESSION_END); 
			break;
		}
	}
	private void E_SLASH_Q_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 17:
			   setType(CHARACTER); 
			break;
		}
	}
	private void E_SLASH_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 18:
			   setType(CHARACTER); 
			break;
		}
	}
	private void E_CHARACTER_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 19:
			   setType(CHARACTER); 
			break;
		}
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2[\u0332\b\1\b\1\b"+
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
		"\t]\4^\t^\4_\t_\4`\t`\4a\ta\4b\tb\4c\tc\4d\td\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3"+
		"\6\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\13"+
		"\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23"+
		"\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\31"+
		"\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36"+
		"\3\36\3\36\3\36\3\37\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\3"+
		"&\6&\u017b\n&\r&\16&\u017c\3&\3&\3\'\3\'\3(\3(\3(\3(\3(\3)\3)\3)\3)\3"+
		")\3*\3*\3*\3*\3*\3*\3*\3*\3+\3+\3+\3+\3+\3+\3,\3,\3,\3,\3,\3,\3,\3,\3"+
		"-\3-\3-\3-\3-\3-\3-\3.\3.\3.\3.\3.\3.\3.\3/\3/\3/\3/\3/\3/\3/\3\60\3\60"+
		"\3\60\3\60\3\60\3\60\3\60\3\60\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61"+
		"\3\62\3\62\3\62\3\62\3\62\3\62\3\63\3\63\3\63\3\63\3\63\3\64\3\64\3\64"+
		"\3\64\3\64\3\64\3\65\3\65\3\65\3\65\7\65\u01dd\n\65\f\65\16\65\u01e0\13"+
		"\65\3\65\3\65\3\65\3\65\3\65\3\66\7\66\u01e8\n\66\f\66\16\66\u01eb\13"+
		"\66\3\66\7\66\u01ee\n\66\f\66\16\66\u01f1\13\66\3\66\3\66\3\66\3\66\7"+
		"\66\u01f7\n\66\f\66\16\66\u01fa\13\66\3\66\3\66\3\67\3\67\3\67\5\67\u0201"+
		"\n\67\3\67\6\67\u0204\n\67\r\67\16\67\u0205\38\38\38\38\38\38\38\38\3"+
		"8\58\u0211\n8\39\59\u0214\n9\39\69\u0217\n9\r9\169\u0218\3:\3:\6:\u021d"+
		"\n:\r:\16:\u021e\3;\3;\5;\u0223\n;\3;\6;\u0226\n;\r;\16;\u0227\3;\3;\6"+
		";\u022c\n;\r;\16;\u022d\3<\3<\3<\3<\3<\3=\3=\6=\u0237\n=\r=\16=\u0238"+
		"\3=\3=\3=\3=\3>\3>\3>\3>\3>\3?\3?\6?\u0246\n?\r?\16?\u0247\3?\3?\3?\3"+
		"?\3@\3@\6@\u0250\n@\r@\16@\u0251\3A\3A\3A\3A\7A\u0258\nA\fA\16A\u025b"+
		"\13A\3B\3B\3B\3B\3B\5B\u0262\nB\3B\3B\3B\3B\3B\3B\3B\3B\3B\7B\u026d\n"+
		"B\fB\16B\u0270\13B\3C\6C\u0273\nC\rC\16C\u0274\3C\7C\u0278\nC\fC\16C\u027b"+
		"\13C\3C\3C\3D\6D\u0280\nD\rD\16D\u0281\3D\5D\u0285\nD\3D\3D\3E\3E\3E\3"+
		"E\7E\u028d\nE\fE\16E\u0290\13E\3E\3E\3E\3F\3F\3G\5G\u0298\nG\3G\3G\5G"+
		"\u029c\nG\3H\3H\3H\3H\3H\3H\3H\3I\3I\3I\3I\3I\3I\3I\3J\3J\3K\3K\3K\3L"+
		"\3L\3L\3L\3L\3M\3M\3M\3N\3N\3N\3N\3N\3O\3O\3O\3P\3P\3P\3Q\3Q\6Q\u02c6"+
		"\nQ\rQ\16Q\u02c7\3Q\3Q\3Q\3Q\3R\3R\3R\3S\3S\3S\3S\3S\3T\3T\3T\3T\3T\3"+
		"U\3U\3U\3V\3V\3V\3W\3W\3W\3W\3W\3W\3W\3W\3W\3W\3W\3W\3W\3W\3X\3X\3X\3"+
		"X\3X\3X\3X\3X\3X\3X\3X\3X\3Y\3Y\3Y\3Y\3Y\3Y\3Y\3Y\3Y\3Y\3Y\3Y\3Y\3Y\3"+
		"Y\3Y\3Y\3Y\3Y\3Z\3Z\3Z\3Z\3Z\3Z\3Z\3Z\3Z\3Z\3Z\3Z\3Z\3Z\3Z\3Z\3Z\3[\3"+
		"[\3\\\3\\\3]\3]\3^\3^\3_\3_\3`\3`\3a\3a\3b\3b\3c\3c\3d\3d\4\u01de\u028e"+
		"\2e\7\3\t\4\13\5\r\6\17\7\21\b\23\t\25\n\27\13\31\f\33\r\35\16\37\17!"+
		"\20#\21%\22\'\23)\24+\25-\26/\27\61\30\63\31\65\32\67\339\34;\35=\36?"+
		"\37A C!E\"G#I$K%M&O\'Q(S)U*W+Y,[-]._/a\60c\61e\62g\63i\64k\65m\66o\67"+
		"q8s9u:w;y<{=}>\177?\u0081@\u0083A\u0085B\u0087C\u0089D\u008bE\u008dF\u008f"+
		"G\u0091H\u0093I\u0095J\u0097K\u0099L\u009bM\u009dN\u009fO\u00a1P\u00a3"+
		"Q\u00a5R\u00a7S\u00a9T\u00abU\u00adV\u00afW\u00b1X\u00b3Y\u00b5Z\u00b7"+
		"[\u00b9\2\u00bb\2\u00bd\2\u00bf\2\u00c1\2\u00c3\2\u00c5\2\u00c7\2\u00c9"+
		"\2\u00cb\2\7\2\3\4\5\6\7\4\2\f\f\17\17\4\2\13\13\"\"\4\2\u00b2\u00b2\u00bc"+
		"\u00bc\3\2\62;\6\2C\\c|\u00d3\u00d3\u00f3\u00f3\u034e\2\7\3\2\2\2\2\t"+
		"\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2"+
		"\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2"+
		"\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2"+
		"+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2"+
		"\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2"+
		"C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3"+
		"\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2"+
		"\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2"+
		"i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\2u\3"+
		"\2\2\2\2w\3\2\2\2\2y\3\2\2\2\2{\3\2\2\2\2}\3\2\2\2\2\177\3\2\2\2\2\u0081"+
		"\3\2\2\2\2\u0083\3\2\2\2\2\u0085\3\2\2\2\2\u0087\3\2\2\2\2\u0089\3\2\2"+
		"\2\2\u008b\3\2\2\2\2\u008d\3\2\2\2\2\u008f\3\2\2\2\2\u0091\3\2\2\2\2\u0093"+
		"\3\2\2\2\2\u0095\3\2\2\2\2\u0097\3\2\2\2\3\u0099\3\2\2\2\4\u009b\3\2\2"+
		"\2\4\u009d\3\2\2\2\4\u009f\3\2\2\2\4\u00a1\3\2\2\2\4\u00a3\3\2\2\2\5\u00a5"+
		"\3\2\2\2\5\u00a7\3\2\2\2\6\u00a9\3\2\2\2\6\u00ab\3\2\2\2\6\u00ad\3\2\2"+
		"\2\6\u00af\3\2\2\2\6\u00b1\3\2\2\2\6\u00b3\3\2\2\2\6\u00b5\3\2\2\2\6\u00b7"+
		"\3\2\2\2\7\u00cd\3\2\2\2\t\u00d5\3\2\2\2\13\u00d9\3\2\2\2\r\u00dd\3\2"+
		"\2\2\17\u00e1\3\2\2\2\21\u00e5\3\2\2\2\23\u00e8\3\2\2\2\25\u00ec\3\2\2"+
		"\2\27\u00ef\3\2\2\2\31\u00f2\3\2\2\2\33\u00f7\3\2\2\2\35\u00fc\3\2\2\2"+
		"\37\u0100\3\2\2\2!\u0108\3\2\2\2#\u0111\3\2\2\2%\u0118\3\2\2\2\'\u0121"+
		"\3\2\2\2)\u012a\3\2\2\2+\u012f\3\2\2\2-\u0139\3\2\2\2/\u0141\3\2\2\2\61"+
		"\u0147\3\2\2\2\63\u0150\3\2\2\2\65\u0158\3\2\2\2\67\u015e\3\2\2\29\u0160"+
		"\3\2\2\2;\u0162\3\2\2\2=\u0164\3\2\2\2?\u0166\3\2\2\2A\u016a\3\2\2\2C"+
		"\u016d\3\2\2\2E\u016f\3\2\2\2G\u0171\3\2\2\2I\u0173\3\2\2\2K\u0175\3\2"+
		"\2\2M\u0177\3\2\2\2O\u017a\3\2\2\2Q\u0180\3\2\2\2S\u0182\3\2\2\2U\u0187"+
		"\3\2\2\2W\u018c\3\2\2\2Y\u0194\3\2\2\2[\u019a\3\2\2\2]\u01a2\3\2\2\2_"+
		"\u01a9\3\2\2\2a\u01b0\3\2\2\2c\u01b7\3\2\2\2e\u01bf\3\2\2\2g\u01c7\3\2"+
		"\2\2i\u01cd\3\2\2\2k\u01d2\3\2\2\2m\u01d8\3\2\2\2o\u01e9\3\2\2\2q\u01fd"+
		"\3\2\2\2s\u0210\3\2\2\2u\u0213\3\2\2\2w\u021a\3\2\2\2y\u0222\3\2\2\2{"+
		"\u022f\3\2\2\2}\u0234\3\2\2\2\177\u023e\3\2\2\2\u0081\u0243\3\2\2\2\u0083"+
		"\u024d\3\2\2\2\u0085\u0253\3\2\2\2\u0087\u0261\3\2\2\2\u0089\u0272\3\2"+
		"\2\2\u008b\u027f\3\2\2\2\u008d\u0288\3\2\2\2\u008f\u0294\3\2\2\2\u0091"+
		"\u029b\3\2\2\2\u0093\u029d\3\2\2\2\u0095\u02a4\3\2\2\2\u0097\u02ab\3\2"+
		"\2\2\u0099\u02ad\3\2\2\2\u009b\u02b0\3\2\2\2\u009d\u02b5\3\2\2\2\u009f"+
		"\u02b8\3\2\2\2\u00a1\u02bd\3\2\2\2\u00a3\u02c0\3\2\2\2\u00a5\u02c3\3\2"+
		"\2\2\u00a7\u02cd\3\2\2\2\u00a9\u02d0\3\2\2\2\u00ab\u02d5\3\2\2\2\u00ad"+
		"\u02da\3\2\2\2\u00af\u02dd\3\2\2\2\u00b1\u02e0\3\2\2\2\u00b3\u02ee\3\2"+
		"\2\2\u00b5\u02fa\3\2\2\2\u00b7\u030d\3\2\2\2\u00b9\u031e\3\2\2\2\u00bb"+
		"\u0320\3\2\2\2\u00bd\u0322\3\2\2\2\u00bf\u0324\3\2\2\2\u00c1\u0326\3\2"+
		"\2\2\u00c3\u0328\3\2\2\2\u00c5\u032a\3\2\2\2\u00c7\u032c\3\2\2\2\u00c9"+
		"\u032e\3\2\2\2\u00cb\u0330\3\2\2\2\u00cd\u00ce\7E\2\2\u00ce\u00cf\7q\2"+
		"\2\u00cf\u00d0\7p\2\2\u00d0\u00d1\7e\2\2\u00d1\u00d2\7g\2\2\u00d2\u00d3"+
		"\7r\2\2\u00d3\u00d4\7v\2\2\u00d4\b\3\2\2\2\u00d5\u00d6\7u\2\2\u00d6\u00d7"+
		"\7w\2\2\u00d7\u00d8\7d\2\2\u00d8\n\3\2\2\2\u00d9\u00da\7w\2\2\u00da\u00db"+
		"\7u\2\2\u00db\u00dc\7g\2\2\u00dc\f\3\2\2\2\u00dd\u00de\7f\2\2\u00de\u00df"+
		"\7u\2\2\u00df\u00e0\7n\2\2\u00e0\16\3\2\2\2\u00e1\u00e2\7x\2\2\u00e2\u00e3"+
		"\7c\2\2\u00e3\u00e4\7t\2\2\u00e4\20\3\2\2\2\u00e5\u00e6\7c\2\2\u00e6\u00e7"+
		"\7u\2\2\u00e7\22\3\2\2\2\u00e8\u00e9\7j\2\2\u00e9\u00ea\7c\2\2\u00ea\u00eb"+
		"\7u\2\2\u00eb\24\3\2\2\2\u00ec\u00ed\7q\2\2\u00ed\u00ee\7p\2\2\u00ee\26"+
		"\3\2\2\2\u00ef\u00f0\7k\2\2\u00f0\u00f1\7u\2\2\u00f1\30\3\2\2\2\u00f2"+
		"\u00f3\7k\2\2\u00f3\u00f4\7p\2\2\u00f4\u00f5\7v\2\2\u00f5\u00f6\7q\2\2"+
		"\u00f6\32\3\2\2\2\u00f7\u00f8\7y\2\2\u00f8\u00f9\7k\2\2\u00f9\u00fa\7"+
		"v\2\2\u00fa\u00fb\7j\2\2\u00fb\34\3\2\2\2\u00fc\u00fd\7c\2\2\u00fd\u00fe"+
		"\7p\2\2\u00fe\u00ff\7{\2\2\u00ff\36\3\2\2\2\u0100\u0101\7g\2\2\u0101\u0102"+
		"\7z\2\2\u0102\u0103\7v\2\2\u0103\u0104\7g\2\2\u0104\u0105\7p\2\2\u0105"+
		"\u0106\7f\2\2\u0106\u0107\7u\2\2\u0107 \3\2\2\2\u0108\u0109\7c\2\2\u0109"+
		"\u010a\7d\2\2\u010a\u010b\7u\2\2\u010b\u010c\7v\2\2\u010c\u010d\7t\2\2"+
		"\u010d\u010e\7c\2\2\u010e\u010f\7e\2\2\u010f\u0110\7v\2\2\u0110\"\3\2"+
		"\2\2\u0111\u0112\7u\2\2\u0112\u0113\7k\2\2\u0113\u0114\7p\2\2\u0114\u0115"+
		"\7i\2\2\u0115\u0116\7n\2\2\u0116\u0117\7g\2\2\u0117$\3\2\2\2\u0118\u0119"+
		"\7t\2\2\u0119\u011a\7g\2\2\u011a\u011b\7s\2\2\u011b\u011c\7w\2\2\u011c"+
		"\u011d\7k\2\2\u011d\u011e\7t\2\2\u011e\u011f\7g\2\2\u011f\u0120\7f\2\2"+
		"\u0120&\3\2\2\2\u0121\u0122\7v\2\2\u0122\u0123\7g\2\2\u0123\u0124\7t\2"+
		"\2\u0124\u0125\7o\2\2\u0125\u0126\7k\2\2\u0126\u0127\7p\2\2\u0127\u0128"+
		"\7c\2\2\u0128\u0129\7n\2\2\u0129(\3\2\2\2\u012a\u012b\7o\2\2\u012b\u012c"+
		"\7c\2\2\u012c\u012d\7k\2\2\u012d\u012e\7p\2\2\u012e*\3\2\2\2\u012f\u0130"+
		"\7r\2\2\u0130\u0131\7t\2\2\u0131\u0132\7q\2\2\u0132\u0133\7v\2\2\u0133"+
		"\u0134\7q\2\2\u0134\u0135\7v\2\2\u0135\u0136\7{\2\2\u0136\u0137\7r\2\2"+
		"\u0137\u0138\7g\2\2\u0138,\3\2\2\2\u0139\u013a\7h\2\2\u013a\u013b\7g\2"+
		"\2\u013b\u013c\7c\2\2\u013c\u013d\7v\2\2\u013d\u013e\7w\2\2\u013e\u013f"+
		"\7t\2\2\u013f\u0140\7g\2\2\u0140.\3\2\2\2\u0141\u0142\7h\2\2\u0142\u0143"+
		"\7k\2\2\u0143\u0144\7p\2\2\u0144\u0145\7c\2\2\u0145\u0146\7n\2\2\u0146"+
		"\60\3\2\2\2\u0147\u0148\7g\2\2\u0148\u0149\7p\2\2\u0149\u014a\7e\2\2\u014a"+
		"\u014b\7n\2\2\u014b\u014c\7q\2\2\u014c\u014d\7u\2\2\u014d\u014e\7g\2\2"+
		"\u014e\u014f\7f\2\2\u014f\62\3\2\2\2\u0150\u0151\7r\2\2\u0151\u0152\7"+
		"t\2\2\u0152\u0153\7k\2\2\u0153\u0154\7x\2\2\u0154\u0155\7c\2\2\u0155\u0156"+
		"\7v\2\2\u0156\u0157\7g\2\2\u0157\64\3\2\2\2\u0158\u0159\7h\2\2\u0159\u015a"+
		"\7c\2\2\u015a\u015b\7e\2\2\u015b\u015c\7g\2\2\u015c\u015d\7v\2\2\u015d"+
		"\66\3\2\2\2\u015e\u015f\7*\2\2\u015f8\3\2\2\2\u0160\u0161\7+\2\2\u0161"+
		":\3\2\2\2\u0162\u0163\7]\2\2\u0163<\3\2\2\2\u0164\u0165\7_\2\2\u0165>"+
		"\3\2\2\2\u0166\u0167\7\60\2\2\u0167\u0168\7\60\2\2\u0168\u0169\7\60\2"+
		"\2\u0169@\3\2\2\2\u016a\u016b\7@\2\2\u016b\u016c\b\37\2\2\u016cB\3\2\2"+
		"\2\u016d\u016e\7>\2\2\u016eD\3\2\2\2\u016f\u0170\7%\2\2\u0170F\3\2\2\2"+
		"\u0171\u0172\7<\2\2\u0172H\3\2\2\2\u0173\u0174\7.\2\2\u0174J\3\2\2\2\u0175"+
		"\u0176\7\60\2\2\u0176L\3\2\2\2\u0177\u0178\7?\2\2\u0178N\3\2\2\2\u0179"+
		"\u017b\7=\2\2\u017a\u0179\3\2\2\2\u017b\u017c\3\2\2\2\u017c\u017a\3\2"+
		"\2\2\u017c\u017d\3\2\2\2\u017d\u017e\3\2\2\2\u017e\u017f\b&\3\2\u017f"+
		"P\3\2\2\2\u0180\u0181\7-\2\2\u0181R\3\2\2\2\u0182\u0183\7y\2\2\u0183\u0184"+
		"\7q\2\2\u0184\u0185\7t\2\2\u0185\u0186\7f\2\2\u0186T\3\2\2\2\u0187\u0188"+
		"\7h\2\2\u0188\u0189\7k\2\2\u0189\u018a\7n\2\2\u018a\u018b\7g\2\2\u018b"+
		"V\3\2\2\2\u018c\u018d\7k\2\2\u018d\u018e\7p\2\2\u018e\u018f\7v\2\2\u018f"+
		"\u0190\7g\2\2\u0190\u0191\7i\2\2\u0191\u0192\7g\2\2\u0192\u0193\7t\2\2"+
		"\u0193X\3\2\2\2\u0194\u0195\7v\2\2\u0195\u0196\7w\2\2\u0196\u0197\7r\2"+
		"\2\u0197\u0198\7n\2\2\u0198\u0199\7g\2\2\u0199Z\3\2\2\2\u019a\u019b\7"+
		"p\2\2\u019b\u019c\7c\2\2\u019c\u019d\7v\2\2\u019d\u019e\7w\2\2\u019e\u019f"+
		"\7t\2\2\u019f\u01a0\7c\2\2\u01a0\u01a1\7n\2\2\u01a1\\\3\2\2\2\u01a2\u01a3"+
		"\7p\2\2\u01a3\u01a4\7c\2\2\u01a4\u01a5\7v\2\2\u01a5\u01a6\7k\2\2\u01a6"+
		"\u01a7\7x\2\2\u01a7\u01a8\7g\2\2\u01a8^\3\2\2\2\u01a9\u01aa\7f\2\2\u01aa"+
		"\u01ab\7q\2\2\u01ab\u01ac\7w\2\2\u01ac\u01ad\7d\2\2\u01ad\u01ae\7n\2\2"+
		"\u01ae\u01af\7g\2\2\u01af`\3\2\2\2\u01b0\u01b1\7u\2\2\u01b1\u01b2\7v\2"+
		"\2\u01b2\u01b3\7t\2\2\u01b3\u01b4\7k\2\2\u01b4\u01b5\7p\2\2\u01b5\u01b6"+
		"\7i\2\2\u01b6b\3\2\2\2\u01b7\u01b8\7d\2\2\u01b8\u01b9\7q\2\2\u01b9\u01ba"+
		"\7q\2\2\u01ba\u01bb\7n\2\2\u01bb\u01bc\7g\2\2\u01bc\u01bd\7c\2\2\u01bd"+
		"\u01be\7p\2\2\u01bed\3\2\2\2\u01bf\u01c0\7o\2\2\u01c0\u01c1\7g\2\2\u01c1"+
		"\u01c2\7c\2\2\u01c2\u01c3\7u\2\2\u01c3\u01c4\7w\2\2\u01c4\u01c5\7t\2\2"+
		"\u01c5\u01c6\7g\2\2\u01c6f\3\2\2\2\u01c7\u01c8\7t\2\2\u01c8\u01c9\7c\2"+
		"\2\u01c9\u01ca\7v\2\2\u01ca\u01cb\7k\2\2\u01cb\u01cc\7q\2\2\u01cch\3\2"+
		"\2\2\u01cd\u01ce\7f\2\2\u01ce\u01cf\7c\2\2\u01cf\u01d0\7v\2\2\u01d0\u01d1"+
		"\7g\2\2\u01d1j\3\2\2\2\u01d2\u01d3\7g\2\2\u01d3\u01d4\7o\2\2\u01d4\u01d5"+
		"\7r\2\2\u01d5\u01d6\7v\2\2\u01d6\u01d7\7{\2\2\u01d7l\3\2\2\2\u01d8\u01d9"+
		"\7\61\2\2\u01d9\u01da\7,\2\2\u01da\u01de\3\2\2\2\u01db\u01dd\13\2\2\2"+
		"\u01dc\u01db\3\2\2\2\u01dd\u01e0\3\2\2\2\u01de\u01df\3\2\2\2\u01de\u01dc"+
		"\3\2\2\2\u01df\u01e1\3\2\2\2\u01e0\u01de\3\2\2\2\u01e1\u01e2\7,\2\2\u01e2"+
		"\u01e3\7\61\2\2\u01e3\u01e4\3\2\2\2\u01e4\u01e5\b\65\4\2\u01e5n\3\2\2"+
		"\2\u01e6\u01e8\t\2\2\2\u01e7\u01e6\3\2\2\2\u01e8\u01eb\3\2\2\2\u01e9\u01e7"+
		"\3\2\2\2\u01e9\u01ea\3\2\2\2\u01ea\u01ef\3\2\2\2\u01eb\u01e9\3\2\2\2\u01ec"+
		"\u01ee\t\3\2\2\u01ed\u01ec\3\2\2\2\u01ee\u01f1\3\2\2\2\u01ef\u01ed\3\2"+
		"\2\2\u01ef\u01f0\3\2\2\2\u01f0\u01f2\3\2\2\2\u01f1\u01ef\3\2\2\2\u01f2"+
		"\u01f3\7\61\2\2\u01f3\u01f4\7\61\2\2\u01f4\u01f8\3\2\2\2\u01f5\u01f7\n"+
		"\2\2\2\u01f6\u01f5\3\2\2\2\u01f7\u01fa\3\2\2\2\u01f8\u01f6\3\2\2\2\u01f8"+
		"\u01f9\3\2\2\2\u01f9\u01fb\3\2\2\2\u01fa\u01f8\3\2\2\2\u01fb\u01fc\b\66"+
		"\4\2\u01fcp\3\2\2\2\u01fd\u0200\7G\2\2\u01fe\u0201\5Q\'\2\u01ff\u0201"+
		"\5\u00c5a\2\u0200\u01fe\3\2\2\2\u0200\u01ff\3\2\2\2\u0200\u0201\3\2\2"+
		"\2\u0201\u0203\3\2\2\2\u0202\u0204\5\u00c9c\2\u0203\u0202\3\2\2\2\u0204"+
		"\u0205\3\2\2\2\u0205\u0203\3\2\2\2\u0205\u0206\3\2\2\2\u0206r\3\2\2\2"+
		"\u0207\u0208\7v\2\2\u0208\u0209\7t\2\2\u0209\u020a\7w\2\2\u020a\u0211"+
		"\7g\2\2\u020b\u020c\7h\2\2\u020c\u020d\7c\2\2\u020d\u020e\7n\2\2\u020e"+
		"\u020f\7u\2\2\u020f\u0211\7g\2\2\u0210\u0207\3\2\2\2\u0210\u020b\3\2\2"+
		"\2\u0211t\3\2\2\2\u0212\u0214\5Q\'\2\u0213\u0212\3\2\2\2\u0213\u0214\3"+
		"\2\2\2\u0214\u0216\3\2\2\2\u0215\u0217\5\u00c9c\2\u0216\u0215\3\2\2\2"+
		"\u0217\u0218\3\2\2\2\u0218\u0216\3\2\2\2\u0218\u0219\3\2\2\2\u0219v\3"+
		"\2\2\2\u021a\u021c\5\u00c5a\2\u021b\u021d\5\u00c9c\2\u021c\u021b\3\2\2"+
		"\2\u021d\u021e\3\2\2\2\u021e\u021c\3\2\2\2\u021e\u021f\3\2\2\2\u021fx"+
		"\3\2\2\2\u0220\u0223\5Q\'\2\u0221\u0223\5\u00c5a\2\u0222\u0220\3\2\2\2"+
		"\u0222\u0221\3\2\2\2\u0222\u0223\3\2\2\2\u0223\u0225\3\2\2\2\u0224\u0226"+
		"\5\u00c9c\2\u0225\u0224\3\2\2\2\u0226\u0227\3\2\2\2\u0227\u0225\3\2\2"+
		"\2\u0227\u0228\3\2\2\2\u0228\u0229\3\2\2\2\u0229\u022b\5K$\2\u022a\u022c"+
		"\5\u00c9c\2\u022b\u022a\3\2\2\2\u022c\u022d\3\2\2\2\u022d\u022b\3\2\2"+
		"\2\u022d\u022e\3\2\2\2\u022ez\3\2\2\2\u022f\u0230\7$\2\2\u0230\u0231\b"+
		"<\5\2\u0231\u0232\3\2\2\2\u0232\u0233\b<\6\2\u0233|\3\2\2\2\u0234\u0236"+
		"\5M%\2\u0235\u0237\5M%\2\u0236\u0235\3\2\2\2\u0237\u0238\3\2\2\2\u0238"+
		"\u0236\3\2\2\2\u0238\u0239\3\2\2\2\u0239\u023a\3\2\2\2\u023a\u023b\b="+
		"\7\2\u023b\u023c\3\2\2\2\u023c\u023d\b=\b\2\u023d~\3\2\2\2\u023e\u023f"+
		"\7)\2\2\u023f\u0240\b>\t\2\u0240\u0241\3\2\2\2\u0241\u0242\b>\n\2\u0242"+
		"\u0080\3\2\2\2\u0243\u0245\5\u00c5a\2\u0244\u0246\5\u00c5a\2\u0245\u0244"+
		"\3\2\2\2\u0246\u0247\3\2\2\2\u0247\u0245\3\2\2\2\u0247\u0248\3\2\2\2\u0248"+
		"\u0249\3\2\2\2\u0249\u024a\b?\13\2\u024a\u024b\3\2\2\2\u024b\u024c\b?"+
		"\f\2\u024c\u0082\3\2\2\2\u024d\u024f\5E!\2\u024e\u0250\5\u00cbd\2\u024f"+
		"\u024e\3\2\2\2\u0250\u0251\3\2\2\2\u0251\u024f\3\2\2\2\u0251\u0252\3\2"+
		"\2\2\u0252\u0084\3\2\2\2\u0253\u0259\5\u00cbd\2\u0254\u0258\5\u00c9c\2"+
		"\u0255\u0258\5\u00cbd\2\u0256\u0258\5\u00c5a\2\u0257\u0254\3\2\2\2\u0257"+
		"\u0255\3\2\2\2\u0257\u0256\3\2\2\2\u0258\u025b\3\2\2\2\u0259\u0257\3\2"+
		"\2\2\u0259\u025a\3\2\2\2\u025a\u0086\3\2\2\2\u025b\u0259\3\2\2\2\u025c"+
		"\u0262\5\u00cbd\2\u025d\u0262\5\u00bd]\2\u025e\u0262\5\u00b9[\2\u025f"+
		"\u0262\5\u00bb\\\2\u0260\u0262\5\u00bf^\2\u0261\u025c\3\2\2\2\u0261\u025d"+
		"\3\2\2\2\u0261\u025e\3\2\2\2\u0261\u025f\3\2\2\2\u0261\u0260\3\2\2\2\u0262"+
		"\u026e\3\2\2\2\u0263\u026d\5\u00c7b\2\u0264\u026d\5\u00c1_\2\u0265\u026d"+
		"\5\u00c3`\2\u0266\u026d\5\u00bd]\2\u0267\u026d\5\u00b9[\2\u0268\u026d"+
		"\5\u00bb\\\2\u0269\u026d\5\u00bf^\2\u026a\u026d\5\u00cbd\2\u026b\u026d"+
		"\5\u00c9c\2\u026c\u0263\3\2\2\2\u026c\u0264\3\2\2\2\u026c\u0265\3\2\2"+
		"\2\u026c\u0266\3\2\2\2\u026c\u0267\3\2\2\2\u026c\u0268\3\2\2\2\u026c\u0269"+
		"\3\2\2\2\u026c\u026a\3\2\2\2\u026c\u026b\3\2\2\2\u026d\u0270\3\2\2\2\u026e"+
		"\u026c\3\2\2\2\u026e\u026f\3\2\2\2\u026f\u0088\3\2\2\2\u0270\u026e\3\2"+
		"\2\2\u0271\u0273\5\u0091G\2\u0272\u0271\3\2\2\2\u0273\u0274\3\2\2\2\u0274"+
		"\u0272\3\2\2\2\u0274\u0275\3\2\2\2\u0275\u0279\3\2\2\2\u0276\u0278\5\u008f"+
		"F\2\u0277\u0276\3\2\2\2\u0278\u027b\3\2\2\2\u0279\u0277\3\2\2\2\u0279"+
		"\u027a\3\2\2\2\u027a\u027c\3\2\2\2\u027b\u0279\3\2\2\2\u027c\u027d\bC"+
		"\r\2\u027d\u008a\3\2\2\2\u027e\u0280\5\u008fF\2\u027f\u027e\3\2\2\2\u0280"+
		"\u0281\3\2\2\2\u0281\u027f\3\2\2\2\u0281\u0282\3\2\2\2\u0282\u0284\3\2"+
		"\2\2\u0283\u0285\7\2\2\3\u0284\u0283\3\2\2\2\u0284\u0285\3\2\2\2\u0285"+
		"\u0286\3\2\2\2\u0286\u0287\bD\16\2\u0287\u008c\3\2\2\2\u0288\u0289\7#"+
		"\2\2\u0289\u028a\7#\2\2\u028a\u028e\3\2\2\2\u028b\u028d\13\2\2\2\u028c"+
		"\u028b\3\2\2\2\u028d\u0290\3\2\2\2\u028e\u028f\3\2\2\2\u028e\u028c\3\2"+
		"\2\2\u028f\u0291\3\2\2\2\u0290\u028e\3\2\2\2\u0291\u0292\5\u0091G\2\u0292"+
		"\u0293\bE\17\2\u0293\u008e\3\2\2\2\u0294\u0295\t\3\2\2\u0295\u0090\3\2"+
		"\2\2\u0296\u0298\7\17\2\2\u0297\u0296\3\2\2\2\u0297\u0298\3\2\2\2\u0298"+
		"\u0299\3\2\2\2\u0299\u029c\7\f\2\2\u029a\u029c\7\17\2\2\u029b\u0297\3"+
		"\2\2\2\u029b\u029a\3\2\2\2\u029c\u0092\3\2\2\2\u029d\u029e\7k\2\2\u029e"+
		"\u029f\7p\2\2\u029f\u02a0\7f\2\2\u02a0\u02a1\7g\2\2\u02a1\u02a2\7p\2\2"+
		"\u02a2\u02a3\7v\2\2\u02a3\u0094\3\2\2\2\u02a4\u02a5\7f\2\2\u02a5\u02a6"+
		"\7g\2\2\u02a6\u02a7\7f\2\2\u02a7\u02a8\7g\2\2\u02a8\u02a9\7p\2\2\u02a9"+
		"\u02aa\7v\2\2\u02aa\u0096\3\2\2\2\u02ab\u02ac\13\2\2\2\u02ac\u0098\3\2"+
		"\2\2\u02ad\u02ae\13\2\2\2\u02ae\u02af\bK\20\2\u02af\u009a\3\2\2\2\u02b0"+
		"\u02b1\7$\2\2\u02b1\u02b2\bL\21\2\u02b2\u02b3\3\2\2\2\u02b3\u02b4\bL\22"+
		"\2\u02b4\u009c\3\2\2\2\u02b5\u02b6\7$\2\2\u02b6\u02b7\bM\23\2\u02b7\u009e"+
		"\3\2\2\2\u02b8\u02b9\7^\2\2\u02b9\u02ba\7$\2\2\u02ba\u02bb\3\2\2\2\u02bb"+
		"\u02bc\bN\24\2\u02bc\u00a0\3\2\2\2\u02bd\u02be\7^\2\2\u02be\u02bf\bO\25"+
		"\2\u02bf\u00a2\3\2\2\2\u02c0\u02c1\13\2\2\2\u02c1\u02c2\bP\26\2\u02c2"+
		"\u00a4\3\2\2\2\u02c3\u02c5\5\u00c5a\2\u02c4\u02c6\5\u00c5a\2\u02c5\u02c4"+
		"\3\2\2\2\u02c6\u02c7\3\2\2\2\u02c7\u02c5\3\2\2\2\u02c7\u02c8\3\2\2\2\u02c8"+
		"\u02c9\3\2\2\2\u02c9\u02ca\bQ\27\2\u02ca\u02cb\3\2\2\2\u02cb\u02cc\bQ"+
		"\22\2\u02cc\u00a6\3\2\2\2\u02cd\u02ce\13\2\2\2\u02ce\u02cf\bR\30\2\u02cf"+
		"\u00a8\3\2\2\2\u02d0\u02d1\7)\2\2\u02d1\u02d2\bS\31\2\u02d2\u02d3\3\2"+
		"\2\2\u02d3\u02d4\bS\22\2\u02d4\u00aa\3\2\2\2\u02d5\u02d6\7^\2\2\u02d6"+
		"\u02d7\7)\2\2\u02d7\u02d8\3\2\2\2\u02d8\u02d9\bT\32\2\u02d9\u00ac\3\2"+
		"\2\2\u02da\u02db\7^\2\2\u02db\u02dc\bU\33\2\u02dc\u00ae\3\2\2\2\u02dd"+
		"\u02de\13\2\2\2\u02de\u02df\bV\34\2\u02df\u00b0\3\2\2\2\u02e0\u02e1\7"+
		"\'\2\2\u02e1\u02e2\7S\2\2\u02e2\u02e3\7W\2\2\u02e3\u02e4\7Q\2\2\u02e4"+
		"\u02e5\7V\2\2\u02e5\u02e6\7G\2\2\u02e6\u02e7\7a\2\2\u02e7\u02e8\7D\2\2"+
		"\u02e8\u02e9\7G\2\2\u02e9\u02ea\7I\2\2\u02ea\u02eb\7K\2\2\u02eb\u02ec"+
		"\7P\2\2\u02ec\u02ed\7\'\2\2\u02ed\u00b2\3\2\2\2\u02ee\u02ef\7\'\2\2\u02ef"+
		"\u02f0\7S\2\2\u02f0\u02f1\7W\2\2\u02f1\u02f2\7Q\2\2\u02f2\u02f3\7V\2\2"+
		"\u02f3\u02f4\7G\2\2\u02f4\u02f5\7a\2\2\u02f5\u02f6\7G\2\2\u02f6\u02f7"+
		"\7P\2\2\u02f7\u02f8\7F\2\2\u02f8\u02f9\7\'\2\2\u02f9\u00b4\3\2\2\2\u02fa"+
		"\u02fb\7\'\2\2\u02fb\u02fc\7G\2\2\u02fc\u02fd\7Z\2\2\u02fd\u02fe\7R\2"+
		"\2\u02fe\u02ff\7T\2\2\u02ff\u0300\7G\2\2\u0300\u0301\7U\2\2\u0301\u0302"+
		"\7U\2\2\u0302\u0303\7K\2\2\u0303\u0304\7Q\2\2\u0304\u0305\7P\2\2\u0305"+
		"\u0306\7a\2\2\u0306\u0307\7D\2\2\u0307\u0308\7G\2\2\u0308\u0309\7I\2\2"+
		"\u0309\u030a\7K\2\2\u030a\u030b\7P\2\2\u030b\u030c\7\'\2\2\u030c\u00b6"+
		"\3\2\2\2\u030d\u030e\7\'\2\2\u030e\u030f\7G\2\2\u030f\u0310\7Z\2\2\u0310"+
		"\u0311\7R\2\2\u0311\u0312\7T\2\2\u0312\u0313\7G\2\2\u0313\u0314\7U\2\2"+
		"\u0314\u0315\7U\2\2\u0315\u0316\7K\2\2\u0316\u0317\7Q\2\2\u0317\u0318"+
		"\7P\2\2\u0318\u0319\7a\2\2\u0319\u031a\7G\2\2\u031a\u031b\7P\2\2\u031b"+
		"\u031c\7F\2\2\u031c\u031d\7\'\2\2\u031d\u00b8\3\2\2\2\u031e\u031f\7&\2"+
		"\2\u031f\u00ba\3\2\2\2\u0320\u0321\7\u20ae\2\2\u0321\u00bc\3\2\2\2\u0322"+
		"\u0323\7\'\2\2\u0323\u00be\3\2\2\2\u0324\u0325\t\4\2\2\u0325\u00c0\3\2"+
		"\2\2\u0326\u0327\7\u00b9\2\2\u0327\u00c2\3\2\2\2\u0328\u0329\7\61\2\2"+
		"\u0329\u00c4\3\2\2\2\u032a\u032b\7/\2\2\u032b\u00c6\3\2\2\2\u032c\u032d"+
		"\7a\2\2\u032d\u00c8\3\2\2\2\u032e\u032f\t\5\2\2\u032f\u00ca\3\2\2\2\u0330"+
		"\u0331\t\6\2\2\u0331\u00cc\3\2\2\2&\2\3\4\5\6\u017c\u01de\u01e9\u01ed"+
		"\u01ef\u01f8\u0200\u0205\u0210\u0213\u0218\u021e\u0222\u0227\u022d\u0238"+
		"\u0247\u0251\u0257\u0259\u0261\u026c\u026e\u0274\u0279\u0281\u0284\u028e"+
		"\u0297\u029b\u02c7\35\3\37\2\3&\3\b\2\2\3<\4\4\4\2\3=\5\4\3\2\3>\6\4\6"+
		"\2\3?\7\4\5\2\3C\b\2\3\2\3E\t\3K\n\3L\13\4\2\2\3M\f\3N\r\3O\16\3P\17\3"+
		"Q\20\3R\21\3S\22\3T\23\3U\24\3V\25";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}