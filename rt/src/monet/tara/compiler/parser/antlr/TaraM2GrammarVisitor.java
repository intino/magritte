// Generated from /home/bycor/Projects/Tara/src/AntlrM2/src/TaraM2Grammar.g4 by ANTLR 4.x
package monet.tara.compiler.parser.antlr;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link TaraM2Grammar}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface TaraM2GrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link TaraM2Grammar#body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBody(@NotNull TaraM2Grammar.BodyContext ctx);

	/**
	 * Visit a parse tree produced by {@link TaraM2Grammar#concept}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConcept(@NotNull TaraM2Grammar.ConceptContext ctx);

	/**
	 * Visit a parse tree produced by {@link TaraM2Grammar#naturalValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNaturalValue(@NotNull TaraM2Grammar.NaturalValueContext ctx);

	/**
	 * Visit a parse tree produced by {@link TaraM2Grammar#root}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRoot(@NotNull TaraM2Grammar.RootContext ctx);

	/**
	 * Visit a parse tree produced by {@link TaraM2Grammar#externalReference}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExternalReference(@NotNull TaraM2Grammar.ExternalReferenceContext ctx);

	/**
	 * Visit a parse tree produced by {@link TaraM2Grammar#stringValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringValue(@NotNull TaraM2Grammar.StringValueContext ctx);

	/**
	 * Visit a parse tree produced by {@link TaraM2Grammar#attribute}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttribute(@NotNull TaraM2Grammar.AttributeContext ctx);

	/**
	 * Visit a parse tree produced by {@link TaraM2Grammar#doubleValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoubleValue(@NotNull TaraM2Grammar.DoubleValueContext ctx);

	/**
	 * Visit a parse tree produced by {@link TaraM2Grammar#booleanValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanValue(@NotNull TaraM2Grammar.BooleanValueContext ctx);

	/**
	 * Visit a parse tree produced by {@link TaraM2Grammar#conceptConstituents}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConceptConstituents(@NotNull TaraM2Grammar.ConceptConstituentsContext ctx);

	/**
	 * Visit a parse tree produced by {@link TaraM2Grammar#integerValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntegerValue(@NotNull TaraM2Grammar.IntegerValueContext ctx);

	/**
	 * Visit a parse tree produced by {@link TaraM2Grammar#stringList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringList(@NotNull TaraM2Grammar.StringListContext ctx);

	/**
	 * Visit a parse tree produced by {@link TaraM2Grammar#hierarchy}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHierarchy(@NotNull TaraM2Grammar.HierarchyContext ctx);

	/**
	 * Visit a parse tree produced by {@link TaraM2Grammar#signature}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSignature(@NotNull TaraM2Grammar.SignatureContext ctx);

	/**
	 * Visit a parse tree produced by {@link TaraM2Grammar#naturalList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNaturalList(@NotNull TaraM2Grammar.NaturalListContext ctx);

	/**
	 * Visit a parse tree produced by {@link TaraM2Grammar#extensible}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExtensible(@NotNull TaraM2Grammar.ExtensibleContext ctx);

	/**
	 * Visit a parse tree produced by {@link TaraM2Grammar#booleanList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanList(@NotNull TaraM2Grammar.BooleanListContext ctx);

	/**
	 * Visit a parse tree produced by {@link TaraM2Grammar#integerList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntegerList(@NotNull TaraM2Grammar.IntegerListContext ctx);

	/**
	 * Visit a parse tree produced by {@link TaraM2Grammar#identifierReference}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifierReference(@NotNull TaraM2Grammar.IdentifierReferenceContext ctx);

	/**
	 * Visit a parse tree produced by {@link TaraM2Grammar#reference}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReference(@NotNull TaraM2Grammar.ReferenceContext ctx);

	/**
	 * Visit a parse tree produced by {@link TaraM2Grammar#imports}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImports(@NotNull TaraM2Grammar.ImportsContext ctx);

	/**
	 * Visit a parse tree produced by {@link TaraM2Grammar#header}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHeader(@NotNull TaraM2Grammar.HeaderContext ctx);

	/**
	 * Visit a parse tree produced by {@link TaraM2Grammar#headerReference}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHeaderReference(@NotNull TaraM2Grammar.HeaderReferenceContext ctx);

	/**
	 * Visit a parse tree produced by {@link TaraM2Grammar#modifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModifier(@NotNull TaraM2Grammar.ModifierContext ctx);

	/**
	 * Visit a parse tree produced by {@link TaraM2Grammar#extension}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExtension(@NotNull TaraM2Grammar.ExtensionContext ctx);

	/**
	 * Visit a parse tree produced by {@link TaraM2Grammar#doc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoc(@NotNull TaraM2Grammar.DocContext ctx);

	/**
	 * Visit a parse tree produced by {@link TaraM2Grammar#word}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWord(@NotNull TaraM2Grammar.WordContext ctx);

	/**
	 * Visit a parse tree produced by {@link TaraM2Grammar#packet}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPacket(@NotNull TaraM2Grammar.PacketContext ctx);

	/**
	 * Visit a parse tree produced by {@link TaraM2Grammar#annotations}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnnotations(@NotNull TaraM2Grammar.AnnotationsContext ctx);

	/**
	 * Visit a parse tree produced by {@link TaraM2Grammar#doubleList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoubleList(@NotNull TaraM2Grammar.DoubleListContext ctx);

	/**
	 * Visit a parse tree produced by {@link TaraM2Grammar#variableNames}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableNames(@NotNull TaraM2Grammar.VariableNamesContext ctx);
}