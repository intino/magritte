// Generated from /Users/oroncal/workspace/tara/language/grammar/src/tara/lang/grammar/TaraGrammar.g4 by ANTLR 4.5.1
package tara.lang.grammar;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TaraGrammar extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		SUB=1, USE=2, DSL=3, VAR=4, AS=5, HAS=6, ON=7, IS=8, INTO=9, WITH=10, 
		ANY=11, EXTENDS=12, CONCEPT=13, ABSTRACT=14, TERMINAL=15, COMPONENT=16, 
		PROTOTYPE=17, FEATURE=18, FINAL=19, ENCLOSED=20, PRIVATE=21, NATIVE=22, 
		LEFT_PARENTHESIS=23, RIGHT_PARENTHESIS=24, LEFT_SQUARE=25, RIGHT_SQUARE=26, 
		LEFT_CURLY=27, RIGHT_CURLY=28, INLINE=29, CLOSE_INLINE=30, HASHTAG=31, 
		COLON=32, COMMA=33, DOT=34, EQUALS=35, STAR=36, LIST=37, SEMICOLON=38, 
		PLUS=39, WORD=40, RESOURCE=41, INT_TYPE=42, TUPLE_TYPE=43, FUNCTION_TYPE=44, 
		DOUBLE_TYPE=45, STRING_TYPE=46, BOOLEAN_TYPE=47, DATE_TYPE=48, TIME_TYPE=49, 
		EMPTY=50, BLOCK_COMMENT=51, LINE_COMMENT=52, SCIENCE_NOT=53, BOOLEAN_VALUE=54, 
		NATURAL_VALUE=55, NEGATIVE_VALUE=56, DOUBLE_VALUE=57, APHOSTROPHE=58, 
		STRING_MULTILINE=59, SINGLE_QUOTE=60, EXPRESSION_MULTILINE=61, ANCHOR_VALUE=62, 
		IDENTIFIER=63, MEASURE_VALUE=64, NEWLINE=65, SPACES=66, DOC=67, SP=68, 
		NL=69, NEW_LINE_INDENT=70, DEDENT=71, UNKNOWN_TOKEN=72, QUOTE=73, Q=74, 
		SLASH_Q=75, SLASH=76, CHARACTER=77, M_QUOTE=78, M_CHARACTER=79, ME_STRING_MULTILINE=80, 
		ME_CHARACTER=81, E_QUOTE=82, E_SLASH_Q=83, E_SLASH=84, E_CHARACTER=85, 
		QUOTE_BEGIN=86, QUOTE_END=87, EXPRESSION_BEGIN=88, EXPRESSION_END=89;
	public static final int
		RULE_root = 0, RULE_dslDeclaration = 1, RULE_imports = 2, RULE_anImport = 3, 
		RULE_doc = 4, RULE_node = 5, RULE_signature = 6, RULE_parent = 7, RULE_withTable = 8, 
		RULE_tableParameters = 9, RULE_parameters = 10, RULE_parameter = 11, RULE_value = 12, 
		RULE_body = 13, RULE_facetApply = 14, RULE_facetTarget = 15, RULE_nodeReference = 16, 
		RULE_with = 17, RULE_variable = 18, RULE_bodyValue = 19, RULE_variableType = 20, 
		RULE_ruleContainer = 21, RULE_ruleValue = 22, RULE_range = 23, RULE_size = 24, 
		RULE_sizeRange = 25, RULE_listRange = 26, RULE_stringValue = 27, RULE_booleanValue = 28, 
		RULE_tupleValue = 29, RULE_integerValue = 30, RULE_doubleValue = 31, RULE_anchor = 32, 
		RULE_metric = 33, RULE_expression = 34, RULE_tags = 35, RULE_annotations = 36, 
		RULE_annotation = 37, RULE_flags = 38, RULE_flag = 39, RULE_varInit = 40, 
		RULE_headerReference = 41, RULE_identifierReference = 42, RULE_hierarchy = 43, 
		RULE_metaidentifier = 44;
	public static final String[] ruleNames = {
		"root", "dslDeclaration", "imports", "anImport", "doc", "node", "signature", 
		"parent", "withTable", "tableParameters", "parameters", "parameter", "value", 
		"body", "facetApply", "facetTarget", "nodeReference", "with", "variable", 
		"bodyValue", "variableType", "ruleContainer", "ruleValue", "range", "size", 
		"sizeRange", "listRange", "stringValue", "booleanValue", "tupleValue", 
		"integerValue", "doubleValue", "anchor", "metric", "expression", "tags", 
		"annotations", "annotation", "flags", "flag", "varInit", "headerReference", 
		"identifierReference", "hierarchy", "metaidentifier"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'sub'", "'use'", "'dsl'", "'var'", "'as'", "'has'", "'on'", "'is'", 
		"'into'", "'with'", "'any'", "'extends'", "'concept'", "'abstract'", "'terminal'", 
		"'component'", "'prototype'", "'feature'", "'final'", "'enclosed'", "'private'", 
		"'native'", "'('", "')'", "'['", "']'", "'{'", "'}'", "'>'", "'<'", "'#'", 
		"':'", "','", "'.'", "'='", "'*'", "'...'", null, "'+'", "'word'", "'resource'", 
		"'integer'", "'tuple'", "'function'", "'double'", "'string'", "'boolean'", 
		"'date'", "'time'", "'empty'", null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		"'indent'", "'dedent'", null, null, "'\"'", "'\\\"'", null, null, null, 
		null, null, null, null, "'\\''", null, null, "'%QUOTE_BEGIN%'", "'%QUOTE_END%'", 
		"'%EXPRESSION_BEGIN%'", "'%EXPRESSION_END%'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "SUB", "USE", "DSL", "VAR", "AS", "HAS", "ON", "IS", "INTO", "WITH", 
		"ANY", "EXTENDS", "CONCEPT", "ABSTRACT", "TERMINAL", "COMPONENT", "PROTOTYPE", 
		"FEATURE", "FINAL", "ENCLOSED", "PRIVATE", "NATIVE", "LEFT_PARENTHESIS", 
		"RIGHT_PARENTHESIS", "LEFT_SQUARE", "RIGHT_SQUARE", "LEFT_CURLY", "RIGHT_CURLY", 
		"INLINE", "CLOSE_INLINE", "HASHTAG", "COLON", "COMMA", "DOT", "EQUALS", 
		"STAR", "LIST", "SEMICOLON", "PLUS", "WORD", "RESOURCE", "INT_TYPE", "TUPLE_TYPE", 
		"FUNCTION_TYPE", "DOUBLE_TYPE", "STRING_TYPE", "BOOLEAN_TYPE", "DATE_TYPE", 
		"TIME_TYPE", "EMPTY", "BLOCK_COMMENT", "LINE_COMMENT", "SCIENCE_NOT", 
		"BOOLEAN_VALUE", "NATURAL_VALUE", "NEGATIVE_VALUE", "DOUBLE_VALUE", "APHOSTROPHE", 
		"STRING_MULTILINE", "SINGLE_QUOTE", "EXPRESSION_MULTILINE", "ANCHOR_VALUE", 
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
			_la = _input.LA(1);
			if (_la==DSL) {
				{
				setState(96);
				dslDeclaration();
				}
			}

			setState(100);
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
			setState(175);
			switch (_input.LA(1)) {
			case SUB:
				{
				{
				setState(148);
				match(SUB);
				setState(150);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(149);
					ruleContainer();
					}
				}

				setState(153);
				_la = _input.LA(1);
				if (_la==LEFT_PARENTHESIS) {
					{
					setState(152);
					parameters();
					}
				}

				setState(155);
				match(IDENTIFIER);
				setState(157);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(156);
					ruleContainer();
					}
				}

				}
				}
				break;
			case IDENTIFIER:
				{
				{
				setState(159);
				metaidentifier();
				setState(161);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(160);
					ruleContainer();
					}
				}

				setState(164);
				_la = _input.LA(1);
				if (_la==LEFT_PARENTHESIS) {
					{
					setState(163);
					parameters();
					}
				}

				setState(170);
				switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
				case 1:
					{
					setState(166);
					match(IDENTIFIER);
					setState(168);
					_la = _input.LA(1);
					if (_la==COLON) {
						{
						setState(167);
						ruleContainer();
						}
					}

					}
					break;
				}
				setState(173);
				_la = _input.LA(1);
				if (_la==EXTENDS) {
					{
					setState(172);
					parent();
					}
				}

				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(185);
			switch (_input.LA(1)) {
			case LIST:
				{
				setState(177);
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
				setState(179);
				_la = _input.LA(1);
				if (_la==ON) {
					{
					setState(178);
					facetTarget();
					}
				}

				setState(181);
				tags();
				setState(183);
				_la = _input.LA(1);
				if (_la==ANCHOR_VALUE) {
					{
					setState(182);
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
			setState(187);
			match(EXTENDS);
			setState(188);
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
			setState(190);
			match(LIST);
			setState(191);
			match(WITH);
			setState(192);
			identifierReference();
			setState(193);
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
			setState(195);
			match(LEFT_PARENTHESIS);
			setState(212);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(197); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(196);
					match(IDENTIFIER);
					}
					}
					setState(199); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==IDENTIFIER );
				setState(209);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(201);
					match(COMMA);
					setState(203); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(202);
						match(IDENTIFIER);
						}
						}
						setState(205); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==IDENTIFIER );
					}
					}
					setState(211);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(214);
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
			setState(216);
			match(LEFT_PARENTHESIS);
			setState(225);
			_la = _input.LA(1);
			if (((((_la - 50)) & ~0x3f) == 0 && ((1L << (_la - 50)) & ((1L << (EMPTY - 50)) | (1L << (BOOLEAN_VALUE - 50)) | (1L << (NATURAL_VALUE - 50)) | (1L << (NEGATIVE_VALUE - 50)) | (1L << (DOUBLE_VALUE - 50)) | (1L << (IDENTIFIER - 50)) | (1L << (NEWLINE - 50)) | (1L << (QUOTE_BEGIN - 50)) | (1L << (EXPRESSION_BEGIN - 50)))) != 0)) {
				{
				setState(217);
				parameter();
				setState(222);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(218);
					match(COMMA);
					setState(219);
					parameter();
					}
					}
					setState(224);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(227);
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
			setState(231);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				{
				setState(229);
				match(IDENTIFIER);
				setState(230);
				match(EQUALS);
				}
				break;
			}
			setState(233);
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
			setState(282);
			switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(236); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(235);
						identifierReference();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(238); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(241); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(240);
						stringValue();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(243); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(246); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(245);
						tupleValue();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(248); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(251); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(250);
					booleanValue();
					}
					}
					setState(253); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==BOOLEAN_VALUE );
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(256); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(255);
						identifierReference();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(258); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(261); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(260);
					integerValue();
					}
					}
					setState(263); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NATURAL_VALUE || _la==NEGATIVE_VALUE );
				setState(266);
				switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
				case 1:
					{
					setState(265);
					metric();
					}
					break;
				}
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(269); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(268);
					doubleValue();
					}
					}
					setState(271); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NATURAL_VALUE) | (1L << NEGATIVE_VALUE) | (1L << DOUBLE_VALUE))) != 0) );
				setState(274);
				switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
				case 1:
					{
					setState(273);
					metric();
					}
					break;
				}
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
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
						expression();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(279); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,39,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(281);
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
			setState(284);
			match(NEW_LINE_INDENT);
			setState(297); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(290);
				switch ( getInterpreter().adaptivePredict(_input,41,_ctx) ) {
				case 1:
					{
					setState(285);
					variable();
					}
					break;
				case 2:
					{
					setState(286);
					node();
					}
					break;
				case 3:
					{
					setState(287);
					varInit();
					}
					break;
				case 4:
					{
					setState(288);
					facetApply();
					}
					break;
				case 5:
					{
					setState(289);
					nodeReference();
					}
					break;
				}
				setState(293); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(292);
					match(NEWLINE);
					}
					}
					setState(295); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NEWLINE );
				}
				}
				setState(299); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SUB) | (1L << VAR) | (1L << AS) | (1L << HAS) | (1L << IDENTIFIER))) != 0) || _la==DOC );
			setState(301);
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
			setState(303);
			match(AS);
			setState(304);
			metaidentifier();
			setState(306);
			_la = _input.LA(1);
			if (_la==LEFT_PARENTHESIS) {
				{
				setState(305);
				parameters();
				}
			}

			setState(309);
			_la = _input.LA(1);
			if (_la==WITH) {
				{
				setState(308);
				with();
				}
			}

			setState(312);
			_la = _input.LA(1);
			if (_la==NEW_LINE_INDENT) {
				{
				setState(311);
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
			setState(314);
			match(ON);
			setState(317);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				{
				setState(315);
				identifierReference();
				}
				break;
			case ANY:
				{
				setState(316);
				match(ANY);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(320);
			_la = _input.LA(1);
			if (_la==WITH) {
				{
				setState(319);
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
		public RuleContainerContext ruleContainer() {
			return getRuleContext(RuleContainerContext.class,0);
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
			setState(322);
			match(HAS);
			setState(324);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(323);
				ruleContainer();
				}
			}

			setState(326);
			identifierReference();
			setState(327);
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
			setState(329);
			match(WITH);
			setState(330);
			identifierReference();
			setState(335);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(331);
				match(COMMA);
				setState(332);
				identifierReference();
				}
				}
				setState(337);
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
			setState(339);
			_la = _input.LA(1);
			if (_la==DOC) {
				{
				setState(338);
				doc();
				}
			}

			setState(341);
			match(VAR);
			setState(342);
			variableType();
			setState(344);
			_la = _input.LA(1);
			if (_la==LEFT_SQUARE) {
				{
				setState(343);
				size();
				}
			}

			setState(347);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(346);
				ruleContainer();
				}
			}

			setState(349);
			match(IDENTIFIER);
			setState(355);
			_la = _input.LA(1);
			if (_la==EQUALS) {
				{
				setState(350);
				match(EQUALS);
				setState(351);
				value();
				setState(353);
				_la = _input.LA(1);
				if (_la==IDENTIFIER || _la==MEASURE_VALUE) {
					{
					setState(352);
					metric();
					}
				}

				}
			}

			setState(358);
			_la = _input.LA(1);
			if (_la==IS) {
				{
				setState(357);
				flags();
				}
			}

			setState(361);
			_la = _input.LA(1);
			if (_la==ANCHOR_VALUE) {
				{
				setState(360);
				anchor();
				}
			}

			setState(364);
			_la = _input.LA(1);
			if (_la==NEW_LINE_INDENT) {
				{
				setState(363);
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
			setState(366);
			match(NEW_LINE_INDENT);
			setState(369);
			switch ( getInterpreter().adaptivePredict(_input,59,_ctx) ) {
			case 1:
				{
				setState(367);
				stringValue();
				}
				break;
			case 2:
				{
				setState(368);
				expression();
				}
				break;
			}
			setState(372);
			_la = _input.LA(1);
			if (_la==NEWLINE) {
				{
				setState(371);
				match(NEWLINE);
				}
			}

			setState(374);
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
		public TerminalNode WORD() { return getToken(TaraGrammar.WORD, 0); }
		public TerminalNode TUPLE_TYPE() { return getToken(TaraGrammar.TUPLE_TYPE, 0); }
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
			setState(387);
			switch (_input.LA(1)) {
			case INT_TYPE:
				enterOuterAlt(_localctx, 1);
				{
				setState(376);
				match(INT_TYPE);
				}
				break;
			case DOUBLE_TYPE:
				enterOuterAlt(_localctx, 2);
				{
				setState(377);
				match(DOUBLE_TYPE);
				}
				break;
			case BOOLEAN_TYPE:
				enterOuterAlt(_localctx, 3);
				{
				setState(378);
				match(BOOLEAN_TYPE);
				}
				break;
			case STRING_TYPE:
				enterOuterAlt(_localctx, 4);
				{
				setState(379);
				match(STRING_TYPE);
				}
				break;
			case FUNCTION_TYPE:
				enterOuterAlt(_localctx, 5);
				{
				setState(380);
				match(FUNCTION_TYPE);
				}
				break;
			case WORD:
				enterOuterAlt(_localctx, 6);
				{
				setState(381);
				match(WORD);
				}
				break;
			case TUPLE_TYPE:
				enterOuterAlt(_localctx, 7);
				{
				setState(382);
				match(TUPLE_TYPE);
				}
				break;
			case DATE_TYPE:
				enterOuterAlt(_localctx, 8);
				{
				setState(383);
				match(DATE_TYPE);
				}
				break;
			case TIME_TYPE:
				enterOuterAlt(_localctx, 9);
				{
				setState(384);
				match(TIME_TYPE);
				}
				break;
			case RESOURCE:
				enterOuterAlt(_localctx, 10);
				{
				setState(385);
				match(RESOURCE);
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 11);
				{
				setState(386);
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
			setState(389);
			match(COLON);
			setState(390);
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
			setState(410);
			switch (_input.LA(1)) {
			case LEFT_CURLY:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(392);
				match(LEFT_CURLY);
				setState(406);
				switch ( getInterpreter().adaptivePredict(_input,65,_ctx) ) {
				case 1:
					{
					setState(394); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(393);
						match(IDENTIFIER);
						}
						}
						setState(396); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==IDENTIFIER );
					}
					break;
				case 2:
					{
					{
					setState(400);
					switch (_input.LA(1)) {
					case STAR:
					case NATURAL_VALUE:
					case NEGATIVE_VALUE:
					case DOUBLE_VALUE:
						{
						setState(398);
						range();
						}
						break;
					case NEWLINE:
					case QUOTE_BEGIN:
						{
						setState(399);
						stringValue();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(403);
					_la = _input.LA(1);
					if (_la==IDENTIFIER || _la==MEASURE_VALUE) {
						{
						setState(402);
						metric();
						}
					}

					}
					}
					break;
				case 3:
					{
					setState(405);
					metric();
					}
					break;
				}
				setState(408);
				match(RIGHT_CURLY);
				}
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(409);
				match(IDENTIFIER);
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
			setState(415);
			switch ( getInterpreter().adaptivePredict(_input,67,_ctx) ) {
			case 1:
				{
				setState(412);
				doubleValue();
				}
				break;
			case 2:
				{
				setState(413);
				integerValue();
				}
				break;
			case 3:
				{
				setState(414);
				match(STAR);
				}
				break;
			}
			setState(417);
			match(DOT);
			setState(418);
			match(DOT);
			setState(422);
			switch ( getInterpreter().adaptivePredict(_input,68,_ctx) ) {
			case 1:
				{
				setState(419);
				doubleValue();
				}
				break;
			case 2:
				{
				setState(420);
				integerValue();
				}
				break;
			case 3:
				{
				setState(421);
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
			setState(424);
			match(LEFT_SQUARE);
			setState(426);
			_la = _input.LA(1);
			if (_la==STAR || _la==NATURAL_VALUE) {
				{
				setState(425);
				sizeRange();
				}
			}

			setState(428);
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
			setState(432);
			switch ( getInterpreter().adaptivePredict(_input,70,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(430);
				match(NATURAL_VALUE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(431);
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
			setState(434);
			_la = _input.LA(1);
			if ( !(_la==STAR || _la==NATURAL_VALUE) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			setState(435);
			match(DOT);
			setState(436);
			match(DOT);
			setState(437);
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
		enterRule(_localctx, 54, RULE_stringValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(440);
			_la = _input.LA(1);
			if (_la==NEWLINE) {
				{
				setState(439);
				match(NEWLINE);
				}
			}

			{
			setState(442);
			match(QUOTE_BEGIN);
			setState(446);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==CHARACTER) {
				{
				{
				setState(443);
				match(CHARACTER);
				}
				}
				setState(448);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(449);
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
		enterRule(_localctx, 56, RULE_booleanValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(451);
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
		enterRule(_localctx, 58, RULE_tupleValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(453);
			stringValue();
			setState(454);
			match(COLON);
			setState(455);
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
		enterRule(_localctx, 60, RULE_integerValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(457);
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
		enterRule(_localctx, 62, RULE_doubleValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(459);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NATURAL_VALUE) | (1L << NEGATIVE_VALUE) | (1L << DOUBLE_VALUE))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			setState(461);
			_la = _input.LA(1);
			if (_la==SCIENCE_NOT) {
				{
				setState(460);
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
		enterRule(_localctx, 64, RULE_anchor);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(463);
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
		enterRule(_localctx, 66, RULE_metric);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(465);
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
		enterRule(_localctx, 68, RULE_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(468);
			_la = _input.LA(1);
			if (_la==NEWLINE) {
				{
				setState(467);
				match(NEWLINE);
				}
			}

			{
			setState(470);
			match(EXPRESSION_BEGIN);
			setState(474);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==CHARACTER) {
				{
				{
				setState(471);
				match(CHARACTER);
				}
				}
				setState(476);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(477);
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
			setState(480);
			_la = _input.LA(1);
			if (_la==IS) {
				{
				setState(479);
				flags();
				}
			}

			setState(483);
			_la = _input.LA(1);
			if (_la==INTO) {
				{
				setState(482);
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
			setState(485);
			match(INTO);
			setState(487); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(486);
				annotation();
				}
				}
				setState(489); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << COMPONENT) | (1L << PROTOTYPE) | (1L << FEATURE) | (1L << ENCLOSED))) != 0) );
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
		public TerminalNode PROTOTYPE() { return getToken(TaraGrammar.PROTOTYPE, 0); }
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
			setState(491);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << COMPONENT) | (1L << PROTOTYPE) | (1L << FEATURE) | (1L << ENCLOSED))) != 0)) ) {
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
		enterRule(_localctx, 76, RULE_flags);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(493);
			match(IS);
			setState(495); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(494);
				flag();
				}
				}
				setState(497); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << CONCEPT) | (1L << ABSTRACT) | (1L << TERMINAL) | (1L << COMPONENT) | (1L << PROTOTYPE) | (1L << FEATURE) | (1L << FINAL) | (1L << ENCLOSED) | (1L << PRIVATE) | (1L << NATIVE))) != 0) );
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
		public TerminalNode PROTOTYPE() { return getToken(TaraGrammar.PROTOTYPE, 0); }
		public TerminalNode ENCLOSED() { return getToken(TaraGrammar.ENCLOSED, 0); }
		public TerminalNode FINAL() { return getToken(TaraGrammar.FINAL, 0); }
		public TerminalNode CONCEPT() { return getToken(TaraGrammar.CONCEPT, 0); }
		public TerminalNode NATIVE() { return getToken(TaraGrammar.NATIVE, 0); }
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
			setState(499);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << CONCEPT) | (1L << ABSTRACT) | (1L << TERMINAL) | (1L << COMPONENT) | (1L << PROTOTYPE) | (1L << FEATURE) | (1L << FINAL) | (1L << ENCLOSED) | (1L << PRIVATE) | (1L << NATIVE))) != 0)) ) {
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
		enterRule(_localctx, 80, RULE_varInit);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(501);
			match(IDENTIFIER);
			setState(505);
			switch (_input.LA(1)) {
			case EQUALS:
				{
				{
				setState(502);
				match(EQUALS);
				setState(503);
				value();
				}
				}
				break;
			case NEW_LINE_INDENT:
				{
				setState(504);
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
			setState(510);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,81,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(507);
					hierarchy();
					}
					} 
				}
				setState(512);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,81,_ctx);
			}
			setState(513);
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
			setState(518);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,82,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(515);
					hierarchy();
					}
					} 
				}
				setState(520);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,82,_ctx);
			}
			setState(521);
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
		enterRule(_localctx, 86, RULE_hierarchy);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(523);
			match(IDENTIFIER);
			setState(524);
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
			setState(526);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3[\u0213\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\3\2\7\2^\n\2\f\2\16\2a\13\2\3\2\5\2d\n\2\3\2\5\2g\n\2"+
		"\3\2\3\2\7\2k\n\2\f\2\16\2n\13\2\7\2p\n\2\f\2\16\2s\13\2\3\2\3\2\3\3\3"+
		"\3\3\3\7\3z\n\3\f\3\16\3}\13\3\3\4\6\4\u0080\n\4\r\4\16\4\u0081\3\5\3"+
		"\5\3\5\6\5\u0087\n\5\r\5\16\5\u0088\3\6\6\6\u008c\n\6\r\6\16\6\u008d\3"+
		"\7\5\7\u0091\n\7\3\7\3\7\5\7\u0095\n\7\3\b\3\b\5\b\u0099\n\b\3\b\5\b\u009c"+
		"\n\b\3\b\3\b\5\b\u00a0\n\b\3\b\3\b\5\b\u00a4\n\b\3\b\5\b\u00a7\n\b\3\b"+
		"\3\b\5\b\u00ab\n\b\5\b\u00ad\n\b\3\b\5\b\u00b0\n\b\5\b\u00b2\n\b\3\b\3"+
		"\b\5\b\u00b6\n\b\3\b\3\b\5\b\u00ba\n\b\5\b\u00bc\n\b\3\t\3\t\3\t\3\n\3"+
		"\n\3\n\3\n\3\n\3\13\3\13\6\13\u00c8\n\13\r\13\16\13\u00c9\3\13\3\13\6"+
		"\13\u00ce\n\13\r\13\16\13\u00cf\7\13\u00d2\n\13\f\13\16\13\u00d5\13\13"+
		"\5\13\u00d7\n\13\3\13\3\13\3\f\3\f\3\f\3\f\7\f\u00df\n\f\f\f\16\f\u00e2"+
		"\13\f\5\f\u00e4\n\f\3\f\3\f\3\r\3\r\5\r\u00ea\n\r\3\r\3\r\3\16\6\16\u00ef"+
		"\n\16\r\16\16\16\u00f0\3\16\6\16\u00f4\n\16\r\16\16\16\u00f5\3\16\6\16"+
		"\u00f9\n\16\r\16\16\16\u00fa\3\16\6\16\u00fe\n\16\r\16\16\16\u00ff\3\16"+
		"\6\16\u0103\n\16\r\16\16\16\u0104\3\16\6\16\u0108\n\16\r\16\16\16\u0109"+
		"\3\16\5\16\u010d\n\16\3\16\6\16\u0110\n\16\r\16\16\16\u0111\3\16\5\16"+
		"\u0115\n\16\3\16\6\16\u0118\n\16\r\16\16\16\u0119\3\16\5\16\u011d\n\16"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u0125\n\17\3\17\6\17\u0128\n\17\r"+
		"\17\16\17\u0129\6\17\u012c\n\17\r\17\16\17\u012d\3\17\3\17\3\20\3\20\3"+
		"\20\5\20\u0135\n\20\3\20\5\20\u0138\n\20\3\20\5\20\u013b\n\20\3\21\3\21"+
		"\3\21\5\21\u0140\n\21\3\21\5\21\u0143\n\21\3\22\3\22\5\22\u0147\n\22\3"+
		"\22\3\22\3\22\3\23\3\23\3\23\3\23\7\23\u0150\n\23\f\23\16\23\u0153\13"+
		"\23\3\24\5\24\u0156\n\24\3\24\3\24\3\24\5\24\u015b\n\24\3\24\5\24\u015e"+
		"\n\24\3\24\3\24\3\24\3\24\5\24\u0164\n\24\5\24\u0166\n\24\3\24\5\24\u0169"+
		"\n\24\3\24\5\24\u016c\n\24\3\24\5\24\u016f\n\24\3\25\3\25\3\25\5\25\u0174"+
		"\n\25\3\25\5\25\u0177\n\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26"+
		"\3\26\3\26\3\26\3\26\5\26\u0186\n\26\3\27\3\27\3\27\3\30\3\30\6\30\u018d"+
		"\n\30\r\30\16\30\u018e\3\30\3\30\5\30\u0193\n\30\3\30\5\30\u0196\n\30"+
		"\3\30\5\30\u0199\n\30\3\30\3\30\5\30\u019d\n\30\3\31\3\31\3\31\5\31\u01a2"+
		"\n\31\3\31\3\31\3\31\3\31\3\31\5\31\u01a9\n\31\3\32\3\32\5\32\u01ad\n"+
		"\32\3\32\3\32\3\33\3\33\5\33\u01b3\n\33\3\34\3\34\3\34\3\34\3\34\3\35"+
		"\5\35\u01bb\n\35\3\35\3\35\7\35\u01bf\n\35\f\35\16\35\u01c2\13\35\3\35"+
		"\3\35\3\36\3\36\3\37\3\37\3\37\3\37\3 \3 \3!\3!\5!\u01d0\n!\3\"\3\"\3"+
		"#\3#\3$\5$\u01d7\n$\3$\3$\7$\u01db\n$\f$\16$\u01de\13$\3$\3$\3%\5%\u01e3"+
		"\n%\3%\5%\u01e6\n%\3&\3&\6&\u01ea\n&\r&\16&\u01eb\3\'\3\'\3(\3(\6(\u01f2"+
		"\n(\r(\16(\u01f3\3)\3)\3*\3*\3*\3*\5*\u01fc\n*\3+\7+\u01ff\n+\f+\16+\u0202"+
		"\13+\3+\3+\3,\7,\u0207\n,\f,\16,\u020a\13,\3,\3,\3-\3-\3-\3.\3.\3.\2\2"+
		"/\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDF"+
		"HJLNPRTVXZ\2\b\4\2&&99\3\29:\3\29;\3\2AB\4\2\22\24\26\26\3\2\17\30\u024e"+
		"\2_\3\2\2\2\4v\3\2\2\2\6\177\3\2\2\2\b\u0083\3\2\2\2\n\u008b\3\2\2\2\f"+
		"\u0090\3\2\2\2\16\u00b1\3\2\2\2\20\u00bd\3\2\2\2\22\u00c0\3\2\2\2\24\u00c5"+
		"\3\2\2\2\26\u00da\3\2\2\2\30\u00e9\3\2\2\2\32\u011c\3\2\2\2\34\u011e\3"+
		"\2\2\2\36\u0131\3\2\2\2 \u013c\3\2\2\2\"\u0144\3\2\2\2$\u014b\3\2\2\2"+
		"&\u0155\3\2\2\2(\u0170\3\2\2\2*\u0185\3\2\2\2,\u0187\3\2\2\2.\u019c\3"+
		"\2\2\2\60\u01a1\3\2\2\2\62\u01aa\3\2\2\2\64\u01b2\3\2\2\2\66\u01b4\3\2"+
		"\2\28\u01ba\3\2\2\2:\u01c5\3\2\2\2<\u01c7\3\2\2\2>\u01cb\3\2\2\2@\u01cd"+
		"\3\2\2\2B\u01d1\3\2\2\2D\u01d3\3\2\2\2F\u01d6\3\2\2\2H\u01e2\3\2\2\2J"+
		"\u01e7\3\2\2\2L\u01ed\3\2\2\2N\u01ef\3\2\2\2P\u01f5\3\2\2\2R\u01f7\3\2"+
		"\2\2T\u0200\3\2\2\2V\u0208\3\2\2\2X\u020d\3\2\2\2Z\u0210\3\2\2\2\\^\7"+
		"C\2\2]\\\3\2\2\2^a\3\2\2\2_]\3\2\2\2_`\3\2\2\2`c\3\2\2\2a_\3\2\2\2bd\5"+
		"\4\3\2cb\3\2\2\2cd\3\2\2\2df\3\2\2\2eg\5\6\4\2fe\3\2\2\2fg\3\2\2\2gq\3"+
		"\2\2\2hl\5\f\7\2ik\7C\2\2ji\3\2\2\2kn\3\2\2\2lj\3\2\2\2lm\3\2\2\2mp\3"+
		"\2\2\2nl\3\2\2\2oh\3\2\2\2ps\3\2\2\2qo\3\2\2\2qr\3\2\2\2rt\3\2\2\2sq\3"+
		"\2\2\2tu\7\2\2\3u\3\3\2\2\2vw\7\5\2\2w{\5T+\2xz\7C\2\2yx\3\2\2\2z}\3\2"+
		"\2\2{y\3\2\2\2{|\3\2\2\2|\5\3\2\2\2}{\3\2\2\2~\u0080\5\b\5\2\177~\3\2"+
		"\2\2\u0080\u0081\3\2\2\2\u0081\177\3\2\2\2\u0081\u0082\3\2\2\2\u0082\7"+
		"\3\2\2\2\u0083\u0084\7\4\2\2\u0084\u0086\5T+\2\u0085\u0087\7C\2\2\u0086"+
		"\u0085\3\2\2\2\u0087\u0088\3\2\2\2\u0088\u0086\3\2\2\2\u0088\u0089\3\2"+
		"\2\2\u0089\t\3\2\2\2\u008a\u008c\7E\2\2\u008b\u008a\3\2\2\2\u008c\u008d"+
		"\3\2\2\2\u008d\u008b\3\2\2\2\u008d\u008e\3\2\2\2\u008e\13\3\2\2\2\u008f"+
		"\u0091\5\n\6\2\u0090\u008f\3\2\2\2\u0090\u0091\3\2\2\2\u0091\u0092\3\2"+
		"\2\2\u0092\u0094\5\16\b\2\u0093\u0095\5\34\17\2\u0094\u0093\3\2\2\2\u0094"+
		"\u0095\3\2\2\2\u0095\r\3\2\2\2\u0096\u0098\7\3\2\2\u0097\u0099\5,\27\2"+
		"\u0098\u0097\3\2\2\2\u0098\u0099\3\2\2\2\u0099\u009b\3\2\2\2\u009a\u009c"+
		"\5\26\f\2\u009b\u009a\3\2\2\2\u009b\u009c\3\2\2\2\u009c\u009d\3\2\2\2"+
		"\u009d\u009f\7A\2\2\u009e\u00a0\5,\27\2\u009f\u009e\3\2\2\2\u009f\u00a0"+
		"\3\2\2\2\u00a0\u00b2\3\2\2\2\u00a1\u00a3\5Z.\2\u00a2\u00a4\5,\27\2\u00a3"+
		"\u00a2\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4\u00a6\3\2\2\2\u00a5\u00a7\5\26"+
		"\f\2\u00a6\u00a5\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\u00ac\3\2\2\2\u00a8"+
		"\u00aa\7A\2\2\u00a9\u00ab\5,\27\2\u00aa\u00a9\3\2\2\2\u00aa\u00ab\3\2"+
		"\2\2\u00ab\u00ad\3\2\2\2\u00ac\u00a8\3\2\2\2\u00ac\u00ad\3\2\2\2\u00ad"+
		"\u00af\3\2\2\2\u00ae\u00b0\5\20\t\2\u00af\u00ae\3\2\2\2\u00af\u00b0\3"+
		"\2\2\2\u00b0\u00b2\3\2\2\2\u00b1\u0096\3\2\2\2\u00b1\u00a1\3\2\2\2\u00b2"+
		"\u00bb\3\2\2\2\u00b3\u00bc\5\22\n\2\u00b4\u00b6\5 \21\2\u00b5\u00b4\3"+
		"\2\2\2\u00b5\u00b6\3\2\2\2\u00b6\u00b7\3\2\2\2\u00b7\u00b9\5H%\2\u00b8"+
		"\u00ba\5B\"\2\u00b9\u00b8\3\2\2\2\u00b9\u00ba\3\2\2\2\u00ba\u00bc\3\2"+
		"\2\2\u00bb\u00b3\3\2\2\2\u00bb\u00b5\3\2\2\2\u00bc\17\3\2\2\2\u00bd\u00be"+
		"\7\16\2\2\u00be\u00bf\5V,\2\u00bf\21\3\2\2\2\u00c0\u00c1\7\'\2\2\u00c1"+
		"\u00c2\7\f\2\2\u00c2\u00c3\5V,\2\u00c3\u00c4\5\24\13\2\u00c4\23\3\2\2"+
		"\2\u00c5\u00d6\7\31\2\2\u00c6\u00c8\7A\2\2\u00c7\u00c6\3\2\2\2\u00c8\u00c9"+
		"\3\2\2\2\u00c9\u00c7\3\2\2\2\u00c9\u00ca\3\2\2\2\u00ca\u00d3\3\2\2\2\u00cb"+
		"\u00cd\7#\2\2\u00cc\u00ce\7A\2\2\u00cd\u00cc\3\2\2\2\u00ce\u00cf\3\2\2"+
		"\2\u00cf\u00cd\3\2\2\2\u00cf\u00d0\3\2\2\2\u00d0\u00d2\3\2\2\2\u00d1\u00cb"+
		"\3\2\2\2\u00d2\u00d5\3\2\2\2\u00d3\u00d1\3\2\2\2\u00d3\u00d4\3\2\2\2\u00d4"+
		"\u00d7\3\2\2\2\u00d5\u00d3\3\2\2\2\u00d6\u00c7\3\2\2\2\u00d6\u00d7\3\2"+
		"\2\2\u00d7\u00d8\3\2\2\2\u00d8\u00d9\7\32\2\2\u00d9\25\3\2\2\2\u00da\u00e3"+
		"\7\31\2\2\u00db\u00e0\5\30\r\2\u00dc\u00dd\7#\2\2\u00dd\u00df\5\30\r\2"+
		"\u00de\u00dc\3\2\2\2\u00df\u00e2\3\2\2\2\u00e0\u00de\3\2\2\2\u00e0\u00e1"+
		"\3\2\2\2\u00e1\u00e4\3\2\2\2\u00e2\u00e0\3\2\2\2\u00e3\u00db\3\2\2\2\u00e3"+
		"\u00e4\3\2\2\2\u00e4\u00e5\3\2\2\2\u00e5\u00e6\7\32\2\2\u00e6\27\3\2\2"+
		"\2\u00e7\u00e8\7A\2\2\u00e8\u00ea\7%\2\2\u00e9\u00e7\3\2\2\2\u00e9\u00ea"+
		"\3\2\2\2\u00ea\u00eb\3\2\2\2\u00eb\u00ec\5\32\16\2\u00ec\31\3\2\2\2\u00ed"+
		"\u00ef\5V,\2\u00ee\u00ed\3\2\2\2\u00ef\u00f0\3\2\2\2\u00f0\u00ee\3\2\2"+
		"\2\u00f0\u00f1\3\2\2\2\u00f1\u011d\3\2\2\2\u00f2\u00f4\58\35\2\u00f3\u00f2"+
		"\3\2\2\2\u00f4\u00f5\3\2\2\2\u00f5\u00f3\3\2\2\2\u00f5\u00f6\3\2\2\2\u00f6"+
		"\u011d\3\2\2\2\u00f7\u00f9\5<\37\2\u00f8\u00f7\3\2\2\2\u00f9\u00fa\3\2"+
		"\2\2\u00fa\u00f8\3\2\2\2\u00fa\u00fb\3\2\2\2\u00fb\u011d\3\2\2\2\u00fc"+
		"\u00fe\5:\36\2\u00fd\u00fc\3\2\2\2\u00fe\u00ff\3\2\2\2\u00ff\u00fd\3\2"+
		"\2\2\u00ff\u0100\3\2\2\2\u0100\u011d\3\2\2\2\u0101\u0103\5V,\2\u0102\u0101"+
		"\3\2\2\2\u0103\u0104\3\2\2\2\u0104\u0102\3\2\2\2\u0104\u0105\3\2\2\2\u0105"+
		"\u011d\3\2\2\2\u0106\u0108\5> \2\u0107\u0106\3\2\2\2\u0108\u0109\3\2\2"+
		"\2\u0109\u0107\3\2\2\2\u0109\u010a\3\2\2\2\u010a\u010c\3\2\2\2\u010b\u010d"+
		"\5D#\2\u010c\u010b\3\2\2\2\u010c\u010d\3\2\2\2\u010d\u011d\3\2\2\2\u010e"+
		"\u0110\5@!\2\u010f\u010e\3\2\2\2\u0110\u0111\3\2\2\2\u0111\u010f\3\2\2"+
		"\2\u0111\u0112\3\2\2\2\u0112\u0114\3\2\2\2\u0113\u0115\5D#\2\u0114\u0113"+
		"\3\2\2\2\u0114\u0115\3\2\2\2\u0115\u011d\3\2\2\2\u0116\u0118\5F$\2\u0117"+
		"\u0116\3\2\2\2\u0118\u0119\3\2\2\2\u0119\u0117\3\2\2\2\u0119\u011a\3\2"+
		"\2\2\u011a\u011d\3\2\2\2\u011b\u011d\7\64\2\2\u011c\u00ee\3\2\2\2\u011c"+
		"\u00f3\3\2\2\2\u011c\u00f8\3\2\2\2\u011c\u00fd\3\2\2\2\u011c\u0102\3\2"+
		"\2\2\u011c\u0107\3\2\2\2\u011c\u010f\3\2\2\2\u011c\u0117\3\2\2\2\u011c"+
		"\u011b\3\2\2\2\u011d\33\3\2\2\2\u011e\u012b\7H\2\2\u011f\u0125\5&\24\2"+
		"\u0120\u0125\5\f\7\2\u0121\u0125\5R*\2\u0122\u0125\5\36\20\2\u0123\u0125"+
		"\5\"\22\2\u0124\u011f\3\2\2\2\u0124\u0120\3\2\2\2\u0124\u0121\3\2\2\2"+
		"\u0124\u0122\3\2\2\2\u0124\u0123\3\2\2\2\u0125\u0127\3\2\2\2\u0126\u0128"+
		"\7C\2\2\u0127\u0126\3\2\2\2\u0128\u0129\3\2\2\2\u0129\u0127\3\2\2\2\u0129"+
		"\u012a\3\2\2\2\u012a\u012c\3\2\2\2\u012b\u0124\3\2\2\2\u012c\u012d\3\2"+
		"\2\2\u012d\u012b\3\2\2\2\u012d\u012e\3\2\2\2\u012e\u012f\3\2\2\2\u012f"+
		"\u0130\7I\2\2\u0130\35\3\2\2\2\u0131\u0132\7\7\2\2\u0132\u0134\5Z.\2\u0133"+
		"\u0135\5\26\f\2\u0134\u0133\3\2\2\2\u0134\u0135\3\2\2\2\u0135\u0137\3"+
		"\2\2\2\u0136\u0138\5$\23\2\u0137\u0136\3\2\2\2\u0137\u0138\3\2\2\2\u0138"+
		"\u013a\3\2\2\2\u0139\u013b\5\34\17\2\u013a\u0139\3\2\2\2\u013a\u013b\3"+
		"\2\2\2\u013b\37\3\2\2\2\u013c\u013f\7\t\2\2\u013d\u0140\5V,\2\u013e\u0140"+
		"\7\r\2\2\u013f\u013d\3\2\2\2\u013f\u013e\3\2\2\2\u0140\u0142\3\2\2\2\u0141"+
		"\u0143\5$\23\2\u0142\u0141\3\2\2\2\u0142\u0143\3\2\2\2\u0143!\3\2\2\2"+
		"\u0144\u0146\7\b\2\2\u0145\u0147\5,\27\2\u0146\u0145\3\2\2\2\u0146\u0147"+
		"\3\2\2\2\u0147\u0148\3\2\2\2\u0148\u0149\5V,\2\u0149\u014a\5H%\2\u014a"+
		"#\3\2\2\2\u014b\u014c\7\f\2\2\u014c\u0151\5V,\2\u014d\u014e\7#\2\2\u014e"+
		"\u0150\5V,\2\u014f\u014d\3\2\2\2\u0150\u0153\3\2\2\2\u0151\u014f\3\2\2"+
		"\2\u0151\u0152\3\2\2\2\u0152%\3\2\2\2\u0153\u0151\3\2\2\2\u0154\u0156"+
		"\5\n\6\2\u0155\u0154\3\2\2\2\u0155\u0156\3\2\2\2\u0156\u0157\3\2\2\2\u0157"+
		"\u0158\7\6\2\2\u0158\u015a\5*\26\2\u0159\u015b\5\62\32\2\u015a\u0159\3"+
		"\2\2\2\u015a\u015b\3\2\2\2\u015b\u015d\3\2\2\2\u015c\u015e\5,\27\2\u015d"+
		"\u015c\3\2\2\2\u015d\u015e\3\2\2\2\u015e\u015f\3\2\2\2\u015f\u0165\7A"+
		"\2\2\u0160\u0161\7%\2\2\u0161\u0163\5\32\16\2\u0162\u0164\5D#\2\u0163"+
		"\u0162\3\2\2\2\u0163\u0164\3\2\2\2\u0164\u0166\3\2\2\2\u0165\u0160\3\2"+
		"\2\2\u0165\u0166\3\2\2\2\u0166\u0168\3\2\2\2\u0167\u0169\5N(\2\u0168\u0167"+
		"\3\2\2\2\u0168\u0169\3\2\2\2\u0169\u016b\3\2\2\2\u016a\u016c\5B\"\2\u016b"+
		"\u016a\3\2\2\2\u016b\u016c\3\2\2\2\u016c\u016e\3\2\2\2\u016d\u016f\5("+
		"\25\2\u016e\u016d\3\2\2\2\u016e\u016f\3\2\2\2\u016f\'\3\2\2\2\u0170\u0173"+
		"\7H\2\2\u0171\u0174\58\35\2\u0172\u0174\5F$\2\u0173\u0171\3\2\2\2\u0173"+
		"\u0172\3\2\2\2\u0174\u0176\3\2\2\2\u0175\u0177\7C\2\2\u0176\u0175\3\2"+
		"\2\2\u0176\u0177\3\2\2\2\u0177\u0178\3\2\2\2\u0178\u0179\7I\2\2\u0179"+
		")\3\2\2\2\u017a\u0186\7,\2\2\u017b\u0186\7/\2\2\u017c\u0186\7\61\2\2\u017d"+
		"\u0186\7\60\2\2\u017e\u0186\7.\2\2\u017f\u0186\7*\2\2\u0180\u0186\7-\2"+
		"\2\u0181\u0186\7\62\2\2\u0182\u0186\7\63\2\2\u0183\u0186\7+\2\2\u0184"+
		"\u0186\5V,\2\u0185\u017a\3\2\2\2\u0185\u017b\3\2\2\2\u0185\u017c\3\2\2"+
		"\2\u0185\u017d\3\2\2\2\u0185\u017e\3\2\2\2\u0185\u017f\3\2\2\2\u0185\u0180"+
		"\3\2\2\2\u0185\u0181\3\2\2\2\u0185\u0182\3\2\2\2\u0185\u0183\3\2\2\2\u0185"+
		"\u0184\3\2\2\2\u0186+\3\2\2\2\u0187\u0188\7\"\2\2\u0188\u0189\5.\30\2"+
		"\u0189-\3\2\2\2\u018a\u0198\7\35\2\2\u018b\u018d\7A\2\2\u018c\u018b\3"+
		"\2\2\2\u018d\u018e\3\2\2\2\u018e\u018c\3\2\2\2\u018e\u018f\3\2\2\2\u018f"+
		"\u0199\3\2\2\2\u0190\u0193\5\60\31\2\u0191\u0193\58\35\2\u0192\u0190\3"+
		"\2\2\2\u0192\u0191\3\2\2\2\u0193\u0195\3\2\2\2\u0194\u0196\5D#\2\u0195"+
		"\u0194\3\2\2\2\u0195\u0196\3\2\2\2\u0196\u0199\3\2\2\2\u0197\u0199\5D"+
		"#\2\u0198\u018c\3\2\2\2\u0198\u0192\3\2\2\2\u0198\u0197\3\2\2\2\u0199"+
		"\u019a\3\2\2\2\u019a\u019d\7\36\2\2\u019b\u019d\7A\2\2\u019c\u018a\3\2"+
		"\2\2\u019c\u019b\3\2\2\2\u019d/\3\2\2\2\u019e\u01a2\5@!\2\u019f\u01a2"+
		"\5> \2\u01a0\u01a2\7&\2\2\u01a1\u019e\3\2\2\2\u01a1\u019f\3\2\2\2\u01a1"+
		"\u01a0\3\2\2\2\u01a2\u01a3\3\2\2\2\u01a3\u01a4\7$\2\2\u01a4\u01a8\7$\2"+
		"\2\u01a5\u01a9\5@!\2\u01a6\u01a9\5> \2\u01a7\u01a9\7&\2\2\u01a8\u01a5"+
		"\3\2\2\2\u01a8\u01a6\3\2\2\2\u01a8\u01a7\3\2\2\2\u01a9\61\3\2\2\2\u01aa"+
		"\u01ac\7\33\2\2\u01ab\u01ad\5\64\33\2\u01ac\u01ab\3\2\2\2\u01ac\u01ad"+
		"\3\2\2\2\u01ad\u01ae\3\2\2\2\u01ae\u01af\7\34\2\2\u01af\63\3\2\2\2\u01b0"+
		"\u01b3\79\2\2\u01b1\u01b3\5\66\34\2\u01b2\u01b0\3\2\2\2\u01b2\u01b1\3"+
		"\2\2\2\u01b3\65\3\2\2\2\u01b4\u01b5\t\2\2\2\u01b5\u01b6\7$\2\2\u01b6\u01b7"+
		"\7$\2\2\u01b7\u01b8\t\2\2\2\u01b8\67\3\2\2\2\u01b9\u01bb\7C\2\2\u01ba"+
		"\u01b9\3\2\2\2\u01ba\u01bb\3\2\2\2\u01bb\u01bc\3\2\2\2\u01bc\u01c0\7X"+
		"\2\2\u01bd\u01bf\7O\2\2\u01be\u01bd\3\2\2\2\u01bf\u01c2\3\2\2\2\u01c0"+
		"\u01be\3\2\2\2\u01c0\u01c1\3\2\2\2\u01c1\u01c3\3\2\2\2\u01c2\u01c0\3\2"+
		"\2\2\u01c3\u01c4\7Y\2\2\u01c49\3\2\2\2\u01c5\u01c6\78\2\2\u01c6;\3\2\2"+
		"\2\u01c7\u01c8\58\35\2\u01c8\u01c9\7\"\2\2\u01c9\u01ca\5@!\2\u01ca=\3"+
		"\2\2\2\u01cb\u01cc\t\3\2\2\u01cc?\3\2\2\2\u01cd\u01cf\t\4\2\2\u01ce\u01d0"+
		"\7\67\2\2\u01cf\u01ce\3\2\2\2\u01cf\u01d0\3\2\2\2\u01d0A\3\2\2\2\u01d1"+
		"\u01d2\7@\2\2\u01d2C\3\2\2\2\u01d3\u01d4\t\5\2\2\u01d4E\3\2\2\2\u01d5"+
		"\u01d7\7C\2\2\u01d6\u01d5\3\2\2\2\u01d6\u01d7\3\2\2\2\u01d7\u01d8\3\2"+
		"\2\2\u01d8\u01dc\7Z\2\2\u01d9\u01db\7O\2\2\u01da\u01d9\3\2\2\2\u01db\u01de"+
		"\3\2\2\2\u01dc\u01da\3\2\2\2\u01dc\u01dd\3\2\2\2\u01dd\u01df\3\2\2\2\u01de"+
		"\u01dc\3\2\2\2\u01df\u01e0\7[\2\2\u01e0G\3\2\2\2\u01e1\u01e3\5N(\2\u01e2"+
		"\u01e1\3\2\2\2\u01e2\u01e3\3\2\2\2\u01e3\u01e5\3\2\2\2\u01e4\u01e6\5J"+
		"&\2\u01e5\u01e4\3\2\2\2\u01e5\u01e6\3\2\2\2\u01e6I\3\2\2\2\u01e7\u01e9"+
		"\7\13\2\2\u01e8\u01ea\5L\'\2\u01e9\u01e8\3\2\2\2\u01ea\u01eb\3\2\2\2\u01eb"+
		"\u01e9\3\2\2\2\u01eb\u01ec\3\2\2\2\u01ecK\3\2\2\2\u01ed\u01ee\t\6\2\2"+
		"\u01eeM\3\2\2\2\u01ef\u01f1\7\n\2\2\u01f0\u01f2\5P)\2\u01f1\u01f0\3\2"+
		"\2\2\u01f2\u01f3\3\2\2\2\u01f3\u01f1\3\2\2\2\u01f3\u01f4\3\2\2\2\u01f4"+
		"O\3\2\2\2\u01f5\u01f6\t\7\2\2\u01f6Q\3\2\2\2\u01f7\u01fb\7A\2\2\u01f8"+
		"\u01f9\7%\2\2\u01f9\u01fc\5\32\16\2\u01fa\u01fc\5(\25\2\u01fb\u01f8\3"+
		"\2\2\2\u01fb\u01fa\3\2\2\2\u01fcS\3\2\2\2\u01fd\u01ff\5X-\2\u01fe\u01fd"+
		"\3\2\2\2\u01ff\u0202\3\2\2\2\u0200\u01fe\3\2\2\2\u0200\u0201\3\2\2\2\u0201"+
		"\u0203\3\2\2\2\u0202\u0200\3\2\2\2\u0203\u0204\7A\2\2\u0204U\3\2\2\2\u0205"+
		"\u0207\5X-\2\u0206\u0205\3\2\2\2\u0207\u020a\3\2\2\2\u0208\u0206\3\2\2"+
		"\2\u0208\u0209\3\2\2\2\u0209\u020b\3\2\2\2\u020a\u0208\3\2\2\2\u020b\u020c"+
		"\7A\2\2\u020cW\3\2\2\2\u020d\u020e\7A\2\2\u020e\u020f\7$\2\2\u020fY\3"+
		"\2\2\2\u0210\u0211\7A\2\2\u0211[\3\2\2\2U_cflq{\u0081\u0088\u008d\u0090"+
		"\u0094\u0098\u009b\u009f\u00a3\u00a6\u00aa\u00ac\u00af\u00b1\u00b5\u00b9"+
		"\u00bb\u00c9\u00cf\u00d3\u00d6\u00e0\u00e3\u00e9\u00f0\u00f5\u00fa\u00ff"+
		"\u0104\u0109\u010c\u0111\u0114\u0119\u011c\u0124\u0129\u012d\u0134\u0137"+
		"\u013a\u013f\u0142\u0146\u0151\u0155\u015a\u015d\u0163\u0165\u0168\u016b"+
		"\u016e\u0173\u0176\u0185\u018e\u0192\u0195\u0198\u019c\u01a1\u01a8\u01ac"+
		"\u01b2\u01ba\u01c0\u01cf\u01d6\u01dc\u01e2\u01e5\u01eb\u01f3\u01fb\u0200"+
		"\u0208";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}