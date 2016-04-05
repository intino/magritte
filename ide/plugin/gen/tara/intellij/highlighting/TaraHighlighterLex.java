/* The following code was generated by JFlex 1.4.3 on 30/03/16 12:21 */

package tara.intellij.highlighting;

import com.intellij.lexer.FlexLexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import tara.Language;
import tara.intellij.lang.LanguageManager;
import tara.intellij.lang.psi.TaraTypes;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


/**
 * This class is a scanner generated by
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 30/03/16 12:21 from the specification file
 * <tt>/Users/oroncal/workspace/tara/ide/plugin/src/tara/intellij/highlighting/TaraHighlighterLex.flex</tt>
 */
class TaraHighlighterLex implements FlexLexer {
	/**
	 * initial size of the lookahead buffer
	 */
	private static final int ZZ_BUFFERSIZE = 16384;

	/**
	 * lexical states
	 */
	public static final int EXPRESSION = 6;
	public static final int QUOTED = 2;
	public static final int YYINITIAL = 0;
	public static final int EXPRESSION_MULTILINE = 8;
	public static final int MULTILINE = 4;

	/**
	 * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
	 * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
	 * at the beginning of a line
	 * l is of the form l = 2*k, k a non negative integer
	 */
	private static final int ZZ_LEXSTATE[] = {
		0, 0, 1, 1, 2, 2, 3, 3, 4, 4
	};

	/**
	 * Translates characters to character classes
	 */
	private static final String ZZ_CMAP_PACKED =
		"\11\55\1\62\1\57\2\0\1\60\16\55\4\0\1\62\1\56\1\46" +
			"\1\0\1\34\1\35\1\0\1\47\1\26\1\27\1\44\1\51\1\41" +
			"\1\50\1\36\1\40\12\61\1\42\1\45\1\0\1\43\1\62\2\0" +
			"\4\34\1\54\25\34\1\30\1\63\1\31\1\0\1\34\1\0\1\5" +
			"\1\3\1\22\1\12\1\6\1\25\1\52\1\4\1\15\1\53\1\34" +
			"\1\13\1\23\1\11\1\14\1\24\1\34\1\17\1\1\1\10\1\2" +
			"\1\16\1\20\1\7\1\21\1\34\1\32\1\0\1\33\1\0\41\55" +
			"\2\0\4\34\4\0\1\34\2\0\1\55\2\0\1\35\4\0\1\34" +
			"\1\0\1\37\2\0\1\34\5\0\27\34\1\0\37\34\1\0\u01ca\34" +
			"\4\0\14\34\16\0\5\34\7\0\1\34\1\0\1\34\21\0\160\55" +
			"\5\34\1\0\2\34\2\0\4\34\10\0\1\34\1\0\3\34\1\0" +
			"\1\34\1\0\24\34\1\0\123\34\1\0\213\34\1\0\5\55\2\0" +
			"\236\34\11\0\46\34\2\0\1\34\7\0\47\34\7\0\1\34\1\0" +
			"\55\55\1\0\1\55\1\0\2\55\1\0\2\55\1\0\1\55\10\0" +
			"\33\34\5\0\3\34\15\0\5\55\6\0\1\34\4\0\13\55\5\0" +
			"\53\34\25\55\12\61\4\0\2\34\1\55\143\34\1\0\1\34\10\55" +
			"\1\0\6\55\2\34\2\55\1\0\4\55\2\34\12\61\3\34\2\0" +
			"\1\34\17\0\1\55\1\34\1\55\36\34\33\55\2\0\131\34\13\55" +
			"\1\34\16\0\12\61\41\34\11\55\2\34\4\0\1\34\5\0\26\34" +
			"\4\55\1\34\11\55\1\34\3\55\1\34\5\55\22\0\31\34\3\55" +
			"\104\0\1\34\1\0\13\34\67\0\33\55\1\0\4\55\66\34\3\55" +
			"\1\34\22\55\1\34\7\55\12\34\2\55\2\0\12\61\1\0\7\34" +
			"\1\0\7\34\1\0\3\55\1\0\10\34\2\0\2\34\2\0\26\34" +
			"\1\0\7\34\1\0\1\34\3\0\4\34\2\0\1\55\1\34\7\55" +
			"\2\0\2\55\2\0\3\55\1\34\10\0\1\55\4\0\2\34\1\0" +
			"\3\34\2\55\2\0\12\61\4\34\7\0\1\34\5\0\3\55\1\0" +
			"\6\34\4\0\2\34\2\0\26\34\1\0\7\34\1\0\2\34\1\0" +
			"\2\34\1\0\2\34\2\0\1\55\1\0\5\55\4\0\2\55\2\0" +
			"\3\55\3\0\1\55\7\0\4\34\1\0\1\34\7\0\12\61\2\55" +
			"\3\34\1\55\13\0\3\55\1\0\11\34\1\0\3\34\1\0\26\34" +
			"\1\0\7\34\1\0\2\34\1\0\5\34\2\0\1\55\1\34\10\55" +
			"\1\0\3\55\1\0\3\55\2\0\1\34\17\0\2\34\2\55\2\0" +
			"\12\61\1\0\1\34\17\0\3\55\1\0\10\34\2\0\2\34\2\0" +
			"\26\34\1\0\7\34\1\0\2\34\1\0\5\34\2\0\1\55\1\34" +
			"\7\55\2\0\2\55\2\0\3\55\10\0\2\55\4\0\2\34\1\0" +
			"\3\34\2\55\2\0\12\61\1\0\1\34\20\0\1\55\1\34\1\0" +
			"\6\34\3\0\3\34\1\0\4\34\3\0\2\34\1\0\1\34\1\0" +
			"\2\34\3\0\2\34\3\0\3\34\3\0\14\34\4\0\5\55\3\0" +
			"\3\55\1\0\4\55\2\0\1\34\6\0\1\55\16\0\12\61\11\0" +
			"\1\34\7\0\3\55\1\0\10\34\1\0\3\34\1\0\27\34\1\0" +
			"\12\34\1\0\5\34\3\0\1\34\7\55\1\0\3\55\1\0\4\55" +
			"\7\0\2\55\1\0\2\34\6\0\2\34\2\55\2\0\12\61\22\0" +
			"\2\55\1\0\10\34\1\0\3\34\1\0\27\34\1\0\12\34\1\0" +
			"\5\34\2\0\1\55\1\34\7\55\1\0\3\55\1\0\4\55\7\0" +
			"\2\55\7\0\1\34\1\0\2\34\2\55\2\0\12\61\1\0\2\34" +
			"\17\0\2\55\1\0\10\34\1\0\3\34\1\0\51\34\2\0\1\34" +
			"\7\55\1\0\3\55\1\0\4\55\1\34\10\0\1\55\10\0\2\34" +
			"\2\55\2\0\12\61\12\0\6\34\2\0\2\55\1\0\22\34\3\0" +
			"\30\34\1\0\11\34\1\0\1\34\2\0\7\34\3\0\1\55\4\0" +
			"\6\55\1\0\1\55\1\0\10\55\22\0\2\55\15\0\60\34\1\55" +
			"\2\34\7\55\4\0\10\34\10\55\1\0\12\61\47\0\2\34\1\0" +
			"\1\34\2\0\2\34\1\0\1\34\2\0\1\34\6\0\4\34\1\0" +
			"\7\34\1\0\3\34\1\0\1\34\1\0\1\34\2\0\2\34\1\0" +
			"\4\34\1\55\2\34\6\55\1\0\2\55\1\34\2\0\5\34\1\0" +
			"\1\34\1\0\6\55\2\0\12\61\2\0\4\34\40\0\1\34\27\0" +
			"\2\55\6\0\12\61\13\0\1\55\1\0\1\55\1\0\1\55\4\0" +
			"\2\55\10\34\1\0\44\34\4\0\24\55\1\0\2\55\5\34\13\55" +
			"\1\0\44\55\11\0\1\55\71\0\53\34\24\55\1\34\12\61\6\0" +
			"\6\34\4\55\4\34\3\55\1\34\3\55\2\34\7\55\3\34\4\55" +
			"\15\34\14\55\1\34\1\55\12\61\4\55\2\0\46\34\1\0\1\34" +
			"\5\0\1\34\2\0\53\34\1\0\u014d\34\1\0\4\34\2\0\7\34" +
			"\1\0\1\34\1\0\4\34\2\0\51\34\1\0\4\34\2\0\41\34" +
			"\1\0\4\34\2\0\7\34\1\0\1\34\1\0\4\34\2\0\17\34" +
			"\1\0\71\34\1\0\4\34\2\0\103\34\2\0\3\55\40\0\20\34" +
			"\20\0\125\34\14\0\u026c\34\2\0\21\34\1\0\32\34\5\0\113\34" +
			"\3\0\3\34\17\0\15\34\1\0\4\34\3\55\13\0\22\34\3\55" +
			"\13\0\22\34\2\55\14\0\15\34\1\0\3\34\1\0\2\55\14\0" +
			"\64\34\40\55\3\0\1\34\3\0\2\34\1\55\2\0\12\61\41\0" +
			"\3\55\2\0\12\61\6\0\130\34\10\0\51\34\1\55\1\34\5\0" +
			"\106\34\12\0\35\34\3\0\14\55\4\0\14\55\12\0\12\61\36\34" +
			"\2\0\5\34\13\0\54\34\4\0\21\55\7\34\2\55\6\0\12\61" +
			"\46\0\27\34\5\55\4\0\65\34\12\55\1\0\35\55\2\0\1\55" +
			"\12\61\6\0\12\61\15\0\1\34\130\0\5\55\57\34\21\55\7\34" +
			"\4\0\12\61\21\0\11\55\14\0\3\55\36\34\15\55\2\34\12\61" +
			"\54\34\16\55\14\0\44\34\24\55\10\0\12\61\3\0\3\34\12\61" +
			"\44\34\122\0\3\55\1\0\25\55\4\34\1\55\4\34\3\55\2\34" +
			"\11\0\300\34\47\55\25\0\4\55\u0116\34\2\0\6\34\2\0\46\34" +
			"\2\0\6\34\2\0\10\34\1\0\1\34\1\0\1\34\1\0\1\34" +
			"\1\0\37\34\2\0\65\34\1\0\7\34\1\0\1\34\3\0\3\34" +
			"\1\0\7\34\3\0\4\34\2\0\6\34\4\0\15\34\5\0\3\34" +
			"\1\0\7\34\16\0\5\55\32\0\5\55\20\0\2\34\23\0\1\34" +
			"\13\0\5\55\5\0\6\55\1\0\1\34\15\0\1\34\20\0\15\34" +
			"\3\0\14\34\1\34\16\34\25\0\15\55\4\0\1\55\3\0\14\55" +
			"\21\0\1\34\4\0\1\34\2\0\12\34\1\0\1\34\3\0\5\34" +
			"\6\0\1\34\1\0\1\34\1\0\1\34\1\0\4\34\1\0\13\34" +
			"\2\0\4\34\5\0\5\34\4\0\1\34\21\0\51\34\u0a77\0\57\34" +
			"\1\0\57\34\1\0\205\34\6\0\4\34\3\55\2\34\14\0\46\34" +
			"\1\0\1\34\5\0\1\34\2\0\70\34\7\0\1\34\17\0\1\55" +
			"\27\34\11\0\7\34\1\0\7\34\1\0\7\34\1\0\7\34\1\0" +
			"\7\34\1\0\7\34\1\0\7\34\1\0\7\34\1\0\40\55\57\0" +
			"\1\34\u01d5\0\3\34\31\0\11\34\6\55\1\0\5\34\2\0\5\34" +
			"\4\0\126\34\2\0\2\55\2\0\3\34\1\0\132\34\1\0\4\34" +
			"\5\0\51\34\3\0\136\34\21\0\33\34\65\0\20\34\u0200\0\u19b6\34" +
			"\112\0\u51cd\34\63\0\u048d\34\103\0\56\34\2\0\u010d\34\3\0\20\34" +
			"\12\61\2\34\24\0\57\34\1\55\4\0\12\55\1\0\31\34\7\0" +
			"\1\55\120\34\2\55\45\0\11\34\2\0\147\34\2\0\4\34\1\0" +
			"\4\34\14\0\13\34\115\0\12\34\1\55\3\34\1\55\4\34\1\55" +
			"\27\34\5\55\20\0\1\34\7\0\64\34\14\0\2\55\62\34\21\55" +
			"\13\0\12\61\6\0\22\55\6\34\3\0\1\34\4\0\12\61\34\34" +
			"\10\55\2\0\27\34\15\55\14\0\35\34\3\0\4\55\57\34\16\55" +
			"\16\0\1\34\12\61\46\0\51\34\16\55\11\0\3\34\1\55\10\34" +
			"\2\55\2\0\12\61\6\0\27\34\3\0\1\34\1\55\4\0\60\34" +
			"\1\55\1\34\3\55\2\34\2\55\5\34\2\55\1\34\1\55\1\34" +
			"\30\0\3\34\2\0\13\34\5\55\2\0\3\34\2\55\12\0\6\34" +
			"\2\0\6\34\2\0\6\34\11\0\7\34\1\0\7\34\221\0\43\34" +
			"\10\55\1\0\2\55\2\0\12\61\6\0\u2ba4\34\14\0\27\34\4\0" +
			"\61\34\u2104\0\u016e\34\2\0\152\34\46\0\7\34\14\0\5\34\5\0" +
			"\1\34\1\55\12\34\1\0\15\34\1\0\5\34\1\0\1\34\1\0" +
			"\2\34\1\0\2\34\1\0\154\34\41\0\u016b\34\22\0\100\34\2\0" +
			"\66\34\50\0\15\34\3\0\20\55\20\0\7\55\14\0\2\34\30\0" +
			"\3\34\31\0\1\34\6\0\5\34\1\0\207\34\2\0\1\55\4\0" +
			"\1\34\13\0\12\61\7\0\32\34\4\0\1\34\1\0\32\34\13\0" +
			"\131\34\3\0\6\34\2\0\6\34\2\0\6\34\2\0\3\34\3\0" +
			"\2\34\3\0\2\34\22\0\3\55\4\0";

	/**
	 * Translates characters to character classes
	 */
	private static final char[] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

	/**
	 * Translates DFA states to action switch labels.
	 */
	private static final int[] ZZ_ACTION = zzUnpackAction();

	private static final String ZZ_ACTION_PACKED_0 =
		"\5\0\1\1\21\2\1\3\1\4\1\5\1\6\1\7" +
			"\1\10\1\11\1\12\1\1\1\13\1\14\1\15\1\16" +
			"\1\17\1\20\1\21\1\1\1\22\1\1\1\23\1\24" +
			"\1\23\1\25\1\26\2\25\1\27\3\25\5\2\1\30" +
			"\14\2\1\31\1\32\13\2\3\0\1\33\1\0\1\34" +
			"\1\35\2\0\1\26\1\27\1\36\1\2\1\37\1\2" +
			"\1\40\1\2\1\41\6\2\1\42\4\2\1\43\14\2" +
			"\1\44\2\45\2\0\1\46\1\47\1\50\7\2\1\51" +
			"\1\52\1\53\3\2\1\54\2\2\1\55\1\56\7\2" +
			"\1\0\1\45\1\0\5\2\1\57\14\2\1\60\1\0" +
			"\1\50\1\61\5\2\1\62\1\63\11\2\1\64\1\2" +
			"\1\65\2\2\1\66\2\2\1\67\2\2\1\70\1\2" +
			"\1\71\1\72\1\73\1\74\1\75\1\76\2\2\1\77" +
			"\1\100\1\101";

	private static int[] zzUnpackAction() {
		int[] result = new int[224];
		int offset = 0;
		offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
		return result;
	}

	private static int zzUnpackAction(String packed, int offset, int[] result) {
		int i = 0;       /* index in packed string  */
		int j = offset;  /* index in unpacked array */
		int l = packed.length();
		while (i < l) {
			int count = packed.charAt(i++);
			int value = packed.charAt(i++);
			do result[j++] = value; while (--count > 0);
		}
		return j;
	}


	/**
	 * Translates a state to a row index in the transition table
	 */
	private static final int[] ZZ_ROWMAP = zzUnpackRowMap();

	private static final String ZZ_ROWMAP_PACKED_0 =
		"\0\0\0\64\0\150\0\234\0\320\0\u0104\0\u0138\0\u016c" +
			"\0\u01a0\0\u01d4\0\u0208\0\u023c\0\u0270\0\u02a4\0\u02d8\0\u030c" +
			"\0\u0340\0\u0374\0\u03a8\0\u03dc\0\u0410\0\u0444\0\u0478\0\u0104" +
			"\0\u0104\0\u0104\0\u0104\0\u0104\0\u0104\0\u04ac\0\u04e0\0\u0514" +
			"\0\u0104\0\u0104\0\u0548\0\u057c\0\u0104\0\u0104\0\u0104\0\u05b0" +
			"\0\u05e4\0\u0618\0\u064c\0\u0680\0\u06b4\0\u0104\0\u0104\0\u06e8" +
			"\0\u071c\0\u0104\0\u0750\0\u0784\0\u07b8\0\u07ec\0\u0820\0\u0854" +
			"\0\u0888\0\u08bc\0\u0270\0\u08f0\0\u0924\0\u0958\0\u098c\0\u09c0" +
			"\0\u09f4\0\u0a28\0\u0a5c\0\u0a90\0\u0ac4\0\u0af8\0\u0b2c\0\u0270" +
			"\0\u0270\0\u0b60\0\u0b94\0\u0bc8\0\u0bfc\0\u0c30\0\u0c64\0\u0c98" +
			"\0\u0ccc\0\u0d00\0\u0d34\0\u0d68\0\u0d9c\0\u0dd0\0\u0e04\0\u0548" +
			"\0\u0e38\0\u0e6c\0\u0ea0\0\u0ed4\0\u0f08\0\u071c\0\u0784\0\u0270" +
			"\0\u0f3c\0\u0270\0\u0f70\0\u0270\0\u0fa4\0\u0270\0\u0fd8\0\u100c" +
			"\0\u1040\0\u1074\0\u10a8\0\u10dc\0\u0270\0\u1110\0\u1144\0\u1178" +
			"\0\u11ac\0\u0270\0\u11e0\0\u1214\0\u1248\0\u127c\0\u12b0\0\u12e4" +
			"\0\u1318\0\u134c\0\u1380\0\u13b4\0\u13e8\0\u141c\0\u0104\0\u0104" +
			"\0\u0dd0\0\u1450\0\u1484\0\u0104\0\u0104\0\u14b8\0\u14ec\0\u1520" +
			"\0\u1554\0\u1588\0\u15bc\0\u15f0\0\u1624\0\u0270\0\u0270\0\u0270" +
			"\0\u1658\0\u168c\0\u16c0\0\u0270\0\u16f4\0\u1728\0\u0270\0\u0270" +
			"\0\u175c\0\u1790\0\u17c4\0\u17f8\0\u182c\0\u1860\0\u1894\0\u18c8" +
			"\0\u1450\0\u18fc\0\u1930\0\u1964\0\u1998\0\u19cc\0\u1a00\0\u0270" +
			"\0\u1a34\0\u1a68\0\u1a9c\0\u1ad0\0\u1b04\0\u1b38\0\u1b6c\0\u1ba0" +
			"\0\u1bd4\0\u1c08\0\u1c3c\0\u1c70\0\u0270\0\u1ca4\0\u1ca4\0\u0270" +
			"\0\u1cd8\0\u1d0c\0\u1d40\0\u1d74\0\u1da8\0\u0270\0\u0270\0\u1ddc" +
			"\0\u1e10\0\u1e44\0\u1e78\0\u1eac\0\u1ee0\0\u1f14\0\u1f48\0\u1f7c" +
			"\0\u0270\0\u1fb0\0\u0270\0\u1fe4\0\u2018\0\u0270\0\u204c\0\u2080" +
			"\0\u0270\0\u20b4\0\u20e8\0\u0270\0\u211c\0\u0270\0\u0270\0\u0270" +
			"\0\u0270\0\u0270\0\u0270\0\u2150\0\u2184\0\u0270\0\u0270\0\u0270";

	private static int[] zzUnpackRowMap() {
		int[] result = new int[224];
		int offset = 0;
		offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
		return result;
	}

	private static int zzUnpackRowMap(String packed, int offset, int[] result) {
		int i = 0;  /* index in packed string  */
		int j = offset;  /* index in unpacked array */
		int l = packed.length();
		while (i < l) {
			int high = packed.charAt(i++) << 16;
			result[j++] = high | packed.charAt(i++);
		}
		return j;
	}

	/**
	 * The transition table of the DFA
	 */
	private static final int[] ZZ_TRANS = zzUnpackTrans();

	private static final String ZZ_TRANS_PACKED_0 =
		"\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1\15" +
			"\1\16\1\15\1\17\1\15\1\20\1\21\1\22\1\23" +
			"\1\24\1\15\1\25\1\15\1\26\1\27\1\30\1\31" +
			"\1\32\1\33\1\34\1\35\1\15\1\36\1\37\1\6" +
			"\1\40\1\41\1\42\1\43\1\44\1\45\1\46\1\47" +
			"\1\50\1\51\3\15\1\6\1\52\1\53\1\6\1\54" +
			"\1\55\1\6\46\56\1\57\14\56\1\60\43\56\1\61" +
			"\17\56\1\60\47\56\1\62\13\56\1\63\50\56\1\64" +
			"\12\56\1\65\65\0\1\15\1\66\5\15\1\67\15\15" +
			"\6\0\1\15\2\0\2\36\7\0\1\15\1\0\4\15" +
			"\3\0\1\15\3\0\1\70\24\15\6\0\1\15\2\0" +
			"\2\36\7\0\1\15\1\0\4\15\3\0\1\15\3\0" +
			"\13\15\1\71\11\15\6\0\1\15\2\0\2\36\7\0" +
			"\1\15\1\0\4\15\3\0\1\15\3\0\4\15\1\72" +
			"\20\15\6\0\1\15\2\0\2\36\7\0\1\15\1\0" +
			"\4\15\3\0\1\15\3\0\1\73\1\15\1\74\5\15" +
			"\1\75\14\15\6\0\1\15\2\0\2\36\7\0\1\15" +
			"\1\0\4\15\3\0\1\15\3\0\6\15\1\76\1\15" +
			"\1\77\11\15\1\100\2\15\6\0\1\15\2\0\2\36" +
			"\7\0\1\15\1\0\4\15\3\0\1\15\3\0\25\15" +
			"\6\0\1\15\2\0\2\36\7\0\1\15\1\0\4\15" +
			"\3\0\1\15\3\0\5\15\1\101\6\15\1\102\1\15" +
			"\1\103\6\15\6\0\1\15\2\0\2\36\7\0\1\15" +
			"\1\0\4\15\3\0\1\15\3\0\1\104\3\15\1\105" +
			"\6\15\1\106\11\15\6\0\1\15\2\0\2\36\7\0" +
			"\1\15\1\0\4\15\3\0\1\15\3\0\2\15\1\107" +
			"\5\15\1\110\14\15\6\0\1\15\2\0\2\36\7\0" +
			"\1\15\1\0\4\15\3\0\1\15\3\0\1\111\7\15" +
			"\1\112\14\15\6\0\1\15\2\0\2\36\7\0\1\15" +
			"\1\0\4\15\3\0\1\15\3\0\4\15\1\113\20\15" +
			"\6\0\1\15\2\0\2\36\7\0\1\15\1\0\4\15" +
			"\3\0\1\15\3\0\5\15\1\114\17\15\6\0\1\15" +
			"\2\0\2\36\7\0\1\15\1\0\4\15\3\0\1\15" +
			"\3\0\13\15\1\115\1\116\10\15\6\0\1\15\2\0" +
			"\2\36\7\0\1\15\1\0\4\15\3\0\1\15\3\0" +
			"\13\15\1\117\11\15\6\0\1\15\2\0\2\36\7\0" +
			"\1\15\1\0\4\15\3\0\1\15\3\0\16\15\1\120" +
			"\6\15\6\0\1\15\2\0\2\36\7\0\1\15\1\0" +
			"\4\15\3\0\1\15\3\0\1\15\1\121\2\15\1\122" +
			"\1\123\6\15\1\124\10\15\6\0\1\15\2\0\2\36" +
			"\7\0\1\15\1\0\4\15\3\0\1\15\3\0\25\36" +
			"\6\0\1\36\2\0\2\36\7\0\1\36\1\0\4\36" +
			"\3\0\1\36\40\0\1\125\65\0\1\126\3\0\1\127" +
			"\62\0\1\130\21\0\25\131\6\0\1\131\15\0\4\131" +
			"\3\0\1\131\52\0\1\132\10\0\1\133\63\0\1\54" +
			"\60\0\1\134\64\0\1\53\42\0\1\135\22\0\1\54" +
			"\64\0\1\55\11\0\2\56\5\0\1\56\26\0\1\56" +
			"\60\0\1\136\30\0\2\56\5\0\1\56\27\0\1\56" +
			"\64\0\1\137\23\0\2\56\5\0\1\56\45\0\2\15" +
			"\1\140\22\15\6\0\1\15\2\0\2\36\7\0\1\15" +
			"\1\0\4\15\3\0\1\15\3\0\16\15\1\141\6\15" +
			"\6\0\1\15\2\0\2\36\7\0\1\15\1\0\4\15" +
			"\3\0\1\15\3\0\5\15\1\142\17\15\6\0\1\15" +
			"\2\0\2\36\7\0\1\15\1\0\4\15\3\0\1\15" +
			"\3\0\13\15\1\143\11\15\6\0\1\15\2\0\2\36" +
			"\7\0\1\15\1\0\4\15\3\0\1\15\3\0\1\144" +
			"\24\15\6\0\1\15\2\0\2\36\7\0\1\15\1\0" +
			"\4\15\3\0\1\15\3\0\1\145\24\15\6\0\1\15" +
			"\2\0\2\36\7\0\1\15\1\0\4\15\3\0\1\15" +
			"\3\0\20\15\1\146\4\15\6\0\1\15\2\0\2\36" +
			"\7\0\1\15\1\0\4\15\3\0\1\15\3\0\7\15" +
			"\1\147\15\15\6\0\1\15\2\0\2\36\7\0\1\15" +
			"\1\0\4\15\3\0\1\15\3\0\21\15\1\150\3\15" +
			"\6\0\1\15\2\0\2\36\7\0\1\15\1\0\4\15" +
			"\3\0\1\15\3\0\23\15\1\151\1\15\6\0\1\15" +
			"\2\0\2\36\7\0\1\15\1\0\4\15\3\0\1\15" +
			"\3\0\16\15\1\152\6\15\6\0\1\15\2\0\2\36" +
			"\7\0\1\15\1\0\4\15\3\0\1\15\3\0\22\15" +
			"\1\153\2\15\6\0\1\15\2\0\2\36\7\0\1\15" +
			"\1\0\4\15\3\0\1\15\3\0\1\15\1\154\23\15" +
			"\6\0\1\15\2\0\2\36\7\0\1\15\1\0\4\15" +
			"\3\0\1\15\3\0\12\15\1\155\12\15\6\0\1\15" +
			"\2\0\2\36\7\0\1\15\1\0\4\15\3\0\1\15" +
			"\3\0\7\15\1\156\15\15\6\0\1\15\2\0\2\36" +
			"\7\0\1\15\1\0\4\15\3\0\1\15\3\0\1\15" +
			"\1\157\23\15\6\0\1\15\2\0\2\36\7\0\1\15" +
			"\1\0\4\15\3\0\1\15\3\0\25\15\6\0\1\15" +
			"\2\0\2\36\7\0\1\15\1\0\1\15\1\160\2\15" +
			"\3\0\1\15\3\0\7\15\1\161\15\15\6\0\1\15" +
			"\2\0\2\36\7\0\1\15\1\0\4\15\3\0\1\15" +
			"\3\0\16\15\1\162\6\15\6\0\1\15\2\0\2\36" +
			"\7\0\1\15\1\0\4\15\3\0\1\15\3\0\1\163" +
			"\3\15\1\164\20\15\6\0\1\15\2\0\2\36\7\0" +
			"\1\15\1\0\4\15\3\0\1\15\3\0\16\15\1\165" +
			"\6\15\6\0\1\15\2\0\2\36\7\0\1\15\1\0" +
			"\4\15\3\0\1\15\3\0\7\15\1\166\15\15\6\0" +
			"\1\15\2\0\2\36\7\0\1\15\1\0\4\15\3\0" +
			"\1\15\3\0\10\15\1\167\11\15\1\170\2\15\6\0" +
			"\1\15\2\0\2\36\7\0\1\15\1\0\4\15\3\0" +
			"\1\15\3\0\13\15\1\171\1\172\10\15\6\0\1\15" +
			"\2\0\2\36\7\0\1\15\1\0\4\15\3\0\1\15" +
			"\3\0\10\15\1\173\14\15\6\0\1\15\2\0\2\36" +
			"\7\0\1\15\1\0\4\15\3\0\1\15\3\0\12\15" +
			"\1\174\12\15\6\0\1\15\2\0\2\36\7\0\1\15" +
			"\1\0\4\15\3\0\1\15\3\0\4\15\1\175\20\15" +
			"\6\0\1\15\2\0\2\36\7\0\1\15\1\0\4\15" +
			"\3\0\1\15\3\0\10\15\1\176\14\15\6\0\1\15" +
			"\2\0\2\36\7\0\1\15\1\0\4\15\3\0\1\15" +
			"\40\0\1\177\25\0\57\126\1\200\1\201\3\126\44\202" +
			"\1\203\17\202\1\0\25\131\6\0\1\131\7\0\1\204" +
			"\5\0\4\131\3\0\1\131\52\0\1\132\51\0\1\135" +
			"\22\0\1\133\2\0\57\134\1\205\4\134\61\0\1\206" +
			"\3\0\14\15\1\207\10\15\6\0\1\15\2\0\2\36" +
			"\7\0\1\15\1\0\4\15\3\0\1\15\3\0\12\15" +
			"\1\210\12\15\6\0\1\15\2\0\2\36\7\0\1\15" +
			"\1\0\4\15\3\0\1\15\3\0\7\15\1\211\15\15" +
			"\6\0\1\15\2\0\2\36\7\0\1\15\1\0\4\15" +
			"\3\0\1\15\3\0\5\15\1\212\17\15\6\0\1\15" +
			"\2\0\2\36\7\0\1\15\1\0\4\15\3\0\1\15" +
			"\3\0\12\15\1\213\12\15\6\0\1\15\2\0\2\36" +
			"\7\0\1\15\1\0\4\15\3\0\1\15\3\0\7\15" +
			"\1\214\15\15\6\0\1\15\2\0\2\36\7\0\1\15" +
			"\1\0\4\15\3\0\1\15\3\0\22\15\1\215\2\15" +
			"\6\0\1\15\2\0\2\36\7\0\1\15\1\0\4\15" +
			"\3\0\1\15\3\0\5\15\1\216\17\15\6\0\1\15" +
			"\2\0\2\36\7\0\1\15\1\0\4\15\3\0\1\15" +
			"\3\0\5\15\1\217\17\15\6\0\1\15\2\0\2\36" +
			"\7\0\1\15\1\0\4\15\3\0\1\15\3\0\5\15" +
			"\1\220\17\15\6\0\1\15\2\0\2\36\7\0\1\15" +
			"\1\0\4\15\3\0\1\15\3\0\2\15\1\221\22\15" +
			"\6\0\1\15\2\0\2\36\7\0\1\15\1\0\4\15" +
			"\3\0\1\15\3\0\5\15\1\222\17\15\6\0\1\15" +
			"\2\0\2\36\7\0\1\15\1\0\4\15\3\0\1\15" +
			"\3\0\5\15\1\223\5\15\1\224\11\15\6\0\1\15" +
			"\2\0\2\36\7\0\1\15\1\0\4\15\3\0\1\15" +
			"\3\0\13\15\1\225\11\15\6\0\1\15\2\0\2\36" +
			"\7\0\1\15\1\0\4\15\3\0\1\15\3\0\21\15" +
			"\1\226\3\15\6\0\1\15\2\0\2\36\7\0\1\15" +
			"\1\0\4\15\3\0\1\15\3\0\11\15\1\227\13\15" +
			"\6\0\1\15\2\0\2\36\7\0\1\15\1\0\4\15" +
			"\3\0\1\15\3\0\3\15\1\230\21\15\6\0\1\15" +
			"\2\0\2\36\7\0\1\15\1\0\4\15\3\0\1\15" +
			"\3\0\21\15\1\231\3\15\6\0\1\15\2\0\2\36" +
			"\7\0\1\15\1\0\4\15\3\0\1\15\3\0\23\15" +
			"\1\232\1\15\6\0\1\15\2\0\2\36\7\0\1\15" +
			"\1\0\4\15\3\0\1\15\3\0\7\15\1\233\15\15" +
			"\6\0\1\15\2\0\2\36\7\0\1\15\1\0\4\15" +
			"\3\0\1\15\3\0\15\15\1\234\7\15\6\0\1\15" +
			"\2\0\2\36\7\0\1\15\1\0\4\15\3\0\1\15" +
			"\3\0\21\15\1\235\3\15\6\0\1\15\2\0\2\36" +
			"\7\0\1\15\1\0\4\15\3\0\1\15\3\0\1\154" +
			"\24\15\6\0\1\15\2\0\2\36\7\0\1\15\1\0" +
			"\4\15\3\0\1\15\3\0\7\15\1\236\15\15\6\0" +
			"\1\15\2\0\2\36\7\0\1\15\1\0\4\15\3\0" +
			"\1\15\3\0\4\15\1\237\20\15\6\0\1\15\2\0" +
			"\2\36\7\0\1\15\1\0\4\15\3\0\1\15\2\0" +
			"\44\202\1\240\57\202\1\241\3\202\1\240\17\202\54\0" +
			"\1\242\4\0\1\206\3\0\10\15\1\243\14\15\6\0" +
			"\1\15\2\0\2\36\7\0\1\15\1\0\4\15\3\0" +
			"\1\15\3\0\5\15\1\244\17\15\6\0\1\15\2\0" +
			"\2\36\7\0\1\15\1\0\4\15\3\0\1\15\3\0" +
			"\16\15\1\245\6\15\6\0\1\15\2\0\2\36\7\0" +
			"\1\15\1\0\4\15\3\0\1\15\3\0\10\15\1\246" +
			"\14\15\6\0\1\15\2\0\2\36\7\0\1\15\1\0" +
			"\4\15\3\0\1\15\3\0\13\15\1\247\11\15\6\0" +
			"\1\15\2\0\2\36\7\0\1\15\1\0\4\15\3\0" +
			"\1\15\3\0\20\15\1\250\4\15\6\0\1\15\2\0" +
			"\2\36\7\0\1\15\1\0\4\15\3\0\1\15\3\0" +
			"\14\15\1\251\10\15\6\0\1\15\2\0\2\36\7\0" +
			"\1\15\1\0\4\15\3\0\1\15\3\0\12\15\1\252" +
			"\12\15\6\0\1\15\2\0\2\36\7\0\1\15\1\0" +
			"\4\15\3\0\1\15\3\0\21\15\1\253\3\15\6\0" +
			"\1\15\2\0\2\36\7\0\1\15\1\0\4\15\3\0" +
			"\1\15\3\0\25\15\6\0\1\15\2\0\2\36\7\0" +
			"\1\15\1\0\1\254\3\15\3\0\1\15\3\0\1\15" +
			"\1\255\23\15\6\0\1\15\2\0\2\36\7\0\1\15" +
			"\1\0\4\15\3\0\1\15\3\0\7\15\1\256\15\15" +
			"\6\0\1\15\2\0\2\36\7\0\1\15\1\0\4\15" +
			"\3\0\1\15\3\0\5\15\1\257\17\15\6\0\1\15" +
			"\2\0\2\36\7\0\1\15\1\0\4\15\3\0\1\15" +
			"\3\0\13\15\1\260\11\15\6\0\1\15\2\0\2\36" +
			"\7\0\1\15\1\0\4\15\3\0\1\15\3\0\13\15" +
			"\1\261\11\15\6\0\1\15\2\0\2\36\7\0\1\15" +
			"\1\0\4\15\3\0\1\15\3\0\4\15\1\262\20\15" +
			"\6\0\1\15\2\0\2\36\7\0\1\15\1\0\4\15" +
			"\3\0\1\15\3\0\7\15\1\263\15\15\6\0\1\15" +
			"\2\0\2\36\7\0\1\15\1\0\4\15\3\0\1\15" +
			"\3\0\1\15\1\264\23\15\6\0\1\15\2\0\2\36" +
			"\7\0\1\15\1\0\4\15\3\0\1\15\3\0\12\15" +
			"\1\265\12\15\6\0\1\15\2\0\2\36\7\0\1\15" +
			"\1\0\4\15\3\0\1\15\2\0\40\202\1\200\3\202" +
			"\1\240\17\202\50\0\2\266\7\0\1\267\3\0\25\15" +
			"\6\0\1\15\2\0\2\36\7\0\1\15\1\0\1\270" +
			"\3\15\3\0\1\15\3\0\4\15\1\271\20\15\6\0" +
			"\1\15\2\0\2\36\7\0\1\15\1\0\4\15\3\0" +
			"\1\15\3\0\4\15\1\272\20\15\6\0\1\15\2\0" +
			"\2\36\7\0\1\15\1\0\4\15\3\0\1\15\3\0" +
			"\11\15\1\273\13\15\6\0\1\15\2\0\2\36\7\0" +
			"\1\15\1\0\4\15\3\0\1\15\3\0\1\274\24\15" +
			"\6\0\1\15\2\0\2\36\7\0\1\15\1\0\4\15" +
			"\3\0\1\15\3\0\10\15\1\275\14\15\6\0\1\15" +
			"\2\0\2\36\7\0\1\15\1\0\4\15\3\0\1\15" +
			"\3\0\5\15\1\276\17\15\6\0\1\15\2\0\2\36" +
			"\7\0\1\15\1\0\4\15\3\0\1\15\3\0\7\15" +
			"\1\277\15\15\6\0\1\15\2\0\2\36\7\0\1\15" +
			"\1\0\4\15\3\0\1\15\3\0\5\15\1\300\17\15" +
			"\6\0\1\15\2\0\2\36\7\0\1\15\1\0\4\15" +
			"\3\0\1\15\3\0\16\15\1\301\6\15\6\0\1\15" +
			"\2\0\2\36\7\0\1\15\1\0\4\15\3\0\1\15" +
			"\3\0\14\15\1\302\10\15\6\0\1\15\2\0\2\36" +
			"\7\0\1\15\1\0\4\15\3\0\1\15\3\0\23\15" +
			"\1\303\1\15\6\0\1\15\2\0\2\36\7\0\1\15" +
			"\1\0\4\15\3\0\1\15\3\0\10\15\1\304\14\15" +
			"\6\0\1\15\2\0\2\36\7\0\1\15\1\0\4\15" +
			"\3\0\1\15\3\0\7\15\1\305\15\15\6\0\1\15" +
			"\2\0\2\36\7\0\1\15\1\0\4\15\3\0\1\15" +
			"\3\0\7\15\1\306\15\15\6\0\1\15\2\0\2\36" +
			"\7\0\1\15\1\0\4\15\3\0\1\15\3\0\14\15" +
			"\1\307\10\15\6\0\1\15\2\0\2\36\7\0\1\15" +
			"\1\0\4\15\3\0\1\15\3\0\16\15\1\310\6\15" +
			"\6\0\1\15\2\0\2\36\7\0\1\15\1\0\4\15" +
			"\3\0\1\15\63\0\1\267\3\0\10\15\1\311\14\15" +
			"\6\0\1\15\2\0\2\36\7\0\1\15\1\0\4\15" +
			"\3\0\1\15\3\0\21\15\1\312\3\15\6\0\1\15" +
			"\2\0\2\36\7\0\1\15\1\0\4\15\3\0\1\15" +
			"\3\0\1\313\24\15\6\0\1\15\2\0\2\36\7\0" +
			"\1\15\1\0\4\15\3\0\1\15\3\0\5\15\1\314" +
			"\17\15\6\0\1\15\2\0\2\36\7\0\1\15\1\0" +
			"\4\15\3\0\1\15\3\0\4\15\1\315\20\15\6\0" +
			"\1\15\2\0\2\36\7\0\1\15\1\0\4\15\3\0" +
			"\1\15\3\0\16\15\1\316\6\15\6\0\1\15\2\0" +
			"\2\36\7\0\1\15\1\0\4\15\3\0\1\15\3\0" +
			"\21\15\1\317\3\15\6\0\1\15\2\0\2\36\7\0" +
			"\1\15\1\0\4\15\3\0\1\15\3\0\15\15\1\320" +
			"\7\15\6\0\1\15\2\0\2\36\7\0\1\15\1\0" +
			"\4\15\3\0\1\15\3\0\7\15\1\321\15\15\6\0" +
			"\1\15\2\0\2\36\7\0\1\15\1\0\4\15\3\0" +
			"\1\15\3\0\5\15\1\322\17\15\6\0\1\15\2\0" +
			"\2\36\7\0\1\15\1\0\4\15\3\0\1\15\3\0" +
			"\20\15\1\323\4\15\6\0\1\15\2\0\2\36\7\0" +
			"\1\15\1\0\4\15\3\0\1\15\3\0\5\15\1\324" +
			"\17\15\6\0\1\15\2\0\2\36\7\0\1\15\1\0" +
			"\4\15\3\0\1\15\3\0\13\15\1\325\11\15\6\0" +
			"\1\15\2\0\2\36\7\0\1\15\1\0\4\15\3\0" +
			"\1\15\3\0\5\15\1\326\17\15\6\0\1\15\2\0" +
			"\2\36\7\0\1\15\1\0\4\15\3\0\1\15\3\0" +
			"\7\15\1\327\15\15\6\0\1\15\2\0\2\36\7\0" +
			"\1\15\1\0\4\15\3\0\1\15\3\0\11\15\1\330" +
			"\13\15\6\0\1\15\2\0\2\36\7\0\1\15\1\0" +
			"\4\15\3\0\1\15\3\0\12\15\1\331\12\15\6\0" +
			"\1\15\2\0\2\36\7\0\1\15\1\0\4\15\3\0" +
			"\1\15\3\0\5\15\1\332\17\15\6\0\1\15\2\0" +
			"\2\36\7\0\1\15\1\0\4\15\3\0\1\15\3\0" +
			"\5\15\1\333\17\15\6\0\1\15\2\0\2\36\7\0" +
			"\1\15\1\0\4\15\3\0\1\15\3\0\10\15\1\334" +
			"\14\15\6\0\1\15\2\0\2\36\7\0\1\15\1\0" +
			"\4\15\3\0\1\15\3\0\23\15\1\335\1\15\6\0" +
			"\1\15\2\0\2\36\7\0\1\15\1\0\4\15\3\0" +
			"\1\15\3\0\10\15\1\336\14\15\6\0\1\15\2\0" +
			"\2\36\7\0\1\15\1\0\4\15\3\0\1\15\3\0" +
			"\7\15\1\337\15\15\6\0\1\15\2\0\2\36\7\0" +
			"\1\15\1\0\4\15\3\0\1\15\3\0\5\15\1\340" +
			"\17\15\6\0\1\15\2\0\2\36\7\0\1\15\1\0" +
			"\4\15\3\0\1\15\2\0";

	private static int[] zzUnpackTrans() {
		int[] result = new int[8632];
		int offset = 0;
		offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
		return result;
	}

	private static int zzUnpackTrans(String packed, int offset, int[] result) {
		int i = 0;       /* index in packed string  */
		int j = offset;  /* index in unpacked array */
		int l = packed.length();
		while (i < l) {
			int count = packed.charAt(i++);
			int value = packed.charAt(i++);
			value--;
			do result[j++] = value; while (--count > 0);
		}
		return j;
	}


	/* error codes */
	private static final int ZZ_UNKNOWN_ERROR = 0;
	private static final int ZZ_NO_MATCH = 1;
	private static final int ZZ_PUSHBACK_2BIG = 2;
	private static final char[] EMPTY_BUFFER = new char[0];
	private static final int YYEOF = -1;
	private static java.io.Reader zzReader = null; // Fake

	/* error messages for the codes above */
	private static final String ZZ_ERROR_MSG[] = {
		"Unkown internal scanner error",
		"Error: could not match input",
		"Error: pushback value was too large"
	};

	/**
	 * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
	 */
	private static final int[] ZZ_ATTRIBUTE = zzUnpackAttribute();

	private static final String ZZ_ATTRIBUTE_PACKED_0 =
		"\5\0\1\11\21\1\6\11\3\1\2\11\2\1\3\11" +
			"\6\1\2\11\2\1\1\11\42\1\3\0\1\1\1\0" +
			"\2\1\2\0\41\1\2\11\1\1\2\0\2\11\32\1" +
			"\1\0\1\1\1\0\23\1\1\0\52\1";

	private static int[] zzUnpackAttribute() {
		int[] result = new int[224];
		int offset = 0;
		offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
		return result;
	}

	private static int zzUnpackAttribute(String packed, int offset, int[] result) {
		int i = 0;       /* index in packed string  */
		int j = offset;  /* index in unpacked array */
		int l = packed.length();
		while (i < l) {
			int count = packed.charAt(i++);
			int value = packed.charAt(i++);
			do result[j++] = value; while (--count > 0);
		}
		return j;
	}

	/**
	 * the current state of the DFA
	 */
	private int zzState;

	/**
	 * the current lexical state
	 */
	private int zzLexicalState = YYINITIAL;

	/**
	 * this buffer contains the current text to be matched and is
	 * the source of the yytext() string
	 */
	private CharSequence zzBuffer = "";

	/**
	 * this buffer may contains the current text array to be matched when it is cheap to acquire it
	 */
	private char[] zzBufferArray;

	/**
	 * the textposition at the last accepting state
	 */
	private int zzMarkedPos;

	/**
	 * the textposition at the last state to be included in yytext
	 */
	private int zzPushbackPos;

	/**
	 * the current text position in the buffer
	 */
	private int zzCurrentPos;

	/**
	 * startRead marks the beginning of the yytext() string in the buffer
	 */
	private int zzStartRead;

	/**
	 * endRead marks the last character in the buffer, that has been read
	 * from input
	 */
	private int zzEndRead;

	/**
	 * zzAtBOL == true <=> the scanner is currently at the beginning of a line
	 */
	private boolean zzAtBOL = true;

	/**
	 * zzAtEOF == true <=> the scanner is at the EOF
	 */
	private boolean zzAtEOF;

	/* user code: */
	private Set<String> identifiers;
	private Project project;
	private static final String DSL = "dsl";
	private String dsl = null;

	public TaraHighlighterLex(java.io.Reader reader, Project project) {
		this.zzReader = reader;
		this.project = project;
	}

	private IElementType evaluateIdentifier() {
		String identifier = yytext().toString();
		if (identifiers == null) return TaraTypes.IDENTIFIER_KEY;
		return identifiers.contains(identifier) ? TaraTypes.METAIDENTIFIER_KEY : TaraTypes.IDENTIFIER_KEY;
	}

	private void loadHeritage() {
		if (identifiers != null) return;
		if (dsl == null) {
			String source = zzBuffer.toString().trim();
			int nl = source.indexOf('\n');
			String dslLine = nl > 0 ? source.substring(0, nl).trim() : source;
			if (!dslLine.startsWith(DSL) || dslLine.split(DSL).length < 2) return;
			dsl = dslLine.split(DSL)[1].trim();
		}
		identifiers = new HashSet();
		Language heritage = LanguageManager.getLanguage(dsl, false, project);
		if (heritage != null) Collections.addAll(identifiers, heritage.lexicon());
	}


	/**
	 * Creates a new scanner
	 *
	 * @param in the java.io.Reader to read input from.
	 */
	TaraHighlighterLex(java.io.Reader in) {
		this.zzReader = in;
	}


	/**
	 * Unpacks the compressed character translation table.
	 *
	 * @param packed the packed character translation table
	 * @return the unpacked character translation table
	 */
	private static char[] zzUnpackCMap(String packed) {
		char[] map = new char[0x10000];
		int i = 0;  /* index in packed string  */
		int j = 0;  /* index in unpacked array */
		while (i < 2272) {
			int count = packed.charAt(i++);
			char value = packed.charAt(i++);
			do map[j++] = value; while (--count > 0);
		}
		return map;
	}

	public final int getTokenStart() {
		return zzStartRead;
	}

	public final int getTokenEnd() {
		return getTokenStart() + yylength();
	}

	public void reset(CharSequence buffer, int start, int end, int initialState) {
		zzBuffer = buffer;
		zzBufferArray = com.intellij.util.text.CharArrayUtil.fromSequenceWithoutCopying(buffer);
		zzCurrentPos = zzMarkedPos = zzStartRead = start;
		zzPushbackPos = 0;
		zzAtEOF = false;
		zzAtBOL = true;
		zzEndRead = end;
		yybegin(initialState);
	}

	/**
	 * Refills the input buffer.
	 *
	 * @return <code>false</code>, iff there was new input.
	 * @throws java.io.IOException if any I/O-Error occurs
	 */
	private boolean zzRefill() throws java.io.IOException {
		return true;
	}


	/**
	 * Returns the current lexical state.
	 */
	public final int yystate() {
		return zzLexicalState;
	}


	/**
	 * Enters a new lexical state
	 *
	 * @param newState the new lexical state
	 */
	public final void yybegin(int newState) {
		zzLexicalState = newState;
	}


	/**
	 * Returns the text matched by the current regular expression.
	 */
	public final CharSequence yytext() {
		return zzBuffer.subSequence(zzStartRead, zzMarkedPos);
	}


	/**
	 * Returns the character at position <tt>pos</tt> from the
	 * matched text.
	 * <p>
	 * It is equivalent to yytext().charAt(pos), but faster
	 *
	 * @param pos the position of the character to fetch.
	 *            A value from 0 to yylength()-1.
	 * @return the character at position pos
	 */
	public final char yycharat(int pos) {
		return zzBufferArray != null ? zzBufferArray[zzStartRead + pos] : zzBuffer.charAt(zzStartRead + pos);
	}


	/**
	 * Returns the length of the matched text region.
	 */
	public final int yylength() {
		return zzMarkedPos - zzStartRead;
	}


	/**
	 * Reports an error that occured while scanning.
	 * <p>
	 * In a wellformed scanner (no or only correct usage of
	 * yypushback(int) and a match-all fallback rule) this method
	 * will only be called with things that "Can't Possibly Happen".
	 * If this method is called, something is seriously wrong
	 * (e.g. a JFlex bug producing a faulty scanner etc.).
	 * <p>
	 * Usual syntax/scanner level error handling should be done
	 * in error fallback rules.
	 *
	 * @param errorCode the code of the errormessage to display
	 */
	private void zzScanError(int errorCode) {
		String message;
		try {
			message = ZZ_ERROR_MSG[errorCode];
		} catch (ArrayIndexOutOfBoundsException e) {
			message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
		}
	}


	/**
	 * Pushes the specified amount of characters back into the input stream.
	 * <p>
	 * They will be read again by then next call of the scanning method
	 *
	 * @param number the number of characters to be read again.
	 *               This number must not be greater than yylength()!
	 */
	public void yypushback(int number) {
		if (number > yylength())
			zzScanError(ZZ_PUSHBACK_2BIG);

		zzMarkedPos -= number;
	}


	/**
	 * Resumes scanning until the next regular expression is matched,
	 * the end of input is encountered or an I/O-Error occurs.
	 *
	 * @return the next token
	 * @throws java.io.IOException if any I/O-Error occurs
	 */
	public IElementType advance() throws java.io.IOException {
		int zzInput;
		int zzAction;

		// cached fields:
		int zzCurrentPosL;
		int zzMarkedPosL;
		int zzEndReadL = zzEndRead;
		CharSequence zzBufferL = zzBuffer;
		char[] zzBufferArrayL = zzBufferArray;
		char[] zzCMapL = ZZ_CMAP;

		int[] zzTransL = ZZ_TRANS;
		int[] zzRowMapL = ZZ_ROWMAP;
		int[] zzAttrL = ZZ_ATTRIBUTE;

		while (true) {
			zzMarkedPosL = zzMarkedPos;

			zzAction = -1;

			zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;

			zzState = ZZ_LEXSTATE[zzLexicalState];


			zzForAction:
			{
				while (true) {

					if (zzCurrentPosL < zzEndReadL)
						zzInput = (zzBufferArrayL != null ? zzBufferArrayL[zzCurrentPosL++] : zzBufferL.charAt(zzCurrentPosL++));
					else if (zzAtEOF) {
						zzInput = YYEOF;
						break zzForAction;
					} else {
						// store back cached positions
						zzCurrentPos = zzCurrentPosL;
						zzMarkedPos = zzMarkedPosL;
						boolean eof = zzRefill();
						// get translated positions and possibly new buffer
						zzCurrentPosL = zzCurrentPos;
						zzMarkedPosL = zzMarkedPos;
						zzBufferL = zzBuffer;
						zzEndReadL = zzEndRead;
						if (eof) {
							zzInput = YYEOF;
							break zzForAction;
						} else {
							zzInput = (zzBufferArrayL != null ? zzBufferArrayL[zzCurrentPosL++] : zzBufferL.charAt(zzCurrentPosL++));
						}
					}
					int zzNext = zzTransL[zzRowMapL[zzState] + zzCMapL[zzInput]];
					if (zzNext == -1) break zzForAction;
					zzState = zzNext;

					int zzAttributes = zzAttrL[zzState];
					if ((zzAttributes & 1) == 1) {
						zzAction = zzState;
						zzMarkedPosL = zzCurrentPosL;
						if ((zzAttributes & 8) == 8) break zzForAction;
					}

				}
			}

			// store back cached position
			zzMarkedPos = zzMarkedPosL;

			switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
				case 5: {
					return TaraTypes.LEFT_SQUARE;
				}
				case 66:
					break;
				case 46: {
					return TaraTypes.WITH;
				}
				case 67:
					break;
				case 35: {
					return TaraTypes.VAR;
				}
				case 68:
					break;
				case 12: {
					return TaraTypes.COLON;
				}
				case 69:
					break;
				case 58: {
					return TaraTypes.ABSTRACT;
				}
				case 70:
					break;
				case 62: {
					return TaraTypes.REACTIVE;
				}
				case 71:
					break;
				case 22: {
					yybegin(YYINITIAL);
					return TaraTypes.QUOTE_END;
				}
				case 72:
					break;
				case 51: {
					return TaraTypes.OBJECT_TYPE;
				}
				case 73:
					break;
				case 64: {
					return TaraTypes.COMPONENT;
				}
				case 74:
					break;
				case 8: {
					return TaraTypes.RIGHT_CURLY;
				}
				case 75:
					break;
				case 40: {
					return TaraTypes.DOUBLE_VALUE_KEY;
				}
				case 76:
					break;
				case 45: {
					return TaraTypes.WORD_TYPE;
				}
				case 77:
					break;
				case 23: {
					yybegin(YYINITIAL);
					return TaraTypes.EXPRESSION_END;
				}
				case 78:
					break;
				case 19: {
					return TokenType.WHITE_SPACE;
				}
				case 79:
					break;
				case 36: {
					return TaraTypes.LIST;
				}
				case 80:
					break;
				case 4: {
					return TaraTypes.RIGHT_PARENTHESIS;
				}
				case 81:
					break;
				case 17: {
					yybegin(EXPRESSION);
					return TaraTypes.EXPRESSION_BEGIN;
				}
				case 82:
					break;
				case 13: {
					return TaraTypes.EQUALS;
				}
				case 83:
					break;
				case 48: {
					return TaraTypes.FINAL;
				}
				case 84:
					break;
				case 61: {
					return TaraTypes.RESOURCE_TYPE;
				}
				case 85:
					break;
				case 10: {
					return TaraTypes.DOT;
				}
				case 86:
					break;
				case 49: {
					return TaraTypes.STRING_TYPE;
				}
				case 87:
					break;
				case 60: {
					return TaraTypes.TERMINAL;
				}
				case 88:
					break;
				case 37: {
					return TaraTypes.COMMENT;
				}
				case 89:
					break;
				case 55: {
					return TaraTypes.CONCEPT;
				}
				case 90:
					break;
				case 43: {
					return TaraTypes.DATE_TYPE;
				}
				case 91:
					break;
				case 47: {
					return TaraTypes.EMPTY_REF;
				}
				case 92:
					break;
				case 3: {
					return TaraTypes.LEFT_PARENTHESIS;
				}
				case 93:
					break;
				case 18: {
					return TaraTypes.PLUS;
				}
				case 94:
					break;
				case 56: {
					return TaraTypes.PRIVATE;
				}
				case 95:
					break;
				case 28: {
					yybegin(EXPRESSION_MULTILINE);
					return TaraTypes.EXPRESSION_BEGIN;
				}
				case 96:
					break;
				case 57: {
					return TaraTypes.FEATURE;
				}
				case 97:
					break;
				case 29: {
					return TaraTypes.NEGATIVE_VALUE_KEY;
				}
				case 98:
					break;
				case 15: {
					return TaraTypes.DSL;
				}
				case 99:
					break;
				case 9: {
					return TaraTypes.METRIC_VALUE_KEY;
				}
				case 100:
					break;
				case 53: {
					return TaraTypes.EXTENDS;
				}
				case 101:
					break;
				case 27: {
					yybegin(MULTILINE);
					return TaraTypes.QUOTE_BEGIN;
				}
				case 102:
					break;
				case 65: {
					return TaraTypes.PROTOTYPE;
				}
				case 103:
					break;
				case 2: {
					return evaluateIdentifier();
				}
				case 104:
					break;
				case 14: {
					return TaraTypes.STAR;
				}
				case 105:
					break;
				case 50: {
					return TaraTypes.DOUBLE_TYPE;
				}
				case 106:
					break;
				case 7: {
					return TaraTypes.LEFT_CURLY;
				}
				case 107:
					break;
				case 6: {
					return TaraTypes.RIGHT_SQUARE;
				}
				case 108:
					break;
				case 20: {
					return TaraTypes.NATURAL_VALUE_KEY;
				}
				case 109:
					break;
				case 41: {
					return TaraTypes.TIME_TYPE;
				}
				case 110:
					break;
				case 33: {
					return TaraTypes.ANY;
				}
				case 111:
					break;
				case 1: {
					return TokenType.BAD_CHARACTER;
				}
				case 112:
					break;
				case 44: {
					return TaraTypes.INTO;
				}
				case 113:
					break;
				case 39: {
					yypushback(1);
					return TaraTypes.DOC_LINE;
				}
				case 114:
					break;
				case 42: {
					return TaraTypes.BOOLEAN_VALUE_KEY;
				}
				case 115:
					break;
				case 24: {
					return TaraTypes.AS;
				}
				case 116:
					break;
				case 25: {
					return TaraTypes.ON;
				}
				case 117:
					break;
				case 30: {
					return TaraTypes.SUB;
				}
				case 118:
					break;
				case 38: {
					return TaraTypes.ANCHOR_VALUE;
				}
				case 119:
					break;
				case 59: {
					return TaraTypes.ENCLOSED;
				}
				case 120:
					break;
				case 16: {
					yybegin(QUOTED);
					return TaraTypes.QUOTE_BEGIN;
				}
				case 121:
					break;
				case 52: {
					return TaraTypes.BOOLEAN_TYPE;
				}
				case 122:
					break;
				case 34: {
					loadHeritage();
					return TaraTypes.DSL;
				}
				case 123:
					break;
				case 26: {
					return TaraTypes.IS;
				}
				case 124:
					break;
				case 31: {
					return TaraTypes.USE;
				}
				case 125:
					break;
				case 54: {
					return TaraTypes.INT_TYPE;
				}
				case 126:
					break;
				case 63: {
					return TaraTypes.FUNCTION_TYPE;
				}
				case 127:
					break;
				case 21: {
					return TaraTypes.CHARACTER;
				}
				case 128:
					break;
				case 11: {
					return TaraTypes.COMMA;
				}
				case 129:
					break;
				case 32: {
					return TaraTypes.HAS;
				}
				case 130:
					break;
				default:
					if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
						zzAtEOF = true;
						return null;
					} else {
						zzScanError(ZZ_NO_MATCH);
					}
			}
		}
	}


}
