// Generated from /Users/oroncal/workspace/tara/rt/src/monet/tara/compiler/parser/antlr/TaraM2Grammar.g4 by ANTLR 4.x

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
		PACKAGE=17, RIGHT_SQUARE=20, GENERIC=7, NEGATIVE_VALUE=41, DOC=49, LETTER=46, 
		CASE=2, CLOSE_AN=24, DEDENT=53, ABSTRACT=14, SINGLETON=6, BOOLEAN_VALUE=39, 
		MULTIPLE=3, UNKNOWN_TOKEN=54, WORD=12, OPEN_BRACKET=21, FINAL=13, IMPORT=16, 
		COMMA=26, IDENTIFIER=44, BASE=15, VAR=10, NL=51, DIGIT=45, INTENTION=8, 
		DOT=27, STRING_VALUE=43, DOUBLE_VALUE=42, POSITIVE=31, NEW_LINE_INDENT=52, 
		NATURAL_TYPE=35, NEGATIVE=32, HAS_NAME=9, DOUBLE_TYPE=36, BOOLEAN_TYPE=38, 
		SEMICOLON=30, REQUIRED=4, INT_TYPE=34, LIST=18, ROOT=5, OPEN_AN=23, CONCEPT=1, 
		COLON=25, ALIAS_TYPE=33, POSITIVE_VALUE=40, NEWLINE=47, PROPERTY=11, SPACES=48, 
		SP=50, STRING_TYPE=37, ASSIGN=28, LEFT_SQUARE=19, DOUBLE_COMMAS=29, CLOSE_BRACKET=22;
	public static final String[] tokenNames = {
		"<INVALID>", "'Concept'", "'case'", "'multiple'", "'required'", "'root'", 
		"'singleton'", "'generic'", "'intention'", "'has-name'", "'var'", "'property'", 
		"'Word'", "'final'", "'abstract'", "'base'", "'import'", "'package'", 
		"LIST", "'['", "']'", "'{'", "'}'", "'<'", "'>'", "':'", "','", "'.'", 
		"'='", "'\"'", "SEMICOLON", "'+'", "'-'", "'Alias'", "'Integer'", "'Natural'", 
		"'Double'", "'String'", "'Boolean'", "BOOLEAN_VALUE", "POSITIVE_VALUE", 
		"NEGATIVE_VALUE", "DOUBLE_VALUE", "STRING_VALUE", "IDENTIFIER", "DIGIT", 
		"LETTER", "NEWLINE", "SPACES", "DOC", "SP", "NL", "'indent'", "'dedent'", 
		"UNKNOWN_TOKEN"
	};
	public static final int
		RULE_root = 0, RULE_header = 1, RULE_imports = 2, RULE_packet = 3, RULE_concept = 4, 
		RULE_signature = 5, RULE_body = 6, RULE_conceptConstituents = 7, RULE_reference = 8, 
		RULE_word = 9, RULE_attribute = 10, RULE_naturalValue = 11, RULE_integerValue = 12, 
		RULE_doubleValue = 13, RULE_booleanValue = 14, RULE_stringValue = 15, 
		RULE_stringList = 16, RULE_booleanList = 17, RULE_naturalList = 18, RULE_integerList = 19, 
		RULE_doubleList = 20, RULE_annotations = 21, RULE_variableNames = 22, 
		RULE_headerReference = 23, RULE_externalReference = 24, RULE_identifierReference = 25, 
		RULE_hierarchy = 26, RULE_modifier = 27, RULE_doc = 28;
	public static final String[] ruleNames = {
		"root", "header", "imports", "packet", "concept", "signature", "body", 
		"conceptConstituents", "reference", "word", "attribute", "naturalValue", 
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
			setState(61);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(58); match(NEWLINE);
					}
					} 
				}
				setState(63);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			}
			setState(65);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				{
				setState(64); header();
				}
				break;
			}
			setState(70);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(67); match(NEWLINE);
				}
				}
				setState(72);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(73); concept();
			setState(77);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(74); match(NEWLINE);
				}
				}
				setState(79);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(80); match(EOF);
			}
		}
		catch (RecognitionException re) {
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
			setState(83);
			_la = _input.LA(1);
			if (_la==PACKAGE) {
				{
				setState(82); packet();
				}
			}

			setState(88);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(85); imports();
					}
					} 
				}
				setState(90);
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
			setState(91); match(NEWLINE);
			setState(92); match(IMPORT);
			setState(93); headerReference();
			}
		}
		catch (RecognitionException re) {
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
			setState(95); match(PACKAGE);
			setState(96); headerReference();
			}
		}
		catch (RecognitionException re) {
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
			setState(99);
			_la = _input.LA(1);
			if (_la==DOC) {
				{
				setState(98); doc();
				}
			}

			setState(101); signature();
			setState(103);
			_la = _input.LA(1);
			if (_la==OPEN_AN) {
				{
				setState(102); annotations();
				}
			}

			setState(106);
			_la = _input.LA(1);
			if (_la==NEW_LINE_INDENT) {
				{
				setState(105); body();
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
			setState(124);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(108); match(CASE);
				setState(109); match(IDENTIFIER);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(110); match(CONCEPT);
				setState(112);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << FINAL) | (1L << ABSTRACT) | (1L << BASE))) != 0)) {
					{
					setState(111); modifier();
					}
				}

				setState(114); match(IDENTIFIER);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(115); match(CONCEPT);
				setState(116); match(COLON);
				setState(117); identifierReference();
				setState(122);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << FINAL) | (1L << ABSTRACT) | (1L << BASE) | (1L << IDENTIFIER))) != 0)) {
					{
					setState(119);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << FINAL) | (1L << ABSTRACT) | (1L << BASE))) != 0)) {
						{
						setState(118); modifier();
						}
					}

					setState(121); match(IDENTIFIER);
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
			setState(126); match(NEW_LINE_INDENT);
			setState(133); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(127); conceptConstituents();
				setState(129); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(128); match(NEWLINE);
					}
					}
					setState(131); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NEWLINE );
				}
				}
				setState(135); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << CONCEPT) | (1L << CASE) | (1L << VAR) | (1L << PROPERTY) | (1L << DOC))) != 0) );
			setState(137); match(DEDENT);
			}
		}
		catch (RecognitionException re) {
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
			setState(143);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(139); attribute();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(140); reference();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(141); word();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(142); concept();
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
			setState(145); match(VAR);
			setState(146); identifierReference();
			setState(148);
			_la = _input.LA(1);
			if (_la==LIST) {
				{
				setState(147); match(LIST);
				}
			}

			setState(150); variableNames();
			}
		}
		catch (RecognitionException re) {
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
			setState(152); match(VAR);
			setState(153); match(WORD);
			setState(154); match(IDENTIFIER);
			setState(155); match(NEW_LINE_INDENT);
			setState(158); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(156); match(IDENTIFIER);
				setState(157); match(NEWLINE);
				}
				}
				setState(160); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==IDENTIFIER );
			setState(162); match(DEDENT);
			}
		}
		catch (RecognitionException re) {
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
			setState(241);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(164);
				_la = _input.LA(1);
				if ( !(_la==VAR || _la==PROPERTY) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(165); match(ALIAS_TYPE);
				setState(166); match(IDENTIFIER);
				setState(169);
				_la = _input.LA(1);
				if (_la==ASSIGN) {
					{
					setState(167); match(ASSIGN);
					setState(168); stringValue();
					}
				}

				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(171);
				_la = _input.LA(1);
				if ( !(_la==VAR || _la==PROPERTY) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(172); match(NATURAL_TYPE);
				setState(183);
				switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
				case 1:
					{
					setState(173); variableNames();
					}
					break;
				case 2:
					{
					setState(174); match(IDENTIFIER);
					setState(175); match(ASSIGN);
					setState(176); naturalValue();
					}
					break;
				case 3:
					{
					setState(177); match(LIST);
					setState(178); match(IDENTIFIER);
					setState(181);
					_la = _input.LA(1);
					if (_la==ASSIGN) {
						{
						setState(179); match(ASSIGN);
						setState(180); naturalList();
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
				setState(185);
				_la = _input.LA(1);
				if ( !(_la==VAR || _la==PROPERTY) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(186); match(INT_TYPE);
				setState(197);
				switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
				case 1:
					{
					setState(187); variableNames();
					}
					break;
				case 2:
					{
					setState(188); match(IDENTIFIER);
					setState(189); match(ASSIGN);
					setState(190); integerValue();
					}
					break;
				case 3:
					{
					setState(191); match(LIST);
					setState(192); match(IDENTIFIER);
					setState(195);
					_la = _input.LA(1);
					if (_la==ASSIGN) {
						{
						setState(193); match(ASSIGN);
						setState(194); integerList();
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
				setState(199);
				_la = _input.LA(1);
				if ( !(_la==VAR || _la==PROPERTY) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(200); match(DOUBLE_TYPE);
				setState(211);
				switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
				case 1:
					{
					setState(201); variableNames();
					}
					break;
				case 2:
					{
					setState(202); match(IDENTIFIER);
					setState(203); match(ASSIGN);
					setState(204); doubleValue();
					}
					break;
				case 3:
					{
					setState(205); match(LIST);
					setState(206); match(IDENTIFIER);
					setState(209);
					_la = _input.LA(1);
					if (_la==ASSIGN) {
						{
						setState(207); match(ASSIGN);
						setState(208); doubleList();
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
				setState(213);
				_la = _input.LA(1);
				if ( !(_la==VAR || _la==PROPERTY) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(214); match(BOOLEAN_TYPE);
				setState(225);
				switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
				case 1:
					{
					setState(215); variableNames();
					}
					break;
				case 2:
					{
					setState(216); match(IDENTIFIER);
					setState(217); match(ASSIGN);
					setState(218); booleanValue();
					}
					break;
				case 3:
					{
					setState(219); match(LIST);
					setState(220); match(IDENTIFIER);
					setState(223);
					_la = _input.LA(1);
					if (_la==ASSIGN) {
						{
						setState(221); match(ASSIGN);
						setState(222); booleanList();
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
				setState(227);
				_la = _input.LA(1);
				if ( !(_la==VAR || _la==PROPERTY) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(228); match(STRING_TYPE);
				setState(239);
				switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
				case 1:
					{
					setState(229); variableNames();
					}
					break;
				case 2:
					{
					setState(230); match(IDENTIFIER);
					setState(231); match(ASSIGN);
					setState(232); stringValue();
					}
					break;
				case 3:
					{
					setState(233); match(LIST);
					setState(234); match(IDENTIFIER);
					setState(237);
					_la = _input.LA(1);
					if (_la==ASSIGN) {
						{
						setState(235); match(ASSIGN);
						setState(236); stringList();
						}
					}

					}
					break;
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
		enterRule(_localctx, 22, RULE_naturalValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(243); match(POSITIVE_VALUE);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 24, RULE_integerValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(245);
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
		enterRule(_localctx, 26, RULE_doubleValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(247);
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
		enterRule(_localctx, 28, RULE_booleanValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(249); match(BOOLEAN_VALUE);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 30, RULE_stringValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(251); match(STRING_VALUE);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 32, RULE_stringList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(253); match(LEFT_SQUARE);
			setState(255); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(254); match(STRING_VALUE);
				}
				}
				setState(257); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==STRING_VALUE );
			setState(259); match(RIGHT_SQUARE);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 34, RULE_booleanList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(261); match(LEFT_SQUARE);
			setState(263); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(262); match(BOOLEAN_VALUE);
				}
				}
				setState(265); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==BOOLEAN_VALUE );
			setState(267); match(RIGHT_SQUARE);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 36, RULE_naturalList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(269); match(LEFT_SQUARE);
			setState(271); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(270); match(POSITIVE_VALUE);
				}
				}
				setState(273); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==POSITIVE_VALUE );
			setState(275); match(RIGHT_SQUARE);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 38, RULE_integerList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(277); match(LEFT_SQUARE);
			setState(279); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(278);
				_la = _input.LA(1);
				if ( !(_la==POSITIVE_VALUE || _la==NEGATIVE_VALUE) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				}
				}
				setState(281); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==POSITIVE_VALUE || _la==NEGATIVE_VALUE );
			setState(283); match(RIGHT_SQUARE);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 40, RULE_doubleList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(285); match(LEFT_SQUARE);
			setState(287); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(286);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << POSITIVE_VALUE) | (1L << NEGATIVE_VALUE) | (1L << DOUBLE_VALUE))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				}
				}
				setState(289); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << POSITIVE_VALUE) | (1L << NEGATIVE_VALUE) | (1L << DOUBLE_VALUE))) != 0) );
			setState(291); match(RIGHT_SQUARE);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 42, RULE_annotations);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(293); match(OPEN_AN);
			setState(295); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(294);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MULTIPLE) | (1L << REQUIRED) | (1L << ROOT) | (1L << SINGLETON) | (1L << GENERIC) | (1L << INTENTION) | (1L << HAS_NAME))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				}
				}
				setState(297); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MULTIPLE) | (1L << REQUIRED) | (1L << ROOT) | (1L << SINGLETON) | (1L << GENERIC) | (1L << INTENTION) | (1L << HAS_NAME))) != 0) );
			setState(299); match(CLOSE_AN);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 44, RULE_variableNames);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(301); match(IDENTIFIER);
			setState(306);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(302); match(COMMA);
				setState(303); match(IDENTIFIER);
				}
				}
				setState(308);
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
		enterRule(_localctx, 46, RULE_headerReference);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(312);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,37,_ctx);
			while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(309); hierarchy();
					}
					} 
				}
				setState(314);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,37,_ctx);
			}
			setState(315); match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 48, RULE_externalReference);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(320);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,38,_ctx);
			while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(317); hierarchy();
					}
					} 
				}
				setState(322);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,38,_ctx);
			}
			setState(323); match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 50, RULE_identifierReference);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(328);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,39,_ctx);
			while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(325); hierarchy();
					}
					} 
				}
				setState(330);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,39,_ctx);
			}
			setState(331); match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 52, RULE_hierarchy);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(333); match(IDENTIFIER);
			setState(334); match(DOT);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 54, RULE_modifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(336);
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
		enterRule(_localctx, 56, RULE_doc);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(339); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(338); match(DOC);
				}
				}
				setState(341); 
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\38\u015a\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\3\2\7\2>\n\2\f\2\16"+
		"\2A\13\2\3\2\5\2D\n\2\3\2\7\2G\n\2\f\2\16\2J\13\2\3\2\3\2\7\2N\n\2\f\2"+
		"\16\2Q\13\2\3\2\3\2\3\3\5\3V\n\3\3\3\7\3Y\n\3\f\3\16\3\\\13\3\3\4\3\4"+
		"\3\4\3\4\3\5\3\5\3\5\3\6\5\6f\n\6\3\6\3\6\5\6j\n\6\3\6\5\6m\n\6\3\7\3"+
		"\7\3\7\3\7\5\7s\n\7\3\7\3\7\3\7\3\7\3\7\5\7z\n\7\3\7\5\7}\n\7\5\7\177"+
		"\n\7\3\b\3\b\3\b\6\b\u0084\n\b\r\b\16\b\u0085\6\b\u0088\n\b\r\b\16\b\u0089"+
		"\3\b\3\b\3\t\3\t\3\t\3\t\5\t\u0092\n\t\3\n\3\n\3\n\5\n\u0097\n\n\3\n\3"+
		"\n\3\13\3\13\3\13\3\13\3\13\3\13\6\13\u00a1\n\13\r\13\16\13\u00a2\3\13"+
		"\3\13\3\f\3\f\3\f\3\f\3\f\5\f\u00ac\n\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\5\f\u00b8\n\f\5\f\u00ba\n\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\5\f\u00c6\n\f\5\f\u00c8\n\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\5\f\u00d4\n\f\5\f\u00d6\n\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\5\f\u00e2\n\f\5\f\u00e4\n\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\5\f\u00f0\n\f\5\f\u00f2\n\f\5\f\u00f4\n\f\3\r\3\r\3\16\3\16\3\17\3"+
		"\17\3\20\3\20\3\21\3\21\3\22\3\22\6\22\u0102\n\22\r\22\16\22\u0103\3\22"+
		"\3\22\3\23\3\23\6\23\u010a\n\23\r\23\16\23\u010b\3\23\3\23\3\24\3\24\6"+
		"\24\u0112\n\24\r\24\16\24\u0113\3\24\3\24\3\25\3\25\6\25\u011a\n\25\r"+
		"\25\16\25\u011b\3\25\3\25\3\26\3\26\6\26\u0122\n\26\r\26\16\26\u0123\3"+
		"\26\3\26\3\27\3\27\6\27\u012a\n\27\r\27\16\27\u012b\3\27\3\27\3\30\3\30"+
		"\3\30\7\30\u0133\n\30\f\30\16\30\u0136\13\30\3\31\7\31\u0139\n\31\f\31"+
		"\16\31\u013c\13\31\3\31\3\31\3\32\7\32\u0141\n\32\f\32\16\32\u0144\13"+
		"\32\3\32\3\32\3\33\7\33\u0149\n\33\f\33\16\33\u014c\13\33\3\33\3\33\3"+
		"\34\3\34\3\34\3\35\3\35\3\36\6\36\u0156\n\36\r\36\16\36\u0157\3\36\2\2"+
		"\37\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:\2\7"+
		"\3\2\f\r\3\2*+\3\2*,\3\2\5\13\3\2\17\21\u0171\2?\3\2\2\2\4U\3\2\2\2\6"+
		"]\3\2\2\2\ba\3\2\2\2\ne\3\2\2\2\f~\3\2\2\2\16\u0080\3\2\2\2\20\u0091\3"+
		"\2\2\2\22\u0093\3\2\2\2\24\u009a\3\2\2\2\26\u00f3\3\2\2\2\30\u00f5\3\2"+
		"\2\2\32\u00f7\3\2\2\2\34\u00f9\3\2\2\2\36\u00fb\3\2\2\2 \u00fd\3\2\2\2"+
		"\"\u00ff\3\2\2\2$\u0107\3\2\2\2&\u010f\3\2\2\2(\u0117\3\2\2\2*\u011f\3"+
		"\2\2\2,\u0127\3\2\2\2.\u012f\3\2\2\2\60\u013a\3\2\2\2\62\u0142\3\2\2\2"+
		"\64\u014a\3\2\2\2\66\u014f\3\2\2\28\u0152\3\2\2\2:\u0155\3\2\2\2<>\7\61"+
		"\2\2=<\3\2\2\2>A\3\2\2\2?=\3\2\2\2?@\3\2\2\2@C\3\2\2\2A?\3\2\2\2BD\5\4"+
		"\3\2CB\3\2\2\2CD\3\2\2\2DH\3\2\2\2EG\7\61\2\2FE\3\2\2\2GJ\3\2\2\2HF\3"+
		"\2\2\2HI\3\2\2\2IK\3\2\2\2JH\3\2\2\2KO\5\n\6\2LN\7\61\2\2ML\3\2\2\2NQ"+
		"\3\2\2\2OM\3\2\2\2OP\3\2\2\2PR\3\2\2\2QO\3\2\2\2RS\7\2\2\3S\3\3\2\2\2"+
		"TV\5\b\5\2UT\3\2\2\2UV\3\2\2\2VZ\3\2\2\2WY\5\6\4\2XW\3\2\2\2Y\\\3\2\2"+
		"\2ZX\3\2\2\2Z[\3\2\2\2[\5\3\2\2\2\\Z\3\2\2\2]^\7\61\2\2^_\7\22\2\2_`\5"+
		"\60\31\2`\7\3\2\2\2ab\7\23\2\2bc\5\60\31\2c\t\3\2\2\2df\5:\36\2ed\3\2"+
		"\2\2ef\3\2\2\2fg\3\2\2\2gi\5\f\7\2hj\5,\27\2ih\3\2\2\2ij\3\2\2\2jl\3\2"+
		"\2\2km\5\16\b\2lk\3\2\2\2lm\3\2\2\2m\13\3\2\2\2no\7\4\2\2o\177\7.\2\2"+
		"pr\7\3\2\2qs\58\35\2rq\3\2\2\2rs\3\2\2\2st\3\2\2\2t\177\7.\2\2uv\7\3\2"+
		"\2vw\7\33\2\2w|\5\64\33\2xz\58\35\2yx\3\2\2\2yz\3\2\2\2z{\3\2\2\2{}\7"+
		".\2\2|y\3\2\2\2|}\3\2\2\2}\177\3\2\2\2~n\3\2\2\2~p\3\2\2\2~u\3\2\2\2\177"+
		"\r\3\2\2\2\u0080\u0087\7\66\2\2\u0081\u0083\5\20\t\2\u0082\u0084\7\61"+
		"\2\2\u0083\u0082\3\2\2\2\u0084\u0085\3\2\2\2\u0085\u0083\3\2\2\2\u0085"+
		"\u0086\3\2\2\2\u0086\u0088\3\2\2\2\u0087\u0081\3\2\2\2\u0088\u0089\3\2"+
		"\2\2\u0089\u0087\3\2\2\2\u0089\u008a\3\2\2\2\u008a\u008b\3\2\2\2\u008b"+
		"\u008c\7\67\2\2\u008c\17\3\2\2\2\u008d\u0092\5\26\f\2\u008e\u0092\5\22"+
		"\n\2\u008f\u0092\5\24\13\2\u0090\u0092\5\n\6\2\u0091\u008d\3\2\2\2\u0091"+
		"\u008e\3\2\2\2\u0091\u008f\3\2\2\2\u0091\u0090\3\2\2\2\u0092\21\3\2\2"+
		"\2\u0093\u0094\7\f\2\2\u0094\u0096\5\64\33\2\u0095\u0097\7\24\2\2\u0096"+
		"\u0095\3\2\2\2\u0096\u0097\3\2\2\2\u0097\u0098\3\2\2\2\u0098\u0099\5."+
		"\30\2\u0099\23\3\2\2\2\u009a\u009b\7\f\2\2\u009b\u009c\7\16\2\2\u009c"+
		"\u009d\7.\2\2\u009d\u00a0\7\66\2\2\u009e\u009f\7.\2\2\u009f\u00a1\7\61"+
		"\2\2\u00a0\u009e\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2\u00a0\3\2\2\2\u00a2"+
		"\u00a3\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4\u00a5\7\67\2\2\u00a5\25\3\2\2"+
		"\2\u00a6\u00a7\t\2\2\2\u00a7\u00a8\7#\2\2\u00a8\u00ab\7.\2\2\u00a9\u00aa"+
		"\7\36\2\2\u00aa\u00ac\5 \21\2\u00ab\u00a9\3\2\2\2\u00ab\u00ac\3\2\2\2"+
		"\u00ac\u00f4\3\2\2\2\u00ad\u00ae\t\2\2\2\u00ae\u00b9\7%\2\2\u00af\u00ba"+
		"\5.\30\2\u00b0\u00b1\7.\2\2\u00b1\u00b2\7\36\2\2\u00b2\u00ba\5\30\r\2"+
		"\u00b3\u00b4\7\24\2\2\u00b4\u00b7\7.\2\2\u00b5\u00b6\7\36\2\2\u00b6\u00b8"+
		"\5&\24\2\u00b7\u00b5\3\2\2\2\u00b7\u00b8\3\2\2\2\u00b8\u00ba\3\2\2\2\u00b9"+
		"\u00af\3\2\2\2\u00b9\u00b0\3\2\2\2\u00b9\u00b3\3\2\2\2\u00ba\u00f4\3\2"+
		"\2\2\u00bb\u00bc\t\2\2\2\u00bc\u00c7\7$\2\2\u00bd\u00c8\5.\30\2\u00be"+
		"\u00bf\7.\2\2\u00bf\u00c0\7\36\2\2\u00c0\u00c8\5\32\16\2\u00c1\u00c2\7"+
		"\24\2\2\u00c2\u00c5\7.\2\2\u00c3\u00c4\7\36\2\2\u00c4\u00c6\5(\25\2\u00c5"+
		"\u00c3\3\2\2\2\u00c5\u00c6\3\2\2\2\u00c6\u00c8\3\2\2\2\u00c7\u00bd\3\2"+
		"\2\2\u00c7\u00be\3\2\2\2\u00c7\u00c1\3\2\2\2\u00c8\u00f4\3\2\2\2\u00c9"+
		"\u00ca\t\2\2\2\u00ca\u00d5\7&\2\2\u00cb\u00d6\5.\30\2\u00cc\u00cd\7.\2"+
		"\2\u00cd\u00ce\7\36\2\2\u00ce\u00d6\5\34\17\2\u00cf\u00d0\7\24\2\2\u00d0"+
		"\u00d3\7.\2\2\u00d1\u00d2\7\36\2\2\u00d2\u00d4\5*\26\2\u00d3\u00d1\3\2"+
		"\2\2\u00d3\u00d4\3\2\2\2\u00d4\u00d6\3\2\2\2\u00d5\u00cb\3\2\2\2\u00d5"+
		"\u00cc\3\2\2\2\u00d5\u00cf\3\2\2\2\u00d6\u00f4\3\2\2\2\u00d7\u00d8\t\2"+
		"\2\2\u00d8\u00e3\7(\2\2\u00d9\u00e4\5.\30\2\u00da\u00db\7.\2\2\u00db\u00dc"+
		"\7\36\2\2\u00dc\u00e4\5\36\20\2\u00dd\u00de\7\24\2\2\u00de\u00e1\7.\2"+
		"\2\u00df\u00e0\7\36\2\2\u00e0\u00e2\5$\23\2\u00e1\u00df\3\2\2\2\u00e1"+
		"\u00e2\3\2\2\2\u00e2\u00e4\3\2\2\2\u00e3\u00d9\3\2\2\2\u00e3\u00da\3\2"+
		"\2\2\u00e3\u00dd\3\2\2\2\u00e4\u00f4\3\2\2\2\u00e5\u00e6\t\2\2\2\u00e6"+
		"\u00f1\7\'\2\2\u00e7\u00f2\5.\30\2\u00e8\u00e9\7.\2\2\u00e9\u00ea\7\36"+
		"\2\2\u00ea\u00f2\5 \21\2\u00eb\u00ec\7\24\2\2\u00ec\u00ef\7.\2\2\u00ed"+
		"\u00ee\7\36\2\2\u00ee\u00f0\5\"\22\2\u00ef\u00ed\3\2\2\2\u00ef\u00f0\3"+
		"\2\2\2\u00f0\u00f2\3\2\2\2\u00f1\u00e7\3\2\2\2\u00f1\u00e8\3\2\2\2\u00f1"+
		"\u00eb\3\2\2\2\u00f2\u00f4\3\2\2\2\u00f3\u00a6\3\2\2\2\u00f3\u00ad\3\2"+
		"\2\2\u00f3\u00bb\3\2\2\2\u00f3\u00c9\3\2\2\2\u00f3\u00d7\3\2\2\2\u00f3"+
		"\u00e5\3\2\2\2\u00f4\27\3\2\2\2\u00f5\u00f6\7*\2\2\u00f6\31\3\2\2\2\u00f7"+
		"\u00f8\t\3\2\2\u00f8\33\3\2\2\2\u00f9\u00fa\t\4\2\2\u00fa\35\3\2\2\2\u00fb"+
		"\u00fc\7)\2\2\u00fc\37\3\2\2\2\u00fd\u00fe\7-\2\2\u00fe!\3\2\2\2\u00ff"+
		"\u0101\7\25\2\2\u0100\u0102\7-\2\2\u0101\u0100\3\2\2\2\u0102\u0103\3\2"+
		"\2\2\u0103\u0101\3\2\2\2\u0103\u0104\3\2\2\2\u0104\u0105\3\2\2\2\u0105"+
		"\u0106\7\26\2\2\u0106#\3\2\2\2\u0107\u0109\7\25\2\2\u0108\u010a\7)\2\2"+
		"\u0109\u0108\3\2\2\2\u010a\u010b\3\2\2\2\u010b\u0109\3\2\2\2\u010b\u010c"+
		"\3\2\2\2\u010c\u010d\3\2\2\2\u010d\u010e\7\26\2\2\u010e%\3\2\2\2\u010f"+
		"\u0111\7\25\2\2\u0110\u0112\7*\2\2\u0111\u0110\3\2\2\2\u0112\u0113\3\2"+
		"\2\2\u0113\u0111\3\2\2\2\u0113\u0114\3\2\2\2\u0114\u0115\3\2\2\2\u0115"+
		"\u0116\7\26\2\2\u0116\'\3\2\2\2\u0117\u0119\7\25\2\2\u0118\u011a\t\3\2"+
		"\2\u0119\u0118\3\2\2\2\u011a\u011b\3\2\2\2\u011b\u0119\3\2\2\2\u011b\u011c"+
		"\3\2\2\2\u011c\u011d\3\2\2\2\u011d\u011e\7\26\2\2\u011e)\3\2\2\2\u011f"+
		"\u0121\7\25\2\2\u0120\u0122\t\4\2\2\u0121\u0120\3\2\2\2\u0122\u0123\3"+
		"\2\2\2\u0123\u0121\3\2\2\2\u0123\u0124\3\2\2\2\u0124\u0125\3\2\2\2\u0125"+
		"\u0126\7\26\2\2\u0126+\3\2\2\2\u0127\u0129\7\31\2\2\u0128\u012a\t\5\2"+
		"\2\u0129\u0128\3\2\2\2\u012a\u012b\3\2\2\2\u012b\u0129\3\2\2\2\u012b\u012c"+
		"\3\2\2\2\u012c\u012d\3\2\2\2\u012d\u012e\7\32\2\2\u012e-\3\2\2\2\u012f"+
		"\u0134\7.\2\2\u0130\u0131\7\34\2\2\u0131\u0133\7.\2\2\u0132\u0130\3\2"+
		"\2\2\u0133\u0136\3\2\2\2\u0134\u0132\3\2\2\2\u0134\u0135\3\2\2\2\u0135"+
		"/\3\2\2\2\u0136\u0134\3\2\2\2\u0137\u0139\5\66\34\2\u0138\u0137\3\2\2"+
		"\2\u0139\u013c\3\2\2\2\u013a\u0138\3\2\2\2\u013a\u013b\3\2\2\2\u013b\u013d"+
		"\3\2\2\2\u013c\u013a\3\2\2\2\u013d\u013e\7.\2\2\u013e\61\3\2\2\2\u013f"+
		"\u0141\5\66\34\2\u0140\u013f\3\2\2\2\u0141\u0144\3\2\2\2\u0142\u0140\3"+
		"\2\2\2\u0142\u0143\3\2\2\2\u0143\u0145\3\2\2\2\u0144\u0142\3\2\2\2\u0145"+
		"\u0146\7.\2\2\u0146\63\3\2\2\2\u0147\u0149\5\66\34\2\u0148\u0147\3\2\2"+
		"\2\u0149\u014c\3\2\2\2\u014a\u0148\3\2\2\2\u014a\u014b\3\2\2\2\u014b\u014d"+
		"\3\2\2\2\u014c\u014a\3\2\2\2\u014d\u014e\7.\2\2\u014e\65\3\2\2\2\u014f"+
		"\u0150\7.\2\2\u0150\u0151\7\35\2\2\u0151\67\3\2\2\2\u0152\u0153\t\6\2"+
		"\2\u01539\3\2\2\2\u0154\u0156\7\63\2\2\u0155\u0154\3\2\2\2\u0156\u0157"+
		"\3\2\2\2\u0157\u0155\3\2\2\2\u0157\u0158\3\2\2\2\u0158;\3\2\2\2+?CHOU"+
		"Zeilry|~\u0085\u0089\u0091\u0096\u00a2\u00ab\u00b7\u00b9\u00c5\u00c7\u00d3"+
		"\u00d5\u00e1\u00e3\u00ef\u00f1\u00f3\u0103\u010b\u0113\u011b\u0123\u012b"+
		"\u0134\u013a\u0142\u014a\u0157";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}