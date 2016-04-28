// Generated from /Users/oroncal/workspace/tara/language/grammar/src/tara/lang/grammar/TaraGrammar.g4 by ANTLR 4.5.1
package tara.lang.grammar;
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
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		SUB=1, USE=2, DSL=3, VAR=4, AS=5, HAS=6, ON=7, IS=8, INTO=9, WITH=10, 
		ANY=11, EXTENDS=12, CONCEPT=13, ABSTRACT=14, TERMINAL=15, COMPONENT=16, 
		FEATURE=17, FINAL=18, ENCLOSED=19, PRIVATE=20, REACTIVE=21, LOCAL=22, 
		VOLATILE=23, VERSIONED=24, LEFT_PARENTHESIS=25, RIGHT_PARENTHESIS=26, 
		LEFT_SQUARE=27, RIGHT_SQUARE=28, LEFT_CURLY=29, RIGHT_CURLY=30, INLINE=31, 
		CLOSE_INLINE=32, AT=33, HASHTAG=34, COLON=35, COMMA=36, DOT=37, EQUALS=38, 
		STAR=39, LIST=40, SEMICOLON=41, PLUS=42, WORD=43, RESOURCE=44, INT_TYPE=45, 
		FUNCTION_TYPE=46, OBJECT_TYPE=47, DOUBLE_TYPE=48, STRING_TYPE=49, BOOLEAN_TYPE=50, 
		DATE_TYPE=51, TIME_TYPE=52, EMPTY=53, BLOCK_COMMENT=54, LINE_COMMENT=55, 
		SCIENCE_NOT=56, BOOLEAN_VALUE=57, NATURAL_VALUE=58, NEGATIVE_VALUE=59, 
		DOUBLE_VALUE=60, APHOSTROPHE=61, STRING_MULTILINE=62, SINGLE_QUOTE=63, 
		EXPRESSION_MULTILINE=64, CLASS_TYPE=65, ANCHOR_VALUE=66, IDENTIFIER=67, 
		MEASURE_VALUE=68, NEWLINE=69, SPACES=70, DOC=71, SP=72, NL=73, NEW_LINE_INDENT=74, 
		DEDENT=75, UNKNOWN_TOKEN=76, QUOTE=77, Q=78, SLASH_Q=79, SLASH=80, CHARACTER=81, 
		M_QUOTE=82, M_CHARACTER=83, ME_STRING_MULTILINE=84, ME_CHARACTER=85, E_QUOTE=86, 
		E_SLASH_Q=87, E_SLASH=88, E_CHARACTER=89, QUOTE_BEGIN=90, QUOTE_END=91, 
		EXPRESSION_BEGIN=92, EXPRESSION_END=93;
	public static final int
		RULE_root = 0, RULE_dslDeclaration = 1, RULE_imports = 2, RULE_anImport = 3, 
		RULE_doc = 4, RULE_node = 5, RULE_signature = 6, RULE_parent = 7, RULE_withTable = 8, 
		RULE_tableParameters = 9, RULE_parameters = 10, RULE_parameter = 11, RULE_value = 12, 
		RULE_body = 13, RULE_facetApply = 14, RULE_facetTarget = 15, RULE_nodeReference = 16, 
		RULE_with = 17, RULE_variable = 18, RULE_bodyValue = 19, RULE_variableType = 20, 
		RULE_ruleContainer = 21, RULE_ruleValue = 22, RULE_classType = 23, RULE_range = 24, 
		RULE_size = 25, RULE_sizeRange = 26, RULE_listRange = 27, RULE_methodReference = 28, 
		RULE_stringValue = 29, RULE_booleanValue = 30, RULE_tupleValue = 31, RULE_integerValue = 32, 
		RULE_doubleValue = 33, RULE_anchor = 34, RULE_metric = 35, RULE_expression = 36, 
		RULE_tags = 37, RULE_annotations = 38, RULE_annotation = 39, RULE_flags = 40, 
		RULE_flag = 41, RULE_varInit = 42, RULE_headerReference = 43, RULE_identifierReference = 44, 
		RULE_hierarchy = 45, RULE_metaidentifier = 46;
	public static final String[] ruleNames = {
		"root", "dslDeclaration", "imports", "anImport", "doc", "node", "signature", 
		"parent", "withTable", "tableParameters", "parameters", "parameter", "value", 
		"body", "facetApply", "facetTarget", "nodeReference", "with", "variable", 
		"bodyValue", "variableType", "ruleContainer", "ruleValue", "classType", 
		"range", "size", "sizeRange", "listRange", "methodReference", "stringValue", 
		"booleanValue", "tupleValue", "integerValue", "doubleValue", "anchor", 
		"metric", "expression", "tags", "annotations", "annotation", "flags", 
		"flag", "varInit", "headerReference", "identifierReference", "hierarchy", 
		"metaidentifier"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'sub'", "'use'", "'dsl'", "'var'", "'as'", "'has'", "'on'", "'is'", 
		"'into'", "'with'", "'any'", "'extends'", "'concept'", "'abstract'", "'terminal'", 
		"'component'", "'feature'", "'final'", "'enclosed'", "'private'", "'reactive'", 
		"'local'", "'volatile'", "'versioned'", "'('", "')'", "'['", "']'", "'{'", 
		"'}'", "'>'", "'<'", "'@'", "'#'", "':'", "','", "'.'", "'='", "'*'", 
		"'...'", null, "'+'", "'word'", "'resource'", "'integer'", "'function'", 
		"'object'", "'double'", "'string'", "'boolean'", "'date'", "'time'", "'empty'", 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, "'indent'", "'dedent'", 
		null, null, "'\"'", "'\\\"'", null, null, null, null, null, null, null, 
		"'\\''", null, null, "'%QUOTE_BEGIN%'", "'%QUOTE_END%'", "'%EXPRESSION_BEGIN%'", 
		"'%EXPRESSION_END%'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "SUB", "USE", "DSL", "VAR", "AS", "HAS", "ON", "IS", "INTO", "WITH", 
		"ANY", "EXTENDS", "CONCEPT", "ABSTRACT", "TERMINAL", "COMPONENT", "FEATURE", 
		"FINAL", "ENCLOSED", "PRIVATE", "REACTIVE", "LOCAL", "VOLATILE", "VERSIONED", 
		"LEFT_PARENTHESIS", "RIGHT_PARENTHESIS", "LEFT_SQUARE", "RIGHT_SQUARE", 
		"LEFT_CURLY", "RIGHT_CURLY", "INLINE", "CLOSE_INLINE", "AT", "HASHTAG", 
		"COLON", "COMMA", "DOT", "EQUALS", "STAR", "LIST", "SEMICOLON", "PLUS", 
		"WORD", "RESOURCE", "INT_TYPE", "FUNCTION_TYPE", "OBJECT_TYPE", "DOUBLE_TYPE", 
		"STRING_TYPE", "BOOLEAN_TYPE", "DATE_TYPE", "TIME_TYPE", "EMPTY", "BLOCK_COMMENT", 
		"LINE_COMMENT", "SCIENCE_NOT", "BOOLEAN_VALUE", "NATURAL_VALUE", "NEGATIVE_VALUE", 
		"DOUBLE_VALUE", "APHOSTROPHE", "STRING_MULTILINE", "SINGLE_QUOTE", "EXPRESSION_MULTILINE", 
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
			_la = _input.LA(1);
			if (_la==DSL) {
				{
				setState(100);
				dslDeclaration();
				}
			}

			setState(104);
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
		public WithTableContext withTable() {
			return getRuleContext(WithTableContext.class,0);
		}
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
		public AnchorContext anchor() {
			return getRuleContext(AnchorContext.class,0);
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
		public List<FacetApplyContext> facetApply() {
			return getRuleContexts(FacetApplyContext.class);
		}
		public FacetApplyContext facetApply(int i) {
			return getRuleContext(FacetApplyContext.class,i);
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
			switch (_input.LA(1)) {
			case SUB:
				{
				{
				setState(152);
				match(SUB);
				setState(154);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(153);
					ruleContainer();
					}
				}

				setState(157);
				_la = _input.LA(1);
				if (_la==LEFT_PARENTHESIS) {
					{
					setState(156);
					parameters();
					}
				}

				setState(159);
				match(IDENTIFIER);
				setState(161);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(160);
					ruleContainer();
					}
				}

				setState(166);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==AS) {
					{
					{
					setState(163);
					facetApply();
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
				setState(171);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(170);
					ruleContainer();
					}
				}

				setState(174);
				_la = _input.LA(1);
				if (_la==LEFT_PARENTHESIS) {
					{
					setState(173);
					parameters();
					}
				}

				setState(180);
				switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
				case 1:
					{
					setState(176);
					match(IDENTIFIER);
					setState(178);
					_la = _input.LA(1);
					if (_la==COLON) {
						{
						setState(177);
						ruleContainer();
						}
					}

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
					facetApply();
					}
					}
					setState(187);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(189);
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
			setState(201);
			switch (_input.LA(1)) {
			case LIST:
				{
				setState(193);
				withTable();
				}
				break;
			case EOF:
			case SUB:
			case ON:
			case IS:
			case INTO:
			case ANCHOR_VALUE:
			case IDENTIFIER:
			case NEWLINE:
			case DOC:
			case NEW_LINE_INDENT:
				{
				setState(195);
				_la = _input.LA(1);
				if (_la==ON) {
					{
					setState(194);
					facetTarget();
					}
				}

				setState(197);
				tags();
				setState(199);
				_la = _input.LA(1);
				if (_la==ANCHOR_VALUE) {
					{
					setState(198);
					anchor();
					}
				}

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
			setState(203);
			match(EXTENDS);
			setState(204);
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
		enterRule(_localctx, 16, RULE_withTable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(206);
			match(LIST);
			setState(207);
			match(WITH);
			setState(208);
			identifierReference();
			setState(209);
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
		enterRule(_localctx, 18, RULE_tableParameters);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(211);
			match(LEFT_PARENTHESIS);
			setState(228);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(213); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(212);
					match(IDENTIFIER);
					}
					}
					setState(215); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==IDENTIFIER );
				setState(225);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(217);
					match(COMMA);
					setState(219); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(218);
						match(IDENTIFIER);
						}
						}
						setState(221); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==IDENTIFIER );
					}
					}
					setState(227);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(230);
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
		enterRule(_localctx, 20, RULE_parameters);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(232);
			match(LEFT_PARENTHESIS);
			setState(241);
			_la = _input.LA(1);
			if (((((_la - 33)) & ~0x3f) == 0 && ((1L << (_la - 33)) & ((1L << (AT - 33)) | (1L << (EMPTY - 33)) | (1L << (BOOLEAN_VALUE - 33)) | (1L << (NATURAL_VALUE - 33)) | (1L << (NEGATIVE_VALUE - 33)) | (1L << (DOUBLE_VALUE - 33)) | (1L << (IDENTIFIER - 33)) | (1L << (NEWLINE - 33)) | (1L << (QUOTE_BEGIN - 33)) | (1L << (EXPRESSION_BEGIN - 33)))) != 0)) {
				{
				setState(233);
				parameter();
				setState(238);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(234);
					match(COMMA);
					setState(235);
					parameter();
					}
					}
					setState(240);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(243);
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
		enterRule(_localctx, 22, RULE_parameter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(247);
			switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
			case 1:
				{
				setState(245);
				match(IDENTIFIER);
				setState(246);
				match(EQUALS);
				}
				break;
			}
			setState(249);
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
			setState(303);
			switch ( getInterpreter().adaptivePredict(_input,43,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(252); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(251);
						identifierReference();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(254); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
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
						stringValue();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(259); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
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
						tupleValue();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(264); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(267); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(266);
					booleanValue();
					}
					}
					setState(269); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==BOOLEAN_VALUE );
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(272); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(271);
						identifierReference();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(274); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,36,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(277); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(276);
					integerValue();
					}
					}
					setState(279); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NATURAL_VALUE || _la==NEGATIVE_VALUE );
				setState(282);
				switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
				case 1:
					{
					setState(281);
					metric();
					}
					break;
				}
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(285); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(284);
					doubleValue();
					}
					}
					setState(287); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NATURAL_VALUE) | (1L << NEGATIVE_VALUE) | (1L << DOUBLE_VALUE))) != 0) );
				setState(290);
				switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
				case 1:
					{
					setState(289);
					metric();
					}
					break;
				}
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(293); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(292);
						expression();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(295); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,41,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(298); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(297);
					methodReference();
					}
					}
					setState(300); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==AT );
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(302);
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
			setState(305);
			match(NEW_LINE_INDENT);
			setState(317); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(310);
				switch ( getInterpreter().adaptivePredict(_input,44,_ctx) ) {
				case 1:
					{
					setState(306);
					variable();
					}
					break;
				case 2:
					{
					setState(307);
					node();
					}
					break;
				case 3:
					{
					setState(308);
					varInit();
					}
					break;
				case 4:
					{
					setState(309);
					nodeReference();
					}
					break;
				}
				setState(313); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(312);
					match(NEWLINE);
					}
					}
					setState(315); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NEWLINE );
				}
				}
				setState(319); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SUB) | (1L << VAR) | (1L << HAS))) != 0) || _la==IDENTIFIER || _la==DOC );
			setState(321);
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
		public MetaidentifierContext metaidentifier() {
			return getRuleContext(MetaidentifierContext.class,0);
		}
		public ParametersContext parameters() {
			return getRuleContext(ParametersContext.class,0);
		}
		public WithContext with() {
			return getRuleContext(WithContext.class,0);
		}
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
		enterRule(_localctx, 28, RULE_facetApply);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(323);
			match(AS);
			setState(324);
			metaidentifier();
			setState(326);
			_la = _input.LA(1);
			if (_la==LEFT_PARENTHESIS) {
				{
				setState(325);
				parameters();
				}
			}

			setState(329);
			_la = _input.LA(1);
			if (_la==WITH) {
				{
				setState(328);
				with();
				}
			}

			setState(332);
			switch ( getInterpreter().adaptivePredict(_input,49,_ctx) ) {
			case 1:
				{
				setState(331);
				body();
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
		enterRule(_localctx, 30, RULE_facetTarget);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(334);
			match(ON);
			setState(337);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				{
				setState(335);
				identifierReference();
				}
				break;
			case ANY:
				{
				setState(336);
				match(ANY);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(340);
			_la = _input.LA(1);
			if (_la==WITH) {
				{
				setState(339);
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
		enterRule(_localctx, 32, RULE_nodeReference);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(342);
			match(HAS);
			setState(344);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(343);
				ruleContainer();
				}
			}

			setState(346);
			identifierReference();
			setState(348);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(347);
				ruleContainer();
				}
			}

			setState(350);
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
		enterRule(_localctx, 34, RULE_with);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(352);
			match(WITH);
			setState(353);
			identifierReference();
			setState(358);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(354);
				match(COMMA);
				setState(355);
				identifierReference();
				}
				}
				setState(360);
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
		public AnchorContext anchor() {
			return getRuleContext(AnchorContext.class,0);
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
		enterRule(_localctx, 36, RULE_variable);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(362);
			_la = _input.LA(1);
			if (_la==DOC) {
				{
				setState(361);
				doc();
				}
			}

			setState(364);
			match(VAR);
			setState(365);
			variableType();
			setState(367);
			_la = _input.LA(1);
			if (_la==LEFT_SQUARE) {
				{
				setState(366);
				size();
				}
			}

			setState(370);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(369);
				ruleContainer();
				}
			}

			setState(372);
			match(IDENTIFIER);
			setState(378);
			_la = _input.LA(1);
			if (_la==EQUALS) {
				{
				setState(373);
				match(EQUALS);
				setState(374);
				value();
				setState(376);
				_la = _input.LA(1);
				if (_la==IDENTIFIER || _la==MEASURE_VALUE) {
					{
					setState(375);
					metric();
					}
				}

				}
			}

			setState(381);
			_la = _input.LA(1);
			if (_la==IS) {
				{
				setState(380);
				flags();
				}
			}

			setState(384);
			_la = _input.LA(1);
			if (_la==ANCHOR_VALUE) {
				{
				setState(383);
				anchor();
				}
			}

			setState(387);
			_la = _input.LA(1);
			if (_la==NEW_LINE_INDENT) {
				{
				setState(386);
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
		enterRule(_localctx, 38, RULE_bodyValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(389);
			match(NEW_LINE_INDENT);
			setState(392);
			switch ( getInterpreter().adaptivePredict(_input,63,_ctx) ) {
			case 1:
				{
				setState(390);
				stringValue();
				}
				break;
			case 2:
				{
				setState(391);
				expression();
				}
				break;
			}
			setState(395);
			_la = _input.LA(1);
			if (_la==NEWLINE) {
				{
				setState(394);
				match(NEWLINE);
				}
			}

			setState(397);
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
		enterRule(_localctx, 40, RULE_variableType);
		try {
			setState(410);
			switch (_input.LA(1)) {
			case INT_TYPE:
				enterOuterAlt(_localctx, 1);
				{
				setState(399);
				match(INT_TYPE);
				}
				break;
			case DOUBLE_TYPE:
				enterOuterAlt(_localctx, 2);
				{
				setState(400);
				match(DOUBLE_TYPE);
				}
				break;
			case BOOLEAN_TYPE:
				enterOuterAlt(_localctx, 3);
				{
				setState(401);
				match(BOOLEAN_TYPE);
				}
				break;
			case STRING_TYPE:
				enterOuterAlt(_localctx, 4);
				{
				setState(402);
				match(STRING_TYPE);
				}
				break;
			case FUNCTION_TYPE:
				enterOuterAlt(_localctx, 5);
				{
				setState(403);
				match(FUNCTION_TYPE);
				}
				break;
			case OBJECT_TYPE:
				enterOuterAlt(_localctx, 6);
				{
				setState(404);
				match(OBJECT_TYPE);
				}
				break;
			case WORD:
				enterOuterAlt(_localctx, 7);
				{
				setState(405);
				match(WORD);
				}
				break;
			case DATE_TYPE:
				enterOuterAlt(_localctx, 8);
				{
				setState(406);
				match(DATE_TYPE);
				}
				break;
			case TIME_TYPE:
				enterOuterAlt(_localctx, 9);
				{
				setState(407);
				match(TIME_TYPE);
				}
				break;
			case RESOURCE:
				enterOuterAlt(_localctx, 10);
				{
				setState(408);
				match(RESOURCE);
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 11);
				{
				setState(409);
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
		enterRule(_localctx, 42, RULE_ruleContainer);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(412);
			match(COLON);
			setState(413);
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
		enterRule(_localctx, 44, RULE_ruleValue);
		int _la;
		try {
			setState(434);
			switch (_input.LA(1)) {
			case LEFT_CURLY:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(415);
				match(LEFT_CURLY);
				setState(430);
				switch ( getInterpreter().adaptivePredict(_input,69,_ctx) ) {
				case 1:
					{
					setState(416);
					classType();
					}
					break;
				case 2:
					{
					setState(418); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(417);
						match(IDENTIFIER);
						}
						}
						setState(420); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==IDENTIFIER );
					}
					break;
				case 3:
					{
					{
					setState(424);
					switch (_input.LA(1)) {
					case STAR:
					case NATURAL_VALUE:
					case NEGATIVE_VALUE:
					case DOUBLE_VALUE:
						{
						setState(422);
						range();
						}
						break;
					case NEWLINE:
					case QUOTE_BEGIN:
						{
						setState(423);
						stringValue();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(427);
					_la = _input.LA(1);
					if (_la==IDENTIFIER || _la==MEASURE_VALUE) {
						{
						setState(426);
						metric();
						}
					}

					}
					}
					break;
				case 4:
					{
					setState(429);
					metric();
					}
					break;
				}
				setState(432);
				match(RIGHT_CURLY);
				}
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(433);
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
		enterRule(_localctx, 46, RULE_classType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(436);
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
		enterRule(_localctx, 48, RULE_range);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(441);
			switch ( getInterpreter().adaptivePredict(_input,71,_ctx) ) {
			case 1:
				{
				setState(438);
				doubleValue();
				}
				break;
			case 2:
				{
				setState(439);
				integerValue();
				}
				break;
			case 3:
				{
				setState(440);
				match(STAR);
				}
				break;
			}
			setState(443);
			match(DOT);
			setState(444);
			match(DOT);
			setState(448);
			switch ( getInterpreter().adaptivePredict(_input,72,_ctx) ) {
			case 1:
				{
				setState(445);
				doubleValue();
				}
				break;
			case 2:
				{
				setState(446);
				integerValue();
				}
				break;
			case 3:
				{
				setState(447);
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
		enterRule(_localctx, 50, RULE_size);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(450);
			match(LEFT_SQUARE);
			setState(452);
			_la = _input.LA(1);
			if (_la==STAR || _la==NATURAL_VALUE) {
				{
				setState(451);
				sizeRange();
				}
			}

			setState(454);
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
		enterRule(_localctx, 52, RULE_sizeRange);
		try {
			setState(458);
			switch ( getInterpreter().adaptivePredict(_input,74,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(456);
				match(NATURAL_VALUE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(457);
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
		enterRule(_localctx, 54, RULE_listRange);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(460);
			_la = _input.LA(1);
			if ( !(_la==STAR || _la==NATURAL_VALUE) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			setState(461);
			match(DOT);
			setState(462);
			match(DOT);
			setState(463);
			_la = _input.LA(1);
			if ( !(_la==STAR || _la==NATURAL_VALUE) ) {
			_errHandler.recoverInline(this);
			} else {
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
		enterRule(_localctx, 56, RULE_methodReference);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(465);
			match(AT);
			setState(466);
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
		enterRule(_localctx, 58, RULE_stringValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(469);
			_la = _input.LA(1);
			if (_la==NEWLINE) {
				{
				setState(468);
				match(NEWLINE);
				}
			}

			{
			setState(471);
			match(QUOTE_BEGIN);
			setState(475);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==CHARACTER) {
				{
				{
				setState(472);
				match(CHARACTER);
				}
				}
				setState(477);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(478);
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
		enterRule(_localctx, 60, RULE_booleanValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(480);
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
		enterRule(_localctx, 62, RULE_tupleValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(482);
			stringValue();
			setState(483);
			match(COLON);
			setState(484);
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
		enterRule(_localctx, 64, RULE_integerValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(486);
			_la = _input.LA(1);
			if ( !(_la==NATURAL_VALUE || _la==NEGATIVE_VALUE) ) {
			_errHandler.recoverInline(this);
			} else {
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
		enterRule(_localctx, 66, RULE_doubleValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(488);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NATURAL_VALUE) | (1L << NEGATIVE_VALUE) | (1L << DOUBLE_VALUE))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			setState(490);
			_la = _input.LA(1);
			if (_la==SCIENCE_NOT) {
				{
				setState(489);
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

	public static class AnchorContext extends ParserRuleContext {
		public TerminalNode ANCHOR_VALUE() { return getToken(TaraGrammar.ANCHOR_VALUE, 0); }
		public AnchorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_anchor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterAnchor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitAnchor(this);
		}
	}

	public final AnchorContext anchor() throws RecognitionException {
		AnchorContext _localctx = new AnchorContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_anchor);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(492);
			match(ANCHOR_VALUE);
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
			setState(494);
			_la = _input.LA(1);
			if ( !(_la==IDENTIFIER || _la==MEASURE_VALUE) ) {
			_errHandler.recoverInline(this);
			} else {
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
			setState(497);
			_la = _input.LA(1);
			if (_la==NEWLINE) {
				{
				setState(496);
				match(NEWLINE);
				}
			}

			{
			setState(499);
			match(EXPRESSION_BEGIN);
			setState(503);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==CHARACTER) {
				{
				{
				setState(500);
				match(CHARACTER);
				}
				}
				setState(505);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(506);
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
			setState(509);
			_la = _input.LA(1);
			if (_la==IS) {
				{
				setState(508);
				flags();
				}
			}

			setState(512);
			_la = _input.LA(1);
			if (_la==INTO) {
				{
				setState(511);
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
			setState(514);
			match(INTO);
			setState(516); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(515);
				annotation();
				}
				}
				setState(518); 
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
			setState(520);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << COMPONENT) | (1L << FEATURE) | (1L << ENCLOSED))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
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
			setState(522);
			match(IS);
			setState(524); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(523);
				flag();
				}
				}
				setState(526); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << CONCEPT) | (1L << ABSTRACT) | (1L << TERMINAL) | (1L << COMPONENT) | (1L << FEATURE) | (1L << FINAL) | (1L << ENCLOSED) | (1L << PRIVATE) | (1L << REACTIVE) | (1L << LOCAL) | (1L << VOLATILE) | (1L << VERSIONED))) != 0) );
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
		public TerminalNode LOCAL() { return getToken(TaraGrammar.LOCAL, 0); }
		public TerminalNode VOLATILE() { return getToken(TaraGrammar.VOLATILE, 0); }
		public TerminalNode VERSIONED() { return getToken(TaraGrammar.VERSIONED, 0); }
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
			setState(528);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << CONCEPT) | (1L << ABSTRACT) | (1L << TERMINAL) | (1L << COMPONENT) | (1L << FEATURE) | (1L << FINAL) | (1L << ENCLOSED) | (1L << PRIVATE) | (1L << REACTIVE) | (1L << LOCAL) | (1L << VOLATILE) | (1L << VERSIONED))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
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
			setState(530);
			match(IDENTIFIER);
			setState(534);
			switch (_input.LA(1)) {
			case EQUALS:
				{
				{
				setState(531);
				match(EQUALS);
				setState(532);
				value();
				}
				}
				break;
			case NEW_LINE_INDENT:
				{
				setState(533);
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
			setState(539);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,85,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(536);
					hierarchy();
					}
					} 
				}
				setState(541);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,85,_ctx);
			}
			setState(542);
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
			setState(547);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,86,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(544);
					hierarchy();
					}
					} 
				}
				setState(549);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,86,_ctx);
			}
			setState(550);
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
			setState(552);
			match(IDENTIFIER);
			setState(553);
			_la = _input.LA(1);
			if ( !(_la==DOT || _la==PLUS) ) {
			_errHandler.recoverInline(this);
			} else {
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
			setState(555);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3_\u0230\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\3\2\7\2b\n\2\f\2\16\2e\13\2\3\2\5\2h"+
		"\n\2\3\2\5\2k\n\2\3\2\3\2\7\2o\n\2\f\2\16\2r\13\2\7\2t\n\2\f\2\16\2w\13"+
		"\2\3\2\3\2\3\3\3\3\3\3\7\3~\n\3\f\3\16\3\u0081\13\3\3\4\6\4\u0084\n\4"+
		"\r\4\16\4\u0085\3\5\3\5\3\5\6\5\u008b\n\5\r\5\16\5\u008c\3\6\6\6\u0090"+
		"\n\6\r\6\16\6\u0091\3\7\5\7\u0095\n\7\3\7\3\7\5\7\u0099\n\7\3\b\3\b\5"+
		"\b\u009d\n\b\3\b\5\b\u00a0\n\b\3\b\3\b\5\b\u00a4\n\b\3\b\7\b\u00a7\n\b"+
		"\f\b\16\b\u00aa\13\b\3\b\3\b\5\b\u00ae\n\b\3\b\5\b\u00b1\n\b\3\b\3\b\5"+
		"\b\u00b5\n\b\5\b\u00b7\n\b\3\b\7\b\u00ba\n\b\f\b\16\b\u00bd\13\b\3\b\5"+
		"\b\u00c0\n\b\5\b\u00c2\n\b\3\b\3\b\5\b\u00c6\n\b\3\b\3\b\5\b\u00ca\n\b"+
		"\5\b\u00cc\n\b\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13\6\13\u00d8\n"+
		"\13\r\13\16\13\u00d9\3\13\3\13\6\13\u00de\n\13\r\13\16\13\u00df\7\13\u00e2"+
		"\n\13\f\13\16\13\u00e5\13\13\5\13\u00e7\n\13\3\13\3\13\3\f\3\f\3\f\3\f"+
		"\7\f\u00ef\n\f\f\f\16\f\u00f2\13\f\5\f\u00f4\n\f\3\f\3\f\3\r\3\r\5\r\u00fa"+
		"\n\r\3\r\3\r\3\16\6\16\u00ff\n\16\r\16\16\16\u0100\3\16\6\16\u0104\n\16"+
		"\r\16\16\16\u0105\3\16\6\16\u0109\n\16\r\16\16\16\u010a\3\16\6\16\u010e"+
		"\n\16\r\16\16\16\u010f\3\16\6\16\u0113\n\16\r\16\16\16\u0114\3\16\6\16"+
		"\u0118\n\16\r\16\16\16\u0119\3\16\5\16\u011d\n\16\3\16\6\16\u0120\n\16"+
		"\r\16\16\16\u0121\3\16\5\16\u0125\n\16\3\16\6\16\u0128\n\16\r\16\16\16"+
		"\u0129\3\16\6\16\u012d\n\16\r\16\16\16\u012e\3\16\5\16\u0132\n\16\3\17"+
		"\3\17\3\17\3\17\3\17\5\17\u0139\n\17\3\17\6\17\u013c\n\17\r\17\16\17\u013d"+
		"\6\17\u0140\n\17\r\17\16\17\u0141\3\17\3\17\3\20\3\20\3\20\5\20\u0149"+
		"\n\20\3\20\5\20\u014c\n\20\3\20\5\20\u014f\n\20\3\21\3\21\3\21\5\21\u0154"+
		"\n\21\3\21\5\21\u0157\n\21\3\22\3\22\5\22\u015b\n\22\3\22\3\22\5\22\u015f"+
		"\n\22\3\22\3\22\3\23\3\23\3\23\3\23\7\23\u0167\n\23\f\23\16\23\u016a\13"+
		"\23\3\24\5\24\u016d\n\24\3\24\3\24\3\24\5\24\u0172\n\24\3\24\5\24\u0175"+
		"\n\24\3\24\3\24\3\24\3\24\5\24\u017b\n\24\5\24\u017d\n\24\3\24\5\24\u0180"+
		"\n\24\3\24\5\24\u0183\n\24\3\24\5\24\u0186\n\24\3\25\3\25\3\25\5\25\u018b"+
		"\n\25\3\25\5\25\u018e\n\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26"+
		"\3\26\3\26\3\26\3\26\5\26\u019d\n\26\3\27\3\27\3\27\3\30\3\30\3\30\6\30"+
		"\u01a5\n\30\r\30\16\30\u01a6\3\30\3\30\5\30\u01ab\n\30\3\30\5\30\u01ae"+
		"\n\30\3\30\5\30\u01b1\n\30\3\30\3\30\5\30\u01b5\n\30\3\31\3\31\3\32\3"+
		"\32\3\32\5\32\u01bc\n\32\3\32\3\32\3\32\3\32\3\32\5\32\u01c3\n\32\3\33"+
		"\3\33\5\33\u01c7\n\33\3\33\3\33\3\34\3\34\5\34\u01cd\n\34\3\35\3\35\3"+
		"\35\3\35\3\35\3\36\3\36\3\36\3\37\5\37\u01d8\n\37\3\37\3\37\7\37\u01dc"+
		"\n\37\f\37\16\37\u01df\13\37\3\37\3\37\3 \3 \3!\3!\3!\3!\3\"\3\"\3#\3"+
		"#\5#\u01ed\n#\3$\3$\3%\3%\3&\5&\u01f4\n&\3&\3&\7&\u01f8\n&\f&\16&\u01fb"+
		"\13&\3&\3&\3\'\5\'\u0200\n\'\3\'\5\'\u0203\n\'\3(\3(\6(\u0207\n(\r(\16"+
		"(\u0208\3)\3)\3*\3*\6*\u020f\n*\r*\16*\u0210\3+\3+\3,\3,\3,\3,\5,\u0219"+
		"\n,\3-\7-\u021c\n-\f-\16-\u021f\13-\3-\3-\3.\7.\u0224\n.\f.\16.\u0227"+
		"\13.\3.\3.\3/\3/\3/\3\60\3\60\3\60\2\2\61\2\4\6\b\n\f\16\20\22\24\26\30"+
		"\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJLNPRTVXZ\\^\2\t\4\2))<<\3\2<="+
		"\3\2<>\3\2EF\4\2\22\23\25\25\3\2\17\32\4\2\'\',,\u026e\2c\3\2\2\2\4z\3"+
		"\2\2\2\6\u0083\3\2\2\2\b\u0087\3\2\2\2\n\u008f\3\2\2\2\f\u0094\3\2\2\2"+
		"\16\u00c1\3\2\2\2\20\u00cd\3\2\2\2\22\u00d0\3\2\2\2\24\u00d5\3\2\2\2\26"+
		"\u00ea\3\2\2\2\30\u00f9\3\2\2\2\32\u0131\3\2\2\2\34\u0133\3\2\2\2\36\u0145"+
		"\3\2\2\2 \u0150\3\2\2\2\"\u0158\3\2\2\2$\u0162\3\2\2\2&\u016c\3\2\2\2"+
		"(\u0187\3\2\2\2*\u019c\3\2\2\2,\u019e\3\2\2\2.\u01b4\3\2\2\2\60\u01b6"+
		"\3\2\2\2\62\u01bb\3\2\2\2\64\u01c4\3\2\2\2\66\u01cc\3\2\2\28\u01ce\3\2"+
		"\2\2:\u01d3\3\2\2\2<\u01d7\3\2\2\2>\u01e2\3\2\2\2@\u01e4\3\2\2\2B\u01e8"+
		"\3\2\2\2D\u01ea\3\2\2\2F\u01ee\3\2\2\2H\u01f0\3\2\2\2J\u01f3\3\2\2\2L"+
		"\u01ff\3\2\2\2N\u0204\3\2\2\2P\u020a\3\2\2\2R\u020c\3\2\2\2T\u0212\3\2"+
		"\2\2V\u0214\3\2\2\2X\u021d\3\2\2\2Z\u0225\3\2\2\2\\\u022a\3\2\2\2^\u022d"+
		"\3\2\2\2`b\7G\2\2a`\3\2\2\2be\3\2\2\2ca\3\2\2\2cd\3\2\2\2dg\3\2\2\2ec"+
		"\3\2\2\2fh\5\4\3\2gf\3\2\2\2gh\3\2\2\2hj\3\2\2\2ik\5\6\4\2ji\3\2\2\2j"+
		"k\3\2\2\2ku\3\2\2\2lp\5\f\7\2mo\7G\2\2nm\3\2\2\2or\3\2\2\2pn\3\2\2\2p"+
		"q\3\2\2\2qt\3\2\2\2rp\3\2\2\2sl\3\2\2\2tw\3\2\2\2us\3\2\2\2uv\3\2\2\2"+
		"vx\3\2\2\2wu\3\2\2\2xy\7\2\2\3y\3\3\2\2\2z{\7\5\2\2{\177\5X-\2|~\7G\2"+
		"\2}|\3\2\2\2~\u0081\3\2\2\2\177}\3\2\2\2\177\u0080\3\2\2\2\u0080\5\3\2"+
		"\2\2\u0081\177\3\2\2\2\u0082\u0084\5\b\5\2\u0083\u0082\3\2\2\2\u0084\u0085"+
		"\3\2\2\2\u0085\u0083\3\2\2\2\u0085\u0086\3\2\2\2\u0086\7\3\2\2\2\u0087"+
		"\u0088\7\4\2\2\u0088\u008a\5X-\2\u0089\u008b\7G\2\2\u008a\u0089\3\2\2"+
		"\2\u008b\u008c\3\2\2\2\u008c\u008a\3\2\2\2\u008c\u008d\3\2\2\2\u008d\t"+
		"\3\2\2\2\u008e\u0090\7I\2\2\u008f\u008e\3\2\2\2\u0090\u0091\3\2\2\2\u0091"+
		"\u008f\3\2\2\2\u0091\u0092\3\2\2\2\u0092\13\3\2\2\2\u0093\u0095\5\n\6"+
		"\2\u0094\u0093\3\2\2\2\u0094\u0095\3\2\2\2\u0095\u0096\3\2\2\2\u0096\u0098"+
		"\5\16\b\2\u0097\u0099\5\34\17\2\u0098\u0097\3\2\2\2\u0098\u0099\3\2\2"+
		"\2\u0099\r\3\2\2\2\u009a\u009c\7\3\2\2\u009b\u009d\5,\27\2\u009c\u009b"+
		"\3\2\2\2\u009c\u009d\3\2\2\2\u009d\u009f\3\2\2\2\u009e\u00a0\5\26\f\2"+
		"\u009f\u009e\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\u00a3"+
		"\7E\2\2\u00a2\u00a4\5,\27\2\u00a3\u00a2\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4"+
		"\u00a8\3\2\2\2\u00a5\u00a7\5\36\20\2\u00a6\u00a5\3\2\2\2\u00a7\u00aa\3"+
		"\2\2\2\u00a8\u00a6\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9\u00c2\3\2\2\2\u00aa"+
		"\u00a8\3\2\2\2\u00ab\u00ad\5^\60\2\u00ac\u00ae\5,\27\2\u00ad\u00ac\3\2"+
		"\2\2\u00ad\u00ae\3\2\2\2\u00ae\u00b0\3\2\2\2\u00af\u00b1\5\26\f\2\u00b0"+
		"\u00af\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1\u00b6\3\2\2\2\u00b2\u00b4\7E"+
		"\2\2\u00b3\u00b5\5,\27\2\u00b4\u00b3\3\2\2\2\u00b4\u00b5\3\2\2\2\u00b5"+
		"\u00b7\3\2\2\2\u00b6\u00b2\3\2\2\2\u00b6\u00b7\3\2\2\2\u00b7\u00bb\3\2"+
		"\2\2\u00b8\u00ba\5\36\20\2\u00b9\u00b8\3\2\2\2\u00ba\u00bd\3\2\2\2\u00bb"+
		"\u00b9\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc\u00bf\3\2\2\2\u00bd\u00bb\3\2"+
		"\2\2\u00be\u00c0\5\20\t\2\u00bf\u00be\3\2\2\2\u00bf\u00c0\3\2\2\2\u00c0"+
		"\u00c2\3\2\2\2\u00c1\u009a\3\2\2\2\u00c1\u00ab\3\2\2\2\u00c2\u00cb\3\2"+
		"\2\2\u00c3\u00cc\5\22\n\2\u00c4\u00c6\5 \21\2\u00c5\u00c4\3\2\2\2\u00c5"+
		"\u00c6\3\2\2\2\u00c6\u00c7\3\2\2\2\u00c7\u00c9\5L\'\2\u00c8\u00ca\5F$"+
		"\2\u00c9\u00c8\3\2\2\2\u00c9\u00ca\3\2\2\2\u00ca\u00cc\3\2\2\2\u00cb\u00c3"+
		"\3\2\2\2\u00cb\u00c5\3\2\2\2\u00cc\17\3\2\2\2\u00cd\u00ce\7\16\2\2\u00ce"+
		"\u00cf\5Z.\2\u00cf\21\3\2\2\2\u00d0\u00d1\7*\2\2\u00d1\u00d2\7\f\2\2\u00d2"+
		"\u00d3\5Z.\2\u00d3\u00d4\5\24\13\2\u00d4\23\3\2\2\2\u00d5\u00e6\7\33\2"+
		"\2\u00d6\u00d8\7E\2\2\u00d7\u00d6\3\2\2\2\u00d8\u00d9\3\2\2\2\u00d9\u00d7"+
		"\3\2\2\2\u00d9\u00da\3\2\2\2\u00da\u00e3\3\2\2\2\u00db\u00dd\7&\2\2\u00dc"+
		"\u00de\7E\2\2\u00dd\u00dc\3\2\2\2\u00de\u00df\3\2\2\2\u00df\u00dd\3\2"+
		"\2\2\u00df\u00e0\3\2\2\2\u00e0\u00e2\3\2\2\2\u00e1\u00db\3\2\2\2\u00e2"+
		"\u00e5\3\2\2\2\u00e3\u00e1\3\2\2\2\u00e3\u00e4\3\2\2\2\u00e4\u00e7\3\2"+
		"\2\2\u00e5\u00e3\3\2\2\2\u00e6\u00d7\3\2\2\2\u00e6\u00e7\3\2\2\2\u00e7"+
		"\u00e8\3\2\2\2\u00e8\u00e9\7\34\2\2\u00e9\25\3\2\2\2\u00ea\u00f3\7\33"+
		"\2\2\u00eb\u00f0\5\30\r\2\u00ec\u00ed\7&\2\2\u00ed\u00ef\5\30\r\2\u00ee"+
		"\u00ec\3\2\2\2\u00ef\u00f2\3\2\2\2\u00f0\u00ee\3\2\2\2\u00f0\u00f1\3\2"+
		"\2\2\u00f1\u00f4\3\2\2\2\u00f2\u00f0\3\2\2\2\u00f3\u00eb\3\2\2\2\u00f3"+
		"\u00f4\3\2\2\2\u00f4\u00f5\3\2\2\2\u00f5\u00f6\7\34\2\2\u00f6\27\3\2\2"+
		"\2\u00f7\u00f8\7E\2\2\u00f8\u00fa\7(\2\2\u00f9\u00f7\3\2\2\2\u00f9\u00fa"+
		"\3\2\2\2\u00fa\u00fb\3\2\2\2\u00fb\u00fc\5\32\16\2\u00fc\31\3\2\2\2\u00fd"+
		"\u00ff\5Z.\2\u00fe\u00fd\3\2\2\2\u00ff\u0100\3\2\2\2\u0100\u00fe\3\2\2"+
		"\2\u0100\u0101\3\2\2\2\u0101\u0132\3\2\2\2\u0102\u0104\5<\37\2\u0103\u0102"+
		"\3\2\2\2\u0104\u0105\3\2\2\2\u0105\u0103\3\2\2\2\u0105\u0106\3\2\2\2\u0106"+
		"\u0132\3\2\2\2\u0107\u0109\5@!\2\u0108\u0107\3\2\2\2\u0109\u010a\3\2\2"+
		"\2\u010a\u0108\3\2\2\2\u010a\u010b\3\2\2\2\u010b\u0132\3\2\2\2\u010c\u010e"+
		"\5> \2\u010d\u010c\3\2\2\2\u010e\u010f\3\2\2\2\u010f\u010d\3\2\2\2\u010f"+
		"\u0110\3\2\2\2\u0110\u0132\3\2\2\2\u0111\u0113\5Z.\2\u0112\u0111\3\2\2"+
		"\2\u0113\u0114\3\2\2\2\u0114\u0112\3\2\2\2\u0114\u0115\3\2\2\2\u0115\u0132"+
		"\3\2\2\2\u0116\u0118\5B\"\2\u0117\u0116\3\2\2\2\u0118\u0119\3\2\2\2\u0119"+
		"\u0117\3\2\2\2\u0119\u011a\3\2\2\2\u011a\u011c\3\2\2\2\u011b\u011d\5H"+
		"%\2\u011c\u011b\3\2\2\2\u011c\u011d\3\2\2\2\u011d\u0132\3\2\2\2\u011e"+
		"\u0120\5D#\2\u011f\u011e\3\2\2\2\u0120\u0121\3\2\2\2\u0121\u011f\3\2\2"+
		"\2\u0121\u0122\3\2\2\2\u0122\u0124\3\2\2\2\u0123\u0125\5H%\2\u0124\u0123"+
		"\3\2\2\2\u0124\u0125\3\2\2\2\u0125\u0132\3\2\2\2\u0126\u0128\5J&\2\u0127"+
		"\u0126\3\2\2\2\u0128\u0129\3\2\2\2\u0129\u0127\3\2\2\2\u0129\u012a\3\2"+
		"\2\2\u012a\u0132\3\2\2\2\u012b\u012d\5:\36\2\u012c\u012b\3\2\2\2\u012d"+
		"\u012e\3\2\2\2\u012e\u012c\3\2\2\2\u012e\u012f\3\2\2\2\u012f\u0132\3\2"+
		"\2\2\u0130\u0132\7\67\2\2\u0131\u00fe\3\2\2\2\u0131\u0103\3\2\2\2\u0131"+
		"\u0108\3\2\2\2\u0131\u010d\3\2\2\2\u0131\u0112\3\2\2\2\u0131\u0117\3\2"+
		"\2\2\u0131\u011f\3\2\2\2\u0131\u0127\3\2\2\2\u0131\u012c\3\2\2\2\u0131"+
		"\u0130\3\2\2\2\u0132\33\3\2\2\2\u0133\u013f\7L\2\2\u0134\u0139\5&\24\2"+
		"\u0135\u0139\5\f\7\2\u0136\u0139\5V,\2\u0137\u0139\5\"\22\2\u0138\u0134"+
		"\3\2\2\2\u0138\u0135\3\2\2\2\u0138\u0136\3\2\2\2\u0138\u0137\3\2\2\2\u0139"+
		"\u013b\3\2\2\2\u013a\u013c\7G\2\2\u013b\u013a\3\2\2\2\u013c\u013d\3\2"+
		"\2\2\u013d\u013b\3\2\2\2\u013d\u013e\3\2\2\2\u013e\u0140\3\2\2\2\u013f"+
		"\u0138\3\2\2\2\u0140\u0141\3\2\2\2\u0141\u013f\3\2\2\2\u0141\u0142\3\2"+
		"\2\2\u0142\u0143\3\2\2\2\u0143\u0144\7M\2\2\u0144\35\3\2\2\2\u0145\u0146"+
		"\7\7\2\2\u0146\u0148\5^\60\2\u0147\u0149\5\26\f\2\u0148\u0147\3\2\2\2"+
		"\u0148\u0149\3\2\2\2\u0149\u014b\3\2\2\2\u014a\u014c\5$\23\2\u014b\u014a"+
		"\3\2\2\2\u014b\u014c\3\2\2\2\u014c\u014e\3\2\2\2\u014d\u014f\5\34\17\2"+
		"\u014e\u014d\3\2\2\2\u014e\u014f\3\2\2\2\u014f\37\3\2\2\2\u0150\u0153"+
		"\7\t\2\2\u0151\u0154\5Z.\2\u0152\u0154\7\r\2\2\u0153\u0151\3\2\2\2\u0153"+
		"\u0152\3\2\2\2\u0154\u0156\3\2\2\2\u0155\u0157\5$\23\2\u0156\u0155\3\2"+
		"\2\2\u0156\u0157\3\2\2\2\u0157!\3\2\2\2\u0158\u015a\7\b\2\2\u0159\u015b"+
		"\5,\27\2\u015a\u0159\3\2\2\2\u015a\u015b\3\2\2\2\u015b\u015c\3\2\2\2\u015c"+
		"\u015e\5Z.\2\u015d\u015f\5,\27\2\u015e\u015d\3\2\2\2\u015e\u015f\3\2\2"+
		"\2\u015f\u0160\3\2\2\2\u0160\u0161\5L\'\2\u0161#\3\2\2\2\u0162\u0163\7"+
		"\f\2\2\u0163\u0168\5Z.\2\u0164\u0165\7&\2\2\u0165\u0167\5Z.\2\u0166\u0164"+
		"\3\2\2\2\u0167\u016a\3\2\2\2\u0168\u0166\3\2\2\2\u0168\u0169\3\2\2\2\u0169"+
		"%\3\2\2\2\u016a\u0168\3\2\2\2\u016b\u016d\5\n\6\2\u016c\u016b\3\2\2\2"+
		"\u016c\u016d\3\2\2\2\u016d\u016e\3\2\2\2\u016e\u016f\7\6\2\2\u016f\u0171"+
		"\5*\26\2\u0170\u0172\5\64\33\2\u0171\u0170\3\2\2\2\u0171\u0172\3\2\2\2"+
		"\u0172\u0174\3\2\2\2\u0173\u0175\5,\27\2\u0174\u0173\3\2\2\2\u0174\u0175"+
		"\3\2\2\2\u0175\u0176\3\2\2\2\u0176\u017c\7E\2\2\u0177\u0178\7(\2\2\u0178"+
		"\u017a\5\32\16\2\u0179\u017b\5H%\2\u017a\u0179\3\2\2\2\u017a\u017b\3\2"+
		"\2\2\u017b\u017d\3\2\2\2\u017c\u0177\3\2\2\2\u017c\u017d\3\2\2\2\u017d"+
		"\u017f\3\2\2\2\u017e\u0180\5R*\2\u017f\u017e\3\2\2\2\u017f\u0180\3\2\2"+
		"\2\u0180\u0182\3\2\2\2\u0181\u0183\5F$\2\u0182\u0181\3\2\2\2\u0182\u0183"+
		"\3\2\2\2\u0183\u0185\3\2\2\2\u0184\u0186\5(\25\2\u0185\u0184\3\2\2\2\u0185"+
		"\u0186\3\2\2\2\u0186\'\3\2\2\2\u0187\u018a\7L\2\2\u0188\u018b\5<\37\2"+
		"\u0189\u018b\5J&\2\u018a\u0188\3\2\2\2\u018a\u0189\3\2\2\2\u018b\u018d"+
		"\3\2\2\2\u018c\u018e\7G\2\2\u018d\u018c\3\2\2\2\u018d\u018e\3\2\2\2\u018e"+
		"\u018f\3\2\2\2\u018f\u0190\7M\2\2\u0190)\3\2\2\2\u0191\u019d\7/\2\2\u0192"+
		"\u019d\7\62\2\2\u0193\u019d\7\64\2\2\u0194\u019d\7\63\2\2\u0195\u019d"+
		"\7\60\2\2\u0196\u019d\7\61\2\2\u0197\u019d\7-\2\2\u0198\u019d\7\65\2\2"+
		"\u0199\u019d\7\66\2\2\u019a\u019d\7.\2\2\u019b\u019d\5Z.\2\u019c\u0191"+
		"\3\2\2\2\u019c\u0192\3\2\2\2\u019c\u0193\3\2\2\2\u019c\u0194\3\2\2\2\u019c"+
		"\u0195\3\2\2\2\u019c\u0196\3\2\2\2\u019c\u0197\3\2\2\2\u019c\u0198\3\2"+
		"\2\2\u019c\u0199\3\2\2\2\u019c\u019a\3\2\2\2\u019c\u019b\3\2\2\2\u019d"+
		"+\3\2\2\2\u019e\u019f\7%\2\2\u019f\u01a0\5.\30\2\u01a0-\3\2\2\2\u01a1"+
		"\u01b0\7\37\2\2\u01a2\u01b1\5\60\31\2\u01a3\u01a5\7E\2\2\u01a4\u01a3\3"+
		"\2\2\2\u01a5\u01a6\3\2\2\2\u01a6\u01a4\3\2\2\2\u01a6\u01a7\3\2\2\2\u01a7"+
		"\u01b1\3\2\2\2\u01a8\u01ab\5\62\32\2\u01a9\u01ab\5<\37\2\u01aa\u01a8\3"+
		"\2\2\2\u01aa\u01a9\3\2\2\2\u01ab\u01ad\3\2\2\2\u01ac\u01ae\5H%\2\u01ad"+
		"\u01ac\3\2\2\2\u01ad\u01ae\3\2\2\2\u01ae\u01b1\3\2\2\2\u01af\u01b1\5H"+
		"%\2\u01b0\u01a2\3\2\2\2\u01b0\u01a4\3\2\2\2\u01b0\u01aa\3\2\2\2\u01b0"+
		"\u01af\3\2\2\2\u01b1\u01b2\3\2\2\2\u01b2\u01b5\7 \2\2\u01b3\u01b5\5Z."+
		"\2\u01b4\u01a1\3\2\2\2\u01b4\u01b3\3\2\2\2\u01b5/\3\2\2\2\u01b6\u01b7"+
		"\7C\2\2\u01b7\61\3\2\2\2\u01b8\u01bc\5D#\2\u01b9\u01bc\5B\"\2\u01ba\u01bc"+
		"\7)\2\2\u01bb\u01b8\3\2\2\2\u01bb\u01b9\3\2\2\2\u01bb\u01ba\3\2\2\2\u01bc"+
		"\u01bd\3\2\2\2\u01bd\u01be\7\'\2\2\u01be\u01c2\7\'\2\2\u01bf\u01c3\5D"+
		"#\2\u01c0\u01c3\5B\"\2\u01c1\u01c3\7)\2\2\u01c2\u01bf\3\2\2\2\u01c2\u01c0"+
		"\3\2\2\2\u01c2\u01c1\3\2\2\2\u01c3\63\3\2\2\2\u01c4\u01c6\7\35\2\2\u01c5"+
		"\u01c7\5\66\34\2\u01c6\u01c5\3\2\2\2\u01c6\u01c7\3\2\2\2\u01c7\u01c8\3"+
		"\2\2\2\u01c8\u01c9\7\36\2\2\u01c9\65\3\2\2\2\u01ca\u01cd\7<\2\2\u01cb"+
		"\u01cd\58\35\2\u01cc\u01ca\3\2\2\2\u01cc\u01cb\3\2\2\2\u01cd\67\3\2\2"+
		"\2\u01ce\u01cf\t\2\2\2\u01cf\u01d0\7\'\2\2\u01d0\u01d1\7\'\2\2\u01d1\u01d2"+
		"\t\2\2\2\u01d29\3\2\2\2\u01d3\u01d4\7#\2\2\u01d4\u01d5\5Z.\2\u01d5;\3"+
		"\2\2\2\u01d6\u01d8\7G\2\2\u01d7\u01d6\3\2\2\2\u01d7\u01d8\3\2\2\2\u01d8"+
		"\u01d9\3\2\2\2\u01d9\u01dd\7\\\2\2\u01da\u01dc\7S\2\2\u01db\u01da\3\2"+
		"\2\2\u01dc\u01df\3\2\2\2\u01dd\u01db\3\2\2\2\u01dd\u01de\3\2\2\2\u01de"+
		"\u01e0\3\2\2\2\u01df\u01dd\3\2\2\2\u01e0\u01e1\7]\2\2\u01e1=\3\2\2\2\u01e2"+
		"\u01e3\7;\2\2\u01e3?\3\2\2\2\u01e4\u01e5\5<\37\2\u01e5\u01e6\7%\2\2\u01e6"+
		"\u01e7\5D#\2\u01e7A\3\2\2\2\u01e8\u01e9\t\3\2\2\u01e9C\3\2\2\2\u01ea\u01ec"+
		"\t\4\2\2\u01eb\u01ed\7:\2\2\u01ec\u01eb\3\2\2\2\u01ec\u01ed\3\2\2\2\u01ed"+
		"E\3\2\2\2\u01ee\u01ef\7D\2\2\u01efG\3\2\2\2\u01f0\u01f1\t\5\2\2\u01f1"+
		"I\3\2\2\2\u01f2\u01f4\7G\2\2\u01f3\u01f2\3\2\2\2\u01f3\u01f4\3\2\2\2\u01f4"+
		"\u01f5\3\2\2\2\u01f5\u01f9\7^\2\2\u01f6\u01f8\7S\2\2\u01f7\u01f6\3\2\2"+
		"\2\u01f8\u01fb\3\2\2\2\u01f9\u01f7\3\2\2\2\u01f9\u01fa\3\2\2\2\u01fa\u01fc"+
		"\3\2\2\2\u01fb\u01f9\3\2\2\2\u01fc\u01fd\7_\2\2\u01fdK\3\2\2\2\u01fe\u0200"+
		"\5R*\2\u01ff\u01fe\3\2\2\2\u01ff\u0200\3\2\2\2\u0200\u0202\3\2\2\2\u0201"+
		"\u0203\5N(\2\u0202\u0201\3\2\2\2\u0202\u0203\3\2\2\2\u0203M\3\2\2\2\u0204"+
		"\u0206\7\13\2\2\u0205\u0207\5P)\2\u0206\u0205\3\2\2\2\u0207\u0208\3\2"+
		"\2\2\u0208\u0206\3\2\2\2\u0208\u0209\3\2\2\2\u0209O\3\2\2\2\u020a\u020b"+
		"\t\6\2\2\u020bQ\3\2\2\2\u020c\u020e\7\n\2\2\u020d\u020f\5T+\2\u020e\u020d"+
		"\3\2\2\2\u020f\u0210\3\2\2\2\u0210\u020e\3\2\2\2\u0210\u0211\3\2\2\2\u0211"+
		"S\3\2\2\2\u0212\u0213\t\7\2\2\u0213U\3\2\2\2\u0214\u0218\7E\2\2\u0215"+
		"\u0216\7(\2\2\u0216\u0219\5\32\16\2\u0217\u0219\5(\25\2\u0218\u0215\3"+
		"\2\2\2\u0218\u0217\3\2\2\2\u0219W\3\2\2\2\u021a\u021c\5\\/\2\u021b\u021a"+
		"\3\2\2\2\u021c\u021f\3\2\2\2\u021d\u021b\3\2\2\2\u021d\u021e\3\2\2\2\u021e"+
		"\u0220\3\2\2\2\u021f\u021d\3\2\2\2\u0220\u0221\7E\2\2\u0221Y\3\2\2\2\u0222"+
		"\u0224\5\\/\2\u0223\u0222\3\2\2\2\u0224\u0227\3\2\2\2\u0225\u0223\3\2"+
		"\2\2\u0225\u0226\3\2\2\2\u0226\u0228\3\2\2\2\u0227\u0225\3\2\2\2\u0228"+
		"\u0229\7E\2\2\u0229[\3\2\2\2\u022a\u022b\7E\2\2\u022b\u022c\t\b\2\2\u022c"+
		"]\3\2\2\2\u022d\u022e\7E\2\2\u022e_\3\2\2\2Ycgjpu\177\u0085\u008c\u0091"+
		"\u0094\u0098\u009c\u009f\u00a3\u00a8\u00ad\u00b0\u00b4\u00b6\u00bb\u00bf"+
		"\u00c1\u00c5\u00c9\u00cb\u00d9\u00df\u00e3\u00e6\u00f0\u00f3\u00f9\u0100"+
		"\u0105\u010a\u010f\u0114\u0119\u011c\u0121\u0124\u0129\u012e\u0131\u0138"+
		"\u013d\u0141\u0148\u014b\u014e\u0153\u0156\u015a\u015e\u0168\u016c\u0171"+
		"\u0174\u017a\u017c\u017f\u0182\u0185\u018a\u018d\u019c\u01a6\u01aa\u01ad"+
		"\u01b0\u01b4\u01bb\u01c2\u01c6\u01cc\u01d7\u01dd\u01ec\u01f3\u01f9\u01ff"+
		"\u0202\u0208\u0210\u0218\u021d\u0225";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}