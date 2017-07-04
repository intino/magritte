// Generated from /Users/oroncal/workspace/tara/core/language/src/io/intino/tara/lang/grammar/TaraGrammar.g4 by ANTLR 4.6
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
	static { RuntimeMetaData.checkVersion("4.6", RuntimeMetaData.VERSION); }

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
		DOUBLE_VALUE=60, APHOSTROPHE=61, STRING_MULTILINE=62, SINGLE_QUOTE=63, 
		EXPRESSION_MULTILINE=64, CLASS_TYPE=65, IDENTIFIER=66, MEASURE_VALUE=67, 
		NEWLINE=68, SPACES=69, DOC=70, SP=71, NL=72, NEW_LINE_INDENT=73, DEDENT=74, 
		UNKNOWN_TOKEN=75, QUOTE=76, Q=77, SLASH_Q=78, SLASH=79, CHARACTER=80, 
		M_QUOTE=81, M_CHARACTER=82, ME_STRING_MULTILINE=83, ME_CHARACTER=84, E_QUOTE=85, 
		E_SLASH_Q=86, E_SLASH=87, E_CHARACTER=88, QUOTE_BEGIN=89, QUOTE_END=90, 
		EXPRESSION_BEGIN=91, EXPRESSION_END=92;
	public static final int
		RULE_root = 0, RULE_dslDeclaration = 1, RULE_imports = 2, RULE_anImport = 3, 
		RULE_doc = 4, RULE_node = 5, RULE_signature = 6, RULE_parent = 7, RULE_parameters = 8, 
		RULE_parameter = 9, RULE_facets = 10, RULE_facet = 11, RULE_withTable = 12, 
		RULE_tableParameters = 13, RULE_value = 14, RULE_body = 15, RULE_facetTarget = 16, 
		RULE_nodeReference = 17, RULE_with = 18, RULE_variable = 19, RULE_bodyValue = 20, 
		RULE_variableType = 21, RULE_ruleContainer = 22, RULE_ruleValue = 23, 
		RULE_classType = 24, RULE_range = 25, RULE_size = 26, RULE_sizeRange = 27, 
		RULE_listRange = 28, RULE_methodReference = 29, RULE_stringValue = 30, 
		RULE_booleanValue = 31, RULE_tupleValue = 32, RULE_integerValue = 33, 
		RULE_doubleValue = 34, RULE_metric = 35, RULE_expression = 36, RULE_tags = 37, 
		RULE_annotations = 38, RULE_annotation = 39, RULE_flags = 40, RULE_flag = 41, 
		RULE_varInit = 42, RULE_headerReference = 43, RULE_identifierReference = 44, 
		RULE_hierarchy = 45, RULE_metaidentifier = 46;
	public static final String[] ruleNames = {
		"root", "dslDeclaration", "imports", "anImport", "doc", "node", "signature", 
		"parent", "parameters", "parameter", "facets", "facet", "withTable", "tableParameters", 
		"value", "body", "facetTarget", "nodeReference", "with", "variable", "bodyValue", 
		"variableType", "ruleContainer", "ruleValue", "classType", "range", "size", 
		"sizeRange", "listRange", "methodReference", "stringValue", "booleanValue", 
		"tupleValue", "integerValue", "doubleValue", "metric", "expression", "tags", 
		"annotations", "annotation", "flags", "flag", "varInit", "headerReference", 
		"identifierReference", "hierarchy", "metaidentifier"
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
		null, null, "'\"'", "'\\\"'", null, null, null, null, null, null, null, 
		"'\\''", null, null, "'%QUOTE_BEGIN%'", "'%QUOTE_END%'", "'%EXPRESSION_BEGIN%'", 
		"'%EXPRESSION_END%'"
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
		"APHOSTROPHE", "STRING_MULTILINE", "SINGLE_QUOTE", "EXPRESSION_MULTILINE", 
		"CLASS_TYPE", "IDENTIFIER", "MEASURE_VALUE", "NEWLINE", "SPACES", "DOC", 
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
			setState(97);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(94);
				match(NEWLINE);
				}
				}
				setState(99);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(101);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DSL) {
				{
				setState(100);
				dslDeclaration();
				}
			}

			setState(104);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==USE) {
				{
				setState(103);
				imports();
				}
			}

			setState(115);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SUB || _la==IDENTIFIER || _la==DOC) {
				{
				{
				setState(106);
				node();
				setState(110);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NEWLINE) {
					{
					{
					setState(107);
					match(NEWLINE);
					}
					}
					setState(112);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				}
				setState(117);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(118);
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
			setState(120);
			match(DSL);
			setState(121);
			headerReference();
			setState(125);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(122);
				match(NEWLINE);
				}
				}
				setState(127);
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
			setState(129); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(128);
				anImport();
				}
				}
				setState(131); 
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
			setState(133);
			match(USE);
			setState(134);
			headerReference();
			setState(136); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(135);
				match(NEWLINE);
				}
				}
				setState(138); 
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
			setState(141); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(140);
				match(DOC);
				}
				}
				setState(143); 
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
			setState(146);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DOC) {
				{
				setState(145);
				doc();
				}
			}

			setState(148);
			signature();
			setState(150);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NEW_LINE_INDENT) {
				{
				setState(149);
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
			setState(191);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SUB:
				{
				{
				setState(152);
				match(SUB);
				setState(156);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COLON) {
					{
					{
					setState(153);
					ruleContainer();
					}
					}
					setState(158);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(160);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LEFT_PARENTHESIS) {
					{
					setState(159);
					parameters();
					}
				}

				setState(162);
				match(IDENTIFIER);
				setState(166);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==AS) {
					{
					{
					setState(163);
					facets();
					}
					}
					setState(168);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				}
				break;
			case IDENTIFIER:
				{
				{
				setState(169);
				metaidentifier();
				setState(173);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COLON) {
					{
					{
					setState(170);
					ruleContainer();
					}
					}
					setState(175);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(177);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LEFT_PARENTHESIS) {
					{
					setState(176);
					parameters();
					}
				}

				setState(180);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
				case 1:
					{
					setState(179);
					match(IDENTIFIER);
					}
					break;
				}
				setState(185);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==AS) {
					{
					{
					setState(182);
					facets();
					}
					}
					setState(187);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(189);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==EXTENDS) {
					{
					setState(188);
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
			setState(194);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ON) {
				{
				setState(193);
				facetTarget();
				}
			}

			setState(196);
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
			setState(198);
			match(EXTENDS);
			setState(199);
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
			setState(201);
			match(LEFT_PARENTHESIS);
			setState(210);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 32)) & ~0x3f) == 0 && ((1L << (_la - 32)) & ((1L << (AT - 32)) | (1L << (EMPTY - 32)) | (1L << (BOOLEAN_VALUE - 32)) | (1L << (NATURAL_VALUE - 32)) | (1L << (NEGATIVE_VALUE - 32)) | (1L << (DOUBLE_VALUE - 32)) | (1L << (IDENTIFIER - 32)) | (1L << (NEWLINE - 32)) | (1L << (QUOTE_BEGIN - 32)) | (1L << (EXPRESSION_BEGIN - 32)))) != 0)) {
				{
				setState(202);
				parameter();
				setState(207);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(203);
					match(COMMA);
					setState(204);
					parameter();
					}
					}
					setState(209);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(212);
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
			setState(216);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				{
				setState(214);
				match(IDENTIFIER);
				setState(215);
				match(EQUALS);
				}
				break;
			}
			setState(218);
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
			setState(220);
			match(AS);
			setState(222); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(221);
					facet();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(224); 
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
			setState(226);
			metaidentifier();
			setState(228);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LEFT_PARENTHESIS) {
				{
				setState(227);
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

	public static class WithTableContext extends ParserRuleContext {
		public TerminalNode LIST() { return getToken(TaraGrammar.LIST, 0); }
		public TerminalNode WITH() { return getToken(TaraGrammar.WITH, 0); }
		public IdentifierReferenceContext identifierReference() {
			return getRuleContext(IdentifierReferenceContext.class,0);
		}
		public TableParametersContext tableParameters() {
			return getRuleContext(TableParametersContext.class,0);
		}
		public WithTableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_withTable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterWithTable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitWithTable(this);
		}
	}

	public final WithTableContext withTable() throws RecognitionException {
		WithTableContext _localctx = new WithTableContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_withTable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(230);
			match(LIST);
			setState(231);
			match(WITH);
			setState(232);
			identifierReference();
			setState(233);
			tableParameters();
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

	public static class TableParametersContext extends ParserRuleContext {
		public TerminalNode LEFT_PARENTHESIS() { return getToken(TaraGrammar.LEFT_PARENTHESIS, 0); }
		public TerminalNode RIGHT_PARENTHESIS() { return getToken(TaraGrammar.RIGHT_PARENTHESIS, 0); }
		public List<TerminalNode> IDENTIFIER() { return getTokens(TaraGrammar.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(TaraGrammar.IDENTIFIER, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(TaraGrammar.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(TaraGrammar.COMMA, i);
		}
		public TableParametersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tableParameters; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterTableParameters(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitTableParameters(this);
		}
	}

	public final TableParametersContext tableParameters() throws RecognitionException {
		TableParametersContext _localctx = new TableParametersContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_tableParameters);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(235);
			match(LEFT_PARENTHESIS);
			setState(252);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(237); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(236);
					match(IDENTIFIER);
					}
					}
					setState(239); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==IDENTIFIER );
				setState(249);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(241);
					match(COMMA);
					setState(243); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(242);
						match(IDENTIFIER);
						}
						}
						setState(245); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==IDENTIFIER );
					}
					}
					setState(251);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(254);
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
		enterRule(_localctx, 28, RULE_value);
		int _la;
		try {
			int _alt;
			setState(308);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,41,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(257); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(256);
						identifierReference();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(259); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(262); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(261);
						stringValue();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(264); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(267); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(266);
						tupleValue();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(269); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(272); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(271);
					booleanValue();
					}
					}
					setState(274); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==BOOLEAN_VALUE );
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(277); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(276);
						identifierReference();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(279); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(282); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(281);
					integerValue();
					}
					}
					setState(284); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NATURAL_VALUE || _la==NEGATIVE_VALUE );
				setState(287);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
				case 1:
					{
					setState(286);
					metric();
					}
					break;
				}
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(290); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(289);
					doubleValue();
					}
					}
					setState(292); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NATURAL_VALUE) | (1L << NEGATIVE_VALUE) | (1L << DOUBLE_VALUE))) != 0) );
				setState(295);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
				case 1:
					{
					setState(294);
					metric();
					}
					break;
				}
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(298); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(297);
						expression();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(300); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,39,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(303); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(302);
					methodReference();
					}
					}
					setState(305); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==AT );
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(307);
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
		enterRule(_localctx, 30, RULE_body);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(310);
			match(NEW_LINE_INDENT);
			setState(322); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(315);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,42,_ctx) ) {
				case 1:
					{
					setState(311);
					variable();
					}
					break;
				case 2:
					{
					setState(312);
					node();
					}
					break;
				case 3:
					{
					setState(313);
					varInit();
					}
					break;
				case 4:
					{
					setState(314);
					nodeReference();
					}
					break;
				}
				setState(318); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(317);
					match(NEWLINE);
					}
					}
					setState(320); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NEWLINE );
				}
				}
				setState(324); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SUB) | (1L << VAR) | (1L << HAS))) != 0) || _la==IDENTIFIER || _la==DOC );
			setState(326);
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
		enterRule(_localctx, 32, RULE_facetTarget);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(328);
			match(ON);
			setState(331);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				{
				setState(329);
				identifierReference();
				}
				break;
			case ANY:
				{
				setState(330);
				match(ANY);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(334);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WITH) {
				{
				setState(333);
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
		enterRule(_localctx, 34, RULE_nodeReference);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(336);
			match(HAS);
			setState(340);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COLON) {
				{
				{
				setState(337);
				ruleContainer();
				}
				}
				setState(342);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(343);
			identifierReference();
			setState(344);
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
		enterRule(_localctx, 36, RULE_with);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(346);
			match(WITH);
			setState(347);
			identifierReference();
			setState(352);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(348);
				match(COMMA);
				setState(349);
				identifierReference();
				}
				}
				setState(354);
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
		enterRule(_localctx, 38, RULE_variable);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(356);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DOC) {
				{
				setState(355);
				doc();
				}
			}

			setState(358);
			match(VAR);
			setState(359);
			variableType();
			setState(361);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LEFT_SQUARE) {
				{
				setState(360);
				size();
				}
			}

			setState(364);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(363);
				ruleContainer();
				}
			}

			setState(366);
			match(IDENTIFIER);
			setState(372);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==EQUALS) {
				{
				setState(367);
				match(EQUALS);
				setState(368);
				value();
				setState(370);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IDENTIFIER || _la==MEASURE_VALUE) {
					{
					setState(369);
					metric();
					}
				}

				}
			}

			setState(375);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IS) {
				{
				setState(374);
				flags();
				}
			}

			setState(378);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NEW_LINE_INDENT) {
				{
				setState(377);
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
		enterRule(_localctx, 40, RULE_bodyValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(380);
			match(NEW_LINE_INDENT);
			setState(383);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,56,_ctx) ) {
			case 1:
				{
				setState(381);
				stringValue();
				}
				break;
			case 2:
				{
				setState(382);
				expression();
				}
				break;
			}
			setState(386);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NEWLINE) {
				{
				setState(385);
				match(NEWLINE);
				}
			}

			setState(388);
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
		enterRule(_localctx, 42, RULE_variableType);
		try {
			setState(402);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT_TYPE:
				enterOuterAlt(_localctx, 1);
				{
				setState(390);
				match(INT_TYPE);
				}
				break;
			case DOUBLE_TYPE:
				enterOuterAlt(_localctx, 2);
				{
				setState(391);
				match(DOUBLE_TYPE);
				}
				break;
			case BOOLEAN_TYPE:
				enterOuterAlt(_localctx, 3);
				{
				setState(392);
				match(BOOLEAN_TYPE);
				}
				break;
			case STRING_TYPE:
				enterOuterAlt(_localctx, 4);
				{
				setState(393);
				match(STRING_TYPE);
				}
				break;
			case FUNCTION_TYPE:
				enterOuterAlt(_localctx, 5);
				{
				setState(394);
				match(FUNCTION_TYPE);
				}
				break;
			case OBJECT_TYPE:
				enterOuterAlt(_localctx, 6);
				{
				setState(395);
				match(OBJECT_TYPE);
				}
				break;
			case WORD:
				enterOuterAlt(_localctx, 7);
				{
				setState(396);
				match(WORD);
				}
				break;
			case DATE_TYPE:
				enterOuterAlt(_localctx, 8);
				{
				setState(397);
				match(DATE_TYPE);
				}
				break;
			case INSTANT_TYPE:
				enterOuterAlt(_localctx, 9);
				{
				setState(398);
				match(INSTANT_TYPE);
				}
				break;
			case TIME_TYPE:
				enterOuterAlt(_localctx, 10);
				{
				setState(399);
				match(TIME_TYPE);
				}
				break;
			case RESOURCE:
				enterOuterAlt(_localctx, 11);
				{
				setState(400);
				match(RESOURCE);
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 12);
				{
				setState(401);
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
		enterRule(_localctx, 44, RULE_ruleContainer);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(404);
			match(COLON);
			setState(405);
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
		enterRule(_localctx, 46, RULE_ruleValue);
		int _la;
		try {
			setState(426);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LEFT_CURLY:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(407);
				match(LEFT_CURLY);
				setState(422);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,62,_ctx) ) {
				case 1:
					{
					setState(408);
					classType();
					}
					break;
				case 2:
					{
					setState(410); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(409);
						match(IDENTIFIER);
						}
						}
						setState(412); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==IDENTIFIER );
					}
					break;
				case 3:
					{
					{
					setState(416);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case STAR:
					case NATURAL_VALUE:
					case NEGATIVE_VALUE:
					case DOUBLE_VALUE:
						{
						setState(414);
						range();
						}
						break;
					case NEWLINE:
					case QUOTE_BEGIN:
						{
						setState(415);
						stringValue();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(419);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==IDENTIFIER || _la==MEASURE_VALUE) {
						{
						setState(418);
						metric();
						}
					}

					}
					}
					break;
				case 4:
					{
					setState(421);
					metric();
					}
					break;
				}
				setState(424);
				match(RIGHT_CURLY);
				}
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(425);
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
		enterRule(_localctx, 48, RULE_classType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(428);
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
		enterRule(_localctx, 50, RULE_range);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(433);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,64,_ctx) ) {
			case 1:
				{
				setState(430);
				doubleValue();
				}
				break;
			case 2:
				{
				setState(431);
				integerValue();
				}
				break;
			case 3:
				{
				setState(432);
				match(STAR);
				}
				break;
			}
			setState(435);
			match(DOT);
			setState(436);
			match(DOT);
			setState(440);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,65,_ctx) ) {
			case 1:
				{
				setState(437);
				doubleValue();
				}
				break;
			case 2:
				{
				setState(438);
				integerValue();
				}
				break;
			case 3:
				{
				setState(439);
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
		enterRule(_localctx, 52, RULE_size);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(442);
			match(LEFT_SQUARE);
			setState(444);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==STAR || _la==NATURAL_VALUE) {
				{
				setState(443);
				sizeRange();
				}
			}

			setState(446);
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
		enterRule(_localctx, 54, RULE_sizeRange);
		try {
			setState(450);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,67,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(448);
				match(NATURAL_VALUE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(449);
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
		enterRule(_localctx, 56, RULE_listRange);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(452);
			_la = _input.LA(1);
			if ( !(_la==STAR || _la==NATURAL_VALUE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(453);
			match(DOT);
			setState(454);
			match(DOT);
			setState(455);
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
		enterRule(_localctx, 58, RULE_methodReference);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(457);
			match(AT);
			setState(458);
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
		enterRule(_localctx, 60, RULE_stringValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(461);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NEWLINE) {
				{
				setState(460);
				match(NEWLINE);
				}
			}

			{
			setState(463);
			match(QUOTE_BEGIN);
			setState(467);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==CHARACTER) {
				{
				{
				setState(464);
				match(CHARACTER);
				}
				}
				setState(469);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(470);
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
		enterRule(_localctx, 62, RULE_booleanValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(472);
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
		enterRule(_localctx, 64, RULE_tupleValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(474);
			stringValue();
			setState(475);
			match(COLON);
			setState(476);
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
		enterRule(_localctx, 66, RULE_integerValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(478);
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
		enterRule(_localctx, 68, RULE_doubleValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(480);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NATURAL_VALUE) | (1L << NEGATIVE_VALUE) | (1L << DOUBLE_VALUE))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(482);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SCIENCE_NOT) {
				{
				setState(481);
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
		enterRule(_localctx, 70, RULE_metric);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(484);
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
		enterRule(_localctx, 72, RULE_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(487);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NEWLINE) {
				{
				setState(486);
				match(NEWLINE);
				}
			}

			{
			setState(489);
			match(EXPRESSION_BEGIN);
			setState(493);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==CHARACTER) {
				{
				{
				setState(490);
				match(CHARACTER);
				}
				}
				setState(495);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(496);
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
		enterRule(_localctx, 74, RULE_tags);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(499);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IS) {
				{
				setState(498);
				flags();
				}
			}

			setState(502);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==INTO) {
				{
				setState(501);
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
		enterRule(_localctx, 76, RULE_annotations);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(504);
			match(INTO);
			setState(506); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(505);
				annotation();
				}
				}
				setState(508); 
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
		enterRule(_localctx, 78, RULE_annotation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(510);
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
		enterRule(_localctx, 80, RULE_flags);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(512);
			match(IS);
			setState(514); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(513);
				flag();
				}
				}
				setState(516); 
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
		enterRule(_localctx, 82, RULE_flag);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(518);
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
		enterRule(_localctx, 84, RULE_varInit);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(520);
			match(IDENTIFIER);
			setState(524);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case EQUALS:
				{
				{
				setState(521);
				match(EQUALS);
				setState(522);
				value();
				}
				}
				break;
			case NEW_LINE_INDENT:
				{
				setState(523);
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
		enterRule(_localctx, 86, RULE_headerReference);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(529);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,78,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(526);
					hierarchy();
					}
					} 
				}
				setState(531);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,78,_ctx);
			}
			setState(532);
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
		enterRule(_localctx, 88, RULE_identifierReference);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(537);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,79,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(534);
					hierarchy();
					}
					} 
				}
				setState(539);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,79,_ctx);
			}
			setState(540);
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
		enterRule(_localctx, 90, RULE_hierarchy);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(542);
			match(IDENTIFIER);
			setState(543);
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
		enterRule(_localctx, 92, RULE_metaidentifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(545);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3^\u0226\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\3\2\7\2b\n\2\f\2\16\2e\13\2\3\2\5\2h"+
		"\n\2\3\2\5\2k\n\2\3\2\3\2\7\2o\n\2\f\2\16\2r\13\2\7\2t\n\2\f\2\16\2w\13"+
		"\2\3\2\3\2\3\3\3\3\3\3\7\3~\n\3\f\3\16\3\u0081\13\3\3\4\6\4\u0084\n\4"+
		"\r\4\16\4\u0085\3\5\3\5\3\5\6\5\u008b\n\5\r\5\16\5\u008c\3\6\6\6\u0090"+
		"\n\6\r\6\16\6\u0091\3\7\5\7\u0095\n\7\3\7\3\7\5\7\u0099\n\7\3\b\3\b\7"+
		"\b\u009d\n\b\f\b\16\b\u00a0\13\b\3\b\5\b\u00a3\n\b\3\b\3\b\7\b\u00a7\n"+
		"\b\f\b\16\b\u00aa\13\b\3\b\3\b\7\b\u00ae\n\b\f\b\16\b\u00b1\13\b\3\b\5"+
		"\b\u00b4\n\b\3\b\5\b\u00b7\n\b\3\b\7\b\u00ba\n\b\f\b\16\b\u00bd\13\b\3"+
		"\b\5\b\u00c0\n\b\5\b\u00c2\n\b\3\b\5\b\u00c5\n\b\3\b\3\b\3\t\3\t\3\t\3"+
		"\n\3\n\3\n\3\n\7\n\u00d0\n\n\f\n\16\n\u00d3\13\n\5\n\u00d5\n\n\3\n\3\n"+
		"\3\13\3\13\5\13\u00db\n\13\3\13\3\13\3\f\3\f\6\f\u00e1\n\f\r\f\16\f\u00e2"+
		"\3\r\3\r\5\r\u00e7\n\r\3\16\3\16\3\16\3\16\3\16\3\17\3\17\6\17\u00f0\n"+
		"\17\r\17\16\17\u00f1\3\17\3\17\6\17\u00f6\n\17\r\17\16\17\u00f7\7\17\u00fa"+
		"\n\17\f\17\16\17\u00fd\13\17\5\17\u00ff\n\17\3\17\3\17\3\20\6\20\u0104"+
		"\n\20\r\20\16\20\u0105\3\20\6\20\u0109\n\20\r\20\16\20\u010a\3\20\6\20"+
		"\u010e\n\20\r\20\16\20\u010f\3\20\6\20\u0113\n\20\r\20\16\20\u0114\3\20"+
		"\6\20\u0118\n\20\r\20\16\20\u0119\3\20\6\20\u011d\n\20\r\20\16\20\u011e"+
		"\3\20\5\20\u0122\n\20\3\20\6\20\u0125\n\20\r\20\16\20\u0126\3\20\5\20"+
		"\u012a\n\20\3\20\6\20\u012d\n\20\r\20\16\20\u012e\3\20\6\20\u0132\n\20"+
		"\r\20\16\20\u0133\3\20\5\20\u0137\n\20\3\21\3\21\3\21\3\21\3\21\5\21\u013e"+
		"\n\21\3\21\6\21\u0141\n\21\r\21\16\21\u0142\6\21\u0145\n\21\r\21\16\21"+
		"\u0146\3\21\3\21\3\22\3\22\3\22\5\22\u014e\n\22\3\22\5\22\u0151\n\22\3"+
		"\23\3\23\7\23\u0155\n\23\f\23\16\23\u0158\13\23\3\23\3\23\3\23\3\24\3"+
		"\24\3\24\3\24\7\24\u0161\n\24\f\24\16\24\u0164\13\24\3\25\5\25\u0167\n"+
		"\25\3\25\3\25\3\25\5\25\u016c\n\25\3\25\5\25\u016f\n\25\3\25\3\25\3\25"+
		"\3\25\5\25\u0175\n\25\5\25\u0177\n\25\3\25\5\25\u017a\n\25\3\25\5\25\u017d"+
		"\n\25\3\26\3\26\3\26\5\26\u0182\n\26\3\26\5\26\u0185\n\26\3\26\3\26\3"+
		"\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\5\27\u0195"+
		"\n\27\3\30\3\30\3\30\3\31\3\31\3\31\6\31\u019d\n\31\r\31\16\31\u019e\3"+
		"\31\3\31\5\31\u01a3\n\31\3\31\5\31\u01a6\n\31\3\31\5\31\u01a9\n\31\3\31"+
		"\3\31\5\31\u01ad\n\31\3\32\3\32\3\33\3\33\3\33\5\33\u01b4\n\33\3\33\3"+
		"\33\3\33\3\33\3\33\5\33\u01bb\n\33\3\34\3\34\5\34\u01bf\n\34\3\34\3\34"+
		"\3\35\3\35\5\35\u01c5\n\35\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3 "+
		"\5 \u01d0\n \3 \3 \7 \u01d4\n \f \16 \u01d7\13 \3 \3 \3!\3!\3\"\3\"\3"+
		"\"\3\"\3#\3#\3$\3$\5$\u01e5\n$\3%\3%\3&\5&\u01ea\n&\3&\3&\7&\u01ee\n&"+
		"\f&\16&\u01f1\13&\3&\3&\3\'\5\'\u01f6\n\'\3\'\5\'\u01f9\n\'\3(\3(\6(\u01fd"+
		"\n(\r(\16(\u01fe\3)\3)\3*\3*\6*\u0205\n*\r*\16*\u0206\3+\3+\3,\3,\3,\3"+
		",\5,\u020f\n,\3-\7-\u0212\n-\f-\16-\u0215\13-\3-\3-\3.\7.\u021a\n.\f."+
		"\16.\u021d\13.\3.\3.\3/\3/\3/\3\60\3\60\3\60\2\2\61\2\4\6\b\n\f\16\20"+
		"\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJLNPRTVXZ\\^\2\t\4"+
		"\2((<<\3\2<=\3\2<>\3\2DE\4\2\22\23\25\25\3\2\17\31\4\2&&++\u025e\2c\3"+
		"\2\2\2\4z\3\2\2\2\6\u0083\3\2\2\2\b\u0087\3\2\2\2\n\u008f\3\2\2\2\f\u0094"+
		"\3\2\2\2\16\u00c1\3\2\2\2\20\u00c8\3\2\2\2\22\u00cb\3\2\2\2\24\u00da\3"+
		"\2\2\2\26\u00de\3\2\2\2\30\u00e4\3\2\2\2\32\u00e8\3\2\2\2\34\u00ed\3\2"+
		"\2\2\36\u0136\3\2\2\2 \u0138\3\2\2\2\"\u014a\3\2\2\2$\u0152\3\2\2\2&\u015c"+
		"\3\2\2\2(\u0166\3\2\2\2*\u017e\3\2\2\2,\u0194\3\2\2\2.\u0196\3\2\2\2\60"+
		"\u01ac\3\2\2\2\62\u01ae\3\2\2\2\64\u01b3\3\2\2\2\66\u01bc\3\2\2\28\u01c4"+
		"\3\2\2\2:\u01c6\3\2\2\2<\u01cb\3\2\2\2>\u01cf\3\2\2\2@\u01da\3\2\2\2B"+
		"\u01dc\3\2\2\2D\u01e0\3\2\2\2F\u01e2\3\2\2\2H\u01e6\3\2\2\2J\u01e9\3\2"+
		"\2\2L\u01f5\3\2\2\2N\u01fa\3\2\2\2P\u0200\3\2\2\2R\u0202\3\2\2\2T\u0208"+
		"\3\2\2\2V\u020a\3\2\2\2X\u0213\3\2\2\2Z\u021b\3\2\2\2\\\u0220\3\2\2\2"+
		"^\u0223\3\2\2\2`b\7F\2\2a`\3\2\2\2be\3\2\2\2ca\3\2\2\2cd\3\2\2\2dg\3\2"+
		"\2\2ec\3\2\2\2fh\5\4\3\2gf\3\2\2\2gh\3\2\2\2hj\3\2\2\2ik\5\6\4\2ji\3\2"+
		"\2\2jk\3\2\2\2ku\3\2\2\2lp\5\f\7\2mo\7F\2\2nm\3\2\2\2or\3\2\2\2pn\3\2"+
		"\2\2pq\3\2\2\2qt\3\2\2\2rp\3\2\2\2sl\3\2\2\2tw\3\2\2\2us\3\2\2\2uv\3\2"+
		"\2\2vx\3\2\2\2wu\3\2\2\2xy\7\2\2\3y\3\3\2\2\2z{\7\5\2\2{\177\5X-\2|~\7"+
		"F\2\2}|\3\2\2\2~\u0081\3\2\2\2\177}\3\2\2\2\177\u0080\3\2\2\2\u0080\5"+
		"\3\2\2\2\u0081\177\3\2\2\2\u0082\u0084\5\b\5\2\u0083\u0082\3\2\2\2\u0084"+
		"\u0085\3\2\2\2\u0085\u0083\3\2\2\2\u0085\u0086\3\2\2\2\u0086\7\3\2\2\2"+
		"\u0087\u0088\7\4\2\2\u0088\u008a\5X-\2\u0089\u008b\7F\2\2\u008a\u0089"+
		"\3\2\2\2\u008b\u008c\3\2\2\2\u008c\u008a\3\2\2\2\u008c\u008d\3\2\2\2\u008d"+
		"\t\3\2\2\2\u008e\u0090\7H\2\2\u008f\u008e\3\2\2\2\u0090\u0091\3\2\2\2"+
		"\u0091\u008f\3\2\2\2\u0091\u0092\3\2\2\2\u0092\13\3\2\2\2\u0093\u0095"+
		"\5\n\6\2\u0094\u0093\3\2\2\2\u0094\u0095\3\2\2\2\u0095\u0096\3\2\2\2\u0096"+
		"\u0098\5\16\b\2\u0097\u0099\5 \21\2\u0098\u0097\3\2\2\2\u0098\u0099\3"+
		"\2\2\2\u0099\r\3\2\2\2\u009a\u009e\7\3\2\2\u009b\u009d\5.\30\2\u009c\u009b"+
		"\3\2\2\2\u009d\u00a0\3\2\2\2\u009e\u009c\3\2\2\2\u009e\u009f\3\2\2\2\u009f"+
		"\u00a2\3\2\2\2\u00a0\u009e\3\2\2\2\u00a1\u00a3\5\22\n\2\u00a2\u00a1\3"+
		"\2\2\2\u00a2\u00a3\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4\u00a8\7D\2\2\u00a5"+
		"\u00a7\5\26\f\2\u00a6\u00a5\3\2\2\2\u00a7\u00aa\3\2\2\2\u00a8\u00a6\3"+
		"\2\2\2\u00a8\u00a9\3\2\2\2\u00a9\u00c2\3\2\2\2\u00aa\u00a8\3\2\2\2\u00ab"+
		"\u00af\5^\60\2\u00ac\u00ae\5.\30\2\u00ad\u00ac\3\2\2\2\u00ae\u00b1\3\2"+
		"\2\2\u00af\u00ad\3\2\2\2\u00af\u00b0\3\2\2\2\u00b0\u00b3\3\2\2\2\u00b1"+
		"\u00af\3\2\2\2\u00b2\u00b4\5\22\n\2\u00b3\u00b2\3\2\2\2\u00b3\u00b4\3"+
		"\2\2\2\u00b4\u00b6\3\2\2\2\u00b5\u00b7\7D\2\2\u00b6\u00b5\3\2\2\2\u00b6"+
		"\u00b7\3\2\2\2\u00b7\u00bb\3\2\2\2\u00b8\u00ba\5\26\f\2\u00b9\u00b8\3"+
		"\2\2\2\u00ba\u00bd\3\2\2\2\u00bb\u00b9\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc"+
		"\u00bf\3\2\2\2\u00bd\u00bb\3\2\2\2\u00be\u00c0\5\20\t\2\u00bf\u00be\3"+
		"\2\2\2\u00bf\u00c0\3\2\2\2\u00c0\u00c2\3\2\2\2\u00c1\u009a\3\2\2\2\u00c1"+
		"\u00ab\3\2\2\2\u00c2\u00c4\3\2\2\2\u00c3\u00c5\5\"\22\2\u00c4\u00c3\3"+
		"\2\2\2\u00c4\u00c5\3\2\2\2\u00c5\u00c6\3\2\2\2\u00c6\u00c7\5L\'\2\u00c7"+
		"\17\3\2\2\2\u00c8\u00c9\7\16\2\2\u00c9\u00ca\5Z.\2\u00ca\21\3\2\2\2\u00cb"+
		"\u00d4\7\32\2\2\u00cc\u00d1\5\24\13\2\u00cd\u00ce\7%\2\2\u00ce\u00d0\5"+
		"\24\13\2\u00cf\u00cd\3\2\2\2\u00d0\u00d3\3\2\2\2\u00d1\u00cf\3\2\2\2\u00d1"+
		"\u00d2\3\2\2\2\u00d2\u00d5\3\2\2\2\u00d3\u00d1\3\2\2\2\u00d4\u00cc\3\2"+
		"\2\2\u00d4\u00d5\3\2\2\2\u00d5\u00d6\3\2\2\2\u00d6\u00d7\7\33\2\2\u00d7"+
		"\23\3\2\2\2\u00d8\u00d9\7D\2\2\u00d9\u00db\7\'\2\2\u00da\u00d8\3\2\2\2"+
		"\u00da\u00db\3\2\2\2\u00db\u00dc\3\2\2\2\u00dc\u00dd\5\36\20\2\u00dd\25"+
		"\3\2\2\2\u00de\u00e0\7\7\2\2\u00df\u00e1\5\30\r\2\u00e0\u00df\3\2\2\2"+
		"\u00e1\u00e2\3\2\2\2\u00e2\u00e0\3\2\2\2\u00e2\u00e3\3\2\2\2\u00e3\27"+
		"\3\2\2\2\u00e4\u00e6\5^\60\2\u00e5\u00e7\5\22\n\2\u00e6\u00e5\3\2\2\2"+
		"\u00e6\u00e7\3\2\2\2\u00e7\31\3\2\2\2\u00e8\u00e9\7)\2\2\u00e9\u00ea\7"+
		"\f\2\2\u00ea\u00eb\5Z.\2\u00eb\u00ec\5\34\17\2\u00ec\33\3\2\2\2\u00ed"+
		"\u00fe\7\32\2\2\u00ee\u00f0\7D\2\2\u00ef\u00ee\3\2\2\2\u00f0\u00f1\3\2"+
		"\2\2\u00f1\u00ef\3\2\2\2\u00f1\u00f2\3\2\2\2\u00f2\u00fb\3\2\2\2\u00f3"+
		"\u00f5\7%\2\2\u00f4\u00f6\7D\2\2\u00f5\u00f4\3\2\2\2\u00f6\u00f7\3\2\2"+
		"\2\u00f7\u00f5\3\2\2\2\u00f7\u00f8\3\2\2\2\u00f8\u00fa\3\2\2\2\u00f9\u00f3"+
		"\3\2\2\2\u00fa\u00fd\3\2\2\2\u00fb\u00f9\3\2\2\2\u00fb\u00fc\3\2\2\2\u00fc"+
		"\u00ff\3\2\2\2\u00fd\u00fb\3\2\2\2\u00fe\u00ef\3\2\2\2\u00fe\u00ff\3\2"+
		"\2\2\u00ff\u0100\3\2\2\2\u0100\u0101\7\33\2\2\u0101\35\3\2\2\2\u0102\u0104"+
		"\5Z.\2\u0103\u0102\3\2\2\2\u0104\u0105\3\2\2\2\u0105\u0103\3\2\2\2\u0105"+
		"\u0106\3\2\2\2\u0106\u0137\3\2\2\2\u0107\u0109\5> \2\u0108\u0107\3\2\2"+
		"\2\u0109\u010a\3\2\2\2\u010a\u0108\3\2\2\2\u010a\u010b\3\2\2\2\u010b\u0137"+
		"\3\2\2\2\u010c\u010e\5B\"\2\u010d\u010c\3\2\2\2\u010e\u010f\3\2\2\2\u010f"+
		"\u010d\3\2\2\2\u010f\u0110\3\2\2\2\u0110\u0137\3\2\2\2\u0111\u0113\5@"+
		"!\2\u0112\u0111\3\2\2\2\u0113\u0114\3\2\2\2\u0114\u0112\3\2\2\2\u0114"+
		"\u0115\3\2\2\2\u0115\u0137\3\2\2\2\u0116\u0118\5Z.\2\u0117\u0116\3\2\2"+
		"\2\u0118\u0119\3\2\2\2\u0119\u0117\3\2\2\2\u0119\u011a\3\2\2\2\u011a\u0137"+
		"\3\2\2\2\u011b\u011d\5D#\2\u011c\u011b\3\2\2\2\u011d\u011e\3\2\2\2\u011e"+
		"\u011c\3\2\2\2\u011e\u011f\3\2\2\2\u011f\u0121\3\2\2\2\u0120\u0122\5H"+
		"%\2\u0121\u0120\3\2\2\2\u0121\u0122\3\2\2\2\u0122\u0137\3\2\2\2\u0123"+
		"\u0125\5F$\2\u0124\u0123\3\2\2\2\u0125\u0126\3\2\2\2\u0126\u0124\3\2\2"+
		"\2\u0126\u0127\3\2\2\2\u0127\u0129\3\2\2\2\u0128\u012a\5H%\2\u0129\u0128"+
		"\3\2\2\2\u0129\u012a\3\2\2\2\u012a\u0137\3\2\2\2\u012b\u012d\5J&\2\u012c"+
		"\u012b\3\2\2\2\u012d\u012e\3\2\2\2\u012e\u012c\3\2\2\2\u012e\u012f\3\2"+
		"\2\2\u012f\u0137\3\2\2\2\u0130\u0132\5<\37\2\u0131\u0130\3\2\2\2\u0132"+
		"\u0133\3\2\2\2\u0133\u0131\3\2\2\2\u0133\u0134\3\2\2\2\u0134\u0137\3\2"+
		"\2\2\u0135\u0137\7\67\2\2\u0136\u0103\3\2\2\2\u0136\u0108\3\2\2\2\u0136"+
		"\u010d\3\2\2\2\u0136\u0112\3\2\2\2\u0136\u0117\3\2\2\2\u0136\u011c\3\2"+
		"\2\2\u0136\u0124\3\2\2\2\u0136\u012c\3\2\2\2\u0136\u0131\3\2\2\2\u0136"+
		"\u0135\3\2\2\2\u0137\37\3\2\2\2\u0138\u0144\7K\2\2\u0139\u013e\5(\25\2"+
		"\u013a\u013e\5\f\7\2\u013b\u013e\5V,\2\u013c\u013e\5$\23\2\u013d\u0139"+
		"\3\2\2\2\u013d\u013a\3\2\2\2\u013d\u013b\3\2\2\2\u013d\u013c\3\2\2\2\u013e"+
		"\u0140\3\2\2\2\u013f\u0141\7F\2\2\u0140\u013f\3\2\2\2\u0141\u0142\3\2"+
		"\2\2\u0142\u0140\3\2\2\2\u0142\u0143\3\2\2\2\u0143\u0145\3\2\2\2\u0144"+
		"\u013d\3\2\2\2\u0145\u0146\3\2\2\2\u0146\u0144\3\2\2\2\u0146\u0147\3\2"+
		"\2\2\u0147\u0148\3\2\2\2\u0148\u0149\7L\2\2\u0149!\3\2\2\2\u014a\u014d"+
		"\7\t\2\2\u014b\u014e\5Z.\2\u014c\u014e\7\r\2\2\u014d\u014b\3\2\2\2\u014d"+
		"\u014c\3\2\2\2\u014e\u0150\3\2\2\2\u014f\u0151\5&\24\2\u0150\u014f\3\2"+
		"\2\2\u0150\u0151\3\2\2\2\u0151#\3\2\2\2\u0152\u0156\7\b\2\2\u0153\u0155"+
		"\5.\30\2\u0154\u0153\3\2\2\2\u0155\u0158\3\2\2\2\u0156\u0154\3\2\2\2\u0156"+
		"\u0157\3\2\2\2\u0157\u0159\3\2\2\2\u0158\u0156\3\2\2\2\u0159\u015a\5Z"+
		".\2\u015a\u015b\5L\'\2\u015b%\3\2\2\2\u015c\u015d\7\f\2\2\u015d\u0162"+
		"\5Z.\2\u015e\u015f\7%\2\2\u015f\u0161\5Z.\2\u0160\u015e\3\2\2\2\u0161"+
		"\u0164\3\2\2\2\u0162\u0160\3\2\2\2\u0162\u0163\3\2\2\2\u0163\'\3\2\2\2"+
		"\u0164\u0162\3\2\2\2\u0165\u0167\5\n\6\2\u0166\u0165\3\2\2\2\u0166\u0167"+
		"\3\2\2\2\u0167\u0168\3\2\2\2\u0168\u0169\7\6\2\2\u0169\u016b\5,\27\2\u016a"+
		"\u016c\5\66\34\2\u016b\u016a\3\2\2\2\u016b\u016c\3\2\2\2\u016c\u016e\3"+
		"\2\2\2\u016d\u016f\5.\30\2\u016e\u016d\3\2\2\2\u016e\u016f\3\2\2\2\u016f"+
		"\u0170\3\2\2\2\u0170\u0176\7D\2\2\u0171\u0172\7\'\2\2\u0172\u0174\5\36"+
		"\20\2\u0173\u0175\5H%\2\u0174\u0173\3\2\2\2\u0174\u0175\3\2\2\2\u0175"+
		"\u0177\3\2\2\2\u0176\u0171\3\2\2\2\u0176\u0177\3\2\2\2\u0177\u0179\3\2"+
		"\2\2\u0178\u017a\5R*\2\u0179\u0178\3\2\2\2\u0179\u017a\3\2\2\2\u017a\u017c"+
		"\3\2\2\2\u017b\u017d\5*\26\2\u017c\u017b\3\2\2\2\u017c\u017d\3\2\2\2\u017d"+
		")\3\2\2\2\u017e\u0181\7K\2\2\u017f\u0182\5> \2\u0180\u0182\5J&\2\u0181"+
		"\u017f\3\2\2\2\u0181\u0180\3\2\2\2\u0182\u0184\3\2\2\2\u0183\u0185\7F"+
		"\2\2\u0184\u0183\3\2\2\2\u0184\u0185\3\2\2\2\u0185\u0186\3\2\2\2\u0186"+
		"\u0187\7L\2\2\u0187+\3\2\2\2\u0188\u0195\7.\2\2\u0189\u0195\7\61\2\2\u018a"+
		"\u0195\7\63\2\2\u018b\u0195\7\62\2\2\u018c\u0195\7/\2\2\u018d\u0195\7"+
		"\60\2\2\u018e\u0195\7,\2\2\u018f\u0195\7\64\2\2\u0190\u0195\7\65\2\2\u0191"+
		"\u0195\7\66\2\2\u0192\u0195\7-\2\2\u0193\u0195\5Z.\2\u0194\u0188\3\2\2"+
		"\2\u0194\u0189\3\2\2\2\u0194\u018a\3\2\2\2\u0194\u018b\3\2\2\2\u0194\u018c"+
		"\3\2\2\2\u0194\u018d\3\2\2\2\u0194\u018e\3\2\2\2\u0194\u018f\3\2\2\2\u0194"+
		"\u0190\3\2\2\2\u0194\u0191\3\2\2\2\u0194\u0192\3\2\2\2\u0194\u0193\3\2"+
		"\2\2\u0195-\3\2\2\2\u0196\u0197\7$\2\2\u0197\u0198\5\60\31\2\u0198/\3"+
		"\2\2\2\u0199\u01a8\7\36\2\2\u019a\u01a9\5\62\32\2\u019b\u019d\7D\2\2\u019c"+
		"\u019b\3\2\2\2\u019d\u019e\3\2\2\2\u019e\u019c\3\2\2\2\u019e\u019f\3\2"+
		"\2\2\u019f\u01a9\3\2\2\2\u01a0\u01a3\5\64\33\2\u01a1\u01a3\5> \2\u01a2"+
		"\u01a0\3\2\2\2\u01a2\u01a1\3\2\2\2\u01a3\u01a5\3\2\2\2\u01a4\u01a6\5H"+
		"%\2\u01a5\u01a4\3\2\2\2\u01a5\u01a6\3\2\2\2\u01a6\u01a9\3\2\2\2\u01a7"+
		"\u01a9\5H%\2\u01a8\u019a\3\2\2\2\u01a8\u019c\3\2\2\2\u01a8\u01a2\3\2\2"+
		"\2\u01a8\u01a7\3\2\2\2\u01a9\u01aa\3\2\2\2\u01aa\u01ad\7\37\2\2\u01ab"+
		"\u01ad\5Z.\2\u01ac\u0199\3\2\2\2\u01ac\u01ab\3\2\2\2\u01ad\61\3\2\2\2"+
		"\u01ae\u01af\7C\2\2\u01af\63\3\2\2\2\u01b0\u01b4\5F$\2\u01b1\u01b4\5D"+
		"#\2\u01b2\u01b4\7(\2\2\u01b3\u01b0\3\2\2\2\u01b3\u01b1\3\2\2\2\u01b3\u01b2"+
		"\3\2\2\2\u01b4\u01b5\3\2\2\2\u01b5\u01b6\7&\2\2\u01b6\u01ba\7&\2\2\u01b7"+
		"\u01bb\5F$\2\u01b8\u01bb\5D#\2\u01b9\u01bb\7(\2\2\u01ba\u01b7\3\2\2\2"+
		"\u01ba\u01b8\3\2\2\2\u01ba\u01b9\3\2\2\2\u01bb\65\3\2\2\2\u01bc\u01be"+
		"\7\34\2\2\u01bd\u01bf\58\35\2\u01be\u01bd\3\2\2\2\u01be\u01bf\3\2\2\2"+
		"\u01bf\u01c0\3\2\2\2\u01c0\u01c1\7\35\2\2\u01c1\67\3\2\2\2\u01c2\u01c5"+
		"\7<\2\2\u01c3\u01c5\5:\36\2\u01c4\u01c2\3\2\2\2\u01c4\u01c3\3\2\2\2\u01c5"+
		"9\3\2\2\2\u01c6\u01c7\t\2\2\2\u01c7\u01c8\7&\2\2\u01c8\u01c9\7&\2\2\u01c9"+
		"\u01ca\t\2\2\2\u01ca;\3\2\2\2\u01cb\u01cc\7\"\2\2\u01cc\u01cd\5Z.\2\u01cd"+
		"=\3\2\2\2\u01ce\u01d0\7F\2\2\u01cf\u01ce\3\2\2\2\u01cf\u01d0\3\2\2\2\u01d0"+
		"\u01d1\3\2\2\2\u01d1\u01d5\7[\2\2\u01d2\u01d4\7R\2\2\u01d3\u01d2\3\2\2"+
		"\2\u01d4\u01d7\3\2\2\2\u01d5\u01d3\3\2\2\2\u01d5\u01d6\3\2\2\2\u01d6\u01d8"+
		"\3\2\2\2\u01d7\u01d5\3\2\2\2\u01d8\u01d9\7\\\2\2\u01d9?\3\2\2\2\u01da"+
		"\u01db\7;\2\2\u01dbA\3\2\2\2\u01dc\u01dd\5> \2\u01dd\u01de\7$\2\2\u01de"+
		"\u01df\5F$\2\u01dfC\3\2\2\2\u01e0\u01e1\t\3\2\2\u01e1E\3\2\2\2\u01e2\u01e4"+
		"\t\4\2\2\u01e3\u01e5\7:\2\2\u01e4\u01e3\3\2\2\2\u01e4\u01e5\3\2\2\2\u01e5"+
		"G\3\2\2\2\u01e6\u01e7\t\5\2\2\u01e7I\3\2\2\2\u01e8\u01ea\7F\2\2\u01e9"+
		"\u01e8\3\2\2\2\u01e9\u01ea\3\2\2\2\u01ea\u01eb\3\2\2\2\u01eb\u01ef\7]"+
		"\2\2\u01ec\u01ee\7R\2\2\u01ed\u01ec\3\2\2\2\u01ee\u01f1\3\2\2\2\u01ef"+
		"\u01ed\3\2\2\2\u01ef\u01f0\3\2\2\2\u01f0\u01f2\3\2\2\2\u01f1\u01ef\3\2"+
		"\2\2\u01f2\u01f3\7^\2\2\u01f3K\3\2\2\2\u01f4\u01f6\5R*\2\u01f5\u01f4\3"+
		"\2\2\2\u01f5\u01f6\3\2\2\2\u01f6\u01f8\3\2\2\2\u01f7\u01f9\5N(\2\u01f8"+
		"\u01f7\3\2\2\2\u01f8\u01f9\3\2\2\2\u01f9M\3\2\2\2\u01fa\u01fc\7\13\2\2"+
		"\u01fb\u01fd\5P)\2\u01fc\u01fb\3\2\2\2\u01fd\u01fe\3\2\2\2\u01fe\u01fc"+
		"\3\2\2\2\u01fe\u01ff\3\2\2\2\u01ffO\3\2\2\2\u0200\u0201\t\6\2\2\u0201"+
		"Q\3\2\2\2\u0202\u0204\7\n\2\2\u0203\u0205\5T+\2\u0204\u0203\3\2\2\2\u0205"+
		"\u0206\3\2\2\2\u0206\u0204\3\2\2\2\u0206\u0207\3\2\2\2\u0207S\3\2\2\2"+
		"\u0208\u0209\t\7\2\2\u0209U\3\2\2\2\u020a\u020e\7D\2\2\u020b\u020c\7\'"+
		"\2\2\u020c\u020f\5\36\20\2\u020d\u020f\5*\26\2\u020e\u020b\3\2\2\2\u020e"+
		"\u020d\3\2\2\2\u020fW\3\2\2\2\u0210\u0212\5\\/\2\u0211\u0210\3\2\2\2\u0212"+
		"\u0215\3\2\2\2\u0213\u0211\3\2\2\2\u0213\u0214\3\2\2\2\u0214\u0216\3\2"+
		"\2\2\u0215\u0213\3\2\2\2\u0216\u0217\7D\2\2\u0217Y\3\2\2\2\u0218\u021a"+
		"\5\\/\2\u0219\u0218\3\2\2\2\u021a\u021d\3\2\2\2\u021b\u0219\3\2\2\2\u021b"+
		"\u021c\3\2\2\2\u021c\u021e\3\2\2\2\u021d\u021b\3\2\2\2\u021e\u021f\7D"+
		"\2\2\u021f[\3\2\2\2\u0220\u0221\7D\2\2\u0221\u0222\t\b\2\2\u0222]\3\2"+
		"\2\2\u0223\u0224\7D\2\2\u0224_\3\2\2\2Rcgjpu\177\u0085\u008c\u0091\u0094"+
		"\u0098\u009e\u00a2\u00a8\u00af\u00b3\u00b6\u00bb\u00bf\u00c1\u00c4\u00d1"+
		"\u00d4\u00da\u00e2\u00e6\u00f1\u00f7\u00fb\u00fe\u0105\u010a\u010f\u0114"+
		"\u0119\u011e\u0121\u0126\u0129\u012e\u0133\u0136\u013d\u0142\u0146\u014d"+
		"\u0150\u0156\u0162\u0166\u016b\u016e\u0174\u0176\u0179\u017c\u0181\u0184"+
		"\u0194\u019e\u01a2\u01a5\u01a8\u01ac\u01b3\u01ba\u01be\u01c4\u01cf\u01d5"+
		"\u01e4\u01e9\u01ef\u01f5\u01f8\u01fe\u0206\u020e\u0213\u021b";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}