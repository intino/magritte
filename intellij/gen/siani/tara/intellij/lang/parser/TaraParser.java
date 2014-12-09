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

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class TaraParser implements PsiParser {

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
    else if (t == ANNOTATIONS) {
      r = annotations(b, 0);
    }
    else if (t == ANNOTATIONS_AND_FACETS) {
      r = annotationsAndFacets(b, 0);
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
    else if (t == CONCEPT) {
      r = concept(b, 0);
    }
    else if (t == CONCEPT_REFERENCE) {
      r = conceptReference(b, 0);
    }
    else if (t == COUNT) {
      r = count(b, 0);
    }
    else if (t == DATE_VALUE) {
      r = dateValue(b, 0);
    }
    else if (t == DOC) {
      r = doc(b, 0);
    }
    else if (t == DOUBLE_VALUE) {
      r = doubleValue(b, 0);
    }
    else if (t == EMPTY_FIELD) {
      r = emptyField(b, 0);
    }
    else if (t == EXPLICIT_PARAMETER) {
      r = explicitParameter(b, 0);
    }
    else if (t == FACET_APPLY) {
      r = facetApply(b, 0);
    }
    else if (t == FACET_TARGET) {
      r = facetTarget(b, 0);
    }
    else if (t == HEADER) {
      r = header(b, 0);
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
    else if (t == INTEGER_VALUE) {
      r = integerValue(b, 0);
    }
    else if (t == LINK_VALUE) {
      r = linkValue(b, 0);
    }
    else if (t == MEASURE) {
      r = measure(b, 0);
    }
    else if (t == META_IDENTIFIER) {
      r = metaIdentifier(b, 0);
    }
    else if (t == META_WORD) {
      r = metaWord(b, 0);
    }
    else if (t == NATURAL_VALUE) {
      r = naturalValue(b, 0);
    }
    else if (t == PARAMETER_VALUE) {
      r = parameterValue(b, 0);
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
    else if (t == VAR_INIT) {
      r = varInit(b, 0);
    }
    else if (t == VAR_INIT_VALUE) {
      r = varInitValue(b, 0);
    }
    else if (t == VARIABLE) {
      r = variable(b, 0);
    }
    else if (t == WORD) {
      r = word(b, 0);
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
  // NEWLINE+ USE_KEY headerReference (AS METAMODEL)?
  public static boolean anImport(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "anImport")) return false;
    if (!nextTokenIs(b, NEWLINE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = anImport_0(b, l + 1);
    r = r && consumeToken(b, USE_KEY);
    r = r && headerReference(b, l + 1);
    r = r && anImport_3(b, l + 1);
    exit_section_(b, m, AN_IMPORT, r);
    return r;
  }

  // NEWLINE+
  private static boolean anImport_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "anImport_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, NEWLINE);
    int c = current_position_(b);
    while (r) {
      if (!consumeToken(b, NEWLINE)) break;
      if (!empty_element_parsed_guard_(b, "anImport_0", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // (AS METAMODEL)?
  private static boolean anImport_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "anImport_3")) return false;
    anImport_3_0(b, l + 1);
    return true;
  }

  // AS METAMODEL
  private static boolean anImport_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "anImport_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, AS, METAMODEL);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // (ABSTRACT | TERMINAL | SINGLE | MULTIPLE | REQUIRED | NAMED
  // 	| COMPONENT | ROOT | FACET | INTENTION | PROPERTY | LOCAL | ADDRESSED | AGGREGATED | COMPOSED)+
  public static boolean annotations(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotations")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<annotations>");
    r = annotations_0(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!annotations_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "annotations", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, l, m, ANNOTATIONS, r, false, null);
    return r;
  }

  // ABSTRACT | TERMINAL | SINGLE | MULTIPLE | REQUIRED | NAMED
  // 	| COMPONENT | ROOT | FACET | INTENTION | PROPERTY | LOCAL | ADDRESSED | AGGREGATED | COMPOSED
  private static boolean annotations_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotations_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ABSTRACT);
    if (!r) r = consumeToken(b, TERMINAL);
    if (!r) r = consumeToken(b, SINGLE);
    if (!r) r = consumeToken(b, MULTIPLE);
    if (!r) r = consumeToken(b, REQUIRED);
    if (!r) r = consumeToken(b, NAMED);
    if (!r) r = consumeToken(b, COMPONENT);
    if (!r) r = consumeToken(b, ROOT);
    if (!r) r = consumeToken(b, FACET);
    if (!r) r = consumeToken(b, INTENTION);
    if (!r) r = consumeToken(b, PROPERTY);
    if (!r) r = consumeToken(b, LOCAL);
    if (!r) r = consumeToken(b, ADDRESSED);
    if (!r) r = consumeToken(b, AGGREGATED);
    if (!r) r = consumeToken(b, COMPOSED);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // IS (annotations+ | facetApply)
  public static boolean annotationsAndFacets(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotationsAndFacets")) return false;
    if (!nextTokenIs(b, IS)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeToken(b, IS);
    p = r; // pin = 1
    r = r && annotationsAndFacets_1(b, l + 1);
    exit_section_(b, l, m, ANNOTATIONS_AND_FACETS, r, p, null);
    return r || p;
  }

  // annotations+ | facetApply
  private static boolean annotationsAndFacets_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotationsAndFacets_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = annotationsAndFacets_1_0(b, l + 1);
    if (!r) r = facetApply(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // annotations+
  private static boolean annotationsAndFacets_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotationsAndFacets_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = annotations(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!annotations(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "annotationsAndFacets_1_0", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // COLON measure
  public static boolean attributeType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attributeType")) return false;
    if (!nextTokenIs(b, COLON)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COLON);
    r = r && measure(b, l + 1);
    exit_section_(b, m, ATTRIBUTE_TYPE, r);
    return r;
  }

  /* ********************************************************** */
  // (NEW_LINE_INDENT | INLINE) (conceptConstituents NEWLINE+)+ DEDENT
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

  // NEW_LINE_INDENT | INLINE
  private static boolean body_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "body_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, NEW_LINE_INDENT);
    if (!r) r = consumeToken(b, INLINE);
    exit_section_(b, m, null, r);
    return r;
  }

  // (conceptConstituents NEWLINE+)+
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

  // conceptConstituents NEWLINE+
  private static boolean body_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "body_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = conceptConstituents(b, l + 1);
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
  // BOOLEAN_TYPE LIST?  IDENTIFIER_KEY (EQUALS (booleanValue+ | emptyField))?
  static boolean booleanAttribute(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "booleanAttribute")) return false;
    if (!nextTokenIs(b, BOOLEAN_TYPE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeToken(b, BOOLEAN_TYPE);
    p = r; // pin = 1
    r = r && report_error_(b, booleanAttribute_1(b, l + 1));
    r = p && report_error_(b, consumeToken(b, IDENTIFIER_KEY)) && r;
    r = p && booleanAttribute_3(b, l + 1) && r;
    exit_section_(b, l, m, null, r, p, null);
    return r || p;
  }

  // LIST?
  private static boolean booleanAttribute_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "booleanAttribute_1")) return false;
    consumeToken(b, LIST);
    return true;
  }

  // (EQUALS (booleanValue+ | emptyField))?
  private static boolean booleanAttribute_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "booleanAttribute_3")) return false;
    booleanAttribute_3_0(b, l + 1);
    return true;
  }

  // EQUALS (booleanValue+ | emptyField)
  private static boolean booleanAttribute_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "booleanAttribute_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, EQUALS);
    r = r && booleanAttribute_3_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // booleanValue+ | emptyField
  private static boolean booleanAttribute_3_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "booleanAttribute_3_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = booleanAttribute_3_0_1_0(b, l + 1);
    if (!r) r = emptyField(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // booleanValue+
  private static boolean booleanAttribute_3_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "booleanAttribute_3_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = booleanValue(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!booleanValue(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "booleanAttribute_3_0_1_0", c)) break;
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
  // doc? signature annotationsAndFacets? body?
  public static boolean concept(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "concept")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, "<concept>");
    r = concept_0(b, l + 1);
    r = r && signature(b, l + 1);
    p = r; // pin = 2
    r = r && report_error_(b, concept_2(b, l + 1));
    r = p && concept_3(b, l + 1) && r;
    exit_section_(b, l, m, CONCEPT, r, p, null);
    return r || p;
  }

  // doc?
  private static boolean concept_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "concept_0")) return false;
    doc(b, l + 1);
    return true;
  }

  // annotationsAndFacets?
  private static boolean concept_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "concept_2")) return false;
    annotationsAndFacets(b, l + 1);
    return true;
  }

  // body?
  private static boolean concept_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "concept_3")) return false;
    body(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // varInit | variable | concept | annotationsAndFacets | facetTarget | conceptReference
  static boolean conceptConstituents(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "conceptConstituents")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = varInit(b, l + 1);
    if (!r) r = variable(b, l + 1);
    if (!r) r = concept(b, l + 1);
    if (!r) r = annotationsAndFacets(b, l + 1);
    if (!r) r = facetTarget(b, l + 1);
    if (!r) r = conceptReference(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // doc? HAS identifierReference (IS annotations)?
  public static boolean conceptReference(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "conceptReference")) return false;
    if (!nextTokenIs(b, "<concept reference>", DOC_LINE, HAS)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, "<concept reference>");
    r = conceptReference_0(b, l + 1);
    r = r && consumeToken(b, HAS);
    p = r; // pin = 2
    r = r && report_error_(b, identifierReference(b, l + 1));
    r = p && conceptReference_3(b, l + 1) && r;
    exit_section_(b, l, m, CONCEPT_REFERENCE, r, p, null);
    return r || p;
  }

  // doc?
  private static boolean conceptReference_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "conceptReference_0")) return false;
    doc(b, l + 1);
    return true;
  }

  // (IS annotations)?
  private static boolean conceptReference_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "conceptReference_3")) return false;
    conceptReference_3_0(b, l + 1);
    return true;
  }

  // IS annotations
  private static boolean conceptReference_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "conceptReference_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IS);
    r = r && annotations(b, l + 1);
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
  // DATE_TYPE    LIST?  IDENTIFIER_KEY (EQUALS (dateValue+ | emptyField))?
  static boolean dateAttribute(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dateAttribute")) return false;
    if (!nextTokenIs(b, DATE_TYPE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeToken(b, DATE_TYPE);
    p = r; // pin = 1
    r = r && report_error_(b, dateAttribute_1(b, l + 1));
    r = p && report_error_(b, consumeToken(b, IDENTIFIER_KEY)) && r;
    r = p && dateAttribute_3(b, l + 1) && r;
    exit_section_(b, l, m, null, r, p, null);
    return r || p;
  }

  // LIST?
  private static boolean dateAttribute_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dateAttribute_1")) return false;
    consumeToken(b, LIST);
    return true;
  }

  // (EQUALS (dateValue+ | emptyField))?
  private static boolean dateAttribute_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dateAttribute_3")) return false;
    dateAttribute_3_0(b, l + 1);
    return true;
  }

  // EQUALS (dateValue+ | emptyField)
  private static boolean dateAttribute_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dateAttribute_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, EQUALS);
    r = r && dateAttribute_3_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // dateValue+ | emptyField
  private static boolean dateAttribute_3_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dateAttribute_3_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = dateAttribute_3_0_1_0(b, l + 1);
    if (!r) r = emptyField(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // dateValue+
  private static boolean dateAttribute_3_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dateAttribute_3_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = dateValue(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!dateValue(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "dateAttribute_3_0_1_0", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // DATE_VALUE_KEY
  public static boolean dateValue(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dateValue")) return false;
    if (!nextTokenIs(b, DATE_VALUE_KEY)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DATE_VALUE_KEY);
    exit_section_(b, m, DATE_VALUE, r);
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
  // DOUBLE_TYPE  attributeType? (LIST | count)?  IDENTIFIER_KEY (EQUALS (doubleValue+  measure? | emptyField))?
  static boolean doubleAttribute(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "doubleAttribute")) return false;
    if (!nextTokenIs(b, DOUBLE_TYPE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeToken(b, DOUBLE_TYPE);
    p = r; // pin = 1
    r = r && report_error_(b, doubleAttribute_1(b, l + 1));
    r = p && report_error_(b, doubleAttribute_2(b, l + 1)) && r;
    r = p && report_error_(b, consumeToken(b, IDENTIFIER_KEY)) && r;
    r = p && doubleAttribute_4(b, l + 1) && r;
    exit_section_(b, l, m, null, r, p, null);
    return r || p;
  }

  // attributeType?
  private static boolean doubleAttribute_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "doubleAttribute_1")) return false;
    attributeType(b, l + 1);
    return true;
  }

  // (LIST | count)?
  private static boolean doubleAttribute_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "doubleAttribute_2")) return false;
    doubleAttribute_2_0(b, l + 1);
    return true;
  }

  // LIST | count
  private static boolean doubleAttribute_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "doubleAttribute_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LIST);
    if (!r) r = count(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (EQUALS (doubleValue+  measure? | emptyField))?
  private static boolean doubleAttribute_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "doubleAttribute_4")) return false;
    doubleAttribute_4_0(b, l + 1);
    return true;
  }

  // EQUALS (doubleValue+  measure? | emptyField)
  private static boolean doubleAttribute_4_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "doubleAttribute_4_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, EQUALS);
    r = r && doubleAttribute_4_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // doubleValue+  measure? | emptyField
  private static boolean doubleAttribute_4_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "doubleAttribute_4_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = doubleAttribute_4_0_1_0(b, l + 1);
    if (!r) r = emptyField(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // doubleValue+  measure?
  private static boolean doubleAttribute_4_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "doubleAttribute_4_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = doubleAttribute_4_0_1_0_0(b, l + 1);
    r = r && doubleAttribute_4_0_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // doubleValue+
  private static boolean doubleAttribute_4_0_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "doubleAttribute_4_0_1_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = doubleValue(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!doubleValue(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "doubleAttribute_4_0_1_0_0", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // measure?
  private static boolean doubleAttribute_4_0_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "doubleAttribute_4_0_1_0_1")) return false;
    measure(b, l + 1);
    return true;
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
  // identifier EQUALS parameterValue
  public static boolean explicitParameter(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "explicitParameter")) return false;
    if (!nextTokenIs(b, IDENTIFIER_KEY)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = identifier(b, l + 1);
    r = r && consumeToken(b, EQUALS);
    p = r; // pin = 2
    r = r && parameterValue(b, l + 1);
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
  // metaIdentifier parameters? (WITH metaIdentifier)? body?
  public static boolean facetApply(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "facetApply")) return false;
    if (!nextTokenIs(b, "<facet apply>", IDENTIFIER_KEY, METAIDENTIFIER_KEY)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, "<facet apply>");
    r = metaIdentifier(b, l + 1);
    p = r; // pin = 1
    r = r && report_error_(b, facetApply_1(b, l + 1));
    r = p && report_error_(b, facetApply_2(b, l + 1)) && r;
    r = p && facetApply_3(b, l + 1) && r;
    exit_section_(b, l, m, FACET_APPLY, r, p, null);
    return r || p;
  }

  // parameters?
  private static boolean facetApply_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "facetApply_1")) return false;
    parameters(b, l + 1);
    return true;
  }

  // (WITH metaIdentifier)?
  private static boolean facetApply_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "facetApply_2")) return false;
    facetApply_2_0(b, l + 1);
    return true;
  }

  // WITH metaIdentifier
  private static boolean facetApply_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "facetApply_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, WITH);
    r = r && metaIdentifier(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // body?
  private static boolean facetApply_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "facetApply_3")) return false;
    body(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // ON identifierReference ALWAYS? body?
  public static boolean facetTarget(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "facetTarget")) return false;
    if (!nextTokenIs(b, ON)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeToken(b, ON);
    p = r; // pin = 1
    r = r && report_error_(b, identifierReference(b, l + 1));
    r = p && report_error_(b, facetTarget_2(b, l + 1)) && r;
    r = p && facetTarget_3(b, l + 1) && r;
    exit_section_(b, l, m, FACET_TARGET, r, p, null);
    return r || p;
  }

  // ALWAYS?
  private static boolean facetTarget_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "facetTarget_2")) return false;
    consumeToken(b, ALWAYS);
    return true;
  }

  // body?
  private static boolean facetTarget_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "facetTarget_3")) return false;
    body(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // imports?
  public static boolean header(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "header")) return false;
    Marker m = enter_section_(b, l, _NONE_, "<header>");
    imports(b, l + 1);
    exit_section_(b, l, m, HEADER, true, false, null);
    return true;
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
  // parameterValue
  public static boolean implicitParameter(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "implicitParameter")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<implicit parameter>");
    r = parameterValue(b, l + 1);
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
  // anImport+
  static boolean imports(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "imports")) return false;
    if (!nextTokenIs(b, NEWLINE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = anImport(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!anImport(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "imports", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // INT_TYPE     attributeType? LIST?  IDENTIFIER_KEY (EQUALS (integerValue+ measure? | emptyField))?
  static boolean integerAttribute(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "integerAttribute")) return false;
    if (!nextTokenIs(b, INT_TYPE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeToken(b, INT_TYPE);
    p = r; // pin = 1
    r = r && report_error_(b, integerAttribute_1(b, l + 1));
    r = p && report_error_(b, integerAttribute_2(b, l + 1)) && r;
    r = p && report_error_(b, consumeToken(b, IDENTIFIER_KEY)) && r;
    r = p && integerAttribute_4(b, l + 1) && r;
    exit_section_(b, l, m, null, r, p, null);
    return r || p;
  }

  // attributeType?
  private static boolean integerAttribute_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "integerAttribute_1")) return false;
    attributeType(b, l + 1);
    return true;
  }

  // LIST?
  private static boolean integerAttribute_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "integerAttribute_2")) return false;
    consumeToken(b, LIST);
    return true;
  }

  // (EQUALS (integerValue+ measure? | emptyField))?
  private static boolean integerAttribute_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "integerAttribute_4")) return false;
    integerAttribute_4_0(b, l + 1);
    return true;
  }

  // EQUALS (integerValue+ measure? | emptyField)
  private static boolean integerAttribute_4_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "integerAttribute_4_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, EQUALS);
    r = r && integerAttribute_4_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // integerValue+ measure? | emptyField
  private static boolean integerAttribute_4_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "integerAttribute_4_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = integerAttribute_4_0_1_0(b, l + 1);
    if (!r) r = emptyField(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // integerValue+ measure?
  private static boolean integerAttribute_4_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "integerAttribute_4_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = integerAttribute_4_0_1_0_0(b, l + 1);
    r = r && integerAttribute_4_0_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // integerValue+
  private static boolean integerAttribute_4_0_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "integerAttribute_4_0_1_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = integerValue(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!integerValue(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "integerAttribute_4_0_1_0_0", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // measure?
  private static boolean integerAttribute_4_0_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "integerAttribute_4_0_1_0_1")) return false;
    measure(b, l + 1);
    return true;
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
  // ADDRESS_VALUE | identifierReference
  public static boolean linkValue(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "linkValue")) return false;
    if (!nextTokenIs(b, "<link value>", ADDRESS_VALUE, IDENTIFIER_KEY)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<link value>");
    r = consumeToken(b, ADDRESS_VALUE);
    if (!r) r = identifierReference(b, l + 1);
    exit_section_(b, l, m, LINK_VALUE, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER_KEY | MEASURE_VALUE
  public static boolean measure(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "measure")) return false;
    if (!nextTokenIs(b, "<measure>", IDENTIFIER_KEY, MEASURE_VALUE)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<measure>");
    r = consumeToken(b, IDENTIFIER_KEY);
    if (!r) r = consumeToken(b, MEASURE_VALUE);
    exit_section_(b, l, m, MEASURE, r, false, null);
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
  // metaIdentifier metaWordNames*
  public static boolean metaWord(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "metaWord")) return false;
    if (!nextTokenIs(b, "<meta word>", IDENTIFIER_KEY, METAIDENTIFIER_KEY)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, "<meta word>");
    r = metaIdentifier(b, l + 1);
    p = r; // pin = 1
    r = r && metaWord_1(b, l + 1);
    exit_section_(b, l, m, META_WORD, r, p, null);
    return r || p;
  }

  // metaWordNames*
  private static boolean metaWord_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "metaWord_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!metaWordNames(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "metaWord_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // DOT identifier
  static boolean metaWordNames(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "metaWordNames")) return false;
    if (!nextTokenIs(b, DOT)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeToken(b, DOT);
    p = r; // pin = 1
    r = r && identifier(b, l + 1);
    exit_section_(b, l, m, null, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // NATURAL_TYPE attributeType? LIST?  IDENTIFIER_KEY (EQUALS (naturalValue+ measure? | emptyField))?
  static boolean naturalAttribute(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "naturalAttribute")) return false;
    if (!nextTokenIs(b, NATURAL_TYPE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeToken(b, NATURAL_TYPE);
    p = r; // pin = 1
    r = r && report_error_(b, naturalAttribute_1(b, l + 1));
    r = p && report_error_(b, naturalAttribute_2(b, l + 1)) && r;
    r = p && report_error_(b, consumeToken(b, IDENTIFIER_KEY)) && r;
    r = p && naturalAttribute_4(b, l + 1) && r;
    exit_section_(b, l, m, null, r, p, null);
    return r || p;
  }

  // attributeType?
  private static boolean naturalAttribute_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "naturalAttribute_1")) return false;
    attributeType(b, l + 1);
    return true;
  }

  // LIST?
  private static boolean naturalAttribute_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "naturalAttribute_2")) return false;
    consumeToken(b, LIST);
    return true;
  }

  // (EQUALS (naturalValue+ measure? | emptyField))?
  private static boolean naturalAttribute_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "naturalAttribute_4")) return false;
    naturalAttribute_4_0(b, l + 1);
    return true;
  }

  // EQUALS (naturalValue+ measure? | emptyField)
  private static boolean naturalAttribute_4_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "naturalAttribute_4_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, EQUALS);
    r = r && naturalAttribute_4_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // naturalValue+ measure? | emptyField
  private static boolean naturalAttribute_4_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "naturalAttribute_4_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = naturalAttribute_4_0_1_0(b, l + 1);
    if (!r) r = emptyField(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // naturalValue+ measure?
  private static boolean naturalAttribute_4_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "naturalAttribute_4_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = naturalAttribute_4_0_1_0_0(b, l + 1);
    r = r && naturalAttribute_4_0_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // naturalValue+
  private static boolean naturalAttribute_4_0_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "naturalAttribute_4_0_1_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = naturalValue(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!naturalValue(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "naturalAttribute_4_0_1_0_0", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // measure?
  private static boolean naturalAttribute_4_0_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "naturalAttribute_4_0_1_0_1")) return false;
    measure(b, l + 1);
    return true;
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
  // identifierReference+
  // 				| stringValue+
  // 		        | booleanValue+
  // 		        | naturalValue+ measure?
  // 		        | integerValue+ measure?
  // 		        | doubleValue+  measure?
  // 		        | dateValue+
  // 		        | metaWord
  // 		        | emptyField
  public static boolean parameterValue(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameterValue")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<parameter value>");
    r = parameterValue_0(b, l + 1);
    if (!r) r = parameterValue_1(b, l + 1);
    if (!r) r = parameterValue_2(b, l + 1);
    if (!r) r = parameterValue_3(b, l + 1);
    if (!r) r = parameterValue_4(b, l + 1);
    if (!r) r = parameterValue_5(b, l + 1);
    if (!r) r = parameterValue_6(b, l + 1);
    if (!r) r = metaWord(b, l + 1);
    if (!r) r = emptyField(b, l + 1);
    exit_section_(b, l, m, PARAMETER_VALUE, r, false, null);
    return r;
  }

  // identifierReference+
  private static boolean parameterValue_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameterValue_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = identifierReference(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!identifierReference(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "parameterValue_0", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // stringValue+
  private static boolean parameterValue_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameterValue_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = stringValue(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!stringValue(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "parameterValue_1", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // booleanValue+
  private static boolean parameterValue_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameterValue_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = booleanValue(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!booleanValue(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "parameterValue_2", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // naturalValue+ measure?
  private static boolean parameterValue_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameterValue_3")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = parameterValue_3_0(b, l + 1);
    r = r && parameterValue_3_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // naturalValue+
  private static boolean parameterValue_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameterValue_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = naturalValue(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!naturalValue(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "parameterValue_3_0", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // measure?
  private static boolean parameterValue_3_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameterValue_3_1")) return false;
    measure(b, l + 1);
    return true;
  }

  // integerValue+ measure?
  private static boolean parameterValue_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameterValue_4")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = parameterValue_4_0(b, l + 1);
    r = r && parameterValue_4_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // integerValue+
  private static boolean parameterValue_4_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameterValue_4_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = integerValue(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!integerValue(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "parameterValue_4_0", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // measure?
  private static boolean parameterValue_4_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameterValue_4_1")) return false;
    measure(b, l + 1);
    return true;
  }

  // doubleValue+  measure?
  private static boolean parameterValue_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameterValue_5")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = parameterValue_5_0(b, l + 1);
    r = r && parameterValue_5_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // doubleValue+
  private static boolean parameterValue_5_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameterValue_5_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = doubleValue(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!doubleValue(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "parameterValue_5_0", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // measure?
  private static boolean parameterValue_5_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameterValue_5_1")) return false;
    measure(b, l + 1);
    return true;
  }

  // dateValue+
  private static boolean parameterValue_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameterValue_6")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = dateValue(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!dateValue(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "parameterValue_6", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
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
  // identifierReference LIST? IDENTIFIER_KEY (EQUALS emptyField)?
  static boolean referenceAttribute(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "referenceAttribute")) return false;
    if (!nextTokenIs(b, IDENTIFIER_KEY)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = identifierReference(b, l + 1);
    p = r; // pin = 1
    r = r && report_error_(b, referenceAttribute_1(b, l + 1));
    r = p && report_error_(b, consumeToken(b, IDENTIFIER_KEY)) && r;
    r = p && referenceAttribute_3(b, l + 1) && r;
    exit_section_(b, l, m, null, r, p, null);
    return r || p;
  }

  // LIST?
  private static boolean referenceAttribute_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "referenceAttribute_1")) return false;
    consumeToken(b, LIST);
    return true;
  }

  // (EQUALS emptyField)?
  private static boolean referenceAttribute_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "referenceAttribute_3")) return false;
    referenceAttribute_3_0(b, l + 1);
    return true;
  }

  // EQUALS emptyField
  private static boolean referenceAttribute_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "referenceAttribute_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, EQUALS);
    r = r && emptyField(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // RESOURCE_KEY attributeType IDENTIFIER_KEY
  static boolean resource(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "resource")) return false;
    if (!nextTokenIs(b, RESOURCE_KEY)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeToken(b, RESOURCE_KEY);
    p = r; // pin = 1
    r = r && report_error_(b, attributeType(b, l + 1));
    r = p && consumeToken(b, IDENTIFIER_KEY) && r;
    exit_section_(b, l, m, null, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // NEWLINE* header? NEWLINE+ (concept NEWLINE+)*
  static boolean root(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "root")) return false;
    if (!nextTokenIs(b, NEWLINE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = root_0(b, l + 1);
    r = r && root_1(b, l + 1);
    r = r && root_2(b, l + 1);
    r = r && root_3(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // NEWLINE*
  private static boolean root_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "root_0")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeToken(b, NEWLINE)) break;
      if (!empty_element_parsed_guard_(b, "root_0", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // header?
  private static boolean root_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "root_1")) return false;
    header(b, l + 1);
    return true;
  }

  // NEWLINE+
  private static boolean root_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "root_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, NEWLINE);
    int c = current_position_(b);
    while (r) {
      if (!consumeToken(b, NEWLINE)) break;
      if (!empty_element_parsed_guard_(b, "root_2", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // (concept NEWLINE+)*
  private static boolean root_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "root_3")) return false;
    int c = current_position_(b);
    while (true) {
      if (!root_3_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "root_3", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // concept NEWLINE+
  private static boolean root_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "root_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = concept(b, l + 1);
    r = r && root_3_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // NEWLINE+
  private static boolean root_3_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "root_3_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, NEWLINE);
    int c = current_position_(b);
    while (r) {
      if (!consumeToken(b, NEWLINE)) break;
      if (!empty_element_parsed_guard_(b, "root_3_0_1", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // subConcept | metaIdentifier parameters? (identifier parent?)? address?
  public static boolean signature(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "signature")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<signature>");
    r = subConcept(b, l + 1);
    if (!r) r = signature_1(b, l + 1);
    exit_section_(b, l, m, SIGNATURE, r, false, null);
    return r;
  }

  // metaIdentifier parameters? (identifier parent?)? address?
  private static boolean signature_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "signature_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = metaIdentifier(b, l + 1);
    r = r && signature_1_1(b, l + 1);
    r = r && signature_1_2(b, l + 1);
    r = r && signature_1_3(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // parameters?
  private static boolean signature_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "signature_1_1")) return false;
    parameters(b, l + 1);
    return true;
  }

  // (identifier parent?)?
  private static boolean signature_1_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "signature_1_2")) return false;
    signature_1_2_0(b, l + 1);
    return true;
  }

  // identifier parent?
  private static boolean signature_1_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "signature_1_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = identifier(b, l + 1);
    r = r && signature_1_2_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // parent?
  private static boolean signature_1_2_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "signature_1_2_0_1")) return false;
    parent(b, l + 1);
    return true;
  }

  // address?
  private static boolean signature_1_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "signature_1_3")) return false;
    address(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // STRING_TYPE  LIST?  IDENTIFIER_KEY (EQUALS (stringValue+  | emptyField))?
  static boolean stringAttribute(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "stringAttribute")) return false;
    if (!nextTokenIs(b, STRING_TYPE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeToken(b, STRING_TYPE);
    p = r; // pin = 1
    r = r && report_error_(b, stringAttribute_1(b, l + 1));
    r = p && report_error_(b, consumeToken(b, IDENTIFIER_KEY)) && r;
    r = p && stringAttribute_3(b, l + 1) && r;
    exit_section_(b, l, m, null, r, p, null);
    return r || p;
  }

  // LIST?
  private static boolean stringAttribute_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "stringAttribute_1")) return false;
    consumeToken(b, LIST);
    return true;
  }

  // (EQUALS (stringValue+  | emptyField))?
  private static boolean stringAttribute_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "stringAttribute_3")) return false;
    stringAttribute_3_0(b, l + 1);
    return true;
  }

  // EQUALS (stringValue+  | emptyField)
  private static boolean stringAttribute_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "stringAttribute_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, EQUALS);
    r = r && stringAttribute_3_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // stringValue+  | emptyField
  private static boolean stringAttribute_3_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "stringAttribute_3_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = stringAttribute_3_0_1_0(b, l + 1);
    if (!r) r = emptyField(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // stringValue+
  private static boolean stringAttribute_3_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "stringAttribute_3_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = stringValue(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!stringValue(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "stringAttribute_3_0_1_0", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // STRING_VALUE_KEY  | (NEWLINE? STRING_MULTILINE_VALUE_KEY)
  public static boolean stringValue(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "stringValue")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<string value>");
    r = consumeToken(b, STRING_VALUE_KEY);
    if (!r) r = stringValue_1(b, l + 1);
    exit_section_(b, l, m, STRING_VALUE, r, false, null);
    return r;
  }

  // NEWLINE? STRING_MULTILINE_VALUE_KEY
  private static boolean stringValue_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "stringValue_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = stringValue_1_0(b, l + 1);
    r = r && consumeToken(b, STRING_MULTILINE_VALUE_KEY);
    exit_section_(b, m, null, r);
    return r;
  }

  // NEWLINE?
  private static boolean stringValue_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "stringValue_1_0")) return false;
    consumeToken(b, NEWLINE);
    return true;
  }

  /* ********************************************************** */
  // SUB identifier
  static boolean subConcept(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "subConcept")) return false;
    if (!nextTokenIs(b, SUB)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeToken(b, SUB);
    p = r; // pin = 1
    r = r && identifier(b, l + 1);
    exit_section_(b, l, m, null, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // IDENTIFIER_KEY EQUALS varInitValue
  public static boolean varInit(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "varInit")) return false;
    if (!nextTokenIs(b, IDENTIFIER_KEY)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeTokens(b, 2, IDENTIFIER_KEY, EQUALS);
    p = r; // pin = 2
    r = r && varInitValue(b, l + 1);
    exit_section_(b, l, m, VAR_INIT, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // emptyField
  //                 | identifier+
  //                 | linkValue+
  //                 | stringValue+
  //                 | booleanValue+
  //                 | dateValue+
  //                 | naturalValue+ measure?
  //                 | integerValue+ measure?
  //                 | doubleValue+  measure?
  public static boolean varInitValue(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "varInitValue")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<var init value>");
    r = emptyField(b, l + 1);
    if (!r) r = varInitValue_1(b, l + 1);
    if (!r) r = varInitValue_2(b, l + 1);
    if (!r) r = varInitValue_3(b, l + 1);
    if (!r) r = varInitValue_4(b, l + 1);
    if (!r) r = varInitValue_5(b, l + 1);
    if (!r) r = varInitValue_6(b, l + 1);
    if (!r) r = varInitValue_7(b, l + 1);
    if (!r) r = varInitValue_8(b, l + 1);
    exit_section_(b, l, m, VAR_INIT_VALUE, r, false, null);
    return r;
  }

  // identifier+
  private static boolean varInitValue_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "varInitValue_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = identifier(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!identifier(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "varInitValue_1", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // linkValue+
  private static boolean varInitValue_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "varInitValue_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = linkValue(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!linkValue(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "varInitValue_2", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // stringValue+
  private static boolean varInitValue_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "varInitValue_3")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = stringValue(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!stringValue(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "varInitValue_3", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // booleanValue+
  private static boolean varInitValue_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "varInitValue_4")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = booleanValue(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!booleanValue(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "varInitValue_4", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // dateValue+
  private static boolean varInitValue_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "varInitValue_5")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = dateValue(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!dateValue(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "varInitValue_5", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // naturalValue+ measure?
  private static boolean varInitValue_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "varInitValue_6")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = varInitValue_6_0(b, l + 1);
    r = r && varInitValue_6_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // naturalValue+
  private static boolean varInitValue_6_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "varInitValue_6_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = naturalValue(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!naturalValue(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "varInitValue_6_0", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // measure?
  private static boolean varInitValue_6_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "varInitValue_6_1")) return false;
    measure(b, l + 1);
    return true;
  }

  // integerValue+ measure?
  private static boolean varInitValue_7(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "varInitValue_7")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = varInitValue_7_0(b, l + 1);
    r = r && varInitValue_7_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // integerValue+
  private static boolean varInitValue_7_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "varInitValue_7_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = integerValue(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!integerValue(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "varInitValue_7_0", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // measure?
  private static boolean varInitValue_7_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "varInitValue_7_1")) return false;
    measure(b, l + 1);
    return true;
  }

  // doubleValue+  measure?
  private static boolean varInitValue_8(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "varInitValue_8")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = varInitValue_8_0(b, l + 1);
    r = r && varInitValue_8_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // doubleValue+
  private static boolean varInitValue_8_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "varInitValue_8_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = doubleValue(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!doubleValue(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "varInitValue_8_0", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // measure?
  private static boolean varInitValue_8_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "varInitValue_8_1")) return false;
    measure(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // doc? VAR variableDeclaration annotationsAndFacets?
  public static boolean variable(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variable")) return false;
    if (!nextTokenIs(b, "<variable>", DOC_LINE, VAR)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, "<variable>");
    r = variable_0(b, l + 1);
    r = r && consumeToken(b, VAR);
    p = r; // pin = 2
    r = r && report_error_(b, variableDeclaration(b, l + 1));
    r = p && variable_3(b, l + 1) && r;
    exit_section_(b, l, m, VARIABLE, r, p, null);
    return r || p;
  }

  // doc?
  private static boolean variable_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variable_0")) return false;
    doc(b, l + 1);
    return true;
  }

  // annotationsAndFacets?
  private static boolean variable_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variable_3")) return false;
    annotationsAndFacets(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // naturalAttribute | integerAttribute | doubleAttribute | booleanAttribute | stringAttribute |
  // 		dateAttribute | resource | referenceAttribute | word
  static boolean variableDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variableDeclaration")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = naturalAttribute(b, l + 1);
    if (!r) r = integerAttribute(b, l + 1);
    if (!r) r = doubleAttribute(b, l + 1);
    if (!r) r = booleanAttribute(b, l + 1);
    if (!r) r = stringAttribute(b, l + 1);
    if (!r) r = dateAttribute(b, l + 1);
    if (!r) r = resource(b, l + 1);
    if (!r) r = referenceAttribute(b, l + 1);
    if (!r) r = word(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // WORD_KEY LIST? IDENTIFIER_KEY NEW_LINE_INDENT (IDENTIFIER_KEY STAR? NEWLINE)+ DEDENT
  public static boolean word(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "word")) return false;
    if (!nextTokenIs(b, WORD_KEY)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeToken(b, WORD_KEY);
    p = r; // pin = 1
    r = r && report_error_(b, word_1(b, l + 1));
    r = p && report_error_(b, consumeTokens(b, -1, IDENTIFIER_KEY, NEW_LINE_INDENT)) && r;
    r = p && report_error_(b, word_4(b, l + 1)) && r;
    r = p && consumeToken(b, DEDENT) && r;
    exit_section_(b, l, m, WORD, r, p, null);
    return r || p;
  }

  // LIST?
  private static boolean word_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "word_1")) return false;
    consumeToken(b, LIST);
    return true;
  }

  // (IDENTIFIER_KEY STAR? NEWLINE)+
  private static boolean word_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "word_4")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = word_4_0(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!word_4_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "word_4", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // IDENTIFIER_KEY STAR? NEWLINE
  private static boolean word_4_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "word_4_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER_KEY);
    r = r && word_4_0_1(b, l + 1);
    r = r && consumeToken(b, NEWLINE);
    exit_section_(b, m, null, r);
    return r;
  }

  // STAR?
  private static boolean word_4_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "word_4_0_1")) return false;
    consumeToken(b, STAR);
    return true;
  }

}
