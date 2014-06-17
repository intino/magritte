// Generated from /Users/oroncal/workspace/tara/rt/src/siani/tara/compiler/parser/antlr/TaraGrammar.g4 by ANTLR 4.x

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
		PACKAGE=18, RIGHT_SQUARE=21, GENERIC=7, NEGATIVE_VALUE=44, DOC=52, LETTER=49, 
		CASE=2, CLOSE_AN=27, DEDENT=56, ABSTRACT=15, SINGLETON=6, BOOLEAN_VALUE=42, 
		METAIDENTIFIER=1, RESOURCE=13, MULTIPLE=3, UNKNOWN_TOKEN=57, WORD=12, 
		OPEN_BRACKET=24, FINAL=14, IMPORT=17, RIGHT_PARENTHESIS=23, COMMA=29, 
		LEFT_PARENTHESIS=22, IDENTIFIER=47, BASE=16, VAR=10, NL=54, DIGIT=48, 
		INTENTION=8, DOT=30, STRING_VALUE=46, DOUBLE_VALUE=45, POSITIVE=34, NEW_LINE_INDENT=55, 
		NATURAL_TYPE=38, NEGATIVE=35, HAS_NAME=9, DOUBLE_TYPE=39, BOOLEAN_TYPE=41, 
		SEMICOLON=33, REQUIRED=4, INT_TYPE=37, LIST=19, ROOT=5, OPEN_AN=26, COLON=28, 
		ALIAS_TYPE=36, POSITIVE_VALUE=43, NEWLINE=50, PROPERTY=11, SPACES=51, 
		SP=53, STRING_TYPE=40, ASSIGN=31, LEFT_SQUARE=20, DOUBLE_COMMAS=32, CLOSE_BRACKET=25;
	public static final String[] tokenNames = {
		"<INVALID>", "'Concept'", "'case'", "'multiple'", "'required'", "'root'", 
		"'singleton'", "'generic'", "'intention'", "'has-name'", "'var'", "'property'", 
		"'Word'", "'Resource'", "'final'", "'abstract'", "'base'", "'import'", 
		"'package'", "LIST", "'['", "']'", "'('", "')'", "'{'", "'}'", "'<'", 
		"'>'", "':'", "','", "'.'", "'='", "'\"'", "SEMICOLON", "'+'", "'-'", 
		"'Alias'", "'Integer'", "'Natural'", "'Double'", "'String'", "'Boolean'", 
		"BOOLEAN_VALUE", "POSITIVE_VALUE", "NEGATIVE_VALUE", "DOUBLE_VALUE", "STRING_VALUE", 
		"IDENTIFIER", "DIGIT", "LETTER", "NEWLINE", "SPACES", "DOC", "SP", "NL", 
		"'indent'", "'dedent'", "UNKNOWN_TOKEN"
	};
	public static final int
		RULE_root = 0, RULE_header = 1, RULE_imports = 2, RULE_packet = 3, RULE_concept = 4, 
		RULE_signature = 5, RULE_parameters = 6, RULE_parameterList = 7, RULE_explicit = 8, 
		RULE_parameter = 9, RULE_body = 10, RULE_conceptConstituents = 11, RULE_identifierList = 12, 
		RULE_attribute = 13, RULE_aliasAttribute = 14, RULE_booleanAttribute = 15, 
		RULE_stringAttribute = 16, RULE_naturalAttribute = 17, RULE_integerAttribute = 18, 
		RULE_doubleAttribute = 19, RULE_resource = 20, RULE_reference = 21, RULE_word = 22, 
		RULE_naturalValue = 23, RULE_integerValue = 24, RULE_doubleValue = 25, 
		RULE_booleanValue = 26, RULE_stringValue = 27, RULE_stringList = 28, RULE_booleanList = 29, 
		RULE_naturalList = 30, RULE_integerList = 31, RULE_doubleList = 32, RULE_annotations = 33, 
		RULE_headerReference = 34, RULE_identifierReference = 35, RULE_hierarchy = 36, 
		RULE_modifier = 37, RULE_doc = 38;
	public static final String[] ruleNames = {
		"root", "header", "imports", "packet", "concept", "signature", "parameters", 
		"parameterList", "explicit", "parameter", "body", "conceptConstituents", 
		"identifierList", "attribute", "aliasAttribute", "booleanAttribute", "stringAttribute", 
		"naturalAttribute", "integerAttribute", "doubleAttribute", "resource", 
		"reference", "word", "naturalValue", "integerValue", "doubleValue", "booleanValue", 
		"stringValue", "stringList", "booleanList", "naturalList", "integerList", 
		"doubleList", "annotations", "headerReference", "identifierReference", 
		"hierarchy", "modifier", "doc"
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
			setState(81);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(78); match(NEWLINE);
					}
					} 
				}
				setState(83);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			}
			setState(85);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				{
				setState(84); header();
				}
				break;
			}
			setState(90);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(87); match(NEWLINE);
				}
				}
				setState(92);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(93); concept();
			setState(97);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(94); match(NEWLINE);
				}
				}
				setState(99);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(100); match(EOF);
			}
		}
		catch (RecognitionException re) {
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
		public ImportsContext imports(int i) {
			return getRuleContext(ImportsContext.class,i);
		}
		public PacketContext packet() {
			return getRuleContext(PacketContext.class,0);
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
			setState(103);
			_la = _input.LA(1);
			if (_la==PACKAGE) {
				{
				setState(102); packet();
				}
			}

			setState(108);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(105); imports();
					}
					} 
				}
				setState(110);
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
		public TerminalNode NEWLINE() { return getToken(TaraGrammar.NEWLINE, 0); }
		public HeaderReferenceContext headerReference() {
			return getRuleContext(HeaderReferenceContext.class,0);
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
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(111); match(NEWLINE);
			setState(112); match(IMPORT);
			setState(113); headerReference();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PacketContext extends ParserRuleContext {
		public HeaderReferenceContext headerReference() {
			return getRuleContext(HeaderReferenceContext.class,0);
		}
		public TerminalNode PACKAGE() { return getToken(TaraGrammar.PACKAGE, 0); }
		public PacketContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_packet; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterPacket(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitPacket(this);
		}
	}

	public final PacketContext packet() throws RecognitionException {
		PacketContext _localctx = new PacketContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_packet);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(115); match(PACKAGE);
			setState(116); headerReference();
			}
		}
		catch (RecognitionException re) {
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
			setState(119);
			_la = _input.LA(1);
			if (_la==DOC) {
				{
				setState(118); doc();
				}
			}

			setState(121); signature();
			setState(123);
			_la = _input.LA(1);
			if (_la==OPEN_AN) {
				{
				setState(122); annotations();
				}
			}

			setState(126);
			_la = _input.LA(1);
			if (_la==NEW_LINE_INDENT) {
				{
				setState(125); body();
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
		public ModifierContext modifier() {
			return getRuleContext(ModifierContext.class,0);
		}
		public TerminalNode CASE() { return getToken(TaraGrammar.CASE, 0); }
		public ParametersContext parameters() {
			return getRuleContext(ParametersContext.class,0);
		}
		public TerminalNode METAIDENTIFIER() { return getToken(TaraGrammar.METAIDENTIFIER, 0); }
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
			setState(144);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				{
				setState(128); match(CASE);
				setState(129); match(IDENTIFIER);
				}
				break;
			case 2:
				{
				{
				setState(130); match(METAIDENTIFIER);
				setState(132);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << FINAL) | (1L << ABSTRACT) | (1L << BASE))) != 0)) {
					{
					setState(131); modifier();
					}
				}

				setState(134); match(IDENTIFIER);
				}
				}
				break;
			case 3:
				{
				{
				setState(135); match(METAIDENTIFIER);
				setState(136); match(COLON);
				setState(137); identifierReference();
				setState(139);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << FINAL) | (1L << ABSTRACT) | (1L << BASE))) != 0)) {
					{
					setState(138); modifier();
					}
				}

				setState(142);
				_la = _input.LA(1);
				if (_la==IDENTIFIER) {
					{
					setState(141); match(IDENTIFIER);
					}
				}

				}
				}
				break;
			}
			setState(147);
			_la = _input.LA(1);
			if (_la==LEFT_PARENTHESIS) {
				{
				setState(146); parameters();
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
		enterRule(_localctx, 12, RULE_parameters);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(149); match(LEFT_PARENTHESIS);
			setState(151);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LEFT_SQUARE) | (1L << BOOLEAN_VALUE) | (1L << POSITIVE_VALUE) | (1L << NEGATIVE_VALUE) | (1L << DOUBLE_VALUE) | (1L << STRING_VALUE) | (1L << IDENTIFIER))) != 0)) {
				{
				setState(150); parameterList();
				}
			}

			setState(153); match(RIGHT_PARENTHESIS);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 14, RULE_parameterList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(156);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				{
				setState(155); explicit();
				}
				break;
			}
			setState(158); parameter();
			setState(166);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(159); match(COMMA);
				setState(161);
				switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
				case 1:
					{
					setState(160); explicit();
					}
					break;
				}
				setState(163); parameter();
				}
				}
				setState(168);
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
		enterRule(_localctx, 16, RULE_explicit);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(169); match(IDENTIFIER);
			setState(170); match(COLON);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 18, RULE_parameter);
		try {
			setState(184);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(172); identifierReference();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(173); stringValue();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(174); booleanValue();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(175); naturalValue();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(176); integerValue();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(177); doubleValue();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(178); stringList();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(179); booleanList();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(180); naturalList();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(181); integerList();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(182); doubleList();
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(183); identifierList();
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
		enterRule(_localctx, 20, RULE_body);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(186); match(NEW_LINE_INDENT);
			setState(193); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(187); conceptConstituents();
				setState(189); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(188); match(NEWLINE);
					}
					}
					setState(191); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NEWLINE );
				}
				}
				setState(195); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << METAIDENTIFIER) | (1L << CASE) | (1L << VAR) | (1L << PROPERTY) | (1L << DOC))) != 0) );
			setState(197); match(DEDENT);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 22, RULE_conceptConstituents);
		try {
			setState(201);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(199); attribute();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(200); concept();
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
		enterRule(_localctx, 24, RULE_identifierList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(203); match(LEFT_SQUARE);
			setState(205); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(204); match(IDENTIFIER);
				}
				}
				setState(207); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==IDENTIFIER );
			setState(209); match(RIGHT_SQUARE);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 26, RULE_attribute);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(220);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				{
				setState(211); aliasAttribute();
				}
				break;
			case 2:
				{
				setState(212); naturalAttribute();
				}
				break;
			case 3:
				{
				setState(213); integerAttribute();
				}
				break;
			case 4:
				{
				setState(214); doubleAttribute();
				}
				break;
			case 5:
				{
				setState(215); booleanAttribute();
				}
				break;
			case 6:
				{
				setState(216); stringAttribute();
				}
				break;
			case 7:
				{
				setState(217); resource();
				}
				break;
			case 8:
				{
				setState(218); reference();
				}
				break;
			case 9:
				{
				setState(219); word();
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

	public static class AliasAttributeContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
		public TerminalNode ALIAS_TYPE() { return getToken(TaraGrammar.ALIAS_TYPE, 0); }
		public TerminalNode PROPERTY() { return getToken(TaraGrammar.PROPERTY, 0); }
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
		enterRule(_localctx, 28, RULE_aliasAttribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(223);
			_la = _input.LA(1);
			if (_la==DOC) {
				{
				setState(222); doc();
				}
			}

			setState(225);
			_la = _input.LA(1);
			if ( !(_la==VAR || _la==PROPERTY) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			setState(226); match(ALIAS_TYPE);
			setState(227); match(IDENTIFIER);
			setState(230);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(228); match(COLON);
				setState(229); stringValue();
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
		public BooleanValueContext booleanValue() {
			return getRuleContext(BooleanValueContext.class,0);
		}
		public TerminalNode BOOLEAN_TYPE() { return getToken(TaraGrammar.BOOLEAN_TYPE, 0); }
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
		public TerminalNode PROPERTY() { return getToken(TaraGrammar.PROPERTY, 0); }
		public TerminalNode VAR() { return getToken(TaraGrammar.VAR, 0); }
		public BooleanListContext booleanList() {
			return getRuleContext(BooleanListContext.class,0);
		}
		public DocContext doc() {
			return getRuleContext(DocContext.class,0);
		}
		public TerminalNode COLON() { return getToken(TaraGrammar.COLON, 0); }
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
		enterRule(_localctx, 30, RULE_booleanAttribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(233);
			_la = _input.LA(1);
			if (_la==DOC) {
				{
				setState(232); doc();
				}
			}

			setState(235);
			_la = _input.LA(1);
			if ( !(_la==VAR || _la==PROPERTY) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			setState(236); match(BOOLEAN_TYPE);
			setState(248);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				{
				{
				setState(237); match(IDENTIFIER);
				setState(240);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(238); match(COLON);
					setState(239); booleanValue();
					}
				}

				}
				}
				break;
			case LIST:
				{
				{
				setState(242); match(LIST);
				setState(243); match(IDENTIFIER);
				setState(246);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(244); match(COLON);
					setState(245); booleanList();
					}
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

	public static class StringAttributeContext extends ParserRuleContext {
		public StringListContext stringList() {
			return getRuleContext(StringListContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
		public TerminalNode PROPERTY() { return getToken(TaraGrammar.PROPERTY, 0); }
		public TerminalNode VAR() { return getToken(TaraGrammar.VAR, 0); }
		public TerminalNode STRING_TYPE() { return getToken(TaraGrammar.STRING_TYPE, 0); }
		public StringValueContext stringValue() {
			return getRuleContext(StringValueContext.class,0);
		}
		public DocContext doc() {
			return getRuleContext(DocContext.class,0);
		}
		public TerminalNode COLON() { return getToken(TaraGrammar.COLON, 0); }
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
		enterRule(_localctx, 32, RULE_stringAttribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(251);
			_la = _input.LA(1);
			if (_la==DOC) {
				{
				setState(250); doc();
				}
			}

			setState(253);
			_la = _input.LA(1);
			if ( !(_la==VAR || _la==PROPERTY) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			setState(254); match(STRING_TYPE);
			setState(266);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				{
				{
				setState(255); match(IDENTIFIER);
				setState(258);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(256); match(COLON);
					setState(257); stringValue();
					}
				}

				}
				}
				break;
			case LIST:
				{
				{
				setState(260); match(LIST);
				setState(261); match(IDENTIFIER);
				setState(264);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(262); match(COLON);
					setState(263); stringList();
					}
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

	public static class NaturalAttributeContext extends ParserRuleContext {
		public TerminalNode NATURAL_TYPE() { return getToken(TaraGrammar.NATURAL_TYPE, 0); }
		public NaturalValueContext naturalValue() {
			return getRuleContext(NaturalValueContext.class,0);
		}
		public NaturalListContext naturalList() {
			return getRuleContext(NaturalListContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
		public TerminalNode PROPERTY() { return getToken(TaraGrammar.PROPERTY, 0); }
		public TerminalNode VAR() { return getToken(TaraGrammar.VAR, 0); }
		public DocContext doc() {
			return getRuleContext(DocContext.class,0);
		}
		public TerminalNode COLON() { return getToken(TaraGrammar.COLON, 0); }
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
		enterRule(_localctx, 34, RULE_naturalAttribute);
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

			setState(271);
			_la = _input.LA(1);
			if ( !(_la==VAR || _la==PROPERTY) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			setState(272); match(NATURAL_TYPE);
			setState(284);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				{
				{
				setState(273); match(IDENTIFIER);
				setState(276);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(274); match(COLON);
					setState(275); naturalValue();
					}
				}

				}
				}
				break;
			case LIST:
				{
				{
				setState(278); match(LIST);
				setState(279); match(IDENTIFIER);
				setState(282);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(280); match(COLON);
					setState(281); naturalList();
					}
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

	public static class IntegerAttributeContext extends ParserRuleContext {
		public TerminalNode INT_TYPE() { return getToken(TaraGrammar.INT_TYPE, 0); }
		public IntegerListContext integerList() {
			return getRuleContext(IntegerListContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
		public TerminalNode PROPERTY() { return getToken(TaraGrammar.PROPERTY, 0); }
		public TerminalNode VAR() { return getToken(TaraGrammar.VAR, 0); }
		public DocContext doc() {
			return getRuleContext(DocContext.class,0);
		}
		public TerminalNode COLON() { return getToken(TaraGrammar.COLON, 0); }
		public TerminalNode LIST() { return getToken(TaraGrammar.LIST, 0); }
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
		enterRule(_localctx, 36, RULE_integerAttribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(287);
			_la = _input.LA(1);
			if (_la==DOC) {
				{
				setState(286); doc();
				}
			}

			setState(289);
			_la = _input.LA(1);
			if ( !(_la==VAR || _la==PROPERTY) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			setState(290); match(INT_TYPE);
			setState(302);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				{
				{
				setState(291); match(IDENTIFIER);
				setState(294);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(292); match(COLON);
					setState(293); integerValue();
					}
				}

				}
				}
				break;
			case LIST:
				{
				{
				setState(296); match(LIST);
				setState(297); match(IDENTIFIER);
				setState(300);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(298); match(COLON);
					setState(299); integerList();
					}
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

	public static class DoubleAttributeContext extends ParserRuleContext {
		public TerminalNode DOUBLE_TYPE() { return getToken(TaraGrammar.DOUBLE_TYPE, 0); }
		public DoubleListContext doubleList() {
			return getRuleContext(DoubleListContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
		public TerminalNode PROPERTY() { return getToken(TaraGrammar.PROPERTY, 0); }
		public TerminalNode VAR() { return getToken(TaraGrammar.VAR, 0); }
		public DoubleValueContext doubleValue() {
			return getRuleContext(DoubleValueContext.class,0);
		}
		public DocContext doc() {
			return getRuleContext(DocContext.class,0);
		}
		public TerminalNode COLON() { return getToken(TaraGrammar.COLON, 0); }
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
		enterRule(_localctx, 38, RULE_doubleAttribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(305);
			_la = _input.LA(1);
			if (_la==DOC) {
				{
				setState(304); doc();
				}
			}

			setState(307);
			_la = _input.LA(1);
			if ( !(_la==VAR || _la==PROPERTY) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			setState(308); match(DOUBLE_TYPE);
			setState(320);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				{
				{
				setState(309); match(IDENTIFIER);
				setState(312);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(310); match(COLON);
					setState(311); doubleValue();
					}
				}

				}
				}
				break;
			case LIST:
				{
				{
				setState(314); match(LIST);
				setState(315); match(IDENTIFIER);
				setState(318);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(316); match(COLON);
					setState(317); doubleList();
					}
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

	public static class ResourceContext extends ParserRuleContext {
		public TerminalNode RESOURCE() { return getToken(TaraGrammar.RESOURCE, 0); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(TaraGrammar.IDENTIFIER, i);
		}
		public List<TerminalNode> IDENTIFIER() { return getTokens(TaraGrammar.IDENTIFIER); }
		public TerminalNode PROPERTY() { return getToken(TaraGrammar.PROPERTY, 0); }
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
		enterRule(_localctx, 40, RULE_resource);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(323);
			_la = _input.LA(1);
			if (_la==DOC) {
				{
				setState(322); doc();
				}
			}

			setState(325);
			_la = _input.LA(1);
			if ( !(_la==VAR || _la==PROPERTY) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			setState(326); match(RESOURCE);
			setState(327); match(COLON);
			setState(328); match(IDENTIFIER);
			setState(329); match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
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
		public IdentifierReferenceContext identifierReference() {
			return getRuleContext(IdentifierReferenceContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
		public TerminalNode VAR() { return getToken(TaraGrammar.VAR, 0); }
		public DocContext doc() {
			return getRuleContext(DocContext.class,0);
		}
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
			setState(332);
			_la = _input.LA(1);
			if (_la==DOC) {
				{
				setState(331); doc();
				}
			}

			setState(334); match(VAR);
			setState(335); identifierReference();
			setState(337);
			_la = _input.LA(1);
			if (_la==LIST) {
				{
				setState(336); match(LIST);
				}
			}

			setState(339); match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
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
		public TerminalNode DEDENT() { return getToken(TaraGrammar.DEDENT, 0); }
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
		enterRule(_localctx, 44, RULE_word);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(341); match(VAR);
			setState(342); match(WORD);
			setState(343); match(IDENTIFIER);
			setState(344); match(NEW_LINE_INDENT);
			setState(347); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(345); match(IDENTIFIER);
				setState(346); match(NEWLINE);
				}
				}
				setState(349); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==IDENTIFIER );
			setState(351); match(DEDENT);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 46, RULE_naturalValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(353); match(POSITIVE_VALUE);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 48, RULE_integerValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(355);
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
		enterRule(_localctx, 50, RULE_doubleValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(357);
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
		enterRule(_localctx, 52, RULE_booleanValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(359); match(BOOLEAN_VALUE);
			}
		}
		catch (RecognitionException re) {
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
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(361); match(STRING_VALUE);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 56, RULE_stringList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(363); match(LEFT_SQUARE);
			setState(365); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(364); match(STRING_VALUE);
				}
				}
				setState(367); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==STRING_VALUE );
			setState(369); match(RIGHT_SQUARE);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 58, RULE_booleanList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(371); match(LEFT_SQUARE);
			setState(373); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(372); match(BOOLEAN_VALUE);
				}
				}
				setState(375); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==BOOLEAN_VALUE );
			setState(377); match(RIGHT_SQUARE);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 60, RULE_naturalList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(379); match(LEFT_SQUARE);
			setState(381); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(380); match(POSITIVE_VALUE);
				}
				}
				setState(383); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==POSITIVE_VALUE );
			setState(385); match(RIGHT_SQUARE);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 62, RULE_integerList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(387); match(LEFT_SQUARE);
			setState(389); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(388);
				_la = _input.LA(1);
				if ( !(_la==POSITIVE_VALUE || _la==NEGATIVE_VALUE) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				}
				}
				setState(391); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==POSITIVE_VALUE || _la==NEGATIVE_VALUE );
			setState(393); match(RIGHT_SQUARE);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 64, RULE_doubleList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(395); match(LEFT_SQUARE);
			setState(397); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(396);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << POSITIVE_VALUE) | (1L << NEGATIVE_VALUE) | (1L << DOUBLE_VALUE))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				}
				}
				setState(399); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << POSITIVE_VALUE) | (1L << NEGATIVE_VALUE) | (1L << DOUBLE_VALUE))) != 0) );
			setState(401); match(RIGHT_SQUARE);
			}
		}
		catch (RecognitionException re) {
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
		public TerminalNode ROOT(int i) {
			return getToken(TaraGrammar.ROOT, i);
		}
		public List<TerminalNode> HAS_NAME() { return getTokens(TaraGrammar.HAS_NAME); }
		public TerminalNode GENERIC(int i) {
			return getToken(TaraGrammar.GENERIC, i);
		}
		public List<TerminalNode> REQUIRED() { return getTokens(TaraGrammar.REQUIRED); }
		public TerminalNode CLOSE_AN() { return getToken(TaraGrammar.CLOSE_AN, 0); }
		public TerminalNode MULTIPLE(int i) {
			return getToken(TaraGrammar.MULTIPLE, i);
		}
		public List<TerminalNode> GENERIC() { return getTokens(TaraGrammar.GENERIC); }
		public TerminalNode INTENTION(int i) {
			return getToken(TaraGrammar.INTENTION, i);
		}
		public List<TerminalNode> MULTIPLE() { return getTokens(TaraGrammar.MULTIPLE); }
		public TerminalNode REQUIRED(int i) {
			return getToken(TaraGrammar.REQUIRED, i);
		}
		public List<TerminalNode> INTENTION() { return getTokens(TaraGrammar.INTENTION); }
		public TerminalNode SINGLETON(int i) {
			return getToken(TaraGrammar.SINGLETON, i);
		}
		public List<TerminalNode> SINGLETON() { return getTokens(TaraGrammar.SINGLETON); }
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
		enterRule(_localctx, 66, RULE_annotations);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(403); match(OPEN_AN);
			setState(405); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(404);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MULTIPLE) | (1L << REQUIRED) | (1L << ROOT) | (1L << SINGLETON) | (1L << GENERIC) | (1L << INTENTION) | (1L << HAS_NAME))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				}
				}
				setState(407); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MULTIPLE) | (1L << REQUIRED) | (1L << ROOT) | (1L << SINGLETON) | (1L << GENERIC) | (1L << INTENTION) | (1L << HAS_NAME))) != 0) );
			setState(409); match(CLOSE_AN);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 68, RULE_headerReference);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(414);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,56,_ctx);
			while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(411); hierarchy();
					}
					} 
				}
				setState(416);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,56,_ctx);
			}
			setState(417); match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 70, RULE_identifierReference);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(422);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,57,_ctx);
			while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(419); hierarchy();
					}
					} 
				}
				setState(424);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,57,_ctx);
			}
			setState(425); match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 72, RULE_hierarchy);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(427); match(IDENTIFIER);
			setState(428); match(DOT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ModifierContext extends ParserRuleContext {
		public TerminalNode FINAL() { return getToken(TaraGrammar.FINAL, 0); }
		public TerminalNode ABSTRACT() { return getToken(TaraGrammar.ABSTRACT, 0); }
		public TerminalNode BASE() { return getToken(TaraGrammar.BASE, 0); }
		public ModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_modifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterModifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitModifier(this);
		}
	}

	public final ModifierContext modifier() throws RecognitionException {
		ModifierContext _localctx = new ModifierContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_modifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(430);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << FINAL) | (1L << ABSTRACT) | (1L << BASE))) != 0)) ) {
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
		enterRule(_localctx, 76, RULE_doc);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(433); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(432); match(DOC);
				}
				}
				setState(435); 
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3;\u01b8\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\3\2\7\2R\n\2\f\2\16"+
		"\2U\13\2\3\2\5\2X\n\2\3\2\7\2[\n\2\f\2\16\2^\13\2\3\2\3\2\7\2b\n\2\f\2"+
		"\16\2e\13\2\3\2\3\2\3\3\5\3j\n\3\3\3\7\3m\n\3\f\3\16\3p\13\3\3\4\3\4\3"+
		"\4\3\4\3\5\3\5\3\5\3\6\5\6z\n\6\3\6\3\6\5\6~\n\6\3\6\5\6\u0081\n\6\3\7"+
		"\3\7\3\7\3\7\5\7\u0087\n\7\3\7\3\7\3\7\3\7\3\7\5\7\u008e\n\7\3\7\5\7\u0091"+
		"\n\7\5\7\u0093\n\7\3\7\5\7\u0096\n\7\3\b\3\b\5\b\u009a\n\b\3\b\3\b\3\t"+
		"\5\t\u009f\n\t\3\t\3\t\3\t\5\t\u00a4\n\t\3\t\7\t\u00a7\n\t\f\t\16\t\u00aa"+
		"\13\t\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\5\13\u00bb\n\13\3\f\3\f\3\f\6\f\u00c0\n\f\r\f\16\f\u00c1\6\f"+
		"\u00c4\n\f\r\f\16\f\u00c5\3\f\3\f\3\r\3\r\5\r\u00cc\n\r\3\16\3\16\6\16"+
		"\u00d0\n\16\r\16\16\16\u00d1\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3"+
		"\17\3\17\3\17\5\17\u00df\n\17\3\20\5\20\u00e2\n\20\3\20\3\20\3\20\3\20"+
		"\3\20\5\20\u00e9\n\20\3\21\5\21\u00ec\n\21\3\21\3\21\3\21\3\21\3\21\5"+
		"\21\u00f3\n\21\3\21\3\21\3\21\3\21\5\21\u00f9\n\21\5\21\u00fb\n\21\3\22"+
		"\5\22\u00fe\n\22\3\22\3\22\3\22\3\22\3\22\5\22\u0105\n\22\3\22\3\22\3"+
		"\22\3\22\5\22\u010b\n\22\5\22\u010d\n\22\3\23\5\23\u0110\n\23\3\23\3\23"+
		"\3\23\3\23\3\23\5\23\u0117\n\23\3\23\3\23\3\23\3\23\5\23\u011d\n\23\5"+
		"\23\u011f\n\23\3\24\5\24\u0122\n\24\3\24\3\24\3\24\3\24\3\24\5\24\u0129"+
		"\n\24\3\24\3\24\3\24\3\24\5\24\u012f\n\24\5\24\u0131\n\24\3\25\5\25\u0134"+
		"\n\25\3\25\3\25\3\25\3\25\3\25\5\25\u013b\n\25\3\25\3\25\3\25\3\25\5\25"+
		"\u0141\n\25\5\25\u0143\n\25\3\26\5\26\u0146\n\26\3\26\3\26\3\26\3\26\3"+
		"\26\3\26\3\27\5\27\u014f\n\27\3\27\3\27\3\27\5\27\u0154\n\27\3\27\3\27"+
		"\3\30\3\30\3\30\3\30\3\30\3\30\6\30\u015e\n\30\r\30\16\30\u015f\3\30\3"+
		"\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\6\36\u0170"+
		"\n\36\r\36\16\36\u0171\3\36\3\36\3\37\3\37\6\37\u0178\n\37\r\37\16\37"+
		"\u0179\3\37\3\37\3 \3 \6 \u0180\n \r \16 \u0181\3 \3 \3!\3!\6!\u0188\n"+
		"!\r!\16!\u0189\3!\3!\3\"\3\"\6\"\u0190\n\"\r\"\16\"\u0191\3\"\3\"\3#\3"+
		"#\6#\u0198\n#\r#\16#\u0199\3#\3#\3$\7$\u019f\n$\f$\16$\u01a2\13$\3$\3"+
		"$\3%\7%\u01a7\n%\f%\16%\u01aa\13%\3%\3%\3&\3&\3&\3\'\3\'\3(\6(\u01b4\n"+
		"(\r(\16(\u01b5\3(\2\2)\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,"+
		".\60\62\64\668:<>@BDFHJLN\2\7\3\2\f\r\3\2-.\3\2-/\3\2\5\13\3\2\20\22\u01dd"+
		"\2S\3\2\2\2\4i\3\2\2\2\6q\3\2\2\2\bu\3\2\2\2\ny\3\2\2\2\f\u0092\3\2\2"+
		"\2\16\u0097\3\2\2\2\20\u009e\3\2\2\2\22\u00ab\3\2\2\2\24\u00ba\3\2\2\2"+
		"\26\u00bc\3\2\2\2\30\u00cb\3\2\2\2\32\u00cd\3\2\2\2\34\u00de\3\2\2\2\36"+
		"\u00e1\3\2\2\2 \u00eb\3\2\2\2\"\u00fd\3\2\2\2$\u010f\3\2\2\2&\u0121\3"+
		"\2\2\2(\u0133\3\2\2\2*\u0145\3\2\2\2,\u014e\3\2\2\2.\u0157\3\2\2\2\60"+
		"\u0163\3\2\2\2\62\u0165\3\2\2\2\64\u0167\3\2\2\2\66\u0169\3\2\2\28\u016b"+
		"\3\2\2\2:\u016d\3\2\2\2<\u0175\3\2\2\2>\u017d\3\2\2\2@\u0185\3\2\2\2B"+
		"\u018d\3\2\2\2D\u0195\3\2\2\2F\u01a0\3\2\2\2H\u01a8\3\2\2\2J\u01ad\3\2"+
		"\2\2L\u01b0\3\2\2\2N\u01b3\3\2\2\2PR\7\64\2\2QP\3\2\2\2RU\3\2\2\2SQ\3"+
		"\2\2\2ST\3\2\2\2TW\3\2\2\2US\3\2\2\2VX\5\4\3\2WV\3\2\2\2WX\3\2\2\2X\\"+
		"\3\2\2\2Y[\7\64\2\2ZY\3\2\2\2[^\3\2\2\2\\Z\3\2\2\2\\]\3\2\2\2]_\3\2\2"+
		"\2^\\\3\2\2\2_c\5\n\6\2`b\7\64\2\2a`\3\2\2\2be\3\2\2\2ca\3\2\2\2cd\3\2"+
		"\2\2df\3\2\2\2ec\3\2\2\2fg\7\2\2\3g\3\3\2\2\2hj\5\b\5\2ih\3\2\2\2ij\3"+
		"\2\2\2jn\3\2\2\2km\5\6\4\2lk\3\2\2\2mp\3\2\2\2nl\3\2\2\2no\3\2\2\2o\5"+
		"\3\2\2\2pn\3\2\2\2qr\7\64\2\2rs\7\23\2\2st\5F$\2t\7\3\2\2\2uv\7\24\2\2"+
		"vw\5F$\2w\t\3\2\2\2xz\5N(\2yx\3\2\2\2yz\3\2\2\2z{\3\2\2\2{}\5\f\7\2|~"+
		"\5D#\2}|\3\2\2\2}~\3\2\2\2~\u0080\3\2\2\2\177\u0081\5\26\f\2\u0080\177"+
		"\3\2\2\2\u0080\u0081\3\2\2\2\u0081\13\3\2\2\2\u0082\u0083\7\4\2\2\u0083"+
		"\u0093\7\61\2\2\u0084\u0086\7\3\2\2\u0085\u0087\5L\'\2\u0086\u0085\3\2"+
		"\2\2\u0086\u0087\3\2\2\2\u0087\u0088\3\2\2\2\u0088\u0093\7\61\2\2\u0089"+
		"\u008a\7\3\2\2\u008a\u008b\7\36\2\2\u008b\u008d\5H%\2\u008c\u008e\5L\'"+
		"\2\u008d\u008c\3\2\2\2\u008d\u008e\3\2\2\2\u008e\u0090\3\2\2\2\u008f\u0091"+
		"\7\61\2\2\u0090\u008f\3\2\2\2\u0090\u0091\3\2\2\2\u0091\u0093\3\2\2\2"+
		"\u0092\u0082\3\2\2\2\u0092\u0084\3\2\2\2\u0092\u0089\3\2\2\2\u0093\u0095"+
		"\3\2\2\2\u0094\u0096\5\16\b\2\u0095\u0094\3\2\2\2\u0095\u0096\3\2\2\2"+
		"\u0096\r\3\2\2\2\u0097\u0099\7\30\2\2\u0098\u009a\5\20\t\2\u0099\u0098"+
		"\3\2\2\2\u0099\u009a\3\2\2\2\u009a\u009b\3\2\2\2\u009b\u009c\7\31\2\2"+
		"\u009c\17\3\2\2\2\u009d\u009f\5\22\n\2\u009e\u009d\3\2\2\2\u009e\u009f"+
		"\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0\u00a8\5\24\13\2\u00a1\u00a3\7\37\2"+
		"\2\u00a2\u00a4\5\22\n\2\u00a3\u00a2\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4"+
		"\u00a5\3\2\2\2\u00a5\u00a7\5\24\13\2\u00a6\u00a1\3\2\2\2\u00a7\u00aa\3"+
		"\2\2\2\u00a8\u00a6\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9\21\3\2\2\2\u00aa"+
		"\u00a8\3\2\2\2\u00ab\u00ac\7\61\2\2\u00ac\u00ad\7\36\2\2\u00ad\23\3\2"+
		"\2\2\u00ae\u00bb\5H%\2\u00af\u00bb\58\35\2\u00b0\u00bb\5\66\34\2\u00b1"+
		"\u00bb\5\60\31\2\u00b2\u00bb\5\62\32\2\u00b3\u00bb\5\64\33\2\u00b4\u00bb"+
		"\5:\36\2\u00b5\u00bb\5<\37\2\u00b6\u00bb\5> \2\u00b7\u00bb\5@!\2\u00b8"+
		"\u00bb\5B\"\2\u00b9\u00bb\5\32\16\2\u00ba\u00ae\3\2\2\2\u00ba\u00af\3"+
		"\2\2\2\u00ba\u00b0\3\2\2\2\u00ba\u00b1\3\2\2\2\u00ba\u00b2\3\2\2\2\u00ba"+
		"\u00b3\3\2\2\2\u00ba\u00b4\3\2\2\2\u00ba\u00b5\3\2\2\2\u00ba\u00b6\3\2"+
		"\2\2\u00ba\u00b7\3\2\2\2\u00ba\u00b8\3\2\2\2\u00ba\u00b9\3\2\2\2\u00bb"+
		"\25\3\2\2\2\u00bc\u00c3\79\2\2\u00bd\u00bf\5\30\r\2\u00be\u00c0\7\64\2"+
		"\2\u00bf\u00be\3\2\2\2\u00c0\u00c1\3\2\2\2\u00c1\u00bf\3\2\2\2\u00c1\u00c2"+
		"\3\2\2\2\u00c2\u00c4\3\2\2\2\u00c3\u00bd\3\2\2\2\u00c4\u00c5\3\2\2\2\u00c5"+
		"\u00c3\3\2\2\2\u00c5\u00c6\3\2\2\2\u00c6\u00c7\3\2\2\2\u00c7\u00c8\7:"+
		"\2\2\u00c8\27\3\2\2\2\u00c9\u00cc\5\34\17\2\u00ca\u00cc\5\n\6\2\u00cb"+
		"\u00c9\3\2\2\2\u00cb\u00ca\3\2\2\2\u00cc\31\3\2\2\2\u00cd\u00cf\7\26\2"+
		"\2\u00ce\u00d0\7\61\2\2\u00cf\u00ce\3\2\2\2\u00d0\u00d1\3\2\2\2\u00d1"+
		"\u00cf\3\2\2\2\u00d1\u00d2\3\2\2\2\u00d2\u00d3\3\2\2\2\u00d3\u00d4\7\27"+
		"\2\2\u00d4\33\3\2\2\2\u00d5\u00df\5\36\20\2\u00d6\u00df\5$\23\2\u00d7"+
		"\u00df\5&\24\2\u00d8\u00df\5(\25\2\u00d9\u00df\5 \21\2\u00da\u00df\5\""+
		"\22\2\u00db\u00df\5*\26\2\u00dc\u00df\5,\27\2\u00dd\u00df\5.\30\2\u00de"+
		"\u00d5\3\2\2\2\u00de\u00d6\3\2\2\2\u00de\u00d7\3\2\2\2\u00de\u00d8\3\2"+
		"\2\2\u00de\u00d9\3\2\2\2\u00de\u00da\3\2\2\2\u00de\u00db\3\2\2\2\u00de"+
		"\u00dc\3\2\2\2\u00de\u00dd\3\2\2\2\u00df\35\3\2\2\2\u00e0\u00e2\5N(\2"+
		"\u00e1\u00e0\3\2\2\2\u00e1\u00e2\3\2\2\2\u00e2\u00e3\3\2\2\2\u00e3\u00e4"+
		"\t\2\2\2\u00e4\u00e5\7&\2\2\u00e5\u00e8\7\61\2\2\u00e6\u00e7\7\36\2\2"+
		"\u00e7\u00e9\58\35\2\u00e8\u00e6\3\2\2\2\u00e8\u00e9\3\2\2\2\u00e9\37"+
		"\3\2\2\2\u00ea\u00ec\5N(\2\u00eb\u00ea\3\2\2\2\u00eb\u00ec\3\2\2\2\u00ec"+
		"\u00ed\3\2\2\2\u00ed\u00ee\t\2\2\2\u00ee\u00fa\7+\2\2\u00ef\u00f2\7\61"+
		"\2\2\u00f0\u00f1\7\36\2\2\u00f1\u00f3\5\66\34\2\u00f2\u00f0\3\2\2\2\u00f2"+
		"\u00f3\3\2\2\2\u00f3\u00fb\3\2\2\2\u00f4\u00f5\7\25\2\2\u00f5\u00f8\7"+
		"\61\2\2\u00f6\u00f7\7\36\2\2\u00f7\u00f9\5<\37\2\u00f8\u00f6\3\2\2\2\u00f8"+
		"\u00f9\3\2\2\2\u00f9\u00fb\3\2\2\2\u00fa\u00ef\3\2\2\2\u00fa\u00f4\3\2"+
		"\2\2\u00fb!\3\2\2\2\u00fc\u00fe\5N(\2\u00fd\u00fc\3\2\2\2\u00fd\u00fe"+
		"\3\2\2\2\u00fe\u00ff\3\2\2\2\u00ff\u0100\t\2\2\2\u0100\u010c\7*\2\2\u0101"+
		"\u0104\7\61\2\2\u0102\u0103\7\36\2\2\u0103\u0105\58\35\2\u0104\u0102\3"+
		"\2\2\2\u0104\u0105\3\2\2\2\u0105\u010d\3\2\2\2\u0106\u0107\7\25\2\2\u0107"+
		"\u010a\7\61\2\2\u0108\u0109\7\36\2\2\u0109\u010b\5:\36\2\u010a\u0108\3"+
		"\2\2\2\u010a\u010b\3\2\2\2\u010b\u010d\3\2\2\2\u010c\u0101\3\2\2\2\u010c"+
		"\u0106\3\2\2\2\u010d#\3\2\2\2\u010e\u0110\5N(\2\u010f\u010e\3\2\2\2\u010f"+
		"\u0110\3\2\2\2\u0110\u0111\3\2\2\2\u0111\u0112\t\2\2\2\u0112\u011e\7("+
		"\2\2\u0113\u0116\7\61\2\2\u0114\u0115\7\36\2\2\u0115\u0117\5\60\31\2\u0116"+
		"\u0114\3\2\2\2\u0116\u0117\3\2\2\2\u0117\u011f\3\2\2\2\u0118\u0119\7\25"+
		"\2\2\u0119\u011c\7\61\2\2\u011a\u011b\7\36\2\2\u011b\u011d\5> \2\u011c"+
		"\u011a\3\2\2\2\u011c\u011d\3\2\2\2\u011d\u011f\3\2\2\2\u011e\u0113\3\2"+
		"\2\2\u011e\u0118\3\2\2\2\u011f%\3\2\2\2\u0120\u0122\5N(\2\u0121\u0120"+
		"\3\2\2\2\u0121\u0122\3\2\2\2\u0122\u0123\3\2\2\2\u0123\u0124\t\2\2\2\u0124"+
		"\u0130\7\'\2\2\u0125\u0128\7\61\2\2\u0126\u0127\7\36\2\2\u0127\u0129\5"+
		"\62\32\2\u0128\u0126\3\2\2\2\u0128\u0129\3\2\2\2\u0129\u0131\3\2\2\2\u012a"+
		"\u012b\7\25\2\2\u012b\u012e\7\61\2\2\u012c\u012d\7\36\2\2\u012d\u012f"+
		"\5@!\2\u012e\u012c\3\2\2\2\u012e\u012f\3\2\2\2\u012f\u0131\3\2\2\2\u0130"+
		"\u0125\3\2\2\2\u0130\u012a\3\2\2\2\u0131\'\3\2\2\2\u0132\u0134\5N(\2\u0133"+
		"\u0132\3\2\2\2\u0133\u0134\3\2\2\2\u0134\u0135\3\2\2\2\u0135\u0136\t\2"+
		"\2\2\u0136\u0142\7)\2\2\u0137\u013a\7\61\2\2\u0138\u0139\7\36\2\2\u0139"+
		"\u013b\5\64\33\2\u013a\u0138\3\2\2\2\u013a\u013b\3\2\2\2\u013b\u0143\3"+
		"\2\2\2\u013c\u013d\7\25\2\2\u013d\u0140\7\61\2\2\u013e\u013f\7\36\2\2"+
		"\u013f\u0141\5B\"\2\u0140\u013e\3\2\2\2\u0140\u0141\3\2\2\2\u0141\u0143"+
		"\3\2\2\2\u0142\u0137\3\2\2\2\u0142\u013c\3\2\2\2\u0143)\3\2\2\2\u0144"+
		"\u0146\5N(\2\u0145\u0144\3\2\2\2\u0145\u0146\3\2\2\2\u0146\u0147\3\2\2"+
		"\2\u0147\u0148\t\2\2\2\u0148\u0149\7\17\2\2\u0149\u014a\7\36\2\2\u014a"+
		"\u014b\7\61\2\2\u014b\u014c\7\61\2\2\u014c+\3\2\2\2\u014d\u014f\5N(\2"+
		"\u014e\u014d\3\2\2\2\u014e\u014f\3\2\2\2\u014f\u0150\3\2\2\2\u0150\u0151"+
		"\7\f\2\2\u0151\u0153\5H%\2\u0152\u0154\7\25\2\2\u0153\u0152\3\2\2\2\u0153"+
		"\u0154\3\2\2\2\u0154\u0155\3\2\2\2\u0155\u0156\7\61\2\2\u0156-\3\2\2\2"+
		"\u0157\u0158\7\f\2\2\u0158\u0159\7\16\2\2\u0159\u015a\7\61\2\2\u015a\u015d"+
		"\79\2\2\u015b\u015c\7\61\2\2\u015c\u015e\7\64\2\2\u015d\u015b\3\2\2\2"+
		"\u015e\u015f\3\2\2\2\u015f\u015d\3\2\2\2\u015f\u0160\3\2\2\2\u0160\u0161"+
		"\3\2\2\2\u0161\u0162\7:\2\2\u0162/\3\2\2\2\u0163\u0164\7-\2\2\u0164\61"+
		"\3\2\2\2\u0165\u0166\t\3\2\2\u0166\63\3\2\2\2\u0167\u0168\t\4\2\2\u0168"+
		"\65\3\2\2\2\u0169\u016a\7,\2\2\u016a\67\3\2\2\2\u016b\u016c\7\60\2\2\u016c"+
		"9\3\2\2\2\u016d\u016f\7\26\2\2\u016e\u0170\7\60\2\2\u016f\u016e\3\2\2"+
		"\2\u0170\u0171\3\2\2\2\u0171\u016f\3\2\2\2\u0171\u0172\3\2\2\2\u0172\u0173"+
		"\3\2\2\2\u0173\u0174\7\27\2\2\u0174;\3\2\2\2\u0175\u0177\7\26\2\2\u0176"+
		"\u0178\7,\2\2\u0177\u0176\3\2\2\2\u0178\u0179\3\2\2\2\u0179\u0177\3\2"+
		"\2\2\u0179\u017a\3\2\2\2\u017a\u017b\3\2\2\2\u017b\u017c\7\27\2\2\u017c"+
		"=\3\2\2\2\u017d\u017f\7\26\2\2\u017e\u0180\7-\2\2\u017f\u017e\3\2\2\2"+
		"\u0180\u0181\3\2\2\2\u0181\u017f\3\2\2\2\u0181\u0182\3\2\2\2\u0182\u0183"+
		"\3\2\2\2\u0183\u0184\7\27\2\2\u0184?\3\2\2\2\u0185\u0187\7\26\2\2\u0186"+
		"\u0188\t\3\2\2\u0187\u0186\3\2\2\2\u0188\u0189\3\2\2\2\u0189\u0187\3\2"+
		"\2\2\u0189\u018a\3\2\2\2\u018a\u018b\3\2\2\2\u018b\u018c\7\27\2\2\u018c"+
		"A\3\2\2\2\u018d\u018f\7\26\2\2\u018e\u0190\t\4\2\2\u018f\u018e\3\2\2\2"+
		"\u0190\u0191\3\2\2\2\u0191\u018f\3\2\2\2\u0191\u0192\3\2\2\2\u0192\u0193"+
		"\3\2\2\2\u0193\u0194\7\27\2\2\u0194C\3\2\2\2\u0195\u0197\7\34\2\2\u0196"+
		"\u0198\t\5\2\2\u0197\u0196\3\2\2\2\u0198\u0199\3\2\2\2\u0199\u0197\3\2"+
		"\2\2\u0199\u019a\3\2\2\2\u019a\u019b\3\2\2\2\u019b\u019c\7\35\2\2\u019c"+
		"E\3\2\2\2\u019d\u019f\5J&\2\u019e\u019d\3\2\2\2\u019f\u01a2\3\2\2\2\u01a0"+
		"\u019e\3\2\2\2\u01a0\u01a1\3\2\2\2\u01a1\u01a3\3\2\2\2\u01a2\u01a0\3\2"+
		"\2\2\u01a3\u01a4\7\61\2\2\u01a4G\3\2\2\2\u01a5\u01a7\5J&\2\u01a6\u01a5"+
		"\3\2\2\2\u01a7\u01aa\3\2\2\2\u01a8\u01a6\3\2\2\2\u01a8\u01a9\3\2\2\2\u01a9"+
		"\u01ab\3\2\2\2\u01aa\u01a8\3\2\2\2\u01ab\u01ac\7\61\2\2\u01acI\3\2\2\2"+
		"\u01ad\u01ae\7\61\2\2\u01ae\u01af\7 \2\2\u01afK\3\2\2\2\u01b0\u01b1\t"+
		"\6\2\2\u01b1M\3\2\2\2\u01b2\u01b4\7\66\2\2\u01b3\u01b2\3\2\2\2\u01b4\u01b5"+
		"\3\2\2\2\u01b5\u01b3\3\2\2\2\u01b5\u01b6\3\2\2\2\u01b6O\3\2\2\2=SW\\c"+
		"iny}\u0080\u0086\u008d\u0090\u0092\u0095\u0099\u009e\u00a3\u00a8\u00ba"+
		"\u00c1\u00c5\u00cb\u00d1\u00de\u00e1\u00e8\u00eb\u00f2\u00f8\u00fa\u00fd"+
		"\u0104\u010a\u010c\u010f\u0116\u011c\u011e\u0121\u0128\u012e\u0130\u0133"+
		"\u013a\u0140\u0142\u0145\u014e\u0153\u015f\u0171\u0179\u0181\u0189\u0191"+
		"\u0199\u01a0\u01a8\u01b5";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}