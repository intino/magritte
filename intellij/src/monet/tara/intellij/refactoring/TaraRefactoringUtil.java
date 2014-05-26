package monet.tara.intellij.refactoring;

import com.intellij.find.findUsages.FindUsagesHandler;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.usageView.UsageInfo;
import com.intellij.util.Processor;
import monet.tara.intellij.findUsages.TaraFindUsagesHandlerFactory;
import monet.tara.intellij.lang.psi.HeaderReference;
import monet.tara.intellij.lang.psi.TaraFile;
import org.jetbrains.annotations.NotNull;

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
		for (PsiElement e : elementsToProcess) {
			handler.processElementUsages(e, new Processor<UsageInfo>() {
				@Override
				public boolean process(UsageInfo usageInfo) {
					if (!usageInfo.isNonCodeUsage) {
						usages.add(usageInfo);
					}
					return true;
				}
			}, FindUsagesHandler.createFindUsagesOptions(element.getProject(), null));
		}
		return usages;
	}

	public static void addImport(TaraFile reference, TaraFile newElement) {
		reference.addImport(newElement.getConcept().getQualifiedName());
	}

	public static void updateImportOfElement(HeaderReference importReference, TaraFile newElement) {
		TaraFile file = (TaraFile) importReference.getContainingFile();
		importReference.getParent().delete();
		addImport(file, newElement);
	}
}
