// This is a generated file. Not intended for manual editing.
package monet.::projectName::.intellij.metamodel.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import com.intellij.openapi.diagnostic.Logger;
import static monet.::projectName::.intellij.metamodel.psi.::projectProperName::Types.*;
import static monet.::projectName::.intellij.metamodel.parser.::projectProperName::ParserUtil.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class ::projectProperName::Parser implements PsiParser {

  public static final Logger LOG_ = Logger.getInstance("monet.::projectName::.intellij.metamodel.parser.::projectProperName::Parser");

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
      result_ = definition(builder_, 0);
    }
    else if (root_ == CONCEPT_INJECTION) {
      result_ = definitionInjection(builder_, 0);
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
    else if (root_ == EXTENDED_CONCEPT) {
      result_ = extendedDefinition(builder_, 0);
    }
    else if (root_ == IDENTIFIER) {
      result_ = identifier(builder_, 0);
    }
    else if (root_ == INTEGER_LIST) {
      result_ = integerList(builder_, 0);
    }
    else if (root_ == INTEGER_VALUE) {
      result_ = integerValue(builder_, 0);
    }
    else if (root_ == MODIFIER) {
      result_ = modifier(builder_, 0);
    }
    else if (root_ == MORPH) {
      result_ = morph(builder_, 0);
    }
    else if (root_ == NATURAL_LIST) {
      result_ = naturalList(builder_, 0);
    }
    else if (root_ == NATURAL_VALUE) {
      result_ = naturalValue(builder_, 0);
    }
    else if (root_ == POLYMORPHIC) {
      result_ = polymorphic(builder_, 0);
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
    else if (root_ == VARIABLE_NAMES) {
      result_ = variableNames(builder_, 0);
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
  // OPEN_AN (MULTIPLE | OPTIONAL | HAS_CODE | EXTENSIBLE| SINGLETON | ROOT)+ CLOSE_AN
  public static boolean annotations(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "annotations")) return false;
    if (!nextTokenIs(builder_, OPEN_AN)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, OPEN_AN);
    result_ = result_ && annotations_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, CLOSE_AN);
    exit_section_(builder_, marker_, ANNOTATIONS, result_);
    return result_;
  }

  // (MULTIPLE | OPTIONAL | HAS_CODE | EXTENSIBLE| SINGLETON | ROOT)+
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

  // MULTIPLE | OPTIONAL | HAS_CODE | EXTENSIBLE| SINGLETON | ROOT
  private static boolean annotations_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "annotations_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, MULTIPLE);
    if (!result_) result_ = consumeToken(builder_, OPTIONAL);
    if (!result_) result_ = consumeToken(builder_, HAS_CODE);
    if (!result_) result_ = consumeToken(builder_, EXTENSIBLE);
    if (!result_) result_ = consumeToken(builder_, SINGLETON);
    if (!result_) result_ = consumeToken(builder_, ROOT);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // VAR     UID_TYPE IDENTIFIER_KEY (ASSIGN stringValue)?
  //            | VAR     INT_TYPE (variableNames | IDENTIFIER_KEY ASSIGN integerValue | LIST IDENTIFIER_KEY (ASSIGN integerList)?)
  //            | VAR  DOUBLE_TYPE (variableNames | IDENTIFIER_KEY ASSIGN doubleValue | LIST IDENTIFIER_KEY (ASSIGN doubleList)?)
  //            | VAR NATURAL_TYPE (variableNames | IDENTIFIER_KEY ASSIGN naturalValue | LIST IDENTIFIER_KEY (ASSIGN naturalList)?)
  //            | VAR BOOLEAN_TYPE (variableNames | IDENTIFIER_KEY ASSIGN booleanValue | LIST IDENTIFIER_KEY (ASSIGN booleanList)?)
  //            | VAR  STRING_TYPE (variableNames | IDENTIFIER_KEY ASSIGN stringValue | LIST IDENTIFIER_KEY (ASSIGN stringList)?)
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

  // VAR     UID_TYPE IDENTIFIER_KEY (ASSIGN stringValue)?
  private static boolean attribute_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, VAR, UID_TYPE, IDENTIFIER_KEY);
    result_ = result_ && attribute_0_3(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (ASSIGN stringValue)?
  private static boolean attribute_0_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_0_3")) return false;
    attribute_0_3_0(builder_, level_ + 1);
    return true;
  }

  // ASSIGN stringValue
  private static boolean attribute_0_3_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_0_3_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, ASSIGN);
    result_ = result_ && stringValue(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // VAR     INT_TYPE (variableNames | IDENTIFIER_KEY ASSIGN integerValue | LIST IDENTIFIER_KEY (ASSIGN integerList)?)
  private static boolean attribute_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, VAR, INT_TYPE);
    result_ = result_ && attribute_1_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // variableNames | IDENTIFIER_KEY ASSIGN integerValue | LIST IDENTIFIER_KEY (ASSIGN integerList)?
  private static boolean attribute_1_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_1_2")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = variableNames(builder_, level_ + 1);
    if (!result_) result_ = attribute_1_2_1(builder_, level_ + 1);
    if (!result_) result_ = attribute_1_2_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // IDENTIFIER_KEY ASSIGN integerValue
  private static boolean attribute_1_2_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_1_2_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, IDENTIFIER_KEY, ASSIGN);
    result_ = result_ && integerValue(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // LIST IDENTIFIER_KEY (ASSIGN integerList)?
  private static boolean attribute_1_2_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_1_2_2")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, LIST, IDENTIFIER_KEY);
    result_ = result_ && attribute_1_2_2_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (ASSIGN integerList)?
  private static boolean attribute_1_2_2_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_1_2_2_2")) return false;
    attribute_1_2_2_2_0(builder_, level_ + 1);
    return true;
  }

  // ASSIGN integerList
  private static boolean attribute_1_2_2_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_1_2_2_2_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, ASSIGN);
    result_ = result_ && integerList(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // VAR  DOUBLE_TYPE (variableNames | IDENTIFIER_KEY ASSIGN doubleValue | LIST IDENTIFIER_KEY (ASSIGN doubleList)?)
  private static boolean attribute_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_2")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, VAR, DOUBLE_TYPE);
    result_ = result_ && attribute_2_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // variableNames | IDENTIFIER_KEY ASSIGN doubleValue | LIST IDENTIFIER_KEY (ASSIGN doubleList)?
  private static boolean attribute_2_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_2_2")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = variableNames(builder_, level_ + 1);
    if (!result_) result_ = attribute_2_2_1(builder_, level_ + 1);
    if (!result_) result_ = attribute_2_2_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // IDENTIFIER_KEY ASSIGN doubleValue
  private static boolean attribute_2_2_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_2_2_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, IDENTIFIER_KEY, ASSIGN);
    result_ = result_ && doubleValue(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // LIST IDENTIFIER_KEY (ASSIGN doubleList)?
  private static boolean attribute_2_2_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_2_2_2")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, LIST, IDENTIFIER_KEY);
    result_ = result_ && attribute_2_2_2_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (ASSIGN doubleList)?
  private static boolean attribute_2_2_2_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_2_2_2_2")) return false;
    attribute_2_2_2_2_0(builder_, level_ + 1);
    return true;
  }

  // ASSIGN doubleList
  private static boolean attribute_2_2_2_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_2_2_2_2_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, ASSIGN);
    result_ = result_ && doubleList(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // VAR NATURAL_TYPE (variableNames | IDENTIFIER_KEY ASSIGN naturalValue | LIST IDENTIFIER_KEY (ASSIGN naturalList)?)
  private static boolean attribute_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_3")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, VAR, NATURAL_TYPE);
    result_ = result_ && attribute_3_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // variableNames | IDENTIFIER_KEY ASSIGN naturalValue | LIST IDENTIFIER_KEY (ASSIGN naturalList)?
  private static boolean attribute_3_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_3_2")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = variableNames(builder_, level_ + 1);
    if (!result_) result_ = attribute_3_2_1(builder_, level_ + 1);
    if (!result_) result_ = attribute_3_2_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // IDENTIFIER_KEY ASSIGN naturalValue
  private static boolean attribute_3_2_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_3_2_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, IDENTIFIER_KEY, ASSIGN);
    result_ = result_ && naturalValue(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // LIST IDENTIFIER_KEY (ASSIGN naturalList)?
  private static boolean attribute_3_2_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_3_2_2")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, LIST, IDENTIFIER_KEY);
    result_ = result_ && attribute_3_2_2_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (ASSIGN naturalList)?
  private static boolean attribute_3_2_2_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_3_2_2_2")) return false;
    attribute_3_2_2_2_0(builder_, level_ + 1);
    return true;
  }

  // ASSIGN naturalList
  private static boolean attribute_3_2_2_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_3_2_2_2_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, ASSIGN);
    result_ = result_ && naturalList(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // VAR BOOLEAN_TYPE (variableNames | IDENTIFIER_KEY ASSIGN booleanValue | LIST IDENTIFIER_KEY (ASSIGN booleanList)?)
  private static boolean attribute_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_4")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, VAR, BOOLEAN_TYPE);
    result_ = result_ && attribute_4_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // variableNames | IDENTIFIER_KEY ASSIGN booleanValue | LIST IDENTIFIER_KEY (ASSIGN booleanList)?
  private static boolean attribute_4_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_4_2")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = variableNames(builder_, level_ + 1);
    if (!result_) result_ = attribute_4_2_1(builder_, level_ + 1);
    if (!result_) result_ = attribute_4_2_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // IDENTIFIER_KEY ASSIGN booleanValue
  private static boolean attribute_4_2_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_4_2_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, IDENTIFIER_KEY, ASSIGN);
    result_ = result_ && booleanValue(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // LIST IDENTIFIER_KEY (ASSIGN booleanList)?
  private static boolean attribute_4_2_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_4_2_2")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, LIST, IDENTIFIER_KEY);
    result_ = result_ && attribute_4_2_2_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (ASSIGN booleanList)?
  private static boolean attribute_4_2_2_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_4_2_2_2")) return false;
    attribute_4_2_2_2_0(builder_, level_ + 1);
    return true;
  }

  // ASSIGN booleanList
  private static boolean attribute_4_2_2_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_4_2_2_2_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, ASSIGN);
    result_ = result_ && booleanList(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // VAR  STRING_TYPE (variableNames | IDENTIFIER_KEY ASSIGN stringValue | LIST IDENTIFIER_KEY (ASSIGN stringList)?)
  private static boolean attribute_5(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_5")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, VAR, STRING_TYPE);
    result_ = result_ && attribute_5_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // variableNames | IDENTIFIER_KEY ASSIGN stringValue | LIST IDENTIFIER_KEY (ASSIGN stringList)?
  private static boolean attribute_5_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_5_2")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = variableNames(builder_, level_ + 1);
    if (!result_) result_ = attribute_5_2_1(builder_, level_ + 1);
    if (!result_) result_ = attribute_5_2_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // IDENTIFIER_KEY ASSIGN stringValue
  private static boolean attribute_5_2_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_5_2_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, IDENTIFIER_KEY, ASSIGN);
    result_ = result_ && stringValue(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // LIST IDENTIFIER_KEY (ASSIGN stringList)?
  private static boolean attribute_5_2_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_5_2_2")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, LIST, IDENTIFIER_KEY);
    result_ = result_ && attribute_5_2_2_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (ASSIGN stringList)?
  private static boolean attribute_5_2_2_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_5_2_2_2")) return false;
    attribute_5_2_2_2_0(builder_, level_ + 1);
    return true;
  }

  // ASSIGN stringList
  private static boolean attribute_5_2_2_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_5_2_2_2_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, ASSIGN);
    result_ = result_ && stringList(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // NEW_LINE_INDENT (definitionConstituents NEWLINE+)+ DEDENT
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

  // (definitionConstituents NEWLINE+)+
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

  // definitionConstituents NEWLINE+
  private static boolean body_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "body_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = definitionConstituents(builder_, level_ + 1);
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
  public static boolean definition(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "definition")) return false;
    if (!nextTokenIs(builder_, "<definition>", CONCEPT_KEY, DOC_LINE)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<definition>");
    result_ = definition_0(builder_, level_ + 1);
    result_ = result_ && signature(builder_, level_ + 1);
    result_ = result_ && definition_2(builder_, level_ + 1);
    result_ = result_ && definition_3(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, CONCEPT, result_, false, null);
    return result_;
  }

  // doc?
  private static boolean definition_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "definition_0")) return false;
    doc(builder_, level_ + 1);
    return true;
  }

  // annotations?
  private static boolean definition_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "definition_2")) return false;
    annotations(builder_, level_ + 1);
    return true;
  }

  // body?
  private static boolean definition_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "definition_3")) return false;
    body(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // attribute
  // 		                      | referenceStatement
  // 		                      | word
  // 		                      | definition
  // 		                      | definitionInjection
  static boolean definitionConstituents(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "definitionConstituents")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = attribute(builder_, level_ + 1);
    if (!result_) result_ = referenceStatement(builder_, level_ + 1);
    if (!result_) result_ = word(builder_, level_ + 1);
    if (!result_) result_ = definition(builder_, level_ + 1);
    if (!result_) result_ = definitionInjection(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // NEW extendedDefinition annotations?
  public static boolean definitionInjection(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "definitionInjection")) return false;
    if (!nextTokenIs(builder_, NEW)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, NEW);
    result_ = result_ && extendedDefinition(builder_, level_ + 1);
    result_ = result_ && definitionInjection_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, CONCEPT_INJECTION, result_);
    return result_;
  }

  // annotations?
  private static boolean definitionInjection_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "definitionInjection_2")) return false;
    annotations(builder_, level_ + 1);
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
  // identifier (DOT identifier)*
  public static boolean extendedDefinition(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "extendedDefinition")) return false;
    if (!nextTokenIs(builder_, IDENTIFIER_KEY)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = identifier(builder_, level_ + 1);
    result_ = result_ && extendedDefinition_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, EXTENDED_CONCEPT, result_);
    return result_;
  }

  // (DOT identifier)*
  private static boolean extendedDefinition_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "extendedDefinition_1")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!extendedDefinition_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "extendedDefinition_1", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  // DOT identifier
  private static boolean extendedDefinition_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "extendedDefinition_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, DOT);
    result_ = result_ && identifier(builder_, level_ + 1);
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
  // MORPH_KEY
  public static boolean morph(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "morph")) return false;
    if (!nextTokenIs(builder_, MORPH_KEY)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, MORPH_KEY);
    exit_section_(builder_, marker_, MORPH, result_);
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
  // POLYMORPHIC_KEY
  public static boolean polymorphic(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "polymorphic")) return false;
    if (!nextTokenIs(builder_, POLYMORPHIC_KEY)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, POLYMORPHIC_KEY);
    exit_section_(builder_, marker_, POLYMORPHIC, result_);
    return result_;
  }

  /* ********************************************************** */
  // VAR extendedDefinition LIST? variableNames
  public static boolean referenceStatement(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "referenceStatement")) return false;
    if (!nextTokenIs(builder_, VAR)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, VAR);
    result_ = result_ && extendedDefinition(builder_, level_ + 1);
    result_ = result_ && referenceStatement_2(builder_, level_ + 1);
    result_ = result_ && variableNames(builder_, level_ + 1);
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
  // (definition | NEWLINE)*
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

  // definition | NEWLINE
  private static boolean root_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "root_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = definition(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, NEWLINE);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // CONCEPT_KEY extendedDefinition?  (polymorphic | modifier? morph?)  AS identifier
  public static boolean signature(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "signature")) return false;
    if (!nextTokenIs(builder_, CONCEPT_KEY)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, CONCEPT_KEY);
    result_ = result_ && signature_1(builder_, level_ + 1);
    result_ = result_ && signature_2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, AS);
    result_ = result_ && identifier(builder_, level_ + 1);
    exit_section_(builder_, marker_, SIGNATURE, result_);
    return result_;
  }

  // extendedDefinition?
  private static boolean signature_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "signature_1")) return false;
    extendedDefinition(builder_, level_ + 1);
    return true;
  }

  // polymorphic | modifier? morph?
  private static boolean signature_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "signature_2")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = polymorphic(builder_, level_ + 1);
    if (!result_) result_ = signature_2_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // modifier? morph?
  private static boolean signature_2_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "signature_2_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = signature_2_1_0(builder_, level_ + 1);
    result_ = result_ && signature_2_1_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // modifier?
  private static boolean signature_2_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "signature_2_1_0")) return false;
    modifier(builder_, level_ + 1);
    return true;
  }

  // morph?
  private static boolean signature_2_1_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "signature_2_1_1")) return false;
    morph(builder_, level_ + 1);
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
  // IDENTIFIER_KEY (COMMA IDENTIFIER_KEY)*
  public static boolean variableNames(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "variableNames")) return false;
    if (!nextTokenIs(builder_, IDENTIFIER_KEY)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, IDENTIFIER_KEY);
    result_ = result_ && variableNames_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, VARIABLE_NAMES, result_);
    return result_;
  }

  // (COMMA IDENTIFIER_KEY)*
  private static boolean variableNames_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "variableNames_1")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!variableNames_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "variableNames_1", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  // COMMA IDENTIFIER_KEY
  private static boolean variableNames_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "variableNames_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, COMMA, IDENTIFIER_KEY);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // VAR WORD_KEY IDENTIFIER_KEY NEW_LINE_INDENT (IDENTIFIER_KEY NEWLINE)+ DEDENT
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

  // (IDENTIFIER_KEY NEWLINE)+
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

  // IDENTIFIER_KEY NEWLINE
  private static boolean word_4_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "word_4_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, IDENTIFIER_KEY, NEWLINE);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

}
