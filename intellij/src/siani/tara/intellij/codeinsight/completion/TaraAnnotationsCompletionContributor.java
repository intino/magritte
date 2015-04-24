package siani.tara.intellij.codeinsight.completion;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PsiElementPattern;
import com.intellij.psi.PsiElement;
import com.intellij.psi.filters.ElementFilter;
import com.intellij.psi.filters.position.FilterPattern;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.lang.lexer.Flags;
import siani.tara.intellij.lang.lexer.Tag;
import siani.tara.intellij.lang.psi.Node;
import siani.tara.intellij.lang.psi.TaraAnnotations;

import static com.intellij.patterns.PlatformPatterns.psiElement;
import static siani.tara.intellij.lang.psi.TaraTypes.*;


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
					PsiElement annotationContext = getContext(parameters.getPosition());
					if (annotationContext == null) return;
					IElementType elementType = annotationContext.getNode().getElementType();
					if (elementType.equals(NODE) || elementType.equals(SUB)) {
						addNodeAnnotations(resultSet);
					} else if (elementType.equals(HAS)) {
						for (Tag annotation : Flags.HAS_ANNOTATIONS)
							resultSet.addElement(LookupElementBuilder.create(annotation.getName()));
					} else for (Tag annotation : Flags.VARIABLE_ANNOTATIONS)
						resultSet.addElement(LookupElementBuilder.create(annotation.getName()));
				}
			}
		);
	}

	private void addNodeAnnotations(CompletionResultSet resultSet) {
		for (Tag tags : Flags.PRIME_ANNOTATIONS)
			resultSet.addElement(LookupElementBuilder.create(tags.getName()));
	}

	public PsiElement getContext(PsiElement element) {
		PsiElement context = element;
		while ((context = context.getPrevSibling()) != null) {
			if (is(context, VAR) || is(context, HAS) || is(context, NODE) || is(context, SUB))
				return context;
		}
		return null;
	}

	private boolean is(PsiElement context, IElementType type) {
		return context.getNode().getElementType().equals(type);
	}

	private static class AfterIsFitFilter implements ElementFilter {
		public boolean isAcceptable(Object element, PsiElement context) {
			return inAnnotations(context);
		}

		private boolean inAnnotations(PsiElement context) {
			PsiElement ctx = (context.getPrevSibling() != null) ? context : context.getParent();
			while (ctx.getPrevSibling() != null && !IDENTIFIER_KEY.equals(ctx.getPrevSibling().getNode().getElementType())) {
				if (IS.equals(ctx.getNode().getElementType())) return true;
				ctx = ctx.getPrevSibling();
			}
			ctx = ctx.getParent();
			while (ctx != null && !Node.class.isInstance(ctx)) {
				if (ctx instanceof TaraAnnotations) return true;
				ctx = ctx.getParent();
			}
			return false;
		}

		public boolean isClassAcceptable(Class hintClass) {
			return true;
		}
	}
}