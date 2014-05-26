package monet.tara.intellij.findUsages;

import com.intellij.find.findUsages.FindUsagesHandler;
import com.intellij.psi.PsiElement;
import monet.tara.intellij.lang.psi.Concept;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public class TaraConceptFindUsagesHandler extends FindUsagesHandler {
	private final Concept concept;

	public TaraConceptFindUsagesHandler(@NotNull Concept psiElement) {
		super(psiElement);
		concept = psiElement;
	}

	@NotNull
	@Override
	public PsiElement[] getPrimaryElements() {
		return new PsiElement[]{concept.getIdentifierNode()};
	}

	@NotNull
	@Override
	public PsiElement[] getSecondaryElements() {
//		if (concept.getBody() == null) return PsiElement.EMPTY_ARRAY;
//		final List<? extends Concept> conceptList = concept.getBody().getConceptList();
//		ArrayList<Identifier> identifiers = new ArrayList<>();
//		for (Concept inner : conceptList)
//			if (inner.getIdentifierNode() != null)
//				identifiers.add((Identifier) inner.getIdentifierNode());
//		if (!identifiers.isEmpty()) return identifiers.toArray(new PsiElement[identifiers.size()]);
		return PsiElement.EMPTY_ARRAY;
	}

	@Override
	protected boolean isSearchForTextOccurencesAvailable(@NotNull PsiElement psiElement, boolean isSingleFile) {
		return true;
	}

	@Override
	protected Collection<String> getStringsToSearch(PsiElement element) {
		return super.getStringsToSearch(element);
	}
}