package monet.tara.compiler.intellij;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import monet.tara.compiler.intellij.metamodel.TaraIcons;
import monet.tara.compiler.intellij.psi.IConcept;
import monet.tara.compiler.intellij.psi.impl.TaraUtil;
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
		final List<IConcept> concepts = TaraUtil.findConcept(project, key, myElement);
		List<ResolveResult> results = new ArrayList<>();
		for (IConcept concept : concepts)
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
		List<IConcept> concepts = TaraUtil.findConcepts(project);
		List<LookupElement> variants = new ArrayList<>();
		for (final IConcept concept : concepts)
			if (concept.getName() != null && concept.getName().length() > 0)
				variants.add(LookupElementBuilder.create(concept).withIcon(TaraIcons.ICON).withTypeText(getFileName(concept)));
		return variants.toArray();
	}

	private String getFileName(IConcept concept) {
		return concept.getContainingFile().getName().split("\\.")[0];
	}
}