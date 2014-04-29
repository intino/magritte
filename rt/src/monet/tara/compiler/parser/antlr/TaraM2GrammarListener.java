// Generated from /home/bycor/Projects/Tara/src/AntlrM2/src/TaraM2Grammar.g4 by ANTLR 4.x

    package monet.tara.compiler.parser.antlr;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link TaraM2Grammar}.
 */
public interface TaraM2GrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link TaraM2Grammar#body}.
	 * @param ctx the parse tree
	 */
	void enterBody(@NotNull TaraM2Grammar.BodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraM2Grammar#body}.
	 * @param ctx the parse tree
	 */
	void exitBody(@NotNull TaraM2Grammar.BodyContext ctx);

	/**
	 * Enter a parse tree produced by {@link TaraM2Grammar#concept}.
	 * @param ctx the parse tree
	 */
	void enterConcept(@NotNull TaraM2Grammar.ConceptContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraM2Grammar#concept}.
	 * @param ctx the parse tree
	 */
	void exitConcept(@NotNull TaraM2Grammar.ConceptContext ctx);

	/**
	 * Enter a parse tree produced by {@link TaraM2Grammar#naturalValue}.
	 * @param ctx the parse tree
	 */
	void enterNaturalValue(@NotNull TaraM2Grammar.NaturalValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraM2Grammar#naturalValue}.
	 * @param ctx the parse tree
	 */
	void exitNaturalValue(@NotNull TaraM2Grammar.NaturalValueContext ctx);

	/**
	 * Enter a parse tree produced by {@link TaraM2Grammar#root}.
	 * @param ctx the parse tree
	 */
	void enterRoot(@NotNull TaraM2Grammar.RootContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraM2Grammar#root}.
	 * @param ctx the parse tree
	 */
	void exitRoot(@NotNull TaraM2Grammar.RootContext ctx);

	/**
	 * Enter a parse tree produced by {@link TaraM2Grammar#attribute}.
	 * @param ctx the parse tree
	 */
	void enterAttribute(@NotNull TaraM2Grammar.AttributeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraM2Grammar#attribute}.
	 * @param ctx the parse tree
	 */
	void exitAttribute(@NotNull TaraM2Grammar.AttributeContext ctx);

	/**
	 * Enter a parse tree produced by {@link TaraM2Grammar#stringValue}.
	 * @param ctx the parse tree
	 */
	void enterStringValue(@NotNull TaraM2Grammar.StringValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraM2Grammar#stringValue}.
	 * @param ctx the parse tree
	 */
	void exitStringValue(@NotNull TaraM2Grammar.StringValueContext ctx);

	/**
	 * Enter a parse tree produced by {@link TaraM2Grammar#doubleValue}.
	 * @param ctx the parse tree
	 */
	void enterDoubleValue(@NotNull TaraM2Grammar.DoubleValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraM2Grammar#doubleValue}.
	 * @param ctx the parse tree
	 */
	void exitDoubleValue(@NotNull TaraM2Grammar.DoubleValueContext ctx);

	/**
	 * Enter a parse tree produced by {@link TaraM2Grammar#lang}.
	 * @param ctx the parse tree
	 */
	void enterLang(@NotNull TaraM2Grammar.LangContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraM2Grammar#lang}.
	 * @param ctx the parse tree
	 */
	void exitLang(@NotNull TaraM2Grammar.LangContext ctx);

	/**
	 * Enter a parse tree produced by {@link TaraM2Grammar#booleanValue}.
	 * @param ctx the parse tree
	 */
	void enterBooleanValue(@NotNull TaraM2Grammar.BooleanValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraM2Grammar#booleanValue}.
	 * @param ctx the parse tree
	 */
	void exitBooleanValue(@NotNull TaraM2Grammar.BooleanValueContext ctx);

	/**
	 * Enter a parse tree produced by {@link TaraM2Grammar#child}.
	 * @param ctx the parse tree
	 */
	void enterChild(@NotNull TaraM2Grammar.ChildContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraM2Grammar#child}.
	 * @param ctx the parse tree
	 */
	void exitChild(@NotNull TaraM2Grammar.ChildContext ctx);

	/**
	 * Enter a parse tree produced by {@link TaraM2Grammar#conceptConstituents}.
	 * @param ctx the parse tree
	 */
	void enterConceptConstituents(@NotNull TaraM2Grammar.ConceptConstituentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraM2Grammar#conceptConstituents}.
	 * @param ctx the parse tree
	 */
	void exitConceptConstituents(@NotNull TaraM2Grammar.ConceptConstituentsContext ctx);

	/**
	 * Enter a parse tree produced by {@link TaraM2Grammar#referenceIdentifier}.
	 * @param ctx the parse tree
	 */
	void enterReferenceIdentifier(@NotNull TaraM2Grammar.ReferenceIdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraM2Grammar#referenceIdentifier}.
	 * @param ctx the parse tree
	 */
	void exitReferenceIdentifier(@NotNull TaraM2Grammar.ReferenceIdentifierContext ctx);

	/**
	 * Enter a parse tree produced by {@link TaraM2Grammar#integerValue}.
	 * @param ctx the parse tree
	 */
	void enterIntegerValue(@NotNull TaraM2Grammar.IntegerValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraM2Grammar#integerValue}.
	 * @param ctx the parse tree
	 */
	void exitIntegerValue(@NotNull TaraM2Grammar.IntegerValueContext ctx);

	/**
	 * Enter a parse tree produced by {@link TaraM2Grammar#stringList}.
	 * @param ctx the parse tree
	 */
	void enterStringList(@NotNull TaraM2Grammar.StringListContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraM2Grammar#stringList}.
	 * @param ctx the parse tree
	 */
	void exitStringList(@NotNull TaraM2Grammar.StringListContext ctx);

	/**
	 * Enter a parse tree produced by {@link TaraM2Grammar#signature}.
	 * @param ctx the parse tree
	 */
	void enterSignature(@NotNull TaraM2Grammar.SignatureContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraM2Grammar#signature}.
	 * @param ctx the parse tree
	 */
	void exitSignature(@NotNull TaraM2Grammar.SignatureContext ctx);

	/**
	 * Enter a parse tree produced by {@link TaraM2Grammar#naturalList}.
	 * @param ctx the parse tree
	 */
	void enterNaturalList(@NotNull TaraM2Grammar.NaturalListContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraM2Grammar#naturalList}.
	 * @param ctx the parse tree
	 */
	void exitNaturalList(@NotNull TaraM2Grammar.NaturalListContext ctx);

	/**
	 * Enter a parse tree produced by {@link TaraM2Grammar#extensible}.
	 * @param ctx the parse tree
	 */
	void enterExtensible(@NotNull TaraM2Grammar.ExtensibleContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraM2Grammar#extensible}.
	 * @param ctx the parse tree
	 */
	void exitExtensible(@NotNull TaraM2Grammar.ExtensibleContext ctx);

	/**
	 * Enter a parse tree produced by {@link TaraM2Grammar#cases}.
	 * @param ctx the parse tree
	 */
	void enterCases(@NotNull TaraM2Grammar.CasesContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraM2Grammar#cases}.
	 * @param ctx the parse tree
	 */
	void exitCases(@NotNull TaraM2Grammar.CasesContext ctx);

	/**
	 * Enter a parse tree produced by {@link TaraM2Grammar#booleanList}.
	 * @param ctx the parse tree
	 */
	void enterBooleanList(@NotNull TaraM2Grammar.BooleanListContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraM2Grammar#booleanList}.
	 * @param ctx the parse tree
	 */
	void exitBooleanList(@NotNull TaraM2Grammar.BooleanListContext ctx);

	/**
	 * Enter a parse tree produced by {@link TaraM2Grammar#integerList}.
	 * @param ctx the parse tree
	 */
	void enterIntegerList(@NotNull TaraM2Grammar.IntegerListContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraM2Grammar#integerList}.
	 * @param ctx the parse tree
	 */
	void exitIntegerList(@NotNull TaraM2Grammar.IntegerListContext ctx);

	/**
	 * Enter a parse tree produced by {@link TaraM2Grammar#reference}.
	 * @param ctx the parse tree
	 */
	void enterReference(@NotNull TaraM2Grammar.ReferenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraM2Grammar#reference}.
	 * @param ctx the parse tree
	 */
	void exitReference(@NotNull TaraM2Grammar.ReferenceContext ctx);

	/**
	 * Enter a parse tree produced by {@link TaraM2Grammar#imports}.
	 * @param ctx the parse tree
	 */
	void enterImports(@NotNull TaraM2Grammar.ImportsContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraM2Grammar#imports}.
	 * @param ctx the parse tree
	 */
	void exitImports(@NotNull TaraM2Grammar.ImportsContext ctx);

	/**
	 * Enter a parse tree produced by {@link TaraM2Grammar#header}.
	 * @param ctx the parse tree
	 */
	void enterHeader(@NotNull TaraM2Grammar.HeaderContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraM2Grammar#header}.
	 * @param ctx the parse tree
	 */
	void exitHeader(@NotNull TaraM2Grammar.HeaderContext ctx);

	/**
	 * Enter a parse tree produced by {@link TaraM2Grammar#headerReference}.
	 * @param ctx the parse tree
	 */
	void enterHeaderReference(@NotNull TaraM2Grammar.HeaderReferenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraM2Grammar#headerReference}.
	 * @param ctx the parse tree
	 */
	void exitHeaderReference(@NotNull TaraM2Grammar.HeaderReferenceContext ctx);

	/**
	 * Enter a parse tree produced by {@link TaraM2Grammar#modifier}.
	 * @param ctx the parse tree
	 */
	void enterModifier(@NotNull TaraM2Grammar.ModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraM2Grammar#modifier}.
	 * @param ctx the parse tree
	 */
	void exitModifier(@NotNull TaraM2Grammar.ModifierContext ctx);

	/**
	 * Enter a parse tree produced by {@link TaraM2Grammar#extension}.
	 * @param ctx the parse tree
	 */
	void enterExtension(@NotNull TaraM2Grammar.ExtensionContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraM2Grammar#extension}.
	 * @param ctx the parse tree
	 */
	void exitExtension(@NotNull TaraM2Grammar.ExtensionContext ctx);

	/**
	 * Enter a parse tree produced by {@link TaraM2Grammar#doc}.
	 * @param ctx the parse tree
	 */
	void enterDoc(@NotNull TaraM2Grammar.DocContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraM2Grammar#doc}.
	 * @param ctx the parse tree
	 */
	void exitDoc(@NotNull TaraM2Grammar.DocContext ctx);

	/**
	 * Enter a parse tree produced by {@link TaraM2Grammar#word}.
	 * @param ctx the parse tree
	 */
	void enterWord(@NotNull TaraM2Grammar.WordContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraM2Grammar#word}.
	 * @param ctx the parse tree
	 */
	void exitWord(@NotNull TaraM2Grammar.WordContext ctx);

	/**
	 * Enter a parse tree produced by {@link TaraM2Grammar#packet}.
	 * @param ctx the parse tree
	 */
	void enterPacket(@NotNull TaraM2Grammar.PacketContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraM2Grammar#packet}.
	 * @param ctx the parse tree
	 */
	void exitPacket(@NotNull TaraM2Grammar.PacketContext ctx);

	/**
	 * Enter a parse tree produced by {@link TaraM2Grammar#annotations}.
	 * @param ctx the parse tree
	 */
	void enterAnnotations(@NotNull TaraM2Grammar.AnnotationsContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraM2Grammar#annotations}.
	 * @param ctx the parse tree
	 */
	void exitAnnotations(@NotNull TaraM2Grammar.AnnotationsContext ctx);

	/**
	 * Enter a parse tree produced by {@link TaraM2Grammar#doubleList}.
	 * @param ctx the parse tree
	 */
	void enterDoubleList(@NotNull TaraM2Grammar.DoubleListContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraM2Grammar#doubleList}.
	 * @param ctx the parse tree
	 */
	void exitDoubleList(@NotNull TaraM2Grammar.DoubleListContext ctx);

	/**
	 * Enter a parse tree produced by {@link TaraM2Grammar#variableNames}.
	 * @param ctx the parse tree
	 */
	void enterVariableNames(@NotNull TaraM2Grammar.VariableNamesContext ctx);
	/**
	 * Exit a parse tree produced by {@link TaraM2Grammar#variableNames}.
	 * @param ctx the parse tree
	 */
	void exitVariableNames(@NotNull TaraM2Grammar.VariableNamesContext ctx);
}