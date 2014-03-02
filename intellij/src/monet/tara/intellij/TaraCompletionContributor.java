package monet.tara.intellij;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.patterns.PsiElementPattern;
import com.intellij.psi.PsiElement;
import com.intellij.psi.filters.position.FilterPattern;
import com.intellij.util.ProcessingContext;
import monet.tara.intellij.codeInsight.completion.StatementFitFilter;
import monet.tara.intellij.metamodel.TaraLanguage;
import monet.tara.intellij.metamodel.psi.TaraTypes;
import org.jetbrains.annotations.NotNull;

import static com.intellij.patterns.PlatformPatterns.psiElement;


public class TaraCompletionContributor extends CompletionContributor {

	private static final FilterPattern IN_BEGIN_STMT = new FilterPattern(new StatementFitFilter());
	private PsiElementPattern.Capture<PsiElement> inSignature = psiElement()
		.withLanguage(TaraLanguage.INSTANCE)
		.and(IN_BEGIN_STMT);
	private static final FilterPattern IN_BODY = new FilterPattern(new StatementFitFilter());

	public TaraCompletionContributor() {
//		extend(CompletionType.BASIC, inSignature,
//			new CompletionProvider<CompletionParameters>() {
//				public void addCompletions(@NotNull CompletionParameters parameters,
//				                           ProcessingContext context,
//				                           @NotNull CompletionResultSet resultSet) {
//					resultSet.addElement(LookupElementBuilder.create("Concept"));
//				}
//			}
//		);

		extend(CompletionType.BASIC, PlatformPatterns.psiElement(TaraTypes.CONCEPT_KEY).withLanguage(TaraLanguage.INSTANCE).
			withParent(PlatformPatterns.psiElement(TaraTypes.SIGNATURE)),
			new CompletionProvider<CompletionParameters>() {
				public void addCompletions(@NotNull CompletionParameters parameters,
				                           ProcessingContext context,
				                           @NotNull CompletionResultSet resultSet) {
					resultSet.addElement(LookupElementBuilder.create("Concept"));
				}
			}
		);
	}

	@Override
	public void beforeCompletion(@NotNull CompletionInitializationContext context) {
		context.setDummyIdentifier("");
		context.getOffsetMap().addOffset(CompletionInitializationContext.START_OFFSET, context.getStartOffset()-1);
	}

//	public void fillCompletionVariants(CompletionParameters parameters, final CompletionResultSet result) {
//		CompletionService.getCompletionService().getVariantsFromContributors(parameters.delegateToClassName(), null, new Consumer<CompletionResult>() {
//			public void consume(final CompletionResult completionResult) {
//				result.passResult(completionResult);
//			}
//		});
//	}
}