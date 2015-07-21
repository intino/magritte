// Generated from /Users/oroncal/workspace/tara/ide/language/src/tara/language/grammar/TaraGrammar.g4 by ANTLR 4.5.1
package tara.language.antlr;
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
	 * Enter a parse tree produced by {@link TaraGrammar#explicitParameter}.
	 * @param ctx the parse tree
	 */
	void enterExplicitParameter(TaraGrammar.ExplicitParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#explicitParameter}.
	 * @param ctx the parse tree
	 */
	void exitExplicitParameter(TaraGrammar.ExplicitParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#implicitParameter}.
	 * @param ctx the parse tree
	 */
	void enterImplicitParameter(TaraGrammar.ImplicitParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#implicitParameter}.
	 * @param ctx the parse tree
	 */
	void exitImplicitParameter(TaraGrammar.ImplicitParameterContext ctx);
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
	 * Enter a parse tree produced by {@link TaraGrammar#facetApply}.
	 * @param ctx the parse tree
	 */
	void enterFacetApply(TaraGrammar.FacetApplyContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#facetApply}.
	 * @param ctx the parse tree
	 */
	void exitFacetApply(TaraGrammar.FacetApplyContext ctx);
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
	 * Enter a parse tree produced by {@link TaraGrammar#contract}.
	 * @param ctx the parse tree
	 */
	void enterContract(TaraGrammar.ContractContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#contract}.
	 * @param ctx the parse tree
	 */
	void exitContract(TaraGrammar.ContractContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#contractValue}.
	 * @param ctx the parse tree
	 */
	void enterContractValue(TaraGrammar.ContractValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#contractValue}.
	 * @param ctx the parse tree
	 */
	void exitContractValue(TaraGrammar.ContractValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#count}.
	 * @param ctx the parse tree
	 */
	void enterCount(TaraGrammar.CountContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#count}.
	 * @param ctx the parse tree
	 */
	void exitCount(TaraGrammar.CountContext ctx);
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
	 * Enter a parse tree produced by {@link TaraGrammar#naturalValue}.
	 * @param ctx the parse tree
	 */
	void enterNaturalValue(TaraGrammar.NaturalValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#naturalValue}.
	 * @param ctx the parse tree
	 */
	void exitNaturalValue(TaraGrammar.NaturalValueContext ctx);
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
	 * Enter a parse tree produced by {@link TaraGrammar#linkValue}.
	 * @param ctx the parse tree
	 */
	void enterLinkValue(TaraGrammar.LinkValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#linkValue}.
	 * @param ctx the parse tree
	 */
	void exitLinkValue(TaraGrammar.LinkValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#plate}.
	 * @param ctx the parse tree
	 */
	void enterPlate(TaraGrammar.PlateContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#plate}.
	 * @param ctx the parse tree
	 */
	void exitPlate(TaraGrammar.PlateContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#measureValue}.
	 * @param ctx the parse tree
	 */
	void enterMeasureValue(TaraGrammar.MeasureValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#measureValue}.
	 * @param ctx the parse tree
	 */
	void exitMeasureValue(TaraGrammar.MeasureValueContext ctx);
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