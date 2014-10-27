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
		DOLLAR=30, RIGHT_SQUARE=25, FACET=15, STAR=40, NEGATIVE_VALUE=59, DOC=71, 
		LETTER=68, DATE_VALUE=64, SUB=2, DEDENT=75, EQUALS=37, BOOLEAN_VALUE=57, 
		METAIDENTIFIER=1, RESOURCE=47, UNKNOWN_TOKEN=76, WORD=46, AS=5, HAS=6, 
		BOX=4, RIGHT_PARENTHESIS=23, DASHES=43, ALWAYS=21, COMMA=35, IS=8, IDENTIFIER=66, 
		LEFT_PARENTHESIS=22, COORDINATE_VALUE=65, STRING_MULTILINE_VALUE_KEY=62, 
		VAR=45, NL=73, DIGIT=67, INTENTION=16, DOT=36, PORT_TYPE=48, COMPONENT=14, 
		STRING_VALUE=61, DOUBLE_VALUE=60, WITH=9, PORT_VALUE=63, POSITIVE=41, 
		NEW_LINE_INDENT=74, DASH=42, PRIVATE=11, COORDINATE_TYPE=49, NATURAL_TYPE=51, 
		TERMINAL=17, UNDERDASH=44, ON=7, AMPERSAND=29, CLOSE_INLINE=28, BOOLEAN_TYPE=54, 
		DOUBLE_TYPE=52, SEMICOLON=39, INT_TYPE=50, REQUIRED=13, LIST=26, PERCENTAGE=32, 
		EMPTY=56, COLON=34, NATURAL_VALUE=58, EURO=31, GRADE=33, NEWLINE=69, SINGLE=12, 
		PROPERTY=19, SPACES=70, SP=72, APHOSTROPHE=38, STRING_TYPE=53, NAMED=18, 
		DATE_TYPE=55, INLINE=27, USE=3, UNIVERSAL=20, LEFT_SQUARE=24, EXTENDS=10;
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
		RULE_parameterList = 9, RULE_explicit = 10, RULE_initValue = 11, RULE_metaWord = 12, 
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
		"parameters", "parameterList", "explicit", "initValue", "metaWord", "metaWordNames", 
		"body", "conceptReference", "facetApply", "facetTarget", "attribute", 
		"resource", "word", "wordNames", "reference", "booleanAttribute", "stringAttribute", 
		"naturalAttribute", "integerAttribute", "doubleAttribute", "dateAttribute", 
		"coordinateAttribute", "portAttribute", "attributeType", "naturalValue", 
		"integerValue", "doubleValue", "booleanValue", "stringValue", "portValue", 
		"dateValue", "coordinateValue", "measure", "annotations", "varInit", "headerReference", 
		"identifierReference", "hierarchy", "metaidentifier"
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
		public MetaidentifierContext metaidentifier() {
			return getRuleContext(MetaidentifierContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
		public ParametersContext parameters() {
			return getRuleContext(ParametersContext.class,0);
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
			setState(177);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(162); match(SUB);
				setState(163); match(IDENTIFIER);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
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
				enterOuterAlt(_localctx, 3);
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
		}
		catch (RecognitionException re) {
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
			setState(179); match(LEFT_PARENTHESIS);
			setState(181);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << METAIDENTIFIER) | (1L << EMPTY) | (1L << BOOLEAN_VALUE) | (1L << NATURAL_VALUE) | (1L << NEGATIVE_VALUE) | (1L << DOUBLE_VALUE) | (1L << STRING_VALUE) | (1L << STRING_MULTILINE_VALUE_KEY) | (1L << PORT_VALUE))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (DATE_VALUE - 64)) | (1L << (COORDINATE_VALUE - 64)) | (1L << (IDENTIFIER - 64)))) != 0)) {
				{
				setState(180); parameterList();
				}
			}

			setState(183); match(RIGHT_PARENTHESIS);
			}
		}
		catch (RecognitionException re) {
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
			setState(204);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(185); explicit();
				setState(186); initValue();
				setState(193);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(187); match(COMMA);
					setState(188); explicit();
					setState(189); initValue();
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
				{
				setState(196); initValue();
				setState(201);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(197); match(COMMA);
					setState(198); initValue();
					}
					}
					setState(203);
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
			setState(206); match(IDENTIFIER);
			setState(207); match(EQUALS);
			}
		}
		catch (RecognitionException re) {
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
		public DateValueContext dateValue(int i) {
			return getRuleContext(DateValueContext.class,i);
		}
		public PortValueContext portValue(int i) {
			return getRuleContext(PortValueContext.class,i);
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
		public List<PortValueContext> portValue() {
			return getRuleContexts(PortValueContext.class);
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
		enterRule(_localctx, 22, RULE_initValue);
		int _la;
		try {
			setState(265);
			switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(210); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(209); identifierReference();
					}
					}
					setState(212); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==IDENTIFIER );
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(215); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(214); stringValue();
					}
					}
					setState(217); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==STRING_VALUE || _la==STRING_MULTILINE_VALUE_KEY );
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(220); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(219); booleanValue();
					}
					}
					setState(222); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==BOOLEAN_VALUE );
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(225); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(224); naturalValue();
					}
					}
					setState(227); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NATURAL_VALUE );
				setState(230);
				_la = _input.LA(1);
				if (((((_la - 30)) & ~0x3f) == 0 && ((1L << (_la - 30)) & ((1L << (DOLLAR - 30)) | (1L << (EURO - 30)) | (1L << (PERCENTAGE - 30)) | (1L << (GRADE - 30)) | (1L << (IDENTIFIER - 30)))) != 0)) {
					{
					setState(229); measure();
					}
				}

				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(233); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(232); integerValue();
					}
					}
					setState(235); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NATURAL_VALUE || _la==NEGATIVE_VALUE );
				setState(238);
				_la = _input.LA(1);
				if (((((_la - 30)) & ~0x3f) == 0 && ((1L << (_la - 30)) & ((1L << (DOLLAR - 30)) | (1L << (EURO - 30)) | (1L << (PERCENTAGE - 30)) | (1L << (GRADE - 30)) | (1L << (IDENTIFIER - 30)))) != 0)) {
					{
					setState(237); measure();
					}
				}

				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(241); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(240); doubleValue();
					}
					}
					setState(243); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NATURAL_VALUE) | (1L << NEGATIVE_VALUE) | (1L << DOUBLE_VALUE))) != 0) );
				setState(246);
				_la = _input.LA(1);
				if (((((_la - 30)) & ~0x3f) == 0 && ((1L << (_la - 30)) & ((1L << (DOLLAR - 30)) | (1L << (EURO - 30)) | (1L << (PERCENTAGE - 30)) | (1L << (GRADE - 30)) | (1L << (IDENTIFIER - 30)))) != 0)) {
					{
					setState(245); measure();
					}
				}

				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(249); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(248); coordinateValue();
					}
					}
					setState(251); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==COORDINATE_VALUE );
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(254); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(253); dateValue();
					}
					}
					setState(256); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==DATE_VALUE );
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(259); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(258); portValue();
					}
					}
					setState(261); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==PORT_VALUE );
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(263); metaWord();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(264); match(EMPTY);
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
			setState(267); metaidentifier();
			setState(271);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DOT) {
				{
				{
				setState(268); metaWordNames();
				}
				}
				setState(273);
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
			setState(274); match(DOT);
			setState(275); match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 28, RULE_body);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(277); match(NEW_LINE_INDENT);
			setState(291); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(284);
				switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
				case 1:
					{
					setState(278); attribute();
					}
					break;
				case 2:
					{
					setState(279); concept();
					}
					break;
				case 3:
					{
					setState(280); varInit();
					}
					break;
				case 4:
					{
					setState(281); facetApply();
					}
					break;
				case 5:
					{
					setState(282); facetTarget();
					}
					break;
				case 6:
					{
					setState(283); conceptReference();
					}
					break;
				}
				setState(287); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(286); match(NEWLINE);
					}
					}
					setState(289); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NEWLINE );
				}
				}
				setState(293); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << METAIDENTIFIER) | (1L << SUB) | (1L << HAS) | (1L << ON) | (1L << IS) | (1L << VAR))) != 0) || _la==IDENTIFIER || _la==DOC );
			setState(295); match(DEDENT);
			}
		}
		catch (RecognitionException re) {
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
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
		public DocContext doc() {
			return getRuleContext(DocContext.class,0);
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
		enterRule(_localctx, 30, RULE_conceptReference);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(298);
			_la = _input.LA(1);
			if (_la==DOC) {
				{
				setState(297); doc();
				}
			}

			setState(300); match(HAS);
			setState(301); identifierReference();
			setState(303);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(302); match(IDENTIFIER);
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
		enterRule(_localctx, 32, RULE_facetApply);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(305); match(IS);
			setState(306); metaidentifier();
			setState(308);
			_la = _input.LA(1);
			if (_la==LEFT_PARENTHESIS) {
				{
				setState(307); parameters();
				}
			}

			setState(312);
			_la = _input.LA(1);
			if (_la==WITH) {
				{
				setState(310); match(WITH);
				setState(311); metaidentifier();
				}
			}

			setState(315);
			_la = _input.LA(1);
			if (_la==NEW_LINE_INDENT) {
				{
				setState(314); body();
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
		enterRule(_localctx, 34, RULE_facetTarget);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(317); match(ON);
			setState(318); identifierReference();
			setState(320);
			_la = _input.LA(1);
			if (_la==ALWAYS) {
				{
				setState(319); match(ALWAYS);
				}
			}

			setState(323);
			_la = _input.LA(1);
			if (_la==NEW_LINE_INDENT) {
				{
				setState(322); body();
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
		public PortAttributeContext portAttribute() {
			return getRuleContext(PortAttributeContext.class,0);
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
		enterRule(_localctx, 36, RULE_attribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(326);
			_la = _input.LA(1);
			if (_la==DOC) {
				{
				setState(325); doc();
				}
			}

			setState(328); match(VAR);
			setState(340);
			switch (_input.LA(1)) {
			case NATURAL_TYPE:
				{
				setState(329); naturalAttribute();
				}
				break;
			case INT_TYPE:
				{
				setState(330); integerAttribute();
				}
				break;
			case DOUBLE_TYPE:
				{
				setState(331); doubleAttribute();
				}
				break;
			case BOOLEAN_TYPE:
				{
				setState(332); booleanAttribute();
				}
				break;
			case STRING_TYPE:
				{
				setState(333); stringAttribute();
				}
				break;
			case DATE_TYPE:
				{
				setState(334); dateAttribute();
				}
				break;
			case COORDINATE_TYPE:
				{
				setState(335); coordinateAttribute();
				}
				break;
			case PORT_TYPE:
				{
				setState(336); portAttribute();
				}
				break;
			case RESOURCE:
				{
				setState(337); resource();
				}
				break;
			case IDENTIFIER:
				{
				setState(338); reference();
				}
				break;
			case WORD:
				{
				setState(339); word();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(343);
			_la = _input.LA(1);
			if (_la==IS) {
				{
				setState(342); annotations();
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
		enterRule(_localctx, 38, RULE_resource);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(345); match(RESOURCE);
			setState(346); attributeType();
			setState(347); match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 40, RULE_word);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(349); match(WORD);
			setState(351);
			_la = _input.LA(1);
			if (_la==LIST) {
				{
				setState(350); match(LIST);
				}
			}

			setState(353); match(IDENTIFIER);
			setState(354); match(NEW_LINE_INDENT);
			setState(358); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(355); wordNames();
				setState(356); match(NEWLINE);
				}
				}
				setState(360); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==IDENTIFIER );
			setState(362); match(DEDENT);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 42, RULE_wordNames);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(364); match(IDENTIFIER);
			setState(366);
			_la = _input.LA(1);
			if (_la==STAR) {
				{
				setState(365); match(STAR);
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
		enterRule(_localctx, 44, RULE_reference);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(368); identifierReference();
			setState(370);
			_la = _input.LA(1);
			if (_la==LIST) {
				{
				setState(369); match(LIST);
				}
			}

			setState(372); match(IDENTIFIER);
			setState(375);
			_la = _input.LA(1);
			if (_la==EQUALS) {
				{
				setState(373); match(EQUALS);
				setState(374); match(EMPTY);
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
		enterRule(_localctx, 46, RULE_booleanAttribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(377); match(BOOLEAN_TYPE);
			setState(379);
			_la = _input.LA(1);
			if (_la==LIST) {
				{
				setState(378); match(LIST);
				}
			}

			setState(381); match(IDENTIFIER);
			setState(389);
			switch (_input.LA(1)) {
			case EQUALS:
				{
				setState(382); match(EQUALS);
				setState(384); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(383); booleanValue();
					}
					}
					setState(386); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==BOOLEAN_VALUE );
				}
				break;
			case EMPTY:
				{
				setState(388); match(EMPTY);
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
		enterRule(_localctx, 48, RULE_stringAttribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(391); match(STRING_TYPE);
			setState(393);
			_la = _input.LA(1);
			if (_la==LIST) {
				{
				setState(392); match(LIST);
				}
			}

			setState(395); match(IDENTIFIER);
			setState(403);
			switch (_input.LA(1)) {
			case EQUALS:
				{
				setState(396); match(EQUALS);
				setState(398); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(397); stringValue();
					}
					}
					setState(400); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==STRING_VALUE || _la==STRING_MULTILINE_VALUE_KEY );
				}
				break;
			case EMPTY:
				{
				setState(402); match(EMPTY);
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
		enterRule(_localctx, 50, RULE_naturalAttribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(405); match(NATURAL_TYPE);
			setState(407);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(406); attributeType();
				}
			}

			setState(410);
			_la = _input.LA(1);
			if (_la==LIST) {
				{
				setState(409); match(LIST);
				}
			}

			setState(412); match(IDENTIFIER);
			setState(423);
			switch (_input.LA(1)) {
			case EQUALS:
				{
				setState(413); match(EQUALS);
				setState(415); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(414); naturalValue();
					}
					}
					setState(417); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NATURAL_VALUE );
				setState(420);
				_la = _input.LA(1);
				if (((((_la - 30)) & ~0x3f) == 0 && ((1L << (_la - 30)) & ((1L << (DOLLAR - 30)) | (1L << (EURO - 30)) | (1L << (PERCENTAGE - 30)) | (1L << (GRADE - 30)) | (1L << (IDENTIFIER - 30)))) != 0)) {
					{
					setState(419); measure();
					}
				}

				}
				break;
			case EMPTY:
				{
				setState(422); match(EMPTY);
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
		enterRule(_localctx, 52, RULE_integerAttribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(425); match(INT_TYPE);
			setState(427);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(426); attributeType();
				}
			}

			setState(430);
			_la = _input.LA(1);
			if (_la==LIST) {
				{
				setState(429); match(LIST);
				}
			}

			setState(432); match(IDENTIFIER);
			setState(443);
			switch (_input.LA(1)) {
			case EQUALS:
				{
				setState(433); match(EQUALS);
				setState(435); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(434); integerValue();
					}
					}
					setState(437); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NATURAL_VALUE || _la==NEGATIVE_VALUE );
				setState(440);
				_la = _input.LA(1);
				if (((((_la - 30)) & ~0x3f) == 0 && ((1L << (_la - 30)) & ((1L << (DOLLAR - 30)) | (1L << (EURO - 30)) | (1L << (PERCENTAGE - 30)) | (1L << (GRADE - 30)) | (1L << (IDENTIFIER - 30)))) != 0)) {
					{
					setState(439); measure();
					}
				}

				}
				break;
			case EMPTY:
				{
				setState(442); match(EMPTY);
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
		enterRule(_localctx, 54, RULE_doubleAttribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(445); match(DOUBLE_TYPE);
			setState(447);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(446); attributeType();
				}
			}

			setState(450);
			_la = _input.LA(1);
			if (_la==LIST) {
				{
				setState(449); match(LIST);
				}
			}

			setState(452); match(IDENTIFIER);
			setState(463);
			switch (_input.LA(1)) {
			case EQUALS:
				{
				setState(453); match(EQUALS);
				setState(455); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(454); doubleValue();
					}
					}
					setState(457); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NATURAL_VALUE) | (1L << NEGATIVE_VALUE) | (1L << DOUBLE_VALUE))) != 0) );
				setState(460);
				_la = _input.LA(1);
				if (((((_la - 30)) & ~0x3f) == 0 && ((1L << (_la - 30)) & ((1L << (DOLLAR - 30)) | (1L << (EURO - 30)) | (1L << (PERCENTAGE - 30)) | (1L << (GRADE - 30)) | (1L << (IDENTIFIER - 30)))) != 0)) {
					{
					setState(459); measure();
					}
				}

				}
				break;
			case EMPTY:
				{
				setState(462); match(EMPTY);
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
		enterRule(_localctx, 56, RULE_dateAttribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(465); match(DATE_TYPE);
			setState(467);
			_la = _input.LA(1);
			if (_la==LIST) {
				{
				setState(466); match(LIST);
				}
			}

			setState(469); match(IDENTIFIER);
			setState(477);
			switch (_input.LA(1)) {
			case EQUALS:
				{
				setState(470); match(EQUALS);
				setState(472); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(471); dateValue();
					}
					}
					setState(474); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==DATE_VALUE );
				}
				break;
			case EMPTY:
				{
				setState(476); match(EMPTY);
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
		enterRule(_localctx, 58, RULE_coordinateAttribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(479); match(COORDINATE_TYPE);
			setState(481);
			_la = _input.LA(1);
			if (_la==LIST) {
				{
				setState(480); match(LIST);
				}
			}

			setState(483); match(IDENTIFIER);
			setState(493);
			_la = _input.LA(1);
			if (_la==EQUALS) {
				{
				setState(484); match(EQUALS);
				setState(491);
				switch (_input.LA(1)) {
				case COORDINATE_VALUE:
					{
					setState(486); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(485); coordinateValue();
						}
						}
						setState(488); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==COORDINATE_VALUE );
					}
					break;
				case EMPTY:
					{
					setState(490); match(EMPTY);
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

	public static class PortAttributeContext extends ParserRuleContext {
		public TerminalNode EMPTY() { return getToken(TaraGrammar.EMPTY, 0); }
		public TerminalNode EQUALS() { return getToken(TaraGrammar.EQUALS, 0); }
		public TerminalNode PORT_TYPE() { return getToken(TaraGrammar.PORT_TYPE, 0); }
		public List<PortValueContext> portValue() {
			return getRuleContexts(PortValueContext.class);
		}
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
		public TerminalNode LIST() { return getToken(TaraGrammar.LIST, 0); }
		public PortValueContext portValue(int i) {
			return getRuleContext(PortValueContext.class,i);
		}
		public PortAttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_portAttribute; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterPortAttribute(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitPortAttribute(this);
		}
	}

	public final PortAttributeContext portAttribute() throws RecognitionException {
		PortAttributeContext _localctx = new PortAttributeContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_portAttribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(495); match(PORT_TYPE);
			setState(497);
			_la = _input.LA(1);
			if (_la==LIST) {
				{
				setState(496); match(LIST);
				}
			}

			setState(499); match(IDENTIFIER);
			setState(509);
			_la = _input.LA(1);
			if (_la==EQUALS) {
				{
				setState(500); match(EQUALS);
				setState(507);
				switch (_input.LA(1)) {
				case PORT_VALUE:
					{
					setState(502); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(501); portValue();
						}
						}
						setState(504); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==PORT_VALUE );
					}
					break;
				case EMPTY:
					{
					setState(506); match(EMPTY);
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
			setState(511); match(COLON);
			setState(512); measure();
			}
		}
		catch (RecognitionException re) {
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
			setState(514); match(NATURAL_VALUE);
			}
		}
		catch (RecognitionException re) {
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
			setState(516);
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
			setState(518);
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
			setState(520); match(BOOLEAN_VALUE);
			}
		}
		catch (RecognitionException re) {
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
			setState(522);
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

	public static class PortValueContext extends ParserRuleContext {
		public TerminalNode PORT_VALUE() { return getToken(TaraGrammar.PORT_VALUE, 0); }
		public PortValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_portValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterPortValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitPortValue(this);
		}
	}

	public final PortValueContext portValue() throws RecognitionException {
		PortValueContext _localctx = new PortValueContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_portValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(524); match(PORT_VALUE);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 76, RULE_dateValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(526); match(DATE_VALUE);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 78, RULE_coordinateValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(528); match(COORDINATE_VALUE);
			}
		}
		catch (RecognitionException re) {
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
			setState(530);
			_la = _input.LA(1);
			if ( !(((((_la - 30)) & ~0x3f) == 0 && ((1L << (_la - 30)) & ((1L << (DOLLAR - 30)) | (1L << (EURO - 30)) | (1L << (PERCENTAGE - 30)) | (1L << (GRADE - 30)) | (1L << (IDENTIFIER - 30)))) != 0)) ) {
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
		public List<TerminalNode> UNIVERSAL() { return getTokens(TaraGrammar.UNIVERSAL); }
		public List<TerminalNode> NAMED() { return getTokens(TaraGrammar.NAMED); }
		public TerminalNode PROPERTY(int i) {
			return getToken(TaraGrammar.PROPERTY, i);
		}
		public TerminalNode FACET(int i) {
			return getToken(TaraGrammar.FACET, i);
		}
		public List<TerminalNode> PRIVATE() { return getTokens(TaraGrammar.PRIVATE); }
		public TerminalNode IS() { return getToken(TaraGrammar.IS, 0); }
		public List<TerminalNode> SINGLE() { return getTokens(TaraGrammar.SINGLE); }
		public TerminalNode TERMINAL(int i) {
			return getToken(TaraGrammar.TERMINAL, i);
		}
		public TerminalNode UNIVERSAL(int i) {
			return getToken(TaraGrammar.UNIVERSAL, i);
		}
		public List<TerminalNode> TERMINAL() { return getTokens(TaraGrammar.TERMINAL); }
		public List<TerminalNode> REQUIRED() { return getTokens(TaraGrammar.REQUIRED); }
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
			setState(532); match(IS);
			setState(534); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(533);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PRIVATE) | (1L << SINGLE) | (1L << REQUIRED) | (1L << COMPONENT) | (1L << FACET) | (1L << INTENTION) | (1L << TERMINAL) | (1L << NAMED) | (1L << PROPERTY) | (1L << UNIVERSAL))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				}
				}
				setState(536); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PRIVATE) | (1L << SINGLE) | (1L << REQUIRED) | (1L << COMPONENT) | (1L << FACET) | (1L << INTENTION) | (1L << TERMINAL) | (1L << NAMED) | (1L << PROPERTY) | (1L << UNIVERSAL))) != 0) );
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 84, RULE_varInit);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(538); match(IDENTIFIER);
			setState(539); match(EQUALS);
			setState(540); initValue();
			}
		}
		catch (RecognitionException re) {
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
			setState(545);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,87,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(542); hierarchy();
					}
					} 
				}
				setState(547);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,87,_ctx);
			}
			setState(548); match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
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
			setState(553);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,88,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(550); hierarchy();
					}
					} 
				}
				setState(555);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,88,_ctx);
			}
			setState(556); match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
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
			setState(558); match(IDENTIFIER);
			setState(559); match(DOT);
			}
		}
		catch (RecognitionException re) {
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
			setState(561);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3N\u0236\4\2\t\2\4"+
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
		"\t\3\t\3\t\3\t\5\t\u00ae\n\t\3\t\3\t\5\t\u00b2\n\t\5\t\u00b4\n\t\3\n\3"+
		"\n\5\n\u00b8\n\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\7\13\u00c2\n\13"+
		"\f\13\16\13\u00c5\13\13\3\13\3\13\3\13\7\13\u00ca\n\13\f\13\16\13\u00cd"+
		"\13\13\5\13\u00cf\n\13\3\f\3\f\3\f\3\r\6\r\u00d5\n\r\r\r\16\r\u00d6\3"+
		"\r\6\r\u00da\n\r\r\r\16\r\u00db\3\r\6\r\u00df\n\r\r\r\16\r\u00e0\3\r\6"+
		"\r\u00e4\n\r\r\r\16\r\u00e5\3\r\5\r\u00e9\n\r\3\r\6\r\u00ec\n\r\r\r\16"+
		"\r\u00ed\3\r\5\r\u00f1\n\r\3\r\6\r\u00f4\n\r\r\r\16\r\u00f5\3\r\5\r\u00f9"+
		"\n\r\3\r\6\r\u00fc\n\r\r\r\16\r\u00fd\3\r\6\r\u0101\n\r\r\r\16\r\u0102"+
		"\3\r\6\r\u0106\n\r\r\r\16\r\u0107\3\r\3\r\5\r\u010c\n\r\3\16\3\16\7\16"+
		"\u0110\n\16\f\16\16\16\u0113\13\16\3\17\3\17\3\17\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\5\20\u011f\n\20\3\20\6\20\u0122\n\20\r\20\16\20\u0123"+
		"\6\20\u0126\n\20\r\20\16\20\u0127\3\20\3\20\3\21\5\21\u012d\n\21\3\21"+
		"\3\21\3\21\5\21\u0132\n\21\3\22\3\22\3\22\5\22\u0137\n\22\3\22\3\22\5"+
		"\22\u013b\n\22\3\22\5\22\u013e\n\22\3\23\3\23\3\23\5\23\u0143\n\23\3\23"+
		"\5\23\u0146\n\23\3\24\5\24\u0149\n\24\3\24\3\24\3\24\3\24\3\24\3\24\3"+
		"\24\3\24\3\24\3\24\3\24\3\24\5\24\u0157\n\24\3\24\5\24\u015a\n\24\3\25"+
		"\3\25\3\25\3\25\3\26\3\26\5\26\u0162\n\26\3\26\3\26\3\26\3\26\3\26\6\26"+
		"\u0169\n\26\r\26\16\26\u016a\3\26\3\26\3\27\3\27\5\27\u0171\n\27\3\30"+
		"\3\30\5\30\u0175\n\30\3\30\3\30\3\30\5\30\u017a\n\30\3\31\3\31\5\31\u017e"+
		"\n\31\3\31\3\31\3\31\6\31\u0183\n\31\r\31\16\31\u0184\3\31\5\31\u0188"+
		"\n\31\3\32\3\32\5\32\u018c\n\32\3\32\3\32\3\32\6\32\u0191\n\32\r\32\16"+
		"\32\u0192\3\32\5\32\u0196\n\32\3\33\3\33\5\33\u019a\n\33\3\33\5\33\u019d"+
		"\n\33\3\33\3\33\3\33\6\33\u01a2\n\33\r\33\16\33\u01a3\3\33\5\33\u01a7"+
		"\n\33\3\33\5\33\u01aa\n\33\3\34\3\34\5\34\u01ae\n\34\3\34\5\34\u01b1\n"+
		"\34\3\34\3\34\3\34\6\34\u01b6\n\34\r\34\16\34\u01b7\3\34\5\34\u01bb\n"+
		"\34\3\34\5\34\u01be\n\34\3\35\3\35\5\35\u01c2\n\35\3\35\5\35\u01c5\n\35"+
		"\3\35\3\35\3\35\6\35\u01ca\n\35\r\35\16\35\u01cb\3\35\5\35\u01cf\n\35"+
		"\3\35\5\35\u01d2\n\35\3\36\3\36\5\36\u01d6\n\36\3\36\3\36\3\36\6\36\u01db"+
		"\n\36\r\36\16\36\u01dc\3\36\5\36\u01e0\n\36\3\37\3\37\5\37\u01e4\n\37"+
		"\3\37\3\37\3\37\6\37\u01e9\n\37\r\37\16\37\u01ea\3\37\5\37\u01ee\n\37"+
		"\5\37\u01f0\n\37\3 \3 \5 \u01f4\n \3 \3 \3 \6 \u01f9\n \r \16 \u01fa\3"+
		" \5 \u01fe\n \5 \u0200\n \3!\3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\3&\3&\3\'"+
		"\3\'\3(\3(\3)\3)\3*\3*\3+\3+\6+\u0219\n+\r+\16+\u021a\3,\3,\3,\3,\3-\7"+
		"-\u0222\n-\f-\16-\u0225\13-\3-\3-\3.\7.\u022a\n.\f.\16.\u022d\13.\3.\3"+
		".\3/\3/\3/\3\60\3\60\3\60\2\2\61\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36"+
		" \"$&(*,.\60\62\64\668:<>@BDFHJLNPRTVXZ\\^\2\b\3\2<=\3\2<>\3\2?@\4\2 "+
		"#DD\3\2\r\26\4\2\3\3DD\u027c\2c\3\2\2\2\4}\3\2\2\2\6\u0082\3\2\2\2\b\u0086"+
		"\3\2\2\2\n\u008b\3\2\2\2\f\u0096\3\2\2\2\16\u009b\3\2\2\2\20\u00b3\3\2"+
		"\2\2\22\u00b5\3\2\2\2\24\u00ce\3\2\2\2\26\u00d0\3\2\2\2\30\u010b\3\2\2"+
		"\2\32\u010d\3\2\2\2\34\u0114\3\2\2\2\36\u0117\3\2\2\2 \u012c\3\2\2\2\""+
		"\u0133\3\2\2\2$\u013f\3\2\2\2&\u0148\3\2\2\2(\u015b\3\2\2\2*\u015f\3\2"+
		"\2\2,\u016e\3\2\2\2.\u0172\3\2\2\2\60\u017b\3\2\2\2\62\u0189\3\2\2\2\64"+
		"\u0197\3\2\2\2\66\u01ab\3\2\2\28\u01bf\3\2\2\2:\u01d3\3\2\2\2<\u01e1\3"+
		"\2\2\2>\u01f1\3\2\2\2@\u0201\3\2\2\2B\u0204\3\2\2\2D\u0206\3\2\2\2F\u0208"+
		"\3\2\2\2H\u020a\3\2\2\2J\u020c\3\2\2\2L\u020e\3\2\2\2N\u0210\3\2\2\2P"+
		"\u0212\3\2\2\2R\u0214\3\2\2\2T\u0216\3\2\2\2V\u021c\3\2\2\2X\u0223\3\2"+
		"\2\2Z\u022b\3\2\2\2\\\u0230\3\2\2\2^\u0233\3\2\2\2`b\7G\2\2a`\3\2\2\2"+
		"be\3\2\2\2ca\3\2\2\2cd\3\2\2\2dg\3\2\2\2ec\3\2\2\2fh\5\4\3\2gf\3\2\2\2"+
		"gh\3\2\2\2hj\3\2\2\2ik\7G\2\2ji\3\2\2\2kl\3\2\2\2lj\3\2\2\2lm\3\2\2\2"+
		"mw\3\2\2\2nr\5\16\b\2oq\7G\2\2po\3\2\2\2qt\3\2\2\2rp\3\2\2\2rs\3\2\2\2"+
		"sv\3\2\2\2tr\3\2\2\2un\3\2\2\2vy\3\2\2\2wu\3\2\2\2wx\3\2\2\2xz\3\2\2\2"+
		"yw\3\2\2\2z{\7\2\2\3{\3\3\2\2\2|~\5\6\4\2}|\3\2\2\2}~\3\2\2\2~\u0080\3"+
		"\2\2\2\177\u0081\5\b\5\2\u0080\177\3\2\2\2\u0080\u0081\3\2\2\2\u0081\5"+
		"\3\2\2\2\u0082\u0083\7\6\2\2\u0083\u0084\5X-\2\u0084\7\3\2\2\2\u0085\u0087"+
		"\5\n\6\2\u0086\u0085\3\2\2\2\u0087\u0088\3\2\2\2\u0088\u0086\3\2\2\2\u0088"+
		"\u0089\3\2\2\2\u0089\t\3\2\2\2\u008a\u008c\7G\2\2\u008b\u008a\3\2\2\2"+
		"\u008c\u008d\3\2\2\2\u008d\u008b\3\2\2\2\u008d\u008e\3\2\2\2\u008e\u008f"+
		"\3\2\2\2\u008f\u0090\7\5\2\2\u0090\u0093\5X-\2\u0091\u0092\7\7\2\2\u0092"+
		"\u0094\7D\2\2\u0093\u0091\3\2\2\2\u0093\u0094\3\2\2\2\u0094\13\3\2\2\2"+
		"\u0095\u0097\7I\2\2\u0096\u0095\3\2\2\2\u0097\u0098\3\2\2\2\u0098\u0096"+
		"\3\2\2\2\u0098\u0099\3\2\2\2\u0099\r\3\2\2\2\u009a\u009c\5\f\7\2\u009b"+
		"\u009a\3\2\2\2\u009b\u009c\3\2\2\2\u009c\u009d\3\2\2\2\u009d\u009f\5\20"+
		"\t\2\u009e\u00a0\5T+\2\u009f\u009e\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0\u00a2"+
		"\3\2\2\2\u00a1\u00a3\5\36\20\2\u00a2\u00a1\3\2\2\2\u00a2\u00a3\3\2\2\2"+
		"\u00a3\17\3\2\2\2\u00a4\u00a5\7\4\2\2\u00a5\u00b4\7D\2\2\u00a6\u00a8\5"+
		"^\60\2\u00a7\u00a9\5\22\n\2\u00a8\u00a7\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9"+
		"\u00aa\3\2\2\2\u00aa\u00ad\7D\2\2\u00ab\u00ac\7\f\2\2\u00ac\u00ae\5Z."+
		"\2\u00ad\u00ab\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae\u00b4\3\2\2\2\u00af\u00b1"+
		"\5^\60\2\u00b0\u00b2\5\22\n\2\u00b1\u00b0\3\2\2\2\u00b1\u00b2\3\2\2\2"+
		"\u00b2\u00b4\3\2\2\2\u00b3\u00a4\3\2\2\2\u00b3\u00a6\3\2\2\2\u00b3\u00af"+
		"\3\2\2\2\u00b4\21\3\2\2\2\u00b5\u00b7\7\30\2\2\u00b6\u00b8\5\24\13\2\u00b7"+
		"\u00b6\3\2\2\2\u00b7\u00b8\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9\u00ba\7\31"+
		"\2\2\u00ba\23\3\2\2\2\u00bb\u00bc\5\26\f\2\u00bc\u00c3\5\30\r\2\u00bd"+
		"\u00be\7%\2\2\u00be\u00bf\5\26\f\2\u00bf\u00c0\5\30\r\2\u00c0\u00c2\3"+
		"\2\2\2\u00c1\u00bd\3\2\2\2\u00c2\u00c5\3\2\2\2\u00c3\u00c1\3\2\2\2\u00c3"+
		"\u00c4\3\2\2\2\u00c4\u00cf\3\2\2\2\u00c5\u00c3\3\2\2\2\u00c6\u00cb\5\30"+
		"\r\2\u00c7\u00c8\7%\2\2\u00c8\u00ca\5\30\r\2\u00c9\u00c7\3\2\2\2\u00ca"+
		"\u00cd\3\2\2\2\u00cb\u00c9\3\2\2\2\u00cb\u00cc\3\2\2\2\u00cc\u00cf\3\2"+
		"\2\2\u00cd\u00cb\3\2\2\2\u00ce\u00bb\3\2\2\2\u00ce\u00c6\3\2\2\2\u00cf"+
		"\25\3\2\2\2\u00d0\u00d1\7D\2\2\u00d1\u00d2\7\'\2\2\u00d2\27\3\2\2\2\u00d3"+
		"\u00d5\5Z.\2\u00d4\u00d3\3\2\2\2\u00d5\u00d6\3\2\2\2\u00d6\u00d4\3\2\2"+
		"\2\u00d6\u00d7\3\2\2\2\u00d7\u010c\3\2\2\2\u00d8\u00da\5J&\2\u00d9\u00d8"+
		"\3\2\2\2\u00da\u00db\3\2\2\2\u00db\u00d9\3\2\2\2\u00db\u00dc\3\2\2\2\u00dc"+
		"\u010c\3\2\2\2\u00dd\u00df\5H%\2\u00de\u00dd\3\2\2\2\u00df\u00e0\3\2\2"+
		"\2\u00e0\u00de\3\2\2\2\u00e0\u00e1\3\2\2\2\u00e1\u010c\3\2\2\2\u00e2\u00e4"+
		"\5B\"\2\u00e3\u00e2\3\2\2\2\u00e4\u00e5\3\2\2\2\u00e5\u00e3\3\2\2\2\u00e5"+
		"\u00e6\3\2\2\2\u00e6\u00e8\3\2\2\2\u00e7\u00e9\5R*\2\u00e8\u00e7\3\2\2"+
		"\2\u00e8\u00e9\3\2\2\2\u00e9\u010c\3\2\2\2\u00ea\u00ec\5D#\2\u00eb\u00ea"+
		"\3\2\2\2\u00ec\u00ed\3\2\2\2\u00ed\u00eb\3\2\2\2\u00ed\u00ee\3\2\2\2\u00ee"+
		"\u00f0\3\2\2\2\u00ef\u00f1\5R*\2\u00f0\u00ef\3\2\2\2\u00f0\u00f1\3\2\2"+
		"\2\u00f1\u010c\3\2\2\2\u00f2\u00f4\5F$\2\u00f3\u00f2\3\2\2\2\u00f4\u00f5"+
		"\3\2\2\2\u00f5\u00f3\3\2\2\2\u00f5\u00f6\3\2\2\2\u00f6\u00f8\3\2\2\2\u00f7"+
		"\u00f9\5R*\2\u00f8\u00f7\3\2\2\2\u00f8\u00f9\3\2\2\2\u00f9\u010c\3\2\2"+
		"\2\u00fa\u00fc\5P)\2\u00fb\u00fa\3\2\2\2\u00fc\u00fd\3\2\2\2\u00fd\u00fb"+
		"\3\2\2\2\u00fd\u00fe\3\2\2\2\u00fe\u010c\3\2\2\2\u00ff\u0101\5N(\2\u0100"+
		"\u00ff\3\2\2\2\u0101\u0102\3\2\2\2\u0102\u0100\3\2\2\2\u0102\u0103\3\2"+
		"\2\2\u0103\u010c\3\2\2\2\u0104\u0106\5L\'\2\u0105\u0104\3\2\2\2\u0106"+
		"\u0107\3\2\2\2\u0107\u0105\3\2\2\2\u0107\u0108\3\2\2\2\u0108\u010c\3\2"+
		"\2\2\u0109\u010c\5\32\16\2\u010a\u010c\7:\2\2\u010b\u00d4\3\2\2\2\u010b"+
		"\u00d9\3\2\2\2\u010b\u00de\3\2\2\2\u010b\u00e3\3\2\2\2\u010b\u00eb\3\2"+
		"\2\2\u010b\u00f3\3\2\2\2\u010b\u00fb\3\2\2\2\u010b\u0100\3\2\2\2\u010b"+
		"\u0105\3\2\2\2\u010b\u0109\3\2\2\2\u010b\u010a\3\2\2\2\u010c\31\3\2\2"+
		"\2\u010d\u0111\5^\60\2\u010e\u0110\5\34\17\2\u010f\u010e\3\2\2\2\u0110"+
		"\u0113\3\2\2\2\u0111\u010f\3\2\2\2\u0111\u0112\3\2\2\2\u0112\33\3\2\2"+
		"\2\u0113\u0111\3\2\2\2\u0114\u0115\7&\2\2\u0115\u0116\7D\2\2\u0116\35"+
		"\3\2\2\2\u0117\u0125\7L\2\2\u0118\u011f\5&\24\2\u0119\u011f\5\16\b\2\u011a"+
		"\u011f\5V,\2\u011b\u011f\5\"\22\2\u011c\u011f\5$\23\2\u011d\u011f\5 \21"+
		"\2\u011e\u0118\3\2\2\2\u011e\u0119\3\2\2\2\u011e\u011a\3\2\2\2\u011e\u011b"+
		"\3\2\2\2\u011e\u011c\3\2\2\2\u011e\u011d\3\2\2\2\u011f\u0121\3\2\2\2\u0120"+
		"\u0122\7G\2\2\u0121\u0120\3\2\2\2\u0122\u0123\3\2\2\2\u0123\u0121\3\2"+
		"\2\2\u0123\u0124\3\2\2\2\u0124\u0126\3\2\2\2\u0125\u011e\3\2\2\2\u0126"+
		"\u0127\3\2\2\2\u0127\u0125\3\2\2\2\u0127\u0128\3\2\2\2\u0128\u0129\3\2"+
		"\2\2\u0129\u012a\7M\2\2\u012a\37\3\2\2\2\u012b\u012d\5\f\7\2\u012c\u012b"+
		"\3\2\2\2\u012c\u012d\3\2\2\2\u012d\u012e\3\2\2\2\u012e\u012f\7\b\2\2\u012f"+
		"\u0131\5Z.\2\u0130\u0132\7D\2\2\u0131\u0130\3\2\2\2\u0131\u0132\3\2\2"+
		"\2\u0132!\3\2\2\2\u0133\u0134\7\n\2\2\u0134\u0136\5^\60\2\u0135\u0137"+
		"\5\22\n\2\u0136\u0135\3\2\2\2\u0136\u0137\3\2\2\2\u0137\u013a\3\2\2\2"+
		"\u0138\u0139\7\13\2\2\u0139\u013b\5^\60\2\u013a\u0138\3\2\2\2\u013a\u013b"+
		"\3\2\2\2\u013b\u013d\3\2\2\2\u013c\u013e\5\36\20\2\u013d\u013c\3\2\2\2"+
		"\u013d\u013e\3\2\2\2\u013e#\3\2\2\2\u013f\u0140\7\t\2\2\u0140\u0142\5"+
		"Z.\2\u0141\u0143\7\27\2\2\u0142\u0141\3\2\2\2\u0142\u0143\3\2\2\2\u0143"+
		"\u0145\3\2\2\2\u0144\u0146\5\36\20\2\u0145\u0144\3\2\2\2\u0145\u0146\3"+
		"\2\2\2\u0146%\3\2\2\2\u0147\u0149\5\f\7\2\u0148\u0147\3\2\2\2\u0148\u0149"+
		"\3\2\2\2\u0149\u014a\3\2\2\2\u014a\u0156\7/\2\2\u014b\u0157\5\64\33\2"+
		"\u014c\u0157\5\66\34\2\u014d\u0157\58\35\2\u014e\u0157\5\60\31\2\u014f"+
		"\u0157\5\62\32\2\u0150\u0157\5:\36\2\u0151\u0157\5<\37\2\u0152\u0157\5"+
		"> \2\u0153\u0157\5(\25\2\u0154\u0157\5.\30\2\u0155\u0157\5*\26\2\u0156"+
		"\u014b\3\2\2\2\u0156\u014c\3\2\2\2\u0156\u014d\3\2\2\2\u0156\u014e\3\2"+
		"\2\2\u0156\u014f\3\2\2\2\u0156\u0150\3\2\2\2\u0156\u0151\3\2\2\2\u0156"+
		"\u0152\3\2\2\2\u0156\u0153\3\2\2\2\u0156\u0154\3\2\2\2\u0156\u0155\3\2"+
		"\2\2\u0157\u0159\3\2\2\2\u0158\u015a\5T+\2\u0159\u0158\3\2\2\2\u0159\u015a"+
		"\3\2\2\2\u015a\'\3\2\2\2\u015b\u015c\7\61\2\2\u015c\u015d\5@!\2\u015d"+
		"\u015e\7D\2\2\u015e)\3\2\2\2\u015f\u0161\7\60\2\2\u0160\u0162\7\34\2\2"+
		"\u0161\u0160\3\2\2\2\u0161\u0162\3\2\2\2\u0162\u0163\3\2\2\2\u0163\u0164"+
		"\7D\2\2\u0164\u0168\7L\2\2\u0165\u0166\5,\27\2\u0166\u0167\7G\2\2\u0167"+
		"\u0169\3\2\2\2\u0168\u0165\3\2\2\2\u0169\u016a\3\2\2\2\u016a\u0168\3\2"+
		"\2\2\u016a\u016b\3\2\2\2\u016b\u016c\3\2\2\2\u016c\u016d\7M\2\2\u016d"+
		"+\3\2\2\2\u016e\u0170\7D\2\2\u016f\u0171\7*\2\2\u0170\u016f\3\2\2\2\u0170"+
		"\u0171\3\2\2\2\u0171-\3\2\2\2\u0172\u0174\5Z.\2\u0173\u0175\7\34\2\2\u0174"+
		"\u0173\3\2\2\2\u0174\u0175\3\2\2\2\u0175\u0176\3\2\2\2\u0176\u0179\7D"+
		"\2\2\u0177\u0178\7\'\2\2\u0178\u017a\7:\2\2\u0179\u0177\3\2\2\2\u0179"+
		"\u017a\3\2\2\2\u017a/\3\2\2\2\u017b\u017d\78\2\2\u017c\u017e\7\34\2\2"+
		"\u017d\u017c\3\2\2\2\u017d\u017e\3\2\2\2\u017e\u017f\3\2\2\2\u017f\u0187"+
		"\7D\2\2\u0180\u0182\7\'\2\2\u0181\u0183\5H%\2\u0182\u0181\3\2\2\2\u0183"+
		"\u0184\3\2\2\2\u0184\u0182\3\2\2\2\u0184\u0185\3\2\2\2\u0185\u0188\3\2"+
		"\2\2\u0186\u0188\7:\2\2\u0187\u0180\3\2\2\2\u0187\u0186\3\2\2\2\u0187"+
		"\u0188\3\2\2\2\u0188\61\3\2\2\2\u0189\u018b\7\67\2\2\u018a\u018c\7\34"+
		"\2\2\u018b\u018a\3\2\2\2\u018b\u018c\3\2\2\2\u018c\u018d\3\2\2\2\u018d"+
		"\u0195\7D\2\2\u018e\u0190\7\'\2\2\u018f\u0191\5J&\2\u0190\u018f\3\2\2"+
		"\2\u0191\u0192\3\2\2\2\u0192\u0190\3\2\2\2\u0192\u0193\3\2\2\2\u0193\u0196"+
		"\3\2\2\2\u0194\u0196\7:\2\2\u0195\u018e\3\2\2\2\u0195\u0194\3\2\2\2\u0195"+
		"\u0196\3\2\2\2\u0196\63\3\2\2\2\u0197\u0199\7\65\2\2\u0198\u019a\5@!\2"+
		"\u0199\u0198\3\2\2\2\u0199\u019a\3\2\2\2\u019a\u019c\3\2\2\2\u019b\u019d"+
		"\7\34\2\2\u019c\u019b\3\2\2\2\u019c\u019d\3\2\2\2\u019d\u019e\3\2\2\2"+
		"\u019e\u01a9\7D\2\2\u019f\u01a1\7\'\2\2\u01a0\u01a2\5B\"\2\u01a1\u01a0"+
		"\3\2\2\2\u01a2\u01a3\3\2\2\2\u01a3\u01a1\3\2\2\2\u01a3\u01a4\3\2\2\2\u01a4"+
		"\u01a6\3\2\2\2\u01a5\u01a7\5R*\2\u01a6\u01a5\3\2\2\2\u01a6\u01a7\3\2\2"+
		"\2\u01a7\u01aa\3\2\2\2\u01a8\u01aa\7:\2\2\u01a9\u019f\3\2\2\2\u01a9\u01a8"+
		"\3\2\2\2\u01a9\u01aa\3\2\2\2\u01aa\65\3\2\2\2\u01ab\u01ad\7\64\2\2\u01ac"+
		"\u01ae\5@!\2\u01ad\u01ac\3\2\2\2\u01ad\u01ae\3\2\2\2\u01ae\u01b0\3\2\2"+
		"\2\u01af\u01b1\7\34\2\2\u01b0\u01af\3\2\2\2\u01b0\u01b1\3\2\2\2\u01b1"+
		"\u01b2\3\2\2\2\u01b2\u01bd\7D\2\2\u01b3\u01b5\7\'\2\2\u01b4\u01b6\5D#"+
		"\2\u01b5\u01b4\3\2\2\2\u01b6\u01b7\3\2\2\2\u01b7\u01b5\3\2\2\2\u01b7\u01b8"+
		"\3\2\2\2\u01b8\u01ba\3\2\2\2\u01b9\u01bb\5R*\2\u01ba\u01b9\3\2\2\2\u01ba"+
		"\u01bb\3\2\2\2\u01bb\u01be\3\2\2\2\u01bc\u01be\7:\2\2\u01bd\u01b3\3\2"+
		"\2\2\u01bd\u01bc\3\2\2\2\u01bd\u01be\3\2\2\2\u01be\67\3\2\2\2\u01bf\u01c1"+
		"\7\66\2\2\u01c0\u01c2\5@!\2\u01c1\u01c0\3\2\2\2\u01c1\u01c2\3\2\2\2\u01c2"+
		"\u01c4\3\2\2\2\u01c3\u01c5\7\34\2\2\u01c4\u01c3\3\2\2\2\u01c4\u01c5\3"+
		"\2\2\2\u01c5\u01c6\3\2\2\2\u01c6\u01d1\7D\2\2\u01c7\u01c9\7\'\2\2\u01c8"+
		"\u01ca\5F$\2\u01c9\u01c8\3\2\2\2\u01ca\u01cb\3\2\2\2\u01cb\u01c9\3\2\2"+
		"\2\u01cb\u01cc\3\2\2\2\u01cc\u01ce\3\2\2\2\u01cd\u01cf\5R*\2\u01ce\u01cd"+
		"\3\2\2\2\u01ce\u01cf\3\2\2\2\u01cf\u01d2\3\2\2\2\u01d0\u01d2\7:\2\2\u01d1"+
		"\u01c7\3\2\2\2\u01d1\u01d0\3\2\2\2\u01d1\u01d2\3\2\2\2\u01d29\3\2\2\2"+
		"\u01d3\u01d5\79\2\2\u01d4\u01d6\7\34\2\2\u01d5\u01d4\3\2\2\2\u01d5\u01d6"+
		"\3\2\2\2\u01d6\u01d7\3\2\2\2\u01d7\u01df\7D\2\2\u01d8\u01da\7\'\2\2\u01d9"+
		"\u01db\5N(\2\u01da\u01d9\3\2\2\2\u01db\u01dc\3\2\2\2\u01dc\u01da\3\2\2"+
		"\2\u01dc\u01dd\3\2\2\2\u01dd\u01e0\3\2\2\2\u01de\u01e0\7:\2\2\u01df\u01d8"+
		"\3\2\2\2\u01df\u01de\3\2\2\2\u01df\u01e0\3\2\2\2\u01e0;\3\2\2\2\u01e1"+
		"\u01e3\7\63\2\2\u01e2\u01e4\7\34\2\2\u01e3\u01e2\3\2\2\2\u01e3\u01e4\3"+
		"\2\2\2\u01e4\u01e5\3\2\2\2\u01e5\u01ef\7D\2\2\u01e6\u01ed\7\'\2\2\u01e7"+
		"\u01e9\5P)\2\u01e8\u01e7\3\2\2\2\u01e9\u01ea\3\2\2\2\u01ea\u01e8\3\2\2"+
		"\2\u01ea\u01eb\3\2\2\2\u01eb\u01ee\3\2\2\2\u01ec\u01ee\7:\2\2\u01ed\u01e8"+
		"\3\2\2\2\u01ed\u01ec\3\2\2\2\u01ee\u01f0\3\2\2\2\u01ef\u01e6\3\2\2\2\u01ef"+
		"\u01f0\3\2\2\2\u01f0=\3\2\2\2\u01f1\u01f3\7\62\2\2\u01f2\u01f4\7\34\2"+
		"\2\u01f3\u01f2\3\2\2\2\u01f3\u01f4\3\2\2\2\u01f4\u01f5\3\2\2\2\u01f5\u01ff"+
		"\7D\2\2\u01f6\u01fd\7\'\2\2\u01f7\u01f9\5L\'\2\u01f8\u01f7\3\2\2\2\u01f9"+
		"\u01fa\3\2\2\2\u01fa\u01f8\3\2\2\2\u01fa\u01fb\3\2\2\2\u01fb\u01fe\3\2"+
		"\2\2\u01fc\u01fe\7:\2\2\u01fd\u01f8\3\2\2\2\u01fd\u01fc\3\2\2\2\u01fe"+
		"\u0200\3\2\2\2\u01ff\u01f6\3\2\2\2\u01ff\u0200\3\2\2\2\u0200?\3\2\2\2"+
		"\u0201\u0202\7$\2\2\u0202\u0203\5R*\2\u0203A\3\2\2\2\u0204\u0205\7<\2"+
		"\2\u0205C\3\2\2\2\u0206\u0207\t\2\2\2\u0207E\3\2\2\2\u0208\u0209\t\3\2"+
		"\2\u0209G\3\2\2\2\u020a\u020b\7;\2\2\u020bI\3\2\2\2\u020c\u020d\t\4\2"+
		"\2\u020dK\3\2\2\2\u020e\u020f\7A\2\2\u020fM\3\2\2\2\u0210\u0211\7B\2\2"+
		"\u0211O\3\2\2\2\u0212\u0213\7C\2\2\u0213Q\3\2\2\2\u0214\u0215\t\5\2\2"+
		"\u0215S\3\2\2\2\u0216\u0218\7\n\2\2\u0217\u0219\t\6\2\2\u0218\u0217\3"+
		"\2\2\2\u0219\u021a\3\2\2\2\u021a\u0218\3\2\2\2\u021a\u021b\3\2\2\2\u021b"+
		"U\3\2\2\2\u021c\u021d\7D\2\2\u021d\u021e\7\'\2\2\u021e\u021f\5\30\r\2"+
		"\u021fW\3\2\2\2\u0220\u0222\5\\/\2\u0221\u0220\3\2\2\2\u0222\u0225\3\2"+
		"\2\2\u0223\u0221\3\2\2\2\u0223\u0224\3\2\2\2\u0224\u0226\3\2\2\2\u0225"+
		"\u0223\3\2\2\2\u0226\u0227\7D\2\2\u0227Y\3\2\2\2\u0228\u022a\5\\/\2\u0229"+
		"\u0228\3\2\2\2\u022a\u022d\3\2\2\2\u022b\u0229\3\2\2\2\u022b\u022c\3\2"+
		"\2\2\u022c\u022e\3\2\2\2\u022d\u022b\3\2\2\2\u022e\u022f\7D\2\2\u022f"+
		"[\3\2\2\2\u0230\u0231\7D\2\2\u0231\u0232\7&\2\2\u0232]\3\2\2\2\u0233\u0234"+
		"\t\7\2\2\u0234_\3\2\2\2[cglrw}\u0080\u0088\u008d\u0093\u0098\u009b\u009f"+
		"\u00a2\u00a8\u00ad\u00b1\u00b3\u00b7\u00c3\u00cb\u00ce\u00d6\u00db\u00e0"+
		"\u00e5\u00e8\u00ed\u00f0\u00f5\u00f8\u00fd\u0102\u0107\u010b\u0111\u011e"+
		"\u0123\u0127\u012c\u0131\u0136\u013a\u013d\u0142\u0145\u0148\u0156\u0159"+
		"\u0161\u016a\u0170\u0174\u0179\u017d\u0184\u0187\u018b\u0192\u0195\u0199"+
		"\u019c\u01a3\u01a6\u01a9\u01ad\u01b0\u01b7\u01ba\u01bd\u01c1\u01c4\u01cb"+
		"\u01ce\u01d1\u01d5\u01dc\u01df\u01e3\u01ea\u01ed\u01ef\u01f3\u01fa\u01fd"+
		"\u01ff\u021a\u0223\u022b";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}