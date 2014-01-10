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

@SuppressWarnings({ "SimplifiableIfStatement", "UnusedAssignment" })
public class TaraParser implements PsiParser {

	public static final Logger LOG_ = Logger.getInstance("TaraParser");

	public ASTNode parse(IElementType root_, PsiBuilder builder_) {
		boolean result_;
		builder_ = adapt_builder_(root_, builder_, this, null);
		Marker marker_ = enter_section_(builder_, 0, _COLLAPSE_, null);
		if (root_ == CHILD) {
			result_ = child(builder_, 0);
		} else if (root_ == CONCEPT_BODY) {
			result_ = conceptBody(builder_, 0);
		} else if (root_ == CONCEPT_DEFINITION) {
			result_ = conceptDefinition(builder_, 0);
		} else if (root_ == CONCEPT_SIGNATURE) {
			result_ = conceptSignature(builder_, 0);
		} else if (root_ == IDENTIFIER) {
			result_ = identifier(builder_, 0);
		} else if (root_ == INTENTION) {
			result_ = intention(builder_, 0);
		} else if (root_ == PRIMITIVE) {
			result_ = primitive(builder_, 0);
		} else if (root_ == PRIMITIVE_TYPE) {
			result_ = primitive_type(builder_, 0);
		} else if (root_ == PROPERTY) {
			result_ = property(builder_, 0);
		} else if (root_ == REFERENCE_STATEMENT) {
			result_ = referenceStatement(builder_, 0);
		} else if (root_ == STATEMENT) {
			result_ = statement(builder_, 0);
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
	// HAS identifier RANGE? ANNOTATION* conceptBody*
	public static boolean child(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "child")) return false;
		if (!nextTokenIs(builder_, HAS)) return false;
		boolean result_ = false;
		Marker marker_ = enter_section_(builder_);
		result_ = consumeToken(builder_, HAS);
		result_ = result_ && identifier(builder_, level_ + 1);
		result_ = result_ && child_2(builder_, level_ + 1);
		result_ = result_ && child_3(builder_, level_ + 1);
		result_ = result_ && child_4(builder_, level_ + 1);
		exit_section_(builder_, marker_, CHILD, result_);
		return result_;
	}

	// RANGE?
	private static boolean child_2(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "child_2")) return false;
		consumeToken(builder_, RANGE);
		return true;
	}

	// ANNOTATION*
	private static boolean child_3(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "child_3")) return false;
		int pos_ = current_position_(builder_);
		while (true) {
			if (!consumeToken(builder_, ANNOTATION)) break;
			if (!empty_element_parsed_guard_(builder_, "child_3", pos_)) break;
			pos_ = current_position_(builder_);
		}
		return true;
	}

	// conceptBody*
	private static boolean child_4(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "child_4")) return false;
		int pos_ = current_position_(builder_);
		while (true) {
			if (!conceptBody(builder_, level_ + 1)) break;
			if (!empty_element_parsed_guard_(builder_, "child_4", pos_)) break;
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
	// conceptSignature conceptBody?
	public static boolean conceptDefinition(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "conceptDefinition")) return false;
		if (!nextTokenIs(builder_, CONCEPT)) return false;
		boolean result_ = false;
		Marker marker_ = enter_section_(builder_);
		result_ = conceptSignature(builder_, level_ + 1);
		result_ = result_ && conceptDefinition_1(builder_, level_ + 1);
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
	// CONCEPT MODIFIERS? identifier (IS identifier)? ANNOTATION*
	public static boolean conceptSignature(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "conceptSignature")) return false;
		if (!nextTokenIs(builder_, CONCEPT)) return false;
		boolean result_ = false;
		Marker marker_ = enter_section_(builder_);
		result_ = consumeToken(builder_, CONCEPT);
		result_ = result_ && conceptSignature_1(builder_, level_ + 1);
		result_ = result_ && identifier(builder_, level_ + 1);
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

	// (IS identifier)?
	private static boolean conceptSignature_3(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "conceptSignature_3")) return false;
		conceptSignature_3_0(builder_, level_ + 1);
		return true;
	}

	// IS identifier
	private static boolean conceptSignature_3_0(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "conceptSignature_3_0")) return false;
		boolean result_ = false;
		Marker marker_ = enter_section_(builder_);
		result_ = consumeToken(builder_, IS);
		result_ = result_ && identifier(builder_, level_ + 1);
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	// ANNOTATION*
	private static boolean conceptSignature_4(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "conceptSignature_4")) return false;
		int pos_ = current_position_(builder_);
		while (true) {
			if (!consumeToken(builder_, ANNOTATION)) break;
			if (!empty_element_parsed_guard_(builder_, "conceptSignature_4", pos_)) break;
			pos_ = current_position_(builder_);
		}
		return true;
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
	// USE identifier COLON identifier (LEFT_P identifier RIGHT_P)?
	public static boolean intention(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "intention")) return false;
		if (!nextTokenIs(builder_, USE)) return false;
		boolean result_ = false;
		Marker marker_ = enter_section_(builder_);
		result_ = consumeToken(builder_, USE);
		result_ = result_ && identifier(builder_, level_ + 1);
		result_ = result_ && consumeToken(builder_, COLON);
		result_ = result_ && identifier(builder_, level_ + 1);
		result_ = result_ && intention_4(builder_, level_ + 1);
		exit_section_(builder_, marker_, INTENTION, result_);
		return result_;
	}

	// (LEFT_P identifier RIGHT_P)?
	private static boolean intention_4(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "intention_4")) return false;
		intention_4_0(builder_, level_ + 1);
		return true;
	}

	// LEFT_P identifier RIGHT_P
	private static boolean intention_4_0(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "intention_4_0")) return false;
		boolean result_ = false;
		Marker marker_ = enter_section_(builder_);
		result_ = consumeToken(builder_, LEFT_P);
		result_ = result_ && identifier(builder_, level_ + 1);
		result_ = result_ && consumeToken(builder_, RIGHT_P);
		exit_section_(builder_, marker_, null, result_);
		return result_;
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
	// primitive_type identifier (ASSIGN  primitive)? ANONYMOUS?
	public static boolean property(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "property")) return false;
		boolean result_ = false;
		Marker marker_ = enter_section_(builder_, level_, _NONE_, "<property>");
		result_ = primitive_type(builder_, level_ + 1);
		result_ = result_ && identifier(builder_, level_ + 1);
		result_ = result_ && property_2(builder_, level_ + 1);
		result_ = result_ && property_3(builder_, level_ + 1);
		exit_section_(builder_, level_, marker_, PROPERTY, result_, false, null);
		return result_;
	}

	// (ASSIGN  primitive)?
	private static boolean property_2(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "property_2")) return false;
		property_2_0(builder_, level_ + 1);
		return true;
	}

	// ASSIGN  primitive
	private static boolean property_2_0(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "property_2_0")) return false;
		boolean result_ = false;
		Marker marker_ = enter_section_(builder_);
		result_ = consumeToken(builder_, ASSIGN);
		result_ = result_ && primitive(builder_, level_ + 1);
		exit_section_(builder_, marker_, null, result_);
		return result_;
	}

	// ANONYMOUS?
	private static boolean property_3(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "property_3")) return false;
		consumeToken(builder_, ANONYMOUS);
		return true;
	}

	/* ********************************************************** */
	// REF identifier IDENTIFIER_KEY
	public static boolean referenceStatement(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "referenceStatement")) return false;
		if (!nextTokenIs(builder_, REF)) return false;
		boolean result_ = false;
		Marker marker_ = enter_section_(builder_);
		result_ = consumeToken(builder_, REF);
		result_ = result_ && identifier(builder_, level_ + 1);
		result_ = result_ && consumeToken(builder_, IDENTIFIER_KEY);
		exit_section_(builder_, marker_, REFERENCE_STATEMENT, result_);
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
	// property | child | referenceStatement | intention
	public static boolean statement(PsiBuilder builder_, int level_) {
		if (!recursion_guard_(builder_, level_, "statement")) return false;
		boolean result_ = false;
		Marker marker_ = enter_section_(builder_, level_, _NONE_, "<statement>");
		result_ = property(builder_, level_ + 1);
		if (!result_) result_ = child(builder_, level_ + 1);
		if (!result_) result_ = referenceStatement(builder_, level_ + 1);
		if (!result_) result_ = intention(builder_, level_ + 1);
		exit_section_(builder_, level_, marker_, STATEMENT, result_, false, null);
		return result_;
	}

}
