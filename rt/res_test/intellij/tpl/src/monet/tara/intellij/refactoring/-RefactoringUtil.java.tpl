package monet.::projectName::.intellij.refactoring;

import com.intellij.find.findUsages.FindUsagesHandler;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.usageView.UsageInfo;
import com.intellij.util.Processor;
import monet.::projectName::.intellij.findUsages.::projectProperName::FindUsagesHandlerFactory;
import monet.::projectName::.intellij.lang.psi.HeaderReference;
import monet.::projectName::.intellij.lang.psi.::projectProperName::File;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ::projectProperName::RefactoringUtil {

	private ::projectProperName::RefactoringUtil() {
	}

	\@NotNull
	public static List<UsageInfo> findUsages(\@NotNull PsiNamedElement element, boolean forHighlightUsages) {
		final List<UsageInfo> usages = new ArrayList<>();
		final FindUsagesHandler handler = new ::projectProperName::FindUsagesHandlerFactory().createFindUsagesHandler(element, forHighlightUsages);
		assert handler != null;
		final List<PsiElement> elementsToProcess = new ArrayList<>();
		elementsToProcess.addAll(Arrays.asList(handler.getPrimaryElements()));
		elementsToProcess.addAll(Arrays.asList(handler.getSecondaryElements()));
		for (PsiElement e \: elementsToProcess) {
			handler.processElementUsages(e, new Processor<UsageInfo>() {
				\@Override
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

	public static void addImport(::projectProperName::File reference, ::projectProperName::File newElement) {
		reference.addImport(newElement.getDefinition().getQualifiedName());
	}

	public static void updateImportOfElement(HeaderReference importReference, ::projectProperName::File newElement) {
		::projectProperName::File file = (::projectProperName::File) importReference.getContainingFile();
		importReference.getParent().delete();
		addImport(file, newElement);
	}
}
