package siani.tara.intellij.codeinsight.completion;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.lang.ASTNode;
import com.intellij.patterns.PsiElementPattern;
import com.intellij.psi.PsiElement;
import com.intellij.psi.filters.ElementFilter;
import com.intellij.psi.filters.position.FilterPattern;
import com.intellij.util.ProcessingContext;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.lang.psi.TaraTypes;
import org.jetbrains.annotations.NotNull;

import static com.intellij.patterns.PlatformPatterns.psiElement;


public class TaraPrimitivesCompletionContributor extends CompletionContributor {

	private PsiElementPattern.Capture<PsiElement> afterVar = psiElement()
		.withLanguage(TaraLanguage.INSTANCE)
		.and(new FilterPattern(new AfterVarFitFilter()));

	public TaraPrimitivesCompletionContributor() {
		extend(CompletionType.BASIC, afterVar,
			new CompletionProvider<CompletionParameters>() {
				public void addCompletions(@NotNull CompletionParameters parameters,
				                           ProcessingContext context,
				                           @NotNull CompletionResultSet resultSet) {
					resultSet.addElement(LookupElementBuilder.create("string"));
					resultSet.addElement(LookupElementBuilder.create("natural"));
					resultSet.addElement(LookupElementBuilder.create("integer"));
					resultSet.addElement(LookupElementBuilder.create("double"));
					resultSet.addElement(LookupElementBuilder.create("boolean"));
					resultSet.addElement(LookupElementBuilder.create("word"));
					resultSet.addElement(LookupElementBuilder.create("date"));
					resultSet.addElement(LookupElementBuilder.create("alias"));
				}
			}
		);
	}

	private static class AfterVarFitFilter implements ElementFilter {
		public boolean isAcceptable(Object element, PsiElement context) {
			if (element instanceof PsiElement && context.getPrevSibling() != null) {
				if (context.getPrevSibling().getPrevSibling() == null) return false;
				final ASTNode ctxPreviousNode = context.getPrevSibling().getPrevSibling().getNode();
				if (TaraTypes.VAR.equals(ctxPreviousNode.getElementType()))
					return true;
			}
			return false;
		}

		public boolean isClassAcceptable(Class hintClass) {
			return true;
		}

	}
}