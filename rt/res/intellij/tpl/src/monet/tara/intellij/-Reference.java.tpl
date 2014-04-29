package monet.::projectName::.intellij;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import monet.::projectName::.intellij.metamodel.::projectProperName::Icons;
import monet.::projectName::.intellij.metamodel.psi.Definition;
import monet.::projectName::.intellij.metamodel.psi.Identifier;
import monet.::projectName::.intellij.metamodel.psi.::projectProperName::Identifier;
import monet.::projectName::.intellij.metamodel.psi.impl.::projectProperName::PsiImplUtil;
import monet.::projectName::.intellij.metamodel.psi.impl.::projectProperName::Util;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ::projectProperName::Reference extends PsiReferenceBase<PsiElement> implements PsiPolyVariantReference {

	public ::projectProperName::Reference(\@NotNull PsiElement element, TextRange textRange) {
		super(element, textRange);
	}

	\@NotNull
	\@Override
	public ResolveResult[] multiResolve(boolean incompleteCode) {
		List<ResolveResult> results = new ArrayList<>();
		PsiElement element = ::projectProperName::Util.resolveReference(myElement);
		if (element != null)
			results.add(new PsiElementResolveResult(element));
		return results.toArray(new ResolveResult[results.size()]);
	}

	\@Nullable
	\@Override
	public PsiElement resolve() {
		ResolveResult[] resolveResults = multiResolve(false);
		return resolveResults.length == 1 ? resolveResults[0].getElement() \: null;
	}

	\@NotNull
	\@Override
	public Object[] getVariants() {
		List<Definition> definitions = new ArrayList<>();
		if (isReferenceToDefinition())
			getVariants((::projectProperName::Identifier) myElement, definitions);
		else if (myElement.getPrevSibling().getPrevSibling() instanceof Identifier)
			getChildrenVariants((::projectProperName::Identifier) myElement.getPrevSibling().getPrevSibling(), definitions);
		return fillVariants(definitions);
	}

	private boolean isReferenceToDefinition() {
		return myElement.getPrevSibling() == null;
	}

	private Object[] fillVariants(List<Definition> definitions) {
		List<LookupElement> variants = new ArrayList<>();
		for (final Definition definition \: definitions)
			if (definition.getName() != null && definition.getName().length() > 0)
				variants.add(LookupElementBuilder.create(definition).withIcon(::projectProperName::Icons.ICON_13).withTypeText(getFileName(definition)));
		return variants.toArray();
	}

	private void getVariants(::projectProperName::Identifier parent, List<Definition> definitions) {
		definitions.addAll(::projectProperName::Util.getRootDefinitions(parent.getProject()));
		Definition context = ::projectProperName::PsiImplUtil.getContextOf(parent);
		definitions.addAll(::projectProperName::Util.getSiblings(context));
	}

	private void getChildrenVariants(::projectProperName::Identifier parent, List<Definition> definitions) {
		Definition definition = ::projectProperName::Util.resolveDefinitionReference(parent);
		Collections.addAll(definitions, ::projectProperName::Util.getChildrenOf(definition));
	}

	private String getFileName(Definition definition) {
		return definition.getContainingFile().getName().split("\\\\.")[0];
	}
}