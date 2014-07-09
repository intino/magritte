// This is a generated file. Not intended for manual editing.
package siani.tara.intellij.lang.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import com.intellij.openapi.diagnostic.Logger;
import static siani.tara.intellij.lang.psi.TaraTypes.*;
import static siani.tara.intellij.lang.parser.TaraParserUtil.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class TaraParser implements PsiParser {

  public static final Logger LOG_ = Logger.getInstance("siani.tara.intellij.lang.parser.TaraParser");

  public ASTNode parse(IElementType root_, PsiBuilder builder_) {
    boolean result_;
    builder_ = adapt_builder_(root_, builder_, this, null);
    Marker marker_ = enter_section_(builder_, 0, _COLLAPSE_, null);
    if (root_ == AN_IMPORT) {
      result_ = anImport(builder_, 0);
    }
    else if (root_ == ANNOTATIONS) {
      result_ = annotations(builder_, 0);
    }
    else if (root_ == ATTRIBUTE) {
      result_ = attribute(builder_, 0);
    }
    else if (root_ == BODY) {
      result_ = body(builder_, 0);
    }
    else if (root_ == BOOLEAN_VALUE) {
      result_ = booleanValue(builder_, 0);
    }
    else if (root_ == BOX) {
      result_ = box(builder_, 0);
    }
    else if (root_ == CONCEPT) {
      result_ = concept(builder_, 0);
    }
    else if (root_ == DOC) {
      result_ = doc(builder_, 0);
    }
    else if (root_ == DOUBLE_VALUE) {
      result_ = doubleValue(builder_, 0);
    }
    else if (root_ == EMPTY) {
      result_ = empty(builder_, 0);
    }
    else if (root_ == EMPTY_FIELD) {
      result_ = emptyField(builder_, 0);
    }
    else if (root_ == EXPLICIT) {
      result_ = explicit(builder_, 0);
    }
    else if (root_ == FACET_APPLY) {
      result_ = facetApply(builder_, 0);
    }
    else if (root_ == FACET_TARGET) {
      result_ = facetTarget(builder_, 0);
    }
    else if (root_ == HEADER) {
      result_ = header(builder_, 0);
    }
    else if (root_ == HEADER_REFERENCE) {
      result_ = headerReference(builder_, 0);
    }
    else if (root_ == IDENTIFIER) {
      result_ = identifier(builder_, 0);
    }
    else if (root_ == IDENTIFIER_REFERENCE) {
      result_ = identifierReference(builder_, 0);
    }
    else if (root_ == INTEGER_VALUE) {
      result_ = integerValue(builder_, 0);
    }
    else if (root_ == META_IDENTIFIER) {
      result_ = metaIdentifier(builder_, 0);
    }
    else if (root_ == META_WORD) {
      result_ = metaWord(builder_, 0);
    }
    else if (root_ == NATURAL_VALUE) {
      result_ = naturalValue(builder_, 0);
    }
    else if (root_ == PARAMETER) {
      result_ = parameter(builder_, 0);
    }
    else if (root_ == PARAMETERS) {
      result_ = parameters(builder_, 0);
    }
    else if (root_ == SIGNATURE) {
      result_ = signature(builder_, 0);
    }
    else if (root_ == STRING_VALUE) {
      result_ = stringValue(builder_, 0);
    }
    else if (root_ == VAR_INIT) {
      result_ = varInit(builder_, 0);
    }
    else if (root_ == WORD) {
      result_ = word(builder_, 0);
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
  // ALIAS_TYPE   LIST?  IDENTIFIER_KEY (EQUALS (stringValue  | emptyField))?
  static boolean aliasAttribute(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "aliasAttribute")) return false;
    if (!nextTokenIs(builder_, ALIAS_TYPE)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, ALIAS_TYPE);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, aliasAttribute_1(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, consumeToken(builder_, IDENTIFIER_KEY)) && result_;
    result_ = pinned_ && aliasAttribute_3(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, null, result_, pinned_, null);
    return result_ || pinned_;
  }

  // LIST?
  private static boolean aliasAttribute_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "aliasAttribute_1")) return false;
    consumeToken(builder_, LIST);
    return true;
  }

  // (EQUALS (stringValue  | emptyField))?
  private static boolean aliasAttribute_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "aliasAttribute_3")) return false;
    aliasAttribute_3_0(builder_, level_ + 1);
    return true;
  }

  // EQUALS (stringValue  | emptyField)
  private static boolean aliasAttribute_3_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "aliasAttribute_3_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, EQUALS);
    result_ = result_ && aliasAttribute_3_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // stringValue  | emptyField
  private static boolean aliasAttribute_3_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "aliasAttribute_3_0_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = stringValue(builder_, level_ + 1);
    if (!result_) result_ = emptyField(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // NEWLINE+ USE_KEY headerReference  (AS METAMODEL)?
  public static boolean anImport(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "anImport")) return false;
    if (!nextTokenIs(builder_, NEWLINE)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = anImport_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, USE_KEY);
    result_ = result_ && headerReference(builder_, level_ + 1);
    result_ = result_ && anImport_3(builder_, level_ + 1);
    exit_section_(builder_, marker_, AN_IMPORT, result_);
    return result_;
  }

  // NEWLINE+
  private static boolean anImport_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "anImport_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, NEWLINE);
    int pos_ = current_position_(builder_);
    while (result_) {
      if (!consumeToken(builder_, NEWLINE)) break;
      if (!empty_element_parsed_guard_(builder_, "anImport_0", pos_)) break;
      pos_ = current_position_(builder_);
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (AS METAMODEL)?
  private static boolean anImport_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "anImport_3")) return false;
    anImport_3_0(builder_, level_ + 1);
    return true;
  }

  // AS METAMODEL
  private static boolean anImport_3_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "anImport_3_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, AS, METAMODEL);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // IS (PRIVATE | TERMINAL | SINGLE | REQUIRED | NAMEABLE | ROOT | PROPERTY)+
  public static boolean annotations(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "annotations")) return false;
    if (!nextTokenIs(builder_, IS)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, IS);
    pinned_ = result_; // pin = 1
    result_ = result_ && annotations_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, ANNOTATIONS, result_, pinned_, null);
    return result_ || pinned_;
  }

  // (PRIVATE | TERMINAL | SINGLE | REQUIRED | NAMEABLE | ROOT | PROPERTY)+
  private static boolean annotations_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "annotations_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = annotations_1_0(builder_, level_ + 1);
    int pos_ = current_position_(builder_);
    while (result_) {
      if (!annotations_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "annotations_1", pos_)) break;
      pos_ = current_position_(builder_);
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // PRIVATE | TERMINAL | SINGLE | REQUIRED | NAMEABLE | ROOT | PROPERTY
  private static boolean annotations_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "annotations_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, PRIVATE);
    if (!result_) result_ = consumeToken(builder_, TERMINAL);
    if (!result_) result_ = consumeToken(builder_, SINGLE);
    if (!result_) result_ = consumeToken(builder_, REQUIRED);
    if (!result_) result_ = consumeToken(builder_, NAMEABLE);
    if (!result_) result_ = consumeToken(builder_, ROOT);
    if (!result_) result_ = consumeToken(builder_, PROPERTY);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // doc? VAR (aliasAttribute | naturalAttribute | integerAttribute | doubleAttribute | booleanAttribute | stringAttribute | dateAttribute
  //                         | locationAttribute | resource | referenceAttribute | word) annotations?
  public static boolean attribute(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute")) return false;
    if (!nextTokenIs(builder_, "<attribute>", DOC_LINE, VAR)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<attribute>");
    result_ = attribute_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, VAR);
    pinned_ = result_; // pin = 2
    result_ = result_ && report_error_(builder_, attribute_2(builder_, level_ + 1));
    result_ = pinned_ && attribute_3(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, ATTRIBUTE, result_, pinned_, null);
    return result_ || pinned_;
  }

  // doc?
  private static boolean attribute_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_0")) return false;
    doc(builder_, level_ + 1);
    return true;
  }

  // aliasAttribute | naturalAttribute | integerAttribute | doubleAttribute | booleanAttribute | stringAttribute | dateAttribute
  //                         | locationAttribute | resource | referenceAttribute | word
  private static boolean attribute_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_2")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = aliasAttribute(builder_, level_ + 1);
    if (!result_) result_ = naturalAttribute(builder_, level_ + 1);
    if (!result_) result_ = integerAttribute(builder_, level_ + 1);
    if (!result_) result_ = doubleAttribute(builder_, level_ + 1);
    if (!result_) result_ = booleanAttribute(builder_, level_ + 1);
    if (!result_) result_ = stringAttribute(builder_, level_ + 1);
    if (!result_) result_ = dateAttribute(builder_, level_ + 1);
    if (!result_) result_ = locationAttribute(builder_, level_ + 1);
    if (!result_) result_ = resource(builder_, level_ + 1);
    if (!result_) result_ = referenceAttribute(builder_, level_ + 1);
    if (!result_) result_ = word(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // annotations?
  private static boolean attribute_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_3")) return false;
    annotations(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // NEW_LINE_INDENT (conceptConstituents NEWLINE+)+ DEDENT
  public static boolean body(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "body")) return false;
    if (!nextTokenIs(builder_, NEW_LINE_INDENT)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, NEW_LINE_INDENT);
    result_ = result_ && body_1(builder_, level_ + 1);
    pinned_ = result_; // pin = 2
    result_ = result_ && consumeToken(builder_, DEDENT);
    exit_section_(builder_, level_, marker_, BODY, result_, pinned_, null);
    return result_ || pinned_;
  }

  // (conceptConstituents NEWLINE+)+
  private static boolean body_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "body_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = body_1_0(builder_, level_ + 1);
    int pos_ = current_position_(builder_);
    while (result_) {
      if (!body_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "body_1", pos_)) break;
      pos_ = current_position_(builder_);
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // conceptConstituents NEWLINE+
  private static boolean body_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "body_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = conceptConstituents(builder_, level_ + 1);
    result_ = result_ && body_1_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // NEWLINE+
  private static boolean body_1_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "body_1_0_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, NEWLINE);
    int pos_ = current_position_(builder_);
    while (result_) {
      if (!consumeToken(builder_, NEWLINE)) break;
      if (!empty_element_parsed_guard_(builder_, "body_1_0_1", pos_)) break;
      pos_ = current_position_(builder_);
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // BOOLEAN_TYPE LIST?  IDENTIFIER_KEY (EQUALS (booleanValue | emptyField))?
  static boolean booleanAttribute(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "booleanAttribute")) return false;
    if (!nextTokenIs(builder_, BOOLEAN_TYPE)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, BOOLEAN_TYPE);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, booleanAttribute_1(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, consumeToken(builder_, IDENTIFIER_KEY)) && result_;
    result_ = pinned_ && booleanAttribute_3(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, null, result_, pinned_, null);
    return result_ || pinned_;
  }

  // LIST?
  private static boolean booleanAttribute_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "booleanAttribute_1")) return false;
    consumeToken(builder_, LIST);
    return true;
  }

  // (EQUALS (booleanValue | emptyField))?
  private static boolean booleanAttribute_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "booleanAttribute_3")) return false;
    booleanAttribute_3_0(builder_, level_ + 1);
    return true;
  }

  // EQUALS (booleanValue | emptyField)
  private static boolean booleanAttribute_3_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "booleanAttribute_3_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, EQUALS);
    result_ = result_ && booleanAttribute_3_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // booleanValue | emptyField
  private static boolean booleanAttribute_3_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "booleanAttribute_3_0_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = booleanValue(builder_, level_ + 1);
    if (!result_) result_ = emptyField(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // BOOLEAN_VALUE_KEY
  public static boolean booleanValue(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "booleanValue")) return false;
    if (!nextTokenIs(builder_, BOOLEAN_VALUE_KEY)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, BOOLEAN_VALUE_KEY);
    exit_section_(builder_, marker_, BOOLEAN_VALUE, result_);
    return result_;
  }

  /* ********************************************************** */
  // BOX_KEY headerReference
  public static boolean box(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "box")) return false;
    if (!nextTokenIs(builder_, BOX_KEY)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, BOX_KEY);
    result_ = result_ && headerReference(builder_, level_ + 1);
    exit_section_(builder_, marker_, BOX, result_);
    return result_;
  }

  /* ********************************************************** */
  // doc? signature annotations? body?
  public static boolean concept(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "concept")) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<concept>");
    result_ = concept_0(builder_, level_ + 1);
    result_ = result_ && signature(builder_, level_ + 1);
    pinned_ = result_; // pin = 2
    result_ = result_ && report_error_(builder_, concept_2(builder_, level_ + 1));
    result_ = pinned_ && concept_3(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, CONCEPT, result_, pinned_, null);
    return result_ || pinned_;
  }

  // doc?
  private static boolean concept_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "concept_0")) return false;
    doc(builder_, level_ + 1);
    return true;
  }

  // annotations?
  private static boolean concept_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "concept_2")) return false;
    annotations(builder_, level_ + 1);
    return true;
  }

  // body?
  private static boolean concept_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "concept_3")) return false;
    body(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // attribute | concept | varInit | facetApply | facetTarget
  static boolean conceptConstituents(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "conceptConstituents")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = attribute(builder_, level_ + 1);
    if (!result_) result_ = concept(builder_, level_ + 1);
    if (!result_) result_ = varInit(builder_, level_ + 1);
    if (!result_) result_ = facetApply(builder_, level_ + 1);
    if (!result_) result_ = facetTarget(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // DATE_TYPE    LIST?  IDENTIFIER_KEY (EQUALS (naturalValue | emptyField))?
  static boolean dateAttribute(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "dateAttribute")) return false;
    if (!nextTokenIs(builder_, DATE_TYPE)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, DATE_TYPE);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, dateAttribute_1(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, consumeToken(builder_, IDENTIFIER_KEY)) && result_;
    result_ = pinned_ && dateAttribute_3(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, null, result_, pinned_, null);
    return result_ || pinned_;
  }

  // LIST?
  private static boolean dateAttribute_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "dateAttribute_1")) return false;
    consumeToken(builder_, LIST);
    return true;
  }

  // (EQUALS (naturalValue | emptyField))?
  private static boolean dateAttribute_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "dateAttribute_3")) return false;
    dateAttribute_3_0(builder_, level_ + 1);
    return true;
  }

  // EQUALS (naturalValue | emptyField)
  private static boolean dateAttribute_3_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "dateAttribute_3_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, EQUALS);
    result_ = result_ && dateAttribute_3_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // naturalValue | emptyField
  private static boolean dateAttribute_3_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "dateAttribute_3_0_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = naturalValue(builder_, level_ + 1);
    if (!result_) result_ = emptyField(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // DOC_LINE+
  public static boolean doc(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "doc")) return false;
    if (!nextTokenIs(builder_, DOC_LINE)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, DOC_LINE);
    int pos_ = current_position_(builder_);
    while (result_) {
      if (!consumeToken(builder_, DOC_LINE)) break;
      if (!empty_element_parsed_guard_(builder_, "doc", pos_)) break;
      pos_ = current_position_(builder_);
    }
    exit_section_(builder_, marker_, DOC, result_);
    return result_;
  }

  /* ********************************************************** */
  // DOUBLE_TYPE  LIST?  IDENTIFIER_KEY (EQUALS (doubleValue  | emptyField))?
  static boolean doubleAttribute(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "doubleAttribute")) return false;
    if (!nextTokenIs(builder_, DOUBLE_TYPE)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, DOUBLE_TYPE);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, doubleAttribute_1(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, consumeToken(builder_, IDENTIFIER_KEY)) && result_;
    result_ = pinned_ && doubleAttribute_3(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, null, result_, pinned_, null);
    return result_ || pinned_;
  }

  // LIST?
  private static boolean doubleAttribute_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "doubleAttribute_1")) return false;
    consumeToken(builder_, LIST);
    return true;
  }

  // (EQUALS (doubleValue  | emptyField))?
  private static boolean doubleAttribute_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "doubleAttribute_3")) return false;
    doubleAttribute_3_0(builder_, level_ + 1);
    return true;
  }

  // EQUALS (doubleValue  | emptyField)
  private static boolean doubleAttribute_3_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "doubleAttribute_3_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, EQUALS);
    result_ = result_ && doubleAttribute_3_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // doubleValue  | emptyField
  private static boolean doubleAttribute_3_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "doubleAttribute_3_0_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = doubleValue(builder_, level_ + 1);
    if (!result_) result_ = emptyField(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // NATURAL_VALUE_KEY | NEGATIVE_VALUE_KEY | DOUBLE_VALUE_KEY
  public static boolean doubleValue(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "doubleValue")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<double value>");
    result_ = consumeToken(builder_, NATURAL_VALUE_KEY);
    if (!result_) result_ = consumeToken(builder_, NEGATIVE_VALUE_KEY);
    if (!result_) result_ = consumeToken(builder_, DOUBLE_VALUE_KEY);
    exit_section_(builder_, level_, marker_, DOUBLE_VALUE, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  public static boolean empty(PsiBuilder builder_, int level_) {
    builder_.mark().done(EMPTY);
    return true;
  }

  /* ********************************************************** */
  // EMPTY_REF
  public static boolean emptyField(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "emptyField")) return false;
    if (!nextTokenIs(builder_, EMPTY_REF)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, EMPTY_REF);
    exit_section_(builder_, marker_, EMPTY_FIELD, result_);
    return result_;
  }

  /* ********************************************************** */
  // identifier EQUALS
  public static boolean explicit(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "explicit")) return false;
    if (!nextTokenIs(builder_, IDENTIFIER_KEY)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = identifier(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, EQUALS);
    exit_section_(builder_, marker_, EXPLICIT, result_);
    return result_;
  }

  /* ********************************************************** */
  // AS identifier parameters? (WITH identifierReference)?
  public static boolean facetApply(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "facetApply")) return false;
    if (!nextTokenIs(builder_, AS)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, AS);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, identifier(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, facetApply_2(builder_, level_ + 1)) && result_;
    result_ = pinned_ && facetApply_3(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, FACET_APPLY, result_, pinned_, null);
    return result_ || pinned_;
  }

  // parameters?
  private static boolean facetApply_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "facetApply_2")) return false;
    parameters(builder_, level_ + 1);
    return true;
  }

  // (WITH identifierReference)?
  private static boolean facetApply_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "facetApply_3")) return false;
    facetApply_3_0(builder_, level_ + 1);
    return true;
  }

  // WITH identifierReference
  private static boolean facetApply_3_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "facetApply_3_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, WITH);
    result_ = result_ && identifierReference(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // ON identifier body?
  public static boolean facetTarget(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "facetTarget")) return false;
    if (!nextTokenIs(builder_, ON)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, ON);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, identifier(builder_, level_ + 1));
    result_ = pinned_ && facetTarget_2(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, FACET_TARGET, result_, pinned_, null);
    return result_ || pinned_;
  }

  // body?
  private static boolean facetTarget_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "facetTarget_2")) return false;
    body(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // box?  imports?
  public static boolean header(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "header")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<header>");
    result_ = header_0(builder_, level_ + 1);
    result_ = result_ && header_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, HEADER, result_, false, null);
    return result_;
  }

  // box?
  private static boolean header_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "header_0")) return false;
    box(builder_, level_ + 1);
    return true;
  }

  // imports?
  private static boolean header_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "header_1")) return false;
    imports(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // hierarchy* identifier
  public static boolean headerReference(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "headerReference")) return false;
    if (!nextTokenIs(builder_, IDENTIFIER_KEY)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = headerReference_0(builder_, level_ + 1);
    result_ = result_ && identifier(builder_, level_ + 1);
    exit_section_(builder_, marker_, HEADER_REFERENCE, result_);
    return result_;
  }

  // hierarchy*
  private static boolean headerReference_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "headerReference_0")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!hierarchy(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "headerReference_0", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  /* ********************************************************** */
  // identifier DOT
  static boolean hierarchy(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "hierarchy")) return false;
    if (!nextTokenIs(builder_, IDENTIFIER_KEY)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = identifier(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, DOT);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // IDENTIFIER_KEY
  public static boolean identifier(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "identifier")) return false;
    if (!nextTokenIs(builder_, IDENTIFIER_KEY)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, IDENTIFIER_KEY);
    exit_section_(builder_, marker_, IDENTIFIER, result_);
    return result_;
  }

  /* ********************************************************** */
  // hierarchy* identifier
  public static boolean identifierReference(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "identifierReference")) return false;
    if (!nextTokenIs(builder_, IDENTIFIER_KEY)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = identifierReference_0(builder_, level_ + 1);
    result_ = result_ && identifier(builder_, level_ + 1);
    exit_section_(builder_, marker_, IDENTIFIER_REFERENCE, result_);
    return result_;
  }

  // hierarchy*
  private static boolean identifierReference_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "identifierReference_0")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!hierarchy(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "identifierReference_0", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  /* ********************************************************** */
  // anImport+
  static boolean imports(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "imports")) return false;
    if (!nextTokenIs(builder_, NEWLINE)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = anImport(builder_, level_ + 1);
    int pos_ = current_position_(builder_);
    while (result_) {
      if (!anImport(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "imports", pos_)) break;
      pos_ = current_position_(builder_);
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // INT_TYPE     LIST?  IDENTIFIER_KEY (EQUALS (integerValue | emptyField))?
  static boolean integerAttribute(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "integerAttribute")) return false;
    if (!nextTokenIs(builder_, INT_TYPE)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, INT_TYPE);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, integerAttribute_1(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, consumeToken(builder_, IDENTIFIER_KEY)) && result_;
    result_ = pinned_ && integerAttribute_3(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, null, result_, pinned_, null);
    return result_ || pinned_;
  }

  // LIST?
  private static boolean integerAttribute_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "integerAttribute_1")) return false;
    consumeToken(builder_, LIST);
    return true;
  }

  // (EQUALS (integerValue | emptyField))?
  private static boolean integerAttribute_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "integerAttribute_3")) return false;
    integerAttribute_3_0(builder_, level_ + 1);
    return true;
  }

  // EQUALS (integerValue | emptyField)
  private static boolean integerAttribute_3_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "integerAttribute_3_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, EQUALS);
    result_ = result_ && integerAttribute_3_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // integerValue | emptyField
  private static boolean integerAttribute_3_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "integerAttribute_3_0_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = integerValue(builder_, level_ + 1);
    if (!result_) result_ = emptyField(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // NATURAL_VALUE_KEY | NEGATIVE_VALUE_KEY
  public static boolean integerValue(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "integerValue")) return false;
    if (!nextTokenIs(builder_, "<integer value>", NATURAL_VALUE_KEY, NEGATIVE_VALUE_KEY)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<integer value>");
    result_ = consumeToken(builder_, NATURAL_VALUE_KEY);
    if (!result_) result_ = consumeToken(builder_, NEGATIVE_VALUE_KEY);
    exit_section_(builder_, level_, marker_, INTEGER_VALUE, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // LOCATION_TYPE       IDENTIFIER_KEY (EQUALS (naturalValue | emptyField))?
  static boolean locationAttribute(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "locationAttribute")) return false;
    if (!nextTokenIs(builder_, LOCATION_TYPE)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeTokens(builder_, 1, LOCATION_TYPE, IDENTIFIER_KEY);
    pinned_ = result_; // pin = 1
    result_ = result_ && locationAttribute_2(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, null, result_, pinned_, null);
    return result_ || pinned_;
  }

  // (EQUALS (naturalValue | emptyField))?
  private static boolean locationAttribute_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "locationAttribute_2")) return false;
    locationAttribute_2_0(builder_, level_ + 1);
    return true;
  }

  // EQUALS (naturalValue | emptyField)
  private static boolean locationAttribute_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "locationAttribute_2_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, EQUALS);
    result_ = result_ && locationAttribute_2_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // naturalValue | emptyField
  private static boolean locationAttribute_2_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "locationAttribute_2_0_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = naturalValue(builder_, level_ + 1);
    if (!result_) result_ = emptyField(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // METAIDENTIFIER_KEY | IDENTIFIER_KEY | INTENTION_KEY
  public static boolean metaIdentifier(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "metaIdentifier")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<meta identifier>");
    result_ = consumeToken(builder_, METAIDENTIFIER_KEY);
    if (!result_) result_ = consumeToken(builder_, IDENTIFIER_KEY);
    if (!result_) result_ = consumeToken(builder_, INTENTION_KEY);
    exit_section_(builder_, level_, marker_, META_IDENTIFIER, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // metaIdentifier metaWordNames*
  public static boolean metaWord(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "metaWord")) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<meta word>");
    result_ = metaIdentifier(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && metaWord_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, META_WORD, result_, pinned_, null);
    return result_ || pinned_;
  }

  // metaWordNames*
  private static boolean metaWord_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "metaWord_1")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!metaWordNames(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "metaWord_1", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  /* ********************************************************** */
  // DOT identifier
  static boolean metaWordNames(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "metaWordNames")) return false;
    if (!nextTokenIs(builder_, DOT)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, DOT);
    pinned_ = result_; // pin = 1
    result_ = result_ && identifier(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, null, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // NATURAL_TYPE LIST?  IDENTIFIER_KEY (EQUALS (naturalValue | emptyField))?
  static boolean naturalAttribute(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "naturalAttribute")) return false;
    if (!nextTokenIs(builder_, NATURAL_TYPE)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, NATURAL_TYPE);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, naturalAttribute_1(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, consumeToken(builder_, IDENTIFIER_KEY)) && result_;
    result_ = pinned_ && naturalAttribute_3(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, null, result_, pinned_, null);
    return result_ || pinned_;
  }

  // LIST?
  private static boolean naturalAttribute_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "naturalAttribute_1")) return false;
    consumeToken(builder_, LIST);
    return true;
  }

  // (EQUALS (naturalValue | emptyField))?
  private static boolean naturalAttribute_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "naturalAttribute_3")) return false;
    naturalAttribute_3_0(builder_, level_ + 1);
    return true;
  }

  // EQUALS (naturalValue | emptyField)
  private static boolean naturalAttribute_3_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "naturalAttribute_3_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, EQUALS);
    result_ = result_ && naturalAttribute_3_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // naturalValue | emptyField
  private static boolean naturalAttribute_3_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "naturalAttribute_3_0_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = naturalValue(builder_, level_ + 1);
    if (!result_) result_ = emptyField(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // NATURAL_VALUE_KEY
  public static boolean naturalValue(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "naturalValue")) return false;
    if (!nextTokenIs(builder_, NATURAL_VALUE_KEY)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, NATURAL_VALUE_KEY);
    exit_section_(builder_, marker_, NATURAL_VALUE, result_);
    return result_;
  }

  /* ********************************************************** */
  // identifierReference+
  // 				| stringValue+
  // 		        | booleanValue+
  // 		        | naturalValue+
  // 		        | integerValue+
  // 		        | doubleValue+
  // 		        | metaWord
  // 		        | empty
  public static boolean parameter(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "parameter")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<parameter>");
    result_ = parameter_0(builder_, level_ + 1);
    if (!result_) result_ = parameter_1(builder_, level_ + 1);
    if (!result_) result_ = parameter_2(builder_, level_ + 1);
    if (!result_) result_ = parameter_3(builder_, level_ + 1);
    if (!result_) result_ = parameter_4(builder_, level_ + 1);
    if (!result_) result_ = parameter_5(builder_, level_ + 1);
    if (!result_) result_ = metaWord(builder_, level_ + 1);
    if (!result_) result_ = empty(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, PARAMETER, result_, false, null);
    return result_;
  }

  // identifierReference+
  private static boolean parameter_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "parameter_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = identifierReference(builder_, level_ + 1);
    int pos_ = current_position_(builder_);
    while (result_) {
      if (!identifierReference(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "parameter_0", pos_)) break;
      pos_ = current_position_(builder_);
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // stringValue+
  private static boolean parameter_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "parameter_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = stringValue(builder_, level_ + 1);
    int pos_ = current_position_(builder_);
    while (result_) {
      if (!stringValue(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "parameter_1", pos_)) break;
      pos_ = current_position_(builder_);
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // booleanValue+
  private static boolean parameter_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "parameter_2")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = booleanValue(builder_, level_ + 1);
    int pos_ = current_position_(builder_);
    while (result_) {
      if (!booleanValue(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "parameter_2", pos_)) break;
      pos_ = current_position_(builder_);
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // naturalValue+
  private static boolean parameter_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "parameter_3")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = naturalValue(builder_, level_ + 1);
    int pos_ = current_position_(builder_);
    while (result_) {
      if (!naturalValue(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "parameter_3", pos_)) break;
      pos_ = current_position_(builder_);
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // integerValue+
  private static boolean parameter_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "parameter_4")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = integerValue(builder_, level_ + 1);
    int pos_ = current_position_(builder_);
    while (result_) {
      if (!integerValue(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "parameter_4", pos_)) break;
      pos_ = current_position_(builder_);
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // doubleValue+
  private static boolean parameter_5(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "parameter_5")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = doubleValue(builder_, level_ + 1);
    int pos_ = current_position_(builder_);
    while (result_) {
      if (!doubleValue(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "parameter_5", pos_)) break;
      pos_ = current_position_(builder_);
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // explicit? parameter (COMMA explicit? parameter)*
  static boolean parameterList(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "parameterList")) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = parameterList_0(builder_, level_ + 1);
    result_ = result_ && parameter(builder_, level_ + 1);
    pinned_ = result_; // pin = 2
    result_ = result_ && parameterList_2(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, null, result_, pinned_, null);
    return result_ || pinned_;
  }

  // explicit?
  private static boolean parameterList_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "parameterList_0")) return false;
    explicit(builder_, level_ + 1);
    return true;
  }

  // (COMMA explicit? parameter)*
  private static boolean parameterList_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "parameterList_2")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!parameterList_2_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "parameterList_2", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  // COMMA explicit? parameter
  private static boolean parameterList_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "parameterList_2_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && parameterList_2_0_1(builder_, level_ + 1);
    result_ = result_ && parameter(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // explicit?
  private static boolean parameterList_2_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "parameterList_2_0_1")) return false;
    explicit(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // LEFT_PARENTHESIS parameterList? RIGHT_PARENTHESIS
  public static boolean parameters(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "parameters")) return false;
    if (!nextTokenIs(builder_, LEFT_PARENTHESIS)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, LEFT_PARENTHESIS);
    result_ = result_ && parameters_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RIGHT_PARENTHESIS);
    exit_section_(builder_, marker_, PARAMETERS, result_);
    return result_;
  }

  // parameterList?
  private static boolean parameters_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "parameters_1")) return false;
    parameterList(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // identifierReference LIST? IDENTIFIER_KEY (EQUALS emptyField)?
  static boolean referenceAttribute(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "referenceAttribute")) return false;
    if (!nextTokenIs(builder_, IDENTIFIER_KEY)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = identifierReference(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, referenceAttribute_1(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, consumeToken(builder_, IDENTIFIER_KEY)) && result_;
    result_ = pinned_ && referenceAttribute_3(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, null, result_, pinned_, null);
    return result_ || pinned_;
  }

  // LIST?
  private static boolean referenceAttribute_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "referenceAttribute_1")) return false;
    consumeToken(builder_, LIST);
    return true;
  }

  // (EQUALS emptyField)?
  private static boolean referenceAttribute_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "referenceAttribute_3")) return false;
    referenceAttribute_3_0(builder_, level_ + 1);
    return true;
  }

  // EQUALS emptyField
  private static boolean referenceAttribute_3_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "referenceAttribute_3_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, EQUALS);
    result_ = result_ && emptyField(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // RESOURCE_KEY EQUALS IDENTIFIER_KEY IDENTIFIER_KEY
  static boolean resource(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "resource")) return false;
    if (!nextTokenIs(builder_, RESOURCE_KEY)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeTokens(builder_, 1, RESOURCE_KEY, EQUALS, IDENTIFIER_KEY, IDENTIFIER_KEY);
    pinned_ = result_; // pin = 1
    exit_section_(builder_, level_, marker_, null, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // NEWLINE* header? NEWLINE+ (concept  NEWLINE*)*
  static boolean root(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "root")) return false;
    if (!nextTokenIs(builder_, "", BOX_KEY, NEWLINE)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = root_0(builder_, level_ + 1);
    result_ = result_ && root_1(builder_, level_ + 1);
    result_ = result_ && root_2(builder_, level_ + 1);
    result_ = result_ && root_3(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // NEWLINE*
  private static boolean root_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "root_0")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!consumeToken(builder_, NEWLINE)) break;
      if (!empty_element_parsed_guard_(builder_, "root_0", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  // header?
  private static boolean root_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "root_1")) return false;
    header(builder_, level_ + 1);
    return true;
  }

  // NEWLINE+
  private static boolean root_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "root_2")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, NEWLINE);
    int pos_ = current_position_(builder_);
    while (result_) {
      if (!consumeToken(builder_, NEWLINE)) break;
      if (!empty_element_parsed_guard_(builder_, "root_2", pos_)) break;
      pos_ = current_position_(builder_);
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (concept  NEWLINE*)*
  private static boolean root_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "root_3")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!root_3_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "root_3", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  // concept  NEWLINE*
  private static boolean root_3_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "root_3_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = concept(builder_, level_ + 1);
    result_ = result_ && root_3_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // NEWLINE*
  private static boolean root_3_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "root_3_0_1")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!consumeToken(builder_, NEWLINE)) break;
      if (!empty_element_parsed_guard_(builder_, "root_3_0_1", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  /* ********************************************************** */
  // ((CASE_KEY identifier)
  // 			| withHeritage
  // 			| (metaIdentifier identifier)) parameters?
  public static boolean signature(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "signature")) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<signature>");
    result_ = signature_0(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && signature_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, SIGNATURE, result_, pinned_, null);
    return result_ || pinned_;
  }

  // (CASE_KEY identifier)
  // 			| withHeritage
  // 			| (metaIdentifier identifier)
  private static boolean signature_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "signature_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = signature_0_0(builder_, level_ + 1);
    if (!result_) result_ = withHeritage(builder_, level_ + 1);
    if (!result_) result_ = signature_0_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // CASE_KEY identifier
  private static boolean signature_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "signature_0_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, CASE_KEY);
    result_ = result_ && identifier(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // metaIdentifier identifier
  private static boolean signature_0_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "signature_0_2")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = metaIdentifier(builder_, level_ + 1);
    result_ = result_ && identifier(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // parameters?
  private static boolean signature_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "signature_1")) return false;
    parameters(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // STRING_TYPE  LIST?  IDENTIFIER_KEY (EQUALS (stringValue  | emptyField))?
  static boolean stringAttribute(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "stringAttribute")) return false;
    if (!nextTokenIs(builder_, STRING_TYPE)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, STRING_TYPE);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, stringAttribute_1(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, consumeToken(builder_, IDENTIFIER_KEY)) && result_;
    result_ = pinned_ && stringAttribute_3(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, null, result_, pinned_, null);
    return result_ || pinned_;
  }

  // LIST?
  private static boolean stringAttribute_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "stringAttribute_1")) return false;
    consumeToken(builder_, LIST);
    return true;
  }

  // (EQUALS (stringValue  | emptyField))?
  private static boolean stringAttribute_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "stringAttribute_3")) return false;
    stringAttribute_3_0(builder_, level_ + 1);
    return true;
  }

  // EQUALS (stringValue  | emptyField)
  private static boolean stringAttribute_3_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "stringAttribute_3_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, EQUALS);
    result_ = result_ && stringAttribute_3_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // stringValue  | emptyField
  private static boolean stringAttribute_3_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "stringAttribute_3_0_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = stringValue(builder_, level_ + 1);
    if (!result_) result_ = emptyField(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // STRING_VALUE_KEY  | STRING_MULTILINE_VALUE_KEY
  public static boolean stringValue(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "stringValue")) return false;
    if (!nextTokenIs(builder_, "<string value>", STRING_MULTILINE_VALUE_KEY, STRING_VALUE_KEY)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<string value>");
    result_ = consumeToken(builder_, STRING_VALUE_KEY);
    if (!result_) result_ = consumeToken(builder_, STRING_MULTILINE_VALUE_KEY);
    exit_section_(builder_, level_, marker_, STRING_VALUE, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // IDENTIFIER_KEY EQUALS ( emptyField
  // 								 | identifierReference+
  // 								 | stringValue+
  //                                  | booleanValue+
  //                                  | naturalValue+
  //                                  | integerValue+
  //                                  | doubleValue+)
  public static boolean varInit(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "varInit")) return false;
    if (!nextTokenIs(builder_, IDENTIFIER_KEY)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeTokens(builder_, 2, IDENTIFIER_KEY, EQUALS);
    pinned_ = result_; // pin = 2
    result_ = result_ && varInit_2(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, VAR_INIT, result_, pinned_, null);
    return result_ || pinned_;
  }

  // emptyField
  // 								 | identifierReference+
  // 								 | stringValue+
  //                                  | booleanValue+
  //                                  | naturalValue+
  //                                  | integerValue+
  //                                  | doubleValue+
  private static boolean varInit_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "varInit_2")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = emptyField(builder_, level_ + 1);
    if (!result_) result_ = varInit_2_1(builder_, level_ + 1);
    if (!result_) result_ = varInit_2_2(builder_, level_ + 1);
    if (!result_) result_ = varInit_2_3(builder_, level_ + 1);
    if (!result_) result_ = varInit_2_4(builder_, level_ + 1);
    if (!result_) result_ = varInit_2_5(builder_, level_ + 1);
    if (!result_) result_ = varInit_2_6(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // identifierReference+
  private static boolean varInit_2_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "varInit_2_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = identifierReference(builder_, level_ + 1);
    int pos_ = current_position_(builder_);
    while (result_) {
      if (!identifierReference(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "varInit_2_1", pos_)) break;
      pos_ = current_position_(builder_);
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // stringValue+
  private static boolean varInit_2_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "varInit_2_2")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = stringValue(builder_, level_ + 1);
    int pos_ = current_position_(builder_);
    while (result_) {
      if (!stringValue(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "varInit_2_2", pos_)) break;
      pos_ = current_position_(builder_);
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // booleanValue+
  private static boolean varInit_2_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "varInit_2_3")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = booleanValue(builder_, level_ + 1);
    int pos_ = current_position_(builder_);
    while (result_) {
      if (!booleanValue(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "varInit_2_3", pos_)) break;
      pos_ = current_position_(builder_);
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // naturalValue+
  private static boolean varInit_2_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "varInit_2_4")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = naturalValue(builder_, level_ + 1);
    int pos_ = current_position_(builder_);
    while (result_) {
      if (!naturalValue(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "varInit_2_4", pos_)) break;
      pos_ = current_position_(builder_);
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // integerValue+
  private static boolean varInit_2_5(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "varInit_2_5")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = integerValue(builder_, level_ + 1);
    int pos_ = current_position_(builder_);
    while (result_) {
      if (!integerValue(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "varInit_2_5", pos_)) break;
      pos_ = current_position_(builder_);
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // doubleValue+
  private static boolean varInit_2_6(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "varInit_2_6")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = doubleValue(builder_, level_ + 1);
    int pos_ = current_position_(builder_);
    while (result_) {
      if (!doubleValue(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "varInit_2_6", pos_)) break;
      pos_ = current_position_(builder_);
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // metaIdentifier COLON identifierReference identifier?
  static boolean withHeritage(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "withHeritage")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = metaIdentifier(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, COLON);
    result_ = result_ && identifierReference(builder_, level_ + 1);
    result_ = result_ && withHeritage_3(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // identifier?
  private static boolean withHeritage_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "withHeritage_3")) return false;
    identifier(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // WORD_KEY IDENTIFIER_KEY NEW_LINE_INDENT (IDENTIFIER_KEY STAR? NEWLINE)+ DEDENT
  public static boolean word(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "word")) return false;
    if (!nextTokenIs(builder_, WORD_KEY)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeTokens(builder_, 1, WORD_KEY, IDENTIFIER_KEY, NEW_LINE_INDENT);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, word_3(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, DEDENT) && result_;
    exit_section_(builder_, level_, marker_, WORD, result_, pinned_, null);
    return result_ || pinned_;
  }

  // (IDENTIFIER_KEY STAR? NEWLINE)+
  private static boolean word_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "word_3")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = word_3_0(builder_, level_ + 1);
    int pos_ = current_position_(builder_);
    while (result_) {
      if (!word_3_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "word_3", pos_)) break;
      pos_ = current_position_(builder_);
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // IDENTIFIER_KEY STAR? NEWLINE
  private static boolean word_3_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "word_3_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, IDENTIFIER_KEY);
    result_ = result_ && word_3_0_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, NEWLINE);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // STAR?
  private static boolean word_3_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "word_3_0_1")) return false;
    consumeToken(builder_, STAR);
    return true;
  }

}
