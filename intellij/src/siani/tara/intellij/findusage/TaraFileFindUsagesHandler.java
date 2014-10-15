package siani.tara.intellij.findusage;

import com.intellij.find.findUsages.FindUsagesHandler;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.Identifier;
import siani.tara.intellij.lang.psi.TaraBoxFile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TaraFileFindUsagesHandler extends FindUsagesHandler {
	private final TaraBoxFile boxFile;

	public TaraFileFindUsagesHandler(TaraBoxFile boxFile) {
		super(boxFile);
		this.boxFile = boxFile;
	}

	@NotNull
	@Override
	public PsiElement[] getPrimaryElements() {
		return new PsiElement[]{boxFile};
//		List<Identifier> list = new ArrayList();
//		for (Concept concept : boxFile.getConcepts())
//			list.add(concept.getIdentifierNode());
//		return list.toArray(new Identifier[list.size()]);
	}

	@NotNull
	@Override
	public PsiElement[] getSecondaryElements() {
		return PsiElement.EMPTY_ARRAY;
	}

	private PsiElement[] getChildren() {
		List<PsiElement> identifiers = new ArrayList();
		for (Concept concept : boxFile.getConcepts()) {
			identifiers.addAll(getIdentifiersOfConcepts(concept.getSubConcepts()));
			identifiers.addAll(getIdentifiersOfConcepts(concept.getInnerConcepts()));
		}
		return identifiers.toArray(new PsiElement[identifiers.size()]);
	}

	private List<Identifier> getIdentifiersOfConcepts(Collection<Concept> concepts) {
		List<Identifier> list = new ArrayList();
		for (Concept concept : concepts) list.add(concept.getIdentifierNode());
		return list;
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
