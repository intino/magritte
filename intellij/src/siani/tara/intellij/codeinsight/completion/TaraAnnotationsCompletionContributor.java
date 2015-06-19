package siani.tara.intellij.codeinsight.completion;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PsiElementPattern;
import com.intellij.psi.PsiElement;
import com.intellij.psi.filters.position.FilterPattern;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.TaraIcons;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.semantic.model.Flags;
import siani.tara.semantic.model.Tag;

import static com.intellij.patterns.PlatformPatterns.psiElement;
import static siani.tara.intellij.lang.psi.TaraTypes.*;


public class TaraAnnotationsCompletionContributor extends CompletionContributor {

	private PsiElementPattern.Capture<PsiElement> afterIsOrInto = psiElement().withLanguage(TaraLanguage.INSTANCE)
		.andOr(new FilterPattern(new TaraFilters.AfterIsFitFilter()), new FilterPattern(new TaraFilters.AfterIntoFitFilter()));

	public TaraAnnotationsCompletionContributor() {
		extend(CompletionType.BASIC, afterIsOrInto, new CompletionProvider<CompletionParameters>() {
				public void addCompletions(@NotNull CompletionParameters parameters,
				                           ProcessingContext context,
				                           @NotNull CompletionResultSet resultSet) {
					addTags(parameters, resultSet);
				}
			}
		);
	}

	private void addTags(@NotNull CompletionParameters parameters, @NotNull CompletionResultSet resultSet) {
		PsiElement annotationContext = getContext(parameters.getPosition());
		if (annotationContext == null) return;
		IElementType elementType = annotationContext.getNode().getElementType();
		if (elementType.equals(NODE) || elementType.equals(SUB)) {
			addNodeTags(resultSet);
		} else if (elementType.equals(HAS)) {
			for (Tag annotation : Flags.HAS_ANNOTATIONS)
				resultSet.addElement(decorate(LookupElementBuilder.create(annotation.name().toLowerCase())));
		} else for (Tag annotation : Flags.VARIABLE_ANNOTATIONS)
			resultSet.addElement(decorate(LookupElementBuilder.create(annotation.name().toLowerCase())));
	}

	private void addNodeTags(CompletionResultSet resultSet) {
		for (Tag tag : Flags.PRIME_ANNOTATIONS)
			resultSet.addElement(decorate(LookupElementBuilder.create(tag.name().toLowerCase())));
	}

	private LookupElementBuilder decorate(LookupElementBuilder builder) {
		return builder.withTypeText("flag", true).withIcon(TaraIcons.ICON_13);
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

}