/* The following code was generated by JFlex 1.4.3 on 21/02/14 10:08 */

package monet.tara.intellij.highlighting;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import monet.tara.compiler.intellij.psi.TaraTypes;


/**
 * This class is a scanner generated by
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 21/02/14 10:08 from the specification file
 * <tt>/Users/oroncal/workspace/tara/intellij/src/monet/tara/intellij/metamodel/TaraHighlighterLex.flex</tt>
 */
class TaraHighlighterLex implements FlexLexer {
	/**
	 * lexical states
	 */
	public static final int YYINITIAL = 0;
	/**
	 * the current lexical state
	 */
	private int zzLexicalState = YYINITIAL;
	/**
	 * initial size of the lookahead buffer
	 */
	private static final int ZZ_BUFFERSIZE = 16384;
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
		"\11\54\1\55\1\51\3\0\16\54\4\0\1\55\1\0\1\36\1\0" +
			"\1\53\2\0\1\50\3\0\1\41\1\0\1\22\1\34\1\0\12\52" +
			"\1\35\1\33\1\37\1\0\1\40\2\0\1\53\1\47\1\1\1\45" +
			"\4\53\1\43\4\53\1\44\4\53\1\46\1\53\1\42\1\53\1\26" +
			"\3\53\1\31\1\0\1\32\1\0\1\53\1\0\1\13\1\17\1\4" +
			"\1\23\1\5\1\10\1\24\1\21\1\15\2\53\1\16\1\12\1\3" +
			"\1\2\1\6\1\53\1\11\1\14\1\7\1\20\1\27\1\30\1\25" +
			"\2\53\1\33\1\0\1\33\1\0\41\54\2\0\4\53\4\0\1\53" +
			"\2\0\1\54\7\0\1\53\4\0\1\53\5\0\27\53\1\0\37\53" +
			"\1\0\u013f\53\31\0\162\53\4\0\14\53\16\0\5\53\11\0\1\53" +
			"\21\0\130\54\5\0\23\54\12\0\1\53\13\0\1\53\1\0\3\53" +
			"\1\0\1\53\1\0\24\53\1\0\54\53\1\0\46\53\1\0\5\53" +
			"\4\0\202\53\1\0\4\54\3\0\105\53\1\0\46\53\2\0\2\53" +
			"\6\0\20\53\41\0\46\53\2\0\1\53\7\0\47\53\11\0\21\54" +
			"\1\0\27\54\1\0\3\54\1\0\1\54\1\0\2\54\1\0\1\54" +
			"\13\0\33\53\5\0\3\53\15\0\4\54\14\0\6\54\13\0\32\53" +
			"\5\0\13\53\16\54\7\0\12\52\4\0\2\53\1\54\143\53\1\0" +
			"\1\53\10\54\1\0\6\54\2\53\2\54\1\0\4\54\2\53\12\52" +
			"\3\53\2\0\1\53\17\0\1\54\1\53\1\54\36\53\33\54\2\0" +
			"\3\53\60\0\46\53\13\54\1\53\u014f\0\3\54\66\53\2\0\1\54" +
			"\1\53\20\54\2\0\1\53\4\54\3\0\12\53\2\54\2\0\12\52" +
			"\21\0\3\54\1\0\10\53\2\0\2\53\2\0\26\53\1\0\7\53" +
			"\1\0\1\53\3\0\4\53\2\0\1\54\1\53\7\54\2\0\2\54" +
			"\2\0\3\54\11\0\1\54\4\0\2\53\1\0\3\53\2\54\2\0" +
			"\12\52\4\53\15\0\3\54\1\0\6\53\4\0\2\53\2\0\26\53" +
			"\1\0\7\53\1\0\2\53\1\0\2\53\1\0\2\53\2\0\1\54" +
			"\1\0\5\54\4\0\2\54\2\0\3\54\13\0\4\53\1\0\1\53" +
			"\7\0\12\52\2\54\3\53\14\0\3\54\1\0\11\53\1\0\3\53" +
			"\1\0\26\53\1\0\7\53\1\0\2\53\1\0\5\53\2\0\1\54" +
			"\1\53\10\54\1\0\3\54\1\0\3\54\2\0\1\53\17\0\2\53" +
			"\2\54\2\0\12\52\1\0\1\53\17\0\3\54\1\0\10\53\2\0" +
			"\2\53\2\0\26\53\1\0\7\53\1\0\2\53\1\0\5\53\2\0" +
			"\1\54\1\53\6\54\3\0\2\54\2\0\3\54\10\0\2\54\4\0" +
			"\2\53\1\0\3\53\4\0\12\52\1\0\1\53\20\0\1\54\1\53" +
			"\1\0\6\53\3\0\3\53\1\0\4\53\3\0\2\53\1\0\1\53" +
			"\1\0\2\53\3\0\2\53\3\0\3\53\3\0\10\53\1\0\3\53" +
			"\4\0\5\54\3\0\3\54\1\0\4\54\11\0\1\54\17\0\11\52" +
			"\11\0\1\53\7\0\3\54\1\0\10\53\1\0\3\53\1\0\27\53" +
			"\1\0\12\53\1\0\5\53\4\0\7\54\1\0\3\54\1\0\4\54" +
			"\7\0\2\54\11\0\2\53\4\0\12\52\22\0\2\54\1\0\10\53" +
			"\1\0\3\53\1\0\27\53\1\0\12\53\1\0\5\53\2\0\1\54" +
			"\1\53\7\54\1\0\3\54\1\0\4\54\7\0\2\54\7\0\1\53" +
			"\1\0\2\53\4\0\12\52\22\0\2\54\1\0\10\53\1\0\3\53" +
			"\1\0\27\53\1\0\20\53\4\0\6\54\2\0\3\54\1\0\4\54" +
			"\11\0\1\54\10\0\2\53\4\0\12\52\22\0\2\54\1\0\22\53" +
			"\3\0\30\53\1\0\11\53\1\0\1\53\2\0\7\53\3\0\1\54" +
			"\4\0\6\54\1\0\1\54\1\0\10\54\22\0\2\54\15\0\60\53" +
			"\1\54\2\53\7\54\4\0\10\53\10\54\1\0\12\52\47\0\2\53" +
			"\1\0\1\53\2\0\2\53\1\0\1\53\2\0\1\53\6\0\4\53" +
			"\1\0\7\53\1\0\3\53\1\0\1\53\1\0\1\53\2\0\2\53" +
			"\1\0\4\53\1\54\2\53\6\54\1\0\2\54\1\53\2\0\5\53" +
			"\1\0\1\53\1\0\6\54\2\0\12\52\2\0\2\53\42\0\1\53" +
			"\27\0\2\54\6\0\12\52\13\0\1\54\1\0\1\54\1\0\1\54" +
			"\4\0\2\54\10\53\1\0\42\53\6\0\24\54\1\0\2\54\4\53" +
			"\4\0\10\54\1\0\44\54\11\0\1\54\71\0\42\53\1\0\5\53" +
			"\1\0\2\53\1\0\7\54\3\0\4\54\6\0\12\52\6\0\6\53" +
			"\4\54\106\0\46\53\12\0\51\53\7\0\132\53\5\0\104\53\5\0" +
			"\122\53\6\0\7\53\1\0\77\53\1\0\1\53\1\0\4\53\2\0" +
			"\7\53\1\0\1\53\1\0\4\53\2\0\47\53\1\0\1\53\1\0" +
			"\4\53\2\0\37\53\1\0\1\53\1\0\4\53\2\0\7\53\1\0" +
			"\1\53\1\0\4\53\2\0\7\53\1\0\7\53\1\0\27\53\1\0" +
			"\37\53\1\0\1\53\1\0\4\53\2\0\7\53\1\0\47\53\1\0" +
			"\23\53\16\0\11\52\56\0\125\53\14\0\u026c\53\2\0\10\53\12\0" +
			"\32\53\5\0\113\53\3\0\3\53\17\0\15\53\1\0\4\53\3\54" +
			"\13\0\22\53\3\54\13\0\22\53\2\54\14\0\15\53\1\0\3\53" +
			"\1\0\2\54\14\0\64\53\40\54\3\0\1\53\3\0\2\53\1\54" +
			"\2\0\12\52\41\0\3\54\2\0\12\52\6\0\130\53\10\0\51\53" +
			"\1\54\126\0\35\53\3\0\14\54\4\0\14\54\12\0\12\52\36\53" +
			"\2\0\5\53\u038b\0\154\53\224\0\234\53\4\0\132\53\6\0\26\53" +
			"\2\0\6\53\2\0\46\53\2\0\6\53\2\0\10\53\1\0\1\53" +
			"\1\0\1\53\1\0\1\53\1\0\37\53\2\0\65\53\1\0\7\53" +
			"\1\0\1\53\3\0\3\53\1\0\7\53\3\0\4\53\2\0\6\53" +
			"\4\0\15\53\5\0\3\53\1\0\7\53\17\0\4\54\32\0\5\54" +
			"\20\0\2\53\23\0\1\53\13\0\4\54\6\0\6\54\1\0\1\53" +
			"\15\0\1\53\40\0\22\53\36\0\15\54\4\0\1\54\3\0\6\54" +
			"\27\0\1\53\4\0\1\53\2\0\12\53\1\0\1\53\3\0\5\53" +
			"\6\0\1\53\1\0\1\53\1\0\1\53\1\0\4\53\1\0\3\53" +
			"\1\0\7\53\3\0\3\53\5\0\5\53\26\0\44\53\u0e81\0\3\53" +
			"\31\0\11\53\6\54\1\0\5\53\2\0\5\53\4\0\126\53\2\0" +
			"\2\54\2\0\3\53\1\0\137\53\5\0\50\53\4\0\136\53\21\0" +
			"\30\53\70\0\20\53\u0200\0\u19b6\53\112\0\u51a6\53\132\0\u048d\53\u0773\0" +
			"\u2ba4\53\u215c\0\u012e\53\2\0\73\53\225\0\7\53\14\0\5\53\5\0" +
			"\1\53\1\54\12\53\1\0\15\53\1\0\5\53\1\0\1\53\1\0" +
			"\2\53\1\0\2\53\1\0\154\53\41\0\u016b\53\22\0\100\53\2\0" +
			"\66\53\50\0\15\53\3\0\20\54\20\0\4\54\17\0\2\53\30\0" +
			"\3\53\31\0\1\53\6\0\5\53\1\0\207\53\2\0\1\54\4\0" +
			"\1\53\13\0\12\52\7\0\32\53\4\0\1\53\1\0\32\53\12\0" +
			"\132\53\3\0\6\53\2\0\6\53\2\0\6\53\2\0\3\53\3\0" +
			"\2\53\3\0\2\53\22\0\3\54\4\0";
	/**
	 * Translates characters to character classes
	 */
	private static final char[] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);
	/**
	 * Translates DFA states to action switch labels.
	 */
	private static final int[] ZZ_ACTION = zzUnpackAction();
	private static final String ZZ_ACTION_PACKED_0 =
		"\1\0\1\1\14\2\1\1\2\2\1\3\1\4\1\3" +
			"\1\5\1\6\1\1\1\7\1\10\1\1\6\2\1\1" +
			"\1\11\1\12\1\11\12\2\1\13\3\2\1\14\2\2" +
			"\1\15\1\0\1\16\6\2\1\0\1\17\1\0\2\2" +
			"\1\20\13\2\1\21\1\22\1\23\4\2\1\24\3\2" +
			"\1\25\1\26\1\2\1\27\3\2\1\0\1\30\7\2" +
			"\1\31\3\2\1\0\12\2\1\0\1\2\1\32\1\33" +
			"\1\2\1\34\5\2\1\0\1\35\1\36\1\37\1\2" +
			"\1\40\1\41\1\2\1\42\1\2\1\43\1\44";
	/**
	 * Translates a state to a row index in the transition table
	 */
	private static final int[] ZZ_ROWMAP = zzUnpackRowMap();
	private static final String ZZ_ROWMAP_PACKED_0 =
		"\0\0\0\56\0\134\0\212\0\270\0\346\0\u0114\0\u0142" +
			"\0\u0170\0\u019e\0\u01cc\0\u01fa\0\u0228\0\u0256\0\u0284\0\u02b2" +
			"\0\u02e0\0\u030e\0\56\0\56\0\56\0\56\0\u033c\0\56" +
			"\0\56\0\u036a\0\u0398\0\u03c6\0\u03f4\0\u0422\0\u0450\0\u047e" +
			"\0\u04ac\0\u04da\0\u0508\0\u0536\0\u0564\0\u0592\0\u05c0\0\u05ee" +
			"\0\u061c\0\u064a\0\u0678\0\u06a6\0\u06d4\0\u0702\0\346\0\u0730" +
			"\0\u075e\0\u078c\0\u07ba\0\u07e8\0\u0816\0\56\0\u033c\0\56" +
			"\0\u0844\0\u0872\0\u08a0\0\u08ce\0\u08fc\0\u092a\0\u04ac\0\56" +
			"\0\u0958\0\u0986\0\u09b4\0\346\0\u09e2\0\u0a10\0\u0a3e\0\u0a6c" +
			"\0\u0a9a\0\u0ac8\0\u0af6\0\u0b24\0\u0b52\0\u0b80\0\u0bae\0\346" +
			"\0\346\0\346\0\u0bdc\0\u0c0a\0\u0c38\0\u0c66\0\u0958\0\u0c94" +
			"\0\u0cc2\0\u0cf0\0\346\0\346\0\u0d1e\0\346\0\u0d4c\0\u0d7a" +
			"\0\u0da8\0\u0dd6\0\346\0\u0e04\0\u0e32\0\u0e60\0\u0e8e\0\u0ebc" +
			"\0\u0eea\0\u0f18\0\346\0\u0f46\0\u0f74\0\u0fa2\0\u0fd0\0\u0ffe" +
			"\0\u102c\0\u105a\0\u1088\0\u10b6\0\u10e4\0\u1112\0\u1140\0\u116e" +
			"\0\u119c\0\u11ca\0\u11f8\0\346\0\346\0\u1226\0\346\0\u1254" +
			"\0\u1282\0\u12b0\0\u12de\0\u130c\0\u133a\0\346\0\346\0\346" +
			"\0\u1368\0\346\0\346\0\u1396\0\56\0\u13c4\0\346\0\346";
	/**
	 * The transition table of the DFA
	 */
	private static final int[] ZZ_TRANS = zzUnpackTrans();
	private static final String ZZ_TRANS_PACKED_0 =
		"\1\2\1\3\1\4\1\5\1\6\1\7\1\6\1\10" +
			"\1\11\1\12\1\13\1\14\1\15\4\6\1\16\1\17" +
			"\3\6\1\20\1\21\1\6\1\22\1\23\1\24\1\25" +
			"\1\26\1\27\1\30\1\31\1\32\1\33\1\34\1\35" +
			"\1\36\1\37\1\40\1\41\1\42\1\43\1\6\1\2" +
			"\1\44\57\0\1\6\1\45\17\6\1\0\6\6\11\0" +
			"\6\6\2\0\3\6\2\0\5\6\1\46\13\6\1\0" +
			"\6\6\11\0\6\6\2\0\3\6\2\0\4\6\1\47" +
			"\14\6\1\0\6\6\11\0\6\6\2\0\3\6\2\0" +
			"\21\6\1\0\6\6\11\0\6\6\2\0\3\6\2\0" +
			"\21\6\1\0\2\6\1\50\3\6\11\0\6\6\2\0" +
			"\3\6\2\0\10\6\1\51\10\6\1\0\6\6\11\0" +
			"\6\6\2\0\3\6\2\0\10\6\1\52\1\6\1\53" +
			"\1\6\1\54\4\6\1\0\6\6\11\0\6\6\2\0" +
			"\3\6\2\0\1\6\1\55\17\6\1\0\6\6\11\0" +
			"\6\6\2\0\3\6\2\0\17\6\1\56\1\6\1\0" +
			"\6\6\11\0\6\6\2\0\3\6\2\0\13\6\1\57" +
			"\2\6\1\60\2\6\1\0\6\6\11\0\6\6\2\0" +
			"\3\6\2\0\14\6\1\61\4\6\1\0\6\6\11\0" +
			"\6\6\2\0\3\6\2\0\12\6\1\62\6\6\1\0" +
			"\6\6\11\0\6\6\2\0\3\6\53\0\1\63\4\0" +
			"\1\6\1\64\17\6\1\0\6\6\11\0\6\6\2\0" +
			"\3\6\2\0\12\6\1\65\6\6\1\0\6\6\11\0" +
			"\6\6\2\0\3\6\33\0\1\66\23\0\36\67\1\70" +
			"\17\67\52\0\1\43\4\0\14\6\1\71\4\6\1\0" +
			"\6\6\11\0\6\6\2\0\3\6\2\0\2\6\1\72" +
			"\16\6\1\0\6\6\11\0\6\6\2\0\3\6\2\0" +
			"\12\6\1\73\6\6\1\0\6\6\11\0\6\6\2\0" +
			"\3\6\2\0\1\6\1\74\17\6\1\0\6\6\11\0" +
			"\6\6\2\0\3\6\2\0\6\6\1\75\12\6\1\0" +
			"\6\6\11\0\6\6\2\0\3\6\2\0\1\6\1\76" +
			"\17\6\1\0\6\6\11\0\6\6\2\0\3\6\1\0" +
			"\51\77\1\100\4\77\51\0\1\42\40\0\1\101\15\0" +
			"\1\43\60\0\1\44\1\0\2\6\1\102\16\6\1\0" +
			"\6\6\11\0\6\6\2\0\3\6\2\0\6\6\1\103" +
			"\12\6\1\0\6\6\11\0\6\6\2\0\3\6\2\0" +
			"\21\6\1\0\5\6\1\104\11\0\6\6\2\0\3\6" +
			"\2\0\6\6\1\105\12\6\1\0\6\6\11\0\6\6" +
			"\2\0\3\6\2\0\17\6\1\106\1\6\1\0\6\6" +
			"\11\0\6\6\2\0\3\6\2\0\1\6\1\107\17\6" +
			"\1\0\6\6\11\0\6\6\2\0\3\6\2\0\15\6" +
			"\1\110\3\6\1\0\6\6\11\0\6\6\2\0\3\6" +
			"\2\0\2\6\1\111\16\6\1\0\6\6\11\0\6\6" +
			"\2\0\3\6\2\0\1\6\1\112\17\6\1\0\6\6" +
			"\11\0\6\6\2\0\3\6\2\0\15\6\1\113\3\6" +
			"\1\0\6\6\11\0\6\6\2\0\3\6\2\0\13\6" +
			"\1\114\5\6\1\0\6\6\11\0\6\6\2\0\3\6" +
			"\2\0\2\6\1\115\16\6\1\0\6\6\11\0\6\6" +
			"\2\0\3\6\2\0\13\6\1\116\5\6\1\0\6\6" +
			"\11\0\6\6\2\0\3\6\35\0\1\101\15\0\1\63" +
			"\4\0\10\6\1\117\10\6\1\0\6\6\11\0\6\6" +
			"\2\0\3\6\2\0\10\6\1\120\10\6\1\0\6\6" +
			"\11\0\6\6\2\0\3\6\2\0\21\6\1\0\1\121" +
			"\5\6\11\0\6\6\2\0\3\6\2\0\6\6\1\122" +
			"\12\6\1\0\6\6\11\0\6\6\2\0\3\6\2\0" +
			"\6\6\1\123\12\6\1\0\6\6\11\0\6\6\2\0" +
			"\3\6\2\0\17\6\1\124\1\6\1\0\6\6\11\0" +
			"\6\6\2\0\3\6\2\0\10\6\1\125\10\6\1\0" +
			"\6\6\11\0\6\6\2\0\3\6\2\0\1\6\1\126" +
			"\17\6\1\0\6\6\11\0\6\6\2\0\3\6\53\0" +
			"\1\127\4\0\3\6\1\130\15\6\1\0\6\6\11\0" +
			"\6\6\2\0\3\6\2\0\14\6\1\131\4\6\1\0" +
			"\6\6\11\0\6\6\2\0\3\6\2\0\4\6\1\132" +
			"\14\6\1\0\6\6\11\0\6\6\2\0\3\6\2\0" +
			"\4\6\1\133\14\6\1\0\6\6\11\0\6\6\2\0" +
			"\3\6\2\0\11\6\1\134\7\6\1\0\6\6\11\0" +
			"\6\6\2\0\3\6\2\0\13\6\1\106\5\6\1\0" +
			"\6\6\11\0\6\6\2\0\3\6\2\0\12\6\1\135" +
			"\6\6\1\0\6\6\11\0\6\6\2\0\3\6\2\0" +
			"\6\6\1\136\12\6\1\0\6\6\11\0\6\6\2\0" +
			"\3\6\2\0\6\6\1\137\12\6\1\0\6\6\11\0" +
			"\6\6\2\0\3\6\2\0\6\6\1\140\12\6\1\0" +
			"\6\6\11\0\6\6\2\0\3\6\2\0\21\6\1\0" +
			"\1\6\1\141\4\6\11\0\6\6\2\0\3\6\2\0" +
			"\21\6\1\142\6\6\11\0\6\6\2\0\3\6\2\0" +
			"\21\6\1\0\1\143\5\6\11\0\6\6\2\0\3\6" +
			"\2\0\17\6\1\144\1\6\1\0\6\6\11\0\6\6" +
			"\2\0\3\6\2\0\16\6\1\145\2\6\1\0\6\6" +
			"\11\0\6\6\2\0\3\6\2\0\14\6\1\146\4\6" +
			"\1\0\6\6\11\0\6\6\2\0\3\6\2\0\15\6" +
			"\1\147\3\6\1\0\6\6\11\0\6\6\2\0\3\6" +
			"\2\0\4\6\1\150\14\6\1\0\6\6\11\0\6\6" +
			"\2\0\3\6\2\0\1\6\1\151\17\6\1\0\6\6" +
			"\11\0\6\6\2\0\3\6\2\0\2\6\1\152\16\6" +
			"\1\0\6\6\11\0\6\6\2\0\3\6\2\0\15\6" +
			"\1\153\3\6\1\0\6\6\11\0\6\6\2\0\3\6" +
			"\2\0\14\6\1\154\4\6\1\0\6\6\11\0\6\6" +
			"\2\0\3\6\2\0\10\6\1\155\10\6\1\0\6\6" +
			"\11\0\6\6\2\0\3\6\2\0\15\6\1\156\3\6" +
			"\1\0\6\6\11\0\6\6\2\0\3\6\5\0\1\157" +
			"\52\0\10\6\1\160\10\6\1\0\6\6\11\0\6\6" +
			"\2\0\3\6\2\0\15\6\1\161\3\6\1\0\6\6" +
			"\11\0\6\6\2\0\3\6\2\0\2\6\1\162\16\6" +
			"\1\0\6\6\11\0\6\6\2\0\3\6\2\0\4\6" +
			"\1\163\14\6\1\0\6\6\11\0\6\6\2\0\3\6" +
			"\2\0\5\6\1\164\13\6\1\0\6\6\11\0\6\6" +
			"\2\0\3\6\2\0\2\6\1\165\16\6\1\0\6\6" +
			"\11\0\6\6\2\0\3\6\2\0\13\6\1\166\5\6" +
			"\1\0\6\6\11\0\6\6\2\0\3\6\2\0\5\6" +
			"\1\167\13\6\1\0\6\6\11\0\6\6\2\0\3\6" +
			"\2\0\12\6\1\170\6\6\1\0\6\6\11\0\6\6" +
			"\2\0\3\6\2\0\4\6\1\171\14\6\1\0\6\6" +
			"\11\0\6\6\2\0\3\6\3\0\1\172\54\0\12\6" +
			"\1\173\6\6\1\0\6\6\11\0\6\6\2\0\3\6" +
			"\2\0\4\6\1\174\14\6\1\0\6\6\11\0\6\6" +
			"\2\0\3\6\2\0\21\6\1\0\1\6\1\175\4\6" +
			"\11\0\6\6\2\0\3\6\2\0\12\6\1\176\6\6" +
			"\1\0\6\6\11\0\6\6\2\0\3\6\2\0\6\6" +
			"\1\177\12\6\1\0\6\6\11\0\6\6\2\0\3\6" +
			"\2\0\12\6\1\200\6\6\1\0\6\6\11\0\6\6" +
			"\2\0\3\6\2\0\14\6\1\201\4\6\1\0\6\6" +
			"\11\0\6\6\2\0\3\6\2\0\15\6\1\202\3\6" +
			"\1\0\6\6\11\0\6\6\2\0\3\6\2\0\3\6" +
			"\1\203\15\6\1\0\6\6\11\0\6\6\2\0\3\6" +
			"\2\0\6\6\1\204\12\6\1\0\6\6\11\0\6\6" +
			"\2\0\3\6\24\0\1\205\33\0\15\6\1\206\3\6" +
			"\1\0\6\6\11\0\6\6\2\0\3\6\2\0\2\6" +
			"\1\207\16\6\1\0\6\6\11\0\6\6\2\0\3\6" +
			"\2\0\15\6\1\210\3\6\1\0\6\6\11\0\6\6" +
			"\2\0\3\6\2\0\16\6\1\211\2\6\1\0\6\6" +
			"\11\0\6\6\2\0\3\6\2\0\4\6\1\212\14\6" +
			"\1\0\6\6\11\0\6\6\2\0\3\6\2\0\6\6" +
			"\1\213\12\6\1\0\6\6\11\0\6\6\2\0\3\6" +
			"\2\0\1\6\1\214\17\6\1\0\6\6\11\0\6\6" +
			"\2\0\3\6\6\0\1\215\51\0\15\6\1\216\3\6" +
			"\1\0\6\6\11\0\6\6\2\0\3\6\2\0\2\6" +
			"\1\217\16\6\1\0\6\6\11\0\6\6\2\0\3\6" +
			"\2\0\4\6\1\220\14\6\1\0\6\6\11\0\6\6" +
			"\2\0\3\6\1\0";
	/* error codes */
	private static final int ZZ_UNKNOWN_ERROR = 0;
	private static final int ZZ_NO_MATCH = 1;
	private static final int ZZ_PUSHBACK_2BIG = 2;
	private static final char[] EMPTY_BUFFER = new char[0];
	private static final int YYEOF = -1;
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
		"\1\0\1\11\20\1\4\11\1\1\2\11\34\1\1\11" +
			"\1\0\1\11\6\1\1\0\1\11\1\0\40\1\1\0" +
			"\14\1\1\0\12\1\1\0\12\1\1\0\7\1\1\11" +
			"\3\1";
	private static java.io.Reader zzReader = null; // Fake
	/**
	 * the current state of the DFA
	 */
	private int zzState;
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

	TaraHighlighterLex(java.io.Reader in) {
		this.zzReader = in;
	}

	/**
	 * Creates a new scanner.
	 * There is also java.io.Reader version of this constructor.
	 *
	 * @param in the java.io.Inputstream to read input from.
	 */
	TaraHighlighterLex(java.io.InputStream in) {
		this(new java.io.InputStreamReader(in));
	}

	private static int[] zzUnpackAction() {
		int[] result = new int[144];
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

	private static int[] zzUnpackRowMap() {
		int[] result = new int[144];
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

	private static int[] zzUnpackTrans() {
		int[] result = new int[5106];
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

  /* user code: */

	private static int[] zzUnpackAttribute() {
		int[] result = new int[144];
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
	 * Unpacks the compressed character translation table.
	 *
	 * @param packed the packed character translation table
	 * @return the unpacked character translation table
	 */
	private static char[] zzUnpackCMap(String packed) {
		char[] map = new char[0x10000];
		int i = 0;  /* index in packed string  */
		int j = 0;  /* index in unpacked array */
		while (i < 1772) {
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
				case 3: {
					return TaraTypes.LEFT_SQUARE;
				}
				case 37:
					break;
				case 24: {
					return TaraTypes.WORD;
				}
				case 38:
					break;
				case 17: {
					return TaraTypes.VAR;
				}
				case 39:
					break;
				case 14: {
					return TaraTypes.STRING_VALUE;
				}
				case 40:
					break;
				case 33: {
					return TaraTypes.ABSTRACT;
				}
				case 41:
					break;
				case 7: {
					return TaraTypes.OPEN_AN;
				}
				case 42:
					break;
				case 32: {
					return TaraTypes.MULTIPLE;
				}
				case 43:
					break;
				case 9: {
					return TokenType.WHITE_SPACE;
				}
				case 44:
					break;
				case 29: {
					return TaraTypes.NATURAL_TYPE;
				}
				case 45:
					break;
				case 13: {
					return TaraTypes.LIST;
				}
				case 46:
					break;
				case 8: {
					return TaraTypes.CLOSE_AN;
				}
				case 47:
					break;
				case 21: {
					return TaraTypes.BOOLEAN_VALUE;
				}
				case 48:
					break;
				case 34: {
					return TaraTypes.HAS_CODE;
				}
				case 49:
					break;
				case 25: {
					return TaraTypes.FINAL;
				}
				case 50:
					break;
				case 10: {
					return TaraTypes.NATURAL_VALUE;
				}
				case 51:
					break;
				case 5: {
					return TaraTypes.DOT;
				}
				case 52:
					break;
				case 27: {
					return TaraTypes.STRING_TYPE;
				}
				case 53:
					break;
				case 6: {
					return TaraTypes.ASSIGN;
				}
				case 54:
					break;
				case 31: {
					return TaraTypes.OPTIONAL;
				}
				case 55:
					break;
				case 2: {
					return TaraTypes.IDENTIFIER_KEY;
				}
				case 56:
					break;
				case 18: {
					return TaraTypes.UID_TYPE;
				}
				case 57:
					break;
				case 22: {
					return TaraTypes.FROM_KEY;
				}
				case 58:
					break;
				case 23: {
					return TaraTypes.ROOT;
				}
				case 59:
					break;
				case 12: {
					return TaraTypes.NEGATIVE_VALUE;
				}
				case 60:
					break;
				case 15: {
					return TaraTypes.DOC_LINE;
				}
				case 61:
					break;
				case 26: {
					return TaraTypes.DOUBLE_TYPE;
				}
				case 62:
					break;
				case 4: {
					return TaraTypes.RIGHT_SQUARE;
				}
				case 63:
					break;
				case 1: {
					return TokenType.BAD_CHARACTER;
				}
				case 64:
					break;
				case 16: {
					return TaraTypes.NEW;
				}
				case 65:
					break;
				case 11: {
					return TaraTypes.AS;
				}
				case 66:
					break;
				case 20: {
					return TaraTypes.DOUBLE_VALUE;
				}
				case 67:
					break;
				case 36: {
					return TaraTypes.EXTENSIBLE;
				}
				case 68:
					break;
				case 30: {
					return TaraTypes.BOOLEAN_TYPE;
				}
				case 69:
					break;
				case 19: {
					return TaraTypes.INT_TYPE;
				}
				case 70:
					break;
				case 28: {
					return TaraTypes.CONCEPT_KEY;
				}
				case 71:
					break;
				case 35: {
					return TaraTypes.SINGLETON;
				}
				case 72:
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
