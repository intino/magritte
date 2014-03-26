package monet.::projectName::.intellij.codeinsight.completion;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PsiElementPattern;
import com.intellij.psi.PsiElement;
import com.intellij.psi.filters.ElementFilter;
import com.intellij.psi.filters.position.FilterPattern;
import com.intellij.util.ProcessingContext;
import monet.::projectName::.intellij.metamodel.::projectProperName::Language;
import monet.::projectName::.intellij.metamodel.psi.::projectProperName::Types;
import org.jetbrains.annotations.NotNull;

import static com.intellij.patterns.PlatformPatterns.psiElement;


public class ::projectProperName::AnnotationsCompletionContributor extends CompletionContributor {

	private PsiElementPattern.Capture<PsiElement> afterVar = psiElement()
		.withLanguage(::projectProperName::Language.INSTANCE)
		.and(new FilterPattern(new AfterAngleFitFilter()));

	public ::projectProperName::AnnotationsCompletionContributor() {
		extend(CompletionType.SMART, afterVar,
			new CompletionProvider<CompletionParameters>() {
				public void addCompletions(\@NotNull CompletionParameters parameters,
				                           ProcessingContext context,
				                           \@NotNull CompletionResultSet resultSet) {
					resultSet.addElement(LookupElementBuilder.create("optional"));
					resultSet.addElement(LookupElementBuilder.create("multiple"));
					resultSet.addElement(LookupElementBuilder.create("root"));
					resultSet.addElement(LookupElementBuilder.create("singleton"));
					resultSet.addElement(LookupElementBuilder.create("has-code"));
					resultSet.addElement(LookupElementBuilder.create("extensible"));
				}
			});
	}

	private static class AfterAngleFitFilter implements ElementFilter {
		public boolean isAcceptable(Object element, PsiElement context) {
			if (element instanceof PsiElement) {
				PsiElement ctx = (context.getPrevSibling() != null) ? context \: context.getParent();
				while (ctx.getPrevSibling() != null && !::projectProperName::Types.IDENTIFIER_KEY.equals(ctx.getPrevSibling().getNode().getElementType())) {
					if (::projectProperName::Types.OPEN_AN.equals(ctx.getNode().getElementType())) return true;
					if (::projectProperName::Types.CLOSE_AN.equals(ctx.getNode().getElementType())) return false;
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