package siani.tara.intellij.refactoring;

import com.intellij.find.findUsages.FindUsagesHandler;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.usageView.UsageInfo;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.findusage.TaraFindUsagesHandlerFactory;
import siani.tara.intellij.lang.psi.TaraModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaraRefactoringUtil {

	private TaraRefactoringUtil() {
	}

	@NotNull
	public static List<UsageInfo> findUsages(@NotNull PsiNamedElement element, boolean forHighlightUsages) {
		final List<UsageInfo> usages = new ArrayList<>();
		final FindUsagesHandler handler = new TaraFindUsagesHandlerFactory().createFindUsagesHandler(element, forHighlightUsages);
		assert handler != null;
		final List<PsiElement> elementsToProcess = new ArrayList<>();
		elementsToProcess.addAll(Arrays.asList(handler.getPrimaryElements()));
		elementsToProcess.addAll(Arrays.asList(handler.getSecondaryElements()));
		for (PsiElement e : elementsToProcess)
			handler.processElementUsages(e, usageInfo -> {
				if (!usageInfo.isNonCodeUsage)
					usages.add(usageInfo);
				return true;
			}, FindUsagesHandler.createFindUsagesOptions(element.getProject(), null));
		return usages;
	}

	public static void addImport(TaraModel reference, TaraModel boxFile) {
		reference.addImport(boxFile.getName());
	}
}
