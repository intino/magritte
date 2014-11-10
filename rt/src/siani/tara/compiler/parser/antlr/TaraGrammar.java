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
		DOLLAR=33, FACET=16, RIGHT_SQUARE=28, STAR=43, NEGATIVE_VALUE=60, DOC=72, 
		LETTER=69, DATE_VALUE=65, SUB=2, DEDENT=76, EQUALS=40, BOOLEAN_VALUE=58, 
		METAIDENTIFIER=1, RESOURCE=49, ADDRESS_VALUE=64, UNKNOWN_TOKEN=77, WORD=48, 
		AS=6, HAS=7, BOX=4, RIGHT_PARENTHESIS=26, DASHES=46, ALWAYS=22, COMMA=38, 
		IS=9, IDENTIFIER=67, LEFT_PARENTHESIS=25, COORDINATE_VALUE=66, STRING_MULTILINE_VALUE_KEY=63, 
		VAR=5, NL=74, DIGIT=68, INTENTION=17, DOT=39, COMPONENT=15, STRING_VALUE=62, 
		DOUBLE_VALUE=61, WITH=10, POSITIVE=44, NEW_LINE_INDENT=75, AGGREGATED=24, 
		ADDRESSED=23, DASH=45, COORDINATE_TYPE=50, PRIVATE=12, NATURAL_TYPE=52, 
		TERMINAL=18, UNDERDASH=47, ON=8, AMPERSAND=32, CLOSE_INLINE=31, BOOLEAN_TYPE=55, 
		DOUBLE_TYPE=53, SEMICOLON=42, INT_TYPE=51, REQUIRED=14, LIST=29, PERCENTAGE=35, 
		EMPTY=57, COLON=37, NATURAL_VALUE=59, EURO=34, GRADE=36, NEWLINE=70, SINGLE=13, 
		PROPERTY=20, SPACES=71, SP=73, APHOSTROPHE=41, STRING_TYPE=54, NAMED=19, 
		DATE_TYPE=56, INLINE=30, USE=3, UNIVERSAL=21, EXTENDS=11, LEFT_SQUARE=27;
	public static final String[] tokenNames = {
		"<INVALID>", "'Concept'", "'sub'", "'use'", "'box'", "'var'", "'as'", 
		"'has'", "'on'", "'is'", "'with'", "'extends'", "'private'", "'single'", 
		"'required'", "'component'", "'facet'", "'intention'", "'terminal'", "'named'", 
		"'property'", "'universal'", "'always'", "'addressed'", "'aggregated'", 
		"'('", "')'", "'['", "']'", "'...'", "'>'", "'<'", "'&'", "'$'", "'€'", 
		"'%'", "'º'", "':'", "','", "'.'", "'='", "'''", "SEMICOLON", "'*'", "'+'", 
		"'-'", "DASHES", "'_'", "'word'", "'resource'", "'coordinate'", "'integer'", 
		"'natural'", "'double'", "'string'", "'boolean'", "'date'", "'empty'", 
		"BOOLEAN_VALUE", "NATURAL_VALUE", "NEGATIVE_VALUE", "DOUBLE_VALUE", "STRING_VALUE", 
		"STRING_MULTILINE_VALUE_KEY", "ADDRESS_VALUE", "DATE_VALUE", "COORDINATE_VALUE", 
		"IDENTIFIER", "DIGIT", "LETTER", "NEWLINE", "SPACES", "DOC", "SP", "NL", 
		"'indent'", "'dedent'", "UNKNOWN_TOKEN"
	};
	public static final int
		RULE_root = 0, RULE_header = 1, RULE_box = 2, RULE_imports = 3, RULE_anImport = 4, 
		RULE_doc = 5, RULE_concept = 6, RULE_signature = 7, RULE_address = 8, 
		RULE_parameters = 9, RULE_parameterList = 10, RULE_explicit = 11, RULE_initValue = 12, 
		RULE_metaWord = 13, RULE_metaWordNames = 14, RULE_body = 15, RULE_attribute = 16, 
		RULE_facetApply = 17, RULE_facetTarget = 18, RULE_conceptReference = 19, 
		RULE_resource = 20, RULE_word = 21, RULE_wordNames = 22, RULE_reference = 23, 
		RULE_booleanAttribute = 24, RULE_stringAttribute = 25, RULE_naturalAttribute = 26, 
		RULE_integerAttribute = 27, RULE_doubleAttribute = 28, RULE_dateAttribute = 29, 
		RULE_coordinateAttribute = 30, RULE_attributeType = 31, RULE_naturalValue = 32, 
		RULE_integerValue = 33, RULE_doubleValue = 34, RULE_booleanValue = 35, 
		RULE_stringValue = 36, RULE_dateValue = 37, RULE_coordinateValue = 38, 
		RULE_linkValue = 39, RULE_measure = 40, RULE_annotations = 41, RULE_varInit = 42, 
		RULE_headerReference = 43, RULE_identifierReference = 44, RULE_hierarchy = 45, 
		RULE_metaidentifier = 46;
	public static final String[] ruleNames = {
		"root", "header", "box", "imports", "anImport", "doc", "concept", "signature", 
		"address", "parameters", "parameterList", "explicit", "initValue", "metaWord", 
		"metaWordNames", "body", "attribute", "facetApply", "facetTarget", "conceptReference", 
		"resource", "word", "wordNames", "reference", "booleanAttribute", "stringAttribute", 
		"naturalAttribute", "integerAttribute", "doubleAttribute", "dateAttribute", 
		"coordinateAttribute", "attributeType", "naturalValue", "integerValue", 
		"doubleValue", "booleanValue", "stringValue", "dateValue", "coordinateValue", 
		"linkValue", "measure", "annotations", "varInit", "headerReference", "identifierReference", 
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
			setState(97);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(94); match(NEWLINE);
					}
					} 
				}
				setState(99);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			}
			setState(101);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				{
				setState(100); header();
				}
				break;
			}
			setState(104); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(103); match(NEWLINE);
				}
				}
				setState(106); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NEWLINE );
			setState(117);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==METAIDENTIFIER || _la==SUB || _la==IDENTIFIER || _la==DOC) {
				{
				{
				setState(108); concept();
				setState(112);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NEWLINE) {
					{
					{
					setState(109); match(NEWLINE);
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
			setState(120); match(EOF);
			}
		}
		catch (RecognitionException re) {
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
			setState(123);
			_la = _input.LA(1);
			if (_la==BOX) {
				{
				setState(122); box();
				}
			}

			setState(126);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(125); imports();
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
			setState(128); match(BOX);
			setState(129); headerReference();
			}
		}
		catch (RecognitionException re) {
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
			setState(132); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(131); anImport();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(134); 
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
			setState(137); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(136); match(NEWLINE);
				}
				}
				setState(139); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NEWLINE );
			setState(141); match(USE);
			setState(142); headerReference();
			setState(145);
			_la = _input.LA(1);
			if (_la==AS) {
				{
				setState(143); match(AS);
				setState(144); match(IDENTIFIER);
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
			setState(148); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(147); match(DOC);
				}
				}
				setState(150); 
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
			setState(153);
			_la = _input.LA(1);
			if (_la==DOC) {
				{
				setState(152); doc();
				}
			}

			setState(155); signature();
			setState(157);
			_la = _input.LA(1);
			if (_la==IS) {
				{
				setState(156); annotations();
				}
			}

			setState(160);
			_la = _input.LA(1);
			if (_la==NEW_LINE_INDENT) {
				{
				setState(159); body();
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
			setState(177);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				{
				{
				setState(162); match(SUB);
				setState(163); match(IDENTIFIER);
				}
				}
				break;
			case 2:
				{
				{
				setState(164); metaidentifier();
				setState(166);
				_la = _input.LA(1);
				if (_la==LEFT_PARENTHESIS) {
					{
					setState(165); parameters();
					}
				}

				setState(168); match(IDENTIFIER);
				setState(171);
				_la = _input.LA(1);
				if (_la==EXTENDS) {
					{
					setState(169); match(EXTENDS);
					setState(170); identifierReference();
					}
				}

				}
				}
				break;
			case 3:
				{
				setState(173); metaidentifier();
				setState(175);
				_la = _input.LA(1);
				if (_la==LEFT_PARENTHESIS) {
					{
					setState(174); parameters();
					}
				}

				}
				break;
			}
			setState(180);
			_la = _input.LA(1);
			if (_la==ADDRESS_VALUE) {
				{
				setState(179); address();
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
			setState(182); match(ADDRESS_VALUE);
			}
		}
		catch (RecognitionException re) {
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
			setState(184); match(LEFT_PARENTHESIS);
			setState(186);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << METAIDENTIFIER) | (1L << EMPTY) | (1L << BOOLEAN_VALUE) | (1L << NATURAL_VALUE) | (1L << NEGATIVE_VALUE) | (1L << DOUBLE_VALUE) | (1L << STRING_VALUE) | (1L << STRING_MULTILINE_VALUE_KEY))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (ADDRESS_VALUE - 64)) | (1L << (DATE_VALUE - 64)) | (1L << (COORDINATE_VALUE - 64)) | (1L << (IDENTIFIER - 64)))) != 0)) {
				{
				setState(185); parameterList();
				}
			}

			setState(188); match(RIGHT_PARENTHESIS);
			}
		}
		catch (RecognitionException re) {
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
			setState(209);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(190); explicit();
				setState(191); initValue();
				setState(198);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(192); match(COMMA);
					setState(193); explicit();
					setState(194); initValue();
					}
					}
					setState(200);
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
				setState(201); initValue();
				setState(206);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(202); match(COMMA);
					setState(203); initValue();
					}
					}
					setState(208);
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
			setState(211); match(IDENTIFIER);
			setState(212); match(EQUALS);
			}
		}
		catch (RecognitionException re) {
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
		public List<CoordinateValueContext> coordinateValue() {
			return getRuleContexts(CoordinateValueContext.class);
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
		public CoordinateValueContext coordinateValue(int i) {
			return getRuleContext(CoordinateValueContext.class,i);
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
			setState(266);
			switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(215); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(214); identifierReference();
					}
					}
					setState(217); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==IDENTIFIER );
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(220); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(219); stringValue();
					}
					}
					setState(222); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==STRING_VALUE || _la==STRING_MULTILINE_VALUE_KEY );
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(225); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(224); booleanValue();
					}
					}
					setState(227); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==BOOLEAN_VALUE );
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(230); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(229); naturalValue();
					}
					}
					setState(232); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NATURAL_VALUE );
				setState(235);
				_la = _input.LA(1);
				if (((((_la - 33)) & ~0x3f) == 0 && ((1L << (_la - 33)) & ((1L << (DOLLAR - 33)) | (1L << (EURO - 33)) | (1L << (PERCENTAGE - 33)) | (1L << (GRADE - 33)) | (1L << (IDENTIFIER - 33)))) != 0)) {
					{
					setState(234); measure();
					}
				}

				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(238); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(237); integerValue();
					}
					}
					setState(240); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NATURAL_VALUE || _la==NEGATIVE_VALUE );
				setState(243);
				_la = _input.LA(1);
				if (((((_la - 33)) & ~0x3f) == 0 && ((1L << (_la - 33)) & ((1L << (DOLLAR - 33)) | (1L << (EURO - 33)) | (1L << (PERCENTAGE - 33)) | (1L << (GRADE - 33)) | (1L << (IDENTIFIER - 33)))) != 0)) {
					{
					setState(242); measure();
					}
				}

				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(246); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(245); doubleValue();
					}
					}
					setState(248); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NATURAL_VALUE) | (1L << NEGATIVE_VALUE) | (1L << DOUBLE_VALUE))) != 0) );
				setState(251);
				_la = _input.LA(1);
				if (((((_la - 33)) & ~0x3f) == 0 && ((1L << (_la - 33)) & ((1L << (DOLLAR - 33)) | (1L << (EURO - 33)) | (1L << (PERCENTAGE - 33)) | (1L << (GRADE - 33)) | (1L << (IDENTIFIER - 33)))) != 0)) {
					{
					setState(250); measure();
					}
				}

				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(254); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(253); coordinateValue();
					}
					}
					setState(256); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==COORDINATE_VALUE );
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(259); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(258); dateValue();
					}
					}
					setState(261); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==DATE_VALUE );
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(263); metaWord();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(264); linkValue();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(265); match(EMPTY);
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
			setState(268); metaidentifier();
			setState(272);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DOT) {
				{
				{
				setState(269); metaWordNames();
				}
				}
				setState(274);
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
			setState(275); match(DOT);
			setState(276); match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
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
		public List<AttributeContext> attribute() {
			return getRuleContexts(AttributeContext.class);
		}
		public List<VarInitContext> varInit() {
			return getRuleContexts(VarInitContext.class);
		}
		public VarInitContext varInit(int i) {
			return getRuleContext(VarInitContext.class,i);
		}
		public AttributeContext attribute(int i) {
			return getRuleContext(AttributeContext.class,i);
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
			setState(278); match(NEW_LINE_INDENT);
			setState(292); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(285);
				switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
				case 1:
					{
					setState(279); attribute();
					}
					break;
				case 2:
					{
					setState(280); concept();
					}
					break;
				case 3:
					{
					setState(281); varInit();
					}
					break;
				case 4:
					{
					setState(282); facetApply();
					}
					break;
				case 5:
					{
					setState(283); facetTarget();
					}
					break;
				case 6:
					{
					setState(284); conceptReference();
					}
					break;
				}
				setState(288); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(287); match(NEWLINE);
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
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << METAIDENTIFIER) | (1L << SUB) | (1L << VAR) | (1L << HAS) | (1L << ON) | (1L << IS))) != 0) || _la==IDENTIFIER || _la==DOC );
			setState(296); match(DEDENT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AttributeContext extends ParserRuleContext {
		public CoordinateAttributeContext coordinateAttribute() {
			return getRuleContext(CoordinateAttributeContext.class,0);
		}
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
		public AttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attribute; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterAttribute(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitAttribute(this);
		}
	}

	public final AttributeContext attribute() throws RecognitionException {
		AttributeContext _localctx = new AttributeContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_attribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(299);
			_la = _input.LA(1);
			if (_la==DOC) {
				{
				setState(298); doc();
				}
			}

			setState(301); match(VAR);
			setState(312);
			switch (_input.LA(1)) {
			case NATURAL_TYPE:
				{
				setState(302); naturalAttribute();
				}
				break;
			case INT_TYPE:
				{
				setState(303); integerAttribute();
				}
				break;
			case DOUBLE_TYPE:
				{
				setState(304); doubleAttribute();
				}
				break;
			case BOOLEAN_TYPE:
				{
				setState(305); booleanAttribute();
				}
				break;
			case STRING_TYPE:
				{
				setState(306); stringAttribute();
				}
				break;
			case DATE_TYPE:
				{
				setState(307); dateAttribute();
				}
				break;
			case COORDINATE_TYPE:
				{
				setState(308); coordinateAttribute();
				}
				break;
			case RESOURCE:
				{
				setState(309); resource();
				}
				break;
			case IDENTIFIER:
				{
				setState(310); reference();
				}
				break;
			case WORD:
				{
				setState(311); word();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(315);
			_la = _input.LA(1);
			if (_la==IS) {
				{
				setState(314); annotations();
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
			setState(317); match(IS);
			setState(318); metaidentifier();
			setState(320);
			_la = _input.LA(1);
			if (_la==LEFT_PARENTHESIS) {
				{
				setState(319); parameters();
				}
			}

			setState(324);
			_la = _input.LA(1);
			if (_la==WITH) {
				{
				setState(322); match(WITH);
				setState(323); metaidentifier();
				}
			}

			setState(327);
			_la = _input.LA(1);
			if (_la==NEW_LINE_INDENT) {
				{
				setState(326); body();
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
			setState(329); match(ON);
			setState(330); identifierReference();
			setState(332);
			_la = _input.LA(1);
			if (_la==ALWAYS) {
				{
				setState(331); match(ALWAYS);
				}
			}

			setState(335);
			_la = _input.LA(1);
			if (_la==NEW_LINE_INDENT) {
				{
				setState(334); body();
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
		public TerminalNode AGGREGATED() { return getToken(TaraGrammar.AGGREGATED, 0); }
		public IdentifierReferenceContext identifierReference() {
			return getRuleContext(IdentifierReferenceContext.class,0);
		}
		public TerminalNode HAS() { return getToken(TaraGrammar.HAS, 0); }
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
		public DocContext doc() {
			return getRuleContext(DocContext.class,0);
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
		enterRule(_localctx, 38, RULE_conceptReference);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(338);
			_la = _input.LA(1);
			if (_la==DOC) {
				{
				setState(337); doc();
				}
			}

			setState(340); match(HAS);
			setState(341); identifierReference();
			setState(343);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(342); match(IDENTIFIER);
				}
			}

			setState(347);
			_la = _input.LA(1);
			if (_la==IS) {
				{
				setState(345); match(IS);
				setState(346); match(AGGREGATED);
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
			setState(349); match(RESOURCE);
			setState(350); attributeType();
			setState(351); match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
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
			setState(353); match(WORD);
			setState(355);
			_la = _input.LA(1);
			if (_la==LIST) {
				{
				setState(354); match(LIST);
				}
			}

			setState(357); match(IDENTIFIER);
			setState(358); match(NEW_LINE_INDENT);
			setState(362); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(359); wordNames();
				setState(360); match(NEWLINE);
				}
				}
				setState(364); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==IDENTIFIER );
			setState(366); match(DEDENT);
			}
		}
		catch (RecognitionException re) {
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
			setState(368); match(IDENTIFIER);
			setState(370);
			_la = _input.LA(1);
			if (_la==STAR) {
				{
				setState(369); match(STAR);
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
			setState(372); identifierReference();
			setState(374);
			_la = _input.LA(1);
			if (_la==LIST) {
				{
				setState(373); match(LIST);
				}
			}

			setState(376); match(IDENTIFIER);
			setState(379);
			_la = _input.LA(1);
			if (_la==EQUALS) {
				{
				setState(377); match(EQUALS);
				setState(378); match(EMPTY);
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
			setState(381); match(BOOLEAN_TYPE);
			setState(383);
			_la = _input.LA(1);
			if (_la==LIST) {
				{
				setState(382); match(LIST);
				}
			}

			setState(385); match(IDENTIFIER);
			setState(393);
			switch (_input.LA(1)) {
			case EQUALS:
				{
				setState(386); match(EQUALS);
				setState(388); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(387); booleanValue();
					}
					}
					setState(390); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==BOOLEAN_VALUE );
				}
				break;
			case EMPTY:
				{
				setState(392); match(EMPTY);
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
			enterOuterAlt(_localctx, 1);
			{
			setState(395); match(STRING_TYPE);
			setState(397);
			_la = _input.LA(1);
			if (_la==LIST) {
				{
				setState(396); match(LIST);
				}
			}

			setState(399); match(IDENTIFIER);
			setState(407);
			switch (_input.LA(1)) {
			case EQUALS:
				{
				setState(400); match(EQUALS);
				setState(402); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(401); stringValue();
					}
					}
					setState(404); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==STRING_VALUE || _la==STRING_MULTILINE_VALUE_KEY );
				}
				break;
			case EMPTY:
				{
				setState(406); match(EMPTY);
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
			setState(409); match(NATURAL_TYPE);
			setState(411);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(410); attributeType();
				}
			}

			setState(414);
			_la = _input.LA(1);
			if (_la==LIST) {
				{
				setState(413); match(LIST);
				}
			}

			setState(416); match(IDENTIFIER);
			setState(427);
			switch (_input.LA(1)) {
			case EQUALS:
				{
				setState(417); match(EQUALS);
				setState(419); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(418); naturalValue();
					}
					}
					setState(421); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NATURAL_VALUE );
				setState(424);
				_la = _input.LA(1);
				if (((((_la - 33)) & ~0x3f) == 0 && ((1L << (_la - 33)) & ((1L << (DOLLAR - 33)) | (1L << (EURO - 33)) | (1L << (PERCENTAGE - 33)) | (1L << (GRADE - 33)) | (1L << (IDENTIFIER - 33)))) != 0)) {
					{
					setState(423); measure();
					}
				}

				}
				break;
			case EMPTY:
				{
				setState(426); match(EMPTY);
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
			setState(429); match(INT_TYPE);
			setState(431);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(430); attributeType();
				}
			}

			setState(434);
			_la = _input.LA(1);
			if (_la==LIST) {
				{
				setState(433); match(LIST);
				}
			}

			setState(436); match(IDENTIFIER);
			setState(447);
			switch (_input.LA(1)) {
			case EQUALS:
				{
				setState(437); match(EQUALS);
				setState(439); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(438); integerValue();
					}
					}
					setState(441); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NATURAL_VALUE || _la==NEGATIVE_VALUE );
				setState(444);
				_la = _input.LA(1);
				if (((((_la - 33)) & ~0x3f) == 0 && ((1L << (_la - 33)) & ((1L << (DOLLAR - 33)) | (1L << (EURO - 33)) | (1L << (PERCENTAGE - 33)) | (1L << (GRADE - 33)) | (1L << (IDENTIFIER - 33)))) != 0)) {
					{
					setState(443); measure();
					}
				}

				}
				break;
			case EMPTY:
				{
				setState(446); match(EMPTY);
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
			setState(449); match(DOUBLE_TYPE);
			setState(451);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(450); attributeType();
				}
			}

			setState(454);
			_la = _input.LA(1);
			if (_la==LIST) {
				{
				setState(453); match(LIST);
				}
			}

			setState(456); match(IDENTIFIER);
			setState(467);
			switch (_input.LA(1)) {
			case EQUALS:
				{
				setState(457); match(EQUALS);
				setState(459); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(458); doubleValue();
					}
					}
					setState(461); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NATURAL_VALUE) | (1L << NEGATIVE_VALUE) | (1L << DOUBLE_VALUE))) != 0) );
				setState(464);
				_la = _input.LA(1);
				if (((((_la - 33)) & ~0x3f) == 0 && ((1L << (_la - 33)) & ((1L << (DOLLAR - 33)) | (1L << (EURO - 33)) | (1L << (PERCENTAGE - 33)) | (1L << (GRADE - 33)) | (1L << (IDENTIFIER - 33)))) != 0)) {
					{
					setState(463); measure();
					}
				}

				}
				break;
			case EMPTY:
				{
				setState(466); match(EMPTY);
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
			setState(469); match(DATE_TYPE);
			setState(471);
			_la = _input.LA(1);
			if (_la==LIST) {
				{
				setState(470); match(LIST);
				}
			}

			setState(473); match(IDENTIFIER);
			setState(481);
			switch (_input.LA(1)) {
			case EQUALS:
				{
				setState(474); match(EQUALS);
				setState(476); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(475); dateValue();
					}
					}
					setState(478); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==DATE_VALUE );
				}
				break;
			case EMPTY:
				{
				setState(480); match(EMPTY);
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

	public static class CoordinateAttributeContext extends ParserRuleContext {
		public TerminalNode EMPTY() { return getToken(TaraGrammar.EMPTY, 0); }
		public TerminalNode COORDINATE_TYPE() { return getToken(TaraGrammar.COORDINATE_TYPE, 0); }
		public List<CoordinateValueContext> coordinateValue() {
			return getRuleContexts(CoordinateValueContext.class);
		}
		public TerminalNode EQUALS() { return getToken(TaraGrammar.EQUALS, 0); }
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
		public CoordinateValueContext coordinateValue(int i) {
			return getRuleContext(CoordinateValueContext.class,i);
		}
		public TerminalNode LIST() { return getToken(TaraGrammar.LIST, 0); }
		public CoordinateAttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_coordinateAttribute; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterCoordinateAttribute(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitCoordinateAttribute(this);
		}
	}

	public final CoordinateAttributeContext coordinateAttribute() throws RecognitionException {
		CoordinateAttributeContext _localctx = new CoordinateAttributeContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_coordinateAttribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(483); match(COORDINATE_TYPE);
			setState(485);
			_la = _input.LA(1);
			if (_la==LIST) {
				{
				setState(484); match(LIST);
				}
			}

			setState(487); match(IDENTIFIER);
			setState(497);
			_la = _input.LA(1);
			if (_la==EQUALS) {
				{
				setState(488); match(EQUALS);
				setState(495);
				switch (_input.LA(1)) {
				case COORDINATE_VALUE:
					{
					setState(490); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(489); coordinateValue();
						}
						}
						setState(492); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==COORDINATE_VALUE );
					}
					break;
				case EMPTY:
					{
					setState(494); match(EMPTY);
					}
					break;
				default:
					throw new NoViableAltException(this);
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
		enterRule(_localctx, 62, RULE_attributeType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(499); match(COLON);
			setState(500); measure();
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 64, RULE_naturalValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(502); match(NATURAL_VALUE);
			}
		}
		catch (RecognitionException re) {
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
			setState(504);
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
		enterRule(_localctx, 68, RULE_doubleValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(506);
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
		enterRule(_localctx, 70, RULE_booleanValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(508); match(BOOLEAN_VALUE);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 72, RULE_stringValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(510);
			_la = _input.LA(1);
			if ( !(_la==STRING_VALUE || _la==STRING_MULTILINE_VALUE_KEY) ) {
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
		enterRule(_localctx, 74, RULE_dateValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(512); match(DATE_VALUE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CoordinateValueContext extends ParserRuleContext {
		public TerminalNode COORDINATE_VALUE() { return getToken(TaraGrammar.COORDINATE_VALUE, 0); }
		public CoordinateValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_coordinateValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterCoordinateValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitCoordinateValue(this);
		}
	}

	public final CoordinateValueContext coordinateValue() throws RecognitionException {
		CoordinateValueContext _localctx = new CoordinateValueContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_coordinateValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(514); match(COORDINATE_VALUE);
			}
		}
		catch (RecognitionException re) {
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
			setState(518);
			switch (_input.LA(1)) {
			case ADDRESS_VALUE:
				enterOuterAlt(_localctx, 1);
				{
				setState(516); address();
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(517); identifierReference();
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

	public static class MeasureContext extends ParserRuleContext {
		public TerminalNode GRADE() { return getToken(TaraGrammar.GRADE, 0); }
		public TerminalNode EURO() { return getToken(TaraGrammar.EURO, 0); }
		public TerminalNode PERCENTAGE() { return getToken(TaraGrammar.PERCENTAGE, 0); }
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
		public TerminalNode DOLLAR() { return getToken(TaraGrammar.DOLLAR, 0); }
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
		enterRule(_localctx, 80, RULE_measure);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(520);
			_la = _input.LA(1);
			if ( !(((((_la - 33)) & ~0x3f) == 0 && ((1L << (_la - 33)) & ((1L << (DOLLAR - 33)) | (1L << (EURO - 33)) | (1L << (PERCENTAGE - 33)) | (1L << (GRADE - 33)) | (1L << (IDENTIFIER - 33)))) != 0)) ) {
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
		public List<TerminalNode> FACET() { return getTokens(TaraGrammar.FACET); }
		public List<TerminalNode> AGGREGATED() { return getTokens(TaraGrammar.AGGREGATED); }
		public List<TerminalNode> UNIVERSAL() { return getTokens(TaraGrammar.UNIVERSAL); }
		public List<TerminalNode> NAMED() { return getTokens(TaraGrammar.NAMED); }
		public TerminalNode PROPERTY(int i) {
			return getToken(TaraGrammar.PROPERTY, i);
		}
		public TerminalNode FACET(int i) {
			return getToken(TaraGrammar.FACET, i);
		}
		public List<TerminalNode> PRIVATE() { return getTokens(TaraGrammar.PRIVATE); }
		public TerminalNode AGGREGATED(int i) {
			return getToken(TaraGrammar.AGGREGATED, i);
		}
		public TerminalNode IS() { return getToken(TaraGrammar.IS, 0); }
		public List<TerminalNode> SINGLE() { return getTokens(TaraGrammar.SINGLE); }
		public TerminalNode TERMINAL(int i) {
			return getToken(TaraGrammar.TERMINAL, i);
		}
		public TerminalNode UNIVERSAL(int i) {
			return getToken(TaraGrammar.UNIVERSAL, i);
		}
		public List<TerminalNode> ADDRESSED() { return getTokens(TaraGrammar.ADDRESSED); }
		public List<TerminalNode> TERMINAL() { return getTokens(TaraGrammar.TERMINAL); }
		public List<TerminalNode> REQUIRED() { return getTokens(TaraGrammar.REQUIRED); }
		public TerminalNode ADDRESSED(int i) {
			return getToken(TaraGrammar.ADDRESSED, i);
		}
		public TerminalNode NAMED(int i) {
			return getToken(TaraGrammar.NAMED, i);
		}
		public List<TerminalNode> PROPERTY() { return getTokens(TaraGrammar.PROPERTY); }
		public TerminalNode INTENTION(int i) {
			return getToken(TaraGrammar.INTENTION, i);
		}
		public TerminalNode REQUIRED(int i) {
			return getToken(TaraGrammar.REQUIRED, i);
		}
		public List<TerminalNode> INTENTION() { return getTokens(TaraGrammar.INTENTION); }
		public TerminalNode SINGLE(int i) {
			return getToken(TaraGrammar.SINGLE, i);
		}
		public TerminalNode COMPONENT(int i) {
			return getToken(TaraGrammar.COMPONENT, i);
		}
		public List<TerminalNode> COMPONENT() { return getTokens(TaraGrammar.COMPONENT); }
		public TerminalNode PRIVATE(int i) {
			return getToken(TaraGrammar.PRIVATE, i);
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
		enterRule(_localctx, 82, RULE_annotations);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(522); match(IS);
			setState(524); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(523);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PRIVATE) | (1L << SINGLE) | (1L << REQUIRED) | (1L << COMPONENT) | (1L << FACET) | (1L << INTENTION) | (1L << TERMINAL) | (1L << NAMED) | (1L << PROPERTY) | (1L << UNIVERSAL) | (1L << ADDRESSED) | (1L << AGGREGATED))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				}
				}
				setState(526); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PRIVATE) | (1L << SINGLE) | (1L << REQUIRED) | (1L << COMPONENT) | (1L << FACET) | (1L << INTENTION) | (1L << TERMINAL) | (1L << NAMED) | (1L << PROPERTY) | (1L << UNIVERSAL) | (1L << ADDRESSED) | (1L << AGGREGATED))) != 0) );
			}
		}
		catch (RecognitionException re) {
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
		public TerminalNode COLON() { return getToken(TaraGrammar.COLON, 0); }
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
		enterRule(_localctx, 84, RULE_varInit);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(528); match(IDENTIFIER);
			setState(529); match(COLON);
			setState(530); initValue();
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 86, RULE_headerReference);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(535);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,85,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(532); hierarchy();
					}
					} 
				}
				setState(537);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,85,_ctx);
			}
			setState(538); match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 88, RULE_identifierReference);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(543);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,86,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(540); hierarchy();
					}
					} 
				}
				setState(545);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,86,_ctx);
			}
			setState(546); match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 90, RULE_hierarchy);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(548); match(IDENTIFIER);
			setState(549); match(DOT);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 92, RULE_metaidentifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(551);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3O\u022c\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\3\2\7\2b\n\2\f\2\16\2e\13\2\3\2\5\2h"+
		"\n\2\3\2\6\2k\n\2\r\2\16\2l\3\2\3\2\7\2q\n\2\f\2\16\2t\13\2\7\2v\n\2\f"+
		"\2\16\2y\13\2\3\2\3\2\3\3\5\3~\n\3\3\3\5\3\u0081\n\3\3\4\3\4\3\4\3\5\6"+
		"\5\u0087\n\5\r\5\16\5\u0088\3\6\6\6\u008c\n\6\r\6\16\6\u008d\3\6\3\6\3"+
		"\6\3\6\5\6\u0094\n\6\3\7\6\7\u0097\n\7\r\7\16\7\u0098\3\b\5\b\u009c\n"+
		"\b\3\b\3\b\5\b\u00a0\n\b\3\b\5\b\u00a3\n\b\3\t\3\t\3\t\3\t\5\t\u00a9\n"+
		"\t\3\t\3\t\3\t\5\t\u00ae\n\t\3\t\3\t\5\t\u00b2\n\t\5\t\u00b4\n\t\3\t\5"+
		"\t\u00b7\n\t\3\n\3\n\3\13\3\13\5\13\u00bd\n\13\3\13\3\13\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\7\f\u00c7\n\f\f\f\16\f\u00ca\13\f\3\f\3\f\3\f\7\f\u00cf\n\f"+
		"\f\f\16\f\u00d2\13\f\5\f\u00d4\n\f\3\r\3\r\3\r\3\16\6\16\u00da\n\16\r"+
		"\16\16\16\u00db\3\16\6\16\u00df\n\16\r\16\16\16\u00e0\3\16\6\16\u00e4"+
		"\n\16\r\16\16\16\u00e5\3\16\6\16\u00e9\n\16\r\16\16\16\u00ea\3\16\5\16"+
		"\u00ee\n\16\3\16\6\16\u00f1\n\16\r\16\16\16\u00f2\3\16\5\16\u00f6\n\16"+
		"\3\16\6\16\u00f9\n\16\r\16\16\16\u00fa\3\16\5\16\u00fe\n\16\3\16\6\16"+
		"\u0101\n\16\r\16\16\16\u0102\3\16\6\16\u0106\n\16\r\16\16\16\u0107\3\16"+
		"\3\16\3\16\5\16\u010d\n\16\3\17\3\17\7\17\u0111\n\17\f\17\16\17\u0114"+
		"\13\17\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u0120\n"+
		"\21\3\21\6\21\u0123\n\21\r\21\16\21\u0124\6\21\u0127\n\21\r\21\16\21\u0128"+
		"\3\21\3\21\3\22\5\22\u012e\n\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\5\22\u013b\n\22\3\22\5\22\u013e\n\22\3\23\3\23\3\23\5"+
		"\23\u0143\n\23\3\23\3\23\5\23\u0147\n\23\3\23\5\23\u014a\n\23\3\24\3\24"+
		"\3\24\5\24\u014f\n\24\3\24\5\24\u0152\n\24\3\25\5\25\u0155\n\25\3\25\3"+
		"\25\3\25\5\25\u015a\n\25\3\25\3\25\5\25\u015e\n\25\3\26\3\26\3\26\3\26"+
		"\3\27\3\27\5\27\u0166\n\27\3\27\3\27\3\27\3\27\3\27\6\27\u016d\n\27\r"+
		"\27\16\27\u016e\3\27\3\27\3\30\3\30\5\30\u0175\n\30\3\31\3\31\5\31\u0179"+
		"\n\31\3\31\3\31\3\31\5\31\u017e\n\31\3\32\3\32\5\32\u0182\n\32\3\32\3"+
		"\32\3\32\6\32\u0187\n\32\r\32\16\32\u0188\3\32\5\32\u018c\n\32\3\33\3"+
		"\33\5\33\u0190\n\33\3\33\3\33\3\33\6\33\u0195\n\33\r\33\16\33\u0196\3"+
		"\33\5\33\u019a\n\33\3\34\3\34\5\34\u019e\n\34\3\34\5\34\u01a1\n\34\3\34"+
		"\3\34\3\34\6\34\u01a6\n\34\r\34\16\34\u01a7\3\34\5\34\u01ab\n\34\3\34"+
		"\5\34\u01ae\n\34\3\35\3\35\5\35\u01b2\n\35\3\35\5\35\u01b5\n\35\3\35\3"+
		"\35\3\35\6\35\u01ba\n\35\r\35\16\35\u01bb\3\35\5\35\u01bf\n\35\3\35\5"+
		"\35\u01c2\n\35\3\36\3\36\5\36\u01c6\n\36\3\36\5\36\u01c9\n\36\3\36\3\36"+
		"\3\36\6\36\u01ce\n\36\r\36\16\36\u01cf\3\36\5\36\u01d3\n\36\3\36\5\36"+
		"\u01d6\n\36\3\37\3\37\5\37\u01da\n\37\3\37\3\37\3\37\6\37\u01df\n\37\r"+
		"\37\16\37\u01e0\3\37\5\37\u01e4\n\37\3 \3 \5 \u01e8\n \3 \3 \3 \6 \u01ed"+
		"\n \r \16 \u01ee\3 \5 \u01f2\n \5 \u01f4\n \3!\3!\3!\3\"\3\"\3#\3#\3$"+
		"\3$\3%\3%\3&\3&\3\'\3\'\3(\3(\3)\3)\5)\u0209\n)\3*\3*\3+\3+\6+\u020f\n"+
		"+\r+\16+\u0210\3,\3,\3,\3,\3-\7-\u0218\n-\f-\16-\u021b\13-\3-\3-\3.\7"+
		".\u0220\n.\f.\16.\u0223\13.\3.\3.\3/\3/\3/\3\60\3\60\3\60\2\2\61\2\4\6"+
		"\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJLNPRT"+
		"VXZ\\^\2\b\3\2=>\3\2=?\3\2@A\4\2#&EE\4\2\16\27\31\32\4\2\3\3EE\u026f\2"+
		"c\3\2\2\2\4}\3\2\2\2\6\u0082\3\2\2\2\b\u0086\3\2\2\2\n\u008b\3\2\2\2\f"+
		"\u0096\3\2\2\2\16\u009b\3\2\2\2\20\u00b3\3\2\2\2\22\u00b8\3\2\2\2\24\u00ba"+
		"\3\2\2\2\26\u00d3\3\2\2\2\30\u00d5\3\2\2\2\32\u010c\3\2\2\2\34\u010e\3"+
		"\2\2\2\36\u0115\3\2\2\2 \u0118\3\2\2\2\"\u012d\3\2\2\2$\u013f\3\2\2\2"+
		"&\u014b\3\2\2\2(\u0154\3\2\2\2*\u015f\3\2\2\2,\u0163\3\2\2\2.\u0172\3"+
		"\2\2\2\60\u0176\3\2\2\2\62\u017f\3\2\2\2\64\u018d\3\2\2\2\66\u019b\3\2"+
		"\2\28\u01af\3\2\2\2:\u01c3\3\2\2\2<\u01d7\3\2\2\2>\u01e5\3\2\2\2@\u01f5"+
		"\3\2\2\2B\u01f8\3\2\2\2D\u01fa\3\2\2\2F\u01fc\3\2\2\2H\u01fe\3\2\2\2J"+
		"\u0200\3\2\2\2L\u0202\3\2\2\2N\u0204\3\2\2\2P\u0208\3\2\2\2R\u020a\3\2"+
		"\2\2T\u020c\3\2\2\2V\u0212\3\2\2\2X\u0219\3\2\2\2Z\u0221\3\2\2\2\\\u0226"+
		"\3\2\2\2^\u0229\3\2\2\2`b\7H\2\2a`\3\2\2\2be\3\2\2\2ca\3\2\2\2cd\3\2\2"+
		"\2dg\3\2\2\2ec\3\2\2\2fh\5\4\3\2gf\3\2\2\2gh\3\2\2\2hj\3\2\2\2ik\7H\2"+
		"\2ji\3\2\2\2kl\3\2\2\2lj\3\2\2\2lm\3\2\2\2mw\3\2\2\2nr\5\16\b\2oq\7H\2"+
		"\2po\3\2\2\2qt\3\2\2\2rp\3\2\2\2rs\3\2\2\2sv\3\2\2\2tr\3\2\2\2un\3\2\2"+
		"\2vy\3\2\2\2wu\3\2\2\2wx\3\2\2\2xz\3\2\2\2yw\3\2\2\2z{\7\2\2\3{\3\3\2"+
		"\2\2|~\5\6\4\2}|\3\2\2\2}~\3\2\2\2~\u0080\3\2\2\2\177\u0081\5\b\5\2\u0080"+
		"\177\3\2\2\2\u0080\u0081\3\2\2\2\u0081\5\3\2\2\2\u0082\u0083\7\6\2\2\u0083"+
		"\u0084\5X-\2\u0084\7\3\2\2\2\u0085\u0087\5\n\6\2\u0086\u0085\3\2\2\2\u0087"+
		"\u0088\3\2\2\2\u0088\u0086\3\2\2\2\u0088\u0089\3\2\2\2\u0089\t\3\2\2\2"+
		"\u008a\u008c\7H\2\2\u008b\u008a\3\2\2\2\u008c\u008d\3\2\2\2\u008d\u008b"+
		"\3\2\2\2\u008d\u008e\3\2\2\2\u008e\u008f\3\2\2\2\u008f\u0090\7\5\2\2\u0090"+
		"\u0093\5X-\2\u0091\u0092\7\b\2\2\u0092\u0094\7E\2\2\u0093\u0091\3\2\2"+
		"\2\u0093\u0094\3\2\2\2\u0094\13\3\2\2\2\u0095\u0097\7J\2\2\u0096\u0095"+
		"\3\2\2\2\u0097\u0098\3\2\2\2\u0098\u0096\3\2\2\2\u0098\u0099\3\2\2\2\u0099"+
		"\r\3\2\2\2\u009a\u009c\5\f\7\2\u009b\u009a\3\2\2\2\u009b\u009c\3\2\2\2"+
		"\u009c\u009d\3\2\2\2\u009d\u009f\5\20\t\2\u009e\u00a0\5T+\2\u009f\u009e"+
		"\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0\u00a2\3\2\2\2\u00a1\u00a3\5 \21\2\u00a2"+
		"\u00a1\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3\17\3\2\2\2\u00a4\u00a5\7\4\2"+
		"\2\u00a5\u00b4\7E\2\2\u00a6\u00a8\5^\60\2\u00a7\u00a9\5\24\13\2\u00a8"+
		"\u00a7\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa\u00ad\7E"+
		"\2\2\u00ab\u00ac\7\r\2\2\u00ac\u00ae\5Z.\2\u00ad\u00ab\3\2\2\2\u00ad\u00ae"+
		"\3\2\2\2\u00ae\u00b4\3\2\2\2\u00af\u00b1\5^\60\2\u00b0\u00b2\5\24\13\2"+
		"\u00b1\u00b0\3\2\2\2\u00b1\u00b2\3\2\2\2\u00b2\u00b4\3\2\2\2\u00b3\u00a4"+
		"\3\2\2\2\u00b3\u00a6\3\2\2\2\u00b3\u00af\3\2\2\2\u00b4\u00b6\3\2\2\2\u00b5"+
		"\u00b7\5\22\n\2\u00b6\u00b5\3\2\2\2\u00b6\u00b7\3\2\2\2\u00b7\21\3\2\2"+
		"\2\u00b8\u00b9\7B\2\2\u00b9\23\3\2\2\2\u00ba\u00bc\7\33\2\2\u00bb\u00bd"+
		"\5\26\f\2\u00bc\u00bb\3\2\2\2\u00bc\u00bd\3\2\2\2\u00bd\u00be\3\2\2\2"+
		"\u00be\u00bf\7\34\2\2\u00bf\25\3\2\2\2\u00c0\u00c1\5\30\r\2\u00c1\u00c8"+
		"\5\32\16\2\u00c2\u00c3\7(\2\2\u00c3\u00c4\5\30\r\2\u00c4\u00c5\5\32\16"+
		"\2\u00c5\u00c7\3\2\2\2\u00c6\u00c2\3\2\2\2\u00c7\u00ca\3\2\2\2\u00c8\u00c6"+
		"\3\2\2\2\u00c8\u00c9\3\2\2\2\u00c9\u00d4\3\2\2\2\u00ca\u00c8\3\2\2\2\u00cb"+
		"\u00d0\5\32\16\2\u00cc\u00cd\7(\2\2\u00cd\u00cf\5\32\16\2\u00ce\u00cc"+
		"\3\2\2\2\u00cf\u00d2\3\2\2\2\u00d0\u00ce\3\2\2\2\u00d0\u00d1\3\2\2\2\u00d1"+
		"\u00d4\3\2\2\2\u00d2\u00d0\3\2\2\2\u00d3\u00c0\3\2\2\2\u00d3\u00cb\3\2"+
		"\2\2\u00d4\27\3\2\2\2\u00d5\u00d6\7E\2\2\u00d6\u00d7\7*\2\2\u00d7\31\3"+
		"\2\2\2\u00d8\u00da\5Z.\2\u00d9\u00d8\3\2\2\2\u00da\u00db\3\2\2\2\u00db"+
		"\u00d9\3\2\2\2\u00db\u00dc\3\2\2\2\u00dc\u010d\3\2\2\2\u00dd\u00df\5J"+
		"&\2\u00de\u00dd\3\2\2\2\u00df\u00e0\3\2\2\2\u00e0\u00de\3\2\2\2\u00e0"+
		"\u00e1\3\2\2\2\u00e1\u010d\3\2\2\2\u00e2\u00e4\5H%\2\u00e3\u00e2\3\2\2"+
		"\2\u00e4\u00e5\3\2\2\2\u00e5\u00e3\3\2\2\2\u00e5\u00e6\3\2\2\2\u00e6\u010d"+
		"\3\2\2\2\u00e7\u00e9\5B\"\2\u00e8\u00e7\3\2\2\2\u00e9\u00ea\3\2\2\2\u00ea"+
		"\u00e8\3\2\2\2\u00ea\u00eb\3\2\2\2\u00eb\u00ed\3\2\2\2\u00ec\u00ee\5R"+
		"*\2\u00ed\u00ec\3\2\2\2\u00ed\u00ee\3\2\2\2\u00ee\u010d\3\2\2\2\u00ef"+
		"\u00f1\5D#\2\u00f0\u00ef\3\2\2\2\u00f1\u00f2\3\2\2\2\u00f2\u00f0\3\2\2"+
		"\2\u00f2\u00f3\3\2\2\2\u00f3\u00f5\3\2\2\2\u00f4\u00f6\5R*\2\u00f5\u00f4"+
		"\3\2\2\2\u00f5\u00f6\3\2\2\2\u00f6\u010d\3\2\2\2\u00f7\u00f9\5F$\2\u00f8"+
		"\u00f7\3\2\2\2\u00f9\u00fa\3\2\2\2\u00fa\u00f8\3\2\2\2\u00fa\u00fb\3\2"+
		"\2\2\u00fb\u00fd\3\2\2\2\u00fc\u00fe\5R*\2\u00fd\u00fc\3\2\2\2\u00fd\u00fe"+
		"\3\2\2\2\u00fe\u010d\3\2\2\2\u00ff\u0101\5N(\2\u0100\u00ff\3\2\2\2\u0101"+
		"\u0102\3\2\2\2\u0102\u0100\3\2\2\2\u0102\u0103\3\2\2\2\u0103\u010d\3\2"+
		"\2\2\u0104\u0106\5L\'\2\u0105\u0104\3\2\2\2\u0106\u0107\3\2\2\2\u0107"+
		"\u0105\3\2\2\2\u0107\u0108\3\2\2\2\u0108\u010d\3\2\2\2\u0109\u010d\5\34"+
		"\17\2\u010a\u010d\5P)\2\u010b\u010d\7;\2\2\u010c\u00d9\3\2\2\2\u010c\u00de"+
		"\3\2\2\2\u010c\u00e3\3\2\2\2\u010c\u00e8\3\2\2\2\u010c\u00f0\3\2\2\2\u010c"+
		"\u00f8\3\2\2\2\u010c\u0100\3\2\2\2\u010c\u0105\3\2\2\2\u010c\u0109\3\2"+
		"\2\2\u010c\u010a\3\2\2\2\u010c\u010b\3\2\2\2\u010d\33\3\2\2\2\u010e\u0112"+
		"\5^\60\2\u010f\u0111\5\36\20\2\u0110\u010f\3\2\2\2\u0111\u0114\3\2\2\2"+
		"\u0112\u0110\3\2\2\2\u0112\u0113\3\2\2\2\u0113\35\3\2\2\2\u0114\u0112"+
		"\3\2\2\2\u0115\u0116\7)\2\2\u0116\u0117\7E\2\2\u0117\37\3\2\2\2\u0118"+
		"\u0126\7M\2\2\u0119\u0120\5\"\22\2\u011a\u0120\5\16\b\2\u011b\u0120\5"+
		"V,\2\u011c\u0120\5$\23\2\u011d\u0120\5&\24\2\u011e\u0120\5(\25\2\u011f"+
		"\u0119\3\2\2\2\u011f\u011a\3\2\2\2\u011f\u011b\3\2\2\2\u011f\u011c\3\2"+
		"\2\2\u011f\u011d\3\2\2\2\u011f\u011e\3\2\2\2\u0120\u0122\3\2\2\2\u0121"+
		"\u0123\7H\2\2\u0122\u0121\3\2\2\2\u0123\u0124\3\2\2\2\u0124\u0122\3\2"+
		"\2\2\u0124\u0125\3\2\2\2\u0125\u0127\3\2\2\2\u0126\u011f\3\2\2\2\u0127"+
		"\u0128\3\2\2\2\u0128\u0126\3\2\2\2\u0128\u0129\3\2\2\2\u0129\u012a\3\2"+
		"\2\2\u012a\u012b\7N\2\2\u012b!\3\2\2\2\u012c\u012e\5\f\7\2\u012d\u012c"+
		"\3\2\2\2\u012d\u012e\3\2\2\2\u012e\u012f\3\2\2\2\u012f\u013a\7\7\2\2\u0130"+
		"\u013b\5\66\34\2\u0131\u013b\58\35\2\u0132\u013b\5:\36\2\u0133\u013b\5"+
		"\62\32\2\u0134\u013b\5\64\33\2\u0135\u013b\5<\37\2\u0136\u013b\5> \2\u0137"+
		"\u013b\5*\26\2\u0138\u013b\5\60\31\2\u0139\u013b\5,\27\2\u013a\u0130\3"+
		"\2\2\2\u013a\u0131\3\2\2\2\u013a\u0132\3\2\2\2\u013a\u0133\3\2\2\2\u013a"+
		"\u0134\3\2\2\2\u013a\u0135\3\2\2\2\u013a\u0136\3\2\2\2\u013a\u0137\3\2"+
		"\2\2\u013a\u0138\3\2\2\2\u013a\u0139\3\2\2\2\u013b\u013d\3\2\2\2\u013c"+
		"\u013e\5T+\2\u013d\u013c\3\2\2\2\u013d\u013e\3\2\2\2\u013e#\3\2\2\2\u013f"+
		"\u0140\7\13\2\2\u0140\u0142\5^\60\2\u0141\u0143\5\24\13\2\u0142\u0141"+
		"\3\2\2\2\u0142\u0143\3\2\2\2\u0143\u0146\3\2\2\2\u0144\u0145\7\f\2\2\u0145"+
		"\u0147\5^\60\2\u0146\u0144\3\2\2\2\u0146\u0147\3\2\2\2\u0147\u0149\3\2"+
		"\2\2\u0148\u014a\5 \21\2\u0149\u0148\3\2\2\2\u0149\u014a\3\2\2\2\u014a"+
		"%\3\2\2\2\u014b\u014c\7\n\2\2\u014c\u014e\5Z.\2\u014d\u014f\7\30\2\2\u014e"+
		"\u014d\3\2\2\2\u014e\u014f\3\2\2\2\u014f\u0151\3\2\2\2\u0150\u0152\5 "+
		"\21\2\u0151\u0150\3\2\2\2\u0151\u0152\3\2\2\2\u0152\'\3\2\2\2\u0153\u0155"+
		"\5\f\7\2\u0154\u0153\3\2\2\2\u0154\u0155\3\2\2\2\u0155\u0156\3\2\2\2\u0156"+
		"\u0157\7\t\2\2\u0157\u0159\5Z.\2\u0158\u015a\7E\2\2\u0159\u0158\3\2\2"+
		"\2\u0159\u015a\3\2\2\2\u015a\u015d\3\2\2\2\u015b\u015c\7\13\2\2\u015c"+
		"\u015e\7\32\2\2\u015d\u015b\3\2\2\2\u015d\u015e\3\2\2\2\u015e)\3\2\2\2"+
		"\u015f\u0160\7\63\2\2\u0160\u0161\5@!\2\u0161\u0162\7E\2\2\u0162+\3\2"+
		"\2\2\u0163\u0165\7\62\2\2\u0164\u0166\7\37\2\2\u0165\u0164\3\2\2\2\u0165"+
		"\u0166\3\2\2\2\u0166\u0167\3\2\2\2\u0167\u0168\7E\2\2\u0168\u016c\7M\2"+
		"\2\u0169\u016a\5.\30\2\u016a\u016b\7H\2\2\u016b\u016d\3\2\2\2\u016c\u0169"+
		"\3\2\2\2\u016d\u016e\3\2\2\2\u016e\u016c\3\2\2\2\u016e\u016f\3\2\2\2\u016f"+
		"\u0170\3\2\2\2\u0170\u0171\7N\2\2\u0171-\3\2\2\2\u0172\u0174\7E\2\2\u0173"+
		"\u0175\7-\2\2\u0174\u0173\3\2\2\2\u0174\u0175\3\2\2\2\u0175/\3\2\2\2\u0176"+
		"\u0178\5Z.\2\u0177\u0179\7\37\2\2\u0178\u0177\3\2\2\2\u0178\u0179\3\2"+
		"\2\2\u0179\u017a\3\2\2\2\u017a\u017d\7E\2\2\u017b\u017c\7*\2\2\u017c\u017e"+
		"\7;\2\2\u017d\u017b\3\2\2\2\u017d\u017e\3\2\2\2\u017e\61\3\2\2\2\u017f"+
		"\u0181\79\2\2\u0180\u0182\7\37\2\2\u0181\u0180\3\2\2\2\u0181\u0182\3\2"+
		"\2\2\u0182\u0183\3\2\2\2\u0183\u018b\7E\2\2\u0184\u0186\7*\2\2\u0185\u0187"+
		"\5H%\2\u0186\u0185\3\2\2\2\u0187\u0188\3\2\2\2\u0188\u0186\3\2\2\2\u0188"+
		"\u0189\3\2\2\2\u0189\u018c\3\2\2\2\u018a\u018c\7;\2\2\u018b\u0184\3\2"+
		"\2\2\u018b\u018a\3\2\2\2\u018b\u018c\3\2\2\2\u018c\63\3\2\2\2\u018d\u018f"+
		"\78\2\2\u018e\u0190\7\37\2\2\u018f\u018e\3\2\2\2\u018f\u0190\3\2\2\2\u0190"+
		"\u0191\3\2\2\2\u0191\u0199\7E\2\2\u0192\u0194\7*\2\2\u0193\u0195\5J&\2"+
		"\u0194\u0193\3\2\2\2\u0195\u0196\3\2\2\2\u0196\u0194\3\2\2\2\u0196\u0197"+
		"\3\2\2\2\u0197\u019a\3\2\2\2\u0198\u019a\7;\2\2\u0199\u0192\3\2\2\2\u0199"+
		"\u0198\3\2\2\2\u0199\u019a\3\2\2\2\u019a\65\3\2\2\2\u019b\u019d\7\66\2"+
		"\2\u019c\u019e\5@!\2\u019d\u019c\3\2\2\2\u019d\u019e\3\2\2\2\u019e\u01a0"+
		"\3\2\2\2\u019f\u01a1\7\37\2\2\u01a0\u019f\3\2\2\2\u01a0\u01a1\3\2\2\2"+
		"\u01a1\u01a2\3\2\2\2\u01a2\u01ad\7E\2\2\u01a3\u01a5\7*\2\2\u01a4\u01a6"+
		"\5B\"\2\u01a5\u01a4\3\2\2\2\u01a6\u01a7\3\2\2\2\u01a7\u01a5\3\2\2\2\u01a7"+
		"\u01a8\3\2\2\2\u01a8\u01aa\3\2\2\2\u01a9\u01ab\5R*\2\u01aa\u01a9\3\2\2"+
		"\2\u01aa\u01ab\3\2\2\2\u01ab\u01ae\3\2\2\2\u01ac\u01ae\7;\2\2\u01ad\u01a3"+
		"\3\2\2\2\u01ad\u01ac\3\2\2\2\u01ad\u01ae\3\2\2\2\u01ae\67\3\2\2\2\u01af"+
		"\u01b1\7\65\2\2\u01b0\u01b2\5@!\2\u01b1\u01b0\3\2\2\2\u01b1\u01b2\3\2"+
		"\2\2\u01b2\u01b4\3\2\2\2\u01b3\u01b5\7\37\2\2\u01b4\u01b3\3\2\2\2\u01b4"+
		"\u01b5\3\2\2\2\u01b5\u01b6\3\2\2\2\u01b6\u01c1\7E\2\2\u01b7\u01b9\7*\2"+
		"\2\u01b8\u01ba\5D#\2\u01b9\u01b8\3\2\2\2\u01ba\u01bb\3\2\2\2\u01bb\u01b9"+
		"\3\2\2\2\u01bb\u01bc\3\2\2\2\u01bc\u01be\3\2\2\2\u01bd\u01bf\5R*\2\u01be"+
		"\u01bd\3\2\2\2\u01be\u01bf\3\2\2\2\u01bf\u01c2\3\2\2\2\u01c0\u01c2\7;"+
		"\2\2\u01c1\u01b7\3\2\2\2\u01c1\u01c0\3\2\2\2\u01c1\u01c2\3\2\2\2\u01c2"+
		"9\3\2\2\2\u01c3\u01c5\7\67\2\2\u01c4\u01c6\5@!\2\u01c5\u01c4\3\2\2\2\u01c5"+
		"\u01c6\3\2\2\2\u01c6\u01c8\3\2\2\2\u01c7\u01c9\7\37\2\2\u01c8\u01c7\3"+
		"\2\2\2\u01c8\u01c9\3\2\2\2\u01c9\u01ca\3\2\2\2\u01ca\u01d5\7E\2\2\u01cb"+
		"\u01cd\7*\2\2\u01cc\u01ce\5F$\2\u01cd\u01cc\3\2\2\2\u01ce\u01cf\3\2\2"+
		"\2\u01cf\u01cd\3\2\2\2\u01cf\u01d0\3\2\2\2\u01d0\u01d2\3\2\2\2\u01d1\u01d3"+
		"\5R*\2\u01d2\u01d1\3\2\2\2\u01d2\u01d3\3\2\2\2\u01d3\u01d6\3\2\2\2\u01d4"+
		"\u01d6\7;\2\2\u01d5\u01cb\3\2\2\2\u01d5\u01d4\3\2\2\2\u01d5\u01d6\3\2"+
		"\2\2\u01d6;\3\2\2\2\u01d7\u01d9\7:\2\2\u01d8\u01da\7\37\2\2\u01d9\u01d8"+
		"\3\2\2\2\u01d9\u01da\3\2\2\2\u01da\u01db\3\2\2\2\u01db\u01e3\7E\2\2\u01dc"+
		"\u01de\7*\2\2\u01dd\u01df\5L\'\2\u01de\u01dd\3\2\2\2\u01df\u01e0\3\2\2"+
		"\2\u01e0\u01de\3\2\2\2\u01e0\u01e1\3\2\2\2\u01e1\u01e4\3\2\2\2\u01e2\u01e4"+
		"\7;\2\2\u01e3\u01dc\3\2\2\2\u01e3\u01e2\3\2\2\2\u01e3\u01e4\3\2\2\2\u01e4"+
		"=\3\2\2\2\u01e5\u01e7\7\64\2\2\u01e6\u01e8\7\37\2\2\u01e7\u01e6\3\2\2"+
		"\2\u01e7\u01e8\3\2\2\2\u01e8\u01e9\3\2\2\2\u01e9\u01f3\7E\2\2\u01ea\u01f1"+
		"\7*\2\2\u01eb\u01ed\5N(\2\u01ec\u01eb\3\2\2\2\u01ed\u01ee\3\2\2\2\u01ee"+
		"\u01ec\3\2\2\2\u01ee\u01ef\3\2\2\2\u01ef\u01f2\3\2\2\2\u01f0\u01f2\7;"+
		"\2\2\u01f1\u01ec\3\2\2\2\u01f1\u01f0\3\2\2\2\u01f2\u01f4\3\2\2\2\u01f3"+
		"\u01ea\3\2\2\2\u01f3\u01f4\3\2\2\2\u01f4?\3\2\2\2\u01f5\u01f6\7\'\2\2"+
		"\u01f6\u01f7\5R*\2\u01f7A\3\2\2\2\u01f8\u01f9\7=\2\2\u01f9C\3\2\2\2\u01fa"+
		"\u01fb\t\2\2\2\u01fbE\3\2\2\2\u01fc\u01fd\t\3\2\2\u01fdG\3\2\2\2\u01fe"+
		"\u01ff\7<\2\2\u01ffI\3\2\2\2\u0200\u0201\t\4\2\2\u0201K\3\2\2\2\u0202"+
		"\u0203\7C\2\2\u0203M\3\2\2\2\u0204\u0205\7D\2\2\u0205O\3\2\2\2\u0206\u0209"+
		"\5\22\n\2\u0207\u0209\5Z.\2\u0208\u0206\3\2\2\2\u0208\u0207\3\2\2\2\u0209"+
		"Q\3\2\2\2\u020a\u020b\t\5\2\2\u020bS\3\2\2\2\u020c\u020e\7\13\2\2\u020d"+
		"\u020f\t\6\2\2\u020e\u020d\3\2\2\2\u020f\u0210\3\2\2\2\u0210\u020e\3\2"+
		"\2\2\u0210\u0211\3\2\2\2\u0211U\3\2\2\2\u0212\u0213\7E\2\2\u0213\u0214"+
		"\7\'\2\2\u0214\u0215\5\32\16\2\u0215W\3\2\2\2\u0216\u0218\5\\/\2\u0217"+
		"\u0216\3\2\2\2\u0218\u021b\3\2\2\2\u0219\u0217\3\2\2\2\u0219\u021a\3\2"+
		"\2\2\u021a\u021c\3\2\2\2\u021b\u0219\3\2\2\2\u021c\u021d\7E\2\2\u021d"+
		"Y\3\2\2\2\u021e\u0220\5\\/\2\u021f\u021e\3\2\2\2\u0220\u0223\3\2\2\2\u0221"+
		"\u021f\3\2\2\2\u0221\u0222\3\2\2\2\u0222\u0224\3\2\2\2\u0223\u0221\3\2"+
		"\2\2\u0224\u0225\7E\2\2\u0225[\3\2\2\2\u0226\u0227\7E\2\2\u0227\u0228"+
		"\7)\2\2\u0228]\3\2\2\2\u0229\u022a\t\7\2\2\u022a_\3\2\2\2Ycglrw}\u0080"+
		"\u0088\u008d\u0093\u0098\u009b\u009f\u00a2\u00a8\u00ad\u00b1\u00b3\u00b6"+
		"\u00bc\u00c8\u00d0\u00d3\u00db\u00e0\u00e5\u00ea\u00ed\u00f2\u00f5\u00fa"+
		"\u00fd\u0102\u0107\u010c\u0112\u011f\u0124\u0128\u012d\u013a\u013d\u0142"+
		"\u0146\u0149\u014e\u0151\u0154\u0159\u015d\u0165\u016e\u0174\u0178\u017d"+
		"\u0181\u0188\u018b\u018f\u0196\u0199\u019d\u01a0\u01a7\u01aa\u01ad\u01b1"+
		"\u01b4\u01bb\u01be\u01c1\u01c5\u01c8\u01cf\u01d2\u01d5\u01d9\u01e0\u01e3"+
		"\u01e7\u01ee\u01f1\u01f3\u0208\u0210\u0219\u0221";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}