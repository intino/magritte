package monet.tara.metamodelplugin;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import monet.tara.metamodelplugin.psi.TaraConceptDefinition;
import monet.tara.metamodelplugin.psi.impl.TaraUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class TaraReference extends PsiReferenceBase<PsiElement> implements PsiPolyVariantReference {
	private String key;

	public TaraReference(@NotNull PsiElement element, TextRange textRange) {
		super(element, textRange);
		key = element.getText().substring(textRange.getStartOffset(), textRange.getEndOffset());
	}

	@NotNull
	@Override
	public ResolveResult[] multiResolve(boolean incompleteCode) {
		Project project = myElement.getProject();
		final List<TaraConceptDefinition> concepts = TaraUtil.findConcept(project, key);
		List<ResolveResult> results = new ArrayList<ResolveResult>();
		for (TaraConceptDefinition concept : concepts)
			results.add(new PsiElementResolveResult(concept));
		return results.toArray(new ResolveResult[results.size()]);
	}

	@Nullable
	@Override
	public PsiElement resolve() {
		ResolveResult[] resolveResults = multiResolve(false);
		return resolveResults.length == 1 ? resolveResults[0].getElement() : null;
	}

	@NotNull
	@Override
	public Object[] getVariants() {
		Project project = myElement.getProject();
		List<TaraConceptDefinition> concepts = TaraUtil.findProperties(project);
		List<LookupElement> variants = new ArrayList<LookupElement>();
		for (final TaraConceptDefinition concept : concepts)
			if (concept.getIdentifier() != null && concept.getIdentifier().length() > 0)
				variants.add(LookupElementBuilder.create(concept).withIcon(TaraIcons.FILE).withTypeText(concept.getContainingFile().getName()));
		return variants.toArray();
	}
}