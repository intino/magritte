/* The following code was generated by JFlex 1.4.3 on 14/01/14 13:16 */

package monet.tara.intellij.metamodel;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import monet.tara.intellij.psi.TaraTypes;

import java.util.Stack;


/**
 * This class is a scanner generated by
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 14/01/14 13:16 from the specification file
 * <tt>/Users/oroncal/workspace/tara/intellij/src/monet/tara/intellij/metamodel/Tara.flex</tt>
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
		"\11\71\1\2\1\4\2\0\1\3\16\71\4\0\1\1\1\0\1\54" +
			"\1\0\1\70\6\0\1\57\1\0\1\26\1\52\1\66\12\67\1\53" +
			"\1\0\1\55\1\0\1\56\1\65\1\0\1\43\1\41\1\5\1\62" +
			"\1\35\1\36\1\46\1\47\1\45\1\70\1\44\1\34\1\70\1\61" +
			"\3\70\1\42\1\63\1\37\1\60\1\70\1\31\3\70\1\50\1\0" +
			"\1\51\1\0\1\40\1\0\1\17\1\23\1\10\1\27\1\11\1\14" +
			"\1\64\1\25\1\21\2\70\1\22\1\16\1\7\1\6\1\12\1\70" +
			"\1\15\1\20\1\13\1\24\1\32\1\70\1\30\2\70\2\0\1\33" +
			"\1\0\41\71\2\0\4\70\4\0\1\70\2\0\1\71\7\0\1\70" +
			"\4\0\1\70\5\0\27\70\1\0\37\70\1\0\u013f\70\31\0\162\70" +
			"\4\0\14\70\16\0\5\70\11\0\1\70\21\0\130\71\5\0\23\71" +
			"\12\0\1\70\13\0\1\70\1\0\3\70\1\0\1\70\1\0\24\70" +
			"\1\0\54\70\1\0\46\70\1\0\5\70\4\0\202\70\1\0\4\71" +
			"\3\0\105\70\1\0\46\70\2\0\2\70\6\0\20\70\41\0\46\70" +
			"\2\0\1\70\7\0\47\70\11\0\21\71\1\0\27\71\1\0\3\71" +
			"\1\0\1\71\1\0\2\71\1\0\1\71\13\0\33\70\5\0\3\70" +
			"\15\0\4\71\14\0\6\71\13\0\32\70\5\0\13\70\16\71\7\0" +
			"\12\67\4\0\2\70\1\71\143\70\1\0\1\70\10\71\1\0\6\71" +
			"\2\70\2\71\1\0\4\71\2\70\12\67\3\70\2\0\1\70\17\0" +
			"\1\71\1\70\1\71\36\70\33\71\2\0\3\70\60\0\46\70\13\71" +
			"\1\70\u014f\0\3\71\66\70\2\0\1\71\1\70\20\71\2\0\1\70" +
			"\4\71\3\0\12\70\2\71\2\0\12\67\21\0\3\71\1\0\10\70" +
			"\2\0\2\70\2\0\26\70\1\0\7\70\1\0\1\70\3\0\4\70" +
			"\2\0\1\71\1\70\7\71\2\0\2\71\2\0\3\71\11\0\1\71" +
			"\4\0\2\70\1\0\3\70\2\71\2\0\12\67\4\70\15\0\3\71" +
			"\1\0\6\70\4\0\2\70\2\0\26\70\1\0\7\70\1\0\2\70" +
			"\1\0\2\70\1\0\2\70\2\0\1\71\1\0\5\71\4\0\2\71" +
			"\2\0\3\71\13\0\4\70\1\0\1\70\7\0\12\67\2\71\3\70" +
			"\14\0\3\71\1\0\11\70\1\0\3\70\1\0\26\70\1\0\7\70" +
			"\1\0\2\70\1\0\5\70\2\0\1\71\1\70\10\71\1\0\3\71" +
			"\1\0\3\71\2\0\1\70\17\0\2\70\2\71\2\0\12\67\1\0" +
			"\1\70\17\0\3\71\1\0\10\70\2\0\2\70\2\0\26\70\1\0" +
			"\7\70\1\0\2\70\1\0\5\70\2\0\1\71\1\70\6\71\3\0" +
			"\2\71\2\0\3\71\10\0\2\71\4\0\2\70\1\0\3\70\4\0" +
			"\12\67\1\0\1\70\20\0\1\71\1\70\1\0\6\70\3\0\3\70" +
			"\1\0\4\70\3\0\2\70\1\0\1\70\1\0\2\70\3\0\2\70" +
			"\3\0\3\70\3\0\10\70\1\0\3\70\4\0\5\71\3\0\3\71" +
			"\1\0\4\71\11\0\1\71\17\0\11\67\11\0\1\70\7\0\3\71" +
			"\1\0\10\70\1\0\3\70\1\0\27\70\1\0\12\70\1\0\5\70" +
			"\4\0\7\71\1\0\3\71\1\0\4\71\7\0\2\71\11\0\2\70" +
			"\4\0\12\67\22\0\2\71\1\0\10\70\1\0\3\70\1\0\27\70" +
			"\1\0\12\70\1\0\5\70\2\0\1\71\1\70\7\71\1\0\3\71" +
			"\1\0\4\71\7\0\2\71\7\0\1\70\1\0\2\70\4\0\12\67" +
			"\22\0\2\71\1\0\10\70\1\0\3\70\1\0\27\70\1\0\20\70" +
			"\4\0\6\71\2\0\3\71\1\0\4\71\11\0\1\71\10\0\2\70" +
			"\4\0\12\67\22\0\2\71\1\0\22\70\3\0\30\70\1\0\11\70" +
			"\1\0\1\70\2\0\7\70\3\0\1\71\4\0\6\71\1\0\1\71" +
			"\1\0\10\71\22\0\2\71\15\0\60\70\1\71\2\70\7\71\4\0" +
			"\10\70\10\71\1\0\12\67\47\0\2\70\1\0\1\70\2\0\2\70" +
			"\1\0\1\70\2\0\1\70\6\0\4\70\1\0\7\70\1\0\3\70" +
			"\1\0\1\70\1\0\1\70\2\0\2\70\1\0\4\70\1\71\2\70" +
			"\6\71\1\0\2\71\1\70\2\0\5\70\1\0\1\70\1\0\6\71" +
			"\2\0\12\67\2\0\2\70\42\0\1\70\27\0\2\71\6\0\12\67" +
			"\13\0\1\71\1\0\1\71\1\0\1\71\4\0\2\71\10\70\1\0" +
			"\42\70\6\0\24\71\1\0\2\71\4\70\4\0\10\71\1\0\44\71" +
			"\11\0\1\71\71\0\42\70\1\0\5\70\1\0\2\70\1\0\7\71" +
			"\3\0\4\71\6\0\12\67\6\0\6\70\4\71\106\0\46\70\12\0" +
			"\51\70\7\0\132\70\5\0\104\70\5\0\122\70\6\0\7\70\1\0" +
			"\77\70\1\0\1\70\1\0\4\70\2\0\7\70\1\0\1\70\1\0" +
			"\4\70\2\0\47\70\1\0\1\70\1\0\4\70\2\0\37\70\1\0" +
			"\1\70\1\0\4\70\2\0\7\70\1\0\1\70\1\0\4\70\2\0" +
			"\7\70\1\0\7\70\1\0\27\70\1\0\37\70\1\0\1\70\1\0" +
			"\4\70\2\0\7\70\1\0\47\70\1\0\23\70\16\0\11\67\56\0" +
			"\125\70\14\0\u026c\70\2\0\10\70\12\0\32\70\5\0\113\70\3\0" +
			"\3\70\17\0\15\70\1\0\4\70\3\71\13\0\22\70\3\71\13\0" +
			"\22\70\2\71\14\0\15\70\1\0\3\70\1\0\2\71\14\0\64\70" +
			"\40\71\3\0\1\70\3\0\2\70\1\71\2\0\12\67\41\0\3\71" +
			"\2\0\12\67\6\0\130\70\10\0\51\70\1\71\126\0\35\70\3\0" +
			"\14\71\4\0\14\71\12\0\12\67\36\70\2\0\5\70\u038b\0\154\70" +
			"\224\0\234\70\4\0\132\70\6\0\26\70\2\0\6\70\2\0\46\70" +
			"\2\0\6\70\2\0\10\70\1\0\1\70\1\0\1\70\1\0\1\70" +
			"\1\0\37\70\2\0\65\70\1\0\7\70\1\0\1\70\3\0\3\70" +
			"\1\0\7\70\3\0\4\70\2\0\6\70\4\0\15\70\5\0\3\70" +
			"\1\0\7\70\17\0\4\71\32\0\5\71\20\0\2\70\23\0\1\70" +
			"\13\0\4\71\6\0\6\71\1\0\1\70\15\0\1\70\40\0\22\70" +
			"\36\0\15\71\4\0\1\71\3\0\6\71\27\0\1\70\4\0\1\70" +
			"\2\0\12\70\1\0\1\70\3\0\5\70\6\0\1\70\1\0\1\70" +
			"\1\0\1\70\1\0\4\70\1\0\3\70\1\0\7\70\3\0\3\70" +
			"\5\0\5\70\26\0\44\70\u0e81\0\3\70\31\0\11\70\6\71\1\0" +
			"\5\70\2\0\5\70\4\0\126\70\2\0\2\71\2\0\3\70\1\0" +
			"\137\70\5\0\50\70\4\0\136\70\21\0\30\70\70\0\20\70\u0200\0" +
			"\u19b6\70\112\0\u51a6\70\132\0\u048d\70\u0773\0\u2ba4\70\u215c\0\u012e\70\2\0" +
			"\73\70\225\0\7\70\14\0\5\70\5\0\1\70\1\71\12\70\1\0" +
			"\15\70\1\0\5\70\1\0\1\70\1\0\2\70\1\0\2\70\1\0" +
			"\154\70\41\0\u016b\70\22\0\100\70\2\0\66\70\50\0\15\70\3\0" +
			"\20\71\20\0\4\71\17\0\2\70\30\0\3\70\31\0\1\70\6\0" +
			"\5\70\1\0\207\70\2\0\1\71\4\0\1\70\13\0\12\67\7\0" +
			"\32\70\4\0\1\70\1\0\32\70\12\0\132\70\3\0\6\70\2\0" +
			"\6\70\2\0\6\70\2\0\3\70\3\0\2\70\3\0\2\70\22\0" +
			"\3\71\4\0";

	/**
	 * Translates characters to character classes
	 */
	private static final char[] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

	/**
	 * Translates DFA states to action switch labels.
	 */
	private static final int[] ZZ_ACTION = zzUnpackAction();

	private static final String ZZ_ACTION_PACKED_0 =
		"\1\0\1\1\2\2\2\3\10\4\1\1\2\4\1\5" +
			"\5\4\1\6\1\7\1\10\1\1\1\11\1\12\1\1" +
			"\4\4\2\1\1\13\2\14\1\0\6\4\1\15\2\4" +
			"\1\16\7\4\1\0\1\17\4\4\3\0\11\4\1\20" +
			"\4\4\1\21\1\22\3\4\1\23\1\0\1\24\3\4" +
			"\1\25\3\4\1\0\1\26\1\4\1\27\4\4\1\0" +
			"\3\4\1\30\2\4\1\0\5\4\1\31\5\4\1\0" +
			"\3\4\1\32\1\33\1\34\4\4\1\0\1\4\1\35" +
			"\1\36\1\37\1\4\1\40\1\41\1\42\3\4\1\43" +
			"\17\4\1\44";

	private static int[] zzUnpackAction() {
		int[] result = new int[162];
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
		"\0\0\0\72\0\164\0\256\0\72\0\350\0\u0122\0\u015c" +
			"\0\u0196\0\u01d0\0\u020a\0\u0244\0\u027e\0\u02b8\0\u02f2\0\u032c" +
			"\0\u0366\0\72\0\u03a0\0\u03da\0\u0414\0\u044e\0\u0488\0\72" +
			"\0\72\0\72\0\u04c2\0\72\0\72\0\u04fc\0\u0536\0\u0570" +
			"\0\u05aa\0\u05e4\0\u061e\0\u0658\0\u0692\0\u06cc\0\u0706\0\350" +
			"\0\u0740\0\u077a\0\u07b4\0\u07ee\0\u0828\0\u0862\0\u0196\0\u089c" +
			"\0\u08d6\0\u0910\0\u094a\0\u0984\0\u09be\0\u09f8\0\u0a32\0\u0a6c" +
			"\0\u0aa6\0\u04c2\0\72\0\u0ae0\0\u0b1a\0\u0b54\0\u0b8e\0\u0bc8" +
			"\0\u0c02\0\u0c3c\0\u0c76\0\u0cb0\0\u0cea\0\u0d24\0\u0d5e\0\u0d98" +
			"\0\u0dd2\0\u0e0c\0\u0e46\0\u0196\0\u0e80\0\u0eba\0\u0ef4\0\u0f2e" +
			"\0\u0196\0\u0196\0\u0f68\0\u0fa2\0\u0fdc\0\72\0\u1016\0\u0c3c" +
			"\0\u1050\0\u108a\0\u10c4\0\u0196\0\u10fe\0\u1138\0\u1172\0\u11ac" +
			"\0\u0196\0\u11e6\0\u0196\0\u1220\0\u125a\0\u1294\0\u12ce\0\u1308" +
			"\0\u1342\0\u137c\0\u13b6\0\u0196\0\u13f0\0\u142a\0\u1464\0\u149e" +
			"\0\u14d8\0\u1512\0\u154c\0\u1586\0\72\0\u15c0\0\u15fa\0\u1634" +
			"\0\u166e\0\u16a8\0\u16e2\0\u171c\0\u1756\0\u1790\0\u0196\0\u0196" +
			"\0\u0196\0\u17ca\0\u1804\0\u183e\0\u1878\0\u18b2\0\u18ec\0\u0196" +
			"\0\u0196\0\u0196\0\u1926\0\u0196\0\u0196\0\72\0\u1960\0\u199a" +
			"\0\u19d4\0\u0196\0\u1a0e\0\u1a48\0\u1a82\0\u1abc\0\u1af6\0\u1b30" +
			"\0\u1b6a\0\u1ba4\0\u1bde\0\u1c18\0\u1c52\0\u1c8c\0\u1cc6\0\u1d00" +
			"\0\u1d3a\0\u0196";

	private static int[] zzUnpackRowMap() {
		int[] result = new int[162];
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
		"\1\2\1\3\1\4\1\5\1\6\1\7\1\10\2\11" +
			"\1\12\2\11\1\13\1\11\1\14\1\15\5\11\1\16" +
			"\1\17\2\11\1\20\1\21\1\22\1\23\1\11\1\24" +
			"\1\25\1\11\1\26\3\11\1\27\2\11\1\30\1\31" +
			"\1\2\1\32\1\33\1\34\1\35\1\36\1\37\1\40" +
			"\1\41\1\42\1\11\1\43\1\44\1\45\1\11\1\2" +
			"\73\0\1\3\72\0\1\4\70\0\1\46\1\47\1\0" +
			"\1\50\72\0\1\11\1\51\17\11\1\0\4\11\1\0" +
			"\14\11\10\0\5\11\2\0\3\11\5\0\5\11\1\52" +
			"\13\11\1\0\4\11\1\0\14\11\10\0\5\11\2\0" +
			"\3\11\5\0\21\11\1\0\4\11\1\0\14\11\10\0" +
			"\5\11\2\0\3\11\5\0\21\11\1\0\1\11\1\53" +
			"\2\11\1\0\14\11\10\0\5\11\2\0\3\11\5\0" +
			"\10\11\1\54\3\11\1\55\4\11\1\0\4\11\1\0" +
			"\14\11\10\0\5\11\2\0\3\11\5\0\17\11\1\56" +
			"\1\11\1\0\4\11\1\0\14\11\10\0\5\11\2\0" +
			"\3\11\5\0\13\11\1\57\2\11\1\60\2\11\1\0" +
			"\4\11\1\0\14\11\10\0\5\11\2\0\3\11\5\0" +
			"\12\11\1\61\6\11\1\0\4\11\1\0\14\11\10\0" +
			"\5\11\2\0\3\11\67\0\1\62\7\0\1\11\1\63" +
			"\17\11\1\0\4\11\1\0\14\11\10\0\5\11\2\0" +
			"\3\11\5\0\12\11\1\64\6\11\1\0\4\11\1\0" +
			"\14\11\10\0\5\11\2\0\3\11\5\0\21\11\1\0" +
			"\4\11\1\0\1\11\1\65\12\11\10\0\5\11\2\0" +
			"\3\11\5\0\12\11\1\66\6\11\1\0\4\11\1\0" +
			"\14\11\10\0\5\11\2\0\3\11\5\0\10\11\1\67" +
			"\10\11\1\0\4\11\1\0\14\11\10\0\5\11\2\0" +
			"\3\11\5\0\1\11\1\70\17\11\1\0\4\11\1\0" +
			"\14\11\10\0\5\11\2\0\3\11\5\0\2\11\1\71" +
			"\16\11\1\0\4\11\1\0\14\11\10\0\5\11\2\0" +
			"\3\11\1\0\1\72\3\0\21\72\1\0\4\72\1\0" +
			"\14\72\4\0\1\73\3\0\5\72\2\0\3\72\67\0" +
			"\1\45\7\0\14\11\1\74\4\11\1\0\4\11\1\0" +
			"\14\11\10\0\5\11\2\0\3\11\5\0\12\11\1\75" +
			"\6\11\1\0\4\11\1\0\14\11\10\0\5\11\2\0" +
			"\3\11\5\0\1\11\1\76\17\11\1\0\4\11\1\0" +
			"\14\11\10\0\5\11\2\0\3\11\5\0\6\11\1\77" +
			"\12\11\1\0\4\11\1\0\14\11\10\0\5\11\2\0" +
			"\3\11\65\0\1\100\71\0\1\101\56\0\1\102\14\0" +
			"\1\45\3\0\1\46\72\0\1\47\74\0\2\11\1\103" +
			"\16\11\1\0\4\11\1\0\14\11\10\0\5\11\2\0" +
			"\3\11\5\0\6\11\1\104\12\11\1\0\4\11\1\0" +
			"\14\11\10\0\5\11\2\0\3\11\5\0\6\11\1\105" +
			"\12\11\1\0\4\11\1\0\14\11\10\0\5\11\2\0" +
			"\3\11\5\0\1\11\1\106\17\11\1\0\4\11\1\0" +
			"\14\11\10\0\5\11\2\0\3\11\5\0\2\11\1\107" +
			"\16\11\1\0\4\11\1\0\14\11\10\0\5\11\2\0" +
			"\3\11\5\0\15\11\1\110\3\11\1\0\4\11\1\0" +
			"\14\11\10\0\5\11\2\0\3\11\5\0\13\11\1\111" +
			"\5\11\1\0\4\11\1\0\14\11\10\0\5\11\2\0" +
			"\3\11\5\0\13\11\1\112\5\11\1\0\4\11\1\0" +
			"\14\11\10\0\5\11\2\0\3\11\52\0\1\102\14\0" +
			"\1\62\7\0\10\11\1\113\10\11\1\0\4\11\1\0" +
			"\14\11\10\0\5\11\2\0\3\11\5\0\10\11\1\114" +
			"\10\11\1\0\4\11\1\0\14\11\10\0\5\11\2\0" +
			"\3\11\5\0\21\11\1\0\4\11\1\0\2\11\1\115" +
			"\11\11\10\0\5\11\2\0\3\11\5\0\15\11\1\116" +
			"\3\11\1\0\4\11\1\0\14\11\10\0\5\11\2\0" +
			"\3\11\5\0\17\11\1\117\1\11\1\0\4\11\1\0" +
			"\14\11\10\0\5\11\2\0\3\11\5\0\1\11\1\120" +
			"\17\11\1\0\4\11\1\0\14\11\10\0\5\11\2\0" +
			"\3\11\5\0\6\11\1\121\12\11\1\0\4\11\1\0" +
			"\14\11\10\0\5\11\2\0\3\11\5\0\21\11\1\0" +
			"\1\122\3\11\1\0\14\11\10\0\5\11\2\0\3\11" +
			"\5\0\6\11\1\123\12\11\1\0\4\11\1\0\14\11" +
			"\10\0\5\11\2\0\3\11\5\0\17\11\1\124\1\11" +
			"\1\0\4\11\1\0\14\11\10\0\5\11\2\0\3\11" +
			"\5\0\10\11\1\125\10\11\1\0\4\11\1\0\14\11" +
			"\10\0\5\11\2\0\3\11\4\100\1\126\65\100\65\101" +
			"\1\127\1\0\3\101\67\0\1\130\7\0\3\11\1\131" +
			"\15\11\1\0\4\11\1\0\14\11\10\0\5\11\2\0" +
			"\3\11\5\0\14\11\1\132\4\11\1\0\4\11\1\0" +
			"\14\11\10\0\5\11\2\0\3\11\5\0\4\11\1\133" +
			"\14\11\1\0\4\11\1\0\14\11\10\0\5\11\2\0" +
			"\3\11\5\0\11\11\1\134\7\11\1\0\4\11\1\0" +
			"\14\11\10\0\5\11\2\0\3\11\5\0\12\11\1\135" +
			"\6\11\1\0\4\11\1\0\14\11\10\0\5\11\2\0" +
			"\3\11\5\0\6\11\1\136\12\11\1\0\4\11\1\0" +
			"\14\11\10\0\5\11\2\0\3\11\5\0\6\11\1\137" +
			"\12\11\1\0\4\11\1\0\14\11\10\0\5\11\2\0" +
			"\3\11\5\0\21\11\1\140\4\11\1\0\14\11\10\0" +
			"\5\11\2\0\3\11\5\0\21\11\1\0\1\141\3\11" +
			"\1\0\14\11\10\0\5\11\2\0\3\11\5\0\21\11" +
			"\1\0\4\11\1\0\3\11\1\142\10\11\10\0\5\11" +
			"\2\0\3\11\5\0\13\11\1\117\5\11\1\0\4\11" +
			"\1\0\14\11\10\0\5\11\2\0\3\11\5\0\4\11" +
			"\1\143\14\11\1\0\4\11\1\0\14\11\10\0\5\11" +
			"\2\0\3\11\5\0\15\11\1\144\3\11\1\0\4\11" +
			"\1\0\14\11\10\0\5\11\2\0\3\11\5\0\17\11" +
			"\1\145\1\11\1\0\4\11\1\0\14\11\10\0\5\11" +
			"\2\0\3\11\5\0\16\11\1\146\2\11\1\0\4\11" +
			"\1\0\14\11\10\0\5\11\2\0\3\11\5\0\14\11" +
			"\1\147\4\11\1\0\4\11\1\0\14\11\10\0\5\11" +
			"\2\0\3\11\66\0\1\150\10\0\4\11\1\151\14\11" +
			"\1\0\4\11\1\0\14\11\10\0\5\11\2\0\3\11" +
			"\5\0\1\11\1\152\17\11\1\0\4\11\1\0\14\11" +
			"\10\0\5\11\2\0\3\11\5\0\2\11\1\153\16\11" +
			"\1\0\4\11\1\0\14\11\10\0\5\11\2\0\3\11" +
			"\5\0\15\11\1\154\3\11\1\0\4\11\1\0\14\11" +
			"\10\0\5\11\2\0\3\11\5\0\14\11\1\155\4\11" +
			"\1\0\4\11\1\0\14\11\10\0\5\11\2\0\3\11" +
			"\5\0\10\11\1\156\10\11\1\0\4\11\1\0\14\11" +
			"\10\0\5\11\2\0\3\11\10\0\1\157\66\0\21\11" +
			"\1\0\4\11\1\0\4\11\1\160\7\11\10\0\5\11" +
			"\2\0\3\11\5\0\4\11\1\161\14\11\1\0\4\11" +
			"\1\0\14\11\10\0\5\11\2\0\3\11\5\0\10\11" +
			"\1\162\10\11\1\0\4\11\1\0\14\11\10\0\5\11" +
			"\2\0\3\11\5\0\15\11\1\163\3\11\1\0\4\11" +
			"\1\0\14\11\10\0\5\11\2\0\3\11\5\0\2\11" +
			"\1\164\16\11\1\0\4\11\1\0\14\11\10\0\5\11" +
			"\2\0\3\11\4\0\1\165\72\0\5\11\1\166\13\11" +
			"\1\0\4\11\1\0\14\11\10\0\5\11\2\0\3\11" +
			"\5\0\2\11\1\167\16\11\1\0\4\11\1\0\14\11" +
			"\10\0\5\11\2\0\3\11\5\0\13\11\1\170\5\11" +
			"\1\0\4\11\1\0\14\11\10\0\5\11\2\0\3\11" +
			"\5\0\5\11\1\171\13\11\1\0\4\11\1\0\14\11" +
			"\10\0\5\11\2\0\3\11\5\0\12\11\1\172\6\11" +
			"\1\0\4\11\1\0\14\11\10\0\5\11\2\0\3\11" +
			"\6\0\1\173\70\0\21\11\1\0\4\11\1\0\5\11" +
			"\1\174\6\11\10\0\5\11\2\0\3\11\5\0\12\11" +
			"\1\175\6\11\1\0\4\11\1\0\14\11\10\0\5\11" +
			"\2\0\3\11\5\0\12\11\1\176\6\11\1\0\4\11" +
			"\1\0\14\11\10\0\5\11\2\0\3\11\5\0\4\11" +
			"\1\177\14\11\1\0\4\11\1\0\14\11\10\0\5\11" +
			"\2\0\3\11\5\0\21\11\1\0\4\11\1\0\14\11" +
			"\10\0\4\11\1\200\2\0\3\11\5\0\6\11\1\201" +
			"\12\11\1\0\4\11\1\0\14\11\10\0\5\11\2\0" +
			"\3\11\5\0\12\11\1\202\6\11\1\0\4\11\1\0" +
			"\14\11\10\0\5\11\2\0\3\11\5\0\14\11\1\203" +
			"\4\11\1\0\4\11\1\0\14\11\10\0\5\11\2\0" +
			"\3\11\5\0\15\11\1\204\3\11\1\0\4\11\1\0" +
			"\14\11\10\0\5\11\2\0\3\11\5\0\3\11\1\205" +
			"\15\11\1\0\4\11\1\0\14\11\10\0\5\11\2\0" +
			"\3\11\27\0\1\206\47\0\21\11\1\0\4\11\1\0" +
			"\6\11\1\207\5\11\10\0\5\11\2\0\3\11\5\0" +
			"\2\11\1\210\16\11\1\0\4\11\1\0\14\11\10\0" +
			"\5\11\2\0\3\11\5\0\15\11\1\211\3\11\1\0" +
			"\4\11\1\0\14\11\10\0\5\11\2\0\3\11\5\0" +
			"\15\11\1\212\3\11\1\0\4\11\1\0\14\11\10\0" +
			"\5\11\2\0\3\11\5\0\16\11\1\213\2\11\1\0" +
			"\4\11\1\0\14\11\10\0\5\11\2\0\3\11\5\0" +
			"\4\11\1\214\14\11\1\0\4\11\1\0\14\11\10\0" +
			"\5\11\2\0\3\11\5\0\6\11\1\215\12\11\1\0" +
			"\4\11\1\0\14\11\10\0\5\11\2\0\3\11\11\0" +
			"\1\216\65\0\21\11\1\0\4\11\1\0\7\11\1\217" +
			"\4\11\10\0\5\11\2\0\3\11\5\0\15\11\1\220" +
			"\3\11\1\0\4\11\1\0\14\11\10\0\5\11\2\0" +
			"\3\11\5\0\1\221\20\11\1\0\4\11\1\0\14\11" +
			"\10\0\5\11\2\0\3\11\5\0\4\11\1\222\14\11" +
			"\1\0\4\11\1\0\14\11\10\0\5\11\2\0\3\11" +
			"\5\0\21\11\1\0\4\11\1\0\10\11\1\223\3\11" +
			"\10\0\5\11\2\0\3\11\5\0\21\11\1\0\4\11" +
			"\1\0\1\11\1\224\12\11\10\0\5\11\2\0\3\11" +
			"\5\0\21\11\1\0\4\11\1\0\3\11\1\225\10\11" +
			"\10\0\5\11\2\0\3\11\5\0\21\11\1\0\4\11" +
			"\1\0\6\11\1\226\5\11\10\0\5\11\2\0\3\11" +
			"\5\0\21\11\1\0\4\11\1\0\11\11\1\227\2\11" +
			"\10\0\5\11\2\0\3\11\5\0\21\11\1\0\4\11" +
			"\1\0\12\11\1\230\1\11\10\0\5\11\2\0\3\11" +
			"\5\0\21\11\1\0\4\11\1\0\13\11\1\231\10\0" +
			"\5\11\2\0\3\11\5\0\21\11\1\0\4\11\1\0" +
			"\3\11\1\232\10\11\10\0\5\11\2\0\3\11\5\0" +
			"\21\11\1\0\4\11\1\0\4\11\1\233\7\11\10\0" +
			"\5\11\2\0\3\11\5\0\21\11\1\0\4\11\1\0" +
			"\5\11\1\234\6\11\10\0\5\11\2\0\3\11\5\0" +
			"\21\11\1\0\4\11\1\0\6\11\1\235\5\11\10\0" +
			"\5\11\2\0\3\11\5\0\21\11\1\0\4\11\1\0" +
			"\7\11\1\236\4\11\10\0\5\11\2\0\3\11\5\0" +
			"\1\237\20\11\1\0\4\11\1\0\14\11\10\0\5\11" +
			"\2\0\3\11\5\0\21\11\1\0\4\11\1\0\10\11" +
			"\1\240\3\11\10\0\5\11\2\0\3\11\5\0\21\11" +
			"\1\0\4\11\1\0\1\11\1\241\12\11\10\0\5\11" +
			"\2\0\3\11\5\0\21\11\1\0\4\11\1\0\3\11" +
			"\1\242\10\11\10\0\5\11\2\0\3\11";

	private static int[] zzUnpackTrans() {
		int[] result = new int[7540];
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
		"\1\0\1\11\2\1\1\11\14\1\1\11\5\1\3\11" +
			"\1\1\2\11\12\1\1\0\21\1\1\0\1\11\4\1" +
			"\3\0\23\1\1\11\1\0\10\1\1\0\7\1\1\0" +
			"\6\1\1\0\5\1\1\11\5\1\1\0\12\1\1\0" +
			"\7\1\1\11\24\1";

	private static int[] zzUnpackAttribute() {
		int[] result = new int[162];
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

	private int yycolumn;

	/**
	 * denotes if the user-EOF-code has already been executed
	 */
	private boolean zzEOFDone;

	/* user code: */
	static Stack<Integer> stack = new Stack<>();

	private int transformToSpaces(CharSequence chain) {
		int value = 0;
		for (int i = 0; i < chain.length(); i++) {
			if (chain.charAt(i) == ('\n')) continue;
			if (chain.charAt(i) == ('\t')) value += 4;
			else value += 1;
		}
		return value;
	}

	private IElementType cleanStack() {
		if (!stack.empty()) {
			stack.pop();
			if (!stack.empty() && isTextDedented(transformToSpaces(yytext())))
				yypushback(yylength());
			return TaraTypes.DEDENT;
		}
		return TokenType.WHITE_SPACE;
	}

	private boolean isTextIndented(int textLength) {
		synchronized (stack) {
			if (!stack.empty())
				return textLength > stack.peek();
			return false;
		}
	}

	private boolean isTextDedented(int textLength) {
		synchronized (stack) {
			if (!stack.empty())
				return textLength < stack.peek();
			return false;
		}
	}

	private IElementType calculateIndentationToken() {
		int textLength = transformToSpaces(yytext());
		if (stack.empty() || isTextIndented(textLength)) {
			stack.push(textLength);
			return TaraTypes.INDENT;
		} else if (isTextDedented(textLength)) {
			stack.pop();
			if (isTextDedented(textLength))
				yypushback(yylength());
			return TaraTypes.DEDENT;
		} else
			return TokenType.WHITE_SPACE;
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
		while (i < 1784) {
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
	 * Contains user EOF-code, which will be executed exactly once,
	 * when the end of file is reached
	 */
	private void zzDoEOF() {
		if (!zzEOFDone) {
			zzEOFDone = true;
			if (stack.size() > 0) {
				stack.pop();
			}

		}
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

			boolean zzR = false;
			for (zzCurrentPosL = zzStartRead; zzCurrentPosL < zzMarkedPosL;
			     zzCurrentPosL++) {
				switch ((zzBufferArrayL != null ? zzBufferArrayL[zzCurrentPosL] : zzBufferL.charAt(zzCurrentPosL))) {
					case '\u000B':
					case '\u000C':
					case '\u0085':
					case '\u2028':
					case '\u2029':
						yycolumn = 0;
						zzR = false;
						break;
					case '\r':
						yycolumn = 0;
						zzR = true;
						break;
					case '\n':
						if (zzR)
							zzR = false;
						else {
							yycolumn = 0;
						}
						break;
					default:
						zzR = false;
						yycolumn++;
				}
			}

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
				case 12: {
					return calculateIndentationToken();
				}
				case 37:
					break;
				case 22: {
					return TaraTypes.WORD;
				}
				case 38:
					break;
				case 16: {
					return TaraTypes.VAR;
				}
				case 39:
					break;
				case 15: {
					return TaraTypes.STRING_VALUE;
				}
				case 40:
					break;
				case 33: {
					return TaraTypes.ABSTRACT;
				}
				case 41:
					break;
				case 6: {
					return TaraTypes.LEFT_BRACKET;
				}
				case 42:
					break;
				case 9: {
					return TaraTypes.OPEN_AN;
				}
				case 43:
					break;
				case 32: {
					return TaraTypes.MULTIPLE;
				}
				case 44:
					break;
				case 2: {
					return TokenType.WHITE_SPACE;
				}
				case 45:
					break;
				case 30: {
					return TaraTypes.NATURAL_TYPE;
				}
				case 46:
					break;
				case 36: {
					return TaraTypes.LIST;
				}
				case 47:
					break;
				case 10: {
					return TaraTypes.CLOSE_AN;
				}
				case 48:
					break;
				case 23: {
					return TaraTypes.BOOLEAN_VALUE;
				}
				case 49:
					break;
				case 34: {
					return TaraTypes.HAS_CODE;
				}
				case 50:
					break;
				case 24: {
					return TaraTypes.FINAL;
				}
				case 51:
					break;
				case 11: {
					return TaraTypes.NATURAL_VALUE;
				}
				case 52:
					break;
				case 27: {
					return TaraTypes.STRING_TYPE;
				}
				case 53:
					break;
				case 8: {
					return TaraTypes.ASSIGN;
				}
				case 54:
					break;
				case 31: {
					return TaraTypes.OPTIONAL;
				}
				case 55:
					break;
				case 4: {
					return TaraTypes.IDENTIFIER_KEY;
				}
				case 56:
					break;
				case 18: {
					return TaraTypes.UID_TYPE;
				}
				case 57:
					break;
				case 21: {
					return TaraTypes.FROM_KEY;
				}
				case 58:
					break;
				case 3: {
					return cleanStack();
				}
				case 59:
					break;
				case 14: {
					return TaraTypes.NEGATIVE_VALUE;
				}
				case 60:
					break;
				case 19: {
					return TaraTypes.DOC_LINE;
				}
				case 61:
					break;
				case 26: {
					return TaraTypes.DOUBLE_TYPE;
				}
				case 62:
					break;
				case 1: {
					return TokenType.BAD_CHARACTER;
				}
				case 63:
					break;
				case 13: {
					return TaraTypes.AS;
				}
				case 64:
					break;
				case 20: {
					return TaraTypes.DOUBLE_VALUE;
				}
				case 65:
					break;
				case 35: {
					return TaraTypes.EXTENSIBLE;
				}
				case 66:
					break;
				case 7: {
					return TaraTypes.RIGHT_BRACKET;
				}
				case 67:
					break;
				case 29: {
					return TaraTypes.BOOLEAN_TYPE;
				}
				case 68:
					break;
				case 17: {
					return TaraTypes.INT_TYPE;
				}
				case 69:
					break;
				case 25: {
					return TaraTypes.DOC_BLOCK;
				}
				case 70:
					break;
				case 28: {
					return TaraTypes.CONCEPT_KEY;
				}
				case 71:
					break;
				case 5: {
					return TaraTypes.DEDENT;
				}
				case 72:
					break;
				default:
					if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
						zzAtEOF = true;
						zzDoEOF();
						return null;
					} else {
						zzScanError(ZZ_NO_MATCH);
					}
			}
		}
	}


}
