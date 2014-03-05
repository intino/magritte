package monet.tara.intellij.codeInsight.completion;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.lang.ASTNode;
import com.intellij.patterns.ElementPattern;
import com.intellij.patterns.PsiElementPattern;
import com.intellij.psi.PsiElement;
import com.intellij.psi.filters.ElementFilter;
import com.intellij.psi.filters.position.FilterPattern;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.ProcessingContext;
import monet.tara.intellij.metamodel.TaraLanguage;
import monet.tara.intellij.metamodel.psi.TaraTypes;
import org.jetbrains.annotations.NotNull;

import static com.intellij.patterns.PlatformPatterns.psiElement;


public class TaraSignatureCompletionContributor extends CompletionContributor {

	private PsiElementPattern.Capture<PsiElement> afterConceptKey = psiElement()
		.withLanguage(TaraLanguage.INSTANCE)
		.and(new FilterPattern(new InSignatureFitFilter()))
		.and(new FilterPattern(new AfterElementFitFilter(TaraTypes.CONCEPT_KEY)));

	private ElementPattern<? extends PsiElement> afterPolymorphicOrMorphKey = psiElement()
		.withLanguage(TaraLanguage.INSTANCE)
		.and(new FilterPattern(new InSignatureFitFilter()))
		.andOr(new FilterPattern(new AfterElementFitFilter(TaraTypes.POLYMORPHIC_KEY)),
			new FilterPattern(new AfterElementFitFilter(TaraTypes.MORPH_KEY)));

	private ElementPattern<? extends PsiElement> afterModifierKey = psiElement()
		.withLanguage(TaraLanguage.INSTANCE)
		.and(new FilterPattern(new InSignatureFitFilter()))
		.andOr(new FilterPattern(new AfterElementFitFilter(TaraTypes.FINAL)),
			new FilterPattern(new AfterElementFitFilter(TaraTypes.ABSTRACT)));

	public TaraSignatureCompletionContributor() {
		extend(CompletionType.BASIC, afterConceptKey,
			new CompletionProvider<CompletionParameters>() {
				public void addCompletions(@NotNull CompletionParameters parameters,
				                           ProcessingContext context,
				                           @NotNull CompletionResultSet resultSet) {
					resultSet.addElement(LookupElementBuilder.create("polymorphic"));
					resultSet.addElement(LookupElementBuilder.create("morph"));
					resultSet.addElement(LookupElementBuilder.create("as"));
					resultSet.addElement(LookupElementBuilder.create("abstract"));
					resultSet.addElement(LookupElementBuilder.create("final"));
				}
			});

		extend(CompletionType.BASIC, afterPolymorphicOrMorphKey,
			new CompletionProvider<CompletionParameters>() {
				public void addCompletions(@NotNull CompletionParameters parameters,
				                           ProcessingContext context,
				                           @NotNull CompletionResultSet resultSet) {
					resultSet.addElement(LookupElementBuilder.create("as"));
				}
			});

		extend(CompletionType.BASIC, afterModifierKey,
			new CompletionProvider<CompletionParameters>() {
				public void addCompletions(@NotNull CompletionParameters parameters,
				                           ProcessingContext context,
				                           @NotNull CompletionResultSet resultSet) {
					resultSet.addElement(LookupElementBuilder.create("morph"));
					resultSet.addElement(LookupElementBuilder.create("as"));
				}
			});
	}

	private static class AfterElementFitFilter implements ElementFilter {
		IElementType type;

		private AfterElementFitFilter(IElementType type) {
			this.type = type;
		}

		public boolean isAcceptable(Object element, PsiElement context) {
			if (element instanceof PsiElement)
				if (context.getPrevSibling() != null && context.getPrevSibling().getPrevSibling() != null) {
					final ASTNode ctxPreviousNode = context.getPrevSibling().getPrevSibling().getNode();
					if (type.equals(ctxPreviousNode.getElementType()))
						return true;
				}
			return false;
		}

		public boolean isClassAcceptable(Class hintClass) {
			return true;
		}
	}

}