// Generated from /Users/octavio/workspace/tara/rt/src/siani/tara/compiler/parser/antlr/TaraGrammar.g4 by ANTLR 4.x

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
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		RIGHT_SQUARE=18, STAR=31, NEGATIVE_VALUE=44, DOC=53, LETTER=50, CASE=2, 
		CLOSE_AN=24, DEDENT=57, BOOLEAN_VALUE=42, METAIDENTIFIER=1, RESOURCE=13, 
		MULTIPLE=4, UNKNOWN_TOKEN=58, WORD=12, OPEN_BRACKET=21, BOX=15, IMPORT=14, 
		RIGHT_PARENTHESIS=20, DASHES=33, COMMA=26, LEFT_PARENTHESIS=19, IDENTIFIER=48, 
		STRING_MULTILINE_VALUE_KEY=47, VAR=10, NL=55, DIGIT=49, INTENTION=8, DOT=27, 
		STRING_VALUE=46, DOUBLE_VALUE=45, POSITIVE=32, NEW_LINE_INDENT=56, DASH=34, 
		PRIVATE=3, TERMINAL=7, NATURAL_TYPE=37, HAS_NAME=9, DOUBLE_TYPE=38, BOOLEAN_TYPE=40, 
		SEMICOLON=30, REQUIRED=5, INT_TYPE=36, ROOT=6, OPEN_AN=23, NAMESPACE=16, 
		EMPTY=41, COLON=25, ALIAS_TYPE=35, POSITIVE_VALUE=43, NEWLINE=51, PROPERTY=11, 
		SPACES=52, SP=54, APHOSTROPHE=29, STRING_TYPE=39, ASSIGN=28, LEFT_SQUARE=17, 
		CLOSE_BRACKET=22;
	public static final String[] tokenNames = {
		"<INVALID>", "'Concept'", "'case'", "'private'", "'multiple'", "'required'", 
		"'root'", "'terminal'", "'intention'", "'has-name'", "'var'", "'property'", 
		"'Word'", "'Resource'", "'import'", "'box'", "'namespace'", "'['", "']'", 
		"'('", "')'", "'{'", "'}'", "'<'", "'>'", "':'", "','", "'.'", "'='", 
		"'''", "SEMICOLON", "'*'", "'+'", "DASHES", "'-'", "'Alias'", "'Integer'", 
		"'Natural'", "'Double'", "'String'", "'Boolean'", "'empty'", "BOOLEAN_VALUE", 
		"POSITIVE_VALUE", "NEGATIVE_VALUE", "DOUBLE_VALUE", "STRING_VALUE", "STRING_MULTILINE_VALUE_KEY", 
		"IDENTIFIER", "DIGIT", "LETTER", "NEWLINE", "SPACES", "DOC", "SP", "NL", 
		"'indent'", "'dedent'", "UNKNOWN_TOKEN"
	};
	public static final int
		RULE_root = 0, RULE_header = 1, RULE_imports = 2, RULE_box = 3, RULE_concept = 4, 
		RULE_signature = 5, RULE_metaidentifier = 6, RULE_parameters = 7, RULE_parameterList = 8, 
		RULE_explicit = 9, RULE_parameter = 10, RULE_body = 11, RULE_conceptConstituents = 12, 
		RULE_identifierList = 13, RULE_varInit = 14, RULE_attribute = 15, RULE_aliasAttribute = 16, 
		RULE_booleanAttribute = 17, RULE_stringAttribute = 18, RULE_naturalAttribute = 19, 
		RULE_integerAttribute = 20, RULE_doubleAttribute = 21, RULE_resource = 22, 
		RULE_reference = 23, RULE_word = 24, RULE_naturalValue = 25, RULE_integerValue = 26, 
		RULE_doubleValue = 27, RULE_booleanValue = 28, RULE_stringValue = 29, 
		RULE_stringList = 30, RULE_booleanList = 31, RULE_naturalList = 32, RULE_integerList = 33, 
		RULE_doubleList = 34, RULE_annotations = 35, RULE_headerReference = 36, 
		RULE_identifierReference = 37, RULE_hierarchy = 38, RULE_doc = 39;
	public static final String[] ruleNames = {
		"root", "header", "imports", "box", "concept", "signature", "metaidentifier", 
		"parameters", "parameterList", "explicit", "parameter", "body", "conceptConstituents", 
		"identifierList", "varInit", "attribute", "aliasAttribute", "booleanAttribute", 
		"stringAttribute", "naturalAttribute", "integerAttribute", "doubleAttribute", 
		"resource", "reference", "word", "naturalValue", "integerValue", "doubleValue", 
		"booleanValue", "stringValue", "stringList", "booleanList", "naturalList", 
		"integerList", "doubleList", "annotations", "headerReference", "identifierReference", 
		"hierarchy", "doc"
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
		public List<TerminalNode> NEWLINE() { return getTokens(TaraGrammar.NEWLINE); }
		public ConceptContext concept() {
			return getRuleContext(ConceptContext.class,0);
		}
		public TerminalNode EOF() { return getToken(TaraGrammar.EOF, 0); }
		public TerminalNode NEWLINE(int i) {
			return getToken(TaraGrammar.NEWLINE, i);
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
			setState(83);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(80); match(NEWLINE);
					}
					} 
				}
				setState(85);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			}
			setState(87);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				{
				setState(86); header();
				}
				break;
			}
			setState(92);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(89); match(NEWLINE);
				}
				}
				setState(94);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(95); concept();
			setState(99);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(96); match(NEWLINE);
				}
				}
				setState(101);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(102); match(EOF);
			}
		}
		catch (RecognitionException re) {
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
		public List<ImportsContext> imports() {
			return getRuleContexts(ImportsContext.class);
		}
		public BoxContext box() {
			return getRuleContext(BoxContext.class,0);
		}
		public ImportsContext imports(int i) {
			return getRuleContext(ImportsContext.class,i);
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
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(105);
			_la = _input.LA(1);
			if (_la==BOX) {
				{
				setState(104); box();
				}
			}

			setState(110);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(107); imports();
					}
					} 
				}
				setState(112);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
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
		public List<TerminalNode> NEWLINE() { return getTokens(TaraGrammar.NEWLINE); }
		public HeaderReferenceContext headerReference() {
			return getRuleContext(HeaderReferenceContext.class,0);
		}
		public TerminalNode NEWLINE(int i) {
			return getToken(TaraGrammar.NEWLINE, i);
		}
		public TerminalNode IMPORT() { return getToken(TaraGrammar.IMPORT, 0); }
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
			setState(114); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(113); match(NEWLINE);
				}
				}
				setState(116); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NEWLINE );
			setState(118); match(IMPORT);
			setState(119); headerReference();
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 6, RULE_box);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(121); match(BOX);
			setState(122); headerReference();
			}
		}
		catch (RecognitionException re) {
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
			setState(125);
			_la = _input.LA(1);
			if (_la==DOC) {
				{
				setState(124); doc();
				}
			}

			setState(127); signature();
			setState(129);
			_la = _input.LA(1);
			if (_la==OPEN_AN) {
				{
				setState(128); annotations();
				}
			}

			setState(132);
			_la = _input.LA(1);
			if (_la==NEW_LINE_INDENT) {
				{
				setState(131); body();
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
		public TerminalNode CASE() { return getToken(TaraGrammar.CASE, 0); }
		public ParametersContext parameters() {
			return getRuleContext(ParametersContext.class,0);
		}
		public TerminalNode COLON() { return getToken(TaraGrammar.COLON, 0); }
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
			setState(145);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				{
				{
				setState(134); match(CASE);
				setState(135); match(IDENTIFIER);
				}
				}
				break;
			case 2:
				{
				{
				setState(136); metaidentifier();
				setState(137); match(IDENTIFIER);
				}
				}
				break;
			case 3:
				{
				{
				setState(139); metaidentifier();
				setState(140); match(COLON);
				setState(141); identifierReference();
				setState(143);
				_la = _input.LA(1);
				if (_la==IDENTIFIER) {
					{
					setState(142); match(IDENTIFIER);
					}
				}

				}
				}
				break;
			}
			setState(148);
			_la = _input.LA(1);
			if (_la==LEFT_PARENTHESIS) {
				{
				setState(147); parameters();
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
		enterRule(_localctx, 12, RULE_metaidentifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(150);
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
			setState(152); match(LEFT_PARENTHESIS);
			setState(154);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LEFT_SQUARE) | (1L << BOOLEAN_VALUE) | (1L << POSITIVE_VALUE) | (1L << NEGATIVE_VALUE) | (1L << DOUBLE_VALUE) | (1L << STRING_VALUE) | (1L << STRING_MULTILINE_VALUE_KEY) | (1L << IDENTIFIER))) != 0)) {
				{
				setState(153); parameterList();
				}
			}

			setState(156); match(RIGHT_PARENTHESIS);
			}
		}
		catch (RecognitionException re) {
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
		public List<ParameterContext> parameter() {
			return getRuleContexts(ParameterContext.class);
		}
		public ParameterContext parameter(int i) {
			return getRuleContext(ParameterContext.class,i);
		}
		public TerminalNode COMMA(int i) {
			return getToken(TaraGrammar.COMMA, i);
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
			enterOuterAlt(_localctx, 1);
			{
			setState(159);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				{
				setState(158); explicit();
				}
				break;
			}
			setState(161); parameter();
			setState(169);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(162); match(COMMA);
				setState(164);
				switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
				case 1:
					{
					setState(163); explicit();
					}
					break;
				}
				setState(166); parameter();
				}
				}
				setState(171);
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

	public static class ExplicitContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
		public TerminalNode COLON() { return getToken(TaraGrammar.COLON, 0); }
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
			setState(172); match(IDENTIFIER);
			setState(173); match(COLON);
			}
		}
		catch (RecognitionException re) {
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
		public BooleanValueContext booleanValue() {
			return getRuleContext(BooleanValueContext.class,0);
		}
		public IdentifierReferenceContext identifierReference() {
			return getRuleContext(IdentifierReferenceContext.class,0);
		}
		public IntegerListContext integerList() {
			return getRuleContext(IntegerListContext.class,0);
		}
		public NaturalValueContext naturalValue() {
			return getRuleContext(NaturalValueContext.class,0);
		}
		public DoubleListContext doubleList() {
			return getRuleContext(DoubleListContext.class,0);
		}
		public NaturalListContext naturalList() {
			return getRuleContext(NaturalListContext.class,0);
		}
		public StringListContext stringList() {
			return getRuleContext(StringListContext.class,0);
		}
		public DoubleValueContext doubleValue() {
			return getRuleContext(DoubleValueContext.class,0);
		}
		public IdentifierListContext identifierList() {
			return getRuleContext(IdentifierListContext.class,0);
		}
		public StringValueContext stringValue() {
			return getRuleContext(StringValueContext.class,0);
		}
		public BooleanListContext booleanList() {
			return getRuleContext(BooleanListContext.class,0);
		}
		public IntegerValueContext integerValue() {
			return getRuleContext(IntegerValueContext.class,0);
		}
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
		enterRule(_localctx, 20, RULE_parameter);
		try {
			setState(187);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(175); identifierReference();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(176); stringValue();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(177); booleanValue();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(178); naturalValue();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(179); integerValue();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(180); doubleValue();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(181); stringList();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(182); booleanList();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(183); naturalList();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(184); integerList();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(185); doubleList();
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(186); identifierList();
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
		public List<TerminalNode> NEWLINE() { return getTokens(TaraGrammar.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(TaraGrammar.NEWLINE, i);
		}
		public List<ConceptConstituentsContext> conceptConstituents() {
			return getRuleContexts(ConceptConstituentsContext.class);
		}
		public TerminalNode DEDENT() { return getToken(TaraGrammar.DEDENT, 0); }
		public TerminalNode NEW_LINE_INDENT() { return getToken(TaraGrammar.NEW_LINE_INDENT, 0); }
		public ConceptConstituentsContext conceptConstituents(int i) {
			return getRuleContext(ConceptConstituentsContext.class,i);
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
		enterRule(_localctx, 22, RULE_body);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(189); match(NEW_LINE_INDENT);
			setState(196); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(190); conceptConstituents();
				setState(192); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(191); match(NEWLINE);
					}
					}
					setState(194); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NEWLINE );
				}
				}
				setState(198); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << METAIDENTIFIER) | (1L << CASE) | (1L << VAR) | (1L << IDENTIFIER) | (1L << DOC))) != 0) );
			setState(200); match(DEDENT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConceptConstituentsContext extends ParserRuleContext {
		public ConceptContext concept() {
			return getRuleContext(ConceptContext.class,0);
		}
		public AttributeContext attribute() {
			return getRuleContext(AttributeContext.class,0);
		}
		public VarInitContext varInit() {
			return getRuleContext(VarInitContext.class,0);
		}
		public ConceptConstituentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conceptConstituents; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterConceptConstituents(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitConceptConstituents(this);
		}
	}

	public final ConceptConstituentsContext conceptConstituents() throws RecognitionException {
		ConceptConstituentsContext _localctx = new ConceptConstituentsContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_conceptConstituents);
		try {
			setState(205);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(202); attribute();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(203); concept();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(204); varInit();
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

	public static class IdentifierListContext extends ParserRuleContext {
		public TerminalNode LEFT_SQUARE() { return getToken(TaraGrammar.LEFT_SQUARE, 0); }
		public TerminalNode RIGHT_SQUARE() { return getToken(TaraGrammar.RIGHT_SQUARE, 0); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(TaraGrammar.IDENTIFIER, i);
		}
		public List<TerminalNode> IDENTIFIER() { return getTokens(TaraGrammar.IDENTIFIER); }
		public IdentifierListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifierList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterIdentifierList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitIdentifierList(this);
		}
	}

	public final IdentifierListContext identifierList() throws RecognitionException {
		IdentifierListContext _localctx = new IdentifierListContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_identifierList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(207); match(LEFT_SQUARE);
			setState(209); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(208); match(IDENTIFIER);
				}
				}
				setState(211); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==IDENTIFIER );
			setState(213); match(RIGHT_SQUARE);
			}
		}
		catch (RecognitionException re) {
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
		public BooleanValueContext booleanValue() {
			return getRuleContext(BooleanValueContext.class,0);
		}
		public IntegerListContext integerList() {
			return getRuleContext(IntegerListContext.class,0);
		}
		public DoubleValueContext doubleValue() {
			return getRuleContext(DoubleValueContext.class,0);
		}
		public StringValueContext stringValue() {
			return getRuleContext(StringValueContext.class,0);
		}
		public IdentifierListContext identifierList() {
			return getRuleContext(IdentifierListContext.class,0);
		}
		public BooleanListContext booleanList() {
			return getRuleContext(BooleanListContext.class,0);
		}
		public TerminalNode EMPTY() { return getToken(TaraGrammar.EMPTY, 0); }
		public IdentifierReferenceContext identifierReference() {
			return getRuleContext(IdentifierReferenceContext.class,0);
		}
		public NaturalValueContext naturalValue() {
			return getRuleContext(NaturalValueContext.class,0);
		}
		public DoubleListContext doubleList() {
			return getRuleContext(DoubleListContext.class,0);
		}
		public NaturalListContext naturalList() {
			return getRuleContext(NaturalListContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
		public StringListContext stringList() {
			return getRuleContext(StringListContext.class,0);
		}
		public TerminalNode COLON() { return getToken(TaraGrammar.COLON, 0); }
		public IntegerValueContext integerValue() {
			return getRuleContext(IntegerValueContext.class,0);
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
		enterRule(_localctx, 28, RULE_varInit);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(215); match(IDENTIFIER);
			setState(216); match(COLON);
			setState(230);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				{
				setState(217); match(EMPTY);
				}
				break;
			case 2:
				{
				setState(218); identifierReference();
				}
				break;
			case 3:
				{
				setState(219); stringValue();
				}
				break;
			case 4:
				{
				setState(220); booleanValue();
				}
				break;
			case 5:
				{
				setState(221); naturalValue();
				}
				break;
			case 6:
				{
				setState(222); integerValue();
				}
				break;
			case 7:
				{
				setState(223); doubleValue();
				}
				break;
			case 8:
				{
				setState(224); identifierList();
				}
				break;
			case 9:
				{
				setState(225); stringList();
				}
				break;
			case 10:
				{
				setState(226); booleanList();
				}
				break;
			case 11:
				{
				setState(227); naturalList();
				}
				break;
			case 12:
				{
				setState(228); integerList();
				}
				break;
			case 13:
				{
				setState(229); doubleList();
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

	public static class AttributeContext extends ParserRuleContext {
		public AliasAttributeContext aliasAttribute() {
			return getRuleContext(AliasAttributeContext.class,0);
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
		public IntegerAttributeContext integerAttribute() {
			return getRuleContext(IntegerAttributeContext.class,0);
		}
		public AnnotationsContext annotations() {
			return getRuleContext(AnnotationsContext.class,0);
		}
		public NaturalAttributeContext naturalAttribute() {
			return getRuleContext(NaturalAttributeContext.class,0);
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
		enterRule(_localctx, 30, RULE_attribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(241);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				{
				setState(232); aliasAttribute();
				}
				break;
			case 2:
				{
				setState(233); naturalAttribute();
				}
				break;
			case 3:
				{
				setState(234); integerAttribute();
				}
				break;
			case 4:
				{
				setState(235); doubleAttribute();
				}
				break;
			case 5:
				{
				setState(236); booleanAttribute();
				}
				break;
			case 6:
				{
				setState(237); stringAttribute();
				}
				break;
			case 7:
				{
				setState(238); resource();
				}
				break;
			case 8:
				{
				setState(239); reference();
				}
				break;
			case 9:
				{
				setState(240); word();
				}
				break;
			}
			setState(244);
			_la = _input.LA(1);
			if (_la==OPEN_AN) {
				{
				setState(243); annotations();
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

	public static class AliasAttributeContext extends ParserRuleContext {
		public TerminalNode EMPTY() { return getToken(TaraGrammar.EMPTY, 0); }
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
		public TerminalNode ALIAS_TYPE() { return getToken(TaraGrammar.ALIAS_TYPE, 0); }
		public TerminalNode VAR() { return getToken(TaraGrammar.VAR, 0); }
		public StringValueContext stringValue() {
			return getRuleContext(StringValueContext.class,0);
		}
		public DocContext doc() {
			return getRuleContext(DocContext.class,0);
		}
		public TerminalNode COLON() { return getToken(TaraGrammar.COLON, 0); }
		public AliasAttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aliasAttribute; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterAliasAttribute(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitAliasAttribute(this);
		}
	}

	public final AliasAttributeContext aliasAttribute() throws RecognitionException {
		AliasAttributeContext _localctx = new AliasAttributeContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_aliasAttribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(247);
			_la = _input.LA(1);
			if (_la==DOC) {
				{
				setState(246); doc();
				}
			}

			setState(249); match(VAR);
			setState(250); match(ALIAS_TYPE);
			setState(251); match(IDENTIFIER);
			setState(255);
			switch (_input.LA(1)) {
			case COLON:
				{
				setState(252); match(COLON);
				setState(253); stringValue();
				}
				break;
			case EMPTY:
				{
				setState(254); match(EMPTY);
				}
				break;
			case OPEN_AN:
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

	public static class BooleanAttributeContext extends ParserRuleContext {
		public TerminalNode EMPTY() { return getToken(TaraGrammar.EMPTY, 0); }
		public BooleanValueContext booleanValue() {
			return getRuleContext(BooleanValueContext.class,0);
		}
		public TerminalNode BOOLEAN_TYPE() { return getToken(TaraGrammar.BOOLEAN_TYPE, 0); }
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
		public TerminalNode VAR() { return getToken(TaraGrammar.VAR, 0); }
		public DocContext doc() {
			return getRuleContext(DocContext.class,0);
		}
		public TerminalNode COLON() { return getToken(TaraGrammar.COLON, 0); }
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
		enterRule(_localctx, 34, RULE_booleanAttribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(258);
			_la = _input.LA(1);
			if (_la==DOC) {
				{
				setState(257); doc();
				}
			}

			setState(260); match(VAR);
			setState(261); match(BOOLEAN_TYPE);
			{
			setState(262); match(IDENTIFIER);
			setState(266);
			switch (_input.LA(1)) {
			case COLON:
				{
				setState(263); match(COLON);
				setState(264); booleanValue();
				}
				break;
			case EMPTY:
				{
				setState(265); match(EMPTY);
				}
				break;
			case OPEN_AN:
			case NEWLINE:
				break;
			default:
				throw new NoViableAltException(this);
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
		public TerminalNode EMPTY() { return getToken(TaraGrammar.EMPTY, 0); }
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
		public TerminalNode STRING_TYPE() { return getToken(TaraGrammar.STRING_TYPE, 0); }
		public TerminalNode VAR() { return getToken(TaraGrammar.VAR, 0); }
		public StringValueContext stringValue() {
			return getRuleContext(StringValueContext.class,0);
		}
		public DocContext doc() {
			return getRuleContext(DocContext.class,0);
		}
		public TerminalNode COLON() { return getToken(TaraGrammar.COLON, 0); }
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
		enterRule(_localctx, 36, RULE_stringAttribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(269);
			_la = _input.LA(1);
			if (_la==DOC) {
				{
				setState(268); doc();
				}
			}

			setState(271); match(VAR);
			setState(272); match(STRING_TYPE);
			{
			setState(273); match(IDENTIFIER);
			setState(277);
			switch (_input.LA(1)) {
			case COLON:
				{
				setState(274); match(COLON);
				setState(275); stringValue();
				}
				break;
			case EMPTY:
				{
				setState(276); match(EMPTY);
				}
				break;
			case OPEN_AN:
			case NEWLINE:
				break;
			default:
				throw new NoViableAltException(this);
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
		public TerminalNode EMPTY() { return getToken(TaraGrammar.EMPTY, 0); }
		public TerminalNode NATURAL_TYPE() { return getToken(TaraGrammar.NATURAL_TYPE, 0); }
		public NaturalValueContext naturalValue() {
			return getRuleContext(NaturalValueContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
		public TerminalNode VAR() { return getToken(TaraGrammar.VAR, 0); }
		public DocContext doc() {
			return getRuleContext(DocContext.class,0);
		}
		public TerminalNode COLON() { return getToken(TaraGrammar.COLON, 0); }
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
		enterRule(_localctx, 38, RULE_naturalAttribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(280);
			_la = _input.LA(1);
			if (_la==DOC) {
				{
				setState(279); doc();
				}
			}

			setState(282); match(VAR);
			setState(283); match(NATURAL_TYPE);
			{
			setState(284); match(IDENTIFIER);
			setState(288);
			switch (_input.LA(1)) {
			case COLON:
				{
				setState(285); match(COLON);
				setState(286); naturalValue();
				}
				break;
			case EMPTY:
				{
				setState(287); match(EMPTY);
				}
				break;
			case OPEN_AN:
			case NEWLINE:
				break;
			default:
				throw new NoViableAltException(this);
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
		public TerminalNode EMPTY() { return getToken(TaraGrammar.EMPTY, 0); }
		public TerminalNode INT_TYPE() { return getToken(TaraGrammar.INT_TYPE, 0); }
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
		public TerminalNode VAR() { return getToken(TaraGrammar.VAR, 0); }
		public DocContext doc() {
			return getRuleContext(DocContext.class,0);
		}
		public TerminalNode COLON() { return getToken(TaraGrammar.COLON, 0); }
		public IntegerValueContext integerValue() {
			return getRuleContext(IntegerValueContext.class,0);
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
		enterRule(_localctx, 40, RULE_integerAttribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(291);
			_la = _input.LA(1);
			if (_la==DOC) {
				{
				setState(290); doc();
				}
			}

			setState(293); match(VAR);
			setState(294); match(INT_TYPE);
			{
			setState(295); match(IDENTIFIER);
			setState(299);
			switch (_input.LA(1)) {
			case COLON:
				{
				setState(296); match(COLON);
				setState(297); integerValue();
				}
				break;
			case EMPTY:
				{
				setState(298); match(EMPTY);
				}
				break;
			case OPEN_AN:
			case NEWLINE:
				break;
			default:
				throw new NoViableAltException(this);
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
		public TerminalNode EMPTY() { return getToken(TaraGrammar.EMPTY, 0); }
		public TerminalNode DOUBLE_TYPE() { return getToken(TaraGrammar.DOUBLE_TYPE, 0); }
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
		public TerminalNode VAR() { return getToken(TaraGrammar.VAR, 0); }
		public DoubleValueContext doubleValue() {
			return getRuleContext(DoubleValueContext.class,0);
		}
		public DocContext doc() {
			return getRuleContext(DocContext.class,0);
		}
		public TerminalNode COLON() { return getToken(TaraGrammar.COLON, 0); }
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
		enterRule(_localctx, 42, RULE_doubleAttribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(302);
			_la = _input.LA(1);
			if (_la==DOC) {
				{
				setState(301); doc();
				}
			}

			setState(304); match(VAR);
			setState(305); match(DOUBLE_TYPE);
			{
			setState(306); match(IDENTIFIER);
			setState(310);
			switch (_input.LA(1)) {
			case COLON:
				{
				setState(307); match(COLON);
				setState(308); doubleValue();
				}
				break;
			case EMPTY:
				{
				setState(309); match(EMPTY);
				}
				break;
			case OPEN_AN:
			case NEWLINE:
				break;
			default:
				throw new NoViableAltException(this);
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
		public TerminalNode RESOURCE() { return getToken(TaraGrammar.RESOURCE, 0); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(TaraGrammar.IDENTIFIER, i);
		}
		public List<TerminalNode> IDENTIFIER() { return getTokens(TaraGrammar.IDENTIFIER); }
		public TerminalNode VAR() { return getToken(TaraGrammar.VAR, 0); }
		public DocContext doc() {
			return getRuleContext(DocContext.class,0);
		}
		public TerminalNode COLON() { return getToken(TaraGrammar.COLON, 0); }
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
		enterRule(_localctx, 44, RULE_resource);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(313);
			_la = _input.LA(1);
			if (_la==DOC) {
				{
				setState(312); doc();
				}
			}

			setState(315); match(VAR);
			setState(316); match(RESOURCE);
			setState(317); match(COLON);
			setState(318); match(IDENTIFIER);
			setState(319); match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
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
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
		public TerminalNode VAR() { return getToken(TaraGrammar.VAR, 0); }
		public DocContext doc() {
			return getRuleContext(DocContext.class,0);
		}
		public TerminalNode COLON() { return getToken(TaraGrammar.COLON, 0); }
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
			setState(322);
			_la = _input.LA(1);
			if (_la==DOC) {
				{
				setState(321); doc();
				}
			}

			setState(324); match(VAR);
			setState(325); identifierReference();
			setState(326); match(IDENTIFIER);
			setState(329);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(327); match(COLON);
				setState(328); match(EMPTY);
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
		public TerminalNode IDENTIFIER(int i) {
			return getToken(TaraGrammar.IDENTIFIER, i);
		}
		public List<TerminalNode> IDENTIFIER() { return getTokens(TaraGrammar.IDENTIFIER); }
		public TerminalNode VAR() { return getToken(TaraGrammar.VAR, 0); }
		public List<TerminalNode> STAR() { return getTokens(TaraGrammar.STAR); }
		public TerminalNode DEDENT() { return getToken(TaraGrammar.DEDENT, 0); }
		public DocContext doc() {
			return getRuleContext(DocContext.class,0);
		}
		public TerminalNode STAR(int i) {
			return getToken(TaraGrammar.STAR, i);
		}
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
		enterRule(_localctx, 48, RULE_word);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(332);
			_la = _input.LA(1);
			if (_la==DOC) {
				{
				setState(331); doc();
				}
			}

			setState(334); match(VAR);
			setState(335); match(WORD);
			setState(336); match(IDENTIFIER);
			setState(337); match(NEW_LINE_INDENT);
			setState(343); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(338); match(IDENTIFIER);
				setState(340);
				_la = _input.LA(1);
				if (_la==STAR) {
					{
					setState(339); match(STAR);
					}
				}

				setState(342); match(NEWLINE);
				}
				}
				setState(345); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==IDENTIFIER );
			setState(347); match(DEDENT);
			}
		}
		catch (RecognitionException re) {
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
		public TerminalNode POSITIVE_VALUE() { return getToken(TaraGrammar.POSITIVE_VALUE, 0); }
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
		enterRule(_localctx, 50, RULE_naturalValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(349); match(POSITIVE_VALUE);
			}
		}
		catch (RecognitionException re) {
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
		public TerminalNode POSITIVE_VALUE() { return getToken(TaraGrammar.POSITIVE_VALUE, 0); }
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
		enterRule(_localctx, 52, RULE_integerValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(351);
			_la = _input.LA(1);
			if ( !(_la==POSITIVE_VALUE || _la==NEGATIVE_VALUE) ) {
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
		public TerminalNode POSITIVE_VALUE() { return getToken(TaraGrammar.POSITIVE_VALUE, 0); }
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
		enterRule(_localctx, 54, RULE_doubleValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(353);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << POSITIVE_VALUE) | (1L << NEGATIVE_VALUE) | (1L << DOUBLE_VALUE))) != 0)) ) {
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
		enterRule(_localctx, 56, RULE_booleanValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(355); match(BOOLEAN_VALUE);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 58, RULE_stringValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(357);
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

	public static class StringListContext extends ParserRuleContext {
		public TerminalNode STRING_VALUE(int i) {
			return getToken(TaraGrammar.STRING_VALUE, i);
		}
		public TerminalNode LEFT_SQUARE() { return getToken(TaraGrammar.LEFT_SQUARE, 0); }
		public List<TerminalNode> STRING_VALUE() { return getTokens(TaraGrammar.STRING_VALUE); }
		public TerminalNode RIGHT_SQUARE() { return getToken(TaraGrammar.RIGHT_SQUARE, 0); }
		public StringListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stringList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterStringList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitStringList(this);
		}
	}

	public final StringListContext stringList() throws RecognitionException {
		StringListContext _localctx = new StringListContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_stringList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(359); match(LEFT_SQUARE);
			setState(361); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(360); match(STRING_VALUE);
				}
				}
				setState(363); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==STRING_VALUE );
			setState(365); match(RIGHT_SQUARE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BooleanListContext extends ParserRuleContext {
		public TerminalNode LEFT_SQUARE() { return getToken(TaraGrammar.LEFT_SQUARE, 0); }
		public List<TerminalNode> BOOLEAN_VALUE() { return getTokens(TaraGrammar.BOOLEAN_VALUE); }
		public TerminalNode RIGHT_SQUARE() { return getToken(TaraGrammar.RIGHT_SQUARE, 0); }
		public TerminalNode BOOLEAN_VALUE(int i) {
			return getToken(TaraGrammar.BOOLEAN_VALUE, i);
		}
		public BooleanListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_booleanList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterBooleanList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitBooleanList(this);
		}
	}

	public final BooleanListContext booleanList() throws RecognitionException {
		BooleanListContext _localctx = new BooleanListContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_booleanList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(367); match(LEFT_SQUARE);
			setState(369); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(368); match(BOOLEAN_VALUE);
				}
				}
				setState(371); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==BOOLEAN_VALUE );
			setState(373); match(RIGHT_SQUARE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NaturalListContext extends ParserRuleContext {
		public TerminalNode LEFT_SQUARE() { return getToken(TaraGrammar.LEFT_SQUARE, 0); }
		public TerminalNode RIGHT_SQUARE() { return getToken(TaraGrammar.RIGHT_SQUARE, 0); }
		public List<TerminalNode> POSITIVE_VALUE() { return getTokens(TaraGrammar.POSITIVE_VALUE); }
		public TerminalNode POSITIVE_VALUE(int i) {
			return getToken(TaraGrammar.POSITIVE_VALUE, i);
		}
		public NaturalListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_naturalList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterNaturalList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitNaturalList(this);
		}
	}

	public final NaturalListContext naturalList() throws RecognitionException {
		NaturalListContext _localctx = new NaturalListContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_naturalList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(375); match(LEFT_SQUARE);
			setState(377); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(376); match(POSITIVE_VALUE);
				}
				}
				setState(379); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==POSITIVE_VALUE );
			setState(381); match(RIGHT_SQUARE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IntegerListContext extends ParserRuleContext {
		public TerminalNode LEFT_SQUARE() { return getToken(TaraGrammar.LEFT_SQUARE, 0); }
		public TerminalNode RIGHT_SQUARE() { return getToken(TaraGrammar.RIGHT_SQUARE, 0); }
		public List<TerminalNode> POSITIVE_VALUE() { return getTokens(TaraGrammar.POSITIVE_VALUE); }
		public TerminalNode NEGATIVE_VALUE(int i) {
			return getToken(TaraGrammar.NEGATIVE_VALUE, i);
		}
		public List<TerminalNode> NEGATIVE_VALUE() { return getTokens(TaraGrammar.NEGATIVE_VALUE); }
		public TerminalNode POSITIVE_VALUE(int i) {
			return getToken(TaraGrammar.POSITIVE_VALUE, i);
		}
		public IntegerListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_integerList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterIntegerList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitIntegerList(this);
		}
	}

	public final IntegerListContext integerList() throws RecognitionException {
		IntegerListContext _localctx = new IntegerListContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_integerList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(383); match(LEFT_SQUARE);
			setState(385); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(384);
				_la = _input.LA(1);
				if ( !(_la==POSITIVE_VALUE || _la==NEGATIVE_VALUE) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				}
				}
				setState(387); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==POSITIVE_VALUE || _la==NEGATIVE_VALUE );
			setState(389); match(RIGHT_SQUARE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DoubleListContext extends ParserRuleContext {
		public List<TerminalNode> DOUBLE_VALUE() { return getTokens(TaraGrammar.DOUBLE_VALUE); }
		public TerminalNode LEFT_SQUARE() { return getToken(TaraGrammar.LEFT_SQUARE, 0); }
		public TerminalNode RIGHT_SQUARE() { return getToken(TaraGrammar.RIGHT_SQUARE, 0); }
		public TerminalNode DOUBLE_VALUE(int i) {
			return getToken(TaraGrammar.DOUBLE_VALUE, i);
		}
		public List<TerminalNode> POSITIVE_VALUE() { return getTokens(TaraGrammar.POSITIVE_VALUE); }
		public TerminalNode NEGATIVE_VALUE(int i) {
			return getToken(TaraGrammar.NEGATIVE_VALUE, i);
		}
		public List<TerminalNode> NEGATIVE_VALUE() { return getTokens(TaraGrammar.NEGATIVE_VALUE); }
		public TerminalNode POSITIVE_VALUE(int i) {
			return getToken(TaraGrammar.POSITIVE_VALUE, i);
		}
		public DoubleListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_doubleList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterDoubleList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitDoubleList(this);
		}
	}

	public final DoubleListContext doubleList() throws RecognitionException {
		DoubleListContext _localctx = new DoubleListContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_doubleList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(391); match(LEFT_SQUARE);
			setState(393); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(392);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << POSITIVE_VALUE) | (1L << NEGATIVE_VALUE) | (1L << DOUBLE_VALUE))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				}
				}
				setState(395); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << POSITIVE_VALUE) | (1L << NEGATIVE_VALUE) | (1L << DOUBLE_VALUE))) != 0) );
			setState(397); match(RIGHT_SQUARE);
			}
		}
		catch (RecognitionException re) {
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
		public TerminalNode OPEN_AN() { return getToken(TaraGrammar.OPEN_AN, 0); }
		public List<TerminalNode> ROOT() { return getTokens(TaraGrammar.ROOT); }
		public TerminalNode PROPERTY(int i) {
			return getToken(TaraGrammar.PROPERTY, i);
		}
		public List<TerminalNode> PRIVATE() { return getTokens(TaraGrammar.PRIVATE); }
		public TerminalNode ROOT(int i) {
			return getToken(TaraGrammar.ROOT, i);
		}
		public List<TerminalNode> HAS_NAME() { return getTokens(TaraGrammar.HAS_NAME); }
		public TerminalNode TERMINAL(int i) {
			return getToken(TaraGrammar.TERMINAL, i);
		}
		public List<TerminalNode> TERMINAL() { return getTokens(TaraGrammar.TERMINAL); }
		public List<TerminalNode> REQUIRED() { return getTokens(TaraGrammar.REQUIRED); }
		public TerminalNode CLOSE_AN() { return getToken(TaraGrammar.CLOSE_AN, 0); }
		public TerminalNode MULTIPLE(int i) {
			return getToken(TaraGrammar.MULTIPLE, i);
		}
		public List<TerminalNode> PROPERTY() { return getTokens(TaraGrammar.PROPERTY); }
		public TerminalNode INTENTION(int i) {
			return getToken(TaraGrammar.INTENTION, i);
		}
		public List<TerminalNode> MULTIPLE() { return getTokens(TaraGrammar.MULTIPLE); }
		public TerminalNode REQUIRED(int i) {
			return getToken(TaraGrammar.REQUIRED, i);
		}
		public List<TerminalNode> INTENTION() { return getTokens(TaraGrammar.INTENTION); }
		public TerminalNode PRIVATE(int i) {
			return getToken(TaraGrammar.PRIVATE, i);
		}
		public TerminalNode HAS_NAME(int i) {
			return getToken(TaraGrammar.HAS_NAME, i);
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
		enterRule(_localctx, 70, RULE_annotations);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(399); match(OPEN_AN);
			setState(401); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(400);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PRIVATE) | (1L << MULTIPLE) | (1L << REQUIRED) | (1L << ROOT) | (1L << TERMINAL) | (1L << INTENTION) | (1L << HAS_NAME) | (1L << PROPERTY))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				}
				}
				setState(403); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PRIVATE) | (1L << MULTIPLE) | (1L << REQUIRED) | (1L << ROOT) | (1L << TERMINAL) | (1L << INTENTION) | (1L << HAS_NAME) | (1L << PROPERTY))) != 0) );
			setState(405); match(CLOSE_AN);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 72, RULE_headerReference);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(410);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,49,_ctx);
			while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(407); hierarchy();
					}
					} 
				}
				setState(412);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,49,_ctx);
			}
			setState(413); match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 74, RULE_identifierReference);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(418);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,50,_ctx);
			while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(415); hierarchy();
					}
					} 
				}
				setState(420);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,50,_ctx);
			}
			setState(421); match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 76, RULE_hierarchy);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(423); match(IDENTIFIER);
			setState(424); match(DOT);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 78, RULE_doc);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(427); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(426); match(DOC);
				}
				}
				setState(429); 
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

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3<\u01b2\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\3\2\7\2T\n\2\f"+
		"\2\16\2W\13\2\3\2\5\2Z\n\2\3\2\7\2]\n\2\f\2\16\2`\13\2\3\2\3\2\7\2d\n"+
		"\2\f\2\16\2g\13\2\3\2\3\2\3\3\5\3l\n\3\3\3\7\3o\n\3\f\3\16\3r\13\3\3\4"+
		"\6\4u\n\4\r\4\16\4v\3\4\3\4\3\4\3\5\3\5\3\5\3\6\5\6\u0080\n\6\3\6\3\6"+
		"\5\6\u0084\n\6\3\6\5\6\u0087\n\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5"+
		"\7\u0092\n\7\5\7\u0094\n\7\3\7\5\7\u0097\n\7\3\b\3\b\3\t\3\t\5\t\u009d"+
		"\n\t\3\t\3\t\3\n\5\n\u00a2\n\n\3\n\3\n\3\n\5\n\u00a7\n\n\3\n\7\n\u00aa"+
		"\n\n\f\n\16\n\u00ad\13\n\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\5\f\u00be\n\f\3\r\3\r\3\r\6\r\u00c3\n\r\r\r\16\r\u00c4"+
		"\6\r\u00c7\n\r\r\r\16\r\u00c8\3\r\3\r\3\16\3\16\3\16\5\16\u00d0\n\16\3"+
		"\17\3\17\6\17\u00d4\n\17\r\17\16\17\u00d5\3\17\3\17\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\5\20\u00e9\n\20"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u00f4\n\21\3\21\5\21"+
		"\u00f7\n\21\3\22\5\22\u00fa\n\22\3\22\3\22\3\22\3\22\3\22\3\22\5\22\u0102"+
		"\n\22\3\23\5\23\u0105\n\23\3\23\3\23\3\23\3\23\3\23\3\23\5\23\u010d\n"+
		"\23\3\24\5\24\u0110\n\24\3\24\3\24\3\24\3\24\3\24\3\24\5\24\u0118\n\24"+
		"\3\25\5\25\u011b\n\25\3\25\3\25\3\25\3\25\3\25\3\25\5\25\u0123\n\25\3"+
		"\26\5\26\u0126\n\26\3\26\3\26\3\26\3\26\3\26\3\26\5\26\u012e\n\26\3\27"+
		"\5\27\u0131\n\27\3\27\3\27\3\27\3\27\3\27\3\27\5\27\u0139\n\27\3\30\5"+
		"\30\u013c\n\30\3\30\3\30\3\30\3\30\3\30\3\30\3\31\5\31\u0145\n\31\3\31"+
		"\3\31\3\31\3\31\3\31\5\31\u014c\n\31\3\32\5\32\u014f\n\32\3\32\3\32\3"+
		"\32\3\32\3\32\3\32\5\32\u0157\n\32\3\32\6\32\u015a\n\32\r\32\16\32\u015b"+
		"\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \6 "+
		"\u016c\n \r \16 \u016d\3 \3 \3!\3!\6!\u0174\n!\r!\16!\u0175\3!\3!\3\""+
		"\3\"\6\"\u017c\n\"\r\"\16\"\u017d\3\"\3\"\3#\3#\6#\u0184\n#\r#\16#\u0185"+
		"\3#\3#\3$\3$\6$\u018c\n$\r$\16$\u018d\3$\3$\3%\3%\6%\u0194\n%\r%\16%\u0195"+
		"\3%\3%\3&\7&\u019b\n&\f&\16&\u019e\13&\3&\3&\3\'\7\'\u01a3\n\'\f\'\16"+
		"\'\u01a6\13\'\3\'\3\'\3(\3(\3(\3)\6)\u01ae\n)\r)\16)\u01af\3)\2\2*\2\4"+
		"\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJLNP"+
		"\2\7\4\2\3\3\62\62\3\2-.\3\2-/\3\2\60\61\4\2\5\13\r\r\u01e1\2U\3\2\2\2"+
		"\4k\3\2\2\2\6t\3\2\2\2\b{\3\2\2\2\n\177\3\2\2\2\f\u0093\3\2\2\2\16\u0098"+
		"\3\2\2\2\20\u009a\3\2\2\2\22\u00a1\3\2\2\2\24\u00ae\3\2\2\2\26\u00bd\3"+
		"\2\2\2\30\u00bf\3\2\2\2\32\u00cf\3\2\2\2\34\u00d1\3\2\2\2\36\u00d9\3\2"+
		"\2\2 \u00f3\3\2\2\2\"\u00f9\3\2\2\2$\u0104\3\2\2\2&\u010f\3\2\2\2(\u011a"+
		"\3\2\2\2*\u0125\3\2\2\2,\u0130\3\2\2\2.\u013b\3\2\2\2\60\u0144\3\2\2\2"+
		"\62\u014e\3\2\2\2\64\u015f\3\2\2\2\66\u0161\3\2\2\28\u0163\3\2\2\2:\u0165"+
		"\3\2\2\2<\u0167\3\2\2\2>\u0169\3\2\2\2@\u0171\3\2\2\2B\u0179\3\2\2\2D"+
		"\u0181\3\2\2\2F\u0189\3\2\2\2H\u0191\3\2\2\2J\u019c\3\2\2\2L\u01a4\3\2"+
		"\2\2N\u01a9\3\2\2\2P\u01ad\3\2\2\2RT\7\65\2\2SR\3\2\2\2TW\3\2\2\2US\3"+
		"\2\2\2UV\3\2\2\2VY\3\2\2\2WU\3\2\2\2XZ\5\4\3\2YX\3\2\2\2YZ\3\2\2\2Z^\3"+
		"\2\2\2[]\7\65\2\2\\[\3\2\2\2]`\3\2\2\2^\\\3\2\2\2^_\3\2\2\2_a\3\2\2\2"+
		"`^\3\2\2\2ae\5\n\6\2bd\7\65\2\2cb\3\2\2\2dg\3\2\2\2ec\3\2\2\2ef\3\2\2"+
		"\2fh\3\2\2\2ge\3\2\2\2hi\7\2\2\3i\3\3\2\2\2jl\5\b\5\2kj\3\2\2\2kl\3\2"+
		"\2\2lp\3\2\2\2mo\5\6\4\2nm\3\2\2\2or\3\2\2\2pn\3\2\2\2pq\3\2\2\2q\5\3"+
		"\2\2\2rp\3\2\2\2su\7\65\2\2ts\3\2\2\2uv\3\2\2\2vt\3\2\2\2vw\3\2\2\2wx"+
		"\3\2\2\2xy\7\20\2\2yz\5J&\2z\7\3\2\2\2{|\7\21\2\2|}\5J&\2}\t\3\2\2\2~"+
		"\u0080\5P)\2\177~\3\2\2\2\177\u0080\3\2\2\2\u0080\u0081\3\2\2\2\u0081"+
		"\u0083\5\f\7\2\u0082\u0084\5H%\2\u0083\u0082\3\2\2\2\u0083\u0084\3\2\2"+
		"\2\u0084\u0086\3\2\2\2\u0085\u0087\5\30\r\2\u0086\u0085\3\2\2\2\u0086"+
		"\u0087\3\2\2\2\u0087\13\3\2\2\2\u0088\u0089\7\4\2\2\u0089\u0094\7\62\2"+
		"\2\u008a\u008b\5\16\b\2\u008b\u008c\7\62\2\2\u008c\u0094\3\2\2\2\u008d"+
		"\u008e\5\16\b\2\u008e\u008f\7\33\2\2\u008f\u0091\5L\'\2\u0090\u0092\7"+
		"\62\2\2\u0091\u0090\3\2\2\2\u0091\u0092\3\2\2\2\u0092\u0094\3\2\2\2\u0093"+
		"\u0088\3\2\2\2\u0093\u008a\3\2\2\2\u0093\u008d\3\2\2\2\u0094\u0096\3\2"+
		"\2\2\u0095\u0097\5\20\t\2\u0096\u0095\3\2\2\2\u0096\u0097\3\2\2\2\u0097"+
		"\r\3\2\2\2\u0098\u0099\t\2\2\2\u0099\17\3\2\2\2\u009a\u009c\7\25\2\2\u009b"+
		"\u009d\5\22\n\2\u009c\u009b\3\2\2\2\u009c\u009d\3\2\2\2\u009d\u009e\3"+
		"\2\2\2\u009e\u009f\7\26\2\2\u009f\21\3\2\2\2\u00a0\u00a2\5\24\13\2\u00a1"+
		"\u00a0\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3\u00ab\5\26"+
		"\f\2\u00a4\u00a6\7\34\2\2\u00a5\u00a7\5\24\13\2\u00a6\u00a5\3\2\2\2\u00a6"+
		"\u00a7\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8\u00aa\5\26\f\2\u00a9\u00a4\3"+
		"\2\2\2\u00aa\u00ad\3\2\2\2\u00ab\u00a9\3\2\2\2\u00ab\u00ac\3\2\2\2\u00ac"+
		"\23\3\2\2\2\u00ad\u00ab\3\2\2\2\u00ae\u00af\7\62\2\2\u00af\u00b0\7\33"+
		"\2\2\u00b0\25\3\2\2\2\u00b1\u00be\5L\'\2\u00b2\u00be\5<\37\2\u00b3\u00be"+
		"\5:\36\2\u00b4\u00be\5\64\33\2\u00b5\u00be\5\66\34\2\u00b6\u00be\58\35"+
		"\2\u00b7\u00be\5> \2\u00b8\u00be\5@!\2\u00b9\u00be\5B\"\2\u00ba\u00be"+
		"\5D#\2\u00bb\u00be\5F$\2\u00bc\u00be\5\34\17\2\u00bd\u00b1\3\2\2\2\u00bd"+
		"\u00b2\3\2\2\2\u00bd\u00b3\3\2\2\2\u00bd\u00b4\3\2\2\2\u00bd\u00b5\3\2"+
		"\2\2\u00bd\u00b6\3\2\2\2\u00bd\u00b7\3\2\2\2\u00bd\u00b8\3\2\2\2\u00bd"+
		"\u00b9\3\2\2\2\u00bd\u00ba\3\2\2\2\u00bd\u00bb\3\2\2\2\u00bd\u00bc\3\2"+
		"\2\2\u00be\27\3\2\2\2\u00bf\u00c6\7:\2\2\u00c0\u00c2\5\32\16\2\u00c1\u00c3"+
		"\7\65\2\2\u00c2\u00c1\3\2\2\2\u00c3\u00c4\3\2\2\2\u00c4\u00c2\3\2\2\2"+
		"\u00c4\u00c5\3\2\2\2\u00c5\u00c7\3\2\2\2\u00c6\u00c0\3\2\2\2\u00c7\u00c8"+
		"\3\2\2\2\u00c8\u00c6\3\2\2\2\u00c8\u00c9\3\2\2\2\u00c9\u00ca\3\2\2\2\u00ca"+
		"\u00cb\7;\2\2\u00cb\31\3\2\2\2\u00cc\u00d0\5 \21\2\u00cd\u00d0\5\n\6\2"+
		"\u00ce\u00d0\5\36\20\2\u00cf\u00cc\3\2\2\2\u00cf\u00cd\3\2\2\2\u00cf\u00ce"+
		"\3\2\2\2\u00d0\33\3\2\2\2\u00d1\u00d3\7\23\2\2\u00d2\u00d4\7\62\2\2\u00d3"+
		"\u00d2\3\2\2\2\u00d4\u00d5\3\2\2\2\u00d5\u00d3\3\2\2\2\u00d5\u00d6\3\2"+
		"\2\2\u00d6\u00d7\3\2\2\2\u00d7\u00d8\7\24\2\2\u00d8\35\3\2\2\2\u00d9\u00da"+
		"\7\62\2\2\u00da\u00e8\7\33\2\2\u00db\u00e9\7+\2\2\u00dc\u00e9\5L\'\2\u00dd"+
		"\u00e9\5<\37\2\u00de\u00e9\5:\36\2\u00df\u00e9\5\64\33\2\u00e0\u00e9\5"+
		"\66\34\2\u00e1\u00e9\58\35\2\u00e2\u00e9\5\34\17\2\u00e3\u00e9\5> \2\u00e4"+
		"\u00e9\5@!\2\u00e5\u00e9\5B\"\2\u00e6\u00e9\5D#\2\u00e7\u00e9\5F$\2\u00e8"+
		"\u00db\3\2\2\2\u00e8\u00dc\3\2\2\2\u00e8\u00dd\3\2\2\2\u00e8\u00de\3\2"+
		"\2\2\u00e8\u00df\3\2\2\2\u00e8\u00e0\3\2\2\2\u00e8\u00e1\3\2\2\2\u00e8"+
		"\u00e2\3\2\2\2\u00e8\u00e3\3\2\2\2\u00e8\u00e4\3\2\2\2\u00e8\u00e5\3\2"+
		"\2\2\u00e8\u00e6\3\2\2\2\u00e8\u00e7\3\2\2\2\u00e9\37\3\2\2\2\u00ea\u00f4"+
		"\5\"\22\2\u00eb\u00f4\5(\25\2\u00ec\u00f4\5*\26\2\u00ed\u00f4\5,\27\2"+
		"\u00ee\u00f4\5$\23\2\u00ef\u00f4\5&\24\2\u00f0\u00f4\5.\30\2\u00f1\u00f4"+
		"\5\60\31\2\u00f2\u00f4\5\62\32\2\u00f3\u00ea\3\2\2\2\u00f3\u00eb\3\2\2"+
		"\2\u00f3\u00ec\3\2\2\2\u00f3\u00ed\3\2\2\2\u00f3\u00ee\3\2\2\2\u00f3\u00ef"+
		"\3\2\2\2\u00f3\u00f0\3\2\2\2\u00f3\u00f1\3\2\2\2\u00f3\u00f2\3\2\2\2\u00f4"+
		"\u00f6\3\2\2\2\u00f5\u00f7\5H%\2\u00f6\u00f5\3\2\2\2\u00f6\u00f7\3\2\2"+
		"\2\u00f7!\3\2\2\2\u00f8\u00fa\5P)\2\u00f9\u00f8\3\2\2\2\u00f9\u00fa\3"+
		"\2\2\2\u00fa\u00fb\3\2\2\2\u00fb\u00fc\7\f\2\2\u00fc\u00fd\7%\2\2\u00fd"+
		"\u0101\7\62\2\2\u00fe\u00ff\7\33\2\2\u00ff\u0102\5<\37\2\u0100\u0102\7"+
		"+\2\2\u0101\u00fe\3\2\2\2\u0101\u0100\3\2\2\2\u0101\u0102\3\2\2\2\u0102"+
		"#\3\2\2\2\u0103\u0105\5P)\2\u0104\u0103\3\2\2\2\u0104\u0105\3\2\2\2\u0105"+
		"\u0106\3\2\2\2\u0106\u0107\7\f\2\2\u0107\u0108\7*\2\2\u0108\u010c\7\62"+
		"\2\2\u0109\u010a\7\33\2\2\u010a\u010d\5:\36\2\u010b\u010d\7+\2\2\u010c"+
		"\u0109\3\2\2\2\u010c\u010b\3\2\2\2\u010c\u010d\3\2\2\2\u010d%\3\2\2\2"+
		"\u010e\u0110\5P)\2\u010f\u010e\3\2\2\2\u010f\u0110\3\2\2\2\u0110\u0111"+
		"\3\2\2\2\u0111\u0112\7\f\2\2\u0112\u0113\7)\2\2\u0113\u0117\7\62\2\2\u0114"+
		"\u0115\7\33\2\2\u0115\u0118\5<\37\2\u0116\u0118\7+\2\2\u0117\u0114\3\2"+
		"\2\2\u0117\u0116\3\2\2\2\u0117\u0118\3\2\2\2\u0118\'\3\2\2\2\u0119\u011b"+
		"\5P)\2\u011a\u0119\3\2\2\2\u011a\u011b\3\2\2\2\u011b\u011c\3\2\2\2\u011c"+
		"\u011d\7\f\2\2\u011d\u011e\7\'\2\2\u011e\u0122\7\62\2\2\u011f\u0120\7"+
		"\33\2\2\u0120\u0123\5\64\33\2\u0121\u0123\7+\2\2\u0122\u011f\3\2\2\2\u0122"+
		"\u0121\3\2\2\2\u0122\u0123\3\2\2\2\u0123)\3\2\2\2\u0124\u0126\5P)\2\u0125"+
		"\u0124\3\2\2\2\u0125\u0126\3\2\2\2\u0126\u0127\3\2\2\2\u0127\u0128\7\f"+
		"\2\2\u0128\u0129\7&\2\2\u0129\u012d\7\62\2\2\u012a\u012b\7\33\2\2\u012b"+
		"\u012e\5\66\34\2\u012c\u012e\7+\2\2\u012d\u012a\3\2\2\2\u012d\u012c\3"+
		"\2\2\2\u012d\u012e\3\2\2\2\u012e+\3\2\2\2\u012f\u0131\5P)\2\u0130\u012f"+
		"\3\2\2\2\u0130\u0131\3\2\2\2\u0131\u0132\3\2\2\2\u0132\u0133\7\f\2\2\u0133"+
		"\u0134\7(\2\2\u0134\u0138\7\62\2\2\u0135\u0136\7\33\2\2\u0136\u0139\5"+
		"8\35\2\u0137\u0139\7+\2\2\u0138\u0135\3\2\2\2\u0138\u0137\3\2\2\2\u0138"+
		"\u0139\3\2\2\2\u0139-\3\2\2\2\u013a\u013c\5P)\2\u013b\u013a\3\2\2\2\u013b"+
		"\u013c\3\2\2\2\u013c\u013d\3\2\2\2\u013d\u013e\7\f\2\2\u013e\u013f\7\17"+
		"\2\2\u013f\u0140\7\33\2\2\u0140\u0141\7\62\2\2\u0141\u0142\7\62\2\2\u0142"+
		"/\3\2\2\2\u0143\u0145\5P)\2\u0144\u0143\3\2\2\2\u0144\u0145\3\2\2\2\u0145"+
		"\u0146\3\2\2\2\u0146\u0147\7\f\2\2\u0147\u0148\5L\'\2\u0148\u014b\7\62"+
		"\2\2\u0149\u014a\7\33\2\2\u014a\u014c\7+\2\2\u014b\u0149\3\2\2\2\u014b"+
		"\u014c\3\2\2\2\u014c\61\3\2\2\2\u014d\u014f\5P)\2\u014e\u014d\3\2\2\2"+
		"\u014e\u014f\3\2\2\2\u014f\u0150\3\2\2\2\u0150\u0151\7\f\2\2\u0151\u0152"+
		"\7\16\2\2\u0152\u0153\7\62\2\2\u0153\u0159\7:\2\2\u0154\u0156\7\62\2\2"+
		"\u0155\u0157\7!\2\2\u0156\u0155\3\2\2\2\u0156\u0157\3\2\2\2\u0157\u0158"+
		"\3\2\2\2\u0158\u015a\7\65\2\2\u0159\u0154\3\2\2\2\u015a\u015b\3\2\2\2"+
		"\u015b\u0159\3\2\2\2\u015b\u015c\3\2\2\2\u015c\u015d\3\2\2\2\u015d\u015e"+
		"\7;\2\2\u015e\63\3\2\2\2\u015f\u0160\7-\2\2\u0160\65\3\2\2\2\u0161\u0162"+
		"\t\3\2\2\u0162\67\3\2\2\2\u0163\u0164\t\4\2\2\u01649\3\2\2\2\u0165\u0166"+
		"\7,\2\2\u0166;\3\2\2\2\u0167\u0168\t\5\2\2\u0168=\3\2\2\2\u0169\u016b"+
		"\7\23\2\2\u016a\u016c\7\60\2\2\u016b\u016a\3\2\2\2\u016c\u016d\3\2\2\2"+
		"\u016d\u016b\3\2\2\2\u016d\u016e\3\2\2\2\u016e\u016f\3\2\2\2\u016f\u0170"+
		"\7\24\2\2\u0170?\3\2\2\2\u0171\u0173\7\23\2\2\u0172\u0174\7,\2\2\u0173"+
		"\u0172\3\2\2\2\u0174\u0175\3\2\2\2\u0175\u0173\3\2\2\2\u0175\u0176\3\2"+
		"\2\2\u0176\u0177\3\2\2\2\u0177\u0178\7\24\2\2\u0178A\3\2\2\2\u0179\u017b"+
		"\7\23\2\2\u017a\u017c\7-\2\2\u017b\u017a\3\2\2\2\u017c\u017d\3\2\2\2\u017d"+
		"\u017b\3\2\2\2\u017d\u017e\3\2\2\2\u017e\u017f\3\2\2\2\u017f\u0180\7\24"+
		"\2\2\u0180C\3\2\2\2\u0181\u0183\7\23\2\2\u0182\u0184\t\3\2\2\u0183\u0182"+
		"\3\2\2\2\u0184\u0185\3\2\2\2\u0185\u0183\3\2\2\2\u0185\u0186\3\2\2\2\u0186"+
		"\u0187\3\2\2\2\u0187\u0188\7\24\2\2\u0188E\3\2\2\2\u0189\u018b\7\23\2"+
		"\2\u018a\u018c\t\4\2\2\u018b\u018a\3\2\2\2\u018c\u018d\3\2\2\2\u018d\u018b"+
		"\3\2\2\2\u018d\u018e\3\2\2\2\u018e\u018f\3\2\2\2\u018f\u0190\7\24\2\2"+
		"\u0190G\3\2\2\2\u0191\u0193\7\31\2\2\u0192\u0194\t\6\2\2\u0193\u0192\3"+
		"\2\2\2\u0194\u0195\3\2\2\2\u0195\u0193\3\2\2\2\u0195\u0196\3\2\2\2\u0196"+
		"\u0197\3\2\2\2\u0197\u0198\7\32\2\2\u0198I\3\2\2\2\u0199\u019b\5N(\2\u019a"+
		"\u0199\3\2\2\2\u019b\u019e\3\2\2\2\u019c\u019a\3\2\2\2\u019c\u019d\3\2"+
		"\2\2\u019d\u019f\3\2\2\2\u019e\u019c\3\2\2\2\u019f\u01a0\7\62\2\2\u01a0"+
		"K\3\2\2\2\u01a1\u01a3\5N(\2\u01a2\u01a1\3\2\2\2\u01a3\u01a6\3\2\2\2\u01a4"+
		"\u01a2\3\2\2\2\u01a4\u01a5\3\2\2\2\u01a5\u01a7\3\2\2\2\u01a6\u01a4\3\2"+
		"\2\2\u01a7\u01a8\7\62\2\2\u01a8M\3\2\2\2\u01a9\u01aa\7\62\2\2\u01aa\u01ab"+
		"\7\35\2\2\u01abO\3\2\2\2\u01ac\u01ae\7\67\2\2\u01ad\u01ac\3\2\2\2\u01ae"+
		"\u01af\3\2\2\2\u01af\u01ad\3\2\2\2\u01af\u01b0\3\2\2\2\u01b0Q\3\2\2\2"+
		"\66UY^ekpv\177\u0083\u0086\u0091\u0093\u0096\u009c\u00a1\u00a6\u00ab\u00bd"+
		"\u00c4\u00c8\u00cf\u00d5\u00e8\u00f3\u00f6\u00f9\u0101\u0104\u010c\u010f"+
		"\u0117\u011a\u0122\u0125\u012d\u0130\u0138\u013b\u0144\u014b\u014e\u0156"+
		"\u015b\u016d\u0175\u017d\u0185\u018d\u0195\u019c\u01a4\u01af";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}