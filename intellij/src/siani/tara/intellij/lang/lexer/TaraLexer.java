/* The following code was generated by JFlex 1.4.3 on 8/10/14 8:16 */

package siani.tara.intellij.lang.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import siani.tara.intellij.lang.psi.TaraTypes;

import java.util.LinkedList;
import java.util.Queue;


/**
 * This class is a scanner generated by
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 8/10/14 8:16 from the specification file
 * <tt>/Users/oroncal/workspace/tara/intellij/src/siani/tara/intellij/lang/lexer/Tara.flex</tt>
 */
class TaraLexer implements FlexLexer {
	/**
	 * initial size of the lookahead buffer
	 */
	private static final int ZZ_BUFFERSIZE = 16384;

	/**
	 * lexical states
	 */
	public static final int YYINITIAL = 0;

	/**
	 * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
	 * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
	 * at the beginning of a line
	 * l is of the form l = 2*k, k a non negative integer
	 */
	private static final int ZZ_LEXSTATE[] = {
		0, 0
	};

	/**
	 * Translates characters to character classes
	 */
	private static final String ZZ_CMAP_PACKED =
		"\11\55\1\1\1\2\3\0\16\55\4\0\1\1\2\0\1\56\1\46" +
			"\1\50\1\54\1\37\1\34\1\35\1\42\1\53\1\43\1\40\1\36" +
			"\1\0\12\57\1\44\1\52\1\0\1\45\1\3\2\0\2\41\1\4" +
			"\27\41\4\0\1\41\1\0\1\17\1\15\1\7\1\25\1\10\1\33" +
			"\1\30\1\16\1\22\2\41\1\23\1\32\1\6\1\5\1\11\1\31" +
			"\1\26\1\13\1\12\1\14\1\27\1\21\1\20\1\24\1\41\4\0" +
			"\41\55\2\0\4\41\4\0\1\41\2\0\1\55\7\0\1\41\4\0" +
			"\1\51\5\0\27\41\1\0\37\41\1\0\u013f\41\31\0\162\41\4\0" +
			"\14\41\16\0\5\41\11\0\1\41\21\0\130\55\5\0\23\55\12\0" +
			"\1\41\13\0\1\41\1\0\3\41\1\0\1\41\1\0\24\41\1\0" +
			"\54\41\1\0\46\41\1\0\5\41\4\0\202\41\1\0\4\55\3\0" +
			"\105\41\1\0\46\41\2\0\2\41\6\0\20\41\41\0\46\41\2\0" +
			"\1\41\7\0\47\41\11\0\21\55\1\0\27\55\1\0\3\55\1\0" +
			"\1\55\1\0\2\55\1\0\1\55\13\0\33\41\5\0\3\41\15\0" +
			"\4\55\14\0\6\55\13\0\32\41\5\0\13\41\16\55\7\0\12\57" +
			"\4\0\2\41\1\55\143\41\1\0\1\41\10\55\1\0\6\55\2\41" +
			"\2\55\1\0\4\55\2\41\12\57\3\41\2\0\1\41\17\0\1\55" +
			"\1\41\1\55\36\41\33\55\2\0\3\41\60\0\46\41\13\55\1\41" +
			"\u014f\0\3\55\66\41\2\0\1\55\1\41\20\55\2\0\1\41\4\55" +
			"\3\0\12\41\2\55\2\0\12\57\21\0\3\55\1\0\10\41\2\0" +
			"\2\41\2\0\26\41\1\0\7\41\1\0\1\41\3\0\4\41\2\0" +
			"\1\55\1\41\7\55\2\0\2\55\2\0\3\55\11\0\1\55\4\0" +
			"\2\41\1\0\3\41\2\55\2\0\12\57\4\41\15\0\3\55\1\0" +
			"\6\41\4\0\2\41\2\0\26\41\1\0\7\41\1\0\2\41\1\0" +
			"\2\41\1\0\2\41\2\0\1\55\1\0\5\55\4\0\2\55\2\0" +
			"\3\55\13\0\4\41\1\0\1\41\7\0\12\57\2\55\3\41\14\0" +
			"\3\55\1\0\11\41\1\0\3\41\1\0\26\41\1\0\7\41\1\0" +
			"\2\41\1\0\5\41\2\0\1\55\1\41\10\55\1\0\3\55\1\0" +
			"\3\55\2\0\1\41\17\0\2\41\2\55\2\0\12\57\1\0\1\41" +
			"\17\0\3\55\1\0\10\41\2\0\2\41\2\0\26\41\1\0\7\41" +
			"\1\0\2\41\1\0\5\41\2\0\1\55\1\41\6\55\3\0\2\55" +
			"\2\0\3\55\10\0\2\55\4\0\2\41\1\0\3\41\4\0\12\57" +
			"\1\0\1\41\20\0\1\55\1\41\1\0\6\41\3\0\3\41\1\0" +
			"\4\41\3\0\2\41\1\0\1\41\1\0\2\41\3\0\2\41\3\0" +
			"\3\41\3\0\10\41\1\0\3\41\4\0\5\55\3\0\3\55\1\0" +
			"\4\55\11\0\1\55\17\0\11\57\11\0\1\41\7\0\3\55\1\0" +
			"\10\41\1\0\3\41\1\0\27\41\1\0\12\41\1\0\5\41\4\0" +
			"\7\55\1\0\3\55\1\0\4\55\7\0\2\55\11\0\2\41\4\0" +
			"\12\57\22\0\2\55\1\0\10\41\1\0\3\41\1\0\27\41\1\0" +
			"\12\41\1\0\5\41\2\0\1\55\1\41\7\55\1\0\3\55\1\0" +
			"\4\55\7\0\2\55\7\0\1\41\1\0\2\41\4\0\12\57\22\0" +
			"\2\55\1\0\10\41\1\0\3\41\1\0\27\41\1\0\20\41\4\0" +
			"\6\55\2\0\3\55\1\0\4\55\11\0\1\55\10\0\2\41\4\0" +
			"\12\57\22\0\2\55\1\0\22\41\3\0\30\41\1\0\11\41\1\0" +
			"\1\41\2\0\7\41\3\0\1\55\4\0\6\55\1\0\1\55\1\0" +
			"\10\55\22\0\2\55\15\0\60\41\1\55\2\41\7\55\4\0\10\41" +
			"\10\55\1\0\12\57\47\0\2\41\1\0\1\41\2\0\2\41\1\0" +
			"\1\41\2\0\1\41\6\0\4\41\1\0\7\41\1\0\3\41\1\0" +
			"\1\41\1\0\1\41\2\0\2\41\1\0\4\41\1\55\2\41\6\55" +
			"\1\0\2\55\1\41\2\0\5\41\1\0\1\41\1\0\6\55\2\0" +
			"\12\57\2\0\2\41\42\0\1\41\27\0\2\55\6\0\12\57\13\0" +
			"\1\55\1\0\1\55\1\0\1\55\4\0\2\55\10\41\1\0\42\41" +
			"\6\0\24\55\1\0\2\55\4\41\4\0\10\55\1\0\44\55\11\0" +
			"\1\55\71\0\42\41\1\0\5\41\1\0\2\41\1\0\7\55\3\0" +
			"\4\55\6\0\12\57\6\0\6\41\4\55\106\0\46\41\12\0\51\41" +
			"\7\0\132\41\5\0\104\41\5\0\122\41\6\0\7\41\1\0\77\41" +
			"\1\0\1\41\1\0\4\41\2\0\7\41\1\0\1\41\1\0\4\41" +
			"\2\0\47\41\1\0\1\41\1\0\4\41\2\0\37\41\1\0\1\41" +
			"\1\0\4\41\2\0\7\41\1\0\1\41\1\0\4\41\2\0\7\41" +
			"\1\0\7\41\1\0\27\41\1\0\37\41\1\0\1\41\1\0\4\41" +
			"\2\0\7\41\1\0\47\41\1\0\23\41\16\0\11\57\56\0\125\41" +
			"\14\0\u026c\41\2\0\10\41\12\0\32\41\5\0\113\41\3\0\3\41" +
			"\17\0\15\41\1\0\4\41\3\55\13\0\22\41\3\55\13\0\22\41" +
			"\2\55\14\0\15\41\1\0\3\41\1\0\2\55\14\0\64\41\40\55" +
			"\3\0\1\41\3\0\2\41\1\55\2\0\12\57\41\0\3\55\2\0" +
			"\12\57\6\0\130\41\10\0\51\41\1\55\126\0\35\41\3\0\14\55" +
			"\4\0\14\55\12\0\12\57\36\41\2\0\5\41\u038b\0\154\41\224\0" +
			"\234\41\4\0\132\41\6\0\26\41\2\0\6\41\2\0\46\41\2\0" +
			"\6\41\2\0\10\41\1\0\1\41\1\0\1\41\1\0\1\41\1\0" +
			"\37\41\2\0\65\41\1\0\7\41\1\0\1\41\3\0\3\41\1\0" +
			"\7\41\3\0\4\41\2\0\6\41\4\0\15\41\5\0\3\41\1\0" +
			"\7\41\17\0\4\55\32\0\5\55\20\0\2\41\23\0\1\41\13\0" +
			"\4\55\6\0\6\55\1\0\1\41\15\0\1\41\40\0\14\41\1\47" +
			"\5\41\36\0\15\55\4\0\1\55\3\0\6\55\27\0\1\41\4\0" +
			"\1\41\2\0\12\41\1\0\1\41\3\0\5\41\6\0\1\41\1\0" +
			"\1\41\1\0\1\41\1\0\4\41\1\0\3\41\1\0\7\41\3\0" +
			"\3\41\5\0\5\41\26\0\44\41\u0e81\0\3\41\31\0\11\41\6\55" +
			"\1\0\5\41\2\0\5\41\4\0\126\41\2\0\2\55\2\0\3\41" +
			"\1\0\137\41\5\0\50\41\4\0\136\41\21\0\30\41\70\0\20\41" +
			"\u0200\0\u19b6\41\112\0\u51a6\41\132\0\u048d\41\u0773\0\u2ba4\41\u215c\0\u012e\41" +
			"\2\0\73\41\225\0\7\41\14\0\5\41\5\0\1\41\1\55\12\41" +
			"\1\0\15\41\1\0\5\41\1\0\1\41\1\0\2\41\1\0\2\41" +
			"\1\0\154\41\41\0\u016b\41\22\0\100\41\2\0\66\41\50\0\15\41" +
			"\3\0\20\55\20\0\4\55\17\0\2\41\30\0\3\41\31\0\1\41" +
			"\6\0\5\41\1\0\207\41\2\0\1\55\4\0\1\41\13\0\12\57" +
			"\7\0\32\41\4\0\1\41\1\0\32\41\12\0\132\41\3\0\6\41" +
			"\2\0\6\41\2\0\6\41\2\0\3\41\3\0\2\41\3\0\2\41" +
			"\22\0\3\55\4\0";

	/**
	 * Translates characters to character classes
	 */
	private static final char[] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

	/**
	 * Translates DFA states to action switch labels.
	 */
	private static final int[] ZZ_ACTION = zzUnpackAction();

	private static final String ZZ_ACTION_PACKED_0 =
		"\1\0\1\1\1\2\1\3\1\4\23\5\1\6\1\7" +
			"\1\10\2\1\1\11\1\12\1\13\1\14\1\15\1\16" +
			"\1\17\1\20\1\21\3\1\1\22\1\3\1\5\1\23" +
			"\17\5\1\24\4\5\1\25\5\5\2\0\1\26\1\0" +
			"\1\27\1\30\1\0\1\31\2\0\15\5\1\32\2\5" +
			"\1\33\1\5\1\34\1\35\10\5\1\36\2\5\1\37" +
			"\2\0\1\40\1\0\1\41\7\5\1\42\3\5\1\43" +
			"\5\5\1\44\1\45\2\5\1\46\3\5\1\0\1\47" +
			"\1\0\2\5\1\50\3\5\1\51\15\5\1\52\1\47" +
			"\2\0\10\5\1\53\1\54\2\5\1\55\2\5\1\56" +
			"\2\5\1\0\1\57\1\60\2\5\1\61\1\5\1\62" +
			"\2\5\1\63\1\5\1\64\2\5\1\65\2\5\1\66" +
			"\1\67\2\5\1\70\1\71\1\5\1\72\1\73\1\74" +
			"\1\75";

	private static int[] zzUnpackAction() {
		int[] result = new int[217];
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
		"\0\0\0\60\0\140\0\220\0\60\0\300\0\360\0\u0120" +
			"\0\u0150\0\u0180\0\u01b0\0\u01e0\0\u0210\0\u0240\0\u0270\0\u02a0" +
			"\0\u02d0\0\u0300\0\u0330\0\u0360\0\u0390\0\u03c0\0\u03f0\0\u0420" +
			"\0\60\0\60\0\u0450\0\u0480\0\u04b0\0\60\0\60\0\60" +
			"\0\60\0\u0300\0\u0300\0\60\0\u0300\0\u04e0\0\u0510\0\u0540" +
			"\0\u0570\0\u05a0\0\u05d0\0\u0600\0\u0300\0\u0630\0\u0660\0\u0690" +
			"\0\u06c0\0\u06f0\0\u0720\0\u0750\0\u0780\0\u07b0\0\u07e0\0\u0810" +
			"\0\u0840\0\u0870\0\u08a0\0\u08d0\0\u0300\0\u0900\0\u0930\0\u0960" +
			"\0\u0990\0\u0300\0\u09c0\0\u09f0\0\u0a20\0\u0a50\0\u0a80\0\u0ab0" +
			"\0\u0480\0\60\0\u0ae0\0\u0b10\0\u0540\0\u0570\0\60\0\u0b40" +
			"\0\u0b70\0\u0ba0\0\u0bd0\0\u0c00\0\u0c30\0\u0c60\0\u0c90\0\u0cc0" +
			"\0\u0cf0\0\u0d20\0\u0d50\0\u0d80\0\u0db0\0\u0de0\0\u0300\0\u0e10" +
			"\0\u0e40\0\u0300\0\u0e70\0\u0300\0\u0300\0\u0ea0\0\u0ed0\0\u0f00" +
			"\0\u0f30\0\u0f60\0\u0f90\0\u0fc0\0\u0ff0\0\u0300\0\u1020\0\u1050" +
			"\0\60\0\u1080\0\u10b0\0\u10e0\0\u1110\0\u1140\0\u1170\0\u11a0" +
			"\0\u11d0\0\u1200\0\u1230\0\u1260\0\u1290\0\u0300\0\u12c0\0\u12f0" +
			"\0\u1320\0\u0300\0\u1350\0\u1380\0\u13b0\0\u13e0\0\u1410\0\u0300" +
			"\0\u0300\0\u1440\0\u1470\0\u0300\0\u14a0\0\u14d0\0\u1500\0\u1530" +
			"\0\u10b0\0\u1560\0\u1590\0\u15c0\0\u0300\0\u15f0\0\u1620\0\u1650" +
			"\0\u0300\0\u1680\0\u16b0\0\u16e0\0\u1710\0\u1740\0\u1770\0\u17a0" +
			"\0\u17d0\0\u1800\0\u1830\0\u1860\0\u1890\0\u18c0\0\u0300\0\u18f0" +
			"\0\u1920\0\u1950\0\u1980\0\u19b0\0\u19e0\0\u1a10\0\u1a40\0\u1a70" +
			"\0\u1aa0\0\u1ad0\0\u0300\0\u0300\0\u1b00\0\u1b30\0\u0300\0\u1b60" +
			"\0\u1b90\0\u0300\0\u1bc0\0\u1bf0\0\u1c20\0\u0300\0\u0300\0\u1c50" +
			"\0\u1c80\0\u0300\0\u1cb0\0\u0300\0\u1ce0\0\u1d10\0\u0300\0\u1d40" +
			"\0\u0300\0\u1d70\0\u1da0\0\u1dd0\0\u1e00\0\u1e30\0\u0300\0\u0300" +
			"\0\u1e60\0\u1e90\0\u0300\0\u0300\0\u1ec0\0\u0300\0\u0300\0\u0300" +
			"\0\u0300";

	private static int[] zzUnpackRowMap() {
		int[] result = new int[217];
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
		"\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11" +
			"\1\12\1\13\1\14\1\15\1\16\1\17\1\20\1\21" +
			"\1\22\1\23\1\24\2\22\1\25\1\26\1\27\3\22" +
			"\1\30\1\31\1\32\1\33\1\34\1\35\1\22\1\36" +
			"\1\37\1\40\1\41\1\42\1\43\1\44\1\45\1\46" +
			"\1\47\1\50\1\2\1\51\1\52\61\0\1\3\57\0" +
			"\1\53\1\4\61\0\1\22\1\54\26\22\4\0\2\22" +
			"\4\0\2\22\1\0\1\22\3\0\1\22\1\0\1\22" +
			"\4\0\2\22\1\55\25\22\4\0\2\22\4\0\2\22" +
			"\1\0\1\22\3\0\1\22\1\0\1\22\4\0\13\22" +
			"\1\56\14\22\4\0\2\22\4\0\2\22\1\0\1\22" +
			"\3\0\1\22\1\0\1\22\4\0\1\22\1\57\26\22" +
			"\4\0\2\22\4\0\2\22\1\0\1\22\3\0\1\22" +
			"\1\0\1\22\4\0\14\22\1\60\11\22\1\61\1\22" +
			"\4\0\2\22\4\0\2\22\1\0\1\22\3\0\1\22" +
			"\1\0\1\22\4\0\1\22\1\62\20\22\1\63\5\22" +
			"\4\0\2\22\4\0\2\22\1\0\1\22\3\0\1\22" +
			"\1\0\1\22\4\0\4\22\1\64\15\22\1\65\5\22" +
			"\4\0\2\22\4\0\2\22\1\0\1\22\3\0\1\22" +
			"\1\0\1\22\4\0\6\22\1\66\1\22\1\67\5\22" +
			"\1\70\11\22\4\0\2\22\4\0\2\22\1\0\1\22" +
			"\3\0\1\22\1\0\1\22\4\0\2\22\1\71\4\22" +
			"\1\72\20\22\4\0\2\22\4\0\2\22\1\0\1\22" +
			"\3\0\1\22\1\0\1\22\4\0\1\22\1\73\26\22" +
			"\4\0\2\22\4\0\2\22\1\0\1\22\3\0\1\22" +
			"\1\0\1\22\4\0\13\22\1\74\14\22\4\0\2\22" +
			"\4\0\2\22\1\0\1\22\3\0\1\22\1\0\1\22" +
			"\4\0\7\22\1\75\7\22\1\76\10\22\4\0\2\22" +
			"\4\0\2\22\1\0\1\22\3\0\1\22\1\0\1\22" +
			"\4\0\30\22\4\0\2\22\4\0\2\22\1\0\1\22" +
			"\3\0\1\22\1\0\1\22\4\0\1\22\1\77\14\22" +
			"\1\100\11\22\4\0\2\22\4\0\2\22\1\0\1\22" +
			"\3\0\1\22\1\0\1\22\4\0\2\22\1\101\4\22" +
			"\1\102\20\22\4\0\2\22\4\0\2\22\1\0\1\22" +
			"\3\0\1\22\1\0\1\22\4\0\1\22\1\103\11\22" +
			"\1\104\14\22\4\0\2\22\4\0\2\22\1\0\1\22" +
			"\3\0\1\22\1\0\1\22\4\0\4\22\1\105\23\22" +
			"\4\0\2\22\4\0\2\22\1\0\1\22\3\0\1\22" +
			"\1\0\1\22\4\0\13\22\1\106\14\22\4\0\2\22" +
			"\4\0\2\22\1\0\1\22\3\0\1\22\1\0\1\22" +
			"\4\0\13\22\1\107\14\22\4\0\2\22\4\0\2\22" +
			"\1\0\1\22\3\0\1\22\1\0\1\22\36\0\1\110" +
			"\21\0\37\111\1\112\20\111\40\0\1\113\16\0\1\114" +
			"\52\0\1\46\64\0\1\52\4\0\30\115\5\0\1\115" +
			"\4\0\2\115\1\0\1\115\3\0\1\115\1\0\1\115" +
			"\2\116\1\117\55\116\36\0\1\120\1\0\1\121\16\0" +
			"\1\52\1\0\1\53\62\0\2\22\1\122\25\22\4\0" +
			"\2\22\4\0\2\22\1\0\1\22\3\0\1\22\1\0" +
			"\1\22\4\0\6\22\1\123\17\22\1\124\1\22\4\0" +
			"\2\22\4\0\2\22\1\0\1\22\3\0\1\22\1\0" +
			"\1\22\4\0\1\22\1\125\24\22\1\126\1\22\4\0" +
			"\2\22\4\0\2\22\1\0\1\22\3\0\1\22\1\0" +
			"\1\22\4\0\6\22\1\127\21\22\4\0\2\22\4\0" +
			"\2\22\1\0\1\22\3\0\1\22\1\0\1\22\4\0" +
			"\5\22\1\130\22\22\4\0\2\22\4\0\2\22\1\0" +
			"\1\22\3\0\1\22\1\0\1\22\4\0\22\22\1\131" +
			"\5\22\4\0\2\22\4\0\2\22\1\0\1\22\3\0" +
			"\1\22\1\0\1\22\4\0\1\22\1\132\14\22\1\133" +
			"\11\22\4\0\2\22\4\0\2\22\1\0\1\22\3\0" +
			"\1\22\1\0\1\22\4\0\22\22\1\134\5\22\4\0" +
			"\2\22\4\0\2\22\1\0\1\22\3\0\1\22\1\0" +
			"\1\22\4\0\10\22\1\135\17\22\4\0\2\22\4\0" +
			"\2\22\1\0\1\22\3\0\1\22\1\0\1\22\4\0" +
			"\22\22\1\136\5\22\4\0\2\22\4\0\2\22\1\0" +
			"\1\22\3\0\1\22\1\0\1\22\4\0\11\22\1\137" +
			"\16\22\4\0\2\22\4\0\2\22\1\0\1\22\3\0" +
			"\1\22\1\0\1\22\4\0\2\22\1\140\25\22\4\0" +
			"\2\22\4\0\2\22\1\0\1\22\3\0\1\22\1\0" +
			"\1\22\4\0\16\22\1\141\11\22\4\0\2\22\4\0" +
			"\2\22\1\0\1\22\3\0\1\22\1\0\1\22\4\0" +
			"\4\22\1\142\23\22\4\0\2\22\4\0\2\22\1\0" +
			"\1\22\3\0\1\22\1\0\1\22\4\0\1\22\1\143" +
			"\12\22\1\144\13\22\4\0\2\22\4\0\2\22\1\0" +
			"\1\22\3\0\1\22\1\0\1\22\4\0\7\22\1\145" +
			"\20\22\4\0\2\22\4\0\2\22\1\0\1\22\3\0" +
			"\1\22\1\0\1\22\4\0\15\22\1\146\12\22\4\0" +
			"\2\22\4\0\2\22\1\0\1\22\3\0\1\22\1\0" +
			"\1\22\4\0\22\22\1\147\5\22\4\0\2\22\4\0" +
			"\2\22\1\0\1\22\3\0\1\22\1\0\1\22\4\0" +
			"\6\22\1\150\21\22\4\0\2\22\4\0\2\22\1\0" +
			"\1\22\3\0\1\22\1\0\1\22\4\0\6\22\1\151" +
			"\21\22\4\0\2\22\4\0\2\22\1\0\1\22\3\0" +
			"\1\22\1\0\1\22\4\0\10\22\1\152\17\22\4\0" +
			"\2\22\4\0\2\22\1\0\1\22\3\0\1\22\1\0" +
			"\1\22\4\0\6\22\1\153\21\22\4\0\2\22\4\0" +
			"\2\22\1\0\1\22\3\0\1\22\1\0\1\22\4\0" +
			"\7\22\1\154\15\22\1\155\2\22\4\0\2\22\4\0" +
			"\2\22\1\0\1\22\3\0\1\22\1\0\1\22\4\0" +
			"\22\22\1\156\5\22\4\0\2\22\4\0\2\22\1\0" +
			"\1\22\3\0\1\22\1\0\1\22\4\0\3\22\1\157" +
			"\13\22\1\160\10\22\4\0\2\22\4\0\2\22\1\0" +
			"\1\22\3\0\1\22\1\0\1\22\36\0\1\161\21\0" +
			"\40\162\1\163\17\162\36\0\1\120\20\0\1\114\57\0" +
			"\1\164\53\0\1\165\3\0\1\166\4\0\3\22\1\167" +
			"\24\22\4\0\2\22\4\0\2\22\1\0\1\22\3\0" +
			"\1\22\1\0\1\22\4\0\10\22\1\170\17\22\4\0" +
			"\2\22\4\0\2\22\1\0\1\22\3\0\1\22\1\0" +
			"\1\22\4\0\4\22\1\171\23\22\4\0\2\22\4\0" +
			"\2\22\1\0\1\22\3\0\1\22\1\0\1\22\4\0" +
			"\22\22\1\172\5\22\4\0\2\22\4\0\2\22\1\0" +
			"\1\22\3\0\1\22\1\0\1\22\4\0\5\22\1\173" +
			"\22\22\4\0\2\22\4\0\2\22\1\0\1\22\3\0" +
			"\1\22\1\0\1\22\4\0\4\22\1\174\23\22\4\0" +
			"\2\22\4\0\2\22\1\0\1\22\3\0\1\22\1\0" +
			"\1\22\4\0\6\22\1\175\21\22\4\0\2\22\4\0" +
			"\2\22\1\0\1\22\3\0\1\22\1\0\1\22\4\0" +
			"\6\22\1\176\21\22\4\0\2\22\4\0\2\22\1\0" +
			"\1\22\3\0\1\22\1\0\1\22\4\0\5\22\1\177" +
			"\22\22\4\0\2\22\4\0\2\22\1\0\1\22\3\0" +
			"\1\22\1\0\1\22\4\0\23\22\1\200\4\22\4\0" +
			"\2\22\4\0\2\22\1\0\1\22\3\0\1\22\1\0" +
			"\1\22\4\0\26\22\1\201\1\22\4\0\2\22\4\0" +
			"\2\22\1\0\1\22\3\0\1\22\1\0\1\22\4\0" +
			"\4\22\1\202\23\22\4\0\2\22\4\0\2\22\1\0" +
			"\1\22\3\0\1\22\1\0\1\22\4\0\16\22\1\203" +
			"\11\22\4\0\2\22\4\0\2\22\1\0\1\22\3\0" +
			"\1\22\1\0\1\22\4\0\24\22\1\204\3\22\4\0" +
			"\2\22\4\0\2\22\1\0\1\22\3\0\1\22\1\0" +
			"\1\22\4\0\23\22\1\205\4\22\4\0\2\22\4\0" +
			"\2\22\1\0\1\22\3\0\1\22\1\0\1\22\4\0" +
			"\17\22\1\206\10\22\4\0\2\22\4\0\2\22\1\0" +
			"\1\22\3\0\1\22\1\0\1\22\4\0\13\22\1\207" +
			"\14\22\4\0\2\22\4\0\2\22\1\0\1\22\3\0" +
			"\1\22\1\0\1\22\4\0\21\22\1\210\6\22\4\0" +
			"\2\22\4\0\2\22\1\0\1\22\3\0\1\22\1\0" +
			"\1\22\4\0\12\22\1\211\15\22\4\0\2\22\4\0" +
			"\2\22\1\0\1\22\3\0\1\22\1\0\1\22\4\0" +
			"\4\22\1\212\23\22\4\0\2\22\4\0\2\22\1\0" +
			"\1\22\3\0\1\22\1\0\1\22\4\0\11\22\1\213" +
			"\16\22\4\0\2\22\4\0\2\22\1\0\1\22\3\0" +
			"\1\22\1\0\1\22\4\0\4\22\1\214\23\22\4\0" +
			"\2\22\4\0\2\22\1\0\1\22\3\0\1\22\1\0" +
			"\1\22\4\0\1\22\1\215\26\22\4\0\2\22\4\0" +
			"\2\22\1\0\1\22\3\0\1\22\1\0\1\22\4\0" +
			"\10\22\1\216\17\22\4\0\2\22\4\0\2\22\1\0" +
			"\1\22\3\0\1\22\1\0\1\22\4\0\4\22\1\217" +
			"\23\22\4\0\2\22\4\0\2\22\1\0\1\22\3\0" +
			"\1\22\1\0\1\22\4\0\7\22\1\135\20\22\4\0" +
			"\2\22\4\0\2\22\1\0\1\22\3\0\1\22\1\0" +
			"\1\22\40\162\1\220\57\162\1\221\17\162\40\0\1\222" +
			"\16\0\1\164\57\0\1\166\40\0\1\121\16\0\1\166" +
			"\4\0\4\22\1\223\23\22\4\0\2\22\4\0\2\22" +
			"\1\0\1\22\3\0\1\22\1\0\1\22\4\0\22\22" +
			"\1\224\5\22\4\0\2\22\4\0\2\22\1\0\1\22" +
			"\3\0\1\22\1\0\1\22\4\0\21\22\1\225\6\22" +
			"\4\0\2\22\4\0\2\22\1\0\1\22\3\0\1\22" +
			"\1\0\1\22\4\0\21\22\1\226\6\22\4\0\2\22" +
			"\4\0\2\22\1\0\1\22\3\0\1\22\1\0\1\22" +
			"\4\0\1\22\1\227\26\22\4\0\2\22\4\0\2\22" +
			"\1\0\1\22\3\0\1\22\1\0\1\22\4\0\2\22" +
			"\1\230\25\22\4\0\2\22\4\0\2\22\1\0\1\22" +
			"\3\0\1\22\1\0\1\22\4\0\20\22\1\231\7\22" +
			"\4\0\2\22\4\0\2\22\1\0\1\22\3\0\1\22" +
			"\1\0\1\22\4\0\4\22\1\232\23\22\4\0\2\22" +
			"\4\0\2\22\1\0\1\22\3\0\1\22\1\0\1\22" +
			"\4\0\13\22\1\233\14\22\4\0\2\22\4\0\2\22" +
			"\1\0\1\22\3\0\1\22\1\0\1\22\4\0\16\22" +
			"\1\234\11\22\4\0\2\22\4\0\2\22\1\0\1\22" +
			"\3\0\1\22\1\0\1\22\4\0\2\22\1\235\25\22" +
			"\4\0\2\22\4\0\2\22\1\0\1\22\3\0\1\22" +
			"\1\0\1\22\4\0\17\22\1\236\10\22\4\0\2\22" +
			"\4\0\2\22\1\0\1\22\3\0\1\22\1\0\1\22" +
			"\4\0\4\22\1\237\23\22\4\0\2\22\4\0\2\22" +
			"\1\0\1\22\3\0\1\22\1\0\1\22\4\0\4\22" +
			"\1\240\23\22\4\0\2\22\4\0\2\22\1\0\1\22" +
			"\3\0\1\22\1\0\1\22\4\0\20\22\1\241\7\22" +
			"\4\0\2\22\4\0\2\22\1\0\1\22\3\0\1\22" +
			"\1\0\1\22\4\0\2\22\1\242\21\22\1\243\3\22" +
			"\4\0\2\22\4\0\2\22\1\0\1\22\3\0\1\22" +
			"\1\0\1\22\4\0\17\22\1\244\10\22\4\0\2\22" +
			"\4\0\2\22\1\0\1\22\3\0\1\22\1\0\1\22" +
			"\4\0\10\22\1\245\17\22\4\0\2\22\4\0\2\22" +
			"\1\0\1\22\3\0\1\22\1\0\1\22\4\0\16\22" +
			"\1\246\11\22\4\0\2\22\4\0\2\22\1\0\1\22" +
			"\3\0\1\22\1\0\1\22\4\0\6\22\1\247\21\22" +
			"\4\0\2\22\4\0\2\22\1\0\1\22\3\0\1\22" +
			"\1\0\1\22\40\162\1\250\17\162\40\0\1\251\12\0" +
			"\1\251\3\0\1\252\4\0\5\22\1\253\22\22\4\0" +
			"\2\22\4\0\2\22\1\0\1\22\3\0\1\22\1\0" +
			"\1\22\4\0\13\22\1\254\14\22\4\0\2\22\4\0" +
			"\2\22\1\0\1\22\3\0\1\22\1\0\1\22\4\0" +
			"\16\22\1\255\11\22\4\0\2\22\4\0\2\22\1\0" +
			"\1\22\3\0\1\22\1\0\1\22\4\0\2\22\1\256" +
			"\25\22\4\0\2\22\4\0\2\22\1\0\1\22\3\0" +
			"\1\22\1\0\1\22\4\0\21\22\1\257\6\22\4\0" +
			"\2\22\4\0\2\22\1\0\1\22\3\0\1\22\1\0" +
			"\1\22\4\0\22\22\1\260\5\22\4\0\2\22\4\0" +
			"\2\22\1\0\1\22\3\0\1\22\1\0\1\22\4\0" +
			"\6\22\1\261\21\22\4\0\2\22\4\0\2\22\1\0" +
			"\1\22\3\0\1\22\1\0\1\22\4\0\2\22\1\262" +
			"\25\22\4\0\2\22\4\0\2\22\1\0\1\22\3\0" +
			"\1\22\1\0\1\22\4\0\24\22\1\263\3\22\4\0" +
			"\2\22\4\0\2\22\1\0\1\22\3\0\1\22\1\0" +
			"\1\22\4\0\4\22\1\264\23\22\4\0\2\22\4\0" +
			"\2\22\1\0\1\22\3\0\1\22\1\0\1\22\4\0" +
			"\22\22\1\265\5\22\4\0\2\22\4\0\2\22\1\0" +
			"\1\22\3\0\1\22\1\0\1\22\4\0\13\22\1\266" +
			"\14\22\4\0\2\22\4\0\2\22\1\0\1\22\3\0" +
			"\1\22\1\0\1\22\4\0\7\22\1\267\20\22\4\0" +
			"\2\22\4\0\2\22\1\0\1\22\3\0\1\22\1\0" +
			"\1\22\4\0\6\22\1\270\21\22\4\0\2\22\4\0" +
			"\2\22\1\0\1\22\3\0\1\22\1\0\1\22\4\0" +
			"\4\22\1\271\23\22\4\0\2\22\4\0\2\22\1\0" +
			"\1\22\3\0\1\22\1\0\1\22\4\0\4\22\1\272" +
			"\23\22\4\0\2\22\4\0\2\22\1\0\1\22\3\0" +
			"\1\22\1\0\1\22\4\0\22\22\1\273\5\22\4\0" +
			"\2\22\4\0\2\22\1\0\1\22\3\0\1\22\1\0" +
			"\1\22\4\0\22\22\1\274\5\22\4\0\2\22\4\0" +
			"\2\22\1\0\1\22\3\0\1\22\1\0\1\22\40\0" +
			"\1\250\76\0\1\252\36\0\1\275\20\0\1\252\4\0" +
			"\6\22\1\276\21\22\4\0\2\22\4\0\2\22\1\0" +
			"\1\22\3\0\1\22\1\0\1\22\4\0\17\22\1\277" +
			"\10\22\4\0\2\22\4\0\2\22\1\0\1\22\3\0" +
			"\1\22\1\0\1\22\4\0\2\22\1\300\25\22\4\0" +
			"\2\22\4\0\2\22\1\0\1\22\3\0\1\22\1\0" +
			"\1\22\4\0\4\22\1\301\23\22\4\0\2\22\4\0" +
			"\2\22\1\0\1\22\3\0\1\22\1\0\1\22\4\0" +
			"\7\22\1\302\20\22\4\0\2\22\4\0\2\22\1\0" +
			"\1\22\3\0\1\22\1\0\1\22\4\0\6\22\1\303" +
			"\21\22\4\0\2\22\4\0\2\22\1\0\1\22\3\0" +
			"\1\22\1\0\1\22\4\0\4\22\1\304\23\22\4\0" +
			"\2\22\4\0\2\22\1\0\1\22\3\0\1\22\1\0" +
			"\1\22\4\0\13\22\1\305\14\22\4\0\2\22\4\0" +
			"\2\22\1\0\1\22\3\0\1\22\1\0\1\22\4\0" +
			"\7\22\1\306\20\22\4\0\2\22\4\0\2\22\1\0" +
			"\1\22\3\0\1\22\1\0\1\22\4\0\2\22\1\307" +
			"\25\22\4\0\2\22\4\0\2\22\1\0\1\22\3\0" +
			"\1\22\1\0\1\22\4\0\16\22\1\310\11\22\4\0" +
			"\2\22\4\0\2\22\1\0\1\22\3\0\1\22\1\0" +
			"\1\22\4\0\22\22\1\311\5\22\4\0\2\22\4\0" +
			"\2\22\1\0\1\22\3\0\1\22\1\0\1\22\4\0" +
			"\3\22\1\312\24\22\4\0\2\22\4\0\2\22\1\0" +
			"\1\22\3\0\1\22\1\0\1\22\4\0\4\22\1\313" +
			"\23\22\4\0\2\22\4\0\2\22\1\0\1\22\3\0" +
			"\1\22\1\0\1\22\57\0\1\314\4\0\13\22\1\315" +
			"\14\22\4\0\2\22\4\0\2\22\1\0\1\22\3\0" +
			"\1\22\1\0\1\22\4\0\2\22\1\316\25\22\4\0" +
			"\2\22\4\0\2\22\1\0\1\22\3\0\1\22\1\0" +
			"\1\22\4\0\20\22\1\317\7\22\4\0\2\22\4\0" +
			"\2\22\1\0\1\22\3\0\1\22\1\0\1\22\4\0" +
			"\17\22\1\320\10\22\4\0\2\22\4\0\2\22\1\0" +
			"\1\22\3\0\1\22\1\0\1\22\4\0\13\22\1\321" +
			"\14\22\4\0\2\22\4\0\2\22\1\0\1\22\3\0" +
			"\1\22\1\0\1\22\4\0\1\22\1\322\26\22\4\0" +
			"\2\22\4\0\2\22\1\0\1\22\3\0\1\22\1\0" +
			"\1\22\4\0\4\22\1\323\23\22\4\0\2\22\4\0" +
			"\2\22\1\0\1\22\3\0\1\22\1\0\1\22\4\0" +
			"\21\22\1\324\6\22\4\0\2\22\4\0\2\22\1\0" +
			"\1\22\3\0\1\22\1\0\1\22\40\0\1\222\16\0" +
			"\1\314\4\0\6\22\1\325\21\22\4\0\2\22\4\0" +
			"\2\22\1\0\1\22\3\0\1\22\1\0\1\22\4\0" +
			"\6\22\1\326\21\22\4\0\2\22\4\0\2\22\1\0" +
			"\1\22\3\0\1\22\1\0\1\22\4\0\17\22\1\327" +
			"\10\22\4\0\2\22\4\0\2\22\1\0\1\22\3\0" +
			"\1\22\1\0\1\22\4\0\2\22\1\330\25\22\4\0" +
			"\2\22\4\0\2\22\1\0\1\22\3\0\1\22\1\0" +
			"\1\22\4\0\4\22\1\331\23\22\4\0\2\22\4\0" +
			"\2\22\1\0\1\22\3\0\1\22\1\0\1\22";

	private static int[] zzUnpackTrans() {
		int[] result = new int[7920];
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
		"Error: pushback defaultValue was too large"
	};

	/**
	 * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
	 */
	private static final int[] ZZ_ATTRIBUTE = zzUnpackAttribute();

	private static final String ZZ_ATTRIBUTE_PACKED_0 =
		"\1\0\1\11\2\1\1\11\23\1\2\11\3\1\4\11" +
			"\2\1\1\11\43\1\2\0\1\11\1\0\2\1\1\0" +
			"\1\11\2\0\37\1\1\11\2\0\1\1\1\0\32\1" +
			"\1\0\1\1\1\0\26\1\2\0\22\1\1\0\34\1";

	private static int[] zzUnpackAttribute() {
		int[] result = new int[217];
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
	private BlockManager blockManager = new BlockManager();
	private Queue<IElementType> queue = new LinkedList<>();
	private boolean end = false;

	private IElementType sendToken() {
		IElementType token = (end) ? null : TaraTypes.NEWLINE;
		if (!queue.isEmpty())
			token = queue.poll();
		if (!queue.isEmpty())
			yypushback(yylength());
		return token;
	}

	private IElementType eof() {
		if (queue.isEmpty() && !end) {
			blockManager.eof();
			storeTokens();
			end = true;
			queue.add(TaraTypes.NEWLINE);
		}
		return sendToken();
	}

	private String getTextSpaces(String text) {
		int index = (text.indexOf(' ') == -1) ? text.indexOf('\t') : text.indexOf(' ');
		return (index == -1) ? "" : text.substring(index);
	}

	private boolean isWhiteLineOrEOF() {
		return (zzMarkedPos >= zzBuffer.length()) || (zzMarkedPos < zzBuffer.length() && zzBuffer.charAt(zzMarkedPos) == '\n');
	}

	private IElementType newlineIndent() {
		if (isWhiteLineOrEOF()) return TokenType.WHITE_SPACE;
		if (queue.isEmpty()) {
			String spaces = getTextSpaces(yytext().toString());
			blockManager.spaces(spaces);
			storeTokens();
		}
		return sendToken();
	}

	private IElementType inline() {
		blockManager.openBracket(yytext().length());
		storeTokens();
		return sendToken();
	}

	private IElementType semicolon() {
		blockManager.semicolon(yytext().length());
		storeTokens();
		return sendToken();
	}

	private void storeTokens() {
		blockManager.actions();
		for (IElementType token : blockManager.actions())
			queue.offer(token);
	}


	TaraLexer(java.io.Reader in) {
		this.zzReader = in;
	}

	/**
	 * Creates a new scanner.
	 * There is also java.io.Reader version of this constructor.
	 *
	 * @param in the java.io.Inputstream to read input from.
	 */
	TaraLexer(java.io.InputStream in) {
		this(new java.io.InputStreamReader(in));
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
		while (i < 1746) {
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
	 * <p/>
	 * It is equivalent to yytext().charAt(pos), but faster
	 *
	 * @param pos the position of the character to fetch.
	 *            A defaultValue from 0 to yylength()-1.
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
	 * <p/>
	 * In a wellformed scanner (no or only correct usage of
	 * yypushback(int) and a match-all fallback rule) this method
	 * will only be called with things that "Can't Possibly Happen".
	 * If this method is called, something is seriously wrong
	 * (e.g. a JFlex bug producing a faulty scanner etc.).
	 * <p/>
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

		throw new Error(message);
	}


	/**
	 * Pushes the specified amount of characters back into the input stream.
	 * <p/>
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
				case 16: {
					return TaraTypes.GRADE;
				}
				case 62:
					break;
				case 39: {
					return TaraTypes.STRING_MULTILINE_VALUE_KEY;
				}
				case 63:
					break;
				case 37: {
					return TaraTypes.WITH;
				}
				case 64:
					break;
				case 30: {
					return TaraTypes.VAR;
				}
				case 65:
					break;
				case 11: {
					return TaraTypes.COLON;
				}
				case 66:
					break;
				case 15: {
					return TaraTypes.PERCENTAGE;
				}
				case 67:
					break;
				case 44: {
					return TaraTypes.SINGLE;
				}
				case 68:
					break;
				case 24: {
					return TaraTypes.CODE_VALUE_KEY;
				}
				case 69:
					break;
				case 58: {
					return TaraTypes.COMPONENT;
				}
				case 70:
					break;
				case 32: {
					return TaraTypes.DOUBLE_VALUE_KEY;
				}
				case 71:
					break;
				case 2: {
					return TokenType.WHITE_SPACE;
				}
				case 72:
					break;
				case 48: {
					return TaraTypes.NATURAL_TYPE;
				}
				case 73:
					break;
				case 7: {
					return TaraTypes.RIGHT_PARENTHESIS;
				}
				case 74:
					break;
				case 31: {
					return TaraTypes.LIST;
				}
				case 75:
					break;
				case 12: {
					return TaraTypes.EQUALS;
				}
				case 76:
					break;
				case 8: {
					return TaraTypes.DOT;
				}
				case 77:
					break;
				case 43: {
					return TaraTypes.STRING_TYPE;
				}
				case 78:
					break;
				case 40: {
					return TaraTypes.NAMED;
				}
				case 79:
					break;
				case 55: {
					return TaraTypes.TERMINAL;
				}
				case 80:
					break;
				case 38: {
					return TaraTypes.DATE_TYPE;
				}
				case 81:
					break;
				case 5: {
					return TaraTypes.IDENTIFIER_KEY;
				}
				case 82:
					break;
				case 41: {
					return TaraTypes.EMPTY_REF;
				}
				case 83:
					break;
				case 6: {
					return TaraTypes.LEFT_PARENTHESIS;
				}
				case 84:
					break;
				case 57: {
					return TaraTypes.REQUIRED;
				}
				case 85:
					break;
				case 50: {
					return TaraTypes.PRIVATE;
				}
				case 86:
					break;
				case 23: {
					return TaraTypes.NEGATIVE_VALUE_KEY;
				}
				case 87:
					break;
				case 4: {
					return inline();
				}
				case 88:
					break;
				case 25: {
					return TaraTypes.DOC_LINE;
				}
				case 89:
					break;
				case 49: {
					return TaraTypes.EXTENDS;
				}
				case 90:
					break;
				case 22: {
					return TaraTypes.STRING_VALUE_KEY;
				}
				case 91:
					break;
				case 54: {
					return TaraTypes.PROPERTY;
				}
				case 92:
					break;
				case 9: {
					return TaraTypes.STAR;
				}
				case 93:
					break;
				case 46: {
					return TaraTypes.DOUBLE_TYPE;
				}
				case 94:
					break;
				case 18: {
					return TaraTypes.NATURAL_VALUE_KEY;
				}
				case 95:
					break;
				case 53: {
					return TaraTypes.COORDINATE_VALUE_KEY;
				}
				case 96:
					break;
				case 17: {
					return semicolon();
				}
				case 97:
					break;
				case 1: {
					return TokenType.BAD_CHARACTER;
				}
				case 98:
					break;
				case 56: {
					return TaraTypes.RESOURCE_KEY;
				}
				case 99:
					break;
				case 27: {
					return TaraTypes.USE_KEY;
				}
				case 100:
					break;
				case 45: {
					return TaraTypes.ALWAYS;
				}
				case 101:
					break;
				case 35: {
					return TaraTypes.BOOLEAN_VALUE_KEY;
				}
				case 102:
					break;
				case 20: {
					return TaraTypes.AS;
				}
				case 103:
					break;
				case 19: {
					return TaraTypes.ON;
				}
				case 104:
					break;
				case 26: {
					return TaraTypes.SUB;
				}
				case 105:
					break;
				case 51: {
					return TaraTypes.BOOLEAN_TYPE;
				}
				case 106:
					break;
				case 21: {
					return TaraTypes.IS;
				}
				case 107:
					break;
				case 34: {
					return TaraTypes.PORT_TYPE;
				}
				case 108:
					break;
				case 52: {
					return TaraTypes.INT_TYPE;
				}
				case 109:
					break;
				case 47: {
					return TaraTypes.METAIDENTIFIER_KEY;
				}
				case 110:
					break;
				case 33: {
					return TaraTypes.DATE_VALUE_KEY;
				}
				case 111:
					break;
				case 61: {
					return TaraTypes.COORDINATE_TYPE;
				}
				case 112:
					break;
				case 59: {
					return TaraTypes.UNIVERSAL;
				}
				case 113:
					break;
				case 14: {
					return TaraTypes.EURO;
				}
				case 114:
					break;
				case 36: {
					return TaraTypes.WORD_KEY;
				}
				case 115:
					break;
				case 60: {
					return TaraTypes.INTENTION;
				}
				case 116:
					break;
				case 13: {
					return TaraTypes.DOLLAR;
				}
				case 117:
					break;
				case 42: {
					return TaraTypes.FACET;
				}
				case 118:
					break;
				case 10: {
					return TaraTypes.COMMA;
				}
				case 119:
					break;
				case 29: {
					return TaraTypes.HAS;
				}
				case 120:
					break;
				case 28: {
					return TaraTypes.BOX_KEY;
				}
				case 121:
					break;
				case 3: {
					return newlineIndent();
				}
				case 122:
					break;
				default:
					if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
						zzAtEOF = true;
						switch (zzLexicalState) {
							case YYINITIAL: {
								return eof();
							}
							case 218:
								break;
							default:
								return null;
						}
					} else {
						zzScanError(ZZ_NO_MATCH);
					}
			}
		}
	}


}
