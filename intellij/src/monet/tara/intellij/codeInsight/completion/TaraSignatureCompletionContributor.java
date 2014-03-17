package monet.tara.intellij.codeInsight.completion;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.lang.ASTNode;
import com.intellij.patterns.PsiElementPattern;
import com.intellij.psi.PsiElement;
import com.intellij.psi.filters.ElementFilter;
import com.intellij.psi.filters.position.FilterPattern;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.ProcessingContext;
import monet.tara.intellij.metamodel.TaraIcons;
import monet.tara.intellij.metamodel.TaraLanguage;
import monet.tara.intellij.metamodel.psi.Concept;
import monet.tara.intellij.metamodel.psi.TaraIdentifier;
import monet.tara.intellij.metamodel.psi.TaraTypes;
import monet.tara.intellij.metamodel.psi.impl.TaraPsiImplUtil;
import monet.tara.intellij.metamodel.psi.impl.TaraUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.intellij.patterns.PlatformPatterns.psiElement;


public class TaraSignatureCompletionContributor extends CompletionContributor {

	private PsiElementPattern.Capture<PsiElement> afterNewLine = psiElement().withLanguage(TaraLanguage.INSTANCE)
		.and(new FilterPattern(new InErrorFilter()));

	private PsiElementPattern.Capture<PsiElement> afterConceptKey = psiElement()
		.withLanguage(TaraLanguage.INSTANCE)
		.and(new FilterPattern(new InSignatureFitFilter()))
		.and(new FilterPattern(new AfterElementFitFilter(TaraTypes.CONCEPT_KEY)));

	private PsiElementPattern.Capture<PsiElement> afterPolymorphicOrMorphKey = psiElement()
		.withLanguage(TaraLanguage.INSTANCE)
		.and(new FilterPattern(new InSignatureFitFilter()))
		.andOr(new FilterPattern(new AfterElementFitFilter(TaraTypes.POLYMORPHIC_KEY)),
			new FilterPattern(new AfterElementFitFilter(TaraTypes.MORPH_KEY)));

	private PsiElementPattern.Capture<PsiElement> afterModifierKey = psiElement()
		.withLanguage(TaraLanguage.INSTANCE)
		.and(new FilterPattern(new InSignatureFitFilter()))
		.andOr(new FilterPattern(new AfterElementFitFilter(TaraTypes.FINAL)),
			new FilterPattern(new AfterElementFitFilter(TaraTypes.ABSTRACT)));


	public TaraSignatureCompletionContributor() {
		extend(CompletionType.SMART, afterConceptKey,
			new CompletionProvider<CompletionParameters>() {
				public void addCompletions(@NotNull CompletionParameters parameters,
				                           ProcessingContext context,
				                           @NotNull CompletionResultSet resultSet) {
					resultSet.addElement(LookupElementBuilder.create("polymorphic"));
					resultSet.addElement(LookupElementBuilder.create("morph"));
					resultSet.addElement(LookupElementBuilder.create("as"));
					resultSet.addElement(LookupElementBuilder.create("abstract"));
					resultSet.addElement(LookupElementBuilder.create("final"));
					resultSet.addAllElements(getVariants(parameters.getOriginalPosition()));
				}
			});

		extend(CompletionType.SMART, afterPolymorphicOrMorphKey,
			new CompletionProvider<CompletionParameters>() {
				public void addCompletions(@NotNull CompletionParameters parameters,
				                           ProcessingContext context,
				                           @NotNull CompletionResultSet resultSet) {
					resultSet.addElement(LookupElementBuilder.create("as"));
				}
			});


		extend(CompletionType.SMART, afterNewLine,
			new CompletionProvider<CompletionParameters>() {
				public void addCompletions(@NotNull CompletionParameters parameters,
				                           ProcessingContext context,
				                           @NotNull CompletionResultSet resultSet) {
					resultSet.addElement(LookupElementBuilder.create("Concept"));
				}
			});

		extend(CompletionType.SMART, afterModifierKey,
			new CompletionProvider<CompletionParameters>() {
				public void addCompletions(@NotNull CompletionParameters parameters,
				                           ProcessingContext context,
				                           @NotNull CompletionResultSet resultSet) {
					resultSet.addElement(LookupElementBuilder.create("morph"));
					resultSet.addElement(LookupElementBuilder.create("as"));
				}
			});
	}

	public static List<LookupElement> getVariants(PsiElement myElement) {
		List<Concept> concepts = new ArrayList<>();
		if (myElement.getPrevSibling().getNode().equals(TaraTypes.DOT))
			getChildrenVariants((TaraIdentifier) myElement.getPrevSibling().getPrevSibling(), concepts);
		else refer(myElement, concepts);
		return fillVariants(concepts);
	}

	private static List<LookupElement> fillVariants(List<Concept> concepts) {
		List<LookupElement> variants = new ArrayList<>();
		for (final Concept concept : concepts)
			if (concept.getName() != null && concept.getName().length() > 0)
				variants.add(LookupElementBuilder.create(concept).withIcon(TaraIcons.ICON_13).withTypeText(getFileName(concept)));
		return variants;
	}

	private static void refer(PsiElement parent, List<Concept> concepts) {
		concepts.addAll(TaraUtil.getRootConcepts(parent.getProject()));
		Concept context = TaraPsiImplUtil.getContextOf(parent);
		concepts.addAll(TaraUtil.getSiblings(context));
	}

	private static void getChildrenVariants(TaraIdentifier parent, List<Concept> concepts) {
		Concept concept = TaraUtil.resolveReferences(parent.getProject(), parent);
		Collections.addAll(concepts, TaraUtil.getChildrenOf(concept));
	}

	private static String getFileName(Concept concept) {
		return concept.getContainingFile().getName().split("\\.")[0];
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

	private class InErrorFilter implements ElementFilter {
		@Override
		public boolean isAcceptable(Object element, @Nullable PsiElement context) {
			if (element instanceof PsiElement) {
				assert context != null;
				if (context.getPrevSibling() == null) return true;
			}
			return false;
		}

		@Override
		public boolean isClassAcceptable(Class hintClass) {
			return true;
		}
	}
}