// This is a generated file. Not intended for manual editing.
package siani.tara.intellij.lang.psi;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import siani.tara.intellij.lang.psi.impl.*;

public interface TaraTypes {

	IElementType ADDRESS = new TaraElementType("ADDRESS");
	IElementType ANNOTATION = new TaraElementType("ANNOTATION");
	IElementType ANNOTATIONS = new TaraElementType("ANNOTATIONS");
	IElementType ANNOTATIONS_AND_FACETS = new TaraElementType("ANNOTATIONS_AND_FACETS");
	IElementType AN_IMPORT = new TaraElementType("AN_IMPORT");
	IElementType ATTRIBUTE_TYPE = new TaraElementType("ATTRIBUTE_TYPE");
	IElementType BODY = new TaraElementType("BODY");
	IElementType BOOLEAN_ATTRIBUTE = new TaraElementType("BOOLEAN_ATTRIBUTE");
	IElementType BOOLEAN_VALUE = new TaraElementType("BOOLEAN_VALUE");
	IElementType CONCEPT = new TaraElementType("CONCEPT");
	IElementType CONCEPT_REFERENCE = new TaraElementType("CONCEPT_REFERENCE");
	IElementType COUNT = new TaraElementType("COUNT");
	IElementType DATE_ATTRIBUTE = new TaraElementType("DATE_ATTRIBUTE");
	IElementType DATE_VALUE = new TaraElementType("DATE_VALUE");
	IElementType DOC = new TaraElementType("DOC");
	IElementType DOUBLE_ATTRIBUTE = new TaraElementType("DOUBLE_ATTRIBUTE");
	IElementType DOUBLE_MEASURE = new TaraElementType("DOUBLE_MEASURE");
	IElementType DOUBLE_VALUE = new TaraElementType("DOUBLE_VALUE");
	IElementType EMPTY_FIELD = new TaraElementType("EMPTY_FIELD");
	IElementType EXPLICIT_PARAMETER = new TaraElementType("EXPLICIT_PARAMETER");
	IElementType FACET_APPLY = new TaraElementType("FACET_APPLY");
	IElementType FACET_TARGET = new TaraElementType("FACET_TARGET");
	IElementType HEADER_REFERENCE = new TaraElementType("HEADER_REFERENCE");
	IElementType IDENTIFIER = new TaraElementType("IDENTIFIER");
	IElementType IDENTIFIER_REFERENCE = new TaraElementType("IDENTIFIER_REFERENCE");
	IElementType IMPLICIT_PARAMETER = new TaraElementType("IMPLICIT_PARAMETER");
	IElementType IMPORTS = new TaraElementType("IMPORTS");
	IElementType INTEGER_ATTRIBUTE = new TaraElementType("INTEGER_ATTRIBUTE");
	IElementType INTEGER_VALUE = new TaraElementType("INTEGER_VALUE");
	IElementType LINK_VALUE = new TaraElementType("LINK_VALUE");
	IElementType MEASURE_ATTRIBUTE = new TaraElementType("MEASURE_ATTRIBUTE");
	IElementType MEASURE_TYPE = new TaraElementType("MEASURE_TYPE");
	IElementType MEASURE_VALUE = new TaraElementType("MEASURE_VALUE");
	IElementType META_IDENTIFIER = new TaraElementType("META_IDENTIFIER");
	IElementType META_WORD = new TaraElementType("META_WORD");
	IElementType NATURAL_ATTRIBUTE = new TaraElementType("NATURAL_ATTRIBUTE");
	IElementType NATURAL_VALUE = new TaraElementType("NATURAL_VALUE");
	IElementType PARAMETERS = new TaraElementType("PARAMETERS");
	IElementType PARAMETER_VALUE = new TaraElementType("PARAMETER_VALUE");
	IElementType RATIO_ATTRIBUTE = new TaraElementType("RATIO_ATTRIBUTE");
	IElementType REFERENCE_ATTRIBUTE = new TaraElementType("REFERENCE_ATTRIBUTE");
	IElementType RESOURCE = new TaraElementType("RESOURCE");
	IElementType SIGNATURE = new TaraElementType("SIGNATURE");
	IElementType STRING_ATTRIBUTE = new TaraElementType("STRING_ATTRIBUTE");
	IElementType STRING_VALUE = new TaraElementType("STRING_VALUE");
	IElementType VARIABLE = new TaraElementType("VARIABLE");
	IElementType VARIABLE_TYPE = new TaraElementType("VARIABLE_TYPE");
	IElementType VAR_INIT = new TaraElementType("VAR_INIT");
	IElementType VAR_INIT_VALUE = new TaraElementType("VAR_INIT_VALUE");
	IElementType WORD = new TaraElementType("WORD");

	IElementType ABSTRACT = new TaraTokenType("ABSTRACT");
	IElementType ADDRESSED = new TaraTokenType("ADDRESSED");
	IElementType ADDRESS_VALUE = new TaraTokenType("ADDRESS_VALUE");
	IElementType AGGREGATED = new TaraTokenType("AGGREGATED");
	IElementType ALWAYS = new TaraTokenType("ALWAYS");
	IElementType AS = new TaraTokenType("AS");
	IElementType BOOLEAN_TYPE = new TaraTokenType("BOOLEAN_TYPE");
	IElementType BOOLEAN_VALUE_KEY = new TaraTokenType("BOOLEAN_VALUE_KEY");
	IElementType CASE = new TaraTokenType("CASE");
	IElementType COLON = new TaraTokenType("COLON");
	IElementType COMMA = new TaraTokenType("COMMA");
	IElementType COMPONENT = new TaraTokenType("COMPONENT");
	IElementType DATE_TYPE = new TaraTokenType("DATE_TYPE");
	IElementType DATE_VALUE_KEY = new TaraTokenType("DATE_VALUE_KEY");
	IElementType DEDENT = new TaraTokenType("DEDENT");
	IElementType DOC_LINE = new TaraTokenType("DOC_LINE");
	IElementType DOT = new TaraTokenType("DOT");
	IElementType DOUBLE_TYPE = new TaraTokenType("DOUBLE_TYPE");
	IElementType DOUBLE_VALUE_KEY = new TaraTokenType("DOUBLE_VALUE_KEY");
	IElementType EMPTY_REF = new TaraTokenType("EMPTY_REF");
	IElementType EQUALS = new TaraTokenType("EQUALS");
	IElementType EXTENDS = new TaraTokenType("EXTENDS");
	IElementType FACET = new TaraTokenType("FACET");
	IElementType HAS = new TaraTokenType("HAS");
	IElementType IDENTIFIER_KEY = new TaraTokenType("IDENTIFIER_KEY");
	IElementType INLINE = new TaraTokenType("INLINE");
	IElementType INTENTION = new TaraTokenType("INTENTION");
	IElementType INT_TYPE = new TaraTokenType("INT_TYPE");
	IElementType IS = new TaraTokenType("IS");
	IElementType LEFT_PARENTHESIS = new TaraTokenType("LEFT_PARENTHESIS");
	IElementType LEFT_SQUARE = new TaraTokenType("LEFT_SQUARE");
	IElementType LIST = new TaraTokenType("LIST");
	IElementType LOCAL = new TaraTokenType("LOCAL");
	IElementType MEASURE_TYPE_KEY = new TaraTokenType("MEASURE_TYPE_KEY");
	IElementType MEASURE_VALUE_KEY = new TaraTokenType("MEASURE_VALUE_KEY");
	IElementType METAIDENTIFIER_KEY = new TaraTokenType("METAIDENTIFIER_KEY");
	IElementType METAMODEL = new TaraTokenType("METAMODEL");
	IElementType NAMED = new TaraTokenType("NAMED");
	IElementType NATURAL_TYPE = new TaraTokenType("NATURAL_TYPE");
	IElementType NATURAL_VALUE_KEY = new TaraTokenType("NATURAL_VALUE_KEY");
	IElementType NEGATIVE_VALUE_KEY = new TaraTokenType("NEGATIVE_VALUE_KEY");
	IElementType NEWLINE = new TaraTokenType("NEWLINE");
	IElementType NEW_LINE_INDENT = TokenType.NEW_LINE_INDENT;
	IElementType ON = new TaraTokenType("ON");
	IElementType PLUS = new TaraTokenType("PLUS");
	IElementType PROPERTY = new TaraTokenType("PROPERTY");
	IElementType RATIO_TYPE = new TaraTokenType("RATIO_TYPE");
	IElementType READONLY = new TaraTokenType("READONLY");
	IElementType REQUIRED = new TaraTokenType("REQUIRED");
	IElementType RESOURCE_KEY = new TaraTokenType("RESOURCE_KEY");
	IElementType RIGHT_PARENTHESIS = new TaraTokenType("RIGHT_PARENTHESIS");
	IElementType RIGHT_SQUARE = new TaraTokenType("RIGHT_SQUARE");
	IElementType ROOT = new TaraTokenType("ROOT");
	IElementType SINGLE = new TaraTokenType("SINGLE");
	IElementType STAR = new TaraTokenType("STAR");
	IElementType STRING_MULTILINE_VALUE_KEY = new TaraTokenType("STRING_MULTILINE_VALUE_KEY");
	IElementType STRING_TYPE = new TaraTokenType("STRING_TYPE");
	IElementType STRING_VALUE_KEY = new TaraTokenType("STRING_VALUE_KEY");
	IElementType SUB = new TaraTokenType("SUB");
	IElementType TERMINAL = new TaraTokenType("TERMINAL");
	IElementType USE_KEY = new TaraTokenType("USE_KEY");
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
			} else if (type == ANNOTATIONS_AND_FACETS) {
				return new TaraAnnotationsAndFacetsImpl(node);
			} else if (type == AN_IMPORT) {
				return new TaraAnImportImpl(node);
			} else if (type == ATTRIBUTE_TYPE) {
				return new TaraAttributeTypeImpl(node);
			} else if (type == BODY) {
				return new TaraBodyImpl(node);
			} else if (type == BOOLEAN_ATTRIBUTE) {
				return new TaraBooleanAttributeImpl(node);
			} else if (type == BOOLEAN_VALUE) {
				return new TaraBooleanValueImpl(node);
			} else if (type == CONCEPT) {
				return new TaraConceptImpl(node);
			} else if (type == CONCEPT_REFERENCE) {
				return new TaraConceptReferenceImpl(node);
			} else if (type == COUNT) {
				return new TaraCountImpl(node);
			} else if (type == DATE_ATTRIBUTE) {
				return new TaraDateAttributeImpl(node);
			} else if (type == DATE_VALUE) {
				return new TaraDateValueImpl(node);
			} else if (type == DOC) {
				return new TaraDocImpl(node);
			} else if (type == DOUBLE_ATTRIBUTE) {
				return new TaraDoubleAttributeImpl(node);
			} else if (type == DOUBLE_MEASURE) {
				return new TaraDoubleMeasureImpl(node);
			} else if (type == DOUBLE_VALUE) {
				return new TaraDoubleValueImpl(node);
			} else if (type == EMPTY_FIELD) {
				return new TaraEmptyFieldImpl(node);
			} else if (type == EXPLICIT_PARAMETER) {
				return new TaraExplicitParameterImpl(node);
			} else if (type == FACET_APPLY) {
				return new TaraFacetApplyImpl(node);
			} else if (type == FACET_TARGET) {
				return new TaraFacetTargetImpl(node);
			} else if (type == HEADER_REFERENCE) {
				return new TaraHeaderReferenceImpl(node);
			} else if (type == IDENTIFIER) {
				return new TaraIdentifierImpl(node);
			} else if (type == IDENTIFIER_REFERENCE) {
				return new TaraIdentifierReferenceImpl(node);
			} else if (type == IMPLICIT_PARAMETER) {
				return new TaraImplicitParameterImpl(node);
			} else if (type == IMPORTS) {
				return new TaraImportsImpl(node);
			} else if (type == INTEGER_ATTRIBUTE) {
				return new TaraIntegerAttributeImpl(node);
			} else if (type == INTEGER_VALUE) {
				return new TaraIntegerValueImpl(node);
			} else if (type == LINK_VALUE) {
				return new TaraLinkValueImpl(node);
			} else if (type == MEASURE_ATTRIBUTE) {
				return new TaraMeasureAttributeImpl(node);
			} else if (type == MEASURE_TYPE) {
				return new TaraMeasureTypeImpl(node);
			} else if (type == MEASURE_VALUE) {
				return new TaraMeasureValueImpl(node);
			} else if (type == META_IDENTIFIER) {
				return new TaraMetaIdentifierImpl(node);
			} else if (type == META_WORD) {
				return new TaraMetaWordImpl(node);
			} else if (type == NATURAL_ATTRIBUTE) {
				return new TaraNaturalAttributeImpl(node);
			} else if (type == NATURAL_VALUE) {
				return new TaraNaturalValueImpl(node);
			} else if (type == PARAMETERS) {
				return new TaraParametersImpl(node);
			} else if (type == PARAMETER_VALUE) {
				return new TaraParameterValueImpl(node);
			} else if (type == RATIO_ATTRIBUTE) {
				return new TaraRatioAttributeImpl(node);
			} else if (type == REFERENCE_ATTRIBUTE) {
				return new TaraReferenceAttributeImpl(node);
			} else if (type == RESOURCE) {
				return new TaraResourceImpl(node);
			} else if (type == SIGNATURE) {
				return new TaraSignatureImpl(node);
			} else if (type == STRING_ATTRIBUTE) {
				return new TaraStringAttributeImpl(node);
			} else if (type == STRING_VALUE) {
				return new TaraStringValueImpl(node);
			} else if (type == VARIABLE) {
				return new TaraVariableImpl(node);
			} else if (type == VARIABLE_TYPE) {
				return new TaraVariableTypeImpl(node);
			} else if (type == VAR_INIT) {
				return new TaraVarInitImpl(node);
			} else if (type == VAR_INIT_VALUE) {
				return new TaraVarInitValueImpl(node);
			} else if (type == WORD) {
				return new TaraWordImpl(node);
			}
			throw new AssertionError("Unknown element type: " + type);
		}
	}
}
