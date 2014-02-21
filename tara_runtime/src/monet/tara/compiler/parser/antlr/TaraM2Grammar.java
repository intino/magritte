// Generated from TaraM2Grammar.g4 by ANTLR 4.1

    package monet.tara.compiler.parser.antlr;

import com.intellij.ui.popup.PopupFactoryImpl;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNSimulator;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TaraM2Grammar extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		NEGATIVE_VALUE=38, LETTER=43, DOC_BLOCK=48, LEFT_BRACKET=15, CLOSE_AN=22, 
		DEDENT=18, SINGLETON=13, ABSTRACT=5, BOOLEAN_VALUE=36, INDENT=17, MULTIPLE=6, 
		UNKNOWN_TOKEN=52, WORD=10, OPEN_BRACKET=19, AS=3, FINAL=4, WHITE_LINE=45, 
		COMMA=23, IDENTIFIER=41, RIGHT_BRACKET=16, DOC_LINE=49, VAR=11, NL=51, 
		DIGIT=42, DOT=24, STRING_VALUE=40, DOUBLE_VALUE=39, POSITIVE=28, NATURAL_TYPE=32, 
		NEGATIVE=29, DOUBLE_TYPE=33, BOOLEAN_TYPE=35, SEMICOLON=27, INT_TYPE=31, 
		LIST=14, ROOT=12, HAS_CODE=8, OPEN_AN=21, CONCEPT=1, ACCENTED_LETTER=44, 
		OPTIONAL=7, EXTENSIBLE=9, POSITIVE_VALUE=37, NEWLINE=47, SPACES=46, SP=50, 
		STRING_TYPE=34, ASSIGN=25, FROM=2, UID_TYPE=30, DOUBLE_COMMAS=26, CLOSE_BRACKET=20;
	public static final String[] tokenNames = {
		"<INVALID>", "'Concept'", "'from'", "'as'", "'final'", "'abstract'", "'multiple'", 
		"'optional'", "'has-code'", "'extensible'", "'Word'", "'var'", "'root'", 
		"'singleton'", "LIST", "'['", "']'", "'('", "')'", "'{'", "CLOSE_BRACKET", 
		"'<'", "'>'", "','", "'.'", "':'", "'\"'", "';'", "'+'", "'-'", "'Uid'", 
		"'Int'", "'Natural'", "'Double'", "'String'", "'Boolean'", "BOOLEAN_VALUE", 
		"POSITIVE_VALUE", "NEGATIVE_VALUE", "DOUBLE_VALUE", "STRING_VALUE", "IDENTIFIER", 
		"DIGIT", "LETTER", "ACCENTED_LETTER", "WHITE_LINE", "SPACES", "NEWLINE", 
		"DOC_BLOCK", "DOC_LINE", "SP", "NL", "UNKNOWN_TOKEN"
	};
	public static final int
		RULE_conceptDefinition = 0, RULE_extendedConcept = 1, RULE_concept = 2, 
		RULE_conceptBody = 3, RULE_conceptConstituent = 4, RULE_component = 5, 
		RULE_attribute = 6, RULE_word = 7, RULE_reference = 8, RULE_stringAssign = 9, 
		RULE_booleanAssign = 10, RULE_integerAssign = 11, RULE_doubleAssign = 12, 
		RULE_naturalAssign = 13, RULE_stringListAssign = 14, RULE_booleanListAssign = 15, 
		RULE_integerListAssign = 16, RULE_doubleListAssign = 17, RULE_naturalListAssign = 18, 
		RULE_from = 19, RULE_fromBody = 20, RULE_fromComponent = 21, RULE_fromConceptAnnotations = 22, 
		RULE_conceptAnnotations = 23, RULE_componentAnnotations = 24, RULE_fromAnnotations = 25, 
		RULE_modifier = 26, RULE_doc = 27;
	public static final String[] ruleNames = {
		"conceptDefinition", "extendedConcept", "concept", "conceptBody", "conceptConstituent", 
		"component", "attribute", "word", "reference", "stringAssign", "booleanAssign", 
		"integerAssign", "doubleAssign", "naturalAssign", "stringListAssign", 
		"booleanListAssign", "integerListAssign", "doubleListAssign", "naturalListAssign", 
		"from", "fromBody", "fromComponent", "fromConceptAnnotations", "conceptAnnotations", 
		"componentAnnotations", "fromAnnotations", "modifier", "doc"
	};

	@Override
	public String getGrammarFileName() { return "TaraM2Grammar.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public TaraM2Grammar(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ConceptDefinitionContext extends ParserRuleContext {
		public List<TerminalNode> NEWLINE() { return getTokens(TaraM2Grammar.NEWLINE); }
		public ConceptContext concept() {
			return getRuleContext(ConceptContext.class,0);
		}
		public TerminalNode EOF() { return getToken(TaraM2Grammar.EOF, 0); }
		public TerminalNode NEWLINE(int i) {
			return getToken(TaraM2Grammar.NEWLINE, i);
		}
		public ConceptDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conceptDefinition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).enterConceptDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).exitConceptDefinition(this);
		}
	}

	public final ConceptDefinitionContext conceptDefinition() throws RecognitionException {
		ConceptDefinitionContext _localctx = new ConceptDefinitionContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_conceptDefinition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(71);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				{
				setState(59);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << CONCEPT) | (1L << IDENTIFIER) | (1L << DOC_BLOCK) | (1L << DOC_LINE))) != 0)) {
					{
					{
					setState(56); concept();
					}
					}
					setState(61);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(63);
				_la = _input.LA(1);
				if (_la==NEWLINE) {
					{
					setState(62); match(NEWLINE);
					}
				}

				}
				break;

			case 2:
				{
				setState(68);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NEWLINE) {
					{
					{
					setState(65); match(NEWLINE);
					}
					}
					setState(70);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			}
			setState(73); match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExtendedConceptContext extends ParserRuleContext {
		public List<TerminalNode> DOT() { return getTokens(TaraM2Grammar.DOT); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(TaraM2Grammar.IDENTIFIER, i);
		}
		public List<TerminalNode> IDENTIFIER() { return getTokens(TaraM2Grammar.IDENTIFIER); }
		public TerminalNode DOT(int i) {
			return getToken(TaraM2Grammar.DOT, i);
		}
		public ExtendedConceptContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_extendedConcept; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).enterExtendedConcept(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).exitExtendedConcept(this);
		}
	}

	public final ExtendedConceptContext extendedConcept() throws RecognitionException {
		ExtendedConceptContext _localctx = new ExtendedConceptContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_extendedConcept);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75); match(IDENTIFIER);
			setState(80);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DOT) {
				{
				{
				setState(76); match(DOT);
				setState(77); match(IDENTIFIER);
				}
				}
				setState(82);
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

	public static class ConceptContext extends ParserRuleContext {
		public TerminalNode NEWLINE() { return getToken(TaraM2Grammar.NEWLINE, 0); }
		public TerminalNode AS() { return getToken(TaraM2Grammar.AS, 0); }
		public ConceptBodyContext conceptBody() {
			return getRuleContext(ConceptBodyContext.class,0);
		}
		public ExtendedConceptContext extendedConcept() {
			return getRuleContext(ExtendedConceptContext.class,0);
		}
		public ConceptAnnotationsContext conceptAnnotations() {
			return getRuleContext(ConceptAnnotationsContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(TaraM2Grammar.IDENTIFIER, 0); }
		public ModifierContext modifier() {
			return getRuleContext(ModifierContext.class,0);
		}
		public DocContext doc() {
			return getRuleContext(DocContext.class,0);
		}
		public TerminalNode CONCEPT() { return getToken(TaraM2Grammar.CONCEPT, 0); }
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
	}

	public final ConceptContext concept() throws RecognitionException {
		ConceptContext _localctx = new ConceptContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_concept);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			_la = _input.LA(1);
			if (_la==DOC_BLOCK || _la==DOC_LINE) {
				{
				setState(83); doc();
				}
			}

			setState(88);
			switch (_input.LA(1)) {
			case CONCEPT:
				{
				setState(86); match(CONCEPT);
				}
				break;
			case IDENTIFIER:
				{
				setState(87); extendedConcept();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(91);
			_la = _input.LA(1);
			if (_la==FINAL || _la==ABSTRACT) {
				{
				setState(90); modifier();
				}
			}

			setState(93); match(AS);
			setState(94); match(IDENTIFIER);
			setState(96);
			_la = _input.LA(1);
			if (_la==OPEN_AN) {
				{
				setState(95); conceptAnnotations();
				}
			}

			setState(98); match(NEWLINE);
			setState(100);
			_la = _input.LA(1);
			if (_la==INDENT) {
				{
				setState(99); conceptBody();
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

	public static class ConceptBodyContext extends ParserRuleContext {
		public TerminalNode INDENT() { return getToken(TaraM2Grammar.INDENT, 0); }
		public List<ConceptConstituentContext> conceptConstituent() {
			return getRuleContexts(ConceptConstituentContext.class);
		}
		public TerminalNode DEDENT() { return getToken(TaraM2Grammar.DEDENT, 0); }
		public ConceptConstituentContext conceptConstituent(int i) {
			return getRuleContext(ConceptConstituentContext.class,i);
		}
		public ConceptBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conceptBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).enterConceptBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).exitConceptBody(this);
		}
	}

	public final ConceptBodyContext conceptBody() throws RecognitionException {
		ConceptBodyContext _localctx = new ConceptBodyContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_conceptBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102); match(INDENT);
			setState(104); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(103); conceptConstituent();
				}
				}
				setState(106); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << CONCEPT) | (1L << FROM) | (1L << VAR) | (1L << IDENTIFIER) | (1L << DOC_BLOCK) | (1L << DOC_LINE))) != 0) );
			setState(108); match(DEDENT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConceptConstituentContext extends ParserRuleContext {
		public TerminalNode NEWLINE() { return getToken(TaraM2Grammar.NEWLINE, 0); }
		public FromContext from() {
			return getRuleContext(FromContext.class,0);
		}
		public WordContext word() {
			return getRuleContext(WordContext.class,0);
		}
		public AttributeContext attribute() {
			return getRuleContext(AttributeContext.class,0);
		}
		public ComponentContext component() {
			return getRuleContext(ComponentContext.class,0);
		}
		public ReferenceContext reference() {
			return getRuleContext(ReferenceContext.class,0);
		}
		public ConceptConstituentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conceptConstituent; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).enterConceptConstituent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).exitConceptConstituent(this);
		}
	}

	public final ConceptConstituentContext conceptConstituent() throws RecognitionException {
		ConceptConstituentContext _localctx = new ConceptConstituentContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_conceptConstituent);
		try {
			setState(119);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(110); word();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(111); attribute();
				setState(112); match(NEWLINE);
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(114); reference();
				setState(115); match(NEWLINE);
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(117); from();
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(118); component();
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

	public static class ComponentContext extends ParserRuleContext {
		public TerminalNode NEWLINE() { return getToken(TaraM2Grammar.NEWLINE, 0); }
		public TerminalNode AS() { return getToken(TaraM2Grammar.AS, 0); }
		public ConceptBodyContext conceptBody() {
			return getRuleContext(ConceptBodyContext.class,0);
		}
		public ExtendedConceptContext extendedConcept() {
			return getRuleContext(ExtendedConceptContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(TaraM2Grammar.IDENTIFIER, 0); }
		public ModifierContext modifier() {
			return getRuleContext(ModifierContext.class,0);
		}
		public ComponentAnnotationsContext componentAnnotations() {
			return getRuleContext(ComponentAnnotationsContext.class,0);
		}
		public DocContext doc() {
			return getRuleContext(DocContext.class,0);
		}
		public TerminalNode CONCEPT() { return getToken(TaraM2Grammar.CONCEPT, 0); }
		public ComponentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_component; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).enterComponent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).exitComponent(this);
		}
	}

	public final ComponentContext component() throws RecognitionException {
		ComponentContext _localctx = new ComponentContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_component);
		int _la;
		try {
			setState(146);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(122);
				_la = _input.LA(1);
				if (_la==DOC_BLOCK || _la==DOC_LINE) {
					{
					setState(121); doc();
					}
				}

				setState(126);
				switch (_input.LA(1)) {
				case CONCEPT:
					{
					setState(124); match(CONCEPT);
					}
					break;
				case IDENTIFIER:
					{
					setState(125); extendedConcept();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(129);
				_la = _input.LA(1);
				if (_la==FINAL || _la==ABSTRACT) {
					{
					setState(128); modifier();
					}
				}

				setState(131); match(AS);
				setState(132); match(IDENTIFIER);
				setState(134);
				_la = _input.LA(1);
				if (_la==OPEN_AN) {
					{
					setState(133); componentAnnotations();
					}
				}

				setState(136); match(NEWLINE);
				setState(138);
				_la = _input.LA(1);
				if (_la==INDENT) {
					{
					setState(137); conceptBody();
					}
				}

				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(140); extendedConcept();
				setState(142);
				_la = _input.LA(1);
				if (_la==OPEN_AN) {
					{
					setState(141); componentAnnotations();
					}
				}

				setState(144); match(NEWLINE);
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

	public static class AttributeContext extends ParserRuleContext {
		public BooleanAssignContext booleanAssign() {
			return getRuleContext(BooleanAssignContext.class,0);
		}
		public TerminalNode INT_TYPE() { return getToken(TaraM2Grammar.INT_TYPE, 0); }
		public TerminalNode BOOLEAN_TYPE() { return getToken(TaraM2Grammar.BOOLEAN_TYPE, 0); }
		public TerminalNode NATURAL_TYPE() { return getToken(TaraM2Grammar.NATURAL_TYPE, 0); }
		public TerminalNode STRING_TYPE() { return getToken(TaraM2Grammar.STRING_TYPE, 0); }
		public IntegerAssignContext integerAssign() {
			return getRuleContext(IntegerAssignContext.class,0);
		}
		public StringListAssignContext stringListAssign() {
			return getRuleContext(StringListAssignContext.class,0);
		}
		public TerminalNode UID_TYPE() { return getToken(TaraM2Grammar.UID_TYPE, 0); }
		public TerminalNode LIST() { return getToken(TaraM2Grammar.LIST, 0); }
		public DoubleAssignContext doubleAssign() {
			return getRuleContext(DoubleAssignContext.class,0);
		}
		public BooleanListAssignContext booleanListAssign() {
			return getRuleContext(BooleanListAssignContext.class,0);
		}
		public TerminalNode DOUBLE_TYPE() { return getToken(TaraM2Grammar.DOUBLE_TYPE, 0); }
		public NaturalAssignContext naturalAssign() {
			return getRuleContext(NaturalAssignContext.class,0);
		}
		public StringAssignContext stringAssign() {
			return getRuleContext(StringAssignContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(TaraM2Grammar.IDENTIFIER, 0); }
		public TerminalNode VAR() { return getToken(TaraM2Grammar.VAR, 0); }
		public DoubleListAssignContext doubleListAssign() {
			return getRuleContext(DoubleListAssignContext.class,0);
		}
		public IntegerListAssignContext integerListAssign() {
			return getRuleContext(IntegerListAssignContext.class,0);
		}
		public NaturalListAssignContext naturalListAssign() {
			return getRuleContext(NaturalListAssignContext.class,0);
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
	}

	public final AttributeContext attribute() throws RecognitionException {
		AttributeContext _localctx = new AttributeContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_attribute);
		int _la;
		try {
			setState(219);
			switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(148); match(VAR);
				setState(149); match(UID_TYPE);
				setState(150); match(IDENTIFIER);
				setState(152);
				_la = _input.LA(1);
				if (_la==ASSIGN) {
					{
					setState(151); stringAssign();
					}
				}

				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(154); match(VAR);
				setState(155); match(INT_TYPE);
				setState(165);
				switch (_input.LA(1)) {
				case IDENTIFIER:
					{
					setState(156); match(IDENTIFIER);
					setState(158);
					_la = _input.LA(1);
					if (_la==ASSIGN) {
						{
						setState(157); integerAssign();
						}
					}

					}
					break;
				case LIST:
					{
					setState(160); match(LIST);
					setState(161); match(IDENTIFIER);
					setState(163);
					_la = _input.LA(1);
					if (_la==ASSIGN) {
						{
						setState(162); integerListAssign();
						}
					}

					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(167); match(VAR);
				setState(168); match(DOUBLE_TYPE);
				setState(178);
				switch (_input.LA(1)) {
				case IDENTIFIER:
					{
					setState(169); match(IDENTIFIER);
					setState(171);
					_la = _input.LA(1);
					if (_la==ASSIGN) {
						{
						setState(170); doubleAssign();
						}
					}

					}
					break;
				case LIST:
					{
					setState(173); match(LIST);
					setState(174); match(IDENTIFIER);
					setState(176);
					_la = _input.LA(1);
					if (_la==ASSIGN) {
						{
						setState(175); doubleListAssign();
						}
					}

					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(180); match(VAR);
				setState(181); match(NATURAL_TYPE);
				setState(191);
				switch (_input.LA(1)) {
				case IDENTIFIER:
					{
					setState(182); match(IDENTIFIER);
					setState(184);
					_la = _input.LA(1);
					if (_la==ASSIGN) {
						{
						setState(183); naturalAssign();
						}
					}

					}
					break;
				case LIST:
					{
					setState(186); match(LIST);
					setState(187); match(IDENTIFIER);
					setState(189);
					_la = _input.LA(1);
					if (_la==ASSIGN) {
						{
						setState(188); naturalListAssign();
						}
					}

					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(193); match(VAR);
				setState(194); match(BOOLEAN_TYPE);
				setState(204);
				switch (_input.LA(1)) {
				case IDENTIFIER:
					{
					setState(195); match(IDENTIFIER);
					setState(197);
					_la = _input.LA(1);
					if (_la==ASSIGN) {
						{
						setState(196); booleanAssign();
						}
					}

					}
					break;
				case LIST:
					{
					setState(199); match(LIST);
					setState(200); match(IDENTIFIER);
					setState(202);
					_la = _input.LA(1);
					if (_la==ASSIGN) {
						{
						setState(201); booleanListAssign();
						}
					}

					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;

			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(206); match(VAR);
				setState(207); match(STRING_TYPE);
				setState(217);
				switch (_input.LA(1)) {
				case IDENTIFIER:
					{
					setState(208); match(IDENTIFIER);
					setState(210);
					_la = _input.LA(1);
					if (_la==ASSIGN) {
						{
						setState(209); stringAssign();
						}
					}

					}
					break;
				case LIST:
					{
					setState(212); match(LIST);
					setState(213); match(IDENTIFIER);
					setState(215);
					_la = _input.LA(1);
					if (_la==ASSIGN) {
						{
						setState(214); stringListAssign();
						}
					}

					}
					break;
				default:
					throw new NoViableAltException(this);
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

	public static class WordContext extends ParserRuleContext {
		public TerminalNode INDENT() { return getToken(TaraM2Grammar.INDENT, 0); }
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
	}

	public final WordContext word() throws RecognitionException {
		WordContext _localctx = new WordContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_word);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(221); match(VAR);
			setState(222); match(WORD);
			setState(223); match(IDENTIFIER);
			setState(224); match(NEWLINE);
			setState(225); match(INDENT);
			setState(228); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(226); match(IDENTIFIER);
				setState(227); match(NEWLINE);
				}
				}
				setState(230); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==IDENTIFIER );
			setState(232); match(DEDENT);
			}
		}
		catch (RecognitionException re) {
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
		public List<TerminalNode> DOT() { return getTokens(TaraM2Grammar.DOT); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(TaraM2Grammar.IDENTIFIER, i);
		}
		public List<TerminalNode> IDENTIFIER() { return getTokens(TaraM2Grammar.IDENTIFIER); }
		public TerminalNode VAR() { return getToken(TaraM2Grammar.VAR, 0); }
		public TerminalNode LIST() { return getToken(TaraM2Grammar.LIST, 0); }
		public TerminalNode DOT(int i) {
			return getToken(TaraM2Grammar.DOT, i);
		}
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
	}

	public final ReferenceContext reference() throws RecognitionException {
		ReferenceContext _localctx = new ReferenceContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_reference);
		int _la;
		try {
			setState(255);
			switch ( getInterpreter().adaptivePredict(_input,39,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(234); match(VAR);
				setState(235); match(IDENTIFIER);
				setState(240);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==DOT) {
					{
					{
					setState(236); match(DOT);
					setState(237); match(IDENTIFIER);
					}
					}
					setState(242);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(243); match(IDENTIFIER);
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(244); match(VAR);
				setState(245); match(IDENTIFIER);
				setState(250);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==DOT) {
					{
					{
					setState(246); match(DOT);
					setState(247); match(IDENTIFIER);
					}
					}
					setState(252);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(253); match(LIST);
				setState(254); match(IDENTIFIER);
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

	public static class StringAssignContext extends ParserRuleContext {
		public TerminalNode STRING_VALUE() { return getToken(TaraM2Grammar.STRING_VALUE, 0); }
		public TerminalNode ASSIGN() { return getToken(TaraM2Grammar.ASSIGN, 0); }
		public StringAssignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stringAssign; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).enterStringAssign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).exitStringAssign(this);
		}
	}

	public final StringAssignContext stringAssign() throws RecognitionException {
		StringAssignContext _localctx = new StringAssignContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_stringAssign);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(257); match(ASSIGN);
			setState(258); match(STRING_VALUE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BooleanAssignContext extends ParserRuleContext {
		public TerminalNode BOOLEAN_VALUE() { return getToken(TaraM2Grammar.BOOLEAN_VALUE, 0); }
		public TerminalNode ASSIGN() { return getToken(TaraM2Grammar.ASSIGN, 0); }
		public BooleanAssignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_booleanAssign; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).enterBooleanAssign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).exitBooleanAssign(this);
		}
	}

	public final BooleanAssignContext booleanAssign() throws RecognitionException {
		BooleanAssignContext _localctx = new BooleanAssignContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_booleanAssign);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(260); match(ASSIGN);
			setState(261); match(BOOLEAN_VALUE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IntegerAssignContext extends ParserRuleContext {
		public TerminalNode ASSIGN() { return getToken(TaraM2Grammar.ASSIGN, 0); }
		public TerminalNode POSITIVE_VALUE() { return getToken(TaraM2Grammar.POSITIVE_VALUE, 0); }
		public TerminalNode NEGATIVE_VALUE() { return getToken(TaraM2Grammar.NEGATIVE_VALUE, 0); }
		public IntegerAssignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_integerAssign; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).enterIntegerAssign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).exitIntegerAssign(this);
		}
	}

	public final IntegerAssignContext integerAssign() throws RecognitionException {
		IntegerAssignContext _localctx = new IntegerAssignContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_integerAssign);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(263); match(ASSIGN);
			setState(264);
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

	public static class DoubleAssignContext extends ParserRuleContext {
		public TerminalNode DOUBLE_VALUE() { return getToken(TaraM2Grammar.DOUBLE_VALUE, 0); }
		public TerminalNode ASSIGN() { return getToken(TaraM2Grammar.ASSIGN, 0); }
		public TerminalNode POSITIVE_VALUE() { return getToken(TaraM2Grammar.POSITIVE_VALUE, 0); }
		public TerminalNode NEGATIVE_VALUE() { return getToken(TaraM2Grammar.NEGATIVE_VALUE, 0); }
		public DoubleAssignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_doubleAssign; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).enterDoubleAssign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).exitDoubleAssign(this);
		}
	}

	public final DoubleAssignContext doubleAssign() throws RecognitionException {
		DoubleAssignContext _localctx = new DoubleAssignContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_doubleAssign);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(266); match(ASSIGN);
			setState(267);
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

	public static class NaturalAssignContext extends ParserRuleContext {
		public TerminalNode ASSIGN() { return getToken(TaraM2Grammar.ASSIGN, 0); }
		public TerminalNode POSITIVE_VALUE() { return getToken(TaraM2Grammar.POSITIVE_VALUE, 0); }
		public NaturalAssignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_naturalAssign; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).enterNaturalAssign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).exitNaturalAssign(this);
		}
	}

	public final NaturalAssignContext naturalAssign() throws RecognitionException {
		NaturalAssignContext _localctx = new NaturalAssignContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_naturalAssign);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(269); match(ASSIGN);
			setState(270); match(POSITIVE_VALUE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StringListAssignContext extends ParserRuleContext {
		public TerminalNode STRING_VALUE(int i) {
			return getToken(TaraM2Grammar.STRING_VALUE, i);
		}
		public List<TerminalNode> STRING_VALUE() { return getTokens(TaraM2Grammar.STRING_VALUE); }
		public TerminalNode ASSIGN() { return getToken(TaraM2Grammar.ASSIGN, 0); }
		public TerminalNode LEFT_BRACKET() { return getToken(TaraM2Grammar.LEFT_BRACKET, 0); }
		public TerminalNode RIGHT_BRACKET() { return getToken(TaraM2Grammar.RIGHT_BRACKET, 0); }
		public StringListAssignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stringListAssign; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).enterStringListAssign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).exitStringListAssign(this);
		}
	}

	public final StringListAssignContext stringListAssign() throws RecognitionException {
		StringListAssignContext _localctx = new StringListAssignContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_stringListAssign);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(272); match(ASSIGN);
			setState(273); match(LEFT_BRACKET);
			setState(275); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(274); match(STRING_VALUE);
				}
				}
				setState(277); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==STRING_VALUE );
			setState(279); match(RIGHT_BRACKET);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BooleanListAssignContext extends ParserRuleContext {
		public List<TerminalNode> BOOLEAN_VALUE() { return getTokens(TaraM2Grammar.BOOLEAN_VALUE); }
		public TerminalNode ASSIGN() { return getToken(TaraM2Grammar.ASSIGN, 0); }
		public TerminalNode LEFT_BRACKET() { return getToken(TaraM2Grammar.LEFT_BRACKET, 0); }
		public TerminalNode BOOLEAN_VALUE(int i) {
			return getToken(TaraM2Grammar.BOOLEAN_VALUE, i);
		}
		public TerminalNode RIGHT_BRACKET() { return getToken(TaraM2Grammar.RIGHT_BRACKET, 0); }
		public BooleanListAssignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_booleanListAssign; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).enterBooleanListAssign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).exitBooleanListAssign(this);
		}
	}

	public final BooleanListAssignContext booleanListAssign() throws RecognitionException {
		BooleanListAssignContext _localctx = new BooleanListAssignContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_booleanListAssign);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(281); match(ASSIGN);
			setState(282); match(LEFT_BRACKET);
			setState(284); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(283); match(BOOLEAN_VALUE);
				}
				}
				setState(286); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==BOOLEAN_VALUE );
			setState(288); match(RIGHT_BRACKET);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IntegerListAssignContext extends ParserRuleContext {
		public TerminalNode ASSIGN() { return getToken(TaraM2Grammar.ASSIGN, 0); }
		public List<TerminalNode> POSITIVE_VALUE() { return getTokens(TaraM2Grammar.POSITIVE_VALUE); }
		public TerminalNode NEGATIVE_VALUE(int i) {
			return getToken(TaraM2Grammar.NEGATIVE_VALUE, i);
		}
		public TerminalNode LEFT_BRACKET() { return getToken(TaraM2Grammar.LEFT_BRACKET, 0); }
		public List<TerminalNode> NEGATIVE_VALUE() { return getTokens(TaraM2Grammar.NEGATIVE_VALUE); }
		public TerminalNode POSITIVE_VALUE(int i) {
			return getToken(TaraM2Grammar.POSITIVE_VALUE, i);
		}
		public TerminalNode RIGHT_BRACKET() { return getToken(TaraM2Grammar.RIGHT_BRACKET, 0); }
		public IntegerListAssignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_integerListAssign; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).enterIntegerListAssign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).exitIntegerListAssign(this);
		}
	}

	public final IntegerListAssignContext integerListAssign() throws RecognitionException {
		IntegerListAssignContext _localctx = new IntegerListAssignContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_integerListAssign);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(290); match(ASSIGN);
			setState(291); match(LEFT_BRACKET);
			setState(293); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(292);
				_la = _input.LA(1);
				if ( !(_la==POSITIVE_VALUE || _la==NEGATIVE_VALUE) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				}
				}
				setState(295); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==POSITIVE_VALUE || _la==NEGATIVE_VALUE );
			setState(297); match(RIGHT_BRACKET);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DoubleListAssignContext extends ParserRuleContext {
		public List<TerminalNode> DOUBLE_VALUE() { return getTokens(TaraM2Grammar.DOUBLE_VALUE); }
		public TerminalNode ASSIGN() { return getToken(TaraM2Grammar.ASSIGN, 0); }
		public TerminalNode DOUBLE_VALUE(int i) {
			return getToken(TaraM2Grammar.DOUBLE_VALUE, i);
		}
		public List<TerminalNode> POSITIVE_VALUE() { return getTokens(TaraM2Grammar.POSITIVE_VALUE); }
		public TerminalNode NEGATIVE_VALUE(int i) {
			return getToken(TaraM2Grammar.NEGATIVE_VALUE, i);
		}
		public TerminalNode LEFT_BRACKET() { return getToken(TaraM2Grammar.LEFT_BRACKET, 0); }
		public List<TerminalNode> NEGATIVE_VALUE() { return getTokens(TaraM2Grammar.NEGATIVE_VALUE); }
		public TerminalNode POSITIVE_VALUE(int i) {
			return getToken(TaraM2Grammar.POSITIVE_VALUE, i);
		}
		public TerminalNode RIGHT_BRACKET() { return getToken(TaraM2Grammar.RIGHT_BRACKET, 0); }
		public DoubleListAssignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_doubleListAssign; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).enterDoubleListAssign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).exitDoubleListAssign(this);
		}
	}

	public final DoubleListAssignContext doubleListAssign() throws RecognitionException {
		DoubleListAssignContext _localctx = new DoubleListAssignContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_doubleListAssign);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(299); match(ASSIGN);
			setState(300); match(LEFT_BRACKET);
			setState(302); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(301);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << POSITIVE_VALUE) | (1L << NEGATIVE_VALUE) | (1L << DOUBLE_VALUE))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				}
				}
				setState(304); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << POSITIVE_VALUE) | (1L << NEGATIVE_VALUE) | (1L << DOUBLE_VALUE))) != 0) );
			setState(306); match(RIGHT_BRACKET);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NaturalListAssignContext extends ParserRuleContext {
		public TerminalNode ASSIGN() { return getToken(TaraM2Grammar.ASSIGN, 0); }
		public List<TerminalNode> POSITIVE_VALUE() { return getTokens(TaraM2Grammar.POSITIVE_VALUE); }
		public TerminalNode LEFT_BRACKET() { return getToken(TaraM2Grammar.LEFT_BRACKET, 0); }
		public TerminalNode POSITIVE_VALUE(int i) {
			return getToken(TaraM2Grammar.POSITIVE_VALUE, i);
		}
		public TerminalNode RIGHT_BRACKET() { return getToken(TaraM2Grammar.RIGHT_BRACKET, 0); }
		public NaturalListAssignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_naturalListAssign; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).enterNaturalListAssign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).exitNaturalListAssign(this);
		}
	}

	public final NaturalListAssignContext naturalListAssign() throws RecognitionException {
		NaturalListAssignContext _localctx = new NaturalListAssignContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_naturalListAssign);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(308); match(ASSIGN);
			setState(309); match(LEFT_BRACKET);
			setState(311); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(310); match(POSITIVE_VALUE);
				}
				}
				setState(313); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==POSITIVE_VALUE );
			setState(315); match(RIGHT_BRACKET);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FromContext extends ParserRuleContext {
		public TerminalNode NEWLINE() { return getToken(TaraM2Grammar.NEWLINE, 0); }
		public FromBodyContext fromBody() {
			return getRuleContext(FromBodyContext.class,0);
		}
		public FromAnnotationsContext fromAnnotations() {
			return getRuleContext(FromAnnotationsContext.class,0);
		}
		public TerminalNode FROM() { return getToken(TaraM2Grammar.FROM, 0); }
		public FromContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_from; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).enterFrom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).exitFrom(this);
		}
	}

	public final FromContext from() throws RecognitionException {
		FromContext _localctx = new FromContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_from);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(317); match(FROM);
			setState(319);
			_la = _input.LA(1);
			if (_la==OPEN_AN) {
				{
				setState(318); fromAnnotations();
				}
			}

			setState(321); match(NEWLINE);
			setState(322); fromBody();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FromBodyContext extends ParserRuleContext {
		public FromComponentContext fromComponent(int i) {
			return getRuleContext(FromComponentContext.class,i);
		}
		public TerminalNode INDENT() { return getToken(TaraM2Grammar.INDENT, 0); }
		public List<FromComponentContext> fromComponent() {
			return getRuleContexts(FromComponentContext.class);
		}
		public TerminalNode DEDENT() { return getToken(TaraM2Grammar.DEDENT, 0); }
		public FromBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fromBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).enterFromBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).exitFromBody(this);
		}
	}

	public final FromBodyContext fromBody() throws RecognitionException {
		FromBodyContext _localctx = new FromBodyContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_fromBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(324); match(INDENT);
			setState(326); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(325); fromComponent();
				}
				}
				setState(328); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << CONCEPT) | (1L << IDENTIFIER) | (1L << DOC_BLOCK) | (1L << DOC_LINE))) != 0) );
			setState(330); match(DEDENT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FromComponentContext extends ParserRuleContext {
		public TerminalNode NEWLINE() { return getToken(TaraM2Grammar.NEWLINE, 0); }
		public TerminalNode AS() { return getToken(TaraM2Grammar.AS, 0); }
		public ConceptBodyContext conceptBody() {
			return getRuleContext(ConceptBodyContext.class,0);
		}
		public ExtendedConceptContext extendedConcept() {
			return getRuleContext(ExtendedConceptContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(TaraM2Grammar.IDENTIFIER, 0); }
		public ModifierContext modifier() {
			return getRuleContext(ModifierContext.class,0);
		}
		public DocContext doc() {
			return getRuleContext(DocContext.class,0);
		}
		public TerminalNode CONCEPT() { return getToken(TaraM2Grammar.CONCEPT, 0); }
		public FromConceptAnnotationsContext fromConceptAnnotations() {
			return getRuleContext(FromConceptAnnotationsContext.class,0);
		}
		public FromComponentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fromComponent; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).enterFromComponent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).exitFromComponent(this);
		}
	}

	public final FromComponentContext fromComponent() throws RecognitionException {
		FromComponentContext _localctx = new FromComponentContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_fromComponent);
		int _la;
		try {
			setState(357);
			switch ( getInterpreter().adaptivePredict(_input,53,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(333);
				_la = _input.LA(1);
				if (_la==DOC_BLOCK || _la==DOC_LINE) {
					{
					setState(332); doc();
					}
				}

				setState(337);
				switch (_input.LA(1)) {
				case CONCEPT:
					{
					setState(335); match(CONCEPT);
					}
					break;
				case IDENTIFIER:
					{
					setState(336); extendedConcept();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(340);
				_la = _input.LA(1);
				if (_la==FINAL || _la==ABSTRACT) {
					{
					setState(339); modifier();
					}
				}

				setState(342); match(AS);
				setState(343); match(IDENTIFIER);
				setState(345);
				_la = _input.LA(1);
				if (_la==OPEN_AN) {
					{
					setState(344); fromConceptAnnotations();
					}
				}

				setState(347); match(NEWLINE);
				setState(349);
				_la = _input.LA(1);
				if (_la==INDENT) {
					{
					setState(348); conceptBody();
					}
				}

				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(351); extendedConcept();
				setState(353);
				_la = _input.LA(1);
				if (_la==OPEN_AN) {
					{
					setState(352); fromConceptAnnotations();
					}
				}

				setState(355); match(NEWLINE);
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

	public static class FromConceptAnnotationsContext extends ParserRuleContext {
		public TerminalNode OPEN_AN() { return getToken(TaraM2Grammar.OPEN_AN, 0); }
		public TerminalNode EXTENSIBLE(int i) {
			return getToken(TaraM2Grammar.EXTENSIBLE, i);
		}
		public TerminalNode CLOSE_AN() { return getToken(TaraM2Grammar.CLOSE_AN, 0); }
		public TerminalNode HAS_CODE(int i) {
			return getToken(TaraM2Grammar.HAS_CODE, i);
		}
		public List<TerminalNode> HAS_CODE() { return getTokens(TaraM2Grammar.HAS_CODE); }
		public List<TerminalNode> EXTENSIBLE() { return getTokens(TaraM2Grammar.EXTENSIBLE); }
		public TerminalNode SINGLETON(int i) {
			return getToken(TaraM2Grammar.SINGLETON, i);
		}
		public List<TerminalNode> SINGLETON() { return getTokens(TaraM2Grammar.SINGLETON); }
		public FromConceptAnnotationsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fromConceptAnnotations; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).enterFromConceptAnnotations(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).exitFromConceptAnnotations(this);
		}
	}

	public final FromConceptAnnotationsContext fromConceptAnnotations() throws RecognitionException {
		FromConceptAnnotationsContext _localctx = new FromConceptAnnotationsContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_fromConceptAnnotations);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(359); match(OPEN_AN);
			setState(361); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(360);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HAS_CODE) | (1L << EXTENSIBLE) | (1L << SINGLETON))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				}
				}
				setState(363); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HAS_CODE) | (1L << EXTENSIBLE) | (1L << SINGLETON))) != 0) );
			setState(365); match(CLOSE_AN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConceptAnnotationsContext extends ParserRuleContext {
		public TerminalNode OPEN_AN() { return getToken(TaraM2Grammar.OPEN_AN, 0); }
		public List<TerminalNode> ROOT() { return getTokens(TaraM2Grammar.ROOT); }
		public TerminalNode EXTENSIBLE(int i) {
			return getToken(TaraM2Grammar.EXTENSIBLE, i);
		}
		public TerminalNode CLOSE_AN() { return getToken(TaraM2Grammar.CLOSE_AN, 0); }
		public TerminalNode HAS_CODE(int i) {
			return getToken(TaraM2Grammar.HAS_CODE, i);
		}
		public List<TerminalNode> HAS_CODE() { return getTokens(TaraM2Grammar.HAS_CODE); }
		public List<TerminalNode> EXTENSIBLE() { return getTokens(TaraM2Grammar.EXTENSIBLE); }
		public TerminalNode ROOT(int i) {
			return getToken(TaraM2Grammar.ROOT, i);
		}
		public TerminalNode SINGLETON(int i) {
			return getToken(TaraM2Grammar.SINGLETON, i);
		}
		public List<TerminalNode> SINGLETON() { return getTokens(TaraM2Grammar.SINGLETON); }
		public ConceptAnnotationsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conceptAnnotations; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).enterConceptAnnotations(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).exitConceptAnnotations(this);
		}
	}

	public final ConceptAnnotationsContext conceptAnnotations() throws RecognitionException {
		ConceptAnnotationsContext _localctx = new ConceptAnnotationsContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_conceptAnnotations);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(367); match(OPEN_AN);
			setState(369); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(368);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HAS_CODE) | (1L << EXTENSIBLE) | (1L << ROOT) | (1L << SINGLETON))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				}
				}
				setState(371); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HAS_CODE) | (1L << EXTENSIBLE) | (1L << ROOT) | (1L << SINGLETON))) != 0) );
			setState(373); match(CLOSE_AN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ComponentAnnotationsContext extends ParserRuleContext {
		public List<TerminalNode> OPTIONAL() { return getTokens(TaraM2Grammar.OPTIONAL); }
		public TerminalNode OPEN_AN() { return getToken(TaraM2Grammar.OPEN_AN, 0); }
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
		public List<TerminalNode> EXTENSIBLE() { return getTokens(TaraM2Grammar.EXTENSIBLE); }
		public List<TerminalNode> MULTIPLE() { return getTokens(TaraM2Grammar.MULTIPLE); }
		public TerminalNode SINGLETON(int i) {
			return getToken(TaraM2Grammar.SINGLETON, i);
		}
		public List<TerminalNode> SINGLETON() { return getTokens(TaraM2Grammar.SINGLETON); }
		public ComponentAnnotationsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_componentAnnotations; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).enterComponentAnnotations(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).exitComponentAnnotations(this);
		}
	}

	public final ComponentAnnotationsContext componentAnnotations() throws RecognitionException {
		ComponentAnnotationsContext _localctx = new ComponentAnnotationsContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_componentAnnotations);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(375); match(OPEN_AN);
			setState(377); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(376);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MULTIPLE) | (1L << OPTIONAL) | (1L << HAS_CODE) | (1L << EXTENSIBLE) | (1L << SINGLETON))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				}
				}
				setState(379); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MULTIPLE) | (1L << OPTIONAL) | (1L << HAS_CODE) | (1L << EXTENSIBLE) | (1L << SINGLETON))) != 0) );
			setState(381); match(CLOSE_AN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FromAnnotationsContext extends ParserRuleContext {
		public List<TerminalNode> OPTIONAL() { return getTokens(TaraM2Grammar.OPTIONAL); }
		public TerminalNode OPEN_AN() { return getToken(TaraM2Grammar.OPEN_AN, 0); }
		public TerminalNode MULTIPLE(int i) {
			return getToken(TaraM2Grammar.MULTIPLE, i);
		}
		public TerminalNode CLOSE_AN() { return getToken(TaraM2Grammar.CLOSE_AN, 0); }
		public TerminalNode OPTIONAL(int i) {
			return getToken(TaraM2Grammar.OPTIONAL, i);
		}
		public List<TerminalNode> MULTIPLE() { return getTokens(TaraM2Grammar.MULTIPLE); }
		public FromAnnotationsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fromAnnotations; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).enterFromAnnotations(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraM2GrammarListener ) ((TaraM2GrammarListener)listener).exitFromAnnotations(this);
		}
	}

	public final FromAnnotationsContext fromAnnotations() throws RecognitionException {
		FromAnnotationsContext _localctx = new FromAnnotationsContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_fromAnnotations);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(383); match(OPEN_AN);
			setState(385); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(384);
				_la = _input.LA(1);
				if ( !(_la==MULTIPLE || _la==OPTIONAL) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				}
				}
				setState(387); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==MULTIPLE || _la==OPTIONAL );
			setState(389); match(CLOSE_AN);
			}
		}
		catch (RecognitionException re) {
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
	}

	public final ModifierContext modifier() throws RecognitionException {
		ModifierContext _localctx = new ModifierContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_modifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(391);
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
		public TerminalNode DOC_LINE() { return getToken(TaraM2Grammar.DOC_LINE, 0); }
		public TerminalNode DOC_BLOCK() { return getToken(TaraM2Grammar.DOC_BLOCK, 0); }
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

		public PopupFactoryImpl.ActionItem DOC() {
			return null;
		}
	}

	public final DocContext doc() throws RecognitionException {
		DocContext _localctx = new DocContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_doc);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(393);
			_la = _input.LA(1);
			if ( !(_la==DOC_BLOCK || _la==DOC_LINE) ) {
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
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\3\66\u018e\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\3\2\7\2<\n\2\f\2\16\2?\13\2\3"+
		"\2\5\2B\n\2\3\2\7\2E\n\2\f\2\16\2H\13\2\5\2J\n\2\3\2\3\2\3\3\3\3\3\3\7"+
		"\3Q\n\3\f\3\16\3T\13\3\3\4\5\4W\n\4\3\4\3\4\5\4[\n\4\3\4\5\4^\n\4\3\4"+
		"\3\4\3\4\5\4c\n\4\3\4\3\4\5\4g\n\4\3\5\3\5\6\5k\n\5\r\5\16\5l\3\5\3\5"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6z\n\6\3\7\5\7}\n\7\3\7\3\7\5\7"+
		"\u0081\n\7\3\7\5\7\u0084\n\7\3\7\3\7\3\7\5\7\u0089\n\7\3\7\3\7\5\7\u008d"+
		"\n\7\3\7\3\7\5\7\u0091\n\7\3\7\3\7\5\7\u0095\n\7\3\b\3\b\3\b\3\b\5\b\u009b"+
		"\n\b\3\b\3\b\3\b\3\b\5\b\u00a1\n\b\3\b\3\b\3\b\5\b\u00a6\n\b\5\b\u00a8"+
		"\n\b\3\b\3\b\3\b\3\b\5\b\u00ae\n\b\3\b\3\b\3\b\5\b\u00b3\n\b\5\b\u00b5"+
		"\n\b\3\b\3\b\3\b\3\b\5\b\u00bb\n\b\3\b\3\b\3\b\5\b\u00c0\n\b\5\b\u00c2"+
		"\n\b\3\b\3\b\3\b\3\b\5\b\u00c8\n\b\3\b\3\b\3\b\5\b\u00cd\n\b\5\b\u00cf"+
		"\n\b\3\b\3\b\3\b\3\b\5\b\u00d5\n\b\3\b\3\b\3\b\5\b\u00da\n\b\5\b\u00dc"+
		"\n\b\5\b\u00de\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\6\t\u00e7\n\t\r\t\16\t"+
		"\u00e8\3\t\3\t\3\n\3\n\3\n\3\n\7\n\u00f1\n\n\f\n\16\n\u00f4\13\n\3\n\3"+
		"\n\3\n\3\n\3\n\7\n\u00fb\n\n\f\n\16\n\u00fe\13\n\3\n\3\n\5\n\u0102\n\n"+
		"\3\13\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\r\3\16\3\16\3\16\3\17\3\17\3\17"+
		"\3\20\3\20\3\20\6\20\u0116\n\20\r\20\16\20\u0117\3\20\3\20\3\21\3\21\3"+
		"\21\6\21\u011f\n\21\r\21\16\21\u0120\3\21\3\21\3\22\3\22\3\22\6\22\u0128"+
		"\n\22\r\22\16\22\u0129\3\22\3\22\3\23\3\23\3\23\6\23\u0131\n\23\r\23\16"+
		"\23\u0132\3\23\3\23\3\24\3\24\3\24\6\24\u013a\n\24\r\24\16\24\u013b\3"+
		"\24\3\24\3\25\3\25\5\25\u0142\n\25\3\25\3\25\3\25\3\26\3\26\6\26\u0149"+
		"\n\26\r\26\16\26\u014a\3\26\3\26\3\27\5\27\u0150\n\27\3\27\3\27\5\27\u0154"+
		"\n\27\3\27\5\27\u0157\n\27\3\27\3\27\3\27\5\27\u015c\n\27\3\27\3\27\5"+
		"\27\u0160\n\27\3\27\3\27\5\27\u0164\n\27\3\27\3\27\5\27\u0168\n\27\3\30"+
		"\3\30\6\30\u016c\n\30\r\30\16\30\u016d\3\30\3\30\3\31\3\31\6\31\u0174"+
		"\n\31\r\31\16\31\u0175\3\31\3\31\3\32\3\32\6\32\u017c\n\32\r\32\16\32"+
		"\u017d\3\32\3\32\3\33\3\33\6\33\u0184\n\33\r\33\16\33\u0185\3\33\3\33"+
		"\3\34\3\34\3\35\3\35\3\35\2\36\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36"+
		" \"$&(*,.\60\62\64\668\2\n\3\2\'(\3\2\')\4\2\n\13\17\17\4\2\n\13\16\17"+
		"\4\2\b\13\17\17\3\2\b\t\3\2\6\7\3\2\62\63\u01b2\2I\3\2\2\2\4M\3\2\2\2"+
		"\6V\3\2\2\2\bh\3\2\2\2\ny\3\2\2\2\f\u0094\3\2\2\2\16\u00dd\3\2\2\2\20"+
		"\u00df\3\2\2\2\22\u0101\3\2\2\2\24\u0103\3\2\2\2\26\u0106\3\2\2\2\30\u0109"+
		"\3\2\2\2\32\u010c\3\2\2\2\34\u010f\3\2\2\2\36\u0112\3\2\2\2 \u011b\3\2"+
		"\2\2\"\u0124\3\2\2\2$\u012d\3\2\2\2&\u0136\3\2\2\2(\u013f\3\2\2\2*\u0146"+
		"\3\2\2\2,\u0167\3\2\2\2.\u0169\3\2\2\2\60\u0171\3\2\2\2\62\u0179\3\2\2"+
		"\2\64\u0181\3\2\2\2\66\u0189\3\2\2\28\u018b\3\2\2\2:<\5\6\4\2;:\3\2\2"+
		"\2<?\3\2\2\2=;\3\2\2\2=>\3\2\2\2>A\3\2\2\2?=\3\2\2\2@B\7\61\2\2A@\3\2"+
		"\2\2AB\3\2\2\2BJ\3\2\2\2CE\7\61\2\2DC\3\2\2\2EH\3\2\2\2FD\3\2\2\2FG\3"+
		"\2\2\2GJ\3\2\2\2HF\3\2\2\2I=\3\2\2\2IF\3\2\2\2JK\3\2\2\2KL\7\2\2\3L\3"+
		"\3\2\2\2MR\7+\2\2NO\7\32\2\2OQ\7+\2\2PN\3\2\2\2QT\3\2\2\2RP\3\2\2\2RS"+
		"\3\2\2\2S\5\3\2\2\2TR\3\2\2\2UW\58\35\2VU\3\2\2\2VW\3\2\2\2WZ\3\2\2\2"+
		"X[\7\3\2\2Y[\5\4\3\2ZX\3\2\2\2ZY\3\2\2\2[]\3\2\2\2\\^\5\66\34\2]\\\3\2"+
		"\2\2]^\3\2\2\2^_\3\2\2\2_`\7\5\2\2`b\7+\2\2ac\5\60\31\2ba\3\2\2\2bc\3"+
		"\2\2\2cd\3\2\2\2df\7\61\2\2eg\5\b\5\2fe\3\2\2\2fg\3\2\2\2g\7\3\2\2\2h"+
		"j\7\23\2\2ik\5\n\6\2ji\3\2\2\2kl\3\2\2\2lj\3\2\2\2lm\3\2\2\2mn\3\2\2\2"+
		"no\7\24\2\2o\t\3\2\2\2pz\5\20\t\2qr\5\16\b\2rs\7\61\2\2sz\3\2\2\2tu\5"+
		"\22\n\2uv\7\61\2\2vz\3\2\2\2wz\5(\25\2xz\5\f\7\2yp\3\2\2\2yq\3\2\2\2y"+
		"t\3\2\2\2yw\3\2\2\2yx\3\2\2\2z\13\3\2\2\2{}\58\35\2|{\3\2\2\2|}\3\2\2"+
		"\2}\u0080\3\2\2\2~\u0081\7\3\2\2\177\u0081\5\4\3\2\u0080~\3\2\2\2\u0080"+
		"\177\3\2\2\2\u0081\u0083\3\2\2\2\u0082\u0084\5\66\34\2\u0083\u0082\3\2"+
		"\2\2\u0083\u0084\3\2\2\2\u0084\u0085\3\2\2\2\u0085\u0086\7\5\2\2\u0086"+
		"\u0088\7+\2\2\u0087\u0089\5\62\32\2\u0088\u0087\3\2\2\2\u0088\u0089\3"+
		"\2\2\2\u0089\u008a\3\2\2\2\u008a\u008c\7\61\2\2\u008b\u008d\5\b\5\2\u008c"+
		"\u008b\3\2\2\2\u008c\u008d\3\2\2\2\u008d\u0095\3\2\2\2\u008e\u0090\5\4"+
		"\3\2\u008f\u0091\5\62\32\2\u0090\u008f\3\2\2\2\u0090\u0091\3\2\2\2\u0091"+
		"\u0092\3\2\2\2\u0092\u0093\7\61\2\2\u0093\u0095\3\2\2\2\u0094|\3\2\2\2"+
		"\u0094\u008e\3\2\2\2\u0095\r\3\2\2\2\u0096\u0097\7\r\2\2\u0097\u0098\7"+
		" \2\2\u0098\u009a\7+\2\2\u0099\u009b\5\24\13\2\u009a\u0099\3\2\2\2\u009a"+
		"\u009b\3\2\2\2\u009b\u00de\3\2\2\2\u009c\u009d\7\r\2\2\u009d\u00a7\7!"+
		"\2\2\u009e\u00a0\7+\2\2\u009f\u00a1\5\30\r\2\u00a0\u009f\3\2\2\2\u00a0"+
		"\u00a1\3\2\2\2\u00a1\u00a8\3\2\2\2\u00a2\u00a3\7\20\2\2\u00a3\u00a5\7"+
		"+\2\2\u00a4\u00a6\5\"\22\2\u00a5\u00a4\3\2\2\2\u00a5\u00a6\3\2\2\2\u00a6"+
		"\u00a8\3\2\2\2\u00a7\u009e\3\2\2\2\u00a7\u00a2\3\2\2\2\u00a8\u00de\3\2"+
		"\2\2\u00a9\u00aa\7\r\2\2\u00aa\u00b4\7#\2\2\u00ab\u00ad\7+\2\2\u00ac\u00ae"+
		"\5\32\16\2\u00ad\u00ac\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae\u00b5\3\2\2\2"+
		"\u00af\u00b0\7\20\2\2\u00b0\u00b2\7+\2\2\u00b1\u00b3\5$\23\2\u00b2\u00b1"+
		"\3\2\2\2\u00b2\u00b3\3\2\2\2\u00b3\u00b5\3\2\2\2\u00b4\u00ab\3\2\2\2\u00b4"+
		"\u00af\3\2\2\2\u00b5\u00de\3\2\2\2\u00b6\u00b7\7\r\2\2\u00b7\u00c1\7\""+
		"\2\2\u00b8\u00ba\7+\2\2\u00b9\u00bb\5\34\17\2\u00ba\u00b9\3\2\2\2\u00ba"+
		"\u00bb\3\2\2\2\u00bb\u00c2\3\2\2\2\u00bc\u00bd\7\20\2\2\u00bd\u00bf\7"+
		"+\2\2\u00be\u00c0\5&\24\2\u00bf\u00be\3\2\2\2\u00bf\u00c0\3\2\2\2\u00c0"+
		"\u00c2\3\2\2\2\u00c1\u00b8\3\2\2\2\u00c1\u00bc\3\2\2\2\u00c2\u00de\3\2"+
		"\2\2\u00c3\u00c4\7\r\2\2\u00c4\u00ce\7%\2\2\u00c5\u00c7\7+\2\2\u00c6\u00c8"+
		"\5\26\f\2\u00c7\u00c6\3\2\2\2\u00c7\u00c8\3\2\2\2\u00c8\u00cf\3\2\2\2"+
		"\u00c9\u00ca\7\20\2\2\u00ca\u00cc\7+\2\2\u00cb\u00cd\5 \21\2\u00cc\u00cb"+
		"\3\2\2\2\u00cc\u00cd\3\2\2\2\u00cd\u00cf\3\2\2\2\u00ce\u00c5\3\2\2\2\u00ce"+
		"\u00c9\3\2\2\2\u00cf\u00de\3\2\2\2\u00d0\u00d1\7\r\2\2\u00d1\u00db\7$"+
		"\2\2\u00d2\u00d4\7+\2\2\u00d3\u00d5\5\24\13\2\u00d4\u00d3\3\2\2\2\u00d4"+
		"\u00d5\3\2\2\2\u00d5\u00dc\3\2\2\2\u00d6\u00d7\7\20\2\2\u00d7\u00d9\7"+
		"+\2\2\u00d8\u00da\5\36\20\2\u00d9\u00d8\3\2\2\2\u00d9\u00da\3\2\2\2\u00da"+
		"\u00dc\3\2\2\2\u00db\u00d2\3\2\2\2\u00db\u00d6\3\2\2\2\u00dc\u00de\3\2"+
		"\2\2\u00dd\u0096\3\2\2\2\u00dd\u009c\3\2\2\2\u00dd\u00a9\3\2\2\2\u00dd"+
		"\u00b6\3\2\2\2\u00dd\u00c3\3\2\2\2\u00dd\u00d0\3\2\2\2\u00de\17\3\2\2"+
		"\2\u00df\u00e0\7\r\2\2\u00e0\u00e1\7\f\2\2\u00e1\u00e2\7+\2\2\u00e2\u00e3"+
		"\7\61\2\2\u00e3\u00e6\7\23\2\2\u00e4\u00e5\7+\2\2\u00e5\u00e7\7\61\2\2"+
		"\u00e6\u00e4\3\2\2\2\u00e7\u00e8\3\2\2\2\u00e8\u00e6\3\2\2\2\u00e8\u00e9"+
		"\3\2\2\2\u00e9\u00ea\3\2\2\2\u00ea\u00eb\7\24\2\2\u00eb\21\3\2\2\2\u00ec"+
		"\u00ed\7\r\2\2\u00ed\u00f2\7+\2\2\u00ee\u00ef\7\32\2\2\u00ef\u00f1\7+"+
		"\2\2\u00f0\u00ee\3\2\2\2\u00f1\u00f4\3\2\2\2\u00f2\u00f0\3\2\2\2\u00f2"+
		"\u00f3\3\2\2\2\u00f3\u00f5\3\2\2\2\u00f4\u00f2\3\2\2\2\u00f5\u0102\7+"+
		"\2\2\u00f6\u00f7\7\r\2\2\u00f7\u00fc\7+\2\2\u00f8\u00f9\7\32\2\2\u00f9"+
		"\u00fb\7+\2\2\u00fa\u00f8\3\2\2\2\u00fb\u00fe\3\2\2\2\u00fc\u00fa\3\2"+
		"\2\2\u00fc\u00fd\3\2\2\2\u00fd\u00ff\3\2\2\2\u00fe\u00fc\3\2\2\2\u00ff"+
		"\u0100\7\20\2\2\u0100\u0102\7+\2\2\u0101\u00ec\3\2\2\2\u0101\u00f6\3\2"+
		"\2\2\u0102\23\3\2\2\2\u0103\u0104\7\33\2\2\u0104\u0105\7*\2\2\u0105\25"+
		"\3\2\2\2\u0106\u0107\7\33\2\2\u0107\u0108\7&\2\2\u0108\27\3\2\2\2\u0109"+
		"\u010a\7\33\2\2\u010a\u010b\t\2\2\2\u010b\31\3\2\2\2\u010c\u010d\7\33"+
		"\2\2\u010d\u010e\t\3\2\2\u010e\33\3\2\2\2\u010f\u0110\7\33\2\2\u0110\u0111"+
		"\7\'\2\2\u0111\35\3\2\2\2\u0112\u0113\7\33\2\2\u0113\u0115\7\21\2\2\u0114"+
		"\u0116\7*\2\2\u0115\u0114\3\2\2\2\u0116\u0117\3\2\2\2\u0117\u0115\3\2"+
		"\2\2\u0117\u0118\3\2\2\2\u0118\u0119\3\2\2\2\u0119\u011a\7\22\2\2\u011a"+
		"\37\3\2\2\2\u011b\u011c\7\33\2\2\u011c\u011e\7\21\2\2\u011d\u011f\7&\2"+
		"\2\u011e\u011d\3\2\2\2\u011f\u0120\3\2\2\2\u0120\u011e\3\2\2\2\u0120\u0121"+
		"\3\2\2\2\u0121\u0122\3\2\2\2\u0122\u0123\7\22\2\2\u0123!\3\2\2\2\u0124"+
		"\u0125\7\33\2\2\u0125\u0127\7\21\2\2\u0126\u0128\t\2\2\2\u0127\u0126\3"+
		"\2\2\2\u0128\u0129\3\2\2\2\u0129\u0127\3\2\2\2\u0129\u012a\3\2\2\2\u012a"+
		"\u012b\3\2\2\2\u012b\u012c\7\22\2\2\u012c#\3\2\2\2\u012d\u012e\7\33\2"+
		"\2\u012e\u0130\7\21\2\2\u012f\u0131\t\3\2\2\u0130\u012f\3\2\2\2\u0131"+
		"\u0132\3\2\2\2\u0132\u0130\3\2\2\2\u0132\u0133\3\2\2\2\u0133\u0134\3\2"+
		"\2\2\u0134\u0135\7\22\2\2\u0135%\3\2\2\2\u0136\u0137\7\33\2\2\u0137\u0139"+
		"\7\21\2\2\u0138\u013a\7\'\2\2\u0139\u0138\3\2\2\2\u013a\u013b\3\2\2\2"+
		"\u013b\u0139\3\2\2\2\u013b\u013c\3\2\2\2\u013c\u013d\3\2\2\2\u013d\u013e"+
		"\7\22\2\2\u013e\'\3\2\2\2\u013f\u0141\7\4\2\2\u0140\u0142\5\64\33\2\u0141"+
		"\u0140\3\2\2\2\u0141\u0142\3\2\2\2\u0142\u0143\3\2\2\2\u0143\u0144\7\61"+
		"\2\2\u0144\u0145\5*\26\2\u0145)\3\2\2\2\u0146\u0148\7\23\2\2\u0147\u0149"+
		"\5,\27\2\u0148\u0147\3\2\2\2\u0149\u014a\3\2\2\2\u014a\u0148\3\2\2\2\u014a"+
		"\u014b\3\2\2\2\u014b\u014c\3\2\2\2\u014c\u014d\7\24\2\2\u014d+\3\2\2\2"+
		"\u014e\u0150\58\35\2\u014f\u014e\3\2\2\2\u014f\u0150\3\2\2\2\u0150\u0153"+
		"\3\2\2\2\u0151\u0154\7\3\2\2\u0152\u0154\5\4\3\2\u0153\u0151\3\2\2\2\u0153"+
		"\u0152\3\2\2\2\u0154\u0156\3\2\2\2\u0155\u0157\5\66\34\2\u0156\u0155\3"+
		"\2\2\2\u0156\u0157\3\2\2\2\u0157\u0158\3\2\2\2\u0158\u0159\7\5\2\2\u0159"+
		"\u015b\7+\2\2\u015a\u015c\5.\30\2\u015b\u015a\3\2\2\2\u015b\u015c\3\2"+
		"\2\2\u015c\u015d\3\2\2\2\u015d\u015f\7\61\2\2\u015e\u0160\5\b\5\2\u015f"+
		"\u015e\3\2\2\2\u015f\u0160\3\2\2\2\u0160\u0168\3\2\2\2\u0161\u0163\5\4"+
		"\3\2\u0162\u0164\5.\30\2\u0163\u0162\3\2\2\2\u0163\u0164\3\2\2\2\u0164"+
		"\u0165\3\2\2\2\u0165\u0166\7\61\2\2\u0166\u0168\3\2\2\2\u0167\u014f\3"+
		"\2\2\2\u0167\u0161\3\2\2\2\u0168-\3\2\2\2\u0169\u016b\7\27\2\2\u016a\u016c"+
		"\t\4\2\2\u016b\u016a\3\2\2\2\u016c\u016d\3\2\2\2\u016d\u016b\3\2\2\2\u016d"+
		"\u016e\3\2\2\2\u016e\u016f\3\2\2\2\u016f\u0170\7\30\2\2\u0170/\3\2\2\2"+
		"\u0171\u0173\7\27\2\2\u0172\u0174\t\5\2\2\u0173\u0172\3\2\2\2\u0174\u0175"+
		"\3\2\2\2\u0175\u0173\3\2\2\2\u0175\u0176\3\2\2\2\u0176\u0177\3\2\2\2\u0177"+
		"\u0178\7\30\2\2\u0178\61\3\2\2\2\u0179\u017b\7\27\2\2\u017a\u017c\t\6"+
		"\2\2\u017b\u017a\3\2\2\2\u017c\u017d\3\2\2\2\u017d\u017b\3\2\2\2\u017d"+
		"\u017e\3\2\2\2\u017e\u017f\3\2\2\2\u017f\u0180\7\30\2\2\u0180\63\3\2\2"+
		"\2\u0181\u0183\7\27\2\2\u0182\u0184\t\7\2\2\u0183\u0182\3\2\2\2\u0184"+
		"\u0185\3\2\2\2\u0185\u0183\3\2\2\2\u0185\u0186\3\2\2\2\u0186\u0187\3\2"+
		"\2\2\u0187\u0188\7\30\2\2\u0188\65\3\2\2\2\u0189\u018a\t\b\2\2\u018a\67"+
		"\3\2\2\2\u018b\u018c\t\t\2\2\u018c9\3\2\2\2<=AFIRVZ]bfly|\u0080\u0083"+
		"\u0088\u008c\u0090\u0094\u009a\u00a0\u00a5\u00a7\u00ad\u00b2\u00b4\u00ba"+
		"\u00bf\u00c1\u00c7\u00cc\u00ce\u00d4\u00d9\u00db\u00dd\u00e8\u00f2\u00fc"+
		"\u0101\u0117\u0120\u0129\u0132\u013b\u0141\u014a\u014f\u0153\u0156\u015b"+
		"\u015f\u0163\u0167\u016d\u0175\u017d\u0185";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}