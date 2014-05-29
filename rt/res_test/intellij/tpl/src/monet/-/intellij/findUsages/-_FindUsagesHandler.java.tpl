package monet.::projectName::.intellij.findUsages;

import com.intellij.find.findUsages.FindUsagesHandler;
import com.intellij.psi.PsiElement;
import monet.::projectName::.intellij.lang.psi.Definition;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public class ::projectProperName::DefinitionFindUsagesHandler extends FindUsagesHandler {
	private final Definition definition;

	public ::projectProperName::DefinitionFindUsagesHandler(\@NotNull Definition psiElement) {
		super(psiElement);
		definition = psiElement;
	}

	\@NotNull
	\@Override
	public PsiElement[] getPrimaryElements() {
		return new PsiElement[]{definition.getIdentifierNode()};
	}

	\@NotNull
	\@Override
	public PsiElement[] getSecondaryElements() {
//		if (definition.getBody() == null) return PsiElement.EMPTY_ARRAY;
//		final List<? extends Definition> definitionList = definition.getBody().getDefinitionList();
//		ArrayList<Identifier> identifiers = new ArrayList<>();
//		for (Definition inner \: definitionList)
//			if (inner.getIdentifierNode() != null)
//				identifiers.add((Identifier) inner.getIdentifierNode());
//		if (!identifiers.isEmpty()) return identifiers.toArray(new PsiElement[identifiers.size()]);
		return PsiElement.EMPTY_ARRAY;
	}

	\@Override
	protected boolean isSearchForTextOccurencesAvailable(\@NotNull PsiElement psiElement, boolean isSingleFile) {
		return true;
	}

	\@Override
	protected Collection<String> getStringsToSearch(PsiElement element) {
		return super.getStringsToSearch(element);
	}
}