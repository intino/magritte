/* The following code was generated by JFlex 1.4.3 on 5/06/15 14:23 */

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
 * on 5/06/15 14:23 from the specification file
 * <tt>/Users/oroncal/workspace/tara/intellij/src/siani/tara/intellij/lang/lexer/Tara.flex</tt>
 */
class TaraLexer implements FlexLexer {
  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int QUOTED = 2;
  public static final int YYINITIAL = 0;
  public static final int MULTILINE = 4;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0,  0,  1,  1,  2, 2
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\55\1\1\1\57\2\0\1\2\16\55\4\0\1\1\1\0\1\40"+
    "\1\52\1\42\1\50\2\0\1\33\1\34\1\56\1\60\1\45\1\41"+
    "\1\37\1\44\12\61\1\46\1\51\1\0\1\47\1\3\2\0\2\42"+
    "\1\4\1\42\1\54\25\42\1\35\1\62\1\36\1\0\1\42\1\0"+
    "\1\17\1\15\1\7\1\22\1\10\1\32\1\27\1\16\1\25\2\42"+
    "\1\23\1\30\1\6\1\5\1\11\1\31\1\21\1\13\1\12\1\14"+
    "\1\20\1\24\1\26\1\53\1\42\1\0\1\2\2\0\41\55\2\0"+
    "\4\42\4\0\1\42\2\0\1\55\2\0\1\50\4\0\1\42\1\0"+
    "\1\43\2\0\1\42\5\0\27\42\1\0\37\42\1\0\u01ca\42\4\0"+
    "\14\42\16\0\5\42\7\0\1\42\1\0\1\42\21\0\160\55\5\42"+
    "\1\0\2\42\2\0\4\42\10\0\1\42\1\0\3\42\1\0\1\42"+
    "\1\0\24\42\1\0\123\42\1\0\213\42\1\0\5\55\2\0\236\42"+
    "\11\0\46\42\2\0\1\42\7\0\47\42\7\0\1\42\1\0\55\55"+
    "\1\0\1\55\1\0\2\55\1\0\2\55\1\0\1\55\10\0\33\42"+
    "\5\0\3\42\15\0\5\55\6\0\1\42\4\0\13\55\5\0\53\42"+
    "\25\55\12\61\4\0\2\42\1\55\143\42\1\0\1\42\10\55\1\0"+
    "\6\55\2\42\2\55\1\0\4\55\2\42\12\61\3\42\2\0\1\42"+
    "\17\0\1\55\1\42\1\55\36\42\33\55\2\0\131\42\13\55\1\42"+
    "\16\0\12\61\41\42\11\55\2\42\4\0\1\42\5\0\26\42\4\55"+
    "\1\42\11\55\1\42\3\55\1\42\5\55\22\0\31\42\3\55\104\0"+
    "\1\42\1\0\13\42\67\0\33\55\1\0\4\55\66\42\3\55\1\42"+
    "\22\55\1\42\7\55\12\42\2\55\2\0\12\61\1\0\7\42\1\0"+
    "\7\42\1\0\3\55\1\0\10\42\2\0\2\42\2\0\26\42\1\0"+
    "\7\42\1\0\1\42\3\0\4\42\2\0\1\55\1\42\7\55\2\0"+
    "\2\55\2\0\3\55\1\42\10\0\1\55\4\0\2\42\1\0\3\42"+
    "\2\55\2\0\12\61\4\42\7\0\1\42\5\0\3\55\1\0\6\42"+
    "\4\0\2\42\2\0\26\42\1\0\7\42\1\0\2\42\1\0\2\42"+
    "\1\0\2\42\2\0\1\55\1\0\5\55\4\0\2\55\2\0\3\55"+
    "\3\0\1\55\7\0\4\42\1\0\1\42\7\0\12\61\2\55\3\42"+
    "\1\55\13\0\3\55\1\0\11\42\1\0\3\42\1\0\26\42\1\0"+
    "\7\42\1\0\2\42\1\0\5\42\2\0\1\55\1\42\10\55\1\0"+
    "\3\55\1\0\3\55\2\0\1\42\17\0\2\42\2\55\2\0\12\61"+
    "\1\0\1\42\17\0\3\55\1\0\10\42\2\0\2\42\2\0\26\42"+
    "\1\0\7\42\1\0\2\42\1\0\5\42\2\0\1\55\1\42\7\55"+
    "\2\0\2\55\2\0\3\55\10\0\2\55\4\0\2\42\1\0\3\42"+
    "\2\55\2\0\12\61\1\0\1\42\20\0\1\55\1\42\1\0\6\42"+
    "\3\0\3\42\1\0\4\42\3\0\2\42\1\0\1\42\1\0\2\42"+
    "\3\0\2\42\3\0\3\42\3\0\14\42\4\0\5\55\3\0\3\55"+
    "\1\0\4\55\2\0\1\42\6\0\1\55\16\0\12\61\11\0\1\42"+
    "\7\0\3\55\1\0\10\42\1\0\3\42\1\0\27\42\1\0\12\42"+
    "\1\0\5\42\3\0\1\42\7\55\1\0\3\55\1\0\4\55\7\0"+
    "\2\55\1\0\2\42\6\0\2\42\2\55\2\0\12\61\22\0\2\55"+
    "\1\0\10\42\1\0\3\42\1\0\27\42\1\0\12\42\1\0\5\42"+
    "\2\0\1\55\1\42\7\55\1\0\3\55\1\0\4\55\7\0\2\55"+
    "\7\0\1\42\1\0\2\42\2\55\2\0\12\61\1\0\2\42\17\0"+
    "\2\55\1\0\10\42\1\0\3\42\1\0\51\42\2\0\1\42\7\55"+
    "\1\0\3\55\1\0\4\55\1\42\10\0\1\55\10\0\2\42\2\55"+
    "\2\0\12\61\12\0\6\42\2\0\2\55\1\0\22\42\3\0\30\42"+
    "\1\0\11\42\1\0\1\42\2\0\7\42\3\0\1\55\4\0\6\55"+
    "\1\0\1\55\1\0\10\55\22\0\2\55\15\0\60\42\1\55\2\42"+
    "\7\55\4\0\10\42\10\55\1\0\12\61\47\0\2\42\1\0\1\42"+
    "\2\0\2\42\1\0\1\42\2\0\1\42\6\0\4\42\1\0\7\42"+
    "\1\0\3\42\1\0\1\42\1\0\1\42\2\0\2\42\1\0\4\42"+
    "\1\55\2\42\6\55\1\0\2\55\1\42\2\0\5\42\1\0\1\42"+
    "\1\0\6\55\2\0\12\61\2\0\4\42\40\0\1\42\27\0\2\55"+
    "\6\0\12\61\13\0\1\55\1\0\1\55\1\0\1\55\4\0\2\55"+
    "\10\42\1\0\44\42\4\0\24\55\1\0\2\55\5\42\13\55\1\0"+
    "\44\55\11\0\1\55\71\0\53\42\24\55\1\42\12\61\6\0\6\42"+
    "\4\55\4\42\3\55\1\42\3\55\2\42\7\55\3\42\4\55\15\42"+
    "\14\55\1\42\1\55\12\61\4\55\2\0\46\42\1\0\1\42\5\0"+
    "\1\42\2\0\53\42\1\0\u014d\42\1\0\4\42\2\0\7\42\1\0"+
    "\1\42\1\0\4\42\2\0\51\42\1\0\4\42\2\0\41\42\1\0"+
    "\4\42\2\0\7\42\1\0\1\42\1\0\4\42\2\0\17\42\1\0"+
    "\71\42\1\0\4\42\2\0\103\42\2\0\3\55\40\0\20\42\20\0"+
    "\125\42\14\0\u026c\42\2\0\21\42\1\0\32\42\5\0\113\42\3\0"+
    "\3\42\17\0\15\42\1\0\4\42\3\55\13\0\22\42\3\55\13\0"+
    "\22\42\2\55\14\0\15\42\1\0\3\42\1\0\2\55\14\0\64\42"+
    "\40\55\3\0\1\42\3\0\2\42\1\55\2\0\12\61\41\0\3\55"+
    "\2\0\12\61\6\0\130\42\10\0\51\42\1\55\1\42\5\0\106\42"+
    "\12\0\35\42\3\0\14\55\4\0\14\55\12\0\12\61\36\42\2\0"+
    "\5\42\13\0\54\42\4\0\21\55\7\42\2\55\6\0\12\61\46\0"+
    "\27\42\5\55\4\0\65\42\12\55\1\0\35\55\2\0\1\55\12\61"+
    "\6\0\12\61\15\0\1\42\130\0\5\55\57\42\21\55\7\42\4\0"+
    "\12\61\21\0\11\55\14\0\3\55\36\42\15\55\2\42\12\61\54\42"+
    "\16\55\14\0\44\42\24\55\10\0\12\61\3\0\3\42\12\61\44\42"+
    "\122\0\3\55\1\0\25\55\4\42\1\55\4\42\3\55\2\42\11\0"+
    "\300\42\47\55\25\0\4\55\u0116\42\2\0\6\42\2\0\46\42\2\0"+
    "\6\42\2\0\10\42\1\0\1\42\1\0\1\42\1\0\1\42\1\0"+
    "\37\42\2\0\65\42\1\0\7\42\1\0\1\42\3\0\3\42\1\0"+
    "\7\42\3\0\4\42\2\0\6\42\4\0\15\42\5\0\3\42\1\0"+
    "\7\42\16\0\5\55\32\0\5\55\20\0\2\42\23\0\1\42\13\0"+
    "\5\55\5\0\6\55\1\0\1\42\15\0\1\42\20\0\15\42\3\0"+
    "\14\42\1\42\16\42\25\0\15\55\4\0\1\55\3\0\14\55\21\0"+
    "\1\42\4\0\1\42\2\0\12\42\1\0\1\42\3\0\5\42\6\0"+
    "\1\42\1\0\1\42\1\0\1\42\1\0\4\42\1\0\13\42\2\0"+
    "\4\42\5\0\5\42\4\0\1\42\21\0\51\42\u0a77\0\57\42\1\0"+
    "\57\42\1\0\205\42\6\0\4\42\3\55\2\42\14\0\46\42\1\0"+
    "\1\42\5\0\1\42\2\0\70\42\7\0\1\42\17\0\1\55\27\42"+
    "\11\0\7\42\1\0\7\42\1\0\7\42\1\0\7\42\1\0\7\42"+
    "\1\0\7\42\1\0\7\42\1\0\7\42\1\0\40\55\57\0\1\42"+
    "\u01d5\0\3\42\31\0\11\42\6\55\1\0\5\42\2\0\5\42\4\0"+
    "\126\42\2\0\2\55\2\0\3\42\1\0\132\42\1\0\4\42\5\0"+
    "\51\42\3\0\136\42\21\0\33\42\65\0\20\42\u0200\0\u19b6\42\112\0"+
    "\u51cd\42\63\0\u048d\42\103\0\56\42\2\0\u010d\42\3\0\20\42\12\61"+
    "\2\42\24\0\57\42\1\55\4\0\12\55\1\0\31\42\7\0\1\55"+
    "\120\42\2\55\45\0\11\42\2\0\147\42\2\0\4\42\1\0\4\42"+
    "\14\0\13\42\115\0\12\42\1\55\3\42\1\55\4\42\1\55\27\42"+
    "\5\55\20\0\1\42\7\0\64\42\14\0\2\55\62\42\21\55\13\0"+
    "\12\61\6\0\22\55\6\42\3\0\1\42\4\0\12\61\34\42\10\55"+
    "\2\0\27\42\15\55\14\0\35\42\3\0\4\55\57\42\16\55\16\0"+
    "\1\42\12\61\46\0\51\42\16\55\11\0\3\42\1\55\10\42\2\55"+
    "\2\0\12\61\6\0\27\42\3\0\1\42\1\55\4\0\60\42\1\55"+
    "\1\42\3\55\2\42\2\55\5\42\2\55\1\42\1\55\1\42\30\0"+
    "\3\42\2\0\13\42\5\55\2\0\3\42\2\55\12\0\6\42\2\0"+
    "\6\42\2\0\6\42\11\0\7\42\1\0\7\42\221\0\43\42\10\55"+
    "\1\0\2\55\2\0\12\61\6\0\u2ba4\42\14\0\27\42\4\0\61\42"+
    "\u2104\0\u016e\42\2\0\152\42\46\0\7\42\14\0\5\42\5\0\1\42"+
    "\1\55\12\42\1\0\15\42\1\0\5\42\1\0\1\42\1\0\2\42"+
    "\1\0\2\42\1\0\154\42\41\0\u016b\42\22\0\100\42\2\0\66\42"+
    "\50\0\15\42\3\0\20\55\20\0\7\55\14\0\2\42\30\0\3\42"+
    "\31\0\1\42\6\0\5\42\1\0\207\42\2\0\1\55\4\0\1\42"+
    "\13\0\12\61\7\0\32\42\4\0\1\42\1\0\32\42\13\0\131\42"+
    "\3\0\6\42\2\0\6\42\2\0\6\42\2\0\3\42\3\0\2\42"+
    "\3\0\2\42\22\0\3\55\4\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\3\0\1\1\1\2\1\3\1\4\23\5\1\6\1\7"+
    "\1\10\1\11\1\12\1\13\2\1\1\14\1\15\1\16"+
    "\1\17\1\20\2\1\1\21\1\22\1\23\2\22\1\3"+
    "\1\5\1\24\15\5\1\25\12\5\1\26\6\5\1\0"+
    "\1\27\1\30\1\31\1\0\1\32\1\0\1\23\11\5"+
    "\1\33\1\5\1\34\1\5\1\35\1\5\1\36\4\5"+
    "\1\37\14\5\1\40\2\0\1\41\10\5\1\42\6\5"+
    "\1\0\1\17\1\43\1\5\1\44\1\45\1\46\1\47"+
    "\3\5\1\50\3\5\1\51\1\0\1\31\1\0\5\5"+
    "\1\52\7\5\1\53\5\5\1\54\1\55\1\31\1\0"+
    "\1\41\2\5\1\56\4\5\1\57\1\60\3\5\1\61"+
    "\4\5\1\62\1\63\1\5\1\64\1\65\1\5\1\66"+
    "\2\5\1\67\1\5\1\70\1\71\1\72\1\73\1\74"+
    "\1\75\1\76";

  private static int [] zzUnpackAction() {
    int [] result = new int[218];
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
    "\0\0\0\63\0\146\0\231\0\314\0\377\0\231\0\u0132"+
    "\0\u0165\0\u0198\0\u01cb\0\u01fe\0\u0231\0\u0264\0\u0297\0\u02ca"+
    "\0\u02fd\0\u0330\0\u0363\0\u0396\0\u03c9\0\u03fc\0\u042f\0\u0462"+
    "\0\u0495\0\u04c8\0\231\0\231\0\231\0\231\0\u04fb\0\231"+
    "\0\u052e\0\u0561\0\231\0\231\0\231\0\u0594\0\u05c7\0\u05fa"+
    "\0\u062d\0\u0660\0\231\0\231\0\u0693\0\u06c6\0\u06f9\0\u072c"+
    "\0\u01cb\0\u075f\0\u0792\0\u07c5\0\u07f8\0\u082b\0\u085e\0\u0891"+
    "\0\u08c4\0\u08f7\0\u092a\0\u095d\0\u0990\0\u09c3\0\u01cb\0\u09f6"+
    "\0\u0a29\0\u0a5c\0\u0a8f\0\u0ac2\0\u0af5\0\u0b28\0\u0b5b\0\u0b8e"+
    "\0\u0bc1\0\u01cb\0\u0bf4\0\u0c27\0\u0c5a\0\u0c8d\0\u0cc0\0\u0cf3"+
    "\0\u0d26\0\u0d59\0\u0d8c\0\u0dbf\0\u0df2\0\u05fa\0\u0e25\0\u06c6"+
    "\0\u0e58\0\u0e8b\0\u0ebe\0\u0ef1\0\u0f24\0\u0f57\0\u0f8a\0\u0fbd"+
    "\0\u0ff0\0\u01cb\0\u1023\0\u01cb\0\u1056\0\u01cb\0\u1089\0\u01cb"+
    "\0\u10bc\0\u10ef\0\u1122\0\u1155\0\u01cb\0\u1188\0\u11bb\0\u11ee"+
    "\0\u1221\0\u1254\0\u1287\0\u12ba\0\u12ed\0\u1320\0\u1353\0\u1386"+
    "\0\u13b9\0\231\0\u13ec\0\u141f\0\u1452\0\u1485\0\u14b8\0\u14eb"+
    "\0\u151e\0\u1551\0\u1584\0\u15b7\0\u15ea\0\u01cb\0\u161d\0\u1650"+
    "\0\u1683\0\u16b6\0\u16e9\0\u171c\0\u174f\0\u1782\0\231\0\u17b5"+
    "\0\u01cb\0\u01cb\0\u01cb\0\u01cb\0\u17e8\0\u181b\0\u184e\0\u01cb"+
    "\0\u1881\0\u18b4\0\u18e7\0\u01cb\0\u191a\0\u13ec\0\u194d\0\u1980"+
    "\0\u19b3\0\u19e6\0\u1a19\0\u1a4c\0\u01cb\0\u1a7f\0\u1ab2\0\u1ae5"+
    "\0\u1b18\0\u1b4b\0\u1b7e\0\u1bb1\0\u01cb\0\u1be4\0\u1c17\0\u1c4a"+
    "\0\u1c7d\0\u1cb0\0\u01cb\0\u01cb\0\231\0\u1ce3\0\u1ce3\0\u1d16"+
    "\0\u1d49\0\u01cb\0\u1d7c\0\u1daf\0\u1de2\0\u1e15\0\u01cb\0\u01cb"+
    "\0\u1e48\0\u1e7b\0\u1eae\0\u01cb\0\u1ee1\0\u1f14\0\u1f47\0\u1f7a"+
    "\0\u01cb\0\u01cb\0\u1fad\0\u01cb\0\u01cb\0\u1fe0\0\u01cb\0\u2013"+
    "\0\u2046\0\u01cb\0\u2079\0\u01cb\0\u01cb\0\u01cb\0\u01cb\0\u01cb"+
    "\0\u01cb\0\u01cb";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[218];
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
    "\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13"+
    "\1\14\1\15\1\16\1\17\1\20\1\21\1\22\1\23"+
    "\1\24\1\25\1\26\1\13\1\27\1\30\2\13\1\31"+
    "\1\13\1\32\1\33\1\34\1\35\1\36\1\37\1\40"+
    "\1\41\1\13\1\4\1\42\1\43\1\44\1\45\1\46"+
    "\1\47\1\50\2\13\2\4\1\6\1\51\1\52\1\4"+
    "\40\53\1\54\21\53\1\55\41\53\1\56\20\53\1\55"+
    "\64\0\1\5\62\0\1\57\1\6\54\0\1\6\7\0"+
    "\1\13\1\60\25\13\6\0\2\13\2\46\6\0\3\13"+
    "\3\0\1\13\5\0\2\13\1\61\24\13\6\0\2\13"+
    "\2\46\6\0\3\13\3\0\1\13\5\0\13\13\1\62"+
    "\13\13\6\0\2\13\2\46\6\0\3\13\3\0\1\13"+
    "\5\0\27\13\6\0\2\13\2\46\6\0\3\13\3\0"+
    "\1\13\5\0\2\13\1\63\17\13\1\64\1\13\1\65"+
    "\2\13\6\0\2\13\2\46\6\0\3\13\3\0\1\13"+
    "\5\0\15\13\1\66\11\13\6\0\2\13\2\46\6\0"+
    "\3\13\3\0\1\13\5\0\4\13\1\67\10\13\1\70"+
    "\11\13\6\0\2\13\2\46\6\0\3\13\3\0\1\13"+
    "\5\0\6\13\1\71\1\13\1\72\10\13\1\73\5\13"+
    "\6\0\2\13\2\46\6\0\3\13\3\0\1\13\5\0"+
    "\7\13\1\74\17\13\6\0\2\13\2\46\6\0\3\13"+
    "\3\0\1\13\5\0\1\13\1\75\25\13\6\0\2\13"+
    "\2\46\6\0\3\13\3\0\1\13\5\0\13\13\1\76"+
    "\13\13\6\0\2\13\2\46\6\0\3\13\3\0\1\13"+
    "\5\0\7\13\1\77\1\13\1\100\15\13\6\0\2\13"+
    "\2\46\6\0\3\13\3\0\1\13\5\0\13\13\1\101"+
    "\13\13\6\0\2\13\2\46\6\0\3\13\3\0\1\13"+
    "\5\0\4\13\1\102\6\13\1\103\13\13\6\0\2\13"+
    "\2\46\6\0\3\13\3\0\1\13\5\0\1\13\1\104"+
    "\5\13\1\105\3\13\1\106\13\13\6\0\2\13\2\46"+
    "\6\0\3\13\3\0\1\13\5\0\1\13\1\107\17\13"+
    "\1\110\5\13\6\0\2\13\2\46\6\0\3\13\3\0"+
    "\1\13\5\0\2\13\1\111\4\13\1\112\14\13\1\113"+
    "\2\13\6\0\2\13\2\46\6\0\3\13\3\0\1\13"+
    "\5\0\4\13\1\114\6\13\1\115\13\13\6\0\2\13"+
    "\2\46\6\0\3\13\3\0\1\13\5\0\4\13\1\116"+
    "\6\13\1\117\5\13\1\120\5\13\6\0\2\13\2\46"+
    "\6\0\3\13\3\0\1\13\40\0\1\121\64\0\1\122"+
    "\17\0\1\123\45\0\1\124\11\0\1\125\10\0\27\46"+
    "\6\0\4\46\6\0\3\46\3\0\1\46\52\0\1\47"+
    "\15\0\27\126\7\0\1\126\10\0\2\126\67\0\1\52"+
    "\40\0\1\127\21\0\1\52\7\0\1\53\3\0\1\53"+
    "\6\0\1\53\16\0\1\53\63\0\1\130\22\0\1\57"+
    "\65\0\2\13\1\131\24\13\6\0\2\13\2\46\6\0"+
    "\3\13\3\0\1\13\5\0\6\13\1\132\20\13\6\0"+
    "\2\13\2\46\6\0\3\13\3\0\1\13\5\0\3\13"+
    "\1\133\23\13\6\0\2\13\2\46\6\0\3\13\3\0"+
    "\1\13\5\0\6\13\1\134\20\13\6\0\2\13\2\46"+
    "\6\0\3\13\3\0\1\13\5\0\5\13\1\135\21\13"+
    "\6\0\2\13\2\46\6\0\3\13\3\0\1\13\5\0"+
    "\21\13\1\136\5\13\6\0\2\13\2\46\6\0\3\13"+
    "\3\0\1\13\5\0\15\13\1\137\11\13\6\0\2\13"+
    "\2\46\6\0\3\13\3\0\1\13\5\0\10\13\1\140"+
    "\16\13\6\0\2\13\2\46\6\0\3\13\3\0\1\13"+
    "\5\0\15\13\1\141\11\13\6\0\2\13\2\46\6\0"+
    "\3\13\3\0\1\13\5\0\11\13\1\142\15\13\6\0"+
    "\2\13\2\46\6\0\3\13\3\0\1\13\5\0\2\13"+
    "\1\143\24\13\6\0\2\13\2\46\6\0\3\13\3\0"+
    "\1\13\5\0\4\13\1\144\22\13\6\0\2\13\2\46"+
    "\6\0\3\13\3\0\1\13\5\0\1\13\1\145\25\13"+
    "\6\0\2\13\2\46\6\0\3\13\3\0\1\13\5\0"+
    "\7\13\1\146\17\13\6\0\2\13\2\46\6\0\3\13"+
    "\3\0\1\13\5\0\7\13\1\147\17\13\6\0\2\13"+
    "\2\46\6\0\3\13\3\0\1\13\5\0\15\13\1\150"+
    "\11\13\6\0\2\13\2\46\6\0\3\13\3\0\1\13"+
    "\5\0\25\13\1\151\1\13\6\0\2\13\2\46\6\0"+
    "\3\13\3\0\1\13\5\0\6\13\1\152\20\13\6\0"+
    "\2\13\2\46\6\0\3\13\3\0\1\13\5\0\3\13"+
    "\1\153\4\13\1\154\16\13\6\0\2\13\2\46\6\0"+
    "\3\13\3\0\1\13\5\0\17\13\1\155\7\13\6\0"+
    "\2\13\2\46\6\0\3\13\3\0\1\13\5\0\6\13"+
    "\1\156\20\13\6\0\2\13\2\46\6\0\3\13\3\0"+
    "\1\13\5\0\15\13\1\157\11\13\6\0\2\13\2\46"+
    "\6\0\3\13\3\0\1\13\5\0\6\13\1\160\20\13"+
    "\6\0\2\13\2\46\6\0\3\13\3\0\1\13\5\0"+
    "\6\13\1\161\20\13\6\0\2\13\2\46\6\0\3\13"+
    "\3\0\1\13\5\0\5\13\1\162\21\13\6\0\2\13"+
    "\2\46\6\0\3\13\3\0\1\13\5\0\13\13\1\163"+
    "\13\13\6\0\2\13\2\46\6\0\3\13\3\0\1\13"+
    "\5\0\21\13\1\164\5\13\6\0\2\13\2\46\6\0"+
    "\3\13\3\0\1\13\5\0\13\13\1\165\13\13\6\0"+
    "\2\13\2\46\6\0\3\13\3\0\1\13\5\0\3\13"+
    "\1\166\13\13\1\167\7\13\6\0\2\13\2\46\6\0"+
    "\3\13\3\0\1\13\5\0\2\13\1\170\14\13\1\171"+
    "\7\13\6\0\2\13\2\46\6\0\3\13\3\0\1\13"+
    "\40\0\1\172\64\0\1\122\60\0\1\127\21\0\1\123"+
    "\1\0\57\124\1\0\3\124\56\173\1\174\4\173\61\0"+
    "\1\175\5\0\3\13\1\176\23\13\6\0\2\13\2\46"+
    "\6\0\3\13\3\0\1\13\5\0\10\13\1\177\10\13"+
    "\1\200\5\13\6\0\2\13\2\46\6\0\3\13\3\0"+
    "\1\13\5\0\17\13\1\201\7\13\6\0\2\13\2\46"+
    "\6\0\3\13\3\0\1\13\5\0\4\13\1\202\22\13"+
    "\6\0\2\13\2\46\6\0\3\13\3\0\1\13\5\0"+
    "\6\13\1\203\20\13\6\0\2\13\2\46\6\0\3\13"+
    "\3\0\1\13\5\0\14\13\1\204\12\13\6\0\2\13"+
    "\2\46\6\0\3\13\3\0\1\13\5\0\24\13\1\205"+
    "\2\13\6\0\2\13\2\46\6\0\3\13\3\0\1\13"+
    "\5\0\4\13\1\206\22\13\6\0\2\13\2\46\6\0"+
    "\3\13\3\0\1\13\5\0\21\13\1\207\5\13\6\0"+
    "\2\13\2\46\6\0\3\13\3\0\1\13\5\0\23\13"+
    "\1\210\3\13\6\0\2\13\2\46\6\0\3\13\3\0"+
    "\1\13\5\0\17\13\1\211\7\13\6\0\2\13\2\46"+
    "\6\0\3\13\3\0\1\13\5\0\6\13\1\212\20\13"+
    "\6\0\2\13\2\46\6\0\3\13\3\0\1\13\5\0"+
    "\10\13\1\213\16\13\6\0\2\13\2\46\6\0\3\13"+
    "\3\0\1\13\5\0\21\13\1\214\5\13\6\0\2\13"+
    "\2\46\6\0\3\13\3\0\1\13\1\0\4\215\27\153"+
    "\6\215\2\153\2\216\6\215\3\153\1\215\1\217\1\215"+
    "\1\153\1\215\4\0\11\13\1\220\15\13\6\0\2\13"+
    "\2\46\6\0\3\13\3\0\1\13\5\0\4\13\1\221"+
    "\22\13\6\0\2\13\2\46\6\0\3\13\3\0\1\13"+
    "\5\0\16\13\1\222\10\13\6\0\2\13\2\46\6\0"+
    "\3\13\3\0\1\13\5\0\12\13\1\223\14\13\6\0"+
    "\2\13\2\46\6\0\3\13\3\0\1\13\5\0\1\13"+
    "\1\224\2\13\1\225\22\13\6\0\2\13\2\46\6\0"+
    "\3\13\3\0\1\13\5\0\17\13\1\226\7\13\6\0"+
    "\2\13\2\46\6\0\3\13\3\0\1\13\5\0\7\13"+
    "\1\227\17\13\6\0\2\13\2\46\6\0\3\13\3\0"+
    "\1\13\5\0\2\13\1\230\24\13\6\0\2\13\2\46"+
    "\6\0\3\13\3\0\1\13\5\0\6\13\1\231\20\13"+
    "\6\0\2\13\2\46\6\0\3\13\3\0\1\13\5\0"+
    "\4\13\1\232\22\13\6\0\2\13\2\46\6\0\3\13"+
    "\3\0\1\13\5\0\7\13\1\140\17\13\6\0\2\13"+
    "\2\46\6\0\3\13\3\0\1\13\5\0\13\13\1\233"+
    "\13\13\6\0\2\13\2\46\6\0\3\13\3\0\1\13"+
    "\5\0\4\13\1\234\22\13\6\0\2\13\2\46\6\0"+
    "\3\13\3\0\1\13\1\0\56\173\1\235\50\173\1\236"+
    "\11\173\1\235\4\173\54\0\1\237\4\0\1\175\5\0"+
    "\4\13\1\240\22\13\6\0\2\13\2\46\6\0\3\13"+
    "\3\0\1\13\5\0\15\13\1\241\11\13\6\0\2\13"+
    "\2\46\6\0\3\13\3\0\1\13\5\0\14\13\1\242"+
    "\12\13\6\0\2\13\2\46\6\0\3\13\3\0\1\13"+
    "\5\0\1\13\1\243\25\13\6\0\2\13\2\46\6\0"+
    "\3\13\3\0\1\13\5\0\2\13\1\244\24\13\6\0"+
    "\2\13\2\46\6\0\3\13\3\0\1\13\5\0\27\13"+
    "\6\0\2\13\2\46\6\0\1\245\2\13\3\0\1\13"+
    "\5\0\13\13\1\246\13\13\6\0\2\13\2\46\6\0"+
    "\3\13\3\0\1\13\5\0\21\13\1\247\5\13\6\0"+
    "\2\13\2\46\6\0\3\13\3\0\1\13\5\0\2\13"+
    "\1\250\24\13\6\0\2\13\2\46\6\0\3\13\3\0"+
    "\1\13\5\0\17\13\1\251\7\13\6\0\2\13\2\46"+
    "\6\0\3\13\3\0\1\13\5\0\4\13\1\252\22\13"+
    "\6\0\2\13\2\46\6\0\3\13\3\0\1\13\5\0"+
    "\15\13\1\253\11\13\6\0\2\13\2\46\6\0\3\13"+
    "\3\0\1\13\5\0\21\13\1\254\5\13\6\0\2\13"+
    "\2\46\6\0\3\13\3\0\1\13\5\0\1\13\1\255"+
    "\25\13\6\0\2\13\2\46\6\0\3\13\3\0\1\13"+
    "\1\0\57\215\1\217\7\215\27\216\6\215\4\216\6\215"+
    "\3\216\1\215\1\217\1\215\1\216\1\215\4\0\17\13"+
    "\1\256\7\13\6\0\2\13\2\46\6\0\3\13\3\0"+
    "\1\13\5\0\23\13\1\257\3\13\6\0\2\13\2\46"+
    "\6\0\3\13\3\0\1\13\5\0\21\13\1\260\5\13"+
    "\6\0\2\13\2\46\6\0\3\13\3\0\1\13\5\0"+
    "\10\13\1\261\16\13\6\0\2\13\2\46\6\0\3\13"+
    "\3\0\1\13\5\0\10\13\1\262\16\13\6\0\2\13"+
    "\2\46\6\0\3\13\3\0\1\13\5\0\6\13\1\263"+
    "\20\13\6\0\2\13\2\46\6\0\3\13\3\0\1\13"+
    "\5\0\17\13\1\264\7\13\6\0\2\13\2\46\6\0"+
    "\3\13\3\0\1\13\1\0\44\173\1\265\11\173\1\235"+
    "\4\173\41\0\1\266\16\0\1\266\1\267\5\0\5\13"+
    "\1\270\21\13\6\0\2\13\2\46\6\0\3\13\3\0"+
    "\1\13\5\0\13\13\1\271\13\13\6\0\2\13\2\46"+
    "\6\0\3\13\3\0\1\13\5\0\4\13\1\272\22\13"+
    "\6\0\2\13\2\46\6\0\3\13\3\0\1\13\5\0"+
    "\7\13\1\273\17\13\6\0\2\13\2\46\6\0\3\13"+
    "\3\0\1\13\5\0\16\13\1\274\10\13\6\0\2\13"+
    "\2\46\6\0\3\13\3\0\1\13\5\0\6\13\1\275"+
    "\20\13\6\0\2\13\2\46\6\0\3\13\3\0\1\13"+
    "\5\0\2\13\1\276\24\13\6\0\2\13\2\46\6\0"+
    "\3\13\3\0\1\13\5\0\23\13\1\277\3\13\6\0"+
    "\2\13\2\46\6\0\3\13\3\0\1\13\5\0\4\13"+
    "\1\300\22\13\6\0\2\13\2\46\6\0\3\13\3\0"+
    "\1\13\5\0\13\13\1\301\13\13\6\0\2\13\2\46"+
    "\6\0\3\13\3\0\1\13\5\0\13\13\1\302\13\13"+
    "\6\0\2\13\2\46\6\0\3\13\3\0\1\13\5\0"+
    "\15\13\1\303\11\13\6\0\2\13\2\46\6\0\3\13"+
    "\3\0\1\13\5\0\4\13\1\304\22\13\6\0\2\13"+
    "\2\46\6\0\3\13\3\0\1\13\5\0\4\13\1\305"+
    "\22\13\6\0\2\13\2\46\6\0\3\13\3\0\1\13"+
    "\5\0\3\13\1\306\23\13\6\0\2\13\2\46\6\0"+
    "\3\13\3\0\1\13\5\0\15\13\1\307\11\13\6\0"+
    "\2\13\2\46\6\0\3\13\3\0\1\13\5\0\15\13"+
    "\1\310\11\13\6\0\2\13\2\46\6\0\3\13\3\0"+
    "\1\13\62\0\1\267\5\0\6\13\1\311\20\13\6\0"+
    "\2\13\2\46\6\0\3\13\3\0\1\13\5\0\17\13"+
    "\1\312\7\13\6\0\2\13\2\46\6\0\3\13\3\0"+
    "\1\13\5\0\4\13\1\313\22\13\6\0\2\13\2\46"+
    "\6\0\3\13\3\0\1\13\5\0\7\13\1\314\17\13"+
    "\6\0\2\13\2\46\6\0\3\13\3\0\1\13\5\0"+
    "\4\13\1\315\22\13\6\0\2\13\2\46\6\0\3\13"+
    "\3\0\1\13\5\0\13\13\1\316\13\13\6\0\2\13"+
    "\2\46\6\0\3\13\3\0\1\13\5\0\2\13\1\317"+
    "\24\13\6\0\2\13\2\46\6\0\3\13\3\0\1\13"+
    "\5\0\3\13\1\320\23\13\6\0\2\13\2\46\6\0"+
    "\3\13\3\0\1\13\5\0\4\13\1\321\22\13\6\0"+
    "\2\13\2\46\6\0\3\13\3\0\1\13\5\0\15\13"+
    "\1\322\11\13\6\0\2\13\2\46\6\0\3\13\3\0"+
    "\1\13\5\0\21\13\1\323\5\13\6\0\2\13\2\46"+
    "\6\0\3\13\3\0\1\13\5\0\4\13\1\324\22\13"+
    "\6\0\2\13\2\46\6\0\3\13\3\0\1\13\5\0"+
    "\4\13\1\325\22\13\6\0\2\13\2\46\6\0\3\13"+
    "\3\0\1\13\5\0\16\13\1\326\10\13\6\0\2\13"+
    "\2\46\6\0\3\13\3\0\1\13\5\0\17\13\1\327"+
    "\7\13\6\0\2\13\2\46\6\0\3\13\3\0\1\13"+
    "\5\0\6\13\1\330\20\13\6\0\2\13\2\46\6\0"+
    "\3\13\3\0\1\13\5\0\16\13\1\331\10\13\6\0"+
    "\2\13\2\46\6\0\3\13\3\0\1\13\5\0\6\13"+
    "\1\332\20\13\6\0\2\13\2\46\6\0\3\13\3\0"+
    "\1\13\1\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[8364];
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
    "\3\0\1\11\2\1\1\11\23\1\4\11\1\1\1\11"+
    "\2\1\3\11\5\1\2\11\44\1\1\0\3\1\1\0"+
    "\1\1\1\0\42\1\1\11\2\0\20\1\1\0\1\1"+
    "\1\11\15\1\1\0\1\1\1\0\25\1\1\11\1\0"+
    "\44\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[218];
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
		IElementType token = (end)? null : TaraTypes.NEWLINE;
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
            queue.add(TaraTypes.NEWLINE);
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

    private IElementType inline() {
        blockManager.openBracket(yytext().length());
        storeTokens();
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


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  TaraLexer(java.io.Reader in) {
    this.zzReader = in;
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
    while (i < 2270) {
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
        case 8: 
          { return TaraTypes.LEFT_SQUARE;
          }
        case 63: break;
        case 38: 
          { return TaraTypes.WITH;
          }
        case 64: break;
        case 30: 
          { return TaraTypes.VAR;
          }
        case 65: break;
        case 13: 
          { return TaraTypes.COLON;
          }
        case 66: break;
        case 60: 
          { return TaraTypes.ABSTRACT;
          }
        case 67: break;
        case 43: 
          { return TaraTypes.RATIO_TYPE;
          }
        case 68: break;
        case 19: 
          { yybegin(YYINITIAL); return TaraTypes.QUOTE_END;
          }
        case 69: break;
        case 15: 
          { return TaraTypes.MEASURE_VALUE_KEY;
          }
        case 70: break;
        case 48: 
          { return TaraTypes.SINGLE;
          }
        case 71: break;
        case 33: 
          { return TaraTypes.DOUBLE_VALUE_KEY;
          }
        case 72: break;
        case 2: 
          { return TokenType.WHITE_SPACE;
          }
        case 73: break;
        case 51: 
          { return TaraTypes.NATURAL_TYPE;
          }
        case 74: break;
        case 7: 
          { return TaraTypes.RIGHT_PARENTHESIS;
          }
        case 75: break;
        case 32: 
          { return TaraTypes.LIST;
          }
        case 76: break;
        case 14: 
          { return TaraTypes.EQUALS;
          }
        case 77: break;
        case 45: 
          { return TaraTypes.FINAL;
          }
        case 78: break;
        case 10: 
          { return TaraTypes.DOT;
          }
        case 79: break;
        case 47: 
          { return TaraTypes.STRING_TYPE;
          }
        case 80: break;
        case 59: 
          { return TaraTypes.TERMINAL;
          }
        case 81: break;
        case 46: 
          { return TaraTypes.NATIVE_TYPE;
          }
        case 82: break;
        case 36: 
          { return TaraTypes.DATE_TYPE;
          }
        case 83: break;
        case 5: 
          { return TaraTypes.IDENTIFIER_KEY;
          }
        case 84: break;
        case 42: 
          { return TaraTypes.EMPTY_REF;
          }
        case 85: break;
        case 6: 
          { return TaraTypes.LEFT_PARENTHESIS;
          }
        case 86: break;
        case 61: 
          { return TaraTypes.REQUIRED;
          }
        case 87: break;
        case 53: 
          { return TaraTypes.PRIVATE;
          }
        case 88: break;
        case 57: 
          { return TaraTypes.FEATURE;
          }
        case 89: break;
        case 24: 
          { return TaraTypes.NEGATIVE_VALUE_KEY;
          }
        case 90: break;
        case 31: 
          { return TaraTypes.DSL;
          }
        case 91: break;
        case 4: 
          { return inline();
          }
        case 92: break;
        case 26: 
          { return TaraTypes.ADDRESS_VALUE;
          }
        case 93: break;
        case 52: 
          { return TaraTypes.EXTENDS;
          }
        case 94: break;
        case 23: 
          { yybegin(MULTILINE); return TaraTypes.QUOTE_BEGIN;
          }
        case 95: break;
        case 49: 
          { return TaraTypes.DOUBLE_TYPE;
          }
        case 96: break;
        case 9: 
          { return TaraTypes.RIGHT_SQUARE;
          }
        case 97: break;
        case 17: 
          { return TaraTypes.NATURAL_VALUE_KEY;
          }
        case 98: break;
        case 16: 
          { return semicolon();
          }
        case 99: break;
        case 1: 
          { return TokenType.BAD_CHARACTER;
          }
        case 100: break;
        case 39: 
          { return TaraTypes.INTO;
          }
        case 101: break;
        case 41: 
          { return TaraTypes.RESOURCE_KEY;
          }
        case 102: break;
        case 35: 
          { yypushback(1); return TaraTypes.DOC_LINE;
          }
        case 103: break;
        case 34: 
          { return TaraTypes.BOOLEAN_VALUE_KEY;
          }
        case 104: break;
        case 21: 
          { return TaraTypes.AS;
          }
        case 105: break;
        case 20: 
          { return TaraTypes.ON;
          }
        case 106: break;
        case 27: 
          { return TaraTypes.SUB;
          }
        case 107: break;
        case 58: 
          { return TaraTypes.ENCLOSED;
          }
        case 108: break;
        case 11: 
          { yybegin(QUOTED); return TaraTypes.QUOTE_BEGIN;
          }
        case 109: break;
        case 54: 
          { return TaraTypes.BOOLEAN_TYPE;
          }
        case 110: break;
        case 22: 
          { return TaraTypes.IS;
          }
        case 111: break;
        case 56: 
          { return TaraTypes.MEASURE_TYPE_KEY;
          }
        case 112: break;
        case 28: 
          { return TaraTypes.USE;
          }
        case 113: break;
        case 62: 
          { return TaraTypes.IMPLICIT;
          }
        case 114: break;
        case 55: 
          { return TaraTypes.INT_TYPE;
          }
        case 115: break;
        case 50: 
          { return TaraTypes.METAIDENTIFIER_KEY;
          }
        case 116: break;
        case 40: 
          { return TaraTypes.MAIN;
          }
        case 117: break;
        case 37: 
          { return TaraTypes.WORD_KEY;
          }
        case 118: break;
        case 18: 
          { return TaraTypes.CHARACTER;
          }
        case 119: break;
        case 44: 
          { return TaraTypes.FACET;
          }
        case 120: break;
        case 12: 
          { return TaraTypes.COMMA;
          }
        case 121: break;
        case 29: 
          { return TaraTypes.HAS;
          }
        case 122: break;
        case 3: 
          { return newlineIndent();
          }
        case 123: break;
        case 25: 
          { 
          }
        case 124: break;
        default:
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            switch (zzLexicalState) {
            case YYINITIAL: {
              return eof();
            }
            case 219: break;
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
