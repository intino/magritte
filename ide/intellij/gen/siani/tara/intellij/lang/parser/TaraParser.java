// This is a generated file. Not intended for manual editing.
package siani.tara.intellij.lang.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static siani.tara.intellij.lang.psi.TaraTypes.*;
import static siani.tara.intellij.lang.parser.TaraParserUtil.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class TaraParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    if (t == ADDRESS) {
      r = address(b, 0);
    }
    else if (t == AN_IMPORT) {
      r = anImport(b, 0);
    }
    else if (t == ANNOTATION) {
      r = annotation(b, 0);
    }
    else if (t == ANNOTATIONS) {
      r = annotations(b, 0);
    }
    else if (t == ATTRIBUTE_TYPE) {
      r = attributeType(b, 0);
    }
    else if (t == BODY) {
      r = body(b, 0);
    }
    else if (t == BOOLEAN_VALUE) {
      r = booleanValue(b, 0);
    }
    else if (t == CONSTRAINT) {
      r = constraint(b, 0);
    }
    else if (t == CONTRACT) {
      r = contract(b, 0);
    }
    else if (t == COUNT) {
      r = count(b, 0);
    }
    else if (t == DOC) {
      r = doc(b, 0);
    }
    else if (t == DOUBLE_VALUE) {
      r = doubleValue(b, 0);
    }
    else if (t == DSL_DECLARATION) {
      r = dslDeclaration(b, 0);
    }
    else if (t == EMPTY_FIELD) {
      r = emptyField(b, 0);
    }
    else if (t == EXPLICIT_PARAMETER) {
      r = explicitParameter(b, 0);
    }
    else if (t == EXPRESSION) {
      r = expression(b, 0);
    }
    else if (t == FACET_APPLY) {
      r = facetApply(b, 0);
    }
    else if (t == FACET_TARGET) {
      r = facetTarget(b, 0);
    }
    else if (t == FLAG) {
      r = flag(b, 0);
    }
    else if (t == FLAGS) {
      r = flags(b, 0);
    }
    else if (t == HEADER_REFERENCE) {
      r = headerReference(b, 0);
    }
    else if (t == IDENTIFIER) {
      r = identifier(b, 0);
    }
    else if (t == IDENTIFIER_REFERENCE) {
      r = identifierReference(b, 0);
    }
    else if (t == IMPLICIT_PARAMETER) {
      r = implicitParameter(b, 0);
    }
    else if (t == IMPORTS) {
      r = imports(b, 0);
    }
    else if (t == INSTANCE_NAME) {
      r = instanceName(b, 0);
    }
    else if (t == INTEGER_VALUE) {
      r = integerValue(b, 0);
    }
    else if (t == MEASURE_VALUE) {
      r = measureValue(b, 0);
    }
    else if (t == META_IDENTIFIER) {
      r = metaIdentifier(b, 0);
    }
    else if (t == NATURAL_VALUE) {
      r = naturalValue(b, 0);
    }
    else if (t == NODE) {
      r = node(b, 0);
    }
    else if (t == NODE_REFERENCE) {
      r = nodeReference(b, 0);
    }
    else if (t == PARAMETERS) {
      r = parameters(b, 0);
    }
    else if (t == SIGNATURE) {
      r = signature(b, 0);
    }
    else if (t == STRING_VALUE) {
      r = stringValue(b, 0);
    }
    else if (t == TAGS) {
      r = tags(b, 0);
    }
    else if (t == VALUE) {
      r = value(b, 0);
    }
    else if (t == VAR_INIT) {
      r = varInit(b, 0);
    }
    else if (t == VARIABLE) {
      r = variable(b, 0);
    }
    else if (t == VARIABLE_TYPE) {
      r = variableType(b, 0);
    }
    else {
      r = parse_root_(t, b, 0);
    }
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return root(b, l + 1);
  }

  /* ********************************************************** */
  // ADDRESS_VALUE
  public static boolean address(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "address")) return false;
    if (!nextTokenIs(b, ADDRESS_VALUE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ADDRESS_VALUE);
    exit_section_(b, m, ADDRESS, r);
    return r;
  }

  /* ********************************************************** */
  // USE headerReference NEWLINE
  public static boolean anImport(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "anImport")) return false;
    if (!nextTokenIs(b, USE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, USE);
    r = r && headerReference(b, l + 1);
    r = r && consumeToken(b, NEWLINE);
    exit_section_(b, m, AN_IMPORT, r);
    return r;
  }

  /* ********************************************************** */
  // TERMINAL
  // 	| SINGLE | REQUIRED
  // 	| FACET | FEATURE | PROTOTYPE | ENCLOSED | MAIN
  public static boolean annotation(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotation")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<annotation>");
    r = consumeToken(b, TERMINAL);
    if (!r) r = consumeToken(b, SINGLE);
    if (!r) r = consumeToken(b, REQUIRED);
    if (!r) r = consumeToken(b, FACET);
    if (!r) r = consumeToken(b, FEATURE);
    if (!r) r = consumeToken(b, PROTOTYPE);
    if (!r) r = consumeToken(b, ENCLOSED);
    if (!r) r = consumeToken(b, MAIN);
    exit_section_(b, l, m, ANNOTATION, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INTO annotation+
  public static boolean annotations(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotations")) return false;
    if (!nextTokenIs(b, INTO)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeToken(b, INTO);
    p = r; // pin = 1
    r = r && annotations_1(b, l + 1);
    exit_section_(b, l, m, ANNOTATIONS, r, p, null);
    return r || p;
  }

  // annotation+
  private static boolean annotations_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotations_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = annotation(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!annotation(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "annotations_1", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // COLON contract
  public static boolean attributeType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attributeType")) return false;
    if (!nextTokenIs(b, COLON)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeToken(b, COLON);
    p = r; // pin = 1
    r = r && contract(b, l + 1);
    exit_section_(b, l, m, ATTRIBUTE_TYPE, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // (NEW_LINE_INDENT NEWLINE?| INLINE) (nodeConstituents NEWLINE+)+ DEDENT
  public static boolean body(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "body")) return false;
    if (!nextTokenIs(b, "<body>", INLINE, NEW_LINE_INDENT)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, "<body>");
    r = body_0(b, l + 1);
    p = r; // pin = 1
    r = r && report_error_(b, body_1(b, l + 1));
    r = p && consumeToken(b, DEDENT) && r;
    exit_section_(b, l, m, BODY, r, p, null);
    return r || p;
  }

  // NEW_LINE_INDENT NEWLINE?| INLINE
  private static boolean body_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "body_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = body_0_0(b, l + 1);
    if (!r) r = consumeToken(b, INLINE);
    exit_section_(b, m, null, r);
    return r;
  }

  // NEW_LINE_INDENT NEWLINE?
  private static boolean body_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "body_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, NEW_LINE_INDENT);
    r = r && body_0_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // NEWLINE?
  private static boolean body_0_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "body_0_0_1")) return false;
    consumeToken(b, NEWLINE);
    return true;
  }

  // (nodeConstituents NEWLINE+)+
  private static boolean body_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "body_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = body_1_0(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!body_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "body_1", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // nodeConstituents NEWLINE+
  private static boolean body_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "body_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = nodeConstituents(b, l + 1);
    r = r && body_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // NEWLINE+
  private static boolean body_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "body_1_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, NEWLINE);
    int c = current_position_(b);
    while (r) {
      if (!consumeToken(b, NEWLINE)) break;
      if (!empty_element_parsed_guard_(b, "body_1_0_1", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // BOOLEAN_VALUE_KEY
  public static boolean booleanValue(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "booleanValue")) return false;
    if (!nextTokenIs(b, BOOLEAN_VALUE_KEY)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, BOOLEAN_VALUE_KEY);
    exit_section_(b, m, BOOLEAN_VALUE, r);
    return r;
  }

  /* ********************************************************** */
  // WITH identifierReference (COMMA identifierReference)*
  public static boolean constraint(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "constraint")) return false;
    if (!nextTokenIs(b, WITH)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeToken(b, WITH);
    p = r; // pin = 1
    r = r && report_error_(b, identifierReference(b, l + 1));
    r = p && constraint_2(b, l + 1) && r;
    exit_section_(b, l, m, CONSTRAINT, r, p, null);
    return r || p;
  }

  // (COMMA identifierReference)*
  private static boolean constraint_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "constraint_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!constraint_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "constraint_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // COMMA identifierReference
  private static boolean constraint_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "constraint_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && identifierReference(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // (LEFT_SQUARE (MEASURE_VALUE_KEY | identifier)+ RIGHT_SQUARE) | (MEASURE_VALUE_KEY | identifier)
  public static boolean contract(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "contract")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<contract>");
    r = contract_0(b, l + 1);
    if (!r) r = contract_1(b, l + 1);
    exit_section_(b, l, m, CONTRACT, r, false, null);
    return r;
  }

  // LEFT_SQUARE (MEASURE_VALUE_KEY | identifier)+ RIGHT_SQUARE
  private static boolean contract_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "contract_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LEFT_SQUARE);
    r = r && contract_0_1(b, l + 1);
    r = r && consumeToken(b, RIGHT_SQUARE);
    exit_section_(b, m, null, r);
    return r;
  }

  // (MEASURE_VALUE_KEY | identifier)+
  private static boolean contract_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "contract_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = contract_0_1_0(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!contract_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "contract_0_1", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // MEASURE_VALUE_KEY | identifier
  private static boolean contract_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "contract_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, MEASURE_VALUE_KEY);
    if (!r) r = identifier(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // MEASURE_VALUE_KEY | identifier
  private static boolean contract_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "contract_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, MEASURE_VALUE_KEY);
    if (!r) r = identifier(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // LEFT_SQUARE NATURAL_VALUE_KEY RIGHT_SQUARE
  public static boolean count(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "count")) return false;
    if (!nextTokenIs(b, LEFT_SQUARE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, LEFT_SQUARE, NATURAL_VALUE_KEY, RIGHT_SQUARE);
    exit_section_(b, m, COUNT, r);
    return r;
  }

  /* ********************************************************** */
  // DOC_LINE+
  public static boolean doc(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "doc")) return false;
    if (!nextTokenIs(b, DOC_LINE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DOC_LINE);
    int c = current_position_(b);
    while (r) {
      if (!consumeToken(b, DOC_LINE)) break;
      if (!empty_element_parsed_guard_(b, "doc", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, DOC, r);
    return r;
  }

  /* ********************************************************** */
  // NATURAL_VALUE_KEY | NEGATIVE_VALUE_KEY | DOUBLE_VALUE_KEY
  public static boolean doubleValue(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "doubleValue")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<double value>");
    r = consumeToken(b, NATURAL_VALUE_KEY);
    if (!r) r = consumeToken(b, NEGATIVE_VALUE_KEY);
    if (!r) r = consumeToken(b, DOUBLE_VALUE_KEY);
    exit_section_(b, l, m, DOUBLE_VALUE, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // DSL (PROTEO | headerReference) NEWLINE+
  public static boolean dslDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dslDeclaration")) return false;
    if (!nextTokenIs(b, DSL)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DSL);
    r = r && dslDeclaration_1(b, l + 1);
    r = r && dslDeclaration_2(b, l + 1);
    exit_section_(b, m, DSL_DECLARATION, r);
    return r;
  }

  // PROTEO | headerReference
  private static boolean dslDeclaration_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dslDeclaration_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PROTEO);
    if (!r) r = headerReference(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // NEWLINE+
  private static boolean dslDeclaration_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dslDeclaration_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, NEWLINE);
    int c = current_position_(b);
    while (r) {
      if (!consumeToken(b, NEWLINE)) break;
      if (!empty_element_parsed_guard_(b, "dslDeclaration_2", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // EMPTY_REF
  public static boolean emptyField(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "emptyField")) return false;
    if (!nextTokenIs(b, EMPTY_REF)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, EMPTY_REF);
    exit_section_(b, m, EMPTY_FIELD, r);
    return r;
  }

  /* ********************************************************** */
  // identifier EQUALS value
  public static boolean explicitParameter(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "explicitParameter")) return false;
    if (!nextTokenIs(b, IDENTIFIER_KEY)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = identifier(b, l + 1);
    r = r && consumeToken(b, EQUALS);
    p = r; // pin = 2
    r = r && value(b, l + 1);
    exit_section_(b, l, m, EXPLICIT_PARAMETER, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // explicitParameter (COMMA explicitParameter)*
  static boolean explicitParameters(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "explicitParameters")) return false;
    if (!nextTokenIs(b, IDENTIFIER_KEY)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = explicitParameter(b, l + 1);
    r = r && explicitParameters_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (COMMA explicitParameter)*
  private static boolean explicitParameters_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "explicitParameters_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!explicitParameters_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "explicitParameters_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // COMMA explicitParameter
  private static boolean explicitParameters_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "explicitParameters_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && explicitParameter(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // NEWLINE? (EXPRESSION_BEGIN CHARACTER* EXPRESSION_END)
  public static boolean expression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expression")) return false;
    if (!nextTokenIs(b, "<expression>", EXPRESSION_BEGIN, NEWLINE)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<expression>");
    r = expression_0(b, l + 1);
    r = r && expression_1(b, l + 1);
    exit_section_(b, l, m, EXPRESSION, r, false, null);
    return r;
  }

  // NEWLINE?
  private static boolean expression_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expression_0")) return false;
    consumeToken(b, NEWLINE);
    return true;
  }

  // EXPRESSION_BEGIN CHARACTER* EXPRESSION_END
  private static boolean expression_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expression_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, EXPRESSION_BEGIN);
    r = r && expression_1_1(b, l + 1);
    r = r && consumeToken(b, EXPRESSION_END);
    exit_section_(b, m, null, r);
    return r;
  }

  // CHARACTER*
  private static boolean expression_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expression_1_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeToken(b, CHARACTER)) break;
      if (!empty_element_parsed_guard_(b, "expression_1_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // AS metaIdentifier parameters? (WITH metaIdentifier)? body?
  public static boolean facetApply(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "facetApply")) return false;
    if (!nextTokenIs(b, AS)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeToken(b, AS);
    p = r; // pin = 1
    r = r && report_error_(b, metaIdentifier(b, l + 1));
    r = p && report_error_(b, facetApply_2(b, l + 1)) && r;
    r = p && report_error_(b, facetApply_3(b, l + 1)) && r;
    r = p && facetApply_4(b, l + 1) && r;
    exit_section_(b, l, m, FACET_APPLY, r, p, null);
    return r || p;
  }

  // parameters?
  private static boolean facetApply_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "facetApply_2")) return false;
    parameters(b, l + 1);
    return true;
  }

  // (WITH metaIdentifier)?
  private static boolean facetApply_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "facetApply_3")) return false;
    facetApply_3_0(b, l + 1);
    return true;
  }

  // WITH metaIdentifier
  private static boolean facetApply_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "facetApply_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, WITH);
    r = r && metaIdentifier(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // body?
  private static boolean facetApply_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "facetApply_4")) return false;
    body(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // ON (identifierReference | ANY) constraint? body?
  public static boolean facetTarget(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "facetTarget")) return false;
    if (!nextTokenIs(b, ON)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeToken(b, ON);
    p = r; // pin = 1
    r = r && report_error_(b, facetTarget_1(b, l + 1));
    r = p && report_error_(b, facetTarget_2(b, l + 1)) && r;
    r = p && facetTarget_3(b, l + 1) && r;
    exit_section_(b, l, m, FACET_TARGET, r, p, null);
    return r || p;
  }

  // identifierReference | ANY
  private static boolean facetTarget_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "facetTarget_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = identifierReference(b, l + 1);
    if (!r) r = consumeToken(b, ANY);
    exit_section_(b, m, null, r);
    return r;
  }

  // constraint?
  private static boolean facetTarget_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "facetTarget_2")) return false;
    constraint(b, l + 1);
    return true;
  }

  // body?
  private static boolean facetTarget_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "facetTarget_3")) return false;
    body(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // ABSTRACT | TERMINAL | MAIN
  // 	| SINGLE | REQUIRED | PRIVATE
  // 	| FACET | FEATURE | PROTOTYPE | ENCLOSED | FINAL
  public static boolean flag(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "flag")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<flag>");
    r = consumeToken(b, ABSTRACT);
    if (!r) r = consumeToken(b, TERMINAL);
    if (!r) r = consumeToken(b, MAIN);
    if (!r) r = consumeToken(b, SINGLE);
    if (!r) r = consumeToken(b, REQUIRED);
    if (!r) r = consumeToken(b, PRIVATE);
    if (!r) r = consumeToken(b, FACET);
    if (!r) r = consumeToken(b, FEATURE);
    if (!r) r = consumeToken(b, PROTOTYPE);
    if (!r) r = consumeToken(b, ENCLOSED);
    if (!r) r = consumeToken(b, FINAL);
    exit_section_(b, l, m, FLAG, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // IS flag+
  public static boolean flags(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "flags")) return false;
    if (!nextTokenIs(b, IS)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeToken(b, IS);
    p = r; // pin = 1
    r = r && flags_1(b, l + 1);
    exit_section_(b, l, m, FLAGS, r, p, null);
    return r || p;
  }

  // flag+
  private static boolean flags_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "flags_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = flag(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!flag(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "flags_1", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // hierarchy* identifier
  public static boolean headerReference(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "headerReference")) return false;
    if (!nextTokenIs(b, IDENTIFIER_KEY)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = headerReference_0(b, l + 1);
    r = r && identifier(b, l + 1);
    exit_section_(b, m, HEADER_REFERENCE, r);
    return r;
  }

  // hierarchy*
  private static boolean headerReference_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "headerReference_0")) return false;
    int c = current_position_(b);
    while (true) {
      if (!hierarchy(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "headerReference_0", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // identifier DOT
  static boolean hierarchy(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "hierarchy")) return false;
    if (!nextTokenIs(b, IDENTIFIER_KEY)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = identifier(b, l + 1);
    r = r && consumeToken(b, DOT);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER_KEY
  public static boolean identifier(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "identifier")) return false;
    if (!nextTokenIs(b, IDENTIFIER_KEY)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER_KEY);
    exit_section_(b, m, IDENTIFIER, r);
    return r;
  }

  /* ********************************************************** */
  // hierarchy* identifier
  public static boolean identifierReference(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "identifierReference")) return false;
    if (!nextTokenIs(b, IDENTIFIER_KEY)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = identifierReference_0(b, l + 1);
    r = r && identifier(b, l + 1);
    exit_section_(b, m, IDENTIFIER_REFERENCE, r);
    return r;
  }

  // hierarchy*
  private static boolean identifierReference_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "identifierReference_0")) return false;
    int c = current_position_(b);
    while (true) {
      if (!hierarchy(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "identifierReference_0", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // value
  public static boolean implicitParameter(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "implicitParameter")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<implicit parameter>");
    r = value(b, l + 1);
    exit_section_(b, l, m, IMPLICIT_PARAMETER, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // implicitParameter (COMMA implicitParameter)*
  static boolean implicitParameters(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "implicitParameters")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = implicitParameter(b, l + 1);
    p = r; // pin = 1
    r = r && implicitParameters_1(b, l + 1);
    exit_section_(b, l, m, null, r, p, null);
    return r || p;
  }

  // (COMMA implicitParameter)*
  private static boolean implicitParameters_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "implicitParameters_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!implicitParameters_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "implicitParameters_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // COMMA implicitParameter
  private static boolean implicitParameters_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "implicitParameters_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && implicitParameter(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // (anImport NEWLINE*)+
  public static boolean imports(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "imports")) return false;
    if (!nextTokenIs(b, USE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = imports_0(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!imports_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "imports", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, IMPORTS, r);
    return r;
  }

  // anImport NEWLINE*
  private static boolean imports_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "imports_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = anImport(b, l + 1);
    r = r && imports_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // NEWLINE*
  private static boolean imports_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "imports_0_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeToken(b, NEWLINE)) break;
      if (!empty_element_parsed_guard_(b, "imports_0_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // ADDRESS_VALUE
  public static boolean instanceName(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "instanceName")) return false;
    if (!nextTokenIs(b, ADDRESS_VALUE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ADDRESS_VALUE);
    exit_section_(b, m, INSTANCE_NAME, r);
    return r;
  }

  /* ********************************************************** */
  // NATURAL_VALUE_KEY | NEGATIVE_VALUE_KEY
  public static boolean integerValue(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "integerValue")) return false;
    if (!nextTokenIs(b, "<integer value>", NATURAL_VALUE_KEY, NEGATIVE_VALUE_KEY)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<integer value>");
    r = consumeToken(b, NATURAL_VALUE_KEY);
    if (!r) r = consumeToken(b, NEGATIVE_VALUE_KEY);
    exit_section_(b, l, m, INTEGER_VALUE, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // identifier | MEASURE_VALUE_KEY
  public static boolean measureValue(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "measureValue")) return false;
    if (!nextTokenIs(b, "<measure value>", IDENTIFIER_KEY, MEASURE_VALUE_KEY)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<measure value>");
    r = identifier(b, l + 1);
    if (!r) r = consumeToken(b, MEASURE_VALUE_KEY);
    exit_section_(b, l, m, MEASURE_VALUE, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // METAIDENTIFIER_KEY | IDENTIFIER_KEY
  public static boolean metaIdentifier(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "metaIdentifier")) return false;
    if (!nextTokenIs(b, "<meta identifier>", IDENTIFIER_KEY, METAIDENTIFIER_KEY)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<meta identifier>");
    r = consumeToken(b, METAIDENTIFIER_KEY);
    if (!r) r = consumeToken(b, IDENTIFIER_KEY);
    exit_section_(b, l, m, META_IDENTIFIER, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // NATURAL_VALUE_KEY
  public static boolean naturalValue(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "naturalValue")) return false;
    if (!nextTokenIs(b, NATURAL_VALUE_KEY)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, NATURAL_VALUE_KEY);
    exit_section_(b, m, NATURAL_VALUE, r);
    return r;
  }

  /* ********************************************************** */
  // signature body?
  public static boolean node(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "node")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, "<node>");
    r = signature(b, l + 1);
    p = r; // pin = 1
    r = r && node_1(b, l + 1);
    exit_section_(b, l, m, NODE, r, p, null);
    return r || p;
  }

  // body?
  private static boolean node_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "node_1")) return false;
    body(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // varInit | variable | node | facetTarget | facetApply | nodeReference | doc
  static boolean nodeConstituents(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "nodeConstituents")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = varInit(b, l + 1);
    if (!r) r = variable(b, l + 1);
    if (!r) r = node(b, l + 1);
    if (!r) r = facetTarget(b, l + 1);
    if (!r) r = facetApply(b, l + 1);
    if (!r) r = nodeReference(b, l + 1);
    if (!r) r = doc(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // HAS identifierReference tags?
  public static boolean nodeReference(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "nodeReference")) return false;
    if (!nextTokenIs(b, HAS)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeToken(b, HAS);
    r = r && identifierReference(b, l + 1);
    p = r; // pin = 2
    r = r && nodeReference_2(b, l + 1);
    exit_section_(b, l, m, NODE_REFERENCE, r, p, null);
    return r || p;
  }

  // tags?
  private static boolean nodeReference_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "nodeReference_2")) return false;
    tags(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // LEFT_PARENTHESIS (explicitParameters | implicitParameters)? RIGHT_PARENTHESIS
  public static boolean parameters(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameters")) return false;
    if (!nextTokenIs(b, LEFT_PARENTHESIS)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeToken(b, LEFT_PARENTHESIS);
    p = r; // pin = 1
    r = r && report_error_(b, parameters_1(b, l + 1));
    r = p && consumeToken(b, RIGHT_PARENTHESIS) && r;
    exit_section_(b, l, m, PARAMETERS, r, p, null);
    return r || p;
  }

  // (explicitParameters | implicitParameters)?
  private static boolean parameters_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameters_1")) return false;
    parameters_1_0(b, l + 1);
    return true;
  }

  // explicitParameters | implicitParameters
  private static boolean parameters_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameters_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = explicitParameters(b, l + 1);
    if (!r) r = implicitParameters(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // EXTENDS identifierReference
  static boolean parent(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parent")) return false;
    if (!nextTokenIs(b, EXTENDS)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeToken(b, EXTENDS);
    p = r; // pin = 1
    r = r && identifierReference(b, l + 1);
    exit_section_(b, l, m, null, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // COMMENT? NEWLINE* dslDeclaration? imports? (node NEWLINE+)*
  static boolean root(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "root")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = root_0(b, l + 1);
    r = r && root_1(b, l + 1);
    r = r && root_2(b, l + 1);
    r = r && root_3(b, l + 1);
    r = r && root_4(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // COMMENT?
  private static boolean root_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "root_0")) return false;
    consumeToken(b, COMMENT);
    return true;
  }

  // NEWLINE*
  private static boolean root_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "root_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeToken(b, NEWLINE)) break;
      if (!empty_element_parsed_guard_(b, "root_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // dslDeclaration?
  private static boolean root_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "root_2")) return false;
    dslDeclaration(b, l + 1);
    return true;
  }

  // imports?
  private static boolean root_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "root_3")) return false;
    imports(b, l + 1);
    return true;
  }

  // (node NEWLINE+)*
  private static boolean root_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "root_4")) return false;
    int c = current_position_(b);
    while (true) {
      if (!root_4_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "root_4", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // node NEWLINE+
  private static boolean root_4_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "root_4_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = node(b, l + 1);
    r = r && root_4_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // NEWLINE+
  private static boolean root_4_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "root_4_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, NEWLINE);
    int c = current_position_(b);
    while (r) {
      if (!consumeToken(b, NEWLINE)) break;
      if (!empty_element_parsed_guard_(b, "root_4_0_1", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // (subNode | metaIdentifier parameters? identifier? parent?) tags? address?
  public static boolean signature(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "signature")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, "<signature>");
    r = signature_0(b, l + 1);
    p = r; // pin = 1
    r = r && report_error_(b, signature_1(b, l + 1));
    r = p && signature_2(b, l + 1) && r;
    exit_section_(b, l, m, SIGNATURE, r, p, null);
    return r || p;
  }

  // subNode | metaIdentifier parameters? identifier? parent?
  private static boolean signature_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "signature_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = subNode(b, l + 1);
    if (!r) r = signature_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // metaIdentifier parameters? identifier? parent?
  private static boolean signature_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "signature_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = metaIdentifier(b, l + 1);
    r = r && signature_0_1_1(b, l + 1);
    r = r && signature_0_1_2(b, l + 1);
    r = r && signature_0_1_3(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // parameters?
  private static boolean signature_0_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "signature_0_1_1")) return false;
    parameters(b, l + 1);
    return true;
  }

  // identifier?
  private static boolean signature_0_1_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "signature_0_1_2")) return false;
    identifier(b, l + 1);
    return true;
  }

  // parent?
  private static boolean signature_0_1_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "signature_0_1_3")) return false;
    parent(b, l + 1);
    return true;
  }

  // tags?
  private static boolean signature_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "signature_1")) return false;
    tags(b, l + 1);
    return true;
  }

  // address?
  private static boolean signature_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "signature_2")) return false;
    address(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // NEWLINE? (QUOTE_BEGIN CHARACTER* QUOTE_END)
  public static boolean stringValue(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "stringValue")) return false;
    if (!nextTokenIs(b, "<string value>", NEWLINE, QUOTE_BEGIN)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<string value>");
    r = stringValue_0(b, l + 1);
    r = r && stringValue_1(b, l + 1);
    exit_section_(b, l, m, STRING_VALUE, r, false, null);
    return r;
  }

  // NEWLINE?
  private static boolean stringValue_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "stringValue_0")) return false;
    consumeToken(b, NEWLINE);
    return true;
  }

  // QUOTE_BEGIN CHARACTER* QUOTE_END
  private static boolean stringValue_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "stringValue_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, QUOTE_BEGIN);
    r = r && stringValue_1_1(b, l + 1);
    r = r && consumeToken(b, QUOTE_END);
    exit_section_(b, m, null, r);
    return r;
  }

  // CHARACTER*
  private static boolean stringValue_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "stringValue_1_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeToken(b, CHARACTER)) break;
      if (!empty_element_parsed_guard_(b, "stringValue_1_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // SUB parameters? identifier
  static boolean subNode(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "subNode")) return false;
    if (!nextTokenIs(b, SUB)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeToken(b, SUB);
    p = r; // pin = 1
    r = r && report_error_(b, subNode_1(b, l + 1));
    r = p && identifier(b, l + 1) && r;
    exit_section_(b, l, m, null, r, p, null);
    return r || p;
  }

  // parameters?
  private static boolean subNode_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "subNode_1")) return false;
    parameters(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // flags? annotations?
  public static boolean tags(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tags")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, "<tags>");
    r = tags_0(b, l + 1);
    p = r; // pin = 1
    r = r && tags_1(b, l + 1);
    exit_section_(b, l, m, TAGS, r, p, null);
    return r || p;
  }

  // flags?
  private static boolean tags_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tags_0")) return false;
    flags(b, l + 1);
    return true;
  }

  // annotations?
  private static boolean tags_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tags_1")) return false;
    annotations(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // stringValue+
  //         | booleanValue+
  //         | naturalValue+ measureValue?
  //         | integerValue+ measureValue?
  //         | doubleValue+  measureValue?
  //         | expression+
  //         | emptyField
  //         | instanceName+
  //         | identifierReference+
  public static boolean value(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "value")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<value>");
    r = value_0(b, l + 1);
    if (!r) r = value_1(b, l + 1);
    if (!r) r = value_2(b, l + 1);
    if (!r) r = value_3(b, l + 1);
    if (!r) r = value_4(b, l + 1);
    if (!r) r = value_5(b, l + 1);
    if (!r) r = emptyField(b, l + 1);
    if (!r) r = value_7(b, l + 1);
    if (!r) r = value_8(b, l + 1);
    exit_section_(b, l, m, VALUE, r, false, null);
    return r;
  }

  // stringValue+
  private static boolean value_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "value_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = stringValue(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!stringValue(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "value_0", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // booleanValue+
  private static boolean value_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "value_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = booleanValue(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!booleanValue(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "value_1", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // naturalValue+ measureValue?
  private static boolean value_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "value_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = value_2_0(b, l + 1);
    r = r && value_2_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // naturalValue+
  private static boolean value_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "value_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = naturalValue(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!naturalValue(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "value_2_0", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // measureValue?
  private static boolean value_2_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "value_2_1")) return false;
    measureValue(b, l + 1);
    return true;
  }

  // integerValue+ measureValue?
  private static boolean value_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "value_3")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = value_3_0(b, l + 1);
    r = r && value_3_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // integerValue+
  private static boolean value_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "value_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = integerValue(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!integerValue(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "value_3_0", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // measureValue?
  private static boolean value_3_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "value_3_1")) return false;
    measureValue(b, l + 1);
    return true;
  }

  // doubleValue+  measureValue?
  private static boolean value_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "value_4")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = value_4_0(b, l + 1);
    r = r && value_4_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // doubleValue+
  private static boolean value_4_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "value_4_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = doubleValue(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!doubleValue(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "value_4_0", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // measureValue?
  private static boolean value_4_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "value_4_1")) return false;
    measureValue(b, l + 1);
    return true;
  }

  // expression+
  private static boolean value_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "value_5")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = expression(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!expression(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "value_5", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // instanceName+
  private static boolean value_7(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "value_7")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = instanceName(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!instanceName(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "value_7", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // identifierReference+
  private static boolean value_8(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "value_8")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = identifierReference(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!identifierReference(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "value_8", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // identifier EQUALS value
  public static boolean varInit(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "varInit")) return false;
    if (!nextTokenIs(b, IDENTIFIER_KEY)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = identifier(b, l + 1);
    r = r && consumeToken(b, EQUALS);
    p = r; // pin = 2
    r = r && value(b, l + 1);
    exit_section_(b, l, m, VAR_INIT, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // VAR variableType attributeType? (LIST | count)? identifier (EQUALS value measureValue?)? flags? (NEW_LINE_INDENT (doc NEWLINE+)+ DEDENT)?
  public static boolean variable(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variable")) return false;
    if (!nextTokenIs(b, VAR)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeToken(b, VAR);
    p = r; // pin = 1
    r = r && report_error_(b, variableType(b, l + 1));
    r = p && report_error_(b, variable_2(b, l + 1)) && r;
    r = p && report_error_(b, variable_3(b, l + 1)) && r;
    r = p && report_error_(b, identifier(b, l + 1)) && r;
    r = p && report_error_(b, variable_5(b, l + 1)) && r;
    r = p && report_error_(b, variable_6(b, l + 1)) && r;
    r = p && variable_7(b, l + 1) && r;
    exit_section_(b, l, m, VARIABLE, r, p, null);
    return r || p;
  }

  // attributeType?
  private static boolean variable_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variable_2")) return false;
    attributeType(b, l + 1);
    return true;
  }

  // (LIST | count)?
  private static boolean variable_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variable_3")) return false;
    variable_3_0(b, l + 1);
    return true;
  }

  // LIST | count
  private static boolean variable_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variable_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LIST);
    if (!r) r = count(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (EQUALS value measureValue?)?
  private static boolean variable_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variable_5")) return false;
    variable_5_0(b, l + 1);
    return true;
  }

  // EQUALS value measureValue?
  private static boolean variable_5_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variable_5_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, EQUALS);
    r = r && value(b, l + 1);
    r = r && variable_5_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // measureValue?
  private static boolean variable_5_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variable_5_0_2")) return false;
    measureValue(b, l + 1);
    return true;
  }

  // flags?
  private static boolean variable_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variable_6")) return false;
    flags(b, l + 1);
    return true;
  }

  // (NEW_LINE_INDENT (doc NEWLINE+)+ DEDENT)?
  private static boolean variable_7(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variable_7")) return false;
    variable_7_0(b, l + 1);
    return true;
  }

  // NEW_LINE_INDENT (doc NEWLINE+)+ DEDENT
  private static boolean variable_7_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variable_7_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, NEW_LINE_INDENT);
    r = r && variable_7_0_1(b, l + 1);
    r = r && consumeToken(b, DEDENT);
    exit_section_(b, m, null, r);
    return r;
  }

  // (doc NEWLINE+)+
  private static boolean variable_7_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variable_7_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = variable_7_0_1_0(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!variable_7_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "variable_7_0_1", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // doc NEWLINE+
  private static boolean variable_7_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variable_7_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = doc(b, l + 1);
    r = r && variable_7_0_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // NEWLINE+
  private static boolean variable_7_0_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variable_7_0_1_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, NEWLINE);
    int c = current_position_(b);
    while (r) {
      if (!consumeToken(b, NEWLINE)) break;
      if (!empty_element_parsed_guard_(b, "variable_7_0_1_0_1", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // NATURAL_TYPE
  //                 | NATIVE_TYPE
  //                 | INT_TYPE
  //                 | BOOLEAN_TYPE
  //                 | STRING_TYPE
  //                 | DATE_TYPE
  //                 | RATIO_TYPE
  //                 | DOUBLE_TYPE
  //                 | MEASURE_TYPE_KEY
  //                 | WORD_KEY
  //                 | RESOURCE_KEY
  //                 | identifierReference
  public static boolean variableType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variableType")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<variable type>");
    r = consumeToken(b, NATURAL_TYPE);
    if (!r) r = consumeToken(b, NATIVE_TYPE);
    if (!r) r = consumeToken(b, INT_TYPE);
    if (!r) r = consumeToken(b, BOOLEAN_TYPE);
    if (!r) r = consumeToken(b, STRING_TYPE);
    if (!r) r = consumeToken(b, DATE_TYPE);
    if (!r) r = consumeToken(b, RATIO_TYPE);
    if (!r) r = consumeToken(b, DOUBLE_TYPE);
    if (!r) r = consumeToken(b, MEASURE_TYPE_KEY);
    if (!r) r = consumeToken(b, WORD_KEY);
    if (!r) r = consumeToken(b, RESOURCE_KEY);
    if (!r) r = identifierReference(b, l + 1);
    exit_section_(b, l, m, VARIABLE_TYPE, r, false, null);
    return r;
  }

}
