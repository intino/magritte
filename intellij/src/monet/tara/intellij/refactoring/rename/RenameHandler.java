package monet.tara.intellij.refactoring.rename;

import com.intellij.codeInsight.TargetElementUtilBase;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.ScrollType;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiReference;
import com.intellij.psi.ResolveResult;
import com.intellij.psi.impl.source.resolve.reference.impl.PsiMultiReference;
import com.intellij.refactoring.rename.PsiElementRenameHandler;
import monet.tara.intellij.lang.psi.resolve.TaraReferenceSolver;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RenameHandler extends PsiElementRenameHandler {

	@Nullable
	private static PsiElement getPsiElement(final Editor editor) {
		final PsiReference reference = TargetElementUtilBase.findReference(editor);
		if (reference instanceof TaraReferenceSolver) {
			final ResolveResult[] resolveResults = ((TaraReferenceSolver) reference).multiResolve(false);
			return resolveResults.length > 0 ? resolveResults[0].getElement() : null;
		} else if (reference instanceof PsiMultiReference) {
			final PsiReference[] references = ((PsiMultiReference) reference).getReferences();
			for (PsiReference psiReference : references)
				if (psiReference instanceof TaraReferenceSolver) {
					final ResolveResult[] resolveResults = ((TaraReferenceSolver) psiReference).multiResolve(false);
					if (resolveResults.length > 0) return resolveResults[0].getElement();
				}
		}
		return null;
	}

	public boolean isAvailableOnDataContext(final DataContext dataContext) {
		final Editor editor = LangDataKeys.EDITOR.getData(dataContext);
		return editor != null && getPsiElement(editor) != null;
	}

	@Override
	public void invoke(@NotNull final Project project, final Editor editor, final PsiFile file, final DataContext dataContext) {
		PsiElement element = getPsiElement(editor);
		editor.getScrollingModel().scrollToCaret(ScrollType.MAKE_VISIBLE);
		final PsiElement nameSuggestionContext = file.findElementAt(editor.getCaretModel().getOffset());
		invoke(element, project, nameSuggestionContext, editor);
	}
}
