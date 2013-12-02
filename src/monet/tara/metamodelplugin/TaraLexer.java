/* The following code was generated by JFlex 1.4.3 on 2/12/13 14:10 */

package monet.tara.metamodelplugin;

import com.intellij.lexer.FlexLexer;
import java.util.Stack;
import com.intellij.psi.tree.IElementType;
import monet.tara.metamodelplugin.psi.TaraTypes;
import com.intellij.psi.TokenType;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 2/12/13 14:10 from the specification file
 * <tt>/home/oroncal/workspace/idea/TaraMetamodelPlugin/src/monet/tara/metamodelplugin/Tara.flex</tt>
 */
class TaraLexer implements FlexLexer {
  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;
  public static final int WAITING = 2;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0,  0,  1, 1
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\41\1\3\1\2\1\0\1\4\1\1\16\41\4\0\1\4\1\5"+
    "\1\31\1\5\1\40\7\0\1\0\1\0\1\30\1\0\12\37\3\0"+
    "\1\32\2\0\1\33\32\40\1\0\1\0\1\0\1\0\1\40\1\0"+
    "\1\11\1\26\1\15\1\25\1\13\1\14\1\27\1\10\1\6\1\35"+
    "\1\40\1\23\1\22\1\17\1\16\1\20\1\40\1\12\1\7\1\21"+
    "\1\24\1\36\1\40\1\34\2\40\4\0\41\41\2\0\4\40\4\0"+
    "\1\40\2\0\1\41\7\0\1\40\4\0\1\40\5\0\27\40\1\0"+
    "\37\40\1\0\u01ca\40\4\0\14\40\16\0\5\40\7\0\1\40\1\0"+
    "\1\40\21\0\160\41\5\40\1\0\2\40\2\0\4\40\10\0\1\40"+
    "\1\0\3\40\1\0\1\40\1\0\24\40\1\0\123\40\1\0\213\40"+
    "\1\0\5\41\2\0\236\40\11\0\46\40\2\0\1\40\7\0\47\40"+
    "\11\0\55\41\1\0\1\41\1\0\2\41\1\0\2\41\1\0\1\41"+
    "\10\0\33\40\5\0\3\40\15\0\4\41\7\0\1\40\4\0\13\41"+
    "\5\0\53\40\25\41\12\37\4\0\2\40\1\41\143\40\1\0\1\40"+
    "\10\41\1\0\6\41\2\40\2\41\1\0\4\41\2\40\12\37\3\40"+
    "\2\0\1\40\17\0\1\41\1\40\1\41\36\40\33\41\2\0\131\40"+
    "\13\41\1\40\16\0\12\37\41\40\11\41\2\40\4\0\1\40\5\0"+
    "\26\40\4\41\1\40\11\41\1\40\3\41\1\40\5\41\22\0\31\40"+
    "\3\41\244\0\4\41\66\40\3\41\1\40\22\41\1\40\7\41\12\40"+
    "\2\41\2\0\12\37\1\0\7\40\1\0\7\40\1\0\3\41\1\0"+
    "\10\40\2\0\2\40\2\0\26\40\1\0\7\40\1\0\1\40\3\0"+
    "\4\40\2\0\1\41\1\40\7\41\2\0\2\41\2\0\3\41\1\40"+
    "\10\0\1\41\4\0\2\40\1\0\3\40\2\41\2\0\12\37\4\40"+
    "\7\0\1\40\5\0\3\41\1\0\6\40\4\0\2\40\2\0\26\40"+
    "\1\0\7\40\1\0\2\40\1\0\2\40\1\0\2\40\2\0\1\41"+
    "\1\0\5\41\4\0\2\41\2\0\3\41\3\0\1\41\7\0\4\40"+
    "\1\0\1\40\7\0\12\37\2\41\3\40\1\41\13\0\3\41\1\0"+
    "\11\40\1\0\3\40\1\0\26\40\1\0\7\40\1\0\2\40\1\0"+
    "\5\40\2\0\1\41\1\40\10\41\1\0\3\41\1\0\3\41\2\0"+
    "\1\40\17\0\2\40\2\41\2\0\12\37\1\0\1\40\17\0\3\41"+
    "\1\0\10\40\2\0\2\40\2\0\26\40\1\0\7\40\1\0\2\40"+
    "\1\0\5\40\2\0\1\41\1\40\7\41\2\0\2\41\2\0\3\41"+
    "\10\0\2\41\4\0\2\40\1\0\3\40\2\41\2\0\12\37\1\0"+
    "\1\40\20\0\1\41\1\40\1\0\6\40\3\0\3\40\1\0\4\40"+
    "\3\0\2\40\1\0\1\40\1\0\2\40\3\0\2\40\3\0\3\40"+
    "\3\0\14\40\4\0\5\41\3\0\3\41\1\0\4\41\2\0\1\40"+
    "\6\0\1\41\16\0\12\37\11\0\1\40\7\0\3\41\1\0\10\40"+
    "\1\0\3\40\1\0\27\40\1\0\12\40\1\0\5\40\3\0\1\40"+
    "\7\41\1\0\3\41\1\0\4\41\7\0\2\41\1\0\2\40\6\0"+
    "\2\40\2\41\2\0\12\37\22\0\2\41\1\0\10\40\1\0\3\40"+
    "\1\0\27\40\1\0\12\40\1\0\5\40\2\0\1\41\1\40\7\41"+
    "\1\0\3\41\1\0\4\41\7\0\2\41\7\0\1\40\1\0\2\40"+
    "\2\41\2\0\12\37\1\0\2\40\17\0\2\41\1\0\10\40\1\0"+
    "\3\40\1\0\51\40\2\0\1\40\7\41\1\0\3\41\1\0\4\41"+
    "\1\40\10\0\1\41\10\0\2\40\2\41\2\0\12\37\12\0\6\40"+
    "\2\0\2\41\1\0\22\40\3\0\30\40\1\0\11\40\1\0\1\40"+
    "\2\0\7\40\3\0\1\41\4\0\6\41\1\0\1\41\1\0\10\41"+
    "\22\0\2\41\15\0\60\40\1\41\2\40\7\41\4\0\10\40\10\41"+
    "\1\0\12\37\47\0\2\40\1\0\1\40\2\0\2\40\1\0\1\40"+
    "\2\0\1\40\6\0\4\40\1\0\7\40\1\0\3\40\1\0\1\40"+
    "\1\0\1\40\2\0\2\40\1\0\4\40\1\41\2\40\6\41\1\0"+
    "\2\41\1\40\2\0\5\40\1\0\1\40\1\0\6\41\2\0\12\37"+
    "\2\0\2\40\42\0\1\40\27\0\2\41\6\0\12\37\13\0\1\41"+
    "\1\0\1\41\1\0\1\41\4\0\2\41\10\40\1\0\44\40\4\0"+
    "\24\41\1\0\2\41\5\40\13\41\1\0\44\41\11\0\1\41\71\0"+
    "\53\40\24\41\1\40\12\37\6\0\6\40\4\41\4\40\3\41\1\40"+
    "\3\41\2\40\7\41\3\40\4\41\15\40\14\41\1\40\1\41\12\37"+
    "\4\41\2\0\46\40\12\0\53\40\1\0\1\40\3\0\u0149\40\1\0"+
    "\4\40\2\0\7\40\1\0\1\40\1\0\4\40\2\0\51\40\1\0"+
    "\4\40\2\0\41\40\1\0\4\40\2\0\7\40\1\0\1\40\1\0"+
    "\4\40\2\0\17\40\1\0\71\40\1\0\4\40\2\0\103\40\2\0"+
    "\3\41\40\0\20\40\20\0\125\40\14\0\u026c\40\2\0\21\40\1\0"+
    "\32\40\5\0\113\40\3\0\3\40\17\0\15\40\1\0\4\40\3\41"+
    "\13\0\22\40\3\41\13\0\22\40\2\41\14\0\15\40\1\0\3\40"+
    "\1\0\2\41\14\0\64\40\40\41\3\0\1\40\3\0\2\40\1\41"+
    "\2\0\12\37\41\0\3\41\2\0\12\37\6\0\130\40\10\0\51\40"+
    "\1\41\1\40\5\0\106\40\12\0\35\40\3\0\14\41\4\0\14\41"+
    "\12\0\12\37\36\40\2\0\5\40\13\0\54\40\4\0\21\41\7\40"+
    "\2\41\6\0\12\37\46\0\27\40\5\41\4\0\65\40\12\41\1\0"+
    "\35\41\2\0\1\41\12\37\6\0\12\37\15\0\1\40\130\0\5\41"+
    "\57\40\21\41\7\40\4\0\12\37\21\0\11\41\14\0\3\41\36\40"+
    "\12\41\3\0\2\40\12\37\6\0\46\40\16\41\14\0\44\40\24\41"+
    "\10\0\12\37\3\0\3\40\12\37\44\40\122\0\3\41\1\0\25\41"+
    "\4\40\1\41\4\40\1\41\15\0\300\40\47\41\25\0\4\41\u0116\40"+
    "\2\0\6\40\2\0\46\40\2\0\6\40\2\0\10\40\1\0\1\40"+
    "\1\0\1\40\1\0\1\40\1\0\37\40\2\0\65\40\1\0\7\40"+
    "\1\0\1\40\3\0\3\40\1\0\7\40\3\0\4\40\2\0\6\40"+
    "\4\0\15\40\5\0\3\40\1\0\7\40\16\0\5\41\32\0\5\41"+
    "\20\0\2\40\23\0\1\40\13\0\5\41\5\0\6\41\1\0\1\40"+
    "\15\0\1\40\20\0\15\40\3\0\32\40\26\0\15\41\4\0\1\41"+
    "\3\0\14\41\21\0\1\40\4\0\1\40\2\0\12\40\1\0\1\40"+
    "\3\0\5\40\6\0\1\40\1\0\1\40\1\0\1\40\1\0\4\40"+
    "\1\0\13\40\2\0\4\40\5\0\5\40\4\0\1\40\21\0\51\40"+
    "\u0a77\0\57\40\1\0\57\40\1\0\205\40\6\0\4\40\3\41\16\0"+
    "\46\40\12\0\66\40\11\0\1\40\17\0\1\41\27\40\11\0\7\40"+
    "\1\0\7\40\1\0\7\40\1\0\7\40\1\0\7\40\1\0\7\40"+
    "\1\0\7\40\1\0\7\40\1\0\40\41\57\0\1\40\u01d5\0\3\40"+
    "\31\0\11\40\6\41\1\0\5\40\2\0\5\40\4\0\126\40\2\0"+
    "\2\41\2\0\3\40\1\0\132\40\1\0\4\40\5\0\51\40\3\0"+
    "\136\40\21\0\33\40\65\0\20\40\u0200\0\u19b6\40\112\0\u51cc\40\64\0"+
    "\u048d\40\103\0\56\40\2\0\u010d\40\3\0\20\40\12\37\2\40\24\0"+
    "\57\40\1\41\14\0\2\41\1\0\31\40\10\0\120\40\2\41\45\0"+
    "\11\40\2\0\147\40\2\0\4\40\1\0\2\40\16\0\12\40\120\0"+
    "\10\40\1\41\3\40\1\41\4\40\1\41\27\40\5\41\20\0\1\40"+
    "\7\0\64\40\14\0\2\41\62\40\21\41\13\0\12\37\6\0\22\41"+
    "\6\40\3\0\1\40\4\0\12\37\34\40\10\41\2\0\27\40\15\41"+
    "\14\0\35\40\3\0\4\41\57\40\16\41\16\0\1\40\12\37\46\0"+
    "\51\40\16\41\11\0\3\40\1\41\10\40\2\41\2\0\12\37\6\0"+
    "\27\40\3\0\1\40\1\41\4\0\60\40\1\41\1\40\3\41\2\40"+
    "\2\41\5\40\2\41\1\40\1\41\1\40\30\0\3\40\43\0\6\40"+
    "\2\0\6\40\2\0\6\40\11\0\7\40\1\0\7\40\221\0\43\40"+
    "\10\41\1\0\2\41\2\0\12\37\6\0\u2ba4\40\14\0\27\40\4\0"+
    "\61\40\u2104\0\u012e\40\2\0\76\40\2\0\152\40\46\0\7\40\14\0"+
    "\5\40\5\0\1\40\1\41\12\40\1\0\15\40\1\0\5\40\1\0"+
    "\1\40\1\0\2\40\1\0\2\40\1\0\154\40\41\0\u016b\40\22\0"+
    "\100\40\2\0\66\40\50\0\15\40\3\0\20\41\20\0\7\41\14\0"+
    "\2\40\30\0\3\40\31\0\1\40\6\0\5\40\1\0\207\40\2\0"+
    "\1\41\4\0\1\40\13\0\12\37\7\0\32\40\4\0\1\40\1\0"+
    "\32\40\13\0\131\40\3\0\6\40\2\0\6\40\2\0\6\40\2\0"+
    "\3\40\3\0\2\40\3\0\2\40\22\0\3\41\4\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\2\0\1\1\1\2\1\3\6\1\1\4\1\2\1\4"+
    "\1\5\1\6\4\7\1\1\1\10\1\1\1\11\1\0"+
    "\1\12\5\0\1\13\1\14\2\7\1\0\1\15\5\0"+
    "\1\16\1\0\1\17\1\20\2\0\2\7\4\0\1\21"+
    "\3\0\2\7\7\0\1\7\1\22\1\0\1\23\2\0"+
    "\1\24\1\0\1\25\1\7\3\0\1\26\1\7\10\0"+
    "\1\27\3\0\1\30\1\31";

  private static int [] zzUnpackAction() {
    int [] result = new int[96];
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
    "\0\0\0\42\0\104\0\104\0\146\0\210\0\252\0\314"+
    "\0\356\0\u0110\0\u0132\0\104\0\u0154\0\u0176\0\u0176\0\u0198"+
    "\0\u01ba\0\u01dc\0\u01fe\0\u0220\0\u0242\0\104\0\u0264\0\u0286"+
    "\0\u02a8\0\104\0\u02ca\0\u02ec\0\u030e\0\u0330\0\u0352\0\104"+
    "\0\u01dc\0\u0374\0\u0396\0\u0242\0\104\0\u03b8\0\u03da\0\u03fc"+
    "\0\u041e\0\u0440\0\104\0\u0462\0\104\0\104\0\u0484\0\u04a6"+
    "\0\u04c8\0\u04ea\0\u050c\0\u052e\0\u0550\0\u0572\0\u0440\0\u0594"+
    "\0\u05b6\0\u05d8\0\u05fa\0\u061c\0\u063e\0\u0660\0\u0682\0\u06a4"+
    "\0\u06c6\0\u06e8\0\u070a\0\u072c\0\u01dc\0\u074e\0\104\0\u0770"+
    "\0\u0792\0\104\0\u07b4\0\104\0\u07d6\0\u07f8\0\u081a\0\u083c"+
    "\0\104\0\u085e\0\u0880\0\u08a2\0\u08c4\0\u08e6\0\u0908\0\u092a"+
    "\0\u094c\0\u096e\0\104\0\u0990\0\u09b2\0\u09d4\0\104\0\104";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[96];
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
    "\1\3\2\4\2\5\1\3\1\6\1\7\1\10\1\3"+
    "\1\11\2\3\1\12\7\3\1\13\15\3\1\14\1\15"+
    "\1\16\1\17\1\20\1\21\2\22\1\23\2\22\1\24"+
    "\13\22\1\3\1\25\1\26\1\27\3\22\1\30\1\22"+
    "\1\3\45\0\2\5\54\0\1\31\5\0\1\32\35\0"+
    "\1\33\31\0\1\34\43\0\1\35\44\0\1\36\41\0"+
    "\1\37\26\0\1\40\41\0\2\17\35\0\1\20\2\0"+
    "\37\20\6\0\1\22\1\41\20\22\4\0\6\22\6\0"+
    "\22\22\4\0\6\22\6\0\20\22\1\42\1\22\4\0"+
    "\6\22\6\0\1\43\21\22\4\0\6\22\6\0\22\44"+
    "\1\0\1\45\2\0\6\44\11\0\1\46\1\47\1\50"+
    "\3\0\1\51\52\0\1\52\6\0\1\30\23\0\1\53"+
    "\32\0\1\54\36\0\1\55\46\0\1\56\44\0\1\57"+
    "\46\0\1\60\23\0\1\22\1\61\20\22\4\0\6\22"+
    "\6\0\11\22\1\62\10\22\4\0\6\22\15\0\1\63"+
    "\42\0\1\64\57\0\1\65\16\0\1\66\67\0\1\67"+
    "\10\0\1\70\50\0\1\71\52\0\1\72\21\0\13\22"+
    "\1\73\6\22\4\0\6\22\6\0\3\22\1\74\16\22"+
    "\4\0\6\22\21\0\1\75\36\0\1\76\44\0\1\77"+
    "\42\0\1\100\36\0\1\101\35\0\1\102\51\0\1\103"+
    "\24\0\4\22\1\104\15\22\4\0\6\22\6\0\15\22"+
    "\1\105\4\22\4\0\6\22\6\0\1\106\54\0\1\107"+
    "\33\0\1\110\41\0\1\111\55\0\1\112\32\0\1\113"+
    "\34\0\1\114\34\0\3\22\1\115\16\22\4\0\6\22"+
    "\16\0\1\116\42\0\1\117\33\0\1\120\51\0\1\121"+
    "\26\0\7\22\1\122\12\22\4\0\6\22\17\0\1\123"+
    "\31\0\1\124\60\0\1\125\21\0\13\22\1\105\6\22"+
    "\4\0\6\22\32\0\1\126\15\0\1\127\56\0\1\130"+
    "\53\0\1\131\32\0\1\132\26\0\1\133\37\0\1\134"+
    "\53\0\1\135\54\0\1\136\16\0\1\137\37\0\1\140"+
    "\30\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[2550];
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
    "\2\0\2\11\7\1\1\11\11\1\1\11\2\1\1\0"+
    "\1\11\5\0\1\11\3\1\1\0\1\11\5\0\1\11"+
    "\1\0\2\11\2\0\2\1\4\0\1\1\3\0\2\1"+
    "\7\0\2\1\1\0\1\11\2\0\1\11\1\0\1\11"+
    "\1\1\3\0\1\11\1\1\10\0\1\11\3\0\2\11";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[96];
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
	static Stack<Integer> stack = new Stack<java.lang.Integer>();
	{
		stack.push(0);
	}

	private void indent(){
    	stack.push(stack.peek()+1);
    }

	private void deindent(){
        stack.pop();
    }

	private int getLevel(CharSequence yytext){
		int level = 0;
		int ws = 0;
		for (int i = 0; i < yytext.length(); i++){
			if (yytext.charAt(i) == '\t') ws += 4;
			if (yytext.charAt(i) == ' ') ws++;
		}
		return level + ws/4;
	}

	private IElementType indentDeindent(int currentLevel){
		if (currentLevel > stack.peek()){
			indent();
			return TaraTypes.INDENT;
		} else if (currentLevel < stack.peek()){
			deindent();
            return TaraTypes.DEINDENT;
		} else
			return TaraTypes.INLINE;
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
    while (i < 2196) {
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
        case 23: 
          { return TaraTypes.NAMEABLE ;
          }
        case 26: break;
        case 7: 
          { return TaraTypes.IDENTIFIER;
          }
        case 27: break;
        case 18: 
          { return TaraTypes.MODIFIERS;
          }
        case 28: break;
        case 12: 
          { return TaraTypes.IS;
          }
        case 29: break;
        case 3: 
          { return indentDeindent(getLevel(yytext()));
          }
        case 30: break;
        case 1: 
          { return TokenType.BAD_CHARACTER;
          }
        case 31: break;
        case 2: 
          { yybegin(YYINITIAL); return TaraTypes.CRLF;
          }
        case 32: break;
        case 9: 
          { return TaraTypes.INT;
          }
        case 33: break;
        case 15: 
          { yybegin(WAITING); return TaraTypes.HAS;
          }
        case 34: break;
        case 13: 
          { return TaraTypes.STRING;
          }
        case 35: break;
        case 4: 
          { return TaraTypes.INDENT;
          }
        case 36: break;
        case 22: 
          { yybegin(WAITING); indentDeindent(getLevel(yytext())); return TaraTypes.CONCEPT;
          }
        case 37: break;
        case 11: 
          { yybegin(YYINITIAL); return TokenType.NEW_LINE_INDENT;
          }
        case 38: break;
        case 21: 
          { yybegin(WAITING); return TaraTypes.DOUBLE_TYPE;
          }
        case 39: break;
        case 5: 
          { return TokenType.WHITE_SPACE;
          }
        case 40: break;
        case 6: 
          { return TaraTypes.COMMENT;
          }
        case 41: break;
        case 25: 
          { return TaraTypes.ACTION;
          }
        case 42: break;
        case 16: 
          { yybegin(WAITING); return TaraTypes.REF;
          }
        case 43: break;
        case 14: 
          { yybegin(WAITING); return TaraTypes.INT_TYPE;
          }
        case 44: break;
        case 17: 
          { return TaraTypes.DOUBLE;
          }
        case 45: break;
        case 24: 
          { return TaraTypes.EXTENSIBLE;
          }
        case 46: break;
        case 20: 
          { yybegin(WAITING); return TaraTypes.STRING_TYPE;
          }
        case 47: break;
        case 10: 
          { yybegin(WAITING); return TaraTypes.ID_TYPE;
          }
        case 48: break;
        case 8: 
          { return TaraTypes.ASSIGN;
          }
        case 49: break;
        case 19: 
          { return TaraTypes.AT_ROOT;
          }
        case 50: break;
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
