package monet.tara.intellij.codeinsight.completion;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PsiElementPattern;
import com.intellij.psi.PsiElement;
import com.intellij.psi.filters.ElementFilter;
import com.intellij.psi.filters.position.FilterPattern;
import com.intellij.util.ProcessingContext;
import monet.tara.intellij.lang.TaraLanguage;
import monet.tara.intellij.lang.parser.TaraAnnotation;
import monet.tara.intellij.lang.psi.TaraTypes;
import org.jetbrains.annotations.NotNull;

import static com.intellij.patterns.PlatformPatterns.psiElement;


public class TaraAnnotationsCompletionContributor extends CompletionContributor {

	private PsiElementPattern.Capture<PsiElement> afterVar = psiElement()
		.withLanguage(TaraLanguage.INSTANCE)
		.and(new FilterPattern(new AfterAngleFitFilter()));

	public TaraAnnotationsCompletionContributor() {
		extend(CompletionType.BASIC, afterVar,
			new CompletionProvider<CompletionParameters>() {
				public void addCompletions(@NotNull CompletionParameters parameters,
				                           ProcessingContext context,
				                           @NotNull CompletionResultSet resultSet) {
					for (String annotation : TaraAnnotation.getAnnotations())
						resultSet.addElement(LookupElementBuilder.create(annotation));
				}
			}
		);
	}

	private static class AfterAngleFitFilter implements ElementFilter {
		public boolean isAcceptable(Object element, PsiElement context) {
			if (element instanceof PsiElement) {
				PsiElement ctx = (context.getPrevSibling() != null) ? context : context.getParent();
				while (ctx.getPrevSibling() != null && !TaraTypes.IDENTIFIER_KEY.equals(ctx.getPrevSibling().getNode().getElementType())) {
					if (TaraTypes.OPEN_AN.equals(ctx.getNode().getElementType())) return true;
					if (TaraTypes.CLOSE_AN.equals(ctx.getNode().getElementType())) return false;
					ctx = ctx.getPrevSibling();
				}
			}
			return false;
		}

		public boolean isClassAcceptable(Class hintClass) {
			return true;
		}
	}
}