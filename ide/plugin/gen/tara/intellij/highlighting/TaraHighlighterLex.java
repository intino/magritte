/* The following code was generated by JFlex 1.4.3 on 5/01/16 9:06 */

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
 * on 5/01/16 9:06 from the specification file
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
			"\11\54\1\61\1\56\2\0\1\57\16\54\4\0\1\61\1\55\1\46" +
					"\1\0\1\34\1\35\1\0\1\47\1\26\1\27\1\44\1\51\1\41" +
					"\1\50\1\36\1\40\12\60\1\42\1\45\1\0\1\43\1\61\2\0" +
					"\4\34\1\53\25\34\1\30\1\62\1\31\1\0\1\34\1\0\1\5" +
					"\1\3\1\22\1\12\1\6\1\25\1\52\1\4\1\15\2\34\1\13" +
					"\1\23\1\11\1\14\1\24\1\34\1\17\1\1\1\10\1\2\1\16" +
					"\1\20\1\7\1\21\1\34\1\32\1\0\1\33\1\0\41\54\2\0" +
					"\4\34\4\0\1\34\2\0\1\54\2\0\1\35\4\0\1\34\1\0" +
					"\1\37\2\0\1\34\5\0\27\34\1\0\37\34\1\0\u01ca\34\4\0" +
					"\14\34\16\0\5\34\7\0\1\34\1\0\1\34\21\0\160\54\5\34" +
					"\1\0\2\34\2\0\4\34\10\0\1\34\1\0\3\34\1\0\1\34" +
					"\1\0\24\34\1\0\123\34\1\0\213\34\1\0\5\54\2\0\236\34" +
					"\11\0\46\34\2\0\1\34\7\0\47\34\7\0\1\34\1\0\55\54" +
					"\1\0\1\54\1\0\2\54\1\0\2\54\1\0\1\54\10\0\33\34" +
					"\5\0\3\34\15\0\5\54\6\0\1\34\4\0\13\54\5\0\53\34" +
					"\25\54\12\60\4\0\2\34\1\54\143\34\1\0\1\34\10\54\1\0" +
					"\6\54\2\34\2\54\1\0\4\54\2\34\12\60\3\34\2\0\1\34" +
					"\17\0\1\54\1\34\1\54\36\34\33\54\2\0\131\34\13\54\1\34" +
					"\16\0\12\60\41\34\11\54\2\34\4\0\1\34\5\0\26\34\4\54" +
					"\1\34\11\54\1\34\3\54\1\34\5\54\22\0\31\34\3\54\104\0" +
					"\1\34\1\0\13\34\67\0\33\54\1\0\4\54\66\34\3\54\1\34" +
					"\22\54\1\34\7\54\12\34\2\54\2\0\12\60\1\0\7\34\1\0" +
					"\7\34\1\0\3\54\1\0\10\34\2\0\2\34\2\0\26\34\1\0" +
					"\7\34\1\0\1\34\3\0\4\34\2\0\1\54\1\34\7\54\2\0" +
					"\2\54\2\0\3\54\1\34\10\0\1\54\4\0\2\34\1\0\3\34" +
					"\2\54\2\0\12\60\4\34\7\0\1\34\5\0\3\54\1\0\6\34" +
					"\4\0\2\34\2\0\26\34\1\0\7\34\1\0\2\34\1\0\2\34" +
					"\1\0\2\34\2\0\1\54\1\0\5\54\4\0\2\54\2\0\3\54" +
					"\3\0\1\54\7\0\4\34\1\0\1\34\7\0\12\60\2\54\3\34" +
					"\1\54\13\0\3\54\1\0\11\34\1\0\3\34\1\0\26\34\1\0" +
					"\7\34\1\0\2\34\1\0\5\34\2\0\1\54\1\34\10\54\1\0" +
					"\3\54\1\0\3\54\2\0\1\34\17\0\2\34\2\54\2\0\12\60" +
					"\1\0\1\34\17\0\3\54\1\0\10\34\2\0\2\34\2\0\26\34" +
					"\1\0\7\34\1\0\2\34\1\0\5\34\2\0\1\54\1\34\7\54" +
					"\2\0\2\54\2\0\3\54\10\0\2\54\4\0\2\34\1\0\3\34" +
					"\2\54\2\0\12\60\1\0\1\34\20\0\1\54\1\34\1\0\6\34" +
					"\3\0\3\34\1\0\4\34\3\0\2\34\1\0\1\34\1\0\2\34" +
					"\3\0\2\34\3\0\3\34\3\0\14\34\4\0\5\54\3\0\3\54" +
					"\1\0\4\54\2\0\1\34\6\0\1\54\16\0\12\60\11\0\1\34" +
					"\7\0\3\54\1\0\10\34\1\0\3\34\1\0\27\34\1\0\12\34" +
					"\1\0\5\34\3\0\1\34\7\54\1\0\3\54\1\0\4\54\7\0" +
					"\2\54\1\0\2\34\6\0\2\34\2\54\2\0\12\60\22\0\2\54" +
					"\1\0\10\34\1\0\3\34\1\0\27\34\1\0\12\34\1\0\5\34" +
					"\2\0\1\54\1\34\7\54\1\0\3\54\1\0\4\54\7\0\2\54" +
					"\7\0\1\34\1\0\2\34\2\54\2\0\12\60\1\0\2\34\17\0" +
					"\2\54\1\0\10\34\1\0\3\34\1\0\51\34\2\0\1\34\7\54" +
					"\1\0\3\54\1\0\4\54\1\34\10\0\1\54\10\0\2\34\2\54" +
					"\2\0\12\60\12\0\6\34\2\0\2\54\1\0\22\34\3\0\30\34" +
					"\1\0\11\34\1\0\1\34\2\0\7\34\3\0\1\54\4\0\6\54" +
					"\1\0\1\54\1\0\10\54\22\0\2\54\15\0\60\34\1\54\2\34" +
					"\7\54\4\0\10\34\10\54\1\0\12\60\47\0\2\34\1\0\1\34" +
					"\2\0\2\34\1\0\1\34\2\0\1\34\6\0\4\34\1\0\7\34" +
					"\1\0\3\34\1\0\1\34\1\0\1\34\2\0\2\34\1\0\4\34" +
					"\1\54\2\34\6\54\1\0\2\54\1\34\2\0\5\34\1\0\1\34" +
					"\1\0\6\54\2\0\12\60\2\0\4\34\40\0\1\34\27\0\2\54" +
					"\6\0\12\60\13\0\1\54\1\0\1\54\1\0\1\54\4\0\2\54" +
					"\10\34\1\0\44\34\4\0\24\54\1\0\2\54\5\34\13\54\1\0" +
					"\44\54\11\0\1\54\71\0\53\34\24\54\1\34\12\60\6\0\6\34" +
					"\4\54\4\34\3\54\1\34\3\54\2\34\7\54\3\34\4\54\15\34" +
					"\14\54\1\34\1\54\12\60\4\54\2\0\46\34\1\0\1\34\5\0" +
					"\1\34\2\0\53\34\1\0\u014d\34\1\0\4\34\2\0\7\34\1\0" +
					"\1\34\1\0\4\34\2\0\51\34\1\0\4\34\2\0\41\34\1\0" +
					"\4\34\2\0\7\34\1\0\1\34\1\0\4\34\2\0\17\34\1\0" +
					"\71\34\1\0\4\34\2\0\103\34\2\0\3\54\40\0\20\34\20\0" +
					"\125\34\14\0\u026c\34\2\0\21\34\1\0\32\34\5\0\113\34\3\0" +
					"\3\34\17\0\15\34\1\0\4\34\3\54\13\0\22\34\3\54\13\0" +
					"\22\34\2\54\14\0\15\34\1\0\3\34\1\0\2\54\14\0\64\34" +
					"\40\54\3\0\1\34\3\0\2\34\1\54\2\0\12\60\41\0\3\54" +
					"\2\0\12\60\6\0\130\34\10\0\51\34\1\54\1\34\5\0\106\34" +
					"\12\0\35\34\3\0\14\54\4\0\14\54\12\0\12\60\36\34\2\0" +
					"\5\34\13\0\54\34\4\0\21\54\7\34\2\54\6\0\12\60\46\0" +
					"\27\34\5\54\4\0\65\34\12\54\1\0\35\54\2\0\1\54\12\60" +
					"\6\0\12\60\15\0\1\34\130\0\5\54\57\34\21\54\7\34\4\0" +
					"\12\60\21\0\11\54\14\0\3\54\36\34\15\54\2\34\12\60\54\34" +
					"\16\54\14\0\44\34\24\54\10\0\12\60\3\0\3\34\12\60\44\34" +
					"\122\0\3\54\1\0\25\54\4\34\1\54\4\34\3\54\2\34\11\0" +
					"\300\34\47\54\25\0\4\54\u0116\34\2\0\6\34\2\0\46\34\2\0" +
					"\6\34\2\0\10\34\1\0\1\34\1\0\1\34\1\0\1\34\1\0" +
					"\37\34\2\0\65\34\1\0\7\34\1\0\1\34\3\0\3\34\1\0" +
					"\7\34\3\0\4\34\2\0\6\34\4\0\15\34\5\0\3\34\1\0" +
					"\7\34\16\0\5\54\32\0\5\54\20\0\2\34\23\0\1\34\13\0" +
					"\5\54\5\0\6\54\1\0\1\34\15\0\1\34\20\0\15\34\3\0" +
					"\14\34\1\34\16\34\25\0\15\54\4\0\1\54\3\0\14\54\21\0" +
					"\1\34\4\0\1\34\2\0\12\34\1\0\1\34\3\0\5\34\6\0" +
					"\1\34\1\0\1\34\1\0\1\34\1\0\4\34\1\0\13\34\2\0" +
					"\4\34\5\0\5\34\4\0\1\34\21\0\51\34\u0a77\0\57\34\1\0" +
					"\57\34\1\0\205\34\6\0\4\34\3\54\2\34\14\0\46\34\1\0" +
					"\1\34\5\0\1\34\2\0\70\34\7\0\1\34\17\0\1\54\27\34" +
					"\11\0\7\34\1\0\7\34\1\0\7\34\1\0\7\34\1\0\7\34" +
					"\1\0\7\34\1\0\7\34\1\0\7\34\1\0\40\54\57\0\1\34" +
					"\u01d5\0\3\34\31\0\11\34\6\54\1\0\5\34\2\0\5\34\4\0" +
					"\126\34\2\0\2\54\2\0\3\34\1\0\132\34\1\0\4\34\5\0" +
					"\51\34\3\0\136\34\21\0\33\34\65\0\20\34\u0200\0\u19b6\34\112\0" +
					"\u51cd\34\63\0\u048d\34\103\0\56\34\2\0\u010d\34\3\0\20\34\12\60" +
					"\2\34\24\0\57\34\1\54\4\0\12\54\1\0\31\34\7\0\1\54" +
					"\120\34\2\54\45\0\11\34\2\0\147\34\2\0\4\34\1\0\4\34" +
					"\14\0\13\34\115\0\12\34\1\54\3\34\1\54\4\34\1\54\27\34" +
					"\5\54\20\0\1\34\7\0\64\34\14\0\2\54\62\34\21\54\13\0" +
					"\12\60\6\0\22\54\6\34\3\0\1\34\4\0\12\60\34\34\10\54" +
					"\2\0\27\34\15\54\14\0\35\34\3\0\4\54\57\34\16\54\16\0" +
					"\1\34\12\60\46\0\51\34\16\54\11\0\3\34\1\54\10\34\2\54" +
					"\2\0\12\60\6\0\27\34\3\0\1\34\1\54\4\0\60\34\1\54" +
					"\1\34\3\54\2\34\2\54\5\34\2\54\1\34\1\54\1\34\30\0" +
					"\3\34\2\0\13\34\5\54\2\0\3\34\2\54\12\0\6\34\2\0" +
					"\6\34\2\0\6\34\11\0\7\34\1\0\7\34\221\0\43\34\10\54" +
					"\1\0\2\54\2\0\12\60\6\0\u2ba4\34\14\0\27\34\4\0\61\34" +
					"\u2104\0\u016e\34\2\0\152\34\46\0\7\34\14\0\5\34\5\0\1\34" +
					"\1\54\12\34\1\0\15\34\1\0\5\34\1\0\1\34\1\0\2\34" +
					"\1\0\2\34\1\0\154\34\41\0\u016b\34\22\0\100\34\2\0\66\34" +
					"\50\0\15\34\3\0\20\54\20\0\7\54\14\0\2\34\30\0\3\34" +
					"\31\0\1\34\6\0\5\34\1\0\207\34\2\0\1\54\4\0\1\34" +
					"\13\0\12\60\7\0\32\34\4\0\1\34\1\0\32\34\13\0\131\34" +
					"\3\0\6\34\2\0\6\34\2\0\6\34\2\0\3\34\3\0\2\34" +
					"\3\0\2\34\22\0\3\54\4\0";

	/**
	 * Translates characters to character classes
	 */
	private static final char[] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

	/**
	 * Translates DFA states to action switch labels.
	 */
	private static final int[] ZZ_ACTION = zzUnpackAction();

	private static final String ZZ_ACTION_PACKED_0 =
			"\5\0\1\1\22\2\1\3\1\4\1\5\1\6\1\7" +
					"\1\10\1\11\1\12\1\1\1\13\1\14\1\15\1\16" +
					"\1\17\1\20\1\21\3\1\1\22\1\23\1\22\1\24" +
					"\1\25\2\24\1\26\3\24\5\2\1\27\15\2\1\30" +
					"\1\31\13\2\2\0\1\32\1\0\1\33\1\34\2\0" +
					"\1\25\1\26\1\35\1\2\1\36\1\2\1\37\1\2" +
					"\1\40\10\2\1\41\3\2\1\42\13\2\2\43\2\0" +
					"\1\44\1\45\1\46\10\2\1\47\1\50\1\2\1\51" +
					"\2\2\1\52\1\2\1\53\1\54\7\2\1\0\1\43" +
					"\1\0\5\2\1\55\1\56\13\2\1\57\1\0\1\46" +
					"\1\60\5\2\1\61\1\62\10\2\1\63\1\2\1\64" +
					"\2\2\1\65\1\2\1\66\2\2\1\67\1\2\1\70" +
					"\1\71\1\72\1\73\1\74\2\2\1\75\1\76\1\77";

	private static int[] zzUnpackAction() {
		int[] result = new int[221];
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
			"\0\0\0\63\0\146\0\231\0\314\0\377\0\u0132\0\u0165" +
					"\0\u0198\0\u01cb\0\u01fe\0\u0231\0\u0264\0\u0297\0\u02ca\0\u02fd" +
					"\0\u0330\0\u0363\0\u0396\0\u03c9\0\u03fc\0\u042f\0\u0462\0\u0495" +
					"\0\377\0\377\0\377\0\377\0\377\0\377\0\u04c8\0\377" +
					"\0\u04fb\0\377\0\377\0\u052e\0\u0561\0\377\0\377\0\377" +
					"\0\u0594\0\u05c7\0\u05fa\0\u062d\0\u0660\0\u0693\0\377\0\377" +
					"\0\u06c6\0\u06f9\0\377\0\u072c\0\u075f\0\u0792\0\u07c5\0\u07f8" +
					"\0\u082b\0\u085e\0\u0891\0\u0264\0\u08c4\0\u08f7\0\u092a\0\u095d" +
					"\0\u0990\0\u09c3\0\u09f6\0\u0a29\0\u0a5c\0\u0a8f\0\u0ac2\0\u0af5" +
					"\0\u0b28\0\u0264\0\u0264\0\u0b5b\0\u0b8e\0\u0bc1\0\u0bf4\0\u0c27" +
					"\0\u0c5a\0\u0c8d\0\u0cc0\0\u0cf3\0\u0d26\0\u0d59\0\u0d8c\0\u0dbf" +
					"\0\u052e\0\u0df2\0\u0e25\0\u0e58\0\u0e8b\0\u0ebe\0\u06f9\0\u075f" +
					"\0\u0264\0\u0ef1\0\u0264\0\u0f24\0\u0264\0\u0f57\0\u0264\0\u0f8a" +
					"\0\u0fbd\0\u0ff0\0\u1023\0\u1056\0\u1089\0\u10bc\0\u10ef\0\u0264" +
					"\0\u1122\0\u1155\0\u1188\0\u0264\0\u11bb\0\u11ee\0\u1221\0\u1254" +
					"\0\u1287\0\u12ba\0\u12ed\0\u1320\0\u1353\0\u1386\0\u13b9\0\377" +
					"\0\u0d8c\0\u13ec\0\u141f\0\377\0\377\0\u1452\0\u1485\0\u14b8" +
					"\0\u14eb\0\u151e\0\u1551\0\u1584\0\u15b7\0\u15ea\0\u0264\0\u0264" +
					"\0\u161d\0\u0264\0\u1650\0\u1683\0\u0264\0\u16b6\0\u0264\0\u0264" +
					"\0\u16e9\0\u171c\0\u174f\0\u1782\0\u17b5\0\u17e8\0\u181b\0\u184e" +
					"\0\u13ec\0\u1881\0\u18b4\0\u18e7\0\u191a\0\u194d\0\u1980\0\u0264" +
					"\0\u0264\0\u19b3\0\u19e6\0\u1a19\0\u1a4c\0\u1a7f\0\u1ab2\0\u1ae5" +
					"\0\u1b18\0\u1b4b\0\u1b7e\0\u1bb1\0\u0264\0\u1be4\0\u1be4\0\u0264" +
					"\0\u1c17\0\u1c4a\0\u1c7d\0\u1cb0\0\u1ce3\0\u0264\0\u0264\0\u1d16" +
					"\0\u1d49\0\u1d7c\0\u1daf\0\u1de2\0\u1e15\0\u1e48\0\u1e7b\0\u0264" +
					"\0\u1eae\0\u0264\0\u1ee1\0\u1f14\0\u0264\0\u1f47\0\u0264\0\u1f7a" +
					"\0\u1fad\0\u0264\0\u1fe0\0\u0264\0\u0264\0\u0264\0\u0264\0\u0264" +
					"\0\u2013\0\u2046\0\u0264\0\u0264\0\u0264";

	private static int[] zzUnpackRowMap() {
		int[] result = new int[221];
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
					"\1\16\1\17\1\20\1\15\1\21\1\22\1\23\1\24" +
					"\1\25\1\15\1\26\1\15\1\27\1\30\1\31\1\32" +
					"\1\33\1\34\1\35\1\36\1\15\1\37\1\40\1\6" +
					"\1\41\1\42\1\43\1\44\1\45\1\46\1\47\1\50" +
					"\1\51\1\52\2\15\1\6\1\53\1\54\1\6\1\55" +
					"\1\56\1\6\46\57\1\60\13\57\1\61\43\57\1\62" +
					"\16\57\1\61\47\57\1\63\12\57\1\64\50\57\1\65" +
					"\11\57\1\66\64\0\1\15\1\67\5\15\1\70\15\15" +
					"\6\0\1\15\2\0\2\37\7\0\1\15\1\0\3\15" +
					"\3\0\1\15\3\0\1\71\24\15\6\0\1\15\2\0" +
					"\2\37\7\0\1\15\1\0\3\15\3\0\1\15\3\0" +
					"\13\15\1\72\11\15\6\0\1\15\2\0\2\37\7\0" +
					"\1\15\1\0\3\15\3\0\1\15\3\0\4\15\1\73" +
					"\20\15\6\0\1\15\2\0\2\37\7\0\1\15\1\0" +
					"\3\15\3\0\1\15\3\0\1\74\1\15\1\75\5\15" +
					"\1\76\14\15\6\0\1\15\2\0\2\37\7\0\1\15" +
					"\1\0\3\15\3\0\1\15\3\0\6\15\1\77\1\15" +
					"\1\100\11\15\1\101\2\15\6\0\1\15\2\0\2\37" +
					"\7\0\1\15\1\0\3\15\3\0\1\15\3\0\25\15" +
					"\6\0\1\15\2\0\2\37\7\0\1\15\1\0\3\15" +
					"\3\0\1\15\3\0\1\15\1\102\3\15\1\103\6\15" +
					"\1\104\1\15\1\105\6\15\6\0\1\15\2\0\2\37" +
					"\7\0\1\15\1\0\3\15\3\0\1\15\3\0\4\15" +
					"\1\106\20\15\6\0\1\15\2\0\2\37\7\0\1\15" +
					"\1\0\3\15\3\0\1\15\3\0\1\107\3\15\1\110" +
					"\6\15\1\111\11\15\6\0\1\15\2\0\2\37\7\0" +
					"\1\15\1\0\3\15\3\0\1\15\3\0\10\15\1\112" +
					"\14\15\6\0\1\15\2\0\2\37\7\0\1\15\1\0" +
					"\3\15\3\0\1\15\3\0\1\113\7\15\1\114\14\15" +
					"\6\0\1\15\2\0\2\37\7\0\1\15\1\0\3\15" +
					"\3\0\1\15\3\0\4\15\1\115\20\15\6\0\1\15" +
					"\2\0\2\37\7\0\1\15\1\0\3\15\3\0\1\15" +
					"\3\0\5\15\1\116\17\15\6\0\1\15\2\0\2\37" +
					"\7\0\1\15\1\0\3\15\3\0\1\15\3\0\13\15" +
					"\1\117\1\120\10\15\6\0\1\15\2\0\2\37\7\0" +
					"\1\15\1\0\3\15\3\0\1\15\3\0\13\15\1\121" +
					"\11\15\6\0\1\15\2\0\2\37\7\0\1\15\1\0" +
					"\3\15\3\0\1\15\3\0\16\15\1\122\6\15\6\0" +
					"\1\15\2\0\2\37\7\0\1\15\1\0\3\15\3\0" +
					"\1\15\3\0\1\15\1\123\2\15\1\124\1\125\6\15" +
					"\1\126\10\15\6\0\1\15\2\0\2\37\7\0\1\15" +
					"\1\0\3\15\3\0\1\15\3\0\25\37\6\0\1\37" +
					"\2\0\2\37\7\0\1\37\1\0\3\37\3\0\1\37" +
					"\42\0\1\127\3\0\1\130\61\0\1\131\20\0\25\132" +
					"\6\0\1\132\15\0\3\132\3\0\1\132\52\0\1\133" +
					"\7\0\1\134\62\0\1\55\57\0\1\135\63\0\1\54" +
					"\42\0\1\136\21\0\1\55\63\0\1\56\11\0\2\57" +
					"\5\0\1\57\26\0\1\57\57\0\1\137\27\0\2\57" +
					"\5\0\1\57\27\0\1\57\63\0\1\140\22\0\2\57" +
					"\5\0\1\57\44\0\2\15\1\141\22\15\6\0\1\15" +
					"\2\0\2\37\7\0\1\15\1\0\3\15\3\0\1\15" +
					"\3\0\16\15\1\142\6\15\6\0\1\15\2\0\2\37" +
					"\7\0\1\15\1\0\3\15\3\0\1\15\3\0\5\15" +
					"\1\143\17\15\6\0\1\15\2\0\2\37\7\0\1\15" +
					"\1\0\3\15\3\0\1\15\3\0\13\15\1\144\11\15" +
					"\6\0\1\15\2\0\2\37\7\0\1\15\1\0\3\15" +
					"\3\0\1\15\3\0\1\145\24\15\6\0\1\15\2\0" +
					"\2\37\7\0\1\15\1\0\3\15\3\0\1\15\3\0" +
					"\1\146\24\15\6\0\1\15\2\0\2\37\7\0\1\15" +
					"\1\0\3\15\3\0\1\15\3\0\20\15\1\147\4\15" +
					"\6\0\1\15\2\0\2\37\7\0\1\15\1\0\3\15" +
					"\3\0\1\15\3\0\7\15\1\150\15\15\6\0\1\15" +
					"\2\0\2\37\7\0\1\15\1\0\3\15\3\0\1\15" +
					"\3\0\21\15\1\151\3\15\6\0\1\15\2\0\2\37" +
					"\7\0\1\15\1\0\3\15\3\0\1\15\3\0\23\15" +
					"\1\152\1\15\6\0\1\15\2\0\2\37\7\0\1\15" +
					"\1\0\3\15\3\0\1\15\3\0\23\15\1\153\1\15" +
					"\6\0\1\15\2\0\2\37\7\0\1\15\1\0\3\15" +
					"\3\0\1\15\3\0\16\15\1\154\6\15\6\0\1\15" +
					"\2\0\2\37\7\0\1\15\1\0\3\15\3\0\1\15" +
					"\3\0\22\15\1\155\2\15\6\0\1\15\2\0\2\37" +
					"\7\0\1\15\1\0\3\15\3\0\1\15\3\0\1\15" +
					"\1\156\23\15\6\0\1\15\2\0\2\37\7\0\1\15" +
					"\1\0\3\15\3\0\1\15\3\0\7\15\1\157\15\15" +
					"\6\0\1\15\2\0\2\37\7\0\1\15\1\0\3\15" +
					"\3\0\1\15\3\0\12\15\1\160\12\15\6\0\1\15" +
					"\2\0\2\37\7\0\1\15\1\0\3\15\3\0\1\15" +
					"\3\0\7\15\1\161\15\15\6\0\1\15\2\0\2\37" +
					"\7\0\1\15\1\0\3\15\3\0\1\15\3\0\1\15" +
					"\1\162\23\15\6\0\1\15\2\0\2\37\7\0\1\15" +
					"\1\0\3\15\3\0\1\15\3\0\7\15\1\163\15\15" +
					"\6\0\1\15\2\0\2\37\7\0\1\15\1\0\3\15" +
					"\3\0\1\15\3\0\16\15\1\164\6\15\6\0\1\15" +
					"\2\0\2\37\7\0\1\15\1\0\3\15\3\0\1\15" +
					"\3\0\1\165\24\15\6\0\1\15\2\0\2\37\7\0" +
					"\1\15\1\0\3\15\3\0\1\15\3\0\16\15\1\166" +
					"\6\15\6\0\1\15\2\0\2\37\7\0\1\15\1\0" +
					"\3\15\3\0\1\15\3\0\7\15\1\167\15\15\6\0" +
					"\1\15\2\0\2\37\7\0\1\15\1\0\3\15\3\0" +
					"\1\15\3\0\10\15\1\170\11\15\1\171\2\15\6\0" +
					"\1\15\2\0\2\37\7\0\1\15\1\0\3\15\3\0" +
					"\1\15\3\0\13\15\1\172\1\173\10\15\6\0\1\15" +
					"\2\0\2\37\7\0\1\15\1\0\3\15\3\0\1\15" +
					"\3\0\10\15\1\174\14\15\6\0\1\15\2\0\2\37" +
					"\7\0\1\15\1\0\3\15\3\0\1\15\3\0\12\15" +
					"\1\175\12\15\6\0\1\15\2\0\2\37\7\0\1\15" +
					"\1\0\3\15\3\0\1\15\3\0\4\15\1\176\20\15" +
					"\6\0\1\15\2\0\2\37\7\0\1\15\1\0\3\15" +
					"\3\0\1\15\3\0\10\15\1\177\14\15\6\0\1\15" +
					"\2\0\2\37\7\0\1\15\1\0\3\15\3\0\1\15" +
					"\2\0\56\127\1\200\1\201\3\127\44\202\1\203\16\202" +
					"\1\0\25\132\6\0\1\132\7\0\1\204\5\0\3\132" +
					"\3\0\1\132\52\0\1\133\50\0\1\136\21\0\1\134" +
					"\2\0\56\135\1\205\4\135\60\0\1\206\3\0\14\15" +
					"\1\207\10\15\6\0\1\15\2\0\2\37\7\0\1\15" +
					"\1\0\3\15\3\0\1\15\3\0\12\15\1\210\12\15" +
					"\6\0\1\15\2\0\2\37\7\0\1\15\1\0\3\15" +
					"\3\0\1\15\3\0\7\15\1\211\15\15\6\0\1\15" +
					"\2\0\2\37\7\0\1\15\1\0\3\15\3\0\1\15" +
					"\3\0\5\15\1\212\17\15\6\0\1\15\2\0\2\37" +
					"\7\0\1\15\1\0\3\15\3\0\1\15\3\0\12\15" +
					"\1\213\12\15\6\0\1\15\2\0\2\37\7\0\1\15" +
					"\1\0\3\15\3\0\1\15\3\0\7\15\1\214\15\15" +
					"\6\0\1\15\2\0\2\37\7\0\1\15\1\0\3\15" +
					"\3\0\1\15\3\0\12\15\1\215\12\15\6\0\1\15" +
					"\2\0\2\37\7\0\1\15\1\0\3\15\3\0\1\15" +
					"\3\0\22\15\1\216\2\15\6\0\1\15\2\0\2\37" +
					"\7\0\1\15\1\0\3\15\3\0\1\15\3\0\5\15" +
					"\1\217\17\15\6\0\1\15\2\0\2\37\7\0\1\15" +
					"\1\0\3\15\3\0\1\15\3\0\5\15\1\220\17\15" +
					"\6\0\1\15\2\0\2\37\7\0\1\15\1\0\3\15" +
					"\3\0\1\15\3\0\14\15\1\221\10\15\6\0\1\15" +
					"\2\0\2\37\7\0\1\15\1\0\3\15\3\0\1\15" +
					"\3\0\5\15\1\222\17\15\6\0\1\15\2\0\2\37" +
					"\7\0\1\15\1\0\3\15\3\0\1\15\3\0\2\15" +
					"\1\223\22\15\6\0\1\15\2\0\2\37\7\0\1\15" +
					"\1\0\3\15\3\0\1\15\3\0\5\15\1\224\5\15" +
					"\1\225\11\15\6\0\1\15\2\0\2\37\7\0\1\15" +
					"\1\0\3\15\3\0\1\15\3\0\13\15\1\226\11\15" +
					"\6\0\1\15\2\0\2\37\7\0\1\15\1\0\3\15" +
					"\3\0\1\15\3\0\11\15\1\227\13\15\6\0\1\15" +
					"\2\0\2\37\7\0\1\15\1\0\3\15\3\0\1\15" +
					"\3\0\3\15\1\230\21\15\6\0\1\15\2\0\2\37" +
					"\7\0\1\15\1\0\3\15\3\0\1\15\3\0\21\15" +
					"\1\231\3\15\6\0\1\15\2\0\2\37\7\0\1\15" +
					"\1\0\3\15\3\0\1\15\3\0\23\15\1\232\1\15" +
					"\6\0\1\15\2\0\2\37\7\0\1\15\1\0\3\15" +
					"\3\0\1\15\3\0\7\15\1\233\15\15\6\0\1\15" +
					"\2\0\2\37\7\0\1\15\1\0\3\15\3\0\1\15" +
					"\3\0\15\15\1\234\7\15\6\0\1\15\2\0\2\37" +
					"\7\0\1\15\1\0\3\15\3\0\1\15\3\0\21\15" +
					"\1\235\3\15\6\0\1\15\2\0\2\37\7\0\1\15" +
					"\1\0\3\15\3\0\1\15\3\0\1\156\24\15\6\0" +
					"\1\15\2\0\2\37\7\0\1\15\1\0\3\15\3\0" +
					"\1\15\3\0\7\15\1\236\15\15\6\0\1\15\2\0" +
					"\2\37\7\0\1\15\1\0\3\15\3\0\1\15\3\0" +
					"\4\15\1\237\20\15\6\0\1\15\2\0\2\37\7\0" +
					"\1\15\1\0\3\15\3\0\1\15\2\0\44\202\1\240" +
					"\56\202\1\241\3\202\1\240\16\202\53\0\1\242\4\0" +
					"\1\206\3\0\10\15\1\243\14\15\6\0\1\15\2\0" +
					"\2\37\7\0\1\15\1\0\3\15\3\0\1\15\3\0" +
					"\5\15\1\244\17\15\6\0\1\15\2\0\2\37\7\0" +
					"\1\15\1\0\3\15\3\0\1\15\3\0\16\15\1\245" +
					"\6\15\6\0\1\15\2\0\2\37\7\0\1\15\1\0" +
					"\3\15\3\0\1\15\3\0\10\15\1\246\14\15\6\0" +
					"\1\15\2\0\2\37\7\0\1\15\1\0\3\15\3\0" +
					"\1\15\3\0\13\15\1\247\11\15\6\0\1\15\2\0" +
					"\2\37\7\0\1\15\1\0\3\15\3\0\1\15\3\0" +
					"\20\15\1\250\4\15\6\0\1\15\2\0\2\37\7\0" +
					"\1\15\1\0\3\15\3\0\1\15\3\0\5\15\1\251" +
					"\17\15\6\0\1\15\2\0\2\37\7\0\1\15\1\0" +
					"\3\15\3\0\1\15\3\0\14\15\1\252\10\15\6\0" +
					"\1\15\2\0\2\37\7\0\1\15\1\0\3\15\3\0" +
					"\1\15\3\0\15\15\1\253\7\15\6\0\1\15\2\0" +
					"\2\37\7\0\1\15\1\0\3\15\3\0\1\15\3\0" +
					"\12\15\1\254\12\15\6\0\1\15\2\0\2\37\7\0" +
					"\1\15\1\0\3\15\3\0\1\15\3\0\25\15\6\0" +
					"\1\15\2\0\2\37\7\0\1\15\1\0\1\255\2\15" +
					"\3\0\1\15\3\0\1\15\1\256\23\15\6\0\1\15" +
					"\2\0\2\37\7\0\1\15\1\0\3\15\3\0\1\15" +
					"\3\0\5\15\1\257\17\15\6\0\1\15\2\0\2\37" +
					"\7\0\1\15\1\0\3\15\3\0\1\15\3\0\13\15" +
					"\1\260\11\15\6\0\1\15\2\0\2\37\7\0\1\15" +
					"\1\0\3\15\3\0\1\15\3\0\13\15\1\261\11\15" +
					"\6\0\1\15\2\0\2\37\7\0\1\15\1\0\3\15" +
					"\3\0\1\15\3\0\4\15\1\262\20\15\6\0\1\15" +
					"\2\0\2\37\7\0\1\15\1\0\3\15\3\0\1\15" +
					"\3\0\7\15\1\263\15\15\6\0\1\15\2\0\2\37" +
					"\7\0\1\15\1\0\3\15\3\0\1\15\3\0\1\15" +
					"\1\264\23\15\6\0\1\15\2\0\2\37\7\0\1\15" +
					"\1\0\3\15\3\0\1\15\3\0\12\15\1\265\12\15" +
					"\6\0\1\15\2\0\2\37\7\0\1\15\1\0\3\15" +
					"\3\0\1\15\2\0\40\202\1\200\3\202\1\240\16\202" +
					"\50\0\2\266\6\0\1\267\3\0\25\15\6\0\1\15" +
					"\2\0\2\37\7\0\1\15\1\0\1\270\2\15\3\0" +
					"\1\15\3\0\4\15\1\271\20\15\6\0\1\15\2\0" +
					"\2\37\7\0\1\15\1\0\3\15\3\0\1\15\3\0" +
					"\4\15\1\272\20\15\6\0\1\15\2\0\2\37\7\0" +
					"\1\15\1\0\3\15\3\0\1\15\3\0\11\15\1\273" +
					"\13\15\6\0\1\15\2\0\2\37\7\0\1\15\1\0" +
					"\3\15\3\0\1\15\3\0\1\274\24\15\6\0\1\15" +
					"\2\0\2\37\7\0\1\15\1\0\3\15\3\0\1\15" +
					"\3\0\10\15\1\275\14\15\6\0\1\15\2\0\2\37" +
					"\7\0\1\15\1\0\3\15\3\0\1\15\3\0\5\15" +
					"\1\276\17\15\6\0\1\15\2\0\2\37\7\0\1\15" +
					"\1\0\3\15\3\0\1\15\3\0\5\15\1\277\17\15" +
					"\6\0\1\15\2\0\2\37\7\0\1\15\1\0\3\15" +
					"\3\0\1\15\3\0\5\15\1\300\17\15\6\0\1\15" +
					"\2\0\2\37\7\0\1\15\1\0\3\15\3\0\1\15" +
					"\3\0\16\15\1\301\6\15\6\0\1\15\2\0\2\37" +
					"\7\0\1\15\1\0\3\15\3\0\1\15\3\0\23\15" +
					"\1\302\1\15\6\0\1\15\2\0\2\37\7\0\1\15" +
					"\1\0\3\15\3\0\1\15\3\0\10\15\1\303\14\15" +
					"\6\0\1\15\2\0\2\37\7\0\1\15\1\0\3\15" +
					"\3\0\1\15\3\0\7\15\1\304\15\15\6\0\1\15" +
					"\2\0\2\37\7\0\1\15\1\0\3\15\3\0\1\15" +
					"\3\0\7\15\1\305\15\15\6\0\1\15\2\0\2\37" +
					"\7\0\1\15\1\0\3\15\3\0\1\15\3\0\14\15" +
					"\1\306\10\15\6\0\1\15\2\0\2\37\7\0\1\15" +
					"\1\0\3\15\3\0\1\15\3\0\16\15\1\307\6\15" +
					"\6\0\1\15\2\0\2\37\7\0\1\15\1\0\3\15" +
					"\3\0\1\15\62\0\1\267\3\0\10\15\1\310\14\15" +
					"\6\0\1\15\2\0\2\37\7\0\1\15\1\0\3\15" +
					"\3\0\1\15\3\0\21\15\1\311\3\15\6\0\1\15" +
					"\2\0\2\37\7\0\1\15\1\0\3\15\3\0\1\15" +
					"\3\0\1\312\24\15\6\0\1\15\2\0\2\37\7\0" +
					"\1\15\1\0\3\15\3\0\1\15\3\0\5\15\1\313" +
					"\17\15\6\0\1\15\2\0\2\37\7\0\1\15\1\0" +
					"\3\15\3\0\1\15\3\0\4\15\1\314\20\15\6\0" +
					"\1\15\2\0\2\37\7\0\1\15\1\0\3\15\3\0" +
					"\1\15\3\0\16\15\1\315\6\15\6\0\1\15\2\0" +
					"\2\37\7\0\1\15\1\0\3\15\3\0\1\15\3\0" +
					"\21\15\1\316\3\15\6\0\1\15\2\0\2\37\7\0" +
					"\1\15\1\0\3\15\3\0\1\15\3\0\7\15\1\317" +
					"\15\15\6\0\1\15\2\0\2\37\7\0\1\15\1\0" +
					"\3\15\3\0\1\15\3\0\5\15\1\320\17\15\6\0" +
					"\1\15\2\0\2\37\7\0\1\15\1\0\3\15\3\0" +
					"\1\15\3\0\20\15\1\321\4\15\6\0\1\15\2\0" +
					"\2\37\7\0\1\15\1\0\3\15\3\0\1\15\3\0" +
					"\5\15\1\322\17\15\6\0\1\15\2\0\2\37\7\0" +
					"\1\15\1\0\3\15\3\0\1\15\3\0\13\15\1\323" +
					"\11\15\6\0\1\15\2\0\2\37\7\0\1\15\1\0" +
					"\3\15\3\0\1\15\3\0\5\15\1\324\17\15\6\0" +
					"\1\15\2\0\2\37\7\0\1\15\1\0\3\15\3\0" +
					"\1\15\3\0\7\15\1\325\15\15\6\0\1\15\2\0" +
					"\2\37\7\0\1\15\1\0\3\15\3\0\1\15\3\0" +
					"\11\15\1\326\13\15\6\0\1\15\2\0\2\37\7\0" +
					"\1\15\1\0\3\15\3\0\1\15\3\0\12\15\1\327" +
					"\12\15\6\0\1\15\2\0\2\37\7\0\1\15\1\0" +
					"\3\15\3\0\1\15\3\0\5\15\1\330\17\15\6\0" +
					"\1\15\2\0\2\37\7\0\1\15\1\0\3\15\3\0" +
					"\1\15\3\0\10\15\1\331\14\15\6\0\1\15\2\0" +
					"\2\37\7\0\1\15\1\0\3\15\3\0\1\15\3\0" +
					"\23\15\1\332\1\15\6\0\1\15\2\0\2\37\7\0" +
					"\1\15\1\0\3\15\3\0\1\15\3\0\10\15\1\333" +
					"\14\15\6\0\1\15\2\0\2\37\7\0\1\15\1\0" +
					"\3\15\3\0\1\15\3\0\7\15\1\334\15\15\6\0" +
					"\1\15\2\0\2\37\7\0\1\15\1\0\3\15\3\0" +
					"\1\15\3\0\5\15\1\335\17\15\6\0\1\15\2\0" +
					"\2\37\7\0\1\15\1\0\3\15\3\0\1\15\2\0";

	private static int[] zzUnpackTrans() {
		int[] result = new int[8313];
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
			"\5\0\1\11\22\1\6\11\1\1\1\11\1\1\2\11" +
					"\2\1\3\11\6\1\2\11\2\1\1\11\43\1\2\0" +
					"\1\1\1\0\2\1\2\0\41\1\1\11\1\1\2\0" +
					"\2\11\32\1\1\0\1\1\1\0\23\1\1\0\47\1";

	private static int[] zzUnpackAttribute() {
		int[] result = new int[221];
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
		zzReader = reader;
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
		Language heritage = LanguageManager.getLanguage(dsl, project);
		if (heritage != null) Collections.addAll(identifiers, heritage.lexicon());
	}


	/**
	 * Creates a new scanner
	 *
	 * @param in the java.io.Reader to read input from.
	 */
	TaraHighlighterLex(java.io.Reader in) {
		zzReader = in;
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
		while (i < 2270) {
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
				case 64:
					break;
				case 44: {
					return TaraTypes.WITH;
				}
				case 65:
					break;
				case 34: {
					return TaraTypes.VAR;
				}
				case 66:
					break;
				case 12: {
					return TaraTypes.COLON;
				}
				case 67:
					break;
				case 57: {
					return TaraTypes.ABSTRACT;
				}
				case 68:
					break;
				case 49: {
					return TaraTypes.NATIVE;
				}
				case 69:
					break;
				case 21: {
					yybegin(YYINITIAL);
					return TaraTypes.QUOTE_END;
				}
				case 70:
					break;
				case 62: {
					return TaraTypes.COMPONENT;
				}
				case 71:
					break;
				case 8: {
					return TaraTypes.RIGHT_CURLY;
				}
				case 72:
					break;
				case 38: {
					return TaraTypes.DOUBLE_VALUE_KEY;
				}
				case 73:
					break;
				case 22: {
					yybegin(YYINITIAL);
					return TaraTypes.EXPRESSION_END;
				}
				case 74:
					break;
				case 18: {
					return TokenType.WHITE_SPACE;
				}
				case 75:
					break;
				case 4: {
					return TaraTypes.RIGHT_PARENTHESIS;
				}
				case 76:
					break;
				case 17: {
					yybegin(EXPRESSION);
					return TaraTypes.EXPRESSION_BEGIN;
				}
				case 77:
					break;
				case 13: {
					return TaraTypes.EQUALS;
				}
				case 78:
					break;
				case 47: {
					return TaraTypes.FINAL;
				}
				case 79:
					break;
				case 10: {
					return TaraTypes.DOT;
				}
				case 80:
					break;
				case 48: {
					return TaraTypes.STRING_TYPE;
				}
				case 81:
					break;
				case 59: {
					return TaraTypes.TERMINAL;
				}
				case 82:
					break;
				case 35: {
					return TaraTypes.COMMENT;
				}
				case 83:
					break;
				case 54: {
					return TaraTypes.CONCEPT;
				}
				case 84:
					break;
				case 41: {
					return TaraTypes.DATE_TYPE;
				}
				case 85:
					break;
				case 45: {
					return TaraTypes.EMPTY_REF;
				}
				case 86:
					break;
				case 3: {
					return TaraTypes.LEFT_PARENTHESIS;
				}
				case 87:
					break;
				case 55: {
					return TaraTypes.PRIVATE;
				}
				case 88:
					break;
				case 27: {
					yybegin(EXPRESSION_MULTILINE);
					return TaraTypes.EXPRESSION_BEGIN;
				}
				case 89:
					break;
				case 56: {
					return TaraTypes.FEATURE;
				}
				case 90:
					break;
				case 28: {
					return TaraTypes.NEGATIVE_VALUE_KEY;
				}
				case 91:
					break;
				case 15: {
					return TaraTypes.DSL;
				}
				case 92:
					break;
				case 9: {
					return TaraTypes.METRIC_VALUE_KEY;
				}
				case 93:
					break;
				case 52: {
					return TaraTypes.EXTENDS;
				}
				case 94:
					break;
				case 26: {
					yybegin(MULTILINE);
					return TaraTypes.QUOTE_BEGIN;
				}
				case 95:
					break;
				case 63: {
					return TaraTypes.PROTOTYPE;
				}
				case 96:
					break;
				case 2: {
					return evaluateIdentifier();
				}
				case 97:
					break;
				case 14: {
					return TaraTypes.STAR;
				}
				case 98:
					break;
				case 50: {
					return TaraTypes.DOUBLE_TYPE;
				}
				case 99:
					break;
				case 7: {
					return TaraTypes.LEFT_CURLY;
				}
				case 100:
					break;
				case 6: {
					return TaraTypes.RIGHT_SQUARE;
				}
				case 101:
					break;
				case 19: {
					return TaraTypes.NATURAL_VALUE_KEY;
				}
				case 102:
					break;
				case 39: {
					return TaraTypes.TIME_TYPE;
				}
				case 103:
					break;
				case 32: {
					return TaraTypes.ANY;
				}
				case 104:
					break;
				case 1: {
					return TokenType.BAD_CHARACTER;
				}
				case 105:
					break;
				case 42: {
					return TaraTypes.INTO;
				}
				case 106:
					break;
				case 60: {
					return TaraTypes.RESOURCE_KEY;
				}
				case 107:
					break;
				case 46: {
					return TaraTypes.TUPLE_TYPE;
				}
				case 108:
					break;
				case 37: {
					yypushback(1);
					return TaraTypes.DOC_LINE;
				}
				case 109:
					break;
				case 40: {
					return TaraTypes.BOOLEAN_VALUE_KEY;
				}
				case 110:
					break;
				case 23: {
					return TaraTypes.AS;
				}
				case 111:
					break;
				case 24: {
					return TaraTypes.ON;
				}
				case 112:
					break;
				case 29: {
					return TaraTypes.SUB;
				}
				case 113:
					break;
				case 36: {
					return TaraTypes.ANCHOR_VALUE;
				}
				case 114:
					break;
				case 58: {
					return TaraTypes.ENCLOSED;
				}
				case 115:
					break;
				case 16: {
					yybegin(QUOTED);
					return TaraTypes.QUOTE_BEGIN;
				}
				case 116:
					break;
				case 51: {
					return TaraTypes.BOOLEAN_TYPE;
				}
				case 117:
					break;
				case 33: {
					loadHeritage();
					return TaraTypes.DSL;
				}
				case 118:
					break;
				case 25: {
					return TaraTypes.IS;
				}
				case 119:
					break;
				case 30: {
					return TaraTypes.USE;
				}
				case 120:
					break;
				case 53: {
					return TaraTypes.INT_TYPE;
				}
				case 121:
					break;
				case 61: {
					return TaraTypes.FUNCTION_TYPE;
				}
				case 122:
					break;
				case 43: {
					return TaraTypes.WORD_KEY;
				}
				case 123:
					break;
				case 20: {
					return TaraTypes.CHARACTER;
				}
				case 124:
					break;
				case 11: {
					return TaraTypes.COMMA;
				}
				case 125:
					break;
				case 31: {
					return TaraTypes.HAS;
				}
				case 126:
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
