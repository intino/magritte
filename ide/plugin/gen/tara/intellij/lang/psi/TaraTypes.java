// This is a generated file. Not intended for manual editing.
package tara.intellij.lang.psi;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import tara.intellij.lang.psi.impl.*;

public interface TaraTypes {

	IElementType ADDRESS = new TaraElementType("ADDRESS");
	IElementType ANNOTATION = new TaraElementType("ANNOTATION");
	IElementType ANNOTATIONS = new TaraElementType("ANNOTATIONS");
	IElementType AN_IMPORT = new TaraElementType("AN_IMPORT");
	IElementType ATTRIBUTE_TYPE = new TaraElementType("ATTRIBUTE_TYPE");
	IElementType BODY = new TaraElementType("BODY");
	IElementType BOOLEAN_VALUE = new TaraElementType("BOOLEAN_VALUE");
	IElementType CONSTRAINT = new TaraElementType("CONSTRAINT");
	IElementType CONTRACT = new TaraElementType("CONTRACT");
	IElementType COUNT = new TaraElementType("COUNT");
	IElementType DOC = new TaraElementType("DOC");
	IElementType DOUBLE_VALUE = new TaraElementType("DOUBLE_VALUE");
	IElementType DSL_DECLARATION = new TaraElementType("DSL_DECLARATION");
	IElementType EMPTY_FIELD = new TaraElementType("EMPTY_FIELD");
	IElementType EXPRESSION = new TaraElementType("EXPRESSION");
	IElementType FACET_APPLY = new TaraElementType("FACET_APPLY");
	IElementType FACET_TARGET = new TaraElementType("FACET_TARGET");
	IElementType FLAG = new TaraElementType("FLAG");
	IElementType FLAGS = new TaraElementType("FLAGS");
	IElementType HEADER_REFERENCE = new TaraElementType("HEADER_REFERENCE");
	IElementType IDENTIFIER = new TaraElementType("IDENTIFIER");
	IElementType IDENTIFIER_REFERENCE = new TaraElementType("IDENTIFIER_REFERENCE");
	IElementType IMPORTS = new TaraElementType("IMPORTS");
	IElementType INSTANCE_NAME = new TaraElementType("INSTANCE_NAME");
	IElementType INTEGER_VALUE = new TaraElementType("INTEGER_VALUE");
	IElementType MEASURE_VALUE = new TaraElementType("MEASURE_VALUE");
	IElementType META_IDENTIFIER = new TaraElementType("META_IDENTIFIER");
	IElementType NATURAL_VALUE = new TaraElementType("NATURAL_VALUE");
	IElementType NODE = new TaraElementType("NODE");
	IElementType NODE_REFERENCE = new TaraElementType("NODE_REFERENCE");
	IElementType PARAMETER = new TaraElementType("PARAMETER");
	IElementType PARAMETERS = new TaraElementType("PARAMETERS");
	IElementType SIGNATURE = new TaraElementType("SIGNATURE");
	IElementType STRING_VALUE = new TaraElementType("STRING_VALUE");
	IElementType TAGS = new TaraElementType("TAGS");
	IElementType TUPLE_VALUE = new TaraElementType("TUPLE_VALUE");
	IElementType VALUE = new TaraElementType("VALUE");
	IElementType VARIABLE = new TaraElementType("VARIABLE");
	IElementType VARIABLE_TYPE = new TaraElementType("VARIABLE_TYPE");
	IElementType VAR_INIT = new TaraElementType("VAR_INIT");

	IElementType ABSTRACT = new TaraTokenType("ABSTRACT");
	IElementType ADDRESS_VALUE = new TaraTokenType("ADDRESS_VALUE");
	IElementType ANY = new TaraTokenType("ANY");
	IElementType AS = new TaraTokenType("AS");
	IElementType BOOLEAN_TYPE = new TaraTokenType("BOOLEAN_TYPE");
	IElementType BOOLEAN_VALUE_KEY = new TaraTokenType("BOOLEAN_VALUE_KEY");
	IElementType CHARACTER = new TaraTokenType("CHARACTER");
	IElementType COLON = new TaraTokenType("COLON");
	IElementType COMMA = new TaraTokenType("COMMA");
	IElementType COMMENT = new TaraTokenType("COMMENT");
	IElementType DATE_TYPE = new TaraTokenType("DATE_TYPE");
	IElementType DEDENT = new TaraTokenType("DEDENT");
	IElementType DOC_LINE = new TaraTokenType("DOC_LINE");
	IElementType DOT = new TaraTokenType("DOT");
	IElementType DOUBLE_TYPE = new TaraTokenType("DOUBLE_TYPE");
	IElementType DOUBLE_VALUE_KEY = new TaraTokenType("DOUBLE_VALUE_KEY");
	IElementType DSL = new TaraTokenType("DSL");
	IElementType EMPTY_REF = new TaraTokenType("EMPTY_REF");
	IElementType ENCLOSED = new TaraTokenType("ENCLOSED");
	IElementType EQUALS = new TaraTokenType("EQUALS");
	IElementType EXPRESSION_BEGIN = new TaraTokenType("EXPRESSION_BEGIN");
	IElementType EXPRESSION_END = new TaraTokenType("EXPRESSION_END");
	IElementType EXTENDS = new TaraTokenType("EXTENDS");
	IElementType FACET = new TaraTokenType("FACET");
	IElementType FEATURE = new TaraTokenType("FEATURE");
	IElementType FINAL = new TaraTokenType("FINAL");
	IElementType HAS = new TaraTokenType("HAS");
	IElementType IDENTIFIER_KEY = new TaraTokenType("IDENTIFIER_KEY");
	IElementType INLINE = new TaraTokenType("INLINE");
	IElementType INTO = new TaraTokenType("INTO");
	IElementType INT_TYPE = new TaraTokenType("INT_TYPE");
	IElementType IS = new TaraTokenType("IS");
	IElementType LEFT_PARENTHESIS = new TaraTokenType("LEFT_PARENTHESIS");
	IElementType LEFT_SQUARE = new TaraTokenType("LEFT_SQUARE");
	IElementType LIST = new TaraTokenType("LIST");
	IElementType MAIN = new TaraTokenType("MAIN");
	IElementType MEASURE_TYPE_KEY = new TaraTokenType("MEASURE_TYPE_KEY");
	IElementType MEASURE_VALUE_KEY = new TaraTokenType("MEASURE_VALUE_KEY");
	IElementType METAIDENTIFIER_KEY = new TaraTokenType("METAIDENTIFIER_KEY");
	IElementType NAMED = new TaraTokenType("NAMED");
	IElementType NATIVE_TYPE = new TaraTokenType("NATIVE_TYPE");
	IElementType NATURAL_TYPE = new TaraTokenType("NATURAL_TYPE");
	IElementType NATURAL_VALUE_KEY = new TaraTokenType("NATURAL_VALUE_KEY");
	IElementType NEGATIVE_VALUE_KEY = new TaraTokenType("NEGATIVE_VALUE_KEY");
	IElementType NEWLINE = new TaraTokenType("NEWLINE");
	IElementType NEW_LINE_INDENT = TokenType.NEW_LINE_INDENT;
	IElementType ON = new TaraTokenType("ON");
	IElementType PRIVATE = new TaraTokenType("PRIVATE");
	IElementType PROTEO = new TaraTokenType("PROTEO");
	IElementType PROTOTYPE = new TaraTokenType("PROTOTYPE");
	IElementType QUOTE_BEGIN = new TaraTokenType("QUOTE_BEGIN");
	IElementType QUOTE_END = new TaraTokenType("QUOTE_END");
	IElementType RATIO_TYPE = new TaraTokenType("RATIO_TYPE");
	IElementType REQUIRED = new TaraTokenType("REQUIRED");
	IElementType RESOURCE_KEY = new TaraTokenType("RESOURCE_KEY");
	IElementType RIGHT_PARENTHESIS = new TaraTokenType("RIGHT_PARENTHESIS");
	IElementType RIGHT_SQUARE = new TaraTokenType("RIGHT_SQUARE");
	IElementType SINGLE = new TaraTokenType("SINGLE");
	IElementType STRING_TYPE = new TaraTokenType("STRING_TYPE");
	IElementType SUB = new TaraTokenType("SUB");
	IElementType TERMINAL = new TaraTokenType("TERMINAL");
	IElementType TIME_TYPE = new TaraTokenType("TIME_TYPE");
	IElementType TUPLE_TYPE = new TaraTokenType("TUPLE_TYPE");
	IElementType TYPE_TYPE = new TaraTokenType("TYPE_TYPE");
	IElementType USE = new TaraTokenType("USE");
	IElementType VAR = new TaraTokenType("VAR");
	IElementType WITH = new TaraTokenType("WITH");
	IElementType WORD_KEY = new TaraTokenType("WORD_KEY");

	class Factory {
		public static PsiElement createElement(ASTNode node) {
			IElementType type = node.getElementType();
			if (type == ADDRESS) {
				return new TaraAddressImpl(node);
			} else if (type == ANNOTATION) {
				return new TaraAnnotationImpl(node);
			} else if (type == ANNOTATIONS) {
				return new TaraAnnotationsImpl(node);
			} else if (type == AN_IMPORT) {
				return new TaraAnImportImpl(node);
			} else if (type == ATTRIBUTE_TYPE) {
				return new TaraAttributeTypeImpl(node);
			} else if (type == BODY) {
				return new TaraBodyImpl(node);
			} else if (type == BOOLEAN_VALUE) {
				return new TaraBooleanValueImpl(node);
			} else if (type == CONSTRAINT) {
				return new TaraConstraintImpl(node);
			} else if (type == CONTRACT) {
				return new TaraContractImpl(node);
			} else if (type == COUNT) {
				return new TaraCountImpl(node);
			} else if (type == DOC) {
				return new TaraDocImpl(node);
			} else if (type == DOUBLE_VALUE) {
				return new TaraDoubleValueImpl(node);
			} else if (type == DSL_DECLARATION) {
				return new TaraDslDeclarationImpl(node);
			} else if (type == EMPTY_FIELD) {
				return new TaraEmptyFieldImpl(node);
			} else if (type == EXPRESSION) {
				return new TaraExpressionImpl(node);
			} else if (type == FACET_APPLY) {
				return new TaraFacetApplyImpl(node);
			} else if (type == FACET_TARGET) {
				return new TaraFacetTargetImpl(node);
			} else if (type == FLAG) {
				return new TaraFlagImpl(node);
			} else if (type == FLAGS) {
				return new TaraFlagsImpl(node);
			} else if (type == HEADER_REFERENCE) {
				return new TaraHeaderReferenceImpl(node);
			} else if (type == IDENTIFIER) {
				return new TaraIdentifierImpl(node);
			} else if (type == IDENTIFIER_REFERENCE) {
				return new TaraIdentifierReferenceImpl(node);
			} else if (type == IMPORTS) {
				return new TaraImportsImpl(node);
			} else if (type == INSTANCE_NAME) {
				return new TaraInstanceNameImpl(node);
			} else if (type == INTEGER_VALUE) {
				return new TaraIntegerValueImpl(node);
			} else if (type == MEASURE_VALUE) {
				return new TaraMeasureValueImpl(node);
			} else if (type == META_IDENTIFIER) {
				return new TaraMetaIdentifierImpl(node);
			} else if (type == NATURAL_VALUE) {
				return new TaraNaturalValueImpl(node);
			} else if (type == NODE) {
				return new TaraNodeImpl(node);
			} else if (type == NODE_REFERENCE) {
				return new TaraNodeReferenceImpl(node);
			} else if (type == PARAMETER) {
				return new TaraParameterImpl(node);
			} else if (type == PARAMETERS) {
				return new TaraParametersImpl(node);
			} else if (type == SIGNATURE) {
				return new TaraSignatureImpl(node);
			} else if (type == STRING_VALUE) {
				return new TaraStringValueImpl(node);
			} else if (type == TAGS) {
				return new TaraTagsImpl(node);
			} else if (type == TUPLE_VALUE) {
				return new TaraTupleValueImpl(node);
			} else if (type == VALUE) {
				return new TaraValueImpl(node);
			} else if (type == VARIABLE) {
				return new TaraVariableImpl(node);
			} else if (type == VARIABLE_TYPE) {
				return new TaraVariableTypeImpl(node);
			} else if (type == VAR_INIT) {
				return new TaraVarInitImpl(node);
			}
			throw new AssertionError("Unknown element type: " + type);
		}
	}
}
