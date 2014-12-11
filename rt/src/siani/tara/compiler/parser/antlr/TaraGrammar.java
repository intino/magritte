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
		MEASURE_VALUE=62, FACET=15, RIGHT_SQUARE=30, STAR=41, NEGATIVE_VALUE=55, 
		DOC=65, DATE_VALUE=60, SUB=2, DEDENT=69, EQUALS=38, ABSTRACT=11, BOOLEAN_VALUE=53, 
		METAIDENTIFIER=1, RESOURCE=44, ADDRESS_VALUE=59, MULTIPLE=25, UNKNOWN_TOKEN=70, 
		WORD=43, AS=5, HAS=6, RIGHT_PARENTHESIS=28, ALWAYS=21, COMMA=36, IS=8, 
		IDENTIFIER=61, LEFT_PARENTHESIS=27, STRING_MULTILINE_VALUE_KEY=58, PLUS=42, 
		VAR=4, NL=67, INTENTION=16, DOT=37, COMPONENT=14, STRING_VALUE=57, DOUBLE_VALUE=56, 
		COMPOSED=24, WITH=9, NEW_LINE_INDENT=68, AGGREGATED=23, ADDRESSED=22, 
		TERMINAL=17, NATURAL_TYPE=46, ON=7, AMPERSAND=34, CLOSE_INLINE=33, BOOLEAN_TYPE=49, 
		DOUBLE_TYPE=47, SEMICOLON=40, SCIENCE_NOT=52, LOCAL=20, REQUIRED=13, INT_TYPE=45, 
		LIST=31, ROOT=26, EMPTY=51, COLON=35, NATURAL_VALUE=54, NEWLINE=63, SINGLE=12, 
		PROPERTY=19, SPACES=64, SP=66, APHOSTROPHE=39, NAMED=18, STRING_TYPE=48, 
		DATE_TYPE=50, INLINE=32, USE=3, EXTENDS=10, LEFT_SQUARE=29;
	public static final String[] tokenNames = {
		"<INVALID>", "'Concept'", "'sub'", "'use'", "'var'", "'as'", "'has'", 
		"'on'", "'is'", "'with'", "'extends'", "'abstract'", "'single'", "'required'", 
		"'component'", "'facet'", "'intention'", "'terminal'", "'named'", "'property'", 
		"'local'", "'always'", "'addressed'", "'aggregated'", "'composed'", "'multiple'", 
		"'root'", "'('", "')'", "'['", "']'", "'...'", "'>'", "'<'", "'&'", "':'", 
		"','", "'.'", "'='", "'''", "SEMICOLON", "'*'", "'+'", "'word'", "'resource'", 
		"'integer'", "'natural'", "'double'", "'string'", "'boolean'", "'date'", 
		"'empty'", "SCIENCE_NOT", "BOOLEAN_VALUE", "NATURAL_VALUE", "NEGATIVE_VALUE", 
		"DOUBLE_VALUE", "STRING_VALUE", "STRING_MULTILINE_VALUE_KEY", "ADDRESS_VALUE", 
		"DATE_VALUE", "IDENTIFIER", "MEASURE_VALUE", "NEWLINE", "SPACES", "DOC", 
		"SP", "NL", "'indent'", "'dedent'", "UNKNOWN_TOKEN"
	};
	public static final int
		RULE_root = 0, RULE_imports = 1, RULE_anImport = 2, RULE_doc = 3, RULE_concept = 4, 
		RULE_signature = 5, RULE_address = 6, RULE_parameters = 7, RULE_parameterList = 8, 
		RULE_explicit = 9, RULE_initValue = 10, RULE_metaWord = 11, RULE_metaWordNames = 12, 
		RULE_body = 13, RULE_variable = 14, RULE_facetApply = 15, RULE_facetTarget = 16, 
		RULE_conceptReference = 17, RULE_resource = 18, RULE_word = 19, RULE_wordNames = 20, 
		RULE_reference = 21, RULE_booleanAttribute = 22, RULE_stringAttribute = 23, 
		RULE_naturalAttribute = 24, RULE_integerAttribute = 25, RULE_doubleAttribute = 26, 
		RULE_dateAttribute = 27, RULE_attributeType = 28, RULE_naturalValue = 29, 
		RULE_integerValue = 30, RULE_doubleValue = 31, RULE_booleanValue = 32, 
		RULE_stringValue = 33, RULE_dateValue = 34, RULE_linkValue = 35, RULE_count = 36, 
		RULE_measure = 37, RULE_annotations = 38, RULE_varInit = 39, RULE_headerReference = 40, 
		RULE_identifierReference = 41, RULE_hierarchy = 42, RULE_metaidentifier = 43;
	public static final String[] ruleNames = {
		"root", "imports", "anImport", "doc", "concept", "signature", "address", 
		"parameters", "parameterList", "explicit", "initValue", "metaWord", "metaWordNames", 
		"body", "variable", "facetApply", "facetTarget", "conceptReference", "resource", 
		"word", "wordNames", "reference", "booleanAttribute", "stringAttribute", 
		"naturalAttribute", "integerAttribute", "doubleAttribute", "dateAttribute", 
		"attributeType", "naturalValue", "integerValue", "doubleValue", "booleanValue", 
		"stringValue", "dateValue", "linkValue", "count", "measure", "annotations", 
		"varInit", "headerReference", "identifierReference", "hierarchy", "metaidentifier"
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
			setState(89);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				{
				setState(88); imports();
				}
				break;
			}
			setState(94);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(91); match(NEWLINE);
				}
				}
				setState(96);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(106);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << METAIDENTIFIER) | (1L << SUB) | (1L << IDENTIFIER))) != 0) || _la==DOC) {
				{
				{
				setState(97); concept();
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
				}
				}
				setState(108);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(109); match(EOF);
			}
		}
		catch (RecognitionException re) {
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
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(112); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(111); anImport();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(114); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
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

	public static class AnImportContext extends ParserRuleContext {
		public TerminalNode AS() { return getToken(TaraGrammar.AS, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(TaraGrammar.NEWLINE); }
		public HeaderReferenceContext headerReference() {
			return getRuleContext(HeaderReferenceContext.class,0);
		}
		public TerminalNode NEWLINE(int i) {
			return getToken(TaraGrammar.NEWLINE, i);
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
			setState(119);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(116); match(NEWLINE);
				}
				}
				setState(121);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(122); match(USE);
			setState(123); headerReference();
			setState(126);
			_la = _input.LA(1);
			if (_la==AS) {
				{
				setState(124); match(AS);
				setState(125); match(IDENTIFIER);
				}
			}

			setState(128); match(NEWLINE);
			}
		}
		catch (RecognitionException re) {
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
			setState(131); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(130); match(DOC);
				}
				}
				setState(133); 
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
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public DocContext doc() {
			return getRuleContext(DocContext.class,0);
		}
		public AnnotationsContext annotations() {
			return getRuleContext(AnnotationsContext.class,0);
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
			setState(136);
			_la = _input.LA(1);
			if (_la==DOC) {
				{
				setState(135); doc();
				}
			}

			setState(138); signature();
			setState(140);
			_la = _input.LA(1);
			if (_la==IS) {
				{
				setState(139); annotations();
				}
			}

			setState(143);
			_la = _input.LA(1);
			if (_la==NEW_LINE_INDENT) {
				{
				setState(142); body();
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
			setState(160);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				{
				{
				setState(145); match(SUB);
				setState(146); match(IDENTIFIER);
				}
				}
				break;
			case 2:
				{
				{
				setState(147); metaidentifier();
				setState(149);
				_la = _input.LA(1);
				if (_la==LEFT_PARENTHESIS) {
					{
					setState(148); parameters();
					}
				}

				setState(151); match(IDENTIFIER);
				setState(154);
				_la = _input.LA(1);
				if (_la==EXTENDS) {
					{
					setState(152); match(EXTENDS);
					setState(153); identifierReference();
					}
				}

				}
				}
				break;
			case 3:
				{
				setState(156); metaidentifier();
				setState(158);
				_la = _input.LA(1);
				if (_la==LEFT_PARENTHESIS) {
					{
					setState(157); parameters();
					}
				}

				}
				break;
			}
			setState(163);
			_la = _input.LA(1);
			if (_la==ADDRESS_VALUE) {
				{
				setState(162); address();
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
			setState(165); match(ADDRESS_VALUE);
			}
		}
		catch (RecognitionException re) {
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
			setState(167); match(LEFT_PARENTHESIS);
			setState(169);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << METAIDENTIFIER) | (1L << EMPTY) | (1L << BOOLEAN_VALUE) | (1L << NATURAL_VALUE) | (1L << NEGATIVE_VALUE) | (1L << DOUBLE_VALUE) | (1L << STRING_VALUE) | (1L << STRING_MULTILINE_VALUE_KEY) | (1L << ADDRESS_VALUE) | (1L << DATE_VALUE) | (1L << IDENTIFIER) | (1L << NEWLINE))) != 0)) {
				{
				setState(168); parameterList();
				}
			}

			setState(171); match(RIGHT_PARENTHESIS);
			}
		}
		catch (RecognitionException re) {
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
			setState(192);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(173); explicit();
				setState(174); initValue();
				setState(181);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(175); match(COMMA);
					setState(176); explicit();
					setState(177); initValue();
					}
					}
					setState(183);
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
				setState(184); initValue();
				setState(189);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(185); match(COMMA);
					setState(186); initValue();
					}
					}
					setState(191);
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
			setState(194); match(IDENTIFIER);
			setState(195); match(EQUALS);
			}
		}
		catch (RecognitionException re) {
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
		public List<DateValueContext> dateValue() {
			return getRuleContexts(DateValueContext.class);
		}
		public LinkValueContext linkValue() {
			return getRuleContext(LinkValueContext.class,0);
		}
		public DateValueContext dateValue(int i) {
			return getRuleContext(DateValueContext.class,i);
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
		public MeasureContext measure() {
			return getRuleContext(MeasureContext.class,0);
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
			setState(244);
			switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(198); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(197); identifierReference();
					}
					}
					setState(200); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==IDENTIFIER );
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(203); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(202); stringValue();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(205); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(208); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(207); booleanValue();
					}
					}
					setState(210); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==BOOLEAN_VALUE );
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(213); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(212); naturalValue();
					}
					}
					setState(215); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NATURAL_VALUE );
				setState(218);
				_la = _input.LA(1);
				if (_la==IDENTIFIER || _la==MEASURE_VALUE) {
					{
					setState(217); measure();
					}
				}

				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(221); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(220); integerValue();
					}
					}
					setState(223); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NATURAL_VALUE || _la==NEGATIVE_VALUE );
				setState(226);
				_la = _input.LA(1);
				if (_la==IDENTIFIER || _la==MEASURE_VALUE) {
					{
					setState(225); measure();
					}
				}

				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(229); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(228); doubleValue();
					}
					}
					setState(231); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NATURAL_VALUE) | (1L << NEGATIVE_VALUE) | (1L << DOUBLE_VALUE))) != 0) );
				setState(234);
				_la = _input.LA(1);
				if (_la==IDENTIFIER || _la==MEASURE_VALUE) {
					{
					setState(233); measure();
					}
				}

				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(237); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(236); dateValue();
					}
					}
					setState(239); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==DATE_VALUE );
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(241); metaWord();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(242); linkValue();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(243); match(EMPTY);
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
			setState(246); metaidentifier();
			setState(250);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DOT) {
				{
				{
				setState(247); metaWordNames();
				}
				}
				setState(252);
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
			setState(253); match(DOT);
			setState(254); match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
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
		public List<VarInitContext> varInit() {
			return getRuleContexts(VarInitContext.class);
		}
		public VarInitContext varInit(int i) {
			return getRuleContext(VarInitContext.class,i);
		}
		public List<VariableContext> variable() {
			return getRuleContexts(VariableContext.class);
		}
		public FacetApplyContext facetApply(int i) {
			return getRuleContext(FacetApplyContext.class,i);
		}
		public TerminalNode NEW_LINE_INDENT() { return getToken(TaraGrammar.NEW_LINE_INDENT, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(TaraGrammar.NEWLINE); }
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
		public List<FacetApplyContext> facetApply() {
			return getRuleContexts(FacetApplyContext.class);
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
			setState(256); match(NEW_LINE_INDENT);
			setState(270); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(263);
				switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
				case 1:
					{
					setState(257); variable();
					}
					break;
				case 2:
					{
					setState(258); concept();
					}
					break;
				case 3:
					{
					setState(259); varInit();
					}
					break;
				case 4:
					{
					setState(260); facetApply();
					}
					break;
				case 5:
					{
					setState(261); facetTarget();
					}
					break;
				case 6:
					{
					setState(262); conceptReference();
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
		public WordContext word() {
			return getRuleContext(WordContext.class,0);
		}
		public ResourceContext resource() {
			return getRuleContext(ResourceContext.class,0);
		}
		public BooleanAttributeContext booleanAttribute() {
			return getRuleContext(BooleanAttributeContext.class,0);
		}
		public TerminalNode VAR() { return getToken(TaraGrammar.VAR, 0); }
		public IntegerAttributeContext integerAttribute() {
			return getRuleContext(IntegerAttributeContext.class,0);
		}
		public DocContext doc() {
			return getRuleContext(DocContext.class,0);
		}
		public AnnotationsContext annotations() {
			return getRuleContext(AnnotationsContext.class,0);
		}
		public NaturalAttributeContext naturalAttribute() {
			return getRuleContext(NaturalAttributeContext.class,0);
		}
		public DateAttributeContext dateAttribute() {
			return getRuleContext(DateAttributeContext.class,0);
		}
		public ReferenceContext reference() {
			return getRuleContext(ReferenceContext.class,0);
		}
		public StringAttributeContext stringAttribute() {
			return getRuleContext(StringAttributeContext.class,0);
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
			setState(277);
			_la = _input.LA(1);
			if (_la==DOC) {
				{
				setState(276); doc();
				}
			}

			setState(279); match(VAR);
			setState(289);
			switch (_input.LA(1)) {
			case NATURAL_TYPE:
				{
				setState(280); naturalAttribute();
				}
				break;
			case INT_TYPE:
				{
				setState(281); integerAttribute();
				}
				break;
			case DOUBLE_TYPE:
				{
				setState(282); doubleAttribute();
				}
				break;
			case BOOLEAN_TYPE:
				{
				setState(283); booleanAttribute();
				}
				break;
			case STRING_TYPE:
				{
				setState(284); stringAttribute();
				}
				break;
			case DATE_TYPE:
				{
				setState(285); dateAttribute();
				}
				break;
			case RESOURCE:
				{
				setState(286); resource();
				}
				break;
			case IDENTIFIER:
				{
				setState(287); reference();
				}
				break;
			case WORD:
				{
				setState(288); word();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(292);
			_la = _input.LA(1);
			if (_la==IS) {
				{
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
		public TerminalNode IS() { return getToken(TaraGrammar.IS, 0); }
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
			setState(294); match(IS);
			setState(295); metaidentifier();
			setState(297);
			_la = _input.LA(1);
			if (_la==LEFT_PARENTHESIS) {
				{
				setState(296); parameters();
				}
			}

			setState(301);
			_la = _input.LA(1);
			if (_la==WITH) {
				{
				setState(299); match(WITH);
				setState(300); metaidentifier();
				}
			}

			setState(304);
			_la = _input.LA(1);
			if (_la==NEW_LINE_INDENT) {
				{
				setState(303); body();
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
			setState(306); match(ON);
			setState(307); identifierReference();
			setState(309);
			_la = _input.LA(1);
			if (_la==ALWAYS) {
				{
				setState(308); match(ALWAYS);
				}
			}

			setState(312);
			_la = _input.LA(1);
			if (_la==NEW_LINE_INDENT) {
				{
				setState(311); body();
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
		public DocContext doc() {
			return getRuleContext(DocContext.class,0);
		}
		public AnnotationsContext annotations() {
			return getRuleContext(AnnotationsContext.class,0);
		}
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
			setState(315);
			_la = _input.LA(1);
			if (_la==DOC) {
				{
				setState(314); doc();
				}
			}

			setState(317); match(HAS);
			setState(318); identifierReference();
			setState(320);
			_la = _input.LA(1);
			if (_la==IS) {
				{
				setState(319); annotations();
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
		enterRule(_localctx, 36, RULE_resource);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(322); match(RESOURCE);
			setState(323); attributeType();
			setState(324); match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
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
			setState(326); match(WORD);
			setState(328);
			_la = _input.LA(1);
			if (_la==LIST) {
				{
				setState(327); match(LIST);
				}
			}

			setState(330); match(IDENTIFIER);
			setState(331); match(NEW_LINE_INDENT);
			setState(335); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(332); wordNames();
				setState(333); match(NEWLINE);
				}
				}
				setState(337); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==IDENTIFIER );
			setState(339); match(DEDENT);
			}
		}
		catch (RecognitionException re) {
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
			setState(341); match(IDENTIFIER);
			setState(343);
			_la = _input.LA(1);
			if (_la==STAR) {
				{
				setState(342); match(STAR);
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
		enterRule(_localctx, 42, RULE_reference);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(345); identifierReference();
			setState(347);
			_la = _input.LA(1);
			if (_la==LIST) {
				{
				setState(346); match(LIST);
				}
			}

			setState(349); match(IDENTIFIER);
			setState(352);
			_la = _input.LA(1);
			if (_la==EQUALS) {
				{
				setState(350); match(EQUALS);
				setState(351); match(EMPTY);
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

	public static class BooleanAttributeContext extends ParserRuleContext {
		public TerminalNode EMPTY() { return getToken(TaraGrammar.EMPTY, 0); }
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
			setState(354); match(BOOLEAN_TYPE);
			setState(356);
			_la = _input.LA(1);
			if (_la==LIST) {
				{
				setState(355); match(LIST);
				}
			}

			setState(358); match(IDENTIFIER);
			setState(366);
			switch (_input.LA(1)) {
			case EQUALS:
				{
				setState(359); match(EQUALS);
				setState(361); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(360); booleanValue();
					}
					}
					setState(363); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==BOOLEAN_VALUE );
				}
				break;
			case EMPTY:
				{
				setState(365); match(EMPTY);
				}
				break;
			case IS:
			case NEWLINE:
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

	public static class StringAttributeContext extends ParserRuleContext {
		public TerminalNode EMPTY() { return getToken(TaraGrammar.EMPTY, 0); }
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
			setState(368); match(STRING_TYPE);
			setState(370);
			_la = _input.LA(1);
			if (_la==LIST) {
				{
				setState(369); match(LIST);
				}
			}

			setState(372); match(IDENTIFIER);
			setState(380);
			switch (_input.LA(1)) {
			case EQUALS:
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
					_alt = getInterpreter().adaptivePredict(_input,54,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case EMPTY:
				{
				setState(379); match(EMPTY);
				}
				break;
			case IS:
			case NEWLINE:
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

	public static class NaturalAttributeContext extends ParserRuleContext {
		public TerminalNode EMPTY() { return getToken(TaraGrammar.EMPTY, 0); }
		public AttributeTypeContext attributeType() {
			return getRuleContext(AttributeTypeContext.class,0);
		}
		public TerminalNode NATURAL_TYPE() { return getToken(TaraGrammar.NATURAL_TYPE, 0); }
		public MeasureContext measure() {
			return getRuleContext(MeasureContext.class,0);
		}
		public List<NaturalValueContext> naturalValue() {
			return getRuleContexts(NaturalValueContext.class);
		}
		public TerminalNode EQUALS() { return getToken(TaraGrammar.EQUALS, 0); }
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
		public NaturalValueContext naturalValue(int i) {
			return getRuleContext(NaturalValueContext.class,i);
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
		enterRule(_localctx, 48, RULE_naturalAttribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(382); match(NATURAL_TYPE);
			setState(384);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(383); attributeType();
				}
			}

			setState(387);
			_la = _input.LA(1);
			if (_la==LIST) {
				{
				setState(386); match(LIST);
				}
			}

			setState(389); match(IDENTIFIER);
			setState(400);
			switch (_input.LA(1)) {
			case EQUALS:
				{
				setState(390); match(EQUALS);
				setState(392); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(391); naturalValue();
					}
					}
					setState(394); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NATURAL_VALUE );
				setState(397);
				_la = _input.LA(1);
				if (_la==IDENTIFIER || _la==MEASURE_VALUE) {
					{
					setState(396); measure();
					}
				}

				}
				break;
			case EMPTY:
				{
				setState(399); match(EMPTY);
				}
				break;
			case IS:
			case NEWLINE:
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

	public static class IntegerAttributeContext extends ParserRuleContext {
		public TerminalNode EMPTY() { return getToken(TaraGrammar.EMPTY, 0); }
		public AttributeTypeContext attributeType() {
			return getRuleContext(AttributeTypeContext.class,0);
		}
		public TerminalNode INT_TYPE() { return getToken(TaraGrammar.INT_TYPE, 0); }
		public IntegerValueContext integerValue(int i) {
			return getRuleContext(IntegerValueContext.class,i);
		}
		public MeasureContext measure() {
			return getRuleContext(MeasureContext.class,0);
		}
		public TerminalNode EQUALS() { return getToken(TaraGrammar.EQUALS, 0); }
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
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
		enterRule(_localctx, 50, RULE_integerAttribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(402); match(INT_TYPE);
			setState(404);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(403); attributeType();
				}
			}

			setState(407);
			_la = _input.LA(1);
			if (_la==LIST) {
				{
				setState(406); match(LIST);
				}
			}

			setState(409); match(IDENTIFIER);
			setState(420);
			switch (_input.LA(1)) {
			case EQUALS:
				{
				setState(410); match(EQUALS);
				setState(412); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(411); integerValue();
					}
					}
					setState(414); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NATURAL_VALUE || _la==NEGATIVE_VALUE );
				setState(417);
				_la = _input.LA(1);
				if (_la==IDENTIFIER || _la==MEASURE_VALUE) {
					{
					setState(416); measure();
					}
				}

				}
				break;
			case EMPTY:
				{
				setState(419); match(EMPTY);
				}
				break;
			case IS:
			case NEWLINE:
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

	public static class DoubleAttributeContext extends ParserRuleContext {
		public TerminalNode EMPTY() { return getToken(TaraGrammar.EMPTY, 0); }
		public AttributeTypeContext attributeType() {
			return getRuleContext(AttributeTypeContext.class,0);
		}
		public DoubleValueContext doubleValue(int i) {
			return getRuleContext(DoubleValueContext.class,i);
		}
		public TerminalNode DOUBLE_TYPE() { return getToken(TaraGrammar.DOUBLE_TYPE, 0); }
		public MeasureContext measure() {
			return getRuleContext(MeasureContext.class,0);
		}
		public CountContext count() {
			return getRuleContext(CountContext.class,0);
		}
		public TerminalNode EQUALS() { return getToken(TaraGrammar.EQUALS, 0); }
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
		public List<DoubleValueContext> doubleValue() {
			return getRuleContexts(DoubleValueContext.class);
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
		enterRule(_localctx, 52, RULE_doubleAttribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(422); match(DOUBLE_TYPE);
			setState(424);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(423); attributeType();
				}
			}

			setState(428);
			switch (_input.LA(1)) {
			case LIST:
				{
				setState(426); match(LIST);
				}
				break;
			case LEFT_SQUARE:
				{
				setState(427); count();
				}
				break;
			case IDENTIFIER:
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(430); match(IDENTIFIER);
			setState(441);
			switch (_input.LA(1)) {
			case EQUALS:
				{
				setState(431); match(EQUALS);
				setState(433); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(432); doubleValue();
					}
					}
					setState(435); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NATURAL_VALUE) | (1L << NEGATIVE_VALUE) | (1L << DOUBLE_VALUE))) != 0) );
				setState(438);
				_la = _input.LA(1);
				if (_la==IDENTIFIER || _la==MEASURE_VALUE) {
					{
					setState(437); measure();
					}
				}

				}
				break;
			case EMPTY:
				{
				setState(440); match(EMPTY);
				}
				break;
			case IS:
			case NEWLINE:
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

	public static class DateAttributeContext extends ParserRuleContext {
		public TerminalNode EMPTY() { return getToken(TaraGrammar.EMPTY, 0); }
		public TerminalNode EQUALS() { return getToken(TaraGrammar.EQUALS, 0); }
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
		public TerminalNode DATE_TYPE() { return getToken(TaraGrammar.DATE_TYPE, 0); }
		public List<DateValueContext> dateValue() {
			return getRuleContexts(DateValueContext.class);
		}
		public DateValueContext dateValue(int i) {
			return getRuleContext(DateValueContext.class,i);
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
		enterRule(_localctx, 54, RULE_dateAttribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(443); match(DATE_TYPE);
			setState(445);
			_la = _input.LA(1);
			if (_la==LIST) {
				{
				setState(444); match(LIST);
				}
			}

			setState(447); match(IDENTIFIER);
			setState(455);
			switch (_input.LA(1)) {
			case EQUALS:
				{
				setState(448); match(EQUALS);
				setState(450); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(449); dateValue();
					}
					}
					setState(452); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==DATE_VALUE );
				}
				break;
			case EMPTY:
				{
				setState(454); match(EMPTY);
				}
				break;
			case IS:
			case NEWLINE:
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

	public static class AttributeTypeContext extends ParserRuleContext {
		public MeasureContext measure() {
			return getRuleContext(MeasureContext.class,0);
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
		enterRule(_localctx, 56, RULE_attributeType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(457); match(COLON);
			setState(458); measure();
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 58, RULE_naturalValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(460); match(NATURAL_VALUE);
			}
		}
		catch (RecognitionException re) {
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
			setState(462);
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
		enterRule(_localctx, 62, RULE_doubleValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(464);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NATURAL_VALUE) | (1L << NEGATIVE_VALUE) | (1L << DOUBLE_VALUE))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			setState(466);
			_la = _input.LA(1);
			if (_la==SCIENCE_NOT) {
				{
				setState(465); match(SCIENCE_NOT);
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
		enterRule(_localctx, 64, RULE_booleanValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(468); match(BOOLEAN_VALUE);
			}
		}
		catch (RecognitionException re) {
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
			setState(475);
			switch (_input.LA(1)) {
			case STRING_VALUE:
				enterOuterAlt(_localctx, 1);
				{
				setState(470); match(STRING_VALUE);
				}
				break;
			case STRING_MULTILINE_VALUE_KEY:
			case NEWLINE:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(472);
				_la = _input.LA(1);
				if (_la==NEWLINE) {
					{
					setState(471); match(NEWLINE);
					}
				}

				setState(474); match(STRING_MULTILINE_VALUE_KEY);
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

	public static class DateValueContext extends ParserRuleContext {
		public TerminalNode DATE_VALUE() { return getToken(TaraGrammar.DATE_VALUE, 0); }
		public DateValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dateValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterDateValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitDateValue(this);
		}
	}

	public final DateValueContext dateValue() throws RecognitionException {
		DateValueContext _localctx = new DateValueContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_dateValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(477); match(DATE_VALUE);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 70, RULE_linkValue);
		try {
			setState(481);
			switch (_input.LA(1)) {
			case ADDRESS_VALUE:
				enterOuterAlt(_localctx, 1);
				{
				setState(479); address();
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(480); identifierReference();
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
		enterRule(_localctx, 72, RULE_count);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(483); match(LEFT_SQUARE);
			setState(484); naturalValue();
			setState(485); match(RIGHT_SQUARE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MeasureContext extends ParserRuleContext {
		public TerminalNode MEASURE_VALUE() { return getToken(TaraGrammar.MEASURE_VALUE, 0); }
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
		public MeasureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_measure; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterMeasure(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitMeasure(this);
		}
	}

	public final MeasureContext measure() throws RecognitionException {
		MeasureContext _localctx = new MeasureContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_measure);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(487);
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

	public static class AnnotationsContext extends ParserRuleContext {
		public List<TerminalNode> AGGREGATED() { return getTokens(TaraGrammar.AGGREGATED); }
		public TerminalNode ABSTRACT(int i) {
			return getToken(TaraGrammar.ABSTRACT, i);
		}
		public List<TerminalNode> NAMED() { return getTokens(TaraGrammar.NAMED); }
		public List<TerminalNode> ABSTRACT() { return getTokens(TaraGrammar.ABSTRACT); }
		public TerminalNode AGGREGATED(int i) {
			return getToken(TaraGrammar.AGGREGATED, i);
		}
		public TerminalNode ROOT(int i) {
			return getToken(TaraGrammar.ROOT, i);
		}
		public List<TerminalNode> SINGLE() { return getTokens(TaraGrammar.SINGLE); }
		public TerminalNode IS() { return getToken(TaraGrammar.IS, 0); }
		public TerminalNode TERMINAL(int i) {
			return getToken(TaraGrammar.TERMINAL, i);
		}
		public List<TerminalNode> TERMINAL() { return getTokens(TaraGrammar.TERMINAL); }
		public TerminalNode ADDRESSED(int i) {
			return getToken(TaraGrammar.ADDRESSED, i);
		}
		public TerminalNode INTENTION(int i) {
			return getToken(TaraGrammar.INTENTION, i);
		}
		public List<TerminalNode> PROPERTY() { return getTokens(TaraGrammar.PROPERTY); }
		public TerminalNode REQUIRED(int i) {
			return getToken(TaraGrammar.REQUIRED, i);
		}
		public TerminalNode LOCAL(int i) {
			return getToken(TaraGrammar.LOCAL, i);
		}
		public List<TerminalNode> INTENTION() { return getTokens(TaraGrammar.INTENTION); }
		public List<TerminalNode> PLUS() { return getTokens(TaraGrammar.PLUS); }
		public List<TerminalNode> COMPONENT() { return getTokens(TaraGrammar.COMPONENT); }
		public TerminalNode COMPONENT(int i) {
			return getToken(TaraGrammar.COMPONENT, i);
		}
		public TerminalNode COMPOSED(int i) {
			return getToken(TaraGrammar.COMPOSED, i);
		}
		public List<TerminalNode> FACET() { return getTokens(TaraGrammar.FACET); }
		public List<TerminalNode> ROOT() { return getTokens(TaraGrammar.ROOT); }
		public TerminalNode PROPERTY(int i) {
			return getToken(TaraGrammar.PROPERTY, i);
		}
		public List<TerminalNode> COMPOSED() { return getTokens(TaraGrammar.COMPOSED); }
		public TerminalNode FACET(int i) {
			return getToken(TaraGrammar.FACET, i);
		}
		public TerminalNode PLUS(int i) {
			return getToken(TaraGrammar.PLUS, i);
		}
		public List<TerminalNode> ADDRESSED() { return getTokens(TaraGrammar.ADDRESSED); }
		public List<TerminalNode> REQUIRED() { return getTokens(TaraGrammar.REQUIRED); }
		public TerminalNode MULTIPLE(int i) {
			return getToken(TaraGrammar.MULTIPLE, i);
		}
		public TerminalNode NAMED(int i) {
			return getToken(TaraGrammar.NAMED, i);
		}
		public List<TerminalNode> MULTIPLE() { return getTokens(TaraGrammar.MULTIPLE); }
		public TerminalNode SINGLE(int i) {
			return getToken(TaraGrammar.SINGLE, i);
		}
		public List<TerminalNode> LOCAL() { return getTokens(TaraGrammar.LOCAL); }
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
			setState(489); match(IS);
			setState(494); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(491);
				_la = _input.LA(1);
				if (_la==PLUS) {
					{
					setState(490); match(PLUS);
					}
				}

				setState(493);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABSTRACT) | (1L << SINGLE) | (1L << REQUIRED) | (1L << COMPONENT) | (1L << FACET) | (1L << INTENTION) | (1L << TERMINAL) | (1L << NAMED) | (1L << PROPERTY) | (1L << LOCAL) | (1L << ADDRESSED) | (1L << AGGREGATED) | (1L << COMPOSED) | (1L << MULTIPLE) | (1L << ROOT))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				}
				}
				setState(496); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABSTRACT) | (1L << SINGLE) | (1L << REQUIRED) | (1L << COMPONENT) | (1L << FACET) | (1L << INTENTION) | (1L << TERMINAL) | (1L << NAMED) | (1L << PROPERTY) | (1L << LOCAL) | (1L << ADDRESSED) | (1L << AGGREGATED) | (1L << COMPOSED) | (1L << MULTIPLE) | (1L << ROOT) | (1L << PLUS))) != 0) );
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 78, RULE_varInit);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(498); match(IDENTIFIER);
			setState(499); match(EQUALS);
			setState(500); initValue();
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 80, RULE_headerReference);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(505);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,80,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(502); hierarchy();
					}
					} 
				}
				setState(507);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,80,_ctx);
			}
			setState(508); match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 82, RULE_identifierReference);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(513);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,81,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(510); hierarchy();
					}
					} 
				}
				setState(515);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,81,_ctx);
			}
			setState(516); match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 84, RULE_hierarchy);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(518); match(IDENTIFIER);
			setState(519); match(DOT);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 86, RULE_metaidentifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(521);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3H\u020e\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\3\2\5\2\\\n\2\3\2\7\2_\n\2\f\2\16\2b\13\2\3\2\3\2\7\2f\n\2"+
		"\f\2\16\2i\13\2\7\2k\n\2\f\2\16\2n\13\2\3\2\3\2\3\3\6\3s\n\3\r\3\16\3"+
		"t\3\4\7\4x\n\4\f\4\16\4{\13\4\3\4\3\4\3\4\3\4\5\4\u0081\n\4\3\4\3\4\3"+
		"\5\6\5\u0086\n\5\r\5\16\5\u0087\3\6\5\6\u008b\n\6\3\6\3\6\5\6\u008f\n"+
		"\6\3\6\5\6\u0092\n\6\3\7\3\7\3\7\3\7\5\7\u0098\n\7\3\7\3\7\3\7\5\7\u009d"+
		"\n\7\3\7\3\7\5\7\u00a1\n\7\5\7\u00a3\n\7\3\7\5\7\u00a6\n\7\3\b\3\b\3\t"+
		"\3\t\5\t\u00ac\n\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\7\n\u00b6\n\n\f\n\16"+
		"\n\u00b9\13\n\3\n\3\n\3\n\7\n\u00be\n\n\f\n\16\n\u00c1\13\n\5\n\u00c3"+
		"\n\n\3\13\3\13\3\13\3\f\6\f\u00c9\n\f\r\f\16\f\u00ca\3\f\6\f\u00ce\n\f"+
		"\r\f\16\f\u00cf\3\f\6\f\u00d3\n\f\r\f\16\f\u00d4\3\f\6\f\u00d8\n\f\r\f"+
		"\16\f\u00d9\3\f\5\f\u00dd\n\f\3\f\6\f\u00e0\n\f\r\f\16\f\u00e1\3\f\5\f"+
		"\u00e5\n\f\3\f\6\f\u00e8\n\f\r\f\16\f\u00e9\3\f\5\f\u00ed\n\f\3\f\6\f"+
		"\u00f0\n\f\r\f\16\f\u00f1\3\f\3\f\3\f\5\f\u00f7\n\f\3\r\3\r\7\r\u00fb"+
		"\n\r\f\r\16\r\u00fe\13\r\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\5\17\u010a\n\17\3\17\6\17\u010d\n\17\r\17\16\17\u010e\6\17\u0111"+
		"\n\17\r\17\16\17\u0112\3\17\3\17\3\20\5\20\u0118\n\20\3\20\3\20\3\20\3"+
		"\20\3\20\3\20\3\20\3\20\3\20\3\20\5\20\u0124\n\20\3\20\5\20\u0127\n\20"+
		"\3\21\3\21\3\21\5\21\u012c\n\21\3\21\3\21\5\21\u0130\n\21\3\21\5\21\u0133"+
		"\n\21\3\22\3\22\3\22\5\22\u0138\n\22\3\22\5\22\u013b\n\22\3\23\5\23\u013e"+
		"\n\23\3\23\3\23\3\23\5\23\u0143\n\23\3\24\3\24\3\24\3\24\3\25\3\25\5\25"+
		"\u014b\n\25\3\25\3\25\3\25\3\25\3\25\6\25\u0152\n\25\r\25\16\25\u0153"+
		"\3\25\3\25\3\26\3\26\5\26\u015a\n\26\3\27\3\27\5\27\u015e\n\27\3\27\3"+
		"\27\3\27\5\27\u0163\n\27\3\30\3\30\5\30\u0167\n\30\3\30\3\30\3\30\6\30"+
		"\u016c\n\30\r\30\16\30\u016d\3\30\5\30\u0171\n\30\3\31\3\31\5\31\u0175"+
		"\n\31\3\31\3\31\3\31\6\31\u017a\n\31\r\31\16\31\u017b\3\31\5\31\u017f"+
		"\n\31\3\32\3\32\5\32\u0183\n\32\3\32\5\32\u0186\n\32\3\32\3\32\3\32\6"+
		"\32\u018b\n\32\r\32\16\32\u018c\3\32\5\32\u0190\n\32\3\32\5\32\u0193\n"+
		"\32\3\33\3\33\5\33\u0197\n\33\3\33\5\33\u019a\n\33\3\33\3\33\3\33\6\33"+
		"\u019f\n\33\r\33\16\33\u01a0\3\33\5\33\u01a4\n\33\3\33\5\33\u01a7\n\33"+
		"\3\34\3\34\5\34\u01ab\n\34\3\34\3\34\5\34\u01af\n\34\3\34\3\34\3\34\6"+
		"\34\u01b4\n\34\r\34\16\34\u01b5\3\34\5\34\u01b9\n\34\3\34\5\34\u01bc\n"+
		"\34\3\35\3\35\5\35\u01c0\n\35\3\35\3\35\3\35\6\35\u01c5\n\35\r\35\16\35"+
		"\u01c6\3\35\5\35\u01ca\n\35\3\36\3\36\3\36\3\37\3\37\3 \3 \3!\3!\5!\u01d5"+
		"\n!\3\"\3\"\3#\3#\5#\u01db\n#\3#\5#\u01de\n#\3$\3$\3%\3%\5%\u01e4\n%\3"+
		"&\3&\3&\3&\3\'\3\'\3(\3(\5(\u01ee\n(\3(\6(\u01f1\n(\r(\16(\u01f2\3)\3"+
		")\3)\3)\3*\7*\u01fa\n*\f*\16*\u01fd\13*\3*\3*\3+\7+\u0202\n+\f+\16+\u0205"+
		"\13+\3+\3+\3,\3,\3,\3-\3-\3-\2\2.\2\4\6\b\n\f\16\20\22\24\26\30\32\34"+
		"\36 \"$&(*,.\60\62\64\668:<>@BDFHJLNPRTVX\2\7\3\289\3\28:\3\2?@\4\2\r"+
		"\26\30\34\4\2\3\3??\u024e\2[\3\2\2\2\4r\3\2\2\2\6y\3\2\2\2\b\u0085\3\2"+
		"\2\2\n\u008a\3\2\2\2\f\u00a2\3\2\2\2\16\u00a7\3\2\2\2\20\u00a9\3\2\2\2"+
		"\22\u00c2\3\2\2\2\24\u00c4\3\2\2\2\26\u00f6\3\2\2\2\30\u00f8\3\2\2\2\32"+
		"\u00ff\3\2\2\2\34\u0102\3\2\2\2\36\u0117\3\2\2\2 \u0128\3\2\2\2\"\u0134"+
		"\3\2\2\2$\u013d\3\2\2\2&\u0144\3\2\2\2(\u0148\3\2\2\2*\u0157\3\2\2\2,"+
		"\u015b\3\2\2\2.\u0164\3\2\2\2\60\u0172\3\2\2\2\62\u0180\3\2\2\2\64\u0194"+
		"\3\2\2\2\66\u01a8\3\2\2\28\u01bd\3\2\2\2:\u01cb\3\2\2\2<\u01ce\3\2\2\2"+
		">\u01d0\3\2\2\2@\u01d2\3\2\2\2B\u01d6\3\2\2\2D\u01dd\3\2\2\2F\u01df\3"+
		"\2\2\2H\u01e3\3\2\2\2J\u01e5\3\2\2\2L\u01e9\3\2\2\2N\u01eb\3\2\2\2P\u01f4"+
		"\3\2\2\2R\u01fb\3\2\2\2T\u0203\3\2\2\2V\u0208\3\2\2\2X\u020b\3\2\2\2Z"+
		"\\\5\4\3\2[Z\3\2\2\2[\\\3\2\2\2\\`\3\2\2\2]_\7A\2\2^]\3\2\2\2_b\3\2\2"+
		"\2`^\3\2\2\2`a\3\2\2\2al\3\2\2\2b`\3\2\2\2cg\5\n\6\2df\7A\2\2ed\3\2\2"+
		"\2fi\3\2\2\2ge\3\2\2\2gh\3\2\2\2hk\3\2\2\2ig\3\2\2\2jc\3\2\2\2kn\3\2\2"+
		"\2lj\3\2\2\2lm\3\2\2\2mo\3\2\2\2nl\3\2\2\2op\7\2\2\3p\3\3\2\2\2qs\5\6"+
		"\4\2rq\3\2\2\2st\3\2\2\2tr\3\2\2\2tu\3\2\2\2u\5\3\2\2\2vx\7A\2\2wv\3\2"+
		"\2\2x{\3\2\2\2yw\3\2\2\2yz\3\2\2\2z|\3\2\2\2{y\3\2\2\2|}\7\5\2\2}\u0080"+
		"\5R*\2~\177\7\7\2\2\177\u0081\7?\2\2\u0080~\3\2\2\2\u0080\u0081\3\2\2"+
		"\2\u0081\u0082\3\2\2\2\u0082\u0083\7A\2\2\u0083\7\3\2\2\2\u0084\u0086"+
		"\7C\2\2\u0085\u0084\3\2\2\2\u0086\u0087\3\2\2\2\u0087\u0085\3\2\2\2\u0087"+
		"\u0088\3\2\2\2\u0088\t\3\2\2\2\u0089\u008b\5\b\5\2\u008a\u0089\3\2\2\2"+
		"\u008a\u008b\3\2\2\2\u008b\u008c\3\2\2\2\u008c\u008e\5\f\7\2\u008d\u008f"+
		"\5N(\2\u008e\u008d\3\2\2\2\u008e\u008f\3\2\2\2\u008f\u0091\3\2\2\2\u0090"+
		"\u0092\5\34\17\2\u0091\u0090\3\2\2\2\u0091\u0092\3\2\2\2\u0092\13\3\2"+
		"\2\2\u0093\u0094\7\4\2\2\u0094\u00a3\7?\2\2\u0095\u0097\5X-\2\u0096\u0098"+
		"\5\20\t\2\u0097\u0096\3\2\2\2\u0097\u0098\3\2\2\2\u0098\u0099\3\2\2\2"+
		"\u0099\u009c\7?\2\2\u009a\u009b\7\f\2\2\u009b\u009d\5T+\2\u009c\u009a"+
		"\3\2\2\2\u009c\u009d\3\2\2\2\u009d\u00a3\3\2\2\2\u009e\u00a0\5X-\2\u009f"+
		"\u00a1\5\20\t\2\u00a0\u009f\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\u00a3\3"+
		"\2\2\2\u00a2\u0093\3\2\2\2\u00a2\u0095\3\2\2\2\u00a2\u009e\3\2\2\2\u00a3"+
		"\u00a5\3\2\2\2\u00a4\u00a6\5\16\b\2\u00a5\u00a4\3\2\2\2\u00a5\u00a6\3"+
		"\2\2\2\u00a6\r\3\2\2\2\u00a7\u00a8\7=\2\2\u00a8\17\3\2\2\2\u00a9\u00ab"+
		"\7\35\2\2\u00aa\u00ac\5\22\n\2\u00ab\u00aa\3\2\2\2\u00ab\u00ac\3\2\2\2"+
		"\u00ac\u00ad\3\2\2\2\u00ad\u00ae\7\36\2\2\u00ae\21\3\2\2\2\u00af\u00b0"+
		"\5\24\13\2\u00b0\u00b7\5\26\f\2\u00b1\u00b2\7&\2\2\u00b2\u00b3\5\24\13"+
		"\2\u00b3\u00b4\5\26\f\2\u00b4\u00b6\3\2\2\2\u00b5\u00b1\3\2\2\2\u00b6"+
		"\u00b9\3\2\2\2\u00b7\u00b5\3\2\2\2\u00b7\u00b8\3\2\2\2\u00b8\u00c3\3\2"+
		"\2\2\u00b9\u00b7\3\2\2\2\u00ba\u00bf\5\26\f\2\u00bb\u00bc\7&\2\2\u00bc"+
		"\u00be\5\26\f\2\u00bd\u00bb\3\2\2\2\u00be\u00c1\3\2\2\2\u00bf\u00bd\3"+
		"\2\2\2\u00bf\u00c0\3\2\2\2\u00c0\u00c3\3\2\2\2\u00c1\u00bf\3\2\2\2\u00c2"+
		"\u00af\3\2\2\2\u00c2\u00ba\3\2\2\2\u00c3\23\3\2\2\2\u00c4\u00c5\7?\2\2"+
		"\u00c5\u00c6\7(\2\2\u00c6\25\3\2\2\2\u00c7\u00c9\5T+\2\u00c8\u00c7\3\2"+
		"\2\2\u00c9\u00ca\3\2\2\2\u00ca\u00c8\3\2\2\2\u00ca\u00cb\3\2\2\2\u00cb"+
		"\u00f7\3\2\2\2\u00cc\u00ce\5D#\2\u00cd\u00cc\3\2\2\2\u00ce\u00cf\3\2\2"+
		"\2\u00cf\u00cd\3\2\2\2\u00cf\u00d0\3\2\2\2\u00d0\u00f7\3\2\2\2\u00d1\u00d3"+
		"\5B\"\2\u00d2\u00d1\3\2\2\2\u00d3\u00d4\3\2\2\2\u00d4\u00d2\3\2\2\2\u00d4"+
		"\u00d5\3\2\2\2\u00d5\u00f7\3\2\2\2\u00d6\u00d8\5<\37\2\u00d7\u00d6\3\2"+
		"\2\2\u00d8\u00d9\3\2\2\2\u00d9\u00d7\3\2\2\2\u00d9\u00da\3\2\2\2\u00da"+
		"\u00dc\3\2\2\2\u00db\u00dd\5L\'\2\u00dc\u00db\3\2\2\2\u00dc\u00dd\3\2"+
		"\2\2\u00dd\u00f7\3\2\2\2\u00de\u00e0\5> \2\u00df\u00de\3\2\2\2\u00e0\u00e1"+
		"\3\2\2\2\u00e1\u00df\3\2\2\2\u00e1\u00e2\3\2\2\2\u00e2\u00e4\3\2\2\2\u00e3"+
		"\u00e5\5L\'\2\u00e4\u00e3\3\2\2\2\u00e4\u00e5\3\2\2\2\u00e5\u00f7\3\2"+
		"\2\2\u00e6\u00e8\5@!\2\u00e7\u00e6\3\2\2\2\u00e8\u00e9\3\2\2\2\u00e9\u00e7"+
		"\3\2\2\2\u00e9\u00ea\3\2\2\2\u00ea\u00ec\3\2\2\2\u00eb\u00ed\5L\'\2\u00ec"+
		"\u00eb\3\2\2\2\u00ec\u00ed\3\2\2\2\u00ed\u00f7\3\2\2\2\u00ee\u00f0\5F"+
		"$\2\u00ef\u00ee\3\2\2\2\u00f0\u00f1\3\2\2\2\u00f1\u00ef\3\2\2\2\u00f1"+
		"\u00f2\3\2\2\2\u00f2\u00f7\3\2\2\2\u00f3\u00f7\5\30\r\2\u00f4\u00f7\5"+
		"H%\2\u00f5\u00f7\7\65\2\2\u00f6\u00c8\3\2\2\2\u00f6\u00cd\3\2\2\2\u00f6"+
		"\u00d2\3\2\2\2\u00f6\u00d7\3\2\2\2\u00f6\u00df\3\2\2\2\u00f6\u00e7\3\2"+
		"\2\2\u00f6\u00ef\3\2\2\2\u00f6\u00f3\3\2\2\2\u00f6\u00f4\3\2\2\2\u00f6"+
		"\u00f5\3\2\2\2\u00f7\27\3\2\2\2\u00f8\u00fc\5X-\2\u00f9\u00fb\5\32\16"+
		"\2\u00fa\u00f9\3\2\2\2\u00fb\u00fe\3\2\2\2\u00fc\u00fa\3\2\2\2\u00fc\u00fd"+
		"\3\2\2\2\u00fd\31\3\2\2\2\u00fe\u00fc\3\2\2\2\u00ff\u0100\7\'\2\2\u0100"+
		"\u0101\7?\2\2\u0101\33\3\2\2\2\u0102\u0110\7F\2\2\u0103\u010a\5\36\20"+
		"\2\u0104\u010a\5\n\6\2\u0105\u010a\5P)\2\u0106\u010a\5 \21\2\u0107\u010a"+
		"\5\"\22\2\u0108\u010a\5$\23\2\u0109\u0103\3\2\2\2\u0109\u0104\3\2\2\2"+
		"\u0109\u0105\3\2\2\2\u0109\u0106\3\2\2\2\u0109\u0107\3\2\2\2\u0109\u0108"+
		"\3\2\2\2\u010a\u010c\3\2\2\2\u010b\u010d\7A\2\2\u010c\u010b\3\2\2\2\u010d"+
		"\u010e\3\2\2\2\u010e\u010c\3\2\2\2\u010e\u010f\3\2\2\2\u010f\u0111\3\2"+
		"\2\2\u0110\u0109\3\2\2\2\u0111\u0112\3\2\2\2\u0112\u0110\3\2\2\2\u0112"+
		"\u0113\3\2\2\2\u0113\u0114\3\2\2\2\u0114\u0115\7G\2\2\u0115\35\3\2\2\2"+
		"\u0116\u0118\5\b\5\2\u0117\u0116\3\2\2\2\u0117\u0118\3\2\2\2\u0118\u0119"+
		"\3\2\2\2\u0119\u0123\7\6\2\2\u011a\u0124\5\62\32\2\u011b\u0124\5\64\33"+
		"\2\u011c\u0124\5\66\34\2\u011d\u0124\5.\30\2\u011e\u0124\5\60\31\2\u011f"+
		"\u0124\58\35\2\u0120\u0124\5&\24\2\u0121\u0124\5,\27\2\u0122\u0124\5("+
		"\25\2\u0123\u011a\3\2\2\2\u0123\u011b\3\2\2\2\u0123\u011c\3\2\2\2\u0123"+
		"\u011d\3\2\2\2\u0123\u011e\3\2\2\2\u0123\u011f\3\2\2\2\u0123\u0120\3\2"+
		"\2\2\u0123\u0121\3\2\2\2\u0123\u0122\3\2\2\2\u0124\u0126\3\2\2\2\u0125"+
		"\u0127\5N(\2\u0126\u0125\3\2\2\2\u0126\u0127\3\2\2\2\u0127\37\3\2\2\2"+
		"\u0128\u0129\7\n\2\2\u0129\u012b\5X-\2\u012a\u012c\5\20\t\2\u012b\u012a"+
		"\3\2\2\2\u012b\u012c\3\2\2\2\u012c\u012f\3\2\2\2\u012d\u012e\7\13\2\2"+
		"\u012e\u0130\5X-\2\u012f\u012d\3\2\2\2\u012f\u0130\3\2\2\2\u0130\u0132"+
		"\3\2\2\2\u0131\u0133\5\34\17\2\u0132\u0131\3\2\2\2\u0132\u0133\3\2\2\2"+
		"\u0133!\3\2\2\2\u0134\u0135\7\t\2\2\u0135\u0137\5T+\2\u0136\u0138\7\27"+
		"\2\2\u0137\u0136\3\2\2\2\u0137\u0138\3\2\2\2\u0138\u013a\3\2\2\2\u0139"+
		"\u013b\5\34\17\2\u013a\u0139\3\2\2\2\u013a\u013b\3\2\2\2\u013b#\3\2\2"+
		"\2\u013c\u013e\5\b\5\2\u013d\u013c\3\2\2\2\u013d\u013e\3\2\2\2\u013e\u013f"+
		"\3\2\2\2\u013f\u0140\7\b\2\2\u0140\u0142\5T+\2\u0141\u0143\5N(\2\u0142"+
		"\u0141\3\2\2\2\u0142\u0143\3\2\2\2\u0143%\3\2\2\2\u0144\u0145\7.\2\2\u0145"+
		"\u0146\5:\36\2\u0146\u0147\7?\2\2\u0147\'\3\2\2\2\u0148\u014a\7-\2\2\u0149"+
		"\u014b\7!\2\2\u014a\u0149\3\2\2\2\u014a\u014b\3\2\2\2\u014b\u014c\3\2"+
		"\2\2\u014c\u014d\7?\2\2\u014d\u0151\7F\2\2\u014e\u014f\5*\26\2\u014f\u0150"+
		"\7A\2\2\u0150\u0152\3\2\2\2\u0151\u014e\3\2\2\2\u0152\u0153\3\2\2\2\u0153"+
		"\u0151\3\2\2\2\u0153\u0154\3\2\2\2\u0154\u0155\3\2\2\2\u0155\u0156\7G"+
		"\2\2\u0156)\3\2\2\2\u0157\u0159\7?\2\2\u0158\u015a\7+\2\2\u0159\u0158"+
		"\3\2\2\2\u0159\u015a\3\2\2\2\u015a+\3\2\2\2\u015b\u015d\5T+\2\u015c\u015e"+
		"\7!\2\2\u015d\u015c\3\2\2\2\u015d\u015e\3\2\2\2\u015e\u015f\3\2\2\2\u015f"+
		"\u0162\7?\2\2\u0160\u0161\7(\2\2\u0161\u0163\7\65\2\2\u0162\u0160\3\2"+
		"\2\2\u0162\u0163\3\2\2\2\u0163-\3\2\2\2\u0164\u0166\7\63\2\2\u0165\u0167"+
		"\7!\2\2\u0166\u0165\3\2\2\2\u0166\u0167\3\2\2\2\u0167\u0168\3\2\2\2\u0168"+
		"\u0170\7?\2\2\u0169\u016b\7(\2\2\u016a\u016c\5B\"\2\u016b\u016a\3\2\2"+
		"\2\u016c\u016d\3\2\2\2\u016d\u016b\3\2\2\2\u016d\u016e\3\2\2\2\u016e\u0171"+
		"\3\2\2\2\u016f\u0171\7\65\2\2\u0170\u0169\3\2\2\2\u0170\u016f\3\2\2\2"+
		"\u0170\u0171\3\2\2\2\u0171/\3\2\2\2\u0172\u0174\7\62\2\2\u0173\u0175\7"+
		"!\2\2\u0174\u0173\3\2\2\2\u0174\u0175\3\2\2\2\u0175\u0176\3\2\2\2\u0176"+
		"\u017e\7?\2\2\u0177\u0179\7(\2\2\u0178\u017a\5D#\2\u0179\u0178\3\2\2\2"+
		"\u017a\u017b\3\2\2\2\u017b\u0179\3\2\2\2\u017b\u017c\3\2\2\2\u017c\u017f"+
		"\3\2\2\2\u017d\u017f\7\65\2\2\u017e\u0177\3\2\2\2\u017e\u017d\3\2\2\2"+
		"\u017e\u017f\3\2\2\2\u017f\61\3\2\2\2\u0180\u0182\7\60\2\2\u0181\u0183"+
		"\5:\36\2\u0182\u0181\3\2\2\2\u0182\u0183\3\2\2\2\u0183\u0185\3\2\2\2\u0184"+
		"\u0186\7!\2\2\u0185\u0184\3\2\2\2\u0185\u0186\3\2\2\2\u0186\u0187\3\2"+
		"\2\2\u0187\u0192\7?\2\2\u0188\u018a\7(\2\2\u0189\u018b\5<\37\2\u018a\u0189"+
		"\3\2\2\2\u018b\u018c\3\2\2\2\u018c\u018a\3\2\2\2\u018c\u018d\3\2\2\2\u018d"+
		"\u018f\3\2\2\2\u018e\u0190\5L\'\2\u018f\u018e\3\2\2\2\u018f\u0190\3\2"+
		"\2\2\u0190\u0193\3\2\2\2\u0191\u0193\7\65\2\2\u0192\u0188\3\2\2\2\u0192"+
		"\u0191\3\2\2\2\u0192\u0193\3\2\2\2\u0193\63\3\2\2\2\u0194\u0196\7/\2\2"+
		"\u0195\u0197\5:\36\2\u0196\u0195\3\2\2\2\u0196\u0197\3\2\2\2\u0197\u0199"+
		"\3\2\2\2\u0198\u019a\7!\2\2\u0199\u0198\3\2\2\2\u0199\u019a\3\2\2\2\u019a"+
		"\u019b\3\2\2\2\u019b\u01a6\7?\2\2\u019c\u019e\7(\2\2\u019d\u019f\5> \2"+
		"\u019e\u019d\3\2\2\2\u019f\u01a0\3\2\2\2\u01a0\u019e\3\2\2\2\u01a0\u01a1"+
		"\3\2\2\2\u01a1\u01a3\3\2\2\2\u01a2\u01a4\5L\'\2\u01a3\u01a2\3\2\2\2\u01a3"+
		"\u01a4\3\2\2\2\u01a4\u01a7\3\2\2\2\u01a5\u01a7\7\65\2\2\u01a6\u019c\3"+
		"\2\2\2\u01a6\u01a5\3\2\2\2\u01a6\u01a7\3\2\2\2\u01a7\65\3\2\2\2\u01a8"+
		"\u01aa\7\61\2\2\u01a9\u01ab\5:\36\2\u01aa\u01a9\3\2\2\2\u01aa\u01ab\3"+
		"\2\2\2\u01ab\u01ae\3\2\2\2\u01ac\u01af\7!\2\2\u01ad\u01af\5J&\2\u01ae"+
		"\u01ac\3\2\2\2\u01ae\u01ad\3\2\2\2\u01ae\u01af\3\2\2\2\u01af\u01b0\3\2"+
		"\2\2\u01b0\u01bb\7?\2\2\u01b1\u01b3\7(\2\2\u01b2\u01b4\5@!\2\u01b3\u01b2"+
		"\3\2\2\2\u01b4\u01b5\3\2\2\2\u01b5\u01b3\3\2\2\2\u01b5\u01b6\3\2\2\2\u01b6"+
		"\u01b8\3\2\2\2\u01b7\u01b9\5L\'\2\u01b8\u01b7\3\2\2\2\u01b8\u01b9\3\2"+
		"\2\2\u01b9\u01bc\3\2\2\2\u01ba\u01bc\7\65\2\2\u01bb\u01b1\3\2\2\2\u01bb"+
		"\u01ba\3\2\2\2\u01bb\u01bc\3\2\2\2\u01bc\67\3\2\2\2\u01bd\u01bf\7\64\2"+
		"\2\u01be\u01c0\7!\2\2\u01bf\u01be\3\2\2\2\u01bf\u01c0\3\2\2\2\u01c0\u01c1"+
		"\3\2\2\2\u01c1\u01c9\7?\2\2\u01c2\u01c4\7(\2\2\u01c3\u01c5\5F$\2\u01c4"+
		"\u01c3\3\2\2\2\u01c5\u01c6\3\2\2\2\u01c6\u01c4\3\2\2\2\u01c6\u01c7\3\2"+
		"\2\2\u01c7\u01ca\3\2\2\2\u01c8\u01ca\7\65\2\2\u01c9\u01c2\3\2\2\2\u01c9"+
		"\u01c8\3\2\2\2\u01c9\u01ca\3\2\2\2\u01ca9\3\2\2\2\u01cb\u01cc\7%\2\2\u01cc"+
		"\u01cd\5L\'\2\u01cd;\3\2\2\2\u01ce\u01cf\78\2\2\u01cf=\3\2\2\2\u01d0\u01d1"+
		"\t\2\2\2\u01d1?\3\2\2\2\u01d2\u01d4\t\3\2\2\u01d3\u01d5\7\66\2\2\u01d4"+
		"\u01d3\3\2\2\2\u01d4\u01d5\3\2\2\2\u01d5A\3\2\2\2\u01d6\u01d7\7\67\2\2"+
		"\u01d7C\3\2\2\2\u01d8\u01de\7;\2\2\u01d9\u01db\7A\2\2\u01da\u01d9\3\2"+
		"\2\2\u01da\u01db\3\2\2\2\u01db\u01dc\3\2\2\2\u01dc\u01de\7<\2\2\u01dd"+
		"\u01d8\3\2\2\2\u01dd\u01da\3\2\2\2\u01deE\3\2\2\2\u01df\u01e0\7>\2\2\u01e0"+
		"G\3\2\2\2\u01e1\u01e4\5\16\b\2\u01e2\u01e4\5T+\2\u01e3\u01e1\3\2\2\2\u01e3"+
		"\u01e2\3\2\2\2\u01e4I\3\2\2\2\u01e5\u01e6\7\37\2\2\u01e6\u01e7\5<\37\2"+
		"\u01e7\u01e8\7 \2\2\u01e8K\3\2\2\2\u01e9\u01ea\t\4\2\2\u01eaM\3\2\2\2"+
		"\u01eb\u01f0\7\n\2\2\u01ec\u01ee\7,\2\2\u01ed\u01ec\3\2\2\2\u01ed\u01ee"+
		"\3\2\2\2\u01ee\u01ef\3\2\2\2\u01ef\u01f1\t\5\2\2\u01f0\u01ed\3\2\2\2\u01f1"+
		"\u01f2\3\2\2\2\u01f2\u01f0\3\2\2\2\u01f2\u01f3\3\2\2\2\u01f3O\3\2\2\2"+
		"\u01f4\u01f5\7?\2\2\u01f5\u01f6\7(\2\2\u01f6\u01f7\5\26\f\2\u01f7Q\3\2"+
		"\2\2\u01f8\u01fa\5V,\2\u01f9\u01f8\3\2\2\2\u01fa\u01fd\3\2\2\2\u01fb\u01f9"+
		"\3\2\2\2\u01fb\u01fc\3\2\2\2\u01fc\u01fe\3\2\2\2\u01fd\u01fb\3\2\2\2\u01fe"+
		"\u01ff\7?\2\2\u01ffS\3\2\2\2\u0200\u0202\5V,\2\u0201\u0200\3\2\2\2\u0202"+
		"\u0205\3\2\2\2\u0203\u0201\3\2\2\2\u0203\u0204\3\2\2\2\u0204\u0206\3\2"+
		"\2\2\u0205\u0203\3\2\2\2\u0206\u0207\7?\2\2\u0207U\3\2\2\2\u0208\u0209"+
		"\7?\2\2\u0209\u020a\7\'\2\2\u020aW\3\2\2\2\u020b\u020c\t\6\2\2\u020cY"+
		"\3\2\2\2T[`glty\u0080\u0087\u008a\u008e\u0091\u0097\u009c\u00a0\u00a2"+
		"\u00a5\u00ab\u00b7\u00bf\u00c2\u00ca\u00cf\u00d4\u00d9\u00dc\u00e1\u00e4"+
		"\u00e9\u00ec\u00f1\u00f6\u00fc\u0109\u010e\u0112\u0117\u0123\u0126\u012b"+
		"\u012f\u0132\u0137\u013a\u013d\u0142\u014a\u0153\u0159\u015d\u0162\u0166"+
		"\u016d\u0170\u0174\u017b\u017e\u0182\u0185\u018c\u018f\u0192\u0196\u0199"+
		"\u01a0\u01a3\u01a6\u01aa\u01ae\u01b5\u01b8\u01bb\u01bf\u01c6\u01c9\u01d4"+
		"\u01da\u01dd\u01e3\u01ed\u01f2\u01fb\u0203";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}