/* The following code was generated by JFlex 1.4.3 on 6/06/14 7:55 */

package monet.tara.intellij.lang.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import monet.tara.intellij.lang.psi.TaraTypes;
import com.intellij.psi.TokenType;
import java.util.LinkedList;
import java.util.Queue;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 6/06/14 7:55 from the specification file
 * <tt>/Users/oroncal/workspace/tara/intellij/src/monet/tara/intellij/lang/lexer/Tara.flex</tt>
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
    "\11\64\1\1\1\2\3\0\16\64\4\0\1\1\1\0\1\47\1\0"+
    "\1\63\2\0\1\61\1\35\1\36\1\0\1\52\1\44\1\30\1\43"+
    "\1\0\12\62\1\45\1\46\1\50\1\0\1\51\2\0\1\53\1\60"+
    "\1\3\1\56\4\63\1\54\4\63\1\55\3\63\1\32\1\57\3\63"+
    "\1\31\3\63\1\37\1\0\1\40\1\0\1\63\1\0\1\15\1\21"+
    "\1\6\1\26\1\7\1\22\1\17\1\27\1\12\1\63\1\16\1\23"+
    "\1\13\1\5\1\4\1\10\1\25\1\14\1\20\1\11\1\24\1\33"+
    "\2\63\1\34\1\63\1\41\1\0\1\42\1\0\41\64\2\0\4\63"+
    "\4\0\1\63\2\0\1\64\7\0\1\63\4\0\1\63\5\0\27\63"+
    "\1\0\37\63\1\0\u01ca\63\4\0\14\63\16\0\5\63\7\0\1\63"+
    "\1\0\1\63\21\0\160\64\5\63\1\0\2\63\2\0\4\63\10\0"+
    "\1\63\1\0\3\63\1\0\1\63\1\0\24\63\1\0\123\63\1\0"+
    "\213\63\1\0\5\64\2\0\236\63\11\0\46\63\2\0\1\63\7\0"+
    "\47\63\11\0\55\64\1\0\1\64\1\0\2\64\1\0\2\64\1\0"+
    "\1\64\10\0\33\63\5\0\3\63\15\0\4\64\7\0\1\63\4\0"+
    "\13\64\5\0\53\63\25\64\12\62\4\0\2\63\1\64\143\63\1\0"+
    "\1\63\10\64\1\0\6\64\2\63\2\64\1\0\4\64\2\63\12\62"+
    "\3\63\2\0\1\63\17\0\1\64\1\63\1\64\36\63\33\64\2\0"+
    "\131\63\13\64\1\63\16\0\12\62\41\63\11\64\2\63\4\0\1\63"+
    "\5\0\26\63\4\64\1\63\11\64\1\63\3\64\1\63\5\64\22\0"+
    "\31\63\3\64\244\0\4\64\66\63\3\64\1\63\22\64\1\63\7\64"+
    "\12\63\2\64\2\0\12\62\1\0\7\63\1\0\7\63\1\0\3\64"+
    "\1\0\10\63\2\0\2\63\2\0\26\63\1\0\7\63\1\0\1\63"+
    "\3\0\4\63\2\0\1\64\1\63\7\64\2\0\2\64\2\0\3\64"+
    "\1\63\10\0\1\64\4\0\2\63\1\0\3\63\2\64\2\0\12\62"+
    "\4\63\7\0\1\63\5\0\3\64\1\0\6\63\4\0\2\63\2\0"+
    "\26\63\1\0\7\63\1\0\2\63\1\0\2\63\1\0\2\63\2\0"+
    "\1\64\1\0\5\64\4\0\2\64\2\0\3\64\3\0\1\64\7\0"+
    "\4\63\1\0\1\63\7\0\12\62\2\64\3\63\1\64\13\0\3\64"+
    "\1\0\11\63\1\0\3\63\1\0\26\63\1\0\7\63\1\0\2\63"+
    "\1\0\5\63\2\0\1\64\1\63\10\64\1\0\3\64\1\0\3\64"+
    "\2\0\1\63\17\0\2\63\2\64\2\0\12\62\1\0\1\63\17\0"+
    "\3\64\1\0\10\63\2\0\2\63\2\0\26\63\1\0\7\63\1\0"+
    "\2\63\1\0\5\63\2\0\1\64\1\63\7\64\2\0\2\64\2\0"+
    "\3\64\10\0\2\64\4\0\2\63\1\0\3\63\2\64\2\0\12\62"+
    "\1\0\1\63\20\0\1\64\1\63\1\0\6\63\3\0\3\63\1\0"+
    "\4\63\3\0\2\63\1\0\1\63\1\0\2\63\3\0\2\63\3\0"+
    "\3\63\3\0\14\63\4\0\5\64\3\0\3\64\1\0\4\64\2\0"+
    "\1\63\6\0\1\64\16\0\12\62\11\0\1\63\7\0\3\64\1\0"+
    "\10\63\1\0\3\63\1\0\27\63\1\0\12\63\1\0\5\63\3\0"+
    "\1\63\7\64\1\0\3\64\1\0\4\64\7\0\2\64\1\0\2\63"+
    "\6\0\2\63\2\64\2\0\12\62\22\0\2\64\1\0\10\63\1\0"+
    "\3\63\1\0\27\63\1\0\12\63\1\0\5\63\2\0\1\64\1\63"+
    "\7\64\1\0\3\64\1\0\4\64\7\0\2\64\7\0\1\63\1\0"+
    "\2\63\2\64\2\0\12\62\1\0\2\63\17\0\2\64\1\0\10\63"+
    "\1\0\3\63\1\0\51\63\2\0\1\63\7\64\1\0\3\64\1\0"+
    "\4\64\1\63\10\0\1\64\10\0\2\63\2\64\2\0\12\62\12\0"+
    "\6\63\2\0\2\64\1\0\22\63\3\0\30\63\1\0\11\63\1\0"+
    "\1\63\2\0\7\63\3\0\1\64\4\0\6\64\1\0\1\64\1\0"+
    "\10\64\22\0\2\64\15\0\60\63\1\64\2\63\7\64\4\0\10\63"+
    "\10\64\1\0\12\62\47\0\2\63\1\0\1\63\2\0\2\63\1\0"+
    "\1\63\2\0\1\63\6\0\4\63\1\0\7\63\1\0\3\63\1\0"+
    "\1\63\1\0\1\63\2\0\2\63\1\0\4\63\1\64\2\63\6\64"+
    "\1\0\2\64\1\63\2\0\5\63\1\0\1\63\1\0\6\64\2\0"+
    "\12\62\2\0\2\63\42\0\1\63\27\0\2\64\6\0\12\62\13\0"+
    "\1\64\1\0\1\64\1\0\1\64\4\0\2\64\10\63\1\0\44\63"+
    "\4\0\24\64\1\0\2\64\5\63\13\64\1\0\44\64\11\0\1\64"+
    "\71\0\53\63\24\64\1\63\12\62\6\0\6\63\4\64\4\63\3\64"+
    "\1\63\3\64\2\63\7\64\3\63\4\64\15\63\14\64\1\63\1\64"+
    "\12\62\4\64\2\0\46\63\12\0\53\63\1\0\1\63\3\0\u0149\63"+
    "\1\0\4\63\2\0\7\63\1\0\1\63\1\0\4\63\2\0\51\63"+
    "\1\0\4\63\2\0\41\63\1\0\4\63\2\0\7\63\1\0\1\63"+
    "\1\0\4\63\2\0\17\63\1\0\71\63\1\0\4\63\2\0\103\63"+
    "\2\0\3\64\40\0\20\63\20\0\125\63\14\0\u026c\63\2\0\21\63"+
    "\1\0\32\63\5\0\113\63\3\0\3\63\17\0\15\63\1\0\4\63"+
    "\3\64\13\0\22\63\3\64\13\0\22\63\2\64\14\0\15\63\1\0"+
    "\3\63\1\0\2\64\14\0\64\63\40\64\3\0\1\63\3\0\2\63"+
    "\1\64\2\0\12\62\41\0\3\64\2\0\12\62\6\0\130\63\10\0"+
    "\51\63\1\64\1\63\5\0\106\63\12\0\35\63\3\0\14\64\4\0"+
    "\14\64\12\0\12\62\36\63\2\0\5\63\13\0\54\63\4\0\21\64"+
    "\7\63\2\64\6\0\12\62\46\0\27\63\5\64\4\0\65\63\12\64"+
    "\1\0\35\64\2\0\1\64\12\62\6\0\12\62\15\0\1\63\130\0"+
    "\5\64\57\63\21\64\7\63\4\0\12\62\21\0\11\64\14\0\3\64"+
    "\36\63\12\64\3\0\2\63\12\62\6\0\46\63\16\64\14\0\44\63"+
    "\24\64\10\0\12\62\3\0\3\63\12\62\44\63\122\0\3\64\1\0"+
    "\25\64\4\63\1\64\4\63\1\64\15\0\300\63\47\64\25\0\4\64"+
    "\u0116\63\2\0\6\63\2\0\46\63\2\0\6\63\2\0\10\63\1\0"+
    "\1\63\1\0\1\63\1\0\1\63\1\0\37\63\2\0\65\63\1\0"+
    "\7\63\1\0\1\63\3\0\3\63\1\0\7\63\3\0\4\63\2\0"+
    "\6\63\4\0\15\63\5\0\3\63\1\0\7\63\16\0\5\64\32\0"+
    "\5\64\20\0\2\63\23\0\1\63\13\0\5\64\5\0\6\64\1\0"+
    "\1\63\15\0\1\63\20\0\15\63\3\0\32\63\26\0\15\64\4\0"+
    "\1\64\3\0\14\64\21\0\1\63\4\0\1\63\2\0\12\63\1\0"+
    "\1\63\3\0\5\63\6\0\1\63\1\0\1\63\1\0\1\63\1\0"+
    "\4\63\1\0\13\63\2\0\4\63\5\0\5\63\4\0\1\63\21\0"+
    "\51\63\u0a77\0\57\63\1\0\57\63\1\0\205\63\6\0\4\63\3\64"+
    "\16\0\46\63\12\0\66\63\11\0\1\63\17\0\1\64\27\63\11\0"+
    "\7\63\1\0\7\63\1\0\7\63\1\0\7\63\1\0\7\63\1\0"+
    "\7\63\1\0\7\63\1\0\7\63\1\0\40\64\57\0\1\63\u01d5\0"+
    "\3\63\31\0\11\63\6\64\1\0\5\63\2\0\5\63\4\0\126\63"+
    "\2\0\2\64\2\0\3\63\1\0\132\63\1\0\4\63\5\0\51\63"+
    "\3\0\136\63\21\0\33\63\65\0\20\63\u0200\0\u19b6\63\112\0\u51cc\63"+
    "\64\0\u048d\63\103\0\56\63\2\0\u010d\63\3\0\20\63\12\62\2\63"+
    "\24\0\57\63\1\64\14\0\2\64\1\0\31\63\10\0\120\63\2\64"+
    "\45\0\11\63\2\0\147\63\2\0\4\63\1\0\2\63\16\0\12\63"+
    "\120\0\10\63\1\64\3\63\1\64\4\63\1\64\27\63\5\64\20\0"+
    "\1\63\7\0\64\63\14\0\2\64\62\63\21\64\13\0\12\62\6\0"+
    "\22\64\6\63\3\0\1\63\4\0\12\62\34\63\10\64\2\0\27\63"+
    "\15\64\14\0\35\63\3\0\4\64\57\63\16\64\16\0\1\63\12\62"+
    "\46\0\51\63\16\64\11\0\3\63\1\64\10\63\2\64\2\0\12\62"+
    "\6\0\27\63\3\0\1\63\1\64\4\0\60\63\1\64\1\63\3\64"+
    "\2\63\2\64\5\63\2\64\1\63\1\64\1\63\30\0\3\63\43\0"+
    "\6\63\2\0\6\63\2\0\6\63\11\0\7\63\1\0\7\63\221\0"+
    "\43\63\10\64\1\0\2\64\2\0\12\62\6\0\u2ba4\63\14\0\27\63"+
    "\4\0\61\63\u2104\0\u012e\63\2\0\76\63\2\0\152\63\46\0\7\63"+
    "\14\0\5\63\5\0\1\63\1\64\12\63\1\0\15\63\1\0\5\63"+
    "\1\0\1\63\1\0\2\63\1\0\2\63\1\0\154\63\41\0\u016b\63"+
    "\22\0\100\63\2\0\66\63\50\0\15\63\3\0\20\64\20\0\7\64"+
    "\14\0\2\63\30\0\3\63\31\0\1\63\6\0\5\63\1\0\207\63"+
    "\2\0\1\64\4\0\1\63\13\0\12\62\7\0\32\63\4\0\1\63"+
    "\1\0\32\63\13\0\131\63\3\0\6\63\2\0\6\63\2\0\6\63"+
    "\2\0\3\63\3\0\2\63\3\0\2\63\22\0\3\64\4\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\1\2\1\3\16\4\1\1\3\4\1\5"+
    "\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1\15"+
    "\1\16\1\1\1\17\1\20\1\1\6\4\1\1\1\21"+
    "\1\3\21\4\1\22\3\4\1\23\1\0\1\24\6\4"+
    "\1\0\1\25\1\0\23\4\1\26\6\4\1\27\1\4"+
    "\1\30\2\4\1\31\3\4\1\32\4\4\1\33\1\4"+
    "\1\0\1\34\21\4\1\35\1\0\1\4\1\36\11\4"+
    "\1\37\5\4\1\0\3\4\1\40\1\41\1\4\1\42"+
    "\1\4\1\43\4\4\1\44\1\4\1\0\1\4\1\45"+
    "\1\46\1\47\1\50\1\4\1\51\1\52\1\53\1\4"+
    "\1\54\1\55\1\56\1\57";

  private static int [] zzUnpackAction() {
    int [] result = new int[189];
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
    "\0\0\0\65\0\152\0\237\0\324\0\u0109\0\u013e\0\u0173"+
    "\0\u01a8\0\u01dd\0\u0212\0\u0247\0\u027c\0\u02b1\0\u02e6\0\u031b"+
    "\0\u0350\0\u0385\0\u03ba\0\u03ef\0\u0424\0\u0459\0\65\0\65"+
    "\0\u048e\0\65\0\65\0\65\0\65\0\65\0\65\0\u04c3"+
    "\0\u04f8\0\65\0\65\0\u052d\0\u0562\0\u0597\0\u05cc\0\u0601"+
    "\0\u0636\0\u066b\0\u06a0\0\u06d5\0\u070a\0\u073f\0\u0774\0\u07a9"+
    "\0\u07de\0\u0813\0\u0848\0\u087d\0\u08b2\0\u08e7\0\u091c\0\u0951"+
    "\0\u0986\0\u09bb\0\u09f0\0\u0a25\0\u0a5a\0\u0a8f\0\u0ac4\0\u0af9"+
    "\0\u0b2e\0\u0b63\0\65\0\u04f8\0\65\0\u0b98\0\u0bcd\0\u0c02"+
    "\0\u0c37\0\u0c6c\0\u0ca1\0\u06a0\0\65\0\u0cd6\0\u0d0b\0\u0d40"+
    "\0\u0d75\0\u0daa\0\u0ddf\0\u0e14\0\u0e49\0\u0e7e\0\u0eb3\0\u0ee8"+
    "\0\u0f1d\0\u0f52\0\u0f87\0\u0fbc\0\u0ff1\0\u1026\0\u105b\0\u1090"+
    "\0\u10c5\0\u0109\0\u10fa\0\u112f\0\u1164\0\u1199\0\u11ce\0\u1203"+
    "\0\u0cd6\0\u1238\0\u0109\0\u126d\0\u12a2\0\u0109\0\u12d7\0\u130c"+
    "\0\u1341\0\u0109\0\u1376\0\u13ab\0\u13e0\0\u1415\0\u0109\0\u144a"+
    "\0\u147f\0\u0109\0\u14b4\0\u14e9\0\u151e\0\u1553\0\u1588\0\u15bd"+
    "\0\u15f2\0\u1627\0\u165c\0\u1691\0\u16c6\0\u16fb\0\u1730\0\u1765"+
    "\0\u179a\0\u17cf\0\u1804\0\u0109\0\u1839\0\u186e\0\u0109\0\u18a3"+
    "\0\u18d8\0\u190d\0\u1942\0\u1977\0\u19ac\0\u19e1\0\u1a16\0\u1a4b"+
    "\0\u0109\0\u1a80\0\u1ab5\0\u1aea\0\u1b1f\0\u1b54\0\u1b89\0\u1bbe"+
    "\0\u1bf3\0\u1c28\0\u0109\0\u0109\0\u1c5d\0\u0109\0\u1c92\0\u0109"+
    "\0\u1cc7\0\u1cfc\0\u1d31\0\u1d66\0\u0109\0\u1d9b\0\u1dd0\0\u1e05"+
    "\0\u0109\0\u0109\0\u0109\0\u0109\0\u1e3a\0\u0109\0\u0109\0\u0109"+
    "\0\u1e6f\0\65\0\u0109\0\u0109\0\u0109";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[189];
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
    "\1\2\1\3\1\4\1\5\2\6\1\7\1\6\1\10"+
    "\1\11\1\12\1\13\1\14\1\15\1\6\1\16\1\17"+
    "\1\20\1\21\4\6\1\22\1\23\1\24\1\25\1\26"+
    "\1\6\1\27\1\30\1\31\1\32\1\33\1\34\1\35"+
    "\1\36\1\37\1\40\1\41\1\42\1\43\1\44\1\45"+
    "\1\46\1\47\1\50\1\51\1\52\1\53\1\54\1\6"+
    "\1\2\66\0\1\3\64\0\1\55\1\4\65\0\1\6"+
    "\1\56\23\6\1\0\4\6\16\0\6\6\1\0\3\6"+
    "\3\0\25\6\1\0\4\6\16\0\6\6\1\0\3\6"+
    "\3\0\12\6\1\57\12\6\1\0\4\6\16\0\6\6"+
    "\1\0\3\6\3\0\11\6\1\60\1\61\12\6\1\0"+
    "\4\6\16\0\6\6\1\0\3\6\3\0\11\6\1\62"+
    "\13\6\1\0\4\6\16\0\6\6\1\0\3\6\3\0"+
    "\2\6\1\63\5\6\1\64\14\6\1\0\4\6\16\0"+
    "\6\6\1\0\3\6\3\0\21\6\1\65\3\6\1\0"+
    "\4\6\16\0\6\6\1\0\3\6\3\0\1\6\1\66"+
    "\2\6\1\67\20\6\1\0\4\6\16\0\6\6\1\0"+
    "\3\6\3\0\16\6\1\70\6\6\1\0\4\6\16\0"+
    "\6\6\1\0\3\6\3\0\4\6\1\71\20\6\1\0"+
    "\4\6\16\0\6\6\1\0\3\6\3\0\7\6\1\72"+
    "\15\6\1\0\4\6\16\0\6\6\1\0\3\6\3\0"+
    "\12\6\1\73\12\6\1\0\4\6\16\0\6\6\1\0"+
    "\3\6\3\0\7\6\1\74\2\6\1\75\12\6\1\0"+
    "\4\6\16\0\6\6\1\0\3\6\3\0\12\6\1\76"+
    "\12\6\1\0\4\6\16\0\6\6\1\0\3\6\62\0"+
    "\1\77\5\0\1\6\1\100\23\6\1\0\4\6\16\0"+
    "\6\6\1\0\3\6\3\0\4\6\1\101\20\6\1\0"+
    "\4\6\16\0\6\6\1\0\3\6\3\0\12\6\1\102"+
    "\12\6\1\0\4\6\16\0\6\6\1\0\3\6\40\0"+
    "\1\103\72\0\1\40\16\0\47\104\1\105\15\104\62\0"+
    "\1\54\5\0\20\6\1\106\4\6\1\0\4\6\16\0"+
    "\6\6\1\0\3\6\3\0\2\6\1\107\22\6\1\0"+
    "\4\6\16\0\6\6\1\0\3\6\3\0\12\6\1\110"+
    "\12\6\1\0\4\6\16\0\6\6\1\0\3\6\3\0"+
    "\1\6\1\111\23\6\1\0\4\6\16\0\6\6\1\0"+
    "\3\6\3\0\6\6\1\112\16\6\1\0\4\6\16\0"+
    "\6\6\1\0\3\6\3\0\1\6\1\113\23\6\1\0"+
    "\4\6\16\0\6\6\1\0\3\6\2\114\1\115\62\114"+
    "\43\0\1\116\16\0\1\54\3\0\1\55\66\0\2\6"+
    "\1\117\22\6\1\0\4\6\16\0\6\6\1\0\3\6"+
    "\3\0\15\6\1\120\7\6\1\0\4\6\16\0\6\6"+
    "\1\0\3\6\3\0\1\6\1\121\23\6\1\0\4\6"+
    "\16\0\6\6\1\0\3\6\3\0\3\6\1\122\21\6"+
    "\1\0\4\6\16\0\6\6\1\0\3\6\3\0\21\6"+
    "\1\123\3\6\1\0\4\6\16\0\6\6\1\0\3\6"+
    "\3\0\6\6\1\124\16\6\1\0\4\6\16\0\6\6"+
    "\1\0\3\6\3\0\5\6\1\125\17\6\1\0\4\6"+
    "\16\0\6\6\1\0\3\6\3\0\20\6\1\126\4\6"+
    "\1\0\4\6\16\0\6\6\1\0\3\6\3\0\1\6"+
    "\1\127\23\6\1\0\4\6\16\0\6\6\1\0\3\6"+
    "\3\0\22\6\1\130\2\6\1\0\4\6\16\0\6\6"+
    "\1\0\3\6\3\0\15\6\1\131\7\6\1\0\4\6"+
    "\16\0\6\6\1\0\3\6\3\0\2\6\1\132\22\6"+
    "\1\0\4\6\16\0\6\6\1\0\3\6\3\0\2\6"+
    "\1\133\22\6\1\0\4\6\16\0\6\6\1\0\3\6"+
    "\3\0\15\6\1\134\7\6\1\0\4\6\16\0\6\6"+
    "\1\0\3\6\3\0\2\6\1\135\22\6\1\0\4\6"+
    "\16\0\6\6\1\0\3\6\3\0\20\6\1\136\4\6"+
    "\1\0\4\6\16\0\6\6\1\0\3\6\3\0\15\6"+
    "\1\137\7\6\1\0\4\6\16\0\6\6\1\0\3\6"+
    "\43\0\1\116\16\0\1\77\5\0\11\6\1\140\13\6"+
    "\1\0\4\6\16\0\6\6\1\0\3\6\3\0\15\6"+
    "\1\141\7\6\1\0\4\6\16\0\6\6\1\0\3\6"+
    "\3\0\11\6\1\142\13\6\1\0\4\6\16\0\6\6"+
    "\1\0\3\6\3\0\7\6\1\143\15\6\1\0\4\6"+
    "\16\0\6\6\1\0\3\6\3\0\6\6\1\144\16\6"+
    "\1\0\4\6\16\0\6\6\1\0\3\6\3\0\6\6"+
    "\1\145\16\6\1\0\4\6\16\0\6\6\1\0\3\6"+
    "\3\0\21\6\1\146\3\6\1\0\4\6\16\0\6\6"+
    "\1\0\3\6\3\0\11\6\1\147\13\6\1\0\4\6"+
    "\16\0\6\6\1\0\3\6\3\0\1\6\1\150\23\6"+
    "\1\0\4\6\16\0\6\6\1\0\3\6\62\0\1\151"+
    "\5\0\3\6\1\152\21\6\1\0\4\6\16\0\6\6"+
    "\1\0\3\6\3\0\4\6\1\153\20\6\1\0\4\6"+
    "\16\0\6\6\1\0\3\6\3\0\5\6\1\154\17\6"+
    "\1\0\4\6\16\0\6\6\1\0\3\6\3\0\13\6"+
    "\1\155\11\6\1\0\4\6\16\0\6\6\1\0\3\6"+
    "\3\0\4\6\1\156\20\6\1\0\4\6\16\0\6\6"+
    "\1\0\3\6\3\0\4\6\1\157\20\6\1\0\4\6"+
    "\16\0\6\6\1\0\3\6\3\0\1\6\1\160\23\6"+
    "\1\0\4\6\16\0\6\6\1\0\3\6\3\0\6\6"+
    "\1\161\16\6\1\0\4\6\16\0\6\6\1\0\3\6"+
    "\3\0\6\6\1\162\16\6\1\0\4\6\16\0\6\6"+
    "\1\0\3\6\3\0\21\6\1\163\3\6\1\0\4\6"+
    "\16\0\6\6\1\0\3\6\3\0\6\6\1\164\16\6"+
    "\1\0\4\6\16\0\6\6\1\0\3\6\3\0\4\6"+
    "\1\165\20\6\1\0\4\6\16\0\6\6\1\0\3\6"+
    "\3\0\14\6\1\166\10\6\1\0\4\6\16\0\6\6"+
    "\1\0\3\6\3\0\4\6\1\167\20\6\1\0\4\6"+
    "\16\0\6\6\1\0\3\6\3\0\12\6\1\170\12\6"+
    "\1\0\4\6\16\0\6\6\1\0\3\6\3\0\15\6"+
    "\1\123\7\6\1\0\4\6\16\0\6\6\1\0\3\6"+
    "\3\0\25\6\1\171\4\6\16\0\6\6\1\0\3\6"+
    "\3\0\23\6\1\172\1\6\1\0\4\6\16\0\6\6"+
    "\1\0\3\6\3\0\1\6\1\173\23\6\1\0\4\6"+
    "\16\0\6\6\1\0\3\6\3\0\12\6\1\174\12\6"+
    "\1\0\4\6\16\0\6\6\1\0\3\6\3\0\4\6"+
    "\1\175\20\6\1\0\4\6\16\0\6\6\1\0\3\6"+
    "\3\0\21\6\1\176\3\6\1\0\4\6\16\0\6\6"+
    "\1\0\3\6\3\0\16\6\1\177\6\6\1\0\4\6"+
    "\16\0\6\6\1\0\3\6\3\0\7\6\1\200\15\6"+
    "\1\0\4\6\16\0\6\6\1\0\3\6\3\0\20\6"+
    "\1\201\4\6\1\0\4\6\16\0\6\6\1\0\3\6"+
    "\3\0\4\6\1\202\20\6\1\0\4\6\16\0\6\6"+
    "\1\0\3\6\3\0\4\6\1\203\20\6\1\0\4\6"+
    "\16\0\6\6\1\0\3\6\3\0\12\6\1\204\12\6"+
    "\1\0\4\6\16\0\6\6\1\0\3\6\3\0\2\6"+
    "\1\205\22\6\1\0\4\6\16\0\6\6\1\0\3\6"+
    "\3\0\11\6\1\206\13\6\1\0\4\6\16\0\6\6"+
    "\1\0\3\6\3\0\7\6\1\207\15\6\1\0\4\6"+
    "\16\0\6\6\1\0\3\6\3\0\7\6\1\210\15\6"+
    "\1\0\4\6\16\0\6\6\1\0\3\6\3\0\11\6"+
    "\1\211\13\6\1\0\4\6\16\0\6\6\1\0\3\6"+
    "\3\0\11\6\1\212\13\6\1\0\4\6\16\0\6\6"+
    "\1\0\3\6\3\0\20\6\1\213\4\6\1\0\4\6"+
    "\16\0\6\6\1\0\3\6\3\0\20\6\1\214\4\6"+
    "\1\0\4\6\16\0\6\6\1\0\3\6\5\0\1\215"+
    "\62\0\21\6\1\216\3\6\1\0\4\6\16\0\6\6"+
    "\1\0\3\6\3\0\15\6\1\217\7\6\1\0\4\6"+
    "\16\0\6\6\1\0\3\6\3\0\14\6\1\220\10\6"+
    "\1\0\4\6\16\0\6\6\1\0\3\6\3\0\11\6"+
    "\1\221\13\6\1\0\4\6\16\0\6\6\1\0\3\6"+
    "\3\0\20\6\1\222\4\6\1\0\4\6\16\0\6\6"+
    "\1\0\3\6\3\0\2\6\1\223\22\6\1\0\4\6"+
    "\16\0\6\6\1\0\3\6\3\0\4\6\1\224\20\6"+
    "\1\0\4\6\16\0\6\6\1\0\3\6\3\0\5\6"+
    "\1\225\17\6\1\0\4\6\16\0\6\6\1\0\3\6"+
    "\3\0\11\6\1\226\13\6\1\0\4\6\16\0\6\6"+
    "\1\0\3\6\3\0\14\6\1\227\10\6\1\0\4\6"+
    "\16\0\6\6\1\0\3\6\3\0\6\6\1\230\16\6"+
    "\1\0\4\6\16\0\6\6\1\0\3\6\3\0\6\6"+
    "\1\231\16\6\1\0\4\6\16\0\6\6\1\0\3\6"+
    "\3\0\5\6\1\232\17\6\1\0\4\6\16\0\6\6"+
    "\1\0\3\6\3\0\11\6\1\233\13\6\1\0\4\6"+
    "\16\0\6\6\1\0\3\6\3\0\12\6\1\234\12\6"+
    "\1\0\4\6\16\0\6\6\1\0\3\6\3\0\7\6"+
    "\1\235\15\6\1\0\4\6\16\0\6\6\1\0\3\6"+
    "\3\0\4\6\1\236\20\6\1\0\4\6\16\0\6\6"+
    "\1\0\3\6\15\0\1\237\52\0\11\6\1\240\13\6"+
    "\1\0\4\6\16\0\6\6\1\0\3\6\3\0\4\6"+
    "\1\241\20\6\1\0\4\6\16\0\6\6\1\0\3\6"+
    "\3\0\12\6\1\242\12\6\1\0\4\6\16\0\6\6"+
    "\1\0\3\6\3\0\4\6\1\243\20\6\1\0\4\6"+
    "\16\0\6\6\1\0\3\6\3\0\14\6\1\244\10\6"+
    "\1\0\4\6\16\0\6\6\1\0\3\6\3\0\12\6"+
    "\1\245\12\6\1\0\4\6\16\0\6\6\1\0\3\6"+
    "\3\0\6\6\1\246\16\6\1\0\4\6\16\0\6\6"+
    "\1\0\3\6\3\0\6\6\1\247\16\6\1\0\4\6"+
    "\16\0\6\6\1\0\3\6\3\0\4\6\1\250\20\6"+
    "\1\0\4\6\16\0\6\6\1\0\3\6\3\0\7\6"+
    "\1\251\15\6\1\0\4\6\16\0\6\6\1\0\3\6"+
    "\3\0\20\6\1\252\4\6\1\0\4\6\16\0\6\6"+
    "\1\0\3\6\3\0\4\6\1\253\20\6\1\0\4\6"+
    "\16\0\6\6\1\0\3\6\3\0\3\6\1\254\21\6"+
    "\1\0\4\6\16\0\6\6\1\0\3\6\3\0\3\6"+
    "\1\255\21\6\1\0\4\6\16\0\6\6\1\0\3\6"+
    "\3\0\6\6\1\256\16\6\1\0\4\6\16\0\6\6"+
    "\1\0\3\6\13\0\1\257\54\0\3\6\1\260\21\6"+
    "\1\0\4\6\16\0\6\6\1\0\3\6\3\0\11\6"+
    "\1\261\13\6\1\0\4\6\16\0\6\6\1\0\3\6"+
    "\3\0\20\6\1\262\4\6\1\0\4\6\16\0\6\6"+
    "\1\0\3\6\3\0\2\6\1\263\22\6\1\0\4\6"+
    "\16\0\6\6\1\0\3\6\3\0\25\6\1\0\3\6"+
    "\1\264\16\0\6\6\1\0\3\6\3\0\1\6\1\265"+
    "\23\6\1\0\4\6\16\0\6\6\1\0\3\6\3\0"+
    "\4\6\1\266\20\6\1\0\4\6\16\0\6\6\1\0"+
    "\3\6\3\0\23\6\1\267\1\6\1\0\4\6\16\0"+
    "\6\6\1\0\3\6\3\0\6\6\1\270\16\6\1\0"+
    "\4\6\16\0\6\6\1\0\3\6\3\0\1\6\1\271"+
    "\23\6\1\0\4\6\16\0\6\6\1\0\3\6\7\0"+
    "\1\272\60\0\4\6\1\273\20\6\1\0\4\6\16\0"+
    "\6\6\1\0\3\6\3\0\2\6\1\274\22\6\1\0"+
    "\4\6\16\0\6\6\1\0\3\6\3\0\2\6\1\275"+
    "\22\6\1\0\4\6\16\0\6\6\1\0\3\6";

  private static int [] zzUnpackTrans() {
    int [] result = new int[7844];
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
    "\1\0\1\11\24\1\2\11\1\1\6\11\2\1\2\11"+
    "\37\1\1\11\1\0\1\11\6\1\1\0\1\11\1\0"+
    "\52\1\1\0\23\1\1\0\21\1\1\0\17\1\1\0"+
    "\12\1\1\11\3\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[189];
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
    while (i < 2238) {
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
        case 7: 
          { return TaraTypes.LEFT_SQUARE;
          }
        case 48: break;
        case 22: 
          { return TaraTypes.VAR;
          }
        case 49: break;
        case 13: 
          { return TaraTypes.COLON;
          }
        case 50: break;
        case 43: 
          { return TaraTypes.ABSTRACT;
          }
        case 51: break;
        case 15: 
          { return TaraTypes.OPEN_AN;
          }
        case 52: break;
        case 41: 
          { return TaraTypes.MULTIPLE;
          }
        case 53: break;
        case 23: 
          { return TaraTypes.DOUBLE_VALUE_KEY;
          }
        case 54: break;
        case 2: 
          { return TokenType.WHITE_SPACE;
          }
        case 55: break;
        case 38: 
          { return TaraTypes.NATURAL_TYPE;
          }
        case 56: break;
        case 6: 
          { return TaraTypes.RIGHT_PARENTHESIS;
          }
        case 57: break;
        case 19: 
          { return TaraTypes.LIST;
          }
        case 58: break;
        case 16: 
          { return TaraTypes.CLOSE_AN;
          }
        case 59: break;
        case 29: 
          { return TaraTypes.FINAL;
          }
        case 60: break;
        case 27: 
          { return TaraTypes.BASE_KEY;
          }
        case 61: break;
        case 11: 
          { return TaraTypes.DOT;
          }
        case 62: break;
        case 46: 
          { return TaraTypes.INTENTION_KEY;
          }
        case 63: break;
        case 33: 
          { return TaraTypes.STRING_TYPE;
          }
        case 64: break;
        case 9: 
          { return openBracket();
          }
        case 65: break;
        case 4: 
          { return TaraTypes.IDENTIFIER_KEY;
          }
        case 66: break;
        case 44: 
          { return TaraTypes.HAS_NAME;
          }
        case 67: break;
        case 5: 
          { return TaraTypes.LEFT_PARENTHESIS;
          }
        case 68: break;
        case 42: 
          { return TaraTypes.REQUIRED;
          }
        case 69: break;
        case 18: 
          { return TaraTypes.NEGATIVE_VALUE_KEY;
          }
        case 70: break;
        case 26: 
          { return TaraTypes.ROOT;
          }
        case 71: break;
        case 21: 
          { return TaraTypes.DOC_LINE;
          }
        case 72: break;
        case 20: 
          { return TaraTypes.STRING_VALUE_KEY;
          }
        case 73: break;
        case 40: 
          { return TaraTypes.PROPERTY;
          }
        case 74: break;
        case 32: 
          { return TaraTypes.DOUBLE_TYPE;
          }
        case 75: break;
        case 8: 
          { return TaraTypes.RIGHT_SQUARE;
          }
        case 76: break;
        case 17: 
          { return TaraTypes.NATURAL_VALUE_KEY;
          }
        case 77: break;
        case 14: 
          { return semicolon();
          }
        case 78: break;
        case 1: 
          { return TokenType.BAD_CHARACTER;
          }
        case 79: break;
        case 45: 
          { return TaraTypes.RESOURCE_KEY;
          }
        case 80: break;
        case 30: 
          { return TaraTypes.ALIAS_TYPE;
          }
        case 81: break;
        case 25: 
          { return TaraTypes.BOOLEAN_VALUE_KEY;
          }
        case 82: break;
        case 31: 
          { return TaraTypes.IMPORT_KEY;
          }
        case 83: break;
        case 39: 
          { return TaraTypes.BOOLEAN_TYPE;
          }
        case 84: break;
        case 24: 
          { return TaraTypes.CASE_KEY;
          }
        case 85: break;
        case 37: 
          { return TaraTypes.INT_TYPE;
          }
        case 86: break;
        case 34: 
          { return TaraTypes.METAIDENTIFIER_KEY;
          }
        case 87: break;
        case 10: 
          { return closeBracket();
          }
        case 88: break;
        case 35: 
          { return TaraTypes.PACKAGE;
          }
        case 89: break;
        case 28: 
          { return TaraTypes.WORD_KEY;
          }
        case 90: break;
        case 36: 
          { return TaraTypes.GENERIC;
          }
        case 91: break;
        case 47: 
          { return TaraTypes.SINGLETON;
          }
        case 92: break;
        case 12: 
          { return TaraTypes.COMMA;
          }
        case 93: break;
        case 3: 
          { return newlineIndent();
          }
        case 94: break;
        default:
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            switch (zzLexicalState) {
            case YYINITIAL: {
              return eof();
            }
            case 190: break;
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
