package siani.tara.intellij.codeinsight.completion;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.lang.ASTNode;
import com.intellij.patterns.PsiElementPattern;
import com.intellij.psi.PsiElement;
import com.intellij.psi.filters.ElementFilter;
import com.intellij.psi.filters.position.FilterPattern;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.TaraTypes;
import siani.tara.intellij.lang.psi.Variable;
import siani.tara.lang.Primitives;

import static com.intellij.patterns.PlatformPatterns.psiElement;


public class TaraPrimitivesCompletionContributor extends CompletionContributor {

	private PsiElementPattern.Capture<PsiElement> afterVar = psiElement()
		.withLanguage(TaraLanguage.INSTANCE)
		.and(new FilterPattern(new AfterSlotFitFilter()));

	public TaraPrimitivesCompletionContributor() {
		extend(CompletionType.BASIC, afterVar,
			new CompletionProvider<CompletionParameters>() {
				public void addCompletions(@NotNull CompletionParameters parameters,
				                           ProcessingContext context,
				                           @NotNull CompletionResultSet resultSet) {
					for (String primitive : Primitives.getPrimitives())
						resultSet.addElement(LookupElementBuilder.create(primitive + " "));
				}
			}
		);

		extend(CompletionType.BASIC, TaraFilters.afterEquals,
			new CompletionProvider<CompletionParameters>() {
				public void addCompletions(@NotNull CompletionParameters parameters,
				                           ProcessingContext context,
				                           @NotNull CompletionResultSet resultSet) {
					resultSet.addElement(LookupElementBuilder.create("empty"));
				}
			}
		);
	}

	private static class AfterSlotFitFilter implements ElementFilter {
		public boolean isAcceptable(Object element, PsiElement context) {
			if (element instanceof PsiElement && isInAttribute(context)) {
				PsiElement parent = context.getParent().getParent();
				if (parent == null) return false;
				if (parent.getPrevSibling() == null || parent.getPrevSibling().getPrevSibling() == null) return false;

				final ASTNode ctxPreviousNode = parent.getPrevSibling().getPrevSibling().getNode();
				if (TaraTypes.SLOT.equals(ctxPreviousNode.getElementType()))
					return true;
			}
			return false;
		}

		private boolean isInAttribute(PsiElement context) {
			PsiElement parent = context.getParent();
			while (parent != null && !(parent instanceof Concept)) {
				if (parent instanceof Variable) return true;
				parent = parent.getParent();
			}
			return false;
		}

		public boolean isClassAcceptable(Class hintClass) {
			return true;
		}

	}
}