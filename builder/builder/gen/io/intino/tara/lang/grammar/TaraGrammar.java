// Generated from /Users/oroncal/workspace/tara/core/language/src/io/intino/tara/lang/grammar/TaraGrammar.g4 by ANTLR 4.7.2
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
	static {
		RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION);
	}

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
			new PredictionContextCache();
	public static final int
			SUB = 1, USE = 2, DSL = 3, VAR = 4, AS = 5, HAS = 6, IS = 7, INTO = 8, WITH = 9, ANY = 10,
			EXTENDS = 11, CONCEPT = 12, ABSTRACT = 13, TERMINAL = 14, COMPONENT = 15, FEATURE = 16,
			DIVINE = 17, REQUIRED = 18, FINAL = 19, ENCLOSED = 20, PRIVATE = 21, REACTIVE = 22,
			VOLATILE = 23, DECORABLE = 24, LEFT_PARENTHESIS = 25, RIGHT_PARENTHESIS = 26,
			LEFT_SQUARE = 27, RIGHT_SQUARE = 28, LEFT_CURLY = 29, RIGHT_CURLY = 30, INLINE = 31,
			CLOSE_INLINE = 32, AT = 33, HASHTAG = 34, COLON = 35, COMMA = 36, DOT = 37, EQUALS = 38,
			STAR = 39, LIST = 40, SEMICOLON = 41, PLUS = 42, WORD = 43, RESOURCE = 44, INT_TYPE = 45,
			FUNCTION_TYPE = 46, OBJECT_TYPE = 47, DOUBLE_TYPE = 48, LONG_TYPE = 49, STRING_TYPE = 50,
			BOOLEAN_TYPE = 51, DATE_TYPE = 52, INSTANT_TYPE = 53, TIME_TYPE = 54, EMPTY = 55,
			BLOCK_COMMENT = 56, LINE_COMMENT = 57, SCIENCE_NOT = 58, BOOLEAN_VALUE = 59, NATURAL_VALUE = 60,
			NEGATIVE_VALUE = 61, DOUBLE_VALUE = 62, STRING = 63, STRING_MULTILINE = 64, SINGLE_QUOTE = 65,
			EXPRESSION_MULTILINE = 66, CLASS_TYPE = 67, IDENTIFIER = 68, MEASURE_VALUE = 69,
			NEWLINE = 70, SPACES = 71, DOC = 72, SP = 73, NL = 74, NEW_LINE_INDENT = 75, DEDENT = 76,
			UNKNOWN_TOKEN = 77, ME_STRING_MULTILINE = 78, ME_CHARACTER = 79, E_QUOTE = 80,
			E_SLASH_Q = 81, E_SLASH = 82, E_CHARACTER = 83, QUOTE_BEGIN = 84, QUOTE_END = 85,
			EXPRESSION_BEGIN = 86, EXPRESSION_END = 87, CHARACTER = 88;
	public static final int
			RULE_root = 0, RULE_dslDeclaration = 1, RULE_imports = 2, RULE_anImport = 3,
			RULE_doc = 4, RULE_node = 5, RULE_signature = 6, RULE_parent = 7, RULE_parameters = 8,
			RULE_parameter = 9, RULE_aspects = 10, RULE_aspect = 11, RULE_value = 12,
			RULE_body = 13, RULE_nodeReference = 14, RULE_with = 15, RULE_variable = 16,
			RULE_bodyValue = 17, RULE_variableType = 18, RULE_ruleContainer = 19,
			RULE_ruleValue = 20, RULE_range = 21, RULE_size = 22, RULE_sizeRange = 23,
			RULE_listRange = 24, RULE_methodReference = 25, RULE_stringValue = 26,
			RULE_booleanValue = 27, RULE_tupleValue = 28, RULE_integerValue = 29,
			RULE_doubleValue = 30, RULE_metric = 31, RULE_expression = 32, RULE_tags = 33,
			RULE_annotations = 34, RULE_annotation = 35, RULE_flags = 36, RULE_flag = 37,
			RULE_varInit = 38, RULE_headerReference = 39, RULE_identifierReference = 40,
			RULE_hierarchy = 41, RULE_metaidentifier = 42;

	private static String[] makeRuleNames() {
		return new String[]{
				"root", "dslDeclaration", "imports", "anImport", "doc", "node", "signature",
				"parent", "parameters", "parameter", "aspects", "aspect", "value", "body",
				"nodeReference", "with", "variable", "bodyValue", "variableType", "ruleContainer",
				"ruleValue", "range", "size", "sizeRange", "listRange", "methodReference",
				"stringValue", "booleanValue", "tupleValue", "integerValue", "doubleValue",
				"metric", "expression", "tags", "annotations", "annotation", "flags",
				"flag", "varInit", "headerReference", "identifierReference", "hierarchy",
				"metaidentifier"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[]{
				null, "'sub'", "'use'", "'dsl'", "'var'", "'as'", "'has'", "'is'", "'into'",
				"'with'", "'any'", "'extends'", "'concept'", "'abstract'", "'terminal'",
				"'component'", "'feature'", "'divine'", "'required'", "'final'", "'enclosed'",
				"'private'", "'reactive'", "'volatile'", "'decorable'", "'('", "')'",
				"'['", "']'", "'{'", "'}'", "'>'", "'<'", "'@'", "'#'", "':'", "','",
				"'.'", "'='", "'*'", "'...'", null, "'+'", "'word'", "'resource'", "'integer'",
				"'function'", "'object'", "'double'", "'long'", "'string'", "'boolean'",
				"'datex'", "'instant'", "'time'", "'empty'", null, null, null, null,
				null, null, null, null, null, null, null, null, null, null, null, null,
				null, null, null, "'indent'", "'dedent'", null, null, null, null, "'\\''",
				"'\\'", null, "'%QUOTE_BEGIN%'", "'%QUOTE_END%'", "'%EXPRESSION_BEGIN%'",
				"'%EXPRESSION_END%'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[]{
				null, "SUB", "USE", "DSL", "VAR", "AS", "HAS", "IS", "INTO", "WITH",
				"ANY", "EXTENDS", "CONCEPT", "ABSTRACT", "TERMINAL", "COMPONENT", "FEATURE",
				"DIVINE", "REQUIRED", "FINAL", "ENCLOSED", "PRIVATE", "REACTIVE", "VOLATILE",
				"DECORABLE", "LEFT_PARENTHESIS", "RIGHT_PARENTHESIS", "LEFT_SQUARE",
				"RIGHT_SQUARE", "LEFT_CURLY", "RIGHT_CURLY", "INLINE", "CLOSE_INLINE",
				"AT", "HASHTAG", "COLON", "COMMA", "DOT", "EQUALS", "STAR", "LIST", "SEMICOLON",
				"PLUS", "WORD", "RESOURCE", "INT_TYPE", "FUNCTION_TYPE", "OBJECT_TYPE",
				"DOUBLE_TYPE", "LONG_TYPE", "STRING_TYPE", "BOOLEAN_TYPE", "DATE_TYPE",
				"INSTANT_TYPE", "TIME_TYPE", "EMPTY", "BLOCK_COMMENT", "LINE_COMMENT",
				"SCIENCE_NOT", "BOOLEAN_VALUE", "NATURAL_VALUE", "NEGATIVE_VALUE", "DOUBLE_VALUE",
				"STRING", "STRING_MULTILINE", "SINGLE_QUOTE", "EXPRESSION_MULTILINE",
				"CLASS_TYPE", "IDENTIFIER", "MEASURE_VALUE", "NEWLINE", "SPACES", "DOC",
				"SP", "NL", "NEW_LINE_INDENT", "DEDENT", "UNKNOWN_TOKEN", "ME_STRING_MULTILINE",
				"ME_CHARACTER", "E_QUOTE", "E_SLASH_Q", "E_SLASH", "E_CHARACTER", "QUOTE_BEGIN",
				"QUOTE_END", "EXPRESSION_BEGIN", "EXPRESSION_END", "CHARACTER"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
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
				setState(89);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == NEWLINE) {
					{
						{
							setState(86);
							match(NEWLINE);
						}
					}
					setState(91);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(93);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == DSL) {
					{
						setState(92);
						dslDeclaration();
					}
				}

				setState(96);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == USE) {
					{
						setState(95);
						imports();
					}
				}

				setState(107);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == SUB || _la == IDENTIFIER || _la == DOC) {
					{
						{
							setState(98);
							node();
							setState(102);
							_errHandler.sync(this);
							_la = _input.LA(1);
							while (_la == NEWLINE) {
								{
									{
										setState(99);
										match(NEWLINE);
									}
								}
								setState(104);
								_errHandler.sync(this);
								_la = _input.LA(1);
							}
						}
					}
					setState(109);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(110);
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
				setState(112);
				match(DSL);
				setState(113);
				headerReference();
				setState(117);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == NEWLINE) {
					{
						{
							setState(114);
							match(NEWLINE);
						}
					}
					setState(119);
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
				setState(121);
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
						{
							setState(120);
							anImport();
						}
					}
					setState(123);
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while (_la == USE);
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
				setState(125);
				match(USE);
				setState(126);
				headerReference();
				setState(128);
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
						{
							setState(127);
							match(NEWLINE);
						}
					}
					setState(130);
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while (_la == NEWLINE);
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
				setState(133);
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
						{
							setState(132);
							match(DOC);
						}
					}
					setState(135);
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while (_la == DOC);
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
				setState(138);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == DOC) {
					{
						setState(137);
						doc();
					}
				}

				setState(140);
				signature();
				setState(142);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == NEW_LINE_INDENT) {
					{
						setState(141);
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
		public WithContext with() {
			return getRuleContext(WithContext.class, 0);
		}
		public TerminalNode SUB() { return getToken(TaraGrammar.SUB, 0); }
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
		public MetaidentifierContext metaidentifier() {
			return getRuleContext(MetaidentifierContext.class,0);
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
		public List<AspectsContext> aspects() {
			return getRuleContexts(AspectsContext.class);
		}
		public AspectsContext aspects(int i) {
			return getRuleContext(AspectsContext.class, i);
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
				setState(183);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
					case SUB: {
						{
							setState(144);
							match(SUB);
							setState(148);
							_errHandler.sync(this);
							_la = _input.LA(1);
							while (_la == COLON) {
								{
									{
										setState(145);
										ruleContainer();
									}
								}
								setState(150);
								_errHandler.sync(this);
								_la = _input.LA(1);
							}
							setState(152);
							_errHandler.sync(this);
							_la = _input.LA(1);
							if (_la == LEFT_PARENTHESIS) {
								{
									setState(151);
									parameters();
								}
							}

							setState(154);
							match(IDENTIFIER);
							setState(158);
							_errHandler.sync(this);
							_la = _input.LA(1);
							while (_la == AS) {
								{
									{
										setState(155);
										aspects();
									}
								}
								setState(160);
								_errHandler.sync(this);
								_la = _input.LA(1);
							}
						}
					}
					break;
					case IDENTIFIER: {
						{
							setState(161);
							metaidentifier();
							setState(165);
							_errHandler.sync(this);
							_la = _input.LA(1);
							while (_la == COLON) {
								{
									{
										setState(162);
										ruleContainer();
									}
								}
								setState(167);
								_errHandler.sync(this);
								_la = _input.LA(1);
							}
							setState(169);
							_errHandler.sync(this);
							_la = _input.LA(1);
							if (_la == LEFT_PARENTHESIS) {
								{
									setState(168);
									parameters();
								}
							}

							setState(172);
							_errHandler.sync(this);
							switch (getInterpreter().adaptivePredict(_input, 16, _ctx)) {
								case 1: {
									setState(171);
									match(IDENTIFIER);
								}
								break;
							}
							setState(177);
							_errHandler.sync(this);
							_la = _input.LA(1);
							while (_la == AS) {
								{
									{
										setState(174);
										aspects();
									}
								}
								setState(179);
								_errHandler.sync(this);
								_la = _input.LA(1);
							}
							setState(181);
							_errHandler.sync(this);
							_la = _input.LA(1);
							if (_la == EXTENDS) {
								{
									setState(180);
									parent();
								}
							}

						}
					}
					break;
					default:
						throw new NoViableAltException(this);
				}
				setState(186);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == WITH) {
					{
						setState(185);
						with();
					}
				}

				setState(188);
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
				setState(190);
				match(EXTENDS);
				setState(191);
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
				setState(193);
				match(LEFT_PARENTHESIS);
				setState(202);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 33)) & ~0x3f) == 0 && ((1L << (_la - 33)) & ((1L << (AT - 33)) | (1L << (EMPTY - 33)) | (1L << (BOOLEAN_VALUE - 33)) | (1L << (NATURAL_VALUE - 33)) | (1L << (NEGATIVE_VALUE - 33)) | (1L << (DOUBLE_VALUE - 33)) | (1L << (STRING - 33)) | (1L << (IDENTIFIER - 33)) | (1L << (NEWLINE - 33)) | (1L << (EXPRESSION_BEGIN - 33)))) != 0)) {
					{
						setState(194);
						parameter();
						setState(199);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la == COMMA) {
							{
								{
									setState(195);
									match(COMMA);
									setState(196);
									parameter();
								}
							}
							setState(201);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
					}
				}

				setState(204);
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
				setState(208);
				_errHandler.sync(this);
				switch (getInterpreter().adaptivePredict(_input, 23, _ctx)) {
					case 1: {
						setState(206);
						match(IDENTIFIER);
						setState(207);
						match(EQUALS);
					}
					break;
				}
				setState(210);
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

	public static class AspectsContext extends ParserRuleContext {
		public TerminalNode AS() {
			return getToken(TaraGrammar.AS, 0);
		}

		public List<AspectContext> aspect() {
			return getRuleContexts(AspectContext.class);
		}

		public AspectContext aspect(int i) {
			return getRuleContext(AspectContext.class, i);
		}

		public AspectsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_aspects;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).enterAspects(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).exitAspects(this);
		}
	}

	public final AspectsContext aspects() throws RecognitionException {
		AspectsContext _localctx = new AspectsContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_aspects);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
				setState(212);
				match(AS);
				setState(214);
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
						case 1: {
							{
								setState(213);
								aspect();
							}
						}
						break;
						default:
							throw new NoViableAltException(this);
					}
					setState(216);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 24, _ctx);
				} while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER);
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

	public static class AspectContext extends ParserRuleContext {
		public MetaidentifierContext metaidentifier() {
			return getRuleContext(MetaidentifierContext.class, 0);
		}

		public ParametersContext parameters() {
			return getRuleContext(ParametersContext.class, 0);
		}

		public AspectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_aspect;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).enterAspect(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).exitAspect(this);
		}
	}

	public final AspectContext aspect() throws RecognitionException {
		AspectContext _localctx = new AspectContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_aspect);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(218);
				metaidentifier();
				setState(220);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == LEFT_PARENTHESIS) {
					{
						setState(219);
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
			setState(274);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
				{
					setState(223);
					_errHandler.sync(this);
					_alt = 1;
					do {
						switch (_alt) {
							case 1: {
								{
									setState(222);
									identifierReference();
								}
							}
							break;
							default:
								throw new NoViableAltException(this);
						}
						setState(225);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input, 26, _ctx);
					} while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER);
				}
				break;
				case 2:
					enterOuterAlt(_localctx, 2);
				{
					setState(228);
					_errHandler.sync(this);
					_alt = 1;
					do {
						switch (_alt) {
							case 1: {
								{
									setState(227);
									stringValue();
								}
							}
							break;
							default:
								throw new NoViableAltException(this);
						}
						setState(230);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input, 27, _ctx);
					} while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER);
				}
				break;
				case 3:
					enterOuterAlt(_localctx, 3);
				{
					setState(233);
					_errHandler.sync(this);
					_alt = 1;
					do {
						switch (_alt) {
							case 1: {
								{
									setState(232);
									tupleValue();
								}
							}
							break;
							default:
								throw new NoViableAltException(this);
						}
						setState(235);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input, 28, _ctx);
					} while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER);
				}
				break;
				case 4:
					enterOuterAlt(_localctx, 4);
				{
					setState(238);
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
							{
								setState(237);
								booleanValue();
							}
						}
						setState(240);
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while (_la == BOOLEAN_VALUE);
				}
				break;
				case 5:
					enterOuterAlt(_localctx, 5);
				{
					setState(243);
					_errHandler.sync(this);
					_alt = 1;
					do {
						switch (_alt) {
							case 1: {
								{
									setState(242);
									identifierReference();
								}
							}
							break;
							default:
								throw new NoViableAltException(this);
						}
						setState(245);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input, 30, _ctx);
					} while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER);
				}
				break;
				case 6:
					enterOuterAlt(_localctx, 6);
				{
					setState(248);
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
							{
								setState(247);
								integerValue();
							}
						}
						setState(250);
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while (_la == NATURAL_VALUE || _la == NEGATIVE_VALUE);
					setState(253);
					_errHandler.sync(this);
					switch (getInterpreter().adaptivePredict(_input, 32, _ctx)) {
						case 1: {
							setState(252);
							metric();
						}
						break;
					}
				}
				break;
				case 7:
					enterOuterAlt(_localctx, 7);
				{
					setState(256);
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
							{
								setState(255);
								doubleValue();
							}
						}
						setState(258);
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NATURAL_VALUE) | (1L << NEGATIVE_VALUE) | (1L << DOUBLE_VALUE))) != 0));
					setState(261);
					_errHandler.sync(this);
					switch (getInterpreter().adaptivePredict(_input, 34, _ctx)) {
						case 1: {
							setState(260);
							metric();
						}
						break;
					}
				}
				break;
				case 8:
					enterOuterAlt(_localctx, 8);
				{
					setState(264);
					_errHandler.sync(this);
					_alt = 1;
					do {
						switch (_alt) {
							case 1: {
								{
									setState(263);
									expression();
								}
							}
							break;
							default:
								throw new NoViableAltException(this);
						}
						setState(266);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input, 35, _ctx);
					} while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER);
				}
				break;
				case 9:
					enterOuterAlt(_localctx, 9);
				{
					setState(269);
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
							{
								setState(268);
								methodReference();
							}
						}
						setState(271);
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while (_la == AT);
				}
				break;
				case 10:
					enterOuterAlt(_localctx, 10);
				{
					setState(273);
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
				setState(276);
				match(NEW_LINE_INDENT);
				setState(288);
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
						{
							setState(281);
							_errHandler.sync(this);
							switch (getInterpreter().adaptivePredict(_input, 38, _ctx)) {
								case 1: {
									setState(277);
									variable();
								}
								break;
								case 2: {
									setState(278);
									node();
								}
								break;
								case 3: {
									setState(279);
									varInit();
								}
								break;
								case 4: {
									setState(280);
									nodeReference();
								}
								break;
							}
							setState(284);
							_errHandler.sync(this);
							_la = _input.LA(1);
							do {
								{
									{
										setState(283);
										match(NEWLINE);
									}
								}
								setState(286);
								_errHandler.sync(this);
								_la = _input.LA(1);
							} while (_la == NEWLINE);
						}
					}
					setState(290);
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SUB) | (1L << VAR) | (1L << HAS))) != 0) || _la == IDENTIFIER || _la == DOC);
				setState(292);
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
		enterRule(_localctx, 28, RULE_nodeReference);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(294);
				match(HAS);
				setState(298);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == COLON) {
					{
						{
							setState(295);
							ruleContainer();
						}
					}
					setState(300);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(301);
				identifierReference();
				setState(302);
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
		enterRule(_localctx, 30, RULE_with);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(304);
				match(WITH);
				setState(305);
				identifierReference();
				setState(310);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == COMMA) {
					{
						{
							setState(306);
							match(COMMA);
							setState(307);
							identifierReference();
						}
					}
					setState(312);
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
		enterRule(_localctx, 32, RULE_variable);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(314);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == DOC) {
					{
						setState(313);
						doc();
					}
				}

				setState(316);
				match(VAR);
				setState(317);
				variableType();
				setState(319);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == LEFT_SQUARE) {
					{
						setState(318);
						size();
					}
				}

				setState(322);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == COLON) {
					{
						setState(321);
						ruleContainer();
					}
				}

				setState(324);
				match(IDENTIFIER);
				setState(330);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == EQUALS) {
					{
						setState(325);
						match(EQUALS);
						setState(326);
						value();
						setState(328);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la == IDENTIFIER || _la == MEASURE_VALUE) {
							{
								setState(327);
								metric();
							}
						}

					}
				}

				setState(333);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == IS) {
					{
						setState(332);
						flags();
					}
				}

				setState(336);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == NEW_LINE_INDENT) {
					{
						setState(335);
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
		enterRule(_localctx, 34, RULE_bodyValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(338);
				match(NEW_LINE_INDENT);
				setState(341);
				_errHandler.sync(this);
				switch (getInterpreter().adaptivePredict(_input, 50, _ctx)) {
					case 1: {
						setState(339);
						stringValue();
					}
					break;
					case 2: {
						setState(340);
						expression();
					}
					break;
				}
				setState(344);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == NEWLINE) {
					{
						setState(343);
						match(NEWLINE);
					}
				}

				setState(346);
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
		public TerminalNode LONG_TYPE() { return getToken(TaraGrammar.LONG_TYPE, 0); }
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
		enterRule(_localctx, 36, RULE_variableType);
		try {
			setState(361);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
				case INT_TYPE:
					enterOuterAlt(_localctx, 1);
				{
					setState(348);
					match(INT_TYPE);
				}
				break;
				case LONG_TYPE:
					enterOuterAlt(_localctx, 2);
				{
					setState(349);
					match(LONG_TYPE);
				}
				break;
				case DOUBLE_TYPE:
					enterOuterAlt(_localctx, 3);
				{
					setState(350);
					match(DOUBLE_TYPE);
				}
				break;
				case BOOLEAN_TYPE:
					enterOuterAlt(_localctx, 4);
				{
					setState(351);
					match(BOOLEAN_TYPE);
				}
				break;
				case STRING_TYPE:
					enterOuterAlt(_localctx, 5);
				{
					setState(352);
					match(STRING_TYPE);
				}
				break;
				case FUNCTION_TYPE:
					enterOuterAlt(_localctx, 6);
				{
					setState(353);
					match(FUNCTION_TYPE);
				}
				break;
				case OBJECT_TYPE:
					enterOuterAlt(_localctx, 7);
				{
					setState(354);
					match(OBJECT_TYPE);
				}
				break;
				case WORD:
					enterOuterAlt(_localctx, 8);
				{
					setState(355);
					match(WORD);
				}
				break;
				case DATE_TYPE:
					enterOuterAlt(_localctx, 9);
				{
					setState(356);
					match(DATE_TYPE);
				}
				break;
				case INSTANT_TYPE:
					enterOuterAlt(_localctx, 10);
				{
					setState(357);
					match(INSTANT_TYPE);
				}
				break;
				case TIME_TYPE:
					enterOuterAlt(_localctx, 11);
				{
					setState(358);
					match(TIME_TYPE);
				}
				break;
				case RESOURCE:
					enterOuterAlt(_localctx, 12);
				{
					setState(359);
					match(RESOURCE);
				}
				break;
				case IDENTIFIER:
					enterOuterAlt(_localctx, 13);
				{
					setState(360);
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
		enterRule(_localctx, 38, RULE_ruleContainer);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(363);
				match(COLON);
				setState(364);
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
		enterRule(_localctx, 40, RULE_ruleValue);
		int _la;
		try {
			setState(384);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
				case LEFT_CURLY:
					enterOuterAlt(_localctx, 1);
				{
					{
						setState(366);
						match(LEFT_CURLY);
						setState(380);
						_errHandler.sync(this);
						switch (getInterpreter().adaptivePredict(_input, 56, _ctx)) {
							case 1: {
								setState(368);
								_errHandler.sync(this);
								_la = _input.LA(1);
								do {
									{
										{
											setState(367);
											match(IDENTIFIER);
										}
									}
									setState(370);
									_errHandler.sync(this);
									_la = _input.LA(1);
								} while (_la == IDENTIFIER);
							}
							break;
							case 2: {
								{
									setState(374);
									_errHandler.sync(this);
									switch (_input.LA(1)) {
										case STAR:
										case NATURAL_VALUE:
										case NEGATIVE_VALUE:
										case DOUBLE_VALUE: {
											setState(372);
											range();
										}
										break;
										case STRING:
										case NEWLINE: {
											setState(373);
											stringValue();
										}
										break;
										default:
											throw new NoViableAltException(this);
									}
									setState(377);
									_errHandler.sync(this);
									_la = _input.LA(1);
									if (_la == IDENTIFIER || _la == MEASURE_VALUE) {
										{
											setState(376);
											metric();
										}
									}

								}
							}
							break;
							case 3: {
								setState(379);
								metric();
							}
							break;
						}
						setState(382);
						match(RIGHT_CURLY);
					}
				}
				break;
				case IDENTIFIER:
					enterOuterAlt(_localctx, 2);
				{
					{
						setState(383);
						identifierReference();
					}
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
		public List<DoubleValueContext> doubleValue() {
			return getRuleContexts(DoubleValueContext.class);
		}
		public DoubleValueContext doubleValue(int i) {
			return getRuleContext(DoubleValueContext.class, i);
		}

		public List<IntegerValueContext> integerValue() {
			return getRuleContexts(IntegerValueContext.class);
		}

		public IntegerValueContext integerValue(int i) {
			return getRuleContext(IntegerValueContext.class, i);
		}

		public List<TerminalNode> STAR() {
			return getTokens(TaraGrammar.STAR);
		}

		public TerminalNode STAR(int i) {
			return getToken(TaraGrammar.STAR, i);
		}

		public List<TerminalNode> DOT() {
			return getTokens(TaraGrammar.DOT);
		}

		public TerminalNode DOT(int i) {
			return getToken(TaraGrammar.DOT, i);
		}

		public RangeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_range;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).enterRange(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).exitRange(this);
		}
	}

	public final RangeContext range() throws RecognitionException {
		RangeContext _localctx = new RangeContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_range);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(389);
				_errHandler.sync(this);
				switch (getInterpreter().adaptivePredict(_input, 58, _ctx)) {
					case 1: {
						setState(386);
						doubleValue();
					}
					break;
					case 2: {
						setState(387);
						integerValue();
					}
					break;
					case 3: {
						setState(388);
						match(STAR);
					}
					break;
				}
				setState(398);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == DOT) {
					{
						setState(391);
						match(DOT);
						setState(392);
						match(DOT);
						setState(396);
						_errHandler.sync(this);
						switch (getInterpreter().adaptivePredict(_input, 59, _ctx)) {
							case 1: {
								setState(393);
								doubleValue();
							}
							break;
							case 2: {
								setState(394);
								integerValue();
							}
							break;
							case 3: {
								setState(395);
								match(STAR);
							}
							break;
						}
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
		enterRule(_localctx, 44, RULE_size);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(400);
				match(LEFT_SQUARE);
				setState(402);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == STAR || _la == NATURAL_VALUE) {
					{
						setState(401);
						sizeRange();
					}
				}

				setState(404);
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
		enterRule(_localctx, 46, RULE_sizeRange);
		try {
			setState(408);
			_errHandler.sync(this);
			switch (getInterpreter().adaptivePredict(_input, 62, _ctx)) {
				case 1:
					enterOuterAlt(_localctx, 1);
				{
					setState(406);
					match(NATURAL_VALUE);
				}
				break;
				case 2:
					enterOuterAlt(_localctx, 2);
				{
					setState(407);
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
		enterRule(_localctx, 48, RULE_listRange);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(410);
				_la = _input.LA(1);
				if (!(_la == STAR || _la == NATURAL_VALUE)) {
					_errHandler.recoverInline(this);
				} else {
					if (_input.LA(1) == Token.EOF) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(411);
				match(DOT);
				setState(412);
				match(DOT);
				setState(413);
				_la = _input.LA(1);
				if (!(_la == STAR || _la == NATURAL_VALUE)) {
					_errHandler.recoverInline(this);
				} else {
					if (_input.LA(1) == Token.EOF) matchedEOF = true;
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
		enterRule(_localctx, 50, RULE_methodReference);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(415);
				match(AT);
				setState(416);
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
		enterRule(_localctx, 52, RULE_stringValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(419);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == NEWLINE) {
					{
						setState(418);
						match(NEWLINE);
					}
				}

				{
					setState(421);
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
		enterRule(_localctx, 54, RULE_booleanValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(423);
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
		enterRule(_localctx, 56, RULE_tupleValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(425);
				stringValue();
				setState(426);
				match(COLON);
				setState(427);
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
		enterRule(_localctx, 58, RULE_integerValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(429);
				_la = _input.LA(1);
				if (!(_la == NATURAL_VALUE || _la == NEGATIVE_VALUE)) {
					_errHandler.recoverInline(this);
				} else {
					if (_input.LA(1) == Token.EOF) matchedEOF = true;
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
		enterRule(_localctx, 60, RULE_doubleValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(431);
				_la = _input.LA(1);
				if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NATURAL_VALUE) | (1L << NEGATIVE_VALUE) | (1L << DOUBLE_VALUE))) != 0))) {
					_errHandler.recoverInline(this);
				} else {
					if (_input.LA(1) == Token.EOF) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(433);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == SCIENCE_NOT) {
					{
						setState(432);
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
		enterRule(_localctx, 62, RULE_metric);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(435);
				_la = _input.LA(1);
				if (!(_la == IDENTIFIER || _la == MEASURE_VALUE)) {
					_errHandler.recoverInline(this);
				} else {
					if (_input.LA(1) == Token.EOF) matchedEOF = true;
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
		enterRule(_localctx, 64, RULE_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(438);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == NEWLINE) {
					{
						setState(437);
						match(NEWLINE);
					}
				}

				{
					setState(440);
					match(EXPRESSION_BEGIN);
					setState(444);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la == CHARACTER) {
						{
							{
								setState(441);
								match(CHARACTER);
							}
						}
						setState(446);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(447);
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
		enterRule(_localctx, 66, RULE_tags);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(450);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == IS) {
					{
						setState(449);
						flags();
					}
				}

				setState(453);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == INTO) {
					{
						setState(452);
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
		enterRule(_localctx, 68, RULE_annotations);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(455);
				match(INTO);
				setState(457);
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
						{
							setState(456);
							annotation();
						}
					}
					setState(459);
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << COMPONENT) | (1L << FEATURE) | (1L << ENCLOSED))) != 0));
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
		enterRule(_localctx, 70, RULE_annotation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(461);
				_la = _input.LA(1);
				if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << COMPONENT) | (1L << FEATURE) | (1L << ENCLOSED))) != 0))) {
					_errHandler.recoverInline(this);
				} else {
					if (_input.LA(1) == Token.EOF) matchedEOF = true;
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
		enterRule(_localctx, 72, RULE_flags);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(463);
				match(IS);
				setState(465);
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
						{
							setState(464);
							flag();
						}
					}
					setState(467);
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << CONCEPT) | (1L << ABSTRACT) | (1L << TERMINAL) | (1L << COMPONENT) | (1L << FEATURE) | (1L << DIVINE) | (1L << REQUIRED) | (1L << FINAL) | (1L << ENCLOSED) | (1L << PRIVATE) | (1L << REACTIVE) | (1L << VOLATILE) | (1L << DECORABLE))) != 0));
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
		public TerminalNode ABSTRACT() {
			return getToken(TaraGrammar.ABSTRACT, 0);
		}

		public TerminalNode TERMINAL() {
			return getToken(TaraGrammar.TERMINAL, 0);
		}

		public TerminalNode COMPONENT() {
			return getToken(TaraGrammar.COMPONENT, 0);
		}

		public TerminalNode PRIVATE() {
			return getToken(TaraGrammar.PRIVATE, 0);
		}

		public TerminalNode FEATURE() {
			return getToken(TaraGrammar.FEATURE, 0);
		}

		public TerminalNode ENCLOSED() {
			return getToken(TaraGrammar.ENCLOSED, 0);
		}

		public TerminalNode FINAL() {
			return getToken(TaraGrammar.FINAL, 0);
		}

		public TerminalNode CONCEPT() {
			return getToken(TaraGrammar.CONCEPT, 0);
		}

		public TerminalNode REACTIVE() {
			return getToken(TaraGrammar.REACTIVE, 0);
		}

		public TerminalNode VOLATILE() {
			return getToken(TaraGrammar.VOLATILE, 0);
		}

		public TerminalNode DECORABLE() {
			return getToken(TaraGrammar.DECORABLE, 0);
		}

		public TerminalNode REQUIRED() {
			return getToken(TaraGrammar.REQUIRED, 0);
		}

		public TerminalNode DIVINE() {
			return getToken(TaraGrammar.DIVINE, 0);
		}

		public FlagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_flag;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).enterFlag(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).exitFlag(this);
		}
	}

	public final FlagContext flag() throws RecognitionException {
		FlagContext _localctx = new FlagContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_flag);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(469);
				_la = _input.LA(1);
				if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << CONCEPT) | (1L << ABSTRACT) | (1L << TERMINAL) | (1L << COMPONENT) | (1L << FEATURE) | (1L << DIVINE) | (1L << REQUIRED) | (1L << FINAL) | (1L << ENCLOSED) | (1L << PRIVATE) | (1L << REACTIVE) | (1L << VOLATILE) | (1L << DECORABLE))) != 0))) {
					_errHandler.recoverInline(this);
				} else {
					if (_input.LA(1) == Token.EOF) matchedEOF = true;
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
		enterRule(_localctx, 76, RULE_varInit);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(471);
				match(IDENTIFIER);
				setState(475);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
					case EQUALS: {
						{
							setState(472);
							match(EQUALS);
							setState(473);
							value();
						}
					}
					break;
					case NEW_LINE_INDENT: {
						setState(474);
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
		enterRule(_localctx, 78, RULE_headerReference);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
				setState(480);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 72, _ctx);
				while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1) {
						{
							{
								setState(477);
								hierarchy();
							}
						}
					}
					setState(482);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 72, _ctx);
				}
				setState(483);
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
		enterRule(_localctx, 80, RULE_identifierReference);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
				setState(488);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 73, _ctx);
				while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1) {
						{
							{
								setState(485);
								hierarchy();
							}
						}
					}
					setState(490);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 73, _ctx);
				}
				setState(491);
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
		enterRule(_localctx, 82, RULE_hierarchy);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(493);
				match(IDENTIFIER);
				setState(494);
				_la = _input.LA(1);
				if (!(_la == DOT || _la == PLUS)) {
					_errHandler.recoverInline(this);
				} else {
					if (_input.LA(1) == Token.EOF) matchedEOF = true;
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
		enterRule(_localctx, 84, RULE_metaidentifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(496);
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
			"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3Z\u01f5\4\2\t\2\4" +
					"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t" +
					"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22" +
					"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31" +
					"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!" +
					"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4" +
					",\t,\3\2\7\2Z\n\2\f\2\16\2]\13\2\3\2\5\2`\n\2\3\2\5\2c\n\2\3\2\3\2\7\2" +
					"g\n\2\f\2\16\2j\13\2\7\2l\n\2\f\2\16\2o\13\2\3\2\3\2\3\3\3\3\3\3\7\3v" +
					"\n\3\f\3\16\3y\13\3\3\4\6\4|\n\4\r\4\16\4}\3\5\3\5\3\5\6\5\u0083\n\5\r" +
					"\5\16\5\u0084\3\6\6\6\u0088\n\6\r\6\16\6\u0089\3\7\5\7\u008d\n\7\3\7\3" +
					"\7\5\7\u0091\n\7\3\b\3\b\7\b\u0095\n\b\f\b\16\b\u0098\13\b\3\b\5\b\u009b" +
					"\n\b\3\b\3\b\7\b\u009f\n\b\f\b\16\b\u00a2\13\b\3\b\3\b\7\b\u00a6\n\b\f" +
					"\b\16\b\u00a9\13\b\3\b\5\b\u00ac\n\b\3\b\5\b\u00af\n\b\3\b\7\b\u00b2\n" +
					"\b\f\b\16\b\u00b5\13\b\3\b\5\b\u00b8\n\b\5\b\u00ba\n\b\3\b\5\b\u00bd\n" +
					"\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\n\7\n\u00c8\n\n\f\n\16\n\u00cb\13" +
					"\n\5\n\u00cd\n\n\3\n\3\n\3\13\3\13\5\13\u00d3\n\13\3\13\3\13\3\f\3\f\6" +
					"\f\u00d9\n\f\r\f\16\f\u00da\3\r\3\r\5\r\u00df\n\r\3\16\6\16\u00e2\n\16" +
					"\r\16\16\16\u00e3\3\16\6\16\u00e7\n\16\r\16\16\16\u00e8\3\16\6\16\u00ec" +
					"\n\16\r\16\16\16\u00ed\3\16\6\16\u00f1\n\16\r\16\16\16\u00f2\3\16\6\16" +
					"\u00f6\n\16\r\16\16\16\u00f7\3\16\6\16\u00fb\n\16\r\16\16\16\u00fc\3\16" +
					"\5\16\u0100\n\16\3\16\6\16\u0103\n\16\r\16\16\16\u0104\3\16\5\16\u0108" +
					"\n\16\3\16\6\16\u010b\n\16\r\16\16\16\u010c\3\16\6\16\u0110\n\16\r\16" +
					"\16\16\u0111\3\16\5\16\u0115\n\16\3\17\3\17\3\17\3\17\3\17\5\17\u011c" +
					"\n\17\3\17\6\17\u011f\n\17\r\17\16\17\u0120\6\17\u0123\n\17\r\17\16\17" +
					"\u0124\3\17\3\17\3\20\3\20\7\20\u012b\n\20\f\20\16\20\u012e\13\20\3\20" +
					"\3\20\3\20\3\21\3\21\3\21\3\21\7\21\u0137\n\21\f\21\16\21\u013a\13\21" +
					"\3\22\5\22\u013d\n\22\3\22\3\22\3\22\5\22\u0142\n\22\3\22\5\22\u0145\n" +
					"\22\3\22\3\22\3\22\3\22\5\22\u014b\n\22\5\22\u014d\n\22\3\22\5\22\u0150" +
					"\n\22\3\22\5\22\u0153\n\22\3\23\3\23\3\23\5\23\u0158\n\23\3\23\5\23\u015b" +
					"\n\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24" +
					"\3\24\3\24\5\24\u016c\n\24\3\25\3\25\3\25\3\26\3\26\6\26\u0173\n\26\r" +
					"\26\16\26\u0174\3\26\3\26\5\26\u0179\n\26\3\26\5\26\u017c\n\26\3\26\5" +
					"\26\u017f\n\26\3\26\3\26\5\26\u0183\n\26\3\27\3\27\3\27\5\27\u0188\n\27" +
					"\3\27\3\27\3\27\3\27\3\27\5\27\u018f\n\27\5\27\u0191\n\27\3\30\3\30\5" +
					"\30\u0195\n\30\3\30\3\30\3\31\3\31\5\31\u019b\n\31\3\32\3\32\3\32\3\32" +
					"\3\32\3\33\3\33\3\33\3\34\5\34\u01a6\n\34\3\34\3\34\3\35\3\35\3\36\3\36" +
					"\3\36\3\36\3\37\3\37\3 \3 \5 \u01b4\n \3!\3!\3\"\5\"\u01b9\n\"\3\"\3\"" +
					"\7\"\u01bd\n\"\f\"\16\"\u01c0\13\"\3\"\3\"\3#\5#\u01c5\n#\3#\5#\u01c8" +
					"\n#\3$\3$\6$\u01cc\n$\r$\16$\u01cd\3%\3%\3&\3&\6&\u01d4\n&\r&\16&\u01d5" +
					"\3\'\3\'\3(\3(\3(\3(\5(\u01de\n(\3)\7)\u01e1\n)\f)\16)\u01e4\13)\3)\3" +
					")\3*\7*\u01e9\n*\f*\16*\u01ec\13*\3*\3*\3+\3+\3+\3,\3,\3,\2\2-\2\4\6\b" +
					"\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJLNPRTV\2" +
					"\t\4\2))>>\3\2>?\3\2>@\3\2FG\4\2\21\22\26\26\3\2\16\32\4\2\'\',,\2\u022b" +
					"\2[\3\2\2\2\4r\3\2\2\2\6{\3\2\2\2\b\177\3\2\2\2\n\u0087\3\2\2\2\f\u008c" +
					"\3\2\2\2\16\u00b9\3\2\2\2\20\u00c0\3\2\2\2\22\u00c3\3\2\2\2\24\u00d2\3" +
					"\2\2\2\26\u00d6\3\2\2\2\30\u00dc\3\2\2\2\32\u0114\3\2\2\2\34\u0116\3\2" +
					"\2\2\36\u0128\3\2\2\2 \u0132\3\2\2\2\"\u013c\3\2\2\2$\u0154\3\2\2\2&\u016b" +
					"\3\2\2\2(\u016d\3\2\2\2*\u0182\3\2\2\2,\u0187\3\2\2\2.\u0192\3\2\2\2\60" +
					"\u019a\3\2\2\2\62\u019c\3\2\2\2\64\u01a1\3\2\2\2\66\u01a5\3\2\2\28\u01a9" +
					"\3\2\2\2:\u01ab\3\2\2\2<\u01af\3\2\2\2>\u01b1\3\2\2\2@\u01b5\3\2\2\2B" +
					"\u01b8\3\2\2\2D\u01c4\3\2\2\2F\u01c9\3\2\2\2H\u01cf\3\2\2\2J\u01d1\3\2" +
					"\2\2L\u01d7\3\2\2\2N\u01d9\3\2\2\2P\u01e2\3\2\2\2R\u01ea\3\2\2\2T\u01ef" +
					"\3\2\2\2V\u01f2\3\2\2\2XZ\7H\2\2YX\3\2\2\2Z]\3\2\2\2[Y\3\2\2\2[\\\3\2" +
					"\2\2\\_\3\2\2\2][\3\2\2\2^`\5\4\3\2_^\3\2\2\2_`\3\2\2\2`b\3\2\2\2ac\5" +
					"\6\4\2ba\3\2\2\2bc\3\2\2\2cm\3\2\2\2dh\5\f\7\2eg\7H\2\2fe\3\2\2\2gj\3" +
					"\2\2\2hf\3\2\2\2hi\3\2\2\2il\3\2\2\2jh\3\2\2\2kd\3\2\2\2lo\3\2\2\2mk\3" +
					"\2\2\2mn\3\2\2\2np\3\2\2\2om\3\2\2\2pq\7\2\2\3q\3\3\2\2\2rs\7\5\2\2sw" +
					"\5P)\2tv\7H\2\2ut\3\2\2\2vy\3\2\2\2wu\3\2\2\2wx\3\2\2\2x\5\3\2\2\2yw\3" +
					"\2\2\2z|\5\b\5\2{z\3\2\2\2|}\3\2\2\2}{\3\2\2\2}~\3\2\2\2~\7\3\2\2\2\177" +
					"\u0080\7\4\2\2\u0080\u0082\5P)\2\u0081\u0083\7H\2\2\u0082\u0081\3\2\2" +
					"\2\u0083\u0084\3\2\2\2\u0084\u0082\3\2\2\2\u0084\u0085\3\2\2\2\u0085\t" +
					"\3\2\2\2\u0086\u0088\7J\2\2\u0087\u0086\3\2\2\2\u0088\u0089\3\2\2\2\u0089" +
					"\u0087\3\2\2\2\u0089\u008a\3\2\2\2\u008a\13\3\2\2\2\u008b\u008d\5\n\6" +
					"\2\u008c\u008b\3\2\2\2\u008c\u008d\3\2\2\2\u008d\u008e\3\2\2\2\u008e\u0090" +
					"\5\16\b\2\u008f\u0091\5\34\17\2\u0090\u008f\3\2\2\2\u0090\u0091\3\2\2" +
					"\2\u0091\r\3\2\2\2\u0092\u0096\7\3\2\2\u0093\u0095\5(\25\2\u0094\u0093" +
					"\3\2\2\2\u0095\u0098\3\2\2\2\u0096\u0094\3\2\2\2\u0096\u0097\3\2\2\2\u0097" +
					"\u009a\3\2\2\2\u0098\u0096\3\2\2\2\u0099\u009b\5\22\n\2\u009a\u0099\3" +
					"\2\2\2\u009a\u009b\3\2\2\2\u009b\u009c\3\2\2\2\u009c\u00a0\7F\2\2\u009d" +
					"\u009f\5\26\f\2\u009e\u009d\3\2\2\2\u009f\u00a2\3\2\2\2\u00a0\u009e\3" +
					"\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\u00ba\3\2\2\2\u00a2\u00a0\3\2\2\2\u00a3" +
					"\u00a7\5V,\2\u00a4\u00a6\5(\25\2\u00a5\u00a4\3\2\2\2\u00a6\u00a9\3\2\2" +
					"\2\u00a7\u00a5\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8\u00ab\3\2\2\2\u00a9\u00a7" +
					"\3\2\2\2\u00aa\u00ac\5\22\n\2\u00ab\u00aa\3\2\2\2\u00ab\u00ac\3\2\2\2" +
					"\u00ac\u00ae\3\2\2\2\u00ad\u00af\7F\2\2\u00ae\u00ad\3\2\2\2\u00ae\u00af" +
					"\3\2\2\2\u00af\u00b3\3\2\2\2\u00b0\u00b2\5\26\f\2\u00b1\u00b0\3\2\2\2" +
					"\u00b2\u00b5\3\2\2\2\u00b3\u00b1\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4\u00b7" +
					"\3\2\2\2\u00b5\u00b3\3\2\2\2\u00b6\u00b8\5\20\t\2\u00b7\u00b6\3\2\2\2" +
					"\u00b7\u00b8\3\2\2\2\u00b8\u00ba\3\2\2\2\u00b9\u0092\3\2\2\2\u00b9\u00a3" +
					"\3\2\2\2\u00ba\u00bc\3\2\2\2\u00bb\u00bd\5 \21\2\u00bc\u00bb\3\2\2\2\u00bc" +
					"\u00bd\3\2\2\2\u00bd\u00be\3\2\2\2\u00be\u00bf\5D#\2\u00bf\17\3\2\2\2" +
					"\u00c0\u00c1\7\r\2\2\u00c1\u00c2\5R*\2\u00c2\21\3\2\2\2\u00c3\u00cc\7" +
					"\33\2\2\u00c4\u00c9\5\24\13\2\u00c5\u00c6\7&\2\2\u00c6\u00c8\5\24\13\2" +
					"\u00c7\u00c5\3\2\2\2\u00c8\u00cb\3\2\2\2\u00c9\u00c7\3\2\2\2\u00c9\u00ca" +
					"\3\2\2\2\u00ca\u00cd\3\2\2\2\u00cb\u00c9\3\2\2\2\u00cc\u00c4\3\2\2\2\u00cc" +
					"\u00cd\3\2\2\2\u00cd\u00ce\3\2\2\2\u00ce\u00cf\7\34\2\2\u00cf\23\3\2\2" +
					"\2\u00d0\u00d1\7F\2\2\u00d1\u00d3\7(\2\2\u00d2\u00d0\3\2\2\2\u00d2\u00d3" +
					"\3\2\2\2\u00d3\u00d4\3\2\2\2\u00d4\u00d5\5\32\16\2\u00d5\25\3\2\2\2\u00d6" +
					"\u00d8\7\7\2\2\u00d7\u00d9\5\30\r\2\u00d8\u00d7\3\2\2\2\u00d9\u00da\3" +
					"\2\2\2\u00da\u00d8\3\2\2\2\u00da\u00db\3\2\2\2\u00db\27\3\2\2\2\u00dc" +
					"\u00de\5V,\2\u00dd\u00df\5\22\n\2\u00de\u00dd\3\2\2\2\u00de\u00df\3\2" +
					"\2\2\u00df\31\3\2\2\2\u00e0\u00e2\5R*\2\u00e1\u00e0\3\2\2\2\u00e2\u00e3" +
					"\3\2\2\2\u00e3\u00e1\3\2\2\2\u00e3\u00e4\3\2\2\2\u00e4\u0115\3\2\2\2\u00e5" +
					"\u00e7\5\66\34\2\u00e6\u00e5\3\2\2\2\u00e7\u00e8\3\2\2\2\u00e8\u00e6\3" +
					"\2\2\2\u00e8\u00e9\3\2\2\2\u00e9\u0115\3\2\2\2\u00ea\u00ec\5:\36\2\u00eb" +
					"\u00ea\3\2\2\2\u00ec\u00ed\3\2\2\2\u00ed\u00eb\3\2\2\2\u00ed\u00ee\3\2" +
					"\2\2\u00ee\u0115\3\2\2\2\u00ef\u00f1\58\35\2\u00f0\u00ef\3\2\2\2\u00f1" +
					"\u00f2\3\2\2\2\u00f2\u00f0\3\2\2\2\u00f2\u00f3\3\2\2\2\u00f3\u0115\3\2" +
					"\2\2\u00f4\u00f6\5R*\2\u00f5\u00f4\3\2\2\2\u00f6\u00f7\3\2\2\2\u00f7\u00f5" +
					"\3\2\2\2\u00f7\u00f8\3\2\2\2\u00f8\u0115\3\2\2\2\u00f9\u00fb\5<\37\2\u00fa" +
					"\u00f9\3\2\2\2\u00fb\u00fc\3\2\2\2\u00fc\u00fa\3\2\2\2\u00fc\u00fd\3\2" +
					"\2\2\u00fd\u00ff\3\2\2\2\u00fe\u0100\5@!\2\u00ff\u00fe\3\2\2\2\u00ff\u0100" +
					"\3\2\2\2\u0100\u0115\3\2\2\2\u0101\u0103\5> \2\u0102\u0101\3\2\2\2\u0103" +
					"\u0104\3\2\2\2\u0104\u0102\3\2\2\2\u0104\u0105\3\2\2\2\u0105\u0107\3\2" +
					"\2\2\u0106\u0108\5@!\2\u0107\u0106\3\2\2\2\u0107\u0108\3\2\2\2\u0108\u0115" +
					"\3\2\2\2\u0109\u010b\5B\"\2\u010a\u0109\3\2\2\2\u010b\u010c\3\2\2\2\u010c" +
					"\u010a\3\2\2\2\u010c\u010d\3\2\2\2\u010d\u0115\3\2\2\2\u010e\u0110\5\64" +
					"\33\2\u010f\u010e\3\2\2\2\u0110\u0111\3\2\2\2\u0111\u010f\3\2\2\2\u0111" +
					"\u0112\3\2\2\2\u0112\u0115\3\2\2\2\u0113\u0115\79\2\2\u0114\u00e1\3\2" +
					"\2\2\u0114\u00e6\3\2\2\2\u0114\u00eb\3\2\2\2\u0114\u00f0\3\2\2\2\u0114" +
					"\u00f5\3\2\2\2\u0114\u00fa\3\2\2\2\u0114\u0102\3\2\2\2\u0114\u010a\3\2" +
					"\2\2\u0114\u010f\3\2\2\2\u0114\u0113\3\2\2\2\u0115\33\3\2\2\2\u0116\u0122" +
					"\7M\2\2\u0117\u011c\5\"\22\2\u0118\u011c\5\f\7\2\u0119\u011c\5N(\2\u011a" +
					"\u011c\5\36\20\2\u011b\u0117\3\2\2\2\u011b\u0118\3\2\2\2\u011b\u0119\3" +
					"\2\2\2\u011b\u011a\3\2\2\2\u011c\u011e\3\2\2\2\u011d\u011f\7H\2\2\u011e" +
					"\u011d\3\2\2\2\u011f\u0120\3\2\2\2\u0120\u011e\3\2\2\2\u0120\u0121\3\2" +
					"\2\2\u0121\u0123\3\2\2\2\u0122\u011b\3\2\2\2\u0123\u0124\3\2\2\2\u0124" +
					"\u0122\3\2\2\2\u0124\u0125\3\2\2\2\u0125\u0126\3\2\2\2\u0126\u0127\7N" +
					"\2\2\u0127\35\3\2\2\2\u0128\u012c\7\b\2\2\u0129\u012b\5(\25\2\u012a\u0129" +
					"\3\2\2\2\u012b\u012e\3\2\2\2\u012c\u012a\3\2\2\2\u012c\u012d\3\2\2\2\u012d" +
					"\u012f\3\2\2\2\u012e\u012c\3\2\2\2\u012f\u0130\5R*\2\u0130\u0131\5D#\2" +
					"\u0131\37\3\2\2\2\u0132\u0133\7\13\2\2\u0133\u0138\5R*\2\u0134\u0135\7" +
					"&\2\2\u0135\u0137\5R*\2\u0136\u0134\3\2\2\2\u0137\u013a\3\2\2\2\u0138" +
					"\u0136\3\2\2\2\u0138\u0139\3\2\2\2\u0139!\3\2\2\2\u013a\u0138\3\2\2\2" +
					"\u013b\u013d\5\n\6\2\u013c\u013b\3\2\2\2\u013c\u013d\3\2\2\2\u013d\u013e" +
					"\3\2\2\2\u013e\u013f\7\6\2\2\u013f\u0141\5&\24\2\u0140\u0142\5.\30\2\u0141" +
					"\u0140\3\2\2\2\u0141\u0142\3\2\2\2\u0142\u0144\3\2\2\2\u0143\u0145\5(" +
					"\25\2\u0144\u0143\3\2\2\2\u0144\u0145\3\2\2\2\u0145\u0146\3\2\2\2\u0146" +
					"\u014c\7F\2\2\u0147\u0148\7(\2\2\u0148\u014a\5\32\16\2\u0149\u014b\5@" +
					"!\2\u014a\u0149\3\2\2\2\u014a\u014b\3\2\2\2\u014b\u014d\3\2\2\2\u014c" +
					"\u0147\3\2\2\2\u014c\u014d\3\2\2\2\u014d\u014f\3\2\2\2\u014e\u0150\5J" +
					"&\2\u014f\u014e\3\2\2\2\u014f\u0150\3\2\2\2\u0150\u0152\3\2\2\2\u0151" +
					"\u0153\5$\23\2\u0152\u0151\3\2\2\2\u0152\u0153\3\2\2\2\u0153#\3\2\2\2" +
					"\u0154\u0157\7M\2\2\u0155\u0158\5\66\34\2\u0156\u0158\5B\"\2\u0157\u0155" +
					"\3\2\2\2\u0157\u0156\3\2\2\2\u0158\u015a\3\2\2\2\u0159\u015b\7H\2\2\u015a" +
					"\u0159\3\2\2\2\u015a\u015b\3\2\2\2\u015b\u015c\3\2\2\2\u015c\u015d\7N" +
					"\2\2\u015d%\3\2\2\2\u015e\u016c\7/\2\2\u015f\u016c\7\63\2\2\u0160\u016c" +
					"\7\62\2\2\u0161\u016c\7\65\2\2\u0162\u016c\7\64\2\2\u0163\u016c\7\60\2" +
					"\2\u0164\u016c\7\61\2\2\u0165\u016c\7-\2\2\u0166\u016c\7\66\2\2\u0167" +
					"\u016c\7\67\2\2\u0168\u016c\78\2\2\u0169\u016c\7.\2\2\u016a\u016c\5R*" +
					"\2\u016b\u015e\3\2\2\2\u016b\u015f\3\2\2\2\u016b\u0160\3\2\2\2\u016b\u0161" +
					"\3\2\2\2\u016b\u0162\3\2\2\2\u016b\u0163\3\2\2\2\u016b\u0164\3\2\2\2\u016b" +
					"\u0165\3\2\2\2\u016b\u0166\3\2\2\2\u016b\u0167\3\2\2\2\u016b\u0168\3\2" +
					"\2\2\u016b\u0169\3\2\2\2\u016b\u016a\3\2\2\2\u016c\'\3\2\2\2\u016d\u016e" +
					"\7%\2\2\u016e\u016f\5*\26\2\u016f)\3\2\2\2\u0170\u017e\7\37\2\2\u0171" +
					"\u0173\7F\2\2\u0172\u0171\3\2\2\2\u0173\u0174\3\2\2\2\u0174\u0172\3\2" +
					"\2\2\u0174\u0175\3\2\2\2\u0175\u017f\3\2\2\2\u0176\u0179\5,\27\2\u0177" +
					"\u0179\5\66\34\2\u0178\u0176\3\2\2\2\u0178\u0177\3\2\2\2\u0179\u017b\3" +
					"\2\2\2\u017a\u017c\5@!\2\u017b\u017a\3\2\2\2\u017b\u017c\3\2\2\2\u017c" +
					"\u017f\3\2\2\2\u017d\u017f\5@!\2\u017e\u0172\3\2\2\2\u017e\u0178\3\2\2" +
					"\2\u017e\u017d\3\2\2\2\u017f\u0180\3\2\2\2\u0180\u0183\7 \2\2\u0181\u0183" +
					"\5R*\2\u0182\u0170\3\2\2\2\u0182\u0181\3\2\2\2\u0183+\3\2\2\2\u0184\u0188" +
					"\5> \2\u0185\u0188\5<\37\2\u0186\u0188\7)\2\2\u0187\u0184\3\2\2\2\u0187" +
					"\u0185\3\2\2\2\u0187\u0186\3\2\2\2\u0188\u0190\3\2\2\2\u0189\u018a\7\'" +
					"\2\2\u018a\u018e\7\'\2\2\u018b\u018f\5> \2\u018c\u018f\5<\37\2\u018d\u018f" +
					"\7)\2\2\u018e\u018b\3\2\2\2\u018e\u018c\3\2\2\2\u018e\u018d\3\2\2\2\u018f" +
					"\u0191\3\2\2\2\u0190\u0189\3\2\2\2\u0190\u0191\3\2\2\2\u0191-\3\2\2\2" +
					"\u0192\u0194\7\35\2\2\u0193\u0195\5\60\31\2\u0194\u0193\3\2\2\2\u0194" +
					"\u0195\3\2\2\2\u0195\u0196\3\2\2\2\u0196\u0197\7\36\2\2\u0197/\3\2\2\2" +
					"\u0198\u019b\7>\2\2\u0199\u019b\5\62\32\2\u019a\u0198\3\2\2\2\u019a\u0199" +
					"\3\2\2\2\u019b\61\3\2\2\2\u019c\u019d\t\2\2\2\u019d\u019e\7\'\2\2\u019e" +
					"\u019f\7\'\2\2\u019f\u01a0\t\2\2\2\u01a0\63\3\2\2\2\u01a1\u01a2\7#\2\2" +
					"\u01a2\u01a3\5R*\2\u01a3\65\3\2\2\2\u01a4\u01a6\7H\2\2\u01a5\u01a4\3\2" +
					"\2\2\u01a5\u01a6\3\2\2\2\u01a6\u01a7\3\2\2\2\u01a7\u01a8\7A\2\2\u01a8" +
					"\67\3\2\2\2\u01a9\u01aa\7=\2\2\u01aa9\3\2\2\2\u01ab\u01ac\5\66\34\2\u01ac" +
					"\u01ad\7%\2\2\u01ad\u01ae\5> \2\u01ae;\3\2\2\2\u01af\u01b0\t\3\2\2\u01b0" +
					"=\3\2\2\2\u01b1\u01b3\t\4\2\2\u01b2\u01b4\7<\2\2\u01b3\u01b2\3\2\2\2\u01b3" +
					"\u01b4\3\2\2\2\u01b4?\3\2\2\2\u01b5\u01b6\t\5\2\2\u01b6A\3\2\2\2\u01b7" +
					"\u01b9\7H\2\2\u01b8\u01b7\3\2\2\2\u01b8\u01b9\3\2\2\2\u01b9\u01ba\3\2" +
					"\2\2\u01ba\u01be\7X\2\2\u01bb\u01bd\7Z\2\2\u01bc\u01bb\3\2\2\2\u01bd\u01c0" +
					"\3\2\2\2\u01be\u01bc\3\2\2\2\u01be\u01bf\3\2\2\2\u01bf\u01c1\3\2\2\2\u01c0" +
					"\u01be\3\2\2\2\u01c1\u01c2\7Y\2\2\u01c2C\3\2\2\2\u01c3\u01c5\5J&\2\u01c4" +
					"\u01c3\3\2\2\2\u01c4\u01c5\3\2\2\2\u01c5\u01c7\3\2\2\2\u01c6\u01c8\5F" +
					"$\2\u01c7\u01c6\3\2\2\2\u01c7\u01c8\3\2\2\2\u01c8E\3\2\2\2\u01c9\u01cb" +
					"\7\n\2\2\u01ca\u01cc\5H%\2\u01cb\u01ca\3\2\2\2\u01cc\u01cd\3\2\2\2\u01cd" +
					"\u01cb\3\2\2\2\u01cd\u01ce\3\2\2\2\u01ceG\3\2\2\2\u01cf\u01d0\t\6\2\2" +
					"\u01d0I\3\2\2\2\u01d1\u01d3\7\t\2\2\u01d2\u01d4\5L\'\2\u01d3\u01d2\3\2" +
					"\2\2\u01d4\u01d5\3\2\2\2\u01d5\u01d3\3\2\2\2\u01d5\u01d6\3\2\2\2\u01d6" +
					"K\3\2\2\2\u01d7\u01d8\t\7\2\2\u01d8M\3\2\2\2\u01d9\u01dd\7F\2\2\u01da" +
					"\u01db\7(\2\2\u01db\u01de\5\32\16\2\u01dc\u01de\5$\23\2\u01dd\u01da\3" +
					"\2\2\2\u01dd\u01dc\3\2\2\2\u01deO\3\2\2\2\u01df\u01e1\5T+\2\u01e0\u01df" +
					"\3\2\2\2\u01e1\u01e4\3\2\2\2\u01e2\u01e0\3\2\2\2\u01e2\u01e3\3\2\2\2\u01e3" +
					"\u01e5\3\2\2\2\u01e4\u01e2\3\2\2\2\u01e5\u01e6\7F\2\2\u01e6Q\3\2\2\2\u01e7" +
					"\u01e9\5T+\2\u01e8\u01e7\3\2\2\2\u01e9\u01ec\3\2\2\2\u01ea\u01e8\3\2\2" +
					"\2\u01ea\u01eb\3\2\2\2\u01eb\u01ed\3\2\2\2\u01ec\u01ea\3\2\2\2\u01ed\u01ee" +
					"\7F\2\2\u01eeS\3\2\2\2\u01ef\u01f0\7F\2\2\u01f0\u01f1\t\b\2\2\u01f1U\3" +
					"\2\2\2\u01f2\u01f3\7F\2\2\u01f3W\3\2\2\2L[_bhmw}\u0084\u0089\u008c\u0090" +
					"\u0096\u009a\u00a0\u00a7\u00ab\u00ae\u00b3\u00b7\u00b9\u00bc\u00c9\u00cc" +
					"\u00d2\u00da\u00de\u00e3\u00e8\u00ed\u00f2\u00f7\u00fc\u00ff\u0104\u0107" +
					"\u010c\u0111\u0114\u011b\u0120\u0124\u012c\u0138\u013c\u0141\u0144\u014a" +
					"\u014c\u014f\u0152\u0157\u015a\u016b\u0174\u0178\u017b\u017e\u0182\u0187" +
					"\u018e\u0190\u0194\u019a\u01a5\u01b3\u01b8\u01be\u01c4\u01c7\u01cd\u01d5" +
					"\u01dd\u01e2\u01ea";
	public static final ATN _ATN =
			new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}