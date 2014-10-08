// This is a generated file. Not intended for manual editing.
package siani.tara.intellij.lang.parser;

import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import com.intellij.lang.PsiParser;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.psi.tree.IElementType;

import static siani.tara.intellij.lang.parser.TaraParserUtil.*;
import static siani.tara.intellij.lang.psi.TaraTypes.*;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class TaraParser implements PsiParser {

	public static final Logger LOG_ = Logger.getInstance("siani.tara.intellij.lang.parser.TaraParser");

	public ASTNode parse(IElementType root_, PsiBuilder builder_) {
		boolean result_;
		builder_ = adapt_builder_(root_, builder_, this, null);
		Marker marker_ = enter_section_(builder_, 0, _COLLAPSE_, null);
		if (root_ == AN_IMPORT) {
			result_ = anImport(builder_, 0);
		} else if (root_ == ANNOTATIONS) {
			result_ = annotations(builder_, 0);
		} else if (root_ == ANNOTATIONS_AND_FACETS) {
			result_ = annotationsAndFacets(builder_, 0);
		} else if (root_ == ATTRIBUTE) {
			result_ = attribute(builder_, 0);
		} else if (root_ == ATTRIBUTE_TYPE) {
			result_ = attributeType(builder_, 0);
		} else if (root_ == BODY) {
			result_ = body(builder_, 0);
		} else if (root_ == BOOLEAN_VALUE) {
			result_ = booleanValue(builder_, 0);
		} else if (root_ == BOX) {
			result_ = box(builder_, 0);
		} else if (root_ == CONCEPT) {
			result_ = concept(builder_, 0);
		} else if (root_ == CONCEPT_REFERENCE) {
			result_ = conceptReference(builder_, 0);
		} else if (root_ == COORDINATE_VALUE) {
			result_ = coordinateValue(builder_, 0);
		} else if (root_ == DATE_VALUE) {
			result_ = dateValue(builder_, 0);
		} else if (root_ == DOC) {
			result_ = doc(builder_, 0);
		} else if (root_ == DOUBLE_VALUE) {
			result_ = doubleValue(builder_, 0);
		} else if (root_ == EMPTY) {
			result_ = empty(builder_, 0);
		} else if (root_ == EMPTY_FIELD) {
			result_ = emptyField(builder_, 0);
		} else if (root_ == EXPLICIT_PARAMETER) {
			result_ = explicitParameter(builder_, 0);
		} else if (root_ == FACET_APPLY) {
			result_ = facetApply(builder_, 0);
		} else if (root_ == FACET_TARGET) {
			result_ = facetTarget(builder_, 0);
		} else if (root_ == HEADER) {
			result_ = header(builder_, 0);
		} else if (root_ == HEADER_REFERENCE) {
			result_ = headerReference(builder_, 0);
		} else if (root_ == IDENTIFIER) {
			result_ = identifier(builder_, 0);
		} else if (root_ == IDENTIFIER_REFERENCE) {
			result_ = identifierReference(builder_, 0);
		} else if (root_ == IMPLICIT_PARAMETER) {
			result_ = implicitParameter(builder_, 0);
		} else if (root_ == INTEGER_VALUE) {
			result_ = integerValue(builder_, 0);
		} else if (root_ == MEASURE) {
			result_ = measure(builder_, 0);
		} else if (root_ == META_IDENTIFIER) {
			result_ = metaIdentifier(builder_, 0);
		} else if (root_ == META_WORD) {
			result_ = metaWord(builder_, 0);
		} else if (root_ == NATURAL_VALUE) {
			result_ = naturalValue(builder_, 0);
		} else if (root_ == PARAMETER_VALUE) {
			result_ = parameterValue(builder_, 0);
		} else if (root_ == PARAMETERS) {
			result_ = parameters(builder_, 0);
		} else if (root_ == PORT_VALUE) {
			result_ = portValue(builder_, 0);
		} else if (root_ == SIGNATURE) {
			result_ = signature(builder_, 0);
		} else if (root_ == STRING_VALUE) {
			result_ = stringValue(builder_, 0);
		} else if (root_ == VAR_INIT) {
			result_ = varInit(builder_, 0);
		} else if (root_ == WORD) {
			result_ = word(builder_, 0);
		} else {
			result_ = parse_root_(root_, builder_, 0);
		}
		exit_section_(builder_, 0, marker_, root_, result_, true, TRUE_CONDITION);
		return builder_.getTreeBuilt();
	}

	protected boolean parse_root_(final IElementType root_, final PsiBuilder builder_, final int level_) {
		return root(builder_, level_ + 1);
	}

	/* ********************************************************** */
	// NEWLINE+ USE_KEY headerReference (AS IDENTIFIER_KEY)?
	public static boolean anImport(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "anImport")) return false;
		if (!nextTokenIs(builder_, NEWLINE)) return false;
		boolean result_;
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
		boolean result_;
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

	// (AS IDENTIFIER_KEY)?
	private static boolean anImport_3(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "anImport_3")) return false;
		anImport_3_0(builder_, level_ + 1);
		return true;
	}

	// AS IDENTIFIER_KEY
	private static boolean anImport_3_0(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "anImport_3_0")) return false;
		boolean result_;
		Marker marker_ = enter_section_(builder_);
		result_ = consumeTokens(builder_, 0, AS, IDENTIFIER_KEY);
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	/* ********************************************************** */
	// (PRIVATE | TERMINAL | SINGLE | REQUIRED | NAMED | COMPONENT | FACET | INTENTION | PROPERTY | UNIVERSAL)+
	public static boolean annotations(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "annotations")) return false;
		boolean result_;
		Marker marker_ = enter_section_(builder_, level_, _NONE_, "<annotations>");
		result_ = annotations_0(builder_, level_ + 1);
		int pos_ = current_position_(builder_);
		while (result_) {
			if (!annotations_0(builder_, level_ + 1)) break;
			if (!empty_element_parsed_guard_(builder_, "annotations", pos_)) break;
			pos_ = current_position_(builder_);
		}
		exit_section_(builder_, level_, marker_, ANNOTATIONS, result_, false, null);
		return result_;
	}

	// PRIVATE | TERMINAL | SINGLE | REQUIRED | NAMED | COMPONENT | FACET | INTENTION | PROPERTY | UNIVERSAL
	private static boolean annotations_0(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "annotations_0")) return false;
		boolean result_;
		Marker marker_ = enter_section_(builder_);
		result_ = consumeToken(builder_, PRIVATE);
		if (!result_) result_ = consumeToken(builder_, TERMINAL);
		if (!result_) result_ = consumeToken(builder_, SINGLE);
		if (!result_) result_ = consumeToken(builder_, REQUIRED);
		if (!result_) result_ = consumeToken(builder_, NAMED);
		if (!result_) result_ = consumeToken(builder_, COMPONENT);
		if (!result_) result_ = consumeToken(builder_, FACET);
		if (!result_) result_ = consumeToken(builder_, INTENTION);
		if (!result_) result_ = consumeToken(builder_, PROPERTY);
		if (!result_) result_ = consumeToken(builder_, UNIVERSAL);
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	/* ********************************************************** */
	// IS (annotations+ | facetApply)
	public static boolean annotationsAndFacets(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "annotationsAndFacets")) return false;
		if (!nextTokenIs(builder_, IS)) return false;
		boolean result_;
		boolean pinned_;
		Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
		result_ = consumeToken(builder_, IS);
		pinned_ = result_; // pin = 1
		result_ = result_ && annotationsAndFacets_1(builder_, level_ + 1);
		exit_section_(builder_, level_, marker_, ANNOTATIONS_AND_FACETS, result_, pinned_, null);
		return result_ || pinned_;
	}

	// annotations+ | facetApply
	private static boolean annotationsAndFacets_1(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "annotationsAndFacets_1")) return false;
		boolean result_;
		Marker marker_ = enter_section_(builder_);
		result_ = annotationsAndFacets_1_0(builder_, level_ + 1);
		if (!result_) result_ = facetApply(builder_, level_ + 1);
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	// annotations+
	private static boolean annotationsAndFacets_1_0(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "annotationsAndFacets_1_0")) return false;
		boolean result_;
		Marker marker_ = enter_section_(builder_);
		result_ = annotations(builder_, level_ + 1);
		int pos_ = current_position_(builder_);
		while (result_) {
			if (!annotations(builder_, level_ + 1)) break;
			if (!empty_element_parsed_guard_(builder_, "annotationsAndFacets_1_0", pos_)) break;
			pos_ = current_position_(builder_);
		}
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	/* ********************************************************** */
	// doc? VAR (refAttribute | naturalAttribute | integerAttribute | doubleAttribute | booleanAttribute | stringAttribute |
	// 			dateAttribute | coordinateAttribute | resource | referenceAttribute | word) annotationsAndFacets?
	public static boolean attribute(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "attribute")) return false;
		if (!nextTokenIs(builder_, "<attribute>", DOC_LINE, VAR)) return false;
		boolean result_;
		boolean pinned_;
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

	// refAttribute | naturalAttribute | integerAttribute | doubleAttribute | booleanAttribute | stringAttribute |
	// 			dateAttribute | coordinateAttribute | resource | referenceAttribute | word
	private static boolean attribute_2(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "attribute_2")) return false;
		boolean result_;
		Marker marker_ = enter_section_(builder_);
		result_ = refAttribute(builder_, level_ + 1);
		if (!result_) result_ = naturalAttribute(builder_, level_ + 1);
		if (!result_) result_ = integerAttribute(builder_, level_ + 1);
		if (!result_) result_ = doubleAttribute(builder_, level_ + 1);
		if (!result_) result_ = booleanAttribute(builder_, level_ + 1);
		if (!result_) result_ = stringAttribute(builder_, level_ + 1);
		if (!result_) result_ = dateAttribute(builder_, level_ + 1);
		if (!result_) result_ = coordinateAttribute(builder_, level_ + 1);
		if (!result_) result_ = resource(builder_, level_ + 1);
		if (!result_) result_ = referenceAttribute(builder_, level_ + 1);
		if (!result_) result_ = word(builder_, level_ + 1);
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	// annotationsAndFacets?
	private static boolean attribute_3(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "attribute_3")) return false;
		annotationsAndFacets(builder_, level_ + 1);
		return true;
	}

	/* ********************************************************** */
	// COLON IDENTIFIER_KEY
	public static boolean attributeType(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "attributeType")) return false;
		if (!nextTokenIs(builder_, COLON)) return false;
		boolean result_;
		Marker marker_ = enter_section_(builder_);
		result_ = consumeTokens(builder_, 0, COLON, IDENTIFIER_KEY);
		exit_section_(builder_, marker_, ATTRIBUTE_TYPE, result_);
		return result_;
	}

	/* ********************************************************** */
	// (NEW_LINE_INDENT | INLINE) (conceptConstituents NEWLINE+)+ DEDENT
	public static boolean body(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "body")) return false;
		if (!nextTokenIs(builder_, "<body>", INLINE, NEW_LINE_INDENT)) return false;
		boolean result_;
		boolean pinned_;
		Marker marker_ = enter_section_(builder_, level_, _NONE_, "<body>");
		result_ = body_0(builder_, level_ + 1);
		pinned_ = result_; // pin = 1
		result_ = result_ && report_error_(builder_, body_1(builder_, level_ + 1));
		result_ = pinned_ && consumeToken(builder_, DEDENT) && result_;
		exit_section_(builder_, level_, marker_, BODY, result_, pinned_, null);
		return result_ || pinned_;
	}

	// NEW_LINE_INDENT | INLINE
	private static boolean body_0(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "body_0")) return false;
		boolean result_;
		Marker marker_ = enter_section_(builder_);
		result_ = consumeToken(builder_, NEW_LINE_INDENT);
		if (!result_) result_ = consumeToken(builder_, INLINE);
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	// (conceptConstituents NEWLINE+)+
	private static boolean body_1(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "body_1")) return false;
		boolean result_;
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
		boolean result_;
		Marker marker_ = enter_section_(builder_);
		result_ = conceptConstituents(builder_, level_ + 1);
		result_ = result_ && body_1_0_1(builder_, level_ + 1);
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	// NEWLINE+
	private static boolean body_1_0_1(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "body_1_0_1")) return false;
		boolean result_;
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
		boolean result_;
		boolean pinned_;
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
		boolean result_;
		Marker marker_ = enter_section_(builder_);
		result_ = consumeToken(builder_, EQUALS);
		result_ = result_ && booleanAttribute_3_0_1(builder_, level_ + 1);
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	// booleanValue | emptyField
	private static boolean booleanAttribute_3_0_1(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "booleanAttribute_3_0_1")) return false;
		boolean result_;
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
		boolean result_;
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
		boolean result_;
		Marker marker_ = enter_section_(builder_);
		result_ = consumeToken(builder_, BOX_KEY);
		result_ = result_ && headerReference(builder_, level_ + 1);
		exit_section_(builder_, marker_, BOX, result_);
		return result_;
	}

	/* ********************************************************** */
	// doc? signature annotationsAndFacets? body?
	public static boolean concept(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "concept")) return false;
		boolean result_;
		boolean pinned_;
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

	// annotationsAndFacets?
	private static boolean concept_2(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "concept_2")) return false;
		annotationsAndFacets(builder_, level_ + 1);
		return true;
	}

	// body?
	private static boolean concept_3(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "concept_3")) return false;
		body(builder_, level_ + 1);
		return true;
	}

	/* ********************************************************** */
	// varInit | attribute | concept | annotationsAndFacets | facetTarget | conceptReference
	static boolean conceptConstituents(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "conceptConstituents")) return false;
		boolean result_;
		Marker marker_ = enter_section_(builder_);
		result_ = varInit(builder_, level_ + 1);
		if (!result_) result_ = attribute(builder_, level_ + 1);
		if (!result_) result_ = concept(builder_, level_ + 1);
		if (!result_) result_ = annotationsAndFacets(builder_, level_ + 1);
		if (!result_) result_ = facetTarget(builder_, level_ + 1);
		if (!result_) result_ = conceptReference(builder_, level_ + 1);
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	/* ********************************************************** */
	// doc? HAS identifierReference identifier?
	public static boolean conceptReference(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "conceptReference")) return false;
		if (!nextTokenIs(builder_, "<concept reference>", DOC_LINE, HAS)) return false;
		boolean result_;
		boolean pinned_;
		Marker marker_ = enter_section_(builder_, level_, _NONE_, "<concept reference>");
		result_ = conceptReference_0(builder_, level_ + 1);
		result_ = result_ && consumeToken(builder_, HAS);
		pinned_ = result_; // pin = 2
		result_ = result_ && report_error_(builder_, identifierReference(builder_, level_ + 1));
		result_ = pinned_ && conceptReference_3(builder_, level_ + 1) && result_;
		exit_section_(builder_, level_, marker_, CONCEPT_REFERENCE, result_, pinned_, null);
		return result_ || pinned_;
	}

	// doc?
	private static boolean conceptReference_0(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "conceptReference_0")) return false;
		doc(builder_, level_ + 1);
		return true;
	}

	// identifier?
	private static boolean conceptReference_3(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "conceptReference_3")) return false;
		identifier(builder_, level_ + 1);
		return true;
	}

	/* ********************************************************** */
	// COORDINATE_TYPE  LIST? IDENTIFIER_KEY (EQUALS (coordinateValue | emptyField))?
	static boolean coordinateAttribute(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "coordinateAttribute")) return false;
		if (!nextTokenIs(builder_, COORDINATE_TYPE)) return false;
		boolean result_;
		boolean pinned_;
		Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
		result_ = consumeToken(builder_, COORDINATE_TYPE);
		pinned_ = result_; // pin = 1
		result_ = result_ && report_error_(builder_, coordinateAttribute_1(builder_, level_ + 1));
		result_ = pinned_ && report_error_(builder_, consumeToken(builder_, IDENTIFIER_KEY)) && result_;
		result_ = pinned_ && coordinateAttribute_3(builder_, level_ + 1) && result_;
		exit_section_(builder_, level_, marker_, null, result_, pinned_, null);
		return result_ || pinned_;
	}

	// LIST?
	private static boolean coordinateAttribute_1(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "coordinateAttribute_1")) return false;
		consumeToken(builder_, LIST);
		return true;
	}

	// (EQUALS (coordinateValue | emptyField))?
	private static boolean coordinateAttribute_3(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "coordinateAttribute_3")) return false;
		coordinateAttribute_3_0(builder_, level_ + 1);
		return true;
	}

	// EQUALS (coordinateValue | emptyField)
	private static boolean coordinateAttribute_3_0(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "coordinateAttribute_3_0")) return false;
		boolean result_;
		Marker marker_ = enter_section_(builder_);
		result_ = consumeToken(builder_, EQUALS);
		result_ = result_ && coordinateAttribute_3_0_1(builder_, level_ + 1);
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	// coordinateValue | emptyField
	private static boolean coordinateAttribute_3_0_1(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "coordinateAttribute_3_0_1")) return false;
		boolean result_;
		Marker marker_ = enter_section_(builder_);
		result_ = coordinateValue(builder_, level_ + 1);
		if (!result_) result_ = emptyField(builder_, level_ + 1);
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	/* ********************************************************** */
	// COORDINATE_VALUE_KEY
	public static boolean coordinateValue(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "coordinateValue")) return false;
		if (!nextTokenIs(builder_, COORDINATE_VALUE_KEY)) return false;
		boolean result_;
		Marker marker_ = enter_section_(builder_);
		result_ = consumeToken(builder_, COORDINATE_VALUE_KEY);
		exit_section_(builder_, marker_, COORDINATE_VALUE, result_);
		return result_;
	}

	/* ********************************************************** */
	// DATE_TYPE    LIST?  IDENTIFIER_KEY (EQUALS (dateValue | emptyField))?
	static boolean dateAttribute(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "dateAttribute")) return false;
		if (!nextTokenIs(builder_, DATE_TYPE)) return false;
		boolean result_;
		boolean pinned_;
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

	// (EQUALS (dateValue | emptyField))?
	private static boolean dateAttribute_3(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "dateAttribute_3")) return false;
		dateAttribute_3_0(builder_, level_ + 1);
		return true;
	}

	// EQUALS (dateValue | emptyField)
	private static boolean dateAttribute_3_0(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "dateAttribute_3_0")) return false;
		boolean result_;
		Marker marker_ = enter_section_(builder_);
		result_ = consumeToken(builder_, EQUALS);
		result_ = result_ && dateAttribute_3_0_1(builder_, level_ + 1);
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	// dateValue | emptyField
	private static boolean dateAttribute_3_0_1(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "dateAttribute_3_0_1")) return false;
		boolean result_;
		Marker marker_ = enter_section_(builder_);
		result_ = dateValue(builder_, level_ + 1);
		if (!result_) result_ = emptyField(builder_, level_ + 1);
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	/* ********************************************************** */
	// DATE_VALUE_KEY
	public static boolean dateValue(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "dateValue")) return false;
		if (!nextTokenIs(builder_, DATE_VALUE_KEY)) return false;
		boolean result_;
		Marker marker_ = enter_section_(builder_);
		result_ = consumeToken(builder_, DATE_VALUE_KEY);
		exit_section_(builder_, marker_, DATE_VALUE, result_);
		return result_;
	}

	/* ********************************************************** */
	// DOC_LINE+
	public static boolean doc(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "doc")) return false;
		if (!nextTokenIs(builder_, DOC_LINE)) return false;
		boolean result_;
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
	// DOUBLE_TYPE  attributeType? LIST?  IDENTIFIER_KEY (EQUALS (doubleValue  measure? | emptyField))?
	static boolean doubleAttribute(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "doubleAttribute")) return false;
		if (!nextTokenIs(builder_, DOUBLE_TYPE)) return false;
		boolean result_;
		boolean pinned_;
		Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
		result_ = consumeToken(builder_, DOUBLE_TYPE);
		pinned_ = result_; // pin = 1
		result_ = result_ && report_error_(builder_, doubleAttribute_1(builder_, level_ + 1));
		result_ = pinned_ && report_error_(builder_, doubleAttribute_2(builder_, level_ + 1)) && result_;
		result_ = pinned_ && report_error_(builder_, consumeToken(builder_, IDENTIFIER_KEY)) && result_;
		result_ = pinned_ && doubleAttribute_4(builder_, level_ + 1) && result_;
		exit_section_(builder_, level_, marker_, null, result_, pinned_, null);
		return result_ || pinned_;
	}

	// attributeType?
	private static boolean doubleAttribute_1(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "doubleAttribute_1")) return false;
		attributeType(builder_, level_ + 1);
		return true;
	}

	// LIST?
	private static boolean doubleAttribute_2(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "doubleAttribute_2")) return false;
		consumeToken(builder_, LIST);
		return true;
	}

	// (EQUALS (doubleValue  measure? | emptyField))?
	private static boolean doubleAttribute_4(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "doubleAttribute_4")) return false;
		doubleAttribute_4_0(builder_, level_ + 1);
		return true;
	}

	// EQUALS (doubleValue  measure? | emptyField)
	private static boolean doubleAttribute_4_0(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "doubleAttribute_4_0")) return false;
		boolean result_;
		Marker marker_ = enter_section_(builder_);
		result_ = consumeToken(builder_, EQUALS);
		result_ = result_ && doubleAttribute_4_0_1(builder_, level_ + 1);
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	// doubleValue  measure? | emptyField
	private static boolean doubleAttribute_4_0_1(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "doubleAttribute_4_0_1")) return false;
		boolean result_;
		Marker marker_ = enter_section_(builder_);
		result_ = doubleAttribute_4_0_1_0(builder_, level_ + 1);
		if (!result_) result_ = emptyField(builder_, level_ + 1);
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	// doubleValue  measure?
	private static boolean doubleAttribute_4_0_1_0(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "doubleAttribute_4_0_1_0")) return false;
		boolean result_;
		Marker marker_ = enter_section_(builder_);
		result_ = doubleValue(builder_, level_ + 1);
		result_ = result_ && doubleAttribute_4_0_1_0_1(builder_, level_ + 1);
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	// measure?
	private static boolean doubleAttribute_4_0_1_0_1(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "doubleAttribute_4_0_1_0_1")) return false;
		measure(builder_, level_ + 1);
		return true;
	}

	/* ********************************************************** */
	// NATURAL_VALUE_KEY | NEGATIVE_VALUE_KEY | DOUBLE_VALUE_KEY
	public static boolean doubleValue(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "doubleValue")) return false;
		boolean result_;
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
		boolean result_;
		Marker marker_ = enter_section_(builder_);
		result_ = consumeToken(builder_, EMPTY_REF);
		exit_section_(builder_, marker_, EMPTY_FIELD, result_);
		return result_;
	}

	/* ********************************************************** */
	// identifier EQUALS parameterValue
	public static boolean explicitParameter(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "explicitParameter")) return false;
		if (!nextTokenIs(builder_, IDENTIFIER_KEY)) return false;
		boolean result_;
		boolean pinned_;
		Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
		result_ = identifier(builder_, level_ + 1);
		result_ = result_ && consumeToken(builder_, EQUALS);
		pinned_ = result_; // pin = 2
		result_ = result_ && parameterValue(builder_, level_ + 1);
		exit_section_(builder_, level_, marker_, EXPLICIT_PARAMETER, result_, pinned_, null);
		return result_ || pinned_;
	}

	/* ********************************************************** */
	// explicitParameter (COMMA explicitParameter)*
	static boolean explicitParameters(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "explicitParameters")) return false;
		if (!nextTokenIs(builder_, IDENTIFIER_KEY)) return false;
		boolean result_;
		Marker marker_ = enter_section_(builder_);
		result_ = explicitParameter(builder_, level_ + 1);
		result_ = result_ && explicitParameters_1(builder_, level_ + 1);
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	// (COMMA explicitParameter)*
	private static boolean explicitParameters_1(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "explicitParameters_1")) return false;
		int pos_ = current_position_(builder_);
		while (true) {
			if (!explicitParameters_1_0(builder_, level_ + 1)) break;
			if (!empty_element_parsed_guard_(builder_, "explicitParameters_1", pos_)) break;
			pos_ = current_position_(builder_);
		}
		return true;
	}

	// COMMA explicitParameter
	private static boolean explicitParameters_1_0(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "explicitParameters_1_0")) return false;
		boolean result_;
		Marker marker_ = enter_section_(builder_);
		result_ = consumeToken(builder_, COMMA);
		result_ = result_ && explicitParameter(builder_, level_ + 1);
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	/* ********************************************************** */
	// metaIdentifier parameters? (WITH metaIdentifier)?
	public static boolean facetApply(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "facetApply")) return false;
		if (!nextTokenIs(builder_, "<facet apply>", IDENTIFIER_KEY, METAIDENTIFIER_KEY)) return false;
		boolean result_;
		Marker marker_ = enter_section_(builder_, level_, _NONE_, "<facet apply>");
		result_ = metaIdentifier(builder_, level_ + 1);
		result_ = result_ && facetApply_1(builder_, level_ + 1);
		result_ = result_ && facetApply_2(builder_, level_ + 1);
		exit_section_(builder_, level_, marker_, FACET_APPLY, result_, false, null);
		return result_;
	}

	// parameters?
	private static boolean facetApply_1(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "facetApply_1")) return false;
		parameters(builder_, level_ + 1);
		return true;
	}

	// (WITH metaIdentifier)?
	private static boolean facetApply_2(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "facetApply_2")) return false;
		facetApply_2_0(builder_, level_ + 1);
		return true;
	}

	// WITH metaIdentifier
	private static boolean facetApply_2_0(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "facetApply_2_0")) return false;
		boolean result_;
		Marker marker_ = enter_section_(builder_);
		result_ = consumeToken(builder_, WITH);
		result_ = result_ && metaIdentifier(builder_, level_ + 1);
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	/* ********************************************************** */
	// ON identifierReference ALWAYS? body?
	public static boolean facetTarget(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "facetTarget")) return false;
		if (!nextTokenIs(builder_, ON)) return false;
		boolean result_;
		boolean pinned_;
		Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
		result_ = consumeToken(builder_, ON);
		pinned_ = result_; // pin = 1
		result_ = result_ && report_error_(builder_, identifierReference(builder_, level_ + 1));
		result_ = pinned_ && report_error_(builder_, facetTarget_2(builder_, level_ + 1)) && result_;
		result_ = pinned_ && facetTarget_3(builder_, level_ + 1) && result_;
		exit_section_(builder_, level_, marker_, FACET_TARGET, result_, pinned_, null);
		return result_ || pinned_;
	}

	// ALWAYS?
	private static boolean facetTarget_2(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "facetTarget_2")) return false;
		consumeToken(builder_, ALWAYS);
		return true;
	}

	// body?
	private static boolean facetTarget_3(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "facetTarget_3")) return false;
		body(builder_, level_ + 1);
		return true;
	}

	/* ********************************************************** */
	// box?  imports?
	public static boolean header(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "header")) return false;
		boolean result_;
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
		boolean result_;
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
		boolean result_;
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
		boolean result_;
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
		boolean result_;
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
	// parameterValue
	public static boolean implicitParameter(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "implicitParameter")) return false;
		boolean result_;
		Marker marker_ = enter_section_(builder_, level_, _NONE_, "<implicit parameter>");
		result_ = parameterValue(builder_, level_ + 1);
		exit_section_(builder_, level_, marker_, IMPLICIT_PARAMETER, result_, false, null);
		return result_;
	}

	/* ********************************************************** */
	// implicitParameter (COMMA implicitParameter)*
	static boolean implicitParameters(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "implicitParameters")) return false;
		boolean result_;
		boolean pinned_;
		Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
		result_ = implicitParameter(builder_, level_ + 1);
		pinned_ = result_; // pin = 1
		result_ = result_ && implicitParameters_1(builder_, level_ + 1);
		exit_section_(builder_, level_, marker_, null, result_, pinned_, null);
		return result_ || pinned_;
	}

	// (COMMA implicitParameter)*
	private static boolean implicitParameters_1(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "implicitParameters_1")) return false;
		int pos_ = current_position_(builder_);
		while (true) {
			if (!implicitParameters_1_0(builder_, level_ + 1)) break;
			if (!empty_element_parsed_guard_(builder_, "implicitParameters_1", pos_)) break;
			pos_ = current_position_(builder_);
		}
		return true;
	}

	// COMMA implicitParameter
	private static boolean implicitParameters_1_0(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "implicitParameters_1_0")) return false;
		boolean result_;
		Marker marker_ = enter_section_(builder_);
		result_ = consumeToken(builder_, COMMA);
		result_ = result_ && implicitParameter(builder_, level_ + 1);
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	/* ********************************************************** */
	// anImport+
	static boolean imports(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "imports")) return false;
		if (!nextTokenIs(builder_, NEWLINE)) return false;
		boolean result_;
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
	// INT_TYPE     attributeType? LIST?  IDENTIFIER_KEY (EQUALS (integerValue measure? | emptyField))?
	static boolean integerAttribute(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "integerAttribute")) return false;
		if (!nextTokenIs(builder_, INT_TYPE)) return false;
		boolean result_;
		boolean pinned_;
		Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
		result_ = consumeToken(builder_, INT_TYPE);
		pinned_ = result_; // pin = 1
		result_ = result_ && report_error_(builder_, integerAttribute_1(builder_, level_ + 1));
		result_ = pinned_ && report_error_(builder_, integerAttribute_2(builder_, level_ + 1)) && result_;
		result_ = pinned_ && report_error_(builder_, consumeToken(builder_, IDENTIFIER_KEY)) && result_;
		result_ = pinned_ && integerAttribute_4(builder_, level_ + 1) && result_;
		exit_section_(builder_, level_, marker_, null, result_, pinned_, null);
		return result_ || pinned_;
	}

	// attributeType?
	private static boolean integerAttribute_1(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "integerAttribute_1")) return false;
		attributeType(builder_, level_ + 1);
		return true;
	}

	// LIST?
	private static boolean integerAttribute_2(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "integerAttribute_2")) return false;
		consumeToken(builder_, LIST);
		return true;
	}

	// (EQUALS (integerValue measure? | emptyField))?
	private static boolean integerAttribute_4(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "integerAttribute_4")) return false;
		integerAttribute_4_0(builder_, level_ + 1);
		return true;
	}

	// EQUALS (integerValue measure? | emptyField)
	private static boolean integerAttribute_4_0(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "integerAttribute_4_0")) return false;
		boolean result_;
		Marker marker_ = enter_section_(builder_);
		result_ = consumeToken(builder_, EQUALS);
		result_ = result_ && integerAttribute_4_0_1(builder_, level_ + 1);
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	// integerValue measure? | emptyField
	private static boolean integerAttribute_4_0_1(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "integerAttribute_4_0_1")) return false;
		boolean result_;
		Marker marker_ = enter_section_(builder_);
		result_ = integerAttribute_4_0_1_0(builder_, level_ + 1);
		if (!result_) result_ = emptyField(builder_, level_ + 1);
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	// integerValue measure?
	private static boolean integerAttribute_4_0_1_0(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "integerAttribute_4_0_1_0")) return false;
		boolean result_;
		Marker marker_ = enter_section_(builder_);
		result_ = integerValue(builder_, level_ + 1);
		result_ = result_ && integerAttribute_4_0_1_0_1(builder_, level_ + 1);
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	// measure?
	private static boolean integerAttribute_4_0_1_0_1(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "integerAttribute_4_0_1_0_1")) return false;
		measure(builder_, level_ + 1);
		return true;
	}

	/* ********************************************************** */
	// NATURAL_VALUE_KEY | NEGATIVE_VALUE_KEY
	public static boolean integerValue(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "integerValue")) return false;
		if (!nextTokenIs(builder_, "<integer value>", NATURAL_VALUE_KEY, NEGATIVE_VALUE_KEY)) return false;
		boolean result_;
		Marker marker_ = enter_section_(builder_, level_, _NONE_, "<integer value>");
		result_ = consumeToken(builder_, NATURAL_VALUE_KEY);
		if (!result_) result_ = consumeToken(builder_, NEGATIVE_VALUE_KEY);
		exit_section_(builder_, level_, marker_, INTEGER_VALUE, result_, false, null);
		return result_;
	}

	/* ********************************************************** */
	// IDENTIFIER_KEY | DOLLAR | EURO | PERCENTAGE | GRADE
	public static boolean measure(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "measure")) return false;
		boolean result_;
		Marker marker_ = enter_section_(builder_, level_, _NONE_, "<measure>");
		result_ = consumeToken(builder_, IDENTIFIER_KEY);
		if (!result_) result_ = consumeToken(builder_, DOLLAR);
		if (!result_) result_ = consumeToken(builder_, EURO);
		if (!result_) result_ = consumeToken(builder_, PERCENTAGE);
		if (!result_) result_ = consumeToken(builder_, GRADE);
		exit_section_(builder_, level_, marker_, MEASURE, result_, false, null);
		return result_;
	}

	/* ********************************************************** */
	// METAIDENTIFIER_KEY | IDENTIFIER_KEY
	public static boolean metaIdentifier(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "metaIdentifier")) return false;
		if (!nextTokenIs(builder_, "<meta identifier>", IDENTIFIER_KEY, METAIDENTIFIER_KEY)) return false;
		boolean result_;
		Marker marker_ = enter_section_(builder_, level_, _NONE_, "<meta identifier>");
		result_ = consumeToken(builder_, METAIDENTIFIER_KEY);
		if (!result_) result_ = consumeToken(builder_, IDENTIFIER_KEY);
		exit_section_(builder_, level_, marker_, META_IDENTIFIER, result_, false, null);
		return result_;
	}

	/* ********************************************************** */
	// metaIdentifier metaWordNames*
	public static boolean metaWord(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "metaWord")) return false;
		if (!nextTokenIs(builder_, "<meta word>", IDENTIFIER_KEY, METAIDENTIFIER_KEY)) return false;
		boolean result_;
		boolean pinned_;
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
		boolean result_;
		boolean pinned_;
		Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
		result_ = consumeToken(builder_, DOT);
		pinned_ = result_; // pin = 1
		result_ = result_ && identifier(builder_, level_ + 1);
		exit_section_(builder_, level_, marker_, null, result_, pinned_, null);
		return result_ || pinned_;
	}

	/* ********************************************************** */
	// NATURAL_TYPE attributeType? LIST?  IDENTIFIER_KEY (EQUALS (naturalValue measure? | emptyField))?
	static boolean naturalAttribute(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "naturalAttribute")) return false;
		if (!nextTokenIs(builder_, NATURAL_TYPE)) return false;
		boolean result_;
		boolean pinned_;
		Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
		result_ = consumeToken(builder_, NATURAL_TYPE);
		pinned_ = result_; // pin = 1
		result_ = result_ && report_error_(builder_, naturalAttribute_1(builder_, level_ + 1));
		result_ = pinned_ && report_error_(builder_, naturalAttribute_2(builder_, level_ + 1)) && result_;
		result_ = pinned_ && report_error_(builder_, consumeToken(builder_, IDENTIFIER_KEY)) && result_;
		result_ = pinned_ && naturalAttribute_4(builder_, level_ + 1) && result_;
		exit_section_(builder_, level_, marker_, null, result_, pinned_, null);
		return result_ || pinned_;
	}

	// attributeType?
	private static boolean naturalAttribute_1(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "naturalAttribute_1")) return false;
		attributeType(builder_, level_ + 1);
		return true;
	}

	// LIST?
	private static boolean naturalAttribute_2(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "naturalAttribute_2")) return false;
		consumeToken(builder_, LIST);
		return true;
	}

	// (EQUALS (naturalValue measure? | emptyField))?
	private static boolean naturalAttribute_4(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "naturalAttribute_4")) return false;
		naturalAttribute_4_0(builder_, level_ + 1);
		return true;
	}

	// EQUALS (naturalValue measure? | emptyField)
	private static boolean naturalAttribute_4_0(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "naturalAttribute_4_0")) return false;
		boolean result_;
		Marker marker_ = enter_section_(builder_);
		result_ = consumeToken(builder_, EQUALS);
		result_ = result_ && naturalAttribute_4_0_1(builder_, level_ + 1);
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	// naturalValue measure? | emptyField
	private static boolean naturalAttribute_4_0_1(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "naturalAttribute_4_0_1")) return false;
		boolean result_;
		Marker marker_ = enter_section_(builder_);
		result_ = naturalAttribute_4_0_1_0(builder_, level_ + 1);
		if (!result_) result_ = emptyField(builder_, level_ + 1);
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	// naturalValue measure?
	private static boolean naturalAttribute_4_0_1_0(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "naturalAttribute_4_0_1_0")) return false;
		boolean result_;
		Marker marker_ = enter_section_(builder_);
		result_ = naturalValue(builder_, level_ + 1);
		result_ = result_ && naturalAttribute_4_0_1_0_1(builder_, level_ + 1);
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	// measure?
	private static boolean naturalAttribute_4_0_1_0_1(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "naturalAttribute_4_0_1_0_1")) return false;
		measure(builder_, level_ + 1);
		return true;
	}

	/* ********************************************************** */
	// NATURAL_VALUE_KEY
	public static boolean naturalValue(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "naturalValue")) return false;
		if (!nextTokenIs(builder_, NATURAL_VALUE_KEY)) return false;
		boolean result_;
		Marker marker_ = enter_section_(builder_);
		result_ = consumeToken(builder_, NATURAL_VALUE_KEY);
		exit_section_(builder_, marker_, NATURAL_VALUE, result_);
		return result_;
	}

	/* ********************************************************** */
	// identifierReference+
	// 				| stringValue+
	// 		        | booleanValue+
	// 		        | naturalValue+ measure?
	// 		        | integerValue+ measure?
	// 		        | doubleValue+  measure?
	// 		        | dateValue+
	// 		        | portValue+
	// 		        | coordinateValue+
	// 		        | metaWord
	// 		        | empty
	public static boolean parameterValue(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "parameterValue")) return false;
		boolean result_;
		Marker marker_ = enter_section_(builder_, level_, _NONE_, "<parameter value>");
		result_ = parameterValue_0(builder_, level_ + 1);
		if (!result_) result_ = parameterValue_1(builder_, level_ + 1);
		if (!result_) result_ = parameterValue_2(builder_, level_ + 1);
		if (!result_) result_ = parameterValue_3(builder_, level_ + 1);
		if (!result_) result_ = parameterValue_4(builder_, level_ + 1);
		if (!result_) result_ = parameterValue_5(builder_, level_ + 1);
		if (!result_) result_ = parameterValue_6(builder_, level_ + 1);
		if (!result_) result_ = parameterValue_7(builder_, level_ + 1);
		if (!result_) result_ = parameterValue_8(builder_, level_ + 1);
		if (!result_) result_ = metaWord(builder_, level_ + 1);
		if (!result_) result_ = empty(builder_, level_ + 1);
		exit_section_(builder_, level_, marker_, PARAMETER_VALUE, result_, false, null);
		return result_;
	}

	// identifierReference+
	private static boolean parameterValue_0(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "parameterValue_0")) return false;
		boolean result_;
		Marker marker_ = enter_section_(builder_);
		result_ = identifierReference(builder_, level_ + 1);
		int pos_ = current_position_(builder_);
		while (result_) {
			if (!identifierReference(builder_, level_ + 1)) break;
			if (!empty_element_parsed_guard_(builder_, "parameterValue_0", pos_)) break;
			pos_ = current_position_(builder_);
		}
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	// stringValue+
	private static boolean parameterValue_1(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "parameterValue_1")) return false;
		boolean result_;
		Marker marker_ = enter_section_(builder_);
		result_ = stringValue(builder_, level_ + 1);
		int pos_ = current_position_(builder_);
		while (result_) {
			if (!stringValue(builder_, level_ + 1)) break;
			if (!empty_element_parsed_guard_(builder_, "parameterValue_1", pos_)) break;
			pos_ = current_position_(builder_);
		}
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	// booleanValue+
	private static boolean parameterValue_2(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "parameterValue_2")) return false;
		boolean result_;
		Marker marker_ = enter_section_(builder_);
		result_ = booleanValue(builder_, level_ + 1);
		int pos_ = current_position_(builder_);
		while (result_) {
			if (!booleanValue(builder_, level_ + 1)) break;
			if (!empty_element_parsed_guard_(builder_, "parameterValue_2", pos_)) break;
			pos_ = current_position_(builder_);
		}
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	// naturalValue+ measure?
	private static boolean parameterValue_3(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "parameterValue_3")) return false;
		boolean result_;
		Marker marker_ = enter_section_(builder_);
		result_ = parameterValue_3_0(builder_, level_ + 1);
		result_ = result_ && parameterValue_3_1(builder_, level_ + 1);
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	// naturalValue+
	private static boolean parameterValue_3_0(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "parameterValue_3_0")) return false;
		boolean result_;
		Marker marker_ = enter_section_(builder_);
		result_ = naturalValue(builder_, level_ + 1);
		int pos_ = current_position_(builder_);
		while (result_) {
			if (!naturalValue(builder_, level_ + 1)) break;
			if (!empty_element_parsed_guard_(builder_, "parameterValue_3_0", pos_)) break;
			pos_ = current_position_(builder_);
		}
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	// measure?
	private static boolean parameterValue_3_1(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "parameterValue_3_1")) return false;
		measure(builder_, level_ + 1);
		return true;
	}

	// integerValue+ measure?
	private static boolean parameterValue_4(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "parameterValue_4")) return false;
		boolean result_;
		Marker marker_ = enter_section_(builder_);
		result_ = parameterValue_4_0(builder_, level_ + 1);
		result_ = result_ && parameterValue_4_1(builder_, level_ + 1);
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	// integerValue+
	private static boolean parameterValue_4_0(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "parameterValue_4_0")) return false;
		boolean result_;
		Marker marker_ = enter_section_(builder_);
		result_ = integerValue(builder_, level_ + 1);
		int pos_ = current_position_(builder_);
		while (result_) {
			if (!integerValue(builder_, level_ + 1)) break;
			if (!empty_element_parsed_guard_(builder_, "parameterValue_4_0", pos_)) break;
			pos_ = current_position_(builder_);
		}
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	// measure?
	private static boolean parameterValue_4_1(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "parameterValue_4_1")) return false;
		measure(builder_, level_ + 1);
		return true;
	}

	// doubleValue+  measure?
	private static boolean parameterValue_5(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "parameterValue_5")) return false;
		boolean result_;
		Marker marker_ = enter_section_(builder_);
		result_ = parameterValue_5_0(builder_, level_ + 1);
		result_ = result_ && parameterValue_5_1(builder_, level_ + 1);
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	// doubleValue+
	private static boolean parameterValue_5_0(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "parameterValue_5_0")) return false;
		boolean result_;
		Marker marker_ = enter_section_(builder_);
		result_ = doubleValue(builder_, level_ + 1);
		int pos_ = current_position_(builder_);
		while (result_) {
			if (!doubleValue(builder_, level_ + 1)) break;
			if (!empty_element_parsed_guard_(builder_, "parameterValue_5_0", pos_)) break;
			pos_ = current_position_(builder_);
		}
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	// measure?
	private static boolean parameterValue_5_1(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "parameterValue_5_1")) return false;
		measure(builder_, level_ + 1);
		return true;
	}

	// dateValue+
	private static boolean parameterValue_6(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "parameterValue_6")) return false;
		boolean result_;
		Marker marker_ = enter_section_(builder_);
		result_ = dateValue(builder_, level_ + 1);
		int pos_ = current_position_(builder_);
		while (result_) {
			if (!dateValue(builder_, level_ + 1)) break;
			if (!empty_element_parsed_guard_(builder_, "parameterValue_6", pos_)) break;
			pos_ = current_position_(builder_);
		}
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	// portValue+
	private static boolean parameterValue_7(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "parameterValue_7")) return false;
		boolean result_;
		Marker marker_ = enter_section_(builder_);
		result_ = portValue(builder_, level_ + 1);
		int pos_ = current_position_(builder_);
		while (result_) {
			if (!portValue(builder_, level_ + 1)) break;
			if (!empty_element_parsed_guard_(builder_, "parameterValue_7", pos_)) break;
			pos_ = current_position_(builder_);
		}
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	// coordinateValue+
	private static boolean parameterValue_8(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "parameterValue_8")) return false;
		boolean result_;
		Marker marker_ = enter_section_(builder_);
		result_ = coordinateValue(builder_, level_ + 1);
		int pos_ = current_position_(builder_);
		while (result_) {
			if (!coordinateValue(builder_, level_ + 1)) break;
			if (!empty_element_parsed_guard_(builder_, "parameterValue_8", pos_)) break;
			pos_ = current_position_(builder_);
		}
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	/* ********************************************************** */
	// LEFT_PARENTHESIS (explicitParameters | implicitParameters)? RIGHT_PARENTHESIS
	public static boolean parameters(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "parameters")) return false;
		if (!nextTokenIs(builder_, LEFT_PARENTHESIS)) return false;
		boolean result_;
		Marker marker_ = enter_section_(builder_);
		result_ = consumeToken(builder_, LEFT_PARENTHESIS);
		result_ = result_ && parameters_1(builder_, level_ + 1);
		result_ = result_ && consumeToken(builder_, RIGHT_PARENTHESIS);
		exit_section_(builder_, marker_, PARAMETERS, result_);
		return result_;
	}

	// (explicitParameters | implicitParameters)?
	private static boolean parameters_1(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "parameters_1")) return false;
		parameters_1_0(builder_, level_ + 1);
		return true;
	}

	// explicitParameters | implicitParameters
	private static boolean parameters_1_0(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "parameters_1_0")) return false;
		boolean result_;
		Marker marker_ = enter_section_(builder_);
		result_ = explicitParameters(builder_, level_ + 1);
		if (!result_) result_ = implicitParameters(builder_, level_ + 1);
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	/* ********************************************************** */
	// EXTENDS identifierReference
	static boolean parent(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "parent")) return false;
		if (!nextTokenIs(builder_, EXTENDS)) return false;
		boolean result_;
		boolean pinned_;
		Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
		result_ = consumeToken(builder_, EXTENDS);
		pinned_ = result_; // pin = 1
		result_ = result_ && identifierReference(builder_, level_ + 1);
		exit_section_(builder_, level_, marker_, null, result_, pinned_, null);
		return result_ || pinned_;
	}

	/* ********************************************************** */
	// CODE_VALUE_KEY
	public static boolean portValue(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "portValue")) return false;
		if (!nextTokenIs(builder_, CODE_VALUE_KEY)) return false;
		boolean result_;
		Marker marker_ = enter_section_(builder_);
		result_ = consumeToken(builder_, CODE_VALUE_KEY);
		exit_section_(builder_, marker_, PORT_VALUE, result_);
		return result_;
	}

	/* ********************************************************** */
	// PORT_TYPE        LIST? IDENTIFIER_KEY (EQUALS (portValue  | emptyField))?
	static boolean refAttribute(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "refAttribute")) return false;
		if (!nextTokenIs(builder_, PORT_TYPE)) return false;
		boolean result_;
		boolean pinned_;
		Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
		result_ = consumeToken(builder_, PORT_TYPE);
		pinned_ = result_; // pin = 1
		result_ = result_ && report_error_(builder_, refAttribute_1(builder_, level_ + 1));
		result_ = pinned_ && report_error_(builder_, consumeToken(builder_, IDENTIFIER_KEY)) && result_;
		result_ = pinned_ && refAttribute_3(builder_, level_ + 1) && result_;
		exit_section_(builder_, level_, marker_, null, result_, pinned_, null);
		return result_ || pinned_;
	}

	// LIST?
	private static boolean refAttribute_1(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "refAttribute_1")) return false;
		consumeToken(builder_, LIST);
		return true;
	}

	// (EQUALS (portValue  | emptyField))?
	private static boolean refAttribute_3(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "refAttribute_3")) return false;
		refAttribute_3_0(builder_, level_ + 1);
		return true;
	}

	// EQUALS (portValue  | emptyField)
	private static boolean refAttribute_3_0(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "refAttribute_3_0")) return false;
		boolean result_;
		Marker marker_ = enter_section_(builder_);
		result_ = consumeToken(builder_, EQUALS);
		result_ = result_ && refAttribute_3_0_1(builder_, level_ + 1);
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	// portValue  | emptyField
	private static boolean refAttribute_3_0_1(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "refAttribute_3_0_1")) return false;
		boolean result_;
		Marker marker_ = enter_section_(builder_);
		result_ = portValue(builder_, level_ + 1);
		if (!result_) result_ = emptyField(builder_, level_ + 1);
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	/* ********************************************************** */
	// identifierReference LIST? IDENTIFIER_KEY (EQUALS emptyField)?
	static boolean referenceAttribute(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "referenceAttribute")) return false;
		if (!nextTokenIs(builder_, IDENTIFIER_KEY)) return false;
		boolean result_;
		boolean pinned_;
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
		boolean result_;
		Marker marker_ = enter_section_(builder_);
		result_ = consumeToken(builder_, EQUALS);
		result_ = result_ && emptyField(builder_, level_ + 1);
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	/* ********************************************************** */
	// RESOURCE_KEY attributeType IDENTIFIER_KEY
	static boolean resource(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "resource")) return false;
		if (!nextTokenIs(builder_, RESOURCE_KEY)) return false;
		boolean result_;
		boolean pinned_;
		Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
		result_ = consumeToken(builder_, RESOURCE_KEY);
		pinned_ = result_; // pin = 1
		result_ = result_ && report_error_(builder_, attributeType(builder_, level_ + 1));
		result_ = pinned_ && consumeToken(builder_, IDENTIFIER_KEY) && result_;
		exit_section_(builder_, level_, marker_, null, result_, pinned_, null);
		return result_ || pinned_;
	}

	/* ********************************************************** */
	// NEWLINE* header? NEWLINE+ (concept  NEWLINE+)*
	static boolean root(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "root")) return false;
		if (!nextTokenIs(builder_, "", BOX_KEY, NEWLINE)) return false;
		boolean result_;
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
		boolean result_;
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

	// (concept  NEWLINE+)*
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

	// concept  NEWLINE+
	private static boolean root_3_0(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "root_3_0")) return false;
		boolean result_;
		Marker marker_ = enter_section_(builder_);
		result_ = concept(builder_, level_ + 1);
		result_ = result_ && root_3_0_1(builder_, level_ + 1);
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	// NEWLINE+
	private static boolean root_3_0_1(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "root_3_0_1")) return false;
		boolean result_;
		Marker marker_ = enter_section_(builder_);
		result_ = consumeToken(builder_, NEWLINE);
		int pos_ = current_position_(builder_);
		while (result_) {
			if (!consumeToken(builder_, NEWLINE)) break;
			if (!empty_element_parsed_guard_(builder_, "root_3_0_1", pos_)) break;
			pos_ = current_position_(builder_);
		}
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	/* ********************************************************** */
	// subConcept | metaIdentifier parameters? (identifier parent?)?
	public static boolean signature(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "signature")) return false;
		boolean result_;
		Marker marker_ = enter_section_(builder_, level_, _NONE_, "<signature>");
		result_ = subConcept(builder_, level_ + 1);
		if (!result_) result_ = signature_1(builder_, level_ + 1);
		exit_section_(builder_, level_, marker_, SIGNATURE, result_, false, null);
		return result_;
	}

	// metaIdentifier parameters? (identifier parent?)?
	private static boolean signature_1(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "signature_1")) return false;
		boolean result_;
		Marker marker_ = enter_section_(builder_);
		result_ = metaIdentifier(builder_, level_ + 1);
		result_ = result_ && signature_1_1(builder_, level_ + 1);
		result_ = result_ && signature_1_2(builder_, level_ + 1);
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	// parameters?
	private static boolean signature_1_1(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "signature_1_1")) return false;
		parameters(builder_, level_ + 1);
		return true;
	}

	// (identifier parent?)?
	private static boolean signature_1_2(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "signature_1_2")) return false;
		signature_1_2_0(builder_, level_ + 1);
		return true;
	}

	// identifier parent?
	private static boolean signature_1_2_0(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "signature_1_2_0")) return false;
		boolean result_;
		Marker marker_ = enter_section_(builder_);
		result_ = identifier(builder_, level_ + 1);
		result_ = result_ && signature_1_2_0_1(builder_, level_ + 1);
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	// parent?
	private static boolean signature_1_2_0_1(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "signature_1_2_0_1")) return false;
		parent(builder_, level_ + 1);
		return true;
	}

	/* ********************************************************** */
	// STRING_TYPE  LIST?  IDENTIFIER_KEY (EQUALS (stringValue  | emptyField))?
	static boolean stringAttribute(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "stringAttribute")) return false;
		if (!nextTokenIs(builder_, STRING_TYPE)) return false;
		boolean result_;
		boolean pinned_;
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
		boolean result_;
		Marker marker_ = enter_section_(builder_);
		result_ = consumeToken(builder_, EQUALS);
		result_ = result_ && stringAttribute_3_0_1(builder_, level_ + 1);
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	// stringValue  | emptyField
	private static boolean stringAttribute_3_0_1(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "stringAttribute_3_0_1")) return false;
		boolean result_;
		Marker marker_ = enter_section_(builder_);
		result_ = stringValue(builder_, level_ + 1);
		if (!result_) result_ = emptyField(builder_, level_ + 1);
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	/* ********************************************************** */
	// STRING_VALUE_KEY  | (NEWLINE? STRING_MULTILINE_VALUE_KEY)
	public static boolean stringValue(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "stringValue")) return false;
		boolean result_;
		Marker marker_ = enter_section_(builder_, level_, _NONE_, "<string value>");
		result_ = consumeToken(builder_, STRING_VALUE_KEY);
		if (!result_) result_ = stringValue_1(builder_, level_ + 1);
		exit_section_(builder_, level_, marker_, STRING_VALUE, result_, false, null);
		return result_;
	}

	// NEWLINE? STRING_MULTILINE_VALUE_KEY
	private static boolean stringValue_1(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "stringValue_1")) return false;
		boolean result_;
		Marker marker_ = enter_section_(builder_);
		result_ = stringValue_1_0(builder_, level_ + 1);
		result_ = result_ && consumeToken(builder_, STRING_MULTILINE_VALUE_KEY);
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	// NEWLINE?
	private static boolean stringValue_1_0(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "stringValue_1_0")) return false;
		consumeToken(builder_, NEWLINE);
		return true;
	}

	/* ********************************************************** */
	// SUB identifier
	static boolean subConcept(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "subConcept")) return false;
		if (!nextTokenIs(builder_, SUB)) return false;
		boolean result_;
		boolean pinned_;
		Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
		result_ = consumeToken(builder_, SUB);
		pinned_ = result_; // pin = 1
		result_ = result_ && identifier(builder_, level_ + 1);
		exit_section_(builder_, level_, marker_, null, result_, pinned_, null);
		return result_ || pinned_;
	}

	/* ********************************************************** */
	// IDENTIFIER_KEY EQUALS ( emptyField
	// 								 | identifierReference+
	// 								 | stringValue+
	//                                  | booleanValue+
	//                                  | dateValue+
	//                                  | portValue+
	//                                  | coordinateValue+
	//                                  | naturalValue+ measure?
	//                                  | integerValue+ measure?
	//                                  | doubleValue+  measure?)
	public static boolean varInit(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "varInit")) return false;
		if (!nextTokenIs(builder_, IDENTIFIER_KEY)) return false;
		boolean result_;
		boolean pinned_;
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
	//                                  | dateValue+
	//                                  | portValue+
	//                                  | coordinateValue+
	//                                  | naturalValue+ measure?
	//                                  | integerValue+ measure?
	//                                  | doubleValue+  measure?
	private static boolean varInit_2(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "varInit_2")) return false;
		boolean result_;
		Marker marker_ = enter_section_(builder_);
		result_ = emptyField(builder_, level_ + 1);
		if (!result_) result_ = varInit_2_1(builder_, level_ + 1);
		if (!result_) result_ = varInit_2_2(builder_, level_ + 1);
		if (!result_) result_ = varInit_2_3(builder_, level_ + 1);
		if (!result_) result_ = varInit_2_4(builder_, level_ + 1);
		if (!result_) result_ = varInit_2_5(builder_, level_ + 1);
		if (!result_) result_ = varInit_2_6(builder_, level_ + 1);
		if (!result_) result_ = varInit_2_7(builder_, level_ + 1);
		if (!result_) result_ = varInit_2_8(builder_, level_ + 1);
		if (!result_) result_ = varInit_2_9(builder_, level_ + 1);
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	// identifierReference+
	private static boolean varInit_2_1(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "varInit_2_1")) return false;
		boolean result_;
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
		boolean result_;
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
		boolean result_;
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

	// dateValue+
	private static boolean varInit_2_4(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "varInit_2_4")) return false;
		boolean result_;
		Marker marker_ = enter_section_(builder_);
		result_ = dateValue(builder_, level_ + 1);
		int pos_ = current_position_(builder_);
		while (result_) {
			if (!dateValue(builder_, level_ + 1)) break;
			if (!empty_element_parsed_guard_(builder_, "varInit_2_4", pos_)) break;
			pos_ = current_position_(builder_);
		}
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	// portValue+
	private static boolean varInit_2_5(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "varInit_2_5")) return false;
		boolean result_;
		Marker marker_ = enter_section_(builder_);
		result_ = portValue(builder_, level_ + 1);
		int pos_ = current_position_(builder_);
		while (result_) {
			if (!portValue(builder_, level_ + 1)) break;
			if (!empty_element_parsed_guard_(builder_, "varInit_2_5", pos_)) break;
			pos_ = current_position_(builder_);
		}
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	// coordinateValue+
	private static boolean varInit_2_6(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "varInit_2_6")) return false;
		boolean result_;
		Marker marker_ = enter_section_(builder_);
		result_ = coordinateValue(builder_, level_ + 1);
		int pos_ = current_position_(builder_);
		while (result_) {
			if (!coordinateValue(builder_, level_ + 1)) break;
			if (!empty_element_parsed_guard_(builder_, "varInit_2_6", pos_)) break;
			pos_ = current_position_(builder_);
		}
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	// naturalValue+ measure?
	private static boolean varInit_2_7(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "varInit_2_7")) return false;
		boolean result_;
		Marker marker_ = enter_section_(builder_);
		result_ = varInit_2_7_0(builder_, level_ + 1);
		result_ = result_ && varInit_2_7_1(builder_, level_ + 1);
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	// naturalValue+
	private static boolean varInit_2_7_0(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "varInit_2_7_0")) return false;
		boolean result_;
		Marker marker_ = enter_section_(builder_);
		result_ = naturalValue(builder_, level_ + 1);
		int pos_ = current_position_(builder_);
		while (result_) {
			if (!naturalValue(builder_, level_ + 1)) break;
			if (!empty_element_parsed_guard_(builder_, "varInit_2_7_0", pos_)) break;
			pos_ = current_position_(builder_);
		}
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	// measure?
	private static boolean varInit_2_7_1(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "varInit_2_7_1")) return false;
		measure(builder_, level_ + 1);
		return true;
	}

	// integerValue+ measure?
	private static boolean varInit_2_8(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "varInit_2_8")) return false;
		boolean result_;
		Marker marker_ = enter_section_(builder_);
		result_ = varInit_2_8_0(builder_, level_ + 1);
		result_ = result_ && varInit_2_8_1(builder_, level_ + 1);
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	// integerValue+
	private static boolean varInit_2_8_0(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "varInit_2_8_0")) return false;
		boolean result_;
		Marker marker_ = enter_section_(builder_);
		result_ = integerValue(builder_, level_ + 1);
		int pos_ = current_position_(builder_);
		while (result_) {
			if (!integerValue(builder_, level_ + 1)) break;
			if (!empty_element_parsed_guard_(builder_, "varInit_2_8_0", pos_)) break;
			pos_ = current_position_(builder_);
		}
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	// measure?
	private static boolean varInit_2_8_1(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "varInit_2_8_1")) return false;
		measure(builder_, level_ + 1);
		return true;
	}

	// doubleValue+  measure?
	private static boolean varInit_2_9(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "varInit_2_9")) return false;
		boolean result_;
		Marker marker_ = enter_section_(builder_);
		result_ = varInit_2_9_0(builder_, level_ + 1);
		result_ = result_ && varInit_2_9_1(builder_, level_ + 1);
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	// doubleValue+
	private static boolean varInit_2_9_0(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "varInit_2_9_0")) return false;
		boolean result_;
		Marker marker_ = enter_section_(builder_);
		result_ = doubleValue(builder_, level_ + 1);
		int pos_ = current_position_(builder_);
		while (result_) {
			if (!doubleValue(builder_, level_ + 1)) break;
			if (!empty_element_parsed_guard_(builder_, "varInit_2_9_0", pos_)) break;
			pos_ = current_position_(builder_);
		}
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	// measure?
	private static boolean varInit_2_9_1(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "varInit_2_9_1")) return false;
		measure(builder_, level_ + 1);
		return true;
	}

	/* ********************************************************** */
	// WORD_KEY IDENTIFIER_KEY NEW_LINE_INDENT (IDENTIFIER_KEY STAR? NEWLINE)+ DEDENT
	public static boolean word(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "word")) return false;
		if (!nextTokenIs(builder_, WORD_KEY)) return false;
		boolean result_;
		boolean pinned_;
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
		boolean result_;
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
		boolean result_;
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
