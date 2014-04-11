
    package monet.tara.compiler.parser.antlr;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TaraM2Grammar extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		RIGHT_SQUARE=18, GENERIC=15, NEGATIVE_VALUE=39, LETTER=44, DOC=47, NEW=11,
		CLOSE_AN=22, DEDENT=51, ABSTRACT=3, SINGLETON=10, MORPH=13, BOOLEAN_VALUE=37,
		MULTIPLE=4, UNKNOWN_TOKEN=52, WORD=14, OPEN_BRACKET=19, FINAL=2, COMMA=24,
		IDENTIFIER=42, POLYMORPHIC=12, VAR=8, NL=49, DIGIT=43, DOT=25, STRING_VALUE=41,
		DOUBLE_VALUE=40, POSITIVE=29, NEW_LINE_INDENT=50, NATURAL_TYPE=33, NEGATIVE=30,
		DOUBLE_TYPE=34, BOOLEAN_TYPE=36, SEMICOLON=28, INT_TYPE=32, LIST=16, ROOT=9,
		HAS_CODE=6, OPEN_AN=21, CONCEPT=1, OPTIONAL=5, EXTENSIBLE=7, COLON=23,
		POSITIVE_VALUE=38, NEWLINE=45, SPACES=46, SP=48, STRING_TYPE=35, ASSIGN=26,
		UID_TYPE=31, LEFT_SQUARE=17, DOUBLE_COMMAS=27, CLOSE_BRACKET=20;
	public static final String[] tokenNames = {
		"<INVALID>", "'Concept'", "'final'", "'abstract'", "'multiple'", "'optional'",
		"'has-code'", "'extensible'", "'var'", "'root'", "'singleton'", "'new'",
		"'polymorphic'", "'morph'", "'Word'", "'generic'", "LIST", "'['", "']'",
		"'{'", "'}'", "'<'", "'>'", "':'", "','", "'.'", "'='", "'\"'", "SEMICOLON",
		"'+'", "'-'", "'Uid'", "'Integer'", "'Natural'", "'Double'", "'String'",
		"'Boolean'", "BOOLEAN_VALUE", "POSITIVE_VALUE", "NEGATIVE_VALUE", "DOUBLE_VALUE",
		"STRING_VALUE", "IDENTIFIER", "DIGIT", "LETTER", "NEWLINE", "SPACES",
		"DOC", "SP", "NL", "'indent'", "'dedent'", "UNKNOWN_TOKEN"
	};
	public static final int
		RULE_root = 0, RULE_concept = 1, RULE_signature = 2, RULE_body = 3, RULE_conceptConstituents = 4,
		RULE_reference = 5, RULE_word = 6, RULE_conceptInjection = 7, RULE_attribute = 8,
		RULE_stringValue = 9, RULE_booleanValue = 10, RULE_integerValue = 11,
		RULE_doubleValue = 12, RULE_naturalValue = 13, RULE_stringList = 14, RULE_booleanList = 15,
		RULE_integerList = 16, RULE_doubleList = 17, RULE_naturalList = 18, RULE_annotations = 19,
		RULE_variableNames = 20, RULE_referenceIdentifier = 21, RULE_modifier = 22,
		RULE_doc = 23;
	public static final String[] ruleNames = {
		"root", "concept", "signature", "body", "conceptConstituents", "reference",
		"word", "conceptInjection", "attribute", "stringValue", "booleanValue",
		"integerValue", "doubleValue", "naturalValue", "stringList", "booleanList",
		"integerList", "doubleList", "naturalList", "annotations", "variableNames",
		"referenceIdentifier", "modifier", "doc"
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
		public List<ConceptContext> concept() {
			return getRuleContexts(ConceptContext.class);
		}
		public TerminalNode EOF() { return getToken(TaraM2Grammar.EOF, 0); }
		public TerminalNode NEWLINE(int i) {
			return getToken(TaraM2Grammar.NEWLINE, i);
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
			enterOuterAlt(_localctx, 1);
			{
			setState(52);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << CONCEPT) | (1L << NEWLINE) | (1L << DOC))) != 0)) {
				{
				setState(50);
				switch (_input.LA(1)) {
				case CONCEPT:
				case DOC:
					{
					setState(48); concept();
					}
					break;
				case NEWLINE:
					{
					setState(49); match(NEWLINE);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(54);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(55); match(EOF);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 2, RULE_concept);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
			_la = _input.LA(1);
			if (_la==DOC) {
				{
				setState(57); doc();
				}
			}

			setState(60); signature();
			setState(62);
			_la = _input.LA(1);
			if (_la==OPEN_AN) {
				{
				setState(61); annotations();
				}
			}

			setState(65);
			_la = _input.LA(1);
			if (_la==NEW_LINE_INDENT) {
				{
				setState(64); body();
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
		public TerminalNode MORPH() { return getToken(TaraM2Grammar.MORPH, 0); }
		public ReferenceIdentifierContext referenceIdentifier() {
			return getRuleContext(ReferenceIdentifierContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(TaraM2Grammar.IDENTIFIER, 0); }
		public ModifierContext modifier() {
			return getRuleContext(ModifierContext.class,0);
		}
		public TerminalNode POLYMORPHIC() { return getToken(TaraM2Grammar.POLYMORPHIC, 0); }
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
		enterRule(_localctx, 4, RULE_signature);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(67); match(CONCEPT);
			setState(70);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(68); match(COLON);
				setState(69); referenceIdentifier();
				}
			}

			setState(79);
			switch (_input.LA(1)) {
			case POLYMORPHIC:
				{
				setState(72); match(POLYMORPHIC);
				}
				break;
			case FINAL:
			case ABSTRACT:
			case MORPH:
			case IDENTIFIER:
				{
				setState(74);
				_la = _input.LA(1);
				if (_la==FINAL || _la==ABSTRACT) {
					{
					setState(73); modifier();
					}
				}

				setState(77);
				_la = _input.LA(1);
				if (_la==MORPH) {
					{
					setState(76); match(MORPH);
					}
				}

				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(81); match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 6, RULE_body);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(83); match(NEW_LINE_INDENT);
			setState(90);
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(84); conceptConstituents();
				setState(86);
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(85); match(NEWLINE);
					}
					}
					setState(88);
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NEWLINE );
				}
				}
				setState(92);
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << CONCEPT) | (1L << VAR) | (1L << NEW) | (1L << DOC))) != 0) );
			setState(94); match(DEDENT);
			}
		}
		catch (RecognitionException re) {
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
		public ConceptInjectionContext conceptInjection() {
			return getRuleContext(ConceptInjectionContext.class,0);
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
		enterRule(_localctx, 8, RULE_conceptConstituents);
		try {
			setState(101);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(96); attribute();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(97); reference();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(98); word();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(99); concept();
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(100); conceptInjection();
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
		public ReferenceIdentifierContext referenceIdentifier() {
			return getRuleContext(ReferenceIdentifierContext.class,0);
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
		enterRule(_localctx, 10, RULE_reference);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(103); match(VAR);
			setState(104); referenceIdentifier();
			setState(106);
			_la = _input.LA(1);
			if (_la==LIST) {
				{
				setState(105); match(LIST);
				}
			}

			setState(108); variableNames();
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 12, RULE_word);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110); match(VAR);
			setState(111); match(WORD);
			setState(112); match(IDENTIFIER);
			setState(113); match(NEW_LINE_INDENT);
			setState(116);
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(114); match(IDENTIFIER);
				setState(115); match(NEWLINE);
				}
				}
				setState(118);
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==IDENTIFIER );
			setState(120); match(DEDENT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConceptInjectionContext extends ParserRuleContext {
		public TerminalNode NEW() { return getToken(TaraM2Grammar.NEW, 0); }
		public ReferenceIdentifierContext referenceIdentifier() {
			return getRuleContext(ReferenceIdentifierContext.class,0);
		}
		public AnnotationsContext annotations() {
			return getRuleContext(AnnotationsContext.class,0);
		}
		public ConceptInjectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conceptInjection; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).enterConceptInjection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).exitConceptInjection(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TaraM2GrammarVisitor ) return ((TaraM2GrammarVisitor<? extends T>)visitor).visitConceptInjection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConceptInjectionContext conceptInjection() throws RecognitionException {
		ConceptInjectionContext _localctx = new ConceptInjectionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_conceptInjection);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(122); match(NEW);
			setState(123); referenceIdentifier();
			setState(125);
			_la = _input.LA(1);
			if (_la==OPEN_AN) {
				{
				setState(124); annotations();
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
		public TerminalNode INT_TYPE() { return getToken(TaraM2Grammar.INT_TYPE, 0); }
		public BooleanValueContext booleanValue() {
			return getRuleContext(BooleanValueContext.class,0);
		}
		public TerminalNode BOOLEAN_TYPE() { return getToken(TaraM2Grammar.BOOLEAN_TYPE, 0); }
		public VariableNamesContext variableNames() {
			return getRuleContext(VariableNamesContext.class,0);
		}
		public TerminalNode NATURAL_TYPE() { return getToken(TaraM2Grammar.NATURAL_TYPE, 0); }
		public IntegerListContext integerList() {
			return getRuleContext(IntegerListContext.class,0);
		}
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
		public TerminalNode UID_TYPE() { return getToken(TaraM2Grammar.UID_TYPE, 0); }
		public TerminalNode LIST() { return getToken(TaraM2Grammar.LIST, 0); }
		public TerminalNode DOUBLE_TYPE() { return getToken(TaraM2Grammar.DOUBLE_TYPE, 0); }
		public NaturalValueContext naturalValue() {
			return getRuleContext(NaturalValueContext.class,0);
		}
		public NaturalListContext naturalList() {
			return getRuleContext(NaturalListContext.class,0);
		}
		public DoubleListContext doubleList() {
			return getRuleContext(DoubleListContext.class,0);
		}
		public StringListContext stringList() {
			return getRuleContext(StringListContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(TaraM2Grammar.IDENTIFIER, 0); }
		public TerminalNode ASSIGN() { return getToken(TaraM2Grammar.ASSIGN, 0); }
		public TerminalNode VAR() { return getToken(TaraM2Grammar.VAR, 0); }
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
		enterRule(_localctx, 16, RULE_attribute);
		int _la;
		try {
			setState(204);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(127); match(VAR);
				setState(128); match(UID_TYPE);
				setState(129); match(IDENTIFIER);
				setState(132);
				_la = _input.LA(1);
				if (_la==ASSIGN) {
					{
					setState(130); match(ASSIGN);
					setState(131); stringValue();
					}
				}

				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(134); match(VAR);
				setState(135); match(INT_TYPE);
				setState(146);
				switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
				case 1:
					{
					setState(136); variableNames();
					}
					break;

				case 2:
					{
					setState(137); match(IDENTIFIER);
					setState(138); match(ASSIGN);
					setState(139); integerValue();
					}
					break;

				case 3:
					{
					setState(140); match(LIST);
					setState(141); match(IDENTIFIER);
					setState(144);
					_la = _input.LA(1);
					if (_la==ASSIGN) {
						{
						setState(142); match(ASSIGN);
						setState(143); integerList();
						}
					}

					}
					break;
				}
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(148); match(VAR);
				setState(149); match(DOUBLE_TYPE);
				setState(160);
				switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
				case 1:
					{
					setState(150); variableNames();
					}
					break;

				case 2:
					{
					setState(151); match(IDENTIFIER);
					setState(152); match(ASSIGN);
					setState(153); doubleValue();
					}
					break;

				case 3:
					{
					setState(154); match(LIST);
					setState(155); match(IDENTIFIER);
					setState(158);
					_la = _input.LA(1);
					if (_la==ASSIGN) {
						{
						setState(156); match(ASSIGN);
						setState(157); doubleList();
						}
					}

					}
					break;
				}
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(162); match(VAR);
				setState(163); match(NATURAL_TYPE);
				setState(174);
				switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
				case 1:
					{
					setState(164); variableNames();
					}
					break;

				case 2:
					{
					setState(165); match(IDENTIFIER);
					setState(166); match(ASSIGN);
					setState(167); naturalValue();
					}
					break;

				case 3:
					{
					setState(168); match(LIST);
					setState(169); match(IDENTIFIER);
					setState(172);
					_la = _input.LA(1);
					if (_la==ASSIGN) {
						{
						setState(170); match(ASSIGN);
						setState(171); naturalList();
						}
					}

					}
					break;
				}
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(176); match(VAR);
				setState(177); match(BOOLEAN_TYPE);
				setState(188);
				switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
				case 1:
					{
					setState(178); variableNames();
					}
					break;

				case 2:
					{
					setState(179); match(IDENTIFIER);
					setState(180); match(ASSIGN);
					setState(181); booleanValue();
					}
					break;

				case 3:
					{
					setState(182); match(LIST);
					setState(183); match(IDENTIFIER);
					setState(186);
					_la = _input.LA(1);
					if (_la==ASSIGN) {
						{
						setState(184); match(ASSIGN);
						setState(185); booleanList();
						}
					}

					}
					break;
				}
				}
				break;

			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(190); match(VAR);
				setState(191); match(STRING_TYPE);
				setState(202);
				switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
				case 1:
					{
					setState(192); variableNames();
					}
					break;

				case 2:
					{
					setState(193); match(IDENTIFIER);
					setState(194); match(ASSIGN);
					setState(195); stringValue();
					}
					break;

				case 3:
					{
					setState(196); match(LIST);
					setState(197); match(IDENTIFIER);
					setState(200);
					_la = _input.LA(1);
					if (_la==ASSIGN) {
						{
						setState(198); match(ASSIGN);
						setState(199); stringList();
						}
					}

					}
					break;
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
		enterRule(_localctx, 18, RULE_stringValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(206); match(STRING_VALUE);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 20, RULE_booleanValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(208); match(BOOLEAN_VALUE);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 22, RULE_integerValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(210);
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
		enterRule(_localctx, 24, RULE_doubleValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(212);
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
		enterRule(_localctx, 26, RULE_naturalValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(214); match(POSITIVE_VALUE);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 28, RULE_stringList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(216); match(LEFT_SQUARE);
			setState(218);
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(217); match(STRING_VALUE);
				}
				}
				setState(220);
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==STRING_VALUE );
			setState(222); match(RIGHT_SQUARE);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 30, RULE_booleanList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(224); match(LEFT_SQUARE);
			setState(226);
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(225); match(BOOLEAN_VALUE);
				}
				}
				setState(228);
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==BOOLEAN_VALUE );
			setState(230); match(RIGHT_SQUARE);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 32, RULE_integerList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(232); match(LEFT_SQUARE);
			setState(234);
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(233);
				_la = _input.LA(1);
				if ( !(_la==POSITIVE_VALUE || _la==NEGATIVE_VALUE) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				}
				}
				setState(236);
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==POSITIVE_VALUE || _la==NEGATIVE_VALUE );
			setState(238); match(RIGHT_SQUARE);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 34, RULE_doubleList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(240); match(LEFT_SQUARE);
			setState(242);
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(241);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << POSITIVE_VALUE) | (1L << NEGATIVE_VALUE) | (1L << DOUBLE_VALUE))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				}
				}
				setState(244);
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << POSITIVE_VALUE) | (1L << NEGATIVE_VALUE) | (1L << DOUBLE_VALUE))) != 0) );
			setState(246); match(RIGHT_SQUARE);
			}
		}
		catch (RecognitionException re) {
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
			setState(248); match(LEFT_SQUARE);
			setState(250);
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(249); match(POSITIVE_VALUE);
				}
				}
				setState(252);
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==POSITIVE_VALUE );
			setState(254); match(RIGHT_SQUARE);
			}
		}
		catch (RecognitionException re) {
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
		public List<TerminalNode> OPTIONAL() { return getTokens(TaraM2Grammar.OPTIONAL); }
		public TerminalNode OPEN_AN() { return getToken(TaraM2Grammar.OPEN_AN, 0); }
		public List<TerminalNode> ROOT() { return getTokens(TaraM2Grammar.ROOT); }
		public TerminalNode EXTENSIBLE(int i) {
			return getToken(TaraM2Grammar.EXTENSIBLE, i);
		}
		public TerminalNode MULTIPLE(int i) {
			return getToken(TaraM2Grammar.MULTIPLE, i);
		}
		public TerminalNode CLOSE_AN() { return getToken(TaraM2Grammar.CLOSE_AN, 0); }
		public TerminalNode OPTIONAL(int i) {
			return getToken(TaraM2Grammar.OPTIONAL, i);
		}
		public TerminalNode HAS_CODE(int i) {
			return getToken(TaraM2Grammar.HAS_CODE, i);
		}
		public List<TerminalNode> HAS_CODE() { return getTokens(TaraM2Grammar.HAS_CODE); }
		public List<TerminalNode> GENERIC() { return getTokens(TaraM2Grammar.GENERIC); }
		public List<TerminalNode> EXTENSIBLE() { return getTokens(TaraM2Grammar.EXTENSIBLE); }
		public List<TerminalNode> MULTIPLE() { return getTokens(TaraM2Grammar.MULTIPLE); }
		public TerminalNode ROOT(int i) {
			return getToken(TaraM2Grammar.ROOT, i);
		}
		public TerminalNode SINGLETON(int i) {
			return getToken(TaraM2Grammar.SINGLETON, i);
		}
		public List<TerminalNode> SINGLETON() { return getTokens(TaraM2Grammar.SINGLETON); }
		public TerminalNode GENERIC(int i) {
			return getToken(TaraM2Grammar.GENERIC, i);
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
		enterRule(_localctx, 38, RULE_annotations);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(256); match(OPEN_AN);
			setState(258);
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(257);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MULTIPLE) | (1L << OPTIONAL) | (1L << HAS_CODE) | (1L << EXTENSIBLE) | (1L << ROOT) | (1L << SINGLETON) | (1L << GENERIC))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				}
				}
				setState(260);
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MULTIPLE) | (1L << OPTIONAL) | (1L << HAS_CODE) | (1L << EXTENSIBLE) | (1L << ROOT) | (1L << SINGLETON) | (1L << GENERIC))) != 0) );
			setState(262); match(CLOSE_AN);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 40, RULE_variableNames);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(264); match(IDENTIFIER);
			setState(269);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(265); match(COMMA);
				setState(266); match(IDENTIFIER);
				}
				}
				setState(271);
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

	public static class ReferenceIdentifierContext extends ParserRuleContext {
		public List<TerminalNode> DOT() { return getTokens(TaraM2Grammar.DOT); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(TaraM2Grammar.IDENTIFIER, i);
		}
		public List<TerminalNode> IDENTIFIER() { return getTokens(TaraM2Grammar.IDENTIFIER); }
		public TerminalNode DOT(int i) {
			return getToken(TaraM2Grammar.DOT, i);
		}
		public ReferenceIdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_referenceIdentifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).enterReferenceIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).exitReferenceIdentifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TaraM2GrammarVisitor ) return ((TaraM2GrammarVisitor<? extends T>)visitor).visitReferenceIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReferenceIdentifierContext referenceIdentifier() throws RecognitionException {
		ReferenceIdentifierContext _localctx = new ReferenceIdentifierContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_referenceIdentifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(272); match(IDENTIFIER);
			setState(277);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DOT) {
				{
				{
				setState(273); match(DOT);
				setState(274); match(IDENTIFIER);
				}
				}
				setState(279);
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

	public static class ModifierContext extends ParserRuleContext {
		public TerminalNode FINAL() { return getToken(TaraM2Grammar.FINAL, 0); }
		public TerminalNode ABSTRACT() { return getToken(TaraM2Grammar.ABSTRACT, 0); }
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
		enterRule(_localctx, 44, RULE_modifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(280);
			_la = _input.LA(1);
			if ( !(_la==FINAL || _la==ABSTRACT) ) {
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
		enterRule(_localctx, 46, RULE_doc);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(283);
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(282); match(DOC);
				}
				}
				setState(285);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\66\u0122\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\3\2\3\2\7\2\65\n\2\f\2\16\28\13\2\3\2\3\2\3\3\5\3=\n\3\3\3\3\3\5\3A\n"+
		"\3\3\3\5\3D\n\3\3\4\3\4\3\4\5\4I\n\4\3\4\3\4\5\4M\n\4\3\4\5\4P\n\4\5\4"+
		"R\n\4\3\4\3\4\3\5\3\5\3\5\6\5Y\n\5\r\5\16\5Z\6\5]\n\5\r\5\16\5^\3\5\3"+
		"\5\3\6\3\6\3\6\3\6\3\6\5\6h\n\6\3\7\3\7\3\7\5\7m\n\7\3\7\3\7\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\6\bw\n\b\r\b\16\bx\3\b\3\b\3\t\3\t\3\t\5\t\u0080\n\t\3"+
		"\n\3\n\3\n\3\n\3\n\5\n\u0087\n\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\5\n\u0093\n\n\5\n\u0095\n\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\5\n\u00a1\n\n\5\n\u00a3\n\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5"+
		"\n\u00af\n\n\5\n\u00b1\n\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n"+
		"\u00bd\n\n\5\n\u00bf\n\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u00cb"+
		"\n\n\5\n\u00cd\n\n\5\n\u00cf\n\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3"+
		"\17\3\17\3\20\3\20\6\20\u00dd\n\20\r\20\16\20\u00de\3\20\3\20\3\21\3\21"+
		"\6\21\u00e5\n\21\r\21\16\21\u00e6\3\21\3\21\3\22\3\22\6\22\u00ed\n\22"+
		"\r\22\16\22\u00ee\3\22\3\22\3\23\3\23\6\23\u00f5\n\23\r\23\16\23\u00f6"+
		"\3\23\3\23\3\24\3\24\6\24\u00fd\n\24\r\24\16\24\u00fe\3\24\3\24\3\25\3"+
		"\25\6\25\u0105\n\25\r\25\16\25\u0106\3\25\3\25\3\26\3\26\3\26\7\26\u010e"+
		"\n\26\f\26\16\26\u0111\13\26\3\27\3\27\3\27\7\27\u0116\n\27\f\27\16\27"+
		"\u0119\13\27\3\30\3\30\3\31\6\31\u011e\n\31\r\31\16\31\u011f\3\31\2\2"+
		"\32\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\2\6\3\2()\3\2("+
		"*\5\2\6\t\13\f\21\21\3\2\4\5\u0139\2\66\3\2\2\2\4<\3\2\2\2\6E\3\2\2\2"+
		"\bU\3\2\2\2\ng\3\2\2\2\fi\3\2\2\2\16p\3\2\2\2\20|\3\2\2\2\22\u00ce\3\2"+
		"\2\2\24\u00d0\3\2\2\2\26\u00d2\3\2\2\2\30\u00d4\3\2\2\2\32\u00d6\3\2\2"+
		"\2\34\u00d8\3\2\2\2\36\u00da\3\2\2\2 \u00e2\3\2\2\2\"\u00ea\3\2\2\2$\u00f2"+
		"\3\2\2\2&\u00fa\3\2\2\2(\u0102\3\2\2\2*\u010a\3\2\2\2,\u0112\3\2\2\2."+
		"\u011a\3\2\2\2\60\u011d\3\2\2\2\62\65\5\4\3\2\63\65\7/\2\2\64\62\3\2\2"+
		"\2\64\63\3\2\2\2\658\3\2\2\2\66\64\3\2\2\2\66\67\3\2\2\2\679\3\2\2\28"+
		"\66\3\2\2\29:\7\2\2\3:\3\3\2\2\2;=\5\60\31\2<;\3\2\2\2<=\3\2\2\2=>\3\2"+
		"\2\2>@\5\6\4\2?A\5(\25\2@?\3\2\2\2@A\3\2\2\2AC\3\2\2\2BD\5\b\5\2CB\3\2"+
		"\2\2CD\3\2\2\2D\5\3\2\2\2EH\7\3\2\2FG\7\31\2\2GI\5,\27\2HF\3\2\2\2HI\3"+
		"\2\2\2IQ\3\2\2\2JR\7\16\2\2KM\5.\30\2LK\3\2\2\2LM\3\2\2\2MO\3\2\2\2NP"+
		"\7\17\2\2ON\3\2\2\2OP\3\2\2\2PR\3\2\2\2QJ\3\2\2\2QL\3\2\2\2RS\3\2\2\2"+
		"ST\7,\2\2T\7\3\2\2\2U\\\7\64\2\2VX\5\n\6\2WY\7/\2\2XW\3\2\2\2YZ\3\2\2"+
		"\2ZX\3\2\2\2Z[\3\2\2\2[]\3\2\2\2\\V\3\2\2\2]^\3\2\2\2^\\\3\2\2\2^_\3\2"+
		"\2\2_`\3\2\2\2`a\7\65\2\2a\t\3\2\2\2bh\5\22\n\2ch\5\f\7\2dh\5\16\b\2e"+
		"h\5\4\3\2fh\5\20\t\2gb\3\2\2\2gc\3\2\2\2gd\3\2\2\2ge\3\2\2\2gf\3\2\2\2"+
		"h\13\3\2\2\2ij\7\n\2\2jl\5,\27\2km\7\22\2\2lk\3\2\2\2lm\3\2\2\2mn\3\2"+
		"\2\2no\5*\26\2o\r\3\2\2\2pq\7\n\2\2qr\7\20\2\2rs\7,\2\2sv\7\64\2\2tu\7"+
		",\2\2uw\7/\2\2vt\3\2\2\2wx\3\2\2\2xv\3\2\2\2xy\3\2\2\2yz\3\2\2\2z{\7\65"+
		"\2\2{\17\3\2\2\2|}\7\r\2\2}\177\5,\27\2~\u0080\5(\25\2\177~\3\2\2\2\177"+
		"\u0080\3\2\2\2\u0080\21\3\2\2\2\u0081\u0082\7\n\2\2\u0082\u0083\7!\2\2"+
		"\u0083\u0086\7,\2\2\u0084\u0085\7\34\2\2\u0085\u0087\5\24\13\2\u0086\u0084"+
		"\3\2\2\2\u0086\u0087\3\2\2\2\u0087\u00cf\3\2\2\2\u0088\u0089\7\n\2\2\u0089"+
		"\u0094\7\"\2\2\u008a\u0095\5*\26\2\u008b\u008c\7,\2\2\u008c\u008d\7\34"+
		"\2\2\u008d\u0095\5\30\r\2\u008e\u008f\7\22\2\2\u008f\u0092\7,\2\2\u0090"+
		"\u0091\7\34\2\2\u0091\u0093\5\"\22\2\u0092\u0090\3\2\2\2\u0092\u0093\3"+
		"\2\2\2\u0093\u0095\3\2\2\2\u0094\u008a\3\2\2\2\u0094\u008b\3\2\2\2\u0094"+
		"\u008e\3\2\2\2\u0095\u00cf\3\2\2\2\u0096\u0097\7\n\2\2\u0097\u00a2\7$"+
		"\2\2\u0098\u00a3\5*\26\2\u0099\u009a\7,\2\2\u009a\u009b\7\34\2\2\u009b"+
		"\u00a3\5\32\16\2\u009c\u009d\7\22\2\2\u009d\u00a0\7,\2\2\u009e\u009f\7"+
		"\34\2\2\u009f\u00a1\5$\23\2\u00a0\u009e\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1"+
		"\u00a3\3\2\2\2\u00a2\u0098\3\2\2\2\u00a2\u0099\3\2\2\2\u00a2\u009c\3\2"+
		"\2\2\u00a3\u00cf\3\2\2\2\u00a4\u00a5\7\n\2\2\u00a5\u00b0\7#\2\2\u00a6"+
		"\u00b1\5*\26\2\u00a7\u00a8\7,\2\2\u00a8\u00a9\7\34\2\2\u00a9\u00b1\5\34"+
		"\17\2\u00aa\u00ab\7\22\2\2\u00ab\u00ae\7,\2\2\u00ac\u00ad\7\34\2\2\u00ad"+
		"\u00af\5&\24\2\u00ae\u00ac\3\2\2\2\u00ae\u00af\3\2\2\2\u00af\u00b1\3\2"+
		"\2\2\u00b0\u00a6\3\2\2\2\u00b0\u00a7\3\2\2\2\u00b0\u00aa\3\2\2\2\u00b1"+
		"\u00cf\3\2\2\2\u00b2\u00b3\7\n\2\2\u00b3\u00be\7&\2\2\u00b4\u00bf\5*\26"+
		"\2\u00b5\u00b6\7,\2\2\u00b6\u00b7\7\34\2\2\u00b7\u00bf\5\26\f\2\u00b8"+
		"\u00b9\7\22\2\2\u00b9\u00bc\7,\2\2\u00ba\u00bb\7\34\2\2\u00bb\u00bd\5"+
		" \21\2\u00bc\u00ba\3\2\2\2\u00bc\u00bd\3\2\2\2\u00bd\u00bf\3\2\2\2\u00be"+
		"\u00b4\3\2\2\2\u00be\u00b5\3\2\2\2\u00be\u00b8\3\2\2\2\u00bf\u00cf\3\2"+
		"\2\2\u00c0\u00c1\7\n\2\2\u00c1\u00cc\7%\2\2\u00c2\u00cd\5*\26\2\u00c3"+
		"\u00c4\7,\2\2\u00c4\u00c5\7\34\2\2\u00c5\u00cd\5\24\13\2\u00c6\u00c7\7"+
		"\22\2\2\u00c7\u00ca\7,\2\2\u00c8\u00c9\7\34\2\2\u00c9\u00cb\5\36\20\2"+
		"\u00ca\u00c8\3\2\2\2\u00ca\u00cb\3\2\2\2\u00cb\u00cd\3\2\2\2\u00cc\u00c2"+
		"\3\2\2\2\u00cc\u00c3\3\2\2\2\u00cc\u00c6\3\2\2\2\u00cd\u00cf\3\2\2\2\u00ce"+
		"\u0081\3\2\2\2\u00ce\u0088\3\2\2\2\u00ce\u0096\3\2\2\2\u00ce\u00a4\3\2"+
		"\2\2\u00ce\u00b2\3\2\2\2\u00ce\u00c0\3\2\2\2\u00cf\23\3\2\2\2\u00d0\u00d1"+
		"\7+\2\2\u00d1\25\3\2\2\2\u00d2\u00d3\7\'\2\2\u00d3\27\3\2\2\2\u00d4\u00d5"+
		"\t\2\2\2\u00d5\31\3\2\2\2\u00d6\u00d7\t\3\2\2\u00d7\33\3\2\2\2\u00d8\u00d9"+
		"\7(\2\2\u00d9\35\3\2\2\2\u00da\u00dc\7\23\2\2\u00db\u00dd\7+\2\2\u00dc"+
		"\u00db\3\2\2\2\u00dd\u00de\3\2\2\2\u00de\u00dc\3\2\2\2\u00de\u00df\3\2"+
		"\2\2\u00df\u00e0\3\2\2\2\u00e0\u00e1\7\24\2\2\u00e1\37\3\2\2\2\u00e2\u00e4"+
		"\7\23\2\2\u00e3\u00e5\7\'\2\2\u00e4\u00e3\3\2\2\2\u00e5\u00e6\3\2\2\2"+
		"\u00e6\u00e4\3\2\2\2\u00e6\u00e7\3\2\2\2\u00e7\u00e8\3\2\2\2\u00e8\u00e9"+
		"\7\24\2\2\u00e9!\3\2\2\2\u00ea\u00ec\7\23\2\2\u00eb\u00ed\t\2\2\2\u00ec"+
		"\u00eb\3\2\2\2\u00ed\u00ee\3\2\2\2\u00ee\u00ec\3\2\2\2\u00ee\u00ef\3\2"+
		"\2\2\u00ef\u00f0\3\2\2\2\u00f0\u00f1\7\24\2\2\u00f1#\3\2\2\2\u00f2\u00f4"+
		"\7\23\2\2\u00f3\u00f5\t\3\2\2\u00f4\u00f3\3\2\2\2\u00f5\u00f6\3\2\2\2"+
		"\u00f6\u00f4\3\2\2\2\u00f6\u00f7\3\2\2\2\u00f7\u00f8\3\2\2\2\u00f8\u00f9"+
		"\7\24\2\2\u00f9%\3\2\2\2\u00fa\u00fc\7\23\2\2\u00fb\u00fd\7(\2\2\u00fc"+
		"\u00fb\3\2\2\2\u00fd\u00fe\3\2\2\2\u00fe\u00fc\3\2\2\2\u00fe\u00ff\3\2"+
		"\2\2\u00ff\u0100\3\2\2\2\u0100\u0101\7\24\2\2\u0101\'\3\2\2\2\u0102\u0104"+
		"\7\27\2\2\u0103\u0105\t\4\2\2\u0104\u0103\3\2\2\2\u0105\u0106\3\2\2\2"+
		"\u0106\u0104\3\2\2\2\u0106\u0107\3\2\2\2\u0107\u0108\3\2\2\2\u0108\u0109"+
		"\7\30\2\2\u0109)\3\2\2\2\u010a\u010f\7,\2\2\u010b\u010c\7\32\2\2\u010c"+
		"\u010e\7,\2\2\u010d\u010b\3\2\2\2\u010e\u0111\3\2\2\2\u010f\u010d\3\2"+
		"\2\2\u010f\u0110\3\2\2\2\u0110+\3\2\2\2\u0111\u010f\3\2\2\2\u0112\u0117"+
		"\7,\2\2\u0113\u0114\7\33\2\2\u0114\u0116\7,\2\2\u0115\u0113\3\2\2\2\u0116"+
		"\u0119\3\2\2\2\u0117\u0115\3\2\2\2\u0117\u0118\3\2\2\2\u0118-\3\2\2\2"+
		"\u0119\u0117\3\2\2\2\u011a\u011b\t\5\2\2\u011b/\3\2\2\2\u011c\u011e\7"+
		"\61\2\2\u011d\u011c\3\2\2\2\u011e\u011f\3\2\2\2\u011f\u011d\3\2\2\2\u011f"+
		"\u0120\3\2\2\2\u0120\61\3\2\2\2&\64\66<@CHLOQZ^glx\177\u0086\u0092\u0094"+
		"\u00a0\u00a2\u00ae\u00b0\u00bc\u00be\u00ca\u00cc\u00ce\u00de\u00e6\u00ee"+
		"\u00f6\u00fe\u0106\u010f\u0117\u011f";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}