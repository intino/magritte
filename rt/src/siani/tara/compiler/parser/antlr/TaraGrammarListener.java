// Generated from /Users/oroncal/workspace/tara/rt/src/siani/tara/compiler/parser/antlr/TaraGrammar.g4 by ANTLR 4.5
package siani.tara.compiler.parser.antlr;
import org.antlr.v4.runtime.misc.NotNull;
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
	void enterRoot(@NotNull TaraGrammar.RootContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#root}.
	 * @param ctx the parse tree
	 */
	void exitRoot(@NotNull TaraGrammar.RootContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#dslDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterDslDeclaration(@NotNull TaraGrammar.DslDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#dslDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitDslDeclaration(@NotNull TaraGrammar.DslDeclarationContext ctx);
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
	 * Enter a parse tree produced by {@link TaraGrammar#node}.
	 * @param ctx the parse tree
	 */
	void enterNode(@NotNull TaraGrammar.NodeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#node}.
	 * @param ctx the parse tree
	 */
	void exitNode(@NotNull TaraGrammar.NodeContext ctx);
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
	 * Enter a parse tree produced by {@link TaraGrammar#parent}.
	 * @param ctx the parse tree
	 */
	void enterParent(@NotNull TaraGrammar.ParentContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#parent}.
	 * @param ctx the parse tree
	 */
	void exitParent(@NotNull TaraGrammar.ParentContext ctx);
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
	 * Enter a parse tree produced by {@link TaraGrammar#explicitParameter}.
	 * @param ctx the parse tree
	 */
	void enterExplicitParameter(@NotNull TaraGrammar.ExplicitParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#explicitParameter}.
	 * @param ctx the parse tree
	 */
	void exitExplicitParameter(@NotNull TaraGrammar.ExplicitParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#implicitParameter}.
	 * @param ctx the parse tree
	 */
	void enterImplicitParameter(@NotNull TaraGrammar.ImplicitParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#implicitParameter}.
	 * @param ctx the parse tree
	 */
	void exitImplicitParameter(@NotNull TaraGrammar.ImplicitParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(@NotNull TaraGrammar.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(@NotNull TaraGrammar.ValueContext ctx);
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
	 * Enter a parse tree produced by {@link TaraGrammar#nodeReference}.
	 * @param ctx the parse tree
	 */
	void enterNodeReference(@NotNull TaraGrammar.NodeReferenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#nodeReference}.
	 * @param ctx the parse tree
	 */
	void exitNodeReference(@NotNull TaraGrammar.NodeReferenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(@NotNull TaraGrammar.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(@NotNull TaraGrammar.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#variableType}.
	 * @param ctx the parse tree
	 */
	void enterVariableType(@NotNull TaraGrammar.VariableTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#variableType}.
	 * @param ctx the parse tree
	 */
	void exitVariableType(@NotNull TaraGrammar.VariableTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#nativeName}.
	 * @param ctx the parse tree
	 */
	void enterNativeName(@NotNull TaraGrammar.NativeNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#nativeName}.
	 * @param ctx the parse tree
	 */
	void exitNativeName(@NotNull TaraGrammar.NativeNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#count}.
	 * @param ctx the parse tree
	 */
	void enterCount(@NotNull TaraGrammar.CountContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#count}.
	 * @param ctx the parse tree
	 */
	void exitCount(@NotNull TaraGrammar.CountContext ctx);
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
	 * Enter a parse tree produced by {@link TaraGrammar#wordValue}.
	 * @param ctx the parse tree
	 */
	void enterWordValue(@NotNull TaraGrammar.WordValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#wordValue}.
	 * @param ctx the parse tree
	 */
	void exitWordValue(@NotNull TaraGrammar.WordValueContext ctx);
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
	 * Enter a parse tree produced by {@link TaraGrammar#linkValue}.
	 * @param ctx the parse tree
	 */
	void enterLinkValue(@NotNull TaraGrammar.LinkValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#linkValue}.
	 * @param ctx the parse tree
	 */
	void exitLinkValue(@NotNull TaraGrammar.LinkValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#address}.
	 * @param ctx the parse tree
	 */
	void enterAddress(@NotNull TaraGrammar.AddressContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#address}.
	 * @param ctx the parse tree
	 */
	void exitAddress(@NotNull TaraGrammar.AddressContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#measureValue}.
	 * @param ctx the parse tree
	 */
	void enterMeasureValue(@NotNull TaraGrammar.MeasureValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#measureValue}.
	 * @param ctx the parse tree
	 */
	void exitMeasureValue(@NotNull TaraGrammar.MeasureValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#tags}.
	 * @param ctx the parse tree
	 */
	void enterTags(@NotNull TaraGrammar.TagsContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#tags}.
	 * @param ctx the parse tree
	 */
	void exitTags(@NotNull TaraGrammar.TagsContext ctx);
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
	 * Enter a parse tree produced by {@link TaraGrammar#annotation}.
	 * @param ctx the parse tree
	 */
	void enterAnnotation(@NotNull TaraGrammar.AnnotationContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#annotation}.
	 * @param ctx the parse tree
	 */
	void exitAnnotation(@NotNull TaraGrammar.AnnotationContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#flags}.
	 * @param ctx the parse tree
	 */
	void enterFlags(@NotNull TaraGrammar.FlagsContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#flags}.
	 * @param ctx the parse tree
	 */
	void exitFlags(@NotNull TaraGrammar.FlagsContext ctx);
	/**
	 * Enter a parse tree produced by {@link TaraGrammar#flag}.
	 * @param ctx the parse tree
	 */
	void enterFlag(@NotNull TaraGrammar.FlagContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#flag}.
	 * @param ctx the parse tree
	 */
	void exitFlag(@NotNull TaraGrammar.FlagContext ctx);
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
	 * Enter a parse tree produced by {@link TaraGrammar#metaidentifier}.
	 * @param ctx the parse tree
	 */
	void enterMetaidentifier(@NotNull TaraGrammar.MetaidentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraGrammar#metaidentifier}.
	 * @param ctx the parse tree
	 */
	void exitMetaidentifier(@NotNull TaraGrammar.MetaidentifierContext ctx);
}