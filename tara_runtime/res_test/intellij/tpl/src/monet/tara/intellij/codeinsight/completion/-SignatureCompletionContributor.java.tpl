package monet.::projectName::.intellij.codeinsight.completion;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.lang.ASTNode;
import com.intellij.patterns.PsiElementPattern;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiErrorElement;
import com.intellij.psi.filters.ElementFilter;
import com.intellij.psi.filters.position.FilterPattern;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.ProcessingContext;
import monet.::projectName::.intellij.metamodel.::projectProperName::Icons;
import monet.::projectName::.intellij.metamodel.::projectProperName::Language;
import monet.::projectName::.intellij.metamodel.psi.Definition;
import monet.::projectName::.intellij.metamodel.psi.::projectProperName::Identifier;
import monet.::projectName::.intellij.metamodel.psi.::projectProperName::Types;
import monet.::projectName::.intellij.metamodel.psi.impl.::projectProperName::PsiImplUtil;
import monet.::projectName::.intellij.metamodel.psi.impl.::projectProperName::Util;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.intellij.patterns.PlatformPatterns.psiElement;

public class ::projectProperName::SignatureCompletionContributor extends CompletionContributor {

	public static final String MORPH = "morph";
	private PsiElementPattern.Capture<PsiElement> afterNewLine = psiElement().withLanguage(::projectProperName::Language.INSTANCE)
		.and(new FilterPattern(new InErrorFilter()));

	private PsiElementPattern.Capture<PsiElement> afterDefinitionKey = psiElement()
		.withLanguage(::projectProperName::Language.INSTANCE)
		.and(new FilterPattern(new InSignatureFitFilter()));

	private PsiElementPattern.Capture<PsiElement> afterPolymorphicOrMorphKey = psiElement()
		.withLanguage(::projectProperName::Language.INSTANCE)
		.and(new FilterPattern(new InSignatureFitFilter()))
		.andOr(new FilterPattern(new AfterElementFitFilter(::projectProperName::Types.POLYMORPHIC_KEY)),
			new FilterPattern(new AfterElementFitFilter(::projectProperName::Types.MORPH_KEY)));

	private PsiElementPattern.Capture<PsiElement> afterModifierKey = psiElement()
		.withLanguage(::projectProperName::Language.INSTANCE)
		.and(new FilterPattern(new InSignatureFitFilter()))
		.andOr(new FilterPattern(new AfterElementFitFilter(::projectProperName::Types.FINAL)),
			new FilterPattern(new AfterElementFitFilter(::projectProperName::Types.ABSTRACT)));

	public ::projectProperName::SignatureCompletionContributor() {
		extend(CompletionType.BASIC, afterDefinitionKey,
			new CompletionProvider<CompletionParameters>() {
				public void addCompletions(\@NotNull CompletionParameters parameters,
				                           ProcessingContext context,
				                           \@NotNull CompletionResultSet resultSet) {
					resultSet.addElement(LookupElementBuilder.create("polymorphic"));
					resultSet.addElement(LookupElementBuilder.create(MORPH));
					resultSet.addElement(LookupElementBuilder.create("as"));
					resultSet.addElement(LookupElementBuilder.create("abstract"));
					resultSet.addElement(LookupElementBuilder.create("final"));
					resultSet.addAllElements(getVariants(parameters.getOriginalPosition()));
				}
			}
		);

		extend(CompletionType.BASIC, afterPolymorphicOrMorphKey,
			new CompletionProvider<CompletionParameters>() {
				public void addCompletions(\@NotNull CompletionParameters parameters,
				                           ProcessingContext context,
				                           \@NotNull CompletionResultSet resultSet) {
					resultSet.addElement(LookupElementBuilder.create("as"));
				}
			}
		);


		extend(CompletionType.BASIC, afterNewLine,
			new CompletionProvider<CompletionParameters>() {
				public void addCompletions(\@NotNull CompletionParameters parameters,
				                           ProcessingContext context,
				                           \@NotNull CompletionResultSet resultSet) {
					resultSet.addElement(LookupElementBuilder.create("Definition"));
					resultSet.addElement(LookupElementBuilder.create("new"));
					resultSet.addElement(LookupElementBuilder.create("var"));
				}
			}
		);

		extend(CompletionType.BASIC, afterModifierKey,
			new CompletionProvider<CompletionParameters>() {
				public void addCompletions(\@NotNull CompletionParameters parameters,
				                           ProcessingContext context,
				                           \@NotNull CompletionResultSet resultSet) {
					resultSet.addElement(LookupElementBuilder.create(MORPH));
					resultSet.addElement(LookupElementBuilder.create("as"));
				}
			}
		);
	}

	public static List<LookupElement> getVariants(PsiElement myElement) {
		List<Definition> definitions = new ArrayList<>();
		if (myElement.getPrevSibling() != null) {
			if (::projectProperName::Types.DOT.equals(myElement.getPrevSibling().getNode()))
				getChildrenVariants((::projectProperName::Identifier) myElement.getPrevSibling().getPrevSibling(), definitions);
			else refer(myElement, definitions);
			return fillVariants(definitions);
		}
		return Collections.EMPTY_LIST;
	}

	private static List<LookupElement> fillVariants(List<Definition> definitions) {
		List<LookupElement> variants = new ArrayList<>();
		for (final Definition definition \: definitions)
			if (definition.getName() != null && definition.getName().length() > 0)
				variants.add(LookupElementBuilder.create(definition).withIcon(::projectProperName::Icons.ICON_13).withTypeText(getFileName(definition)));
		return variants;
	}

	private static void refer(PsiElement parent, List<Definition> definitions) {
		definitions.addAll(::projectProperName::Util.getRootDefinitions(parent.getProject()));
		Definition context = ::projectProperName::PsiImplUtil.getContextOf(parent);
		definitions.addAll(::projectProperName::Util.getSiblings(context));
	}

	private static void getChildrenVariants(::projectProperName::Identifier parent, List<Definition> definitions) {
		Definition definition = ::projectProperName::Util.resolveReferences(parent.getProject(), parent);
		Collections.addAll(definitions, ::projectProperName::Util.getChildrenOf(definition));
	}

	private static String getFileName(Definition definition) {
		return definition.getContainingFile().getName().split("\\\\.")[0];
	}

	private static class AfterElementFitFilter implements ElementFilter {
		IElementType type;

		private AfterElementFitFilter(IElementType type) {
			this.type = type;
		}

		public boolean isAcceptable(Object element, PsiElement context) {
			if (element instanceof PsiElement && context.getPrevSibling() != null && context.getPrevSibling().getPrevSibling() != null) {
				final ASTNode ctxPreviousNode = context.getPrevSibling().getPrevSibling().getNode();
				if (type.equals(ctxPreviousNode.getElementType())) return true;
			}
			return false;
		}

		public boolean isClassAcceptable(Class hintClass) {
			return true;
		}
	}

	private class InErrorFilter implements ElementFilter {
		\@Override
		public boolean isAcceptable(Object element, \@Nullable PsiElement context) {
			if (element instanceof PsiElement) {
				assert context != null;
				if (((PsiElement)element).getParent() instanceof PsiErrorElement) return true;
			}
			return false;
		}

		\@Override
		public boolean isClassAcceptable(Class hintClass) {
			return true;
		}
	}
}