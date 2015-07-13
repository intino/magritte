package tara.intellij.findusage;

import com.intellij.find.findUsages.FindUsagesHandler;
import com.intellij.find.findUsages.FindUsagesOptions;
import com.intellij.psi.PsiElement;
import com.intellij.usageView.UsageInfo;
import com.intellij.util.Processor;
import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.psi.TaraModel;

public class TaraFileFindUsagesHandler extends FindUsagesHandler {
	private final TaraModel boxFile;

	public TaraFileFindUsagesHandler(TaraModel boxFile) {
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

	@Override
	public boolean processElementUsages(@NotNull PsiElement element, @NotNull Processor<UsageInfo> processor, @NotNull FindUsagesOptions options) {
		return super.processElementUsages(element, processor, options);
	}


}
