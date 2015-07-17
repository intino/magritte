/* The following code was generated by JFlex 1.4.3 on 10/07/15 11:37 */

package tara.intellij.lang.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import tara.intellij.lang.psi.TaraTypes;
import com.intellij.psi.TokenType;
import java.util.LinkedList;
import java.util.Queue;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 10/07/15 11:37 from the specification file
 * <tt>/Users/oroncal/workspace/tara/ide/intellij/src/siani/tara/intellij/lang/lexer/Tara.flex</tt>
 */
class TaraLexer implements FlexLexer {
  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int EXPRESSION = 6;
  public static final int QUOTED = 2;
  public static final int YYINITIAL = 0;
  public static final int EXPRESSION_MULTILINE = 8;
  public static final int MULTILINE = 4;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0,  0,  1,  1,  2,  2,  3,  3,  4, 4
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\56\1\1\1\57\2\0\1\2\16\56\4\0\1\1\1\60\1\41"+
    "\1\54\1\44\1\52\1\0\1\42\1\34\1\35\1\0\1\61\1\47"+
    "\1\43\1\40\1\46\12\62\1\50\1\53\1\0\1\51\1\3\2\0"+
    "\2\44\1\4\1\44\1\55\25\44\1\36\1\63\1\37\1\0\1\44"+
    "\1\0\1\17\1\15\1\7\1\22\1\10\1\33\1\30\1\16\1\25"+
    "\2\44\1\23\1\31\1\6\1\5\1\11\1\32\1\21\1\13\1\12"+
    "\1\14\1\20\1\24\1\27\1\26\1\44\1\0\1\2\2\0\41\56"+
    "\2\0\4\44\4\0\1\44\2\0\1\56\2\0\1\52\4\0\1\44"+
    "\1\0\1\45\2\0\1\44\5\0\27\44\1\0\37\44\1\0\u01ca\44"+
    "\4\0\14\44\16\0\5\44\7\0\1\44\1\0\1\44\21\0\160\56"+
    "\5\44\1\0\2\44\2\0\4\44\10\0\1\44\1\0\3\44\1\0"+
    "\1\44\1\0\24\44\1\0\123\44\1\0\213\44\1\0\5\56\2\0"+
    "\236\44\11\0\46\44\2\0\1\44\7\0\47\44\7\0\1\44\1\0"+
    "\55\56\1\0\1\56\1\0\2\56\1\0\2\56\1\0\1\56\10\0"+
    "\33\44\5\0\3\44\15\0\5\56\6\0\1\44\4\0\13\56\5\0"+
    "\53\44\25\56\12\62\4\0\2\44\1\56\143\44\1\0\1\44\10\56"+
    "\1\0\6\56\2\44\2\56\1\0\4\56\2\44\12\62\3\44\2\0"+
    "\1\44\17\0\1\56\1\44\1\56\36\44\33\56\2\0\131\44\13\56"+
    "\1\44\16\0\12\62\41\44\11\56\2\44\4\0\1\44\5\0\26\44"+
    "\4\56\1\44\11\56\1\44\3\56\1\44\5\56\22\0\31\44\3\56"+
    "\104\0\1\44\1\0\13\44\67\0\33\56\1\0\4\56\66\44\3\56"+
    "\1\44\22\56\1\44\7\56\12\44\2\56\2\0\12\62\1\0\7\44"+
    "\1\0\7\44\1\0\3\56\1\0\10\44\2\0\2\44\2\0\26\44"+
    "\1\0\7\44\1\0\1\44\3\0\4\44\2\0\1\56\1\44\7\56"+
    "\2\0\2\56\2\0\3\56\1\44\10\0\1\56\4\0\2\44\1\0"+
    "\3\44\2\56\2\0\12\62\4\44\7\0\1\44\5\0\3\56\1\0"+
    "\6\44\4\0\2\44\2\0\26\44\1\0\7\44\1\0\2\44\1\0"+
    "\2\44\1\0\2\44\2\0\1\56\1\0\5\56\4\0\2\56\2\0"+
    "\3\56\3\0\1\56\7\0\4\44\1\0\1\44\7\0\12\62\2\56"+
    "\3\44\1\56\13\0\3\56\1\0\11\44\1\0\3\44\1\0\26\44"+
    "\1\0\7\44\1\0\2\44\1\0\5\44\2\0\1\56\1\44\10\56"+
    "\1\0\3\56\1\0\3\56\2\0\1\44\17\0\2\44\2\56\2\0"+
    "\12\62\1\0\1\44\17\0\3\56\1\0\10\44\2\0\2\44\2\0"+
    "\26\44\1\0\7\44\1\0\2\44\1\0\5\44\2\0\1\56\1\44"+
    "\7\56\2\0\2\56\2\0\3\56\10\0\2\56\4\0\2\44\1\0"+
    "\3\44\2\56\2\0\12\62\1\0\1\44\20\0\1\56\1\44\1\0"+
    "\6\44\3\0\3\44\1\0\4\44\3\0\2\44\1\0\1\44\1\0"+
    "\2\44\3\0\2\44\3\0\3\44\3\0\14\44\4\0\5\56\3\0"+
    "\3\56\1\0\4\56\2\0\1\44\6\0\1\56\16\0\12\62\11\0"+
    "\1\44\7\0\3\56\1\0\10\44\1\0\3\44\1\0\27\44\1\0"+
    "\12\44\1\0\5\44\3\0\1\44\7\56\1\0\3\56\1\0\4\56"+
    "\7\0\2\56\1\0\2\44\6\0\2\44\2\56\2\0\12\62\22\0"+
    "\2\56\1\0\10\44\1\0\3\44\1\0\27\44\1\0\12\44\1\0"+
    "\5\44\2\0\1\56\1\44\7\56\1\0\3\56\1\0\4\56\7\0"+
    "\2\56\7\0\1\44\1\0\2\44\2\56\2\0\12\62\1\0\2\44"+
    "\17\0\2\56\1\0\10\44\1\0\3\44\1\0\51\44\2\0\1\44"+
    "\7\56\1\0\3\56\1\0\4\56\1\44\10\0\1\56\10\0\2\44"+
    "\2\56\2\0\12\62\12\0\6\44\2\0\2\56\1\0\22\44\3\0"+
    "\30\44\1\0\11\44\1\0\1\44\2\0\7\44\3\0\1\56\4\0"+
    "\6\56\1\0\1\56\1\0\10\56\22\0\2\56\15\0\60\44\1\56"+
    "\2\44\7\56\4\0\10\44\10\56\1\0\12\62\47\0\2\44\1\0"+
    "\1\44\2\0\2\44\1\0\1\44\2\0\1\44\6\0\4\44\1\0"+
    "\7\44\1\0\3\44\1\0\1\44\1\0\1\44\2\0\2\44\1\0"+
    "\4\44\1\56\2\44\6\56\1\0\2\56\1\44\2\0\5\44\1\0"+
    "\1\44\1\0\6\56\2\0\12\62\2\0\4\44\40\0\1\44\27\0"+
    "\2\56\6\0\12\62\13\0\1\56\1\0\1\56\1\0\1\56\4\0"+
    "\2\56\10\44\1\0\44\44\4\0\24\56\1\0\2\56\5\44\13\56"+
    "\1\0\44\56\11\0\1\56\71\0\53\44\24\56\1\44\12\62\6\0"+
    "\6\44\4\56\4\44\3\56\1\44\3\56\2\44\7\56\3\44\4\56"+
    "\15\44\14\56\1\44\1\56\12\62\4\56\2\0\46\44\1\0\1\44"+
    "\5\0\1\44\2\0\53\44\1\0\u014d\44\1\0\4\44\2\0\7\44"+
    "\1\0\1\44\1\0\4\44\2\0\51\44\1\0\4\44\2\0\41\44"+
    "\1\0\4\44\2\0\7\44\1\0\1\44\1\0\4\44\2\0\17\44"+
    "\1\0\71\44\1\0\4\44\2\0\103\44\2\0\3\56\40\0\20\44"+
    "\20\0\125\44\14\0\u026c\44\2\0\21\44\1\0\32\44\5\0\113\44"+
    "\3\0\3\44\17\0\15\44\1\0\4\44\3\56\13\0\22\44\3\56"+
    "\13\0\22\44\2\56\14\0\15\44\1\0\3\44\1\0\2\56\14\0"+
    "\64\44\40\56\3\0\1\44\3\0\2\44\1\56\2\0\12\62\41\0"+
    "\3\56\2\0\12\62\6\0\130\44\10\0\51\44\1\56\1\44\5\0"+
    "\106\44\12\0\35\44\3\0\14\56\4\0\14\56\12\0\12\62\36\44"+
    "\2\0\5\44\13\0\54\44\4\0\21\56\7\44\2\56\6\0\12\62"+
    "\46\0\27\44\5\56\4\0\65\44\12\56\1\0\35\56\2\0\1\56"+
    "\12\62\6\0\12\62\15\0\1\44\130\0\5\56\57\44\21\56\7\44"+
    "\4\0\12\62\21\0\11\56\14\0\3\56\36\44\15\56\2\44\12\62"+
    "\54\44\16\56\14\0\44\44\24\56\10\0\12\62\3\0\3\44\12\62"+
    "\44\44\122\0\3\56\1\0\25\56\4\44\1\56\4\44\3\56\2\44"+
    "\11\0\300\44\47\56\25\0\4\56\u0116\44\2\0\6\44\2\0\46\44"+
    "\2\0\6\44\2\0\10\44\1\0\1\44\1\0\1\44\1\0\1\44"+
    "\1\0\37\44\2\0\65\44\1\0\7\44\1\0\1\44\3\0\3\44"+
    "\1\0\7\44\3\0\4\44\2\0\6\44\4\0\15\44\5\0\3\44"+
    "\1\0\7\44\16\0\5\56\32\0\5\56\20\0\2\44\23\0\1\44"+
    "\13\0\5\56\5\0\6\56\1\0\1\44\15\0\1\44\20\0\15\44"+
    "\3\0\14\44\1\44\16\44\25\0\15\56\4\0\1\56\3\0\14\56"+
    "\21\0\1\44\4\0\1\44\2\0\12\44\1\0\1\44\3\0\5\44"+
    "\6\0\1\44\1\0\1\44\1\0\1\44\1\0\4\44\1\0\13\44"+
    "\2\0\4\44\5\0\5\44\4\0\1\44\21\0\51\44\u0a77\0\57\44"+
    "\1\0\57\44\1\0\205\44\6\0\4\44\3\56\2\44\14\0\46\44"+
    "\1\0\1\44\5\0\1\44\2\0\70\44\7\0\1\44\17\0\1\56"+
    "\27\44\11\0\7\44\1\0\7\44\1\0\7\44\1\0\7\44\1\0"+
    "\7\44\1\0\7\44\1\0\7\44\1\0\7\44\1\0\40\56\57\0"+
    "\1\44\u01d5\0\3\44\31\0\11\44\6\56\1\0\5\44\2\0\5\44"+
    "\4\0\126\44\2\0\2\56\2\0\3\44\1\0\132\44\1\0\4\44"+
    "\5\0\51\44\3\0\136\44\21\0\33\44\65\0\20\44\u0200\0\u19b6\44"+
    "\112\0\u51cd\44\63\0\u048d\44\103\0\56\44\2\0\u010d\44\3\0\20\44"+
    "\12\62\2\44\24\0\57\44\1\56\4\0\12\56\1\0\31\44\7\0"+
    "\1\56\120\44\2\56\45\0\11\44\2\0\147\44\2\0\4\44\1\0"+
    "\4\44\14\0\13\44\115\0\12\44\1\56\3\44\1\56\4\44\1\56"+
    "\27\44\5\56\20\0\1\44\7\0\64\44\14\0\2\56\62\44\21\56"+
    "\13\0\12\62\6\0\22\56\6\44\3\0\1\44\4\0\12\62\34\44"+
    "\10\56\2\0\27\44\15\56\14\0\35\44\3\0\4\56\57\44\16\56"+
    "\16\0\1\44\12\62\46\0\51\44\16\56\11\0\3\44\1\56\10\44"+
    "\2\56\2\0\12\62\6\0\27\44\3\0\1\44\1\56\4\0\60\44"+
    "\1\56\1\44\3\56\2\44\2\56\5\44\2\56\1\44\1\56\1\44"+
    "\30\0\3\44\2\0\13\44\5\56\2\0\3\44\2\56\12\0\6\44"+
    "\2\0\6\44\2\0\6\44\11\0\7\44\1\0\7\44\221\0\43\44"+
    "\10\56\1\0\2\56\2\0\12\62\6\0\u2ba4\44\14\0\27\44\4\0"+
    "\61\44\u2104\0\u016e\44\2\0\152\44\46\0\7\44\14\0\5\44\5\0"+
    "\1\44\1\56\12\44\1\0\15\44\1\0\5\44\1\0\1\44\1\0"+
    "\2\44\1\0\2\44\1\0\154\44\41\0\u016b\44\22\0\100\44\2\0"+
    "\66\44\50\0\15\44\3\0\20\56\20\0\7\56\14\0\2\44\30\0"+
    "\3\44\31\0\1\44\6\0\5\44\1\0\207\44\2\0\1\56\4\0"+
    "\1\44\13\0\12\62\7\0\32\44\4\0\1\44\1\0\32\44\13\0"+
    "\131\44\3\0\6\44\2\0\6\44\2\0\6\44\2\0\3\44\3\0"+
    "\2\44\3\0\2\44\22\0\3\56\4\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\5\0\1\1\1\2\1\3\1\4\23\5\1\6\1\7"+
    "\1\10\1\11\1\12\1\13\1\14\2\1\1\15\1\16"+
    "\1\17\1\20\1\21\3\1\1\22\1\23\1\24\2\23"+
    "\1\25\3\23\1\3\1\0\1\5\1\26\16\5\1\27"+
    "\12\5\1\30\5\5\1\0\1\31\1\32\1\33\1\34"+
    "\1\35\2\0\1\24\1\25\12\5\1\36\1\5\1\37"+
    "\1\5\1\40\1\41\1\5\1\42\3\5\1\43\13\5"+
    "\1\44\1\45\1\46\11\5\1\47\7\5\1\50\1\51"+
    "\1\52\1\53\2\5\1\54\3\5\1\55\1\0\5\5"+
    "\1\56\10\5\1\57\4\5\1\60\1\61\1\0\1\46"+
    "\2\5\1\62\5\5\1\63\1\64\3\5\1\65\3\5"+
    "\1\66\1\67\1\5\1\70\1\5\1\71\1\5\1\72"+
    "\2\5\1\73\1\74\1\75\1\76\1\5\1\77\1\100"+
    "\1\101\1\102";

  private static int [] zzUnpackAction() {
    int [] result = new int[223];
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
    "\0\0\0\64\0\150\0\234\0\320\0\u0104\0\u0138\0\u016c"+
    "\0\u0104\0\u01a0\0\u01d4\0\u0208\0\u023c\0\u0270\0\u02a4\0\u02d8"+
    "\0\u030c\0\u0340\0\u0374\0\u03a8\0\u03dc\0\u0410\0\u0444\0\u0478"+
    "\0\u04ac\0\u04e0\0\u0514\0\u0548\0\u0104\0\u0104\0\u0104\0\u0104"+
    "\0\u057c\0\u0104\0\u0104\0\u05b0\0\u05e4\0\u0104\0\u0104\0\u0618"+
    "\0\u064c\0\u0680\0\u06b4\0\u06e8\0\u071c\0\u0750\0\u0104\0\u0104"+
    "\0\u0784\0\u07b8\0\u0104\0\u07ec\0\u0820\0\u0854\0\u0888\0\u05e4"+
    "\0\u08bc\0\u023c\0\u08f0\0\u0924\0\u0958\0\u098c\0\u09c0\0\u09f4"+
    "\0\u0a28\0\u0a5c\0\u0a90\0\u0ac4\0\u0af8\0\u0b2c\0\u0b60\0\u0b94"+
    "\0\u023c\0\u0bc8\0\u0bfc\0\u0c30\0\u0c64\0\u0c98\0\u0ccc\0\u0d00"+
    "\0\u0d34\0\u0d68\0\u0d9c\0\u023c\0\u0dd0\0\u0e04\0\u0e38\0\u0e6c"+
    "\0\u0ea0\0\u0ed4\0\u0f08\0\u0f3c\0\u0f70\0\u0618\0\u06b4\0\u0fa4"+
    "\0\u0fd8\0\u07b8\0\u0820\0\u100c\0\u1040\0\u1074\0\u10a8\0\u10dc"+
    "\0\u1110\0\u1144\0\u1178\0\u11ac\0\u11e0\0\u023c\0\u1214\0\u023c"+
    "\0\u1248\0\u023c\0\u023c\0\u127c\0\u023c\0\u12b0\0\u12e4\0\u1318"+
    "\0\u023c\0\u134c\0\u1380\0\u13b4\0\u13e8\0\u141c\0\u1450\0\u1484"+
    "\0\u14b8\0\u14ec\0\u1520\0\u1554\0\u0104\0\u0104\0\u1588\0\u15bc"+
    "\0\u15f0\0\u1624\0\u1658\0\u168c\0\u16c0\0\u16f4\0\u1728\0\u175c"+
    "\0\u023c\0\u1790\0\u17c4\0\u17f8\0\u182c\0\u1860\0\u1894\0\u18c8"+
    "\0\u023c\0\u023c\0\u023c\0\u023c\0\u18fc\0\u1930\0\u023c\0\u1964"+
    "\0\u1998\0\u19cc\0\u023c\0\u1a00\0\u1a34\0\u1a68\0\u1a9c\0\u1ad0"+
    "\0\u1b04\0\u023c\0\u1b38\0\u1b6c\0\u1ba0\0\u1bd4\0\u1c08\0\u1c3c"+
    "\0\u1c70\0\u1ca4\0\u023c\0\u1cd8\0\u1d0c\0\u1d40\0\u1d74\0\u023c"+
    "\0\u023c\0\u1da8\0\u1da8\0\u1ddc\0\u1e10\0\u023c\0\u1e44\0\u1e78"+
    "\0\u1eac\0\u1ee0\0\u1f14\0\u023c\0\u023c\0\u1f48\0\u1f7c\0\u1fb0"+
    "\0\u023c\0\u1fe4\0\u2018\0\u204c\0\u023c\0\u023c\0\u2080\0\u023c"+
    "\0\u20b4\0\u023c\0\u20e8\0\u023c\0\u211c\0\u2150\0\u023c\0\u023c"+
    "\0\u023c\0\u023c\0\u2184\0\u023c\0\u023c\0\u023c\0\u023c";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[223];
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
    "\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1\15"+
    "\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25"+
    "\1\26\1\27\1\30\1\15\1\31\1\32\3\15\1\33"+
    "\1\15\1\34\1\35\1\36\1\37\1\40\1\41\1\42"+
    "\1\43\1\44\1\15\1\6\1\45\1\46\1\47\1\50"+
    "\1\51\1\52\1\53\1\15\1\6\1\10\1\54\1\55"+
    "\1\56\1\6\41\57\1\60\21\57\1\61\51\57\1\62"+
    "\11\57\1\61\42\57\1\63\20\57\1\64\43\57\1\65"+
    "\17\57\1\66\65\0\1\7\63\0\1\67\1\10\43\0"+
    "\1\70\10\0\1\10\10\0\1\15\1\71\26\15\7\0"+
    "\2\15\2\51\6\0\2\15\3\0\1\15\5\0\2\15"+
    "\1\72\25\15\7\0\2\15\2\51\6\0\2\15\3\0"+
    "\1\15\5\0\13\15\1\73\14\15\7\0\2\15\2\51"+
    "\6\0\2\15\3\0\1\15\5\0\30\15\7\0\2\15"+
    "\2\51\6\0\2\15\3\0\1\15\5\0\2\15\1\74"+
    "\20\15\1\75\1\15\1\76\2\15\7\0\2\15\2\51"+
    "\6\0\2\15\3\0\1\15\5\0\15\15\1\77\12\15"+
    "\7\0\2\15\2\51\6\0\2\15\3\0\1\15\5\0"+
    "\4\15\1\100\10\15\1\101\12\15\7\0\2\15\2\51"+
    "\6\0\2\15\3\0\1\15\5\0\6\15\1\102\1\15"+
    "\1\103\10\15\1\104\6\15\7\0\2\15\2\51\6\0"+
    "\2\15\3\0\1\15\5\0\7\15\1\105\20\15\7\0"+
    "\2\15\2\51\6\0\2\15\3\0\1\15\5\0\1\15"+
    "\1\106\26\15\7\0\2\15\2\51\6\0\2\15\3\0"+
    "\1\15\5\0\13\15\1\107\14\15\7\0\2\15\2\51"+
    "\6\0\2\15\3\0\1\15\5\0\2\15\1\110\4\15"+
    "\1\111\1\15\1\112\16\15\7\0\2\15\2\51\6\0"+
    "\2\15\3\0\1\15\5\0\13\15\1\113\14\15\7\0"+
    "\2\15\2\51\6\0\2\15\3\0\1\15\5\0\4\15"+
    "\1\114\6\15\1\115\14\15\7\0\2\15\2\51\6\0"+
    "\2\15\3\0\1\15\5\0\1\15\1\116\5\15\1\117"+
    "\3\15\1\120\14\15\7\0\2\15\2\51\6\0\2\15"+
    "\3\0\1\15\5\0\1\15\1\121\17\15\1\122\6\15"+
    "\7\0\2\15\2\51\6\0\2\15\3\0\1\15\5\0"+
    "\2\15\1\123\4\15\1\124\20\15\7\0\2\15\2\51"+
    "\6\0\2\15\3\0\1\15\5\0\4\15\1\125\6\15"+
    "\1\126\14\15\7\0\2\15\2\51\6\0\2\15\3\0"+
    "\1\15\5\0\4\15\1\127\6\15\1\130\5\15\1\131"+
    "\6\15\7\0\2\15\2\51\6\0\2\15\3\0\1\15"+
    "\41\0\1\132\66\0\1\133\16\0\1\134\47\0\1\135"+
    "\66\0\1\136\16\0\30\51\7\0\4\51\6\0\2\51"+
    "\3\0\1\51\54\0\1\52\14\0\30\137\10\0\1\137"+
    "\10\0\1\137\66\0\1\140\65\0\1\56\41\0\1\141"+
    "\21\0\1\56\7\0\1\57\3\0\1\57\6\0\1\57"+
    "\17\0\1\57\73\0\1\142\20\0\1\57\3\0\1\57"+
    "\6\0\1\57\20\0\1\57\64\0\1\143\26\0\1\57"+
    "\3\0\1\57\6\0\1\57\43\0\1\67\44\0\1\70"+
    "\21\0\2\15\1\144\25\15\7\0\2\15\2\51\6\0"+
    "\2\15\3\0\1\15\5\0\6\15\1\145\21\15\7\0"+
    "\2\15\2\51\6\0\2\15\3\0\1\15\5\0\3\15"+
    "\1\146\24\15\7\0\2\15\2\51\6\0\2\15\3\0"+
    "\1\15\5\0\6\15\1\147\21\15\7\0\2\15\2\51"+
    "\6\0\2\15\3\0\1\15\5\0\5\15\1\150\22\15"+
    "\7\0\2\15\2\51\6\0\2\15\3\0\1\15\5\0"+
    "\1\15\1\151\17\15\1\152\6\15\7\0\2\15\2\51"+
    "\6\0\2\15\3\0\1\15\5\0\15\15\1\153\12\15"+
    "\7\0\2\15\2\51\6\0\2\15\3\0\1\15\5\0"+
    "\10\15\1\154\17\15\7\0\2\15\2\51\6\0\2\15"+
    "\3\0\1\15\5\0\15\15\1\155\12\15\7\0\2\15"+
    "\2\51\6\0\2\15\3\0\1\15\5\0\11\15\1\156"+
    "\16\15\7\0\2\15\2\51\6\0\2\15\3\0\1\15"+
    "\5\0\2\15\1\157\25\15\7\0\2\15\2\51\6\0"+
    "\2\15\3\0\1\15\5\0\4\15\1\160\23\15\7\0"+
    "\2\15\2\51\6\0\2\15\3\0\1\15\5\0\1\15"+
    "\1\161\26\15\7\0\2\15\2\51\6\0\2\15\3\0"+
    "\1\15\5\0\7\15\1\162\20\15\7\0\2\15\2\51"+
    "\6\0\2\15\3\0\1\15\5\0\22\15\1\163\5\15"+
    "\7\0\2\15\2\51\6\0\2\15\3\0\1\15\5\0"+
    "\7\15\1\164\20\15\7\0\2\15\2\51\6\0\2\15"+
    "\3\0\1\15\5\0\15\15\1\165\12\15\7\0\2\15"+
    "\2\51\6\0\2\15\3\0\1\15\5\0\26\15\1\166"+
    "\1\15\7\0\2\15\2\51\6\0\2\15\3\0\1\15"+
    "\5\0\6\15\1\167\21\15\7\0\2\15\2\51\6\0"+
    "\2\15\3\0\1\15\5\0\10\15\1\170\17\15\7\0"+
    "\2\15\2\51\6\0\2\15\3\0\1\15\5\0\17\15"+
    "\1\171\10\15\7\0\2\15\2\51\6\0\2\15\3\0"+
    "\1\15\5\0\6\15\1\172\21\15\7\0\2\15\2\51"+
    "\6\0\2\15\3\0\1\15\5\0\15\15\1\173\12\15"+
    "\7\0\2\15\2\51\6\0\2\15\3\0\1\15\5\0"+
    "\6\15\1\174\21\15\7\0\2\15\2\51\6\0\2\15"+
    "\3\0\1\15\5\0\6\15\1\175\21\15\7\0\2\15"+
    "\2\51\6\0\2\15\3\0\1\15\5\0\13\15\1\176"+
    "\14\15\7\0\2\15\2\51\6\0\2\15\3\0\1\15"+
    "\5\0\21\15\1\177\6\15\7\0\2\15\2\51\6\0"+
    "\2\15\3\0\1\15\5\0\13\15\1\200\14\15\7\0"+
    "\2\15\2\51\6\0\2\15\3\0\1\15\5\0\3\15"+
    "\1\201\13\15\1\202\10\15\7\0\2\15\2\51\6\0"+
    "\2\15\3\0\1\15\5\0\2\15\1\203\14\15\1\204"+
    "\10\15\7\0\2\15\2\51\6\0\2\15\3\0\1\15"+
    "\41\0\1\205\66\0\1\133\60\0\1\141\21\0\1\134"+
    "\1\0\57\135\1\0\4\135\57\140\1\206\4\140\62\0"+
    "\1\207\5\0\3\15\1\210\24\15\7\0\2\15\2\51"+
    "\6\0\2\15\3\0\1\15\5\0\10\15\1\211\10\15"+
    "\1\212\6\15\7\0\2\15\2\51\6\0\2\15\3\0"+
    "\1\15\5\0\17\15\1\213\10\15\7\0\2\15\2\51"+
    "\6\0\2\15\3\0\1\15\5\0\4\15\1\214\23\15"+
    "\7\0\2\15\2\51\6\0\2\15\3\0\1\15\5\0"+
    "\6\15\1\215\21\15\7\0\2\15\2\51\6\0\2\15"+
    "\3\0\1\15\5\0\6\15\1\216\21\15\7\0\2\15"+
    "\2\51\6\0\2\15\3\0\1\15\5\0\14\15\1\217"+
    "\13\15\7\0\2\15\2\51\6\0\2\15\3\0\1\15"+
    "\5\0\25\15\1\220\2\15\7\0\2\15\2\51\6\0"+
    "\2\15\3\0\1\15\5\0\4\15\1\221\23\15\7\0"+
    "\2\15\2\51\6\0\2\15\3\0\1\15\5\0\21\15"+
    "\1\222\6\15\7\0\2\15\2\51\6\0\2\15\3\0"+
    "\1\15\5\0\24\15\1\223\3\15\7\0\2\15\2\51"+
    "\6\0\2\15\3\0\1\15\5\0\17\15\1\224\10\15"+
    "\7\0\2\15\2\51\6\0\2\15\3\0\1\15\5\0"+
    "\6\15\1\225\21\15\7\0\2\15\2\51\6\0\2\15"+
    "\3\0\1\15\5\0\10\15\1\226\17\15\7\0\2\15"+
    "\2\51\6\0\2\15\3\0\1\15\5\0\21\15\1\227"+
    "\6\15\7\0\2\15\2\51\6\0\2\15\3\0\1\15"+
    "\5\0\11\15\1\230\16\15\7\0\2\15\2\51\6\0"+
    "\2\15\3\0\1\15\5\0\4\15\1\231\23\15\7\0"+
    "\2\15\2\51\6\0\2\15\3\0\1\15\5\0\16\15"+
    "\1\232\11\15\7\0\2\15\2\51\6\0\2\15\3\0"+
    "\1\15\5\0\12\15\1\233\15\15\7\0\2\15\2\51"+
    "\6\0\2\15\3\0\1\15\5\0\1\15\1\234\2\15"+
    "\1\235\23\15\7\0\2\15\2\51\6\0\2\15\3\0"+
    "\1\15\5\0\7\15\1\236\20\15\7\0\2\15\2\51"+
    "\6\0\2\15\3\0\1\15\5\0\2\15\1\237\25\15"+
    "\7\0\2\15\2\51\6\0\2\15\3\0\1\15\5\0"+
    "\6\15\1\240\21\15\7\0\2\15\2\51\6\0\2\15"+
    "\3\0\1\15\5\0\4\15\1\241\23\15\7\0\2\15"+
    "\2\51\6\0\2\15\3\0\1\15\5\0\7\15\1\154"+
    "\20\15\7\0\2\15\2\51\6\0\2\15\3\0\1\15"+
    "\5\0\13\15\1\242\14\15\7\0\2\15\2\51\6\0"+
    "\2\15\3\0\1\15\5\0\4\15\1\243\23\15\7\0"+
    "\2\15\2\51\6\0\2\15\3\0\1\15\56\0\1\244"+
    "\4\0\1\207\5\0\4\15\1\245\23\15\7\0\2\15"+
    "\2\51\6\0\2\15\3\0\1\15\5\0\15\15\1\246"+
    "\12\15\7\0\2\15\2\51\6\0\2\15\3\0\1\15"+
    "\5\0\14\15\1\247\13\15\7\0\2\15\2\51\6\0"+
    "\2\15\3\0\1\15\5\0\1\15\1\250\26\15\7\0"+
    "\2\15\2\51\6\0\2\15\3\0\1\15\5\0\2\15"+
    "\1\251\25\15\7\0\2\15\2\51\6\0\2\15\3\0"+
    "\1\15\5\0\22\15\1\252\5\15\7\0\2\15\2\51"+
    "\6\0\2\15\3\0\1\15\5\0\1\15\1\253\26\15"+
    "\7\0\2\15\2\51\6\0\2\15\3\0\1\15\5\0"+
    "\13\15\1\254\14\15\7\0\2\15\2\51\6\0\2\15"+
    "\3\0\1\15\5\0\21\15\1\255\6\15\7\0\2\15"+
    "\2\51\6\0\2\15\3\0\1\15\5\0\2\15\1\256"+
    "\25\15\7\0\2\15\2\51\6\0\2\15\3\0\1\15"+
    "\5\0\17\15\1\257\10\15\7\0\2\15\2\51\6\0"+
    "\2\15\3\0\1\15\5\0\4\15\1\260\23\15\7\0"+
    "\2\15\2\51\6\0\2\15\3\0\1\15\5\0\15\15"+
    "\1\261\12\15\7\0\2\15\2\51\6\0\2\15\3\0"+
    "\1\15\5\0\21\15\1\262\6\15\7\0\2\15\2\51"+
    "\6\0\2\15\3\0\1\15\5\0\1\15\1\263\26\15"+
    "\7\0\2\15\2\51\6\0\2\15\3\0\1\15\5\0"+
    "\17\15\1\264\10\15\7\0\2\15\2\51\6\0\2\15"+
    "\3\0\1\15\5\0\24\15\1\265\3\15\7\0\2\15"+
    "\2\51\6\0\2\15\3\0\1\15\5\0\10\15\1\266"+
    "\17\15\7\0\2\15\2\51\6\0\2\15\3\0\1\15"+
    "\5\0\10\15\1\267\17\15\7\0\2\15\2\51\6\0"+
    "\2\15\3\0\1\15\5\0\6\15\1\270\21\15\7\0"+
    "\2\15\2\51\6\0\2\15\3\0\1\15\5\0\17\15"+
    "\1\271\10\15\7\0\2\15\2\51\6\0\2\15\3\0"+
    "\1\15\44\0\1\272\15\0\1\272\1\273\5\0\5\15"+
    "\1\274\22\15\7\0\2\15\2\51\6\0\2\15\3\0"+
    "\1\15\5\0\13\15\1\275\14\15\7\0\2\15\2\51"+
    "\6\0\2\15\3\0\1\15\5\0\4\15\1\276\23\15"+
    "\7\0\2\15\2\51\6\0\2\15\3\0\1\15\5\0"+
    "\7\15\1\277\20\15\7\0\2\15\2\51\6\0\2\15"+
    "\3\0\1\15\5\0\16\15\1\300\11\15\7\0\2\15"+
    "\2\51\6\0\2\15\3\0\1\15\5\0\6\15\1\301"+
    "\21\15\7\0\2\15\2\51\6\0\2\15\3\0\1\15"+
    "\5\0\6\15\1\302\21\15\7\0\2\15\2\51\6\0"+
    "\2\15\3\0\1\15\5\0\2\15\1\303\25\15\7\0"+
    "\2\15\2\51\6\0\2\15\3\0\1\15\5\0\24\15"+
    "\1\304\3\15\7\0\2\15\2\51\6\0\2\15\3\0"+
    "\1\15\5\0\4\15\1\305\23\15\7\0\2\15\2\51"+
    "\6\0\2\15\3\0\1\15\5\0\13\15\1\306\14\15"+
    "\7\0\2\15\2\51\6\0\2\15\3\0\1\15\5\0"+
    "\13\15\1\307\14\15\7\0\2\15\2\51\6\0\2\15"+
    "\3\0\1\15\5\0\15\15\1\310\12\15\7\0\2\15"+
    "\2\51\6\0\2\15\3\0\1\15\5\0\4\15\1\311"+
    "\23\15\7\0\2\15\2\51\6\0\2\15\3\0\1\15"+
    "\5\0\4\15\1\312\23\15\7\0\2\15\2\51\6\0"+
    "\2\15\3\0\1\15\5\0\15\15\1\313\12\15\7\0"+
    "\2\15\2\51\6\0\2\15\3\0\1\15\5\0\15\15"+
    "\1\314\12\15\7\0\2\15\2\51\6\0\2\15\3\0"+
    "\1\15\63\0\1\273\5\0\6\15\1\315\21\15\7\0"+
    "\2\15\2\51\6\0\2\15\3\0\1\15\5\0\17\15"+
    "\1\316\10\15\7\0\2\15\2\51\6\0\2\15\3\0"+
    "\1\15\5\0\4\15\1\317\23\15\7\0\2\15\2\51"+
    "\6\0\2\15\3\0\1\15\5\0\7\15\1\320\20\15"+
    "\7\0\2\15\2\51\6\0\2\15\3\0\1\15\5\0"+
    "\22\15\1\321\5\15\7\0\2\15\2\51\6\0\2\15"+
    "\3\0\1\15\5\0\4\15\1\322\23\15\7\0\2\15"+
    "\2\51\6\0\2\15\3\0\1\15\5\0\13\15\1\323"+
    "\14\15\7\0\2\15\2\51\6\0\2\15\3\0\1\15"+
    "\5\0\2\15\1\324\25\15\7\0\2\15\2\51\6\0"+
    "\2\15\3\0\1\15\5\0\3\15\1\325\24\15\7\0"+
    "\2\15\2\51\6\0\2\15\3\0\1\15\5\0\4\15"+
    "\1\326\23\15\7\0\2\15\2\51\6\0\2\15\3\0"+
    "\1\15\5\0\15\15\1\327\12\15\7\0\2\15\2\51"+
    "\6\0\2\15\3\0\1\15\5\0\4\15\1\330\23\15"+
    "\7\0\2\15\2\51\6\0\2\15\3\0\1\15\5\0"+
    "\4\15\1\331\23\15\7\0\2\15\2\51\6\0\2\15"+
    "\3\0\1\15\5\0\16\15\1\332\11\15\7\0\2\15"+
    "\2\51\6\0\2\15\3\0\1\15\5\0\5\15\1\333"+
    "\22\15\7\0\2\15\2\51\6\0\2\15\3\0\1\15"+
    "\5\0\17\15\1\334\10\15\7\0\2\15\2\51\6\0"+
    "\2\15\3\0\1\15\5\0\6\15\1\335\21\15\7\0"+
    "\2\15\2\51\6\0\2\15\3\0\1\15\5\0\16\15"+
    "\1\336\11\15\7\0\2\15\2\51\6\0\2\15\3\0"+
    "\1\15\5\0\4\15\1\337\23\15\7\0\2\15\2\51"+
    "\6\0\2\15\3\0\1\15\1\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[8632];
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
    "\5\0\1\11\2\1\1\11\23\1\4\11\1\1\2\11"+
    "\2\1\2\11\7\1\2\11\2\1\1\11\4\1\1\0"+
    "\41\1\1\0\5\1\2\0\43\1\2\11\35\1\1\0"+
    "\25\1\1\0\45\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[223];
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
    zzReader = in;
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
    while (i < 2272) {
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
        case 67: break;
        case 42: 
          { return TaraTypes.WITH;
          }
        case 68: break;
        case 34: 
          { return TaraTypes.VAR;
          }
        case 69: break;
        case 14: 
          { return TaraTypes.COLON;
          }
        case 70: break;
        case 64: 
          { return TaraTypes.ABSTRACT;
          }
        case 71: break;
        case 47: 
          { return TaraTypes.RATIO_TYPE;
          }
        case 72: break;
        case 20: 
          { yybegin(YYINITIAL); return TaraTypes.QUOTE_END;
          }
        case 73: break;
        case 16: 
          { return TaraTypes.MEASURE_VALUE_KEY;
          }
        case 74: break;
        case 52: 
          { return TaraTypes.SINGLE;
          }
        case 75: break;
        case 38: 
          { return TaraTypes.DOUBLE_VALUE_KEY;
          }
        case 76: break;
        case 21: 
          { yybegin(YYINITIAL); return TaraTypes.EXPRESSION_END;
          }
        case 77: break;
        case 2: 
          { return TokenType.WHITE_SPACE;
          }
        case 78: break;
        case 55: 
          { return TaraTypes.NATURAL_TYPE;
          }
        case 79: break;
        case 36: 
          { return TaraTypes.LIST;
          }
        case 80: break;
        case 7: 
          { return TaraTypes.RIGHT_PARENTHESIS;
          }
        case 81: break;
        case 12: 
          { yybegin(EXPRESSION); return TaraTypes.EXPRESSION_BEGIN;
          }
        case 82: break;
        case 15: 
          { return TaraTypes.EQUALS;
          }
        case 83: break;
        case 49: 
          { return TaraTypes.FINAL;
          }
        case 84: break;
        case 10: 
          { return TaraTypes.DOT;
          }
        case 85: break;
        case 51: 
          { return TaraTypes.STRING_TYPE;
          }
        case 86: break;
        case 63: 
          { return TaraTypes.TERMINAL;
          }
        case 87: break;
        case 50: 
          { return TaraTypes.NATIVE_TYPE;
          }
        case 88: break;
        case 40: 
          { return TaraTypes.DATE_TYPE;
          }
        case 89: break;
        case 5: 
          { return TaraTypes.IDENTIFIER_KEY;
          }
        case 90: break;
        case 46: 
          { return TaraTypes.EMPTY_REF;
          }
        case 91: break;
        case 6: 
          { return TaraTypes.LEFT_PARENTHESIS;
          }
        case 92: break;
        case 65: 
          { return TaraTypes.REQUIRED;
          }
        case 93: break;
        case 57: 
          { return TaraTypes.PRIVATE;
          }
        case 94: break;
        case 25: 
          { yybegin(EXPRESSION_MULTILINE); return TaraTypes.EXPRESSION_BEGIN;
          }
        case 95: break;
        case 61: 
          { return TaraTypes.FEATURE;
          }
        case 96: break;
        case 26: 
          { return TaraTypes.NEGATIVE_VALUE_KEY;
          }
        case 97: break;
        case 35: 
          { return TaraTypes.DSL;
          }
        case 98: break;
        case 4: 
          { return inline();
          }
        case 99: break;
        case 29: 
          { return TaraTypes.ADDRESS_VALUE;
          }
        case 100: break;
        case 56: 
          { return TaraTypes.EXTENDS;
          }
        case 101: break;
        case 28: 
          { yybegin(MULTILINE); return TaraTypes.QUOTE_BEGIN;
          }
        case 102: break;
        case 66: 
          { return TaraTypes.PROTOTYPE;
          }
        case 103: break;
        case 53: 
          { return TaraTypes.DOUBLE_TYPE;
          }
        case 104: break;
        case 9: 
          { return TaraTypes.RIGHT_SQUARE;
          }
        case 105: break;
        case 18: 
          { return TaraTypes.NATURAL_VALUE_KEY;
          }
        case 106: break;
        case 17: 
          { return semicolon();
          }
        case 107: break;
        case 33: 
          { return TaraTypes.ANY;
          }
        case 108: break;
        case 1: 
          { return TokenType.BAD_CHARACTER;
          }
        case 109: break;
        case 43: 
          { return TaraTypes.INTO;
          }
        case 110: break;
        case 45: 
          { return TaraTypes.RESOURCE_KEY;
          }
        case 111: break;
        case 37: 
          { yypushback(1); return TaraTypes.DOC_LINE;
          }
        case 112: break;
        case 39: 
          { return TaraTypes.BOOLEAN_VALUE_KEY;
          }
        case 113: break;
        case 23: 
          { return TaraTypes.AS;
          }
        case 114: break;
        case 22: 
          { return TaraTypes.ON;
          }
        case 115: break;
        case 30: 
          { return TaraTypes.SUB;
          }
        case 116: break;
        case 62: 
          { return TaraTypes.ENCLOSED;
          }
        case 117: break;
        case 11: 
          { yybegin(QUOTED); return TaraTypes.QUOTE_BEGIN;
          }
        case 118: break;
        case 58: 
          { return TaraTypes.BOOLEAN_TYPE;
          }
        case 119: break;
        case 24: 
          { return TaraTypes.IS;
          }
        case 120: break;
        case 60: 
          { return TaraTypes.MEASURE_TYPE_KEY;
          }
        case 121: break;
        case 31: 
          { return TaraTypes.USE;
          }
        case 122: break;
        case 59: 
          { return TaraTypes.INT_TYPE;
          }
        case 123: break;
        case 54: 
          { return TaraTypes.METAIDENTIFIER_KEY;
          }
        case 124: break;
        case 44: 
          { return TaraTypes.MAIN;
          }
        case 125: break;
        case 41: 
          { return TaraTypes.WORD_KEY;
          }
        case 126: break;
        case 19: 
          { return TaraTypes.CHARACTER;
          }
        case 127: break;
        case 48: 
          { return TaraTypes.FACET;
          }
        case 128: break;
        case 13: 
          { return TaraTypes.COMMA;
          }
        case 129: break;
        case 32: 
          { return TaraTypes.HAS;
          }
        case 130: break;
        case 3: 
          { return newlineIndent();
          }
        case 131: break;
        case 27: 
          { 
          }
        case 132: break;
        default:
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            switch (zzLexicalState) {
            case YYINITIAL: {
              return eof();
            }
            case 224: break;
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