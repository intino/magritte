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
		PACKAGE=16, RIGHT_SQUARE=19, STAR=32, NEGATIVE_VALUE=45, DOC=54, LETTER=51, 
		CASE=2, CLOSE_AN=25, DEDENT=58, BOOLEAN_VALUE=43, METAIDENTIFIER=1, RESOURCE=13, 
		MULTIPLE=4, UNKNOWN_TOKEN=59, WORD=12, OPEN_BRACKET=22, IMPORT=15, RIGHT_PARENTHESIS=21, 
		DASHES=34, COMMA=27, IDENTIFIER=49, LEFT_PARENTHESIS=20, STRING_MULTILINE_VALUE_KEY=48, 
		BASE=14, VAR=10, NL=56, DIGIT=50, INTENTION=8, DOT=28, STRING_VALUE=47, 
		DOUBLE_VALUE=46, POSITIVE=33, NEW_LINE_INDENT=57, DASH=35, PRIVATE=3, 
		TERMINAL=7, NATURAL_TYPE=38, HAS_NAME=9, DOUBLE_TYPE=39, BOOLEAN_TYPE=41, 
		SEMICOLON=31, REQUIRED=5, INT_TYPE=37, ROOT=6, OPEN_AN=24, NAMESPACE=17, 
		EMPTY=42, COLON=26, ALIAS_TYPE=36, POSITIVE_VALUE=44, NEWLINE=52, PROPERTY=11, 
		SPACES=53, SP=55, APHOSTROPHE=30, STRING_TYPE=40, ASSIGN=29, LEFT_SQUARE=18, 
		CLOSE_BRACKET=23;
	public static final String[] tokenNames = {
		"<INVALID>", "'Concept'", "'case'", "'private'", "'multiple'", "'required'", 
		"'root'", "'terminal'", "'intention'", "'has-name'", "'var'", "'property'", 
		"'Word'", "'Resource'", "'base'", "'import'", "'package'", "'namespace'", 
		"'['", "']'", "'('", "')'", "'{'", "'}'", "'<'", "'>'", "':'", "','", 
		"'.'", "'='", "'''", "SEMICOLON", "'*'", "'+'", "DASHES", "'-'", "'Alias'", 
		"'Integer'", "'Natural'", "'Double'", "'String'", "'Boolean'", "'empty'", 
		"BOOLEAN_VALUE", "POSITIVE_VALUE", "NEGATIVE_VALUE", "DOUBLE_VALUE", "STRING_VALUE", 
		"STRING_MULTILINE_VALUE_KEY", "IDENTIFIER", "DIGIT", "LETTER", "NEWLINE", 
		"SPACES", "DOC", "SP", "NL", "'indent'", "'dedent'", "UNKNOWN_TOKEN"
	};
	public static final int
		RULE_root = 0, RULE_header = 1, RULE_namespace = 2, RULE_imports = 3, 
		RULE_packet = 4, RULE_module = 5, RULE_concept = 6, RULE_signature = 7, 
		RULE_metaidentifier = 8, RULE_parameters = 9, RULE_parameterList = 10, 
		RULE_explicit = 11, RULE_parameter = 12, RULE_body = 13, RULE_conceptConstituents = 14, 
		RULE_identifierList = 15, RULE_varInit = 16, RULE_attribute = 17, RULE_aliasAttribute = 18, 
		RULE_booleanAttribute = 19, RULE_stringAttribute = 20, RULE_naturalAttribute = 21, 
		RULE_integerAttribute = 22, RULE_doubleAttribute = 23, RULE_resource = 24, 
		RULE_reference = 25, RULE_word = 26, RULE_naturalValue = 27, RULE_integerValue = 28, 
		RULE_doubleValue = 29, RULE_booleanValue = 30, RULE_stringValue = 31, 
		RULE_stringList = 32, RULE_booleanList = 33, RULE_naturalList = 34, RULE_integerList = 35, 
		RULE_doubleList = 36, RULE_annotations = 37, RULE_headerReference = 38, 
		RULE_identifierReference = 39, RULE_hierarchy = 40, RULE_doc = 41;
	public static final String[] ruleNames = {
		"root", "header", "namespace", "imports", "packet", "module", "concept", 
		"signature", "metaidentifier", "parameters", "parameterList", "explicit", 
		"parameter", "body", "conceptConstituents", "identifierList", "varInit", 
		"attribute", "aliasAttribute", "booleanAttribute", "stringAttribute", 
		"naturalAttribute", "integerAttribute", "doubleAttribute", "resource", 
		"reference", "word", "naturalValue", "integerValue", "doubleValue", "booleanValue", 
		"stringValue", "stringList", "booleanList", "naturalList", "integerList", 
		"doubleList", "annotations", "headerReference", "identifierReference", 
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
			setState(87);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(84); match(NEWLINE);
					}
					} 
				}
				setState(89);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			}
			setState(91);
			_la = _input.LA(1);
			if (_la==NAMESPACE) {
				{
				setState(90); header();
				}
			}

			setState(96);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(93); match(NEWLINE);
				}
				}
				setState(98);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(99); concept();
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
			setState(106); match(EOF);
			}
		}
		catch (RecognitionException re) {
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
		public List<TerminalNode> NEWLINE() { return getTokens(TaraGrammar.NEWLINE); }
		public List<ImportsContext> imports() {
			return getRuleContexts(ImportsContext.class);
		}
		public TerminalNode NEWLINE(int i) {
			return getToken(TaraGrammar.NEWLINE, i);
		}
		public NamespaceContext namespace() {
			return getRuleContext(NamespaceContext.class,0);
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
			setState(108); namespace();
			setState(110); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(109); match(NEWLINE);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(112); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			} while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER );
			setState(115);
			_la = _input.LA(1);
			if (_la==PACKAGE) {
				{
				setState(114); packet();
				}
			}

			setState(120);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(117); imports();
					}
					} 
				}
				setState(122);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
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

	public static class NamespaceContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
		public TerminalNode NAMESPACE() { return getToken(TaraGrammar.NAMESPACE, 0); }
		public NamespaceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_namespace; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterNamespace(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitNamespace(this);
		}
	}

	public final NamespaceContext namespace() throws RecognitionException {
		NamespaceContext _localctx = new NamespaceContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_namespace);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(123); match(NAMESPACE);
			setState(124); match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 6, RULE_imports);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(126); match(NEWLINE);
			setState(127); match(IMPORT);
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

	public static class PacketContext extends ParserRuleContext {
		public HeaderReferenceContext headerReference() {
			return getRuleContext(HeaderReferenceContext.class,0);
		}
		public ModuleContext module() {
			return getRuleContext(ModuleContext.class,0);
		}
		public TerminalNode PACKAGE() { return getToken(TaraGrammar.PACKAGE, 0); }
		public TerminalNode COLON() { return getToken(TaraGrammar.COLON, 0); }
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
		enterRule(_localctx, 8, RULE_packet);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(130); match(PACKAGE);
			setState(134);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				{
				setState(131); module();
				setState(132); match(COLON);
				}
				break;
			}
			setState(136); headerReference();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ModuleContext extends ParserRuleContext {
		public List<TerminalNode> DOT() { return getTokens(TaraGrammar.DOT); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(TaraGrammar.IDENTIFIER, i);
		}
		public List<TerminalNode> IDENTIFIER() { return getTokens(TaraGrammar.IDENTIFIER); }
		public TerminalNode DOT(int i) {
			return getToken(TaraGrammar.DOT, i);
		}
		public ModuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_module; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterModule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitModule(this);
		}
	}

	public final ModuleContext module() throws RecognitionException {
		ModuleContext _localctx = new ModuleContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_module);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(142);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(138); match(IDENTIFIER);
					setState(139); match(DOT);
					}
					} 
				}
				setState(144);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			}
			setState(145); match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
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
			setState(148);
			_la = _input.LA(1);
			if (_la==DOC) {
				{
				setState(147); doc();
				}
			}

			setState(150); signature();
			setState(152);
			_la = _input.LA(1);
			if (_la==OPEN_AN) {
				{
				setState(151); annotations();
				}
			}

			setState(155);
			_la = _input.LA(1);
			if (_la==NEW_LINE_INDENT) {
				{
				setState(154); body();
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
		enterRule(_localctx, 14, RULE_signature);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(168);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				{
				{
				setState(157); match(CASE);
				setState(158); match(IDENTIFIER);
				}
				}
				break;
			case 2:
				{
				{
				setState(159); metaidentifier();
				setState(160); match(IDENTIFIER);
				}
				}
				break;
			case 3:
				{
				{
				setState(162); metaidentifier();
				setState(163); match(COLON);
				setState(164); identifierReference();
				setState(166);
				_la = _input.LA(1);
				if (_la==IDENTIFIER) {
					{
					setState(165); match(IDENTIFIER);
					}
				}

				}
				}
				break;
			}
			setState(171);
			_la = _input.LA(1);
			if (_la==LEFT_PARENTHESIS) {
				{
				setState(170); parameters();
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
		enterRule(_localctx, 16, RULE_metaidentifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(173);
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
		enterRule(_localctx, 18, RULE_parameters);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(175); match(LEFT_PARENTHESIS);
			setState(177);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LEFT_SQUARE) | (1L << BOOLEAN_VALUE) | (1L << POSITIVE_VALUE) | (1L << NEGATIVE_VALUE) | (1L << DOUBLE_VALUE) | (1L << STRING_VALUE) | (1L << STRING_MULTILINE_VALUE_KEY) | (1L << IDENTIFIER))) != 0)) {
				{
				setState(176); parameterList();
				}
			}

			setState(179); match(RIGHT_PARENTHESIS);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 20, RULE_parameterList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(182);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				{
				setState(181); explicit();
				}
				break;
			}
			setState(184); parameter();
			setState(192);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(185); match(COMMA);
				setState(187);
				switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
				case 1:
					{
					setState(186); explicit();
					}
					break;
				}
				setState(189); parameter();
				}
				}
				setState(194);
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
		enterRule(_localctx, 22, RULE_explicit);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(195); match(IDENTIFIER);
			setState(196); match(COLON);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 24, RULE_parameter);
		try {
			setState(210);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(198); identifierReference();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(199); stringValue();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(200); booleanValue();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(201); naturalValue();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(202); integerValue();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(203); doubleValue();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(204); stringList();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(205); booleanList();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(206); naturalList();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(207); integerList();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(208); doubleList();
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(209); identifierList();
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
		enterRule(_localctx, 26, RULE_body);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(212); match(NEW_LINE_INDENT);
			setState(219); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(213); conceptConstituents();
				setState(215); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(214); match(NEWLINE);
					}
					}
					setState(217); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NEWLINE );
				}
				}
				setState(221); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << METAIDENTIFIER) | (1L << CASE) | (1L << VAR) | (1L << IDENTIFIER) | (1L << DOC))) != 0) );
			setState(223); match(DEDENT);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 28, RULE_conceptConstituents);
		try {
			setState(228);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(225); attribute();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(226); concept();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(227); varInit();
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
		enterRule(_localctx, 30, RULE_identifierList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(230); match(LEFT_SQUARE);
			setState(232); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(231); match(IDENTIFIER);
				}
				}
				setState(234); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==IDENTIFIER );
			setState(236); match(RIGHT_SQUARE);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 32, RULE_varInit);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(238); match(IDENTIFIER);
			setState(239); match(COLON);
			setState(253);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				{
				setState(240); match(EMPTY);
				}
				break;
			case 2:
				{
				setState(241); identifierReference();
				}
				break;
			case 3:
				{
				setState(242); stringValue();
				}
				break;
			case 4:
				{
				setState(243); booleanValue();
				}
				break;
			case 5:
				{
				setState(244); naturalValue();
				}
				break;
			case 6:
				{
				setState(245); integerValue();
				}
				break;
			case 7:
				{
				setState(246); doubleValue();
				}
				break;
			case 8:
				{
				setState(247); identifierList();
				}
				break;
			case 9:
				{
				setState(248); stringList();
				}
				break;
			case 10:
				{
				setState(249); booleanList();
				}
				break;
			case 11:
				{
				setState(250); naturalList();
				}
				break;
			case 12:
				{
				setState(251); integerList();
				}
				break;
			case 13:
				{
				setState(252); doubleList();
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
		enterRule(_localctx, 34, RULE_attribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(264);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				{
				setState(255); aliasAttribute();
				}
				break;
			case 2:
				{
				setState(256); naturalAttribute();
				}
				break;
			case 3:
				{
				setState(257); integerAttribute();
				}
				break;
			case 4:
				{
				setState(258); doubleAttribute();
				}
				break;
			case 5:
				{
				setState(259); booleanAttribute();
				}
				break;
			case 6:
				{
				setState(260); stringAttribute();
				}
				break;
			case 7:
				{
				setState(261); resource();
				}
				break;
			case 8:
				{
				setState(262); reference();
				}
				break;
			case 9:
				{
				setState(263); word();
				}
				break;
			}
			setState(267);
			_la = _input.LA(1);
			if (_la==OPEN_AN) {
				{
				setState(266); annotations();
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
		enterRule(_localctx, 36, RULE_aliasAttribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(270);
			_la = _input.LA(1);
			if (_la==DOC) {
				{
				setState(269); doc();
				}
			}

			setState(272); match(VAR);
			setState(273); match(ALIAS_TYPE);
			setState(274); match(IDENTIFIER);
			setState(278);
			switch (_input.LA(1)) {
			case COLON:
				{
				setState(275); match(COLON);
				setState(276); stringValue();
				}
				break;
			case EMPTY:
				{
				setState(277); match(EMPTY);
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
		enterRule(_localctx, 38, RULE_booleanAttribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(281);
			_la = _input.LA(1);
			if (_la==DOC) {
				{
				setState(280); doc();
				}
			}

			setState(283); match(VAR);
			setState(284); match(BOOLEAN_TYPE);
			{
			setState(285); match(IDENTIFIER);
			setState(289);
			switch (_input.LA(1)) {
			case COLON:
				{
				setState(286); match(COLON);
				setState(287); booleanValue();
				}
				break;
			case EMPTY:
				{
				setState(288); match(EMPTY);
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
		enterRule(_localctx, 40, RULE_stringAttribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(292);
			_la = _input.LA(1);
			if (_la==DOC) {
				{
				setState(291); doc();
				}
			}

			setState(294); match(VAR);
			setState(295); match(STRING_TYPE);
			{
			setState(296); match(IDENTIFIER);
			setState(300);
			switch (_input.LA(1)) {
			case COLON:
				{
				setState(297); match(COLON);
				setState(298); stringValue();
				}
				break;
			case EMPTY:
				{
				setState(299); match(EMPTY);
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
		enterRule(_localctx, 42, RULE_naturalAttribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(303);
			_la = _input.LA(1);
			if (_la==DOC) {
				{
				setState(302); doc();
				}
			}

			setState(305); match(VAR);
			setState(306); match(NATURAL_TYPE);
			{
			setState(307); match(IDENTIFIER);
			setState(311);
			switch (_input.LA(1)) {
			case COLON:
				{
				setState(308); match(COLON);
				setState(309); naturalValue();
				}
				break;
			case EMPTY:
				{
				setState(310); match(EMPTY);
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
		enterRule(_localctx, 44, RULE_integerAttribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(314);
			_la = _input.LA(1);
			if (_la==DOC) {
				{
				setState(313); doc();
				}
			}

			setState(316); match(VAR);
			setState(317); match(INT_TYPE);
			{
			setState(318); match(IDENTIFIER);
			setState(322);
			switch (_input.LA(1)) {
			case COLON:
				{
				setState(319); match(COLON);
				setState(320); integerValue();
				}
				break;
			case EMPTY:
				{
				setState(321); match(EMPTY);
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
		enterRule(_localctx, 46, RULE_doubleAttribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(325);
			_la = _input.LA(1);
			if (_la==DOC) {
				{
				setState(324); doc();
				}
			}

			setState(327); match(VAR);
			setState(328); match(DOUBLE_TYPE);
			{
			setState(329); match(IDENTIFIER);
			setState(333);
			switch (_input.LA(1)) {
			case COLON:
				{
				setState(330); match(COLON);
				setState(331); doubleValue();
				}
				break;
			case EMPTY:
				{
				setState(332); match(EMPTY);
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
		enterRule(_localctx, 48, RULE_resource);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(336);
			_la = _input.LA(1);
			if (_la==DOC) {
				{
				setState(335); doc();
				}
			}

			setState(338); match(VAR);
			setState(339); match(RESOURCE);
			setState(340); match(COLON);
			setState(341); match(IDENTIFIER);
			setState(342); match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 50, RULE_reference);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(345);
			_la = _input.LA(1);
			if (_la==DOC) {
				{
				setState(344); doc();
				}
			}

			setState(347); match(VAR);
			setState(348); identifierReference();
			setState(349); match(IDENTIFIER);
			setState(352);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(350); match(COLON);
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
		enterRule(_localctx, 52, RULE_word);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(355);
			_la = _input.LA(1);
			if (_la==DOC) {
				{
				setState(354); doc();
				}
			}

			setState(357); match(VAR);
			setState(358); match(WORD);
			setState(359); match(IDENTIFIER);
			setState(360); match(NEW_LINE_INDENT);
			setState(366); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(361); match(IDENTIFIER);
				setState(363);
				_la = _input.LA(1);
				if (_la==STAR) {
					{
					setState(362); match(STAR);
					}
				}

				setState(365); match(NEWLINE);
				}
				}
				setState(368); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==IDENTIFIER );
			setState(370); match(DEDENT);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 54, RULE_naturalValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(372); match(POSITIVE_VALUE);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 56, RULE_integerValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(374);
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
		enterRule(_localctx, 58, RULE_doubleValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(376);
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
		enterRule(_localctx, 60, RULE_booleanValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(378); match(BOOLEAN_VALUE);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 62, RULE_stringValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(380);
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
		enterRule(_localctx, 64, RULE_stringList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(382); match(LEFT_SQUARE);
			setState(384); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(383); match(STRING_VALUE);
				}
				}
				setState(386); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==STRING_VALUE );
			setState(388); match(RIGHT_SQUARE);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 66, RULE_booleanList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(390); match(LEFT_SQUARE);
			setState(392); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(391); match(BOOLEAN_VALUE);
				}
				}
				setState(394); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==BOOLEAN_VALUE );
			setState(396); match(RIGHT_SQUARE);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 68, RULE_naturalList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(398); match(LEFT_SQUARE);
			setState(400); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(399); match(POSITIVE_VALUE);
				}
				}
				setState(402); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==POSITIVE_VALUE );
			setState(404); match(RIGHT_SQUARE);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 70, RULE_integerList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(406); match(LEFT_SQUARE);
			setState(408); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(407);
				_la = _input.LA(1);
				if ( !(_la==POSITIVE_VALUE || _la==NEGATIVE_VALUE) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				}
				}
				setState(410); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==POSITIVE_VALUE || _la==NEGATIVE_VALUE );
			setState(412); match(RIGHT_SQUARE);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 72, RULE_doubleList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(414); match(LEFT_SQUARE);
			setState(416); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(415);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << POSITIVE_VALUE) | (1L << NEGATIVE_VALUE) | (1L << DOUBLE_VALUE))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				}
				}
				setState(418); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << POSITIVE_VALUE) | (1L << NEGATIVE_VALUE) | (1L << DOUBLE_VALUE))) != 0) );
			setState(420); match(RIGHT_SQUARE);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 74, RULE_annotations);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(422); match(OPEN_AN);
			setState(424); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(423);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PRIVATE) | (1L << MULTIPLE) | (1L << REQUIRED) | (1L << ROOT) | (1L << TERMINAL) | (1L << INTENTION) | (1L << HAS_NAME) | (1L << PROPERTY))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				}
				}
				setState(426); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PRIVATE) | (1L << MULTIPLE) | (1L << REQUIRED) | (1L << ROOT) | (1L << TERMINAL) | (1L << INTENTION) | (1L << HAS_NAME) | (1L << PROPERTY))) != 0) );
			setState(428); match(CLOSE_AN);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 76, RULE_headerReference);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(433);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,51,_ctx);
			while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(430); hierarchy();
					}
					} 
				}
				setState(435);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,51,_ctx);
			}
			setState(436); match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 78, RULE_identifierReference);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(441);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,52,_ctx);
			while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(438); hierarchy();
					}
					} 
				}
				setState(443);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,52,_ctx);
			}
			setState(444); match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 80, RULE_hierarchy);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(446); match(IDENTIFIER);
			setState(447); match(DOT);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 82, RULE_doc);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(450); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(449); match(DOC);
				}
				}
				setState(452); 
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3=\u01c9\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\3"+
		"\2\7\2X\n\2\f\2\16\2[\13\2\3\2\5\2^\n\2\3\2\7\2a\n\2\f\2\16\2d\13\2\3"+
		"\2\3\2\7\2h\n\2\f\2\16\2k\13\2\3\2\3\2\3\3\3\3\6\3q\n\3\r\3\16\3r\3\3"+
		"\5\3v\n\3\3\3\7\3y\n\3\f\3\16\3|\13\3\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\6"+
		"\3\6\3\6\3\6\5\6\u0089\n\6\3\6\3\6\3\7\3\7\7\7\u008f\n\7\f\7\16\7\u0092"+
		"\13\7\3\7\3\7\3\b\5\b\u0097\n\b\3\b\3\b\5\b\u009b\n\b\3\b\5\b\u009e\n"+
		"\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u00a9\n\t\5\t\u00ab\n\t\3\t"+
		"\5\t\u00ae\n\t\3\n\3\n\3\13\3\13\5\13\u00b4\n\13\3\13\3\13\3\f\5\f\u00b9"+
		"\n\f\3\f\3\f\3\f\5\f\u00be\n\f\3\f\7\f\u00c1\n\f\f\f\16\f\u00c4\13\f\3"+
		"\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\5\16\u00d5\n\16\3\17\3\17\3\17\6\17\u00da\n\17\r\17\16\17\u00db\6\17"+
		"\u00de\n\17\r\17\16\17\u00df\3\17\3\17\3\20\3\20\3\20\5\20\u00e7\n\20"+
		"\3\21\3\21\6\21\u00eb\n\21\r\21\16\21\u00ec\3\21\3\21\3\22\3\22\3\22\3"+
		"\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\5\22\u0100"+
		"\n\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\5\23\u010b\n\23\3\23"+
		"\5\23\u010e\n\23\3\24\5\24\u0111\n\24\3\24\3\24\3\24\3\24\3\24\3\24\5"+
		"\24\u0119\n\24\3\25\5\25\u011c\n\25\3\25\3\25\3\25\3\25\3\25\3\25\5\25"+
		"\u0124\n\25\3\26\5\26\u0127\n\26\3\26\3\26\3\26\3\26\3\26\3\26\5\26\u012f"+
		"\n\26\3\27\5\27\u0132\n\27\3\27\3\27\3\27\3\27\3\27\3\27\5\27\u013a\n"+
		"\27\3\30\5\30\u013d\n\30\3\30\3\30\3\30\3\30\3\30\3\30\5\30\u0145\n\30"+
		"\3\31\5\31\u0148\n\31\3\31\3\31\3\31\3\31\3\31\3\31\5\31\u0150\n\31\3"+
		"\32\5\32\u0153\n\32\3\32\3\32\3\32\3\32\3\32\3\32\3\33\5\33\u015c\n\33"+
		"\3\33\3\33\3\33\3\33\3\33\5\33\u0163\n\33\3\34\5\34\u0166\n\34\3\34\3"+
		"\34\3\34\3\34\3\34\3\34\5\34\u016e\n\34\3\34\6\34\u0171\n\34\r\34\16\34"+
		"\u0172\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3\"\3\"\6\""+
		"\u0183\n\"\r\"\16\"\u0184\3\"\3\"\3#\3#\6#\u018b\n#\r#\16#\u018c\3#\3"+
		"#\3$\3$\6$\u0193\n$\r$\16$\u0194\3$\3$\3%\3%\6%\u019b\n%\r%\16%\u019c"+
		"\3%\3%\3&\3&\6&\u01a3\n&\r&\16&\u01a4\3&\3&\3\'\3\'\6\'\u01ab\n\'\r\'"+
		"\16\'\u01ac\3\'\3\'\3(\7(\u01b2\n(\f(\16(\u01b5\13(\3(\3(\3)\7)\u01ba"+
		"\n)\f)\16)\u01bd\13)\3)\3)\3*\3*\3*\3+\6+\u01c5\n+\r+\16+\u01c6\3+\2\2"+
		",\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDF"+
		"HJLNPRT\2\7\4\2\3\3\63\63\3\2./\3\2.\60\3\2\61\62\4\2\5\13\r\r\u01f8\2"+
		"Y\3\2\2\2\4n\3\2\2\2\6}\3\2\2\2\b\u0080\3\2\2\2\n\u0084\3\2\2\2\f\u0090"+
		"\3\2\2\2\16\u0096\3\2\2\2\20\u00aa\3\2\2\2\22\u00af\3\2\2\2\24\u00b1\3"+
		"\2\2\2\26\u00b8\3\2\2\2\30\u00c5\3\2\2\2\32\u00d4\3\2\2\2\34\u00d6\3\2"+
		"\2\2\36\u00e6\3\2\2\2 \u00e8\3\2\2\2\"\u00f0\3\2\2\2$\u010a\3\2\2\2&\u0110"+
		"\3\2\2\2(\u011b\3\2\2\2*\u0126\3\2\2\2,\u0131\3\2\2\2.\u013c\3\2\2\2\60"+
		"\u0147\3\2\2\2\62\u0152\3\2\2\2\64\u015b\3\2\2\2\66\u0165\3\2\2\28\u0176"+
		"\3\2\2\2:\u0178\3\2\2\2<\u017a\3\2\2\2>\u017c\3\2\2\2@\u017e\3\2\2\2B"+
		"\u0180\3\2\2\2D\u0188\3\2\2\2F\u0190\3\2\2\2H\u0198\3\2\2\2J\u01a0\3\2"+
		"\2\2L\u01a8\3\2\2\2N\u01b3\3\2\2\2P\u01bb\3\2\2\2R\u01c0\3\2\2\2T\u01c4"+
		"\3\2\2\2VX\7\66\2\2WV\3\2\2\2X[\3\2\2\2YW\3\2\2\2YZ\3\2\2\2Z]\3\2\2\2"+
		"[Y\3\2\2\2\\^\5\4\3\2]\\\3\2\2\2]^\3\2\2\2^b\3\2\2\2_a\7\66\2\2`_\3\2"+
		"\2\2ad\3\2\2\2b`\3\2\2\2bc\3\2\2\2ce\3\2\2\2db\3\2\2\2ei\5\16\b\2fh\7"+
		"\66\2\2gf\3\2\2\2hk\3\2\2\2ig\3\2\2\2ij\3\2\2\2jl\3\2\2\2ki\3\2\2\2lm"+
		"\7\2\2\3m\3\3\2\2\2np\5\6\4\2oq\7\66\2\2po\3\2\2\2qr\3\2\2\2rp\3\2\2\2"+
		"rs\3\2\2\2su\3\2\2\2tv\5\n\6\2ut\3\2\2\2uv\3\2\2\2vz\3\2\2\2wy\5\b\5\2"+
		"xw\3\2\2\2y|\3\2\2\2zx\3\2\2\2z{\3\2\2\2{\5\3\2\2\2|z\3\2\2\2}~\7\23\2"+
		"\2~\177\7\63\2\2\177\7\3\2\2\2\u0080\u0081\7\66\2\2\u0081\u0082\7\21\2"+
		"\2\u0082\u0083\5N(\2\u0083\t\3\2\2\2\u0084\u0088\7\22\2\2\u0085\u0086"+
		"\5\f\7\2\u0086\u0087\7\34\2\2\u0087\u0089\3\2\2\2\u0088\u0085\3\2\2\2"+
		"\u0088\u0089\3\2\2\2\u0089\u008a\3\2\2\2\u008a\u008b\5N(\2\u008b\13\3"+
		"\2\2\2\u008c\u008d\7\63\2\2\u008d\u008f\7\36\2\2\u008e\u008c\3\2\2\2\u008f"+
		"\u0092\3\2\2\2\u0090\u008e\3\2\2\2\u0090\u0091\3\2\2\2\u0091\u0093\3\2"+
		"\2\2\u0092\u0090\3\2\2\2\u0093\u0094\7\63\2\2\u0094\r\3\2\2\2\u0095\u0097"+
		"\5T+\2\u0096\u0095\3\2\2\2\u0096\u0097\3\2\2\2\u0097\u0098\3\2\2\2\u0098"+
		"\u009a\5\20\t\2\u0099\u009b\5L\'\2\u009a\u0099\3\2\2\2\u009a\u009b\3\2"+
		"\2\2\u009b\u009d\3\2\2\2\u009c\u009e\5\34\17\2\u009d\u009c\3\2\2\2\u009d"+
		"\u009e\3\2\2\2\u009e\17\3\2\2\2\u009f\u00a0\7\4\2\2\u00a0\u00ab\7\63\2"+
		"\2\u00a1\u00a2\5\22\n\2\u00a2\u00a3\7\63\2\2\u00a3\u00ab\3\2\2\2\u00a4"+
		"\u00a5\5\22\n\2\u00a5\u00a6\7\34\2\2\u00a6\u00a8\5P)\2\u00a7\u00a9\7\63"+
		"\2\2\u00a8\u00a7\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9\u00ab\3\2\2\2\u00aa"+
		"\u009f\3\2\2\2\u00aa\u00a1\3\2\2\2\u00aa\u00a4\3\2\2\2\u00ab\u00ad\3\2"+
		"\2\2\u00ac\u00ae\5\24\13\2\u00ad\u00ac\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae"+
		"\21\3\2\2\2\u00af\u00b0\t\2\2\2\u00b0\23\3\2\2\2\u00b1\u00b3\7\26\2\2"+
		"\u00b2\u00b4\5\26\f\2\u00b3\u00b2\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4\u00b5"+
		"\3\2\2\2\u00b5\u00b6\7\27\2\2\u00b6\25\3\2\2\2\u00b7\u00b9\5\30\r\2\u00b8"+
		"\u00b7\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9\u00ba\3\2\2\2\u00ba\u00c2\5\32"+
		"\16\2\u00bb\u00bd\7\35\2\2\u00bc\u00be\5\30\r\2\u00bd\u00bc\3\2\2\2\u00bd"+
		"\u00be\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf\u00c1\5\32\16\2\u00c0\u00bb\3"+
		"\2\2\2\u00c1\u00c4\3\2\2\2\u00c2\u00c0\3\2\2\2\u00c2\u00c3\3\2\2\2\u00c3"+
		"\27\3\2\2\2\u00c4\u00c2\3\2\2\2\u00c5\u00c6\7\63\2\2\u00c6\u00c7\7\34"+
		"\2\2\u00c7\31\3\2\2\2\u00c8\u00d5\5P)\2\u00c9\u00d5\5@!\2\u00ca\u00d5"+
		"\5> \2\u00cb\u00d5\58\35\2\u00cc\u00d5\5:\36\2\u00cd\u00d5\5<\37\2\u00ce"+
		"\u00d5\5B\"\2\u00cf\u00d5\5D#\2\u00d0\u00d5\5F$\2\u00d1\u00d5\5H%\2\u00d2"+
		"\u00d5\5J&\2\u00d3\u00d5\5 \21\2\u00d4\u00c8\3\2\2\2\u00d4\u00c9\3\2\2"+
		"\2\u00d4\u00ca\3\2\2\2\u00d4\u00cb\3\2\2\2\u00d4\u00cc\3\2\2\2\u00d4\u00cd"+
		"\3\2\2\2\u00d4\u00ce\3\2\2\2\u00d4\u00cf\3\2\2\2\u00d4\u00d0\3\2\2\2\u00d4"+
		"\u00d1\3\2\2\2\u00d4\u00d2\3\2\2\2\u00d4\u00d3\3\2\2\2\u00d5\33\3\2\2"+
		"\2\u00d6\u00dd\7;\2\2\u00d7\u00d9\5\36\20\2\u00d8\u00da\7\66\2\2\u00d9"+
		"\u00d8\3\2\2\2\u00da\u00db\3\2\2\2\u00db\u00d9\3\2\2\2\u00db\u00dc\3\2"+
		"\2\2\u00dc\u00de\3\2\2\2\u00dd\u00d7\3\2\2\2\u00de\u00df\3\2\2\2\u00df"+
		"\u00dd\3\2\2\2\u00df\u00e0\3\2\2\2\u00e0\u00e1\3\2\2\2\u00e1\u00e2\7<"+
		"\2\2\u00e2\35\3\2\2\2\u00e3\u00e7\5$\23\2\u00e4\u00e7\5\16\b\2\u00e5\u00e7"+
		"\5\"\22\2\u00e6\u00e3\3\2\2\2\u00e6\u00e4\3\2\2\2\u00e6\u00e5\3\2\2\2"+
		"\u00e7\37\3\2\2\2\u00e8\u00ea\7\24\2\2\u00e9\u00eb\7\63\2\2\u00ea\u00e9"+
		"\3\2\2\2\u00eb\u00ec\3\2\2\2\u00ec\u00ea\3\2\2\2\u00ec\u00ed\3\2\2\2\u00ed"+
		"\u00ee\3\2\2\2\u00ee\u00ef\7\25\2\2\u00ef!\3\2\2\2\u00f0\u00f1\7\63\2"+
		"\2\u00f1\u00ff\7\34\2\2\u00f2\u0100\7,\2\2\u00f3\u0100\5P)\2\u00f4\u0100"+
		"\5@!\2\u00f5\u0100\5> \2\u00f6\u0100\58\35\2\u00f7\u0100\5:\36\2\u00f8"+
		"\u0100\5<\37\2\u00f9\u0100\5 \21\2\u00fa\u0100\5B\"\2\u00fb\u0100\5D#"+
		"\2\u00fc\u0100\5F$\2\u00fd\u0100\5H%\2\u00fe\u0100\5J&\2\u00ff\u00f2\3"+
		"\2\2\2\u00ff\u00f3\3\2\2\2\u00ff\u00f4\3\2\2\2\u00ff\u00f5\3\2\2\2\u00ff"+
		"\u00f6\3\2\2\2\u00ff\u00f7\3\2\2\2\u00ff\u00f8\3\2\2\2\u00ff\u00f9\3\2"+
		"\2\2\u00ff\u00fa\3\2\2\2\u00ff\u00fb\3\2\2\2\u00ff\u00fc\3\2\2\2\u00ff"+
		"\u00fd\3\2\2\2\u00ff\u00fe\3\2\2\2\u0100#\3\2\2\2\u0101\u010b\5&\24\2"+
		"\u0102\u010b\5,\27\2\u0103\u010b\5.\30\2\u0104\u010b\5\60\31\2\u0105\u010b"+
		"\5(\25\2\u0106\u010b\5*\26\2\u0107\u010b\5\62\32\2\u0108\u010b\5\64\33"+
		"\2\u0109\u010b\5\66\34\2\u010a\u0101\3\2\2\2\u010a\u0102\3\2\2\2\u010a"+
		"\u0103\3\2\2\2\u010a\u0104\3\2\2\2\u010a\u0105\3\2\2\2\u010a\u0106\3\2"+
		"\2\2\u010a\u0107\3\2\2\2\u010a\u0108\3\2\2\2\u010a\u0109\3\2\2\2\u010b"+
		"\u010d\3\2\2\2\u010c\u010e\5L\'\2\u010d\u010c\3\2\2\2\u010d\u010e\3\2"+
		"\2\2\u010e%\3\2\2\2\u010f\u0111\5T+\2\u0110\u010f\3\2\2\2\u0110\u0111"+
		"\3\2\2\2\u0111\u0112\3\2\2\2\u0112\u0113\7\f\2\2\u0113\u0114\7&\2\2\u0114"+
		"\u0118\7\63\2\2\u0115\u0116\7\34\2\2\u0116\u0119\5@!\2\u0117\u0119\7,"+
		"\2\2\u0118\u0115\3\2\2\2\u0118\u0117\3\2\2\2\u0118\u0119\3\2\2\2\u0119"+
		"\'\3\2\2\2\u011a\u011c\5T+\2\u011b\u011a\3\2\2\2\u011b\u011c\3\2\2\2\u011c"+
		"\u011d\3\2\2\2\u011d\u011e\7\f\2\2\u011e\u011f\7+\2\2\u011f\u0123\7\63"+
		"\2\2\u0120\u0121\7\34\2\2\u0121\u0124\5> \2\u0122\u0124\7,\2\2\u0123\u0120"+
		"\3\2\2\2\u0123\u0122\3\2\2\2\u0123\u0124\3\2\2\2\u0124)\3\2\2\2\u0125"+
		"\u0127\5T+\2\u0126\u0125\3\2\2\2\u0126\u0127\3\2\2\2\u0127\u0128\3\2\2"+
		"\2\u0128\u0129\7\f\2\2\u0129\u012a\7*\2\2\u012a\u012e\7\63\2\2\u012b\u012c"+
		"\7\34\2\2\u012c\u012f\5@!\2\u012d\u012f\7,\2\2\u012e\u012b\3\2\2\2\u012e"+
		"\u012d\3\2\2\2\u012e\u012f\3\2\2\2\u012f+\3\2\2\2\u0130\u0132\5T+\2\u0131"+
		"\u0130\3\2\2\2\u0131\u0132\3\2\2\2\u0132\u0133\3\2\2\2\u0133\u0134\7\f"+
		"\2\2\u0134\u0135\7(\2\2\u0135\u0139\7\63\2\2\u0136\u0137\7\34\2\2\u0137"+
		"\u013a\58\35\2\u0138\u013a\7,\2\2\u0139\u0136\3\2\2\2\u0139\u0138\3\2"+
		"\2\2\u0139\u013a\3\2\2\2\u013a-\3\2\2\2\u013b\u013d\5T+\2\u013c\u013b"+
		"\3\2\2\2\u013c\u013d\3\2\2\2\u013d\u013e\3\2\2\2\u013e\u013f\7\f\2\2\u013f"+
		"\u0140\7\'\2\2\u0140\u0144\7\63\2\2\u0141\u0142\7\34\2\2\u0142\u0145\5"+
		":\36\2\u0143\u0145\7,\2\2\u0144\u0141\3\2\2\2\u0144\u0143\3\2\2\2\u0144"+
		"\u0145\3\2\2\2\u0145/\3\2\2\2\u0146\u0148\5T+\2\u0147\u0146\3\2\2\2\u0147"+
		"\u0148\3\2\2\2\u0148\u0149\3\2\2\2\u0149\u014a\7\f\2\2\u014a\u014b\7)"+
		"\2\2\u014b\u014f\7\63\2\2\u014c\u014d\7\34\2\2\u014d\u0150\5<\37\2\u014e"+
		"\u0150\7,\2\2\u014f\u014c\3\2\2\2\u014f\u014e\3\2\2\2\u014f\u0150\3\2"+
		"\2\2\u0150\61\3\2\2\2\u0151\u0153\5T+\2\u0152\u0151\3\2\2\2\u0152\u0153"+
		"\3\2\2\2\u0153\u0154\3\2\2\2\u0154\u0155\7\f\2\2\u0155\u0156\7\17\2\2"+
		"\u0156\u0157\7\34\2\2\u0157\u0158\7\63\2\2\u0158\u0159\7\63\2\2\u0159"+
		"\63\3\2\2\2\u015a\u015c\5T+\2\u015b\u015a\3\2\2\2\u015b\u015c\3\2\2\2"+
		"\u015c\u015d\3\2\2\2\u015d\u015e\7\f\2\2\u015e\u015f\5P)\2\u015f\u0162"+
		"\7\63\2\2\u0160\u0161\7\34\2\2\u0161\u0163\7,\2\2\u0162\u0160\3\2\2\2"+
		"\u0162\u0163\3\2\2\2\u0163\65\3\2\2\2\u0164\u0166\5T+\2\u0165\u0164\3"+
		"\2\2\2\u0165\u0166\3\2\2\2\u0166\u0167\3\2\2\2\u0167\u0168\7\f\2\2\u0168"+
		"\u0169\7\16\2\2\u0169\u016a\7\63\2\2\u016a\u0170\7;\2\2\u016b\u016d\7"+
		"\63\2\2\u016c\u016e\7\"\2\2\u016d\u016c\3\2\2\2\u016d\u016e\3\2\2\2\u016e"+
		"\u016f\3\2\2\2\u016f\u0171\7\66\2\2\u0170\u016b\3\2\2\2\u0171\u0172\3"+
		"\2\2\2\u0172\u0170\3\2\2\2\u0172\u0173\3\2\2\2\u0173\u0174\3\2\2\2\u0174"+
		"\u0175\7<\2\2\u0175\67\3\2\2\2\u0176\u0177\7.\2\2\u01779\3\2\2\2\u0178"+
		"\u0179\t\3\2\2\u0179;\3\2\2\2\u017a\u017b\t\4\2\2\u017b=\3\2\2\2\u017c"+
		"\u017d\7-\2\2\u017d?\3\2\2\2\u017e\u017f\t\5\2\2\u017fA\3\2\2\2\u0180"+
		"\u0182\7\24\2\2\u0181\u0183\7\61\2\2\u0182\u0181\3\2\2\2\u0183\u0184\3"+
		"\2\2\2\u0184\u0182\3\2\2\2\u0184\u0185\3\2\2\2\u0185\u0186\3\2\2\2\u0186"+
		"\u0187\7\25\2\2\u0187C\3\2\2\2\u0188\u018a\7\24\2\2\u0189\u018b\7-\2\2"+
		"\u018a\u0189\3\2\2\2\u018b\u018c\3\2\2\2\u018c\u018a\3\2\2\2\u018c\u018d"+
		"\3\2\2\2\u018d\u018e\3\2\2\2\u018e\u018f\7\25\2\2\u018fE\3\2\2\2\u0190"+
		"\u0192\7\24\2\2\u0191\u0193\7.\2\2\u0192\u0191\3\2\2\2\u0193\u0194\3\2"+
		"\2\2\u0194\u0192\3\2\2\2\u0194\u0195\3\2\2\2\u0195\u0196\3\2\2\2\u0196"+
		"\u0197\7\25\2\2\u0197G\3\2\2\2\u0198\u019a\7\24\2\2\u0199\u019b\t\3\2"+
		"\2\u019a\u0199\3\2\2\2\u019b\u019c\3\2\2\2\u019c\u019a\3\2\2\2\u019c\u019d"+
		"\3\2\2\2\u019d\u019e\3\2\2\2\u019e\u019f\7\25\2\2\u019fI\3\2\2\2\u01a0"+
		"\u01a2\7\24\2\2\u01a1\u01a3\t\4\2\2\u01a2\u01a1\3\2\2\2\u01a3\u01a4\3"+
		"\2\2\2\u01a4\u01a2\3\2\2\2\u01a4\u01a5\3\2\2\2\u01a5\u01a6\3\2\2\2\u01a6"+
		"\u01a7\7\25\2\2\u01a7K\3\2\2\2\u01a8\u01aa\7\32\2\2\u01a9\u01ab\t\6\2"+
		"\2\u01aa\u01a9\3\2\2\2\u01ab\u01ac\3\2\2\2\u01ac\u01aa\3\2\2\2\u01ac\u01ad"+
		"\3\2\2\2\u01ad\u01ae\3\2\2\2\u01ae\u01af\7\33\2\2\u01afM\3\2\2\2\u01b0"+
		"\u01b2\5R*\2\u01b1\u01b0\3\2\2\2\u01b2\u01b5\3\2\2\2\u01b3\u01b1\3\2\2"+
		"\2\u01b3\u01b4\3\2\2\2\u01b4\u01b6\3\2\2\2\u01b5\u01b3\3\2\2\2\u01b6\u01b7"+
		"\7\63\2\2\u01b7O\3\2\2\2\u01b8\u01ba\5R*\2\u01b9\u01b8\3\2\2\2\u01ba\u01bd"+
		"\3\2\2\2\u01bb\u01b9\3\2\2\2\u01bb\u01bc\3\2\2\2\u01bc\u01be\3\2\2\2\u01bd"+
		"\u01bb\3\2\2\2\u01be\u01bf\7\63\2\2\u01bfQ\3\2\2\2\u01c0\u01c1\7\63\2"+
		"\2\u01c1\u01c2\7\36\2\2\u01c2S\3\2\2\2\u01c3\u01c5\78\2\2\u01c4\u01c3"+
		"\3\2\2\2\u01c5\u01c6\3\2\2\2\u01c6\u01c4\3\2\2\2\u01c6\u01c7\3\2\2\2\u01c7"+
		"U\3\2\2\28Y]biruz\u0088\u0090\u0096\u009a\u009d\u00a8\u00aa\u00ad\u00b3"+
		"\u00b8\u00bd\u00c2\u00d4\u00db\u00df\u00e6\u00ec\u00ff\u010a\u010d\u0110"+
		"\u0118\u011b\u0123\u0126\u012e\u0131\u0139\u013c\u0144\u0147\u014f\u0152"+
		"\u015b\u0162\u0165\u016d\u0172\u0184\u018c\u0194\u019c\u01a4\u01ac\u01b3"+
		"\u01bb\u01c6";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}