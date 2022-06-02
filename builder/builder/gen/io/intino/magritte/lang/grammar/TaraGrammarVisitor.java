// Generated from /Users/oroncal/workspace/magritte/core/language/src/io/intino/magritte/lang/grammar/TaraGrammar.g4 by ANTLR 4.10.1
package io.intino.magritte.lang.grammar;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link TaraGrammar}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface TaraGrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#root}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRoot(TaraGrammar.RootContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#dslDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDslDeclaration(TaraGrammar.DslDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#imports}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImports(TaraGrammar.ImportsContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#anImport}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnImport(TaraGrammar.AnImportContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#doc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoc(TaraGrammar.DocContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#node}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNode(TaraGrammar.NodeContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#signature}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSignature(TaraGrammar.SignatureContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#parent}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParent(TaraGrammar.ParentContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#parameters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameters(TaraGrammar.ParametersContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#parameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameter(TaraGrammar.ParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#aspects}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAspects(TaraGrammar.AspectsContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#aspect}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAspect(TaraGrammar.AspectContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(TaraGrammar.ValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBody(TaraGrammar.BodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#nodeReference}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNodeReference(TaraGrammar.NodeReferenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#with}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWith(TaraGrammar.WithContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(TaraGrammar.VariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#bodyValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBodyValue(TaraGrammar.BodyValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#variableType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableType(TaraGrammar.VariableTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#ruleContainer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuleContainer(TaraGrammar.RuleContainerContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#ruleValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuleValue(TaraGrammar.RuleValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#range}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRange(TaraGrammar.RangeContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#size}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSize(TaraGrammar.SizeContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#sizeRange}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSizeRange(TaraGrammar.SizeRangeContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#listRange}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListRange(TaraGrammar.ListRangeContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#methodReference}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodReference(TaraGrammar.MethodReferenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#stringValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringValue(TaraGrammar.StringValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#booleanValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanValue(TaraGrammar.BooleanValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#tupleValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTupleValue(TaraGrammar.TupleValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#integerValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntegerValue(TaraGrammar.IntegerValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#doubleValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoubleValue(TaraGrammar.DoubleValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#metric}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMetric(TaraGrammar.MetricContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(TaraGrammar.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#tags}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTags(TaraGrammar.TagsContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#annotations}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnnotations(TaraGrammar.AnnotationsContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#annotation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnnotation(TaraGrammar.AnnotationContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#flags}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFlags(TaraGrammar.FlagsContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#flag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFlag(TaraGrammar.FlagContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#varInit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarInit(TaraGrammar.VarInitContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#headerReference}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHeaderReference(TaraGrammar.HeaderReferenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#identifierReference}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifierReference(TaraGrammar.IdentifierReferenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#hierarchy}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHierarchy(TaraGrammar.HierarchyContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#metaidentifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMetaidentifier(TaraGrammar.MetaidentifierContext ctx);
}