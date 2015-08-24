// Generated from /Users/oroncal/workspace/tara/language/grammar/src/tara/language/grammar/TaraGrammar.g4 by ANTLR 4.5.1
package tara.language.grammar;
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
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		METAIDENTIFIER=1, SUB=2, USE=3, DSL=4, VAR=5, AS=6, HAS=7, ON=8, IS=9, 
		INTO=10, WITH=11, ANY=12, EXTENDS=13, ABSTRACT=14, SINGLE=15, REQUIRED=16, 
		TERMINAL=17, MAIN=18, PROTOTYPE=19, FEATURE=20, FINAL=21, ENCLOSED=22, 
		PRIVATE=23, FACET=24, LEFT_PARENTHESIS=25, RIGHT_PARENTHESIS=26, LEFT_SQUARE=27, 
		RIGHT_SQUARE=28, LIST=29, INLINE=30, CLOSE_INLINE=31, HASHTAG=32, COLON=33, 
		COMMA=34, DOT=35, EQUALS=36, SEMICOLON=37, PLUS=38, WORD=39, RESOURCE=40, 
		INT_TYPE=41, TUPLE_TYPE=42, NATURAL_TYPE=43, NATIVE_TYPE=44, DOUBLE_TYPE=45, 
		STRING_TYPE=46, BOOLEAN_TYPE=47, MEASURE_TYPE=48, RATIO_TYPE=49, DATE_TYPE=50, 
		TIME_TYPE=51, EMPTY=52, BLOCK_COMMENT=53, LINE_COMMENT=54, SCIENCE_NOT=55, 
		BOOLEAN_VALUE=56, NATURAL_VALUE=57, NEGATIVE_VALUE=58, DOUBLE_VALUE=59, 
		APHOSTROPHE=60, STRING_MULTILINE=61, SINGLE_QUOTE=62, EXPRESSION_MULTILINE=63, 
		PLATE_VALUE=64, IDENTIFIER=65, MEASURE_VALUE=66, NEWLINE=67, SPACES=68, 
		DOC=69, SP=70, NL=71, NEW_LINE_INDENT=72, DEDENT=73, UNKNOWN_TOKEN=74, 
		M_CHARACTER=75, QUOTE=76, Q=77, SLASH_Q=78, SLASH=79, CHARACTER=80, ME_STRING_MULTILINE=81, 
		ME_CHARACTER=82, E_QUOTE=83, E_SLASH_Q=84, E_SLASH=85, E_CHARACTER=86, 
		QUOTE_BEGIN=87, QUOTE_END=88, EXPRESSION_BEGIN=89, EXPRESSION_END=90;
	public static final int
		RULE_root = 0, RULE_dslDeclaration = 1, RULE_imports = 2, RULE_anImport = 3, 
		RULE_doc = 4, RULE_node = 5, RULE_signature = 6, RULE_parent = 7, RULE_parameters = 8, 
		RULE_parameter = 9, RULE_value = 10, RULE_body = 11, RULE_facetApply = 12, 
		RULE_facetTarget = 13, RULE_nodeReference = 14, RULE_with = 15, RULE_variable = 16, 
		RULE_variableType = 17, RULE_contract = 18, RULE_contractValue = 19, RULE_count = 20, 
		RULE_stringValue = 21, RULE_booleanValue = 22, RULE_tupleValue = 23, RULE_naturalValue = 24, 
		RULE_integerValue = 25, RULE_doubleValue = 26, RULE_linkValue = 27, RULE_plate = 28, 
		RULE_measureValue = 29, RULE_expression = 30, RULE_tags = 31, RULE_annotations = 32, 
		RULE_annotation = 33, RULE_flags = 34, RULE_flag = 35, RULE_varInit = 36, 
		RULE_headerReference = 37, RULE_identifierReference = 38, RULE_hierarchy = 39, 
		RULE_metaidentifier = 40;
	public static final String[] ruleNames = {
		"root", "dslDeclaration", "imports", "anImport", "doc", "node", "signature", 
		"parent", "parameters", "parameter", "value", "body", "facetApply", "facetTarget", 
		"nodeReference", "with", "variable", "variableType", "contract", "contractValue", 
		"count", "stringValue", "booleanValue", "tupleValue", "naturalValue", 
		"integerValue", "doubleValue", "linkValue", "plate", "measureValue", "expression", 
		"tags", "annotations", "annotation", "flags", "flag", "varInit", "headerReference", 
		"identifierReference", "hierarchy", "metaidentifier"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'Concept'", "'sub'", "'use'", "'dsl'", "'var'", "'as'", "'has'", 
		"'on'", "'is'", "'into'", "'with'", "'any'", "'extends'", "'abstract'", 
		"'single'", "'required'", "'terminal'", "'main'", "'prototype'", "'feature'", 
		"'final'", "'enclosed'", "'private'", "'facet'", "'('", "')'", "'['", 
		"']'", "'...'", "'>'", "'<'", "'#'", "':'", "','", "'.'", "'='", null, 
		"'+'", "'word'", "'file'", "'integer'", "'tuple'", "'natural'", "'native'", 
		"'double'", "'string'", "'boolean'", "'measure'", "'ratio'", "'date'", 
		"'time'", "'empty'", null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, "'indent'", 
		"'dedent'", null, null, null, "'\"'", "'\\\"'", null, null, null, null, 
		null, "'\\''", null, null, "'%QUOTE_BEGIN%'", "'%QUOTE_END%'", "'%EXPRESSION_BEGIN%'", 
		"'%EXPRESSION_END%'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "METAIDENTIFIER", "SUB", "USE", "DSL", "VAR", "AS", "HAS", "ON", 
		"IS", "INTO", "WITH", "ANY", "EXTENDS", "ABSTRACT", "SINGLE", "REQUIRED", 
		"TERMINAL", "MAIN", "PROTOTYPE", "FEATURE", "FINAL", "ENCLOSED", "PRIVATE", 
		"FACET", "LEFT_PARENTHESIS", "RIGHT_PARENTHESIS", "LEFT_SQUARE", "RIGHT_SQUARE", 
		"LIST", "INLINE", "CLOSE_INLINE", "HASHTAG", "COLON", "COMMA", "DOT", 
		"EQUALS", "SEMICOLON", "PLUS", "WORD", "RESOURCE", "INT_TYPE", "TUPLE_TYPE", 
		"NATURAL_TYPE", "NATIVE_TYPE", "DOUBLE_TYPE", "STRING_TYPE", "BOOLEAN_TYPE", 
		"MEASURE_TYPE", "RATIO_TYPE", "DATE_TYPE", "TIME_TYPE", "EMPTY", "BLOCK_COMMENT", 
		"LINE_COMMENT", "SCIENCE_NOT", "BOOLEAN_VALUE", "NATURAL_VALUE", "NEGATIVE_VALUE", 
		"DOUBLE_VALUE", "APHOSTROPHE", "STRING_MULTILINE", "SINGLE_QUOTE", "EXPRESSION_MULTILINE", 
		"PLATE_VALUE", "IDENTIFIER", "MEASURE_VALUE", "NEWLINE", "SPACES", "DOC", 
		"SP", "NL", "NEW_LINE_INDENT", "DEDENT", "UNKNOWN_TOKEN", "M_CHARACTER", 
		"QUOTE", "Q", "SLASH_Q", "SLASH", "CHARACTER", "ME_STRING_MULTILINE", 
		"ME_CHARACTER", "E_QUOTE", "E_SLASH_Q", "E_SLASH", "E_CHARACTER", "QUOTE_BEGIN", 
		"QUOTE_END", "EXPRESSION_BEGIN", "EXPRESSION_END"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "TaraGrammar.g4"; }

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
		public TerminalNode EOF() { return getToken(TaraGrammar.EOF, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(TaraGrammar.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(TaraGrammar.NEWLINE, i);
		}
		public DslDeclarationContext dslDeclaration() {
			return getRuleContext(DslDeclarationContext.class,0);
		}
		public ImportsContext imports() {
			return getRuleContext(ImportsContext.class,0);
		}
		public List<NodeContext> node() {
			return getRuleContexts(NodeContext.class);
		}
		public NodeContext node(int i) {
			return getRuleContext(NodeContext.class,i);
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
			enterOuterAlt(_localctx, 1);
			{
			setState(85);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(82);
				match(NEWLINE);
				}
				}
				setState(87);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(89);
			_la = _input.LA(1);
			if (_la==DSL) {
				{
				setState(88);
				dslDeclaration();
				}
			}

			setState(92);
			_la = _input.LA(1);
			if (_la==USE) {
				{
				setState(91);
				imports();
				}
			}

			setState(103);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==METAIDENTIFIER || _la==SUB || _la==IDENTIFIER) {
				{
				{
				setState(94);
				node();
				setState(98);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NEWLINE) {
					{
					{
					setState(95);
					match(NEWLINE);
					}
					}
					setState(100);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				}
				setState(105);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(106);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DslDeclarationContext extends ParserRuleContext {
		public TerminalNode DSL() { return getToken(TaraGrammar.DSL, 0); }
		public HeaderReferenceContext headerReference() {
			return getRuleContext(HeaderReferenceContext.class,0);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(TaraGrammar.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(TaraGrammar.NEWLINE, i);
		}
		public DslDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dslDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterDslDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitDslDeclaration(this);
		}
	}

	public final DslDeclarationContext dslDeclaration() throws RecognitionException {
		DslDeclarationContext _localctx = new DslDeclarationContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_dslDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108);
			match(DSL);
			setState(109);
			headerReference();
			setState(113);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(110);
				match(NEWLINE);
				}
				}
				setState(115);
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

	public static class ImportsContext extends ParserRuleContext {
		public List<AnImportContext> anImport() {
			return getRuleContexts(AnImportContext.class);
		}
		public AnImportContext anImport(int i) {
			return getRuleContext(AnImportContext.class,i);
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
		enterRule(_localctx, 4, RULE_imports);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(117); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(116);
				anImport();
				}
				}
				setState(119); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==USE );
			}
		}
		catch (RecognitionException re) {
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
		public TerminalNode USE() { return getToken(TaraGrammar.USE, 0); }
		public HeaderReferenceContext headerReference() {
			return getRuleContext(HeaderReferenceContext.class,0);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(TaraGrammar.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(TaraGrammar.NEWLINE, i);
		}
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
		enterRule(_localctx, 6, RULE_anImport);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(121);
			match(USE);
			setState(122);
			headerReference();
			setState(124); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(123);
				match(NEWLINE);
				}
				}
				setState(126); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NEWLINE );
			}
		}
		catch (RecognitionException re) {
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
		public List<TerminalNode> DOC() { return getTokens(TaraGrammar.DOC); }
		public TerminalNode DOC(int i) {
			return getToken(TaraGrammar.DOC, i);
		}
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
		enterRule(_localctx, 8, RULE_doc);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(129); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(128);
				match(DOC);
				}
				}
				setState(131); 
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

	public static class NodeContext extends ParserRuleContext {
		public SignatureContext signature() {
			return getRuleContext(SignatureContext.class,0);
		}
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public NodeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_node; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterNode(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitNode(this);
		}
	}

	public final NodeContext node() throws RecognitionException {
		NodeContext _localctx = new NodeContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_node);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(133);
			signature();
			setState(135);
			_la = _input.LA(1);
			if (_la==NEW_LINE_INDENT) {
				{
				setState(134);
				body();
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
		public TagsContext tags() {
			return getRuleContext(TagsContext.class,0);
		}
		public PlateContext plate() {
			return getRuleContext(PlateContext.class,0);
		}
		public TerminalNode SUB() { return getToken(TaraGrammar.SUB, 0); }
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
		public MetaidentifierContext metaidentifier() {
			return getRuleContext(MetaidentifierContext.class,0);
		}
		public ParametersContext parameters() {
			return getRuleContext(ParametersContext.class,0);
		}
		public ParentContext parent() {
			return getRuleContext(ParentContext.class,0);
		}
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
		enterRule(_localctx, 12, RULE_signature);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(152);
			switch (_input.LA(1)) {
			case SUB:
				{
				{
				setState(137);
				match(SUB);
				setState(139);
				_la = _input.LA(1);
				if (_la==LEFT_PARENTHESIS) {
					{
					setState(138);
					parameters();
					}
				}

				setState(141);
				match(IDENTIFIER);
				}
				}
				break;
			case METAIDENTIFIER:
			case IDENTIFIER:
				{
				{
				setState(142);
				metaidentifier();
				setState(144);
				_la = _input.LA(1);
				if (_la==LEFT_PARENTHESIS) {
					{
					setState(143);
					parameters();
					}
				}

				setState(147);
				switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
				case 1:
					{
					setState(146);
					match(IDENTIFIER);
					}
					break;
				}
				setState(150);
				_la = _input.LA(1);
				if (_la==EXTENDS) {
					{
					setState(149);
					parent();
					}
				}

				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(154);
			tags();
			setState(156);
			_la = _input.LA(1);
			if (_la==PLATE_VALUE) {
				{
				setState(155);
				plate();
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

	public static class ParentContext extends ParserRuleContext {
		public TerminalNode EXTENDS() { return getToken(TaraGrammar.EXTENDS, 0); }
		public IdentifierReferenceContext identifierReference() {
			return getRuleContext(IdentifierReferenceContext.class,0);
		}
		public ParentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parent; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterParent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitParent(this);
		}
	}

	public final ParentContext parent() throws RecognitionException {
		ParentContext _localctx = new ParentContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_parent);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(158);
			match(EXTENDS);
			setState(159);
			identifierReference();
			}
		}
		catch (RecognitionException re) {
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
		public List<ParameterContext> parameter() {
			return getRuleContexts(ParameterContext.class);
		}
		public ParameterContext parameter(int i) {
			return getRuleContext(ParameterContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(TaraGrammar.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(TaraGrammar.COMMA, i);
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
			setState(161);
			match(LEFT_PARENTHESIS);
			setState(170);
			_la = _input.LA(1);
			if (((((_la - 52)) & ~0x3f) == 0 && ((1L << (_la - 52)) & ((1L << (EMPTY - 52)) | (1L << (BOOLEAN_VALUE - 52)) | (1L << (NATURAL_VALUE - 52)) | (1L << (NEGATIVE_VALUE - 52)) | (1L << (DOUBLE_VALUE - 52)) | (1L << (PLATE_VALUE - 52)) | (1L << (IDENTIFIER - 52)) | (1L << (NEWLINE - 52)) | (1L << (QUOTE_BEGIN - 52)) | (1L << (EXPRESSION_BEGIN - 52)))) != 0)) {
				{
				setState(162);
				parameter();
				setState(167);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(163);
					match(COMMA);
					setState(164);
					parameter();
					}
					}
					setState(169);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(172);
			match(RIGHT_PARENTHESIS);
			}
		}
		catch (RecognitionException re) {
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
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
		public TerminalNode EQUALS() { return getToken(TaraGrammar.EQUALS, 0); }
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
			enterOuterAlt(_localctx, 1);
			{
			setState(176);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				{
				setState(174);
				match(IDENTIFIER);
				setState(175);
				match(EQUALS);
				}
				break;
			}
			setState(178);
			value();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ValueContext extends ParserRuleContext {
		public List<IdentifierReferenceContext> identifierReference() {
			return getRuleContexts(IdentifierReferenceContext.class);
		}
		public IdentifierReferenceContext identifierReference(int i) {
			return getRuleContext(IdentifierReferenceContext.class,i);
		}
		public List<StringValueContext> stringValue() {
			return getRuleContexts(StringValueContext.class);
		}
		public StringValueContext stringValue(int i) {
			return getRuleContext(StringValueContext.class,i);
		}
		public List<TupleValueContext> tupleValue() {
			return getRuleContexts(TupleValueContext.class);
		}
		public TupleValueContext tupleValue(int i) {
			return getRuleContext(TupleValueContext.class,i);
		}
		public List<BooleanValueContext> booleanValue() {
			return getRuleContexts(BooleanValueContext.class);
		}
		public BooleanValueContext booleanValue(int i) {
			return getRuleContext(BooleanValueContext.class,i);
		}
		public List<LinkValueContext> linkValue() {
			return getRuleContexts(LinkValueContext.class);
		}
		public LinkValueContext linkValue(int i) {
			return getRuleContext(LinkValueContext.class,i);
		}
		public List<NaturalValueContext> naturalValue() {
			return getRuleContexts(NaturalValueContext.class);
		}
		public NaturalValueContext naturalValue(int i) {
			return getRuleContext(NaturalValueContext.class,i);
		}
		public MeasureValueContext measureValue() {
			return getRuleContext(MeasureValueContext.class,0);
		}
		public List<IntegerValueContext> integerValue() {
			return getRuleContexts(IntegerValueContext.class);
		}
		public IntegerValueContext integerValue(int i) {
			return getRuleContext(IntegerValueContext.class,i);
		}
		public List<DoubleValueContext> doubleValue() {
			return getRuleContexts(DoubleValueContext.class);
		}
		public DoubleValueContext doubleValue(int i) {
			return getRuleContext(DoubleValueContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode EMPTY() { return getToken(TaraGrammar.EMPTY, 0); }
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitValue(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_value);
		int _la;
		try {
			int _alt;
			setState(235);
			switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(181); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(180);
					identifierReference();
					}
					}
					setState(183); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==IDENTIFIER );
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(186); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(185);
						stringValue();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(188); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(191); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(190);
						tupleValue();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(193); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(196); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(195);
					booleanValue();
					}
					}
					setState(198); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==BOOLEAN_VALUE );
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(201); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(200);
					linkValue();
					}
					}
					setState(203); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==PLATE_VALUE || _la==IDENTIFIER );
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(206); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(205);
					naturalValue();
					}
					}
					setState(208); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NATURAL_VALUE );
				setState(211);
				switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
				case 1:
					{
					setState(210);
					measureValue();
					}
					break;
				}
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(214); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(213);
					integerValue();
					}
					}
					setState(216); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NATURAL_VALUE || _la==NEGATIVE_VALUE );
				setState(219);
				switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
				case 1:
					{
					setState(218);
					measureValue();
					}
					break;
				}
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(222); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(221);
					doubleValue();
					}
					}
					setState(224); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NATURAL_VALUE) | (1L << NEGATIVE_VALUE) | (1L << DOUBLE_VALUE))) != 0) );
				setState(227);
				switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
				case 1:
					{
					setState(226);
					measureValue();
					}
					break;
				}
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(230); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(229);
						expression();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(232); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(234);
				match(EMPTY);
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
		public TerminalNode NEW_LINE_INDENT() { return getToken(TaraGrammar.NEW_LINE_INDENT, 0); }
		public TerminalNode DEDENT() { return getToken(TaraGrammar.DEDENT, 0); }
		public List<VariableContext> variable() {
			return getRuleContexts(VariableContext.class);
		}
		public VariableContext variable(int i) {
			return getRuleContext(VariableContext.class,i);
		}
		public List<NodeContext> node() {
			return getRuleContexts(NodeContext.class);
		}
		public NodeContext node(int i) {
			return getRuleContext(NodeContext.class,i);
		}
		public List<VarInitContext> varInit() {
			return getRuleContexts(VarInitContext.class);
		}
		public VarInitContext varInit(int i) {
			return getRuleContext(VarInitContext.class,i);
		}
		public List<FacetApplyContext> facetApply() {
			return getRuleContexts(FacetApplyContext.class);
		}
		public FacetApplyContext facetApply(int i) {
			return getRuleContext(FacetApplyContext.class,i);
		}
		public List<FacetTargetContext> facetTarget() {
			return getRuleContexts(FacetTargetContext.class);
		}
		public FacetTargetContext facetTarget(int i) {
			return getRuleContext(FacetTargetContext.class,i);
		}
		public List<NodeReferenceContext> nodeReference() {
			return getRuleContexts(NodeReferenceContext.class);
		}
		public NodeReferenceContext nodeReference(int i) {
			return getRuleContext(NodeReferenceContext.class,i);
		}
		public List<DocContext> doc() {
			return getRuleContexts(DocContext.class);
		}
		public DocContext doc(int i) {
			return getRuleContext(DocContext.class,i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(TaraGrammar.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(TaraGrammar.NEWLINE, i);
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
			setState(237);
			match(NEW_LINE_INDENT);
			setState(252); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(245);
				switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
				case 1:
					{
					setState(238);
					variable();
					}
					break;
				case 2:
					{
					setState(239);
					node();
					}
					break;
				case 3:
					{
					setState(240);
					varInit();
					}
					break;
				case 4:
					{
					setState(241);
					facetApply();
					}
					break;
				case 5:
					{
					setState(242);
					facetTarget();
					}
					break;
				case 6:
					{
					setState(243);
					nodeReference();
					}
					break;
				case 7:
					{
					setState(244);
					doc();
					}
					break;
				}
				setState(248); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(247);
					match(NEWLINE);
					}
					}
					setState(250); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NEWLINE );
				}
				}
				setState(254); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << METAIDENTIFIER) | (1L << SUB) | (1L << VAR) | (1L << AS) | (1L << HAS) | (1L << ON))) != 0) || _la==IDENTIFIER || _la==DOC );
			setState(256);
			match(DEDENT);
			}
		}
		catch (RecognitionException re) {
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
		public TerminalNode AS() { return getToken(TaraGrammar.AS, 0); }
		public MetaidentifierContext metaidentifier() {
			return getRuleContext(MetaidentifierContext.class,0);
		}
		public ParametersContext parameters() {
			return getRuleContext(ParametersContext.class,0);
		}
		public WithContext with() {
			return getRuleContext(WithContext.class,0);
		}
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
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
		enterRule(_localctx, 24, RULE_facetApply);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(258);
			match(AS);
			setState(259);
			metaidentifier();
			setState(261);
			_la = _input.LA(1);
			if (_la==LEFT_PARENTHESIS) {
				{
				setState(260);
				parameters();
				}
			}

			setState(264);
			_la = _input.LA(1);
			if (_la==WITH) {
				{
				setState(263);
				with();
				}
			}

			setState(267);
			_la = _input.LA(1);
			if (_la==NEW_LINE_INDENT) {
				{
				setState(266);
				body();
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
		public TerminalNode ON() { return getToken(TaraGrammar.ON, 0); }
		public IdentifierReferenceContext identifierReference() {
			return getRuleContext(IdentifierReferenceContext.class,0);
		}
		public TerminalNode ANY() { return getToken(TaraGrammar.ANY, 0); }
		public WithContext with() {
			return getRuleContext(WithContext.class,0);
		}
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
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
		enterRule(_localctx, 26, RULE_facetTarget);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(269);
			match(ON);
			setState(272);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				{
				setState(270);
				identifierReference();
				}
				break;
			case ANY:
				{
				setState(271);
				match(ANY);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(275);
			_la = _input.LA(1);
			if (_la==WITH) {
				{
				setState(274);
				with();
				}
			}

			setState(278);
			_la = _input.LA(1);
			if (_la==NEW_LINE_INDENT) {
				{
				setState(277);
				body();
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

	public static class NodeReferenceContext extends ParserRuleContext {
		public TerminalNode HAS() { return getToken(TaraGrammar.HAS, 0); }
		public IdentifierReferenceContext identifierReference() {
			return getRuleContext(IdentifierReferenceContext.class,0);
		}
		public TagsContext tags() {
			return getRuleContext(TagsContext.class,0);
		}
		public NodeReferenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nodeReference; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterNodeReference(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitNodeReference(this);
		}
	}

	public final NodeReferenceContext nodeReference() throws RecognitionException {
		NodeReferenceContext _localctx = new NodeReferenceContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_nodeReference);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(280);
			match(HAS);
			setState(281);
			identifierReference();
			setState(282);
			tags();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WithContext extends ParserRuleContext {
		public TerminalNode WITH() { return getToken(TaraGrammar.WITH, 0); }
		public List<IdentifierReferenceContext> identifierReference() {
			return getRuleContexts(IdentifierReferenceContext.class);
		}
		public IdentifierReferenceContext identifierReference(int i) {
			return getRuleContext(IdentifierReferenceContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(TaraGrammar.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(TaraGrammar.COMMA, i);
		}
		public WithContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_with; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterWith(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitWith(this);
		}
	}

	public final WithContext with() throws RecognitionException {
		WithContext _localctx = new WithContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_with);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(284);
			match(WITH);
			setState(285);
			identifierReference();
			setState(290);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(286);
				match(COMMA);
				setState(287);
				identifierReference();
				}
				}
				setState(292);
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

	public static class VariableContext extends ParserRuleContext {
		public TerminalNode VAR() { return getToken(TaraGrammar.VAR, 0); }
		public VariableTypeContext variableType() {
			return getRuleContext(VariableTypeContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
		public ContractContext contract() {
			return getRuleContext(ContractContext.class,0);
		}
		public TerminalNode LIST() { return getToken(TaraGrammar.LIST, 0); }
		public CountContext count() {
			return getRuleContext(CountContext.class,0);
		}
		public TerminalNode EQUALS() { return getToken(TaraGrammar.EQUALS, 0); }
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public FlagsContext flags() {
			return getRuleContext(FlagsContext.class,0);
		}
		public TerminalNode MEASURE_VALUE() { return getToken(TaraGrammar.MEASURE_VALUE, 0); }
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
			match(VAR);
			setState(294);
			variableType();
			setState(296);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(295);
				contract();
				}
			}

			setState(300);
			switch (_input.LA(1)) {
			case LIST:
				{
				setState(298);
				match(LIST);
				}
				break;
			case LEFT_SQUARE:
				{
				setState(299);
				count();
				}
				break;
			case IDENTIFIER:
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(302);
			match(IDENTIFIER);
			setState(308);
			_la = _input.LA(1);
			if (_la==EQUALS) {
				{
				setState(303);
				match(EQUALS);
				setState(304);
				value();
				setState(306);
				_la = _input.LA(1);
				if (_la==MEASURE_VALUE) {
					{
					setState(305);
					match(MEASURE_VALUE);
					}
				}

				}
			}

			setState(311);
			_la = _input.LA(1);
			if (_la==IS) {
				{
				setState(310);
				flags();
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

	public static class VariableTypeContext extends ParserRuleContext {
		public TerminalNode NATURAL_TYPE() { return getToken(TaraGrammar.NATURAL_TYPE, 0); }
		public TerminalNode INT_TYPE() { return getToken(TaraGrammar.INT_TYPE, 0); }
		public TerminalNode BOOLEAN_TYPE() { return getToken(TaraGrammar.BOOLEAN_TYPE, 0); }
		public TerminalNode TUPLE_TYPE() { return getToken(TaraGrammar.TUPLE_TYPE, 0); }
		public TerminalNode STRING_TYPE() { return getToken(TaraGrammar.STRING_TYPE, 0); }
		public TerminalNode DATE_TYPE() { return getToken(TaraGrammar.DATE_TYPE, 0); }
		public TerminalNode TIME_TYPE() { return getToken(TaraGrammar.TIME_TYPE, 0); }
		public TerminalNode NATIVE_TYPE() { return getToken(TaraGrammar.NATIVE_TYPE, 0); }
		public TerminalNode RATIO_TYPE() { return getToken(TaraGrammar.RATIO_TYPE, 0); }
		public TerminalNode DOUBLE_TYPE() { return getToken(TaraGrammar.DOUBLE_TYPE, 0); }
		public TerminalNode MEASURE_TYPE() { return getToken(TaraGrammar.MEASURE_TYPE, 0); }
		public TerminalNode WORD() { return getToken(TaraGrammar.WORD, 0); }
		public TerminalNode RESOURCE() { return getToken(TaraGrammar.RESOURCE, 0); }
		public IdentifierReferenceContext identifierReference() {
			return getRuleContext(IdentifierReferenceContext.class,0);
		}
		public VariableTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterVariableType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitVariableType(this);
		}
	}

	public final VariableTypeContext variableType() throws RecognitionException {
		VariableTypeContext _localctx = new VariableTypeContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_variableType);
		try {
			setState(327);
			switch (_input.LA(1)) {
			case NATURAL_TYPE:
				enterOuterAlt(_localctx, 1);
				{
				setState(313);
				match(NATURAL_TYPE);
				}
				break;
			case INT_TYPE:
				enterOuterAlt(_localctx, 2);
				{
				setState(314);
				match(INT_TYPE);
				}
				break;
			case BOOLEAN_TYPE:
				enterOuterAlt(_localctx, 3);
				{
				setState(315);
				match(BOOLEAN_TYPE);
				}
				break;
			case TUPLE_TYPE:
				enterOuterAlt(_localctx, 4);
				{
				setState(316);
				match(TUPLE_TYPE);
				}
				break;
			case STRING_TYPE:
				enterOuterAlt(_localctx, 5);
				{
				setState(317);
				match(STRING_TYPE);
				}
				break;
			case DATE_TYPE:
				enterOuterAlt(_localctx, 6);
				{
				setState(318);
				match(DATE_TYPE);
				}
				break;
			case TIME_TYPE:
				enterOuterAlt(_localctx, 7);
				{
				setState(319);
				match(TIME_TYPE);
				}
				break;
			case NATIVE_TYPE:
				enterOuterAlt(_localctx, 8);
				{
				setState(320);
				match(NATIVE_TYPE);
				}
				break;
			case RATIO_TYPE:
				enterOuterAlt(_localctx, 9);
				{
				setState(321);
				match(RATIO_TYPE);
				}
				break;
			case DOUBLE_TYPE:
				enterOuterAlt(_localctx, 10);
				{
				setState(322);
				match(DOUBLE_TYPE);
				}
				break;
			case MEASURE_TYPE:
				enterOuterAlt(_localctx, 11);
				{
				setState(323);
				match(MEASURE_TYPE);
				}
				break;
			case WORD:
				enterOuterAlt(_localctx, 12);
				{
				setState(324);
				match(WORD);
				}
				break;
			case RESOURCE:
				enterOuterAlt(_localctx, 13);
				{
				setState(325);
				match(RESOURCE);
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 14);
				{
				setState(326);
				identifierReference();
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

	public static class ContractContext extends ParserRuleContext {
		public TerminalNode COLON() { return getToken(TaraGrammar.COLON, 0); }
		public ContractValueContext contractValue() {
			return getRuleContext(ContractValueContext.class,0);
		}
		public ContractContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_contract; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterContract(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitContract(this);
		}
	}

	public final ContractContext contract() throws RecognitionException {
		ContractContext _localctx = new ContractContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_contract);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(329);
			match(COLON);
			setState(330);
			contractValue();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ContractValueContext extends ParserRuleContext {
		public TerminalNode LEFT_SQUARE() { return getToken(TaraGrammar.LEFT_SQUARE, 0); }
		public TerminalNode RIGHT_SQUARE() { return getToken(TaraGrammar.RIGHT_SQUARE, 0); }
		public List<TerminalNode> MEASURE_VALUE() { return getTokens(TaraGrammar.MEASURE_VALUE); }
		public TerminalNode MEASURE_VALUE(int i) {
			return getToken(TaraGrammar.MEASURE_VALUE, i);
		}
		public List<TerminalNode> IDENTIFIER() { return getTokens(TaraGrammar.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(TaraGrammar.IDENTIFIER, i);
		}
		public ContractValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_contractValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterContractValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitContractValue(this);
		}
	}

	public final ContractValueContext contractValue() throws RecognitionException {
		ContractValueContext _localctx = new ContractValueContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_contractValue);
		int _la;
		try {
			setState(340);
			switch (_input.LA(1)) {
			case LEFT_SQUARE:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(332);
				match(LEFT_SQUARE);
				setState(334); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(333);
					_la = _input.LA(1);
					if ( !(_la==IDENTIFIER || _la==MEASURE_VALUE) ) {
					_errHandler.recoverInline(this);
					} else {
						consume();
					}
					}
					}
					setState(336); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==IDENTIFIER || _la==MEASURE_VALUE );
				setState(338);
				match(RIGHT_SQUARE);
				}
				}
				break;
			case IDENTIFIER:
			case MEASURE_VALUE:
				enterOuterAlt(_localctx, 2);
				{
				setState(339);
				_la = _input.LA(1);
				if ( !(_la==IDENTIFIER || _la==MEASURE_VALUE) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
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

	public static class CountContext extends ParserRuleContext {
		public TerminalNode LEFT_SQUARE() { return getToken(TaraGrammar.LEFT_SQUARE, 0); }
		public TerminalNode NATURAL_VALUE() { return getToken(TaraGrammar.NATURAL_VALUE, 0); }
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
		enterRule(_localctx, 40, RULE_count);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(342);
			match(LEFT_SQUARE);
			setState(343);
			match(NATURAL_VALUE);
			setState(344);
			match(RIGHT_SQUARE);
			}
		}
		catch (RecognitionException re) {
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
		public TerminalNode QUOTE_BEGIN() { return getToken(TaraGrammar.QUOTE_BEGIN, 0); }
		public TerminalNode QUOTE_END() { return getToken(TaraGrammar.QUOTE_END, 0); }
		public TerminalNode NEWLINE() { return getToken(TaraGrammar.NEWLINE, 0); }
		public List<TerminalNode> CHARACTER() { return getTokens(TaraGrammar.CHARACTER); }
		public TerminalNode CHARACTER(int i) {
			return getToken(TaraGrammar.CHARACTER, i);
		}
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
		enterRule(_localctx, 42, RULE_stringValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(347);
			_la = _input.LA(1);
			if (_la==NEWLINE) {
				{
				setState(346);
				match(NEWLINE);
				}
			}

			{
			setState(349);
			match(QUOTE_BEGIN);
			setState(353);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==CHARACTER) {
				{
				{
				setState(350);
				match(CHARACTER);
				}
				}
				setState(355);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(356);
			match(QUOTE_END);
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
		enterRule(_localctx, 44, RULE_booleanValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(358);
			match(BOOLEAN_VALUE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TupleValueContext extends ParserRuleContext {
		public StringValueContext stringValue() {
			return getRuleContext(StringValueContext.class,0);
		}
		public TerminalNode COLON() { return getToken(TaraGrammar.COLON, 0); }
		public DoubleValueContext doubleValue() {
			return getRuleContext(DoubleValueContext.class,0);
		}
		public TupleValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tupleValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterTupleValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitTupleValue(this);
		}
	}

	public final TupleValueContext tupleValue() throws RecognitionException {
		TupleValueContext _localctx = new TupleValueContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_tupleValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(360);
			stringValue();
			setState(361);
			match(COLON);
			setState(362);
			doubleValue();
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 48, RULE_naturalValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(364);
			match(NATURAL_VALUE);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 50, RULE_integerValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(366);
			_la = _input.LA(1);
			if ( !(_la==NATURAL_VALUE || _la==NEGATIVE_VALUE) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
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

	public static class DoubleValueContext extends ParserRuleContext {
		public TerminalNode NATURAL_VALUE() { return getToken(TaraGrammar.NATURAL_VALUE, 0); }
		public TerminalNode NEGATIVE_VALUE() { return getToken(TaraGrammar.NEGATIVE_VALUE, 0); }
		public TerminalNode DOUBLE_VALUE() { return getToken(TaraGrammar.DOUBLE_VALUE, 0); }
		public TerminalNode SCIENCE_NOT() { return getToken(TaraGrammar.SCIENCE_NOT, 0); }
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
		enterRule(_localctx, 52, RULE_doubleValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(368);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NATURAL_VALUE) | (1L << NEGATIVE_VALUE) | (1L << DOUBLE_VALUE))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			setState(370);
			_la = _input.LA(1);
			if (_la==SCIENCE_NOT) {
				{
				setState(369);
				match(SCIENCE_NOT);
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

	public static class LinkValueContext extends ParserRuleContext {
		public PlateContext plate() {
			return getRuleContext(PlateContext.class,0);
		}
		public IdentifierReferenceContext identifierReference() {
			return getRuleContext(IdentifierReferenceContext.class,0);
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
		enterRule(_localctx, 54, RULE_linkValue);
		try {
			setState(374);
			switch (_input.LA(1)) {
			case PLATE_VALUE:
				enterOuterAlt(_localctx, 1);
				{
				setState(372);
				plate();
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(373);
				identifierReference();
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

	public static class PlateContext extends ParserRuleContext {
		public TerminalNode PLATE_VALUE() { return getToken(TaraGrammar.PLATE_VALUE, 0); }
		public PlateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_plate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterPlate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitPlate(this);
		}
	}

	public final PlateContext plate() throws RecognitionException {
		PlateContext _localctx = new PlateContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_plate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(376);
			match(PLATE_VALUE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MeasureValueContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
		public TerminalNode MEASURE_VALUE() { return getToken(TaraGrammar.MEASURE_VALUE, 0); }
		public MeasureValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_measureValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterMeasureValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitMeasureValue(this);
		}
	}

	public final MeasureValueContext measureValue() throws RecognitionException {
		MeasureValueContext _localctx = new MeasureValueContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_measureValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(378);
			_la = _input.LA(1);
			if ( !(_la==IDENTIFIER || _la==MEASURE_VALUE) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
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

	public static class ExpressionContext extends ParserRuleContext {
		public TerminalNode EXPRESSION_BEGIN() { return getToken(TaraGrammar.EXPRESSION_BEGIN, 0); }
		public TerminalNode EXPRESSION_END() { return getToken(TaraGrammar.EXPRESSION_END, 0); }
		public TerminalNode NEWLINE() { return getToken(TaraGrammar.NEWLINE, 0); }
		public List<TerminalNode> CHARACTER() { return getTokens(TaraGrammar.CHARACTER); }
		public TerminalNode CHARACTER(int i) {
			return getToken(TaraGrammar.CHARACTER, i);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(381);
			_la = _input.LA(1);
			if (_la==NEWLINE) {
				{
				setState(380);
				match(NEWLINE);
				}
			}

			{
			setState(383);
			match(EXPRESSION_BEGIN);
			setState(387);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==CHARACTER) {
				{
				{
				setState(384);
				match(CHARACTER);
				}
				}
				setState(389);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(390);
			match(EXPRESSION_END);
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

	public static class TagsContext extends ParserRuleContext {
		public FlagsContext flags() {
			return getRuleContext(FlagsContext.class,0);
		}
		public AnnotationsContext annotations() {
			return getRuleContext(AnnotationsContext.class,0);
		}
		public TagsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tags; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterTags(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitTags(this);
		}
	}

	public final TagsContext tags() throws RecognitionException {
		TagsContext _localctx = new TagsContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_tags);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(393);
			_la = _input.LA(1);
			if (_la==IS) {
				{
				setState(392);
				flags();
				}
			}

			setState(396);
			_la = _input.LA(1);
			if (_la==INTO) {
				{
				setState(395);
				annotations();
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

	public static class AnnotationsContext extends ParserRuleContext {
		public TerminalNode INTO() { return getToken(TaraGrammar.INTO, 0); }
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
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
		enterRule(_localctx, 64, RULE_annotations);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(398);
			match(INTO);
			setState(400); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(399);
				annotation();
				}
				}
				setState(402); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SINGLE) | (1L << REQUIRED) | (1L << TERMINAL) | (1L << MAIN) | (1L << PROTOTYPE) | (1L << FEATURE) | (1L << ENCLOSED) | (1L << FACET))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AnnotationContext extends ParserRuleContext {
		public TerminalNode TERMINAL() { return getToken(TaraGrammar.TERMINAL, 0); }
		public TerminalNode MAIN() { return getToken(TaraGrammar.MAIN, 0); }
		public TerminalNode SINGLE() { return getToken(TaraGrammar.SINGLE, 0); }
		public TerminalNode REQUIRED() { return getToken(TaraGrammar.REQUIRED, 0); }
		public TerminalNode FACET() { return getToken(TaraGrammar.FACET, 0); }
		public TerminalNode FEATURE() { return getToken(TaraGrammar.FEATURE, 0); }
		public TerminalNode PROTOTYPE() { return getToken(TaraGrammar.PROTOTYPE, 0); }
		public TerminalNode ENCLOSED() { return getToken(TaraGrammar.ENCLOSED, 0); }
		public AnnotationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterAnnotation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitAnnotation(this);
		}
	}

	public final AnnotationContext annotation() throws RecognitionException {
		AnnotationContext _localctx = new AnnotationContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_annotation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(404);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SINGLE) | (1L << REQUIRED) | (1L << TERMINAL) | (1L << MAIN) | (1L << PROTOTYPE) | (1L << FEATURE) | (1L << ENCLOSED) | (1L << FACET))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
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

	public static class FlagsContext extends ParserRuleContext {
		public TerminalNode IS() { return getToken(TaraGrammar.IS, 0); }
		public List<FlagContext> flag() {
			return getRuleContexts(FlagContext.class);
		}
		public FlagContext flag(int i) {
			return getRuleContext(FlagContext.class,i);
		}
		public FlagsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_flags; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterFlags(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitFlags(this);
		}
	}

	public final FlagsContext flags() throws RecognitionException {
		FlagsContext _localctx = new FlagsContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_flags);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(406);
			match(IS);
			setState(408); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(407);
				flag();
				}
				}
				setState(410); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABSTRACT) | (1L << SINGLE) | (1L << REQUIRED) | (1L << TERMINAL) | (1L << MAIN) | (1L << PROTOTYPE) | (1L << FEATURE) | (1L << FINAL) | (1L << ENCLOSED) | (1L << PRIVATE) | (1L << FACET))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FlagContext extends ParserRuleContext {
		public TerminalNode ABSTRACT() { return getToken(TaraGrammar.ABSTRACT, 0); }
		public TerminalNode TERMINAL() { return getToken(TaraGrammar.TERMINAL, 0); }
		public TerminalNode MAIN() { return getToken(TaraGrammar.MAIN, 0); }
		public TerminalNode SINGLE() { return getToken(TaraGrammar.SINGLE, 0); }
		public TerminalNode REQUIRED() { return getToken(TaraGrammar.REQUIRED, 0); }
		public TerminalNode PRIVATE() { return getToken(TaraGrammar.PRIVATE, 0); }
		public TerminalNode FACET() { return getToken(TaraGrammar.FACET, 0); }
		public TerminalNode FEATURE() { return getToken(TaraGrammar.FEATURE, 0); }
		public TerminalNode PROTOTYPE() { return getToken(TaraGrammar.PROTOTYPE, 0); }
		public TerminalNode ENCLOSED() { return getToken(TaraGrammar.ENCLOSED, 0); }
		public TerminalNode FINAL() { return getToken(TaraGrammar.FINAL, 0); }
		public FlagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_flag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).enterFlag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TaraGrammarListener ) ((TaraGrammarListener)listener).exitFlag(this);
		}
	}

	public final FlagContext flag() throws RecognitionException {
		FlagContext _localctx = new FlagContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_flag);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(412);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABSTRACT) | (1L << SINGLE) | (1L << REQUIRED) | (1L << TERMINAL) | (1L << MAIN) | (1L << PROTOTYPE) | (1L << FEATURE) | (1L << FINAL) | (1L << ENCLOSED) | (1L << PRIVATE) | (1L << FACET))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
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

	public static class VarInitContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
		public TerminalNode EQUALS() { return getToken(TaraGrammar.EQUALS, 0); }
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
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
		enterRule(_localctx, 72, RULE_varInit);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(414);
			match(IDENTIFIER);
			setState(415);
			match(EQUALS);
			setState(416);
			value();
			}
		}
		catch (RecognitionException re) {
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
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
		public List<HierarchyContext> hierarchy() {
			return getRuleContexts(HierarchyContext.class);
		}
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
		enterRule(_localctx, 74, RULE_headerReference);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(421);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,60,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(418);
					hierarchy();
					}
					} 
				}
				setState(423);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,60,_ctx);
			}
			setState(424);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
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
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
		public List<HierarchyContext> hierarchy() {
			return getRuleContexts(HierarchyContext.class);
		}
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
		enterRule(_localctx, 76, RULE_identifierReference);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(429);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,61,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(426);
					hierarchy();
					}
					} 
				}
				setState(431);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,61,_ctx);
			}
			setState(432);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
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
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
		public TerminalNode DOT() { return getToken(TaraGrammar.DOT, 0); }
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
		enterRule(_localctx, 78, RULE_hierarchy);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(434);
			match(IDENTIFIER);
			setState(435);
			match(DOT);
			}
		}
		catch (RecognitionException re) {
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
		public TerminalNode METAIDENTIFIER() { return getToken(TaraGrammar.METAIDENTIFIER, 0); }
		public TerminalNode IDENTIFIER() { return getToken(TaraGrammar.IDENTIFIER, 0); }
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
		enterRule(_localctx, 80, RULE_metaidentifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(437);
			_la = _input.LA(1);
			if ( !(_la==METAIDENTIFIER || _la==IDENTIFIER) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
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

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\\\u01ba\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\3\2\7\2"+
		"V\n\2\f\2\16\2Y\13\2\3\2\5\2\\\n\2\3\2\5\2_\n\2\3\2\3\2\7\2c\n\2\f\2\16"+
		"\2f\13\2\7\2h\n\2\f\2\16\2k\13\2\3\2\3\2\3\3\3\3\3\3\7\3r\n\3\f\3\16\3"+
		"u\13\3\3\4\6\4x\n\4\r\4\16\4y\3\5\3\5\3\5\6\5\177\n\5\r\5\16\5\u0080\3"+
		"\6\6\6\u0084\n\6\r\6\16\6\u0085\3\7\3\7\5\7\u008a\n\7\3\b\3\b\5\b\u008e"+
		"\n\b\3\b\3\b\3\b\5\b\u0093\n\b\3\b\5\b\u0096\n\b\3\b\5\b\u0099\n\b\5\b"+
		"\u009b\n\b\3\b\3\b\5\b\u009f\n\b\3\t\3\t\3\t\3\n\3\n\3\n\3\n\7\n\u00a8"+
		"\n\n\f\n\16\n\u00ab\13\n\5\n\u00ad\n\n\3\n\3\n\3\13\3\13\5\13\u00b3\n"+
		"\13\3\13\3\13\3\f\6\f\u00b8\n\f\r\f\16\f\u00b9\3\f\6\f\u00bd\n\f\r\f\16"+
		"\f\u00be\3\f\6\f\u00c2\n\f\r\f\16\f\u00c3\3\f\6\f\u00c7\n\f\r\f\16\f\u00c8"+
		"\3\f\6\f\u00cc\n\f\r\f\16\f\u00cd\3\f\6\f\u00d1\n\f\r\f\16\f\u00d2\3\f"+
		"\5\f\u00d6\n\f\3\f\6\f\u00d9\n\f\r\f\16\f\u00da\3\f\5\f\u00de\n\f\3\f"+
		"\6\f\u00e1\n\f\r\f\16\f\u00e2\3\f\5\f\u00e6\n\f\3\f\6\f\u00e9\n\f\r\f"+
		"\16\f\u00ea\3\f\5\f\u00ee\n\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u00f8"+
		"\n\r\3\r\6\r\u00fb\n\r\r\r\16\r\u00fc\6\r\u00ff\n\r\r\r\16\r\u0100\3\r"+
		"\3\r\3\16\3\16\3\16\5\16\u0108\n\16\3\16\5\16\u010b\n\16\3\16\5\16\u010e"+
		"\n\16\3\17\3\17\3\17\5\17\u0113\n\17\3\17\5\17\u0116\n\17\3\17\5\17\u0119"+
		"\n\17\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\7\21\u0123\n\21\f\21\16"+
		"\21\u0126\13\21\3\22\3\22\3\22\5\22\u012b\n\22\3\22\3\22\5\22\u012f\n"+
		"\22\3\22\3\22\3\22\3\22\5\22\u0135\n\22\5\22\u0137\n\22\3\22\5\22\u013a"+
		"\n\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\5\23\u014a\n\23\3\24\3\24\3\24\3\25\3\25\6\25\u0151\n\25\r\25\16"+
		"\25\u0152\3\25\3\25\5\25\u0157\n\25\3\26\3\26\3\26\3\26\3\27\5\27\u015e"+
		"\n\27\3\27\3\27\7\27\u0162\n\27\f\27\16\27\u0165\13\27\3\27\3\27\3\30"+
		"\3\30\3\31\3\31\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\5\34\u0175\n\34"+
		"\3\35\3\35\5\35\u0179\n\35\3\36\3\36\3\37\3\37\3 \5 \u0180\n \3 \3 \7"+
		" \u0184\n \f \16 \u0187\13 \3 \3 \3!\5!\u018c\n!\3!\5!\u018f\n!\3\"\3"+
		"\"\6\"\u0193\n\"\r\"\16\"\u0194\3#\3#\3$\3$\6$\u019b\n$\r$\16$\u019c\3"+
		"%\3%\3&\3&\3&\3&\3\'\7\'\u01a6\n\'\f\'\16\'\u01a9\13\'\3\'\3\'\3(\7(\u01ae"+
		"\n(\f(\16(\u01b1\13(\3(\3(\3)\3)\3)\3*\3*\3*\2\2+\2\4\6\b\n\f\16\20\22"+
		"\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJLNPR\2\b\3\2CD\3\2;<"+
		"\3\2;=\5\2\21\26\30\30\32\32\3\2\20\32\4\2\3\3CC\u01e8\2W\3\2\2\2\4n\3"+
		"\2\2\2\6w\3\2\2\2\b{\3\2\2\2\n\u0083\3\2\2\2\f\u0087\3\2\2\2\16\u009a"+
		"\3\2\2\2\20\u00a0\3\2\2\2\22\u00a3\3\2\2\2\24\u00b2\3\2\2\2\26\u00ed\3"+
		"\2\2\2\30\u00ef\3\2\2\2\32\u0104\3\2\2\2\34\u010f\3\2\2\2\36\u011a\3\2"+
		"\2\2 \u011e\3\2\2\2\"\u0127\3\2\2\2$\u0149\3\2\2\2&\u014b\3\2\2\2(\u0156"+
		"\3\2\2\2*\u0158\3\2\2\2,\u015d\3\2\2\2.\u0168\3\2\2\2\60\u016a\3\2\2\2"+
		"\62\u016e\3\2\2\2\64\u0170\3\2\2\2\66\u0172\3\2\2\28\u0178\3\2\2\2:\u017a"+
		"\3\2\2\2<\u017c\3\2\2\2>\u017f\3\2\2\2@\u018b\3\2\2\2B\u0190\3\2\2\2D"+
		"\u0196\3\2\2\2F\u0198\3\2\2\2H\u019e\3\2\2\2J\u01a0\3\2\2\2L\u01a7\3\2"+
		"\2\2N\u01af\3\2\2\2P\u01b4\3\2\2\2R\u01b7\3\2\2\2TV\7E\2\2UT\3\2\2\2V"+
		"Y\3\2\2\2WU\3\2\2\2WX\3\2\2\2X[\3\2\2\2YW\3\2\2\2Z\\\5\4\3\2[Z\3\2\2\2"+
		"[\\\3\2\2\2\\^\3\2\2\2]_\5\6\4\2^]\3\2\2\2^_\3\2\2\2_i\3\2\2\2`d\5\f\7"+
		"\2ac\7E\2\2ba\3\2\2\2cf\3\2\2\2db\3\2\2\2de\3\2\2\2eh\3\2\2\2fd\3\2\2"+
		"\2g`\3\2\2\2hk\3\2\2\2ig\3\2\2\2ij\3\2\2\2jl\3\2\2\2ki\3\2\2\2lm\7\2\2"+
		"\3m\3\3\2\2\2no\7\6\2\2os\5L\'\2pr\7E\2\2qp\3\2\2\2ru\3\2\2\2sq\3\2\2"+
		"\2st\3\2\2\2t\5\3\2\2\2us\3\2\2\2vx\5\b\5\2wv\3\2\2\2xy\3\2\2\2yw\3\2"+
		"\2\2yz\3\2\2\2z\7\3\2\2\2{|\7\5\2\2|~\5L\'\2}\177\7E\2\2~}\3\2\2\2\177"+
		"\u0080\3\2\2\2\u0080~\3\2\2\2\u0080\u0081\3\2\2\2\u0081\t\3\2\2\2\u0082"+
		"\u0084\7G\2\2\u0083\u0082\3\2\2\2\u0084\u0085\3\2\2\2\u0085\u0083\3\2"+
		"\2\2\u0085\u0086\3\2\2\2\u0086\13\3\2\2\2\u0087\u0089\5\16\b\2\u0088\u008a"+
		"\5\30\r\2\u0089\u0088\3\2\2\2\u0089\u008a\3\2\2\2\u008a\r\3\2\2\2\u008b"+
		"\u008d\7\4\2\2\u008c\u008e\5\22\n\2\u008d\u008c\3\2\2\2\u008d\u008e\3"+
		"\2\2\2\u008e\u008f\3\2\2\2\u008f\u009b\7C\2\2\u0090\u0092\5R*\2\u0091"+
		"\u0093\5\22\n\2\u0092\u0091\3\2\2\2\u0092\u0093\3\2\2\2\u0093\u0095\3"+
		"\2\2\2\u0094\u0096\7C\2\2\u0095\u0094\3\2\2\2\u0095\u0096\3\2\2\2\u0096"+
		"\u0098\3\2\2\2\u0097\u0099\5\20\t\2\u0098\u0097\3\2\2\2\u0098\u0099\3"+
		"\2\2\2\u0099\u009b\3\2\2\2\u009a\u008b\3\2\2\2\u009a\u0090\3\2\2\2\u009b"+
		"\u009c\3\2\2\2\u009c\u009e\5@!\2\u009d\u009f\5:\36\2\u009e\u009d\3\2\2"+
		"\2\u009e\u009f\3\2\2\2\u009f\17\3\2\2\2\u00a0\u00a1\7\17\2\2\u00a1\u00a2"+
		"\5N(\2\u00a2\21\3\2\2\2\u00a3\u00ac\7\33\2\2\u00a4\u00a9\5\24\13\2\u00a5"+
		"\u00a6\7$\2\2\u00a6\u00a8\5\24\13\2\u00a7\u00a5\3\2\2\2\u00a8\u00ab\3"+
		"\2\2\2\u00a9\u00a7\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa\u00ad\3\2\2\2\u00ab"+
		"\u00a9\3\2\2\2\u00ac\u00a4\3\2\2\2\u00ac\u00ad\3\2\2\2\u00ad\u00ae\3\2"+
		"\2\2\u00ae\u00af\7\34\2\2\u00af\23\3\2\2\2\u00b0\u00b1\7C\2\2\u00b1\u00b3"+
		"\7&\2\2\u00b2\u00b0\3\2\2\2\u00b2\u00b3\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4"+
		"\u00b5\5\26\f\2\u00b5\25\3\2\2\2\u00b6\u00b8\5N(\2\u00b7\u00b6\3\2\2\2"+
		"\u00b8\u00b9\3\2\2\2\u00b9\u00b7\3\2\2\2\u00b9\u00ba\3\2\2\2\u00ba\u00ee"+
		"\3\2\2\2\u00bb\u00bd\5,\27\2\u00bc\u00bb\3\2\2\2\u00bd\u00be\3\2\2\2\u00be"+
		"\u00bc\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf\u00ee\3\2\2\2\u00c0\u00c2\5\60"+
		"\31\2\u00c1\u00c0\3\2\2\2\u00c2\u00c3\3\2\2\2\u00c3\u00c1\3\2\2\2\u00c3"+
		"\u00c4\3\2\2\2\u00c4\u00ee\3\2\2\2\u00c5\u00c7\5.\30\2\u00c6\u00c5\3\2"+
		"\2\2\u00c7\u00c8\3\2\2\2\u00c8\u00c6\3\2\2\2\u00c8\u00c9\3\2\2\2\u00c9"+
		"\u00ee\3\2\2\2\u00ca\u00cc\58\35\2\u00cb\u00ca\3\2\2\2\u00cc\u00cd\3\2"+
		"\2\2\u00cd\u00cb\3\2\2\2\u00cd\u00ce\3\2\2\2\u00ce\u00ee\3\2\2\2\u00cf"+
		"\u00d1\5\62\32\2\u00d0\u00cf\3\2\2\2\u00d1\u00d2\3\2\2\2\u00d2\u00d0\3"+
		"\2\2\2\u00d2\u00d3\3\2\2\2\u00d3\u00d5\3\2\2\2\u00d4\u00d6\5<\37\2\u00d5"+
		"\u00d4\3\2\2\2\u00d5\u00d6\3\2\2\2\u00d6\u00ee\3\2\2\2\u00d7\u00d9\5\64"+
		"\33\2\u00d8\u00d7\3\2\2\2\u00d9\u00da\3\2\2\2\u00da\u00d8\3\2\2\2\u00da"+
		"\u00db\3\2\2\2\u00db\u00dd\3\2\2\2\u00dc\u00de\5<\37\2\u00dd\u00dc\3\2"+
		"\2\2\u00dd\u00de\3\2\2\2\u00de\u00ee\3\2\2\2\u00df\u00e1\5\66\34\2\u00e0"+
		"\u00df\3\2\2\2\u00e1\u00e2\3\2\2\2\u00e2\u00e0\3\2\2\2\u00e2\u00e3\3\2"+
		"\2\2\u00e3\u00e5\3\2\2\2\u00e4\u00e6\5<\37\2\u00e5\u00e4\3\2\2\2\u00e5"+
		"\u00e6\3\2\2\2\u00e6\u00ee\3\2\2\2\u00e7\u00e9\5> \2\u00e8\u00e7\3\2\2"+
		"\2\u00e9\u00ea\3\2\2\2\u00ea\u00e8\3\2\2\2\u00ea\u00eb\3\2\2\2\u00eb\u00ee"+
		"\3\2\2\2\u00ec\u00ee\7\66\2\2\u00ed\u00b7\3\2\2\2\u00ed\u00bc\3\2\2\2"+
		"\u00ed\u00c1\3\2\2\2\u00ed\u00c6\3\2\2\2\u00ed\u00cb\3\2\2\2\u00ed\u00d0"+
		"\3\2\2\2\u00ed\u00d8\3\2\2\2\u00ed\u00e0\3\2\2\2\u00ed\u00e8\3\2\2\2\u00ed"+
		"\u00ec\3\2\2\2\u00ee\27\3\2\2\2\u00ef\u00fe\7J\2\2\u00f0\u00f8\5\"\22"+
		"\2\u00f1\u00f8\5\f\7\2\u00f2\u00f8\5J&\2\u00f3\u00f8\5\32\16\2\u00f4\u00f8"+
		"\5\34\17\2\u00f5\u00f8\5\36\20\2\u00f6\u00f8\5\n\6\2\u00f7\u00f0\3\2\2"+
		"\2\u00f7\u00f1\3\2\2\2\u00f7\u00f2\3\2\2\2\u00f7\u00f3\3\2\2\2\u00f7\u00f4"+
		"\3\2\2\2\u00f7\u00f5\3\2\2\2\u00f7\u00f6\3\2\2\2\u00f8\u00fa\3\2\2\2\u00f9"+
		"\u00fb\7E\2\2\u00fa\u00f9\3\2\2\2\u00fb\u00fc\3\2\2\2\u00fc\u00fa\3\2"+
		"\2\2\u00fc\u00fd\3\2\2\2\u00fd\u00ff\3\2\2\2\u00fe\u00f7\3\2\2\2\u00ff"+
		"\u0100\3\2\2\2\u0100\u00fe\3\2\2\2\u0100\u0101\3\2\2\2\u0101\u0102\3\2"+
		"\2\2\u0102\u0103\7K\2\2\u0103\31\3\2\2\2\u0104\u0105\7\b\2\2\u0105\u0107"+
		"\5R*\2\u0106\u0108\5\22\n\2\u0107\u0106\3\2\2\2\u0107\u0108\3\2\2\2\u0108"+
		"\u010a\3\2\2\2\u0109\u010b\5 \21\2\u010a\u0109\3\2\2\2\u010a\u010b\3\2"+
		"\2\2\u010b\u010d\3\2\2\2\u010c\u010e\5\30\r\2\u010d\u010c\3\2\2\2\u010d"+
		"\u010e\3\2\2\2\u010e\33\3\2\2\2\u010f\u0112\7\n\2\2\u0110\u0113\5N(\2"+
		"\u0111\u0113\7\16\2\2\u0112\u0110\3\2\2\2\u0112\u0111\3\2\2\2\u0113\u0115"+
		"\3\2\2\2\u0114\u0116\5 \21\2\u0115\u0114\3\2\2\2\u0115\u0116\3\2\2\2\u0116"+
		"\u0118\3\2\2\2\u0117\u0119\5\30\r\2\u0118\u0117\3\2\2\2\u0118\u0119\3"+
		"\2\2\2\u0119\35\3\2\2\2\u011a\u011b\7\t\2\2\u011b\u011c\5N(\2\u011c\u011d"+
		"\5@!\2\u011d\37\3\2\2\2\u011e\u011f\7\r\2\2\u011f\u0124\5N(\2\u0120\u0121"+
		"\7$\2\2\u0121\u0123\5N(\2\u0122\u0120\3\2\2\2\u0123\u0126\3\2\2\2\u0124"+
		"\u0122\3\2\2\2\u0124\u0125\3\2\2\2\u0125!\3\2\2\2\u0126\u0124\3\2\2\2"+
		"\u0127\u0128\7\7\2\2\u0128\u012a\5$\23\2\u0129\u012b\5&\24\2\u012a\u0129"+
		"\3\2\2\2\u012a\u012b\3\2\2\2\u012b\u012e\3\2\2\2\u012c\u012f\7\37\2\2"+
		"\u012d\u012f\5*\26\2\u012e\u012c\3\2\2\2\u012e\u012d\3\2\2\2\u012e\u012f"+
		"\3\2\2\2\u012f\u0130\3\2\2\2\u0130\u0136\7C\2\2\u0131\u0132\7&\2\2\u0132"+
		"\u0134\5\26\f\2\u0133\u0135\7D\2\2\u0134\u0133\3\2\2\2\u0134\u0135\3\2"+
		"\2\2\u0135\u0137\3\2\2\2\u0136\u0131\3\2\2\2\u0136\u0137\3\2\2\2\u0137"+
		"\u0139\3\2\2\2\u0138\u013a\5F$\2\u0139\u0138\3\2\2\2\u0139\u013a\3\2\2"+
		"\2\u013a#\3\2\2\2\u013b\u014a\7-\2\2\u013c\u014a\7+\2\2\u013d\u014a\7"+
		"\61\2\2\u013e\u014a\7,\2\2\u013f\u014a\7\60\2\2\u0140\u014a\7\64\2\2\u0141"+
		"\u014a\7\65\2\2\u0142\u014a\7.\2\2\u0143\u014a\7\63\2\2\u0144\u014a\7"+
		"/\2\2\u0145\u014a\7\62\2\2\u0146\u014a\7)\2\2\u0147\u014a\7*\2\2\u0148"+
		"\u014a\5N(\2\u0149\u013b\3\2\2\2\u0149\u013c\3\2\2\2\u0149\u013d\3\2\2"+
		"\2\u0149\u013e\3\2\2\2\u0149\u013f\3\2\2\2\u0149\u0140\3\2\2\2\u0149\u0141"+
		"\3\2\2\2\u0149\u0142\3\2\2\2\u0149\u0143\3\2\2\2\u0149\u0144\3\2\2\2\u0149"+
		"\u0145\3\2\2\2\u0149\u0146\3\2\2\2\u0149\u0147\3\2\2\2\u0149\u0148\3\2"+
		"\2\2\u014a%\3\2\2\2\u014b\u014c\7#\2\2\u014c\u014d\5(\25\2\u014d\'\3\2"+
		"\2\2\u014e\u0150\7\35\2\2\u014f\u0151\t\2\2\2\u0150\u014f\3\2\2\2\u0151"+
		"\u0152\3\2\2\2\u0152\u0150\3\2\2\2\u0152\u0153\3\2\2\2\u0153\u0154\3\2"+
		"\2\2\u0154\u0157\7\36\2\2\u0155\u0157\t\2\2\2\u0156\u014e\3\2\2\2\u0156"+
		"\u0155\3\2\2\2\u0157)\3\2\2\2\u0158\u0159\7\35\2\2\u0159\u015a\7;\2\2"+
		"\u015a\u015b\7\36\2\2\u015b+\3\2\2\2\u015c\u015e\7E\2\2\u015d\u015c\3"+
		"\2\2\2\u015d\u015e\3\2\2\2\u015e\u015f\3\2\2\2\u015f\u0163\7Y\2\2\u0160"+
		"\u0162\7R\2\2\u0161\u0160\3\2\2\2\u0162\u0165\3\2\2\2\u0163\u0161\3\2"+
		"\2\2\u0163\u0164\3\2\2\2\u0164\u0166\3\2\2\2\u0165\u0163\3\2\2\2\u0166"+
		"\u0167\7Z\2\2\u0167-\3\2\2\2\u0168\u0169\7:\2\2\u0169/\3\2\2\2\u016a\u016b"+
		"\5,\27\2\u016b\u016c\7#\2\2\u016c\u016d\5\66\34\2\u016d\61\3\2\2\2\u016e"+
		"\u016f\7;\2\2\u016f\63\3\2\2\2\u0170\u0171\t\3\2\2\u0171\65\3\2\2\2\u0172"+
		"\u0174\t\4\2\2\u0173\u0175\79\2\2\u0174\u0173\3\2\2\2\u0174\u0175\3\2"+
		"\2\2\u0175\67\3\2\2\2\u0176\u0179\5:\36\2\u0177\u0179\5N(\2\u0178\u0176"+
		"\3\2\2\2\u0178\u0177\3\2\2\2\u01799\3\2\2\2\u017a\u017b\7B\2\2\u017b;"+
		"\3\2\2\2\u017c\u017d\t\2\2\2\u017d=\3\2\2\2\u017e\u0180\7E\2\2\u017f\u017e"+
		"\3\2\2\2\u017f\u0180\3\2\2\2\u0180\u0181\3\2\2\2\u0181\u0185\7[\2\2\u0182"+
		"\u0184\7R\2\2\u0183\u0182\3\2\2\2\u0184\u0187\3\2\2\2\u0185\u0183\3\2"+
		"\2\2\u0185\u0186\3\2\2\2\u0186\u0188\3\2\2\2\u0187\u0185\3\2\2\2\u0188"+
		"\u0189\7\\\2\2\u0189?\3\2\2\2\u018a\u018c\5F$\2\u018b\u018a\3\2\2\2\u018b"+
		"\u018c\3\2\2\2\u018c\u018e\3\2\2\2\u018d\u018f\5B\"\2\u018e\u018d\3\2"+
		"\2\2\u018e\u018f\3\2\2\2\u018fA\3\2\2\2\u0190\u0192\7\f\2\2\u0191\u0193"+
		"\5D#\2\u0192\u0191\3\2\2\2\u0193\u0194\3\2\2\2\u0194\u0192\3\2\2\2\u0194"+
		"\u0195\3\2\2\2\u0195C\3\2\2\2\u0196\u0197\t\5\2\2\u0197E\3\2\2\2\u0198"+
		"\u019a\7\13\2\2\u0199\u019b\5H%\2\u019a\u0199\3\2\2\2\u019b\u019c\3\2"+
		"\2\2\u019c\u019a\3\2\2\2\u019c\u019d\3\2\2\2\u019dG\3\2\2\2\u019e\u019f"+
		"\t\6\2\2\u019fI\3\2\2\2\u01a0\u01a1\7C\2\2\u01a1\u01a2\7&\2\2\u01a2\u01a3"+
		"\5\26\f\2\u01a3K\3\2\2\2\u01a4\u01a6\5P)\2\u01a5\u01a4\3\2\2\2\u01a6\u01a9"+
		"\3\2\2\2\u01a7\u01a5\3\2\2\2\u01a7\u01a8\3\2\2\2\u01a8\u01aa\3\2\2\2\u01a9"+
		"\u01a7\3\2\2\2\u01aa\u01ab\7C\2\2\u01abM\3\2\2\2\u01ac\u01ae\5P)\2\u01ad"+
		"\u01ac\3\2\2\2\u01ae\u01b1\3\2\2\2\u01af\u01ad\3\2\2\2\u01af\u01b0\3\2"+
		"\2\2\u01b0\u01b2\3\2\2\2\u01b1\u01af\3\2\2\2\u01b2\u01b3\7C\2\2\u01b3"+
		"O\3\2\2\2\u01b4\u01b5\7C\2\2\u01b5\u01b6\7%\2\2\u01b6Q\3\2\2\2\u01b7\u01b8"+
		"\t\7\2\2\u01b8S\3\2\2\2@W[^disy\u0080\u0085\u0089\u008d\u0092\u0095\u0098"+
		"\u009a\u009e\u00a9\u00ac\u00b2\u00b9\u00be\u00c3\u00c8\u00cd\u00d2\u00d5"+
		"\u00da\u00dd\u00e2\u00e5\u00ea\u00ed\u00f7\u00fc\u0100\u0107\u010a\u010d"+
		"\u0112\u0115\u0118\u0124\u012a\u012e\u0134\u0136\u0139\u0149\u0152\u0156"+
		"\u015d\u0163\u0174\u0178\u017f\u0185\u018b\u018e\u0194\u019c\u01a7\u01af";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}