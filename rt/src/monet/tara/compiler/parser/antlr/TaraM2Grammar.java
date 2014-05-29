// Generated from /Users/octavio/workspace/tara/rt/src/monet/tara/compiler/parser/antlr/TaraM2Grammar.g4 by ANTLR 4.x

package monet.tara.compiler.parser.antlr;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TaraM2Grammar extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		PACKAGE=18, RIGHT_SQUARE=21, GENERIC=7, NEGATIVE_VALUE=42, DOC=50, LETTER=47, 
		CASE=2, CLOSE_AN=25, DEDENT=54, ABSTRACT=15, SINGLETON=6, BOOLEAN_VALUE=40, 
		RESOURCE=13, MULTIPLE=3, UNKNOWN_TOKEN=55, WORD=12, OPEN_BRACKET=22, FINAL=14, 
		IMPORT=17, COMMA=27, IDENTIFIER=45, BASE=16, VAR=10, NL=52, DIGIT=46, 
		INTENTION=8, DOT=28, STRING_VALUE=44, DOUBLE_VALUE=43, POSITIVE=32, NEW_LINE_INDENT=53, 
		NATURAL_TYPE=36, NEGATIVE=33, HAS_NAME=9, DOUBLE_TYPE=37, BOOLEAN_TYPE=39, 
		SEMICOLON=31, REQUIRED=4, INT_TYPE=35, LIST=19, ROOT=5, OPEN_AN=24, CONCEPT=1, 
		COLON=26, ALIAS_TYPE=34, POSITIVE_VALUE=41, NEWLINE=48, PROPERTY=11, SPACES=49, 
		SP=51, STRING_TYPE=38, ASSIGN=29, LEFT_SQUARE=20, DOUBLE_COMMAS=30, CLOSE_BRACKET=23;
	public static final String[] tokenNames = {
		"<INVALID>", "'Concept'", "'case'", "'multiple'", "'required'", "'root'", 
		"'singleton'", "'generic'", "'intention'", "'has-name'", "'var'", "'property'", 
		"'NodeWord'", "'Resource'", "'final'", "'abstract'", "'base'", "'import'",
		"'package'", "LIST", "'['", "']'", "'{'", "'}'", "'<'", "'>'", "':'", 
		"','", "'.'", "'='", "'\"'", "SEMICOLON", "'+'", "'-'", "'Alias'", "'Integer'", 
		"'Natural'", "'Double'", "'String'", "'Boolean'", "BOOLEAN_VALUE", "POSITIVE_VALUE", 
		"NEGATIVE_VALUE", "DOUBLE_VALUE", "STRING_VALUE", "IDENTIFIER", "DIGIT", 
		"LETTER", "NEWLINE", "SPACES", "DOC", "SP", "NL", "'indent'", "'dedent'", 
		"UNKNOWN_TOKEN"
	};
	public static final int
		RULE_root = 0, RULE_header = 1, RULE_imports = 2, RULE_packet = 3, RULE_concept = 4, 
		RULE_signature = 5, RULE_body = 6, RULE_conceptConstituents = 7, RULE_reference = 8, 
		RULE_word = 9, RULE_attribute = 10, RULE_resource = 11, RULE_naturalValue = 12, 
		RULE_integerValue = 13, RULE_doubleValue = 14, RULE_booleanValue = 15, 
		RULE_stringValue = 16, RULE_stringList = 17, RULE_booleanList = 18, RULE_naturalList = 19, 
		RULE_integerList = 20, RULE_doubleList = 21, RULE_annotations = 22, RULE_variableNames = 23, 
		RULE_headerReference = 24, RULE_externalReference = 25, RULE_identifierReference = 26, 
		RULE_hierarchy = 27, RULE_modifier = 28, RULE_doc = 29;
	public static final String[] ruleNames = {
		"root", "header", "imports", "packet", "concept", "signature", "body", 
		"conceptConstituents", "reference", "word", "attribute", "resource", "naturalValue", 
		"integerValue", "doubleValue", "booleanValue", "stringValue", "stringList", 
		"booleanList", "naturalList", "integerList", "doubleList", "annotations", 
		"variableNames", "headerReference", "externalReference", "identifierReference", 
		"hierarchy", "modifier", "doc"
	};

	@Override
	public String getGrammarFileName() { return "TaraM2Grammar.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public TaraM2Grammar(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class RootContext extends ParserRuleContext {
		public List<TerminalNode> NEWLINE() { return getTokens(TaraM2Grammar.NEWLINE); }
		public ConceptContext concept() {
			return getRuleContext(ConceptContext.class,0);
		}
		public TerminalNode EOF() { return getToken(TaraM2Grammar.EOF, 0); }
		public TerminalNode NEWLINE(int i) {
			return getToken(TaraM2Grammar.NEWLINE, i);
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
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).enterRoot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).exitRoot(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TaraM2GrammarVisitor ) return ((TaraM2GrammarVisitor<? extends T>)visitor).visitRoot(this);
			else return visitor.visitChildren(this);
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
			setState(63);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(60); match(NEWLINE);
					}
					} 
				}
				setState(65);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			}
			setState(67);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				{
				setState(66); header();
				}
				break;
			}
			setState(72);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(69); match(NEWLINE);
				}
				}
				setState(74);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(75); concept();
			setState(79);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(76); match(NEWLINE);
				}
				}
				setState(81);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(82); match(EOF);
			}
		}
		catch (RecognitionException re) {
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
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).enterHeader(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).exitHeader(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TaraM2GrammarVisitor ) return ((TaraM2GrammarVisitor<? extends T>)visitor).visitHeader(this);
			else return visitor.visitChildren(this);
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
			setState(85);
			_la = _input.LA(1);
			if (_la==PACKAGE) {
				{
				setState(84); packet();
				}
			}

			setState(90);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(87); imports();
					}
					} 
				}
				setState(92);
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
		public TerminalNode NEWLINE() { return getToken(TaraM2Grammar.NEWLINE, 0); }
		public HeaderReferenceContext headerReference() {
			return getRuleContext(HeaderReferenceContext.class,0);
		}
		public TerminalNode IMPORT() { return getToken(TaraM2Grammar.IMPORT, 0); }
		public ImportsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_imports; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).enterImports(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).exitImports(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TaraM2GrammarVisitor ) return ((TaraM2GrammarVisitor<? extends T>)visitor).visitImports(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImportsContext imports() throws RecognitionException {
		ImportsContext _localctx = new ImportsContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_imports);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(93); match(NEWLINE);
			setState(94); match(IMPORT);
			setState(95); headerReference();
			}
		}
		catch (RecognitionException re) {
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
		public TerminalNode PACKAGE() { return getToken(TaraM2Grammar.PACKAGE, 0); }
		public PacketContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_packet; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).enterPacket(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).exitPacket(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TaraM2GrammarVisitor ) return ((TaraM2GrammarVisitor<? extends T>)visitor).visitPacket(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PacketContext packet() throws RecognitionException {
		PacketContext _localctx = new PacketContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_packet);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(97); match(PACKAGE);
			setState(98); headerReference();
			}
		}
		catch (RecognitionException re) {
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
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).enterConcept(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).exitConcept(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TaraM2GrammarVisitor ) return ((TaraM2GrammarVisitor<? extends T>)visitor).visitConcept(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConceptContext concept() throws RecognitionException {
		ConceptContext _localctx = new ConceptContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_concept);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(101);
			_la = _input.LA(1);
			if (_la==DOC) {
				{
				setState(100); doc();
				}
			}

			setState(103); signature();
			setState(105);
			_la = _input.LA(1);
			if (_la==OPEN_AN) {
				{
				setState(104); annotations();
				}
			}

			setState(108);
			_la = _input.LA(1);
			if (_la==NEW_LINE_INDENT) {
				{
				setState(107); body();
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
		public TerminalNode IDENTIFIER() { return getToken(TaraM2Grammar.IDENTIFIER, 0); }
		public ModifierContext modifier() {
			return getRuleContext(ModifierContext.class,0);
		}
		public TerminalNode CASE() { return getToken(TaraM2Grammar.CASE, 0); }
		public TerminalNode CONCEPT() { return getToken(TaraM2Grammar.CONCEPT, 0); }
		public TerminalNode COLON() { return getToken(TaraM2Grammar.COLON, 0); }
		public SignatureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_signature; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).enterSignature(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).exitSignature(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TaraM2GrammarVisitor ) return ((TaraM2GrammarVisitor<? extends T>)visitor).visitSignature(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SignatureContext signature() throws RecognitionException {
		SignatureContext _localctx = new SignatureContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_signature);
		int _la;
		try {
			setState(126);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(110); match(CASE);
				setState(111); match(IDENTIFIER);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(112); match(CONCEPT);
				setState(114);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << FINAL) | (1L << ABSTRACT) | (1L << BASE))) != 0)) {
					{
					setState(113); modifier();
					}
				}

				setState(116); match(IDENTIFIER);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(117); match(CONCEPT);
				setState(118); match(COLON);
				setState(119); identifierReference();
				setState(124);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << FINAL) | (1L << ABSTRACT) | (1L << BASE) | (1L << IDENTIFIER))) != 0)) {
					{
					setState(121);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << FINAL) | (1L << ABSTRACT) | (1L << BASE))) != 0)) {
						{
						setState(120); modifier();
						}
					}

					setState(123); match(IDENTIFIER);
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

	public static class BodyContext extends ParserRuleContext {
		public List<TerminalNode> NEWLINE() { return getTokens(TaraM2Grammar.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(TaraM2Grammar.NEWLINE, i);
		}
		public List<ConceptConstituentsContext> conceptConstituents() {
			return getRuleContexts(ConceptConstituentsContext.class);
		}
		public TerminalNode DEDENT() { return getToken(TaraM2Grammar.DEDENT, 0); }
		public TerminalNode NEW_LINE_INDENT() { return getToken(TaraM2Grammar.NEW_LINE_INDENT, 0); }
		public ConceptConstituentsContext conceptConstituents(int i) {
			return getRuleContext(ConceptConstituentsContext.class,i);
		}
		public BodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_body; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).enterBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).exitBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TaraM2GrammarVisitor ) return ((TaraM2GrammarVisitor<? extends T>)visitor).visitBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BodyContext body() throws RecognitionException {
		BodyContext _localctx = new BodyContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_body);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(128); match(NEW_LINE_INDENT);
			setState(135); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(129); conceptConstituents();
				setState(131); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(130); match(NEWLINE);
					}
					}
					setState(133); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NEWLINE );
				}
				}
				setState(137); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << CONCEPT) | (1L << CASE) | (1L << VAR) | (1L << PROPERTY) | (1L << DOC))) != 0) );
			setState(139); match(DEDENT);
			}
		}
		catch (RecognitionException re) {
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
		public WordContext word() {
			return getRuleContext(WordContext.class,0);
		}
		public AttributeContext attribute() {
			return getRuleContext(AttributeContext.class,0);
		}
		public ReferenceContext reference() {
			return getRuleContext(ReferenceContext.class,0);
		}
		public ConceptConstituentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conceptConstituents; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).enterConceptConstituents(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).exitConceptConstituents(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TaraM2GrammarVisitor ) return ((TaraM2GrammarVisitor<? extends T>)visitor).visitConceptConstituents(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConceptConstituentsContext conceptConstituents() throws RecognitionException {
		ConceptConstituentsContext _localctx = new ConceptConstituentsContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_conceptConstituents);
		try {
			setState(145);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(141); attribute();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(142); reference();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(143); word();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(144); concept();
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

	public static class ReferenceContext extends ParserRuleContext {
		public VariableNamesContext variableNames() {
			return getRuleContext(VariableNamesContext.class,0);
		}
		public IdentifierReferenceContext identifierReference() {
			return getRuleContext(IdentifierReferenceContext.class,0);
		}
		public TerminalNode VAR() { return getToken(TaraM2Grammar.VAR, 0); }
		public TerminalNode LIST() { return getToken(TaraM2Grammar.LIST, 0); }
		public ReferenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_reference; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).enterReference(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).exitReference(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TaraM2GrammarVisitor ) return ((TaraM2GrammarVisitor<? extends T>)visitor).visitReference(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReferenceContext reference() throws RecognitionException {
		ReferenceContext _localctx = new ReferenceContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_reference);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(147); match(VAR);
			setState(148); identifierReference();
			setState(150);
			_la = _input.LA(1);
			if (_la==LIST) {
				{
				setState(149); match(LIST);
				}
			}

			setState(152); variableNames();
			}
		}
		catch (RecognitionException re) {
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
		public List<TerminalNode> NEWLINE() { return getTokens(TaraM2Grammar.NEWLINE); }
		public TerminalNode WORD() { return getToken(TaraM2Grammar.WORD, 0); }
		public TerminalNode NEWLINE(int i) {
			return getToken(TaraM2Grammar.NEWLINE, i);
		}
		public TerminalNode IDENTIFIER(int i) {
			return getToken(TaraM2Grammar.IDENTIFIER, i);
		}
		public List<TerminalNode> IDENTIFIER() { return getTokens(TaraM2Grammar.IDENTIFIER); }
		public TerminalNode VAR() { return getToken(TaraM2Grammar.VAR, 0); }
		public TerminalNode DEDENT() { return getToken(TaraM2Grammar.DEDENT, 0); }
		public TerminalNode NEW_LINE_INDENT() { return getToken(TaraM2Grammar.NEW_LINE_INDENT, 0); }
		public WordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_word; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).enterWord(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).exitWord(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TaraM2GrammarVisitor ) return ((TaraM2GrammarVisitor<? extends T>)visitor).visitWord(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WordContext word() throws RecognitionException {
		WordContext _localctx = new WordContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_word);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(154); match(VAR);
			setState(155); match(WORD);
			setState(156); match(IDENTIFIER);
			setState(157); match(NEW_LINE_INDENT);
			setState(160); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(158); match(IDENTIFIER);
				setState(159); match(NEWLINE);
				}
				}
				setState(162); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==IDENTIFIER );
			setState(164); match(DEDENT);
			}
		}
		catch (RecognitionException re) {
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
		public TerminalNode INT_TYPE() { return getToken(TaraM2Grammar.INT_TYPE, 0); }
		public BooleanValueContext booleanValue() {
			return getRuleContext(BooleanValueContext.class,0);
		}
		public TerminalNode BOOLEAN_TYPE() { return getToken(TaraM2Grammar.BOOLEAN_TYPE, 0); }
		public VariableNamesContext variableNames() {
			return getRuleContext(VariableNamesContext.class,0);
		}
		public IntegerListContext integerList() {
			return getRuleContext(IntegerListContext.class,0);
		}
		public TerminalNode NATURAL_TYPE() { return getToken(TaraM2Grammar.NATURAL_TYPE, 0); }
		public TerminalNode ALIAS_TYPE() { return getToken(TaraM2Grammar.ALIAS_TYPE, 0); }
		public TerminalNode STRING_TYPE() { return getToken(TaraM2Grammar.STRING_TYPE, 0); }
		public DoubleValueContext doubleValue() {
			return getRuleContext(DoubleValueContext.class,0);
		}
		public StringValueContext stringValue() {
			return getRuleContext(StringValueContext.class,0);
		}
		public BooleanListContext booleanList() {
			return getRuleContext(BooleanListContext.class,0);
		}
		public TerminalNode LIST() { return getToken(TaraM2Grammar.LIST, 0); }
		public TerminalNode DOUBLE_TYPE() { return getToken(TaraM2Grammar.DOUBLE_TYPE, 0); }
		public ResourceContext resource() {
			return getRuleContext(ResourceContext.class,0);
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
		public TerminalNode IDENTIFIER() { return getToken(TaraM2Grammar.IDENTIFIER, 0); }
		public TerminalNode ASSIGN() { return getToken(TaraM2Grammar.ASSIGN, 0); }
		public TerminalNode VAR() { return getToken(TaraM2Grammar.VAR, 0); }
		public TerminalNode PROPERTY() { return getToken(TaraM2Grammar.PROPERTY, 0); }
		public IntegerValueContext integerValue() {
			return getRuleContext(IntegerValueContext.class,0);
		}
		public AttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attribute; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).enterAttribute(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).exitAttribute(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TaraM2GrammarVisitor ) return ((TaraM2GrammarVisitor<? extends T>)visitor).visitAttribute(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AttributeContext attribute() throws RecognitionException {
		AttributeContext _localctx = new AttributeContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_attribute);
		int _la;
		try {
			setState(244);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(166);
				_la = _input.LA(1);
				if ( !(_la==VAR || _la==PROPERTY) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(167); match(ALIAS_TYPE);
				setState(168); match(IDENTIFIER);
				setState(171);
				_la = _input.LA(1);
				if (_la==ASSIGN) {
					{
					setState(169); match(ASSIGN);
					setState(170); stringValue();
					}
				}

				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(173);
				_la = _input.LA(1);
				if ( !(_la==VAR || _la==PROPERTY) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(174); match(NATURAL_TYPE);
				setState(185);
				switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
				case 1:
					{
					setState(175); variableNames();
					}
					break;
				case 2:
					{
					setState(176); match(IDENTIFIER);
					setState(177); match(ASSIGN);
					setState(178); naturalValue();
					}
					break;
				case 3:
					{
					setState(179); match(LIST);
					setState(180); match(IDENTIFIER);
					setState(183);
					_la = _input.LA(1);
					if (_la==ASSIGN) {
						{
						setState(181); match(ASSIGN);
						setState(182); naturalList();
						}
					}

					}
					break;
				}
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				{
				setState(187);
				_la = _input.LA(1);
				if ( !(_la==VAR || _la==PROPERTY) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(188); match(INT_TYPE);
				setState(199);
				switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
				case 1:
					{
					setState(189); variableNames();
					}
					break;
				case 2:
					{
					setState(190); match(IDENTIFIER);
					setState(191); match(ASSIGN);
					setState(192); integerValue();
					}
					break;
				case 3:
					{
					setState(193); match(LIST);
					setState(194); match(IDENTIFIER);
					setState(197);
					_la = _input.LA(1);
					if (_la==ASSIGN) {
						{
						setState(195); match(ASSIGN);
						setState(196); integerList();
						}
					}

					}
					break;
				}
				}
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				{
				setState(201);
				_la = _input.LA(1);
				if ( !(_la==VAR || _la==PROPERTY) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(202); match(DOUBLE_TYPE);
				setState(213);
				switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
				case 1:
					{
					setState(203); variableNames();
					}
					break;
				case 2:
					{
					setState(204); match(IDENTIFIER);
					setState(205); match(ASSIGN);
					setState(206); doubleValue();
					}
					break;
				case 3:
					{
					setState(207); match(LIST);
					setState(208); match(IDENTIFIER);
					setState(211);
					_la = _input.LA(1);
					if (_la==ASSIGN) {
						{
						setState(209); match(ASSIGN);
						setState(210); doubleList();
						}
					}

					}
					break;
				}
				}
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				{
				setState(215);
				_la = _input.LA(1);
				if ( !(_la==VAR || _la==PROPERTY) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(216); match(BOOLEAN_TYPE);
				setState(227);
				switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
				case 1:
					{
					setState(217); variableNames();
					}
					break;
				case 2:
					{
					setState(218); match(IDENTIFIER);
					setState(219); match(ASSIGN);
					setState(220); booleanValue();
					}
					break;
				case 3:
					{
					setState(221); match(LIST);
					setState(222); match(IDENTIFIER);
					setState(225);
					_la = _input.LA(1);
					if (_la==ASSIGN) {
						{
						setState(223); match(ASSIGN);
						setState(224); booleanList();
						}
					}

					}
					break;
				}
				}
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				{
				setState(229);
				_la = _input.LA(1);
				if ( !(_la==VAR || _la==PROPERTY) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(230); match(STRING_TYPE);
				setState(241);
				switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
				case 1:
					{
					setState(231); variableNames();
					}
					break;
				case 2:
					{
					setState(232); match(IDENTIFIER);
					setState(233); match(ASSIGN);
					setState(234); stringValue();
					}
					break;
				case 3:
					{
					setState(235); match(LIST);
					setState(236); match(IDENTIFIER);
					setState(239);
					_la = _input.LA(1);
					if (_la==ASSIGN) {
						{
						setState(237); match(ASSIGN);
						setState(238); stringList();
						}
					}

					}
					break;
				}
				}
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(243); resource();
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

	public static class ResourceContext extends ParserRuleContext {
		public TerminalNode RESOURCE() { return getToken(TaraM2Grammar.RESOURCE, 0); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(TaraM2Grammar.IDENTIFIER, i);
		}
		public List<TerminalNode> IDENTIFIER() { return getTokens(TaraM2Grammar.IDENTIFIER); }
		public TerminalNode PROPERTY() { return getToken(TaraM2Grammar.PROPERTY, 0); }
		public TerminalNode VAR() { return getToken(TaraM2Grammar.VAR, 0); }
		public TerminalNode COLON() { return getToken(TaraM2Grammar.COLON, 0); }
		public ResourceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_resource; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).enterResource(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).exitResource(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TaraM2GrammarVisitor ) return ((TaraM2GrammarVisitor<? extends T>)visitor).visitResource(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ResourceContext resource() throws RecognitionException {
		ResourceContext _localctx = new ResourceContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_resource);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(246);
			_la = _input.LA(1);
			if ( !(_la==VAR || _la==PROPERTY) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			setState(247); match(RESOURCE);
			setState(248); match(COLON);
			setState(249); match(IDENTIFIER);
			setState(250); match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
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
		public TerminalNode POSITIVE_VALUE() { return getToken(TaraM2Grammar.POSITIVE_VALUE, 0); }
		public NaturalValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_naturalValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).enterNaturalValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).exitNaturalValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TaraM2GrammarVisitor ) return ((TaraM2GrammarVisitor<? extends T>)visitor).visitNaturalValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NaturalValueContext naturalValue() throws RecognitionException {
		NaturalValueContext _localctx = new NaturalValueContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_naturalValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(252); match(POSITIVE_VALUE);
			}
		}
		catch (RecognitionException re) {
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
		public TerminalNode POSITIVE_VALUE() { return getToken(TaraM2Grammar.POSITIVE_VALUE, 0); }
		public TerminalNode NEGATIVE_VALUE() { return getToken(TaraM2Grammar.NEGATIVE_VALUE, 0); }
		public IntegerValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_integerValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).enterIntegerValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).exitIntegerValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TaraM2GrammarVisitor ) return ((TaraM2GrammarVisitor<? extends T>)visitor).visitIntegerValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IntegerValueContext integerValue() throws RecognitionException {
		IntegerValueContext _localctx = new IntegerValueContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_integerValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(254);
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
		public TerminalNode DOUBLE_VALUE() { return getToken(TaraM2Grammar.DOUBLE_VALUE, 0); }
		public TerminalNode POSITIVE_VALUE() { return getToken(TaraM2Grammar.POSITIVE_VALUE, 0); }
		public TerminalNode NEGATIVE_VALUE() { return getToken(TaraM2Grammar.NEGATIVE_VALUE, 0); }
		public DoubleValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_doubleValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).enterDoubleValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).exitDoubleValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TaraM2GrammarVisitor ) return ((TaraM2GrammarVisitor<? extends T>)visitor).visitDoubleValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DoubleValueContext doubleValue() throws RecognitionException {
		DoubleValueContext _localctx = new DoubleValueContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_doubleValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(256);
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
		public TerminalNode BOOLEAN_VALUE() { return getToken(TaraM2Grammar.BOOLEAN_VALUE, 0); }
		public BooleanValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_booleanValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).enterBooleanValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).exitBooleanValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TaraM2GrammarVisitor ) return ((TaraM2GrammarVisitor<? extends T>)visitor).visitBooleanValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BooleanValueContext booleanValue() throws RecognitionException {
		BooleanValueContext _localctx = new BooleanValueContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_booleanValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(258); match(BOOLEAN_VALUE);
			}
		}
		catch (RecognitionException re) {
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
		public TerminalNode STRING_VALUE() { return getToken(TaraM2Grammar.STRING_VALUE, 0); }
		public StringValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stringValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).enterStringValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).exitStringValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TaraM2GrammarVisitor ) return ((TaraM2GrammarVisitor<? extends T>)visitor).visitStringValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StringValueContext stringValue() throws RecognitionException {
		StringValueContext _localctx = new StringValueContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_stringValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(260); match(STRING_VALUE);
			}
		}
		catch (RecognitionException re) {
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
			return getToken(TaraM2Grammar.STRING_VALUE, i);
		}
		public TerminalNode LEFT_SQUARE() { return getToken(TaraM2Grammar.LEFT_SQUARE, 0); }
		public List<TerminalNode> STRING_VALUE() { return getTokens(TaraM2Grammar.STRING_VALUE); }
		public TerminalNode RIGHT_SQUARE() { return getToken(TaraM2Grammar.RIGHT_SQUARE, 0); }
		public StringListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stringList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).enterStringList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).exitStringList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TaraM2GrammarVisitor ) return ((TaraM2GrammarVisitor<? extends T>)visitor).visitStringList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StringListContext stringList() throws RecognitionException {
		StringListContext _localctx = new StringListContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_stringList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(262); match(LEFT_SQUARE);
			setState(264); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(263); match(STRING_VALUE);
				}
				}
				setState(266); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==STRING_VALUE );
			setState(268); match(RIGHT_SQUARE);
			}
		}
		catch (RecognitionException re) {
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
		public TerminalNode LEFT_SQUARE() { return getToken(TaraM2Grammar.LEFT_SQUARE, 0); }
		public List<TerminalNode> BOOLEAN_VALUE() { return getTokens(TaraM2Grammar.BOOLEAN_VALUE); }
		public TerminalNode RIGHT_SQUARE() { return getToken(TaraM2Grammar.RIGHT_SQUARE, 0); }
		public TerminalNode BOOLEAN_VALUE(int i) {
			return getToken(TaraM2Grammar.BOOLEAN_VALUE, i);
		}
		public BooleanListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_booleanList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).enterBooleanList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).exitBooleanList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TaraM2GrammarVisitor ) return ((TaraM2GrammarVisitor<? extends T>)visitor).visitBooleanList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BooleanListContext booleanList() throws RecognitionException {
		BooleanListContext _localctx = new BooleanListContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_booleanList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(270); match(LEFT_SQUARE);
			setState(272); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(271); match(BOOLEAN_VALUE);
				}
				}
				setState(274); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==BOOLEAN_VALUE );
			setState(276); match(RIGHT_SQUARE);
			}
		}
		catch (RecognitionException re) {
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
		public TerminalNode LEFT_SQUARE() { return getToken(TaraM2Grammar.LEFT_SQUARE, 0); }
		public TerminalNode RIGHT_SQUARE() { return getToken(TaraM2Grammar.RIGHT_SQUARE, 0); }
		public List<TerminalNode> POSITIVE_VALUE() { return getTokens(TaraM2Grammar.POSITIVE_VALUE); }
		public TerminalNode POSITIVE_VALUE(int i) {
			return getToken(TaraM2Grammar.POSITIVE_VALUE, i);
		}
		public NaturalListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_naturalList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).enterNaturalList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).exitNaturalList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TaraM2GrammarVisitor ) return ((TaraM2GrammarVisitor<? extends T>)visitor).visitNaturalList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NaturalListContext naturalList() throws RecognitionException {
		NaturalListContext _localctx = new NaturalListContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_naturalList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(278); match(LEFT_SQUARE);
			setState(280); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(279); match(POSITIVE_VALUE);
				}
				}
				setState(282); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==POSITIVE_VALUE );
			setState(284); match(RIGHT_SQUARE);
			}
		}
		catch (RecognitionException re) {
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
		public TerminalNode LEFT_SQUARE() { return getToken(TaraM2Grammar.LEFT_SQUARE, 0); }
		public TerminalNode RIGHT_SQUARE() { return getToken(TaraM2Grammar.RIGHT_SQUARE, 0); }
		public List<TerminalNode> POSITIVE_VALUE() { return getTokens(TaraM2Grammar.POSITIVE_VALUE); }
		public TerminalNode NEGATIVE_VALUE(int i) {
			return getToken(TaraM2Grammar.NEGATIVE_VALUE, i);
		}
		public List<TerminalNode> NEGATIVE_VALUE() { return getTokens(TaraM2Grammar.NEGATIVE_VALUE); }
		public TerminalNode POSITIVE_VALUE(int i) {
			return getToken(TaraM2Grammar.POSITIVE_VALUE, i);
		}
		public IntegerListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_integerList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).enterIntegerList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).exitIntegerList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TaraM2GrammarVisitor ) return ((TaraM2GrammarVisitor<? extends T>)visitor).visitIntegerList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IntegerListContext integerList() throws RecognitionException {
		IntegerListContext _localctx = new IntegerListContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_integerList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(286); match(LEFT_SQUARE);
			setState(288); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(287);
				_la = _input.LA(1);
				if ( !(_la==POSITIVE_VALUE || _la==NEGATIVE_VALUE) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				}
				}
				setState(290); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==POSITIVE_VALUE || _la==NEGATIVE_VALUE );
			setState(292); match(RIGHT_SQUARE);
			}
		}
		catch (RecognitionException re) {
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
		public List<TerminalNode> DOUBLE_VALUE() { return getTokens(TaraM2Grammar.DOUBLE_VALUE); }
		public TerminalNode LEFT_SQUARE() { return getToken(TaraM2Grammar.LEFT_SQUARE, 0); }
		public TerminalNode RIGHT_SQUARE() { return getToken(TaraM2Grammar.RIGHT_SQUARE, 0); }
		public TerminalNode DOUBLE_VALUE(int i) {
			return getToken(TaraM2Grammar.DOUBLE_VALUE, i);
		}
		public List<TerminalNode> POSITIVE_VALUE() { return getTokens(TaraM2Grammar.POSITIVE_VALUE); }
		public TerminalNode NEGATIVE_VALUE(int i) {
			return getToken(TaraM2Grammar.NEGATIVE_VALUE, i);
		}
		public List<TerminalNode> NEGATIVE_VALUE() { return getTokens(TaraM2Grammar.NEGATIVE_VALUE); }
		public TerminalNode POSITIVE_VALUE(int i) {
			return getToken(TaraM2Grammar.POSITIVE_VALUE, i);
		}
		public DoubleListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_doubleList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).enterDoubleList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).exitDoubleList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TaraM2GrammarVisitor ) return ((TaraM2GrammarVisitor<? extends T>)visitor).visitDoubleList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DoubleListContext doubleList() throws RecognitionException {
		DoubleListContext _localctx = new DoubleListContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_doubleList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(294); match(LEFT_SQUARE);
			setState(296); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(295);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << POSITIVE_VALUE) | (1L << NEGATIVE_VALUE) | (1L << DOUBLE_VALUE))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				}
				}
				setState(298); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << POSITIVE_VALUE) | (1L << NEGATIVE_VALUE) | (1L << DOUBLE_VALUE))) != 0) );
			setState(300); match(RIGHT_SQUARE);
			}
		}
		catch (RecognitionException re) {
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
		public TerminalNode OPEN_AN() { return getToken(TaraM2Grammar.OPEN_AN, 0); }
		public List<TerminalNode> ROOT() { return getTokens(TaraM2Grammar.ROOT); }
		public TerminalNode ROOT(int i) {
			return getToken(TaraM2Grammar.ROOT, i);
		}
		public List<TerminalNode> HAS_NAME() { return getTokens(TaraM2Grammar.HAS_NAME); }
		public TerminalNode GENERIC(int i) {
			return getToken(TaraM2Grammar.GENERIC, i);
		}
		public List<TerminalNode> REQUIRED() { return getTokens(TaraM2Grammar.REQUIRED); }
		public TerminalNode CLOSE_AN() { return getToken(TaraM2Grammar.CLOSE_AN, 0); }
		public TerminalNode MULTIPLE(int i) {
			return getToken(TaraM2Grammar.MULTIPLE, i);
		}
		public List<TerminalNode> GENERIC() { return getTokens(TaraM2Grammar.GENERIC); }
		public TerminalNode INTENTION(int i) {
			return getToken(TaraM2Grammar.INTENTION, i);
		}
		public List<TerminalNode> MULTIPLE() { return getTokens(TaraM2Grammar.MULTIPLE); }
		public TerminalNode REQUIRED(int i) {
			return getToken(TaraM2Grammar.REQUIRED, i);
		}
		public List<TerminalNode> INTENTION() { return getTokens(TaraM2Grammar.INTENTION); }
		public TerminalNode SINGLETON(int i) {
			return getToken(TaraM2Grammar.SINGLETON, i);
		}
		public List<TerminalNode> SINGLETON() { return getTokens(TaraM2Grammar.SINGLETON); }
		public TerminalNode HAS_NAME(int i) {
			return getToken(TaraM2Grammar.HAS_NAME, i);
		}
		public AnnotationsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotations; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).enterAnnotations(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).exitAnnotations(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TaraM2GrammarVisitor ) return ((TaraM2GrammarVisitor<? extends T>)visitor).visitAnnotations(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnnotationsContext annotations() throws RecognitionException {
		AnnotationsContext _localctx = new AnnotationsContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_annotations);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(302); match(OPEN_AN);
			setState(304); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(303);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MULTIPLE) | (1L << REQUIRED) | (1L << ROOT) | (1L << SINGLETON) | (1L << GENERIC) | (1L << INTENTION) | (1L << HAS_NAME))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				}
				}
				setState(306); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MULTIPLE) | (1L << REQUIRED) | (1L << ROOT) | (1L << SINGLETON) | (1L << GENERIC) | (1L << INTENTION) | (1L << HAS_NAME))) != 0) );
			setState(308); match(CLOSE_AN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableNamesContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER(int i) {
			return getToken(TaraM2Grammar.IDENTIFIER, i);
		}
		public List<TerminalNode> IDENTIFIER() { return getTokens(TaraM2Grammar.IDENTIFIER); }
		public List<TerminalNode> COMMA() { return getTokens(TaraM2Grammar.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(TaraM2Grammar.COMMA, i);
		}
		public VariableNamesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableNames; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).enterVariableNames(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).exitVariableNames(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TaraM2GrammarVisitor ) return ((TaraM2GrammarVisitor<? extends T>)visitor).visitVariableNames(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableNamesContext variableNames() throws RecognitionException {
		VariableNamesContext _localctx = new VariableNamesContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_variableNames);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(310); match(IDENTIFIER);
			setState(315);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(311); match(COMMA);
				setState(312); match(IDENTIFIER);
				}
				}
				setState(317);
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

	public static class HeaderReferenceContext extends ParserRuleContext {
		public List<HierarchyContext> hierarchy() {
			return getRuleContexts(HierarchyContext.class);
		}
		public TerminalNode IDENTIFIER() { return getToken(TaraM2Grammar.IDENTIFIER, 0); }
		public HierarchyContext hierarchy(int i) {
			return getRuleContext(HierarchyContext.class,i);
		}
		public HeaderReferenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_headerReference; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).enterHeaderReference(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).exitHeaderReference(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TaraM2GrammarVisitor ) return ((TaraM2GrammarVisitor<? extends T>)visitor).visitHeaderReference(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HeaderReferenceContext headerReference() throws RecognitionException {
		HeaderReferenceContext _localctx = new HeaderReferenceContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_headerReference);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(321);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,37,_ctx);
			while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(318); hierarchy();
					}
					} 
				}
				setState(323);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,37,_ctx);
			}
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

	public static class ExternalReferenceContext extends ParserRuleContext {
		public List<HierarchyContext> hierarchy() {
			return getRuleContexts(HierarchyContext.class);
		}
		public TerminalNode IDENTIFIER() { return getToken(TaraM2Grammar.IDENTIFIER, 0); }
		public HierarchyContext hierarchy(int i) {
			return getRuleContext(HierarchyContext.class,i);
		}
		public ExternalReferenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_externalReference; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).enterExternalReference(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).exitExternalReference(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TaraM2GrammarVisitor ) return ((TaraM2GrammarVisitor<? extends T>)visitor).visitExternalReference(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExternalReferenceContext externalReference() throws RecognitionException {
		ExternalReferenceContext _localctx = new ExternalReferenceContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_externalReference);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(329);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,38,_ctx);
			while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(326); hierarchy();
					}
					} 
				}
				setState(331);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,38,_ctx);
			}
			setState(332); match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
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
		public TerminalNode IDENTIFIER() { return getToken(TaraM2Grammar.IDENTIFIER, 0); }
		public HierarchyContext hierarchy(int i) {
			return getRuleContext(HierarchyContext.class,i);
		}
		public IdentifierReferenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifierReference; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).enterIdentifierReference(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).exitIdentifierReference(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TaraM2GrammarVisitor ) return ((TaraM2GrammarVisitor<? extends T>)visitor).visitIdentifierReference(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdentifierReferenceContext identifierReference() throws RecognitionException {
		IdentifierReferenceContext _localctx = new IdentifierReferenceContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_identifierReference);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(337);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,39,_ctx);
			while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(334); hierarchy();
					}
					} 
				}
				setState(339);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,39,_ctx);
			}
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

	public static class HierarchyContext extends ParserRuleContext {
		public TerminalNode DOT() { return getToken(TaraM2Grammar.DOT, 0); }
		public TerminalNode IDENTIFIER() { return getToken(TaraM2Grammar.IDENTIFIER, 0); }
		public HierarchyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_hierarchy; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).enterHierarchy(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).exitHierarchy(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TaraM2GrammarVisitor ) return ((TaraM2GrammarVisitor<? extends T>)visitor).visitHierarchy(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HierarchyContext hierarchy() throws RecognitionException {
		HierarchyContext _localctx = new HierarchyContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_hierarchy);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(342); match(IDENTIFIER);
			setState(343); match(DOT);
			}
		}
		catch (RecognitionException re) {
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
		public TerminalNode FINAL() { return getToken(TaraM2Grammar.FINAL, 0); }
		public TerminalNode ABSTRACT() { return getToken(TaraM2Grammar.ABSTRACT, 0); }
		public TerminalNode BASE() { return getToken(TaraM2Grammar.BASE, 0); }
		public ModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_modifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).enterModifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).exitModifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TaraM2GrammarVisitor ) return ((TaraM2GrammarVisitor<? extends T>)visitor).visitModifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ModifierContext modifier() throws RecognitionException {
		ModifierContext _localctx = new ModifierContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_modifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(345);
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
			return getToken(TaraM2Grammar.DOC, i);
		}
		public List<TerminalNode> DOC() { return getTokens(TaraM2Grammar.DOC); }
		public DocContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_doc; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).enterDoc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).exitDoc(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TaraM2GrammarVisitor ) return ((TaraM2GrammarVisitor<? extends T>)visitor).visitDoc(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DocContext doc() throws RecognitionException {
		DocContext _localctx = new DocContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_doc);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(348); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(347); match(DOC);
				}
				}
				setState(350); 
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\39\u0163\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\3\2\7\2@"+
		"\n\2\f\2\16\2C\13\2\3\2\5\2F\n\2\3\2\7\2I\n\2\f\2\16\2L\13\2\3\2\3\2\7"+
		"\2P\n\2\f\2\16\2S\13\2\3\2\3\2\3\3\5\3X\n\3\3\3\7\3[\n\3\f\3\16\3^\13"+
		"\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\6\5\6h\n\6\3\6\3\6\5\6l\n\6\3\6\5\6o"+
		"\n\6\3\7\3\7\3\7\3\7\5\7u\n\7\3\7\3\7\3\7\3\7\3\7\5\7|\n\7\3\7\5\7\177"+
		"\n\7\5\7\u0081\n\7\3\b\3\b\3\b\6\b\u0086\n\b\r\b\16\b\u0087\6\b\u008a"+
		"\n\b\r\b\16\b\u008b\3\b\3\b\3\t\3\t\3\t\3\t\5\t\u0094\n\t\3\n\3\n\3\n"+
		"\5\n\u0099\n\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\6\13\u00a3\n\13\r"+
		"\13\16\13\u00a4\3\13\3\13\3\f\3\f\3\f\3\f\3\f\5\f\u00ae\n\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u00ba\n\f\5\f\u00bc\n\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u00c8\n\f\5\f\u00ca\n\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u00d6\n\f\5\f\u00d8\n\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u00e4\n\f\5\f\u00e6\n\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\5\f\u00f2\n\f\5\f\u00f4\n\f\3\f\5\f\u00f7\n\f\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3"+
		"\22\3\23\3\23\6\23\u010b\n\23\r\23\16\23\u010c\3\23\3\23\3\24\3\24\6\24"+
		"\u0113\n\24\r\24\16\24\u0114\3\24\3\24\3\25\3\25\6\25\u011b\n\25\r\25"+
		"\16\25\u011c\3\25\3\25\3\26\3\26\6\26\u0123\n\26\r\26\16\26\u0124\3\26"+
		"\3\26\3\27\3\27\6\27\u012b\n\27\r\27\16\27\u012c\3\27\3\27\3\30\3\30\6"+
		"\30\u0133\n\30\r\30\16\30\u0134\3\30\3\30\3\31\3\31\3\31\7\31\u013c\n"+
		"\31\f\31\16\31\u013f\13\31\3\32\7\32\u0142\n\32\f\32\16\32\u0145\13\32"+
		"\3\32\3\32\3\33\7\33\u014a\n\33\f\33\16\33\u014d\13\33\3\33\3\33\3\34"+
		"\7\34\u0152\n\34\f\34\16\34\u0155\13\34\3\34\3\34\3\35\3\35\3\35\3\36"+
		"\3\36\3\37\6\37\u015f\n\37\r\37\16\37\u0160\3\37\2\2 \2\4\6\b\n\f\16\20"+
		"\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<\2\7\3\2\f\r\3\2+,\3\2+-"+
		"\3\2\5\13\3\2\20\22\u017a\2A\3\2\2\2\4W\3\2\2\2\6_\3\2\2\2\bc\3\2\2\2"+
		"\ng\3\2\2\2\f\u0080\3\2\2\2\16\u0082\3\2\2\2\20\u0093\3\2\2\2\22\u0095"+
		"\3\2\2\2\24\u009c\3\2\2\2\26\u00f6\3\2\2\2\30\u00f8\3\2\2\2\32\u00fe\3"+
		"\2\2\2\34\u0100\3\2\2\2\36\u0102\3\2\2\2 \u0104\3\2\2\2\"\u0106\3\2\2"+
		"\2$\u0108\3\2\2\2&\u0110\3\2\2\2(\u0118\3\2\2\2*\u0120\3\2\2\2,\u0128"+
		"\3\2\2\2.\u0130\3\2\2\2\60\u0138\3\2\2\2\62\u0143\3\2\2\2\64\u014b\3\2"+
		"\2\2\66\u0153\3\2\2\28\u0158\3\2\2\2:\u015b\3\2\2\2<\u015e\3\2\2\2>@\7"+
		"\62\2\2?>\3\2\2\2@C\3\2\2\2A?\3\2\2\2AB\3\2\2\2BE\3\2\2\2CA\3\2\2\2DF"+
		"\5\4\3\2ED\3\2\2\2EF\3\2\2\2FJ\3\2\2\2GI\7\62\2\2HG\3\2\2\2IL\3\2\2\2"+
		"JH\3\2\2\2JK\3\2\2\2KM\3\2\2\2LJ\3\2\2\2MQ\5\n\6\2NP\7\62\2\2ON\3\2\2"+
		"\2PS\3\2\2\2QO\3\2\2\2QR\3\2\2\2RT\3\2\2\2SQ\3\2\2\2TU\7\2\2\3U\3\3\2"+
		"\2\2VX\5\b\5\2WV\3\2\2\2WX\3\2\2\2X\\\3\2\2\2Y[\5\6\4\2ZY\3\2\2\2[^\3"+
		"\2\2\2\\Z\3\2\2\2\\]\3\2\2\2]\5\3\2\2\2^\\\3\2\2\2_`\7\62\2\2`a\7\23\2"+
		"\2ab\5\62\32\2b\7\3\2\2\2cd\7\24\2\2de\5\62\32\2e\t\3\2\2\2fh\5<\37\2"+
		"gf\3\2\2\2gh\3\2\2\2hi\3\2\2\2ik\5\f\7\2jl\5.\30\2kj\3\2\2\2kl\3\2\2\2"+
		"ln\3\2\2\2mo\5\16\b\2nm\3\2\2\2no\3\2\2\2o\13\3\2\2\2pq\7\4\2\2q\u0081"+
		"\7/\2\2rt\7\3\2\2su\5:\36\2ts\3\2\2\2tu\3\2\2\2uv\3\2\2\2v\u0081\7/\2"+
		"\2wx\7\3\2\2xy\7\34\2\2y~\5\66\34\2z|\5:\36\2{z\3\2\2\2{|\3\2\2\2|}\3"+
		"\2\2\2}\177\7/\2\2~{\3\2\2\2~\177\3\2\2\2\177\u0081\3\2\2\2\u0080p\3\2"+
		"\2\2\u0080r\3\2\2\2\u0080w\3\2\2\2\u0081\r\3\2\2\2\u0082\u0089\7\67\2"+
		"\2\u0083\u0085\5\20\t\2\u0084\u0086\7\62\2\2\u0085\u0084\3\2\2\2\u0086"+
		"\u0087\3\2\2\2\u0087\u0085\3\2\2\2\u0087\u0088\3\2\2\2\u0088\u008a\3\2"+
		"\2\2\u0089\u0083\3\2\2\2\u008a\u008b\3\2\2\2\u008b\u0089\3\2\2\2\u008b"+
		"\u008c\3\2\2\2\u008c\u008d\3\2\2\2\u008d\u008e\78\2\2\u008e\17\3\2\2\2"+
		"\u008f\u0094\5\26\f\2\u0090\u0094\5\22\n\2\u0091\u0094\5\24\13\2\u0092"+
		"\u0094\5\n\6\2\u0093\u008f\3\2\2\2\u0093\u0090\3\2\2\2\u0093\u0091\3\2"+
		"\2\2\u0093\u0092\3\2\2\2\u0094\21\3\2\2\2\u0095\u0096\7\f\2\2\u0096\u0098"+
		"\5\66\34\2\u0097\u0099\7\25\2\2\u0098\u0097\3\2\2\2\u0098\u0099\3\2\2"+
		"\2\u0099\u009a\3\2\2\2\u009a\u009b\5\60\31\2\u009b\23\3\2\2\2\u009c\u009d"+
		"\7\f\2\2\u009d\u009e\7\16\2\2\u009e\u009f\7/\2\2\u009f\u00a2\7\67\2\2"+
		"\u00a0\u00a1\7/\2\2\u00a1\u00a3\7\62\2\2\u00a2\u00a0\3\2\2\2\u00a3\u00a4"+
		"\3\2\2\2\u00a4\u00a2\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5\u00a6\3\2\2\2\u00a6"+
		"\u00a7\78\2\2\u00a7\25\3\2\2\2\u00a8\u00a9\t\2\2\2\u00a9\u00aa\7$\2\2"+
		"\u00aa\u00ad\7/\2\2\u00ab\u00ac\7\37\2\2\u00ac\u00ae\5\"\22\2\u00ad\u00ab"+
		"\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae\u00f7\3\2\2\2\u00af\u00b0\t\2\2\2\u00b0"+
		"\u00bb\7&\2\2\u00b1\u00bc\5\60\31\2\u00b2\u00b3\7/\2\2\u00b3\u00b4\7\37"+
		"\2\2\u00b4\u00bc\5\32\16\2\u00b5\u00b6\7\25\2\2\u00b6\u00b9\7/\2\2\u00b7"+
		"\u00b8\7\37\2\2\u00b8\u00ba\5(\25\2\u00b9\u00b7\3\2\2\2\u00b9\u00ba\3"+
		"\2\2\2\u00ba\u00bc\3\2\2\2\u00bb\u00b1\3\2\2\2\u00bb\u00b2\3\2\2\2\u00bb"+
		"\u00b5\3\2\2\2\u00bc\u00f7\3\2\2\2\u00bd\u00be\t\2\2\2\u00be\u00c9\7%"+
		"\2\2\u00bf\u00ca\5\60\31\2\u00c0\u00c1\7/\2\2\u00c1\u00c2\7\37\2\2\u00c2"+
		"\u00ca\5\34\17\2\u00c3\u00c4\7\25\2\2\u00c4\u00c7\7/\2\2\u00c5\u00c6\7"+
		"\37\2\2\u00c6\u00c8\5*\26\2\u00c7\u00c5\3\2\2\2\u00c7\u00c8\3\2\2\2\u00c8"+
		"\u00ca\3\2\2\2\u00c9\u00bf\3\2\2\2\u00c9\u00c0\3\2\2\2\u00c9\u00c3\3\2"+
		"\2\2\u00ca\u00f7\3\2\2\2\u00cb\u00cc\t\2\2\2\u00cc\u00d7\7\'\2\2\u00cd"+
		"\u00d8\5\60\31\2\u00ce\u00cf\7/\2\2\u00cf\u00d0\7\37\2\2\u00d0\u00d8\5"+
		"\36\20\2\u00d1\u00d2\7\25\2\2\u00d2\u00d5\7/\2\2\u00d3\u00d4\7\37\2\2"+
		"\u00d4\u00d6\5,\27\2\u00d5\u00d3\3\2\2\2\u00d5\u00d6\3\2\2\2\u00d6\u00d8"+
		"\3\2\2\2\u00d7\u00cd\3\2\2\2\u00d7\u00ce\3\2\2\2\u00d7\u00d1\3\2\2\2\u00d8"+
		"\u00f7\3\2\2\2\u00d9\u00da\t\2\2\2\u00da\u00e5\7)\2\2\u00db\u00e6\5\60"+
		"\31\2\u00dc\u00dd\7/\2\2\u00dd\u00de\7\37\2\2\u00de\u00e6\5 \21\2\u00df"+
		"\u00e0\7\25\2\2\u00e0\u00e3\7/\2\2\u00e1\u00e2\7\37\2\2\u00e2\u00e4\5"+
		"&\24\2\u00e3\u00e1\3\2\2\2\u00e3\u00e4\3\2\2\2\u00e4\u00e6\3\2\2\2\u00e5"+
		"\u00db\3\2\2\2\u00e5\u00dc\3\2\2\2\u00e5\u00df\3\2\2\2\u00e6\u00f7\3\2"+
		"\2\2\u00e7\u00e8\t\2\2\2\u00e8\u00f3\7(\2\2\u00e9\u00f4\5\60\31\2\u00ea"+
		"\u00eb\7/\2\2\u00eb\u00ec\7\37\2\2\u00ec\u00f4\5\"\22\2\u00ed\u00ee\7"+
		"\25\2\2\u00ee\u00f1\7/\2\2\u00ef\u00f0\7\37\2\2\u00f0\u00f2\5$\23\2\u00f1"+
		"\u00ef\3\2\2\2\u00f1\u00f2\3\2\2\2\u00f2\u00f4\3\2\2\2\u00f3\u00e9\3\2"+
		"\2\2\u00f3\u00ea\3\2\2\2\u00f3\u00ed\3\2\2\2\u00f4\u00f7\3\2\2\2\u00f5"+
		"\u00f7\5\30\r\2\u00f6\u00a8\3\2\2\2\u00f6\u00af\3\2\2\2\u00f6\u00bd\3"+
		"\2\2\2\u00f6\u00cb\3\2\2\2\u00f6\u00d9\3\2\2\2\u00f6\u00e7\3\2\2\2\u00f6"+
		"\u00f5\3\2\2\2\u00f7\27\3\2\2\2\u00f8\u00f9\t\2\2\2\u00f9\u00fa\7\17\2"+
		"\2\u00fa\u00fb\7\34\2\2\u00fb\u00fc\7/\2\2\u00fc\u00fd\7/\2\2\u00fd\31"+
		"\3\2\2\2\u00fe\u00ff\7+\2\2\u00ff\33\3\2\2\2\u0100\u0101\t\3\2\2\u0101"+
		"\35\3\2\2\2\u0102\u0103\t\4\2\2\u0103\37\3\2\2\2\u0104\u0105\7*\2\2\u0105"+
		"!\3\2\2\2\u0106\u0107\7.\2\2\u0107#\3\2\2\2\u0108\u010a\7\26\2\2\u0109"+
		"\u010b\7.\2\2\u010a\u0109\3\2\2\2\u010b\u010c\3\2\2\2\u010c\u010a\3\2"+
		"\2\2\u010c\u010d\3\2\2\2\u010d\u010e\3\2\2\2\u010e\u010f\7\27\2\2\u010f"+
		"%\3\2\2\2\u0110\u0112\7\26\2\2\u0111\u0113\7*\2\2\u0112\u0111\3\2\2\2"+
		"\u0113\u0114\3\2\2\2\u0114\u0112\3\2\2\2\u0114\u0115\3\2\2\2\u0115\u0116"+
		"\3\2\2\2\u0116\u0117\7\27\2\2\u0117\'\3\2\2\2\u0118\u011a\7\26\2\2\u0119"+
		"\u011b\7+\2\2\u011a\u0119\3\2\2\2\u011b\u011c\3\2\2\2\u011c\u011a\3\2"+
		"\2\2\u011c\u011d\3\2\2\2\u011d\u011e\3\2\2\2\u011e\u011f\7\27\2\2\u011f"+
		")\3\2\2\2\u0120\u0122\7\26\2\2\u0121\u0123\t\3\2\2\u0122\u0121\3\2\2\2"+
		"\u0123\u0124\3\2\2\2\u0124\u0122\3\2\2\2\u0124\u0125\3\2\2\2\u0125\u0126"+
		"\3\2\2\2\u0126\u0127\7\27\2\2\u0127+\3\2\2\2\u0128\u012a\7\26\2\2\u0129"+
		"\u012b\t\4\2\2\u012a\u0129\3\2\2\2\u012b\u012c\3\2\2\2\u012c\u012a\3\2"+
		"\2\2\u012c\u012d\3\2\2\2\u012d\u012e\3\2\2\2\u012e\u012f\7\27\2\2\u012f"+
		"-\3\2\2\2\u0130\u0132\7\32\2\2\u0131\u0133\t\5\2\2\u0132\u0131\3\2\2\2"+
		"\u0133\u0134\3\2\2\2\u0134\u0132\3\2\2\2\u0134\u0135\3\2\2\2\u0135\u0136"+
		"\3\2\2\2\u0136\u0137\7\33\2\2\u0137/\3\2\2\2\u0138\u013d\7/\2\2\u0139"+
		"\u013a\7\35\2\2\u013a\u013c\7/\2\2\u013b\u0139\3\2\2\2\u013c\u013f\3\2"+
		"\2\2\u013d\u013b\3\2\2\2\u013d\u013e\3\2\2\2\u013e\61\3\2\2\2\u013f\u013d"+
		"\3\2\2\2\u0140\u0142\58\35\2\u0141\u0140\3\2\2\2\u0142\u0145\3\2\2\2\u0143"+
		"\u0141\3\2\2\2\u0143\u0144\3\2\2\2\u0144\u0146\3\2\2\2\u0145\u0143\3\2"+
		"\2\2\u0146\u0147\7/\2\2\u0147\63\3\2\2\2\u0148\u014a\58\35\2\u0149\u0148"+
		"\3\2\2\2\u014a\u014d\3\2\2\2\u014b\u0149\3\2\2\2\u014b\u014c\3\2\2\2\u014c"+
		"\u014e\3\2\2\2\u014d\u014b\3\2\2\2\u014e\u014f\7/\2\2\u014f\65\3\2\2\2"+
		"\u0150\u0152\58\35\2\u0151\u0150\3\2\2\2\u0152\u0155\3\2\2\2\u0153\u0151"+
		"\3\2\2\2\u0153\u0154\3\2\2\2\u0154\u0156\3\2\2\2\u0155\u0153\3\2\2\2\u0156"+
		"\u0157\7/\2\2\u0157\67\3\2\2\2\u0158\u0159\7/\2\2\u0159\u015a\7\36\2\2"+
		"\u015a9\3\2\2\2\u015b\u015c\t\6\2\2\u015c;\3\2\2\2\u015d\u015f\7\64\2"+
		"\2\u015e\u015d\3\2\2\2\u015f\u0160\3\2\2\2\u0160\u015e\3\2\2\2\u0160\u0161"+
		"\3\2\2\2\u0161=\3\2\2\2+AEJQW\\gknt{~\u0080\u0087\u008b\u0093\u0098\u00a4"+
		"\u00ad\u00b9\u00bb\u00c7\u00c9\u00d5\u00d7\u00e3\u00e5\u00f1\u00f3\u00f6"+
		"\u010c\u0114\u011c\u0124\u012c\u0134\u013d\u0143\u014b\u0153\u0160";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}