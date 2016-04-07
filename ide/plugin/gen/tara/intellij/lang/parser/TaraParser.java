// This is a generated file. Not intended for manual editing.
package tara.intellij.lang.parser;

import com.intellij.lang.ASTNode;
import com.intellij.lang.LightPsiParser;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import com.intellij.lang.PsiParser;
import com.intellij.psi.tree.IElementType;

import static tara.intellij.lang.parser.TaraParserUtil.*;
import static tara.intellij.lang.psi.TaraTypes.*;

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
    if (t == AN_IMPORT) {
      r = anImport(b, 0);
    }
    else if (t == ANCHOR) {
      r = anchor(b, 0);
    }
    else if (t == ANNOTATION) {
      r = annotation(b, 0);
    }
    else if (t == ANNOTATIONS) {
      r = annotations(b, 0);
    }
    else if (t == BODY) {
      r = body(b, 0);
    }
    else if (t == BODY_VALUE) {
      r = bodyValue(b, 0);
    }
    else if (t == BOOLEAN_VALUE) {
      r = booleanValue(b, 0);
    } else if (t == CLASS_REFERENCE) {
      r = classReference(b, 0);
    } else if (t == CLASS_TYPE_VALUE) {
      r = classTypeValue(b, 0);
    }
    else if (t == CONSTRAINT) {
      r = constraint(b, 0);
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
    else if (t == IMPORTS) {
      r = imports(b, 0);
    }
    else if (t == INTEGER_VALUE) {
      r = integerValue(b, 0);
    }
    else if (t == LIST_RANGE) {
      r = listRange(b, 0);
    }
    else if (t == META_IDENTIFIER) {
      r = metaIdentifier(b, 0);
    }
    else if (t == METRIC) {
      r = metric(b, 0);
    }
    else if (t == NODE) {
      r = node(b, 0);
    }
    else if (t == NODE_REFERENCE) {
      r = nodeReference(b, 0);
    }
    else if (t == PARAMETER) {
      r = parameter(b, 0);
    }
    else if (t == PARAMETERS) {
      r = parameters(b, 0);
    }
    else if (t == RANGE) {
      r = range(b, 0);
    }
    else if (t == RULE) {
      r = rule(b, 0);
    }
    else if (t == RULE_CONTAINER) {
      r = ruleContainer(b, 0);
    }
    else if (t == SIGNATURE) {
      r = signature(b, 0);
    }
    else if (t == SIZE) {
      r = size(b, 0);
    }
    else if (t == SIZE_RANGE) {
      r = sizeRange(b, 0);
    }
    else if (t == STRING_VALUE) {
      r = stringValue(b, 0);
    }
    else if (t == TABLE_PARAMETERS) {
      r = tableParameters(b, 0);
    }
    else if (t == TAGS) {
      r = tags(b, 0);
    }
    else if (t == TUPLE_VALUE) {
      r = tupleValue(b, 0);
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
    else if (t == WITH_TABLE) {
      r = withTable(b, 0);
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
  // ANCHOR_VALUE
  public static boolean anchor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "anchor")) return false;
    if (!nextTokenIs(b, ANCHOR_VALUE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ANCHOR_VALUE);
    exit_section_(b, m, ANCHOR, r);
    return r;
  }

  /* ********************************************************** */
  // COMPONENT | FEATURE | PROTOTYPE | ENCLOSED
  public static boolean annotation(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotation")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ANNOTATION, "<annotation>");
    r = consumeToken(b, COMPONENT);
    if (!r) r = consumeToken(b, FEATURE);
    if (!r) r = consumeToken(b, PROTOTYPE);
    if (!r) r = consumeToken(b, ENCLOSED);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INTO annotation+
  public static boolean annotations(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotations")) return false;
    if (!nextTokenIs(b, INTO)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, ANNOTATIONS, null);
    r = consumeToken(b, INTO);
    p = r; // pin = 1
    r = r && annotations_1(b, l + 1);
    exit_section_(b, l, m, r, p, null);
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
  // (NEW_LINE_INDENT NEWLINE? | INLINE) (nodeConstituents NEWLINE+)+ DEDENT
  public static boolean body(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "body")) return false;
    if (!nextTokenIs(b, "<body>", INLINE, NEW_LINE_INDENT)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, BODY, "<body>");
    r = body_0(b, l + 1);
    p = r; // pin = 1
    r = r && report_error_(b, body_1(b, l + 1));
    r = p && consumeToken(b, DEDENT) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // NEW_LINE_INDENT NEWLINE? | INLINE
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
  // NEW_LINE_INDENT (stringValue | expression) NEWLINE? DEDENT
  public static boolean bodyValue(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "bodyValue")) return false;
    if (!nextTokenIs(b, NEW_LINE_INDENT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, NEW_LINE_INDENT);
    r = r && bodyValue_1(b, l + 1);
    r = r && bodyValue_2(b, l + 1);
    r = r && consumeToken(b, DEDENT);
    exit_section_(b, m, BODY_VALUE, r);
    return r;
  }

  // stringValue | expression
  private static boolean bodyValue_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "bodyValue_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = stringValue(b, l + 1);
    if (!r) r = expression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // NEWLINE?
  private static boolean bodyValue_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "bodyValue_2")) return false;
    consumeToken(b, NEWLINE);
    return true;
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
  // AT identifierReference
  public static boolean classReference(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classReference")) return false;
    if (!nextTokenIs(b, AT)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, CLASS_REFERENCE, null);
    r = consumeToken(b, AT);
    p = r; // pin = 1
    r = r && identifierReference(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // CLASS_TYPE
  public static boolean classTypeValue(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classTypeValue")) return false;
    if (!nextTokenIs(b, CLASS_TYPE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, CLASS_TYPE);
    exit_section_(b, m, CLASS_TYPE_VALUE, r);
    return r;
  }

  /* ********************************************************** */
  // WITH identifierReference (COMMA identifierReference)*
  public static boolean constraint(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "constraint")) return false;
    if (!nextTokenIs(b, WITH)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, CONSTRAINT, null);
    r = consumeToken(b, WITH);
    p = r; // pin = 1
    r = r && report_error_(b, identifierReference(b, l + 1));
    r = p && constraint_2(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
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
  // (DOC_LINE NEWLINE?)+
  public static boolean doc(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "doc")) return false;
    if (!nextTokenIs(b, DOC_LINE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = doc_0(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!doc_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "doc", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, DOC, r);
    return r;
  }

  // DOC_LINE NEWLINE?
  private static boolean doc_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "doc_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DOC_LINE);
    r = r && doc_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // NEWLINE?
  private static boolean doc_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "doc_0_1")) return false;
    consumeToken(b, NEWLINE);
    return true;
  }

  /* ********************************************************** */
  // NATURAL_VALUE_KEY | NEGATIVE_VALUE_KEY | DOUBLE_VALUE_KEY
  public static boolean doubleValue(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "doubleValue")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, DOUBLE_VALUE, "<double value>");
    r = consumeToken(b, NATURAL_VALUE_KEY);
    if (!r) r = consumeToken(b, NEGATIVE_VALUE_KEY);
    if (!r) r = consumeToken(b, DOUBLE_VALUE_KEY);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // DSL headerReference
  public static boolean dslDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dslDeclaration")) return false;
    if (!nextTokenIs(b, DSL)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DSL);
    r = r && headerReference(b, l + 1);
    exit_section_(b, m, DSL_DECLARATION, r);
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
  // EXPRESSION_BEGIN CHARACTER* EXPRESSION_END
  public static boolean expression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expression")) return false;
    if (!nextTokenIs(b, EXPRESSION_BEGIN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, EXPRESSION_BEGIN);
    r = r && expression_1(b, l + 1);
    r = r && consumeToken(b, EXPRESSION_END);
    exit_section_(b, m, EXPRESSION, r);
    return r;
  }

  // CHARACTER*
  private static boolean expression_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expression_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeToken(b, CHARACTER)) break;
      if (!empty_element_parsed_guard_(b, "expression_1", c)) break;
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
    Marker m = enter_section_(b, l, _NONE_, FACET_APPLY, null);
    r = consumeToken(b, AS);
    p = r; // pin = 1
    r = r && report_error_(b, metaIdentifier(b, l + 1));
    r = p && report_error_(b, facetApply_2(b, l + 1)) && r;
    r = p && report_error_(b, facetApply_3(b, l + 1)) && r;
    r = p && facetApply_4(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
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
  // ON (identifierReference | ANY) constraint?
  public static boolean facetTarget(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "facetTarget")) return false;
    if (!nextTokenIs(b, ON)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, FACET_TARGET, null);
    r = consumeToken(b, ON);
    p = r; // pin = 1
    r = r && report_error_(b, facetTarget_1(b, l + 1));
    r = p && facetTarget_2(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
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

  /* ********************************************************** */
  // ABSTRACT | TERMINAL | PRIVATE | REACTIVE | COMPONENT
  // 	| FEATURE | PROTOTYPE | ENCLOSED | FINAL | CONCEPT | VOLATILE
  public static boolean flag(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "flag")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FLAG, "<flag>");
    r = consumeToken(b, ABSTRACT);
    if (!r) r = consumeToken(b, TERMINAL);
    if (!r) r = consumeToken(b, PRIVATE);
    if (!r) r = consumeToken(b, REACTIVE);
    if (!r) r = consumeToken(b, COMPONENT);
    if (!r) r = consumeToken(b, FEATURE);
    if (!r) r = consumeToken(b, PROTOTYPE);
    if (!r) r = consumeToken(b, ENCLOSED);
    if (!r) r = consumeToken(b, FINAL);
    if (!r) r = consumeToken(b, CONCEPT);
    if (!r) r = consumeToken(b, VOLATILE);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // IS flag+
  public static boolean flags(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "flags")) return false;
    if (!nextTokenIs(b, IS)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, FLAGS, null);
    r = consumeToken(b, IS);
    p = r; // pin = 1
    r = r && flags_1(b, l + 1);
    exit_section_(b, l, m, r, p, null);
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
  // identifier (DOT | PLUS)
  static boolean hierarchy(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "hierarchy")) return false;
    if (!nextTokenIs(b, IDENTIFIER_KEY)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = identifier(b, l + 1);
    r = r && hierarchy_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // DOT | PLUS
  private static boolean hierarchy_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "hierarchy_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DOT);
    if (!r) r = consumeToken(b, PLUS);
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
  // NATURAL_VALUE_KEY | NEGATIVE_VALUE_KEY
  public static boolean integerValue(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "integerValue")) return false;
    if (!nextTokenIs(b, "<integer value>", NATURAL_VALUE_KEY, NEGATIVE_VALUE_KEY)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, INTEGER_VALUE, "<integer value>");
    r = consumeToken(b, NATURAL_VALUE_KEY);
    if (!r) r = consumeToken(b, NEGATIVE_VALUE_KEY);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // (NATURAL_VALUE_KEY | STAR) DOT DOT (NATURAL_VALUE_KEY | STAR)
  public static boolean listRange(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "listRange")) return false;
    if (!nextTokenIs(b, "<list range>", NATURAL_VALUE_KEY, STAR)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, LIST_RANGE, "<list range>");
    r = listRange_0(b, l + 1);
    r = r && consumeTokens(b, 0, DOT, DOT);
    r = r && listRange_3(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // NATURAL_VALUE_KEY | STAR
  private static boolean listRange_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "listRange_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, NATURAL_VALUE_KEY);
    if (!r) r = consumeToken(b, STAR);
    exit_section_(b, m, null, r);
    return r;
  }

  // NATURAL_VALUE_KEY | STAR
  private static boolean listRange_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "listRange_3")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, NATURAL_VALUE_KEY);
    if (!r) r = consumeToken(b, STAR);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // METAIDENTIFIER_KEY | IDENTIFIER_KEY
  public static boolean metaIdentifier(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "metaIdentifier")) return false;
    if (!nextTokenIs(b, "<meta identifier>", IDENTIFIER_KEY, METAIDENTIFIER_KEY)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, META_IDENTIFIER, "<meta identifier>");
    r = consumeToken(b, METAIDENTIFIER_KEY);
    if (!r) r = consumeToken(b, IDENTIFIER_KEY);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // identifier | METRIC_VALUE_KEY
  public static boolean metric(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "metric")) return false;
    if (!nextTokenIs(b, "<metric>", IDENTIFIER_KEY, METRIC_VALUE_KEY)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, METRIC, "<metric>");
    r = identifier(b, l + 1);
    if (!r) r = consumeToken(b, METRIC_VALUE_KEY);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // doc? signature body?
  public static boolean node(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "node")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, NODE, "<node>");
    r = node_0(b, l + 1);
    r = r && signature(b, l + 1);
    p = r; // pin = 2
    r = r && node_2(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // doc?
  private static boolean node_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "node_0")) return false;
    doc(b, l + 1);
    return true;
  }

  // body?
  private static boolean node_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "node_2")) return false;
    body(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // varInit | variable | node | facetApply | nodeReference
  static boolean nodeConstituents(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "nodeConstituents")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = varInit(b, l + 1);
    if (!r) r = variable(b, l + 1);
    if (!r) r = node(b, l + 1);
    if (!r) r = facetApply(b, l + 1);
    if (!r) r = nodeReference(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // HAS ruleContainer? identifierReference ruleContainer? tags?
  public static boolean nodeReference(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "nodeReference")) return false;
    if (!nextTokenIs(b, HAS)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, NODE_REFERENCE, null);
    r = consumeToken(b, HAS);
    p = r; // pin = 1
    r = r && report_error_(b, nodeReference_1(b, l + 1));
    r = p && report_error_(b, identifierReference(b, l + 1)) && r;
    r = p && report_error_(b, nodeReference_3(b, l + 1)) && r;
    r = p && nodeReference_4(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // ruleContainer?
  private static boolean nodeReference_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "nodeReference_1")) return false;
    ruleContainer(b, l + 1);
    return true;
  }

  // ruleContainer?
  private static boolean nodeReference_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "nodeReference_3")) return false;
    ruleContainer(b, l + 1);
    return true;
  }

  // tags?
  private static boolean nodeReference_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "nodeReference_4")) return false;
    tags(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // (identifier EQUALS)? value
  public static boolean parameter(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameter")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PARAMETER, "<parameter>");
    r = parameter_0(b, l + 1);
    r = r && value(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (identifier EQUALS)?
  private static boolean parameter_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameter_0")) return false;
    parameter_0_0(b, l + 1);
    return true;
  }

  // identifier EQUALS
  private static boolean parameter_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameter_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = identifier(b, l + 1);
    r = r && consumeToken(b, EQUALS);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // LEFT_PARENTHESIS (parameter (COMMA parameter)*)? RIGHT_PARENTHESIS
  public static boolean parameters(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameters")) return false;
    if (!nextTokenIs(b, LEFT_PARENTHESIS)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, PARAMETERS, null);
    r = consumeToken(b, LEFT_PARENTHESIS);
    p = r; // pin = 1
    r = r && report_error_(b, parameters_1(b, l + 1));
    r = p && consumeToken(b, RIGHT_PARENTHESIS) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (parameter (COMMA parameter)*)?
  private static boolean parameters_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameters_1")) return false;
    parameters_1_0(b, l + 1);
    return true;
  }

  // parameter (COMMA parameter)*
  private static boolean parameters_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameters_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = parameter(b, l + 1);
    r = r && parameters_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (COMMA parameter)*
  private static boolean parameters_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameters_1_0_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!parameters_1_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "parameters_1_0_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // COMMA parameter
  private static boolean parameters_1_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameters_1_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && parameter(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // EXTENDS identifierReference
  static boolean parent(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parent")) return false;
    if (!nextTokenIs(b, EXTENDS)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeToken(b, EXTENDS);
    p = r; // pin = 1
    r = r && identifierReference(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // (doubleValue | integerValue | STAR) DOT DOT (doubleValue | integerValue | STAR)
  public static boolean range(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "range")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, RANGE, "<range>");
    r = range_0(b, l + 1);
    r = r && consumeTokens(b, 0, DOT, DOT);
    r = r && range_3(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // doubleValue | integerValue | STAR
  private static boolean range_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "range_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = doubleValue(b, l + 1);
    if (!r) r = integerValue(b, l + 1);
    if (!r) r = consumeToken(b, STAR);
    exit_section_(b, m, null, r);
    return r;
  }

  // doubleValue | integerValue | STAR
  private static boolean range_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "range_3")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = doubleValue(b, l + 1);
    if (!r) r = integerValue(b, l + 1);
    if (!r) r = consumeToken(b, STAR);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // COMMENT? NEWLINE* (dslDeclaration NEWLINE+)? imports? (node NEWLINE+)*
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

  // (dslDeclaration NEWLINE+)?
  private static boolean root_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "root_2")) return false;
    root_2_0(b, l + 1);
    return true;
  }

  // dslDeclaration NEWLINE+
  private static boolean root_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "root_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = dslDeclaration(b, l + 1);
    r = r && root_2_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // NEWLINE+
  private static boolean root_2_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "root_2_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, NEWLINE);
    int c = current_position_(b);
    while (r) {
      if (!consumeToken(b, NEWLINE)) break;
      if (!empty_element_parsed_guard_(b, "root_2_0_1", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
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
  // (LEFT_CURLY (classTypeValue | identifier+ | ((range | stringValue) metric?) | metric) RIGHT_CURLY) | identifierReference
  public static boolean rule(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "rule")) return false;
    if (!nextTokenIs(b, "<rule>", IDENTIFIER_KEY, LEFT_CURLY)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, RULE, "<rule>");
    r = rule_0(b, l + 1);
    if (!r) r = identifierReference(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // LEFT_CURLY (classTypeValue | identifier+ | ((range | stringValue) metric?) | metric) RIGHT_CURLY
  private static boolean rule_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "rule_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LEFT_CURLY);
    r = r && rule_0_1(b, l + 1);
    r = r && consumeToken(b, RIGHT_CURLY);
    exit_section_(b, m, null, r);
    return r;
  }

  // classTypeValue | identifier+ | ((range | stringValue) metric?) | metric
  private static boolean rule_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "rule_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = classTypeValue(b, l + 1);
    if (!r) r = rule_0_1_1(b, l + 1);
    if (!r) r = rule_0_1_2(b, l + 1);
    if (!r) r = metric(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // identifier+
  private static boolean rule_0_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "rule_0_1_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = identifier(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!identifier(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "rule_0_1_1", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // (range | stringValue) metric?
  private static boolean rule_0_1_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "rule_0_1_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = rule_0_1_2_0(b, l + 1);
    r = r && rule_0_1_2_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // range | stringValue
  private static boolean rule_0_1_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "rule_0_1_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = range(b, l + 1);
    if (!r) r = stringValue(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // metric?
  private static boolean rule_0_1_2_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "rule_0_1_2_1")) return false;
    metric(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // COLON rule
  public static boolean ruleContainer(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ruleContainer")) return false;
    if (!nextTokenIs(b, COLON)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, RULE_CONTAINER, null);
    r = consumeToken(b, COLON);
    p = r; // pin = 1
    r = r && rule(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // (subNode | (metaIdentifier ruleContainer? parameters? (identifier ruleContainer?)? parent?)) (withTable | facetTarget? tags? anchor?)
  public static boolean signature(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "signature")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, SIGNATURE, "<signature>");
    r = signature_0(b, l + 1);
    p = r; // pin = 1
    r = r && signature_1(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // subNode | (metaIdentifier ruleContainer? parameters? (identifier ruleContainer?)? parent?)
  private static boolean signature_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "signature_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = subNode(b, l + 1);
    if (!r) r = signature_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // metaIdentifier ruleContainer? parameters? (identifier ruleContainer?)? parent?
  private static boolean signature_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "signature_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = metaIdentifier(b, l + 1);
    r = r && signature_0_1_1(b, l + 1);
    r = r && signature_0_1_2(b, l + 1);
    r = r && signature_0_1_3(b, l + 1);
    r = r && signature_0_1_4(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ruleContainer?
  private static boolean signature_0_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "signature_0_1_1")) return false;
    ruleContainer(b, l + 1);
    return true;
  }

  // parameters?
  private static boolean signature_0_1_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "signature_0_1_2")) return false;
    parameters(b, l + 1);
    return true;
  }

  // (identifier ruleContainer?)?
  private static boolean signature_0_1_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "signature_0_1_3")) return false;
    signature_0_1_3_0(b, l + 1);
    return true;
  }

  // identifier ruleContainer?
  private static boolean signature_0_1_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "signature_0_1_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = identifier(b, l + 1);
    r = r && signature_0_1_3_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ruleContainer?
  private static boolean signature_0_1_3_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "signature_0_1_3_0_1")) return false;
    ruleContainer(b, l + 1);
    return true;
  }

  // parent?
  private static boolean signature_0_1_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "signature_0_1_4")) return false;
    parent(b, l + 1);
    return true;
  }

  // withTable | facetTarget? tags? anchor?
  private static boolean signature_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "signature_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = withTable(b, l + 1);
    if (!r) r = signature_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // facetTarget? tags? anchor?
  private static boolean signature_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "signature_1_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = signature_1_1_0(b, l + 1);
    r = r && signature_1_1_1(b, l + 1);
    r = r && signature_1_1_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // facetTarget?
  private static boolean signature_1_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "signature_1_1_0")) return false;
    facetTarget(b, l + 1);
    return true;
  }

  // tags?
  private static boolean signature_1_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "signature_1_1_1")) return false;
    tags(b, l + 1);
    return true;
  }

  // anchor?
  private static boolean signature_1_1_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "signature_1_1_2")) return false;
    anchor(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // NATURAL_VALUE_KEY | listRange
  public static boolean size(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "size")) return false;
    if (!nextTokenIs(b, "<size>", NATURAL_VALUE_KEY, STAR)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SIZE, "<size>");
    r = consumeToken(b, NATURAL_VALUE_KEY);
    if (!r) r = listRange(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // LEFT_SQUARE size? RIGHT_SQUARE
  public static boolean sizeRange(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "sizeRange")) return false;
    if (!nextTokenIs(b, LEFT_SQUARE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LEFT_SQUARE);
    r = r && sizeRange_1(b, l + 1);
    r = r && consumeToken(b, RIGHT_SQUARE);
    exit_section_(b, m, SIZE_RANGE, r);
    return r;
  }

  // size?
  private static boolean sizeRange_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "sizeRange_1")) return false;
    size(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // QUOTE_BEGIN CHARACTER* QUOTE_END
  public static boolean stringValue(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "stringValue")) return false;
    if (!nextTokenIs(b, QUOTE_BEGIN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, QUOTE_BEGIN);
    r = r && stringValue_1(b, l + 1);
    r = r && consumeToken(b, QUOTE_END);
    exit_section_(b, m, STRING_VALUE, r);
    return r;
  }

  // CHARACTER*
  private static boolean stringValue_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "stringValue_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeToken(b, CHARACTER)) break;
      if (!empty_element_parsed_guard_(b, "stringValue_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // SUB ruleContainer? parameters? identifier ruleContainer?
  static boolean subNode(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "subNode")) return false;
    if (!nextTokenIs(b, SUB)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeToken(b, SUB);
    p = r; // pin = 1
    r = r && report_error_(b, subNode_1(b, l + 1));
    r = p && report_error_(b, subNode_2(b, l + 1)) && r;
    r = p && report_error_(b, identifier(b, l + 1)) && r;
    r = p && subNode_4(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // ruleContainer?
  private static boolean subNode_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "subNode_1")) return false;
    ruleContainer(b, l + 1);
    return true;
  }

  // parameters?
  private static boolean subNode_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "subNode_2")) return false;
    parameters(b, l + 1);
    return true;
  }

  // ruleContainer?
  private static boolean subNode_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "subNode_4")) return false;
    ruleContainer(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // LEFT_PARENTHESIS (IDENTIFIER_KEY+ (COMMA IDENTIFIER_KEY+)*)? RIGHT_PARENTHESIS
  public static boolean tableParameters(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tableParameters")) return false;
    if (!nextTokenIs(b, LEFT_PARENTHESIS)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LEFT_PARENTHESIS);
    r = r && tableParameters_1(b, l + 1);
    r = r && consumeToken(b, RIGHT_PARENTHESIS);
    exit_section_(b, m, TABLE_PARAMETERS, r);
    return r;
  }

  // (IDENTIFIER_KEY+ (COMMA IDENTIFIER_KEY+)*)?
  private static boolean tableParameters_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tableParameters_1")) return false;
    tableParameters_1_0(b, l + 1);
    return true;
  }

  // IDENTIFIER_KEY+ (COMMA IDENTIFIER_KEY+)*
  private static boolean tableParameters_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tableParameters_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = tableParameters_1_0_0(b, l + 1);
    r = r && tableParameters_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // IDENTIFIER_KEY+
  private static boolean tableParameters_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tableParameters_1_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER_KEY);
    int c = current_position_(b);
    while (r) {
      if (!consumeToken(b, IDENTIFIER_KEY)) break;
      if (!empty_element_parsed_guard_(b, "tableParameters_1_0_0", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // (COMMA IDENTIFIER_KEY+)*
  private static boolean tableParameters_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tableParameters_1_0_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!tableParameters_1_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "tableParameters_1_0_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // COMMA IDENTIFIER_KEY+
  private static boolean tableParameters_1_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tableParameters_1_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && tableParameters_1_0_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // IDENTIFIER_KEY+
  private static boolean tableParameters_1_0_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tableParameters_1_0_1_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER_KEY);
    int c = current_position_(b);
    while (r) {
      if (!consumeToken(b, IDENTIFIER_KEY)) break;
      if (!empty_element_parsed_guard_(b, "tableParameters_1_0_1_0_1", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // flags? annotations?
  public static boolean tags(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tags")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, TAGS, "<tags>");
    r = tags_0(b, l + 1);
    p = r; // pin = 1
    r = r && tags_1(b, l + 1);
    exit_section_(b, l, m, r, p, null);
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
  // stringValue COLON doubleValue
  public static boolean tupleValue(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tupleValue")) return false;
    if (!nextTokenIs(b, QUOTE_BEGIN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = stringValue(b, l + 1);
    r = r && consumeToken(b, COLON);
    r = r && doubleValue(b, l + 1);
    exit_section_(b, m, TUPLE_VALUE, r);
    return r;
  }

  /* ********************************************************** */
  // stringValue+
  //         | booleanValue+
  //         | tupleValue+
  //         | integerValue+ metric?
  //         | doubleValue+  metric?
  //         | expression+
  //         | emptyField
  //         | identifierReference+
  //         | classReference+
  public static boolean value(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "value")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, VALUE, "<value>");
    r = value_0(b, l + 1);
    if (!r) r = value_1(b, l + 1);
    if (!r) r = value_2(b, l + 1);
    if (!r) r = value_3(b, l + 1);
    if (!r) r = value_4(b, l + 1);
    if (!r) r = value_5(b, l + 1);
    if (!r) r = emptyField(b, l + 1);
    if (!r) r = value_7(b, l + 1);
    if (!r) r = value_8(b, l + 1);
    exit_section_(b, l, m, r, false, null);
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

  // tupleValue+
  private static boolean value_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "value_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = tupleValue(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!tupleValue(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "value_2", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // integerValue+ metric?
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

  // metric?
  private static boolean value_3_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "value_3_1")) return false;
    metric(b, l + 1);
    return true;
  }

  // doubleValue+  metric?
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

  // metric?
  private static boolean value_4_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "value_4_1")) return false;
    metric(b, l + 1);
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

  // identifierReference+
  private static boolean value_7(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "value_7")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = identifierReference(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!identifierReference(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "value_7", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // classReference+
  private static boolean value_8(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "value_8")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = classReference(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!classReference(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "value_8", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // identifier ((EQUALS value) | bodyValue)
  public static boolean varInit(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "varInit")) return false;
    if (!nextTokenIs(b, IDENTIFIER_KEY)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = identifier(b, l + 1);
    r = r && varInit_1(b, l + 1);
    exit_section_(b, m, VAR_INIT, r);
    return r;
  }

  // (EQUALS value) | bodyValue
  private static boolean varInit_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "varInit_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = varInit_1_0(b, l + 1);
    if (!r) r = bodyValue(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // EQUALS value
  private static boolean varInit_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "varInit_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, EQUALS);
    r = r && value(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // doc? VAR variableType sizeRange? ruleContainer? identifier (EQUALS value)? flags? anchor? bodyValue?
  public static boolean variable(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variable")) return false;
    if (!nextTokenIs(b, "<variable>", DOC_LINE, VAR)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, VARIABLE, "<variable>");
    r = variable_0(b, l + 1);
    r = r && consumeToken(b, VAR);
    p = r; // pin = 2
    r = r && report_error_(b, variableType(b, l + 1));
    r = p && report_error_(b, variable_3(b, l + 1)) && r;
    r = p && report_error_(b, variable_4(b, l + 1)) && r;
    r = p && report_error_(b, identifier(b, l + 1)) && r;
    r = p && report_error_(b, variable_6(b, l + 1)) && r;
    r = p && report_error_(b, variable_7(b, l + 1)) && r;
    r = p && report_error_(b, variable_8(b, l + 1)) && r;
    r = p && variable_9(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // doc?
  private static boolean variable_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variable_0")) return false;
    doc(b, l + 1);
    return true;
  }

  // sizeRange?
  private static boolean variable_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variable_3")) return false;
    sizeRange(b, l + 1);
    return true;
  }

  // ruleContainer?
  private static boolean variable_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variable_4")) return false;
    ruleContainer(b, l + 1);
    return true;
  }

  // (EQUALS value)?
  private static boolean variable_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variable_6")) return false;
    variable_6_0(b, l + 1);
    return true;
  }

  // EQUALS value
  private static boolean variable_6_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variable_6_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, EQUALS);
    r = r && value(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // flags?
  private static boolean variable_7(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variable_7")) return false;
    flags(b, l + 1);
    return true;
  }

  // anchor?
  private static boolean variable_8(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variable_8")) return false;
    anchor(b, l + 1);
    return true;
  }

  // bodyValue?
  private static boolean variable_9(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variable_9")) return false;
    bodyValue(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // FUNCTION_TYPE
  //                 | INT_TYPE
  //                 | DOUBLE_TYPE
  //                 | BOOLEAN_TYPE
  //                 | STRING_TYPE
  //                 | DATE_TYPE
  //                 | TIME_TYPE
  //                 | WORD_TYPE
  //                 | OBJECT_TYPE
  //                 | RESOURCE_TYPE
  //                 | identifierReference
  public static boolean variableType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variableType")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, VARIABLE_TYPE, "<variable type>");
    r = consumeToken(b, FUNCTION_TYPE);
    if (!r) r = consumeToken(b, INT_TYPE);
    if (!r) r = consumeToken(b, DOUBLE_TYPE);
    if (!r) r = consumeToken(b, BOOLEAN_TYPE);
    if (!r) r = consumeToken(b, STRING_TYPE);
    if (!r) r = consumeToken(b, DATE_TYPE);
    if (!r) r = consumeToken(b, TIME_TYPE);
    if (!r) r = consumeToken(b, WORD_TYPE);
    if (!r) r = consumeToken(b, OBJECT_TYPE);
    if (!r) r = consumeToken(b, RESOURCE_TYPE);
    if (!r) r = identifierReference(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // LIST WITH identifierReference tableParameters
  public static boolean withTable(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "withTable")) return false;
    if (!nextTokenIs(b, LIST)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, WITH_TABLE, null);
    r = consumeTokens(b, 1, LIST, WITH);
    p = r; // pin = 1
    r = r && report_error_(b, identifierReference(b, l + 1));
    r = p && tableParameters(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

}
