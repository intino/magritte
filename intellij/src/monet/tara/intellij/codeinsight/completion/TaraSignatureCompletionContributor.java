package monet.tara.intellij.codeinsight.completion;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.psi.PsiElement;
import com.intellij.util.ProcessingContext;
import monet.tara.intellij.metamodel.TaraIcons;
import monet.tara.intellij.metamodel.psi.Concept;
import monet.tara.intellij.metamodel.psi.TaraIdentifier;
import monet.tara.intellij.metamodel.psi.TaraTypes;
import monet.tara.intellij.metamodel.psi.impl.TaraPsiImplUtil;
import monet.tara.intellij.metamodel.psi.impl.TaraUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaraSignatureCompletionContributor extends CompletionContributor {

	public static final String MORPH = "morph";

	public TaraSignatureCompletionContributor() {
		extend(CompletionType.BASIC, TaraFilters.afterConceptKey,
			new CompletionProvider<CompletionParameters>() {
				public void addCompletions(@NotNull CompletionParameters parameters,
				                           ProcessingContext context,
				                           @NotNull CompletionResultSet resultSet) {
					resultSet.addElement(LookupElementBuilder.create("polymorphic"));
					resultSet.addElement(LookupElementBuilder.create(MORPH));
					resultSet.addElement(LookupElementBuilder.create("as"));
					resultSet.addElement(LookupElementBuilder.create("abstract"));
					resultSet.addElement(LookupElementBuilder.create("final"));
				}
			}
		);

		extend(CompletionType.BASIC, TaraFilters.inheritance,
			new CompletionProvider<CompletionParameters>() {
				public void addCompletions(@NotNull CompletionParameters parameters,
				                           ProcessingContext context,
				                           @NotNull CompletionResultSet resultSet) {
					resultSet.addAllElements(getVariants(parameters.getOriginalPosition() != null ?
						parameters.getOriginalPosition() : parameters.getPosition()));
				}
			}
		);

		extend(CompletionType.BASIC, TaraFilters.afterPolymorphicOrMorphKey,
			new CompletionProvider<CompletionParameters>() {
				public void addCompletions(@NotNull CompletionParameters parameters,
				                           ProcessingContext context,
				                           @NotNull CompletionResultSet resultSet) {
					resultSet.addElement(LookupElementBuilder.create("as"));
				}
			}
		);

		extend(CompletionType.BASIC, TaraFilters.afterNewLine,
			new CompletionProvider<CompletionParameters>() {
				public void addCompletions(@NotNull CompletionParameters parameters,
				                           ProcessingContext context,
				                           @NotNull CompletionResultSet resultSet) {
					resultSet.addElement(LookupElementBuilder.create("Concept"));
					resultSet.addElement(LookupElementBuilder.create("new"));
					resultSet.addElement(LookupElementBuilder.create("var"));
				}
			}
		);

		extend(CompletionType.BASIC, TaraFilters.afterModifierKey,
			new CompletionProvider<CompletionParameters>() {
				public void addCompletions(@NotNull CompletionParameters parameters,
				                           ProcessingContext context,
				                           @NotNull CompletionResultSet resultSet) {
					resultSet.addElement(LookupElementBuilder.create(MORPH));
					resultSet.addElement(LookupElementBuilder.create("as"));
				}
			}
		);
	}

	public static List<LookupElement> getVariants(PsiElement myElement) {
		List<Concept> concepts = new ArrayList<>();
		if (myElement.getPrevSibling() != null) {
			if (TaraTypes.DOT.equals(myElement.getPrevSibling().getNode().getElementType()))
				getChildrenVariants((TaraIdentifier) myElement.getPrevSibling().getPrevSibling(), concepts);
			else refer(myElement, concepts);
			return fillVariants(concepts);
		}
		return Collections.EMPTY_LIST;
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


}