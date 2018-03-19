// Generated from /Users/oroncal/workspace/tara/core/language/src/io/intino/tara/lang/grammar/TaraGrammar.g4 by ANTLR 4.7
package io.intino.tara.lang.grammar;
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
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		SUB=1, USE=2, DSL=3, VAR=4, AS=5, HAS=6, ON=7, IS=8, INTO=9, WITH=10, 
		ANY=11, EXTENDS=12, CONCEPT=13, ABSTRACT=14, TERMINAL=15, COMPONENT=16, 
		FEATURE=17, FINAL=18, ENCLOSED=19, PRIVATE=20, REACTIVE=21, VOLATILE=22, 
		DECORABLE=23, LEFT_PARENTHESIS=24, RIGHT_PARENTHESIS=25, LEFT_SQUARE=26, 
		RIGHT_SQUARE=27, LEFT_CURLY=28, RIGHT_CURLY=29, INLINE=30, CLOSE_INLINE=31, 
		AT=32, HASHTAG=33, COLON=34, COMMA=35, DOT=36, EQUALS=37, STAR=38, LIST=39, 
		SEMICOLON=40, PLUS=41, WORD=42, RESOURCE=43, INT_TYPE=44, FUNCTION_TYPE=45, 
		OBJECT_TYPE=46, DOUBLE_TYPE=47, STRING_TYPE=48, BOOLEAN_TYPE=49, DATE_TYPE=50, 
		INSTANT_TYPE=51, TIME_TYPE=52, EMPTY=53, BLOCK_COMMENT=54, LINE_COMMENT=55, 
		SCIENCE_NOT=56, BOOLEAN_VALUE=57, NATURAL_VALUE=58, NEGATIVE_VALUE=59, 
		DOUBLE_VALUE=60, STRING=61, STRING_MULTILINE=62, SINGLE_QUOTE=63, EXPRESSION_MULTILINE=64, 
		CLASS_TYPE=65, IDENTIFIER=66, MEASURE_VALUE=67, NEWLINE=68, SPACES=69, 
		DOC=70, SP=71, NL=72, NEW_LINE_INDENT=73, DEDENT=74, UNKNOWN_TOKEN=75, 
		SLASH=76, QUOTE=77, CHARACTER=78, M_QUOTE=79, M_CHARACTER=80, ME_STRING_MULTILINE=81, 
		ME_CHARACTER=82, E_QUOTE=83, E_SLASH_Q=84, E_SLASH=85, E_CHARACTER=86, 
		QUOTE_BEGIN=87, QUOTE_END=88, EXPRESSION_BEGIN=89, EXPRESSION_END=90;
	public static final int
		RULE_root = 0, RULE_dslDeclaration = 1, RULE_imports = 2, RULE_anImport = 3, 
		RULE_doc = 4, RULE_node = 5, RULE_signature = 6, RULE_parent = 7, RULE_parameters = 8, 
		RULE_parameter = 9, RULE_facets = 10, RULE_facet = 11, RULE_value = 12, 
		RULE_body = 13, RULE_facetTarget = 14, RULE_nodeReference = 15, RULE_with = 16, 
		RULE_variable = 17, RULE_bodyValue = 18, RULE_variableType = 19, RULE_ruleContainer = 20, 
		RULE_ruleValue = 21, RULE_classType = 22, RULE_range = 23, RULE_size = 24, 
		RULE_sizeRange = 25, RULE_listRange = 26, RULE_methodReference = 27, RULE_stringValue = 28, 
		RULE_booleanValue = 29, RULE_tupleValue = 30, RULE_integerValue = 31, 
		RULE_doubleValue = 32, RULE_metric = 33, RULE_expression = 34, RULE_tags = 35, 
		RULE_annotations = 36, RULE_annotation = 37, RULE_flags = 38, RULE_flag = 39, 
		RULE_varInit = 40, RULE_headerReference = 41, RULE_identifierReference = 42, 
		RULE_hierarchy = 43, RULE_metaidentifier = 44;
	public static final String[] ruleNames = {
		"root", "dslDeclaration", "imports", "anImport", "doc", "node", "signature", 
		"parent", "parameters", "parameter", "facets", "facet", "value", "body", 
		"facetTarget", "nodeReference", "with", "variable", "bodyValue", "variableType", 
		"ruleContainer", "ruleValue", "classType", "range", "size", "sizeRange", 
		"listRange", "methodReference", "stringValue", "booleanValue", "tupleValue", 
		"integerValue", "doubleValue", "metric", "expression", "tags", "annotations", 
		"annotation", "flags", "flag", "varInit", "headerReference", "identifierReference", 
		"hierarchy", "metaidentifier"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'sub'", "'use'", "'dsl'", "'var'", "'as'", "'has'", "'on'", "'is'", 
		"'into'", "'with'", "'any'", "'extends'", "'concept'", "'abstract'", "'terminal'", 
		"'component'", "'feature'", "'final'", "'enclosed'", "'private'", "'reactive'", 
		"'volatile'", "'decorable'", "'('", "')'", "'['", "']'", "'{'", "'}'", 
		"'>'", "'<'", "'@'", "'#'", "':'", "','", "'.'", "'='", "'*'", "'...'", 
		null, "'+'", "'word'", "'resource'", "'integer'", "'function'", "'object'", 
		"'double'", "'string'", "'boolean'", "'datex'", "'instant'", "'time'", 
		"'empty'", null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, "'indent'", "'dedent'", 
		null, "'\\\"'", null, null, null, null, null, null, null, "'\\''", "'\\'", 
		null, "'%QUOTE_BEGIN%'", "'%QUOTE_END%'", "'%EXPRESSION_BEGIN%'", "'%EXPRESSION_END%'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "SUB", "USE", "DSL", "VAR", "AS", "HAS", "ON", "IS", "INTO", "WITH", 
		"ANY", "EXTENDS", "CONCEPT", "ABSTRACT", "TERMINAL", "COMPONENT", "FEATURE", 
		"FINAL", "ENCLOSED", "PRIVATE", "REACTIVE", "VOLATILE", "DECORABLE", "LEFT_PARENTHESIS", 
		"RIGHT_PARENTHESIS", "LEFT_SQUARE", "RIGHT_SQUARE", "LEFT_CURLY", "RIGHT_CURLY", 
		"INLINE", "CLOSE_INLINE", "AT", "HASHTAG", "COLON", "COMMA", "DOT", "EQUALS", 
		"STAR", "LIST", "SEMICOLON", "PLUS", "WORD", "RESOURCE", "INT_TYPE", "FUNCTION_TYPE", 
		"OBJECT_TYPE", "DOUBLE_TYPE", "STRING_TYPE", "BOOLEAN_TYPE", "DATE_TYPE", 
		"INSTANT_TYPE", "TIME_TYPE", "EMPTY", "BLOCK_COMMENT", "LINE_COMMENT", 
		"SCIENCE_NOT", "BOOLEAN_VALUE", "NATURAL_VALUE", "NEGATIVE_VALUE", "DOUBLE_VALUE", 
		"STRING", "STRING_MULTILINE", "SINGLE_QUOTE", "EXPRESSION_MULTILINE", 
		"CLASS_TYPE", "IDENTIFIER", "MEASURE_VALUE", "NEWLINE", "SPACES", "DOC", 
		"SP", "NL", "NEW_LINE_INDENT", "DEDENT", "UNKNOWN_TOKEN", "SLASH", "QUOTE", 
		"CHARACTER", "M_QUOTE", "M_CHARACTER", "ME_STRING_MULTILINE", "ME_CHARACTER", 
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
			setState(93);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(90);
				match(NEWLINE);
				}
				}
				setState(95);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(97);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DSL) {
				{
				setState(96);
				dslDeclaration();
				}
			}

			setState(100);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==USE) {
				{
				setState(99);
				imports();
				}
			}

			setState(111);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SUB || _la==IDENTIFIER || _la==DOC) {
				{
				{
				setState(102);
				node();
				setState(106);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NEWLINE) {
					{
					{
					setState(103);
					match(NEWLINE);
					}
					}
					setState(108);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				}
				setState(113);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(114);
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
			setState(116);
			match(DSL);
			setState(117);
			headerReference();
			setState(121);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(118);
				match(NEWLINE);
				}
				}
				setState(123);
				_errHandler.sync(this);
				_la = _input.LA(1);
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
			setState(125); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(124);
				anImport();
				}
				}
				setState(127); 
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
			setState(129);
			match(USE);
			setState(130);
			headerReference();
			setState(132); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(131);
				match(NEWLINE);
				}
				}
				setState(134); 
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
			setState(137); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(136);
				match(DOC);
				}
				}
				setState(139); 
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
		public DocContext doc() {
			return getRuleContext(DocContext.class,0);
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
			setState(142);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DOC) {
				{
				setState(141);
				doc();
				}
			}

			setState(144);
			signature();
			setState(146);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NEW_LINE_INDENT) {
				{
				setState(145);
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
		public TerminalNode SUB() { return getToken(TaraGrammar.SUB, 0); }
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
		public MetaidentifierContext metaidentifier() {
			return getRuleContext(MetaidentifierContext.class,0);
		}
		public FacetTargetContext facetTarget() {
			return getRuleContext(FacetTargetContext.class,0);
		}
		public List<RuleContainerContext> ruleContainer() {
			return getRuleContexts(RuleContainerContext.class);
		}
		public RuleContainerContext ruleContainer(int i) {
			return getRuleContext(RuleContainerContext.class,i);
		}
		public ParametersContext parameters() {
			return getRuleContext(ParametersContext.class,0);
		}
		public List<FacetsContext> facets() {
			return getRuleContexts(FacetsContext.class);
		}
		public FacetsContext facets(int i) {
			return getRuleContext(FacetsContext.class,i);
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
			setState(187);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SUB:
				{
				{
				setState(148);
				match(SUB);
				setState(152);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COLON) {
					{
					{
					setState(149);
					ruleContainer();
					}
					}
					setState(154);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(156);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LEFT_PARENTHESIS) {
					{
					setState(155);
					parameters();
					}
				}

				setState(158);
				match(IDENTIFIER);
				setState(162);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==AS) {
					{
					{
					setState(159);
					facets();
					}
					}
					setState(164);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				}
				break;
			case IDENTIFIER:
				{
				{
				setState(165);
				metaidentifier();
				setState(169);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COLON) {
					{
					{
					setState(166);
					ruleContainer();
					}
					}
					setState(171);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(173);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LEFT_PARENTHESIS) {
					{
					setState(172);
					parameters();
					}
				}

				setState(176);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
				case 1:
					{
					setState(175);
					match(IDENTIFIER);
					}
					break;
				}
				setState(181);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==AS) {
					{
					{
					setState(178);
					facets();
					}
					}
					setState(183);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(185);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==EXTENDS) {
					{
					setState(184);
					parent();
					}
				}

				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			{
			setState(190);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ON) {
				{
				setState(189);
				facetTarget();
				}
			}

			setState(192);
			tags();
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
			setState(194);
			match(EXTENDS);
			setState(195);
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
		public List<ParameterContext> parameter() {
			return getRuleContexts(ParameterContext.class);
		}
		public ParameterContext parameter(int i) {
			return getRuleContext(ParameterContext.class,i);
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
			setState(197);
			match(LEFT_PARENTHESIS);
			setState(206);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 32)) & ~0x3f) == 0 && ((1L << (_la - 32)) & ((1L << (AT - 32)) | (1L << (EMPTY - 32)) | (1L << (BOOLEAN_VALUE - 32)) | (1L << (NATURAL_VALUE - 32)) | (1L << (NEGATIVE_VALUE - 32)) | (1L << (DOUBLE_VALUE - 32)) | (1L << (STRING - 32)) | (1L << (IDENTIFIER - 32)) | (1L << (NEWLINE - 32)) | (1L << (EXPRESSION_BEGIN - 32)))) != 0)) {
				{
				setState(198);
				parameter();
				setState(203);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(199);
					match(COMMA);
					setState(200);
					parameter();
					}
					}
					setState(205);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(208);
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

	public static class ParameterContext extends ParserRuleContext {
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
		public TerminalNode EQUALS() { return getToken(TaraGrammar.EQUALS, 0); }
		public ParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitParameter(this);
		}
	}

	public final ParameterContext parameter() throws RecognitionException {
		ParameterContext _localctx = new ParameterContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_parameter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(212);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				{
				setState(210);
				match(IDENTIFIER);
				setState(211);
				match(EQUALS);
				}
				break;
			}
			setState(214);
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

	public static class FacetsContext extends ParserRuleContext {
		public TerminalNode AS() { return getToken(TaraGrammar.AS, 0); }
		public List<FacetContext> facet() {
			return getRuleContexts(FacetContext.class);
		}
		public FacetContext facet(int i) {
			return getRuleContext(FacetContext.class,i);
		}
		public FacetsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_facets; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterFacets(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitFacets(this);
		}
	}

	public final FacetsContext facets() throws RecognitionException {
		FacetsContext _localctx = new FacetsContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_facets);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(216);
			match(AS);
			setState(218); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(217);
					facet();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(220); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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

	public static class FacetContext extends ParserRuleContext {
		public MetaidentifierContext metaidentifier() {
			return getRuleContext(MetaidentifierContext.class,0);
		}
		public ParametersContext parameters() {
			return getRuleContext(ParametersContext.class,0);
		}
		public FacetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_facet; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterFacet(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitFacet(this);
		}
	}

	public final FacetContext facet() throws RecognitionException {
		FacetContext _localctx = new FacetContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_facet);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(222);
			metaidentifier();
			setState(224);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LEFT_PARENTHESIS) {
				{
				setState(223);
				parameters();
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
		public List<TupleValueContext> tupleValue() {
			return getRuleContexts(TupleValueContext.class);
		}
		public TupleValueContext tupleValue(int i) {
			return getRuleContext(TupleValueContext.class,i);
		}
		public List<BooleanValueContext> booleanValue() {
			return getRuleContexts(BooleanValueContext.class);
		}
		public BooleanValueContext booleanValue(int i) {
			return getRuleContext(BooleanValueContext.class,i);
		}
		public List<IntegerValueContext> integerValue() {
			return getRuleContexts(IntegerValueContext.class);
		}
		public IntegerValueContext integerValue(int i) {
			return getRuleContext(IntegerValueContext.class,i);
		}
		public MetricContext metric() {
			return getRuleContext(MetricContext.class,0);
		}
		public List<DoubleValueContext> doubleValue() {
			return getRuleContexts(DoubleValueContext.class);
		}
		public DoubleValueContext doubleValue(int i) {
			return getRuleContext(DoubleValueContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<MethodReferenceContext> methodReference() {
			return getRuleContexts(MethodReferenceContext.class);
		}
		public MethodReferenceContext methodReference(int i) {
			return getRuleContext(MethodReferenceContext.class,i);
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
		enterRule(_localctx, 24, RULE_value);
		int _la;
		try {
			int _alt;
			setState(278);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(227); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(226);
						identifierReference();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(229); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(232); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(231);
						stringValue();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(234); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(237); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(236);
						tupleValue();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(239); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(242); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(241);
					booleanValue();
					}
					}
					setState(244); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==BOOLEAN_VALUE );
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(247); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(246);
						identifierReference();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(249); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(252); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(251);
					integerValue();
					}
					}
					setState(254); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NATURAL_VALUE || _la==NEGATIVE_VALUE );
				setState(257);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
				case 1:
					{
					setState(256);
					metric();
					}
					break;
				}
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(260); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(259);
					doubleValue();
					}
					}
					setState(262); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NATURAL_VALUE) | (1L << NEGATIVE_VALUE) | (1L << DOUBLE_VALUE))) != 0) );
				setState(265);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
				case 1:
					{
					setState(264);
					metric();
					}
					break;
				}
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(268); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(267);
						expression();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(270); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,35,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(273); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(272);
					methodReference();
					}
					}
					setState(275); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==AT );
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(277);
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
		public List<NodeReferenceContext> nodeReference() {
			return getRuleContexts(NodeReferenceContext.class);
		}
		public NodeReferenceContext nodeReference(int i) {
			return getRuleContext(NodeReferenceContext.class,i);
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
		enterRule(_localctx, 26, RULE_body);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(280);
			match(NEW_LINE_INDENT);
			setState(292); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(285);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
				case 1:
					{
					setState(281);
					variable();
					}
					break;
				case 2:
					{
					setState(282);
					node();
					}
					break;
				case 3:
					{
					setState(283);
					varInit();
					}
					break;
				case 4:
					{
					setState(284);
					nodeReference();
					}
					break;
				}
				setState(288); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(287);
					match(NEWLINE);
					}
					}
					setState(290); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NEWLINE );
				}
				}
				setState(294); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SUB) | (1L << VAR) | (1L << HAS))) != 0) || _la==IDENTIFIER || _la==DOC );
			setState(296);
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

	public static class FacetTargetContext extends ParserRuleContext {
		public TerminalNode ON() { return getToken(TaraGrammar.ON, 0); }
		public IdentifierReferenceContext identifierReference() {
			return getRuleContext(IdentifierReferenceContext.class,0);
		}
		public TerminalNode ANY() { return getToken(TaraGrammar.ANY, 0); }
		public WithContext with() {
			return getRuleContext(WithContext.class,0);
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
			setState(298);
			match(ON);
			setState(301);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				{
				setState(299);
				identifierReference();
				}
				break;
			case ANY:
				{
				setState(300);
				match(ANY);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(304);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WITH) {
				{
				setState(303);
				with();
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
		public List<RuleContainerContext> ruleContainer() {
			return getRuleContexts(RuleContainerContext.class);
		}
		public RuleContainerContext ruleContainer(int i) {
			return getRuleContext(RuleContainerContext.class,i);
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
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(306);
			match(HAS);
			setState(310);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COLON) {
				{
				{
				setState(307);
				ruleContainer();
				}
				}
				setState(312);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(313);
			identifierReference();
			setState(314);
			tags();
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

	public static class WithContext extends ParserRuleContext {
		public TerminalNode WITH() { return getToken(TaraGrammar.WITH, 0); }
		public List<IdentifierReferenceContext> identifierReference() {
			return getRuleContexts(IdentifierReferenceContext.class);
		}
		public IdentifierReferenceContext identifierReference(int i) {
			return getRuleContext(IdentifierReferenceContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(TaraGrammar.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(TaraGrammar.COMMA, i);
		}
		public WithContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_with; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterWith(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitWith(this);
		}
	}

	public final WithContext with() throws RecognitionException {
		WithContext _localctx = new WithContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_with);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(316);
			match(WITH);
			setState(317);
			identifierReference();
			setState(322);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(318);
				match(COMMA);
				setState(319);
				identifierReference();
				}
				}
				setState(324);
				_errHandler.sync(this);
				_la = _input.LA(1);
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
		public DocContext doc() {
			return getRuleContext(DocContext.class,0);
		}
		public SizeContext size() {
			return getRuleContext(SizeContext.class,0);
		}
		public RuleContainerContext ruleContainer() {
			return getRuleContext(RuleContainerContext.class,0);
		}
		public TerminalNode EQUALS() { return getToken(TaraGrammar.EQUALS, 0); }
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public FlagsContext flags() {
			return getRuleContext(FlagsContext.class,0);
		}
		public BodyValueContext bodyValue() {
			return getRuleContext(BodyValueContext.class,0);
		}
		public MetricContext metric() {
			return getRuleContext(MetricContext.class,0);
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
		enterRule(_localctx, 34, RULE_variable);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(326);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DOC) {
				{
				setState(325);
				doc();
				}
			}

			setState(328);
			match(VAR);
			setState(329);
			variableType();
			setState(331);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LEFT_SQUARE) {
				{
				setState(330);
				size();
				}
			}

			setState(334);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(333);
				ruleContainer();
				}
			}

			setState(336);
			match(IDENTIFIER);
			setState(342);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==EQUALS) {
				{
				setState(337);
				match(EQUALS);
				setState(338);
				value();
				setState(340);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IDENTIFIER || _la==MEASURE_VALUE) {
					{
					setState(339);
					metric();
					}
				}

				}
			}

			setState(345);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IS) {
				{
				setState(344);
				flags();
				}
			}

			setState(348);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NEW_LINE_INDENT) {
				{
				setState(347);
				bodyValue();
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

	public static class BodyValueContext extends ParserRuleContext {
		public TerminalNode NEW_LINE_INDENT() { return getToken(TaraGrammar.NEW_LINE_INDENT, 0); }
		public TerminalNode DEDENT() { return getToken(TaraGrammar.DEDENT, 0); }
		public StringValueContext stringValue() {
			return getRuleContext(StringValueContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode NEWLINE() { return getToken(TaraGrammar.NEWLINE, 0); }
		public BodyValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bodyValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterBodyValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitBodyValue(this);
		}
	}

	public final BodyValueContext bodyValue() throws RecognitionException {
		BodyValueContext _localctx = new BodyValueContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_bodyValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(350);
			match(NEW_LINE_INDENT);
			setState(353);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,52,_ctx) ) {
			case 1:
				{
				setState(351);
				stringValue();
				}
				break;
			case 2:
				{
				setState(352);
				expression();
				}
				break;
			}
			setState(356);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NEWLINE) {
				{
				setState(355);
				match(NEWLINE);
				}
			}

			setState(358);
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

	public static class VariableTypeContext extends ParserRuleContext {
		public TerminalNode INT_TYPE() { return getToken(TaraGrammar.INT_TYPE, 0); }
		public TerminalNode DOUBLE_TYPE() { return getToken(TaraGrammar.DOUBLE_TYPE, 0); }
		public TerminalNode BOOLEAN_TYPE() { return getToken(TaraGrammar.BOOLEAN_TYPE, 0); }
		public TerminalNode STRING_TYPE() { return getToken(TaraGrammar.STRING_TYPE, 0); }
		public TerminalNode FUNCTION_TYPE() { return getToken(TaraGrammar.FUNCTION_TYPE, 0); }
		public TerminalNode OBJECT_TYPE() { return getToken(TaraGrammar.OBJECT_TYPE, 0); }
		public TerminalNode WORD() { return getToken(TaraGrammar.WORD, 0); }
		public TerminalNode DATE_TYPE() { return getToken(TaraGrammar.DATE_TYPE, 0); }
		public TerminalNode INSTANT_TYPE() { return getToken(TaraGrammar.INSTANT_TYPE, 0); }
		public TerminalNode TIME_TYPE() { return getToken(TaraGrammar.TIME_TYPE, 0); }
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
		enterRule(_localctx, 38, RULE_variableType);
		try {
			setState(372);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT_TYPE:
				enterOuterAlt(_localctx, 1);
				{
				setState(360);
				match(INT_TYPE);
				}
				break;
			case DOUBLE_TYPE:
				enterOuterAlt(_localctx, 2);
				{
				setState(361);
				match(DOUBLE_TYPE);
				}
				break;
			case BOOLEAN_TYPE:
				enterOuterAlt(_localctx, 3);
				{
				setState(362);
				match(BOOLEAN_TYPE);
				}
				break;
			case STRING_TYPE:
				enterOuterAlt(_localctx, 4);
				{
				setState(363);
				match(STRING_TYPE);
				}
				break;
			case FUNCTION_TYPE:
				enterOuterAlt(_localctx, 5);
				{
				setState(364);
				match(FUNCTION_TYPE);
				}
				break;
			case OBJECT_TYPE:
				enterOuterAlt(_localctx, 6);
				{
				setState(365);
				match(OBJECT_TYPE);
				}
				break;
			case WORD:
				enterOuterAlt(_localctx, 7);
				{
				setState(366);
				match(WORD);
				}
				break;
			case DATE_TYPE:
				enterOuterAlt(_localctx, 8);
				{
				setState(367);
				match(DATE_TYPE);
				}
				break;
			case INSTANT_TYPE:
				enterOuterAlt(_localctx, 9);
				{
				setState(368);
				match(INSTANT_TYPE);
				}
				break;
			case TIME_TYPE:
				enterOuterAlt(_localctx, 10);
				{
				setState(369);
				match(TIME_TYPE);
				}
				break;
			case RESOURCE:
				enterOuterAlt(_localctx, 11);
				{
				setState(370);
				match(RESOURCE);
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 12);
				{
				setState(371);
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

	public static class RuleContainerContext extends ParserRuleContext {
		public TerminalNode COLON() { return getToken(TaraGrammar.COLON, 0); }
		public RuleValueContext ruleValue() {
			return getRuleContext(RuleValueContext.class,0);
		}
		public RuleContainerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleContainer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterRuleContainer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitRuleContainer(this);
		}
	}

	public final RuleContainerContext ruleContainer() throws RecognitionException {
		RuleContainerContext _localctx = new RuleContainerContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_ruleContainer);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(374);
			match(COLON);
			setState(375);
			ruleValue();
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

	public static class RuleValueContext extends ParserRuleContext {
		public TerminalNode LEFT_CURLY() { return getToken(TaraGrammar.LEFT_CURLY, 0); }
		public TerminalNode RIGHT_CURLY() { return getToken(TaraGrammar.RIGHT_CURLY, 0); }
		public ClassTypeContext classType() {
			return getRuleContext(ClassTypeContext.class,0);
		}
		public MetricContext metric() {
			return getRuleContext(MetricContext.class,0);
		}
		public List<TerminalNode> IDENTIFIER() { return getTokens(TaraGrammar.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(TaraGrammar.IDENTIFIER, i);
		}
		public RangeContext range() {
			return getRuleContext(RangeContext.class,0);
		}
		public StringValueContext stringValue() {
			return getRuleContext(StringValueContext.class,0);
		}
		public IdentifierReferenceContext identifierReference() {
			return getRuleContext(IdentifierReferenceContext.class,0);
		}
		public RuleValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterRuleValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitRuleValue(this);
		}
	}

	public final RuleValueContext ruleValue() throws RecognitionException {
		RuleValueContext _localctx = new RuleValueContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_ruleValue);
		int _la;
		try {
			setState(396);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LEFT_CURLY:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(377);
				match(LEFT_CURLY);
				setState(392);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,58,_ctx) ) {
				case 1:
					{
					setState(378);
					classType();
					}
					break;
				case 2:
					{
					setState(380); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(379);
						match(IDENTIFIER);
						}
						}
						setState(382); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==IDENTIFIER );
					}
					break;
				case 3:
					{
					{
					setState(386);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case STAR:
					case NATURAL_VALUE:
					case NEGATIVE_VALUE:
					case DOUBLE_VALUE:
						{
						setState(384);
						range();
						}
						break;
					case STRING:
					case NEWLINE:
						{
						setState(385);
						stringValue();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(389);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==IDENTIFIER || _la==MEASURE_VALUE) {
						{
						setState(388);
						metric();
						}
					}

					}
					}
					break;
				case 4:
					{
					setState(391);
					metric();
					}
					break;
				}
				setState(394);
				match(RIGHT_CURLY);
				}
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(395);
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

	public static class ClassTypeContext extends ParserRuleContext {
		public TerminalNode CLASS_TYPE() { return getToken(TaraGrammar.CLASS_TYPE, 0); }
		public ClassTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterClassType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitClassType(this);
		}
	}

	public final ClassTypeContext classType() throws RecognitionException {
		ClassTypeContext _localctx = new ClassTypeContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_classType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(398);
			match(CLASS_TYPE);
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

	public static class RangeContext extends ParserRuleContext {
		public List<TerminalNode> DOT() { return getTokens(TaraGrammar.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(TaraGrammar.DOT, i);
		}
		public List<DoubleValueContext> doubleValue() {
			return getRuleContexts(DoubleValueContext.class);
		}
		public DoubleValueContext doubleValue(int i) {
			return getRuleContext(DoubleValueContext.class,i);
		}
		public List<IntegerValueContext> integerValue() {
			return getRuleContexts(IntegerValueContext.class);
		}
		public IntegerValueContext integerValue(int i) {
			return getRuleContext(IntegerValueContext.class,i);
		}
		public List<TerminalNode> STAR() { return getTokens(TaraGrammar.STAR); }
		public TerminalNode STAR(int i) {
			return getToken(TaraGrammar.STAR, i);
		}
		public RangeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_range; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterRange(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitRange(this);
		}
	}

	public final RangeContext range() throws RecognitionException {
		RangeContext _localctx = new RangeContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_range);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(403);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,60,_ctx) ) {
			case 1:
				{
				setState(400);
				doubleValue();
				}
				break;
			case 2:
				{
				setState(401);
				integerValue();
				}
				break;
			case 3:
				{
				setState(402);
				match(STAR);
				}
				break;
			}
			setState(405);
			match(DOT);
			setState(406);
			match(DOT);
			setState(410);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,61,_ctx) ) {
			case 1:
				{
				setState(407);
				doubleValue();
				}
				break;
			case 2:
				{
				setState(408);
				integerValue();
				}
				break;
			case 3:
				{
				setState(409);
				match(STAR);
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

	public static class SizeContext extends ParserRuleContext {
		public TerminalNode LEFT_SQUARE() { return getToken(TaraGrammar.LEFT_SQUARE, 0); }
		public TerminalNode RIGHT_SQUARE() { return getToken(TaraGrammar.RIGHT_SQUARE, 0); }
		public SizeRangeContext sizeRange() {
			return getRuleContext(SizeRangeContext.class,0);
		}
		public SizeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_size; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterSize(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitSize(this);
		}
	}

	public final SizeContext size() throws RecognitionException {
		SizeContext _localctx = new SizeContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_size);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(412);
			match(LEFT_SQUARE);
			setState(414);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==STAR || _la==NATURAL_VALUE) {
				{
				setState(413);
				sizeRange();
				}
			}

			setState(416);
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

	public static class SizeRangeContext extends ParserRuleContext {
		public TerminalNode NATURAL_VALUE() { return getToken(TaraGrammar.NATURAL_VALUE, 0); }
		public ListRangeContext listRange() {
			return getRuleContext(ListRangeContext.class,0);
		}
		public SizeRangeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sizeRange; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterSizeRange(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitSizeRange(this);
		}
	}

	public final SizeRangeContext sizeRange() throws RecognitionException {
		SizeRangeContext _localctx = new SizeRangeContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_sizeRange);
		try {
			setState(420);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,63,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(418);
				match(NATURAL_VALUE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(419);
				listRange();
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

	public static class ListRangeContext extends ParserRuleContext {
		public List<TerminalNode> DOT() { return getTokens(TaraGrammar.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(TaraGrammar.DOT, i);
		}
		public List<TerminalNode> NATURAL_VALUE() { return getTokens(TaraGrammar.NATURAL_VALUE); }
		public TerminalNode NATURAL_VALUE(int i) {
			return getToken(TaraGrammar.NATURAL_VALUE, i);
		}
		public List<TerminalNode> STAR() { return getTokens(TaraGrammar.STAR); }
		public TerminalNode STAR(int i) {
			return getToken(TaraGrammar.STAR, i);
		}
		public ListRangeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listRange; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterListRange(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitListRange(this);
		}
	}

	public final ListRangeContext listRange() throws RecognitionException {
		ListRangeContext _localctx = new ListRangeContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_listRange);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(422);
			_la = _input.LA(1);
			if ( !(_la==STAR || _la==NATURAL_VALUE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(423);
			match(DOT);
			setState(424);
			match(DOT);
			setState(425);
			_la = _input.LA(1);
			if ( !(_la==STAR || _la==NATURAL_VALUE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static class MethodReferenceContext extends ParserRuleContext {
		public TerminalNode AT() { return getToken(TaraGrammar.AT, 0); }
		public IdentifierReferenceContext identifierReference() {
			return getRuleContext(IdentifierReferenceContext.class,0);
		}
		public MethodReferenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodReference; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterMethodReference(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitMethodReference(this);
		}
	}

	public final MethodReferenceContext methodReference() throws RecognitionException {
		MethodReferenceContext _localctx = new MethodReferenceContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_methodReference);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(427);
			match(AT);
			setState(428);
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

	public static class StringValueContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(TaraGrammar.STRING, 0); }
		public TerminalNode NEWLINE() { return getToken(TaraGrammar.NEWLINE, 0); }
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
		enterRule(_localctx, 56, RULE_stringValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(431);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NEWLINE) {
				{
				setState(430);
				match(NEWLINE);
				}
			}

			{
			setState(433);
			match(STRING);
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
		enterRule(_localctx, 58, RULE_booleanValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(435);
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

	public static class TupleValueContext extends ParserRuleContext {
		public StringValueContext stringValue() {
			return getRuleContext(StringValueContext.class,0);
		}
		public TerminalNode COLON() { return getToken(TaraGrammar.COLON, 0); }
		public DoubleValueContext doubleValue() {
			return getRuleContext(DoubleValueContext.class,0);
		}
		public TupleValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tupleValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterTupleValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitTupleValue(this);
		}
	}

	public final TupleValueContext tupleValue() throws RecognitionException {
		TupleValueContext _localctx = new TupleValueContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_tupleValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(437);
			stringValue();
			setState(438);
			match(COLON);
			setState(439);
			doubleValue();
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
		enterRule(_localctx, 62, RULE_integerValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(441);
			_la = _input.LA(1);
			if ( !(_la==NATURAL_VALUE || _la==NEGATIVE_VALUE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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
		enterRule(_localctx, 64, RULE_doubleValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(443);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NATURAL_VALUE) | (1L << NEGATIVE_VALUE) | (1L << DOUBLE_VALUE))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(445);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SCIENCE_NOT) {
				{
				setState(444);
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

	public static class MetricContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
		public TerminalNode MEASURE_VALUE() { return getToken(TaraGrammar.MEASURE_VALUE, 0); }
		public MetricContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_metric; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterMetric(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitMetric(this);
		}
	}

	public final MetricContext metric() throws RecognitionException {
		MetricContext _localctx = new MetricContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_metric);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(447);
			_la = _input.LA(1);
			if ( !(_la==IDENTIFIER || _la==MEASURE_VALUE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static class ExpressionContext extends ParserRuleContext {
		public TerminalNode EXPRESSION_BEGIN() { return getToken(TaraGrammar.EXPRESSION_BEGIN, 0); }
		public TerminalNode EXPRESSION_END() { return getToken(TaraGrammar.EXPRESSION_END, 0); }
		public TerminalNode NEWLINE() { return getToken(TaraGrammar.NEWLINE, 0); }
		public List<TerminalNode> CHARACTER() { return getTokens(TaraGrammar.CHARACTER); }
		public TerminalNode CHARACTER(int i) {
			return getToken(TaraGrammar.CHARACTER, i);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(450);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NEWLINE) {
				{
				setState(449);
				match(NEWLINE);
				}
			}

			{
			setState(452);
			match(EXPRESSION_BEGIN);
			setState(456);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==CHARACTER) {
				{
				{
				setState(453);
				match(CHARACTER);
				}
				}
				setState(458);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(459);
			match(EXPRESSION_END);
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
		enterRule(_localctx, 70, RULE_tags);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(462);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IS) {
				{
				setState(461);
				flags();
				}
			}

			setState(465);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==INTO) {
				{
				setState(464);
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
		enterRule(_localctx, 72, RULE_annotations);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(467);
			match(INTO);
			setState(469); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(468);
				annotation();
				}
				}
				setState(471); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << COMPONENT) | (1L << FEATURE) | (1L << ENCLOSED))) != 0) );
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
		public TerminalNode COMPONENT() { return getToken(TaraGrammar.COMPONENT, 0); }
		public TerminalNode FEATURE() { return getToken(TaraGrammar.FEATURE, 0); }
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
		enterRule(_localctx, 74, RULE_annotation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(473);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << COMPONENT) | (1L << FEATURE) | (1L << ENCLOSED))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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
		enterRule(_localctx, 76, RULE_flags);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(475);
			match(IS);
			setState(477); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(476);
				flag();
				}
				}
				setState(479); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << CONCEPT) | (1L << ABSTRACT) | (1L << TERMINAL) | (1L << COMPONENT) | (1L << FEATURE) | (1L << FINAL) | (1L << ENCLOSED) | (1L << PRIVATE) | (1L << REACTIVE) | (1L << VOLATILE) | (1L << DECORABLE))) != 0) );
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
		public TerminalNode COMPONENT() { return getToken(TaraGrammar.COMPONENT, 0); }
		public TerminalNode PRIVATE() { return getToken(TaraGrammar.PRIVATE, 0); }
		public TerminalNode FEATURE() { return getToken(TaraGrammar.FEATURE, 0); }
		public TerminalNode ENCLOSED() { return getToken(TaraGrammar.ENCLOSED, 0); }
		public TerminalNode FINAL() { return getToken(TaraGrammar.FINAL, 0); }
		public TerminalNode CONCEPT() { return getToken(TaraGrammar.CONCEPT, 0); }
		public TerminalNode REACTIVE() { return getToken(TaraGrammar.REACTIVE, 0); }
		public TerminalNode VOLATILE() { return getToken(TaraGrammar.VOLATILE, 0); }
		public TerminalNode DECORABLE() { return getToken(TaraGrammar.DECORABLE, 0); }
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
		enterRule(_localctx, 78, RULE_flag);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(481);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << CONCEPT) | (1L << ABSTRACT) | (1L << TERMINAL) | (1L << COMPONENT) | (1L << FEATURE) | (1L << FINAL) | (1L << ENCLOSED) | (1L << PRIVATE) | (1L << REACTIVE) | (1L << VOLATILE) | (1L << DECORABLE))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static class VarInitContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
		public BodyValueContext bodyValue() {
			return getRuleContext(BodyValueContext.class,0);
		}
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
		enterRule(_localctx, 80, RULE_varInit);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(483);
			match(IDENTIFIER);
			setState(487);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case EQUALS:
				{
				{
				setState(484);
				match(EQUALS);
				setState(485);
				value();
				}
				}
				break;
			case NEW_LINE_INDENT:
				{
				setState(486);
				bodyValue();
				}
				break;
			default:
				throw new NoViableAltException(this);
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
		enterRule(_localctx, 82, RULE_headerReference);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(492);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,73,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(489);
					hierarchy();
					}
					} 
				}
				setState(494);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,73,_ctx);
			}
			setState(495);
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
		enterRule(_localctx, 84, RULE_identifierReference);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(500);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,74,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(497);
					hierarchy();
					}
					} 
				}
				setState(502);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,74,_ctx);
			}
			setState(503);
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
		public TerminalNode PLUS() { return getToken(TaraGrammar.PLUS, 0); }
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
		enterRule(_localctx, 86, RULE_hierarchy);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(505);
			match(IDENTIFIER);
			setState(506);
			_la = _input.LA(1);
			if ( !(_la==DOT || _la==PLUS) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static class MetaidentifierContext extends ParserRuleContext {
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
		enterRule(_localctx, 88, RULE_metaidentifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(508);
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\\\u0201\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\3\2\7\2^\n\2\f\2\16\2a\13\2\3\2\5\2d\n\2\3\2\5\2g\n\2"+
		"\3\2\3\2\7\2k\n\2\f\2\16\2n\13\2\7\2p\n\2\f\2\16\2s\13\2\3\2\3\2\3\3\3"+
		"\3\3\3\7\3z\n\3\f\3\16\3}\13\3\3\4\6\4\u0080\n\4\r\4\16\4\u0081\3\5\3"+
		"\5\3\5\6\5\u0087\n\5\r\5\16\5\u0088\3\6\6\6\u008c\n\6\r\6\16\6\u008d\3"+
		"\7\5\7\u0091\n\7\3\7\3\7\5\7\u0095\n\7\3\b\3\b\7\b\u0099\n\b\f\b\16\b"+
		"\u009c\13\b\3\b\5\b\u009f\n\b\3\b\3\b\7\b\u00a3\n\b\f\b\16\b\u00a6\13"+
		"\b\3\b\3\b\7\b\u00aa\n\b\f\b\16\b\u00ad\13\b\3\b\5\b\u00b0\n\b\3\b\5\b"+
		"\u00b3\n\b\3\b\7\b\u00b6\n\b\f\b\16\b\u00b9\13\b\3\b\5\b\u00bc\n\b\5\b"+
		"\u00be\n\b\3\b\5\b\u00c1\n\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\n\7\n\u00cc"+
		"\n\n\f\n\16\n\u00cf\13\n\5\n\u00d1\n\n\3\n\3\n\3\13\3\13\5\13\u00d7\n"+
		"\13\3\13\3\13\3\f\3\f\6\f\u00dd\n\f\r\f\16\f\u00de\3\r\3\r\5\r\u00e3\n"+
		"\r\3\16\6\16\u00e6\n\16\r\16\16\16\u00e7\3\16\6\16\u00eb\n\16\r\16\16"+
		"\16\u00ec\3\16\6\16\u00f0\n\16\r\16\16\16\u00f1\3\16\6\16\u00f5\n\16\r"+
		"\16\16\16\u00f6\3\16\6\16\u00fa\n\16\r\16\16\16\u00fb\3\16\6\16\u00ff"+
		"\n\16\r\16\16\16\u0100\3\16\5\16\u0104\n\16\3\16\6\16\u0107\n\16\r\16"+
		"\16\16\u0108\3\16\5\16\u010c\n\16\3\16\6\16\u010f\n\16\r\16\16\16\u0110"+
		"\3\16\6\16\u0114\n\16\r\16\16\16\u0115\3\16\5\16\u0119\n\16\3\17\3\17"+
		"\3\17\3\17\3\17\5\17\u0120\n\17\3\17\6\17\u0123\n\17\r\17\16\17\u0124"+
		"\6\17\u0127\n\17\r\17\16\17\u0128\3\17\3\17\3\20\3\20\3\20\5\20\u0130"+
		"\n\20\3\20\5\20\u0133\n\20\3\21\3\21\7\21\u0137\n\21\f\21\16\21\u013a"+
		"\13\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\7\22\u0143\n\22\f\22\16\22\u0146"+
		"\13\22\3\23\5\23\u0149\n\23\3\23\3\23\3\23\5\23\u014e\n\23\3\23\5\23\u0151"+
		"\n\23\3\23\3\23\3\23\3\23\5\23\u0157\n\23\5\23\u0159\n\23\3\23\5\23\u015c"+
		"\n\23\3\23\5\23\u015f\n\23\3\24\3\24\3\24\5\24\u0164\n\24\3\24\5\24\u0167"+
		"\n\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\5\25\u0177\n\25\3\26\3\26\3\26\3\27\3\27\3\27\6\27\u017f\n\27\r"+
		"\27\16\27\u0180\3\27\3\27\5\27\u0185\n\27\3\27\5\27\u0188\n\27\3\27\5"+
		"\27\u018b\n\27\3\27\3\27\5\27\u018f\n\27\3\30\3\30\3\31\3\31\3\31\5\31"+
		"\u0196\n\31\3\31\3\31\3\31\3\31\3\31\5\31\u019d\n\31\3\32\3\32\5\32\u01a1"+
		"\n\32\3\32\3\32\3\33\3\33\5\33\u01a7\n\33\3\34\3\34\3\34\3\34\3\34\3\35"+
		"\3\35\3\35\3\36\5\36\u01b2\n\36\3\36\3\36\3\37\3\37\3 \3 \3 \3 \3!\3!"+
		"\3\"\3\"\5\"\u01c0\n\"\3#\3#\3$\5$\u01c5\n$\3$\3$\7$\u01c9\n$\f$\16$\u01cc"+
		"\13$\3$\3$\3%\5%\u01d1\n%\3%\5%\u01d4\n%\3&\3&\6&\u01d8\n&\r&\16&\u01d9"+
		"\3\'\3\'\3(\3(\6(\u01e0\n(\r(\16(\u01e1\3)\3)\3*\3*\3*\3*\5*\u01ea\n*"+
		"\3+\7+\u01ed\n+\f+\16+\u01f0\13+\3+\3+\3,\7,\u01f5\n,\f,\16,\u01f8\13"+
		",\3,\3,\3-\3-\3-\3.\3.\3.\2\2/\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36"+
		" \"$&(*,.\60\62\64\668:<>@BDFHJLNPRTVXZ\2\t\4\2((<<\3\2<=\3\2<>\3\2DE"+
		"\4\2\22\23\25\25\3\2\17\31\4\2&&++\2\u0236\2_\3\2\2\2\4v\3\2\2\2\6\177"+
		"\3\2\2\2\b\u0083\3\2\2\2\n\u008b\3\2\2\2\f\u0090\3\2\2\2\16\u00bd\3\2"+
		"\2\2\20\u00c4\3\2\2\2\22\u00c7\3\2\2\2\24\u00d6\3\2\2\2\26\u00da\3\2\2"+
		"\2\30\u00e0\3\2\2\2\32\u0118\3\2\2\2\34\u011a\3\2\2\2\36\u012c\3\2\2\2"+
		" \u0134\3\2\2\2\"\u013e\3\2\2\2$\u0148\3\2\2\2&\u0160\3\2\2\2(\u0176\3"+
		"\2\2\2*\u0178\3\2\2\2,\u018e\3\2\2\2.\u0190\3\2\2\2\60\u0195\3\2\2\2\62"+
		"\u019e\3\2\2\2\64\u01a6\3\2\2\2\66\u01a8\3\2\2\28\u01ad\3\2\2\2:\u01b1"+
		"\3\2\2\2<\u01b5\3\2\2\2>\u01b7\3\2\2\2@\u01bb\3\2\2\2B\u01bd\3\2\2\2D"+
		"\u01c1\3\2\2\2F\u01c4\3\2\2\2H\u01d0\3\2\2\2J\u01d5\3\2\2\2L\u01db\3\2"+
		"\2\2N\u01dd\3\2\2\2P\u01e3\3\2\2\2R\u01e5\3\2\2\2T\u01ee\3\2\2\2V\u01f6"+
		"\3\2\2\2X\u01fb\3\2\2\2Z\u01fe\3\2\2\2\\^\7F\2\2]\\\3\2\2\2^a\3\2\2\2"+
		"_]\3\2\2\2_`\3\2\2\2`c\3\2\2\2a_\3\2\2\2bd\5\4\3\2cb\3\2\2\2cd\3\2\2\2"+
		"df\3\2\2\2eg\5\6\4\2fe\3\2\2\2fg\3\2\2\2gq\3\2\2\2hl\5\f\7\2ik\7F\2\2"+
		"ji\3\2\2\2kn\3\2\2\2lj\3\2\2\2lm\3\2\2\2mp\3\2\2\2nl\3\2\2\2oh\3\2\2\2"+
		"ps\3\2\2\2qo\3\2\2\2qr\3\2\2\2rt\3\2\2\2sq\3\2\2\2tu\7\2\2\3u\3\3\2\2"+
		"\2vw\7\5\2\2w{\5T+\2xz\7F\2\2yx\3\2\2\2z}\3\2\2\2{y\3\2\2\2{|\3\2\2\2"+
		"|\5\3\2\2\2}{\3\2\2\2~\u0080\5\b\5\2\177~\3\2\2\2\u0080\u0081\3\2\2\2"+
		"\u0081\177\3\2\2\2\u0081\u0082\3\2\2\2\u0082\7\3\2\2\2\u0083\u0084\7\4"+
		"\2\2\u0084\u0086\5T+\2\u0085\u0087\7F\2\2\u0086\u0085\3\2\2\2\u0087\u0088"+
		"\3\2\2\2\u0088\u0086\3\2\2\2\u0088\u0089\3\2\2\2\u0089\t\3\2\2\2\u008a"+
		"\u008c\7H\2\2\u008b\u008a\3\2\2\2\u008c\u008d\3\2\2\2\u008d\u008b\3\2"+
		"\2\2\u008d\u008e\3\2\2\2\u008e\13\3\2\2\2\u008f\u0091\5\n\6\2\u0090\u008f"+
		"\3\2\2\2\u0090\u0091\3\2\2\2\u0091\u0092\3\2\2\2\u0092\u0094\5\16\b\2"+
		"\u0093\u0095\5\34\17\2\u0094\u0093\3\2\2\2\u0094\u0095\3\2\2\2\u0095\r"+
		"\3\2\2\2\u0096\u009a\7\3\2\2\u0097\u0099\5*\26\2\u0098\u0097\3\2\2\2\u0099"+
		"\u009c\3\2\2\2\u009a\u0098\3\2\2\2\u009a\u009b\3\2\2\2\u009b\u009e\3\2"+
		"\2\2\u009c\u009a\3\2\2\2\u009d\u009f\5\22\n\2\u009e\u009d\3\2\2\2\u009e"+
		"\u009f\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0\u00a4\7D\2\2\u00a1\u00a3\5\26"+
		"\f\2\u00a2\u00a1\3\2\2\2\u00a3\u00a6\3\2\2\2\u00a4\u00a2\3\2\2\2\u00a4"+
		"\u00a5\3\2\2\2\u00a5\u00be\3\2\2\2\u00a6\u00a4\3\2\2\2\u00a7\u00ab\5Z"+
		".\2\u00a8\u00aa\5*\26\2\u00a9\u00a8\3\2\2\2\u00aa\u00ad\3\2\2\2\u00ab"+
		"\u00a9\3\2\2\2\u00ab\u00ac\3\2\2\2\u00ac\u00af\3\2\2\2\u00ad\u00ab\3\2"+
		"\2\2\u00ae\u00b0\5\22\n\2\u00af\u00ae\3\2\2\2\u00af\u00b0\3\2\2\2\u00b0"+
		"\u00b2\3\2\2\2\u00b1\u00b3\7D\2\2\u00b2\u00b1\3\2\2\2\u00b2\u00b3\3\2"+
		"\2\2\u00b3\u00b7\3\2\2\2\u00b4\u00b6\5\26\f\2\u00b5\u00b4\3\2\2\2\u00b6"+
		"\u00b9\3\2\2\2\u00b7\u00b5\3\2\2\2\u00b7\u00b8\3\2\2\2\u00b8\u00bb\3\2"+
		"\2\2\u00b9\u00b7\3\2\2\2\u00ba\u00bc\5\20\t\2\u00bb\u00ba\3\2\2\2\u00bb"+
		"\u00bc\3\2\2\2\u00bc\u00be\3\2\2\2\u00bd\u0096\3\2\2\2\u00bd\u00a7\3\2"+
		"\2\2\u00be\u00c0\3\2\2\2\u00bf\u00c1\5\36\20\2\u00c0\u00bf\3\2\2\2\u00c0"+
		"\u00c1\3\2\2\2\u00c1\u00c2\3\2\2\2\u00c2\u00c3\5H%\2\u00c3\17\3\2\2\2"+
		"\u00c4\u00c5\7\16\2\2\u00c5\u00c6\5V,\2\u00c6\21\3\2\2\2\u00c7\u00d0\7"+
		"\32\2\2\u00c8\u00cd\5\24\13\2\u00c9\u00ca\7%\2\2\u00ca\u00cc\5\24\13\2"+
		"\u00cb\u00c9\3\2\2\2\u00cc\u00cf\3\2\2\2\u00cd\u00cb\3\2\2\2\u00cd\u00ce"+
		"\3\2\2\2\u00ce\u00d1\3\2\2\2\u00cf\u00cd\3\2\2\2\u00d0\u00c8\3\2\2\2\u00d0"+
		"\u00d1\3\2\2\2\u00d1\u00d2\3\2\2\2\u00d2\u00d3\7\33\2\2\u00d3\23\3\2\2"+
		"\2\u00d4\u00d5\7D\2\2\u00d5\u00d7\7\'\2\2\u00d6\u00d4\3\2\2\2\u00d6\u00d7"+
		"\3\2\2\2\u00d7\u00d8\3\2\2\2\u00d8\u00d9\5\32\16\2\u00d9\25\3\2\2\2\u00da"+
		"\u00dc\7\7\2\2\u00db\u00dd\5\30\r\2\u00dc\u00db\3\2\2\2\u00dd\u00de\3"+
		"\2\2\2\u00de\u00dc\3\2\2\2\u00de\u00df\3\2\2\2\u00df\27\3\2\2\2\u00e0"+
		"\u00e2\5Z.\2\u00e1\u00e3\5\22\n\2\u00e2\u00e1\3\2\2\2\u00e2\u00e3\3\2"+
		"\2\2\u00e3\31\3\2\2\2\u00e4\u00e6\5V,\2\u00e5\u00e4\3\2\2\2\u00e6\u00e7"+
		"\3\2\2\2\u00e7\u00e5\3\2\2\2\u00e7\u00e8\3\2\2\2\u00e8\u0119\3\2\2\2\u00e9"+
		"\u00eb\5:\36\2\u00ea\u00e9\3\2\2\2\u00eb\u00ec\3\2\2\2\u00ec\u00ea\3\2"+
		"\2\2\u00ec\u00ed\3\2\2\2\u00ed\u0119\3\2\2\2\u00ee\u00f0\5> \2\u00ef\u00ee"+
		"\3\2\2\2\u00f0\u00f1\3\2\2\2\u00f1\u00ef\3\2\2\2\u00f1\u00f2\3\2\2\2\u00f2"+
		"\u0119\3\2\2\2\u00f3\u00f5\5<\37\2\u00f4\u00f3\3\2\2\2\u00f5\u00f6\3\2"+
		"\2\2\u00f6\u00f4\3\2\2\2\u00f6\u00f7\3\2\2\2\u00f7\u0119\3\2\2\2\u00f8"+
		"\u00fa\5V,\2\u00f9\u00f8\3\2\2\2\u00fa\u00fb\3\2\2\2\u00fb\u00f9\3\2\2"+
		"\2\u00fb\u00fc\3\2\2\2\u00fc\u0119\3\2\2\2\u00fd\u00ff\5@!\2\u00fe\u00fd"+
		"\3\2\2\2\u00ff\u0100\3\2\2\2\u0100\u00fe\3\2\2\2\u0100\u0101\3\2\2\2\u0101"+
		"\u0103\3\2\2\2\u0102\u0104\5D#\2\u0103\u0102\3\2\2\2\u0103\u0104\3\2\2"+
		"\2\u0104\u0119\3\2\2\2\u0105\u0107\5B\"\2\u0106\u0105\3\2\2\2\u0107\u0108"+
		"\3\2\2\2\u0108\u0106\3\2\2\2\u0108\u0109\3\2\2\2\u0109\u010b\3\2\2\2\u010a"+
		"\u010c\5D#\2\u010b\u010a\3\2\2\2\u010b\u010c\3\2\2\2\u010c\u0119\3\2\2"+
		"\2\u010d\u010f\5F$\2\u010e\u010d\3\2\2\2\u010f\u0110\3\2\2\2\u0110\u010e"+
		"\3\2\2\2\u0110\u0111\3\2\2\2\u0111\u0119\3\2\2\2\u0112\u0114\58\35\2\u0113"+
		"\u0112\3\2\2\2\u0114\u0115\3\2\2\2\u0115\u0113\3\2\2\2\u0115\u0116\3\2"+
		"\2\2\u0116\u0119\3\2\2\2\u0117\u0119\7\67\2\2\u0118\u00e5\3\2\2\2\u0118"+
		"\u00ea\3\2\2\2\u0118\u00ef\3\2\2\2\u0118\u00f4\3\2\2\2\u0118\u00f9\3\2"+
		"\2\2\u0118\u00fe\3\2\2\2\u0118\u0106\3\2\2\2\u0118\u010e\3\2\2\2\u0118"+
		"\u0113\3\2\2\2\u0118\u0117\3\2\2\2\u0119\33\3\2\2\2\u011a\u0126\7K\2\2"+
		"\u011b\u0120\5$\23\2\u011c\u0120\5\f\7\2\u011d\u0120\5R*\2\u011e\u0120"+
		"\5 \21\2\u011f\u011b\3\2\2\2\u011f\u011c\3\2\2\2\u011f\u011d\3\2\2\2\u011f"+
		"\u011e\3\2\2\2\u0120\u0122\3\2\2\2\u0121\u0123\7F\2\2\u0122\u0121\3\2"+
		"\2\2\u0123\u0124\3\2\2\2\u0124\u0122\3\2\2\2\u0124\u0125\3\2\2\2\u0125"+
		"\u0127\3\2\2\2\u0126\u011f\3\2\2\2\u0127\u0128\3\2\2\2\u0128\u0126\3\2"+
		"\2\2\u0128\u0129\3\2\2\2\u0129\u012a\3\2\2\2\u012a\u012b\7L\2\2\u012b"+
		"\35\3\2\2\2\u012c\u012f\7\t\2\2\u012d\u0130\5V,\2\u012e\u0130\7\r\2\2"+
		"\u012f\u012d\3\2\2\2\u012f\u012e\3\2\2\2\u0130\u0132\3\2\2\2\u0131\u0133"+
		"\5\"\22\2\u0132\u0131\3\2\2\2\u0132\u0133\3\2\2\2\u0133\37\3\2\2\2\u0134"+
		"\u0138\7\b\2\2\u0135\u0137\5*\26\2\u0136\u0135\3\2\2\2\u0137\u013a\3\2"+
		"\2\2\u0138\u0136\3\2\2\2\u0138\u0139\3\2\2\2\u0139\u013b\3\2\2\2\u013a"+
		"\u0138\3\2\2\2\u013b\u013c\5V,\2\u013c\u013d\5H%\2\u013d!\3\2\2\2\u013e"+
		"\u013f\7\f\2\2\u013f\u0144\5V,\2\u0140\u0141\7%\2\2\u0141\u0143\5V,\2"+
		"\u0142\u0140\3\2\2\2\u0143\u0146\3\2\2\2\u0144\u0142\3\2\2\2\u0144\u0145"+
		"\3\2\2\2\u0145#\3\2\2\2\u0146\u0144\3\2\2\2\u0147\u0149\5\n\6\2\u0148"+
		"\u0147\3\2\2\2\u0148\u0149\3\2\2\2\u0149\u014a\3\2\2\2\u014a\u014b\7\6"+
		"\2\2\u014b\u014d\5(\25\2\u014c\u014e\5\62\32\2\u014d\u014c\3\2\2\2\u014d"+
		"\u014e\3\2\2\2\u014e\u0150\3\2\2\2\u014f\u0151\5*\26\2\u0150\u014f\3\2"+
		"\2\2\u0150\u0151\3\2\2\2\u0151\u0152\3\2\2\2\u0152\u0158\7D\2\2\u0153"+
		"\u0154\7\'\2\2\u0154\u0156\5\32\16\2\u0155\u0157\5D#\2\u0156\u0155\3\2"+
		"\2\2\u0156\u0157\3\2\2\2\u0157\u0159\3\2\2\2\u0158\u0153\3\2\2\2\u0158"+
		"\u0159\3\2\2\2\u0159\u015b\3\2\2\2\u015a\u015c\5N(\2\u015b\u015a\3\2\2"+
		"\2\u015b\u015c\3\2\2\2\u015c\u015e\3\2\2\2\u015d\u015f\5&\24\2\u015e\u015d"+
		"\3\2\2\2\u015e\u015f\3\2\2\2\u015f%\3\2\2\2\u0160\u0163\7K\2\2\u0161\u0164"+
		"\5:\36\2\u0162\u0164\5F$\2\u0163\u0161\3\2\2\2\u0163\u0162\3\2\2\2\u0164"+
		"\u0166\3\2\2\2\u0165\u0167\7F\2\2\u0166\u0165\3\2\2\2\u0166\u0167\3\2"+
		"\2\2\u0167\u0168\3\2\2\2\u0168\u0169\7L\2\2\u0169\'\3\2\2\2\u016a\u0177"+
		"\7.\2\2\u016b\u0177\7\61\2\2\u016c\u0177\7\63\2\2\u016d\u0177\7\62\2\2"+
		"\u016e\u0177\7/\2\2\u016f\u0177\7\60\2\2\u0170\u0177\7,\2\2\u0171\u0177"+
		"\7\64\2\2\u0172\u0177\7\65\2\2\u0173\u0177\7\66\2\2\u0174\u0177\7-\2\2"+
		"\u0175\u0177\5V,\2\u0176\u016a\3\2\2\2\u0176\u016b\3\2\2\2\u0176\u016c"+
		"\3\2\2\2\u0176\u016d\3\2\2\2\u0176\u016e\3\2\2\2\u0176\u016f\3\2\2\2\u0176"+
		"\u0170\3\2\2\2\u0176\u0171\3\2\2\2\u0176\u0172\3\2\2\2\u0176\u0173\3\2"+
		"\2\2\u0176\u0174\3\2\2\2\u0176\u0175\3\2\2\2\u0177)\3\2\2\2\u0178\u0179"+
		"\7$\2\2\u0179\u017a\5,\27\2\u017a+\3\2\2\2\u017b\u018a\7\36\2\2\u017c"+
		"\u018b\5.\30\2\u017d\u017f\7D\2\2\u017e\u017d\3\2\2\2\u017f\u0180\3\2"+
		"\2\2\u0180\u017e\3\2\2\2\u0180\u0181\3\2\2\2\u0181\u018b\3\2\2\2\u0182"+
		"\u0185\5\60\31\2\u0183\u0185\5:\36\2\u0184\u0182\3\2\2\2\u0184\u0183\3"+
		"\2\2\2\u0185\u0187\3\2\2\2\u0186\u0188\5D#\2\u0187\u0186\3\2\2\2\u0187"+
		"\u0188\3\2\2\2\u0188\u018b\3\2\2\2\u0189\u018b\5D#\2\u018a\u017c\3\2\2"+
		"\2\u018a\u017e\3\2\2\2\u018a\u0184\3\2\2\2\u018a\u0189\3\2\2\2\u018b\u018c"+
		"\3\2\2\2\u018c\u018f\7\37\2\2\u018d\u018f\5V,\2\u018e\u017b\3\2\2\2\u018e"+
		"\u018d\3\2\2\2\u018f-\3\2\2\2\u0190\u0191\7C\2\2\u0191/\3\2\2\2\u0192"+
		"\u0196\5B\"\2\u0193\u0196\5@!\2\u0194\u0196\7(\2\2\u0195\u0192\3\2\2\2"+
		"\u0195\u0193\3\2\2\2\u0195\u0194\3\2\2\2\u0196\u0197\3\2\2\2\u0197\u0198"+
		"\7&\2\2\u0198\u019c\7&\2\2\u0199\u019d\5B\"\2\u019a\u019d\5@!\2\u019b"+
		"\u019d\7(\2\2\u019c\u0199\3\2\2\2\u019c\u019a\3\2\2\2\u019c\u019b\3\2"+
		"\2\2\u019d\61\3\2\2\2\u019e\u01a0\7\34\2\2\u019f\u01a1\5\64\33\2\u01a0"+
		"\u019f\3\2\2\2\u01a0\u01a1\3\2\2\2\u01a1\u01a2\3\2\2\2\u01a2\u01a3\7\35"+
		"\2\2\u01a3\63\3\2\2\2\u01a4\u01a7\7<\2\2\u01a5\u01a7\5\66\34\2\u01a6\u01a4"+
		"\3\2\2\2\u01a6\u01a5\3\2\2\2\u01a7\65\3\2\2\2\u01a8\u01a9\t\2\2\2\u01a9"+
		"\u01aa\7&\2\2\u01aa\u01ab\7&\2\2\u01ab\u01ac\t\2\2\2\u01ac\67\3\2\2\2"+
		"\u01ad\u01ae\7\"\2\2\u01ae\u01af\5V,\2\u01af9\3\2\2\2\u01b0\u01b2\7F\2"+
		"\2\u01b1\u01b0\3\2\2\2\u01b1\u01b2\3\2\2\2\u01b2\u01b3\3\2\2\2\u01b3\u01b4"+
		"\7?\2\2\u01b4;\3\2\2\2\u01b5\u01b6\7;\2\2\u01b6=\3\2\2\2\u01b7\u01b8\5"+
		":\36\2\u01b8\u01b9\7$\2\2\u01b9\u01ba\5B\"\2\u01ba?\3\2\2\2\u01bb\u01bc"+
		"\t\3\2\2\u01bcA\3\2\2\2\u01bd\u01bf\t\4\2\2\u01be\u01c0\7:\2\2\u01bf\u01be"+
		"\3\2\2\2\u01bf\u01c0\3\2\2\2\u01c0C\3\2\2\2\u01c1\u01c2\t\5\2\2\u01c2"+
		"E\3\2\2\2\u01c3\u01c5\7F\2\2\u01c4\u01c3\3\2\2\2\u01c4\u01c5\3\2\2\2\u01c5"+
		"\u01c6\3\2\2\2\u01c6\u01ca\7[\2\2\u01c7\u01c9\7P\2\2\u01c8\u01c7\3\2\2"+
		"\2\u01c9\u01cc\3\2\2\2\u01ca\u01c8\3\2\2\2\u01ca\u01cb\3\2\2\2\u01cb\u01cd"+
		"\3\2\2\2\u01cc\u01ca\3\2\2\2\u01cd\u01ce\7\\\2\2\u01ceG\3\2\2\2\u01cf"+
		"\u01d1\5N(\2\u01d0\u01cf\3\2\2\2\u01d0\u01d1\3\2\2\2\u01d1\u01d3\3\2\2"+
		"\2\u01d2\u01d4\5J&\2\u01d3\u01d2\3\2\2\2\u01d3\u01d4\3\2\2\2\u01d4I\3"+
		"\2\2\2\u01d5\u01d7\7\13\2\2\u01d6\u01d8\5L\'\2\u01d7\u01d6\3\2\2\2\u01d8"+
		"\u01d9\3\2\2\2\u01d9\u01d7\3\2\2\2\u01d9\u01da\3\2\2\2\u01daK\3\2\2\2"+
		"\u01db\u01dc\t\6\2\2\u01dcM\3\2\2\2\u01dd\u01df\7\n\2\2\u01de\u01e0\5"+
		"P)\2\u01df\u01de\3\2\2\2\u01e0\u01e1\3\2\2\2\u01e1\u01df\3\2\2\2\u01e1"+
		"\u01e2\3\2\2\2\u01e2O\3\2\2\2\u01e3\u01e4\t\7\2\2\u01e4Q\3\2\2\2\u01e5"+
		"\u01e9\7D\2\2\u01e6\u01e7\7\'\2\2\u01e7\u01ea\5\32\16\2\u01e8\u01ea\5"+
		"&\24\2\u01e9\u01e6\3\2\2\2\u01e9\u01e8\3\2\2\2\u01eaS\3\2\2\2\u01eb\u01ed"+
		"\5X-\2\u01ec\u01eb\3\2\2\2\u01ed\u01f0\3\2\2\2\u01ee\u01ec\3\2\2\2\u01ee"+
		"\u01ef\3\2\2\2\u01ef\u01f1\3\2\2\2\u01f0\u01ee\3\2\2\2\u01f1\u01f2\7D"+
		"\2\2\u01f2U\3\2\2\2\u01f3\u01f5\5X-\2\u01f4\u01f3\3\2\2\2\u01f5\u01f8"+
		"\3\2\2\2\u01f6\u01f4\3\2\2\2\u01f6\u01f7\3\2\2\2\u01f7\u01f9\3\2\2\2\u01f8"+
		"\u01f6\3\2\2\2\u01f9\u01fa\7D\2\2\u01faW\3\2\2\2\u01fb\u01fc\7D\2\2\u01fc"+
		"\u01fd\t\b\2\2\u01fdY\3\2\2\2\u01fe\u01ff\7D\2\2\u01ff[\3\2\2\2M_cflq"+
		"{\u0081\u0088\u008d\u0090\u0094\u009a\u009e\u00a4\u00ab\u00af\u00b2\u00b7"+
		"\u00bb\u00bd\u00c0\u00cd\u00d0\u00d6\u00de\u00e2\u00e7\u00ec\u00f1\u00f6"+
		"\u00fb\u0100\u0103\u0108\u010b\u0110\u0115\u0118\u011f\u0124\u0128\u012f"+
		"\u0132\u0138\u0144\u0148\u014d\u0150\u0156\u0158\u015b\u015e\u0163\u0166"+
		"\u0176\u0180\u0184\u0187\u018a\u018e\u0195\u019c\u01a0\u01a6\u01b1\u01bf"+
		"\u01c4\u01ca\u01d0\u01d3\u01d9\u01e1\u01e9\u01ee\u01f6";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}