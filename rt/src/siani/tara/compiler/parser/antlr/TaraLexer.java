// Generated from /Users/oroncal/workspace/tara/rt/src/siani/tara/compiler/parser/antlr/TaraLexer.g4 by ANTLR 4.4.1-dev

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
	static { RuntimeMetaData.checkVersion("4.4.1-dev", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		METAIDENTIFIER=1, SUB=2, USE=3, BOX=4, VAR=5, AS=6, HAS=7, ON=8, IS=9, 
		WITH=10, EXTENDS=11, ABSTRACT=12, SINGLE=13, REQUIRED=14, COMPONENT=15, 
		FACET=16, INTENTION=17, TERMINAL=18, NAMED=19, PROPERTY=20, UNIVERSAL=21, 
		ALWAYS=22, ADDRESSED=23, AGGREGATED=24, COMPOSED=25, MULTIPLE=26, ROOT=27, 
		LEFT_PARENTHESIS=28, RIGHT_PARENTHESIS=29, LEFT_SQUARE=30, RIGHT_SQUARE=31, 
		LIST=32, INLINE=33, CLOSE_INLINE=34, AMPERSAND=35, COLON=36, COMMA=37, 
		DOT=38, EQUALS=39, APHOSTROPHE=40, SEMICOLON=41, STAR=42, POSITIVE=43, 
		WORD=44, RESOURCE=45, INT_TYPE=46, NATURAL_TYPE=47, DOUBLE_TYPE=48, STRING_TYPE=49, 
		BOOLEAN_TYPE=50, DATE_TYPE=51, EMPTY=52, BOOLEAN_VALUE=53, NATURAL_VALUE=54, 
		NEGATIVE_VALUE=55, DOUBLE_VALUE=56, STRING_VALUE=57, STRING_MULTILINE_VALUE_KEY=58, 
		ADDRESS_VALUE=59, DATE_VALUE=60, IDENTIFIER=61, MEASURE_VALUE=62, DIGIT=63, 
		LETTER=64, NEWLINE=65, SPACES=66, DOC=67, SP=68, NL=69, NEW_LINE_INDENT=70, 
		DEDENT=71, UNKNOWN_TOKEN=72;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'", "'\\u0004'", "'\\u0005'", 
		"'\\u0006'", "'\\u0007'", "'\b'", "'\t'", "'\n'", "'\\u000B'", "'\f'", 
		"'\r'", "'\\u000E'", "'\\u000F'", "'\\u0010'", "'\\u0011'", "'\\u0012'", 
		"'\\u0013'", "'\\u0014'", "'\\u0015'", "'\\u0016'", "'\\u0017'", "'\\u0018'", 
		"'\\u0019'", "'\\u001A'", "'\\u001B'", "'\\u001C'", "'\\u001D'", "'\\u001E'", 
		"'\\u001F'", "' '", "'!'", "'\"'", "'#'", "'$'", "'%'", "'&'", "'''", 
		"'('", "')'", "'*'", "'+'", "','", "'-'", "'.'", "'/'", "'0'", "'1'", 
		"'2'", "'3'", "'4'", "'5'", "'6'", "'7'", "'8'", "'9'", "':'", "';'", 
		"'<'", "'='", "'>'", "'?'", "'@'", "'A'", "'B'", "'C'", "'D'", "'E'", 
		"'F'", "'G'", "'H'"
	};
	public static final String[] ruleNames = {
		"METAIDENTIFIER", "SUB", "USE", "BOX", "VAR", "AS", "HAS", "ON", "IS", 
		"WITH", "EXTENDS", "ABSTRACT", "SINGLE", "REQUIRED", "COMPONENT", "FACET", 
		"INTENTION", "TERMINAL", "NAMED", "PROPERTY", "UNIVERSAL", "ALWAYS", "ADDRESSED", 
		"AGGREGATED", "COMPOSED", "MULTIPLE", "ROOT", "LEFT_PARENTHESIS", "RIGHT_PARENTHESIS", 
		"LEFT_SQUARE", "RIGHT_SQUARE", "LIST", "INLINE", "CLOSE_INLINE", "AMPERSAND", 
		"COLON", "COMMA", "DOT", "EQUALS", "APHOSTROPHE", "SEMICOLON", "STAR", 
		"POSITIVE", "WORD", "RESOURCE", "INT_TYPE", "NATURAL_TYPE", "DOUBLE_TYPE", 
		"STRING_TYPE", "BOOLEAN_TYPE", "DATE_TYPE", "EMPTY", "BOOLEAN_VALUE", 
		"NATURAL_VALUE", "NEGATIVE_VALUE", "DOUBLE_VALUE", "STRING_VALUE", "STRING_MULTILINE_VALUE_KEY", 
		"ADDRESS_VALUE", "DATE_VALUE", "IDENTIFIER", "MEASURE_VALUE", "DIGIT", 
		"LETTER", "NEWLINE", "SPACES", "DOC", "SP", "NL", "NEW_LINE_INDENT", "DEDENT", 
		"UNKNOWN_TOKEN", "DOLLAR", "EURO", "PERCENTAGE", "GRADE", "BY", "DIVIDED_BY", 
		"DASH", "DASHES", "UNDERDASH"
	};


	    BlockManager blockManager = new BlockManager();
	    private static java.util.Queue<Token> queue = new java.util.LinkedList<>();

	    @Override
	    public void reset(){
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
	public String[] getTokenNames() { return tokenNames; }

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
		case 32: INLINE_action((RuleContext)_localctx, actionIndex); break;
		case 40: SEMICOLON_action((RuleContext)_localctx, actionIndex); break;
		case 64: NEWLINE_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void SEMICOLON_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1:  semicolon();  break;
		}
	}
	private void INLINE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:  inline();  break;
		}
	}
	private void NEWLINE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 2:  newlinesAndSpaces();  break;
		}
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2J\u0283\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
		"\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\4O\tO\4P\tP\4Q\tQ\4R\tR\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3"+
		"\6\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\13"+
		"\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3"+
		"\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3"+
		"\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3"+
		"\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3"+
		"\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3"+
		"\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3"+
		"\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3"+
		"\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3"+
		"\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3"+
		"\34\3\34\3\34\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3!\3"+
		"!\3\"\3\"\3\"\3#\3#\3$\3$\3%\3%\3&\3&\3\'\3\'\3(\3(\3)\3)\3*\6*\u017e"+
		"\n*\r*\16*\u017f\3*\3*\3+\3+\3,\3,\3-\3-\3-\3-\3-\3.\3.\3.\3.\3.\3.\3"+
		".\3.\3.\3/\3/\3/\3/\3/\3/\3/\3/\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60"+
		"\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\62\3\62\3\62\3\62\3\62\3\62\3\62"+
		"\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\64\3\64\3\64\3\64\3\64\3\65"+
		"\3\65\3\65\3\65\3\65\3\65\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66"+
		"\5\66\u01d0\n\66\3\67\5\67\u01d3\n\67\3\67\6\67\u01d6\n\67\r\67\16\67"+
		"\u01d7\38\38\68\u01dc\n8\r8\168\u01dd\39\39\59\u01e2\n9\39\69\u01e5\n"+
		"9\r9\169\u01e6\39\39\69\u01eb\n9\r9\169\u01ec\3:\3:\7:\u01f1\n:\f:\16"+
		":\u01f4\13:\3:\3:\3;\3;\7;\u01fa\n;\f;\16;\u01fd\13;\3;\3;\3<\3<\3<\3"+
		"<\3<\3<\3<\3<\3<\6<\u020a\n<\r<\16<\u020b\3=\3=\3=\6=\u0211\n=\r=\16="+
		"\u0212\3=\3=\3=\5=\u0218\n=\3>\3>\3>\3>\3>\7>\u021f\n>\f>\16>\u0222\13"+
		">\3?\3?\3?\3?\3?\3?\3?\3?\3?\6?\u022d\n?\r?\16?\u022e\3@\3@\3A\3A\3B\6"+
		"B\u0236\nB\rB\16B\u0237\3B\7B\u023b\nB\fB\16B\u023e\13B\3B\3B\3C\6C\u0243"+
		"\nC\rC\16C\u0244\3C\5C\u0248\nC\3C\3C\3D\3D\7D\u024e\nD\fD\16D\u0251\13"+
		"D\3D\3D\3E\3E\3F\5F\u0258\nF\3F\3F\5F\u025c\nF\3G\3G\3G\3G\3G\3G\3G\3"+
		"H\3H\3H\3H\3H\3H\3H\3I\3I\3J\3J\3K\3K\3L\3L\3M\3M\3N\3N\3O\3O\3P\3P\3"+
		"Q\3Q\6Q\u027e\nQ\rQ\16Q\u027f\3R\3R\3\u024f\2S\3\3\5\4\7\5\t\6\13\7\r"+
		"\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25"+
		")\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O"+
		")Q*S+U,W-Y.[/]\60_\61a\62c\63e\64g\65i\66k\67m8o9q:s;u<w=y>{?}@\177A\u0081"+
		"B\u0083C\u0085D\u0087E\u0089F\u008bG\u008dH\u008fI\u0091J\u0093\2\u0095"+
		"\2\u0097\2\u0099\2\u009b\2\u009d\2\u009f\2\u00a1\2\u00a3\2\3\2\7\3\2)"+
		")\3\2//\3\2\62;\6\2C\\c|\u00d3\u00d3\u00f3\u00f3\4\2\13\13\"\"\u029c\2"+
		"\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2"+
		"\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2"+
		"\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2"+
		"\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2"+
		"\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2"+
		"\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2"+
		"\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U"+
		"\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2"+
		"\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2"+
		"\2o\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\2u\3\2\2\2\2w\3\2\2\2\2y\3\2\2\2\2{"+
		"\3\2\2\2\2}\3\2\2\2\2\177\3\2\2\2\2\u0081\3\2\2\2\2\u0083\3\2\2\2\2\u0085"+
		"\3\2\2\2\2\u0087\3\2\2\2\2\u0089\3\2\2\2\2\u008b\3\2\2\2\2\u008d\3\2\2"+
		"\2\2\u008f\3\2\2\2\2\u0091\3\2\2\2\3\u00a5\3\2\2\2\5\u00ad\3\2\2\2\7\u00b1"+
		"\3\2\2\2\t\u00b5\3\2\2\2\13\u00b9\3\2\2\2\r\u00bd\3\2\2\2\17\u00c0\3\2"+
		"\2\2\21\u00c4\3\2\2\2\23\u00c7\3\2\2\2\25\u00ca\3\2\2\2\27\u00cf\3\2\2"+
		"\2\31\u00d7\3\2\2\2\33\u00e0\3\2\2\2\35\u00e7\3\2\2\2\37\u00f0\3\2\2\2"+
		"!\u00fa\3\2\2\2#\u0100\3\2\2\2%\u010a\3\2\2\2\'\u0113\3\2\2\2)\u0119\3"+
		"\2\2\2+\u0122\3\2\2\2-\u012c\3\2\2\2/\u0133\3\2\2\2\61\u013d\3\2\2\2\63"+
		"\u0148\3\2\2\2\65\u0151\3\2\2\2\67\u015a\3\2\2\29\u015f\3\2\2\2;\u0161"+
		"\3\2\2\2=\u0163\3\2\2\2?\u0165\3\2\2\2A\u0167\3\2\2\2C\u016b\3\2\2\2E"+
		"\u016e\3\2\2\2G\u0170\3\2\2\2I\u0172\3\2\2\2K\u0174\3\2\2\2M\u0176\3\2"+
		"\2\2O\u0178\3\2\2\2Q\u017a\3\2\2\2S\u017d\3\2\2\2U\u0183\3\2\2\2W\u0185"+
		"\3\2\2\2Y\u0187\3\2\2\2[\u018c\3\2\2\2]\u0195\3\2\2\2_\u019d\3\2\2\2a"+
		"\u01a5\3\2\2\2c\u01ac\3\2\2\2e\u01b3\3\2\2\2g\u01bb\3\2\2\2i\u01c0\3\2"+
		"\2\2k\u01cf\3\2\2\2m\u01d2\3\2\2\2o\u01d9\3\2\2\2q\u01e1\3\2\2\2s\u01ee"+
		"\3\2\2\2u\u01f7\3\2\2\2w\u0200\3\2\2\2y\u0217\3\2\2\2{\u0219\3\2\2\2}"+
		"\u022c\3\2\2\2\177\u0230\3\2\2\2\u0081\u0232\3\2\2\2\u0083\u0235\3\2\2"+
		"\2\u0085\u0242\3\2\2\2\u0087\u024b\3\2\2\2\u0089\u0254\3\2\2\2\u008b\u025b"+
		"\3\2\2\2\u008d\u025d\3\2\2\2\u008f\u0264\3\2\2\2\u0091\u026b\3\2\2\2\u0093"+
		"\u026d\3\2\2\2\u0095\u026f\3\2\2\2\u0097\u0271\3\2\2\2\u0099\u0273\3\2"+
		"\2\2\u009b\u0275\3\2\2\2\u009d\u0277\3\2\2\2\u009f\u0279\3\2\2\2\u00a1"+
		"\u027b\3\2\2\2\u00a3\u0281\3\2\2\2\u00a5\u00a6\7E\2\2\u00a6\u00a7\7q\2"+
		"\2\u00a7\u00a8\7p\2\2\u00a8\u00a9\7e\2\2\u00a9\u00aa\7g\2\2\u00aa\u00ab"+
		"\7r\2\2\u00ab\u00ac\7v\2\2\u00ac\4\3\2\2\2\u00ad\u00ae\7u\2\2\u00ae\u00af"+
		"\7w\2\2\u00af\u00b0\7d\2\2\u00b0\6\3\2\2\2\u00b1\u00b2\7w\2\2\u00b2\u00b3"+
		"\7u\2\2\u00b3\u00b4\7g\2\2\u00b4\b\3\2\2\2\u00b5\u00b6\7d\2\2\u00b6\u00b7"+
		"\7q\2\2\u00b7\u00b8\7z\2\2\u00b8\n\3\2\2\2\u00b9\u00ba\7x\2\2\u00ba\u00bb"+
		"\7c\2\2\u00bb\u00bc\7t\2\2\u00bc\f\3\2\2\2\u00bd\u00be\7c\2\2\u00be\u00bf"+
		"\7u\2\2\u00bf\16\3\2\2\2\u00c0\u00c1\7j\2\2\u00c1\u00c2\7c\2\2\u00c2\u00c3"+
		"\7u\2\2\u00c3\20\3\2\2\2\u00c4\u00c5\7q\2\2\u00c5\u00c6\7p\2\2\u00c6\22"+
		"\3\2\2\2\u00c7\u00c8\7k\2\2\u00c8\u00c9\7u\2\2\u00c9\24\3\2\2\2\u00ca"+
		"\u00cb\7y\2\2\u00cb\u00cc\7k\2\2\u00cc\u00cd\7v\2\2\u00cd\u00ce\7j\2\2"+
		"\u00ce\26\3\2\2\2\u00cf\u00d0\7g\2\2\u00d0\u00d1\7z\2\2\u00d1\u00d2\7"+
		"v\2\2\u00d2\u00d3\7g\2\2\u00d3\u00d4\7p\2\2\u00d4\u00d5\7f\2\2\u00d5\u00d6"+
		"\7u\2\2\u00d6\30\3\2\2\2\u00d7\u00d8\7c\2\2\u00d8\u00d9\7d\2\2\u00d9\u00da"+
		"\7u\2\2\u00da\u00db\7v\2\2\u00db\u00dc\7t\2\2\u00dc\u00dd\7c\2\2\u00dd"+
		"\u00de\7e\2\2\u00de\u00df\7v\2\2\u00df\32\3\2\2\2\u00e0\u00e1\7u\2\2\u00e1"+
		"\u00e2\7k\2\2\u00e2\u00e3\7p\2\2\u00e3\u00e4\7i\2\2\u00e4\u00e5\7n\2\2"+
		"\u00e5\u00e6\7g\2\2\u00e6\34\3\2\2\2\u00e7\u00e8\7t\2\2\u00e8\u00e9\7"+
		"g\2\2\u00e9\u00ea\7s\2\2\u00ea\u00eb\7w\2\2\u00eb\u00ec\7k\2\2\u00ec\u00ed"+
		"\7t\2\2\u00ed\u00ee\7g\2\2\u00ee\u00ef\7f\2\2\u00ef\36\3\2\2\2\u00f0\u00f1"+
		"\7e\2\2\u00f1\u00f2\7q\2\2\u00f2\u00f3\7o\2\2\u00f3\u00f4\7r\2\2\u00f4"+
		"\u00f5\7q\2\2\u00f5\u00f6\7p\2\2\u00f6\u00f7\7g\2\2\u00f7\u00f8\7p\2\2"+
		"\u00f8\u00f9\7v\2\2\u00f9 \3\2\2\2\u00fa\u00fb\7h\2\2\u00fb\u00fc\7c\2"+
		"\2\u00fc\u00fd\7e\2\2\u00fd\u00fe\7g\2\2\u00fe\u00ff\7v\2\2\u00ff\"\3"+
		"\2\2\2\u0100\u0101\7k\2\2\u0101\u0102\7p\2\2\u0102\u0103\7v\2\2\u0103"+
		"\u0104\7g\2\2\u0104\u0105\7p\2\2\u0105\u0106\7v\2\2\u0106\u0107\7k\2\2"+
		"\u0107\u0108\7q\2\2\u0108\u0109\7p\2\2\u0109$\3\2\2\2\u010a\u010b\7v\2"+
		"\2\u010b\u010c\7g\2\2\u010c\u010d\7t\2\2\u010d\u010e\7o\2\2\u010e\u010f"+
		"\7k\2\2\u010f\u0110\7p\2\2\u0110\u0111\7c\2\2\u0111\u0112\7n\2\2\u0112"+
		"&\3\2\2\2\u0113\u0114\7p\2\2\u0114\u0115\7c\2\2\u0115\u0116\7o\2\2\u0116"+
		"\u0117\7g\2\2\u0117\u0118\7f\2\2\u0118(\3\2\2\2\u0119\u011a\7r\2\2\u011a"+
		"\u011b\7t\2\2\u011b\u011c\7q\2\2\u011c\u011d\7r\2\2\u011d\u011e\7g\2\2"+
		"\u011e\u011f\7t\2\2\u011f\u0120\7v\2\2\u0120\u0121\7{\2\2\u0121*\3\2\2"+
		"\2\u0122\u0123\7w\2\2\u0123\u0124\7p\2\2\u0124\u0125\7k\2\2\u0125\u0126"+
		"\7x\2\2\u0126\u0127\7g\2\2\u0127\u0128\7t\2\2\u0128\u0129\7u\2\2\u0129"+
		"\u012a\7c\2\2\u012a\u012b\7n\2\2\u012b,\3\2\2\2\u012c\u012d\7c\2\2\u012d"+
		"\u012e\7n\2\2\u012e\u012f\7y\2\2\u012f\u0130\7c\2\2\u0130\u0131\7{\2\2"+
		"\u0131\u0132\7u\2\2\u0132.\3\2\2\2\u0133\u0134\7c\2\2\u0134\u0135\7f\2"+
		"\2\u0135\u0136\7f\2\2\u0136\u0137\7t\2\2\u0137\u0138\7g\2\2\u0138\u0139"+
		"\7u\2\2\u0139\u013a\7u\2\2\u013a\u013b\7g\2\2\u013b\u013c\7f\2\2\u013c"+
		"\60\3\2\2\2\u013d\u013e\7c\2\2\u013e\u013f\7i\2\2\u013f\u0140\7i\2\2\u0140"+
		"\u0141\7t\2\2\u0141\u0142\7g\2\2\u0142\u0143\7i\2\2\u0143\u0144\7c\2\2"+
		"\u0144\u0145\7v\2\2\u0145\u0146\7g\2\2\u0146\u0147\7f\2\2\u0147\62\3\2"+
		"\2\2\u0148\u0149\7e\2\2\u0149\u014a\7q\2\2\u014a\u014b\7o\2\2\u014b\u014c"+
		"\7r\2\2\u014c\u014d\7q\2\2\u014d\u014e\7u\2\2\u014e\u014f\7g\2\2\u014f"+
		"\u0150\7f\2\2\u0150\64\3\2\2\2\u0151\u0152\7o\2\2\u0152\u0153\7w\2\2\u0153"+
		"\u0154\7n\2\2\u0154\u0155\7v\2\2\u0155\u0156\7k\2\2\u0156\u0157\7r\2\2"+
		"\u0157\u0158\7n\2\2\u0158\u0159\7g\2\2\u0159\66\3\2\2\2\u015a\u015b\7"+
		"t\2\2\u015b\u015c\7q\2\2\u015c\u015d\7q\2\2\u015d\u015e\7v\2\2\u015e8"+
		"\3\2\2\2\u015f\u0160\7*\2\2\u0160:\3\2\2\2\u0161\u0162\7+\2\2\u0162<\3"+
		"\2\2\2\u0163\u0164\7]\2\2\u0164>\3\2\2\2\u0165\u0166\7_\2\2\u0166@\3\2"+
		"\2\2\u0167\u0168\7\60\2\2\u0168\u0169\7\60\2\2\u0169\u016a\7\60\2\2\u016a"+
		"B\3\2\2\2\u016b\u016c\7@\2\2\u016c\u016d\b\"\2\2\u016dD\3\2\2\2\u016e"+
		"\u016f\7>\2\2\u016fF\3\2\2\2\u0170\u0171\7(\2\2\u0171H\3\2\2\2\u0172\u0173"+
		"\7<\2\2\u0173J\3\2\2\2\u0174\u0175\7.\2\2\u0175L\3\2\2\2\u0176\u0177\7"+
		"\60\2\2\u0177N\3\2\2\2\u0178\u0179\7?\2\2\u0179P\3\2\2\2\u017a\u017b\7"+
		")\2\2\u017bR\3\2\2\2\u017c\u017e\7=\2\2\u017d\u017c\3\2\2\2\u017e\u017f"+
		"\3\2\2\2\u017f\u017d\3\2\2\2\u017f\u0180\3\2\2\2\u0180\u0181\3\2\2\2\u0181"+
		"\u0182\b*\3\2\u0182T\3\2\2\2\u0183\u0184\7,\2\2\u0184V\3\2\2\2\u0185\u0186"+
		"\7-\2\2\u0186X\3\2\2\2\u0187\u0188\7y\2\2\u0188\u0189\7q\2\2\u0189\u018a"+
		"\7t\2\2\u018a\u018b\7f\2\2\u018bZ\3\2\2\2\u018c\u018d\7t\2\2\u018d\u018e"+
		"\7g\2\2\u018e\u018f\7u\2\2\u018f\u0190\7q\2\2\u0190\u0191\7w\2\2\u0191"+
		"\u0192\7t\2\2\u0192\u0193\7e\2\2\u0193\u0194\7g\2\2\u0194\\\3\2\2\2\u0195"+
		"\u0196\7k\2\2\u0196\u0197\7p\2\2\u0197\u0198\7v\2\2\u0198\u0199\7g\2\2"+
		"\u0199\u019a\7i\2\2\u019a\u019b\7g\2\2\u019b\u019c\7t\2\2\u019c^\3\2\2"+
		"\2\u019d\u019e\7p\2\2\u019e\u019f\7c\2\2\u019f\u01a0\7v\2\2\u01a0\u01a1"+
		"\7w\2\2\u01a1\u01a2\7t\2\2\u01a2\u01a3\7c\2\2\u01a3\u01a4\7n\2\2\u01a4"+
		"`\3\2\2\2\u01a5\u01a6\7f\2\2\u01a6\u01a7\7q\2\2\u01a7\u01a8\7w\2\2\u01a8"+
		"\u01a9\7d\2\2\u01a9\u01aa\7n\2\2\u01aa\u01ab\7g\2\2\u01abb\3\2\2\2\u01ac"+
		"\u01ad\7u\2\2\u01ad\u01ae\7v\2\2\u01ae\u01af\7t\2\2\u01af\u01b0\7k\2\2"+
		"\u01b0\u01b1\7p\2\2\u01b1\u01b2\7i\2\2\u01b2d\3\2\2\2\u01b3\u01b4\7d\2"+
		"\2\u01b4\u01b5\7q\2\2\u01b5\u01b6\7q\2\2\u01b6\u01b7\7n\2\2\u01b7\u01b8"+
		"\7g\2\2\u01b8\u01b9\7c\2\2\u01b9\u01ba\7p\2\2\u01baf\3\2\2\2\u01bb\u01bc"+
		"\7f\2\2\u01bc\u01bd\7c\2\2\u01bd\u01be\7v\2\2\u01be\u01bf\7g\2\2\u01bf"+
		"h\3\2\2\2\u01c0\u01c1\7g\2\2\u01c1\u01c2\7o\2\2\u01c2\u01c3\7r\2\2\u01c3"+
		"\u01c4\7v\2\2\u01c4\u01c5\7{\2\2\u01c5j\3\2\2\2\u01c6\u01c7\7v\2\2\u01c7"+
		"\u01c8\7t\2\2\u01c8\u01c9\7w\2\2\u01c9\u01d0\7g\2\2\u01ca\u01cb\7h\2\2"+
		"\u01cb\u01cc\7c\2\2\u01cc\u01cd\7n\2\2\u01cd\u01ce\7u\2\2\u01ce\u01d0"+
		"\7g\2\2\u01cf\u01c6\3\2\2\2\u01cf\u01ca\3\2\2\2\u01d0l\3\2\2\2\u01d1\u01d3"+
		"\5W,\2\u01d2\u01d1\3\2\2\2\u01d2\u01d3\3\2\2\2\u01d3\u01d5\3\2\2\2\u01d4"+
		"\u01d6\5\177@\2\u01d5\u01d4\3\2\2\2\u01d6\u01d7\3\2\2\2\u01d7\u01d5\3"+
		"\2\2\2\u01d7\u01d8\3\2\2\2\u01d8n\3\2\2\2\u01d9\u01db\5\u009fP\2\u01da"+
		"\u01dc\5\177@\2\u01db\u01da\3\2\2\2\u01dc\u01dd\3\2\2\2\u01dd\u01db\3"+
		"\2\2\2\u01dd\u01de\3\2\2\2\u01dep\3\2\2\2\u01df\u01e2\5W,\2\u01e0\u01e2"+
		"\5\u009fP\2\u01e1\u01df\3\2\2\2\u01e1\u01e0\3\2\2\2\u01e1\u01e2\3\2\2"+
		"\2\u01e2\u01e4\3\2\2\2\u01e3\u01e5\5\177@\2\u01e4\u01e3\3\2\2\2\u01e5"+
		"\u01e6\3\2\2\2\u01e6\u01e4\3\2\2\2\u01e6\u01e7\3\2\2\2\u01e7\u01e8\3\2"+
		"\2\2\u01e8\u01ea\5M\'\2\u01e9\u01eb\5\177@\2\u01ea\u01e9\3\2\2\2\u01eb"+
		"\u01ec\3\2\2\2\u01ec\u01ea\3\2\2\2\u01ec\u01ed\3\2\2\2\u01edr\3\2\2\2"+
		"\u01ee\u01f2\5Q)\2\u01ef\u01f1\n\2\2\2\u01f0\u01ef\3\2\2\2\u01f1\u01f4"+
		"\3\2\2\2\u01f2\u01f0\3\2\2\2\u01f2\u01f3\3\2\2\2\u01f3\u01f5\3\2\2\2\u01f4"+
		"\u01f2\3\2\2\2\u01f5\u01f6\5Q)\2\u01f6t\3\2\2\2\u01f7\u01fb\5\u00a1Q\2"+
		"\u01f8\u01fa\n\3\2\2\u01f9\u01f8\3\2\2\2\u01fa\u01fd\3\2\2\2\u01fb\u01f9"+
		"\3\2\2\2\u01fb\u01fc\3\2\2\2\u01fc\u01fe\3\2\2\2\u01fd\u01fb\3\2\2\2\u01fe"+
		"\u01ff\5\u00a1Q\2\u01ffv\3\2\2\2\u0200\u0201\5G$\2\u0201\u0202\5\177@"+
		"\2\u0202\u0203\5\177@\2\u0203\u0209\5\177@\2\u0204\u0205\5M\'\2\u0205"+
		"\u0206\5\177@\2\u0206\u0207\5\177@\2\u0207\u0208\5\177@\2\u0208\u020a"+
		"\3\2\2\2\u0209\u0204\3\2\2\2\u020a\u020b\3\2\2\2\u020b\u0209\3\2\2\2\u020b"+
		"\u020c\3\2\2\2\u020cx\3\2\2\2\u020d\u020e\5m\67\2\u020e\u020f\5\u009f"+
		"P\2\u020f\u0211\3\2\2\2\u0210\u020d\3\2\2\2\u0211\u0212\3\2\2\2\u0212"+
		"\u0210\3\2\2\2\u0212\u0213\3\2\2\2\u0213\u0214\3\2\2\2\u0214\u0215\5m"+
		"\67\2\u0215\u0218\3\2\2\2\u0216\u0218\5m\67\2\u0217\u0210\3\2\2\2\u0217"+
		"\u0216\3\2\2\2\u0218z\3\2\2\2\u0219\u0220\5\u0081A\2\u021a\u021f\5\177"+
		"@\2\u021b\u021f\5\u0081A\2\u021c\u021f\5\u009fP\2\u021d\u021f\5\u00a3"+
		"R\2\u021e\u021a\3\2\2\2\u021e\u021b\3\2\2\2\u021e\u021c\3\2\2\2\u021e"+
		"\u021d\3\2\2\2\u021f\u0222\3\2\2\2\u0220\u021e\3\2\2\2\u0220\u0221\3\2"+
		"\2\2\u0221|\3\2\2\2\u0222\u0220\3\2\2\2\u0223\u022d\5\u00a3R\2\u0224\u022d"+
		"\5\u009bN\2\u0225\u022d\5\u009dO\2\u0226\u022d\5\u0097L\2\u0227\u022d"+
		"\5\u0093J\2\u0228\u022d\5\u0095K\2\u0229\u022d\5\u0099M\2\u022a\u022d"+
		"\5\u0081A\2\u022b\u022d\5\177@\2\u022c\u0223\3\2\2\2\u022c\u0224\3\2\2"+
		"\2\u022c\u0225\3\2\2\2\u022c\u0226\3\2\2\2\u022c\u0227\3\2\2\2\u022c\u0228"+
		"\3\2\2\2\u022c\u0229\3\2\2\2\u022c\u022a\3\2\2\2\u022c\u022b\3\2\2\2\u022d"+
		"\u022e\3\2\2\2\u022e\u022c\3\2\2\2\u022e\u022f\3\2\2\2\u022f~\3\2\2\2"+
		"\u0230\u0231\t\4\2\2\u0231\u0080\3\2\2\2\u0232\u0233\t\5\2\2\u0233\u0082"+
		"\3\2\2\2\u0234\u0236\5\u008bF\2\u0235\u0234\3\2\2\2\u0236\u0237\3\2\2"+
		"\2\u0237\u0235\3\2\2\2\u0237\u0238\3\2\2\2\u0238\u023c\3\2\2\2\u0239\u023b"+
		"\5\u0089E\2\u023a\u0239\3\2\2\2\u023b\u023e\3\2\2\2\u023c\u023a\3\2\2"+
		"\2\u023c\u023d\3\2\2\2\u023d\u023f\3\2\2\2\u023e\u023c\3\2\2\2\u023f\u0240"+
		"\bB\4\2\u0240\u0084\3\2\2\2\u0241\u0243\5\u0089E\2\u0242\u0241\3\2\2\2"+
		"\u0243\u0244\3\2\2\2\u0244\u0242\3\2\2\2\u0244\u0245\3\2\2\2\u0245\u0247"+
		"\3\2\2\2\u0246\u0248\7\2\2\3\u0247\u0246\3\2\2\2\u0247\u0248\3\2\2\2\u0248"+
		"\u0249\3\2\2\2\u0249\u024a\bC\5\2\u024a\u0086\3\2\2\2\u024b\u024f\7%\2"+
		"\2\u024c\u024e\13\2\2\2\u024d\u024c\3\2\2\2\u024e\u0251\3\2\2\2\u024f"+
		"\u0250\3\2\2\2\u024f\u024d\3\2\2\2\u0250\u0252\3\2\2\2\u0251\u024f\3\2"+
		"\2\2\u0252\u0253\5\u008bF\2\u0253\u0088\3\2\2\2\u0254\u0255\t\6\2\2\u0255"+
		"\u008a\3\2\2\2\u0256\u0258\7\17\2\2\u0257\u0256\3\2\2\2\u0257\u0258\3"+
		"\2\2\2\u0258\u0259\3\2\2\2\u0259\u025c\7\f\2\2\u025a\u025c\7\17\2\2\u025b"+
		"\u0257\3\2\2\2\u025b\u025a\3\2\2\2\u025c\u008c\3\2\2\2\u025d\u025e\7k"+
		"\2\2\u025e\u025f\7p\2\2\u025f\u0260\7f\2\2\u0260\u0261\7g\2\2\u0261\u0262"+
		"\7p\2\2\u0262\u0263\7v\2\2\u0263\u008e\3\2\2\2\u0264\u0265\7f\2\2\u0265"+
		"\u0266\7g\2\2\u0266\u0267\7f\2\2\u0267\u0268\7g\2\2\u0268\u0269\7p\2\2"+
		"\u0269\u026a\7v\2\2\u026a\u0090\3\2\2\2\u026b\u026c\13\2\2\2\u026c\u0092"+
		"\3\2\2\2\u026d\u026e\7&\2\2\u026e\u0094\3\2\2\2\u026f\u0270\7\u20ae\2"+
		"\2\u0270\u0096\3\2\2\2\u0271\u0272\7\'\2\2\u0272\u0098\3\2\2\2\u0273\u0274"+
		"\7\u00bc\2\2\u0274\u009a\3\2\2\2\u0275\u0276\7\u00b9\2\2\u0276\u009c\3"+
		"\2\2\2\u0277\u0278\7\61\2\2\u0278\u009e\3\2\2\2\u0279\u027a\7/\2\2\u027a"+
		"\u00a0\3\2\2\2\u027b\u027d\5\u009fP\2\u027c\u027e\5\u009fP\2\u027d\u027c"+
		"\3\2\2\2\u027e\u027f\3\2\2\2\u027f\u027d\3\2\2\2\u027f\u0280\3\2\2\2\u0280"+
		"\u00a2\3\2\2\2\u0281\u0282\7a\2\2\u0282\u00a4\3\2\2\2\34\2\u017f\u01cf"+
		"\u01d2\u01d7\u01dd\u01e1\u01e6\u01ec\u01f2\u01fb\u020b\u0212\u0217\u021e"+
		"\u0220\u022c\u022e\u0237\u023c\u0244\u0247\u024f\u0257\u025b\u027f\6\3"+
		"\"\2\3*\3\3B\4\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}