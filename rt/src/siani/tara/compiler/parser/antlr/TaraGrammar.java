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
		MEASURE_VALUE=62, FACET=16, RIGHT_SQUARE=31, STAR=42, NEGATIVE_VALUE=55, 
		DOC=67, LETTER=64, DATE_VALUE=60, SUB=2, DEDENT=71, EQUALS=39, ABSTRACT=12, 
		BOOLEAN_VALUE=53, METAIDENTIFIER=1, RESOURCE=45, ADDRESS_VALUE=59, MULTIPLE=26, 
		UNKNOWN_TOKEN=72, WORD=44, AS=6, HAS=7, BOX=4, RIGHT_PARENTHESIS=29, ALWAYS=22, 
		COMMA=37, IS=9, IDENTIFIER=61, LEFT_PARENTHESIS=28, STRING_MULTILINE_VALUE_KEY=58, 
		VAR=5, NL=69, DIGIT=63, INTENTION=17, DOT=38, COMPONENT=15, STRING_VALUE=57, 
		DOUBLE_VALUE=56, WITH=10, COMPOSED=25, POSITIVE=43, NEW_LINE_INDENT=70, 
		AGGREGATED=24, ADDRESSED=23, TERMINAL=18, NATURAL_TYPE=47, ON=8, AMPERSAND=35, 
		CLOSE_INLINE=34, BOOLEAN_TYPE=50, DOUBLE_TYPE=48, SEMICOLON=41, REQUIRED=14, 
		INT_TYPE=46, LIST=32, ROOT=27, EMPTY=52, COLON=36, NATURAL_VALUE=54, NEWLINE=65, 
		SINGLE=13, PROPERTY=20, SPACES=66, SP=68, APHOSTROPHE=40, STRING_TYPE=49, 
		NAMED=19, DATE_TYPE=51, INLINE=33, USE=3, UNIVERSAL=21, EXTENDS=11, LEFT_SQUARE=30;
	public static final String[] tokenNames = {
		"<INVALID>", "'Concept'", "'sub'", "'use'", "'box'", "'var'", "'as'", 
		"'has'", "'on'", "'is'", "'with'", "'extends'", "'abstract'", "'single'", 
		"'required'", "'component'", "'facet'", "'intention'", "'terminal'", "'named'", 
		"'property'", "'universal'", "'always'", "'addressed'", "'aggregated'", 
		"'composed'", "'multiple'", "'root'", "'('", "')'", "'['", "']'", "'...'", 
		"'>'", "'<'", "'&'", "':'", "','", "'.'", "'='", "'''", "SEMICOLON", "'*'", 
		"'+'", "'word'", "'resource'", "'integer'", "'natural'", "'double'", "'string'", 
		"'boolean'", "'date'", "'empty'", "BOOLEAN_VALUE", "NATURAL_VALUE", "NEGATIVE_VALUE", 
		"DOUBLE_VALUE", "STRING_VALUE", "STRING_MULTILINE_VALUE_KEY", "ADDRESS_VALUE", 
		"DATE_VALUE", "IDENTIFIER", "MEASURE_VALUE", "DIGIT", "LETTER", "NEWLINE", 
		"SPACES", "DOC", "SP", "NL", "'indent'", "'dedent'", "UNKNOWN_TOKEN"
	};
	public static final int
		RULE_root = 0, RULE_header = 1, RULE_box = 2, RULE_imports = 3, RULE_anImport = 4, 
		RULE_doc = 5, RULE_concept = 6, RULE_signature = 7, RULE_address = 8, 
		RULE_parameters = 9, RULE_parameterList = 10, RULE_explicit = 11, RULE_initValue = 12, 
		RULE_metaWord = 13, RULE_metaWordNames = 14, RULE_body = 15, RULE_variable = 16, 
		RULE_facetApply = 17, RULE_facetTarget = 18, RULE_conceptReference = 19, 
		RULE_resource = 20, RULE_word = 21, RULE_wordNames = 22, RULE_reference = 23, 
		RULE_booleanAttribute = 24, RULE_stringAttribute = 25, RULE_naturalAttribute = 26, 
		RULE_integerAttribute = 27, RULE_doubleAttribute = 28, RULE_dateAttribute = 29, 
		RULE_attributeType = 30, RULE_naturalValue = 31, RULE_integerValue = 32, 
		RULE_doubleValue = 33, RULE_booleanValue = 34, RULE_stringValue = 35, 
		RULE_dateValue = 36, RULE_linkValue = 37, RULE_count = 38, RULE_measure = 39, 
		RULE_annotations = 40, RULE_varInit = 41, RULE_headerReference = 42, RULE_identifierReference = 43, 
		RULE_hierarchy = 44, RULE_metaidentifier = 45;
	public static final String[] ruleNames = {
		"root", "header", "box", "imports", "anImport", "doc", "concept", "signature", 
		"address", "parameters", "parameterList", "explicit", "initValue", "metaWord", 
		"metaWordNames", "body", "variable", "facetApply", "facetTarget", "conceptReference", 
		"resource", "word", "wordNames", "reference", "booleanAttribute", "stringAttribute", 
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
		public TerminalNode NEWLINE(int i) {
			return getToken(TaraGrammar.NEWLINE, i);
		}
		public ConceptContext concept(int i) {
			return getRuleContext(ConceptContext.class,i);
		}
		public HeaderContext header() {
			return getRuleContext(HeaderContext.class,0);
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
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(95);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(92); match(NEWLINE);
					}
					} 
				}
				setState(97);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			}
			setState(99);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				{
				setState(98); header();
				}
				break;
			}
			setState(104);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(101); match(NEWLINE);
				}
				}
				setState(106);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class HeaderContext extends ParserRuleContext {
		public ImportsContext imports() {
			return getRuleContext(ImportsContext.class,0);
		}
		public BoxContext box() {
			return getRuleContext(BoxContext.class,0);
		}
		public HeaderContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_header; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterHeader(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitHeader(this);
		}
	}

	public final HeaderContext header() throws RecognitionException {
		HeaderContext _localctx = new HeaderContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_header);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(122);
			_la = _input.LA(1);
			if (_la==BOX) {
				{
				setState(121); box();
				}
			}

			setState(125);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(124); imports();
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

	public static class BoxContext extends ParserRuleContext {
		public HeaderReferenceContext headerReference() {
			return getRuleContext(HeaderReferenceContext.class,0);
		}
		public TerminalNode BOX() { return getToken(TaraGrammar.BOX, 0); }
		public BoxContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_box; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterBox(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitBox(this);
		}
	}

	public final BoxContext box() throws RecognitionException {
		BoxContext _localctx = new BoxContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_box);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(127); match(BOX);
			setState(128); headerReference();
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 6, RULE_imports);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(131); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(130); anImport();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(133); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
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
		enterRule(_localctx, 8, RULE_anImport);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(136); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(135); match(NEWLINE);
				}
				}
				setState(138); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NEWLINE );
			setState(140); match(USE);
			setState(141); headerReference();
			setState(144);
			_la = _input.LA(1);
			if (_la==AS) {
				{
				setState(142); match(AS);
				setState(143); match(IDENTIFIER);
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
		enterRule(_localctx, 10, RULE_doc);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(147); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(146); match(DOC);
				}
				}
				setState(149); 
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
		enterRule(_localctx, 12, RULE_concept);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(152);
			_la = _input.LA(1);
			if (_la==DOC) {
				{
				setState(151); doc();
				}
			}

			setState(154); signature();
			setState(156);
			_la = _input.LA(1);
			if (_la==IS) {
				{
				setState(155); annotations();
				}
			}

			setState(159);
			_la = _input.LA(1);
			if (_la==NEW_LINE_INDENT) {
				{
				setState(158); body();
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
		enterRule(_localctx, 14, RULE_signature);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(176);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				{
				{
				setState(161); match(SUB);
				setState(162); match(IDENTIFIER);
				}
				}
				break;
			case 2:
				{
				{
				setState(163); metaidentifier();
				setState(165);
				_la = _input.LA(1);
				if (_la==LEFT_PARENTHESIS) {
					{
					setState(164); parameters();
					}
				}

				setState(167); match(IDENTIFIER);
				setState(170);
				_la = _input.LA(1);
				if (_la==EXTENDS) {
					{
					setState(168); match(EXTENDS);
					setState(169); identifierReference();
					}
				}

				}
				}
				break;
			case 3:
				{
				setState(172); metaidentifier();
				setState(174);
				_la = _input.LA(1);
				if (_la==LEFT_PARENTHESIS) {
					{
					setState(173); parameters();
					}
				}

				}
				break;
			}
			setState(179);
			_la = _input.LA(1);
			if (_la==ADDRESS_VALUE) {
				{
				setState(178); address();
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
		enterRule(_localctx, 16, RULE_address);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(181); match(ADDRESS_VALUE);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 18, RULE_parameters);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(183); match(LEFT_PARENTHESIS);
			setState(185);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << METAIDENTIFIER) | (1L << EMPTY) | (1L << BOOLEAN_VALUE) | (1L << NATURAL_VALUE) | (1L << NEGATIVE_VALUE) | (1L << DOUBLE_VALUE) | (1L << STRING_VALUE) | (1L << STRING_MULTILINE_VALUE_KEY) | (1L << ADDRESS_VALUE) | (1L << DATE_VALUE) | (1L << IDENTIFIER))) != 0) || _la==NEWLINE) {
				{
				setState(184); parameterList();
				}
			}

			setState(187); match(RIGHT_PARENTHESIS);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 20, RULE_parameterList);
		int _la;
		try {
			setState(208);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(189); explicit();
				setState(190); initValue();
				setState(197);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(191); match(COMMA);
					setState(192); explicit();
					setState(193); initValue();
					}
					}
					setState(199);
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
				setState(200); initValue();
				setState(205);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(201); match(COMMA);
					setState(202); initValue();
					}
					}
					setState(207);
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
		enterRule(_localctx, 22, RULE_explicit);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(210); match(IDENTIFIER);
			setState(211); match(EQUALS);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 24, RULE_initValue);
		int _la;
		try {
			int _alt;
			setState(260);
			switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(214); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(213); identifierReference();
					}
					}
					setState(216); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==IDENTIFIER );
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(219); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(218); stringValue();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(221); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(224); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(223); booleanValue();
					}
					}
					setState(226); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==BOOLEAN_VALUE );
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(229); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(228); naturalValue();
					}
					}
					setState(231); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NATURAL_VALUE );
				setState(234);
				_la = _input.LA(1);
				if (_la==IDENTIFIER || _la==MEASURE_VALUE) {
					{
					setState(233); measure();
					}
				}

				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(237); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(236); integerValue();
					}
					}
					setState(239); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NATURAL_VALUE || _la==NEGATIVE_VALUE );
				setState(242);
				_la = _input.LA(1);
				if (_la==IDENTIFIER || _la==MEASURE_VALUE) {
					{
					setState(241); measure();
					}
				}

				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(245); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(244); doubleValue();
					}
					}
					setState(247); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NATURAL_VALUE) | (1L << NEGATIVE_VALUE) | (1L << DOUBLE_VALUE))) != 0) );
				setState(250);
				_la = _input.LA(1);
				if (_la==IDENTIFIER || _la==MEASURE_VALUE) {
					{
					setState(249); measure();
					}
				}

				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(253); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(252); dateValue();
					}
					}
					setState(255); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==DATE_VALUE );
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(257); metaWord();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(258); linkValue();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(259); match(EMPTY);
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
		enterRule(_localctx, 26, RULE_metaWord);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(262); metaidentifier();
			setState(266);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DOT) {
				{
				{
				setState(263); metaWordNames();
				}
				}
				setState(268);
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
		enterRule(_localctx, 28, RULE_metaWordNames);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(269); match(DOT);
			setState(270); match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 30, RULE_body);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(272); match(NEW_LINE_INDENT);
			setState(286); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(279);
				switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
				case 1:
					{
					setState(273); variable();
					}
					break;
				case 2:
					{
					setState(274); concept();
					}
					break;
				case 3:
					{
					setState(275); varInit();
					}
					break;
				case 4:
					{
					setState(276); facetApply();
					}
					break;
				case 5:
					{
					setState(277); facetTarget();
					}
					break;
				case 6:
					{
					setState(278); conceptReference();
					}
					break;
				}
				setState(282); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(281); match(NEWLINE);
					}
					}
					setState(284); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NEWLINE );
				}
				}
				setState(288); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << METAIDENTIFIER) | (1L << SUB) | (1L << VAR) | (1L << HAS) | (1L << ON) | (1L << IS) | (1L << IDENTIFIER))) != 0) || _la==DOC );
			setState(290); match(DEDENT);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 32, RULE_variable);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(293);
			_la = _input.LA(1);
			if (_la==DOC) {
				{
				setState(292); doc();
				}
			}

			setState(295); match(VAR);
			setState(305);
			switch (_input.LA(1)) {
			case NATURAL_TYPE:
				{
				setState(296); naturalAttribute();
				}
				break;
			case INT_TYPE:
				{
				setState(297); integerAttribute();
				}
				break;
			case DOUBLE_TYPE:
				{
				setState(298); doubleAttribute();
				}
				break;
			case BOOLEAN_TYPE:
				{
				setState(299); booleanAttribute();
				}
				break;
			case STRING_TYPE:
				{
				setState(300); stringAttribute();
				}
				break;
			case DATE_TYPE:
				{
				setState(301); dateAttribute();
				}
				break;
			case RESOURCE:
				{
				setState(302); resource();
				}
				break;
			case IDENTIFIER:
				{
				setState(303); reference();
				}
				break;
			case WORD:
				{
				setState(304); word();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(308);
			_la = _input.LA(1);
			if (_la==IS) {
				{
				setState(307); annotations();
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
		enterRule(_localctx, 34, RULE_facetApply);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(310); match(IS);
			setState(311); metaidentifier();
			setState(313);
			_la = _input.LA(1);
			if (_la==LEFT_PARENTHESIS) {
				{
				setState(312); parameters();
				}
			}

			setState(317);
			_la = _input.LA(1);
			if (_la==WITH) {
				{
				setState(315); match(WITH);
				setState(316); metaidentifier();
				}
			}

			setState(320);
			_la = _input.LA(1);
			if (_la==NEW_LINE_INDENT) {
				{
				setState(319); body();
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
		enterRule(_localctx, 36, RULE_facetTarget);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(322); match(ON);
			setState(323); identifierReference();
			setState(325);
			_la = _input.LA(1);
			if (_la==ALWAYS) {
				{
				setState(324); match(ALWAYS);
				}
			}

			setState(328);
			_la = _input.LA(1);
			if (_la==NEW_LINE_INDENT) {
				{
				setState(327); body();
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
		enterRule(_localctx, 38, RULE_conceptReference);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(331);
			_la = _input.LA(1);
			if (_la==DOC) {
				{
				setState(330); doc();
				}
			}

			setState(333); match(HAS);
			setState(334); identifierReference();
			setState(336);
			_la = _input.LA(1);
			if (_la==IS) {
				{
				setState(335); annotations();
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
		enterRule(_localctx, 42, RULE_word);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(342); match(WORD);
			setState(344);
			_la = _input.LA(1);
			if (_la==LIST) {
				{
				setState(343); match(LIST);
				}
			}

			setState(346); match(IDENTIFIER);
			setState(347); match(NEW_LINE_INDENT);
			setState(351); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(348); wordNames();
				setState(349); match(NEWLINE);
				}
				}
				setState(353); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==IDENTIFIER );
			setState(355); match(DEDENT);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 44, RULE_wordNames);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(357); match(IDENTIFIER);
			setState(359);
			_la = _input.LA(1);
			if (_la==STAR) {
				{
				setState(358); match(STAR);
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
		enterRule(_localctx, 46, RULE_reference);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(361); identifierReference();
			setState(363);
			_la = _input.LA(1);
			if (_la==LIST) {
				{
				setState(362); match(LIST);
				}
			}

			setState(365); match(IDENTIFIER);
			setState(368);
			_la = _input.LA(1);
			if (_la==EQUALS) {
				{
				setState(366); match(EQUALS);
				setState(367); match(EMPTY);
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
		enterRule(_localctx, 48, RULE_booleanAttribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(370); match(BOOLEAN_TYPE);
			setState(372);
			_la = _input.LA(1);
			if (_la==LIST) {
				{
				setState(371); match(LIST);
				}
			}

			setState(374); match(IDENTIFIER);
			setState(382);
			switch (_input.LA(1)) {
			case EQUALS:
				{
				setState(375); match(EQUALS);
				setState(377); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(376); booleanValue();
					}
					}
					setState(379); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==BOOLEAN_VALUE );
				}
				break;
			case EMPTY:
				{
				setState(381); match(EMPTY);
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
		enterRule(_localctx, 50, RULE_stringAttribute);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(384); match(STRING_TYPE);
			setState(386);
			_la = _input.LA(1);
			if (_la==LIST) {
				{
				setState(385); match(LIST);
				}
			}

			setState(388); match(IDENTIFIER);
			setState(396);
			switch (_input.LA(1)) {
			case EQUALS:
				{
				setState(389); match(EQUALS);
				setState(391); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(390); stringValue();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(393); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,57,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case EMPTY:
				{
				setState(395); match(EMPTY);
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
		enterRule(_localctx, 52, RULE_naturalAttribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(398); match(NATURAL_TYPE);
			setState(400);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(399); attributeType();
				}
			}

			setState(403);
			_la = _input.LA(1);
			if (_la==LIST) {
				{
				setState(402); match(LIST);
				}
			}

			setState(405); match(IDENTIFIER);
			setState(416);
			switch (_input.LA(1)) {
			case EQUALS:
				{
				setState(406); match(EQUALS);
				setState(408); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(407); naturalValue();
					}
					}
					setState(410); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NATURAL_VALUE );
				setState(413);
				_la = _input.LA(1);
				if (_la==IDENTIFIER || _la==MEASURE_VALUE) {
					{
					setState(412); measure();
					}
				}

				}
				break;
			case EMPTY:
				{
				setState(415); match(EMPTY);
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
				setState(419); attributeType();
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
			setState(436);
			switch (_input.LA(1)) {
			case EQUALS:
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
					setState(432); measure();
					}
				}

				}
				break;
			case EMPTY:
				{
				setState(435); match(EMPTY);
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
		enterRule(_localctx, 56, RULE_doubleAttribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(438); match(DOUBLE_TYPE);
			setState(440);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(439); attributeType();
				}
			}

			setState(444);
			switch (_input.LA(1)) {
			case LIST:
				{
				setState(442); match(LIST);
				}
				break;
			case LEFT_SQUARE:
				{
				setState(443); count();
				}
				break;
			case IDENTIFIER:
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(446); match(IDENTIFIER);
			setState(457);
			switch (_input.LA(1)) {
			case EQUALS:
				{
				setState(447); match(EQUALS);
				setState(449); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(448); doubleValue();
					}
					}
					setState(451); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NATURAL_VALUE) | (1L << NEGATIVE_VALUE) | (1L << DOUBLE_VALUE))) != 0) );
				setState(454);
				_la = _input.LA(1);
				if (_la==IDENTIFIER || _la==MEASURE_VALUE) {
					{
					setState(453); measure();
					}
				}

				}
				break;
			case EMPTY:
				{
				setState(456); match(EMPTY);
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
		enterRule(_localctx, 58, RULE_dateAttribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(459); match(DATE_TYPE);
			setState(461);
			_la = _input.LA(1);
			if (_la==LIST) {
				{
				setState(460); match(LIST);
				}
			}

			setState(463); match(IDENTIFIER);
			setState(471);
			switch (_input.LA(1)) {
			case EQUALS:
				{
				setState(464); match(EQUALS);
				setState(466); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(465); dateValue();
					}
					}
					setState(468); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==DATE_VALUE );
				}
				break;
			case EMPTY:
				{
				setState(470); match(EMPTY);
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
		enterRule(_localctx, 60, RULE_attributeType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(473); match(COLON);
			setState(474); measure();
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 62, RULE_naturalValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(476); match(NATURAL_VALUE);
			}
		}
		catch (RecognitionException re) {
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
			setState(478);
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
		enterRule(_localctx, 66, RULE_doubleValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(480);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NATURAL_VALUE) | (1L << NEGATIVE_VALUE) | (1L << DOUBLE_VALUE))) != 0)) ) {
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
			setState(482); match(BOOLEAN_VALUE);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 70, RULE_stringValue);
		int _la;
		try {
			setState(489);
			switch (_input.LA(1)) {
			case STRING_VALUE:
				enterOuterAlt(_localctx, 1);
				{
				setState(484); match(STRING_VALUE);
				}
				break;
			case STRING_MULTILINE_VALUE_KEY:
			case NEWLINE:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(486);
				_la = _input.LA(1);
				if (_la==NEWLINE) {
					{
					setState(485); match(NEWLINE);
					}
				}

				setState(488); match(STRING_MULTILINE_VALUE_KEY);
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
		enterRule(_localctx, 72, RULE_dateValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(491); match(DATE_VALUE);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 74, RULE_linkValue);
		try {
			setState(495);
			switch (_input.LA(1)) {
			case ADDRESS_VALUE:
				enterOuterAlt(_localctx, 1);
				{
				setState(493); address();
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(494); identifierReference();
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
		enterRule(_localctx, 76, RULE_count);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(497); match(LEFT_SQUARE);
			setState(498); naturalValue();
			setState(499); match(RIGHT_SQUARE);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 78, RULE_measure);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(501);
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
		public List<TerminalNode> INTENTION() { return getTokens(TaraGrammar.INTENTION); }
		public List<TerminalNode> COMPONENT() { return getTokens(TaraGrammar.COMPONENT); }
		public TerminalNode COMPONENT(int i) {
			return getToken(TaraGrammar.COMPONENT, i);
		}
		public TerminalNode COMPOSED(int i) {
			return getToken(TaraGrammar.COMPOSED, i);
		}
		public List<TerminalNode> FACET() { return getTokens(TaraGrammar.FACET); }
		public List<TerminalNode> ROOT() { return getTokens(TaraGrammar.ROOT); }
		public List<TerminalNode> UNIVERSAL() { return getTokens(TaraGrammar.UNIVERSAL); }
		public TerminalNode PROPERTY(int i) {
			return getToken(TaraGrammar.PROPERTY, i);
		}
		public List<TerminalNode> COMPOSED() { return getTokens(TaraGrammar.COMPOSED); }
		public TerminalNode FACET(int i) {
			return getToken(TaraGrammar.FACET, i);
		}
		public TerminalNode UNIVERSAL(int i) {
			return getToken(TaraGrammar.UNIVERSAL, i);
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
		enterRule(_localctx, 80, RULE_annotations);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(503); match(IS);
			setState(505); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(504);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABSTRACT) | (1L << SINGLE) | (1L << REQUIRED) | (1L << COMPONENT) | (1L << FACET) | (1L << INTENTION) | (1L << TERMINAL) | (1L << NAMED) | (1L << PROPERTY) | (1L << UNIVERSAL) | (1L << ADDRESSED) | (1L << AGGREGATED) | (1L << COMPOSED) | (1L << MULTIPLE) | (1L << ROOT))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				}
				}
				setState(507); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABSTRACT) | (1L << SINGLE) | (1L << REQUIRED) | (1L << COMPONENT) | (1L << FACET) | (1L << INTENTION) | (1L << TERMINAL) | (1L << NAMED) | (1L << PROPERTY) | (1L << UNIVERSAL) | (1L << ADDRESSED) | (1L << AGGREGATED) | (1L << COMPOSED) | (1L << MULTIPLE) | (1L << ROOT))) != 0) );
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 82, RULE_varInit);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(509); match(IDENTIFIER);
			setState(510); match(EQUALS);
			setState(511); initValue();
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 84, RULE_headerReference);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(516);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,81,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(513); hierarchy();
					}
					} 
				}
				setState(518);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,81,_ctx);
			}
			setState(519); match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 86, RULE_identifierReference);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(524);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,82,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(521); hierarchy();
					}
					} 
				}
				setState(526);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,82,_ctx);
			}
			setState(527); match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 88, RULE_hierarchy);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(529); match(IDENTIFIER);
			setState(530); match(DOT);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 90, RULE_metaidentifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(532);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3J\u0219\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\3\2\7\2`\n\2\f\2\16\2c\13\2\3\2\5\2f\n\2\3\2\7"+
		"\2i\n\2\f\2\16\2l\13\2\3\2\3\2\7\2p\n\2\f\2\16\2s\13\2\7\2u\n\2\f\2\16"+
		"\2x\13\2\3\2\3\2\3\3\5\3}\n\3\3\3\5\3\u0080\n\3\3\4\3\4\3\4\3\5\6\5\u0086"+
		"\n\5\r\5\16\5\u0087\3\6\6\6\u008b\n\6\r\6\16\6\u008c\3\6\3\6\3\6\3\6\5"+
		"\6\u0093\n\6\3\7\6\7\u0096\n\7\r\7\16\7\u0097\3\b\5\b\u009b\n\b\3\b\3"+
		"\b\5\b\u009f\n\b\3\b\5\b\u00a2\n\b\3\t\3\t\3\t\3\t\5\t\u00a8\n\t\3\t\3"+
		"\t\3\t\5\t\u00ad\n\t\3\t\3\t\5\t\u00b1\n\t\5\t\u00b3\n\t\3\t\5\t\u00b6"+
		"\n\t\3\n\3\n\3\13\3\13\5\13\u00bc\n\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\7\f\u00c6\n\f\f\f\16\f\u00c9\13\f\3\f\3\f\3\f\7\f\u00ce\n\f\f\f\16"+
		"\f\u00d1\13\f\5\f\u00d3\n\f\3\r\3\r\3\r\3\16\6\16\u00d9\n\16\r\16\16\16"+
		"\u00da\3\16\6\16\u00de\n\16\r\16\16\16\u00df\3\16\6\16\u00e3\n\16\r\16"+
		"\16\16\u00e4\3\16\6\16\u00e8\n\16\r\16\16\16\u00e9\3\16\5\16\u00ed\n\16"+
		"\3\16\6\16\u00f0\n\16\r\16\16\16\u00f1\3\16\5\16\u00f5\n\16\3\16\6\16"+
		"\u00f8\n\16\r\16\16\16\u00f9\3\16\5\16\u00fd\n\16\3\16\6\16\u0100\n\16"+
		"\r\16\16\16\u0101\3\16\3\16\3\16\5\16\u0107\n\16\3\17\3\17\7\17\u010b"+
		"\n\17\f\17\16\17\u010e\13\17\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3"+
		"\21\3\21\5\21\u011a\n\21\3\21\6\21\u011d\n\21\r\21\16\21\u011e\6\21\u0121"+
		"\n\21\r\21\16\21\u0122\3\21\3\21\3\22\5\22\u0128\n\22\3\22\3\22\3\22\3"+
		"\22\3\22\3\22\3\22\3\22\3\22\3\22\5\22\u0134\n\22\3\22\5\22\u0137\n\22"+
		"\3\23\3\23\3\23\5\23\u013c\n\23\3\23\3\23\5\23\u0140\n\23\3\23\5\23\u0143"+
		"\n\23\3\24\3\24\3\24\5\24\u0148\n\24\3\24\5\24\u014b\n\24\3\25\5\25\u014e"+
		"\n\25\3\25\3\25\3\25\5\25\u0153\n\25\3\26\3\26\3\26\3\26\3\27\3\27\5\27"+
		"\u015b\n\27\3\27\3\27\3\27\3\27\3\27\6\27\u0162\n\27\r\27\16\27\u0163"+
		"\3\27\3\27\3\30\3\30\5\30\u016a\n\30\3\31\3\31\5\31\u016e\n\31\3\31\3"+
		"\31\3\31\5\31\u0173\n\31\3\32\3\32\5\32\u0177\n\32\3\32\3\32\3\32\6\32"+
		"\u017c\n\32\r\32\16\32\u017d\3\32\5\32\u0181\n\32\3\33\3\33\5\33\u0185"+
		"\n\33\3\33\3\33\3\33\6\33\u018a\n\33\r\33\16\33\u018b\3\33\5\33\u018f"+
		"\n\33\3\34\3\34\5\34\u0193\n\34\3\34\5\34\u0196\n\34\3\34\3\34\3\34\6"+
		"\34\u019b\n\34\r\34\16\34\u019c\3\34\5\34\u01a0\n\34\3\34\5\34\u01a3\n"+
		"\34\3\35\3\35\5\35\u01a7\n\35\3\35\5\35\u01aa\n\35\3\35\3\35\3\35\6\35"+
		"\u01af\n\35\r\35\16\35\u01b0\3\35\5\35\u01b4\n\35\3\35\5\35\u01b7\n\35"+
		"\3\36\3\36\5\36\u01bb\n\36\3\36\3\36\5\36\u01bf\n\36\3\36\3\36\3\36\6"+
		"\36\u01c4\n\36\r\36\16\36\u01c5\3\36\5\36\u01c9\n\36\3\36\5\36\u01cc\n"+
		"\36\3\37\3\37\5\37\u01d0\n\37\3\37\3\37\3\37\6\37\u01d5\n\37\r\37\16\37"+
		"\u01d6\3\37\5\37\u01da\n\37\3 \3 \3 \3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\5"+
		"%\u01e9\n%\3%\5%\u01ec\n%\3&\3&\3\'\3\'\5\'\u01f2\n\'\3(\3(\3(\3(\3)\3"+
		")\3*\3*\6*\u01fc\n*\r*\16*\u01fd\3+\3+\3+\3+\3,\7,\u0205\n,\f,\16,\u0208"+
		"\13,\3,\3,\3-\7-\u020d\n-\f-\16-\u0210\13-\3-\3-\3.\3.\3.\3/\3/\3/\2\2"+
		"\60\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@B"+
		"DFHJLNPRTVXZ\\\2\7\3\289\3\28:\3\2?@\4\2\16\27\31\35\4\2\3\3??\u0258\2"+
		"a\3\2\2\2\4|\3\2\2\2\6\u0081\3\2\2\2\b\u0085\3\2\2\2\n\u008a\3\2\2\2\f"+
		"\u0095\3\2\2\2\16\u009a\3\2\2\2\20\u00b2\3\2\2\2\22\u00b7\3\2\2\2\24\u00b9"+
		"\3\2\2\2\26\u00d2\3\2\2\2\30\u00d4\3\2\2\2\32\u0106\3\2\2\2\34\u0108\3"+
		"\2\2\2\36\u010f\3\2\2\2 \u0112\3\2\2\2\"\u0127\3\2\2\2$\u0138\3\2\2\2"+
		"&\u0144\3\2\2\2(\u014d\3\2\2\2*\u0154\3\2\2\2,\u0158\3\2\2\2.\u0167\3"+
		"\2\2\2\60\u016b\3\2\2\2\62\u0174\3\2\2\2\64\u0182\3\2\2\2\66\u0190\3\2"+
		"\2\28\u01a4\3\2\2\2:\u01b8\3\2\2\2<\u01cd\3\2\2\2>\u01db\3\2\2\2@\u01de"+
		"\3\2\2\2B\u01e0\3\2\2\2D\u01e2\3\2\2\2F\u01e4\3\2\2\2H\u01eb\3\2\2\2J"+
		"\u01ed\3\2\2\2L\u01f1\3\2\2\2N\u01f3\3\2\2\2P\u01f7\3\2\2\2R\u01f9\3\2"+
		"\2\2T\u01ff\3\2\2\2V\u0206\3\2\2\2X\u020e\3\2\2\2Z\u0213\3\2\2\2\\\u0216"+
		"\3\2\2\2^`\7C\2\2_^\3\2\2\2`c\3\2\2\2a_\3\2\2\2ab\3\2\2\2be\3\2\2\2ca"+
		"\3\2\2\2df\5\4\3\2ed\3\2\2\2ef\3\2\2\2fj\3\2\2\2gi\7C\2\2hg\3\2\2\2il"+
		"\3\2\2\2jh\3\2\2\2jk\3\2\2\2kv\3\2\2\2lj\3\2\2\2mq\5\16\b\2np\7C\2\2o"+
		"n\3\2\2\2ps\3\2\2\2qo\3\2\2\2qr\3\2\2\2ru\3\2\2\2sq\3\2\2\2tm\3\2\2\2"+
		"ux\3\2\2\2vt\3\2\2\2vw\3\2\2\2wy\3\2\2\2xv\3\2\2\2yz\7\2\2\3z\3\3\2\2"+
		"\2{}\5\6\4\2|{\3\2\2\2|}\3\2\2\2}\177\3\2\2\2~\u0080\5\b\5\2\177~\3\2"+
		"\2\2\177\u0080\3\2\2\2\u0080\5\3\2\2\2\u0081\u0082\7\6\2\2\u0082\u0083"+
		"\5V,\2\u0083\7\3\2\2\2\u0084\u0086\5\n\6\2\u0085\u0084\3\2\2\2\u0086\u0087"+
		"\3\2\2\2\u0087\u0085\3\2\2\2\u0087\u0088\3\2\2\2\u0088\t\3\2\2\2\u0089"+
		"\u008b\7C\2\2\u008a\u0089\3\2\2\2\u008b\u008c\3\2\2\2\u008c\u008a\3\2"+
		"\2\2\u008c\u008d\3\2\2\2\u008d\u008e\3\2\2\2\u008e\u008f\7\5\2\2\u008f"+
		"\u0092\5V,\2\u0090\u0091\7\b\2\2\u0091\u0093\7?\2\2\u0092\u0090\3\2\2"+
		"\2\u0092\u0093\3\2\2\2\u0093\13\3\2\2\2\u0094\u0096\7E\2\2\u0095\u0094"+
		"\3\2\2\2\u0096\u0097\3\2\2\2\u0097\u0095\3\2\2\2\u0097\u0098\3\2\2\2\u0098"+
		"\r\3\2\2\2\u0099\u009b\5\f\7\2\u009a\u0099\3\2\2\2\u009a\u009b\3\2\2\2"+
		"\u009b\u009c\3\2\2\2\u009c\u009e\5\20\t\2\u009d\u009f\5R*\2\u009e\u009d"+
		"\3\2\2\2\u009e\u009f\3\2\2\2\u009f\u00a1\3\2\2\2\u00a0\u00a2\5 \21\2\u00a1"+
		"\u00a0\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2\17\3\2\2\2\u00a3\u00a4\7\4\2"+
		"\2\u00a4\u00b3\7?\2\2\u00a5\u00a7\5\\/\2\u00a6\u00a8\5\24\13\2\u00a7\u00a6"+
		"\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9\u00ac\7?\2\2\u00aa"+
		"\u00ab\7\r\2\2\u00ab\u00ad\5X-\2\u00ac\u00aa\3\2\2\2\u00ac\u00ad\3\2\2"+
		"\2\u00ad\u00b3\3\2\2\2\u00ae\u00b0\5\\/\2\u00af\u00b1\5\24\13\2\u00b0"+
		"\u00af\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1\u00b3\3\2\2\2\u00b2\u00a3\3\2"+
		"\2\2\u00b2\u00a5\3\2\2\2\u00b2\u00ae\3\2\2\2\u00b3\u00b5\3\2\2\2\u00b4"+
		"\u00b6\5\22\n\2\u00b5\u00b4\3\2\2\2\u00b5\u00b6\3\2\2\2\u00b6\21\3\2\2"+
		"\2\u00b7\u00b8\7=\2\2\u00b8\23\3\2\2\2\u00b9\u00bb\7\36\2\2\u00ba\u00bc"+
		"\5\26\f\2\u00bb\u00ba\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc\u00bd\3\2\2\2"+
		"\u00bd\u00be\7\37\2\2\u00be\25\3\2\2\2\u00bf\u00c0\5\30\r\2\u00c0\u00c7"+
		"\5\32\16\2\u00c1\u00c2\7\'\2\2\u00c2\u00c3\5\30\r\2\u00c3\u00c4\5\32\16"+
		"\2\u00c4\u00c6\3\2\2\2\u00c5\u00c1\3\2\2\2\u00c6\u00c9\3\2\2\2\u00c7\u00c5"+
		"\3\2\2\2\u00c7\u00c8\3\2\2\2\u00c8\u00d3\3\2\2\2\u00c9\u00c7\3\2\2\2\u00ca"+
		"\u00cf\5\32\16\2\u00cb\u00cc\7\'\2\2\u00cc\u00ce\5\32\16\2\u00cd\u00cb"+
		"\3\2\2\2\u00ce\u00d1\3\2\2\2\u00cf\u00cd\3\2\2\2\u00cf\u00d0\3\2\2\2\u00d0"+
		"\u00d3\3\2\2\2\u00d1\u00cf\3\2\2\2\u00d2\u00bf\3\2\2\2\u00d2\u00ca\3\2"+
		"\2\2\u00d3\27\3\2\2\2\u00d4\u00d5\7?\2\2\u00d5\u00d6\7)\2\2\u00d6\31\3"+
		"\2\2\2\u00d7\u00d9\5X-\2\u00d8\u00d7\3\2\2\2\u00d9\u00da\3\2\2\2\u00da"+
		"\u00d8\3\2\2\2\u00da\u00db\3\2\2\2\u00db\u0107\3\2\2\2\u00dc\u00de\5H"+
		"%\2\u00dd\u00dc\3\2\2\2\u00de\u00df\3\2\2\2\u00df\u00dd\3\2\2\2\u00df"+
		"\u00e0\3\2\2\2\u00e0\u0107\3\2\2\2\u00e1\u00e3\5F$\2\u00e2\u00e1\3\2\2"+
		"\2\u00e3\u00e4\3\2\2\2\u00e4\u00e2\3\2\2\2\u00e4\u00e5\3\2\2\2\u00e5\u0107"+
		"\3\2\2\2\u00e6\u00e8\5@!\2\u00e7\u00e6\3\2\2\2\u00e8\u00e9\3\2\2\2\u00e9"+
		"\u00e7\3\2\2\2\u00e9\u00ea\3\2\2\2\u00ea\u00ec\3\2\2\2\u00eb\u00ed\5P"+
		")\2\u00ec\u00eb\3\2\2\2\u00ec\u00ed\3\2\2\2\u00ed\u0107\3\2\2\2\u00ee"+
		"\u00f0\5B\"\2\u00ef\u00ee\3\2\2\2\u00f0\u00f1\3\2\2\2\u00f1\u00ef\3\2"+
		"\2\2\u00f1\u00f2\3\2\2\2\u00f2\u00f4\3\2\2\2\u00f3\u00f5\5P)\2\u00f4\u00f3"+
		"\3\2\2\2\u00f4\u00f5\3\2\2\2\u00f5\u0107\3\2\2\2\u00f6\u00f8\5D#\2\u00f7"+
		"\u00f6\3\2\2\2\u00f8\u00f9\3\2\2\2\u00f9\u00f7\3\2\2\2\u00f9\u00fa\3\2"+
		"\2\2\u00fa\u00fc\3\2\2\2\u00fb\u00fd\5P)\2\u00fc\u00fb\3\2\2\2\u00fc\u00fd"+
		"\3\2\2\2\u00fd\u0107\3\2\2\2\u00fe\u0100\5J&\2\u00ff\u00fe\3\2\2\2\u0100"+
		"\u0101\3\2\2\2\u0101\u00ff\3\2\2\2\u0101\u0102\3\2\2\2\u0102\u0107\3\2"+
		"\2\2\u0103\u0107\5\34\17\2\u0104\u0107\5L\'\2\u0105\u0107\7\66\2\2\u0106"+
		"\u00d8\3\2\2\2\u0106\u00dd\3\2\2\2\u0106\u00e2\3\2\2\2\u0106\u00e7\3\2"+
		"\2\2\u0106\u00ef\3\2\2\2\u0106\u00f7\3\2\2\2\u0106\u00ff\3\2\2\2\u0106"+
		"\u0103\3\2\2\2\u0106\u0104\3\2\2\2\u0106\u0105\3\2\2\2\u0107\33\3\2\2"+
		"\2\u0108\u010c\5\\/\2\u0109\u010b\5\36\20\2\u010a\u0109\3\2\2\2\u010b"+
		"\u010e\3\2\2\2\u010c\u010a\3\2\2\2\u010c\u010d\3\2\2\2\u010d\35\3\2\2"+
		"\2\u010e\u010c\3\2\2\2\u010f\u0110\7(\2\2\u0110\u0111\7?\2\2\u0111\37"+
		"\3\2\2\2\u0112\u0120\7H\2\2\u0113\u011a\5\"\22\2\u0114\u011a\5\16\b\2"+
		"\u0115\u011a\5T+\2\u0116\u011a\5$\23\2\u0117\u011a\5&\24\2\u0118\u011a"+
		"\5(\25\2\u0119\u0113\3\2\2\2\u0119\u0114\3\2\2\2\u0119\u0115\3\2\2\2\u0119"+
		"\u0116\3\2\2\2\u0119\u0117\3\2\2\2\u0119\u0118\3\2\2\2\u011a\u011c\3\2"+
		"\2\2\u011b\u011d\7C\2\2\u011c\u011b\3\2\2\2\u011d\u011e\3\2\2\2\u011e"+
		"\u011c\3\2\2\2\u011e\u011f\3\2\2\2\u011f\u0121\3\2\2\2\u0120\u0119\3\2"+
		"\2\2\u0121\u0122\3\2\2\2\u0122\u0120\3\2\2\2\u0122\u0123\3\2\2\2\u0123"+
		"\u0124\3\2\2\2\u0124\u0125\7I\2\2\u0125!\3\2\2\2\u0126\u0128\5\f\7\2\u0127"+
		"\u0126\3\2\2\2\u0127\u0128\3\2\2\2\u0128\u0129\3\2\2\2\u0129\u0133\7\7"+
		"\2\2\u012a\u0134\5\66\34\2\u012b\u0134\58\35\2\u012c\u0134\5:\36\2\u012d"+
		"\u0134\5\62\32\2\u012e\u0134\5\64\33\2\u012f\u0134\5<\37\2\u0130\u0134"+
		"\5*\26\2\u0131\u0134\5\60\31\2\u0132\u0134\5,\27\2\u0133\u012a\3\2\2\2"+
		"\u0133\u012b\3\2\2\2\u0133\u012c\3\2\2\2\u0133\u012d\3\2\2\2\u0133\u012e"+
		"\3\2\2\2\u0133\u012f\3\2\2\2\u0133\u0130\3\2\2\2\u0133\u0131\3\2\2\2\u0133"+
		"\u0132\3\2\2\2\u0134\u0136\3\2\2\2\u0135\u0137\5R*\2\u0136\u0135\3\2\2"+
		"\2\u0136\u0137\3\2\2\2\u0137#\3\2\2\2\u0138\u0139\7\13\2\2\u0139\u013b"+
		"\5\\/\2\u013a\u013c\5\24\13\2\u013b\u013a\3\2\2\2\u013b\u013c\3\2\2\2"+
		"\u013c\u013f\3\2\2\2\u013d\u013e\7\f\2\2\u013e\u0140\5\\/\2\u013f\u013d"+
		"\3\2\2\2\u013f\u0140\3\2\2\2\u0140\u0142\3\2\2\2\u0141\u0143\5 \21\2\u0142"+
		"\u0141\3\2\2\2\u0142\u0143\3\2\2\2\u0143%\3\2\2\2\u0144\u0145\7\n\2\2"+
		"\u0145\u0147\5X-\2\u0146\u0148\7\30\2\2\u0147\u0146\3\2\2\2\u0147\u0148"+
		"\3\2\2\2\u0148\u014a\3\2\2\2\u0149\u014b\5 \21\2\u014a\u0149\3\2\2\2\u014a"+
		"\u014b\3\2\2\2\u014b\'\3\2\2\2\u014c\u014e\5\f\7\2\u014d\u014c\3\2\2\2"+
		"\u014d\u014e\3\2\2\2\u014e\u014f\3\2\2\2\u014f\u0150\7\t\2\2\u0150\u0152"+
		"\5X-\2\u0151\u0153\5R*\2\u0152\u0151\3\2\2\2\u0152\u0153\3\2\2\2\u0153"+
		")\3\2\2\2\u0154\u0155\7/\2\2\u0155\u0156\5> \2\u0156\u0157\7?\2\2\u0157"+
		"+\3\2\2\2\u0158\u015a\7.\2\2\u0159\u015b\7\"\2\2\u015a\u0159\3\2\2\2\u015a"+
		"\u015b\3\2\2\2\u015b\u015c\3\2\2\2\u015c\u015d\7?\2\2\u015d\u0161\7H\2"+
		"\2\u015e\u015f\5.\30\2\u015f\u0160\7C\2\2\u0160\u0162\3\2\2\2\u0161\u015e"+
		"\3\2\2\2\u0162\u0163\3\2\2\2\u0163\u0161\3\2\2\2\u0163\u0164\3\2\2\2\u0164"+
		"\u0165\3\2\2\2\u0165\u0166\7I\2\2\u0166-\3\2\2\2\u0167\u0169\7?\2\2\u0168"+
		"\u016a\7,\2\2\u0169\u0168\3\2\2\2\u0169\u016a\3\2\2\2\u016a/\3\2\2\2\u016b"+
		"\u016d\5X-\2\u016c\u016e\7\"\2\2\u016d\u016c\3\2\2\2\u016d\u016e\3\2\2"+
		"\2\u016e\u016f\3\2\2\2\u016f\u0172\7?\2\2\u0170\u0171\7)\2\2\u0171\u0173"+
		"\7\66\2\2\u0172\u0170\3\2\2\2\u0172\u0173\3\2\2\2\u0173\61\3\2\2\2\u0174"+
		"\u0176\7\64\2\2\u0175\u0177\7\"\2\2\u0176\u0175\3\2\2\2\u0176\u0177\3"+
		"\2\2\2\u0177\u0178\3\2\2\2\u0178\u0180\7?\2\2\u0179\u017b\7)\2\2\u017a"+
		"\u017c\5F$\2\u017b\u017a\3\2\2\2\u017c\u017d\3\2\2\2\u017d\u017b\3\2\2"+
		"\2\u017d\u017e\3\2\2\2\u017e\u0181\3\2\2\2\u017f\u0181\7\66\2\2\u0180"+
		"\u0179\3\2\2\2\u0180\u017f\3\2\2\2\u0180\u0181\3\2\2\2\u0181\63\3\2\2"+
		"\2\u0182\u0184\7\63\2\2\u0183\u0185\7\"\2\2\u0184\u0183\3\2\2\2\u0184"+
		"\u0185\3\2\2\2\u0185\u0186\3\2\2\2\u0186\u018e\7?\2\2\u0187\u0189\7)\2"+
		"\2\u0188\u018a\5H%\2\u0189\u0188\3\2\2\2\u018a\u018b\3\2\2\2\u018b\u0189"+
		"\3\2\2\2\u018b\u018c\3\2\2\2\u018c\u018f\3\2\2\2\u018d\u018f\7\66\2\2"+
		"\u018e\u0187\3\2\2\2\u018e\u018d\3\2\2\2\u018e\u018f\3\2\2\2\u018f\65"+
		"\3\2\2\2\u0190\u0192\7\61\2\2\u0191\u0193\5> \2\u0192\u0191\3\2\2\2\u0192"+
		"\u0193\3\2\2\2\u0193\u0195\3\2\2\2\u0194\u0196\7\"\2\2\u0195\u0194\3\2"+
		"\2\2\u0195\u0196\3\2\2\2\u0196\u0197\3\2\2\2\u0197\u01a2\7?\2\2\u0198"+
		"\u019a\7)\2\2\u0199\u019b\5@!\2\u019a\u0199\3\2\2\2\u019b\u019c\3\2\2"+
		"\2\u019c\u019a\3\2\2\2\u019c\u019d\3\2\2\2\u019d\u019f\3\2\2\2\u019e\u01a0"+
		"\5P)\2\u019f\u019e\3\2\2\2\u019f\u01a0\3\2\2\2\u01a0\u01a3\3\2\2\2\u01a1"+
		"\u01a3\7\66\2\2\u01a2\u0198\3\2\2\2\u01a2\u01a1\3\2\2\2\u01a2\u01a3\3"+
		"\2\2\2\u01a3\67\3\2\2\2\u01a4\u01a6\7\60\2\2\u01a5\u01a7\5> \2\u01a6\u01a5"+
		"\3\2\2\2\u01a6\u01a7\3\2\2\2\u01a7\u01a9\3\2\2\2\u01a8\u01aa\7\"\2\2\u01a9"+
		"\u01a8\3\2\2\2\u01a9\u01aa\3\2\2\2\u01aa\u01ab\3\2\2\2\u01ab\u01b6\7?"+
		"\2\2\u01ac\u01ae\7)\2\2\u01ad\u01af\5B\"\2\u01ae\u01ad\3\2\2\2\u01af\u01b0"+
		"\3\2\2\2\u01b0\u01ae\3\2\2\2\u01b0\u01b1\3\2\2\2\u01b1\u01b3\3\2\2\2\u01b2"+
		"\u01b4\5P)\2\u01b3\u01b2\3\2\2\2\u01b3\u01b4\3\2\2\2\u01b4\u01b7\3\2\2"+
		"\2\u01b5\u01b7\7\66\2\2\u01b6\u01ac\3\2\2\2\u01b6\u01b5\3\2\2\2\u01b6"+
		"\u01b7\3\2\2\2\u01b79\3\2\2\2\u01b8\u01ba\7\62\2\2\u01b9\u01bb\5> \2\u01ba"+
		"\u01b9\3\2\2\2\u01ba\u01bb\3\2\2\2\u01bb\u01be\3\2\2\2\u01bc\u01bf\7\""+
		"\2\2\u01bd\u01bf\5N(\2\u01be\u01bc\3\2\2\2\u01be\u01bd\3\2\2\2\u01be\u01bf"+
		"\3\2\2\2\u01bf\u01c0\3\2\2\2\u01c0\u01cb\7?\2\2\u01c1\u01c3\7)\2\2\u01c2"+
		"\u01c4\5D#\2\u01c3\u01c2\3\2\2\2\u01c4\u01c5\3\2\2\2\u01c5\u01c3\3\2\2"+
		"\2\u01c5\u01c6\3\2\2\2\u01c6\u01c8\3\2\2\2\u01c7\u01c9\5P)\2\u01c8\u01c7"+
		"\3\2\2\2\u01c8\u01c9\3\2\2\2\u01c9\u01cc\3\2\2\2\u01ca\u01cc\7\66\2\2"+
		"\u01cb\u01c1\3\2\2\2\u01cb\u01ca\3\2\2\2\u01cb\u01cc\3\2\2\2\u01cc;\3"+
		"\2\2\2\u01cd\u01cf\7\65\2\2\u01ce\u01d0\7\"\2\2\u01cf\u01ce\3\2\2\2\u01cf"+
		"\u01d0\3\2\2\2\u01d0\u01d1\3\2\2\2\u01d1\u01d9\7?\2\2\u01d2\u01d4\7)\2"+
		"\2\u01d3\u01d5\5J&\2\u01d4\u01d3\3\2\2\2\u01d5\u01d6\3\2\2\2\u01d6\u01d4"+
		"\3\2\2\2\u01d6\u01d7\3\2\2\2\u01d7\u01da\3\2\2\2\u01d8\u01da\7\66\2\2"+
		"\u01d9\u01d2\3\2\2\2\u01d9\u01d8\3\2\2\2\u01d9\u01da\3\2\2\2\u01da=\3"+
		"\2\2\2\u01db\u01dc\7&\2\2\u01dc\u01dd\5P)\2\u01dd?\3\2\2\2\u01de\u01df"+
		"\78\2\2\u01dfA\3\2\2\2\u01e0\u01e1\t\2\2\2\u01e1C\3\2\2\2\u01e2\u01e3"+
		"\t\3\2\2\u01e3E\3\2\2\2\u01e4\u01e5\7\67\2\2\u01e5G\3\2\2\2\u01e6\u01ec"+
		"\7;\2\2\u01e7\u01e9\7C\2\2\u01e8\u01e7\3\2\2\2\u01e8\u01e9\3\2\2\2\u01e9"+
		"\u01ea\3\2\2\2\u01ea\u01ec\7<\2\2\u01eb\u01e6\3\2\2\2\u01eb\u01e8\3\2"+
		"\2\2\u01ecI\3\2\2\2\u01ed\u01ee\7>\2\2\u01eeK\3\2\2\2\u01ef\u01f2\5\22"+
		"\n\2\u01f0\u01f2\5X-\2\u01f1\u01ef\3\2\2\2\u01f1\u01f0\3\2\2\2\u01f2M"+
		"\3\2\2\2\u01f3\u01f4\7 \2\2\u01f4\u01f5\5@!\2\u01f5\u01f6\7!\2\2\u01f6"+
		"O\3\2\2\2\u01f7\u01f8\t\4\2\2\u01f8Q\3\2\2\2\u01f9\u01fb\7\13\2\2\u01fa"+
		"\u01fc\t\5\2\2\u01fb\u01fa\3\2\2\2\u01fc\u01fd\3\2\2\2\u01fd\u01fb\3\2"+
		"\2\2\u01fd\u01fe\3\2\2\2\u01feS\3\2\2\2\u01ff\u0200\7?\2\2\u0200\u0201"+
		"\7)\2\2\u0201\u0202\5\32\16\2\u0202U\3\2\2\2\u0203\u0205\5Z.\2\u0204\u0203"+
		"\3\2\2\2\u0205\u0208\3\2\2\2\u0206\u0204\3\2\2\2\u0206\u0207\3\2\2\2\u0207"+
		"\u0209\3\2\2\2\u0208\u0206\3\2\2\2\u0209\u020a\7?\2\2\u020aW\3\2\2\2\u020b"+
		"\u020d\5Z.\2\u020c\u020b\3\2\2\2\u020d\u0210\3\2\2\2\u020e\u020c\3\2\2"+
		"\2\u020e\u020f\3\2\2\2\u020f\u0211\3\2\2\2\u0210\u020e\3\2\2\2\u0211\u0212"+
		"\7?\2\2\u0212Y\3\2\2\2\u0213\u0214\7?\2\2\u0214\u0215\7(\2\2\u0215[\3"+
		"\2\2\2\u0216\u0217\t\6\2\2\u0217]\3\2\2\2Uaejqv|\177\u0087\u008c\u0092"+
		"\u0097\u009a\u009e\u00a1\u00a7\u00ac\u00b0\u00b2\u00b5\u00bb\u00c7\u00cf"+
		"\u00d2\u00da\u00df\u00e4\u00e9\u00ec\u00f1\u00f4\u00f9\u00fc\u0101\u0106"+
		"\u010c\u0119\u011e\u0122\u0127\u0133\u0136\u013b\u013f\u0142\u0147\u014a"+
		"\u014d\u0152\u015a\u0163\u0169\u016d\u0172\u0176\u017d\u0180\u0184\u018b"+
		"\u018e\u0192\u0195\u019c\u019f\u01a2\u01a6\u01a9\u01b0\u01b3\u01b6\u01ba"+
		"\u01be\u01c5\u01c8\u01cb\u01cf\u01d6\u01d9\u01e8\u01eb\u01f1\u01fd\u0206"+
		"\u020e";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}