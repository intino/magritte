/* The following code was generated by JFlex 1.4.3 on 16/12/13 14:05 */

package monet.tara.metamodel;

import com.intellij.lexer.FlexLexer;
import java.util.Stack;
import com.intellij.psi.tree.IElementType;
import monet.tara.metamodel.psi.TaraTypes;
import com.intellij.psi.TokenType;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 16/12/13 14:05 from the specification file
 * <tt>/home/oroncal/workspace/idea/TaraPlugin/src/monet/tara/metamodel/Tara.flex</tt>
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
    "\11\47\1\3\1\1\2\0\1\5\16\47\4\0\1\2\1\4\1\36"+
    "\1\4\1\46\3\0\1\27\1\30\2\0\1\0\1\0\1\34\1\0"+
    "\12\45\1\35\2\0\1\37\2\0\1\40\32\46\1\31\1\0\1\32"+
    "\1\0\1\46\1\0\1\11\1\26\1\15\1\25\1\13\1\14\1\33"+
    "\1\10\1\6\1\42\1\46\1\23\1\22\1\17\1\16\1\20\1\46"+
    "\1\12\1\7\1\21\1\24\1\43\1\46\1\41\1\44\1\46\4\0"+
    "\41\47\2\0\4\46\4\0\1\46\2\0\1\47\7\0\1\46\4\0"+
    "\1\46\5\0\27\46\1\0\37\46\1\0\u01ca\46\4\0\14\46\16\0"+
    "\5\46\7\0\1\46\1\0\1\46\21\0\160\47\5\46\1\0\2\46"+
    "\2\0\4\46\10\0\1\46\1\0\3\46\1\0\1\46\1\0\24\46"+
    "\1\0\123\46\1\0\213\46\1\0\5\47\2\0\236\46\11\0\46\46"+
    "\2\0\1\46\7\0\47\46\11\0\55\47\1\0\1\47\1\0\2\47"+
    "\1\0\2\47\1\0\1\47\10\0\33\46\5\0\3\46\15\0\4\47"+
    "\7\0\1\46\4\0\13\47\5\0\53\46\25\47\12\45\4\0\2\46"+
    "\1\47\143\46\1\0\1\46\10\47\1\0\6\47\2\46\2\47\1\0"+
    "\4\47\2\46\12\45\3\46\2\0\1\46\17\0\1\47\1\46\1\47"+
    "\36\46\33\47\2\0\131\46\13\47\1\46\16\0\12\45\41\46\11\47"+
    "\2\46\4\0\1\46\5\0\26\46\4\47\1\46\11\47\1\46\3\47"+
    "\1\46\5\47\22\0\31\46\3\47\244\0\4\47\66\46\3\47\1\46"+
    "\22\47\1\46\7\47\12\46\2\47\2\0\12\45\1\0\7\46\1\0"+
    "\7\46\1\0\3\47\1\0\10\46\2\0\2\46\2\0\26\46\1\0"+
    "\7\46\1\0\1\46\3\0\4\46\2\0\1\47\1\46\7\47\2\0"+
    "\2\47\2\0\3\47\1\46\10\0\1\47\4\0\2\46\1\0\3\46"+
    "\2\47\2\0\12\45\4\46\7\0\1\46\5\0\3\47\1\0\6\46"+
    "\4\0\2\46\2\0\26\46\1\0\7\46\1\0\2\46\1\0\2\46"+
    "\1\0\2\46\2\0\1\47\1\0\5\47\4\0\2\47\2\0\3\47"+
    "\3\0\1\47\7\0\4\46\1\0\1\46\7\0\12\45\2\47\3\46"+
    "\1\47\13\0\3\47\1\0\11\46\1\0\3\46\1\0\26\46\1\0"+
    "\7\46\1\0\2\46\1\0\5\46\2\0\1\47\1\46\10\47\1\0"+
    "\3\47\1\0\3\47\2\0\1\46\17\0\2\46\2\47\2\0\12\45"+
    "\1\0\1\46\17\0\3\47\1\0\10\46\2\0\2\46\2\0\26\46"+
    "\1\0\7\46\1\0\2\46\1\0\5\46\2\0\1\47\1\46\7\47"+
    "\2\0\2\47\2\0\3\47\10\0\2\47\4\0\2\46\1\0\3\46"+
    "\2\47\2\0\12\45\1\0\1\46\20\0\1\47\1\46\1\0\6\46"+
    "\3\0\3\46\1\0\4\46\3\0\2\46\1\0\1\46\1\0\2\46"+
    "\3\0\2\46\3\0\3\46\3\0\14\46\4\0\5\47\3\0\3\47"+
    "\1\0\4\47\2\0\1\46\6\0\1\47\16\0\12\45\11\0\1\46"+
    "\7\0\3\47\1\0\10\46\1\0\3\46\1\0\27\46\1\0\12\46"+
    "\1\0\5\46\3\0\1\46\7\47\1\0\3\47\1\0\4\47\7\0"+
    "\2\47\1\0\2\46\6\0\2\46\2\47\2\0\12\45\22\0\2\47"+
    "\1\0\10\46\1\0\3\46\1\0\27\46\1\0\12\46\1\0\5\46"+
    "\2\0\1\47\1\46\7\47\1\0\3\47\1\0\4\47\7\0\2\47"+
    "\7\0\1\46\1\0\2\46\2\47\2\0\12\45\1\0\2\46\17\0"+
    "\2\47\1\0\10\46\1\0\3\46\1\0\51\46\2\0\1\46\7\47"+
    "\1\0\3\47\1\0\4\47\1\46\10\0\1\47\10\0\2\46\2\47"+
    "\2\0\12\45\12\0\6\46\2\0\2\47\1\0\22\46\3\0\30\46"+
    "\1\0\11\46\1\0\1\46\2\0\7\46\3\0\1\47\4\0\6\47"+
    "\1\0\1\47\1\0\10\47\22\0\2\47\15\0\60\46\1\47\2\46"+
    "\7\47\4\0\10\46\10\47\1\0\12\45\47\0\2\46\1\0\1\46"+
    "\2\0\2\46\1\0\1\46\2\0\1\46\6\0\4\46\1\0\7\46"+
    "\1\0\3\46\1\0\1\46\1\0\1\46\2\0\2\46\1\0\4\46"+
    "\1\47\2\46\6\47\1\0\2\47\1\46\2\0\5\46\1\0\1\46"+
    "\1\0\6\47\2\0\12\45\2\0\2\46\42\0\1\46\27\0\2\47"+
    "\6\0\12\45\13\0\1\47\1\0\1\47\1\0\1\47\4\0\2\47"+
    "\10\46\1\0\44\46\4\0\24\47\1\0\2\47\5\46\13\47\1\0"+
    "\44\47\11\0\1\47\71\0\53\46\24\47\1\46\12\45\6\0\6\46"+
    "\4\47\4\46\3\47\1\46\3\47\2\46\7\47\3\46\4\47\15\46"+
    "\14\47\1\46\1\47\12\45\4\47\2\0\46\46\12\0\53\46\1\0"+
    "\1\46\3\0\u0149\46\1\0\4\46\2\0\7\46\1\0\1\46\1\0"+
    "\4\46\2\0\51\46\1\0\4\46\2\0\41\46\1\0\4\46\2\0"+
    "\7\46\1\0\1\46\1\0\4\46\2\0\17\46\1\0\71\46\1\0"+
    "\4\46\2\0\103\46\2\0\3\47\40\0\20\46\20\0\125\46\14\0"+
    "\u026c\46\2\0\21\46\1\0\32\46\5\0\113\46\3\0\3\46\17\0"+
    "\15\46\1\0\4\46\3\47\13\0\22\46\3\47\13\0\22\46\2\47"+
    "\14\0\15\46\1\0\3\46\1\0\2\47\14\0\64\46\40\47\3\0"+
    "\1\46\3\0\2\46\1\47\2\0\12\45\41\0\3\47\2\0\12\45"+
    "\6\0\130\46\10\0\51\46\1\47\1\46\5\0\106\46\12\0\35\46"+
    "\3\0\14\47\4\0\14\47\12\0\12\45\36\46\2\0\5\46\13\0"+
    "\54\46\4\0\21\47\7\46\2\47\6\0\12\45\46\0\27\46\5\47"+
    "\4\0\65\46\12\47\1\0\35\47\2\0\1\47\12\45\6\0\12\45"+
    "\15\0\1\46\130\0\5\47\57\46\21\47\7\46\4\0\12\45\21\0"+
    "\11\47\14\0\3\47\36\46\12\47\3\0\2\46\12\45\6\0\46\46"+
    "\16\47\14\0\44\46\24\47\10\0\12\45\3\0\3\46\12\45\44\46"+
    "\122\0\3\47\1\0\25\47\4\46\1\47\4\46\1\47\15\0\300\46"+
    "\47\47\25\0\4\47\u0116\46\2\0\6\46\2\0\46\46\2\0\6\46"+
    "\2\0\10\46\1\0\1\46\1\0\1\46\1\0\1\46\1\0\37\46"+
    "\2\0\65\46\1\0\7\46\1\0\1\46\3\0\3\46\1\0\7\46"+
    "\3\0\4\46\2\0\6\46\4\0\15\46\5\0\3\46\1\0\7\46"+
    "\16\0\5\47\32\0\5\47\20\0\2\46\23\0\1\46\13\0\5\47"+
    "\5\0\6\47\1\0\1\46\15\0\1\46\20\0\15\46\3\0\32\46"+
    "\26\0\15\47\4\0\1\47\3\0\14\47\21\0\1\46\4\0\1\46"+
    "\2\0\12\46\1\0\1\46\3\0\5\46\6\0\1\46\1\0\1\46"+
    "\1\0\1\46\1\0\4\46\1\0\13\46\2\0\4\46\5\0\5\46"+
    "\4\0\1\46\21\0\51\46\u0a77\0\57\46\1\0\57\46\1\0\205\46"+
    "\6\0\4\46\3\47\16\0\46\46\12\0\66\46\11\0\1\46\17\0"+
    "\1\47\27\46\11\0\7\46\1\0\7\46\1\0\7\46\1\0\7\46"+
    "\1\0\7\46\1\0\7\46\1\0\7\46\1\0\7\46\1\0\40\47"+
    "\57\0\1\46\u01d5\0\3\46\31\0\11\46\6\47\1\0\5\46\2\0"+
    "\5\46\4\0\126\46\2\0\2\47\2\0\3\46\1\0\132\46\1\0"+
    "\4\46\5\0\51\46\3\0\136\46\21\0\33\46\65\0\20\46\u0200\0"+
    "\u19b6\46\112\0\u51cc\46\64\0\u048d\46\103\0\56\46\2\0\u010d\46\3\0"+
    "\20\46\12\45\2\46\24\0\57\46\1\47\14\0\2\47\1\0\31\46"+
    "\10\0\120\46\2\47\45\0\11\46\2\0\147\46\2\0\4\46\1\0"+
    "\2\46\16\0\12\46\120\0\10\46\1\47\3\46\1\47\4\46\1\47"+
    "\27\46\5\47\20\0\1\46\7\0\64\46\14\0\2\47\62\46\21\47"+
    "\13\0\12\45\6\0\22\47\6\46\3\0\1\46\4\0\12\45\34\46"+
    "\10\47\2\0\27\46\15\47\14\0\35\46\3\0\4\47\57\46\16\47"+
    "\16\0\1\46\12\45\46\0\51\46\16\47\11\0\3\46\1\47\10\46"+
    "\2\47\2\0\12\45\6\0\27\46\3\0\1\46\1\47\4\0\60\46"+
    "\1\47\1\46\3\47\2\46\2\47\5\46\2\47\1\46\1\47\1\46"+
    "\30\0\3\46\43\0\6\46\2\0\6\46\2\0\6\46\11\0\7\46"+
    "\1\0\7\46\221\0\43\46\10\47\1\0\2\47\2\0\12\45\6\0"+
    "\u2ba4\46\14\0\27\46\4\0\61\46\u2104\0\u012e\46\2\0\76\46\2\0"+
    "\152\46\46\0\7\46\14\0\5\46\5\0\1\46\1\47\12\46\1\0"+
    "\15\46\1\0\5\46\1\0\1\46\1\0\2\46\1\0\2\46\1\0"+
    "\154\46\41\0\u016b\46\22\0\100\46\2\0\66\46\50\0\15\46\3\0"+
    "\20\47\20\0\7\47\14\0\2\46\30\0\3\46\31\0\1\46\6\0"+
    "\5\46\1\0\207\46\2\0\1\47\4\0\1\46\13\0\12\45\7\0"+
    "\32\46\4\0\1\46\1\0\32\46\13\0\131\46\3\0\6\46\2\0"+
    "\6\46\2\0\6\46\2\0\3\46\3\0\2\46\3\0\2\46\22\0"+
    "\3\47\4\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\1\2\2\3\1\4\12\5\1\6\1\7"+
    "\1\1\1\10\1\1\1\11\1\1\1\12\1\13\1\3"+
    "\2\4\1\5\1\14\1\5\1\15\10\5\1\0\1\10"+
    "\1\0\1\16\1\11\5\0\1\12\1\2\1\17\1\5"+
    "\1\20\1\5\1\21\2\5\1\22\1\5\6\0\1\23"+
    "\5\5\6\0\2\5\1\24\2\5\4\0\1\25\2\0"+
    "\1\26\2\5\1\27\1\30\4\0\1\5\1\31\12\0"+
    "\1\32\1\0";

  private static int [] zzUnpackAction() {
    int [] result = new int[114];
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
    "\0\0\0\50\0\50\0\50\0\120\0\170\0\240\0\310"+
    "\0\360\0\u0118\0\u0140\0\u0168\0\u0190\0\u01b8\0\u01e0\0\u0208"+
    "\0\50\0\50\0\u0230\0\u0258\0\u0280\0\u02a8\0\u02d0\0\u02f8"+
    "\0\u0320\0\u0348\0\u0370\0\u0398\0\u03c0\0\u03c0\0\u03e8\0\u03c0"+
    "\0\u0410\0\u0438\0\u0460\0\u0488\0\u04b0\0\u04d8\0\u0500\0\u0528"+
    "\0\u0550\0\u0320\0\u0578\0\u05a0\0\u0320\0\u05c8\0\u05f0\0\u0618"+
    "\0\u0640\0\u0668\0\u0690\0\u05a0\0\u03c0\0\u06b8\0\u03c0\0\u06e0"+
    "\0\u03c0\0\u0708\0\u0730\0\u03c0\0\u0758\0\u0780\0\u07a8\0\u07d0"+
    "\0\u07f8\0\u0820\0\u0848\0\u0668\0\u0870\0\u0898\0\u08c0\0\u08e8"+
    "\0\u0910\0\u0938\0\u0960\0\u0988\0\u09b0\0\u09d8\0\u0a00\0\u0a28"+
    "\0\u0a50\0\u03c0\0\u0a78\0\u0aa0\0\u0ac8\0\u0af0\0\u0b18\0\u0b40"+
    "\0\u05a0\0\u0b68\0\u0b90\0\u03c0\0\u0bb8\0\u0be0\0\u03c0\0\u05a0"+
    "\0\u0c08\0\u0c30\0\u0c58\0\u0c80\0\u0ca8\0\u03c0\0\u0cd0\0\u0cf8"+
    "\0\u0d20\0\u0d48\0\u0d70\0\u0d98\0\u0dc0\0\u0de8\0\u0e10\0\u0e38"+
    "\0\u05a0\0\u0e60";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[114];
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
    "\1\2\1\3\1\4\1\5\1\6\1\2\1\7\1\10"+
    "\1\11\1\12\1\13\1\14\1\15\1\16\6\14\1\17"+
    "\1\20\1\14\1\21\1\22\1\23\1\2\1\14\1\2"+
    "\1\24\1\25\1\26\1\27\4\14\1\30\1\14\1\2"+
    "\2\0\1\31\47\0\1\31\1\32\44\0\1\33\1\0"+
    "\1\34\2\33\1\0\42\33\2\0\1\31\3\0\1\35"+
    "\1\36\7\35\1\37\5\35\1\40\1\35\4\0\1\35"+
    "\5\0\7\35\2\0\1\31\3\0\13\35\1\41\5\35"+
    "\4\0\1\35\5\0\7\35\2\0\1\31\3\0\3\35"+
    "\1\42\15\35\4\0\1\35\5\0\7\35\2\0\1\31"+
    "\3\0\20\35\1\43\4\0\1\35\5\0\7\35\2\0"+
    "\1\31\3\0\5\35\1\44\13\35\4\0\1\35\5\0"+
    "\7\35\2\0\1\31\3\0\21\35\4\0\1\35\5\0"+
    "\7\35\2\0\1\31\3\0\1\45\20\35\4\0\1\35"+
    "\5\0\7\35\2\0\1\31\3\0\10\35\1\46\10\35"+
    "\4\0\1\35\5\0\7\35\2\0\1\31\3\0\1\35"+
    "\1\47\17\35\4\0\1\35\5\0\7\35\2\0\1\31"+
    "\3\0\10\35\1\50\10\35\4\0\1\35\5\0\7\35"+
    "\2\0\1\31\42\0\1\51\4\0\1\52\47\0\1\31"+
    "\3\0\21\53\4\0\1\53\2\0\1\54\2\0\7\53"+
    "\2\0\1\55\47\0\1\31\6\0\1\56\1\57\1\60"+
    "\3\0\1\61\32\0\1\31\31\0\1\62\10\0\1\63"+
    "\3\0\1\64\1\31\50\0\1\32\44\0\1\33\1\0"+
    "\3\33\1\0\43\33\1\64\1\34\2\33\1\0\42\33"+
    "\6\0\21\35\4\0\1\35\5\0\7\35\6\0\13\35"+
    "\1\65\5\35\4\0\1\35\5\0\7\35\6\0\4\35"+
    "\1\66\14\35\4\0\1\35\5\0\7\35\6\0\1\35"+
    "\1\67\17\35\4\0\1\35\5\0\7\35\6\0\1\35"+
    "\1\70\17\35\4\0\1\35\5\0\7\35\6\0\6\35"+
    "\1\71\12\35\4\0\1\35\5\0\7\35\6\0\11\35"+
    "\1\72\7\35\4\0\1\35\5\0\7\35\6\0\11\35"+
    "\1\73\7\35\4\0\1\35\5\0\7\35\6\0\5\35"+
    "\1\74\13\35\4\0\1\35\5\0\7\35\6\0\16\35"+
    "\1\75\2\35\4\0\1\35\5\0\7\35\34\0\1\76"+
    "\10\0\1\51\10\0\21\53\4\0\1\53\2\0\1\54"+
    "\2\0\7\53\65\0\1\77\1\0\1\100\46\0\1\101"+
    "\72\0\1\102\17\0\1\103\103\0\1\104\36\0\1\62"+
    "\10\0\1\63\10\0\1\105\20\35\4\0\1\35\5\0"+
    "\7\35\6\0\13\35\1\106\5\35\4\0\1\35\5\0"+
    "\7\35\6\0\3\35\1\107\15\35\4\0\1\35\5\0"+
    "\7\35\6\0\7\35\1\110\11\35\4\0\1\35\5\0"+
    "\7\35\6\0\20\35\1\111\4\0\1\35\5\0\7\35"+
    "\34\0\1\112\34\0\1\113\44\0\1\114\47\0\1\115"+
    "\52\0\1\116\50\0\1\117\33\0\11\35\1\120\7\35"+
    "\4\0\1\35\5\0\7\35\6\0\4\35\1\121\14\35"+
    "\4\0\1\35\5\0\7\35\6\0\15\35\1\122\3\35"+
    "\4\0\1\35\5\0\7\35\6\0\5\35\1\123\13\35"+
    "\4\0\1\35\5\0\7\35\6\0\15\35\1\124\3\35"+
    "\4\0\1\35\5\0\7\35\17\0\1\125\25\0\1\126"+
    "\10\0\1\127\60\0\1\130\51\0\1\131\41\0\1\132"+
    "\47\0\1\133\42\0\21\35\4\0\1\134\5\0\7\35"+
    "\6\0\3\35\1\135\15\35\4\0\1\35\5\0\7\35"+
    "\6\0\12\35\1\136\6\35\4\0\1\35\5\0\7\35"+
    "\6\0\5\35\1\137\13\35\4\0\1\35\5\0\7\35"+
    "\32\0\1\140\47\0\1\140\12\0\1\126\20\0\1\141"+
    "\75\0\1\142\22\0\1\143\41\0\1\144\44\0\7\35"+
    "\1\145\11\35\4\0\1\35\5\0\7\35\6\0\13\35"+
    "\1\146\5\35\4\0\1\35\5\0\7\35\17\0\1\147"+
    "\52\0\1\150\34\0\1\151\66\0\1\152\27\0\13\35"+
    "\1\122\5\35\4\0\1\35\5\0\7\35\35\0\1\153"+
    "\30\0\1\154\37\0\1\144\64\0\1\155\66\0\1\156"+
    "\31\0\1\157\36\0\1\131\45\0\1\160\45\0\1\161"+
    "\103\0\1\162\15\0\1\131\36\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[3720];
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
    "\1\0\47\1\1\0\1\1\1\0\1\11\1\1\5\0"+
    "\1\1\1\11\11\1\6\0\6\1\6\0\5\1\4\0"+
    "\1\11\2\0\4\1\1\11\4\0\2\1\12\0\1\11"+
    "\1\0";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[114];
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

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;

  /* user code: */

	static Stack<Integer> stack = new Stack<>();

	private int transformToSpaces(CharSequence chain){
		int value = 0;
		for(int i = 0; i < chain.length(); i++)
			if (chain.charAt(i) == ('\t')) value += 4;
			else value += 1;
		return value;
	}

	private IElementType cleanStack(){
		if (!stack.empty()) {
            stack.pop();
            if (!stack.empty() && isTextIndented(transformToSpaces(yytext())))
                yypushback(yylength());
            return TaraTypes.DEDENT;
        }
            return TokenType.WHITE_SPACE;
    }

	private boolean isTextIndented(int textLength){
		if (!stack.empty())
			return textLength > stack.peek();
		return false;
	}

	private boolean isTextDedented(int textLength){
		if (!stack.empty())
    		return textLength < stack.peek();
		return false;
    }

	private boolean isTextSibling(int textLength){
		if (!stack.empty())
			return textLength == stack.peek();
		return false;
    }

	private IElementType calculateIndentationToken() {
		int textLength = transformToSpaces(yytext());
        if (stack.empty() || isTextIndented(textLength)){
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
    while (i < 2204) {
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
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() {
    if (!zzEOFDone) {
      zzEOFDone = true;
    	if(stack.size() > 0) {
		stack.pop();
	}


    }
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
            zzInput = zzBufferL.charAt(zzCurrentPosL++);
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
              zzInput = zzBufferL.charAt(zzCurrentPosL++);
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
        case 5: 
          { return TaraTypes.IDENTIFIER;
          }
        case 27: break;
        case 20: 
          { return TaraTypes.MODIFIERS;
          }
        case 28: break;
        case 12: 
          { return TaraTypes.IS;
          }
        case 29: break;
        case 1: 
          { return TokenType.BAD_CHARACTER;
          }
        case 30: break;
        case 10: 
          { return TaraTypes.INT;
          }
        case 31: break;
        case 2: 
          { return cleanStack();
          }
        case 32: break;
        case 14: 
          { return TaraTypes.STRING;
          }
        case 33: break;
        case 11: 
          { return calculateIndentationToken();
          }
        case 34: break;
        case 8: 
          { return TaraTypes.COLON;
          }
        case 35: break;
        case 24: 
          { return TaraTypes.RANGE;
          }
        case 36: break;
        case 22: 
          { return TaraTypes.STRING_TYPE;
          }
        case 37: break;
        case 26: 
          { return TaraTypes.ANONYMOUS;
          }
        case 38: break;
        case 3: 
          { return TokenType.WHITE_SPACE;
          }
        case 39: break;
        case 4: 
          { return TaraTypes.COMMENT;
          }
        case 40: break;
        case 25: 
          { return TaraTypes.CONCEPT;
          }
        case 41: break;
        case 16: 
          { return TaraTypes.HAS;
          }
        case 42: break;
        case 15: 
          { return TaraTypes.INT_TYPE;
          }
        case 43: break;
        case 19: 
          { return TaraTypes.DOUBLE;
          }
        case 44: break;
        case 18: 
          { return TaraTypes.USE;
          }
        case 45: break;
        case 13: 
          { return TaraTypes.ID_TYPE;
          }
        case 46: break;
        case 7: 
          { return TaraTypes.RIGHT_P;
          }
        case 47: break;
        case 17: 
          { return TaraTypes.REF;
          }
        case 48: break;
        case 21: 
          { return TaraTypes.ANNOTATION;
          }
        case 49: break;
        case 9: 
          { return TaraTypes.ASSIGN;
          }
        case 50: break;
        case 23: 
          { return TaraTypes.DOUBLE_TYPE;
          }
        case 51: break;
        case 6: 
          { return TaraTypes.LEFT_P;
          }
        case 52: break;
        default:
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            zzDoEOF();
            return null;
          }
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
