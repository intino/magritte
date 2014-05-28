package monet.::projectName::.intellij.findUsages;

import com.intellij.find.findUsages.FindUsagesHandler;
import com.intellij.find.findUsages.FindUsagesHandlerFactory;
import com.intellij.psi.PsiElement;
import monet.::projectName::.intellij.lang.psi.::projectProperName::Definition;
import monet.::projectName::.intellij.lang.psi.::projectProperName::File;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ::projectProperName::FindUsagesHandlerFactory extends FindUsagesHandlerFactory {

	\@Override
	public boolean canFindUsages(\@NotNull PsiElement element) {
		return element instanceof ::projectProperName::Definition;
	}

	\@Nullable
	\@Override
	public FindUsagesHandler createFindUsagesHandler(\@NotNull PsiElement element, boolean forHighlightUsages) {
		return element instanceof ::projectProperName::File ? new ::projectProperName::DefinitionFindUsagesHandler(((::projectProperName::File) element).getDefinition()) \: null;
	}
}
