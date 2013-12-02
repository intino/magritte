// This is a generated file. Not intended for manual editing.
package monet.tara.metamodelplugin;

import org.jetbrains.annotations.*;
import com.intellij.lang.LighterASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import com.intellij.openapi.diagnostic.Logger;
import static monet.tara.metamodelplugin.psi.TaraTypes.*;
import static monet.tara.metamodelplugin.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class TaraParser implements PsiParser {

  public static Logger LOG_ = Logger.getInstance("monet.tara.metamodelplugin.TaraParser");

  @NotNull
  public ASTNode parse(IElementType root_, PsiBuilder builder_) {
    int level_ = 0;
    boolean result_;
    builder_ = adapt_builder_(root_, builder_, this);
    if (root_ == ANNOTATION) {
      result_ = ANNOTATION(builder_, level_ + 1);
    }
    else if (root_ == PRIMITIVE) {
      result_ = PRIMITIVE(builder_, level_ + 1);
    }
    else if (root_ == PRIMITIVE_TYPE) {
      result_ = PRIMITIVE_TYPE(builder_, level_ + 1);
    }
    else if (root_ == ATTR_SIBLINGS) {
      result_ = attrSiblings(builder_, level_ + 1);
    }
    else if (root_ == CHILDREN_BODY) {
      result_ = childrenBody(builder_, level_ + 1);
    }
    else if (root_ == CONCEPT_BODY) {
      result_ = conceptBody(builder_, level_ + 1);
    }
    else if (root_ == CONCEPT_DEFINITION) {
      result_ = conceptDefinition(builder_, level_ + 1);
    }
    else if (root_ == PROPERTIES_BODY) {
      result_ = propertiesBody(builder_, level_ + 1);
    }
    else if (root_ == REFERENCES_BODY) {
      result_ = referencesBody(builder_, level_ + 1);
    }
    else {
      Marker marker_ = builder_.mark();
      enterErrorRecordingSection(builder_, level_, _SECTION_RECOVER_, null);
      result_ = parse_root_(root_, builder_, level_);
      exitErrorRecordingSection(builder_, level_, result_, true, _SECTION_RECOVER_, TOKEN_ADVANCER);
      marker_.done(root_);
    }
    return builder_.getTreeBuilt();
  }

  protected boolean parse_root_(final IElementType root_, final PsiBuilder builder_, final int level_) {
    return root(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // NAMEABLE|AT_ROOT|EXTENSIBLE|ACTION
  public static boolean ANNOTATION(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ANNOTATION")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<annotation>");
    result_ = consumeToken(builder_, NAMEABLE);
    if (!result_) result_ = consumeToken(builder_, AT_ROOT);
    if (!result_) result_ = consumeToken(builder_, EXTENSIBLE);
    if (!result_) result_ = consumeToken(builder_, ACTION);
    if (result_) {
      marker_.done(ANNOTATION);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  /* ********************************************************** */
  // INT|DOUBLE|STRING
  public static boolean PRIMITIVE(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PRIMITIVE")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<primitive>");
    result_ = consumeToken(builder_, INT);
    if (!result_) result_ = consumeToken(builder_, DOUBLE);
    if (!result_) result_ = consumeToken(builder_, STRING);
    if (result_) {
      marker_.done(PRIMITIVE);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  /* ********************************************************** */
  // ID_TYPE|INT_TYPE|STRING_TYPE|DOUBLE_TYPE
  public static boolean PRIMITIVE_TYPE(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PRIMITIVE_TYPE")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<primitive type>");
    result_ = consumeToken(builder_, ID_TYPE);
    if (!result_) result_ = consumeToken(builder_, INT_TYPE);
    if (!result_) result_ = consumeToken(builder_, STRING_TYPE);
    if (!result_) result_ = consumeToken(builder_, DOUBLE_TYPE);
    if (result_) {
      marker_.done(PRIMITIVE_TYPE);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  /* ********************************************************** */
  // INLINE (propertiesBody|childrenBody|referencesBody) CRLF
  public static boolean attrSiblings(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attrSiblings")) return false;
    if (!nextTokenIs(builder_, INLINE)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, INLINE);
    result_ = result_ && attrSiblings_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, CRLF);
    if (result_) {
      marker_.done(ATTR_SIBLINGS);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  // propertiesBody|childrenBody|referencesBody
  private static boolean attrSiblings_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attrSiblings_1")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = propertiesBody(builder_, level_ + 1);
    if (!result_) result_ = childrenBody(builder_, level_ + 1);
    if (!result_) result_ = referencesBody(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // HAS IDENTIFIER
  public static boolean childrenBody(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "childrenBody")) return false;
    if (!nextTokenIs(builder_, HAS)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeTokens(builder_, 0, HAS, IDENTIFIER);
    if (result_) {
      marker_.done(CHILDREN_BODY);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  /* ********************************************************** */
  // (propertiesBody|childrenBody|referencesBody) CRLF attrSiblings*
  public static boolean conceptBody(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "conceptBody")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<concept body>");
    result_ = conceptBody_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, CRLF);
    result_ = result_ && conceptBody_2(builder_, level_ + 1);
    if (result_) {
      marker_.done(CONCEPT_BODY);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  // propertiesBody|childrenBody|referencesBody
  private static boolean conceptBody_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "conceptBody_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = propertiesBody(builder_, level_ + 1);
    if (!result_) result_ = childrenBody(builder_, level_ + 1);
    if (!result_) result_ = referencesBody(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // attrSiblings*
  private static boolean conceptBody_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "conceptBody_2")) return false;
    int offset_ = builder_.getCurrentOffset();
    while (true) {
      if (!attrSiblings(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "conceptBody_2");
        break;
      }
      offset_ = next_offset_;
    }
    return true;
  }

  /* ********************************************************** */
  // DEINDENT* CONCEPT MODIFIERS? IDENTIFIER (IS IDENTIFIER)? ANNOTATION* ((NEW_LINE_INDENT conceptBody) | CRLF)
  public static boolean conceptDefinition(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "conceptDefinition")) return false;
    if (!nextTokenIs(builder_, CONCEPT) && !nextTokenIs(builder_, DEINDENT)
        && replaceVariants(builder_, 2, "<concept definition>")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<concept definition>");
    result_ = conceptDefinition_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, CONCEPT);
    result_ = result_ && conceptDefinition_2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, IDENTIFIER);
    result_ = result_ && conceptDefinition_4(builder_, level_ + 1);
    result_ = result_ && conceptDefinition_5(builder_, level_ + 1);
    result_ = result_ && conceptDefinition_6(builder_, level_ + 1);
    if (result_) {
      marker_.done(CONCEPT_DEFINITION);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  // DEINDENT*
  private static boolean conceptDefinition_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "conceptDefinition_0")) return false;
    int offset_ = builder_.getCurrentOffset();
    while (true) {
      if (!consumeToken(builder_, DEINDENT)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "conceptDefinition_0");
        break;
      }
      offset_ = next_offset_;
    }
    return true;
  }

  // MODIFIERS?
  private static boolean conceptDefinition_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "conceptDefinition_2")) return false;
    consumeToken(builder_, MODIFIERS);
    return true;
  }

  // (IS IDENTIFIER)?
  private static boolean conceptDefinition_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "conceptDefinition_4")) return false;
    conceptDefinition_4_0(builder_, level_ + 1);
    return true;
  }

  // IS IDENTIFIER
  private static boolean conceptDefinition_4_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "conceptDefinition_4_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeTokens(builder_, 0, IS, IDENTIFIER);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // ANNOTATION*
  private static boolean conceptDefinition_5(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "conceptDefinition_5")) return false;
    int offset_ = builder_.getCurrentOffset();
    while (true) {
      if (!ANNOTATION(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "conceptDefinition_5");
        break;
      }
      offset_ = next_offset_;
    }
    return true;
  }

  // (NEW_LINE_INDENT conceptBody) | CRLF
  private static boolean conceptDefinition_6(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "conceptDefinition_6")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = conceptDefinition_6_0(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, CRLF);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // NEW_LINE_INDENT conceptBody
  private static boolean conceptDefinition_6_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "conceptDefinition_6_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, NEW_LINE_INDENT);
    result_ = result_ && conceptBody(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // PRIMITIVE_TYPE IDENTIFIER (ASSIGN PRIMITIVE)?
  public static boolean propertiesBody(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "propertiesBody")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<properties body>");
    result_ = PRIMITIVE_TYPE(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, IDENTIFIER);
    result_ = result_ && propertiesBody_2(builder_, level_ + 1);
    if (result_) {
      marker_.done(PROPERTIES_BODY);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  // (ASSIGN PRIMITIVE)?
  private static boolean propertiesBody_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "propertiesBody_2")) return false;
    propertiesBody_2_0(builder_, level_ + 1);
    return true;
  }

  // ASSIGN PRIMITIVE
  private static boolean propertiesBody_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "propertiesBody_2_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, ASSIGN);
    result_ = result_ && PRIMITIVE(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // REF IDENTIFIER
  public static boolean referencesBody(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "referencesBody")) return false;
    if (!nextTokenIs(builder_, REF)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeTokens(builder_, 0, REF, IDENTIFIER);
    if (result_) {
      marker_.done(REFERENCES_BODY);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  /* ********************************************************** */
  // (COMMENT|conceptDefinition|CRLF)*
  static boolean root(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "root")) return false;
    int offset_ = builder_.getCurrentOffset();
    while (true) {
      if (!root_0(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "root");
        break;
      }
      offset_ = next_offset_;
    }
    return true;
  }

  // COMMENT|conceptDefinition|CRLF
  private static boolean root_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "root_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, COMMENT);
    if (!result_) result_ = conceptDefinition(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, CRLF);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

}
