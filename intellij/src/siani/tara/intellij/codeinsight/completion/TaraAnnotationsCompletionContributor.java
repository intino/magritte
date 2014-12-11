package siani.tara.intellij.codeinsight.completion;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PsiElementPattern;
import com.intellij.psi.PsiElement;
import com.intellij.psi.filters.ElementFilter;
import com.intellij.psi.filters.position.FilterPattern;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.TaraAnnotationsAndFacets;
import siani.tara.intellij.lang.psi.TaraTypes;
import siani.tara.lang.Annotations;

import static com.intellij.patterns.PlatformPatterns.psiElement;


public class TaraAnnotationsCompletionContributor extends CompletionContributor {

	private PsiElementPattern.Capture<PsiElement> afterIs = psiElement()
		.withLanguage(TaraLanguage.INSTANCE)
		.and(new FilterPattern(new AfterIsFitFilter()));

	public TaraAnnotationsCompletionContributor() {
		extend(CompletionType.BASIC, afterIs,
			new CompletionProvider<CompletionParameters>() {
				public void addCompletions(@NotNull CompletionParameters parameters,
				                           ProcessingContext context,
				                           @NotNull CompletionResultSet resultSet) {
					for (String annotation : Annotations.getAnnotations())
						resultSet.addElement(LookupElementBuilder.create(annotation));
				}
			}
		);
	}

	private static class AfterIsFitFilter implements ElementFilter {
		public boolean isAcceptable(Object element, PsiElement context) {
			return inAnnotations(context);
		}

		private boolean inAnnotations(PsiElement context) {
			PsiElement ctx = (context.getPrevSibling() != null) ? context : context.getParent();
			while (ctx.getPrevSibling() != null && !TaraTypes.IDENTIFIER_KEY.equals(ctx.getPrevSibling().getNode().getElementType())) {
				if (TaraTypes.IS.equals(ctx.getNode().getElementType())) return true;
				ctx = ctx.getPrevSibling();
			}
			ctx = ctx.getParent();
			while (ctx != null && !Concept.class.isInstance(ctx)) {
				if (ctx instanceof TaraAnnotationsAndFacets) return true;
				ctx = ctx.getParent();
			}
			return false;
		}

		public boolean isClassAcceptable(Class hintClass) {
			return true;
		}
	}
}