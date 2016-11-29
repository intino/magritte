package tara.intellij.codeinsight.completion;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.module.Module;
import com.intellij.patterns.PsiElementPattern;
import com.intellij.psi.PsiElement;
import com.intellij.psi.filters.position.FilterPattern;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import tara.compiler.shared.Configuration;
import tara.intellij.lang.TaraIcons;
import tara.intellij.lang.TaraLanguage;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.intellij.project.TaraModuleType;
import tara.lang.model.Flags;
import tara.lang.model.Tag;

import static com.intellij.patterns.PlatformPatterns.psiElement;
import static tara.compiler.shared.Configuration.Level.Application;
import static tara.compiler.shared.Configuration.Level.System;
import static tara.intellij.lang.psi.TaraTypes.*;
import static tara.intellij.project.module.ModuleProvider.moduleOf;


public class TaraAnnotationsCompletionContributor extends CompletionContributor {

	private PsiElementPattern.Capture<PsiElement> afterIs = psiElement().withLanguage(TaraLanguage.INSTANCE)
		.and(new FilterPattern(new TaraFilters.AfterIsFitFilter()));
	private PsiElementPattern.Capture<PsiElement> afterInto = psiElement().withLanguage(TaraLanguage.INSTANCE)
		.and(new FilterPattern(new TaraFilters.AfterIntoFitFilter()));

	public TaraAnnotationsCompletionContributor() {
		addAfterInto();
		addAfterIs();
	}

	private void addAfterIs() {
		extend(CompletionType.BASIC, afterIs, new CompletionProvider<CompletionParameters>() {
				public void addCompletions(@NotNull CompletionParameters parameters,
										   ProcessingContext context,
										   @NotNull CompletionResultSet resultSet) {
					final Module module = moduleOf(parameters.getOriginalFile());
					if (!TaraModuleType.isTara(module)) return;
					addTags(parameters, resultSet);
				}
			}
		);
	}

	private void addAfterInto() {
		extend(CompletionType.BASIC, afterInto, new CompletionProvider<CompletionParameters>() {
				public void addCompletions(@NotNull CompletionParameters parameters,
										   ProcessingContext context,
										   @NotNull CompletionResultSet resultSet) {
					final Module module = moduleOf(parameters.getOriginalFile());
					if (!TaraModuleType.isTara(module)) return;
					final Configuration.Level type = TaraUtil.configurationOf(module).level();
					if (type.equals(System) || type.equals(Application)) return;
					addTags(parameters, resultSet);
				}
			}
		);
	}

	private void addTags(@NotNull CompletionParameters parameters, @NotNull CompletionResultSet resultSet) {
		PsiElement annotationContext = getContext(parameters.getPosition());
		if (annotationContext == null) return;
		IElementType elementType = annotationContext.getNode().getElementType();
		if (elementType.equals(IDENTIFIER_KEY) || elementType.equals(METAIDENTIFIER_KEY) || elementType.equals(SUB))
			addNodeTags(resultSet);
		else if (elementType.equals(HAS)) addHasAnnotations(resultSet);
		else addVariableAnnotations(resultSet);
	}

	private void addVariableAnnotations(@NotNull CompletionResultSet resultSet) {
		for (Tag annotation : Flags.forVariable())
			resultSet.addElement(decorate(LookupElementBuilder.create(annotation.name().toLowerCase())));
	}

	private void addHasAnnotations(@NotNull CompletionResultSet resultSet) {
		for (Tag annotation : Flags.forReference())
			resultSet.addElement(decorate(LookupElementBuilder.create(annotation.name().toLowerCase())));
	}

	private void addNodeTags(CompletionResultSet resultSet) {
		for (Tag tag : Flags.forRoot())
			resultSet.addElement(decorate(LookupElementBuilder.create(tag.name().toLowerCase())));
	}

	private LookupElementBuilder decorate(LookupElementBuilder builder) {
		return builder.withTypeText("flag", true).withIcon(TaraIcons.ICON_16);
	}

	private PsiElement getContext(PsiElement element) {
		PsiElement context = element.getContext();
		while ((context = context.getPrevSibling()) != null)
			if (isStartingToken(context) || (context.getPrevSibling() != null && isAfterBreakLine(context)))
				return context;
		return null;
	}

	private boolean isStartingToken(PsiElement context) {
		return is(context, VAR) || is(context, HAS) || is(context, SUB) || is(context, METAIDENTIFIER_KEY);
	}

	private boolean isAfterBreakLine(PsiElement context) {
		return is(context.getPrevSibling(), DEDENT) || is(context.getPrevSibling(), NEWLINE) || is(context.getPrevSibling(), NEW_LINE_INDENT) ||
			is(context.getPrevSibling(), IMPORTS) || is(context.getPrevSibling(), DSL_DECLARATION);
	}

	private boolean is(PsiElement context, IElementType type) {
		return context.getNode().getElementType().equals(type);
	}

}