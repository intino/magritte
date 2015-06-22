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
import siani.tara.intellij.lang.lexer.TaraPrimitives;
import siani.tara.intellij.lang.psi.Node;
import siani.tara.intellij.lang.psi.TaraTypes;
import siani.tara.intellij.lang.psi.TaraVariableType;
import siani.tara.intellij.lang.psi.Variable;

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
					for (String primitive : TaraPrimitives.getPrimitives())
						resultSet.addElement(LookupElementBuilder.create(primitive + " ").withTypeText("Primitive"));
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

	private static class AfterVarFitFilter implements ElementFilter {
		public boolean isAcceptable(Object element, PsiElement context) {
			if (element instanceof PsiElement && isInAttribute(context)) {
				PsiElement parent = getVariableType(context);
				if (parent == null) return false;
				if (parent.getPrevSibling() == null || parent.getPrevSibling().getPrevSibling() == null) return false;

				final ASTNode ctxPreviousNode = parent.getPrevSibling().getPrevSibling().getNode();
				if (TaraTypes.VAR.equals(ctxPreviousNode.getElementType()))
					return true;
			}
			return false;
		}

		private boolean isInAttribute(PsiElement context) {
			PsiElement parent = context.getParent();
			while (parent != null && !(parent instanceof Node)) {
				if (parent instanceof Variable) return true;
				parent = parent.getParent();
			}
			return false;
		}

		public TaraVariableType getVariableType(PsiElement element) {
			PsiElement parent = element.getParent();
			while (parent != null && !(parent instanceof Node)) {
				if (parent instanceof TaraVariableType) return (TaraVariableType) parent;
				parent = parent.getParent();
			}
			return null;
		}

		public boolean isClassAcceptable(Class hintClass) {
			return true;
		}

	}

}