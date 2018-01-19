// Generated from /Users/oroncal/workspace/tara/core/language/src/io/intino/tara/lang/grammar/TaraGrammar.g4 by ANTLR 4.7
package io.intino.tara.lang.grammar;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link TaraGrammar}.
 */
public interface TaraGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#root}.
	 * @param ctx the parse tree
	 */
	void enterRoot(TaraGrammar.RootContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#root}.
	 * @param ctx the parse tree
	 */
	void exitRoot(TaraGrammar.RootContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#dslDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterDslDeclaration(TaraGrammar.DslDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#dslDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitDslDeclaration(TaraGrammar.DslDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#imports}.
	 * @param ctx the parse tree
	 */
	void enterImports(TaraGrammar.ImportsContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#imports}.
	 * @param ctx the parse tree
	 */
	void exitImports(TaraGrammar.ImportsContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#anImport}.
	 * @param ctx the parse tree
	 */
	void enterAnImport(TaraGrammar.AnImportContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#anImport}.
	 * @param ctx the parse tree
	 */
	void exitAnImport(TaraGrammar.AnImportContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#doc}.
	 * @param ctx the parse tree
	 */
	void enterDoc(TaraGrammar.DocContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#doc}.
	 * @param ctx the parse tree
	 */
	void exitDoc(TaraGrammar.DocContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#node}.
	 * @param ctx the parse tree
	 */
	void enterNode(TaraGrammar.NodeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#node}.
	 * @param ctx the parse tree
	 */
	void exitNode(TaraGrammar.NodeContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#signature}.
	 * @param ctx the parse tree
	 */
	void enterSignature(TaraGrammar.SignatureContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#signature}.
	 * @param ctx the parse tree
	 */
	void exitSignature(TaraGrammar.SignatureContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#parent}.
	 * @param ctx the parse tree
	 */
	void enterParent(TaraGrammar.ParentContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#parent}.
	 * @param ctx the parse tree
	 */
	void exitParent(TaraGrammar.ParentContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#parameters}.
	 * @param ctx the parse tree
	 */
	void enterParameters(TaraGrammar.ParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#parameters}.
	 * @param ctx the parse tree
	 */
	void exitParameters(TaraGrammar.ParametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#parameter}.
	 * @param ctx the parse tree
	 */
	void enterParameter(TaraGrammar.ParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#parameter}.
	 * @param ctx the parse tree
	 */
	void exitParameter(TaraGrammar.ParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#facets}.
	 * @param ctx the parse tree
	 */
	void enterFacets(TaraGrammar.FacetsContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#facets}.
	 * @param ctx the parse tree
	 */
	void exitFacets(TaraGrammar.FacetsContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#facet}.
	 * @param ctx the parse tree
	 */
	void enterFacet(TaraGrammar.FacetContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#facet}.
	 * @param ctx the parse tree
	 */
	void exitFacet(TaraGrammar.FacetContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#tableParameters}.
	 * @param ctx the parse tree
	 */
	void enterTableParameters(TaraGrammar.TableParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#tableParameters}.
	 * @param ctx the parse tree
	 */
	void exitTableParameters(TaraGrammar.TableParametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(TaraGrammar.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(TaraGrammar.ValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#body}.
	 * @param ctx the parse tree
	 */
	void enterBody(TaraGrammar.BodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#body}.
	 * @param ctx the parse tree
	 */
	void exitBody(TaraGrammar.BodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#facetTarget}.
	 * @param ctx the parse tree
	 */
	void enterFacetTarget(TaraGrammar.FacetTargetContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#facetTarget}.
	 * @param ctx the parse tree
	 */
	void exitFacetTarget(TaraGrammar.FacetTargetContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#nodeReference}.
	 * @param ctx the parse tree
	 */
	void enterNodeReference(TaraGrammar.NodeReferenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#nodeReference}.
	 * @param ctx the parse tree
	 */
	void exitNodeReference(TaraGrammar.NodeReferenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#with}.
	 * @param ctx the parse tree
	 */
	void enterWith(TaraGrammar.WithContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#with}.
	 * @param ctx the parse tree
	 */
	void exitWith(TaraGrammar.WithContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(TaraGrammar.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(TaraGrammar.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#bodyValue}.
	 * @param ctx the parse tree
	 */
	void enterBodyValue(TaraGrammar.BodyValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#bodyValue}.
	 * @param ctx the parse tree
	 */
	void exitBodyValue(TaraGrammar.BodyValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#variableType}.
	 * @param ctx the parse tree
	 */
	void enterVariableType(TaraGrammar.VariableTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#variableType}.
	 * @param ctx the parse tree
	 */
	void exitVariableType(TaraGrammar.VariableTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#ruleContainer}.
	 * @param ctx the parse tree
	 */
	void enterRuleContainer(TaraGrammar.RuleContainerContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#ruleContainer}.
	 * @param ctx the parse tree
	 */
	void exitRuleContainer(TaraGrammar.RuleContainerContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#ruleValue}.
	 * @param ctx the parse tree
	 */
	void enterRuleValue(TaraGrammar.RuleValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#ruleValue}.
	 * @param ctx the parse tree
	 */
	void exitRuleValue(TaraGrammar.RuleValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#classType}.
	 * @param ctx the parse tree
	 */
	void enterClassType(TaraGrammar.ClassTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#classType}.
	 * @param ctx the parse tree
	 */
	void exitClassType(TaraGrammar.ClassTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#range}.
	 * @param ctx the parse tree
	 */
	void enterRange(TaraGrammar.RangeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#range}.
	 * @param ctx the parse tree
	 */
	void exitRange(TaraGrammar.RangeContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#size}.
	 * @param ctx the parse tree
	 */
	void enterSize(TaraGrammar.SizeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#size}.
	 * @param ctx the parse tree
	 */
	void exitSize(TaraGrammar.SizeContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#sizeRange}.
	 * @param ctx the parse tree
	 */
	void enterSizeRange(TaraGrammar.SizeRangeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#sizeRange}.
	 * @param ctx the parse tree
	 */
	void exitSizeRange(TaraGrammar.SizeRangeContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#listRange}.
	 * @param ctx the parse tree
	 */
	void enterListRange(TaraGrammar.ListRangeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#listRange}.
	 * @param ctx the parse tree
	 */
	void exitListRange(TaraGrammar.ListRangeContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#methodReference}.
	 * @param ctx the parse tree
	 */
	void enterMethodReference(TaraGrammar.MethodReferenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#methodReference}.
	 * @param ctx the parse tree
	 */
	void exitMethodReference(TaraGrammar.MethodReferenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#stringValue}.
	 * @param ctx the parse tree
	 */
	void enterStringValue(TaraGrammar.StringValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#stringValue}.
	 * @param ctx the parse tree
	 */
	void exitStringValue(TaraGrammar.StringValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#booleanValue}.
	 * @param ctx the parse tree
	 */
	void enterBooleanValue(TaraGrammar.BooleanValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#booleanValue}.
	 * @param ctx the parse tree
	 */
	void exitBooleanValue(TaraGrammar.BooleanValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#tupleValue}.
	 * @param ctx the parse tree
	 */
	void enterTupleValue(TaraGrammar.TupleValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#tupleValue}.
	 * @param ctx the parse tree
	 */
	void exitTupleValue(TaraGrammar.TupleValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#integerValue}.
	 * @param ctx the parse tree
	 */
	void enterIntegerValue(TaraGrammar.IntegerValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#integerValue}.
	 * @param ctx the parse tree
	 */
	void exitIntegerValue(TaraGrammar.IntegerValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#doubleValue}.
	 * @param ctx the parse tree
	 */
	void enterDoubleValue(TaraGrammar.DoubleValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#doubleValue}.
	 * @param ctx the parse tree
	 */
	void exitDoubleValue(TaraGrammar.DoubleValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#metric}.
	 * @param ctx the parse tree
	 */
	void enterMetric(TaraGrammar.MetricContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#metric}.
	 * @param ctx the parse tree
	 */
	void exitMetric(TaraGrammar.MetricContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(TaraGrammar.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(TaraGrammar.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#tags}.
	 * @param ctx the parse tree
	 */
	void enterTags(TaraGrammar.TagsContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#tags}.
	 * @param ctx the parse tree
	 */
	void exitTags(TaraGrammar.TagsContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#annotations}.
	 * @param ctx the parse tree
	 */
	void enterAnnotations(TaraGrammar.AnnotationsContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#annotations}.
	 * @param ctx the parse tree
	 */
	void exitAnnotations(TaraGrammar.AnnotationsContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#annotation}.
	 * @param ctx the parse tree
	 */
	void enterAnnotation(TaraGrammar.AnnotationContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#annotation}.
	 * @param ctx the parse tree
	 */
	void exitAnnotation(TaraGrammar.AnnotationContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#flags}.
	 * @param ctx the parse tree
	 */
	void enterFlags(TaraGrammar.FlagsContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#flags}.
	 * @param ctx the parse tree
	 */
	void exitFlags(TaraGrammar.FlagsContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#flag}.
	 * @param ctx the parse tree
	 */
	void enterFlag(TaraGrammar.FlagContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#flag}.
	 * @param ctx the parse tree
	 */
	void exitFlag(TaraGrammar.FlagContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#varInit}.
	 * @param ctx the parse tree
	 */
	void enterVarInit(TaraGrammar.VarInitContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#varInit}.
	 * @param ctx the parse tree
	 */
	void exitVarInit(TaraGrammar.VarInitContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#headerReference}.
	 * @param ctx the parse tree
	 */
	void enterHeaderReference(TaraGrammar.HeaderReferenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#headerReference}.
	 * @param ctx the parse tree
	 */
	void exitHeaderReference(TaraGrammar.HeaderReferenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#identifierReference}.
	 * @param ctx the parse tree
	 */
	void enterIdentifierReference(TaraGrammar.IdentifierReferenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#identifierReference}.
	 * @param ctx the parse tree
	 */
	void exitIdentifierReference(TaraGrammar.IdentifierReferenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#hierarchy}.
	 * @param ctx the parse tree
	 */
	void enterHierarchy(TaraGrammar.HierarchyContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#hierarchy}.
	 * @param ctx the parse tree
	 */
	void exitHierarchy(TaraGrammar.HierarchyContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#metaidentifier}.
	 * @param ctx the parse tree
	 */
	void enterMetaidentifier(TaraGrammar.MetaidentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#metaidentifier}.
	 * @param ctx the parse tree
	 */
	void exitMetaidentifier(TaraGrammar.MetaidentifierContext ctx);
}