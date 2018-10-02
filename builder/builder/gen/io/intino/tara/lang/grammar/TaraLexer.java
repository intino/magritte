// Generated from /Users/oroncal/workspace/tara/core/language/src/io/intino/tara/lang/lexicon/TaraLexer.g4 by ANTLR 4.7
package io.intino.tara.lang.grammar;

	import static io.intino.tara.lang.grammar.TaraGrammar.CHARACTER;
	import io.intino.tara.compiler.parser.antlr.BlockManager;
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
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		SUB=1, USE=2, DSL=3, VAR=4, AS=5, HAS=6, ON=7, IS=8, INTO=9, WITH=10, 
		ANY=11, EXTENDS=12, CONCEPT=13, ABSTRACT=14, TERMINAL=15, COMPONENT=16, 
		FEATURE=17, REQUIRED=18, FINAL=19, ENCLOSED=20, PRIVATE=21, REACTIVE=22, 
		VOLATILE=23, DECORABLE=24, LEFT_PARENTHESIS=25, RIGHT_PARENTHESIS=26, 
		LEFT_SQUARE=27, RIGHT_SQUARE=28, LEFT_CURLY=29, RIGHT_CURLY=30, INLINE=31, 
		CLOSE_INLINE=32, AT=33, HASHTAG=34, COLON=35, COMMA=36, DOT=37, EQUALS=38, 
		STAR=39, LIST=40, SEMICOLON=41, PLUS=42, WORD=43, RESOURCE=44, INT_TYPE=45, 
		FUNCTION_TYPE=46, OBJECT_TYPE=47, DOUBLE_TYPE=48, LONG_TYPE=49, STRING_TYPE=50, 
		BOOLEAN_TYPE=51, DATE_TYPE=52, INSTANT_TYPE=53, TIME_TYPE=54, EMPTY=55, 
		BLOCK_COMMENT=56, LINE_COMMENT=57, SCIENCE_NOT=58, BOOLEAN_VALUE=59, NATURAL_VALUE=60, 
		NEGATIVE_VALUE=61, DOUBLE_VALUE=62, STRING=63, STRING_MULTILINE=64, SINGLE_QUOTE=65, 
		EXPRESSION_MULTILINE=66, CLASS_TYPE=67, IDENTIFIER=68, MEASURE_VALUE=69, 
		NEWLINE=70, SPACES=71, DOC=72, SP=73, NL=74, NEW_LINE_INDENT=75, DEDENT=76, 
		UNKNOWN_TOKEN=77, ME_STRING_MULTILINE=78, ME_CHARACTER=79, E_QUOTE=80, 
		E_SLASH_Q=81, E_SLASH=82, E_CHARACTER=83, QUOTE_BEGIN=84, QUOTE_END=85, 
		EXPRESSION_BEGIN=86, EXPRESSION_END=87;
	public static final int
		EXPRESSION_MULTILINE_MODE=1, EXPRESSION_QUOTED=2;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE", "EXPRESSION_MULTILINE_MODE", "EXPRESSION_QUOTED"
	};

	public static final String[] ruleNames = {
		"SUB", "USE", "DSL", "VAR", "AS", "HAS", "ON", "IS", "INTO", "WITH", "ANY", 
		"EXTENDS", "CONCEPT", "ABSTRACT", "TERMINAL", "COMPONENT", "FEATURE", 
		"REQUIRED", "FINAL", "ENCLOSED", "PRIVATE", "REACTIVE", "VOLATILE", "DECORABLE", 
		"LEFT_PARENTHESIS", "RIGHT_PARENTHESIS", "LEFT_SQUARE", "RIGHT_SQUARE", 
		"LEFT_CURLY", "RIGHT_CURLY", "INLINE", "CLOSE_INLINE", "AT", "HASHTAG", 
		"COLON", "COMMA", "DOT", "EQUALS", "STAR", "LIST", "SEMICOLON", "PLUS", 
		"WORD", "RESOURCE", "INT_TYPE", "FUNCTION_TYPE", "OBJECT_TYPE", "DOUBLE_TYPE", 
		"LONG_TYPE", "STRING_TYPE", "BOOLEAN_TYPE", "DATE_TYPE", "INSTANT_TYPE", 
		"TIME_TYPE", "EMPTY", "BLOCK_COMMENT", "LINE_COMMENT", "SCIENCE_NOT", 
		"BOOLEAN_VALUE", "NATURAL_VALUE", "NEGATIVE_VALUE", "DOUBLE_VALUE", "STRING", 
		"STRING_MULTILINE", "SINGLE_QUOTE", "EXPRESSION_MULTILINE", "CLASS_TYPE", 
		"IDENTIFIER", "MEASURE_VALUE", "NEWLINE", "SPACES", "DOC", "SP", "NL", 
		"NEW_LINE_INDENT", "DEDENT", "UNKNOWN_TOKEN", "ME_STRING_MULTILINE", "ME_CHARACTER", 
		"E_QUOTE", "E_SLASH_Q", "E_SLASH", "E_CHARACTER", "QUOTE_BEGIN", "QUOTE_END", 
		"EXPRESSION_BEGIN", "EXPRESSION_END", "DOLLAR", "EURO", "PERCENTAGE", 
		"GRADE", "BY", "DIVIDED_BY", "DASH", "UNDERDASH", "DIGIT", "LETTER"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'sub'", "'use'", "'dsl'", "'var'", "'as'", "'has'", "'on'", "'is'", 
		"'into'", "'with'", "'any'", "'extends'", "'concept'", "'abstract'", "'terminal'", 
		"'component'", "'feature'", "'required'", "'final'", "'enclosed'", "'private'", 
		"'reactive'", "'volatile'", "'decorable'", "'('", "')'", "'['", "']'", 
		"'{'", "'}'", "'>'", "'<'", "'@'", "'#'", "':'", "','", "'.'", "'='", 
		"'*'", "'...'", null, "'+'", "'word'", "'resource'", "'integer'", "'function'", 
		"'object'", "'double'", "'long'", "'string'", "'boolean'", "'datex'", 
		"'instant'", "'time'", "'empty'", null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, "'indent'", "'dedent'", null, null, null, null, "'\\''", "'\\'", 
		null, "'%QUOTE_BEGIN%'", "'%QUOTE_END%'", "'%EXPRESSION_BEGIN%'", "'%EXPRESSION_END%'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "SUB", "USE", "DSL", "VAR", "AS", "HAS", "ON", "IS", "INTO", "WITH", 
		"ANY", "EXTENDS", "CONCEPT", "ABSTRACT", "TERMINAL", "COMPONENT", "FEATURE", 
		"REQUIRED", "FINAL", "ENCLOSED", "PRIVATE", "REACTIVE", "VOLATILE", "DECORABLE", 
		"LEFT_PARENTHESIS", "RIGHT_PARENTHESIS", "LEFT_SQUARE", "RIGHT_SQUARE", 
		"LEFT_CURLY", "RIGHT_CURLY", "INLINE", "CLOSE_INLINE", "AT", "HASHTAG", 
		"COLON", "COMMA", "DOT", "EQUALS", "STAR", "LIST", "SEMICOLON", "PLUS", 
		"WORD", "RESOURCE", "INT_TYPE", "FUNCTION_TYPE", "OBJECT_TYPE", "DOUBLE_TYPE", 
		"LONG_TYPE", "STRING_TYPE", "BOOLEAN_TYPE", "DATE_TYPE", "INSTANT_TYPE", 
		"TIME_TYPE", "EMPTY", "BLOCK_COMMENT", "LINE_COMMENT", "SCIENCE_NOT", 
		"BOOLEAN_VALUE", "NATURAL_VALUE", "NEGATIVE_VALUE", "DOUBLE_VALUE", "STRING", 
		"STRING_MULTILINE", "SINGLE_QUOTE", "EXPRESSION_MULTILINE", "CLASS_TYPE", 
		"IDENTIFIER", "MEASURE_VALUE", "NEWLINE", "SPACES", "DOC", "SP", "NL", 
		"NEW_LINE_INDENT", "DEDENT", "UNKNOWN_TOKEN", "ME_STRING_MULTILINE", "ME_CHARACTER", 
		"E_QUOTE", "E_SLASH_Q", "E_SLASH", "E_CHARACTER", "QUOTE_BEGIN", "QUOTE_END", 
		"EXPRESSION_BEGIN", "EXPRESSION_END"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2Y\u0357\b\1\b\1\b"+
		"\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n"+
		"\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21"+
		"\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30"+
		"\4\31\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37"+
		"\4 \t \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t"+
		"*\4+\t+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63"+
		"\4\64\t\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t"+
		"<\4=\t=\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4"+
		"H\tH\4I\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\4O\tO\4P\tP\4Q\tQ\4R\tR\4S\t"+
		"S\4T\tT\4U\tU\4V\tV\4W\tW\4X\tX\4Y\tY\4Z\tZ\4[\t[\4\\\t\\\4]\t]\4^\t^"+
		"\4_\t_\4`\t`\4a\ta\4b\tb\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3"+
		"\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t"+
		"\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30"+
		"\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\33"+
		"\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3 \3!\3!\3\"\3\"\3"+
		"#\3#\3$\3$\3%\3%\3&\3&\3\'\3\'\3(\3(\3)\3)\3)\3)\3*\6*\u0187\n*\r*\16"+
		"*\u0188\3*\3*\3+\3+\3,\3,\3,\3,\3,\3-\3-\3-\3-\3-\3-\3-\3-\3-\3.\3.\3"+
		".\3.\3.\3.\3.\3.\3/\3/\3/\3/\3/\3/\3/\3/\3/\3\60\3\60\3\60\3\60\3\60\3"+
		"\60\3\60\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\62\3\62\3\62\3\62\3\62\3"+
		"\63\3\63\3\63\3\63\3\63\3\63\3\63\3\64\3\64\3\64\3\64\3\64\3\64\3\64\3"+
		"\64\3\65\3\65\3\65\3\65\3\65\3\65\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3"+
		"\66\3\67\3\67\3\67\3\67\3\67\38\38\38\38\38\38\39\39\39\39\79\u01ed\n"+
		"9\f9\169\u01f0\139\39\39\39\39\39\3:\7:\u01f8\n:\f:\16:\u01fb\13:\3:\7"+
		":\u01fe\n:\f:\16:\u0201\13:\3:\3:\3:\3:\7:\u0207\n:\f:\16:\u020a\13:\3"+
		":\3:\3;\3;\3;\5;\u0211\n;\3;\6;\u0214\n;\r;\16;\u0215\3<\3<\3<\3<\3<\3"+
		"<\3<\3<\3<\5<\u0221\n<\3=\5=\u0224\n=\3=\6=\u0227\n=\r=\16=\u0228\3>\3"+
		">\6>\u022d\n>\r>\16>\u022e\3?\3?\5?\u0233\n?\3?\6?\u0236\n?\r?\16?\u0237"+
		"\3?\3?\6?\u023c\n?\r?\16?\u023d\3@\3@\3@\3@\7@\u0244\n@\f@\16@\u0247\13"+
		"@\3@\3@\3A\3A\6A\u024d\nA\rA\16A\u024e\3A\3A\3A\7A\u0254\nA\fA\16A\u0257"+
		"\13A\3A\3A\6A\u025b\nA\rA\16A\u025c\3A\3A\3B\3B\3B\3B\3B\3C\3C\6C\u0268"+
		"\nC\rC\16C\u0269\3C\3C\3C\3C\3D\3D\3D\3D\3D\7D\u0275\nD\fD\16D\u0278\13"+
		"D\3D\3D\3D\3D\3D\3D\7D\u0280\nD\fD\16D\u0283\13D\3D\3D\5D\u0287\nD\3D"+
		"\3D\3E\3E\5E\u028d\nE\3E\3E\3E\3E\7E\u0293\nE\fE\16E\u0296\13E\3F\3F\3"+
		"F\3F\3F\5F\u029d\nF\3F\3F\3F\3F\3F\3F\3F\3F\3F\7F\u02a8\nF\fF\16F\u02ab"+
		"\13F\3G\6G\u02ae\nG\rG\16G\u02af\3G\7G\u02b3\nG\fG\16G\u02b6\13G\3G\3"+
		"G\3H\6H\u02bb\nH\rH\16H\u02bc\3H\5H\u02c0\nH\3H\3H\3I\3I\3I\3I\7I\u02c8"+
		"\nI\fI\16I\u02cb\13I\3I\3I\3I\3J\3J\3K\5K\u02d3\nK\3K\3K\5K\u02d7\nK\3"+
		"L\3L\3L\3L\3L\3L\3L\3M\3M\3M\3M\3M\3M\3M\3N\3N\3O\3O\6O\u02eb\nO\rO\16"+
		"O\u02ec\3O\3O\3O\3O\3P\3P\3P\3Q\3Q\3Q\3Q\3Q\3R\3R\3R\3R\3R\3S\3S\3S\3"+
		"T\3T\3T\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3V\3V\3V\3V\3V\3V\3"+
		"V\3V\3V\3V\3V\3V\3W\3W\3W\3W\3W\3W\3W\3W\3W\3W\3W\3W\3W\3W\3W\3W\3W\3"+
		"W\3W\3X\3X\3X\3X\3X\3X\3X\3X\3X\3X\3X\3X\3X\3X\3X\3X\3X\3Y\3Y\3Z\3Z\3"+
		"[\3[\3\\\3\\\3]\3]\3^\3^\3_\3_\3`\3`\3a\3a\3b\3b\4\u01ee\u02c9\2c\5\3"+
		"\7\4\t\5\13\6\r\7\17\b\21\t\23\n\25\13\27\f\31\r\33\16\35\17\37\20!\21"+
		"#\22%\23\'\24)\25+\26-\27/\30\61\31\63\32\65\33\67\349\35;\36=\37? A!"+
		"C\"E#G$I%K&M\'O(Q)S*U+W,Y-[.]/_\60a\61c\62e\63g\64i\65k\66m\67o8q9s:u"+
		";w<y={>}?\177@\u0081A\u0083B\u0085C\u0087D\u0089E\u008bF\u008dG\u008f"+
		"H\u0091I\u0093J\u0095K\u0097L\u0099M\u009bN\u009dO\u009fP\u00a1Q\u00a3"+
		"R\u00a5S\u00a7T\u00a9U\u00abV\u00adW\u00afX\u00b1Y\u00b3\2\u00b5\2\u00b7"+
		"\2\u00b9\2\u00bb\2\u00bd\2\u00bf\2\u00c1\2\u00c3\2\u00c5\2\5\2\3\4\t\4"+
		"\2\f\f\17\17\4\2\13\13\"\"\4\2$$^^\4\2??^^\4\2\u00b2\u00b2\u00bc\u00bc"+
		"\3\2\62;\20\2C\\c|\u00c3\u00c3\u00cb\u00cb\u00cf\u00cf\u00d3\u00d3\u00d5"+
		"\u00d5\u00dc\u00dc\u00e3\u00e3\u00eb\u00eb\u00ef\u00ef\u00f3\u00f3\u00f5"+
		"\u00f5\u00fc\u00fc\2\u037e\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3"+
		"\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2"+
		"\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3"+
		"\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2"+
		"\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\2"+
		"9\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3"+
		"\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2"+
		"\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2"+
		"_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3"+
		"\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\2u\3\2\2\2\2w\3\2\2"+
		"\2\2y\3\2\2\2\2{\3\2\2\2\2}\3\2\2\2\2\177\3\2\2\2\2\u0081\3\2\2\2\2\u0083"+
		"\3\2\2\2\2\u0085\3\2\2\2\2\u0087\3\2\2\2\2\u0089\3\2\2\2\2\u008b\3\2\2"+
		"\2\2\u008d\3\2\2\2\2\u008f\3\2\2\2\2\u0091\3\2\2\2\2\u0093\3\2\2\2\2\u0095"+
		"\3\2\2\2\2\u0097\3\2\2\2\2\u0099\3\2\2\2\2\u009b\3\2\2\2\2\u009d\3\2\2"+
		"\2\3\u009f\3\2\2\2\3\u00a1\3\2\2\2\4\u00a3\3\2\2\2\4\u00a5\3\2\2\2\4\u00a7"+
		"\3\2\2\2\4\u00a9\3\2\2\2\4\u00ab\3\2\2\2\4\u00ad\3\2\2\2\4\u00af\3\2\2"+
		"\2\4\u00b1\3\2\2\2\5\u00c7\3\2\2\2\7\u00cb\3\2\2\2\t\u00cf\3\2\2\2\13"+
		"\u00d3\3\2\2\2\r\u00d7\3\2\2\2\17\u00da\3\2\2\2\21\u00de\3\2\2\2\23\u00e1"+
		"\3\2\2\2\25\u00e4\3\2\2\2\27\u00e9\3\2\2\2\31\u00ee\3\2\2\2\33\u00f2\3"+
		"\2\2\2\35\u00fa\3\2\2\2\37\u0102\3\2\2\2!\u010b\3\2\2\2#\u0114\3\2\2\2"+
		"%\u011e\3\2\2\2\'\u0126\3\2\2\2)\u012f\3\2\2\2+\u0135\3\2\2\2-\u013e\3"+
		"\2\2\2/\u0146\3\2\2\2\61\u014f\3\2\2\2\63\u0158\3\2\2\2\65\u0162\3\2\2"+
		"\2\67\u0164\3\2\2\29\u0166\3\2\2\2;\u0168\3\2\2\2=\u016a\3\2\2\2?\u016c"+
		"\3\2\2\2A\u016e\3\2\2\2C\u0171\3\2\2\2E\u0173\3\2\2\2G\u0175\3\2\2\2I"+
		"\u0177\3\2\2\2K\u0179\3\2\2\2M\u017b\3\2\2\2O\u017d\3\2\2\2Q\u017f\3\2"+
		"\2\2S\u0181\3\2\2\2U\u0186\3\2\2\2W\u018c\3\2\2\2Y\u018e\3\2\2\2[\u0193"+
		"\3\2\2\2]\u019c\3\2\2\2_\u01a4\3\2\2\2a\u01ad\3\2\2\2c\u01b4\3\2\2\2e"+
		"\u01bb\3\2\2\2g\u01c0\3\2\2\2i\u01c7\3\2\2\2k\u01cf\3\2\2\2m\u01d5\3\2"+
		"\2\2o\u01dd\3\2\2\2q\u01e2\3\2\2\2s\u01e8\3\2\2\2u\u01f9\3\2\2\2w\u020d"+
		"\3\2\2\2y\u0220\3\2\2\2{\u0223\3\2\2\2}\u022a\3\2\2\2\177\u0232\3\2\2"+
		"\2\u0081\u023f\3\2\2\2\u0083\u024a\3\2\2\2\u0085\u0260\3\2\2\2\u0087\u0265"+
		"\3\2\2\2\u0089\u026f\3\2\2\2\u008b\u028c\3\2\2\2\u008d\u029c\3\2\2\2\u008f"+
		"\u02ad\3\2\2\2\u0091\u02ba\3\2\2\2\u0093\u02c3\3\2\2\2\u0095\u02cf\3\2"+
		"\2\2\u0097\u02d6\3\2\2\2\u0099\u02d8\3\2\2\2\u009b\u02df\3\2\2\2\u009d"+
		"\u02e6\3\2\2\2\u009f\u02e8\3\2\2\2\u00a1\u02f2\3\2\2\2\u00a3\u02f5\3\2"+
		"\2\2\u00a5\u02fa\3\2\2\2\u00a7\u02ff\3\2\2\2\u00a9\u0302\3\2\2\2\u00ab"+
		"\u0305\3\2\2\2\u00ad\u0313\3\2\2\2\u00af\u031f\3\2\2\2\u00b1\u0332\3\2"+
		"\2\2\u00b3\u0343\3\2\2\2\u00b5\u0345\3\2\2\2\u00b7\u0347\3\2\2\2\u00b9"+
		"\u0349\3\2\2\2\u00bb\u034b\3\2\2\2\u00bd\u034d\3\2\2\2\u00bf\u034f\3\2"+
		"\2\2\u00c1\u0351\3\2\2\2\u00c3\u0353\3\2\2\2\u00c5\u0355\3\2\2\2\u00c7"+
		"\u00c8\7u\2\2\u00c8\u00c9\7w\2\2\u00c9\u00ca\7d\2\2\u00ca\6\3\2\2\2\u00cb"+
		"\u00cc\7w\2\2\u00cc\u00cd\7u\2\2\u00cd\u00ce\7g\2\2\u00ce\b\3\2\2\2\u00cf"+
		"\u00d0\7f\2\2\u00d0\u00d1\7u\2\2\u00d1\u00d2\7n\2\2\u00d2\n\3\2\2\2\u00d3"+
		"\u00d4\7x\2\2\u00d4\u00d5\7c\2\2\u00d5\u00d6\7t\2\2\u00d6\f\3\2\2\2\u00d7"+
		"\u00d8\7c\2\2\u00d8\u00d9\7u\2\2\u00d9\16\3\2\2\2\u00da\u00db\7j\2\2\u00db"+
		"\u00dc\7c\2\2\u00dc\u00dd\7u\2\2\u00dd\20\3\2\2\2\u00de\u00df\7q\2\2\u00df"+
		"\u00e0\7p\2\2\u00e0\22\3\2\2\2\u00e1\u00e2\7k\2\2\u00e2\u00e3\7u\2\2\u00e3"+
		"\24\3\2\2\2\u00e4\u00e5\7k\2\2\u00e5\u00e6\7p\2\2\u00e6\u00e7\7v\2\2\u00e7"+
		"\u00e8\7q\2\2\u00e8\26\3\2\2\2\u00e9\u00ea\7y\2\2\u00ea\u00eb\7k\2\2\u00eb"+
		"\u00ec\7v\2\2\u00ec\u00ed\7j\2\2\u00ed\30\3\2\2\2\u00ee\u00ef\7c\2\2\u00ef"+
		"\u00f0\7p\2\2\u00f0\u00f1\7{\2\2\u00f1\32\3\2\2\2\u00f2\u00f3\7g\2\2\u00f3"+
		"\u00f4\7z\2\2\u00f4\u00f5\7v\2\2\u00f5\u00f6\7g\2\2\u00f6\u00f7\7p\2\2"+
		"\u00f7\u00f8\7f\2\2\u00f8\u00f9\7u\2\2\u00f9\34\3\2\2\2\u00fa\u00fb\7"+
		"e\2\2\u00fb\u00fc\7q\2\2\u00fc\u00fd\7p\2\2\u00fd\u00fe\7e\2\2\u00fe\u00ff"+
		"\7g\2\2\u00ff\u0100\7r\2\2\u0100\u0101\7v\2\2\u0101\36\3\2\2\2\u0102\u0103"+
		"\7c\2\2\u0103\u0104\7d\2\2\u0104\u0105\7u\2\2\u0105\u0106\7v\2\2\u0106"+
		"\u0107\7t\2\2\u0107\u0108\7c\2\2\u0108\u0109\7e\2\2\u0109\u010a\7v\2\2"+
		"\u010a \3\2\2\2\u010b\u010c\7v\2\2\u010c\u010d\7g\2\2\u010d\u010e\7t\2"+
		"\2\u010e\u010f\7o\2\2\u010f\u0110\7k\2\2\u0110\u0111\7p\2\2\u0111\u0112"+
		"\7c\2\2\u0112\u0113\7n\2\2\u0113\"\3\2\2\2\u0114\u0115\7e\2\2\u0115\u0116"+
		"\7q\2\2\u0116\u0117\7o\2\2\u0117\u0118\7r\2\2\u0118\u0119\7q\2\2\u0119"+
		"\u011a\7p\2\2\u011a\u011b\7g\2\2\u011b\u011c\7p\2\2\u011c\u011d\7v\2\2"+
		"\u011d$\3\2\2\2\u011e\u011f\7h\2\2\u011f\u0120\7g\2\2\u0120\u0121\7c\2"+
		"\2\u0121\u0122\7v\2\2\u0122\u0123\7w\2\2\u0123\u0124\7t\2\2\u0124\u0125"+
		"\7g\2\2\u0125&\3\2\2\2\u0126\u0127\7t\2\2\u0127\u0128\7g\2\2\u0128\u0129"+
		"\7s\2\2\u0129\u012a\7w\2\2\u012a\u012b\7k\2\2\u012b\u012c\7t\2\2\u012c"+
		"\u012d\7g\2\2\u012d\u012e\7f\2\2\u012e(\3\2\2\2\u012f\u0130\7h\2\2\u0130"+
		"\u0131\7k\2\2\u0131\u0132\7p\2\2\u0132\u0133\7c\2\2\u0133\u0134\7n\2\2"+
		"\u0134*\3\2\2\2\u0135\u0136\7g\2\2\u0136\u0137\7p\2\2\u0137\u0138\7e\2"+
		"\2\u0138\u0139\7n\2\2\u0139\u013a\7q\2\2\u013a\u013b\7u\2\2\u013b\u013c"+
		"\7g\2\2\u013c\u013d\7f\2\2\u013d,\3\2\2\2\u013e\u013f\7r\2\2\u013f\u0140"+
		"\7t\2\2\u0140\u0141\7k\2\2\u0141\u0142\7x\2\2\u0142\u0143\7c\2\2\u0143"+
		"\u0144\7v\2\2\u0144\u0145\7g\2\2\u0145.\3\2\2\2\u0146\u0147\7t\2\2\u0147"+
		"\u0148\7g\2\2\u0148\u0149\7c\2\2\u0149\u014a\7e\2\2\u014a\u014b\7v\2\2"+
		"\u014b\u014c\7k\2\2\u014c\u014d\7x\2\2\u014d\u014e\7g\2\2\u014e\60\3\2"+
		"\2\2\u014f\u0150\7x\2\2\u0150\u0151\7q\2\2\u0151\u0152\7n\2\2\u0152\u0153"+
		"\7c\2\2\u0153\u0154\7v\2\2\u0154\u0155\7k\2\2\u0155\u0156\7n\2\2\u0156"+
		"\u0157\7g\2\2\u0157\62\3\2\2\2\u0158\u0159\7f\2\2\u0159\u015a\7g\2\2\u015a"+
		"\u015b\7e\2\2\u015b\u015c\7q\2\2\u015c\u015d\7t\2\2\u015d\u015e\7c\2\2"+
		"\u015e\u015f\7d\2\2\u015f\u0160\7n\2\2\u0160\u0161\7g\2\2\u0161\64\3\2"+
		"\2\2\u0162\u0163\7*\2\2\u0163\66\3\2\2\2\u0164\u0165\7+\2\2\u01658\3\2"+
		"\2\2\u0166\u0167\7]\2\2\u0167:\3\2\2\2\u0168\u0169\7_\2\2\u0169<\3\2\2"+
		"\2\u016a\u016b\7}\2\2\u016b>\3\2\2\2\u016c\u016d\7\177\2\2\u016d@\3\2"+
		"\2\2\u016e\u016f\7@\2\2\u016f\u0170\b \2\2\u0170B\3\2\2\2\u0171\u0172"+
		"\7>\2\2\u0172D\3\2\2\2\u0173\u0174\7B\2\2\u0174F\3\2\2\2\u0175\u0176\7"+
		"%\2\2\u0176H\3\2\2\2\u0177\u0178\7<\2\2\u0178J\3\2\2\2\u0179\u017a\7."+
		"\2\2\u017aL\3\2\2\2\u017b\u017c\7\60\2\2\u017cN\3\2\2\2\u017d\u017e\7"+
		"?\2\2\u017eP\3\2\2\2\u017f\u0180\7,\2\2\u0180R\3\2\2\2\u0181\u0182\7\60"+
		"\2\2\u0182\u0183\7\60\2\2\u0183\u0184\7\60\2\2\u0184T\3\2\2\2\u0185\u0187"+
		"\7=\2\2\u0186\u0185\3\2\2\2\u0187\u0188\3\2\2\2\u0188\u0186\3\2\2\2\u0188"+
		"\u0189\3\2\2\2\u0189\u018a\3\2\2\2\u018a\u018b\b*\3\2\u018bV\3\2\2\2\u018c"+
		"\u018d\7-\2\2\u018dX\3\2\2\2\u018e\u018f\7y\2\2\u018f\u0190\7q\2\2\u0190"+
		"\u0191\7t\2\2\u0191\u0192\7f\2\2\u0192Z\3\2\2\2\u0193\u0194\7t\2\2\u0194"+
		"\u0195\7g\2\2\u0195\u0196\7u\2\2\u0196\u0197\7q\2\2\u0197\u0198\7w\2\2"+
		"\u0198\u0199\7t\2\2\u0199\u019a\7e\2\2\u019a\u019b\7g\2\2\u019b\\\3\2"+
		"\2\2\u019c\u019d\7k\2\2\u019d\u019e\7p\2\2\u019e\u019f\7v\2\2\u019f\u01a0"+
		"\7g\2\2\u01a0\u01a1\7i\2\2\u01a1\u01a2\7g\2\2\u01a2\u01a3\7t\2\2\u01a3"+
		"^\3\2\2\2\u01a4\u01a5\7h\2\2\u01a5\u01a6\7w\2\2\u01a6\u01a7\7p\2\2\u01a7"+
		"\u01a8\7e\2\2\u01a8\u01a9\7v\2\2\u01a9\u01aa\7k\2\2\u01aa\u01ab\7q\2\2"+
		"\u01ab\u01ac\7p\2\2\u01ac`\3\2\2\2\u01ad\u01ae\7q\2\2\u01ae\u01af\7d\2"+
		"\2\u01af\u01b0\7l\2\2\u01b0\u01b1\7g\2\2\u01b1\u01b2\7e\2\2\u01b2\u01b3"+
		"\7v\2\2\u01b3b\3\2\2\2\u01b4\u01b5\7f\2\2\u01b5\u01b6\7q\2\2\u01b6\u01b7"+
		"\7w\2\2\u01b7\u01b8\7d\2\2\u01b8\u01b9\7n\2\2\u01b9\u01ba\7g\2\2\u01ba"+
		"d\3\2\2\2\u01bb\u01bc\7n\2\2\u01bc\u01bd\7q\2\2\u01bd\u01be\7p\2\2\u01be"+
		"\u01bf\7i\2\2\u01bff\3\2\2\2\u01c0\u01c1\7u\2\2\u01c1\u01c2\7v\2\2\u01c2"+
		"\u01c3\7t\2\2\u01c3\u01c4\7k\2\2\u01c4\u01c5\7p\2\2\u01c5\u01c6\7i\2\2"+
		"\u01c6h\3\2\2\2\u01c7\u01c8\7d\2\2\u01c8\u01c9\7q\2\2\u01c9\u01ca\7q\2"+
		"\2\u01ca\u01cb\7n\2\2\u01cb\u01cc\7g\2\2\u01cc\u01cd\7c\2\2\u01cd\u01ce"+
		"\7p\2\2\u01cej\3\2\2\2\u01cf\u01d0\7f\2\2\u01d0\u01d1\7c\2\2\u01d1\u01d2"+
		"\7v\2\2\u01d2\u01d3\7g\2\2\u01d3\u01d4\7z\2\2\u01d4l\3\2\2\2\u01d5\u01d6"+
		"\7k\2\2\u01d6\u01d7\7p\2\2\u01d7\u01d8\7u\2\2\u01d8\u01d9\7v\2\2\u01d9"+
		"\u01da\7c\2\2\u01da\u01db\7p\2\2\u01db\u01dc\7v\2\2\u01dcn\3\2\2\2\u01dd"+
		"\u01de\7v\2\2\u01de\u01df\7k\2\2\u01df\u01e0\7o\2\2\u01e0\u01e1\7g\2\2"+
		"\u01e1p\3\2\2\2\u01e2\u01e3\7g\2\2\u01e3\u01e4\7o\2\2\u01e4\u01e5\7r\2"+
		"\2\u01e5\u01e6\7v\2\2\u01e6\u01e7\7{\2\2\u01e7r\3\2\2\2\u01e8\u01e9\7"+
		"\61\2\2\u01e9\u01ea\7,\2\2\u01ea\u01ee\3\2\2\2\u01eb\u01ed\13\2\2\2\u01ec"+
		"\u01eb\3\2\2\2\u01ed\u01f0\3\2\2\2\u01ee\u01ef\3\2\2\2\u01ee\u01ec\3\2"+
		"\2\2\u01ef\u01f1\3\2\2\2\u01f0\u01ee\3\2\2\2\u01f1\u01f2\7,\2\2\u01f2"+
		"\u01f3\7\61\2\2\u01f3\u01f4\3\2\2\2\u01f4\u01f5\b9\4\2\u01f5t\3\2\2\2"+
		"\u01f6\u01f8\t\2\2\2\u01f7\u01f6\3\2\2\2\u01f8\u01fb\3\2\2\2\u01f9\u01f7"+
		"\3\2\2\2\u01f9\u01fa\3\2\2\2\u01fa\u01ff\3\2\2\2\u01fb\u01f9\3\2\2\2\u01fc"+
		"\u01fe\t\3\2\2\u01fd\u01fc\3\2\2\2\u01fe\u0201\3\2\2\2\u01ff\u01fd\3\2"+
		"\2\2\u01ff\u0200\3\2\2\2\u0200\u0202\3\2\2\2\u0201\u01ff\3\2\2\2\u0202"+
		"\u0203\7\61\2\2\u0203\u0204\7\61\2\2\u0204\u0208\3\2\2\2\u0205\u0207\n"+
		"\2\2\2\u0206\u0205\3\2\2\2\u0207\u020a\3\2\2\2\u0208\u0206\3\2\2\2\u0208"+
		"\u0209\3\2\2\2\u0209\u020b\3\2\2\2\u020a\u0208\3\2\2\2\u020b\u020c\b:"+
		"\4\2\u020cv\3\2\2\2\u020d\u0210\7G\2\2\u020e\u0211\5W+\2\u020f\u0211\5"+
		"\u00bf_\2\u0210\u020e\3\2\2\2\u0210\u020f\3\2\2\2\u0210\u0211\3\2\2\2"+
		"\u0211\u0213\3\2\2\2\u0212\u0214\5\u00c3a\2\u0213\u0212\3\2\2\2\u0214"+
		"\u0215\3\2\2\2\u0215\u0213\3\2\2\2\u0215\u0216\3\2\2\2\u0216x\3\2\2\2"+
		"\u0217\u0218\7v\2\2\u0218\u0219\7t\2\2\u0219\u021a\7w\2\2\u021a\u0221"+
		"\7g\2\2\u021b\u021c\7h\2\2\u021c\u021d\7c\2\2\u021d\u021e\7n\2\2\u021e"+
		"\u021f\7u\2\2\u021f\u0221\7g\2\2\u0220\u0217\3\2\2\2\u0220\u021b\3\2\2"+
		"\2\u0221z\3\2\2\2\u0222\u0224\5W+\2\u0223\u0222\3\2\2\2\u0223\u0224\3"+
		"\2\2\2\u0224\u0226\3\2\2\2\u0225\u0227\5\u00c3a\2\u0226\u0225\3\2\2\2"+
		"\u0227\u0228\3\2\2\2\u0228\u0226\3\2\2\2\u0228\u0229\3\2\2\2\u0229|\3"+
		"\2\2\2\u022a\u022c\5\u00bf_\2\u022b\u022d\5\u00c3a\2\u022c\u022b\3\2\2"+
		"\2\u022d\u022e\3\2\2\2\u022e\u022c\3\2\2\2\u022e\u022f\3\2\2\2\u022f~"+
		"\3\2\2\2\u0230\u0233\5W+\2\u0231\u0233\5\u00bf_\2\u0232\u0230\3\2\2\2"+
		"\u0232\u0231\3\2\2\2\u0232\u0233\3\2\2\2\u0233\u0235\3\2\2\2\u0234\u0236"+
		"\5\u00c3a\2\u0235\u0234\3\2\2\2\u0236\u0237\3\2\2\2\u0237\u0235\3\2\2"+
		"\2\u0237\u0238\3\2\2\2\u0238\u0239\3\2\2\2\u0239\u023b\5M&\2\u023a\u023c"+
		"\5\u00c3a\2\u023b\u023a\3\2\2\2\u023c\u023d\3\2\2\2\u023d\u023b\3\2\2"+
		"\2\u023d\u023e\3\2\2\2\u023e\u0080\3\2\2\2\u023f\u0245\7$\2\2\u0240\u0244"+
		"\n\4\2\2\u0241\u0242\7^\2\2\u0242\u0244\t\4\2\2\u0243\u0240\3\2\2\2\u0243"+
		"\u0241\3\2\2\2\u0244\u0247\3\2\2\2\u0245\u0243\3\2\2\2\u0245\u0246\3\2"+
		"\2\2\u0246\u0248\3\2\2\2\u0247\u0245\3\2\2\2\u0248\u0249\7$\2\2\u0249"+
		"\u0082\3\2\2\2\u024a\u024c\5O\'\2\u024b\u024d\5O\'\2\u024c\u024b\3\2\2"+
		"\2\u024d\u024e\3\2\2\2\u024e\u024c\3\2\2\2\u024e\u024f\3\2\2\2\u024f\u0255"+
		"\3\2\2\2\u0250\u0254\n\5\2\2\u0251\u0252\7^\2\2\u0252\u0254\t\5\2\2\u0253"+
		"\u0250\3\2\2\2\u0253\u0251\3\2\2\2\u0254\u0257\3\2\2\2\u0255\u0253\3\2"+
		"\2\2\u0255\u0256\3\2\2\2\u0256\u0258\3\2\2\2\u0257\u0255\3\2\2\2\u0258"+
		"\u025a\5O\'\2\u0259\u025b\5O\'\2\u025a\u0259\3\2\2\2\u025b\u025c\3\2\2"+
		"\2\u025c\u025a\3\2\2\2\u025c\u025d\3\2\2\2\u025d\u025e\3\2\2\2\u025e\u025f"+
		"\bA\5\2\u025f\u0084\3\2\2\2\u0260\u0261\7)\2\2\u0261\u0262\bB\6\2\u0262"+
		"\u0263\3\2\2\2\u0263\u0264\bB\7\2\u0264\u0086\3\2\2\2\u0265\u0267\5\u00bf"+
		"_\2\u0266\u0268\5\u00bf_\2\u0267\u0266\3\2\2\2\u0268\u0269\3\2\2\2\u0269"+
		"\u0267\3\2\2\2\u0269\u026a\3\2\2\2\u026a\u026b\3\2\2\2\u026b\u026c\bC"+
		"\b\2\u026c\u026d\3\2\2\2\u026d\u026e\bC\t\2\u026e\u0088\3\2\2\2\u026f"+
		"\u0270\5\u008bE\2\u0270\u0276\5C!\2\u0271\u0272\5\u008bE\2\u0272\u0273"+
		"\5M&\2\u0273\u0275\3\2\2\2\u0274\u0271\3\2\2\2\u0275\u0278\3\2\2\2\u0276"+
		"\u0274\3\2\2\2\u0276\u0277\3\2\2\2\u0277\u0279\3\2\2\2\u0278\u0276\3\2"+
		"\2\2\u0279\u027a\5\u008bE\2\u027a\u0286\3\2\2\2\u027b\u0281\5K%\2\u027c"+
		"\u027d\5\u008bE\2\u027d\u027e\5M&\2\u027e\u0280\3\2\2\2\u027f\u027c\3"+
		"\2\2\2\u0280\u0283\3\2\2\2\u0281\u027f\3\2\2\2\u0281\u0282\3\2\2\2\u0282"+
		"\u0284\3\2\2\2\u0283\u0281\3\2\2\2\u0284\u0285\5\u008bE\2\u0285\u0287"+
		"\3\2\2\2\u0286\u027b\3\2\2\2\u0286\u0287\3\2\2\2\u0287\u0288\3\2\2\2\u0288"+
		"\u0289\5A \2\u0289\u008a\3\2\2\2\u028a\u028d\5\u00c5b\2\u028b\u028d\5"+
		"\u00c1`\2\u028c\u028a\3\2\2\2\u028c\u028b\3\2\2\2\u028d\u0294\3\2\2\2"+
		"\u028e\u0293\5\u00c3a\2\u028f\u0293\5\u00c5b\2\u0290\u0293\5\u00bf_\2"+
		"\u0291\u0293\5\u00c1`\2\u0292\u028e\3\2\2\2\u0292\u028f\3\2\2\2\u0292"+
		"\u0290\3\2\2\2\u0292\u0291\3\2\2\2\u0293\u0296\3\2\2\2\u0294\u0292\3\2"+
		"\2\2\u0294\u0295\3\2\2\2\u0295\u008c\3\2\2\2\u0296\u0294\3\2\2\2\u0297"+
		"\u029d\5\u00c5b\2\u0298\u029d\5\u00b7[\2\u0299\u029d\5\u00b3Y\2\u029a"+
		"\u029d\5\u00b5Z\2\u029b\u029d\5\u00b9\\\2\u029c\u0297\3\2\2\2\u029c\u0298"+
		"\3\2\2\2\u029c\u0299\3\2\2\2\u029c\u029a\3\2\2\2\u029c\u029b\3\2\2\2\u029d"+
		"\u02a9\3\2\2\2\u029e\u02a8\5\u00c1`\2\u029f\u02a8\5\u00bb]\2\u02a0\u02a8"+
		"\5\u00bd^\2\u02a1\u02a8\5\u00b7[\2\u02a2\u02a8\5\u00b3Y\2\u02a3\u02a8"+
		"\5\u00b5Z\2\u02a4\u02a8\5\u00b9\\\2\u02a5\u02a8\5\u00c5b\2\u02a6\u02a8"+
		"\5\u00c3a\2\u02a7\u029e\3\2\2\2\u02a7\u029f\3\2\2\2\u02a7\u02a0\3\2\2"+
		"\2\u02a7\u02a1\3\2\2\2\u02a7\u02a2\3\2\2\2\u02a7\u02a3\3\2\2\2\u02a7\u02a4"+
		"\3\2\2\2\u02a7\u02a5\3\2\2\2\u02a7\u02a6\3\2\2\2\u02a8\u02ab\3\2\2\2\u02a9"+
		"\u02a7\3\2\2\2\u02a9\u02aa\3\2\2\2\u02aa\u008e\3\2\2\2\u02ab\u02a9\3\2"+
		"\2\2\u02ac\u02ae\5\u0097K\2\u02ad\u02ac\3\2\2\2\u02ae\u02af\3\2\2\2\u02af"+
		"\u02ad\3\2\2\2\u02af\u02b0\3\2\2\2\u02b0\u02b4\3\2\2\2\u02b1\u02b3\5\u0095"+
		"J\2\u02b2\u02b1\3\2\2\2\u02b3\u02b6\3\2\2\2\u02b4\u02b2\3\2\2\2\u02b4"+
		"\u02b5\3\2\2\2\u02b5\u02b7\3\2\2\2\u02b6\u02b4\3\2\2\2\u02b7\u02b8\bG"+
		"\n\2\u02b8\u0090\3\2\2\2\u02b9\u02bb\5\u0095J\2\u02ba\u02b9\3\2\2\2\u02bb"+
		"\u02bc\3\2\2\2\u02bc\u02ba\3\2\2\2\u02bc\u02bd\3\2\2\2\u02bd\u02bf\3\2"+
		"\2\2\u02be\u02c0\7\2\2\3\u02bf\u02be\3\2\2\2\u02bf\u02c0\3\2\2\2\u02c0"+
		"\u02c1\3\2\2\2\u02c1\u02c2\bH\13\2\u02c2\u0092\3\2\2\2\u02c3\u02c4\7#"+
		"\2\2\u02c4\u02c5\7#\2\2\u02c5\u02c9\3\2\2\2\u02c6\u02c8\13\2\2\2\u02c7"+
		"\u02c6\3\2\2\2\u02c8\u02cb\3\2\2\2\u02c9\u02ca\3\2\2\2\u02c9\u02c7\3\2"+
		"\2\2\u02ca\u02cc\3\2\2\2\u02cb\u02c9\3\2\2\2\u02cc\u02cd\5\u008fG\2\u02cd"+
		"\u02ce\bI\f\2\u02ce\u0094\3\2\2\2\u02cf\u02d0\t\3\2\2\u02d0\u0096\3\2"+
		"\2\2\u02d1\u02d3\7\17\2\2\u02d2\u02d1\3\2\2\2\u02d2\u02d3\3\2\2\2\u02d3"+
		"\u02d4\3\2\2\2\u02d4\u02d7\7\f\2\2\u02d5\u02d7\7\17\2\2\u02d6\u02d2\3"+
		"\2\2\2\u02d6\u02d5\3\2\2\2\u02d7\u0098\3\2\2\2\u02d8\u02d9\7k\2\2\u02d9"+
		"\u02da\7p\2\2\u02da\u02db\7f\2\2\u02db\u02dc\7g\2\2\u02dc\u02dd\7p\2\2"+
		"\u02dd\u02de\7v\2\2\u02de\u009a\3\2\2\2\u02df\u02e0\7f\2\2\u02e0\u02e1"+
		"\7g\2\2\u02e1\u02e2\7f\2\2\u02e2\u02e3\7g\2\2\u02e3\u02e4\7p\2\2\u02e4"+
		"\u02e5\7v\2\2\u02e5\u009c\3\2\2\2\u02e6\u02e7\13\2\2\2\u02e7\u009e\3\2"+
		"\2\2\u02e8\u02ea\5\u00bf_\2\u02e9\u02eb\5\u00bf_\2\u02ea\u02e9\3\2\2\2"+
		"\u02eb\u02ec\3\2\2\2\u02ec\u02ea\3\2\2\2\u02ec\u02ed\3\2\2\2\u02ed\u02ee"+
		"\3\2\2\2\u02ee\u02ef\bO\r\2\u02ef\u02f0\3\2\2\2\u02f0\u02f1\bO\16\2\u02f1"+
		"\u00a0\3\2\2\2\u02f2\u02f3\13\2\2\2\u02f3\u02f4\bP\17\2\u02f4\u00a2\3"+
		"\2\2\2\u02f5\u02f6\7)\2\2\u02f6\u02f7\bQ\20\2\u02f7\u02f8\3\2\2\2\u02f8"+
		"\u02f9\bQ\16\2\u02f9\u00a4\3\2\2\2\u02fa\u02fb\7^\2\2\u02fb\u02fc\7)\2"+
		"\2\u02fc\u02fd\3\2\2\2\u02fd\u02fe\bR\21\2\u02fe\u00a6\3\2\2\2\u02ff\u0300"+
		"\7^\2\2\u0300\u0301\bS\22\2\u0301\u00a8\3\2\2\2\u0302\u0303\13\2\2\2\u0303"+
		"\u0304\bT\23\2\u0304\u00aa\3\2\2\2\u0305\u0306\7\'\2\2\u0306\u0307\7S"+
		"\2\2\u0307\u0308\7W\2\2\u0308\u0309\7Q\2\2\u0309\u030a\7V\2\2\u030a\u030b"+
		"\7G\2\2\u030b\u030c\7a\2\2\u030c\u030d\7D\2\2\u030d\u030e\7G\2\2\u030e"+
		"\u030f\7I\2\2\u030f\u0310\7K\2\2\u0310\u0311\7P\2\2\u0311\u0312\7\'\2"+
		"\2\u0312\u00ac\3\2\2\2\u0313\u0314\7\'\2\2\u0314\u0315\7S\2\2\u0315\u0316"+
		"\7W\2\2\u0316\u0317\7Q\2\2\u0317\u0318\7V\2\2\u0318\u0319\7G\2\2\u0319"+
		"\u031a\7a\2\2\u031a\u031b\7G\2\2\u031b\u031c\7P\2\2\u031c\u031d\7F\2\2"+
		"\u031d\u031e\7\'\2\2\u031e\u00ae\3\2\2\2\u031f\u0320\7\'\2\2\u0320\u0321"+
		"\7G\2\2\u0321\u0322\7Z\2\2\u0322\u0323\7R\2\2\u0323\u0324\7T\2\2\u0324"+
		"\u0325\7G\2\2\u0325\u0326\7U\2\2\u0326\u0327\7U\2\2\u0327\u0328\7K\2\2"+
		"\u0328\u0329\7Q\2\2\u0329\u032a\7P\2\2\u032a\u032b\7a\2\2\u032b\u032c"+
		"\7D\2\2\u032c\u032d\7G\2\2\u032d\u032e\7I\2\2\u032e\u032f\7K\2\2\u032f"+
		"\u0330\7P\2\2\u0330\u0331\7\'\2\2\u0331\u00b0\3\2\2\2\u0332\u0333\7\'"+
		"\2\2\u0333\u0334\7G\2\2\u0334\u0335\7Z\2\2\u0335\u0336\7R\2\2\u0336\u0337"+
		"\7T\2\2\u0337\u0338\7G\2\2\u0338\u0339\7U\2\2\u0339\u033a\7U\2\2\u033a"+
		"\u033b\7K\2\2\u033b\u033c\7Q\2\2\u033c\u033d\7P\2\2\u033d\u033e\7a\2\2"+
		"\u033e\u033f\7G\2\2\u033f\u0340\7P\2\2\u0340\u0341\7F\2\2\u0341\u0342"+
		"\7\'\2\2\u0342\u00b2\3\2\2\2\u0343\u0344\7&\2\2\u0344\u00b4\3\2\2\2\u0345"+
		"\u0346\7\u20ae\2\2\u0346\u00b6\3\2\2\2\u0347\u0348\7\'\2\2\u0348\u00b8"+
		"\3\2\2\2\u0349\u034a\t\6\2\2\u034a\u00ba\3\2\2\2\u034b\u034c\7\u00b9\2"+
		"\2\u034c\u00bc\3\2\2\2\u034d\u034e\7\61\2\2\u034e\u00be\3\2\2\2\u034f"+
		"\u0350\7/\2\2\u0350\u00c0\3\2\2\2\u0351\u0352\7a\2\2\u0352\u00c2\3\2\2"+
		"\2\u0353\u0354\t\7\2\2\u0354\u00c4\3\2\2\2\u0355\u0356\t\b\2\2\u0356\u00c6"+
		"\3\2\2\2,\2\3\4\u0188\u01ee\u01f9\u01fd\u01ff\u0208\u0210\u0215\u0220"+
		"\u0223\u0228\u022e\u0232\u0237\u023d\u0243\u0245\u024e\u0253\u0255\u025c"+
		"\u0269\u0276\u0281\u0286\u028c\u0292\u0294\u029c\u02a7\u02a9\u02af\u02b4"+
		"\u02bc\u02bf\u02c9\u02d2\u02d6\u02ec\24\3 \2\3*\3\b\2\2\3A\4\3B\5\4\4"+
		"\2\3C\6\4\3\2\3G\7\2\3\2\3I\b\3O\t\4\2\2\3P\n\3Q\13\3R\f\3S\r\3T\16";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}