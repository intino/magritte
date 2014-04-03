// Generated from /home/bycor/Projects/Tara/src/AntlrM2/src/TaraM2Grammar.g4 by ANTLR 4.x

package monet.tara.compiler.parser.antlr;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

public interface TaraM2GrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link TaraM2Grammar#naturalList}.
	 *
	 * @param ctx the parse tree
	 */
	void enterNaturalList(@NotNull TaraM2Grammar.NaturalListContext ctx);

	/**
	 * Exit a parse tree produced by {@link TaraM2Grammar#naturalList}.
	 *
	 * @param ctx the parse tree
	 */
	void exitNaturalList(@NotNull TaraM2Grammar.NaturalListContext ctx);

	void enterBody(@NotNull TaraM2Grammar.BodyContext ctx);

	void exitBody(@NotNull TaraM2Grammar.BodyContext ctx);

	void enterConcept(@NotNull TaraM2Grammar.ConceptContext ctx);

	void exitConcept(@NotNull TaraM2Grammar.ConceptContext ctx);

	void enterNaturalValue(@NotNull TaraM2Grammar.NaturalValueContext ctx);

	void exitNaturalValue(@NotNull TaraM2Grammar.NaturalValueContext ctx);

	void enterBooleanList(@NotNull TaraM2Grammar.BooleanListContext ctx);

	void exitBooleanList(@NotNull TaraM2Grammar.BooleanListContext ctx);

	void enterRoot(@NotNull TaraM2Grammar.RootContext ctx);

	void exitRoot(@NotNull TaraM2Grammar.RootContext ctx);

	void enterIntegerList(@NotNull TaraM2Grammar.IntegerListContext ctx);

	void exitIntegerList(@NotNull TaraM2Grammar.IntegerListContext ctx);

	void enterAttribute(@NotNull TaraM2Grammar.AttributeContext ctx);

	void exitAttribute(@NotNull TaraM2Grammar.AttributeContext ctx);

	void enterStringValue(@NotNull TaraM2Grammar.StringValueContext ctx);

	void exitStringValue(@NotNull TaraM2Grammar.StringValueContext ctx);

	void enterDoubleValue(@NotNull TaraM2Grammar.DoubleValueContext ctx);

	void exitDoubleValue(@NotNull TaraM2Grammar.DoubleValueContext ctx);

	void enterReference(@NotNull TaraM2Grammar.ReferenceContext ctx);

	void exitReference(@NotNull TaraM2Grammar.ReferenceContext ctx);

	void enterBooleanValue(@NotNull TaraM2Grammar.BooleanValueContext ctx);

	void exitBooleanValue(@NotNull TaraM2Grammar.BooleanValueContext ctx);

	void enterModifier(@NotNull TaraM2Grammar.ModifierContext ctx);

	void exitModifier(@NotNull TaraM2Grammar.ModifierContext ctx);

	void enterConceptConstituents(@NotNull TaraM2Grammar.ConceptConstituentsContext ctx);

	void exitConceptConstituents(@NotNull TaraM2Grammar.ConceptConstituentsContext ctx);

	void enterReferenceIdentifier(@NotNull TaraM2Grammar.ReferenceIdentifierContext ctx);

	void exitReferenceIdentifier(@NotNull TaraM2Grammar.ReferenceIdentifierContext ctx);

	void enterDoc(@NotNull TaraM2Grammar.DocContext ctx);

	void exitDoc(@NotNull TaraM2Grammar.DocContext ctx);

	void enterIntegerValue(@NotNull TaraM2Grammar.IntegerValueContext ctx);

	void exitIntegerValue(@NotNull TaraM2Grammar.IntegerValueContext ctx);

	void enterStringList(@NotNull TaraM2Grammar.StringListContext ctx);

	void exitStringList(@NotNull TaraM2Grammar.StringListContext ctx);

	void enterWord(@NotNull TaraM2Grammar.WordContext ctx);

	void exitWord(@NotNull TaraM2Grammar.WordContext ctx);

	void enterAnnotations(@NotNull TaraM2Grammar.AnnotationsContext ctx);

	void exitAnnotations(@NotNull TaraM2Grammar.AnnotationsContext ctx);

	void enterDoubleList(@NotNull TaraM2Grammar.DoubleListContext ctx);

	void exitDoubleList(@NotNull TaraM2Grammar.DoubleListContext ctx);

	void enterVariableNames(@NotNull TaraM2Grammar.VariableNamesContext ctx);

	void exitVariableNames(@NotNull TaraM2Grammar.VariableNamesContext ctx);

	void enterSignature(@NotNull TaraM2Grammar.SignatureContext ctx);

	void exitSignature(@NotNull TaraM2Grammar.SignatureContext ctx);

	void enterConceptInjection(@NotNull TaraM2Grammar.ConceptInjectionContext ctx);

	void exitConceptInjection(@NotNull TaraM2Grammar.ConceptInjectionContext ctx);
}