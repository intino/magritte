// Generated from /home/bycor/Projects/Tara/src/AntlrM2/src/TaraM2Grammar.g4 by ANTLR 4.x

package monet.tara.compiler.parser.antlr;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TaraM2Grammar extends Parser {
	public static final int
		RIGHT_SQUARE = 17, NEGATIVE_VALUE = 37, LETTER = 42, DOC = 45, NEW = 14, CLOSE_AN = 21,
		DEDENT = 49, SINGLETON = 13, ABSTRACT = 5, BOOLEAN_VALUE = 35, MULTIPLE = 6, UNKNOWN_TOKEN = 50,
		WORD = 10, OPEN_BRACKET = 18, AS = 3, FINAL = 4, COMMA = 22, IDENTIFIER = 40, VAR = 11,
		DIGIT = 41, NL = 47, DOT = 23, STRING_VALUE = 39, DOUBLE_VALUE = 38, POSITIVE = 27,
		NATURAL_TYPE = 31, NEGATIVE = 28, DOUBLE_TYPE = 32, BOOLEAN_TYPE = 34, SEMICOLON = 26,
		INT_TYPE = 30, LIST = 15, ROOT = 12, HAS_CODE = 8, OPEN_AN = 20, CONCEPT = 1, OPTIONAL = 7,
		EXTENSIBLE = 9, POSITIVE_VALUE = 36, NEWLINE = 43, SPACES = 44, SP = 46, STRING_TYPE = 33,
		ASSIGN = 24, FROM = 2, UID_TYPE = 29, LEFT_SQUARE = 16, DOUBLE_COMMAS = 25, NEWLINE_INDENT = 48,
		CLOSE_BRACKET = 19;
	public static final String[] tokenNames = {
		"<INVALID>", "'Concept'", "'from'", "'as'", "'final'", "'abstract'", "'multiple'",
		"'optional'", "'has-code'", "'extensible'", "'Word'", "'var'", "'root'",
		"'singleton'", "'new'", "LIST", "'['", "']'", "'{'", "'}'", "'<'", "'>'",
		"','", "'.'", "':'", "'\"'", "SEMICOLON", "'+'", "'-'", "'Uid'", "'Int'",
		"'Natural'", "'Double'", "'String'", "'Boolean'", "BOOLEAN_VALUE", "POSITIVE_VALUE",
		"NEGATIVE_VALUE", "DOUBLE_VALUE", "STRING_VALUE", "IDENTIFIER", "DIGIT",
		"LETTER", "NEWLINE", "SPACES", "DOC", "SP", "NL", "'indent'", "'dedent'",
		"UNKNOWN_TOKEN"
	};
	public static final int
		RULE_root = 0, RULE_extendedConcept = 1, RULE_conceptSignature = 2, RULE_concept = 3,
		RULE_conceptBody = 4, RULE_conceptConstituent = 5, RULE_component = 6,
		RULE_from = 7, RULE_fromBody = 8, RULE_fromComponent = 9, RULE_attribute = 10,
		RULE_reference = 11, RULE_word = 12, RULE_stringAssign = 13, RULE_booleanAssign = 14,
		RULE_integerAssign = 15, RULE_doubleAssign = 16, RULE_naturalAssign = 17,
		RULE_stringListAssign = 18, RULE_booleanListAssign = 19, RULE_integerListAssign = 20,
		RULE_doubleListAssign = 21, RULE_naturalListAssign = 22, RULE_conceptAnnotations = 23,
		RULE_componentAnnotations = 24, RULE_fromAnnotations = 25, RULE_fromComponentAnnotations = 26,
		RULE_modifier = 27, RULE_doc = 28;
	public static final String[] ruleNames = {
		"root", "extendedConcept", "conceptSignature", "concept", "conceptBody",
		"conceptConstituent", "component", "from", "fromBody", "fromComponent",
		"attribute", "reference", "word", "stringAssign", "booleanAssign", "integerAssign",
		"doubleAssign", "naturalAssign", "stringListAssign", "booleanListAssign",
		"integerListAssign", "doubleListAssign", "naturalListAssign", "conceptAnnotations",
		"componentAnnotations", "fromAnnotations", "fromComponentAnnotations",
		"modifier", "doc"
	};
	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\64\u017e\4\2\t\2" +
			"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13" +
			"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22" +
			"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31" +
			"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\3\2\3\2\7\2?\n\2\f" +
			"\2\16\2B\13\2\3\2\3\2\3\3\3\3\3\3\7\3I\n\3\f\3\16\3L\13\3\3\4\3\4\5\4" +
			"P\n\4\3\4\5\4S\n\4\3\4\3\4\3\4\3\5\5\5Y\n\5\3\5\3\5\5\5]\n\5\3\5\5\5`" +
			"\n\5\3\6\3\6\3\6\6\6e\n\6\r\6\16\6f\6\6i\n\6\r\6\16\6j\3\6\3\6\3\7\3\7" +
			"\3\7\3\7\3\7\5\7t\n\7\3\b\5\bw\n\b\3\b\3\b\5\b{\n\b\3\b\5\b~\n\b\3\b\3" +
			"\b\3\b\5\b\u0083\n\b\5\b\u0085\n\b\3\t\3\t\5\t\u0089\n\t\3\t\3\t\3\n\3" +
			"\n\3\n\6\n\u0090\n\n\r\n\16\n\u0091\6\n\u0094\n\n\r\n\16\n\u0095\3\n\3" +
			"\n\3\13\5\13\u009b\n\13\3\13\3\13\5\13\u009f\n\13\3\13\5\13\u00a2\n\13" +
			"\3\13\3\13\3\13\5\13\u00a7\n\13\5\13\u00a9\n\13\3\f\3\f\3\f\3\f\5\f\u00af" +
			"\n\f\3\f\3\f\3\f\3\f\5\f\u00b5\n\f\3\f\3\f\3\f\5\f\u00ba\n\f\5\f\u00bc" +
			"\n\f\3\f\3\f\3\f\3\f\5\f\u00c2\n\f\3\f\3\f\3\f\5\f\u00c7\n\f\5\f\u00c9" +
			"\n\f\3\f\3\f\3\f\3\f\5\f\u00cf\n\f\3\f\3\f\3\f\5\f\u00d4\n\f\5\f\u00d6" +
			"\n\f\3\f\3\f\3\f\3\f\5\f\u00dc\n\f\3\f\3\f\3\f\5\f\u00e1\n\f\5\f\u00e3" +
			"\n\f\3\f\3\f\3\f\3\f\5\f\u00e9\n\f\3\f\3\f\3\f\5\f\u00ee\n\f\5\f\u00f0" +
			"\n\f\5\f\u00f2\n\f\3\r\3\r\3\r\3\r\7\r\u00f8\n\r\f\r\16\r\u00fb\13\r\3" +
			"\r\3\r\3\r\3\r\3\r\7\r\u0102\n\r\f\r\16\r\u0105\13\r\3\r\3\r\5\r\u0109" +
			"\n\r\3\16\3\16\3\16\3\16\3\16\3\16\6\16\u0111\n\16\r\16\16\16\u0112\6" +
			"\16\u0115\n\16\r\16\16\16\u0116\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3\20" +
			"\3\21\3\21\3\21\3\22\3\22\3\22\3\23\3\23\3\23\3\24\3\24\3\24\6\24\u012d" +
			"\n\24\r\24\16\24\u012e\3\24\3\24\3\25\3\25\3\25\6\25\u0136\n\25\r\25\16" +
			"\25\u0137\3\25\3\25\3\26\3\26\3\26\6\26\u013f\n\26\r\26\16\26\u0140\3" +
			"\26\3\26\3\27\3\27\3\27\6\27\u0148\n\27\r\27\16\27\u0149\3\27\3\27\3\30" +
			"\3\30\3\30\6\30\u0151\n\30\r\30\16\30\u0152\3\30\3\30\3\31\3\31\6\31\u0159" +
			"\n\31\r\31\16\31\u015a\3\31\3\31\3\32\3\32\6\32\u0161\n\32\r\32\16\32" +
			"\u0162\3\32\3\32\3\33\3\33\6\33\u0169\n\33\r\33\16\33\u016a\3\33\3\33" +
			"\3\34\3\34\6\34\u0171\n\34\r\34\16\34\u0172\3\34\3\34\3\35\3\35\3\36\6" +
			"\36\u017a\n\36\r\36\16\36\u017b\3\36\2\2\37\2\4\6\b\n\f\16\20\22\24\26" +
			"\30\32\34\36 \"$&(*,.\60\62\64\668:\2\t\3\2&\'\3\2&(\4\2\n\13\16\17\4" +
			"\2\b\13\17\17\3\2\b\t\4\2\n\13\17\17\3\2\6\7\u019f\2@\3\2\2\2\4E\3\2\2" +
			"\2\6M\3\2\2\2\bX\3\2\2\2\na\3\2\2\2\fs\3\2\2\2\16\u0084\3\2\2\2\20\u0086" +
			"\3\2\2\2\22\u008c\3\2\2\2\24\u00a8\3\2\2\2\26\u00f1\3\2\2\2\30\u0108\3" +
			"\2\2\2\32\u010a\3\2\2\2\34\u011a\3\2\2\2\36\u011d\3\2\2\2 \u0120\3\2\2" +
			"\2\"\u0123\3\2\2\2$\u0126\3\2\2\2&\u0129\3\2\2\2(\u0132\3\2\2\2*\u013b" +
			"\3\2\2\2,\u0144\3\2\2\2.\u014d\3\2\2\2\60\u0156\3\2\2\2\62\u015e\3\2\2" +
			"\2\64\u0166\3\2\2\2\66\u016e\3\2\2\28\u0176\3\2\2\2:\u0179\3\2\2\2<?\5" +
			"\b\5\2=?\7-\2\2><\3\2\2\2>=\3\2\2\2?B\3\2\2\2@>\3\2\2\2@A\3\2\2\2AC\3" +
			"\2\2\2B@\3\2\2\2CD\7\2\2\3D\3\3\2\2\2EJ\7*\2\2FG\7\31\2\2GI\7*\2\2HF\3" +
			"\2\2\2IL\3\2\2\2JH\3\2\2\2JK\3\2\2\2K\5\3\2\2\2LJ\3\2\2\2MO\7\3\2\2NP" +
			"\5\4\3\2ON\3\2\2\2OP\3\2\2\2PR\3\2\2\2QS\58\35\2RQ\3\2\2\2RS\3\2\2\2S" +
			"T\3\2\2\2TU\7\5\2\2UV\7*\2\2V\7\3\2\2\2WY\5:\36\2XW\3\2\2\2XY\3\2\2\2" +
			"YZ\3\2\2\2Z\\\5\6\4\2[]\5\60\31\2\\[\3\2\2\2\\]\3\2\2\2]_\3\2\2\2^`\5" +
			"\n\6\2_^\3\2\2\2_`\3\2\2\2`\t\3\2\2\2ah\7\62\2\2bd\5\f\7\2ce\7-\2\2dc" +
			"\3\2\2\2ef\3\2\2\2fd\3\2\2\2fg\3\2\2\2gi\3\2\2\2hb\3\2\2\2ij\3\2\2\2j" +
			"h\3\2\2\2jk\3\2\2\2kl\3\2\2\2lm\7\63\2\2m\13\3\2\2\2nt\5\26\f\2ot\5\30" +
			"\r\2pt\5\32\16\2qt\5\20\t\2rt\5\16\b\2sn\3\2\2\2so\3\2\2\2sp\3\2\2\2s" +
			"q\3\2\2\2sr\3\2\2\2t\r\3\2\2\2uw\5:\36\2vu\3\2\2\2vw\3\2\2\2wx\3\2\2\2" +
			"xz\5\6\4\2y{\5\62\32\2zy\3\2\2\2z{\3\2\2\2{}\3\2\2\2|~\5\n\6\2}|\3\2\2" +
			"\2}~\3\2\2\2~\u0085\3\2\2\2\177\u0080\7\20\2\2\u0080\u0082\5\4\3\2\u0081" +
			"\u0083\5\62\32\2\u0082\u0081\3\2\2\2\u0082\u0083\3\2\2\2\u0083\u0085\3" +
			"\2\2\2\u0084v\3\2\2\2\u0084\177\3\2\2\2\u0085\17\3\2\2\2\u0086\u0088\7" +
			"\4\2\2\u0087\u0089\5\64\33\2\u0088\u0087\3\2\2\2\u0088\u0089\3\2\2\2\u0089" +
			"\u008a\3\2\2\2\u008a\u008b\5\22\n\2\u008b\21\3\2\2\2\u008c\u0093\7\62" +
			"\2\2\u008d\u008f\5\24\13\2\u008e\u0090\7-\2\2\u008f\u008e\3\2\2\2\u0090" +
			"\u0091\3\2\2\2\u0091\u008f\3\2\2\2\u0091\u0092\3\2\2\2\u0092\u0094\3\2" +
			"\2\2\u0093\u008d\3\2\2\2\u0094\u0095\3\2\2\2\u0095\u0093\3\2\2\2\u0095" +
			"\u0096\3\2\2\2\u0096\u0097\3\2\2\2\u0097\u0098\7\63\2\2\u0098\23\3\2\2" +
			"\2\u0099\u009b\5:\36\2\u009a\u0099\3\2\2\2\u009a\u009b\3\2\2\2\u009b\u009c" +
			"\3\2\2\2\u009c\u009e\5\6\4\2\u009d\u009f\5\66\34\2\u009e\u009d\3\2\2\2" +
			"\u009e\u009f\3\2\2\2\u009f\u00a1\3\2\2\2\u00a0\u00a2\5\n\6\2\u00a1\u00a0" +
			"\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2\u00a9\3\2\2\2\u00a3\u00a4\7\20\2\2" +
			"\u00a4\u00a6\5\4\3\2\u00a5\u00a7\5\66\34\2\u00a6\u00a5\3\2\2\2\u00a6\u00a7" +
			"\3\2\2\2\u00a7\u00a9\3\2\2\2\u00a8\u009a\3\2\2\2\u00a8\u00a3\3\2\2\2\u00a9" +
			"\25\3\2\2\2\u00aa\u00ab\7\r\2\2\u00ab\u00ac\7\37\2\2\u00ac\u00ae\7*\2" +
			"\2\u00ad\u00af\5\34\17\2\u00ae\u00ad\3\2\2\2\u00ae\u00af\3\2\2\2\u00af" +
			"\u00f2\3\2\2\2\u00b0\u00b1\7\r\2\2\u00b1\u00bb\7 \2\2\u00b2\u00b4\7*\2" +
			"\2\u00b3\u00b5\5 \21\2\u00b4\u00b3\3\2\2\2\u00b4\u00b5\3\2\2\2\u00b5\u00bc" +
			"\3\2\2\2\u00b6\u00b7\7\21\2\2\u00b7\u00b9\7*\2\2\u00b8\u00ba\5*\26\2\u00b9" +
			"\u00b8\3\2\2\2\u00b9\u00ba\3\2\2\2\u00ba\u00bc\3\2\2\2\u00bb\u00b2\3\2" +
			"\2\2\u00bb\u00b6\3\2\2\2\u00bc\u00f2\3\2\2\2\u00bd\u00be\7\r\2\2\u00be" +
			"\u00c8\7\"\2\2\u00bf\u00c1\7*\2\2\u00c0\u00c2\5\"\22\2\u00c1\u00c0\3\2" +
			"\2\2\u00c1\u00c2\3\2\2\2\u00c2\u00c9\3\2\2\2\u00c3\u00c4\7\21\2\2\u00c4" +
			"\u00c6\7*\2\2\u00c5\u00c7\5,\27\2\u00c6\u00c5\3\2\2\2\u00c6\u00c7\3\2" +
			"\2\2\u00c7\u00c9\3\2\2\2\u00c8\u00bf\3\2\2\2\u00c8\u00c3\3\2\2\2\u00c9" +
			"\u00f2\3\2\2\2\u00ca\u00cb\7\r\2\2\u00cb\u00d5\7!\2\2\u00cc\u00ce\7*\2" +
			"\2\u00cd\u00cf\5$\23\2\u00ce\u00cd\3\2\2\2\u00ce\u00cf\3\2\2\2\u00cf\u00d6" +
			"\3\2\2\2\u00d0\u00d1\7\21\2\2\u00d1\u00d3\7*\2\2\u00d2\u00d4\5.\30\2\u00d3" +
			"\u00d2\3\2\2\2\u00d3\u00d4\3\2\2\2\u00d4\u00d6\3\2\2\2\u00d5\u00cc\3\2" +
			"\2\2\u00d5\u00d0\3\2\2\2\u00d6\u00f2\3\2\2\2\u00d7\u00d8\7\r\2\2\u00d8" +
			"\u00e2\7$\2\2\u00d9\u00db\7*\2\2\u00da\u00dc\5\36\20\2\u00db\u00da\3\2" +
			"\2\2\u00db\u00dc\3\2\2\2\u00dc\u00e3\3\2\2\2\u00dd\u00de\7\21\2\2\u00de" +
			"\u00e0\7*\2\2\u00df\u00e1\5(\25\2\u00e0\u00df\3\2\2\2\u00e0\u00e1\3\2" +
			"\2\2\u00e1\u00e3\3\2\2\2\u00e2\u00d9\3\2\2\2\u00e2\u00dd\3\2\2\2\u00e3" +
			"\u00f2\3\2\2\2\u00e4\u00e5\7\r\2\2\u00e5\u00ef\7#\2\2\u00e6\u00e8\7*\2" +
			"\2\u00e7\u00e9\5\34\17\2\u00e8\u00e7\3\2\2\2\u00e8\u00e9\3\2\2\2\u00e9" +
			"\u00f0\3\2\2\2\u00ea\u00eb\7\21\2\2\u00eb\u00ed\7*\2\2\u00ec\u00ee\5&" +
			"\24\2\u00ed\u00ec\3\2\2\2\u00ed\u00ee\3\2\2\2\u00ee\u00f0\3\2\2\2\u00ef" +
			"\u00e6\3\2\2\2\u00ef\u00ea\3\2\2\2\u00f0\u00f2\3\2\2\2\u00f1\u00aa\3\2" +
			"\2\2\u00f1\u00b0\3\2\2\2\u00f1\u00bd\3\2\2\2\u00f1\u00ca\3\2\2\2\u00f1" +
			"\u00d7\3\2\2\2\u00f1\u00e4\3\2\2\2\u00f2\27\3\2\2\2\u00f3\u00f4\7\r\2" +
			"\2\u00f4\u00f9\7*\2\2\u00f5\u00f6\7\31\2\2\u00f6\u00f8\7*\2\2\u00f7\u00f5" +
			"\3\2\2\2\u00f8\u00fb\3\2\2\2\u00f9\u00f7\3\2\2\2\u00f9\u00fa\3\2\2\2\u00fa" +
			"\u00fc\3\2\2\2\u00fb\u00f9\3\2\2\2\u00fc\u0109\7*\2\2\u00fd\u00fe\7\r" +
			"\2\2\u00fe\u0103\7*\2\2\u00ff\u0100\7\31\2\2\u0100\u0102\7*\2\2\u0101" +
			"\u00ff\3\2\2\2\u0102\u0105\3\2\2\2\u0103\u0101\3\2\2\2\u0103\u0104\3\2" +
			"\2\2\u0104\u0106\3\2\2\2\u0105\u0103\3\2\2\2\u0106\u0107\7\21\2\2\u0107" +
			"\u0109\7*\2\2\u0108\u00f3\3\2\2\2\u0108\u00fd\3\2\2\2\u0109\31\3\2\2\2" +
			"\u010a\u010b\7\r\2\2\u010b\u010c\7\f\2\2\u010c\u010d\7*\2\2\u010d\u0114" +
			"\7\62\2\2\u010e\u0110\7*\2\2\u010f\u0111\7-\2\2\u0110\u010f\3\2\2\2\u0111" +
			"\u0112\3\2\2\2\u0112\u0110\3\2\2\2\u0112\u0113\3\2\2\2\u0113\u0115\3\2" +
			"\2\2\u0114\u010e\3\2\2\2\u0115\u0116\3\2\2\2\u0116\u0114\3\2\2\2\u0116" +
			"\u0117\3\2\2\2\u0117\u0118\3\2\2\2\u0118\u0119\7\63\2\2\u0119\33\3\2\2" +
			"\2\u011a\u011b\7\32\2\2\u011b\u011c\7)\2\2\u011c\35\3\2\2\2\u011d\u011e" +
			"\7\32\2\2\u011e\u011f\7%\2\2\u011f\37\3\2\2\2\u0120\u0121\7\32\2\2\u0121" +
			"\u0122\t\2\2\2\u0122!\3\2\2\2\u0123\u0124\7\32\2\2\u0124\u0125\t\3\2\2" +
			"\u0125#\3\2\2\2\u0126\u0127\7\32\2\2\u0127\u0128\7&\2\2\u0128%\3\2\2\2" +
			"\u0129\u012a\7\32\2\2\u012a\u012c\7\22\2\2\u012b\u012d\7)\2\2\u012c\u012b" +
			"\3\2\2\2\u012d\u012e\3\2\2\2\u012e\u012c\3\2\2\2\u012e\u012f\3\2\2\2\u012f" +
			"\u0130\3\2\2\2\u0130\u0131\7\23\2\2\u0131\'\3\2\2\2\u0132\u0133\7\32\2" +
			"\2\u0133\u0135\7\22\2\2\u0134\u0136\7%\2\2\u0135\u0134\3\2\2\2\u0136\u0137" +
			"\3\2\2\2\u0137\u0135\3\2\2\2\u0137\u0138\3\2\2\2\u0138\u0139\3\2\2\2\u0139" +
			"\u013a\7\23\2\2\u013a)\3\2\2\2\u013b\u013c\7\32\2\2\u013c\u013e\7\22\2" +
			"\2\u013d\u013f\t\2\2\2\u013e\u013d\3\2\2\2\u013f\u0140\3\2\2\2\u0140\u013e" +
			"\3\2\2\2\u0140\u0141\3\2\2\2\u0141\u0142\3\2\2\2\u0142\u0143\7\23\2\2" +
			"\u0143+\3\2\2\2\u0144\u0145\7\32\2\2\u0145\u0147\7\22\2\2\u0146\u0148" +
			"\t\3\2\2\u0147\u0146\3\2\2\2\u0148\u0149\3\2\2\2\u0149\u0147\3\2\2\2\u0149" +
			"\u014a\3\2\2\2\u014a\u014b\3\2\2\2\u014b\u014c\7\23\2\2\u014c-\3\2\2\2" +
			"\u014d\u014e\7\32\2\2\u014e\u0150\7\22\2\2\u014f\u0151\7&\2\2\u0150\u014f" +
			"\3\2\2\2\u0151\u0152\3\2\2\2\u0152\u0150\3\2\2\2\u0152\u0153\3\2\2\2\u0153" +
			"\u0154\3\2\2\2\u0154\u0155\7\23\2\2\u0155/\3\2\2\2\u0156\u0158\7\26\2" +
			"\2\u0157\u0159\t\4\2\2\u0158\u0157\3\2\2\2\u0159\u015a\3\2\2\2\u015a\u0158" +
			"\3\2\2\2\u015a\u015b\3\2\2\2\u015b\u015c\3\2\2\2\u015c\u015d\7\27\2\2" +
			"\u015d\61\3\2\2\2\u015e\u0160\7\26\2\2\u015f\u0161\t\5\2\2\u0160\u015f" +
			"\3\2\2\2\u0161\u0162\3\2\2\2\u0162\u0160\3\2\2\2\u0162\u0163\3\2\2\2\u0163" +
			"\u0164\3\2\2\2\u0164\u0165\7\27\2\2\u0165\63\3\2\2\2\u0166\u0168\7\26" +
			"\2\2\u0167\u0169\t\6\2\2\u0168\u0167\3\2\2\2\u0169\u016a\3\2\2\2\u016a" +
			"\u0168\3\2\2\2\u016a\u016b\3\2\2\2\u016b\u016c\3\2\2\2\u016c\u016d\7\27" +
			"\2\2\u016d\65\3\2\2\2\u016e\u0170\7\26\2\2\u016f\u0171\t\7\2\2\u0170\u016f" +
			"\3\2\2\2\u0171\u0172\3\2\2\2\u0172\u0170\3\2\2\2\u0172\u0173\3\2\2\2\u0173" +
			"\u0174\3\2\2\2\u0174\u0175\7\27\2\2\u0175\67\3\2\2\2\u0176\u0177\t\b\2" +
			"\2\u01779\3\2\2\2\u0178\u017a\7/\2\2\u0179\u0178\3\2\2\2\u017a\u017b\3" +
			"\2\2\2\u017b\u0179\3\2\2\2\u017b\u017c\3\2\2\2\u017c;\3\2\2\2:>@JORX\\" +
			"_fjsvz}\u0082\u0084\u0088\u0091\u0095\u009a\u009e\u00a1\u00a6\u00a8\u00ae" +
			"\u00b4\u00b9\u00bb\u00c1\u00c6\u00c8\u00ce\u00d3\u00d5\u00db\u00e0\u00e2" +
			"\u00e8\u00ed\u00ef\u00f1\u00f9\u0103\u0108\u0112\u0116\u012e\u0137\u0140" +
			"\u0149\u0152\u015a\u0162\u016a\u0172\u017b";


	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();

	public TaraM2Grammar(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this, null, null, _sharedContextCache);
	}

	@Override
	public String getGrammarFileName() {
		return "TaraM2Grammar.g4";
	}

	@Override
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override
	public String[] getRuleNames() {
		return ruleNames;
	}

	public String getSerializedATN() {
		return _serializedATN;
	}

	@Override
	public ATN getATN() {
		return null;
	}

	public final RootContext root() throws RecognitionException {
		RootContext _localctx = new RootContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_root);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(62);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << CONCEPT) | (1L << NEWLINE) | (1L << DOC))) != 0)) {
					{
						setState(60);
						switch (_input.LA(1)) {
							case CONCEPT:
							case DOC: {
								setState(58);
								concept();
							}
							break;
							case NEWLINE: {
								setState(59);
								match(NEWLINE);
							}
							break;
							default:
								throw new NoViableAltException(this);
						}
					}
					setState(64);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(65);
				match(EOF);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final ExtendedConceptContext extendedConcept() throws RecognitionException {
		ExtendedConceptContext _localctx = new ExtendedConceptContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_extendedConcept);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(67);
				match(IDENTIFIER);
				setState(72);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == DOT) {
					{
						{
							setState(68);
							match(DOT);
							setState(69);
							match(IDENTIFIER);
						}
					}
					setState(74);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final ConceptSignatureContext conceptSignature() throws RecognitionException {
		ConceptSignatureContext _localctx = new ConceptSignatureContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_conceptSignature);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(75);
				match(CONCEPT);
				setState(77);
				_la = _input.LA(1);
				if (_la == IDENTIFIER) {
					{
						setState(76);
						extendedConcept();
					}
				}

				setState(80);
				_la = _input.LA(1);
				if (_la == FINAL || _la == ABSTRACT) {
					{
						setState(79);
						modifier();
					}
				}

				setState(82);
				match(AS);
				setState(83);
				match(IDENTIFIER);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final ConceptContext concept() throws RecognitionException {
		ConceptContext _localctx = new ConceptContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_concept);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(86);
				_la = _input.LA(1);
				if (_la == DOC) {
					{
						setState(85);
						doc();
					}
				}

				setState(88);
				conceptSignature();
				setState(90);
				_la = _input.LA(1);
				if (_la == OPEN_AN) {
					{
						setState(89);
						conceptAnnotations();
					}
				}

				setState(93);
				_la = _input.LA(1);
				if (_la == NEWLINE_INDENT) {
					{
						setState(92);
						conceptBody();
					}
				}

			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final ConceptBodyContext conceptBody() throws RecognitionException {
		ConceptBodyContext _localctx = new ConceptBodyContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_conceptBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(95);
				match(NEWLINE_INDENT);
				setState(102);
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
						{
							setState(96);
							conceptConstituent();
							setState(98);
							_errHandler.sync(this);
							_la = _input.LA(1);
							do {
								{
									{
										setState(97);
										match(NEWLINE);
									}
								}
								setState(100);
								_errHandler.sync(this);
								_la = _input.LA(1);
							} while (_la == NEWLINE);
						}
					}
					setState(104);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << CONCEPT) | (1L << FROM) | (1L << VAR) | (1L << NEW) | (1L << DOC))) != 0));
				setState(106);
				match(DEDENT);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final ConceptConstituentContext conceptConstituent() throws RecognitionException {
		ConceptConstituentContext _localctx = new ConceptConstituentContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_conceptConstituent);
		try {
			setState(113);
			switch (getInterpreter().adaptivePredict(_input, 10, _ctx)) {
				case 1:
					enterOuterAlt(_localctx, 1);
				{
					setState(108);
					attribute();
				}
				break;
				case 2:
					enterOuterAlt(_localctx, 2);
				{
					setState(109);
					reference();
				}
				break;
				case 3:
					enterOuterAlt(_localctx, 3);
				{
					setState(110);
					word();
				}
				break;
				case 4:
					enterOuterAlt(_localctx, 4);
				{
					setState(111);
					from();
				}
				break;
				case 5:
					enterOuterAlt(_localctx, 5);
				{
					setState(112);
					component();
				}
				break;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final ComponentContext component() throws RecognitionException {
		ComponentContext _localctx = new ComponentContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_component);
		int _la;
		try {
			setState(130);
			switch (_input.LA(1)) {
				case CONCEPT:
				case DOC:
					enterOuterAlt(_localctx, 1);
				{
					setState(116);
					_la = _input.LA(1);
					if (_la == DOC) {
						{
							setState(115);
							doc();
						}
					}

					setState(118);
					conceptSignature();
					setState(120);
					_la = _input.LA(1);
					if (_la == OPEN_AN) {
						{
							setState(119);
							componentAnnotations();
						}
					}

					setState(123);
					_la = _input.LA(1);
					if (_la == NEWLINE_INDENT) {
						{
							setState(122);
							conceptBody();
						}
					}

				}
				break;
				case NEW:
					enterOuterAlt(_localctx, 2);
				{
					setState(125);
					match(NEW);
					setState(126);
					extendedConcept();
					setState(128);
					_la = _input.LA(1);
					if (_la == OPEN_AN) {
						{
							setState(127);
							componentAnnotations();
						}
					}

				}
				break;
				default:
					throw new NoViableAltException(this);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final FromContext from() throws RecognitionException {
		FromContext _localctx = new FromContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_from);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(132);
				match(FROM);
				setState(134);
				_la = _input.LA(1);
				if (_la == OPEN_AN) {
					{
						setState(133);
						fromAnnotations();
					}
				}

				setState(136);
				fromBody();
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final FromBodyContext fromBody() throws RecognitionException {
		FromBodyContext _localctx = new FromBodyContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_fromBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(138);
				match(NEWLINE_INDENT);
				setState(145);
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
						{
							setState(139);
							fromComponent();
							setState(141);
							_errHandler.sync(this);
							_la = _input.LA(1);
							do {
								{
									{
										setState(140);
										match(NEWLINE);
									}
								}
								setState(143);
								_errHandler.sync(this);
								_la = _input.LA(1);
							} while (_la == NEWLINE);
						}
					}
					setState(147);
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << CONCEPT) | (1L << NEW) | (1L << DOC))) != 0));
				setState(149);
				match(DEDENT);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final FromComponentContext fromComponent() throws RecognitionException {
		FromComponentContext _localctx = new FromComponentContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_fromComponent);
		int _la;
		try {
			setState(166);
			switch (_input.LA(1)) {
				case CONCEPT:
				case DOC:
					enterOuterAlt(_localctx, 1);
				{
					setState(152);
					_la = _input.LA(1);
					if (_la == DOC) {
						{
							setState(151);
							doc();
						}
					}

					setState(154);
					conceptSignature();
					setState(156);
					_la = _input.LA(1);
					if (_la == OPEN_AN) {
						{
							setState(155);
							fromComponentAnnotations();
						}
					}

					setState(159);
					_la = _input.LA(1);
					if (_la == NEWLINE_INDENT) {
						{
							setState(158);
							conceptBody();
						}
					}

				}
				break;
				case NEW:
					enterOuterAlt(_localctx, 2);
				{
					setState(161);
					match(NEW);
					setState(162);
					extendedConcept();
					setState(164);
					_la = _input.LA(1);
					if (_la == OPEN_AN) {
						{
							setState(163);
							fromComponentAnnotations();
						}
					}

				}
				break;
				default:
					throw new NoViableAltException(this);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final AttributeContext attribute() throws RecognitionException {
		AttributeContext _localctx = new AttributeContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_attribute);
		int _la;
		try {
			setState(239);
			switch (getInterpreter().adaptivePredict(_input, 40, _ctx)) {
				case 1:
					enterOuterAlt(_localctx, 1);
				{
					setState(168);
					match(VAR);
					setState(169);
					match(UID_TYPE);
					setState(170);
					match(IDENTIFIER);
					setState(172);
					_la = _input.LA(1);
					if (_la == ASSIGN) {
						{
							setState(171);
							stringAssign();
						}
					}

				}
				break;
				case 2:
					enterOuterAlt(_localctx, 2);
				{
					setState(174);
					match(VAR);
					setState(175);
					match(INT_TYPE);
					setState(185);
					switch (_input.LA(1)) {
						case IDENTIFIER: {
							setState(176);
							match(IDENTIFIER);
							setState(178);
							_la = _input.LA(1);
							if (_la == ASSIGN) {
								{
									setState(177);
									integerAssign();
								}
							}

						}
						break;
						case LIST: {
							setState(180);
							match(LIST);
							setState(181);
							match(IDENTIFIER);
							setState(183);
							_la = _input.LA(1);
							if (_la == ASSIGN) {
								{
									setState(182);
									integerListAssign();
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
					setState(187);
					match(VAR);
					setState(188);
					match(DOUBLE_TYPE);
					setState(198);
					switch (_input.LA(1)) {
						case IDENTIFIER: {
							setState(189);
							match(IDENTIFIER);
							setState(191);
							_la = _input.LA(1);
							if (_la == ASSIGN) {
								{
									setState(190);
									doubleAssign();
								}
							}

						}
						break;
						case LIST: {
							setState(193);
							match(LIST);
							setState(194);
							match(IDENTIFIER);
							setState(196);
							_la = _input.LA(1);
							if (_la == ASSIGN) {
								{
									setState(195);
									doubleListAssign();
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
					setState(200);
					match(VAR);
					setState(201);
					match(NATURAL_TYPE);
					setState(211);
					switch (_input.LA(1)) {
						case IDENTIFIER: {
							setState(202);
							match(IDENTIFIER);
							setState(204);
							_la = _input.LA(1);
							if (_la == ASSIGN) {
								{
									setState(203);
									naturalAssign();
								}
							}

						}
						break;
						case LIST: {
							setState(206);
							match(LIST);
							setState(207);
							match(IDENTIFIER);
							setState(209);
							_la = _input.LA(1);
							if (_la == ASSIGN) {
								{
									setState(208);
									naturalListAssign();
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
					setState(213);
					match(VAR);
					setState(214);
					match(BOOLEAN_TYPE);
					setState(224);
					switch (_input.LA(1)) {
						case IDENTIFIER: {
							setState(215);
							match(IDENTIFIER);
							setState(217);
							_la = _input.LA(1);
							if (_la == ASSIGN) {
								{
									setState(216);
									booleanAssign();
								}
							}

						}
						break;
						case LIST: {
							setState(219);
							match(LIST);
							setState(220);
							match(IDENTIFIER);
							setState(222);
							_la = _input.LA(1);
							if (_la == ASSIGN) {
								{
									setState(221);
									booleanListAssign();
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
					setState(226);
					match(VAR);
					setState(227);
					match(STRING_TYPE);
					setState(237);
					switch (_input.LA(1)) {
						case IDENTIFIER: {
							setState(228);
							match(IDENTIFIER);
							setState(230);
							_la = _input.LA(1);
							if (_la == ASSIGN) {
								{
									setState(229);
									stringAssign();
								}
							}

						}
						break;
						case LIST: {
							setState(232);
							match(LIST);
							setState(233);
							match(IDENTIFIER);
							setState(235);
							_la = _input.LA(1);
							if (_la == ASSIGN) {
								{
									setState(234);
									stringListAssign();
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
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final ReferenceContext reference() throws RecognitionException {
		ReferenceContext _localctx = new ReferenceContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_reference);
		int _la;
		try {
			setState(262);
			switch (getInterpreter().adaptivePredict(_input, 43, _ctx)) {
				case 1:
					enterOuterAlt(_localctx, 1);
				{
					setState(241);
					match(VAR);
					setState(242);
					match(IDENTIFIER);
					setState(247);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la == DOT) {
						{
							{
								setState(243);
								match(DOT);
								setState(244);
								match(IDENTIFIER);
							}
						}
						setState(249);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(250);
					match(IDENTIFIER);
				}
				break;
				case 2:
					enterOuterAlt(_localctx, 2);
				{
					setState(251);
					match(VAR);
					setState(252);
					match(IDENTIFIER);
					setState(257);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la == DOT) {
						{
							{
								setState(253);
								match(DOT);
								setState(254);
								match(IDENTIFIER);
							}
						}
						setState(259);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(260);
					match(LIST);
					setState(261);
					match(IDENTIFIER);
				}
				break;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final WordContext word() throws RecognitionException {
		WordContext _localctx = new WordContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_word);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(264);
				match(VAR);
				setState(265);
				match(WORD);
				setState(266);
				match(IDENTIFIER);
				setState(267);
				match(NEWLINE_INDENT);
				setState(274);
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
						{
							setState(268);
							match(IDENTIFIER);
							setState(270);
							_errHandler.sync(this);
							_la = _input.LA(1);
							do {
								{
									{
										setState(269);
										match(NEWLINE);
									}
								}
								setState(272);
								_errHandler.sync(this);
								_la = _input.LA(1);
							} while (_la == NEWLINE);
						}
					}
					setState(276);
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while (_la == IDENTIFIER);
				setState(278);
				match(DEDENT);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final StringAssignContext stringAssign() throws RecognitionException {
		StringAssignContext _localctx = new StringAssignContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_stringAssign);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(280);
				match(ASSIGN);
				setState(281);
				match(STRING_VALUE);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final BooleanAssignContext booleanAssign() throws RecognitionException {
		BooleanAssignContext _localctx = new BooleanAssignContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_booleanAssign);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(283);
				match(ASSIGN);
				setState(284);
				match(BOOLEAN_VALUE);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final IntegerAssignContext integerAssign() throws RecognitionException {
		IntegerAssignContext _localctx = new IntegerAssignContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_integerAssign);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(286);
				match(ASSIGN);
				setState(287);
				_la = _input.LA(1);
				if (!(_la == POSITIVE_VALUE || _la == NEGATIVE_VALUE)) {
					_errHandler.recoverInline(this);
				}
				consume();
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final DoubleAssignContext doubleAssign() throws RecognitionException {
		DoubleAssignContext _localctx = new DoubleAssignContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_doubleAssign);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(289);
				match(ASSIGN);
				setState(290);
				_la = _input.LA(1);
				if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << POSITIVE_VALUE) | (1L << NEGATIVE_VALUE) | (1L << DOUBLE_VALUE))) != 0))) {
					_errHandler.recoverInline(this);
				}
				consume();
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final NaturalAssignContext naturalAssign() throws RecognitionException {
		NaturalAssignContext _localctx = new NaturalAssignContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_naturalAssign);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(292);
				match(ASSIGN);
				setState(293);
				match(POSITIVE_VALUE);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final StringListAssignContext stringListAssign() throws RecognitionException {
		StringListAssignContext _localctx = new StringListAssignContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_stringListAssign);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(295);
				match(ASSIGN);
				setState(296);
				match(LEFT_SQUARE);
				setState(298);
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
						{
							setState(297);
							match(STRING_VALUE);
						}
					}
					setState(300);
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while (_la == STRING_VALUE);
				setState(302);
				match(RIGHT_SQUARE);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final BooleanListAssignContext booleanListAssign() throws RecognitionException {
		BooleanListAssignContext _localctx = new BooleanListAssignContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_booleanListAssign);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(304);
				match(ASSIGN);
				setState(305);
				match(LEFT_SQUARE);
				setState(307);
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
						{
							setState(306);
							match(BOOLEAN_VALUE);
						}
					}
					setState(309);
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while (_la == BOOLEAN_VALUE);
				setState(311);
				match(RIGHT_SQUARE);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final IntegerListAssignContext integerListAssign() throws RecognitionException {
		IntegerListAssignContext _localctx = new IntegerListAssignContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_integerListAssign);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(313);
				match(ASSIGN);
				setState(314);
				match(LEFT_SQUARE);
				setState(316);
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
						{
							setState(315);
							_la = _input.LA(1);
							if (!(_la == POSITIVE_VALUE || _la == NEGATIVE_VALUE)) {
								_errHandler.recoverInline(this);
							}
							consume();
						}
					}
					setState(318);
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while (_la == POSITIVE_VALUE || _la == NEGATIVE_VALUE);
				setState(320);
				match(RIGHT_SQUARE);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final DoubleListAssignContext doubleListAssign() throws RecognitionException {
		DoubleListAssignContext _localctx = new DoubleListAssignContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_doubleListAssign);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(322);
				match(ASSIGN);
				setState(323);
				match(LEFT_SQUARE);
				setState(325);
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
						{
							setState(324);
							_la = _input.LA(1);
							if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << POSITIVE_VALUE) | (1L << NEGATIVE_VALUE) | (1L << DOUBLE_VALUE))) != 0))) {
								_errHandler.recoverInline(this);
							}
							consume();
						}
					}
					setState(327);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << POSITIVE_VALUE) | (1L << NEGATIVE_VALUE) | (1L << DOUBLE_VALUE))) != 0));
				setState(329);
				match(RIGHT_SQUARE);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final NaturalListAssignContext naturalListAssign() throws RecognitionException {
		NaturalListAssignContext _localctx = new NaturalListAssignContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_naturalListAssign);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(331);
				match(ASSIGN);
				setState(332);
				match(LEFT_SQUARE);
				setState(334);
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
						{
							setState(333);
							match(POSITIVE_VALUE);
						}
					}
					setState(336);
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while (_la == POSITIVE_VALUE);
				setState(338);
				match(RIGHT_SQUARE);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final ConceptAnnotationsContext conceptAnnotations() throws RecognitionException {
		ConceptAnnotationsContext _localctx = new ConceptAnnotationsContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_conceptAnnotations);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(340);
				match(OPEN_AN);
				setState(342);
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
						{
							setState(341);
							_la = _input.LA(1);
							if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HAS_CODE) | (1L << EXTENSIBLE) | (1L << ROOT) | (1L << SINGLETON))) != 0))) {
								_errHandler.recoverInline(this);
							}
							consume();
						}
					}
					setState(344);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HAS_CODE) | (1L << EXTENSIBLE) | (1L << ROOT) | (1L << SINGLETON))) != 0));
				setState(346);
				match(CLOSE_AN);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final ComponentAnnotationsContext componentAnnotations() throws RecognitionException {
		ComponentAnnotationsContext _localctx = new ComponentAnnotationsContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_componentAnnotations);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(348);
				match(OPEN_AN);
				setState(350);
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
						{
							setState(349);
							_la = _input.LA(1);
							if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MULTIPLE) | (1L << OPTIONAL) | (1L << HAS_CODE) | (1L << EXTENSIBLE) | (1L << SINGLETON))) != 0))) {
								_errHandler.recoverInline(this);
							}
							consume();
						}
					}
					setState(352);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MULTIPLE) | (1L << OPTIONAL) | (1L << HAS_CODE) | (1L << EXTENSIBLE) | (1L << SINGLETON))) != 0));
				setState(354);
				match(CLOSE_AN);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final FromAnnotationsContext fromAnnotations() throws RecognitionException {
		FromAnnotationsContext _localctx = new FromAnnotationsContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_fromAnnotations);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(356);
				match(OPEN_AN);
				setState(358);
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
						{
							setState(357);
							_la = _input.LA(1);
							if (!(_la == MULTIPLE || _la == OPTIONAL)) {
								_errHandler.recoverInline(this);
							}
							consume();
						}
					}
					setState(360);
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while (_la == MULTIPLE || _la == OPTIONAL);
				setState(362);
				match(CLOSE_AN);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final FromComponentAnnotationsContext fromComponentAnnotations() throws RecognitionException {
		FromComponentAnnotationsContext _localctx = new FromComponentAnnotationsContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_fromComponentAnnotations);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(364);
				match(OPEN_AN);
				setState(366);
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
						{
							setState(365);
							_la = _input.LA(1);
							if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HAS_CODE) | (1L << EXTENSIBLE) | (1L << SINGLETON))) != 0))) {
								_errHandler.recoverInline(this);
							}
							consume();
						}
					}
					setState(368);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HAS_CODE) | (1L << EXTENSIBLE) | (1L << SINGLETON))) != 0));
				setState(370);
				match(CLOSE_AN);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final ModifierContext modifier() throws RecognitionException {
		ModifierContext _localctx = new ModifierContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_modifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(372);
				_la = _input.LA(1);
				if (!(_la == FINAL || _la == ABSTRACT)) {
					_errHandler.recoverInline(this);
				}
				consume();
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final DocContext doc() throws RecognitionException {
		DocContext _localctx = new DocContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_doc);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(375);
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
						{
							setState(374);
							match(DOC);
						}
					}
					setState(377);
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while (_la == DOC);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RootContext extends ParserRuleContext {
		public RootContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public List<TerminalNode> NEWLINE() {
			return getTokens(TaraM2Grammar.NEWLINE);
		}

		public List<ConceptContext> concept() {
			return getRuleContexts(ConceptContext.class);
		}

		public TerminalNode EOF() {
			return getToken(TaraM2Grammar.EOF, 0);
		}

		public TerminalNode NEWLINE(int i) {
			return getToken(TaraM2Grammar.NEWLINE, i);
		}

		public ConceptContext concept(int i) {
			return getRuleContext(ConceptContext.class, i);
		}

		@Override
		public int getRuleIndex() {
			return RULE_root;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraM2GrammarListener) ((TaraM2GrammarListener) listener).enterRoot(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraM2GrammarListener) ((TaraM2GrammarListener) listener).exitRoot(this);
		}
	}

	public static class ExtendedConceptContext extends ParserRuleContext {
		public ExtendedConceptContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public List<TerminalNode> DOT() {
			return getTokens(TaraM2Grammar.DOT);
		}

		public TerminalNode IDENTIFIER(int i) {
			return getToken(TaraM2Grammar.IDENTIFIER, i);
		}

		public List<TerminalNode> IDENTIFIER() {
			return getTokens(TaraM2Grammar.IDENTIFIER);
		}

		public TerminalNode DOT(int i) {
			return getToken(TaraM2Grammar.DOT, i);
		}

		@Override
		public int getRuleIndex() {
			return RULE_extendedConcept;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraM2GrammarListener)
				((TaraM2GrammarListener) listener).enterExtendedConcept(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraM2GrammarListener) ((TaraM2GrammarListener) listener).exitExtendedConcept(this);
		}
	}

	public static class ConceptSignatureContext extends ParserRuleContext {
		public ConceptSignatureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public TerminalNode AS() {
			return getToken(TaraM2Grammar.AS, 0);
		}

		public ExtendedConceptContext extendedConcept() {
			return getRuleContext(ExtendedConceptContext.class, 0);
		}

		public TerminalNode IDENTIFIER() {
			return getToken(TaraM2Grammar.IDENTIFIER, 0);
		}

		public ModifierContext modifier() {
			return getRuleContext(ModifierContext.class, 0);
		}

		public TerminalNode CONCEPT() {
			return getToken(TaraM2Grammar.CONCEPT, 0);
		}

		@Override
		public int getRuleIndex() {
			return RULE_conceptSignature;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraM2GrammarListener)
				((TaraM2GrammarListener) listener).enterConceptSignature(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraM2GrammarListener)
				((TaraM2GrammarListener) listener).exitConceptSignature(this);
		}
	}

	public static class ConceptContext extends ParserRuleContext {
		public ConceptContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public ConceptBodyContext conceptBody() {
			return getRuleContext(ConceptBodyContext.class, 0);
		}

		public ConceptAnnotationsContext conceptAnnotations() {
			return getRuleContext(ConceptAnnotationsContext.class, 0);
		}

		public ConceptSignatureContext conceptSignature() {
			return getRuleContext(ConceptSignatureContext.class, 0);
		}

		public DocContext doc() {
			return getRuleContext(DocContext.class, 0);
		}

		@Override
		public int getRuleIndex() {
			return RULE_concept;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraM2GrammarListener) ((TaraM2GrammarListener) listener).enterConcept(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraM2GrammarListener) ((TaraM2GrammarListener) listener).exitConcept(this);
		}
	}

	public static class ConceptBodyContext extends ParserRuleContext {
		public ConceptBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public List<TerminalNode> NEWLINE() {
			return getTokens(TaraM2Grammar.NEWLINE);
		}

		public TerminalNode NEWLINE(int i) {
			return getToken(TaraM2Grammar.NEWLINE, i);
		}

		public List<ConceptConstituentContext> conceptConstituent() {
			return getRuleContexts(ConceptConstituentContext.class);
		}

		public TerminalNode DEDENT() {
			return getToken(TaraM2Grammar.DEDENT, 0);
		}

		public ConceptConstituentContext conceptConstituent(int i) {
			return getRuleContext(ConceptConstituentContext.class, i);
		}

		public TerminalNode NEWLINE_INDENT() {
			return getToken(TaraM2Grammar.NEWLINE_INDENT, 0);
		}

		@Override
		public int getRuleIndex() {
			return RULE_conceptBody;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraM2GrammarListener) ((TaraM2GrammarListener) listener).enterConceptBody(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraM2GrammarListener) ((TaraM2GrammarListener) listener).exitConceptBody(this);
		}
	}

	public static class ConceptConstituentContext extends ParserRuleContext {
		public ConceptConstituentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public FromContext from() {
			return getRuleContext(FromContext.class, 0);
		}

		public WordContext word() {
			return getRuleContext(WordContext.class, 0);
		}

		public AttributeContext attribute() {
			return getRuleContext(AttributeContext.class, 0);
		}

		public ComponentContext component() {
			return getRuleContext(ComponentContext.class, 0);
		}

		public ReferenceContext reference() {
			return getRuleContext(ReferenceContext.class, 0);
		}

		@Override
		public int getRuleIndex() {
			return RULE_conceptConstituent;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraM2GrammarListener)
				((TaraM2GrammarListener) listener).enterConceptConstituent(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraM2GrammarListener)
				((TaraM2GrammarListener) listener).exitConceptConstituent(this);
		}
	}

	public static class ComponentContext extends ParserRuleContext {
		public ComponentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public ExtendedConceptContext extendedConcept() {
			return getRuleContext(ExtendedConceptContext.class, 0);
		}

		public ConceptBodyContext conceptBody() {
			return getRuleContext(ConceptBodyContext.class, 0);
		}

		public TerminalNode NEW() {
			return getToken(TaraM2Grammar.NEW, 0);
		}

		public ConceptSignatureContext conceptSignature() {
			return getRuleContext(ConceptSignatureContext.class, 0);
		}

		public ComponentAnnotationsContext componentAnnotations() {
			return getRuleContext(ComponentAnnotationsContext.class, 0);
		}

		public DocContext doc() {
			return getRuleContext(DocContext.class, 0);
		}

		@Override
		public int getRuleIndex() {
			return RULE_component;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraM2GrammarListener) ((TaraM2GrammarListener) listener).enterComponent(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraM2GrammarListener) ((TaraM2GrammarListener) listener).exitComponent(this);
		}
	}

	public static class FromContext extends ParserRuleContext {
		public FromContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public FromBodyContext fromBody() {
			return getRuleContext(FromBodyContext.class, 0);
		}

		public FromAnnotationsContext fromAnnotations() {
			return getRuleContext(FromAnnotationsContext.class, 0);
		}

		public TerminalNode FROM() {
			return getToken(TaraM2Grammar.FROM, 0);
		}

		@Override
		public int getRuleIndex() {
			return RULE_from;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraM2GrammarListener) ((TaraM2GrammarListener) listener).enterFrom(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraM2GrammarListener) ((TaraM2GrammarListener) listener).exitFrom(this);
		}
	}

	public static class FromBodyContext extends ParserRuleContext {
		public FromBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public List<TerminalNode> NEWLINE() {
			return getTokens(TaraM2Grammar.NEWLINE);
		}

		public FromComponentContext fromComponent(int i) {
			return getRuleContext(FromComponentContext.class, i);
		}

		public List<FromComponentContext> fromComponent() {
			return getRuleContexts(FromComponentContext.class);
		}

		public TerminalNode NEWLINE(int i) {
			return getToken(TaraM2Grammar.NEWLINE, i);
		}

		public TerminalNode DEDENT() {
			return getToken(TaraM2Grammar.DEDENT, 0);
		}

		public TerminalNode NEWLINE_INDENT() {
			return getToken(TaraM2Grammar.NEWLINE_INDENT, 0);
		}

		@Override
		public int getRuleIndex() {
			return RULE_fromBody;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraM2GrammarListener) ((TaraM2GrammarListener) listener).enterFromBody(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraM2GrammarListener) ((TaraM2GrammarListener) listener).exitFromBody(this);
		}
	}

	public static class FromComponentContext extends ParserRuleContext {
		public FromComponentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public ExtendedConceptContext extendedConcept() {
			return getRuleContext(ExtendedConceptContext.class, 0);
		}

		public ConceptBodyContext conceptBody() {
			return getRuleContext(ConceptBodyContext.class, 0);
		}

		public TerminalNode NEW() {
			return getToken(TaraM2Grammar.NEW, 0);
		}

		public ConceptSignatureContext conceptSignature() {
			return getRuleContext(ConceptSignatureContext.class, 0);
		}

		public DocContext doc() {
			return getRuleContext(DocContext.class, 0);
		}

		public FromComponentAnnotationsContext fromComponentAnnotations() {
			return getRuleContext(FromComponentAnnotationsContext.class, 0);
		}

		@Override
		public int getRuleIndex() {
			return RULE_fromComponent;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraM2GrammarListener) ((TaraM2GrammarListener) listener).enterFromComponent(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraM2GrammarListener) ((TaraM2GrammarListener) listener).exitFromComponent(this);
		}
	}

	public static class AttributeContext extends ParserRuleContext {
		public AttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public BooleanAssignContext booleanAssign() {
			return getRuleContext(BooleanAssignContext.class, 0);
		}

		public TerminalNode INT_TYPE() {
			return getToken(TaraM2Grammar.INT_TYPE, 0);
		}

		public TerminalNode BOOLEAN_TYPE() {
			return getToken(TaraM2Grammar.BOOLEAN_TYPE, 0);
		}

		public TerminalNode NATURAL_TYPE() {
			return getToken(TaraM2Grammar.NATURAL_TYPE, 0);
		}

		public TerminalNode STRING_TYPE() {
			return getToken(TaraM2Grammar.STRING_TYPE, 0);
		}

		public IntegerAssignContext integerAssign() {
			return getRuleContext(IntegerAssignContext.class, 0);
		}

		public StringListAssignContext stringListAssign() {
			return getRuleContext(StringListAssignContext.class, 0);
		}

		public TerminalNode UID_TYPE() {
			return getToken(TaraM2Grammar.UID_TYPE, 0);
		}

		public TerminalNode LIST() {
			return getToken(TaraM2Grammar.LIST, 0);
		}

		public DoubleAssignContext doubleAssign() {
			return getRuleContext(DoubleAssignContext.class, 0);
		}

		public BooleanListAssignContext booleanListAssign() {
			return getRuleContext(BooleanListAssignContext.class, 0);
		}

		public TerminalNode DOUBLE_TYPE() {
			return getToken(TaraM2Grammar.DOUBLE_TYPE, 0);
		}

		public NaturalAssignContext naturalAssign() {
			return getRuleContext(NaturalAssignContext.class, 0);
		}

		public StringAssignContext stringAssign() {
			return getRuleContext(StringAssignContext.class, 0);
		}

		public TerminalNode IDENTIFIER() {
			return getToken(TaraM2Grammar.IDENTIFIER, 0);
		}

		public TerminalNode VAR() {
			return getToken(TaraM2Grammar.VAR, 0);
		}

		public DoubleListAssignContext doubleListAssign() {
			return getRuleContext(DoubleListAssignContext.class, 0);
		}

		public IntegerListAssignContext integerListAssign() {
			return getRuleContext(IntegerListAssignContext.class, 0);
		}

		public NaturalListAssignContext naturalListAssign() {
			return getRuleContext(NaturalListAssignContext.class, 0);
		}

		@Override
		public int getRuleIndex() {
			return RULE_attribute;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraM2GrammarListener) ((TaraM2GrammarListener) listener).enterAttribute(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraM2GrammarListener) ((TaraM2GrammarListener) listener).exitAttribute(this);
		}
	}

	public static class ReferenceContext extends ParserRuleContext {
		public ReferenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public List<TerminalNode> DOT() {
			return getTokens(TaraM2Grammar.DOT);
		}

		public TerminalNode IDENTIFIER(int i) {
			return getToken(TaraM2Grammar.IDENTIFIER, i);
		}

		public List<TerminalNode> IDENTIFIER() {
			return getTokens(TaraM2Grammar.IDENTIFIER);
		}

		public TerminalNode VAR() {
			return getToken(TaraM2Grammar.VAR, 0);
		}

		public TerminalNode LIST() {
			return getToken(TaraM2Grammar.LIST, 0);
		}

		public TerminalNode DOT(int i) {
			return getToken(TaraM2Grammar.DOT, i);
		}

		@Override
		public int getRuleIndex() {
			return RULE_reference;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraM2GrammarListener) ((TaraM2GrammarListener) listener).enterReference(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraM2GrammarListener) ((TaraM2GrammarListener) listener).exitReference(this);
		}
	}

	public static class WordContext extends ParserRuleContext {
		public WordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public List<TerminalNode> NEWLINE() {
			return getTokens(TaraM2Grammar.NEWLINE);
		}

		public TerminalNode WORD() {
			return getToken(TaraM2Grammar.WORD, 0);
		}

		public TerminalNode NEWLINE(int i) {
			return getToken(TaraM2Grammar.NEWLINE, i);
		}

		public TerminalNode IDENTIFIER(int i) {
			return getToken(TaraM2Grammar.IDENTIFIER, i);
		}

		public List<TerminalNode> IDENTIFIER() {
			return getTokens(TaraM2Grammar.IDENTIFIER);
		}

		public TerminalNode VAR() {
			return getToken(TaraM2Grammar.VAR, 0);
		}

		public TerminalNode DEDENT() {
			return getToken(TaraM2Grammar.DEDENT, 0);
		}

		public TerminalNode NEWLINE_INDENT() {
			return getToken(TaraM2Grammar.NEWLINE_INDENT, 0);
		}

		@Override
		public int getRuleIndex() {
			return RULE_word;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraM2GrammarListener) ((TaraM2GrammarListener) listener).enterWord(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraM2GrammarListener) ((TaraM2GrammarListener) listener).exitWord(this);
		}
	}

	public static class StringAssignContext extends ParserRuleContext {
		public StringAssignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public TerminalNode STRING_VALUE() {
			return getToken(TaraM2Grammar.STRING_VALUE, 0);
		}

		public TerminalNode ASSIGN() {
			return getToken(TaraM2Grammar.ASSIGN, 0);
		}

		@Override
		public int getRuleIndex() {
			return RULE_stringAssign;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraM2GrammarListener) ((TaraM2GrammarListener) listener).enterStringAssign(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraM2GrammarListener) ((TaraM2GrammarListener) listener).exitStringAssign(this);
		}
	}

	public static class BooleanAssignContext extends ParserRuleContext {
		public BooleanAssignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public TerminalNode BOOLEAN_VALUE() {
			return getToken(TaraM2Grammar.BOOLEAN_VALUE, 0);
		}

		public TerminalNode ASSIGN() {
			return getToken(TaraM2Grammar.ASSIGN, 0);
		}

		@Override
		public int getRuleIndex() {
			return RULE_booleanAssign;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraM2GrammarListener) ((TaraM2GrammarListener) listener).enterBooleanAssign(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraM2GrammarListener) ((TaraM2GrammarListener) listener).exitBooleanAssign(this);
		}
	}

	public static class IntegerAssignContext extends ParserRuleContext {
		public IntegerAssignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public TerminalNode ASSIGN() {
			return getToken(TaraM2Grammar.ASSIGN, 0);
		}

		public TerminalNode POSITIVE_VALUE() {
			return getToken(TaraM2Grammar.POSITIVE_VALUE, 0);
		}

		public TerminalNode NEGATIVE_VALUE() {
			return getToken(TaraM2Grammar.NEGATIVE_VALUE, 0);
		}

		@Override
		public int getRuleIndex() {
			return RULE_integerAssign;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraM2GrammarListener) ((TaraM2GrammarListener) listener).enterIntegerAssign(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraM2GrammarListener) ((TaraM2GrammarListener) listener).exitIntegerAssign(this);
		}
	}

	public static class DoubleAssignContext extends ParserRuleContext {
		public DoubleAssignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public TerminalNode DOUBLE_VALUE() {
			return getToken(TaraM2Grammar.DOUBLE_VALUE, 0);
		}

		public TerminalNode ASSIGN() {
			return getToken(TaraM2Grammar.ASSIGN, 0);
		}

		public TerminalNode POSITIVE_VALUE() {
			return getToken(TaraM2Grammar.POSITIVE_VALUE, 0);
		}

		public TerminalNode NEGATIVE_VALUE() {
			return getToken(TaraM2Grammar.NEGATIVE_VALUE, 0);
		}

		@Override
		public int getRuleIndex() {
			return RULE_doubleAssign;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraM2GrammarListener) ((TaraM2GrammarListener) listener).enterDoubleAssign(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraM2GrammarListener) ((TaraM2GrammarListener) listener).exitDoubleAssign(this);
		}
	}

	public static class NaturalAssignContext extends ParserRuleContext {
		public NaturalAssignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public TerminalNode ASSIGN() {
			return getToken(TaraM2Grammar.ASSIGN, 0);
		}

		public TerminalNode POSITIVE_VALUE() {
			return getToken(TaraM2Grammar.POSITIVE_VALUE, 0);
		}

		@Override
		public int getRuleIndex() {
			return RULE_naturalAssign;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraM2GrammarListener) ((TaraM2GrammarListener) listener).enterNaturalAssign(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraM2GrammarListener) ((TaraM2GrammarListener) listener).exitNaturalAssign(this);
		}
	}

	public static class StringListAssignContext extends ParserRuleContext {
		public StringListAssignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public TerminalNode STRING_VALUE(int i) {
			return getToken(TaraM2Grammar.STRING_VALUE, i);
		}

		public TerminalNode LEFT_SQUARE() {
			return getToken(TaraM2Grammar.LEFT_SQUARE, 0);
		}

		public List<TerminalNode> STRING_VALUE() {
			return getTokens(TaraM2Grammar.STRING_VALUE);
		}

		public TerminalNode RIGHT_SQUARE() {
			return getToken(TaraM2Grammar.RIGHT_SQUARE, 0);
		}

		public TerminalNode ASSIGN() {
			return getToken(TaraM2Grammar.ASSIGN, 0);
		}

		@Override
		public int getRuleIndex() {
			return RULE_stringListAssign;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraM2GrammarListener)
				((TaraM2GrammarListener) listener).enterStringListAssign(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraM2GrammarListener)
				((TaraM2GrammarListener) listener).exitStringListAssign(this);
		}
	}

	public static class BooleanListAssignContext extends ParserRuleContext {
		public BooleanListAssignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public TerminalNode LEFT_SQUARE() {
			return getToken(TaraM2Grammar.LEFT_SQUARE, 0);
		}

		public List<TerminalNode> BOOLEAN_VALUE() {
			return getTokens(TaraM2Grammar.BOOLEAN_VALUE);
		}

		public TerminalNode RIGHT_SQUARE() {
			return getToken(TaraM2Grammar.RIGHT_SQUARE, 0);
		}

		public TerminalNode ASSIGN() {
			return getToken(TaraM2Grammar.ASSIGN, 0);
		}

		public TerminalNode BOOLEAN_VALUE(int i) {
			return getToken(TaraM2Grammar.BOOLEAN_VALUE, i);
		}

		@Override
		public int getRuleIndex() {
			return RULE_booleanListAssign;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraM2GrammarListener)
				((TaraM2GrammarListener) listener).enterBooleanListAssign(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraM2GrammarListener)
				((TaraM2GrammarListener) listener).exitBooleanListAssign(this);
		}
	}

	public static class IntegerListAssignContext extends ParserRuleContext {
		public IntegerListAssignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public TerminalNode LEFT_SQUARE() {
			return getToken(TaraM2Grammar.LEFT_SQUARE, 0);
		}

		public TerminalNode RIGHT_SQUARE() {
			return getToken(TaraM2Grammar.RIGHT_SQUARE, 0);
		}

		public TerminalNode ASSIGN() {
			return getToken(TaraM2Grammar.ASSIGN, 0);
		}

		public List<TerminalNode> POSITIVE_VALUE() {
			return getTokens(TaraM2Grammar.POSITIVE_VALUE);
		}

		public TerminalNode NEGATIVE_VALUE(int i) {
			return getToken(TaraM2Grammar.NEGATIVE_VALUE, i);
		}

		public List<TerminalNode> NEGATIVE_VALUE() {
			return getTokens(TaraM2Grammar.NEGATIVE_VALUE);
		}

		public TerminalNode POSITIVE_VALUE(int i) {
			return getToken(TaraM2Grammar.POSITIVE_VALUE, i);
		}

		@Override
		public int getRuleIndex() {
			return RULE_integerListAssign;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraM2GrammarListener)
				((TaraM2GrammarListener) listener).enterIntegerListAssign(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraM2GrammarListener)
				((TaraM2GrammarListener) listener).exitIntegerListAssign(this);
		}
	}

	public static class DoubleListAssignContext extends ParserRuleContext {
		public DoubleListAssignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public List<TerminalNode> DOUBLE_VALUE() {
			return getTokens(TaraM2Grammar.DOUBLE_VALUE);
		}

		public TerminalNode LEFT_SQUARE() {
			return getToken(TaraM2Grammar.LEFT_SQUARE, 0);
		}

		public TerminalNode RIGHT_SQUARE() {
			return getToken(TaraM2Grammar.RIGHT_SQUARE, 0);
		}

		public TerminalNode ASSIGN() {
			return getToken(TaraM2Grammar.ASSIGN, 0);
		}

		public TerminalNode DOUBLE_VALUE(int i) {
			return getToken(TaraM2Grammar.DOUBLE_VALUE, i);
		}

		public List<TerminalNode> POSITIVE_VALUE() {
			return getTokens(TaraM2Grammar.POSITIVE_VALUE);
		}

		public TerminalNode NEGATIVE_VALUE(int i) {
			return getToken(TaraM2Grammar.NEGATIVE_VALUE, i);
		}

		public List<TerminalNode> NEGATIVE_VALUE() {
			return getTokens(TaraM2Grammar.NEGATIVE_VALUE);
		}

		public TerminalNode POSITIVE_VALUE(int i) {
			return getToken(TaraM2Grammar.POSITIVE_VALUE, i);
		}

		@Override
		public int getRuleIndex() {
			return RULE_doubleListAssign;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraM2GrammarListener)
				((TaraM2GrammarListener) listener).enterDoubleListAssign(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraM2GrammarListener)
				((TaraM2GrammarListener) listener).exitDoubleListAssign(this);
		}
	}

	public static class NaturalListAssignContext extends ParserRuleContext {
		public NaturalListAssignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public TerminalNode LEFT_SQUARE() {
			return getToken(TaraM2Grammar.LEFT_SQUARE, 0);
		}

		public TerminalNode RIGHT_SQUARE() {
			return getToken(TaraM2Grammar.RIGHT_SQUARE, 0);
		}

		public TerminalNode ASSIGN() {
			return getToken(TaraM2Grammar.ASSIGN, 0);
		}

		public List<TerminalNode> POSITIVE_VALUE() {
			return getTokens(TaraM2Grammar.POSITIVE_VALUE);
		}

		public TerminalNode POSITIVE_VALUE(int i) {
			return getToken(TaraM2Grammar.POSITIVE_VALUE, i);
		}

		@Override
		public int getRuleIndex() {
			return RULE_naturalListAssign;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraM2GrammarListener)
				((TaraM2GrammarListener) listener).enterNaturalListAssign(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraM2GrammarListener)
				((TaraM2GrammarListener) listener).exitNaturalListAssign(this);
		}
	}

	public static class ConceptAnnotationsContext extends ParserRuleContext {
		public ConceptAnnotationsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public TerminalNode OPEN_AN() {
			return getToken(TaraM2Grammar.OPEN_AN, 0);
		}

		public List<TerminalNode> ROOT() {
			return getTokens(TaraM2Grammar.ROOT);
		}

		public TerminalNode EXTENSIBLE(int i) {
			return getToken(TaraM2Grammar.EXTENSIBLE, i);
		}

		public TerminalNode CLOSE_AN() {
			return getToken(TaraM2Grammar.CLOSE_AN, 0);
		}

		public TerminalNode HAS_CODE(int i) {
			return getToken(TaraM2Grammar.HAS_CODE, i);
		}

		public List<TerminalNode> HAS_CODE() {
			return getTokens(TaraM2Grammar.HAS_CODE);
		}

		public List<TerminalNode> EXTENSIBLE() {
			return getTokens(TaraM2Grammar.EXTENSIBLE);
		}

		public TerminalNode ROOT(int i) {
			return getToken(TaraM2Grammar.ROOT, i);
		}

		public TerminalNode SINGLETON(int i) {
			return getToken(TaraM2Grammar.SINGLETON, i);
		}

		public List<TerminalNode> SINGLETON() {
			return getTokens(TaraM2Grammar.SINGLETON);
		}

		@Override
		public int getRuleIndex() {
			return RULE_conceptAnnotations;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraM2GrammarListener)
				((TaraM2GrammarListener) listener).enterConceptAnnotations(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraM2GrammarListener)
				((TaraM2GrammarListener) listener).exitConceptAnnotations(this);
		}
	}

	public static class ComponentAnnotationsContext extends ParserRuleContext {
		public ComponentAnnotationsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public List<TerminalNode> OPTIONAL() {
			return getTokens(TaraM2Grammar.OPTIONAL);
		}

		public TerminalNode OPEN_AN() {
			return getToken(TaraM2Grammar.OPEN_AN, 0);
		}

		public TerminalNode EXTENSIBLE(int i) {
			return getToken(TaraM2Grammar.EXTENSIBLE, i);
		}

		public TerminalNode MULTIPLE(int i) {
			return getToken(TaraM2Grammar.MULTIPLE, i);
		}

		public TerminalNode CLOSE_AN() {
			return getToken(TaraM2Grammar.CLOSE_AN, 0);
		}

		public TerminalNode OPTIONAL(int i) {
			return getToken(TaraM2Grammar.OPTIONAL, i);
		}

		public TerminalNode HAS_CODE(int i) {
			return getToken(TaraM2Grammar.HAS_CODE, i);
		}

		public List<TerminalNode> HAS_CODE() {
			return getTokens(TaraM2Grammar.HAS_CODE);
		}

		public List<TerminalNode> EXTENSIBLE() {
			return getTokens(TaraM2Grammar.EXTENSIBLE);
		}

		public List<TerminalNode> MULTIPLE() {
			return getTokens(TaraM2Grammar.MULTIPLE);
		}

		public TerminalNode SINGLETON(int i) {
			return getToken(TaraM2Grammar.SINGLETON, i);
		}

		public List<TerminalNode> SINGLETON() {
			return getTokens(TaraM2Grammar.SINGLETON);
		}

		@Override
		public int getRuleIndex() {
			return RULE_componentAnnotations;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraM2GrammarListener)
				((TaraM2GrammarListener) listener).enterComponentAnnotations(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraM2GrammarListener)
				((TaraM2GrammarListener) listener).exitComponentAnnotations(this);
		}
	}

	public static class FromAnnotationsContext extends ParserRuleContext {
		public FromAnnotationsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public List<TerminalNode> OPTIONAL() {
			return getTokens(TaraM2Grammar.OPTIONAL);
		}

		public TerminalNode OPEN_AN() {
			return getToken(TaraM2Grammar.OPEN_AN, 0);
		}

		public TerminalNode MULTIPLE(int i) {
			return getToken(TaraM2Grammar.MULTIPLE, i);
		}

		public TerminalNode CLOSE_AN() {
			return getToken(TaraM2Grammar.CLOSE_AN, 0);
		}

		public TerminalNode OPTIONAL(int i) {
			return getToken(TaraM2Grammar.OPTIONAL, i);
		}

		public List<TerminalNode> MULTIPLE() {
			return getTokens(TaraM2Grammar.MULTIPLE);
		}

		@Override
		public int getRuleIndex() {
			return RULE_fromAnnotations;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraM2GrammarListener)
				((TaraM2GrammarListener) listener).enterFromAnnotations(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraM2GrammarListener) ((TaraM2GrammarListener) listener).exitFromAnnotations(this);
		}
	}

	public static class FromComponentAnnotationsContext extends ParserRuleContext {
		public FromComponentAnnotationsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public TerminalNode OPEN_AN() {
			return getToken(TaraM2Grammar.OPEN_AN, 0);
		}

		public TerminalNode EXTENSIBLE(int i) {
			return getToken(TaraM2Grammar.EXTENSIBLE, i);
		}

		public TerminalNode CLOSE_AN() {
			return getToken(TaraM2Grammar.CLOSE_AN, 0);
		}

		public TerminalNode HAS_CODE(int i) {
			return getToken(TaraM2Grammar.HAS_CODE, i);
		}

		public List<TerminalNode> HAS_CODE() {
			return getTokens(TaraM2Grammar.HAS_CODE);
		}

		public List<TerminalNode> EXTENSIBLE() {
			return getTokens(TaraM2Grammar.EXTENSIBLE);
		}

		public TerminalNode SINGLETON(int i) {
			return getToken(TaraM2Grammar.SINGLETON, i);
		}

		public List<TerminalNode> SINGLETON() {
			return getTokens(TaraM2Grammar.SINGLETON);
		}

		@Override
		public int getRuleIndex() {
			return RULE_fromComponentAnnotations;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraM2GrammarListener)
				((TaraM2GrammarListener) listener).enterFromComponentAnnotations(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraM2GrammarListener)
				((TaraM2GrammarListener) listener).exitFromComponentAnnotations(this);
		}
	}

	public static class ModifierContext extends ParserRuleContext {
		public ModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public TerminalNode FINAL() {
			return getToken(TaraM2Grammar.FINAL, 0);
		}

		public TerminalNode ABSTRACT() {
			return getToken(TaraM2Grammar.ABSTRACT, 0);
		}

		@Override
		public int getRuleIndex() {
			return RULE_modifier;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraM2GrammarListener) ((TaraM2GrammarListener) listener).enterModifier(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraM2GrammarListener) ((TaraM2GrammarListener) listener).exitModifier(this);
		}
	}

	public static class DocContext extends ParserRuleContext {
		public DocContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public TerminalNode DOC(int i) {
			return getToken(TaraM2Grammar.DOC, i);
		}

		public List<TerminalNode> DOC() {
			return getTokens(TaraM2Grammar.DOC);
		}

		@Override
		public int getRuleIndex() {
			return RULE_doc;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TaraM2GrammarListener) ((TaraM2GrammarListener) listener).enterDoc(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TaraM2GrammarListener) ((TaraM2GrammarListener) listener).exitDoc(this);
		}
	}
}