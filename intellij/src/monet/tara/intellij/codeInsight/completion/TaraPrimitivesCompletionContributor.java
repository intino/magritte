package monet.tara.intellij.codeInsight.completion;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.lang.ASTNode;
import com.intellij.patterns.PsiElementPattern;
import com.intellij.psi.PsiElement;
import com.intellij.psi.filters.ElementFilter;
import com.intellij.psi.filters.position.FilterPattern;
import com.intellij.util.ProcessingContext;
import monet.tara.intellij.metamodel.TaraLanguage;
import monet.tara.intellij.metamodel.psi.TaraTypes;
import org.jetbrains.annotations.NotNull;

import static com.intellij.patterns.PlatformPatterns.psiElement;


public class TaraPrimitivesCompletionContributor extends CompletionContributor {

	private PsiElementPattern.Capture<PsiElement> afterVar = psiElement()
		.withLanguage(TaraLanguage.INSTANCE)
		.and(new FilterPattern(new AfterVarFitFilter()));

	public TaraPrimitivesCompletionContributor() {
		extend(CompletionType.SMART, afterVar,
			new CompletionProvider<CompletionParameters>() {
				public void addCompletions(@NotNull CompletionParameters parameters,
				                           ProcessingContext context,
				                           @NotNull CompletionResultSet resultSet) {
					resultSet.addElement(LookupElementBuilder.create("String"));
					resultSet.addElement(LookupElementBuilder.create("Natural"));
					resultSet.addElement(LookupElementBuilder.create("Integer"));
					resultSet.addElement(LookupElementBuilder.create("Double"));
					resultSet.addElement(LookupElementBuilder.create("Boolean"));
					resultSet.addElement(LookupElementBuilder.create("Uid"));
				}
			});
	}

	private static class AfterVarFitFilter implements ElementFilter {
		public boolean isAcceptable(Object element, PsiElement context) {
			if (element instanceof PsiElement)
				if (context.getPrevSibling() != null) {
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