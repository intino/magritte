/* The following code was generated by JFlex 1.4.3 on 4/07/14 14:22 */

package siani.tara.intellij.lang.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import siani.tara.intellij.lang.psi.TaraTypes;
import com.intellij.psi.TokenType;
import java.util.LinkedList;
import java.util.Queue;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 4/07/14 14:22 from the specification file
 * <tt>/Users/oroncal/workspace/tara/intellij/src/siani/tara/intellij/lang/lexer/Tara.flex</tt>
 */
class TaraLexer implements FlexLexer {
  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0, 0
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\55\1\1\1\2\3\0\16\55\4\0\1\1\1\0\1\0\1\52"+
    "\1\54\2\0\1\41\1\33\1\34\1\44\1\50\1\45\1\42\1\43"+
    "\1\0\12\53\1\46\1\47\5\0\2\54\1\3\5\54\1\12\21\54"+
    "\1\35\1\0\1\36\1\0\1\54\1\0\1\14\1\17\1\6\1\22"+
    "\1\7\1\51\1\30\1\25\1\13\2\54\1\23\1\21\1\5\1\4"+
    "\1\10\1\31\1\26\1\15\1\11\1\16\1\27\1\24\1\20\1\32"+
    "\1\54\1\37\1\0\1\40\1\0\41\55\2\0\4\54\4\0\1\54"+
    "\2\0\1\55\7\0\1\54\4\0\1\54\5\0\27\54\1\0\37\54"+
    "\1\0\u01ca\54\4\0\14\54\16\0\5\54\7\0\1\54\1\0\1\54"+
    "\21\0\160\55\5\54\1\0\2\54\2\0\4\54\10\0\1\54\1\0"+
    "\3\54\1\0\1\54\1\0\24\54\1\0\123\54\1\0\213\54\1\0"+
    "\5\55\2\0\236\54\11\0\46\54\2\0\1\54\7\0\47\54\11\0"+
    "\55\55\1\0\1\55\1\0\2\55\1\0\2\55\1\0\1\55\10\0"+
    "\33\54\5\0\3\54\15\0\4\55\7\0\1\54\4\0\13\55\5\0"+
    "\53\54\25\55\12\53\4\0\2\54\1\55\143\54\1\0\1\54\10\55"+
    "\1\0\6\55\2\54\2\55\1\0\4\55\2\54\12\53\3\54\2\0"+
    "\1\54\17\0\1\55\1\54\1\55\36\54\33\55\2\0\131\54\13\55"+
    "\1\54\16\0\12\53\41\54\11\55\2\54\4\0\1\54\5\0\26\54"+
    "\4\55\1\54\11\55\1\54\3\55\1\54\5\55\22\0\31\54\3\55"+
    "\244\0\4\55\66\54\3\55\1\54\22\55\1\54\7\55\12\54\2\55"+
    "\2\0\12\53\1\0\7\54\1\0\7\54\1\0\3\55\1\0\10\54"+
    "\2\0\2\54\2\0\26\54\1\0\7\54\1\0\1\54\3\0\4\54"+
    "\2\0\1\55\1\54\7\55\2\0\2\55\2\0\3\55\1\54\10\0"+
    "\1\55\4\0\2\54\1\0\3\54\2\55\2\0\12\53\4\54\7\0"+
    "\1\54\5\0\3\55\1\0\6\54\4\0\2\54\2\0\26\54\1\0"+
    "\7\54\1\0\2\54\1\0\2\54\1\0\2\54\2\0\1\55\1\0"+
    "\5\55\4\0\2\55\2\0\3\55\3\0\1\55\7\0\4\54\1\0"+
    "\1\54\7\0\12\53\2\55\3\54\1\55\13\0\3\55\1\0\11\54"+
    "\1\0\3\54\1\0\26\54\1\0\7\54\1\0\2\54\1\0\5\54"+
    "\2\0\1\55\1\54\10\55\1\0\3\55\1\0\3\55\2\0\1\54"+
    "\17\0\2\54\2\55\2\0\12\53\1\0\1\54\17\0\3\55\1\0"+
    "\10\54\2\0\2\54\2\0\26\54\1\0\7\54\1\0\2\54\1\0"+
    "\5\54\2\0\1\55\1\54\7\55\2\0\2\55\2\0\3\55\10\0"+
    "\2\55\4\0\2\54\1\0\3\54\2\55\2\0\12\53\1\0\1\54"+
    "\20\0\1\55\1\54\1\0\6\54\3\0\3\54\1\0\4\54\3\0"+
    "\2\54\1\0\1\54\1\0\2\54\3\0\2\54\3\0\3\54\3\0"+
    "\14\54\4\0\5\55\3\0\3\55\1\0\4\55\2\0\1\54\6\0"+
    "\1\55\16\0\12\53\11\0\1\54\7\0\3\55\1\0\10\54\1\0"+
    "\3\54\1\0\27\54\1\0\12\54\1\0\5\54\3\0\1\54\7\55"+
    "\1\0\3\55\1\0\4\55\7\0\2\55\1\0\2\54\6\0\2\54"+
    "\2\55\2\0\12\53\22\0\2\55\1\0\10\54\1\0\3\54\1\0"+
    "\27\54\1\0\12\54\1\0\5\54\2\0\1\55\1\54\7\55\1\0"+
    "\3\55\1\0\4\55\7\0\2\55\7\0\1\54\1\0\2\54\2\55"+
    "\2\0\12\53\1\0\2\54\17\0\2\55\1\0\10\54\1\0\3\54"+
    "\1\0\51\54\2\0\1\54\7\55\1\0\3\55\1\0\4\55\1\54"+
    "\10\0\1\55\10\0\2\54\2\55\2\0\12\53\12\0\6\54\2\0"+
    "\2\55\1\0\22\54\3\0\30\54\1\0\11\54\1\0\1\54\2\0"+
    "\7\54\3\0\1\55\4\0\6\55\1\0\1\55\1\0\10\55\22\0"+
    "\2\55\15\0\60\54\1\55\2\54\7\55\4\0\10\54\10\55\1\0"+
    "\12\53\47\0\2\54\1\0\1\54\2\0\2\54\1\0\1\54\2\0"+
    "\1\54\6\0\4\54\1\0\7\54\1\0\3\54\1\0\1\54\1\0"+
    "\1\54\2\0\2\54\1\0\4\54\1\55\2\54\6\55\1\0\2\55"+
    "\1\54\2\0\5\54\1\0\1\54\1\0\6\55\2\0\12\53\2\0"+
    "\2\54\42\0\1\54\27\0\2\55\6\0\12\53\13\0\1\55\1\0"+
    "\1\55\1\0\1\55\4\0\2\55\10\54\1\0\44\54\4\0\24\55"+
    "\1\0\2\55\5\54\13\55\1\0\44\55\11\0\1\55\71\0\53\54"+
    "\24\55\1\54\12\53\6\0\6\54\4\55\4\54\3\55\1\54\3\55"+
    "\2\54\7\55\3\54\4\55\15\54\14\55\1\54\1\55\12\53\4\55"+
    "\2\0\46\54\12\0\53\54\1\0\1\54\3\0\u0149\54\1\0\4\54"+
    "\2\0\7\54\1\0\1\54\1\0\4\54\2\0\51\54\1\0\4\54"+
    "\2\0\41\54\1\0\4\54\2\0\7\54\1\0\1\54\1\0\4\54"+
    "\2\0\17\54\1\0\71\54\1\0\4\54\2\0\103\54\2\0\3\55"+
    "\40\0\20\54\20\0\125\54\14\0\u026c\54\2\0\21\54\1\0\32\54"+
    "\5\0\113\54\3\0\3\54\17\0\15\54\1\0\4\54\3\55\13\0"+
    "\22\54\3\55\13\0\22\54\2\55\14\0\15\54\1\0\3\54\1\0"+
    "\2\55\14\0\64\54\40\55\3\0\1\54\3\0\2\54\1\55\2\0"+
    "\12\53\41\0\3\55\2\0\12\53\6\0\130\54\10\0\51\54\1\55"+
    "\1\54\5\0\106\54\12\0\35\54\3\0\14\55\4\0\14\55\12\0"+
    "\12\53\36\54\2\0\5\54\13\0\54\54\4\0\21\55\7\54\2\55"+
    "\6\0\12\53\46\0\27\54\5\55\4\0\65\54\12\55\1\0\35\55"+
    "\2\0\1\55\12\53\6\0\12\53\15\0\1\54\130\0\5\55\57\54"+
    "\21\55\7\54\4\0\12\53\21\0\11\55\14\0\3\55\36\54\12\55"+
    "\3\0\2\54\12\53\6\0\46\54\16\55\14\0\44\54\24\55\10\0"+
    "\12\53\3\0\3\54\12\53\44\54\122\0\3\55\1\0\25\55\4\54"+
    "\1\55\4\54\1\55\15\0\300\54\47\55\25\0\4\55\u0116\54\2\0"+
    "\6\54\2\0\46\54\2\0\6\54\2\0\10\54\1\0\1\54\1\0"+
    "\1\54\1\0\1\54\1\0\37\54\2\0\65\54\1\0\7\54\1\0"+
    "\1\54\3\0\3\54\1\0\7\54\3\0\4\54\2\0\6\54\4\0"+
    "\15\54\5\0\3\54\1\0\7\54\16\0\5\55\32\0\5\55\20\0"+
    "\2\54\23\0\1\54\13\0\5\55\5\0\6\55\1\0\1\54\15\0"+
    "\1\54\20\0\15\54\3\0\32\54\26\0\15\55\4\0\1\55\3\0"+
    "\14\55\21\0\1\54\4\0\1\54\2\0\12\54\1\0\1\54\3\0"+
    "\5\54\6\0\1\54\1\0\1\54\1\0\1\54\1\0\4\54\1\0"+
    "\13\54\2\0\4\54\5\0\5\54\4\0\1\54\21\0\51\54\u0a77\0"+
    "\57\54\1\0\57\54\1\0\205\54\6\0\4\54\3\55\16\0\46\54"+
    "\12\0\66\54\11\0\1\54\17\0\1\55\27\54\11\0\7\54\1\0"+
    "\7\54\1\0\7\54\1\0\7\54\1\0\7\54\1\0\7\54\1\0"+
    "\7\54\1\0\7\54\1\0\40\55\57\0\1\54\u01d5\0\3\54\31\0"+
    "\11\54\6\55\1\0\5\54\2\0\5\54\4\0\126\54\2\0\2\55"+
    "\2\0\3\54\1\0\132\54\1\0\4\54\5\0\51\54\3\0\136\54"+
    "\21\0\33\54\65\0\20\54\u0200\0\u19b6\54\112\0\u51cc\54\64\0\u048d\54"+
    "\103\0\56\54\2\0\u010d\54\3\0\20\54\12\53\2\54\24\0\57\54"+
    "\1\55\14\0\2\55\1\0\31\54\10\0\120\54\2\55\45\0\11\54"+
    "\2\0\147\54\2\0\4\54\1\0\2\54\16\0\12\54\120\0\10\54"+
    "\1\55\3\54\1\55\4\54\1\55\27\54\5\55\20\0\1\54\7\0"+
    "\64\54\14\0\2\55\62\54\21\55\13\0\12\53\6\0\22\55\6\54"+
    "\3\0\1\54\4\0\12\53\34\54\10\55\2\0\27\54\15\55\14\0"+
    "\35\54\3\0\4\55\57\54\16\55\16\0\1\54\12\53\46\0\51\54"+
    "\16\55\11\0\3\54\1\55\10\54\2\55\2\0\12\53\6\0\27\54"+
    "\3\0\1\54\1\55\4\0\60\54\1\55\1\54\3\55\2\54\2\55"+
    "\5\54\2\55\1\54\1\55\1\54\30\0\3\54\43\0\6\54\2\0"+
    "\6\54\2\0\6\54\11\0\7\54\1\0\7\54\221\0\43\54\10\55"+
    "\1\0\2\55\2\0\12\53\6\0\u2ba4\54\14\0\27\54\4\0\61\54"+
    "\u2104\0\u012e\54\2\0\76\54\2\0\152\54\46\0\7\54\14\0\5\54"+
    "\5\0\1\54\1\55\12\54\1\0\15\54\1\0\5\54\1\0\1\54"+
    "\1\0\2\54\1\0\2\54\1\0\154\54\41\0\u016b\54\22\0\100\54"+
    "\2\0\66\54\50\0\15\54\3\0\20\55\20\0\7\55\14\0\2\54"+
    "\30\0\3\54\31\0\1\54\6\0\5\54\1\0\207\54\2\0\1\55"+
    "\4\0\1\54\13\0\12\53\7\0\32\54\4\0\1\54\1\0\32\54"+
    "\13\0\131\54\3\0\6\54\2\0\6\54\2\0\6\54\2\0\3\54"+
    "\3\0\2\54\3\0\2\54\22\0\3\55\4\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\1\2\1\3\23\4\1\5\1\6\1\7"+
    "\1\10\1\11\1\12\2\1\1\13\1\14\1\15\1\16"+
    "\1\17\1\1\1\4\1\1\1\20\1\3\1\4\1\21"+
    "\10\4\1\22\1\23\15\4\1\24\1\0\1\25\1\0"+
    "\1\26\1\4\1\0\1\27\1\0\16\4\1\30\1\4"+
    "\1\31\10\4\1\32\2\0\1\4\1\33\3\4\1\34"+
    "\4\4\1\35\10\4\1\36\1\37\1\40\1\41\2\4"+
    "\1\0\1\42\3\4\1\43\5\4\1\44\7\4\1\42"+
    "\10\4\1\45\1\46\2\4\1\47\2\4\1\50\1\51"+
    "\2\4\1\52\2\4\1\53\1\54\3\4\1\55\1\56"+
    "\1\57\2\4\1\60\1\61\1\62\1\63";

  private static int [] zzUnpackAction() {
    int [] result = new int[184];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
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
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\56\0\134\0\212\0\270\0\346\0\u0114\0\u0142"+
    "\0\u0170\0\u019e\0\u01cc\0\u01fa\0\u0228\0\u0256\0\u0284\0\u02b2"+
    "\0\u02e0\0\u030e\0\u033c\0\u036a\0\u0398\0\u03c6\0\u03f4\0\56"+
    "\0\56\0\u0422\0\56\0\56\0\56\0\u0450\0\u047e\0\56"+
    "\0\56\0\56\0\56\0\u04ac\0\u04da\0\u0508\0\u0536\0\u0564"+
    "\0\u0592\0\u05c0\0\u030e\0\u05ee\0\u061c\0\u064a\0\u0678\0\u06a6"+
    "\0\u06d4\0\u0702\0\u0730\0\u030e\0\u030e\0\u075e\0\u078c\0\u07ba"+
    "\0\u07e8\0\u0816\0\u0844\0\u0872\0\u08a0\0\u08ce\0\u08fc\0\u092a"+
    "\0\u0958\0\u0986\0\56\0\u0450\0\56\0\u09b4\0\u09e2\0\u0a10"+
    "\0\u0536\0\56\0\u0a3e\0\u0a6c\0\u0a9a\0\u0ac8\0\u0af6\0\u0b24"+
    "\0\u0b52\0\u0b80\0\u0bae\0\u0bdc\0\u0c0a\0\u0c38\0\u0c66\0\u0c94"+
    "\0\u0cc2\0\u030e\0\u0cf0\0\u030e\0\u0d1e\0\u0d4c\0\u0d7a\0\u0da8"+
    "\0\u0dd6\0\u0e04\0\u0e32\0\u0e60\0\u030e\0\u0e8e\0\u0ebc\0\u0eea"+
    "\0\u0a3e\0\u0f18\0\u0f46\0\u0f74\0\u030e\0\u0fa2\0\u0fd0\0\u0ffe"+
    "\0\u102c\0\u030e\0\u105a\0\u1088\0\u10b6\0\u10e4\0\u1112\0\u1140"+
    "\0\u116e\0\u119c\0\u030e\0\u030e\0\u030e\0\u030e\0\u11ca\0\u11f8"+
    "\0\u1226\0\u0ebc\0\u1254\0\u1282\0\u12b0\0\u030e\0\u12de\0\u130c"+
    "\0\u133a\0\u1368\0\u1396\0\u030e\0\u13c4\0\u13f2\0\u1420\0\u144e"+
    "\0\u147c\0\u14aa\0\u14d8\0\u1506\0\u1534\0\u1562\0\u1590\0\u15be"+
    "\0\u15ec\0\u161a\0\u1648\0\u1676\0\u030e\0\u030e\0\u16a4\0\u16d2"+
    "\0\u030e\0\u1700\0\u172e\0\u030e\0\u030e\0\u175c\0\u178a\0\u030e"+
    "\0\u17b8\0\u17e6\0\u030e\0\u030e\0\u1814\0\u1842\0\u1870\0\u030e"+
    "\0\u030e\0\u030e\0\u189e\0\u18cc\0\u030e\0\u030e\0\u030e\0\u030e";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[184];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
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
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11"+
    "\1\12\1\13\1\14\1\15\1\16\1\17\1\20\1\21"+
    "\1\22\1\23\1\24\1\22\1\25\1\22\1\26\1\27"+
    "\3\22\1\30\1\31\1\32\1\33\1\34\1\35\1\36"+
    "\1\37\1\40\1\41\1\42\1\43\1\44\1\45\1\46"+
    "\1\47\1\50\1\22\1\2\57\0\1\3\55\0\1\51"+
    "\1\4\56\0\1\22\1\52\26\22\16\0\1\22\1\0"+
    "\3\22\3\0\2\22\1\53\25\22\16\0\1\22\1\0"+
    "\3\22\3\0\11\22\1\54\16\22\16\0\1\22\1\0"+
    "\3\22\3\0\11\22\1\55\16\22\16\0\1\22\1\0"+
    "\3\22\3\0\16\22\1\56\11\22\16\0\1\22\1\0"+
    "\3\22\3\0\23\22\1\57\4\22\16\0\1\22\1\0"+
    "\3\22\3\0\4\22\1\60\16\22\1\61\4\22\16\0"+
    "\1\22\1\0\3\22\3\0\2\22\1\62\25\22\16\0"+
    "\1\22\1\0\3\22\3\0\2\22\1\63\7\22\1\64"+
    "\15\22\16\0\1\22\1\0\3\22\3\0\12\22\1\65"+
    "\5\22\1\66\7\22\16\0\1\22\1\0\3\22\3\0"+
    "\6\22\1\67\1\22\1\70\17\22\16\0\1\22\1\0"+
    "\3\22\3\0\12\22\1\71\15\22\16\0\1\22\1\0"+
    "\3\22\3\0\1\22\1\72\26\22\16\0\1\22\1\0"+
    "\3\22\3\0\30\22\16\0\1\22\1\0\3\22\3\0"+
    "\4\22\1\73\23\22\16\0\1\22\1\0\3\22\3\0"+
    "\1\22\1\74\7\22\1\75\16\22\16\0\1\22\1\0"+
    "\3\22\3\0\1\22\1\76\6\22\1\77\17\22\16\0"+
    "\1\22\1\0\3\22\3\0\1\22\1\100\2\22\1\101"+
    "\23\22\16\0\1\22\1\0\3\22\3\0\11\22\1\102"+
    "\16\22\16\0\1\22\1\0\3\22\36\0\1\103\17\0"+
    "\41\104\1\105\14\104\42\0\1\106\10\0\1\107\51\0"+
    "\1\44\61\0\1\50\5\0\11\22\1\110\16\22\16\0"+
    "\1\22\1\0\3\22\2\111\1\112\53\111\43\0\1\113"+
    "\7\0\1\50\3\0\1\51\57\0\2\22\1\114\25\22"+
    "\16\0\1\22\1\0\3\22\3\0\6\22\1\115\7\22"+
    "\1\116\11\22\16\0\1\22\1\0\3\22\3\0\12\22"+
    "\1\117\15\22\16\0\1\22\1\0\3\22\3\0\5\22"+
    "\1\120\22\22\16\0\1\22\1\0\3\22\3\0\1\22"+
    "\1\121\6\22\1\122\17\22\16\0\1\22\1\0\3\22"+
    "\3\0\23\22\1\123\4\22\16\0\1\22\1\0\3\22"+
    "\3\0\13\22\1\124\14\22\16\0\1\22\1\0\3\22"+
    "\3\0\6\22\1\125\21\22\16\0\1\22\1\0\3\22"+
    "\3\0\6\22\1\126\21\22\16\0\1\22\1\0\3\22"+
    "\3\0\10\22\1\127\17\22\16\0\1\22\1\0\3\22"+
    "\3\0\23\22\1\130\4\22\16\0\1\22\1\0\3\22"+
    "\3\0\2\22\1\131\25\22\16\0\1\22\1\0\3\22"+
    "\3\0\4\22\1\132\23\22\16\0\1\22\1\0\3\22"+
    "\3\0\1\22\1\133\13\22\1\134\12\22\16\0\1\22"+
    "\1\0\3\22\3\0\6\22\1\135\21\22\16\0\1\22"+
    "\1\0\3\22\3\0\13\22\1\136\14\22\16\0\1\22"+
    "\1\0\3\22\3\0\6\22\1\137\21\22\16\0\1\22"+
    "\1\0\3\22\3\0\23\22\1\140\4\22\16\0\1\22"+
    "\1\0\3\22\3\0\6\22\1\141\21\22\16\0\1\22"+
    "\1\0\3\22\3\0\1\22\1\142\26\22\16\0\1\22"+
    "\1\0\3\22\3\0\12\22\1\143\13\22\1\144\1\22"+
    "\16\0\1\22\1\0\3\22\3\0\23\22\1\145\4\22"+
    "\16\0\1\22\1\0\3\22\42\146\1\147\13\146\43\0"+
    "\1\113\7\0\1\107\5\0\20\22\1\150\7\22\16\0"+
    "\1\22\1\0\3\22\53\0\1\151\5\0\3\22\1\152"+
    "\24\22\16\0\1\22\1\0\3\22\3\0\13\22\1\153"+
    "\14\22\16\0\1\22\1\0\3\22\3\0\4\22\1\154"+
    "\23\22\16\0\1\22\1\0\3\22\3\0\4\22\1\155"+
    "\23\22\16\0\1\22\1\0\3\22\3\0\6\22\1\156"+
    "\21\22\16\0\1\22\1\0\3\22\3\0\5\22\1\157"+
    "\22\22\16\0\1\22\1\0\3\22\3\0\24\22\1\160"+
    "\3\22\16\0\1\22\1\0\3\22\3\0\16\22\1\161"+
    "\11\22\16\0\1\22\1\0\3\22\3\0\4\22\1\162"+
    "\23\22\16\0\1\22\1\0\3\22\3\0\4\22\1\163"+
    "\23\22\16\0\1\22\1\0\3\22\3\0\4\22\1\164"+
    "\23\22\16\0\1\22\1\0\3\22\3\0\11\22\1\165"+
    "\16\22\16\0\1\22\1\0\3\22\3\0\10\22\1\166"+
    "\17\22\16\0\1\22\1\0\3\22\3\0\25\22\1\167"+
    "\2\22\16\0\1\22\1\0\3\22\3\0\20\22\1\170"+
    "\7\22\16\0\1\22\1\0\3\22\3\0\11\22\1\171"+
    "\16\22\16\0\1\22\1\0\3\22\3\0\14\22\1\172"+
    "\13\22\16\0\1\22\1\0\3\22\3\0\4\22\1\173"+
    "\23\22\16\0\1\22\1\0\3\22\3\0\17\22\1\174"+
    "\10\22\16\0\1\22\1\0\3\22\3\0\22\22\1\175"+
    "\5\22\16\0\1\22\1\0\3\22\3\0\6\22\1\176"+
    "\21\22\16\0\1\22\1\0\3\22\3\0\1\22\1\177"+
    "\26\22\16\0\1\22\1\0\3\22\3\0\13\22\1\200"+
    "\14\22\16\0\1\22\1\0\3\22\42\146\1\201\55\146"+
    "\1\202\13\146\3\0\12\22\1\124\15\22\16\0\1\22"+
    "\1\0\3\22\3\0\4\22\1\203\23\22\16\0\1\22"+
    "\1\0\3\22\3\0\23\22\1\204\4\22\16\0\1\22"+
    "\1\0\3\22\3\0\11\22\1\205\16\22\16\0\1\22"+
    "\1\0\3\22\3\0\27\22\1\206\16\0\1\22\1\0"+
    "\3\22\3\0\4\22\1\207\23\22\16\0\1\22\1\0"+
    "\3\22\3\0\11\22\1\210\16\22\16\0\1\22\1\0"+
    "\3\22\3\0\10\22\1\211\17\22\16\0\1\22\1\0"+
    "\3\22\3\0\2\22\1\212\25\22\16\0\1\22\1\0"+
    "\3\22\3\0\25\22\1\213\2\22\16\0\1\22\1\0"+
    "\3\22\3\0\12\22\1\214\15\22\16\0\1\22\1\0"+
    "\3\22\3\0\2\22\1\215\25\22\16\0\1\22\1\0"+
    "\3\22\3\0\20\22\1\216\7\22\16\0\1\22\1\0"+
    "\3\22\3\0\4\22\1\217\23\22\16\0\1\22\1\0"+
    "\3\22\3\0\16\22\1\220\11\22\16\0\1\22\1\0"+
    "\3\22\3\0\20\22\1\221\7\22\16\0\1\22\1\0"+
    "\3\22\3\0\13\22\1\222\14\22\16\0\1\22\1\0"+
    "\3\22\3\0\10\22\1\223\17\22\16\0\1\22\1\0"+
    "\3\22\42\146\1\224\13\146\3\0\5\22\1\225\22\22"+
    "\16\0\1\22\1\0\3\22\3\0\11\22\1\226\16\22"+
    "\16\0\1\22\1\0\3\22\3\0\14\22\1\227\13\22"+
    "\16\0\1\22\1\0\3\22\3\0\23\22\1\230\4\22"+
    "\16\0\1\22\1\0\3\22\3\0\6\22\1\231\21\22"+
    "\16\0\1\22\1\0\3\22\3\0\2\22\1\232\25\22"+
    "\16\0\1\22\1\0\3\22\3\0\6\22\1\233\21\22"+
    "\16\0\1\22\1\0\3\22\3\0\4\22\1\234\23\22"+
    "\16\0\1\22\1\0\3\22\3\0\25\22\1\235\2\22"+
    "\16\0\1\22\1\0\3\22\3\0\4\22\1\236\23\22"+
    "\16\0\1\22\1\0\3\22\3\0\11\22\1\237\16\22"+
    "\16\0\1\22\1\0\3\22\3\0\1\22\1\240\26\22"+
    "\16\0\1\22\1\0\3\22\3\0\4\22\1\241\23\22"+
    "\16\0\1\22\1\0\3\22\3\0\23\22\1\242\4\22"+
    "\16\0\1\22\1\0\3\22\3\0\23\22\1\243\4\22"+
    "\16\0\1\22\1\0\3\22\42\0\1\224\16\0\6\22"+
    "\1\244\21\22\16\0\1\22\1\0\3\22\3\0\20\22"+
    "\1\245\7\22\16\0\1\22\1\0\3\22\3\0\20\22"+
    "\1\246\7\22\16\0\1\22\1\0\3\22\3\0\6\22"+
    "\1\247\21\22\16\0\1\22\1\0\3\22\3\0\4\22"+
    "\1\250\23\22\16\0\1\22\1\0\3\22\3\0\11\22"+
    "\1\251\16\22\16\0\1\22\1\0\3\22\3\0\10\22"+
    "\1\252\17\22\16\0\1\22\1\0\3\22\3\0\23\22"+
    "\1\253\4\22\16\0\1\22\1\0\3\22\3\0\2\22"+
    "\1\254\25\22\16\0\1\22\1\0\3\22\3\0\17\22"+
    "\1\255\10\22\16\0\1\22\1\0\3\22\3\0\3\22"+
    "\1\256\24\22\16\0\1\22\1\0\3\22\3\0\4\22"+
    "\1\257\23\22\16\0\1\22\1\0\3\22\3\0\4\22"+
    "\1\260\23\22\16\0\1\22\1\0\3\22\3\0\27\22"+
    "\1\261\16\0\1\22\1\0\3\22\3\0\20\22\1\262"+
    "\7\22\16\0\1\22\1\0\3\22\3\0\1\22\1\263"+
    "\26\22\16\0\1\22\1\0\3\22\3\0\4\22\1\264"+
    "\23\22\16\0\1\22\1\0\3\22\3\0\4\22\1\265"+
    "\23\22\16\0\1\22\1\0\3\22\3\0\17\22\1\266"+
    "\10\22\16\0\1\22\1\0\3\22\3\0\2\22\1\267"+
    "\25\22\16\0\1\22\1\0\3\22\3\0\20\22\1\270"+
    "\7\22\16\0\1\22\1\0\3\22";

  private static int [] zzUnpackTrans() {
    int [] result = new int[6394];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
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
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\1\11\25\1\2\11\1\1\3\11\2\1\4\11"+
    "\37\1\1\11\1\0\1\11\1\0\2\1\1\0\1\11"+
    "\1\0\32\1\2\0\31\1\1\0\67\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[184];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
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

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private CharSequence zzBuffer = "";

  /** this buffer may contains the current text array to be matched when it is cheap to acquire it */
  private char[] zzBufferArray;

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the textposition at the last state to be included in yytext */
  private int zzPushbackPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /**
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /* user code: */
	private BlockManager blockManager = new BlockManager();
	private Queue<IElementType> queue = new LinkedList<>();
	private boolean end = false;

	private IElementType sendToken() {
		IElementType token = (end)? null:TokenType.WHITE_SPACE;
		if (!queue.isEmpty())
			token = queue.poll();
		if (!queue.isEmpty())
			yypushback(yylength());
		return token;
	}

	private IElementType eof(){
		if (queue.isEmpty() && !end) {
            blockManager.eof();
            storeTokens();
            end = true;
        }
        return sendToken();
    }

	private String getTextSpaces(String text){
        int index = (text.indexOf(' ') == -1)? text.indexOf('\t') : text.indexOf(' ');
        return (index == -1)? "" : text.substring(index);
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

    private IElementType openBracket() {
        blockManager.openBracket(yytext().length());
        storeTokens();
        return sendToken();
    }

    private IElementType closeBracket() {
       if (queue.isEmpty()) {
            blockManager.closeBracket(yytext().length());
            storeTokens();
        }
        return sendToken();
    }

	private IElementType semicolon(){
        blockManager.semicolon(yytext().length());
        storeTokens();
        return sendToken();
    }

    private void storeTokens(){
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
   * @param   in  the java.io.Inputstream to read input from.
   */
  TaraLexer(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }

  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x10000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 2214) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }

  public final int getTokenStart(){
    return zzStartRead;
  }

  public final int getTokenEnd(){
    return getTokenStart() + yylength();
  }

  public void reset(CharSequence buffer, int start, int end,int initialState){
    zzBuffer = buffer;
    zzBufferArray = com.intellij.util.text.CharArrayUtil.fromSequenceWithoutCopying(buffer);
    zzCurrentPos = zzMarkedPos = zzStartRead = start;
    zzPushbackPos = 0;
    zzAtEOF  = false;
    zzAtBOL = true;
    zzEndRead = end;
    yybegin(initialState);
  }

  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   *
   * @exception   java.io.IOException  if any I/O-Error occurs
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
   *
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch.
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBufferArray != null ? zzBufferArray[zzStartRead+pos]:zzBuffer.charAt(zzStartRead+pos);
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of
   * yypushback(int) and a match-all fallback rule) this method
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  }


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
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
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;

      zzState = ZZ_LEXSTATE[zzLexicalState];


      zzForAction: {
        while (true) {

          if (zzCurrentPosL < zzEndReadL)
            zzInput = (zzBufferArrayL != null ? zzBufferArrayL[zzCurrentPosL++] : zzBufferL.charAt(zzCurrentPosL++));
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = (zzBufferArrayL != null ? zzBufferArrayL[zzCurrentPosL++] : zzBufferL.charAt(zzCurrentPosL++));
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 34: 
          { return TaraTypes.STRING_MULTILINE_VALUE_KEY;
          }
        case 52: break;
        case 7: 
          { return TaraTypes.LEFT_SQUARE;
          }
        case 53: break;
        case 32: 
          { return TaraTypes.WITH;
          }
        case 54: break;
        case 26: 
          { return TaraTypes.VAR;
          }
        case 55: break;
        case 14: 
          { return TaraTypes.COLON;
          }
        case 56: break;
        case 38: 
          { return TaraTypes.SINGLE;
          }
        case 57: break;
        case 27: 
          { return TaraTypes.DOUBLE_VALUE_KEY;
          }
        case 58: break;
        case 2: 
          { return TokenType.WHITE_SPACE;
          }
        case 59: break;
        case 41: 
          { return TaraTypes.NATURAL_TYPE;
          }
        case 60: break;
        case 6: 
          { return TaraTypes.RIGHT_PARENTHESIS;
          }
        case 61: break;
        case 20: 
          { return TaraTypes.LIST;
          }
        case 62: break;
        case 11: 
          { return TaraTypes.DOT;
          }
        case 63: break;
        case 50: 
          { return TaraTypes.INTENTION_KEY;
          }
        case 64: break;
        case 37: 
          { return TaraTypes.STRING_TYPE;
          }
        case 65: break;
        case 9: 
          { return openBracket();
          }
        case 66: break;
        case 47: 
          { return TaraTypes.TERMINAL;
          }
        case 67: break;
        case 30: 
          { return TaraTypes.DATE_TYPE;
          }
        case 68: break;
        case 51: 
          { return TaraTypes.METAMODEL;
          }
        case 69: break;
        case 4: 
          { return TaraTypes.IDENTIFIER_KEY;
          }
        case 70: break;
        case 35: 
          { return TaraTypes.EMPTY_REF;
          }
        case 71: break;
        case 5: 
          { return TaraTypes.LEFT_PARENTHESIS;
          }
        case 72: break;
        case 49: 
          { return TaraTypes.REQUIRED;
          }
        case 73: break;
        case 42: 
          { return TaraTypes.PRIVATE;
          }
        case 74: break;
        case 22: 
          { return TaraTypes.NEGATIVE_VALUE_KEY;
          }
        case 75: break;
        case 33: 
          { return TaraTypes.ROOT;
          }
        case 76: break;
        case 23: 
          { return TaraTypes.DOC_LINE;
          }
        case 77: break;
        case 21: 
          { return TaraTypes.STRING_VALUE_KEY;
          }
        case 78: break;
        case 46: 
          { return TaraTypes.PROPERTY;
          }
        case 79: break;
        case 12: 
          { return TaraTypes.STAR;
          }
        case 80: break;
        case 39: 
          { return TaraTypes.DOUBLE_TYPE;
          }
        case 81: break;
        case 8: 
          { return TaraTypes.RIGHT_SQUARE;
          }
        case 82: break;
        case 16: 
          { return TaraTypes.NATURAL_VALUE_KEY;
          }
        case 83: break;
        case 15: 
          { return semicolon();
          }
        case 84: break;
        case 1: 
          { return TokenType.BAD_CHARACTER;
          }
        case 85: break;
        case 48: 
          { return TaraTypes.RESOURCE_KEY;
          }
        case 86: break;
        case 24: 
          { return TaraTypes.USE_KEY;
          }
        case 87: break;
        case 36: 
          { return TaraTypes.ALIAS_TYPE;
          }
        case 88: break;
        case 29: 
          { return TaraTypes.BOOLEAN_VALUE_KEY;
          }
        case 89: break;
        case 45: 
          { return TaraTypes.NAMEABLE;
          }
        case 90: break;
        case 19: 
          { return TaraTypes.AS;
          }
        case 91: break;
        case 17: 
          { return TaraTypes.ON;
          }
        case 92: break;
        case 44: 
          { return TaraTypes.BOOLEAN_TYPE;
          }
        case 93: break;
        case 28: 
          { return TaraTypes.CASE_KEY;
          }
        case 94: break;
        case 18: 
          { return TaraTypes.IS;
          }
        case 95: break;
        case 43: 
          { return TaraTypes.INT_TYPE;
          }
        case 96: break;
        case 40: 
          { return TaraTypes.METAIDENTIFIER_KEY;
          }
        case 97: break;
        case 10: 
          { return closeBracket();
          }
        case 98: break;
        case 31: 
          { return TaraTypes.WORD_KEY;
          }
        case 99: break;
        case 13: 
          { return TaraTypes.COMMA;
          }
        case 100: break;
        case 25: 
          { return TaraTypes.BOX_KEY;
          }
        case 101: break;
        case 3: 
          { return newlineIndent();
          }
        case 102: break;
        default:
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            switch (zzLexicalState) {
            case YYINITIAL: {
              return eof();
            }
            case 185: break;
            default:
            return null;
            }
          }
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
