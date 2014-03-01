package monet.tara.intellij;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import monet.tara.intellij.metamodel.TaraIcons;
import monet.tara.intellij.metamodel.psi.Concept;
import monet.tara.intellij.metamodel.psi.impl.TaraUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class TaraReference extends PsiReferenceBase<PsiElement> implements PsiPolyVariantReference {

	public TaraReference(@NotNull PsiElement element, TextRange textRange) {
		super(element, textRange);
	}

	@NotNull
	@Override
	public ResolveResult[] multiResolve(boolean incompleteCode) {
		Project project = myElement.getProject();//TODO
		final Concept concept = TaraUtil.resolveReferences(project, myElement);
		List<ResolveResult> results = new ArrayList<>();
		if (concept != null)
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
		List<Concept> concepts = TaraUtil.findAllConcepts(project);
		List<LookupElement> variants = new ArrayList<>();
		for (final Concept concept : concepts)
			if (concept.getName() != null && concept.getName().length() > 0)
				variants.add(LookupElementBuilder.create(concept).withIcon(TaraIcons.ICON_13).withTypeText(getFileName(concept)));
		return variants.toArray();
	}

	private String getFileName(Concept concept) {
		return concept.getContainingFile().getName().split("\\.")[0];
	}
}