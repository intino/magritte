// This is a generated file. Not intended for manual editing.
package monet.tara.compiler.intellij.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import com.intellij.openapi.diagnostic.Logger;
import static monet.tara.compiler.intellij.psi.TaraTypes.*;
import static monet.tara.intellij.parser.TaraParserUtil.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class TaraParser implements PsiParser {

  public static final Logger LOG_ = Logger.getInstance("monet.tara.compiler.intellij.parser.TaraParser");

  public ASTNode parse(IElementType root_, PsiBuilder builder_) {
    boolean result_;
    builder_ = adapt_builder_(root_, builder_, this, null);
    Marker marker_ = enter_section_(builder_, 0, _COLLAPSE_, null);
    if (root_ == ATTRIBUTE) {
      result_ = attribute(builder_, 0);
    }
    else if (root_ == BOOLEAN_ASSIGN) {
      result_ = booleanAssign(builder_, 0);
    }
    else if (root_ == BOOLEAN_LIST_ASSIGN) {
      result_ = booleanListAssign(builder_, 0);
    }
    else if (root_ == COMPONENT) {
      result_ = component(builder_, 0);
    }
    else if (root_ == COMPONENT_ANNOTATIONS) {
      result_ = componentAnnotations(builder_, 0);
    }
    else if (root_ == CONCEPT) {
      result_ = concept(builder_, 0);
    }
    else if (root_ == CONCEPT_ANNOTATIONS) {
      result_ = conceptAnnotations(builder_, 0);
    }
    else if (root_ == CONCEPT_BODY) {
      result_ = conceptBody(builder_, 0);
    }
    else if (root_ == CONCEPT_CONSTITUENTS) {
      result_ = conceptConstituents(builder_, 0);
    }
    else if (root_ == CONCEPT_SIGNATURE) {
      result_ = conceptSignature(builder_, 0);
    }
    else if (root_ == DOC) {
      result_ = doc(builder_, 0);
    }
    else if (root_ == DOUBLE_ASSIGN) {
      result_ = doubleAssign(builder_, 0);
    }
    else if (root_ == DOUBLE_LIST_ASSIGN) {
      result_ = doubleListAssign(builder_, 0);
    }
    else if (root_ == EXTENDED_CONCEPT) {
      result_ = extendedConcept(builder_, 0);
    }
    else if (root_ == FROM) {
      result_ = from(builder_, 0);
    }
    else if (root_ == FROM_ANNOTATIONS) {
      result_ = fromAnnotations(builder_, 0);
    }
    else if (root_ == FROM_BODY) {
      result_ = fromBody(builder_, 0);
    }
    else if (root_ == FROM_COMPONENT) {
      result_ = fromComponent(builder_, 0);
    }
    else if (root_ == FROM_COMPONENT_ANNOTATIONS) {
      result_ = fromComponentAnnotations(builder_, 0);
    }
    else if (root_ == IDENTIFIER) {
      result_ = identifier(builder_, 0);
    }
    else if (root_ == INTEGER_ASSIGN) {
      result_ = integerAssign(builder_, 0);
    }
    else if (root_ == INTEGER_LIST_ASSIGN) {
      result_ = integerListAssign(builder_, 0);
    }
    else if (root_ == INTEGER_VALUE) {
      result_ = integerValue(builder_, 0);
    }
    else if (root_ == MODIFIER) {
      result_ = modifier(builder_, 0);
    }
    else if (root_ == NATURAL_ASSIGN) {
      result_ = naturalAssign(builder_, 0);
    }
    else if (root_ == NATURAL_LIST_ASSIGN) {
      result_ = naturalListAssign(builder_, 0);
    }
    else if (root_ == REFERENCE_STATEMENT) {
      result_ = referenceStatement(builder_, 0);
    }
    else if (root_ == STRING_ASSIGN) {
      result_ = stringAssign(builder_, 0);
    }
    else if (root_ == STRING_LIST_ASSIGN) {
      result_ = stringListAssign(builder_, 0);
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
  // VAR    UID_TYPE IDENTIFIER_KEY stringAssign?
  //            | VAR     INT_TYPE (IDENTIFIER_KEY integerAssign? | LIST IDENTIFIER_KEY integerListAssign?)
  //            | VAR  DOUBLE_TYPE (IDENTIFIER_KEY doubleAssign?  | LIST IDENTIFIER_KEY doubleListAssign?)
  //            | VAR NATURAL_TYPE (IDENTIFIER_KEY naturalAssign? | LIST IDENTIFIER_KEY naturalListAssign?)
  //            | VAR BOOLEAN_TYPE (IDENTIFIER_KEY booleanAssign? | LIST IDENTIFIER_KEY booleanListAssign?)
  //            | VAR  STRING_TYPE (IDENTIFIER_KEY stringAssign?  | LIST IDENTIFIER_KEY stringListAssign?)
  public static boolean attribute(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute")) return false;
    if (!nextTokenIs(builder_, VAR)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = attribute_0(builder_, level_ + 1);
    if (!result_) result_ = attribute_1(builder_, level_ + 1);
    if (!result_) result_ = attribute_2(builder_, level_ + 1);
    if (!result_) result_ = attribute_3(builder_, level_ + 1);
    if (!result_) result_ = attribute_4(builder_, level_ + 1);
    if (!result_) result_ = attribute_5(builder_, level_ + 1);
    exit_section_(builder_, marker_, ATTRIBUTE, result_);
    return result_;
  }

  // VAR    UID_TYPE IDENTIFIER_KEY stringAssign?
  private static boolean attribute_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, VAR, UID_TYPE, IDENTIFIER_KEY);
    result_ = result_ && attribute_0_3(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // stringAssign?
  private static boolean attribute_0_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_0_3")) return false;
    stringAssign(builder_, level_ + 1);
    return true;
  }

  // VAR     INT_TYPE (IDENTIFIER_KEY integerAssign? | LIST IDENTIFIER_KEY integerListAssign?)
  private static boolean attribute_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, VAR, INT_TYPE);
    result_ = result_ && attribute_1_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // IDENTIFIER_KEY integerAssign? | LIST IDENTIFIER_KEY integerListAssign?
  private static boolean attribute_1_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_1_2")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = attribute_1_2_0(builder_, level_ + 1);
    if (!result_) result_ = attribute_1_2_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // IDENTIFIER_KEY integerAssign?
  private static boolean attribute_1_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_1_2_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, IDENTIFIER_KEY);
    result_ = result_ && attribute_1_2_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // integerAssign?
  private static boolean attribute_1_2_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_1_2_0_1")) return false;
    integerAssign(builder_, level_ + 1);
    return true;
  }

  // LIST IDENTIFIER_KEY integerListAssign?
  private static boolean attribute_1_2_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_1_2_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, LIST, IDENTIFIER_KEY);
    result_ = result_ && attribute_1_2_1_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // integerListAssign?
  private static boolean attribute_1_2_1_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_1_2_1_2")) return false;
    integerListAssign(builder_, level_ + 1);
    return true;
  }

  // VAR  DOUBLE_TYPE (IDENTIFIER_KEY doubleAssign?  | LIST IDENTIFIER_KEY doubleListAssign?)
  private static boolean attribute_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_2")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, VAR, DOUBLE_TYPE);
    result_ = result_ && attribute_2_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // IDENTIFIER_KEY doubleAssign?  | LIST IDENTIFIER_KEY doubleListAssign?
  private static boolean attribute_2_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_2_2")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = attribute_2_2_0(builder_, level_ + 1);
    if (!result_) result_ = attribute_2_2_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // IDENTIFIER_KEY doubleAssign?
  private static boolean attribute_2_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_2_2_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, IDENTIFIER_KEY);
    result_ = result_ && attribute_2_2_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // doubleAssign?
  private static boolean attribute_2_2_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_2_2_0_1")) return false;
    doubleAssign(builder_, level_ + 1);
    return true;
  }

  // LIST IDENTIFIER_KEY doubleListAssign?
  private static boolean attribute_2_2_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_2_2_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, LIST, IDENTIFIER_KEY);
    result_ = result_ && attribute_2_2_1_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // doubleListAssign?
  private static boolean attribute_2_2_1_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_2_2_1_2")) return false;
    doubleListAssign(builder_, level_ + 1);
    return true;
  }

  // VAR NATURAL_TYPE (IDENTIFIER_KEY naturalAssign? | LIST IDENTIFIER_KEY naturalListAssign?)
  private static boolean attribute_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_3")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, VAR, NATURAL_TYPE);
    result_ = result_ && attribute_3_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // IDENTIFIER_KEY naturalAssign? | LIST IDENTIFIER_KEY naturalListAssign?
  private static boolean attribute_3_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_3_2")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = attribute_3_2_0(builder_, level_ + 1);
    if (!result_) result_ = attribute_3_2_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // IDENTIFIER_KEY naturalAssign?
  private static boolean attribute_3_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_3_2_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, IDENTIFIER_KEY);
    result_ = result_ && attribute_3_2_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // naturalAssign?
  private static boolean attribute_3_2_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_3_2_0_1")) return false;
    naturalAssign(builder_, level_ + 1);
    return true;
  }

  // LIST IDENTIFIER_KEY naturalListAssign?
  private static boolean attribute_3_2_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_3_2_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, LIST, IDENTIFIER_KEY);
    result_ = result_ && attribute_3_2_1_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // naturalListAssign?
  private static boolean attribute_3_2_1_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_3_2_1_2")) return false;
    naturalListAssign(builder_, level_ + 1);
    return true;
  }

  // VAR BOOLEAN_TYPE (IDENTIFIER_KEY booleanAssign? | LIST IDENTIFIER_KEY booleanListAssign?)
  private static boolean attribute_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_4")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, VAR, BOOLEAN_TYPE);
    result_ = result_ && attribute_4_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // IDENTIFIER_KEY booleanAssign? | LIST IDENTIFIER_KEY booleanListAssign?
  private static boolean attribute_4_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_4_2")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = attribute_4_2_0(builder_, level_ + 1);
    if (!result_) result_ = attribute_4_2_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // IDENTIFIER_KEY booleanAssign?
  private static boolean attribute_4_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_4_2_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, IDENTIFIER_KEY);
    result_ = result_ && attribute_4_2_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // booleanAssign?
  private static boolean attribute_4_2_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_4_2_0_1")) return false;
    booleanAssign(builder_, level_ + 1);
    return true;
  }

  // LIST IDENTIFIER_KEY booleanListAssign?
  private static boolean attribute_4_2_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_4_2_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, LIST, IDENTIFIER_KEY);
    result_ = result_ && attribute_4_2_1_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // booleanListAssign?
  private static boolean attribute_4_2_1_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_4_2_1_2")) return false;
    booleanListAssign(builder_, level_ + 1);
    return true;
  }

  // VAR  STRING_TYPE (IDENTIFIER_KEY stringAssign?  | LIST IDENTIFIER_KEY stringListAssign?)
  private static boolean attribute_5(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_5")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, VAR, STRING_TYPE);
    result_ = result_ && attribute_5_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // IDENTIFIER_KEY stringAssign?  | LIST IDENTIFIER_KEY stringListAssign?
  private static boolean attribute_5_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_5_2")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = attribute_5_2_0(builder_, level_ + 1);
    if (!result_) result_ = attribute_5_2_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // IDENTIFIER_KEY stringAssign?
  private static boolean attribute_5_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_5_2_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, IDENTIFIER_KEY);
    result_ = result_ && attribute_5_2_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // stringAssign?
  private static boolean attribute_5_2_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_5_2_0_1")) return false;
    stringAssign(builder_, level_ + 1);
    return true;
  }

  // LIST IDENTIFIER_KEY stringListAssign?
  private static boolean attribute_5_2_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_5_2_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, LIST, IDENTIFIER_KEY);
    result_ = result_ && attribute_5_2_1_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // stringListAssign?
  private static boolean attribute_5_2_1_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_5_2_1_2")) return false;
    stringListAssign(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // ASSIGN BOOLEAN_VALUE
  public static boolean booleanAssign(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "booleanAssign")) return false;
    if (!nextTokenIs(builder_, ASSIGN)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, ASSIGN, BOOLEAN_VALUE);
    exit_section_(builder_, marker_, BOOLEAN_ASSIGN, result_);
    return result_;
  }

  /* ********************************************************** */
  // ASSIGN LEFT_SQUARE BOOLEAN_VALUE+ RIGHT_SQUARE
  public static boolean booleanListAssign(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "booleanListAssign")) return false;
    if (!nextTokenIs(builder_, ASSIGN)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, ASSIGN, LEFT_SQUARE);
    result_ = result_ && booleanListAssign_2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RIGHT_SQUARE);
    exit_section_(builder_, marker_, BOOLEAN_LIST_ASSIGN, result_);
    return result_;
  }

  // BOOLEAN_VALUE+
  private static boolean booleanListAssign_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "booleanListAssign_2")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, BOOLEAN_VALUE);
    int pos_ = current_position_(builder_);
    while (result_) {
      if (!consumeToken(builder_, BOOLEAN_VALUE)) break;
      if (!empty_element_parsed_guard_(builder_, "booleanListAssign_2", pos_)) break;
      pos_ = current_position_(builder_);
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // doc? conceptSignature componentAnnotations? conceptBody?
  //             | NEW extendedConcept componentAnnotations?
  public static boolean component(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "component")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<component>");
    result_ = component_0(builder_, level_ + 1);
    if (!result_) result_ = component_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, COMPONENT, result_, false, null);
    return result_;
  }

  // doc? conceptSignature componentAnnotations? conceptBody?
  private static boolean component_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "component_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = component_0_0(builder_, level_ + 1);
    result_ = result_ && conceptSignature(builder_, level_ + 1);
    result_ = result_ && component_0_2(builder_, level_ + 1);
    result_ = result_ && component_0_3(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // doc?
  private static boolean component_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "component_0_0")) return false;
    doc(builder_, level_ + 1);
    return true;
  }

  // componentAnnotations?
  private static boolean component_0_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "component_0_2")) return false;
    componentAnnotations(builder_, level_ + 1);
    return true;
  }

  // conceptBody?
  private static boolean component_0_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "component_0_3")) return false;
    conceptBody(builder_, level_ + 1);
    return true;
  }

  // NEW extendedConcept componentAnnotations?
  private static boolean component_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "component_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, NEW);
    result_ = result_ && extendedConcept(builder_, level_ + 1);
    result_ = result_ && component_1_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // componentAnnotations?
  private static boolean component_1_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "component_1_2")) return false;
    componentAnnotations(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // OPEN_AN (MULTIPLE | OPTIONAL | HAS_CODE | EXTENSIBLE| SINGLETON)+ CLOSE_AN
  public static boolean componentAnnotations(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "componentAnnotations")) return false;
    if (!nextTokenIs(builder_, OPEN_AN)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, OPEN_AN);
    result_ = result_ && componentAnnotations_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, CLOSE_AN);
    exit_section_(builder_, marker_, COMPONENT_ANNOTATIONS, result_);
    return result_;
  }

  // (MULTIPLE | OPTIONAL | HAS_CODE | EXTENSIBLE| SINGLETON)+
  private static boolean componentAnnotations_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "componentAnnotations_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = componentAnnotations_1_0(builder_, level_ + 1);
    int pos_ = current_position_(builder_);
    while (result_) {
      if (!componentAnnotations_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "componentAnnotations_1", pos_)) break;
      pos_ = current_position_(builder_);
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // MULTIPLE | OPTIONAL | HAS_CODE | EXTENSIBLE| SINGLETON
  private static boolean componentAnnotations_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "componentAnnotations_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, MULTIPLE);
    if (!result_) result_ = consumeToken(builder_, OPTIONAL);
    if (!result_) result_ = consumeToken(builder_, HAS_CODE);
    if (!result_) result_ = consumeToken(builder_, EXTENSIBLE);
    if (!result_) result_ = consumeToken(builder_, SINGLETON);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // doc? conceptSignature conceptAnnotations? conceptBody?
  public static boolean concept(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "concept")) return false;
    if (!nextTokenIs(builder_, "<concept>", CONCEPT_KEY, DOC_LINE)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<concept>");
    result_ = concept_0(builder_, level_ + 1);
    result_ = result_ && conceptSignature(builder_, level_ + 1);
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

  // conceptAnnotations?
  private static boolean concept_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "concept_2")) return false;
    conceptAnnotations(builder_, level_ + 1);
    return true;
  }

  // conceptBody?
  private static boolean concept_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "concept_3")) return false;
    conceptBody(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // OPEN_AN (ROOT | HAS_CODE | EXTENSIBLE | SINGLETON)+ CLOSE_AN
  public static boolean conceptAnnotations(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "conceptAnnotations")) return false;
    if (!nextTokenIs(builder_, OPEN_AN)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, OPEN_AN);
    result_ = result_ && conceptAnnotations_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, CLOSE_AN);
    exit_section_(builder_, marker_, CONCEPT_ANNOTATIONS, result_);
    return result_;
  }

  // (ROOT | HAS_CODE | EXTENSIBLE | SINGLETON)+
  private static boolean conceptAnnotations_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "conceptAnnotations_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = conceptAnnotations_1_0(builder_, level_ + 1);
    int pos_ = current_position_(builder_);
    while (result_) {
      if (!conceptAnnotations_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "conceptAnnotations_1", pos_)) break;
      pos_ = current_position_(builder_);
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ROOT | HAS_CODE | EXTENSIBLE | SINGLETON
  private static boolean conceptAnnotations_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "conceptAnnotations_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, ROOT);
    if (!result_) result_ = consumeToken(builder_, HAS_CODE);
    if (!result_) result_ = consumeToken(builder_, EXTENSIBLE);
    if (!result_) result_ = consumeToken(builder_, SINGLETON);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // NEW_LINE_INDENT (conceptConstituents NEWLINE+)+ DEDENT
  public static boolean conceptBody(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "conceptBody")) return false;
    if (!nextTokenIs(builder_, NEW_LINE_INDENT)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, NEW_LINE_INDENT);
    result_ = result_ && conceptBody_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, DEDENT);
    exit_section_(builder_, marker_, CONCEPT_BODY, result_);
    return result_;
  }

  // (conceptConstituents NEWLINE+)+
  private static boolean conceptBody_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "conceptBody_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = conceptBody_1_0(builder_, level_ + 1);
    int pos_ = current_position_(builder_);
    while (result_) {
      if (!conceptBody_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "conceptBody_1", pos_)) break;
      pos_ = current_position_(builder_);
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // conceptConstituents NEWLINE+
  private static boolean conceptBody_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "conceptBody_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = conceptConstituents(builder_, level_ + 1);
    result_ = result_ && conceptBody_1_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // NEWLINE+
  private static boolean conceptBody_1_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "conceptBody_1_0_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, NEWLINE);
    int pos_ = current_position_(builder_);
    while (result_) {
      if (!consumeToken(builder_, NEWLINE)) break;
      if (!empty_element_parsed_guard_(builder_, "conceptBody_1_0_1", pos_)) break;
      pos_ = current_position_(builder_);
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // attribute
  //                       | referenceStatement
  //                       | word
  //                       | from
  //                       | component
  public static boolean conceptConstituents(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "conceptConstituents")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<concept constituents>");
    result_ = attribute(builder_, level_ + 1);
    if (!result_) result_ = referenceStatement(builder_, level_ + 1);
    if (!result_) result_ = word(builder_, level_ + 1);
    if (!result_) result_ = from(builder_, level_ + 1);
    if (!result_) result_ = component(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, CONCEPT_CONSTITUENTS, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // CONCEPT_KEY extendedConcept? modifier? AS identifier
  public static boolean conceptSignature(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "conceptSignature")) return false;
    if (!nextTokenIs(builder_, CONCEPT_KEY)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, CONCEPT_KEY);
    result_ = result_ && conceptSignature_1(builder_, level_ + 1);
    result_ = result_ && conceptSignature_2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, AS);
    result_ = result_ && identifier(builder_, level_ + 1);
    exit_section_(builder_, marker_, CONCEPT_SIGNATURE, result_);
    return result_;
  }

  // extendedConcept?
  private static boolean conceptSignature_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "conceptSignature_1")) return false;
    extendedConcept(builder_, level_ + 1);
    return true;
  }

  // modifier?
  private static boolean conceptSignature_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "conceptSignature_2")) return false;
    modifier(builder_, level_ + 1);
    return true;
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
  // ASSIGN (integerValue | DOUBLE_VALUE)
  public static boolean doubleAssign(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "doubleAssign")) return false;
    if (!nextTokenIs(builder_, ASSIGN)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, ASSIGN);
    result_ = result_ && doubleAssign_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, DOUBLE_ASSIGN, result_);
    return result_;
  }

  // integerValue | DOUBLE_VALUE
  private static boolean doubleAssign_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "doubleAssign_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = integerValue(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, DOUBLE_VALUE);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // ASSIGN LEFT_SQUARE (integerValue | DOUBLE_VALUE)+ RIGHT_SQUARE
  public static boolean doubleListAssign(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "doubleListAssign")) return false;
    if (!nextTokenIs(builder_, ASSIGN)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, ASSIGN, LEFT_SQUARE);
    result_ = result_ && doubleListAssign_2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RIGHT_SQUARE);
    exit_section_(builder_, marker_, DOUBLE_LIST_ASSIGN, result_);
    return result_;
  }

  // (integerValue | DOUBLE_VALUE)+
  private static boolean doubleListAssign_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "doubleListAssign_2")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = doubleListAssign_2_0(builder_, level_ + 1);
    int pos_ = current_position_(builder_);
    while (result_) {
      if (!doubleListAssign_2_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "doubleListAssign_2", pos_)) break;
      pos_ = current_position_(builder_);
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // integerValue | DOUBLE_VALUE
  private static boolean doubleListAssign_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "doubleListAssign_2_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = integerValue(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, DOUBLE_VALUE);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // identifier (DOT identifier)*
  public static boolean extendedConcept(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "extendedConcept")) return false;
    if (!nextTokenIs(builder_, IDENTIFIER_KEY)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = identifier(builder_, level_ + 1);
    result_ = result_ && extendedConcept_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, EXTENDED_CONCEPT, result_);
    return result_;
  }

  // (DOT identifier)*
  private static boolean extendedConcept_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "extendedConcept_1")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!extendedConcept_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "extendedConcept_1", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  // DOT identifier
  private static boolean extendedConcept_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "extendedConcept_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, DOT);
    result_ = result_ && identifier(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // FROM_KEY fromAnnotations? fromBody
  public static boolean from(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "from")) return false;
    if (!nextTokenIs(builder_, FROM_KEY)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, FROM_KEY);
    result_ = result_ && from_1(builder_, level_ + 1);
    result_ = result_ && fromBody(builder_, level_ + 1);
    exit_section_(builder_, marker_, FROM, result_);
    return result_;
  }

  // fromAnnotations?
  private static boolean from_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "from_1")) return false;
    fromAnnotations(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // OPEN_AN (OPTIONAL | MULTIPLE)+ CLOSE_AN
  public static boolean fromAnnotations(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "fromAnnotations")) return false;
    if (!nextTokenIs(builder_, OPEN_AN)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, OPEN_AN);
    result_ = result_ && fromAnnotations_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, CLOSE_AN);
    exit_section_(builder_, marker_, FROM_ANNOTATIONS, result_);
    return result_;
  }

  // (OPTIONAL | MULTIPLE)+
  private static boolean fromAnnotations_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "fromAnnotations_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = fromAnnotations_1_0(builder_, level_ + 1);
    int pos_ = current_position_(builder_);
    while (result_) {
      if (!fromAnnotations_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "fromAnnotations_1", pos_)) break;
      pos_ = current_position_(builder_);
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // OPTIONAL | MULTIPLE
  private static boolean fromAnnotations_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "fromAnnotations_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, OPTIONAL);
    if (!result_) result_ = consumeToken(builder_, MULTIPLE);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // NEW_LINE_INDENT (fromComponent NEWLINE+)+ DEDENT
  public static boolean fromBody(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "fromBody")) return false;
    if (!nextTokenIs(builder_, NEW_LINE_INDENT)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, NEW_LINE_INDENT);
    result_ = result_ && fromBody_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, DEDENT);
    exit_section_(builder_, marker_, FROM_BODY, result_);
    return result_;
  }

  // (fromComponent NEWLINE+)+
  private static boolean fromBody_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "fromBody_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = fromBody_1_0(builder_, level_ + 1);
    int pos_ = current_position_(builder_);
    while (result_) {
      if (!fromBody_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "fromBody_1", pos_)) break;
      pos_ = current_position_(builder_);
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // fromComponent NEWLINE+
  private static boolean fromBody_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "fromBody_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = fromComponent(builder_, level_ + 1);
    result_ = result_ && fromBody_1_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // NEWLINE+
  private static boolean fromBody_1_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "fromBody_1_0_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, NEWLINE);
    int pos_ = current_position_(builder_);
    while (result_) {
      if (!consumeToken(builder_, NEWLINE)) break;
      if (!empty_element_parsed_guard_(builder_, "fromBody_1_0_1", pos_)) break;
      pos_ = current_position_(builder_);
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // (doc? conceptSignature fromComponentAnnotations? conceptBody?) | ( NEW extendedConcept fromComponentAnnotations?)
  public static boolean fromComponent(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "fromComponent")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<from component>");
    result_ = fromComponent_0(builder_, level_ + 1);
    if (!result_) result_ = fromComponent_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, FROM_COMPONENT, result_, false, null);
    return result_;
  }

  // doc? conceptSignature fromComponentAnnotations? conceptBody?
  private static boolean fromComponent_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "fromComponent_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = fromComponent_0_0(builder_, level_ + 1);
    result_ = result_ && conceptSignature(builder_, level_ + 1);
    result_ = result_ && fromComponent_0_2(builder_, level_ + 1);
    result_ = result_ && fromComponent_0_3(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // doc?
  private static boolean fromComponent_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "fromComponent_0_0")) return false;
    doc(builder_, level_ + 1);
    return true;
  }

  // fromComponentAnnotations?
  private static boolean fromComponent_0_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "fromComponent_0_2")) return false;
    fromComponentAnnotations(builder_, level_ + 1);
    return true;
  }

  // conceptBody?
  private static boolean fromComponent_0_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "fromComponent_0_3")) return false;
    conceptBody(builder_, level_ + 1);
    return true;
  }

  // NEW extendedConcept fromComponentAnnotations?
  private static boolean fromComponent_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "fromComponent_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, NEW);
    result_ = result_ && extendedConcept(builder_, level_ + 1);
    result_ = result_ && fromComponent_1_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // fromComponentAnnotations?
  private static boolean fromComponent_1_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "fromComponent_1_2")) return false;
    fromComponentAnnotations(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // OPEN_AN (HAS_CODE | EXTENSIBLE | SINGLETON)+ CLOSE_AN
  public static boolean fromComponentAnnotations(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "fromComponentAnnotations")) return false;
    if (!nextTokenIs(builder_, OPEN_AN)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, OPEN_AN);
    result_ = result_ && fromComponentAnnotations_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, CLOSE_AN);
    exit_section_(builder_, marker_, FROM_COMPONENT_ANNOTATIONS, result_);
    return result_;
  }

  // (HAS_CODE | EXTENSIBLE | SINGLETON)+
  private static boolean fromComponentAnnotations_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "fromComponentAnnotations_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = fromComponentAnnotations_1_0(builder_, level_ + 1);
    int pos_ = current_position_(builder_);
    while (result_) {
      if (!fromComponentAnnotations_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "fromComponentAnnotations_1", pos_)) break;
      pos_ = current_position_(builder_);
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // HAS_CODE | EXTENSIBLE | SINGLETON
  private static boolean fromComponentAnnotations_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "fromComponentAnnotations_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, HAS_CODE);
    if (!result_) result_ = consumeToken(builder_, EXTENSIBLE);
    if (!result_) result_ = consumeToken(builder_, SINGLETON);
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
  // ASSIGN integerValue
  public static boolean integerAssign(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "integerAssign")) return false;
    if (!nextTokenIs(builder_, ASSIGN)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, ASSIGN);
    result_ = result_ && integerValue(builder_, level_ + 1);
    exit_section_(builder_, marker_, INTEGER_ASSIGN, result_);
    return result_;
  }

  /* ********************************************************** */
  // ASSIGN LEFT_SQUARE integerValue+ RIGHT_SQUARE
  public static boolean integerListAssign(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "integerListAssign")) return false;
    if (!nextTokenIs(builder_, ASSIGN)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, ASSIGN, LEFT_SQUARE);
    result_ = result_ && integerListAssign_2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RIGHT_SQUARE);
    exit_section_(builder_, marker_, INTEGER_LIST_ASSIGN, result_);
    return result_;
  }

  // integerValue+
  private static boolean integerListAssign_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "integerListAssign_2")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = integerValue(builder_, level_ + 1);
    int pos_ = current_position_(builder_);
    while (result_) {
      if (!integerValue(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "integerListAssign_2", pos_)) break;
      pos_ = current_position_(builder_);
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // NATURAL_VALUE
  //               | NEGATIVE_VALUE
  public static boolean integerValue(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "integerValue")) return false;
    if (!nextTokenIs(builder_, "<integer value>", NATURAL_VALUE, NEGATIVE_VALUE)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<integer value>");
    result_ = consumeToken(builder_, NATURAL_VALUE);
    if (!result_) result_ = consumeToken(builder_, NEGATIVE_VALUE);
    exit_section_(builder_, level_, marker_, INTEGER_VALUE, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // ABSTRACT
  //           | FINAL
  public static boolean modifier(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "modifier")) return false;
    if (!nextTokenIs(builder_, "<modifier>", ABSTRACT, FINAL)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<modifier>");
    result_ = consumeToken(builder_, ABSTRACT);
    if (!result_) result_ = consumeToken(builder_, FINAL);
    exit_section_(builder_, level_, marker_, MODIFIER, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // ASSIGN NATURAL_VALUE
  public static boolean naturalAssign(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "naturalAssign")) return false;
    if (!nextTokenIs(builder_, ASSIGN)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, ASSIGN, NATURAL_VALUE);
    exit_section_(builder_, marker_, NATURAL_ASSIGN, result_);
    return result_;
  }

  /* ********************************************************** */
  // ASSIGN LEFT_SQUARE NATURAL_VALUE+ RIGHT_SQUARE
  public static boolean naturalListAssign(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "naturalListAssign")) return false;
    if (!nextTokenIs(builder_, ASSIGN)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, ASSIGN, LEFT_SQUARE);
    result_ = result_ && naturalListAssign_2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RIGHT_SQUARE);
    exit_section_(builder_, marker_, NATURAL_LIST_ASSIGN, result_);
    return result_;
  }

  // NATURAL_VALUE+
  private static boolean naturalListAssign_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "naturalListAssign_2")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, NATURAL_VALUE);
    int pos_ = current_position_(builder_);
    while (result_) {
      if (!consumeToken(builder_, NATURAL_VALUE)) break;
      if (!empty_element_parsed_guard_(builder_, "naturalListAssign_2", pos_)) break;
      pos_ = current_position_(builder_);
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // VAR extendedConcept LIST? IDENTIFIER_KEY
  public static boolean referenceStatement(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "referenceStatement")) return false;
    if (!nextTokenIs(builder_, VAR)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, VAR);
    result_ = result_ && extendedConcept(builder_, level_ + 1);
    result_ = result_ && referenceStatement_2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, IDENTIFIER_KEY);
    exit_section_(builder_, marker_, REFERENCE_STATEMENT, result_);
    return result_;
  }

  // LIST?
  private static boolean referenceStatement_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "referenceStatement_2")) return false;
    consumeToken(builder_, LIST);
    return true;
  }

  /* ********************************************************** */
  // (concept | NEWLINE)*
  static boolean root(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "root")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!root_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "root", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  // concept | NEWLINE
  private static boolean root_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "root_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = concept(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, NEWLINE);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // ASSIGN STRING_VALUE
  public static boolean stringAssign(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "stringAssign")) return false;
    if (!nextTokenIs(builder_, ASSIGN)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, ASSIGN, STRING_VALUE);
    exit_section_(builder_, marker_, STRING_ASSIGN, result_);
    return result_;
  }

  /* ********************************************************** */
  // ASSIGN LEFT_SQUARE STRING_VALUE+ RIGHT_SQUARE
  public static boolean stringListAssign(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "stringListAssign")) return false;
    if (!nextTokenIs(builder_, ASSIGN)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, ASSIGN, LEFT_SQUARE);
    result_ = result_ && stringListAssign_2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RIGHT_SQUARE);
    exit_section_(builder_, marker_, STRING_LIST_ASSIGN, result_);
    return result_;
  }

  // STRING_VALUE+
  private static boolean stringListAssign_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "stringListAssign_2")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, STRING_VALUE);
    int pos_ = current_position_(builder_);
    while (result_) {
      if (!consumeToken(builder_, STRING_VALUE)) break;
      if (!empty_element_parsed_guard_(builder_, "stringListAssign_2", pos_)) break;
      pos_ = current_position_(builder_);
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // VAR WORD_KEY IDENTIFIER_KEY NEW_LINE_INDENT (IDENTIFIER_KEY NEWLINE+)+ DEDENT
  public static boolean word(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "word")) return false;
    if (!nextTokenIs(builder_, VAR)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, VAR, WORD_KEY, IDENTIFIER_KEY, NEW_LINE_INDENT);
    result_ = result_ && word_4(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, DEDENT);
    exit_section_(builder_, marker_, WORD, result_);
    return result_;
  }

  // (IDENTIFIER_KEY NEWLINE+)+
  private static boolean word_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "word_4")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = word_4_0(builder_, level_ + 1);
    int pos_ = current_position_(builder_);
    while (result_) {
      if (!word_4_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "word_4", pos_)) break;
      pos_ = current_position_(builder_);
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // IDENTIFIER_KEY NEWLINE+
  private static boolean word_4_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "word_4_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, IDENTIFIER_KEY);
    result_ = result_ && word_4_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // NEWLINE+
  private static boolean word_4_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "word_4_0_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, NEWLINE);
    int pos_ = current_position_(builder_);
    while (result_) {
      if (!consumeToken(builder_, NEWLINE)) break;
      if (!empty_element_parsed_guard_(builder_, "word_4_0_1", pos_)) break;
      pos_ = current_position_(builder_);
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

}
