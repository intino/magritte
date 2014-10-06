// Generated from /Users/oroncal/workspace/tara/rt/src/siani/tara/compiler/parser/antlr/TaraGrammar.g4 by ANTLR 4.4.1-dev

package siani.tara.compiler.parser.antlr;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link TaraGrammar}.
 */
public interface TaraGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#body}.
	 * @param ctx the parse tree
	 */
	void enterBody(@NotNull TaraGrammar.BodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#body}.
	 * @param ctx the parse tree
	 */
	void exitBody(@NotNull TaraGrammar.BodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#portAttribute}.
	 * @param ctx the parse tree
	 */
	void enterPortAttribute(@NotNull TaraGrammar.PortAttributeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#portAttribute}.
	 * @param ctx the parse tree
	 */
	void exitPortAttribute(@NotNull TaraGrammar.PortAttributeContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#integerAttribute}.
	 * @param ctx the parse tree
	 */
	void enterIntegerAttribute(@NotNull TaraGrammar.IntegerAttributeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#integerAttribute}.
	 * @param ctx the parse tree
	 */
	void exitIntegerAttribute(@NotNull TaraGrammar.IntegerAttributeContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#concept}.
	 * @param ctx the parse tree
	 */
	void enterConcept(@NotNull TaraGrammar.ConceptContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#concept}.
	 * @param ctx the parse tree
	 */
	void exitConcept(@NotNull TaraGrammar.ConceptContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#coordinateValue}.
	 * @param ctx the parse tree
	 */
	void enterCoordinateValue(@NotNull TaraGrammar.CoordinateValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#coordinateValue}.
	 * @param ctx the parse tree
	 */
	void exitCoordinateValue(@NotNull TaraGrammar.CoordinateValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#naturalValue}.
	 * @param ctx the parse tree
	 */
	void enterNaturalValue(@NotNull TaraGrammar.NaturalValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#naturalValue}.
	 * @param ctx the parse tree
	 */
	void exitNaturalValue(@NotNull TaraGrammar.NaturalValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#measure}.
	 * @param ctx the parse tree
	 */
	void enterMeasure(@NotNull TaraGrammar.MeasureContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#measure}.
	 * @param ctx the parse tree
	 */
	void exitMeasure(@NotNull TaraGrammar.MeasureContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#root}.
	 * @param ctx the parse tree
	 */
	void enterRoot(@NotNull TaraGrammar.RootContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#root}.
	 * @param ctx the parse tree
	 */
	void exitRoot(@NotNull TaraGrammar.RootContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#stringValue}.
	 * @param ctx the parse tree
	 */
	void enterStringValue(@NotNull TaraGrammar.StringValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#stringValue}.
	 * @param ctx the parse tree
	 */
	void exitStringValue(@NotNull TaraGrammar.StringValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#attribute}.
	 * @param ctx the parse tree
	 */
	void enterAttribute(@NotNull TaraGrammar.AttributeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#attribute}.
	 * @param ctx the parse tree
	 */
	void exitAttribute(@NotNull TaraGrammar.AttributeContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#doubleValue}.
	 * @param ctx the parse tree
	 */
	void enterDoubleValue(@NotNull TaraGrammar.DoubleValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#doubleValue}.
	 * @param ctx the parse tree
	 */
	void exitDoubleValue(@NotNull TaraGrammar.DoubleValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#attributeType}.
	 * @param ctx the parse tree
	 */
	void enterAttributeType(@NotNull TaraGrammar.AttributeTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#attributeType}.
	 * @param ctx the parse tree
	 */
	void exitAttributeType(@NotNull TaraGrammar.AttributeTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#metaidentifier}.
	 * @param ctx the parse tree
	 */
	void enterMetaidentifier(@NotNull TaraGrammar.MetaidentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#metaidentifier}.
	 * @param ctx the parse tree
	 */
	void exitMetaidentifier(@NotNull TaraGrammar.MetaidentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#anImport}.
	 * @param ctx the parse tree
	 */
	void enterAnImport(@NotNull TaraGrammar.AnImportContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#anImport}.
	 * @param ctx the parse tree
	 */
	void exitAnImport(@NotNull TaraGrammar.AnImportContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#doubleAttribute}.
	 * @param ctx the parse tree
	 */
	void enterDoubleAttribute(@NotNull TaraGrammar.DoubleAttributeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#doubleAttribute}.
	 * @param ctx the parse tree
	 */
	void exitDoubleAttribute(@NotNull TaraGrammar.DoubleAttributeContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#booleanValue}.
	 * @param ctx the parse tree
	 */
	void enterBooleanValue(@NotNull TaraGrammar.BooleanValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#booleanValue}.
	 * @param ctx the parse tree
	 */
	void exitBooleanValue(@NotNull TaraGrammar.BooleanValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#naturalAttribute}.
	 * @param ctx the parse tree
	 */
	void enterNaturalAttribute(@NotNull TaraGrammar.NaturalAttributeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#naturalAttribute}.
	 * @param ctx the parse tree
	 */
	void exitNaturalAttribute(@NotNull TaraGrammar.NaturalAttributeContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#stringAttribute}.
	 * @param ctx the parse tree
	 */
	void enterStringAttribute(@NotNull TaraGrammar.StringAttributeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#stringAttribute}.
	 * @param ctx the parse tree
	 */
	void exitStringAttribute(@NotNull TaraGrammar.StringAttributeContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#parameter}.
	 * @param ctx the parse tree
	 */
	void enterParameter(@NotNull TaraGrammar.ParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#parameter}.
	 * @param ctx the parse tree
	 */
	void exitParameter(@NotNull TaraGrammar.ParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#codeValue}.
	 * @param ctx the parse tree
	 */
	void enterCodeValue(@NotNull TaraGrammar.CodeValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#codeValue}.
	 * @param ctx the parse tree
	 */
	void exitCodeValue(@NotNull TaraGrammar.CodeValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#parameterList}.
	 * @param ctx the parse tree
	 */
	void enterParameterList(@NotNull TaraGrammar.ParameterListContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#parameterList}.
	 * @param ctx the parse tree
	 */
	void exitParameterList(@NotNull TaraGrammar.ParameterListContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#integerValue}.
	 * @param ctx the parse tree
	 */
	void enterIntegerValue(@NotNull TaraGrammar.IntegerValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#integerValue}.
	 * @param ctx the parse tree
	 */
	void exitIntegerValue(@NotNull TaraGrammar.IntegerValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#coordinateAttribute}.
	 * @param ctx the parse tree
	 */
	void enterCoordinateAttribute(@NotNull TaraGrammar.CoordinateAttributeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#coordinateAttribute}.
	 * @param ctx the parse tree
	 */
	void exitCoordinateAttribute(@NotNull TaraGrammar.CoordinateAttributeContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#metaWordNames}.
	 * @param ctx the parse tree
	 */
	void enterMetaWordNames(@NotNull TaraGrammar.MetaWordNamesContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#metaWordNames}.
	 * @param ctx the parse tree
	 */
	void exitMetaWordNames(@NotNull TaraGrammar.MetaWordNamesContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#hierarchy}.
	 * @param ctx the parse tree
	 */
	void enterHierarchy(@NotNull TaraGrammar.HierarchyContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#hierarchy}.
	 * @param ctx the parse tree
	 */
	void exitHierarchy(@NotNull TaraGrammar.HierarchyContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#parameters}.
	 * @param ctx the parse tree
	 */
	void enterParameters(@NotNull TaraGrammar.ParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#parameters}.
	 * @param ctx the parse tree
	 */
	void exitParameters(@NotNull TaraGrammar.ParametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#metaWord}.
	 * @param ctx the parse tree
	 */
	void enterMetaWord(@NotNull TaraGrammar.MetaWordContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#metaWord}.
	 * @param ctx the parse tree
	 */
	void exitMetaWord(@NotNull TaraGrammar.MetaWordContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#signature}.
	 * @param ctx the parse tree
	 */
	void enterSignature(@NotNull TaraGrammar.SignatureContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#signature}.
	 * @param ctx the parse tree
	 */
	void exitSignature(@NotNull TaraGrammar.SignatureContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#dateAttribute}.
	 * @param ctx the parse tree
	 */
	void enterDateAttribute(@NotNull TaraGrammar.DateAttributeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#dateAttribute}.
	 * @param ctx the parse tree
	 */
	void exitDateAttribute(@NotNull TaraGrammar.DateAttributeContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#booleanAttribute}.
	 * @param ctx the parse tree
	 */
	void enterBooleanAttribute(@NotNull TaraGrammar.BooleanAttributeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#booleanAttribute}.
	 * @param ctx the parse tree
	 */
	void exitBooleanAttribute(@NotNull TaraGrammar.BooleanAttributeContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#resource}.
	 * @param ctx the parse tree
	 */
	void enterResource(@NotNull TaraGrammar.ResourceContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#resource}.
	 * @param ctx the parse tree
	 */
	void exitResource(@NotNull TaraGrammar.ResourceContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#identifierReference}.
	 * @param ctx the parse tree
	 */
	void enterIdentifierReference(@NotNull TaraGrammar.IdentifierReferenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#identifierReference}.
	 * @param ctx the parse tree
	 */
	void exitIdentifierReference(@NotNull TaraGrammar.IdentifierReferenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#wordNames}.
	 * @param ctx the parse tree
	 */
	void enterWordNames(@NotNull TaraGrammar.WordNamesContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#wordNames}.
	 * @param ctx the parse tree
	 */
	void exitWordNames(@NotNull TaraGrammar.WordNamesContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#conceptReference}.
	 * @param ctx the parse tree
	 */
	void enterConceptReference(@NotNull TaraGrammar.ConceptReferenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#conceptReference}.
	 * @param ctx the parse tree
	 */
	void exitConceptReference(@NotNull TaraGrammar.ConceptReferenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#imports}.
	 * @param ctx the parse tree
	 */
	void enterImports(@NotNull TaraGrammar.ImportsContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#imports}.
	 * @param ctx the parse tree
	 */
	void exitImports(@NotNull TaraGrammar.ImportsContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#header}.
	 * @param ctx the parse tree
	 */
	void enterHeader(@NotNull TaraGrammar.HeaderContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#header}.
	 * @param ctx the parse tree
	 */
	void exitHeader(@NotNull TaraGrammar.HeaderContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#reference}.
	 * @param ctx the parse tree
	 */
	void enterReference(@NotNull TaraGrammar.ReferenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#reference}.
	 * @param ctx the parse tree
	 */
	void exitReference(@NotNull TaraGrammar.ReferenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#headerReference}.
	 * @param ctx the parse tree
	 */
	void enterHeaderReference(@NotNull TaraGrammar.HeaderReferenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#headerReference}.
	 * @param ctx the parse tree
	 */
	void exitHeaderReference(@NotNull TaraGrammar.HeaderReferenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#facetApply}.
	 * @param ctx the parse tree
	 */
	void enterFacetApply(@NotNull TaraGrammar.FacetApplyContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#facetApply}.
	 * @param ctx the parse tree
	 */
	void exitFacetApply(@NotNull TaraGrammar.FacetApplyContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#explicit}.
	 * @param ctx the parse tree
	 */
	void enterExplicit(@NotNull TaraGrammar.ExplicitContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#explicit}.
	 * @param ctx the parse tree
	 */
	void exitExplicit(@NotNull TaraGrammar.ExplicitContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#varInit}.
	 * @param ctx the parse tree
	 */
	void enterVarInit(@NotNull TaraGrammar.VarInitContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#varInit}.
	 * @param ctx the parse tree
	 */
	void exitVarInit(@NotNull TaraGrammar.VarInitContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#doc}.
	 * @param ctx the parse tree
	 */
	void enterDoc(@NotNull TaraGrammar.DocContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#doc}.
	 * @param ctx the parse tree
	 */
	void exitDoc(@NotNull TaraGrammar.DocContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#box}.
	 * @param ctx the parse tree
	 */
	void enterBox(@NotNull TaraGrammar.BoxContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#box}.
	 * @param ctx the parse tree
	 */
	void exitBox(@NotNull TaraGrammar.BoxContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#word}.
	 * @param ctx the parse tree
	 */
	void enterWord(@NotNull TaraGrammar.WordContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#word}.
	 * @param ctx the parse tree
	 */
	void exitWord(@NotNull TaraGrammar.WordContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#annotations}.
	 * @param ctx the parse tree
	 */
	void enterAnnotations(@NotNull TaraGrammar.AnnotationsContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#annotations}.
	 * @param ctx the parse tree
	 */
	void exitAnnotations(@NotNull TaraGrammar.AnnotationsContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#facetTarget}.
	 * @param ctx the parse tree
	 */
	void enterFacetTarget(@NotNull TaraGrammar.FacetTargetContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#facetTarget}.
	 * @param ctx the parse tree
	 */
	void exitFacetTarget(@NotNull TaraGrammar.FacetTargetContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#dateValue}.
	 * @param ctx the parse tree
	 */
	void enterDateValue(@NotNull TaraGrammar.DateValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#dateValue}.
	 * @param ctx the parse tree
	 */
	void exitDateValue(@NotNull TaraGrammar.DateValueContext ctx);
}