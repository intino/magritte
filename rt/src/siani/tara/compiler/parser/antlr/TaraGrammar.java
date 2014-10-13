// Generated from /Users/oroncal/workspace/tara/rt/src/siani/tara/compiler/parser/antlr/TaraGrammar.g4 by ANTLR 4.4.1-dev

package siani.tara.compiler.parser.antlr;

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
	static {
		RuntimeMetaData.checkVersion("4.4.1-dev", RuntimeMetaData.VERSION);
	}

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		DOLLAR = 30, RIGHT_SQUARE = 25, FACET = 15, STAR = 40, NEGATIVE_VALUE = 59, DOC = 71,
		LETTER = 68, DATE_VALUE = 64, SUB = 2, DEDENT = 75, EQUALS = 37, BOOLEAN_VALUE = 57,
		METAIDENTIFIER = 1, RESOURCE = 47, UNKNOWN_TOKEN = 76, WORD = 46, AS = 5, HAS = 6,
		BOX = 4, RIGHT_PARENTHESIS = 23, DASHES = 43, ALWAYS = 21, COMMA = 35, IS = 8, IDENTIFIER = 66,
		LEFT_PARENTHESIS = 22, COORDINATE_VALUE = 65, STRING_MULTILINE_VALUE_KEY = 62,
		VAR = 45, NL = 73, DIGIT = 67, INTENTION = 16, DOT = 36, PORT_TYPE = 48, COMPONENT = 14,
		STRING_VALUE = 61, DOUBLE_VALUE = 60, WITH = 9, PORT_VALUE = 63, POSITIVE = 41,
		NEW_LINE_INDENT = 74, DASH = 42, PRIVATE = 11, COORDINATE_TYPE = 49, NATURAL_TYPE = 51,
		TERMINAL = 17, UNDERDASH = 44, ON = 7, AMPERSAND = 29, CLOSE_INLINE = 28, BOOLEAN_TYPE = 54,
		DOUBLE_TYPE = 52, SEMICOLON = 39, INT_TYPE = 50, REQUIRED = 13, LIST = 26, PERCENTAGE = 32,
		EMPTY = 56, COLON = 34, NATURAL_VALUE = 58, EURO = 31, GRADE = 33, NEWLINE = 69, SINGLE = 12,
		PROPERTY = 19, SPACES = 70, SP = 72, APHOSTROPHE = 38, STRING_TYPE = 53, NAMED = 18,
		DATE_TYPE = 55, INLINE = 27, USE = 3, UNIVERSAL = 20, LEFT_SQUARE = 24, EXTENDS = 10;
	public static final String[] tokenNames = {
		"<INVALID>", "'Concept'", "'sub'", "'use'", "'box'", "'as'", "'has'",
		"'on'", "'is'", "'with'", "'extends'", "'private'", "'single'", "'required'",
		"'component'", "'facet'", "'intention'", "'terminal'", "'named'", "'property'",
		"'universal'", "'always'", "'('", "')'", "'['", "']'", "'...'", "'>'",
		"'<'", "'&'", "'$'", "'€'", "'%'", "'º'", "':'", "','", "'.'", "'='",
		"'''", "SEMICOLON", "'*'", "'+'", "'-'", "DASHES", "'_'", "'var'", "'word'",
		"'resource'", "'port'", "'coordinate'", "'integer'", "'natural'", "'double'",
		"'string'", "'boolean'", "'date'", "'empty'", "BOOLEAN_VALUE", "NATURAL_VALUE",
		"NEGATIVE_VALUE", "DOUBLE_VALUE", "STRING_VALUE", "STRING_MULTILINE_VALUE_KEY",
		"PORT_VALUE", "DATE_VALUE", "COORDINATE_VALUE", "IDENTIFIER", "DIGIT",
		"LETTER", "NEWLINE", "SPACES", "DOC", "SP", "NL", "'indent'", "'dedent'",
		"UNKNOWN_TOKEN"
	};
	public static final int
		RULE_root = 0, RULE_header = 1, RULE_box = 2, RULE_imports = 3, RULE_anImport = 4,
		RULE_doc = 5, RULE_concept = 6, RULE_signature = 7, RULE_parameters = 8,
		RULE_parameterList = 9, RULE_explicit = 10, RULE_parameter = 11, RULE_metaWord = 12,
		RULE_metaWordNames = 13, RULE_body = 14, RULE_conceptReference = 15, RULE_facetApply = 16,
		RULE_facetTarget = 17, RULE_attribute = 18, RULE_resource = 19, RULE_word = 20,
		RULE_wordNames = 21, RULE_reference = 22, RULE_booleanAttribute = 23,
		RULE_stringAttribute = 24, RULE_naturalAttribute = 25, RULE_integerAttribute = 26,
		RULE_doubleAttribute = 27, RULE_dateAttribute = 28, RULE_coordinateAttribute = 29,
		RULE_portAttribute = 30, RULE_attributeType = 31, RULE_naturalValue = 32,
		RULE_integerValue = 33, RULE_doubleValue = 34, RULE_booleanValue = 35,
		RULE_stringValue = 36, RULE_portValue = 37, RULE_dateValue = 38, RULE_coordinateValue = 39,
		RULE_measure = 40, RULE_annotations = 41, RULE_varInit = 42, RULE_headerReference = 43,
		RULE_identifierReference = 44, RULE_hierarchy = 45, RULE_metaidentifier = 46;
	public static final String[] ruleNames = {
		"root", "header", "box", "imports", "anImport", "doc", "concept", "signature",
		"parameters", "parameterList", "explicit", "parameter", "metaWord", "metaWordNames",
		"body", "conceptReference", "facetApply", "facetTarget", "attribute",
		"resource", "word", "wordNames", "reference", "booleanAttribute", "stringAttribute",
		"naturalAttribute", "integerAttribute", "doubleAttribute", "dateAttribute",
		"coordinateAttribute", "portAttribute", "attributeType", "naturalValue",
		"integerValue", "doubleValue", "booleanValue", "stringValue", "portValue",
		"dateValue", "coordinateValue", "measure", "annotations", "varInit", "headerReference",
		"identifierReference", "hierarchy", "metaidentifier"
	};

	@Override
	public String getGrammarFileName() {
		return "TaraGrammar.g4";
	}

	@Override
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override
	public String[] getRuleNames() {
		return ruleNames;
	}

	@Override
	public String getSerializedATN() {
		return _serializedATN;
	}

	@Override
	public ATN getATN() {
		return _ATN;
	}

	public TaraGrammar(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
	}

	public static class RootContext extends ParserRuleContext {
		public List<ConceptContext> concept() {
			return getRuleContexts(ConceptContext.class);
		}

		public List<TerminalNode> NEWLINE() {
			return getTokens(TaraGrammar.NEWLINE);
		}

		public TerminalNode EOF() {
			return getToken(TaraGrammar.EOF, 0);
		}

		public TerminalNode NEWLINE(int i) {
			return getToken(TaraGrammar.NEWLINE, i);
		}

		public ConceptContext concept(int i) {
			return getRuleContext(ConceptContext.class, i);
		}

		public HeaderContext header() {
			return getRuleContext(HeaderContext.class, 0);
		}

		public RootContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_root;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).enterRoot(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).exitRoot(this);
		}
	}

	public final RootContext root() throws RecognitionException {
		RootContext _localctx = new RootContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_root);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
				setState(97);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 0, _ctx);
				while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1) {
						{
							{
								setState(94);
								match(NEWLINE);
							}
						}
					}
					setState(99);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 0, _ctx);
				}
				setState(101);
				switch (getInterpreter().adaptivePredict(_input, 1, _ctx)) {
					case 1: {
						setState(100);
						header();
					}
					break;
				}
				setState(104);
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
						{
							setState(103);
							match(NEWLINE);
						}
					}
					setState(106);
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while (_la == NEWLINE);
				setState(117);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << METAIDENTIFIER) | (1L << SUB) | (1L << INTENTION))) != 0) || _la == IDENTIFIER || _la == DOC) {
					{
						{
							setState(108);
							concept();
							setState(112);
							_errHandler.sync(this);
							_la = _input.LA(1);
							while (_la == NEWLINE) {
								{
									{
										setState(109);
										match(NEWLINE);
									}
								}
								setState(114);
								_errHandler.sync(this);
								_la = _input.LA(1);
							}
						}
					}
					setState(119);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(120);
				match(EOF);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class HeaderContext extends ParserRuleContext {
		public ImportsContext imports() {
			return getRuleContext(ImportsContext.class, 0);
		}

		public BoxContext box() {
			return getRuleContext(BoxContext.class, 0);
		}

		public HeaderContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_header;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).enterHeader(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).exitHeader(this);
		}
	}

	public final HeaderContext header() throws RecognitionException {
		HeaderContext _localctx = new HeaderContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_header);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(123);
				_la = _input.LA(1);
				if (_la == BOX) {
					{
						setState(122);
						box();
					}
				}

				setState(126);
				switch (getInterpreter().adaptivePredict(_input, 6, _ctx)) {
					case 1: {
						setState(125);
						imports();
					}
					break;
				}
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BoxContext extends ParserRuleContext {
		public HeaderReferenceContext headerReference() {
			return getRuleContext(HeaderReferenceContext.class, 0);
		}

		public TerminalNode BOX() {
			return getToken(TaraGrammar.BOX, 0);
		}

		public BoxContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_box;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).enterBox(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).exitBox(this);
		}
	}

	public final BoxContext box() throws RecognitionException {
		BoxContext _localctx = new BoxContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_box);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(128);
				match(BOX);
				setState(129);
				headerReference();
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ImportsContext extends ParserRuleContext {
		public AnImportContext anImport(int i) {
			return getRuleContext(AnImportContext.class, i);
		}

		public List<AnImportContext> anImport() {
			return getRuleContexts(AnImportContext.class);
		}

		public ImportsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_imports;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).enterImports(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).exitImports(this);
		}
	}

	public final ImportsContext imports() throws RecognitionException {
		ImportsContext _localctx = new ImportsContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_imports);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
				setState(132);
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
						case 1: {
							{
								setState(131);
								anImport();
							}
						}
						break;
						default:
							throw new NoViableAltException(this);
					}
					setState(134);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 7, _ctx);
				} while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AnImportContext extends ParserRuleContext {
		public TerminalNode AS() {
			return getToken(TaraGrammar.AS, 0);
		}

		public List<TerminalNode> NEWLINE() {
			return getTokens(TaraGrammar.NEWLINE);
		}

		public HeaderReferenceContext headerReference() {
			return getRuleContext(HeaderReferenceContext.class, 0);
		}

		public TerminalNode NEWLINE(int i) {
			return getToken(TaraGrammar.NEWLINE, i);
		}

		public TerminalNode USE() {
			return getToken(TaraGrammar.USE, 0);
		}

		public TerminalNode IDENTIFIER() {
			return getToken(TaraGrammar.IDENTIFIER, 0);
		}

		public AnImportContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_anImport;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).enterAnImport(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).exitAnImport(this);
		}
	}

	public final AnImportContext anImport() throws RecognitionException {
		AnImportContext _localctx = new AnImportContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_anImport);
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
							match(NEWLINE);
						}
					}
					setState(139);
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while (_la == NEWLINE);
				setState(141);
				match(USE);
				setState(142);
				headerReference();
				setState(145);
				_la = _input.LA(1);
				if (_la == AS) {
					{
						setState(143);
						match(AS);
						setState(144);
						match(IDENTIFIER);
					}
				}

			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DocContext extends ParserRuleContext {
		public TerminalNode DOC(int i) {
			return getToken(TaraGrammar.DOC, i);
		}

		public List<TerminalNode> DOC() {
			return getTokens(TaraGrammar.DOC);
		}

		public DocContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_doc;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).enterDoc(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).exitDoc(this);
		}
	}

	public final DocContext doc() throws RecognitionException {
		DocContext _localctx = new DocContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_doc);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(148);
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
						{
							setState(147);
							match(DOC);
						}
					}
					setState(150);
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while (_la == DOC);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConceptContext extends ParserRuleContext {
		public BodyContext body() {
			return getRuleContext(BodyContext.class, 0);
		}

		public DocContext doc() {
			return getRuleContext(DocContext.class, 0);
		}

		public AnnotationsContext annotations() {
			return getRuleContext(AnnotationsContext.class, 0);
		}

		public SignatureContext signature() {
			return getRuleContext(SignatureContext.class, 0);
		}

		public ConceptContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_concept;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).enterConcept(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).exitConcept(this);
		}
	}

	public final ConceptContext concept() throws RecognitionException {
		ConceptContext _localctx = new ConceptContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_concept);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(153);
				_la = _input.LA(1);
				if (_la == DOC) {
					{
						setState(152);
						doc();
					}
				}

				setState(155);
				signature();
				setState(157);
				_la = _input.LA(1);
				if (_la == IS) {
					{
						setState(156);
						annotations();
					}
				}

				setState(160);
				_la = _input.LA(1);
				if (_la == NEW_LINE_INDENT) {
					{
						setState(159);
						body();
					}
				}

			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SignatureContext extends ParserRuleContext {
		public IdentifierReferenceContext identifierReference() {
			return getRuleContext(IdentifierReferenceContext.class, 0);
		}

		public TerminalNode IDENTIFIER() {
			return getToken(TaraGrammar.IDENTIFIER, 0);
		}

		public MetaidentifierContext metaidentifier() {
			return getRuleContext(MetaidentifierContext.class, 0);
		}

		public ParametersContext parameters() {
			return getRuleContext(ParametersContext.class, 0);
		}

		public TerminalNode EXTENDS() {
			return getToken(TaraGrammar.EXTENDS, 0);
		}

		public TerminalNode SUB() {
			return getToken(TaraGrammar.SUB, 0);
		}

		public SignatureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_signature;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).enterSignature(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).exitSignature(this);
		}
	}

	public final SignatureContext signature() throws RecognitionException {
		SignatureContext _localctx = new SignatureContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_signature);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(177);
				switch (getInterpreter().adaptivePredict(_input, 17, _ctx)) {
					case 1: {
						{
							setState(162);
							match(SUB);
							setState(163);
							match(IDENTIFIER);
						}
					}
					break;
					case 2: {
						{
							setState(164);
							metaidentifier();
							setState(166);
							_la = _input.LA(1);
							if (_la == LEFT_PARENTHESIS) {
								{
									setState(165);
									parameters();
								}
							}

							setState(168);
							match(IDENTIFIER);
							setState(171);
							_la = _input.LA(1);
							if (_la == EXTENDS) {
								{
									setState(169);
									match(EXTENDS);
									setState(170);
									identifierReference();
								}
							}

						}
					}
					break;
					case 3: {
						setState(173);
						metaidentifier();
						setState(175);
						_la = _input.LA(1);
						if (_la == LEFT_PARENTHESIS) {
							{
								setState(174);
								parameters();
							}
						}

					}
					break;
				}
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParametersContext extends ParserRuleContext {
		public TerminalNode LEFT_PARENTHESIS() {
			return getToken(TaraGrammar.LEFT_PARENTHESIS, 0);
		}

		public TerminalNode RIGHT_PARENTHESIS() {
			return getToken(TaraGrammar.RIGHT_PARENTHESIS, 0);
		}

		public ParameterListContext parameterList() {
			return getRuleContext(ParameterListContext.class, 0);
		}

		public ParametersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_parameters;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).enterParameters(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).exitParameters(this);
		}
	}

	public final ParametersContext parameters() throws RecognitionException {
		ParametersContext _localctx = new ParametersContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_parameters);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(179);
				match(LEFT_PARENTHESIS);
				setState(181);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << METAIDENTIFIER) | (1L << INTENTION) | (1L << BOOLEAN_VALUE) | (1L << NATURAL_VALUE) | (1L << NEGATIVE_VALUE) | (1L << DOUBLE_VALUE) | (1L << STRING_VALUE) | (1L << STRING_MULTILINE_VALUE_KEY) | (1L << PORT_VALUE))) != 0) || _la == DATE_VALUE || _la == IDENTIFIER) {
					{
						setState(180);
						parameterList();
					}
				}

				setState(183);
				match(RIGHT_PARENTHESIS);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParameterListContext extends ParserRuleContext {
		public ExplicitContext explicit(int i) {
			return getRuleContext(ExplicitContext.class, i);
		}

		public List<ExplicitContext> explicit() {
			return getRuleContexts(ExplicitContext.class);
		}

		public List<TerminalNode> COMMA() {
			return getTokens(TaraGrammar.COMMA);
		}

		public List<ParameterContext> parameter() {
			return getRuleContexts(ParameterContext.class);
		}

		public ParameterContext parameter(int i) {
			return getRuleContext(ParameterContext.class, i);
		}

		public TerminalNode COMMA(int i) {
			return getToken(TaraGrammar.COMMA, i);
		}

		public ParameterListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_parameterList;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).enterParameterList(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).exitParameterList(this);
		}
	}

	public final ParameterListContext parameterList() throws RecognitionException {
		ParameterListContext _localctx = new ParameterListContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_parameterList);
		int _la;
		try {
			setState(201);
			switch (getInterpreter().adaptivePredict(_input, 21, _ctx)) {
				case 1:
					enterOuterAlt(_localctx, 1);
				{
					{
						setState(185);
						explicit();
						setState(186);
						parameter();
						setState(193);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la == COMMA) {
							{
								{
									setState(187);
									match(COMMA);
									setState(188);
									explicit();
									setState(189);
									parameter();
								}
							}
							setState(195);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
					}
				}
				break;
				case 2:
					enterOuterAlt(_localctx, 2);
				{
					setState(197);
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
							{
								setState(196);
								parameter();
							}
						}
						setState(199);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << METAIDENTIFIER) | (1L << INTENTION) | (1L << BOOLEAN_VALUE) | (1L << NATURAL_VALUE) | (1L << NEGATIVE_VALUE) | (1L << DOUBLE_VALUE) | (1L << STRING_VALUE) | (1L << STRING_MULTILINE_VALUE_KEY) | (1L << PORT_VALUE))) != 0) || _la == DATE_VALUE || _la == IDENTIFIER);
				}
				break;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExplicitContext extends ParserRuleContext {
		public TerminalNode EQUALS() {
			return getToken(TaraGrammar.EQUALS, 0);
		}

		public TerminalNode IDENTIFIER() {
			return getToken(TaraGrammar.IDENTIFIER, 0);
		}

		public ExplicitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_explicit;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).enterExplicit(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).exitExplicit(this);
		}
	}

	public final ExplicitContext explicit() throws RecognitionException {
		ExplicitContext _localctx = new ExplicitContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_explicit);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(203);
				match(IDENTIFIER);
				setState(204);
				match(EQUALS);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParameterContext extends ParserRuleContext {
		public List<BooleanValueContext> booleanValue() {
			return getRuleContexts(BooleanValueContext.class);
		}

		public BooleanValueContext booleanValue(int i) {
			return getRuleContext(BooleanValueContext.class, i);
		}

		public List<DoubleValueContext> doubleValue() {
			return getRuleContexts(DoubleValueContext.class);
		}

		public List<StringValueContext> stringValue() {
			return getRuleContexts(StringValueContext.class);
		}

		public List<DateValueContext> dateValue() {
			return getRuleContexts(DateValueContext.class);
		}

		public DateValueContext dateValue(int i) {
			return getRuleContext(DateValueContext.class, i);
		}

		public PortValueContext portValue(int i) {
			return getRuleContext(PortValueContext.class, i);
		}

		public IdentifierReferenceContext identifierReference() {
			return getRuleContext(IdentifierReferenceContext.class, 0);
		}

		public DoubleValueContext doubleValue(int i) {
			return getRuleContext(DoubleValueContext.class, i);
		}

		public IntegerValueContext integerValue(int i) {
			return getRuleContext(IntegerValueContext.class, i);
		}

		public List<NaturalValueContext> naturalValue() {
			return getRuleContexts(NaturalValueContext.class);
		}

		public MeasureContext measure() {
			return getRuleContext(MeasureContext.class, 0);
		}

		public List<PortValueContext> portValue() {
			return getRuleContexts(PortValueContext.class);
		}

		public NaturalValueContext naturalValue(int i) {
			return getRuleContext(NaturalValueContext.class, i);
		}

		public StringValueContext stringValue(int i) {
			return getRuleContext(StringValueContext.class, i);
		}

		public MetaWordContext metaWord() {
			return getRuleContext(MetaWordContext.class, 0);
		}

		public List<IntegerValueContext> integerValue() {
			return getRuleContexts(IntegerValueContext.class);
		}

		public ParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_parameter;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).enterParameter(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).exitParameter(this);
		}
	}

	public final ParameterContext parameter() throws RecognitionException {
		ParameterContext _localctx = new ParameterContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_parameter);
		try {
			int _alt;
			setState(252);
			switch (getInterpreter().adaptivePredict(_input, 32, _ctx)) {
				case 1:
					enterOuterAlt(_localctx, 1);
				{
					setState(206);
					identifierReference();
				}
				break;
				case 2:
					enterOuterAlt(_localctx, 2);
				{
					setState(208);
					_errHandler.sync(this);
					_alt = 1;
					do {
						switch (_alt) {
							case 1: {
								{
									setState(207);
									stringValue();
								}
							}
							break;
							default:
								throw new NoViableAltException(this);
						}
						setState(210);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input, 22, _ctx);
					} while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER);
				}
				break;
				case 3:
					enterOuterAlt(_localctx, 3);
				{
					setState(213);
					_errHandler.sync(this);
					_alt = 1;
					do {
						switch (_alt) {
							case 1: {
								{
									setState(212);
									booleanValue();
								}
							}
							break;
							default:
								throw new NoViableAltException(this);
						}
						setState(215);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input, 23, _ctx);
					} while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER);
				}
				break;
				case 4:
					enterOuterAlt(_localctx, 4);
				{
					setState(218);
					_errHandler.sync(this);
					_alt = 1;
					do {
						switch (_alt) {
							case 1: {
								{
									setState(217);
									naturalValue();
								}
							}
							break;
							default:
								throw new NoViableAltException(this);
						}
						setState(220);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input, 24, _ctx);
					} while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER);
					setState(223);
					switch (getInterpreter().adaptivePredict(_input, 25, _ctx)) {
						case 1: {
							setState(222);
							measure();
						}
						break;
					}
				}
				break;
				case 5:
					enterOuterAlt(_localctx, 5);
				{
					setState(226);
					_errHandler.sync(this);
					_alt = 1;
					do {
						switch (_alt) {
							case 1: {
								{
									setState(225);
									integerValue();
								}
							}
							break;
							default:
								throw new NoViableAltException(this);
						}
						setState(228);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input, 26, _ctx);
					} while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER);
					setState(231);
					switch (getInterpreter().adaptivePredict(_input, 27, _ctx)) {
						case 1: {
							setState(230);
							measure();
						}
						break;
					}
				}
				break;
				case 6:
					enterOuterAlt(_localctx, 6);
				{
					setState(234);
					_errHandler.sync(this);
					_alt = 1;
					do {
						switch (_alt) {
							case 1: {
								{
									setState(233);
									doubleValue();
								}
							}
							break;
							default:
								throw new NoViableAltException(this);
						}
						setState(236);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input, 28, _ctx);
					} while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER);
					setState(239);
					switch (getInterpreter().adaptivePredict(_input, 29, _ctx)) {
						case 1: {
							setState(238);
							measure();
						}
						break;
					}
				}
				break;
				case 7:
					enterOuterAlt(_localctx, 7);
				{
					setState(242);
					_errHandler.sync(this);
					_alt = 1;
					do {
						switch (_alt) {
							case 1: {
								{
									setState(241);
									dateValue();
								}
							}
							break;
							default:
								throw new NoViableAltException(this);
						}
						setState(244);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input, 30, _ctx);
					} while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER);
				}
				break;
				case 8:
					enterOuterAlt(_localctx, 8);
				{
					setState(247);
					_errHandler.sync(this);
					_alt = 1;
					do {
						switch (_alt) {
							case 1: {
								{
									setState(246);
									portValue();
								}
							}
							break;
							default:
								throw new NoViableAltException(this);
						}
						setState(249);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input, 31, _ctx);
					} while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER);
				}
				break;
				case 9:
					enterOuterAlt(_localctx, 9);
				{
					setState(251);
					metaWord();
				}
				break;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MetaWordContext extends ParserRuleContext {
		public MetaWordNamesContext metaWordNames(int i) {
			return getRuleContext(MetaWordNamesContext.class, i);
		}

		public MetaidentifierContext metaidentifier() {
			return getRuleContext(MetaidentifierContext.class, 0);
		}

		public List<MetaWordNamesContext> metaWordNames() {
			return getRuleContexts(MetaWordNamesContext.class);
		}

		public MetaWordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_metaWord;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).enterMetaWord(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).exitMetaWord(this);
		}
	}

	public final MetaWordContext metaWord() throws RecognitionException {
		MetaWordContext _localctx = new MetaWordContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_metaWord);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(254);
				metaidentifier();
				setState(258);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == DOT) {
					{
						{
							setState(255);
							metaWordNames();
						}
					}
					setState(260);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MetaWordNamesContext extends ParserRuleContext {
		public TerminalNode DOT() {
			return getToken(TaraGrammar.DOT, 0);
		}

		public TerminalNode IDENTIFIER() {
			return getToken(TaraGrammar.IDENTIFIER, 0);
		}

		public MetaWordNamesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_metaWordNames;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).enterMetaWordNames(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).exitMetaWordNames(this);
		}
	}

	public final MetaWordNamesContext metaWordNames() throws RecognitionException {
		MetaWordNamesContext _localctx = new MetaWordNamesContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_metaWordNames);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(261);
				match(DOT);
				setState(262);
				match(IDENTIFIER);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BodyContext extends ParserRuleContext {
		public List<ConceptContext> concept() {
			return getRuleContexts(ConceptContext.class);
		}

		public TerminalNode NEWLINE(int i) {
			return getToken(TaraGrammar.NEWLINE, i);
		}

		public List<AttributeContext> attribute() {
			return getRuleContexts(AttributeContext.class);
		}

		public List<VarInitContext> varInit() {
			return getRuleContexts(VarInitContext.class);
		}

		public VarInitContext varInit(int i) {
			return getRuleContext(VarInitContext.class, i);
		}

		public AttributeContext attribute(int i) {
			return getRuleContext(AttributeContext.class, i);
		}

		public FacetApplyContext facetApply(int i) {
			return getRuleContext(FacetApplyContext.class, i);
		}

		public TerminalNode NEW_LINE_INDENT() {
			return getToken(TaraGrammar.NEW_LINE_INDENT, 0);
		}

		public List<TerminalNode> NEWLINE() {
			return getTokens(TaraGrammar.NEWLINE);
		}

		public ConceptReferenceContext conceptReference(int i) {
			return getRuleContext(ConceptReferenceContext.class, i);
		}

		public List<ConceptReferenceContext> conceptReference() {
			return getRuleContexts(ConceptReferenceContext.class);
		}

		public List<FacetTargetContext> facetTarget() {
			return getRuleContexts(FacetTargetContext.class);
		}

		public ConceptContext concept(int i) {
			return getRuleContext(ConceptContext.class, i);
		}

		public TerminalNode DEDENT() {
			return getToken(TaraGrammar.DEDENT, 0);
		}

		public FacetTargetContext facetTarget(int i) {
			return getRuleContext(FacetTargetContext.class, i);
		}

		public List<FacetApplyContext> facetApply() {
			return getRuleContexts(FacetApplyContext.class);
		}

		public BodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_body;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).enterBody(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).exitBody(this);
		}
	}

	public final BodyContext body() throws RecognitionException {
		BodyContext _localctx = new BodyContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_body);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(264);
				match(NEW_LINE_INDENT);
				setState(278);
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
						{
							setState(271);
							switch (getInterpreter().adaptivePredict(_input, 34, _ctx)) {
								case 1: {
									setState(265);
									attribute();
								}
								break;
								case 2: {
									setState(266);
									concept();
								}
								break;
								case 3: {
									setState(267);
									varInit();
								}
								break;
								case 4: {
									setState(268);
									facetApply();
								}
								break;
								case 5: {
									setState(269);
									facetTarget();
								}
								break;
								case 6: {
									setState(270);
									conceptReference();
								}
								break;
							}
							setState(274);
							_errHandler.sync(this);
							_la = _input.LA(1);
							do {
								{
									{
										setState(273);
										match(NEWLINE);
									}
								}
								setState(276);
								_errHandler.sync(this);
								_la = _input.LA(1);
							} while (_la == NEWLINE);
						}
					}
					setState(280);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << METAIDENTIFIER) | (1L << SUB) | (1L << HAS) | (1L << ON) | (1L << IS) | (1L << INTENTION) | (1L << VAR))) != 0) || _la == IDENTIFIER || _la == DOC);
				setState(282);
				match(DEDENT);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConceptReferenceContext extends ParserRuleContext {
		public IdentifierReferenceContext identifierReference() {
			return getRuleContext(IdentifierReferenceContext.class, 0);
		}

		public TerminalNode HAS() {
			return getToken(TaraGrammar.HAS, 0);
		}

		public TerminalNode IDENTIFIER() {
			return getToken(TaraGrammar.IDENTIFIER, 0);
		}

		public DocContext doc() {
			return getRuleContext(DocContext.class, 0);
		}

		public ConceptReferenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_conceptReference;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).enterConceptReference(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).exitConceptReference(this);
		}
	}

	public final ConceptReferenceContext conceptReference() throws RecognitionException {
		ConceptReferenceContext _localctx = new ConceptReferenceContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_conceptReference);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(285);
				_la = _input.LA(1);
				if (_la == DOC) {
					{
						setState(284);
						doc();
					}
				}

				setState(287);
				match(HAS);
				setState(288);
				identifierReference();
				setState(290);
				_la = _input.LA(1);
				if (_la == IDENTIFIER) {
					{
						setState(289);
						match(IDENTIFIER);
					}
				}

			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FacetApplyContext extends ParserRuleContext {
		public BodyContext body() {
			return getRuleContext(BodyContext.class, 0);
		}

		public List<MetaidentifierContext> metaidentifier() {
			return getRuleContexts(MetaidentifierContext.class);
		}

		public TerminalNode WITH() {
			return getToken(TaraGrammar.WITH, 0);
		}

		public ParametersContext parameters() {
			return getRuleContext(ParametersContext.class, 0);
		}

		public MetaidentifierContext metaidentifier(int i) {
			return getRuleContext(MetaidentifierContext.class, i);
		}

		public TerminalNode IS() {
			return getToken(TaraGrammar.IS, 0);
		}

		public FacetApplyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_facetApply;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).enterFacetApply(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).exitFacetApply(this);
		}
	}

	public final FacetApplyContext facetApply() throws RecognitionException {
		FacetApplyContext _localctx = new FacetApplyContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_facetApply);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(292);
				match(IS);
				setState(293);
				metaidentifier();
				setState(295);
				_la = _input.LA(1);
				if (_la == LEFT_PARENTHESIS) {
					{
						setState(294);
						parameters();
					}
				}

				setState(299);
				_la = _input.LA(1);
				if (_la == WITH) {
					{
						setState(297);
						match(WITH);
						setState(298);
						metaidentifier();
					}
				}

				setState(302);
				_la = _input.LA(1);
				if (_la == NEW_LINE_INDENT) {
					{
						setState(301);
						body();
					}
				}

			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FacetTargetContext extends ParserRuleContext {
		public IdentifierReferenceContext identifierReference() {
			return getRuleContext(IdentifierReferenceContext.class, 0);
		}

		public TerminalNode ON() {
			return getToken(TaraGrammar.ON, 0);
		}

		public BodyContext body() {
			return getRuleContext(BodyContext.class, 0);
		}

		public TerminalNode ALWAYS() {
			return getToken(TaraGrammar.ALWAYS, 0);
		}

		public FacetTargetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_facetTarget;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).enterFacetTarget(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).exitFacetTarget(this);
		}
	}

	public final FacetTargetContext facetTarget() throws RecognitionException {
		FacetTargetContext _localctx = new FacetTargetContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_facetTarget);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(304);
				match(ON);
				setState(305);
				identifierReference();
				setState(307);
				_la = _input.LA(1);
				if (_la == ALWAYS) {
					{
						setState(306);
						match(ALWAYS);
					}
				}

				setState(310);
				_la = _input.LA(1);
				if (_la == NEW_LINE_INDENT) {
					{
						setState(309);
						body();
					}
				}

			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AttributeContext extends ParserRuleContext {
		public DoubleAttributeContext doubleAttribute() {
			return getRuleContext(DoubleAttributeContext.class, 0);
		}

		public PortAttributeContext portAttribute() {
			return getRuleContext(PortAttributeContext.class, 0);
		}

		public IntegerAttributeContext integerAttribute() {
			return getRuleContext(IntegerAttributeContext.class, 0);
		}

		public DateAttributeContext dateAttribute() {
			return getRuleContext(DateAttributeContext.class, 0);
		}

		public StringAttributeContext stringAttribute() {
			return getRuleContext(StringAttributeContext.class, 0);
		}

		public CoordinateAttributeContext coordinateAttribute() {
			return getRuleContext(CoordinateAttributeContext.class, 0);
		}

		public ResourceContext resource() {
			return getRuleContext(ResourceContext.class, 0);
		}

		public WordContext word() {
			return getRuleContext(WordContext.class, 0);
		}

		public BooleanAttributeContext booleanAttribute() {
			return getRuleContext(BooleanAttributeContext.class, 0);
		}

		public TerminalNode VAR() {
			return getToken(TaraGrammar.VAR, 0);
		}

		public DocContext doc() {
			return getRuleContext(DocContext.class, 0);
		}

		public AnnotationsContext annotations() {
			return getRuleContext(AnnotationsContext.class, 0);
		}

		public NaturalAttributeContext naturalAttribute() {
			return getRuleContext(NaturalAttributeContext.class, 0);
		}

		public ReferenceContext reference() {
			return getRuleContext(ReferenceContext.class, 0);
		}

		public AttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_attribute;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).enterAttribute(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).exitAttribute(this);
		}
	}

	public final AttributeContext attribute() throws RecognitionException {
		AttributeContext _localctx = new AttributeContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_attribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(313);
				_la = _input.LA(1);
				if (_la == DOC) {
					{
						setState(312);
						doc();
					}
				}

				setState(315);
				match(VAR);
				setState(327);
				switch (_input.LA(1)) {
					case NATURAL_TYPE: {
						setState(316);
						naturalAttribute();
					}
					break;
					case INT_TYPE: {
						setState(317);
						integerAttribute();
					}
					break;
					case DOUBLE_TYPE: {
						setState(318);
						doubleAttribute();
					}
					break;
					case BOOLEAN_TYPE: {
						setState(319);
						booleanAttribute();
					}
					break;
					case STRING_TYPE: {
						setState(320);
						stringAttribute();
					}
					break;
					case DATE_TYPE: {
						setState(321);
						dateAttribute();
					}
					break;
					case COORDINATE_TYPE: {
						setState(322);
						coordinateAttribute();
					}
					break;
					case PORT_TYPE: {
						setState(323);
						portAttribute();
					}
					break;
					case RESOURCE: {
						setState(324);
						resource();
					}
					break;
					case IDENTIFIER: {
						setState(325);
						reference();
					}
					break;
					case WORD: {
						setState(326);
						word();
					}
					break;
					default:
						throw new NoViableAltException(this);
				}
				setState(330);
				_la = _input.LA(1);
				if (_la == IS) {
					{
						setState(329);
						annotations();
					}
				}

			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ResourceContext extends ParserRuleContext {
		public AttributeTypeContext attributeType() {
			return getRuleContext(AttributeTypeContext.class, 0);
		}

		public TerminalNode RESOURCE() {
			return getToken(TaraGrammar.RESOURCE, 0);
		}

		public TerminalNode IDENTIFIER() {
			return getToken(TaraGrammar.IDENTIFIER, 0);
		}

		public ResourceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_resource;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).enterResource(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).exitResource(this);
		}
	}

	public final ResourceContext resource() throws RecognitionException {
		ResourceContext _localctx = new ResourceContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_resource);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(332);
				match(RESOURCE);
				setState(333);
				attributeType();
				setState(334);
				match(IDENTIFIER);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WordContext extends ParserRuleContext {
		public List<TerminalNode> NEWLINE() {
			return getTokens(TaraGrammar.NEWLINE);
		}

		public TerminalNode WORD() {
			return getToken(TaraGrammar.WORD, 0);
		}

		public TerminalNode NEWLINE(int i) {
			return getToken(TaraGrammar.NEWLINE, i);
		}

		public WordNamesContext wordNames(int i) {
			return getRuleContext(WordNamesContext.class, i);
		}

		public TerminalNode IDENTIFIER() {
			return getToken(TaraGrammar.IDENTIFIER, 0);
		}

		public TerminalNode DEDENT() {
			return getToken(TaraGrammar.DEDENT, 0);
		}

		public List<WordNamesContext> wordNames() {
			return getRuleContexts(WordNamesContext.class);
		}

		public TerminalNode NEW_LINE_INDENT() {
			return getToken(TaraGrammar.NEW_LINE_INDENT, 0);
		}

		public WordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_word;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).enterWord(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).exitWord(this);
		}
	}

	public final WordContext word() throws RecognitionException {
		WordContext _localctx = new WordContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_word);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(336);
				match(WORD);
				setState(337);
				match(IDENTIFIER);
				setState(338);
				match(NEW_LINE_INDENT);
				setState(342);
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
						{
							setState(339);
							wordNames();
							setState(340);
							match(NEWLINE);
						}
					}
					setState(344);
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while (_la == IDENTIFIER);
				setState(346);
				match(DEDENT);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WordNamesContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() {
			return getToken(TaraGrammar.IDENTIFIER, 0);
		}

		public TerminalNode STAR() {
			return getToken(TaraGrammar.STAR, 0);
		}

		public WordNamesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_wordNames;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).enterWordNames(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).exitWordNames(this);
		}
	}

	public final WordNamesContext wordNames() throws RecognitionException {
		WordNamesContext _localctx = new WordNamesContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_wordNames);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(348);
				match(IDENTIFIER);
				setState(350);
				_la = _input.LA(1);
				if (_la == STAR) {
					{
						setState(349);
						match(STAR);
					}
				}

			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReferenceContext extends ParserRuleContext {
		public TerminalNode EMPTY() {
			return getToken(TaraGrammar.EMPTY, 0);
		}

		public IdentifierReferenceContext identifierReference() {
			return getRuleContext(IdentifierReferenceContext.class, 0);
		}

		public TerminalNode EQUALS() {
			return getToken(TaraGrammar.EQUALS, 0);
		}

		public TerminalNode IDENTIFIER() {
			return getToken(TaraGrammar.IDENTIFIER, 0);
		}

		public TerminalNode LIST() {
			return getToken(TaraGrammar.LIST, 0);
		}

		public ReferenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_reference;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).enterReference(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).exitReference(this);
		}
	}

	public final ReferenceContext reference() throws RecognitionException {
		ReferenceContext _localctx = new ReferenceContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_reference);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(352);
				identifierReference();
				setState(354);
				_la = _input.LA(1);
				if (_la == LIST) {
					{
						setState(353);
						match(LIST);
					}
				}

				setState(356);
				match(IDENTIFIER);
				setState(359);
				_la = _input.LA(1);
				if (_la == EQUALS) {
					{
						setState(357);
						match(EQUALS);
						setState(358);
						match(EMPTY);
					}
				}

			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BooleanAttributeContext extends ParserRuleContext {
		public TerminalNode EMPTY() {
			return getToken(TaraGrammar.EMPTY, 0);
		}

		public BooleanValueContext booleanValue(int i) {
			return getRuleContext(BooleanValueContext.class, i);
		}

		public List<BooleanValueContext> booleanValue() {
			return getRuleContexts(BooleanValueContext.class);
		}

		public TerminalNode BOOLEAN_TYPE() {
			return getToken(TaraGrammar.BOOLEAN_TYPE, 0);
		}

		public TerminalNode EQUALS() {
			return getToken(TaraGrammar.EQUALS, 0);
		}

		public TerminalNode IDENTIFIER() {
			return getToken(TaraGrammar.IDENTIFIER, 0);
		}

		public TerminalNode LIST() {
			return getToken(TaraGrammar.LIST, 0);
		}

		public BooleanAttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_booleanAttribute;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).enterBooleanAttribute(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).exitBooleanAttribute(this);
		}
	}

	public final BooleanAttributeContext booleanAttribute() throws RecognitionException {
		BooleanAttributeContext _localctx = new BooleanAttributeContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_booleanAttribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(361);
				match(BOOLEAN_TYPE);
				setState(363);
				_la = _input.LA(1);
				if (_la == LIST) {
					{
						setState(362);
						match(LIST);
					}
				}

				setState(365);
				match(IDENTIFIER);
				setState(373);
				switch (_input.LA(1)) {
					case EQUALS: {
						setState(366);
						match(EQUALS);
						setState(368);
						_errHandler.sync(this);
						_la = _input.LA(1);
						do {
							{
								{
									setState(367);
									booleanValue();
								}
							}
							setState(370);
							_errHandler.sync(this);
							_la = _input.LA(1);
						} while (_la == BOOLEAN_VALUE);
					}
					break;
					case EMPTY: {
						setState(372);
						match(EMPTY);
					}
					break;
					case IS:
					case NEWLINE:
						break;
					default:
						throw new NoViableAltException(this);
				}
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StringAttributeContext extends ParserRuleContext {
		public TerminalNode EMPTY() {
			return getToken(TaraGrammar.EMPTY, 0);
		}

		public TerminalNode EQUALS() {
			return getToken(TaraGrammar.EQUALS, 0);
		}

		public TerminalNode IDENTIFIER() {
			return getToken(TaraGrammar.IDENTIFIER, 0);
		}

		public TerminalNode STRING_TYPE() {
			return getToken(TaraGrammar.STRING_TYPE, 0);
		}

		public List<StringValueContext> stringValue() {
			return getRuleContexts(StringValueContext.class);
		}

		public StringValueContext stringValue(int i) {
			return getRuleContext(StringValueContext.class, i);
		}

		public TerminalNode LIST() {
			return getToken(TaraGrammar.LIST, 0);
		}

		public StringAttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_stringAttribute;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).enterStringAttribute(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).exitStringAttribute(this);
		}
	}

	public final StringAttributeContext stringAttribute() throws RecognitionException {
		StringAttributeContext _localctx = new StringAttributeContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_stringAttribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(375);
				match(STRING_TYPE);
				setState(377);
				_la = _input.LA(1);
				if (_la == LIST) {
					{
						setState(376);
						match(LIST);
					}
				}

				setState(379);
				match(IDENTIFIER);
				setState(387);
				switch (_input.LA(1)) {
					case EQUALS: {
						setState(380);
						match(EQUALS);
						setState(382);
						_errHandler.sync(this);
						_la = _input.LA(1);
						do {
							{
								{
									setState(381);
									stringValue();
								}
							}
							setState(384);
							_errHandler.sync(this);
							_la = _input.LA(1);
						} while (_la == STRING_VALUE || _la == STRING_MULTILINE_VALUE_KEY);
					}
					break;
					case EMPTY: {
						setState(386);
						match(EMPTY);
					}
					break;
					case IS:
					case NEWLINE:
						break;
					default:
						throw new NoViableAltException(this);
				}
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NaturalAttributeContext extends ParserRuleContext {
		public TerminalNode EMPTY() {
			return getToken(TaraGrammar.EMPTY, 0);
		}

		public AttributeTypeContext attributeType() {
			return getRuleContext(AttributeTypeContext.class, 0);
		}

		public TerminalNode NATURAL_TYPE() {
			return getToken(TaraGrammar.NATURAL_TYPE, 0);
		}

		public MeasureContext measure() {
			return getRuleContext(MeasureContext.class, 0);
		}

		public List<NaturalValueContext> naturalValue() {
			return getRuleContexts(NaturalValueContext.class);
		}

		public TerminalNode EQUALS() {
			return getToken(TaraGrammar.EQUALS, 0);
		}

		public TerminalNode IDENTIFIER() {
			return getToken(TaraGrammar.IDENTIFIER, 0);
		}

		public NaturalValueContext naturalValue(int i) {
			return getRuleContext(NaturalValueContext.class, i);
		}

		public TerminalNode LIST() {
			return getToken(TaraGrammar.LIST, 0);
		}

		public NaturalAttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_naturalAttribute;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).enterNaturalAttribute(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).exitNaturalAttribute(this);
		}
	}

	public final NaturalAttributeContext naturalAttribute() throws RecognitionException {
		NaturalAttributeContext _localctx = new NaturalAttributeContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_naturalAttribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(389);
				match(NATURAL_TYPE);
				setState(391);
				_la = _input.LA(1);
				if (_la == COLON) {
					{
						setState(390);
						attributeType();
					}
				}

				setState(394);
				_la = _input.LA(1);
				if (_la == LIST) {
					{
						setState(393);
						match(LIST);
					}
				}

				setState(396);
				match(IDENTIFIER);
				setState(407);
				switch (_input.LA(1)) {
					case EQUALS: {
						setState(397);
						match(EQUALS);
						setState(399);
						_errHandler.sync(this);
						_la = _input.LA(1);
						do {
							{
								{
									setState(398);
									naturalValue();
								}
							}
							setState(401);
							_errHandler.sync(this);
							_la = _input.LA(1);
						} while (_la == NATURAL_VALUE);
						setState(404);
						_la = _input.LA(1);
						if (((((_la - 30)) & ~0x3f) == 0 && ((1L << (_la - 30)) & ((1L << (DOLLAR - 30)) | (1L << (EURO - 30)) | (1L << (PERCENTAGE - 30)) | (1L << (GRADE - 30)) | (1L << (IDENTIFIER - 30)))) != 0)) {
							{
								setState(403);
								measure();
							}
						}

					}
					break;
					case EMPTY: {
						setState(406);
						match(EMPTY);
					}
					break;
					case IS:
					case NEWLINE:
						break;
					default:
						throw new NoViableAltException(this);
				}
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IntegerAttributeContext extends ParserRuleContext {
		public TerminalNode EMPTY() {
			return getToken(TaraGrammar.EMPTY, 0);
		}

		public AttributeTypeContext attributeType() {
			return getRuleContext(AttributeTypeContext.class, 0);
		}

		public TerminalNode INT_TYPE() {
			return getToken(TaraGrammar.INT_TYPE, 0);
		}

		public IntegerValueContext integerValue(int i) {
			return getRuleContext(IntegerValueContext.class, i);
		}

		public MeasureContext measure() {
			return getRuleContext(MeasureContext.class, 0);
		}

		public TerminalNode EQUALS() {
			return getToken(TaraGrammar.EQUALS, 0);
		}

		public TerminalNode IDENTIFIER() {
			return getToken(TaraGrammar.IDENTIFIER, 0);
		}

		public TerminalNode LIST() {
			return getToken(TaraGrammar.LIST, 0);
		}

		public List<IntegerValueContext> integerValue() {
			return getRuleContexts(IntegerValueContext.class);
		}

		public IntegerAttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_integerAttribute;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).enterIntegerAttribute(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).exitIntegerAttribute(this);
		}
	}

	public final IntegerAttributeContext integerAttribute() throws RecognitionException {
		IntegerAttributeContext _localctx = new IntegerAttributeContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_integerAttribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(409);
				match(INT_TYPE);
				setState(411);
				_la = _input.LA(1);
				if (_la == COLON) {
					{
						setState(410);
						attributeType();
					}
				}

				setState(414);
				_la = _input.LA(1);
				if (_la == LIST) {
					{
						setState(413);
						match(LIST);
					}
				}

				setState(416);
				match(IDENTIFIER);
				setState(427);
				switch (_input.LA(1)) {
					case EQUALS: {
						setState(417);
						match(EQUALS);
						setState(419);
						_errHandler.sync(this);
						_la = _input.LA(1);
						do {
							{
								{
									setState(418);
									integerValue();
								}
							}
							setState(421);
							_errHandler.sync(this);
							_la = _input.LA(1);
						} while (_la == NATURAL_VALUE || _la == NEGATIVE_VALUE);
						setState(424);
						_la = _input.LA(1);
						if (((((_la - 30)) & ~0x3f) == 0 && ((1L << (_la - 30)) & ((1L << (DOLLAR - 30)) | (1L << (EURO - 30)) | (1L << (PERCENTAGE - 30)) | (1L << (GRADE - 30)) | (1L << (IDENTIFIER - 30)))) != 0)) {
							{
								setState(423);
								measure();
							}
						}

					}
					break;
					case EMPTY: {
						setState(426);
						match(EMPTY);
					}
					break;
					case IS:
					case NEWLINE:
						break;
					default:
						throw new NoViableAltException(this);
				}
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DoubleAttributeContext extends ParserRuleContext {
		public TerminalNode EMPTY() {
			return getToken(TaraGrammar.EMPTY, 0);
		}

		public AttributeTypeContext attributeType() {
			return getRuleContext(AttributeTypeContext.class, 0);
		}

		public DoubleValueContext doubleValue(int i) {
			return getRuleContext(DoubleValueContext.class, i);
		}

		public TerminalNode DOUBLE_TYPE() {
			return getToken(TaraGrammar.DOUBLE_TYPE, 0);
		}

		public MeasureContext measure() {
			return getRuleContext(MeasureContext.class, 0);
		}

		public TerminalNode EQUALS() {
			return getToken(TaraGrammar.EQUALS, 0);
		}

		public TerminalNode IDENTIFIER() {
			return getToken(TaraGrammar.IDENTIFIER, 0);
		}

		public List<DoubleValueContext> doubleValue() {
			return getRuleContexts(DoubleValueContext.class);
		}

		public TerminalNode LIST() {
			return getToken(TaraGrammar.LIST, 0);
		}

		public DoubleAttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_doubleAttribute;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).enterDoubleAttribute(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).exitDoubleAttribute(this);
		}
	}

	public final DoubleAttributeContext doubleAttribute() throws RecognitionException {
		DoubleAttributeContext _localctx = new DoubleAttributeContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_doubleAttribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(429);
				match(DOUBLE_TYPE);
				setState(431);
				_la = _input.LA(1);
				if (_la == COLON) {
					{
						setState(430);
						attributeType();
					}
				}

				setState(434);
				_la = _input.LA(1);
				if (_la == LIST) {
					{
						setState(433);
						match(LIST);
					}
				}

				setState(436);
				match(IDENTIFIER);
				setState(447);
				switch (_input.LA(1)) {
					case EQUALS: {
						setState(437);
						match(EQUALS);
						setState(439);
						_errHandler.sync(this);
						_la = _input.LA(1);
						do {
							{
								{
									setState(438);
									doubleValue();
								}
							}
							setState(441);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NATURAL_VALUE) | (1L << NEGATIVE_VALUE) | (1L << DOUBLE_VALUE))) != 0));
						setState(444);
						_la = _input.LA(1);
						if (((((_la - 30)) & ~0x3f) == 0 && ((1L << (_la - 30)) & ((1L << (DOLLAR - 30)) | (1L << (EURO - 30)) | (1L << (PERCENTAGE - 30)) | (1L << (GRADE - 30)) | (1L << (IDENTIFIER - 30)))) != 0)) {
							{
								setState(443);
								measure();
							}
						}

					}
					break;
					case EMPTY: {
						setState(446);
						match(EMPTY);
					}
					break;
					case IS:
					case NEWLINE:
						break;
					default:
						throw new NoViableAltException(this);
				}
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DateAttributeContext extends ParserRuleContext {
		public TerminalNode EMPTY() {
			return getToken(TaraGrammar.EMPTY, 0);
		}

		public TerminalNode EQUALS() {
			return getToken(TaraGrammar.EQUALS, 0);
		}

		public TerminalNode IDENTIFIER() {
			return getToken(TaraGrammar.IDENTIFIER, 0);
		}

		public TerminalNode DATE_TYPE() {
			return getToken(TaraGrammar.DATE_TYPE, 0);
		}

		public List<DateValueContext> dateValue() {
			return getRuleContexts(DateValueContext.class);
		}

		public DateValueContext dateValue(int i) {
			return getRuleContext(DateValueContext.class, i);
		}

		public TerminalNode LIST() {
			return getToken(TaraGrammar.LIST, 0);
		}

		public DateAttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_dateAttribute;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).enterDateAttribute(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).exitDateAttribute(this);
		}
	}

	public final DateAttributeContext dateAttribute() throws RecognitionException {
		DateAttributeContext _localctx = new DateAttributeContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_dateAttribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(449);
				match(DATE_TYPE);
				setState(451);
				_la = _input.LA(1);
				if (_la == LIST) {
					{
						setState(450);
						match(LIST);
					}
				}

				setState(453);
				match(IDENTIFIER);
				setState(461);
				switch (_input.LA(1)) {
					case EQUALS: {
						setState(454);
						match(EQUALS);
						setState(456);
						_errHandler.sync(this);
						_la = _input.LA(1);
						do {
							{
								{
									setState(455);
									dateValue();
								}
							}
							setState(458);
							_errHandler.sync(this);
							_la = _input.LA(1);
						} while (_la == DATE_VALUE);
					}
					break;
					case EMPTY: {
						setState(460);
						match(EMPTY);
					}
					break;
					case IS:
					case NEWLINE:
						break;
					default:
						throw new NoViableAltException(this);
				}
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CoordinateAttributeContext extends ParserRuleContext {
		public TerminalNode EMPTY() {
			return getToken(TaraGrammar.EMPTY, 0);
		}

		public TerminalNode COORDINATE_TYPE() {
			return getToken(TaraGrammar.COORDINATE_TYPE, 0);
		}

		public List<CoordinateValueContext> coordinateValue() {
			return getRuleContexts(CoordinateValueContext.class);
		}

		public TerminalNode EQUALS() {
			return getToken(TaraGrammar.EQUALS, 0);
		}

		public TerminalNode IDENTIFIER() {
			return getToken(TaraGrammar.IDENTIFIER, 0);
		}

		public CoordinateValueContext coordinateValue(int i) {
			return getRuleContext(CoordinateValueContext.class, i);
		}

		public TerminalNode LIST() {
			return getToken(TaraGrammar.LIST, 0);
		}

		public CoordinateAttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_coordinateAttribute;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener)
				((TaraGrammarListener) listener).enterCoordinateAttribute(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).exitCoordinateAttribute(this);
		}
	}

	public final CoordinateAttributeContext coordinateAttribute() throws RecognitionException {
		CoordinateAttributeContext _localctx = new CoordinateAttributeContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_coordinateAttribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(463);
				match(COORDINATE_TYPE);
				setState(465);
				_la = _input.LA(1);
				if (_la == LIST) {
					{
						setState(464);
						match(LIST);
					}
				}

				setState(467);
				match(IDENTIFIER);
				setState(477);
				_la = _input.LA(1);
				if (_la == EQUALS) {
					{
						setState(468);
						match(EQUALS);
						setState(475);
						switch (_input.LA(1)) {
							case COORDINATE_VALUE: {
								setState(470);
								_errHandler.sync(this);
								_la = _input.LA(1);
								do {
									{
										{
											setState(469);
											coordinateValue();
										}
									}
									setState(472);
									_errHandler.sync(this);
									_la = _input.LA(1);
								} while (_la == COORDINATE_VALUE);
							}
							break;
							case EMPTY: {
								setState(474);
								match(EMPTY);
							}
							break;
							default:
								throw new NoViableAltException(this);
						}
					}
				}

			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PortAttributeContext extends ParserRuleContext {
		public TerminalNode EMPTY() {
			return getToken(TaraGrammar.EMPTY, 0);
		}

		public TerminalNode EQUALS() {
			return getToken(TaraGrammar.EQUALS, 0);
		}

		public TerminalNode PORT_TYPE() {
			return getToken(TaraGrammar.PORT_TYPE, 0);
		}

		public List<PortValueContext> portValue() {
			return getRuleContexts(PortValueContext.class);
		}

		public TerminalNode IDENTIFIER() {
			return getToken(TaraGrammar.IDENTIFIER, 0);
		}

		public TerminalNode LIST() {
			return getToken(TaraGrammar.LIST, 0);
		}

		public PortValueContext portValue(int i) {
			return getRuleContext(PortValueContext.class, i);
		}

		public PortAttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_portAttribute;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).enterPortAttribute(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).exitPortAttribute(this);
		}
	}

	public final PortAttributeContext portAttribute() throws RecognitionException {
		PortAttributeContext _localctx = new PortAttributeContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_portAttribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(479);
				match(PORT_TYPE);
				setState(481);
				_la = _input.LA(1);
				if (_la == LIST) {
					{
						setState(480);
						match(LIST);
					}
				}

				setState(483);
				match(IDENTIFIER);
				setState(493);
				_la = _input.LA(1);
				if (_la == EQUALS) {
					{
						setState(484);
						match(EQUALS);
						setState(491);
						switch (_input.LA(1)) {
							case PORT_VALUE: {
								setState(486);
								_errHandler.sync(this);
								_la = _input.LA(1);
								do {
									{
										{
											setState(485);
											portValue();
										}
									}
									setState(488);
									_errHandler.sync(this);
									_la = _input.LA(1);
								} while (_la == PORT_VALUE);
							}
							break;
							case EMPTY: {
								setState(490);
								match(EMPTY);
							}
							break;
							default:
								throw new NoViableAltException(this);
						}
					}
				}

			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AttributeTypeContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() {
			return getToken(TaraGrammar.IDENTIFIER, 0);
		}

		public TerminalNode COLON() {
			return getToken(TaraGrammar.COLON, 0);
		}

		public AttributeTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_attributeType;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).enterAttributeType(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).exitAttributeType(this);
		}
	}

	public final AttributeTypeContext attributeType() throws RecognitionException {
		AttributeTypeContext _localctx = new AttributeTypeContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_attributeType);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(495);
				match(COLON);
				setState(496);
				match(IDENTIFIER);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NaturalValueContext extends ParserRuleContext {
		public TerminalNode NATURAL_VALUE() {
			return getToken(TaraGrammar.NATURAL_VALUE, 0);
		}

		public NaturalValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_naturalValue;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).enterNaturalValue(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).exitNaturalValue(this);
		}
	}

	public final NaturalValueContext naturalValue() throws RecognitionException {
		NaturalValueContext _localctx = new NaturalValueContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_naturalValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(498);
				match(NATURAL_VALUE);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IntegerValueContext extends ParserRuleContext {
		public TerminalNode NATURAL_VALUE() {
			return getToken(TaraGrammar.NATURAL_VALUE, 0);
		}

		public TerminalNode NEGATIVE_VALUE() {
			return getToken(TaraGrammar.NEGATIVE_VALUE, 0);
		}

		public IntegerValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_integerValue;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).enterIntegerValue(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).exitIntegerValue(this);
		}
	}

	public final IntegerValueContext integerValue() throws RecognitionException {
		IntegerValueContext _localctx = new IntegerValueContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_integerValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(500);
				_la = _input.LA(1);
				if (!(_la == NATURAL_VALUE || _la == NEGATIVE_VALUE)) {
					_errHandler.recoverInline(this);
				}
				consume();
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DoubleValueContext extends ParserRuleContext {
		public TerminalNode DOUBLE_VALUE() {
			return getToken(TaraGrammar.DOUBLE_VALUE, 0);
		}

		public TerminalNode NATURAL_VALUE() {
			return getToken(TaraGrammar.NATURAL_VALUE, 0);
		}

		public TerminalNode NEGATIVE_VALUE() {
			return getToken(TaraGrammar.NEGATIVE_VALUE, 0);
		}

		public DoubleValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_doubleValue;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).enterDoubleValue(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).exitDoubleValue(this);
		}
	}

	public final DoubleValueContext doubleValue() throws RecognitionException {
		DoubleValueContext _localctx = new DoubleValueContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_doubleValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(502);
				_la = _input.LA(1);
				if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NATURAL_VALUE) | (1L << NEGATIVE_VALUE) | (1L << DOUBLE_VALUE))) != 0))) {
					_errHandler.recoverInline(this);
				}
				consume();
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BooleanValueContext extends ParserRuleContext {
		public TerminalNode BOOLEAN_VALUE() {
			return getToken(TaraGrammar.BOOLEAN_VALUE, 0);
		}

		public BooleanValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_booleanValue;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).enterBooleanValue(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).exitBooleanValue(this);
		}
	}

	public final BooleanValueContext booleanValue() throws RecognitionException {
		BooleanValueContext _localctx = new BooleanValueContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_booleanValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(504);
				match(BOOLEAN_VALUE);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StringValueContext extends ParserRuleContext {
		public TerminalNode STRING_VALUE() {
			return getToken(TaraGrammar.STRING_VALUE, 0);
		}

		public TerminalNode STRING_MULTILINE_VALUE_KEY() {
			return getToken(TaraGrammar.STRING_MULTILINE_VALUE_KEY, 0);
		}

		public StringValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_stringValue;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).enterStringValue(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).exitStringValue(this);
		}
	}

	public final StringValueContext stringValue() throws RecognitionException {
		StringValueContext _localctx = new StringValueContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_stringValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(506);
				_la = _input.LA(1);
				if (!(_la == STRING_VALUE || _la == STRING_MULTILINE_VALUE_KEY)) {
					_errHandler.recoverInline(this);
				}
				consume();
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PortValueContext extends ParserRuleContext {
		public TerminalNode PORT_VALUE() {
			return getToken(TaraGrammar.PORT_VALUE, 0);
		}

		public PortValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_portValue;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).enterPortValue(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).exitPortValue(this);
		}
	}

	public final PortValueContext portValue() throws RecognitionException {
		PortValueContext _localctx = new PortValueContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_portValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(508);
				match(PORT_VALUE);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DateValueContext extends ParserRuleContext {
		public TerminalNode DATE_VALUE() {
			return getToken(TaraGrammar.DATE_VALUE, 0);
		}

		public DateValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_dateValue;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).enterDateValue(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).exitDateValue(this);
		}
	}

	public final DateValueContext dateValue() throws RecognitionException {
		DateValueContext _localctx = new DateValueContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_dateValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(510);
				match(DATE_VALUE);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CoordinateValueContext extends ParserRuleContext {
		public TerminalNode COORDINATE_VALUE() {
			return getToken(TaraGrammar.COORDINATE_VALUE, 0);
		}

		public CoordinateValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_coordinateValue;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).enterCoordinateValue(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).exitCoordinateValue(this);
		}
	}

	public final CoordinateValueContext coordinateValue() throws RecognitionException {
		CoordinateValueContext _localctx = new CoordinateValueContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_coordinateValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(512);
				match(COORDINATE_VALUE);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MeasureContext extends ParserRuleContext {
		public TerminalNode GRADE() {
			return getToken(TaraGrammar.GRADE, 0);
		}

		public TerminalNode EURO() {
			return getToken(TaraGrammar.EURO, 0);
		}

		public TerminalNode PERCENTAGE() {
			return getToken(TaraGrammar.PERCENTAGE, 0);
		}

		public TerminalNode IDENTIFIER() {
			return getToken(TaraGrammar.IDENTIFIER, 0);
		}

		public TerminalNode DOLLAR() {
			return getToken(TaraGrammar.DOLLAR, 0);
		}

		public MeasureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_measure;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).enterMeasure(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).exitMeasure(this);
		}
	}

	public final MeasureContext measure() throws RecognitionException {
		MeasureContext _localctx = new MeasureContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_measure);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(514);
				_la = _input.LA(1);
				if (!(((((_la - 30)) & ~0x3f) == 0 && ((1L << (_la - 30)) & ((1L << (DOLLAR - 30)) | (1L << (EURO - 30)) | (1L << (PERCENTAGE - 30)) | (1L << (GRADE - 30)) | (1L << (IDENTIFIER - 30)))) != 0))) {
					_errHandler.recoverInline(this);
				}
				consume();
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AnnotationsContext extends ParserRuleContext {
		public List<TerminalNode> FACET() {
			return getTokens(TaraGrammar.FACET);
		}

		public List<TerminalNode> UNIVERSAL() {
			return getTokens(TaraGrammar.UNIVERSAL);
		}

		public List<TerminalNode> NAMED() {
			return getTokens(TaraGrammar.NAMED);
		}

		public TerminalNode PROPERTY(int i) {
			return getToken(TaraGrammar.PROPERTY, i);
		}

		public List<TerminalNode> PRIVATE() {
			return getTokens(TaraGrammar.PRIVATE);
		}

		public TerminalNode FACET(int i) {
			return getToken(TaraGrammar.FACET, i);
		}

		public TerminalNode IS() {
			return getToken(TaraGrammar.IS, 0);
		}

		public List<TerminalNode> SINGLE() {
			return getTokens(TaraGrammar.SINGLE);
		}

		public TerminalNode TERMINAL(int i) {
			return getToken(TaraGrammar.TERMINAL, i);
		}

		public TerminalNode UNIVERSAL(int i) {
			return getToken(TaraGrammar.UNIVERSAL, i);
		}

		public List<TerminalNode> TERMINAL() {
			return getTokens(TaraGrammar.TERMINAL);
		}

		public List<TerminalNode> REQUIRED() {
			return getTokens(TaraGrammar.REQUIRED);
		}

		public TerminalNode NAMED(int i) {
			return getToken(TaraGrammar.NAMED, i);
		}

		public List<TerminalNode> PROPERTY() {
			return getTokens(TaraGrammar.PROPERTY);
		}

		public TerminalNode INTENTION(int i) {
			return getToken(TaraGrammar.INTENTION, i);
		}

		public TerminalNode REQUIRED(int i) {
			return getToken(TaraGrammar.REQUIRED, i);
		}

		public List<TerminalNode> INTENTION() {
			return getTokens(TaraGrammar.INTENTION);
		}

		public TerminalNode SINGLE(int i) {
			return getToken(TaraGrammar.SINGLE, i);
		}

		public TerminalNode COMPONENT(int i) {
			return getToken(TaraGrammar.COMPONENT, i);
		}

		public List<TerminalNode> COMPONENT() {
			return getTokens(TaraGrammar.COMPONENT);
		}

		public TerminalNode PRIVATE(int i) {
			return getToken(TaraGrammar.PRIVATE, i);
		}

		public AnnotationsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_annotations;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).enterAnnotations(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).exitAnnotations(this);
		}
	}

	public final AnnotationsContext annotations() throws RecognitionException {
		AnnotationsContext _localctx = new AnnotationsContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_annotations);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
				setState(516);
				match(IS);
				setState(518);
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
						case 1: {
							{
								setState(517);
								_la = _input.LA(1);
								if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PRIVATE) | (1L << SINGLE) | (1L << REQUIRED) | (1L << COMPONENT) | (1L << FACET) | (1L << INTENTION) | (1L << TERMINAL) | (1L << NAMED) | (1L << PROPERTY) | (1L << UNIVERSAL))) != 0))) {
									_errHandler.recoverInline(this);
								}
								consume();
							}
						}
						break;
						default:
							throw new NoViableAltException(this);
					}
					setState(520);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 83, _ctx);
				} while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarInitContext extends ParserRuleContext {
		public List<BooleanValueContext> booleanValue() {
			return getRuleContexts(BooleanValueContext.class);
		}

		public BooleanValueContext booleanValue(int i) {
			return getRuleContext(BooleanValueContext.class, i);
		}

		public TerminalNode EQUALS() {
			return getToken(TaraGrammar.EQUALS, 0);
		}

		public List<DoubleValueContext> doubleValue() {
			return getRuleContexts(DoubleValueContext.class);
		}

		public List<StringValueContext> stringValue() {
			return getRuleContexts(StringValueContext.class);
		}

		public List<DateValueContext> dateValue() {
			return getRuleContexts(DateValueContext.class);
		}

		public DateValueContext dateValue(int i) {
			return getRuleContext(DateValueContext.class, i);
		}

		public PortValueContext portValue(int i) {
			return getRuleContext(PortValueContext.class, i);
		}

		public TerminalNode EMPTY() {
			return getToken(TaraGrammar.EMPTY, 0);
		}

		public DoubleValueContext doubleValue(int i) {
			return getRuleContext(DoubleValueContext.class, i);
		}

		public List<IdentifierReferenceContext> identifierReference() {
			return getRuleContexts(IdentifierReferenceContext.class);
		}

		public IntegerValueContext integerValue(int i) {
			return getRuleContext(IntegerValueContext.class, i);
		}

		public MeasureContext measure() {
			return getRuleContext(MeasureContext.class, 0);
		}

		public List<NaturalValueContext> naturalValue() {
			return getRuleContexts(NaturalValueContext.class);
		}

		public List<CoordinateValueContext> coordinateValue() {
			return getRuleContexts(CoordinateValueContext.class);
		}

		public TerminalNode IDENTIFIER() {
			return getToken(TaraGrammar.IDENTIFIER, 0);
		}

		public List<PortValueContext> portValue() {
			return getRuleContexts(PortValueContext.class);
		}

		public NaturalValueContext naturalValue(int i) {
			return getRuleContext(NaturalValueContext.class, i);
		}

		public CoordinateValueContext coordinateValue(int i) {
			return getRuleContext(CoordinateValueContext.class, i);
		}

		public IdentifierReferenceContext identifierReference(int i) {
			return getRuleContext(IdentifierReferenceContext.class, i);
		}

		public StringValueContext stringValue(int i) {
			return getRuleContext(StringValueContext.class, i);
		}

		public List<IntegerValueContext> integerValue() {
			return getRuleContexts(IntegerValueContext.class);
		}

		public VarInitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_varInit;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).enterVarInit(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).exitVarInit(this);
		}
	}

	public final VarInitContext varInit() throws RecognitionException {
		VarInitContext _localctx = new VarInitContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_varInit);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(522);
				match(IDENTIFIER);
				setState(523);
				match(EQUALS);
				setState(579);
				switch (getInterpreter().adaptivePredict(_input, 96, _ctx)) {
					case 1: {
						setState(524);
						match(EMPTY);
					}
					break;
					case 2: {
						setState(526);
						_errHandler.sync(this);
						_la = _input.LA(1);
						do {
							{
								{
									setState(525);
									identifierReference();
								}
							}
							setState(528);
							_errHandler.sync(this);
							_la = _input.LA(1);
						} while (_la == IDENTIFIER);
					}
					break;
					case 3: {
						setState(531);
						_errHandler.sync(this);
						_la = _input.LA(1);
						do {
							{
								{
									setState(530);
									stringValue();
								}
							}
							setState(533);
							_errHandler.sync(this);
							_la = _input.LA(1);
						} while (_la == STRING_VALUE || _la == STRING_MULTILINE_VALUE_KEY);
					}
					break;
					case 4: {
						setState(536);
						_errHandler.sync(this);
						_la = _input.LA(1);
						do {
							{
								{
									setState(535);
									booleanValue();
								}
							}
							setState(538);
							_errHandler.sync(this);
							_la = _input.LA(1);
						} while (_la == BOOLEAN_VALUE);
					}
					break;
					case 5: {
						setState(541);
						_errHandler.sync(this);
						_la = _input.LA(1);
						do {
							{
								{
									setState(540);
									dateValue();
								}
							}
							setState(543);
							_errHandler.sync(this);
							_la = _input.LA(1);
						} while (_la == DATE_VALUE);
					}
					break;
					case 6: {
						setState(546);
						_errHandler.sync(this);
						_la = _input.LA(1);
						do {
							{
								{
									setState(545);
									portValue();
								}
							}
							setState(548);
							_errHandler.sync(this);
							_la = _input.LA(1);
						} while (_la == PORT_VALUE);
					}
					break;
					case 7: {
						setState(551);
						_errHandler.sync(this);
						_la = _input.LA(1);
						do {
							{
								{
									setState(550);
									coordinateValue();
								}
							}
							setState(553);
							_errHandler.sync(this);
							_la = _input.LA(1);
						} while (_la == COORDINATE_VALUE);
					}
					break;
					case 8: {
						setState(556);
						_errHandler.sync(this);
						_la = _input.LA(1);
						do {
							{
								{
									setState(555);
									naturalValue();
								}
							}
							setState(558);
							_errHandler.sync(this);
							_la = _input.LA(1);
						} while (_la == NATURAL_VALUE);
						setState(561);
						_la = _input.LA(1);
						if (((((_la - 30)) & ~0x3f) == 0 && ((1L << (_la - 30)) & ((1L << (DOLLAR - 30)) | (1L << (EURO - 30)) | (1L << (PERCENTAGE - 30)) | (1L << (GRADE - 30)) | (1L << (IDENTIFIER - 30)))) != 0)) {
							{
								setState(560);
								measure();
							}
						}

					}
					break;
					case 9: {
						setState(564);
						_errHandler.sync(this);
						_la = _input.LA(1);
						do {
							{
								{
									setState(563);
									integerValue();
								}
							}
							setState(566);
							_errHandler.sync(this);
							_la = _input.LA(1);
						} while (_la == NATURAL_VALUE || _la == NEGATIVE_VALUE);
						setState(569);
						_la = _input.LA(1);
						if (((((_la - 30)) & ~0x3f) == 0 && ((1L << (_la - 30)) & ((1L << (DOLLAR - 30)) | (1L << (EURO - 30)) | (1L << (PERCENTAGE - 30)) | (1L << (GRADE - 30)) | (1L << (IDENTIFIER - 30)))) != 0)) {
							{
								setState(568);
								measure();
							}
						}

					}
					break;
					case 10: {
						setState(572);
						_errHandler.sync(this);
						_la = _input.LA(1);
						do {
							{
								{
									setState(571);
									doubleValue();
								}
							}
							setState(574);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NATURAL_VALUE) | (1L << NEGATIVE_VALUE) | (1L << DOUBLE_VALUE))) != 0));
						setState(577);
						_la = _input.LA(1);
						if (((((_la - 30)) & ~0x3f) == 0 && ((1L << (_la - 30)) & ((1L << (DOLLAR - 30)) | (1L << (EURO - 30)) | (1L << (PERCENTAGE - 30)) | (1L << (GRADE - 30)) | (1L << (IDENTIFIER - 30)))) != 0)) {
							{
								setState(576);
								measure();
							}
						}

					}
					break;
				}
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class HeaderReferenceContext extends ParserRuleContext {
		public List<HierarchyContext> hierarchy() {
			return getRuleContexts(HierarchyContext.class);
		}

		public TerminalNode IDENTIFIER() {
			return getToken(TaraGrammar.IDENTIFIER, 0);
		}

		public HierarchyContext hierarchy(int i) {
			return getRuleContext(HierarchyContext.class, i);
		}

		public HeaderReferenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_headerReference;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).enterHeaderReference(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).exitHeaderReference(this);
		}
	}

	public final HeaderReferenceContext headerReference() throws RecognitionException {
		HeaderReferenceContext _localctx = new HeaderReferenceContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_headerReference);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
				setState(584);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 97, _ctx);
				while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1) {
						{
							{
								setState(581);
								hierarchy();
							}
						}
					}
					setState(586);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 97, _ctx);
				}
				setState(587);
				match(IDENTIFIER);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdentifierReferenceContext extends ParserRuleContext {
		public List<HierarchyContext> hierarchy() {
			return getRuleContexts(HierarchyContext.class);
		}

		public TerminalNode IDENTIFIER() {
			return getToken(TaraGrammar.IDENTIFIER, 0);
		}

		public HierarchyContext hierarchy(int i) {
			return getRuleContext(HierarchyContext.class, i);
		}

		public IdentifierReferenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_identifierReference;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener)
				((TaraGrammarListener) listener).enterIdentifierReference(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).exitIdentifierReference(this);
		}
	}

	public final IdentifierReferenceContext identifierReference() throws RecognitionException {
		IdentifierReferenceContext _localctx = new IdentifierReferenceContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_identifierReference);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
				setState(592);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 98, _ctx);
				while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1) {
						{
							{
								setState(589);
								hierarchy();
							}
						}
					}
					setState(594);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 98, _ctx);
				}
				setState(595);
				match(IDENTIFIER);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class HierarchyContext extends ParserRuleContext {
		public TerminalNode DOT() {
			return getToken(TaraGrammar.DOT, 0);
		}

		public TerminalNode IDENTIFIER() {
			return getToken(TaraGrammar.IDENTIFIER, 0);
		}

		public HierarchyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_hierarchy;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).enterHierarchy(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).exitHierarchy(this);
		}
	}

	public final HierarchyContext hierarchy() throws RecognitionException {
		HierarchyContext _localctx = new HierarchyContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_hierarchy);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(597);
				match(IDENTIFIER);
				setState(598);
				match(DOT);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MetaidentifierContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() {
			return getToken(TaraGrammar.IDENTIFIER, 0);
		}

		public TerminalNode INTENTION() {
			return getToken(TaraGrammar.INTENTION, 0);
		}

		public TerminalNode METAIDENTIFIER() {
			return getToken(TaraGrammar.METAIDENTIFIER, 0);
		}

		public MetaidentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_metaidentifier;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).enterMetaidentifier(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraGrammarListener) ((TaraGrammarListener) listener).exitMetaidentifier(this);
		}
	}

	public final MetaidentifierContext metaidentifier() throws RecognitionException {
		MetaidentifierContext _localctx = new MetaidentifierContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_metaidentifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(600);
				_la = _input.LA(1);
				if (!(_la == METAIDENTIFIER || _la == INTENTION || _la == IDENTIFIER)) {
					_errHandler.recoverInline(this);
				}
				consume();
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3N\u025d\4\2\t\2\4" +
			"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t" +
			"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22" +
			"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31" +
			"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!" +
			"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4" +
			",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\3\2\7\2b\n\2\f\2\16\2e\13\2\3\2\5\2h" +
			"\n\2\3\2\6\2k\n\2\r\2\16\2l\3\2\3\2\7\2q\n\2\f\2\16\2t\13\2\7\2v\n\2\f" +
			"\2\16\2y\13\2\3\2\3\2\3\3\5\3~\n\3\3\3\5\3\u0081\n\3\3\4\3\4\3\4\3\5\6" +
			"\5\u0087\n\5\r\5\16\5\u0088\3\6\6\6\u008c\n\6\r\6\16\6\u008d\3\6\3\6\3" +
			"\6\3\6\5\6\u0094\n\6\3\7\6\7\u0097\n\7\r\7\16\7\u0098\3\b\5\b\u009c\n" +
			"\b\3\b\3\b\5\b\u00a0\n\b\3\b\5\b\u00a3\n\b\3\t\3\t\3\t\3\t\5\t\u00a9\n" +
			"\t\3\t\3\t\3\t\5\t\u00ae\n\t\3\t\3\t\5\t\u00b2\n\t\5\t\u00b4\n\t\3\n\3" +
			"\n\5\n\u00b8\n\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\7\13\u00c2\n\13" +
			"\f\13\16\13\u00c5\13\13\3\13\6\13\u00c8\n\13\r\13\16\13\u00c9\5\13\u00cc" +
			"\n\13\3\f\3\f\3\f\3\r\3\r\6\r\u00d3\n\r\r\r\16\r\u00d4\3\r\6\r\u00d8\n" +
			"\r\r\r\16\r\u00d9\3\r\6\r\u00dd\n\r\r\r\16\r\u00de\3\r\5\r\u00e2\n\r\3" +
			"\r\6\r\u00e5\n\r\r\r\16\r\u00e6\3\r\5\r\u00ea\n\r\3\r\6\r\u00ed\n\r\r" +
			"\r\16\r\u00ee\3\r\5\r\u00f2\n\r\3\r\6\r\u00f5\n\r\r\r\16\r\u00f6\3\r\6" +
			"\r\u00fa\n\r\r\r\16\r\u00fb\3\r\5\r\u00ff\n\r\3\16\3\16\7\16\u0103\n\16" +
			"\f\16\16\16\u0106\13\16\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3" +
			"\20\5\20\u0112\n\20\3\20\6\20\u0115\n\20\r\20\16\20\u0116\6\20\u0119\n" +
			"\20\r\20\16\20\u011a\3\20\3\20\3\21\5\21\u0120\n\21\3\21\3\21\3\21\5\21" +
			"\u0125\n\21\3\22\3\22\3\22\5\22\u012a\n\22\3\22\3\22\5\22\u012e\n\22\3" +
			"\22\5\22\u0131\n\22\3\23\3\23\3\23\5\23\u0136\n\23\3\23\5\23\u0139\n\23" +
			"\3\24\5\24\u013c\n\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24" +
			"\3\24\3\24\5\24\u014a\n\24\3\24\5\24\u014d\n\24\3\25\3\25\3\25\3\25\3" +
			"\26\3\26\3\26\3\26\3\26\3\26\6\26\u0159\n\26\r\26\16\26\u015a\3\26\3\26" +
			"\3\27\3\27\5\27\u0161\n\27\3\30\3\30\5\30\u0165\n\30\3\30\3\30\3\30\5" +
			"\30\u016a\n\30\3\31\3\31\5\31\u016e\n\31\3\31\3\31\3\31\6\31\u0173\n\31" +
			"\r\31\16\31\u0174\3\31\5\31\u0178\n\31\3\32\3\32\5\32\u017c\n\32\3\32" +
			"\3\32\3\32\6\32\u0181\n\32\r\32\16\32\u0182\3\32\5\32\u0186\n\32\3\33" +
			"\3\33\5\33\u018a\n\33\3\33\5\33\u018d\n\33\3\33\3\33\3\33\6\33\u0192\n" +
			"\33\r\33\16\33\u0193\3\33\5\33\u0197\n\33\3\33\5\33\u019a\n\33\3\34\3" +
			"\34\5\34\u019e\n\34\3\34\5\34\u01a1\n\34\3\34\3\34\3\34\6\34\u01a6\n\34" +
			"\r\34\16\34\u01a7\3\34\5\34\u01ab\n\34\3\34\5\34\u01ae\n\34\3\35\3\35" +
			"\5\35\u01b2\n\35\3\35\5\35\u01b5\n\35\3\35\3\35\3\35\6\35\u01ba\n\35\r" +
			"\35\16\35\u01bb\3\35\5\35\u01bf\n\35\3\35\5\35\u01c2\n\35\3\36\3\36\5" +
			"\36\u01c6\n\36\3\36\3\36\3\36\6\36\u01cb\n\36\r\36\16\36\u01cc\3\36\5" +
			"\36\u01d0\n\36\3\37\3\37\5\37\u01d4\n\37\3\37\3\37\3\37\6\37\u01d9\n\37" +
			"\r\37\16\37\u01da\3\37\5\37\u01de\n\37\5\37\u01e0\n\37\3 \3 \5 \u01e4" +
			"\n \3 \3 \3 \6 \u01e9\n \r \16 \u01ea\3 \5 \u01ee\n \5 \u01f0\n \3!\3" +
			"!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\3&\3&\3\'\3\'\3(\3(\3)\3)\3*\3*\3+\3+\6" +
			"+\u0209\n+\r+\16+\u020a\3,\3,\3,\3,\6,\u0211\n,\r,\16,\u0212\3,\6,\u0216" +
			"\n,\r,\16,\u0217\3,\6,\u021b\n,\r,\16,\u021c\3,\6,\u0220\n,\r,\16,\u0221" +
			"\3,\6,\u0225\n,\r,\16,\u0226\3,\6,\u022a\n,\r,\16,\u022b\3,\6,\u022f\n" +
			",\r,\16,\u0230\3,\5,\u0234\n,\3,\6,\u0237\n,\r,\16,\u0238\3,\5,\u023c" +
			"\n,\3,\6,\u023f\n,\r,\16,\u0240\3,\5,\u0244\n,\5,\u0246\n,\3-\7-\u0249" +
			"\n-\f-\16-\u024c\13-\3-\3-\3.\7.\u0251\n.\f.\16.\u0254\13.\3.\3.\3/\3" +
			"/\3/\3\60\3\60\3\60\2\2\61\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$" +
			"&(*,.\60\62\64\668:<>@BDFHJLNPRTVXZ\\^\2\b\3\2<=\3\2<>\3\2?@\4\2 #DD\3" +
			"\2\r\26\5\2\3\3\22\22DD\u02b3\2c\3\2\2\2\4}\3\2\2\2\6\u0082\3\2\2\2\b" +
			"\u0086\3\2\2\2\n\u008b\3\2\2\2\f\u0096\3\2\2\2\16\u009b\3\2\2\2\20\u00b3" +
			"\3\2\2\2\22\u00b5\3\2\2\2\24\u00cb\3\2\2\2\26\u00cd\3\2\2\2\30\u00fe\3" +
			"\2\2\2\32\u0100\3\2\2\2\34\u0107\3\2\2\2\36\u010a\3\2\2\2 \u011f\3\2\2" +
			"\2\"\u0126\3\2\2\2$\u0132\3\2\2\2&\u013b\3\2\2\2(\u014e\3\2\2\2*\u0152" +
			"\3\2\2\2,\u015e\3\2\2\2.\u0162\3\2\2\2\60\u016b\3\2\2\2\62\u0179\3\2\2" +
			"\2\64\u0187\3\2\2\2\66\u019b\3\2\2\28\u01af\3\2\2\2:\u01c3\3\2\2\2<\u01d1" +
			"\3\2\2\2>\u01e1\3\2\2\2@\u01f1\3\2\2\2B\u01f4\3\2\2\2D\u01f6\3\2\2\2F" +
			"\u01f8\3\2\2\2H\u01fa\3\2\2\2J\u01fc\3\2\2\2L\u01fe\3\2\2\2N\u0200\3\2" +
			"\2\2P\u0202\3\2\2\2R\u0204\3\2\2\2T\u0206\3\2\2\2V\u020c\3\2\2\2X\u024a" +
			"\3\2\2\2Z\u0252\3\2\2\2\\\u0257\3\2\2\2^\u025a\3\2\2\2`b\7G\2\2a`\3\2" +
			"\2\2be\3\2\2\2ca\3\2\2\2cd\3\2\2\2dg\3\2\2\2ec\3\2\2\2fh\5\4\3\2gf\3\2" +
			"\2\2gh\3\2\2\2hj\3\2\2\2ik\7G\2\2ji\3\2\2\2kl\3\2\2\2lj\3\2\2\2lm\3\2" +
			"\2\2mw\3\2\2\2nr\5\16\b\2oq\7G\2\2po\3\2\2\2qt\3\2\2\2rp\3\2\2\2rs\3\2" +
			"\2\2sv\3\2\2\2tr\3\2\2\2un\3\2\2\2vy\3\2\2\2wu\3\2\2\2wx\3\2\2\2xz\3\2" +
			"\2\2yw\3\2\2\2z{\7\2\2\3{\3\3\2\2\2|~\5\6\4\2}|\3\2\2\2}~\3\2\2\2~\u0080" +
			"\3\2\2\2\177\u0081\5\b\5\2\u0080\177\3\2\2\2\u0080\u0081\3\2\2\2\u0081" +
			"\5\3\2\2\2\u0082\u0083\7\6\2\2\u0083\u0084\5X-\2\u0084\7\3\2\2\2\u0085" +
			"\u0087\5\n\6\2\u0086\u0085\3\2\2\2\u0087\u0088\3\2\2\2\u0088\u0086\3\2" +
			"\2\2\u0088\u0089\3\2\2\2\u0089\t\3\2\2\2\u008a\u008c\7G\2\2\u008b\u008a" +
			"\3\2\2\2\u008c\u008d\3\2\2\2\u008d\u008b\3\2\2\2\u008d\u008e\3\2\2\2\u008e" +
			"\u008f\3\2\2\2\u008f\u0090\7\5\2\2\u0090\u0093\5X-\2\u0091\u0092\7\7\2" +
			"\2\u0092\u0094\7D\2\2\u0093\u0091\3\2\2\2\u0093\u0094\3\2\2\2\u0094\13" +
			"\3\2\2\2\u0095\u0097\7I\2\2\u0096\u0095\3\2\2\2\u0097\u0098\3\2\2\2\u0098" +
			"\u0096\3\2\2\2\u0098\u0099\3\2\2\2\u0099\r\3\2\2\2\u009a\u009c\5\f\7\2" +
			"\u009b\u009a\3\2\2\2\u009b\u009c\3\2\2\2\u009c\u009d\3\2\2\2\u009d\u009f" +
			"\5\20\t\2\u009e\u00a0\5T+\2\u009f\u009e\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0" +
			"\u00a2\3\2\2\2\u00a1\u00a3\5\36\20\2\u00a2\u00a1\3\2\2\2\u00a2\u00a3\3" +
			"\2\2\2\u00a3\17\3\2\2\2\u00a4\u00a5\7\4\2\2\u00a5\u00b4\7D\2\2\u00a6\u00a8" +
			"\5^\60\2\u00a7\u00a9\5\22\n\2\u00a8\u00a7\3\2\2\2\u00a8\u00a9\3\2\2\2" +
			"\u00a9\u00aa\3\2\2\2\u00aa\u00ad\7D\2\2\u00ab\u00ac\7\f\2\2\u00ac\u00ae" +
			"\5Z.\2\u00ad\u00ab\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae\u00b4\3\2\2\2\u00af" +
			"\u00b1\5^\60\2\u00b0\u00b2\5\22\n\2\u00b1\u00b0\3\2\2\2\u00b1\u00b2\3" +
			"\2\2\2\u00b2\u00b4\3\2\2\2\u00b3\u00a4\3\2\2\2\u00b3\u00a6\3\2\2\2\u00b3" +
			"\u00af\3\2\2\2\u00b4\21\3\2\2\2\u00b5\u00b7\7\30\2\2\u00b6\u00b8\5\24" +
			"\13\2\u00b7\u00b6\3\2\2\2\u00b7\u00b8\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9" +
			"\u00ba\7\31\2\2\u00ba\23\3\2\2\2\u00bb\u00bc\5\26\f\2\u00bc\u00c3\5\30" +
			"\r\2\u00bd\u00be\7%\2\2\u00be\u00bf\5\26\f\2\u00bf\u00c0\5\30\r\2\u00c0" +
			"\u00c2\3\2\2\2\u00c1\u00bd\3\2\2\2\u00c2\u00c5\3\2\2\2\u00c3\u00c1\3\2" +
			"\2\2\u00c3\u00c4\3\2\2\2\u00c4\u00cc\3\2\2\2\u00c5\u00c3\3\2\2\2\u00c6" +
			"\u00c8\5\30\r\2\u00c7\u00c6\3\2\2\2\u00c8\u00c9\3\2\2\2\u00c9\u00c7\3" +
			"\2\2\2\u00c9\u00ca\3\2\2\2\u00ca\u00cc\3\2\2\2\u00cb\u00bb\3\2\2\2\u00cb" +
			"\u00c7\3\2\2\2\u00cc\25\3\2\2\2\u00cd\u00ce\7D\2\2\u00ce\u00cf\7\'\2\2" +
			"\u00cf\27\3\2\2\2\u00d0\u00ff\5Z.\2\u00d1\u00d3\5J&\2\u00d2\u00d1\3\2" +
			"\2\2\u00d3\u00d4\3\2\2\2\u00d4\u00d2\3\2\2\2\u00d4\u00d5\3\2\2\2\u00d5" +
			"\u00ff\3\2\2\2\u00d6\u00d8\5H%\2\u00d7\u00d6\3\2\2\2\u00d8\u00d9\3\2\2" +
			"\2\u00d9\u00d7\3\2\2\2\u00d9\u00da\3\2\2\2\u00da\u00ff\3\2\2\2\u00db\u00dd" +
			"\5B\"\2\u00dc\u00db\3\2\2\2\u00dd\u00de\3\2\2\2\u00de\u00dc\3\2\2\2\u00de" +
			"\u00df\3\2\2\2\u00df\u00e1\3\2\2\2\u00e0\u00e2\5R*\2\u00e1\u00e0\3\2\2" +
			"\2\u00e1\u00e2\3\2\2\2\u00e2\u00ff\3\2\2\2\u00e3\u00e5\5D#\2\u00e4\u00e3" +
			"\3\2\2\2\u00e5\u00e6\3\2\2\2\u00e6\u00e4\3\2\2\2\u00e6\u00e7\3\2\2\2\u00e7" +
			"\u00e9\3\2\2\2\u00e8\u00ea\5R*\2\u00e9\u00e8\3\2\2\2\u00e9\u00ea\3\2\2" +
			"\2\u00ea\u00ff\3\2\2\2\u00eb\u00ed\5F$\2\u00ec\u00eb\3\2\2\2\u00ed\u00ee" +
			"\3\2\2\2\u00ee\u00ec\3\2\2\2\u00ee\u00ef\3\2\2\2\u00ef\u00f1\3\2\2\2\u00f0" +
			"\u00f2\5R*\2\u00f1\u00f0\3\2\2\2\u00f1\u00f2\3\2\2\2\u00f2\u00ff\3\2\2" +
			"\2\u00f3\u00f5\5N(\2\u00f4\u00f3\3\2\2\2\u00f5\u00f6\3\2\2\2\u00f6\u00f4" +
			"\3\2\2\2\u00f6\u00f7\3\2\2\2\u00f7\u00ff\3\2\2\2\u00f8\u00fa\5L\'\2\u00f9" +
			"\u00f8\3\2\2\2\u00fa\u00fb\3\2\2\2\u00fb\u00f9\3\2\2\2\u00fb\u00fc\3\2" +
			"\2\2\u00fc\u00ff\3\2\2\2\u00fd\u00ff\5\32\16\2\u00fe\u00d0\3\2\2\2\u00fe" +
			"\u00d2\3\2\2\2\u00fe\u00d7\3\2\2\2\u00fe\u00dc\3\2\2\2\u00fe\u00e4\3\2" +
			"\2\2\u00fe\u00ec\3\2\2\2\u00fe\u00f4\3\2\2\2\u00fe\u00f9\3\2\2\2\u00fe" +
			"\u00fd\3\2\2\2\u00ff\31\3\2\2\2\u0100\u0104\5^\60\2\u0101\u0103\5\34\17" +
			"\2\u0102\u0101\3\2\2\2\u0103\u0106\3\2\2\2\u0104\u0102\3\2\2\2\u0104\u0105" +
			"\3\2\2\2\u0105\33\3\2\2\2\u0106\u0104\3\2\2\2\u0107\u0108\7&\2\2\u0108" +
			"\u0109\7D\2\2\u0109\35\3\2\2\2\u010a\u0118\7L\2\2\u010b\u0112\5&\24\2" +
			"\u010c\u0112\5\16\b\2\u010d\u0112\5V,\2\u010e\u0112\5\"\22\2\u010f\u0112" +
			"\5$\23\2\u0110\u0112\5 \21\2\u0111\u010b\3\2\2\2\u0111\u010c\3\2\2\2\u0111" +
			"\u010d\3\2\2\2\u0111\u010e\3\2\2\2\u0111\u010f\3\2\2\2\u0111\u0110\3\2" +
			"\2\2\u0112\u0114\3\2\2\2\u0113\u0115\7G\2\2\u0114\u0113\3\2\2\2\u0115" +
			"\u0116\3\2\2\2\u0116\u0114\3\2\2\2\u0116\u0117\3\2\2\2\u0117\u0119\3\2" +
			"\2\2\u0118\u0111\3\2\2\2\u0119\u011a\3\2\2\2\u011a\u0118\3\2\2\2\u011a" +
			"\u011b\3\2\2\2\u011b\u011c\3\2\2\2\u011c\u011d\7M\2\2\u011d\37\3\2\2\2" +
			"\u011e\u0120\5\f\7\2\u011f\u011e\3\2\2\2\u011f\u0120\3\2\2\2\u0120\u0121" +
			"\3\2\2\2\u0121\u0122\7\b\2\2\u0122\u0124\5Z.\2\u0123\u0125\7D\2\2\u0124" +
			"\u0123\3\2\2\2\u0124\u0125\3\2\2\2\u0125!\3\2\2\2\u0126\u0127\7\n\2\2" +
			"\u0127\u0129\5^\60\2\u0128\u012a\5\22\n\2\u0129\u0128\3\2\2\2\u0129\u012a" +
			"\3\2\2\2\u012a\u012d\3\2\2\2\u012b\u012c\7\13\2\2\u012c\u012e\5^\60\2" +
			"\u012d\u012b\3\2\2\2\u012d\u012e\3\2\2\2\u012e\u0130\3\2\2\2\u012f\u0131" +
			"\5\36\20\2\u0130\u012f\3\2\2\2\u0130\u0131\3\2\2\2\u0131#\3\2\2\2\u0132" +
			"\u0133\7\t\2\2\u0133\u0135\5Z.\2\u0134\u0136\7\27\2\2\u0135\u0134\3\2" +
			"\2\2\u0135\u0136\3\2\2\2\u0136\u0138\3\2\2\2\u0137\u0139\5\36\20\2\u0138" +
			"\u0137\3\2\2\2\u0138\u0139\3\2\2\2\u0139%\3\2\2\2\u013a\u013c\5\f\7\2" +
			"\u013b\u013a\3\2\2\2\u013b\u013c\3\2\2\2\u013c\u013d\3\2\2\2\u013d\u0149" +
			"\7/\2\2\u013e\u014a\5\64\33\2\u013f\u014a\5\66\34\2\u0140\u014a\58\35" +
			"\2\u0141\u014a\5\60\31\2\u0142\u014a\5\62\32\2\u0143\u014a\5:\36\2\u0144" +
			"\u014a\5<\37\2\u0145\u014a\5> \2\u0146\u014a\5(\25\2\u0147\u014a\5.\30" +
			"\2\u0148\u014a\5*\26\2\u0149\u013e\3\2\2\2\u0149\u013f\3\2\2\2\u0149\u0140" +
			"\3\2\2\2\u0149\u0141\3\2\2\2\u0149\u0142\3\2\2\2\u0149\u0143\3\2\2\2\u0149" +
			"\u0144\3\2\2\2\u0149\u0145\3\2\2\2\u0149\u0146\3\2\2\2\u0149\u0147\3\2" +
			"\2\2\u0149\u0148\3\2\2\2\u014a\u014c\3\2\2\2\u014b\u014d\5T+\2\u014c\u014b" +
			"\3\2\2\2\u014c\u014d\3\2\2\2\u014d\'\3\2\2\2\u014e\u014f\7\61\2\2\u014f" +
			"\u0150\5@!\2\u0150\u0151\7D\2\2\u0151)\3\2\2\2\u0152\u0153\7\60\2\2\u0153" +
			"\u0154\7D\2\2\u0154\u0158\7L\2\2\u0155\u0156\5,\27\2\u0156\u0157\7G\2" +
			"\2\u0157\u0159\3\2\2\2\u0158\u0155\3\2\2\2\u0159\u015a\3\2\2\2\u015a\u0158" +
			"\3\2\2\2\u015a\u015b\3\2\2\2\u015b\u015c\3\2\2\2\u015c\u015d\7M\2\2\u015d" +
			"+\3\2\2\2\u015e\u0160\7D\2\2\u015f\u0161\7*\2\2\u0160\u015f\3\2\2\2\u0160" +
			"\u0161\3\2\2\2\u0161-\3\2\2\2\u0162\u0164\5Z.\2\u0163\u0165\7\34\2\2\u0164" +
			"\u0163\3\2\2\2\u0164\u0165\3\2\2\2\u0165\u0166\3\2\2\2\u0166\u0169\7D" +
			"\2\2\u0167\u0168\7\'\2\2\u0168\u016a\7:\2\2\u0169\u0167\3\2\2\2\u0169" +
			"\u016a\3\2\2\2\u016a/\3\2\2\2\u016b\u016d\78\2\2\u016c\u016e\7\34\2\2" +
			"\u016d\u016c\3\2\2\2\u016d\u016e\3\2\2\2\u016e\u016f\3\2\2\2\u016f\u0177" +
			"\7D\2\2\u0170\u0172\7\'\2\2\u0171\u0173\5H%\2\u0172\u0171\3\2\2\2\u0173" +
			"\u0174\3\2\2\2\u0174\u0172\3\2\2\2\u0174\u0175\3\2\2\2\u0175\u0178\3\2" +
			"\2\2\u0176\u0178\7:\2\2\u0177\u0170\3\2\2\2\u0177\u0176\3\2\2\2\u0177" +
			"\u0178\3\2\2\2\u0178\61\3\2\2\2\u0179\u017b\7\67\2\2\u017a\u017c\7\34" +
			"\2\2\u017b\u017a\3\2\2\2\u017b\u017c\3\2\2\2\u017c\u017d\3\2\2\2\u017d" +
			"\u0185\7D\2\2\u017e\u0180\7\'\2\2\u017f\u0181\5J&\2\u0180\u017f\3\2\2" +
			"\2\u0181\u0182\3\2\2\2\u0182\u0180\3\2\2\2\u0182\u0183\3\2\2\2\u0183\u0186" +
			"\3\2\2\2\u0184\u0186\7:\2\2\u0185\u017e\3\2\2\2\u0185\u0184\3\2\2\2\u0185" +
			"\u0186\3\2\2\2\u0186\63\3\2\2\2\u0187\u0189\7\65\2\2\u0188\u018a\5@!\2" +
			"\u0189\u0188\3\2\2\2\u0189\u018a\3\2\2\2\u018a\u018c\3\2\2\2\u018b\u018d" +
			"\7\34\2\2\u018c\u018b\3\2\2\2\u018c\u018d\3\2\2\2\u018d\u018e\3\2\2\2" +
			"\u018e\u0199\7D\2\2\u018f\u0191\7\'\2\2\u0190\u0192\5B\"\2\u0191\u0190" +
			"\3\2\2\2\u0192\u0193\3\2\2\2\u0193\u0191\3\2\2\2\u0193\u0194\3\2\2\2\u0194" +
			"\u0196\3\2\2\2\u0195\u0197\5R*\2\u0196\u0195\3\2\2\2\u0196\u0197\3\2\2" +
			"\2\u0197\u019a\3\2\2\2\u0198\u019a\7:\2\2\u0199\u018f\3\2\2\2\u0199\u0198" +
			"\3\2\2\2\u0199\u019a\3\2\2\2\u019a\65\3\2\2\2\u019b\u019d\7\64\2\2\u019c" +
			"\u019e\5@!\2\u019d\u019c\3\2\2\2\u019d\u019e\3\2\2\2\u019e\u01a0\3\2\2" +
			"\2\u019f\u01a1\7\34\2\2\u01a0\u019f\3\2\2\2\u01a0\u01a1\3\2\2\2\u01a1" +
			"\u01a2\3\2\2\2\u01a2\u01ad\7D\2\2\u01a3\u01a5\7\'\2\2\u01a4\u01a6\5D#" +
			"\2\u01a5\u01a4\3\2\2\2\u01a6\u01a7\3\2\2\2\u01a7\u01a5\3\2\2\2\u01a7\u01a8" +
			"\3\2\2\2\u01a8\u01aa\3\2\2\2\u01a9\u01ab\5R*\2\u01aa\u01a9\3\2\2\2\u01aa" +
			"\u01ab\3\2\2\2\u01ab\u01ae\3\2\2\2\u01ac\u01ae\7:\2\2\u01ad\u01a3\3\2" +
			"\2\2\u01ad\u01ac\3\2\2\2\u01ad\u01ae\3\2\2\2\u01ae\67\3\2\2\2\u01af\u01b1" +
			"\7\66\2\2\u01b0\u01b2\5@!\2\u01b1\u01b0\3\2\2\2\u01b1\u01b2\3\2\2\2\u01b2" +
			"\u01b4\3\2\2\2\u01b3\u01b5\7\34\2\2\u01b4\u01b3\3\2\2\2\u01b4\u01b5\3" +
			"\2\2\2\u01b5\u01b6\3\2\2\2\u01b6\u01c1\7D\2\2\u01b7\u01b9\7\'\2\2\u01b8" +
			"\u01ba\5F$\2\u01b9\u01b8\3\2\2\2\u01ba\u01bb\3\2\2\2\u01bb\u01b9\3\2\2" +
			"\2\u01bb\u01bc\3\2\2\2\u01bc\u01be\3\2\2\2\u01bd\u01bf\5R*\2\u01be\u01bd" +
			"\3\2\2\2\u01be\u01bf\3\2\2\2\u01bf\u01c2\3\2\2\2\u01c0\u01c2\7:\2\2\u01c1" +
			"\u01b7\3\2\2\2\u01c1\u01c0\3\2\2\2\u01c1\u01c2\3\2\2\2\u01c29\3\2\2\2" +
			"\u01c3\u01c5\79\2\2\u01c4\u01c6\7\34\2\2\u01c5\u01c4\3\2\2\2\u01c5\u01c6" +
			"\3\2\2\2\u01c6\u01c7\3\2\2\2\u01c7\u01cf\7D\2\2\u01c8\u01ca\7\'\2\2\u01c9" +
			"\u01cb\5N(\2\u01ca\u01c9\3\2\2\2\u01cb\u01cc\3\2\2\2\u01cc\u01ca\3\2\2" +
			"\2\u01cc\u01cd\3\2\2\2\u01cd\u01d0\3\2\2\2\u01ce\u01d0\7:\2\2\u01cf\u01c8" +
			"\3\2\2\2\u01cf\u01ce\3\2\2\2\u01cf\u01d0\3\2\2\2\u01d0;\3\2\2\2\u01d1" +
			"\u01d3\7\63\2\2\u01d2\u01d4\7\34\2\2\u01d3\u01d2\3\2\2\2\u01d3\u01d4\3" +
			"\2\2\2\u01d4\u01d5\3\2\2\2\u01d5\u01df\7D\2\2\u01d6\u01dd\7\'\2\2\u01d7" +
			"\u01d9\5P)\2\u01d8\u01d7\3\2\2\2\u01d9\u01da\3\2\2\2\u01da\u01d8\3\2\2" +
			"\2\u01da\u01db\3\2\2\2\u01db\u01de\3\2\2\2\u01dc\u01de\7:\2\2\u01dd\u01d8" +
			"\3\2\2\2\u01dd\u01dc\3\2\2\2\u01de\u01e0\3\2\2\2\u01df\u01d6\3\2\2\2\u01df" +
			"\u01e0\3\2\2\2\u01e0=\3\2\2\2\u01e1\u01e3\7\62\2\2\u01e2\u01e4\7\34\2" +
			"\2\u01e3\u01e2\3\2\2\2\u01e3\u01e4\3\2\2\2\u01e4\u01e5\3\2\2\2\u01e5\u01ef" +
			"\7D\2\2\u01e6\u01ed\7\'\2\2\u01e7\u01e9\5L\'\2\u01e8\u01e7\3\2\2\2\u01e9" +
			"\u01ea\3\2\2\2\u01ea\u01e8\3\2\2\2\u01ea\u01eb\3\2\2\2\u01eb\u01ee\3\2" +
			"\2\2\u01ec\u01ee\7:\2\2\u01ed\u01e8\3\2\2\2\u01ed\u01ec\3\2\2\2\u01ee" +
			"\u01f0\3\2\2\2\u01ef\u01e6\3\2\2\2\u01ef\u01f0\3\2\2\2\u01f0?\3\2\2\2" +
			"\u01f1\u01f2\7$\2\2\u01f2\u01f3\7D\2\2\u01f3A\3\2\2\2\u01f4\u01f5\7<\2" +
			"\2\u01f5C\3\2\2\2\u01f6\u01f7\t\2\2\2\u01f7E\3\2\2\2\u01f8\u01f9\t\3\2" +
			"\2\u01f9G\3\2\2\2\u01fa\u01fb\7;\2\2\u01fbI\3\2\2\2\u01fc\u01fd\t\4\2" +
			"\2\u01fdK\3\2\2\2\u01fe\u01ff\7A\2\2\u01ffM\3\2\2\2\u0200\u0201\7B\2\2" +
			"\u0201O\3\2\2\2\u0202\u0203\7C\2\2\u0203Q\3\2\2\2\u0204\u0205\t\5\2\2" +
			"\u0205S\3\2\2\2\u0206\u0208\7\n\2\2\u0207\u0209\t\6\2\2\u0208\u0207\3" +
			"\2\2\2\u0209\u020a\3\2\2\2\u020a\u0208\3\2\2\2\u020a\u020b\3\2\2\2\u020b" +
			"U\3\2\2\2\u020c\u020d\7D\2\2\u020d\u0245\7\'\2\2\u020e\u0246\7:\2\2\u020f" +
			"\u0211\5Z.\2\u0210\u020f\3\2\2\2\u0211\u0212\3\2\2\2\u0212\u0210\3\2\2" +
			"\2\u0212\u0213\3\2\2\2\u0213\u0246\3\2\2\2\u0214\u0216\5J&\2\u0215\u0214" +
			"\3\2\2\2\u0216\u0217\3\2\2\2\u0217\u0215\3\2\2\2\u0217\u0218\3\2\2\2\u0218" +
			"\u0246\3\2\2\2\u0219\u021b\5H%\2\u021a\u0219\3\2\2\2\u021b\u021c\3\2\2" +
			"\2\u021c\u021a\3\2\2\2\u021c\u021d\3\2\2\2\u021d\u0246\3\2\2\2\u021e\u0220" +
			"\5N(\2\u021f\u021e\3\2\2\2\u0220\u0221\3\2\2\2\u0221\u021f\3\2\2\2\u0221" +
			"\u0222\3\2\2\2\u0222\u0246\3\2\2\2\u0223\u0225\5L\'\2\u0224\u0223\3\2" +
			"\2\2\u0225\u0226\3\2\2\2\u0226\u0224\3\2\2\2\u0226\u0227\3\2\2\2\u0227" +
			"\u0246\3\2\2\2\u0228\u022a\5P)\2\u0229\u0228\3\2\2\2\u022a\u022b\3\2\2" +
			"\2\u022b\u0229\3\2\2\2\u022b\u022c\3\2\2\2\u022c\u0246\3\2\2\2\u022d\u022f" +
			"\5B\"\2\u022e\u022d\3\2\2\2\u022f\u0230\3\2\2\2\u0230\u022e\3\2\2\2\u0230" +
			"\u0231\3\2\2\2\u0231\u0233\3\2\2\2\u0232\u0234\5R*\2\u0233\u0232\3\2\2" +
			"\2\u0233\u0234\3\2\2\2\u0234\u0246\3\2\2\2\u0235\u0237\5D#\2\u0236\u0235" +
			"\3\2\2\2\u0237\u0238\3\2\2\2\u0238\u0236\3\2\2\2\u0238\u0239\3\2\2\2\u0239" +
			"\u023b\3\2\2\2\u023a\u023c\5R*\2\u023b\u023a\3\2\2\2\u023b\u023c\3\2\2" +
			"\2\u023c\u0246\3\2\2\2\u023d\u023f\5F$\2\u023e\u023d\3\2\2\2\u023f\u0240" +
			"\3\2\2\2\u0240\u023e\3\2\2\2\u0240\u0241\3\2\2\2\u0241\u0243\3\2\2\2\u0242" +
			"\u0244\5R*\2\u0243\u0242\3\2\2\2\u0243\u0244\3\2\2\2\u0244\u0246\3\2\2" +
			"\2\u0245\u020e\3\2\2\2\u0245\u0210\3\2\2\2\u0245\u0215\3\2\2\2\u0245\u021a" +
			"\3\2\2\2\u0245\u021f\3\2\2\2\u0245\u0224\3\2\2\2\u0245\u0229\3\2\2\2\u0245" +
			"\u022e\3\2\2\2\u0245\u0236\3\2\2\2\u0245\u023e\3\2\2\2\u0246W\3\2\2\2" +
			"\u0247\u0249\5\\/\2\u0248\u0247\3\2\2\2\u0249\u024c\3\2\2\2\u024a\u0248" +
			"\3\2\2\2\u024a\u024b\3\2\2\2\u024b\u024d\3\2\2\2\u024c\u024a\3\2\2\2\u024d" +
			"\u024e\7D\2\2\u024eY\3\2\2\2\u024f\u0251\5\\/\2\u0250\u024f\3\2\2\2\u0251" +
			"\u0254\3\2\2\2\u0252\u0250\3\2\2\2\u0252\u0253\3\2\2\2\u0253\u0255\3\2" +
			"\2\2\u0254\u0252\3\2\2\2\u0255\u0256\7D\2\2\u0256[\3\2\2\2\u0257\u0258" +
			"\7D\2\2\u0258\u0259\7&\2\2\u0259]\3\2\2\2\u025a\u025b\t\7\2\2\u025b_\3" +
			"\2\2\2ecglrw}\u0080\u0088\u008d\u0093\u0098\u009b\u009f\u00a2\u00a8\u00ad" +
			"\u00b1\u00b3\u00b7\u00c3\u00c9\u00cb\u00d4\u00d9\u00de\u00e1\u00e6\u00e9" +
			"\u00ee\u00f1\u00f6\u00fb\u00fe\u0104\u0111\u0116\u011a\u011f\u0124\u0129" +
			"\u012d\u0130\u0135\u0138\u013b\u0149\u014c\u015a\u0160\u0164\u0169\u016d" +
			"\u0174\u0177\u017b\u0182\u0185\u0189\u018c\u0193\u0196\u0199\u019d\u01a0" +
			"\u01a7\u01aa\u01ad\u01b1\u01b4\u01bb\u01be\u01c1\u01c5\u01cc\u01cf\u01d3" +
			"\u01da\u01dd\u01df\u01e3\u01ea\u01ed\u01ef\u020a\u0212\u0217\u021c\u0221" +
			"\u0226\u022b\u0230\u0233\u0238\u023b\u0240\u0243\u0245\u024a\u0252";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());

	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}