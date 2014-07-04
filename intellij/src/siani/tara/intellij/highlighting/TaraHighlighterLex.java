/* The following code was generated by JFlex 1.4.3 on 4/07/14 9:16 */

package siani.tara.intellij.highlighting;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import siani.tara.intellij.lang.psi.TaraTypes;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import siani.tara.intellij.project.module.ModuleProvider;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.lang.TreeWrapper;

import java.util.Set;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 4/07/14 9:16 from the specification file
 * <tt>/Users/oroncal/workspace/tara/intellij/src/siani/tara/intellij/highlighting/TaraHighlighterLex.flex</tt>
 */
class TaraHighlighterLex implements FlexLexer {
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
    "\11\51\1\52\1\46\3\0\16\51\4\0\1\52\2\0\1\45\1\50"+
    "\2\0\1\41\1\31\1\32\1\36\1\43\1\37\1\42\1\35\1\0"+
    "\12\47\1\40\1\33\1\0\4\0\2\50\1\1\5\50\1\10\21\50"+
    "\1\33\1\0\1\34\1\0\1\50\1\0\1\13\1\20\1\4\1\14"+
    "\1\5\1\44\1\26\1\23\1\11\2\50\1\15\1\12\1\3\1\2"+
    "\1\6\1\27\1\24\1\16\1\7\1\17\1\25\1\22\1\21\1\30"+
    "\1\50\1\33\1\0\1\33\1\0\41\51\2\0\4\50\4\0\1\50"+
    "\2\0\1\51\7\0\1\50\4\0\1\50\5\0\27\50\1\0\37\50"+
    "\1\0\u01ca\50\4\0\14\50\16\0\5\50\7\0\1\50\1\0\1\50"+
    "\21\0\160\51\5\50\1\0\2\50\2\0\4\50\10\0\1\50\1\0"+
    "\3\50\1\0\1\50\1\0\24\50\1\0\123\50\1\0\213\50\1\0"+
    "\5\51\2\0\236\50\11\0\46\50\2\0\1\50\7\0\47\50\11\0"+
    "\55\51\1\0\1\51\1\0\2\51\1\0\2\51\1\0\1\51\10\0"+
    "\33\50\5\0\3\50\15\0\4\51\7\0\1\50\4\0\13\51\5\0"+
    "\53\50\25\51\12\47\4\0\2\50\1\51\143\50\1\0\1\50\10\51"+
    "\1\0\6\51\2\50\2\51\1\0\4\51\2\50\12\47\3\50\2\0"+
    "\1\50\17\0\1\51\1\50\1\51\36\50\33\51\2\0\131\50\13\51"+
    "\1\50\16\0\12\47\41\50\11\51\2\50\4\0\1\50\5\0\26\50"+
    "\4\51\1\50\11\51\1\50\3\51\1\50\5\51\22\0\31\50\3\51"+
    "\244\0\4\51\66\50\3\51\1\50\22\51\1\50\7\51\12\50\2\51"+
    "\2\0\12\47\1\0\7\50\1\0\7\50\1\0\3\51\1\0\10\50"+
    "\2\0\2\50\2\0\26\50\1\0\7\50\1\0\1\50\3\0\4\50"+
    "\2\0\1\51\1\50\7\51\2\0\2\51\2\0\3\51\1\50\10\0"+
    "\1\51\4\0\2\50\1\0\3\50\2\51\2\0\12\47\4\50\7\0"+
    "\1\50\5\0\3\51\1\0\6\50\4\0\2\50\2\0\26\50\1\0"+
    "\7\50\1\0\2\50\1\0\2\50\1\0\2\50\2\0\1\51\1\0"+
    "\5\51\4\0\2\51\2\0\3\51\3\0\1\51\7\0\4\50\1\0"+
    "\1\50\7\0\12\47\2\51\3\50\1\51\13\0\3\51\1\0\11\50"+
    "\1\0\3\50\1\0\26\50\1\0\7\50\1\0\2\50\1\0\5\50"+
    "\2\0\1\51\1\50\10\51\1\0\3\51\1\0\3\51\2\0\1\50"+
    "\17\0\2\50\2\51\2\0\12\47\1\0\1\50\17\0\3\51\1\0"+
    "\10\50\2\0\2\50\2\0\26\50\1\0\7\50\1\0\2\50\1\0"+
    "\5\50\2\0\1\51\1\50\7\51\2\0\2\51\2\0\3\51\10\0"+
    "\2\51\4\0\2\50\1\0\3\50\2\51\2\0\12\47\1\0\1\50"+
    "\20\0\1\51\1\50\1\0\6\50\3\0\3\50\1\0\4\50\3\0"+
    "\2\50\1\0\1\50\1\0\2\50\3\0\2\50\3\0\3\50\3\0"+
    "\14\50\4\0\5\51\3\0\3\51\1\0\4\51\2\0\1\50\6\0"+
    "\1\51\16\0\12\47\11\0\1\50\7\0\3\51\1\0\10\50\1\0"+
    "\3\50\1\0\27\50\1\0\12\50\1\0\5\50\3\0\1\50\7\51"+
    "\1\0\3\51\1\0\4\51\7\0\2\51\1\0\2\50\6\0\2\50"+
    "\2\51\2\0\12\47\22\0\2\51\1\0\10\50\1\0\3\50\1\0"+
    "\27\50\1\0\12\50\1\0\5\50\2\0\1\51\1\50\7\51\1\0"+
    "\3\51\1\0\4\51\7\0\2\51\7\0\1\50\1\0\2\50\2\51"+
    "\2\0\12\47\1\0\2\50\17\0\2\51\1\0\10\50\1\0\3\50"+
    "\1\0\51\50\2\0\1\50\7\51\1\0\3\51\1\0\4\51\1\50"+
    "\10\0\1\51\10\0\2\50\2\51\2\0\12\47\12\0\6\50\2\0"+
    "\2\51\1\0\22\50\3\0\30\50\1\0\11\50\1\0\1\50\2\0"+
    "\7\50\3\0\1\51\4\0\6\51\1\0\1\51\1\0\10\51\22\0"+
    "\2\51\15\0\60\50\1\51\2\50\7\51\4\0\10\50\10\51\1\0"+
    "\12\47\47\0\2\50\1\0\1\50\2\0\2\50\1\0\1\50\2\0"+
    "\1\50\6\0\4\50\1\0\7\50\1\0\3\50\1\0\1\50\1\0"+
    "\1\50\2\0\2\50\1\0\4\50\1\51\2\50\6\51\1\0\2\51"+
    "\1\50\2\0\5\50\1\0\1\50\1\0\6\51\2\0\12\47\2\0"+
    "\2\50\42\0\1\50\27\0\2\51\6\0\12\47\13\0\1\51\1\0"+
    "\1\51\1\0\1\51\4\0\2\51\10\50\1\0\44\50\4\0\24\51"+
    "\1\0\2\51\5\50\13\51\1\0\44\51\11\0\1\51\71\0\53\50"+
    "\24\51\1\50\12\47\6\0\6\50\4\51\4\50\3\51\1\50\3\51"+
    "\2\50\7\51\3\50\4\51\15\50\14\51\1\50\1\51\12\47\4\51"+
    "\2\0\46\50\12\0\53\50\1\0\1\50\3\0\u0149\50\1\0\4\50"+
    "\2\0\7\50\1\0\1\50\1\0\4\50\2\0\51\50\1\0\4\50"+
    "\2\0\41\50\1\0\4\50\2\0\7\50\1\0\1\50\1\0\4\50"+
    "\2\0\17\50\1\0\71\50\1\0\4\50\2\0\103\50\2\0\3\51"+
    "\40\0\20\50\20\0\125\50\14\0\u026c\50\2\0\21\50\1\0\32\50"+
    "\5\0\113\50\3\0\3\50\17\0\15\50\1\0\4\50\3\51\13\0"+
    "\22\50\3\51\13\0\22\50\2\51\14\0\15\50\1\0\3\50\1\0"+
    "\2\51\14\0\64\50\40\51\3\0\1\50\3\0\2\50\1\51\2\0"+
    "\12\47\41\0\3\51\2\0\12\47\6\0\130\50\10\0\51\50\1\51"+
    "\1\50\5\0\106\50\12\0\35\50\3\0\14\51\4\0\14\51\12\0"+
    "\12\47\36\50\2\0\5\50\13\0\54\50\4\0\21\51\7\50\2\51"+
    "\6\0\12\47\46\0\27\50\5\51\4\0\65\50\12\51\1\0\35\51"+
    "\2\0\1\51\12\47\6\0\12\47\15\0\1\50\130\0\5\51\57\50"+
    "\21\51\7\50\4\0\12\47\21\0\11\51\14\0\3\51\36\50\12\51"+
    "\3\0\2\50\12\47\6\0\46\50\16\51\14\0\44\50\24\51\10\0"+
    "\12\47\3\0\3\50\12\47\44\50\122\0\3\51\1\0\25\51\4\50"+
    "\1\51\4\50\1\51\15\0\300\50\47\51\25\0\4\51\u0116\50\2\0"+
    "\6\50\2\0\46\50\2\0\6\50\2\0\10\50\1\0\1\50\1\0"+
    "\1\50\1\0\1\50\1\0\37\50\2\0\65\50\1\0\7\50\1\0"+
    "\1\50\3\0\3\50\1\0\7\50\3\0\4\50\2\0\6\50\4\0"+
    "\15\50\5\0\3\50\1\0\7\50\16\0\5\51\32\0\5\51\20\0"+
    "\2\50\23\0\1\50\13\0\5\51\5\0\6\51\1\0\1\50\15\0"+
    "\1\50\20\0\15\50\3\0\32\50\26\0\15\51\4\0\1\51\3\0"+
    "\14\51\21\0\1\50\4\0\1\50\2\0\12\50\1\0\1\50\3\0"+
    "\5\50\6\0\1\50\1\0\1\50\1\0\1\50\1\0\4\50\1\0"+
    "\13\50\2\0\4\50\5\0\5\50\4\0\1\50\21\0\51\50\u0a77\0"+
    "\57\50\1\0\57\50\1\0\205\50\6\0\4\50\3\51\16\0\46\50"+
    "\12\0\66\50\11\0\1\50\17\0\1\51\27\50\11\0\7\50\1\0"+
    "\7\50\1\0\7\50\1\0\7\50\1\0\7\50\1\0\7\50\1\0"+
    "\7\50\1\0\7\50\1\0\40\51\57\0\1\50\u01d5\0\3\50\31\0"+
    "\11\50\6\51\1\0\5\50\2\0\5\50\4\0\126\50\2\0\2\51"+
    "\2\0\3\50\1\0\132\50\1\0\4\50\5\0\51\50\3\0\136\50"+
    "\21\0\33\50\65\0\20\50\u0200\0\u19b6\50\112\0\u51cc\50\64\0\u048d\50"+
    "\103\0\56\50\2\0\u010d\50\3\0\20\50\12\47\2\50\24\0\57\50"+
    "\1\51\14\0\2\51\1\0\31\50\10\0\120\50\2\51\45\0\11\50"+
    "\2\0\147\50\2\0\4\50\1\0\2\50\16\0\12\50\120\0\10\50"+
    "\1\51\3\50\1\51\4\50\1\51\27\50\5\51\20\0\1\50\7\0"+
    "\64\50\14\0\2\51\62\50\21\51\13\0\12\47\6\0\22\51\6\50"+
    "\3\0\1\50\4\0\12\47\34\50\10\51\2\0\27\50\15\51\14\0"+
    "\35\50\3\0\4\51\57\50\16\51\16\0\1\50\12\47\46\0\51\50"+
    "\16\51\11\0\3\50\1\51\10\50\2\51\2\0\12\47\6\0\27\50"+
    "\3\0\1\50\1\51\4\0\60\50\1\51\1\50\3\51\2\50\2\51"+
    "\5\50\2\51\1\50\1\51\1\50\30\0\3\50\43\0\6\50\2\0"+
    "\6\50\2\0\6\50\11\0\7\50\1\0\7\50\221\0\43\50\10\51"+
    "\1\0\2\51\2\0\12\47\6\0\u2ba4\50\14\0\27\50\4\0\61\50"+
    "\u2104\0\u012e\50\2\0\76\50\2\0\152\50\46\0\7\50\14\0\5\50"+
    "\5\0\1\50\1\51\12\50\1\0\15\50\1\0\5\50\1\0\1\50"+
    "\1\0\2\50\1\0\2\50\1\0\154\50\41\0\u016b\50\22\0\100\50"+
    "\2\0\66\50\50\0\15\50\3\0\20\51\20\0\7\51\14\0\2\50"+
    "\30\0\3\50\31\0\1\50\6\0\5\50\1\0\207\50\2\0\1\51"+
    "\4\0\1\50\13\0\12\47\7\0\32\50\4\0\1\50\1\0\32\50"+
    "\13\0\131\50\3\0\6\50\2\0\6\50\2\0\6\50\2\0\3\50"+
    "\3\0\2\50\3\0\2\50\22\0\3\51\4\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\23\2\1\3\1\4\1\5\1\6\1\7"+
    "\1\10\1\11\1\12\3\1\1\2\1\1\1\13\1\14"+
    "\1\13\1\2\1\15\10\2\1\16\2\2\1\17\13\2"+
    "\1\0\1\20\1\0\1\21\1\2\1\0\1\22\1\0"+
    "\21\2\1\23\1\2\1\24\5\2\1\25\2\0\1\2"+
    "\1\26\3\2\1\27\4\2\1\30\5\2\1\31\3\2"+
    "\1\32\1\33\1\34\2\2\1\0\1\35\3\2\1\36"+
    "\6\2\1\37\6\2\1\35\11\2\1\40\1\41\1\42"+
    "\3\2\1\43\1\44\2\2\1\45\2\2\1\46\1\2"+
    "\1\47\2\2\1\50\1\51\1\52\2\2\1\53\1\54"+
    "\1\55\1\56";

  private static int [] zzUnpackAction() {
    int [] result = new int[179];
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
    "\0\0\0\53\0\126\0\201\0\254\0\327\0\u0102\0\u012d"+
    "\0\u0158\0\u0183\0\u01ae\0\u01d9\0\u0204\0\u022f\0\u025a\0\u0285"+
    "\0\u02b0\0\u02db\0\u0306\0\u0331\0\u035c\0\53\0\53\0\53"+
    "\0\53\0\53\0\53\0\53\0\53\0\u0387\0\u03b2\0\u03dd"+
    "\0\u0408\0\u0433\0\u045e\0\u0489\0\u04b4\0\u04df\0\u025a\0\u050a"+
    "\0\u0535\0\u0560\0\u058b\0\u05b6\0\u05e1\0\u060c\0\u0637\0\u025a"+
    "\0\u0662\0\u068d\0\u025a\0\u06b8\0\u06e3\0\u070e\0\u0739\0\u0764"+
    "\0\u078f\0\u07ba\0\u07e5\0\u0810\0\u083b\0\u0866\0\u0387\0\53"+
    "\0\u0891\0\u08bc\0\u08e7\0\u0433\0\53\0\u0912\0\u093d\0\u0968"+
    "\0\u0993\0\u09be\0\u09e9\0\u0a14\0\u0a3f\0\u0a6a\0\u0a95\0\u0ac0"+
    "\0\u0aeb\0\u0b16\0\u0b41\0\u0b6c\0\u0b97\0\u0bc2\0\u0bed\0\u025a"+
    "\0\u0c18\0\u025a\0\u0c43\0\u0c6e\0\u0c99\0\u0cc4\0\u0cef\0\u025a"+
    "\0\u0d1a\0\u0d45\0\u0d70\0\u0912\0\u0d9b\0\u0dc6\0\u0df1\0\u025a"+
    "\0\u0e1c\0\u0e47\0\u0e72\0\u0e9d\0\u025a\0\u0ec8\0\u0ef3\0\u0f1e"+
    "\0\u0f49\0\u0f74\0\u025a\0\u0f9f\0\u0fca\0\u0ff5\0\u025a\0\u025a"+
    "\0\u025a\0\u1020\0\u104b\0\u1076\0\u0d45\0\u10a1\0\u10cc\0\u10f7"+
    "\0\u025a\0\u1122\0\u114d\0\u1178\0\u11a3\0\u11ce\0\u11f9\0\u025a"+
    "\0\u1224\0\u124f\0\u127a\0\u12a5\0\u12d0\0\u12fb\0\u1326\0\u1351"+
    "\0\u137c\0\u13a7\0\u13d2\0\u13fd\0\u1428\0\u1453\0\u147e\0\u14a9"+
    "\0\u025a\0\u025a\0\u025a\0\u14d4\0\u14ff\0\u152a\0\u025a\0\u025a"+
    "\0\u1555\0\u1580\0\u025a\0\u15ab\0\u15d6\0\u025a\0\u1601\0\u025a"+
    "\0\u162c\0\u1657\0\u025a\0\u025a\0\u025a\0\u1682\0\u16ad\0\u025a"+
    "\0\u025a\0\u025a\0\u025a";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[179];
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
    "\1\22\1\17\1\23\1\17\1\24\1\25\3\17\1\26"+
    "\1\27\1\30\1\31\1\32\1\33\1\34\1\35\1\36"+
    "\1\37\1\40\1\41\1\42\1\43\1\44\1\17\1\2"+
    "\1\45\54\0\1\17\1\46\26\17\13\0\1\17\2\0"+
    "\3\17\2\0\2\17\1\47\25\17\13\0\1\17\2\0"+
    "\3\17\2\0\12\17\1\50\15\17\13\0\1\17\2\0"+
    "\3\17\2\0\12\17\1\51\15\17\13\0\1\17\2\0"+
    "\3\17\2\0\11\17\1\52\16\17\13\0\1\17\2\0"+
    "\3\17\2\0\23\17\1\53\4\17\13\0\1\17\2\0"+
    "\3\17\2\0\4\17\1\54\16\17\1\55\4\17\13\0"+
    "\1\17\2\0\3\17\2\0\2\17\1\56\25\17\13\0"+
    "\1\17\2\0\3\17\2\0\2\17\1\57\12\17\1\60"+
    "\12\17\13\0\1\17\2\0\3\17\2\0\4\17\1\61"+
    "\23\17\13\0\1\17\2\0\3\17\2\0\14\17\1\62"+
    "\1\63\12\17\13\0\1\17\2\0\3\17\2\0\1\17"+
    "\1\64\10\17\1\65\15\17\13\0\1\17\2\0\3\17"+
    "\2\0\30\17\13\0\1\17\2\0\3\17\2\0\6\17"+
    "\1\66\1\17\1\67\17\17\13\0\1\17\2\0\3\17"+
    "\2\0\15\17\1\70\12\17\13\0\1\17\2\0\3\17"+
    "\2\0\1\17\1\71\26\17\13\0\1\17\2\0\3\17"+
    "\2\0\1\17\1\72\6\17\1\73\17\17\13\0\1\17"+
    "\2\0\3\17\2\0\1\17\1\74\2\17\1\75\23\17"+
    "\13\0\1\17\2\0\3\17\2\0\12\17\1\76\15\17"+
    "\13\0\1\17\2\0\3\17\1\0\41\77\1\100\11\77"+
    "\42\0\1\101\4\0\1\102\52\0\1\44\4\0\12\17"+
    "\1\103\15\17\13\0\1\17\2\0\3\17\1\0\46\104"+
    "\1\105\4\104\46\0\1\43\41\0\1\106\11\0\1\44"+
    "\55\0\1\45\1\0\2\17\1\107\25\17\13\0\1\17"+
    "\2\0\3\17\2\0\6\17\1\110\2\17\1\111\16\17"+
    "\13\0\1\17\2\0\3\17\2\0\15\17\1\112\12\17"+
    "\13\0\1\17\2\0\3\17\2\0\5\17\1\113\22\17"+
    "\13\0\1\17\2\0\3\17\2\0\1\17\1\114\6\17"+
    "\1\115\17\17\13\0\1\17\2\0\3\17\2\0\23\17"+
    "\1\116\4\17\13\0\1\17\2\0\3\17\2\0\16\17"+
    "\1\117\11\17\13\0\1\17\2\0\3\17\2\0\6\17"+
    "\1\120\21\17\13\0\1\17\2\0\3\17\2\0\6\17"+
    "\1\121\21\17\13\0\1\17\2\0\3\17\2\0\6\17"+
    "\1\122\21\17\13\0\1\17\2\0\3\17\2\0\10\17"+
    "\1\123\17\17\13\0\1\17\2\0\3\17\2\0\16\17"+
    "\1\124\11\17\13\0\1\17\2\0\3\17\2\0\6\17"+
    "\1\125\21\17\13\0\1\17\2\0\3\17\2\0\23\17"+
    "\1\126\4\17\13\0\1\17\2\0\3\17\2\0\2\17"+
    "\1\127\25\17\13\0\1\17\2\0\3\17\2\0\4\17"+
    "\1\130\23\17\13\0\1\17\2\0\3\17\2\0\1\17"+
    "\1\131\16\17\1\132\7\17\13\0\1\17\2\0\3\17"+
    "\2\0\23\17\1\133\4\17\13\0\1\17\2\0\3\17"+
    "\2\0\6\17\1\134\21\17\13\0\1\17\2\0\3\17"+
    "\2\0\1\17\1\135\26\17\13\0\1\17\2\0\3\17"+
    "\2\0\15\17\1\136\10\17\1\137\1\17\13\0\1\17"+
    "\2\0\3\17\2\0\23\17\1\140\4\17\13\0\1\17"+
    "\2\0\3\17\1\0\42\141\1\142\10\141\35\0\1\106"+
    "\11\0\1\102\4\0\14\17\1\143\13\17\13\0\1\17"+
    "\2\0\3\17\50\0\1\144\4\0\3\17\1\145\24\17"+
    "\13\0\1\17\2\0\3\17\2\0\16\17\1\146\11\17"+
    "\13\0\1\17\2\0\3\17\2\0\4\17\1\147\23\17"+
    "\13\0\1\17\2\0\3\17\2\0\4\17\1\150\23\17"+
    "\13\0\1\17\2\0\3\17\2\0\6\17\1\151\21\17"+
    "\13\0\1\17\2\0\3\17\2\0\5\17\1\152\22\17"+
    "\13\0\1\17\2\0\3\17\2\0\24\17\1\153\3\17"+
    "\13\0\1\17\2\0\3\17\2\0\11\17\1\154\16\17"+
    "\13\0\1\17\2\0\3\17\2\0\4\17\1\155\23\17"+
    "\13\0\1\17\2\0\3\17\2\0\4\17\1\156\23\17"+
    "\13\0\1\17\2\0\3\17\2\0\4\17\1\157\23\17"+
    "\13\0\1\17\2\0\3\17\2\0\12\17\1\160\15\17"+
    "\13\0\1\17\2\0\3\17\2\0\12\17\1\161\15\17"+
    "\13\0\1\17\2\0\3\17\2\0\17\17\1\162\10\17"+
    "\13\0\1\17\2\0\3\17\2\0\4\17\1\163\23\17"+
    "\13\0\1\17\2\0\3\17\2\0\10\17\1\164\17\17"+
    "\13\0\1\17\2\0\3\17\2\0\25\17\1\165\2\17"+
    "\13\0\1\17\2\0\3\17\2\0\14\17\1\166\13\17"+
    "\13\0\1\17\2\0\3\17\2\0\13\17\1\167\14\17"+
    "\13\0\1\17\2\0\3\17\2\0\22\17\1\170\5\17"+
    "\13\0\1\17\2\0\3\17\2\0\6\17\1\171\21\17"+
    "\13\0\1\17\2\0\3\17\2\0\1\17\1\172\26\17"+
    "\13\0\1\17\2\0\3\17\2\0\16\17\1\173\11\17"+
    "\13\0\1\17\2\0\3\17\1\0\42\141\1\174\52\141"+
    "\1\175\10\141\1\0\15\17\1\117\12\17\13\0\1\17"+
    "\2\0\3\17\2\0\4\17\1\176\23\17\13\0\1\17"+
    "\2\0\3\17\2\0\23\17\1\177\4\17\13\0\1\17"+
    "\2\0\3\17\2\0\12\17\1\200\15\17\13\0\1\17"+
    "\2\0\3\17\2\0\27\17\1\201\13\0\1\17\2\0"+
    "\3\17\2\0\4\17\1\202\23\17\13\0\1\17\2\0"+
    "\3\17\2\0\12\17\1\203\15\17\13\0\1\17\2\0"+
    "\3\17\2\0\10\17\1\204\17\17\13\0\1\17\2\0"+
    "\3\17\2\0\2\17\1\205\25\17\13\0\1\17\2\0"+
    "\3\17\2\0\25\17\1\206\2\17\13\0\1\17\2\0"+
    "\3\17\2\0\11\17\1\207\16\17\13\0\1\17\2\0"+
    "\3\17\2\0\15\17\1\210\12\17\13\0\1\17\2\0"+
    "\3\17\2\0\14\17\1\211\13\17\13\0\1\17\2\0"+
    "\3\17\2\0\2\17\1\212\25\17\13\0\1\17\2\0"+
    "\3\17\2\0\14\17\1\213\13\17\13\0\1\17\2\0"+
    "\3\17\2\0\4\17\1\214\23\17\13\0\1\17\2\0"+
    "\3\17\2\0\16\17\1\215\11\17\13\0\1\17\2\0"+
    "\3\17\2\0\10\17\1\216\17\17\13\0\1\17\2\0"+
    "\3\17\1\0\42\141\1\217\10\141\1\0\5\17\1\220"+
    "\22\17\13\0\1\17\2\0\3\17\2\0\12\17\1\221"+
    "\15\17\13\0\1\17\2\0\3\17\2\0\17\17\1\222"+
    "\10\17\13\0\1\17\2\0\3\17\2\0\23\17\1\223"+
    "\4\17\13\0\1\17\2\0\3\17\2\0\6\17\1\224"+
    "\21\17\13\0\1\17\2\0\3\17\2\0\2\17\1\225"+
    "\25\17\13\0\1\17\2\0\3\17\2\0\6\17\1\226"+
    "\21\17\13\0\1\17\2\0\3\17\2\0\4\17\1\227"+
    "\23\17\13\0\1\17\2\0\3\17\2\0\1\17\1\230"+
    "\26\17\13\0\1\17\2\0\3\17\2\0\4\17\1\231"+
    "\23\17\13\0\1\17\2\0\3\17\2\0\25\17\1\232"+
    "\2\17\13\0\1\17\2\0\3\17\2\0\4\17\1\233"+
    "\23\17\13\0\1\17\2\0\3\17\2\0\12\17\1\234"+
    "\15\17\13\0\1\17\2\0\3\17\2\0\23\17\1\235"+
    "\4\17\13\0\1\17\2\0\3\17\2\0\23\17\1\236"+
    "\4\17\13\0\1\17\2\0\3\17\43\0\1\217\11\0"+
    "\6\17\1\237\21\17\13\0\1\17\2\0\3\17\2\0"+
    "\14\17\1\240\13\17\13\0\1\17\2\0\3\17\2\0"+
    "\14\17\1\241\13\17\13\0\1\17\2\0\3\17\2\0"+
    "\6\17\1\242\21\17\13\0\1\17\2\0\3\17\2\0"+
    "\4\17\1\243\23\17\13\0\1\17\2\0\3\17\2\0"+
    "\12\17\1\244\15\17\13\0\1\17\2\0\3\17\2\0"+
    "\10\17\1\245\17\17\13\0\1\17\2\0\3\17\2\0"+
    "\23\17\1\246\4\17\13\0\1\17\2\0\3\17\2\0"+
    "\13\17\1\247\14\17\13\0\1\17\2\0\3\17\2\0"+
    "\2\17\1\250\25\17\13\0\1\17\2\0\3\17\2\0"+
    "\3\17\1\251\24\17\13\0\1\17\2\0\3\17\2\0"+
    "\4\17\1\252\23\17\13\0\1\17\2\0\3\17\2\0"+
    "\4\17\1\253\23\17\13\0\1\17\2\0\3\17\2\0"+
    "\27\17\1\254\13\0\1\17\2\0\3\17\2\0\14\17"+
    "\1\255\13\17\13\0\1\17\2\0\3\17\2\0\1\17"+
    "\1\256\26\17\13\0\1\17\2\0\3\17\2\0\4\17"+
    "\1\257\23\17\13\0\1\17\2\0\3\17\2\0\4\17"+
    "\1\260\23\17\13\0\1\17\2\0\3\17\2\0\13\17"+
    "\1\261\14\17\13\0\1\17\2\0\3\17\2\0\2\17"+
    "\1\262\25\17\13\0\1\17\2\0\3\17\2\0\14\17"+
    "\1\263\13\17\13\0\1\17\2\0\3\17\1\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[5848];
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
    "\1\0\1\11\23\1\10\11\41\1\1\0\1\11\1\0"+
    "\2\1\1\0\1\11\1\0\32\1\2\0\31\1\1\0"+
    "\67\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[179];
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
	private Set<String> identifiers;


	private IElementType evaluateIdentifier() {
		String identifier = yytext().toString();
		if (identifiers == null) return TaraTypes.IDENTIFIER_KEY;
		return identifiers.contains(identifier) ? TaraTypes.METAIDENTIFIER_KEY : TaraTypes.IDENTIFIER_KEY;
	}

	private void loadHeritage() {
		String[] uses = zzBuffer.toString().split("use");
        String destiny = null;
        for (String use : uses)
            if (use.contains("as metamodel")) {
                destiny = use.split("as metamodel")[0].trim();
                break;
            }
        TreeWrapper heritage = TaraLanguage.getHeritage(destiny);
        if (heritage != null)
            identifiers = heritage.getIdentifiers();
}


  TaraHighlighterLex(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  TaraHighlighterLex(java.io.InputStream in) {
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
        case 29: 
          { return TaraTypes.STRING_MULTILINE_VALUE_KEY;
          }
        case 47: break;
        case 5: 
          { return TaraTypes.LEFT_SQUARE;
          }
        case 48: break;
        case 27: 
          { return TaraTypes.WITH;
          }
        case 49: break;
        case 21: 
          { return TaraTypes.VAR;
          }
        case 50: break;
        case 10: 
          { return TaraTypes.COLON;
          }
        case 51: break;
        case 34: 
          { return TaraTypes.SINGLE;
          }
        case 52: break;
        case 22: 
          { return TaraTypes.DOUBLE_VALUE_KEY;
          }
        case 53: break;
        case 11: 
          { return TokenType.WHITE_SPACE;
          }
        case 54: break;
        case 36: 
          { return TaraTypes.NATURAL_TYPE;
          }
        case 55: break;
        case 4: 
          { return TaraTypes.RIGHT_PARENTHESIS;
          }
        case 56: break;
        case 7: 
          { return TaraTypes.DOT;
          }
        case 57: break;
        case 45: 
          { return TaraTypes.INTENTION_KEY;
          }
        case 58: break;
        case 33: 
          { return TaraTypes.STRING_TYPE;
          }
        case 59: break;
        case 42: 
          { return TaraTypes.TERMINAL;
          }
        case 60: break;
        case 25: 
          { return TaraTypes.DATE_TYPE;
          }
        case 61: break;
        case 46: 
          { return TaraTypes.METAMODEL;
          }
        case 62: break;
        case 30: 
          { return TaraTypes.EMPTY_REF;
          }
        case 63: break;
        case 3: 
          { return TaraTypes.LEFT_PARENTHESIS;
          }
        case 64: break;
        case 44: 
          { return TaraTypes.REQUIRED;
          }
        case 65: break;
        case 37: 
          { return TaraTypes.PRIVATE;
          }
        case 66: break;
        case 17: 
          { return TaraTypes.NEGATIVE_VALUE_KEY;
          }
        case 67: break;
        case 28: 
          { return TaraTypes.ROOT;
          }
        case 68: break;
        case 18: 
          { return TaraTypes.DOC_LINE;
          }
        case 69: break;
        case 16: 
          { return TaraTypes.STRING_VALUE_KEY;
          }
        case 70: break;
        case 41: 
          { return TaraTypes.PROPERTY;
          }
        case 71: break;
        case 2: 
          { return evaluateIdentifier();
          }
        case 72: break;
        case 8: 
          { return TaraTypes.STAR;
          }
        case 73: break;
        case 32: 
          { return TaraTypes.DOUBLE_TYPE;
          }
        case 74: break;
        case 6: 
          { return TaraTypes.RIGHT_SQUARE;
          }
        case 75: break;
        case 12: 
          { return TaraTypes.NATURAL_VALUE_KEY;
          }
        case 76: break;
        case 1: 
          { return TokenType.BAD_CHARACTER;
          }
        case 77: break;
        case 43: 
          { return TaraTypes.RESOURCE_KEY;
          }
        case 78: break;
        case 19: 
          { return TaraTypes.USE_KEY;
          }
        case 79: break;
        case 31: 
          { return TaraTypes.ALIAS_TYPE;
          }
        case 80: break;
        case 24: 
          { return TaraTypes.BOOLEAN_VALUE_KEY;
          }
        case 81: break;
        case 40: 
          { return TaraTypes.NAMEABLE;
          }
        case 82: break;
        case 15: 
          { return TaraTypes.AS;
          }
        case 83: break;
        case 13: 
          { return TaraTypes.ON;
          }
        case 84: break;
        case 39: 
          { return TaraTypes.BOOLEAN_TYPE;
          }
        case 85: break;
        case 23: 
          { return TaraTypes.CASE_KEY;
          }
        case 86: break;
        case 14: 
          { return TaraTypes.IS;
          }
        case 87: break;
        case 38: 
          { return TaraTypes.INT_TYPE;
          }
        case 88: break;
        case 35: 
          { return TaraTypes.METAIDENTIFIER_KEY;
          }
        case 89: break;
        case 20: 
          { loadHeritage();
										return TaraTypes.BOX_KEY;
          }
        case 90: break;
        case 26: 
          { return TaraTypes.WORD_KEY;
          }
        case 91: break;
        case 9: 
          { return TaraTypes.COMMA;
          }
        case 92: break;
        default:
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            return null;
          }
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
