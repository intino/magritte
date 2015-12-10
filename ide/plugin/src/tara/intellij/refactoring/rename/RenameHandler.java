package tara.intellij.refactoring.rename;

import com.intellij.codeInsight.TargetElementUtilBase;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.ScrollType;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.psi.impl.source.resolve.reference.impl.PsiMultiReference;
import com.intellij.refactoring.rename.PsiElementRenameHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.intellij.lang.TaraLanguage;
import tara.intellij.lang.psi.Identifier;
import tara.intellij.lang.psi.IdentifierReference;
import tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import tara.intellij.lang.psi.resolve.ReferenceManager;
import tara.intellij.lang.psi.resolve.TaraNodeReferenceSolver;

public class RenameHandler extends PsiElementRenameHandler {

	@Nullable
	private static PsiElement getPsiElement(final Editor editor) {
		final PsiReference reference = TargetElementUtilBase.findReference(editor);
		if (reference instanceof TaraNodeReferenceSolver) {
			final ResolveResult[] resolveResults = ((TaraNodeReferenceSolver) reference).multiResolve(false);
			return resolveResults.length > 0 ? resolveResults[0].getElement() : null;
		} else if (reference instanceof PsiMultiReference) {
			final PsiReference[] references = ((PsiMultiReference) reference).getReferences();
			for (PsiReference psiReference : references)
				if (psiReference instanceof TaraNodeReferenceSolver) {
					final ResolveResult[] resolveResults = ((TaraNodeReferenceSolver) psiReference).multiResolve(false);
					if (resolveResults.length > 0) return resolveResults[0].getElement();
				}
		}
		return null;
	}

	public boolean isAvailableOnDataContext(final DataContext dataContext) {
		final Editor editor = LangDataKeys.EDITOR.getData(dataContext);
		final PsiElement data = LangDataKeys.PSI_ELEMENT.getData(dataContext);
		return (data != null && !(data instanceof PsiDirectory) && data.getLanguage() instanceof TaraLanguage) || (editor != null && getPsiElement(editor) != null);
	}

	@Override
	public void invoke(@NotNull final Project project, final Editor editor, final PsiFile file, final DataContext dataContext) {
		PsiElement element = getPsiElement(editor);
		if (element == null) element = LangDataKeys.PSI_ELEMENT.getData(dataContext);
		editor.getScrollingModel().scrollToCaret(ScrollType.MAKE_VISIBLE);
		final PsiElement nameSuggestionContext = file.findElementAt(editor.getCaretModel().getOffset());
		invoke(element, project, nameSuggestionContext, editor);
	}

	private Identifier solveReference(Identifier element) {
		return (Identifier) ReferenceManager.resolve(element);
	}

	private boolean isReference(PsiElement prev) {
		return TaraPsiImplUtil.getContainerByType(prev, IdentifierReference.class) != null;
	}
}
