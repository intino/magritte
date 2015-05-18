// Generated from /Users/oroncal/workspace/tara/rt/src/siani/tara/compiler/parser/antlr/TaraGrammar.g4 by ANTLR 4.5
package siani.tara.compiler.parser.antlr;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TaraGrammar extends Parser {
	static { RuntimeMetaData.checkVersion("4.5", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		METAIDENTIFIER=1, SUB=2, USE=3, DSL=4, VAR=5, AS=6, HAS=7, ON=8, IS=9, 
		INTO=10, WITH=11, EXTENDS=12, ABSTRACT=13, SINGLE=14, MULTIPLE=15, REQUIRED=16, 
		TERMINAL=17, ROOT=18, PROPERTY=19, FEATURE=20, READONLY=21, ENCLOSED=22, 
		FACET=23, LEFT_PARENTHESIS=24, RIGHT_PARENTHESIS=25, LEFT_SQUARE=26, RIGHT_SQUARE=27, 
		LIST=28, INLINE=29, CLOSE_INLINE=30, HASHTAG=31, COLON=32, COMMA=33, DOT=34, 
		EQUALS=35, SEMICOLON=36, STAR=37, PLUS=38, WORD=39, RESOURCE=40, INT_TYPE=41, 
		NATURAL_TYPE=42, NATIVE_TYPE=43, DOUBLE_TYPE=44, STRING_TYPE=45, BOOLEAN_TYPE=46, 
		MEASURE_TYPE=47, RATIO_TYPE=48, DATE_TYPE=49, EMPTY=50, BLOCK_COMMENT=51, 
		LINE_COMMENT=52, SCIENCE_NOT=53, BOOLEAN_VALUE=54, NATURAL_VALUE=55, NEGATIVE_VALUE=56, 
		DOUBLE_VALUE=57, APHOSTROPHE=58, STRING_MULTILINE=59, PLATE_VALUE=60, 
		IDENTIFIER=61, MEASURE_VALUE=62, NEWLINE=63, SPACES=64, DOC=65, SP=66, 
		NL=67, NEW_LINE_INDENT=68, DEDENT=69, UNKNOWN_TOKEN=70, M_STRING_MULTILINE=71, 
		M_CHARACTER=72, QUOTE=73, Q=74, SLASH_Q=75, SLASH=76, CHARACTER=77, QUOTE_END=78, 
		QUOTE_BEGIN=79;
	public static final int
		RULE_root = 0, RULE_dslDeclaration = 1, RULE_imports = 2, RULE_anImport = 3, 
		RULE_doc = 4, RULE_node = 5, RULE_signature = 6, RULE_parent = 7, RULE_parameters = 8, 
		RULE_explicitParameter = 9, RULE_implicitParameter = 10, RULE_value = 11, 
		RULE_body = 12, RULE_facetApply = 13, RULE_facetTarget = 14, RULE_nodeReference = 15, 
		RULE_variable = 16, RULE_variableType = 17, RULE_nativeName = 18, RULE_count = 19, 
		RULE_word = 20, RULE_wordValue = 21, RULE_stringValue = 22, RULE_booleanValue = 23, 
		RULE_naturalValue = 24, RULE_integerValue = 25, RULE_doubleValue = 26, 
		RULE_linkValue = 27, RULE_plate = 28, RULE_measureValue = 29, RULE_tags = 30, 
		RULE_annotations = 31, RULE_annotation = 32, RULE_flags = 33, RULE_flag = 34, 
		RULE_varInit = 35, RULE_headerReference = 36, RULE_identifierReference = 37, 
		RULE_hierarchy = 38, RULE_metaidentifier = 39;
	public static final String[] ruleNames = {
		"root", "dslDeclaration", "imports", "anImport", "doc", "node", "signature", 
		"parent", "parameters", "explicitParameter", "implicitParameter", "value", 
		"body", "facetApply", "facetTarget", "nodeReference", "variable", "variableType", 
		"nativeName", "count", "word", "wordValue", "stringValue", "booleanValue", 
		"naturalValue", "integerValue", "doubleValue", "linkValue", "plate", "measureValue", 
		"tags", "annotations", "annotation", "flags", "flag", "varInit", "headerReference", 
		"identifierReference", "hierarchy", "metaidentifier"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'Concept'", "'sub'", "'use'", "'dsl'", "'var'", "'as'", "'has'", 
		"'on'", "'is'", "'into'", "'with'", "'extends'", "'abstract'", "'single'", 
		"'multiple'", "'required'", "'terminal'", "'root'", "'property'", "'feature'", 
		"'readonly'", "'enclosed'", "'facet'", "'('", "')'", "'['", "']'", "'...'", 
		"'>'", "'<'", "'#'", "':'", "','", "'.'", "'='", null, "'*'", "'+'", "'word'", 
		"'file'", "'integer'", "'natural'", "'native'", "'double'", "'string'", 
		"'boolean'", "'measure'", "'ratio'", "'date'", "'empty'", null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, "'indent'", "'dedent'", null, null, null, null, "'\"'", 
		"'\\\"'", "'\\'", null, "'t53647656ext'", "'te245656786xt'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "METAIDENTIFIER", "SUB", "USE", "DSL", "VAR", "AS", "HAS", "ON", 
		"IS", "INTO", "WITH", "EXTENDS", "ABSTRACT", "SINGLE", "MULTIPLE", "REQUIRED", 
		"TERMINAL", "ROOT", "PROPERTY", "FEATURE", "READONLY", "ENCLOSED", "FACET", 
		"LEFT_PARENTHESIS", "RIGHT_PARENTHESIS", "LEFT_SQUARE", "RIGHT_SQUARE", 
		"LIST", "INLINE", "CLOSE_INLINE", "HASHTAG", "COLON", "COMMA", "DOT", 
		"EQUALS", "SEMICOLON", "STAR", "PLUS", "WORD", "RESOURCE", "INT_TYPE", 
		"NATURAL_TYPE", "NATIVE_TYPE", "DOUBLE_TYPE", "STRING_TYPE", "BOOLEAN_TYPE", 
		"MEASURE_TYPE", "RATIO_TYPE", "DATE_TYPE", "EMPTY", "BLOCK_COMMENT", "LINE_COMMENT", 
		"SCIENCE_NOT", "BOOLEAN_VALUE", "NATURAL_VALUE", "NEGATIVE_VALUE", "DOUBLE_VALUE", 
		"APHOSTROPHE", "STRING_MULTILINE", "PLATE_VALUE", "IDENTIFIER", "MEASURE_VALUE", 
		"NEWLINE", "SPACES", "DOC", "SP", "NL", "NEW_LINE_INDENT", "DEDENT", "UNKNOWN_TOKEN", 
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

	@Override
	public String getGrammarFileName() { return "TaraGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public TaraGrammar(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class RootContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(TaraGrammar.EOF, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(TaraGrammar.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(TaraGrammar.NEWLINE, i);
		}
		public DslDeclarationContext dslDeclaration() {
			return getRuleContext(DslDeclarationContext.class,0);
		}
		public ImportsContext imports() {
			return getRuleContext(ImportsContext.class,0);
		}
		public List<NodeContext> node() {
			return getRuleContexts(NodeContext.class);
		}
		public NodeContext node(int i) {
			return getRuleContext(NodeContext.class,i);
		}
		public RootContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_root; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterRoot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitRoot(this);
		}
	}

	public final RootContext root() throws RecognitionException {
		RootContext _localctx = new RootContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_root);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(83);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(80); 
				match(NEWLINE);
				}
				}
				setState(85);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(87);
			_la = _input.LA(1);
			if (_la==DSL) {
				{
				setState(86); 
				dslDeclaration();
				}
			}

			setState(90);
			_la = _input.LA(1);
			if (_la==USE) {
				{
				setState(89); 
				imports();
				}
			}

			setState(101);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << METAIDENTIFIER) | (1L << SUB) | (1L << IDENTIFIER))) != 0)) {
				{
				{
				setState(92); 
				node();
				setState(96);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NEWLINE) {
					{
					{
					setState(93); 
					match(NEWLINE);
					}
					}
					setState(98);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				}
				setState(103);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(104); 
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DslDeclarationContext extends ParserRuleContext {
		public TerminalNode DSL() { return getToken(TaraGrammar.DSL, 0); }
		public HeaderReferenceContext headerReference() {
			return getRuleContext(HeaderReferenceContext.class,0);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(TaraGrammar.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(TaraGrammar.NEWLINE, i);
		}
		public DslDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dslDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterDslDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitDslDeclaration(this);
		}
	}

	public final DslDeclarationContext dslDeclaration() throws RecognitionException {
		DslDeclarationContext _localctx = new DslDeclarationContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_dslDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(106); 
			match(DSL);
			setState(107); 
			headerReference();
			setState(109); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(108); 
				match(NEWLINE);
				}
				}
				setState(111); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NEWLINE );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ImportsContext extends ParserRuleContext {
		public List<AnImportContext> anImport() {
			return getRuleContexts(AnImportContext.class);
		}
		public AnImportContext anImport(int i) {
			return getRuleContext(AnImportContext.class,i);
		}
		public ImportsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_imports; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterImports(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitImports(this);
		}
	}

	public final ImportsContext imports() throws RecognitionException {
		ImportsContext _localctx = new ImportsContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_imports);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(114); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(113); 
				anImport();
				}
				}
				setState(116); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==USE );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AnImportContext extends ParserRuleContext {
		public TerminalNode USE() { return getToken(TaraGrammar.USE, 0); }
		public HeaderReferenceContext headerReference() {
			return getRuleContext(HeaderReferenceContext.class,0);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(TaraGrammar.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(TaraGrammar.NEWLINE, i);
		}
		public AnImportContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_anImport; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterAnImport(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitAnImport(this);
		}
	}

	public final AnImportContext anImport() throws RecognitionException {
		AnImportContext _localctx = new AnImportContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_anImport);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(118); 
			match(USE);
			setState(119); 
			headerReference();
			setState(121); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(120); 
				match(NEWLINE);
				}
				}
				setState(123); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NEWLINE );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DocContext extends ParserRuleContext {
		public List<TerminalNode> DOC() { return getTokens(TaraGrammar.DOC); }
		public TerminalNode DOC(int i) {
			return getToken(TaraGrammar.DOC, i);
		}
		public DocContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_doc; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterDoc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitDoc(this);
		}
	}

	public final DocContext doc() throws RecognitionException {
		DocContext _localctx = new DocContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_doc);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(126); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(125); 
				match(DOC);
				}
				}
				setState(128); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==DOC );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NodeContext extends ParserRuleContext {
		public SignatureContext signature() {
			return getRuleContext(SignatureContext.class,0);
		}
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public NodeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_node; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterNode(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitNode(this);
		}
	}

	public final NodeContext node() throws RecognitionException {
		NodeContext _localctx = new NodeContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_node);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(130); 
			signature();
			setState(132);
			_la = _input.LA(1);
			if (_la==NEW_LINE_INDENT) {
				{
				setState(131); 
				body();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SignatureContext extends ParserRuleContext {
		public TagsContext tags() {
			return getRuleContext(TagsContext.class,0);
		}
		public PlateContext plate() {
			return getRuleContext(PlateContext.class,0);
		}
		public TerminalNode SUB() { return getToken(TaraGrammar.SUB, 0); }
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
		public MetaidentifierContext metaidentifier() {
			return getRuleContext(MetaidentifierContext.class,0);
		}
		public ParametersContext parameters() {
			return getRuleContext(ParametersContext.class,0);
		}
		public ParentContext parent() {
			return getRuleContext(ParentContext.class,0);
		}
		public SignatureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_signature; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterSignature(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitSignature(this);
		}
	}

	public final SignatureContext signature() throws RecognitionException {
		SignatureContext _localctx = new SignatureContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_signature);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(149);
			switch (_input.LA(1)) {
			case SUB:
				{
				{
				setState(134); 
				match(SUB);
				setState(136);
				_la = _input.LA(1);
				if (_la==LEFT_PARENTHESIS) {
					{
					setState(135); 
					parameters();
					}
				}

				setState(138); 
				match(IDENTIFIER);
				}
				}
				break;
			case METAIDENTIFIER:
			case IDENTIFIER:
				{
				{
				setState(139); 
				metaidentifier();
				setState(141);
				_la = _input.LA(1);
				if (_la==LEFT_PARENTHESIS) {
					{
					setState(140); 
					parameters();
					}
				}

				setState(144);
				switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
				case 1:
					{
					setState(143); 
					match(IDENTIFIER);
					}
					break;
				}
				setState(147);
				_la = _input.LA(1);
				if (_la==EXTENDS) {
					{
					setState(146); 
					parent();
					}
				}

				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(152);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				{
				setState(151); 
				tags();
				}
				break;
			}
			setState(155);
			_la = _input.LA(1);
			if (_la==PLATE_VALUE) {
				{
				setState(154); 
				plate();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParentContext extends ParserRuleContext {
		public TerminalNode EXTENDS() { return getToken(TaraGrammar.EXTENDS, 0); }
		public IdentifierReferenceContext identifierReference() {
			return getRuleContext(IdentifierReferenceContext.class,0);
		}
		public ParentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parent; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterParent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitParent(this);
		}
	}

	public final ParentContext parent() throws RecognitionException {
		ParentContext _localctx = new ParentContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_parent);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(157); 
			match(EXTENDS);
			setState(158); 
			identifierReference();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParametersContext extends ParserRuleContext {
		public TerminalNode LEFT_PARENTHESIS() { return getToken(TaraGrammar.LEFT_PARENTHESIS, 0); }
		public TerminalNode RIGHT_PARENTHESIS() { return getToken(TaraGrammar.RIGHT_PARENTHESIS, 0); }
		public List<ExplicitParameterContext> explicitParameter() {
			return getRuleContexts(ExplicitParameterContext.class);
		}
		public ExplicitParameterContext explicitParameter(int i) {
			return getRuleContext(ExplicitParameterContext.class,i);
		}
		public List<ImplicitParameterContext> implicitParameter() {
			return getRuleContexts(ImplicitParameterContext.class);
		}
		public ImplicitParameterContext implicitParameter(int i) {
			return getRuleContext(ImplicitParameterContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(TaraGrammar.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(TaraGrammar.COMMA, i);
		}
		public ParametersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameters; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterParameters(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitParameters(this);
		}
	}

	public final ParametersContext parameters() throws RecognitionException {
		ParametersContext _localctx = new ParametersContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_parameters);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(160); 
			match(LEFT_PARENTHESIS);
			setState(177);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				{
				{
				setState(161); 
				explicitParameter();
				setState(166);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(162); 
					match(COMMA);
					setState(163); 
					explicitParameter();
					}
					}
					setState(168);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				}
				break;
			case 2:
				{
				{
				setState(169); 
				implicitParameter();
				setState(174);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(170); 
					match(COMMA);
					setState(171); 
					implicitParameter();
					}
					}
					setState(176);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				}
				break;
			}
			setState(179); 
			match(RIGHT_PARENTHESIS);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExplicitParameterContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
		public TerminalNode EQUALS() { return getToken(TaraGrammar.EQUALS, 0); }
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public ExplicitParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_explicitParameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterExplicitParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitExplicitParameter(this);
		}
	}

	public final ExplicitParameterContext explicitParameter() throws RecognitionException {
		ExplicitParameterContext _localctx = new ExplicitParameterContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_explicitParameter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(181); 
			match(IDENTIFIER);
			setState(182); 
			match(EQUALS);
			setState(183); 
			value();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ImplicitParameterContext extends ParserRuleContext {
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public ImplicitParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_implicitParameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterImplicitParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitImplicitParameter(this);
		}
	}

	public final ImplicitParameterContext implicitParameter() throws RecognitionException {
		ImplicitParameterContext _localctx = new ImplicitParameterContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_implicitParameter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(185); 
			value();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ValueContext extends ParserRuleContext {
		public List<IdentifierReferenceContext> identifierReference() {
			return getRuleContexts(IdentifierReferenceContext.class);
		}
		public IdentifierReferenceContext identifierReference(int i) {
			return getRuleContext(IdentifierReferenceContext.class,i);
		}
		public List<StringValueContext> stringValue() {
			return getRuleContexts(StringValueContext.class);
		}
		public StringValueContext stringValue(int i) {
			return getRuleContext(StringValueContext.class,i);
		}
		public List<BooleanValueContext> booleanValue() {
			return getRuleContexts(BooleanValueContext.class);
		}
		public BooleanValueContext booleanValue(int i) {
			return getRuleContext(BooleanValueContext.class,i);
		}
		public List<LinkValueContext> linkValue() {
			return getRuleContexts(LinkValueContext.class);
		}
		public LinkValueContext linkValue(int i) {
			return getRuleContext(LinkValueContext.class,i);
		}
		public List<NaturalValueContext> naturalValue() {
			return getRuleContexts(NaturalValueContext.class);
		}
		public NaturalValueContext naturalValue(int i) {
			return getRuleContext(NaturalValueContext.class,i);
		}
		public MeasureValueContext measureValue() {
			return getRuleContext(MeasureValueContext.class,0);
		}
		public List<IntegerValueContext> integerValue() {
			return getRuleContexts(IntegerValueContext.class);
		}
		public IntegerValueContext integerValue(int i) {
			return getRuleContext(IntegerValueContext.class,i);
		}
		public List<DoubleValueContext> doubleValue() {
			return getRuleContexts(DoubleValueContext.class);
		}
		public DoubleValueContext doubleValue(int i) {
			return getRuleContext(DoubleValueContext.class,i);
		}
		public TerminalNode EMPTY() { return getToken(TaraGrammar.EMPTY, 0); }
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitValue(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_value);
		int _la;
		try {
			int _alt;
			setState(232);
			switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(188); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(187); 
					identifierReference();
					}
					}
					setState(190); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==IDENTIFIER );
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(193); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(192); 
						stringValue();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(195); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(198); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(197); 
					booleanValue();
					}
					}
					setState(200); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==BOOLEAN_VALUE );
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(203); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(202); 
					linkValue();
					}
					}
					setState(205); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==PLATE_VALUE || _la==IDENTIFIER );
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(208); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(207); 
					naturalValue();
					}
					}
					setState(210); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NATURAL_VALUE );
				setState(213);
				_la = _input.LA(1);
				if (_la==IDENTIFIER || _la==MEASURE_VALUE) {
					{
					setState(212); 
					measureValue();
					}
				}

				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(216); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(215); 
					integerValue();
					}
					}
					setState(218); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NATURAL_VALUE || _la==NEGATIVE_VALUE );
				setState(221);
				_la = _input.LA(1);
				if (_la==IDENTIFIER || _la==MEASURE_VALUE) {
					{
					setState(220); 
					measureValue();
					}
				}

				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(224); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(223); 
					doubleValue();
					}
					}
					setState(226); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NATURAL_VALUE) | (1L << NEGATIVE_VALUE) | (1L << DOUBLE_VALUE))) != 0) );
				setState(229);
				_la = _input.LA(1);
				if (_la==IDENTIFIER || _la==MEASURE_VALUE) {
					{
					setState(228); 
					measureValue();
					}
				}

				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(231); 
				match(EMPTY);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BodyContext extends ParserRuleContext {
		public TerminalNode NEW_LINE_INDENT() { return getToken(TaraGrammar.NEW_LINE_INDENT, 0); }
		public TerminalNode DEDENT() { return getToken(TaraGrammar.DEDENT, 0); }
		public List<VariableContext> variable() {
			return getRuleContexts(VariableContext.class);
		}
		public VariableContext variable(int i) {
			return getRuleContext(VariableContext.class,i);
		}
		public List<NodeContext> node() {
			return getRuleContexts(NodeContext.class);
		}
		public NodeContext node(int i) {
			return getRuleContext(NodeContext.class,i);
		}
		public List<VarInitContext> varInit() {
			return getRuleContexts(VarInitContext.class);
		}
		public VarInitContext varInit(int i) {
			return getRuleContext(VarInitContext.class,i);
		}
		public List<FacetApplyContext> facetApply() {
			return getRuleContexts(FacetApplyContext.class);
		}
		public FacetApplyContext facetApply(int i) {
			return getRuleContext(FacetApplyContext.class,i);
		}
		public List<FacetTargetContext> facetTarget() {
			return getRuleContexts(FacetTargetContext.class);
		}
		public FacetTargetContext facetTarget(int i) {
			return getRuleContext(FacetTargetContext.class,i);
		}
		public List<NodeReferenceContext> nodeReference() {
			return getRuleContexts(NodeReferenceContext.class);
		}
		public NodeReferenceContext nodeReference(int i) {
			return getRuleContext(NodeReferenceContext.class,i);
		}
		public List<DocContext> doc() {
			return getRuleContexts(DocContext.class);
		}
		public DocContext doc(int i) {
			return getRuleContext(DocContext.class,i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(TaraGrammar.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(TaraGrammar.NEWLINE, i);
		}
		public BodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_body; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitBody(this);
		}
	}

	public final BodyContext body() throws RecognitionException {
		BodyContext _localctx = new BodyContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_body);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(234); 
			match(NEW_LINE_INDENT);
			setState(249); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(242);
				switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
				case 1:
					{
					setState(235); 
					variable();
					}
					break;
				case 2:
					{
					setState(236); 
					node();
					}
					break;
				case 3:
					{
					setState(237); 
					varInit();
					}
					break;
				case 4:
					{
					setState(238); 
					facetApply();
					}
					break;
				case 5:
					{
					setState(239); 
					facetTarget();
					}
					break;
				case 6:
					{
					setState(240); 
					nodeReference();
					}
					break;
				case 7:
					{
					setState(241); 
					doc();
					}
					break;
				}
				setState(245); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(244); 
					match(NEWLINE);
					}
					}
					setState(247); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NEWLINE );
				}
				}
				setState(251); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << METAIDENTIFIER) | (1L << SUB) | (1L << VAR) | (1L << AS) | (1L << HAS) | (1L << ON) | (1L << IDENTIFIER))) != 0) || _la==DOC );
			setState(253); 
			match(DEDENT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FacetApplyContext extends ParserRuleContext {
		public TerminalNode AS() { return getToken(TaraGrammar.AS, 0); }
		public List<MetaidentifierContext> metaidentifier() {
			return getRuleContexts(MetaidentifierContext.class);
		}
		public MetaidentifierContext metaidentifier(int i) {
			return getRuleContext(MetaidentifierContext.class,i);
		}
		public ParametersContext parameters() {
			return getRuleContext(ParametersContext.class,0);
		}
		public TerminalNode WITH() { return getToken(TaraGrammar.WITH, 0); }
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public FacetApplyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_facetApply; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterFacetApply(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitFacetApply(this);
		}
	}

	public final FacetApplyContext facetApply() throws RecognitionException {
		FacetApplyContext _localctx = new FacetApplyContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_facetApply);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(255); 
			match(AS);
			setState(256); 
			metaidentifier();
			setState(258);
			_la = _input.LA(1);
			if (_la==LEFT_PARENTHESIS) {
				{
				setState(257); 
				parameters();
				}
			}

			setState(262);
			_la = _input.LA(1);
			if (_la==WITH) {
				{
				setState(260); 
				match(WITH);
				setState(261); 
				metaidentifier();
				}
			}

			setState(265);
			_la = _input.LA(1);
			if (_la==NEW_LINE_INDENT) {
				{
				setState(264); 
				body();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FacetTargetContext extends ParserRuleContext {
		public TerminalNode ON() { return getToken(TaraGrammar.ON, 0); }
		public IdentifierReferenceContext identifierReference() {
			return getRuleContext(IdentifierReferenceContext.class,0);
		}
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public FacetTargetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_facetTarget; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterFacetTarget(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitFacetTarget(this);
		}
	}

	public final FacetTargetContext facetTarget() throws RecognitionException {
		FacetTargetContext _localctx = new FacetTargetContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_facetTarget);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(267); 
			match(ON);
			setState(268); 
			identifierReference();
			setState(270);
			_la = _input.LA(1);
			if (_la==NEW_LINE_INDENT) {
				{
				setState(269); 
				body();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NodeReferenceContext extends ParserRuleContext {
		public TerminalNode HAS() { return getToken(TaraGrammar.HAS, 0); }
		public IdentifierReferenceContext identifierReference() {
			return getRuleContext(IdentifierReferenceContext.class,0);
		}
		public TagsContext tags() {
			return getRuleContext(TagsContext.class,0);
		}
		public NodeReferenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nodeReference; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterNodeReference(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitNodeReference(this);
		}
	}

	public final NodeReferenceContext nodeReference() throws RecognitionException {
		NodeReferenceContext _localctx = new NodeReferenceContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_nodeReference);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(272); 
			match(HAS);
			setState(273); 
			identifierReference();
			setState(275);
			switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
			case 1:
				{
				setState(274); 
				tags();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableContext extends ParserRuleContext {
		public TerminalNode VAR() { return getToken(TaraGrammar.VAR, 0); }
		public VariableTypeContext variableType() {
			return getRuleContext(VariableTypeContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
		public NativeNameContext nativeName() {
			return getRuleContext(NativeNameContext.class,0);
		}
		public TerminalNode LIST() { return getToken(TaraGrammar.LIST, 0); }
		public CountContext count() {
			return getRuleContext(CountContext.class,0);
		}
		public WordContext word() {
			return getRuleContext(WordContext.class,0);
		}
		public List<FlagsContext> flags() {
			return getRuleContexts(FlagsContext.class);
		}
		public FlagsContext flags(int i) {
			return getRuleContext(FlagsContext.class,i);
		}
		public TerminalNode EQUALS() { return getToken(TaraGrammar.EQUALS, 0); }
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitVariable(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_variable);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(277); 
			match(VAR);
			setState(278); 
			variableType();
			setState(280);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(279); 
				nativeName();
				}
			}

			setState(284);
			switch (_input.LA(1)) {
			case LIST:
				{
				setState(282); 
				match(LIST);
				}
				break;
			case LEFT_SQUARE:
				{
				setState(283); 
				count();
				}
				break;
			case IDENTIFIER:
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(286); 
			match(IDENTIFIER);
			setState(293);
			switch ( getInterpreter().adaptivePredict(_input,42,_ctx) ) {
			case 1:
				{
				setState(288);
				_la = _input.LA(1);
				if (_la==IS) {
					{
					setState(287); 
					flags();
					}
				}

				setState(290); 
				word();
				}
				break;
			case 2:
				{
				{
				setState(291); 
				match(EQUALS);
				setState(292); 
				value();
				}
				}
				break;
			}
			setState(296);
			_la = _input.LA(1);
			if (_la==IS) {
				{
				setState(295); 
				flags();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableTypeContext extends ParserRuleContext {
		public TerminalNode NATURAL_TYPE() { return getToken(TaraGrammar.NATURAL_TYPE, 0); }
		public TerminalNode INT_TYPE() { return getToken(TaraGrammar.INT_TYPE, 0); }
		public TerminalNode BOOLEAN_TYPE() { return getToken(TaraGrammar.BOOLEAN_TYPE, 0); }
		public TerminalNode STRING_TYPE() { return getToken(TaraGrammar.STRING_TYPE, 0); }
		public TerminalNode DATE_TYPE() { return getToken(TaraGrammar.DATE_TYPE, 0); }
		public TerminalNode NATIVE_TYPE() { return getToken(TaraGrammar.NATIVE_TYPE, 0); }
		public TerminalNode RATIO_TYPE() { return getToken(TaraGrammar.RATIO_TYPE, 0); }
		public TerminalNode DOUBLE_TYPE() { return getToken(TaraGrammar.DOUBLE_TYPE, 0); }
		public TerminalNode MEASURE_TYPE() { return getToken(TaraGrammar.MEASURE_TYPE, 0); }
		public TerminalNode WORD() { return getToken(TaraGrammar.WORD, 0); }
		public TerminalNode RESOURCE() { return getToken(TaraGrammar.RESOURCE, 0); }
		public IdentifierReferenceContext identifierReference() {
			return getRuleContext(IdentifierReferenceContext.class,0);
		}
		public VariableTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterVariableType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitVariableType(this);
		}
	}

	public final VariableTypeContext variableType() throws RecognitionException {
		VariableTypeContext _localctx = new VariableTypeContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_variableType);
		try {
			setState(310);
			switch (_input.LA(1)) {
			case NATURAL_TYPE:
				enterOuterAlt(_localctx, 1);
				{
				setState(298); 
				match(NATURAL_TYPE);
				}
				break;
			case INT_TYPE:
				enterOuterAlt(_localctx, 2);
				{
				setState(299); 
				match(INT_TYPE);
				}
				break;
			case BOOLEAN_TYPE:
				enterOuterAlt(_localctx, 3);
				{
				setState(300); 
				match(BOOLEAN_TYPE);
				}
				break;
			case STRING_TYPE:
				enterOuterAlt(_localctx, 4);
				{
				setState(301); 
				match(STRING_TYPE);
				}
				break;
			case DATE_TYPE:
				enterOuterAlt(_localctx, 5);
				{
				setState(302); 
				match(DATE_TYPE);
				}
				break;
			case NATIVE_TYPE:
				enterOuterAlt(_localctx, 6);
				{
				setState(303); 
				match(NATIVE_TYPE);
				}
				break;
			case RATIO_TYPE:
				enterOuterAlt(_localctx, 7);
				{
				setState(304); 
				match(RATIO_TYPE);
				}
				break;
			case DOUBLE_TYPE:
				enterOuterAlt(_localctx, 8);
				{
				setState(305); 
				match(DOUBLE_TYPE);
				}
				break;
			case MEASURE_TYPE:
				enterOuterAlt(_localctx, 9);
				{
				setState(306); 
				match(MEASURE_TYPE);
				}
				break;
			case WORD:
				enterOuterAlt(_localctx, 10);
				{
				setState(307); 
				match(WORD);
				}
				break;
			case RESOURCE:
				enterOuterAlt(_localctx, 11);
				{
				setState(308); 
				match(RESOURCE);
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 12);
				{
				setState(309); 
				identifierReference();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NativeNameContext extends ParserRuleContext {
		public TerminalNode COLON() { return getToken(TaraGrammar.COLON, 0); }
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
		public TerminalNode MEASURE_VALUE() { return getToken(TaraGrammar.MEASURE_VALUE, 0); }
		public NativeNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nativeName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterNativeName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitNativeName(this);
		}
	}

	public final NativeNameContext nativeName() throws RecognitionException {
		NativeNameContext _localctx = new NativeNameContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_nativeName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(312); 
			match(COLON);
			setState(313);
			_la = _input.LA(1);
			if ( !(_la==IDENTIFIER || _la==MEASURE_VALUE) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CountContext extends ParserRuleContext {
		public TerminalNode LEFT_SQUARE() { return getToken(TaraGrammar.LEFT_SQUARE, 0); }
		public TerminalNode NATURAL_VALUE() { return getToken(TaraGrammar.NATURAL_VALUE, 0); }
		public TerminalNode RIGHT_SQUARE() { return getToken(TaraGrammar.RIGHT_SQUARE, 0); }
		public CountContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_count; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterCount(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitCount(this);
		}
	}

	public final CountContext count() throws RecognitionException {
		CountContext _localctx = new CountContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_count);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(315); 
			match(LEFT_SQUARE);
			setState(316); 
			match(NATURAL_VALUE);
			setState(317); 
			match(RIGHT_SQUARE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WordContext extends ParserRuleContext {
		public TerminalNode NEW_LINE_INDENT() { return getToken(TaraGrammar.NEW_LINE_INDENT, 0); }
		public TerminalNode DEDENT() { return getToken(TaraGrammar.DEDENT, 0); }
		public List<WordValueContext> wordValue() {
			return getRuleContexts(WordValueContext.class);
		}
		public WordValueContext wordValue(int i) {
			return getRuleContext(WordValueContext.class,i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(TaraGrammar.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(TaraGrammar.NEWLINE, i);
		}
		public WordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_word; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterWord(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitWord(this);
		}
	}

	public final WordContext word() throws RecognitionException {
		WordContext _localctx = new WordContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_word);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(319); 
			match(NEW_LINE_INDENT);
			setState(323); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(320); 
				wordValue();
				setState(321); 
				match(NEWLINE);
				}
				}
				setState(325); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==IDENTIFIER );
			setState(327); 
			match(DEDENT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WordValueContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
		public TerminalNode STAR() { return getToken(TaraGrammar.STAR, 0); }
		public WordValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_wordValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterWordValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitWordValue(this);
		}
	}

	public final WordValueContext wordValue() throws RecognitionException {
		WordValueContext _localctx = new WordValueContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_wordValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(329); 
			match(IDENTIFIER);
			setState(331);
			_la = _input.LA(1);
			if (_la==STAR) {
				{
				setState(330); 
				match(STAR);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StringValueContext extends ParserRuleContext {
		public TerminalNode QUOTE_BEGIN() { return getToken(TaraGrammar.QUOTE_BEGIN, 0); }
		public TerminalNode QUOTE_END() { return getToken(TaraGrammar.QUOTE_END, 0); }
		public TerminalNode NEWLINE() { return getToken(TaraGrammar.NEWLINE, 0); }
		public List<TerminalNode> CHARACTER() { return getTokens(TaraGrammar.CHARACTER); }
		public TerminalNode CHARACTER(int i) {
			return getToken(TaraGrammar.CHARACTER, i);
		}
		public StringValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stringValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterStringValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitStringValue(this);
		}
	}

	public final StringValueContext stringValue() throws RecognitionException {
		StringValueContext _localctx = new StringValueContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_stringValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(334);
			_la = _input.LA(1);
			if (_la==NEWLINE) {
				{
				setState(333); 
				match(NEWLINE);
				}
			}

			{
			setState(336); 
			match(QUOTE_BEGIN);
			setState(340);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==CHARACTER) {
				{
				{
				setState(337); 
				match(CHARACTER);
				}
				}
				setState(342);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(343); 
			match(QUOTE_END);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BooleanValueContext extends ParserRuleContext {
		public TerminalNode BOOLEAN_VALUE() { return getToken(TaraGrammar.BOOLEAN_VALUE, 0); }
		public BooleanValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_booleanValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterBooleanValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitBooleanValue(this);
		}
	}

	public final BooleanValueContext booleanValue() throws RecognitionException {
		BooleanValueContext _localctx = new BooleanValueContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_booleanValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(345); 
			match(BOOLEAN_VALUE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NaturalValueContext extends ParserRuleContext {
		public TerminalNode NATURAL_VALUE() { return getToken(TaraGrammar.NATURAL_VALUE, 0); }
		public NaturalValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_naturalValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterNaturalValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitNaturalValue(this);
		}
	}

	public final NaturalValueContext naturalValue() throws RecognitionException {
		NaturalValueContext _localctx = new NaturalValueContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_naturalValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(347); 
			match(NATURAL_VALUE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IntegerValueContext extends ParserRuleContext {
		public TerminalNode NATURAL_VALUE() { return getToken(TaraGrammar.NATURAL_VALUE, 0); }
		public TerminalNode NEGATIVE_VALUE() { return getToken(TaraGrammar.NEGATIVE_VALUE, 0); }
		public IntegerValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_integerValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterIntegerValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitIntegerValue(this);
		}
	}

	public final IntegerValueContext integerValue() throws RecognitionException {
		IntegerValueContext _localctx = new IntegerValueContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_integerValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(349);
			_la = _input.LA(1);
			if ( !(_la==NATURAL_VALUE || _la==NEGATIVE_VALUE) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DoubleValueContext extends ParserRuleContext {
		public TerminalNode NATURAL_VALUE() { return getToken(TaraGrammar.NATURAL_VALUE, 0); }
		public TerminalNode NEGATIVE_VALUE() { return getToken(TaraGrammar.NEGATIVE_VALUE, 0); }
		public TerminalNode DOUBLE_VALUE() { return getToken(TaraGrammar.DOUBLE_VALUE, 0); }
		public TerminalNode SCIENCE_NOT() { return getToken(TaraGrammar.SCIENCE_NOT, 0); }
		public DoubleValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_doubleValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterDoubleValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitDoubleValue(this);
		}
	}

	public final DoubleValueContext doubleValue() throws RecognitionException {
		DoubleValueContext _localctx = new DoubleValueContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_doubleValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(351);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NATURAL_VALUE) | (1L << NEGATIVE_VALUE) | (1L << DOUBLE_VALUE))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			setState(353);
			_la = _input.LA(1);
			if (_la==SCIENCE_NOT) {
				{
				setState(352); 
				match(SCIENCE_NOT);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LinkValueContext extends ParserRuleContext {
		public PlateContext plate() {
			return getRuleContext(PlateContext.class,0);
		}
		public IdentifierReferenceContext identifierReference() {
			return getRuleContext(IdentifierReferenceContext.class,0);
		}
		public LinkValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_linkValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterLinkValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitLinkValue(this);
		}
	}

	public final LinkValueContext linkValue() throws RecognitionException {
		LinkValueContext _localctx = new LinkValueContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_linkValue);
		try {
			setState(357);
			switch (_input.LA(1)) {
			case PLATE_VALUE:
				enterOuterAlt(_localctx, 1);
				{
				setState(355); 
				plate();
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(356); 
				identifierReference();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PlateContext extends ParserRuleContext {
		public TerminalNode PLATE_VALUE() { return getToken(TaraGrammar.PLATE_VALUE, 0); }
		public PlateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_plate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterPlate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitPlate(this);
		}
	}

	public final PlateContext plate() throws RecognitionException {
		PlateContext _localctx = new PlateContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_plate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(359); 
			match(PLATE_VALUE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MeasureValueContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
		public TerminalNode MEASURE_VALUE() { return getToken(TaraGrammar.MEASURE_VALUE, 0); }
		public MeasureValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_measureValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterMeasureValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitMeasureValue(this);
		}
	}

	public final MeasureValueContext measureValue() throws RecognitionException {
		MeasureValueContext _localctx = new MeasureValueContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_measureValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(361);
			_la = _input.LA(1);
			if ( !(_la==IDENTIFIER || _la==MEASURE_VALUE) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TagsContext extends ParserRuleContext {
		public FlagsContext flags() {
			return getRuleContext(FlagsContext.class,0);
		}
		public AnnotationsContext annotations() {
			return getRuleContext(AnnotationsContext.class,0);
		}
		public TagsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tags; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterTags(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitTags(this);
		}
	}

	public final TagsContext tags() throws RecognitionException {
		TagsContext _localctx = new TagsContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_tags);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(364);
			_la = _input.LA(1);
			if (_la==IS) {
				{
				setState(363); 
				flags();
				}
			}

			setState(367);
			_la = _input.LA(1);
			if (_la==INTO) {
				{
				setState(366); 
				annotations();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AnnotationsContext extends ParserRuleContext {
		public TerminalNode INTO() { return getToken(TaraGrammar.INTO, 0); }
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public AnnotationsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotations; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterAnnotations(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitAnnotations(this);
		}
	}

	public final AnnotationsContext annotations() throws RecognitionException {
		AnnotationsContext _localctx = new AnnotationsContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_annotations);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(369); 
			match(INTO);
			setState(371); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(370); 
				annotation();
				}
				}
				setState(373); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SINGLE) | (1L << MULTIPLE) | (1L << REQUIRED) | (1L << TERMINAL) | (1L << ROOT) | (1L << PROPERTY) | (1L << FEATURE) | (1L << ENCLOSED) | (1L << FACET))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AnnotationContext extends ParserRuleContext {
		public TerminalNode TERMINAL() { return getToken(TaraGrammar.TERMINAL, 0); }
		public TerminalNode ROOT() { return getToken(TaraGrammar.ROOT, 0); }
		public TerminalNode SINGLE() { return getToken(TaraGrammar.SINGLE, 0); }
		public TerminalNode MULTIPLE() { return getToken(TaraGrammar.MULTIPLE, 0); }
		public TerminalNode REQUIRED() { return getToken(TaraGrammar.REQUIRED, 0); }
		public TerminalNode FACET() { return getToken(TaraGrammar.FACET, 0); }
		public TerminalNode FEATURE() { return getToken(TaraGrammar.FEATURE, 0); }
		public TerminalNode PROPERTY() { return getToken(TaraGrammar.PROPERTY, 0); }
		public TerminalNode ENCLOSED() { return getToken(TaraGrammar.ENCLOSED, 0); }
		public AnnotationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterAnnotation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitAnnotation(this);
		}
	}

	public final AnnotationContext annotation() throws RecognitionException {
		AnnotationContext _localctx = new AnnotationContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_annotation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(375);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SINGLE) | (1L << MULTIPLE) | (1L << REQUIRED) | (1L << TERMINAL) | (1L << ROOT) | (1L << PROPERTY) | (1L << FEATURE) | (1L << ENCLOSED) | (1L << FACET))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FlagsContext extends ParserRuleContext {
		public TerminalNode IS() { return getToken(TaraGrammar.IS, 0); }
		public List<FlagContext> flag() {
			return getRuleContexts(FlagContext.class);
		}
		public FlagContext flag(int i) {
			return getRuleContext(FlagContext.class,i);
		}
		public FlagsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_flags; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterFlags(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitFlags(this);
		}
	}

	public final FlagsContext flags() throws RecognitionException {
		FlagsContext _localctx = new FlagsContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_flags);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(377); 
			match(IS);
			setState(379); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(378); 
				flag();
				}
				}
				setState(381); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABSTRACT) | (1L << SINGLE) | (1L << MULTIPLE) | (1L << REQUIRED) | (1L << TERMINAL) | (1L << ROOT) | (1L << PROPERTY) | (1L << FEATURE) | (1L << READONLY) | (1L << ENCLOSED) | (1L << FACET))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FlagContext extends ParserRuleContext {
		public TerminalNode ABSTRACT() { return getToken(TaraGrammar.ABSTRACT, 0); }
		public TerminalNode TERMINAL() { return getToken(TaraGrammar.TERMINAL, 0); }
		public TerminalNode ROOT() { return getToken(TaraGrammar.ROOT, 0); }
		public TerminalNode SINGLE() { return getToken(TaraGrammar.SINGLE, 0); }
		public TerminalNode MULTIPLE() { return getToken(TaraGrammar.MULTIPLE, 0); }
		public TerminalNode REQUIRED() { return getToken(TaraGrammar.REQUIRED, 0); }
		public TerminalNode FACET() { return getToken(TaraGrammar.FACET, 0); }
		public TerminalNode FEATURE() { return getToken(TaraGrammar.FEATURE, 0); }
		public TerminalNode PROPERTY() { return getToken(TaraGrammar.PROPERTY, 0); }
		public TerminalNode ENCLOSED() { return getToken(TaraGrammar.ENCLOSED, 0); }
		public TerminalNode READONLY() { return getToken(TaraGrammar.READONLY, 0); }
		public FlagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_flag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterFlag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitFlag(this);
		}
	}

	public final FlagContext flag() throws RecognitionException {
		FlagContext _localctx = new FlagContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_flag);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(383);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABSTRACT) | (1L << SINGLE) | (1L << MULTIPLE) | (1L << REQUIRED) | (1L << TERMINAL) | (1L << ROOT) | (1L << PROPERTY) | (1L << FEATURE) | (1L << READONLY) | (1L << ENCLOSED) | (1L << FACET))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarInitContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
		public TerminalNode EQUALS() { return getToken(TaraGrammar.EQUALS, 0); }
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public VarInitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varInit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterVarInit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitVarInit(this);
		}
	}

	public final VarInitContext varInit() throws RecognitionException {
		VarInitContext _localctx = new VarInitContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_varInit);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(385); 
			match(IDENTIFIER);
			setState(386); 
			match(EQUALS);
			setState(387); 
			value();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class HeaderReferenceContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
		public List<HierarchyContext> hierarchy() {
			return getRuleContexts(HierarchyContext.class);
		}
		public HierarchyContext hierarchy(int i) {
			return getRuleContext(HierarchyContext.class,i);
		}
		public HeaderReferenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_headerReference; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterHeaderReference(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitHeaderReference(this);
		}
	}

	public final HeaderReferenceContext headerReference() throws RecognitionException {
		HeaderReferenceContext _localctx = new HeaderReferenceContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_headerReference);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(392);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,55,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(389); 
					hierarchy();
					}
					} 
				}
				setState(394);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,55,_ctx);
			}
			setState(395); 
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdentifierReferenceContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
		public List<HierarchyContext> hierarchy() {
			return getRuleContexts(HierarchyContext.class);
		}
		public HierarchyContext hierarchy(int i) {
			return getRuleContext(HierarchyContext.class,i);
		}
		public IdentifierReferenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifierReference; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterIdentifierReference(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitIdentifierReference(this);
		}
	}

	public final IdentifierReferenceContext identifierReference() throws RecognitionException {
		IdentifierReferenceContext _localctx = new IdentifierReferenceContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_identifierReference);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(400);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,56,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(397); 
					hierarchy();
					}
					} 
				}
				setState(402);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,56,_ctx);
			}
			setState(403); 
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class HierarchyContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
		public TerminalNode DOT() { return getToken(TaraGrammar.DOT, 0); }
		public HierarchyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_hierarchy; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterHierarchy(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitHierarchy(this);
		}
	}

	public final HierarchyContext hierarchy() throws RecognitionException {
		HierarchyContext _localctx = new HierarchyContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_hierarchy);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(405); 
			match(IDENTIFIER);
			setState(406); 
			match(DOT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MetaidentifierContext extends ParserRuleContext {
		public TerminalNode METAIDENTIFIER() { return getToken(TaraGrammar.METAIDENTIFIER, 0); }
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
		public MetaidentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_metaidentifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterMetaidentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitMetaidentifier(this);
		}
	}

	public final MetaidentifierContext metaidentifier() throws RecognitionException {
		MetaidentifierContext _localctx = new MetaidentifierContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_metaidentifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(408);
			_la = _input.LA(1);
			if ( !(_la==METAIDENTIFIER || _la==IDENTIFIER) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3Q\u019d\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\3\2\7\2T\n\2\f"+
		"\2\16\2W\13\2\3\2\5\2Z\n\2\3\2\5\2]\n\2\3\2\3\2\7\2a\n\2\f\2\16\2d\13"+
		"\2\7\2f\n\2\f\2\16\2i\13\2\3\2\3\2\3\3\3\3\3\3\6\3p\n\3\r\3\16\3q\3\4"+
		"\6\4u\n\4\r\4\16\4v\3\5\3\5\3\5\6\5|\n\5\r\5\16\5}\3\6\6\6\u0081\n\6\r"+
		"\6\16\6\u0082\3\7\3\7\5\7\u0087\n\7\3\b\3\b\5\b\u008b\n\b\3\b\3\b\3\b"+
		"\5\b\u0090\n\b\3\b\5\b\u0093\n\b\3\b\5\b\u0096\n\b\5\b\u0098\n\b\3\b\5"+
		"\b\u009b\n\b\3\b\5\b\u009e\n\b\3\t\3\t\3\t\3\n\3\n\3\n\3\n\7\n\u00a7\n"+
		"\n\f\n\16\n\u00aa\13\n\3\n\3\n\3\n\7\n\u00af\n\n\f\n\16\n\u00b2\13\n\5"+
		"\n\u00b4\n\n\3\n\3\n\3\13\3\13\3\13\3\13\3\f\3\f\3\r\6\r\u00bf\n\r\r\r"+
		"\16\r\u00c0\3\r\6\r\u00c4\n\r\r\r\16\r\u00c5\3\r\6\r\u00c9\n\r\r\r\16"+
		"\r\u00ca\3\r\6\r\u00ce\n\r\r\r\16\r\u00cf\3\r\6\r\u00d3\n\r\r\r\16\r\u00d4"+
		"\3\r\5\r\u00d8\n\r\3\r\6\r\u00db\n\r\r\r\16\r\u00dc\3\r\5\r\u00e0\n\r"+
		"\3\r\6\r\u00e3\n\r\r\r\16\r\u00e4\3\r\5\r\u00e8\n\r\3\r\5\r\u00eb\n\r"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u00f5\n\16\3\16\6\16\u00f8"+
		"\n\16\r\16\16\16\u00f9\6\16\u00fc\n\16\r\16\16\16\u00fd\3\16\3\16\3\17"+
		"\3\17\3\17\5\17\u0105\n\17\3\17\3\17\5\17\u0109\n\17\3\17\5\17\u010c\n"+
		"\17\3\20\3\20\3\20\5\20\u0111\n\20\3\21\3\21\3\21\5\21\u0116\n\21\3\22"+
		"\3\22\3\22\5\22\u011b\n\22\3\22\3\22\5\22\u011f\n\22\3\22\3\22\5\22\u0123"+
		"\n\22\3\22\3\22\3\22\5\22\u0128\n\22\3\22\5\22\u012b\n\22\3\23\3\23\3"+
		"\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\5\23\u0139\n\23\3\24"+
		"\3\24\3\24\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\6\26\u0146\n\26\r\26"+
		"\16\26\u0147\3\26\3\26\3\27\3\27\5\27\u014e\n\27\3\30\5\30\u0151\n\30"+
		"\3\30\3\30\7\30\u0155\n\30\f\30\16\30\u0158\13\30\3\30\3\30\3\31\3\31"+
		"\3\32\3\32\3\33\3\33\3\34\3\34\5\34\u0164\n\34\3\35\3\35\5\35\u0168\n"+
		"\35\3\36\3\36\3\37\3\37\3 \5 \u016f\n \3 \5 \u0172\n \3!\3!\6!\u0176\n"+
		"!\r!\16!\u0177\3\"\3\"\3#\3#\6#\u017e\n#\r#\16#\u017f\3$\3$\3%\3%\3%\3"+
		"%\3&\7&\u0189\n&\f&\16&\u018c\13&\3&\3&\3\'\7\'\u0191\n\'\f\'\16\'\u0194"+
		"\13\'\3\'\3\'\3(\3(\3(\3)\3)\3)\2\2*\2\4\6\b\n\f\16\20\22\24\26\30\32"+
		"\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJLNP\2\b\3\2?@\3\29:\3\29;\4\2\20"+
		"\26\30\31\3\2\17\31\4\2\3\3??\u01c4\2U\3\2\2\2\4l\3\2\2\2\6t\3\2\2\2\b"+
		"x\3\2\2\2\n\u0080\3\2\2\2\f\u0084\3\2\2\2\16\u0097\3\2\2\2\20\u009f\3"+
		"\2\2\2\22\u00a2\3\2\2\2\24\u00b7\3\2\2\2\26\u00bb\3\2\2\2\30\u00ea\3\2"+
		"\2\2\32\u00ec\3\2\2\2\34\u0101\3\2\2\2\36\u010d\3\2\2\2 \u0112\3\2\2\2"+
		"\"\u0117\3\2\2\2$\u0138\3\2\2\2&\u013a\3\2\2\2(\u013d\3\2\2\2*\u0141\3"+
		"\2\2\2,\u014b\3\2\2\2.\u0150\3\2\2\2\60\u015b\3\2\2\2\62\u015d\3\2\2\2"+
		"\64\u015f\3\2\2\2\66\u0161\3\2\2\28\u0167\3\2\2\2:\u0169\3\2\2\2<\u016b"+
		"\3\2\2\2>\u016e\3\2\2\2@\u0173\3\2\2\2B\u0179\3\2\2\2D\u017b\3\2\2\2F"+
		"\u0181\3\2\2\2H\u0183\3\2\2\2J\u018a\3\2\2\2L\u0192\3\2\2\2N\u0197\3\2"+
		"\2\2P\u019a\3\2\2\2RT\7A\2\2SR\3\2\2\2TW\3\2\2\2US\3\2\2\2UV\3\2\2\2V"+
		"Y\3\2\2\2WU\3\2\2\2XZ\5\4\3\2YX\3\2\2\2YZ\3\2\2\2Z\\\3\2\2\2[]\5\6\4\2"+
		"\\[\3\2\2\2\\]\3\2\2\2]g\3\2\2\2^b\5\f\7\2_a\7A\2\2`_\3\2\2\2ad\3\2\2"+
		"\2b`\3\2\2\2bc\3\2\2\2cf\3\2\2\2db\3\2\2\2e^\3\2\2\2fi\3\2\2\2ge\3\2\2"+
		"\2gh\3\2\2\2hj\3\2\2\2ig\3\2\2\2jk\7\2\2\3k\3\3\2\2\2lm\7\6\2\2mo\5J&"+
		"\2np\7A\2\2on\3\2\2\2pq\3\2\2\2qo\3\2\2\2qr\3\2\2\2r\5\3\2\2\2su\5\b\5"+
		"\2ts\3\2\2\2uv\3\2\2\2vt\3\2\2\2vw\3\2\2\2w\7\3\2\2\2xy\7\5\2\2y{\5J&"+
		"\2z|\7A\2\2{z\3\2\2\2|}\3\2\2\2}{\3\2\2\2}~\3\2\2\2~\t\3\2\2\2\177\u0081"+
		"\7C\2\2\u0080\177\3\2\2\2\u0081\u0082\3\2\2\2\u0082\u0080\3\2\2\2\u0082"+
		"\u0083\3\2\2\2\u0083\13\3\2\2\2\u0084\u0086\5\16\b\2\u0085\u0087\5\32"+
		"\16\2\u0086\u0085\3\2\2\2\u0086\u0087\3\2\2\2\u0087\r\3\2\2\2\u0088\u008a"+
		"\7\4\2\2\u0089\u008b\5\22\n\2\u008a\u0089\3\2\2\2\u008a\u008b\3\2\2\2"+
		"\u008b\u008c\3\2\2\2\u008c\u0098\7?\2\2\u008d\u008f\5P)\2\u008e\u0090"+
		"\5\22\n\2\u008f\u008e\3\2\2\2\u008f\u0090\3\2\2\2\u0090\u0092\3\2\2\2"+
		"\u0091\u0093\7?\2\2\u0092\u0091\3\2\2\2\u0092\u0093\3\2\2\2\u0093\u0095"+
		"\3\2\2\2\u0094\u0096\5\20\t\2\u0095\u0094\3\2\2\2\u0095\u0096\3\2\2\2"+
		"\u0096\u0098\3\2\2\2\u0097\u0088\3\2\2\2\u0097\u008d\3\2\2\2\u0098\u009a"+
		"\3\2\2\2\u0099\u009b\5> \2\u009a\u0099\3\2\2\2\u009a\u009b\3\2\2\2\u009b"+
		"\u009d\3\2\2\2\u009c\u009e\5:\36\2\u009d\u009c\3\2\2\2\u009d\u009e\3\2"+
		"\2\2\u009e\17\3\2\2\2\u009f\u00a0\7\16\2\2\u00a0\u00a1\5L\'\2\u00a1\21"+
		"\3\2\2\2\u00a2\u00b3\7\32\2\2\u00a3\u00a8\5\24\13\2\u00a4\u00a5\7#\2\2"+
		"\u00a5\u00a7\5\24\13\2\u00a6\u00a4\3\2\2\2\u00a7\u00aa\3\2\2\2\u00a8\u00a6"+
		"\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9\u00b4\3\2\2\2\u00aa\u00a8\3\2\2\2\u00ab"+
		"\u00b0\5\26\f\2\u00ac\u00ad\7#\2\2\u00ad\u00af\5\26\f\2\u00ae\u00ac\3"+
		"\2\2\2\u00af\u00b2\3\2\2\2\u00b0\u00ae\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1"+
		"\u00b4\3\2\2\2\u00b2\u00b0\3\2\2\2\u00b3\u00a3\3\2\2\2\u00b3\u00ab\3\2"+
		"\2\2\u00b4\u00b5\3\2\2\2\u00b5\u00b6\7\33\2\2\u00b6\23\3\2\2\2\u00b7\u00b8"+
		"\7?\2\2\u00b8\u00b9\7%\2\2\u00b9\u00ba\5\30\r\2\u00ba\25\3\2\2\2\u00bb"+
		"\u00bc\5\30\r\2\u00bc\27\3\2\2\2\u00bd\u00bf\5L\'\2\u00be\u00bd\3\2\2"+
		"\2\u00bf\u00c0\3\2\2\2\u00c0\u00be\3\2\2\2\u00c0\u00c1\3\2\2\2\u00c1\u00eb"+
		"\3\2\2\2\u00c2\u00c4\5.\30\2\u00c3\u00c2\3\2\2\2\u00c4\u00c5\3\2\2\2\u00c5"+
		"\u00c3\3\2\2\2\u00c5\u00c6\3\2\2\2\u00c6\u00eb\3\2\2\2\u00c7\u00c9\5\60"+
		"\31\2\u00c8\u00c7\3\2\2\2\u00c9\u00ca\3\2\2\2\u00ca\u00c8\3\2\2\2\u00ca"+
		"\u00cb\3\2\2\2\u00cb\u00eb\3\2\2\2\u00cc\u00ce\58\35\2\u00cd\u00cc\3\2"+
		"\2\2\u00ce\u00cf\3\2\2\2\u00cf\u00cd\3\2\2\2\u00cf\u00d0\3\2\2\2\u00d0"+
		"\u00eb\3\2\2\2\u00d1\u00d3\5\62\32\2\u00d2\u00d1\3\2\2\2\u00d3\u00d4\3"+
		"\2\2\2\u00d4\u00d2\3\2\2\2\u00d4\u00d5\3\2\2\2\u00d5\u00d7\3\2\2\2\u00d6"+
		"\u00d8\5<\37\2\u00d7\u00d6\3\2\2\2\u00d7\u00d8\3\2\2\2\u00d8\u00eb\3\2"+
		"\2\2\u00d9\u00db\5\64\33\2\u00da\u00d9\3\2\2\2\u00db\u00dc\3\2\2\2\u00dc"+
		"\u00da\3\2\2\2\u00dc\u00dd\3\2\2\2\u00dd\u00df\3\2\2\2\u00de\u00e0\5<"+
		"\37\2\u00df\u00de\3\2\2\2\u00df\u00e0\3\2\2\2\u00e0\u00eb\3\2\2\2\u00e1"+
		"\u00e3\5\66\34\2\u00e2\u00e1\3\2\2\2\u00e3\u00e4\3\2\2\2\u00e4\u00e2\3"+
		"\2\2\2\u00e4\u00e5\3\2\2\2\u00e5\u00e7\3\2\2\2\u00e6\u00e8\5<\37\2\u00e7"+
		"\u00e6\3\2\2\2\u00e7\u00e8\3\2\2\2\u00e8\u00eb\3\2\2\2\u00e9\u00eb\7\64"+
		"\2\2\u00ea\u00be\3\2\2\2\u00ea\u00c3\3\2\2\2\u00ea\u00c8\3\2\2\2\u00ea"+
		"\u00cd\3\2\2\2\u00ea\u00d2\3\2\2\2\u00ea\u00da\3\2\2\2\u00ea\u00e2\3\2"+
		"\2\2\u00ea\u00e9\3\2\2\2\u00eb\31\3\2\2\2\u00ec\u00fb\7F\2\2\u00ed\u00f5"+
		"\5\"\22\2\u00ee\u00f5\5\f\7\2\u00ef\u00f5\5H%\2\u00f0\u00f5\5\34\17\2"+
		"\u00f1\u00f5\5\36\20\2\u00f2\u00f5\5 \21\2\u00f3\u00f5\5\n\6\2\u00f4\u00ed"+
		"\3\2\2\2\u00f4\u00ee\3\2\2\2\u00f4\u00ef\3\2\2\2\u00f4\u00f0\3\2\2\2\u00f4"+
		"\u00f1\3\2\2\2\u00f4\u00f2\3\2\2\2\u00f4\u00f3\3\2\2\2\u00f5\u00f7\3\2"+
		"\2\2\u00f6\u00f8\7A\2\2\u00f7\u00f6\3\2\2\2\u00f8\u00f9\3\2\2\2\u00f9"+
		"\u00f7\3\2\2\2\u00f9\u00fa\3\2\2\2\u00fa\u00fc\3\2\2\2\u00fb\u00f4\3\2"+
		"\2\2\u00fc\u00fd\3\2\2\2\u00fd\u00fb\3\2\2\2\u00fd\u00fe\3\2\2\2\u00fe"+
		"\u00ff\3\2\2\2\u00ff\u0100\7G\2\2\u0100\33\3\2\2\2\u0101\u0102\7\b\2\2"+
		"\u0102\u0104\5P)\2\u0103\u0105\5\22\n\2\u0104\u0103\3\2\2\2\u0104\u0105"+
		"\3\2\2\2\u0105\u0108\3\2\2\2\u0106\u0107\7\r\2\2\u0107\u0109\5P)\2\u0108"+
		"\u0106\3\2\2\2\u0108\u0109\3\2\2\2\u0109\u010b\3\2\2\2\u010a\u010c\5\32"+
		"\16\2\u010b\u010a\3\2\2\2\u010b\u010c\3\2\2\2\u010c\35\3\2\2\2\u010d\u010e"+
		"\7\n\2\2\u010e\u0110\5L\'\2\u010f\u0111\5\32\16\2\u0110\u010f\3\2\2\2"+
		"\u0110\u0111\3\2\2\2\u0111\37\3\2\2\2\u0112\u0113\7\t\2\2\u0113\u0115"+
		"\5L\'\2\u0114\u0116\5> \2\u0115\u0114\3\2\2\2\u0115\u0116\3\2\2\2\u0116"+
		"!\3\2\2\2\u0117\u0118\7\7\2\2\u0118\u011a\5$\23\2\u0119\u011b\5&\24\2"+
		"\u011a\u0119\3\2\2\2\u011a\u011b\3\2\2\2\u011b\u011e\3\2\2\2\u011c\u011f"+
		"\7\36\2\2\u011d\u011f\5(\25\2\u011e\u011c\3\2\2\2\u011e\u011d\3\2\2\2"+
		"\u011e\u011f\3\2\2\2\u011f\u0120\3\2\2\2\u0120\u0127\7?\2\2\u0121\u0123"+
		"\5D#\2\u0122\u0121\3\2\2\2\u0122\u0123\3\2\2\2\u0123\u0124\3\2\2\2\u0124"+
		"\u0128\5*\26\2\u0125\u0126\7%\2\2\u0126\u0128\5\30\r\2\u0127\u0122\3\2"+
		"\2\2\u0127\u0125\3\2\2\2\u0127\u0128\3\2\2\2\u0128\u012a\3\2\2\2\u0129"+
		"\u012b\5D#\2\u012a\u0129\3\2\2\2\u012a\u012b\3\2\2\2\u012b#\3\2\2\2\u012c"+
		"\u0139\7,\2\2\u012d\u0139\7+\2\2\u012e\u0139\7\60\2\2\u012f\u0139\7/\2"+
		"\2\u0130\u0139\7\63\2\2\u0131\u0139\7-\2\2\u0132\u0139\7\62\2\2\u0133"+
		"\u0139\7.\2\2\u0134\u0139\7\61\2\2\u0135\u0139\7)\2\2\u0136\u0139\7*\2"+
		"\2\u0137\u0139\5L\'\2\u0138\u012c\3\2\2\2\u0138\u012d\3\2\2\2\u0138\u012e"+
		"\3\2\2\2\u0138\u012f\3\2\2\2\u0138\u0130\3\2\2\2\u0138\u0131\3\2\2\2\u0138"+
		"\u0132\3\2\2\2\u0138\u0133\3\2\2\2\u0138\u0134\3\2\2\2\u0138\u0135\3\2"+
		"\2\2\u0138\u0136\3\2\2\2\u0138\u0137\3\2\2\2\u0139%\3\2\2\2\u013a\u013b"+
		"\7\"\2\2\u013b\u013c\t\2\2\2\u013c\'\3\2\2\2\u013d\u013e\7\34\2\2\u013e"+
		"\u013f\79\2\2\u013f\u0140\7\35\2\2\u0140)\3\2\2\2\u0141\u0145\7F\2\2\u0142"+
		"\u0143\5,\27\2\u0143\u0144\7A\2\2\u0144\u0146\3\2\2\2\u0145\u0142\3\2"+
		"\2\2\u0146\u0147\3\2\2\2\u0147\u0145\3\2\2\2\u0147\u0148\3\2\2\2\u0148"+
		"\u0149\3\2\2\2\u0149\u014a\7G\2\2\u014a+\3\2\2\2\u014b\u014d\7?\2\2\u014c"+
		"\u014e\7\'\2\2\u014d\u014c\3\2\2\2\u014d\u014e\3\2\2\2\u014e-\3\2\2\2"+
		"\u014f\u0151\7A\2\2\u0150\u014f\3\2\2\2\u0150\u0151\3\2\2\2\u0151\u0152"+
		"\3\2\2\2\u0152\u0156\7Q\2\2\u0153\u0155\7O\2\2\u0154\u0153\3\2\2\2\u0155"+
		"\u0158\3\2\2\2\u0156\u0154\3\2\2\2\u0156\u0157\3\2\2\2\u0157\u0159\3\2"+
		"\2\2\u0158\u0156\3\2\2\2\u0159\u015a\7P\2\2\u015a/\3\2\2\2\u015b\u015c"+
		"\78\2\2\u015c\61\3\2\2\2\u015d\u015e\79\2\2\u015e\63\3\2\2\2\u015f\u0160"+
		"\t\3\2\2\u0160\65\3\2\2\2\u0161\u0163\t\4\2\2\u0162\u0164\7\67\2\2\u0163"+
		"\u0162\3\2\2\2\u0163\u0164\3\2\2\2\u0164\67\3\2\2\2\u0165\u0168\5:\36"+
		"\2\u0166\u0168\5L\'\2\u0167\u0165\3\2\2\2\u0167\u0166\3\2\2\2\u01689\3"+
		"\2\2\2\u0169\u016a\7>\2\2\u016a;\3\2\2\2\u016b\u016c\t\2\2\2\u016c=\3"+
		"\2\2\2\u016d\u016f\5D#\2\u016e\u016d\3\2\2\2\u016e\u016f\3\2\2\2\u016f"+
		"\u0171\3\2\2\2\u0170\u0172\5@!\2\u0171\u0170\3\2\2\2\u0171\u0172\3\2\2"+
		"\2\u0172?\3\2\2\2\u0173\u0175\7\f\2\2\u0174\u0176\5B\"\2\u0175\u0174\3"+
		"\2\2\2\u0176\u0177\3\2\2\2\u0177\u0175\3\2\2\2\u0177\u0178\3\2\2\2\u0178"+
		"A\3\2\2\2\u0179\u017a\t\5\2\2\u017aC\3\2\2\2\u017b\u017d\7\13\2\2\u017c"+
		"\u017e\5F$\2\u017d\u017c\3\2\2\2\u017e\u017f\3\2\2\2\u017f\u017d\3\2\2"+
		"\2\u017f\u0180\3\2\2\2\u0180E\3\2\2\2\u0181\u0182\t\6\2\2\u0182G\3\2\2"+
		"\2\u0183\u0184\7?\2\2\u0184\u0185\7%\2\2\u0185\u0186\5\30\r\2\u0186I\3"+
		"\2\2\2\u0187\u0189\5N(\2\u0188\u0187\3\2\2\2\u0189\u018c\3\2\2\2\u018a"+
		"\u0188\3\2\2\2\u018a\u018b\3\2\2\2\u018b\u018d\3\2\2\2\u018c\u018a\3\2"+
		"\2\2\u018d\u018e\7?\2\2\u018eK\3\2\2\2\u018f\u0191\5N(\2\u0190\u018f\3"+
		"\2\2\2\u0191\u0194\3\2\2\2\u0192\u0190\3\2\2\2\u0192\u0193\3\2\2\2\u0193"+
		"\u0195\3\2\2\2\u0194\u0192\3\2\2\2\u0195\u0196\7?\2\2\u0196M\3\2\2\2\u0197"+
		"\u0198\7?\2\2\u0198\u0199\7$\2\2\u0199O\3\2\2\2\u019a\u019b\t\7\2\2\u019b"+
		"Q\3\2\2\2;UY\\bgqv}\u0082\u0086\u008a\u008f\u0092\u0095\u0097\u009a\u009d"+
		"\u00a8\u00b0\u00b3\u00c0\u00c5\u00ca\u00cf\u00d4\u00d7\u00dc\u00df\u00e4"+
		"\u00e7\u00ea\u00f4\u00f9\u00fd\u0104\u0108\u010b\u0110\u0115\u011a\u011e"+
		"\u0122\u0127\u012a\u0138\u0147\u014d\u0150\u0156\u0163\u0167\u016e\u0171"+
		"\u0177\u017f\u018a\u0192";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}