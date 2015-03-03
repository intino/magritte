package siani.tara.intellij.findusage;

import com.intellij.find.findUsages.FindUsagesHandler;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.psi.TaraBoxFile;

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
	}

	@NotNull
	@Override
	public PsiElement[] getSecondaryElements() {
		return PsiElement.EMPTY_ARRAY;
	}


	@Override
	protected boolean isSearchForTextOccurencesAvailable(@NotNull PsiElement psiElement, boolean isSingleFile) {
		return true;
	}
}
