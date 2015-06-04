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
		INTO=10, WITH=11, EXTENDS=12, ABSTRACT=13, SINGLE=14, REQUIRED=15, TERMINAL=16, 
		ROOT=17, IMPLICIT=18, FEATURE=19, FINAL=20, ENCLOSED=21, FACET=22, LEFT_PARENTHESIS=23, 
		RIGHT_PARENTHESIS=24, LEFT_SQUARE=25, RIGHT_SQUARE=26, LIST=27, INLINE=28, 
		CLOSE_INLINE=29, HASHTAG=30, COLON=31, COMMA=32, DOT=33, EQUALS=34, SEMICOLON=35, 
		PLUS=36, WORD=37, RESOURCE=38, INT_TYPE=39, NATURAL_TYPE=40, NATIVE_TYPE=41, 
		DOUBLE_TYPE=42, STRING_TYPE=43, BOOLEAN_TYPE=44, MEASURE_TYPE=45, RATIO_TYPE=46, 
		DATE_TYPE=47, EMPTY=48, BLOCK_COMMENT=49, LINE_COMMENT=50, SCIENCE_NOT=51, 
		BOOLEAN_VALUE=52, NATURAL_VALUE=53, NEGATIVE_VALUE=54, DOUBLE_VALUE=55, 
		APHOSTROPHE=56, STRING_MULTILINE=57, PLATE_VALUE=58, IDENTIFIER=59, MEASURE_VALUE=60, 
		NEWLINE=61, SPACES=62, DOC=63, SP=64, NL=65, NEW_LINE_INDENT=66, DEDENT=67, 
		UNKNOWN_TOKEN=68, M_STRING_MULTILINE=69, M_CHARACTER=70, QUOTE=71, Q=72, 
		SLASH_Q=73, SLASH=74, CHARACTER=75, QUOTE_END=76, QUOTE_BEGIN=77;
	public static final int MULTILINE = 1;
	public static final int QUOTED = 2;
	public static String[] modeNames = {
		"DEFAULT_MODE", "MULTILINE", "QUOTED"
	};

	public static final String[] ruleNames = {
		"METAIDENTIFIER", "SUB", "USE", "DSL", "VAR", "AS", "HAS", "ON", "IS", 
		"INTO", "WITH", "EXTENDS", "ABSTRACT", "SINGLE", "REQUIRED", "TERMINAL", 
		"ROOT", "IMPLICIT", "FEATURE", "FINAL", "ENCLOSED", "FACET", "LEFT_PARENTHESIS", 
		"RIGHT_PARENTHESIS", "LEFT_SQUARE", "RIGHT_SQUARE", "LIST", "INLINE", 
		"CLOSE_INLINE", "HASHTAG", "COLON", "COMMA", "DOT", "EQUALS", "SEMICOLON", 
		"PLUS", "WORD", "RESOURCE", "INT_TYPE", "NATURAL_TYPE", "NATIVE_TYPE", 
		"DOUBLE_TYPE", "STRING_TYPE", "BOOLEAN_TYPE", "MEASURE_TYPE", "RATIO_TYPE", 
		"DATE_TYPE", "EMPTY", "BLOCK_COMMENT", "LINE_COMMENT", "SCIENCE_NOT", 
		"BOOLEAN_VALUE", "NATURAL_VALUE", "NEGATIVE_VALUE", "DOUBLE_VALUE", "APHOSTROPHE", 
		"STRING_MULTILINE", "PLATE_VALUE", "IDENTIFIER", "MEASURE_VALUE", "NEWLINE", 
		"SPACES", "DOC", "SP", "NL", "NEW_LINE_INDENT", "DEDENT", "UNKNOWN_TOKEN", 
		"M_STRING_MULTILINE", "M_CHARACTER", "QUOTE", "Q", "SLASH_Q", "SLASH", 
		"CHARACTER", "QUOTE_END", "QUOTE_BEGIN", "DOLLAR", "EURO", "PERCENTAGE", 
		"GRADE", "BY", "DIVIDED_BY", "DASH", "UNDERDASH", "DIGIT", "LETTER"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'Concept'", "'sub'", "'use'", "'dsl'", "'var'", "'as'", "'has'", 
		"'on'", "'is'", "'into'", "'with'", "'extends'", "'abstract'", "'single'", 
		"'required'", "'terminal'", "'root'", "'implicit'", "'feature'", "'final'", 
		"'enclosed'", "'facet'", "'('", "')'", "'['", "']'", "'...'", "'>'", "'<'", 
		"'#'", "':'", "','", "'.'", "'='", null, "'+'", "'word'", "'file'", "'integer'", 
		"'natural'", "'native'", "'double'", "'string'", "'boolean'", "'measure'", 
		"'ratio'", "'date'", "'empty'", null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, "'indent'", 
		"'dedent'", null, null, null, null, "'\"'", "'\\\"'", "'\\'", null, "'t53647656ext'", 
		"'te245656786xt'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "METAIDENTIFIER", "SUB", "USE", "DSL", "VAR", "AS", "HAS", "ON", 
		"IS", "INTO", "WITH", "EXTENDS", "ABSTRACT", "SINGLE", "REQUIRED", "TERMINAL", 
		"ROOT", "IMPLICIT", "FEATURE", "FINAL", "ENCLOSED", "FACET", "LEFT_PARENTHESIS", 
		"RIGHT_PARENTHESIS", "LEFT_SQUARE", "RIGHT_SQUARE", "LIST", "INLINE", 
		"CLOSE_INLINE", "HASHTAG", "COLON", "COMMA", "DOT", "EQUALS", "SEMICOLON", 
		"PLUS", "WORD", "RESOURCE", "INT_TYPE", "NATURAL_TYPE", "NATIVE_TYPE", 
		"DOUBLE_TYPE", "STRING_TYPE", "BOOLEAN_TYPE", "MEASURE_TYPE", "RATIO_TYPE", 
		"DATE_TYPE", "EMPTY", "BLOCK_COMMENT", "LINE_COMMENT", "SCIENCE_NOT", 
		"BOOLEAN_VALUE", "NATURAL_VALUE", "NEGATIVE_VALUE", "DOUBLE_VALUE", "APHOSTROPHE", 
		"STRING_MULTILINE", "PLATE_VALUE", "IDENTIFIER", "MEASURE_VALUE", "NEWLINE", 
		"SPACES", "DOC", "SP", "NL", "NEW_LINE_INDENT", "DEDENT", "UNKNOWN_TOKEN", 
		"M_STRING_MULTILINE", "M_CHARACTER", "QUOTE", "Q", "SLASH_Q", "SLASH", 
		"CHARACTER", "QUOTE_END", "QUOTE_BEGIN"
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

		case 34: 
			SEMICOLON_action((RuleContext)_localctx, actionIndex); 
			break;

		case 55: 
			APHOSTROPHE_action((RuleContext)_localctx, actionIndex); 
			break;

		case 56: 
			STRING_MULTILINE_action((RuleContext)_localctx, actionIndex); 
			break;

		case 60: 
			NEWLINE_action((RuleContext)_localctx, actionIndex); 
			break;

		case 62: 
			DOC_action((RuleContext)_localctx, actionIndex); 
			break;

		case 68: 
			M_STRING_MULTILINE_action((RuleContext)_localctx, actionIndex); 
			break;

		case 69: 
			M_CHARACTER_action((RuleContext)_localctx, actionIndex); 
			break;

		case 70: 
			QUOTE_action((RuleContext)_localctx, actionIndex); 
			break;

		case 71: 
			Q_action((RuleContext)_localctx, actionIndex); 
			break;

		case 72: 
			SLASH_Q_action((RuleContext)_localctx, actionIndex); 
			break;

		case 73: 
			SLASH_action((RuleContext)_localctx, actionIndex); 
			break;

		case 74: 
			CHARACTER_action((RuleContext)_localctx, actionIndex); 
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
	private void NEWLINE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 4: 
			 newlinesAndSpaces();  
			break;
		}
	}
	private void DOC_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 5: 
			emitToken(DOC);emitToken(NEWLINE); 
			break;
		}
	}
	private void M_STRING_MULTILINE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 6: 
			   setType(QUOTE_END);  
			break;
		}
	}
	private void M_CHARACTER_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 7: 
			   setType(CHARACTER);  
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

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2O\u02b6\b\1\b\1\b"+
		"\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n"+
		"\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21"+
		"\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30"+
		"\4\31\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37"+
		"\4 \t \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t"+
		"*\4+\t+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63"+
		"\4\64\t\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t"+
		"<\4=\t=\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4"+
		"H\tH\4I\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\4O\tO\4P\tP\4Q\tQ\4R\tR\4S\t"+
		"S\4T\tT\4U\tU\4V\tV\4W\tW\4X\tX\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3"+
		"\3\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7"+
		"\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\f"+
		"\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3"+
		"\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3"+
		"\21\3\21\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3"+
		"\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3"+
		"\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3"+
		"\27\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\34\3"+
		"\34\3\35\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3#\3#\3$\6"+
		"$\u0154\n$\r$\16$\u0155\3$\3$\3%\3%\3&\3&\3&\3&\3&\3\'\3\'\3\'\3\'\3\'"+
		"\3(\3(\3(\3(\3(\3(\3(\3(\3)\3)\3)\3)\3)\3)\3)\3)\3*\3*\3*\3*\3*\3*\3*"+
		"\3+\3+\3+\3+\3+\3+\3+\3,\3,\3,\3,\3,\3,\3,\3-\3-\3-\3-\3-\3-\3-\3-\3."+
		"\3.\3.\3.\3.\3.\3.\3.\3/\3/\3/\3/\3/\3/\3\60\3\60\3\60\3\60\3\60\3\61"+
		"\3\61\3\61\3\61\3\61\3\61\3\62\3\62\3\62\3\62\7\62\u01b0\n\62\f\62\16"+
		"\62\u01b3\13\62\3\62\3\62\3\62\3\62\3\62\3\63\3\63\3\63\3\63\7\63\u01be"+
		"\n\63\f\63\16\63\u01c1\13\63\3\63\3\63\3\64\3\64\3\64\5\64\u01c8\n\64"+
		"\3\64\6\64\u01cb\n\64\r\64\16\64\u01cc\3\65\3\65\3\65\3\65\3\65\3\65\3"+
		"\65\3\65\3\65\5\65\u01d8\n\65\3\66\5\66\u01db\n\66\3\66\6\66\u01de\n\66"+
		"\r\66\16\66\u01df\3\67\3\67\6\67\u01e4\n\67\r\67\16\67\u01e5\38\38\58"+
		"\u01ea\n8\38\68\u01ed\n8\r8\168\u01ee\38\38\68\u01f3\n8\r8\168\u01f4\3"+
		"9\39\39\39\39\3:\3:\6:\u01fe\n:\r:\16:\u01ff\3:\3:\3:\3:\3;\3;\6;\u0208"+
		"\n;\r;\16;\u0209\3<\3<\3<\3<\3<\7<\u0211\n<\f<\16<\u0214\13<\3=\3=\3="+
		"\3=\3=\5=\u021b\n=\3=\3=\3=\3=\3=\3=\3=\3=\3=\7=\u0226\n=\f=\16=\u0229"+
		"\13=\3>\6>\u022c\n>\r>\16>\u022d\3>\7>\u0231\n>\f>\16>\u0234\13>\3>\3"+
		">\3?\6?\u0239\n?\r?\16?\u023a\3?\5?\u023e\n?\3?\3?\3@\3@\3@\3@\3@\7@\u0247"+
		"\n@\f@\16@\u024a\13@\3@\3@\3@\3A\3A\3B\5B\u0252\nB\3B\3B\5B\u0256\nB\3"+
		"C\3C\3C\3C\3C\3C\3C\3D\3D\3D\3D\3D\3D\3D\3E\3E\3F\3F\6F\u026a\nF\rF\16"+
		"F\u026b\3F\3F\3F\3F\3G\3G\3G\3H\3H\3H\3H\3H\3I\3I\3I\3J\3J\3J\3J\3J\3"+
		"K\3K\3K\3L\3L\3L\3M\3M\3M\3M\3M\3M\3M\3M\3M\3M\3M\3M\3M\3N\3N\3N\3N\3"+
		"N\3N\3N\3N\3N\3N\3N\3N\3N\3N\3O\3O\3P\3P\3Q\3Q\3R\3R\3S\3S\3T\3T\3U\3"+
		"U\3V\3V\3W\3W\3X\3X\4\u01b1\u0248\2Y\5\3\7\4\t\5\13\6\r\7\17\b\21\t\23"+
		"\n\25\13\27\f\31\r\33\16\35\17\37\20!\21#\22%\23\'\24)\25+\26-\27/\30"+
		"\61\31\63\32\65\33\67\349\35;\36=\37? A!C\"E#G$I%K&M\'O(Q)S*U+W,Y-[.]"+
		"/_\60a\61c\62e\63g\64i\65k\66m\67o8q9s:u;w<y={>}?\177@\u0081A\u0083B\u0085"+
		"C\u0087D\u0089E\u008bF\u008dG\u008fH\u0091I\u0093J\u0095K\u0097L\u0099"+
		"M\u009bN\u009dO\u009f\2\u00a1\2\u00a3\2\u00a5\2\u00a7\2\u00a9\2\u00ab"+
		"\2\u00ad\2\u00af\2\u00b1\2\5\2\3\4\7\4\2\f\f\17\17\4\2\13\13\"\"\4\2\u00b2"+
		"\u00b2\u00bc\u00bc\3\2\62;\6\2C\\c|\u00d3\u00d3\u00f3\u00f3\u02d2\2\5"+
		"\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2"+
		"\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33"+
		"\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2"+
		"\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2"+
		"\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2"+
		"\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K"+
		"\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2"+
		"\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2"+
		"\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q"+
		"\3\2\2\2\2s\3\2\2\2\2u\3\2\2\2\2w\3\2\2\2\2y\3\2\2\2\2{\3\2\2\2\2}\3\2"+
		"\2\2\2\177\3\2\2\2\2\u0081\3\2\2\2\2\u0083\3\2\2\2\2\u0085\3\2\2\2\2\u0087"+
		"\3\2\2\2\2\u0089\3\2\2\2\2\u008b\3\2\2\2\3\u008d\3\2\2\2\3\u008f\3\2\2"+
		"\2\4\u0091\3\2\2\2\4\u0093\3\2\2\2\4\u0095\3\2\2\2\4\u0097\3\2\2\2\4\u0099"+
		"\3\2\2\2\4\u009b\3\2\2\2\4\u009d\3\2\2\2\5\u00b3\3\2\2\2\7\u00bb\3\2\2"+
		"\2\t\u00bf\3\2\2\2\13\u00c3\3\2\2\2\r\u00c7\3\2\2\2\17\u00cb\3\2\2\2\21"+
		"\u00ce\3\2\2\2\23\u00d2\3\2\2\2\25\u00d5\3\2\2\2\27\u00d8\3\2\2\2\31\u00dd"+
		"\3\2\2\2\33\u00e2\3\2\2\2\35\u00ea\3\2\2\2\37\u00f3\3\2\2\2!\u00fa\3\2"+
		"\2\2#\u0103\3\2\2\2%\u010c\3\2\2\2\'\u0111\3\2\2\2)\u011a\3\2\2\2+\u0122"+
		"\3\2\2\2-\u0128\3\2\2\2/\u0131\3\2\2\2\61\u0137\3\2\2\2\63\u0139\3\2\2"+
		"\2\65\u013b\3\2\2\2\67\u013d\3\2\2\29\u013f\3\2\2\2;\u0143\3\2\2\2=\u0146"+
		"\3\2\2\2?\u0148\3\2\2\2A\u014a\3\2\2\2C\u014c\3\2\2\2E\u014e\3\2\2\2G"+
		"\u0150\3\2\2\2I\u0153\3\2\2\2K\u0159\3\2\2\2M\u015b\3\2\2\2O\u0160\3\2"+
		"\2\2Q\u0165\3\2\2\2S\u016d\3\2\2\2U\u0175\3\2\2\2W\u017c\3\2\2\2Y\u0183"+
		"\3\2\2\2[\u018a\3\2\2\2]\u0192\3\2\2\2_\u019a\3\2\2\2a\u01a0\3\2\2\2c"+
		"\u01a5\3\2\2\2e\u01ab\3\2\2\2g\u01b9\3\2\2\2i\u01c4\3\2\2\2k\u01d7\3\2"+
		"\2\2m\u01da\3\2\2\2o\u01e1\3\2\2\2q\u01e9\3\2\2\2s\u01f6\3\2\2\2u\u01fb"+
		"\3\2\2\2w\u0205\3\2\2\2y\u020b\3\2\2\2{\u021a\3\2\2\2}\u022b\3\2\2\2\177"+
		"\u0238\3\2\2\2\u0081\u0241\3\2\2\2\u0083\u024e\3\2\2\2\u0085\u0255\3\2"+
		"\2\2\u0087\u0257\3\2\2\2\u0089\u025e\3\2\2\2\u008b\u0265\3\2\2\2\u008d"+
		"\u0267\3\2\2\2\u008f\u0271\3\2\2\2\u0091\u0274\3\2\2\2\u0093\u0279\3\2"+
		"\2\2\u0095\u027c\3\2\2\2\u0097\u0281\3\2\2\2\u0099\u0284\3\2\2\2\u009b"+
		"\u0287\3\2\2\2\u009d\u0294\3\2\2\2\u009f\u02a2\3\2\2\2\u00a1\u02a4\3\2"+
		"\2\2\u00a3\u02a6\3\2\2\2\u00a5\u02a8\3\2\2\2\u00a7\u02aa\3\2\2\2\u00a9"+
		"\u02ac\3\2\2\2\u00ab\u02ae\3\2\2\2\u00ad\u02b0\3\2\2\2\u00af\u02b2\3\2"+
		"\2\2\u00b1\u02b4\3\2\2\2\u00b3\u00b4\7E\2\2\u00b4\u00b5\7q\2\2\u00b5\u00b6"+
		"\7p\2\2\u00b6\u00b7\7e\2\2\u00b7\u00b8\7g\2\2\u00b8\u00b9\7r\2\2\u00b9"+
		"\u00ba\7v\2\2\u00ba\6\3\2\2\2\u00bb\u00bc\7u\2\2\u00bc\u00bd\7w\2\2\u00bd"+
		"\u00be\7d\2\2\u00be\b\3\2\2\2\u00bf\u00c0\7w\2\2\u00c0\u00c1\7u\2\2\u00c1"+
		"\u00c2\7g\2\2\u00c2\n\3\2\2\2\u00c3\u00c4\7f\2\2\u00c4\u00c5\7u\2\2\u00c5"+
		"\u00c6\7n\2\2\u00c6\f\3\2\2\2\u00c7\u00c8\7x\2\2\u00c8\u00c9\7c\2\2\u00c9"+
		"\u00ca\7t\2\2\u00ca\16\3\2\2\2\u00cb\u00cc\7c\2\2\u00cc\u00cd\7u\2\2\u00cd"+
		"\20\3\2\2\2\u00ce\u00cf\7j\2\2\u00cf\u00d0\7c\2\2\u00d0\u00d1\7u\2\2\u00d1"+
		"\22\3\2\2\2\u00d2\u00d3\7q\2\2\u00d3\u00d4\7p\2\2\u00d4\24\3\2\2\2\u00d5"+
		"\u00d6\7k\2\2\u00d6\u00d7\7u\2\2\u00d7\26\3\2\2\2\u00d8\u00d9\7k\2\2\u00d9"+
		"\u00da\7p\2\2\u00da\u00db\7v\2\2\u00db\u00dc\7q\2\2\u00dc\30\3\2\2\2\u00dd"+
		"\u00de\7y\2\2\u00de\u00df\7k\2\2\u00df\u00e0\7v\2\2\u00e0\u00e1\7j\2\2"+
		"\u00e1\32\3\2\2\2\u00e2\u00e3\7g\2\2\u00e3\u00e4\7z\2\2\u00e4\u00e5\7"+
		"v\2\2\u00e5\u00e6\7g\2\2\u00e6\u00e7\7p\2\2\u00e7\u00e8\7f\2\2\u00e8\u00e9"+
		"\7u\2\2\u00e9\34\3\2\2\2\u00ea\u00eb\7c\2\2\u00eb\u00ec\7d\2\2\u00ec\u00ed"+
		"\7u\2\2\u00ed\u00ee\7v\2\2\u00ee\u00ef\7t\2\2\u00ef\u00f0\7c\2\2\u00f0"+
		"\u00f1\7e\2\2\u00f1\u00f2\7v\2\2\u00f2\36\3\2\2\2\u00f3\u00f4\7u\2\2\u00f4"+
		"\u00f5\7k\2\2\u00f5\u00f6\7p\2\2\u00f6\u00f7\7i\2\2\u00f7\u00f8\7n\2\2"+
		"\u00f8\u00f9\7g\2\2\u00f9 \3\2\2\2\u00fa\u00fb\7t\2\2\u00fb\u00fc\7g\2"+
		"\2\u00fc\u00fd\7s\2\2\u00fd\u00fe\7w\2\2\u00fe\u00ff\7k\2\2\u00ff\u0100"+
		"\7t\2\2\u0100\u0101\7g\2\2\u0101\u0102\7f\2\2\u0102\"\3\2\2\2\u0103\u0104"+
		"\7v\2\2\u0104\u0105\7g\2\2\u0105\u0106\7t\2\2\u0106\u0107\7o\2\2\u0107"+
		"\u0108\7k\2\2\u0108\u0109\7p\2\2\u0109\u010a\7c\2\2\u010a\u010b\7n\2\2"+
		"\u010b$\3\2\2\2\u010c\u010d\7t\2\2\u010d\u010e\7q\2\2\u010e\u010f\7q\2"+
		"\2\u010f\u0110\7v\2\2\u0110&\3\2\2\2\u0111\u0112\7k\2\2\u0112\u0113\7"+
		"o\2\2\u0113\u0114\7r\2\2\u0114\u0115\7n\2\2\u0115\u0116\7k\2\2\u0116\u0117"+
		"\7e\2\2\u0117\u0118\7k\2\2\u0118\u0119\7v\2\2\u0119(\3\2\2\2\u011a\u011b"+
		"\7h\2\2\u011b\u011c\7g\2\2\u011c\u011d\7c\2\2\u011d\u011e\7v\2\2\u011e"+
		"\u011f\7w\2\2\u011f\u0120\7t\2\2\u0120\u0121\7g\2\2\u0121*\3\2\2\2\u0122"+
		"\u0123\7h\2\2\u0123\u0124\7k\2\2\u0124\u0125\7p\2\2\u0125\u0126\7c\2\2"+
		"\u0126\u0127\7n\2\2\u0127,\3\2\2\2\u0128\u0129\7g\2\2\u0129\u012a\7p\2"+
		"\2\u012a\u012b\7e\2\2\u012b\u012c\7n\2\2\u012c\u012d\7q\2\2\u012d\u012e"+
		"\7u\2\2\u012e\u012f\7g\2\2\u012f\u0130\7f\2\2\u0130.\3\2\2\2\u0131\u0132"+
		"\7h\2\2\u0132\u0133\7c\2\2\u0133\u0134\7e\2\2\u0134\u0135\7g\2\2\u0135"+
		"\u0136\7v\2\2\u0136\60\3\2\2\2\u0137\u0138\7*\2\2\u0138\62\3\2\2\2\u0139"+
		"\u013a\7+\2\2\u013a\64\3\2\2\2\u013b\u013c\7]\2\2\u013c\66\3\2\2\2\u013d"+
		"\u013e\7_\2\2\u013e8\3\2\2\2\u013f\u0140\7\60\2\2\u0140\u0141\7\60\2\2"+
		"\u0141\u0142\7\60\2\2\u0142:\3\2\2\2\u0143\u0144\7@\2\2\u0144\u0145\b"+
		"\35\2\2\u0145<\3\2\2\2\u0146\u0147\7>\2\2\u0147>\3\2\2\2\u0148\u0149\7"+
		"%\2\2\u0149@\3\2\2\2\u014a\u014b\7<\2\2\u014bB\3\2\2\2\u014c\u014d\7."+
		"\2\2\u014dD\3\2\2\2\u014e\u014f\7\60\2\2\u014fF\3\2\2\2\u0150\u0151\7"+
		"?\2\2\u0151H\3\2\2\2\u0152\u0154\7=\2\2\u0153\u0152\3\2\2\2\u0154\u0155"+
		"\3\2\2\2\u0155\u0153\3\2\2\2\u0155\u0156\3\2\2\2\u0156\u0157\3\2\2\2\u0157"+
		"\u0158\b$\3\2\u0158J\3\2\2\2\u0159\u015a\7-\2\2\u015aL\3\2\2\2\u015b\u015c"+
		"\7y\2\2\u015c\u015d\7q\2\2\u015d\u015e\7t\2\2\u015e\u015f\7f\2\2\u015f"+
		"N\3\2\2\2\u0160\u0161\7h\2\2\u0161\u0162\7k\2\2\u0162\u0163\7n\2\2\u0163"+
		"\u0164\7g\2\2\u0164P\3\2\2\2\u0165\u0166\7k\2\2\u0166\u0167\7p\2\2\u0167"+
		"\u0168\7v\2\2\u0168\u0169\7g\2\2\u0169\u016a\7i\2\2\u016a\u016b\7g\2\2"+
		"\u016b\u016c\7t\2\2\u016cR\3\2\2\2\u016d\u016e\7p\2\2\u016e\u016f\7c\2"+
		"\2\u016f\u0170\7v\2\2\u0170\u0171\7w\2\2\u0171\u0172\7t\2\2\u0172\u0173"+
		"\7c\2\2\u0173\u0174\7n\2\2\u0174T\3\2\2\2\u0175\u0176\7p\2\2\u0176\u0177"+
		"\7c\2\2\u0177\u0178\7v\2\2\u0178\u0179\7k\2\2\u0179\u017a\7x\2\2\u017a"+
		"\u017b\7g\2\2\u017bV\3\2\2\2\u017c\u017d\7f\2\2\u017d\u017e\7q\2\2\u017e"+
		"\u017f\7w\2\2\u017f\u0180\7d\2\2\u0180\u0181\7n\2\2\u0181\u0182\7g\2\2"+
		"\u0182X\3\2\2\2\u0183\u0184\7u\2\2\u0184\u0185\7v\2\2\u0185\u0186\7t\2"+
		"\2\u0186\u0187\7k\2\2\u0187\u0188\7p\2\2\u0188\u0189\7i\2\2\u0189Z\3\2"+
		"\2\2\u018a\u018b\7d\2\2\u018b\u018c\7q\2\2\u018c\u018d\7q\2\2\u018d\u018e"+
		"\7n\2\2\u018e\u018f\7g\2\2\u018f\u0190\7c\2\2\u0190\u0191\7p\2\2\u0191"+
		"\\\3\2\2\2\u0192\u0193\7o\2\2\u0193\u0194\7g\2\2\u0194\u0195\7c\2\2\u0195"+
		"\u0196\7u\2\2\u0196\u0197\7w\2\2\u0197\u0198\7t\2\2\u0198\u0199\7g\2\2"+
		"\u0199^\3\2\2\2\u019a\u019b\7t\2\2\u019b\u019c\7c\2\2\u019c\u019d\7v\2"+
		"\2\u019d\u019e\7k\2\2\u019e\u019f\7q\2\2\u019f`\3\2\2\2\u01a0\u01a1\7"+
		"f\2\2\u01a1\u01a2\7c\2\2\u01a2\u01a3\7v\2\2\u01a3\u01a4\7g\2\2\u01a4b"+
		"\3\2\2\2\u01a5\u01a6\7g\2\2\u01a6\u01a7\7o\2\2\u01a7\u01a8\7r\2\2\u01a8"+
		"\u01a9\7v\2\2\u01a9\u01aa\7{\2\2\u01aad\3\2\2\2\u01ab\u01ac\7\61\2\2\u01ac"+
		"\u01ad\7,\2\2\u01ad\u01b1\3\2\2\2\u01ae\u01b0\13\2\2\2\u01af\u01ae\3\2"+
		"\2\2\u01b0\u01b3\3\2\2\2\u01b1\u01b2\3\2\2\2\u01b1\u01af\3\2\2\2\u01b2"+
		"\u01b4\3\2\2\2\u01b3\u01b1\3\2\2\2\u01b4\u01b5\7,\2\2\u01b5\u01b6\7\61"+
		"\2\2\u01b6\u01b7\3\2\2\2\u01b7\u01b8\b\62\4\2\u01b8f\3\2\2\2\u01b9\u01ba"+
		"\7\61\2\2\u01ba\u01bb\7\61\2\2\u01bb\u01bf\3\2\2\2\u01bc\u01be\n\2\2\2"+
		"\u01bd\u01bc\3\2\2\2\u01be\u01c1\3\2\2\2\u01bf\u01bd\3\2\2\2\u01bf\u01c0"+
		"\3\2\2\2\u01c0\u01c2\3\2\2\2\u01c1\u01bf\3\2\2\2\u01c2\u01c3\b\63\4\2"+
		"\u01c3h\3\2\2\2\u01c4\u01c7\7G\2\2\u01c5\u01c8\5K%\2\u01c6\u01c8\5\u00ab"+
		"U\2\u01c7\u01c5\3\2\2\2\u01c7\u01c6\3\2\2\2\u01c7\u01c8\3\2\2\2\u01c8"+
		"\u01ca\3\2\2\2\u01c9\u01cb\5\u00afW\2\u01ca\u01c9\3\2\2\2\u01cb\u01cc"+
		"\3\2\2\2\u01cc\u01ca\3\2\2\2\u01cc\u01cd\3\2\2\2\u01cdj\3\2\2\2\u01ce"+
		"\u01cf\7v\2\2\u01cf\u01d0\7t\2\2\u01d0\u01d1\7w\2\2\u01d1\u01d8\7g\2\2"+
		"\u01d2\u01d3\7h\2\2\u01d3\u01d4\7c\2\2\u01d4\u01d5\7n\2\2\u01d5\u01d6"+
		"\7u\2\2\u01d6\u01d8\7g\2\2\u01d7\u01ce\3\2\2\2\u01d7\u01d2\3\2\2\2\u01d8"+
		"l\3\2\2\2\u01d9\u01db\5K%\2\u01da\u01d9\3\2\2\2\u01da\u01db\3\2\2\2\u01db"+
		"\u01dd\3\2\2\2\u01dc\u01de\5\u00afW\2\u01dd\u01dc\3\2\2\2\u01de\u01df"+
		"\3\2\2\2\u01df\u01dd\3\2\2\2\u01df\u01e0\3\2\2\2\u01e0n\3\2\2\2\u01e1"+
		"\u01e3\5\u00abU\2\u01e2\u01e4\5\u00afW\2\u01e3\u01e2\3\2\2\2\u01e4\u01e5"+
		"\3\2\2\2\u01e5\u01e3\3\2\2\2\u01e5\u01e6\3\2\2\2\u01e6p\3\2\2\2\u01e7"+
		"\u01ea\5K%\2\u01e8\u01ea\5\u00abU\2\u01e9\u01e7\3\2\2\2\u01e9\u01e8\3"+
		"\2\2\2\u01e9\u01ea\3\2\2\2\u01ea\u01ec\3\2\2\2\u01eb\u01ed\5\u00afW\2"+
		"\u01ec\u01eb\3\2\2\2\u01ed\u01ee\3\2\2\2\u01ee\u01ec\3\2\2\2\u01ee\u01ef"+
		"\3\2\2\2\u01ef\u01f0\3\2\2\2\u01f0\u01f2\5E\"\2\u01f1\u01f3\5\u00afW\2"+
		"\u01f2\u01f1\3\2\2\2\u01f3\u01f4\3\2\2\2\u01f4\u01f2\3\2\2\2\u01f4\u01f5"+
		"\3\2\2\2\u01f5r\3\2\2\2\u01f6\u01f7\7$\2\2\u01f7\u01f8\b9\5\2\u01f8\u01f9"+
		"\3\2\2\2\u01f9\u01fa\b9\6\2\u01fat\3\2\2\2\u01fb\u01fd\5\u00abU\2\u01fc"+
		"\u01fe\5\u00abU\2\u01fd\u01fc\3\2\2\2\u01fe\u01ff\3\2\2\2\u01ff\u01fd"+
		"\3\2\2\2\u01ff\u0200\3\2\2\2\u0200\u0201\3\2\2\2\u0201\u0202\b:\7\2\u0202"+
		"\u0203\3\2\2\2\u0203\u0204\b:\b\2\u0204v\3\2\2\2\u0205\u0207\5?\37\2\u0206"+
		"\u0208\5\u00b1X\2\u0207\u0206\3\2\2\2\u0208\u0209\3\2\2\2\u0209\u0207"+
		"\3\2\2\2\u0209\u020a\3\2\2\2\u020ax\3\2\2\2\u020b\u0212\5\u00b1X\2\u020c"+
		"\u0211\5\u00afW\2\u020d\u0211\5\u00b1X\2\u020e\u0211\5\u00abU\2\u020f"+
		"\u0211\5\u00adV\2\u0210\u020c\3\2\2\2\u0210\u020d\3\2\2\2\u0210\u020e"+
		"\3\2\2\2\u0210\u020f\3\2\2\2\u0211\u0214\3\2\2\2\u0212\u0210\3\2\2\2\u0212"+
		"\u0213\3\2\2\2\u0213z\3\2\2\2\u0214\u0212\3\2\2\2\u0215\u021b\5\u00b1"+
		"X\2\u0216\u021b\5\u00a3Q\2\u0217\u021b\5\u009fO\2\u0218\u021b\5\u00a1"+
		"P\2\u0219\u021b\5\u00a5R\2\u021a\u0215\3\2\2\2\u021a\u0216\3\2\2\2\u021a"+
		"\u0217\3\2\2\2\u021a\u0218\3\2\2\2\u021a\u0219\3\2\2\2\u021b\u0227\3\2"+
		"\2\2\u021c\u0226\5\u00adV\2\u021d\u0226\5\u00a7S\2\u021e\u0226\5\u00a9"+
		"T\2\u021f\u0226\5\u00a3Q\2\u0220\u0226\5\u009fO\2\u0221\u0226\5\u00a1"+
		"P\2\u0222\u0226\5\u00a5R\2\u0223\u0226\5\u00b1X\2\u0224\u0226\5\u00af"+
		"W\2\u0225\u021c\3\2\2\2\u0225\u021d\3\2\2\2\u0225\u021e\3\2\2\2\u0225"+
		"\u021f\3\2\2\2\u0225\u0220\3\2\2\2\u0225\u0221\3\2\2\2\u0225\u0222\3\2"+
		"\2\2\u0225\u0223\3\2\2\2\u0225\u0224\3\2\2\2\u0226\u0229\3\2\2\2\u0227"+
		"\u0225\3\2\2\2\u0227\u0228\3\2\2\2\u0228|\3\2\2\2\u0229\u0227\3\2\2\2"+
		"\u022a\u022c\5\u0085B\2\u022b\u022a\3\2\2\2\u022c\u022d\3\2\2\2\u022d"+
		"\u022b\3\2\2\2\u022d\u022e\3\2\2\2\u022e\u0232\3\2\2\2\u022f\u0231\5\u0083"+
		"A\2\u0230\u022f\3\2\2\2\u0231\u0234\3\2\2\2\u0232\u0230\3\2\2\2\u0232"+
		"\u0233\3\2\2\2\u0233\u0235\3\2\2\2\u0234\u0232\3\2\2\2\u0235\u0236\b>"+
		"\t\2\u0236~\3\2\2\2\u0237\u0239\5\u0083A\2\u0238\u0237\3\2\2\2\u0239\u023a"+
		"\3\2\2\2\u023a\u0238\3\2\2\2\u023a\u023b\3\2\2\2\u023b\u023d\3\2\2\2\u023c"+
		"\u023e\7\2\2\3\u023d\u023c\3\2\2\2\u023d\u023e\3\2\2\2\u023e\u023f\3\2"+
		"\2\2\u023f\u0240\b?\4\2\u0240\u0080\3\2\2\2\u0241\u0242\7f\2\2\u0242\u0243"+
		"\7q\2\2\u0243\u0244\7e\2\2\u0244\u0248\3\2\2\2\u0245\u0247\13\2\2\2\u0246"+
		"\u0245\3\2\2\2\u0247\u024a\3\2\2\2\u0248\u0249\3\2\2\2\u0248\u0246\3\2"+
		"\2\2\u0249\u024b\3\2\2\2\u024a\u0248\3\2\2\2\u024b\u024c\5\u0085B\2\u024c"+
		"\u024d\b@\n\2\u024d\u0082\3\2\2\2\u024e\u024f\t\3\2\2\u024f\u0084\3\2"+
		"\2\2\u0250\u0252\7\17\2\2\u0251\u0250\3\2\2\2\u0251\u0252\3\2\2\2\u0252"+
		"\u0253\3\2\2\2\u0253\u0256\7\f\2\2\u0254\u0256\7\17\2\2\u0255\u0251\3"+
		"\2\2\2\u0255\u0254\3\2\2\2\u0256\u0086\3\2\2\2\u0257\u0258\7k\2\2\u0258"+
		"\u0259\7p\2\2\u0259\u025a\7f\2\2\u025a\u025b\7g\2\2\u025b\u025c\7p\2\2"+
		"\u025c\u025d\7v\2\2\u025d\u0088\3\2\2\2\u025e\u025f\7f\2\2\u025f\u0260"+
		"\7g\2\2\u0260\u0261\7f\2\2\u0261\u0262\7g\2\2\u0262\u0263\7p\2\2\u0263"+
		"\u0264\7v\2\2\u0264\u008a\3\2\2\2\u0265\u0266\13\2\2\2\u0266\u008c\3\2"+
		"\2\2\u0267\u0269\5\u00abU\2\u0268\u026a\5\u00abU\2\u0269\u0268\3\2\2\2"+
		"\u026a\u026b\3\2\2\2\u026b\u0269\3\2\2\2\u026b\u026c\3\2\2\2\u026c\u026d"+
		"\3\2\2\2\u026d\u026e\bF\13\2\u026e\u026f\3\2\2\2\u026f\u0270\bF\f\2\u0270"+
		"\u008e\3\2\2\2\u0271\u0272\13\2\2\2\u0272\u0273\bG\r\2\u0273\u0090\3\2"+
		"\2\2\u0274\u0275\7$\2\2\u0275\u0276\bH\16\2\u0276\u0277\3\2\2\2\u0277"+
		"\u0278\bH\f\2\u0278\u0092\3\2\2\2\u0279\u027a\7$\2\2\u027a\u027b\bI\17"+
		"\2\u027b\u0094\3\2\2\2\u027c\u027d\7^\2\2\u027d\u027e\7$\2\2\u027e\u027f"+
		"\3\2\2\2\u027f\u0280\bJ\20\2\u0280\u0096\3\2\2\2\u0281\u0282\7^\2\2\u0282"+
		"\u0283\bK\21\2\u0283\u0098\3\2\2\2\u0284\u0285\13\2\2\2\u0285\u0286\b"+
		"L\22\2\u0286\u009a\3\2\2\2\u0287\u0288\7v\2\2\u0288\u0289\7\67\2\2\u0289"+
		"\u028a\7\65\2\2\u028a\u028b\78\2\2\u028b\u028c\7\66\2\2\u028c\u028d\7"+
		"9\2\2\u028d\u028e\78\2\2\u028e\u028f\7\67\2\2\u028f\u0290\78\2\2\u0290"+
		"\u0291\7g\2\2\u0291\u0292\7z\2\2\u0292\u0293\7v\2\2\u0293\u009c\3\2\2"+
		"\2\u0294\u0295\7v\2\2\u0295\u0296\7g\2\2\u0296\u0297\7\64\2\2\u0297\u0298"+
		"\7\66\2\2\u0298\u0299\7\67\2\2\u0299\u029a\78\2\2\u029a\u029b\7\67\2\2"+
		"\u029b\u029c\78\2\2\u029c\u029d\79\2\2\u029d\u029e\7:\2\2\u029e\u029f"+
		"\78\2\2\u029f\u02a0\7z\2\2\u02a0\u02a1\7v\2\2\u02a1\u009e\3\2\2\2\u02a2"+
		"\u02a3\7&\2\2\u02a3\u00a0\3\2\2\2\u02a4\u02a5\7\u20ae\2\2\u02a5\u00a2"+
		"\3\2\2\2\u02a6\u02a7\7\'\2\2\u02a7\u00a4\3\2\2\2\u02a8\u02a9\t\4\2\2\u02a9"+
		"\u00a6\3\2\2\2\u02aa\u02ab\7\u00b9\2\2\u02ab\u00a8\3\2\2\2\u02ac\u02ad"+
		"\7\61\2\2\u02ad\u00aa\3\2\2\2\u02ae\u02af\7/\2\2\u02af\u00ac\3\2\2\2\u02b0"+
		"\u02b1\7a\2\2\u02b1\u00ae\3\2\2\2\u02b2\u02b3\t\5\2\2\u02b3\u00b0\3\2"+
		"\2\2\u02b4\u02b5\t\6\2\2\u02b5\u00b2\3\2\2\2 \2\3\4\u0155\u01b1\u01bf"+
		"\u01c7\u01cc\u01d7\u01da\u01df\u01e5\u01e9\u01ee\u01f4\u01ff\u0209\u0210"+
		"\u0212\u021a\u0225\u0227\u022d\u0232\u023a\u023d\u0248\u0251\u0255\u026b"+
		"\23\3\35\2\3$\3\2\3\2\39\4\4\4\2\3:\5\4\3\2\3>\6\3@\7\3F\b\4\2\2\3G\t"+
		"\3H\n\3I\13\3J\f\3K\r\3L\16";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}