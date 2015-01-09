// Generated from /Users/oroncal/workspace/tara/rt/src/siani/tara/compiler/parser/antlr/TaraGrammar.g4 by ANTLR 4.4.1-dev
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
	static { RuntimeMetaData.checkVersion("4.4.1-dev", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		MEASURE_VALUE=62, FACET=15, RIGHT_SQUARE=29, STAR=40, NEGATIVE_VALUE=56, 
		DOC=65, SUB=2, DEDENT=69, EQUALS=37, ABSTRACT=11, BOOLEAN_VALUE=54, METAIDENTIFIER=1, 
		RATIO_TYPE=50, RESOURCE=43, ADDRESS_VALUE=60, UNKNOWN_TOKEN=70, WORD=42, 
		AS=5, HAS=6, RIGHT_PARENTHESIS=27, ALWAYS=21, COMMA=35, IS=8, IDENTIFIER=61, 
		LEFT_PARENTHESIS=26, STRING_MULTILINE_VALUE_KEY=59, PLUS=41, VAR=4, NL=67, 
		INTENTION=16, DOT=36, COMPONENT=14, STRING_VALUE=58, DOUBLE_VALUE=57, 
		WITH=9, NEW_LINE_INDENT=68, AGGREGATED=23, ADDRESSED=22, TERMINAL=17, 
		NATURAL_TYPE=45, ON=7, AMPERSAND=33, CLOSE_INLINE=32, DOUBLE_TYPE=46, 
		BOOLEAN_TYPE=48, SEMICOLON=39, MEASURE_TYPE=49, SCIENCE_NOT=53, LOCAL=20, 
		REQUIRED=13, INT_TYPE=44, READONLY=24, LIST=30, ROOT=25, EMPTY=52, COLON=34, 
		NATURAL_VALUE=55, NEWLINE=63, SINGLE=12, PROPERTY=19, SPACES=64, SP=66, 
		APHOSTROPHE=38, NAMED=18, STRING_TYPE=47, DATE_TYPE=51, INLINE=31, USE=3, 
		EXTENDS=10, LEFT_SQUARE=28;
	public static final String[] tokenNames = {
		"<INVALID>", "'Concept'", "'sub'", "'use'", "'var'", "'as'", "'has'", 
		"'on'", "'is'", "'with'", "'extends'", "'abstract'", "'single'", "'required'", 
		"'component'", "'facet'", "'intention'", "'terminal'", "'named'", "'property'", 
		"'local'", "'always'", "'addressed'", "'aggregated'", "'readonly'", "'root'", 
		"'('", "')'", "'['", "']'", "'...'", "'>'", "'<'", "'&'", "':'", "','", 
		"'.'", "'='", "'\"'", "SEMICOLON", "'*'", "'+'", "'word'", "'file'", "'integer'", 
		"'natural'", "'double'", "'string'", "'boolean'", "'measure'", "'ratio'", 
		"'date'", "'empty'", "SCIENCE_NOT", "BOOLEAN_VALUE", "NATURAL_VALUE", 
		"NEGATIVE_VALUE", "DOUBLE_VALUE", "STRING_VALUE", "STRING_MULTILINE_VALUE_KEY", 
		"ADDRESS_VALUE", "IDENTIFIER", "MEASURE_VALUE", "NEWLINE", "SPACES", "DOC", 
		"SP", "NL", "'indent'", "'dedent'", "UNKNOWN_TOKEN"
	};
	public static final int
		RULE_root = 0, RULE_imports = 1, RULE_anImport = 2, RULE_doc = 3, RULE_concept = 4, 
		RULE_signature = 5, RULE_address = 6, RULE_parameters = 7, RULE_parameterList = 8, 
		RULE_explicit = 9, RULE_initValue = 10, RULE_metaWord = 11, RULE_metaWordNames = 12, 
		RULE_body = 13, RULE_variable = 14, RULE_facetApply = 15, RULE_facetTarget = 16, 
		RULE_conceptReference = 17, RULE_word = 18, RULE_wordNames = 19, RULE_resource = 20, 
		RULE_booleanAttribute = 21, RULE_stringAttribute = 22, RULE_dateAttribute = 23, 
		RULE_ratioAttribute = 24, RULE_naturalAttribute = 25, RULE_integerAttribute = 26, 
		RULE_doubleAttribute = 27, RULE_measureAttribute = 28, RULE_reference = 29, 
		RULE_attributeType = 30, RULE_measureType = 31, RULE_doubleMeasure = 32, 
		RULE_stringValue = 33, RULE_booleanValue = 34, RULE_naturalValue = 35, 
		RULE_integerValue = 36, RULE_doubleValue = 37, RULE_linkValue = 38, RULE_count = 39, 
		RULE_measureValue = 40, RULE_annotationsAndFacets = 41, RULE_annotations = 42, 
		RULE_annotation = 43, RULE_varInit = 44, RULE_headerReference = 45, RULE_identifierReference = 46, 
		RULE_hierarchy = 47, RULE_metaidentifier = 48;
	public static final String[] ruleNames = {
		"root", "imports", "anImport", "doc", "concept", "signature", "address", 
		"parameters", "parameterList", "explicit", "initValue", "metaWord", "metaWordNames", 
		"body", "variable", "facetApply", "facetTarget", "conceptReference", "word", 
		"wordNames", "resource", "booleanAttribute", "stringAttribute", "dateAttribute", 
		"ratioAttribute", "naturalAttribute", "integerAttribute", "doubleAttribute", 
		"measureAttribute", "reference", "attributeType", "measureType", "doubleMeasure", 
		"stringValue", "booleanValue", "naturalValue", "integerValue", "doubleValue", 
		"linkValue", "count", "measureValue", "annotationsAndFacets", "annotations", 
		"annotation", "varInit", "headerReference", "identifierReference", "hierarchy", 
		"metaidentifier"
	};

	@Override
	public String getGrammarFileName() { return "TaraGrammar.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

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
		public List<ConceptContext> concept() {
			return getRuleContexts(ConceptContext.class);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(TaraGrammar.NEWLINE); }
		public TerminalNode EOF() { return getToken(TaraGrammar.EOF, 0); }
		public ImportsContext imports() {
			return getRuleContext(ImportsContext.class,0);
		}
		public TerminalNode NEWLINE(int i) {
			return getToken(TaraGrammar.NEWLINE, i);
		}
		public ConceptContext concept(int i) {
			return getRuleContext(ConceptContext.class,i);
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
			setState(101);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(98); match(NEWLINE);
				}
				}
				setState(103);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(105);
			_la = _input.LA(1);
			if (_la==USE) {
				{
				setState(104); imports();
				}
			}

			setState(116);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << METAIDENTIFIER) | (1L << SUB) | (1L << IDENTIFIER))) != 0) || _la==DOC) {
				{
				{
				setState(107); concept();
				setState(111);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NEWLINE) {
					{
					{
					setState(108); match(NEWLINE);
					}
					}
					setState(113);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				}
				setState(118);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(119); match(EOF);
			}
		}
		catch (RecognitionException re) {
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
		public AnImportContext anImport(int i) {
			return getRuleContext(AnImportContext.class,i);
		}
		public List<AnImportContext> anImport() {
			return getRuleContexts(AnImportContext.class);
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
		enterRule(_localctx, 2, RULE_imports);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(122); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(121); anImport();
				}
				}
				setState(124); 
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
		public TerminalNode AS() { return getToken(TaraGrammar.AS, 0); }
		public TerminalNode NEWLINE() { return getToken(TaraGrammar.NEWLINE, 0); }
		public HeaderReferenceContext headerReference() {
			return getRuleContext(HeaderReferenceContext.class,0);
		}
		public TerminalNode USE() { return getToken(TaraGrammar.USE, 0); }
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
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
		enterRule(_localctx, 4, RULE_anImport);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(126); match(USE);
			setState(127); headerReference();
			setState(130);
			_la = _input.LA(1);
			if (_la==AS) {
				{
				setState(128); match(AS);
				setState(129); match(IDENTIFIER);
				}
			}

			setState(132); match(NEWLINE);
			}
		}
		catch (RecognitionException re) {
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
		public TerminalNode DOC(int i) {
			return getToken(TaraGrammar.DOC, i);
		}
		public List<TerminalNode> DOC() { return getTokens(TaraGrammar.DOC); }
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
		enterRule(_localctx, 6, RULE_doc);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(135); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(134); match(DOC);
				}
				}
				setState(137); 
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

	public static class ConceptContext extends ParserRuleContext {
		public AnnotationsAndFacetsContext annotationsAndFacets() {
			return getRuleContext(AnnotationsAndFacetsContext.class,0);
		}
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public DocContext doc() {
			return getRuleContext(DocContext.class,0);
		}
		public SignatureContext signature() {
			return getRuleContext(SignatureContext.class,0);
		}
		public ConceptContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_concept; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterConcept(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitConcept(this);
		}
	}

	public final ConceptContext concept() throws RecognitionException {
		ConceptContext _localctx = new ConceptContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_concept);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(140);
			_la = _input.LA(1);
			if (_la==DOC) {
				{
				setState(139); doc();
				}
			}

			setState(142); signature();
			setState(144);
			_la = _input.LA(1);
			if (_la==IS) {
				{
				setState(143); annotationsAndFacets();
				}
			}

			setState(147);
			_la = _input.LA(1);
			if (_la==NEW_LINE_INDENT) {
				{
				setState(146); body();
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
		public IdentifierReferenceContext identifierReference() {
			return getRuleContext(IdentifierReferenceContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
		public MetaidentifierContext metaidentifier() {
			return getRuleContext(MetaidentifierContext.class,0);
		}
		public ParametersContext parameters() {
			return getRuleContext(ParametersContext.class,0);
		}
		public AddressContext address() {
			return getRuleContext(AddressContext.class,0);
		}
		public TerminalNode EXTENDS() { return getToken(TaraGrammar.EXTENDS, 0); }
		public TerminalNode SUB() { return getToken(TaraGrammar.SUB, 0); }
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
		enterRule(_localctx, 10, RULE_signature);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(164);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				{
				{
				setState(149); match(SUB);
				setState(150); match(IDENTIFIER);
				}
				}
				break;
			case 2:
				{
				{
				setState(151); metaidentifier();
				setState(153);
				_la = _input.LA(1);
				if (_la==LEFT_PARENTHESIS) {
					{
					setState(152); parameters();
					}
				}

				setState(155); match(IDENTIFIER);
				setState(158);
				_la = _input.LA(1);
				if (_la==EXTENDS) {
					{
					setState(156); match(EXTENDS);
					setState(157); identifierReference();
					}
				}

				}
				}
				break;
			case 3:
				{
				setState(160); metaidentifier();
				setState(162);
				_la = _input.LA(1);
				if (_la==LEFT_PARENTHESIS) {
					{
					setState(161); parameters();
					}
				}

				}
				break;
			}
			setState(167);
			_la = _input.LA(1);
			if (_la==ADDRESS_VALUE) {
				{
				setState(166); address();
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

	public static class AddressContext extends ParserRuleContext {
		public TerminalNode ADDRESS_VALUE() { return getToken(TaraGrammar.ADDRESS_VALUE, 0); }
		public AddressContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_address; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterAddress(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitAddress(this);
		}
	}

	public final AddressContext address() throws RecognitionException {
		AddressContext _localctx = new AddressContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_address);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(169); match(ADDRESS_VALUE);
			}
		}
		catch (RecognitionException re) {
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
		public ParameterListContext parameterList() {
			return getRuleContext(ParameterListContext.class,0);
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
		enterRule(_localctx, 14, RULE_parameters);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(171); match(LEFT_PARENTHESIS);
			setState(173);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << METAIDENTIFIER) | (1L << EMPTY) | (1L << BOOLEAN_VALUE) | (1L << NATURAL_VALUE) | (1L << NEGATIVE_VALUE) | (1L << DOUBLE_VALUE) | (1L << STRING_VALUE) | (1L << STRING_MULTILINE_VALUE_KEY) | (1L << ADDRESS_VALUE) | (1L << IDENTIFIER) | (1L << NEWLINE))) != 0)) {
				{
				setState(172); parameterList();
				}
			}

			setState(175); match(RIGHT_PARENTHESIS);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParameterListContext extends ParserRuleContext {
		public ExplicitContext explicit(int i) {
			return getRuleContext(ExplicitContext.class,i);
		}
		public List<ExplicitContext> explicit() {
			return getRuleContexts(ExplicitContext.class);
		}
		public List<TerminalNode> COMMA() { return getTokens(TaraGrammar.COMMA); }
		public InitValueContext initValue(int i) {
			return getRuleContext(InitValueContext.class,i);
		}
		public TerminalNode COMMA(int i) {
			return getToken(TaraGrammar.COMMA, i);
		}
		public List<InitValueContext> initValue() {
			return getRuleContexts(InitValueContext.class);
		}
		public ParameterListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameterList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterParameterList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitParameterList(this);
		}
	}

	public final ParameterListContext parameterList() throws RecognitionException {
		ParameterListContext _localctx = new ParameterListContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_parameterList);
		int _la;
		try {
			setState(196);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(177); explicit();
				setState(178); initValue();
				setState(185);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(179); match(COMMA);
					setState(180); explicit();
					setState(181); initValue();
					}
					}
					setState(187);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(188); initValue();
				setState(193);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(189); match(COMMA);
					setState(190); initValue();
					}
					}
					setState(195);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
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

	public static class ExplicitContext extends ParserRuleContext {
		public TerminalNode EQUALS() { return getToken(TaraGrammar.EQUALS, 0); }
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
		public ExplicitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_explicit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterExplicit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitExplicit(this);
		}
	}

	public final ExplicitContext explicit() throws RecognitionException {
		ExplicitContext _localctx = new ExplicitContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_explicit);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(198); match(IDENTIFIER);
			setState(199); match(EQUALS);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InitValueContext extends ParserRuleContext {
		public List<BooleanValueContext> booleanValue() {
			return getRuleContexts(BooleanValueContext.class);
		}
		public BooleanValueContext booleanValue(int i) {
			return getRuleContext(BooleanValueContext.class,i);
		}
		public List<DoubleValueContext> doubleValue() {
			return getRuleContexts(DoubleValueContext.class);
		}
		public List<StringValueContext> stringValue() {
			return getRuleContexts(StringValueContext.class);
		}
		public LinkValueContext linkValue() {
			return getRuleContext(LinkValueContext.class,0);
		}
		public TerminalNode EMPTY() { return getToken(TaraGrammar.EMPTY, 0); }
		public DoubleValueContext doubleValue(int i) {
			return getRuleContext(DoubleValueContext.class,i);
		}
		public List<IdentifierReferenceContext> identifierReference() {
			return getRuleContexts(IdentifierReferenceContext.class);
		}
		public IntegerValueContext integerValue(int i) {
			return getRuleContext(IntegerValueContext.class,i);
		}
		public List<NaturalValueContext> naturalValue() {
			return getRuleContexts(NaturalValueContext.class);
		}
		public MeasureValueContext measureValue() {
			return getRuleContext(MeasureValueContext.class,0);
		}
		public NaturalValueContext naturalValue(int i) {
			return getRuleContext(NaturalValueContext.class,i);
		}
		public IdentifierReferenceContext identifierReference(int i) {
			return getRuleContext(IdentifierReferenceContext.class,i);
		}
		public StringValueContext stringValue(int i) {
			return getRuleContext(StringValueContext.class,i);
		}
		public MetaWordContext metaWord() {
			return getRuleContext(MetaWordContext.class,0);
		}
		public List<IntegerValueContext> integerValue() {
			return getRuleContexts(IntegerValueContext.class);
		}
		public InitValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_initValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterInitValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitInitValue(this);
		}
	}

	public final InitValueContext initValue() throws RecognitionException {
		InitValueContext _localctx = new InitValueContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_initValue);
		int _la;
		try {
			int _alt;
			setState(243);
			switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(202); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(201); identifierReference();
					}
					}
					setState(204); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==IDENTIFIER );
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(207); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(206); stringValue();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(209); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(212); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(211); booleanValue();
					}
					}
					setState(214); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==BOOLEAN_VALUE );
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(217); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(216); naturalValue();
					}
					}
					setState(219); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NATURAL_VALUE );
				setState(222);
				_la = _input.LA(1);
				if (_la==IDENTIFIER || _la==MEASURE_VALUE) {
					{
					setState(221); measureValue();
					}
				}

				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(225); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(224); integerValue();
					}
					}
					setState(227); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NATURAL_VALUE || _la==NEGATIVE_VALUE );
				setState(230);
				_la = _input.LA(1);
				if (_la==IDENTIFIER || _la==MEASURE_VALUE) {
					{
					setState(229); measureValue();
					}
				}

				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(233); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(232); doubleValue();
					}
					}
					setState(235); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NATURAL_VALUE) | (1L << NEGATIVE_VALUE) | (1L << DOUBLE_VALUE))) != 0) );
				setState(238);
				_la = _input.LA(1);
				if (_la==IDENTIFIER || _la==MEASURE_VALUE) {
					{
					setState(237); measureValue();
					}
				}

				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(240); metaWord();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(241); linkValue();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(242); match(EMPTY);
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

	public static class MetaWordContext extends ParserRuleContext {
		public MetaWordNamesContext metaWordNames(int i) {
			return getRuleContext(MetaWordNamesContext.class,i);
		}
		public MetaidentifierContext metaidentifier() {
			return getRuleContext(MetaidentifierContext.class,0);
		}
		public List<MetaWordNamesContext> metaWordNames() {
			return getRuleContexts(MetaWordNamesContext.class);
		}
		public MetaWordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_metaWord; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterMetaWord(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitMetaWord(this);
		}
	}

	public final MetaWordContext metaWord() throws RecognitionException {
		MetaWordContext _localctx = new MetaWordContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_metaWord);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(245); metaidentifier();
			setState(249);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DOT) {
				{
				{
				setState(246); metaWordNames();
				}
				}
				setState(251);
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

	public static class MetaWordNamesContext extends ParserRuleContext {
		public TerminalNode DOT() { return getToken(TaraGrammar.DOT, 0); }
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
		public MetaWordNamesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_metaWordNames; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterMetaWordNames(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitMetaWordNames(this);
		}
	}

	public final MetaWordNamesContext metaWordNames() throws RecognitionException {
		MetaWordNamesContext _localctx = new MetaWordNamesContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_metaWordNames);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(252); match(DOT);
			setState(253); match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
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
		public List<ConceptContext> concept() {
			return getRuleContexts(ConceptContext.class);
		}
		public TerminalNode NEWLINE(int i) {
			return getToken(TaraGrammar.NEWLINE, i);
		}
		public AnnotationsAndFacetsContext annotationsAndFacets(int i) {
			return getRuleContext(AnnotationsAndFacetsContext.class,i);
		}
		public List<VarInitContext> varInit() {
			return getRuleContexts(VarInitContext.class);
		}
		public VarInitContext varInit(int i) {
			return getRuleContext(VarInitContext.class,i);
		}
		public List<VariableContext> variable() {
			return getRuleContexts(VariableContext.class);
		}
		public TerminalNode NEW_LINE_INDENT() { return getToken(TaraGrammar.NEW_LINE_INDENT, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(TaraGrammar.NEWLINE); }
		public DocContext doc(int i) {
			return getRuleContext(DocContext.class,i);
		}
		public List<AnnotationsAndFacetsContext> annotationsAndFacets() {
			return getRuleContexts(AnnotationsAndFacetsContext.class);
		}
		public ConceptReferenceContext conceptReference(int i) {
			return getRuleContext(ConceptReferenceContext.class,i);
		}
		public List<ConceptReferenceContext> conceptReference() {
			return getRuleContexts(ConceptReferenceContext.class);
		}
		public List<FacetTargetContext> facetTarget() {
			return getRuleContexts(FacetTargetContext.class);
		}
		public VariableContext variable(int i) {
			return getRuleContext(VariableContext.class,i);
		}
		public ConceptContext concept(int i) {
			return getRuleContext(ConceptContext.class,i);
		}
		public TerminalNode DEDENT() { return getToken(TaraGrammar.DEDENT, 0); }
		public FacetTargetContext facetTarget(int i) {
			return getRuleContext(FacetTargetContext.class,i);
		}
		public List<DocContext> doc() {
			return getRuleContexts(DocContext.class);
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
			setState(255); match(NEW_LINE_INDENT);
			setState(270); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(263);
				switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
				case 1:
					{
					setState(256); variable();
					}
					break;
				case 2:
					{
					setState(257); concept();
					}
					break;
				case 3:
					{
					setState(258); varInit();
					}
					break;
				case 4:
					{
					setState(259); annotationsAndFacets();
					}
					break;
				case 5:
					{
					setState(260); facetTarget();
					}
					break;
				case 6:
					{
					setState(261); conceptReference();
					}
					break;
				case 7:
					{
					setState(262); doc();
					}
					break;
				}
				setState(266); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(265); match(NEWLINE);
					}
					}
					setState(268); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NEWLINE );
				}
				}
				setState(272); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << METAIDENTIFIER) | (1L << SUB) | (1L << VAR) | (1L << HAS) | (1L << ON) | (1L << IS) | (1L << IDENTIFIER))) != 0) || _la==DOC );
			setState(274); match(DEDENT);
			}
		}
		catch (RecognitionException re) {
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
		public DoubleAttributeContext doubleAttribute() {
			return getRuleContext(DoubleAttributeContext.class,0);
		}
		public IntegerAttributeContext integerAttribute() {
			return getRuleContext(IntegerAttributeContext.class,0);
		}
		public TerminalNode IS() { return getToken(TaraGrammar.IS, 0); }
		public DateAttributeContext dateAttribute() {
			return getRuleContext(DateAttributeContext.class,0);
		}
		public StringAttributeContext stringAttribute() {
			return getRuleContext(StringAttributeContext.class,0);
		}
		public ResourceContext resource() {
			return getRuleContext(ResourceContext.class,0);
		}
		public WordContext word() {
			return getRuleContext(WordContext.class,0);
		}
		public BooleanAttributeContext booleanAttribute() {
			return getRuleContext(BooleanAttributeContext.class,0);
		}
		public TerminalNode VAR() { return getToken(TaraGrammar.VAR, 0); }
		public RatioAttributeContext ratioAttribute() {
			return getRuleContext(RatioAttributeContext.class,0);
		}
		public AnnotationsContext annotations() {
			return getRuleContext(AnnotationsContext.class,0);
		}
		public NaturalAttributeContext naturalAttribute() {
			return getRuleContext(NaturalAttributeContext.class,0);
		}
		public MeasureAttributeContext measureAttribute() {
			return getRuleContext(MeasureAttributeContext.class,0);
		}
		public ReferenceContext reference() {
			return getRuleContext(ReferenceContext.class,0);
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
		enterRule(_localctx, 28, RULE_variable);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(276); match(VAR);
			setState(288);
			switch (_input.LA(1)) {
			case NATURAL_TYPE:
				{
				setState(277); naturalAttribute();
				}
				break;
			case INT_TYPE:
				{
				setState(278); integerAttribute();
				}
				break;
			case DOUBLE_TYPE:
				{
				setState(279); doubleAttribute();
				}
				break;
			case RATIO_TYPE:
				{
				setState(280); ratioAttribute();
				}
				break;
			case MEASURE_TYPE:
				{
				setState(281); measureAttribute();
				}
				break;
			case BOOLEAN_TYPE:
				{
				setState(282); booleanAttribute();
				}
				break;
			case STRING_TYPE:
				{
				setState(283); stringAttribute();
				}
				break;
			case DATE_TYPE:
				{
				setState(284); dateAttribute();
				}
				break;
			case RESOURCE:
				{
				setState(285); resource();
				}
				break;
			case IDENTIFIER:
				{
				setState(286); reference();
				}
				break;
			case WORD:
				{
				setState(287); word();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(292);
			_la = _input.LA(1);
			if (_la==IS) {
				{
				setState(290); match(IS);
				setState(291); annotations();
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

	public static class FacetApplyContext extends ParserRuleContext {
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public List<MetaidentifierContext> metaidentifier() {
			return getRuleContexts(MetaidentifierContext.class);
		}
		public TerminalNode WITH() { return getToken(TaraGrammar.WITH, 0); }
		public ParametersContext parameters() {
			return getRuleContext(ParametersContext.class,0);
		}
		public MetaidentifierContext metaidentifier(int i) {
			return getRuleContext(MetaidentifierContext.class,i);
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
		enterRule(_localctx, 30, RULE_facetApply);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(294); metaidentifier();
			setState(296);
			_la = _input.LA(1);
			if (_la==LEFT_PARENTHESIS) {
				{
				setState(295); parameters();
				}
			}

			setState(300);
			_la = _input.LA(1);
			if (_la==WITH) {
				{
				setState(298); match(WITH);
				setState(299); metaidentifier();
				}
			}

			setState(303);
			switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
			case 1:
				{
				setState(302); body();
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
		public IdentifierReferenceContext identifierReference() {
			return getRuleContext(IdentifierReferenceContext.class,0);
		}
		public TerminalNode ON() { return getToken(TaraGrammar.ON, 0); }
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public TerminalNode ALWAYS() { return getToken(TaraGrammar.ALWAYS, 0); }
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
			setState(305); match(ON);
			setState(306); identifierReference();
			setState(308);
			_la = _input.LA(1);
			if (_la==ALWAYS) {
				{
				setState(307); match(ALWAYS);
				}
			}

			setState(311);
			_la = _input.LA(1);
			if (_la==NEW_LINE_INDENT) {
				{
				setState(310); body();
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

	public static class ConceptReferenceContext extends ParserRuleContext {
		public IdentifierReferenceContext identifierReference() {
			return getRuleContext(IdentifierReferenceContext.class,0);
		}
		public TerminalNode HAS() { return getToken(TaraGrammar.HAS, 0); }
		public AnnotationsContext annotations() {
			return getRuleContext(AnnotationsContext.class,0);
		}
		public TerminalNode IS() { return getToken(TaraGrammar.IS, 0); }
		public ConceptReferenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conceptReference; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterConceptReference(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitConceptReference(this);
		}
	}

	public final ConceptReferenceContext conceptReference() throws RecognitionException {
		ConceptReferenceContext _localctx = new ConceptReferenceContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_conceptReference);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(313); match(HAS);
			setState(314); identifierReference();
			setState(317);
			_la = _input.LA(1);
			if (_la==IS) {
				{
				setState(315); match(IS);
				setState(316); annotations();
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

	public static class WordContext extends ParserRuleContext {
		public List<TerminalNode> NEWLINE() { return getTokens(TaraGrammar.NEWLINE); }
		public TerminalNode WORD() { return getToken(TaraGrammar.WORD, 0); }
		public TerminalNode NEWLINE(int i) {
			return getToken(TaraGrammar.NEWLINE, i);
		}
		public WordNamesContext wordNames(int i) {
			return getRuleContext(WordNamesContext.class,i);
		}
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
		public TerminalNode DEDENT() { return getToken(TaraGrammar.DEDENT, 0); }
		public List<WordNamesContext> wordNames() {
			return getRuleContexts(WordNamesContext.class);
		}
		public TerminalNode LIST() { return getToken(TaraGrammar.LIST, 0); }
		public TerminalNode NEW_LINE_INDENT() { return getToken(TaraGrammar.NEW_LINE_INDENT, 0); }
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
		enterRule(_localctx, 36, RULE_word);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(319); match(WORD);
			setState(321);
			_la = _input.LA(1);
			if (_la==LIST) {
				{
				setState(320); match(LIST);
				}
			}

			setState(323); match(IDENTIFIER);
			setState(324); match(NEW_LINE_INDENT);
			setState(328); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(325); wordNames();
				setState(326); match(NEWLINE);
				}
				}
				setState(330); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==IDENTIFIER );
			setState(332); match(DEDENT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WordNamesContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
		public TerminalNode STAR() { return getToken(TaraGrammar.STAR, 0); }
		public WordNamesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_wordNames; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterWordNames(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitWordNames(this);
		}
	}

	public final WordNamesContext wordNames() throws RecognitionException {
		WordNamesContext _localctx = new WordNamesContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_wordNames);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(334); match(IDENTIFIER);
			setState(336);
			_la = _input.LA(1);
			if (_la==STAR) {
				{
				setState(335); match(STAR);
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

	public static class ResourceContext extends ParserRuleContext {
		public AttributeTypeContext attributeType() {
			return getRuleContext(AttributeTypeContext.class,0);
		}
		public TerminalNode RESOURCE() { return getToken(TaraGrammar.RESOURCE, 0); }
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
		public ResourceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_resource; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterResource(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitResource(this);
		}
	}

	public final ResourceContext resource() throws RecognitionException {
		ResourceContext _localctx = new ResourceContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_resource);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(338); match(RESOURCE);
			setState(339); attributeType();
			setState(340); match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BooleanAttributeContext extends ParserRuleContext {
		public BooleanValueContext booleanValue(int i) {
			return getRuleContext(BooleanValueContext.class,i);
		}
		public List<BooleanValueContext> booleanValue() {
			return getRuleContexts(BooleanValueContext.class);
		}
		public TerminalNode BOOLEAN_TYPE() { return getToken(TaraGrammar.BOOLEAN_TYPE, 0); }
		public TerminalNode EQUALS() { return getToken(TaraGrammar.EQUALS, 0); }
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
		public TerminalNode LIST() { return getToken(TaraGrammar.LIST, 0); }
		public BooleanAttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_booleanAttribute; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterBooleanAttribute(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitBooleanAttribute(this);
		}
	}

	public final BooleanAttributeContext booleanAttribute() throws RecognitionException {
		BooleanAttributeContext _localctx = new BooleanAttributeContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_booleanAttribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(342); match(BOOLEAN_TYPE);
			setState(344);
			_la = _input.LA(1);
			if (_la==LIST) {
				{
				setState(343); match(LIST);
				}
			}

			setState(346); match(IDENTIFIER);
			setState(353);
			_la = _input.LA(1);
			if (_la==EQUALS) {
				{
				setState(347); match(EQUALS);
				setState(349); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(348); booleanValue();
					}
					}
					setState(351); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==BOOLEAN_VALUE );
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

	public static class StringAttributeContext extends ParserRuleContext {
		public TerminalNode EQUALS() { return getToken(TaraGrammar.EQUALS, 0); }
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
		public TerminalNode STRING_TYPE() { return getToken(TaraGrammar.STRING_TYPE, 0); }
		public List<StringValueContext> stringValue() {
			return getRuleContexts(StringValueContext.class);
		}
		public StringValueContext stringValue(int i) {
			return getRuleContext(StringValueContext.class,i);
		}
		public TerminalNode LIST() { return getToken(TaraGrammar.LIST, 0); }
		public StringAttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stringAttribute; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterStringAttribute(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitStringAttribute(this);
		}
	}

	public final StringAttributeContext stringAttribute() throws RecognitionException {
		StringAttributeContext _localctx = new StringAttributeContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_stringAttribute);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(355); match(STRING_TYPE);
			setState(357);
			_la = _input.LA(1);
			if (_la==LIST) {
				{
				setState(356); match(LIST);
				}
			}

			setState(359); match(IDENTIFIER);
			setState(366);
			_la = _input.LA(1);
			if (_la==EQUALS) {
				{
				setState(360); match(EQUALS);
				setState(362); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(361); stringValue();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(364); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,48,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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

	public static class DateAttributeContext extends ParserRuleContext {
		public TerminalNode EQUALS() { return getToken(TaraGrammar.EQUALS, 0); }
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
		public TerminalNode DATE_TYPE() { return getToken(TaraGrammar.DATE_TYPE, 0); }
		public List<StringValueContext> stringValue() {
			return getRuleContexts(StringValueContext.class);
		}
		public StringValueContext stringValue(int i) {
			return getRuleContext(StringValueContext.class,i);
		}
		public TerminalNode LIST() { return getToken(TaraGrammar.LIST, 0); }
		public DateAttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dateAttribute; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterDateAttribute(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitDateAttribute(this);
		}
	}

	public final DateAttributeContext dateAttribute() throws RecognitionException {
		DateAttributeContext _localctx = new DateAttributeContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_dateAttribute);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(368); match(DATE_TYPE);
			setState(370);
			_la = _input.LA(1);
			if (_la==LIST) {
				{
				setState(369); match(LIST);
				}
			}

			setState(372); match(IDENTIFIER);
			setState(379);
			_la = _input.LA(1);
			if (_la==EQUALS) {
				{
				setState(373); match(EQUALS);
				setState(375); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(374); stringValue();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(377); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,51,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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

	public static class RatioAttributeContext extends ParserRuleContext {
		public DoubleValueContext doubleValue(int i) {
			return getRuleContext(DoubleValueContext.class,i);
		}
		public TerminalNode EQUALS() { return getToken(TaraGrammar.EQUALS, 0); }
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
		public List<DoubleValueContext> doubleValue() {
			return getRuleContexts(DoubleValueContext.class);
		}
		public TerminalNode RATIO_TYPE() { return getToken(TaraGrammar.RATIO_TYPE, 0); }
		public TerminalNode LIST() { return getToken(TaraGrammar.LIST, 0); }
		public RatioAttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ratioAttribute; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterRatioAttribute(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitRatioAttribute(this);
		}
	}

	public final RatioAttributeContext ratioAttribute() throws RecognitionException {
		RatioAttributeContext _localctx = new RatioAttributeContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_ratioAttribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(381); match(RATIO_TYPE);
			setState(383);
			_la = _input.LA(1);
			if (_la==LIST) {
				{
				setState(382); match(LIST);
				}
			}

			setState(385); match(IDENTIFIER);
			setState(392);
			_la = _input.LA(1);
			if (_la==EQUALS) {
				{
				setState(386); match(EQUALS);
				setState(388); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(387); doubleValue();
					}
					}
					setState(390); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NATURAL_VALUE) | (1L << NEGATIVE_VALUE) | (1L << DOUBLE_VALUE))) != 0) );
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

	public static class NaturalAttributeContext extends ParserRuleContext {
		public TerminalNode NATURAL_TYPE() { return getToken(TaraGrammar.NATURAL_TYPE, 0); }
		public List<NaturalValueContext> naturalValue() {
			return getRuleContexts(NaturalValueContext.class);
		}
		public TerminalNode EQUALS() { return getToken(TaraGrammar.EQUALS, 0); }
		public MeasureValueContext measureValue() {
			return getRuleContext(MeasureValueContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
		public NaturalValueContext naturalValue(int i) {
			return getRuleContext(NaturalValueContext.class,i);
		}
		public DoubleMeasureContext doubleMeasure() {
			return getRuleContext(DoubleMeasureContext.class,0);
		}
		public TerminalNode LIST() { return getToken(TaraGrammar.LIST, 0); }
		public NaturalAttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_naturalAttribute; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterNaturalAttribute(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitNaturalAttribute(this);
		}
	}

	public final NaturalAttributeContext naturalAttribute() throws RecognitionException {
		NaturalAttributeContext _localctx = new NaturalAttributeContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_naturalAttribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(394); match(NATURAL_TYPE);
			setState(396);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(395); doubleMeasure();
				}
			}

			setState(399);
			_la = _input.LA(1);
			if (_la==LIST) {
				{
				setState(398); match(LIST);
				}
			}

			setState(401); match(IDENTIFIER);
			setState(411);
			_la = _input.LA(1);
			if (_la==EQUALS) {
				{
				setState(402); match(EQUALS);
				setState(404); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(403); naturalValue();
					}
					}
					setState(406); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NATURAL_VALUE );
				setState(409);
				_la = _input.LA(1);
				if (_la==IDENTIFIER || _la==MEASURE_VALUE) {
					{
					setState(408); measureValue();
					}
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

	public static class IntegerAttributeContext extends ParserRuleContext {
		public TerminalNode INT_TYPE() { return getToken(TaraGrammar.INT_TYPE, 0); }
		public IntegerValueContext integerValue(int i) {
			return getRuleContext(IntegerValueContext.class,i);
		}
		public TerminalNode EQUALS() { return getToken(TaraGrammar.EQUALS, 0); }
		public MeasureValueContext measureValue() {
			return getRuleContext(MeasureValueContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
		public DoubleMeasureContext doubleMeasure() {
			return getRuleContext(DoubleMeasureContext.class,0);
		}
		public TerminalNode LIST() { return getToken(TaraGrammar.LIST, 0); }
		public List<IntegerValueContext> integerValue() {
			return getRuleContexts(IntegerValueContext.class);
		}
		public IntegerAttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_integerAttribute; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterIntegerAttribute(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitIntegerAttribute(this);
		}
	}

	public final IntegerAttributeContext integerAttribute() throws RecognitionException {
		IntegerAttributeContext _localctx = new IntegerAttributeContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_integerAttribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(413); match(INT_TYPE);
			setState(415);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(414); doubleMeasure();
				}
			}

			setState(418);
			_la = _input.LA(1);
			if (_la==LIST) {
				{
				setState(417); match(LIST);
				}
			}

			setState(420); match(IDENTIFIER);
			setState(430);
			_la = _input.LA(1);
			if (_la==EQUALS) {
				{
				setState(421); match(EQUALS);
				setState(423); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(422); integerValue();
					}
					}
					setState(425); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NATURAL_VALUE || _la==NEGATIVE_VALUE );
				setState(428);
				_la = _input.LA(1);
				if (_la==IDENTIFIER || _la==MEASURE_VALUE) {
					{
					setState(427); measureValue();
					}
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

	public static class DoubleAttributeContext extends ParserRuleContext {
		public DoubleValueContext doubleValue(int i) {
			return getRuleContext(DoubleValueContext.class,i);
		}
		public TerminalNode DOUBLE_TYPE() { return getToken(TaraGrammar.DOUBLE_TYPE, 0); }
		public CountContext count() {
			return getRuleContext(CountContext.class,0);
		}
		public TerminalNode EQUALS() { return getToken(TaraGrammar.EQUALS, 0); }
		public MeasureValueContext measureValue() {
			return getRuleContext(MeasureValueContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
		public List<DoubleValueContext> doubleValue() {
			return getRuleContexts(DoubleValueContext.class);
		}
		public DoubleMeasureContext doubleMeasure() {
			return getRuleContext(DoubleMeasureContext.class,0);
		}
		public TerminalNode LIST() { return getToken(TaraGrammar.LIST, 0); }
		public DoubleAttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_doubleAttribute; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterDoubleAttribute(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitDoubleAttribute(this);
		}
	}

	public final DoubleAttributeContext doubleAttribute() throws RecognitionException {
		DoubleAttributeContext _localctx = new DoubleAttributeContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_doubleAttribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(432); match(DOUBLE_TYPE);
			setState(435);
			switch (_input.LA(1)) {
			case LIST:
				{
				setState(433); match(LIST);
				}
				break;
			case LEFT_SQUARE:
				{
				setState(434); count();
				}
				break;
			case COLON:
			case IDENTIFIER:
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(438);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(437); doubleMeasure();
				}
			}

			setState(440); match(IDENTIFIER);
			setState(450);
			_la = _input.LA(1);
			if (_la==EQUALS) {
				{
				setState(441); match(EQUALS);
				setState(443); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(442); doubleValue();
					}
					}
					setState(445); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NATURAL_VALUE) | (1L << NEGATIVE_VALUE) | (1L << DOUBLE_VALUE))) != 0) );
				setState(448);
				_la = _input.LA(1);
				if (_la==IDENTIFIER || _la==MEASURE_VALUE) {
					{
					setState(447); measureValue();
					}
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

	public static class MeasureAttributeContext extends ParserRuleContext {
		public AttributeTypeContext attributeType() {
			return getRuleContext(AttributeTypeContext.class,0);
		}
		public DoubleValueContext doubleValue(int i) {
			return getRuleContext(DoubleValueContext.class,i);
		}
		public CountContext count() {
			return getRuleContext(CountContext.class,0);
		}
		public TerminalNode EQUALS() { return getToken(TaraGrammar.EQUALS, 0); }
		public MeasureValueContext measureValue() {
			return getRuleContext(MeasureValueContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
		public List<DoubleValueContext> doubleValue() {
			return getRuleContexts(DoubleValueContext.class);
		}
		public TerminalNode MEASURE_TYPE() { return getToken(TaraGrammar.MEASURE_TYPE, 0); }
		public TerminalNode LIST() { return getToken(TaraGrammar.LIST, 0); }
		public MeasureAttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_measureAttribute; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterMeasureAttribute(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitMeasureAttribute(this);
		}
	}

	public final MeasureAttributeContext measureAttribute() throws RecognitionException {
		MeasureAttributeContext _localctx = new MeasureAttributeContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_measureAttribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(452); match(MEASURE_TYPE);
			setState(453); attributeType();
			setState(456);
			switch (_input.LA(1)) {
			case LIST:
				{
				setState(454); match(LIST);
				}
				break;
			case LEFT_SQUARE:
				{
				setState(455); count();
				}
				break;
			case IDENTIFIER:
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(458); match(IDENTIFIER);
			setState(468);
			_la = _input.LA(1);
			if (_la==EQUALS) {
				{
				setState(459); match(EQUALS);
				setState(461); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(460); doubleValue();
					}
					}
					setState(463); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NATURAL_VALUE) | (1L << NEGATIVE_VALUE) | (1L << DOUBLE_VALUE))) != 0) );
				setState(466);
				_la = _input.LA(1);
				if (_la==IDENTIFIER || _la==MEASURE_VALUE) {
					{
					setState(465); measureValue();
					}
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

	public static class ReferenceContext extends ParserRuleContext {
		public TerminalNode EMPTY() { return getToken(TaraGrammar.EMPTY, 0); }
		public IdentifierReferenceContext identifierReference() {
			return getRuleContext(IdentifierReferenceContext.class,0);
		}
		public TerminalNode EQUALS() { return getToken(TaraGrammar.EQUALS, 0); }
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
		public TerminalNode LIST() { return getToken(TaraGrammar.LIST, 0); }
		public ReferenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_reference; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterReference(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitReference(this);
		}
	}

	public final ReferenceContext reference() throws RecognitionException {
		ReferenceContext _localctx = new ReferenceContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_reference);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(470); identifierReference();
			setState(472);
			_la = _input.LA(1);
			if (_la==LIST) {
				{
				setState(471); match(LIST);
				}
			}

			setState(474); match(IDENTIFIER);
			setState(477);
			_la = _input.LA(1);
			if (_la==EQUALS) {
				{
				setState(475); match(EQUALS);
				setState(476); match(EMPTY);
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

	public static class AttributeTypeContext extends ParserRuleContext {
		public MeasureTypeContext measureType() {
			return getRuleContext(MeasureTypeContext.class,0);
		}
		public TerminalNode COLON() { return getToken(TaraGrammar.COLON, 0); }
		public AttributeTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attributeType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterAttributeType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitAttributeType(this);
		}
	}

	public final AttributeTypeContext attributeType() throws RecognitionException {
		AttributeTypeContext _localctx = new AttributeTypeContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_attributeType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(479); match(COLON);
			setState(480); measureType();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MeasureTypeContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
		public MeasureTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_measureType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterMeasureType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitMeasureType(this);
		}
	}

	public final MeasureTypeContext measureType() throws RecognitionException {
		MeasureTypeContext _localctx = new MeasureTypeContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_measureType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(482); match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DoubleMeasureContext extends ParserRuleContext {
		public TerminalNode MEASURE_VALUE() { return getToken(TaraGrammar.MEASURE_VALUE, 0); }
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
		public TerminalNode COLON() { return getToken(TaraGrammar.COLON, 0); }
		public DoubleMeasureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_doubleMeasure; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterDoubleMeasure(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitDoubleMeasure(this);
		}
	}

	public final DoubleMeasureContext doubleMeasure() throws RecognitionException {
		DoubleMeasureContext _localctx = new DoubleMeasureContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_doubleMeasure);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(484); match(COLON);
			setState(485);
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

	public static class StringValueContext extends ParserRuleContext {
		public TerminalNode NEWLINE() { return getToken(TaraGrammar.NEWLINE, 0); }
		public TerminalNode STRING_VALUE() { return getToken(TaraGrammar.STRING_VALUE, 0); }
		public TerminalNode STRING_MULTILINE_VALUE_KEY() { return getToken(TaraGrammar.STRING_MULTILINE_VALUE_KEY, 0); }
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
		enterRule(_localctx, 66, RULE_stringValue);
		int _la;
		try {
			setState(492);
			switch (_input.LA(1)) {
			case STRING_VALUE:
				enterOuterAlt(_localctx, 1);
				{
				setState(487); match(STRING_VALUE);
				}
				break;
			case STRING_MULTILINE_VALUE_KEY:
			case NEWLINE:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(489);
				_la = _input.LA(1);
				if (_la==NEWLINE) {
					{
					setState(488); match(NEWLINE);
					}
				}

				setState(491); match(STRING_MULTILINE_VALUE_KEY);
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
		enterRule(_localctx, 68, RULE_booleanValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(494); match(BOOLEAN_VALUE);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 70, RULE_naturalValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(496); match(NATURAL_VALUE);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 72, RULE_integerValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(498);
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
		public TerminalNode SCIENCE_NOT() { return getToken(TaraGrammar.SCIENCE_NOT, 0); }
		public TerminalNode DOUBLE_VALUE() { return getToken(TaraGrammar.DOUBLE_VALUE, 0); }
		public TerminalNode NATURAL_VALUE() { return getToken(TaraGrammar.NATURAL_VALUE, 0); }
		public TerminalNode NEGATIVE_VALUE() { return getToken(TaraGrammar.NEGATIVE_VALUE, 0); }
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
		enterRule(_localctx, 74, RULE_doubleValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(500);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NATURAL_VALUE) | (1L << NEGATIVE_VALUE) | (1L << DOUBLE_VALUE))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			setState(502);
			_la = _input.LA(1);
			if (_la==SCIENCE_NOT) {
				{
				setState(501); match(SCIENCE_NOT);
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
		public IdentifierReferenceContext identifierReference() {
			return getRuleContext(IdentifierReferenceContext.class,0);
		}
		public AddressContext address() {
			return getRuleContext(AddressContext.class,0);
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
		enterRule(_localctx, 76, RULE_linkValue);
		try {
			setState(506);
			switch (_input.LA(1)) {
			case ADDRESS_VALUE:
				enterOuterAlt(_localctx, 1);
				{
				setState(504); address();
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(505); identifierReference();
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

	public static class CountContext extends ParserRuleContext {
		public TerminalNode LEFT_SQUARE() { return getToken(TaraGrammar.LEFT_SQUARE, 0); }
		public NaturalValueContext naturalValue() {
			return getRuleContext(NaturalValueContext.class,0);
		}
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
		enterRule(_localctx, 78, RULE_count);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(508); match(LEFT_SQUARE);
			setState(509); naturalValue();
			setState(510); match(RIGHT_SQUARE);
			}
		}
		catch (RecognitionException re) {
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
		public TerminalNode MEASURE_VALUE() { return getToken(TaraGrammar.MEASURE_VALUE, 0); }
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
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
		enterRule(_localctx, 80, RULE_measureValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(512);
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

	public static class AnnotationsAndFacetsContext extends ParserRuleContext {
		public FacetApplyContext facetApply() {
			return getRuleContext(FacetApplyContext.class,0);
		}
		public AnnotationsContext annotations() {
			return getRuleContext(AnnotationsContext.class,0);
		}
		public TerminalNode IS() { return getToken(TaraGrammar.IS, 0); }
		public AnnotationsAndFacetsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotationsAndFacets; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterAnnotationsAndFacets(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitAnnotationsAndFacets(this);
		}
	}

	public final AnnotationsAndFacetsContext annotationsAndFacets() throws RecognitionException {
		AnnotationsAndFacetsContext _localctx = new AnnotationsAndFacetsContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_annotationsAndFacets);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(514); match(IS);
			setState(517);
			switch (_input.LA(1)) {
			case ABSTRACT:
			case SINGLE:
			case REQUIRED:
			case COMPONENT:
			case FACET:
			case INTENTION:
			case TERMINAL:
			case NAMED:
			case PROPERTY:
			case LOCAL:
			case ADDRESSED:
			case AGGREGATED:
			case READONLY:
			case ROOT:
			case PLUS:
				{
				setState(515); annotations();
				}
				break;
			case METAIDENTIFIER:
			case IDENTIFIER:
				{
				setState(516); facetApply();
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

	public static class AnnotationsContext extends ParserRuleContext {
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
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
		enterRule(_localctx, 84, RULE_annotations);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(520); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(519); annotation();
				}
				}
				setState(522); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABSTRACT) | (1L << SINGLE) | (1L << REQUIRED) | (1L << COMPONENT) | (1L << FACET) | (1L << INTENTION) | (1L << TERMINAL) | (1L << NAMED) | (1L << PROPERTY) | (1L << LOCAL) | (1L << ADDRESSED) | (1L << AGGREGATED) | (1L << READONLY) | (1L << ROOT) | (1L << PLUS))) != 0) );
			}
		}
		catch (RecognitionException re) {
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
		public TerminalNode FACET() { return getToken(TaraGrammar.FACET, 0); }
		public TerminalNode AGGREGATED() { return getToken(TaraGrammar.AGGREGATED, 0); }
		public TerminalNode ROOT() { return getToken(TaraGrammar.ROOT, 0); }
		public TerminalNode NAMED() { return getToken(TaraGrammar.NAMED, 0); }
		public TerminalNode ABSTRACT() { return getToken(TaraGrammar.ABSTRACT, 0); }
		public TerminalNode SINGLE() { return getToken(TaraGrammar.SINGLE, 0); }
		public TerminalNode ADDRESSED() { return getToken(TaraGrammar.ADDRESSED, 0); }
		public TerminalNode TERMINAL() { return getToken(TaraGrammar.TERMINAL, 0); }
		public TerminalNode REQUIRED() { return getToken(TaraGrammar.REQUIRED, 0); }
		public TerminalNode PROPERTY() { return getToken(TaraGrammar.PROPERTY, 0); }
		public TerminalNode READONLY() { return getToken(TaraGrammar.READONLY, 0); }
		public TerminalNode INTENTION() { return getToken(TaraGrammar.INTENTION, 0); }
		public TerminalNode LOCAL() { return getToken(TaraGrammar.LOCAL, 0); }
		public TerminalNode PLUS() { return getToken(TaraGrammar.PLUS, 0); }
		public TerminalNode COMPONENT() { return getToken(TaraGrammar.COMPONENT, 0); }
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
		enterRule(_localctx, 86, RULE_annotation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(525);
			_la = _input.LA(1);
			if (_la==PLUS) {
				{
				setState(524); match(PLUS);
				}
			}

			setState(527);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABSTRACT) | (1L << SINGLE) | (1L << REQUIRED) | (1L << COMPONENT) | (1L << FACET) | (1L << INTENTION) | (1L << TERMINAL) | (1L << NAMED) | (1L << PROPERTY) | (1L << LOCAL) | (1L << ADDRESSED) | (1L << AGGREGATED) | (1L << READONLY) | (1L << ROOT))) != 0)) ) {
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
		public TerminalNode EQUALS() { return getToken(TaraGrammar.EQUALS, 0); }
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
		public InitValueContext initValue() {
			return getRuleContext(InitValueContext.class,0);
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
		enterRule(_localctx, 88, RULE_varInit);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(529); match(IDENTIFIER);
			setState(530); match(EQUALS);
			setState(531); initValue();
			}
		}
		catch (RecognitionException re) {
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
		public List<HierarchyContext> hierarchy() {
			return getRuleContexts(HierarchyContext.class);
		}
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
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
		enterRule(_localctx, 90, RULE_headerReference);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(536);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,84,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(533); hierarchy();
					}
					} 
				}
				setState(538);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,84,_ctx);
			}
			setState(539); match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
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
		public List<HierarchyContext> hierarchy() {
			return getRuleContexts(HierarchyContext.class);
		}
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
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
		enterRule(_localctx, 92, RULE_identifierReference);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(544);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,85,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(541); hierarchy();
					}
					} 
				}
				setState(546);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,85,_ctx);
			}
			setState(547); match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
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
		public TerminalNode DOT() { return getToken(TaraGrammar.DOT, 0); }
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
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
		enterRule(_localctx, 94, RULE_hierarchy);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(549); match(IDENTIFIER);
			setState(550); match(DOT);
			}
		}
		catch (RecognitionException re) {
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
		public TerminalNode METAIDENTIFIER() { return getToken(TaraGrammar.METAIDENTIFIER, 0); }
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
		enterRule(_localctx, 96, RULE_metaidentifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(552);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3H\u022d\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\3\2\7\2f\n\2\f\2"+
		"\16\2i\13\2\3\2\5\2l\n\2\3\2\3\2\7\2p\n\2\f\2\16\2s\13\2\7\2u\n\2\f\2"+
		"\16\2x\13\2\3\2\3\2\3\3\6\3}\n\3\r\3\16\3~\3\4\3\4\3\4\3\4\5\4\u0085\n"+
		"\4\3\4\3\4\3\5\6\5\u008a\n\5\r\5\16\5\u008b\3\6\5\6\u008f\n\6\3\6\3\6"+
		"\5\6\u0093\n\6\3\6\5\6\u0096\n\6\3\7\3\7\3\7\3\7\5\7\u009c\n\7\3\7\3\7"+
		"\3\7\5\7\u00a1\n\7\3\7\3\7\5\7\u00a5\n\7\5\7\u00a7\n\7\3\7\5\7\u00aa\n"+
		"\7\3\b\3\b\3\t\3\t\5\t\u00b0\n\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\7\n\u00ba"+
		"\n\n\f\n\16\n\u00bd\13\n\3\n\3\n\3\n\7\n\u00c2\n\n\f\n\16\n\u00c5\13\n"+
		"\5\n\u00c7\n\n\3\13\3\13\3\13\3\f\6\f\u00cd\n\f\r\f\16\f\u00ce\3\f\6\f"+
		"\u00d2\n\f\r\f\16\f\u00d3\3\f\6\f\u00d7\n\f\r\f\16\f\u00d8\3\f\6\f\u00dc"+
		"\n\f\r\f\16\f\u00dd\3\f\5\f\u00e1\n\f\3\f\6\f\u00e4\n\f\r\f\16\f\u00e5"+
		"\3\f\5\f\u00e9\n\f\3\f\6\f\u00ec\n\f\r\f\16\f\u00ed\3\f\5\f\u00f1\n\f"+
		"\3\f\3\f\3\f\5\f\u00f6\n\f\3\r\3\r\7\r\u00fa\n\r\f\r\16\r\u00fd\13\r\3"+
		"\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u010a\n\17"+
		"\3\17\6\17\u010d\n\17\r\17\16\17\u010e\6\17\u0111\n\17\r\17\16\17\u0112"+
		"\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\5\20\u0123\n\20\3\20\3\20\5\20\u0127\n\20\3\21\3\21\5\21\u012b\n\21\3"+
		"\21\3\21\5\21\u012f\n\21\3\21\5\21\u0132\n\21\3\22\3\22\3\22\5\22\u0137"+
		"\n\22\3\22\5\22\u013a\n\22\3\23\3\23\3\23\3\23\5\23\u0140\n\23\3\24\3"+
		"\24\5\24\u0144\n\24\3\24\3\24\3\24\3\24\3\24\6\24\u014b\n\24\r\24\16\24"+
		"\u014c\3\24\3\24\3\25\3\25\5\25\u0153\n\25\3\26\3\26\3\26\3\26\3\27\3"+
		"\27\5\27\u015b\n\27\3\27\3\27\3\27\6\27\u0160\n\27\r\27\16\27\u0161\5"+
		"\27\u0164\n\27\3\30\3\30\5\30\u0168\n\30\3\30\3\30\3\30\6\30\u016d\n\30"+
		"\r\30\16\30\u016e\5\30\u0171\n\30\3\31\3\31\5\31\u0175\n\31\3\31\3\31"+
		"\3\31\6\31\u017a\n\31\r\31\16\31\u017b\5\31\u017e\n\31\3\32\3\32\5\32"+
		"\u0182\n\32\3\32\3\32\3\32\6\32\u0187\n\32\r\32\16\32\u0188\5\32\u018b"+
		"\n\32\3\33\3\33\5\33\u018f\n\33\3\33\5\33\u0192\n\33\3\33\3\33\3\33\6"+
		"\33\u0197\n\33\r\33\16\33\u0198\3\33\5\33\u019c\n\33\5\33\u019e\n\33\3"+
		"\34\3\34\5\34\u01a2\n\34\3\34\5\34\u01a5\n\34\3\34\3\34\3\34\6\34\u01aa"+
		"\n\34\r\34\16\34\u01ab\3\34\5\34\u01af\n\34\5\34\u01b1\n\34\3\35\3\35"+
		"\3\35\5\35\u01b6\n\35\3\35\5\35\u01b9\n\35\3\35\3\35\3\35\6\35\u01be\n"+
		"\35\r\35\16\35\u01bf\3\35\5\35\u01c3\n\35\5\35\u01c5\n\35\3\36\3\36\3"+
		"\36\3\36\5\36\u01cb\n\36\3\36\3\36\3\36\6\36\u01d0\n\36\r\36\16\36\u01d1"+
		"\3\36\5\36\u01d5\n\36\5\36\u01d7\n\36\3\37\3\37\5\37\u01db\n\37\3\37\3"+
		"\37\3\37\5\37\u01e0\n\37\3 \3 \3 \3!\3!\3\"\3\"\3\"\3#\3#\5#\u01ec\n#"+
		"\3#\5#\u01ef\n#\3$\3$\3%\3%\3&\3&\3\'\3\'\5\'\u01f9\n\'\3(\3(\5(\u01fd"+
		"\n(\3)\3)\3)\3)\3*\3*\3+\3+\3+\5+\u0208\n+\3,\6,\u020b\n,\r,\16,\u020c"+
		"\3-\5-\u0210\n-\3-\3-\3.\3.\3.\3.\3/\7/\u0219\n/\f/\16/\u021c\13/\3/\3"+
		"/\3\60\7\60\u0221\n\60\f\60\16\60\u0224\13\60\3\60\3\60\3\61\3\61\3\61"+
		"\3\62\3\62\3\62\2\2\63\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,"+
		".\60\62\64\668:<>@BDFHJLNPRTVXZ\\^`b\2\7\3\2?@\3\29:\3\29;\4\2\r\26\30"+
		"\33\4\2\3\3??\u0269\2g\3\2\2\2\4|\3\2\2\2\6\u0080\3\2\2\2\b\u0089\3\2"+
		"\2\2\n\u008e\3\2\2\2\f\u00a6\3\2\2\2\16\u00ab\3\2\2\2\20\u00ad\3\2\2\2"+
		"\22\u00c6\3\2\2\2\24\u00c8\3\2\2\2\26\u00f5\3\2\2\2\30\u00f7\3\2\2\2\32"+
		"\u00fe\3\2\2\2\34\u0101\3\2\2\2\36\u0116\3\2\2\2 \u0128\3\2\2\2\"\u0133"+
		"\3\2\2\2$\u013b\3\2\2\2&\u0141\3\2\2\2(\u0150\3\2\2\2*\u0154\3\2\2\2,"+
		"\u0158\3\2\2\2.\u0165\3\2\2\2\60\u0172\3\2\2\2\62\u017f\3\2\2\2\64\u018c"+
		"\3\2\2\2\66\u019f\3\2\2\28\u01b2\3\2\2\2:\u01c6\3\2\2\2<\u01d8\3\2\2\2"+
		">\u01e1\3\2\2\2@\u01e4\3\2\2\2B\u01e6\3\2\2\2D\u01ee\3\2\2\2F\u01f0\3"+
		"\2\2\2H\u01f2\3\2\2\2J\u01f4\3\2\2\2L\u01f6\3\2\2\2N\u01fc\3\2\2\2P\u01fe"+
		"\3\2\2\2R\u0202\3\2\2\2T\u0204\3\2\2\2V\u020a\3\2\2\2X\u020f\3\2\2\2Z"+
		"\u0213\3\2\2\2\\\u021a\3\2\2\2^\u0222\3\2\2\2`\u0227\3\2\2\2b\u022a\3"+
		"\2\2\2df\7A\2\2ed\3\2\2\2fi\3\2\2\2ge\3\2\2\2gh\3\2\2\2hk\3\2\2\2ig\3"+
		"\2\2\2jl\5\4\3\2kj\3\2\2\2kl\3\2\2\2lv\3\2\2\2mq\5\n\6\2np\7A\2\2on\3"+
		"\2\2\2ps\3\2\2\2qo\3\2\2\2qr\3\2\2\2ru\3\2\2\2sq\3\2\2\2tm\3\2\2\2ux\3"+
		"\2\2\2vt\3\2\2\2vw\3\2\2\2wy\3\2\2\2xv\3\2\2\2yz\7\2\2\3z\3\3\2\2\2{}"+
		"\5\6\4\2|{\3\2\2\2}~\3\2\2\2~|\3\2\2\2~\177\3\2\2\2\177\5\3\2\2\2\u0080"+
		"\u0081\7\5\2\2\u0081\u0084\5\\/\2\u0082\u0083\7\7\2\2\u0083\u0085\7?\2"+
		"\2\u0084\u0082\3\2\2\2\u0084\u0085\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u0087"+
		"\7A\2\2\u0087\7\3\2\2\2\u0088\u008a\7C\2\2\u0089\u0088\3\2\2\2\u008a\u008b"+
		"\3\2\2\2\u008b\u0089\3\2\2\2\u008b\u008c\3\2\2\2\u008c\t\3\2\2\2\u008d"+
		"\u008f\5\b\5\2\u008e\u008d\3\2\2\2\u008e\u008f\3\2\2\2\u008f\u0090\3\2"+
		"\2\2\u0090\u0092\5\f\7\2\u0091\u0093\5T+\2\u0092\u0091\3\2\2\2\u0092\u0093"+
		"\3\2\2\2\u0093\u0095\3\2\2\2\u0094\u0096\5\34\17\2\u0095\u0094\3\2\2\2"+
		"\u0095\u0096\3\2\2\2\u0096\13\3\2\2\2\u0097\u0098\7\4\2\2\u0098\u00a7"+
		"\7?\2\2\u0099\u009b\5b\62\2\u009a\u009c\5\20\t\2\u009b\u009a\3\2\2\2\u009b"+
		"\u009c\3\2\2\2\u009c\u009d\3\2\2\2\u009d\u00a0\7?\2\2\u009e\u009f\7\f"+
		"\2\2\u009f\u00a1\5^\60\2\u00a0\u009e\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1"+
		"\u00a7\3\2\2\2\u00a2\u00a4\5b\62\2\u00a3\u00a5\5\20\t\2\u00a4\u00a3\3"+
		"\2\2\2\u00a4\u00a5\3\2\2\2\u00a5\u00a7\3\2\2\2\u00a6\u0097\3\2\2\2\u00a6"+
		"\u0099\3\2\2\2\u00a6\u00a2\3\2\2\2\u00a7\u00a9\3\2\2\2\u00a8\u00aa\5\16"+
		"\b\2\u00a9\u00a8\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa\r\3\2\2\2\u00ab\u00ac"+
		"\7>\2\2\u00ac\17\3\2\2\2\u00ad\u00af\7\34\2\2\u00ae\u00b0\5\22\n\2\u00af"+
		"\u00ae\3\2\2\2\u00af\u00b0\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1\u00b2\7\35"+
		"\2\2\u00b2\21\3\2\2\2\u00b3\u00b4\5\24\13\2\u00b4\u00bb\5\26\f\2\u00b5"+
		"\u00b6\7%\2\2\u00b6\u00b7\5\24\13\2\u00b7\u00b8\5\26\f\2\u00b8\u00ba\3"+
		"\2\2\2\u00b9\u00b5\3\2\2\2\u00ba\u00bd\3\2\2\2\u00bb\u00b9\3\2\2\2\u00bb"+
		"\u00bc\3\2\2\2\u00bc\u00c7\3\2\2\2\u00bd\u00bb\3\2\2\2\u00be\u00c3\5\26"+
		"\f\2\u00bf\u00c0\7%\2\2\u00c0\u00c2\5\26\f\2\u00c1\u00bf\3\2\2\2\u00c2"+
		"\u00c5\3\2\2\2\u00c3\u00c1\3\2\2\2\u00c3\u00c4\3\2\2\2\u00c4\u00c7\3\2"+
		"\2\2\u00c5\u00c3\3\2\2\2\u00c6\u00b3\3\2\2\2\u00c6\u00be\3\2\2\2\u00c7"+
		"\23\3\2\2\2\u00c8\u00c9\7?\2\2\u00c9\u00ca\7\'\2\2\u00ca\25\3\2\2\2\u00cb"+
		"\u00cd\5^\60\2\u00cc\u00cb\3\2\2\2\u00cd\u00ce\3\2\2\2\u00ce\u00cc\3\2"+
		"\2\2\u00ce\u00cf\3\2\2\2\u00cf\u00f6\3\2\2\2\u00d0\u00d2\5D#\2\u00d1\u00d0"+
		"\3\2\2\2\u00d2\u00d3\3\2\2\2\u00d3\u00d1\3\2\2\2\u00d3\u00d4\3\2\2\2\u00d4"+
		"\u00f6\3\2\2\2\u00d5\u00d7\5F$\2\u00d6\u00d5\3\2\2\2\u00d7\u00d8\3\2\2"+
		"\2\u00d8\u00d6\3\2\2\2\u00d8\u00d9\3\2\2\2\u00d9\u00f6\3\2\2\2\u00da\u00dc"+
		"\5H%\2\u00db\u00da\3\2\2\2\u00dc\u00dd\3\2\2\2\u00dd\u00db\3\2\2\2\u00dd"+
		"\u00de\3\2\2\2\u00de\u00e0\3\2\2\2\u00df\u00e1\5R*\2\u00e0\u00df\3\2\2"+
		"\2\u00e0\u00e1\3\2\2\2\u00e1\u00f6\3\2\2\2\u00e2\u00e4\5J&\2\u00e3\u00e2"+
		"\3\2\2\2\u00e4\u00e5\3\2\2\2\u00e5\u00e3\3\2\2\2\u00e5\u00e6\3\2\2\2\u00e6"+
		"\u00e8\3\2\2\2\u00e7\u00e9\5R*\2\u00e8\u00e7\3\2\2\2\u00e8\u00e9\3\2\2"+
		"\2\u00e9\u00f6\3\2\2\2\u00ea\u00ec\5L\'\2\u00eb\u00ea\3\2\2\2\u00ec\u00ed"+
		"\3\2\2\2\u00ed\u00eb\3\2\2\2\u00ed\u00ee\3\2\2\2\u00ee\u00f0\3\2\2\2\u00ef"+
		"\u00f1\5R*\2\u00f0\u00ef\3\2\2\2\u00f0\u00f1\3\2\2\2\u00f1\u00f6\3\2\2"+
		"\2\u00f2\u00f6\5\30\r\2\u00f3\u00f6\5N(\2\u00f4\u00f6\7\66\2\2\u00f5\u00cc"+
		"\3\2\2\2\u00f5\u00d1\3\2\2\2\u00f5\u00d6\3\2\2\2\u00f5\u00db\3\2\2\2\u00f5"+
		"\u00e3\3\2\2\2\u00f5\u00eb\3\2\2\2\u00f5\u00f2\3\2\2\2\u00f5\u00f3\3\2"+
		"\2\2\u00f5\u00f4\3\2\2\2\u00f6\27\3\2\2\2\u00f7\u00fb\5b\62\2\u00f8\u00fa"+
		"\5\32\16\2\u00f9\u00f8\3\2\2\2\u00fa\u00fd\3\2\2\2\u00fb\u00f9\3\2\2\2"+
		"\u00fb\u00fc\3\2\2\2\u00fc\31\3\2\2\2\u00fd\u00fb\3\2\2\2\u00fe\u00ff"+
		"\7&\2\2\u00ff\u0100\7?\2\2\u0100\33\3\2\2\2\u0101\u0110\7F\2\2\u0102\u010a"+
		"\5\36\20\2\u0103\u010a\5\n\6\2\u0104\u010a\5Z.\2\u0105\u010a\5T+\2\u0106"+
		"\u010a\5\"\22\2\u0107\u010a\5$\23\2\u0108\u010a\5\b\5\2\u0109\u0102\3"+
		"\2\2\2\u0109\u0103\3\2\2\2\u0109\u0104\3\2\2\2\u0109\u0105\3\2\2\2\u0109"+
		"\u0106\3\2\2\2\u0109\u0107\3\2\2\2\u0109\u0108\3\2\2\2\u010a\u010c\3\2"+
		"\2\2\u010b\u010d\7A\2\2\u010c\u010b\3\2\2\2\u010d\u010e\3\2\2\2\u010e"+
		"\u010c\3\2\2\2\u010e\u010f\3\2\2\2\u010f\u0111\3\2\2\2\u0110\u0109\3\2"+
		"\2\2\u0111\u0112\3\2\2\2\u0112\u0110\3\2\2\2\u0112\u0113\3\2\2\2\u0113"+
		"\u0114\3\2\2\2\u0114\u0115\7G\2\2\u0115\35\3\2\2\2\u0116\u0122\7\6\2\2"+
		"\u0117\u0123\5\64\33\2\u0118\u0123\5\66\34\2\u0119\u0123\58\35\2\u011a"+
		"\u0123\5\62\32\2\u011b\u0123\5:\36\2\u011c\u0123\5,\27\2\u011d\u0123\5"+
		".\30\2\u011e\u0123\5\60\31\2\u011f\u0123\5*\26\2\u0120\u0123\5<\37\2\u0121"+
		"\u0123\5&\24\2\u0122\u0117\3\2\2\2\u0122\u0118\3\2\2\2\u0122\u0119\3\2"+
		"\2\2\u0122\u011a\3\2\2\2\u0122\u011b\3\2\2\2\u0122\u011c\3\2\2\2\u0122"+
		"\u011d\3\2\2\2\u0122\u011e\3\2\2\2\u0122\u011f\3\2\2\2\u0122\u0120\3\2"+
		"\2\2\u0122\u0121\3\2\2\2\u0123\u0126\3\2\2\2\u0124\u0125\7\n\2\2\u0125"+
		"\u0127\5V,\2\u0126\u0124\3\2\2\2\u0126\u0127\3\2\2\2\u0127\37\3\2\2\2"+
		"\u0128\u012a\5b\62\2\u0129\u012b\5\20\t\2\u012a\u0129\3\2\2\2\u012a\u012b"+
		"\3\2\2\2\u012b\u012e\3\2\2\2\u012c\u012d\7\13\2\2\u012d\u012f\5b\62\2"+
		"\u012e\u012c\3\2\2\2\u012e\u012f\3\2\2\2\u012f\u0131\3\2\2\2\u0130\u0132"+
		"\5\34\17\2\u0131\u0130\3\2\2\2\u0131\u0132\3\2\2\2\u0132!\3\2\2\2\u0133"+
		"\u0134\7\t\2\2\u0134\u0136\5^\60\2\u0135\u0137\7\27\2\2\u0136\u0135\3"+
		"\2\2\2\u0136\u0137\3\2\2\2\u0137\u0139\3\2\2\2\u0138\u013a\5\34\17\2\u0139"+
		"\u0138\3\2\2\2\u0139\u013a\3\2\2\2\u013a#\3\2\2\2\u013b\u013c\7\b\2\2"+
		"\u013c\u013f\5^\60\2\u013d\u013e\7\n\2\2\u013e\u0140\5V,\2\u013f\u013d"+
		"\3\2\2\2\u013f\u0140\3\2\2\2\u0140%\3\2\2\2\u0141\u0143\7,\2\2\u0142\u0144"+
		"\7 \2\2\u0143\u0142\3\2\2\2\u0143\u0144\3\2\2\2\u0144\u0145\3\2\2\2\u0145"+
		"\u0146\7?\2\2\u0146\u014a\7F\2\2\u0147\u0148\5(\25\2\u0148\u0149\7A\2"+
		"\2\u0149\u014b\3\2\2\2\u014a\u0147\3\2\2\2\u014b\u014c\3\2\2\2\u014c\u014a"+
		"\3\2\2\2\u014c\u014d\3\2\2\2\u014d\u014e\3\2\2\2\u014e\u014f\7G\2\2\u014f"+
		"\'\3\2\2\2\u0150\u0152\7?\2\2\u0151\u0153\7*\2\2\u0152\u0151\3\2\2\2\u0152"+
		"\u0153\3\2\2\2\u0153)\3\2\2\2\u0154\u0155\7-\2\2\u0155\u0156\5> \2\u0156"+
		"\u0157\7?\2\2\u0157+\3\2\2\2\u0158\u015a\7\62\2\2\u0159\u015b\7 \2\2\u015a"+
		"\u0159\3\2\2\2\u015a\u015b\3\2\2\2\u015b\u015c\3\2\2\2\u015c\u0163\7?"+
		"\2\2\u015d\u015f\7\'\2\2\u015e\u0160\5F$\2\u015f\u015e\3\2\2\2\u0160\u0161"+
		"\3\2\2\2\u0161\u015f\3\2\2\2\u0161\u0162\3\2\2\2\u0162\u0164\3\2\2\2\u0163"+
		"\u015d\3\2\2\2\u0163\u0164\3\2\2\2\u0164-\3\2\2\2\u0165\u0167\7\61\2\2"+
		"\u0166\u0168\7 \2\2\u0167\u0166\3\2\2\2\u0167\u0168\3\2\2\2\u0168\u0169"+
		"\3\2\2\2\u0169\u0170\7?\2\2\u016a\u016c\7\'\2\2\u016b\u016d\5D#\2\u016c"+
		"\u016b\3\2\2\2\u016d\u016e\3\2\2\2\u016e\u016c\3\2\2\2\u016e\u016f\3\2"+
		"\2\2\u016f\u0171\3\2\2\2\u0170\u016a\3\2\2\2\u0170\u0171\3\2\2\2\u0171"+
		"/\3\2\2\2\u0172\u0174\7\65\2\2\u0173\u0175\7 \2\2\u0174\u0173\3\2\2\2"+
		"\u0174\u0175\3\2\2\2\u0175\u0176\3\2\2\2\u0176\u017d\7?\2\2\u0177\u0179"+
		"\7\'\2\2\u0178\u017a\5D#\2\u0179\u0178\3\2\2\2\u017a\u017b\3\2\2\2\u017b"+
		"\u0179\3\2\2\2\u017b\u017c\3\2\2\2\u017c\u017e\3\2\2\2\u017d\u0177\3\2"+
		"\2\2\u017d\u017e\3\2\2\2\u017e\61\3\2\2\2\u017f\u0181\7\64\2\2\u0180\u0182"+
		"\7 \2\2\u0181\u0180\3\2\2\2\u0181\u0182\3\2\2\2\u0182\u0183\3\2\2\2\u0183"+
		"\u018a\7?\2\2\u0184\u0186\7\'\2\2\u0185\u0187\5L\'\2\u0186\u0185\3\2\2"+
		"\2\u0187\u0188\3\2\2\2\u0188\u0186\3\2\2\2\u0188\u0189\3\2\2\2\u0189\u018b"+
		"\3\2\2\2\u018a\u0184\3\2\2\2\u018a\u018b\3\2\2\2\u018b\63\3\2\2\2\u018c"+
		"\u018e\7/\2\2\u018d\u018f\5B\"\2\u018e\u018d\3\2\2\2\u018e\u018f\3\2\2"+
		"\2\u018f\u0191\3\2\2\2\u0190\u0192\7 \2\2\u0191\u0190\3\2\2\2\u0191\u0192"+
		"\3\2\2\2\u0192\u0193\3\2\2\2\u0193\u019d\7?\2\2\u0194\u0196\7\'\2\2\u0195"+
		"\u0197\5H%\2\u0196\u0195\3\2\2\2\u0197\u0198\3\2\2\2\u0198\u0196\3\2\2"+
		"\2\u0198\u0199\3\2\2\2\u0199\u019b\3\2\2\2\u019a\u019c\5R*\2\u019b\u019a"+
		"\3\2\2\2\u019b\u019c\3\2\2\2\u019c\u019e\3\2\2\2\u019d\u0194\3\2\2\2\u019d"+
		"\u019e\3\2\2\2\u019e\65\3\2\2\2\u019f\u01a1\7.\2\2\u01a0\u01a2\5B\"\2"+
		"\u01a1\u01a0\3\2\2\2\u01a1\u01a2\3\2\2\2\u01a2\u01a4\3\2\2\2\u01a3\u01a5"+
		"\7 \2\2\u01a4\u01a3\3\2\2\2\u01a4\u01a5\3\2\2\2\u01a5\u01a6\3\2\2\2\u01a6"+
		"\u01b0\7?\2\2\u01a7\u01a9\7\'\2\2\u01a8\u01aa\5J&\2\u01a9\u01a8\3\2\2"+
		"\2\u01aa\u01ab\3\2\2\2\u01ab\u01a9\3\2\2\2\u01ab\u01ac\3\2\2\2\u01ac\u01ae"+
		"\3\2\2\2\u01ad\u01af\5R*\2\u01ae\u01ad\3\2\2\2\u01ae\u01af\3\2\2\2\u01af"+
		"\u01b1\3\2\2\2\u01b0\u01a7\3\2\2\2\u01b0\u01b1\3\2\2\2\u01b1\67\3\2\2"+
		"\2\u01b2\u01b5\7\60\2\2\u01b3\u01b6\7 \2\2\u01b4\u01b6\5P)\2\u01b5\u01b3"+
		"\3\2\2\2\u01b5\u01b4\3\2\2\2\u01b5\u01b6\3\2\2\2\u01b6\u01b8\3\2\2\2\u01b7"+
		"\u01b9\5B\"\2\u01b8\u01b7\3\2\2\2\u01b8\u01b9\3\2\2\2\u01b9\u01ba\3\2"+
		"\2\2\u01ba\u01c4\7?\2\2\u01bb\u01bd\7\'\2\2\u01bc\u01be\5L\'\2\u01bd\u01bc"+
		"\3\2\2\2\u01be\u01bf\3\2\2\2\u01bf\u01bd\3\2\2\2\u01bf\u01c0\3\2\2\2\u01c0"+
		"\u01c2\3\2\2\2\u01c1\u01c3\5R*\2\u01c2\u01c1\3\2\2\2\u01c2\u01c3\3\2\2"+
		"\2\u01c3\u01c5\3\2\2\2\u01c4\u01bb\3\2\2\2\u01c4\u01c5\3\2\2\2\u01c59"+
		"\3\2\2\2\u01c6\u01c7\7\63\2\2\u01c7\u01ca\5> \2\u01c8\u01cb\7 \2\2\u01c9"+
		"\u01cb\5P)\2\u01ca\u01c8\3\2\2\2\u01ca\u01c9\3\2\2\2\u01ca\u01cb\3\2\2"+
		"\2\u01cb\u01cc\3\2\2\2\u01cc\u01d6\7?\2\2\u01cd\u01cf\7\'\2\2\u01ce\u01d0"+
		"\5L\'\2\u01cf\u01ce\3\2\2\2\u01d0\u01d1\3\2\2\2\u01d1\u01cf\3\2\2\2\u01d1"+
		"\u01d2\3\2\2\2\u01d2\u01d4\3\2\2\2\u01d3\u01d5\5R*\2\u01d4\u01d3\3\2\2"+
		"\2\u01d4\u01d5\3\2\2\2\u01d5\u01d7\3\2\2\2\u01d6\u01cd\3\2\2\2\u01d6\u01d7"+
		"\3\2\2\2\u01d7;\3\2\2\2\u01d8\u01da\5^\60\2\u01d9\u01db\7 \2\2\u01da\u01d9"+
		"\3\2\2\2\u01da\u01db\3\2\2\2\u01db\u01dc\3\2\2\2\u01dc\u01df\7?\2\2\u01dd"+
		"\u01de\7\'\2\2\u01de\u01e0\7\66\2\2\u01df\u01dd\3\2\2\2\u01df\u01e0\3"+
		"\2\2\2\u01e0=\3\2\2\2\u01e1\u01e2\7$\2\2\u01e2\u01e3\5@!\2\u01e3?\3\2"+
		"\2\2\u01e4\u01e5\7?\2\2\u01e5A\3\2\2\2\u01e6\u01e7\7$\2\2\u01e7\u01e8"+
		"\t\2\2\2\u01e8C\3\2\2\2\u01e9\u01ef\7<\2\2\u01ea\u01ec\7A\2\2\u01eb\u01ea"+
		"\3\2\2\2\u01eb\u01ec\3\2\2\2\u01ec\u01ed\3\2\2\2\u01ed\u01ef\7=\2\2\u01ee"+
		"\u01e9\3\2\2\2\u01ee\u01eb\3\2\2\2\u01efE\3\2\2\2\u01f0\u01f1\78\2\2\u01f1"+
		"G\3\2\2\2\u01f2\u01f3\79\2\2\u01f3I\3\2\2\2\u01f4\u01f5\t\3\2\2\u01f5"+
		"K\3\2\2\2\u01f6\u01f8\t\4\2\2\u01f7\u01f9\7\67\2\2\u01f8\u01f7\3\2\2\2"+
		"\u01f8\u01f9\3\2\2\2\u01f9M\3\2\2\2\u01fa\u01fd\5\16\b\2\u01fb\u01fd\5"+
		"^\60\2\u01fc\u01fa\3\2\2\2\u01fc\u01fb\3\2\2\2\u01fdO\3\2\2\2\u01fe\u01ff"+
		"\7\36\2\2\u01ff\u0200\5H%\2\u0200\u0201\7\37\2\2\u0201Q\3\2\2\2\u0202"+
		"\u0203\t\2\2\2\u0203S\3\2\2\2\u0204\u0207\7\n\2\2\u0205\u0208\5V,\2\u0206"+
		"\u0208\5 \21\2\u0207\u0205\3\2\2\2\u0207\u0206\3\2\2\2\u0208U\3\2\2\2"+
		"\u0209\u020b\5X-\2\u020a\u0209\3\2\2\2\u020b\u020c\3\2\2\2\u020c\u020a"+
		"\3\2\2\2\u020c\u020d\3\2\2\2\u020dW\3\2\2\2\u020e\u0210\7+\2\2\u020f\u020e"+
		"\3\2\2\2\u020f\u0210\3\2\2\2\u0210\u0211\3\2\2\2\u0211\u0212\t\5\2\2\u0212"+
		"Y\3\2\2\2\u0213\u0214\7?\2\2\u0214\u0215\7\'\2\2\u0215\u0216\5\26\f\2"+
		"\u0216[\3\2\2\2\u0217\u0219\5`\61\2\u0218\u0217\3\2\2\2\u0219\u021c\3"+
		"\2\2\2\u021a\u0218\3\2\2\2\u021a\u021b\3\2\2\2\u021b\u021d\3\2\2\2\u021c"+
		"\u021a\3\2\2\2\u021d\u021e\7?\2\2\u021e]\3\2\2\2\u021f\u0221\5`\61\2\u0220"+
		"\u021f\3\2\2\2\u0221\u0224\3\2\2\2\u0222\u0220\3\2\2\2\u0222\u0223\3\2"+
		"\2\2\u0223\u0225\3\2\2\2\u0224\u0222\3\2\2\2\u0225\u0226\7?\2\2\u0226"+
		"_\3\2\2\2\u0227\u0228\7?\2\2\u0228\u0229\7&\2\2\u0229a\3\2\2\2\u022a\u022b"+
		"\t\6\2\2\u022bc\3\2\2\2Xgkqv~\u0084\u008b\u008e\u0092\u0095\u009b\u00a0"+
		"\u00a4\u00a6\u00a9\u00af\u00bb\u00c3\u00c6\u00ce\u00d3\u00d8\u00dd\u00e0"+
		"\u00e5\u00e8\u00ed\u00f0\u00f5\u00fb\u0109\u010e\u0112\u0122\u0126\u012a"+
		"\u012e\u0131\u0136\u0139\u013f\u0143\u014c\u0152\u015a\u0161\u0163\u0167"+
		"\u016e\u0170\u0174\u017b\u017d\u0181\u0188\u018a\u018e\u0191\u0198\u019b"+
		"\u019d\u01a1\u01a4\u01ab\u01ae\u01b0\u01b5\u01b8\u01bf\u01c2\u01c4\u01ca"+
		"\u01d1\u01d4\u01d6\u01da\u01df\u01eb\u01ee\u01f8\u01fc\u0207\u020c\u020f"+
		"\u021a\u0222";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}