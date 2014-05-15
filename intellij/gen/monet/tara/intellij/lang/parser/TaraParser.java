// This is a generated file. Not intended for manual editing.
package monet.tara.intellij.lang.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import com.intellij.openapi.diagnostic.Logger;
import static monet.tara.intellij.lang.psi.TaraTypes.*;
import static monet.tara.intellij.lang.parser.TaraParserUtil.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class TaraParser implements PsiParser {

  public static final Logger LOG_ = Logger.getInstance("monet.tara.intellij.lang.parser.TaraParser");

  public ASTNode parse(IElementType root_, PsiBuilder builder_) {
    boolean result_;
    builder_ = adapt_builder_(root_, builder_, this, null);
    Marker marker_ = enter_section_(builder_, 0, _COLLAPSE_, null);
    if (root_ == ANNOTATIONS) {
      result_ = annotations(builder_, 0);
    }
    else if (root_ == ATTRIBUTE) {
      result_ = attribute(builder_, 0);
    }
    else if (root_ == BODY) {
      result_ = body(builder_, 0);
    }
    else if (root_ == BOOLEAN_LIST) {
      result_ = booleanList(builder_, 0);
    }
    else if (root_ == BOOLEAN_VALUE) {
      result_ = booleanValue(builder_, 0);
    }
    else if (root_ == CONCEPT) {
      result_ = concept(builder_, 0);
    }
    else if (root_ == DOC) {
      result_ = doc(builder_, 0);
    }
    else if (root_ == DOUBLE_LIST) {
      result_ = doubleList(builder_, 0);
    }
    else if (root_ == DOUBLE_VALUE) {
      result_ = doubleValue(builder_, 0);
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
    else if (root_ == IMPORT_STATEMENT) {
      result_ = importStatement(builder_, 0);
    }
    else if (root_ == INTEGER_LIST) {
      result_ = integerList(builder_, 0);
    }
    else if (root_ == INTEGER_VALUE) {
      result_ = integerValue(builder_, 0);
    }
    else if (root_ == INTENTION) {
      result_ = intention(builder_, 0);
    }
    else if (root_ == MODIFIER) {
      result_ = modifier(builder_, 0);
    }
    else if (root_ == NATURAL_LIST) {
      result_ = naturalList(builder_, 0);
    }
    else if (root_ == NATURAL_VALUE) {
      result_ = naturalValue(builder_, 0);
    }
    else if (root_ == PACKET) {
      result_ = packet(builder_, 0);
    }
    else if (root_ == REFERENCE_STATEMENT) {
      result_ = referenceStatement(builder_, 0);
    }
    else if (root_ == SIGNATURE) {
      result_ = signature(builder_, 0);
    }
    else if (root_ == STRING_LIST) {
      result_ = stringList(builder_, 0);
    }
    else if (root_ == STRING_VALUE) {
      result_ = stringValue(builder_, 0);
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
  // VAR  STRING_TYPE ((IDENTIFIER_KEY (COLON stringValue)?) | (LIST IDENTIFIER_KEY (COLON stringList)?))
  static boolean StringAttribute(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "StringAttribute")) return false;
    if (!nextTokenIs(builder_, VAR)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeTokens(builder_, 2, VAR, STRING_TYPE);
    pinned_ = result_; // pin = 2
    result_ = result_ && StringAttribute_2(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, null, result_, pinned_, null);
    return result_ || pinned_;
  }

  // (IDENTIFIER_KEY (COLON stringValue)?) | (LIST IDENTIFIER_KEY (COLON stringList)?)
  private static boolean StringAttribute_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "StringAttribute_2")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = StringAttribute_2_0(builder_, level_ + 1);
    if (!result_) result_ = StringAttribute_2_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // IDENTIFIER_KEY (COLON stringValue)?
  private static boolean StringAttribute_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "StringAttribute_2_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, IDENTIFIER_KEY);
    result_ = result_ && StringAttribute_2_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (COLON stringValue)?
  private static boolean StringAttribute_2_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "StringAttribute_2_0_1")) return false;
    StringAttribute_2_0_1_0(builder_, level_ + 1);
    return true;
  }

  // COLON stringValue
  private static boolean StringAttribute_2_0_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "StringAttribute_2_0_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COLON);
    result_ = result_ && stringValue(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // LIST IDENTIFIER_KEY (COLON stringList)?
  private static boolean StringAttribute_2_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "StringAttribute_2_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, LIST, IDENTIFIER_KEY);
    result_ = result_ && StringAttribute_2_1_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (COLON stringList)?
  private static boolean StringAttribute_2_1_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "StringAttribute_2_1_2")) return false;
    StringAttribute_2_1_2_0(builder_, level_ + 1);
    return true;
  }

  // COLON stringList
  private static boolean StringAttribute_2_1_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "StringAttribute_2_1_2_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COLON);
    result_ = result_ && stringList(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // OPEN_AN (GENERIC | MULTIPLE | OPTIONAL | HAS_CODE | intention | SINGLETON | ROOT)+ CLOSE_AN
  public static boolean annotations(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "annotations")) return false;
    if (!nextTokenIs(builder_, OPEN_AN)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeToken(builder_, OPEN_AN);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, annotations_1(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, CLOSE_AN) && result_;
    exit_section_(builder_, level_, marker_, ANNOTATIONS, result_, pinned_, null);
    return result_ || pinned_;
  }

  // (GENERIC | MULTIPLE | OPTIONAL | HAS_CODE | intention | SINGLETON | ROOT)+
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

  // GENERIC | MULTIPLE | OPTIONAL | HAS_CODE | intention | SINGLETON | ROOT
  private static boolean annotations_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "annotations_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, GENERIC);
    if (!result_) result_ = consumeToken(builder_, MULTIPLE);
    if (!result_) result_ = consumeToken(builder_, OPTIONAL);
    if (!result_) result_ = consumeToken(builder_, HAS_CODE);
    if (!result_) result_ = intention(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, SINGLETON);
    if (!result_) result_ = consumeToken(builder_, ROOT);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // doc? ( uuidAttribute
  // 		           | integerAttribute
  // 		           | doubleAttribute
  // 		           | naturalAttribute
  // 		           | booleanAttribute
  // 		           | StringAttribute)
  public static boolean attribute(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute")) return false;
    if (!nextTokenIs(builder_, "<attribute>", DOC_LINE, VAR)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<attribute>");
    result_ = attribute_0(builder_, level_ + 1);
    result_ = result_ && attribute_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, ATTRIBUTE, result_, false, null);
    return result_;
  }

  // doc?
  private static boolean attribute_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_0")) return false;
    doc(builder_, level_ + 1);
    return true;
  }

  // uuidAttribute
  // 		           | integerAttribute
  // 		           | doubleAttribute
  // 		           | naturalAttribute
  // 		           | booleanAttribute
  // 		           | StringAttribute
  private static boolean attribute_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = uuidAttribute(builder_, level_ + 1);
    if (!result_) result_ = integerAttribute(builder_, level_ + 1);
    if (!result_) result_ = doubleAttribute(builder_, level_ + 1);
    if (!result_) result_ = naturalAttribute(builder_, level_ + 1);
    if (!result_) result_ = booleanAttribute(builder_, level_ + 1);
    if (!result_) result_ = StringAttribute(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // NEW_LINE_INDENT (conceptConstituents NEWLINE+)+ DEDENT
  public static boolean body(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "body")) return false;
    if (!nextTokenIs(builder_, NEW_LINE_INDENT)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, NEW_LINE_INDENT);
    result_ = result_ && body_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, DEDENT);
    exit_section_(builder_, marker_, BODY, result_);
    return result_;
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
  // VAR BOOLEAN_TYPE ((IDENTIFIER_KEY (COLON booleanValue)?) | (LIST IDENTIFIER_KEY (COLON booleanList)?))
  static boolean booleanAttribute(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "booleanAttribute")) return false;
    if (!nextTokenIs(builder_, VAR)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeTokens(builder_, 2, VAR, BOOLEAN_TYPE);
    pinned_ = result_; // pin = 2
    result_ = result_ && booleanAttribute_2(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, null, result_, pinned_, null);
    return result_ || pinned_;
  }

  // (IDENTIFIER_KEY (COLON booleanValue)?) | (LIST IDENTIFIER_KEY (COLON booleanList)?)
  private static boolean booleanAttribute_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "booleanAttribute_2")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = booleanAttribute_2_0(builder_, level_ + 1);
    if (!result_) result_ = booleanAttribute_2_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // IDENTIFIER_KEY (COLON booleanValue)?
  private static boolean booleanAttribute_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "booleanAttribute_2_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, IDENTIFIER_KEY);
    result_ = result_ && booleanAttribute_2_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (COLON booleanValue)?
  private static boolean booleanAttribute_2_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "booleanAttribute_2_0_1")) return false;
    booleanAttribute_2_0_1_0(builder_, level_ + 1);
    return true;
  }

  // COLON booleanValue
  private static boolean booleanAttribute_2_0_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "booleanAttribute_2_0_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COLON);
    result_ = result_ && booleanValue(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // LIST IDENTIFIER_KEY (COLON booleanList)?
  private static boolean booleanAttribute_2_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "booleanAttribute_2_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, LIST, IDENTIFIER_KEY);
    result_ = result_ && booleanAttribute_2_1_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (COLON booleanList)?
  private static boolean booleanAttribute_2_1_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "booleanAttribute_2_1_2")) return false;
    booleanAttribute_2_1_2_0(builder_, level_ + 1);
    return true;
  }

  // COLON booleanList
  private static boolean booleanAttribute_2_1_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "booleanAttribute_2_1_2_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COLON);
    result_ = result_ && booleanList(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // LEFT_SQUARE BOOLEAN_VALUE_KEY+ RIGHT_SQUARE
  public static boolean booleanList(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "booleanList")) return false;
    if (!nextTokenIs(builder_, LEFT_SQUARE)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, LEFT_SQUARE);
    result_ = result_ && booleanList_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RIGHT_SQUARE);
    exit_section_(builder_, marker_, BOOLEAN_LIST, result_);
    return result_;
  }

  // BOOLEAN_VALUE_KEY+
  private static boolean booleanList_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "booleanList_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, BOOLEAN_VALUE_KEY);
    int pos_ = current_position_(builder_);
    while (result_) {
      if (!consumeToken(builder_, BOOLEAN_VALUE_KEY)) break;
      if (!empty_element_parsed_guard_(builder_, "booleanList_1", pos_)) break;
      pos_ = current_position_(builder_);
    }
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
  // doc? signature annotations? body?
  public static boolean concept(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "concept")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<concept>");
    result_ = concept_0(builder_, level_ + 1);
    result_ = result_ && signature(builder_, level_ + 1);
    result_ = result_ && concept_2(builder_, level_ + 1);
    result_ = result_ && concept_3(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, CONCEPT, result_, false, null);
    return result_;
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
  // attribute
  // 		                      | referenceStatement
  // 		                      | word
  // 		                      | concept
  static boolean conceptConstituents(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "conceptConstituents")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = attribute(builder_, level_ + 1);
    if (!result_) result_ = referenceStatement(builder_, level_ + 1);
    if (!result_) result_ = word(builder_, level_ + 1);
    if (!result_) result_ = concept(builder_, level_ + 1);
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
  // VAR  DOUBLE_TYPE ((IDENTIFIER_KEY (COLON doubleValue)?)  | (LIST IDENTIFIER_KEY (COLON doubleList)?))
  static boolean doubleAttribute(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "doubleAttribute")) return false;
    if (!nextTokenIs(builder_, VAR)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeTokens(builder_, 2, VAR, DOUBLE_TYPE);
    pinned_ = result_; // pin = 2
    result_ = result_ && doubleAttribute_2(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, null, result_, pinned_, null);
    return result_ || pinned_;
  }

  // (IDENTIFIER_KEY (COLON doubleValue)?)  | (LIST IDENTIFIER_KEY (COLON doubleList)?)
  private static boolean doubleAttribute_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "doubleAttribute_2")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = doubleAttribute_2_0(builder_, level_ + 1);
    if (!result_) result_ = doubleAttribute_2_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // IDENTIFIER_KEY (COLON doubleValue)?
  private static boolean doubleAttribute_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "doubleAttribute_2_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, IDENTIFIER_KEY);
    result_ = result_ && doubleAttribute_2_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (COLON doubleValue)?
  private static boolean doubleAttribute_2_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "doubleAttribute_2_0_1")) return false;
    doubleAttribute_2_0_1_0(builder_, level_ + 1);
    return true;
  }

  // COLON doubleValue
  private static boolean doubleAttribute_2_0_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "doubleAttribute_2_0_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COLON);
    result_ = result_ && doubleValue(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // LIST IDENTIFIER_KEY (COLON doubleList)?
  private static boolean doubleAttribute_2_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "doubleAttribute_2_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, LIST, IDENTIFIER_KEY);
    result_ = result_ && doubleAttribute_2_1_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (COLON doubleList)?
  private static boolean doubleAttribute_2_1_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "doubleAttribute_2_1_2")) return false;
    doubleAttribute_2_1_2_0(builder_, level_ + 1);
    return true;
  }

  // COLON doubleList
  private static boolean doubleAttribute_2_1_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "doubleAttribute_2_1_2_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COLON);
    result_ = result_ && doubleList(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // LEFT_SQUARE (NATURAL_VALUE_KEY | NEGATIVE_VALUE_KEY | DOUBLE_VALUE_KEY)+ RIGHT_SQUARE
  public static boolean doubleList(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "doubleList")) return false;
    if (!nextTokenIs(builder_, LEFT_SQUARE)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, LEFT_SQUARE);
    result_ = result_ && doubleList_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RIGHT_SQUARE);
    exit_section_(builder_, marker_, DOUBLE_LIST, result_);
    return result_;
  }

  // (NATURAL_VALUE_KEY | NEGATIVE_VALUE_KEY | DOUBLE_VALUE_KEY)+
  private static boolean doubleList_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "doubleList_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = doubleList_1_0(builder_, level_ + 1);
    int pos_ = current_position_(builder_);
    while (result_) {
      if (!doubleList_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "doubleList_1", pos_)) break;
      pos_ = current_position_(builder_);
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // NATURAL_VALUE_KEY | NEGATIVE_VALUE_KEY | DOUBLE_VALUE_KEY
  private static boolean doubleList_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "doubleList_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, NATURAL_VALUE_KEY);
    if (!result_) result_ = consumeToken(builder_, NEGATIVE_VALUE_KEY);
    if (!result_) result_ = consumeToken(builder_, DOUBLE_VALUE_KEY);
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
  // packet? importStatement*
  public static boolean header(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "header")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<header>");
    result_ = header_0(builder_, level_ + 1);
    result_ = result_ && header_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, HEADER, result_, false, null);
    return result_;
  }

  // packet?
  private static boolean header_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "header_0")) return false;
    packet(builder_, level_ + 1);
    return true;
  }

  // importStatement*
  private static boolean header_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "header_1")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!importStatement(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "header_1", pos_)) break;
      pos_ = current_position_(builder_);
    }
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
  // NEWLINE IMPORT_KEY headerReference
  public static boolean importStatement(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "importStatement")) return false;
    if (!nextTokenIs(builder_, NEWLINE)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, NEWLINE, IMPORT_KEY);
    result_ = result_ && headerReference(builder_, level_ + 1);
    exit_section_(builder_, marker_, IMPORT_STATEMENT, result_);
    return result_;
  }

  /* ********************************************************** */
  // VAR INT_TYPE ((IDENTIFIER_KEY (COLON integerValue)?) | (LIST IDENTIFIER_KEY (COLON integerList)?))
  static boolean integerAttribute(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "integerAttribute")) return false;
    if (!nextTokenIs(builder_, VAR)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeTokens(builder_, 2, VAR, INT_TYPE);
    pinned_ = result_; // pin = 2
    result_ = result_ && integerAttribute_2(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, null, result_, pinned_, null);
    return result_ || pinned_;
  }

  // (IDENTIFIER_KEY (COLON integerValue)?) | (LIST IDENTIFIER_KEY (COLON integerList)?)
  private static boolean integerAttribute_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "integerAttribute_2")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = integerAttribute_2_0(builder_, level_ + 1);
    if (!result_) result_ = integerAttribute_2_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // IDENTIFIER_KEY (COLON integerValue)?
  private static boolean integerAttribute_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "integerAttribute_2_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, IDENTIFIER_KEY);
    result_ = result_ && integerAttribute_2_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (COLON integerValue)?
  private static boolean integerAttribute_2_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "integerAttribute_2_0_1")) return false;
    integerAttribute_2_0_1_0(builder_, level_ + 1);
    return true;
  }

  // COLON integerValue
  private static boolean integerAttribute_2_0_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "integerAttribute_2_0_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COLON);
    result_ = result_ && integerValue(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // LIST IDENTIFIER_KEY (COLON integerList)?
  private static boolean integerAttribute_2_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "integerAttribute_2_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, LIST, IDENTIFIER_KEY);
    result_ = result_ && integerAttribute_2_1_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (COLON integerList)?
  private static boolean integerAttribute_2_1_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "integerAttribute_2_1_2")) return false;
    integerAttribute_2_1_2_0(builder_, level_ + 1);
    return true;
  }

  // COLON integerList
  private static boolean integerAttribute_2_1_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "integerAttribute_2_1_2_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COLON);
    result_ = result_ && integerList(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // LEFT_SQUARE (NATURAL_VALUE_KEY | NEGATIVE_VALUE_KEY)+ RIGHT_SQUARE
  public static boolean integerList(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "integerList")) return false;
    if (!nextTokenIs(builder_, LEFT_SQUARE)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, LEFT_SQUARE);
    result_ = result_ && integerList_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RIGHT_SQUARE);
    exit_section_(builder_, marker_, INTEGER_LIST, result_);
    return result_;
  }

  // (NATURAL_VALUE_KEY | NEGATIVE_VALUE_KEY)+
  private static boolean integerList_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "integerList_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = integerList_1_0(builder_, level_ + 1);
    int pos_ = current_position_(builder_);
    while (result_) {
      if (!integerList_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "integerList_1", pos_)) break;
      pos_ = current_position_(builder_);
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // NATURAL_VALUE_KEY | NEGATIVE_VALUE_KEY
  private static boolean integerList_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "integerList_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, NATURAL_VALUE_KEY);
    if (!result_) result_ = consumeToken(builder_, NEGATIVE_VALUE_KEY);
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
  // INTENTION_KEY
  public static boolean intention(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "intention")) return false;
    if (!nextTokenIs(builder_, INTENTION_KEY)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, INTENTION_KEY);
    exit_section_(builder_, marker_, INTENTION, result_);
    return result_;
  }

  /* ********************************************************** */
  // ABSTRACT
  //           | FINAL
  //           | BASE_KEY
  public static boolean modifier(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "modifier")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<modifier>");
    result_ = consumeToken(builder_, ABSTRACT);
    if (!result_) result_ = consumeToken(builder_, FINAL);
    if (!result_) result_ = consumeToken(builder_, BASE_KEY);
    exit_section_(builder_, level_, marker_, MODIFIER, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // VAR NATURAL_TYPE ((IDENTIFIER_KEY (COLON naturalValue)?) | (LIST IDENTIFIER_KEY (COLON naturalList)?))
  static boolean naturalAttribute(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "naturalAttribute")) return false;
    if (!nextTokenIs(builder_, VAR)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeTokens(builder_, 2, VAR, NATURAL_TYPE);
    pinned_ = result_; // pin = 2
    result_ = result_ && naturalAttribute_2(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, null, result_, pinned_, null);
    return result_ || pinned_;
  }

  // (IDENTIFIER_KEY (COLON naturalValue)?) | (LIST IDENTIFIER_KEY (COLON naturalList)?)
  private static boolean naturalAttribute_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "naturalAttribute_2")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = naturalAttribute_2_0(builder_, level_ + 1);
    if (!result_) result_ = naturalAttribute_2_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // IDENTIFIER_KEY (COLON naturalValue)?
  private static boolean naturalAttribute_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "naturalAttribute_2_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, IDENTIFIER_KEY);
    result_ = result_ && naturalAttribute_2_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (COLON naturalValue)?
  private static boolean naturalAttribute_2_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "naturalAttribute_2_0_1")) return false;
    naturalAttribute_2_0_1_0(builder_, level_ + 1);
    return true;
  }

  // COLON naturalValue
  private static boolean naturalAttribute_2_0_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "naturalAttribute_2_0_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COLON);
    result_ = result_ && naturalValue(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // LIST IDENTIFIER_KEY (COLON naturalList)?
  private static boolean naturalAttribute_2_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "naturalAttribute_2_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, LIST, IDENTIFIER_KEY);
    result_ = result_ && naturalAttribute_2_1_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (COLON naturalList)?
  private static boolean naturalAttribute_2_1_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "naturalAttribute_2_1_2")) return false;
    naturalAttribute_2_1_2_0(builder_, level_ + 1);
    return true;
  }

  // COLON naturalList
  private static boolean naturalAttribute_2_1_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "naturalAttribute_2_1_2_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COLON);
    result_ = result_ && naturalList(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // LEFT_SQUARE NATURAL_VALUE_KEY+ RIGHT_SQUARE
  public static boolean naturalList(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "naturalList")) return false;
    if (!nextTokenIs(builder_, LEFT_SQUARE)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, LEFT_SQUARE);
    result_ = result_ && naturalList_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RIGHT_SQUARE);
    exit_section_(builder_, marker_, NATURAL_LIST, result_);
    return result_;
  }

  // NATURAL_VALUE_KEY+
  private static boolean naturalList_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "naturalList_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, NATURAL_VALUE_KEY);
    int pos_ = current_position_(builder_);
    while (result_) {
      if (!consumeToken(builder_, NATURAL_VALUE_KEY)) break;
      if (!empty_element_parsed_guard_(builder_, "naturalList_1", pos_)) break;
      pos_ = current_position_(builder_);
    }
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
  // PACKAGE headerReference
  public static boolean packet(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "packet")) return false;
    if (!nextTokenIs(builder_, PACKAGE)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, PACKAGE);
    result_ = result_ && headerReference(builder_, level_ + 1);
    exit_section_(builder_, marker_, PACKET, result_);
    return result_;
  }

  /* ********************************************************** */
  // doc? VAR identifierReference LIST? IDENTIFIER_KEY
  public static boolean referenceStatement(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "referenceStatement")) return false;
    if (!nextTokenIs(builder_, "<reference statement>", DOC_LINE, VAR)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<reference statement>");
    result_ = referenceStatement_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, VAR);
    result_ = result_ && identifierReference(builder_, level_ + 1);
    result_ = result_ && referenceStatement_3(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, IDENTIFIER_KEY);
    exit_section_(builder_, level_, marker_, REFERENCE_STATEMENT, result_, false, null);
    return result_;
  }

  // doc?
  private static boolean referenceStatement_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "referenceStatement_0")) return false;
    doc(builder_, level_ + 1);
    return true;
  }

  // LIST?
  private static boolean referenceStatement_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "referenceStatement_3")) return false;
    consumeToken(builder_, LIST);
    return true;
  }

  /* ********************************************************** */
  // NEWLINE* header? NEWLINE* concept?  NEWLINE*
  static boolean root(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "root")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = root_0(builder_, level_ + 1);
    result_ = result_ && root_1(builder_, level_ + 1);
    result_ = result_ && root_2(builder_, level_ + 1);
    result_ = result_ && root_3(builder_, level_ + 1);
    result_ = result_ && root_4(builder_, level_ + 1);
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

  // NEWLINE*
  private static boolean root_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "root_2")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!consumeToken(builder_, NEWLINE)) break;
      if (!empty_element_parsed_guard_(builder_, "root_2", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  // concept?
  private static boolean root_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "root_3")) return false;
    concept(builder_, level_ + 1);
    return true;
  }

  // NEWLINE*
  private static boolean root_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "root_4")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!consumeToken(builder_, NEWLINE)) break;
      if (!empty_element_parsed_guard_(builder_, "root_4", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  /* ********************************************************** */
  // (CASE_KEY identifier)
  //          | (CONCEPT_KEY modifier? identifier)
  //          | (CONCEPT_KEY COLON identifierReference (modifier? identifier)?)
  public static boolean signature(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "signature")) return false;
    if (!nextTokenIs(builder_, "<signature>", CASE_KEY, CONCEPT_KEY)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<signature>");
    result_ = signature_0(builder_, level_ + 1);
    if (!result_) result_ = signature_1(builder_, level_ + 1);
    if (!result_) result_ = signature_2(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, SIGNATURE, result_, false, null);
    return result_;
  }

  // CASE_KEY identifier
  private static boolean signature_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "signature_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, CASE_KEY);
    result_ = result_ && identifier(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // CONCEPT_KEY modifier? identifier
  private static boolean signature_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "signature_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, CONCEPT_KEY);
    result_ = result_ && signature_1_1(builder_, level_ + 1);
    result_ = result_ && identifier(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // modifier?
  private static boolean signature_1_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "signature_1_1")) return false;
    modifier(builder_, level_ + 1);
    return true;
  }

  // CONCEPT_KEY COLON identifierReference (modifier? identifier)?
  private static boolean signature_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "signature_2")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, CONCEPT_KEY, COLON);
    result_ = result_ && identifierReference(builder_, level_ + 1);
    result_ = result_ && signature_2_3(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (modifier? identifier)?
  private static boolean signature_2_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "signature_2_3")) return false;
    signature_2_3_0(builder_, level_ + 1);
    return true;
  }

  // modifier? identifier
  private static boolean signature_2_3_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "signature_2_3_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = signature_2_3_0_0(builder_, level_ + 1);
    result_ = result_ && identifier(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // modifier?
  private static boolean signature_2_3_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "signature_2_3_0_0")) return false;
    modifier(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // LEFT_SQUARE STRING_VALUE_KEY+ RIGHT_SQUARE
  public static boolean stringList(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "stringList")) return false;
    if (!nextTokenIs(builder_, LEFT_SQUARE)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, LEFT_SQUARE);
    result_ = result_ && stringList_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RIGHT_SQUARE);
    exit_section_(builder_, marker_, STRING_LIST, result_);
    return result_;
  }

  // STRING_VALUE_KEY+
  private static boolean stringList_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "stringList_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, STRING_VALUE_KEY);
    int pos_ = current_position_(builder_);
    while (result_) {
      if (!consumeToken(builder_, STRING_VALUE_KEY)) break;
      if (!empty_element_parsed_guard_(builder_, "stringList_1", pos_)) break;
      pos_ = current_position_(builder_);
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // STRING_VALUE_KEY
  public static boolean stringValue(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "stringValue")) return false;
    if (!nextTokenIs(builder_, STRING_VALUE_KEY)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, STRING_VALUE_KEY);
    exit_section_(builder_, marker_, STRING_VALUE, result_);
    return result_;
  }

  /* ********************************************************** */
  // VAR     UID_TYPE IDENTIFIER_KEY (COLON stringValue)?
  static boolean uuidAttribute(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "uuidAttribute")) return false;
    if (!nextTokenIs(builder_, VAR)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeTokens(builder_, 3, VAR, UID_TYPE, IDENTIFIER_KEY);
    pinned_ = result_; // pin = 3
    result_ = result_ && uuidAttribute_3(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, null, result_, pinned_, null);
    return result_ || pinned_;
  }

  // (COLON stringValue)?
  private static boolean uuidAttribute_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "uuidAttribute_3")) return false;
    uuidAttribute_3_0(builder_, level_ + 1);
    return true;
  }

  // COLON stringValue
  private static boolean uuidAttribute_3_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "uuidAttribute_3_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COLON);
    result_ = result_ && stringValue(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // doc? VAR WORD_KEY IDENTIFIER_KEY NEW_LINE_INDENT (IDENTIFIER_KEY NEWLINE)+ DEDENT
  public static boolean word(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "word")) return false;
    if (!nextTokenIs(builder_, "<word>", DOC_LINE, VAR)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<word>");
    result_ = word_0(builder_, level_ + 1);
    result_ = result_ && consumeTokens(builder_, 0, VAR, WORD_KEY, IDENTIFIER_KEY, NEW_LINE_INDENT);
    result_ = result_ && word_5(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, DEDENT);
    exit_section_(builder_, level_, marker_, WORD, result_, false, null);
    return result_;
  }

  // doc?
  private static boolean word_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "word_0")) return false;
    doc(builder_, level_ + 1);
    return true;
  }

  // (IDENTIFIER_KEY NEWLINE)+
  private static boolean word_5(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "word_5")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = word_5_0(builder_, level_ + 1);
    int pos_ = current_position_(builder_);
    while (result_) {
      if (!word_5_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "word_5", pos_)) break;
      pos_ = current_position_(builder_);
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // IDENTIFIER_KEY NEWLINE
  private static boolean word_5_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "word_5_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, IDENTIFIER_KEY, NEWLINE);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

}
