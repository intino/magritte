// This is a generated file. Not intended for manual editing.
package monet.tara.metamodel;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import com.intellij.openapi.diagnostic.Logger;
import static monet.tara.metamodel.psi.TaraTypes.*;
import static monet.tara.metamodel.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class TaraParser implements PsiParser {

  public static final Logger LOG_ = Logger.getInstance("monet.tara.metamodel.TaraParser");

  public ASTNode parse(IElementType root_, PsiBuilder builder_) {
    boolean result_;
    builder_ = adapt_builder_(root_, builder_, this, null);
    Marker marker_ = enter_section_(builder_, 0, _COLLAPSE_, null);
    if (root_ == ANNOTATION) {
      result_ = annotation(builder_, 0);
    }
    else if (root_ == CHILDREN_BODY) {
      result_ = childrenBody(builder_, 0);
    }
    else if (root_ == CONCEPT_BODY) {
      result_ = conceptBody(builder_, 0);
    }
    else if (root_ == CONCEPT_DEFINITION) {
      result_ = conceptDefinition(builder_, 0);
    }
    else if (root_ == CONCEPT_SIGNATURE) {
      result_ = conceptSignature(builder_, 0);
    }
    else if (root_ == PRIMITIVE) {
      result_ = primitive(builder_, 0);
    }
    else if (root_ == PRIMITIVE_TYPE) {
      result_ = primitive_type(builder_, 0);
    }
    else if (root_ == PROPERTIES_BODY) {
      result_ = propertiesBody(builder_, 0);
    }
    else if (root_ == REFERENCES_BODY) {
      result_ = referencesBody(builder_, 0);
    }
    else if (root_ == STATEMENT) {
      result_ = statement(builder_, 0);
    }
    else {
      result_ = parse_root_(root_, builder_, 0);
    }
    exit_section_(builder_, 0, marker_, root_, result_, true, TRUE_CONDITION);
    return builder_.getTreeBuilt();
  }

  protected boolean parse_root_(final IElementType root_, final PsiBuilder builder_, final int level_) {
    return root(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // NAMEABLE|AT_ROOT|EXTENSIBLE|ACTION
  public static boolean annotation(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "annotation")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<annotation>");
    result_ = consumeToken(builder_, NAMEABLE);
    if (!result_) result_ = consumeToken(builder_, AT_ROOT);
    if (!result_) result_ = consumeToken(builder_, EXTENSIBLE);
    if (!result_) result_ = consumeToken(builder_, ACTION);
    exit_section_(builder_, level_, marker_, ANNOTATION, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // HAS IDENTIFIER annotation* conceptBody*
  public static boolean childrenBody(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "childrenBody")) return false;
    if (!nextTokenIs(builder_, HAS)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, HAS, IDENTIFIER);
    result_ = result_ && childrenBody_2(builder_, level_ + 1);
    result_ = result_ && childrenBody_3(builder_, level_ + 1);
    exit_section_(builder_, marker_, CHILDREN_BODY, result_);
    return result_;
  }

  // annotation*
  private static boolean childrenBody_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "childrenBody_2")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!annotation(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "childrenBody_2", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  // conceptBody*
  private static boolean childrenBody_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "childrenBody_3")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!conceptBody(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "childrenBody_3", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  /* ********************************************************** */
  // INDENT statement+ DEDENT
  public static boolean conceptBody(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "conceptBody")) return false;
    if (!nextTokenIs(builder_, INDENT)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, INDENT);
    result_ = result_ && conceptBody_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, DEDENT);
    exit_section_(builder_, marker_, CONCEPT_BODY, result_);
    return result_;
  }

  // statement+
  private static boolean conceptBody_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "conceptBody_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = statement(builder_, level_ + 1);
    int pos_ = current_position_(builder_);
    while (result_) {
      if (!statement(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "conceptBody_1", pos_)) break;
      pos_ = current_position_(builder_);
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // conceptSignature conceptBody? END_CONCEPT
  public static boolean conceptDefinition(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "conceptDefinition")) return false;
    if (!nextTokenIs(builder_, CONCEPT)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = conceptSignature(builder_, level_ + 1);
    result_ = result_ && conceptDefinition_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, END_CONCEPT);
    exit_section_(builder_, marker_, CONCEPT_DEFINITION, result_);
    return result_;
  }

  // conceptBody?
  private static boolean conceptDefinition_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "conceptDefinition_1")) return false;
    conceptBody(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // CONCEPT MODIFIERS? IDENTIFIER (IS IDENTIFIER)? annotation*
  public static boolean conceptSignature(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "conceptSignature")) return false;
    if (!nextTokenIs(builder_, CONCEPT)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, CONCEPT);
    result_ = result_ && conceptSignature_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, IDENTIFIER);
    result_ = result_ && conceptSignature_3(builder_, level_ + 1);
    result_ = result_ && conceptSignature_4(builder_, level_ + 1);
    exit_section_(builder_, marker_, CONCEPT_SIGNATURE, result_);
    return result_;
  }

  // MODIFIERS?
  private static boolean conceptSignature_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "conceptSignature_1")) return false;
    consumeToken(builder_, MODIFIERS);
    return true;
  }

  // (IS IDENTIFIER)?
  private static boolean conceptSignature_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "conceptSignature_3")) return false;
    conceptSignature_3_0(builder_, level_ + 1);
    return true;
  }

  // IS IDENTIFIER
  private static boolean conceptSignature_3_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "conceptSignature_3_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, IS, IDENTIFIER);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // annotation*
  private static boolean conceptSignature_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "conceptSignature_4")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!annotation(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "conceptSignature_4", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  /* ********************************************************** */
  // INT|DOUBLE|STRING
  public static boolean primitive(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "primitive")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<primitive>");
    result_ = consumeToken(builder_, INT);
    if (!result_) result_ = consumeToken(builder_, DOUBLE);
    if (!result_) result_ = consumeToken(builder_, STRING);
    exit_section_(builder_, level_, marker_, PRIMITIVE, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // ID_TYPE|INT_TYPE|STRING_TYPE|DOUBLE_TYPE
  public static boolean primitive_type(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "primitive_type")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<primitive type>");
    result_ = consumeToken(builder_, ID_TYPE);
    if (!result_) result_ = consumeToken(builder_, INT_TYPE);
    if (!result_) result_ = consumeToken(builder_, STRING_TYPE);
    if (!result_) result_ = consumeToken(builder_, DOUBLE_TYPE);
    exit_section_(builder_, level_, marker_, PRIMITIVE_TYPE, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // primitive_type IDENTIFIER (ASSIGN primitive)?
  public static boolean propertiesBody(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "propertiesBody")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<properties body>");
    result_ = primitive_type(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, IDENTIFIER);
    result_ = result_ && propertiesBody_2(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, PROPERTIES_BODY, result_, false, null);
    return result_;
  }

  // (ASSIGN primitive)?
  private static boolean propertiesBody_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "propertiesBody_2")) return false;
    propertiesBody_2_0(builder_, level_ + 1);
    return true;
  }

  // ASSIGN primitive
  private static boolean propertiesBody_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "propertiesBody_2_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, ASSIGN);
    result_ = result_ && primitive(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // REF IDENTIFIER
  public static boolean referencesBody(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "referencesBody")) return false;
    if (!nextTokenIs(builder_, REF)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, REF, IDENTIFIER);
    exit_section_(builder_, marker_, REFERENCES_BODY, result_);
    return result_;
  }

  /* ********************************************************** */
  // (COMMENT|conceptDefinition)+
  static boolean root(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "root")) return false;
    if (!nextTokenIs(builder_, "", COMMENT, CONCEPT)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = root_0(builder_, level_ + 1);
    int pos_ = current_position_(builder_);
    while (result_) {
      if (!root_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "root", pos_)) break;
      pos_ = current_position_(builder_);
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // COMMENT|conceptDefinition
  private static boolean root_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "root_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMENT);
    if (!result_) result_ = conceptDefinition(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // propertiesBody | childrenBody | referencesBody
  public static boolean statement(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "statement")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<statement>");
    result_ = propertiesBody(builder_, level_ + 1);
    if (!result_) result_ = childrenBody(builder_, level_ + 1);
    if (!result_) result_ = referencesBody(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, STATEMENT, result_, false, null);
    return result_;
  }

}
