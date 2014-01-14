// This is a generated file. Not intended for manual editing.
package monet.tara.intellij.parser;

import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import com.intellij.lang.PsiParser;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.psi.tree.IElementType;

import static monet.tara.intellij.parser.TaraParserUtil.*;
import static monet.tara.intellij.psi.TaraTypes.*;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class TaraParser implements PsiParser {

	public static final Logger LOG_ = Logger.getInstance("monet.tara.intellij.parser.TaraParser");

	public ASTNode parse(IElementType root_, PsiBuilder builder_) {
		boolean result_;
		builder_ = adapt_builder_(root_, builder_, this, null);
		Marker marker_ = enter_section_(builder_, 0, _COLLAPSE_, null);
		if (root_ == ANNOTATION) {
			result_ = annotation(builder_, 0);
		} else if (root_ == ATTRIBUTE) {
			result_ = attribute(builder_, 0);
		} else if (root_ == ATTRIBUTE_LIST) {
			result_ = attributeList(builder_, 0);
		} else if (root_ == BOOLEAN_ASSIGN) {
			result_ = booleanAssign(builder_, 0);
		} else if (root_ == BOOLEAN_LIST_ASSIGN) {
			result_ = booleanListAssign(builder_, 0);
		} else if (root_ == CHILD) {
			result_ = child(builder_, 0);
		} else if (root_ == CHILD_ANNOTATION) {
			result_ = childAnnotation(builder_, 0);
		} else if (root_ == CONCEPT) {
			result_ = concept(builder_, 0);
		} else if (root_ == CONCEPT_ANNOTATION) {
			result_ = conceptAnnotation(builder_, 0);
		} else if (root_ == CONCEPT_BODY) {
			result_ = conceptBody(builder_, 0);
		} else if (root_ == CONCEPT_CONSTITUENTS) {
			result_ = conceptConstituents(builder_, 0);
		} else if (root_ == CONCEPT_SIGNATURE) {
			result_ = conceptSignature(builder_, 0);
		} else if (root_ == DOC) {
			result_ = doc(builder_, 0);
		} else if (root_ == DOUBLE_ASSIGN) {
			result_ = doubleAssign(builder_, 0);
		} else if (root_ == DOUBLE_LIST_ASSIGN) {
			result_ = doubleListAssign(builder_, 0);
		} else if (root_ == FROM) {
			result_ = from(builder_, 0);
		} else if (root_ == FROM_BODY) {
			result_ = fromBody(builder_, 0);
		} else if (root_ == IDENTIFIER) {
			result_ = identifier(builder_, 0);
		} else if (root_ == INTEGER_ASSIGN) {
			result_ = integerAssign(builder_, 0);
		} else if (root_ == INTEGER_LIST_ASSIGN) {
			result_ = integerListAssign(builder_, 0);
		} else if (root_ == INTEGER_VALUE) {
			result_ = integerValue(builder_, 0);
		} else if (root_ == MODIFIER) {
			result_ = modifier(builder_, 0);
		} else if (root_ == NATURAL_ASSIGN) {
			result_ = naturalAssign(builder_, 0);
		} else if (root_ == NATURAL_LIST_ASSIGN) {
			result_ = naturalListAssign(builder_, 0);
		} else if (root_ == RANGE) {
			result_ = range(builder_, 0);
		} else if (root_ == RANGE_ANNOTATION) {
			result_ = rangeAnnotation(builder_, 0);
		} else if (root_ == REFERENCE_STATEMENT) {
			result_ = referenceStatement(builder_, 0);
		} else if (root_ == REFERENCE_STATEMENT_LIST) {
			result_ = referenceStatementList(builder_, 0);
		} else if (root_ == STRING_ASSIGN) {
			result_ = stringAssign(builder_, 0);
		} else if (root_ == STRING_LIST_ASSIGN) {
			result_ = stringListAssign(builder_, 0);
		} else if (root_ == WORD_BODY) {
			result_ = wordBody(builder_, 0);
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
	// HAS_CODE
	//             | EXTENSIBLE
	public static boolean annotation(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "annotation")) return false;
		if (!nextTokenIs(builder_, "<annotation>", EXTENSIBLE, HAS_CODE)) return false;
		boolean result_ = false;
		Marker marker_ = enter_section_(builder_, level_, _NONE_, "<annotation>");
		result_ = consumeToken(builder_, HAS_CODE);
		if (!result_) result_ = consumeToken(builder_, EXTENSIBLE);
		exit_section_(builder_, level_, marker_, ANNOTATION, result_, false, null);
		return result_;
	}

	/* ********************************************************** */
	// VAR     UID_TYPE IDENTIFIER_KEY stringAssign?
	//            | VAR     INT_TYPE IDENTIFIER_KEY integerAssign?
	//            | VAR  DOUBLE_TYPE IDENTIFIER_KEY doubleAssign?
	//            | VAR NATURAL_TYPE IDENTIFIER_KEY naturalAssign?
	//            | VAR BOOLEAN_TYPE IDENTIFIER_KEY booleanAssign?
	//            | VAR  STRING_TYPE IDENTIFIER_KEY stringAssign?
	//            | VAR WORD IDENTIFIER_KEY wordBody
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
		if (!result_) result_ = attribute_6(builder_, level_ + 1);
		exit_section_(builder_, marker_, ATTRIBUTE, result_);
		return result_;
	}

	// VAR     UID_TYPE IDENTIFIER_KEY stringAssign?
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

	// VAR     INT_TYPE IDENTIFIER_KEY integerAssign?
	private static boolean attribute_1(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "attribute_1")) return false;
		boolean result_ = false;
		Marker marker_ = enter_section_(builder_);
		result_ = consumeTokens(builder_, 0, VAR, INT_TYPE, IDENTIFIER_KEY);
		result_ = result_ && attribute_1_3(builder_, level_ + 1);
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	// integerAssign?
	private static boolean attribute_1_3(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "attribute_1_3")) return false;
		integerAssign(builder_, level_ + 1);
		return true;
	}

	// VAR  DOUBLE_TYPE IDENTIFIER_KEY doubleAssign?
	private static boolean attribute_2(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "attribute_2")) return false;
		boolean result_ = false;
		Marker marker_ = enter_section_(builder_);
		result_ = consumeTokens(builder_, 0, VAR, DOUBLE_TYPE, IDENTIFIER_KEY);
		result_ = result_ && attribute_2_3(builder_, level_ + 1);
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	// doubleAssign?
	private static boolean attribute_2_3(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "attribute_2_3")) return false;
		doubleAssign(builder_, level_ + 1);
		return true;
	}

	// VAR NATURAL_TYPE IDENTIFIER_KEY naturalAssign?
	private static boolean attribute_3(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "attribute_3")) return false;
		boolean result_ = false;
		Marker marker_ = enter_section_(builder_);
		result_ = consumeTokens(builder_, 0, VAR, NATURAL_TYPE, IDENTIFIER_KEY);
		result_ = result_ && attribute_3_3(builder_, level_ + 1);
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	// naturalAssign?
	private static boolean attribute_3_3(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "attribute_3_3")) return false;
		naturalAssign(builder_, level_ + 1);
		return true;
	}

	// VAR BOOLEAN_TYPE IDENTIFIER_KEY booleanAssign?
	private static boolean attribute_4(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "attribute_4")) return false;
		boolean result_ = false;
		Marker marker_ = enter_section_(builder_);
		result_ = consumeTokens(builder_, 0, VAR, BOOLEAN_TYPE, IDENTIFIER_KEY);
		result_ = result_ && attribute_4_3(builder_, level_ + 1);
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	// booleanAssign?
	private static boolean attribute_4_3(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "attribute_4_3")) return false;
		booleanAssign(builder_, level_ + 1);
		return true;
	}

	// VAR  STRING_TYPE IDENTIFIER_KEY stringAssign?
	private static boolean attribute_5(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "attribute_5")) return false;
		boolean result_ = false;
		Marker marker_ = enter_section_(builder_);
		result_ = consumeTokens(builder_, 0, VAR, STRING_TYPE, IDENTIFIER_KEY);
		result_ = result_ && attribute_5_3(builder_, level_ + 1);
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	// stringAssign?
	private static boolean attribute_5_3(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "attribute_5_3")) return false;
		stringAssign(builder_, level_ + 1);
		return true;
	}

	// VAR WORD IDENTIFIER_KEY wordBody
	private static boolean attribute_6(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "attribute_6")) return false;
		boolean result_ = false;
		Marker marker_ = enter_section_(builder_);
		result_ = consumeTokens(builder_, 0, VAR, WORD, IDENTIFIER_KEY);
		result_ = result_ && wordBody(builder_, level_ + 1);
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	/* ********************************************************** */
	// VAR     INT_TYPE LIST IDENTIFIER_KEY integerListAssign?
	//                | VAR  DOUBLE_TYPE LIST IDENTIFIER_KEY doubleListAssign?
	//                | VAR NATURAL_TYPE LIST IDENTIFIER_KEY naturalListAssign?
	//                | VAR BOOLEAN_TYPE LIST IDENTIFIER_KEY booleanListAssign?
	//                | VAR  STRING_TYPE LIST IDENTIFIER_KEY stringListAssign?
	public static boolean attributeList(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "attributeList")) return false;
		if (!nextTokenIs(builder_, VAR)) return false;
		boolean result_ = false;
		Marker marker_ = enter_section_(builder_);
		result_ = attributeList_0(builder_, level_ + 1);
		if (!result_) result_ = attributeList_1(builder_, level_ + 1);
		if (!result_) result_ = attributeList_2(builder_, level_ + 1);
		if (!result_) result_ = attributeList_3(builder_, level_ + 1);
		if (!result_) result_ = attributeList_4(builder_, level_ + 1);
		exit_section_(builder_, marker_, ATTRIBUTE_LIST, result_);
		return result_;
	}

	// VAR     INT_TYPE LIST IDENTIFIER_KEY integerListAssign?
	private static boolean attributeList_0(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "attributeList_0")) return false;
		boolean result_ = false;
		Marker marker_ = enter_section_(builder_);
		result_ = consumeTokens(builder_, 0, VAR, INT_TYPE, LIST, IDENTIFIER_KEY);
		result_ = result_ && attributeList_0_4(builder_, level_ + 1);
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	// integerListAssign?
	private static boolean attributeList_0_4(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "attributeList_0_4")) return false;
		integerListAssign(builder_, level_ + 1);
		return true;
	}

	// VAR  DOUBLE_TYPE LIST IDENTIFIER_KEY doubleListAssign?
	private static boolean attributeList_1(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "attributeList_1")) return false;
		boolean result_ = false;
		Marker marker_ = enter_section_(builder_);
		result_ = consumeTokens(builder_, 0, VAR, DOUBLE_TYPE, LIST, IDENTIFIER_KEY);
		result_ = result_ && attributeList_1_4(builder_, level_ + 1);
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	// doubleListAssign?
	private static boolean attributeList_1_4(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "attributeList_1_4")) return false;
		doubleListAssign(builder_, level_ + 1);
		return true;
	}

	// VAR NATURAL_TYPE LIST IDENTIFIER_KEY naturalListAssign?
	private static boolean attributeList_2(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "attributeList_2")) return false;
		boolean result_ = false;
		Marker marker_ = enter_section_(builder_);
		result_ = consumeTokens(builder_, 0, VAR, NATURAL_TYPE, LIST, IDENTIFIER_KEY);
		result_ = result_ && attributeList_2_4(builder_, level_ + 1);
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	// naturalListAssign?
	private static boolean attributeList_2_4(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "attributeList_2_4")) return false;
		naturalListAssign(builder_, level_ + 1);
		return true;
	}

	// VAR BOOLEAN_TYPE LIST IDENTIFIER_KEY booleanListAssign?
	private static boolean attributeList_3(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "attributeList_3")) return false;
		boolean result_ = false;
		Marker marker_ = enter_section_(builder_);
		result_ = consumeTokens(builder_, 0, VAR, BOOLEAN_TYPE, LIST, IDENTIFIER_KEY);
		result_ = result_ && attributeList_3_4(builder_, level_ + 1);
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	// booleanListAssign?
	private static boolean attributeList_3_4(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "attributeList_3_4")) return false;
		booleanListAssign(builder_, level_ + 1);
		return true;
	}

	// VAR  STRING_TYPE LIST IDENTIFIER_KEY stringListAssign?
	private static boolean attributeList_4(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "attributeList_4")) return false;
		boolean result_ = false;
		Marker marker_ = enter_section_(builder_);
		result_ = consumeTokens(builder_, 0, VAR, STRING_TYPE, LIST, IDENTIFIER_KEY);
		result_ = result_ && attributeList_4_4(builder_, level_ + 1);
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	// stringListAssign?
	private static boolean attributeList_4_4(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "attributeList_4_4")) return false;
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
	// ASSIGN LEFT_BRACKET BOOLEAN_VALUE+ RIGHT_BRACKET
	public static boolean booleanListAssign(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "booleanListAssign")) return false;
		if (!nextTokenIs(builder_, ASSIGN)) return false;
		boolean result_ = false;
		Marker marker_ = enter_section_(builder_);
		result_ = consumeTokens(builder_, 0, ASSIGN, LEFT_BRACKET);
		result_ = result_ && booleanListAssign_2(builder_, level_ + 1);
		result_ = result_ && consumeToken(builder_, RIGHT_BRACKET);
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
	// doc? (CONCEPT_KEY | identifier) modifier? (AS identifier)? childAnnotation? conceptBody?
	public static boolean child(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "child")) return false;
		boolean result_ = false;
		Marker marker_ = enter_section_(builder_, level_, _NONE_, "<child>");
		result_ = child_0(builder_, level_ + 1);
		result_ = result_ && child_1(builder_, level_ + 1);
		result_ = result_ && child_2(builder_, level_ + 1);
		result_ = result_ && child_3(builder_, level_ + 1);
		result_ = result_ && child_4(builder_, level_ + 1);
		result_ = result_ && child_5(builder_, level_ + 1);
		exit_section_(builder_, level_, marker_, CHILD, result_, false, null);
		return result_;
	}

	// doc?
	private static boolean child_0(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "child_0")) return false;
		doc(builder_, level_ + 1);
		return true;
	}

	// CONCEPT_KEY | identifier
	private static boolean child_1(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "child_1")) return false;
		boolean result_ = false;
		Marker marker_ = enter_section_(builder_);
		result_ = consumeToken(builder_, CONCEPT_KEY);
		if (!result_) result_ = identifier(builder_, level_ + 1);
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	// modifier?
	private static boolean child_2(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "child_2")) return false;
		modifier(builder_, level_ + 1);
		return true;
	}

	// (AS identifier)?
	private static boolean child_3(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "child_3")) return false;
		child_3_0(builder_, level_ + 1);
		return true;
	}

	// AS identifier
	private static boolean child_3_0(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "child_3_0")) return false;
		boolean result_ = false;
		Marker marker_ = enter_section_(builder_);
		result_ = consumeToken(builder_, AS);
		result_ = result_ && identifier(builder_, level_ + 1);
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	// childAnnotation?
	private static boolean child_4(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "child_4")) return false;
		childAnnotation(builder_, level_ + 1);
		return true;
	}

	// conceptBody?
	private static boolean child_5(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "child_5")) return false;
		conceptBody(builder_, level_ + 1);
		return true;
	}

	/* ********************************************************** */
	// OPEN_AN (range | annotation)+ CLOSE_AN
	public static boolean childAnnotation(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "childAnnotation")) return false;
		if (!nextTokenIs(builder_, OPEN_AN)) return false;
		boolean result_ = false;
		Marker marker_ = enter_section_(builder_);
		result_ = consumeToken(builder_, OPEN_AN);
		result_ = result_ && childAnnotation_1(builder_, level_ + 1);
		result_ = result_ && consumeToken(builder_, CLOSE_AN);
		exit_section_(builder_, marker_, CHILD_ANNOTATION, result_);
		return result_;
	}

	// (range | annotation)+
	private static boolean childAnnotation_1(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "childAnnotation_1")) return false;
		boolean result_ = false;
		Marker marker_ = enter_section_(builder_);
		result_ = childAnnotation_1_0(builder_, level_ + 1);
		int pos_ = current_position_(builder_);
		while (result_) {
			if (!childAnnotation_1_0(builder_, level_ + 1)) break;
			if (!empty_element_parsed_guard_(builder_, "childAnnotation_1", pos_)) break;
			pos_ = current_position_(builder_);
		}
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	// range | annotation
	private static boolean childAnnotation_1_0(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "childAnnotation_1_0")) return false;
		boolean result_ = false;
		Marker marker_ = enter_section_(builder_);
		result_ = range(builder_, level_ + 1);
		if (!result_) result_ = annotation(builder_, level_ + 1);
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	/* ********************************************************** */
	// doc? conceptSignature conceptBody?
	public static boolean concept(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "concept")) return false;
		boolean result_ = false;
		Marker marker_ = enter_section_(builder_, level_, _NONE_, "<concept>");
		result_ = concept_0(builder_, level_ + 1);
		result_ = result_ && conceptSignature(builder_, level_ + 1);
		result_ = result_ && concept_2(builder_, level_ + 1);
		exit_section_(builder_, level_, marker_, CONCEPT, result_, false, null);
		return result_;
	}

	// doc?
	private static boolean concept_0(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "concept_0")) return false;
		doc(builder_, level_ + 1);
		return true;
	}

	// conceptBody?
	private static boolean concept_2(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "concept_2")) return false;
		conceptBody(builder_, level_ + 1);
		return true;
	}

	/* ********************************************************** */
	// OPEN_AN annotation+ CLOSE_AN
	public static boolean conceptAnnotation(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "conceptAnnotation")) return false;
		if (!nextTokenIs(builder_, OPEN_AN)) return false;
		boolean result_ = false;
		Marker marker_ = enter_section_(builder_);
		result_ = consumeToken(builder_, OPEN_AN);
		result_ = result_ && conceptAnnotation_1(builder_, level_ + 1);
		result_ = result_ && consumeToken(builder_, CLOSE_AN);
		exit_section_(builder_, marker_, CONCEPT_ANNOTATION, result_);
		return result_;
	}

	// annotation+
	private static boolean conceptAnnotation_1(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "conceptAnnotation_1")) return false;
		boolean result_ = false;
		Marker marker_ = enter_section_(builder_);
		result_ = annotation(builder_, level_ + 1);
		int pos_ = current_position_(builder_);
		while (result_) {
			if (!annotation(builder_, level_ + 1)) break;
			if (!empty_element_parsed_guard_(builder_, "conceptAnnotation_1", pos_)) break;
			pos_ = current_position_(builder_);
		}
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	/* ********************************************************** */
	// INDENT conceptConstituents+ DEDENT
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

	// conceptConstituents+
	private static boolean conceptBody_1(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "conceptBody_1")) return false;
		boolean result_ = false;
		Marker marker_ = enter_section_(builder_);
		result_ = conceptConstituents(builder_, level_ + 1);
		int pos_ = current_position_(builder_);
		while (result_) {
			if (!conceptConstituents(builder_, level_ + 1)) break;
			if (!empty_element_parsed_guard_(builder_, "conceptBody_1", pos_)) break;
			pos_ = current_position_(builder_);
		}
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	/* ********************************************************** */
	// attribute
	//                       | attributeList
	//                       | referenceStatement
	//                       | referenceStatementList
	//                       | from
	//                       | child
	public static boolean conceptConstituents(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "conceptConstituents")) return false;
		boolean result_ = false;
		Marker marker_ = enter_section_(builder_, level_, _NONE_, "<concept constituents>");
		result_ = attribute(builder_, level_ + 1);
		if (!result_) result_ = attributeList(builder_, level_ + 1);
		if (!result_) result_ = referenceStatement(builder_, level_ + 1);
		if (!result_) result_ = referenceStatementList(builder_, level_ + 1);
		if (!result_) result_ = from(builder_, level_ + 1);
		if (!result_) result_ = child(builder_, level_ + 1);
		exit_section_(builder_, level_, marker_, CONCEPT_CONSTITUENTS, result_, false, null);
		return result_;
	}

	/* ********************************************************** */
	// (CONCEPT_KEY | identifier) modifier? AS identifier conceptAnnotation?
	public static boolean conceptSignature(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "conceptSignature")) return false;
		if (!nextTokenIs(builder_, "<concept signature>", CONCEPT_KEY, IDENTIFIER_KEY)) return false;
		boolean result_ = false;
		Marker marker_ = enter_section_(builder_, level_, _NONE_, "<concept signature>");
		result_ = conceptSignature_0(builder_, level_ + 1);
		result_ = result_ && conceptSignature_1(builder_, level_ + 1);
		result_ = result_ && consumeToken(builder_, AS);
		result_ = result_ && identifier(builder_, level_ + 1);
		result_ = result_ && conceptSignature_4(builder_, level_ + 1);
		exit_section_(builder_, level_, marker_, CONCEPT_SIGNATURE, result_, false, null);
		return result_;
	}

	// CONCEPT_KEY | identifier
	private static boolean conceptSignature_0(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "conceptSignature_0")) return false;
		boolean result_ = false;
		Marker marker_ = enter_section_(builder_);
		result_ = consumeToken(builder_, CONCEPT_KEY);
		if (!result_) result_ = identifier(builder_, level_ + 1);
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	// modifier?
	private static boolean conceptSignature_1(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "conceptSignature_1")) return false;
		modifier(builder_, level_ + 1);
		return true;
	}

	// conceptAnnotation?
	private static boolean conceptSignature_4(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "conceptSignature_4")) return false;
		conceptAnnotation(builder_, level_ + 1);
		return true;
	}

	/* ********************************************************** */
	// DOC_LINE
	//      | DOC_BLOCK
	public static boolean doc(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "doc")) return false;
		if (!nextTokenIs(builder_, "<doc>", DOC_BLOCK, DOC_LINE)) return false;
		boolean result_ = false;
		Marker marker_ = enter_section_(builder_, level_, _NONE_, "<doc>");
		result_ = consumeToken(builder_, DOC_LINE);
		if (!result_) result_ = consumeToken(builder_, DOC_BLOCK);
		exit_section_(builder_, level_, marker_, DOC, result_, false, null);
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
	// ASSIGN LEFT_BRACKET (integerValue | DOUBLE_VALUE)+ RIGHT_BRACKET
	public static boolean doubleListAssign(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "doubleListAssign")) return false;
		if (!nextTokenIs(builder_, ASSIGN)) return false;
		boolean result_ = false;
		Marker marker_ = enter_section_(builder_);
		result_ = consumeTokens(builder_, 0, ASSIGN, LEFT_BRACKET);
		result_ = result_ && doubleListAssign_2(builder_, level_ + 1);
		result_ = result_ && consumeToken(builder_, RIGHT_BRACKET);
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
	// FROM_KEY identifier rangeAnnotation?
	//     | rangeAnnotation? fromBody
	public static boolean from(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "from")) return false;
		boolean result_ = false;
		Marker marker_ = enter_section_(builder_, level_, _NONE_, "<from>");
		result_ = from_0(builder_, level_ + 1);
		if (!result_) result_ = from_1(builder_, level_ + 1);
		exit_section_(builder_, level_, marker_, FROM, result_, false, null);
		return result_;
	}

	// FROM_KEY identifier rangeAnnotation?
	private static boolean from_0(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "from_0")) return false;
		boolean result_ = false;
		Marker marker_ = enter_section_(builder_);
		result_ = consumeToken(builder_, FROM_KEY);
		result_ = result_ && identifier(builder_, level_ + 1);
		result_ = result_ && from_0_2(builder_, level_ + 1);
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	// rangeAnnotation?
	private static boolean from_0_2(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "from_0_2")) return false;
		rangeAnnotation(builder_, level_ + 1);
		return true;
	}

	// rangeAnnotation? fromBody
	private static boolean from_1(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "from_1")) return false;
		boolean result_ = false;
		Marker marker_ = enter_section_(builder_);
		result_ = from_1_0(builder_, level_ + 1);
		result_ = result_ && fromBody(builder_, level_ + 1);
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	// rangeAnnotation?
	private static boolean from_1_0(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "from_1_0")) return false;
		rangeAnnotation(builder_, level_ + 1);
		return true;
	}

	/* ********************************************************** */
	// INDENT child DEDENT
	public static boolean fromBody(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "fromBody")) return false;
		if (!nextTokenIs(builder_, INDENT)) return false;
		boolean result_ = false;
		Marker marker_ = enter_section_(builder_);
		result_ = consumeToken(builder_, INDENT);
		result_ = result_ && child(builder_, level_ + 1);
		result_ = result_ && consumeToken(builder_, DEDENT);
		exit_section_(builder_, marker_, FROM_BODY, result_);
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
	// ASSIGN LEFT_BRACKET integerValue+ RIGHT_BRACKET
	public static boolean integerListAssign(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "integerListAssign")) return false;
		if (!nextTokenIs(builder_, ASSIGN)) return false;
		boolean result_ = false;
		Marker marker_ = enter_section_(builder_);
		result_ = consumeTokens(builder_, 0, ASSIGN, LEFT_BRACKET);
		result_ = result_ && integerListAssign_2(builder_, level_ + 1);
		result_ = result_ && consumeToken(builder_, RIGHT_BRACKET);
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
	// ASSIGN LEFT_BRACKET NATURAL_VALUE+ RIGHT_BRACKET
	public static boolean naturalListAssign(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "naturalListAssign")) return false;
		if (!nextTokenIs(builder_, ASSIGN)) return false;
		boolean result_ = false;
		Marker marker_ = enter_section_(builder_);
		result_ = consumeTokens(builder_, 0, ASSIGN, LEFT_BRACKET);
		result_ = result_ && naturalListAssign_2(builder_, level_ + 1);
		result_ = result_ && consumeToken(builder_, RIGHT_BRACKET);
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
	// OPTIONAL
	//        | MULTIPLE
	public static boolean range(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "range")) return false;
		if (!nextTokenIs(builder_, "<range>", MULTIPLE, OPTIONAL)) return false;
		boolean result_ = false;
		Marker marker_ = enter_section_(builder_, level_, _NONE_, "<range>");
		result_ = consumeToken(builder_, OPTIONAL);
		if (!result_) result_ = consumeToken(builder_, MULTIPLE);
		exit_section_(builder_, level_, marker_, RANGE, result_, false, null);
		return result_;
	}

	/* ********************************************************** */
	// OPEN_AN range+ CLOSE_AN
	public static boolean rangeAnnotation(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "rangeAnnotation")) return false;
		if (!nextTokenIs(builder_, OPEN_AN)) return false;
		boolean result_ = false;
		Marker marker_ = enter_section_(builder_);
		result_ = consumeToken(builder_, OPEN_AN);
		result_ = result_ && rangeAnnotation_1(builder_, level_ + 1);
		result_ = result_ && consumeToken(builder_, CLOSE_AN);
		exit_section_(builder_, marker_, RANGE_ANNOTATION, result_);
		return result_;
	}

	// range+
	private static boolean rangeAnnotation_1(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "rangeAnnotation_1")) return false;
		boolean result_ = false;
		Marker marker_ = enter_section_(builder_);
		result_ = range(builder_, level_ + 1);
		int pos_ = current_position_(builder_);
		while (result_) {
			if (!range(builder_, level_ + 1)) break;
			if (!empty_element_parsed_guard_(builder_, "rangeAnnotation_1", pos_)) break;
			pos_ = current_position_(builder_);
		}
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	/* ********************************************************** */
	// VAR identifier IDENTIFIER_KEY
	public static boolean referenceStatement(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "referenceStatement")) return false;
		if (!nextTokenIs(builder_, VAR)) return false;
		boolean result_ = false;
		Marker marker_ = enter_section_(builder_);
		result_ = consumeToken(builder_, VAR);
		result_ = result_ && identifier(builder_, level_ + 1);
		result_ = result_ && consumeToken(builder_, IDENTIFIER_KEY);
		exit_section_(builder_, marker_, REFERENCE_STATEMENT, result_);
		return result_;
	}

	/* ********************************************************** */
	// VAR identifier LIST IDENTIFIER_KEY
	public static boolean referenceStatementList(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "referenceStatementList")) return false;
		if (!nextTokenIs(builder_, VAR)) return false;
		boolean result_ = false;
		Marker marker_ = enter_section_(builder_);
		result_ = consumeToken(builder_, VAR);
		result_ = result_ && identifier(builder_, level_ + 1);
		result_ = result_ && consumeTokens(builder_, 0, LIST, IDENTIFIER_KEY);
		exit_section_(builder_, marker_, REFERENCE_STATEMENT_LIST, result_);
		return result_;
	}

	/* ********************************************************** */
	// (concept)*
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

	// (concept)
	private static boolean root_0(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "root_0")) return false;
		boolean result_ = false;
		Marker marker_ = enter_section_(builder_);
		result_ = concept(builder_, level_ + 1);
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
	// ASSIGN LEFT_BRACKET STRING_VALUE+ RIGHT_BRACKET
	public static boolean stringListAssign(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "stringListAssign")) return false;
		if (!nextTokenIs(builder_, ASSIGN)) return false;
		boolean result_ = false;
		Marker marker_ = enter_section_(builder_);
		result_ = consumeTokens(builder_, 0, ASSIGN, LEFT_BRACKET);
		result_ = result_ && stringListAssign_2(builder_, level_ + 1);
		result_ = result_ && consumeToken(builder_, RIGHT_BRACKET);
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
	// INDENT IDENTIFIER_KEY+ DEDENT
	public static boolean wordBody(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "wordBody")) return false;
		if (!nextTokenIs(builder_, INDENT)) return false;
		boolean result_ = false;
		Marker marker_ = enter_section_(builder_);
		result_ = consumeToken(builder_, INDENT);
		result_ = result_ && wordBody_1(builder_, level_ + 1);
		result_ = result_ && consumeToken(builder_, DEDENT);
		exit_section_(builder_, marker_, WORD_BODY, result_);
		return result_;
	}

	// IDENTIFIER_KEY+
	private static boolean wordBody_1(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "wordBody_1")) return false;
		boolean result_ = false;
		Marker marker_ = enter_section_(builder_);
		result_ = consumeToken(builder_, IDENTIFIER_KEY);
		int pos_ = current_position_(builder_);
		while (result_) {
			if (!consumeToken(builder_, IDENTIFIER_KEY)) break;
			if (!empty_element_parsed_guard_(builder_, "wordBody_1", pos_)) break;
			pos_ = current_position_(builder_);
		}
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

}
