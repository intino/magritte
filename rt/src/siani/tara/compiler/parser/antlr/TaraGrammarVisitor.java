// Generated from /Users/octavio/workspace/tara/rt/src/siani/tara/compiler/parser/antlr/TaraGrammar.g4 by ANTLR 4.x

package siani.tara.compiler.parser.antlr;

import org.antlr.v4.runtime.misc.NotNull;
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
	 * Visit a parse tree produced by {@link TaraGrammar#body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBody(@NotNull TaraGrammar.BodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#concept}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConcept(@NotNull TaraGrammar.ConceptContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#integerAttribute}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntegerAttribute(@NotNull TaraGrammar.IntegerAttributeContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#naturalValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNaturalValue(@NotNull TaraGrammar.NaturalValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#root}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRoot(@NotNull TaraGrammar.RootContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#stringValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringValue(@NotNull TaraGrammar.StringValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#attribute}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttribute(@NotNull TaraGrammar.AttributeContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#doubleValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoubleValue(@NotNull TaraGrammar.DoubleValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#identifierList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifierList(@NotNull TaraGrammar.IdentifierListContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#metaidentifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMetaidentifier(@NotNull TaraGrammar.MetaidentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#doubleAttribute}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoubleAttribute(@NotNull TaraGrammar.DoubleAttributeContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#booleanValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanValue(@NotNull TaraGrammar.BooleanValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#namespace}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNamespace(@NotNull TaraGrammar.NamespaceContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#naturalAttribute}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNaturalAttribute(@NotNull TaraGrammar.NaturalAttributeContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#conceptConstituents}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConceptConstituents(@NotNull TaraGrammar.ConceptConstituentsContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#parameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameter(@NotNull TaraGrammar.ParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#stringAttribute}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringAttribute(@NotNull TaraGrammar.StringAttributeContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#parameterList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterList(@NotNull TaraGrammar.ParameterListContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#integerValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntegerValue(@NotNull TaraGrammar.IntegerValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#stringList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringList(@NotNull TaraGrammar.StringListContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#hierarchy}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHierarchy(@NotNull TaraGrammar.HierarchyContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#parameters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameters(@NotNull TaraGrammar.ParametersContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#signature}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSignature(@NotNull TaraGrammar.SignatureContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#naturalList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNaturalList(@NotNull TaraGrammar.NaturalListContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#module}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModule(@NotNull TaraGrammar.ModuleContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#booleanList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanList(@NotNull TaraGrammar.BooleanListContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#booleanAttribute}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanAttribute(@NotNull TaraGrammar.BooleanAttributeContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#resource}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitResource(@NotNull TaraGrammar.ResourceContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#integerList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntegerList(@NotNull TaraGrammar.IntegerListContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#identifierReference}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifierReference(@NotNull TaraGrammar.IdentifierReferenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#reference}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReference(@NotNull TaraGrammar.ReferenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#imports}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImports(@NotNull TaraGrammar.ImportsContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#header}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHeader(@NotNull TaraGrammar.HeaderContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#headerReference}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHeaderReference(@NotNull TaraGrammar.HeaderReferenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#explicit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExplicit(@NotNull TaraGrammar.ExplicitContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#doc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoc(@NotNull TaraGrammar.DocContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#base}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBase(@NotNull TaraGrammar.BaseContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#varInit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarInit(@NotNull TaraGrammar.VarInitContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#aliasAttribute}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAliasAttribute(@NotNull TaraGrammar.AliasAttributeContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#word}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWord(@NotNull TaraGrammar.WordContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#annotations}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnnotations(@NotNull TaraGrammar.AnnotationsContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#packet}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPacket(@NotNull TaraGrammar.PacketContext ctx);
	/**
	 * Visit a parse tree produced by {@link TaraGrammar#doubleList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoubleList(@NotNull TaraGrammar.DoubleListContext ctx);
}