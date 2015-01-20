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
	static { RuntimeMetaData.checkVersion("4.4.1-dev", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		MEASURE_VALUE=63, FACET=15, RIGHT_SQUARE=30, STAR=41, NEGATIVE_VALUE=57, 
		DOC=66, CASE=25, SUB=2, DEDENT=70, EQUALS=38, ABSTRACT=11, BOOLEAN_VALUE=55, 
		METAIDENTIFIER=1, RATIO_TYPE=51, RESOURCE=44, ADDRESS_VALUE=61, UNKNOWN_TOKEN=71, 
		WORD=43, AS=5, HAS=6, RIGHT_PARENTHESIS=28, ALWAYS=21, COMMA=36, IS=8, 
		IDENTIFIER=62, LEFT_PARENTHESIS=27, STRING_MULTILINE_VALUE_KEY=60, PLUS=42, 
		VAR=4, NL=68, INTENTION=16, DOT=37, COMPONENT=14, STRING_VALUE=59, DOUBLE_VALUE=58, 
		WITH=9, NEW_LINE_INDENT=69, AGGREGATED=23, ADDRESSED=22, TERMINAL=17, 
		NATURAL_TYPE=46, ON=7, AMPERSAND=34, CLOSE_INLINE=33, BOOLEAN_TYPE=49, 
		DOUBLE_TYPE=47, SEMICOLON=40, MEASURE_TYPE=50, SCIENCE_NOT=54, LOCAL=20, 
		REQUIRED=13, INT_TYPE=45, READONLY=24, LIST=31, ROOT=26, EMPTY=53, COLON=35, 
		NATURAL_VALUE=56, NEWLINE=64, SINGLE=12, PROPERTY=19, SPACES=65, SP=67, 
		APHOSTROPHE=39, NAMED=18, STRING_TYPE=48, DATE_TYPE=52, INLINE=32, USE=3, 
		EXTENDS=10, LEFT_SQUARE=29;
	public static final String[] tokenNames = {
		"<INVALID>", "'Concept'", "'sub'", "'use'", "'var'", "'as'", "'has'", 
		"'on'", "'is'", "'with'", "'extends'", "'abstract'", "'single'", "'required'", 
		"'component'", "'facet'", "'intention'", "'terminal'", "'named'", "'property'", 
		"'local'", "'always'", "'addressed'", "'aggregated'", "'readonly'", "'case'", 
		"'root'", "'('", "')'", "'['", "']'", "'...'", "'>'", "'<'", "'&'", "':'", 
		"','", "'.'", "'='", "'\"'", "SEMICOLON", "'*'", "'+'", "'word'", "'file'", 
		"'integer'", "'natural'", "'double'", "'string'", "'boolean'", "'measure'", 
		"'ratio'", "'date'", "'empty'", "SCIENCE_NOT", "BOOLEAN_VALUE", "NATURAL_VALUE", 
		"NEGATIVE_VALUE", "DOUBLE_VALUE", "STRING_VALUE", "STRING_MULTILINE_VALUE_KEY", 
		"ADDRESS_VALUE", "IDENTIFIER", "MEASURE_VALUE", "NEWLINE", "SPACES", "DOC", 
		"SP", "NL", "'indent'", "'dedent'", "UNKNOWN_TOKEN"
	};
	public static final int
		RULE_root = 0, RULE_imports = 1, RULE_anImport = 2, RULE_doc = 3, RULE_concept = 4, 
		RULE_signature = 5, RULE_parent = 6, RULE_address = 7, RULE_parameters = 8, 
		RULE_parameterList = 9, RULE_explicit = 10, RULE_initValue = 11, RULE_metaWord = 12, 
		RULE_metaWordNames = 13, RULE_body = 14, RULE_variable = 15, RULE_facetApply = 16, 
		RULE_facetTarget = 17, RULE_conceptReference = 18, RULE_word = 19, RULE_wordNames = 20, 
		RULE_resource = 21, RULE_booleanAttribute = 22, RULE_stringAttribute = 23, 
		RULE_dateAttribute = 24, RULE_ratioAttribute = 25, RULE_naturalAttribute = 26, 
		RULE_integerAttribute = 27, RULE_doubleAttribute = 28, RULE_measureAttribute = 29, 
		RULE_reference = 30, RULE_attributeType = 31, RULE_measureType = 32, RULE_doubleMeasure = 33, 
		RULE_stringValue = 34, RULE_booleanValue = 35, RULE_naturalValue = 36, 
		RULE_integerValue = 37, RULE_doubleValue = 38, RULE_linkValue = 39, RULE_count = 40, 
		RULE_measureValue = 41, RULE_annotationsAndFacets = 42, RULE_annotations = 43, 
		RULE_annotation = 44, RULE_varInit = 45, RULE_headerReference = 46, RULE_identifierReference = 47, 
		RULE_hierarchy = 48, RULE_metaidentifier = 49;
	public static final String[] ruleNames = {
		"root", "imports", "anImport", "doc", "concept", "signature", "parent", 
		"address", "parameters", "parameterList", "explicit", "initValue", "metaWord", 
		"metaWordNames", "body", "variable", "facetApply", "facetTarget", "conceptReference", 
		"word", "wordNames", "resource", "booleanAttribute", "stringAttribute", 
		"dateAttribute", "ratioAttribute", "naturalAttribute", "integerAttribute", 
		"doubleAttribute", "measureAttribute", "reference", "attributeType", "measureType", 
		"doubleMeasure", "stringValue", "booleanValue", "naturalValue", "integerValue", 
		"doubleValue", "linkValue", "count", "measureValue", "annotationsAndFacets", 
		"annotations", "annotation", "varInit", "headerReference", "identifierReference", 
		"hierarchy", "metaidentifier"
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
			setState(103);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(100); match(NEWLINE);
				}
				}
				setState(105);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(107);
			_la = _input.LA(1);
			if (_la==USE) {
				{
				setState(106); imports();
				}
			}

			setState(118);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << METAIDENTIFIER) | (1L << SUB) | (1L << IDENTIFIER))) != 0) || _la==DOC) {
				{
				{
				setState(109); concept();
				setState(113);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NEWLINE) {
					{
					{
					setState(110); match(NEWLINE);
					}
					}
					setState(115);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				}
				setState(120);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(121); match(EOF);
			}
		}
		catch (RecognitionException re) {
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
			setState(124); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(123); anImport();
				}
				}
				setState(126); 
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
			setState(128); match(USE);
			setState(129); headerReference();
			setState(132);
			_la = _input.LA(1);
			if (_la==AS) {
				{
				setState(130); match(AS);
				setState(131); match(IDENTIFIER);
				}
			}

			setState(134); match(NEWLINE);
			}
		}
		catch (RecognitionException re) {
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
			setState(137); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(136); match(DOC);
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
			setState(142);
			_la = _input.LA(1);
			if (_la==DOC) {
				{
				setState(141); doc();
				}
			}

			setState(144); signature();
			setState(146);
			_la = _input.LA(1);
			if (_la==IS) {
				{
				setState(145); annotationsAndFacets();
				}
			}

			setState(149);
			_la = _input.LA(1);
			if (_la==NEW_LINE_INDENT) {
				{
				setState(148); body();
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
		public ParentContext parent() {
			return getRuleContext(ParentContext.class,0);
		}
		public MetaidentifierContext metaidentifier() {
			return getRuleContext(MetaidentifierContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
		public ParametersContext parameters() {
			return getRuleContext(ParametersContext.class,0);
		}
		public AddressContext address() {
			return getRuleContext(AddressContext.class,0);
		}
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
			setState(166);
			switch (_input.LA(1)) {
			case SUB:
				{
				{
				setState(151); match(SUB);
				setState(153);
				_la = _input.LA(1);
				if (_la==LEFT_PARENTHESIS) {
					{
					setState(152); parameters();
					}
				}

				setState(155); match(IDENTIFIER);
				}
				}
				break;
			case METAIDENTIFIER:
			case IDENTIFIER:
				{
				{
				setState(156); metaidentifier();
				setState(158);
				_la = _input.LA(1);
				if (_la==LEFT_PARENTHESIS) {
					{
					setState(157); parameters();
					}
				}

				setState(161);
				switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
				case 1:
					{
					setState(160); match(IDENTIFIER);
					}
					break;
				}
				setState(164);
				_la = _input.LA(1);
				if (_la==EXTENDS) {
					{
					setState(163); parent();
					}
				}

				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(169);
			_la = _input.LA(1);
			if (_la==ADDRESS_VALUE) {
				{
				setState(168); address();
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
		public IdentifierReferenceContext identifierReference() {
			return getRuleContext(IdentifierReferenceContext.class,0);
		}
		public TerminalNode EXTENDS() { return getToken(TaraGrammar.EXTENDS, 0); }
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
		enterRule(_localctx, 12, RULE_parent);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(171); match(EXTENDS);
			setState(172); identifierReference();
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 14, RULE_address);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(174); match(ADDRESS_VALUE);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 16, RULE_parameters);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(176); match(LEFT_PARENTHESIS);
			setState(178);
			_la = _input.LA(1);
			if (((((_la - 1)) & ~0x3f) == 0 && ((1L << (_la - 1)) & ((1L << (METAIDENTIFIER - 1)) | (1L << (EMPTY - 1)) | (1L << (BOOLEAN_VALUE - 1)) | (1L << (NATURAL_VALUE - 1)) | (1L << (NEGATIVE_VALUE - 1)) | (1L << (DOUBLE_VALUE - 1)) | (1L << (STRING_VALUE - 1)) | (1L << (STRING_MULTILINE_VALUE_KEY - 1)) | (1L << (ADDRESS_VALUE - 1)) | (1L << (IDENTIFIER - 1)) | (1L << (NEWLINE - 1)))) != 0)) {
				{
				setState(177); parameterList();
				}
			}

			setState(180); match(RIGHT_PARENTHESIS);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 18, RULE_parameterList);
		int _la;
		try {
			setState(201);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(182); explicit();
				setState(183); initValue();
				setState(190);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(184); match(COMMA);
					setState(185); explicit();
					setState(186); initValue();
					}
					}
					setState(192);
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
				setState(193); initValue();
				setState(198);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(194); match(COMMA);
					setState(195); initValue();
					}
					}
					setState(200);
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
		enterRule(_localctx, 20, RULE_explicit);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(203); match(IDENTIFIER);
			setState(204); match(EQUALS);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 22, RULE_initValue);
		int _la;
		try {
			int _alt;
			setState(248);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(207); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(206); identifierReference();
					}
					}
					setState(209); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==IDENTIFIER );
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(212); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(211); stringValue();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(214); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(217); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(216); booleanValue();
					}
					}
					setState(219); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==BOOLEAN_VALUE );
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(222); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(221); naturalValue();
					}
					}
					setState(224); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NATURAL_VALUE );
				setState(227);
				_la = _input.LA(1);
				if (_la==IDENTIFIER || _la==MEASURE_VALUE) {
					{
					setState(226); measureValue();
					}
				}

				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(230); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(229); integerValue();
					}
					}
					setState(232); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NATURAL_VALUE || _la==NEGATIVE_VALUE );
				setState(235);
				_la = _input.LA(1);
				if (_la==IDENTIFIER || _la==MEASURE_VALUE) {
					{
					setState(234); measureValue();
					}
				}

				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(238); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(237); doubleValue();
					}
					}
					setState(240); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NATURAL_VALUE) | (1L << NEGATIVE_VALUE) | (1L << DOUBLE_VALUE))) != 0) );
				setState(243);
				_la = _input.LA(1);
				if (_la==IDENTIFIER || _la==MEASURE_VALUE) {
					{
					setState(242); measureValue();
					}
				}

				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(245); metaWord();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(246); linkValue();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(247); match(EMPTY);
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
		enterRule(_localctx, 24, RULE_metaWord);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(250); metaidentifier();
			setState(254);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DOT) {
				{
				{
				setState(251); metaWordNames();
				}
				}
				setState(256);
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
		enterRule(_localctx, 26, RULE_metaWordNames);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(257); match(DOT);
			setState(258); match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 28, RULE_body);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(260); match(NEW_LINE_INDENT);
			setState(275); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(268);
				switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
				case 1:
					{
					setState(261); variable();
					}
					break;
				case 2:
					{
					setState(262); concept();
					}
					break;
				case 3:
					{
					setState(263); varInit();
					}
					break;
				case 4:
					{
					setState(264); annotationsAndFacets();
					}
					break;
				case 5:
					{
					setState(265); facetTarget();
					}
					break;
				case 6:
					{
					setState(266); conceptReference();
					}
					break;
				case 7:
					{
					setState(267); doc();
					}
					break;
				}
				setState(271); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(270); match(NEWLINE);
					}
					}
					setState(273); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NEWLINE );
				}
				}
				setState(277); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << METAIDENTIFIER) | (1L << SUB) | (1L << VAR) | (1L << HAS) | (1L << ON) | (1L << IS) | (1L << IDENTIFIER))) != 0) || _la==DOC );
			setState(279); match(DEDENT);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 30, RULE_variable);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(281); match(VAR);
			setState(293);
			switch (_input.LA(1)) {
			case NATURAL_TYPE:
				{
				setState(282); naturalAttribute();
				}
				break;
			case INT_TYPE:
				{
				setState(283); integerAttribute();
				}
				break;
			case DOUBLE_TYPE:
				{
				setState(284); doubleAttribute();
				}
				break;
			case RATIO_TYPE:
				{
				setState(285); ratioAttribute();
				}
				break;
			case MEASURE_TYPE:
				{
				setState(286); measureAttribute();
				}
				break;
			case BOOLEAN_TYPE:
				{
				setState(287); booleanAttribute();
				}
				break;
			case STRING_TYPE:
				{
				setState(288); stringAttribute();
				}
				break;
			case DATE_TYPE:
				{
				setState(289); dateAttribute();
				}
				break;
			case RESOURCE:
				{
				setState(290); resource();
				}
				break;
			case IDENTIFIER:
				{
				setState(291); reference();
				}
				break;
			case WORD:
				{
				setState(292); word();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(297);
			_la = _input.LA(1);
			if (_la==IS) {
				{
				setState(295); match(IS);
				setState(296); annotations();
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
		enterRule(_localctx, 32, RULE_facetApply);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(299); metaidentifier();
			setState(301);
			_la = _input.LA(1);
			if (_la==LEFT_PARENTHESIS) {
				{
				setState(300); parameters();
				}
			}

			setState(305);
			_la = _input.LA(1);
			if (_la==WITH) {
				{
				setState(303); match(WITH);
				setState(304); metaidentifier();
				}
			}

			setState(308);
			switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
			case 1:
				{
				setState(307); body();
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
		enterRule(_localctx, 34, RULE_facetTarget);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(310); match(ON);
			setState(311); identifierReference();
			setState(313);
			_la = _input.LA(1);
			if (_la==ALWAYS) {
				{
				setState(312); match(ALWAYS);
				}
			}

			setState(316);
			_la = _input.LA(1);
			if (_la==NEW_LINE_INDENT) {
				{
				setState(315); body();
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
		enterRule(_localctx, 36, RULE_conceptReference);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(318); match(HAS);
			setState(319); identifierReference();
			setState(322);
			_la = _input.LA(1);
			if (_la==IS) {
				{
				setState(320); match(IS);
				setState(321); annotations();
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
		enterRule(_localctx, 38, RULE_word);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(324); match(WORD);
			setState(326);
			_la = _input.LA(1);
			if (_la==LIST) {
				{
				setState(325); match(LIST);
				}
			}

			setState(328); match(IDENTIFIER);
			setState(329); match(NEW_LINE_INDENT);
			setState(333); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(330); wordNames();
				setState(331); match(NEWLINE);
				}
				}
				setState(335); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==IDENTIFIER );
			setState(337); match(DEDENT);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 40, RULE_wordNames);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(339); match(IDENTIFIER);
			setState(341);
			_la = _input.LA(1);
			if (_la==STAR) {
				{
				setState(340); match(STAR);
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
		enterRule(_localctx, 42, RULE_resource);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(343); match(RESOURCE);
			setState(344); attributeType();
			setState(345); match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 44, RULE_booleanAttribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(347); match(BOOLEAN_TYPE);
			setState(349);
			_la = _input.LA(1);
			if (_la==LIST) {
				{
				setState(348); match(LIST);
				}
			}

			setState(351); match(IDENTIFIER);
			setState(358);
			_la = _input.LA(1);
			if (_la==EQUALS) {
				{
				setState(352); match(EQUALS);
				setState(354); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(353); booleanValue();
					}
					}
					setState(356); 
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
		enterRule(_localctx, 46, RULE_stringAttribute);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(360); match(STRING_TYPE);
			setState(362);
			_la = _input.LA(1);
			if (_la==LIST) {
				{
				setState(361); match(LIST);
				}
			}

			setState(364); match(IDENTIFIER);
			setState(371);
			_la = _input.LA(1);
			if (_la==EQUALS) {
				{
				setState(365); match(EQUALS);
				setState(367); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(366); stringValue();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(369); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,49,_ctx);
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
		enterRule(_localctx, 48, RULE_dateAttribute);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(373); match(DATE_TYPE);
			setState(375);
			_la = _input.LA(1);
			if (_la==LIST) {
				{
				setState(374); match(LIST);
				}
			}

			setState(377); match(IDENTIFIER);
			setState(384);
			_la = _input.LA(1);
			if (_la==EQUALS) {
				{
				setState(378); match(EQUALS);
				setState(380); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(379); stringValue();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(382); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,52,_ctx);
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
		enterRule(_localctx, 50, RULE_ratioAttribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(386); match(RATIO_TYPE);
			setState(388);
			_la = _input.LA(1);
			if (_la==LIST) {
				{
				setState(387); match(LIST);
				}
			}

			setState(390); match(IDENTIFIER);
			setState(397);
			_la = _input.LA(1);
			if (_la==EQUALS) {
				{
				setState(391); match(EQUALS);
				setState(393); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(392); doubleValue();
					}
					}
					setState(395); 
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
		enterRule(_localctx, 52, RULE_naturalAttribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(399); match(NATURAL_TYPE);
			setState(401);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(400); doubleMeasure();
				}
			}

			setState(404);
			_la = _input.LA(1);
			if (_la==LIST) {
				{
				setState(403); match(LIST);
				}
			}

			setState(406); match(IDENTIFIER);
			setState(416);
			_la = _input.LA(1);
			if (_la==EQUALS) {
				{
				setState(407); match(EQUALS);
				setState(409); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(408); naturalValue();
					}
					}
					setState(411); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NATURAL_VALUE );
				setState(414);
				_la = _input.LA(1);
				if (_la==IDENTIFIER || _la==MEASURE_VALUE) {
					{
					setState(413); measureValue();
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
		enterRule(_localctx, 54, RULE_integerAttribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(418); match(INT_TYPE);
			setState(420);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(419); doubleMeasure();
				}
			}

			setState(423);
			_la = _input.LA(1);
			if (_la==LIST) {
				{
				setState(422); match(LIST);
				}
			}

			setState(425); match(IDENTIFIER);
			setState(435);
			_la = _input.LA(1);
			if (_la==EQUALS) {
				{
				setState(426); match(EQUALS);
				setState(428); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(427); integerValue();
					}
					}
					setState(430); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NATURAL_VALUE || _la==NEGATIVE_VALUE );
				setState(433);
				_la = _input.LA(1);
				if (_la==IDENTIFIER || _la==MEASURE_VALUE) {
					{
					setState(432); measureValue();
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
		enterRule(_localctx, 56, RULE_doubleAttribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(437); match(DOUBLE_TYPE);
			setState(440);
			switch (_input.LA(1)) {
			case LIST:
				{
				setState(438); match(LIST);
				}
				break;
			case LEFT_SQUARE:
				{
				setState(439); count();
				}
				break;
			case COLON:
			case IDENTIFIER:
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(443);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(442); doubleMeasure();
				}
			}

			setState(445); match(IDENTIFIER);
			setState(455);
			_la = _input.LA(1);
			if (_la==EQUALS) {
				{
				setState(446); match(EQUALS);
				setState(448); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(447); doubleValue();
					}
					}
					setState(450); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NATURAL_VALUE) | (1L << NEGATIVE_VALUE) | (1L << DOUBLE_VALUE))) != 0) );
				setState(453);
				_la = _input.LA(1);
				if (_la==IDENTIFIER || _la==MEASURE_VALUE) {
					{
					setState(452); measureValue();
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
		enterRule(_localctx, 58, RULE_measureAttribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(457); match(MEASURE_TYPE);
			setState(458); attributeType();
			setState(461);
			switch (_input.LA(1)) {
			case LIST:
				{
				setState(459); match(LIST);
				}
				break;
			case LEFT_SQUARE:
				{
				setState(460); count();
				}
				break;
			case IDENTIFIER:
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(463); match(IDENTIFIER);
			setState(473);
			_la = _input.LA(1);
			if (_la==EQUALS) {
				{
				setState(464); match(EQUALS);
				setState(466); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(465); doubleValue();
					}
					}
					setState(468); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NATURAL_VALUE) | (1L << NEGATIVE_VALUE) | (1L << DOUBLE_VALUE))) != 0) );
				setState(471);
				_la = _input.LA(1);
				if (_la==IDENTIFIER || _la==MEASURE_VALUE) {
					{
					setState(470); measureValue();
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
		enterRule(_localctx, 60, RULE_reference);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(475); identifierReference();
			setState(477);
			_la = _input.LA(1);
			if (_la==LIST) {
				{
				setState(476); match(LIST);
				}
			}

			setState(479); match(IDENTIFIER);
			setState(482);
			_la = _input.LA(1);
			if (_la==EQUALS) {
				{
				setState(480); match(EQUALS);
				setState(481); match(EMPTY);
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
		enterRule(_localctx, 62, RULE_attributeType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(484); match(COLON);
			setState(485); measureType();
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 64, RULE_measureType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(487); match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 66, RULE_doubleMeasure);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(489); match(COLON);
			setState(490);
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
		enterRule(_localctx, 68, RULE_stringValue);
		int _la;
		try {
			setState(497);
			switch (_input.LA(1)) {
			case STRING_VALUE:
				enterOuterAlt(_localctx, 1);
				{
				setState(492); match(STRING_VALUE);
				}
				break;
			case STRING_MULTILINE_VALUE_KEY:
			case NEWLINE:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(494);
				_la = _input.LA(1);
				if (_la==NEWLINE) {
					{
					setState(493); match(NEWLINE);
					}
				}

				setState(496); match(STRING_MULTILINE_VALUE_KEY);
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
		enterRule(_localctx, 70, RULE_booleanValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(499); match(BOOLEAN_VALUE);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 72, RULE_naturalValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(501); match(NATURAL_VALUE);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 74, RULE_integerValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(503);
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
		enterRule(_localctx, 76, RULE_doubleValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(505);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NATURAL_VALUE) | (1L << NEGATIVE_VALUE) | (1L << DOUBLE_VALUE))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			setState(507);
			_la = _input.LA(1);
			if (_la==SCIENCE_NOT) {
				{
				setState(506); match(SCIENCE_NOT);
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
		enterRule(_localctx, 78, RULE_linkValue);
		try {
			setState(511);
			switch (_input.LA(1)) {
			case ADDRESS_VALUE:
				enterOuterAlt(_localctx, 1);
				{
				setState(509); address();
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(510); identifierReference();
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
		enterRule(_localctx, 80, RULE_count);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(513); match(LEFT_SQUARE);
			setState(514); naturalValue();
			setState(515); match(RIGHT_SQUARE);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 82, RULE_measureValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(517);
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
		enterRule(_localctx, 84, RULE_annotationsAndFacets);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(519); match(IS);
			setState(522);
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
			case CASE:
			case ROOT:
			case PLUS:
				{
				setState(520); annotations();
				}
				break;
			case METAIDENTIFIER:
			case IDENTIFIER:
				{
				setState(521); facetApply();
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
		enterRule(_localctx, 86, RULE_annotations);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(525); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(524); annotation();
				}
				}
				setState(527); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABSTRACT) | (1L << SINGLE) | (1L << REQUIRED) | (1L << COMPONENT) | (1L << FACET) | (1L << INTENTION) | (1L << TERMINAL) | (1L << NAMED) | (1L << PROPERTY) | (1L << LOCAL) | (1L << ADDRESSED) | (1L << AGGREGATED) | (1L << READONLY) | (1L << CASE) | (1L << ROOT) | (1L << PLUS))) != 0) );
			}
		}
		catch (RecognitionException re) {
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
		public TerminalNode CASE() { return getToken(TaraGrammar.CASE, 0); }
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
		enterRule(_localctx, 88, RULE_annotation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(530);
			_la = _input.LA(1);
			if (_la==PLUS) {
				{
				setState(529); match(PLUS);
				}
			}

			setState(532);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABSTRACT) | (1L << SINGLE) | (1L << REQUIRED) | (1L << COMPONENT) | (1L << FACET) | (1L << INTENTION) | (1L << TERMINAL) | (1L << NAMED) | (1L << PROPERTY) | (1L << LOCAL) | (1L << ADDRESSED) | (1L << AGGREGATED) | (1L << READONLY) | (1L << CASE) | (1L << ROOT))) != 0)) ) {
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
		enterRule(_localctx, 90, RULE_varInit);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(534); match(IDENTIFIER);
			setState(535); match(EQUALS);
			setState(536); initValue();
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 92, RULE_headerReference);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(541);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,85,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(538); hierarchy();
					}
					} 
				}
				setState(543);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,85,_ctx);
			}
			setState(544); match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 94, RULE_identifierReference);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(549);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,86,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(546); hierarchy();
					}
					} 
				}
				setState(551);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,86,_ctx);
			}
			setState(552); match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 96, RULE_hierarchy);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(554); match(IDENTIFIER);
			setState(555); match(DOT);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 98, RULE_metaidentifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(557);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3I\u0232\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\3\2\7\2"+
		"h\n\2\f\2\16\2k\13\2\3\2\5\2n\n\2\3\2\3\2\7\2r\n\2\f\2\16\2u\13\2\7\2"+
		"w\n\2\f\2\16\2z\13\2\3\2\3\2\3\3\6\3\177\n\3\r\3\16\3\u0080\3\4\3\4\3"+
		"\4\3\4\5\4\u0087\n\4\3\4\3\4\3\5\6\5\u008c\n\5\r\5\16\5\u008d\3\6\5\6"+
		"\u0091\n\6\3\6\3\6\5\6\u0095\n\6\3\6\5\6\u0098\n\6\3\7\3\7\5\7\u009c\n"+
		"\7\3\7\3\7\3\7\5\7\u00a1\n\7\3\7\5\7\u00a4\n\7\3\7\5\7\u00a7\n\7\5\7\u00a9"+
		"\n\7\3\7\5\7\u00ac\n\7\3\b\3\b\3\b\3\t\3\t\3\n\3\n\5\n\u00b5\n\n\3\n\3"+
		"\n\3\13\3\13\3\13\3\13\3\13\3\13\7\13\u00bf\n\13\f\13\16\13\u00c2\13\13"+
		"\3\13\3\13\3\13\7\13\u00c7\n\13\f\13\16\13\u00ca\13\13\5\13\u00cc\n\13"+
		"\3\f\3\f\3\f\3\r\6\r\u00d2\n\r\r\r\16\r\u00d3\3\r\6\r\u00d7\n\r\r\r\16"+
		"\r\u00d8\3\r\6\r\u00dc\n\r\r\r\16\r\u00dd\3\r\6\r\u00e1\n\r\r\r\16\r\u00e2"+
		"\3\r\5\r\u00e6\n\r\3\r\6\r\u00e9\n\r\r\r\16\r\u00ea\3\r\5\r\u00ee\n\r"+
		"\3\r\6\r\u00f1\n\r\r\r\16\r\u00f2\3\r\5\r\u00f6\n\r\3\r\3\r\3\r\5\r\u00fb"+
		"\n\r\3\16\3\16\7\16\u00ff\n\16\f\16\16\16\u0102\13\16\3\17\3\17\3\17\3"+
		"\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\5\20\u010f\n\20\3\20\6\20\u0112"+
		"\n\20\r\20\16\20\u0113\6\20\u0116\n\20\r\20\16\20\u0117\3\20\3\20\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u0128\n\21"+
		"\3\21\3\21\5\21\u012c\n\21\3\22\3\22\5\22\u0130\n\22\3\22\3\22\5\22\u0134"+
		"\n\22\3\22\5\22\u0137\n\22\3\23\3\23\3\23\5\23\u013c\n\23\3\23\5\23\u013f"+
		"\n\23\3\24\3\24\3\24\3\24\5\24\u0145\n\24\3\25\3\25\5\25\u0149\n\25\3"+
		"\25\3\25\3\25\3\25\3\25\6\25\u0150\n\25\r\25\16\25\u0151\3\25\3\25\3\26"+
		"\3\26\5\26\u0158\n\26\3\27\3\27\3\27\3\27\3\30\3\30\5\30\u0160\n\30\3"+
		"\30\3\30\3\30\6\30\u0165\n\30\r\30\16\30\u0166\5\30\u0169\n\30\3\31\3"+
		"\31\5\31\u016d\n\31\3\31\3\31\3\31\6\31\u0172\n\31\r\31\16\31\u0173\5"+
		"\31\u0176\n\31\3\32\3\32\5\32\u017a\n\32\3\32\3\32\3\32\6\32\u017f\n\32"+
		"\r\32\16\32\u0180\5\32\u0183\n\32\3\33\3\33\5\33\u0187\n\33\3\33\3\33"+
		"\3\33\6\33\u018c\n\33\r\33\16\33\u018d\5\33\u0190\n\33\3\34\3\34\5\34"+
		"\u0194\n\34\3\34\5\34\u0197\n\34\3\34\3\34\3\34\6\34\u019c\n\34\r\34\16"+
		"\34\u019d\3\34\5\34\u01a1\n\34\5\34\u01a3\n\34\3\35\3\35\5\35\u01a7\n"+
		"\35\3\35\5\35\u01aa\n\35\3\35\3\35\3\35\6\35\u01af\n\35\r\35\16\35\u01b0"+
		"\3\35\5\35\u01b4\n\35\5\35\u01b6\n\35\3\36\3\36\3\36\5\36\u01bb\n\36\3"+
		"\36\5\36\u01be\n\36\3\36\3\36\3\36\6\36\u01c3\n\36\r\36\16\36\u01c4\3"+
		"\36\5\36\u01c8\n\36\5\36\u01ca\n\36\3\37\3\37\3\37\3\37\5\37\u01d0\n\37"+
		"\3\37\3\37\3\37\6\37\u01d5\n\37\r\37\16\37\u01d6\3\37\5\37\u01da\n\37"+
		"\5\37\u01dc\n\37\3 \3 \5 \u01e0\n \3 \3 \3 \5 \u01e5\n \3!\3!\3!\3\"\3"+
		"\"\3#\3#\3#\3$\3$\5$\u01f1\n$\3$\5$\u01f4\n$\3%\3%\3&\3&\3\'\3\'\3(\3"+
		"(\5(\u01fe\n(\3)\3)\5)\u0202\n)\3*\3*\3*\3*\3+\3+\3,\3,\3,\5,\u020d\n"+
		",\3-\6-\u0210\n-\r-\16-\u0211\3.\5.\u0215\n.\3.\3.\3/\3/\3/\3/\3\60\7"+
		"\60\u021e\n\60\f\60\16\60\u0221\13\60\3\60\3\60\3\61\7\61\u0226\n\61\f"+
		"\61\16\61\u0229\13\61\3\61\3\61\3\62\3\62\3\62\3\63\3\63\3\63\2\2\64\2"+
		"\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJL"+
		"NPRTVXZ\\^`bd\2\7\3\2@A\3\2:;\3\2:<\4\2\r\26\30\34\4\2\3\3@@\u026d\2i"+
		"\3\2\2\2\4~\3\2\2\2\6\u0082\3\2\2\2\b\u008b\3\2\2\2\n\u0090\3\2\2\2\f"+
		"\u00a8\3\2\2\2\16\u00ad\3\2\2\2\20\u00b0\3\2\2\2\22\u00b2\3\2\2\2\24\u00cb"+
		"\3\2\2\2\26\u00cd\3\2\2\2\30\u00fa\3\2\2\2\32\u00fc\3\2\2\2\34\u0103\3"+
		"\2\2\2\36\u0106\3\2\2\2 \u011b\3\2\2\2\"\u012d\3\2\2\2$\u0138\3\2\2\2"+
		"&\u0140\3\2\2\2(\u0146\3\2\2\2*\u0155\3\2\2\2,\u0159\3\2\2\2.\u015d\3"+
		"\2\2\2\60\u016a\3\2\2\2\62\u0177\3\2\2\2\64\u0184\3\2\2\2\66\u0191\3\2"+
		"\2\28\u01a4\3\2\2\2:\u01b7\3\2\2\2<\u01cb\3\2\2\2>\u01dd\3\2\2\2@\u01e6"+
		"\3\2\2\2B\u01e9\3\2\2\2D\u01eb\3\2\2\2F\u01f3\3\2\2\2H\u01f5\3\2\2\2J"+
		"\u01f7\3\2\2\2L\u01f9\3\2\2\2N\u01fb\3\2\2\2P\u0201\3\2\2\2R\u0203\3\2"+
		"\2\2T\u0207\3\2\2\2V\u0209\3\2\2\2X\u020f\3\2\2\2Z\u0214\3\2\2\2\\\u0218"+
		"\3\2\2\2^\u021f\3\2\2\2`\u0227\3\2\2\2b\u022c\3\2\2\2d\u022f\3\2\2\2f"+
		"h\7B\2\2gf\3\2\2\2hk\3\2\2\2ig\3\2\2\2ij\3\2\2\2jm\3\2\2\2ki\3\2\2\2l"+
		"n\5\4\3\2ml\3\2\2\2mn\3\2\2\2nx\3\2\2\2os\5\n\6\2pr\7B\2\2qp\3\2\2\2r"+
		"u\3\2\2\2sq\3\2\2\2st\3\2\2\2tw\3\2\2\2us\3\2\2\2vo\3\2\2\2wz\3\2\2\2"+
		"xv\3\2\2\2xy\3\2\2\2y{\3\2\2\2zx\3\2\2\2{|\7\2\2\3|\3\3\2\2\2}\177\5\6"+
		"\4\2~}\3\2\2\2\177\u0080\3\2\2\2\u0080~\3\2\2\2\u0080\u0081\3\2\2\2\u0081"+
		"\5\3\2\2\2\u0082\u0083\7\5\2\2\u0083\u0086\5^\60\2\u0084\u0085\7\7\2\2"+
		"\u0085\u0087\7@\2\2\u0086\u0084\3\2\2\2\u0086\u0087\3\2\2\2\u0087\u0088"+
		"\3\2\2\2\u0088\u0089\7B\2\2\u0089\7\3\2\2\2\u008a\u008c\7D\2\2\u008b\u008a"+
		"\3\2\2\2\u008c\u008d\3\2\2\2\u008d\u008b\3\2\2\2\u008d\u008e\3\2\2\2\u008e"+
		"\t\3\2\2\2\u008f\u0091\5\b\5\2\u0090\u008f\3\2\2\2\u0090\u0091\3\2\2\2"+
		"\u0091\u0092\3\2\2\2\u0092\u0094\5\f\7\2\u0093\u0095\5V,\2\u0094\u0093"+
		"\3\2\2\2\u0094\u0095\3\2\2\2\u0095\u0097\3\2\2\2\u0096\u0098\5\36\20\2"+
		"\u0097\u0096\3\2\2\2\u0097\u0098\3\2\2\2\u0098\13\3\2\2\2\u0099\u009b"+
		"\7\4\2\2\u009a\u009c\5\22\n\2\u009b\u009a\3\2\2\2\u009b\u009c\3\2\2\2"+
		"\u009c\u009d\3\2\2\2\u009d\u00a9\7@\2\2\u009e\u00a0\5d\63\2\u009f\u00a1"+
		"\5\22\n\2\u00a0\u009f\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\u00a3\3\2\2\2"+
		"\u00a2\u00a4\7@\2\2\u00a3\u00a2\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4\u00a6"+
		"\3\2\2\2\u00a5\u00a7\5\16\b\2\u00a6\u00a5\3\2\2\2\u00a6\u00a7\3\2\2\2"+
		"\u00a7\u00a9\3\2\2\2\u00a8\u0099\3\2\2\2\u00a8\u009e\3\2\2\2\u00a9\u00ab"+
		"\3\2\2\2\u00aa\u00ac\5\20\t\2\u00ab\u00aa\3\2\2\2\u00ab\u00ac\3\2\2\2"+
		"\u00ac\r\3\2\2\2\u00ad\u00ae\7\f\2\2\u00ae\u00af\5`\61\2\u00af\17\3\2"+
		"\2\2\u00b0\u00b1\7?\2\2\u00b1\21\3\2\2\2\u00b2\u00b4\7\35\2\2\u00b3\u00b5"+
		"\5\24\13\2\u00b4\u00b3\3\2\2\2\u00b4\u00b5\3\2\2\2\u00b5\u00b6\3\2\2\2"+
		"\u00b6\u00b7\7\36\2\2\u00b7\23\3\2\2\2\u00b8\u00b9\5\26\f\2\u00b9\u00c0"+
		"\5\30\r\2\u00ba\u00bb\7&\2\2\u00bb\u00bc\5\26\f\2\u00bc\u00bd\5\30\r\2"+
		"\u00bd\u00bf\3\2\2\2\u00be\u00ba\3\2\2\2\u00bf\u00c2\3\2\2\2\u00c0\u00be"+
		"\3\2\2\2\u00c0\u00c1\3\2\2\2\u00c1\u00cc\3\2\2\2\u00c2\u00c0\3\2\2\2\u00c3"+
		"\u00c8\5\30\r\2\u00c4\u00c5\7&\2\2\u00c5\u00c7\5\30\r\2\u00c6\u00c4\3"+
		"\2\2\2\u00c7\u00ca\3\2\2\2\u00c8\u00c6\3\2\2\2\u00c8\u00c9\3\2\2\2\u00c9"+
		"\u00cc\3\2\2\2\u00ca\u00c8\3\2\2\2\u00cb\u00b8\3\2\2\2\u00cb\u00c3\3\2"+
		"\2\2\u00cc\25\3\2\2\2\u00cd\u00ce\7@\2\2\u00ce\u00cf\7(\2\2\u00cf\27\3"+
		"\2\2\2\u00d0\u00d2\5`\61\2\u00d1\u00d0\3\2\2\2\u00d2\u00d3\3\2\2\2\u00d3"+
		"\u00d1\3\2\2\2\u00d3\u00d4\3\2\2\2\u00d4\u00fb\3\2\2\2\u00d5\u00d7\5F"+
		"$\2\u00d6\u00d5\3\2\2\2\u00d7\u00d8\3\2\2\2\u00d8\u00d6\3\2\2\2\u00d8"+
		"\u00d9\3\2\2\2\u00d9\u00fb\3\2\2\2\u00da\u00dc\5H%\2\u00db\u00da\3\2\2"+
		"\2\u00dc\u00dd\3\2\2\2\u00dd\u00db\3\2\2\2\u00dd\u00de\3\2\2\2\u00de\u00fb"+
		"\3\2\2\2\u00df\u00e1\5J&\2\u00e0\u00df\3\2\2\2\u00e1\u00e2\3\2\2\2\u00e2"+
		"\u00e0\3\2\2\2\u00e2\u00e3\3\2\2\2\u00e3\u00e5\3\2\2\2\u00e4\u00e6\5T"+
		"+\2\u00e5\u00e4\3\2\2\2\u00e5\u00e6\3\2\2\2\u00e6\u00fb\3\2\2\2\u00e7"+
		"\u00e9\5L\'\2\u00e8\u00e7\3\2\2\2\u00e9\u00ea\3\2\2\2\u00ea\u00e8\3\2"+
		"\2\2\u00ea\u00eb\3\2\2\2\u00eb\u00ed\3\2\2\2\u00ec\u00ee\5T+\2\u00ed\u00ec"+
		"\3\2\2\2\u00ed\u00ee\3\2\2\2\u00ee\u00fb\3\2\2\2\u00ef\u00f1\5N(\2\u00f0"+
		"\u00ef\3\2\2\2\u00f1\u00f2\3\2\2\2\u00f2\u00f0\3\2\2\2\u00f2\u00f3\3\2"+
		"\2\2\u00f3\u00f5\3\2\2\2\u00f4\u00f6\5T+\2\u00f5\u00f4\3\2\2\2\u00f5\u00f6"+
		"\3\2\2\2\u00f6\u00fb\3\2\2\2\u00f7\u00fb\5\32\16\2\u00f8\u00fb\5P)\2\u00f9"+
		"\u00fb\7\67\2\2\u00fa\u00d1\3\2\2\2\u00fa\u00d6\3\2\2\2\u00fa\u00db\3"+
		"\2\2\2\u00fa\u00e0\3\2\2\2\u00fa\u00e8\3\2\2\2\u00fa\u00f0\3\2\2\2\u00fa"+
		"\u00f7\3\2\2\2\u00fa\u00f8\3\2\2\2\u00fa\u00f9\3\2\2\2\u00fb\31\3\2\2"+
		"\2\u00fc\u0100\5d\63\2\u00fd\u00ff\5\34\17\2\u00fe\u00fd\3\2\2\2\u00ff"+
		"\u0102\3\2\2\2\u0100\u00fe\3\2\2\2\u0100\u0101\3\2\2\2\u0101\33\3\2\2"+
		"\2\u0102\u0100\3\2\2\2\u0103\u0104\7\'\2\2\u0104\u0105\7@\2\2\u0105\35"+
		"\3\2\2\2\u0106\u0115\7G\2\2\u0107\u010f\5 \21\2\u0108\u010f\5\n\6\2\u0109"+
		"\u010f\5\\/\2\u010a\u010f\5V,\2\u010b\u010f\5$\23\2\u010c\u010f\5&\24"+
		"\2\u010d\u010f\5\b\5\2\u010e\u0107\3\2\2\2\u010e\u0108\3\2\2\2\u010e\u0109"+
		"\3\2\2\2\u010e\u010a\3\2\2\2\u010e\u010b\3\2\2\2\u010e\u010c\3\2\2\2\u010e"+
		"\u010d\3\2\2\2\u010f\u0111\3\2\2\2\u0110\u0112\7B\2\2\u0111\u0110\3\2"+
		"\2\2\u0112\u0113\3\2\2\2\u0113\u0111\3\2\2\2\u0113\u0114\3\2\2\2\u0114"+
		"\u0116\3\2\2\2\u0115\u010e\3\2\2\2\u0116\u0117\3\2\2\2\u0117\u0115\3\2"+
		"\2\2\u0117\u0118\3\2\2\2\u0118\u0119\3\2\2\2\u0119\u011a\7H\2\2\u011a"+
		"\37\3\2\2\2\u011b\u0127\7\6\2\2\u011c\u0128\5\66\34\2\u011d\u0128\58\35"+
		"\2\u011e\u0128\5:\36\2\u011f\u0128\5\64\33\2\u0120\u0128\5<\37\2\u0121"+
		"\u0128\5.\30\2\u0122\u0128\5\60\31\2\u0123\u0128\5\62\32\2\u0124\u0128"+
		"\5,\27\2\u0125\u0128\5> \2\u0126\u0128\5(\25\2\u0127\u011c\3\2\2\2\u0127"+
		"\u011d\3\2\2\2\u0127\u011e\3\2\2\2\u0127\u011f\3\2\2\2\u0127\u0120\3\2"+
		"\2\2\u0127\u0121\3\2\2\2\u0127\u0122\3\2\2\2\u0127\u0123\3\2\2\2\u0127"+
		"\u0124\3\2\2\2\u0127\u0125\3\2\2\2\u0127\u0126\3\2\2\2\u0128\u012b\3\2"+
		"\2\2\u0129\u012a\7\n\2\2\u012a\u012c\5X-\2\u012b\u0129\3\2\2\2\u012b\u012c"+
		"\3\2\2\2\u012c!\3\2\2\2\u012d\u012f\5d\63\2\u012e\u0130\5\22\n\2\u012f"+
		"\u012e\3\2\2\2\u012f\u0130\3\2\2\2\u0130\u0133\3\2\2\2\u0131\u0132\7\13"+
		"\2\2\u0132\u0134\5d\63\2\u0133\u0131\3\2\2\2\u0133\u0134\3\2\2\2\u0134"+
		"\u0136\3\2\2\2\u0135\u0137\5\36\20\2\u0136\u0135\3\2\2\2\u0136\u0137\3"+
		"\2\2\2\u0137#\3\2\2\2\u0138\u0139\7\t\2\2\u0139\u013b\5`\61\2\u013a\u013c"+
		"\7\27\2\2\u013b\u013a\3\2\2\2\u013b\u013c\3\2\2\2\u013c\u013e\3\2\2\2"+
		"\u013d\u013f\5\36\20\2\u013e\u013d\3\2\2\2\u013e\u013f\3\2\2\2\u013f%"+
		"\3\2\2\2\u0140\u0141\7\b\2\2\u0141\u0144\5`\61\2\u0142\u0143\7\n\2\2\u0143"+
		"\u0145\5X-\2\u0144\u0142\3\2\2\2\u0144\u0145\3\2\2\2\u0145\'\3\2\2\2\u0146"+
		"\u0148\7-\2\2\u0147\u0149\7!\2\2\u0148\u0147\3\2\2\2\u0148\u0149\3\2\2"+
		"\2\u0149\u014a\3\2\2\2\u014a\u014b\7@\2\2\u014b\u014f\7G\2\2\u014c\u014d"+
		"\5*\26\2\u014d\u014e\7B\2\2\u014e\u0150\3\2\2\2\u014f\u014c\3\2\2\2\u0150"+
		"\u0151\3\2\2\2\u0151\u014f\3\2\2\2\u0151\u0152\3\2\2\2\u0152\u0153\3\2"+
		"\2\2\u0153\u0154\7H\2\2\u0154)\3\2\2\2\u0155\u0157\7@\2\2\u0156\u0158"+
		"\7+\2\2\u0157\u0156\3\2\2\2\u0157\u0158\3\2\2\2\u0158+\3\2\2\2\u0159\u015a"+
		"\7.\2\2\u015a\u015b\5@!\2\u015b\u015c\7@\2\2\u015c-\3\2\2\2\u015d\u015f"+
		"\7\63\2\2\u015e\u0160\7!\2\2\u015f\u015e\3\2\2\2\u015f\u0160\3\2\2\2\u0160"+
		"\u0161\3\2\2\2\u0161\u0168\7@\2\2\u0162\u0164\7(\2\2\u0163\u0165\5H%\2"+
		"\u0164\u0163\3\2\2\2\u0165\u0166\3\2\2\2\u0166\u0164\3\2\2\2\u0166\u0167"+
		"\3\2\2\2\u0167\u0169\3\2\2\2\u0168\u0162\3\2\2\2\u0168\u0169\3\2\2\2\u0169"+
		"/\3\2\2\2\u016a\u016c\7\62\2\2\u016b\u016d\7!\2\2\u016c\u016b\3\2\2\2"+
		"\u016c\u016d\3\2\2\2\u016d\u016e\3\2\2\2\u016e\u0175\7@\2\2\u016f\u0171"+
		"\7(\2\2\u0170\u0172\5F$\2\u0171\u0170\3\2\2\2\u0172\u0173\3\2\2\2\u0173"+
		"\u0171\3\2\2\2\u0173\u0174\3\2\2\2\u0174\u0176\3\2\2\2\u0175\u016f\3\2"+
		"\2\2\u0175\u0176\3\2\2\2\u0176\61\3\2\2\2\u0177\u0179\7\66\2\2\u0178\u017a"+
		"\7!\2\2\u0179\u0178\3\2\2\2\u0179\u017a\3\2\2\2\u017a\u017b\3\2\2\2\u017b"+
		"\u0182\7@\2\2\u017c\u017e\7(\2\2\u017d\u017f\5F$\2\u017e\u017d\3\2\2\2"+
		"\u017f\u0180\3\2\2\2\u0180\u017e\3\2\2\2\u0180\u0181\3\2\2\2\u0181\u0183"+
		"\3\2\2\2\u0182\u017c\3\2\2\2\u0182\u0183\3\2\2\2\u0183\63\3\2\2\2\u0184"+
		"\u0186\7\65\2\2\u0185\u0187\7!\2\2\u0186\u0185\3\2\2\2\u0186\u0187\3\2"+
		"\2\2\u0187\u0188\3\2\2\2\u0188\u018f\7@\2\2\u0189\u018b\7(\2\2\u018a\u018c"+
		"\5N(\2\u018b\u018a\3\2\2\2\u018c\u018d\3\2\2\2\u018d\u018b\3\2\2\2\u018d"+
		"\u018e\3\2\2\2\u018e\u0190\3\2\2\2\u018f\u0189\3\2\2\2\u018f\u0190\3\2"+
		"\2\2\u0190\65\3\2\2\2\u0191\u0193\7\60\2\2\u0192\u0194\5D#\2\u0193\u0192"+
		"\3\2\2\2\u0193\u0194\3\2\2\2\u0194\u0196\3\2\2\2\u0195\u0197\7!\2\2\u0196"+
		"\u0195\3\2\2\2\u0196\u0197\3\2\2\2\u0197\u0198\3\2\2\2\u0198\u01a2\7@"+
		"\2\2\u0199\u019b\7(\2\2\u019a\u019c\5J&\2\u019b\u019a\3\2\2\2\u019c\u019d"+
		"\3\2\2\2\u019d\u019b\3\2\2\2\u019d\u019e\3\2\2\2\u019e\u01a0\3\2\2\2\u019f"+
		"\u01a1\5T+\2\u01a0\u019f\3\2\2\2\u01a0\u01a1\3\2\2\2\u01a1\u01a3\3\2\2"+
		"\2\u01a2\u0199\3\2\2\2\u01a2\u01a3\3\2\2\2\u01a3\67\3\2\2\2\u01a4\u01a6"+
		"\7/\2\2\u01a5\u01a7\5D#\2\u01a6\u01a5\3\2\2\2\u01a6\u01a7\3\2\2\2\u01a7"+
		"\u01a9\3\2\2\2\u01a8\u01aa\7!\2\2\u01a9\u01a8\3\2\2\2\u01a9\u01aa\3\2"+
		"\2\2\u01aa\u01ab\3\2\2\2\u01ab\u01b5\7@\2\2\u01ac\u01ae\7(\2\2\u01ad\u01af"+
		"\5L\'\2\u01ae\u01ad\3\2\2\2\u01af\u01b0\3\2\2\2\u01b0\u01ae\3\2\2\2\u01b0"+
		"\u01b1\3\2\2\2\u01b1\u01b3\3\2\2\2\u01b2\u01b4\5T+\2\u01b3\u01b2\3\2\2"+
		"\2\u01b3\u01b4\3\2\2\2\u01b4\u01b6\3\2\2\2\u01b5\u01ac\3\2\2\2\u01b5\u01b6"+
		"\3\2\2\2\u01b69\3\2\2\2\u01b7\u01ba\7\61\2\2\u01b8\u01bb\7!\2\2\u01b9"+
		"\u01bb\5R*\2\u01ba\u01b8\3\2\2\2\u01ba\u01b9\3\2\2\2\u01ba\u01bb\3\2\2"+
		"\2\u01bb\u01bd\3\2\2\2\u01bc\u01be\5D#\2\u01bd\u01bc\3\2\2\2\u01bd\u01be"+
		"\3\2\2\2\u01be\u01bf\3\2\2\2\u01bf\u01c9\7@\2\2\u01c0\u01c2\7(\2\2\u01c1"+
		"\u01c3\5N(\2\u01c2\u01c1\3\2\2\2\u01c3\u01c4\3\2\2\2\u01c4\u01c2\3\2\2"+
		"\2\u01c4\u01c5\3\2\2\2\u01c5\u01c7\3\2\2\2\u01c6\u01c8\5T+\2\u01c7\u01c6"+
		"\3\2\2\2\u01c7\u01c8\3\2\2\2\u01c8\u01ca\3\2\2\2\u01c9\u01c0\3\2\2\2\u01c9"+
		"\u01ca\3\2\2\2\u01ca;\3\2\2\2\u01cb\u01cc\7\64\2\2\u01cc\u01cf\5@!\2\u01cd"+
		"\u01d0\7!\2\2\u01ce\u01d0\5R*\2\u01cf\u01cd\3\2\2\2\u01cf\u01ce\3\2\2"+
		"\2\u01cf\u01d0\3\2\2\2\u01d0\u01d1\3\2\2\2\u01d1\u01db\7@\2\2\u01d2\u01d4"+
		"\7(\2\2\u01d3\u01d5\5N(\2\u01d4\u01d3\3\2\2\2\u01d5\u01d6\3\2\2\2\u01d6"+
		"\u01d4\3\2\2\2\u01d6\u01d7\3\2\2\2\u01d7\u01d9\3\2\2\2\u01d8\u01da\5T"+
		"+\2\u01d9\u01d8\3\2\2\2\u01d9\u01da\3\2\2\2\u01da\u01dc\3\2\2\2\u01db"+
		"\u01d2\3\2\2\2\u01db\u01dc\3\2\2\2\u01dc=\3\2\2\2\u01dd\u01df\5`\61\2"+
		"\u01de\u01e0\7!\2\2\u01df\u01de\3\2\2\2\u01df\u01e0\3\2\2\2\u01e0\u01e1"+
		"\3\2\2\2\u01e1\u01e4\7@\2\2\u01e2\u01e3\7(\2\2\u01e3\u01e5\7\67\2\2\u01e4"+
		"\u01e2\3\2\2\2\u01e4\u01e5\3\2\2\2\u01e5?\3\2\2\2\u01e6\u01e7\7%\2\2\u01e7"+
		"\u01e8\5B\"\2\u01e8A\3\2\2\2\u01e9\u01ea\7@\2\2\u01eaC\3\2\2\2\u01eb\u01ec"+
		"\7%\2\2\u01ec\u01ed\t\2\2\2\u01edE\3\2\2\2\u01ee\u01f4\7=\2\2\u01ef\u01f1"+
		"\7B\2\2\u01f0\u01ef\3\2\2\2\u01f0\u01f1\3\2\2\2\u01f1\u01f2\3\2\2\2\u01f2"+
		"\u01f4\7>\2\2\u01f3\u01ee\3\2\2\2\u01f3\u01f0\3\2\2\2\u01f4G\3\2\2\2\u01f5"+
		"\u01f6\79\2\2\u01f6I\3\2\2\2\u01f7\u01f8\7:\2\2\u01f8K\3\2\2\2\u01f9\u01fa"+
		"\t\3\2\2\u01faM\3\2\2\2\u01fb\u01fd\t\4\2\2\u01fc\u01fe\78\2\2\u01fd\u01fc"+
		"\3\2\2\2\u01fd\u01fe\3\2\2\2\u01feO\3\2\2\2\u01ff\u0202\5\20\t\2\u0200"+
		"\u0202\5`\61\2\u0201\u01ff\3\2\2\2\u0201\u0200\3\2\2\2\u0202Q\3\2\2\2"+
		"\u0203\u0204\7\37\2\2\u0204\u0205\5J&\2\u0205\u0206\7 \2\2\u0206S\3\2"+
		"\2\2\u0207\u0208\t\2\2\2\u0208U\3\2\2\2\u0209\u020c\7\n\2\2\u020a\u020d"+
		"\5X-\2\u020b\u020d\5\"\22\2\u020c\u020a\3\2\2\2\u020c\u020b\3\2\2\2\u020d"+
		"W\3\2\2\2\u020e\u0210\5Z.\2\u020f\u020e\3\2\2\2\u0210\u0211\3\2\2\2\u0211"+
		"\u020f\3\2\2\2\u0211\u0212\3\2\2\2\u0212Y\3\2\2\2\u0213\u0215\7,\2\2\u0214"+
		"\u0213\3\2\2\2\u0214\u0215\3\2\2\2\u0215\u0216\3\2\2\2\u0216\u0217\t\5"+
		"\2\2\u0217[\3\2\2\2\u0218\u0219\7@\2\2\u0219\u021a\7(\2\2\u021a\u021b"+
		"\5\30\r\2\u021b]\3\2\2\2\u021c\u021e\5b\62\2\u021d\u021c\3\2\2\2\u021e"+
		"\u0221\3\2\2\2\u021f\u021d\3\2\2\2\u021f\u0220\3\2\2\2\u0220\u0222\3\2"+
		"\2\2\u0221\u021f\3\2\2\2\u0222\u0223\7@\2\2\u0223_\3\2\2\2\u0224\u0226"+
		"\5b\62\2\u0225\u0224\3\2\2\2\u0226\u0229\3\2\2\2\u0227\u0225\3\2\2\2\u0227"+
		"\u0228\3\2\2\2\u0228\u022a\3\2\2\2\u0229\u0227\3\2\2\2\u022a\u022b\7@"+
		"\2\2\u022ba\3\2\2\2\u022c\u022d\7@\2\2\u022d\u022e\7\'\2\2\u022ec\3\2"+
		"\2\2\u022f\u0230\t\6\2\2\u0230e\3\2\2\2Yimsx\u0080\u0086\u008d\u0090\u0094"+
		"\u0097\u009b\u00a0\u00a3\u00a6\u00a8\u00ab\u00b4\u00c0\u00c8\u00cb\u00d3"+
		"\u00d8\u00dd\u00e2\u00e5\u00ea\u00ed\u00f2\u00f5\u00fa\u0100\u010e\u0113"+
		"\u0117\u0127\u012b\u012f\u0133\u0136\u013b\u013e\u0144\u0148\u0151\u0157"+
		"\u015f\u0166\u0168\u016c\u0173\u0175\u0179\u0180\u0182\u0186\u018d\u018f"+
		"\u0193\u0196\u019d\u01a0\u01a2\u01a6\u01a9\u01b0\u01b3\u01b5\u01ba\u01bd"+
		"\u01c4\u01c7\u01c9\u01cf\u01d6\u01d9\u01db\u01df\u01e4\u01f0\u01f3\u01fd"+
		"\u0201\u020c\u0211\u0214\u021f\u0227";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}